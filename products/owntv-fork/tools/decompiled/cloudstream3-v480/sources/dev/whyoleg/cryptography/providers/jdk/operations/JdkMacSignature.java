package dev.whyoleg.cryptography.providers.jdk.operations;

import dev.whyoleg.cryptography.operations.SignFunction;
import dev.whyoleg.cryptography.operations.SignatureGenerator;
import dev.whyoleg.cryptography.operations.SignatureVerifier;
import dev.whyoleg.cryptography.operations.VerifyFunction;
import dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState;
import dev.whyoleg.cryptography.providers.jdk.Pooled;
import java.security.InvalidKeyException;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.RawSource;
import kotlinx.io.bytestring.ByteString;

/* JADX INFO: compiled from: JdkMacSignature.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B%\bF\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\u0010\u0005\u001a\u00060\u0006j\u0002`\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\n\u0010\u0010\u001a\u00020\u0011H\u0082\u0080\u0004J\n\u0010\u0012\u001a\u00020\u0013H\u0096\u0080\u0004J\n\u0010\u0014\u001a\u00020\u0015H\u0096\u0080\u0004R\u0013\u0010\u0005\u001a\u00060\u0006j\u0002`\u0007X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0019\u0010\f\u001a\f\u0012\b\u0012\u00060\u000ej\u0002`\u000f0\rX\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/operations/JdkMacSignature;", "Ldev/whyoleg/cryptography/operations/SignatureGenerator;", "Ldev/whyoleg/cryptography/operations/SignatureVerifier;", "state", "Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "key", "Ljavax/crypto/SecretKey;", "Ldev/whyoleg/cryptography/providers/jdk/JSecretKey;", "algorithm", "", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;Ljavax/crypto/SecretKey;Ljava/lang/String;)V", "mac", "Ldev/whyoleg/cryptography/providers/jdk/Pooled;", "Ljavax/crypto/Mac;", "Ldev/whyoleg/cryptography/providers/jdk/JMac;", "createFunction", "Ldev/whyoleg/cryptography/providers/jdk/operations/JdkMacFunction;", "createSignFunction", "Ldev/whyoleg/cryptography/operations/SignFunction;", "createVerifyFunction", "Ldev/whyoleg/cryptography/operations/VerifyFunction;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class JdkMacSignature implements SignatureGenerator, SignatureVerifier {
    private final SecretKey key;
    private final Pooled<Mac> mac;

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

    public JdkMacSignature(JdkCryptographyState state, SecretKey key, String algorithm) {
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        this.key = key;
        this.mac = state.mac(algorithm);
    }

    private final JdkMacFunction createFunction() throws InvalidKeyException {
        Pooled.Resource<Mac> resourceBorrowResource = this.mac.borrowResource();
        resourceBorrowResource.access().init(this.key);
        return new JdkMacFunction(resourceBorrowResource);
    }

    @Override // dev.whyoleg.cryptography.operations.SignatureGenerator
    public SignFunction createSignFunction() {
        return createFunction();
    }

    @Override // dev.whyoleg.cryptography.operations.SignatureVerifier
    public VerifyFunction createVerifyFunction() {
        return createFunction();
    }
}
