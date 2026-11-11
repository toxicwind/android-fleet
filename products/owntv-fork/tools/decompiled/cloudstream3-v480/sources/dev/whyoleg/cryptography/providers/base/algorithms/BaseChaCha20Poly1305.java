package dev.whyoleg.cryptography.providers.base.algorithms;

import dev.whyoleg.cryptography.CryptographyAlgorithmId;
import dev.whyoleg.cryptography.CryptographyProviderApi;
import dev.whyoleg.cryptography.CryptographySystem;
import dev.whyoleg.cryptography.algorithms.ChaCha20Poly1305;
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

/* JADX INFO: compiled from: BaseChaCha20Poly1305.kt */
/* JADX INFO: loaded from: classes7.dex */
@CryptographyProviderApi
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b'\u0018\u00002\u00020\u0001:\u0003\r\u000e\u000fB\t\bF¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H¤\u0080\u0004J\u0016\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00050\tH\u0086\u0080\u0004J\u0010\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\fH\u0086\u0080\u0004¨\u0006\u0010"}, d2 = {"Ldev/whyoleg/cryptography/providers/base/algorithms/BaseChaCha20Poly1305;", "Ldev/whyoleg/cryptography/algorithms/ChaCha20Poly1305;", "<init>", "()V", "wrapKey", "Ldev/whyoleg/cryptography/algorithms/ChaCha20Poly1305$Key;", "rawKey", "", "keyDecoder", "Ldev/whyoleg/cryptography/materials/Decoder;", "Ldev/whyoleg/cryptography/algorithms/ChaCha20Poly1305$Key$Format;", "keyGenerator", "Ldev/whyoleg/cryptography/operations/KeyGenerator;", "BaseKeyDecoder", "BaseKeyGenerator", "BaseKey", "cryptography-provider-base"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class BaseChaCha20Poly1305 implements ChaCha20Poly1305 {
    @Override // dev.whyoleg.cryptography.algorithms.ChaCha20Poly1305, dev.whyoleg.cryptography.CryptographyAlgorithm
    public /* synthetic */ CryptographyAlgorithmId getId() {
        return ChaCha20Poly1305.INSTANCE;
    }

    protected abstract ChaCha20Poly1305.Key wrapKey(byte[] rawKey);

    @Override // dev.whyoleg.cryptography.algorithms.ChaCha20Poly1305
    public final Decoder<ChaCha20Poly1305.Key.Format, ChaCha20Poly1305.Key> keyDecoder() {
        return new BaseKeyDecoder();
    }

    @Override // dev.whyoleg.cryptography.algorithms.ChaCha20Poly1305
    public final KeyGenerator<ChaCha20Poly1305.Key> keyGenerator() {
        return new BaseKeyGenerator();
    }

    /* JADX INFO: compiled from: BaseChaCha20Poly1305.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0000\b\u0082\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\bF¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\tH\u0096\u0080\u0004¨\u0006\n"}, d2 = {"Ldev/whyoleg/cryptography/providers/base/algorithms/BaseChaCha20Poly1305$BaseKeyDecoder;", "Ldev/whyoleg/cryptography/materials/Decoder;", "Ldev/whyoleg/cryptography/algorithms/ChaCha20Poly1305$Key$Format;", "Ldev/whyoleg/cryptography/algorithms/ChaCha20Poly1305$Key;", "<init>", "(Ldev/whyoleg/cryptography/providers/base/algorithms/BaseChaCha20Poly1305;)V", "decodeFromByteArrayBlocking", "format", "bytes", "", "cryptography-provider-base"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class BaseKeyDecoder implements Decoder<ChaCha20Poly1305.Key.Format, ChaCha20Poly1305.Key> {

        /* JADX INFO: compiled from: BaseChaCha20Poly1305.kt */
        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[ChaCha20Poly1305.Key.Format.values().length];
                try {
                    iArr[ChaCha20Poly1305.Key.Format.RAW.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[ChaCha20Poly1305.Key.Format.JWK.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        @Override // dev.whyoleg.cryptography.materials.Decoder
        public /* synthetic */ Object decodeFromByteArray(EncodingFormat encodingFormat, byte[] bArr, Continuation<? super ChaCha20Poly1305.Key> continuation) {
            return Decoder.CC.decodeFromByteArray$suspendImpl(this, encodingFormat, bArr, continuation);
        }

        @Override // dev.whyoleg.cryptography.materials.Decoder
        public /* synthetic */ Object decodeFromByteString(EncodingFormat encodingFormat, ByteString byteString, Continuation<? super ChaCha20Poly1305.Key> continuation) {
            return Decoder.CC.decodeFromByteString$suspendImpl(this, encodingFormat, byteString, continuation);
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [dev.whyoleg.cryptography.algorithms.ChaCha20Poly1305$Key, java.lang.Object] */
        @Override // dev.whyoleg.cryptography.materials.Decoder
        public /* synthetic */ ChaCha20Poly1305.Key decodeFromByteStringBlocking(EncodingFormat encodingFormat, ByteString byteString) {
            return Decoder.CC.$default$decodeFromByteStringBlocking(this, encodingFormat, byteString);
        }

        public BaseKeyDecoder() {
        }

        @Override // dev.whyoleg.cryptography.materials.Decoder
        public ChaCha20Poly1305.Key decodeFromByteArrayBlocking(ChaCha20Poly1305.Key.Format format, byte[] bytes) {
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
                bArrCopyOf = JsonWebKeys.INSTANCE.decodeSymmetricKey(ChaCha20Poly1305.INSTANCE, bytes);
            }
            if (bArrCopyOf.length != 32) {
                throw new IllegalArgumentException("ChaCha20-Poly1305 key size must be 256 bits".toString());
            }
            return BaseChaCha20Poly1305.this.wrapKey(bArrCopyOf);
        }
    }

    /* JADX INFO: compiled from: BaseChaCha20Poly1305.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\bF¢\u0006\u0004\b\u0003\u0010\u0004J\n\u0010\u0005\u001a\u00020\u0002H\u0096\u0080\u0004¨\u0006\u0006"}, d2 = {"Ldev/whyoleg/cryptography/providers/base/algorithms/BaseChaCha20Poly1305$BaseKeyGenerator;", "Ldev/whyoleg/cryptography/operations/KeyGenerator;", "Ldev/whyoleg/cryptography/algorithms/ChaCha20Poly1305$Key;", "<init>", "(Ldev/whyoleg/cryptography/providers/base/algorithms/BaseChaCha20Poly1305;)V", "generateKeyBlocking", "cryptography-provider-base"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class BaseKeyGenerator implements KeyGenerator<ChaCha20Poly1305.Key> {
        @Override // dev.whyoleg.cryptography.operations.KeyGenerator
        public /* synthetic */ Object generateKey(Continuation<? super ChaCha20Poly1305.Key> continuation) {
            return KeyGenerator.CC.generateKey$suspendImpl(this, continuation);
        }

        public BaseKeyGenerator() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // dev.whyoleg.cryptography.operations.KeyGenerator
        public ChaCha20Poly1305.Key generateKeyBlocking() {
            return BaseChaCha20Poly1305.this.wrapKey(CryptographySystem.INSTANCE.getDefaultRandom().nextBytes(32));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: compiled from: BaseChaCha20Poly1305.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b¤\u0004\u0018\u00002\u00020\u0001B\u0011\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH\u0086\u0080\u0004R\u0015\u0010\u0002\u001a\u00020\u0003X\u0084\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000b"}, d2 = {"Ldev/whyoleg/cryptography/providers/base/algorithms/BaseChaCha20Poly1305$BaseKey;", "Ldev/whyoleg/cryptography/algorithms/ChaCha20Poly1305$Key;", "key", "", "<init>", "(Ldev/whyoleg/cryptography/providers/base/algorithms/BaseChaCha20Poly1305;[B)V", "getKey", "()[B", "encodeToByteArrayBlocking", "format", "Ldev/whyoleg/cryptography/algorithms/ChaCha20Poly1305$Key$Format;", "cryptography-provider-base"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public abstract class BaseKey implements ChaCha20Poly1305.Key {
        private final byte[] key;
        final /* synthetic */ BaseChaCha20Poly1305 this$0;

        /* JADX INFO: compiled from: BaseChaCha20Poly1305.kt */
        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[ChaCha20Poly1305.Key.Format.values().length];
                try {
                    iArr[ChaCha20Poly1305.Key.Format.RAW.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[ChaCha20Poly1305.Key.Format.JWK.ordinal()] = 2;
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

        public BaseKey(BaseChaCha20Poly1305 baseChaCha20Poly1305, byte[] key) {
            Intrinsics.checkNotNullParameter(key, "key");
            this.this$0 = baseChaCha20Poly1305;
            this.key = key;
        }

        protected final byte[] getKey() {
            return this.key;
        }

        @Override // dev.whyoleg.cryptography.materials.Encodable
        public final byte[] encodeToByteArrayBlocking(ChaCha20Poly1305.Key.Format format) {
            Intrinsics.checkNotNullParameter(format, "format");
            int i = WhenMappings.$EnumSwitchMapping$0[format.ordinal()];
            if (i != 1) {
                if (i != 2) {
                    throw new NoWhenBranchMatchedException();
                }
                return JsonWebKeys.INSTANCE.encodeSymmetricKey(ChaCha20Poly1305.INSTANCE, this.key);
            }
            byte[] bArr = this.key;
            byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
            Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
            return bArrCopyOf;
        }
    }
}
