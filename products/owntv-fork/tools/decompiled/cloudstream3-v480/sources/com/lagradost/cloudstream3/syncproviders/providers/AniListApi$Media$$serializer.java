package com.lagradost.cloudstream3.syncproviders.providers;

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
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.StringSerializer;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: AniListApi.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0019\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006H\u0086\u0080\u0004¢\u0006\u0002\u0010\bJ\u0012\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0086\u0080\u0004J\u001a\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0086\u0080\u0004R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"com/lagradost/cloudstream3/syncproviders/providers/AniListApi.Media.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Media;", "<init>", "()V", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", ES6Iterator.VALUE_PROPERTY, "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly")
public final /* synthetic */ class AniListApi$Media$$serializer implements GeneratedSerializer<AniListApi.Media> {
    public static final AniListApi$Media$$serializer INSTANCE;
    private static final SerialDescriptor descriptor;

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        AniListApi$Media$$serializer aniListApi$Media$$serializer = new AniListApi$Media$$serializer();
        INSTANCE = aniListApi$Media$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.lagradost.cloudstream3.syncproviders.providers.AniListApi.Media", aniListApi$Media$$serializer, 11);
        pluginGeneratedSerialDescriptor.addElement("id", false);
        pluginGeneratedSerialDescriptor.addElement("idMal", false);
        pluginGeneratedSerialDescriptor.addElement("season", false);
        pluginGeneratedSerialDescriptor.addElement("seasonYear", false);
        pluginGeneratedSerialDescriptor.addElement("format", false);
        pluginGeneratedSerialDescriptor.addElement("episodes", false);
        pluginGeneratedSerialDescriptor.addElement("title", false);
        pluginGeneratedSerialDescriptor.addElement("description", false);
        pluginGeneratedSerialDescriptor.addElement("coverImage", false);
        pluginGeneratedSerialDescriptor.addElement("synonyms", false);
        pluginGeneratedSerialDescriptor.addElement("nextAiringEpisode", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private AniListApi$Media$$serializer() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public final KSerializer<?>[] childSerializers() {
        return new KSerializer[]{IntSerializer.INSTANCE, BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), IntSerializer.INSTANCE, BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), IntSerializer.INSTANCE, AniListApi$Title$$serializer.INSTANCE, BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), AniListApi$CoverImage$$serializer.INSTANCE, AniListApi.Media.$childSerializers[9].getValue(), BuiltinSerializersKt.getNullable(AniListApi$SeasonNextAiringEpisode$$serializer.INSTANCE)};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public final AniListApi.Media deserialize(Decoder decoder) {
        int iDecodeIntElement;
        AniListApi.SeasonNextAiringEpisode seasonNextAiringEpisode;
        List list;
        AniListApi.CoverImage coverImage;
        AniListApi.Title title;
        String str;
        int i;
        int i2;
        String str2;
        int i3;
        String str3;
        Integer num;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        Lazy[] lazyArr = AniListApi.Media.$childSerializers;
        int i4 = 10;
        int i5 = 9;
        int i6 = 0;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            iDecodeIntElement = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 0);
            num = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 1, IntSerializer.INSTANCE, null);
            String str4 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 2, StringSerializer.INSTANCE, null);
            int iDecodeIntElement2 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 3);
            String str5 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 4, StringSerializer.INSTANCE, null);
            int iDecodeIntElement3 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 5);
            AniListApi.Title title2 = (AniListApi.Title) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 6, AniListApi$Title$$serializer.INSTANCE, null);
            String str6 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 7, StringSerializer.INSTANCE, null);
            AniListApi.CoverImage coverImage2 = (AniListApi.CoverImage) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 8, AniListApi$CoverImage$$serializer.INSTANCE, null);
            list = (List) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 9, (DeserializationStrategy) lazyArr[9].getValue(), null);
            seasonNextAiringEpisode = (AniListApi.SeasonNextAiringEpisode) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 10, AniListApi$SeasonNextAiringEpisode$$serializer.INSTANCE, null);
            str = str6;
            title = title2;
            i = iDecodeIntElement3;
            i2 = iDecodeIntElement2;
            coverImage = coverImage2;
            str2 = str5;
            str3 = str4;
            i3 = 2047;
        } else {
            boolean z = true;
            iDecodeIntElement = 0;
            int iDecodeIntElement4 = 0;
            AniListApi.SeasonNextAiringEpisode seasonNextAiringEpisode2 = null;
            List list2 = null;
            AniListApi.CoverImage coverImage3 = null;
            AniListApi.Title title3 = null;
            String str7 = null;
            String str8 = null;
            String str9 = null;
            Integer num2 = null;
            int iDecodeIntElement5 = 0;
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(serialDescriptor);
                switch (iDecodeElementIndex) {
                    case -1:
                        z = false;
                        i4 = 10;
                        break;
                    case 0:
                        iDecodeIntElement = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 0);
                        i6 |= 1;
                        i4 = 10;
                        i5 = 9;
                        break;
                    case 1:
                        i6 |= 2;
                        num2 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 1, IntSerializer.INSTANCE, num2);
                        i4 = 10;
                        i5 = 9;
                        break;
                    case 2:
                        str9 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 2, StringSerializer.INSTANCE, str9);
                        i6 |= 4;
                        i4 = 10;
                        i5 = 9;
                        break;
                    case 3:
                        iDecodeIntElement4 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 3);
                        i6 |= 8;
                        i4 = 10;
                        i5 = 9;
                        break;
                    case 4:
                        str8 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 4, StringSerializer.INSTANCE, str8);
                        i6 |= 16;
                        i4 = 10;
                        i5 = 9;
                        break;
                    case 5:
                        iDecodeIntElement5 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 5);
                        i6 |= 32;
                        i4 = 10;
                        break;
                    case 6:
                        title3 = (AniListApi.Title) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 6, AniListApi$Title$$serializer.INSTANCE, title3);
                        i6 |= 64;
                        i4 = 10;
                        break;
                    case 7:
                        str7 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 7, StringSerializer.INSTANCE, str7);
                        i6 |= 128;
                        i4 = 10;
                        break;
                    case 8:
                        coverImage3 = (AniListApi.CoverImage) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 8, AniListApi$CoverImage$$serializer.INSTANCE, coverImage3);
                        i6 |= 256;
                        i4 = 10;
                        break;
                    case 9:
                        list2 = (List) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, i5, (DeserializationStrategy) lazyArr[i5].getValue(), list2);
                        i6 |= 512;
                        break;
                    case 10:
                        seasonNextAiringEpisode2 = (AniListApi.SeasonNextAiringEpisode) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, i4, AniListApi$SeasonNextAiringEpisode$$serializer.INSTANCE, seasonNextAiringEpisode2);
                        i6 |= 1024;
                        break;
                    default:
                        throw new UnknownFieldException(iDecodeElementIndex);
                }
            }
            Integer num3 = num2;
            seasonNextAiringEpisode = seasonNextAiringEpisode2;
            list = list2;
            coverImage = coverImage3;
            title = title3;
            str = str7;
            i = iDecodeIntElement5;
            i2 = iDecodeIntElement4;
            str2 = str8;
            i3 = i6;
            str3 = str9;
            num = num3;
        }
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return new AniListApi.Media(i3, iDecodeIntElement, num, str3, i2, str2, i, title, str, coverImage, list, seasonNextAiringEpisode, null);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(Encoder encoder, AniListApi.Media value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        AniListApi.Media.write$Self$app(value, compositeEncoderBeginStructure, serialDescriptor);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public /* bridge */ KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.CC.$default$typeParametersSerializers(this);
    }
}
