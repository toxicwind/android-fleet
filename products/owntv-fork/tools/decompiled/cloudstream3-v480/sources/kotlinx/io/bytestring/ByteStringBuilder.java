package kotlinx.io.bytestring;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ByteStringBuilder.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0013\bF\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\u000e\u001a\u00020\u000fH\u0086\u0080\u0004J\u0012\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0086\u0080\u0004J&\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00072\b\b\u0002\u0010\u0015\u001a\u00020\u00032\b\b\u0002\u0010\u0016\u001a\u00020\u0003H\u0086\u0080\u0004J\u0012\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u0003H\u0082\u0080\u0004R\u000f\u0010\u0006\u001a\u00020\u0007X\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\b\u001a\u00020\u0003X\u0082\u008e\b¢\u0006\u0002\n\u0000R\u0015\u0010\t\u001a\u00020\u00038FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0015\u0010\f\u001a\u00020\u00038FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\r\u0010\u000b¨\u0006\u0019"}, d2 = {"Lkotlinx/io/bytestring/ByteStringBuilder;", "", "initialCapacity", "", "<init>", "(I)V", "buffer", "", TypedValues.CycleType.S_WAVE_OFFSET, ContentDisposition.Parameters.Size, "getSize", "()I", "capacity", "getCapacity", "toByteString", "Lkotlinx/io/bytestring/ByteString;", "append", "", "byte", "", "array", "startIndex", "endIndex", "ensureCapacity", "requiredCapacity", "kotlinx-io-bytestring"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ByteStringBuilder {
    private byte[] buffer;
    private int offset;

    public ByteStringBuilder() {
        this(0, 1, null);
    }

    public ByteStringBuilder(int i) {
        this.buffer = new byte[i];
    }

    public /* synthetic */ ByteStringBuilder(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i);
    }

    /* JADX INFO: renamed from: getSize, reason: from getter */
    public final int getOffset() {
        return this.offset;
    }

    public final int getCapacity() {
        return this.buffer.length;
    }

    public final ByteString toByteString() {
        if (getOffset() == 0) {
            return ByteStringKt.ByteString();
        }
        if (this.buffer.length == getOffset()) {
            return ByteString.INSTANCE.wrap$kotlinx_io_bytestring(this.buffer);
        }
        return new ByteString(this.buffer, 0, getOffset());
    }

    public final void append(byte b) {
        ensureCapacity(getOffset() + 1);
        byte[] bArr = this.buffer;
        int i = this.offset;
        this.offset = i + 1;
        bArr[i] = b;
    }

    public static /* synthetic */ void append$default(ByteStringBuilder byteStringBuilder, byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length;
        }
        byteStringBuilder.append(bArr, i, i2);
    }

    public final void append(byte[] array, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(array, "array");
        if (startIndex > endIndex) {
            throw new IllegalArgumentException(("startIndex (" + startIndex + ") > endIndex (" + endIndex + ')').toString());
        }
        if (startIndex < 0 || endIndex > array.length) {
            throw new IndexOutOfBoundsException("startIndex (" + startIndex + ") and endIndex (" + endIndex + ") represents an interval out of array's bounds [0.." + array.length + ").");
        }
        ensureCapacity((this.offset + endIndex) - startIndex);
        ArraysKt.copyInto(array, this.buffer, this.offset, startIndex, endIndex);
        this.offset += endIndex - startIndex;
    }

    private final void ensureCapacity(int requiredCapacity) {
        byte[] bArr = this.buffer;
        if (bArr.length >= requiredCapacity) {
            return;
        }
        byte[] bArr2 = new byte[Math.max(bArr.length == 0 ? 16 : (int) (((double) bArr.length) * 1.5d), requiredCapacity)];
        ArraysKt.copyInto$default(this.buffer, bArr2, 0, 0, 0, 14, (Object) null);
        this.buffer = bArr2;
    }
}
