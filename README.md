# DynoSesh [![Build Status](https://travis-ci.com/RoryMalcolm/DynoSesh.svg?token=FQDDF89L6dtyFjq9mPRs&branch=master)](https://travis-ci.com/RoryMalcolm/DynoSesh)

## Description

When communicating over a protocol, errors can occur throughout the session which corrupt the state of communication between the parties, this corruption can mean that further communication is impossible, and at worst lead to security vulnerabilites. To navigate around this, in part, session types have been developed - these lay the groundwork for “acceptable” communication over a protocol, allowing for the session to self check to ensure that the state is valid. A type relating to the respective protocol is produced, this is a programmatic representation of the expected inputs and outputs in all cases of the protocol’s features, which is then checked against the “real world” implementation of the protocol. Two approaches can be used to perform this analysis; static checking, which checks the state of the communication is valid at compile time and dynamic checking, which performs the analysis at run time as the program is executing. The aim of this project is to produce a working library that implements dynamically checked session types in Java, allowing for the user to define acceptable protocol communication and have the state consistently checked as transaction of messages between parties occurs.

## API Usage
The current API specification is as in the code sample that follows, with the ProtocolBuilder class exposing an internal domain specific language API to build a finite state machine representation of the protocol that will be tested against. When the protocol representation has been fully built, the build() method returns  a Protocol object which can then be attached to a ProtocolMonitor for checking that communication correctly complies to the specification.

```java
ProtocolBuilder protocolBuilder = new ProtocolBuilder();
Protocol protocol = protocolBuilder
      .node()
        .payload(null)
        .connection()
          .fromActor("0")
          .toActor("1")
          .to("1")
      .node()
        .payload(TestClass.class)
        .connection()
          .fromActor("0")
          .toActor("1")
          .to("1")
      .build();
ProtocolMonitor monitor = new ProtocolMonitor(protocol);
monitor.addActor(new QueueActor());
monitor.addActor(new QueueActor());

monitor.send("1", "0", new TestClass("Hello!"));
```

## Examples

Examples of potential use cases are stored within the 'example' package within the ```/src/main/java/com/dynosesh``` folder.
The sockets example shows an implementation of the session type library running over a (local, but model can be expanded further afield) network. The example shows the main implementation stages, with a central ProtocolMonitor used to control and check that communication over the protocol is correct.