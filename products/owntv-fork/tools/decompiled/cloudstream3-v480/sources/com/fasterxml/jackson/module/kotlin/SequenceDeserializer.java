package com.fasterxml.jackson.module.kotlin;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;

/* JADX INFO: compiled from: KotlinDeserializers.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/SequenceDeserializer;", "Lcom/fasterxml/jackson/databind/deser/std/StdDeserializer;", "Lkotlin/sequences/Sequence;", "()V", "deserialize", TtmlNode.TAG_P, "Lcom/fasterxml/jackson/core/JsonParser;", "ctxt", "Lcom/fasterxml/jackson/databind/DeserializationContext;", "jackson-module-kotlin"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class SequenceDeserializer extends StdDeserializer<Sequence<?>> {
    public static final SequenceDeserializer INSTANCE = new SequenceDeserializer();

    private SequenceDeserializer() {
        super((Class<?>) Sequence.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Sequence<?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        Intrinsics.checkNotNullParameter(p, "p");
        Intrinsics.checkNotNullParameter(ctxt, "ctxt");
        Object value = ctxt.readValue(p, (Class<Object>) List.class);
        Intrinsics.checkNotNullExpressionValue(value, "ctxt.readValue(p, List::class.java)");
        return CollectionsKt.asSequence((Iterable) value);
    }
}
