package qrcode.internals;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: BitBuffer.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0011\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\tH\u0082\u0002J\u0016\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\tJ\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u000fH\u0007J\b\u0010\u0016\u001a\u00020\u0017H\u0016R\u001e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001e\u0010\n\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\t@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lqrcode/internals/BitBuffer;", "", "<init>", "()V", ES6Iterator.VALUE_PROPERTY, "", "buffer", "getBuffer", "()[I", "", "lengthInBits", "getLengthInBits", "()I", "increments", "get", "", "index", "put", "", "num", "length", "bit", "toString", "", "qrcode-kotlin_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class BitBuffer {
    private final int increments = 32;
    private int[] buffer = new int[32];
    private int lengthInBits = 0;

    public final int[] getBuffer() {
        return this.buffer;
    }

    public final int getLengthInBits() {
        return this.lengthInBits;
    }

    private final boolean get(int index) {
        return ((this.buffer[index / 8] >>> (7 - (index % 8))) & 1) == 1;
    }

    public final void put(int num, int length) {
        for (int i = 0; i < length; i++) {
            boolean z = true;
            if (((num >>> ((length - i) - 1)) & 1) != 1) {
                z = false;
            }
            put(z);
        }
    }

    public final void put(boolean bit) {
        int i = this.lengthInBits;
        int[] iArr = this.buffer;
        if (i == iArr.length * 8) {
            int[] iArrCopyOf = Arrays.copyOf(iArr, iArr.length + this.increments);
            Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(...)");
            this.buffer = iArrCopyOf;
        }
        if (bit) {
            int[] iArr2 = this.buffer;
            int i2 = this.lengthInBits;
            iArr2[i2 / 8] = (128 >>> (i2 % 8)) | iArr2[i2 / 8];
        }
        this.lengthInBits++;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = this.lengthInBits;
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(get(i2) ? '1' : '0');
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }
}
