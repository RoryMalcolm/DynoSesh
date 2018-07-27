package com.dynosesh.example.sockets;

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
   * @param port The port toActor listen on
   */
  ReceiverClient(int port) {
    this.port = port;
  }

  /**
   * Starts the thread toActor listen over the socket
   */
  @Override
  public void run() {
    try {
      Socket socket = new Socket("localhost", port);
      ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
      Sendable sendable = (Sendable) objectInputStream.readObject();
      if (sendable.getClass() == SendableString.class) {
        System.out.println(sendable.getPayload());
        String string = (String) sendable.getPayload();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(new SendableInteger(string.length(), "0"));
      } else if (sendable.getClass() == SendableInteger.class) {
        int firstInt = (int) sendable.getPayload();
        System.out.println(sendable.getPayload());
        sendable = (Sendable) objectInputStream.readObject();
        System.out.println(sendable.getPayload());
        int secondInt = (int) sendable.getPayload();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(new SendableInteger(firstInt + secondInt, "0"));
      }
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
}
