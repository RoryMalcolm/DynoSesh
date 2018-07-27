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
        .toActor("0")
        .to("1")
        .connection()
        .toActor("0")
        .to("3")
        .node()
        .payload(SendableString.class)
        .connection()
        .toActor("1")
        .to("2");
  }

  private static void secondSideOfGraph(ProtocolBuilder protocolBuilder) {
    protocolBuilder.node()
        .payload(SendableInteger.class)
        .node()
        .payload(SendableInteger.class)
        .connection()
        .toActor("0")
        .to("4")
        .node()
        .payload(SendableInteger.class)
        .connection()
        .toActor("1")
        .to("5")
        .node()
        .payload(SendableInteger.class);
  }
}
