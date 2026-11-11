package kotlinx.datetime.serializers;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.DateTimePeriod;
import kotlinx.datetime.DateTimePeriodKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.ClassSerialDescriptorBuilder;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.LongSerializer;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: DateTimePeriodSerializers.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0016R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0011"}, d2 = {"Lkotlinx/datetime/serializers/DateTimePeriodComponentSerializer;", "Lkotlinx/serialization/KSerializer;", "Lkotlinx/datetime/DateTimePeriod;", "<init>", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", ES6Iterator.VALUE_PROPERTY, "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class DateTimePeriodComponentSerializer implements KSerializer<DateTimePeriod> {
    public static final DateTimePeriodComponentSerializer INSTANCE = new DateTimePeriodComponentSerializer();
    private static final SerialDescriptor descriptor = SerialDescriptorsKt.buildClassSerialDescriptor("kotlinx.datetime.DateTimePeriod/components", new SerialDescriptor[0], new Function1() { // from class: kotlinx.datetime.serializers.DateTimePeriodComponentSerializer$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return DateTimePeriodComponentSerializer.descriptor$lambda$0((ClassSerialDescriptorBuilder) obj);
        }
    });

    private DateTimePeriodComponentSerializer() {
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public DateTimePeriod deserialize(Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(descriptor2);
        int iDecodeIntElement = 0;
        int iDecodeIntElement2 = 0;
        int iDecodeIntElement3 = 0;
        int iDecodeIntElement4 = 0;
        int iDecodeIntElement5 = 0;
        int iDecodeIntElement6 = 0;
        long jDecodeLongElement = 0;
        while (true) {
            DateTimePeriodComponentSerializer dateTimePeriodComponentSerializer = INSTANCE;
            int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(dateTimePeriodComponentSerializer.getDescriptor());
            switch (iDecodeElementIndex) {
                case -1:
                    DateTimePeriod DateTimePeriod = DateTimePeriodKt.DateTimePeriod(iDecodeIntElement, iDecodeIntElement2, iDecodeIntElement3, iDecodeIntElement4, iDecodeIntElement5, iDecodeIntElement6, jDecodeLongElement);
                    compositeDecoderBeginStructure.endStructure(descriptor2);
                    return DateTimePeriod;
                case 0:
                    iDecodeIntElement = compositeDecoderBeginStructure.decodeIntElement(dateTimePeriodComponentSerializer.getDescriptor(), 0);
                    break;
                case 1:
                    iDecodeIntElement2 = compositeDecoderBeginStructure.decodeIntElement(dateTimePeriodComponentSerializer.getDescriptor(), 1);
                    break;
                case 2:
                    iDecodeIntElement3 = compositeDecoderBeginStructure.decodeIntElement(dateTimePeriodComponentSerializer.getDescriptor(), 2);
                    break;
                case 3:
                    iDecodeIntElement4 = compositeDecoderBeginStructure.decodeIntElement(dateTimePeriodComponentSerializer.getDescriptor(), 3);
                    break;
                case 4:
                    iDecodeIntElement5 = compositeDecoderBeginStructure.decodeIntElement(dateTimePeriodComponentSerializer.getDescriptor(), 4);
                    break;
                case 5:
                    iDecodeIntElement6 = compositeDecoderBeginStructure.decodeIntElement(dateTimePeriodComponentSerializer.getDescriptor(), 5);
                    break;
                case 6:
                    jDecodeLongElement = compositeDecoderBeginStructure.decodeLongElement(dateTimePeriodComponentSerializer.getDescriptor(), 6);
                    break;
                default:
                    throw new SerializationException("Unexpected index: " + iDecodeElementIndex);
            }
        }
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, DateTimePeriod value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(descriptor2);
        if (value.getYears() != 0) {
            compositeEncoderBeginStructure.encodeIntElement(INSTANCE.getDescriptor(), 0, value.getYears());
        }
        if (value.getMonths() != 0) {
            compositeEncoderBeginStructure.encodeIntElement(INSTANCE.getDescriptor(), 1, value.getMonths());
        }
        if (value.getDays() != 0) {
            compositeEncoderBeginStructure.encodeIntElement(INSTANCE.getDescriptor(), 2, value.getDays());
        }
        if (value.getHours() != 0) {
            compositeEncoderBeginStructure.encodeIntElement(INSTANCE.getDescriptor(), 3, value.getHours());
        }
        if (value.getMinutes() != 0) {
            compositeEncoderBeginStructure.encodeIntElement(INSTANCE.getDescriptor(), 4, value.getMinutes());
        }
        if (value.getSeconds() != 0) {
            compositeEncoderBeginStructure.encodeIntElement(INSTANCE.getDescriptor(), 5, value.getSeconds());
        }
        if (value.getNanoseconds() != 0) {
            compositeEncoderBeginStructure.encodeLongElement(INSTANCE.getDescriptor(), 6, value.getNanoseconds());
        }
        compositeEncoderBeginStructure.endStructure(descriptor2);
    }

    static final Unit descriptor$lambda$0(ClassSerialDescriptorBuilder buildClassSerialDescriptor) {
        Intrinsics.checkNotNullParameter(buildClassSerialDescriptor, "$this$buildClassSerialDescriptor");
        buildClassSerialDescriptor.element("years", IntSerializer.INSTANCE.getDescriptor(), CollectionsKt.emptyList(), true);
        buildClassSerialDescriptor.element("months", IntSerializer.INSTANCE.getDescriptor(), CollectionsKt.emptyList(), true);
        buildClassSerialDescriptor.element("days", IntSerializer.INSTANCE.getDescriptor(), CollectionsKt.emptyList(), true);
        buildClassSerialDescriptor.element("hours", IntSerializer.INSTANCE.getDescriptor(), CollectionsKt.emptyList(), true);
        buildClassSerialDescriptor.element("minutes", IntSerializer.INSTANCE.getDescriptor(), CollectionsKt.emptyList(), true);
        buildClassSerialDescriptor.element("seconds", IntSerializer.INSTANCE.getDescriptor(), CollectionsKt.emptyList(), true);
        buildClassSerialDescriptor.element("nanoseconds", LongSerializer.INSTANCE.getDescriptor(), CollectionsKt.emptyList(), true);
        return Unit.INSTANCE;
    }
}
