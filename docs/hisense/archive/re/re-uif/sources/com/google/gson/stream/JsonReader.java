package com.google.gson.stream;

import com.google.gson.internal.JsonReaderInternalAccess;
import com.google.gson.internal.bind.JsonTreeReader;
import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;

/* JADX INFO: loaded from: classes.dex */
public class JsonReader implements Closeable {
    private static final char[] NON_EXECUTE_PREFIX = ")]}'\n".toCharArray();
    private final Reader in;
    private String name;
    private boolean skipping;
    private JsonToken token;
    private String value;
    private int valueLength;
    private int valuePos;
    private final StringPool stringPool = new StringPool();
    private boolean lenient = false;
    private final char[] buffer = new char[1024];
    private int pos = 0;
    private int limit = 0;
    private int bufferStartLine = 1;
    private int bufferStartColumn = 1;
    private JsonScope[] stack = new JsonScope[32];
    private int stackSize = 0;

    static {
        JsonReaderInternalAccess.INSTANCE = new JsonReaderInternalAccess() { // from class: com.google.gson.stream.JsonReader.1
            @Override // com.google.gson.internal.JsonReaderInternalAccess
            public void promoteNameToValue(JsonReader jsonReader) throws IOException {
                if (jsonReader instanceof JsonTreeReader) {
                    ((JsonTreeReader) jsonReader).promoteNameToValue();
                    return;
                }
                jsonReader.peek();
                if (jsonReader.token == JsonToken.NAME) {
                    jsonReader.value = jsonReader.name;
                    jsonReader.name = null;
                    jsonReader.token = JsonToken.STRING;
                    return;
                }
                throw new IllegalStateException("Expected a name but was " + jsonReader.peek() + "  at line " + jsonReader.getLineNumber() + " column " + jsonReader.getColumnNumber());
            }
        };
    }

    public JsonReader(Reader reader) {
        push(JsonScope.EMPTY_DOCUMENT);
        this.skipping = false;
        if (reader == null) {
            throw new NullPointerException("in == null");
        }
        this.in = reader;
    }

    public final void setLenient(boolean z) {
        this.lenient = z;
    }

    public final boolean isLenient() {
        return this.lenient;
    }

    public void beginArray() throws IOException {
        expect(JsonToken.BEGIN_ARRAY);
    }

    public void endArray() throws IOException {
        expect(JsonToken.END_ARRAY);
    }

    public void beginObject() throws IOException {
        expect(JsonToken.BEGIN_OBJECT);
    }

    public void endObject() throws IOException {
        expect(JsonToken.END_OBJECT);
    }

