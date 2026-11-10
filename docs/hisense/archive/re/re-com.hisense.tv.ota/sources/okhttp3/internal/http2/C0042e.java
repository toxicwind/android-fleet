package okhttp3.internal.http2;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import okio.ByteString;

/* JADX INFO: renamed from: okhttp3.internal.http2.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0042e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final C0039b[] f239a = {new C0039b(C0039b.i, ""), new C0039b(C0039b.f, "GET"), new C0039b(C0039b.f, "POST"), new C0039b(C0039b.g, "/"), new C0039b(C0039b.g, "/index.html"), new C0039b(C0039b.h, "http"), new C0039b(C0039b.h, "https"), new C0039b(C0039b.e, "200"), new C0039b(C0039b.e, "204"), new C0039b(C0039b.e, "206"), new C0039b(C0039b.e, "304"), new C0039b(C0039b.e, "400"), new C0039b(C0039b.e, "404"), new C0039b(C0039b.e, "500"), new C0039b("accept-charset", ""), new C0039b("accept-encoding", "gzip, deflate"), new C0039b("accept-language", ""), new C0039b("accept-ranges", ""), new C0039b("accept", ""), new C0039b("access-control-allow-origin", ""), new C0039b("age", ""), new C0039b("allow", ""), new C0039b("authorization", ""), new C0039b("cache-control", ""), new C0039b("content-disposition", ""), new C0039b("content-encoding", ""), new C0039b("content-language", ""), new C0039b("content-length", ""), new C0039b("content-location", ""), new C0039b("content-range", ""), new C0039b("content-type", ""), new C0039b("cookie", ""), new C0039b("date", ""), new C0039b("etag", ""), new C0039b("expect", ""), new C0039b("expires", ""), new C0039b("from", ""), new C0039b("host", ""), new C0039b("if-match", ""), new C0039b("if-modified-since", ""), new C0039b("if-none-match", ""), new C0039b("if-range", ""), new C0039b("if-unmodified-since", ""), new C0039b("last-modified", ""), new C0039b("link", ""), new C0039b("location", ""), new C0039b("max-forwards", ""), new C0039b("proxy-authenticate", ""), new C0039b("proxy-authorization", ""), new C0039b("range", ""), new C0039b("referer", ""), new C0039b("refresh", ""), new C0039b("retry-after", ""), new C0039b("server", ""), new C0039b("set-cookie", ""), new C0039b("strict-transport-security", ""), new C0039b("transfer-encoding", ""), new C0039b("user-agent", ""), new C0039b("vary", ""), new C0039b("via", ""), new C0039b("www-authenticate", "")};
    static final Map b = b();

    static ByteString a(ByteString byteString) throws IOException {
        int iR = byteString.r();
        for (int i = 0; i < iR; i++) {
            byte bI = byteString.i(i);
            if (bI >= 65 && bI <= 90) {
                throw new IOException("PROTOCOL_ERROR response malformed: mixed case name: " + byteString.w());
            }
        }
        return byteString;
    }

    private static Map b() {
        LinkedHashMap linkedHashMap = new LinkedHashMap(f239a.length);
        int i = 0;
        while (true) {
            C0039b[] c0039bArr = f239a;
            if (i >= c0039bArr.length) {
                return Collections.unmodifiableMap(linkedHashMap);
            }
            if (!linkedHashMap.containsKey(c0039bArr[i].f236a)) {
                linkedHashMap.put(f239a[i].f236a, Integer.valueOf(i));
            }
            i++;
        }
    }
}
