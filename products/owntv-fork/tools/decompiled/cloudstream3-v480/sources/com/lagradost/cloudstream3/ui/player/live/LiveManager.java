package com.lagradost.cloudstream3.ui.player.live;

import androidx.media3.common.C;
import androidx.media3.common.Player;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LiveManager.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0013\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\fJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011J\u0006\u0010\u0013\u001a\u00020\u0014R\u0016\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0013\u0010\b\u001a\u0004\u0018\u00010\u00038F¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/live/LiveManager;", "", "player", "Landroidx/media3/common/Player;", "<init>", "(Landroidx/media3/common/Player;)V", "_currentPlayer", "Ljava/lang/ref/WeakReference;", "currentPlayer", "getCurrentPlayer", "()Landroidx/media3/common/Player;", "lastLivestreamChunk", "Lcom/lagradost/cloudstream3/ui/player/live/LivestreamChunk;", "submitLivestreamChunk", "", "chunk", "getTimeAheadOfLive", "", "position", "isAtLiveEdge", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class LiveManager {
    private WeakReference<Player> _currentPlayer;
    private LivestreamChunk lastLivestreamChunk;

    public final Player getCurrentPlayer() {
        WeakReference<Player> weakReference = this._currentPlayer;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    public LiveManager(Player player) {
        this._currentPlayer = new WeakReference<>(player);
    }

    public final void submitLivestreamChunk(LivestreamChunk chunk) {
        Intrinsics.checkNotNullParameter(chunk, "chunk");
        this.lastLivestreamChunk = chunk;
    }

    public final long getTimeAheadOfLive(long position) {
        long timeAheadOfLive;
        Player currentPlayer = getCurrentPlayer();
        if (currentPlayer == null || !currentPlayer.isCurrentMediaItemDynamic() || currentPlayer.getDuration() == C.TIME_UNSET) {
            return 0L;
        }
        if (currentPlayer.getCurrentLiveOffset() != C.TIME_UNSET && currentPlayer.getCurrentLiveOffset() < currentPlayer.getDuration()) {
            timeAheadOfLive = 5000 - ((currentPlayer.getCurrentLiveOffset() - currentPlayer.getCurrentPosition()) + position);
        } else {
            LivestreamChunk livestreamChunk = this.lastLivestreamChunk;
            timeAheadOfLive = livestreamChunk != null ? livestreamChunk.getTimeAheadOfLive(position) : 0L;
        }
        return Math.max(0L, timeAheadOfLive);
    }

    public final boolean isAtLiveEdge() {
        Player currentPlayer = getCurrentPlayer();
        if (currentPlayer != null && currentPlayer.isCurrentMediaItemDynamic() && currentPlayer.getDuration() != C.TIME_UNSET) {
            if (currentPlayer.getCurrentLiveOffset() != C.TIME_UNSET && currentPlayer.getCurrentLiveOffset() < currentPlayer.getDuration()) {
                return currentPlayer.getCurrentLiveOffset() < 11000;
            }
            LivestreamChunk livestreamChunk = this.lastLivestreamChunk;
            if (livestreamChunk != null && livestreamChunk.isPositionLive(currentPlayer.getCurrentPosition())) {
                return true;
            }
        }
        return false;
    }
}
