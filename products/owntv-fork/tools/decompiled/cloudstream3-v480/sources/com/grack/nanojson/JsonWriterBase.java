package com.grack.nanojson;

import com.grack.nanojson.JsonWriterBase;
import io.ktor.util.date.GMTDateParser;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.BitSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: classes6.dex */
public class JsonWriterBase<SELF extends JsonWriterBase<SELF>> implements JsonSink<SELF> {
    static final int BUFFER_SIZE = 10240;
    protected final Appendable appendable;
    private final byte[] bb;
    private int bo;
    private final StringBuilder buffer;
    private boolean first;
    private boolean inObject;
    private int indent;
    private String indentString;
    protected final OutputStream out;
    private int stateIndex;
    private BitSet states;
    protected final boolean utf8;
    private static final char[] NULL = {'n', AbstractJsonLexerKt.UNICODE_ESC, 'l', 'l'};
    private static final char[] TRUE = {'t', 'r', AbstractJsonLexerKt.UNICODE_ESC, 'e'};
    private static final char[] FALSE = {'f', 'a', 'l', GMTDateParser.SECONDS, 'e'};
    private static final char[] HEX = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', GMTDateParser.DAY_OF_MONTH, 'e', 'f'};
    private static final char[] UNICODE_SMALL = {'\\', AbstractJsonLexerKt.UNICODE_ESC, '0', '0'};
    private static final char[] UNICODE_LARGE = {'\\', AbstractJsonLexerKt.UNICODE_ESC};

    JsonWriterBase(OutputStream outputStream, String str) {
        this.bo = 0;
        this.states = new BitSet();
        this.stateIndex = 0;
        this.first = true;
        this.indent = 0;
        this.appendable = null;
        this.out = outputStream;
        this.indentString = str;
        this.utf8 = true;
        this.buffer = null;
        this.bb = new byte[10240];
    }

    JsonWriterBase(Appendable appendable, String str) {
        this.bo = 0;
        this.states = new BitSet();
        this.stateIndex = 0;
        this.first = true;
        this.indent = 0;
        this.appendable = appendable;
        this.out = null;
        this.indentString = str;
        this.utf8 = false;
        this.buffer = new StringBuilder(10240);
        this.bb = null;
    }

    private void appendIndent() {
        for (int i = 0; i < this.indent; i++) {
            raw(this.indentString);
        }
    }

    private void appendNewLine() {
        raw('\n');
    }

    private SELF castThis() {
        return this;
    }

