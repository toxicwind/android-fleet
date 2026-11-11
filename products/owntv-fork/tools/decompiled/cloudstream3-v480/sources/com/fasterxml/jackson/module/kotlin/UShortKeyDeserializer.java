package com.fasterxml.jackson.module.kotlin;

import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.exc.InputCoercionException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdKeyDeserializer;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.UShort;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: KotlinKeyDeserializers.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J*\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0002\b\t\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006\n"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/UShortKeyDeserializer;", "Lcom/fasterxml/jackson/databind/deser/std/StdKeyDeserializer;", "()V", "deserializeKey", "Lkotlin/UShort;", "key", "", "ctxt", "Lcom/fasterxml/jackson/databind/DeserializationContext;", "deserializeKey-QDdqv-c", "jackson-module-kotlin"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class UShortKeyDeserializer extends StdKeyDeserializer {
    public static final UShortKeyDeserializer INSTANCE = new UShortKeyDeserializer();

    private UShortKeyDeserializer() {
        super(5, UShort.class);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdKeyDeserializer, com.fasterxml.jackson.databind.KeyDeserializer
    /* JADX INFO: renamed from: deserializeKey-QDdqv-c, reason: not valid java name and merged with bridge method [inline-methods] */
    public UShort deserializeKey(String key, DeserializationContext ctxt) throws IOException {
        Intrinsics.checkNotNullParameter(ctxt, "ctxt");
        Object objDeserializeKey = super.deserializeKey(key, ctxt);
        if (objDeserializeKey == null) {
            return null;
        }
        UShort uShortAsUShort = UnsignedNumbersKt.asUShort(((Integer) objDeserializeKey).intValue());
        if (uShortAsUShort != null) {
            return UShort.m9222boximpl(uShortAsUShort.getData());
        }
        throw new InputCoercionException(null, "Numeric value (" + ((Object) key) + ") out of range of UShort (0 - 65535).", JsonToken.VALUE_NUMBER_INT, UShort.class);
    }
}
