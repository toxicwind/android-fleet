package dev.whyoleg.cryptography.providers.jdk.algorithms;

import dev.whyoleg.cryptography.algorithms.EC;
import dev.whyoleg.cryptography.algorithms.ECDSA;
import java.security.PrivateKey;
import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JdkEcdsa.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
final /* synthetic */ class JdkEcdsa$wrapPrivateKey$1 extends FunctionReferenceImpl implements Function3<PrivateKey, EC.Curve, ECDSA.PublicKey, JdkEcdsa.EcdsaPrivateKey> {
    JdkEcdsa$wrapPrivateKey$1(Object obj) {
        super(3, obj, JdkEcdsa.EcdsaPrivateKey.class, "<init>", "<init>(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEcdsa;Ljava/security/PrivateKey;Ljava/lang/String;Ldev/whyoleg/cryptography/algorithms/ECDSA$PublicKey;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ JdkEcdsa.EcdsaPrivateKey invoke(PrivateKey privateKey, EC.Curve curve, ECDSA.PublicKey publicKey) {
        return m8752invokeGZQjZoM(privateKey, curve.m8696unboximpl(), publicKey);
    }

    /* JADX INFO: renamed from: invoke-GZQjZoM, reason: not valid java name */
    public final JdkEcdsa.EcdsaPrivateKey m8752invokeGZQjZoM(PrivateKey p0, String p1, ECDSA.PublicKey publicKey) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        return new JdkEcdsa.EcdsaPrivateKey((JdkEcdsa) this.receiver, p0, p1, publicKey, null);
    }
}
