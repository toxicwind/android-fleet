package dev.whyoleg.cryptography.providers.jdk.algorithms;

import dev.whyoleg.cryptography.BinarySize;
import dev.whyoleg.cryptography.operations.Decryptor;
import dev.whyoleg.cryptography.providers.base.operations.BaseDecryptor;
import dev.whyoleg.cryptography.providers.base.operations.CipherFunction;
import dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState;
import dev.whyoleg.cryptography.providers.jdk.Pooled;
import dev.whyoleg.cryptography.providers.jdk.operations.JdkCipherFunction;
import io.ktor.http.ContentDisposition;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.interfaces.RSAKey;
import javax.crypto.Cipher;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.RawSink;
import kotlinx.io.RawSource;
import kotlinx.io.bytestring.ByteString;

/* JADX INFO: compiled from: JdkRsaRaw.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0011B\u001d\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006¢\u0006\u0004\b\u0007\u0010\bJ\n\u0010\u000f\u001a\u00020\u0010H\u0096\u0080\u0004R\u000f\u0010\u0002\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0013\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0019\u0010\t\u001a\f\u0012\b\u0012\u00060\u000bj\u0002`\f0\nX\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\r\u001a\u00020\u000eX\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/RsaRawDecryptor;", "Ldev/whyoleg/cryptography/providers/base/operations/BaseDecryptor;", "state", "Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "key", "Ljava/security/PrivateKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPrivateKey;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;Ljava/security/PrivateKey;)V", "cipher", "Ldev/whyoleg/cryptography/providers/jdk/Pooled;", "Ljavax/crypto/Cipher;", "Ldev/whyoleg/cryptography/providers/jdk/JCipher;", "outputSize", "", "createDecryptFunction", "Ldev/whyoleg/cryptography/providers/base/operations/CipherFunction;", "RsaRawDecryptFunction", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class RsaRawDecryptor implements BaseDecryptor {
    private final Pooled<Cipher> cipher;
    private final PrivateKey key;
    private final int outputSize;
    private final JdkCryptographyState state;

    @Override // dev.whyoleg.cryptography.operations.Decryptor
    public /* synthetic */ Object decrypt(ByteString byteString, Continuation continuation) {
        return Decryptor.CC.decrypt$suspendImpl(this, byteString, (Continuation<? super ByteString>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.Decryptor
    public /* synthetic */ Object decrypt(byte[] bArr, Continuation continuation) {
        return Decryptor.CC.decrypt$suspendImpl(this, bArr, (Continuation<? super byte[]>) continuation);
    }

    @Override // dev.whyoleg.cryptography.operations.Decryptor
    public /* synthetic */ ByteString decryptBlocking(ByteString byteString) {
        return Decryptor.CC.$default$decryptBlocking(this, byteString);
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseDecryptor, dev.whyoleg.cryptography.operations.Decryptor
    public /* synthetic */ byte[] decryptBlocking(byte[] bArr) {
        return BaseDecryptor.CC.$default$decryptBlocking(this, bArr);
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseDecryptor, dev.whyoleg.cryptography.operations.Decryptor
    public /* synthetic */ RawSink decryptingSink(RawSink rawSink) {
        return BaseDecryptor.CC.$default$decryptingSink(this, rawSink);
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseDecryptor, dev.whyoleg.cryptography.operations.Decryptor
    public /* synthetic */ RawSource decryptingSource(RawSource rawSource) {
        return BaseDecryptor.CC.$default$decryptingSource(this, rawSource);
    }

    public RsaRawDecryptor(JdkCryptographyState state, PrivateKey key) {
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(key, "key");
        this.state = state;
        this.key = key;
        this.cipher = JdkCryptographyState.cipher$default(state, "RSA/ECB/NoPadding", false, 2, null);
        BinarySize.Companion companion = BinarySize.INSTANCE;
        Intrinsics.checkNotNull(key, "null cannot be cast to non-null type java.security.interfaces.RSAKey");
        this.outputSize = BinarySize.m8661getInBytesimpl(companion.m8670getBitsM1VWhS4(((RSAKey) key).getModulus().bitLength()));
    }

    @Override // dev.whyoleg.cryptography.providers.base.operations.BaseDecryptor
    public CipherFunction createDecryptFunction() throws InvalidKeyException {
        int i = this.outputSize;
        Pooled.Resource<Cipher> resourceBorrowResource = this.cipher.borrowResource();
        resourceBorrowResource.access().init(2, this.key, this.state.getSecureRandom());
        Unit unit = Unit.INSTANCE;
        return new RsaRawDecryptFunction(i, resourceBorrowResource);
    }

    /* JADX INFO: compiled from: JdkRsaRaw.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u000b\b\u0002\u0018\u00002\u00020\u0001B#\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u00060\u0006j\u0002`\u00070\u0005¢\u0006\u0004\b\b\u0010\tJ\u0012\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0003H\u0094\u0080\u0004J\n\u0010\f\u001a\u00020\rH\u0094\u0080\u0004J\u001a\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0003H\u0094\u0080\u0004J\"\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0003H\u0094\u0080\u0004J2\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0003H\u0094\u0080\u0004J\u0016\u0010\u0016\u001a\u00020\r*\u00020\r2\u0006\u0010\u0017\u001a\u00020\u0003H\u0082\u0080\u0004R\u000f\u0010\u0002\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/algorithms/RsaRawDecryptor$RsaRawDecryptFunction;", "Ldev/whyoleg/cryptography/providers/jdk/operations/JdkCipherFunction;", "outputSize", "", "cipher", "Ldev/whyoleg/cryptography/providers/jdk/Pooled$Resource;", "Ljavax/crypto/Cipher;", "Ldev/whyoleg/cryptography/providers/jdk/JCipher;", "<init>", "(ILdev/whyoleg/cryptography/providers/jdk/Pooled$Resource;)V", "maxOutputSize", "inputSize", "finalizeToByteArray", "", "finalizeIntoByteArray", "destination", "destinationOffset", "transformAndFinalizeToByteArray", "source", "startIndex", "endIndex", "transformAndFinalizeIntoByteArray", "pad", ContentDisposition.Parameters.Size, "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private static final class RsaRawDecryptFunction extends JdkCipherFunction {
        private final int outputSize;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public RsaRawDecryptFunction(int i, Pooled.Resource<Cipher> cipher) {
            super(cipher);
            Intrinsics.checkNotNullParameter(cipher, "cipher");
            this.outputSize = i;
        }

        @Override // dev.whyoleg.cryptography.providers.jdk.operations.JdkCipherFunction, dev.whyoleg.cryptography.providers.base.operations.BaseCipherFunction
        protected int maxOutputSize(int inputSize) {
            return this.outputSize;
        }

        @Override // dev.whyoleg.cryptography.providers.jdk.operations.JdkCipherFunction, dev.whyoleg.cryptography.providers.base.operations.BaseCipherFunction
        protected byte[] finalizeToByteArray() {
            return pad(super.finalizeToByteArray(), this.outputSize);
        }

        @Override // dev.whyoleg.cryptography.providers.jdk.operations.JdkCipherFunction, dev.whyoleg.cryptography.providers.base.operations.BaseCipherFunction
        protected int finalizeIntoByteArray(byte[] destination, int destinationOffset) {
            Intrinsics.checkNotNullParameter(destination, "destination");
            super.finalizeIntoByteArray(destination, destinationOffset);
            return this.outputSize;
        }

        @Override // dev.whyoleg.cryptography.providers.jdk.operations.JdkCipherFunction, dev.whyoleg.cryptography.providers.base.operations.BaseCipherFunction
        protected byte[] transformAndFinalizeToByteArray(byte[] source, int startIndex, int endIndex) {
            Intrinsics.checkNotNullParameter(source, "source");
            return pad(super.transformAndFinalizeToByteArray(source, startIndex, endIndex), this.outputSize);
        }

        @Override // dev.whyoleg.cryptography.providers.jdk.operations.JdkCipherFunction, dev.whyoleg.cryptography.providers.base.operations.BaseCipherFunction
        protected int transformAndFinalizeIntoByteArray(byte[] source, byte[] destination, int destinationOffset, int startIndex, int endIndex) {
            Intrinsics.checkNotNullParameter(source, "source");
            Intrinsics.checkNotNullParameter(destination, "destination");
            super.transformAndFinalizeIntoByteArray(source, destination, destinationOffset, startIndex, endIndex);
            return this.outputSize;
        }

        private final byte[] pad(byte[] bArr, int i) {
            if (bArr.length == i) {
                return bArr;
            }
            byte[] bArr2 = new byte[i];
            ArraysKt.copyInto$default(bArr, bArr2, i - bArr.length, 0, 0, 12, (Object) null);
            return bArr2;
        }
    }
}
