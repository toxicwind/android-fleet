package dev.whyoleg.cryptography.serialization.asn1.modules;

import dev.whyoleg.cryptography.serialization.asn1.ObjectIdentifier;
import dev.whyoleg.cryptography.serialization.asn1.ObjectIdentifier$$serializer;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.InlineClassDescriptor;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: EC.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0019\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006H\u0086\u0080\u0004¢\u0006\u0002\u0010\bJ\u0019\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0086\u0080\u0004¢\u0006\u0004\b\f\u0010\rJ!\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0002H\u0086\u0080\u0004¢\u0006\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0015\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"dev/whyoleg/cryptography/serialization/asn1/modules/EcParameters.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Ldev/whyoleg/cryptography/serialization/asn1/modules/EcParameters;", "<init>", "()V", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "deserialize-kq5xtGw", "(Lkotlinx/serialization/encoding/Decoder;)Ljava/lang/String;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", ES6Iterator.VALUE_PROPERTY, "serialize-ovaej6M", "(Lkotlinx/serialization/encoding/Encoder;Ljava/lang/String;)V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "cryptography-serialization-asn1-modules"}, k = 1, mv = {2, 3, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly")
public final /* synthetic */ class EcParameters$$serializer implements GeneratedSerializer<EcParameters> {
    public static final EcParameters$$serializer INSTANCE;
    private static final SerialDescriptor descriptor;

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public /* synthetic */ KSerializer[] typeParametersSerializers() {
        return GeneratedSerializer.CC.$default$typeParametersSerializers(this);
    }

    static {
        EcParameters$$serializer ecParameters$$serializer = new EcParameters$$serializer();
        INSTANCE = ecParameters$$serializer;
        InlineClassDescriptor inlineClassDescriptor = new InlineClassDescriptor("dev.whyoleg.cryptography.serialization.asn1.modules.EcParameters", ecParameters$$serializer);
        inlineClassDescriptor.addElement("namedCurve", false);
        descriptor = inlineClassDescriptor;
    }

    private EcParameters$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public final KSerializer<?>[] childSerializers() {
        return new KSerializer[]{ObjectIdentifier$$serializer.INSTANCE};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public /* bridge */ /* synthetic */ Object deserialize(Decoder decoder) {
        return EcParameters.m8777boximpl(m8785deserializekq5xtGw(decoder));
    }

    /* JADX INFO: renamed from: deserialize-kq5xtGw, reason: not valid java name */
    public final String m8785deserializekq5xtGw(Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        return EcParameters.m8778constructorimpl(((ObjectIdentifier) decoder.decodeInline(descriptor).decodeSerializableValue(ObjectIdentifier$$serializer.INSTANCE)).m8761unboximpl());
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public /* bridge */ /* synthetic */ void serialize(Encoder encoder, Object obj) {
        m8786serializeovaej6M(encoder, ((EcParameters) obj).m8784unboximpl());
    }

    /* JADX INFO: renamed from: serialize-ovaej6M, reason: not valid java name */
    public final void m8786serializeovaej6M(Encoder encoder, String value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        Encoder encoderEncodeInline = encoder.encodeInline(descriptor);
        if (encoderEncodeInline == null) {
            return;
        }
        encoderEncodeInline.encodeSerializableValue(ObjectIdentifier$$serializer.INSTANCE, ObjectIdentifier.m8755boximpl(value));
    }
}
