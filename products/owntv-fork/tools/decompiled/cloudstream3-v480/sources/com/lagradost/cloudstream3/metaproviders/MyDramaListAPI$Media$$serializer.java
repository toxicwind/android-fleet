package com.lagradost.cloudstream3.metaproviders;

import androidx.core.app.NotificationCompat;
import androidx.media3.common.C;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
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
import kotlinx.serialization.internal.DoubleSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: MyDramaList.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0019\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006H\u0086\u0080\u0004¢\u0006\u0002\u0010\bJ\u0012\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0086\u0080\u0004J\u001a\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0086\u0080\u0004R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"com/lagradost/cloudstream3/metaproviders/MyDramaListAPI.Media.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Media;", "<init>", "()V", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", ES6Iterator.VALUE_PROPERTY, "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly")
public final /* synthetic */ class MyDramaListAPI$Media$$serializer implements GeneratedSerializer<MyDramaListAPI.Media> {
    public static final MyDramaListAPI$Media$$serializer INSTANCE;
    private static final SerialDescriptor descriptor;

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        MyDramaListAPI$Media$$serializer myDramaListAPI$Media$$serializer = new MyDramaListAPI$Media$$serializer();
        INSTANCE = myDramaListAPI$Media$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.lagradost.cloudstream3.metaproviders.MyDramaListAPI.Media", myDramaListAPI$Media$$serializer, 33);
        pluginGeneratedSerialDescriptor.addElement("id", false);
        pluginGeneratedSerialDescriptor.addElement("slug", false);
        pluginGeneratedSerialDescriptor.addElement("title", false);
        pluginGeneratedSerialDescriptor.addElement("original_title", false);
        pluginGeneratedSerialDescriptor.addElement("year", false);
        pluginGeneratedSerialDescriptor.addElement("episodes", false);
        pluginGeneratedSerialDescriptor.addElement("rating", false);
        pluginGeneratedSerialDescriptor.addElement("permalink", true);
        pluginGeneratedSerialDescriptor.addElement("synopsis", true);
        pluginGeneratedSerialDescriptor.addElement("type", true);
        pluginGeneratedSerialDescriptor.addElement("media_type", true);
        pluginGeneratedSerialDescriptor.addElement("country", true);
        pluginGeneratedSerialDescriptor.addElement("language", true);
        pluginGeneratedSerialDescriptor.addElement("images", false);
        pluginGeneratedSerialDescriptor.addElement("alt_titles", true);
        pluginGeneratedSerialDescriptor.addElement("votes", true);
        pluginGeneratedSerialDescriptor.addElement("aired_start", true);
        pluginGeneratedSerialDescriptor.addElement("released", true);
        pluginGeneratedSerialDescriptor.addElement("release_dates_fmt", false);
        pluginGeneratedSerialDescriptor.addElement("genres", true);
        pluginGeneratedSerialDescriptor.addElement("trailer", false);
        pluginGeneratedSerialDescriptor.addElement("watchers", false);
        pluginGeneratedSerialDescriptor.addElement("ranked", false);
        pluginGeneratedSerialDescriptor.addElement("popularity", false);
        pluginGeneratedSerialDescriptor.addElement("runtime", false);
        pluginGeneratedSerialDescriptor.addElement("reviews_count", false);
        pluginGeneratedSerialDescriptor.addElement("recs_count", false);
        pluginGeneratedSerialDescriptor.addElement("comments_count", false);
        pluginGeneratedSerialDescriptor.addElement("certification", false);
        pluginGeneratedSerialDescriptor.addElement(NotificationCompat.CATEGORY_STATUS, false);
        pluginGeneratedSerialDescriptor.addElement("enable_ads", false);
        pluginGeneratedSerialDescriptor.addElement("sources", false);
        pluginGeneratedSerialDescriptor.addElement("updated_at", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private MyDramaListAPI$Media$$serializer() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public final KSerializer<?>[] childSerializers() {
        Lazy[] lazyArr = MyDramaListAPI.Media.$childSerializers;
        return new KSerializer[]{LongSerializer.INSTANCE, StringSerializer.INSTANCE, StringSerializer.INSTANCE, StringSerializer.INSTANCE, IntSerializer.INSTANCE, LongSerializer.INSTANCE, DoubleSerializer.INSTANCE, BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), MyDramaListAPI$Images$$serializer.INSTANCE, BuiltinSerializersKt.getNullable((KSerializer) lazyArr[14].getValue()), BuiltinSerializersKt.getNullable(LongSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), StringSerializer.INSTANCE, BuiltinSerializersKt.getNullable((KSerializer) lazyArr[19].getValue()), BuiltinSerializersKt.getNullable(MyDramaListAPI$Trailer$$serializer.INSTANCE), LongSerializer.INSTANCE, LongSerializer.INSTANCE, LongSerializer.INSTANCE, LongSerializer.INSTANCE, LongSerializer.INSTANCE, LongSerializer.INSTANCE, LongSerializer.INSTANCE, StringSerializer.INSTANCE, StringSerializer.INSTANCE, BooleanSerializer.INSTANCE, lazyArr[31].getValue(), LongSerializer.INSTANCE};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public final MyDramaListAPI.Media deserialize(Decoder decoder) {
        String str;
        String str2;
        String str3;
        int i;
        String str4;
        Long l;
        MyDramaListAPI.Images images;
        String str5;
        List list;
        String str6;
        MyDramaListAPI.Trailer trailer;
        String str7;
        String str8;
        int i2;
        long j;
        long j2;
        long j3;
        long j4;
        String str9;
        List list2;
        long j5;
        long j6;
        long j7;
        long jDecodeLongElement;
        int i3;
        boolean z;
        List list3;
        String str10;
        String str11;
        String str12;
        String str13;
        String str14;
        double d;
        long j8;
        long j9;
        int i4;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        Lazy[] lazyArr = MyDramaListAPI.Media.$childSerializers;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            long jDecodeLongElement2 = compositeDecoderBeginStructure.decodeLongElement(serialDescriptor, 0);
            String strDecodeStringElement = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 1);
            String strDecodeStringElement2 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 2);
            String strDecodeStringElement3 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 3);
            int iDecodeIntElement = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 4);
            long jDecodeLongElement3 = compositeDecoderBeginStructure.decodeLongElement(serialDescriptor, 5);
            double dDecodeDoubleElement = compositeDecoderBeginStructure.decodeDoubleElement(serialDescriptor, 6);
            String str15 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 7, StringSerializer.INSTANCE, null);
            String str16 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 8, StringSerializer.INSTANCE, null);
            String str17 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 9, StringSerializer.INSTANCE, null);
            String str18 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 10, StringSerializer.INSTANCE, null);
            String str19 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 11, StringSerializer.INSTANCE, null);
            String str20 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 12, StringSerializer.INSTANCE, null);
            MyDramaListAPI.Images images2 = (MyDramaListAPI.Images) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 13, MyDramaListAPI$Images$$serializer.INSTANCE, null);
            List list4 = (List) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 14, (DeserializationStrategy) lazyArr[14].getValue(), null);
            Long l2 = (Long) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 15, LongSerializer.INSTANCE, null);
            String str21 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 16, StringSerializer.INSTANCE, null);
            String str22 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 17, StringSerializer.INSTANCE, null);
            String strDecodeStringElement4 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 18);
            str9 = str22;
            List list5 = (List) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 19, (DeserializationStrategy) lazyArr[19].getValue(), null);
            MyDramaListAPI.Trailer trailer2 = (MyDramaListAPI.Trailer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 20, MyDramaListAPI$Trailer$$serializer.INSTANCE, null);
            long jDecodeLongElement4 = compositeDecoderBeginStructure.decodeLongElement(serialDescriptor, 21);
            long jDecodeLongElement5 = compositeDecoderBeginStructure.decodeLongElement(serialDescriptor, 22);
            long jDecodeLongElement6 = compositeDecoderBeginStructure.decodeLongElement(serialDescriptor, 23);
            long jDecodeLongElement7 = compositeDecoderBeginStructure.decodeLongElement(serialDescriptor, 24);
            long jDecodeLongElement8 = compositeDecoderBeginStructure.decodeLongElement(serialDescriptor, 25);
            long jDecodeLongElement9 = compositeDecoderBeginStructure.decodeLongElement(serialDescriptor, 26);
            long jDecodeLongElement10 = compositeDecoderBeginStructure.decodeLongElement(serialDescriptor, 27);
            String strDecodeStringElement5 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 28);
            String strDecodeStringElement6 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 29);
            boolean zDecodeBooleanElement = compositeDecoderBeginStructure.decodeBooleanElement(serialDescriptor, 30);
            str13 = strDecodeStringElement5;
            str4 = str21;
            list3 = (List) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 31, (DeserializationStrategy) lazyArr[31].getValue(), null);
            l = l2;
            jDecodeLongElement = compositeDecoderBeginStructure.decodeLongElement(serialDescriptor, 32);
            str14 = strDecodeStringElement6;
            z = zDecodeBooleanElement;
            i = -1;
            str2 = strDecodeStringElement2;
            str12 = strDecodeStringElement;
            str6 = str18;
            i3 = 1;
            str = str19;
            str11 = str20;
            str5 = str17;
            str10 = str15;
            j = jDecodeLongElement2;
            i2 = iDecodeIntElement;
            str7 = strDecodeStringElement3;
            str3 = str16;
            str8 = strDecodeStringElement4;
            list2 = list5;
            j2 = jDecodeLongElement3;
            images = images2;
            list = list4;
            d = dDecodeDoubleElement;
            trailer = trailer2;
            j7 = jDecodeLongElement10;
            j6 = jDecodeLongElement9;
            j5 = jDecodeLongElement8;
            j9 = jDecodeLongElement7;
            j8 = jDecodeLongElement6;
            j3 = jDecodeLongElement4;
            j4 = jDecodeLongElement5;
        } else {
            int i5 = 32;
            boolean z2 = false;
            int iDecodeIntElement2 = 0;
            int i6 = 0;
            String str23 = null;
            String str24 = null;
            String str25 = null;
            Long l3 = null;
            MyDramaListAPI.Images images3 = null;
            String str26 = null;
            String str27 = null;
            String str28 = null;
            List list6 = null;
            String str29 = null;
            String str30 = null;
            String str31 = null;
            String str32 = null;
            String str33 = null;
            List list7 = null;
            MyDramaListAPI.Trailer trailer3 = null;
            List list8 = null;
            long jDecodeLongElement11 = 0;
            long jDecodeLongElement12 = 0;
            long jDecodeLongElement13 = 0;
            long jDecodeLongElement14 = 0;
            long jDecodeLongElement15 = 0;
            long jDecodeLongElement16 = 0;
            long jDecodeLongElement17 = 0;
            long jDecodeLongElement18 = 0;
            long jDecodeLongElement19 = 0;
            long jDecodeLongElement20 = 0;
            double dDecodeDoubleElement2 = 0.0d;
            boolean z3 = true;
            int i7 = 0;
            String str34 = null;
            String str35 = null;
            String str36 = null;
            while (true) {
                boolean z4 = z2;
                if (z3) {
                    int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(serialDescriptor);
                    switch (iDecodeElementIndex) {
                        case -1:
                            str24 = str24;
                            trailer3 = trailer3;
                            list8 = list8;
                            list7 = list7;
                            Unit unit = Unit.INSTANCE;
                            z3 = false;
                            str24 = str24;
                            list7 = list7;
                            z2 = z4;
                            list8 = list8;
                            i5 = 32;
                            trailer3 = trailer3;
                            break;
                        case 0:
                            str24 = str24;
                            trailer3 = trailer3;
                            list8 = list8;
                            list7 = list7;
                            jDecodeLongElement11 = compositeDecoderBeginStructure.decodeLongElement(serialDescriptor, 0);
                            i7 |= 1;
                            Unit unit2 = Unit.INSTANCE;
                            str24 = str24;
                            list7 = list7;
                            z2 = z4;
                            list8 = list8;
                            i5 = 32;
                            trailer3 = trailer3;
                            break;
                        case 1:
                            trailer3 = trailer3;
                            list8 = list8;
                            list7 = list7;
                            String strDecodeStringElement7 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 1);
                            i7 |= 2;
                            Unit unit3 = Unit.INSTANCE;
                            str24 = strDecodeStringElement7;
                            str24 = str24;
                            list7 = list7;
                            z2 = z4;
                            list8 = list8;
                            i5 = 32;
                            trailer3 = trailer3;
                            break;
                        case 2:
                            str24 = str24;
                            trailer3 = trailer3;
                            list8 = list8;
                            list7 = list7;
                            String strDecodeStringElement8 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 2);
                            i7 |= 4;
                            Unit unit4 = Unit.INSTANCE;
                            str35 = strDecodeStringElement8;
                            str24 = str24;
                            list7 = list7;
                            z2 = z4;
                            list8 = list8;
                            i5 = 32;
                            trailer3 = trailer3;
                            break;
                        case 3:
                            str24 = str24;
                            trailer3 = trailer3;
                            list8 = list8;
                            list7 = list7;
                            String strDecodeStringElement9 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 3);
                            i7 |= 8;
                            Unit unit5 = Unit.INSTANCE;
                            str36 = strDecodeStringElement9;
                            str24 = str24;
                            list7 = list7;
                            z2 = z4;
                            list8 = list8;
                            i5 = 32;
                            trailer3 = trailer3;
                            break;
                        case 4:
                            iDecodeIntElement2 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 4);
                            i7 |= 16;
                            Unit unit6 = Unit.INSTANCE;
                            str24 = str24;
                            list7 = list7;
                            z2 = z4;
                            list8 = list8;
                            i5 = 32;
                            trailer3 = trailer3;
                            break;
                        case 5:
                            jDecodeLongElement12 = compositeDecoderBeginStructure.decodeLongElement(serialDescriptor, 5);
                            i7 |= 32;
                            Unit unit7 = Unit.INSTANCE;
                            str24 = str24;
                            list7 = list7;
                            z2 = z4;
                            list8 = list8;
                            i5 = 32;
                            trailer3 = trailer3;
                            break;
                        case 6:
                            dDecodeDoubleElement2 = compositeDecoderBeginStructure.decodeDoubleElement(serialDescriptor, 6);
                            i7 |= 64;
                            Unit unit8 = Unit.INSTANCE;
                            str24 = str24;
                            list7 = list7;
                            z2 = z4;
                            list8 = list8;
                            i5 = 32;
                            trailer3 = trailer3;
                            break;
                        case 7:
                            str34 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 7, StringSerializer.INSTANCE, str34);
                            i7 |= 128;
                            Unit unit9 = Unit.INSTANCE;
                            str24 = str24;
                            list7 = list7;
                            z2 = z4;
                            list8 = list8;
                            i5 = 32;
                            trailer3 = trailer3;
                            break;
                        case 8:
                            str23 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 8, StringSerializer.INSTANCE, str23);
                            i7 |= 256;
                            Unit unit10 = Unit.INSTANCE;
                            str24 = str24;
                            list7 = list7;
                            z2 = z4;
                            list8 = list8;
                            i5 = 32;
                            trailer3 = trailer3;
                            break;
                        case 9:
                            str24 = str24;
                            trailer3 = trailer3;
                            list8 = list8;
                            list7 = list7;
                            String str37 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 9, StringSerializer.INSTANCE, str28);
                            i7 |= 512;
                            Unit unit11 = Unit.INSTANCE;
                            str28 = str37;
                            str24 = str24;
                            list7 = list7;
                            z2 = z4;
                            list8 = list8;
                            i5 = 32;
                            trailer3 = trailer3;
                            break;
                        case 10:
                            str24 = str24;
                            trailer3 = trailer3;
                            list8 = list8;
                            list7 = list7;
                            String str38 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 10, StringSerializer.INSTANCE, str29);
                            i7 |= 1024;
                            Unit unit12 = Unit.INSTANCE;
                            str29 = str38;
                            str24 = str24;
                            list7 = list7;
                            z2 = z4;
                            list8 = list8;
                            i5 = 32;
                            trailer3 = trailer3;
                            break;
                        case 11:
                            str24 = str24;
                            trailer3 = trailer3;
                            list8 = list8;
                            list7 = list7;
                            String str39 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 11, StringSerializer.INSTANCE, str27);
                            i7 |= 2048;
                            Unit unit13 = Unit.INSTANCE;
                            str27 = str39;
                            str24 = str24;
                            list7 = list7;
                            z2 = z4;
                            list8 = list8;
                            i5 = 32;
                            trailer3 = trailer3;
                            break;
                        case 12:
                            str24 = str24;
                            trailer3 = trailer3;
                            list8 = list8;
                            list7 = list7;
                            String str40 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 12, StringSerializer.INSTANCE, str26);
                            i7 |= 4096;
                            Unit unit14 = Unit.INSTANCE;
                            str26 = str40;
                            str24 = str24;
                            list7 = list7;
                            z2 = z4;
                            list8 = list8;
                            i5 = 32;
                            trailer3 = trailer3;
                            break;
                        case 13:
                            str24 = str24;
                            trailer3 = trailer3;
                            list8 = list8;
                            list7 = list7;
                            MyDramaListAPI.Images images4 = (MyDramaListAPI.Images) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 13, MyDramaListAPI$Images$$serializer.INSTANCE, images3);
                            i7 |= 8192;
                            Unit unit15 = Unit.INSTANCE;
                            images3 = images4;
                            str24 = str24;
                            list7 = list7;
                            z2 = z4;
                            list8 = list8;
                            i5 = 32;
                            trailer3 = trailer3;
                            break;
                        case 14:
                            str24 = str24;
                            trailer3 = trailer3;
                            list8 = list8;
                            list7 = list7;
                            List list9 = (List) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 14, (DeserializationStrategy) lazyArr[14].getValue(), list6);
                            i7 |= 16384;
                            Unit unit16 = Unit.INSTANCE;
                            list6 = list9;
                            str24 = str24;
                            list7 = list7;
                            z2 = z4;
                            list8 = list8;
                            i5 = 32;
                            trailer3 = trailer3;
                            break;
                        case 15:
                            str24 = str24;
                            trailer3 = trailer3;
                            list8 = list8;
                            list7 = list7;
                            Long l4 = (Long) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 15, LongSerializer.INSTANCE, l3);
                            i7 |= 32768;
                            Unit unit17 = Unit.INSTANCE;
                            l3 = l4;
                            str24 = str24;
                            list7 = list7;
                            z2 = z4;
                            list8 = list8;
                            i5 = 32;
                            trailer3 = trailer3;
                            break;
                        case 16:
                            trailer3 = trailer3;
                            list8 = list8;
                            list7 = list7;
                            String str41 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 16, StringSerializer.INSTANCE, str25);
                            i7 |= 65536;
                            Unit unit18 = Unit.INSTANCE;
                            str25 = str41;
                            str24 = str24;
                            list7 = list7;
                            z2 = z4;
                            list8 = list8;
                            i5 = 32;
                            trailer3 = trailer3;
                            break;
                        case 17:
                            trailer3 = trailer3;
                            list8 = list8;
                            list7 = list7;
                            String str42 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 17, StringSerializer.INSTANCE, str33);
                            i7 |= 131072;
                            Unit unit19 = Unit.INSTANCE;
                            str33 = str42;
                            str24 = str24;
                            list7 = list7;
                            z2 = z4;
                            list8 = list8;
                            i5 = 32;
                            trailer3 = trailer3;
                            break;
                        case 18:
                            trailer3 = trailer3;
                            list8 = list8;
                            String strDecodeStringElement10 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 18);
                            i7 |= 262144;
                            Unit unit20 = Unit.INSTANCE;
                            str30 = strDecodeStringElement10;
                            list7 = list7;
                            str24 = str24;
                            list7 = list7;
                            z2 = z4;
                            list8 = list8;
                            i5 = 32;
                            trailer3 = trailer3;
                            break;
                        case 19:
                            list8 = list8;
                            trailer3 = trailer3;
                            List list10 = (List) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 19, (DeserializationStrategy) lazyArr[19].getValue(), list7);
                            i7 |= 524288;
                            Unit unit21 = Unit.INSTANCE;
                            list7 = list10;
                            str24 = str24;
                            list7 = list7;
                            z2 = z4;
                            list8 = list8;
                            i5 = 32;
                            trailer3 = trailer3;
                            break;
                        case 20:
                            list8 = list8;
                            MyDramaListAPI.Trailer trailer4 = (MyDramaListAPI.Trailer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 20, MyDramaListAPI$Trailer$$serializer.INSTANCE, trailer3);
                            i7 |= 1048576;
                            Unit unit22 = Unit.INSTANCE;
                            trailer3 = trailer4;
                            list7 = list7;
                            str24 = str24;
                            list7 = list7;
                            z2 = z4;
                            list8 = list8;
                            i5 = 32;
                            trailer3 = trailer3;
                            break;
                        case 21:
                            jDecodeLongElement13 = compositeDecoderBeginStructure.decodeLongElement(serialDescriptor, 21);
                            i4 = 2097152;
                            i7 |= i4;
                            Unit unit23 = Unit.INSTANCE;
                            list8 = list8;
                            list7 = list7;
                            str24 = str24;
                            list7 = list7;
                            z2 = z4;
                            list8 = list8;
                            i5 = 32;
                            trailer3 = trailer3;
                            break;
                        case 22:
                            jDecodeLongElement14 = compositeDecoderBeginStructure.decodeLongElement(serialDescriptor, 22);
                            i4 = 4194304;
                            i7 |= i4;
                            Unit unit24 = Unit.INSTANCE;
                            list8 = list8;
                            list7 = list7;
                            str24 = str24;
                            list7 = list7;
                            z2 = z4;
                            list8 = list8;
                            i5 = 32;
                            trailer3 = trailer3;
                            break;
                        case 23:
                            jDecodeLongElement15 = compositeDecoderBeginStructure.decodeLongElement(serialDescriptor, 23);
                            i4 = 8388608;
                            i7 |= i4;
                            Unit unit25 = Unit.INSTANCE;
                            list8 = list8;
                            list7 = list7;
                            str24 = str24;
                            list7 = list7;
                            z2 = z4;
                            list8 = list8;
                            i5 = 32;
                            trailer3 = trailer3;
                            break;
                        case 24:
                            jDecodeLongElement16 = compositeDecoderBeginStructure.decodeLongElement(serialDescriptor, 24);
                            i4 = 16777216;
                            i7 |= i4;
                            Unit unit26 = Unit.INSTANCE;
                            list8 = list8;
                            list7 = list7;
                            str24 = str24;
                            list7 = list7;
                            z2 = z4;
                            list8 = list8;
                            i5 = 32;
                            trailer3 = trailer3;
                            break;
                        case 25:
                            jDecodeLongElement17 = compositeDecoderBeginStructure.decodeLongElement(serialDescriptor, 25);
                            i4 = 33554432;
                            i7 |= i4;
                            Unit unit27 = Unit.INSTANCE;
                            list8 = list8;
                            list7 = list7;
                            str24 = str24;
                            list7 = list7;
                            z2 = z4;
                            list8 = list8;
                            i5 = 32;
                            trailer3 = trailer3;
                            break;
                        case 26:
                            jDecodeLongElement18 = compositeDecoderBeginStructure.decodeLongElement(serialDescriptor, 26);
                            i4 = 67108864;
                            i7 |= i4;
                            Unit unit28 = Unit.INSTANCE;
                            list8 = list8;
                            list7 = list7;
                            str24 = str24;
                            list7 = list7;
                            z2 = z4;
                            list8 = list8;
                            i5 = 32;
                            trailer3 = trailer3;
                            break;
                        case 27:
                            jDecodeLongElement19 = compositeDecoderBeginStructure.decodeLongElement(serialDescriptor, 27);
                            i4 = C.BUFFER_FLAG_FIRST_SAMPLE;
                            i7 |= i4;
                            Unit unit29 = Unit.INSTANCE;
                            list8 = list8;
                            list7 = list7;
                            str24 = str24;
                            list7 = list7;
                            z2 = z4;
                            list8 = list8;
                            i5 = 32;
                            trailer3 = trailer3;
                            break;
                        case 28:
                            str24 = str24;
                            list8 = list8;
                            String strDecodeStringElement11 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 28);
                            i7 |= 268435456;
                            Unit unit30 = Unit.INSTANCE;
                            str31 = strDecodeStringElement11;
                            list8 = list8;
                            list7 = list7;
                            str24 = str24;
                            list7 = list7;
                            z2 = z4;
                            list8 = list8;
                            i5 = 32;
                            trailer3 = trailer3;
                            break;
                        case 29:
                            str24 = str24;
                            list8 = list8;
                            String strDecodeStringElement12 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 29);
                            i7 |= 536870912;
                            Unit unit31 = Unit.INSTANCE;
                            str32 = strDecodeStringElement12;
                            list8 = list8;
                            list7 = list7;
                            str24 = str24;
                            list7 = list7;
                            z2 = z4;
                            list8 = list8;
                            i5 = 32;
                            trailer3 = trailer3;
                            break;
                        case 30:
                            str24 = str24;
                            list8 = list8;
                            boolean zDecodeBooleanElement2 = compositeDecoderBeginStructure.decodeBooleanElement(serialDescriptor, 30);
                            i7 |= 1073741824;
                            Unit unit32 = Unit.INSTANCE;
                            z4 = zDecodeBooleanElement2;
                            list8 = list8;
                            list7 = list7;
                            str24 = str24;
                            list7 = list7;
                            z2 = z4;
                            list8 = list8;
                            i5 = 32;
                            trailer3 = trailer3;
                            break;
                        case 31:
                            str24 = str24;
                            List list11 = (List) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 31, (DeserializationStrategy) lazyArr[31].getValue(), list8);
                            i7 |= Integer.MIN_VALUE;
                            Unit unit33 = Unit.INSTANCE;
                            list8 = list11;
                            list7 = list7;
                            str24 = str24;
                            list7 = list7;
                            z2 = z4;
                            list8 = list8;
                            i5 = 32;
                            trailer3 = trailer3;
                            break;
                        case 32:
                            jDecodeLongElement20 = compositeDecoderBeginStructure.decodeLongElement(serialDescriptor, i5);
                            Unit unit34 = Unit.INSTANCE;
                            str24 = str24;
                            list8 = list8;
                            i6 = 1;
                            list7 = list7;
                            str24 = str24;
                            list7 = list7;
                            z2 = z4;
                            list8 = list8;
                            i5 = 32;
                            trailer3 = trailer3;
                            break;
                        default:
                            throw new UnknownFieldException(iDecodeElementIndex);
                    }
                } else {
                    String str43 = str24;
                    String str44 = str35;
                    str = str27;
                    str2 = str44;
                    str3 = str23;
                    i = i7;
                    str4 = str25;
                    l = l3;
                    images = images3;
                    str5 = str28;
                    list = list6;
                    str6 = str29;
                    trailer = trailer3;
                    str7 = str36;
                    str8 = str30;
                    i2 = iDecodeIntElement2;
                    j = jDecodeLongElement11;
                    j2 = jDecodeLongElement12;
                    j3 = jDecodeLongElement13;
                    j4 = jDecodeLongElement14;
                    str9 = str33;
                    list2 = list7;
                    j5 = jDecodeLongElement17;
                    j6 = jDecodeLongElement18;
                    j7 = jDecodeLongElement19;
                    jDecodeLongElement = jDecodeLongElement20;
                    i3 = i6;
                    z = z4;
                    list3 = list8;
                    str10 = str34;
                    str11 = str26;
                    str12 = str43;
                    str13 = str31;
                    str14 = str32;
                    d = dDecodeDoubleElement2;
                    j8 = jDecodeLongElement15;
                    j9 = jDecodeLongElement16;
                }
            }
        }
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return new MyDramaListAPI.Media(i, i3, j, str12, str2, str7, i2, j2, d, str10, str3, str5, str6, str, str11, images, list, l, str4, str9, str8, list2, trailer, j3, j4, j8, j9, j5, j6, j7, str13, str14, z, list3, jDecodeLongElement, (SerializationConstructorMarker) null);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(Encoder encoder, MyDramaListAPI.Media value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        MyDramaListAPI.Media.write$Self$library(value, compositeEncoderBeginStructure, serialDescriptor);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public /* bridge */ KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.CC.$default$typeParametersSerializers(this);
    }
}
