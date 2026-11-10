package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;

/* JADX WARN: Unexpected interfaces in signature: [java.lang.Object] */
/* JADX INFO: loaded from: classes.dex */
public abstract class JsonSerializer<T> {

    public static abstract class None extends JsonSerializer<Object> {
    }

    public Class<T> handledType() {
        return null;
    }

    public boolean isEmpty(SerializerProvider serializerProvider, T t) {
        return t == null;
    }

    public boolean isUnwrappingSerializer() {
        return false;
    }

    public abstract void serialize(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException;

    public JsonSerializer<T> unwrappingSerializer(NameTransformer nameTransformer) {
        return this;
    }

    public boolean usesObjectId() {
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void serializeWithType(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException {
        Class clsHandledType = handledType();
        if (clsHandledType == null) {
            clsHandledType = t.getClass();
        }
        serializerProvider.reportBadDefinition((Class<?>) clsHandledType, String.format("Type id handling not implemented for type %s (by serializer of type %s)", clsHandledType.getName(), getClass().getName()));
    }
}
