// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ritp.proto

package ritp;

public interface EndOrBuilder extends
    // @@protoc_insertion_point(interface_extends:ritp.End)
    com.google.protobuf.MessageLiteOrBuilder {

  /**
   * <code>.ritp.End.Reason reason = 1;</code>
   * @return The enum numeric value on the wire for reason.
   */
  int getReasonValue();
  /**
   * <code>.ritp.End.Reason reason = 1;</code>
   * @return The reason.
   */
  End.Reason getReason();

  /**
   * <code>string message = 2;</code>
   * @return The message.
   */
  String getMessage();
  /**
   * <code>string message = 2;</code>
   * @return The bytes for message.
   */
  com.google.protobuf.ByteString
      getMessageBytes();
}
