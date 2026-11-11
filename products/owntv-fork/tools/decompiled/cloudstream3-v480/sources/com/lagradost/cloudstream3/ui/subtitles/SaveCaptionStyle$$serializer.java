package com.lagradost.cloudstream3.ui.subtitles;

import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.FloatSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: SubtitlesFragment.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0019\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006H\u0086\u0080\u0004¢\u0006\u0002\u0010\bJ\u0012\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0086\u0080\u0004J\u001a\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0086\u0080\u0004R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"com/lagradost/cloudstream3/ui/subtitles/SaveCaptionStyle.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/lagradost/cloudstream3/ui/subtitles/SaveCaptionStyle;", "<init>", "()V", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", ES6Iterator.VALUE_PROPERTY, "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly")
public final /* synthetic */ class SaveCaptionStyle$$serializer implements GeneratedSerializer<SaveCaptionStyle> {
    public static final SaveCaptionStyle$$serializer INSTANCE;
    private static final SerialDescriptor descriptor;

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        SaveCaptionStyle$$serializer saveCaptionStyle$$serializer = new SaveCaptionStyle$$serializer();
        INSTANCE = saveCaptionStyle$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.lagradost.cloudstream3.ui.subtitles.SaveCaptionStyle", saveCaptionStyle$$serializer, 17);
        pluginGeneratedSerialDescriptor.addElement("foregroundColor", false);
        pluginGeneratedSerialDescriptor.addElement(TtmlNode.ATTR_TTS_BACKGROUND_COLOR, false);
        pluginGeneratedSerialDescriptor.addElement("windowColor", false);
        pluginGeneratedSerialDescriptor.addElement("edgeType", false);
        pluginGeneratedSerialDescriptor.addElement("edgeColor", false);
        pluginGeneratedSerialDescriptor.addElement("typeface", false);
        pluginGeneratedSerialDescriptor.addElement("typefaceFilePath", false);
        pluginGeneratedSerialDescriptor.addElement("elevation", false);
        pluginGeneratedSerialDescriptor.addElement("fixedTextSize", false);
        pluginGeneratedSerialDescriptor.addElement("edgeSize", true);
        pluginGeneratedSerialDescriptor.addElement("removeCaptions", true);
        pluginGeneratedSerialDescriptor.addElement("removeBloat", true);
        pluginGeneratedSerialDescriptor.addElement("upperCase", true);
        pluginGeneratedSerialDescriptor.addElement(TtmlNode.BOLD, true);
        pluginGeneratedSerialDescriptor.addElement(TtmlNode.ITALIC, true);
        pluginGeneratedSerialDescriptor.addElement("backgroundRadius", true);
        pluginGeneratedSerialDescriptor.addElement("alignment", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private SaveCaptionStyle$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public final KSerializer<?>[] childSerializers() {
        return new KSerializer[]{IntSerializer.INSTANCE, IntSerializer.INSTANCE, IntSerializer.INSTANCE, IntSerializer.INSTANCE, IntSerializer.INSTANCE, BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), IntSerializer.INSTANCE, BuiltinSerializersKt.getNullable(FloatSerializer.INSTANCE), BuiltinSerializersKt.getNullable(FloatSerializer.INSTANCE), BooleanSerializer.INSTANCE, BooleanSerializer.INSTANCE, BooleanSerializer.INSTANCE, BooleanSerializer.INSTANCE, BooleanSerializer.INSTANCE, BuiltinSerializersKt.getNullable(FloatSerializer.INSTANCE), BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE)};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public final SaveCaptionStyle deserialize(Decoder decoder) {
        Integer num;
        Integer num2;
        Float f;
        int i;
        int i2;
        boolean z;
        boolean z2;
        boolean z3;
        int i3;
        int i4;
        boolean z4;
        int i5;
        Float f2;
        boolean z5;
        int i6;
        String str;
        Float f3;
        int i7;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        int i8 = 11;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            int iDecodeIntElement = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 0);
            int iDecodeIntElement2 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 1);
            int iDecodeIntElement3 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 2);
            int iDecodeIntElement4 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 3);
            int iDecodeIntElement5 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 4);
            Integer num3 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 5, IntSerializer.INSTANCE, null);
            String str2 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 6, StringSerializer.INSTANCE, null);
            int iDecodeIntElement6 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 7);
            Float f4 = (Float) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 8, FloatSerializer.INSTANCE, null);
            Float f5 = (Float) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 9, FloatSerializer.INSTANCE, null);
            boolean zDecodeBooleanElement = compositeDecoderBeginStructure.decodeBooleanElement(serialDescriptor, 10);
            boolean zDecodeBooleanElement2 = compositeDecoderBeginStructure.decodeBooleanElement(serialDescriptor, 11);
            boolean zDecodeBooleanElement3 = compositeDecoderBeginStructure.decodeBooleanElement(serialDescriptor, 12);
            boolean zDecodeBooleanElement4 = compositeDecoderBeginStructure.decodeBooleanElement(serialDescriptor, 13);
            boolean zDecodeBooleanElement5 = compositeDecoderBeginStructure.decodeBooleanElement(serialDescriptor, 14);
            i4 = iDecodeIntElement5;
            i7 = iDecodeIntElement;
            f = (Float) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 15, FloatSerializer.INSTANCE, null);
            num2 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 16, IntSerializer.INSTANCE, null);
            z = zDecodeBooleanElement4;
            z2 = zDecodeBooleanElement2;
            z3 = zDecodeBooleanElement;
            f2 = f5;
            i3 = iDecodeIntElement6;
            f3 = f4;
            z4 = zDecodeBooleanElement3;
            i = iDecodeIntElement3;
            z5 = zDecodeBooleanElement5;
            i6 = iDecodeIntElement2;
            i2 = iDecodeIntElement4;
            str = str2;
            num = num3;
            i5 = 131071;
        } else {
            int i9 = 0;
            int i10 = 16;
            boolean z6 = true;
            Integer num4 = null;
            String str3 = null;
            Integer num5 = null;
            Float f6 = null;
            Float f7 = null;
            Float f8 = null;
            int iDecodeIntElement7 = 0;
            boolean zDecodeBooleanElement6 = false;
            int iDecodeIntElement8 = 0;
            int iDecodeIntElement9 = 0;
            int iDecodeIntElement10 = 0;
            boolean zDecodeBooleanElement7 = false;
            boolean zDecodeBooleanElement8 = false;
            boolean zDecodeBooleanElement9 = false;
            int iDecodeIntElement11 = 0;
            int iDecodeIntElement12 = 0;
            boolean zDecodeBooleanElement10 = false;
            while (z6) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(serialDescriptor);
                switch (iDecodeElementIndex) {
                    case -1:
                        z6 = false;
                        i10 = 16;
                        break;
                    case 0:
                        i9 |= 1;
                        iDecodeIntElement7 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 0);
                        i10 = 16;
                        i8 = 11;
                        break;
                    case 1:
                        iDecodeIntElement9 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 1);
                        i9 |= 2;
                        i10 = 16;
                        i8 = 11;
                        break;
                    case 2:
                        iDecodeIntElement8 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 2);
                        i9 |= 4;
                        i10 = 16;
                        i8 = 11;
                        break;
                    case 3:
                        iDecodeIntElement10 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 3);
                        i9 |= 8;
                        i10 = 16;
                        i8 = 11;
                        break;
                    case 4:
                        iDecodeIntElement12 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 4);
                        i9 |= 16;
                        i10 = 16;
                        i8 = 11;
                        break;
                    case 5:
                        num4 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 5, IntSerializer.INSTANCE, num4);
                        i9 |= 32;
                        i10 = 16;
                        i8 = 11;
                        break;
                    case 6:
                        str3 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 6, StringSerializer.INSTANCE, str3);
                        i9 |= 64;
                        i10 = 16;
                        i8 = 11;
                        break;
                    case 7:
                        iDecodeIntElement11 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 7);
                        i9 |= 128;
                        i10 = 16;
                        break;
                    case 8:
                        f7 = (Float) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 8, FloatSerializer.INSTANCE, f7);
                        i9 |= 256;
                        i10 = 16;
                        break;
                    case 9:
                        f8 = (Float) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 9, FloatSerializer.INSTANCE, f8);
                        i9 |= 512;
                        i10 = 16;
                        break;
                    case 10:
                        zDecodeBooleanElement9 = compositeDecoderBeginStructure.decodeBooleanElement(serialDescriptor, 10);
                        i9 |= 1024;
                        i10 = 16;
                        break;
                    case 11:
                        zDecodeBooleanElement8 = compositeDecoderBeginStructure.decodeBooleanElement(serialDescriptor, i8);
                        i9 |= 2048;
                        i10 = 16;
                        break;
                    case 12:
                        zDecodeBooleanElement10 = compositeDecoderBeginStructure.decodeBooleanElement(serialDescriptor, 12);
                        i9 |= 4096;
                        i10 = 16;
                        break;
                    case 13:
                        zDecodeBooleanElement7 = compositeDecoderBeginStructure.decodeBooleanElement(serialDescriptor, 13);
                        i9 |= 8192;
                        i10 = 16;
                        break;
                    case 14:
                        zDecodeBooleanElement6 = compositeDecoderBeginStructure.decodeBooleanElement(serialDescriptor, 14);
                        i9 |= 16384;
                        i10 = 16;
                        break;
                    case 15:
                        f6 = (Float) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 15, FloatSerializer.INSTANCE, f6);
                        i9 |= 32768;
                        i10 = 16;
                        break;
                    case 16:
                        num5 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, i10, IntSerializer.INSTANCE, num5);
                        i9 |= 65536;
                        break;
                    default:
                        throw new UnknownFieldException(iDecodeElementIndex);
                }
            }
            num = num4;
            num2 = num5;
            f = f6;
            i = iDecodeIntElement8;
            i2 = iDecodeIntElement10;
            z = zDecodeBooleanElement7;
            z2 = zDecodeBooleanElement8;
            z3 = zDecodeBooleanElement9;
            i3 = iDecodeIntElement11;
            i4 = iDecodeIntElement12;
            z4 = zDecodeBooleanElement10;
            i5 = i9;
            f2 = f8;
            z5 = zDecodeBooleanElement6;
            i6 = iDecodeIntElement9;
            str = str3;
            f3 = f7;
            i7 = iDecodeIntElement7;
        }
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return new SaveCaptionStyle(i5, i7, i6, i, i2, i4, num, str, i3, f3, f2, z3, z2, z4, z, z5, f, num2, (SerializationConstructorMarker) null);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(Encoder encoder, SaveCaptionStyle value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        SaveCaptionStyle.write$Self$app(value, compositeEncoderBeginStructure, serialDescriptor);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public /* bridge */ KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.CC.$default$typeParametersSerializers(this);
    }
}
