package dev.whyoleg.cryptography.providers.jdk.algorithms;

import dev.whyoleg.cryptography.CryptographyAlgorithmId;
import dev.whyoleg.cryptography.algorithms.Digest;
import java.security.PublicKey;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JdkRsaRaw.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
final /* synthetic */ class JdkRsaRaw$wrapPublicKey$1 extends FunctionReferenceImpl implements Function2<PublicKey, CryptographyAlgorithmId<Digest>, JdkRsaRaw.RsaRawPublicKey> {
    JdkRsaRaw$wrapPublicKey$1(Object obj) {
        super(2, obj, JdkRsaRaw.RsaRawPublicKey.class, "<init>", "<init>(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsaRaw;Ljava/security/PublicKey;Ldev/whyoleg/cryptography/CryptographyAlgorithmId;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function2
    public final JdkRsaRaw.RsaRawPublicKey invoke(PublicKey p0, CryptographyAlgorithmId<Digest> p1) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        return new JdkRsaRaw.RsaRawPublicKey((JdkRsaRaw) this.receiver, p0, p1);
    }
}
