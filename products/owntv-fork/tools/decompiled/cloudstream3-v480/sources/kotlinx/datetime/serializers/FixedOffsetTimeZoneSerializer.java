package kotlinx.datetime.serializers;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.FixedOffsetTimeZone;
import kotlinx.datetime.TimeZone;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: TimeZoneSerializers.kt */
/* JADX INFO: loaded from: classes5.dex */
@Deprecated(level = DeprecationLevel.WARNING, message = "Serializing FixedOffsetTimeZoneSerializer is discouraged. Please serialize the string id instead.")
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0016R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0011"}, d2 = {"Lkotlinx/datetime/serializers/FixedOffsetTimeZoneSerializer;", "Lkotlinx/serialization/KSerializer;", "Lkotlinx/datetime/FixedOffsetTimeZone;", "<init>", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", ES6Iterator.VALUE_PROPERTY, "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class FixedOffsetTimeZoneSerializer implements KSerializer<FixedOffsetTimeZone> {
    public static final FixedOffsetTimeZoneSerializer INSTANCE = new FixedOffsetTimeZoneSerializer();
    private static final SerialDescriptor descriptor = SerialDescriptorsKt.PrimitiveSerialDescriptor("kotlinx.datetime.FixedOffsetTimeZone", PrimitiveKind.STRING.INSTANCE);

    private FixedOffsetTimeZoneSerializer() {
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public FixedOffsetTimeZone deserialize(Decoder decoder) throws Exception {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        TimeZone timeZoneOf = TimeZone.INSTANCE.of(decoder.decodeString());
        if (timeZoneOf instanceof FixedOffsetTimeZone) {
            return (FixedOffsetTimeZone) timeZoneOf;
        }
        throw new SerializationException("Timezone identifier '" + timeZoneOf + "' does not correspond to a fixed-offset timezone");
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, FixedOffsetTimeZone value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        encoder.encodeString(value.getId());
    }
}
