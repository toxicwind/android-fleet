package dev.whyoleg.cryptography.providers.base.materials;

import androidx.tvprovider.media.tv.TvContractCompat;
import dev.whyoleg.cryptography.CryptographyProviderApi;
import dev.whyoleg.cryptography.serialization.asn1.BitArray;
import dev.whyoleg.cryptography.serialization.asn1.Der;
import dev.whyoleg.cryptography.serialization.asn1.ObjectIdentifier;
import dev.whyoleg.cryptography.serialization.asn1.modules.AlgorithmIdentifier;
import dev.whyoleg.cryptography.serialization.asn1.modules.ECKt;
import dev.whyoleg.cryptography.serialization.asn1.modules.EcAlgorithmIdentifier;
import dev.whyoleg.cryptography.serialization.asn1.modules.EcParameters;
import dev.whyoleg.cryptography.serialization.asn1.modules.EcPrivateKey;
import dev.whyoleg.cryptography.serialization.asn1.modules.PrivateKeyInfo;
import dev.whyoleg.cryptography.serialization.asn1.modules.SubjectPublicKeyInfo;
import dev.whyoleg.cryptography.serialization.pem.PemDocument;
import dev.whyoleg.cryptography.serialization.pem.PemLabel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.bytestring.unsafe.UnsafeByteStringOperations;
import kotlinx.serialization.builtins.BuiltinSerializersKt;

