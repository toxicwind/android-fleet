package app.cash.zipline.internal.bridge;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: LenientUnitSerializer.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÁ\u0002\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u001c\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0002H\u0096\u0080\u0004J\u0014\u0010\u000f\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0010\u001a\u00020\u0011H\u0096\u0080\u0004R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0015\u0010\u0007\u001a\u00020\bX\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0012"}, d2 = {"Lapp/cash/zipline/internal/bridge/LenientUnitSerializer;", "Lkotlinx/serialization/KSerializer;", "", "<init>", "()V", "delegateSerializer", "", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "serialize", "encoder", "Lkotlinx/serialization/encoding/Encoder;", ES6Iterator.VALUE_PROPERTY, "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class LenientUnitSerializer implements KSerializer<Object> {
    public static final LenientUnitSerializer INSTANCE = new LenientUnitSerializer();
    private static final KSerializer<Unit> delegateSerializer;
    private static final SerialDescriptor descriptor;

    private LenientUnitSerializer() {
    }

    static {
        KSerializer<Unit> kSerializerSerializer = BuiltinSerializersKt.serializer(Unit.INSTANCE);
        delegateSerializer = kSerializerSerializer;
        descriptor = SerialDescriptorsKt.SerialDescriptor("LenientUnit", kSerializerSerializer.getDescriptor());
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, Object value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        if (value != null) {
            encoder.encodeSerializableValue(delegateSerializer, Unit.INSTANCE);
        } else {
            encoder.encodeNull();
        }
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public Object deserialize(Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        if (decoder.decodeNotNullMark()) {
            decoder.decodeSerializableValue(delegateSerializer);
            return Unit.INSTANCE;
        }
        decoder.decodeNull();
        return null;
    }
}
