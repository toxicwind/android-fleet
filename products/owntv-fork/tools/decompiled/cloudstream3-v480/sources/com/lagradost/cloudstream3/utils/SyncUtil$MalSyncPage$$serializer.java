package com.lagradost.cloudstream3.utils;

import com.google.android.gms.common.internal.ImagesContract;
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
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.StringSerializer;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: SyncUtil.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0019\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006H\u0086\u0080\u0004¢\u0006\u0002\u0010\bJ\u0012\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0086\u0080\u0004J\u001a\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0086\u0080\u0004R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"com/lagradost/cloudstream3/utils/SyncUtil.MalSyncPage.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/lagradost/cloudstream3/utils/SyncUtil$MalSyncPage;", "<init>", "()V", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", ES6Iterator.VALUE_PROPERTY, "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly")
public final /* synthetic */ class SyncUtil$MalSyncPage$$serializer implements GeneratedSerializer<SyncUtil.MalSyncPage> {
    public static final SyncUtil$MalSyncPage$$serializer INSTANCE;
    private static final SerialDescriptor descriptor;

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        SyncUtil$MalSyncPage$$serializer syncUtil$MalSyncPage$$serializer = new SyncUtil$MalSyncPage$$serializer();
        INSTANCE = syncUtil$MalSyncPage$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.lagradost.cloudstream3.utils.SyncUtil.MalSyncPage", syncUtil$MalSyncPage$$serializer, 18);
        pluginGeneratedSerialDescriptor.addElement("identifier", false);
        pluginGeneratedSerialDescriptor.addElement("type", false);
        pluginGeneratedSerialDescriptor.addElement("page", false);
        pluginGeneratedSerialDescriptor.addElement("title", false);
        pluginGeneratedSerialDescriptor.addElement(ImagesContract.URL, false);
        pluginGeneratedSerialDescriptor.addElement("image", false);
        pluginGeneratedSerialDescriptor.addElement("hentai", false);
        pluginGeneratedSerialDescriptor.addElement("sticky", false);
        pluginGeneratedSerialDescriptor.addElement("active", false);
        pluginGeneratedSerialDescriptor.addElement("actor", false);
        pluginGeneratedSerialDescriptor.addElement("malId", false);
        pluginGeneratedSerialDescriptor.addElement("aniId", false);
        pluginGeneratedSerialDescriptor.addElement("createdAt", false);
        pluginGeneratedSerialDescriptor.addElement("updatedAt", false);
        pluginGeneratedSerialDescriptor.addElement("deletedAt", false);
        pluginGeneratedSerialDescriptor.addElement("Mal", false);
        pluginGeneratedSerialDescriptor.addElement("Anilist", false);
        pluginGeneratedSerialDescriptor.addElement("malUrl", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private SyncUtil$MalSyncPage$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public final KSerializer<?>[] childSerializers() {
        return new KSerializer[]{BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(BooleanSerializer.INSTANCE), BuiltinSerializersKt.getNullable(BooleanSerializer.INSTANCE), BuiltinSerializersKt.getNullable(BooleanSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE), BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(SyncUtil$Mal$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(SyncUtil$Anilist$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE)};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public final SyncUtil.MalSyncPage deserialize(Decoder decoder) {
        int i;
        String str;
        Boolean bool;
        String str2;
        String str3;
        String str4;
        Integer num;
        SyncUtil.Anilist anilist;
        SyncUtil.Mal mal;
        String str5;
        String str6;
        String str7;
        String str8;
        Integer num2;
        Boolean bool2;
        Boolean bool3;
        String str9;
        String str10;
        String str11;
        String str12;
        SyncUtil.Anilist anilist2;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            String str13 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 0, StringSerializer.INSTANCE, null);
            String str14 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 1, StringSerializer.INSTANCE, null);
            String str15 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 2, StringSerializer.INSTANCE, null);
            String str16 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 3, StringSerializer.INSTANCE, null);
            String str17 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 4, StringSerializer.INSTANCE, null);
            String str18 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 5, StringSerializer.INSTANCE, null);
            Boolean bool4 = (Boolean) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 6, BooleanSerializer.INSTANCE, null);
            Boolean bool5 = (Boolean) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 7, BooleanSerializer.INSTANCE, null);
            Boolean bool6 = (Boolean) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 8, BooleanSerializer.INSTANCE, null);
            String str19 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 9, StringSerializer.INSTANCE, null);
            Integer num3 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 10, IntSerializer.INSTANCE, null);
            Integer num4 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 11, IntSerializer.INSTANCE, null);
            String str20 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 12, StringSerializer.INSTANCE, null);
            String str21 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 13, StringSerializer.INSTANCE, null);
            String str22 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 14, StringSerializer.INSTANCE, null);
            SyncUtil.Mal mal2 = (SyncUtil.Mal) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 15, SyncUtil$Mal$$serializer.INSTANCE, null);
            i = 262143;
            str3 = str18;
            str2 = str17;
            anilist = (SyncUtil.Anilist) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 16, SyncUtil$Anilist$$serializer.INSTANCE, null);
            str7 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 17, StringSerializer.INSTANCE, null);
            bool = bool4;
            mal = mal2;
            str5 = str22;
            str = str21;
            str4 = str20;
            str10 = str16;
            bool2 = bool6;
            num = num3;
            str8 = str19;
            bool3 = bool5;
            str9 = str13;
            str11 = str15;
            str6 = str14;
            num2 = num4;
        } else {
            boolean z = true;
            String str23 = null;
            String str24 = null;
            String str25 = null;
            String str26 = null;
            Integer num5 = null;
            Integer num6 = null;
            Boolean bool7 = null;
            Boolean bool8 = null;
            Boolean bool9 = null;
            String str27 = null;
            String str28 = null;
            String str29 = null;
            String str30 = null;
            String str31 = null;
            SyncUtil.Mal mal3 = null;
            SyncUtil.Anilist anilist3 = null;
            String str32 = null;
            i = 0;
            String str33 = null;
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(serialDescriptor);
                switch (iDecodeElementIndex) {
                    case -1:
                        str12 = str32;
                        z = false;
                        str33 = str33;
                        str23 = str23;
                        anilist3 = anilist3;
                        str32 = str12;
                        break;
                    case 0:
                        str12 = str32;
                        str29 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 0, StringSerializer.INSTANCE, str29);
                        i |= 1;
                        str33 = str33;
                        str23 = str23;
                        anilist3 = anilist3;
                        str32 = str12;
                        break;
                    case 1:
                        anilist2 = anilist3;
                        i |= 2;
                        str23 = str23;
                        str32 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 1, StringSerializer.INSTANCE, str32);
                        str33 = str33;
                        anilist3 = anilist2;
                        break;
                    case 2:
                        anilist2 = anilist3;
                        str25 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 2, StringSerializer.INSTANCE, str25);
                        i |= 4;
                        str30 = str30;
                        str33 = str33;
                        anilist3 = anilist2;
                        break;
                    case 3:
                        anilist2 = anilist3;
                        str24 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 3, StringSerializer.INSTANCE, str24);
                        i |= 8;
                        str33 = str33;
                        str25 = str25;
                        anilist3 = anilist2;
                        break;
                    case 4:
                        anilist2 = anilist3;
                        str27 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 4, StringSerializer.INSTANCE, str27);
                        i |= 16;
                        str33 = str33;
                        str25 = str25;
                        anilist3 = anilist2;
                        break;
                    case 5:
                        anilist2 = anilist3;
                        str28 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 5, StringSerializer.INSTANCE, str28);
                        i |= 32;
                        str33 = str33;
                        str25 = str25;
                        anilist3 = anilist2;
                        break;
                    case 6:
                        anilist2 = anilist3;
                        bool9 = (Boolean) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 6, BooleanSerializer.INSTANCE, bool9);
                        i |= 64;
                        str33 = str33;
                        str25 = str25;
                        anilist3 = anilist2;
                        break;
                    case 7:
                        anilist2 = anilist3;
                        bool8 = (Boolean) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 7, BooleanSerializer.INSTANCE, bool8);
                        i |= 128;
                        str33 = str33;
                        str25 = str25;
                        anilist3 = anilist2;
                        break;
                    case 8:
                        anilist2 = anilist3;
                        bool7 = (Boolean) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 8, BooleanSerializer.INSTANCE, bool7);
                        i |= 256;
                        str33 = str33;
                        str25 = str25;
                        anilist3 = anilist2;
                        break;
                    case 9:
                        anilist2 = anilist3;
                        str23 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 9, StringSerializer.INSTANCE, str23);
                        i |= 512;
                        str33 = str33;
                        str25 = str25;
                        anilist3 = anilist2;
                        break;
                    case 10:
                        anilist2 = anilist3;
                        num6 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 10, IntSerializer.INSTANCE, num6);
                        i |= 1024;
                        str33 = str33;
                        str25 = str25;
                        anilist3 = anilist2;
                        break;
                    case 11:
                        anilist2 = anilist3;
                        num5 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 11, IntSerializer.INSTANCE, num5);
                        i |= 2048;
                        str33 = str33;
                        str25 = str25;
                        anilist3 = anilist2;
                        break;
                    case 12:
                        anilist2 = anilist3;
                        str26 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 12, StringSerializer.INSTANCE, str26);
                        i |= 4096;
                        str33 = str33;
                        str25 = str25;
                        anilist3 = anilist2;
                        break;
                    case 13:
                        anilist2 = anilist3;
                        str30 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 13, StringSerializer.INSTANCE, str30);
                        i |= 8192;
                        str31 = str31;
                        str33 = str33;
                        str25 = str25;
                        anilist3 = anilist2;
                        break;
                    case 14:
                        anilist2 = anilist3;
                        str31 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 14, StringSerializer.INSTANCE, str31);
                        i |= 16384;
                        mal3 = mal3;
                        str33 = str33;
                        str25 = str25;
                        anilist3 = anilist2;
                        break;
                    case 15:
                        anilist2 = anilist3;
                        mal3 = (SyncUtil.Mal) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 15, SyncUtil$Mal$$serializer.INSTANCE, mal3);
                        i |= 32768;
                        str33 = str33;
                        str25 = str25;
                        anilist3 = anilist2;
                        break;
                    case 16:
                        i |= 65536;
                        str33 = str33;
                        anilist3 = (SyncUtil.Anilist) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 16, SyncUtil$Anilist$$serializer.INSTANCE, anilist3);
                        str25 = str25;
                        break;
                    case 17:
                        str33 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 17, StringSerializer.INSTANCE, str33);
                        i |= 131072;
                        str25 = str25;
                        break;
                    default:
                        throw new UnknownFieldException(iDecodeElementIndex);
                }
            }
            str = str30;
            String str34 = str32;
            String str35 = str23;
            String str36 = str29;
            String str37 = str28;
            bool = bool9;
            str2 = str27;
            str3 = str37;
            str4 = str26;
            num = num6;
            anilist = anilist3;
            mal = mal3;
            str5 = str31;
            str6 = str34;
            str7 = str33;
            str8 = str35;
            num2 = num5;
            bool2 = bool7;
            bool3 = bool8;
            str9 = str36;
            str10 = str24;
            str11 = str25;
        }
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return new SyncUtil.MalSyncPage(i, str9, str6, str11, str10, str2, str3, bool, bool3, bool2, str8, num, num2, str4, str, str5, mal, anilist, str7, null);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(Encoder encoder, SyncUtil.MalSyncPage value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        SyncUtil.MalSyncPage.write$Self$app(value, compositeEncoderBeginStructure, serialDescriptor);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public /* bridge */ KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.CC.$default$typeParametersSerializers(this);
    }
}
