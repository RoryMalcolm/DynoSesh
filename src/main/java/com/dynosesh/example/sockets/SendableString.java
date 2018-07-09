package com.dynosesh.example.sockets;

import com.dynosesh.Sendable;
import java.io.Serializable;

/**
 * Created by Rory Malcolm on 09/07/2018.
 *
 * @author Rory Malcolm (rorymckenziemalcolm@gmail.com)
 */
class SendableString extends Sendable implements Serializable {

  /**
   * Used to facilitate communication over a protocol.
   * <p>
   * Contains a payload, the type of which is checked to ensure it complies to the protocol
   * implementation.
   * </p>
   *
   * @param payload The payload of the message
   */
  public SendableString(String payload, String target) {
    super(payload, target);
  }
}
