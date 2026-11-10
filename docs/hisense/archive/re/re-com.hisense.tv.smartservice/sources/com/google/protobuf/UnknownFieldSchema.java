package com.google.protobuf;

/* JADX INFO: loaded from: classes2.dex */
public abstract class UnknownFieldSchema<T, B> {
    public abstract void addFixed32(B b2, int i, int i2);

    public abstract void addFixed64(B b2, int i, long j);

    public abstract void addGroup(B b2, int i, T t);

    public abstract void addLengthDelimited(B b2, int i, ByteString byteString);

    public abstract void addVarint(B b2, int i, long j);

    public abstract B getBuilderFromMessage(Object obj);

    public abstract T getFromMessage(Object obj);

    public abstract int getSerializedSize(T t);

    public abstract int getSerializedSizeAsMessageSet(T t);

    public abstract void makeImmutable(Object obj);

    public abstract T merge(T t, T t2);

    public final void mergeFrom(B b2, Reader reader) {
        while (reader.getFieldNumber() != Integer.MAX_VALUE && mergeOneFieldFrom(b2, reader)) {
        }
    }

    public final boolean mergeOneFieldFrom(B b2, Reader reader) throws InvalidProtocolBufferException {
        int tag = reader.getTag();
        int i = tag >>> 3;
        int i2 = tag & 7;
        if (i2 == 0) {
            addVarint(b2, i, reader.readInt64());
            return true;
        }
        if (i2 == 1) {
            addFixed64(b2, i, reader.readFixed64());
            return true;
        }
        if (i2 == 2) {
            addLengthDelimited(b2, i, reader.readBytes());
            return true;
        }
        if (i2 != 3) {
            if (i2 == 4) {
                return false;
            }
            if (i2 != 5) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
            addFixed32(b2, i, reader.readFixed32());
            return true;
        }
        B bNewBuilder = newBuilder();
        int i3 = 4 | (i << 3);
        mergeFrom(bNewBuilder, reader);
        if (i3 != reader.getTag()) {
            throw InvalidProtocolBufferException.invalidEndTag();
        }
        addGroup(b2, i, toImmutable(bNewBuilder));
        return true;
    }

    public abstract B newBuilder();

    public abstract void setBuilderToMessage(Object obj, B b2);

    public abstract void setToMessage(Object obj, T t);

    public abstract boolean shouldDiscardUnknownFields(Reader reader);

    public abstract T toImmutable(B b2);

    public abstract void writeAsMessageSetTo(T t, Writer writer);

    public abstract void writeTo(T t, Writer writer);
}
