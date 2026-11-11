package dev.whyoleg.cryptography.providers.jdk.algorithms;

import dev.whyoleg.cryptography.algorithms.EC;
import java.security.PublicKey;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JdkEcdh.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
final /* synthetic */ class JdkEcdh$wrapPublicKey$1 extends FunctionReferenceImpl implements Function2<PublicKey, EC.Curve, JdkEcdh.EcdhPublicKey> {
    JdkEcdh$wrapPublicKey$1(Object obj) {
        super(2, obj, JdkEcdh.EcdhPublicKey.class, "<init>", "<init>(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkEcdh;Ljava/security/PublicKey;Ljava/lang/String;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ JdkEcdh.EcdhPublicKey invoke(PublicKey publicKey, EC.Curve curve) {
        return m8751invoketxG9UtI(publicKey, curve.m8696unboximpl());
    }

    /* JADX INFO: renamed from: invoke-txG9UtI, reason: not valid java name */
    public final JdkEcdh.EcdhPublicKey m8751invoketxG9UtI(PublicKey p0, String p1) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        return new JdkEcdh.EcdhPublicKey((JdkEcdh) this.receiver, p0, p1, null);
    }
}
