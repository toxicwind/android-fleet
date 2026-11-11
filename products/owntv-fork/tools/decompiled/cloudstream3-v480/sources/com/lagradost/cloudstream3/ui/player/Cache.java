package com.lagradost.cloudstream3.ui.player;

import com.lagradost.cloudstream3.APIHolder;
import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RepoLinkGenerator.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\bHÆ\u0003J\t\u0010\u001b\u001a\u00020\nHÆ\u0003J=\u0010\u001c\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0014\u0010\u001d\u001a\u00020\n2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001f\u001a\u00020 HÖ\u0081\u0004J\n\u0010!\u001a\u00020\"HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006#"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/Cache;", "", "linkCache", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "subtitleCache", "Lcom/lagradost/cloudstream3/ui/player/SubtitleData;", "lastCachedTimestamp", "", "saturated", "", "<init>", "(Ljava/util/Set;Ljava/util/Set;JZ)V", "getLinkCache", "()Ljava/util/Set;", "getSubtitleCache", "getLastCachedTimestamp", "()J", "setLastCachedTimestamp", "(J)V", "getSaturated", "()Z", "setSaturated", "(Z)V", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class Cache {
    private long lastCachedTimestamp;
    private final Set<ExtractorLink> linkCache;
    private boolean saturated;
    private final Set<SubtitleData> subtitleCache;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Cache copy$default(Cache cache, Set set, Set set2, long j, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            set = cache.linkCache;
        }
        if ((i & 2) != 0) {
            set2 = cache.subtitleCache;
        }
        if ((i & 4) != 0) {
            j = cache.lastCachedTimestamp;
        }
        if ((i & 8) != 0) {
            z = cache.saturated;
        }
        boolean z2 = z;
        return cache.copy(set, set2, j, z2);
    }

    public final Set<ExtractorLink> component1() {
        return this.linkCache;
    }

    public final Set<SubtitleData> component2() {
        return this.subtitleCache;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final long getLastCachedTimestamp() {
        return this.lastCachedTimestamp;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getSaturated() {
        return this.saturated;
    }

    public final Cache copy(Set<ExtractorLink> linkCache, Set<SubtitleData> subtitleCache, long lastCachedTimestamp, boolean saturated) {
        Intrinsics.checkNotNullParameter(linkCache, "linkCache");
        Intrinsics.checkNotNullParameter(subtitleCache, "subtitleCache");
        return new Cache(linkCache, subtitleCache, lastCachedTimestamp, saturated);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Cache)) {
            return false;
        }
        Cache cache = (Cache) other;
        return Intrinsics.areEqual(this.linkCache, cache.linkCache) && Intrinsics.areEqual(this.subtitleCache, cache.subtitleCache) && this.lastCachedTimestamp == cache.lastCachedTimestamp && this.saturated == cache.saturated;
    }

    public int hashCode() {
        return (((((this.linkCache.hashCode() * 31) + this.subtitleCache.hashCode()) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.lastCachedTimestamp)) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.saturated);
    }

    public String toString() {
        return "Cache(linkCache=" + this.linkCache + ", subtitleCache=" + this.subtitleCache + ", lastCachedTimestamp=" + this.lastCachedTimestamp + ", saturated=" + this.saturated + ')';
    }

    public Cache(Set<ExtractorLink> linkCache, Set<SubtitleData> subtitleCache, long j, boolean z) {
        Intrinsics.checkNotNullParameter(linkCache, "linkCache");
        Intrinsics.checkNotNullParameter(subtitleCache, "subtitleCache");
        this.linkCache = linkCache;
        this.subtitleCache = subtitleCache;
        this.lastCachedTimestamp = j;
        this.saturated = z;
    }

    public final Set<ExtractorLink> getLinkCache() {
        return this.linkCache;
    }

    public final Set<SubtitleData> getSubtitleCache() {
        return this.subtitleCache;
    }

    public /* synthetic */ Cache(Set set, Set set2, long j, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(set, set2, (i & 4) != 0 ? APIHolder.INSTANCE.getUnixTime() : j, z);
    }

    public final long getLastCachedTimestamp() {
        return this.lastCachedTimestamp;
    }

    public final void setLastCachedTimestamp(long j) {
        this.lastCachedTimestamp = j;
    }

    public final boolean getSaturated() {
        return this.saturated;
    }

    public final void setSaturated(boolean z) {
        this.saturated = z;
    }
}
