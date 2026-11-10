package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class NullNode extends ValueNode {
    public static final NullNode instance = new NullNode();
    private static final long serialVersionUID = 1;

    @Override // com.fasterxml.jackson.databind.JsonNode
    public String asText() {
        return "null";
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public String asText(String str) {
        return str;
    }

    protected NullNode() {
    }

    protected Object readResolve() {
        return instance;
    }

    public static NullNode getInstance() {
        return instance;
    }

    @Override // com.fasterxml.jackson.databind.node.ValueNode
    public JsonToken asToken() {
        return JsonToken.VALUE_NULL;
    }

    @Override // com.fasterxml.jackson.databind.node.BaseJsonNode, com.fasterxml.jackson.databind.JsonSerializable
    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        serializerProvider.defaultSerializeNull(jsonGenerator);
    }

    public boolean equals(Object obj) {
        return obj == this || (obj instanceof NullNode);
    }

    public int hashCode() {
        return JsonNodeType.NULL.ordinal();
    }
}
