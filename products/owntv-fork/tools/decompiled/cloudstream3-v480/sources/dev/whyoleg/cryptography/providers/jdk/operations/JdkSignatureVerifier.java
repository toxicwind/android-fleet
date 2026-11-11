package dev.whyoleg.cryptography.providers.jdk.operations;

import dev.whyoleg.cryptography.operations.SignatureVerifier;
import dev.whyoleg.cryptography.operations.VerifyFunction;
import dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState;
import dev.whyoleg.cryptography.providers.jdk.Pooled;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.AlgorithmParameterSpec;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.RawSource;
import kotlinx.io.bytestring.ByteString;

/* JADX INFO: compiled from: JdkSignatureVerifier.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B/\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fJ\n\u0010\u0011\u001a\u00020\u0012H\u0096\u0080\u0004R\u0013\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0019\u0010\r\u001a\f\u0012\b\u0012\u00060\u000fj\u0002`\u00100\u000eX\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/operations/JdkSignatureVerifier;", "Ldev/whyoleg/cryptography/operations/SignatureVerifier;", "state", "Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "key", "Ljava/security/PublicKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPublicKey;", "algorithm", "", "parameters", "Ljava/security/spec/AlgorithmParameterSpec;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;Ljava/security/PublicKey;Ljava/lang/String;Ljava/security/spec/AlgorithmParameterSpec;)V", "signature", "Ldev/whyoleg/cryptography/providers/jdk/Pooled;", "Ljava/security/Signature;", "Ldev/whyoleg/cryptography/providers/jdk/JSignature;", "createVerifyFunction", "Ldev/whyoleg/cryptography/operations/VerifyFunction;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class JdkSignatureVerifier implements SignatureVerifier {
    private final PublicKey key;
    private final AlgorithmParameterSpec parameters;
    private final Pooled<Signature> signature;

    @Override // dev.whyoleg.cryptography.operations.SignatureVerifier
    public /* synthetic */ Object tryVerifySignature(RawSource rawSource, ByteString byteString, Continuation continuation) {
        return SignatureVerifier.CC.tryVerifySignature$suspendImpl(this, rawSource, byteString, (Continuation<? super Boolean>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.SignatureVerifier
    public /* synthetic */ Object tryVerifySignature(ByteString byteString, ByteString byteString2, Continuation continuation) {
        return SignatureVerifier.CC.tryVerifySignature$suspendImpl(this, byteString, byteString2, (Continuation<? super Boolean>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.SignatureVerifier
    public /* synthetic */ Object tryVerifySignature(byte[] bArr, byte[] bArr2, Continuation continuation) {
        return SignatureVerifier.CC.tryVerifySignature$suspendImpl(this, bArr, bArr2, (Continuation<? super Boolean>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.SignatureVerifier
    public /* synthetic */ boolean tryVerifySignatureBlocking(RawSource rawSource, ByteString byteString) {
        return SignatureVerifier.CC.$default$tryVerifySignatureBlocking(this, rawSource, byteString);
    }

    @Override // dev.whyoleg.cryptography.operations.SignatureVerifier
    public /* synthetic */ boolean tryVerifySignatureBlocking(ByteString byteString, ByteString byteString2) {
        return SignatureVerifier.CC.$default$tryVerifySignatureBlocking(this, byteString, byteString2);
    }

    @Override // dev.whyoleg.cryptography.operations.SignatureVerifier
    public /* synthetic */ boolean tryVerifySignatureBlocking(byte[] bArr, byte[] bArr2) {
        return SignatureVerifier.CC.$default$tryVerifySignatureBlocking(this, bArr, bArr2);
    }

    @Override // dev.whyoleg.cryptography.operations.SignatureVerifier
    public /* synthetic */ Object verifySignature(RawSource rawSource, ByteString byteString, Continuation continuation) {
        return SignatureVerifier.CC.verifySignature$suspendImpl(this, rawSource, byteString, (Continuation<? super Unit>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.SignatureVerifier
    public /* synthetic */ Object verifySignature(ByteString byteString, ByteString byteString2, Continuation continuation) {
        return SignatureVerifier.CC.verifySignature$suspendImpl(this, byteString, byteString2, (Continuation<? super Unit>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.SignatureVerifier
    public /* synthetic */ Object verifySignature(byte[] bArr, byte[] bArr2, Continuation continuation) {
        return SignatureVerifier.CC.verifySignature$suspendImpl(this, bArr, bArr2, (Continuation<? super Unit>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.SignatureVerifier
    public /* synthetic */ void verifySignatureBlocking(RawSource rawSource, ByteString byteString) {
        SignatureVerifier.CC.$default$verifySignatureBlocking(this, rawSource, byteString);
    }

    @Override // dev.whyoleg.cryptography.operations.SignatureVerifier
    public /* synthetic */ void verifySignatureBlocking(ByteString byteString, ByteString byteString2) {
        SignatureVerifier.CC.$default$verifySignatureBlocking(this, byteString, byteString2);
    }

    @Override // dev.whyoleg.cryptography.operations.SignatureVerifier
    public /* synthetic */ void verifySignatureBlocking(byte[] bArr, byte[] bArr2) {
        SignatureVerifier.CC.$default$verifySignatureBlocking(this, bArr, bArr2);
    }

    public JdkSignatureVerifier(JdkCryptographyState state, PublicKey key, String algorithm, AlgorithmParameterSpec algorithmParameterSpec) {
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        this.key = key;
        this.parameters = algorithmParameterSpec;
        this.signature = state.signature(algorithm);
    }

    @Override // dev.whyoleg.cryptography.operations.SignatureVerifier
    public VerifyFunction createVerifyFunction() {
        return new JdkVerifyFunction(this.key, this.parameters, this.signature.borrowResource());
    }
}
