package com.google.firestore.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Timestamp;

/* JADX INFO: loaded from: classes.dex */
public interface TargetOrBuilder extends MessageLiteOrBuilder {
    Target.DocumentsTarget getDocuments();

    boolean getOnce();

    Target.QueryTarget getQuery();

    Timestamp getReadTime();

    ByteString getResumeToken();

    Target.ResumeTypeCase getResumeTypeCase();

    int getTargetId();

    Target.TargetTypeCase getTargetTypeCase();

    boolean hasDocuments();

    boolean hasQuery();

    boolean hasReadTime();
}
