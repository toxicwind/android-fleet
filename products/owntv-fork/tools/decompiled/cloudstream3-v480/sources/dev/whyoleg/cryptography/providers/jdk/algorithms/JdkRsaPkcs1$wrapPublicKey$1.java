package dev.whyoleg.cryptography.providers.jdk.algorithms;

import dev.whyoleg.cryptography.CryptographyAlgorithmId;
import dev.whyoleg.cryptography.algorithms.Digest;
import java.security.PublicKey;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JdkRsaPkcs1.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
final /* synthetic */ class JdkRsaPkcs1$wrapPublicKey$1 extends FunctionReferenceImpl implements Function2<PublicKey, CryptographyAlgorithmId<Digest>, JdkRsaPkcs1.RsaPkcs1PublicKey> {
    JdkRsaPkcs1$wrapPublicKey$1(Object obj) {
        super(2, obj, JdkRsaPkcs1.RsaPkcs1PublicKey.class, "<init>", "<init>(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkRsaPkcs1;Ljava/security/PublicKey;Ldev/whyoleg/cryptography/CryptographyAlgorithmId;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function2
    public final JdkRsaPkcs1.RsaPkcs1PublicKey invoke(PublicKey p0, CryptographyAlgorithmId<Digest> p1) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        return new JdkRsaPkcs1.RsaPkcs1PublicKey((JdkRsaPkcs1) this.receiver, p0, p1);
    }
}
