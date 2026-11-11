package dev.whyoleg.cryptography.providers.jdk.materials;

import dev.whyoleg.cryptography.materials.Encodable;
import dev.whyoleg.cryptography.materials.EncodingFormat;
import java.security.Key;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.bytestring.ByteString;

/* JADX INFO: compiled from: JdkEncodableKey.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\b \u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0015\bF\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006¢\u0006\u0004\b\u0007\u0010\bJ\n\u0010\t\u001a\u00020\nH\u0084\u0080\u0004R\u0013\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/materials/JdkEncodableKey;", "F", "Ldev/whyoleg/cryptography/materials/EncodingFormat;", "Ldev/whyoleg/cryptography/materials/Encodable;", "key", "Ljava/security/Key;", "Ldev/whyoleg/cryptography/providers/jdk/JKey;", "<init>", "(Ljava/security/Key;)V", "encodeToDer", "", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class JdkEncodableKey<F extends EncodingFormat> implements Encodable<F> {
    private final Key key;

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

    public JdkEncodableKey(Key key) {
        Intrinsics.checkNotNullParameter(key, "key");
        this.key = key;
    }

    protected final byte[] encodeToDer() {
        if (Intrinsics.areEqual(this.key.getFormat(), "PKCS#8") || Intrinsics.areEqual(this.key.getFormat(), "X.509")) {
            byte[] encoded = this.key.getEncoded();
            Intrinsics.checkNotNullExpressionValue(encoded, "getEncoded(...)");
            return encoded;
        }
        throw new IllegalStateException(("Wrong JDK Key format, expected `PKCS#8` or `X.509 got `" + this.key.getFormat() + '`').toString());
    }
}
