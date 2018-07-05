package com.dynosesh.actor;

import com.dynosesh.Sendable;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketActor implements Actor {

  private Socket client;

  /**
   * Creates a WebSocket on the inputted port
   *
   * @param port The port for which the socket should be initialised on
   */
  SocketActor(int port) {
    try {
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
  public Sendable receiveTask() {
    try {
      InputStream inputStream = client.getInputStream();
      ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
      Sendable sendable = (Sendable) objectInputStream.readObject();
      objectInputStream.close();
      return sendable;
    } catch (IOException | ClassNotFoundException e) {
      return null;
    }
  }
}
