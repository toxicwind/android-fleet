package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseJsonNode extends JsonNode implements Serializable {
    private static final long serialVersionUID = 1;

    @Override // com.fasterxml.jackson.databind.JsonSerializable
    public abstract void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException;

    Object writeReplace() {
        return NodeSerialization.from(this);
    }

    protected BaseJsonNode() {
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public String toString() {
        return InternalNodeMapper.nodeToString(this);
    }
}
