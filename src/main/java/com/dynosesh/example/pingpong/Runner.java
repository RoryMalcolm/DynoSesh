package com.dynosesh.example.pingpong;

import com.dynosesh.ProtocolMonitor;
import com.dynosesh.protocol.Protocol;
import com.dynosesh.protocol.ProtocolBuilder;

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
    ProtocolBuilder protocolBuilder = new ProtocolBuilder();
    setupProtocol(protocolBuilder);
    Protocol protocol = protocolBuilder
        .build();
    ProtocolMonitor monitor = new ProtocolMonitor(protocol);
    Thread monitorThread = new Thread(new ProtocolServer(monitor));
    Thread thread = new Thread(new SenderClient(2000));
    Thread thread1 = new Thread(new ReceiverClient(2001));
    monitorThread.start();
    thread1.start();
    thread.start();
  }

  private static void setupProtocol(ProtocolBuilder protocolBuilder) {
    protocolBuilder
        .node()
          .payload(null)
          .connection()
            .toActor("1")
            .fromActor("0")
            .to("1")
        .node()
          .payload(SendableString.class)
          .connection()
            .toActor("0")
            .fromActor("1")
            .to("2")
        .node()
          .payload(SendableString.class);
  }
}
