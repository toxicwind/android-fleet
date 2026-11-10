package com.google.firestore.v1;

import com.google.protobuf.MessageLiteOrBuilder;

/* JADX INFO: loaded from: classes.dex */
public interface TransactionOptionsOrBuilder extends MessageLiteOrBuilder {
    TransactionOptions.ModeCase getModeCase();

    TransactionOptions.ReadOnly getReadOnly();

    TransactionOptions.ReadWrite getReadWrite();

    boolean hasReadOnly();

    boolean hasReadWrite();
}
