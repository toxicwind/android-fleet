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
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: DSA.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0019\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006H\u0086\u0080\u0004¢\u0006\u0002\u0010\bJ\u0012\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0086\u0080\u0004J\u001a\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0086\u0080\u0004R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"dev/whyoleg/cryptography/serialization/asn1/modules/DsaParameters.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Ldev/whyoleg/cryptography/serialization/asn1/modules/DsaParameters;", "<init>", "()V", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", ES6Iterator.VALUE_PROPERTY, "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "cryptography-serialization-asn1-modules"}, k = 1, mv = {2, 3, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly")
public final /* synthetic */ class DsaParameters$$serializer implements GeneratedSerializer<DsaParameters> {
    public static final DsaParameters$$serializer INSTANCE;
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
        DsaParameters$$serializer dsaParameters$$serializer = new DsaParameters$$serializer();
        INSTANCE = dsaParameters$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("dev.whyoleg.cryptography.serialization.asn1.modules.DsaParameters", dsaParameters$$serializer, 3);
        pluginGeneratedSerialDescriptor.addElement("prime", false);
        pluginGeneratedSerialDescriptor.addElement("subprime", false);
        pluginGeneratedSerialDescriptor.addElement("generator", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private DsaParameters$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public final KSerializer<?>[] childSerializers() {
        return new KSerializer[]{BigIntAsStringSerializer.INSTANCE, BigIntAsStringSerializer.INSTANCE, BigIntAsStringSerializer.INSTANCE};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public final DsaParameters deserialize(Decoder decoder) {
        int i;
        BigInt bigInt;
        BigInt bigInt2;
        BigInt bigInt3;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        BigInt bigInt4 = null;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            BigInt bigInt5 = (BigInt) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 0, BigIntAsStringSerializer.INSTANCE, null);
            BigInt bigInt6 = (BigInt) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 1, BigIntAsStringSerializer.INSTANCE, null);
            bigInt = bigInt5;
            bigInt3 = (BigInt) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 2, BigIntAsStringSerializer.INSTANCE, null);
            bigInt2 = bigInt6;
            i = 7;
        } else {
            boolean z = true;
            int i2 = 0;
            BigInt bigInt7 = null;
            BigInt bigInt8 = null;
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(serialDescriptor);
                if (iDecodeElementIndex == -1) {
                    z = false;
                } else if (iDecodeElementIndex == 0) {
                    bigInt4 = (BigInt) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 0, BigIntAsStringSerializer.INSTANCE, bigInt4);
                    i2 |= 1;
                } else if (iDecodeElementIndex == 1) {
                    bigInt7 = (BigInt) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 1, BigIntAsStringSerializer.INSTANCE, bigInt7);
                    i2 |= 2;
                } else {
                    if (iDecodeElementIndex != 2) {
                        throw new UnknownFieldException(iDecodeElementIndex);
                    }
                    bigInt8 = (BigInt) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 2, BigIntAsStringSerializer.INSTANCE, bigInt8);
                    i2 |= 4;
                }
            }
            i = i2;
            bigInt = bigInt4;
            bigInt2 = bigInt7;
            bigInt3 = bigInt8;
        }
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return new DsaParameters(i, bigInt, bigInt2, bigInt3, null);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(Encoder encoder, DsaParameters value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        DsaParameters.write$Self$cryptography_serialization_asn1_modules(value, compositeEncoderBeginStructure, serialDescriptor);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }
}
