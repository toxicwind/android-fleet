package dev.whyoleg.cryptography.bigint;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: BigIntSerializers.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\bB¢\u0006\u0004\b\u0003\u0010\u0004J\u001a\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0002H\u0096\u0080\u0004J\u0012\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0011H\u0096\u0080\u0004R\u001b\u0010\u0005\u001a\u00020\u0006X\u0096\u0084\b¢\u0006\u000e\n\u0000\u0012\u0004\b\u0007\u0010\u0004\u001a\u0004\b\b\u0010\t¨\u0006\u0012"}, d2 = {"Ldev/whyoleg/cryptography/bigint/BigIntAsByteArraySerializer;", "Lkotlinx/serialization/KSerializer;", "Ldev/whyoleg/cryptography/bigint/BigInt;", "<init>", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor$annotations", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", ES6Iterator.VALUE_PROPERTY, "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "cryptography-bigint"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class BigIntAsByteArraySerializer implements KSerializer<BigInt> {
    public static final BigIntAsByteArraySerializer INSTANCE = new BigIntAsByteArraySerializer();
    private static final SerialDescriptor descriptor = SerialDescriptorsKt.SerialDescriptor("BigInt", BuiltinSerializersKt.ByteArraySerializer().getDescriptor());

    public static /* synthetic */ void getDescriptor$annotations() {
    }

    private BigIntAsByteArraySerializer() {
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, BigInt value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        encoder.encodeSerializableValue(BuiltinSerializersKt.ByteArraySerializer(), BigIntKt.encodeToByteArray(value));
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public BigInt deserialize(Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        return BigIntKt.decodeToBigInt((byte[]) decoder.decodeSerializableValue(BuiltinSerializersKt.ByteArraySerializer()));
    }
}
