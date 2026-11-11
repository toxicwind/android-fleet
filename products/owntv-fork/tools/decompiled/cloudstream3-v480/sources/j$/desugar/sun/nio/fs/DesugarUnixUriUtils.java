package j$.desugar.sun.nio.fs;

import coil3.util.UtilsKt;
import io.ktor.util.date.GMTDateParser;
import j$.nio.file.FileSystem;
import j$.time.ZoneId;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
abstract class DesugarUnixUriUtils {
    private static final long H_ALPHA;
    private static final long H_ALPHANUM;
    private static final long H_LOWALPHA;
    private static final long H_MARK;
    private static final long H_PATH;
    private static final long H_PCHAR;
    private static final long H_UNRESERVED;
    private static final long H_UPALPHA;
    private static final long L_ALPHANUM;
    private static final long L_DIGIT;
    private static final long L_MARK;
    private static final long L_PATH;
    private static final long L_PCHAR;
    private static final long L_UNRESERVED;
    private static final char[] hexDigits;

    private static boolean match(char c, long j, long j2) {
        if (c < '@') {
            return (j & (1 << c)) != 0;
        }
        return c < 128 && ((1 << (c - 64)) & j2) != 0;
    }

    static DesugarUnixPath fromUri(FileSystem fileSystem, URI uri, String str, String str2) {
        byte bDecode;
        if (!uri.isAbsolute()) {
            ZoneId.AnonymousClass4.m("URI is not absolute");
            return null;
        }
        if (uri.isOpaque()) {
            ZoneId.AnonymousClass4.m("URI is not hierarchical");
            return null;
        }
        String scheme = uri.getScheme();
        if (scheme == null || !scheme.equalsIgnoreCase(UtilsKt.SCHEME_FILE)) {
            ZoneId.AnonymousClass4.m("URI scheme is not \"file\"");
            return null;
        }
        if (uri.getRawAuthority() != null) {
            ZoneId.AnonymousClass4.m("URI has an authority component");
            return null;
        }
        if (uri.getRawFragment() != null) {
            ZoneId.AnonymousClass4.m("URI has a fragment component");
            return null;
        }
        if (uri.getRawQuery() != null) {
            ZoneId.AnonymousClass4.m("URI has a query component");
            return null;
        }
        String rawPath = uri.getRawPath();
        int length = rawPath.length();
        if (length == 0) {
            ZoneId.AnonymousClass4.m("URI path component is empty");
            return null;
        }
        if (rawPath.endsWith("/") && length > 1) {
            length--;
        }
        byte[] bArrCopyOf = new byte[length];
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = i + 1;
            char cCharAt = rawPath.charAt(i);
            if (cCharAt == '%') {
                int i4 = i + 2;
                i += 3;
                bDecode = (byte) ((decode(rawPath.charAt(i3)) << 4) | decode(rawPath.charAt(i4)));
                if (bDecode == 0) {
                    ZoneId.AnonymousClass4.m("Nul character not allowed");
                    return null;
                }
            } else {
                if (cCharAt == 0 || cCharAt >= 128) {
                    ZoneId.AnonymousClass4.m("Bad escape");
                    return null;
                }
                bDecode = (byte) cCharAt;
                i = i3;
            }
            bArrCopyOf[i2] = bDecode;
            i2++;
        }
        if (i2 != length) {
            bArrCopyOf = Arrays.copyOf(bArrCopyOf, i2);
        }
        return new DesugarUnixPath(fileSystem, new String(bArrCopyOf, DesugarUtil.jnuEncoding()), str, str2);
    }

    static URI toUri(DesugarUnixPath desugarUnixPath) {
        byte[] bArrAsByteArray = desugarUnixPath.toAbsolutePath().asByteArray();
        StringBuilder sb = new StringBuilder("file:///");
        for (int i = 1; i < bArrAsByteArray.length; i++) {
            char c = (char) (bArrAsByteArray[i] & 255);
            if (match(c, L_PATH, H_PATH)) {
                sb.append(c);
            } else {
                sb.append('%');
                char[] cArr = hexDigits;
                sb.append(cArr[(c >> 4) & 15]);
                sb.append(cArr[c & 15]);
            }
        }
        if (sb.charAt(sb.length() - 1) != '/' && desugarUnixPath.toFile().isDirectory()) {
            sb.append('/');
        }
        try {
            return new URI(sb.toString());
        } catch (URISyntaxException e) {
            throw new AssertionError(e);
        }
    }

    private static long lowMask(String str) {
        int length = str.length();
        long j = 0;
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt < '@') {
                j |= 1 << cCharAt;
            }
        }
        return j;
    }

    private static long highMask(String str) {
        int length = str.length();
        long j = 0;
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt >= '@' && cCharAt < 128) {
                j |= 1 << (cCharAt - '@');
            }
        }
        return j;
    }

    private static long lowMask(char c, char c2) {
        long j = 0;
        for (int iMax = Math.max(Math.min((int) c, 63), 0); iMax <= Math.max(Math.min((int) c2, 63), 0); iMax++) {
            j |= 1 << iMax;
        }
        return j;
    }

    private static long highMask(char c, char c2) {
        long j = 0;
        for (int iMax = Math.max(Math.min((int) c, 127), 64) - 64; iMax <= Math.max(Math.min((int) c2, 127), 64) - 64; iMax++) {
            j |= 1 << iMax;
        }
        return j;
    }

    private static int decode(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'a' && c <= 'f') {
            return c - 'W';
        }
        if (c < 'A' || c > 'F') {
            throw new AssertionError();
        }
        return c - '7';
    }

    static {
        long jLowMask = lowMask('0', '9');
        L_DIGIT = jLowMask;
        long jHighMask = highMask('A', 'Z');
        H_UPALPHA = jHighMask;
        long jHighMask2 = highMask('a', GMTDateParser.ZONE);
        H_LOWALPHA = jHighMask2;
        long j = jHighMask | jHighMask2;
        H_ALPHA = j;
        L_ALPHANUM = jLowMask;
        H_ALPHANUM = j;
        long jLowMask2 = lowMask("-_.!~*'()");
        L_MARK = jLowMask2;
        long jHighMask3 = highMask("-_.!~*'()");
        H_MARK = jHighMask3;
        long j2 = jLowMask | jLowMask2;
        L_UNRESERVED = j2;
        long j3 = j | jHighMask3;
        H_UNRESERVED = j3;
        long jLowMask3 = j2 | lowMask(":@&=+$,");
        L_PCHAR = jLowMask3;
        long jHighMask4 = j3 | highMask(":@&=+$,");
        H_PCHAR = jHighMask4;
        L_PATH = jLowMask3 | lowMask(";/");
        H_PATH = highMask(";/") | jHighMask4;
        hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    }
}
