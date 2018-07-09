package com.dynosesh.example.sockets;

import com.dynosesh.Sendable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ReceiverClient implements Runnable {

  private int port;

  ReceiverClient(int port) {
    this.port = port;
  }

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
