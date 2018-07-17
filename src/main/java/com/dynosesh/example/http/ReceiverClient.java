package com.dynosesh.example.http;

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
   * @param port The port to listen on
   */
  ReceiverClient(int port) {
    this.port = port;
  }

  /**
   * Starts the thread to listen over the socket
   */
  @Override
  public void run() {

  }
}
