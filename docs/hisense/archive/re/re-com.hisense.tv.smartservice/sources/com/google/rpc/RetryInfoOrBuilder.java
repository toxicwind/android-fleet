package com.google.rpc;

import com.google.protobuf.Duration;
import com.google.protobuf.MessageLiteOrBuilder;

/* JADX INFO: loaded from: classes2.dex */
public interface RetryInfoOrBuilder extends MessageLiteOrBuilder {
    Duration getRetryDelay();

    boolean hasRetryDelay();
}
