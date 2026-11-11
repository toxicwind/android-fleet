package dev.whyoleg.cryptography.providers.jdk.algorithms;

import dev.whyoleg.cryptography.algorithms.ECDSA;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JdkEcdsa.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
final /* synthetic */ class JdkEcdsa$wrapKeyPair$1 extends FunctionReferenceImpl implements Function2<ECDSA.PublicKey, ECDSA.PrivateKey, JdkEcdsa.EcdsaKeyPair> {
    public static final JdkEcdsa$wrapKeyPair$1 INSTANCE = new JdkEcdsa$wrapKeyPair$1();

    JdkEcdsa$wrapKeyPair$1() {
        super(2, JdkEcdsa.EcdsaKeyPair.class, "<init>", "<init>(Ldev/whyoleg/cryptography/algorithms/ECDSA$PublicKey;Ldev/whyoleg/cryptography/algorithms/ECDSA$PrivateKey;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function2
    public final JdkEcdsa.EcdsaKeyPair invoke(ECDSA.PublicKey p0, ECDSA.PrivateKey p1) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        return new JdkEcdsa.EcdsaKeyPair(p0, p1);
    }
}
