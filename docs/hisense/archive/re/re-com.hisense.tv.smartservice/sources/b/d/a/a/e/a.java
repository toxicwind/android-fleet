package b.d.a.a.e;

import com.mediatek.twoworlds.tv.MtkTvScanDvbsBase;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f194a = {8000, 8000, MtkTvScanDvbsBase.EXCHANGE_TYPE_SB_GET, MtkTvScanDvbsBase.EXCHANGE_TYPE_SB_GET};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f195b = {4000, 4000, 200, 200};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final byte[][] f196c = new byte[4][];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final char[][] f197d = new char[4][];

    public final byte[] a(int i) {
        int i2 = f194a[i];
        if (i2 <= 0) {
            i2 = 0;
        }
        byte[][] bArr = this.f196c;
        byte[] bArr2 = bArr[i];
        if (bArr2 == null || bArr2.length < i2) {
            return new byte[i2];
        }
        bArr[i] = null;
        return bArr2;
    }

    public char[] a(int i, int i2) {
        int i3 = f195b[i];
        if (i2 < i3) {
            i2 = i3;
        }
        char[][] cArr = this.f197d;
        char[] cArr2 = cArr[i];
        if (cArr2 != null && cArr2.length >= i2) {
            cArr[i] = null;
            return cArr2;
        }
        return new char[i2];
    }
}
