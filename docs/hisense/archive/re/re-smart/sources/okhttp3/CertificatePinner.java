package okhttp3;

import b.a.a.a.a;
import com.google.firebase.installations.Utils;
import d.j;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.net.ssl.SSLPeerUnverifiedException;
import okhttp3.internal.Util;
import okhttp3.internal.tls.CertificateChainCleaner;

/* JADX INFO: loaded from: classes2.dex */
public final class CertificatePinner {
    public static final CertificatePinner DEFAULT = new Builder().build();
    public final CertificateChainCleaner certificateChainCleaner;
    public final Set<Pin> pins;

    public static final class Builder {
        public final List<Pin> pins = new ArrayList();

        public Builder add(String str, String... strArr) {
            if (str == null) {
                throw new NullPointerException("pattern == null");
            }
            for (String str2 : strArr) {
                this.pins.add(new Pin(str, str2));
            }
            return this;
        }

        public CertificatePinner build() {
            return new CertificatePinner(new LinkedHashSet(this.pins), null);
        }
    }

    static final class Pin {
        public static final String WILDCARD = "*.";
        public final String canonicalHostname;
        public final j hash;
        public final String hashAlgorithm;
        public final String pattern;

        public Pin(String str, String str2) {
            String strHost;
            this.pattern = str;
            if (str.startsWith(WILDCARD)) {
                StringBuilder sbA = a.a("http://");
                sbA.append(str.substring(2));
                strHost = HttpUrl.get(sbA.toString()).host();
            } else {
                strHost = HttpUrl.get("http://" + str).host();
            }
            this.canonicalHostname = strHost;
            if (str2.startsWith("sha1/")) {
                this.hashAlgorithm = "sha1/";
                this.hash = j.a(str2.substring(5));
            } else {
                if (!str2.startsWith("sha256/")) {
                    throw new IllegalArgumentException(a.a("pins must start with 'sha256/' or 'sha1/': ", str2));
                }
                this.hashAlgorithm = "sha256/";
                this.hash = j.a(str2.substring(7));
            }
            if (this.hash == null) {
                throw new IllegalArgumentException(a.a("pins must be base64: ", str2));
            }
        }

        public boolean equals(Object obj) {
            if (obj instanceof Pin) {
                Pin pin = (Pin) obj;
                if (this.pattern.equals(pin.pattern) && this.hashAlgorithm.equals(pin.hashAlgorithm) && this.hash.equals(pin.hash)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            return this.hash.hashCode() + ((this.hashAlgorithm.hashCode() + ((this.pattern.hashCode() + 527) * 31)) * 31);
        }

        public boolean matches(String str) {
            if (!this.pattern.startsWith(WILDCARD)) {
                return str.equals(this.canonicalHostname);
            }
            int iIndexOf = str.indexOf(46);
            if ((str.length() - iIndexOf) - 1 == this.canonicalHostname.length()) {
                String str2 = this.canonicalHostname;
                if (str.regionMatches(false, iIndexOf + 1, str2, 0, str2.length())) {
                    return true;
                }
            }
            return false;
        }

        public String toString() {
            return this.hashAlgorithm + this.hash.a();
        }
    }

    public CertificatePinner(Set<Pin> set, CertificateChainCleaner certificateChainCleaner) {
        this.pins = set;
        this.certificateChainCleaner = certificateChainCleaner;
    }

    public static String pin(Certificate certificate) {
        if (!(certificate instanceof X509Certificate)) {
            throw new IllegalArgumentException("Certificate pinning requires X509 certificates");
        }
        StringBuilder sbA = a.a("sha256/");
        sbA.append(sha256((X509Certificate) certificate).a());
        return sbA.toString();
    }

    public static j sha1(X509Certificate x509Certificate) {
        return j.a(x509Certificate.getPublicKey().getEncoded()).e();
    }

    public static j sha256(X509Certificate x509Certificate) {
        return j.a(x509Certificate.getPublicKey().getEncoded()).f();
    }

    public void check(String str, List<Certificate> list) {
        List<Pin> listFindMatchingPins = findMatchingPins(str);
        if (listFindMatchingPins.isEmpty()) {
            return;
        }
        CertificateChainCleaner certificateChainCleaner = this.certificateChainCleaner;
        if (certificateChainCleaner != null) {
            list = certificateChainCleaner.clean(list, str);
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            X509Certificate x509Certificate = (X509Certificate) list.get(i);
            int size2 = listFindMatchingPins.size();
            j jVarSha256 = null;
            j jVarSha1 = null;
            for (int i2 = 0; i2 < size2; i2++) {
                Pin pin = listFindMatchingPins.get(i2);
                if (pin.hashAlgorithm.equals("sha256/")) {
                    if (jVarSha256 == null) {
                        jVarSha256 = sha256(x509Certificate);
                    }
                    if (pin.hash.equals(jVarSha256)) {
                        return;
                    }
                } else {
                    if (!pin.hashAlgorithm.equals("sha1/")) {
                        StringBuilder sbA = a.a("unsupported hashAlgorithm: ");
                        sbA.append(pin.hashAlgorithm);
                        throw new AssertionError(sbA.toString());
                    }
                    if (jVarSha1 == null) {
                        jVarSha1 = sha1(x509Certificate);
                    }
                    if (pin.hash.equals(jVarSha1)) {
                        return;
                    }
                }
            }
        }
        StringBuilder sbB = a.b("Certificate pinning failure!", "\n  Peer certificate chain:");
        int size3 = list.size();
        for (int i3 = 0; i3 < size3; i3++) {
            X509Certificate x509Certificate2 = (X509Certificate) list.get(i3);
            sbB.append("\n    ");
            sbB.append(pin(x509Certificate2));
            sbB.append(": ");
            sbB.append(x509Certificate2.getSubjectDN().getName());
        }
        sbB.append("\n  Pinned certificates for ");
        sbB.append(str);
        sbB.append(Utils.APP_ID_IDENTIFICATION_SUBSTRING);
        int size4 = listFindMatchingPins.size();
        for (int i4 = 0; i4 < size4; i4++) {
            Pin pin2 = listFindMatchingPins.get(i4);
            sbB.append("\n    ");
            sbB.append(pin2);
        }
        throw new SSLPeerUnverifiedException(sbB.toString());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CertificatePinner) {
            CertificatePinner certificatePinner = (CertificatePinner) obj;
            if (Util.equal(this.certificateChainCleaner, certificatePinner.certificateChainCleaner) && this.pins.equals(certificatePinner.pins)) {
                return true;
            }
        }
        return false;
    }

    public List<Pin> findMatchingPins(String str) {
        List<Pin> listEmptyList = Collections.emptyList();
        for (Pin pin : this.pins) {
            if (pin.matches(str)) {
                if (listEmptyList.isEmpty()) {
                    listEmptyList = new ArrayList<>();
                }
                listEmptyList.add(pin);
            }
        }
        return listEmptyList;
    }

    public int hashCode() {
        CertificateChainCleaner certificateChainCleaner = this.certificateChainCleaner;
        return this.pins.hashCode() + ((certificateChainCleaner != null ? certificateChainCleaner.hashCode() : 0) * 31);
    }

    public CertificatePinner withCertificateChainCleaner(CertificateChainCleaner certificateChainCleaner) {
        return Util.equal(this.certificateChainCleaner, certificateChainCleaner) ? this : new CertificatePinner(this.pins, certificateChainCleaner);
    }

    public void check(String str, Certificate... certificateArr) {
        check(str, Arrays.asList(certificateArr));
    }
}
