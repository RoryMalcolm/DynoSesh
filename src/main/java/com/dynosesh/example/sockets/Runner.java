package com.dynosesh.example.sockets;

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
    firstSideOfGraph(protocolBuilder);
    secondSideOfGraph(protocolBuilder);
    Protocol protocol = protocolBuilder
        .build();
    ProtocolMonitor monitor = new ProtocolMonitor(protocol);
    Thread monitorThread = new Thread(new ProtocolServer(monitor));
    Thread thread = new Thread(new SenderClient(2000));
    Thread thread1 = new Thread(new ReceiverClient(2001));
    monitorThread.start();
    thread.start();
    thread1.start();
  }

  private static void firstSideOfGraph(ProtocolBuilder protocolBuilder) {
    protocolBuilder.node()
        .payload(null)
        .connection()
        .actor("0")
        .toActor("1")
        .connection()
        .actor("0")
        .toActor("3")
        .node()
        .payload(SendableString.class)
        .connection()
        .actor("1")
        .toActor("2");
  }

  private static void secondSideOfGraph(ProtocolBuilder protocolBuilder) {
    protocolBuilder.node()
        .payload(SendableInteger.class)
        .node()
        .payload(SendableInteger.class)
        .connection()
        .actor("0")
        .toActor("4")
        .node()
        .payload(SendableInteger.class)
        .connection()
        .actor("1")
        .toActor("5")
        .node()
        .payload(SendableInteger.class);
  }
}
