package com.dynosesh.example.http;

import com.dynosesh.Sendable;
import com.dynosesh.example.http.types.Certificate;
import com.dynosesh.example.http.types.CertificateRequest;
import com.dynosesh.example.http.types.ChangeCipherSpec;
import com.dynosesh.example.http.types.Finished;
import com.dynosesh.example.http.types.Hello;
import com.dynosesh.example.http.types.HelloDone;
import com.dynosesh.example.http.types.ServerKeyExchange;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Rory Malcolm on 09/07/2018.
 *
 * @author Rory Malcolm (rorymckenziemalcolm@gmail.com)
 */
public class Server implements Runnable {

  private int port;
  private boolean useEncryption;

  /**
   * Client for sending information over the socket
   *
   * @param port The port to send information over
   * @param useEncryption Specifies whether encryption should be used by the server
   */
  Server(int port, boolean useEncryption) {

    this.port = port;
    this.useEncryption = useEncryption;
  }

  /**
   * Sends information over the socket
   */
  @Override
  public void run() {
    try {
      Socket socket = new Socket("localhost", port);
      ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
      streamsInitialised(objectOutputStream, objectInputStream);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      System.out.println("Protocol was violated on the server!");
      e.printStackTrace();
    }
  }

  private void streamsInitialised(ObjectOutputStream objectOutputStream,
      ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
    Sendable sendable = (Sendable) objectInputStream.readObject();
    System.out.println(sendable.getPayload());
    objectOutputStream.writeObject(new Hello("Hello from the server", "0"));
    if (useEncryption) {
      encryptedPath(objectOutputStream, objectInputStream);
    } else {
      unencryptedPath(objectOutputStream, objectInputStream);
    }
    finalPath(objectOutputStream, objectInputStream);
  }

  private void encryptedPath(ObjectOutputStream objectOutputStream,
      ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
    objectOutputStream.writeObject(new Certificate("Certificate from the server",
        "0"));
    objectOutputStream.writeObject(new ServerKeyExchange("ServerKeyExchange "
        + "from the server", "0"));
    objectOutputStream
        .writeObject(new CertificateRequest("CertificateRequest from the server",
            "0"));
    objectOutputStream.writeObject(new HelloDone("HelloDone from the server",
        "0"));
    Sendable sendable = (Sendable) objectInputStream.readObject();
    System.out.println(sendable.getPayload());
    sendable = (Sendable) objectInputStream.readObject();
    System.out.println(sendable.getPayload());
    sendable = (Sendable) objectInputStream.readObject();
    System.out.println(sendable.getPayload());
  }

  private void unencryptedPath(ObjectOutputStream objectOutputStream,
      ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
    objectOutputStream.writeObject(new HelloDone("HelloDone from the server", "0"));
    Sendable sendable = (Sendable) objectInputStream.readObject();
    System.out.println(sendable.getPayload());
  }

  private void finalPath(ObjectOutputStream objectOutputStream,
      ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
    Sendable sendable = (Sendable) objectInputStream.readObject();
    System.out.println(sendable.getPayload());
    sendable = (Sendable) objectInputStream.readObject();
    System.out.println(sendable.getPayload());
    objectOutputStream.writeObject(new ChangeCipherSpec("ChangeCipherSpec from the server",
        "0"));
    objectOutputStream.writeObject(new Finished("Finished from the server", "0"));
  }
}
