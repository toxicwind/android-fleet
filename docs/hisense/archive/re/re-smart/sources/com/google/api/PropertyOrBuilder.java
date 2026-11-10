package com.google.api;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

/* JADX INFO: loaded from: classes.dex */
public interface PropertyOrBuilder extends MessageLiteOrBuilder {
    String getDescription();

    ByteString getDescriptionBytes();

    String getName();

    ByteString getNameBytes();

    Property.PropertyType getType();

    int getTypeValue();
}
