package com.lagradost.cloudstream3.ui.player.live;

import android.content.Context;
import android.util.AttributeSet;
import androidx.media3.common.Player;
import androidx.media3.ui.PlayerControlView;
import androidx.media3.ui.PlayerView;
import androidx.media3.ui.R;
import com.github.rubensousa.previewseekbar.media3.PreviewTimeBar;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LivePreviewTimeBar.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\fJ\u0006\u0010\u0014\u001a\u00020\u0015R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0016"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/live/LivePreviewTimeBar;", "Lcom/github/rubensousa/previewseekbar/media3/PreviewTimeBar;", "ctx", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "getCtx", "()Landroid/content/Context;", "_currentPlayerView", "Ljava/lang/ref/WeakReference;", "Landroidx/media3/ui/PlayerView;", "currentPlayer", "Landroidx/media3/common/Player;", "getCurrentPlayer", "()Landroidx/media3/common/Player;", "registerPlayerView", "", "player", "isAtLiveEdge", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class LivePreviewTimeBar extends PreviewTimeBar {
    private WeakReference<PlayerView> _currentPlayerView;
    private final Context ctx;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LivePreviewTimeBar(Context ctx, AttributeSet attrs) {
        super(ctx, attrs);
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        this.ctx = ctx;
    }

    public final Context getCtx() {
        return this.ctx;
    }

    public final Player getCurrentPlayer() {
        PlayerView playerView;
        WeakReference<PlayerView> weakReference = this._currentPlayerView;
        if (weakReference == null || (playerView = weakReference.get()) == null) {
            return null;
        }
        return playerView.getPlayer();
    }

    public final void registerPlayerView(PlayerView player) {
        WeakReference<PlayerView> weakReference = new WeakReference<>(player);
        this._currentPlayerView = weakReference;
        PlayerView playerView = weakReference.get();
        PlayerControlView playerControlView = playerView != null ? (PlayerControlView) playerView.findViewById(R.id.exo_controller) : null;
        if (playerControlView != null) {
            playerControlView.setProgressUpdateListener(new PlayerControlView.ProgressUpdateListener() { // from class: com.lagradost.cloudstream3.ui.player.live.LivePreviewTimeBar$$ExternalSyntheticLambda0
                @Override // androidx.media3.ui.PlayerControlView.ProgressUpdateListener
                public final void onProgressUpdate(long j, long j2) {
                    LivePreviewTimeBar.registerPlayerView$lambda$0(this.f$0, j, j2);
                }
            });
        }
    }

    static final void registerPlayerView$lambda$0(LivePreviewTimeBar livePreviewTimeBar, long j, long j2) {
        Player currentPlayer = livePreviewTimeBar.getCurrentPlayer();
        if (currentPlayer == null || !livePreviewTimeBar.isAtLiveEdge()) {
            return;
        }
        livePreviewTimeBar.setPosition(currentPlayer.getDuration());
    }

    public final boolean isAtLiveEdge() {
        LiveManager liveManager = LiveHelper.INSTANCE.getLiveManager(getCurrentPlayer());
        return liveManager != null && liveManager.isAtLiveEdge();
    }
}
