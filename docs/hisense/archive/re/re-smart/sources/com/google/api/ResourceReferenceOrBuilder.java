package com.google.api;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

/* JADX INFO: loaded from: classes.dex */
public interface ResourceReferenceOrBuilder extends MessageLiteOrBuilder {
    String getChildType();

    ByteString getChildTypeBytes();

    String getType();

    ByteString getTypeBytes();
}
