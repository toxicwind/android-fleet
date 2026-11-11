package dev.whyoleg.cryptography.serialization.pem;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: PemLabel.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0087@\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0011\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u000b\u001a\u00020\fHÖ\u0081\u0004J\n\u0010\r\u001a\u00020\u0003HÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002¨\u0006\u000f"}, d2 = {"Ldev/whyoleg/cryptography/serialization/pem/PemLabel;", "", ES6Iterator.VALUE_PROPERTY, "", "constructor-impl", "(Ljava/lang/String;)Ljava/lang/String;", "getValue", "()Ljava/lang/String;", "equals", "", "other", "hashCode", "", "toString", "Companion", "cryptography-serialization-pem"}, k = 1, mv = {2, 3, 0}, xi = 48)
@JvmInline
public final class PemLabel {
    private final String value;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String PublicKey = m8802constructorimpl("PUBLIC KEY");
    private static final String PrivateKey = m8802constructorimpl("PRIVATE KEY");
    private static final String EncryptedPrivateKey = m8802constructorimpl("ENCRYPTED PRIVATE KEY");
    private static final String RsaPublicKey = m8802constructorimpl("RSA PUBLIC KEY");
    private static final String RsaPrivateKey = m8802constructorimpl("RSA PRIVATE KEY");
    private static final String EcPrivateKey = m8802constructorimpl("EC PRIVATE KEY");
    private static final String Certificate = m8802constructorimpl("CERTIFICATE");
    private static final String CertificateRequest = m8802constructorimpl("CERTIFICATE REQUEST");
    private static final String DhParameters = m8802constructorimpl("DH PARAMETERS");
    private static final String DsaParameters = m8802constructorimpl("DSA PARAMETERS");

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ PemLabel m8801boximpl(String str) {
        return new PemLabel(str);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static String m8802constructorimpl(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return value;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m8803equalsimpl(String str, Object obj) {
        return (obj instanceof PemLabel) && Intrinsics.areEqual(str, ((PemLabel) obj).m8807unboximpl());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m8804equalsimpl0(String str, String str2) {
        return Intrinsics.areEqual(str, str2);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m8805hashCodeimpl(String str) {
        return str.hashCode();
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m8806toStringimpl(String str) {
        return "PemLabel(value=" + str + ')';
    }

    public boolean equals(Object other) {
        return m8803equalsimpl(this.value, other);
    }

    public int hashCode() {
        return m8805hashCodeimpl(this.value);
    }

    public String toString() {
        return m8806toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ String m8807unboximpl() {
        return this.value;
    }

    /* JADX INFO: compiled from: PemLabel.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0016\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0004\u001a\u00020\u0005X\u0086\u0084\b¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0017\u0010\t\u001a\u00020\u0005X\u0086\u0084\b¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0017\u0010\u000b\u001a\u00020\u0005X\u0086\u0084\b¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\f\u0010\u0007R\u0017\u0010\r\u001a\u00020\u0005X\u0086\u0084\b¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u000e\u0010\u0007R\u0017\u0010\u000f\u001a\u00020\u0005X\u0086\u0084\b¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0010\u0010\u0007R\u0017\u0010\u0011\u001a\u00020\u0005X\u0086\u0084\b¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0012\u0010\u0007R\u0017\u0010\u0013\u001a\u00020\u0005X\u0086\u0084\b¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0014\u0010\u0007R\u0017\u0010\u0015\u001a\u00020\u0005X\u0086\u0084\b¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0016\u0010\u0007R\u0017\u0010\u0017\u001a\u00020\u0005X\u0086\u0084\b¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0018\u0010\u0007R\u0017\u0010\u0019\u001a\u00020\u0005X\u0086\u0084\b¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u001a\u0010\u0007¨\u0006\u001b"}, d2 = {"Ldev/whyoleg/cryptography/serialization/pem/PemLabel$Companion;", "", "<init>", "()V", "PublicKey", "Ldev/whyoleg/cryptography/serialization/pem/PemLabel;", "getPublicKey-2EFq_Wg", "()Ljava/lang/String;", "Ljava/lang/String;", "PrivateKey", "getPrivateKey-2EFq_Wg", "EncryptedPrivateKey", "getEncryptedPrivateKey-2EFq_Wg", "RsaPublicKey", "getRsaPublicKey-2EFq_Wg", "RsaPrivateKey", "getRsaPrivateKey-2EFq_Wg", "EcPrivateKey", "getEcPrivateKey-2EFq_Wg", "Certificate", "getCertificate-2EFq_Wg", "CertificateRequest", "getCertificateRequest-2EFq_Wg", "DhParameters", "getDhParameters-2EFq_Wg", "DsaParameters", "getDsaParameters-2EFq_Wg", "cryptography-serialization-pem"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getPublicKey-2EFq_Wg, reason: not valid java name */
        public final String m8815getPublicKey2EFq_Wg() {
            return PemLabel.PublicKey;
        }

        /* JADX INFO: renamed from: getPrivateKey-2EFq_Wg, reason: not valid java name */
        public final String m8814getPrivateKey2EFq_Wg() {
            return PemLabel.PrivateKey;
        }

        /* JADX INFO: renamed from: getEncryptedPrivateKey-2EFq_Wg, reason: not valid java name */
        public final String m8813getEncryptedPrivateKey2EFq_Wg() {
            return PemLabel.EncryptedPrivateKey;
        }

        /* JADX INFO: renamed from: getRsaPublicKey-2EFq_Wg, reason: not valid java name */
        public final String m8817getRsaPublicKey2EFq_Wg() {
            return PemLabel.RsaPublicKey;
        }

        /* JADX INFO: renamed from: getRsaPrivateKey-2EFq_Wg, reason: not valid java name */
        public final String m8816getRsaPrivateKey2EFq_Wg() {
            return PemLabel.RsaPrivateKey;
        }

        /* JADX INFO: renamed from: getEcPrivateKey-2EFq_Wg, reason: not valid java name */
        public final String m8812getEcPrivateKey2EFq_Wg() {
            return PemLabel.EcPrivateKey;
        }

        /* JADX INFO: renamed from: getCertificate-2EFq_Wg, reason: not valid java name */
        public final String m8808getCertificate2EFq_Wg() {
            return PemLabel.Certificate;
        }

        /* JADX INFO: renamed from: getCertificateRequest-2EFq_Wg, reason: not valid java name */
        public final String m8809getCertificateRequest2EFq_Wg() {
            return PemLabel.CertificateRequest;
        }

        /* JADX INFO: renamed from: getDhParameters-2EFq_Wg, reason: not valid java name */
        public final String m8810getDhParameters2EFq_Wg() {
            return PemLabel.DhParameters;
        }

        /* JADX INFO: renamed from: getDsaParameters-2EFq_Wg, reason: not valid java name */
        public final String m8811getDsaParameters2EFq_Wg() {
            return PemLabel.DsaParameters;
        }
    }

    private /* synthetic */ PemLabel(String str) {
        this.value = str;
    }

    public final String getValue() {
        return this.value;
    }
}
