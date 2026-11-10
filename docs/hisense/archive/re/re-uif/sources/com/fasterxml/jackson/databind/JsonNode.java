package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.databind.util.ClassUtil;
import java.util.Iterator;
import java.util.Map;

/* JADX WARN: Unexpected interfaces in signature: [java.lang.Iterable<com.fasterxml.jackson.databind.JsonNode>] */
/* JADX INFO: loaded from: classes.dex */
public abstract class JsonNode extends JsonSerializable.Base implements Object {
    public double asDouble(double d) {
        return d;
    }

    public int asInt(int i) {
        return i;
    }

    public long asLong(long j) {
        return j;
    }

    public abstract String asText();

    public abstract <T extends JsonNode> T deepCopy();

    public JsonNode get(String str) {
        return null;
    }

    public boolean isArray() {
        return false;
    }

    public abstract String toString();

    protected JsonNode() {
    }

    public String asText(String str) {
        String strAsText = asText();
        return strAsText == null ? str : strAsText;
    }

    public int asInt() {
        return asInt(0);
    }

    public long asLong() {
        return asLong(0L);
    }

    public double asDouble() {
        return asDouble(0.0d);
    }

    public final Iterator<JsonNode> iterator() {
        return elements();
    }

    public Iterator<JsonNode> elements() {
        return ClassUtil.emptyIterator();
    }

    public Iterator<Map.Entry<String, JsonNode>> fields() {
        return ClassUtil.emptyIterator();
    }
}
