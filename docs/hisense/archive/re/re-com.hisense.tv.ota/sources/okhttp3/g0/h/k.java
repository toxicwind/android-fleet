package okhttp3.g0.h;

import java.net.Proxy;
import okhttp3.M;
import okhttp3.Y;

/* JADX INFO: loaded from: classes.dex */
public final class k {
    public static String a(Y y, Proxy.Type type) {
        StringBuilder sb = new StringBuilder();
        sb.append(y.g());
        sb.append(' ');
        boolean zB = b(y, type);
        M mK = y.k();
        if (zB) {
            sb.append(mK);
        } else {
            sb.append(c(mK));
        }
        sb.append(" HTTP/1.1");
        return sb.toString();
    }

    private static boolean b(Y y, Proxy.Type type) {
        return !y.f() && type == Proxy.Type.HTTP;
    }

    public static String c(M m) {
        String strG = m.g();
        String strI = m.i();
        if (strI == null) {
            return strG;
        }
        return strG + '?' + strI;
    }
}
