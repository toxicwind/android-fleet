package qrcode.internals;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.exoplayer.upstream.CmcdData;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: Polynomial.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\f\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J0\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0005H\u0002J\u0011\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0005H\u0086\u0002J\u0006\u0010\u0014\u001a\u00020\u0005J\u000e\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0000J\u000e\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0000R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0018"}, d2 = {"Lqrcode/internals/Polynomial;", "", "num", "", "shift", "", "<init>", "([II)V", "data", "getData", "()[I", "arraycopy", "", TypedValues.TransitionType.S_FROM, "fromPos", TypedValues.TransitionType.S_TO, "toPos", "length", "get", CmcdData.OBJECT_TYPE_INIT_SEGMENT, "len", "multiply", "other", "mod", "qrcode-kotlin_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class Polynomial {
    private final int[] data;

    public Polynomial(int[] num, int i) {
        Intrinsics.checkNotNullParameter(num, "num");
        int length = num.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                i2 = -1;
                break;
            } else if (num[i2] != 0) {
                break;
            } else {
                i2++;
            }
        }
        int iCoerceAtLeast = RangesKt.coerceAtLeast(i2, 0);
        int length2 = (num.length - iCoerceAtLeast) + i;
        int[] iArr = new int[length2];
        for (int i3 = 0; i3 < length2; i3++) {
            iArr[i3] = 0;
        }
        this.data = iArr;
        arraycopy(num, iCoerceAtLeast, iArr, 0, num.length - iCoerceAtLeast);
    }

    public /* synthetic */ Polynomial(int[] iArr, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(iArr, (i2 & 2) != 0 ? 0 : i);
    }

    public final int[] getData() {
        return this.data;
    }

    private final void arraycopy(int[] from, int fromPos, int[] to, int toPos, int length) {
        for (int i = 0; i < length; i++) {
            to[toPos + i] = from[fromPos + i];
        }
    }

    public final int get(int i) {
        return this.data[i];
    }

    public final int len() {
        return this.data.length;
    }

    public final Polynomial multiply(Polynomial other) {
        Intrinsics.checkNotNullParameter(other, "other");
        int iLen = (len() + other.len()) - 1;
        int[] iArr = new int[iLen];
        int i = 0;
        for (int i2 = 0; i2 < iLen; i2++) {
            iArr[i2] = 0;
        }
        int iLen2 = len();
        for (int i3 = 0; i3 < iLen2; i3++) {
            int iLen3 = other.len();
            for (int i4 = 0; i4 < iLen3; i4++) {
                int i5 = i3 + i4;
                iArr[i5] = iArr[i5] ^ QRMath.INSTANCE.gexp(QRMath.INSTANCE.glog(get(i3)) + QRMath.INSTANCE.glog(other.get(i4)));
            }
        }
        return new Polynomial(iArr, i, 2, null);
    }

    public final Polynomial mod(Polynomial other) {
        Intrinsics.checkNotNullParameter(other, "other");
        if (len() - other.len() < 0) {
            return this;
        }
        int i = 0;
        int iGlog = QRMath.INSTANCE.glog(get(0)) - QRMath.INSTANCE.glog(other.get(0));
        int[] iArr = this.data;
        int[] iArrCopyOf = Arrays.copyOf(iArr, iArr.length);
        Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(...)");
        int[] iArr2 = other.data;
        int length = iArr2.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            iArrCopyOf[i3] = QRMath.INSTANCE.gexp(QRMath.INSTANCE.glog(iArr2[i2]) + iGlog) ^ iArrCopyOf[i3];
            i2++;
            i3++;
        }
        return new Polynomial(iArrCopyOf, i, 2, null).mod(other);
    }
}
