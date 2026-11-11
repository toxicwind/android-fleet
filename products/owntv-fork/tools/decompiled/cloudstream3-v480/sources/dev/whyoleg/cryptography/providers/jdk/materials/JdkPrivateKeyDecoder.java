package dev.whyoleg.cryptography.providers.jdk.materials;

import androidx.tvprovider.media.tv.TvContractCompat;
import dev.whyoleg.cryptography.materials.Decoder;
import dev.whyoleg.cryptography.materials.EncodingFormat;
import dev.whyoleg.cryptography.providers.jdk.JdkCryptographyState;
import dev.whyoleg.cryptography.providers.jdk.Pooled;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.bytestring.ByteString;

/* JADX INFO: compiled from: JdkPrivateKeyDecoder.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b \u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\u0004\b\u0001\u0010\u00032\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u0004B\u0019\bF\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\u0011\u001a\u00028\u00012\u0006\u0010\u0012\u001a\u00020\u0013H\u0084\u0080\u0004¢\u0006\u0002\u0010\u0014J\u0017\u0010\u0015\u001a\u00028\u00012\u0006\u0010\u0016\u001a\u00020\u0017H\u0094\u0080\u0004¢\u0006\u0002\u0010\u0018J\u0016\u0010\u0019\u001a\u00060\u001aj\u0002`\u001b2\u0006\u0010\u0012\u001a\u00020\u0013H\u0084\u0080\u0004J\u0016\u0010\u001c\u001a\u00060\u001aj\u0002`\u001b2\u0006\u0010\u0016\u001a\u00020\u0017H\u0084\u0080\u0004J\u0017\u0010\u001d\u001a\u00028\u0001*\u00060\u001aj\u0002`\u001bH¤\u0080\u0004¢\u0006\u0002\u0010\u001eR\u0015\u0010\u0005\u001a\u00020\u0006X\u0084\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\r\u001a\f\u0012\b\u0012\u00060\u000fj\u0002`\u00100\u000eX\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/materials/JdkPrivateKeyDecoder;", "F", "Ldev/whyoleg/cryptography/materials/EncodingFormat;", "K", "Ldev/whyoleg/cryptography/materials/Decoder;", "state", "Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "algorithm", "", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;Ljava/lang/String;)V", "getState", "()Ldev/whyoleg/cryptography/providers/jdk/JdkCryptographyState;", "keyFactory", "Ldev/whyoleg/cryptography/providers/jdk/Pooled;", "Ljava/security/KeyFactory;", "Ldev/whyoleg/cryptography/providers/jdk/JKeyFactory;", "decode", "spec", "Ljava/security/spec/KeySpec;", "(Ljava/security/spec/KeySpec;)Ljava/lang/Object;", "decodeFromDer", TvContractCompat.PARAM_INPUT, "", "([B)Ljava/lang/Object;", "decodeRaw", "Ljava/security/PrivateKey;", "Ldev/whyoleg/cryptography/providers/jdk/JPrivateKey;", "decodeFromDerRaw", "convert", "(Ljava/security/PrivateKey;)Ljava/lang/Object;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class JdkPrivateKeyDecoder<F extends EncodingFormat, K> implements Decoder<F, K> {
    private final Pooled<KeyFactory> keyFactory;
    private final JdkCryptographyState state;

    protected abstract K convert(PrivateKey privateKey);

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

    public JdkPrivateKeyDecoder(JdkCryptographyState state, String algorithm) {
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        this.state = state;
        this.keyFactory = state.keyFactory(algorithm);
    }

    protected final JdkCryptographyState getState() {
        return this.state;
    }

    protected final K decode(KeySpec spec) {
        Intrinsics.checkNotNullParameter(spec, "spec");
        return convert(decodeRaw(spec));
    }

    protected K decodeFromDer(byte[] input) {
        Intrinsics.checkNotNullParameter(input, "input");
        return convert(decodeFromDerRaw(input));
    }

    protected final PrivateKey decodeRaw(KeySpec spec) {
        Intrinsics.checkNotNullParameter(spec, "spec");
        Pooled<KeyFactory> pooled = this.keyFactory;
        Object objBorrow = pooled.borrow();
        try {
            PrivateKey privateKeyGeneratePrivate = ((KeyFactory) objBorrow).generatePrivate(spec);
            pooled.recycle(objBorrow);
            Intrinsics.checkNotNullExpressionValue(privateKeyGeneratePrivate, "use(...)");
            return privateKeyGeneratePrivate;
        } catch (Throwable th) {
            pooled.recycle(objBorrow);
            throw th;
        }
    }

    protected final PrivateKey decodeFromDerRaw(byte[] input) {
        Intrinsics.checkNotNullParameter(input, "input");
        return decodeRaw(new PKCS8EncodedKeySpec(input));
    }
}
