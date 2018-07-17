package com.dynosesh.example.http;

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
  }
}
