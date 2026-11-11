package com.lagradost.cloudstream3.syncproviders.providers;

import androidx.constraintlayout.core.motion.utils.TypedValues;
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
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.StringSerializer;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: AniListApi.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0019\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006H\u0086\u0080\u0004¢\u0006\u0002\u0010\bJ\u0012\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0086\u0080\u0004J\u001a\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0086\u0080\u0004R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"com/lagradost/cloudstream3/syncproviders/providers/AniListApi.SeasonMedia.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonMedia;", "<init>", "()V", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", ES6Iterator.VALUE_PROPERTY, "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly")
public final /* synthetic */ class AniListApi$SeasonMedia$$serializer implements GeneratedSerializer<AniListApi.SeasonMedia> {
    public static final AniListApi$SeasonMedia$$serializer INSTANCE;
    private static final SerialDescriptor descriptor;

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        AniListApi$SeasonMedia$$serializer aniListApi$SeasonMedia$$serializer = new AniListApi$SeasonMedia$$serializer();
        INSTANCE = aniListApi$SeasonMedia$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.lagradost.cloudstream3.syncproviders.providers.AniListApi.SeasonMedia", aniListApi$SeasonMedia$$serializer, 17);
        pluginGeneratedSerialDescriptor.addElement("id", false);
        pluginGeneratedSerialDescriptor.addElement("title", false);
        pluginGeneratedSerialDescriptor.addElement("idMal", false);
        pluginGeneratedSerialDescriptor.addElement("format", false);
        pluginGeneratedSerialDescriptor.addElement("nextAiringEpisode", false);
        pluginGeneratedSerialDescriptor.addElement("relations", false);
        pluginGeneratedSerialDescriptor.addElement("coverImage", false);
        pluginGeneratedSerialDescriptor.addElement(TypedValues.TransitionType.S_DURATION, false);
        pluginGeneratedSerialDescriptor.addElement("episodes", false);
        pluginGeneratedSerialDescriptor.addElement("genres", false);
        pluginGeneratedSerialDescriptor.addElement("synonyms", false);
        pluginGeneratedSerialDescriptor.addElement("averageScore", false);
        pluginGeneratedSerialDescriptor.addElement("isAdult", false);
        pluginGeneratedSerialDescriptor.addElement("trailer", false);
        pluginGeneratedSerialDescriptor.addElement("description", false);
        pluginGeneratedSerialDescriptor.addElement("characters", false);
        pluginGeneratedSerialDescriptor.addElement("recommendations", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private AniListApi$SeasonMedia$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public final KSerializer<?>[] childSerializers() {
        Lazy[] lazyArr = AniListApi.SeasonMedia.$childSerializers;
        return new KSerializer[]{BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE), BuiltinSerializersKt.getNullable(AniListApi$MediaTitle$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(AniListApi$SeasonNextAiringEpisode$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(AniListApi$SeasonEdges$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(AniListApi$MediaCoverImage$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE), BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE), BuiltinSerializersKt.getNullable((KSerializer) lazyArr[9].getValue()), BuiltinSerializersKt.getNullable((KSerializer) lazyArr[10].getValue()), BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE), BuiltinSerializersKt.getNullable(BooleanSerializer.INSTANCE), BuiltinSerializersKt.getNullable(AniListApi$MediaTrailer$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(AniListApi$CharacterConnection$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(AniListApi$RecommendationConnection$$serializer.INSTANCE)};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public final AniListApi.SeasonMedia deserialize(Decoder decoder) {
        Integer num;
        String str;
        AniListApi.SeasonEdges seasonEdges;
        int i;
        List list;
        AniListApi.CharacterConnection characterConnection;
        String str2;
        AniListApi.MediaTrailer mediaTrailer;
        Boolean bool;
        AniListApi.RecommendationConnection recommendationConnection;
        Integer num2;
        Integer num3;
        AniListApi.MediaCoverImage mediaCoverImage;
        AniListApi.SeasonNextAiringEpisode seasonNextAiringEpisode;
        AniListApi.MediaTitle mediaTitle;
        Integer num4;
        List list2;
        Integer num5;
        Integer num6;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        Lazy[] lazyArr = AniListApi.SeasonMedia.$childSerializers;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            Integer num7 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 0, IntSerializer.INSTANCE, null);
            AniListApi.MediaTitle mediaTitle2 = (AniListApi.MediaTitle) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 1, AniListApi$MediaTitle$$serializer.INSTANCE, null);
            Integer num8 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 2, IntSerializer.INSTANCE, null);
            String str3 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 3, StringSerializer.INSTANCE, null);
            AniListApi.SeasonNextAiringEpisode seasonNextAiringEpisode2 = (AniListApi.SeasonNextAiringEpisode) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 4, AniListApi$SeasonNextAiringEpisode$$serializer.INSTANCE, null);
            AniListApi.SeasonEdges seasonEdges2 = (AniListApi.SeasonEdges) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 5, AniListApi$SeasonEdges$$serializer.INSTANCE, null);
            AniListApi.MediaCoverImage mediaCoverImage2 = (AniListApi.MediaCoverImage) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 6, AniListApi$MediaCoverImage$$serializer.INSTANCE, null);
            Integer num9 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 7, IntSerializer.INSTANCE, null);
            Integer num10 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 8, IntSerializer.INSTANCE, null);
            List list3 = (List) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 9, (DeserializationStrategy) lazyArr[9].getValue(), null);
            List list4 = (List) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 10, (DeserializationStrategy) lazyArr[10].getValue(), null);
            num = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 11, IntSerializer.INSTANCE, null);
            Boolean bool2 = (Boolean) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 12, BooleanSerializer.INSTANCE, null);
            AniListApi.MediaTrailer mediaTrailer2 = (AniListApi.MediaTrailer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 13, AniListApi$MediaTrailer$$serializer.INSTANCE, null);
            String str4 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 14, StringSerializer.INSTANCE, null);
            i = 131071;
            characterConnection = (AniListApi.CharacterConnection) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 15, AniListApi$CharacterConnection$$serializer.INSTANCE, null);
            recommendationConnection = (AniListApi.RecommendationConnection) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 16, AniListApi$RecommendationConnection$$serializer.INSTANCE, null);
            seasonEdges = seasonEdges2;
            str2 = str4;
            mediaTrailer = mediaTrailer2;
            bool = bool2;
            list2 = list4;
            list = list3;
            mediaTitle = mediaTitle2;
            num3 = num9;
            mediaCoverImage = mediaCoverImage2;
            num2 = num10;
            num4 = num7;
            seasonNextAiringEpisode = seasonNextAiringEpisode2;
            str = str3;
            num5 = num8;
        } else {
            int i2 = 10;
            int i3 = 9;
            boolean z = true;
            AniListApi.RecommendationConnection recommendationConnection2 = null;
            num = null;
            List list5 = null;
            Integer num11 = null;
            Integer num12 = null;
            Integer num13 = null;
            AniListApi.MediaCoverImage mediaCoverImage3 = null;
            AniListApi.SeasonNextAiringEpisode seasonNextAiringEpisode3 = null;
            str = null;
            List list6 = null;
            seasonEdges = null;
            Integer num14 = null;
            Boolean bool3 = null;
            AniListApi.MediaTrailer mediaTrailer3 = null;
            String str5 = null;
            AniListApi.CharacterConnection characterConnection2 = null;
            i = 0;
            AniListApi.MediaTitle mediaTitle3 = null;
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(serialDescriptor);
                switch (iDecodeElementIndex) {
                    case -1:
                        num6 = num14;
                        z = false;
                        mediaTitle3 = mediaTitle3;
                        i3 = 9;
                        i2 = 10;
                        num14 = num6;
                        recommendationConnection2 = recommendationConnection2;
                        characterConnection2 = characterConnection2;
                        break;
                    case 0:
                        num6 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 0, IntSerializer.INSTANCE, num14);
                        i |= 1;
                        mediaTitle3 = mediaTitle3;
                        i3 = 9;
                        i2 = 10;
                        num14 = num6;
                        recommendationConnection2 = recommendationConnection2;
                        characterConnection2 = characterConnection2;
                        break;
                    case 1:
                        characterConnection2 = characterConnection2;
                        mediaTitle3 = (AniListApi.MediaTitle) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 1, AniListApi$MediaTitle$$serializer.INSTANCE, mediaTitle3);
                        i |= 2;
                        bool3 = bool3;
                        recommendationConnection2 = recommendationConnection2;
                        i3 = 9;
                        i2 = 10;
                        characterConnection2 = characterConnection2;
                        break;
                    case 2:
                        num11 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 2, IntSerializer.INSTANCE, num11);
                        i |= 4;
                        recommendationConnection2 = recommendationConnection2;
                        mediaTitle3 = mediaTitle3;
                        i3 = 9;
                        i2 = 10;
                        characterConnection2 = characterConnection2;
                        break;
                    case 3:
                        str = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 3, StringSerializer.INSTANCE, str);
                        i |= 8;
                        recommendationConnection2 = recommendationConnection2;
                        mediaTitle3 = mediaTitle3;
                        i3 = 9;
                        i2 = 10;
                        characterConnection2 = characterConnection2;
                        break;
                    case 4:
                        seasonNextAiringEpisode3 = (AniListApi.SeasonNextAiringEpisode) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 4, AniListApi$SeasonNextAiringEpisode$$serializer.INSTANCE, seasonNextAiringEpisode3);
                        i |= 16;
                        recommendationConnection2 = recommendationConnection2;
                        mediaTitle3 = mediaTitle3;
                        i3 = 9;
                        i2 = 10;
                        characterConnection2 = characterConnection2;
                        break;
                    case 5:
                        seasonEdges = (AniListApi.SeasonEdges) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 5, AniListApi$SeasonEdges$$serializer.INSTANCE, seasonEdges);
                        i |= 32;
                        recommendationConnection2 = recommendationConnection2;
                        mediaTitle3 = mediaTitle3;
                        i3 = 9;
                        i2 = 10;
                        characterConnection2 = characterConnection2;
                        break;
                    case 6:
                        mediaCoverImage3 = (AniListApi.MediaCoverImage) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 6, AniListApi$MediaCoverImage$$serializer.INSTANCE, mediaCoverImage3);
                        i |= 64;
                        recommendationConnection2 = recommendationConnection2;
                        mediaTitle3 = mediaTitle3;
                        i3 = 9;
                        i2 = 10;
                        characterConnection2 = characterConnection2;
                        break;
                    case 7:
                        num13 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 7, IntSerializer.INSTANCE, num13);
                        i |= 128;
                        recommendationConnection2 = recommendationConnection2;
                        mediaTitle3 = mediaTitle3;
                        i3 = 9;
                        i2 = 10;
                        characterConnection2 = characterConnection2;
                        break;
                    case 8:
                        num12 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 8, IntSerializer.INSTANCE, num12);
                        i |= 256;
                        recommendationConnection2 = recommendationConnection2;
                        mediaTitle3 = mediaTitle3;
                        i3 = 9;
                        i2 = 10;
                        characterConnection2 = characterConnection2;
                        break;
                    case 9:
                        characterConnection2 = characterConnection2;
                        list6 = (List) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, i3, (DeserializationStrategy) lazyArr[i3].getValue(), list6);
                        i |= 512;
                        recommendationConnection2 = recommendationConnection2;
                        mediaTitle3 = mediaTitle3;
                        i2 = 10;
                        characterConnection2 = characterConnection2;
                        break;
                    case 10:
                        characterConnection2 = characterConnection2;
                        list5 = (List) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, i2, (DeserializationStrategy) lazyArr[i2].getValue(), list5);
                        i |= 1024;
                        recommendationConnection2 = recommendationConnection2;
                        mediaTitle3 = mediaTitle3;
                        characterConnection2 = characterConnection2;
                        break;
                    case 11:
                        characterConnection2 = characterConnection2;
                        num = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 11, IntSerializer.INSTANCE, num);
                        i |= 2048;
                        recommendationConnection2 = recommendationConnection2;
                        mediaTitle3 = mediaTitle3;
                        characterConnection2 = characterConnection2;
                        break;
                    case 12:
                        characterConnection2 = characterConnection2;
                        bool3 = (Boolean) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 12, BooleanSerializer.INSTANCE, bool3);
                        i |= 4096;
                        mediaTrailer3 = mediaTrailer3;
                        recommendationConnection2 = recommendationConnection2;
                        mediaTitle3 = mediaTitle3;
                        characterConnection2 = characterConnection2;
                        break;
                    case 13:
                        characterConnection2 = characterConnection2;
                        mediaTrailer3 = (AniListApi.MediaTrailer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 13, AniListApi$MediaTrailer$$serializer.INSTANCE, mediaTrailer3);
                        i |= 8192;
                        str5 = str5;
                        recommendationConnection2 = recommendationConnection2;
                        mediaTitle3 = mediaTitle3;
                        characterConnection2 = characterConnection2;
                        break;
                    case 14:
                        characterConnection2 = characterConnection2;
                        str5 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 14, StringSerializer.INSTANCE, str5);
                        i |= 16384;
                        recommendationConnection2 = recommendationConnection2;
                        mediaTitle3 = mediaTitle3;
                        characterConnection2 = characterConnection2;
                        break;
                    case 15:
                        i |= 32768;
                        recommendationConnection2 = recommendationConnection2;
                        characterConnection2 = (AniListApi.CharacterConnection) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 15, AniListApi$CharacterConnection$$serializer.INSTANCE, characterConnection2);
                        mediaTitle3 = mediaTitle3;
                        break;
                    case 16:
                        recommendationConnection2 = (AniListApi.RecommendationConnection) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 16, AniListApi$RecommendationConnection$$serializer.INSTANCE, recommendationConnection2);
                        i |= 65536;
                        mediaTitle3 = mediaTitle3;
                        break;
                    default:
                        throw new UnknownFieldException(iDecodeElementIndex);
                }
            }
            AniListApi.CharacterConnection characterConnection3 = characterConnection2;
            list = list6;
            characterConnection = characterConnection3;
            str2 = str5;
            mediaTrailer = mediaTrailer3;
            bool = bool3;
            recommendationConnection = recommendationConnection2;
            num2 = num12;
            num3 = num13;
            mediaCoverImage = mediaCoverImage3;
            seasonNextAiringEpisode = seasonNextAiringEpisode3;
            mediaTitle = mediaTitle3;
            num4 = num14;
            list2 = list5;
            num5 = num11;
        }
        int i4 = i;
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return new AniListApi.SeasonMedia(i4, num4, mediaTitle, num5, str, seasonNextAiringEpisode, seasonEdges, mediaCoverImage, num3, num2, list, list2, num, bool, mediaTrailer, str2, characterConnection, recommendationConnection, null);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(Encoder encoder, AniListApi.SeasonMedia value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        AniListApi.SeasonMedia.write$Self$app(value, compositeEncoderBeginStructure, serialDescriptor);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public /* bridge */ KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.CC.$default$typeParametersSerializers(this);
    }
}
