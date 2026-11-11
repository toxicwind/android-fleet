package dev.whyoleg.cryptography.serialization.asn1.modules;

import dev.whyoleg.cryptography.serialization.asn1.BitArray;
import dev.whyoleg.cryptography.serialization.asn1.BitArray$$serializer;
import dev.whyoleg.cryptography.serialization.asn1.ContextSpecificTag;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ByteArraySerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* JADX INFO: compiled from: EC.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 %2\u00020\u0001:\u0002$%B1\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bBA\b\u0010\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\n\u0010\u000fJ%\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0001¢\u0006\u0002\b#R\u0015\u0010\u0002\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0015\u0010\u0004\u001a\u00020\u0005X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R!\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0084\b¢\u0006\u0010\n\u0002\u0010\u0018\u0012\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u001f\u0010\b\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0084\b¢\u0006\u000e\n\u0000\u0012\u0004\b\u0019\u0010\u0015\u001a\u0004\b\u001a\u0010\u001b¨\u0006&"}, d2 = {"Ldev/whyoleg/cryptography/serialization/asn1/modules/EcPrivateKey;", "", "version", "", "privateKey", "", "parameters", "Ldev/whyoleg/cryptography/serialization/asn1/modules/EcParameters;", "publicKey", "Ldev/whyoleg/cryptography/serialization/asn1/BitArray;", "<init>", "(I[BLjava/lang/String;Ldev/whyoleg/cryptography/serialization/asn1/BitArray;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(II[BLjava/lang/String;Ldev/whyoleg/cryptography/serialization/asn1/BitArray;Lkotlinx/serialization/internal/SerializationConstructorMarker;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getVersion", "()I", "getPrivateKey", "()[B", "getParameters-g4m2kjQ$annotations", "()V", "getParameters-g4m2kjQ", "()Ljava/lang/String;", "Ljava/lang/String;", "getPublicKey$annotations", "getPublicKey", "()Ldev/whyoleg/cryptography/serialization/asn1/BitArray;", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$cryptography_serialization_asn1_modules", "$serializer", "Companion", "cryptography-serialization-asn1-modules"}, k = 1, mv = {2, 3, 0}, xi = 48)
@Serializable
public final class EcPrivateKey {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String parameters;
    private final byte[] privateKey;
    private final BitArray publicKey;
    private final int version;

    public /* synthetic */ EcPrivateKey(int i, int i2, byte[] bArr, String str, BitArray bitArray, SerializationConstructorMarker serializationConstructorMarker, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, bArr, str, bitArray, serializationConstructorMarker);
    }

    public /* synthetic */ EcPrivateKey(int i, byte[] bArr, String str, BitArray bitArray, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, bArr, str, bitArray);
    }

    @ContextSpecificTag(classIndex = 0, type = ContextSpecificTag.TagType.EXPLICIT)
    /* JADX INFO: renamed from: getParameters-g4m2kjQ$annotations, reason: not valid java name */
    public static /* synthetic */ void m8796getParametersg4m2kjQ$annotations() {
    }

    @ContextSpecificTag(classIndex = 1, type = ContextSpecificTag.TagType.EXPLICIT)
    public static /* synthetic */ void getPublicKey$annotations() {
    }

    /* JADX INFO: compiled from: EC.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Ldev/whyoleg/cryptography/serialization/asn1/modules/EcPrivateKey$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Ldev/whyoleg/cryptography/serialization/asn1/modules/EcPrivateKey;", "cryptography-serialization-asn1-modules"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<EcPrivateKey> serializer() {
            return EcPrivateKey$$serializer.INSTANCE;
        }
    }

    private /* synthetic */ EcPrivateKey(int i, int i2, byte[] bArr, String str, BitArray bitArray, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (i & 3)) {
            PluginExceptionsKt.throwMissingFieldException(i, 3, EcPrivateKey$$serializer.INSTANCE.getDescriptor());
        }
        this.version = i2;
        this.privateKey = bArr;
        if ((i & 4) == 0) {
            this.parameters = null;
        } else {
            this.parameters = str;
        }
        if ((i & 8) == 0) {
            this.publicKey = null;
        } else {
            this.publicKey = bitArray;
        }
    }

    private EcPrivateKey(int i, byte[] privateKey, String str, BitArray bitArray) {
        Intrinsics.checkNotNullParameter(privateKey, "privateKey");
        this.version = i;
        this.privateKey = privateKey;
        this.parameters = str;
        this.publicKey = bitArray;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$cryptography_serialization_asn1_modules(EcPrivateKey self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeIntElement(serialDesc, 0, self.version);
        output.encodeSerializableElement(serialDesc, 1, ByteArraySerializer.INSTANCE, self.privateKey);
        if (output.shouldEncodeElementDefault(serialDesc, 2) || self.parameters != null) {
            EcParameters$$serializer ecParameters$$serializer = EcParameters$$serializer.INSTANCE;
            String str = self.parameters;
            output.encodeNullableSerializableElement(serialDesc, 2, ecParameters$$serializer, str != null ? EcParameters.m8777boximpl(str) : null);
        }
        if (!output.shouldEncodeElementDefault(serialDesc, 3) && self.publicKey == null) {
            return;
        }
        output.encodeNullableSerializableElement(serialDesc, 3, BitArray$$serializer.INSTANCE, self.publicKey);
    }

    public /* synthetic */ EcPrivateKey(int i, byte[] bArr, String str, BitArray bitArray, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, bArr, (i2 & 4) != 0 ? null : str, (i2 & 8) != 0 ? null : bitArray, null);
    }

    public final int getVersion() {
        return this.version;
    }

    public final byte[] getPrivateKey() {
        return this.privateKey;
    }

    /* JADX INFO: renamed from: getParameters-g4m2kjQ, reason: not valid java name and from getter */
    public final String getParameters() {
        return this.parameters;
    }

    public final BitArray getPublicKey() {
        return this.publicKey;
    }
}