/* JADX INFO: compiled from: keys.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0007\u001a!\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0001H\u0087\u0080\u0004¢\u0006\u0004\b\u0005\u0010\u0006\u001a!\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0001H\u0087\u0080\u0004¢\u0006\u0004\b\b\u0010\u0006\u001a!\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u0001H\u0087\u0080\u0004¢\u0006\u0004\b\f\u0010\u0006\u001a\u001a\u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u0001H\u0087\u0080\u0004\u001a!\u0010\u0010\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u0001H\u0087\u0080\u0004¢\u0006\u0004\b\u0011\u0010\u0006\u001a\"\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u0001H\u0087\u0080\u0004\u001a!\u0010\u0015\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u0001H\u0087\u0080\u0004¢\u0006\u0004\b\u0016\u0010\u0006\u001a\"\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u0001H\u0087\u0080\u0004\u001a\u0012\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u0001H\u0087\u0080\u0004\u001a\u0012\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u0001H\u0087\u0080\u0004¨\u0006\u001b"}, d2 = {"unwrapPem", "", "label", "Ldev/whyoleg/cryptography/serialization/pem/PemLabel;", "key", "unwrapPem-unSj4pc", "(Ljava/lang/String;[B)[B", "wrapPem", "wrapPem-unSj4pc", "unwrapSubjectPublicKeyInfo", "algorithm", "Ldev/whyoleg/cryptography/serialization/asn1/ObjectIdentifier;", "unwrapSubjectPublicKeyInfo-4RESAxk", "wrapSubjectPublicKeyInfo", "identifier", "Ldev/whyoleg/cryptography/serialization/asn1/modules/AlgorithmIdentifier;", "unwrapPrivateKeyInfo", "unwrapPrivateKeyInfo-4RESAxk", "wrapPrivateKeyInfo", "version", "", "unwrapCurvePrivateKeyInfo", "unwrapCurvePrivateKeyInfo-4RESAxk", "wrapCurvePrivateKeyInfo", "convertEcPrivateKeyFromPkcs8ToSec1", TvContractCompat.PARAM_INPUT, "convertEcPrivateKeyFromSec1ToPkcs8", "cryptography-provider-base"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class KeysKt {
    @CryptographyProviderApi
    /* JADX INFO: renamed from: unwrapPem-unSj4pc, reason: not valid java name */
    public static final byte[] m8744unwrapPemunSj4pc(String label, byte[] key) {
        Intrinsics.checkNotNullParameter(label, "label");
        Intrinsics.checkNotNullParameter(key, "key");
        PemDocument pemDocumentDecode = PemDocument.INSTANCE.decode(key);
        if (!PemLabel.m8804equalsimpl0(pemDocumentDecode.getLabel(), label)) {
            throw new IllegalStateException(("Wrong PEM label, expected " + ((Object) PemLabel.m8806toStringimpl(label)) + ", actual " + ((Object) PemLabel.m8806toStringimpl(pemDocumentDecode.getLabel()))).toString());
        }
        UnsafeByteStringOperations unsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
        return pemDocumentDecode.getContent().getData();
    }

    @CryptographyProviderApi
    /* JADX INFO: renamed from: wrapPem-unSj4pc, reason: not valid java name */
    public static final byte[] m8747wrapPemunSj4pc(String label, byte[] key) {
        Intrinsics.checkNotNullParameter(label, "label");
        Intrinsics.checkNotNullParameter(key, "key");
        return new PemDocument(label, UnsafeByteStringOperations.INSTANCE.wrapUnsafe(key), (DefaultConstructorMarker) null).encodeToByteArray();
    }

    @CryptographyProviderApi
    /* JADX INFO: renamed from: unwrapSubjectPublicKeyInfo-4RESAxk, reason: not valid java name */
    public static final byte[] m8746unwrapSubjectPublicKeyInfo4RESAxk(String algorithm, byte[] key) {
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        Intrinsics.checkNotNullParameter(key, "key");
        SubjectPublicKeyInfo subjectPublicKeyInfo = (SubjectPublicKeyInfo) Der.INSTANCE.decodeFromByteArray(SubjectPublicKeyInfo.INSTANCE.serializer(), key);
        if (!ObjectIdentifier.m8758equalsimpl0(subjectPublicKeyInfo.getAlgorithm().getAlgorithm(), algorithm)) {
            throw new IllegalStateException(("Expected algorithm '" + algorithm + "', received: '" + ((Object) ObjectIdentifier.m8760toStringimpl(subjectPublicKeyInfo.getAlgorithm().getAlgorithm())) + '\'').toString());
        }
        return subjectPublicKeyInfo.getSubjectPublicKey().getByteArray();
    }

    @CryptographyProviderApi
    public static final byte[] wrapSubjectPublicKeyInfo(AlgorithmIdentifier identifier, byte[] key) {
        Intrinsics.checkNotNullParameter(identifier, "identifier");
        Intrinsics.checkNotNullParameter(key, "key");
        return Der.INSTANCE.encodeToByteArray(SubjectPublicKeyInfo.INSTANCE.serializer(), new SubjectPublicKeyInfo(identifier, new BitArray(0, key)));
    }

    @CryptographyProviderApi
    /* JADX INFO: renamed from: unwrapPrivateKeyInfo-4RESAxk, reason: not valid java name */
    public static final byte[] m8745unwrapPrivateKeyInfo4RESAxk(String algorithm, byte[] key) {
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        Intrinsics.checkNotNullParameter(key, "key");
        PrivateKeyInfo privateKeyInfo = (PrivateKeyInfo) Der.INSTANCE.decodeFromByteArray(PrivateKeyInfo.INSTANCE.serializer(), key);
        if (!ObjectIdentifier.m8758equalsimpl0(privateKeyInfo.getPrivateKeyAlgorithm().getAlgorithm(), algorithm)) {
            throw new IllegalStateException(("Expected algorithm '" + algorithm + "', received: '" + ((Object) ObjectIdentifier.m8760toStringimpl(privateKeyInfo.getPrivateKeyAlgorithm().getAlgorithm())) + '\'').toString());
        }
        return privateKeyInfo.getPrivateKey();
    }

    @CryptographyProviderApi
    public static final byte[] wrapPrivateKeyInfo(int i, AlgorithmIdentifier identifier, byte[] key) {
        Intrinsics.checkNotNullParameter(identifier, "identifier");
        Intrinsics.checkNotNullParameter(key, "key");
        return Der.INSTANCE.encodeToByteArray(PrivateKeyInfo.INSTANCE.serializer(), new PrivateKeyInfo(i, identifier, key, (BitArray) null, 8, (DefaultConstructorMarker) null));
    }

    @CryptographyProviderApi
    /* JADX INFO: renamed from: unwrapCurvePrivateKeyInfo-4RESAxk, reason: not valid java name */
    public static final byte[] m8743unwrapCurvePrivateKeyInfo4RESAxk(String algorithm, byte[] key) {
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        Intrinsics.checkNotNullParameter(key, "key");
        return (byte[]) Der.INSTANCE.decodeFromByteArray(BuiltinSerializersKt.ByteArraySerializer(), m8745unwrapPrivateKeyInfo4RESAxk(algorithm, key));
    }

    @CryptographyProviderApi
    public static final byte[] wrapCurvePrivateKeyInfo(int i, AlgorithmIdentifier identifier, byte[] key) {
        Intrinsics.checkNotNullParameter(identifier, "identifier");
        Intrinsics.checkNotNullParameter(key, "key");
        return wrapPrivateKeyInfo(i, identifier, Der.INSTANCE.encodeToByteArray(BuiltinSerializersKt.ByteArraySerializer(), key));
    }

    @CryptographyProviderApi
    public static final byte[] convertEcPrivateKeyFromPkcs8ToSec1(byte[] input) {
        Intrinsics.checkNotNullParameter(input, "input");
        PrivateKeyInfo privateKeyInfo = (PrivateKeyInfo) Der.INSTANCE.decodeFromByteArray(PrivateKeyInfo.INSTANCE.serializer(), input);
        AlgorithmIdentifier privateKeyAlgorithm = privateKeyInfo.getPrivateKeyAlgorithm();
        if (!(privateKeyAlgorithm instanceof EcAlgorithmIdentifier)) {
            throw new IllegalStateException(("Expected algorithm '" + ((Object) ObjectIdentifier.m8760toStringimpl(ECKt.getEC(ObjectIdentifier.INSTANCE))) + "', received: '" + ((Object) ObjectIdentifier.m8760toStringimpl(privateKeyAlgorithm.getAlgorithm())) + '\'').toString());
        }
        EcPrivateKey ecPrivateKey = (EcPrivateKey) Der.INSTANCE.decodeFromByteArray(EcPrivateKey.INSTANCE.serializer(), privateKeyInfo.getPrivateKey());
        if (ecPrivateKey.getParameters() != null) {
            return privateKeyInfo.getPrivateKey();
        }
        return Der.INSTANCE.encodeToByteArray(EcPrivateKey.INSTANCE.serializer(), new EcPrivateKey(ecPrivateKey.getVersion(), ecPrivateKey.getPrivateKey(), ((EcAlgorithmIdentifier) privateKeyAlgorithm).getParameters(), ecPrivateKey.getPublicKey(), null));
    }

    @CryptographyProviderApi
    public static final byte[] convertEcPrivateKeyFromSec1ToPkcs8(byte[] input) {
        Intrinsics.checkNotNullParameter(input, "input");
        EcPrivateKey ecPrivateKey = (EcPrivateKey) Der.INSTANCE.decodeFromByteArray(EcPrivateKey.INSTANCE.serializer(), input);
        String parameters = ecPrivateKey.getParameters();
        if ((parameters != null ? EcParameters.m8777boximpl(parameters) : null) == null) {
            throw new IllegalStateException("EC Parameters are not present in the key".toString());
        }
        return Der.INSTANCE.encodeToByteArray(PrivateKeyInfo.INSTANCE.serializer(), new PrivateKeyInfo(0, new EcAlgorithmIdentifier(ecPrivateKey.getParameters(), null), input, (BitArray) null, 8, (DefaultConstructorMarker) null));
    }
}
