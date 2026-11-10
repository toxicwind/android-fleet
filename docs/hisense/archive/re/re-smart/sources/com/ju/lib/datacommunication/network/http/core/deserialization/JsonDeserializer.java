package com.ju.lib.datacommunication.network.http.core.deserialization;

import b.a.a.a.a;
import b.c.a.b;

/* JADX INFO: loaded from: classes2.dex */
public class JsonDeserializer implements IDeserializer {
    @Override // com.ju.lib.datacommunication.network.http.core.deserialization.IDeserializer
    public <T> T transform(String str, Class<T> cls) throws DeserializeException {
        try {
            return (T) b.b(cls).parse(str);
        } catch (Exception e2) {
            throw new DeserializeException(a.a("Parse response error! ", str), e2);
        }
    }
}
