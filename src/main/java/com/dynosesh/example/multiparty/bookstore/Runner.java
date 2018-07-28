package com.dynosesh.example.multiparty.bookstore;

import com.dynosesh.ProtocolMonitor;
import com.dynosesh.example.multiparty.bookstore.types.Okay;
import com.dynosesh.example.multiparty.bookstore.types.Quit;
import com.dynosesh.example.multiparty.bookstore.types.SendableDateTime;
import com.dynosesh.example.multiparty.bookstore.types.SendableInteger;
import com.dynosesh.example.multiparty.bookstore.types.SendableString;
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
    buildProtocol(protocolBuilder);
    Protocol protocol = protocolBuilder
        .build();
    ProtocolMonitor monitor = new ProtocolMonitor(protocol);
    Thread monitorThread = new Thread(new ProtocolServer(monitor));
    Thread bookStoreThread = new Thread(new BookStore(2050));
    Thread buyerOneThread = new Thread(new BuyerOne(2051));
    Thread buyerTwoThread = new Thread(new BuyerTwo(2052));
    monitorThread.start();
    bookStoreThread.start();
    buyerOneThread.start();
    buyerTwoThread.start();
  }

  private static void buildProtocol(ProtocolBuilder protocolBuilder) {
    protocolBuilder
        .node()
          .payload(null)
          .connection()
            .toActor("0")
            .fromActor("1")
            .to("1")
        .node()
          .payload(SendableString.class)
          .connection()
            .toActor("1")
            .fromActor("0")
            .to("2")
        .node()
          .payload(SendableInteger.class)
          .connection()
            .toActor("2")
            .fromActor("0")
            .to("3")
        .node()
          .payload(SendableInteger.class)
          .connection()
            .toActor("1")
            .fromActor("0")
            .to("4")
        .node()
          .payload(Okay.class)
          .connection()
            .toActor("2")
            .fromActor("1")
            .to("5")
        .node()
          .payload(SendableInteger.class)
          .connection()
            .toActor("0")
            .fromActor("2")
            .to("6")
        .node()
          .payload(Okay.class)
          .connection()
            .toActor("0")
            .fromActor("2")
            .to("7")
        .node()
          .payload(SendableString.class)
          .connection()
            .toActor("2")
            .fromActor("0")
            .to("8")
        .node()
          .payload(SendableDateTime.class)
          .connection()
            .toActor("0")
            .fromActor("2")
            .to("9")
        .node()
          .payload(Quit.class);
  }
}
