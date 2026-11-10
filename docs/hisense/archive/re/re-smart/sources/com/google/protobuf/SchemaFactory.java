package com.google.protobuf;

/* JADX INFO: loaded from: classes2.dex */
public interface SchemaFactory {
    <T> Schema<T> createSchema(Class<T> cls);
}
