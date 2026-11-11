package com.lagradost.cloudstream3.ui.result;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentActivity;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.LoadResponse;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.databinding.FragmentResultBinding;
import com.lagradost.cloudstream3.databinding.FragmentResultSwipeBinding;
import com.lagradost.cloudstream3.databinding.FragmentTrailerBinding;
import com.lagradost.cloudstream3.databinding.TrailerCustomLayoutBinding;
import com.lagradost.cloudstream3.ui.player.CSPlayerEvent;
import com.lagradost.cloudstream3.ui.player.CSPlayerLoading;
import com.lagradost.cloudstream3.ui.player.PlayerEventSource;
import com.lagradost.cloudstream3.ui.player.PlayerGestureHelper;
import com.lagradost.cloudstream3.ui.player.PlayerView;
import com.lagradost.cloudstream3.ui.player.SubtitleData;
import com.lagradost.cloudstream3.utils.BackPressedCallbackHelper;
import com.lagradost.cloudstream3.utils.UIHelper;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ResultTrailerPlayer.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 H2\u00020\u0001:\u0001HB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0014H\u0002J\b\u0010\u0016\u001a\u00020\u0005H\u0016J\b\u0010\u0017\u001a\u00020\u0014H\u0016J\b\u0010\u0018\u001a\u00020\u0014H\u0016J\u0010\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u0005H\u0016J\u0018\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u0005H\u0016J\b\u0010\u001e\u001a\u00020\u0014H\u0016J\b\u0010\u001f\u001a\u00020\u0014H\u0016J\u0018\u0010 \u001a\u00020\u00142\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"H\u0016J\b\u0010$\u001a\u00020\u0014H\u0016J\u0010\u0010%\u001a\u00020\u00142\u0006\u0010&\u001a\u00020'H\u0016J\b\u0010(\u001a\u00020\u0014H\u0002J\u0018\u0010)\u001a\u00020\u00142\u0006\u0010*\u001a\u00020\u00112\u0006\u0010+\u001a\u00020\u0011H\u0016J\b\u0010,\u001a\u00020\u0014H\u0016J\b\u0010-\u001a\u00020\u0014H\u0016J(\u0010.\u001a\u00020\u00142\u0006\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u0001022\f\u00103\u001a\b\u0012\u0004\u0012\u00020\u001404H\u0016J\b\u00105\u001a\u00020\u0014H\u0016J\u0016\u00106\u001a\u00020\u00142\f\u00107\u001a\b\u0012\u0004\u0012\u00020908H\u0016J\b\u0010:\u001a\u00020\u0014H\u0016J\b\u0010;\u001a\u00020\u0014H\u0016J\u0012\u0010<\u001a\u00020\u00142\b\u0010=\u001a\u0004\u0018\u00010>H\u0016J\u0010\u0010?\u001a\u00020\u00142\u0006\u0010@\u001a\u00020\u0005H\u0002J\b\u0010A\u001a\u00020\u0014H\u0016J\b\u0010B\u001a\u00020\u0014H\u0016J\u001a\u0010C\u001a\u00020\u00142\u0006\u0010D\u001a\u00020E2\b\u0010F\u001a\u0004\u0018\u00010GH\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0094\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X\u0094\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\tR\u001a\u0010\f\u001a\u00020\u0005X\u0094\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0007\"\u0004\b\u000e\u0010\tR\u001c\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006I"}, d2 = {"Lcom/lagradost/cloudstream3/ui/result/ResultTrailerPlayer;", "Lcom/lagradost/cloudstream3/ui/result/ResultFragmentPhone;", "<init>", "()V", "lockRotation", "", "getLockRotation", "()Z", "setLockRotation", "(Z)V", "isFullScreenPlayer", "setFullScreenPlayer", "hasPipModeSupport", "getHasPipModeSupport", "setHasPipModeSupport", "playerWidthHeight", "Lkotlin/Pair;", "", "introVisible", "onSingleTap", "", "showControls", "isUIShowing", "onAutoHideUI", "onHidePlayerUI", "onHoldSpeedUp", "show", "onGestureEnd", "hadSwipe", "wasUiShowing", "nextEpisode", "prevEpisode", "playerPositionChanged", "position", "", TypedValues.TransitionType.S_DURATION, "nextMirror", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "fixPlayerSize", "playerDimensionsLoaded", "width", "height", "showMirrorsDialogue", "showTracksDialogue", "openOnlineSubPicker", "context", "Landroid/content/Context;", "loadResponse", "Lcom/lagradost/cloudstream3/LoadResponse;", "dismissCallback", "Lkotlin/Function0;", "subtitlesChanged", "embeddedSubtitlesFetched", "subtitles", "", "Lcom/lagradost/cloudstream3/ui/player/SubtitleData;", "onTracksInfoChanged", "exitedPipMode", "onSeekPreviewText", "text", "", "updateFullscreen", "fullscreen", "updateUIVisibility", "playerStatusChanged", "onBindingCreated", "binding", "Lcom/lagradost/cloudstream3/databinding/FragmentResultSwipeBinding;", "savedInstanceState", "Landroid/os/Bundle;", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ResultTrailerPlayer extends ResultFragmentPhone {
    public static final String TAG = "ResultTrailerPlayer";
    private boolean hasPipModeSupport;
    private boolean introVisible = true;
    private boolean isFullScreenPlayer;
    private boolean lockRotation;
    private Pair<Integer, Integer> playerWidthHeight;

    @Override // com.lagradost.cloudstream3.ui.result.ResultFragmentPhone, com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void embeddedSubtitlesFetched(List<SubtitleData> subtitles) {
        Intrinsics.checkNotNullParameter(subtitles, "subtitles");
    }

    @Override // com.lagradost.cloudstream3.ui.result.ResultFragmentPhone, com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void exitedPipMode() {
    }

    @Override // com.lagradost.cloudstream3.ui.result.ResultFragmentPhone, com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void nextEpisode() {
    }

    @Override // com.lagradost.cloudstream3.ui.result.ResultFragmentPhone, com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void nextMirror() {
    }

    @Override // com.lagradost.cloudstream3.ui.result.ResultFragmentPhone, com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void onTracksInfoChanged() {
    }

    @Override // com.lagradost.cloudstream3.ui.result.ResultFragmentPhone
    public void openOnlineSubPicker(Context context, LoadResponse loadResponse, Function0<Unit> dismissCallback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(dismissCallback, "dismissCallback");
    }

    @Override // com.lagradost.cloudstream3.ui.result.ResultFragmentPhone, com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void playerPositionChanged(long position, long duration) {
    }

    @Override // com.lagradost.cloudstream3.ui.result.ResultFragmentPhone, com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void prevEpisode() {
    }

    @Override // com.lagradost.cloudstream3.ui.result.ResultFragmentPhone
    public void showMirrorsDialogue() {
    }

    @Override // com.lagradost.cloudstream3.ui.result.ResultFragmentPhone
    public void showTracksDialogue() {
    }

    @Override // com.lagradost.cloudstream3.ui.result.ResultFragmentPhone, com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void subtitlesChanged() {
    }

    @Override // com.lagradost.cloudstream3.ui.result.ResultFragmentPhone
    protected boolean getLockRotation() {
        return this.lockRotation;
    }

    @Override // com.lagradost.cloudstream3.ui.result.ResultFragmentPhone
    protected void setLockRotation(boolean z) {
        this.lockRotation = z;
    }

    @Override // com.lagradost.cloudstream3.ui.result.ResultFragmentPhone
    /* JADX INFO: renamed from: isFullScreenPlayer, reason: from getter */
    protected boolean getIsFullScreenPlayer() {
        return this.isFullScreenPlayer;
    }

    @Override // com.lagradost.cloudstream3.ui.result.ResultFragmentPhone
    protected void setFullScreenPlayer(boolean z) {
        this.isFullScreenPlayer = z;
    }

    @Override // com.lagradost.cloudstream3.ui.result.ResultFragmentPhone
    protected boolean getHasPipModeSupport() {
        return this.hasPipModeSupport;
    }

    @Override // com.lagradost.cloudstream3.ui.result.ResultFragmentPhone
    protected void setHasPipModeSupport(boolean z) {
        this.hasPipModeSupport = z;
    }

    @Override // com.lagradost.cloudstream3.ui.result.ResultFragmentPhone, com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void onSingleTap() {
        if (this.introVisible) {
            return;
        }
        if (getIsShowing()) {
            uiReset();
        } else {
            showControls();
        }
    }

    private final void showControls() {
        if (this.introVisible) {
            return;
        }
        setShowing(true);
        updateUIVisibility();
        PlayerView playerHostView = getPlayerHostView();
        if (playerHostView != null) {
            PlayerView.scheduleAutoHide$default(playerHostView, 0L, 1, null);
        }
    }

    @Override // com.lagradost.cloudstream3.ui.result.ResultFragmentPhone, com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public boolean isUIShowing() {
        return getIsShowing();
    }

    @Override // com.lagradost.cloudstream3.ui.result.ResultFragmentPhone, com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void onAutoHideUI() {
        if (getPlayer().getIsPlaying()) {
            uiReset();
        }
    }

    @Override // com.lagradost.cloudstream3.ui.result.ResultFragmentPhone, com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void onHidePlayerUI() {
        uiReset();
    }

    @Override // com.lagradost.cloudstream3.ui.result.ResultFragmentPhone, com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void onHoldSpeedUp(boolean show) {
        if (show && getIsShowing()) {
            uiReset();
        }
    }

    @Override // com.lagradost.cloudstream3.ui.result.ResultFragmentPhone, com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void onGestureEnd(boolean hadSwipe, boolean wasUiShowing) {
        if (!getPlayer().getIsPlaying() && hadSwipe && wasUiShowing && !getIsShowing()) {
            setShowing(true);
            showControls();
        } else {
            PlayerView playerHostView = getPlayerHostView();
            if (playerHostView != null) {
                PlayerView.scheduleAutoHide$default(playerHostView, 0L, 1, null);
            }
        }
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragment, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        uiReset();
        fixPlayerSize();
    }

    private final void fixPlayerSize() {
        Context context;
        Resources resources;
        Configuration configuration;
        FrameLayout frameLayout;
        FragmentResultBinding resultBinding;
        final FrameLayout frameLayout2;
        FrameLayout frameLayout3;
        FrameLayout frameLayout4;
        FragmentTrailerBinding fragmentTrailerBinding;
        ConstraintLayout constraintLayout;
        FrameLayout frameLayout5;
        FrameLayout frameLayout6;
        FragmentResultSwipeBinding binding = getBinding();
        if (binding != null) {
            if (getIsFullScreenPlayer()) {
                if (Build.VERSION.SDK_INT >= 30) {
                    ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), null);
                    binding.getRoot().getOverlay().clear();
                }
                binding.getRoot().setPadding(0, 0, 0, 0);
            } else {
                UIHelper uIHelper = UIHelper.INSTANCE;
                LinearLayout root = binding.getRoot();
                Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
                UIHelper.fixSystemBarsPadding$default(uIHelper, root, null, null, false, false, false, false, false, false, TypedValues.PositionType.TYPE_POSITION_TYPE, null);
                if (Build.VERSION.SDK_INT >= 30) {
                    ViewCompat.requestApplyInsets(binding.getRoot());
                }
            }
        }
        Pair<Integer, Integer> pair = this.playerWidthHeight;
        if (pair != null) {
            int iIntValue = pair.component1().intValue();
            int iIntValue2 = pair.component2().intValue();
            if (iIntValue <= 0 || iIntValue2 <= 0 || (context = getContext()) == null || (resources = context.getResources()) == null || (configuration = resources.getConfiguration()) == null) {
                return;
            }
            int screenWidth = configuration.orientation == 2 ? CommonActivity.INSTANCE.getScreenWidth() : CommonActivity.INSTANCE.getScreenHeight();
            FragmentResultBinding resultBinding2 = getResultBinding();
            if (resultBinding2 != null && (frameLayout6 = resultBinding2.resultSmallscreenHolder) != null) {
                frameLayout6.setVisibility(!getIsFullScreenPlayer() ? 0 : 8);
            }
            FragmentResultSwipeBinding binding2 = getBinding();
            if (binding2 != null && (frameLayout5 = binding2.resultFullscreenHolder) != null) {
                frameLayout5.setVisibility(getIsFullScreenPlayer() ? 0 : 8);
            }
            int i = (screenWidth * iIntValue2) / iIntValue;
            FragmentResultBinding resultBinding3 = getResultBinding();
            if (resultBinding3 != null && (fragmentTrailerBinding = resultBinding3.fragmentTrailer) != null && (constraintLayout = fragmentTrailerBinding.playerBackground) != null) {
                constraintLayout.setVisibility(0);
                constraintLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, getIsFullScreenPlayer() ? -1 : i));
            }
            TrailerCustomLayoutBinding playerBinding = getPlayerBinding();
            if (playerBinding != null && (frameLayout3 = playerBinding.playerIntroPlay) != null) {
                FragmentResultBinding resultBinding4 = getResultBinding();
                frameLayout3.setLayoutParams(new FrameLayout.LayoutParams(-1, (resultBinding4 == null || (frameLayout4 = resultBinding4.resultTopHolder) == null) ? -1 : frameLayout4.getMeasuredHeight()));
            }
            TrailerCustomLayoutBinding playerBinding2 = getPlayerBinding();
            if (playerBinding2 == null || (frameLayout = playerBinding2.playerIntroPlay) == null || frameLayout.getVisibility() != 8 || (resultBinding = getResultBinding()) == null || (frameLayout2 = resultBinding.resultTopHolder) == null) {
                return;
            }
            int measuredHeight = frameLayout2.getMeasuredHeight();
            if (getIsFullScreenPlayer()) {
                i = -1;
            }
            ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(measuredHeight, i);
            valueAnimatorOfInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultTrailerPlayer$$ExternalSyntheticLambda3
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    ResultTrailerPlayer.fixPlayerSize$lambda$1$2$0(frameLayout2, valueAnimator);
                }
            });
            valueAnimatorOfInt.setDuration(200L);
            valueAnimatorOfInt.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void fixPlayerSize$lambda$1$2$0(FrameLayout frameLayout, ValueAnimator va) {
        Intrinsics.checkNotNullParameter(va, "va");
        Object animatedValue = va.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Int");
        int iIntValue = ((Integer) animatedValue).intValue();
        ViewGroup.LayoutParams layoutParams = frameLayout.getLayoutParams();
        Intrinsics.checkNotNullExpressionValue(layoutParams, "getLayoutParams(...)");
        layoutParams.height = iIntValue;
        frameLayout.setLayoutParams(layoutParams);
    }

    @Override // com.lagradost.cloudstream3.ui.result.ResultFragmentPhone, com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void playerDimensionsLoaded(int width, int height) {
        FragmentActivity activity;
        PlayerView playerHostView;
        this.playerWidthHeight = TuplesKt.to(Integer.valueOf(width), Integer.valueOf(height));
        fixPlayerSize();
        if (!getLockRotation() || (activity = getActivity()) == null || (playerHostView = getPlayerHostView()) == null) {
            return;
        }
        activity.setRequestedOrientation(playerHostView.dynamicOrientation());
    }

    @Override // com.lagradost.cloudstream3.ui.result.ResultFragmentPhone, com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void onSeekPreviewText(String text) {
        TextView textView;
        TrailerCustomLayoutBinding playerBinding = getPlayerBinding();
        if (playerBinding == null || (textView = playerBinding.playerTimeText) == null) {
            return;
        }
        textView.setVisibility(text != null ? 0 : 8);
        if (text != null) {
            textView.setText(text);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateFullscreen(boolean fullscreen) {
        FragmentTrailerBinding fragmentTrailerBinding;
        ConstraintLayout constraintLayout;
        FrameLayout frameLayout;
        FragmentTrailerBinding fragmentTrailerBinding2;
        ConstraintLayout constraintLayout2;
        FrameLayout frameLayout2;
        ImageView imageView;
        setFullScreenPlayer(fullscreen);
        setLockRotation(fullscreen);
        PlayerView playerHostView = getPlayerHostView();
        if (playerHostView != null) {
            playerHostView.setFullScreen(fullscreen);
        }
        TrailerCustomLayoutBinding playerBinding = getPlayerBinding();
        if (playerBinding != null && (imageView = playerBinding.playerFullscreen) != null) {
            imageView.setImageResource(fullscreen ? R.drawable.baseline_fullscreen_exit_24 : R.drawable.baseline_fullscreen_24);
        }
        if (fullscreen) {
            PlayerView playerHostView2 = getPlayerHostView();
            if (playerHostView2 != null) {
                PlayerView.enterFullscreen$default(playerHostView2, null, 1, null);
            }
            FragmentResultSwipeBinding binding = getBinding();
            if (binding != null) {
                FrameLayout resultTopBar = binding.resultTopBar;
                Intrinsics.checkNotNullExpressionValue(resultTopBar, "resultTopBar");
                resultTopBar.setVisibility(8);
                FrameLayout resultFullscreenHolder = binding.resultFullscreenHolder;
                Intrinsics.checkNotNullExpressionValue(resultFullscreenHolder, "resultFullscreenHolder");
                resultFullscreenHolder.setVisibility(0);
                FrameLayout resultMainHolder = binding.resultMainHolder;
                Intrinsics.checkNotNullExpressionValue(resultMainHolder, "resultMainHolder");
                resultMainHolder.setVisibility(8);
            }
            FragmentResultBinding resultBinding = getResultBinding();
            if (resultBinding != null && (fragmentTrailerBinding2 = resultBinding.fragmentTrailer) != null && (constraintLayout2 = fragmentTrailerBinding2.playerBackground) != null) {
                ViewGroup viewGroup = (ViewGroup) constraintLayout2.getParent();
                if (viewGroup != null) {
                    viewGroup.removeView(constraintLayout2);
                }
                FragmentResultSwipeBinding binding2 = getBinding();
                if (binding2 != null && (frameLayout2 = binding2.resultFullscreenHolder) != null) {
                    frameLayout2.addView(constraintLayout2);
                }
            }
        } else {
            FragmentResultSwipeBinding binding3 = getBinding();
            if (binding3 != null) {
                FrameLayout resultTopBar2 = binding3.resultTopBar;
                Intrinsics.checkNotNullExpressionValue(resultTopBar2, "resultTopBar");
                resultTopBar2.setVisibility(0);
                FrameLayout resultFullscreenHolder2 = binding3.resultFullscreenHolder;
                Intrinsics.checkNotNullExpressionValue(resultFullscreenHolder2, "resultFullscreenHolder");
                resultFullscreenHolder2.setVisibility(8);
                FrameLayout resultMainHolder2 = binding3.resultMainHolder;
                Intrinsics.checkNotNullExpressionValue(resultMainHolder2, "resultMainHolder");
                resultMainHolder2.setVisibility(0);
                FragmentResultBinding resultBinding2 = getResultBinding();
                if (resultBinding2 != null && (fragmentTrailerBinding = resultBinding2.fragmentTrailer) != null && (constraintLayout = fragmentTrailerBinding.playerBackground) != null) {
                    ViewGroup viewGroup2 = (ViewGroup) constraintLayout.getParent();
                    if (viewGroup2 != null) {
                        viewGroup2.removeView(constraintLayout);
                    }
                    FragmentResultBinding resultBinding3 = getResultBinding();
                    if (resultBinding3 != null && (frameLayout = resultBinding3.resultSmallscreenHolder) != null) {
                        frameLayout.addView(constraintLayout);
                    }
                }
            }
            PlayerView playerHostView3 = getPlayerHostView();
            if (playerHostView3 != null) {
                playerHostView3.exitFullscreen();
            }
        }
        fixPlayerSize();
        uiReset();
        if (getIsFullScreenPlayer()) {
            FragmentActivity activity = getActivity();
            if (activity != null) {
                BackPressedCallbackHelper.INSTANCE.attachBackPressedCallback(activity, TAG, new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultTrailerPlayer$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ResultTrailerPlayer.updateFullscreen$lambda$3(this.f$0, (BackPressedCallbackHelper.CallbackHelper) obj);
                    }
                });
                return;
            }
            return;
        }
        FragmentActivity activity2 = getActivity();
        if (activity2 != null) {
            BackPressedCallbackHelper.INSTANCE.detachBackPressedCallback(activity2, TAG);
        }
    }

    static final Unit updateFullscreen$lambda$3(ResultTrailerPlayer resultTrailerPlayer, BackPressedCallbackHelper.CallbackHelper attachBackPressedCallback) {
        Intrinsics.checkNotNullParameter(attachBackPressedCallback, "$this$attachBackPressedCallback");
        resultTrailerPlayer.updateFullscreen(false);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:31:0x0073  */
    @Override // com.lagradost.cloudstream3.ui.result.ResultFragmentPhone
    public void updateUIVisibility() {
        PlayerGestureHelper gestureHelper;
        boolean z;
        super.updateUIVisibility();
        TrailerCustomLayoutBinding playerBinding = getPlayerBinding();
        if (playerBinding != null) {
            LinearLayout playerGoBackHolder = playerBinding.playerGoBackHolder;
            Intrinsics.checkNotNullExpressionValue(playerGoBackHolder, "playerGoBackHolder");
            playerGoBackHolder.setVisibility(8);
            boolean z2 = getIsShowing() && !this.introVisible;
            FrameLayout playerTopHolder = playerBinding.playerTopHolder;
            Intrinsics.checkNotNullExpressionValue(playerTopHolder, "playerTopHolder");
            playerTopHolder.setVisibility(z2 ? 0 : 8);
            ConstraintLayout playerVideoHolder = playerBinding.playerVideoHolder;
            Intrinsics.checkNotNullExpressionValue(playerVideoHolder, "playerVideoHolder");
            playerVideoHolder.setVisibility(z2 ? 0 : 8);
            View shadowOverlay = playerBinding.shadowOverlay;
            Intrinsics.checkNotNullExpressionValue(shadowOverlay, "shadowOverlay");
            shadowOverlay.setVisibility(z2 ? 0 : 8);
            FrameLayout playerPausePlayHolderHolder = playerBinding.playerPausePlayHolderHolder;
            Intrinsics.checkNotNullExpressionValue(playerPausePlayHolderHolder, "playerPausePlayHolderHolder");
            FrameLayout frameLayout = playerPausePlayHolderHolder;
            if (z2) {
                PlayerView playerHostView = getPlayerHostView();
                z = (playerHostView != null ? playerHostView.getCurrentPlayerStatus() : null) != CSPlayerLoading.IsBuffering;
            }
            frameLayout.setVisibility(z ? 0 : 8);
        }
        PlayerView playerHostView2 = getPlayerHostView();
        if (playerHostView2 == null || (gestureHelper = playerHostView2.getGestureHelper()) == null) {
            return;
        }
        gestureHelper.animateCenterControls((!getIsShowing() || this.introVisible) ? 0.0f : 1.0f);
    }

    @Override // com.lagradost.cloudstream3.ui.result.ResultFragmentPhone, com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void playerStatusChanged() {
        TrailerCustomLayoutBinding playerBinding;
        FrameLayout frameLayout;
        if (!this.introVisible || (playerBinding = getPlayerBinding()) == null || (frameLayout = playerBinding.playerPausePlayHolderHolder) == null) {
            return;
        }
        frameLayout.setVisibility(8);
    }

    @Override // com.lagradost.cloudstream3.ui.result.ResultFragmentPhone, com.lagradost.cloudstream3.ui.BaseFragment, com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void onBindingCreated(FragmentResultSwipeBinding binding, Bundle savedInstanceState) {
        FrameLayout frameLayout;
        ImageView imageView;
        Intrinsics.checkNotNullParameter(binding, "binding");
        super.onBindingCreated(binding, savedInstanceState);
        PlayerView playerHostView = getPlayerHostView();
        if (playerHostView != null) {
            TrailerCustomLayoutBinding playerBinding = getPlayerBinding();
            playerHostView.setVideoOutline(playerBinding != null ? playerBinding.videoOutline : null);
        }
        PlayerView playerHostView2 = getPlayerHostView();
        if (playerHostView2 != null) {
            playerHostView2.requestUpdateBrightnessOverlayOnNextLayout();
        }
        TrailerCustomLayoutBinding playerBinding2 = getPlayerBinding();
        if (playerBinding2 != null && (imageView = playerBinding2.playerFullscreen) != null) {
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultTrailerPlayer$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ResultTrailerPlayer resultTrailerPlayer = this.f$0;
                    resultTrailerPlayer.updateFullscreen(!resultTrailerPlayer.getIsFullScreenPlayer());
                }
            });
        }
        updateFullscreen(getIsFullScreenPlayer());
        uiReset();
        TrailerCustomLayoutBinding playerBinding3 = getPlayerBinding();
        if (playerBinding3 == null || (frameLayout = playerBinding3.playerIntroPlay) == null) {
            return;
        }
        frameLayout.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultTrailerPlayer$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ResultTrailerPlayer.onBindingCreated$lambda$1(this.f$0, view);
            }
        });
    }

    static final void onBindingCreated$lambda$1(ResultTrailerPlayer resultTrailerPlayer, View view) {
        FrameLayout frameLayout;
        TrailerCustomLayoutBinding playerBinding = resultTrailerPlayer.getPlayerBinding();
        if (playerBinding != null && (frameLayout = playerBinding.playerIntroPlay) != null) {
            frameLayout.setVisibility(8);
        }
        resultTrailerPlayer.introVisible = false;
        resultTrailerPlayer.getPlayer().handleEvent(CSPlayerEvent.Play, PlayerEventSource.UI);
        resultTrailerPlayer.fixPlayerSize();
        resultTrailerPlayer.showControls();
    }
}
