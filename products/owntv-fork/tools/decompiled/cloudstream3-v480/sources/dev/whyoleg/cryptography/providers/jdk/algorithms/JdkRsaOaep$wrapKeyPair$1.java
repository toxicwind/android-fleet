package dev.whyoleg.cryptography.providers.jdk.algorithms;

import dev.whyoleg.cryptography.algorithms.RSA;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JdkRsaOaep.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
final /* synthetic */ class JdkRsaOaep$wrapKeyPair$1 extends FunctionReferenceImpl implements Function2<RSA.OAEP.PublicKey, RSA.OAEP.PrivateKey, JdkRsaOaep.RsaOaepKeyPair> {
    public static final JdkRsaOaep$wrapKeyPair$1 INSTANCE = new JdkRsaOaep$wrapKeyPair$1();

    JdkRsaOaep$wrapKeyPair$1() {
        super(2, JdkRsaOaep.RsaOaepKeyPair.class, "<init>", "<init>(Ldev/whyoleg/cryptography/algorithms/RSA$OAEP$PublicKey;Ldev/whyoleg/cryptography/algorithms/RSA$OAEP$PrivateKey;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function2
    public final JdkRsaOaep.RsaOaepKeyPair invoke(RSA.OAEP.PublicKey p0, RSA.OAEP.PrivateKey p1) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        return new JdkRsaOaep.RsaOaepKeyPair(p0, p1);
    }
}
