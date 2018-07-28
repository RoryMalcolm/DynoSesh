package com.dynosesh.example.multiparty.bookstore;

import com.dynosesh.Sendable;
import com.dynosesh.example.multiparty.bookstore.types.Okay;
import com.dynosesh.example.multiparty.bookstore.types.Quit;
import com.dynosesh.example.multiparty.bookstore.types.SendableString;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class BuyerTwo implements Runnable {

  private int port;

  public BuyerTwo(int port) {
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
      System.out.println("Protocol was violated on BuyerTwo!");
      e.printStackTrace();
    }
  }

  private void streamsInitialised(ObjectOutputStream objectOutputStream,
      ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
    Sendable quote = (Sendable) objectInputStream.readObject();
    System.out.println(" BuyerTwo: quote is " + quote.getPayload());
    Sendable quoteHalved = (Sendable) objectInputStream.readObject();
    System.out.println(" BuyerTwo: split quote is " + quoteHalved.getPayload());
    objectOutputStream.writeObject(new Okay(true, "0"));
    objectOutputStream.writeObject(new SendableString("64 Zoo Lane", "0"));
    Sendable currentDate = (Sendable) objectInputStream.readObject();
    System.out.println(" BuyerTwo: received " + currentDate.getPayload());
    objectOutputStream.writeObject(new Quit(true, "0"));
  }
}
