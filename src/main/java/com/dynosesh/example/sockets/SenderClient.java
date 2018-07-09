package com.dynosesh.example.sockets;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Rory Malcolm on 09/07/2018.
 *
 * @author Rory Malcolm (rorymckenziemalcolm@gmail.com)
 */
public class SenderClient implements Runnable {

  private int port;

  /**
   * Client for sending information over the socket
   *
   * @param port The port to send information over
   */
  SenderClient(int port) {
    this.port = port;
  }

  /**
   * Sends information over the socket
   */
  @Override
  public void run() {
    try {
      Socket socket = new Socket("localhost", port);
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
      for (int i = 0; i < 10; i++) {
        objectOutputStream.writeObject(new SendableString("Hello, world", "1"));
        objectOutputStream.flush();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
