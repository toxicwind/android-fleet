package androidx.work;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: Data_.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000P\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0018\n\u0000\n\u0002\u0010\u0005\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\t\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\u0007\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u0006\n\u0002\u0010\u0013\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\u001a\u001b\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0002¢\u0006\u0002\u0010\u0005\u001a\u001b\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00060\u00012\u0006\u0010\u0003\u001a\u00020\u0007H\u0002¢\u0006\u0002\u0010\b\u001a\u001b\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\t0\u00012\u0006\u0010\u0003\u001a\u00020\nH\u0002¢\u0006\u0002\u0010\u000b\u001a\u001b\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\f0\u00012\u0006\u0010\u0003\u001a\u00020\rH\u0002¢\u0006\u0002\u0010\u000e\u001a\u001b\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00012\u0006\u0010\u0003\u001a\u00020\u0010H\u0002¢\u0006\u0002\u0010\u0011\u001a\u001b\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00120\u00012\u0006\u0010\u0003\u001a\u00020\u0013H\u0002¢\u0006\u0002\u0010\u0014\"\u0013\u0010\u0015\u001a\u00070\u0016¢\u0006\u0002\b\u0017X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"convertPrimitiveArray", "", "", ES6Iterator.VALUE_PROPERTY, "", "([Z)[Ljava/lang/Boolean;", "", "", "([B)[Ljava/lang/Byte;", "", "", "([I)[Ljava/lang/Integer;", "", "", "([J)[Ljava/lang/Long;", "", "", "([F)[Ljava/lang/Float;", "", "", "([D)[Ljava/lang/Double;", "TAG", "", "Lorg/jspecify/annotations/NonNull;", "work-runtime_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class Data_Kt {
    private static final String TAG;

    /* JADX INFO: Access modifiers changed from: private */
    public static final Boolean[] convertPrimitiveArray(boolean[] zArr) {
        int length = zArr.length;
        Boolean[] boolArr = new Boolean[length];
        for (int i = 0; i < length; i++) {
            boolArr[i] = Boolean.valueOf(zArr[i]);
        }
        return boolArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Byte[] convertPrimitiveArray(byte[] bArr) {
        int length = bArr.length;
        Byte[] bArr2 = new Byte[length];
        for (int i = 0; i < length; i++) {
            bArr2[i] = Byte.valueOf(bArr[i]);
        }
        return bArr2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Integer[] convertPrimitiveArray(int[] iArr) {
        int length = iArr.length;
        Integer[] numArr = new Integer[length];
        for (int i = 0; i < length; i++) {
            numArr[i] = Integer.valueOf(iArr[i]);
        }
        return numArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Long[] convertPrimitiveArray(long[] jArr) {
        int length = jArr.length;
        Long[] lArr = new Long[length];
        for (int i = 0; i < length; i++) {
            lArr[i] = Long.valueOf(jArr[i]);
        }
        return lArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Float[] convertPrimitiveArray(float[] fArr) {
        int length = fArr.length;
        Float[] fArr2 = new Float[length];
        for (int i = 0; i < length; i++) {
            fArr2[i] = Float.valueOf(fArr[i]);
        }
        return fArr2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Double[] convertPrimitiveArray(double[] dArr) {
        int length = dArr.length;
        Double[] dArr2 = new Double[length];
        for (int i = 0; i < length; i++) {
            dArr2[i] = Double.valueOf(dArr[i]);
        }
        return dArr2;
    }

    static {
        String strTagWithPrefix = Logger.tagWithPrefix("Data");
        Intrinsics.checkNotNullExpressionValue(strTagWithPrefix, "tagWithPrefix(...)");
        TAG = strTagWithPrefix;
    }
}
