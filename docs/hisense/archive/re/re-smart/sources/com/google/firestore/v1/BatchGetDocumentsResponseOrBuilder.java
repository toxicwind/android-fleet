package com.google.firestore.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Timestamp;

/* JADX INFO: loaded from: classes.dex */
public interface BatchGetDocumentsResponseOrBuilder extends MessageLiteOrBuilder {
    Document getFound();

    String getMissing();

    ByteString getMissingBytes();

    Timestamp getReadTime();

    BatchGetDocumentsResponse.ResultCase getResultCase();

    ByteString getTransaction();

    boolean hasFound();

    boolean hasReadTime();
}
