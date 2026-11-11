package app.cash.zipline.internal.bridge;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: passByReference.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u001a\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0002H\u0096\u0080\u0004J\u0012\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0014H\u0096\u0080\u0004R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0015\u0010\t\u001a\u00020\nX\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0015"}, d2 = {"Lapp/cash/zipline/internal/bridge/PassByReferenceSerializer;", "Lkotlinx/serialization/KSerializer;", "Lapp/cash/zipline/internal/bridge/PassByReference;", "endpoint", "Lapp/cash/zipline/internal/bridge/Endpoint;", "<init>", "(Lapp/cash/zipline/internal/bridge/Endpoint;)V", "getEndpoint", "()Lapp/cash/zipline/internal/bridge/Endpoint;", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", ES6Iterator.VALUE_PROPERTY, "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class PassByReferenceSerializer implements KSerializer<PassByReference> {
    private final SerialDescriptor descriptor;
    private final Endpoint endpoint;

    public PassByReferenceSerializer(Endpoint endpoint) {
        Intrinsics.checkNotNullParameter(endpoint, "endpoint");
        this.endpoint = endpoint;
        this.descriptor = SerialDescriptorsKt.PrimitiveSerialDescriptor("PassByReference", PrimitiveKind.STRING.INSTANCE);
    }

    public final Endpoint getEndpoint() {
        return this.endpoint;
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, PassByReference value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        if (!(value instanceof SendByReference)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        String strGeneratePassByReferenceName$zipline_release = this.endpoint.generatePassByReferenceName$zipline_release();
        SendByReference sendByReference = (SendByReference) value;
        if (sendByReference.getService() instanceof HasPassByReferenceName) {
            ((HasPassByReferenceName) sendByReference.getService()).setPassByReferenceName(strGeneratePassByReferenceName$zipline_release);
        }
        this.endpoint.getCallCodec().getEncodedServiceNames().add(strGeneratePassByReferenceName$zipline_release);
        sendByReference.bind(this.endpoint, strGeneratePassByReferenceName$zipline_release);
        encoder.encodeString(strGeneratePassByReferenceName$zipline_release);
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public PassByReference deserialize(Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        String strDecodeString = decoder.decodeString();
        this.endpoint.getCallCodec().getDecodedServiceNames().add(strDecodeString);
        return new ReceiveByReference(strDecodeString, this.endpoint);
    }
}
