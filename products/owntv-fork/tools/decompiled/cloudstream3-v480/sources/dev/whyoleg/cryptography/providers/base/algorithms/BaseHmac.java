package dev.whyoleg.cryptography.providers.base.algorithms;

import dev.whyoleg.cryptography.CryptographyAlgorithmId;
import dev.whyoleg.cryptography.CryptographyProviderApi;
import dev.whyoleg.cryptography.CryptographySystem;
import dev.whyoleg.cryptography.algorithms.Digest;
import dev.whyoleg.cryptography.algorithms.HMAC;
import dev.whyoleg.cryptography.materials.Decoder;
import dev.whyoleg.cryptography.materials.Encodable;
import dev.whyoleg.cryptography.materials.EncodingFormat;
import dev.whyoleg.cryptography.operations.KeyGenerator;
import dev.whyoleg.cryptography.providers.base.materials.JsonWebKeys;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.bytestring.ByteString;

/* JADX INFO: compiled from: BaseHmac.kt */
/* JADX INFO: loaded from: classes7.dex */
@CryptographyProviderApi
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b'\u0018\u00002\u00020\u0001:\u0003\u0012\u0013\u0014B\t\bF¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\nH¤\u0080\u0004J\u0018\u0010\u000b\u001a\u00020\f2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H¤\u0080\u0004J$\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00050\u000e2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0086\u0080\u0004J\u001e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\u00112\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0086\u0080\u0004¨\u0006\u0015"}, d2 = {"Ldev/whyoleg/cryptography/providers/base/algorithms/BaseHmac;", "Ldev/whyoleg/cryptography/algorithms/HMAC;", "<init>", "()V", "wrapKey", "Ldev/whyoleg/cryptography/algorithms/HMAC$Key;", "digest", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/Digest;", "rawKey", "", "blockSize", "", "keyDecoder", "Ldev/whyoleg/cryptography/materials/Decoder;", "Ldev/whyoleg/cryptography/algorithms/HMAC$Key$Format;", "keyGenerator", "Ldev/whyoleg/cryptography/operations/KeyGenerator;", "BaseKeyDecoder", "BaseKeyGenerator", "BaseKey", "cryptography-provider-base"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class BaseHmac implements HMAC {
    protected abstract int blockSize(CryptographyAlgorithmId<Digest> digest);

    @Override // dev.whyoleg.cryptography.algorithms.HMAC, dev.whyoleg.cryptography.CryptographyAlgorithm
    public /* synthetic */ CryptographyAlgorithmId getId() {
        return HMAC.INSTANCE;
    }

    protected abstract HMAC.Key wrapKey(CryptographyAlgorithmId<Digest> digest, byte[] rawKey);

    @Override // dev.whyoleg.cryptography.algorithms.HMAC
    public final Decoder<HMAC.Key.Format, HMAC.Key> keyDecoder(CryptographyAlgorithmId<Digest> digest) {
        Intrinsics.checkNotNullParameter(digest, "digest");
        return new BaseKeyDecoder(this, digest);
    }

    @Override // dev.whyoleg.cryptography.algorithms.HMAC
    public final KeyGenerator<HMAC.Key> keyGenerator(CryptographyAlgorithmId<Digest> digest) {
        Intrinsics.checkNotNullParameter(digest, "digest");
        return new BaseKeyGenerator(this, digest);
    }

    /* JADX INFO: compiled from: BaseHmac.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0000\b\u0082\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0017\bF\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u001a\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0096\u0080\u0004R\u0015\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Ldev/whyoleg/cryptography/providers/base/algorithms/BaseHmac$BaseKeyDecoder;", "Ldev/whyoleg/cryptography/materials/Decoder;", "Ldev/whyoleg/cryptography/algorithms/HMAC$Key$Format;", "Ldev/whyoleg/cryptography/algorithms/HMAC$Key;", "digest", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/Digest;", "<init>", "(Ldev/whyoleg/cryptography/providers/base/algorithms/BaseHmac;Ldev/whyoleg/cryptography/CryptographyAlgorithmId;)V", "decodeFromByteArrayBlocking", "format", "bytes", "", "cryptography-provider-base"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class BaseKeyDecoder implements Decoder<HMAC.Key.Format, HMAC.Key> {
        private final CryptographyAlgorithmId<Digest> digest;
        final /* synthetic */ BaseHmac this$0;

        /* JADX INFO: compiled from: BaseHmac.kt */
        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[HMAC.Key.Format.values().length];
                try {
                    iArr[HMAC.Key.Format.RAW.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[HMAC.Key.Format.JWK.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        @Override // dev.whyoleg.cryptography.materials.Decoder
        public /* synthetic */ Object decodeFromByteArray(EncodingFormat encodingFormat, byte[] bArr, Continuation<? super HMAC.Key> continuation) {
            return Decoder.CC.decodeFromByteArray$suspendImpl(this, encodingFormat, bArr, continuation);
        }

        @Override // dev.whyoleg.cryptography.materials.Decoder
        public /* synthetic */ Object decodeFromByteString(EncodingFormat encodingFormat, ByteString byteString, Continuation<? super HMAC.Key> continuation) {
            return Decoder.CC.decodeFromByteString$suspendImpl(this, encodingFormat, byteString, continuation);
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [dev.whyoleg.cryptography.algorithms.HMAC$Key, java.lang.Object] */
        @Override // dev.whyoleg.cryptography.materials.Decoder
        public /* synthetic */ HMAC.Key decodeFromByteStringBlocking(EncodingFormat encodingFormat, ByteString byteString) {
            return Decoder.CC.$default$decodeFromByteStringBlocking(this, encodingFormat, byteString);
        }

        public BaseKeyDecoder(BaseHmac baseHmac, CryptographyAlgorithmId<Digest> digest) {
            Intrinsics.checkNotNullParameter(digest, "digest");
            this.this$0 = baseHmac;
            this.digest = digest;
        }

        @Override // dev.whyoleg.cryptography.materials.Decoder
        public HMAC.Key decodeFromByteArrayBlocking(HMAC.Key.Format format, byte[] bytes) {
            byte[] bArrCopyOf;
            Intrinsics.checkNotNullParameter(format, "format");
            Intrinsics.checkNotNullParameter(bytes, "bytes");
            int i = WhenMappings.$EnumSwitchMapping$0[format.ordinal()];
            if (i == 1) {
                bArrCopyOf = Arrays.copyOf(bytes, bytes.length);
                Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
            } else {
                if (i != 2) {
                    throw new NoWhenBranchMatchedException();
                }
                bArrCopyOf = JsonWebKeys.INSTANCE.decodeHmacKey(this.digest, bytes);
            }
            return this.this$0.wrapKey(this.digest, bArrCopyOf);
        }
    }

    /* JADX INFO: compiled from: BaseHmac.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\bF\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\n\u0010\b\u001a\u00020\u0002H\u0096\u0080\u0004R\u0015\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Ldev/whyoleg/cryptography/providers/base/algorithms/BaseHmac$BaseKeyGenerator;", "Ldev/whyoleg/cryptography/operations/KeyGenerator;", "Ldev/whyoleg/cryptography/algorithms/HMAC$Key;", "digest", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/Digest;", "<init>", "(Ldev/whyoleg/cryptography/providers/base/algorithms/BaseHmac;Ldev/whyoleg/cryptography/CryptographyAlgorithmId;)V", "generateKeyBlocking", "cryptography-provider-base"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class BaseKeyGenerator implements KeyGenerator<HMAC.Key> {
        private final CryptographyAlgorithmId<Digest> digest;
        final /* synthetic */ BaseHmac this$0;

        @Override // dev.whyoleg.cryptography.operations.KeyGenerator
        public /* synthetic */ Object generateKey(Continuation<? super HMAC.Key> continuation) {
            return KeyGenerator.CC.generateKey$suspendImpl(this, continuation);
        }

        public BaseKeyGenerator(BaseHmac baseHmac, CryptographyAlgorithmId<Digest> digest) {
            Intrinsics.checkNotNullParameter(digest, "digest");
            this.this$0 = baseHmac;
            this.digest = digest;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // dev.whyoleg.cryptography.operations.KeyGenerator
        public HMAC.Key generateKeyBlocking() {
            return this.this$0.wrapKey(this.digest, CryptographySystem.INSTANCE.getDefaultRandom().nextBytes(this.this$0.blockSize(this.digest)));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: compiled from: BaseHmac.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b¤\u0004\u0018\u00002\u00020\u0001B\u001f\bF\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0012\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rH\u0086\u0080\u0004R\u0015\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0015\u0010\u0005\u001a\u00020\u0006X\u0084\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000e"}, d2 = {"Ldev/whyoleg/cryptography/providers/base/algorithms/BaseHmac$BaseKey;", "Ldev/whyoleg/cryptography/algorithms/HMAC$Key;", "digest", "Ldev/whyoleg/cryptography/CryptographyAlgorithmId;", "Ldev/whyoleg/cryptography/algorithms/Digest;", "key", "", "<init>", "(Ldev/whyoleg/cryptography/providers/base/algorithms/BaseHmac;Ldev/whyoleg/cryptography/CryptographyAlgorithmId;[B)V", "getKey", "()[B", "encodeToByteArrayBlocking", "format", "Ldev/whyoleg/cryptography/algorithms/HMAC$Key$Format;", "cryptography-provider-base"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public abstract class BaseKey implements HMAC.Key {
        private final CryptographyAlgorithmId<Digest> digest;
        private final byte[] key;
        final /* synthetic */ BaseHmac this$0;

        /* JADX INFO: compiled from: BaseHmac.kt */
        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[HMAC.Key.Format.values().length];
                try {
                    iArr[HMAC.Key.Format.RAW.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[HMAC.Key.Format.JWK.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        @Override // dev.whyoleg.cryptography.materials.Encodable
        public /* synthetic */ Object encodeToByteArray(EncodingFormat encodingFormat, Continuation continuation) {
            return Encodable.CC.encodeToByteArray$suspendImpl(this, encodingFormat, continuation);
        }

        @Override // dev.whyoleg.cryptography.materials.Encodable
        public /* synthetic */ Object encodeToByteString(EncodingFormat encodingFormat, Continuation continuation) {
            return Encodable.CC.encodeToByteString$suspendImpl(this, encodingFormat, continuation);
        }

        @Override // dev.whyoleg.cryptography.materials.Encodable
        public /* synthetic */ ByteString encodeToByteStringBlocking(EncodingFormat encodingFormat) {
            return Encodable.CC.$default$encodeToByteStringBlocking(this, encodingFormat);
        }

        public BaseKey(BaseHmac baseHmac, CryptographyAlgorithmId<Digest> digest, byte[] key) {
            Intrinsics.checkNotNullParameter(digest, "digest");
            Intrinsics.checkNotNullParameter(key, "key");
            this.this$0 = baseHmac;
            this.digest = digest;
            this.key = key;
        }

        protected final byte[] getKey() {
            return this.key;
        }

        @Override // dev.whyoleg.cryptography.materials.Encodable
        public final byte[] encodeToByteArrayBlocking(HMAC.Key.Format format) {
            Intrinsics.checkNotNullParameter(format, "format");
            int i = WhenMappings.$EnumSwitchMapping$0[format.ordinal()];
            if (i != 1) {
                if (i != 2) {
                    throw new NoWhenBranchMatchedException();
                }
                return JsonWebKeys.INSTANCE.encodeHmacKey(this.digest, this.key);
            }
            byte[] bArr = this.key;
            byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
            Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
            return bArrCopyOf;
        }
    }
}
