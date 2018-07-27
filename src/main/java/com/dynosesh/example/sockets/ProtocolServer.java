package com.dynosesh.example.sockets;

import com.dynosesh.ProtocolMonitor;

/**
 * Created by Rory Malcolm on 09/07/2018.
 *
 * @author Rory Malcolm (rorymckenziemalcolm@gmail.com)
 */
public class ProtocolServer implements Runnable {

  private ProtocolMonitor monitor;

  /**
   * ProtocolServer that controls communication, ensuring it complies with the protocol
   *
   * @param monitor The protocol monitor for the server to check against
   */
  ProtocolServer(ProtocolMonitor monitor) {
    this.monitor = monitor;
  }

  /**
   * Starts the server with actors for facilitating communication over the protocol
   */
  @Override
  public void run() {
    SocketActor[] actors = new SocketActor[2];
    actors[0] = new SocketActor(2000, monitor, "0");
    actors[1] = new SocketActor(2001, monitor, "1");
    monitor.addActor(actors[0]);
    monitor.addActor(actors[1]);
    Thread actorOneThread = new Thread(actors[0]);
    Thread actorTwoThread = new Thread(actors[1]);
    actorOneThread.start();
    actorTwoThread.start();
  }
}
