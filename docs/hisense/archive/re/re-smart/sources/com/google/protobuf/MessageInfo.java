package com.google.protobuf;

/* JADX INFO: loaded from: classes2.dex */
public interface MessageInfo {
    MessageLite getDefaultInstance();

    ProtoSyntax getSyntax();

    boolean isMessageSetWireFormat();
}
