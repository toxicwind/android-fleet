package kotlinx.io;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.common.C;
import androidx.media3.exoplayer.MediaPeriodQueue;
import androidx.media3.exoplayer.audio.SilenceSkippingAudioProcessor;
import dev.whyoleg.cryptography.serialization.asn1.internal.DerTagKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.unsafe.SegmentWriteContext;
import kotlinx.io.unsafe.UnsafeBufferOperations;
import kotlinx.io.unsafe.UnsafeBufferOperationsKt;
import okhttp3.internal.connection.RealConnection;

/* JADX INFO: compiled from: Sinks.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000d\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0002\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0086\u0080\u0004\u001a\u0016\u0010\u0007\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0086\u0080\u0004\u001a\u0016\u0010\n\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fH\u0086\u0080\u0004\u001a\u0016\u0010\r\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fH\u0086\u0080\u0004\u001a\u0016\u0010\u000e\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fH\u0086\u0080\u0004\u001a\u001d\u0010\u000f\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0011H\u0086\u0080\u0004¢\u0006\u0004\b\u0012\u0010\u0013\u001a\u001d\u0010\u0014\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0015H\u0086\u0080\u0004¢\u0006\u0004\b\u0016\u0010\u0017\u001a\u001d\u0010\u0018\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\b\u001a\u00020\u0019H\u0086\u0080\u0004¢\u0006\u0004\b\u001a\u0010\u001b\u001a\u001d\u0010\u001c\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u001dH\u0086\u0080\u0004¢\u0006\u0004\b\u001e\u0010\u001f\u001a\u001d\u0010 \u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0015H\u0086\u0080\u0004¢\u0006\u0004\b!\u0010\u0017\u001a\u001d\u0010\"\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\b\u001a\u00020\u0019H\u0086\u0080\u0004¢\u0006\u0004\b#\u0010\u001b\u001a\u001d\u0010$\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u001dH\u0086\u0080\u0004¢\u0006\u0004\b%\u0010\u001f\u001a\u0016\u0010&\u001a\u00020\u0003*\u00020\u00042\u0006\u0010'\u001a\u00020(H\u0086\u0080\u0004\u001a\u0016\u0010)\u001a\u00020\u0003*\u00020\u00042\u0006\u0010*\u001a\u00020+H\u0086\u0080\u0004\u001a\u0016\u0010,\u001a\u00020\u0003*\u00020\u00042\u0006\u0010'\u001a\u00020(H\u0086\u0080\u0004\u001a\u0016\u0010-\u001a\u00020\u0003*\u00020\u00042\u0006\u0010*\u001a\u00020+H\u0086\u0080\u0004\u001a2\u0010.\u001a\u00020\u0003*\u00020\u00042\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020\u000300H\u0087\u0088\u0004ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\"\u000f\u0010\u0000\u001a\u00020\u0001X\u0082\u0084\b¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u00062"}, d2 = {"HEX_DIGIT_BYTES", "", "writeShortLe", "", "Lkotlinx/io/Sink;", "short", "", "writeIntLe", "int", "", "writeLongLe", "long", "", "writeDecimalLong", "writeHexadecimalUnsignedLong", "writeUByte", "byte", "Lkotlin/UByte;", "writeUByte-EK-6454", "(Lkotlinx/io/Sink;B)V", "writeUShort", "Lkotlin/UShort;", "writeUShort-i8woANY", "(Lkotlinx/io/Sink;S)V", "writeUInt", "Lkotlin/UInt;", "writeUInt-Qn1smSk", "(Lkotlinx/io/Sink;I)V", "writeULong", "Lkotlin/ULong;", "writeULong-2TYgG_w", "(Lkotlinx/io/Sink;J)V", "writeUShortLe", "writeUShortLe-i8woANY", "writeUIntLe", "writeUIntLe-Qn1smSk", "writeULongLe", "writeULongLe-2TYgG_w", "writeFloat", TypedValues.Custom.S_FLOAT, "", "writeDouble", "double", "", "writeFloatLe", "writeDoubleLe", "writeToInternalBuffer", "lambda", "Lkotlin/Function1;", "Lkotlinx/io/Buffer;", "kotlinx-io-core"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class SinksKt {
    private static final byte[] HEX_DIGIT_BYTES;

    static {
        byte[] bArr = new byte[16];
        int i = 0;
        while (i < 16) {
            bArr[i] = (byte) ((i < 10 ? 48 : 87) + i);
            i++;
        }
        HEX_DIGIT_BYTES = bArr;
    }

    public static final void writeShortLe(Sink sink, short s) {
        Intrinsics.checkNotNullParameter(sink, "<this>");
        sink.writeShort(_UtilsJvmKt.reverseBytes(s));
    }

    public static final void writeIntLe(Sink sink, int i) {
        Intrinsics.checkNotNullParameter(sink, "<this>");
        sink.writeInt(_UtilsJvmKt.reverseBytes(i));
    }

    public static final void writeLongLe(Sink sink, long j) {
        Intrinsics.checkNotNullParameter(sink, "<this>");
        sink.writeLong(_UtilsJvmKt.reverseBytes(j));
    }

    public static final void writeDecimalLong(Sink sink, long j) {
        int i;
        Intrinsics.checkNotNullParameter(sink, "<this>");
        if (j == 0) {
            sink.writeByte(DerTagKt.DerTag_SEQUENCE);
            return;
        }
        int i2 = 1;
        if (j < 0) {
            j = -j;
            if (j < 0) {
                Utf8Kt.writeString$default(sink, "-9223372036854775808", 0, 0, 6, (Object) null);
                return;
            }
            i = 1;
        } else {
            i = 0;
        }
        if (j < 100000000) {
            if (j < 10000) {
                if (j >= 100) {
                    i2 = j < 1000 ? 3 : 4;
                } else if (j >= 10) {
                    i2 = 2;
                }
            } else if (j < 1000000) {
                i2 = j < SilenceSkippingAudioProcessor.DEFAULT_MINIMUM_SILENCE_DURATION_US ? 5 : 6;
            } else {
                i2 = j < 10000000 ? 7 : 8;
            }
        } else if (j < MediaPeriodQueue.INITIAL_RENDERER_POSITION_OFFSET_US) {
            if (j < RealConnection.IDLE_CONNECTION_HEALTHY_NS) {
                i2 = j < C.NANOS_PER_SECOND ? 9 : 10;
            } else {
                i2 = j < 100000000000L ? 11 : 12;
            }
        } else if (j < 1000000000000000L) {
            if (j < 10000000000000L) {
                i2 = 13;
            } else {
                i2 = j < 100000000000000L ? 14 : 15;
            }
        } else if (j < 100000000000000000L) {
            i2 = j < 10000000000000000L ? 16 : 17;
        } else {
            i2 = j < 1000000000000000000L ? 18 : 19;
        }
        if (i != 0) {
            i2++;
        }
        Buffer bufferField = sink.getBufferField();
        UnsafeBufferOperations unsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
        Segment segmentWritableSegment = bufferField.writableSegment(i2);
        SegmentWriteContext segmentWriteContext = UnsafeBufferOperationsKt.SegmentWriteContextImpl;
        int i3 = i2 - 1;
        if (i <= i3) {
            while (true) {
                segmentWriteContext.setUnchecked(segmentWritableSegment, i3, HEX_DIGIT_BYTES[(byte) (j % 10)]);
                j /= 10;
                if (i3 == i) {
                    break;
                } else {
                    i3--;
                }
            }
        }
        if (i != 0) {
            segmentWriteContext.setUnchecked(segmentWritableSegment, 0, (byte) 45);
        }
        segmentWritableSegment.setLimit(segmentWritableSegment.getLimit() + i2);
        bufferField.setSizeMut(bufferField.getSizeMut() + ((long) i2));
        sink.hintEmit();
    }

    public static final void writeHexadecimalUnsignedLong(Sink sink, long j) {
        Intrinsics.checkNotNullParameter(sink, "<this>");
        if (j == 0) {
            sink.writeByte(DerTagKt.DerTag_SEQUENCE);
            return;
        }
        int iNumberOfLeadingZeros = j == 0 ? 1 : (67 - Long.numberOfLeadingZeros(j)) / 4;
        Buffer bufferField = sink.getBufferField();
        UnsafeBufferOperations unsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
        Segment segmentWritableSegment = bufferField.writableSegment(iNumberOfLeadingZeros);
        SegmentWriteContext segmentWriteContext = UnsafeBufferOperationsKt.SegmentWriteContextImpl;
        for (int i = iNumberOfLeadingZeros - 1; -1 < i; i--) {
            segmentWriteContext.setUnchecked(segmentWritableSegment, i, HEX_DIGIT_BYTES[((int) j) & 15]);
            j >>>= 4;
        }
        segmentWritableSegment.setLimit(segmentWritableSegment.getLimit() + iNumberOfLeadingZeros);
        bufferField.setSizeMut(bufferField.getSizeMut() + ((long) iNumberOfLeadingZeros));
        sink.hintEmit();
    }

    /* JADX INFO: renamed from: writeUByte-EK-6454, reason: not valid java name */
    public static final void m10601writeUByteEK6454(Sink writeUByte, byte b) {
        Intrinsics.checkNotNullParameter(writeUByte, "$this$writeUByte");
        writeUByte.writeByte(b);
    }

    /* JADX INFO: renamed from: writeUShort-i8woANY, reason: not valid java name */
    public static final void m10606writeUShorti8woANY(Sink writeUShort, short s) {
        Intrinsics.checkNotNullParameter(writeUShort, "$this$writeUShort");
        writeUShort.writeShort(s);
    }

    /* JADX INFO: renamed from: writeUInt-Qn1smSk, reason: not valid java name */
    public static final void m10602writeUIntQn1smSk(Sink writeUInt, int i) {
        Intrinsics.checkNotNullParameter(writeUInt, "$this$writeUInt");
        writeUInt.writeInt(i);
    }

    /* JADX INFO: renamed from: writeULong-2TYgG_w, reason: not valid java name */
    public static final void m10604writeULong2TYgG_w(Sink writeULong, long j) {
        Intrinsics.checkNotNullParameter(writeULong, "$this$writeULong");
        writeULong.writeLong(j);
    }

    /* JADX INFO: renamed from: writeUShortLe-i8woANY, reason: not valid java name */
    public static final void m10607writeUShortLei8woANY(Sink writeUShortLe, short s) {
        Intrinsics.checkNotNullParameter(writeUShortLe, "$this$writeUShortLe");
        writeShortLe(writeUShortLe, s);
    }

    /* JADX INFO: renamed from: writeUIntLe-Qn1smSk, reason: not valid java name */
    public static final void m10603writeUIntLeQn1smSk(Sink writeUIntLe, int i) {
        Intrinsics.checkNotNullParameter(writeUIntLe, "$this$writeUIntLe");
        writeIntLe(writeUIntLe, i);
    }

    /* JADX INFO: renamed from: writeULongLe-2TYgG_w, reason: not valid java name */
    public static final void m10605writeULongLe2TYgG_w(Sink writeULongLe, long j) {
        Intrinsics.checkNotNullParameter(writeULongLe, "$this$writeULongLe");
        writeLongLe(writeULongLe, j);
    }

    public static final void writeFloat(Sink sink, float f) {
        Intrinsics.checkNotNullParameter(sink, "<this>");
        sink.writeInt(Float.floatToIntBits(f));
    }

    public static final void writeDouble(Sink sink, double d) {
        Intrinsics.checkNotNullParameter(sink, "<this>");
        sink.writeLong(Double.doubleToLongBits(d));
    }

    public static final void writeFloatLe(Sink sink, float f) {
        Intrinsics.checkNotNullParameter(sink, "<this>");
        writeIntLe(sink, Float.floatToIntBits(f));
    }

    public static final void writeDoubleLe(Sink sink, double d) {
        Intrinsics.checkNotNullParameter(sink, "<this>");
        writeLongLe(sink, Double.doubleToLongBits(d));
    }

    public static final void writeToInternalBuffer(Sink sink, Function1<? super Buffer, Unit> lambda) {
        Intrinsics.checkNotNullParameter(sink, "<this>");
        Intrinsics.checkNotNullParameter(lambda, "lambda");
        lambda.invoke(sink.getBufferField());
        sink.hintEmit();
    }
}