    private void expect(JsonToken jsonToken) throws IOException {
        peek();
        if (this.token != jsonToken) {
            throw new IllegalStateException("Expected " + jsonToken + " but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber());
        }
        advance();
    }

    public boolean hasNext() throws IOException {
        peek();
        JsonToken jsonToken = this.token;
        return (jsonToken == JsonToken.END_OBJECT || jsonToken == JsonToken.END_ARRAY) ? false : true;
    }

    public JsonToken peek() throws IOException {
        JsonToken jsonToken;
        JsonToken jsonToken2 = this.token;
        if (jsonToken2 != null) {
            return jsonToken2;
        }
        switch (AnonymousClass2.$SwitchMap$com$google$gson$stream$JsonScope[this.stack[this.stackSize - 1].ordinal()]) {
            case 1:
                if (this.lenient) {
                    consumeNonExecutePrefix();
                }
                this.stack[this.stackSize - 1] = JsonScope.NONEMPTY_DOCUMENT;
                JsonToken jsonTokenNextValue = nextValue();
                if (this.lenient || (jsonToken = this.token) == JsonToken.BEGIN_ARRAY || jsonToken == JsonToken.BEGIN_OBJECT) {
                    return jsonTokenNextValue;
                }
                throw new IOException("Expected JSON document to start with '[' or '{' but was " + this.token + " at line " + getLineNumber() + " column " + getColumnNumber());
            case 2:
                return nextInArray(true);
            case 3:
                return nextInArray(false);
            case 4:
                return nextInObject(true);
            case 5:
                return objectValue();
            case 6:
                return nextInObject(false);
            case 7:
                if (nextNonWhitespace(false) == -1) {
                    return JsonToken.END_DOCUMENT;
                }
                this.pos--;
                if (!this.lenient) {
                    syntaxError("Expected EOF");
                    throw null;
                }
                return nextValue();
            case 8:
                throw new IllegalStateException("JsonReader is closed");
            default:
                throw new AssertionError();
        }
    }

    /* JADX INFO: renamed from: com.google.gson.stream.JsonReader$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$google$gson$stream$JsonScope;

        static {
            int[] iArr = new int[JsonScope.values().length];
            $SwitchMap$com$google$gson$stream$JsonScope = iArr;
            try {
                iArr[JsonScope.EMPTY_DOCUMENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonScope[JsonScope.EMPTY_ARRAY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonScope[JsonScope.NONEMPTY_ARRAY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonScope[JsonScope.EMPTY_OBJECT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonScope[JsonScope.DANGLING_NAME.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonScope[JsonScope.NONEMPTY_OBJECT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonScope[JsonScope.NONEMPTY_DOCUMENT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonScope[JsonScope.CLOSED.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    private void consumeNonExecutePrefix() throws IOException {
        nextNonWhitespace(true);
        int i = this.pos - 1;
        this.pos = i;
        char[] cArr = NON_EXECUTE_PREFIX;
        if (i + cArr.length > this.limit && !fillBuffer(cArr.length)) {
            return;
        }
        int i2 = 0;
        while (true) {
            char[] cArr2 = NON_EXECUTE_PREFIX;
            if (i2 < cArr2.length) {
                if (this.buffer[this.pos + i2] != cArr2[i2]) {
                    return;
                } else {
                    i2++;
                }
            } else {
                this.pos += cArr2.length;
                return;
            }
        }
    }

    private JsonToken advance() throws IOException {
        peek();
        JsonToken jsonToken = this.token;
        this.token = null;
        this.value = null;
        this.name = null;
        return jsonToken;
    }

    public String nextName() throws IOException {
        peek();
        if (this.token != JsonToken.NAME) {
            throw new IllegalStateException("Expected a name but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber());
        }
        String str = this.name;
        advance();
        return str;
    }

    public String nextString() throws IOException {
        peek();
        JsonToken jsonToken = this.token;
        if (jsonToken != JsonToken.STRING && jsonToken != JsonToken.NUMBER) {
            throw new IllegalStateException("Expected a string but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber());
        }
        String str = this.value;
        advance();
        return str;
    }

    public boolean nextBoolean() throws IOException {
        peek();
        if (this.token != JsonToken.BOOLEAN) {
            throw new IllegalStateException("Expected a boolean but was " + this.token + " at line " + getLineNumber() + " column " + getColumnNumber());
        }
        boolean z = this.value == "true";
        advance();
        return z;
    }

    public void nextNull() throws IOException {
        peek();
        if (this.token != JsonToken.NULL) {
            throw new IllegalStateException("Expected null but was " + this.token + " at line " + getLineNumber() + " column " + getColumnNumber());
        }
        advance();
    }

    public double nextDouble() throws IOException {
        peek();
        JsonToken jsonToken = this.token;
        if (jsonToken != JsonToken.STRING && jsonToken != JsonToken.NUMBER) {
            throw new IllegalStateException("Expected a double but was " + this.token + " at line " + getLineNumber() + " column " + getColumnNumber());
        }
        double d = Double.parseDouble(this.value);
        if (d >= 1.0d && this.value.startsWith("0")) {
            throw new MalformedJsonException("JSON forbids octal prefixes: " + this.value + " at line " + getLineNumber() + " column " + getColumnNumber());
        }
        if (!this.lenient && (Double.isNaN(d) || Double.isInfinite(d))) {
            throw new MalformedJsonException("JSON forbids NaN and infinities: " + this.value + " at line " + getLineNumber() + " column " + getColumnNumber());
        }
        advance();
        return d;
    }

    public long nextLong() throws IOException {
        long j;
        peek();
        JsonToken jsonToken = this.token;
        if (jsonToken != JsonToken.STRING && jsonToken != JsonToken.NUMBER) {
            throw new IllegalStateException("Expected a long but was " + this.token + " at line " + getLineNumber() + " column " + getColumnNumber());
        }
        try {
            j = Long.parseLong(this.value);
        } catch (NumberFormatException unused) {
            double d = Double.parseDouble(this.value);
            long j2 = (long) d;
            if (j2 != d) {
                throw new NumberFormatException("Expected a long but was " + this.value + " at line " + getLineNumber() + " column " + getColumnNumber());
            }
            j = j2;
        }
        if (j >= 1 && this.value.startsWith("0")) {
            throw new MalformedJsonException("JSON forbids octal prefixes: " + this.value + " at line " + getLineNumber() + " column " + getColumnNumber());
        }
        advance();
        return j;
    }

    public int nextInt() throws IOException {
        int i;
        peek();
        JsonToken jsonToken = this.token;
        if (jsonToken != JsonToken.STRING && jsonToken != JsonToken.NUMBER) {
            throw new IllegalStateException("Expected an int but was " + this.token + " at line " + getLineNumber() + " column " + getColumnNumber());
        }
        try {
            i = Integer.parseInt(this.value);
        } catch (NumberFormatException unused) {
            double d = Double.parseDouble(this.value);
            int i2 = (int) d;
            if (i2 != d) {
                throw new NumberFormatException("Expected an int but was " + this.value + " at line " + getLineNumber() + " column " + getColumnNumber());
            }
            i = i2;
        }
        if (i >= 1 && this.value.startsWith("0")) {
            throw new MalformedJsonException("JSON forbids octal prefixes: " + this.value + " at line " + getLineNumber() + " column " + getColumnNumber());
        }
        advance();
        return i;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.value = null;
        this.token = null;
        this.stack[0] = JsonScope.CLOSED;
        this.stackSize = 1;
        this.in.close();
    }

    public void skipValue() throws IOException {
        this.skipping = true;
        int i = 0;
        do {
            try {
                JsonToken jsonTokenAdvance = advance();
                if (jsonTokenAdvance == JsonToken.BEGIN_ARRAY || jsonTokenAdvance == JsonToken.BEGIN_OBJECT) {
                    i++;
                } else if (jsonTokenAdvance == JsonToken.END_ARRAY || jsonTokenAdvance == JsonToken.END_OBJECT) {
                    i--;
                }
            } finally {
                this.skipping = false;
            }
        } while (i != 0);
    }

    private void push(JsonScope jsonScope) {
        int i = this.stackSize;
        JsonScope[] jsonScopeArr = this.stack;
        if (i == jsonScopeArr.length) {
            JsonScope[] jsonScopeArr2 = new JsonScope[i * 2];
            System.arraycopy(jsonScopeArr, 0, jsonScopeArr2, 0, i);
            this.stack = jsonScopeArr2;
        }
        JsonScope[] jsonScopeArr3 = this.stack;
        int i2 = this.stackSize;
        this.stackSize = i2 + 1;
        jsonScopeArr3[i2] = jsonScope;
    }

    private JsonToken nextInArray(boolean z) throws IOException {
        if (z) {
            this.stack[this.stackSize - 1] = JsonScope.NONEMPTY_ARRAY;
        } else {
            int iNextNonWhitespace = nextNonWhitespace(true);
            if (iNextNonWhitespace != 44) {
                if (iNextNonWhitespace != 59) {
                    if (iNextNonWhitespace == 93) {
                        this.stackSize--;
                        JsonToken jsonToken = JsonToken.END_ARRAY;
                        this.token = jsonToken;
                        return jsonToken;
                    }
                    syntaxError("Unterminated array");
                    throw null;
                }
                checkLenient();
            }
        }
        int iNextNonWhitespace2 = nextNonWhitespace(true);
        if (iNextNonWhitespace2 != 44 && iNextNonWhitespace2 != 59) {
            if (iNextNonWhitespace2 != 93) {
                this.pos--;
                return nextValue();
            }
            if (z) {
                this.stackSize--;
                JsonToken jsonToken2 = JsonToken.END_ARRAY;
                this.token = jsonToken2;
                return jsonToken2;
            }
        }
        checkLenient();
        this.pos--;
        this.value = "null";
        JsonToken jsonToken3 = JsonToken.NULL;
        this.token = jsonToken3;
        return jsonToken3;
    }

    private JsonToken nextInObject(boolean z) throws IOException {
        if (z) {
            if (nextNonWhitespace(true) == 125) {
                this.stackSize--;
                JsonToken jsonToken = JsonToken.END_OBJECT;
                this.token = jsonToken;
                return jsonToken;
            }
            this.pos--;
        } else {
            int iNextNonWhitespace = nextNonWhitespace(true);
            if (iNextNonWhitespace != 44 && iNextNonWhitespace != 59) {
                if (iNextNonWhitespace == 125) {
                    this.stackSize--;
                    JsonToken jsonToken2 = JsonToken.END_OBJECT;
                    this.token = jsonToken2;
                    return jsonToken2;
                }
                syntaxError("Unterminated object");
                throw null;
            }
        }
        int iNextNonWhitespace2 = nextNonWhitespace(true);
        if (iNextNonWhitespace2 == 34) {
            this.name = nextString((char) iNextNonWhitespace2);
        } else if (iNextNonWhitespace2 == 39) {
            checkLenient();
            this.name = nextString((char) iNextNonWhitespace2);
        } else {
            checkLenient();
            this.pos--;
            String strNextLiteral = nextLiteral(false);
            this.name = strNextLiteral;
            if (strNextLiteral.length() == 0) {
                syntaxError("Expected name");
                throw null;
            }
        }
        this.stack[this.stackSize - 1] = JsonScope.DANGLING_NAME;
        JsonToken jsonToken3 = JsonToken.NAME;
        this.token = jsonToken3;
        return jsonToken3;
    }

    private JsonToken objectValue() throws IOException {
        int iNextNonWhitespace = nextNonWhitespace(true);
        if (iNextNonWhitespace != 58) {
            if (iNextNonWhitespace == 61) {
                checkLenient();
                if (this.pos < this.limit || fillBuffer(1)) {
                    char[] cArr = this.buffer;
                    int i = this.pos;
                    if (cArr[i] == '>') {
                        this.pos = i + 1;
                    }
                }
            } else {
                syntaxError("Expected ':'");
                throw null;
            }
        }
        this.stack[this.stackSize - 1] = JsonScope.NONEMPTY_OBJECT;
        return nextValue();
    }

    private JsonToken nextValue() throws IOException {
        int iNextNonWhitespace = nextNonWhitespace(true);
        if (iNextNonWhitespace != 34) {
            if (iNextNonWhitespace != 39) {
                if (iNextNonWhitespace == 91) {
                    push(JsonScope.EMPTY_ARRAY);
                    JsonToken jsonToken = JsonToken.BEGIN_ARRAY;
                    this.token = jsonToken;
                    return jsonToken;
                }
                if (iNextNonWhitespace == 123) {
                    push(JsonScope.EMPTY_OBJECT);
                    JsonToken jsonToken2 = JsonToken.BEGIN_OBJECT;
                    this.token = jsonToken2;
                    return jsonToken2;
                }
                this.pos--;
                return readLiteral();
            }
            checkLenient();
        }
        this.value = nextString((char) iNextNonWhitespace);
        JsonToken jsonToken3 = JsonToken.STRING;
        this.token = jsonToken3;
        return jsonToken3;
    }

    private boolean fillBuffer(int i) throws IOException {
        int i2;
        char[] cArr = this.buffer;
        int i3 = this.bufferStartLine;
        int i4 = this.bufferStartColumn;
        int i5 = this.pos;
        for (int i6 = 0; i6 < i5; i6++) {
            if (cArr[i6] == '\n') {
                i3++;
                i4 = 1;
            } else {
                i4++;
            }
        }
        this.bufferStartLine = i3;
        this.bufferStartColumn = i4;
        int i7 = this.limit;
        int i8 = this.pos;
        if (i7 != i8) {
            int i9 = i7 - i8;
            this.limit = i9;
            System.arraycopy(cArr, i8, cArr, 0, i9);
        } else {
            this.limit = 0;
        }
        this.pos = 0;
        do {
            Reader reader = this.in;
            int i10 = this.limit;
            int i11 = reader.read(cArr, i10, cArr.length - i10);
            if (i11 == -1) {
                return false;
            }
            int i12 = this.limit + i11;
            this.limit = i12;
            if (this.bufferStartLine == 1 && (i2 = this.bufferStartColumn) == 1 && i12 > 0 && cArr[0] == 65279) {
                this.pos++;
                this.bufferStartColumn = i2 - 1;
            }
        } while (this.limit < i);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getLineNumber() {
        int i = this.bufferStartLine;
        for (int i2 = 0; i2 < this.pos; i2++) {
            if (this.buffer[i2] == '\n') {
                i++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getColumnNumber() {
        int i = this.bufferStartColumn;
        for (int i2 = 0; i2 < this.pos; i2++) {
            i = this.buffer[i2] == '\n' ? 1 : i + 1;
        }
        return i;
    }

    private int nextNonWhitespace(boolean z) throws IOException {
        char[] cArr = this.buffer;
        int i = this.pos;
        int i2 = this.limit;
        while (true) {
            if (i == i2) {
                this.pos = i;
                if (!fillBuffer(1)) {
                    if (!z) {
                        return -1;
                    }
                    throw new EOFException("End of input at line " + getLineNumber() + " column " + getColumnNumber());
                }
                i = this.pos;
                i2 = this.limit;
            }
            int i3 = i + 1;
            char c = cArr[i];
            if (c == '\t' || c == '\n' || c == '\r' || c == ' ') {
                i = i3;
            } else if (c == '#') {
                this.pos = i3;
                checkLenient();
                skipToEndOfLine();
                i = this.pos;
                i2 = this.limit;
            } else if (c == '/') {
                this.pos = i3;
                if (i3 == i2 && !fillBuffer(1)) {
                    return c;
                }
                checkLenient();
                int i4 = this.pos;
                char c2 = cArr[i4];
                if (c2 == '*') {
                    this.pos = i4 + 1;
                    if (!skipTo("*/")) {
                        syntaxError("Unterminated comment");
                        throw null;
                    }
                    i = this.pos + 2;
                    i2 = this.limit;
                } else {
                    if (c2 != '/') {
                        return c;
                    }
                    this.pos = i4 + 1;
                    skipToEndOfLine();
                    i = this.pos;
                    i2 = this.limit;
                }
            } else {
                this.pos = i3;
                return c;
            }
        }
    }

    private void checkLenient() throws IOException {
        if (this.lenient) {
            return;
        }
        syntaxError("Use JsonReader.setLenient(true) to accept malformed JSON");
        throw null;
    }

    private void skipToEndOfLine() throws IOException {
        char c;
        do {
            if (this.pos >= this.limit && !fillBuffer(1)) {
                return;
            }
            char[] cArr = this.buffer;
            int i = this.pos;
            this.pos = i + 1;
            c = cArr[i];
            if (c == '\r') {
                return;
            }
        } while (c != '\n');
    }

    private boolean skipTo(String str) throws IOException {
        while (true) {
            if (this.pos + str.length() > this.limit && !fillBuffer(str.length())) {
                return false;
            }
            for (int i = 0; i < str.length(); i++) {
                if (this.buffer[this.pos + i] != str.charAt(i)) {
                    this.pos++;
                }
            }
            return true;
        }
    }

    private String nextString(char c) throws IOException {
        int i;
        char[] cArr = this.buffer;
        StringBuilder sb = null;
        do {
            int i2 = this.pos;
            int i3 = this.limit;
            while (true) {
                int i4 = i3;
                i = i2;
                while (true) {
                    if (i2 < i4) {
                        int i5 = i2 + 1;
                        char c2 = cArr[i2];
                        if (c2 == c) {
                            this.pos = i5;
                            if (this.skipping) {
                                return "skipped!";
                            }
                            if (sb == null) {
                                return this.stringPool.get(cArr, i, (i5 - i) - 1);
                            }
                            sb.append(cArr, i, (i5 - i) - 1);
                            return sb.toString();
                        }
                        if (c2 == '\\') {
                            this.pos = i5;
                            if (sb == null) {
                                sb = new StringBuilder();
                            }
                            sb.append(cArr, i, (i5 - i) - 1);
                            sb.append(readEscapeCharacter());
                            i2 = this.pos;
                            i3 = this.limit;
                        } else {
                            i2 = i5;
                        }
                    }
                }
            }
            if (sb == null) {
                sb = new StringBuilder();
            }
            sb.append(cArr, i, i2 - i);
            this.pos = i2;
        } while (fillBuffer(1));
        syntaxError("Unterminated string");
        throw null;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:31:0x004a. Please report as an issue. */
    private String nextLiteral(boolean z) throws IOException {
        this.valuePos = -1;
        int i = 0;
        this.valueLength = 0;
        String string = null;
        int i2 = 0;
        StringBuilder sb = null;
        while (true) {
            int i3 = this.pos;
            if (i3 + i2 < this.limit) {
                char c = this.buffer[i3 + i2];
                if (c != '\t' && c != '\n' && c != '\f' && c != '\r' && c != ' ') {
                    if (c != '#') {
                        if (c != ',') {
                            if (c != '/' && c != '=') {
                                if (c != '{' && c != '}' && c != ':') {
                                    if (c != ';') {
                                        switch (c) {
                                            case '[':
                                            case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_SEEK_READY /* 93 */:
                                                break;
                                            case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_UOP_INVALID /* 92 */:
                                                break;
                                            default:
                                                i2++;
                                                break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    checkLenient();
                }
                i = i2;
            } else if (i2 < this.buffer.length) {
                if (!fillBuffer(i2 + 1)) {
                    this.buffer[this.limit] = 0;
                    i = i2;
                }
            } else {
                if (sb == null) {
                    sb = new StringBuilder();
                }
                sb.append(this.buffer, this.pos, i2);
                this.valueLength += i2;
                this.pos += i2;
                if (fillBuffer(1)) {
                    i2 = 0;
                }
            }
            if (z && sb == null) {
                this.valuePos = this.pos;
            } else if (this.skipping) {
                string = "skipped!";
            } else if (sb == null) {
                string = this.stringPool.get(this.buffer, this.pos, i);
            } else {
                sb.append(this.buffer, this.pos, i);
                string = sb.toString();
            }
            this.valueLength += i;
            this.pos += i;
            return string;
        }
    }

    public String toString() {
        return getClass().getSimpleName() + " near " + ((Object) getSnippet());
    }

    private char readEscapeCharacter() throws IOException {
        int i;
        int i2;
        if (this.pos == this.limit && !fillBuffer(1)) {
            syntaxError("Unterminated escape sequence");
            throw null;
        }
        char[] cArr = this.buffer;
        int i3 = this.pos;
        int i4 = i3 + 1;
        this.pos = i4;
        char c = cArr[i3];
        if (c == 'b') {
            return '\b';
        }
        if (c == 'f') {
            return '\f';
        }
        if (c == 'n') {
            return '\n';
        }
        if (c == 'r') {
            return '\r';
        }
        if (c == 't') {
            return '\t';
        }
        if (c != 'u') {
            return c;
        }
        if (i4 + 4 > this.limit && !fillBuffer(4)) {
            syntaxError("Unterminated escape sequence");
            throw null;
        }
        char c2 = 0;
        int i5 = this.pos;
        int i6 = i5 + 4;
        while (i5 < i6) {
            char c3 = this.buffer[i5];
            char c4 = (char) (c2 << 4);
            if (c3 < '0' || c3 > '9') {
                if (c3 >= 'a' && c3 <= 'f') {
                    i = c3 - 'a';
                } else {
                    if (c3 < 'A' || c3 > 'F') {
                        throw new NumberFormatException("\\u" + this.stringPool.get(this.buffer, this.pos, 4));
                    }
                    i = c3 - 'A';
                }
                i2 = i + 10;
            } else {
                i2 = c3 - '0';
            }
            c2 = (char) (c4 + i2);
            i5++;
        }
        this.pos += 4;
        return c2;
    }

    private JsonToken readLiteral() throws IOException {
        this.value = nextLiteral(true);
        if (this.valueLength == 0) {
            syntaxError("Expected literal value");
            throw null;
        }
        JsonToken jsonTokenDecodeLiteral = decodeLiteral();
        this.token = jsonTokenDecodeLiteral;
        if (jsonTokenDecodeLiteral == JsonToken.STRING) {
            checkLenient();
        }
        return this.token;
    }

    private JsonToken decodeLiteral() throws IOException {
        int i = this.valuePos;
        if (i == -1) {
            return JsonToken.STRING;
        }
        if (this.valueLength == 4) {
            char[] cArr = this.buffer;
            if ('n' == cArr[i] || 'N' == cArr[i]) {
                char[] cArr2 = this.buffer;
                int i2 = this.valuePos;
                if ('u' == cArr2[i2 + 1] || 'U' == cArr2[i2 + 1]) {
                    char[] cArr3 = this.buffer;
                    int i3 = this.valuePos;
                    if ('l' == cArr3[i3 + 2] || 'L' == cArr3[i3 + 2]) {
                        char[] cArr4 = this.buffer;
                        int i4 = this.valuePos;
                        if ('l' == cArr4[i4 + 3] || 'L' == cArr4[i4 + 3]) {
                            this.value = "null";
                            return JsonToken.NULL;
                        }
                    }
                }
            }
        }
        if (this.valueLength == 4) {
            char[] cArr5 = this.buffer;
            int i5 = this.valuePos;
            if ('t' == cArr5[i5] || 'T' == cArr5[i5]) {
                char[] cArr6 = this.buffer;
                int i6 = this.valuePos;
                if ('r' == cArr6[i6 + 1] || 'R' == cArr6[i6 + 1]) {
                    char[] cArr7 = this.buffer;
                    int i7 = this.valuePos;
                    if ('u' == cArr7[i7 + 2] || 'U' == cArr7[i7 + 2]) {
                        char[] cArr8 = this.buffer;
                        int i8 = this.valuePos;
                        if ('e' == cArr8[i8 + 3] || 'E' == cArr8[i8 + 3]) {
                            this.value = "true";
                            return JsonToken.BOOLEAN;
                        }
                    }
                }
            }
        }
        if (this.valueLength == 5) {
            char[] cArr9 = this.buffer;
            int i9 = this.valuePos;
            if ('f' == cArr9[i9] || 'F' == cArr9[i9]) {
                char[] cArr10 = this.buffer;
                int i10 = this.valuePos;
                if ('a' == cArr10[i10 + 1] || 'A' == cArr10[i10 + 1]) {
                    char[] cArr11 = this.buffer;
                    int i11 = this.valuePos;
                    if ('l' == cArr11[i11 + 2] || 'L' == cArr11[i11 + 2]) {
                        char[] cArr12 = this.buffer;
                        int i12 = this.valuePos;
                        if ('s' == cArr12[i12 + 3] || 'S' == cArr12[i12 + 3]) {
                            char[] cArr13 = this.buffer;
                            int i13 = this.valuePos;
                            if ('e' == cArr13[i13 + 4] || 'E' == cArr13[i13 + 4]) {
                                this.value = "false";
                                return JsonToken.BOOLEAN;
                            }
                        }
                    }
                }
            }
        }
        this.value = this.stringPool.get(this.buffer, this.valuePos, this.valueLength);
        return decodeNumber(this.buffer, this.valuePos, this.valueLength);
    }

    private JsonToken decodeNumber(char[] cArr, int i, int i2) {
        int i3;
        int i4;
        char c;
        char c2 = cArr[i];
        if (c2 == '-') {
            int i5 = i + 1;
            i3 = i5;
            c2 = cArr[i5];
        } else {
            i3 = i;
        }
        if (c2 == '0') {
            i4 = i3 + 1;
            c = cArr[i4];
        } else if (c2 >= '1' && c2 <= '9') {
            i4 = i3 + 1;
            c = cArr[i4];
            while (c >= '0' && c <= '9') {
                i4++;
                c = cArr[i4];
            }
        } else {
            return JsonToken.STRING;
        }
        if (c == '.') {
            i4++;
            c = cArr[i4];
            while (c >= '0' && c <= '9') {
                i4++;
                c = cArr[i4];
            }
        }
        if (c == 'e' || c == 'E') {
            int i6 = i4 + 1;
            char c3 = cArr[i6];
            if (c3 == '+' || c3 == '-') {
                i6++;
                c3 = cArr[i6];
            }
            if (c3 >= '0' && c3 <= '9') {
                i4 = i6 + 1;
                char c4 = cArr[i4];
                while (c4 >= '0' && c4 <= '9') {
                    i4++;
                    c4 = cArr[i4];
                }
            } else {
                return JsonToken.STRING;
            }
        }
        if (i4 == i + i2) {
            return JsonToken.NUMBER;
        }
        return JsonToken.STRING;
    }

    private IOException syntaxError(String str) throws IOException {
        throw new MalformedJsonException(str + " at line " + getLineNumber() + " column " + getColumnNumber());
    }

    private CharSequence getSnippet() {
        StringBuilder sb = new StringBuilder();
        int iMin = Math.min(this.pos, 20);
        sb.append(this.buffer, this.pos - iMin, iMin);
        sb.append(this.buffer, this.pos, Math.min(this.limit - this.pos, 20));
        return sb;
    }
}
