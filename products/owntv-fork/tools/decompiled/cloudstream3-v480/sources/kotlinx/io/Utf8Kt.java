package kotlinx.io;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import com.google.common.base.Ascii;
import java.io.EOFException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.io.internal._Utf8Kt;
import kotlinx.io.unsafe.SegmentReadContext;
import kotlinx.io.unsafe.SegmentWriteContext;
import kotlinx.io.unsafe.UnsafeBufferOperations;
import kotlinx.io.unsafe.UnsafeBufferOperationsKt;

/* JADX INFO: compiled from: Utf8.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0010\t\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\b\u0003\u001a\"\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0004H\u0080\u0080\u0004\u001a\u0016\u0010\u0006\u001a\u00020\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\u0004H\u0086\u0080\u0004\u001a*\u0010\n\u001a\u00020\u0007*\u00020\b2\u0006\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0004H\u0086\u0080\u0004\u001a*\u0010\n\u001a\u00020\u0007*\u00020\b2\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0004H\u0086\u0080\u0004\u001a\u000e\u0010\u000e\u001a\u00020\u0002*\u00020\u000fH\u0086\u0080\u0004\u001a\u000e\u0010\u000e\u001a\u00020\u0002*\u00020\u0010H\u0086\u0080\u0004\u001a\u0016\u0010\u000e\u001a\u00020\u0002*\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0001H\u0086\u0080\u0004\u001a\u000e\u0010\u0012\u001a\u00020\u0004*\u00020\u000fH\u0086\u0080\u0004\u001a\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0002*\u00020\u000fH\u0086\u0080\u0004\u001a\u0018\u0010\u0014\u001a\u00020\u0002*\u00020\u000f2\b\b\u0002\u0010\u0015\u001a\u00020\u0001H\u0086\u0080\u0004\u001a\u000e\u0010\u0016\u001a\u00020\u0004*\u00020\u0010H\u0082\u0080\u0004\u001a2\u0010\u0017\u001a\u00020\u0007*\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u001b0\u001aH\u0082\u0088\u0004\u001a\u0016\u0010\u001c\u001a\u00020\u0007*\u00020\u00102\u0006\u0010\t\u001a\u00020\u0004H\u0082\u0080\u0004\u001a\u0016\u0010\u001d\u001a\u00020\u0002*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0001H\u0082\u0080\u0004¨\u0006\u001e"}, d2 = {"utf8Size", "", "", "startIndex", "", "endIndex", "writeCodePointValue", "", "Lkotlinx/io/Sink;", "codePoint", "writeString", TypedValues.Custom.S_STRING, "chars", "", "readString", "Lkotlinx/io/Source;", "Lkotlinx/io/Buffer;", "byteCount", "readCodePointValue", "readLine", "readLineStrict", "limit", "commonReadUtf8CodePoint", "commonWriteUtf8", "beginIndex", "charAt", "Lkotlin/Function1;", "", "commonWriteUtf8CodePoint", "commonReadUtf8", "kotlinx-io-core"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class Utf8Kt {
    public static /* synthetic */ long utf8Size$default(String str, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        return utf8Size(str, i, i2);
    }

    public static final long utf8Size(String str, int i, int i2) {
        long j;
        Intrinsics.checkNotNullParameter(str, "<this>");
        _UtilKt.checkBounds(str.length(), i, i2);
        long j2 = 0;
        while (i < i2) {
            char cCharAt = str.charAt(i);
            if (cCharAt < 128) {
                j2++;
            } else {
                if (cCharAt < 2048) {
                    j = 2;
                } else if (cCharAt < 55296 || cCharAt > 57343) {
                    j = 3;
                } else {
                    int i3 = i + 1;
                    char cCharAt2 = i3 < i2 ? str.charAt(i3) : (char) 0;
                    if (cCharAt > 56319 || cCharAt2 < 56320 || cCharAt2 > 57343) {
                        j2++;
                        i = i3;
                    } else {
                        j2 += 4;
                        i += 2;
                    }
                }
                j2 += j;
            }
            i++;
        }
        return j2;
    }

    public static /* synthetic */ void writeString$default(Sink sink, String str, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = str.length();
        }
        writeString(sink, str, i, i2);
    }

    public static final void writeString(Sink sink, String string, int i, int i2) {
        long sizeMut;
        long j;
        Intrinsics.checkNotNullParameter(sink, "<this>");
        Intrinsics.checkNotNullParameter(string, "string");
        int i3 = i;
        _UtilKt.checkBounds(string.length(), i3, i2);
        Buffer bufferField = sink.getBufferField();
        while (i3 < i2) {
            Ref.IntRef intRef = new Ref.IntRef();
            char cCharAt = string.charAt(i3);
            Character.valueOf(cCharAt).getClass();
            intRef.element = cCharAt;
            if (intRef.element < 128) {
                UnsafeBufferOperations unsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
                Segment segmentWritableSegment = bufferField.writableSegment(1);
                SegmentWriteContext segmentWriteContext = UnsafeBufferOperationsKt.SegmentWriteContextImpl;
                int i4 = -i3;
                int iMin = Math.min(i2, segmentWritableSegment.getRemainingCapacity() + i3);
                int i5 = i3 + 1;
                segmentWriteContext.setUnchecked(segmentWritableSegment, i3 + i4, (byte) intRef.element);
                while (true) {
                    i3 = i5;
                    if (i3 >= iMin) {
                        break;
                    }
                    char cCharAt2 = string.charAt(i3);
                    Character.valueOf(cCharAt2).getClass();
                    intRef.element = cCharAt2;
                    if (intRef.element >= 128) {
                        break;
                    }
                    i5 = i3 + 1;
                    segmentWriteContext.setUnchecked(segmentWritableSegment, i3 + i4, (byte) intRef.element);
                }
                int i6 = i4 + i3;
                if (i6 == 1) {
                    segmentWritableSegment.setLimit(segmentWritableSegment.getLimit() + i6);
                    bufferField.setSizeMut(bufferField.getSizeMut() + ((long) i6));
                } else {
                    if (i6 < 0 || i6 > segmentWritableSegment.getRemainingCapacity()) {
                        throw new IllegalStateException(("Invalid number of bytes written: " + i6 + ". Should be in 0.." + segmentWritableSegment.getRemainingCapacity()).toString());
                    }
                    if (i6 != 0) {
                        segmentWritableSegment.setLimit(segmentWritableSegment.getLimit() + i6);
                        bufferField.setSizeMut(bufferField.getSizeMut() + ((long) i6));
                    } else if (SegmentKt.isEmpty(segmentWritableSegment)) {
                        bufferField.recycleTail();
                    }
                }
            } else {
                if (intRef.element < 2048) {
                    UnsafeBufferOperations unsafeBufferOperations2 = UnsafeBufferOperations.INSTANCE;
                    Segment segmentWritableSegment2 = bufferField.writableSegment(2);
                    UnsafeBufferOperationsKt.SegmentWriteContextImpl.setUnchecked(segmentWritableSegment2, 0, (byte) ((intRef.element >> 6) | 192), (byte) ((intRef.element & 63) | 128));
                    segmentWritableSegment2.setLimit(segmentWritableSegment2.getLimit() + 2);
                    sizeMut = bufferField.getSizeMut();
                    j = 2;
                } else if (intRef.element < 55296 || intRef.element > 57343) {
                    UnsafeBufferOperations unsafeBufferOperations3 = UnsafeBufferOperations.INSTANCE;
                    Segment segmentWritableSegment3 = bufferField.writableSegment(3);
                    UnsafeBufferOperationsKt.SegmentWriteContextImpl.setUnchecked(segmentWritableSegment3, 0, (byte) ((intRef.element >> 12) | 224), (byte) (((intRef.element >> 6) & 63) | 128), (byte) ((intRef.element & 63) | 128));
                    segmentWritableSegment3.setLimit(segmentWritableSegment3.getLimit() + 3);
                    sizeMut = bufferField.getSizeMut();
                    j = 3;
                } else {
                    int i7 = i3 + 1;
                    char cCharAt3 = i7 < i2 ? string.charAt(i7) : (char) 0;
                    if (intRef.element > 56319 || 56320 > cCharAt3 || cCharAt3 >= 57344) {
                        bufferField.writeByte((byte) 63);
                        i3 = i7;
                    } else {
                        int i8 = (((intRef.element & AnalyticsListener.EVENT_DRM_KEYS_LOADED) << 10) | (cCharAt3 & 1023)) + 65536;
                        UnsafeBufferOperations unsafeBufferOperations4 = UnsafeBufferOperations.INSTANCE;
                        Segment segmentWritableSegment4 = bufferField.writableSegment(4);
                        UnsafeBufferOperationsKt.SegmentWriteContextImpl.setUnchecked(segmentWritableSegment4, 0, (byte) ((i8 >> 18) | 240), (byte) (((i8 >> 12) & 63) | 128), (byte) (((i8 >> 6) & 63) | 128), (byte) ((i8 & 63) | 128));
                        segmentWritableSegment4.setLimit(segmentWritableSegment4.getLimit() + 4);
                        bufferField.setSizeMut(bufferField.getSizeMut() + 4);
                        i3 += 2;
                    }
                }
                bufferField.setSizeMut(sizeMut + j);
                i3++;
            }
        }
        sink.hintEmit();
    }

    public static /* synthetic */ void writeString$default(Sink sink, CharSequence charSequence, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = charSequence.length();
        }
        writeString(sink, charSequence, i, i2);
    }

    public static final void writeString(Sink sink, CharSequence chars, int i, int i2) {
        long sizeMut;
        long j;
        Intrinsics.checkNotNullParameter(sink, "<this>");
        Intrinsics.checkNotNullParameter(chars, "chars");
        int i3 = i;
        _UtilKt.checkBounds(chars.length(), i3, i2);
        Buffer bufferField = sink.getBufferField();
        while (i3 < i2) {
            Ref.IntRef intRef = new Ref.IntRef();
            char cCharAt = chars.charAt(i3);
            Character.valueOf(cCharAt).getClass();
            intRef.element = cCharAt;
            if (intRef.element < 128) {
                UnsafeBufferOperations unsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
                Segment segmentWritableSegment = bufferField.writableSegment(1);
                SegmentWriteContext segmentWriteContext = UnsafeBufferOperationsKt.SegmentWriteContextImpl;
                int i4 = -i3;
                int iMin = Math.min(i2, segmentWritableSegment.getRemainingCapacity() + i3);
                int i5 = i3 + 1;
                segmentWriteContext.setUnchecked(segmentWritableSegment, i3 + i4, (byte) intRef.element);
                while (true) {
                    i3 = i5;
                    if (i3 >= iMin) {
                        break;
                    }
                    char cCharAt2 = chars.charAt(i3);
                    Character.valueOf(cCharAt2).getClass();
                    intRef.element = cCharAt2;
                    if (intRef.element >= 128) {
                        break;
                    }
                    i5 = i3 + 1;
                    segmentWriteContext.setUnchecked(segmentWritableSegment, i3 + i4, (byte) intRef.element);
                }
                int i6 = i4 + i3;
                if (i6 == 1) {
                    segmentWritableSegment.setLimit(segmentWritableSegment.getLimit() + i6);
                    bufferField.setSizeMut(bufferField.getSizeMut() + ((long) i6));
                } else {
                    if (i6 < 0 || i6 > segmentWritableSegment.getRemainingCapacity()) {
                        throw new IllegalStateException(("Invalid number of bytes written: " + i6 + ". Should be in 0.." + segmentWritableSegment.getRemainingCapacity()).toString());
                    }
                    if (i6 != 0) {
                        segmentWritableSegment.setLimit(segmentWritableSegment.getLimit() + i6);
                        bufferField.setSizeMut(bufferField.getSizeMut() + ((long) i6));
                    } else if (SegmentKt.isEmpty(segmentWritableSegment)) {
                        bufferField.recycleTail();
                    }
                }
            } else {
                if (intRef.element < 2048) {
                    UnsafeBufferOperations unsafeBufferOperations2 = UnsafeBufferOperations.INSTANCE;
                    Segment segmentWritableSegment2 = bufferField.writableSegment(2);
                    UnsafeBufferOperationsKt.SegmentWriteContextImpl.setUnchecked(segmentWritableSegment2, 0, (byte) ((intRef.element >> 6) | 192), (byte) ((intRef.element & 63) | 128));
                    segmentWritableSegment2.setLimit(segmentWritableSegment2.getLimit() + 2);
                    sizeMut = bufferField.getSizeMut();
                    j = 2;
                } else if (intRef.element < 55296 || intRef.element > 57343) {
                    UnsafeBufferOperations unsafeBufferOperations3 = UnsafeBufferOperations.INSTANCE;
                    Segment segmentWritableSegment3 = bufferField.writableSegment(3);
                    UnsafeBufferOperationsKt.SegmentWriteContextImpl.setUnchecked(segmentWritableSegment3, 0, (byte) ((intRef.element >> 12) | 224), (byte) (((intRef.element >> 6) & 63) | 128), (byte) ((intRef.element & 63) | 128));
                    segmentWritableSegment3.setLimit(segmentWritableSegment3.getLimit() + 3);
                    sizeMut = bufferField.getSizeMut();
                    j = 3;
                } else {
                    int i7 = i3 + 1;
                    char cCharAt3 = i7 < i2 ? chars.charAt(i7) : (char) 0;
                    if (intRef.element > 56319 || 56320 > cCharAt3 || cCharAt3 >= 57344) {
                        bufferField.writeByte((byte) 63);
                        i3 = i7;
                    } else {
                        int i8 = (((intRef.element & AnalyticsListener.EVENT_DRM_KEYS_LOADED) << 10) | (cCharAt3 & 1023)) + 65536;
                        UnsafeBufferOperations unsafeBufferOperations4 = UnsafeBufferOperations.INSTANCE;
                        Segment segmentWritableSegment4 = bufferField.writableSegment(4);
                        UnsafeBufferOperationsKt.SegmentWriteContextImpl.setUnchecked(segmentWritableSegment4, 0, (byte) ((i8 >> 18) | 240), (byte) (((i8 >> 12) & 63) | 128), (byte) (((i8 >> 6) & 63) | 128), (byte) ((i8 & 63) | 128));
                        segmentWritableSegment4.setLimit(segmentWritableSegment4.getLimit() + 4);
                        bufferField.setSizeMut(bufferField.getSizeMut() + 4);
                        i3 += 2;
                    }
                }
                bufferField.setSizeMut(sizeMut + j);
                i3++;
            }
        }
        sink.hintEmit();
    }

    public static final String readString(Source source) {
        Intrinsics.checkNotNullParameter(source, "<this>");
        source.request(Long.MAX_VALUE);
        return commonReadUtf8(source.getBufferField(), source.getBufferField().getSizeMut());
    }

    public static final String readString(Buffer buffer) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        return commonReadUtf8(buffer, buffer.getSizeMut());
    }

    public static final String readString(Source source, long j) {
        Intrinsics.checkNotNullParameter(source, "<this>");
        source.require(j);
        return commonReadUtf8(source.getBufferField(), j);
    }

    public static final int readCodePointValue(Source source) {
        Intrinsics.checkNotNullParameter(source, "<this>");
        if (source instanceof Buffer) {
            return commonReadUtf8CodePoint((Buffer) source);
        }
        source.require(1L);
        byte b = source.getBufferField().get(0L);
        if ((b & 224) == 192) {
            source.require(2L);
        } else if ((b & 240) == 224) {
            source.require(3L);
        } else if ((b & 248) == 240) {
            source.require(4L);
        }
        return commonReadUtf8CodePoint(source.getBufferField());
    }

    public static final String readLine(Source source) {
        int i;
        Intrinsics.checkNotNullParameter(source, "<this>");
        if (!source.request(1L)) {
            return null;
        }
        long jIndexOf$default = SourcesKt.indexOf$default(source, (byte) 10, 0L, 0L, 6, null);
        if (jIndexOf$default == -1) {
            return readString(source);
        }
        if (jIndexOf$default == 0) {
            source.skip(1L);
            return "";
        }
        long j = jIndexOf$default - 1;
        if (source.getBufferField().get(j) == 13) {
            i = 2;
            jIndexOf$default = j;
        } else {
            i = 1;
        }
        String string = readString(source, jIndexOf$default);
        source.skip(i);
        return string;
    }

    public static /* synthetic */ String readLineStrict$default(Source source, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = Long.MAX_VALUE;
        }
        return readLineStrict(source, j);
    }

    public static final String readLineStrict(Source source, long j) throws EOFException {
        Intrinsics.checkNotNullParameter(source, "<this>");
        if (j < 0) {
            throw new IllegalArgumentException(("limit (" + j + ") < 0").toString());
        }
        long j2 = 1;
        source.require(1L);
        long jIndexOf = SourcesKt.indexOf(source, (byte) 10, 0L, j);
        if (jIndexOf == 0) {
            source.skip(1L);
            return "";
        }
        if (jIndexOf > 0) {
            long j3 = jIndexOf - 1;
            if (source.getBufferField().get(j3) == 13) {
                j2 = 2;
                jIndexOf = j3;
            }
            String string = readString(source, jIndexOf);
            source.skip(j2);
            return string;
        }
        if (source.getBufferField().getSizeMut() < j) {
            throw new EOFException();
        }
        if (j == Long.MAX_VALUE) {
            throw new EOFException();
        }
        long j4 = j + 1;
        if (!source.request(j4)) {
            throw new EOFException();
        }
        byte b = source.getBufferField().get(j);
        if (b == 10) {
            String string2 = readString(source, j);
            source.skip(1L);
            return string2;
        }
        if (b != 13 || !source.request(j + 2)) {
            throw new EOFException();
        }
        if (source.getBufferField().get(j4) != 10) {
            throw new EOFException();
        }
        String string3 = readString(source, j);
        source.skip(2L);
        return string3;
    }

    private static final int commonReadUtf8CodePoint(Buffer buffer) throws EOFException {
        int i;
        int i2;
        int i3;
        buffer.require(1L);
        byte b = buffer.get(0L);
        if ((b & 128) == 0) {
            i = b & 127;
            i3 = 0;
            i2 = 1;
        } else if ((b & 224) == 192) {
            i = b & Ascii.US;
            i2 = 2;
            i3 = 128;
        } else if ((b & 240) == 224) {
            i = b & Ascii.SI;
            i2 = 3;
            i3 = 2048;
        } else {
            if ((b & 248) != 240) {
                buffer.skip(1L);
                return 65533;
            }
            i = b & 7;
            i2 = 4;
            i3 = 65536;
        }
        long j = i2;
        if (buffer.getSizeMut() < j) {
            throw new EOFException("size < " + i2 + ": " + buffer.getSizeMut() + " (to read code point prefixed 0x" + _UtilKt.toHexString(b) + ')');
        }
        for (int i4 = 1; i4 < i2; i4++) {
            long j2 = i4;
            byte b2 = buffer.get(j2);
            if ((b2 & 192) != 128) {
                buffer.skip(j2);
                return 65533;
            }
            i = (i << 6) | (b2 & 63);
        }
        buffer.skip(j);
        if (i > 1114111) {
            return 65533;
        }
        if ((55296 > i || i >= 57344) && i >= i3) {
            return i;
        }
        return 65533;
    }

    private static final void commonWriteUtf8(Buffer buffer, int i, int i2, Function1<? super Integer, Character> function1) {
        char cCharValue;
        while (i < i2) {
            char cCharValue2 = function1.invoke(Integer.valueOf(i)).charValue();
            if (cCharValue2 < 128) {
                UnsafeBufferOperations unsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
                Segment segmentWritableSegment = buffer.writableSegment(1);
                SegmentWriteContext segmentWriteContext = UnsafeBufferOperationsKt.SegmentWriteContextImpl;
                int i3 = -i;
                int iMin = Math.min(i2, segmentWritableSegment.getRemainingCapacity() + i);
                int i4 = i + 1;
                segmentWriteContext.setUnchecked(segmentWritableSegment, i + i3, (byte) cCharValue2);
                while (true) {
                    i = i4;
                    if (i >= iMin || (cCharValue = function1.invoke(Integer.valueOf(i)).charValue()) >= 128) {
                        break;
                    }
                    i4 = i + 1;
                    segmentWriteContext.setUnchecked(segmentWritableSegment, i + i3, (byte) cCharValue);
                }
                int i5 = i3 + i;
                if (i5 == 1) {
                    segmentWritableSegment.setLimit(segmentWritableSegment.getLimit() + i5);
                    buffer.setSizeMut(buffer.getSizeMut() + ((long) i5));
                } else {
                    if (i5 < 0 || i5 > segmentWritableSegment.getRemainingCapacity()) {
                        throw new IllegalStateException(("Invalid number of bytes written: " + i5 + ". Should be in 0.." + segmentWritableSegment.getRemainingCapacity()).toString());
                    }
                    if (i5 != 0) {
                        segmentWritableSegment.setLimit(segmentWritableSegment.getLimit() + i5);
                        buffer.setSizeMut(buffer.getSizeMut() + ((long) i5));
                    } else if (SegmentKt.isEmpty(segmentWritableSegment)) {
                        buffer.recycleTail();
                    }
                }
            } else {
                if (cCharValue2 < 2048) {
                    UnsafeBufferOperations unsafeBufferOperations2 = UnsafeBufferOperations.INSTANCE;
                    Segment segmentWritableSegment2 = buffer.writableSegment(2);
                    UnsafeBufferOperationsKt.SegmentWriteContextImpl.setUnchecked(segmentWritableSegment2, 0, (byte) ((cCharValue2 >> 6) | 192), (byte) ((cCharValue2 & '?') | 128));
                    segmentWritableSegment2.setLimit(segmentWritableSegment2.getLimit() + 2);
                    buffer.setSizeMut(buffer.getSizeMut() + 2);
                } else if (cCharValue2 < 55296 || cCharValue2 > 57343) {
                    UnsafeBufferOperations unsafeBufferOperations3 = UnsafeBufferOperations.INSTANCE;
                    Segment segmentWritableSegment3 = buffer.writableSegment(3);
                    UnsafeBufferOperationsKt.SegmentWriteContextImpl.setUnchecked(segmentWritableSegment3, 0, (byte) ((cCharValue2 >> '\f') | 224), (byte) (((cCharValue2 >> 6) & 63) | 128), (byte) ((cCharValue2 & '?') | 128));
                    segmentWritableSegment3.setLimit(segmentWritableSegment3.getLimit() + 3);
                    buffer.setSizeMut(buffer.getSizeMut() + 3);
                } else {
                    int i6 = i + 1;
                    char cCharValue3 = i6 < i2 ? function1.invoke(Integer.valueOf(i6)).charValue() : (char) 0;
                    if (cCharValue2 > 56319 || 56320 > cCharValue3 || cCharValue3 >= 57344) {
                        buffer.writeByte((byte) 63);
                        i = i6;
                    } else {
                        int i7 = (((cCharValue2 & 1023) << 10) | (cCharValue3 & 1023)) + 65536;
                        UnsafeBufferOperations unsafeBufferOperations4 = UnsafeBufferOperations.INSTANCE;
                        Segment segmentWritableSegment4 = buffer.writableSegment(4);
                        UnsafeBufferOperationsKt.SegmentWriteContextImpl.setUnchecked(segmentWritableSegment4, 0, (byte) ((i7 >> 18) | 240), (byte) (((i7 >> 12) & 63) | 128), (byte) (((i7 >> 6) & 63) | 128), (byte) ((i7 & 63) | 128));
                        segmentWritableSegment4.setLimit(segmentWritableSegment4.getLimit() + 4);
                        buffer.setSizeMut(buffer.getSizeMut() + 4);
                        i += 2;
                    }
                }
                i++;
            }
        }
    }

    private static final void commonWriteUtf8CodePoint(Buffer buffer, int i) {
        if (i < 0 || i > 1114111) {
            throw new IllegalArgumentException("Code point value is out of Unicode codespace 0..0x10ffff: 0x" + _UtilKt.toHexString(i) + " (" + i + ')');
        }
        if (i < 128) {
            buffer.writeByte((byte) i);
            return;
        }
        if (i < 2048) {
            UnsafeBufferOperations unsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
            Segment segmentWritableSegment = buffer.writableSegment(2);
            SegmentWriteContext segmentWriteContext = UnsafeBufferOperationsKt.SegmentWriteContextImpl;
            segmentWriteContext.setUnchecked(segmentWritableSegment, 0, (byte) ((i >> 6) | 192));
            segmentWriteContext.setUnchecked(segmentWritableSegment, 1, (byte) ((i & 63) | 128));
            segmentWritableSegment.setLimit(segmentWritableSegment.getLimit() + 2);
            buffer.setSizeMut(buffer.getSizeMut() + 2);
            return;
        }
        if (55296 <= i && i < 57344) {
            buffer.writeByte((byte) 63);
            return;
        }
        if (i < 65536) {
            UnsafeBufferOperations unsafeBufferOperations2 = UnsafeBufferOperations.INSTANCE;
            Segment segmentWritableSegment2 = buffer.writableSegment(3);
            SegmentWriteContext segmentWriteContext2 = UnsafeBufferOperationsKt.SegmentWriteContextImpl;
            segmentWriteContext2.setUnchecked(segmentWritableSegment2, 0, (byte) ((i >> 12) | 224));
            segmentWriteContext2.setUnchecked(segmentWritableSegment2, 1, (byte) (((i >> 6) & 63) | 128));
            segmentWriteContext2.setUnchecked(segmentWritableSegment2, 2, (byte) ((i & 63) | 128));
            segmentWritableSegment2.setLimit(segmentWritableSegment2.getLimit() + 3);
            buffer.setSizeMut(buffer.getSizeMut() + 3);
            return;
        }
        UnsafeBufferOperations unsafeBufferOperations3 = UnsafeBufferOperations.INSTANCE;
        Segment segmentWritableSegment3 = buffer.writableSegment(4);
        SegmentWriteContext segmentWriteContext3 = UnsafeBufferOperationsKt.SegmentWriteContextImpl;
        segmentWriteContext3.setUnchecked(segmentWritableSegment3, 0, (byte) ((i >> 18) | 240));
        segmentWriteContext3.setUnchecked(segmentWritableSegment3, 1, (byte) (((i >> 12) & 63) | 128));
        segmentWriteContext3.setUnchecked(segmentWritableSegment3, 2, (byte) (((i >> 6) & 63) | 128));
        segmentWriteContext3.setUnchecked(segmentWritableSegment3, 3, (byte) ((i & 63) | 128));
        segmentWritableSegment3.setLimit(segmentWritableSegment3.getLimit() + 4);
        buffer.setSizeMut(buffer.getSizeMut() + 4);
    }

    private static final String commonReadUtf8(Buffer buffer, long j) throws EOFException {
        if (j == 0) {
            return "";
        }
        UnsafeBufferOperations unsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
        Segment head = buffer.getHead();
        if (head != null) {
            SegmentReadContext unused = UnsafeBufferOperationsKt.SegmentReadContextImpl;
            if (head.getSize() < j) {
                return _Utf8Kt.commonToUtf8String$default(SourcesKt.readByteArray(buffer, (int) j), 0, 0, 3, null);
            }
            byte[] bArrDataAsByteArray = head.dataAsByteArray(true);
            int pos = head.getPos();
            String strCommonToUtf8String = _Utf8Kt.commonToUtf8String(bArrDataAsByteArray, pos, Math.min(head.getLimit(), ((int) j) + pos));
            buffer.skip(j);
            return strCommonToUtf8String;
        }
        throw new IllegalStateException("Unreacheable".toString());
    }

    public static final void writeCodePointValue(Sink sink, int i) {
        Intrinsics.checkNotNullParameter(sink, "<this>");
        commonWriteUtf8CodePoint(sink.getBufferField(), i);
        sink.hintEmit();
    }
}
