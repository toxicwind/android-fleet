package kotlinx.datetime.serializers;

import kotlin.KotlinNothingValueException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.DateTimeUnit;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.MissingFieldException;
import kotlinx.serialization.descriptors.ClassSerialDescriptorBuilder;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.LongSerializer;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: DateTimeUnitSerializers.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0002H\u0016J\u0010\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0012H\u0016R\u001b\u0010\u0005\u001a\u00020\u00068VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u0013"}, d2 = {"Lkotlinx/datetime/serializers/TimeBasedDateTimeUnitSerializer;", "Lkotlinx/serialization/KSerializer;", "Lkotlinx/datetime/DateTimeUnit$TimeBased;", "<init>", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "descriptor$delegate", "Lkotlin/Lazy;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", ES6Iterator.VALUE_PROPERTY, "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class TimeBasedDateTimeUnitSerializer implements KSerializer<DateTimeUnit.TimeBased> {
    public static final TimeBasedDateTimeUnitSerializer INSTANCE = new TimeBasedDateTimeUnitSerializer();

    /* JADX INFO: renamed from: descriptor$delegate, reason: from kotlin metadata */
    private static final Lazy descriptor = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: kotlinx.datetime.serializers.TimeBasedDateTimeUnitSerializer$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return SerialDescriptorsKt.buildClassSerialDescriptor("kotlinx.datetime.TimeBased", new SerialDescriptor[0], new Function1() { // from class: kotlinx.datetime.serializers.TimeBasedDateTimeUnitSerializer$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TimeBasedDateTimeUnitSerializer.descriptor_delegate$lambda$1$lambda$0((ClassSerialDescriptorBuilder) obj);
                }
            });
        }
    });

    private TimeBasedDateTimeUnitSerializer() {
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return (SerialDescriptor) descriptor.getValue();
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, DateTimeUnit.TimeBased value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(descriptor2);
        compositeEncoderBeginStructure.encodeLongElement(INSTANCE.getDescriptor(), 0, value.getNanoseconds());
        compositeEncoderBeginStructure.endStructure(descriptor2);
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public DateTimeUnit.TimeBased deserialize(Decoder decoder) {
        long jDecodeLongElement;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(descriptor2);
        boolean z = true;
        if (!compositeDecoderBeginStructure.decodeSequentially()) {
            long jDecodeLongElement2 = 0;
            boolean z2 = false;
            while (true) {
                TimeBasedDateTimeUnitSerializer timeBasedDateTimeUnitSerializer = INSTANCE;
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(timeBasedDateTimeUnitSerializer.getDescriptor());
                if (iDecodeElementIndex == -1) {
                    z = z2;
                    jDecodeLongElement = jDecodeLongElement2;
                    break;
                }
                if (iDecodeElementIndex == 0) {
                    jDecodeLongElement2 = compositeDecoderBeginStructure.decodeLongElement(timeBasedDateTimeUnitSerializer.getDescriptor(), 0);
                    z2 = true;
                } else {
                    DateTimeUnitSerializersKt.throwUnknownIndexException(iDecodeElementIndex);
                    throw new KotlinNothingValueException();
                }
            }
        } else {
            jDecodeLongElement = compositeDecoderBeginStructure.decodeLongElement(INSTANCE.getDescriptor(), 0);
        }
        Unit unit = Unit.INSTANCE;
        compositeDecoderBeginStructure.endStructure(descriptor2);
        if (!z) {
            throw new MissingFieldException("nanoseconds", getDescriptor().getSerialName());
        }
        return new DateTimeUnit.TimeBased(jDecodeLongElement);
    }

    static final Unit descriptor_delegate$lambda$1$lambda$0(ClassSerialDescriptorBuilder buildClassSerialDescriptor) {
        Intrinsics.checkNotNullParameter(buildClassSerialDescriptor, "$this$buildClassSerialDescriptor");
        buildClassSerialDescriptor.element("nanoseconds", LongSerializer.INSTANCE.getDescriptor(), CollectionsKt.emptyList(), false);
        return Unit.INSTANCE;
    }
}
