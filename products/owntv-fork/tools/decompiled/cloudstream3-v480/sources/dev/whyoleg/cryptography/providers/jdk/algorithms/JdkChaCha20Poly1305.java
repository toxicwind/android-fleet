package dev.whyoleg.cryptography.providers.jdk.algorithms;

import dev.whyoleg.cryptography.algorithms.ChaCha20Poly1305;
import dev.whyoleg.cryptography.operations.IvAuthenticatedCipher;
import dev.whyoleg.cryptography.providers.base.algorithms.BaseChaCha20Poly1305;
import dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JdkChaCha20Poly1305.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001:\u0001\nB\u0011\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0094\u0080\u0004R\u000f\u0010\u0002\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkChaCha20Poly1305;", "Ldev/whyoleg/cryptography/providers/base/algorithms/BaseChaCha20Poly1305;", "state", "Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;)V", "wrapKey", "Ldev/whyoleg/cryptography/algorithms/ChaCha20Poly1305$Key;", "rawKey", "", "ChaCha20Poly1305Key", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class JdkChaCha20Poly1305 extends BaseChaCha20Poly1305 {
    private final JdkCryptographyState state;

    public JdkChaCha20Poly1305(JdkCryptographyState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        this.state = state;
    }

    @Override // dev.whyoleg.cryptography.providers.base.algorithms.BaseChaCha20Poly1305
    protected ChaCha20Poly1305.Key wrapKey(byte[] rawKey) {
        Intrinsics.checkNotNullParameter(rawKey, "rawKey");
        return new ChaCha20Poly1305Key(this, rawKey);
    }

    /* JADX INFO: compiled from: JdkChaCha20Poly1305.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u00012\u00060\u0002R\u00020\u0003B\u0011\bF\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\n\u0010\u000b\u001a\u00020\fH\u0096\u0080\u0004R\u0013\u0010\b\u001a\u00060\tj\u0002`\nX\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkChaCha20Poly1305$ChaCha20Poly1305Key;", "Ldev/whyoleg/cryptography/algorithms/ChaCha20Poly1305$Key;", "Ldev/whyoleg/cryptography/providers/base/algorithms/BaseChaCha20Poly1305$BaseKey;", "Ldev/whyoleg/cryptography/providers/base/algorithms/BaseChaCha20Poly1305;", "rawKey", "", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/algorithms/JdkChaCha20Poly1305;[B)V", "secretKey", "Ljavax/crypto/SecretKey;", "Ldev/whyoleg/cryptography/providers/jdk/JSecretKey;", "cipher", "Ldev/whyoleg/cryptography/operations/IvAuthenticatedCipher;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class ChaCha20Poly1305Key extends BaseChaCha20Poly1305.BaseKey implements ChaCha20Poly1305.Key {
        private final SecretKey secretKey;
        final /* synthetic */ JdkChaCha20Poly1305 this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ChaCha20Poly1305Key(JdkChaCha20Poly1305 jdkChaCha20Poly1305, byte[] rawKey) {
            super(jdkChaCha20Poly1305, rawKey);
            Intrinsics.checkNotNullParameter(rawKey, "rawKey");
            this.this$0 = jdkChaCha20Poly1305;
            this.secretKey = new SecretKeySpec(rawKey, "ChaCha20");
        }

        @Override // dev.whyoleg.cryptography.algorithms.ChaCha20Poly1305.Key
        public IvAuthenticatedCipher cipher() {
            return new JdkChaCha20Poly1305Cipher(this.this$0.state, this.secretKey);
        }
    }
}
