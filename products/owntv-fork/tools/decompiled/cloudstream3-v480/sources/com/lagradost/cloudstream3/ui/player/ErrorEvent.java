package com.lagradost.cloudstream3.ui.player;

import androidx.mediarouter.media.MediaRouteProviderProtocol;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: IPlayer.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/ErrorEvent;", "Lcom/lagradost/cloudstream3/ui/player/PlayerEvent;", MediaRouteProviderProtocol.SERVICE_DATA_ERROR, "", "source", "Lcom/lagradost/cloudstream3/ui/player/PlayerEventSource;", "<init>", "(Ljava/lang/Throwable;Lcom/lagradost/cloudstream3/ui/player/PlayerEventSource;)V", "getError", "()Ljava/lang/Throwable;", "getSource", "()Lcom/lagradost/cloudstream3/ui/player/PlayerEventSource;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class ErrorEvent extends PlayerEvent {
    private final Throwable error;
    private final PlayerEventSource source;

    public static /* synthetic */ ErrorEvent copy$default(ErrorEvent errorEvent, Throwable th, PlayerEventSource playerEventSource, int i, Object obj) {
        if ((i & 1) != 0) {
            th = errorEvent.error;
        }
        if ((i & 2) != 0) {
            playerEventSource = errorEvent.source;
        }
        return errorEvent.copy(th, playerEventSource);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Throwable getError() {
        return this.error;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final PlayerEventSource getSource() {
        return this.source;
    }

    public final ErrorEvent copy(Throwable error, PlayerEventSource source) {
        Intrinsics.checkNotNullParameter(error, "error");
        Intrinsics.checkNotNullParameter(source, "source");
        return new ErrorEvent(error, source);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ErrorEvent)) {
            return false;
        }
        ErrorEvent errorEvent = (ErrorEvent) other;
        return Intrinsics.areEqual(this.error, errorEvent.error) && this.source == errorEvent.source;
    }

    public int hashCode() {
        return (this.error.hashCode() * 31) + this.source.hashCode();
    }

    public String toString() {
        return "ErrorEvent(error=" + this.error + ", source=" + this.source + ')';
    }

    public ErrorEvent(Throwable error, PlayerEventSource source) {
        Intrinsics.checkNotNullParameter(error, "error");
        Intrinsics.checkNotNullParameter(source, "source");
        this.error = error;
        this.source = source;
    }

    public final Throwable getError() {
        return this.error;
    }

    public /* synthetic */ ErrorEvent(Throwable th, PlayerEventSource playerEventSource, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(th, (i & 2) != 0 ? PlayerEventSource.Player : playerEventSource);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerEvent
    public PlayerEventSource getSource() {
        return this.source;
    }
}
