package com.lagradost.cloudstream3.plugins;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lagradost.cloudstream3.ui.settings.extensions.RepositoryData;
import com.lagradost.cloudstream3.ui.settings.extensions.RepositoryData$$serializer;
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

/* JADX INFO: compiled from: RepositoryManager.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 +2\u00020\u0001:\u0002+,B%\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tB9\b\u0010\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\b\u0010\u000eJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0007HÆ\u0003J'\u0010\u001c\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0014\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010 \u001a\u00020\u000bHÖ\u0081\u0004J\n\u0010!\u001a\u00020\"HÖ\u0081\u0004J%\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u00002\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)H\u0001¢\u0006\u0002\b*R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u0010\u001a\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u0010\u001a\u0004\b\u0017\u0010\u0018¨\u0006-"}, d2 = {"Lcom/lagradost/cloudstream3/plugins/PluginWrapper;", "", "repository", "Lcom/lagradost/cloudstream3/plugins/Repository;", "repositoryData", "Lcom/lagradost/cloudstream3/ui/settings/extensions/RepositoryData;", "plugin", "Lcom/lagradost/cloudstream3/plugins/SitePlugin;", "<init>", "(Lcom/lagradost/cloudstream3/plugins/Repository;Lcom/lagradost/cloudstream3/ui/settings/extensions/RepositoryData;Lcom/lagradost/cloudstream3/plugins/SitePlugin;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/plugins/Repository;Lcom/lagradost/cloudstream3/ui/settings/extensions/RepositoryData;Lcom/lagradost/cloudstream3/plugins/SitePlugin;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getRepository$annotations", "()V", "getRepository", "()Lcom/lagradost/cloudstream3/plugins/Repository;", "getRepositoryData$annotations", "getRepositoryData", "()Lcom/lagradost/cloudstream3/ui/settings/extensions/RepositoryData;", "getPlugin$annotations", "getPlugin", "()Lcom/lagradost/cloudstream3/plugins/SitePlugin;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "Companion", "$serializer", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
@Serializable
public final /* data */ class PluginWrapper {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Repository localRepository = new Repository("", "", "", 1, CollectionsKt.emptyList());
    private static final RepositoryData localRepositoryData = new RepositoryData("", "", "");

    @JsonProperty("plugin")
    private final SitePlugin plugin;

    @JsonProperty("repository")
    private final Repository repository;

    @JsonProperty("repositoryData")
    private final RepositoryData repositoryData;

    public static /* synthetic */ PluginWrapper copy$default(PluginWrapper pluginWrapper, Repository repository, RepositoryData repositoryData, SitePlugin sitePlugin, int i, Object obj) {
        if ((i & 1) != 0) {
            repository = pluginWrapper.repository;
        }
        if ((i & 2) != 0) {
            repositoryData = pluginWrapper.repositoryData;
        }
        if ((i & 4) != 0) {
            sitePlugin = pluginWrapper.plugin;
        }
        return pluginWrapper.copy(repository, repositoryData, sitePlugin);
    }

    @SerialName("plugin")
    public static /* synthetic */ void getPlugin$annotations() {
    }

    @SerialName("repository")
    public static /* synthetic */ void getRepository$annotations() {
    }

    @SerialName("repositoryData")
    public static /* synthetic */ void getRepositoryData$annotations() {
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Repository getRepository() {
        return this.repository;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final RepositoryData getRepositoryData() {
        return this.repositoryData;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final SitePlugin getPlugin() {
        return this.plugin;
    }

    public final PluginWrapper copy(@JsonProperty("repository") Repository repository, @JsonProperty("repositoryData") RepositoryData repositoryData, @JsonProperty("plugin") SitePlugin plugin) {
        Intrinsics.checkNotNullParameter(repository, "repository");
        Intrinsics.checkNotNullParameter(repositoryData, "repositoryData");
        Intrinsics.checkNotNullParameter(plugin, "plugin");
        return new PluginWrapper(repository, repositoryData, plugin);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PluginWrapper)) {
            return false;
        }
        PluginWrapper pluginWrapper = (PluginWrapper) other;
        return Intrinsics.areEqual(this.repository, pluginWrapper.repository) && Intrinsics.areEqual(this.repositoryData, pluginWrapper.repositoryData) && Intrinsics.areEqual(this.plugin, pluginWrapper.plugin);
    }

    public int hashCode() {
        return (((this.repository.hashCode() * 31) + this.repositoryData.hashCode()) * 31) + this.plugin.hashCode();
    }

    public String toString() {
        return "PluginWrapper(repository=" + this.repository + ", repositoryData=" + this.repositoryData + ", plugin=" + this.plugin + ')';
    }

    public /* synthetic */ PluginWrapper(int i, Repository repository, RepositoryData repositoryData, SitePlugin sitePlugin, SerializationConstructorMarker serializationConstructorMarker) {
        if (7 != (i & 7)) {
            PluginExceptionsKt.throwMissingFieldException(i, 7, PluginWrapper$$serializer.INSTANCE.getDescriptor());
        }
        this.repository = repository;
        this.repositoryData = repositoryData;
        this.plugin = sitePlugin;
    }

    public PluginWrapper(@JsonProperty("repository") Repository repository, @JsonProperty("repositoryData") RepositoryData repositoryData, @JsonProperty("plugin") SitePlugin plugin) {
        Intrinsics.checkNotNullParameter(repository, "repository");
        Intrinsics.checkNotNullParameter(repositoryData, "repositoryData");
        Intrinsics.checkNotNullParameter(plugin, "plugin");
        this.repository = repository;
        this.repositoryData = repositoryData;
        this.plugin = plugin;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$app(PluginWrapper self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeSerializableElement(serialDesc, 0, Repository$$serializer.INSTANCE, self.repository);
        output.encodeSerializableElement(serialDesc, 1, RepositoryData$$serializer.INSTANCE, self.repositoryData);
        output.encodeSerializableElement(serialDesc, 2, SitePlugin$$serializer.INSTANCE, self.plugin);
    }

    public final Repository getRepository() {
        return this.repository;
    }

    public final RepositoryData getRepositoryData() {
        return this.repositoryData;
    }

    public final SitePlugin getPlugin() {
        return this.plugin;
    }

    /* JADX INFO: compiled from: RepositoryManager.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/lagradost/cloudstream3/plugins/PluginWrapper$Companion;", "", "<init>", "()V", "localRepository", "Lcom/lagradost/cloudstream3/plugins/Repository;", "localRepositoryData", "Lcom/lagradost/cloudstream3/ui/settings/extensions/RepositoryData;", "getLocalPluginWrapper", "Lcom/lagradost/cloudstream3/plugins/PluginWrapper;", "plugin", "Lcom/lagradost/cloudstream3/plugins/SitePlugin;", "serializer", "Lkotlinx/serialization/KSerializer;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<PluginWrapper> serializer() {
            return PluginWrapper$$serializer.INSTANCE;
        }

        public final PluginWrapper getLocalPluginWrapper(SitePlugin plugin) {
            Intrinsics.checkNotNullParameter(plugin, "plugin");
            return new PluginWrapper(PluginWrapper.localRepository, PluginWrapper.localRepositoryData, plugin);
        }
    }
}
