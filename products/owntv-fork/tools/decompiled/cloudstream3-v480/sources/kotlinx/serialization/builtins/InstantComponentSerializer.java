package kotlinx.serialization.builtins;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Instant;
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
import kotlinx.serialization.internal.LongSerializer;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: InstantComponentSerializer.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\bB¢\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0096\u0080\u0004J\u001a\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0096\u0080\u0004R\u0015\u0010\u0005\u001a\u00020\u0006X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0011"}, d2 = {"Lkotlinx/serialization/builtins/InstantComponentSerializer;", "Lkotlinx/serialization/KSerializer;", "Lkotlin/time/Instant;", "<init>", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", ES6Iterator.VALUE_PROPERTY, "kotlinx-serialization-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class InstantComponentSerializer implements KSerializer<Instant> {
    public static final InstantComponentSerializer INSTANCE = new InstantComponentSerializer();
    private static final SerialDescriptor descriptor = SerialDescriptorsKt.buildClassSerialDescriptor("kotlinx.serialization.InstantComponentSerializer", new SerialDescriptor[0], new Function1() { // from class: kotlinx.serialization.builtins.InstantComponentSerializer$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return InstantComponentSerializer.descriptor$lambda$0((ClassSerialDescriptorBuilder) obj);
        }
    });

    private InstantComponentSerializer() {
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public Instant deserialize(Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(descriptor2);
        long jDecodeLongElement = 0;
        boolean z = true;
        int iDecodeIntElement = 0;
        while (true) {
            InstantComponentSerializer instantComponentSerializer = INSTANCE;
            int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(instantComponentSerializer.getDescriptor());
            if (iDecodeElementIndex == -1) {
                if (z) {
                    throw new MissingFieldException("epochSeconds", instantComponentSerializer.getDescriptor().getSerialName());
                }
                Instant instantFromEpochSeconds = Instant.INSTANCE.fromEpochSeconds(jDecodeLongElement, iDecodeIntElement);
                compositeDecoderBeginStructure.endStructure(descriptor2);
                return instantFromEpochSeconds;
            }
            if (iDecodeElementIndex == 0) {
                jDecodeLongElement = compositeDecoderBeginStructure.decodeLongElement(instantComponentSerializer.getDescriptor(), 0);
                z = false;
            } else if (iDecodeElementIndex == 1) {
                iDecodeIntElement = compositeDecoderBeginStructure.decodeIntElement(instantComponentSerializer.getDescriptor(), 1);
            } else {
                throw new SerializationException("Unexpected index: " + iDecodeElementIndex);
            }
        }
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, Instant value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(descriptor2);
        InstantComponentSerializer instantComponentSerializer = INSTANCE;
        compositeEncoderBeginStructure.encodeLongElement(instantComponentSerializer.getDescriptor(), 0, value.getEpochSeconds());
        if (value.getNanosecondsOfSecond() != 0 || compositeEncoderBeginStructure.shouldEncodeElementDefault(instantComponentSerializer.getDescriptor(), 1)) {
            compositeEncoderBeginStructure.encodeIntElement(instantComponentSerializer.getDescriptor(), 1, value.getNanosecondsOfSecond());
        }
        compositeEncoderBeginStructure.endStructure(descriptor2);
    }

    static final Unit descriptor$lambda$0(ClassSerialDescriptorBuilder buildClassSerialDescriptor) {
        Intrinsics.checkNotNullParameter(buildClassSerialDescriptor, "$this$buildClassSerialDescriptor");
        buildClassSerialDescriptor.element("epochSeconds", LongSerializer.INSTANCE.getDescriptor(), CollectionsKt.emptyList(), false);
        buildClassSerialDescriptor.element("nanosecondsOfSecond", LongSerializer.INSTANCE.getDescriptor(), CollectionsKt.emptyList(), true);
        return Unit.INSTANCE;
    }
}
