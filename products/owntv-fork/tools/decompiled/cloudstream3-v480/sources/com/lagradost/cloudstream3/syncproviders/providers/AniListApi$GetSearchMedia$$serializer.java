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

/* JADX INFO: compiled from: AniListApi.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0019\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006H\u0086\u0080\u0004¢\u0006\u0002\u0010\bJ\u0012\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0086\u0080\u0004J\u001a\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0086\u0080\u0004R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"com/lagradost/cloudstream3/syncproviders/providers/AniListApi.GetSearchMedia.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetSearchMedia;", "<init>", "()V", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", ES6Iterator.VALUE_PROPERTY, "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly")
public final /* synthetic */ class AniListApi$GetSearchMedia$$serializer implements GeneratedSerializer<AniListApi.GetSearchMedia> {
    public static final AniListApi$GetSearchMedia$$serializer INSTANCE;
    private static final SerialDescriptor descriptor;

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        AniListApi$GetSearchMedia$$serializer aniListApi$GetSearchMedia$$serializer = new AniListApi$GetSearchMedia$$serializer();
        INSTANCE = aniListApi$GetSearchMedia$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.lagradost.cloudstream3.syncproviders.providers.AniListApi.GetSearchMedia", aniListApi$GetSearchMedia$$serializer, 12);
        pluginGeneratedSerialDescriptor.addElement("id", false);
        pluginGeneratedSerialDescriptor.addElement("idMal", false);
        pluginGeneratedSerialDescriptor.addElement("seasonYear", false);
        pluginGeneratedSerialDescriptor.addElement("title", false);
        pluginGeneratedSerialDescriptor.addElement("startDate", false);
        pluginGeneratedSerialDescriptor.addElement("averageScore", false);
        pluginGeneratedSerialDescriptor.addElement("meanScore", false);
        pluginGeneratedSerialDescriptor.addElement("bannerImage", false);
        pluginGeneratedSerialDescriptor.addElement("trailer", false);
        pluginGeneratedSerialDescriptor.addElement("nextAiringEpisode", false);
        pluginGeneratedSerialDescriptor.addElement("recommendations", false);
        pluginGeneratedSerialDescriptor.addElement("relations", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private AniListApi$GetSearchMedia$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public final KSerializer<?>[] childSerializers() {
        return new KSerializer[]{IntSerializer.INSTANCE, BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE), IntSerializer.INSTANCE, AniListApi$GetSearchTitle$$serializer.INSTANCE, AniListApi$StartedAt$$serializer.INSTANCE, BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE), BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(AniListApi$TrailerObject$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(AniListApi$SeasonNextAiringEpisode$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(AniListApi$Recommendations$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(AniListApi$SeasonEdges$$serializer.INSTANCE)};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public final AniListApi.GetSearchMedia deserialize(Decoder decoder) {
        AniListApi.SeasonEdges seasonEdges;
        AniListApi.SeasonNextAiringEpisode seasonNextAiringEpisode;
        AniListApi.Recommendations recommendations;
        AniListApi.GetSearchTitle getSearchTitle;
        int i;
        String str;
        Integer num;
        AniListApi.StartedAt startedAt;
        Integer num2;
        Integer num3;
        int i2;
        AniListApi.TrailerObject trailerObject;
        int i3;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        int i4 = 11;
        int i5 = 0;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            int iDecodeIntElement = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 0);
            Integer num4 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 1, IntSerializer.INSTANCE, null);
            int iDecodeIntElement2 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 2);
            AniListApi.GetSearchTitle getSearchTitle2 = (AniListApi.GetSearchTitle) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 3, AniListApi$GetSearchTitle$$serializer.INSTANCE, null);
            startedAt = (AniListApi.StartedAt) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 4, AniListApi$StartedAt$$serializer.INSTANCE, null);
            Integer num5 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 5, IntSerializer.INSTANCE, null);
            Integer num6 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 6, IntSerializer.INSTANCE, null);
            String str2 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 7, StringSerializer.INSTANCE, null);
            AniListApi.TrailerObject trailerObject2 = (AniListApi.TrailerObject) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 8, AniListApi$TrailerObject$$serializer.INSTANCE, null);
            AniListApi.SeasonNextAiringEpisode seasonNextAiringEpisode2 = (AniListApi.SeasonNextAiringEpisode) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 9, AniListApi$SeasonNextAiringEpisode$$serializer.INSTANCE, null);
            AniListApi.Recommendations recommendations2 = (AniListApi.Recommendations) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 10, AniListApi$Recommendations$$serializer.INSTANCE, null);
            str = str2;
            i = 4095;
            seasonEdges = (AniListApi.SeasonEdges) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 11, AniListApi$SeasonEdges$$serializer.INSTANCE, null);
            recommendations = recommendations2;
            seasonNextAiringEpisode = seasonNextAiringEpisode2;
            trailerObject = trailerObject2;
            getSearchTitle = getSearchTitle2;
            i2 = iDecodeIntElement2;
            num3 = num5;
            num2 = num4;
            num = num6;
            i3 = iDecodeIntElement;
        } else {
            boolean z = true;
            int iDecodeIntElement3 = 0;
            AniListApi.SeasonEdges seasonEdges2 = null;
            AniListApi.SeasonNextAiringEpisode seasonNextAiringEpisode3 = null;
            AniListApi.TrailerObject trailerObject3 = null;
            String str3 = null;
            Integer num7 = null;
            AniListApi.Recommendations recommendations3 = null;
            Integer num8 = null;
            AniListApi.StartedAt startedAt2 = null;
            AniListApi.GetSearchTitle getSearchTitle3 = null;
            Integer num9 = null;
            int iDecodeIntElement4 = 0;
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(serialDescriptor);
                switch (iDecodeElementIndex) {
                    case -1:
                        iDecodeIntElement3 = iDecodeIntElement3;
                        z = false;
                        i4 = 11;
                        break;
                    case 0:
                        i5 |= 1;
                        iDecodeIntElement3 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 0);
                        i4 = 11;
                        num9 = num9;
                        break;
                    case 1:
                        num9 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 1, IntSerializer.INSTANCE, num9);
                        i5 |= 2;
                        iDecodeIntElement3 = iDecodeIntElement3;
                        i4 = 11;
                        break;
                    case 2:
                        iDecodeIntElement4 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 2);
                        i5 |= 4;
                        i4 = 11;
                        break;
                    case 3:
                        getSearchTitle3 = (AniListApi.GetSearchTitle) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 3, AniListApi$GetSearchTitle$$serializer.INSTANCE, getSearchTitle3);
                        i5 |= 8;
                        i4 = 11;
                        break;
                    case 4:
                        startedAt2 = (AniListApi.StartedAt) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 4, AniListApi$StartedAt$$serializer.INSTANCE, startedAt2);
                        i5 |= 16;
                        i4 = 11;
                        break;
                    case 5:
                        num7 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 5, IntSerializer.INSTANCE, num7);
                        i5 |= 32;
                        i4 = 11;
                        break;
                    case 6:
                        num8 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 6, IntSerializer.INSTANCE, num8);
                        i5 |= 64;
                        i4 = 11;
                        break;
                    case 7:
                        str3 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 7, StringSerializer.INSTANCE, str3);
                        i5 |= 128;
                        i4 = 11;
                        break;
                    case 8:
                        trailerObject3 = (AniListApi.TrailerObject) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 8, AniListApi$TrailerObject$$serializer.INSTANCE, trailerObject3);
                        i5 |= 256;
                        i4 = 11;
                        break;
                    case 9:
                        seasonNextAiringEpisode3 = (AniListApi.SeasonNextAiringEpisode) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 9, AniListApi$SeasonNextAiringEpisode$$serializer.INSTANCE, seasonNextAiringEpisode3);
                        i5 |= 512;
                        i4 = 11;
                        break;
                    case 10:
                        recommendations3 = (AniListApi.Recommendations) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 10, AniListApi$Recommendations$$serializer.INSTANCE, recommendations3);
                        i5 |= 1024;
                        i4 = 11;
                        break;
                    case 11:
                        seasonEdges2 = (AniListApi.SeasonEdges) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, i4, AniListApi$SeasonEdges$$serializer.INSTANCE, seasonEdges2);
                        i5 |= 2048;
                        break;
                    default:
                        throw new UnknownFieldException(iDecodeElementIndex);
                }
            }
            Integer num10 = num9;
            seasonEdges = seasonEdges2;
            seasonNextAiringEpisode = seasonNextAiringEpisode3;
            recommendations = recommendations3;
            getSearchTitle = getSearchTitle3;
            i = i5;
            str = str3;
            num = num8;
            startedAt = startedAt2;
            num2 = num10;
            num3 = num7;
            i2 = iDecodeIntElement4;
            trailerObject = trailerObject3;
            i3 = iDecodeIntElement3;
        }
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return new AniListApi.GetSearchMedia(i, i3, num2, i2, getSearchTitle, startedAt, num3, num, str, trailerObject, seasonNextAiringEpisode, recommendations, seasonEdges, null);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(Encoder encoder, AniListApi.GetSearchMedia value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        AniListApi.GetSearchMedia.write$Self$app(value, compositeEncoderBeginStructure, serialDescriptor);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public /* bridge */ KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.CC.$default$typeParametersSerializers(this);
    }
}
