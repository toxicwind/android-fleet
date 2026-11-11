package com.lagradost.cloudstream3.plugins;

import com.lagradost.cloudstream3.ui.settings.extensions.RepositoryData;
import com.lagradost.cloudstream3.ui.settings.extensions.RepositoryData$$serializer;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: RepositoryManager.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0019\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006H\u0086\u0080\u0004¢\u0006\u0002\u0010\bJ\u0012\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0086\u0080\u0004J\u001a\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0086\u0080\u0004R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"com/lagradost/cloudstream3/plugins/PluginWrapper.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/lagradost/cloudstream3/plugins/PluginWrapper;", "<init>", "()V", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", ES6Iterator.VALUE_PROPERTY, "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly")
public final /* synthetic */ class PluginWrapper$$serializer implements GeneratedSerializer<PluginWrapper> {
    public static final PluginWrapper$$serializer INSTANCE;
    private static final SerialDescriptor descriptor;

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        PluginWrapper$$serializer pluginWrapper$$serializer = new PluginWrapper$$serializer();
        INSTANCE = pluginWrapper$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.lagradost.cloudstream3.plugins.PluginWrapper", pluginWrapper$$serializer, 3);
        pluginGeneratedSerialDescriptor.addElement("repository", false);
        pluginGeneratedSerialDescriptor.addElement("repositoryData", false);
        pluginGeneratedSerialDescriptor.addElement("plugin", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private PluginWrapper$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public final KSerializer<?>[] childSerializers() {
        return new KSerializer[]{Repository$$serializer.INSTANCE, RepositoryData$$serializer.INSTANCE, SitePlugin$$serializer.INSTANCE};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public final PluginWrapper deserialize(Decoder decoder) {
        int i;
        Repository repository;
        RepositoryData repositoryData;
        SitePlugin sitePlugin;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        Repository repository2 = null;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            Repository repository3 = (Repository) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 0, Repository$$serializer.INSTANCE, null);
            RepositoryData repositoryData2 = (RepositoryData) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 1, RepositoryData$$serializer.INSTANCE, null);
            repository = repository3;
            sitePlugin = (SitePlugin) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 2, SitePlugin$$serializer.INSTANCE, null);
            repositoryData = repositoryData2;
            i = 7;
        } else {
            boolean z = true;
            int i2 = 0;
            RepositoryData repositoryData3 = null;
            SitePlugin sitePlugin2 = null;
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(serialDescriptor);
                if (iDecodeElementIndex == -1) {
                    z = false;
                } else if (iDecodeElementIndex == 0) {
                    repository2 = (Repository) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 0, Repository$$serializer.INSTANCE, repository2);
                    i2 |= 1;
                } else if (iDecodeElementIndex == 1) {
                    repositoryData3 = (RepositoryData) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 1, RepositoryData$$serializer.INSTANCE, repositoryData3);
                    i2 |= 2;
                } else {
                    if (iDecodeElementIndex != 2) {
                        throw new UnknownFieldException(iDecodeElementIndex);
                    }
                    sitePlugin2 = (SitePlugin) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 2, SitePlugin$$serializer.INSTANCE, sitePlugin2);
                    i2 |= 4;
                }
            }
            i = i2;
            repository = repository2;
            repositoryData = repositoryData3;
            sitePlugin = sitePlugin2;
        }
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return new PluginWrapper(i, repository, repositoryData, sitePlugin, null);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(Encoder encoder, PluginWrapper value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        PluginWrapper.write$Self$app(value, compositeEncoderBeginStructure, serialDescriptor);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public /* bridge */ KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.CC.$default$typeParametersSerializers(this);
    }
}
