package com.lagradost.cloudstream3.plugins;

import androidx.core.app.NotificationCompat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* JADX INFO: compiled from: RepositoryManager.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b4\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 Z2\u00020\u0001:\u0002YZB\u00ad\u0001\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0001\u0010\b\u001a\u00020\u0003\u0012\b\b\u0001\u0010\t\u001a\u00020\u0003\u0012\u000e\b\u0001\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b\u0012\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0001\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000b\u0012\n\b\u0001\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0001\u0010\u0013\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0014\u0010\u0015B\u00ad\u0001\b\u0010\u0012\u0006\u0010\u0016\u001a\u00020\u0005\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000b\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018¢\u0006\u0004\b\u0014\u0010\u0019J\t\u0010<\u001a\u00020\u0003HÆ\u0003J\t\u0010=\u001a\u00020\u0005HÆ\u0003J\t\u0010>\u001a\u00020\u0005HÆ\u0003J\t\u0010?\u001a\u00020\u0005HÆ\u0003J\t\u0010@\u001a\u00020\u0003HÆ\u0003J\t\u0010A\u001a\u00020\u0003HÆ\u0003J\u000f\u0010B\u001a\b\u0012\u0004\u0012\u00020\u00030\u000bHÆ\u0003J\u000b\u0010C\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010D\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010E\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000bHÆ\u0003J\u000b\u0010F\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010G\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010H\u001a\u0004\u0018\u00010\u0012HÆ\u0003¢\u0006\u0002\u00108J\u000b\u0010I\u001a\u0004\u0018\u00010\u0003HÆ\u0003J´\u0001\u0010J\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\u00052\b\b\u0003\u0010\b\u001a\u00020\u00032\b\b\u0003\u0010\t\u001a\u00020\u00032\u000e\b\u0003\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b2\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u00032\u0010\b\u0003\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000b2\n\b\u0003\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\b\u0003\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010KJ\u0014\u0010L\u001a\u00020M2\b\u0010N\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010O\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010P\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010Q\u001a\u00020R2\u0006\u0010S\u001a\u00020\u00002\u0006\u0010T\u001a\u00020U2\u0006\u0010V\u001a\u00020WH\u0001¢\u0006\u0002\bXR\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001e\u0010\u001b\u001a\u0004\b\u001f\u0010 R\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b!\u0010\u001b\u001a\u0004\b\"\u0010 R\u001c\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b#\u0010\u001b\u001a\u0004\b$\u0010 R\u001c\u0010\b\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b%\u0010\u001b\u001a\u0004\b&\u0010\u001dR\u001c\u0010\t\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b'\u0010\u001b\u001a\u0004\b(\u0010\u001dR\"\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b)\u0010\u001b\u001a\u0004\b*\u0010+R\u001e\u0010\f\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b,\u0010\u001b\u001a\u0004\b-\u0010\u001dR\u001e\u0010\r\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b.\u0010\u001b\u001a\u0004\b/\u0010\u001dR$\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b0\u0010\u001b\u001a\u0004\b1\u0010+R\u001e\u0010\u000f\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b2\u0010\u001b\u001a\u0004\b3\u0010\u001dR\u001e\u0010\u0010\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b4\u0010\u001b\u001a\u0004\b5\u0010\u001dR \u0010\u0011\u001a\u0004\u0018\u00010\u00128\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u00109\u0012\u0004\b6\u0010\u001b\u001a\u0004\b7\u00108R\u001e\u0010\u0013\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b:\u0010\u001b\u001a\u0004\b;\u0010\u001d¨\u0006["}, d2 = {"Lcom/lagradost/cloudstream3/plugins/SitePlugin;", "", ImagesContract.URL, "", NotificationCompat.CATEGORY_STATUS, "", "version", "apiVersion", "name", "internalName", "authors", "", "description", "repositoryUrl", "tvTypes", "language", "iconUrl", "fileSize", "", "fileHash", "<init>", "(Ljava/lang/String;IIILjava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;IIILjava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getUrl$annotations", "()V", "getUrl", "()Ljava/lang/String;", "getStatus$annotations", "getStatus", "()I", "getVersion$annotations", "getVersion", "getApiVersion$annotations", "getApiVersion", "getName$annotations", "getName", "getInternalName$annotations", "getInternalName", "getAuthors$annotations", "getAuthors", "()Ljava/util/List;", "getDescription$annotations", "getDescription", "getRepositoryUrl$annotations", "getRepositoryUrl", "getTvTypes$annotations", "getTvTypes", "getLanguage$annotations", "getLanguage", "getIconUrl$annotations", "getIconUrl", "getFileSize$annotations", "getFileSize", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getFileHash$annotations", "getFileHash", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "copy", "(Ljava/lang/String;IIILjava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;)Lcom/lagradost/cloudstream3/plugins/SitePlugin;", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
@Serializable
public final /* data */ class SitePlugin {

    @JsonProperty("apiVersion")
    private final int apiVersion;

    @JsonProperty("authors")
    private final List<String> authors;

    @JsonProperty("description")
    private final String description;

    @JsonProperty("fileHash")
    private final String fileHash;

    @JsonProperty("fileSize")
    private final Long fileSize;

    @JsonProperty("iconUrl")
    private final String iconUrl;

    @JsonProperty("internalName")
    private final String internalName;

    @JsonProperty("language")
    private final String language;

    @JsonProperty("name")
    private final String name;

    @JsonProperty("repositoryUrl")
    private final String repositoryUrl;

    @JsonProperty(NotificationCompat.CATEGORY_STATUS)
    private final int status;

    @JsonProperty("tvTypes")
    private final List<String> tvTypes;

    @JsonProperty(ImagesContract.URL)
    private final String url;

    @JsonProperty("version")
    private final int version;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<KSerializer<Object>>[] $childSerializers = {null, null, null, null, null, null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.plugins.SitePlugin$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return SitePlugin._childSerializers$_anonymous_();
        }
    }), null, null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.plugins.SitePlugin$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return SitePlugin._childSerializers$_anonymous_$0();
        }
    }), null, null, null, null};

    /* JADX INFO: Access modifiers changed from: private */
    public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
        return new ArrayListSerializer(StringSerializer.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final /* synthetic */ KSerializer _childSerializers$_anonymous_$0() {
        return new ArrayListSerializer(StringSerializer.INSTANCE);
    }

    @SerialName("apiVersion")
    public static /* synthetic */ void getApiVersion$annotations() {
    }

    @SerialName("authors")
    public static /* synthetic */ void getAuthors$annotations() {
    }

    @SerialName("description")
    public static /* synthetic */ void getDescription$annotations() {
    }

    @SerialName("fileHash")
    public static /* synthetic */ void getFileHash$annotations() {
    }

    @SerialName("fileSize")
    public static /* synthetic */ void getFileSize$annotations() {
    }

    @SerialName("iconUrl")
    public static /* synthetic */ void getIconUrl$annotations() {
    }

    @SerialName("internalName")
    public static /* synthetic */ void getInternalName$annotations() {
    }

    @SerialName("language")
    public static /* synthetic */ void getLanguage$annotations() {
    }

    @SerialName("name")
    public static /* synthetic */ void getName$annotations() {
    }

    @SerialName("repositoryUrl")
    public static /* synthetic */ void getRepositoryUrl$annotations() {
    }

    @SerialName(NotificationCompat.CATEGORY_STATUS)
    public static /* synthetic */ void getStatus$annotations() {
    }

    @SerialName("tvTypes")
    public static /* synthetic */ void getTvTypes$annotations() {
    }

    @SerialName(ImagesContract.URL)
    public static /* synthetic */ void getUrl$annotations() {
    }

    @SerialName("version")
    public static /* synthetic */ void getVersion$annotations() {
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    public final List<String> component10() {
        return this.tvTypes;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getLanguage() {
        return this.language;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getIconUrl() {
        return this.iconUrl;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final Long getFileSize() {
        return this.fileSize;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getFileHash() {
        return this.fileHash;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getVersion() {
        return this.version;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getApiVersion() {
        return this.apiVersion;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getInternalName() {
        return this.internalName;
    }

    public final List<String> component7() {
        return this.authors;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getRepositoryUrl() {
        return this.repositoryUrl;
    }

    public final SitePlugin copy(@JsonProperty(ImagesContract.URL) String url, @JsonProperty(NotificationCompat.CATEGORY_STATUS) int status, @JsonProperty("version") int version, @JsonProperty("apiVersion") int apiVersion, @JsonProperty("name") String name, @JsonProperty("internalName") String internalName, @JsonProperty("authors") List<String> authors, @JsonProperty("description") String description, @JsonProperty("repositoryUrl") String repositoryUrl, @JsonProperty("tvTypes") List<String> tvTypes, @JsonProperty("language") String language, @JsonProperty("iconUrl") String iconUrl, @JsonProperty("fileSize") Long fileSize, @JsonProperty("fileHash") String fileHash) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(internalName, "internalName");
        Intrinsics.checkNotNullParameter(authors, "authors");
        return new SitePlugin(url, status, version, apiVersion, name, internalName, authors, description, repositoryUrl, tvTypes, language, iconUrl, fileSize, fileHash);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SitePlugin)) {
            return false;
        }
        SitePlugin sitePlugin = (SitePlugin) other;
        return Intrinsics.areEqual(this.url, sitePlugin.url) && this.status == sitePlugin.status && this.version == sitePlugin.version && this.apiVersion == sitePlugin.apiVersion && Intrinsics.areEqual(this.name, sitePlugin.name) && Intrinsics.areEqual(this.internalName, sitePlugin.internalName) && Intrinsics.areEqual(this.authors, sitePlugin.authors) && Intrinsics.areEqual(this.description, sitePlugin.description) && Intrinsics.areEqual(this.repositoryUrl, sitePlugin.repositoryUrl) && Intrinsics.areEqual(this.tvTypes, sitePlugin.tvTypes) && Intrinsics.areEqual(this.language, sitePlugin.language) && Intrinsics.areEqual(this.iconUrl, sitePlugin.iconUrl) && Intrinsics.areEqual(this.fileSize, sitePlugin.fileSize) && Intrinsics.areEqual(this.fileHash, sitePlugin.fileHash);
    }

    public int hashCode() {
        int iHashCode = ((((((((((((this.url.hashCode() * 31) + this.status) * 31) + this.version) * 31) + this.apiVersion) * 31) + this.name.hashCode()) * 31) + this.internalName.hashCode()) * 31) + this.authors.hashCode()) * 31;
        String str = this.description;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.repositoryUrl;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        List<String> list = this.tvTypes;
        int iHashCode4 = (iHashCode3 + (list == null ? 0 : list.hashCode())) * 31;
        String str3 = this.language;
        int iHashCode5 = (iHashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.iconUrl;
        int iHashCode6 = (iHashCode5 + (str4 == null ? 0 : str4.hashCode())) * 31;
        Long l = this.fileSize;
        int iHashCode7 = (iHashCode6 + (l == null ? 0 : l.hashCode())) * 31;
        String str5 = this.fileHash;
        return iHashCode7 + (str5 != null ? str5.hashCode() : 0);
    }

    public String toString() {
        return "SitePlugin(url=" + this.url + ", status=" + this.status + ", version=" + this.version + ", apiVersion=" + this.apiVersion + ", name=" + this.name + ", internalName=" + this.internalName + ", authors=" + this.authors + ", description=" + this.description + ", repositoryUrl=" + this.repositoryUrl + ", tvTypes=" + this.tvTypes + ", language=" + this.language + ", iconUrl=" + this.iconUrl + ", fileSize=" + this.fileSize + ", fileHash=" + this.fileHash + ')';
    }

    /* JADX INFO: compiled from: RepositoryManager.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/plugins/SitePlugin$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/plugins/SitePlugin;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<SitePlugin> serializer() {
            return SitePlugin$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ SitePlugin(int i, String str, int i2, int i3, int i4, String str2, String str3, List list, String str4, String str5, List list2, String str6, String str7, Long l, String str8, SerializationConstructorMarker serializationConstructorMarker) {
        if (16383 != (i & 16383)) {
            PluginExceptionsKt.throwMissingFieldException(i, 16383, SitePlugin$$serializer.INSTANCE.getDescriptor());
        }
        this.url = str;
        this.status = i2;
        this.version = i3;
        this.apiVersion = i4;
        this.name = str2;
        this.internalName = str3;
        this.authors = list;
        this.description = str4;
        this.repositoryUrl = str5;
        this.tvTypes = list2;
        this.language = str6;
        this.iconUrl = str7;
        this.fileSize = l;
        this.fileHash = str8;
    }

    public SitePlugin(@JsonProperty(ImagesContract.URL) String url, @JsonProperty(NotificationCompat.CATEGORY_STATUS) int i, @JsonProperty("version") int i2, @JsonProperty("apiVersion") int i3, @JsonProperty("name") String name, @JsonProperty("internalName") String internalName, @JsonProperty("authors") List<String> authors, @JsonProperty("description") String str, @JsonProperty("repositoryUrl") String str2, @JsonProperty("tvTypes") List<String> list, @JsonProperty("language") String str3, @JsonProperty("iconUrl") String str4, @JsonProperty("fileSize") Long l, @JsonProperty("fileHash") String str5) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(internalName, "internalName");
        Intrinsics.checkNotNullParameter(authors, "authors");
        this.url = url;
        this.status = i;
        this.version = i2;
        this.apiVersion = i3;
        this.name = name;
        this.internalName = internalName;
        this.authors = authors;
        this.description = str;
        this.repositoryUrl = str2;
        this.tvTypes = list;
        this.language = str3;
        this.iconUrl = str4;
        this.fileSize = l;
        this.fileHash = str5;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$app(SitePlugin self, CompositeEncoder output, SerialDescriptor serialDesc) {
        Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
        output.encodeStringElement(serialDesc, 0, self.url);
        output.encodeIntElement(serialDesc, 1, self.status);
        output.encodeIntElement(serialDesc, 2, self.version);
        output.encodeIntElement(serialDesc, 3, self.apiVersion);
        output.encodeStringElement(serialDesc, 4, self.name);
        output.encodeStringElement(serialDesc, 5, self.internalName);
        output.encodeSerializableElement(serialDesc, 6, lazyArr[6].getValue(), self.authors);
        output.encodeNullableSerializableElement(serialDesc, 7, StringSerializer.INSTANCE, self.description);
        output.encodeNullableSerializableElement(serialDesc, 8, StringSerializer.INSTANCE, self.repositoryUrl);
        output.encodeNullableSerializableElement(serialDesc, 9, lazyArr[9].getValue(), self.tvTypes);
        output.encodeNullableSerializableElement(serialDesc, 10, StringSerializer.INSTANCE, self.language);
        output.encodeNullableSerializableElement(serialDesc, 11, StringSerializer.INSTANCE, self.iconUrl);
        output.encodeNullableSerializableElement(serialDesc, 12, LongSerializer.INSTANCE, self.fileSize);
        output.encodeNullableSerializableElement(serialDesc, 13, StringSerializer.INSTANCE, self.fileHash);
    }

    public final String getUrl() {
        return this.url;
    }

    public final int getStatus() {
        return this.status;
    }

    public final int getVersion() {
        return this.version;
    }

    public final int getApiVersion() {
        return this.apiVersion;
    }

    public final String getName() {
        return this.name;
    }

    public final String getInternalName() {
        return this.internalName;
    }

    public final List<String> getAuthors() {
        return this.authors;
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getRepositoryUrl() {
        return this.repositoryUrl;
    }

    public final List<String> getTvTypes() {
        return this.tvTypes;
    }

    public final String getLanguage() {
        return this.language;
    }

    public final String getIconUrl() {
        return this.iconUrl;
    }

    public final Long getFileSize() {
        return this.fileSize;
    }

    public final String getFileHash() {
        return this.fileHash;
    }
}
