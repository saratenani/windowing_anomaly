/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package io.confluent.demo;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class Anomaly extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -1574315134763864032L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Anomaly\",\"namespace\":\"io.confluent.demo\",\"fields\":[{\"name\":\"id\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"window_hash\",\"type\":\"int\"},{\"name\":\"window_start\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"window_end\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Anomaly> ENCODER =
      new BinaryMessageEncoder<Anomaly>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Anomaly> DECODER =
      new BinaryMessageDecoder<Anomaly>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<Anomaly> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<Anomaly> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Anomaly>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this Anomaly to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a Anomaly from a ByteBuffer. */
  public static Anomaly fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.String id;
  @Deprecated public int window_hash;
  @Deprecated public java.lang.String window_start;
  @Deprecated public java.lang.String window_end;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Anomaly() {}

  /**
   * All-args constructor.
   * @param id The new value for id
   * @param window_hash The new value for window_hash
   * @param window_start The new value for window_start
   * @param window_end The new value for window_end
   */
  public Anomaly(java.lang.String id, java.lang.Integer window_hash, java.lang.String window_start, java.lang.String window_end) {
    this.id = id;
    this.window_hash = window_hash;
    this.window_start = window_start;
    this.window_end = window_end;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return id;
    case 1: return window_hash;
    case 2: return window_start;
    case 3: return window_end;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: id = (java.lang.String)value$; break;
    case 1: window_hash = (java.lang.Integer)value$; break;
    case 2: window_start = (java.lang.String)value$; break;
    case 3: window_end = (java.lang.String)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'id' field.
   * @return The value of the 'id' field.
   */
  public java.lang.String getId() {
    return id;
  }

  /**
   * Sets the value of the 'id' field.
   * @param value the value to set.
   */
  public void setId(java.lang.String value) {
    this.id = value;
  }

  /**
   * Gets the value of the 'window_hash' field.
   * @return The value of the 'window_hash' field.
   */
  public java.lang.Integer getWindowHash() {
    return window_hash;
  }

  /**
   * Sets the value of the 'window_hash' field.
   * @param value the value to set.
   */
  public void setWindowHash(java.lang.Integer value) {
    this.window_hash = value;
  }

  /**
   * Gets the value of the 'window_start' field.
   * @return The value of the 'window_start' field.
   */
  public java.lang.String getWindowStart() {
    return window_start;
  }

  /**
   * Sets the value of the 'window_start' field.
   * @param value the value to set.
   */
  public void setWindowStart(java.lang.String value) {
    this.window_start = value;
  }

  /**
   * Gets the value of the 'window_end' field.
   * @return The value of the 'window_end' field.
   */
  public java.lang.String getWindowEnd() {
    return window_end;
  }

  /**
   * Sets the value of the 'window_end' field.
   * @param value the value to set.
   */
  public void setWindowEnd(java.lang.String value) {
    this.window_end = value;
  }

  /**
   * Creates a new Anomaly RecordBuilder.
   * @return A new Anomaly RecordBuilder
   */
  public static io.confluent.demo.Anomaly.Builder newBuilder() {
    return new io.confluent.demo.Anomaly.Builder();
  }

  /**
   * Creates a new Anomaly RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Anomaly RecordBuilder
   */
  public static io.confluent.demo.Anomaly.Builder newBuilder(io.confluent.demo.Anomaly.Builder other) {
    return new io.confluent.demo.Anomaly.Builder(other);
  }

  /**
   * Creates a new Anomaly RecordBuilder by copying an existing Anomaly instance.
   * @param other The existing instance to copy.
   * @return A new Anomaly RecordBuilder
   */
  public static io.confluent.demo.Anomaly.Builder newBuilder(io.confluent.demo.Anomaly other) {
    return new io.confluent.demo.Anomaly.Builder(other);
  }

  /**
   * RecordBuilder for Anomaly instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Anomaly>
    implements org.apache.avro.data.RecordBuilder<Anomaly> {

    private java.lang.String id;
    private int window_hash;
    private java.lang.String window_start;
    private java.lang.String window_end;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(io.confluent.demo.Anomaly.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.window_hash)) {
        this.window_hash = data().deepCopy(fields()[1].schema(), other.window_hash);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.window_start)) {
        this.window_start = data().deepCopy(fields()[2].schema(), other.window_start);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.window_end)) {
        this.window_end = data().deepCopy(fields()[3].schema(), other.window_end);
        fieldSetFlags()[3] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing Anomaly instance
     * @param other The existing instance to copy.
     */
    private Builder(io.confluent.demo.Anomaly other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.window_hash)) {
        this.window_hash = data().deepCopy(fields()[1].schema(), other.window_hash);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.window_start)) {
        this.window_start = data().deepCopy(fields()[2].schema(), other.window_start);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.window_end)) {
        this.window_end = data().deepCopy(fields()[3].schema(), other.window_end);
        fieldSetFlags()[3] = true;
      }
    }

    /**
      * Gets the value of the 'id' field.
      * @return The value.
      */
    public java.lang.String getId() {
      return id;
    }

    /**
      * Sets the value of the 'id' field.
      * @param value The value of 'id'.
      * @return This builder.
      */
    public io.confluent.demo.Anomaly.Builder setId(java.lang.String value) {
      validate(fields()[0], value);
      this.id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'id' field has been set.
      * @return True if the 'id' field has been set, false otherwise.
      */
    public boolean hasId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'id' field.
      * @return This builder.
      */
    public io.confluent.demo.Anomaly.Builder clearId() {
      id = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'window_hash' field.
      * @return The value.
      */
    public java.lang.Integer getWindowHash() {
      return window_hash;
    }

    /**
      * Sets the value of the 'window_hash' field.
      * @param value The value of 'window_hash'.
      * @return This builder.
      */
    public io.confluent.demo.Anomaly.Builder setWindowHash(int value) {
      validate(fields()[1], value);
      this.window_hash = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'window_hash' field has been set.
      * @return True if the 'window_hash' field has been set, false otherwise.
      */
    public boolean hasWindowHash() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'window_hash' field.
      * @return This builder.
      */
    public io.confluent.demo.Anomaly.Builder clearWindowHash() {
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'window_start' field.
      * @return The value.
      */
    public java.lang.String getWindowStart() {
      return window_start;
    }

    /**
      * Sets the value of the 'window_start' field.
      * @param value The value of 'window_start'.
      * @return This builder.
      */
    public io.confluent.demo.Anomaly.Builder setWindowStart(java.lang.String value) {
      validate(fields()[2], value);
      this.window_start = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'window_start' field has been set.
      * @return True if the 'window_start' field has been set, false otherwise.
      */
    public boolean hasWindowStart() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'window_start' field.
      * @return This builder.
      */
    public io.confluent.demo.Anomaly.Builder clearWindowStart() {
      window_start = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'window_end' field.
      * @return The value.
      */
    public java.lang.String getWindowEnd() {
      return window_end;
    }

    /**
      * Sets the value of the 'window_end' field.
      * @param value The value of 'window_end'.
      * @return This builder.
      */
    public io.confluent.demo.Anomaly.Builder setWindowEnd(java.lang.String value) {
      validate(fields()[3], value);
      this.window_end = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'window_end' field has been set.
      * @return True if the 'window_end' field has been set, false otherwise.
      */
    public boolean hasWindowEnd() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'window_end' field.
      * @return This builder.
      */
    public io.confluent.demo.Anomaly.Builder clearWindowEnd() {
      window_end = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Anomaly build() {
      try {
        Anomaly record = new Anomaly();
        record.id = fieldSetFlags()[0] ? this.id : (java.lang.String) defaultValue(fields()[0]);
        record.window_hash = fieldSetFlags()[1] ? this.window_hash : (java.lang.Integer) defaultValue(fields()[1]);
        record.window_start = fieldSetFlags()[2] ? this.window_start : (java.lang.String) defaultValue(fields()[2]);
        record.window_end = fieldSetFlags()[3] ? this.window_end : (java.lang.String) defaultValue(fields()[3]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Anomaly>
    WRITER$ = (org.apache.avro.io.DatumWriter<Anomaly>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Anomaly>
    READER$ = (org.apache.avro.io.DatumReader<Anomaly>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}