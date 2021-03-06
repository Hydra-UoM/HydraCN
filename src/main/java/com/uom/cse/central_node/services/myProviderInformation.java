/**
 * Autogenerated by Thrift Compiler (0.9.2)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
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
@Generated(value = "Autogenerated by Thrift Compiler (0.9.2)", date = "2016-1-31")
public class myProviderInformation implements org.apache.thrift.TBase<myProviderInformation, myProviderInformation._Fields>, java.io.Serializable, Cloneable, Comparable<myProviderInformation> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("myProviderInformation");

  private static final org.apache.thrift.protocol.TField PROVIDER__ID_FIELD_DESC = new org.apache.thrift.protocol.TField("Provider_ID", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField PROVIDER__NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("Provider_Name", org.apache.thrift.protocol.TType.STRING, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new myProviderInformationStandardSchemeFactory());
    schemes.put(TupleScheme.class, new myProviderInformationTupleSchemeFactory());
  }

  public String Provider_ID; // required
  public String Provider_Name; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    PROVIDER__ID((short)1, "Provider_ID"),
    PROVIDER__NAME((short)2, "Provider_Name");

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
        case 1: // PROVIDER__ID
          return PROVIDER__ID;
        case 2: // PROVIDER__NAME
          return PROVIDER__NAME;
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
    tmpMap.put(_Fields.PROVIDER__ID, new org.apache.thrift.meta_data.FieldMetaData("Provider_ID", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.PROVIDER__NAME, new org.apache.thrift.meta_data.FieldMetaData("Provider_Name", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(myProviderInformation.class, metaDataMap);
  }

  public myProviderInformation() {
  }

  public myProviderInformation(
    String Provider_ID,
    String Provider_Name)
  {
    this();
    this.Provider_ID = Provider_ID;
    this.Provider_Name = Provider_Name;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public myProviderInformation(myProviderInformation other) {
    if (other.isSetProvider_ID()) {
      this.Provider_ID = other.Provider_ID;
    }
    if (other.isSetProvider_Name()) {
      this.Provider_Name = other.Provider_Name;
    }
  }

  public myProviderInformation deepCopy() {
    return new myProviderInformation(this);
  }

  @Override
  public void clear() {
    this.Provider_ID = null;
    this.Provider_Name = null;
  }

  public String getProvider_ID() {
    return this.Provider_ID;
  }

  public myProviderInformation setProvider_ID(String Provider_ID) {
    this.Provider_ID = Provider_ID;
    return this;
  }

  public void unsetProvider_ID() {
    this.Provider_ID = null;
  }

  /** Returns true if field Provider_ID is set (has been assigned a value) and false otherwise */
  public boolean isSetProvider_ID() {
    return this.Provider_ID != null;
  }

  public void setProvider_IDIsSet(boolean value) {
    if (!value) {
      this.Provider_ID = null;
    }
  }

  public String getProvider_Name() {
    return this.Provider_Name;
  }

  public myProviderInformation setProvider_Name(String Provider_Name) {
    this.Provider_Name = Provider_Name;
    return this;
  }

  public void unsetProvider_Name() {
    this.Provider_Name = null;
  }

  /** Returns true if field Provider_Name is set (has been assigned a value) and false otherwise */
  public boolean isSetProvider_Name() {
    return this.Provider_Name != null;
  }

  public void setProvider_NameIsSet(boolean value) {
    if (!value) {
      this.Provider_Name = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case PROVIDER__ID:
      if (value == null) {
        unsetProvider_ID();
      } else {
        setProvider_ID((String)value);
      }
      break;

    case PROVIDER__NAME:
      if (value == null) {
        unsetProvider_Name();
      } else {
        setProvider_Name((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case PROVIDER__ID:
      return getProvider_ID();

    case PROVIDER__NAME:
      return getProvider_Name();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case PROVIDER__ID:
      return isSetProvider_ID();
    case PROVIDER__NAME:
      return isSetProvider_Name();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof myProviderInformation)
      return this.equals((myProviderInformation)that);
    return false;
  }

  public boolean equals(myProviderInformation that) {
    if (that == null)
      return false;

    boolean this_present_Provider_ID = true && this.isSetProvider_ID();
    boolean that_present_Provider_ID = true && that.isSetProvider_ID();
    if (this_present_Provider_ID || that_present_Provider_ID) {
      if (!(this_present_Provider_ID && that_present_Provider_ID))
        return false;
      if (!this.Provider_ID.equals(that.Provider_ID))
        return false;
    }

    boolean this_present_Provider_Name = true && this.isSetProvider_Name();
    boolean that_present_Provider_Name = true && that.isSetProvider_Name();
    if (this_present_Provider_Name || that_present_Provider_Name) {
      if (!(this_present_Provider_Name && that_present_Provider_Name))
        return false;
      if (!this.Provider_Name.equals(that.Provider_Name))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_Provider_ID = true && (isSetProvider_ID());
    list.add(present_Provider_ID);
    if (present_Provider_ID)
      list.add(Provider_ID);

    boolean present_Provider_Name = true && (isSetProvider_Name());
    list.add(present_Provider_Name);
    if (present_Provider_Name)
      list.add(Provider_Name);

    return list.hashCode();
  }

  @Override
  public int compareTo(myProviderInformation other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetProvider_ID()).compareTo(other.isSetProvider_ID());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetProvider_ID()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.Provider_ID, other.Provider_ID);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetProvider_Name()).compareTo(other.isSetProvider_Name());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetProvider_Name()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.Provider_Name, other.Provider_Name);
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
    StringBuilder sb = new StringBuilder("myProviderInformation(");
    boolean first = true;

    sb.append("Provider_ID:");
    if (this.Provider_ID == null) {
      sb.append("null");
    } else {
      sb.append(this.Provider_ID);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("Provider_Name:");
    if (this.Provider_Name == null) {
      sb.append("null");
    } else {
      sb.append(this.Provider_Name);
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

  private static class myProviderInformationStandardSchemeFactory implements SchemeFactory {
    public myProviderInformationStandardScheme getScheme() {
      return new myProviderInformationStandardScheme();
    }
  }

  private static class myProviderInformationStandardScheme extends StandardScheme<myProviderInformation> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, myProviderInformation struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // PROVIDER__ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.Provider_ID = iprot.readString();
              struct.setProvider_IDIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // PROVIDER__NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.Provider_Name = iprot.readString();
              struct.setProvider_NameIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, myProviderInformation struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.Provider_ID != null) {
        oprot.writeFieldBegin(PROVIDER__ID_FIELD_DESC);
        oprot.writeString(struct.Provider_ID);
        oprot.writeFieldEnd();
      }
      if (struct.Provider_Name != null) {
        oprot.writeFieldBegin(PROVIDER__NAME_FIELD_DESC);
        oprot.writeString(struct.Provider_Name);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class myProviderInformationTupleSchemeFactory implements SchemeFactory {
    public myProviderInformationTupleScheme getScheme() {
      return new myProviderInformationTupleScheme();
    }
  }

  private static class myProviderInformationTupleScheme extends TupleScheme<myProviderInformation> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, myProviderInformation struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetProvider_ID()) {
        optionals.set(0);
      }
      if (struct.isSetProvider_Name()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetProvider_ID()) {
        oprot.writeString(struct.Provider_ID);
      }
      if (struct.isSetProvider_Name()) {
        oprot.writeString(struct.Provider_Name);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, myProviderInformation struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.Provider_ID = iprot.readString();
        struct.setProvider_IDIsSet(true);
      }
      if (incoming.get(1)) {
        struct.Provider_Name = iprot.readString();
        struct.setProvider_NameIsSet(true);
      }
    }
  }

}

