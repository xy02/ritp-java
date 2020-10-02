// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ritp.proto

package ritp;

/**
 * <pre>
 *一条流有以下生命周期：
 *1. 发送端发送一个header来创建一条流，并创建一个Producer来发送之后的流数据buf。
 *2. 接收端收到并处理header后创建一个Consumer来处理对端Producer发来的数据流。
 *3. Consumer发送close信号表示结束流并不再接收任何流消息，Producer必须在收到close后不再发送任何流消息。
 *4. Consumer发送pull信号表示拉取流数据buf的个数，pull可累加并且一旦发送不可撤回。
 *4. Producer必须在收到Consumer发来的pull后才能发送buf，发送buf的总个数必须小于等于pull的累加总和。
 *5. Producer发送end表示不再发送任何流消息并结束流，Consumer必须在收到end后不再发送任何控制信号。
 * </pre>
 *
 * Protobuf type {@code ritp.Msg}
 */
public  final class Msg extends
    com.google.protobuf.GeneratedMessageLite<
        Msg, Msg.Builder> implements
    // @@protoc_insertion_point(message_implements:ritp.Msg)
    MsgOrBuilder {
  private Msg() {
  }
  private int typeCase_ = 0;
  private Object type_;
  public enum TypeCase {
    HEADER(2),
    CLOSE(3),
    PULL(4),
    BUF(5),
    END(6),
    TYPE_NOT_SET(0);
    private final int value;
    private TypeCase(int value) {
      this.value = value;
    }
    /**
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @Deprecated
    public static TypeCase valueOf(int value) {
      return forNumber(value);
    }

    public static TypeCase forNumber(int value) {
      switch (value) {
        case 2: return HEADER;
        case 3: return CLOSE;
        case 4: return PULL;
        case 5: return BUF;
        case 6: return END;
        case 0: return TYPE_NOT_SET;
        default: return null;
      }
    }
    public int getNumber() {
      return this.value;
    }
  };

  @Override
  public TypeCase
  getTypeCase() {
    return TypeCase.forNumber(
        typeCase_);
  }

  private void clearType() {
    typeCase_ = 0;
    type_ = null;
  }

  public static final int STREAM_ID_FIELD_NUMBER = 1;
  private int streamId_;
  /**
   * <pre>
   *流ID
   * </pre>
   *
   * <code>uint32 stream_id = 1;</code>
   * @return The streamId.
   */
  @Override
  public int getStreamId() {
    return streamId_;
  }
  /**
   * <pre>
   *流ID
   * </pre>
   *
   * <code>uint32 stream_id = 1;</code>
   * @param value The streamId to set.
   */
  private void setStreamId(int value) {
    
    streamId_ = value;
  }
  /**
   * <pre>
   *流ID
   * </pre>
   *
   * <code>uint32 stream_id = 1;</code>
   */
  private void clearStreamId() {
    
    streamId_ = 0;
  }

  public static final int HEADER_FIELD_NUMBER = 2;
  /**
   * <pre>
   *流数据头，发送一条此数据开始一条流，本端Producer生产数据，对端Consumer消费数据
   * </pre>
   *
   * <code>.ritp.Header header = 2;</code>
   */
  @Override
  public boolean hasHeader() {
    return typeCase_ == 2;
  }
  /**
   * <pre>
   *流数据头，发送一条此数据开始一条流，本端Producer生产数据，对端Consumer消费数据
   * </pre>
   *
   * <code>.ritp.Header header = 2;</code>
   */
  @Override
  public Header getHeader() {
    if (typeCase_ == 2) {
       return (Header) type_;
    }
    return Header.getDefaultInstance();
  }
  /**
   * <pre>
   *流数据头，发送一条此数据开始一条流，本端Producer生产数据，对端Consumer消费数据
   * </pre>
   *
   * <code>.ritp.Header header = 2;</code>
   */
  private void setHeader(Header value) {
    value.getClass();
  type_ = value;
    typeCase_ = 2;
  }
  /**
   * <pre>
   *流数据头，发送一条此数据开始一条流，本端Producer生产数据，对端Consumer消费数据
   * </pre>
   *
   * <code>.ritp.Header header = 2;</code>
   */
  private void mergeHeader(Header value) {
    value.getClass();
  if (typeCase_ == 2 &&
        type_ != Header.getDefaultInstance()) {
      type_ = Header.newBuilder((Header) type_)
          .mergeFrom(value).buildPartial();
    } else {
      type_ = value;
    }
    typeCase_ = 2;
  }
  /**
   * <pre>
   *流数据头，发送一条此数据开始一条流，本端Producer生产数据，对端Consumer消费数据
   * </pre>
   *
   * <code>.ritp.Header header = 2;</code>
   */
  private void clearHeader() {
    if (typeCase_ == 2) {
      typeCase_ = 0;
      type_ = null;
    }
  }

  public static final int CLOSE_FIELD_NUMBER = 3;
  /**
   * <pre>
   *Consumer关闭流，不再接收流数据
   * </pre>
   *
   * <code>.ritp.Close close = 3;</code>
   */
  @Override
  public boolean hasClose() {
    return typeCase_ == 3;
  }
  /**
   * <pre>
   *Consumer关闭流，不再接收流数据
   * </pre>
   *
   * <code>.ritp.Close close = 3;</code>
   */
  @Override
  public Close getClose() {
    if (typeCase_ == 3) {
       return (Close) type_;
    }
    return Close.getDefaultInstance();
  }
  /**
   * <pre>
   *Consumer关闭流，不再接收流数据
   * </pre>
   *
   * <code>.ritp.Close close = 3;</code>
   */
  private void setClose(Close value) {
    value.getClass();
  type_ = value;
    typeCase_ = 3;
  }
  /**
   * <pre>
   *Consumer关闭流，不再接收流数据
   * </pre>
   *
   * <code>.ritp.Close close = 3;</code>
   */
  private void mergeClose(Close value) {
    value.getClass();
  if (typeCase_ == 3 &&
        type_ != Close.getDefaultInstance()) {
      type_ = Close.newBuilder((Close) type_)
          .mergeFrom(value).buildPartial();
    } else {
      type_ = value;
    }
    typeCase_ = 3;
  }
  /**
   * <pre>
   *Consumer关闭流，不再接收流数据
   * </pre>
   *
   * <code>.ritp.Close close = 3;</code>
   */
  private void clearClose() {
    if (typeCase_ == 3) {
      typeCase_ = 0;
      type_ = null;
    }
  }

  public static final int PULL_FIELD_NUMBER = 4;
  /**
   * <pre>
   *表示Consumer可以接收更多的buf的个数，等同于ReactiveStream里request(n)的意义
   *生产者Producer发送的buf总个数必须小于等于消费者Consumer发送的pull的累加总和
   * </pre>
   *
   * <code>uint32 pull = 4;</code>
   * @return The pull.
   */
  @Override
  public int getPull() {
    if (typeCase_ == 4) {
      return (Integer) type_;
    }
    return 0;
  }
  /**
   * <pre>
   *表示Consumer可以接收更多的buf的个数，等同于ReactiveStream里request(n)的意义
   *生产者Producer发送的buf总个数必须小于等于消费者Consumer发送的pull的累加总和
   * </pre>
   *
   * <code>uint32 pull = 4;</code>
   * @param value The pull to set.
   */
  private void setPull(int value) {
    typeCase_ = 4;
    type_ = value;
  }
  /**
   * <pre>
   *表示Consumer可以接收更多的buf的个数，等同于ReactiveStream里request(n)的意义
   *生产者Producer发送的buf总个数必须小于等于消费者Consumer发送的pull的累加总和
   * </pre>
   *
   * <code>uint32 pull = 4;</code>
   */
  private void clearPull() {
    if (typeCase_ == 4) {
      typeCase_ = 0;
      type_ = null;
    }
  }

  public static final int BUF_FIELD_NUMBER = 5;
  /**
   * <pre>
   *Producer发送流数据，只有收到对端Consumer发送的pull后，本端才能发送有限个数的buf
   * </pre>
   *
   * <code>bytes buf = 5;</code>
   * @return The buf.
   */
  @Override
  public com.google.protobuf.ByteString getBuf() {
    if (typeCase_ == 5) {
      return (com.google.protobuf.ByteString) type_;
    }
    return com.google.protobuf.ByteString.EMPTY;
  }
  /**
   * <pre>
   *Producer发送流数据，只有收到对端Consumer发送的pull后，本端才能发送有限个数的buf
   * </pre>
   *
   * <code>bytes buf = 5;</code>
   * @param value The buf to set.
   */
  private void setBuf(com.google.protobuf.ByteString value) {
    value.getClass();
  typeCase_ = 5;
    type_ = value;
  }
  /**
   * <pre>
   *Producer发送流数据，只有收到对端Consumer发送的pull后，本端才能发送有限个数的buf
   * </pre>
   *
   * <code>bytes buf = 5;</code>
   */
  private void clearBuf() {
    if (typeCase_ == 5) {
      typeCase_ = 0;
      type_ = null;
    }
  }

  public static final int END_FIELD_NUMBER = 6;
  /**
   * <pre>
   *Producer结束流
   * </pre>
   *
   * <code>.ritp.End end = 6;</code>
   */
  @Override
  public boolean hasEnd() {
    return typeCase_ == 6;
  }
  /**
   * <pre>
   *Producer结束流
   * </pre>
   *
   * <code>.ritp.End end = 6;</code>
   */
  @Override
  public End getEnd() {
    if (typeCase_ == 6) {
       return (End) type_;
    }
    return End.getDefaultInstance();
  }
  /**
   * <pre>
   *Producer结束流
   * </pre>
   *
   * <code>.ritp.End end = 6;</code>
   */
  private void setEnd(End value) {
    value.getClass();
  type_ = value;
    typeCase_ = 6;
  }
  /**
   * <pre>
   *Producer结束流
   * </pre>
   *
   * <code>.ritp.End end = 6;</code>
   */
  private void mergeEnd(End value) {
    value.getClass();
  if (typeCase_ == 6 &&
        type_ != End.getDefaultInstance()) {
      type_ = End.newBuilder((End) type_)
          .mergeFrom(value).buildPartial();
    } else {
      type_ = value;
    }
    typeCase_ = 6;
  }
  /**
   * <pre>
   *Producer结束流
   * </pre>
   *
   * <code>.ritp.End end = 6;</code>
   */
  private void clearEnd() {
    if (typeCase_ == 6) {
      typeCase_ = 0;
      type_ = null;
    }
  }

  public static Msg parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data);
  }
  public static Msg parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data, extensionRegistry);
  }
  public static Msg parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data);
  }
  public static Msg parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data, extensionRegistry);
  }
  public static Msg parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data);
  }
  public static Msg parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data, extensionRegistry);
  }
  public static Msg parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input);
  }
  public static Msg parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input, extensionRegistry);
  }
  public static Msg parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return parseDelimitedFrom(DEFAULT_INSTANCE, input);
  }
  public static Msg parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
  }
  public static Msg parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input);
  }
  public static Msg parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input, extensionRegistry);
  }

  public static Builder newBuilder() {
    return (Builder) DEFAULT_INSTANCE.createBuilder();
  }
  public static Builder newBuilder(Msg prototype) {
    return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
  }

  /**
   * <pre>
   *一条流有以下生命周期：
   *1. 发送端发送一个header来创建一条流，并创建一个Producer来发送之后的流数据buf。
   *2. 接收端收到并处理header后创建一个Consumer来处理对端Producer发来的数据流。
   *3. Consumer发送close信号表示结束流并不再接收任何流消息，Producer必须在收到close后不再发送任何流消息。
   *4. Consumer发送pull信号表示拉取流数据buf的个数，pull可累加并且一旦发送不可撤回。
   *4. Producer必须在收到Consumer发来的pull后才能发送buf，发送buf的总个数必须小于等于pull的累加总和。
   *5. Producer发送end表示不再发送任何流消息并结束流，Consumer必须在收到end后不再发送任何控制信号。
   * </pre>
   *
   * Protobuf type {@code ritp.Msg}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageLite.Builder<
        Msg, Builder> implements
      // @@protoc_insertion_point(builder_implements:ritp.Msg)
      MsgOrBuilder {
    // Construct using ritp.Msg.newBuilder()
    private Builder() {
      super(DEFAULT_INSTANCE);
    }

    @Override
    public TypeCase
        getTypeCase() {
      return instance.getTypeCase();
    }

    public Builder clearType() {
      copyOnWrite();
      instance.clearType();
      return this;
    }


    /**
     * <pre>
     *流ID
     * </pre>
     *
     * <code>uint32 stream_id = 1;</code>
     * @return The streamId.
     */
    @Override
    public int getStreamId() {
      return instance.getStreamId();
    }
    /**
     * <pre>
     *流ID
     * </pre>
     *
     * <code>uint32 stream_id = 1;</code>
     * @param value The streamId to set.
     * @return This builder for chaining.
     */
    public Builder setStreamId(int value) {
      copyOnWrite();
      instance.setStreamId(value);
      return this;
    }
    /**
     * <pre>
     *流ID
     * </pre>
     *
     * <code>uint32 stream_id = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearStreamId() {
      copyOnWrite();
      instance.clearStreamId();
      return this;
    }

    /**
     * <pre>
     *流数据头，发送一条此数据开始一条流，本端Producer生产数据，对端Consumer消费数据
     * </pre>
     *
     * <code>.ritp.Header header = 2;</code>
     */
    @Override
    public boolean hasHeader() {
      return instance.hasHeader();
    }
    /**
     * <pre>
     *流数据头，发送一条此数据开始一条流，本端Producer生产数据，对端Consumer消费数据
     * </pre>
     *
     * <code>.ritp.Header header = 2;</code>
     */
    @Override
    public Header getHeader() {
      return instance.getHeader();
    }
    /**
     * <pre>
     *流数据头，发送一条此数据开始一条流，本端Producer生产数据，对端Consumer消费数据
     * </pre>
     *
     * <code>.ritp.Header header = 2;</code>
     */
    public Builder setHeader(Header value) {
      copyOnWrite();
      instance.setHeader(value);
      return this;
    }
    /**
     * <pre>
     *流数据头，发送一条此数据开始一条流，本端Producer生产数据，对端Consumer消费数据
     * </pre>
     *
     * <code>.ritp.Header header = 2;</code>
     */
    public Builder setHeader(
        Header.Builder builderForValue) {
      copyOnWrite();
      instance.setHeader(builderForValue.build());
      return this;
    }
    /**
     * <pre>
     *流数据头，发送一条此数据开始一条流，本端Producer生产数据，对端Consumer消费数据
     * </pre>
     *
     * <code>.ritp.Header header = 2;</code>
     */
    public Builder mergeHeader(Header value) {
      copyOnWrite();
      instance.mergeHeader(value);
      return this;
    }
    /**
     * <pre>
     *流数据头，发送一条此数据开始一条流，本端Producer生产数据，对端Consumer消费数据
     * </pre>
     *
     * <code>.ritp.Header header = 2;</code>
     */
    public Builder clearHeader() {
      copyOnWrite();
      instance.clearHeader();
      return this;
    }

    /**
     * <pre>
     *Consumer关闭流，不再接收流数据
     * </pre>
     *
     * <code>.ritp.Close close = 3;</code>
     */
    @Override
    public boolean hasClose() {
      return instance.hasClose();
    }
    /**
     * <pre>
     *Consumer关闭流，不再接收流数据
     * </pre>
     *
     * <code>.ritp.Close close = 3;</code>
     */
    @Override
    public Close getClose() {
      return instance.getClose();
    }
    /**
     * <pre>
     *Consumer关闭流，不再接收流数据
     * </pre>
     *
     * <code>.ritp.Close close = 3;</code>
     */
    public Builder setClose(Close value) {
      copyOnWrite();
      instance.setClose(value);
      return this;
    }
    /**
     * <pre>
     *Consumer关闭流，不再接收流数据
     * </pre>
     *
     * <code>.ritp.Close close = 3;</code>
     */
    public Builder setClose(
        Close.Builder builderForValue) {
      copyOnWrite();
      instance.setClose(builderForValue.build());
      return this;
    }
    /**
     * <pre>
     *Consumer关闭流，不再接收流数据
     * </pre>
     *
     * <code>.ritp.Close close = 3;</code>
     */
    public Builder mergeClose(Close value) {
      copyOnWrite();
      instance.mergeClose(value);
      return this;
    }
    /**
     * <pre>
     *Consumer关闭流，不再接收流数据
     * </pre>
     *
     * <code>.ritp.Close close = 3;</code>
     */
    public Builder clearClose() {
      copyOnWrite();
      instance.clearClose();
      return this;
    }

    /**
     * <pre>
     *表示Consumer可以接收更多的buf的个数，等同于ReactiveStream里request(n)的意义
     *生产者Producer发送的buf总个数必须小于等于消费者Consumer发送的pull的累加总和
     * </pre>
     *
     * <code>uint32 pull = 4;</code>
     * @return The pull.
     */
    @Override
    public int getPull() {
      return instance.getPull();
    }
    /**
     * <pre>
     *表示Consumer可以接收更多的buf的个数，等同于ReactiveStream里request(n)的意义
     *生产者Producer发送的buf总个数必须小于等于消费者Consumer发送的pull的累加总和
     * </pre>
     *
     * <code>uint32 pull = 4;</code>
     * @param value The pull to set.
     * @return This builder for chaining.
     */
    public Builder setPull(int value) {
      copyOnWrite();
      instance.setPull(value);
      return this;
    }
    /**
     * <pre>
     *表示Consumer可以接收更多的buf的个数，等同于ReactiveStream里request(n)的意义
     *生产者Producer发送的buf总个数必须小于等于消费者Consumer发送的pull的累加总和
     * </pre>
     *
     * <code>uint32 pull = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearPull() {
      copyOnWrite();
      instance.clearPull();
      return this;
    }

    /**
     * <pre>
     *Producer发送流数据，只有收到对端Consumer发送的pull后，本端才能发送有限个数的buf
     * </pre>
     *
     * <code>bytes buf = 5;</code>
     * @return The buf.
     */
    @Override
    public com.google.protobuf.ByteString getBuf() {
      return instance.getBuf();
    }
    /**
     * <pre>
     *Producer发送流数据，只有收到对端Consumer发送的pull后，本端才能发送有限个数的buf
     * </pre>
     *
     * <code>bytes buf = 5;</code>
     * @param value The buf to set.
     * @return This builder for chaining.
     */
    public Builder setBuf(com.google.protobuf.ByteString value) {
      copyOnWrite();
      instance.setBuf(value);
      return this;
    }
    /**
     * <pre>
     *Producer发送流数据，只有收到对端Consumer发送的pull后，本端才能发送有限个数的buf
     * </pre>
     *
     * <code>bytes buf = 5;</code>
     * @return This builder for chaining.
     */
    public Builder clearBuf() {
      copyOnWrite();
      instance.clearBuf();
      return this;
    }

    /**
     * <pre>
     *Producer结束流
     * </pre>
     *
     * <code>.ritp.End end = 6;</code>
     */
    @Override
    public boolean hasEnd() {
      return instance.hasEnd();
    }
    /**
     * <pre>
     *Producer结束流
     * </pre>
     *
     * <code>.ritp.End end = 6;</code>
     */
    @Override
    public End getEnd() {
      return instance.getEnd();
    }
    /**
     * <pre>
     *Producer结束流
     * </pre>
     *
     * <code>.ritp.End end = 6;</code>
     */
    public Builder setEnd(End value) {
      copyOnWrite();
      instance.setEnd(value);
      return this;
    }
    /**
     * <pre>
     *Producer结束流
     * </pre>
     *
     * <code>.ritp.End end = 6;</code>
     */
    public Builder setEnd(
        End.Builder builderForValue) {
      copyOnWrite();
      instance.setEnd(builderForValue.build());
      return this;
    }
    /**
     * <pre>
     *Producer结束流
     * </pre>
     *
     * <code>.ritp.End end = 6;</code>
     */
    public Builder mergeEnd(End value) {
      copyOnWrite();
      instance.mergeEnd(value);
      return this;
    }
    /**
     * <pre>
     *Producer结束流
     * </pre>
     *
     * <code>.ritp.End end = 6;</code>
     */
    public Builder clearEnd() {
      copyOnWrite();
      instance.clearEnd();
      return this;
    }

    // @@protoc_insertion_point(builder_scope:ritp.Msg)
  }
  @Override
  @SuppressWarnings({"unchecked", "fallthrough"})
  protected final Object dynamicMethod(
      MethodToInvoke method,
      Object arg0, Object arg1) {
    switch (method) {
      case NEW_MUTABLE_INSTANCE: {
        return new Msg();
      }
      case NEW_BUILDER: {
        return new Builder();
      }
      case BUILD_MESSAGE_INFO: {
          Object[] objects = new Object[] {
            "type_",
            "typeCase_",
            "streamId_",
            Header.class,
            Close.class,
            End.class,
          };
          String info =
              "\u0000\u0006\u0001\u0000\u0001\u0006\u0006\u0000\u0000\u0000\u0001\u000b\u0002<\u0000" +
              "\u0003<\u0000\u0004>\u0000\u0005=\u0000\u0006<\u0000";
          return newMessageInfo(DEFAULT_INSTANCE, info, objects);
      }
      // fall through
      case GET_DEFAULT_INSTANCE: {
        return DEFAULT_INSTANCE;
      }
      case GET_PARSER: {
        com.google.protobuf.Parser<Msg> parser = PARSER;
        if (parser == null) {
          synchronized (Msg.class) {
            parser = PARSER;
            if (parser == null) {
              parser =
                  new DefaultInstanceBasedParser<Msg>(
                      DEFAULT_INSTANCE);
              PARSER = parser;
            }
          }
        }
        return parser;
    }
    case GET_MEMOIZED_IS_INITIALIZED: {
      return (byte) 1;
    }
    case SET_MEMOIZED_IS_INITIALIZED: {
      return null;
    }
    }
    throw new UnsupportedOperationException();
  }


  // @@protoc_insertion_point(class_scope:ritp.Msg)
  private static final Msg DEFAULT_INSTANCE;
  static {
    Msg defaultInstance = new Msg();
    // New instances are implicitly immutable so no need to make
    // immutable.
    DEFAULT_INSTANCE = defaultInstance;
    com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(
      Msg.class, defaultInstance);
  }

  public static Msg getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static volatile com.google.protobuf.Parser<Msg> PARSER;

  public static com.google.protobuf.Parser<Msg> parser() {
    return DEFAULT_INSTANCE.getParserForType();
  }
}
