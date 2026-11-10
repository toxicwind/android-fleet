package okhttp3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: renamed from: okhttp3.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0058s {
    static final Comparator b = new r();
    private static final Map c = new LinkedHashMap();
    public static final C0058s d;
    public static final C0058s e;
    public static final C0058s f;
    public static final C0058s g;
    public static final C0058s h;
    public static final C0058s i;
    public static final C0058s j;
    public static final C0058s k;
    public static final C0058s l;
    public static final C0058s m;
    public static final C0058s n;
    public static final C0058s o;
    public static final C0058s p;
    public static final C0058s q;
    public static final C0058s r;
    public static final C0058s s;
    public static final C0058s t;
    public static final C0058s u;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final String f254a;

    static {
        c("SSL_RSA_WITH_NULL_MD5", 1);
        c("SSL_RSA_WITH_NULL_SHA", 2);
        c("SSL_RSA_EXPORT_WITH_RC4_40_MD5", 3);
        c("SSL_RSA_WITH_RC4_128_MD5", 4);
        c("SSL_RSA_WITH_RC4_128_SHA", 5);
        c("SSL_RSA_EXPORT_WITH_DES40_CBC_SHA", 8);
        c("SSL_RSA_WITH_DES_CBC_SHA", 9);
        d = c("SSL_RSA_WITH_3DES_EDE_CBC_SHA", 10);
        c("SSL_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA", 17);
        c("SSL_DHE_DSS_WITH_DES_CBC_SHA", 18);
        c("SSL_DHE_DSS_WITH_3DES_EDE_CBC_SHA", 19);
        c("SSL_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA", 20);
        c("SSL_DHE_RSA_WITH_DES_CBC_SHA", 21);
        c("SSL_DHE_RSA_WITH_3DES_EDE_CBC_SHA", 22);
        c("SSL_DH_anon_EXPORT_WITH_RC4_40_MD5", 23);
        c("SSL_DH_anon_WITH_RC4_128_MD5", 24);
        c("SSL_DH_anon_EXPORT_WITH_DES40_CBC_SHA", 25);
        c("SSL_DH_anon_WITH_DES_CBC_SHA", 26);
        c("SSL_DH_anon_WITH_3DES_EDE_CBC_SHA", 27);
        c("TLS_KRB5_WITH_DES_CBC_SHA", 30);
        c("TLS_KRB5_WITH_3DES_EDE_CBC_SHA", 31);
        c("TLS_KRB5_WITH_RC4_128_SHA", 32);
        c("TLS_KRB5_WITH_DES_CBC_MD5", 34);
        c("TLS_KRB5_WITH_3DES_EDE_CBC_MD5", 35);
        c("TLS_KRB5_WITH_RC4_128_MD5", 36);
        c("TLS_KRB5_EXPORT_WITH_DES_CBC_40_SHA", 38);
        c("TLS_KRB5_EXPORT_WITH_RC4_40_SHA", 40);
        c("TLS_KRB5_EXPORT_WITH_DES_CBC_40_MD5", 41);
        c("TLS_KRB5_EXPORT_WITH_RC4_40_MD5", 43);
        e = c("TLS_RSA_WITH_AES_128_CBC_SHA", 47);
        c("TLS_DHE_DSS_WITH_AES_128_CBC_SHA", 50);
        c("TLS_DHE_RSA_WITH_AES_128_CBC_SHA", 51);
        c("TLS_DH_anon_WITH_AES_128_CBC_SHA", 52);
        f = c("TLS_RSA_WITH_AES_256_CBC_SHA", 53);
        c("TLS_DHE_DSS_WITH_AES_256_CBC_SHA", 56);
        c("TLS_DHE_RSA_WITH_AES_256_CBC_SHA", 57);
        c("TLS_DH_anon_WITH_AES_256_CBC_SHA", 58);
        c("TLS_RSA_WITH_NULL_SHA256", 59);
        c("TLS_RSA_WITH_AES_128_CBC_SHA256", 60);
        c("TLS_RSA_WITH_AES_256_CBC_SHA256", 61);
        c("TLS_DHE_DSS_WITH_AES_128_CBC_SHA256", 64);
        c("TLS_RSA_WITH_CAMELLIA_128_CBC_SHA", 65);
        c("TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA", 68);
        c("TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA", 69);
        c("TLS_DHE_RSA_WITH_AES_128_CBC_SHA256", 103);
        c("TLS_DHE_DSS_WITH_AES_256_CBC_SHA256", 106);
        c("TLS_DHE_RSA_WITH_AES_256_CBC_SHA256", 107);
        c("TLS_DH_anon_WITH_AES_128_CBC_SHA256", 108);
        c("TLS_DH_anon_WITH_AES_256_CBC_SHA256", 109);
        c("TLS_RSA_WITH_CAMELLIA_256_CBC_SHA", 132);
        c("TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA", 135);
        c("TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA", 136);
        c("TLS_PSK_WITH_RC4_128_SHA", 138);
        c("TLS_PSK_WITH_3DES_EDE_CBC_SHA", 139);
        c("TLS_PSK_WITH_AES_128_CBC_SHA", 140);
        c("TLS_PSK_WITH_AES_256_CBC_SHA", 141);
        c("TLS_RSA_WITH_SEED_CBC_SHA", 150);
        g = c("TLS_RSA_WITH_AES_128_GCM_SHA256", 156);
        h = c("TLS_RSA_WITH_AES_256_GCM_SHA384", 157);
        c("TLS_DHE_RSA_WITH_AES_128_GCM_SHA256", 158);
        c("TLS_DHE_RSA_WITH_AES_256_GCM_SHA384", 159);
        c("TLS_DHE_DSS_WITH_AES_128_GCM_SHA256", 162);
        c("TLS_DHE_DSS_WITH_AES_256_GCM_SHA384", 163);
        c("TLS_DH_anon_WITH_AES_128_GCM_SHA256", 166);
        c("TLS_DH_anon_WITH_AES_256_GCM_SHA384", 167);
        c("TLS_EMPTY_RENEGOTIATION_INFO_SCSV", 255);
        c("TLS_FALLBACK_SCSV", 22016);
        c("TLS_ECDH_ECDSA_WITH_NULL_SHA", 49153);
        c("TLS_ECDH_ECDSA_WITH_RC4_128_SHA", 49154);
        c("TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA", 49155);
        c("TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA", 49156);
        c("TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA", 49157);
        c("TLS_ECDHE_ECDSA_WITH_NULL_SHA", 49158);
        c("TLS_ECDHE_ECDSA_WITH_RC4_128_SHA", 49159);
        c("TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA", 49160);
        c("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA", 49161);
        c("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA", 49162);
        c("TLS_ECDH_RSA_WITH_NULL_SHA", 49163);
        c("TLS_ECDH_RSA_WITH_RC4_128_SHA", 49164);
        c("TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA", 49165);
        c("TLS_ECDH_RSA_WITH_AES_128_CBC_SHA", 49166);
        c("TLS_ECDH_RSA_WITH_AES_256_CBC_SHA", 49167);
        c("TLS_ECDHE_RSA_WITH_NULL_SHA", 49168);
        c("TLS_ECDHE_RSA_WITH_RC4_128_SHA", 49169);
        c("TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA", 49170);
        i = c("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA", 49171);
        j = c("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA", 49172);
        c("TLS_ECDH_anon_WITH_NULL_SHA", 49173);
        c("TLS_ECDH_anon_WITH_RC4_128_SHA", 49174);
        c("TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA", 49175);
        c("TLS_ECDH_anon_WITH_AES_128_CBC_SHA", 49176);
        c("TLS_ECDH_anon_WITH_AES_256_CBC_SHA", 49177);
        c("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256", 49187);
        c("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384", 49188);
        c("TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256", 49189);
        c("TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384", 49190);
        c("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256", 49191);
        c("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384", 49192);
        c("TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256", 49193);
        c("TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384", 49194);
        k = c("TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256", 49195);
        l = c("TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384", 49196);
        c("TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256", 49197);
        c("TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384", 49198);
        m = c("TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256", 49199);
        n = c("TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384", 49200);
        c("TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256", 49201);
        c("TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384", 49202);
        c("TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA", 49205);
        c("TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA", 49206);
        o = c("TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256", 52392);
        p = c("TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256", 52393);
        c("TLS_DHE_RSA_WITH_CHACHA20_POLY1305_SHA256", 52394);
        c("TLS_ECDHE_PSK_WITH_CHACHA20_POLY1305_SHA256", 52396);
        q = c("TLS_AES_128_GCM_SHA256", 4865);
        r = c("TLS_AES_256_GCM_SHA384", 4866);
        s = c("TLS_CHACHA20_POLY1305_SHA256", 4867);
        t = c("TLS_AES_128_CCM_SHA256", 4868);
        u = c("TLS_AES_256_CCM_8_SHA256", 4869);
    }

    private C0058s(String str) {
        if (str == null) {
            throw null;
        }
        this.f254a = str;
    }

    public static synchronized C0058s a(String str) {
        C0058s c0058s;
        c0058s = (C0058s) c.get(str);
        if (c0058s == null) {
            c0058s = (C0058s) c.get(e(str));
            if (c0058s == null) {
                c0058s = new C0058s(str);
            }
            c.put(str, c0058s);
        }
        return c0058s;
    }

    static List b(String... strArr) {
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            arrayList.add(a(str));
        }
        return Collections.unmodifiableList(arrayList);
    }

    private static C0058s c(String str, int i2) {
        C0058s c0058s = new C0058s(str);
        c.put(str, c0058s);
        return c0058s;
    }

    private static String e(String str) {
        if (str.startsWith("TLS_")) {
            return "SSL_" + str.substring(4);
        }
        if (!str.startsWith("SSL_")) {
            return str;
        }
        return "TLS_" + str.substring(4);
    }

    public String d() {
        return this.f254a;
    }

    public String toString() {
        return this.f254a;
    }
}
