package dev.whyoleg.cryptography.providers.jdk.algorithms;

import dev.whyoleg.cryptography.algorithms.EC;
import java.security.PublicKey;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JdkEcdsa.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
final /* synthetic */ class JdkEcdsa$wrapPublicKey$1 extends FunctionReferenceImpl implements Function2<PublicKey, EC.Curve, JdkEcdsa.EcdsaPublicKey> {
    JdkEcdsa$wrapPublicKey$1(Object obj) {
        super(2, obj, JdkEcdsa.EcdsaPublicKey.class, "<init>", "<init>(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEcdsa;Ljava/security/PublicKey;Ljava/lang/String;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ JdkEcdsa.EcdsaPublicKey invoke(PublicKey publicKey, EC.Curve curve) {
        return m8753invoketxG9UtI(publicKey, curve.m8696unboximpl());
    }

    /* JADX INFO: renamed from: invoke-txG9UtI, reason: not valid java name */
    public final JdkEcdsa.EcdsaPublicKey m8753invoketxG9UtI(PublicKey p0, String p1) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        return new JdkEcdsa.EcdsaPublicKey((JdkEcdsa) this.receiver, p0, p1, null);
    }
}
