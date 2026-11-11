package dev.whyoleg.cryptography.providers.base.algorithms;

import dev.whyoleg.cryptography.BinarySize;
import dev.whyoleg.cryptography.CryptographyProviderApi;
import dev.whyoleg.cryptography.CryptographySystem;
import dev.whyoleg.cryptography.algorithms.AES;
import dev.whyoleg.cryptography.algorithms.AES.Key;
import dev.whyoleg.cryptography.materials.Decoder;
import dev.whyoleg.cryptography.materials.Encodable;
import dev.whyoleg.cryptography.materials.EncodingFormat;
import dev.whyoleg.cryptography.operations.KeyGenerator;
import dev.whyoleg.cryptography.providers.base.materials.JsonWebKeys;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.bytestring.ByteString;

/* JADX INFO: compiled from: BaseAes.kt */
/* JADX INFO: loaded from: classes7.dex */
@CryptographyProviderApi
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\b'\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0003\u0017\u0018\u0019B\t\bF¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\u0006\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020\bH¤\u0080\u0004¢\u0006\u0002\u0010\tJ\u0016\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00028\u00000\u000bH\u0086\u0080\u0004J\u001f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0086\u0080\u0004¢\u0006\u0004\b\u0011\u0010\u0012J\u0019\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000f\u001a\u00020\u0010H\u0082\u0080\u0004¢\u0006\u0004\b\u0015\u0010\u0016¨\u0006\u001a"}, d2 = {"Ldev/whyoleg/cryptography/providers/base/algorithms/BaseAes;", "K", "Ldev/whyoleg/cryptography/algorithms/AES$Key;", "Ldev/whyoleg/cryptography/algorithms/AES;", "<init>", "()V", "wrapKey", "rawKey", "", "([B)Ldev/whyoleg/cryptography/algorithms/AES$Key;", "keyDecoder", "Ldev/whyoleg/cryptography/materials/Decoder;", "Ldev/whyoleg/cryptography/algorithms/AES$Key$Format;", "keyGenerator", "Ldev/whyoleg/cryptography/operations/KeyGenerator;", "keySize", "Ldev/whyoleg/cryptography/BinarySize;", "keyGenerator-6q1zMKY", "(I)Ldev/whyoleg/cryptography/operations/KeyGenerator;", "requireAesKeySize", "", "requireAesKeySize-6q1zMKY", "(I)V", "BaseKeyDecoder", "BaseKeyGenerator", "BaseKey", "cryptography-provider-base"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class BaseAes<K extends AES.Key> implements AES<K> {
    @Override // dev.whyoleg.cryptography.algorithms.AES
    /* JADX INFO: renamed from: keyGenerator-__FLmac */
    public /* synthetic */ KeyGenerator mo8673keyGenerator__FLmac(int i) {
        return mo8672keyGenerator6q1zMKY(i);
    }

    protected abstract K wrapKey(byte[] rawKey);

    @Override // dev.whyoleg.cryptography.algorithms.AES
    public final Decoder<AES.Key.Format, K> keyDecoder() {
        return new BaseKeyDecoder();
    }

    @Override // dev.whyoleg.cryptography.algorithms.AES
    /* JADX INFO: renamed from: keyGenerator-6q1zMKY */
    public final KeyGenerator<K> mo8672keyGenerator6q1zMKY(int keySize) {
        return new BaseKeyGenerator(this, keySize, null);
    }

    /* JADX INFO: compiled from: BaseAes.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00028\u00000\u0001B\t\bF¢\u0006\u0004\b\u0003\u0010\u0004J\u001f\u0010\u0005\u001a\u00028\u00002\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0096\u0080\u0004¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Ldev/whyoleg/cryptography/providers/base/algorithms/BaseAes$BaseKeyDecoder;", "Ldev/whyoleg/cryptography/materials/Decoder;", "Ldev/whyoleg/cryptography/algorithms/AES$Key$Format;", "<init>", "(Ldev/whyoleg/cryptography/providers/base/algorithms/BaseAes;)V", "decodeFromByteArrayBlocking", "format", "bytes", "", "(Ldev/whyoleg/cryptography/algorithms/AES$Key$Format;[B)Ldev/whyoleg/cryptography/algorithms/AES$Key;", "cryptography-provider-base"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class BaseKeyDecoder implements Decoder<AES.Key.Format, K> {

        /* JADX INFO: compiled from: BaseAes.kt */
        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[AES.Key.Format.values().length];
                try {
                    iArr[AES.Key.Format.RAW.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[AES.Key.Format.JWK.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        @Override // dev.whyoleg.cryptography.materials.Decoder
        public /* synthetic */ Object decodeFromByteArray(EncodingFormat encodingFormat, byte[] bArr, Continuation continuation) {
            return Decoder.CC.decodeFromByteArray$suspendImpl(this, encodingFormat, bArr, continuation);
        }

        @Override // dev.whyoleg.cryptography.materials.Decoder
        public /* synthetic */ Object decodeFromByteString(EncodingFormat encodingFormat, ByteString byteString, Continuation continuation) {
            return Decoder.CC.decodeFromByteString$suspendImpl(this, encodingFormat, byteString, continuation);
        }

        @Override // dev.whyoleg.cryptography.materials.Decoder
        public /* synthetic */ Object decodeFromByteStringBlocking(EncodingFormat encodingFormat, ByteString byteString) {
            return Decoder.CC.$default$decodeFromByteStringBlocking(this, encodingFormat, byteString);
        }

        public BaseKeyDecoder() {
        }

        @Override // dev.whyoleg.cryptography.materials.Decoder
        public K decodeFromByteArrayBlocking(AES.Key.Format format, byte[] bytes) {
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
                bArrCopyOf = JsonWebKeys.INSTANCE.decodeSymmetricKey(BaseAes.this.getId(), bytes);
            }
            BaseAes.this.m8736requireAesKeySize6q1zMKY(BinarySize.INSTANCE.m8671getBytesM1VWhS4(bArrCopyOf.length));
            return (K) BaseAes.this.wrapKey(bArrCopyOf);
        }
    }

    /* JADX INFO: compiled from: BaseAes.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001B\u0011\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00028\u0000H\u0096\u0080\u0004¢\u0006\u0002\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\t"}, d2 = {"Ldev/whyoleg/cryptography/providers/base/algorithms/BaseAes$BaseKeyGenerator;", "Ldev/whyoleg/cryptography/operations/KeyGenerator;", "keySize", "Ldev/whyoleg/cryptography/BinarySize;", "<init>", "(Ldev/whyoleg/cryptography/providers/base/algorithms/BaseAes;ILkotlin/jvm/internal/DefaultConstructorMarker;)V", "I", "generateKeyBlocking", "()Ldev/whyoleg/cryptography/algorithms/AES$Key;", "cryptography-provider-base"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class BaseKeyGenerator implements KeyGenerator<K> {
        private final int keySize;

        public /* synthetic */ BaseKeyGenerator(BaseAes baseAes, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(i);
        }

        @Override // dev.whyoleg.cryptography.operations.KeyGenerator
        public /* synthetic */ Object generateKey(Continuation continuation) {
            return KeyGenerator.CC.generateKey$suspendImpl(this, continuation);
        }

        private BaseKeyGenerator(int i) {
            this.keySize = i;
            BaseAes.this.m8736requireAesKeySize6q1zMKY(i);
        }

        @Override // dev.whyoleg.cryptography.operations.KeyGenerator
        public K generateKeyBlocking() {
            return (K) BaseAes.this.wrapKey(CryptographySystem.INSTANCE.getDefaultRandom().nextBytes(BinarySize.m8661getInBytesimpl(this.keySize)));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: compiled from: BaseAes.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b¤\u0004\u0018\u00002\u00020\u0001B\u0011\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH\u0086\u0080\u0004R\u0015\u0010\u0002\u001a\u00020\u0003X\u0084\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000b"}, d2 = {"Ldev/whyoleg/cryptography/providers/base/algorithms/BaseAes$BaseKey;", "Ldev/whyoleg/cryptography/algorithms/AES$Key;", "key", "", "<init>", "(Ldev/whyoleg/cryptography/providers/base/algorithms/BaseAes;[B)V", "getKey", "()[B", "encodeToByteArrayBlocking", "format", "Ldev/whyoleg/cryptography/algorithms/AES$Key$Format;", "cryptography-provider-base"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public abstract class BaseKey implements AES.Key {
        private final byte[] key;
        final /* synthetic */ BaseAes<K> this$0;

        /* JADX INFO: compiled from: BaseAes.kt */
        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[AES.Key.Format.values().length];
                try {
                    iArr[AES.Key.Format.RAW.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[AES.Key.Format.JWK.ordinal()] = 2;
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

        public BaseKey(BaseAes baseAes, byte[] key) {
            Intrinsics.checkNotNullParameter(key, "key");
            this.this$0 = baseAes;
            this.key = key;
        }

        protected final byte[] getKey() {
            return this.key;
        }

        @Override // dev.whyoleg.cryptography.materials.Encodable
        public final byte[] encodeToByteArrayBlocking(AES.Key.Format format) {
            Intrinsics.checkNotNullParameter(format, "format");
            int i = WhenMappings.$EnumSwitchMapping$0[format.ordinal()];
            if (i != 1) {
                if (i != 2) {
                    throw new NoWhenBranchMatchedException();
                }
                return JsonWebKeys.INSTANCE.encodeSymmetricKey(this.this$0.getId(), this.key);
            }
            byte[] bArr = this.key;
            byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
            Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
            return bArrCopyOf;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: requireAesKeySize-6q1zMKY, reason: not valid java name */
    public final void m8736requireAesKeySize6q1zMKY(int keySize) {
        if (!BinarySize.m8659equalsimpl0(keySize, AES.Key.Size.INSTANCE.m8680getB128XsYwlU8()) && !BinarySize.m8659equalsimpl0(keySize, AES.Key.Size.INSTANCE.m8681getB192XsYwlU8()) && !BinarySize.m8659equalsimpl0(keySize, AES.Key.Size.INSTANCE.m8682getB256XsYwlU8())) {
            throw new IllegalArgumentException("AES key size must be 128, 192 or 256 bits".toString());
        }
    }
}
