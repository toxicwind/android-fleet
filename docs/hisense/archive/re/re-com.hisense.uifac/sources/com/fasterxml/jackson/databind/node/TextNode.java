package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class TextNode extends ValueNode {
    static final TextNode EMPTY_STRING_NODE = new TextNode("");
    private static final long serialVersionUID = 2;
    protected final String _value;

    public TextNode(String str) {
        this._value = str;
    }

    public static TextNode valueOf(String str) {
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return EMPTY_STRING_NODE;
        }
        return new TextNode(str);
    }

    @Override // com.fasterxml.jackson.databind.node.ValueNode
    public JsonToken asToken() {
        return JsonToken.VALUE_STRING;
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public String asText() {
        return this._value;
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public String asText(String str) {
        String str2 = this._value;
        return str2 == null ? str : str2;
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public int asInt(int i) {
        return NumberInput.parseAsInt(this._value, i);
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public long asLong(long j) {
        return NumberInput.parseAsLong(this._value, j);
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public double asDouble(double d) {
        return NumberInput.parseAsDouble(this._value, d);
    }

    @Override // com.fasterxml.jackson.databind.node.BaseJsonNode, com.fasterxml.jackson.databind.JsonSerializable
    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String str = this._value;
        if (str == null) {
            jsonGenerator.writeNull();
        } else {
            jsonGenerator.writeString(str);
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && (obj instanceof TextNode)) {
            return ((TextNode) obj)._value.equals(this._value);
        }
        return false;
    }

    public int hashCode() {
        return this._value.hashCode();
    }
}
