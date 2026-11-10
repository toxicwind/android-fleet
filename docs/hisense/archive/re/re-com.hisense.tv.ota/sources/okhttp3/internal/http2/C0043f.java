package okhttp3.internal.http2;

import java.io.IOException;
import okio.ByteString;

/* JADX INFO: renamed from: okhttp3.internal.http2.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0043f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final ByteString f240a = ByteString.h("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");
    private static final String[] b = {"DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", "WINDOW_UPDATE", "CONTINUATION"};
    static final String[] c = new String[64];
    static final String[] d = new String[256];

    static {
        int i = 0;
        int i2 = 0;
        while (true) {
            String[] strArr = d;
            if (i2 >= strArr.length) {
                break;
            }
            strArr[i2] = okhttp3.g0.e.r("%8s", Integer.toBinaryString(i2)).replace(' ', '0');
            i2++;
        }
        String[] strArr2 = c;
        strArr2[0] = "";
        strArr2[1] = "END_STREAM";
        int[] iArr = {1};
        strArr2[8] = "PADDED";
        for (int i3 = 0; i3 < 1; i3++) {
            int i4 = iArr[i3];
            c[i4 | 8] = c[i4] + "|PADDED";
        }
        String[] strArr3 = c;
        strArr3[4] = "END_HEADERS";
        strArr3[32] = "PRIORITY";
        strArr3[36] = "END_HEADERS|PRIORITY";
        int[] iArr2 = {4, 32, 36};
        for (int i5 = 0; i5 < 3; i5++) {
            int i6 = iArr2[i5];
            for (int i7 = 0; i7 < 1; i7++) {
                int i8 = iArr[i7];
                int i9 = i8 | i6;
                c[i9] = c[i8] + '|' + c[i6];
                c[i9 | 8] = c[i8] + '|' + c[i6] + "|PADDED";
            }
        }
        while (true) {
            String[] strArr4 = c;
            if (i >= strArr4.length) {
                return;
            }
            if (strArr4[i] == null) {
                strArr4[i] = d[i];
            }
            i++;
        }
    }

    private C0043f() {
    }

    static String a(byte b2, byte b3) {
        if (b3 == 0) {
            return "";
        }
        if (b2 != 2 && b2 != 3) {
            if (b2 == 4 || b2 == 6) {
                return b3 == 1 ? "ACK" : d[b3];
            }
            if (b2 != 7 && b2 != 8) {
                String[] strArr = c;
                String str = b3 < strArr.length ? strArr[b3] : d[b3];
                if (b2 != 5 || (b3 & 4) == 0) {
                    return (b2 != 0 || (b3 & 32) == 0) ? str : str.replace("PRIORITY", "COMPRESSED");
                }
                return str.replace("HEADERS", "PUSH_PROMISE");
            }
        }
        return d[b3];
    }

    static String b(boolean z, int i, int i2, byte b2, byte b3) {
        String[] strArr = b;
        String strR = b2 < strArr.length ? strArr[b2] : okhttp3.g0.e.r("0x%02x", Byte.valueOf(b2));
        String strA = a(b2, b3);
        Object[] objArr = new Object[5];
        objArr[0] = z ? "<<" : ">>";
        objArr[1] = Integer.valueOf(i);
        objArr[2] = Integer.valueOf(i2);
        objArr[3] = strR;
        objArr[4] = strA;
        return okhttp3.g0.e.r("%s 0x%08x %5d %-13s %s", objArr);
    }

    static IllegalArgumentException c(String str, Object... objArr) {
        throw new IllegalArgumentException(okhttp3.g0.e.r(str, objArr));
    }

    static IOException d(String str, Object... objArr) throws IOException {
        throw new IOException(okhttp3.g0.e.r(str, objArr));
    }
}
