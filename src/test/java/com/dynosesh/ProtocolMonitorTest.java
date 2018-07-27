package com.dynosesh;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.dynosesh.actor.QueueActor;
import com.dynosesh.exceptions.InvalidSessionException;
import com.dynosesh.protocol.Connection;
import com.dynosesh.protocol.Node;
import com.dynosesh.protocol.Protocol;
import com.dynosesh.protocol.ProtocolBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Created by Rory Malcolm on 19/06/2018.
 */
class ProtocolMonitorTest {

  private ProtocolMonitor protocolMonitor;

  @BeforeEach
  void setUp() {
    ProtocolBuilder protocolBuilder = new ProtocolBuilder();
    Node startNode = new Node(null);
    Node mediumNode = new Node(TestLayer.class);
    Node finishNode = new Node(TestLayer.class);
    startNode.addConnection(new Connection("1", "1"));
    mediumNode.addConnection(new Connection("1", "2"));
    protocolBuilder.addNode(startNode);
    protocolBuilder.addNode(mediumNode);
    protocolBuilder.addNode(finishNode);
    Protocol protocol = protocolBuilder.build();
    protocolMonitor = new ProtocolMonitor(protocol);
    for (int i = 0; i < 10; i++) {
      protocolMonitor.addActor(new QueueActor());
    }
  }

  @Test
  void normalExecution() {
    try {
      protocolMonitor.send("1",
          "0", new TestLayer("Hello world!"));
      protocolMonitor.send("1",
          "0", new TestLayer("Alright mate!"));
    } catch (InvalidSessionException e) {
      e.printStackTrace();
      fail("Exception thrown");
    }
  }

  @Test
  void failingExecutionWrongType() {
    boolean didThrow = false;
    class WrongType extends Sendable {

      /**
       * Used toActor facilitate communication over a protocol.
       * <p>
       * Contains a payload, the type of which is checked toActor ensure it complies toActor the protocol
       * implementation.
       * </p>
       *
       * @param payload The payload of the message
       */
      private WrongType(Object payload) {
        super(payload);
      }
    }
    try {
      protocolMonitor.send("0",
          "1", new WrongType("Should Fail"));
    } catch (InvalidSessionException e) {
      didThrow = true;
    }
    assertTrue(didThrow);
  }

  @Test
  void failingExecutionWrongAddress() {
    boolean didThrow = false;
    try {
      protocolMonitor.send("0",
          "2", new TestLayer("Hello world!"));
    } catch (InvalidSessionException e) {
      didThrow = true;
    }
    assertTrue(didThrow);
  }

  @Test
  void httpDemonstration() {
    class HttpRequest extends Sendable {

      /**
       * Used toActor facilitate communication over a protocol.
       * <p>
       * Contains a payload, the type of which is checked toActor ensure it complies toActor the protocol
       * implementation.
       * </p>
       *
       * @param payload The payload of the message
       */
      private HttpRequest(Object payload) {
        super(payload);
      }
    }
    class HttpResponse extends Sendable {

      /**
       * Used toActor facilitate communication over a protocol.
       * <p>
       * Contains a payload, the type of which is checked toActor ensure it complies toActor the protocol
       * implementation.
       * </p>
       *
       * @param payload The payload of the message
       */
      private HttpResponse(Object payload) {
        super(payload);
      }
    }

    ProtocolBuilder protocolBuilder = new ProtocolBuilder();
    Node startNode = new Node(null);
    Node mediumNode = new Node(HttpRequest.class);
    Node finishNode = new Node(HttpResponse.class);
    startNode.addConnection(new Connection("0", "1"));
    mediumNode.addConnection(new Connection("1", "2"));
    protocolBuilder.addNode(startNode);
    protocolBuilder.addNode(mediumNode);
    protocolBuilder.addNode(finishNode);
    protocolMonitor = new ProtocolMonitor(protocolBuilder.build());
    for (int i = 0; i < 10; i++) {
      protocolMonitor.addActor(new QueueActor());
    }
    try {
      protocolMonitor.send("0",
          "1", new HttpRequest("GET"));
      protocolMonitor.send("1",
          "0", new HttpResponse("Payload"));

    } catch (InvalidSessionException e) {
      e.printStackTrace();
      fail("Exception thrown");
    }
  }

