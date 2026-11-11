package kotlinx.serialization.internal;

import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.List;
import kotlin.IgnorableReturnValue;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.ExperimentalSerializationApi;
import kotlinx.serialization.InternalSerializationApi;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.modules.SerializersModule;
import kotlinx.serialization.modules.SerializersModuleBuildersKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: Tagged.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b(\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b'\u0018\u0000*\n\b\u0000\u0010\u0001*\u0004\u0018\u00010\u00022\u00020\u00032\u00020\u0004B\t\bF¢\u0006\u0004\b\u0005\u0010\u0006J\u001b\u0010\u0007\u001a\u00028\u0000*\u00020\b2\u0006\u0010\t\u001a\u00020\nH¤\u0080\u0004¢\u0006\u0002\u0010\u000bJ\u001f\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00028\u00002\u0006\u0010\u0013\u001a\u00020\u0002H\u0094\u0080\u0004¢\u0006\u0002\u0010\u0014J\u0017\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00028\u0000H\u0094\u0080\u0004¢\u0006\u0002\u0010\u0016J\u0017\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00028\u0000H\u0094\u0080\u0004¢\u0006\u0002\u0010\u0016J\u001f\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00028\u00002\u0006\u0010\u0013\u001a\u00020\nH\u0094\u0080\u0004¢\u0006\u0002\u0010\u0019J\u001f\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00028\u00002\u0006\u0010\u0013\u001a\u00020\u001bH\u0094\u0080\u0004¢\u0006\u0002\u0010\u001cJ\u001f\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00028\u00002\u0006\u0010\u0013\u001a\u00020\u001eH\u0094\u0080\u0004¢\u0006\u0002\u0010\u001fJ\u001f\u0010 \u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00028\u00002\u0006\u0010\u0013\u001a\u00020!H\u0094\u0080\u0004¢\u0006\u0002\u0010\"J\u001f\u0010#\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00028\u00002\u0006\u0010\u0013\u001a\u00020$H\u0094\u0080\u0004¢\u0006\u0002\u0010%J\u001f\u0010&\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00028\u00002\u0006\u0010\u0013\u001a\u00020'H\u0094\u0080\u0004¢\u0006\u0002\u0010(J\u001f\u0010)\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00028\u00002\u0006\u0010\u0013\u001a\u00020*H\u0094\u0080\u0004¢\u0006\u0002\u0010+J\u001f\u0010,\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00028\u00002\u0006\u0010\u0013\u001a\u00020-H\u0094\u0080\u0004¢\u0006\u0002\u0010.J\u001f\u0010/\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00028\u00002\u0006\u0010\u0013\u001a\u000200H\u0094\u0080\u0004¢\u0006\u0002\u00101J'\u00102\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00028\u00002\u0006\u00103\u001a\u00020\b2\u0006\u00104\u001a\u00020\nH\u0094\u0080\u0004¢\u0006\u0002\u00105J\u001f\u00106\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00028\u00002\u0006\u00107\u001a\u00020\bH\u0094\u0080\u0004¢\u0006\u0002\u00108J\u0012\u00109\u001a\u00020\u00032\u0006\u0010:\u001a\u00020\bH\u0096\u0080\u0004J\u001a\u0010;\u001a\u00020*2\u0006\u0010<\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0082\u0080\u0004J\n\u0010=\u001a\u00020\u0011H\u0096\u0080\u0004J\n\u0010>\u001a\u00020\u0011H\u0096\u0080\u0004J\u0012\u0010?\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020*H\u0086\u0080\u0004J\u0012\u0010@\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u001bH\u0086\u0080\u0004J\u0012\u0010A\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u001eH\u0086\u0080\u0004J\u0012\u0010B\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\nH\u0086\u0080\u0004J\u0012\u0010C\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020!H\u0086\u0080\u0004J\u0012\u0010D\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020$H\u0086\u0080\u0004J\u0012\u0010E\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020'H\u0086\u0080\u0004J\u0012\u0010F\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020-H\u0086\u0080\u0004J\u0012\u0010G\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u000200H\u0086\u0080\u0004J\u001a\u0010H\u001a\u00020\u00112\u0006\u00103\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0086\u0080\u0004J\u0012\u0010I\u001a\u00020\u00042\u0006\u0010:\u001a\u00020\bH\u0096\u0080\u0004J\u0012\u0010J\u001a\u00020\u00112\u0006\u0010:\u001a\u00020\bH\u0086\u0080\u0004J\u0012\u0010K\u001a\u00020\u00112\u0006\u0010:\u001a\u00020\bH\u0094\u0080\u0004J\"\u0010L\u001a\u00020\u00112\u0006\u0010:\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020*H\u0086\u0080\u0004J\"\u0010M\u001a\u00020\u00112\u0006\u0010:\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u001bH\u0086\u0080\u0004J\"\u0010N\u001a\u00020\u00112\u0006\u0010:\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u001eH\u0086\u0080\u0004J\"\u0010O\u001a\u00020\u00112\u0006\u0010:\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\nH\u0086\u0080\u0004J\"\u0010P\u001a\u00020\u00112\u0006\u0010:\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020!H\u0086\u0080\u0004J\"\u0010Q\u001a\u00020\u00112\u0006\u0010:\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020$H\u0086\u0080\u0004J\"\u0010R\u001a\u00020\u00112\u0006\u0010:\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020'H\u0086\u0080\u0004J\"\u0010S\u001a\u00020\u00112\u0006\u0010:\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020-H\u0086\u0080\u0004J\"\u0010T\u001a\u00020\u00112\u0006\u0010:\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0013\u001a\u000200H\u0086\u0080\u0004J\u001a\u0010U\u001a\u00020\u00032\u0006\u0010:\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0086\u0080\u0004JA\u0010V\u001a\u00020\u0011\"\n\b\u0001\u0010W*\u0004\u0018\u00010\u00022\u0006\u0010:\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010X\u001a\b\u0012\u0004\u0012\u0002HW0Y2\u0006\u0010\u0013\u001a\u0002HWH\u0096\u0080\u0004¢\u0006\u0002\u0010ZJA\u0010[\u001a\u00020\u0011\"\b\b\u0001\u0010W*\u00020\u00022\u0006\u0010:\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010X\u001a\b\u0012\u0004\u0012\u0002HW0Y2\b\u0010\u0013\u001a\u0004\u0018\u0001HWH\u0096\u0080\u0004¢\u0006\u0002\u0010ZJ\u0017\u0010d\u001a\u00020\u00112\u0006\u0010e\u001a\u00028\u0000H\u0084\u0080\u0004¢\u0006\u0002\u0010\u0016J\u000f\u0010f\u001a\u00028\u0000H\u0085\u0080\b¢\u0006\u0002\u0010aR\u0015\u0010\f\u001a\u00020\r8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u001f\u0010\\\u001a\u0012\u0012\u0004\u0012\u00028\u00000]j\b\u0012\u0004\u0012\u00028\u0000`^X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0015\u0010_\u001a\u00028\u00008DX\u0084\u0084\b¢\u0006\u0006\u001a\u0004\b`\u0010aR\u0017\u0010b\u001a\u0004\u0018\u00018\u00008DX\u0084\u0084\b¢\u0006\u0006\u001a\u0004\bc\u0010a¨\u0006g"}, d2 = {"Lkotlinx/serialization/internal/TaggedEncoder;", "Tag", "", "Lkotlinx/serialization/encoding/Encoder;", "Lkotlinx/serialization/encoding/CompositeEncoder;", "<init>", "()V", "getTag", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "index", "", "(Lkotlinx/serialization/descriptors/SerialDescriptor;I)Ljava/lang/Object;", "serializersModule", "Lkotlinx/serialization/modules/SerializersModule;", "getSerializersModule", "()Lkotlinx/serialization/modules/SerializersModule;", "encodeTaggedValue", "", "tag", ES6Iterator.VALUE_PROPERTY, "(Ljava/lang/Object;Ljava/lang/Object;)V", "encodeTaggedNonNullMark", "(Ljava/lang/Object;)V", "encodeTaggedNull", "encodeTaggedInt", "(Ljava/lang/Object;I)V", "encodeTaggedByte", "", "(Ljava/lang/Object;B)V", "encodeTaggedShort", "", "(Ljava/lang/Object;S)V", "encodeTaggedLong", "", "(Ljava/lang/Object;J)V", "encodeTaggedFloat", "", "(Ljava/lang/Object;F)V", "encodeTaggedDouble", "", "(Ljava/lang/Object;D)V", "encodeTaggedBoolean", "", "(Ljava/lang/Object;Z)V", "encodeTaggedChar", "", "(Ljava/lang/Object;C)V", "encodeTaggedString", "", "(Ljava/lang/Object;Ljava/lang/String;)V", "encodeTaggedEnum", "enumDescriptor", "ordinal", "(Ljava/lang/Object;Lkotlinx/serialization/descriptors/SerialDescriptor;I)V", "encodeTaggedInline", "inlineDescriptor", "(Ljava/lang/Object;Lkotlinx/serialization/descriptors/SerialDescriptor;)Lkotlinx/serialization/encoding/Encoder;", "encodeInline", "descriptor", "encodeElement", "desc", "encodeNotNullMark", "encodeNull", "encodeBoolean", "encodeByte", "encodeShort", "encodeInt", "encodeLong", "encodeFloat", "encodeDouble", "encodeChar", "encodeString", "encodeEnum", "beginStructure", "endStructure", "endEncode", "encodeBooleanElement", "encodeByteElement", "encodeShortElement", "encodeIntElement", "encodeLongElement", "encodeFloatElement", "encodeDoubleElement", "encodeCharElement", "encodeStringElement", "encodeInlineElement", "encodeSerializableElement", ExifInterface.GPS_DIRECTION_TRUE, "serializer", "Lkotlinx/serialization/SerializationStrategy;", "(Lkotlinx/serialization/descriptors/SerialDescriptor;ILkotlinx/serialization/SerializationStrategy;Ljava/lang/Object;)V", "encodeNullableSerializableElement", "tagStack", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "currentTag", "getCurrentTag", "()Ljava/lang/Object;", "currentTagOrNull", "getCurrentTagOrNull", "pushTag", "name", "popTag", "kotlinx-serialization-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
@InternalSerializationApi
public abstract class TaggedEncoder<Tag> implements Encoder, CompositeEncoder {
    private final ArrayList<Tag> tagStack = new ArrayList<>();

    protected void encodeTaggedNonNullMark(Tag tag) {
    }

    protected void endEncode(SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
    }

    protected abstract Tag getTag(SerialDescriptor serialDescriptor, int i);

    @Override // kotlinx.serialization.encoding.Encoder
    public /* bridge */ CompositeEncoder beginCollection(SerialDescriptor serialDescriptor, int i) {
        return Encoder.CC.$default$beginCollection(this, serialDescriptor, i);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    @ExperimentalSerializationApi
    public /* bridge */ <T> void encodeNullableSerializableValue(SerializationStrategy<? super T> serializationStrategy, T t) {
        Encoder.CC.$default$encodeNullableSerializableValue(this, serializationStrategy, t);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public /* bridge */ <T> void encodeSerializableValue(SerializationStrategy<? super T> serializationStrategy, T t) {
        Encoder.CC.$default$encodeSerializableValue(this, serializationStrategy, t);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    @ExperimentalSerializationApi
    public /* bridge */ boolean shouldEncodeElementDefault(SerialDescriptor serialDescriptor, int i) {
        return CompositeEncoder.CC.$default$shouldEncodeElementDefault(this, serialDescriptor, i);
    }

    @Override // kotlinx.serialization.encoding.Encoder, kotlinx.serialization.encoding.CompositeEncoder
    public SerializersModule getSerializersModule() {
        return SerializersModuleBuildersKt.EmptySerializersModule();
    }

    protected void encodeTaggedValue(Tag tag, Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        throw new SerializationException("Non-serializable " + Reflection.getOrCreateKotlinClass(value.getClass()) + " is not supported by " + Reflection.getOrCreateKotlinClass(getClass()) + " encoder");
    }

    protected void encodeTaggedNull(Tag tag) {
        throw new SerializationException("null is not supported");
    }

    protected void encodeTaggedInt(Tag tag, int value) {
        encodeTaggedValue(tag, Integer.valueOf(value));
    }

    protected void encodeTaggedByte(Tag tag, byte value) {
        encodeTaggedValue(tag, Byte.valueOf(value));
    }

    protected void encodeTaggedShort(Tag tag, short value) {
        encodeTaggedValue(tag, Short.valueOf(value));
    }

    protected void encodeTaggedLong(Tag tag, long value) {
        encodeTaggedValue(tag, Long.valueOf(value));
    }

    protected void encodeTaggedFloat(Tag tag, float value) {
        encodeTaggedValue(tag, Float.valueOf(value));
    }

    protected void encodeTaggedDouble(Tag tag, double value) {
        encodeTaggedValue(tag, Double.valueOf(value));
    }

    protected void encodeTaggedBoolean(Tag tag, boolean value) {
        encodeTaggedValue(tag, Boolean.valueOf(value));
    }

    protected void encodeTaggedChar(Tag tag, char value) {
        encodeTaggedValue(tag, Character.valueOf(value));
    }

    protected void encodeTaggedString(Tag tag, String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        encodeTaggedValue(tag, value);
    }

    protected void encodeTaggedEnum(Tag tag, SerialDescriptor enumDescriptor, int ordinal) {
        Intrinsics.checkNotNullParameter(enumDescriptor, "enumDescriptor");
        encodeTaggedValue(tag, Integer.valueOf(ordinal));
    }

    protected Encoder encodeTaggedInline(Tag tag, SerialDescriptor inlineDescriptor) {
        Intrinsics.checkNotNullParameter(inlineDescriptor, "inlineDescriptor");
        pushTag(tag);
        return this;
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public Encoder encodeInline(SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return encodeTaggedInline(popTag(), descriptor);
    }

    private final boolean encodeElement(SerialDescriptor desc, int index) {
        pushTag(getTag(desc, index));
        return true;
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public void encodeNotNullMark() {
        encodeTaggedNonNullMark(getCurrentTag());
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public void encodeNull() {
        encodeTaggedNull(popTag());
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public final void encodeBoolean(boolean value) {
        encodeTaggedBoolean(popTag(), value);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public final void encodeByte(byte value) {
        encodeTaggedByte(popTag(), value);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public final void encodeShort(short value) {
        encodeTaggedShort(popTag(), value);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public final void encodeInt(int value) {
        encodeTaggedInt(popTag(), value);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public final void encodeLong(long value) {
        encodeTaggedLong(popTag(), value);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public final void encodeFloat(float value) {
        encodeTaggedFloat(popTag(), value);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public final void encodeDouble(double value) {
        encodeTaggedDouble(popTag(), value);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public final void encodeChar(char value) {
        encodeTaggedChar(popTag(), value);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public final void encodeString(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        encodeTaggedString(popTag(), value);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    /* JADX INFO: renamed from: encodeEnum */
    public final void mo8764encodeEnum(SerialDescriptor enumDescriptor, int index) {
        Intrinsics.checkNotNullParameter(enumDescriptor, "enumDescriptor");
        encodeTaggedEnum(popTag(), enumDescriptor, index);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public CompositeEncoder beginStructure(SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return this;
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void endStructure(SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        if (!this.tagStack.isEmpty()) {
            popTag();
        }
        endEncode(descriptor);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void encodeBooleanElement(SerialDescriptor descriptor, int index, boolean value) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        encodeTaggedBoolean(getTag(descriptor, index), value);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void encodeByteElement(SerialDescriptor descriptor, int index, byte value) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        encodeTaggedByte(getTag(descriptor, index), value);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void encodeShortElement(SerialDescriptor descriptor, int index, short value) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        encodeTaggedShort(getTag(descriptor, index), value);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void encodeIntElement(SerialDescriptor descriptor, int index, int value) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        encodeTaggedInt(getTag(descriptor, index), value);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void encodeLongElement(SerialDescriptor descriptor, int index, long value) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        encodeTaggedLong(getTag(descriptor, index), value);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void encodeFloatElement(SerialDescriptor descriptor, int index, float value) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        encodeTaggedFloat(getTag(descriptor, index), value);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void encodeDoubleElement(SerialDescriptor descriptor, int index, double value) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        encodeTaggedDouble(getTag(descriptor, index), value);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void encodeCharElement(SerialDescriptor descriptor, int index, char value) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        encodeTaggedChar(getTag(descriptor, index), value);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void encodeStringElement(SerialDescriptor descriptor, int index, String value) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(value, "value");
        encodeTaggedString(getTag(descriptor, index), value);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final Encoder encodeInlineElement(SerialDescriptor descriptor, int index) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return encodeTaggedInline(getTag(descriptor, index), descriptor.getElementDescriptor(index));
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public <T> void encodeSerializableElement(SerialDescriptor descriptor, int index, SerializationStrategy<? super T> serializer, T value) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        if (encodeElement(descriptor, index)) {
            encodeSerializableValue(serializer, value);
        }
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public <T> void encodeNullableSerializableElement(SerialDescriptor descriptor, int index, SerializationStrategy<? super T> serializer, T value) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        if (encodeElement(descriptor, index)) {
            encodeNullableSerializableValue(serializer, value);
        }
    }

    protected final Tag getCurrentTag() {
        return (Tag) CollectionsKt.last((List) this.tagStack);
    }

    protected final Tag getCurrentTagOrNull() {
        return (Tag) CollectionsKt.lastOrNull((List) this.tagStack);
    }

    protected final void pushTag(Tag name) {
        this.tagStack.add(name);
    }

    @IgnorableReturnValue
    protected final Tag popTag() {
        if (!this.tagStack.isEmpty()) {
            ArrayList<Tag> arrayList = this.tagStack;
            return arrayList.remove(CollectionsKt.getLastIndex(arrayList));
        }
        throw new SerializationException("No tag in stack for requested element");
    }
}
