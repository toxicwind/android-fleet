package app.cash.zipline.internal.bridge;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: calls.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001B\u0019\u0012\u0010\u0010\u0003\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00010\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u0002H\u0096\u0080\u0004J\u0016\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0096\u0080\u0004R\u001e\u0010\u0003\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00010\u0002X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0015\u0010\b\u001a\u00020\tX\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lapp/cash/zipline/internal/bridge/ArgsListSerializer;", "Lkotlinx/serialization/KSerializer;", "", "serializers", "<init>", "(Ljava/util/List;)V", "getSerializers$zipline_release", "()Ljava/util/List;", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", ES6Iterator.VALUE_PROPERTY, "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ArgsListSerializer implements KSerializer<List<?>> {
    private final SerialDescriptor descriptor;
    private final List<KSerializer<?>> serializers;

    /* JADX WARN: Multi-variable type inference failed */
    public ArgsListSerializer(List<? extends KSerializer<?>> serializers) {
        Intrinsics.checkNotNullParameter(serializers, "serializers");
        this.serializers = serializers;
        this.descriptor = CallsKt.argsListDescriptor;
    }

    public final List<KSerializer<?>> getSerializers$zipline_release() {
        return this.serializers;
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, List<?> value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        if (value.size() != this.serializers.size()) {
            throw new IllegalStateException("Check failed.");
        }
        SerialDescriptor descriptor = getDescriptor();
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(descriptor);
        int size = getSerializers$zipline_release().size();
        for (int i = 0; i < size; i++) {
            SerialDescriptor descriptor2 = getDescriptor();
            KSerializer<?> kSerializer = getSerializers$zipline_release().get(i);
            Intrinsics.checkNotNull(kSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<kotlin.Any?>");
            compositeEncoderBeginStructure.encodeSerializableElement(descriptor2, i, kSerializer, value.get(i));
        }
        compositeEncoderBeginStructure.endStructure(descriptor);
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public List<?> deserialize(Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor = getDescriptor();
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(descriptor);
        ArrayList arrayList = new ArrayList();
        int size = getSerializers$zipline_release().size();
        for (int i = 0; i < size; i++) {
            if (compositeDecoderBeginStructure.decodeElementIndex(getDescriptor()) != i) {
                throw new IllegalStateException("Check failed.");
            }
            arrayList.add(CompositeDecoder.CC.decodeSerializableElement$default(compositeDecoderBeginStructure, getDescriptor(), i, getSerializers$zipline_release().get(i), null, 8, null));
        }
        if (compositeDecoderBeginStructure.decodeElementIndex(getDescriptor()) != -1) {
            throw new IllegalStateException("Check failed.");
        }
        compositeDecoderBeginStructure.endStructure(descriptor);
        return arrayList;
    }
}
