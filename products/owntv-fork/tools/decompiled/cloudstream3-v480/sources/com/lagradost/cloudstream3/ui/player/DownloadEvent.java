package com.lagradost.cloudstream3.ui.player;

import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: IPlayer.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0011J\t\u0010\u0019\u001a\u00020\tHÆ\u0003JB\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001¢\u0006\u0002\u0010\u001bJ\u0014\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fHÖ\u0083\u0004J\n\u0010 \u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010!\u001a\u00020\"HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006#"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/DownloadEvent;", "Lcom/lagradost/cloudstream3/ui/player/PlayerEvent;", "downloadedBytes", "", "totalBytes", "downloadSpeed", "connections", "", "source", "Lcom/lagradost/cloudstream3/ui/player/PlayerEventSource;", "<init>", "(JJJLjava/lang/Integer;Lcom/lagradost/cloudstream3/ui/player/PlayerEventSource;)V", "getDownloadedBytes", "()J", "getTotalBytes", "getDownloadSpeed", "getConnections", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getSource", "()Lcom/lagradost/cloudstream3/ui/player/PlayerEventSource;", "component1", "component2", "component3", "component4", "component5", "copy", "(JJJLjava/lang/Integer;Lcom/lagradost/cloudstream3/ui/player/PlayerEventSource;)Lcom/lagradost/cloudstream3/ui/player/DownloadEvent;", "equals", "", "other", "", "hashCode", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class DownloadEvent extends PlayerEvent {
    private final Integer connections;
    private final long downloadSpeed;
    private final long downloadedBytes;
    private final PlayerEventSource source;
    private final long totalBytes;

    public static /* synthetic */ DownloadEvent copy$default(DownloadEvent downloadEvent, long j, long j2, long j3, Integer num, PlayerEventSource playerEventSource, int i, Object obj) {
        if ((i & 1) != 0) {
            j = downloadEvent.downloadedBytes;
        }
        long j4 = j;
        if ((i & 2) != 0) {
            j2 = downloadEvent.totalBytes;
        }
        long j5 = j2;
        if ((i & 4) != 0) {
            j3 = downloadEvent.downloadSpeed;
        }
        return downloadEvent.copy(j4, j5, j3, (i & 8) != 0 ? downloadEvent.connections : num, (i & 16) != 0 ? downloadEvent.source : playerEventSource);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getDownloadedBytes() {
        return this.downloadedBytes;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final long getTotalBytes() {
        return this.totalBytes;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final long getDownloadSpeed() {
        return this.downloadSpeed;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Integer getConnections() {
        return this.connections;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final PlayerEventSource getSource() {
        return this.source;
    }

    public final DownloadEvent copy(long downloadedBytes, long totalBytes, long downloadSpeed, Integer connections, PlayerEventSource source) {
        Intrinsics.checkNotNullParameter(source, "source");
        return new DownloadEvent(downloadedBytes, totalBytes, downloadSpeed, connections, source);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DownloadEvent)) {
            return false;
        }
        DownloadEvent downloadEvent = (DownloadEvent) other;
        return this.downloadedBytes == downloadEvent.downloadedBytes && this.totalBytes == downloadEvent.totalBytes && this.downloadSpeed == downloadEvent.downloadSpeed && Intrinsics.areEqual(this.connections, downloadEvent.connections) && this.source == downloadEvent.source;
    }

    public int hashCode() {
        int iM = ((((NextAiring$$ExternalSyntheticBackport0.m(this.downloadedBytes) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.totalBytes)) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.downloadSpeed)) * 31;
        Integer num = this.connections;
        return ((iM + (num == null ? 0 : num.hashCode())) * 31) + this.source.hashCode();
    }

    public String toString() {
        return "DownloadEvent(downloadedBytes=" + this.downloadedBytes + ", totalBytes=" + this.totalBytes + ", downloadSpeed=" + this.downloadSpeed + ", connections=" + this.connections + ", source=" + this.source + ')';
    }

    public DownloadEvent(long j, long j2, long j3, Integer num, PlayerEventSource source) {
        Intrinsics.checkNotNullParameter(source, "source");
        this.downloadedBytes = j;
        this.totalBytes = j2;
        this.downloadSpeed = j3;
        this.connections = num;
        this.source = source;
    }

    public final long getDownloadedBytes() {
        return this.downloadedBytes;
    }

    public final long getTotalBytes() {
        return this.totalBytes;
    }

    public final long getDownloadSpeed() {
        return this.downloadSpeed;
    }

    public final Integer getConnections() {
        return this.connections;
    }

    public /* synthetic */ DownloadEvent(long j, long j2, long j3, Integer num, PlayerEventSource playerEventSource, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, j3, num, (i & 16) != 0 ? PlayerEventSource.Player : playerEventSource);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerEvent
    public PlayerEventSource getSource() {
        return this.source;
    }
}
