package com.uom.cse.central_node.services;


import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.2)", date = "2015-12-28")
public class myFailureInformation implements org.apache.thrift.TBase<myFailureInformation, myFailureInformation._Fields>, java.io.Serializable, Cloneable, Comparable<myFailureInformation> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("myFailureInformation");

  private static final org.apache.thrift.protocol.TField FAILURE__REASON_FIELD_DESC = new org.apache.thrift.protocol.TField("Failure_Reason", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField STATUS_FIELD_DESC = new org.apache.thrift.protocol.TField("Status", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField SUB__STATUS_FIELD_DESC = new org.apache.thrift.protocol.TField("Sub_Status", org.apache.thrift.protocol.TType.STRING, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new myFailureInformationStandardSchemeFactory());
    schemes.put(TupleScheme.class, new myFailureInformationTupleSchemeFactory());
  }

  public String Failure_Reason; // required
  public String Status; // required
  public String Sub_Status; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    FAILURE__REASON((short)1, "Failure_Reason"),
    STATUS((short)2, "Status"),
    SUB__STATUS((short)3, "Sub_Status");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // FAILURE__REASON
          return FAILURE__REASON;
        case 2: // STATUS
          return STATUS;
        case 3: // SUB__STATUS
          return SUB__STATUS;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.FAILURE__REASON, new org.apache.thrift.meta_data.FieldMetaData("Failure_Reason", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.STATUS, new org.apache.thrift.meta_data.FieldMetaData("Status", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.SUB__STATUS, new org.apache.thrift.meta_data.FieldMetaData("Sub_Status", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(myFailureInformation.class, metaDataMap);
  }

  public myFailureInformation() {
  }

  public myFailureInformation(
    String Failure_Reason,
    String Status,
    String Sub_Status)
  {
    this();
    this.Failure_Reason = Failure_Reason;
    this.Status = Status;
    this.Sub_Status = Sub_Status;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public myFailureInformation(myFailureInformation other) {
    if (other.isSetFailure_Reason()) {
      this.Failure_Reason = other.Failure_Reason;
    }
    if (other.isSetStatus()) {
      this.Status = other.Status;
    }
    if (other.isSetSub_Status()) {
      this.Sub_Status = other.Sub_Status;
    }
  }

  public myFailureInformation deepCopy() {
    return new myFailureInformation(this);
  }

  @Override
  public void clear() {
    this.Failure_Reason = null;
    this.Status = null;
    this.Sub_Status = null;
  }

  public String getFailure_Reason() {
    return this.Failure_Reason;
  }

  public myFailureInformation setFailure_Reason(String Failure_Reason) {
    this.Failure_Reason = Failure_Reason;
    return this;
  }

  public void unsetFailure_Reason() {
    this.Failure_Reason = null;
  }

  /** Returns true if field Failure_Reason is set (has been assigned a value) and false otherwise */
  public boolean isSetFailure_Reason() {
    return this.Failure_Reason != null;
  }

  public void setFailure_ReasonIsSet(boolean value) {
    if (!value) {
      this.Failure_Reason = null;
    }
  }

  public String getStatus() {
    return this.Status;
  }

  public myFailureInformation setStatus(String Status) {
    this.Status = Status;
    return this;
  }

  public void unsetStatus() {
    this.Status = null;
  }

  /** Returns true if field Status is set (has been assigned a value) and false otherwise */
  public boolean isSetStatus() {
    return this.Status != null;
  }

  public void setStatusIsSet(boolean value) {
    if (!value) {
      this.Status = null;
    }
  }

  public String getSub_Status() {
    return this.Sub_Status;
  }

  public myFailureInformation setSub_Status(String Sub_Status) {
    this.Sub_Status = Sub_Status;
    return this;
  }

  public void unsetSub_Status() {
    this.Sub_Status = null;
  }

  /** Returns true if field Sub_Status is set (has been assigned a value) and false otherwise */
  public boolean isSetSub_Status() {
    return this.Sub_Status != null;
  }

  public void setSub_StatusIsSet(boolean value) {
    if (!value) {
      this.Sub_Status = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case FAILURE__REASON:
      if (value == null) {
        unsetFailure_Reason();
      } else {
        setFailure_Reason((String)value);
      }
      break;

    case STATUS:
      if (value == null) {
        unsetStatus();
      } else {
        setStatus((String)value);
      }
      break;

    case SUB__STATUS:
      if (value == null) {
        unsetSub_Status();
      } else {
        setSub_Status((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case FAILURE__REASON:
      return getFailure_Reason();

    case STATUS:
      return getStatus();

    case SUB__STATUS:
      return getSub_Status();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case FAILURE__REASON:
      return isSetFailure_Reason();
    case STATUS:
      return isSetStatus();
    case SUB__STATUS:
      return isSetSub_Status();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof myFailureInformation)
      return this.equals((myFailureInformation)that);
    return false;
  }

  public boolean equals(myFailureInformation that) {
    if (that == null)
      return false;

    boolean this_present_Failure_Reason = true && this.isSetFailure_Reason();
    boolean that_present_Failure_Reason = true && that.isSetFailure_Reason();
    if (this_present_Failure_Reason || that_present_Failure_Reason) {
      if (!(this_present_Failure_Reason && that_present_Failure_Reason))
        return false;
      if (!this.Failure_Reason.equals(that.Failure_Reason))
        return false;
    }

    boolean this_present_Status = true && this.isSetStatus();
    boolean that_present_Status = true && that.isSetStatus();
    if (this_present_Status || that_present_Status) {
      if (!(this_present_Status && that_present_Status))
        return false;
      if (!this.Status.equals(that.Status))
        return false;
    }

    boolean this_present_Sub_Status = true && this.isSetSub_Status();
    boolean that_present_Sub_Status = true && that.isSetSub_Status();
    if (this_present_Sub_Status || that_present_Sub_Status) {
      if (!(this_present_Sub_Status && that_present_Sub_Status))
        return false;
      if (!this.Sub_Status.equals(that.Sub_Status))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_Failure_Reason = true && (isSetFailure_Reason());
    list.add(present_Failure_Reason);
    if (present_Failure_Reason)
      list.add(Failure_Reason);

    boolean present_Status = true && (isSetStatus());
    list.add(present_Status);
    if (present_Status)
      list.add(Status);

    boolean present_Sub_Status = true && (isSetSub_Status());
    list.add(present_Sub_Status);
    if (present_Sub_Status)
      list.add(Sub_Status);

    return list.hashCode();
  }

  @Override
  public int compareTo(myFailureInformation other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetFailure_Reason()).compareTo(other.isSetFailure_Reason());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetFailure_Reason()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.Failure_Reason, other.Failure_Reason);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetStatus()).compareTo(other.isSetStatus());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetStatus()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.Status, other.Status);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetSub_Status()).compareTo(other.isSetSub_Status());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSub_Status()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.Sub_Status, other.Sub_Status);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("myFailureInformation(");
    boolean first = true;

    sb.append("Failure_Reason:");
    if (this.Failure_Reason == null) {
      sb.append("null");
    } else {
      sb.append(this.Failure_Reason);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("Status:");
    if (this.Status == null) {
      sb.append("null");
    } else {
      sb.append(this.Status);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("Sub_Status:");
    if (this.Sub_Status == null) {
      sb.append("null");
    } else {
      sb.append(this.Sub_Status);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class myFailureInformationStandardSchemeFactory implements SchemeFactory {
    public myFailureInformationStandardScheme getScheme() {
      return new myFailureInformationStandardScheme();
    }
  }

  private static class myFailureInformationStandardScheme extends StandardScheme<myFailureInformation> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, myFailureInformation struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // FAILURE__REASON
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.Failure_Reason = iprot.readString();
              struct.setFailure_ReasonIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // STATUS
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.Status = iprot.readString();
              struct.setStatusIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // SUB__STATUS
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.Sub_Status = iprot.readString();
              struct.setSub_StatusIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, myFailureInformation struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.Failure_Reason != null) {
        oprot.writeFieldBegin(FAILURE__REASON_FIELD_DESC);
        oprot.writeString(struct.Failure_Reason);
        oprot.writeFieldEnd();
      }
      if (struct.Status != null) {
        oprot.writeFieldBegin(STATUS_FIELD_DESC);
        oprot.writeString(struct.Status);
        oprot.writeFieldEnd();
      }
      if (struct.Sub_Status != null) {
        oprot.writeFieldBegin(SUB__STATUS_FIELD_DESC);
        oprot.writeString(struct.Sub_Status);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class myFailureInformationTupleSchemeFactory implements SchemeFactory {
    public myFailureInformationTupleScheme getScheme() {
      return new myFailureInformationTupleScheme();
    }
  }

  private static class myFailureInformationTupleScheme extends TupleScheme<myFailureInformation> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, myFailureInformation struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetFailure_Reason()) {
        optionals.set(0);
      }
      if (struct.isSetStatus()) {
        optionals.set(1);
      }
      if (struct.isSetSub_Status()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetFailure_Reason()) {
        oprot.writeString(struct.Failure_Reason);
      }
      if (struct.isSetStatus()) {
        oprot.writeString(struct.Status);
      }
      if (struct.isSetSub_Status()) {
        oprot.writeString(struct.Sub_Status);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, myFailureInformation struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.Failure_Reason = iprot.readString();
        struct.setFailure_ReasonIsSet(true);
      }
      if (incoming.get(1)) {
        struct.Status = iprot.readString();
        struct.setStatusIsSet(true);
      }
      if (incoming.get(2)) {
        struct.Sub_Status = iprot.readString();
        struct.setSub_StatusIsSet(true);
      }
    }
  }

}
