package com.fasterxml.jackson.databind.node;

/* JADX INFO: loaded from: classes.dex */
public abstract class NumericNode extends ValueNode {
    private static final long serialVersionUID = 1;

    public abstract double doubleValue();

    public abstract int intValue();

    public abstract long longValue();

    protected NumericNode() {
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public final int asInt() {
        return intValue();
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public final int asInt(int i) {
        return intValue();
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public final long asLong() {
        return longValue();
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public final long asLong(long j) {
        return longValue();
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public final double asDouble() {
        return doubleValue();
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public final double asDouble(double d) {
        return doubleValue();
    }
}
