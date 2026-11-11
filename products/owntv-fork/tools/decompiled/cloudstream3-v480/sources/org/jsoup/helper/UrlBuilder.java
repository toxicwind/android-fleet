package org.jsoup.helper;

import j$.net.URLDecoder;
import j$.net.URLEncoder;
import java.io.UnsupportedEncodingException;
import java.net.IDN;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import kotlin.text.Typography;
import org.jsoup.Connection;
import org.jsoup.internal.StringUtil;

/* JADX INFO: loaded from: classes8.dex */
final class UrlBuilder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String unsafeCharacters = "<>\"{}|\\^[]`";
    StringBuilder q;
    URL u;

    private static boolean isHex(char c) {
        if (c >= '0' && c <= '9') {
            return true;
        }
        if (c < 'A' || c > 'F') {
            return c >= 'a' && c <= 'f';
        }
        return true;
    }

    UrlBuilder(URL url) {
        this.u = url;
        if (url.getQuery() != null) {
            StringBuilder sbBorrowBuilder = StringUtil.borrowBuilder();
            sbBorrowBuilder.append(this.u.getQuery());
            this.q = sbBorrowBuilder;
        }
    }

    URL build() {
        try {
            URI uri = new URI(this.u.getProtocol(), this.u.getUserInfo(), IDN.toASCII(decodePart(this.u.getHost())), this.u.getPort(), null, null, null);
            StringBuilder sbBorrowBuilder = StringUtil.borrowBuilder();
            sbBorrowBuilder.append(uri.toASCIIString());
            appendToAscii(this.u.getPath(), false, sbBorrowBuilder);
            if (this.q != null) {
                sbBorrowBuilder.append('?');
                appendToAscii(StringUtil.releaseBuilder(this.q), true, sbBorrowBuilder);
            }
            if (this.u.getRef() != null) {
                sbBorrowBuilder.append('#');
                appendToAscii(this.u.getRef(), false, sbBorrowBuilder);
            }
            URL url = new URL(StringUtil.releaseBuilder(sbBorrowBuilder));
            this.u = url;
            return url;
        } catch (UnsupportedEncodingException | MalformedURLException | URISyntaxException unused) {
            return this.u;
        }
    }

    void appendKeyVal(Connection.KeyVal keyVal) throws UnsupportedEncodingException {
        StringBuilder sb = this.q;
        if (sb == null) {
            this.q = StringUtil.borrowBuilder();
        } else {
            sb.append(Typography.amp);
        }
        StringBuilder sb2 = this.q;
        sb2.append(URLEncoder.encode(keyVal.key(), DataUtil.UTF_8.name()));
        sb2.append('=');
        sb2.append(URLEncoder.encode(keyVal.value(), DataUtil.UTF_8.name()));
    }

    private static String decodePart(String str) {
        try {
            return URLDecoder.decode(str, DataUtil.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0056  */
    private static void appendToAscii(String str, boolean z, StringBuilder sb) throws UnsupportedEncodingException {
        int i = 0;
        while (i < str.length()) {
            int iCodePointAt = str.codePointAt(i);
            if (iCodePointAt == 32) {
                sb.append(z ? '+' : "%20");
            } else if (iCodePointAt == 37) {
                if (i < str.length() - 2) {
                    int i2 = i + 1;
                    if (isHex(str.charAt(i2))) {
                        int i3 = i + 2;
                        if (isHex(str.charAt(i3))) {
                            sb.append('%');
                            sb.append(str.charAt(i2));
                            sb.append(str.charAt(i3));
                            i = i3;
                        } else {
                            sb.append("%25");
                        }
                    } else {
                        sb.append("%25");
                    }
                } else {
                    sb.append("%25");
                }
            } else if (iCodePointAt > 127 || unsafeCharacters.indexOf(iCodePointAt) != -1) {
                sb.append(URLEncoder.encode(new String(Character.toChars(iCodePointAt)), DataUtil.UTF_8.name()));
                if (Character.charCount(iCodePointAt) == 2) {
                    i++;
                }
            } else {
                sb.append((char) iCodePointAt);
            }
            i++;
        }
    }
}
