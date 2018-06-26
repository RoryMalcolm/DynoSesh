package com.dynosesh;

import com.dynosesh.exceptions.InvalidSessionException;
import com.dynosesh.protocol.Connection;
import com.dynosesh.protocol.Node;
import com.dynosesh.protocol.Protocol;
import com.dynosesh.protocol.ProtocolFactory;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;

/**
 * Created by Rory Malcolm on 19/06/2018.
 */
public class ProtocolMonitorTest {

  private ProtocolMonitor protocolMonitor;

  @Before
  public void setUp() {
    ProtocolFactory protocolFactory = new ProtocolFactory();
    Node startNode = new Node(null);
    Node mediumNode = new Node(TestLayer.class);
    Node finishNode = new Node(TestLayer.class);
    startNode.addConnection(new Connection("1", mediumNode));
    mediumNode.addConnection(new Connection("1", finishNode));
    protocolFactory.addNode(startNode);
    protocolFactory.addNode(mediumNode);
    protocolFactory.addNode(finishNode);
    Protocol protocol = protocolFactory.build();
    this.protocolMonitor = new ProtocolMonitor(protocol);
    for (int i = 0; i < 10; i++) {
      this.protocolMonitor.addActor(String.valueOf(i), new Actor());
    }
  }

  @Test
  public void normalExecution() {
    try {
      this.protocolMonitor.send("1",
          "0", new TestLayer("Hello world!"));
      this.protocolMonitor.send("1",
          "0", new TestLayer("Alright mate!"));
    } catch (InvalidSessionException e) {
      e.printStackTrace();
      fail("Exception thrown");
    }
  }

  @Test
  public void failingExecutionWrongType() {
    boolean didThrow = false;
    class WrongType extends Sendable {
      /**
       * Used to facilitate communication over a protocol.
       * <p>
       * Contains a payload, the type of which is checked to ensure it complies to the protocol
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
      this.protocolMonitor.send("0",
          "1", new WrongType("Should Fail"));
    } catch (InvalidSessionException e) {
      didThrow = true;
    }
    assertTrue(didThrow);
  }

  @Test
  public void failingExecutionWrongAddress() {
    boolean didThrow = false;
    try {
      this.protocolMonitor.send("0",
          "2", new TestLayer("Hello world!"));
    } catch (InvalidSessionException e) {
      didThrow = true;
    }
    assertTrue(didThrow);
  }

  @Test
  public void httpDemonstration() {
    class HttpRequest extends Sendable {

      /**
       * Used to facilitate communication over a protocol.
       * <p>
       * Contains a payload, the type of which is checked to ensure it complies to the protocol
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
       * Used to facilitate communication over a protocol.
       * <p>
       * Contains a payload, the type of which is checked to ensure it complies to the protocol
       * implementation.
       * </p>
       *
       * @param payload The payload of the message
       */
      private HttpResponse(Object payload) {
        super(payload);
      }
    }

    ProtocolFactory protocolFactory = new ProtocolFactory();
    Node startNode = new Node(null);
    Node mediumNode = new Node(HttpRequest.class);
    Node finishNode = new Node(HttpResponse.class);
    startNode.addConnection(new Connection("0", mediumNode));
    mediumNode.addConnection(new Connection("1", finishNode));
    protocolFactory.addNode(startNode);
    protocolFactory.addNode(mediumNode);
    protocolFactory.addNode(finishNode);
    this.protocolMonitor = new ProtocolMonitor(protocolFactory.build());
    for (int i = 0; i < 10; i++) {
      this.protocolMonitor.addActor(String.valueOf(i), new Actor());
    }
    try {
      this.protocolMonitor.send("0",
          "1", new HttpRequest("GET"));
      this.protocolMonitor.send("1",
          "0", new HttpResponse("Payload"));

    } catch (InvalidSessionException e) {
      e.printStackTrace();
      fail("Exception thrown");
    }
  }

