package okhttp3.g0;

import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.IDN;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.J;
import okhttp3.K;
import okhttp3.M;
import okhttp3.a0;
import okhttp3.e0;
import okhttp3.internal.http2.C0039b;
import okio.B;
import okio.ByteString;
import okio.D;
import okio.h;
import okio.j;

/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final byte[] f185a;
    public static final String[] b = new String[0];
    public static final e0 c;
    private static final ByteString d;
    private static final ByteString e;
    private static final ByteString f;
    private static final ByteString g;
    private static final ByteString h;
    public static final Charset i;
    private static final Charset j;
    private static final Charset k;
    private static final Charset l;
    private static final Charset m;
    public static final TimeZone n;
    public static final Comparator o;
    private static final Method p;
    private static final Pattern q;

    static {
        byte[] bArr = new byte[0];
        f185a = bArr;
        Method declaredMethod = null;
        c = e0.O(null, bArr);
        a0.c(null, f185a);
        d = ByteString.e("efbbbf");
        e = ByteString.e("feff");
        f = ByteString.e("fffe");
        g = ByteString.e("0000ffff");
        h = ByteString.e("ffff0000");
        i = Charset.forName("UTF-8");
        Charset.forName("ISO-8859-1");
        j = Charset.forName("UTF-16BE");
        k = Charset.forName("UTF-16LE");
        l = Charset.forName("UTF-32BE");
        m = Charset.forName("UTF-32LE");
        n = TimeZone.getTimeZone("GMT");
        o = new c();
        try {
            declaredMethod = Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class);
        } catch (Exception unused) {
        }
        p = declaredMethod;
        q = Pattern.compile("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");
    }

    public static boolean A(AssertionError assertionError) {
        return (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) ? false : true;
    }

    public static boolean B(Comparator comparator, String[] strArr, String[] strArr2) {
        if (strArr != null && strArr2 != null && strArr.length != 0 && strArr2.length != 0) {
            for (String str : strArr) {
                for (String str2 : strArr2) {
                    if (comparator.compare(str, str2) == 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static X509TrustManager C() {
        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((KeyStore) null);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            if (trustManagers.length == 1 && (trustManagers[0] instanceof X509TrustManager)) {
                return (X509TrustManager) trustManagers[0];
            }
            throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
        } catch (GeneralSecurityException e2) {
            throw b("No System TLS", e2);
        }
    }

    public static boolean D(B b2, int i2, TimeUnit timeUnit) {
        long jNanoTime = System.nanoTime();
        long jC = b2.b().e() ? b2.b().c() - jNanoTime : Long.MAX_VALUE;
        b2.b().d(Math.min(jC, timeUnit.toNanos(i2)) + jNanoTime);
        try {
            h hVar = new h();
            while (b2.K(hVar, 8192L) != -1) {
                hVar.M();
            }
            D dB = b2.b();
            if (jC == Long.MAX_VALUE) {
                dB.a();
            } else {
                dB.d(jNanoTime + jC);
            }
            return true;
        } catch (InterruptedIOException unused) {
            D dB2 = b2.b();
            if (jC == Long.MAX_VALUE) {
                dB2.a();
            } else {
                dB2.d(jNanoTime + jC);
            }
            return false;
        } catch (Throwable th) {
            D dB3 = b2.b();
            if (jC == Long.MAX_VALUE) {
                dB3.a();
            } else {
                dB3.d(jNanoTime + jC);
            }
            throw th;
        }
    }

    public static int E(String str, int i2, int i3) {
        while (i2 < i3) {
            char cCharAt = str.charAt(i2);
            if (cCharAt != '\t' && cCharAt != '\n' && cCharAt != '\f' && cCharAt != '\r' && cCharAt != ' ') {
                return i2;
            }
            i2++;
        }
        return i3;
    }

    public static int F(String str, int i2, int i3) {
        for (int i4 = i3 - 1; i4 >= i2; i4--) {
            char cCharAt = str.charAt(i4);
            if (cCharAt != '\t' && cCharAt != '\n' && cCharAt != '\f' && cCharAt != '\r' && cCharAt != ' ') {
                return i4 + 1;
            }
        }
        return i2;
    }

    public static ThreadFactory G(String str, boolean z) {
        return new d(str, z);
    }

    public static K H(List list) {
        J j2 = new J();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            C0039b c0039b = (C0039b) it.next();
            a.f182a.b(j2, c0039b.f236a.w(), c0039b.b.w());
        }
        return j2.d();
    }

    public static String I(String str, int i2, int i3) {
        int iE = E(str, i2, i3);
        return str.substring(iE, F(str, iE, i3));
    }

    public static boolean J(String str) {
        return q.matcher(str).matches();
    }

    public static void a(Throwable th, Throwable th2) {
        Method method = p;
        if (method != null) {
            try {
                method.invoke(th, th2);
            } catch (IllegalAccessException | InvocationTargetException unused) {
            }
        }
    }

    public static AssertionError b(String str, Exception exc) {
        AssertionError assertionError = new AssertionError(str);
        try {
            assertionError.initCause(exc);
        } catch (IllegalStateException unused) {
        }
        return assertionError;
    }

    public static Charset c(j jVar, Charset charset) {
        if (jVar.y(0L, d)) {
            jVar.m(d.r());
            return i;
        }
        if (jVar.y(0L, e)) {
            jVar.m(e.r());
            return j;
        }
        if (jVar.y(0L, f)) {
            jVar.m(f.r());
            return k;
        }
        if (jVar.y(0L, g)) {
            jVar.m(g.r());
            return l;
        }
        if (!jVar.y(0L, h)) {
            return charset;
        }
        jVar.m(h.r());
        return m;
    }

    public static String d(String str) {
        if (!str.contains(":")) {
            try {
                String lowerCase = IDN.toASCII(str).toLowerCase(Locale.US);
                if (lowerCase.isEmpty() || j(lowerCase)) {
                    return null;
                }
                return lowerCase;
            } catch (IllegalArgumentException unused) {
                return null;
            }
        }
        InetAddress inetAddressM = (str.startsWith("[") && str.endsWith("]")) ? m(str, 1, str.length() - 1) : m(str, 0, str.length());
        if (inetAddressM == null) {
            return null;
        }
        byte[] address = inetAddressM.getAddress();
        if (address.length == 16) {
            return y(address);
        }
        throw new AssertionError("Invalid IPv6 address: '" + str + "'");
    }

    public static int e(String str, long j2, TimeUnit timeUnit) {
        if (j2 < 0) {
            throw new IllegalArgumentException(str + " < 0");
        }
        if (timeUnit == null) {
            throw new NullPointerException("unit == null");
        }
        long millis = timeUnit.toMillis(j2);
        if (millis > 2147483647L) {
            throw new IllegalArgumentException(str + " too large.");
        }
        if (millis != 0 || j2 <= 0) {
            return (int) millis;
        }
        throw new IllegalArgumentException(str + " too small.");
    }

    public static void f(long j2, long j3, long j4) {
        if ((j3 | j4) < 0 || j3 > j2 || j2 - j3 < j4) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public static void g(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e2) {
                throw e2;
            } catch (Exception unused) {
            }
        }
    }

    public static void h(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (AssertionError e2) {
                if (!A(e2)) {
                    throw e2;
                }
            } catch (RuntimeException e3) {
                throw e3;
            } catch (Exception unused) {
            }
        }
    }

    public static String[] i(String[] strArr, String str) {
        int length = strArr.length + 1;
        String[] strArr2 = new String[length];
        System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
        strArr2[length - 1] = str;
        return strArr2;
    }

    private static boolean j(String str) {
        for (int i2 = 0; i2 < str.length(); i2++) {
            char cCharAt = str.charAt(i2);
            if (cCharAt <= 31 || cCharAt >= 127 || " #%/:?@[\\]".indexOf(cCharAt) != -1) {
                return true;
            }
        }
        return false;
    }

    public static int k(char c2) {
        if (c2 >= '0' && c2 <= '9') {
            return c2 - '0';
        }
        char c3 = 'a';
        if (c2 < 'a' || c2 > 'f') {
            c3 = 'A';
            if (c2 < 'A' || c2 > 'F') {
                return -1;
            }
        }
        return (c2 - c3) + 10;
    }

    private static boolean l(String str, int i2, int i3, byte[] bArr, int i4) {
        int i5 = i4;
        while (i2 < i3) {
            if (i5 == bArr.length) {
                return false;
            }
            if (i5 != i4) {
                if (str.charAt(i2) != '.') {
                    return false;
                }
                i2++;
            }
            int i6 = i2;
            int i7 = 0;
            while (i6 < i3) {
                char cCharAt = str.charAt(i6);
                if (cCharAt < '0' || cCharAt > '9') {
                    break;
                }
                if ((i7 == 0 && i2 != i6) || (i7 = ((i7 * 10) + cCharAt) - 48) > 255) {
                    return false;
                }
                i6++;
            }
            if (i6 - i2 == 0) {
                return false;
            }
            bArr[i5] = (byte) i7;
            i5++;
            i2 = i6;
        }
        return i5 == i4 + 4;
    }

    /* JADX WARN: Code duplicated, block: B:31:0x004f  */
    /* JADX WARN: Code duplicated, block: B:34:0x005a A[LOOP:1: B:30:0x004d->B:34:0x005a, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:59:0x0060 A[EDGE_INSN: B:59:0x0060->B:35:0x0060 BREAK  A[LOOP:1: B:30:0x004d->B:34:0x005a], SYNTHETIC] */
    private static InetAddress m(String str, int i2, int i3) {
        int i4;
        int i5;
        int iK;
        byte[] bArr = new byte[16];
        int i6 = -1;
        int i7 = -1;
        int i8 = 0;
        while (i2 < i3) {
            if (i8 == 16) {
                return null;
            }
            int i9 = i2 + 2;
            if (i9 <= i3 && str.regionMatches(i2, "::", 0, 2)) {
                if (i6 != -1) {
                    return null;
                }
                i8 += 2;
                i6 = i8;
                if (i9 == i3) {
                    break;
                }
                i7 = i9;
                i4 = 0;
                i2 = i7;
                while (i2 < i3) {
                    iK = k(str.charAt(i2));
                    if (iK == -1) {
                        break;
                        break;
                    }
                    i4 = (i4 << 4) + iK;
                    i2++;
                }
                i5 = i2 - i7;
                if (i5 != 0) {
                }
                return null;
            }
            if (i8 != 0) {
                if (!str.regionMatches(i2, ":", 0, 1)) {
                    if (!str.regionMatches(i2, ".", 0, 1) || !l(str, i7, i3, bArr, i8 - 2)) {
                        return null;
                    }
                    i8 += 2;
                    break;
                }
                i2++;
            }
            i7 = i2;
            i4 = 0;
            i2 = i7;
            while (i2 < i3) {
                iK = k(str.charAt(i2));
                if (iK == -1) {
                    break;
                }
                i4 = (i4 << 4) + iK;
                i2++;
            }
            i5 = i2 - i7;
            if (i5 != 0 || i5 > 4) {
                return null;
            }
            int i10 = i8 + 1;
            bArr[i8] = (byte) ((i4 >>> 8) & 255);
            i8 = i10 + 1;
            bArr[i10] = (byte) (i4 & 255);
        }
        if (i8 != 16) {
            if (i6 == -1) {
                return null;
            }
            int i11 = i8 - i6;
            System.arraycopy(bArr, i6, bArr, 16 - i11, i11);
            Arrays.fill(bArr, i6, (16 - i8) + i6, (byte) 0);
        }
        try {
            return InetAddress.getByAddress(bArr);
        } catch (UnknownHostException unused) {
            throw new AssertionError();
        }
    }

    public static int n(String str, int i2, int i3, char c2) {
        while (i2 < i3) {
            if (str.charAt(i2) == c2) {
                return i2;
            }
            i2++;
        }
        return i3;
    }

    public static int o(String str, int i2, int i3, String str2) {
        while (i2 < i3) {
            if (str2.indexOf(str.charAt(i2)) != -1) {
                return i2;
            }
            i2++;
        }
        return i3;
    }

    public static boolean p(B b2, int i2, TimeUnit timeUnit) {
        try {
            return D(b2, i2, timeUnit);
        } catch (IOException unused) {
            return false;
        }
    }

    public static boolean q(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static String r(String str, Object... objArr) {
        return String.format(Locale.US, str, objArr);
    }

    public static String s(M m2, boolean z) {
        String strL;
        if (m2.l().contains(":")) {
            strL = "[" + m2.l() + "]";
        } else {
            strL = m2.l();
        }
        if (!z && m2.w() == M.d(m2.B())) {
            return strL;
        }
        return strL + ":" + m2.w();
    }

    public static List t(List list) {
        return Collections.unmodifiableList(new ArrayList(list));
    }

    public static List u(Object... objArr) {
        return Collections.unmodifiableList(Arrays.asList((Object[]) objArr.clone()));
    }

    public static Map v(Map map) {
        return map.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(new LinkedHashMap(map));
    }

    public static int w(Comparator comparator, String[] strArr, String str) {
        int length = strArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (comparator.compare(strArr[i2], str) == 0) {
                return i2;
            }
        }
        return -1;
    }

    public static int x(String str) {
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            char cCharAt = str.charAt(i2);
            if (cCharAt <= 31 || cCharAt >= 127) {
                return i2;
            }
        }
        return -1;
    }

    private static String y(byte[] bArr) {
        int i2 = 0;
        int i3 = -1;
        int i4 = 0;
        int i5 = 0;
        while (i4 < bArr.length) {
            int i6 = i4;
            while (i6 < 16 && bArr[i6] == 0 && bArr[i6 + 1] == 0) {
                i6 += 2;
            }
            int i7 = i6 - i4;
            if (i7 > i5 && i7 >= 4) {
                i3 = i4;
                i5 = i7;
            }
            i4 = i6 + 2;
        }
        h hVar = new h();
        while (i2 < bArr.length) {
            if (i2 == i3) {
                hVar.k0(58);
                i2 += i5;
                if (i2 == 16) {
                    hVar.k0(58);
                }
            } else {
                if (i2 > 0) {
                    hVar.k0(58);
                }
                hVar.m0(((bArr[i2] & 255) << 8) | (bArr[i2 + 1] & 255));
                i2 += 2;
            }
        }
        return hVar.Y();
    }

    public static String[] z(Comparator comparator, String[] strArr, String[] strArr2) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            for (String str2 : strArr2) {
                if (comparator.compare(str, str2) == 0) {
                    arrayList.add(str);
                    break;
                }
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }
}
