package com.lagradost.cloudstream3.ui.player;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: IPlayer.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/EpisodeSeekEvent;", "Lcom/lagradost/cloudstream3/ui/player/PlayerEvent;", TypedValues.CycleType.S_WAVE_OFFSET, "", "source", "Lcom/lagradost/cloudstream3/ui/player/PlayerEventSource;", "<init>", "(ILcom/lagradost/cloudstream3/ui/player/PlayerEventSource;)V", "getOffset", "()I", "getSource", "()Lcom/lagradost/cloudstream3/ui/player/PlayerEventSource;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class EpisodeSeekEvent extends PlayerEvent {
    private final int offset;
    private final PlayerEventSource source;

    public static /* synthetic */ EpisodeSeekEvent copy$default(EpisodeSeekEvent episodeSeekEvent, int i, PlayerEventSource playerEventSource, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = episodeSeekEvent.offset;
        }
        if ((i2 & 2) != 0) {
            playerEventSource = episodeSeekEvent.source;
        }
        return episodeSeekEvent.copy(i, playerEventSource);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getOffset() {
        return this.offset;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final PlayerEventSource getSource() {
        return this.source;
    }

    public final EpisodeSeekEvent copy(int offset, PlayerEventSource source) {
        Intrinsics.checkNotNullParameter(source, "source");
        return new EpisodeSeekEvent(offset, source);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EpisodeSeekEvent)) {
            return false;
        }
        EpisodeSeekEvent episodeSeekEvent = (EpisodeSeekEvent) other;
        return this.offset == episodeSeekEvent.offset && this.source == episodeSeekEvent.source;
    }

    public int hashCode() {
        return (this.offset * 31) + this.source.hashCode();
    }

    public String toString() {
        return "EpisodeSeekEvent(offset=" + this.offset + ", source=" + this.source + ')';
    }

    public EpisodeSeekEvent(int i, PlayerEventSource source) {
        Intrinsics.checkNotNullParameter(source, "source");
        this.offset = i;
        this.source = source;
    }

    public final int getOffset() {
        return this.offset;
    }

    public /* synthetic */ EpisodeSeekEvent(int i, PlayerEventSource playerEventSource, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? PlayerEventSource.Player : playerEventSource);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerEvent
    public PlayerEventSource getSource() {
        return this.source;
    }
}