    /* JADX WARN: Code duplicated, block: B:50:0x016f  */
    private void emitStringValue(String str) {
        String str2;
        char c;
        raw('\"');
        int i = 0;
        char c2 = 0;
        while (i < str.length()) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '\f') {
                str2 = "\\f";
            } else if (cCharAt != '\r') {
                if (cCharAt == '\"') {
                    raw('\\');
                    raw(cCharAt);
                } else if (cCharAt != '/') {
                    if (cCharAt != '\\') {
                        switch (cCharAt) {
                            case '\b':
                                str2 = "\\b";
                                break;
                            case '\t':
                                str2 = "\\t";
                                break;
                            case '\n':
                                str2 = "\\n";
                                break;
                            default:
                                if (shouldBeEscaped(cCharAt)) {
                                    if (cCharAt < 256) {
                                        raw(UNICODE_SMALL);
                                        char[] cArr = HEX;
                                        raw(cArr[(cCharAt >> 4) & 15]);
                                        c = cArr[cCharAt & 15];
                                    } else {
                                        raw(UNICODE_LARGE);
                                        char[] cArr2 = HEX;
                                        raw(cArr2[(cCharAt >> '\f') & 15]);
                                        raw(cArr2[(cCharAt >> '\b') & 15]);
                                        raw(cArr2[(cCharAt >> 4) & 15]);
                                        c = cArr2[cCharAt & 15];
                                    }
                                    raw(c);
                                } else if (this.utf8) {
                                    if (this.bo + 4 > 10240) {
                                        flush();
                                    }
                                    if (cCharAt < 128) {
                                        byte[] bArr = this.bb;
                                        int i2 = this.bo;
                                        this.bo = i2 + 1;
                                        bArr[i2] = (byte) cCharAt;
                                    } else if (cCharAt < 2048) {
                                        byte[] bArr2 = this.bb;
                                        int i3 = this.bo;
                                        int i4 = i3 + 1;
                                        this.bo = i4;
                                        bArr2[i3] = (byte) ((cCharAt >> 6) | 192);
                                        this.bo = i3 + 2;
                                        bArr2[i4] = (byte) (128 | (cCharAt & '?'));
                                    } else if (cCharAt < 55296) {
                                        byte[] bArr3 = this.bb;
                                        int i5 = this.bo;
                                        int i6 = i5 + 1;
                                        this.bo = i6;
                                        bArr3[i5] = (byte) ((cCharAt >> '\f') | 224);
                                        int i7 = i5 + 2;
                                        this.bo = i7;
                                        bArr3[i6] = (byte) (((cCharAt >> 6) & 63) | 128);
                                        this.bo = i5 + 3;
                                        bArr3[i7] = (byte) (128 | (cCharAt & '?'));
                                    } else if (cCharAt >= 57343) {
                                        byte[] bArr4 = this.bb;
                                        int i8 = this.bo;
                                        int i9 = i8 + 1;
                                        this.bo = i9;
                                        bArr4[i8] = (byte) ((cCharAt >> '\f') | 224);
                                        int i10 = i8 + 2;
                                        this.bo = i10;
                                        bArr4[i9] = (byte) (((cCharAt >> 6) & 63) | 128);
                                        this.bo = i8 + 3;
                                        bArr4[i10] = (byte) (128 | (cCharAt & '?'));
                                    } else {
                                        i++;
                                        int codePoint = Character.toCodePoint(cCharAt, str.charAt(i));
                                        if (codePoint >= 2097151) {
                                            throw new JsonWriterException("Unable to encode character 0x" + Integer.toHexString(codePoint));
                                        }
                                        byte[] bArr5 = this.bb;
                                        int i11 = this.bo;
                                        int i12 = i11 + 1;
                                        this.bo = i12;
                                        bArr5[i11] = (byte) ((codePoint >> 18) | 240);
                                        int i13 = i11 + 2;
                                        this.bo = i13;
                                        bArr5[i12] = (byte) (((codePoint >> 12) & 63) | 128);
                                        int i14 = i11 + 3;
                                        this.bo = i14;
                                        bArr5[i13] = (byte) (((codePoint >> 6) & 63) | 128);
                                        this.bo = i11 + 4;
                                        bArr5[i14] = (byte) (128 | (codePoint & 63));
                                    }
                                }
                                break;
                        }
                    } else {
                        raw('\\');
                    }
                    raw(cCharAt);
                } else {
                    if (c2 == '<') {
                        raw('\\');
                    }
                    raw(cCharAt);
                }
                i++;
                c2 = cCharAt;
            } else {
                str2 = "\\r";
            }
            raw(str2);
            i++;
            c2 = cCharAt;
        }
        raw('\"');
    }

    private void flush() {
        try {
            if (this.utf8) {
                this.out.write(this.bb, 0, this.bo);
                this.bo = 0;
            } else {
                this.appendable.append(this.buffer.toString());
                this.buffer.setLength(0);
            }
        } catch (IOException e) {
            throw new JsonWriterException(e);
        }
    }

    private void pre() {
        if (this.first) {
            this.first = false;
            return;
        }
        if (this.stateIndex == 0) {
            throw new JsonWriterException("Invalid call to emit a value in a finished JSON writer");
        }
        raw(AbstractJsonLexerKt.COMMA);
        if (this.indentString == null || !this.inObject) {
            return;
        }
        appendNewLine();
    }

    private void preValue() {
        if (this.inObject) {
            throw new JsonWriterException("Invalid call to emit a keyless value while writing an object");
        }
        pre();
    }

    private void preValue(String str) {
        if (!this.inObject) {
            throw new JsonWriterException("Invalid call to emit a key value while not writing an object");
        }
        pre();
        if (this.indentString != null) {
            appendIndent();
        }
        emitStringValue(str);
        raw(AbstractJsonLexerKt.COLON);
    }

    private void raw(char c) {
        if (!this.utf8) {
            this.buffer.append(c);
            if (this.buffer.length() > 10240) {
                flush();
                return;
            }
            return;
        }
        if (this.bo + 1 > 10240) {
            flush();
        }
        byte[] bArr = this.bb;
        int i = this.bo;
        this.bo = i + 1;
        bArr[i] = (byte) c;
    }

    private void raw(String str) {
        if (!this.utf8) {
            this.buffer.append(str);
            if (this.buffer.length() > 10240) {
                flush();
                return;
            }
            return;
        }
        int length = str.length();
        if (this.bo + length > 10240) {
            flush();
        }
        for (int i = 0; i < length; i++) {
            byte[] bArr = this.bb;
            int i2 = this.bo;
            this.bo = i2 + 1;
            bArr[i2] = (byte) str.charAt(i);
        }
    }

    private void raw(char[] cArr) {
        if (!this.utf8) {
            this.buffer.append(cArr);
            if (this.buffer.length() > 10240) {
                flush();
                return;
            }
            return;
        }
        if (this.bo + cArr.length > 10240) {
            flush();
        }
        for (char c : cArr) {
            byte[] bArr = this.bb;
            int i = this.bo;
            this.bo = i + 1;
            bArr[i] = (byte) c;
        }
    }

    private boolean shouldBeEscaped(char c) {
        if (c < ' ') {
            return true;
        }
        if (c < 128 || c >= 160) {
            return c >= 8192 && c < 8448;
        }
        return true;
    }

    @Override // com.grack.nanojson.JsonSink
    public /* bridge */ /* synthetic */ JsonSink array(String str, Collection collection) {
        return array(str, (Collection<?>) collection);
    }

    @Override // com.grack.nanojson.JsonSink
    public /* bridge */ /* synthetic */ JsonSink array(Collection collection) {
        return array((Collection<?>) collection);
    }

    @Override // com.grack.nanojson.JsonSink
    public SELF array() {
        preValue();
        BitSet bitSet = this.states;
        int i = this.stateIndex;
        this.stateIndex = i + 1;
        bitSet.set(i, this.inObject);
        this.inObject = false;
        this.first = true;
        raw(AbstractJsonLexerKt.BEGIN_LIST);
        return (SELF) castThis();
    }

    @Override // com.grack.nanojson.JsonSink
    public SELF array(String str) {
        preValue(str);
        BitSet bitSet = this.states;
        int i = this.stateIndex;
        this.stateIndex = i + 1;
        bitSet.set(i, this.inObject);
        this.inObject = false;
        this.first = true;
        raw(AbstractJsonLexerKt.BEGIN_LIST);
        return (SELF) castThis();
    }

    @Override // com.grack.nanojson.JsonSink
    public SELF array(String str, Collection<?> collection) {
        if (str == null) {
            array();
        } else {
            array(str);
        }
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            value(it.next());
        }
        return (SELF) end();
    }

    @Override // com.grack.nanojson.JsonSink
    public SELF array(Collection<?> collection) {
        return (SELF) array((String) null, collection);
    }

    protected void doneInternal() {
        if (this.stateIndex > 0) {
            throw new JsonWriterException("Unclosed JSON objects and/or arrays when closing writer");
        }
        if (this.first) {
            throw new JsonWriterException("Nothing was written to the JSON writer");
        }
        flush();
    }

    @Override // com.grack.nanojson.JsonSink
    public SELF end() {
        char c;
        if (this.stateIndex == 0) {
            throw new JsonWriterException("Invalid call to end()");
        }
        if (this.inObject) {
            if (this.indentString != null) {
                this.indent--;
                appendNewLine();
                appendIndent();
            }
            c = AbstractJsonLexerKt.END_OBJ;
        } else {
            c = AbstractJsonLexerKt.END_LIST;
        }
        raw(c);
        this.first = false;
        BitSet bitSet = this.states;
        int i = this.stateIndex - 1;
        this.stateIndex = i;
        this.inObject = bitSet.get(i);
        return (SELF) castThis();
    }

    @Override // com.grack.nanojson.JsonSink
    public SELF nul() {
        preValue();
        raw(NULL);
        return (SELF) castThis();
    }

    @Override // com.grack.nanojson.JsonSink
    public SELF nul(String str) {
        preValue(str);
        raw(NULL);
        return (SELF) castThis();
    }

    @Override // com.grack.nanojson.JsonSink
    public /* bridge */ /* synthetic */ JsonSink object(String str, Map map) {
        return object(str, (Map<?, ?>) map);
    }

    @Override // com.grack.nanojson.JsonSink
    public /* bridge */ /* synthetic */ JsonSink object(Map map) {
        return object((Map<?, ?>) map);
    }

    @Override // com.grack.nanojson.JsonSink
    public SELF object() {
        preValue();
        BitSet bitSet = this.states;
        int i = this.stateIndex;
        this.stateIndex = i + 1;
        bitSet.set(i, this.inObject);
        this.inObject = true;
        this.first = true;
        raw(AbstractJsonLexerKt.BEGIN_OBJ);
        if (this.indentString != null) {
            this.indent++;
            appendNewLine();
        }
        return (SELF) castThis();
    }

    @Override // com.grack.nanojson.JsonSink
    public SELF object(String str) {
        preValue(str);
        BitSet bitSet = this.states;
        int i = this.stateIndex;
        this.stateIndex = i + 1;
        bitSet.set(i, this.inObject);
        this.inObject = true;
        this.first = true;
        raw(AbstractJsonLexerKt.BEGIN_OBJ);
        if (this.indentString != null) {
            this.indent++;
            appendNewLine();
        }
        return (SELF) castThis();
    }

    @Override // com.grack.nanojson.JsonSink
    public SELF object(String str, Map<?, ?> map) {
        if (str == null) {
            object();
        } else {
            object(str);
        }
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            Object value = entry.getValue();
            if (!(entry.getKey() instanceof String)) {
                StringBuilder sb = new StringBuilder("Invalid key type for map: ");
                sb.append(entry.getKey() == null ? AbstractJsonLexerKt.NULL : entry.getKey().getClass());
                throw new JsonWriterException(sb.toString());
            }
            value((String) entry.getKey(), value);
        }
        return (SELF) end();
    }

    @Override // com.grack.nanojson.JsonSink
    public SELF object(Map<?, ?> map) {
        return (SELF) object((String) null, map);
    }

    @Override // com.grack.nanojson.JsonSink
    public SELF value(double d) {
        preValue();
        raw(Double.toString(d));
        return (SELF) castThis();
    }

    @Override // com.grack.nanojson.JsonSink
    public SELF value(float f) {
        preValue();
        raw(Float.toString(f));
        return (SELF) castThis();
    }

    @Override // com.grack.nanojson.JsonSink
    public SELF value(int i) {
        preValue();
        raw(Integer.toString(i));
        return (SELF) castThis();
    }

    @Override // com.grack.nanojson.JsonSink
    public SELF value(long j) {
        preValue();
        raw(Long.toString(j));
        return (SELF) castThis();
    }

    @Override // com.grack.nanojson.JsonSink
    public SELF value(Number number) {
        preValue();
        if (number == null) {
            raw(NULL);
        } else {
            raw(number.toString());
        }
        return (SELF) castThis();
    }

    @Override // com.grack.nanojson.JsonSink
    public SELF value(Object obj) {
        if (obj == null) {
            return (SELF) nul();
        }
        if (obj instanceof String) {
            return (SELF) value((String) obj);
        }
        if (obj instanceof Number) {
            return (SELF) value((Number) obj);
        }
        if (obj instanceof Boolean) {
            return (SELF) value(((Boolean) obj).booleanValue());
        }
        if (obj instanceof Collection) {
            return (SELF) array((Collection<?>) obj);
        }
        if (obj instanceof Map) {
            return (SELF) object((Map<?, ?>) obj);
        }
        if (!obj.getClass().isArray()) {
            throw new JsonWriterException("Unable to handle type: " + obj.getClass());
        }
        int length = Array.getLength(obj);
        array();
        for (int i = 0; i < length; i++) {
            value(Array.get(obj, i));
        }
        return (SELF) end();
    }

    @Override // com.grack.nanojson.JsonSink
    public SELF value(String str) {
        if (str == null) {
            return (SELF) nul();
        }
        preValue();
        emitStringValue(str);
        return (SELF) castThis();
    }

    @Override // com.grack.nanojson.JsonSink
    public SELF value(String str, double d) {
        preValue(str);
        raw(Double.toString(d));
        return (SELF) castThis();
    }

    @Override // com.grack.nanojson.JsonSink
    public SELF value(String str, float f) {
        preValue(str);
        raw(Float.toString(f));
        return (SELF) castThis();
    }

    @Override // com.grack.nanojson.JsonSink
    public SELF value(String str, int i) {
        preValue(str);
        raw(Integer.toString(i));
        return (SELF) castThis();
    }

    @Override // com.grack.nanojson.JsonSink
    public SELF value(String str, long j) {
        preValue(str);
        raw(Long.toString(j));
        return (SELF) castThis();
    }

    @Override // com.grack.nanojson.JsonSink
    public SELF value(String str, Number number) {
        if (number == null) {
            return (SELF) nul(str);
        }
        preValue(str);
        raw(number.toString());
        return (SELF) castThis();
    }

    @Override // com.grack.nanojson.JsonSink
    public SELF value(String str, Object obj) {
        if (obj == null) {
            return (SELF) nul(str);
        }
        if (obj instanceof String) {
            return (SELF) value(str, (String) obj);
        }
        if (obj instanceof Number) {
            return (SELF) value(str, (Number) obj);
        }
        if (obj instanceof Boolean) {
            return (SELF) value(str, ((Boolean) obj).booleanValue());
        }
        if (obj instanceof Collection) {
            return (SELF) array(str, (Collection<?>) obj);
        }
        if (obj instanceof Map) {
            return (SELF) object(str, (Map<?, ?>) obj);
        }
        if (!obj.getClass().isArray()) {
            throw new JsonWriterException("Unable to handle type: " + obj.getClass());
        }
        int length = Array.getLength(obj);
        array(str);
        for (int i = 0; i < length; i++) {
            value(Array.get(obj, i));
        }
        return (SELF) end();
    }

    @Override // com.grack.nanojson.JsonSink
    public SELF value(String str, String str2) {
        if (str2 == null) {
            return (SELF) nul(str);
        }
        preValue(str);
        emitStringValue(str2);
        return (SELF) castThis();
    }

    @Override // com.grack.nanojson.JsonSink
    public SELF value(String str, boolean z) {
        preValue(str);
        raw(z ? TRUE : FALSE);
        return (SELF) castThis();
    }

    @Override // com.grack.nanojson.JsonSink
    public SELF value(boolean z) {
        preValue();
        raw(z ? TRUE : FALSE);
        return (SELF) castThis();
    }
}
