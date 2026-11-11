package com.lagradost.cloudstream3.syncproviders.providers;

import com.google.android.gms.cast.MediaTrack;
import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: SubSource.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0019\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006H\u0086\u0080\u0004¢\u0006\u0002\u0010\bJ\u0012\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0086\u0080\u0004J\u001a\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0086\u0080\u0004R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"com/lagradost/cloudstream3/syncproviders/providers/SubSourceApi.Subtitle.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/SubSourceApi$Subtitle;", "<init>", "()V", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", ES6Iterator.VALUE_PROPERTY, "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly")
public final /* synthetic */ class SubSourceApi$Subtitle$$serializer implements GeneratedSerializer<SubSourceApi.Subtitle> {
    public static final SubSourceApi$Subtitle$$serializer INSTANCE;
    private static final SerialDescriptor descriptor;

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        SubSourceApi$Subtitle$$serializer subSourceApi$Subtitle$$serializer = new SubSourceApi$Subtitle$$serializer();
        INSTANCE = subSourceApi$Subtitle$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.lagradost.cloudstream3.syncproviders.providers.SubSourceApi.Subtitle", subSourceApi$Subtitle$$serializer, 20);
        pluginGeneratedSerialDescriptor.addElement("id", true);
        pluginGeneratedSerialDescriptor.addElement("uploaded_at", true);
        pluginGeneratedSerialDescriptor.addElement("language", true);
        pluginGeneratedSerialDescriptor.addElement("rating", true);
        pluginGeneratedSerialDescriptor.addElement("uploaded_by", true);
        pluginGeneratedSerialDescriptor.addElement("release_info", true);
        pluginGeneratedSerialDescriptor.addElement(MediaTrack.ROLE_COMMENTARY, true);
        pluginGeneratedSerialDescriptor.addElement("files", true);
        pluginGeneratedSerialDescriptor.addElement(ContentDisposition.Parameters.Size, true);
        pluginGeneratedSerialDescriptor.addElement("downloads", true);
        pluginGeneratedSerialDescriptor.addElement("comments", true);
        pluginGeneratedSerialDescriptor.addElement("production_type", true);
        pluginGeneratedSerialDescriptor.addElement("release_type", true);
        pluginGeneratedSerialDescriptor.addElement("episode", true);
        pluginGeneratedSerialDescriptor.addElement("hearing_impaired", true);
        pluginGeneratedSerialDescriptor.addElement("foreign_parts", true);
        pluginGeneratedSerialDescriptor.addElement("framerate", true);
        pluginGeneratedSerialDescriptor.addElement("preview", true);
        pluginGeneratedSerialDescriptor.addElement("user_uploaded", true);
        pluginGeneratedSerialDescriptor.addElement("download_token", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private SubSourceApi$Subtitle$$serializer() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public final KSerializer<?>[] childSerializers() {
        return new KSerializer[]{BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE), SubSourceApi.Subtitle.$childSerializers[5].getValue(), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE), BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(BooleanSerializer.INSTANCE), StringSerializer.INSTANCE};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public final SubSourceApi.Subtitle deserialize(Decoder decoder) {
        String str;
        String str2;
        String str3;
        Integer num;
        String str4;
        String strDecodeStringElement;
        String str5;
        String str6;
        String str7;
        String str8;
        Integer num2;
        Boolean bool;
        String str9;
        String str10;
        Integer num3;
        String str11;
        Integer num4;
        String str12;
        ArrayList arrayList;
        Integer num5;
        int i;
        int i2;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        Lazy[] lazyArr = SubSourceApi.Subtitle.$childSerializers;
        Integer num6 = null;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            Integer num7 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 0, IntSerializer.INSTANCE, null);
            String str13 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 1, StringSerializer.INSTANCE, null);
            str8 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 2, StringSerializer.INSTANCE, null);
            String str14 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 3, StringSerializer.INSTANCE, null);
            Integer num8 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 4, IntSerializer.INSTANCE, null);
            ArrayList arrayList2 = (ArrayList) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 5, (DeserializationStrategy) lazyArr[5].getValue(), null);
            String str15 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 6, StringSerializer.INSTANCE, null);
            String str16 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 7, StringSerializer.INSTANCE, null);
            String str17 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 8, StringSerializer.INSTANCE, null);
            Integer num9 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 9, IntSerializer.INSTANCE, null);
            Integer num10 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 10, IntSerializer.INSTANCE, null);
            String str18 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 11, StringSerializer.INSTANCE, null);
            String str19 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 12, StringSerializer.INSTANCE, null);
            String str20 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 13, StringSerializer.INSTANCE, null);
            num = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 14, IntSerializer.INSTANCE, null);
            String str21 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 15, StringSerializer.INSTANCE, null);
            String str22 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 16, StringSerializer.INSTANCE, null);
            String str23 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 17, StringSerializer.INSTANCE, null);
            str = str22;
            str5 = str19;
            str3 = str21;
            str2 = str20;
            bool = (Boolean) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 18, BooleanSerializer.INSTANCE, null);
            strDecodeStringElement = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 19);
            str6 = str15;
            str7 = str14;
            i = 1048575;
            num3 = num10;
            str10 = str16;
            arrayList = arrayList2;
            str4 = str23;
            str9 = str18;
            num4 = num9;
            str11 = str17;
            str12 = str13;
            num2 = num8;
            num5 = num7;
        } else {
            int i3 = 19;
            int i4 = 5;
            boolean z = true;
            int i5 = 0;
            Integer num11 = null;
            ArrayList arrayList3 = null;
            Integer num12 = null;
            String str24 = null;
            String str25 = null;
            String str26 = null;
            String str27 = null;
            String str28 = null;
            Integer num13 = null;
            String str29 = null;
            String str30 = null;
            String str31 = null;
            String str32 = null;
            Integer num14 = null;
            String str33 = null;
            String str34 = null;
            String str35 = null;
            Boolean bool2 = null;
            String strDecodeStringElement2 = null;
            while (z) {
                lazyArr = lazyArr;
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(serialDescriptor);
                switch (iDecodeElementIndex) {
                    case -1:
                        z = false;
                        lazyArr = lazyArr;
                        i4 = 5;
                        num6 = num6;
                        num11 = num11;
                        break;
                    case 0:
                        i5 |= 1;
                        num11 = num11;
                        i4 = 5;
                        num6 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 0, IntSerializer.INSTANCE, num6);
                        i3 = 19;
                        break;
                    case 1:
                        str27 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 1, StringSerializer.INSTANCE, str27);
                        bool2 = bool2;
                        num11 = num11;
                        num6 = num6;
                        i3 = 19;
                        i4 = 5;
                        i5 |= 2;
                        lazyArr = lazyArr;
                        break;
                    case 2:
                        str26 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 2, StringSerializer.INSTANCE, str26);
                        i2 = i5 | 4;
                        i3 = 19;
                        i4 = 5;
                        i5 = i2;
                        bool2 = bool2;
                        lazyArr = lazyArr;
                        break;
                    case 3:
                        str24 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 3, StringSerializer.INSTANCE, str24);
                        i2 = i5 | 8;
                        i3 = 19;
                        i4 = 5;
                        i5 = i2;
                        bool2 = bool2;
                        lazyArr = lazyArr;
                        break;
                    case 4:
                        num11 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 4, IntSerializer.INSTANCE, num11);
                        i2 = i5 | 16;
                        i3 = 19;
                        i4 = 5;
                        i5 = i2;
                        bool2 = bool2;
                        lazyArr = lazyArr;
                        break;
                    case 5:
                        bool2 = bool2;
                        arrayList3 = (ArrayList) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, i4, (DeserializationStrategy) lazyArr[i4].getValue(), arrayList3);
                        i2 = i5 | 32;
                        i3 = 19;
                        i5 = i2;
                        bool2 = bool2;
                        lazyArr = lazyArr;
                        break;
                    case 6:
                        bool2 = bool2;
                        str25 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 6, StringSerializer.INSTANCE, str25);
                        i2 = i5 | 64;
                        i3 = 19;
                        i5 = i2;
                        bool2 = bool2;
                        lazyArr = lazyArr;
                        break;
                    case 7:
                        bool2 = bool2;
                        str30 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 7, StringSerializer.INSTANCE, str30);
                        i2 = i5 | 128;
                        i3 = 19;
                        i5 = i2;
                        bool2 = bool2;
                        lazyArr = lazyArr;
                        break;
                    case 8:
                        bool2 = bool2;
                        str29 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 8, StringSerializer.INSTANCE, str29);
                        i2 = i5 | 256;
                        i3 = 19;
                        i5 = i2;
                        bool2 = bool2;
                        lazyArr = lazyArr;
                        break;
                    case 9:
                        bool2 = bool2;
                        num12 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 9, IntSerializer.INSTANCE, num12);
                        i2 = i5 | 512;
                        i3 = 19;
                        i5 = i2;
                        bool2 = bool2;
                        lazyArr = lazyArr;
                        break;
                    case 10:
                        bool2 = bool2;
                        num13 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 10, IntSerializer.INSTANCE, num13);
                        i2 = i5 | 1024;
                        i3 = 19;
                        i5 = i2;
                        bool2 = bool2;
                        lazyArr = lazyArr;
                        break;
                    case 11:
                        bool2 = bool2;
                        str28 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 11, StringSerializer.INSTANCE, str28);
                        i2 = i5 | 2048;
                        i3 = 19;
                        i5 = i2;
                        bool2 = bool2;
                        lazyArr = lazyArr;
                        break;
                    case 12:
                        bool2 = bool2;
                        str31 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 12, StringSerializer.INSTANCE, str31);
                        i2 = i5 | 4096;
                        str32 = str32;
                        i3 = 19;
                        i5 = i2;
                        bool2 = bool2;
                        lazyArr = lazyArr;
                        break;
                    case 13:
                        bool2 = bool2;
                        str32 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 13, StringSerializer.INSTANCE, str32);
                        i2 = i5 | 8192;
                        num14 = num14;
                        i3 = 19;
                        i5 = i2;
                        bool2 = bool2;
                        lazyArr = lazyArr;
                        break;
                    case 14:
                        bool2 = bool2;
                        num14 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 14, IntSerializer.INSTANCE, num14);
                        i2 = i5 | 16384;
                        str33 = str33;
                        i3 = 19;
                        i5 = i2;
                        bool2 = bool2;
                        lazyArr = lazyArr;
                        break;
                    case 15:
                        bool2 = bool2;
                        str33 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 15, StringSerializer.INSTANCE, str33);
                        i2 = i5 | 32768;
                        str34 = str34;
                        i3 = 19;
                        i5 = i2;
                        bool2 = bool2;
                        lazyArr = lazyArr;
                        break;
                    case 16:
                        bool2 = bool2;
                        str34 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 16, StringSerializer.INSTANCE, str34);
                        i2 = i5 | 65536;
                        str35 = str35;
                        i3 = 19;
                        i5 = i2;
                        bool2 = bool2;
                        lazyArr = lazyArr;
                        break;
                    case 17:
                        bool2 = bool2;
                        str35 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 17, StringSerializer.INSTANCE, str35);
                        i2 = i5 | 131072;
                        i3 = 19;
                        i5 = i2;
                        bool2 = bool2;
                        lazyArr = lazyArr;
                        break;
                    case 18:
                        int i6 = i5;
                        str27 = str27;
                        num6 = num6;
                        i5 = i6 | 262144;
                        bool2 = (Boolean) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 18, BooleanSerializer.INSTANCE, bool2);
                        i3 = 19;
                        break;
                    case 19:
                        strDecodeStringElement2 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, i3);
                        i5 |= 524288;
                        lazyArr = lazyArr;
                        break;
                    default:
                        throw new UnknownFieldException(iDecodeElementIndex);
                }
            }
            Integer num15 = num11;
            Integer num16 = num6;
            str = str34;
            str2 = str32;
            str3 = str33;
            num = num14;
            str4 = str35;
            strDecodeStringElement = strDecodeStringElement2;
            str5 = str31;
            str6 = str25;
            str7 = str24;
            str8 = str26;
            num2 = num15;
            bool = bool2;
            str9 = str28;
            str10 = str30;
            num3 = num13;
            str11 = str29;
            num4 = num12;
            str12 = str27;
            arrayList = arrayList3;
            num5 = num16;
            i = i5;
        }
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return new SubSourceApi.Subtitle(i, num5, str12, str8, str7, num2, arrayList, str6, str10, str11, num4, num3, str9, str5, str2, num, str3, str, str4, bool, strDecodeStringElement, (SerializationConstructorMarker) null);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(Encoder encoder, SubSourceApi.Subtitle value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        SubSourceApi.Subtitle.write$Self$app(value, compositeEncoderBeginStructure, serialDescriptor);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public /* bridge */ KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.CC.$default$typeParametersSerializers(this);
    }
}
