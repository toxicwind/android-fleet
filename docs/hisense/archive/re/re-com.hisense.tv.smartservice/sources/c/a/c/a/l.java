package c.a.c.a;

/* JADX INFO: loaded from: classes2.dex */
public enum l {
    TLS_1_2("TLSv1.2"),
    TLS_1_1("TLSv1.1"),
    TLS_1_0("TLSv1"),
    SSL_3_0("SSLv3");


    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f1672f;

    l(String str) {
        this.f1672f = str;
    }

    public static l a(String str) {
        if ("TLSv1.2".equals(str)) {
            return TLS_1_2;
        }
        if ("TLSv1.1".equals(str)) {
            return TLS_1_1;
        }
        if ("TLSv1".equals(str)) {
            return TLS_1_0;
        }
        if ("SSLv3".equals(str)) {
            return SSL_3_0;
        }
        throw new IllegalArgumentException(b.a.a.a.a.a("Unexpected TLS version: ", str));
    }
}
