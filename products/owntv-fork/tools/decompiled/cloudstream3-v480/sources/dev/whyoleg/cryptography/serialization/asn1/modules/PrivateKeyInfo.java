package dev.whyoleg.cryptography.serialization.asn1.modules;

import dev.whyoleg.cryptography.serialization.asn1.BitArray;
import dev.whyoleg.cryptography.serialization.asn1.BitArray$$serializer;
import dev.whyoleg.cryptography.serialization.asn1.ContextSpecificTag;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.ContextualSerializer;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ByteArraySerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* JADX INFO: compiled from: KeyInfo.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 $2\u00020\u0001:\u0002#$B-\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bBA\b\u0010\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\n\u0010\u000fJ%\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0001¢\u0006\u0002\b\"R\u0015\u0010\u0002\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001d\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0084\b¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0015\u0010\u0006\u001a\u00020\u0007X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001f\u0010\b\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0084\b¢\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u0013\u001a\u0004\b\u0019\u0010\u001a¨\u0006%"}, d2 = {"Ldev/whyoleg/cryptography/serialization/asn1/modules/PrivateKeyInfo;", "", "version", "", "privateKeyAlgorithm", "Ldev/whyoleg/cryptography/serialization/asn1/modules/AlgorithmIdentifier;", "privateKey", "", "publicKey", "Ldev/whyoleg/cryptography/serialization/asn1/BitArray;", "<init>", "(ILdev/whyoleg/cryptography/serialization/asn1/modules/AlgorithmIdentifier;[BLdev/whyoleg/cryptography/serialization/asn1/BitArray;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IILdev/whyoleg/cryptography/serialization/asn1/modules/AlgorithmIdentifier;[BLdev/whyoleg/cryptography/serialization/asn1/BitArray;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getVersion", "()I", "getPrivateKeyAlgorithm$annotations", "()V", "getPrivateKeyAlgorithm", "()Ldev/whyoleg/cryptography/serialization/asn1/modules/AlgorithmIdentifier;", "getPrivateKey", "()[B", "getPublicKey$annotations", "getPublicKey", "()Ldev/whyoleg/cryptography/serialization/asn1/BitArray;", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$cryptography_serialization_asn1_modules", "$serializer", "Companion", "cryptography-serialization-asn1-modules"}, k = 1, mv = {2, 3, 0}, xi = 48)
@Serializable
public final class PrivateKeyInfo {
    private final byte[] privateKey;
    private final AlgorithmIdentifier privateKeyAlgorithm;
    private final BitArray publicKey;
    private final int version;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<KSerializer<Object>>[] $childSerializers = {null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: dev.whyoleg.cryptography.serialization.asn1.modules.PrivateKeyInfo$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return PrivateKeyInfo._childSerializers$_anonymous_();
        }
    }), null, null};

    /* JADX INFO: Access modifiers changed from: private */
    public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
        return new ContextualSerializer(Reflection.getOrCreateKotlinClass(AlgorithmIdentifier.class), DefaultAlgorithmIdentifierSerializer.INSTANCE, new KSerializer[0]);
    }

    public static /* synthetic */ void getPrivateKeyAlgorithm$annotations() {
    }

    @ContextSpecificTag(classIndex = 1, type = ContextSpecificTag.TagType.IMPLICIT)
    public static /* synthetic */ void getPublicKey$annotations() {
    }

    /* JADX INFO: compiled from: KeyInfo.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Ldev/whyoleg/cryptography/serialization/asn1/modules/PrivateKeyInfo$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Ldev/whyoleg/cryptography/serialization/asn1/modules/PrivateKeyInfo;", "cryptography-serialization-asn1-modules"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<PrivateKeyInfo> serializer() {
            return PrivateKeyInfo$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ PrivateKeyInfo(int i, int i2, AlgorithmIdentifier algorithmIdentifier, byte[] bArr, BitArray bitArray, SerializationConstructorMarker serializationConstructorMarker) {
        if (7 != (i & 7)) {
            PluginExceptionsKt.throwMissingFieldException(i, 7, PrivateKeyInfo$$serializer.INSTANCE.getDescriptor());
        }
        this.version = i2;
        this.privateKeyAlgorithm = algorithmIdentifier;
        this.privateKey = bArr;
        if ((i & 8) == 0) {
            this.publicKey = null;
        } else {
            this.publicKey = bitArray;
        }
    }

    public PrivateKeyInfo(int i, AlgorithmIdentifier privateKeyAlgorithm, byte[] privateKey, BitArray bitArray) {
        Intrinsics.checkNotNullParameter(privateKeyAlgorithm, "privateKeyAlgorithm");
        Intrinsics.checkNotNullParameter(privateKey, "privateKey");
        this.version = i;
        this.privateKeyAlgorithm = privateKeyAlgorithm;
        this.privateKey = privateKey;
        this.publicKey = bitArray;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$cryptography_serialization_asn1_modules(PrivateKeyInfo self, CompositeEncoder output, SerialDescriptor serialDesc) {
        Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
        output.encodeIntElement(serialDesc, 0, self.version);
        output.encodeSerializableElement(serialDesc, 1, lazyArr[1].getValue(), self.privateKeyAlgorithm);
        output.encodeSerializableElement(serialDesc, 2, ByteArraySerializer.INSTANCE, self.privateKey);
        if (!output.shouldEncodeElementDefault(serialDesc, 3) && self.publicKey == null) {
            return;
        }
        output.encodeNullableSerializableElement(serialDesc, 3, BitArray$$serializer.INSTANCE, self.publicKey);
    }

    public /* synthetic */ PrivateKeyInfo(int i, AlgorithmIdentifier algorithmIdentifier, byte[] bArr, BitArray bitArray, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, algorithmIdentifier, bArr, (i2 & 8) != 0 ? null : bitArray);
    }

    public final int getVersion() {
        return this.version;
    }

    public final AlgorithmIdentifier getPrivateKeyAlgorithm() {
        return this.privateKeyAlgorithm;
    }

    public final byte[] getPrivateKey() {
        return this.privateKey;
    }

    public final BitArray getPublicKey() {
        return this.publicKey;
    }
}
