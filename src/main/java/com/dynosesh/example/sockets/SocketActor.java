package com.dynosesh.example.sockets;

import com.dynosesh.ProtocolMonitor;
import com.dynosesh.Sendable;
import com.dynosesh.actor.Actor;
import com.dynosesh.exceptions.InvalidSessionException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketActor implements Actor, Runnable {

  private Socket client;
  private ProtocolMonitor protocolMonitor;
  private String address;
  private ObjectOutputStream objectOutputStream;
  private ObjectInputStream objectInputStream;

  /**
   * Creates a WebSocket on the inputted port
   *
   * @param port The port for which the socket should be initialised on
   */
  SocketActor(int port, ProtocolMonitor protocolMonitor, String address) {
    try {
      this.objectInputStream = null;
      this.objectOutputStream = null;
      this.protocolMonitor = protocolMonitor;
      this.address = address;
      ServerSocket webSocket = new ServerSocket(port);
      client = webSocket.accept();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Sends a Sendable object over the output stream of the WebSocket
   *
   * @param sendable The object for serialisation over the WebSocket
   */
  @Override
  public void sendTask(Sendable sendable) {
    try {
      if (this.objectOutputStream == null) {
        OutputStream outputStream = client.getOutputStream();
        this.objectOutputStream = new ObjectOutputStream(outputStream);
      }
      this.objectOutputStream.writeObject(sendable);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Receives an object over a WebSocket
   *
   * @return The object from the WebSocket
   */
  @Override
  public Sendable receiveTask() throws InvalidSessionException {
    try {
      if (this.objectInputStream == null) {
        InputStream inputStream = client.getInputStream();
        this.objectInputStream = new ObjectInputStream(inputStream);
      }
      Sendable sendable = (Sendable) this.objectInputStream.readObject();
      protocolMonitor.send(this.address, sendable.getTarget(), sendable);
      return sendable;
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
      throw new InvalidSessionException("Invalid session");
    }
  }

  @Override
  public void run() {
    while (true) {
      try {
        receiveTask();
      } catch (InvalidSessionException e) {
        System.out.println("Invalid Session.... \n Closing....");
        System.exit(1);
      }
    }
  }
}
