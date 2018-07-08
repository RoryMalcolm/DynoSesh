package com.dynosesh.example.sockets;

import com.dynosesh.ProtocolMonitor;

public class ProtocolServer implements Runnable {

  private ProtocolMonitor monitor;

  ProtocolServer(ProtocolMonitor monitor) {
    this.monitor = monitor;
  }

  @Override
  public void run() {
    SocketActor[] actors = new SocketActor[2];
    actors[0] = new SocketActor(100, monitor, "0");
    actors[1] = new SocketActor(101, monitor, "1");
    monitor.addActor(actors[0]);
    monitor.addActor(actors[1]);
    Thread actorOneThread = new Thread(actors[0]);
    Thread actorTwoThread = new Thread(actors[1]);
    actorOneThread.start();
    actorTwoThread.start();
  }
}
