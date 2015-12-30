package com.uom.cse.central_node.services;

import java.util.Map;
import java.util.HashMap;
import org.apache.thrift.TEnum;

public enum CommandType implements org.apache.thrift.TEnum {
  USER_DEFINED_COMMAND(0),
  FULL_INFO_COMMAND(1),
  CRITICAL_EVENTS_COMMAND(2),
  INSTANT_EVENTS_COMMAND(3);

  private final int value;

  private CommandType(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  public static CommandType findByValue(int value) { 
    switch (value) {
      case 0:
        return USER_DEFINED_COMMAND;
      case 1:
        return FULL_INFO_COMMAND;
      case 2:
        return CRITICAL_EVENTS_COMMAND;
      case 3:
        return INSTANT_EVENTS_COMMAND;
      default:
        return null;
    }
  }
}
