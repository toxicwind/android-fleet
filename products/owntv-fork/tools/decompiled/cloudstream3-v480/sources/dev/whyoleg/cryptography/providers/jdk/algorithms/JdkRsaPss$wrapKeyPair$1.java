package dev.whyoleg.cryptography.providers.jdk.algorithms;

import dev.whyoleg.cryptography.algorithms.RSA;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JdkRsaPss.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
final /* synthetic */ class JdkRsaPss$wrapKeyPair$1 extends FunctionReferenceImpl implements Function2<RSA.PSS.PublicKey, RSA.PSS.PrivateKey, JdkRsaPss.RsaPssKeyPair> {
    public static final JdkRsaPss$wrapKeyPair$1 INSTANCE = new JdkRsaPss$wrapKeyPair$1();

    JdkRsaPss$wrapKeyPair$1() {
        super(2, JdkRsaPss.RsaPssKeyPair.class, "<init>", "<init>(Ldev/whyoleg/cryptography/algorithms/RSA$PSS$PublicKey;Ldev/whyoleg/cryptography/algorithms/RSA$PSS$PrivateKey;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function2
    public final JdkRsaPss.RsaPssKeyPair invoke(RSA.PSS.PublicKey p0, RSA.PSS.PrivateKey p1) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        return new JdkRsaPss.RsaPssKeyPair(p0, p1);
    }
}
