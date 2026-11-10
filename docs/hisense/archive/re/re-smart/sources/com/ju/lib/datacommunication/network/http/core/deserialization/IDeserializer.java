package com.ju.lib.datacommunication.network.http.core.deserialization;

/* JADX INFO: loaded from: classes2.dex */
public interface IDeserializer {
    <T> T transform(String str, Class<T> cls);
}
