package com.dynosesh.example.multiparty.bookstore;

import com.dynosesh.Sendable;
import com.dynosesh.example.multiparty.bookstore.types.Okay;
import com.dynosesh.example.multiparty.bookstore.types.SendableDateTime;
import com.dynosesh.example.multiparty.bookstore.types.SendableInteger;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;

public class BookStore implements Runnable {

  private int port;

  public BookStore(int port) {
    this.port = port;
  }

  @Override
  public void run() {
    try {
      Socket socket = new Socket("localhost", port);
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
      objectOutputStream.flush();
      ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
      streamsInitialised(objectOutputStream, objectInputStream);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      System.out.println("Protocol was violated on the BookStore!");
      e.printStackTrace();
    }
  }

  private void streamsInitialised(ObjectOutputStream objectOutputStream,
      ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
    Sendable inputTitle = (Sendable) objectInputStream.readObject();
    System.out.println("BookStore: received " + inputTitle.getPayload());
    objectOutputStream.writeObject(new SendableInteger(10, "1"));
    objectOutputStream.writeObject(new SendableInteger(10, "2"));
    objectOutputStream.writeObject(new Okay(true, "1"));
    Sendable inputOkay = (Sendable) objectInputStream.readObject();
    Sendable inputAddress = (Sendable) objectInputStream.readObject();
    System.out.println("BookStore: received address " + inputAddress.getPayload());
    objectOutputStream.writeObject(new SendableDateTime(new Date(), "2"));
    Sendable inputQuit = (Sendable) objectInputStream.readObject();
  }
}
