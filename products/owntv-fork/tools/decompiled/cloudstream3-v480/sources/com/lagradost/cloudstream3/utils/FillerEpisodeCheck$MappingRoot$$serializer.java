package com.lagradost.cloudstream3.utils;

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
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.StringSerializer;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: FillerEpisodeCheck.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0019\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006H\u0086\u0080\u0004¢\u0006\u0002\u0010\bJ\u0012\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0086\u0080\u0004J\u001a\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0086\u0080\u0004R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"com/lagradost/cloudstream3/utils/FillerEpisodeCheck.MappingRoot.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/lagradost/cloudstream3/utils/FillerEpisodeCheck$MappingRoot;", "<init>", "()V", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", ES6Iterator.VALUE_PROPERTY, "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly")
public final /* synthetic */ class FillerEpisodeCheck$MappingRoot$$serializer implements GeneratedSerializer<FillerEpisodeCheck.MappingRoot> {
    public static final FillerEpisodeCheck$MappingRoot$$serializer INSTANCE;
    private static final SerialDescriptor descriptor;

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        FillerEpisodeCheck$MappingRoot$$serializer fillerEpisodeCheck$MappingRoot$$serializer = new FillerEpisodeCheck$MappingRoot$$serializer();
        INSTANCE = fillerEpisodeCheck$MappingRoot$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.lagradost.cloudstream3.utils.FillerEpisodeCheck.MappingRoot", fillerEpisodeCheck$MappingRoot$$serializer, 15);
        pluginGeneratedSerialDescriptor.addElement("type", false);
        pluginGeneratedSerialDescriptor.addElement("anidb_id", false);
        pluginGeneratedSerialDescriptor.addElement("anilist_id", false);
        pluginGeneratedSerialDescriptor.addElement("animecountdown_id", false);
        pluginGeneratedSerialDescriptor.addElement("animenewsnetwork_id", false);
        pluginGeneratedSerialDescriptor.addElement("anime-planet_id", false);
        pluginGeneratedSerialDescriptor.addElement("anisearch_id", false);
        pluginGeneratedSerialDescriptor.addElement("imdb_id", false);
        pluginGeneratedSerialDescriptor.addElement("kitsu_id", false);
        pluginGeneratedSerialDescriptor.addElement("livechart_id", false);
        pluginGeneratedSerialDescriptor.addElement("mal_id", false);
        pluginGeneratedSerialDescriptor.addElement("simkl_id", false);
        pluginGeneratedSerialDescriptor.addElement("themoviedb_id", false);
        pluginGeneratedSerialDescriptor.addElement("tvdb_id", false);
        pluginGeneratedSerialDescriptor.addElement("season", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private FillerEpisodeCheck$MappingRoot$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public final KSerializer<?>[] childSerializers() {
        return new KSerializer[]{BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(LongSerializer.INSTANCE), BuiltinSerializersKt.getNullable(LongSerializer.INSTANCE), BuiltinSerializersKt.getNullable(LongSerializer.INSTANCE), BuiltinSerializersKt.getNullable(LongSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(LongSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(LongSerializer.INSTANCE), BuiltinSerializersKt.getNullable(LongSerializer.INSTANCE), BuiltinSerializersKt.getNullable(LongSerializer.INSTANCE), BuiltinSerializersKt.getNullable(LongSerializer.INSTANCE), BuiltinSerializersKt.getNullable(LongSerializer.INSTANCE), BuiltinSerializersKt.getNullable(LongSerializer.INSTANCE), BuiltinSerializersKt.getNullable(FillerEpisodeCheck$Season$$serializer.INSTANCE)};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public final FillerEpisodeCheck.MappingRoot deserialize(Decoder decoder) {
        int i;
        Long l;
        Long l2;
        Long l3;
        Long l4;
        Long l5;
        FillerEpisodeCheck.Season season;
        String str;
        Long l6;
        Long l7;
        Long l8;
        String str2;
        String str3;
        Long l9;
        Long l10;
        Long l11;
        Long l12;
        Long l13;
        Long l14;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            String str4 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 0, StringSerializer.INSTANCE, null);
            Long l15 = (Long) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 1, LongSerializer.INSTANCE, null);
            Long l16 = (Long) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 2, LongSerializer.INSTANCE, null);
            Long l17 = (Long) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 3, LongSerializer.INSTANCE, null);
            Long l18 = (Long) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 4, LongSerializer.INSTANCE, null);
            String str5 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 5, StringSerializer.INSTANCE, null);
            Long l19 = (Long) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 6, LongSerializer.INSTANCE, null);
            String str6 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 7, StringSerializer.INSTANCE, null);
            Long l20 = (Long) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 8, LongSerializer.INSTANCE, null);
            Long l21 = (Long) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 9, LongSerializer.INSTANCE, null);
            Long l22 = (Long) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 10, LongSerializer.INSTANCE, null);
            Long l23 = (Long) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 11, LongSerializer.INSTANCE, null);
            Long l24 = (Long) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 12, LongSerializer.INSTANCE, null);
            i = 32767;
            l = l22;
            str = str4;
            l5 = (Long) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 13, LongSerializer.INSTANCE, null);
            season = (FillerEpisodeCheck.Season) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 14, FillerEpisodeCheck$Season$$serializer.INSTANCE, null);
            l3 = l23;
            l10 = l20;
            l6 = l24;
            l4 = l21;
            l7 = l19;
            l9 = l17;
            l2 = l15;
            str2 = str5;
            l8 = l18;
            str3 = str6;
            l11 = l16;
        } else {
            boolean z = true;
            Long l25 = null;
            Long l26 = null;
            Long l27 = null;
            Long l28 = null;
            Long l29 = null;
            String str7 = null;
            Long l30 = null;
            Long l31 = null;
            Long l32 = null;
            String str8 = null;
            Long l33 = null;
            String str9 = null;
            Long l34 = null;
            Long l35 = null;
            i = 0;
            FillerEpisodeCheck.Season season2 = null;
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(serialDescriptor);
                switch (iDecodeElementIndex) {
                    case -1:
                        l13 = l35;
                        z = false;
                        l25 = l25;
                        l35 = l13;
                        l34 = l34;
                        break;
                    case 0:
                        l13 = l35;
                        str9 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 0, StringSerializer.INSTANCE, str9);
                        i |= 1;
                        l25 = l25;
                        l35 = l13;
                        l34 = l34;
                        break;
                    case 1:
                        l34 = l34;
                        i |= 2;
                        l14 = l25;
                        l35 = (Long) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 1, LongSerializer.INSTANCE, l35);
                        l25 = l14;
                        l34 = l34;
                        break;
                    case 2:
                        l34 = l34;
                        l14 = (Long) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 2, LongSerializer.INSTANCE, l25);
                        i |= 4;
                        l25 = l14;
                        l34 = l34;
                        break;
                    case 3:
                        l12 = l34;
                        l33 = (Long) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 3, LongSerializer.INSTANCE, l33);
                        i |= 8;
                        season2 = season2;
                        l34 = l12;
                        l25 = l25;
                        break;
                    case 4:
                        l12 = l34;
                        l31 = (Long) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 4, LongSerializer.INSTANCE, l31);
                        i |= 16;
                        season2 = season2;
                        l34 = l12;
                        l25 = l25;
                        break;
                    case 5:
                        l12 = l34;
                        str8 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 5, StringSerializer.INSTANCE, str8);
                        i |= 32;
                        season2 = season2;
                        l34 = l12;
                        l25 = l25;
                        break;
                    case 6:
                        l12 = l34;
                        l30 = (Long) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 6, LongSerializer.INSTANCE, l30);
                        i |= 64;
                        season2 = season2;
                        l34 = l12;
                        l25 = l25;
                        break;
                    case 7:
                        l12 = l34;
                        str7 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 7, StringSerializer.INSTANCE, str7);
                        i |= 128;
                        season2 = season2;
                        l34 = l12;
                        l25 = l25;
                        break;
                    case 8:
                        l12 = l34;
                        l29 = (Long) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 8, LongSerializer.INSTANCE, l29);
                        i |= 256;
                        season2 = season2;
                        l34 = l12;
                        l25 = l25;
                        break;
                    case 9:
                        l12 = l34;
                        l32 = (Long) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 9, LongSerializer.INSTANCE, l32);
                        i |= 512;
                        season2 = season2;
                        l34 = l12;
                        l25 = l25;
                        break;
                    case 10:
                        l12 = l34;
                        l28 = (Long) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 10, LongSerializer.INSTANCE, l28);
                        i |= 1024;
                        season2 = season2;
                        l34 = l12;
                        l25 = l25;
                        break;
                    case 11:
                        l12 = l34;
                        l27 = (Long) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 11, LongSerializer.INSTANCE, l27);
                        i |= 2048;
                        season2 = season2;
                        l34 = l12;
                        l25 = l25;
                        break;
                    case 12:
                        l12 = l34;
                        l26 = (Long) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 12, LongSerializer.INSTANCE, l26);
                        i |= 4096;
                        season2 = season2;
                        l34 = l12;
                        l25 = l25;
                        break;
                    case 13:
                        l12 = (Long) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 13, LongSerializer.INSTANCE, l34);
                        i |= 8192;
                        season2 = season2;
                        l34 = l12;
                        l25 = l25;
                        break;
                    case 14:
                        l25 = l25;
                        season2 = (FillerEpisodeCheck.Season) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 14, FillerEpisodeCheck$Season$$serializer.INSTANCE, season2);
                        i |= 16384;
                        l25 = l25;
                        break;
                    default:
                        throw new UnknownFieldException(iDecodeElementIndex);
                }
            }
            Long l36 = l34;
            FillerEpisodeCheck.Season season3 = season2;
            String str10 = str9;
            l = l28;
            l2 = l35;
            l3 = l27;
            l4 = l32;
            l5 = l36;
            season = season3;
            str = str10;
            l6 = l26;
            l7 = l30;
            l8 = l31;
            str2 = str8;
            str3 = str7;
            l9 = l33;
            l10 = l29;
            l11 = l25;
        }
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return new FillerEpisodeCheck.MappingRoot(i, str, l2, l11, l9, l8, str2, l7, str3, l10, l4, l, l3, l6, l5, season, null);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(Encoder encoder, FillerEpisodeCheck.MappingRoot value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        FillerEpisodeCheck.MappingRoot.write$Self$app(value, compositeEncoderBeginStructure, serialDescriptor);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public /* bridge */ KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.CC.$default$typeParametersSerializers(this);
    }
}
