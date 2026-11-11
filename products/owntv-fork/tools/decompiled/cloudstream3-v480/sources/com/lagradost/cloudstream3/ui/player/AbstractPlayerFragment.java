package com.lagradost.cloudstream3.ui.player;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.session.MediaSession;
import androidx.media3.ui.SubtitleView;
import androidx.viewbinding.ViewBinding;
import com.lagradost.cloudstream3.ui.BaseFragment;
import com.lagradost.cloudstream3.utils.videoskip.VideoSkipStamp;
import java.util.List;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: AbstractPlayerFragment.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u009e\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\u00020\u0004B\u0015\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010/\u001a\u000200H\u0016J\b\u00101\u001a\u000200H\u0016J\u0018\u00102\u001a\u0002002\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u000204H\u0016J\u0018\u00106\u001a\u0002002\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u000208H\u0016J\b\u0010:\u001a\u000200H\u0016J\u0016\u0010;\u001a\u0002002\f\u0010<\u001a\b\u0012\u0004\u0012\u00020>0=H\u0016J\b\u0010?\u001a\u000200H\u0016J\b\u0010@\u001a\u000200H\u0016J\b\u0010A\u001a\u00020BH\u0016J\b\u0010C\u001a\u000200H\u0016J\u0010\u0010D\u001a\u0002002\u0006\u0010E\u001a\u00020FH\u0016J\u0010\u0010G\u001a\u0002002\u0006\u0010H\u001a\u00020IH\u0016J\u001f\u0010J\u001a\u0002002\u0006\u0010K\u001a\u00028\u00002\b\u0010L\u001a\u0004\u0018\u00010MH\u0016¢\u0006\u0002\u0010NJ\u0010\u0010O\u001a\u0002002\u0006\u0010P\u001a\u00020BH\u0016J\b\u0010Q\u001a\u000200H\u0016J\b\u0010R\u001a\u000200H\u0016J\b\u0010S\u001a\u000200H\u0016J\b\u0010T\u001a\u000200H\u0016J\u0006\u0010U\u001a\u000200J\u0018\u0010V\u001a\u0002002\u0006\u0010V\u001a\u00020W2\u0006\u0010X\u001a\u00020BH\u0016R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R$\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\n8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u00188F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u001c8F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0013\u0010\u001f\u001a\u0004\u0018\u00010 8F¢\u0006\u0006\u001a\u0004\b!\u0010\"R$\u0010$\u001a\u00020#2\u0006\u0010\u0011\u001a\u00020#8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R(\u0010*\u001a\u0004\u0018\u00010)2\b\u0010\u0011\u001a\u0004\u0018\u00010)8D@DX\u0084\u000e¢\u0006\f\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.¨\u0006Y"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/AbstractPlayerFragment;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/viewbinding/ViewBinding;", "Lcom/lagradost/cloudstream3/ui/BaseFragment;", "Lcom/lagradost/cloudstream3/ui/player/PlayerView$Callbacks;", "bindingCreator", "Lcom/lagradost/cloudstream3/ui/BaseFragment$BindingCreator;", "<init>", "(Lcom/lagradost/cloudstream3/ui/BaseFragment$BindingCreator;)V", "_player", "Lcom/lagradost/cloudstream3/ui/player/IPlayer;", "playerHostView", "Lcom/lagradost/cloudstream3/ui/player/PlayerView;", "getPlayerHostView", "()Lcom/lagradost/cloudstream3/ui/player/PlayerView;", "setPlayerHostView", "(Lcom/lagradost/cloudstream3/ui/player/PlayerView;)V", ES6Iterator.VALUE_PROPERTY, "player", "getPlayer", "()Lcom/lagradost/cloudstream3/ui/player/IPlayer;", "setPlayer", "(Lcom/lagradost/cloudstream3/ui/player/IPlayer;)V", "subView", "Landroidx/media3/ui/SubtitleView;", "getSubView", "()Landroidx/media3/ui/SubtitleView;", "playerPausePlay", "Landroid/widget/ImageView;", "getPlayerPausePlay", "()Landroid/widget/ImageView;", "playerView", "Landroidx/media3/ui/PlayerView;", "getPlayerView", "()Landroidx/media3/ui/PlayerView;", "Lcom/lagradost/cloudstream3/ui/player/CSPlayerLoading;", "currentPlayerStatus", "getCurrentPlayerStatus", "()Lcom/lagradost/cloudstream3/ui/player/CSPlayerLoading;", "setCurrentPlayerStatus", "(Lcom/lagradost/cloudstream3/ui/player/CSPlayerLoading;)V", "Landroidx/media3/session/MediaSession;", "mMediaSession", "getMMediaSession", "()Landroidx/media3/session/MediaSession;", "setMMediaSession", "(Landroidx/media3/session/MediaSession;)V", "nextEpisode", "", "prevEpisode", "playerPositionChanged", "position", "", TypedValues.TransitionType.S_DURATION, "playerDimensionsLoaded", "width", "", "height", "subtitlesChanged", "embeddedSubtitlesFetched", "subtitles", "", "Lcom/lagradost/cloudstream3/ui/player/SubtitleData;", "onTracksInfoChanged", "exitedPipMode", "hasNextMirror", "", "nextMirror", "playerError", "exception", "", "fixLayout", "view", "Landroid/view/View;", "onBindingCreated", "binding", "savedInstanceState", "Landroid/os/Bundle;", "(Landroidx/viewbinding/ViewBinding;Landroid/os/Bundle;)V", "onPictureInPictureModeChanged", "isInPictureInPictureMode", "onDestroy", "onPause", "onStop", "onResume", "nextResize", "resize", "Lcom/lagradost/cloudstream3/ui/player/PlayerResize;", "showToast", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class AbstractPlayerFragment<T extends ViewBinding> extends BaseFragment<T> implements PlayerView.Callbacks {
    private IPlayer _player;
    private PlayerView playerHostView;

    @Override // com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void fixLayout(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractPlayerFragment(BaseFragment.BindingCreator<T> bindingCreator) {
        super(bindingCreator);
        Intrinsics.checkNotNullParameter(bindingCreator, "bindingCreator");
        this._player = new CS3IPlayer();
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public /* bridge */ boolean isUIShowing() {
        return PlayerView.Callbacks.CC.$default$isUIShowing(this);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public /* bridge */ void onAutoHideUI() {
        PlayerView.Callbacks.CC.$default$onAutoHideUI(this);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public /* bridge */ void onBrightnessExtra(float f) {
        PlayerView.Callbacks.CC.$default$onBrightnessExtra(this, f);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public /* bridge */ void onDownload(DownloadEvent downloadEvent) {
        Intrinsics.checkNotNullParameter(downloadEvent, "event");
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public /* bridge */ void onGestureEnd(boolean z, boolean z2) {
        PlayerView.Callbacks.CC.$default$onGestureEnd(this, z, z2);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public /* bridge */ void onHidePlayerUI() {
        PlayerView.Callbacks.CC.$default$onHidePlayerUI(this);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public /* bridge */ void onHoldSpeedUp(boolean z) {
        PlayerView.Callbacks.CC.$default$onHoldSpeedUp(this, z);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public /* bridge */ void onSeekPreviewText(String str) {
        PlayerView.Callbacks.CC.$default$onSeekPreviewText(this, str);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public /* bridge */ void onSingleTap() {
        PlayerView.Callbacks.CC.$default$onSingleTap(this);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public /* bridge */ void onTimestamp(VideoSkipStamp videoSkipStamp) {
        PlayerView.Callbacks.CC.$default$onTimestamp(this, videoSkipStamp);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public /* bridge */ void onTimestampSkipped(VideoSkipStamp videoSkipStamp) {
        Intrinsics.checkNotNullParameter(videoSkipStamp, "timestamp");
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public /* bridge */ void onTouchDown() {
        PlayerView.Callbacks.CC.$default$onTouchDown(this);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public /* bridge */ void playerStatusChanged() {
        PlayerView.Callbacks.CC.$default$playerStatusChanged(this);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public /* bridge */ void playerUpdated(Object obj) {
        PlayerView.Callbacks.CC.$default$playerUpdated(this, obj);
    }

    protected final PlayerView getPlayerHostView() {
        return this.playerHostView;
    }

    protected final void setPlayerHostView(PlayerView playerView) {
        this.playerHostView = playerView;
    }

    public final IPlayer getPlayer() {
        IPlayer player;
        PlayerView playerView = this.playerHostView;
        return (playerView == null || (player = playerView.getPlayer()) == null) ? this._player : player;
    }

    public final void setPlayer(IPlayer value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this._player = value;
        PlayerView playerView = this.playerHostView;
        if (playerView != null) {
            playerView.setPlayer(value);
        }
    }

    public final SubtitleView getSubView() {
        PlayerView playerView = this.playerHostView;
        if (playerView != null) {
            return playerView.getSubView();
        }
        return null;
    }

    public final ImageView getPlayerPausePlay() {
        PlayerView playerView = this.playerHostView;
        if (playerView != null) {
            return playerView.getPlayerPausePlay();
        }
        return null;
    }

    public final androidx.media3.ui.PlayerView getPlayerView() {
        PlayerView playerView = this.playerHostView;
        if (playerView != null) {
            return playerView.getExoPlayerView();
        }
        return null;
    }

    public final CSPlayerLoading getCurrentPlayerStatus() {
        CSPlayerLoading currentPlayerStatus;
        PlayerView playerView = this.playerHostView;
        return (playerView == null || (currentPlayerStatus = playerView.getCurrentPlayerStatus()) == null) ? CSPlayerLoading.IsBuffering : currentPlayerStatus;
    }

    public final void setCurrentPlayerStatus(CSPlayerLoading value) {
        Intrinsics.checkNotNullParameter(value, "value");
        PlayerView playerView = this.playerHostView;
        if (playerView != null) {
            playerView.setCurrentPlayerStatus(value);
        }
    }

    protected final MediaSession getMMediaSession() {
        PlayerView playerView = this.playerHostView;
        if (playerView != null) {
            return playerView.getMMediaSession();
        }
        return null;
    }

    protected final void setMMediaSession(MediaSession mediaSession) {
        PlayerView playerView = this.playerHostView;
        if (playerView != null) {
            playerView.setMMediaSession(mediaSession);
        }
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void nextEpisode() {
        throw new NotImplementedError(null, 1, null);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void prevEpisode() {
        throw new NotImplementedError(null, 1, null);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void playerPositionChanged(long position, long duration) {
        throw new NotImplementedError(null, 1, null);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void playerDimensionsLoaded(int width, int height) {
        throw new NotImplementedError(null, 1, null);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void subtitlesChanged() {
        throw new NotImplementedError(null, 1, null);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void embeddedSubtitlesFetched(List<SubtitleData> subtitles) {
        Intrinsics.checkNotNullParameter(subtitles, "subtitles");
        throw new NotImplementedError(null, 1, null);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void onTracksInfoChanged() {
        throw new NotImplementedError(null, 1, null);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void exitedPipMode() {
        throw new NotImplementedError(null, 1, null);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public boolean hasNextMirror() {
        throw new NotImplementedError(null, 1, null);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void nextMirror() {
        throw new NotImplementedError(null, 1, null);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void playerError(Throwable exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        PlayerView playerView = this.playerHostView;
        if (playerView != null) {
            playerView.playerError(exception);
        }
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragment, com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void onBindingCreated(T binding, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(binding, "binding");
        Context context = getContext();
        if (context == null) {
            return;
        }
        PlayerView playerView = new PlayerView(context, null, 2, null);
        this.playerHostView = playerView;
        playerView.setPlayer(this._player);
        PlayerView playerView2 = this.playerHostView;
        if (playerView2 != null) {
            playerView2.setCallbacks(this);
        }
        PlayerView playerView3 = this.playerHostView;
        if (playerView3 != null) {
            View root = binding.getRoot();
            Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
            playerView3.bindViews(root);
        }
        PlayerView playerView4 = this.playerHostView;
        if (playerView4 != null) {
            playerView4.initialize();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode);
        PlayerView playerView = this.playerHostView;
        if (playerView != null) {
            playerView.onPictureInPictureModeChanged(isInPictureInPictureMode, getActivity());
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        PlayerView playerView = this.playerHostView;
        if (playerView != null) {
            playerView.release();
        }
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        PlayerView playerView = this.playerHostView;
        if (playerView != null) {
            playerView.releaseKeyEventListener();
        }
        super.onPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        PlayerView playerView = this.playerHostView;
        if (playerView != null) {
            playerView.onStop();
        }
        super.onStop();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        PlayerView playerView;
        Context context = getContext();
        if (context != null && (playerView = this.playerHostView) != null) {
            playerView.onResume(context);
        }
        super.onResume();
    }

    public final void nextResize() {
        PlayerView playerView = this.playerHostView;
        if (playerView != null) {
            playerView.nextResize();
        }
    }

    public void resize(PlayerResize resize, boolean showToast) {
        Intrinsics.checkNotNullParameter(resize, "resize");
        PlayerView playerView = this.playerHostView;
        if (playerView != null) {
            playerView.resize(resize, showToast);
        }
    }
}
