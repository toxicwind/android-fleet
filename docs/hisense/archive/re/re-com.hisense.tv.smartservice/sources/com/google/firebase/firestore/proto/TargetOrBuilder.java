package com.google.firebase.firestore.proto;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Timestamp;

/* JADX INFO: loaded from: classes.dex */
public interface TargetOrBuilder extends MessageLiteOrBuilder {
    com.google.firestore.v1.Target.DocumentsTarget getDocuments();

    Timestamp getLastLimboFreeSnapshotVersion();

    long getLastListenSequenceNumber();

    com.google.firestore.v1.Target.QueryTarget getQuery();

    ByteString getResumeToken();

    Timestamp getSnapshotVersion();

    int getTargetId();

    Target.TargetTypeCase getTargetTypeCase();

    boolean hasDocuments();

    boolean hasLastLimboFreeSnapshotVersion();

    boolean hasQuery();

    boolean hasSnapshotVersion();
}
