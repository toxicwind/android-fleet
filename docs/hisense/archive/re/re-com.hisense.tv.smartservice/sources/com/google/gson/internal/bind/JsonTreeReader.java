package com.google.gson.internal.bind;

import b.a.a.a.a;
import com.google.common.net.InetAddresses;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import java.io.Reader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class JsonTreeReader extends JsonReader {
    public int[] pathIndices;
    public String[] pathNames;
    public Object[] stack;
    public int stackSize;
    public static final Reader UNREADABLE_READER = new Reader() { // from class: com.google.gson.internal.bind.JsonTreeReader.1
        @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            throw new AssertionError();
        }

        @Override // java.io.Reader
        public int read(char[] cArr, int i, int i2) {
            throw new AssertionError();
        }
    };
    public static final Object SENTINEL_CLOSED = new Object();

    public JsonTreeReader(JsonElement jsonElement) {
        super(UNREADABLE_READER);
        this.stack = new Object[32];
        this.stackSize = 0;
        this.pathNames = new String[32];
        this.pathIndices = new int[32];
        push(jsonElement);
    }

    private void expect(JsonToken jsonToken) {
        if (peek() == jsonToken) {
            return;
        }
        throw new IllegalStateException("Expected " + jsonToken + " but was " + peek() + locationString());
    }

    private String getPath(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append('$');
        int i = 0;
        while (true) {
            int i2 = this.stackSize;
            if (i >= i2) {
                return sb.toString();
            }
            Object[] objArr = this.stack;
            if (objArr[i] instanceof JsonArray) {
                i++;
                if (i < i2 && (objArr[i] instanceof Iterator)) {
                    int i3 = this.pathIndices[i];
                    if (z && i3 > 0 && (i == i2 - 1 || i == i2 - 2)) {
                        i3--;
                    }
                    sb.append('[');
                    sb.append(i3);
                    sb.append(']');
                }
            } else if ((objArr[i] instanceof JsonObject) && (i = i + 1) < i2 && (objArr[i] instanceof Iterator)) {
                sb.append(InetAddresses.IPV4_DELIMITER);
                String[] strArr = this.pathNames;
                if (strArr[i] != null) {
                    sb.append(strArr[i]);
                }
            }
            i++;
        }
    }

    private String locationString() {
        StringBuilder sbA = a.a(" at path ");
        sbA.append(getPath());
        return sbA.toString();
    }

    private Object peekStack() {
        return this.stack[this.stackSize - 1];
    }

    private Object popStack() {
        Object[] objArr = this.stack;
        int i = this.stackSize - 1;
        this.stackSize = i;
        Object obj = objArr[i];
        objArr[this.stackSize] = null;
        return obj;
    }

    private void push(Object obj) {
        int i = this.stackSize;
        Object[] objArr = this.stack;
        if (i == objArr.length) {
            int i2 = i * 2;
            this.stack = Arrays.copyOf(objArr, i2);
            this.pathIndices = Arrays.copyOf(this.pathIndices, i2);
            this.pathNames = (String[]) Arrays.copyOf(this.pathNames, i2);
        }
        Object[] objArr2 = this.stack;
        int i3 = this.stackSize;
        this.stackSize = i3 + 1;
        objArr2[i3] = obj;
    }

    @Override // com.google.gson.stream.JsonReader
    public void beginArray() {
        expect(JsonToken.BEGIN_ARRAY);
        push(((JsonArray) peekStack()).iterator());
        this.pathIndices[this.stackSize - 1] = 0;
    }

    @Override // com.google.gson.stream.JsonReader
    public void beginObject() {
        expect(JsonToken.BEGIN_OBJECT);
        push(((JsonObject) peekStack()).entrySet().iterator());
    }

    @Override // com.google.gson.stream.JsonReader, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.stack = new Object[]{SENTINEL_CLOSED};
        this.stackSize = 1;
    }

    @Override // com.google.gson.stream.JsonReader
    public void endArray() {
        expect(JsonToken.END_ARRAY);
        popStack();
        popStack();
        int i = this.stackSize;
        if (i > 0) {
            int[] iArr = this.pathIndices;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    @Override // com.google.gson.stream.JsonReader
    public void endObject() {
        expect(JsonToken.END_OBJECT);
        popStack();
        popStack();
        int i = this.stackSize;
        if (i > 0) {
            int[] iArr = this.pathIndices;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    @Override // com.google.gson.stream.JsonReader
    public String getPreviousPath() {
        return getPath(true);
    }

    @Override // com.google.gson.stream.JsonReader
    public boolean hasNext() {
        JsonToken jsonTokenPeek = peek();
        return (jsonTokenPeek == JsonToken.END_OBJECT || jsonTokenPeek == JsonToken.END_ARRAY || jsonTokenPeek == JsonToken.END_DOCUMENT) ? false : true;
    }

    @Override // com.google.gson.stream.JsonReader
    public boolean nextBoolean() {
        expect(JsonToken.BOOLEAN);
        boolean asBoolean = ((JsonPrimitive) popStack()).getAsBoolean();
        int i = this.stackSize;
        if (i > 0) {
            int[] iArr = this.pathIndices;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return asBoolean;
    }

    @Override // com.google.gson.stream.JsonReader
    public double nextDouble() {
        JsonToken jsonTokenPeek = peek();
        if (jsonTokenPeek != JsonToken.NUMBER && jsonTokenPeek != JsonToken.STRING) {
            StringBuilder sbA = a.a("Expected ");
            sbA.append(JsonToken.NUMBER);
            sbA.append(" but was ");
            sbA.append(jsonTokenPeek);
            sbA.append(locationString());
            throw new IllegalStateException(sbA.toString());
        }
        double asDouble = ((JsonPrimitive) peekStack()).getAsDouble();
        if (!isLenient() && (Double.isNaN(asDouble) || Double.isInfinite(asDouble))) {
            throw new NumberFormatException("JSON forbids NaN and infinities: " + asDouble);
        }
        popStack();
        int i = this.stackSize;
        if (i > 0) {
            int[] iArr = this.pathIndices;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return asDouble;
    }

    @Override // com.google.gson.stream.JsonReader
    public int nextInt() {
        JsonToken jsonTokenPeek = peek();
        if (jsonTokenPeek != JsonToken.NUMBER && jsonTokenPeek != JsonToken.STRING) {
            StringBuilder sbA = a.a("Expected ");
            sbA.append(JsonToken.NUMBER);
            sbA.append(" but was ");
            sbA.append(jsonTokenPeek);
            sbA.append(locationString());
            throw new IllegalStateException(sbA.toString());
        }
        int asInt = ((JsonPrimitive) peekStack()).getAsInt();
        popStack();
        int i = this.stackSize;
        if (i > 0) {
            int[] iArr = this.pathIndices;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return asInt;
    }

    public JsonElement nextJsonElement() {
        JsonToken jsonTokenPeek = peek();
        if (jsonTokenPeek == JsonToken.NAME || jsonTokenPeek == JsonToken.END_ARRAY || jsonTokenPeek == JsonToken.END_OBJECT || jsonTokenPeek == JsonToken.END_DOCUMENT) {
            throw new IllegalStateException(a.a("Unexpected ", jsonTokenPeek, " when reading a JsonElement."));
        }
        JsonElement jsonElement = (JsonElement) peekStack();
        skipValue();
        return jsonElement;
    }

    @Override // com.google.gson.stream.JsonReader
    public long nextLong() {
        JsonToken jsonTokenPeek = peek();
        if (jsonTokenPeek != JsonToken.NUMBER && jsonTokenPeek != JsonToken.STRING) {
            StringBuilder sbA = a.a("Expected ");
            sbA.append(JsonToken.NUMBER);
            sbA.append(" but was ");
            sbA.append(jsonTokenPeek);
            sbA.append(locationString());
            throw new IllegalStateException(sbA.toString());
        }
        long asLong = ((JsonPrimitive) peekStack()).getAsLong();
        popStack();
        int i = this.stackSize;
        if (i > 0) {
            int[] iArr = this.pathIndices;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return asLong;
    }

    @Override // com.google.gson.stream.JsonReader
    public String nextName() {
        expect(JsonToken.NAME);
        Map.Entry entry = (Map.Entry) ((Iterator) peekStack()).next();
        String str = (String) entry.getKey();
        this.pathNames[this.stackSize - 1] = str;
        push(entry.getValue());
        return str;
    }

    @Override // com.google.gson.stream.JsonReader
    public void nextNull() {
        expect(JsonToken.NULL);
        popStack();
        int i = this.stackSize;
        if (i > 0) {
            int[] iArr = this.pathIndices;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    @Override // com.google.gson.stream.JsonReader
    public String nextString() {
        JsonToken jsonTokenPeek = peek();
        if (jsonTokenPeek != JsonToken.STRING && jsonTokenPeek != JsonToken.NUMBER) {
            StringBuilder sbA = a.a("Expected ");
            sbA.append(JsonToken.STRING);
            sbA.append(" but was ");
            sbA.append(jsonTokenPeek);
            sbA.append(locationString());
            throw new IllegalStateException(sbA.toString());
        }
        String asString = ((JsonPrimitive) popStack()).getAsString();
        int i = this.stackSize;
        if (i > 0) {
            int[] iArr = this.pathIndices;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return asString;
    }

    @Override // com.google.gson.stream.JsonReader
    public JsonToken peek() {
        if (this.stackSize == 0) {
            return JsonToken.END_DOCUMENT;
        }
        Object objPeekStack = peekStack();
        if (objPeekStack instanceof Iterator) {
            boolean z = this.stack[this.stackSize - 2] instanceof JsonObject;
            Iterator it = (Iterator) objPeekStack;
            if (!it.hasNext()) {
                return z ? JsonToken.END_OBJECT : JsonToken.END_ARRAY;
            }
            if (z) {
                return JsonToken.NAME;
            }
            push(it.next());
            return peek();
        }
        if (objPeekStack instanceof JsonObject) {
            return JsonToken.BEGIN_OBJECT;
        }
        if (objPeekStack instanceof JsonArray) {
            return JsonToken.BEGIN_ARRAY;
        }
        if (!(objPeekStack instanceof JsonPrimitive)) {
            if (objPeekStack instanceof JsonNull) {
                return JsonToken.NULL;
            }
            if (objPeekStack == SENTINEL_CLOSED) {
                throw new IllegalStateException("JsonReader is closed");
            }
            throw new AssertionError();
        }
        JsonPrimitive jsonPrimitive = (JsonPrimitive) objPeekStack;
        if (jsonPrimitive.isString()) {
            return JsonToken.STRING;
        }
        if (jsonPrimitive.isBoolean()) {
            return JsonToken.BOOLEAN;
        }
        if (jsonPrimitive.isNumber()) {
            return JsonToken.NUMBER;
        }
        throw new AssertionError();
    }

    public void promoteNameToValue() {
        expect(JsonToken.NAME);
        Map.Entry entry = (Map.Entry) ((Iterator) peekStack()).next();
        push(entry.getValue());
        push(new JsonPrimitive((String) entry.getKey()));
    }

    @Override // com.google.gson.stream.JsonReader
    public void skipValue() {
        if (peek() == JsonToken.NAME) {
            nextName();
            this.pathNames[this.stackSize - 2] = "null";
        } else {
            popStack();
            int i = this.stackSize;
            if (i > 0) {
                this.pathNames[i - 1] = "null";
            }
        }
        int i2 = this.stackSize;
        if (i2 > 0) {
            int[] iArr = this.pathIndices;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
        }
    }

    @Override // com.google.gson.stream.JsonReader
    public String toString() {
        return JsonTreeReader.class.getSimpleName() + locationString();
    }

    @Override // com.google.gson.stream.JsonReader
    public String getPath() {
        return getPath(false);
    }
}
