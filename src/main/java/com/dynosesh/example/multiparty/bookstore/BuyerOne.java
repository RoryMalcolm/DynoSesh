package com.dynosesh.example.multiparty.bookstore;

import com.dynosesh.Sendable;
import com.dynosesh.example.multiparty.bookstore.types.SendableInteger;
import com.dynosesh.example.multiparty.bookstore.types.SendableString;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class BuyerOne implements Runnable {

  private int port;

  public BuyerOne(int port) {
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
      System.out.println("Protocol was violated on BuyerOne!");
      e.printStackTrace();
    }

  }

  private void streamsInitialised(ObjectOutputStream objectOutputStream,
      ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
    objectOutputStream.writeObject(new SendableString("The New Old World by Perry Anderson",
        "0"));
    Sendable quote = (Sendable) objectInputStream.readObject();
    System.out.println(" BuyerOne: quote is " + quote.getPayload());
    Sendable okay = (Sendable) objectInputStream.readObject();
    System.out.println(" BuyerOne: received Okay");
    Integer returnValue = (Integer) quote.getPayload();
    objectOutputStream.writeObject(new SendableInteger(returnValue / 2, "2"));
  }

}
