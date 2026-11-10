package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class BooleanNode extends ValueNode {
    private static final long serialVersionUID = 2;
    private final boolean _value;
    public static final BooleanNode TRUE = new BooleanNode(true);
    public static final BooleanNode FALSE = new BooleanNode(false);

    protected BooleanNode(boolean z) {
        this._value = z;
    }

    protected Object readResolve() {
        return this._value ? TRUE : FALSE;
    }

    public static BooleanNode getTrue() {
        return TRUE;
    }

    public static BooleanNode getFalse() {
        return FALSE;
    }

    @Override // com.fasterxml.jackson.databind.node.ValueNode
    public JsonToken asToken() {
        return this._value ? JsonToken.VALUE_TRUE : JsonToken.VALUE_FALSE;
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public String asText() {
        return this._value ? "true" : "false";
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public int asInt(int i) {
        return this._value ? 1 : 0;
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public long asLong(long j) {
        return this._value ? 1L : 0L;
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public double asDouble(double d) {
        return this._value ? 1.0d : 0.0d;
    }

    @Override // com.fasterxml.jackson.databind.node.BaseJsonNode, com.fasterxml.jackson.databind.JsonSerializable
    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeBoolean(this._value);
    }

    public int hashCode() {
        return this._value ? 3 : 1;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return obj != null && (obj instanceof BooleanNode) && this._value == ((BooleanNode) obj)._value;
    }
}
