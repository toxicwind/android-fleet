package com.lagradost.cloudstream3.ui.player;

import com.lagradost.cloudstream3.utils.videoskip.VideoSkipStamp;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: IPlayer.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/TimestampSkippedEvent;", "Lcom/lagradost/cloudstream3/ui/player/PlayerEvent;", "timestamp", "Lcom/lagradost/cloudstream3/utils/videoskip/VideoSkipStamp;", "source", "Lcom/lagradost/cloudstream3/ui/player/PlayerEventSource;", "<init>", "(Lcom/lagradost/cloudstream3/utils/videoskip/VideoSkipStamp;Lcom/lagradost/cloudstream3/ui/player/PlayerEventSource;)V", "getTimestamp", "()Lcom/lagradost/cloudstream3/utils/videoskip/VideoSkipStamp;", "getSource", "()Lcom/lagradost/cloudstream3/ui/player/PlayerEventSource;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TimestampSkippedEvent extends PlayerEvent {
    private final PlayerEventSource source;
    private final VideoSkipStamp timestamp;

    public static /* synthetic */ TimestampSkippedEvent copy$default(TimestampSkippedEvent timestampSkippedEvent, VideoSkipStamp videoSkipStamp, PlayerEventSource playerEventSource, int i, Object obj) {
        if ((i & 1) != 0) {
            videoSkipStamp = timestampSkippedEvent.timestamp;
        }
        if ((i & 2) != 0) {
            playerEventSource = timestampSkippedEvent.source;
        }
        return timestampSkippedEvent.copy(videoSkipStamp, playerEventSource);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final VideoSkipStamp getTimestamp() {
        return this.timestamp;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final PlayerEventSource getSource() {
        return this.source;
    }

    public final TimestampSkippedEvent copy(VideoSkipStamp timestamp, PlayerEventSource source) {
        Intrinsics.checkNotNullParameter(timestamp, "timestamp");
        Intrinsics.checkNotNullParameter(source, "source");
        return new TimestampSkippedEvent(timestamp, source);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TimestampSkippedEvent)) {
            return false;
        }
        TimestampSkippedEvent timestampSkippedEvent = (TimestampSkippedEvent) other;
        return Intrinsics.areEqual(this.timestamp, timestampSkippedEvent.timestamp) && this.source == timestampSkippedEvent.source;
    }

    public int hashCode() {
        return (this.timestamp.hashCode() * 31) + this.source.hashCode();
    }

    public String toString() {
        return "TimestampSkippedEvent(timestamp=" + this.timestamp + ", source=" + this.source + ')';
    }

    public TimestampSkippedEvent(VideoSkipStamp timestamp, PlayerEventSource source) {
        Intrinsics.checkNotNullParameter(timestamp, "timestamp");
        Intrinsics.checkNotNullParameter(source, "source");
        this.timestamp = timestamp;
        this.source = source;
    }

    public final VideoSkipStamp getTimestamp() {
        return this.timestamp;
    }

    public /* synthetic */ TimestampSkippedEvent(VideoSkipStamp videoSkipStamp, PlayerEventSource playerEventSource, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(videoSkipStamp, (i & 2) != 0 ? PlayerEventSource.Player : playerEventSource);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerEvent
    public PlayerEventSource getSource() {
        return this.source;
    }
}
