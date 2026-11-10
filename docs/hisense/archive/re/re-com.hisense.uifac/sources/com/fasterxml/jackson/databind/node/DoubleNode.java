package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.io.NumberOutput;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class DoubleNode extends NumericNode {
    protected final double _value;

    public DoubleNode(double d) {
        this._value = d;
    }

    public static DoubleNode valueOf(double d) {
        return new DoubleNode(d);
    }

    @Override // com.fasterxml.jackson.databind.node.ValueNode
    public JsonToken asToken() {
        return JsonToken.VALUE_NUMBER_FLOAT;
    }

    @Override // com.fasterxml.jackson.databind.node.NumericNode
    public int intValue() {
        return (int) this._value;
    }

    @Override // com.fasterxml.jackson.databind.node.NumericNode
    public long longValue() {
        return (long) this._value;
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
        if (obj != null && (obj instanceof DoubleNode)) {
            return Double.compare(this._value, ((DoubleNode) obj)._value) == 0;
        }
        return false;
    }

    public int hashCode() {
        long jDoubleToLongBits = Double.doubleToLongBits(this._value);
        return ((int) jDoubleToLongBits) ^ ((int) (jDoubleToLongBits >> 32));
    }
}
