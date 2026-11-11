package kotlinx.datetime.serializers;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.LocalTime;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.MissingFieldException;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.ClassSerialDescriptorBuilder;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.ShortSerializer;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: LocalTimeSerializers.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0016R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0011"}, d2 = {"Lkotlinx/datetime/serializers/LocalTimeComponentSerializer;", "Lkotlinx/serialization/KSerializer;", "Lkotlinx/datetime/LocalTime;", "<init>", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", ES6Iterator.VALUE_PROPERTY, "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class LocalTimeComponentSerializer implements KSerializer<LocalTime> {
    public static final LocalTimeComponentSerializer INSTANCE = new LocalTimeComponentSerializer();
    private static final SerialDescriptor descriptor = SerialDescriptorsKt.buildClassSerialDescriptor("kotlinx.datetime.LocalTime/components", new SerialDescriptor[0], new Function1() { // from class: kotlinx.datetime.serializers.LocalTimeComponentSerializer$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return LocalTimeComponentSerializer.descriptor$lambda$0((ClassSerialDescriptorBuilder) obj);
        }
    });

    private LocalTimeComponentSerializer() {
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public LocalTime deserialize(Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(descriptor2);
        Short shValueOf = null;
        Short shValueOf2 = null;
        short sDecodeShortElement = 0;
        int iDecodeIntElement = 0;
        while (true) {
            LocalTimeComponentSerializer localTimeComponentSerializer = INSTANCE;
            int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(localTimeComponentSerializer.getDescriptor());
            if (iDecodeElementIndex == -1) {
                if (shValueOf == null) {
                    throw new MissingFieldException("hour", localTimeComponentSerializer.getDescriptor().getSerialName());
                }
                if (shValueOf2 == null) {
                    throw new MissingFieldException("minute", localTimeComponentSerializer.getDescriptor().getSerialName());
                }
                LocalTime localTime = new LocalTime(shValueOf.shortValue(), shValueOf2.shortValue(), sDecodeShortElement, iDecodeIntElement);
                compositeDecoderBeginStructure.endStructure(descriptor2);
                return localTime;
            }
            if (iDecodeElementIndex == 0) {
                shValueOf = Short.valueOf(compositeDecoderBeginStructure.decodeShortElement(localTimeComponentSerializer.getDescriptor(), 0));
            } else if (iDecodeElementIndex == 1) {
                shValueOf2 = Short.valueOf(compositeDecoderBeginStructure.decodeShortElement(localTimeComponentSerializer.getDescriptor(), 1));
            } else if (iDecodeElementIndex == 2) {
                sDecodeShortElement = compositeDecoderBeginStructure.decodeShortElement(localTimeComponentSerializer.getDescriptor(), 2);
            } else if (iDecodeElementIndex == 3) {
                iDecodeIntElement = compositeDecoderBeginStructure.decodeIntElement(localTimeComponentSerializer.getDescriptor(), 3);
            } else {
                throw new SerializationException("Unexpected index: " + iDecodeElementIndex);
            }
        }
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, LocalTime value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(descriptor2);
        LocalTimeComponentSerializer localTimeComponentSerializer = INSTANCE;
        compositeEncoderBeginStructure.encodeShortElement(localTimeComponentSerializer.getDescriptor(), 0, (short) value.getHour());
        compositeEncoderBeginStructure.encodeShortElement(localTimeComponentSerializer.getDescriptor(), 1, (short) value.getMinute());
        if (value.getSecond() != 0 || value.getNanosecond() != 0) {
            compositeEncoderBeginStructure.encodeShortElement(localTimeComponentSerializer.getDescriptor(), 2, (short) value.getSecond());
            if (value.getNanosecond() != 0) {
                compositeEncoderBeginStructure.encodeIntElement(localTimeComponentSerializer.getDescriptor(), 3, value.getNanosecond());
            }
        }
        compositeEncoderBeginStructure.endStructure(descriptor2);
    }

    static final Unit descriptor$lambda$0(ClassSerialDescriptorBuilder buildClassSerialDescriptor) {
        Intrinsics.checkNotNullParameter(buildClassSerialDescriptor, "$this$buildClassSerialDescriptor");
        buildClassSerialDescriptor.element("hour", ShortSerializer.INSTANCE.getDescriptor(), CollectionsKt.emptyList(), false);
        buildClassSerialDescriptor.element("minute", ShortSerializer.INSTANCE.getDescriptor(), CollectionsKt.emptyList(), false);
        buildClassSerialDescriptor.element("second", ShortSerializer.INSTANCE.getDescriptor(), CollectionsKt.emptyList(), true);
        buildClassSerialDescriptor.element("nanosecond", IntSerializer.INSTANCE.getDescriptor(), CollectionsKt.emptyList(), true);
        return Unit.INSTANCE;
    }
}
