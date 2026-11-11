package com.fasterxml.jackson.module.kotlin;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.Regex;
import kotlin.text.RegexOption;

/* JADX INFO: compiled from: KotlinDeserializers.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/RegexDeserializer;", "Lcom/fasterxml/jackson/databind/deser/std/StdDeserializer;", "Lkotlin/text/Regex;", "()V", "deserialize", TtmlNode.TAG_P, "Lcom/fasterxml/jackson/core/JsonParser;", "ctxt", "Lcom/fasterxml/jackson/databind/DeserializationContext;", "jackson-module-kotlin"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class RegexDeserializer extends StdDeserializer<Regex> {
    public static final RegexDeserializer INSTANCE = new RegexDeserializer();

    private RegexDeserializer() {
        super((Class<?>) Regex.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Regex deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        Set setEmptySet;
        Intrinsics.checkNotNullParameter(p, "p");
        Intrinsics.checkNotNullParameter(ctxt, "ctxt");
        JsonNode tree = ctxt.readTree(p);
        if (tree.isTextual()) {
            String strAsText = tree.asText();
            Intrinsics.checkNotNullExpressionValue(strAsText, "node.asText()");
            return new Regex(strAsText);
        }
        if (tree.isObject()) {
            String pattern = tree.get("pattern").asText();
            if (tree.has("options")) {
                JsonNode jsonNode = tree.get("options");
                if (!jsonNode.isArray()) {
                    throw new IllegalStateException(Intrinsics.stringPlus("Expected an array of strings for RegexOptions, but type was ", tree.getNodeType()));
                }
                Iterator<JsonNode> itElements = jsonNode.elements();
                Intrinsics.checkNotNullExpressionValue(itElements, "optionsNode.elements()");
                setEmptySet = SequencesKt.toSet(SequencesKt.map(SequencesKt.asSequence(itElements), new Function1<JsonNode, RegexOption>() { // from class: com.fasterxml.jackson.module.kotlin.RegexDeserializer$deserialize$options$1
                    @Override // kotlin.jvm.functions.Function1
                    public final RegexOption invoke(JsonNode jsonNode2) {
                        String strAsText2 = jsonNode2.asText();
                        Intrinsics.checkNotNullExpressionValue(strAsText2, "it.asText()");
                        return RegexOption.valueOf(strAsText2);
                    }
                }));
            } else {
                setEmptySet = SetsKt.emptySet();
            }
            Intrinsics.checkNotNullExpressionValue(pattern, "pattern");
            return new Regex(pattern, (Set<? extends RegexOption>) setEmptySet);
        }
        throw new IllegalStateException(Intrinsics.stringPlus("Expected a string or an object to deserialize a Regex, but type was ", tree.getNodeType()));
    }
}
