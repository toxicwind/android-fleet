package okhttp3.internal.http;

import com.google.common.base.Ascii;
import java.net.Proxy;
import okhttp3.HttpUrl;
import okhttp3.Request;

/* JADX INFO: loaded from: classes2.dex */
public final class RequestLine {
    public static String get(Request request, Proxy.Type type) {
        StringBuilder sb = new StringBuilder();
        sb.append(request.method());
        sb.append(Ascii.CASE_MASK);
        if (includeAuthorityInRequestLine(request, type)) {
            sb.append(request.url());
        } else {
            sb.append(requestPath(request.url()));
        }
        sb.append(" HTTP/1.1");
        return sb.toString();
    }

    public static boolean includeAuthorityInRequestLine(Request request, Proxy.Type type) {
        return !request.isHttps() && type == Proxy.Type.HTTP;
    }

    public static String requestPath(HttpUrl httpUrl) {
        String strEncodedPath = httpUrl.encodedPath();
        String strEncodedQuery = httpUrl.encodedQuery();
        if (strEncodedQuery == null) {
            return strEncodedPath;
        }
        return strEncodedPath + '?' + strEncodedQuery;
    }
}
