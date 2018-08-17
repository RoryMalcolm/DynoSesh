package com.dynosesh.example.pingpong;

import com.dynosesh.Sendable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Rory Malcolm on 09/07/2018.
 *
 * @author Rory Malcolm (rorymckenziemalcolm@gmail.com)
 */
public class ReceiverClient implements Runnable {

  private int port;

  /**
   * The client that handles receiving and printing out information
   *
   * @param port The port to listen on
   */
  ReceiverClient(int port) {
    this.port = port;
  }

  /**
   * Starts the thread to listen over the socket
   */
  @Override
  public void run() {
    try {
      Socket socket = new Socket("localhost", port);
      ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
      Sendable sendable = (Sendable) objectInputStream.readObject();
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
      objectOutputStream.flush();
      System.out.println(sendable.getPayload());
      objectOutputStream.writeObject(new SendableString("Pong", "0"));
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
}
