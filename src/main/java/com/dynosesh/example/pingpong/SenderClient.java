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
      objectOutputStream.flush();
      objectOutputStream.writeObject(new SendableString("Ping", "1"));
      ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
      Sendable sendable = (Sendable) objectInputStream.readObject();
      System.out.println(sendable.getPayload());
      System.exit(0);
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
}
