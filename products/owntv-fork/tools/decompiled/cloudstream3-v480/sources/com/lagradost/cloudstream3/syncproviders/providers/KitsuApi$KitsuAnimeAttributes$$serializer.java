package com.lagradost.cloudstream3.syncproviders.providers;

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
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0019\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006H\u0086\u0080\u0004¢\u0006\u0002\u0010\bJ\u0012\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0086\u0080\u0004J\u001a\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0086\u0080\u0004R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"com/lagradost/cloudstream3/syncproviders/providers/KitsuApi.KitsuAnimeAttributes.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuAnimeAttributes;", "<init>", "()V", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", ES6Iterator.VALUE_PROPERTY, "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly")
public final /* synthetic */ class KitsuApi$KitsuAnimeAttributes$$serializer implements GeneratedSerializer<KitsuApi.KitsuAnimeAttributes> {
    public static final KitsuApi$KitsuAnimeAttributes$$serializer INSTANCE;
    private static final SerialDescriptor descriptor;

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        KitsuApi$KitsuAnimeAttributes$$serializer kitsuApi$KitsuAnimeAttributes$$serializer = new KitsuApi$KitsuAnimeAttributes$$serializer();
        INSTANCE = kitsuApi$KitsuAnimeAttributes$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.lagradost.cloudstream3.syncproviders.providers.KitsuApi.KitsuAnimeAttributes", kitsuApi$KitsuAnimeAttributes$$serializer, 8);
        pluginGeneratedSerialDescriptor.addElement("titles", false);
        pluginGeneratedSerialDescriptor.addElement("canonicalTitle", false);
        pluginGeneratedSerialDescriptor.addElement("posterImage", false);
        pluginGeneratedSerialDescriptor.addElement("synopsis", false);
        pluginGeneratedSerialDescriptor.addElement("startDate", false);
        pluginGeneratedSerialDescriptor.addElement("endDate", false);
        pluginGeneratedSerialDescriptor.addElement("episodeCount", false);
        pluginGeneratedSerialDescriptor.addElement("episodeLength", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private KitsuApi$KitsuAnimeAttributes$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public final KSerializer<?>[] childSerializers() {
        return new KSerializer[]{BuiltinSerializersKt.getNullable(KitsuApi$KitsuTitles$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(KitsuApi$KitsuPosterImage$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE), BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE)};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public final KitsuApi.KitsuAnimeAttributes deserialize(Decoder decoder) {
        Integer num;
        KitsuApi.KitsuTitles kitsuTitles;
        KitsuApi.KitsuPosterImage kitsuPosterImage;
        String str;
        String str2;
        String str3;
        String str4;
        Integer num2;
        int i;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        int i2 = 7;
        KitsuApi.KitsuTitles kitsuTitles2 = null;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            KitsuApi.KitsuTitles kitsuTitles3 = (KitsuApi.KitsuTitles) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 0, KitsuApi$KitsuTitles$$serializer.INSTANCE, null);
            String str5 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 1, StringSerializer.INSTANCE, null);
            KitsuApi.KitsuPosterImage kitsuPosterImage2 = (KitsuApi.KitsuPosterImage) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 2, KitsuApi$KitsuPosterImage$$serializer.INSTANCE, null);
            String str6 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 3, StringSerializer.INSTANCE, null);
            String str7 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 4, StringSerializer.INSTANCE, null);
            String str8 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 5, StringSerializer.INSTANCE, null);
            Integer num3 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 6, IntSerializer.INSTANCE, null);
            num2 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 7, IntSerializer.INSTANCE, null);
            num = num3;
            str2 = str8;
            str4 = str6;
            str = str7;
            kitsuPosterImage = kitsuPosterImage2;
            i = 255;
            str3 = str5;
            kitsuTitles = kitsuTitles3;
        } else {
            boolean z = true;
            int i3 = 0;
            Integer num4 = null;
            Integer num5 = null;
            String str9 = null;
            String str10 = null;
            KitsuApi.KitsuPosterImage kitsuPosterImage3 = null;
            String str11 = null;
            String str12 = null;
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(serialDescriptor);
                switch (iDecodeElementIndex) {
                    case -1:
                        z = false;
                        i2 = 7;
                        break;
                    case 0:
                        kitsuTitles2 = (KitsuApi.KitsuTitles) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 0, KitsuApi$KitsuTitles$$serializer.INSTANCE, kitsuTitles2);
                        i3 |= 1;
                        i2 = 7;
                        break;
                    case 1:
                        str10 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 1, StringSerializer.INSTANCE, str10);
                        i3 |= 2;
                        i2 = 7;
                        break;
                    case 2:
                        kitsuPosterImage3 = (KitsuApi.KitsuPosterImage) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 2, KitsuApi$KitsuPosterImage$$serializer.INSTANCE, kitsuPosterImage3);
                        i3 |= 4;
                        i2 = 7;
                        break;
                    case 3:
                        str11 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 3, StringSerializer.INSTANCE, str11);
                        i3 |= 8;
                        i2 = 7;
                        break;
                    case 4:
                        str12 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 4, StringSerializer.INSTANCE, str12);
                        i3 |= 16;
                        break;
                    case 5:
                        str9 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 5, StringSerializer.INSTANCE, str9);
                        i3 |= 32;
                        break;
                    case 6:
                        num5 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 6, IntSerializer.INSTANCE, num5);
                        i3 |= 64;
                        break;
                    case 7:
                        num4 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, i2, IntSerializer.INSTANCE, num4);
                        i3 |= 128;
                        break;
                    default:
                        throw new UnknownFieldException(iDecodeElementIndex);
                }
            }
            String str13 = str12;
            num = num5;
            kitsuTitles = kitsuTitles2;
            kitsuPosterImage = kitsuPosterImage3;
            str = str13;
            String str14 = str11;
            str2 = str9;
            str3 = str10;
            str4 = str14;
            num2 = num4;
            i = i3;
        }
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return new KitsuApi.KitsuAnimeAttributes(i, kitsuTitles, str3, kitsuPosterImage, str4, str, str2, num, num2, null);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(Encoder encoder, KitsuApi.KitsuAnimeAttributes value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        KitsuApi.KitsuAnimeAttributes.write$Self$app(value, compositeEncoderBeginStructure, serialDescriptor);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public /* bridge */ KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.CC.$default$typeParametersSerializers(this);
    }
}
