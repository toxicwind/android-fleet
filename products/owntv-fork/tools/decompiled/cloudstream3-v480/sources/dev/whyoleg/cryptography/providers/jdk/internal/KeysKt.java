package dev.whyoleg.cryptography.providers.jdk.internal;

import androidx.tvprovider.media.tv.TvContractCompat;
import dev.whyoleg.cryptography.serialization.asn1.BitArray;
import dev.whyoleg.cryptography.serialization.asn1.Der;
import dev.whyoleg.cryptography.serialization.asn1.ObjectIdentifier;
import dev.whyoleg.cryptography.serialization.asn1.modules.AlgorithmIdentifier;
import dev.whyoleg.cryptography.serialization.asn1.modules.ECKt;
import dev.whyoleg.cryptography.serialization.asn1.modules.EcAlgorithmIdentifier;
import dev.whyoleg.cryptography.serialization.asn1.modules.EcPrivateKey;
import dev.whyoleg.cryptography.serialization.asn1.modules.PrivateKeyInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: keys.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a#\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0001H\u0080\u0080\u0004¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u0014\u0010\u0007\u001a\u0004\u0018\u00010\u00012\u0006\u0010\b\u001a\u00020\u0001H\u0080\u0080\u0004¨\u0006\t"}, d2 = {"getPublicKeyFromPrivateKeyPkcs8", "", "algorithm", "Ldev/whyoleg/cryptography/serialization/asn1/ObjectIdentifier;", "key", "getPublicKeyFromPrivateKeyPkcs8-4RESAxk", "(Ljava/lang/String;[B)[B", "getEcPublicKeyFromPrivateKeyPkcs8", TvContractCompat.PARAM_INPUT, "cryptography-provider-jdk"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class KeysKt {
    /* JADX INFO: renamed from: getPublicKeyFromPrivateKeyPkcs8-4RESAxk, reason: not valid java name */
    public static final byte[] m8754getPublicKeyFromPrivateKeyPkcs84RESAxk(String algorithm, byte[] key) {
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        Intrinsics.checkNotNullParameter(key, "key");
        PrivateKeyInfo privateKeyInfo = (PrivateKeyInfo) Der.INSTANCE.decodeFromByteArray(PrivateKeyInfo.INSTANCE.serializer(), key);
        if (!ObjectIdentifier.m8758equalsimpl0(privateKeyInfo.getPrivateKeyAlgorithm().getAlgorithm(), algorithm)) {
            throw new IllegalStateException(("Expected algorithm '" + algorithm + "', received: '" + ((Object) ObjectIdentifier.m8760toStringimpl(privateKeyInfo.getPrivateKeyAlgorithm().getAlgorithm())) + '\'').toString());
        }
        BitArray publicKey = privateKeyInfo.getPublicKey();
        if (publicKey != null) {
            return publicKey.getByteArray();
        }
        return null;
    }

    public static final byte[] getEcPublicKeyFromPrivateKeyPkcs8(byte[] input) {
        Intrinsics.checkNotNullParameter(input, "input");
        PrivateKeyInfo privateKeyInfo = (PrivateKeyInfo) Der.INSTANCE.decodeFromByteArray(PrivateKeyInfo.INSTANCE.serializer(), input);
        AlgorithmIdentifier privateKeyAlgorithm = privateKeyInfo.getPrivateKeyAlgorithm();
        if (!(privateKeyAlgorithm instanceof EcAlgorithmIdentifier)) {
            throw new IllegalStateException(("Expected algorithm '" + ((Object) ObjectIdentifier.m8760toStringimpl(ECKt.getEC(ObjectIdentifier.INSTANCE))) + "', received: '" + ((Object) ObjectIdentifier.m8760toStringimpl(privateKeyAlgorithm.getAlgorithm())) + '\'').toString());
        }
        BitArray publicKey = ((EcPrivateKey) Der.INSTANCE.decodeFromByteArray(EcPrivateKey.INSTANCE.serializer(), privateKeyInfo.getPrivateKey())).getPublicKey();
        if (publicKey != null) {
            return publicKey.getByteArray();
        }
        return null;
    }
}
