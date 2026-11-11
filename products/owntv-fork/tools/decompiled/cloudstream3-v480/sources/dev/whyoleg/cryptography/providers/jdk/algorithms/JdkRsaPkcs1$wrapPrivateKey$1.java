package dev.whyoleg.cryptography.providers.jdk.algorithms;

import dev.whyoleg.cryptography.CryptographyAlgorithmId;
import dev.whyoleg.cryptography.algorithms.Digest;
import dev.whyoleg.cryptography.algorithms.RSA;
import java.security.PrivateKey;
import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JdkRsaPkcs1.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
final /* synthetic */ class JdkRsaPkcs1$wrapPrivateKey$1 extends FunctionReferenceImpl implements Function3<PrivateKey, CryptographyAlgorithmId<Digest>, RSA.PKCS1.PublicKey, JdkRsaPkcs1.RsaPkcs1PrivateKey> {
    JdkRsaPkcs1$wrapPrivateKey$1(Object obj) {
        super(3, obj, JdkRsaPkcs1.RsaPkcs1PrivateKey.class, "<init>", "<init>(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsaPkcs1;Ljava/security/PrivateKey;Ldev/whyoleg/cryptography/CryptographyAlgorithmId;Ldev/whyoleg/cryptography/algorithms/RSA$PKCS1$PublicKey;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function3
    public final JdkRsaPkcs1.RsaPkcs1PrivateKey invoke(PrivateKey p0, CryptographyAlgorithmId<Digest> p1, RSA.PKCS1.PublicKey publicKey) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        return new JdkRsaPkcs1.RsaPkcs1PrivateKey((JdkRsaPkcs1) this.receiver, p0, p1, publicKey);
    }
}
