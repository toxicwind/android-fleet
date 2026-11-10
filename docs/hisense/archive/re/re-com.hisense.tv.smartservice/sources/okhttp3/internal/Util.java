package okhttp3.internal;

import b.a.a.a.a;
import com.google.common.primitives.UnsignedLong;
import com.google.firebase.installations.Utils;
import d.C;
import d.g;
import d.i;
import d.j;
import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.IDN;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.internal.http2.Header;

/* JADX INFO: loaded from: classes2.dex */
public final class Util {
    public static final Pattern VERIFY_AS_IP_ADDRESS;
    public static final Method addSuppressedExceptionMethod;
    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    public static final String[] EMPTY_STRING_ARRAY = new String[0];
    public static final ResponseBody EMPTY_RESPONSE = ResponseBody.create((MediaType) null, EMPTY_BYTE_ARRAY);
    public static final RequestBody EMPTY_REQUEST = RequestBody.create((MediaType) null, EMPTY_BYTE_ARRAY);
    public static final j UTF_8_BOM = j.b("efbbbf");
    public static final j UTF_16_BE_BOM = j.b("feff");
    public static final j UTF_16_LE_BOM = j.b("fffe");
    public static final j UTF_32_BE_BOM = j.b("0000ffff");
    public static final j UTF_32_LE_BOM = j.b("ffff0000");
    public static final Charset UTF_8 = Charset.forName("UTF-8");
    public static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    public static final Charset UTF_16_BE = Charset.forName("UTF-16BE");
    public static final Charset UTF_16_LE = Charset.forName("UTF-16LE");
    public static final Charset UTF_32_BE = Charset.forName("UTF-32BE");
    public static final Charset UTF_32_LE = Charset.forName("UTF-32LE");
    public static final TimeZone UTC = TimeZone.getTimeZone("GMT");
    public static final Comparator<String> NATURAL_ORDER = new Comparator<String>() { // from class: okhttp3.internal.Util.1
        @Override // java.util.Comparator
        public int compare(String str, String str2) {
            return str.compareTo(str2);
        }
    };

