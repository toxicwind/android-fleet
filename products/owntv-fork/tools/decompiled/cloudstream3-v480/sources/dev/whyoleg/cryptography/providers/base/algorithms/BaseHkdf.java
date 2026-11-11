package dev.whyoleg.cryptography.providers.base.algorithms;

import androidx.tvprovider.media.tv.TvContractCompat;
import dev.whyoleg.cryptography.BinarySize;
import dev.whyoleg.cryptography.CryptographyAlgorithmId;
import dev.whyoleg.cryptography.CryptographyProvider;
import dev.whyoleg.cryptography.CryptographyProviderApi;
import dev.whyoleg.cryptography.algorithms.Digest;
import dev.whyoleg.cryptography.algorithms.HKDF;
import dev.whyoleg.cryptography.algorithms.HMAC;
import dev.whyoleg.cryptography.materials.Decoder;
import dev.whyoleg.cryptography.operations.SecretDerivation;
import dev.whyoleg.cryptography.operations.SignFunction;
import dev.whyoleg.cryptography.operations.UpdateFunction;
import dev.whyoleg.cryptography.providers.base.BytesKt;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.bytestring.ByteString;

/* JADX INFO: compiled from: BaseHkdf.kt */
/* JADX INFO: loaded from: classes7.dex */
@CryptographyProviderApi
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\b'\u0018\u00002\u00020\u0001:\u0001\u0016B\u0011\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J;\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0096\u0080\u0004¢\u0006\u0004\b\u0012\u0010\u0013J\u0018\u0010\u0014\u001a\u00020\u00152\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH¤\u0080\u0004R\u000f\u0010\u0006\u001a\u00020\u0007X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Ldev/whyoleg/cryptography/providers/base/algorithms/BaseHkdf;", "Ldev/whyoleg/cryptography/algorithms/HKDF;", "provider", "Ldev/whyoleg/cryptography/CryptographyProvider;", "<init>", "(Ldev/whyoleg/cryptography/CryptographyProvider;)V", "hmac", "Ldev/whyoleg/cryptography/algorithms/HMAC;", "secretDerivation", "Ldev/whyoleg/cryptography/operations/SecretDerivation;", "digest", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/Digest;", "outputSize", "Ldev/whyoleg/cryptography/BinarySize;", "salt", "", "info", "secretDerivation-nkIq3jI", "(Ldev/whyoleg/cryptography/CryptographyAlgorithmId;I[B[B)Ldev/whyoleg/cryptography/operations/SecretDerivation;", "digestSize", "", "HkdfSecretDerivation", "cryptography-provider-base"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class BaseHkdf implements HKDF {
    private final HMAC hmac;

    protected abstract int digestSize(CryptographyAlgorithmId<Digest> digest);

    @Override // dev.whyoleg.cryptography.algorithms.HKDF, dev.whyoleg.cryptography.CryptographyAlgorithm
    public /* synthetic */ CryptographyAlgorithmId getId() {
        return HKDF.INSTANCE;
    }

    @Override // dev.whyoleg.cryptography.algorithms.HKDF
    /* JADX INFO: renamed from: secretDerivation-nkIq3jI */
    public /* synthetic */ SecretDerivation mo8704secretDerivationnkIq3jI(CryptographyAlgorithmId cryptographyAlgorithmId, int i, ByteString byteString, ByteString byteString2) {
        return HKDF.CC.m8706$default$secretDerivationnkIq3jI(this, cryptographyAlgorithmId, i, byteString, byteString2);
    }

    public BaseHkdf(CryptographyProvider provider) {
        Intrinsics.checkNotNullParameter(provider, "provider");
        this.hmac = (HMAC) provider.get(HMAC.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0020  */
    /* JADX WARN: Code duplicated, block: B:23:0x0030  */
    @Override // dev.whyoleg.cryptography.algorithms.HKDF
    /* JADX INFO: renamed from: secretDerivation-nkIq3jI */
    public SecretDerivation mo8705secretDerivationnkIq3jI(CryptographyAlgorithmId<Digest> digest, int outputSize, byte[] salt, byte[] info) {
        Intrinsics.checkNotNullParameter(digest, "digest");
        int iDigestSize = digestSize(digest);
        Decoder<HMAC.Key.Format, HMAC.Key> decoderKeyDecoder = this.hmac.keyDecoder(digest);
        if (salt == null) {
            salt = new byte[iDigestSize];
        } else {
            if (salt.length == 0) {
                salt = null;
            }
            if (salt == null) {
                salt = new byte[iDigestSize];
            }
        }
        byte[] bArr = salt;
        if (info == null) {
            info = BytesKt.getEmptyByteArray();
        } else {
            if (info.length == 0) {
                info = null;
            }
            if (info == null) {
                info = BytesKt.getEmptyByteArray();
            }
        }
        return new HkdfSecretDerivation(decoderKeyDecoder, iDigestSize, outputSize, bArr, info, null);
    }

    /* JADX INFO: compiled from: BaseHkdf.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B=\bF\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u0012\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u000bH\u0096\u0080\u0004R\u001b\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0006\u001a\u00020\u0007X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\tX\u0082\u0084\b¢\u0006\u0004\n\u0002\u0010\u000fR\u000f\u0010\n\u001a\u00020\u000bX\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\f\u001a\u00020\u000bX\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Ldev/whyoleg/cryptography/providers/base/algorithms/BaseHkdf$HkdfSecretDerivation;", "Ldev/whyoleg/cryptography/operations/SecretDerivation;", "decoder", "Ldev/whyoleg/cryptography/materials/Decoder;", "Ldev/whyoleg/cryptography/algorithms/HMAC$Key$Format;", "Ldev/whyoleg/cryptography/algorithms/HMAC$Key;", "digestSize", "", "outputSize", "Ldev/whyoleg/cryptography/BinarySize;", "salt", "", "info", "<init>", "(Ldev/whyoleg/cryptography/materials/Decoder;II[B[BLkotlin/jvm/internal/DefaultConstructorMarker;)V", "I", "deriveSecretToByteArrayBlocking", TvContractCompat.PARAM_INPUT, "cryptography-provider-base"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private static final class HkdfSecretDerivation implements SecretDerivation {
        private final Decoder<HMAC.Key.Format, HMAC.Key> decoder;
        private final int digestSize;
        private final byte[] info;
        private final int outputSize;
        private final byte[] salt;

        public /* synthetic */ HkdfSecretDerivation(Decoder decoder, int i, int i2, byte[] bArr, byte[] bArr2, DefaultConstructorMarker defaultConstructorMarker) {
            this(decoder, i, i2, bArr, bArr2);
        }

        @Override // dev.whyoleg.cryptography.operations.SecretDerivation
        public /* synthetic */ Object deriveSecret(ByteString byteString, Continuation continuation) {
            return SecretDerivation.CC.deriveSecret$suspendImpl(this, byteString, (Continuation<? super ByteString>) continuation);
        }

        @Override // dev.whyoleg.cryptography.operations.SecretDerivation
        public /* synthetic */ Object deriveSecret(byte[] bArr, Continuation continuation) {
            return SecretDerivation.CC.deriveSecret$suspendImpl(this, bArr, (Continuation<? super ByteString>) continuation);
        }

        @Override // dev.whyoleg.cryptography.operations.SecretDerivation
        public /* synthetic */ ByteString deriveSecretBlocking(ByteString byteString) {
            return SecretDerivation.CC.$default$deriveSecretBlocking(this, byteString);
        }

        @Override // dev.whyoleg.cryptography.operations.SecretDerivation
        public /* synthetic */ ByteString deriveSecretBlocking(byte[] bArr) {
            return SecretDerivation.CC.$default$deriveSecretBlocking(this, bArr);
        }

        @Override // dev.whyoleg.cryptography.operations.SecretDerivation
        public /* synthetic */ Object deriveSecretToByteArray(ByteString byteString, Continuation continuation) {
            return SecretDerivation.CC.deriveSecretToByteArray$suspendImpl(this, byteString, (Continuation<? super byte[]>) continuation);
        }

        @Override // dev.whyoleg.cryptography.operations.SecretDerivation
        public /* synthetic */ Object deriveSecretToByteArray(byte[] bArr, Continuation continuation) {
            return SecretDerivation.CC.deriveSecretToByteArray$suspendImpl(this, bArr, (Continuation<? super byte[]>) continuation);
        }

        @Override // dev.whyoleg.cryptography.operations.SecretDerivation
        public /* synthetic */ byte[] deriveSecretToByteArrayBlocking(ByteString byteString) {
            return SecretDerivation.CC.$default$deriveSecretToByteArrayBlocking(this, byteString);
        }

        private HkdfSecretDerivation(Decoder<HMAC.Key.Format, HMAC.Key> decoder, int i, int i2, byte[] salt, byte[] info) {
            Intrinsics.checkNotNullParameter(decoder, "decoder");
            Intrinsics.checkNotNullParameter(salt, "salt");
            Intrinsics.checkNotNullParameter(info, "info");
            this.decoder = decoder;
            this.digestSize = i;
            this.outputSize = i2;
            this.salt = salt;
            this.info = info;
        }

        @Override // dev.whyoleg.cryptography.operations.SecretDerivation
        public byte[] deriveSecretToByteArrayBlocking(byte[] input) {
            Intrinsics.checkNotNullParameter(input, "input");
            int iCeil = (int) Math.ceil(((double) BinarySize.m8661getInBytesimpl(this.outputSize)) / ((double) this.digestSize));
            if (iCeil > 255) {
                throw new IllegalArgumentException(("out length must be maximal 255 * hash-length; requested: " + ((Object) BinarySize.m8667toStringimpl(this.outputSize))).toString());
            }
            SignFunction signFunctionCreateSignFunction = this.decoder.decodeFromByteArrayBlocking(HMAC.Key.Format.RAW, this.decoder.decodeFromByteArrayBlocking(HMAC.Key.Format.RAW, this.salt).signatureGenerator().generateSignatureBlocking(input)).signatureGenerator().createSignFunction();
            byte[] emptyByteArray = BytesKt.getEmptyByteArray();
            byte[] emptyByteArray2 = BytesKt.getEmptyByteArray();
            int i = 0;
            while (i < iCeil) {
                SignFunction signFunction = signFunctionCreateSignFunction;
                UpdateFunction.CC.update$default(signFunction, emptyByteArray2, 0, 0, 6, (Object) null);
                UpdateFunction.CC.update$default(signFunction, this.info, 0, 0, 6, (Object) null);
                i++;
                UpdateFunction.CC.update$default(signFunction, new byte[]{(byte) i}, 0, 0, 6, (Object) null);
                emptyByteArray2 = signFunctionCreateSignFunction.signToByteArray();
                emptyByteArray = ArraysKt.plus(emptyByteArray, emptyByteArray2);
            }
            return BytesKt.ensureSizeExactly(emptyByteArray, BinarySize.m8661getInBytesimpl(this.outputSize));
        }
    }
}
