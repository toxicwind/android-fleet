package j$.net;

import j$.util.Objects;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;

/* JADX INFO: loaded from: classes3.dex */
public class URLDecoder {
    static String dfltEncName = URLEncoder.dfltEncName;

    /* JADX INFO: renamed from: j$.net.URLDecoder$0, reason: invalid class name */
    public abstract /* synthetic */ class AnonymousClass0 {
        public static /* synthetic */ int m(CharSequence charSequence, int i, int i2, int i3) {
            return Integer.parseInt(charSequence.subSequence(i, i2).toString(), i3);
        }
    }

    private static boolean isValidHexChar(char c) {
        if ('0' <= c && c <= '9') {
            return true;
        }
        if ('a' > c || c > 'f') {
            return 'A' <= c && c <= 'F';
        }
        return true;
    }

    public static String decode(String str, String str2) throws UnsupportedEncodingException {
        if (str2.isEmpty()) {
            throw new UnsupportedEncodingException("URLDecoder: empty string enc parameter");
        }
        try {
            return decode(str, Charset.forName(str2));
        } catch (IllegalCharsetNameException | UnsupportedCharsetException unused) {
            throw new UnsupportedEncodingException(str2);
        }
    }

    public static String decode(String str, Charset charset) {
        Objects.requireNonNull(charset, "Charset");
        int length = str.length();
        StringBuilder sb = new StringBuilder(length > 500 ? length / 2 : length);
        byte[] bArr = null;
        int i = 0;
        boolean z = false;
        while (i < length) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '%') {
                if (bArr == null) {
                    try {
                        bArr = new byte[(length - i) / 3];
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("URLDecoder: Illegal hex characters in escape (%) pattern - " + e.getMessage());
                    }
                }
                int i2 = 0;
                while (true) {
                    int i3 = i + 2;
                    if (i3 >= length || cCharAt != '%') {
                        break;
                    }
                    int i4 = i + 1;
                    if (!isValidHexChar(str.charAt(i4)) || !isValidHexChar(str.charAt(i3))) {
                        throw new IllegalArgumentException("URLDecoder: Illegal hex characters in escape (%) pattern : " + str.substring(i, i + 3));
                    }
                    int i5 = i + 3;
                    int iM = AnonymousClass0.m(str, i4, i5, 16);
                    if (iM < 0) {
                        throw new IllegalArgumentException("URLDecoder: Illegal hex characters in escape (%) pattern - negative value : " + str.substring(i, i5));
                    }
                    int i6 = i2 + 1;
                    bArr[i2] = (byte) iM;
                    if (i5 < length) {
                        cCharAt = str.charAt(i5);
                    }
                    i2 = i6;
                    i = i5;
                }
                if (i < length && cCharAt == '%') {
                    throw new IllegalArgumentException("URLDecoder: Incomplete trailing escape (%) pattern");
                }
                sb.append(new String(bArr, 0, i2, charset));
            } else if (cCharAt == '+') {
                sb.append(' ');
                i++;
            } else {
                sb.append(cCharAt);
                i++;
            }
            z = true;
        }
        return z ? sb.toString() : str;
    }
}
