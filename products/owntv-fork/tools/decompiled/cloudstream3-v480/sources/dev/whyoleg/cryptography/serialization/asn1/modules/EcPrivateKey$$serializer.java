package dev.whyoleg.cryptography.serialization.asn1.modules;

import dev.whyoleg.cryptography.serialization.asn1.BitArray;
import dev.whyoleg.cryptography.serialization.asn1.BitArray$$serializer;
import dev.whyoleg.cryptography.serialization.asn1.ContextSpecificTag;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
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
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: EC.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0019\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006H\u0086\u0080\u0004¢\u0006\u0002\u0010\bJ\u0012\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0086\u0080\u0004J\u001a\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0086\u0080\u0004R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"dev/whyoleg/cryptography/serialization/asn1/modules/EcPrivateKey.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Ldev/whyoleg/cryptography/serialization/asn1/modules/EcPrivateKey;", "<init>", "()V", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", ES6Iterator.VALUE_PROPERTY, "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "cryptography-serialization-asn1-modules"}, k = 1, mv = {2, 3, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly")
public final /* synthetic */ class EcPrivateKey$$serializer implements GeneratedSerializer<EcPrivateKey> {
    public static final EcPrivateKey$$serializer INSTANCE;
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
        EcPrivateKey$$serializer ecPrivateKey$$serializer = new EcPrivateKey$$serializer();
        INSTANCE = ecPrivateKey$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("dev.whyoleg.cryptography.serialization.asn1.modules.EcPrivateKey", ecPrivateKey$$serializer, 4);
        final byte b = 0;
        pluginGeneratedSerialDescriptor.addElement("version", false);
        pluginGeneratedSerialDescriptor.addElement("privateKey", false);
        final byte b2 = 1;
        pluginGeneratedSerialDescriptor.addElement("parameters", true);
        final ContextSpecificTag.TagType tagType = ContextSpecificTag.TagType.EXPLICIT;
        pluginGeneratedSerialDescriptor.pushAnnotation(new ContextSpecificTag(b, tagType) { // from class: dev.whyoleg.cryptography.serialization.asn1.modules.EcPrivateKey$$serializer$annotationImpl$dev_whyoleg_cryptography_serialization_asn1_ContextSpecificTag$0
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
                EcPrivateKey$$serializer$annotationImpl$dev_whyoleg_cryptography_serialization_asn1_ContextSpecificTag$0 ecPrivateKey$$serializer$annotationImpl$dev_whyoleg_cryptography_serialization_asn1_ContextSpecificTag$0 = this;
                return ecPrivateKey$$serializer$annotationImpl$dev_whyoleg_cryptography_serialization_asn1_ContextSpecificTag$0.classIndex() == contextSpecificTag.classIndex() && ecPrivateKey$$serializer$annotationImpl$dev_whyoleg_cryptography_serialization_asn1_ContextSpecificTag$0.type() == contextSpecificTag.type();
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
        pluginGeneratedSerialDescriptor.addElement("publicKey", true);
        final ContextSpecificTag.TagType tagType2 = ContextSpecificTag.TagType.EXPLICIT;
        pluginGeneratedSerialDescriptor.pushAnnotation(new ContextSpecificTag(b2, tagType2) { // from class: dev.whyoleg.cryptography.serialization.asn1.modules.EcPrivateKey$$serializer$annotationImpl$dev_whyoleg_cryptography_serialization_asn1_ContextSpecificTag$0
            private final /* synthetic */ byte classIndex;
            private final /* synthetic */ ContextSpecificTag.TagType type;

            {
                Intrinsics.checkNotNullParameter(tagType2, "type");
                this.type = tagType2;
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
                EcPrivateKey$$serializer$annotationImpl$dev_whyoleg_cryptography_serialization_asn1_ContextSpecificTag$0 ecPrivateKey$$serializer$annotationImpl$dev_whyoleg_cryptography_serialization_asn1_ContextSpecificTag$0 = this;
                return ecPrivateKey$$serializer$annotationImpl$dev_whyoleg_cryptography_serialization_asn1_ContextSpecificTag$0.classIndex() == contextSpecificTag.classIndex() && ecPrivateKey$$serializer$annotationImpl$dev_whyoleg_cryptography_serialization_asn1_ContextSpecificTag$0.type() == contextSpecificTag.type();
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

    private EcPrivateKey$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public final KSerializer<?>[] childSerializers() {
        return new KSerializer[]{IntSerializer.INSTANCE, ByteArraySerializer.INSTANCE, BuiltinSerializersKt.getNullable(EcParameters$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(BitArray$$serializer.INSTANCE)};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public final EcPrivateKey deserialize(Decoder decoder) {
        int iDecodeIntElement;
        BitArray bitArray;
        String str;
        byte[] bArr;
        int i;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            iDecodeIntElement = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 0);
            byte[] bArr2 = (byte[]) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 1, ByteArraySerializer.INSTANCE, null);
            EcParameters ecParameters = (EcParameters) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 2, EcParameters$$serializer.INSTANCE, null);
            String strM8784unboximpl = ecParameters != null ? ecParameters.m8784unboximpl() : null;
            bitArray = (BitArray) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 3, BitArray$$serializer.INSTANCE, null);
            str = strM8784unboximpl;
            bArr = bArr2;
            i = 15;
        } else {
            boolean z = true;
            iDecodeIntElement = 0;
            int i2 = 0;
            BitArray bitArray2 = null;
            String strM8784unboximpl2 = null;
            byte[] bArr3 = null;
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(serialDescriptor);
                if (iDecodeElementIndex == -1) {
                    z = false;
                } else if (iDecodeElementIndex == 0) {
                    iDecodeIntElement = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 0);
                    i2 |= 1;
                } else if (iDecodeElementIndex == 1) {
                    bArr3 = (byte[]) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 1, ByteArraySerializer.INSTANCE, bArr3);
                    i2 |= 2;
                } else if (iDecodeElementIndex == 2) {
                    EcParameters ecParameters2 = (EcParameters) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 2, EcParameters$$serializer.INSTANCE, strM8784unboximpl2 != null ? EcParameters.m8777boximpl(strM8784unboximpl2) : null);
                    strM8784unboximpl2 = ecParameters2 != null ? ecParameters2.m8784unboximpl() : null;
                    i2 |= 4;
                } else {
                    if (iDecodeElementIndex != 3) {
                        throw new UnknownFieldException(iDecodeElementIndex);
                    }
                    bitArray2 = (BitArray) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 3, BitArray$$serializer.INSTANCE, bitArray2);
                    i2 |= 8;
                }
            }
            bitArray = bitArray2;
            str = strM8784unboximpl2;
            bArr = bArr3;
            i = i2;
        }
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return new EcPrivateKey(i, iDecodeIntElement, bArr, str, bitArray, null, null);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(Encoder encoder, EcPrivateKey value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        EcPrivateKey.write$Self$cryptography_serialization_asn1_modules(value, compositeEncoderBeginStructure, serialDescriptor);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }
}
