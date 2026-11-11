package org.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: classes8.dex */
public class JSONTokener {
    private long character;
    private long characterPreviousLine;
    private boolean eof;
    private long index;
    private JSONParserConfiguration jsonParserConfiguration;
    private long line;
    private char previous;
    private final Reader reader;
    private boolean usePrevious;

    public static int dehexchar(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'A' && c <= 'F') {
            return c - '7';
        }
        if (c < 'a' || c > 'f') {
            return -1;
        }
        return c - 'W';
    }

    public JSONTokener(Reader reader) {
        this(reader, new JSONParserConfiguration());
    }

    public JSONTokener(Reader reader, JSONParserConfiguration jSONParserConfiguration) {
        this.jsonParserConfiguration = jSONParserConfiguration;
        this.reader = reader.markSupported() ? reader : new BufferedReader(reader);
        this.eof = false;
        this.usePrevious = false;
        this.previous = (char) 0;
        this.index = 0L;
        this.character = 1L;
        this.characterPreviousLine = 0L;
        this.line = 1L;
    }

    public JSONTokener(InputStream inputStream) {
        this(inputStream, new JSONParserConfiguration());
    }

    public JSONTokener(InputStream inputStream, JSONParserConfiguration jSONParserConfiguration) {
        this(new InputStreamReader(inputStream, Charset.forName("UTF-8")), jSONParserConfiguration);
    }

    public JSONTokener(String str) {
        this(new StringReader(str));
    }

    public JSONTokener(String str, JSONParserConfiguration jSONParserConfiguration) {
        this(new StringReader(str), jSONParserConfiguration);
    }

    public JSONParserConfiguration getJsonParserConfiguration() {
        return this.jsonParserConfiguration;
    }

    @Deprecated
    public void setJsonParserConfiguration(JSONParserConfiguration jSONParserConfiguration) {
        this.jsonParserConfiguration = jSONParserConfiguration;
    }

    public void back() throws JSONException {
        if (this.usePrevious || this.index <= 0) {
            throw new JSONException("Stepping back two steps is not supported");
        }
        decrementIndexes();
        this.usePrevious = true;
        this.eof = false;
    }

    private void decrementIndexes() {
        this.index--;
        char c = this.previous;
        if (c == '\r' || c == '\n') {
            this.line--;
            this.character = this.characterPreviousLine;
        } else {
            long j = this.character;
            if (j > 0) {
                this.character = j - 1;
            }
        }
    }

    public boolean end() {
        return this.eof && !this.usePrevious;
    }

    public boolean more() throws JSONException {
        if (this.usePrevious) {
            return true;
        }
        try {
            this.reader.mark(1);
            try {
                if (this.reader.read() <= 0) {
                    this.eof = true;
                    return false;
                }
                this.reader.reset();
                return true;
            } catch (IOException e) {
                throw new JSONException("Unable to read the next character from the stream", e);
            }
        } catch (IOException e2) {
            throw new JSONException("Unable to preserve stream position", e2);
        }
    }

    public char next() throws JSONException {
        int i;
        if (this.usePrevious) {
            this.usePrevious = false;
            i = this.previous;
        } else {
            try {
                i = this.reader.read();
            } catch (IOException e) {
                throw new JSONException(e);
            }
        }
        if (i <= 0) {
            this.eof = true;
            return (char) 0;
        }
        incrementIndexes(i);
        char c = (char) i;
        this.previous = c;
        return c;
    }

    protected char getPrevious() {
        return this.previous;
    }

    private void incrementIndexes(int i) {
        if (i > 0) {
            this.index++;
            if (i == 13) {
                this.line++;
                this.characterPreviousLine = this.character;
                this.character = 0L;
            } else {
                if (i == 10) {
                    if (this.previous != '\r') {
                        this.line++;
                        this.characterPreviousLine = this.character;
                    }
                    this.character = 0L;
                    return;
                }
                this.character++;
            }
        }
    }

    public char next(char c) throws JSONException {
        char next = next();
        if (next == c) {
            return next;
        }
        if (next > 0) {
            throw syntaxError("Expected '" + c + "' and instead saw '" + next + "'");
        }
        throw syntaxError("Expected '" + c + "' and instead saw ''");
    }

    public String next(int i) throws JSONException {
        if (i == 0) {
            return "";
        }
        char[] cArr = new char[i];
        for (int i2 = 0; i2 < i; i2++) {
            cArr[i2] = next();
            if (end()) {
                throw syntaxError("Substring bounds error");
            }
        }
        return new String(cArr);
    }

    public char nextClean() throws JSONException {
        char next;
        do {
            next = next();
            if (next == 0) {
                break;
            }
        } while (next <= ' ');
        return next;
    }

    public String nextString(char c) throws JSONException {
        char next;
        StringBuilder sb = new StringBuilder();
        while (true) {
            next = next();
            if (next == 0 || next == '\n' || next == '\r') {
                break;
            }
            if (next == '\\') {
                char next2 = next();
                if (next2 == '\"' || next2 == '\'' || next2 == '/' || next2 == '\\') {
                    sb.append(next2);
                } else if (next2 == 'b') {
                    sb.append('\b');
                } else if (next2 == 'f') {
                    sb.append('\f');
                } else if (next2 == 'n') {
                    sb.append('\n');
                } else if (next2 == 'r') {
                    sb.append('\r');
                } else if (next2 == 't') {
                    sb.append('\t');
                } else if (next2 == 'u') {
                    String next3 = next(4);
                    try {
                        sb.append((char) Integer.parseInt(next3, 16));
                    } catch (NumberFormatException e) {
                        throw syntaxError("Illegal escape. \\u must be followed by a 4 digit hexadecimal number. \\" + next3 + " is not valid.", e);
                    }
                } else {
                    throw syntaxError("Illegal escape. Escape sequence  \\" + next2 + " is not valid.");
                }
            } else {
                if (next == c) {
                    return sb.toString();
                }
                sb.append(next);
            }
        }
        throw syntaxError("Unterminated string. Character with int code " + ((int) next) + " is not allowed within a quoted string.");
    }

    public String nextTo(char c) throws JSONException {
        char next;
        StringBuilder sb = new StringBuilder();
        while (true) {
            next = next();
            if (next == c || next == 0 || next == '\n' || next == '\r') {
                break;
            }
            sb.append(next);
        }
        if (next != 0) {
            back();
        }
        return sb.toString().trim();
    }

    public String nextTo(String str) throws JSONException {
        char next;
        StringBuilder sb = new StringBuilder();
        while (true) {
            next = next();
            if (str.indexOf(next) >= 0 || next == 0 || next == '\n' || next == '\r') {
                break;
            }
            sb.append(next);
        }
        if (next != 0) {
            back();
        }
        return sb.toString().trim();
    }

    public Object nextValue() throws JSONException {
        char cNextClean = nextClean();
        if (cNextClean == '[') {
            back();
            try {
                return new JSONArray(this, this.jsonParserConfiguration);
            } catch (StackOverflowError e) {
                throw new JSONException("JSON Array or Object depth too large to process.", e);
            }
        }
        if (cNextClean == '{') {
            back();
            try {
                return new JSONObject(this, this.jsonParserConfiguration);
            } catch (StackOverflowError e2) {
                throw new JSONException("JSON Array or Object depth too large to process.", e2);
            }
        }
        return nextSimpleValue(cNextClean);
    }

    Object nextSimpleValue(char c) {
        JSONParserConfiguration jSONParserConfiguration = this.jsonParserConfiguration;
        if (jSONParserConfiguration != null && jSONParserConfiguration.isStrictMode() && c == '\'') {
            throw syntaxError("Strict mode error: Single quoted strings are not allowed");
        }
        if (c == '\"' || c == '\'') {
            return nextString(c);
        }
        StringBuilder sb = new StringBuilder();
        while (c >= ' ' && ",:]}/\\\"[{;=#".indexOf(c) < 0) {
            sb.append(c);
            c = next();
        }
        if (!this.eof) {
            back();
        }
        String strTrim = sb.toString().trim();
        if ("".equals(strTrim)) {
            throw syntaxError("Missing value");
        }
        JSONParserConfiguration jSONParserConfiguration2 = this.jsonParserConfiguration;
        if (jSONParserConfiguration2 != null && jSONParserConfiguration2.isStrictMode() && strTrim.endsWith(".")) {
            throw syntaxError(String.format("Strict mode error: Value '%s' ends with dot", strTrim));
        }
        Object objStringToValue = JSONObject.stringToValue(strTrim);
        JSONParserConfiguration jSONParserConfiguration3 = this.jsonParserConfiguration;
        if (jSONParserConfiguration3 != null && jSONParserConfiguration3.isStrictMode()) {
            if ((objStringToValue instanceof Boolean) && !"true".equals(strTrim) && !"false".equals(strTrim)) {
                throw syntaxError(String.format("Strict mode error: Value '%s' is not lowercase boolean", objStringToValue));
            }
            if (objStringToValue == JSONObject.NULL && !AbstractJsonLexerKt.NULL.equals(strTrim)) {
                throw syntaxError(String.format("Strict mode error: Value '%s' is not lowercase null", objStringToValue));
            }
            if (objStringToValue instanceof String) {
                throw syntaxError(String.format("Strict mode error: Value '%s' is not surrounded by quotes", objStringToValue));
            }
        }
        return objStringToValue;
    }

    public char skipTo(char c) throws JSONException {
        char next;
        try {
            long j = this.index;
            long j2 = this.character;
            long j3 = this.line;
            this.reader.mark(1000000);
            do {
                next = next();
                if (next == 0) {
                    this.reader.reset();
                    this.index = j;
                    this.character = j2;
                    this.line = j3;
                    return (char) 0;
                }
            } while (next != c);
            this.reader.mark(1);
            back();
            return next;
        } catch (IOException e) {
            throw new JSONException(e);
        }
    }

    public JSONException syntaxError(String str) {
        return new JSONException(str + toString());
    }

    public JSONException syntaxError(String str, Throwable th) {
        return new JSONException(str + toString(), th);
    }

    public String toString() {
        return " at " + this.index + " [character " + this.character + " line " + this.line + "]";
    }

    public void close() throws IOException {
        Reader reader = this.reader;
        if (reader != null) {
            reader.close();
        }
    }
}
