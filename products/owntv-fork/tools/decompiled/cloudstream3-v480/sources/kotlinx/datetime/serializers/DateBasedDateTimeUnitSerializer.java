package kotlinx.datetime.serializers;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlinx.datetime.DateTimeUnit;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.InternalSerializationApi;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SealedClassSerializer;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.AbstractPolymorphicSerializer;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: DateTimeUnitSerializers.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\"\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0017J \u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0002H\u0017R!\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00020\u00168VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u001a8VX\u0096\u0004¢\u0006\f\u0012\u0004\b\u001b\u0010\u0004\u001a\u0004\b\u001c\u0010\u001d¨\u0006\u001e"}, d2 = {"Lkotlinx/datetime/serializers/DateBasedDateTimeUnitSerializer;", "Lkotlinx/serialization/internal/AbstractPolymorphicSerializer;", "Lkotlinx/datetime/DateTimeUnit$DateBased;", "<init>", "()V", "impl", "Lkotlinx/serialization/SealedClassSerializer;", "getImpl", "()Lkotlinx/serialization/SealedClassSerializer;", "impl$delegate", "Lkotlin/Lazy;", "findPolymorphicSerializerOrNull", "Lkotlinx/serialization/DeserializationStrategy;", "decoder", "Lkotlinx/serialization/encoding/CompositeDecoder;", "klassName", "", "Lkotlinx/serialization/SerializationStrategy;", "encoder", "Lkotlinx/serialization/encoding/Encoder;", ES6Iterator.VALUE_PROPERTY, "baseClass", "Lkotlin/reflect/KClass;", "getBaseClass", "()Lkotlin/reflect/KClass;", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor$annotations", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class DateBasedDateTimeUnitSerializer extends AbstractPolymorphicSerializer<DateTimeUnit.DateBased> {
    public static final DateBasedDateTimeUnitSerializer INSTANCE = new DateBasedDateTimeUnitSerializer();

    /* JADX INFO: renamed from: impl$delegate, reason: from kotlin metadata */
    private static final Lazy impl = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: kotlinx.datetime.serializers.DateBasedDateTimeUnitSerializer$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return DateBasedDateTimeUnitSerializer.impl_delegate$lambda$0();
        }
    });

    public static /* synthetic */ void getDescriptor$annotations() {
    }

    @Override // kotlinx.serialization.internal.AbstractPolymorphicSerializer
    public KClass<DateTimeUnit.DateBased> getBaseClass() {
        return Reflection.getOrCreateKotlinClass(DateTimeUnit.DateBased.class);
    }

    private DateBasedDateTimeUnitSerializer() {
    }

    private final SealedClassSerializer<DateTimeUnit.DateBased> getImpl() {
        return (SealedClassSerializer) impl.getValue();
    }

    static final SealedClassSerializer impl_delegate$lambda$0() {
        return new SealedClassSerializer("kotlinx.datetime.DateTimeUnit.DateBased", Reflection.getOrCreateKotlinClass(DateTimeUnit.DateBased.class), new KClass[]{Reflection.getOrCreateKotlinClass(DateTimeUnit.DayBased.class), Reflection.getOrCreateKotlinClass(DateTimeUnit.MonthBased.class)}, new KSerializer[]{DayBasedDateTimeUnitSerializer.INSTANCE, MonthBasedDateTimeUnitSerializer.INSTANCE});
    }

    @Override // kotlinx.serialization.internal.AbstractPolymorphicSerializer
    @InternalSerializationApi
    public DeserializationStrategy<DateTimeUnit.DateBased> findPolymorphicSerializerOrNull(CompositeDecoder decoder, String klassName) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        return getImpl().findPolymorphicSerializerOrNull(decoder, klassName);
    }

    @Override // kotlinx.serialization.internal.AbstractPolymorphicSerializer
    @InternalSerializationApi
    public SerializationStrategy<DateTimeUnit.DateBased> findPolymorphicSerializerOrNull(Encoder encoder, DateTimeUnit.DateBased value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        return getImpl().findPolymorphicSerializerOrNull(encoder, value);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return getImpl().getDescriptor();
    }
}
