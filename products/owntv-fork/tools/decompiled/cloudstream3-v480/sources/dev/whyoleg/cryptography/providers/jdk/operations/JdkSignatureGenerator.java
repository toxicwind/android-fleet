package dev.whyoleg.cryptography.providers.jdk.operations;

import dev.whyoleg.cryptography.operations.SignFunction;
import dev.whyoleg.cryptography.operations.SignatureGenerator;
import dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState;
import dev.whyoleg.cryptography.providers.jdk.Pooled;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.AlgorithmParameterSpec;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.RawSource;
import kotlinx.io.bytestring.ByteString;

/* JADX INFO: compiled from: JdkSignatureGenerator.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B/\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fJ\n\u0010\u0011\u001a\u00020\u0012H\u0096\u0080\u0004R\u000f\u0010\u0002\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0013\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0019\u0010\r\u001a\f\u0012\b\u0012\u00060\u000fj\u0002`\u00100\u000eX\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/operations/JdkSignatureGenerator;", "Ldev/whyoleg/cryptography/operations/SignatureGenerator;", "state", "Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "key", "Ljava/security/PrivateKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPrivateKey;", "algorithm", "", "parameters", "Ljava/security/spec/AlgorithmParameterSpec;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;Ljava/security/PrivateKey;Ljava/lang/String;Ljava/security/spec/AlgorithmParameterSpec;)V", "signature", "Ldev/whyoleg/cryptography/providers/jdk/Pooled;", "Ljava/security/Signature;", "Ldev/whyoleg/cryptography/providers/jdk/JSignature;", "createSignFunction", "Ldev/whyoleg/cryptography/operations/SignFunction;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class JdkSignatureGenerator implements SignatureGenerator {
    private final PrivateKey key;
    private final AlgorithmParameterSpec parameters;
    private final Pooled<Signature> signature;
    private final JdkCryptographyState state;

    @Override // dev.whyoleg.cryptography.operations.SignatureGenerator
    public /* synthetic */ Object generateSignature(RawSource rawSource, Continuation continuation) {
        return SignatureGenerator.CC.generateSignature$suspendImpl(this, rawSource, (Continuation<? super ByteString>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.SignatureGenerator
    public /* synthetic */ Object generateSignature(ByteString byteString, Continuation continuation) {
        return SignatureGenerator.CC.generateSignature$suspendImpl(this, byteString, (Continuation<? super ByteString>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.SignatureGenerator
    public /* synthetic */ Object generateSignature(byte[] bArr, Continuation continuation) {
        return SignatureGenerator.CC.generateSignature$suspendImpl(this, bArr, (Continuation<? super byte[]>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.SignatureGenerator
    public /* synthetic */ ByteString generateSignatureBlocking(RawSource rawSource) {
        return SignatureGenerator.CC.$default$generateSignatureBlocking(this, rawSource);
    }

    @Override // dev.whyoleg.cryptography.operations.SignatureGenerator
    public /* synthetic */ ByteString generateSignatureBlocking(ByteString byteString) {
        return SignatureGenerator.CC.$default$generateSignatureBlocking(this, byteString);
    }

    @Override // dev.whyoleg.cryptography.operations.SignatureGenerator
    public /* synthetic */ byte[] generateSignatureBlocking(byte[] bArr) {
        return SignatureGenerator.CC.$default$generateSignatureBlocking(this, bArr);
    }

    public JdkSignatureGenerator(JdkCryptographyState state, PrivateKey key, String algorithm, AlgorithmParameterSpec algorithmParameterSpec) {
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        this.state = state;
        this.key = key;
        this.parameters = algorithmParameterSpec;
        this.signature = state.signature(algorithm);
    }

    @Override // dev.whyoleg.cryptography.operations.SignatureGenerator
    public SignFunction createSignFunction() {
        return new JdkSignFunction(this.state, this.key, this.parameters, this.signature.borrowResource());
    }
}
