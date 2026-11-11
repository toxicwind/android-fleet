package dev.whyoleg.cryptography.serialization.asn1.internal;

import io.ktor.http.ContentDisposition;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ByteArrayOutput.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0005\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\t\bF¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0007H\u0082\u0080\u0004J\n\u0010\u000e\u001a\u00020\u0005H\u0086\u0080\u0004J\u0012\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086\u0080\u0004J\u0012\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0007H\u0086\u0080\u0004J\u0012\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0005H\u0086\u0080\u0004J\u0012\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0000H\u0086\u0080\u0004R\u000f\u0010\u0004\u001a\u00020\u0005X\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0006\u001a\u00020\u0007X\u0082\u008e\b¢\u0006\u0002\n\u0000R\u0015\u0010\b\u001a\u00020\u00078FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Ldev/whyoleg/cryptography/serialization/asn1/internal/ByteArrayOutput;", "", "<init>", "()V", "array", "", "position", "", ContentDisposition.Parameters.Size, "getSize", "()I", "ensureCapacity", "", "elementsToAppend", "toByteArray", "write", "byte", "", "bytes", "output", "cryptography-serialization-asn1"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ByteArrayOutput {
    private byte[] array = new byte[32];
    private int position;

    /* JADX INFO: renamed from: getSize, reason: from getter */
    public final int getPosition() {
        return this.position;
    }

    private final void ensureCapacity(int elementsToAppend) {
        int i = this.position;
        int i2 = i + elementsToAppend;
        byte[] bArr = this.array;
        if (i2 <= bArr.length) {
            return;
        }
        byte[] bArrCopyOf = Arrays.copyOf(bArr, Integer.highestOneBit(i + elementsToAppend) << 1);
        Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
        this.array = bArrCopyOf;
    }

    public final byte[] toByteArray() {
        byte[] bArrCopyOf = Arrays.copyOf(this.array, this.position);
        Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
        return bArrCopyOf;
    }

    public final void write(byte b) {
        ensureCapacity(1);
        byte[] bArr = this.array;
        int i = this.position;
        this.position = i + 1;
        bArr[i] = b;
    }

    public final void write(int i) {
        write((byte) i);
    }

    public final void write(byte[] bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        if (bytes.length == 0) {
            return;
        }
        ensureCapacity(bytes.length);
        ArraysKt.copyInto$default(bytes, this.array, this.position, 0, 0, 12, (Object) null);
        this.position += bytes.length;
    }

    public final void write(ByteArrayOutput output) {
        Intrinsics.checkNotNullParameter(output, "output");
        if (output.getPosition() == 0) {
            return;
        }
        ensureCapacity(output.position);
        ArraysKt.copyInto(output.array, this.array, this.position, 0, output.position);
        this.position += output.position;
    }
}
