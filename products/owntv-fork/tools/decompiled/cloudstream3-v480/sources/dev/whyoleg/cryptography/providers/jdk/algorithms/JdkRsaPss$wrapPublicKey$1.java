package dev.whyoleg.cryptography.providers.jdk.algorithms;

import dev.whyoleg.cryptography.CryptographyAlgorithmId;
import dev.whyoleg.cryptography.algorithms.Digest;
import java.security.PublicKey;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JdkRsaPss.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
final /* synthetic */ class JdkRsaPss$wrapPublicKey$1 extends FunctionReferenceImpl implements Function2<PublicKey, CryptographyAlgorithmId<Digest>, JdkRsaPss.RsaPssPublicKey> {
    JdkRsaPss$wrapPublicKey$1(Object obj) {
        super(2, obj, JdkRsaPss.RsaPssPublicKey.class, "<init>", "<init>(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsaPss;Ljava/security/PublicKey;Ldev/whyoleg/cryptography/CryptographyAlgorithmId;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function2
    public final JdkRsaPss.RsaPssPublicKey invoke(PublicKey p0, CryptographyAlgorithmId<Digest> p1) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        return new JdkRsaPss.RsaPssPublicKey((JdkRsaPss) this.receiver, p0, p1);
    }
}
