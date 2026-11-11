package dev.whyoleg.cryptography.providers.jdk.algorithms;

import dev.whyoleg.cryptography.algorithms.RSA;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JdkRsaPkcs1.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
final /* synthetic */ class JdkRsaPkcs1$wrapKeyPair$1 extends FunctionReferenceImpl implements Function2<RSA.PKCS1.PublicKey, RSA.PKCS1.PrivateKey, JdkRsaPkcs1.RsaPkcs1KeyPair> {
    public static final JdkRsaPkcs1$wrapKeyPair$1 INSTANCE = new JdkRsaPkcs1$wrapKeyPair$1();

    JdkRsaPkcs1$wrapKeyPair$1() {
        super(2, JdkRsaPkcs1.RsaPkcs1KeyPair.class, "<init>", "<init>(Ldev/whyoleg/cryptography/algorithms/RSA$PKCS1$PublicKey;Ldev/whyoleg/cryptography/algorithms/RSA$PKCS1$PrivateKey;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function2
    public final JdkRsaPkcs1.RsaPkcs1KeyPair invoke(RSA.PKCS1.PublicKey p0, RSA.PKCS1.PrivateKey p1) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        return new JdkRsaPkcs1.RsaPkcs1KeyPair(p0, p1);
    }
}
