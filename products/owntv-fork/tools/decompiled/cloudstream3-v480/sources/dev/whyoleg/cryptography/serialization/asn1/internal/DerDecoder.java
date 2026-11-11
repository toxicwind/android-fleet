package dev.whyoleg.cryptography.serialization.asn1.internal;

import androidx.exifinterface.media.ExifInterface;
import androidx.tvprovider.media.tv.TvContractCompat;
import dev.whyoleg.cryptography.bigint.BigInt;
import dev.whyoleg.cryptography.serialization.asn1.BitArray;
import dev.whyoleg.cryptography.serialization.asn1.ContextSpecificTag;
import dev.whyoleg.cryptography.serialization.asn1.Der;
import dev.whyoleg.cryptography.serialization.asn1.ObjectIdentifier;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.PolymorphicKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.descriptors.StructureKind;
import kotlinx.serialization.encoding.AbstractDecoder;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: compiled from: DerDecoder.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0000\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0019\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\f\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u0082\u0080\u0004J\u0012\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0015H\u0096\u0080\u0004J\n\u0010\u0016\u001a\u00020\u0017H\u0096\u0080\u0004J\f\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0096\u0080\u0004J\n\u0010\u001a\u001a\u00020\u001bH\u0096\u0080\u0004J\n\u0010\u001c\u001a\u00020\u001dH\u0096\u0080\u0004J\n\u0010\u001e\u001a\u00020\u000fH\u0096\u0080\u0004J\n\u0010\u001f\u001a\u00020 H\u0096\u0080\u0004J#\u0010!\u001a\u0002H\"\"\u0004\b\u0000\u0010\"2\f\u0010#\u001a\b\u0012\u0004\u0012\u0002H\"0$H\u0096\u0080\u0004¢\u0006\u0002\u0010%J\u0012\u0010&\u001a\u00020'2\u0006\u0010\u0014\u001a\u00020\u0015H\u0096\u0080\u0004J\u0012\u0010(\u001a\u00020)2\u0006\u0010\u0014\u001a\u00020\u0015H\u0096\u0080\u0004J\u001a\u0010*\u001a\u00020)2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010+\u001a\u00020\u000fH\u0096\u0080\u0004J\u0012\u0010,\u001a\u00020\u000f2\u0006\u0010-\u001a\u00020\u0015H\u0096\u0080\u0004J\n\u0010.\u001a\u00020/H\u0096\u0080\u0004J\n\u00100\u001a\u000201H\u0096\u0080\u0004J\n\u00102\u001a\u00020\u0017H\u0096\u0080\u0004J\n\u00103\u001a\u000204H\u0096\u0080\u0004J\n\u00105\u001a\u000206H\u0096\u0080\u0004J\n\u00107\u001a\u000208H\u0096\u0080\u0004R\u000f\u0010\u0002\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\b\u001a\u00020\tX\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0015\u0010\n\u001a\u00020\u000b8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u000f\u0010\u000e\u001a\u00020\u000fX\u0082\u008e\b¢\u0006\u0002\n\u0000R\u0011\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u008e\b¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Ldev/whyoleg/cryptography/serialization/asn1/internal/DerDecoder;", "Lkotlinx/serialization/encoding/AbstractDecoder;", "der", "Ldev/whyoleg/cryptography/serialization/asn1/Der;", "byteArrayInput", "Ldev/whyoleg/cryptography/serialization/asn1/internal/ByteArrayInput;", "<init>", "(Ldev/whyoleg/cryptography/serialization/asn1/Der;Ldev/whyoleg/cryptography/serialization/asn1/internal/ByteArrayInput;)V", TvContractCompat.PARAM_INPUT, "Ldev/whyoleg/cryptography/serialization/asn1/internal/DerInput;", "serializersModule", "Lkotlinx/serialization/modules/SerializersModule;", "getSerializersModule", "()Lkotlinx/serialization/modules/SerializersModule;", "currentIndex", "", "tagOverride", "Ldev/whyoleg/cryptography/serialization/asn1/ContextSpecificTag;", "getAndResetTagOverride", "decodeElementIndex", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "decodeNotNullMark", "", "decodeNull", "", "decodeByte", "", "decodeShort", "", "decodeInt", "decodeLong", "", "decodeSerializableValue", ExifInterface.GPS_DIRECTION_TRUE, "deserializer", "Lkotlinx/serialization/DeserializationStrategy;", "(Lkotlinx/serialization/DeserializationStrategy;)Ljava/lang/Object;", "beginStructure", "Lkotlinx/serialization/encoding/CompositeDecoder;", "decodeInline", "Lkotlinx/serialization/encoding/Decoder;", "decodeInlineElement", "index", "decodeEnum", "enumDescriptor", "decodeString", "", "decodeChar", "", "decodeBoolean", "decodeDouble", "", "decodeFloat", "", "decodeValue", "", "cryptography-serialization-asn1"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DerDecoder extends AbstractDecoder {
    private int currentIndex;
    private final Der der;
    private final DerInput input;
    private ContextSpecificTag tagOverride;

    public DerDecoder(Der der, ByteArrayInput byteArrayInput) {
        Intrinsics.checkNotNullParameter(der, "der");
        Intrinsics.checkNotNullParameter(byteArrayInput, "byteArrayInput");
        this.der = der;
        this.input = new DerInput(byteArrayInput);
    }

    @Override // kotlinx.serialization.encoding.Decoder, kotlinx.serialization.encoding.CompositeDecoder
    public SerializersModule getSerializersModule() {
        return this.der.getSerializersModule();
    }

    private final ContextSpecificTag getAndResetTagOverride() {
        ContextSpecificTag contextSpecificTag = this.tagOverride;
        this.tagOverride = null;
        return contextSpecificTag;
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public int decodeElementIndex(SerialDescriptor descriptor) {
        ContextSpecificTag contextSpecificTag;
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        if (this.input.getEof()) {
            return -1;
        }
        byte bPeakTag = this.input.peakTag();
        while (true) {
            int i = this.currentIndex;
            ContextSpecificTag elementContextSpecificTag = DerTagKt.getElementContextSpecificTag(descriptor, i);
            this.tagOverride = elementContextSpecificTag;
            if (elementContextSpecificTag == null || !descriptor.isElementOptional(i) || ((contextSpecificTag = this.tagOverride) != null && bPeakTag == DerTagKt.getTag(contextSpecificTag))) {
                break;
            }
            this.currentIndex++;
        }
        int i2 = this.currentIndex;
        this.currentIndex = i2 + 1;
        return i2;
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public boolean decodeNotNullMark() {
        return this.input.isNotNull();
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public Void decodeNull() {
        return this.input.readNull();
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public byte decodeByte() {
        return this.input.readInteger(getAndResetTagOverride()).byteValue();
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public short decodeShort() {
        return this.input.readInteger(getAndResetTagOverride()).shortValue();
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public int decodeInt() {
        return this.input.readInteger(getAndResetTagOverride()).intValue();
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public long decodeLong() {
        return this.input.readInteger(getAndResetTagOverride()).longValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public <T> T decodeSerializableValue(DeserializationStrategy<? extends T> deserializer) {
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        SerialDescriptor descriptor = deserializer.getDescriptor();
        if (Intrinsics.areEqual(descriptor, BuiltinSerializersKt.ByteArraySerializer().getDescriptor())) {
            return (T) this.input.readOctetString(getAndResetTagOverride());
        }
        if (Intrinsics.areEqual(descriptor, BitArray.INSTANCE.serializer().getDescriptor())) {
            return (T) this.input.readBitString(getAndResetTagOverride());
        }
        if (Intrinsics.areEqual(descriptor, ObjectIdentifier.INSTANCE.serializer().getDescriptor())) {
            return (T) ObjectIdentifier.m8755boximpl(this.input.m8765readObjectIdentifieriVrrli8(getAndResetTagOverride()));
        }
        return Intrinsics.areEqual(descriptor, BigInt.INSTANCE.serializer().getDescriptor()) ? (T) this.input.readInteger(getAndResetTagOverride()) : deserializer.deserialize(this);
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public CompositeDecoder beginStructure(SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        SerialKind kind = descriptor.getKind();
        if (Intrinsics.areEqual(kind, StructureKind.CLASS.INSTANCE) || (kind instanceof PolymorphicKind)) {
            return new DerDecoder(this.der, this.input.readSequence(getAndResetTagOverride()));
        }
        throw new SerializationException("This serial kind is not supported as structure: " + descriptor);
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public Decoder decodeInline(SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return this;
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.CompositeDecoder
    public Decoder decodeInlineElement(SerialDescriptor descriptor, int index) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return this;
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public int decodeEnum(SerialDescriptor enumDescriptor) {
        Intrinsics.checkNotNullParameter(enumDescriptor, "enumDescriptor");
        throw new IllegalStateException("Enum decoding is not supported".toString());
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public String decodeString() {
        throw new IllegalStateException("String decoding is not supported".toString());
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public char decodeChar() {
        throw new IllegalStateException("Char decoding is not supported".toString());
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public boolean decodeBoolean() {
        throw new IllegalStateException("Boolean decoding is not supported".toString());
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public double decodeDouble() {
        throw new IllegalStateException("Double decoding is not supported".toString());
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public float decodeFloat() {
        throw new IllegalStateException("Float decoding is not supported".toString());
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder
    public Object decodeValue() {
        throw new IllegalStateException("should not be called".toString());
    }
}
