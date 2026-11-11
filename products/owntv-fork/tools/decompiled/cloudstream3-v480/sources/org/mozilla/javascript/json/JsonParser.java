package org.mozilla.javascript.json;

import java.util.ArrayList;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;

/* JADX INFO: loaded from: classes8.dex */
public class JsonParser {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private Context cx;
    private int length;
    private int pos;
    private Scriptable scope;
    private String src;

    private static int fromHex(char c) {
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

    public JsonParser(Context context, Scriptable scriptable) {
        this.cx = context;
        this.scope = scriptable;
    }

    public synchronized Object parseValue(String str) throws ParseException {
        Object value;
        try {
            if (str == null) {
                throw new ParseException("Input string may not be null");
            }
            this.pos = 0;
            this.length = str.length();
            this.src = str;
            value = readValue();
            consumeWhitespace();
            if (this.pos < this.length) {
                throw new ParseException("Expected end of stream at char " + this.pos);
            }
        } catch (Throwable th) {
            throw th;
        }
        return value;
    }

    private Object readValue() throws ParseException {
        consumeWhitespace();
        int i = this.pos;
        if (i < this.length) {
            String str = this.src;
            this.pos = i + 1;
            char cCharAt = str.charAt(i);
            if (cCharAt == '\"') {
                return readString();
            }
            if (cCharAt != '-') {
                if (cCharAt == '[') {
                    return readArray();
                }
                if (cCharAt == 'f') {
                    return readFalse();
                }
                if (cCharAt == 'n') {
                    return readNull();
                }
                if (cCharAt == 't') {
                    return readTrue();
                }
                if (cCharAt == '{') {
                    return readObject();
                }
                switch (cCharAt) {
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                        break;
                    default:
                        throw new ParseException("Unexpected token: " + cCharAt);
                }
            }
            return readNumber(cCharAt);
        }
        throw new ParseException("Empty JSON string");
    }

    private Object readObject() throws ParseException {
        consumeWhitespace();
        Scriptable scriptableNewObject = this.cx.newObject(this.scope);
        int i = this.pos;
        if (i < this.length && this.src.charAt(i) == '}') {
            this.pos++;
            return scriptableNewObject;
        }
        boolean z = false;
        while (true) {
            int i2 = this.pos;
            if (i2 < this.length) {
                String str = this.src;
                this.pos = i2 + 1;
                char cCharAt = str.charAt(i2);
                if (cCharAt != '\"') {
                    if (cCharAt != ',') {
                        if (cCharAt != '}') {
                            throw new ParseException("Unexpected token in object literal");
                        }
                        if (z) {
                            return scriptableNewObject;
                        }
                        throw new ParseException("Unexpected comma in object literal");
                    }
                    if (!z) {
                        throw new ParseException("Unexpected comma in object literal");
                    }
                    z = false;
                } else {
                    if (z) {
                        throw new ParseException("Missing comma in object literal");
                    }
                    String string = readString();
                    consume(AbstractJsonLexerKt.COLON);
                    Object value = readValue();
                    ScriptRuntime.StringIdOrIndex stringIdOrIndex = ScriptRuntime.toStringIdOrIndex(string);
                    if (stringIdOrIndex.getStringId() == null) {
                        scriptableNewObject.put(stringIdOrIndex.getIndex(), scriptableNewObject, value);
                    } else {
                        scriptableNewObject.put(stringIdOrIndex.getStringId(), scriptableNewObject, value);
                    }
                    z = true;
                }
                consumeWhitespace();
            } else {
                throw new ParseException("Unterminated object literal");
            }
        }
    }

    private Object readArray() throws ParseException {
        consumeWhitespace();
        int i = this.pos;
        if (i < this.length && this.src.charAt(i) == ']') {
            this.pos++;
            return this.cx.newArray(this.scope, 0);
        }
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        while (true) {
            int i2 = this.pos;
            if (i2 < this.length) {
                char cCharAt = this.src.charAt(i2);
                if (cCharAt != ',') {
                    if (cCharAt == ']') {
                        if (!z) {
                            throw new ParseException("Unexpected comma in array literal");
                        }
                        this.pos++;
                        return this.cx.newArray(this.scope, arrayList.toArray());
                    }
                    if (z) {
                        throw new ParseException("Missing comma in array literal");
                    }
                    arrayList.add(readValue());
                    z = true;
                } else {
                    if (!z) {
                        throw new ParseException("Unexpected comma in array literal");
                    }
                    this.pos++;
                    z = false;
                }
                consumeWhitespace();
            } else {
                throw new ParseException("Unterminated array literal");
            }
        }
    }

    private String readString() throws ParseException {
        char cCharAt;
        int i = this.pos;
        do {
            int i2 = this.pos;
            if (i2 < this.length) {
                String str = this.src;
                this.pos = i2 + 1;
                cCharAt = str.charAt(i2);
                if (cCharAt <= 31) {
                    throw new ParseException("String contains control character");
                }
                if (cCharAt == '\\') {
                }
            }
            StringBuilder sb = new StringBuilder();
            while (true) {
                int i3 = this.pos;
                if (i3 < this.length) {
                    sb.append((CharSequence) this.src, i, i3 - 1);
                    int i4 = this.pos;
                    if (i4 >= this.length) {
                        throw new ParseException("Unterminated string");
                    }
                    String str2 = this.src;
                    this.pos = i4 + 1;
                    char cCharAt2 = str2.charAt(i4);
                    if (cCharAt2 == '\"') {
                        sb.append('\"');
                    } else if (cCharAt2 == '/') {
                        sb.append('/');
                    } else if (cCharAt2 == '\\') {
                        sb.append('\\');
                    } else if (cCharAt2 == 'b') {
                        sb.append('\b');
                    } else if (cCharAt2 == 'f') {
                        sb.append('\f');
                    } else if (cCharAt2 == 'n') {
                        sb.append('\n');
                    } else if (cCharAt2 == 'r') {
                        sb.append('\r');
                    } else if (cCharAt2 == 't') {
                        sb.append('\t');
                    } else if (cCharAt2 == 'u') {
                        int i5 = this.length;
                        int i6 = this.pos;
                        if (i5 - i6 < 5) {
                            throw new ParseException("Invalid character code: \\u" + this.src.substring(this.pos));
                        }
                        int iFromHex = (fromHex(this.src.charAt(i6)) << 12) | (fromHex(this.src.charAt(this.pos + 1)) << 8) | (fromHex(this.src.charAt(this.pos + 2)) << 4) | fromHex(this.src.charAt(this.pos + 3));
                        if (iFromHex < 0) {
                            String str3 = this.src;
                            int i7 = this.pos;
                            throw new ParseException("Invalid character code: " + str3.substring(i7, i7 + 4));
                        }
                        this.pos += 4;
                        sb.append((char) iFromHex);
                    } else {
                        throw new ParseException("Unexpected character in string: '\\" + cCharAt2 + "'");
                    }
                    i = this.pos;
                    while (true) {
                        int i8 = this.pos;
                        if (i8 >= this.length) {
                            break;
                        }
                        String str4 = this.src;
                        this.pos = i8 + 1;
                        char cCharAt3 = str4.charAt(i8);
                        if (cCharAt3 <= 31) {
                            throw new ParseException("String contains control character");
                        }
                        if (cCharAt3 == '\\') {
                            break;
                        }
                        if (cCharAt3 == '\"') {
                            sb.append((CharSequence) this.src, i, this.pos - 1);
                            return sb.toString();
                        }
                    }
                } else {
                    throw new ParseException("Unterminated string literal");
                }
            }
        } while (cCharAt != '\"');
        return this.src.substring(i, this.pos - 1);
    }

    private Number readNumber(char c) throws ParseException {
        char cCharAt;
        int i = this.pos - 1;
        if (c == '-' && ((c = nextOrNumberError(i)) < '0' || c > '9')) {
            throw numberError(i, this.pos);
        }
        if (c != '0') {
            readDigits();
        }
        int i2 = this.pos;
        if (i2 < this.length && this.src.charAt(i2) == '.') {
            this.pos++;
            char cNextOrNumberError = nextOrNumberError(i);
            if (cNextOrNumberError < '0' || cNextOrNumberError > '9') {
                throw numberError(i, this.pos);
            }
            readDigits();
        }
        int i3 = this.pos;
        if (i3 < this.length && ((cCharAt = this.src.charAt(i3)) == 'e' || cCharAt == 'E')) {
            this.pos++;
            char cNextOrNumberError2 = nextOrNumberError(i);
            if (cNextOrNumberError2 == '-' || cNextOrNumberError2 == '+') {
                cNextOrNumberError2 = nextOrNumberError(i);
            }
            if (cNextOrNumberError2 < '0' || cNextOrNumberError2 > '9') {
                throw numberError(i, this.pos);
            }
            readDigits();
        }
        double d = Double.parseDouble(this.src.substring(i, this.pos));
        int i4 = (int) d;
        if (i4 == d) {
            return Integer.valueOf(i4);
        }
        return Double.valueOf(d);
    }

    private ParseException numberError(int i, int i2) {
        return new ParseException("Unsupported number format: " + this.src.substring(i, i2));
    }

    private char nextOrNumberError(int i) throws ParseException {
        int i2 = this.pos;
        int i3 = this.length;
        if (i2 >= i3) {
            throw numberError(i, i3);
        }
        String str = this.src;
        this.pos = i2 + 1;
        return str.charAt(i2);
    }

    private void readDigits() {
        char cCharAt;
        while (true) {
            int i = this.pos;
            if (i >= this.length || (cCharAt = this.src.charAt(i)) < '0' || cCharAt > '9') {
                return;
            } else {
                this.pos++;
            }
        }
    }

    private Boolean readTrue() throws ParseException {
        int i = this.length;
        int i2 = this.pos;
        if (i - i2 < 3 || this.src.charAt(i2) != 'r' || this.src.charAt(this.pos + 1) != 'u' || this.src.charAt(this.pos + 2) != 'e') {
            throw new ParseException("Unexpected token: t");
        }
        this.pos += 3;
        return Boolean.TRUE;
    }

    private Boolean readFalse() throws ParseException {
        int i = this.length;
        int i2 = this.pos;
        if (i - i2 < 4 || this.src.charAt(i2) != 'a' || this.src.charAt(this.pos + 1) != 'l' || this.src.charAt(this.pos + 2) != 's' || this.src.charAt(this.pos + 3) != 'e') {
            throw new ParseException("Unexpected token: f");
        }
        this.pos += 4;
        return Boolean.FALSE;
    }

    private Object readNull() throws ParseException {
        int i = this.length;
        int i2 = this.pos;
        if (i - i2 < 3 || this.src.charAt(i2) != 'u' || this.src.charAt(this.pos + 1) != 'l' || this.src.charAt(this.pos + 2) != 'l') {
            throw new ParseException("Unexpected token: n");
        }
        this.pos += 3;
        return null;
    }

    private void consumeWhitespace() {
        while (true) {
            int i = this.pos;
            if (i >= this.length) {
                return;
            }
            char cCharAt = this.src.charAt(i);
            if (cCharAt != '\t' && cCharAt != '\n' && cCharAt != '\r' && cCharAt != ' ') {
                return;
            } else {
                this.pos++;
            }
        }
    }

    private void consume(char c) throws ParseException {
        consumeWhitespace();
        int i = this.pos;
        if (i >= this.length) {
            throw new ParseException("Expected " + c + " but reached end of stream");
        }
        String str = this.src;
        this.pos = i + 1;
        char cCharAt = str.charAt(i);
        if (cCharAt == c) {
            return;
        }
        throw new ParseException("Expected " + c + " found " + cCharAt);
    }

    public static class ParseException extends Exception {
        private static final long serialVersionUID = 4804542791749920772L;

        ParseException(String str) {
            super(str);
        }

        ParseException(Exception exc) {
            super(exc);
        }
    }
}
