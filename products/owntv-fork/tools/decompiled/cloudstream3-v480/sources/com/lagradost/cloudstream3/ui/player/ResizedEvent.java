package com.lagradost.cloudstream3.ui.player;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: IPlayer.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0019"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/ResizedEvent;", "Lcom/lagradost/cloudstream3/ui/player/PlayerEvent;", "height", "", "width", "source", "Lcom/lagradost/cloudstream3/ui/player/PlayerEventSource;", "<init>", "(IILcom/lagradost/cloudstream3/ui/player/PlayerEventSource;)V", "getHeight", "()I", "getWidth", "getSource", "()Lcom/lagradost/cloudstream3/ui/player/PlayerEventSource;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class ResizedEvent extends PlayerEvent {
    private final int height;
    private final PlayerEventSource source;
    private final int width;

    public static /* synthetic */ ResizedEvent copy$default(ResizedEvent resizedEvent, int i, int i2, PlayerEventSource playerEventSource, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = resizedEvent.height;
        }
        if ((i3 & 2) != 0) {
            i2 = resizedEvent.width;
        }
        if ((i3 & 4) != 0) {
            playerEventSource = resizedEvent.source;
        }
        return resizedEvent.copy(i, i2, playerEventSource);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getHeight() {
        return this.height;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getWidth() {
        return this.width;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final PlayerEventSource getSource() {
        return this.source;
    }

    public final ResizedEvent copy(int height, int width, PlayerEventSource source) {
        Intrinsics.checkNotNullParameter(source, "source");
        return new ResizedEvent(height, width, source);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ResizedEvent)) {
            return false;
        }
        ResizedEvent resizedEvent = (ResizedEvent) other;
        return this.height == resizedEvent.height && this.width == resizedEvent.width && this.source == resizedEvent.source;
    }

    public int hashCode() {
        return (((this.height * 31) + this.width) * 31) + this.source.hashCode();
    }

    public String toString() {
        return "ResizedEvent(height=" + this.height + ", width=" + this.width + ", source=" + this.source + ')';
    }

    public ResizedEvent(int i, int i2, PlayerEventSource source) {
        Intrinsics.checkNotNullParameter(source, "source");
        this.height = i;
        this.width = i2;
        this.source = source;
    }

    public final int getHeight() {
        return this.height;
    }

    public final int getWidth() {
        return this.width;
    }

    public /* synthetic */ ResizedEvent(int i, int i2, PlayerEventSource playerEventSource, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, (i3 & 4) != 0 ? PlayerEventSource.Player : playerEventSource);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerEvent
    public PlayerEventSource getSource() {
        return this.source;
    }
}
