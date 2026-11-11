package com.lagradost.cloudstream3.syncproviders.providers;

import androidx.core.app.NotificationCompat;
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
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.StringSerializer;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: KitsuApi.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0019\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006H\u0086\u0080\u0004¢\u0006\u0002\u0010\bJ\u0012\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0086\u0080\u0004J\u001a\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0086\u0080\u0004R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"com/lagradost/cloudstream3/syncproviders/providers/KitsuApi.KitsuNodeAttributes.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuNodeAttributes;", "<init>", "()V", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", ES6Iterator.VALUE_PROPERTY, "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly")
public final /* synthetic */ class KitsuApi$KitsuNodeAttributes$$serializer implements GeneratedSerializer<KitsuApi.KitsuNodeAttributes> {
    public static final KitsuApi$KitsuNodeAttributes$$serializer INSTANCE;
    private static final SerialDescriptor descriptor;

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        KitsuApi$KitsuNodeAttributes$$serializer kitsuApi$KitsuNodeAttributes$$serializer = new KitsuApi$KitsuNodeAttributes$$serializer();
        INSTANCE = kitsuApi$KitsuNodeAttributes$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.lagradost.cloudstream3.syncproviders.providers.KitsuApi.KitsuNodeAttributes", kitsuApi$KitsuNodeAttributes$$serializer, 16);
        pluginGeneratedSerialDescriptor.addElement("titles", false);
        pluginGeneratedSerialDescriptor.addElement("canonicalTitle", false);
        pluginGeneratedSerialDescriptor.addElement("posterImage", false);
        pluginGeneratedSerialDescriptor.addElement("synopsis", false);
        pluginGeneratedSerialDescriptor.addElement("startDate", false);
        pluginGeneratedSerialDescriptor.addElement("endDate", false);
        pluginGeneratedSerialDescriptor.addElement("episodeCount", false);
        pluginGeneratedSerialDescriptor.addElement("episodeLength", false);
        pluginGeneratedSerialDescriptor.addElement("name", false);
        pluginGeneratedSerialDescriptor.addElement("location", false);
        pluginGeneratedSerialDescriptor.addElement("createdAt", false);
        pluginGeneratedSerialDescriptor.addElement("avatar", false);
        pluginGeneratedSerialDescriptor.addElement("progress", false);
        pluginGeneratedSerialDescriptor.addElement("ratingTwenty", false);
        pluginGeneratedSerialDescriptor.addElement("updatedAt", false);
        pluginGeneratedSerialDescriptor.addElement(NotificationCompat.CATEGORY_STATUS, false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private KitsuApi$KitsuNodeAttributes$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public final KSerializer<?>[] childSerializers() {
        return new KSerializer[]{BuiltinSerializersKt.getNullable(KitsuApi$KitsuTitles$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(KitsuApi$KitsuPosterImage$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE), BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(KitsuApi$KitsuUserAvatar$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE), BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE)};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public final KitsuApi.KitsuNodeAttributes deserialize(Decoder decoder) {
        int i;
        String str;
        String str2;
        KitsuApi.KitsuUserAvatar kitsuUserAvatar;
        String str3;
        String str4;
        Integer num;
        String str5;
        KitsuApi.KitsuTitles kitsuTitles;
        Integer num2;
        String str6;
        Integer num3;
        String str7;
        String str8;
        KitsuApi.KitsuPosterImage kitsuPosterImage;
        Integer num4;
        String str9;
        String str10;
        String str11;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            KitsuApi.KitsuTitles kitsuTitles2 = (KitsuApi.KitsuTitles) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 0, KitsuApi$KitsuTitles$$serializer.INSTANCE, null);
            String str12 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 1, StringSerializer.INSTANCE, null);
            KitsuApi.KitsuPosterImage kitsuPosterImage2 = (KitsuApi.KitsuPosterImage) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 2, KitsuApi$KitsuPosterImage$$serializer.INSTANCE, null);
            String str13 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 3, StringSerializer.INSTANCE, null);
            String str14 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 4, StringSerializer.INSTANCE, null);
            String str15 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 5, StringSerializer.INSTANCE, null);
            Integer num5 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 6, IntSerializer.INSTANCE, null);
            Integer num6 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 7, IntSerializer.INSTANCE, null);
            String str16 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 8, StringSerializer.INSTANCE, null);
            String str17 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 9, StringSerializer.INSTANCE, null);
            String str18 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 10, StringSerializer.INSTANCE, null);
            KitsuApi.KitsuUserAvatar kitsuUserAvatar2 = (KitsuApi.KitsuUserAvatar) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 11, KitsuApi$KitsuUserAvatar$$serializer.INSTANCE, null);
            Integer num7 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 12, IntSerializer.INSTANCE, null);
            Integer num8 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 13, IntSerializer.INSTANCE, null);
            i = 65535;
            str = str18;
            kitsuTitles = kitsuTitles2;
            str8 = str15;
            str7 = str14;
            str4 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 14, StringSerializer.INSTANCE, null);
            str5 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 15, StringSerializer.INSTANCE, null);
            kitsuUserAvatar = kitsuUserAvatar2;
            num4 = num6;
            num = num8;
            num2 = num7;
            kitsuPosterImage = kitsuPosterImage2;
            str3 = str17;
            str6 = str16;
            str2 = str12;
            num3 = num5;
            str9 = str13;
        } else {
            boolean z = true;
            String str19 = null;
            KitsuApi.KitsuPosterImage kitsuPosterImage3 = null;
            Integer num9 = null;
            KitsuApi.KitsuUserAvatar kitsuUserAvatar3 = null;
            String str20 = null;
            String str21 = null;
            Integer num10 = null;
            Integer num11 = null;
            String str22 = null;
            String str23 = null;
            String str24 = null;
            KitsuApi.KitsuTitles kitsuTitles3 = null;
            Integer num12 = null;
            String str25 = null;
            String str26 = null;
            i = 0;
            String str27 = null;
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(serialDescriptor);
                switch (iDecodeElementIndex) {
                    case -1:
                        str10 = str26;
                        z = false;
                        str27 = str27;
                        str19 = str19;
                        str25 = str25;
                        str26 = str10;
                        break;
                    case 0:
                        str10 = str26;
                        kitsuTitles3 = (KitsuApi.KitsuTitles) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 0, KitsuApi$KitsuTitles$$serializer.INSTANCE, kitsuTitles3);
                        i |= 1;
                        str27 = str27;
                        str19 = str19;
                        str25 = str25;
                        str26 = str10;
                        break;
                    case 1:
                        str11 = str25;
                        i |= 2;
                        str19 = str19;
                        str26 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 1, StringSerializer.INSTANCE, str26);
                        str27 = str27;
                        str25 = str11;
                        break;
                    case 2:
                        str11 = str25;
                        kitsuPosterImage3 = (KitsuApi.KitsuPosterImage) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 2, KitsuApi$KitsuPosterImage$$serializer.INSTANCE, kitsuPosterImage3);
                        i |= 4;
                        num12 = num12;
                        str27 = str27;
                        str25 = str11;
                        break;
                    case 3:
                        str11 = str25;
                        str19 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 3, StringSerializer.INSTANCE, str19);
                        i |= 8;
                        str27 = str27;
                        kitsuPosterImage3 = kitsuPosterImage3;
                        str25 = str11;
                        break;
                    case 4:
                        str11 = str25;
                        str22 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 4, StringSerializer.INSTANCE, str22);
                        i |= 16;
                        str27 = str27;
                        kitsuPosterImage3 = kitsuPosterImage3;
                        str25 = str11;
                        break;
                    case 5:
                        str11 = str25;
                        str24 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 5, StringSerializer.INSTANCE, str24);
                        i |= 32;
                        str27 = str27;
                        kitsuPosterImage3 = kitsuPosterImage3;
                        str25 = str11;
                        break;
                    case 6:
                        str11 = str25;
                        num11 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 6, IntSerializer.INSTANCE, num11);
                        i |= 64;
                        str27 = str27;
                        kitsuPosterImage3 = kitsuPosterImage3;
                        str25 = str11;
                        break;
                    case 7:
                        str11 = str25;
                        num10 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 7, IntSerializer.INSTANCE, num10);
                        i |= 128;
                        str27 = str27;
                        kitsuPosterImage3 = kitsuPosterImage3;
                        str25 = str11;
                        break;
                    case 8:
                        str11 = str25;
                        str21 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 8, StringSerializer.INSTANCE, str21);
                        i |= 256;
                        str27 = str27;
                        kitsuPosterImage3 = kitsuPosterImage3;
                        str25 = str11;
                        break;
                    case 9:
                        str11 = str25;
                        str23 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 9, StringSerializer.INSTANCE, str23);
                        i |= 512;
                        str27 = str27;
                        kitsuPosterImage3 = kitsuPosterImage3;
                        str25 = str11;
                        break;
                    case 10:
                        str11 = str25;
                        str20 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 10, StringSerializer.INSTANCE, str20);
                        i |= 1024;
                        str27 = str27;
                        kitsuPosterImage3 = kitsuPosterImage3;
                        str25 = str11;
                        break;
                    case 11:
                        str11 = str25;
                        kitsuUserAvatar3 = (KitsuApi.KitsuUserAvatar) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 11, KitsuApi$KitsuUserAvatar$$serializer.INSTANCE, kitsuUserAvatar3);
                        i |= 2048;
                        str27 = str27;
                        kitsuPosterImage3 = kitsuPosterImage3;
                        str25 = str11;
                        break;
                    case 12:
                        str11 = str25;
                        num9 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 12, IntSerializer.INSTANCE, num9);
                        i |= 4096;
                        str27 = str27;
                        kitsuPosterImage3 = kitsuPosterImage3;
                        str25 = str11;
                        break;
                    case 13:
                        str11 = str25;
                        num12 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 13, IntSerializer.INSTANCE, num12);
                        i |= 8192;
                        str27 = str27;
                        kitsuPosterImage3 = kitsuPosterImage3;
                        str25 = str11;
                        break;
                    case 14:
                        i |= 16384;
                        str27 = str27;
                        str25 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 14, StringSerializer.INSTANCE, str25);
                        kitsuPosterImage3 = kitsuPosterImage3;
                        break;
                    case 15:
                        str27 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 15, StringSerializer.INSTANCE, str27);
                        i |= 32768;
                        kitsuPosterImage3 = kitsuPosterImage3;
                        break;
                    default:
                        throw new UnknownFieldException(iDecodeElementIndex);
                }
            }
            String str28 = str26;
            String str29 = str19;
            KitsuApi.KitsuTitles kitsuTitles4 = kitsuTitles3;
            str = str20;
            str2 = str28;
            kitsuUserAvatar = kitsuUserAvatar3;
            str3 = str23;
            str4 = str25;
            num = num12;
            str5 = str27;
            kitsuTitles = kitsuTitles4;
            num2 = num9;
            str6 = str21;
            num3 = num11;
            str7 = str22;
            str8 = str24;
            kitsuPosterImage = kitsuPosterImage3;
            num4 = num10;
            str9 = str29;
        }
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return new KitsuApi.KitsuNodeAttributes(i, kitsuTitles, str2, kitsuPosterImage, str9, str7, str8, num3, num4, str6, str3, str, kitsuUserAvatar, num2, num, str4, str5, null);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(Encoder encoder, KitsuApi.KitsuNodeAttributes value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        KitsuApi.KitsuNodeAttributes.write$Self$app(value, compositeEncoderBeginStructure, serialDescriptor);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public /* bridge */ KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.CC.$default$typeParametersSerializers(this);
    }
}
