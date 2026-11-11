package com.grack.nanojson;

import java.io.InputStream;
import java.io.StringReader;
import java.util.Arrays;
import java.util.BitSet;

/* JADX INFO: loaded from: classes6.dex */
public final class JsonReader {
    private boolean inObject;
    private int token;
    private JsonTokener tokener;
    private BitSet states = new BitSet();
    private int stateIndex = 0;
    private boolean first = true;
    private StringBuilder key = new StringBuilder();

    public enum Type {
        OBJECT,
        ARRAY,
        STRING,
        NUMBER,
        BOOLEAN,
        NULL
    }

    JsonReader(JsonTokener jsonTokener) throws JsonParserException {
        this.tokener = jsonTokener;
        this.token = jsonTokener.advanceToToken(false);
    }

    private JsonParserException createTokenMismatchException(int... iArr) {
        return this.tokener.createParseException(null, "token mismatch (expected " + Arrays.toString(iArr) + ", was " + this.token + ")", true);
    }

    public static JsonReader from(InputStream inputStream) throws JsonParserException {
        return new JsonReader(new JsonTokener(inputStream));
    }

    public static JsonReader from(String str) throws JsonParserException {
        return new JsonReader(new JsonTokener(new StringReader(str)));
    }

    public void array() throws JsonParserException {
        if (this.token != 11) {
            throw createTokenMismatchException(11);
        }
        BitSet bitSet = this.states;
        int i = this.stateIndex;
        this.stateIndex = i + 1;
        bitSet.set(i, this.inObject);
        this.inObject = false;
        this.first = true;
    }

    public boolean bool() throws JsonParserException {
        int i = this.token;
        if (i == 6) {
            return true;
        }
        if (i == 7) {
            return false;
        }
        throw createTokenMismatchException(6, 7);
    }

    public Type current() throws JsonParserException {
        switch (this.token) {
            case 5:
                return Type.NULL;
            case 6:
            case 7:
                return Type.BOOLEAN;
            case 8:
                return Type.STRING;
            case 9:
                return Type.NUMBER;
            case 10:
                return Type.OBJECT;
            case 11:
                return Type.ARRAY;
            default:
                throw createTokenMismatchException(5, 6, 7, 9, 8, 10, 11);
        }
    }

    public double doubleVal() throws JsonParserException {
        return Double.parseDouble(this.tokener.reusableBuffer.toString());
    }

    public float floatVal() throws JsonParserException {
        return Float.parseFloat(this.tokener.reusableBuffer.toString());
    }

    public int intVal() throws JsonParserException {
        String string = this.tokener.reusableBuffer.toString();
        return this.tokener.isDouble ? (int) Double.parseDouble(string) : Integer.parseInt(string);
    }

    public String key() throws JsonParserException {
        if (this.inObject) {
            return this.key.toString();
        }
        throw this.tokener.createParseException(null, "Not reading an object", true);
    }

    public long longVal() throws JsonParserException {
        String string = this.tokener.reusableBuffer.toString();
        return this.tokener.isDouble ? (long) Double.parseDouble(string) : Long.parseLong(string);
    }

    public boolean next() throws JsonParserException {
        int i = this.stateIndex;
        JsonTokener jsonTokener = this.tokener;
        if (i == 0) {
            throw jsonTokener.createParseException(null, "Unabled to call next() at the root", true);
        }
        int iAdvanceToToken = jsonTokener.advanceToToken(false);
        this.token = iAdvanceToToken;
        if (!this.inObject) {
            if (iAdvanceToToken != 4) {
                if (!this.first) {
                    if (iAdvanceToToken != 1) {
                        throw createTokenMismatchException(1, 4);
                    }
                    this.token = this.tokener.advanceToToken(false);
                }
            }
            BitSet bitSet = this.states;
            int i2 = this.stateIndex - 1;
            this.stateIndex = i2;
            this.inObject = bitSet.get(i2);
            this.first = false;
            return false;
        }
        if (iAdvanceToToken != 3) {
            if (!this.first) {
                if (iAdvanceToToken != 1) {
                    throw createTokenMismatchException(1, 3);
                }
                this.token = this.tokener.advanceToToken(false);
            }
            if (this.token != 8) {
                throw createTokenMismatchException(8);
            }
            this.key.setLength(0);
            this.key.append((CharSequence) this.tokener.reusableBuffer);
            int iAdvanceToToken2 = this.tokener.advanceToToken(false);
            this.token = iAdvanceToToken2;
            if (iAdvanceToToken2 != 2) {
                throw createTokenMismatchException(2);
            }
            this.token = this.tokener.advanceToToken(false);
        }
        BitSet bitSet2 = this.states;
        int i3 = this.stateIndex - 1;
        this.stateIndex = i3;
        this.inObject = bitSet2.get(i3);
        this.first = false;
        return false;
        int i4 = this.token;
        if (i4 != 5 && i4 != 8 && i4 != 9 && i4 != 6 && i4 != 7 && i4 != 10 && i4 != 11) {
            throw createTokenMismatchException(5, 8, 9, 6, 7, 10, 11);
        }
        this.first = false;
        return true;
    }

    public void nul() throws JsonParserException {
        if (this.token != 5) {
            throw createTokenMismatchException(5);
        }
    }

    public Number number() throws JsonParserException {
        if (this.token == 5) {
            return null;
        }
        return new JsonLazyNumber(this.tokener.reusableBuffer.toString(), this.tokener.isDouble);
    }

    public void object() throws JsonParserException {
        if (this.token != 10) {
            throw createTokenMismatchException(10);
        }
        BitSet bitSet = this.states;
        int i = this.stateIndex;
        this.stateIndex = i + 1;
        bitSet.set(i, this.inObject);
        this.inObject = true;
        this.first = true;
    }

    public boolean pop() throws JsonParserException {
        while (!next()) {
        }
        this.first = false;
        BitSet bitSet = this.states;
        int i = this.stateIndex - 1;
        this.stateIndex = i;
        this.inObject = bitSet.get(i);
        return this.token != 0;
    }

    public String string() throws JsonParserException {
        int i = this.token;
        if (i == 5) {
            return null;
        }
        if (i == 8) {
            return this.tokener.reusableBuffer.toString();
        }
        throw createTokenMismatchException(5, 8);
    }

    public Object value() throws JsonParserException {
        boolean z;
        switch (this.token) {
            case 5:
                return null;
            case 6:
                z = true;
                break;
            case 7:
                z = false;
                break;
            case 8:
                return string();
            case 9:
                return number();
            default:
                throw createTokenMismatchException(5, 6, 7, 9, 8);
        }
        return Boolean.valueOf(z);
    }
}
