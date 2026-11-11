package com.lagradost.cloudstream3.utils.videoskip;

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
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.StringSerializer;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: IntroDbSkip.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0019\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006H\u0086\u0080\u0004¢\u0006\u0002\u0010\bJ\u0012\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0086\u0080\u0004J\u001a\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0086\u0080\u0004R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"com/lagradost/cloudstream3/utils/videoskip/IntroDbSkip.IntroDbResponse.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/lagradost/cloudstream3/utils/videoskip/IntroDbSkip$IntroDbResponse;", "<init>", "()V", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", ES6Iterator.VALUE_PROPERTY, "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly")
public final /* synthetic */ class IntroDbSkip$IntroDbResponse$$serializer implements GeneratedSerializer<IntroDbSkip.IntroDbResponse> {
    public static final IntroDbSkip$IntroDbResponse$$serializer INSTANCE;
    private static final SerialDescriptor descriptor;

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        IntroDbSkip$IntroDbResponse$$serializer introDbSkip$IntroDbResponse$$serializer = new IntroDbSkip$IntroDbResponse$$serializer();
        INSTANCE = introDbSkip$IntroDbResponse$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.lagradost.cloudstream3.utils.videoskip.IntroDbSkip.IntroDbResponse", introDbSkip$IntroDbResponse$$serializer, 6);
        pluginGeneratedSerialDescriptor.addElement("imdb_id", false);
        pluginGeneratedSerialDescriptor.addElement("season", false);
        pluginGeneratedSerialDescriptor.addElement("episode", false);
        pluginGeneratedSerialDescriptor.addElement("intro", false);
        pluginGeneratedSerialDescriptor.addElement("recap", false);
        pluginGeneratedSerialDescriptor.addElement("outro", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private IntroDbSkip$IntroDbResponse$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public final KSerializer<?>[] childSerializers() {
        return new KSerializer[]{BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE), BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE), BuiltinSerializersKt.getNullable(IntroDbSkip$Segment$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(IntroDbSkip$Segment$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(IntroDbSkip$Segment$$serializer.INSTANCE)};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public final IntroDbSkip.IntroDbResponse deserialize(Decoder decoder) {
        IntroDbSkip.Segment segment;
        IntroDbSkip.Segment segment2;
        Integer num;
        IntroDbSkip.Segment segment3;
        String str;
        Integer num2;
        int i;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        int i2 = 5;
        String str2 = null;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            String str3 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 0, StringSerializer.INSTANCE, null);
            Integer num3 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 1, IntSerializer.INSTANCE, null);
            Integer num4 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 2, IntSerializer.INSTANCE, null);
            IntroDbSkip.Segment segment4 = (IntroDbSkip.Segment) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 3, IntroDbSkip$Segment$$serializer.INSTANCE, null);
            IntroDbSkip.Segment segment5 = (IntroDbSkip.Segment) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 4, IntroDbSkip$Segment$$serializer.INSTANCE, null);
            str = str3;
            segment2 = (IntroDbSkip.Segment) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 5, IntroDbSkip$Segment$$serializer.INSTANCE, null);
            segment3 = segment4;
            segment = segment5;
            num = num4;
            num2 = num3;
            i = 63;
        } else {
            boolean z = true;
            int i3 = 0;
            Integer num5 = null;
            Integer num6 = null;
            IntroDbSkip.Segment segment6 = null;
            IntroDbSkip.Segment segment7 = null;
            IntroDbSkip.Segment segment8 = null;
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(serialDescriptor);
                switch (iDecodeElementIndex) {
                    case -1:
                        z = false;
                        i2 = 5;
                        break;
                    case 0:
                        str2 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 0, StringSerializer.INSTANCE, str2);
                        i3 |= 1;
                        i2 = 5;
                        break;
                    case 1:
                        num5 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 1, IntSerializer.INSTANCE, num5);
                        i3 |= 2;
                        break;
                    case 2:
                        num6 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 2, IntSerializer.INSTANCE, num6);
                        i3 |= 4;
                        break;
                    case 3:
                        segment6 = (IntroDbSkip.Segment) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 3, IntroDbSkip$Segment$$serializer.INSTANCE, segment6);
                        i3 |= 8;
                        break;
                    case 4:
                        segment7 = (IntroDbSkip.Segment) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 4, IntroDbSkip$Segment$$serializer.INSTANCE, segment7);
                        i3 |= 16;
                        break;
                    case 5:
                        segment8 = (IntroDbSkip.Segment) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, i2, IntroDbSkip$Segment$$serializer.INSTANCE, segment8);
                        i3 |= 32;
                        break;
                    default:
                        throw new UnknownFieldException(iDecodeElementIndex);
                }
            }
            segment = segment7;
            segment2 = segment8;
            num = num6;
            segment3 = segment6;
            str = str2;
            num2 = num5;
            i = i3;
        }
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return new IntroDbSkip.IntroDbResponse(i, str, num2, num, segment3, segment, segment2, null);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(Encoder encoder, IntroDbSkip.IntroDbResponse value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        IntroDbSkip.IntroDbResponse.write$Self$app(value, compositeEncoderBeginStructure, serialDescriptor);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public /* bridge */ KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.CC.$default$typeParametersSerializers(this);
    }
}
