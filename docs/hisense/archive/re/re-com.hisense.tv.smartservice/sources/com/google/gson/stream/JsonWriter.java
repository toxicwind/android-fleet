package com.google.gson.stream;

import b.a.a.a.a;
import com.google.firebase.installations.Utils;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class JsonWriter implements Closeable, Flushable {
    public static final String[] HTML_SAFE_REPLACEMENT_CHARS;
    public String deferredName;
    public boolean htmlSafe;
    public String indent;
    public boolean lenient;
    public final Writer out;
    public String separator;
    public boolean serializeNulls;
    public int[] stack = new int[32];
    public int stackSize = 0;
    public static final Pattern VALID_JSON_NUMBER_PATTERN = Pattern.compile("-?(?:0|[1-9][0-9]*)(?:\\.[0-9]+)?(?:[eE][-+]?[0-9]+)?");
    public static final String[] REPLACEMENT_CHARS = new String[128];

    static {
        for (int i = 0; i <= 31; i++) {
            REPLACEMENT_CHARS[i] = String.format("\\u%04x", Integer.valueOf(i));
        }
        String[] strArr = REPLACEMENT_CHARS;
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
        HTML_SAFE_REPLACEMENT_CHARS = (String[]) strArr.clone();
        String[] strArr2 = HTML_SAFE_REPLACEMENT_CHARS;
        strArr2[60] = "\\u003c";
        strArr2[62] = "\\u003e";
        strArr2[38] = "\\u0026";
        strArr2[61] = "\\u003d";
        strArr2[39] = "\\u0027";
    }

    public JsonWriter(Writer writer) {
        push(6);
        this.separator = Utils.APP_ID_IDENTIFICATION_SUBSTRING;
        this.serializeNulls = true;
        if (writer == null) {
            throw new NullPointerException("out == null");
        }
        this.out = writer;
    }

    private void beforeName() throws IOException {
        int iPeek = peek();
        if (iPeek == 5) {
            this.out.write(44);
        } else if (iPeek != 3) {
            throw new IllegalStateException("Nesting problem.");
        }
        newline();
        replaceTop(4);
    }

    private void beforeValue() throws IOException {
        int iPeek = peek();
        if (iPeek == 1) {
            replaceTop(2);
            newline();
            return;
        }
        if (iPeek == 2) {
            this.out.append(',');
            newline();
        } else {
            if (iPeek == 4) {
                this.out.append((CharSequence) this.separator);
                replaceTop(5);
                return;
            }
            if (iPeek != 6) {
                if (iPeek != 7) {
                    throw new IllegalStateException("Nesting problem.");
                }
                if (!this.lenient) {
                    throw new IllegalStateException("JSON must have only one top-level value.");
                }
            }
            replaceTop(7);
        }
    }

    private JsonWriter close(int i, int i2, char c2) throws IOException {
        int iPeek = peek();
        if (iPeek != i2 && iPeek != i) {
            throw new IllegalStateException("Nesting problem.");
        }
        if (this.deferredName != null) {
            StringBuilder sbA = a.a("Dangling name: ");
            sbA.append(this.deferredName);
            throw new IllegalStateException(sbA.toString());
        }
        this.stackSize--;
        if (iPeek == i2) {
            newline();
        }
        this.out.write(c2);
        return this;
    }

    public static boolean isTrustedNumberType(Class<? extends Number> cls) {
        return cls == Integer.class || cls == Long.class || cls == Double.class || cls == Float.class || cls == Byte.class || cls == Short.class || cls == BigDecimal.class || cls == BigInteger.class || cls == AtomicInteger.class || cls == AtomicLong.class;
    }

    private void newline() throws IOException {
        if (this.indent == null) {
            return;
        }
        this.out.write(10);
        int i = this.stackSize;
        for (int i2 = 1; i2 < i; i2++) {
            this.out.write(this.indent);
        }
    }

    private JsonWriter open(int i, char c2) throws IOException {
        beforeValue();
        push(i);
        this.out.write(c2);
        return this;
    }

    private int peek() {
        int i = this.stackSize;
        if (i != 0) {
            return this.stack[i - 1];
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    private void push(int i) {
        int i2 = this.stackSize;
        int[] iArr = this.stack;
        if (i2 == iArr.length) {
            this.stack = Arrays.copyOf(iArr, i2 * 2);
        }
        int[] iArr2 = this.stack;
        int i3 = this.stackSize;
        this.stackSize = i3 + 1;
        iArr2[i3] = i;
    }

    private void replaceTop(int i) {
        this.stack[this.stackSize - 1] = i;
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0034  */
    private void string(String str) throws IOException {
        String str2;
        String[] strArr = this.htmlSafe ? HTML_SAFE_REPLACEMENT_CHARS : REPLACEMENT_CHARS;
        this.out.write(34);
        int length = str.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            char cCharAt = str.charAt(i2);
            if (cCharAt < 128) {
                str2 = strArr[cCharAt];
                if (str2 != null) {
                    if (i < i2) {
                        this.out.write(str, i, i2 - i);
                    }
                    this.out.write(str2);
                    i = i2 + 1;
                }
            } else {
                if (cCharAt == 8232) {
                    str2 = "\\u2028";
                } else if (cCharAt == 8233) {
                    str2 = "\\u2029";
                }
                if (i < i2) {
                    this.out.write(str, i, i2 - i);
                }
                this.out.write(str2);
                i = i2 + 1;
            }
        }
        if (i < length) {
            this.out.write(str, i, length - i);
        }
        this.out.write(34);
    }

    private void writeDeferredName() throws IOException {
        if (this.deferredName != null) {
            beforeName();
            string(this.deferredName);
            this.deferredName = null;
        }
    }

    public JsonWriter beginArray() {
        writeDeferredName();
        open(1, '[');
        return this;
    }

    public JsonWriter beginObject() {
        writeDeferredName();
        open(3, '{');
        return this;
    }

    public JsonWriter endArray() {
        close(1, 2, ']');
        return this;
    }

    public JsonWriter endObject() {
        close(3, 5, '}');
        return this;
    }

    public void flush() throws IOException {
        if (this.stackSize == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.out.flush();
    }

    public final boolean getSerializeNulls() {
        return this.serializeNulls;
    }

    public final boolean isHtmlSafe() {
        return this.htmlSafe;
    }

    public boolean isLenient() {
        return this.lenient;
    }

    public JsonWriter jsonValue(String str) throws IOException {
        if (str == null) {
            return nullValue();
        }
        writeDeferredName();
        beforeValue();
        this.out.append((CharSequence) str);
        return this;
    }

    public JsonWriter name(String str) {
        if (str == null) {
            throw new NullPointerException("name == null");
        }
        if (this.deferredName != null) {
            throw new IllegalStateException();
        }
        if (this.stackSize == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.deferredName = str;
        return this;
    }

    public JsonWriter nullValue() {
        if (this.deferredName != null) {
            if (!this.serializeNulls) {
                this.deferredName = null;
                return this;
            }
            writeDeferredName();
        }
        beforeValue();
        this.out.write("null");
        return this;
    }

    public final void setHtmlSafe(boolean z) {
        this.htmlSafe = z;
    }

    public final void setIndent(String str) {
        if (str.length() == 0) {
            this.indent = null;
            this.separator = Utils.APP_ID_IDENTIFICATION_SUBSTRING;
        } else {
            this.indent = str;
            this.separator = ": ";
        }
    }

    public final void setLenient(boolean z) {
        this.lenient = z;
    }

    public final void setSerializeNulls(boolean z) {
        this.serializeNulls = z;
    }

    public JsonWriter value(String str) {
        if (str == null) {
            return nullValue();
        }
        writeDeferredName();
        beforeValue();
        string(str);
        return this;
    }

    public JsonWriter value(boolean z) {
        writeDeferredName();
        beforeValue();
        this.out.write(z ? "true" : "false");
        return this;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.out.close();
        int i = this.stackSize;
        if (i <= 1 && (i != 1 || this.stack[i - 1] == 7)) {
            this.stackSize = 0;
            return;
        }
        throw new IOException("Incomplete document");
    }

    public JsonWriter value(Boolean bool) {
        if (bool == null) {
            return nullValue();
        }
        writeDeferredName();
        beforeValue();
        this.out.write(bool.booleanValue() ? "true" : "false");
        return this;
    }

    public JsonWriter value(double d2) throws IOException {
        writeDeferredName();
        if (!this.lenient && (Double.isNaN(d2) || Double.isInfinite(d2))) {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + d2);
        }
        beforeValue();
        this.out.append((CharSequence) Double.toString(d2));
        return this;
    }

    public JsonWriter value(long j) {
        writeDeferredName();
        beforeValue();
        this.out.write(Long.toString(j));
        return this;
    }

    public JsonWriter value(Number number) {
        if (number == null) {
            return nullValue();
        }
        writeDeferredName();
        String string = number.toString();
        if (!string.equals("-Infinity") && !string.equals("Infinity") && !string.equals("NaN")) {
            Class<?> cls = number.getClass();
            if (!isTrustedNumberType(cls) && !VALID_JSON_NUMBER_PATTERN.matcher(string).matches()) {
                throw new IllegalArgumentException("String created by " + cls + " is not a valid JSON number: " + string);
            }
        } else if (!this.lenient) {
            throw new IllegalArgumentException(a.a("Numeric values must be finite, but was ", string));
        }
        beforeValue();
        this.out.append((CharSequence) string);
        return this;
    }
}
