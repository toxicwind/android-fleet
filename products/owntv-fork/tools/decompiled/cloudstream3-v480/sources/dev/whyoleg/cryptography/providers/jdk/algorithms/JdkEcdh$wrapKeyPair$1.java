package dev.whyoleg.cryptography.providers.jdk.algorithms;

import dev.whyoleg.cryptography.algorithms.ECDH;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JdkEcdh.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
final /* synthetic */ class JdkEcdh$wrapKeyPair$1 extends FunctionReferenceImpl implements Function2<ECDH.PublicKey, ECDH.PrivateKey, JdkEcdh.EcdhKeyPair> {
    public static final JdkEcdh$wrapKeyPair$1 INSTANCE = new JdkEcdh$wrapKeyPair$1();

    JdkEcdh$wrapKeyPair$1() {
        super(2, JdkEcdh.EcdhKeyPair.class, "<init>", "<init>(Ldev/whyoleg/cryptography/algorithms/ECDH$PublicKey;Ldev/whyoleg/cryptography/algorithms/ECDH$PrivateKey;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function2
    public final JdkEcdh.EcdhKeyPair invoke(ECDH.PublicKey p0, ECDH.PrivateKey p1) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        return new JdkEcdh.EcdhKeyPair(p0, p1);
    }
}
