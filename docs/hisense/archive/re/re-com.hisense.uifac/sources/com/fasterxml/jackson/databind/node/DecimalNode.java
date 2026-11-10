package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.math.BigDecimal;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: loaded from: classes.dex */
public class DecimalNode extends NumericNode {
    public static final DecimalNode ZERO = new DecimalNode(BigDecimal.ZERO);
    protected final BigDecimal _value;

    static {
        BigDecimal.valueOf(-2147483648L);
        BigDecimal.valueOf(2147483647L);
        BigDecimal.valueOf(Long.MIN_VALUE);
        BigDecimal.valueOf(LongCompanionObject.MAX_VALUE);
    }

    public DecimalNode(BigDecimal bigDecimal) {
        this._value = bigDecimal;
    }

    public static DecimalNode valueOf(BigDecimal bigDecimal) {
        return new DecimalNode(bigDecimal);
    }

    @Override // com.fasterxml.jackson.databind.node.ValueNode
    public JsonToken asToken() {
        return JsonToken.VALUE_NUMBER_FLOAT;
    }

    @Override // com.fasterxml.jackson.databind.node.NumericNode
    public int intValue() {
        return this._value.intValue();
    }

    @Override // com.fasterxml.jackson.databind.node.NumericNode
    public long longValue() {
        return this._value.longValue();
    }

    @Override // com.fasterxml.jackson.databind.node.NumericNode
    public double doubleValue() {
        return this._value.doubleValue();
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public String asText() {
        return this._value.toString();
    }

    @Override // com.fasterxml.jackson.databind.node.BaseJsonNode, com.fasterxml.jackson.databind.JsonSerializable
    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(this._value);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return obj != null && (obj instanceof DecimalNode) && ((DecimalNode) obj)._value.compareTo(this._value) == 0;
    }

    public int hashCode() {
        return Double.valueOf(doubleValue()).hashCode();
    }
}
