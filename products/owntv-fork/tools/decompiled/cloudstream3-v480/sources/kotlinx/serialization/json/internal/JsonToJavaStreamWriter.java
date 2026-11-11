package kotlinx.serialization.json.internal;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import kotlin.IgnorableReturnValue;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.serialization.json.JsonEncodingException;
import org.mozilla.javascript.ES6Iterator;
import org.schabi.newpipe.extractor.services.peertube.PeertubeParsingHelper;

/* JADX INFO: compiled from: JvmJsonStreams.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0012\b\u0000\u0018\u00002\u00020\u0001B\u0011\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0096\u0080\u0004J\u0012\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0012H\u0096\u0080\u0004J\u0012\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u0015H\u0096\u0080\u0004J\u0012\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u0015H\u0096\u0080\u0004J\u001a\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u0015H\u0082\u0080\u0004J\u001a\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u000bH\u0083\u0080\bJ\n\u0010\u001d\u001a\u00020\rH\u0096\u0080\u0004J\n\u0010\u001e\u001a\u00020\rH\u0082\u0080\u0004J\u0012\u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\u000bH\u0082\u0088\u0004J\u0012\u0010\u0013\u001a\u00020\r2\u0006\u0010!\u001a\u00020\u000bH\u0082\u0088\u0004J\n\u0010\"\u001a\u00020\u000bH\u0082\u0088\u0004J\u001a\u0010#\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\t2\u0006\u0010$\u001a\u00020\u000bH\u0082\u0080\u0004J\u0012\u0010%\u001a\u00020\r2\u0006\u0010&\u001a\u00020\u000bH\u0082\u0080\u0004R\u000f\u0010\u0002\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0006\u001a\u00020\u0007X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\b\u001a\u00020\tX\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\n\u001a\u00020\u000bX\u0082\u008e\b¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lkotlinx/serialization/json/internal/JsonToJavaStreamWriter;", "Lkotlinx/serialization/json/internal/InternalJsonWriter;", "stream", "Ljava/io/OutputStream;", "<init>", "(Ljava/io/OutputStream;)V", "buffer", "", "charArray", "", "indexInBuffer", "", "writeLong", "", ES6Iterator.VALUE_PROPERTY, "", "writeChar", "char", "", "write", "text", "", "writeQuoted", "appendStringSlowPath", "currentSize", TypedValues.Custom.S_STRING, "ensureTotalCapacity", "oldSize", "additional", "release", "flush", "ensure", "bytesCount", "byte", "rest", "writeUtf8", PeertubeParsingHelper.COUNT_KEY, "writeUtf8CodePoint", "codePoint", "kotlinx-serialization-json"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class JsonToJavaStreamWriter implements InternalJsonWriter {
    private final byte[] buffer;
    private char[] charArray;
    private int indexInBuffer;
    private final OutputStream stream;

    public JsonToJavaStreamWriter(OutputStream stream) {
        Intrinsics.checkNotNullParameter(stream, "stream");
        this.stream = stream;
        this.buffer = ByteArrayPool.INSTANCE.take();
        this.charArray = CharArrayPool.INSTANCE.take();
    }

    @Override // kotlinx.serialization.json.internal.InternalJsonWriter
    public void writeLong(long value) throws IOException {
        write(String.valueOf(value));
    }

    @Override // kotlinx.serialization.json.internal.InternalJsonWriter
    public void writeChar(char c) throws IOException {
        writeUtf8CodePoint(c);
    }

    @Override // kotlinx.serialization.json.internal.InternalJsonWriter
    public void write(String text) throws IOException {
        Intrinsics.checkNotNullParameter(text, "text");
        int length = text.length();
        ensureTotalCapacity(0, length);
        text.getChars(0, length, this.charArray, 0);
        writeUtf8(this.charArray, length);
    }

    @Override // kotlinx.serialization.json.internal.InternalJsonWriter
    public void writeQuoted(String text) throws IOException {
        Intrinsics.checkNotNullParameter(text, "text");
        ensureTotalCapacity(0, text.length() + 2);
        char[] cArr = this.charArray;
        cArr[0] = '\"';
        int length = text.length();
        text.getChars(0, length, cArr, 1);
        int i = length + 1;
        for (int i2 = 1; i2 < i; i2++) {
            char c = cArr[i2];
            if (c < StringOpsKt.getESCAPE_MARKERS().length && StringOpsKt.getESCAPE_MARKERS()[c] != 0) {
                appendStringSlowPath(i2, text);
                return;
            }
        }
        cArr[i] = '\"';
        writeUtf8(cArr, length + 2);
        flush();
    }

    private final void appendStringSlowPath(int currentSize, String string) throws IOException {
        byte b;
        int length = string.length();
        for (int i = currentSize - 1; i < length; i++) {
            int iEnsureTotalCapacity = ensureTotalCapacity(currentSize, 2);
            char cCharAt = string.charAt(i);
            if (cCharAt >= StringOpsKt.getESCAPE_MARKERS().length || (b = StringOpsKt.getESCAPE_MARKERS()[cCharAt]) == 0) {
                int i2 = iEnsureTotalCapacity + 1;
                this.charArray[iEnsureTotalCapacity] = cCharAt;
                currentSize = i2;
            } else if (b == 1) {
                String str = StringOpsKt.getESCAPE_STRINGS()[cCharAt];
                Intrinsics.checkNotNull(str);
                int iEnsureTotalCapacity2 = ensureTotalCapacity(iEnsureTotalCapacity, str.length());
                str.getChars(0, str.length(), this.charArray, iEnsureTotalCapacity2);
                currentSize = iEnsureTotalCapacity2 + str.length();
            } else {
                char[] cArr = this.charArray;
                cArr[iEnsureTotalCapacity] = '\\';
                cArr[iEnsureTotalCapacity + 1] = (char) b;
                currentSize = iEnsureTotalCapacity + 2;
            }
        }
        ensureTotalCapacity(currentSize, 1);
        char[] cArr2 = this.charArray;
        cArr2[currentSize] = '\"';
        writeUtf8(cArr2, currentSize + 1);
        flush();
    }

    @IgnorableReturnValue
    private final int ensureTotalCapacity(int oldSize, int additional) {
        int i = additional + oldSize;
        char[] cArr = this.charArray;
        if (cArr.length <= i) {
            char[] cArrCopyOf = Arrays.copyOf(cArr, RangesKt.coerceAtLeast(i, oldSize * 2));
            Intrinsics.checkNotNullExpressionValue(cArrCopyOf, "copyOf(...)");
            this.charArray = cArrCopyOf;
        }
        return oldSize;
    }

    @Override // kotlinx.serialization.json.internal.InternalJsonWriter
    public void release() throws IOException {
        flush();
        CharArrayPool.INSTANCE.release(this.charArray);
        ByteArrayPool.INSTANCE.release(this.buffer);
    }

    private final void flush() throws IOException {
        this.stream.write(this.buffer, 0, this.indexInBuffer);
        this.indexInBuffer = 0;
    }

    private final void write(int i) {
        byte[] bArr = this.buffer;
        int i2 = this.indexInBuffer;
        this.indexInBuffer = i2 + 1;
        bArr[i2] = (byte) i;
    }

    private final int rest() {
        return this.buffer.length - this.indexInBuffer;
    }

    private final void writeUtf8(char[] string, int count) throws IOException {
        if (count < 0) {
            throw new IllegalArgumentException("count < 0".toString());
        }
        if (count > string.length) {
            throw new IllegalArgumentException(("count > string.length: " + count + " > " + string.length).toString());
        }
        int i = 0;
        while (i < count) {
            char c = string[i];
            if (c < 128) {
                if (this.buffer.length - this.indexInBuffer < 1) {
                    flush();
                }
                byte[] bArr = this.buffer;
                int i2 = this.indexInBuffer;
                int i3 = i2 + 1;
                this.indexInBuffer = i3;
                bArr[i2] = (byte) c;
                i++;
                int iMin = Math.min(count, (bArr.length - i3) + i);
                while (i < iMin) {
                    char c2 = string[i];
                    if (c2 >= 128) {
                        break;
                    }
                    byte[] bArr2 = this.buffer;
                    int i4 = this.indexInBuffer;
                    this.indexInBuffer = i4 + 1;
                    bArr2[i4] = (byte) c2;
                    i++;
                }
            } else {
                if (c < 2048) {
                    if (this.buffer.length - this.indexInBuffer < 2) {
                        flush();
                    }
                    byte[] bArr3 = this.buffer;
                    int i5 = this.indexInBuffer;
                    int i6 = i5 + 1;
                    this.indexInBuffer = i6;
                    bArr3[i5] = (byte) ((c >> 6) | 192);
                    this.indexInBuffer = i5 + 2;
                    bArr3[i6] = (byte) ((c & '?') | 128);
                } else if (c >= 55296 && c <= 57343) {
                    int i7 = i + 1;
                    char c3 = i7 < count ? string[i7] : (char) 0;
                    if (c > 56319 || 56320 > c3 || c3 >= 57344) {
                        if (this.buffer.length - this.indexInBuffer < 1) {
                            flush();
                        }
                        byte[] bArr4 = this.buffer;
                        int i8 = this.indexInBuffer;
                        this.indexInBuffer = i8 + 1;
                        bArr4[i8] = 63;
                        i = i7;
                    } else {
                        int i9 = (((c & 1023) << 10) | (c3 & 1023)) + 65536;
                        if (this.buffer.length - this.indexInBuffer < 4) {
                            flush();
                        }
                        byte[] bArr5 = this.buffer;
                        int i10 = this.indexInBuffer;
                        int i11 = i10 + 1;
                        this.indexInBuffer = i11;
                        bArr5[i10] = (byte) ((i9 >> 18) | 240);
                        int i12 = i10 + 2;
                        this.indexInBuffer = i12;
                        bArr5[i11] = (byte) (((i9 >> 12) & 63) | 128);
                        int i13 = i10 + 3;
                        this.indexInBuffer = i13;
                        bArr5[i12] = (byte) (((i9 >> 6) & 63) | 128);
                        this.indexInBuffer = i10 + 4;
                        bArr5[i13] = (byte) ((i9 & 63) | 128);
                        i += 2;
                    }
                } else {
                    if (this.buffer.length - this.indexInBuffer < 3) {
                        flush();
                    }
                    byte[] bArr6 = this.buffer;
                    int i14 = this.indexInBuffer;
                    int i15 = i14 + 1;
                    this.indexInBuffer = i15;
                    bArr6[i14] = (byte) ((c >> '\f') | 224);
                    int i16 = i14 + 2;
                    this.indexInBuffer = i16;
                    bArr6[i15] = (byte) (((c >> 6) & 63) | 128);
                    this.indexInBuffer = i14 + 3;
                    bArr6[i16] = (byte) ((c & '?') | 128);
                }
                i++;
            }
        }
    }

    private final void ensure(int bytesCount) throws IOException {
        if (this.buffer.length - this.indexInBuffer < bytesCount) {
            flush();
        }
    }

    private final void writeUtf8CodePoint(int codePoint) throws IOException {
        if (codePoint < 128) {
            if (this.buffer.length - this.indexInBuffer < 1) {
                flush();
            }
            byte[] bArr = this.buffer;
            int i = this.indexInBuffer;
            this.indexInBuffer = i + 1;
            bArr[i] = (byte) codePoint;
            return;
        }
        if (codePoint < 2048) {
            if (this.buffer.length - this.indexInBuffer < 2) {
                flush();
            }
            byte[] bArr2 = this.buffer;
            int i2 = this.indexInBuffer;
            int i3 = i2 + 1;
            this.indexInBuffer = i3;
            bArr2[i2] = (byte) ((codePoint >> 6) | 192);
            this.indexInBuffer = i2 + 2;
            bArr2[i3] = (byte) ((codePoint & 63) | 128);
            return;
        }
        if (55296 <= codePoint && codePoint < 57344) {
            if (this.buffer.length - this.indexInBuffer < 1) {
                flush();
            }
            byte[] bArr3 = this.buffer;
            int i4 = this.indexInBuffer;
            this.indexInBuffer = i4 + 1;
            bArr3[i4] = 63;
            return;
        }
        if (codePoint < 65536) {
            if (this.buffer.length - this.indexInBuffer < 3) {
                flush();
            }
            byte[] bArr4 = this.buffer;
            int i5 = this.indexInBuffer;
            int i6 = i5 + 1;
            this.indexInBuffer = i6;
            bArr4[i5] = (byte) ((codePoint >> 12) | 224);
            int i7 = i5 + 2;
            this.indexInBuffer = i7;
            bArr4[i6] = (byte) (((codePoint >> 6) & 63) | 128);
            this.indexInBuffer = i5 + 3;
            bArr4[i7] = (byte) ((codePoint & 63) | 128);
            return;
        }
        if (codePoint > 1114111) {
            throw new JsonEncodingException("Unexpected code point: " + codePoint + ". Check your strings for malformed UTF-8 sequences.", null, null, 6, null);
        }
        if (this.buffer.length - this.indexInBuffer < 4) {
            flush();
        }
        byte[] bArr5 = this.buffer;
        int i8 = this.indexInBuffer;
        int i9 = i8 + 1;
        this.indexInBuffer = i9;
        bArr5[i8] = (byte) ((codePoint >> 18) | 240);
        int i10 = i8 + 2;
        this.indexInBuffer = i10;
        bArr5[i9] = (byte) (((codePoint >> 12) & 63) | 128);
        int i11 = i8 + 3;
        this.indexInBuffer = i11;
        bArr5[i10] = (byte) (((codePoint >> 6) & 63) | 128);
        this.indexInBuffer = i8 + 4;
        bArr5[i11] = (byte) ((codePoint & 63) | 128);
    }
}