    static {
        Method declaredMethod = null;
        try {
            declaredMethod = Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class);
        } catch (Exception unused) {
        }
        addSuppressedExceptionMethod = declaredMethod;
        VERIFY_AS_IP_ADDRESS = Pattern.compile("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");
    }

    public static void addSuppressedIfPossible(Throwable th, Throwable th2) {
        Method method = addSuppressedExceptionMethod;
        if (method != null) {
            try {
                method.invoke(th, th2);
            } catch (IllegalAccessException | InvocationTargetException unused) {
            }
        }
    }

    public static AssertionError assertionError(String str, Exception exc) {
        AssertionError assertionError = new AssertionError(str);
        try {
            assertionError.initCause(exc);
        } catch (IllegalStateException unused) {
        }
        return assertionError;
    }

    public static Charset bomAwareCharset(i iVar, Charset charset) {
        if (iVar.a(0L, UTF_8_BOM)) {
            iVar.skip(UTF_8_BOM.g());
            return UTF_8;
        }
        if (iVar.a(0L, UTF_16_BE_BOM)) {
            iVar.skip(UTF_16_BE_BOM.g());
            return UTF_16_BE;
        }
        if (iVar.a(0L, UTF_16_LE_BOM)) {
            iVar.skip(UTF_16_LE_BOM.g());
            return UTF_16_LE;
        }
        if (iVar.a(0L, UTF_32_BE_BOM)) {
            iVar.skip(UTF_32_BE_BOM.g());
            return UTF_32_BE;
        }
        if (!iVar.a(0L, UTF_32_LE_BOM)) {
            return charset;
        }
        iVar.skip(UTF_32_LE_BOM.g());
        return UTF_32_LE;
    }

    public static String canonicalizeHost(String str) {
        if (!str.contains(Utils.APP_ID_IDENTIFICATION_SUBSTRING)) {
            try {
                String lowerCase = IDN.toASCII(str).toLowerCase(Locale.US);
                if (lowerCase.isEmpty() || containsInvalidHostnameAsciiCodes(lowerCase)) {
                    return null;
                }
                return lowerCase;
            } catch (IllegalArgumentException unused) {
                return null;
            }
        }
        InetAddress inetAddressDecodeIpv6 = (str.startsWith("[") && str.endsWith("]")) ? decodeIpv6(str, 1, str.length() - 1) : decodeIpv6(str, 0, str.length());
        if (inetAddressDecodeIpv6 == null) {
            return null;
        }
        byte[] address = inetAddressDecodeIpv6.getAddress();
        if (address.length == 16) {
            return inet6AddressToAscii(address);
        }
        throw new AssertionError(a.a("Invalid IPv6 address: '", str, "'"));
    }

    public static int checkDuration(String str, long j, TimeUnit timeUnit) {
        if (j < 0) {
            throw new IllegalArgumentException(a.a(str, " < 0"));
        }
        if (timeUnit == null) {
            throw new NullPointerException("unit == null");
        }
        long millis = timeUnit.toMillis(j);
        if (millis > 2147483647L) {
            throw new IllegalArgumentException(a.a(str, " too large."));
        }
        if (millis != 0 || j <= 0) {
            return (int) millis;
        }
        throw new IllegalArgumentException(a.a(str, " too small."));
    }

    public static void checkOffsetAndCount(long j, long j2, long j3) {
        if ((j2 | j3) < 0 || j2 > j || j - j2 < j3) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e2) {
                throw e2;
            } catch (Exception unused) {
            }
        }
    }

    public static String[] concat(String[] strArr, String str) {
        String[] strArr2 = new String[strArr.length + 1];
        System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
        strArr2[strArr2.length - 1] = str;
        return strArr2;
    }

    public static boolean containsInvalidHostnameAsciiCodes(String str) {
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt <= 31 || cCharAt >= 127 || " #%/:?@[\\]".indexOf(cCharAt) != -1) {
                return true;
            }
        }
        return false;
    }

    public static int decodeHexDigit(char c2) {
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

    public static boolean decodeIpv4Suffix(String str, int i, int i2, byte[] bArr, int i3) {
        int i4 = i3;
        while (i < i2) {
            if (i4 == bArr.length) {
                return false;
            }
            if (i4 != i3) {
                if (str.charAt(i) != '.') {
                    return false;
                }
                i++;
            }
            int i5 = i;
            int i6 = 0;
            while (i5 < i2) {
                char cCharAt = str.charAt(i5);
                if (cCharAt < '0' || cCharAt > '9') {
                    break;
                }
                if ((i6 == 0 && i != i5) || (i6 = ((i6 * 10) + cCharAt) - 48) > 255) {
                    return false;
                }
                i5++;
            }
            if (i5 - i == 0) {
                return false;
            }
            bArr[i4] = (byte) i6;
            i4++;
            i = i5;
        }
        return i4 == i3 + 4;
    }

    /* JADX WARN: Code duplicated, block: B:32:0x0051  */
    /* JADX WARN: Code duplicated, block: B:35:0x005c A[LOOP:1: B:31:0x004f->B:35:0x005c, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:61:0x0062 A[EDGE_INSN: B:61:0x0062->B:36:0x0062 BREAK  A[LOOP:1: B:31:0x004f->B:35:0x005c], SYNTHETIC] */
    public static InetAddress decodeIpv6(String str, int i, int i2) {
        int i3;
        int i4;
        int iDecodeHexDigit;
        byte[] bArr = new byte[16];
        int i5 = -1;
        int i6 = -1;
        int i7 = 0;
        while (i < i2) {
            if (i7 == bArr.length) {
                return null;
            }
            int i8 = i + 2;
            if (i8 <= i2 && str.regionMatches(i, "::", 0, 2)) {
                if (i5 != -1) {
                    return null;
                }
                i7 += 2;
                if (i8 == i2) {
                    i5 = i7;
                    break;
                }
                i5 = i7;
                i6 = i8;
                i3 = 0;
                i = i6;
                while (i < i2) {
                    iDecodeHexDigit = decodeHexDigit(str.charAt(i));
                    if (iDecodeHexDigit == -1) {
                        break;
                        break;
                    }
                    i3 = (i3 << 4) + iDecodeHexDigit;
                    i++;
                }
                i4 = i - i6;
                if (i4 != 0) {
                }
                return null;
            }
            if (i7 != 0) {
                if (!str.regionMatches(i, Utils.APP_ID_IDENTIFICATION_SUBSTRING, 0, 1)) {
                    if (!str.regionMatches(i, ".", 0, 1) || !decodeIpv4Suffix(str, i6, i2, bArr, i7 - 2)) {
                        return null;
                    }
                    i7 += 2;
                    break;
                }
                i++;
            }
            i6 = i;
            i3 = 0;
            i = i6;
            while (i < i2) {
                iDecodeHexDigit = decodeHexDigit(str.charAt(i));
                if (iDecodeHexDigit == -1) {
                    break;
                }
                i3 = (i3 << 4) + iDecodeHexDigit;
                i++;
            }
            i4 = i - i6;
            if (i4 != 0 || i4 > 4) {
                return null;
            }
            int i9 = i7 + 1;
            bArr[i7] = (byte) ((i3 >>> 8) & 255);
            i7 = i9 + 1;
            bArr[i9] = (byte) (i3 & 255);
        }
        if (i7 != bArr.length) {
            if (i5 == -1) {
                return null;
            }
            int i10 = i7 - i5;
            System.arraycopy(bArr, i5, bArr, bArr.length - i10, i10);
            Arrays.fill(bArr, i5, (bArr.length - i7) + i5, (byte) 0);
        }
        try {
            return InetAddress.getByAddress(bArr);
        } catch (UnknownHostException unused) {
            throw new AssertionError();
        }
    }

    public static int delimiterOffset(String str, int i, int i2, String str2) {
        while (i < i2) {
            if (str2.indexOf(str.charAt(i)) != -1) {
                return i;
            }
            i++;
        }
        return i2;
    }

    public static boolean discard(C c2, int i, TimeUnit timeUnit) {
        try {
            return skipAll(c2, i, timeUnit);
        } catch (IOException unused) {
            return false;
        }
    }

    public static boolean equal(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static String format(String str, Object... objArr) {
        return String.format(Locale.US, str, objArr);
    }

    public static String hostHeader(HttpUrl httpUrl, boolean z) {
        String strHost;
        if (httpUrl.host().contains(Utils.APP_ID_IDENTIFICATION_SUBSTRING)) {
            StringBuilder sbA = a.a("[");
            sbA.append(httpUrl.host());
            sbA.append("]");
            strHost = sbA.toString();
        } else {
            strHost = httpUrl.host();
        }
        if (!z && httpUrl.port() == HttpUrl.defaultPort(httpUrl.scheme())) {
            return strHost;
        }
        StringBuilder sbB = a.b(strHost, Utils.APP_ID_IDENTIFICATION_SUBSTRING);
        sbB.append(httpUrl.port());
        return sbB.toString();
    }

    public static <T> List<T> immutableList(List<T> list) {
        return Collections.unmodifiableList(new ArrayList(list));
    }

    public static <K, V> Map<K, V> immutableMap(Map<K, V> map) {
        return map.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(new LinkedHashMap(map));
    }

    public static int indexOf(Comparator<String> comparator, String[] strArr, String str) {
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            if (comparator.compare(strArr[i], str) == 0) {
                return i;
            }
        }
        return -1;
    }

    public static int indexOfControlOrNonAscii(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt <= 31 || cCharAt >= 127) {
                return i;
            }
        }
        return -1;
    }

    public static String inet6AddressToAscii(byte[] bArr) {
        int i = 0;
        int i2 = 0;
        int i3 = -1;
        int i4 = 0;
        while (i4 < bArr.length) {
            int i5 = i4;
            while (i5 < 16 && bArr[i5] == 0 && bArr[i5 + 1] == 0) {
                i5 += 2;
            }
            int i6 = i5 - i4;
            if (i6 > i2 && i6 >= 4) {
                i3 = i4;
                i2 = i6;
            }
            i4 = i5 + 2;
        }
        g gVar = new g();
        while (i < bArr.length) {
            if (i == i3) {
                gVar.writeByte(58);
                i += i2;
                if (i == 16) {
                    gVar.writeByte(58);
                }
            } else {
                if (i > 0) {
                    gVar.writeByte(58);
                }
                gVar.f(((bArr[i] & 255) << 8) | (bArr[i + 1] & 255));
                i += 2;
            }
        }
        return gVar.o();
    }

    public static String[] intersect(Comparator<? super String> comparator, String[] strArr, String[] strArr2) {
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

    public static boolean isAndroidGetsocknameError(AssertionError assertionError) {
        return (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) ? false : true;
    }

    public static boolean nonEmptyIntersection(Comparator<String> comparator, String[] strArr, String[] strArr2) {
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

    public static X509TrustManager platformTrustManager() {
        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((KeyStore) null);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            if (trustManagers.length == 1 && (trustManagers[0] instanceof X509TrustManager)) {
                return (X509TrustManager) trustManagers[0];
            }
            throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
        } catch (GeneralSecurityException e2) {
            throw assertionError("No System TLS", e2);
        }
    }

    public static boolean skipAll(C c2, int i, TimeUnit timeUnit) {
        long jNanoTime = System.nanoTime();
        long jDeadlineNanoTime = c2.timeout().hasDeadline() ? c2.timeout().deadlineNanoTime() - jNanoTime : Long.MAX_VALUE;
        c2.timeout().deadlineNanoTime(Math.min(jDeadlineNanoTime, timeUnit.toNanos(i)) + jNanoTime);
        try {
            g gVar = new g();
            while (c2.read(gVar, 8192L) != -1) {
                gVar.clear();
            }
            if (jDeadlineNanoTime == UnsignedLong.UNSIGNED_MASK) {
                c2.timeout().clearDeadline();
            } else {
                c2.timeout().deadlineNanoTime(jNanoTime + jDeadlineNanoTime);
            }
            return true;
        } catch (InterruptedIOException unused) {
            if (jDeadlineNanoTime == UnsignedLong.UNSIGNED_MASK) {
                c2.timeout().clearDeadline();
            } else {
                c2.timeout().deadlineNanoTime(jNanoTime + jDeadlineNanoTime);
            }
            return false;
        } catch (Throwable th) {
            if (jDeadlineNanoTime == UnsignedLong.UNSIGNED_MASK) {
                c2.timeout().clearDeadline();
            } else {
                c2.timeout().deadlineNanoTime(jNanoTime + jDeadlineNanoTime);
            }
            throw th;
        }
    }

    public static int skipLeadingAsciiWhitespace(String str, int i, int i2) {
        while (i < i2) {
            char cCharAt = str.charAt(i);
            if (cCharAt != '\t' && cCharAt != '\n' && cCharAt != '\f' && cCharAt != '\r' && cCharAt != ' ') {
                return i;
            }
            i++;
        }
        return i2;
    }

    public static int skipTrailingAsciiWhitespace(String str, int i, int i2) {
        for (int i3 = i2 - 1; i3 >= i; i3--) {
            char cCharAt = str.charAt(i3);
            if (cCharAt != '\t' && cCharAt != '\n' && cCharAt != '\f' && cCharAt != '\r' && cCharAt != ' ') {
                return i3 + 1;
            }
        }
        return i;
    }

    public static ThreadFactory threadFactory(final String str, final boolean z) {
        return new ThreadFactory() { // from class: okhttp3.internal.Util.2
            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable, str);
                thread.setDaemon(z);
                return thread;
            }
        };
    }

    public static Headers toHeaders(List<Header> list) {
        Headers.Builder builder = new Headers.Builder();
        for (Header header : list) {
            Internal.instance.addLenient(builder, header.name.j(), header.value.j());
        }
        return builder.build();
    }

    public static String trimSubstring(String str, int i, int i2) {
        int iSkipLeadingAsciiWhitespace = skipLeadingAsciiWhitespace(str, i, i2);
        return str.substring(iSkipLeadingAsciiWhitespace, skipTrailingAsciiWhitespace(str, iSkipLeadingAsciiWhitespace, i2));
    }

    public static boolean verifyAsIpAddress(String str) {
        return VERIFY_AS_IP_ADDRESS.matcher(str).matches();
    }

    public static int delimiterOffset(String str, int i, int i2, char c2) {
        while (i < i2) {
            if (str.charAt(i) == c2) {
                return i;
            }
            i++;
        }
        return i2;
    }

    public static <T> List<T> immutableList(T... tArr) {
        return Collections.unmodifiableList(Arrays.asList((Object[]) tArr.clone()));
    }

    public static void closeQuietly(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (AssertionError e2) {
                if (!isAndroidGetsocknameError(e2)) {
                    throw e2;
                }
            } catch (RuntimeException e3) {
                throw e3;
            } catch (Exception unused) {
            }
        }
    }

    public static void closeQuietly(ServerSocket serverSocket) {
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (RuntimeException e2) {
                throw e2;
            } catch (Exception unused) {
            }
        }
    }
}
