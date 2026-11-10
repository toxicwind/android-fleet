package okhttp3;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.net.ssl.SSLPeerUnverifiedException;
import okio.ByteString;

/* JADX INFO: renamed from: okhttp3.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0057q {
    public static final C0057q c = new C0055o().a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Set f253a;
    private final okhttp3.g0.n.c b;

    C0057q(Set set, okhttp3.g0.n.c cVar) {
        this.f253a = set;
        this.b = cVar;
    }

    public static String c(Certificate certificate) {
        if (!(certificate instanceof X509Certificate)) {
            throw new IllegalArgumentException("Certificate pinning requires X509 certificates");
        }
        return "sha256/" + e((X509Certificate) certificate).a();
    }

    static ByteString d(X509Certificate x509Certificate) {
        return ByteString.l(x509Certificate.getPublicKey().getEncoded()).p();
    }

    static ByteString e(X509Certificate x509Certificate) {
        return ByteString.l(x509Certificate.getPublicKey().getEncoded()).q();
    }

    public void a(String str, List list) throws SSLPeerUnverifiedException {
        List listB = b(str);
        if (listB.isEmpty()) {
            return;
        }
        okhttp3.g0.n.c cVar = this.b;
        if (cVar != null) {
            list = cVar.a(list, str);
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            X509Certificate x509Certificate = (X509Certificate) list.get(i);
            int size2 = listB.size();
            ByteString byteStringE = null;
            ByteString byteStringD = null;
            for (int i2 = 0; i2 < size2; i2++) {
                C0056p c0056p = (C0056p) listB.get(i2);
                if (c0056p.c.equals("sha256/")) {
                    if (byteStringE == null) {
                        byteStringE = e(x509Certificate);
                    }
                    if (c0056p.d.equals(byteStringE)) {
                        return;
                    }
                } else {
                    if (!c0056p.c.equals("sha1/")) {
                        throw new AssertionError("unsupported hashAlgorithm: " + c0056p.c);
                    }
                    if (byteStringD == null) {
                        byteStringD = d(x509Certificate);
                    }
                    if (c0056p.d.equals(byteStringD)) {
                        return;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Certificate pinning failure!");
        sb.append("\n  Peer certificate chain:");
        int size3 = list.size();
        for (int i3 = 0; i3 < size3; i3++) {
            X509Certificate x509Certificate2 = (X509Certificate) list.get(i3);
            sb.append("\n    ");
            sb.append(c(x509Certificate2));
            sb.append(": ");
            sb.append(x509Certificate2.getSubjectDN().getName());
        }
        sb.append("\n  Pinned certificates for ");
        sb.append(str);
        sb.append(":");
        int size4 = listB.size();
        for (int i4 = 0; i4 < size4; i4++) {
            C0056p c0056p2 = (C0056p) listB.get(i4);
            sb.append("\n    ");
            sb.append(c0056p2);
        }
        throw new SSLPeerUnverifiedException(sb.toString());
    }

    List b(String str) {
        List listEmptyList = Collections.emptyList();
        for (C0056p c0056p : this.f253a) {
            if (c0056p.a(str)) {
                if (listEmptyList.isEmpty()) {
                    listEmptyList = new ArrayList();
                }
                listEmptyList.add(c0056p);
            }
        }
        return listEmptyList;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C0057q) {
            C0057q c0057q = (C0057q) obj;
            if (okhttp3.g0.e.q(this.b, c0057q.b) && this.f253a.equals(c0057q.f253a)) {
                return true;
            }
        }
        return false;
    }

    C0057q f(okhttp3.g0.n.c cVar) {
        return okhttp3.g0.e.q(this.b, cVar) ? this : new C0057q(this.f253a, cVar);
    }

    public int hashCode() {
        okhttp3.g0.n.c cVar = this.b;
        return ((cVar != null ? cVar.hashCode() : 0) * 31) + this.f253a.hashCode();
    }
}
