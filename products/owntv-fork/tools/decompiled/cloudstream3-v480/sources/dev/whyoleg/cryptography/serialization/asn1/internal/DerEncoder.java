package dev.whyoleg.cryptography.serialization.asn1.internal;

import androidx.exifinterface.media.ExifInterface;
import dev.whyoleg.cryptography.bigint.BigInt;
import dev.whyoleg.cryptography.bigint.BigIntKt;
import dev.whyoleg.cryptography.serialization.asn1.BitArray;
import dev.whyoleg.cryptography.serialization.asn1.ContextSpecificTag;
import dev.whyoleg.cryptography.serialization.asn1.Der;
import dev.whyoleg.cryptography.serialization.asn1.ObjectIdentifier;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.PolymorphicKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.descriptors.StructureKind;
import kotlinx.serialization.encoding.AbstractEncoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.modules.SerializersModule;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: DerEncoder.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0000\n\u0000\b\u0000\u0018\u00002\u00020\u0001B%\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\u001a\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0096\u0080\u0004J\f\u0010\u0017\u001a\u0004\u0018\u00010\u0016H\u0082\u0080\u0004J\u001a\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0096\u0080\u0004J\n\u0010\u0019\u001a\u00020\u001aH\u0096\u0080\u0004J\n\u0010\u001b\u001a\u00020\u001aH\u0096\u0080\u0004J\u0012\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001eH\u0096\u0080\u0004J\u0012\u0010\u001f\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020 H\u0096\u0080\u0004J\u0012\u0010!\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u0014H\u0096\u0080\u0004J\u0012\u0010\"\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020#H\u0096\u0080\u0004J+\u0010$\u001a\u00020\u001a\"\u0004\b\u0000\u0010%2\f\u0010&\u001a\b\u0012\u0004\u0012\u0002H%0'2\u0006\u0010\u001d\u001a\u0002H%H\u0096\u0080\u0004¢\u0006\u0002\u0010(J\u0012\u0010)\u001a\u00020*2\u0006\u0010\u0011\u001a\u00020\u0012H\u0096\u0080\u0004J\u0012\u0010+\u001a\u00020\u001a2\u0006\u0010\u0011\u001a\u00020\u0012H\u0096\u0080\u0004J\u0012\u0010,\u001a\u00020-2\u0006\u0010\u0011\u001a\u00020\u0012H\u0096\u0080\u0004J\u001a\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0096\u0080\u0004J\u0012\u00101\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u000202H\u0096\u0080\u0004J\u0012\u00103\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u000204H\u0096\u0080\u0004J\u0012\u00105\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u0010H\u0096\u0080\u0004J\u0012\u00106\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u000207H\u0096\u0080\u0004J\u0012\u00108\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u000209H\u0096\u0080\u0004J\u0012\u0010:\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020;H\u0096\u0080\u0004R\u000f\u0010\u0002\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\n\u001a\u00020\u0007X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0015\u0010\u000b\u001a\u00020\f8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u008e\b¢\u0006\u0002\n\u0000¨\u0006<"}, d2 = {"Ldev/whyoleg/cryptography/serialization/asn1/internal/DerEncoder;", "Lkotlinx/serialization/encoding/AbstractEncoder;", "der", "Ldev/whyoleg/cryptography/serialization/asn1/Der;", "byteArrayOutput", "Ldev/whyoleg/cryptography/serialization/asn1/internal/ByteArrayOutput;", "parentOutput", "Ldev/whyoleg/cryptography/serialization/asn1/internal/DerOutput;", "<init>", "(Ldev/whyoleg/cryptography/serialization/asn1/Der;Ldev/whyoleg/cryptography/serialization/asn1/internal/ByteArrayOutput;Ldev/whyoleg/cryptography/serialization/asn1/internal/DerOutput;)V", "output", "serializersModule", "Lkotlinx/serialization/modules/SerializersModule;", "getSerializersModule", "()Lkotlinx/serialization/modules/SerializersModule;", "shouldEncodeElementDefault", "", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "index", "", "tagOverride", "Ldev/whyoleg/cryptography/serialization/asn1/ContextSpecificTag;", "getAndResetTagOverride", "encodeElement", "encodeNotNullMark", "", "encodeNull", "encodeByte", ES6Iterator.VALUE_PROPERTY, "", "encodeShort", "", "encodeInt", "encodeLong", "", "encodeSerializableValue", ExifInterface.GPS_DIRECTION_TRUE, "serializer", "Lkotlinx/serialization/SerializationStrategy;", "(Lkotlinx/serialization/SerializationStrategy;Ljava/lang/Object;)V", "beginStructure", "Lkotlinx/serialization/encoding/CompositeEncoder;", "endStructure", "encodeInline", "Lkotlinx/serialization/encoding/Encoder;", "encodeEnum", "", "enumDescriptor", "encodeString", "", "encodeChar", "", "encodeBoolean", "encodeFloat", "", "encodeDouble", "", "encodeValue", "", "cryptography-serialization-asn1"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DerEncoder extends AbstractEncoder {
    private final Der der;
    private final DerOutput output;
    private final DerOutput parentOutput;
    private ContextSpecificTag tagOverride;

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public void encodeNotNullMark() {
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.CompositeEncoder
    public boolean shouldEncodeElementDefault(SerialDescriptor descriptor, int index) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return false;
    }

    public DerEncoder(Der der, ByteArrayOutput byteArrayOutput, DerOutput derOutput) {
        Intrinsics.checkNotNullParameter(der, "der");
        Intrinsics.checkNotNullParameter(byteArrayOutput, "byteArrayOutput");
        this.der = der;
        this.parentOutput = derOutput;
        this.output = new DerOutput(byteArrayOutput);
    }

    public /* synthetic */ DerEncoder(Der der, ByteArrayOutput byteArrayOutput, DerOutput derOutput, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(der, byteArrayOutput, (i & 4) != 0 ? null : derOutput);
    }

    @Override // kotlinx.serialization.encoding.Encoder, kotlinx.serialization.encoding.CompositeEncoder
    public SerializersModule getSerializersModule() {
        return this.der.getSerializersModule();
    }

    private final ContextSpecificTag getAndResetTagOverride() {
        ContextSpecificTag contextSpecificTag = this.tagOverride;
        this.tagOverride = null;
        return contextSpecificTag;
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder
    public boolean encodeElement(SerialDescriptor descriptor, int index) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        this.tagOverride = DerTagKt.getElementContextSpecificTag(descriptor, index);
        return true;
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public void encodeNull() {
        this.output.writeNull();
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public void encodeByte(byte value) {
        this.output.writeInteger(getAndResetTagOverride(), BigIntKt.toBigInt(value));
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public void encodeShort(short value) {
        this.output.writeInteger(getAndResetTagOverride(), BigIntKt.toBigInt(value));
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public void encodeInt(int value) {
        this.output.writeInteger(getAndResetTagOverride(), BigIntKt.toBigInt(value));
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public void encodeLong(long value) {
        this.output.writeInteger(getAndResetTagOverride(), BigIntKt.toBigInt(value));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public <T> void encodeSerializableValue(SerializationStrategy<? super T> serializer, T value) {
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        SerialDescriptor descriptor = serializer.getDescriptor();
        if (Intrinsics.areEqual(descriptor, BuiltinSerializersKt.ByteArraySerializer().getDescriptor())) {
            DerOutput derOutput = this.output;
            ContextSpecificTag andResetTagOverride = getAndResetTagOverride();
            Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.ByteArray");
            derOutput.writeOctetString(andResetTagOverride, (byte[]) value);
            return;
        }
        if (Intrinsics.areEqual(descriptor, BitArray.INSTANCE.serializer().getDescriptor())) {
            DerOutput derOutput2 = this.output;
            ContextSpecificTag andResetTagOverride2 = getAndResetTagOverride();
            Intrinsics.checkNotNull(value, "null cannot be cast to non-null type dev.whyoleg.cryptography.serialization.asn1.BitArray");
            derOutput2.writeBitString(andResetTagOverride2, (BitArray) value);
            return;
        }
        if (Intrinsics.areEqual(descriptor, ObjectIdentifier.INSTANCE.serializer().getDescriptor())) {
            DerOutput derOutput3 = this.output;
            ContextSpecificTag andResetTagOverride3 = getAndResetTagOverride();
            Intrinsics.checkNotNull(value, "null cannot be cast to non-null type dev.whyoleg.cryptography.serialization.asn1.ObjectIdentifier");
            derOutput3.m8766writeObjectIdentifierIHeWLgs(andResetTagOverride3, ((ObjectIdentifier) value).m8761unboximpl());
            return;
        }
        if (!Intrinsics.areEqual(descriptor, BigInt.INSTANCE.serializer().getDescriptor())) {
            serializer.serialize(this, value);
            return;
        }
        DerOutput derOutput4 = this.output;
        ContextSpecificTag andResetTagOverride4 = getAndResetTagOverride();
        Intrinsics.checkNotNull(value, "null cannot be cast to non-null type dev.whyoleg.cryptography.bigint.BigInt");
        derOutput4.writeInteger(andResetTagOverride4, (BigInt) value);
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public CompositeEncoder beginStructure(SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        SerialKind kind = descriptor.getKind();
        if (Intrinsics.areEqual(kind, StructureKind.CLASS.INSTANCE) || (kind instanceof PolymorphicKind)) {
            return new DerEncoder(this.der, new ByteArrayOutput(), this.output);
        }
        throw new SerializationException("This serial kind is not supported as structure: " + descriptor);
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.CompositeEncoder
    public void endStructure(SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        DerOutput derOutput = this.parentOutput;
        if (derOutput == null) {
            throw new IllegalStateException("Should be called after beginStructure".toString());
        }
        derOutput.writeSequence(getAndResetTagOverride(), this.output);
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public Encoder encodeInline(SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return this;
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    /* JADX INFO: renamed from: encodeEnum, reason: merged with bridge method [inline-methods] */
    public Void mo8764encodeEnum(SerialDescriptor enumDescriptor, int index) {
        Intrinsics.checkNotNullParameter(enumDescriptor, "enumDescriptor");
        throw new IllegalStateException("Enum encoding is not supported".toString());
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public void encodeString(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        throw new IllegalStateException("String encoding is not supported".toString());
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public void encodeChar(char value) {
        throw new IllegalStateException("Char encoding is not supported".toString());
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public void encodeBoolean(boolean value) {
        throw new IllegalStateException("Boolean encoding is not supported".toString());
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public void encodeFloat(float value) {
        throw new IllegalStateException("Float encoding is not supported".toString());
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public void encodeDouble(double value) {
        throw new IllegalStateException("Double encoding is not supported".toString());
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder
    public void encodeValue(Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        throw new IllegalStateException("should not be called".toString());
    }
}
