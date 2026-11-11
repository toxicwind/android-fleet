package com.lagradost.cloudstream3.utils;

import java.util.Map;
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
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: BackupUtils.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0019\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006H\u0086\u0080\u0004¢\u0006\u0002\u0010\bJ\u0012\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0086\u0080\u0004J\u001a\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0086\u0080\u0004R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"com/lagradost/cloudstream3/utils/BackupUtils.BackupVars.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/lagradost/cloudstream3/utils/BackupUtils$BackupVars;", "<init>", "()V", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", ES6Iterator.VALUE_PROPERTY, "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly")
public final /* synthetic */ class BackupUtils$BackupVars$$serializer implements GeneratedSerializer<BackupUtils.BackupVars> {
    public static final BackupUtils$BackupVars$$serializer INSTANCE;
    private static final SerialDescriptor descriptor;

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        BackupUtils$BackupVars$$serializer backupUtils$BackupVars$$serializer = new BackupUtils$BackupVars$$serializer();
        INSTANCE = backupUtils$BackupVars$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.lagradost.cloudstream3.utils.BackupUtils.BackupVars", backupUtils$BackupVars$$serializer, 6);
        pluginGeneratedSerialDescriptor.addElement("_Bool", false);
        pluginGeneratedSerialDescriptor.addElement("_Int", false);
        pluginGeneratedSerialDescriptor.addElement("_String", false);
        pluginGeneratedSerialDescriptor.addElement("_Float", false);
        pluginGeneratedSerialDescriptor.addElement("_Long", false);
        pluginGeneratedSerialDescriptor.addElement("_StringSet", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private BackupUtils$BackupVars$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public final KSerializer<?>[] childSerializers() {
        Lazy[] lazyArr = BackupUtils.BackupVars.$childSerializers;
        return new KSerializer[]{BuiltinSerializersKt.getNullable((KSerializer) lazyArr[0].getValue()), BuiltinSerializersKt.getNullable((KSerializer) lazyArr[1].getValue()), BuiltinSerializersKt.getNullable((KSerializer) lazyArr[2].getValue()), BuiltinSerializersKt.getNullable((KSerializer) lazyArr[3].getValue()), BuiltinSerializersKt.getNullable((KSerializer) lazyArr[4].getValue()), BuiltinSerializersKt.getNullable((KSerializer) lazyArr[5].getValue())};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public final BackupUtils.BackupVars deserialize(Decoder decoder) {
        Map map;
        Map map2;
        Map map3;
        Map map4;
        Map map5;
        Map map6;
        int i;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        Lazy[] lazyArr = BackupUtils.BackupVars.$childSerializers;
        int i2 = 5;
        int i3 = 0;
        Map map7 = null;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            Map map8 = (Map) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 0, (DeserializationStrategy) lazyArr[0].getValue(), null);
            Map map9 = (Map) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 1, (DeserializationStrategy) lazyArr[1].getValue(), null);
            Map map10 = (Map) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 2, (DeserializationStrategy) lazyArr[2].getValue(), null);
            Map map11 = (Map) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 3, (DeserializationStrategy) lazyArr[3].getValue(), null);
            Map map12 = (Map) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 4, (DeserializationStrategy) lazyArr[4].getValue(), null);
            map2 = (Map) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 5, (DeserializationStrategy) lazyArr[5].getValue(), null);
            map5 = map8;
            i = 63;
            map4 = map11;
            map = map12;
            map3 = map10;
            map6 = map9;
        } else {
            int i4 = 1;
            int i5 = 0;
            Map map13 = null;
            Map map14 = null;
            Map map15 = null;
            Map map16 = null;
            Map map17 = null;
            while (i4 != 0) {
                int i6 = i3;
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(serialDescriptor);
                switch (iDecodeElementIndex) {
                    case -1:
                        i3 = i6;
                        i4 = i3;
                        i2 = 5;
                        break;
                    case 0:
                        map7 = (Map) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, i6, (DeserializationStrategy) lazyArr[i6].getValue(), map7);
                        i5 |= 1;
                        i3 = i6;
                        i2 = 5;
                        break;
                    case 1:
                        map13 = (Map) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 1, (DeserializationStrategy) lazyArr[1].getValue(), map13);
                        i5 |= 2;
                        i3 = i6;
                        break;
                    case 2:
                        map14 = (Map) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 2, (DeserializationStrategy) lazyArr[2].getValue(), map14);
                        i5 |= 4;
                        i3 = i6;
                        break;
                    case 3:
                        map15 = (Map) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 3, (DeserializationStrategy) lazyArr[3].getValue(), map15);
                        i5 |= 8;
                        i3 = i6;
                        break;
                    case 4:
                        map16 = (Map) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 4, (DeserializationStrategy) lazyArr[4].getValue(), map16);
                        i5 |= 16;
                        i3 = i6;
                        break;
                    case 5:
                        map17 = (Map) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, i2, (DeserializationStrategy) lazyArr[i2].getValue(), map17);
                        i5 |= 32;
                        i3 = i6;
                        break;
                    default:
                        throw new UnknownFieldException(iDecodeElementIndex);
                }
            }
            map = map16;
            map2 = map17;
            map3 = map14;
            map4 = map15;
            map5 = map7;
            map6 = map13;
            i = i5;
        }
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return new BackupUtils.BackupVars(i, map5, map6, map3, map4, map, map2, null);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(Encoder encoder, BackupUtils.BackupVars value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        BackupUtils.BackupVars.write$Self$app(value, compositeEncoderBeginStructure, serialDescriptor);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public /* bridge */ KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.CC.$default$typeParametersSerializers(this);
    }
}
