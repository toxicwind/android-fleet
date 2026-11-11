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
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.StringSerializer;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: KitsuApi.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0019\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006H\u0086\u0080\u0004¢\u0006\u0002\u0010\bJ\u0012\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0086\u0080\u0004J\u001a\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0086\u0080\u0004R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"com/lagradost/cloudstream3/syncproviders/providers/KitsuApi.KitsuNode.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuNode;", "<init>", "()V", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", ES6Iterator.VALUE_PROPERTY, "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly")
public final /* synthetic */ class KitsuApi$KitsuNode$$serializer implements GeneratedSerializer<KitsuApi.KitsuNode> {
    public static final KitsuApi$KitsuNode$$serializer INSTANCE;
    private static final SerialDescriptor descriptor;

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        KitsuApi$KitsuNode$$serializer kitsuApi$KitsuNode$$serializer = new KitsuApi$KitsuNode$$serializer();
        INSTANCE = kitsuApi$KitsuNode$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.lagradost.cloudstream3.syncproviders.providers.KitsuApi.KitsuNode", kitsuApi$KitsuNode$$serializer, 4);
        pluginGeneratedSerialDescriptor.addElement("id", false);
        pluginGeneratedSerialDescriptor.addElement("attributes", false);
        pluginGeneratedSerialDescriptor.addElement("relationships", false);
        pluginGeneratedSerialDescriptor.addElement("anime", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private KitsuApi$KitsuNode$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public final KSerializer<?>[] childSerializers() {
        return new KSerializer[]{StringSerializer.INSTANCE, KitsuApi$KitsuNodeAttributes$$serializer.INSTANCE, BuiltinSerializersKt.getNullable(KitsuApi$KitsuRelationships$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(KitsuApi$KitsuAnimeData$$serializer.INSTANCE)};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public final KitsuApi.KitsuNode deserialize(Decoder decoder) {
        int i;
        String str;
        KitsuApi.KitsuNodeAttributes kitsuNodeAttributes;
        KitsuApi.KitsuRelationships kitsuRelationships;
        KitsuApi.KitsuAnimeData kitsuAnimeData;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        String strDecodeStringElement = null;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            String strDecodeStringElement2 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 0);
            KitsuApi.KitsuNodeAttributes kitsuNodeAttributes2 = (KitsuApi.KitsuNodeAttributes) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 1, KitsuApi$KitsuNodeAttributes$$serializer.INSTANCE, null);
            KitsuApi.KitsuRelationships kitsuRelationships2 = (KitsuApi.KitsuRelationships) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 2, KitsuApi$KitsuRelationships$$serializer.INSTANCE, null);
            str = strDecodeStringElement2;
            kitsuAnimeData = (KitsuApi.KitsuAnimeData) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 3, KitsuApi$KitsuAnimeData$$serializer.INSTANCE, null);
            kitsuRelationships = kitsuRelationships2;
            kitsuNodeAttributes = kitsuNodeAttributes2;
            i = 15;
        } else {
            boolean z = true;
            int i2 = 0;
            KitsuApi.KitsuNodeAttributes kitsuNodeAttributes3 = null;
            KitsuApi.KitsuRelationships kitsuRelationships3 = null;
            KitsuApi.KitsuAnimeData kitsuAnimeData2 = null;
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(serialDescriptor);
                if (iDecodeElementIndex == -1) {
                    z = false;
                } else if (iDecodeElementIndex == 0) {
                    strDecodeStringElement = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 0);
                    i2 |= 1;
                } else if (iDecodeElementIndex == 1) {
                    kitsuNodeAttributes3 = (KitsuApi.KitsuNodeAttributes) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 1, KitsuApi$KitsuNodeAttributes$$serializer.INSTANCE, kitsuNodeAttributes3);
                    i2 |= 2;
                } else if (iDecodeElementIndex == 2) {
                    kitsuRelationships3 = (KitsuApi.KitsuRelationships) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 2, KitsuApi$KitsuRelationships$$serializer.INSTANCE, kitsuRelationships3);
                    i2 |= 4;
                } else {
                    if (iDecodeElementIndex != 3) {
                        throw new UnknownFieldException(iDecodeElementIndex);
                    }
                    kitsuAnimeData2 = (KitsuApi.KitsuAnimeData) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 3, KitsuApi$KitsuAnimeData$$serializer.INSTANCE, kitsuAnimeData2);
                    i2 |= 8;
                }
            }
            i = i2;
            str = strDecodeStringElement;
            kitsuNodeAttributes = kitsuNodeAttributes3;
            kitsuRelationships = kitsuRelationships3;
            kitsuAnimeData = kitsuAnimeData2;
        }
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return new KitsuApi.KitsuNode(i, str, kitsuNodeAttributes, kitsuRelationships, kitsuAnimeData, null);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(Encoder encoder, KitsuApi.KitsuNode value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        KitsuApi.KitsuNode.write$Self$app(value, compositeEncoderBeginStructure, serialDescriptor);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public /* bridge */ KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.CC.$default$typeParametersSerializers(this);
    }
}
