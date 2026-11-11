package dev.whyoleg.cryptography.providers.jdk.operations;

import dev.whyoleg.cryptography.operations.SignFunction;
import dev.whyoleg.cryptography.operations.UpdateFunction;
import dev.whyoleg.cryptography.providers.base.BytesKt;
import dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState;
import dev.whyoleg.cryptography.providers.jdk.Pooled;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.AlgorithmParameterSpec;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.RawSink;
import kotlinx.io.RawSource;
import kotlinx.io.bytestring.ByteString;
import kotlinx.io.bytestring.unsafe.UnsafeByteStringOperations;

/* JADX INFO: compiled from: JdkSignatureGenerator.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0002\u0018\u00002\u00020\u0001B9\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0010\u0010\t\u001a\f\u0012\b\u0012\u00060\u000bj\u0002`\f0\n¢\u0006\u0004\b\r\u0010\u000eJ\"\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0096\u0080\u0004J\u001a\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u0014H\u0096\u0080\u0004J\n\u0010\u0019\u001a\u00020\u0012H\u0096\u0080\u0004J\n\u0010\u001a\u001a\u00020\u0010H\u0096\u0080\u0004J\b\u0010\u001b\u001a\u00020\u0010H\u0016R\u000f\u0010\u0002\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0013\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0019\u0010\t\u001a\f\u0012\b\u0012\u00060\u000bj\u0002`\f0\nX\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/operations/JdkSignFunction;", "Ldev/whyoleg/cryptography/operations/SignFunction;", "state", "Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "key", "Ljava/security/PrivateKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPrivateKey;", "parameters", "Ljava/security/spec/AlgorithmParameterSpec;", "jsignature", "Ldev/whyoleg/cryptography/providers/jdk/Pooled$Resource;", "Ljava/security/Signature;", "Ldev/whyoleg/cryptography/providers/jdk/JSignature;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;Ljava/security/PrivateKey;Ljava/security/spec/AlgorithmParameterSpec;Ldev/whyoleg/cryptography/providers/jdk/Pooled$Resource;)V", "update", "", "source", "", "startIndex", "", "endIndex", "signIntoByteArray", "destination", "destinationOffset", "signToByteArray", "reset", "close", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class JdkSignFunction implements SignFunction {
    private final Pooled.Resource<Signature> jsignature;
    private final PrivateKey key;
    private final AlgorithmParameterSpec parameters;
    private final JdkCryptographyState state;

    @Override // dev.whyoleg.cryptography.operations.SignFunction
    public /* synthetic */ ByteString sign() {
        return UnsafeByteStringOperations.INSTANCE.wrapUnsafe(signToByteArray());
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

    public JdkSignFunction(JdkCryptographyState state, PrivateKey key, AlgorithmParameterSpec algorithmParameterSpec, Pooled.Resource<Signature> jsignature) throws InvalidKeyException, InvalidAlgorithmParameterException {
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(jsignature, "jsignature");
        this.state = state;
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

    @Override // dev.whyoleg.cryptography.operations.SignFunction
    public int signIntoByteArray(byte[] destination, int destinationOffset) throws SignatureException {
        Intrinsics.checkNotNullParameter(destination, "destination");
        byte[] bArrSignToByteArray = signToByteArray();
        BytesKt.checkBounds(destination.length, destinationOffset, bArrSignToByteArray.length + destinationOffset);
        ArraysKt.copyInto$default(bArrSignToByteArray, destination, destinationOffset, 0, 0, 12, (Object) null);
        return bArrSignToByteArray.length;
    }

    @Override // dev.whyoleg.cryptography.operations.SignFunction
    public byte[] signToByteArray() throws SignatureException {
        byte[] bArrSign = this.jsignature.access().sign();
        Intrinsics.checkNotNullExpressionValue(bArrSign, "sign(...)");
        return bArrSign;
    }

    @Override // dev.whyoleg.cryptography.operations.UpdateFunction
    public void reset() throws InvalidKeyException, InvalidAlgorithmParameterException {
        Signature signatureAccess = this.jsignature.access();
        signatureAccess.initSign(this.key, this.state.getSecureRandom());
        AlgorithmParameterSpec algorithmParameterSpec = this.parameters;
        if (algorithmParameterSpec != null) {
            signatureAccess.setParameter(algorithmParameterSpec);
        }
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        this.jsignature.close();
    }
}
