package qrcode.internals;

import kotlin.Metadata;

/* JADX INFO: compiled from: QRMath.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bJ\u000e\u0010\n\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bJF\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lqrcode/internals/QRMath;", "", "<init>", "()V", "EXP_TABLE", "", "LOG_TABLE", "glog", "", "n", "gexp", "rectsIntersect", "", "x1", "y1", "w1", "h1", "x2", "y2", "w2", "h2", "qrcode-kotlin_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class QRMath {
    public static final QRMath INSTANCE = new QRMath();
    private static final int[] EXP_TABLE = new int[256];
    private static final int[] LOG_TABLE = new int[256];

    public final boolean rectsIntersect(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2) {
        return w1 + x1 < x2 || x1 > w2 + x2 || h1 + y1 < y2 || y1 > h2 + y2;
    }

    private QRMath() {
    }

    static {
        int i;
        int i2 = 0;
        while (true) {
            if (i2 >= 8) {
                break;
            }
            EXP_TABLE[i2] = 1 << i2;
            i2++;
        }
        for (i = 8; i < 256; i++) {
            int[] iArr = EXP_TABLE;
            iArr[i] = ((iArr[i - 4] ^ iArr[i - 5]) ^ iArr[i - 6]) ^ iArr[i - 8];
        }
        for (int i3 = 0; i3 < 255; i3++) {
            LOG_TABLE[EXP_TABLE[i3]] = i3;
        }
    }

    public final int glog(int n) {
        return LOG_TABLE[n];
    }

    public final int gexp(int n) {
        while (n < 0) {
            n += 255;
        }
        while (n >= 256) {
            n -= 255;
        }
        return EXP_TABLE[n];
    }
}
