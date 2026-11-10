package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.io.NumberOutput;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class LongNode extends NumericNode {
    protected final long _value;

    public LongNode(long j) {
        this._value = j;
    }

    public static LongNode valueOf(long j) {
        return new LongNode(j);
    }

    @Override // com.fasterxml.jackson.databind.node.ValueNode
    public JsonToken asToken() {
        return JsonToken.VALUE_NUMBER_INT;
    }

    @Override // com.fasterxml.jackson.databind.node.NumericNode
    public int intValue() {
        return (int) this._value;
    }

    @Override // com.fasterxml.jackson.databind.node.NumericNode
    public long longValue() {
        return this._value;
    }

    @Override // com.fasterxml.jackson.databind.node.NumericNode
    public double doubleValue() {
        return this._value;
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public String asText() {
        return NumberOutput.toString(this._value);
    }

    @Override // com.fasterxml.jackson.databind.node.BaseJsonNode, com.fasterxml.jackson.databind.JsonSerializable
    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(this._value);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return obj != null && (obj instanceof LongNode) && ((LongNode) obj)._value == this._value;
    }

    public int hashCode() {
        long j = this._value;
        return ((int) j) ^ ((int) (j >> 32));
    }
}