  @Test
  void recursiveGraphTest() {
    ProtocolBuilder protocolBuilder = new ProtocolBuilder();
    Node startNode = new Node(null);
    Node mediumNode = new Node(TestLayer.class);
    startNode.addConnection(new Connection("1", "1"));
    mediumNode.addConnection(new Connection("1", "1"));
    protocolBuilder.addNode(startNode);
    protocolBuilder.addNode(mediumNode);
    Protocol protocol = protocolBuilder.build();
    protocolMonitor = new ProtocolMonitor(protocol);
    for (int i = 0; i < 10; i++) {
      protocolMonitor.addActor(new QueueActor());
    }
    try {
      protocolMonitor.send("1",
          "0", new TestLayer("Hello world!"));
    } catch (InvalidSessionException e) {
      fail("Errored on choice");
    }
    for (int i = 0; i < 1000; i++) {
      try {
        protocolMonitor.send("1",
            "0", new TestLayer("Hello world!"));
      } catch (InvalidSessionException e) {
        fail("Errored on choice");
      }
    }
  }

  @Test
  void multiDirectionalGraphTest() {
    class ChoiceOne extends Sendable {

      /**
       * Used toActor facilitate communication over a protocol.
       * <p>
       * Contains a payload, the type of which is checked toActor ensure it complies toActor the protocol
       * implementation.
       * </p>
       *
       * @param payload The payload of the message
       */
      private ChoiceOne(Object payload) {
        super(payload);
      }
    }

    class ChoiceTwo extends Sendable {

      /**
       * Used toActor facilitate communication over a protocol.
       * <p>
       * Contains a payload, the type of which is checked toActor ensure it complies toActor the protocol
       * implementation.
       * </p>
       *
       * @param payload The payload of the message
       */
      private ChoiceTwo(Object payload) {
        super(payload);
      }
    }
    ProtocolBuilder protocolBuilder = new ProtocolBuilder();
    Node startNode = new Node(null);
    Node mediumNode = new Node(TestLayer.class);
    Node finalChoiceOne = new Node(ChoiceOne.class);
    Node finalChoiceTwo = new Node(ChoiceTwo.class);
    startNode.addConnection(new Connection("1", "1"));
    mediumNode.addConnection(new Connection("1", "2"));
    mediumNode.addConnection(new Connection("1", "3"));
    protocolBuilder.addNode(startNode);
    protocolBuilder.addNode(mediumNode);
    protocolBuilder.addNode(finalChoiceOne);
    protocolBuilder.addNode(finalChoiceTwo);
    Protocol protocol = protocolBuilder.build();
    protocolMonitor = new ProtocolMonitor(protocol);
    for (int i = 0; i < 10; i++) {
      protocolMonitor.addActor(new QueueActor());
    }
    try {
      protocolMonitor.send("1",
          "0", new TestLayer("Hello world!"));
      protocolMonitor.send("1",
          "0", new ChoiceOne("ChoiceOne"));
    } catch (InvalidSessionException e) {
      fail("Errored on choice");
    }
    protocolBuilder = new ProtocolBuilder();
    startNode = new Node(null);
    mediumNode = new Node(TestLayer.class);
    finalChoiceOne = new Node(ChoiceOne.class);
    finalChoiceTwo = new Node(ChoiceTwo.class);
    startNode.addConnection(new Connection("1", "1"));
    mediumNode.addConnection(new Connection("1", "2"));
    mediumNode.addConnection(new Connection("1", "3"));
    protocolBuilder.addNode(startNode);
    protocolBuilder.addNode(mediumNode);
    protocolBuilder.addNode(finalChoiceOne);
    protocolBuilder.addNode(finalChoiceTwo);
    protocol = protocolBuilder.build();
    protocolMonitor = new ProtocolMonitor(protocol);
    for (int i = 0; i < 10; i++) {
      protocolMonitor.addActor(new QueueActor());
    }
    try {
      protocolMonitor.send("1",
          "0", new TestLayer("Hello world!"));
      protocolMonitor.send("1",
          "0", new ChoiceTwo("ChoiceTwo"));
    } catch (InvalidSessionException e) {
      fail("Errored on choice");
    }
  }

  class TestLayer extends Sendable {

    TestLayer(String payload) {
      super(payload);
    }
  }
}