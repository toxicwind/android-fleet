package dev.whyoleg.cryptography.providers.jdk.algorithms;

import dev.whyoleg.cryptography.algorithms.RSA;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JdkRsaRaw.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
final /* synthetic */ class JdkRsaRaw$wrapKeyPair$1 extends FunctionReferenceImpl implements Function2<RSA.RAW.PublicKey, RSA.RAW.PrivateKey, JdkRsaRaw.RsaRawKeyPair> {
    public static final JdkRsaRaw$wrapKeyPair$1 INSTANCE = new JdkRsaRaw$wrapKeyPair$1();

    JdkRsaRaw$wrapKeyPair$1() {
        super(2, JdkRsaRaw.RsaRawKeyPair.class, "<init>", "<init>(Ldev/whyoleg/cryptography/algorithms/RSA$RAW$PublicKey;Ldev/whyoleg/cryptography/algorithms/RSA$RAW$PrivateKey;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function2
    public final JdkRsaRaw.RsaRawKeyPair invoke(RSA.RAW.PublicKey p0, RSA.RAW.PrivateKey p1) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        return new JdkRsaRaw.RsaRawKeyPair(p0, p1);
    }
}
