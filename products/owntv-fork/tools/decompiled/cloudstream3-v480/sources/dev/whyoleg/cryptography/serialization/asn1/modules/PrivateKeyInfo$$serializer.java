package dev.whyoleg.cryptography.serialization.asn1.modules;

import dev.whyoleg.cryptography.serialization.asn1.BitArray;
import dev.whyoleg.cryptography.serialization.asn1.BitArray$$serializer;
import dev.whyoleg.cryptography.serialization.asn1.ContextSpecificTag;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.ByteArraySerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: KeyInfo.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0019\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006H\u0086\u0080\u0004¢\u0006\u0002\u0010\bJ\u0012\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0086\u0080\u0004J\u001a\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0086\u0080\u0004R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"dev/whyoleg/cryptography/serialization/asn1/modules/PrivateKeyInfo.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Ldev/whyoleg/cryptography/serialization/asn1/modules/PrivateKeyInfo;", "<init>", "()V", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", ES6Iterator.VALUE_PROPERTY, "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "cryptography-serialization-asn1-modules"}, k = 1, mv = {2, 3, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly")
public final /* synthetic */ class PrivateKeyInfo$$serializer implements GeneratedSerializer<PrivateKeyInfo> {
    public static final PrivateKeyInfo$$serializer INSTANCE;
    private static final SerialDescriptor descriptor;

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public /* synthetic */ KSerializer[] typeParametersSerializers() {
        return GeneratedSerializer.CC.$default$typeParametersSerializers(this);
    }

    static {
        PrivateKeyInfo$$serializer privateKeyInfo$$serializer = new PrivateKeyInfo$$serializer();
        INSTANCE = privateKeyInfo$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("dev.whyoleg.cryptography.serialization.asn1.modules.PrivateKeyInfo", privateKeyInfo$$serializer, 4);
        pluginGeneratedSerialDescriptor.addElement("version", false);
        pluginGeneratedSerialDescriptor.addElement("privateKeyAlgorithm", false);
        pluginGeneratedSerialDescriptor.addElement("privateKey", false);
        final byte b = 1;
        pluginGeneratedSerialDescriptor.addElement("publicKey", true);
        final ContextSpecificTag.TagType tagType = ContextSpecificTag.TagType.IMPLICIT;
        pluginGeneratedSerialDescriptor.pushAnnotation(new ContextSpecificTag(b, tagType) { // from class: dev.whyoleg.cryptography.serialization.asn1.modules.PrivateKeyInfo$$serializer$annotationImpl$dev_whyoleg_cryptography_serialization_asn1_ContextSpecificTag$0
            private final /* synthetic */ byte classIndex;
            private final /* synthetic */ ContextSpecificTag.TagType type;

            {
                Intrinsics.checkNotNullParameter(tagType, "type");
                this.type = tagType;
            }

            @Override // java.lang.annotation.Annotation
            public final /* synthetic */ Class annotationType() {
                return ContextSpecificTag.class;
            }

            @Override // dev.whyoleg.cryptography.serialization.asn1.ContextSpecificTag
            public final /* synthetic */ byte classIndex() {
                return this.classIndex;
            }

            @Override // java.lang.annotation.Annotation
            public final boolean equals(Object obj) {
                if (!(obj instanceof ContextSpecificTag)) {
                    return false;
                }
                ContextSpecificTag contextSpecificTag = (ContextSpecificTag) obj;
                PrivateKeyInfo$$serializer$annotationImpl$dev_whyoleg_cryptography_serialization_asn1_ContextSpecificTag$0 privateKeyInfo$$serializer$annotationImpl$dev_whyoleg_cryptography_serialization_asn1_ContextSpecificTag$0 = this;
                return privateKeyInfo$$serializer$annotationImpl$dev_whyoleg_cryptography_serialization_asn1_ContextSpecificTag$0.classIndex() == contextSpecificTag.classIndex() && privateKeyInfo$$serializer$annotationImpl$dev_whyoleg_cryptography_serialization_asn1_ContextSpecificTag$0.type() == contextSpecificTag.type();
            }

            @Override // java.lang.annotation.Annotation
            public final int hashCode() {
                return ((-815570234) ^ this.classIndex) + (this.type.hashCode() ^ 454102470);
            }

            @Override // java.lang.annotation.Annotation
            public final String toString() {
                return "@dev.whyoleg.cryptography.serialization.asn1.ContextSpecificTag(classIndex=" + ((int) this.classIndex) + ", type=" + this.type + ')';
            }

            @Override // dev.whyoleg.cryptography.serialization.asn1.ContextSpecificTag
            public final /* synthetic */ ContextSpecificTag.TagType type() {
                return this.type;
            }
        });
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private PrivateKeyInfo$$serializer() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public final KSerializer<?>[] childSerializers() {
        return new KSerializer[]{IntSerializer.INSTANCE, PrivateKeyInfo.$childSerializers[1].getValue(), ByteArraySerializer.INSTANCE, BuiltinSerializersKt.getNullable(BitArray$$serializer.INSTANCE)};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public final PrivateKeyInfo deserialize(Decoder decoder) {
        int iDecodeIntElement;
        int i;
        AlgorithmIdentifier algorithmIdentifier;
        byte[] bArr;
        BitArray bitArray;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        Lazy[] lazyArr = PrivateKeyInfo.$childSerializers;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            iDecodeIntElement = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 0);
            AlgorithmIdentifier algorithmIdentifier2 = (AlgorithmIdentifier) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 1, (DeserializationStrategy) lazyArr[1].getValue(), null);
            byte[] bArr2 = (byte[]) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 2, ByteArraySerializer.INSTANCE, null);
            algorithmIdentifier = algorithmIdentifier2;
            bitArray = (BitArray) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 3, BitArray$$serializer.INSTANCE, null);
            bArr = bArr2;
            i = 15;
        } else {
            boolean z = true;
            iDecodeIntElement = 0;
            AlgorithmIdentifier algorithmIdentifier3 = null;
            byte[] bArr3 = null;
            BitArray bitArray2 = null;
            int i2 = 0;
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(serialDescriptor);
                if (iDecodeElementIndex == -1) {
                    z = false;
                } else if (iDecodeElementIndex == 0) {
                    iDecodeIntElement = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 0);
                    i2 |= 1;
                } else if (iDecodeElementIndex == 1) {
                    algorithmIdentifier3 = (AlgorithmIdentifier) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 1, (DeserializationStrategy) lazyArr[1].getValue(), algorithmIdentifier3);
                    i2 |= 2;
                } else if (iDecodeElementIndex == 2) {
                    bArr3 = (byte[]) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 2, ByteArraySerializer.INSTANCE, bArr3);
                    i2 |= 4;
                } else {
                    if (iDecodeElementIndex != 3) {
                        throw new UnknownFieldException(iDecodeElementIndex);
                    }
                    bitArray2 = (BitArray) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 3, BitArray$$serializer.INSTANCE, bitArray2);
                    i2 |= 8;
                }
            }
            i = i2;
            algorithmIdentifier = algorithmIdentifier3;
            bArr = bArr3;
            bitArray = bitArray2;
        }
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return new PrivateKeyInfo(i, iDecodeIntElement, algorithmIdentifier, bArr, bitArray, (SerializationConstructorMarker) null);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(Encoder encoder, PrivateKeyInfo value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        PrivateKeyInfo.write$Self$cryptography_serialization_asn1_modules(value, compositeEncoderBeginStructure, serialDescriptor);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }
}
