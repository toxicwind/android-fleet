package kotlinx.datetime.serializers;

import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.LocalDate;
import kotlinx.datetime.MonthKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.MissingFieldException;
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

/* JADX INFO: compiled from: LocalDateSerializers.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0016R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0011"}, d2 = {"Lkotlinx/datetime/serializers/LocalDateComponentSerializer;", "Lkotlinx/serialization/KSerializer;", "Lkotlinx/datetime/LocalDate;", "<init>", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", ES6Iterator.VALUE_PROPERTY, "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class LocalDateComponentSerializer implements KSerializer<LocalDate> {
    public static final LocalDateComponentSerializer INSTANCE = new LocalDateComponentSerializer();
    private static final SerialDescriptor descriptor = SerialDescriptorsKt.buildClassSerialDescriptor("kotlinx.datetime.LocalDate/components", new SerialDescriptor[0], new Function1() { // from class: kotlinx.datetime.serializers.LocalDateComponentSerializer$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return LocalDateComponentSerializer.descriptor$lambda$0((ClassSerialDescriptorBuilder) obj);
        }
    });

    private LocalDateComponentSerializer() {
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public LocalDate deserialize(Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(descriptor2);
        Integer numValueOf = null;
        Short shValueOf = null;
        Short shValueOf2 = null;
        while (true) {
            LocalDateComponentSerializer localDateComponentSerializer = INSTANCE;
            int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(localDateComponentSerializer.getDescriptor());
            if (iDecodeElementIndex == -1) {
                if (numValueOf == null) {
                    throw new MissingFieldException("year", localDateComponentSerializer.getDescriptor().getSerialName());
                }
                if (shValueOf == null) {
                    throw new MissingFieldException("month", localDateComponentSerializer.getDescriptor().getSerialName());
                }
                if (shValueOf2 == null) {
                    throw new MissingFieldException("day", localDateComponentSerializer.getDescriptor().getSerialName());
                }
                LocalDate localDate = new LocalDate(numValueOf.intValue(), shValueOf.shortValue(), shValueOf2.shortValue());
                compositeDecoderBeginStructure.endStructure(descriptor2);
                return localDate;
            }
            if (iDecodeElementIndex == 0) {
                numValueOf = Integer.valueOf(compositeDecoderBeginStructure.decodeIntElement(localDateComponentSerializer.getDescriptor(), 0));
            } else if (iDecodeElementIndex == 1) {
                shValueOf = Short.valueOf(compositeDecoderBeginStructure.decodeShortElement(localDateComponentSerializer.getDescriptor(), 1));
            } else if (iDecodeElementIndex == 2) {
                shValueOf2 = Short.valueOf(compositeDecoderBeginStructure.decodeShortElement(localDateComponentSerializer.getDescriptor(), 2));
            } else {
                DateTimeUnitSerializersKt.throwUnknownIndexException(iDecodeElementIndex);
                throw new KotlinNothingValueException();
            }
        }
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, LocalDate value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(descriptor2);
        LocalDateComponentSerializer localDateComponentSerializer = INSTANCE;
        compositeEncoderBeginStructure.encodeIntElement(localDateComponentSerializer.getDescriptor(), 0, value.getYear());
        compositeEncoderBeginStructure.encodeShortElement(localDateComponentSerializer.getDescriptor(), 1, (short) MonthKt.getNumber(value.m10564getMonth()));
        compositeEncoderBeginStructure.encodeShortElement(localDateComponentSerializer.getDescriptor(), 2, (short) value.getDay());
        compositeEncoderBeginStructure.endStructure(descriptor2);
    }

    static final Unit descriptor$lambda$0(ClassSerialDescriptorBuilder buildClassSerialDescriptor) {
        Intrinsics.checkNotNullParameter(buildClassSerialDescriptor, "$this$buildClassSerialDescriptor");
        buildClassSerialDescriptor.element("year", IntSerializer.INSTANCE.getDescriptor(), CollectionsKt.emptyList(), false);
        buildClassSerialDescriptor.element("month", ShortSerializer.INSTANCE.getDescriptor(), CollectionsKt.emptyList(), false);
        buildClassSerialDescriptor.element("day", ShortSerializer.INSTANCE.getDescriptor(), CollectionsKt.emptyList(), false);
        return Unit.INSTANCE;
    }
}
