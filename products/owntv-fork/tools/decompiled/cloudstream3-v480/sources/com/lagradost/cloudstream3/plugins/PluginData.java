package com.lagradost.cloudstream3.plugins;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import java.io.File;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* JADX INFO: compiled from: PluginManager.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 22\u00020\u0001:\u000212B;\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0001\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bBI\b\u0010\u0012\u0006\u0010\f\u001a\u00020\t\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\n\u0010\u000fJ\b\u0010\u001d\u001a\u00020\u001eH\u0007J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0006HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\tHÆ\u0003J=\u0010$\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00062\b\b\u0003\u0010\u0007\u001a\u00020\u00032\b\b\u0003\u0010\b\u001a\u00020\tHÆ\u0001J\u0014\u0010%\u001a\u00020\u00062\b\u0010&\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010'\u001a\u00020\tHÖ\u0081\u0004J\n\u0010(\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u00002\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0001¢\u0006\u0002\b0R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u0011\u001a\u0004\b\u0015\u0010\u0013R\u001c\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u0011\u001a\u0004\b\u0005\u0010\u0017R\u001c\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u0011\u001a\u0004\b\u0019\u0010\u0013R\u001c\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\u0011\u001a\u0004\b\u001b\u0010\u001c¨\u00063"}, d2 = {"Lcom/lagradost/cloudstream3/plugins/PluginData;", "", "internalName", "", ImagesContract.URL, "isOnline", "", "filePath", "version", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;I)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;ZLjava/lang/String;ILkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getInternalName$annotations", "()V", "getInternalName", "()Ljava/lang/String;", "getUrl$annotations", "getUrl", "isOnline$annotations", "()Z", "getFilePath$annotations", "getFilePath", "getVersion$annotations", "getVersion", "()I", "toSitePlugin", "Lcom/lagradost/cloudstream3/plugins/SitePlugin;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
@Serializable
public final /* data */ class PluginData {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @JsonProperty("filePath")
    private final String filePath;

    @JsonProperty("internalName")
    private final String internalName;

    @JsonProperty("isOnline")
    private final boolean isOnline;

    @JsonProperty(ImagesContract.URL)
    private final String url;

    @JsonProperty("version")
    private final int version;

    public static /* synthetic */ PluginData copy$default(PluginData pluginData, String str, String str2, boolean z, String str3, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = pluginData.internalName;
        }
        if ((i2 & 2) != 0) {
            str2 = pluginData.url;
        }
        if ((i2 & 4) != 0) {
            z = pluginData.isOnline;
        }
        if ((i2 & 8) != 0) {
            str3 = pluginData.filePath;
        }
        if ((i2 & 16) != 0) {
            i = pluginData.version;
        }
        int i3 = i;
        boolean z2 = z;
        return pluginData.copy(str, str2, z2, str3, i3);
    }

    @SerialName("filePath")
    public static /* synthetic */ void getFilePath$annotations() {
    }

    @SerialName("internalName")
    public static /* synthetic */ void getInternalName$annotations() {
    }

    @SerialName(ImagesContract.URL)
    public static /* synthetic */ void getUrl$annotations() {
    }

    @SerialName("version")
    public static /* synthetic */ void getVersion$annotations() {
    }

    @SerialName("isOnline")
    public static /* synthetic */ void isOnline$annotations() {
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getInternalName() {
        return this.internalName;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getIsOnline() {
        return this.isOnline;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getFilePath() {
        return this.filePath;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getVersion() {
        return this.version;
    }

    public final PluginData copy(@JsonProperty("internalName") String internalName, @JsonProperty(ImagesContract.URL) String url, @JsonProperty("isOnline") boolean isOnline, @JsonProperty("filePath") String filePath, @JsonProperty("version") int version) {
        Intrinsics.checkNotNullParameter(internalName, "internalName");
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        return new PluginData(internalName, url, isOnline, filePath, version);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PluginData)) {
            return false;
        }
        PluginData pluginData = (PluginData) other;
        return Intrinsics.areEqual(this.internalName, pluginData.internalName) && Intrinsics.areEqual(this.url, pluginData.url) && this.isOnline == pluginData.isOnline && Intrinsics.areEqual(this.filePath, pluginData.filePath) && this.version == pluginData.version;
    }

    public int hashCode() {
        int iHashCode = this.internalName.hashCode() * 31;
        String str = this.url;
        return ((((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.isOnline)) * 31) + this.filePath.hashCode()) * 31) + this.version;
    }

    public String toString() {
        return "PluginData(internalName=" + this.internalName + ", url=" + this.url + ", isOnline=" + this.isOnline + ", filePath=" + this.filePath + ", version=" + this.version + ')';
    }

    /* JADX INFO: compiled from: PluginManager.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/plugins/PluginData$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/plugins/PluginData;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<PluginData> serializer() {
            return PluginData$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ PluginData(int i, String str, String str2, boolean z, String str3, int i2, SerializationConstructorMarker serializationConstructorMarker) {
        if (31 != (i & 31)) {
            PluginExceptionsKt.throwMissingFieldException(i, 31, PluginData$$serializer.INSTANCE.getDescriptor());
        }
        this.internalName = str;
        this.url = str2;
        this.isOnline = z;
        this.filePath = str3;
        this.version = i2;
    }

    public PluginData(@JsonProperty("internalName") String internalName, @JsonProperty(ImagesContract.URL) String str, @JsonProperty("isOnline") boolean z, @JsonProperty("filePath") String filePath, @JsonProperty("version") int i) {
        Intrinsics.checkNotNullParameter(internalName, "internalName");
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        this.internalName = internalName;
        this.url = str;
        this.isOnline = z;
        this.filePath = filePath;
        this.version = i;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$app(PluginData self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeStringElement(serialDesc, 0, self.internalName);
        output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.url);
        output.encodeBooleanElement(serialDesc, 2, self.isOnline);
        output.encodeStringElement(serialDesc, 3, self.filePath);
        output.encodeIntElement(serialDesc, 4, self.version);
    }

    public final String getInternalName() {
        return this.internalName;
    }

    public final String getUrl() {
        return this.url;
    }

    public final boolean isOnline() {
        return this.isOnline;
    }

    public final String getFilePath() {
        return this.filePath;
    }

    public final int getVersion() {
        return this.version;
    }

    public final SitePlugin toSitePlugin() {
        String str = this.filePath;
        int iMax = Math.max(1, this.version);
        String str2 = this.internalName;
        return new SitePlugin(str, 1, iMax, 1, str2, str2, CollectionsKt.emptyList(), new File(this.filePath).getName(), null, null, null, null, Long.valueOf(new File(this.filePath).length()), null);
    }
}
