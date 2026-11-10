package c.a;

import com.google.common.io.BaseEncoding;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes2.dex */
public final class P {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Charset f840a = Charset.forName("US-ASCII");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final BaseEncoding f841b = C0219ea.f1799b;

    public interface a<T> extends C0219ea.h<T> {
    }

    public static <T> C0219ea.e<T> a(String str, a<T> aVar) {
        boolean z = false;
        if (str != null && !str.isEmpty() && str.charAt(0) == ':') {
            z = true;
        }
        return C0219ea.e.a(str, z, aVar);
    }

    public static byte[][] b(C0219ea c0219ea) {
        int i = c0219ea.f1801d * 2;
        byte[][] bArr = new byte[i][];
        Object[] objArr = c0219ea.f1800c;
        if (objArr instanceof byte[][]) {
            System.arraycopy(objArr, 0, bArr, 0, i);
        } else {
            for (int i2 = 0; i2 < c0219ea.f1801d; i2++) {
                int i3 = i2 * 2;
                bArr[i3] = c0219ea.b(i2);
                bArr[i3 + 1] = c0219ea.d(i2);
            }
        }
        return bArr;
    }

    public static C0219ea a(byte[]... bArr) {
        return new C0219ea(bArr);
    }

    public static int a(C0219ea c0219ea) {
        return c0219ea.f1801d;
    }
}