  @Test
  public void recursiveGraphTest() {
    ProtocolFactory protocolFactory = new ProtocolFactory();
    Node startNode = new Node(null);
    Node mediumNode = new Node(TestLayer.class);
    startNode.addConnection(new Connection("1", mediumNode));
    mediumNode.addConnection(new Connection("1", mediumNode));
    protocolFactory.addNode(startNode);
    protocolFactory.addNode(mediumNode);
    Protocol protocol = protocolFactory.build();
    this.protocolMonitor = new ProtocolMonitor(protocol);
    for (int i = 0; i < 10; i++) {
      this.protocolMonitor.addActor(String.valueOf(i), new Actor());
    }
    try {
      this.protocolMonitor.send("1",
          "0", new TestLayer("Hello world!"));
    } catch (InvalidSessionException e) {
      fail();
    }
    for (int i = 0; i < 1000; i++) {
      try {
        this.protocolMonitor.send("1",
            "0", new TestLayer("Hello world!"));
      } catch (InvalidSessionException e) {
        fail();
      }
    }
  }

  @Test
  public void multiDirectionalGraphTest() {
    class ChoiceOne extends Sendable {

      /**
       * Used to facilitate communication over a protocol.
       * <p>
       * Contains a payload, the type of which is checked to ensure it complies to the protocol
       * implementation.
       * </p>
       *
       * @param payload The payload of the message
       */
      public ChoiceOne(Object payload) {
        super(payload);
      }
    }

    class ChoiceTwo extends Sendable {

      /**
       * Used to facilitate communication over a protocol.
       * <p>
       * Contains a payload, the type of which is checked to ensure it complies to the protocol
       * implementation.
       * </p>
       *
       * @param payload The payload of the message
       */
      public ChoiceTwo(Object payload) {
        super(payload);
      }
    }
    ProtocolFactory protocolFactory = new ProtocolFactory();
    Node startNode = new Node(null);
    Node mediumNode = new Node(TestLayer.class);
    Node finalChoiceOne = new Node(ChoiceOne.class);
    Node finalChoiceTwo = new Node(ChoiceTwo.class);
    startNode.addConnection(new Connection("1", mediumNode));
    mediumNode.addConnection(new Connection("1", finalChoiceOne));
    mediumNode.addConnection(new Connection("1", finalChoiceTwo));
    protocolFactory.addNode(startNode);
    protocolFactory.addNode(mediumNode);
    Protocol protocol = protocolFactory.build();
    this.protocolMonitor = new ProtocolMonitor(protocol);
    for (int i = 0; i < 10; i++) {
      this.protocolMonitor.addActor(String.valueOf(i), new Actor());
    }
    try {
      this.protocolMonitor.send("1",
          "0", new TestLayer("Hello world!"));
      this.protocolMonitor.send("1",
          "0", new ChoiceOne("ChoiceOne"));
    } catch (InvalidSessionException e) {
      fail();
    }
    protocolFactory = new ProtocolFactory();
    startNode = new Node(null);
    mediumNode = new Node(TestLayer.class);
    finalChoiceOne = new Node(ChoiceOne.class);
    finalChoiceTwo = new Node(ChoiceTwo.class);
    startNode.addConnection(new Connection("1", mediumNode));
    mediumNode.addConnection(new Connection("1", finalChoiceOne));
    mediumNode.addConnection(new Connection("1", finalChoiceTwo));
    protocolFactory.addNode(startNode);
    protocolFactory.addNode(mediumNode);
    protocol = protocolFactory.build();
    this.protocolMonitor = new ProtocolMonitor(protocol);
    for (int i = 0; i < 10; i++) {
      this.protocolMonitor.addActor(String.valueOf(i), new Actor());
    }
    try {
      this.protocolMonitor.send("1",
          "0", new TestLayer("Hello world!"));
      this.protocolMonitor.send("1",
          "0", new ChoiceTwo("ChoiceTwo"));
    } catch (InvalidSessionException e) {
      fail();
    }
  }

  class TestLayer extends Sendable {
    TestLayer(String payload) {
      super(payload);
    }
  }
}