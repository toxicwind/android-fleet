package kotlinx.datetime.serializers;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.DatePeriod;
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
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u000bH\u0002J\u0010\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\t\u001a\u00020\u0002H\u0016R\u0014\u0010\f\u001a\u00020\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0016"}, d2 = {"Lkotlinx/datetime/serializers/DatePeriodComponentSerializer;", "Lkotlinx/serialization/KSerializer;", "Lkotlinx/datetime/DatePeriod;", "<init>", "()V", "unexpectedNonzero", "", "fieldName", "", ES6Iterator.VALUE_PROPERTY, "", "", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class DatePeriodComponentSerializer implements KSerializer<DatePeriod> {
    public static final DatePeriodComponentSerializer INSTANCE = new DatePeriodComponentSerializer();
    private static final SerialDescriptor descriptor = SerialDescriptorsKt.buildClassSerialDescriptor("kotlinx.datetime.DatePeriod/components", new SerialDescriptor[0], new Function1() { // from class: kotlinx.datetime.serializers.DatePeriodComponentSerializer$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return DatePeriodComponentSerializer.descriptor$lambda$0((ClassSerialDescriptorBuilder) obj);
        }
    });

    private DatePeriodComponentSerializer() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void unexpectedNonzero(String fieldName, long value) {
        if (value == 0) {
            return;
        }
        throw new SerializationException("DatePeriod should have non-date components be zero, but got " + value + " in '" + fieldName + '\'');
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void unexpectedNonzero(String fieldName, int value) {
        unexpectedNonzero(fieldName, value);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public DatePeriod deserialize(Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(descriptor2);
        int iDecodeIntElement = 0;
        int iDecodeIntElement2 = 0;
        int iDecodeIntElement3 = 0;
        while (true) {
            DatePeriodComponentSerializer datePeriodComponentSerializer = INSTANCE;
            int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(datePeriodComponentSerializer.getDescriptor());
            switch (iDecodeElementIndex) {
                case -1:
                    DatePeriod datePeriod = new DatePeriod(iDecodeIntElement, iDecodeIntElement2, iDecodeIntElement3);
                    compositeDecoderBeginStructure.endStructure(descriptor2);
                    return datePeriod;
                case 0:
                    iDecodeIntElement = compositeDecoderBeginStructure.decodeIntElement(datePeriodComponentSerializer.getDescriptor(), 0);
                    break;
                case 1:
                    iDecodeIntElement2 = compositeDecoderBeginStructure.decodeIntElement(datePeriodComponentSerializer.getDescriptor(), 1);
                    break;
                case 2:
                    iDecodeIntElement3 = compositeDecoderBeginStructure.decodeIntElement(datePeriodComponentSerializer.getDescriptor(), 2);
                    break;
                case 3:
                    datePeriodComponentSerializer.unexpectedNonzero("hours", compositeDecoderBeginStructure.decodeIntElement(datePeriodComponentSerializer.getDescriptor(), 3));
                    break;
                case 4:
                    datePeriodComponentSerializer.unexpectedNonzero("minutes", compositeDecoderBeginStructure.decodeIntElement(datePeriodComponentSerializer.getDescriptor(), 4));
                    break;
                case 5:
                    datePeriodComponentSerializer.unexpectedNonzero("seconds", compositeDecoderBeginStructure.decodeIntElement(datePeriodComponentSerializer.getDescriptor(), 5));
                    break;
                case 6:
                    datePeriodComponentSerializer.unexpectedNonzero("nanoseconds", compositeDecoderBeginStructure.decodeLongElement(datePeriodComponentSerializer.getDescriptor(), 6));
                    break;
                default:
                    throw new SerializationException("Unexpected index: " + iDecodeElementIndex);
            }
        }
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, DatePeriod value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(descriptor2);
        if (value.getYears() != 0) {
            compositeEncoderBeginStructure.encodeIntElement(DateTimePeriodComponentSerializer.INSTANCE.getDescriptor(), 0, value.getYears());
        }
        if (value.getMonths() != 0) {
            compositeEncoderBeginStructure.encodeIntElement(DateTimePeriodComponentSerializer.INSTANCE.getDescriptor(), 1, value.getMonths());
        }
        if (value.getDays() != 0) {
            compositeEncoderBeginStructure.encodeIntElement(DateTimePeriodComponentSerializer.INSTANCE.getDescriptor(), 2, value.getDays());
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
