package dev.whyoleg.cryptography.providers.jdk.algorithms;

import dev.whyoleg.cryptography.CryptographyAlgorithmId;
import dev.whyoleg.cryptography.algorithms.Digest;
import dev.whyoleg.cryptography.algorithms.RSA;
import java.security.PrivateKey;
import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JdkRsaOaep.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
final /* synthetic */ class JdkRsaOaep$wrapPrivateKey$1 extends FunctionReferenceImpl implements Function3<PrivateKey, CryptographyAlgorithmId<Digest>, RSA.OAEP.PublicKey, JdkRsaOaep.RsaOaepPrivateKey> {
    JdkRsaOaep$wrapPrivateKey$1(Object obj) {
        super(3, obj, JdkRsaOaep.RsaOaepPrivateKey.class, "<init>", "<init>(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsaOaep;Ljava/security/PrivateKey;Ldev/whyoleg/cryptography/CryptographyAlgorithmId;Ldev/whyoleg/cryptography/algorithms/RSA$OAEP$PublicKey;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function3
    public final JdkRsaOaep.RsaOaepPrivateKey invoke(PrivateKey p0, CryptographyAlgorithmId<Digest> p1, RSA.OAEP.PublicKey publicKey) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        return new JdkRsaOaep.RsaOaepPrivateKey((JdkRsaOaep) this.receiver, p0, p1, publicKey);
    }
}
