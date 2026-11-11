package dev.whyoleg.cryptography.providers.jdk.algorithms;

import dev.whyoleg.cryptography.algorithms.EC;
import dev.whyoleg.cryptography.algorithms.ECDH;
import java.security.PrivateKey;
import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JdkEcdh.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
final /* synthetic */ class JdkEcdh$wrapPrivateKey$1 extends FunctionReferenceImpl implements Function3<PrivateKey, EC.Curve, ECDH.PublicKey, JdkEcdh.EcdhPrivateKey> {
    JdkEcdh$wrapPrivateKey$1(Object obj) {
        super(3, obj, JdkEcdh.EcdhPrivateKey.class, "<init>", "<init>(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEcdh;Ljava/security/PrivateKey;Ljava/lang/String;Ldev/whyoleg/cryptography/algorithms/ECDH$PublicKey;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ JdkEcdh.EcdhPrivateKey invoke(PrivateKey privateKey, EC.Curve curve, ECDH.PublicKey publicKey) {
        return m8750invokeGZQjZoM(privateKey, curve.m8696unboximpl(), publicKey);
    }

    /* JADX INFO: renamed from: invoke-GZQjZoM, reason: not valid java name */
    public final JdkEcdh.EcdhPrivateKey m8750invokeGZQjZoM(PrivateKey p0, String p1, ECDH.PublicKey publicKey) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        return new JdkEcdh.EcdhPrivateKey((JdkEcdh) this.receiver, p0, p1, publicKey, null);
    }
}
