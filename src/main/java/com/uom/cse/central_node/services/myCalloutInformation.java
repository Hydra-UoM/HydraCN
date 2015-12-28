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
public class myCalloutInformation implements org.apache.thrift.TBase<myCalloutInformation, myCalloutInformation._Fields>, java.io.Serializable, Cloneable, Comparable<myCalloutInformation> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("myCalloutInformation");

  private static final org.apache.thrift.protocol.TField CALLOUT__ID_FIELD_DESC = new org.apache.thrift.protocol.TField("Callout_ID", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField CALLOUT__NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("Callout_Name", org.apache.thrift.protocol.TType.STRING, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new myCalloutInformationStandardSchemeFactory());
    schemes.put(TupleScheme.class, new myCalloutInformationTupleSchemeFactory());
  }

  public String Callout_ID; // required
  public String Callout_Name; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    CALLOUT__ID((short)1, "Callout_ID"),
    CALLOUT__NAME((short)2, "Callout_Name");

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
        case 1: // CALLOUT__ID
          return CALLOUT__ID;
        case 2: // CALLOUT__NAME
          return CALLOUT__NAME;
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
    tmpMap.put(_Fields.CALLOUT__ID, new org.apache.thrift.meta_data.FieldMetaData("Callout_ID", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.CALLOUT__NAME, new org.apache.thrift.meta_data.FieldMetaData("Callout_Name", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(myCalloutInformation.class, metaDataMap);
  }

  public myCalloutInformation() {
  }

  public myCalloutInformation(
    String Callout_ID,
    String Callout_Name)
  {
    this();
    this.Callout_ID = Callout_ID;
    this.Callout_Name = Callout_Name;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public myCalloutInformation(myCalloutInformation other) {
    if (other.isSetCallout_ID()) {
      this.Callout_ID = other.Callout_ID;
    }
    if (other.isSetCallout_Name()) {
      this.Callout_Name = other.Callout_Name;
    }
  }

  public myCalloutInformation deepCopy() {
    return new myCalloutInformation(this);
  }

  @Override
  public void clear() {
    this.Callout_ID = null;
    this.Callout_Name = null;
  }

  public String getCallout_ID() {
    return this.Callout_ID;
  }

  public myCalloutInformation setCallout_ID(String Callout_ID) {
    this.Callout_ID = Callout_ID;
    return this;
  }

  public void unsetCallout_ID() {
    this.Callout_ID = null;
  }

  /** Returns true if field Callout_ID is set (has been assigned a value) and false otherwise */
  public boolean isSetCallout_ID() {
    return this.Callout_ID != null;
  }

  public void setCallout_IDIsSet(boolean value) {
    if (!value) {
      this.Callout_ID = null;
    }
  }

  public String getCallout_Name() {
    return this.Callout_Name;
  }

  public myCalloutInformation setCallout_Name(String Callout_Name) {
    this.Callout_Name = Callout_Name;
    return this;
  }

  public void unsetCallout_Name() {
    this.Callout_Name = null;
  }

  /** Returns true if field Callout_Name is set (has been assigned a value) and false otherwise */
  public boolean isSetCallout_Name() {
    return this.Callout_Name != null;
  }

  public void setCallout_NameIsSet(boolean value) {
    if (!value) {
      this.Callout_Name = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case CALLOUT__ID:
      if (value == null) {
        unsetCallout_ID();
      } else {
        setCallout_ID((String)value);
      }
      break;

    case CALLOUT__NAME:
      if (value == null) {
        unsetCallout_Name();
      } else {
        setCallout_Name((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case CALLOUT__ID:
      return getCallout_ID();

    case CALLOUT__NAME:
      return getCallout_Name();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case CALLOUT__ID:
      return isSetCallout_ID();
    case CALLOUT__NAME:
      return isSetCallout_Name();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof myCalloutInformation)
      return this.equals((myCalloutInformation)that);
    return false;
  }

  public boolean equals(myCalloutInformation that) {
    if (that == null)
      return false;

    boolean this_present_Callout_ID = true && this.isSetCallout_ID();
    boolean that_present_Callout_ID = true && that.isSetCallout_ID();
    if (this_present_Callout_ID || that_present_Callout_ID) {
      if (!(this_present_Callout_ID && that_present_Callout_ID))
        return false;
      if (!this.Callout_ID.equals(that.Callout_ID))
        return false;
    }

    boolean this_present_Callout_Name = true && this.isSetCallout_Name();
    boolean that_present_Callout_Name = true && that.isSetCallout_Name();
    if (this_present_Callout_Name || that_present_Callout_Name) {
      if (!(this_present_Callout_Name && that_present_Callout_Name))
        return false;
      if (!this.Callout_Name.equals(that.Callout_Name))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_Callout_ID = true && (isSetCallout_ID());
    list.add(present_Callout_ID);
    if (present_Callout_ID)
      list.add(Callout_ID);

    boolean present_Callout_Name = true && (isSetCallout_Name());
    list.add(present_Callout_Name);
    if (present_Callout_Name)
      list.add(Callout_Name);

    return list.hashCode();
  }

  @Override
  public int compareTo(myCalloutInformation other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetCallout_ID()).compareTo(other.isSetCallout_ID());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCallout_ID()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.Callout_ID, other.Callout_ID);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetCallout_Name()).compareTo(other.isSetCallout_Name());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCallout_Name()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.Callout_Name, other.Callout_Name);
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
    StringBuilder sb = new StringBuilder("myCalloutInformation(");
    boolean first = true;

    sb.append("Callout_ID:");
    if (this.Callout_ID == null) {
      sb.append("null");
    } else {
      sb.append(this.Callout_ID);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("Callout_Name:");
    if (this.Callout_Name == null) {
      sb.append("null");
    } else {
      sb.append(this.Callout_Name);
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

  private static class myCalloutInformationStandardSchemeFactory implements SchemeFactory {
    public myCalloutInformationStandardScheme getScheme() {
      return new myCalloutInformationStandardScheme();
    }
  }

  private static class myCalloutInformationStandardScheme extends StandardScheme<myCalloutInformation> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, myCalloutInformation struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // CALLOUT__ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.Callout_ID = iprot.readString();
              struct.setCallout_IDIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // CALLOUT__NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.Callout_Name = iprot.readString();
              struct.setCallout_NameIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, myCalloutInformation struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.Callout_ID != null) {
        oprot.writeFieldBegin(CALLOUT__ID_FIELD_DESC);
        oprot.writeString(struct.Callout_ID);
        oprot.writeFieldEnd();
      }
      if (struct.Callout_Name != null) {
        oprot.writeFieldBegin(CALLOUT__NAME_FIELD_DESC);
        oprot.writeString(struct.Callout_Name);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class myCalloutInformationTupleSchemeFactory implements SchemeFactory {
    public myCalloutInformationTupleScheme getScheme() {
      return new myCalloutInformationTupleScheme();
    }
  }

  private static class myCalloutInformationTupleScheme extends TupleScheme<myCalloutInformation> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, myCalloutInformation struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetCallout_ID()) {
        optionals.set(0);
      }
      if (struct.isSetCallout_Name()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetCallout_ID()) {
        oprot.writeString(struct.Callout_ID);
      }
      if (struct.isSetCallout_Name()) {
        oprot.writeString(struct.Callout_Name);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, myCalloutInformation struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.Callout_ID = iprot.readString();
        struct.setCallout_IDIsSet(true);
      }
      if (incoming.get(1)) {
        struct.Callout_Name = iprot.readString();
        struct.setCallout_NameIsSet(true);
      }
    }
  }

}
