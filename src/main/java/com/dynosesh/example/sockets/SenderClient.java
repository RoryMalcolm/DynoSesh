package com.dynosesh.example.sockets;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SenderClient implements Runnable {

  private int port;

  SenderClient(int port) {
    this.port = port;
  }

  @Override
  public void run() {
    try {
      Socket socket = new Socket("localhost", port);
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
      objectOutputStream.writeObject(new SendableString("Hello, world", "1"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
