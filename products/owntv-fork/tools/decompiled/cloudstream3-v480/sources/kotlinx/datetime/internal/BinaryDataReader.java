package kotlinx.datetime.internal;

import kotlin.Metadata;
import kotlin.UByte;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: BinaryDataReader.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\b\u001a\u00020\tJ\r\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\u0006\u0010\u000e\u001a\u00020\u0005J\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0005J\u000e\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0005J\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0005R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lkotlinx/datetime/internal/BinaryDataReader;", "", "bytes", "", "position", "", "<init>", "([BI)V", "readByte", "", "readUnsignedByte", "Lkotlin/UByte;", "readUnsignedByte-w2LRezQ", "()B", "readInt", "readLong", "", "readUtf8String", "", "exactLength", "readNullTerminatedUtf8String", "fieldSize", "readAsciiChar", "", "skip", "", "length", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class BinaryDataReader {
    private final byte[] bytes;
    private int position;

    public BinaryDataReader(byte[] bytes, int i) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        this.bytes = bytes;
        this.position = i;
    }

    public /* synthetic */ BinaryDataReader(byte[] bArr, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(bArr, (i2 & 2) != 0 ? 0 : i);
    }

    public final byte readByte() {
        byte[] bArr = this.bytes;
        int i = this.position;
        this.position = i + 1;
        return bArr[i];
    }

    /* JADX INFO: renamed from: readUnsignedByte-w2LRezQ, reason: not valid java name */
    public final byte m10576readUnsignedBytew2LRezQ() {
        return UByte.m8954constructorimpl(readByte());
    }

    public final int readInt() {
        byte[] bArr = this.bytes;
        int i = this.position;
        int i2 = ((bArr[i] & 255) << 24) | ((bArr[i + 1] & 255) << 16) | ((bArr[i + 2] & 255) << 8);
        int i3 = bArr[i + 3] & 255;
        this.position = i + 4;
        Unit unit = Unit.INSTANCE;
        return i2 | i3;
    }

    public final long readLong() {
        byte[] bArr = this.bytes;
        int i = this.position;
        long j = ((((long) bArr[i]) & 255) << 56) | ((((long) bArr[i + 1]) & 255) << 48) | ((((long) bArr[i + 2]) & 255) << 40) | ((((long) bArr[i + 3]) & 255) << 32) | ((((long) bArr[i + 4]) & 255) << 24) | ((((long) bArr[i + 5]) & 255) << 16) | ((((long) bArr[i + 6]) & 255) << 8);
        long j2 = 255 & ((long) bArr[i + 7]);
        this.position = i + 8;
        Unit unit = Unit.INSTANCE;
        return j | j2;
    }

    public final String readUtf8String(int exactLength) {
        byte[] bArr = this.bytes;
        int i = this.position;
        String strDecodeToString$default = StringsKt.decodeToString$default(bArr, i, i + exactLength, false, 4, null);
        this.position += exactLength;
        return strDecodeToString$default;
    }

    public final String readNullTerminatedUtf8String(int fieldSize) {
        int i;
        byte[] bArr;
        int i2 = 0;
        while (true) {
            i = this.position;
            int i3 = i + i2;
            bArr = this.bytes;
            if (i3 >= bArr.length || bArr[i + i2] == 0 || i2 >= fieldSize) {
                break;
            }
            i2++;
        }
        String strDecodeToString$default = StringsKt.decodeToString$default(bArr, i, i + i2, false, 4, null);
        this.position += fieldSize;
        return strDecodeToString$default;
    }

    public final char readAsciiChar() {
        return (char) readByte();
    }

    public final void skip(int length) {
        this.position += length;
    }
}
