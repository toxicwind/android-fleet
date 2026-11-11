package dev.whyoleg.cryptography.providers.jdk.operations;

import dev.whyoleg.cryptography.operations.UpdateFunction;
import dev.whyoleg.cryptography.operations.VerifyFunction;
import dev.whyoleg.cryptography.providers.base.BytesKt;
import dev.whyoleg.cryptography.providers.jdk.Pooled;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.AlgorithmParameterSpec;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.RawSink;
import kotlinx.io.RawSource;
import kotlinx.io.bytestring.ByteString;

/* JADX INFO: compiled from: JdkSignatureVerifier.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B1\bF\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0010\u0010\u0007\u001a\f\u0012\b\u0012\u00060\tj\u0002`\n0\b¢\u0006\u0004\b\u000b\u0010\fJ\"\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0096\u0080\u0004J\"\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0096\u0080\u0004J\n\u0010\u0017\u001a\u00020\u000eH\u0096\u0080\u0004J\"\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0096\u0080\u0004J\b\u0010\u0019\u001a\u00020\u000eH\u0016R\u0013\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0019\u0010\u0007\u001a\f\u0012\b\u0012\u00060\tj\u0002`\n0\bX\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/operations/JdkVerifyFunction;", "Ldev/whyoleg/cryptography/operations/VerifyFunction;", "key", "Ljava/security/PublicKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPublicKey;", "parameters", "Ljava/security/spec/AlgorithmParameterSpec;", "jsignature", "Ldev/whyoleg/cryptography/providers/jdk/Pooled$Resource;", "Ljava/security/Signature;", "Ldev/whyoleg/cryptography/providers/jdk/JSignature;", "<init>", "(Ljava/security/PublicKey;Ljava/security/spec/AlgorithmParameterSpec;Ldev/whyoleg/cryptography/providers/jdk/Pooled$Resource;)V", "update", "", "source", "", "startIndex", "", "endIndex", "tryVerify", "", "signature", "reset", "verify", "close", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class JdkVerifyFunction implements VerifyFunction {
    private final Pooled.Resource<Signature> jsignature;
    private final PublicKey key;
    private final AlgorithmParameterSpec parameters;

    @Override // dev.whyoleg.cryptography.operations.VerifyFunction
    public /* synthetic */ boolean tryVerify(ByteString byteString, int i, int i2) {
        return VerifyFunction.CC.$default$tryVerify(this, byteString, i, i2);
    }

    @Override // dev.whyoleg.cryptography.operations.UpdateFunction
    public /* synthetic */ void update(RawSource rawSource) {
        UpdateFunction.CC.$default$update(this, rawSource);
    }

    @Override // dev.whyoleg.cryptography.operations.UpdateFunction
    public /* synthetic */ void update(ByteString byteString, int i, int i2) {
        UpdateFunction.CC.$default$update(this, byteString, i, i2);
    }

    @Override // dev.whyoleg.cryptography.operations.UpdateFunction
    public /* synthetic */ RawSink updatingSink(RawSink rawSink) {
        return UpdateFunction.CC.$default$updatingSink(this, rawSink);
    }

    @Override // dev.whyoleg.cryptography.operations.UpdateFunction
    public /* synthetic */ RawSource updatingSource(RawSource rawSource) {
        return UpdateFunction.CC.$default$updatingSource(this, rawSource);
    }

    @Override // dev.whyoleg.cryptography.operations.VerifyFunction
    public /* synthetic */ void verify(ByteString byteString, int i, int i2) {
        VerifyFunction.CC.$default$verify(this, byteString, i, i2);
    }

    public JdkVerifyFunction(PublicKey key, AlgorithmParameterSpec algorithmParameterSpec, Pooled.Resource<Signature> jsignature) throws InvalidKeyException, InvalidAlgorithmParameterException {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(jsignature, "jsignature");
        this.key = key;
        this.parameters = algorithmParameterSpec;
        this.jsignature = jsignature;
        reset();
    }

    @Override // dev.whyoleg.cryptography.operations.UpdateFunction
    public void update(byte[] source, int startIndex, int endIndex) throws SignatureException {
        Intrinsics.checkNotNullParameter(source, "source");
        BytesKt.checkBounds(source.length, startIndex, endIndex);
        this.jsignature.access().update(source, startIndex, endIndex - startIndex);
    }

    @Override // dev.whyoleg.cryptography.operations.VerifyFunction
    public boolean tryVerify(byte[] signature, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(signature, "signature");
        BytesKt.checkBounds(signature.length, startIndex, endIndex);
        return this.jsignature.access().verify(signature, startIndex, endIndex - startIndex);
    }

    @Override // dev.whyoleg.cryptography.operations.UpdateFunction
    public void reset() throws InvalidKeyException, InvalidAlgorithmParameterException {
        Signature signatureAccess = this.jsignature.access();
        signatureAccess.initVerify(this.key);
        AlgorithmParameterSpec algorithmParameterSpec = this.parameters;
        if (algorithmParameterSpec != null) {
            signatureAccess.setParameter(algorithmParameterSpec);
        }
    }

    @Override // dev.whyoleg.cryptography.operations.VerifyFunction
    public void verify(byte[] signature, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(signature, "signature");
        if (!tryVerify(signature, startIndex, endIndex)) {
            throw new IllegalStateException("Invalid signature".toString());
        }
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        this.jsignature.close();
    }
}
