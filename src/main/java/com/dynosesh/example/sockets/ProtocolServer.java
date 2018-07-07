package com.dynosesh.example.sockets;

import com.dynosesh.ProtocolMonitor;

public class ProtocolServer implements Runnable {

  private ProtocolMonitor monitor;

  ProtocolServer(ProtocolMonitor monitor) {
    this.monitor = monitor;
  }

  @Override
  public void run() {
    monitor.addActor(new SocketActor(100, monitor, "0"));
    monitor.addActor(new SocketActor(101, monitor, "1"));
  }
}
