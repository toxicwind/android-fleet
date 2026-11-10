package com.google.firestore.v1;

import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Timestamp;

/* JADX INFO: loaded from: classes.dex */
public interface PreconditionOrBuilder extends MessageLiteOrBuilder {
    Precondition.ConditionTypeCase getConditionTypeCase();

    boolean getExists();

    Timestamp getUpdateTime();

    boolean hasUpdateTime();
}
