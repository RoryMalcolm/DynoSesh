package com.dynosesh.example.sockets;

import com.dynosesh.Sendable;
import java.io.IOException;
import java.io.ObjectInputStream;
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
      for (int i = 0; i < 10; i++) {
        Sendable sendable = (Sendable) objectInputStream.readObject();
        System.out.println(sendable.getPayload());
      }
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
}
