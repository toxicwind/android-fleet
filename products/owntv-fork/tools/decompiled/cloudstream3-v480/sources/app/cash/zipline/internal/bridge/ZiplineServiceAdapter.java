package app.cash.zipline.internal.bridge;

import androidx.exifinterface.media.ExifInterface;
import app.cash.zipline.ZiplineFunction;
import app.cash.zipline.ZiplineService;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.ContextualSerializer;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.modules.SerializersModule;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: ZiplineServiceAdapter.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b!\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u001c\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00180\n2\u0006\u0010\u0019\u001a\u00020\u001aH&J\u0015\u0010\u001b\u001a\u00028\u00002\u0006\u0010\u001c\u001a\u00020\u001dH&¢\u0006\u0002\u0010\u001eJ\u001f\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00028\u0000H\u0096\u0080\u0004¢\u0006\u0002\u0010$J\u0017\u0010%\u001a\u00028\u00002\u0006\u0010&\u001a\u00020'H\u0096\u0080\u0004¢\u0006\u0002\u0010(J\u0014\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010,H\u0096\u0082\u0004J\n\u0010-\u001a\u00020.H\u0096\u0080\u0004J\n\u0010/\u001a\u00020\u000eH\u0096\u0080\u0004R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0012\u0010\r\u001a\u00020\u000eX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0012\u0010\u0011\u001a\u00020\u000eX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0010R\u0015\u0010\u0013\u001a\u00020\u0014X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u00060"}, d2 = {"Lapp/cash/zipline/internal/bridge/ZiplineServiceAdapter;", ExifInterface.GPS_DIRECTION_TRUE, "Lapp/cash/zipline/ZiplineService;", "Lkotlinx/serialization/KSerializer;", "<init>", "()V", "contextualSerializer", "Lkotlinx/serialization/ContextualSerializer;", "Lapp/cash/zipline/internal/bridge/PassByReference;", "serializers", "", "getSerializers", "()Ljava/util/List;", "serialName", "", "getSerialName", "()Ljava/lang/String;", "simpleName", "getSimpleName", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "ziplineFunctions", "Lapp/cash/zipline/ZiplineFunction;", "serializersModule", "Lkotlinx/serialization/modules/SerializersModule;", "outboundService", "callHandler", "Lapp/cash/zipline/internal/bridge/OutboundCallHandler;", "(Lapp/cash/zipline/internal/bridge/OutboundCallHandler;)Lapp/cash/zipline/ZiplineService;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", ES6Iterator.VALUE_PROPERTY, "(Lkotlinx/serialization/encoding/Encoder;Lapp/cash/zipline/ZiplineService;)V", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "(Lkotlinx/serialization/encoding/Decoder;)Lapp/cash/zipline/ZiplineService;", "equals", "", "other", "", "hashCode", "", "toString", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class ZiplineServiceAdapter<T extends ZiplineService> implements KSerializer<T> {
    private final ContextualSerializer<PassByReference> contextualSerializer;
    private final SerialDescriptor descriptor;

    public abstract String getSerialName();

    public abstract List<KSerializer<?>> getSerializers();

    public abstract String getSimpleName();

    public abstract T outboundService(OutboundCallHandler callHandler);

    public abstract List<ZiplineFunction<T>> ziplineFunctions(SerializersModule serializersModule);

    public ZiplineServiceAdapter() {
        ContextualSerializer<PassByReference> contextualSerializer = new ContextualSerializer<>(Reflection.getOrCreateKotlinClass(PassByReference.class));
        this.contextualSerializer = contextualSerializer;
        this.descriptor = contextualSerializer.getDescriptor();
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, T value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        this.contextualSerializer.serialize(encoder, new SendByReference(value, this));
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public T deserialize(Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        PassByReference passByReferenceDeserialize = this.contextualSerializer.deserialize(decoder);
        Intrinsics.checkNotNull(passByReferenceDeserialize, "null cannot be cast to non-null type app.cash.zipline.internal.bridge.ReceiveByReference");
        return (T) ((ReceiveByReference) passByReferenceDeserialize).take(this);
    }

    public boolean equals(Object other) {
        return (other instanceof ZiplineServiceAdapter) && getClass() == other.getClass() && Intrinsics.areEqual(getSerializers(), ((ZiplineServiceAdapter) other).getSerializers());
    }

    public int hashCode() {
        return Reflection.getOrCreateKotlinClass(getClass()).hashCode();
    }

    public String toString() {
        return getSimpleName();
    }
}
