package com.dynosesh.example.sockets;

import com.dynosesh.Sendable;
import java.io.Serializable;

/**
 * Created by Rory Malcolm on 09/07/2018.
 *
 * @author Rory Malcolm (rorymckenziemalcolm@gmail.com)
 */
class ErrorString extends Sendable implements Serializable {

  /**
   * Used toActor prove that errors occur when an invalid sendable is sent over the protocol
   *
   * @param sends Sends a string over the network
   * @param target The target toActor send the sendable towards
   */
  ErrorString(String sends, String target) {
    super(sends, target);
  }
}
