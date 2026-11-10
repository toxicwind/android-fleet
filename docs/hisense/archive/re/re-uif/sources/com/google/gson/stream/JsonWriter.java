package com.google.gson.stream;

import java.io.Closeable;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class JsonWriter implements Closeable {
    private String deferredName;
    private boolean htmlSafe;
    private String indent;
    private boolean lenient;
    private final Writer out;
    private String separator;
    private boolean serializeNulls;
    private final List<JsonScope> stack;

    public JsonWriter(Writer writer) {
        ArrayList arrayList = new ArrayList();
        this.stack = arrayList;
        arrayList.add(JsonScope.EMPTY_DOCUMENT);
        this.separator = ":";
        this.serializeNulls = true;
        if (writer == null) {
            throw new NullPointerException("out == null");
        }
        this.out = writer;
    }

    public final void setLenient(boolean z) {
        this.lenient = z;
    }

    public boolean isLenient() {
        return this.lenient;
    }

    public final boolean getSerializeNulls() {
        return this.serializeNulls;
    }

    public JsonWriter beginArray() throws IOException {
        writeDeferredName();
        open(JsonScope.EMPTY_ARRAY, "[");
        return this;
    }

    public JsonWriter endArray() throws IOException {
        close(JsonScope.EMPTY_ARRAY, JsonScope.NONEMPTY_ARRAY, "]");
        return this;
    }

    public JsonWriter beginObject() throws IOException {
        writeDeferredName();
        open(JsonScope.EMPTY_OBJECT, "{");
        return this;
    }

    public JsonWriter endObject() throws IOException {
        close(JsonScope.EMPTY_OBJECT, JsonScope.NONEMPTY_OBJECT, "}");
        return this;
    }

    private JsonWriter open(JsonScope jsonScope, String str) throws IOException {
        beforeValue(true);
        this.stack.add(jsonScope);
        this.out.write(str);
        return this;
    }

    private JsonWriter close(JsonScope jsonScope, JsonScope jsonScope2, String str) throws IOException {
        JsonScope jsonScopePeek = peek();
        if (jsonScopePeek != jsonScope2 && jsonScopePeek != jsonScope) {
            throw new IllegalStateException("Nesting problem: " + this.stack);
        }
        if (this.deferredName != null) {
            throw new IllegalStateException("Dangling name: " + this.deferredName);
        }
        List<JsonScope> list = this.stack;
        list.remove(list.size() - 1);
        if (jsonScopePeek == jsonScope2) {
            newline();
        }
        this.out.write(str);
        return this;
    }

    private JsonScope peek() {
        List<JsonScope> list = this.stack;
        return list.get(list.size() - 1);
    }

    private void replaceTop(JsonScope jsonScope) {
        List<JsonScope> list = this.stack;
        list.set(list.size() - 1, jsonScope);
    }

    public JsonWriter name(String str) throws IOException {
        if (str == null) {
            throw new NullPointerException("name == null");
        }
        if (this.deferredName != null) {
            throw new IllegalStateException();
        }
        this.deferredName = str;
        return this;
    }

    private void writeDeferredName() throws IOException {
        if (this.deferredName != null) {
            beforeName();
            string(this.deferredName);
            this.deferredName = null;
        }
    }

    public JsonWriter value(String str) throws IOException {
        if (str == null) {
            return nullValue();
        }
        writeDeferredName();
        beforeValue(false);
        string(str);
        return this;
    }

    public JsonWriter nullValue() throws IOException {
        if (this.deferredName != null) {
            if (this.serializeNulls) {
                writeDeferredName();
            } else {
                this.deferredName = null;
                return this;
            }
        }
        beforeValue(false);
        this.out.write("null");
        return this;
    }

    public JsonWriter value(boolean z) throws IOException {
        writeDeferredName();
        beforeValue(false);
        this.out.write(z ? "true" : "false");
        return this;
    }

    public JsonWriter value(long j) throws IOException {
        writeDeferredName();
        beforeValue(false);
        this.out.write(Long.toString(j));
        return this;
    }

    public JsonWriter value(Number number) throws IOException {
        if (number == null) {
            return nullValue();
        }
        writeDeferredName();
        String string = number.toString();
        if (!this.lenient && (string.equals("-Infinity") || string.equals("Infinity") || string.equals("NaN"))) {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + number);
        }
        beforeValue(false);
        this.out.append((CharSequence) string);
        return this;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.out.close();
        if (peek() != JsonScope.NONEMPTY_DOCUMENT) {
            throw new IOException("Incomplete document");
        }
    }

    /* JADX WARN: Code duplicated, block: B:29:0x0082  */
    /* JADX WARN: Code duplicated, block: B:31:0x0086  */
    /* JADX WARN: Code duplicated, block: B:32:0x0098  */
    /* JADX WARN: Switch 'out' block B:29:0x0082 for B:19:0x0036 already processed. Defaulting to fallback option. */
    private void string(String str) throws IOException {
        this.out.write("\"");
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '\f') {
                this.out.write("\\f");
            } else if (cCharAt == '\r') {
                this.out.write("\\r");
            } else if (cCharAt == '\"' || cCharAt == '\\') {
                this.out.write(92);
                this.out.write(cCharAt);
            } else if (cCharAt == '&' || cCharAt == '\'') {
                if (this.htmlSafe) {
                    this.out.write(String.format("\\u%04x", Integer.valueOf(cCharAt)));
                } else {
                    this.out.write(cCharAt);
                }
            } else if (cCharAt != 8232 && cCharAt != 8233) {
                switch (cCharAt) {
                    case '\b':
                        this.out.write("\\b");
                        continue;
                    case '\t':
                        this.out.write("\\t");
                        continue;
                    case '\n':
                        this.out.write("\\n");
                        continue;
                    default:
                        switch (cCharAt) {
                            case '<':
                            case '=':
                            case '>':
                                if (this.htmlSafe) {
                                    this.out.write(String.format("\\u%04x", Integer.valueOf(cCharAt)));
                                } else {
                                    this.out.write(cCharAt);
                                }
                                break;
                            default:
                                if (cCharAt <= 31) {
                                    this.out.write(String.format("\\u%04x", Integer.valueOf(cCharAt)));
                                    continue;
                                } else {
                                    this.out.write(cCharAt);
                                }
                                break;
                        }
                        break;
                }
            } else {
                this.out.write(String.format("\\u%04x", Integer.valueOf(cCharAt)));
            }
        }
        this.out.write("\"");
    }

    private void newline() throws IOException {
        if (this.indent == null) {
            return;
        }
        this.out.write("\n");
        for (int i = 1; i < this.stack.size(); i++) {
            this.out.write(this.indent);
        }
    }

    private void beforeName() throws IOException {
        JsonScope jsonScopePeek = peek();
        if (jsonScopePeek == JsonScope.NONEMPTY_OBJECT) {
            this.out.write(44);
        } else if (jsonScopePeek != JsonScope.EMPTY_OBJECT) {
            throw new IllegalStateException("Nesting problem: " + this.stack);
        }
        newline();
        replaceTop(JsonScope.DANGLING_NAME);
    }

    /* JADX INFO: renamed from: com.google.gson.stream.JsonWriter$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
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
                $SwitchMap$com$google$gson$stream$JsonScope[JsonScope.DANGLING_NAME.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonScope[JsonScope.NONEMPTY_DOCUMENT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private void beforeValue(boolean z) throws IOException {
        int i = AnonymousClass1.$SwitchMap$com$google$gson$stream$JsonScope[peek().ordinal()];
        if (i == 1) {
            if (!this.lenient && !z) {
                throw new IllegalStateException("JSON must start with an array or an object.");
            }
            replaceTop(JsonScope.NONEMPTY_DOCUMENT);
            return;
        }
        if (i == 2) {
            replaceTop(JsonScope.NONEMPTY_ARRAY);
            newline();
            return;
        }
        if (i == 3) {
            this.out.append(',');
            newline();
        } else if (i == 4) {
            this.out.append((CharSequence) this.separator);
            replaceTop(JsonScope.NONEMPTY_OBJECT);
        } else {
            if (i == 5) {
                throw new IllegalStateException("JSON must have only one top-level value.");
            }
            throw new IllegalStateException("Nesting problem: " + this.stack);
        }
    }
}
