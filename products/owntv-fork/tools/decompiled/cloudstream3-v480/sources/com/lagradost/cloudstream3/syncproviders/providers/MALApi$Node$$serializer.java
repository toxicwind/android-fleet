package com.lagradost.cloudstream3.syncproviders.providers;

import androidx.core.app.NotificationCompat;
import java.util.List;
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
import kotlinx.serialization.internal.DoubleSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.StringSerializer;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: MALApi.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0019\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006H\u0086\u0080\u0004¢\u0006\u0002\u0010\bJ\u0012\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0086\u0080\u0004J\u001a\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0086\u0080\u0004R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"com/lagradost/cloudstream3/syncproviders/providers/MALApi.Node.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Node;", "<init>", "()V", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", ES6Iterator.VALUE_PROPERTY, "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly")
public final /* synthetic */ class MALApi$Node$$serializer implements GeneratedSerializer<MALApi.Node> {
    public static final MALApi$Node$$serializer INSTANCE;
    private static final SerialDescriptor descriptor;

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        MALApi$Node$$serializer mALApi$Node$$serializer = new MALApi$Node$$serializer();
        INSTANCE = mALApi$Node$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.lagradost.cloudstream3.syncproviders.providers.MALApi.Node", mALApi$Node$$serializer, 23);
        pluginGeneratedSerialDescriptor.addElement("id", false);
        pluginGeneratedSerialDescriptor.addElement("title", false);
        pluginGeneratedSerialDescriptor.addElement("main_picture", false);
        pluginGeneratedSerialDescriptor.addElement("alternative_titles", false);
        pluginGeneratedSerialDescriptor.addElement("media_type", false);
        pluginGeneratedSerialDescriptor.addElement("num_episodes", false);
        pluginGeneratedSerialDescriptor.addElement(NotificationCompat.CATEGORY_STATUS, false);
        pluginGeneratedSerialDescriptor.addElement("start_date", false);
        pluginGeneratedSerialDescriptor.addElement("end_date", false);
        pluginGeneratedSerialDescriptor.addElement("average_episode_duration", false);
        pluginGeneratedSerialDescriptor.addElement("synopsis", false);
        pluginGeneratedSerialDescriptor.addElement("mean", false);
        pluginGeneratedSerialDescriptor.addElement("genres", false);
        pluginGeneratedSerialDescriptor.addElement("rank", false);
        pluginGeneratedSerialDescriptor.addElement("popularity", false);
        pluginGeneratedSerialDescriptor.addElement("num_list_users", false);
        pluginGeneratedSerialDescriptor.addElement("num_favorites", false);
        pluginGeneratedSerialDescriptor.addElement("num_scoring_users", false);
        pluginGeneratedSerialDescriptor.addElement("start_season", false);
        pluginGeneratedSerialDescriptor.addElement("broadcast", false);
        pluginGeneratedSerialDescriptor.addElement("nsfw", false);
        pluginGeneratedSerialDescriptor.addElement("created_at", false);
        pluginGeneratedSerialDescriptor.addElement("updated_at", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private MALApi$Node$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public final KSerializer<?>[] childSerializers() {
        return new KSerializer[]{IntSerializer.INSTANCE, StringSerializer.INSTANCE, BuiltinSerializersKt.getNullable(MALApi$MainPicture$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(MALApi$AlternativeTitles$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(DoubleSerializer.INSTANCE), BuiltinSerializersKt.getNullable((KSerializer) MALApi.Node.$childSerializers[12].getValue()), BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE), BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE), BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE), BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE), BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE), BuiltinSerializersKt.getNullable(MALApi$StartSeason$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(MALApi$Broadcast$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE)};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public final MALApi.Node deserialize(Decoder decoder) {
        Integer num;
        Double d;
        Integer num2;
        Integer num3;
        int i;
        List list;
        MALApi.AlternativeTitles alternativeTitles;
        String str;
        String str2;
        String str3;
        Integer num4;
        String str4;
        Integer num5;
        String str5;
        int i2;
        MALApi.MainPicture mainPicture;
        String str6;
        Integer num6;
        String str7;
        String str8;
        String str9;
        Integer num7;
        MALApi.StartSeason startSeason;
        MALApi.Broadcast broadcast;
        int i3;
        String str10;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        Lazy[] lazyArr = MALApi.Node.$childSerializers;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            int iDecodeIntElement = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 0);
            String strDecodeStringElement = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 1);
            MALApi.MainPicture mainPicture2 = (MALApi.MainPicture) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 2, MALApi$MainPicture$$serializer.INSTANCE, null);
            MALApi.AlternativeTitles alternativeTitles2 = (MALApi.AlternativeTitles) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 3, MALApi$AlternativeTitles$$serializer.INSTANCE, null);
            String str11 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 4, StringSerializer.INSTANCE, null);
            Integer num8 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 5, IntSerializer.INSTANCE, null);
            String str12 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 6, StringSerializer.INSTANCE, null);
            String str13 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 7, StringSerializer.INSTANCE, null);
            String str14 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 8, StringSerializer.INSTANCE, null);
            Integer num9 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 9, IntSerializer.INSTANCE, null);
            String str15 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 10, StringSerializer.INSTANCE, null);
            Double d2 = (Double) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 11, DoubleSerializer.INSTANCE, null);
            list = (List) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 12, (DeserializationStrategy) lazyArr[12].getValue(), null);
            Integer num10 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 13, IntSerializer.INSTANCE, null);
            Integer num11 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 14, IntSerializer.INSTANCE, null);
            Integer num12 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 15, IntSerializer.INSTANCE, null);
            Integer num13 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 16, IntSerializer.INSTANCE, null);
            Integer num14 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 17, IntSerializer.INSTANCE, null);
            MALApi.StartSeason startSeason2 = (MALApi.StartSeason) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 18, MALApi$StartSeason$$serializer.INSTANCE, null);
            MALApi.Broadcast broadcast2 = (MALApi.Broadcast) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 19, MALApi$Broadcast$$serializer.INSTANCE, null);
            String str16 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 20, StringSerializer.INSTANCE, null);
            String str17 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 21, StringSerializer.INSTANCE, null);
            str5 = strDecodeStringElement;
            i = 8388607;
            num = num10;
            d = d2;
            str6 = str16;
            broadcast = broadcast2;
            str7 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 22, StringSerializer.INSTANCE, null);
            num7 = num8;
            startSeason = startSeason2;
            num6 = num14;
            num3 = num13;
            num2 = num12;
            str8 = str17;
            str3 = str14;
            str = str12;
            num4 = num9;
            str2 = str13;
            num5 = num11;
            i2 = iDecodeIntElement;
            alternativeTitles = alternativeTitles2;
            str9 = str11;
            str4 = str15;
            mainPicture = mainPicture2;
        } else {
            int iDecodeIntElement2 = 0;
            boolean z = true;
            Integer num15 = null;
            Integer num16 = null;
            String str18 = null;
            MALApi.StartSeason startSeason3 = null;
            MALApi.Broadcast broadcast3 = null;
            String str19 = null;
            Integer num17 = null;
            Integer num18 = null;
            List list2 = null;
            Double d3 = null;
            String str20 = null;
            String str21 = null;
            String strDecodeStringElement2 = null;
            MALApi.MainPicture mainPicture3 = null;
            MALApi.AlternativeTitles alternativeTitles3 = null;
            String str22 = null;
            Integer num19 = null;
            String str23 = null;
            String str24 = null;
            String str25 = null;
            Integer num20 = null;
            int i4 = 0;
            Integer num21 = null;
            while (z) {
                String str26 = str19;
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(serialDescriptor);
                switch (iDecodeElementIndex) {
                    case -1:
                        str10 = str26;
                        z = false;
                        d3 = d3;
                        num15 = num15;
                        str19 = str10;
                        num16 = num16;
                        break;
                    case 0:
                        str10 = str26;
                        iDecodeIntElement2 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 0);
                        i4 |= 1;
                        d3 = d3;
                        num15 = num15;
                        str19 = str10;
                        num16 = num16;
                        break;
                    case 1:
                        str10 = str26;
                        strDecodeStringElement2 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 1);
                        i4 |= 2;
                        d3 = d3;
                        num15 = num15;
                        str19 = str10;
                        num16 = num16;
                        break;
                    case 2:
                        num16 = num16;
                        str10 = str26;
                        mainPicture3 = (MALApi.MainPicture) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 2, MALApi$MainPicture$$serializer.INSTANCE, mainPicture3);
                        i4 |= 4;
                        d3 = d3;
                        num15 = num15;
                        alternativeTitles3 = alternativeTitles3;
                        str19 = str10;
                        num16 = num16;
                        break;
                    case 3:
                        num16 = num16;
                        str10 = str26;
                        alternativeTitles3 = (MALApi.AlternativeTitles) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 3, MALApi$AlternativeTitles$$serializer.INSTANCE, alternativeTitles3);
                        i4 |= 8;
                        d3 = d3;
                        num15 = num15;
                        str22 = str22;
                        str19 = str10;
                        num16 = num16;
                        break;
                    case 4:
                        num16 = num16;
                        str10 = str26;
                        str22 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 4, StringSerializer.INSTANCE, str22);
                        i4 |= 16;
                        d3 = d3;
                        num15 = num15;
                        num19 = num19;
                        str19 = str10;
                        num16 = num16;
                        break;
                    case 5:
                        num16 = num16;
                        str10 = str26;
                        num19 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 5, IntSerializer.INSTANCE, num19);
                        i4 |= 32;
                        d3 = d3;
                        num15 = num15;
                        str23 = str23;
                        str19 = str10;
                        num16 = num16;
                        break;
                    case 6:
                        num16 = num16;
                        str10 = str26;
                        str23 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 6, StringSerializer.INSTANCE, str23);
                        i4 |= 64;
                        d3 = d3;
                        num15 = num15;
                        str24 = str24;
                        str19 = str10;
                        num16 = num16;
                        break;
                    case 7:
                        num16 = num16;
                        str10 = str26;
                        str24 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 7, StringSerializer.INSTANCE, str24);
                        i4 |= 128;
                        d3 = d3;
                        num15 = num15;
                        str25 = str25;
                        str19 = str10;
                        num16 = num16;
                        break;
                    case 8:
                        num16 = num16;
                        str10 = str26;
                        str25 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 8, StringSerializer.INSTANCE, str25);
                        i4 |= 256;
                        d3 = d3;
                        num15 = num15;
                        num20 = num20;
                        str19 = str10;
                        num16 = num16;
                        break;
                    case 9:
                        str10 = str26;
                        num20 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 9, IntSerializer.INSTANCE, num20);
                        i4 |= 512;
                        d3 = d3;
                        num15 = num15;
                        str19 = str10;
                        num16 = num16;
                        break;
                    case 10:
                        i4 |= 1024;
                        d3 = d3;
                        num16 = num16;
                        str19 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 10, StringSerializer.INSTANCE, str26);
                        num15 = num15;
                        break;
                    case 11:
                        d3 = (Double) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 11, DoubleSerializer.INSTANCE, d3);
                        i4 |= 2048;
                        num15 = num15;
                        str19 = str26;
                        break;
                    case 12:
                        d3 = d3;
                        list2 = (List) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 12, (DeserializationStrategy) lazyArr[12].getValue(), list2);
                        i4 |= 4096;
                        str19 = str26;
                        d3 = d3;
                        break;
                    case 13:
                        d3 = d3;
                        num15 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 13, IntSerializer.INSTANCE, num15);
                        i4 |= 8192;
                        str19 = str26;
                        d3 = d3;
                        break;
                    case 14:
                        d3 = d3;
                        num16 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 14, IntSerializer.INSTANCE, num16);
                        i4 |= 16384;
                        str19 = str26;
                        d3 = d3;
                        break;
                    case 15:
                        num21 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 15, IntSerializer.INSTANCE, num21);
                        i3 = 32768;
                        i4 |= i3;
                        str19 = str26;
                        d3 = d3;
                        break;
                    case 16:
                        num18 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 16, IntSerializer.INSTANCE, num18);
                        i3 = 65536;
                        i4 |= i3;
                        str19 = str26;
                        d3 = d3;
                        break;
                    case 17:
                        num17 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 17, IntSerializer.INSTANCE, num17);
                        i3 = 131072;
                        i4 |= i3;
                        str19 = str26;
                        d3 = d3;
                        break;
                    case 18:
                        startSeason3 = (MALApi.StartSeason) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 18, MALApi$StartSeason$$serializer.INSTANCE, startSeason3);
                        i3 = 262144;
                        i4 |= i3;
                        str19 = str26;
                        d3 = d3;
                        break;
                    case 19:
                        broadcast3 = (MALApi.Broadcast) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 19, MALApi$Broadcast$$serializer.INSTANCE, broadcast3);
                        i3 = 524288;
                        i4 |= i3;
                        str19 = str26;
                        d3 = d3;
                        break;
                    case 20:
                        str18 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 20, StringSerializer.INSTANCE, str18);
                        i3 = 1048576;
                        i4 |= i3;
                        str19 = str26;
                        d3 = d3;
                        break;
                    case 21:
                        str21 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 21, StringSerializer.INSTANCE, str21);
                        i3 = 2097152;
                        i4 |= i3;
                        str19 = str26;
                        d3 = d3;
                        break;
                    case 22:
                        str20 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 22, StringSerializer.INSTANCE, str20);
                        i3 = 4194304;
                        i4 |= i3;
                        str19 = str26;
                        d3 = d3;
                        break;
                    default:
                        throw new UnknownFieldException(iDecodeElementIndex);
                }
            }
            num = num15;
            d = d3;
            num2 = num21;
            num3 = num18;
            i = i4;
            list = list2;
            alternativeTitles = alternativeTitles3;
            str = str23;
            str2 = str24;
            str3 = str25;
            num4 = num20;
            str4 = str19;
            num5 = num16;
            str5 = strDecodeStringElement2;
            i2 = iDecodeIntElement2;
            mainPicture = mainPicture3;
            str6 = str18;
            num6 = num17;
            str7 = str20;
            str8 = str21;
            str9 = str22;
            num7 = num19;
            startSeason = startSeason3;
            broadcast = broadcast3;
        }
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return new MALApi.Node(i, i2, str5, mainPicture, alternativeTitles, str9, num7, str, str2, str3, num4, str4, d, list, num, num5, num2, num3, num6, startSeason, broadcast, str6, str8, str7, null);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(Encoder encoder, MALApi.Node value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        MALApi.Node.write$Self$app(value, compositeEncoderBeginStructure, serialDescriptor);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public /* bridge */ KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.CC.$default$typeParametersSerializers(this);
    }
}
