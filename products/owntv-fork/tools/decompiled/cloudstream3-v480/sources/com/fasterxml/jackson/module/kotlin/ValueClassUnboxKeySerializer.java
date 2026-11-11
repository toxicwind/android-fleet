package com.fasterxml.jackson.module.kotlin;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: KotlinKeySerializers.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/ValueClassUnboxKeySerializer;", "Lcom/fasterxml/jackson/databind/ser/std/StdSerializer;", "", "()V", "serialize", "", ES6Iterator.VALUE_PROPERTY, "gen", "Lcom/fasterxml/jackson/core/JsonGenerator;", "provider", "Lcom/fasterxml/jackson/databind/SerializerProvider;", "jackson-module-kotlin"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class ValueClassUnboxKeySerializer extends StdSerializer<Object> {
    public static final ValueClassUnboxKeySerializer INSTANCE = new ValueClassUnboxKeySerializer();

    private ValueClassUnboxKeySerializer() {
        super(Object.class);
    }

    @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IllegalAccessException, NoSuchMethodException, IOException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(gen, "gen");
        Intrinsics.checkNotNullParameter(provider, "provider");
        Method method = value.getClass().getMethod("unbox-impl", null);
        Object objInvoke = method.invoke(value, null);
        if (objInvoke == null) {
            provider.findNullKeySerializer(provider.getTypeFactory().constructType(method.getGenericReturnType()), null).serialize(null, gen, provider);
        } else {
            provider.findKeySerializer(objInvoke.getClass(), (BeanProperty) null).serialize(objInvoke, gen, provider);
        }
    }
}
