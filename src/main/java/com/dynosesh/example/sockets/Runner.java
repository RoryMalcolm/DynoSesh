package com.dynosesh.example.sockets;

import com.dynosesh.ProtocolMonitor;
import com.dynosesh.protocol.Protocol;
import com.dynosesh.protocol.ProtocolFactory;

/**
 * Created by Rory Malcolm on 09/07/2018.
 *
 * @author Rory Malcolm (rorymckenziemalcolm@gmail.com)
 */
public class Runner {

  /**
   * Starts the protocol showcase
   *
   * @param args Arguments from the command line - unused
   */
  public static void main(String[] args) {
    ProtocolFactory protocolFactory = new ProtocolFactory();
    Protocol protocol = protocolFactory
        .node()
        .payload(null)
        .connection()
        .actor("0")
        .to("1")
        .node()
        .payload(SendableString.class)
        .connection()
        .actor("0")
        .to("1")
        .build();
    ProtocolMonitor monitor = new ProtocolMonitor(protocol);
    Thread monitorThread = new Thread(new ProtocolServer(monitor));
    Thread thread = new Thread(new SenderClient(100));
    Thread thread1 = new Thread(new ReceiverClient(101));
    monitorThread.start();
    thread.start();
    thread1.start();
  }
}
