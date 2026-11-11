package dev.whyoleg.cryptography.providers.jdk.materials;

import dev.whyoleg.cryptography.operations.ParametersGenerator;
import dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState;
import dev.whyoleg.cryptography.providers.jdk.Pooled;
import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JdkParametersGenerator.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b \u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0019\bF\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0012\u0010\u000f\u001a\u00020\u0010*\u00060\rj\u0002`\u000eH¤\u0080\u0004J\u0017\u0010\u0011\u001a\u00028\u0000*\u00060\u0012j\u0002`\u0013H¤\u0080\u0004¢\u0006\u0002\u0010\u0014J\u000f\u0010\u0015\u001a\u00028\u0000H\u0086\u0080\u0004¢\u0006\u0002\u0010\u0016R\u0015\u0010\u0003\u001a\u00020\u0004X\u0084\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0019\u0010\u000b\u001a\f\u0012\b\u0012\u00060\rj\u0002`\u000e0\fX\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/materials/JdkParametersGenerator;", "P", "Ldev/whyoleg/cryptography/operations/ParametersGenerator;", "state", "Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "algorithm", "", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;Ljava/lang/String;)V", "getState", "()Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "algorithmParameterGenerator", "Ldev/whyoleg/cryptography/providers/jdk/Pooled;", "Ljava/security/AlgorithmParameterGenerator;", "Ldev/whyoleg/cryptography/providers/jdk/JAlgorithmParameterGenerator;", "init", "", "convert", "Ljava/security/AlgorithmParameters;", "Ldev/whyoleg/cryptography/providers/jdk/JAlgorithmParameters;", "(Ljava/security/AlgorithmParameters;)Ljava/lang/Object;", "generateParametersBlocking", "()Ljava/lang/Object;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class JdkParametersGenerator<P> implements ParametersGenerator<P> {
    private final Pooled<AlgorithmParameterGenerator> algorithmParameterGenerator;
    private final JdkCryptographyState state;

    protected abstract P convert(AlgorithmParameters algorithmParameters);

    @Override // dev.whyoleg.cryptography.operations.ParametersGenerator
    public /* synthetic */ Object generateParameters(Continuation continuation) {
        return ParametersGenerator.CC.generateParameters$suspendImpl(this, continuation);
    }

    protected abstract void init(AlgorithmParameterGenerator algorithmParameterGenerator);

    public JdkParametersGenerator(JdkCryptographyState state, String algorithm) {
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        this.state = state;
        this.algorithmParameterGenerator = state.algorithmParameterGenerator(algorithm);
    }

    protected final JdkCryptographyState getState() {
        return this.state;
    }

    @Override // dev.whyoleg.cryptography.operations.ParametersGenerator
    public final P generateParametersBlocking() {
        Pooled<AlgorithmParameterGenerator> pooled = this.algorithmParameterGenerator;
        Object objBorrow = pooled.borrow();
        try {
            AlgorithmParameterGenerator algorithmParameterGenerator = (AlgorithmParameterGenerator) objBorrow;
            init(algorithmParameterGenerator);
            AlgorithmParameters algorithmParametersGenerateParameters = algorithmParameterGenerator.generateParameters();
            pooled.recycle(objBorrow);
            Intrinsics.checkNotNullExpressionValue(algorithmParametersGenerateParameters, "use(...)");
            return convert(algorithmParametersGenerateParameters);
        } catch (Throwable th) {
            pooled.recycle(objBorrow);
            throw th;
        }
    }
}
