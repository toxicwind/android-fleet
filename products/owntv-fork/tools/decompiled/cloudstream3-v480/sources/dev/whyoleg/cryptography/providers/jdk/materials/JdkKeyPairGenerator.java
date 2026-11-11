package dev.whyoleg.cryptography.providers.jdk.materials;

import dev.whyoleg.cryptography.operations.KeyGenerator;
import dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState;
import dev.whyoleg.cryptography.providers.jdk.Pooled;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JdkKeyPairGenerator.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b \u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0019\bF\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0012\u0010\u000f\u001a\u00020\u0010*\u00060\rj\u0002`\u000eH¤\u0080\u0004J\u0017\u0010\u0011\u001a\u00028\u0000*\u00060\u0012j\u0002`\u0013H¤\u0080\u0004¢\u0006\u0002\u0010\u0014J\u000f\u0010\u0015\u001a\u00028\u0000H\u0086\u0080\u0004¢\u0006\u0002\u0010\u0016R\u0015\u0010\u0003\u001a\u00020\u0004X\u0084\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0019\u0010\u000b\u001a\f\u0012\b\u0012\u00060\rj\u0002`\u000e0\fX\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/materials/JdkKeyPairGenerator;", "K", "Ldev/whyoleg/cryptography/operations/KeyGenerator;", "state", "Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "algorithm", "", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;Ljava/lang/String;)V", "getState", "()Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "keyPairGenerator", "Ldev/whyoleg/cryptography/providers/jdk/Pooled;", "Ljava/security/KeyPairGenerator;", "Ldev/whyoleg/cryptography/providers/jdk/JKeyPairGenerator;", "init", "", "convert", "Ljava/security/KeyPair;", "Ldev/whyoleg/cryptography/providers/jdk/JKeyPair;", "(Ljava/security/KeyPair;)Ljava/lang/Object;", "generateKeyBlocking", "()Ljava/lang/Object;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class JdkKeyPairGenerator<K> implements KeyGenerator<K> {
    private final Pooled<KeyPairGenerator> keyPairGenerator;
    private final JdkCryptographyState state;

    protected abstract K convert(KeyPair keyPair);

    @Override // dev.whyoleg.cryptography.operations.KeyGenerator
    public /* synthetic */ Object generateKey(Continuation continuation) {
        return KeyGenerator.CC.generateKey$suspendImpl(this, continuation);
    }

    protected abstract void init(KeyPairGenerator keyPairGenerator);

    public JdkKeyPairGenerator(JdkCryptographyState state, String algorithm) {
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        this.state = state;
        this.keyPairGenerator = state.keyPairGenerator(algorithm);
    }

    protected final JdkCryptographyState getState() {
        return this.state;
    }

    @Override // dev.whyoleg.cryptography.operations.KeyGenerator
    public final K generateKeyBlocking() {
        Pooled<KeyPairGenerator> pooled = this.keyPairGenerator;
        Object objBorrow = pooled.borrow();
        try {
            KeyPairGenerator keyPairGenerator = (KeyPairGenerator) objBorrow;
            init(keyPairGenerator);
            KeyPair keyPairGenerateKeyPair = keyPairGenerator.generateKeyPair();
            pooled.recycle(objBorrow);
            Intrinsics.checkNotNullExpressionValue(keyPairGenerateKeyPair, "use(...)");
            return convert(keyPairGenerateKeyPair);
        } catch (Throwable th) {
            pooled.recycle(objBorrow);
            throw th;
        }
    }
}
