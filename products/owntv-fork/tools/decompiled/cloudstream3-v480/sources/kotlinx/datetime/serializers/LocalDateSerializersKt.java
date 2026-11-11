package kotlinx.datetime.serializers;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.format.DateTimeFormat;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;

/* JADX INFO: compiled from: LocalDateSerializers.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a&\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000¨\u0006\u0006"}, d2 = {"asKSerializer", "Lkotlinx/serialization/KSerializer;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/datetime/format/DateTimeFormat;", "serialName", "", "kotlinx-datetime"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class LocalDateSerializersKt {
    public static final <T> KSerializer<T> asKSerializer(DateTimeFormat<T> dateTimeFormat, String serialName) {
        Intrinsics.checkNotNullParameter(dateTimeFormat, "<this>");
        Intrinsics.checkNotNullParameter(serialName, "serialName");
        return new KSerializer<T>(serialName, dateTimeFormat) { // from class: kotlinx.datetime.serializers.LocalDateSerializersKt.asKSerializer.1
            final /* synthetic */ String $serialName;
            final /* synthetic */ DateTimeFormat<T> $this_asKSerializer;
            private final SerialDescriptor descriptor;

            {
                this.$serialName = serialName;
                this.$this_asKSerializer = dateTimeFormat;
                this.descriptor = SerialDescriptorsKt.PrimitiveSerialDescriptor(serialName, PrimitiveKind.STRING.INSTANCE);
            }

            @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
            public SerialDescriptor getDescriptor() {
                return this.descriptor;
            }

            @Override // kotlinx.serialization.DeserializationStrategy
            public T deserialize(Decoder decoder) {
                Intrinsics.checkNotNullParameter(decoder, "decoder");
                return this.$this_asKSerializer.parse(decoder.decodeString());
            }

            @Override // kotlinx.serialization.SerializationStrategy
            public void serialize(Encoder encoder, T value) {
                Intrinsics.checkNotNullParameter(encoder, "encoder");
                encoder.encodeString(this.$this_asKSerializer.format(value));
            }

            /* JADX INFO: renamed from: toString, reason: from getter */
            public String get$serialName() {
                return this.$serialName;
            }
        };
    }
}
