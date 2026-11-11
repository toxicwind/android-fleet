package com.google.protobuf;

/* JADX INFO: loaded from: classes6.dex */
public class InvalidProtobufRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public InvalidProtobufRuntimeException(String description) {
        super(description);
    }

    public InvalidProtobufRuntimeException(Exception e) {
        super(e.getMessage(), e);
    }

    public InvalidProtobufRuntimeException(String description, Exception e) {
        super(description, e);
    }
}
