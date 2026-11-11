package com.lagradost.cloudstream3.ui.settings.extensions;

import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import com.lagradost.cloudstream3.plugins.PluginWrapper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PluginAdapter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/lagradost/cloudstream3/ui/settings/extensions/PluginViewData;", "", "pluginWrapper", "Lcom/lagradost/cloudstream3/plugins/PluginWrapper;", "isDownloaded", "", "<init>", "(Lcom/lagradost/cloudstream3/plugins/PluginWrapper;Z)V", "getPluginWrapper", "()Lcom/lagradost/cloudstream3/plugins/PluginWrapper;", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class PluginViewData {
    private final boolean isDownloaded;
    private final PluginWrapper pluginWrapper;

    public static /* synthetic */ PluginViewData copy$default(PluginViewData pluginViewData, PluginWrapper pluginWrapper, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            pluginWrapper = pluginViewData.pluginWrapper;
        }
        if ((i & 2) != 0) {
            z = pluginViewData.isDownloaded;
        }
        return pluginViewData.copy(pluginWrapper, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final PluginWrapper getPluginWrapper() {
        return this.pluginWrapper;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getIsDownloaded() {
        return this.isDownloaded;
    }

    public final PluginViewData copy(PluginWrapper pluginWrapper, boolean isDownloaded) {
        Intrinsics.checkNotNullParameter(pluginWrapper, "pluginWrapper");
        return new PluginViewData(pluginWrapper, isDownloaded);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PluginViewData)) {
            return false;
        }
        PluginViewData pluginViewData = (PluginViewData) other;
        return Intrinsics.areEqual(this.pluginWrapper, pluginViewData.pluginWrapper) && this.isDownloaded == pluginViewData.isDownloaded;
    }

    public int hashCode() {
        return (this.pluginWrapper.hashCode() * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.isDownloaded);
    }

    public String toString() {
        return "PluginViewData(pluginWrapper=" + this.pluginWrapper + ", isDownloaded=" + this.isDownloaded + ')';
    }

    public PluginViewData(PluginWrapper pluginWrapper, boolean z) {
        Intrinsics.checkNotNullParameter(pluginWrapper, "pluginWrapper");
        this.pluginWrapper = pluginWrapper;
        this.isDownloaded = z;
    }

    public final PluginWrapper getPluginWrapper() {
        return this.pluginWrapper;
    }

    public final boolean isDownloaded() {
        return this.isDownloaded;
    }
}
