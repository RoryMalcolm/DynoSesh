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

public class SocketActor implements Actor {

  private Socket client;
  private ProtocolMonitor protocolMonitor;
  private String address;

  /**
   * Creates a WebSocket on the inputted port
   *
   * @param port The port for which the socket should be initialised on
   */
  SocketActor(int port, ProtocolMonitor protocolMonitor, String address) {
    try {
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
      OutputStream outputStream = client.getOutputStream();
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
      objectOutputStream.writeObject(sendable);
      objectOutputStream.close();
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
      InputStream inputStream = client.getInputStream();
      ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
      Sendable sendable = (Sendable) objectInputStream.readObject();
      protocolMonitor.send(this.address, sendable.getTarget(), sendable);
      objectInputStream.close();
      return sendable;
    } catch (IOException | ClassNotFoundException e) {
      return null;
    }
  }
}
