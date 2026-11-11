package dev.whyoleg.cryptography.providers.jdk.operations;

import dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState;
import dev.whyoleg.cryptography.providers.jdk.Pooled;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.KeyAgreement;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JdkKeyAgreement.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a8\u0010\u0000\u001a\u00020\u0001*\f\u0012\b\u0012\u00060\u0003j\u0002`\u00040\u00022\u0006\u0010\u0005\u001a\u00020\u00062\n\u0010\u0007\u001a\u00060\bj\u0002`\t2\n\u0010\n\u001a\u00060\u000bj\u0002`\fH\u0080\u0080\u0004¨\u0006\r"}, d2 = {"doAgreement", "", "Ldev/whyoleg/cryptography/providers/jdk/Pooled;", "Ljavax/crypto/KeyAgreement;", "Ldev/whyoleg/cryptography/providers/jdk/JKeyAgreement;", "state", "Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "privateKey", "Ljava/security/PrivateKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPrivateKey;", "publicKey", "Ljava/security/PublicKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPublicKey;", "cryptography-provider-jdk"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class JdkKeyAgreementKt {
    public static final byte[] doAgreement(Pooled<KeyAgreement> pooled, JdkCryptographyState state, PrivateKey privateKey, PublicKey publicKey) {
        Intrinsics.checkNotNullParameter(pooled, "<this>");
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(privateKey, "privateKey");
        Intrinsics.checkNotNullParameter(publicKey, "publicKey");
        Object objBorrow = pooled.borrow();
        try {
            KeyAgreement keyAgreement = (KeyAgreement) objBorrow;
            keyAgreement.init(privateKey, state.getSecureRandom());
            keyAgreement.doPhase(publicKey, true);
            byte[] bArrGenerateSecret = keyAgreement.generateSecret();
            pooled.recycle(objBorrow);
            Intrinsics.checkNotNullExpressionValue(bArrGenerateSecret, "use(...)");
            return bArrGenerateSecret;
        } catch (Throwable th) {
            pooled.recycle(objBorrow);
            throw th;
        }
    }
}
