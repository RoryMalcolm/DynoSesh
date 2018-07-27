package com.dynosesh.example.http;

import com.dynosesh.Sendable;
import com.dynosesh.example.http.types.Certificate;
import com.dynosesh.example.http.types.CertificateVerify;
import com.dynosesh.example.http.types.ChangeCipherSpec;
import com.dynosesh.example.http.types.ClientKeyExchange;
import com.dynosesh.example.http.types.Finished;
import com.dynosesh.example.http.types.Hello;
import com.dynosesh.example.http.types.HelloDone;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Rory Malcolm on 09/07/2018.
 *
 * @author Rory Malcolm (rorymckenziemalcolm@gmail.com)
 */
public class Client implements Runnable {

  private int port;

  /**
   * The client that handles receiving and printing out information
   *
   * @param port The port toActor listen on
   */
  Client(int port) {
    this.port = port;
  }

  /**
   * Starts the thread toActor listen over the socket
   */
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
      System.out.println("Protocol was violated on the client!");
      e.printStackTrace();
    }
  }

  private void streamsInitialised(ObjectOutputStream objectOutputStream,
      ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
    objectOutputStream.writeObject(new Hello("Hello sent from client", "1"));
    Sendable sendable = (Sendable) objectInputStream.readObject();
    System.out.println(sendable.getPayload());
    sendable = (Sendable) objectInputStream.readObject();
    System.out.println(sendable.getPayload());
    if (sendable.getClass() == HelloDone.class) {
      unecryptedPath(objectOutputStream, objectInputStream);
    } else {
      encryptedPath(objectOutputStream, objectInputStream);
    }
  }

  private void unecryptedPath(ObjectOutputStream objectOutputStream,
      ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
    objectOutputStream
        .writeObject(
            new ClientKeyExchange("Client key exchange sent from client", "1"));
    objectOutputStream
        .writeObject(
            new ChangeCipherSpec("Change cipher spec sent from client", "1"));
    objectOutputStream
        .writeObject(
            new Finished("Finished sent from client", "1"));
    Sendable sendable = (Sendable) objectInputStream.readObject();
    System.out.println(sendable.getPayload());
    sendable = (Sendable) objectInputStream.readObject();
    System.out.println(sendable.getPayload());
  }

  private void encryptedPath(ObjectOutputStream objectOutputStream,
      ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
    for (int i = 0; i <= 2; i++) {
      Sendable sendable = (Sendable) objectInputStream.readObject();
      System.out.println(sendable.getPayload());
    }
    objectOutputStream.writeObject(new Certificate("Certificate sent from client",
        "1"));
    objectOutputStream.writeObject(new ClientKeyExchange("ClientKeyExchange sent "
        + "from client",
        "1"));
    objectOutputStream.writeObject(new CertificateVerify("CertificateVerify "
        + "sent from client",
        "1"));
    objectOutputStream
        .writeObject(
            new ChangeCipherSpec("Change cipher spec sent from client", "1"));
    objectOutputStream
        .writeObject(
            new Finished("Finished sent from client", "1"));
    Sendable sendable = (Sendable) objectInputStream.readObject();
    System.out.println(sendable.getPayload());
    sendable = (Sendable) objectInputStream.readObject();
    System.out.println(sendable.getPayload());
  }
}
