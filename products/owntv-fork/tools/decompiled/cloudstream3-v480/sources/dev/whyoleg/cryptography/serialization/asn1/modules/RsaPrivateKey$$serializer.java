package dev.whyoleg.cryptography.serialization.asn1.modules;

import dev.whyoleg.cryptography.bigint.BigInt;
import dev.whyoleg.cryptography.bigint.BigIntAsStringSerializer;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: RSA.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0019\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006H\u0086\u0080\u0004¢\u0006\u0002\u0010\bJ\u0012\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0086\u0080\u0004J\u001a\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0086\u0080\u0004R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"dev/whyoleg/cryptography/serialization/asn1/modules/RsaPrivateKey.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Ldev/whyoleg/cryptography/serialization/asn1/modules/RsaPrivateKey;", "<init>", "()V", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", ES6Iterator.VALUE_PROPERTY, "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "cryptography-serialization-asn1-modules"}, k = 1, mv = {2, 3, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly")
public final /* synthetic */ class RsaPrivateKey$$serializer implements GeneratedSerializer<RsaPrivateKey> {
    public static final RsaPrivateKey$$serializer INSTANCE;
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
        RsaPrivateKey$$serializer rsaPrivateKey$$serializer = new RsaPrivateKey$$serializer();
        INSTANCE = rsaPrivateKey$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("dev.whyoleg.cryptography.serialization.asn1.modules.RsaPrivateKey", rsaPrivateKey$$serializer, 9);
        pluginGeneratedSerialDescriptor.addElement("version", false);
        pluginGeneratedSerialDescriptor.addElement("modulus", false);
        pluginGeneratedSerialDescriptor.addElement("publicExponent", false);
        pluginGeneratedSerialDescriptor.addElement("privateExponent", false);
        pluginGeneratedSerialDescriptor.addElement("prime1", false);
        pluginGeneratedSerialDescriptor.addElement("prime2", false);
        pluginGeneratedSerialDescriptor.addElement("exponent1", false);
        pluginGeneratedSerialDescriptor.addElement("exponent2", false);
        pluginGeneratedSerialDescriptor.addElement("coefficient", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private RsaPrivateKey$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public final KSerializer<?>[] childSerializers() {
        return new KSerializer[]{IntSerializer.INSTANCE, BigIntAsStringSerializer.INSTANCE, BigIntAsStringSerializer.INSTANCE, BigIntAsStringSerializer.INSTANCE, BigIntAsStringSerializer.INSTANCE, BigIntAsStringSerializer.INSTANCE, BigIntAsStringSerializer.INSTANCE, BigIntAsStringSerializer.INSTANCE, BigIntAsStringSerializer.INSTANCE};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public final RsaPrivateKey deserialize(Decoder decoder) {
        int iDecodeIntElement;
        BigInt bigInt;
        BigInt bigInt2;
        BigInt bigInt3;
        BigInt bigInt4;
        BigInt bigInt5;
        int i;
        BigInt bigInt6;
        BigInt bigInt7;
        BigInt bigInt8;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        int i2 = 7;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            iDecodeIntElement = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 0);
            BigInt bigInt9 = (BigInt) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 1, BigIntAsStringSerializer.INSTANCE, null);
            BigInt bigInt10 = (BigInt) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 2, BigIntAsStringSerializer.INSTANCE, null);
            BigInt bigInt11 = (BigInt) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 3, BigIntAsStringSerializer.INSTANCE, null);
            BigInt bigInt12 = (BigInt) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 4, BigIntAsStringSerializer.INSTANCE, null);
            BigInt bigInt13 = (BigInt) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 5, BigIntAsStringSerializer.INSTANCE, null);
            BigInt bigInt14 = (BigInt) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 6, BigIntAsStringSerializer.INSTANCE, null);
            bigInt4 = bigInt10;
            bigInt2 = bigInt9;
            bigInt8 = (BigInt) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 7, BigIntAsStringSerializer.INSTANCE, null);
            bigInt = bigInt14;
            bigInt3 = bigInt13;
            bigInt5 = (BigInt) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 8, BigIntAsStringSerializer.INSTANCE, null);
            bigInt6 = bigInt12;
            i = 511;
            bigInt7 = bigInt11;
        } else {
            boolean z = true;
            iDecodeIntElement = 0;
            BigInt bigInt15 = null;
            BigInt bigInt16 = null;
            BigInt bigInt17 = null;
            BigInt bigInt18 = null;
            BigInt bigInt19 = null;
            BigInt bigInt20 = null;
            BigInt bigInt21 = null;
            int i3 = 0;
            BigInt bigInt22 = null;
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(serialDescriptor);
                switch (iDecodeElementIndex) {
                    case -1:
                        z = false;
                        break;
                    case 0:
                        iDecodeIntElement = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 0);
                        i3 |= 1;
                        i2 = 7;
                        break;
                    case 1:
                        bigInt19 = (BigInt) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 1, BigIntAsStringSerializer.INSTANCE, bigInt19);
                        i3 |= 2;
                        i2 = 7;
                        break;
                    case 2:
                        bigInt20 = (BigInt) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 2, BigIntAsStringSerializer.INSTANCE, bigInt20);
                        i3 |= 4;
                        i2 = 7;
                        break;
                    case 3:
                        bigInt21 = (BigInt) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 3, BigIntAsStringSerializer.INSTANCE, bigInt21);
                        i3 |= 8;
                        i2 = 7;
                        break;
                    case 4:
                        bigInt22 = (BigInt) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 4, BigIntAsStringSerializer.INSTANCE, bigInt22);
                        i3 |= 16;
                        i2 = 7;
                        break;
                    case 5:
                        bigInt18 = (BigInt) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 5, BigIntAsStringSerializer.INSTANCE, bigInt18);
                        i3 |= 32;
                        i2 = 7;
                        break;
                    case 6:
                        bigInt17 = (BigInt) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 6, BigIntAsStringSerializer.INSTANCE, bigInt17);
                        i3 |= 64;
                        break;
                    case 7:
                        bigInt15 = (BigInt) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, i2, BigIntAsStringSerializer.INSTANCE, bigInt15);
                        i3 |= 128;
                        break;
                    case 8:
                        bigInt16 = (BigInt) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 8, BigIntAsStringSerializer.INSTANCE, bigInt16);
                        i3 |= 256;
                        break;
                    default:
                        throw new UnknownFieldException(iDecodeElementIndex);
                }
            }
            BigInt bigInt23 = bigInt20;
            bigInt = bigInt17;
            bigInt2 = bigInt19;
            bigInt3 = bigInt18;
            bigInt4 = bigInt23;
            bigInt5 = bigInt16;
            i = i3;
            bigInt6 = bigInt22;
            bigInt7 = bigInt21;
            bigInt8 = bigInt15;
        }
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return new RsaPrivateKey(i, iDecodeIntElement, bigInt2, bigInt4, bigInt7, bigInt6, bigInt3, bigInt, bigInt8, bigInt5, null);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(Encoder encoder, RsaPrivateKey value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        RsaPrivateKey.write$Self$cryptography_serialization_asn1_modules(value, compositeEncoderBeginStructure, serialDescriptor);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }
}
