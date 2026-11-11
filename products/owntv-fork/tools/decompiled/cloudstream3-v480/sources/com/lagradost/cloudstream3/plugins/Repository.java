package com.lagradost.cloudstream3.plugins;

import com.fasterxml.jackson.annotation.JsonProperty;
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
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* JADX INFO: compiled from: RepositoryManager.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 22\u00020\u0001:\u000212BC\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u0012\u000e\b\u0001\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t¢\u0006\u0004\b\n\u0010\u000bBQ\b\u0010\u0012\u0006\u0010\f\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\n\u0010\u000fJ\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0007HÆ\u0003J\u000f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00030\tHÆ\u0003JE\u0010#\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0003\u0010\u0006\u001a\u00020\u00072\u000e\b\u0003\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\tHÆ\u0001J\u0014\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010'\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010(\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u00002\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0001¢\u0006\u0002\b0R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u0011\u001a\u0004\b\u0015\u0010\u0013R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u0011\u001a\u0004\b\u0017\u0010\u0013R\u001c\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u0011\u001a\u0004\b\u0019\u0010\u001aR\"\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001b\u0010\u0011\u001a\u0004\b\u001c\u0010\u001d¨\u00063"}, d2 = {"Lcom/lagradost/cloudstream3/plugins/Repository;", "", "iconUrl", "", "name", "description", "manifestVersion", "", "pluginLists", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/util/List;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getIconUrl$annotations", "()V", "getIconUrl", "()Ljava/lang/String;", "getName$annotations", "getName", "getDescription$annotations", "getDescription", "getManifestVersion$annotations", "getManifestVersion", "()I", "getPluginLists$annotations", "getPluginLists", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
@Serializable
public final /* data */ class Repository {

    @JsonProperty("description")
    private final String description;

    @JsonProperty("iconUrl")
    private final String iconUrl;

    @JsonProperty("manifestVersion")
    private final int manifestVersion;

    @JsonProperty("name")
    private final String name;

    @JsonProperty("pluginLists")
    private final List<String> pluginLists;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<KSerializer<Object>>[] $childSerializers = {null, null, null, null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.plugins.Repository$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return Repository._childSerializers$_anonymous_();
        }
    })};

    /* JADX INFO: Access modifiers changed from: private */
    public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
        return new ArrayListSerializer(StringSerializer.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Repository copy$default(Repository repository, String str, String str2, String str3, int i, List list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = repository.iconUrl;
        }
        if ((i2 & 2) != 0) {
            str2 = repository.name;
        }
        if ((i2 & 4) != 0) {
            str3 = repository.description;
        }
        if ((i2 & 8) != 0) {
            i = repository.manifestVersion;
        }
        if ((i2 & 16) != 0) {
            list = repository.pluginLists;
        }
        List list2 = list;
        String str4 = str3;
        return repository.copy(str, str2, str4, i, list2);
    }

    @SerialName("description")
    public static /* synthetic */ void getDescription$annotations() {
    }

    @SerialName("iconUrl")
    public static /* synthetic */ void getIconUrl$annotations() {
    }

    @SerialName("manifestVersion")
    public static /* synthetic */ void getManifestVersion$annotations() {
    }

    @SerialName("name")
    public static /* synthetic */ void getName$annotations() {
    }

    @SerialName("pluginLists")
    public static /* synthetic */ void getPluginLists$annotations() {
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getIconUrl() {
        return this.iconUrl;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getManifestVersion() {
        return this.manifestVersion;
    }

    public final List<String> component5() {
        return this.pluginLists;
    }

    public final Repository copy(@JsonProperty("iconUrl") String iconUrl, @JsonProperty("name") String name, @JsonProperty("description") String description, @JsonProperty("manifestVersion") int manifestVersion, @JsonProperty("pluginLists") List<String> pluginLists) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(pluginLists, "pluginLists");
        return new Repository(iconUrl, name, description, manifestVersion, pluginLists);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Repository)) {
            return false;
        }
        Repository repository = (Repository) other;
        return Intrinsics.areEqual(this.iconUrl, repository.iconUrl) && Intrinsics.areEqual(this.name, repository.name) && Intrinsics.areEqual(this.description, repository.description) && this.manifestVersion == repository.manifestVersion && Intrinsics.areEqual(this.pluginLists, repository.pluginLists);
    }

    public int hashCode() {
        String str = this.iconUrl;
        int iHashCode = (((str == null ? 0 : str.hashCode()) * 31) + this.name.hashCode()) * 31;
        String str2 = this.description;
        return ((((iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + this.manifestVersion) * 31) + this.pluginLists.hashCode();
    }

    public String toString() {
        return "Repository(iconUrl=" + this.iconUrl + ", name=" + this.name + ", description=" + this.description + ", manifestVersion=" + this.manifestVersion + ", pluginLists=" + this.pluginLists + ')';
    }

    /* JADX INFO: compiled from: RepositoryManager.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/plugins/Repository$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/plugins/Repository;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<Repository> serializer() {
            return Repository$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ Repository(int i, String str, String str2, String str3, int i2, List list, SerializationConstructorMarker serializationConstructorMarker) {
        if (31 != (i & 31)) {
            PluginExceptionsKt.throwMissingFieldException(i, 31, Repository$$serializer.INSTANCE.getDescriptor());
        }
        this.iconUrl = str;
        this.name = str2;
        this.description = str3;
        this.manifestVersion = i2;
        this.pluginLists = list;
    }

    public Repository(@JsonProperty("iconUrl") String str, @JsonProperty("name") String name, @JsonProperty("description") String str2, @JsonProperty("manifestVersion") int i, @JsonProperty("pluginLists") List<String> pluginLists) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(pluginLists, "pluginLists");
        this.iconUrl = str;
        this.name = name;
        this.description = str2;
        this.manifestVersion = i;
        this.pluginLists = pluginLists;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$app(Repository self, CompositeEncoder output, SerialDescriptor serialDesc) {
        Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
        output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.iconUrl);
        output.encodeStringElement(serialDesc, 1, self.name);
        output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.description);
        output.encodeIntElement(serialDesc, 3, self.manifestVersion);
        output.encodeSerializableElement(serialDesc, 4, lazyArr[4].getValue(), self.pluginLists);
    }

    public final String getIconUrl() {
        return this.iconUrl;
    }

    public final String getName() {
        return this.name;
    }

    public final String getDescription() {
        return this.description;
    }

    public final int getManifestVersion() {
        return this.manifestVersion;
    }

    public final List<String> getPluginLists() {
        return this.pluginLists;
    }
}
