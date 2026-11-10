package c.a.c.a;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c.a.c.a.a[] f1623a = {c.a.c.a.a.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256, c.a.c.a.a.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256, c.a.c.a.a.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256, c.a.c.a.a.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA, c.a.c.a.a.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA, c.a.c.a.a.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA, c.a.c.a.a.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA, c.a.c.a.a.TLS_DHE_RSA_WITH_AES_128_CBC_SHA, c.a.c.a.a.TLS_DHE_DSS_WITH_AES_128_CBC_SHA, c.a.c.a.a.TLS_DHE_RSA_WITH_AES_256_CBC_SHA, c.a.c.a.a.TLS_RSA_WITH_AES_128_GCM_SHA256, c.a.c.a.a.TLS_RSA_WITH_AES_128_CBC_SHA, c.a.c.a.a.TLS_RSA_WITH_AES_256_CBC_SHA, c.a.c.a.a.TLS_RSA_WITH_3DES_EDE_CBC_SHA};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final c f1624b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f1625c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String[] f1626d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String[] f1627e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f1628f;

    static {
        a aVar = new a(true);
        aVar.a(f1623a);
        aVar.a(l.TLS_1_2, l.TLS_1_1, l.TLS_1_0);
        aVar.a(true);
        f1624b = aVar.a();
        a aVar2 = new a(f1624b);
        aVar2.a(l.TLS_1_0);
        aVar2.a(true);
        aVar2.a();
        new a(false).a();
    }

    public /* synthetic */ c(a aVar, b bVar) {
        this.f1625c = aVar.f1629a;
        this.f1626d = aVar.f1630b;
        this.f1627e = aVar.f1631c;
        this.f1628f = aVar.f1632d;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof c)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        c cVar = (c) obj;
        boolean z = this.f1625c;
        if (z != cVar.f1625c) {
            return false;
        }
        return !z || (Arrays.equals(this.f1626d, cVar.f1626d) && Arrays.equals(this.f1627e, cVar.f1627e) && this.f1628f == cVar.f1628f);
    }

    public int hashCode() {
        if (!this.f1625c) {
            return 17;
        }
        return ((Arrays.hashCode(this.f1627e) + ((Arrays.hashCode(this.f1626d) + 527) * 31)) * 31) + (!this.f1628f ? 1 : 0);
    }

    public String toString() {
        List listA;
        if (!this.f1625c) {
            return "ConnectionSpec()";
        }
        String[] strArr = this.f1626d;
        int i = 0;
        if (strArr == null) {
            listA = null;
        } else {
            c.a.c.a.a[] aVarArr = new c.a.c.a.a[strArr.length];
            int i2 = 0;
            while (true) {
                String[] strArr2 = this.f1626d;
                if (i2 >= strArr2.length) {
                    break;
                }
                aVarArr[i2] = c.a.c.a.a.a(strArr2[i2]);
                i2++;
            }
            listA = m.a(aVarArr);
        }
        StringBuilder sbB = b.a.a.a.a.b("ConnectionSpec(cipherSuites=", listA == null ? "[use default]" : listA.toString(), ", tlsVersions=");
        l[] lVarArr = new l[this.f1627e.length];
        while (true) {
            String[] strArr3 = this.f1627e;
            if (i >= strArr3.length) {
                sbB.append(m.a(lVarArr));
                sbB.append(", supportsTlsExtensions=");
                sbB.append(this.f1628f);
                sbB.append(")");
                return sbB.toString();
            }
            lVarArr[i] = l.a(strArr3[i]);
            i++;
        }
    }

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1629a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String[] f1630b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String[] f1631c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1632d;

        public a(boolean z) {
            this.f1629a = z;
        }

        public a a(c.a.c.a.a... aVarArr) {
            if (!this.f1629a) {
                throw new IllegalStateException("no cipher suites for cleartext connections");
            }
            String[] strArr = new String[aVarArr.length];
            for (int i = 0; i < aVarArr.length; i++) {
                strArr[i] = aVarArr[i].Ta;
            }
            this.f1630b = strArr;
            return this;
        }

        public a(c cVar) {
            this.f1629a = cVar.f1625c;
            this.f1630b = cVar.f1626d;
            this.f1631c = cVar.f1627e;
            this.f1632d = cVar.f1628f;
        }

        public a a(l... lVarArr) {
            if (this.f1629a) {
                if (lVarArr.length != 0) {
                    String[] strArr = new String[lVarArr.length];
                    for (int i = 0; i < lVarArr.length; i++) {
                        strArr[i] = lVarArr[i].f1672f;
                    }
                    this.f1631c = strArr;
                    return this;
                }
                throw new IllegalArgumentException("At least one TlsVersion is required");
            }
            throw new IllegalStateException("no TLS versions for cleartext connections");
        }

        public a a(boolean z) {
            if (this.f1629a) {
                this.f1632d = z;
                return this;
            }
            throw new IllegalStateException("no TLS extensions for cleartext connections");
        }

        public c a() {
            return new c(this, null);
        }
    }
}
