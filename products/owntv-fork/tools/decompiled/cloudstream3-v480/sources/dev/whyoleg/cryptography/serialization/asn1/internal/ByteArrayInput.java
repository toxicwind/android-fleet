package dev.whyoleg.cryptography.serialization.asn1.internal;

import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.schabi.newpipe.extractor.services.peertube.PeertubeParsingHelper;

/* JADX INFO: compiled from: ByteArrayInput.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B%\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\n\u0010\u0011\u001a\u00020\u0012H\u0096\u0080\u0004J\n\u0010\u0013\u001a\u00020\u0014H\u0086\u0080\u0004J\n\u0010\u0015\u001a\u00020\u0014H\u0086\u0080\u0004J\u0012\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0005H\u0086\u0080\u0004J\u0012\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0005H\u0086\u0080\u0004J\u0012\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0005H\u0082\u0080\u0004R\u000f\u0010\u0002\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0006\u001a\u00020\u0005X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\t\u001a\u00020\u0005X\u0082\u008e\b¢\u0006\u0002\n\u0000R\u0015\u0010\n\u001a\u00020\u00058BX\u0082\u0084\b¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0015\u0010\r\u001a\u00020\u000e8FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001b"}, d2 = {"Ldev/whyoleg/cryptography/serialization/asn1/internal/ByteArrayInput;", "", "array", "", "startIndex", "", "endIndex", "<init>", "([BII)V", "position", "available", "getAvailable", "()I", "eof", "", "getEof", "()Z", "toString", "", "peak", "", "read", "length", "readSlice", "ensureAvailableBytes", "", PeertubeParsingHelper.COUNT_KEY, "cryptography-serialization-asn1"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ByteArrayInput {
    private final byte[] array;
    private final int endIndex;
    private int position;

    public ByteArrayInput(byte[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        this.array = array;
        this.endIndex = i2;
        this.position = i;
    }

    public /* synthetic */ ByteArrayInput(byte[] bArr, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(bArr, (i3 & 2) != 0 ? 0 : i, (i3 & 4) != 0 ? bArr.length : i2);
    }

    private final int getAvailable() {
        return this.endIndex - this.position;
    }

    public final boolean getEof() {
        return getAvailable() == 0;
    }

    public String toString() {
        return "ByteArrayInput(size=" + this.array.length + ", endIndex=" + this.endIndex + ", position=" + this.position + ", available=" + getAvailable() + ')';
    }

    public final byte peak() {
        ensureAvailableBytes(1);
        return this.array[this.position];
    }

    public final byte read() {
        ensureAvailableBytes(1);
        byte[] bArr = this.array;
        int i = this.position;
        this.position = i + 1;
        return bArr[i];
    }

    public final byte[] read(int length) {
        ensureAvailableBytes(length);
        if (length == 0) {
            return ByteArrayInputKt.emptyArray;
        }
        byte[] bArr = this.array;
        int i = this.position;
        byte[] bArrCopyOfRange = ArraysKt.copyOfRange(bArr, i, i + length);
        this.position += length;
        return bArrCopyOfRange;
    }

    public final ByteArrayInput readSlice(int length) {
        ensureAvailableBytes(length);
        if (length == 0) {
            return new ByteArrayInput(ByteArrayInputKt.emptyArray, 0, 0, 6, null);
        }
        byte[] bArr = this.array;
        int i = this.position;
        ByteArrayInput byteArrayInput = new ByteArrayInput(bArr, i, i + length);
        this.position += length;
        return byteArrayInput;
    }

    private final void ensureAvailableBytes(int count) {
        if (getAvailable() >= count) {
            return;
        }
        throw new IllegalStateException(("Unexpected EOF, available " + getAvailable() + " bytes, requested: " + count).toString());
    }
}
