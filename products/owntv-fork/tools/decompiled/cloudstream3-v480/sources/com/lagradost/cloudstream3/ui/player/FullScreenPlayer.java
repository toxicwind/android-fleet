package com.lagradost.cloudstream3.ui.player;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewPropertyAnimator;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.view.ViewGroupKt;
import androidx.fragment.app.FragmentActivity;
import androidx.media3.ui.SubtitleView;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.slider.Slider;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.LoadResponse;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.databinding.FragmentPlayerBinding;
import com.lagradost.cloudstream3.databinding.PlayerCustomLayoutBinding;
import com.lagradost.cloudstream3.databinding.SpeedDialogBinding;
import com.lagradost.cloudstream3.databinding.SubtitleOffsetBinding;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.ui.BaseAdapter;
import com.lagradost.cloudstream3.ui.BaseFragment;
import com.lagradost.cloudstream3.ui.player.source_priority.QualityDataHelper;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.ui.subtitles.SaveCaptionStyle;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import com.lagradost.cloudstream3.utils.BackPressedCallbackHelper;
import com.lagradost.cloudstream3.utils.DataStoreHelper;
import com.lagradost.cloudstream3.utils.TextUtilKt;
import com.lagradost.cloudstream3.utils.UIHelper;
import com.lagradost.cloudstream3.utils.UiText;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: FullScreenPlayer.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\t\n\u0002\b\u001a\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\b\u0017\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\r\u0010\u0005\u001a\u00020\u0006H\u0016¢\u0006\u0002\u0010\u0007J\u0010\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020BH\u0016J\u0006\u0010R\u001a\u00020\tJ\b\u0010S\u001a\u00020@H\u0002J\b\u0010T\u001a\u00020@H\u0016J\b\u0010U\u001a\u00020@H\u0016J\b\u0010V\u001a\u00020@H\u0016J(\u0010W\u001a\u00020@2\u0006\u0010X\u001a\u00020Y2\b\u0010Z\u001a\u0004\u0018\u00010[2\f\u0010\\\u001a\b\u0012\u0004\u0012\u00020@0]H\u0016J\b\u0010^\u001a\u00020@H\u0016J\b\u0010_\u001a\u00020\tH\u0016J\b\u0010`\u001a\u00020@H\u0016J\u000f\u0010a\u001a\u0004\u0018\u00010\tH\u0002¢\u0006\u0002\u0010bJ\u0014\u0010c\u001a\u00020@*\u00020B2\u0006\u0010\u001c\u001a\u00020dH\u0002J\u0014\u0010e\u001a\u00020@*\u00020B2\u0006\u0010\u001c\u001a\u00020dH\u0002J\b\u0010f\u001a\u00020@H\u0004J\b\u0010g\u001a\u00020@H\u0016J\u0010\u0010h\u001a\u00020@2\u0006\u0010i\u001a\u00020jH\u0002J\u0010\u0010k\u001a\u00020@2\u0006\u0010i\u001a\u00020jH\u0002J\u0010\u0010l\u001a\u00020@2\u0006\u0010i\u001a\u00020jH\u0002J\u0012\u0010m\u001a\u00020@2\b\b\u0002\u0010n\u001a\u00020\tH\u0002J\b\u0010o\u001a\u00020@H\u0002J\b\u0010p\u001a\u00020@H\u0016J\b\u0010q\u001a\u00020@H\u0016J\b\u0010r\u001a\u00020@H\u0016J\u0010\u0010s\u001a\u00020@2\u0006\u0010t\u001a\u00020dH\u0002J\b\u0010u\u001a\u00020@H\u0002J\b\u0010v\u001a\u00020@H\u0002J\u0010\u0010w\u001a\u00020@2\u0006\u0010x\u001a\u00020yH\u0007J\b\u0010z\u001a\u00020@H\u0002J\b\u0010{\u001a\u00020@H\u0002J\b\u0010|\u001a\u00020@H\u0002J\b\u0010}\u001a\u00020@H\u0002J\b\u0010~\u001a\u00020@H\u0002J\b\u0010\u007f\u001a\u00020@H\u0004J\t\u0010\u0080\u0001\u001a\u00020@H\u0016J\t\u0010\u0081\u0001\u001a\u00020@H\u0004J\t\u0010\u0082\u0001\u001a\u00020\tH\u0016J\t\u0010\u0083\u0001\u001a\u00020@H\u0016J\t\u0010\u0084\u0001\u001a\u00020@H\u0016J\u0015\u0010\u0085\u0001\u001a\u00020@2\n\u0010\u0086\u0001\u001a\u0005\u0018\u00010\u0087\u0001H\u0017J\t\u0010\u0088\u0001\u001a\u00020@H\u0016J\u001b\u0010\u0089\u0001\u001a\u00020@2\u0007\u0010\u008a\u0001\u001a\u00020\t2\u0007\u0010\u008b\u0001\u001a\u00020\tH\u0016J\t\u0010\u008c\u0001\u001a\u00020@H\u0016J\u0012\u0010\u008d\u0001\u001a\u00020@2\u0007\u0010\u008e\u0001\u001a\u00020\tH\u0016J\u0013\u0010\u008f\u0001\u001a\u00020@2\b\u0010\u0090\u0001\u001a\u00030\u0091\u0001H\u0016J\u001c\u0010\u0092\u0001\u001a\u00020@2\b\u0010\u0092\u0001\u001a\u00030\u0093\u00012\u0007\u0010\u0094\u0001\u001a\u00020\tH\u0016J\u001a\u0010\u0095\u0001\u001a\u0004\u0018\u00010\t2\u0007\u0010\u0096\u0001\u001a\u00020\u0006H\u0002¢\u0006\u0003\u0010\u0097\u0001J\u001c\u0010\u0098\u0001\u001a\u00020\t2\b\u0010\u0099\u0001\u001a\u00030\u009a\u00012\u0007\u0010\u009b\u0001\u001a\u00020\tH\u0002J\t\u0010\u009c\u0001\u001a\u00020@H\u0004J\u0013\u0010\u009d\u0001\u001a\u00020@2\b\u0010\u009e\u0001\u001a\u00030\u009f\u0001H\u0016J\u001d\u0010 \u0001\u001a\u00020@2\u0006\u0010x\u001a\u00020\u00022\n\u0010¡\u0001\u001a\u0005\u0018\u00010\u009f\u0001H\u0016J\t\u0010¢\u0001\u001a\u00020@H\u0002J\f\u00109\u001a\u00020@*\u00020\u000fH\u0002J\u001b\u0010£\u0001\u001a\u00020@2\u0007\u0010¤\u0001\u001a\u00020\u00062\u0007\u0010¥\u0001\u001a\u00020\u0006H\u0016J\u0012\u0010¦\u0001\u001a\u00020@2\u0007\u0010\u008e\u0001\u001a\u00020\tH\u0002J\u0012\u0010§\u0001\u001a\u00020@2\u0007\u0010\u008e\u0001\u001a\u00020\tH\u0002R\u001a\u0010\b\u001a\u00020\tX\u0094\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\tX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000b\"\u0004\b\u0015\u0010\rR\u001a\u0010\u0016\u001a\u00020\tX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000b\"\u0004\b\u0017\u0010\rR\u001a\u0010\u0018\u001a\u00020\tX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u000b\"\u0004\b\u001a\u0010\rR\u000e\u0010\u001b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u001d\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\t@BX\u0084\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u000bR\u001a\u0010\u001f\u001a\u00020\u0006X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010$\u001a\u00020%X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u001a\u0010*\u001a\u00020%X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010'\"\u0004\b,\u0010)R\u001a\u0010-\u001a\u00020\tX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u000b\"\u0004\b/\u0010\rR\u001a\u00100\u001a\u00020\tX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\u000b\"\u0004\b2\u0010\rR\u001a\u00103\u001a\u00020\tX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u000b\"\u0004\b5\u0010\rR\u001a\u00106\u001a\u00020\tX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\u000b\"\u0004\b8\u0010\rR\u000e\u00109\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010:\u001a\u00020%2\u0006\u0010\u001c\u001a\u00020%8D@DX\u0084\u000e¢\u0006\f\u001a\u0004\b;\u0010'\"\u0004\b<\u0010)R\u000e\u0010=\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R(\u0010D\u001a\u0004\u0018\u00010C2\b\u0010\u001c\u001a\u0004\u0018\u00010C@DX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR(\u0010I\u001a\u0004\u0018\u00010C2\b\u0010\u001c\u001a\u0004\u0018\u00010C@DX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010F\"\u0004\bK\u0010HR(\u0010L\u001a\u0004\u0018\u00010C2\b\u0010\u001c\u001a\u0004\u0018\u00010C@DX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010F\"\u0004\bN\u0010HR(\u0010O\u001a\u0004\u0018\u00010C2\b\u0010\u001c\u001a\u0004\u0018\u00010C@DX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u0010F\"\u0004\bQ\u0010H¨\u0006¨\u0001"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/FullScreenPlayer;", "Lcom/lagradost/cloudstream3/ui/player/AbstractPlayerFragment;", "Lcom/lagradost/cloudstream3/databinding/FragmentPlayerBinding;", "<init>", "()V", "pickLayout", "", "()Ljava/lang/Integer;", "lockRotation", "", "getLockRotation", "()Z", "setLockRotation", "(Z)V", "playerBinding", "Lcom/lagradost/cloudstream3/databinding/PlayerCustomLayoutBinding;", "getPlayerBinding", "()Lcom/lagradost/cloudstream3/databinding/PlayerCustomLayoutBinding;", "setPlayerBinding", "(Lcom/lagradost/cloudstream3/databinding/PlayerCustomLayoutBinding;)V", "isShowing", "setShowing", "isLocked", "setLocked", "timestampShowState", "getTimestampShowState", "setTimestampShowState", "metadataVisibilityToken", ES6Iterator.VALUE_PROPERTY, "hasEpisodes", "getHasEpisodes", "currentQualityProfile", "getCurrentQualityProfile", "()I", "setCurrentQualityProfile", "(I)V", "androidTVInterfaceOffSeekTime", "", "getAndroidTVInterfaceOffSeekTime", "()J", "setAndroidTVInterfaceOffSeekTime", "(J)V", "androidTVInterfaceOnSeekTime", "getAndroidTVInterfaceOnSeekTime", "setAndroidTVInterfaceOnSeekTime", "playBackSpeedEnabled", "getPlayBackSpeedEnabled", "setPlayBackSpeedEnabled", "playerResizeEnabled", "getPlayerResizeEnabled", "setPlayerResizeEnabled", "playerRotateEnabled", "getPlayerRotateEnabled", "setPlayerRotateEnabled", "rotatedManually", "getRotatedManually", "setRotatedManually", "hideControlsNames", "subtitleDelay", "getSubtitleDelay", "setSubtitleDelay", "isShowingEpisodeOverlay", "previousPlayStatus", "fixLayout", "", "view", "Landroid/view/View;", "Landroid/app/Dialog;", "selectSourceDialog", "getSelectSourceDialog", "()Landroid/app/Dialog;", "setSelectSourceDialog", "(Landroid/app/Dialog;)V", "selectTrackDialog", "getSelectTrackDialog", "setSelectTrackDialog", "selectSpeedDialog", "getSelectSpeedDialog", "setSelectSpeedDialog", "selectSubtitlesDialog", "getSelectSubtitlesDialog", "setSelectSubtitlesDialog", "isDialogOpen", "scheduleMetadataVisibility", "onDestroyView", "showMirrorsDialogue", "showTracksDialogue", "openOnlineSubPicker", "context", "Landroid/content/Context;", "loadResponse", "Lcom/lagradost/cloudstream3/LoadResponse;", "dismissCallback", "Lkotlin/Function0;", "showEpisodesOverlay", "isThereEpisodes", "exitedPipMode", "animateLayoutChangesForSubtitles", "()Ljava/lang/Boolean;", "animateY", "", "animateX", "animateLayoutChanges", "subtitlesChanged", "restoreOrientationWithSensor", "activity", "Landroid/app/Activity;", "toggleOrientationWithSensor", "lockOrientation", "updateOrientation", "ignoreDynamicOrientation", "setupKeyEventListener", "onResume", "onStop", "onDestroy", "setPlayBackSpeed", "speed", "skipOp", "showSubtitleOffsetDialog", "updateSpeedDialogBinding", "binding", "Lcom/lagradost/cloudstream3/databinding/SpeedDialogBinding;", "showSpeedDialog", "onClickChange", "toggleLock", "updateUIVisibility", "updateLockUI", "autoHide", "onAutoHideUI", "hidePlayerUI", "isUIShowing", "onSingleTap", "onTouchDown", "onSeekPreviewText", "text", "", "onHidePlayerUI", "onGestureEnd", "hadSwipe", "wasUiShowing", "playerStatusChanged", "onHoldSpeedUp", "show", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "resize", "Lcom/lagradost/cloudstream3/ui/player/PlayerResize;", "showToast", "handleKeyDownEvent", "keyCode", "(I)Ljava/lang/Boolean;", "handleKeyEvent", NotificationCompat.CATEGORY_EVENT, "Landroid/view/KeyEvent;", "hasNavigated", "uiReset", "onSaveInstanceState", "outState", "Landroid/os/Bundle;", "onBindingCreated", "savedInstanceState", "toggleRotate", "playerDimensionsLoaded", "width", "height", "toggleEpisodesOverlay", "animateEpisodesOverlay", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class FullScreenPlayer extends AbstractPlayerFragment<FragmentPlayerBinding> {
    private long androidTVInterfaceOffSeekTime;
    private long androidTVInterfaceOnSeekTime;
    private int currentQualityProfile;
    private boolean hasEpisodes;
    private boolean hideControlsNames;
    private boolean isLocked;
    private boolean isShowing;
    private boolean isShowingEpisodeOverlay;
    private boolean lockRotation;
    private int metadataVisibilityToken;
    private boolean playBackSpeedEnabled;
    private PlayerCustomLayoutBinding playerBinding;
    private boolean playerResizeEnabled;
    private boolean playerRotateEnabled;
    private boolean previousPlayStatus;
    private boolean rotatedManually;
    private Dialog selectSourceDialog;
    private Dialog selectSpeedDialog;
    private Dialog selectSubtitlesDialog;
    private Dialog selectTrackDialog;
    private boolean timestampShowState;

    @Override // com.lagradost.cloudstream3.ui.player.AbstractPlayerFragment, com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void fixLayout(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
    }

    public boolean isThereEpisodes() {
        return false;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.player.FullScreenPlayer$1, reason: invalid class name */
    /* JADX INFO: compiled from: FullScreenPlayer.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<View, FragmentPlayerBinding> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(1, FragmentPlayerBinding.class, "bind", "bind(Landroid/view/View;)Lcom/lagradost/cloudstream3/databinding/FragmentPlayerBinding;", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public final FragmentPlayerBinding invoke(View p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return FragmentPlayerBinding.bind(p0);
        }
    }

    public FullScreenPlayer() {
        super(new BaseFragment.BindingCreator.Bind(AnonymousClass1.INSTANCE));
        this.lockRotation = true;
        this.currentQualityProfile = 1;
        this.androidTVInterfaceOffSeekTime = 10000L;
        this.androidTVInterfaceOnSeekTime = 30000L;
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragment, com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public Integer pickLayout() {
        return Integer.valueOf(R.layout.fragment_player);
    }

    protected boolean getLockRotation() {
        return this.lockRotation;
    }

    protected void setLockRotation(boolean z) {
        this.lockRotation = z;
    }

    protected final PlayerCustomLayoutBinding getPlayerBinding() {
        return this.playerBinding;
    }

    protected final void setPlayerBinding(PlayerCustomLayoutBinding playerCustomLayoutBinding) {
        this.playerBinding = playerCustomLayoutBinding;
    }

    /* JADX INFO: renamed from: isShowing, reason: from getter */
    protected final boolean getIsShowing() {
        return this.isShowing;
    }

    protected final void setShowing(boolean z) {
        this.isShowing = z;
    }

    /* JADX INFO: renamed from: isLocked, reason: from getter */
    protected final boolean getIsLocked() {
        return this.isLocked;
    }

    protected final void setLocked(boolean z) {
        this.isLocked = z;
    }

    protected final boolean getTimestampShowState() {
        return this.timestampShowState;
    }

    protected final void setTimestampShowState(boolean z) {
        this.timestampShowState = z;
    }

    protected final boolean getHasEpisodes() {
        return this.hasEpisodes;
    }

    protected final int getCurrentQualityProfile() {
        return this.currentQualityProfile;
    }

    protected final void setCurrentQualityProfile(int i) {
        this.currentQualityProfile = i;
    }

    protected final long getAndroidTVInterfaceOffSeekTime() {
        return this.androidTVInterfaceOffSeekTime;
    }

    protected final void setAndroidTVInterfaceOffSeekTime(long j) {
        this.androidTVInterfaceOffSeekTime = j;
    }

    protected final long getAndroidTVInterfaceOnSeekTime() {
        return this.androidTVInterfaceOnSeekTime;
    }

    protected final void setAndroidTVInterfaceOnSeekTime(long j) {
        this.androidTVInterfaceOnSeekTime = j;
    }

    protected final boolean getPlayBackSpeedEnabled() {
        return this.playBackSpeedEnabled;
    }

    protected final void setPlayBackSpeedEnabled(boolean z) {
        this.playBackSpeedEnabled = z;
    }

    protected final boolean getPlayerResizeEnabled() {
        return this.playerResizeEnabled;
    }

    protected final void setPlayerResizeEnabled(boolean z) {
        this.playerResizeEnabled = z;
    }

    protected final boolean getPlayerRotateEnabled() {
        return this.playerRotateEnabled;
    }

    protected final void setPlayerRotateEnabled(boolean z) {
        this.playerRotateEnabled = z;
    }

    protected final boolean getRotatedManually() {
        return this.rotatedManually;
    }

    protected final void setRotatedManually(boolean z) {
        this.rotatedManually = z;
    }

    protected final void setSubtitleDelay(long j) {
        try {
            getPlayer().setSubtitleOffset(-j);
        } catch (Exception e) {
            ArchComponentExtKt.logError(e);
        }
    }

    protected final long getSubtitleDelay() {
        try {
            return -getPlayer().getCurrentSubtitleOffset();
        } catch (Exception e) {
            ArchComponentExtKt.logError(e);
            return 0L;
        }
    }

    protected final Dialog getSelectSourceDialog() {
        return this.selectSourceDialog;
    }

    protected final void setSelectSourceDialog(Dialog dialog) {
        Dialog dialog2 = this.selectSourceDialog;
        this.selectSourceDialog = dialog;
        if (dialog != null || dialog2 == null) {
            return;
        }
        autoHide();
    }

    protected final Dialog getSelectTrackDialog() {
        return this.selectTrackDialog;
    }

    protected final void setSelectTrackDialog(Dialog dialog) {
        Dialog dialog2 = this.selectTrackDialog;
        this.selectTrackDialog = dialog;
        if (dialog != null || dialog2 == null) {
            return;
        }
        autoHide();
    }

    protected final Dialog getSelectSpeedDialog() {
        return this.selectSpeedDialog;
    }

    protected final void setSelectSpeedDialog(Dialog dialog) {
        Dialog dialog2 = this.selectSpeedDialog;
        this.selectSpeedDialog = dialog;
        if (dialog != null || dialog2 == null) {
            return;
        }
        autoHide();
    }

    protected final Dialog getSelectSubtitlesDialog() {
        return this.selectSubtitlesDialog;
    }

    protected final void setSelectSubtitlesDialog(Dialog dialog) {
        Dialog dialog2 = this.selectSubtitlesDialog;
        this.selectSubtitlesDialog = dialog;
        if (dialog != null || dialog2 == null) {
            return;
        }
        autoHide();
    }

    public final boolean isDialogOpen() {
        Dialog dialog;
        Dialog dialog2;
        Dialog dialog3;
        Dialog dialog4 = this.selectSourceDialog;
        return (dialog4 != null && dialog4.isShowing()) || ((dialog = this.selectTrackDialog) != null && dialog.isShowing()) || (((dialog2 = this.selectSpeedDialog) != null && dialog2.isShowing()) || (((dialog3 = this.selectSubtitlesDialog) != null && dialog3.isShowing()) || this.isShowingEpisodeOverlay));
    }

    private final void scheduleMetadataVisibility() {
        final FrameLayout frameLayout;
        Context context;
        PlayerCustomLayoutBinding playerCustomLayoutBinding = this.playerBinding;
        if (playerCustomLayoutBinding == null || (frameLayout = playerCustomLayoutBinding.playerMetadataScrim) == null || (context = frameLayout.getContext()) == null) {
            return;
        }
        if (AppContextUtils.INSTANCE.shouldShowPlayerMetadata(context)) {
            if (!Globals.INSTANCE.isLayout(1)) {
                boolean z = getCurrentPlayerStatus() == CSPlayerLoading.IsPaused;
                final int i = this.metadataVisibilityToken + 1;
                this.metadataVisibilityToken = i;
                if (z) {
                    frameLayout.postDelayed(new Runnable() { // from class: com.lagradost.cloudstream3.ui.player.FullScreenPlayer$$ExternalSyntheticLambda4
                        @Override // java.lang.Runnable
                        public final void run() {
                            FullScreenPlayer.scheduleMetadataVisibility$lambda$0(i, this, frameLayout);
                        }
                    }, 8000L);
                    return;
                } else {
                    if (frameLayout.getVisibility() == 0) {
                        frameLayout.animate().alpha(0.0f).setDuration(300L).setInterpolator(new AccelerateDecelerateInterpolator()).withEndAction(new Runnable() { // from class: com.lagradost.cloudstream3.ui.player.FullScreenPlayer$$ExternalSyntheticLambda5
                            @Override // java.lang.Runnable
                            public final void run() {
                                FullScreenPlayer.scheduleMetadataVisibility$lambda$1(frameLayout);
                            }
                        }).start();
                        return;
                    }
                    return;
                }
            }
            frameLayout.setVisibility(8);
            this.metadataVisibilityToken++;
            return;
        }
        frameLayout.setVisibility(8);
        this.metadataVisibilityToken++;
    }

    static final void scheduleMetadataVisibility$lambda$0(int i, FullScreenPlayer fullScreenPlayer, FrameLayout frameLayout) {
        if (i != fullScreenPlayer.metadataVisibilityToken) {
            return;
        }
        FrameLayout frameLayout2 = frameLayout;
        if (frameLayout2.getVisibility() == 0 || fullScreenPlayer.getCurrentPlayerStatus() != CSPlayerLoading.IsPaused || fullScreenPlayer.isDialogOpen()) {
            return;
        }
        frameLayout.setAlpha(0.0f);
        frameLayout2.setVisibility(0);
        frameLayout.animate().alpha(1.0f).setDuration(500L).setInterpolator(new DecelerateInterpolator()).start();
        fullScreenPlayer.hidePlayerUI();
    }

    static final void scheduleMetadataVisibility$lambda$1(FrameLayout frameLayout) {
        frameLayout.setAlpha(0.0f);
        frameLayout.setVisibility(8);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        PlayerView playerHostView = getPlayerHostView();
        if (playerHostView != null) {
            playerHostView.releaseOverlayLayoutListener();
        }
        this.playerBinding = null;
        super.onDestroyView();
    }

    public void showMirrorsDialogue() {
        throw new NotImplementedError(null, 1, null);
    }

    public void showTracksDialogue() {
        throw new NotImplementedError(null, 1, null);
    }

    public void openOnlineSubPicker(Context context, LoadResponse loadResponse, Function0<Unit> dismissCallback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(dismissCallback, "dismissCallback");
        throw new NotImplementedError(null, 1, null);
    }

    public void showEpisodesOverlay() {
        throw new NotImplementedError(null, 1, null);
    }

    @Override // com.lagradost.cloudstream3.ui.player.AbstractPlayerFragment, com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void exitedPipMode() {
        animateLayoutChanges();
    }

    private final Boolean animateLayoutChangesForSubtitles() {
        LinearLayout linearLayout;
        PlayerCustomLayoutBinding playerCustomLayoutBinding = this.playerBinding;
        if (playerCustomLayoutBinding == null || (linearLayout = playerCustomLayoutBinding.bottomPlayerBar) == null) {
            return null;
        }
        return Boolean.valueOf(linearLayout.post(new Runnable() { // from class: com.lagradost.cloudstream3.ui.player.FullScreenPlayer$$ExternalSyntheticLambda32
            @Override // java.lang.Runnable
            public final void run() {
                FullScreenPlayer.animateLayoutChangesForSubtitles$lambda$0(this.f$0);
            }
        }));
    }

    static final void animateLayoutChangesForSubtitles$lambda$0(FullScreenPlayer fullScreenPlayer) {
        int iMin;
        SubtitleView subView = fullScreenPlayer.getSubView();
        if (subView == null) {
            return;
        }
        SaveCaptionStyle style = CustomDecoder.INSTANCE.getStyle();
        PlayerCustomLayoutBinding playerCustomLayoutBinding = fullScreenPlayer.playerBinding;
        if (playerCustomLayoutBinding == null) {
            return;
        }
        if (fullScreenPlayer.isShowing) {
            iMin = Math.min(-UIHelper.INSTANCE.getToPx(style.getElevation()), playerCustomLayoutBinding.previewFrameLayout.getHeight() - playerCustomLayoutBinding.bottomPlayerBar.getHeight());
        } else {
            iMin = -UIHelper.INSTANCE.getToPx(style.getElevation());
        }
        fullScreenPlayer.animateY(subView, iMin);
    }

    private final void animateY(View view, float f) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, "translationY", f);
        objectAnimatorOfFloat.setDuration(200L);
        objectAnimatorOfFloat.start();
    }

    private final void animateX(View view, float f) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, "translationX", f);
        objectAnimatorOfFloat.setDuration(200L);
        objectAnimatorOfFloat.start();
    }

    protected final void animateLayoutChanges() {
        PlayerGestureHelper gestureHelper;
        PlayerCustomLayoutBinding playerCustomLayoutBinding = this.playerBinding;
        if (playerCustomLayoutBinding != null) {
            if (Globals.INSTANCE.isLayout(1)) {
                playerCustomLayoutBinding.exoProgress.setEnabled(this.isShowing);
            }
            if (this.isShowing) {
                updateUIVisibility();
            } else {
                toggleEpisodesOverlay(false);
                playerCustomLayoutBinding.playerHolder.postDelayed(new Runnable() { // from class: com.lagradost.cloudstream3.ui.player.FullScreenPlayer$$ExternalSyntheticLambda34
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.updateUIVisibility();
                    }
                }, 200L);
            }
            float f = this.isShowing ? 0.0f : -UIHelper.INSTANCE.getToPx(50);
            Iterator it = CollectionsKt.listOfNotNull((Object[]) new View[]{playerCustomLayoutBinding.playerVideoTitleHolder, playerCustomLayoutBinding.playerVideoTitleRez, playerCustomLayoutBinding.playerVideoInfo, playerCustomLayoutBinding.playerGoBackHolder}).iterator();
            while (it.hasNext()) {
                animateY((View) it.next(), f);
            }
            FrameLayout playerMetadataScrim = playerCustomLayoutBinding.playerMetadataScrim;
            Intrinsics.checkNotNullExpressionValue(playerMetadataScrim, "playerMetadataScrim");
            animateY(playerMetadataScrim, 1.0f);
            float toPx = this.isShowing ? 0.0f : UIHelper.INSTANCE.getToPx(50);
            LinearLayout bottomPlayerBar = playerCustomLayoutBinding.bottomPlayerBar;
            Intrinsics.checkNotNullExpressionValue(bottomPlayerBar, "bottomPlayerBar");
            animateY(bottomPlayerBar, toPx);
            if (Globals.INSTANCE.isLayout(1)) {
                ImageView playerEpisodesButton = playerCustomLayoutBinding.playerEpisodesButton;
                Intrinsics.checkNotNullExpressionValue(playerEpisodesButton, "playerEpisodesButton");
                animateX(playerEpisodesButton, this.isShowing ? 0.0f : UIHelper.INSTANCE.getToPx(50));
            }
            float f2 = this.isShowing ? 1.0f : 0.0f;
            AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f - f2, f2);
            alphaAnimation.setDuration(100L);
            alphaAnimation.setFillAfter(true);
            animateLayoutChangesForSubtitles();
            float f3 = this.isShowing ? 0.0f : -UIHelper.INSTANCE.getToPx(50);
            ImageView playerOpenSource = playerCustomLayoutBinding.playerOpenSource;
            Intrinsics.checkNotNullExpressionValue(playerOpenSource, "playerOpenSource");
            animateY(playerOpenSource, f3);
            if (!this.isLocked) {
                PlayerView playerHostView = getPlayerHostView();
                if (playerHostView != null && (gestureHelper = playerHostView.getGestureHelper()) != null) {
                    gestureHelper.animateCenterControls(f2);
                }
                View shadowOverlay = playerCustomLayoutBinding.shadowOverlay;
                Intrinsics.checkNotNullExpressionValue(shadowOverlay, "shadowOverlay");
                shadowOverlay.setVisibility(0);
                AlphaAnimation alphaAnimation2 = alphaAnimation;
                playerCustomLayoutBinding.shadowOverlay.startAnimation(alphaAnimation2);
                playerCustomLayoutBinding.downloadBothHeader.startAnimation(alphaAnimation2);
            }
            AlphaAnimation alphaAnimation3 = alphaAnimation;
            playerCustomLayoutBinding.bottomPlayerBar.startAnimation(alphaAnimation3);
            playerCustomLayoutBinding.playerOpenSource.startAnimation(alphaAnimation3);
            playerCustomLayoutBinding.playerTopHolder.startAnimation(alphaAnimation3);
        }
    }

    @Override // com.lagradost.cloudstream3.ui.player.AbstractPlayerFragment, com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void subtitlesChanged() {
        boolean z;
        MaterialButton materialButton;
        CurrentTracks videoTracks = getPlayer().getVideoTracks();
        List<TextTrack> currentTextTracks = videoTracks.getCurrentTextTracks();
        boolean z2 = true;
        if (!(currentTextTracks instanceof Collection) || !currentTextTracks.isEmpty()) {
            Iterator<T> it = currentTextTracks.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = true;
                    break;
                } else if (!Intrinsics.areEqual(((TextTrack) it.next()).getSampleMimeType(), "application/x-media3-cues")) {
                    z = false;
                    break;
                }
            }
        } else {
            z = true;
            break;
        }
        PlayerCustomLayoutBinding playerCustomLayoutBinding = this.playerBinding;
        if (playerCustomLayoutBinding == null || (materialButton = playerCustomLayoutBinding.playerSubtitleOffsetBtt) == null) {
            return;
        }
        MaterialButton materialButton2 = materialButton;
        if (!z && !videoTracks.getCurrentTextTracks().isEmpty()) {
            z2 = false;
        }
        materialButton2.setVisibility(z2 ? 8 : 0);
    }

    private final void restoreOrientationWithSensor(Activity activity) {
        int iDynamicOrientation;
        int i = activity.getResources().getConfiguration().orientation;
        if (i == 1) {
            iDynamicOrientation = 7;
        } else if (i != 2) {
            PlayerView playerHostView = getPlayerHostView();
            if (playerHostView == null) {
                return;
            } else {
                iDynamicOrientation = playerHostView.dynamicOrientation();
            }
        } else {
            iDynamicOrientation = 6;
        }
        activity.setRequestedOrientation(iDynamicOrientation);
    }

    private final void toggleOrientationWithSensor(Activity activity) {
        int iDynamicOrientation;
        int i = activity.getResources().getConfiguration().orientation;
        if (i == 1) {
            iDynamicOrientation = 6;
        } else if (i != 2) {
            PlayerView playerHostView = getPlayerHostView();
            if (playerHostView == null) {
                return;
            } else {
                iDynamicOrientation = playerHostView.dynamicOrientation();
            }
        } else {
            iDynamicOrientation = 7;
        }
        activity.setRequestedOrientation(iDynamicOrientation);
    }

    private final void lockOrientation(Activity activity) {
        Display display;
        int iDynamicOrientation;
        if (Build.VERSION.SDK_INT < 30) {
            Object systemService = activity.getSystemService("window");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.WindowManager");
            display = ((WindowManager) systemService).getDefaultDisplay();
        } else {
            display = activity.getDisplay();
            Intrinsics.checkNotNull(display);
        }
        int rotation = display.getRotation();
        int i = activity.getResources().getConfiguration().orientation;
        int i2 = 1;
        if (i == 1) {
            if (rotation != 0 && rotation != 3) {
                i2 = 9;
            }
            iDynamicOrientation = i2;
        } else if (i != 2) {
            PlayerView playerHostView = getPlayerHostView();
            if (playerHostView == null) {
                return;
            } else {
                iDynamicOrientation = playerHostView.dynamicOrientation();
            }
        } else {
            iDynamicOrientation = (rotation == 0 || rotation == 1) ? 0 : 8;
        }
        activity.setRequestedOrientation(iDynamicOrientation);
    }

    static /* synthetic */ void updateOrientation$default(FullScreenPlayer fullScreenPlayer, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: updateOrientation");
        }
        if ((i & 1) != 0) {
            z = false;
        }
        fullScreenPlayer.updateOrientation(z);
    }

    private final void updateOrientation(boolean ignoreDynamicOrientation) {
        FragmentActivity activity = getActivity();
        if (activity == null || !getLockRotation()) {
            return;
        }
        if (this.isLocked) {
            lockOrientation(activity);
            return;
        }
        if (ignoreDynamicOrientation || this.rotatedManually) {
            restoreOrientationWithSensor(activity);
            return;
        }
        PlayerView playerHostView = getPlayerHostView();
        if (playerHostView != null) {
            activity.setRequestedOrientation(playerHostView.dynamicOrientation());
        }
    }

    private final void setupKeyEventListener() {
        CommonActivity.INSTANCE.setKeyEventListener(new Function1() { // from class: com.lagradost.cloudstream3.ui.player.FullScreenPlayer$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(FullScreenPlayer.setupKeyEventListener$lambda$0(this.f$0, (Pair) obj));
            }
        });
    }

    static final boolean setupKeyEventListener$lambda$0(FullScreenPlayer fullScreenPlayer, Pair pair) {
        Intrinsics.checkNotNullParameter(pair, "<destruct>");
        KeyEvent keyEvent = (KeyEvent) pair.component1();
        boolean zBooleanValue = ((Boolean) pair.component2()).booleanValue();
        if (keyEvent == null) {
            return false;
        }
        if (keyEvent.getAction() == 0 && (keyEvent.getKeyCode() == 24 || keyEvent.getKeyCode() == 25)) {
            PlayerView playerHostView = fullScreenPlayer.getPlayerHostView();
            if (playerHostView != null) {
                return playerHostView.handleVolumeKey(keyEvent.getKeyCode());
            }
            return false;
        }
        if (fullScreenPlayer.getPlayer().isActive()) {
            return fullScreenPlayer.handleKeyEvent(keyEvent, zBooleanValue);
        }
        return false;
    }

    static final Unit onResume$lambda$0(FullScreenPlayer fullScreenPlayer) {
        updateOrientation$default(fullScreenPlayer, false, 1, null);
        return Unit.INSTANCE;
    }

    @Override // com.lagradost.cloudstream3.ui.player.AbstractPlayerFragment, androidx.fragment.app.Fragment
    public void onResume() {
        PlayerView playerHostView = getPlayerHostView();
        if (playerHostView != null) {
            playerHostView.enterFullscreen(new Function0() { // from class: com.lagradost.cloudstream3.ui.player.FullScreenPlayer$$ExternalSyntheticLambda35
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return FullScreenPlayer.onResume$lambda$0(this.f$0);
                }
            });
        }
        setupKeyEventListener();
        PlayerView playerHostView2 = getPlayerHostView();
        if (playerHostView2 != null) {
            playerHostView2.verifyVolume();
        }
        FragmentActivity activity = getActivity();
        if (activity != null) {
            BackPressedCallbackHelper.INSTANCE.attachBackPressedCallback(activity, "FullScreenPlayer", new Function1() { // from class: com.lagradost.cloudstream3.ui.player.FullScreenPlayer$$ExternalSyntheticLambda36
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return FullScreenPlayer.onResume$lambda$1(this.f$0, (BackPressedCallbackHelper.CallbackHelper) obj);
                }
            });
        }
        PlayerView playerHostView3 = getPlayerHostView();
        if (playerHostView3 != null) {
            playerHostView3.requestUpdateBrightnessOverlayOnNextLayout();
        }
        super.onResume();
    }

    static final Unit onResume$lambda$1(FullScreenPlayer fullScreenPlayer, BackPressedCallbackHelper.CallbackHelper attachBackPressedCallback) {
        ImageView playerPausePlay;
        Intrinsics.checkNotNullParameter(attachBackPressedCallback, "$this$attachBackPressedCallback");
        if (fullScreenPlayer.isShowingEpisodeOverlay) {
            if (Globals.INSTANCE.isLayout(6) && (playerPausePlay = fullScreenPlayer.getPlayerPausePlay()) != null) {
                playerPausePlay.requestFocus();
            }
            fullScreenPlayer.toggleEpisodesOverlay(false);
            return Unit.INSTANCE;
        }
        if (fullScreenPlayer.isShowing && Globals.INSTANCE.isLayout(6)) {
            fullScreenPlayer.onClickChange();
        } else {
            FragmentActivity activity = fullScreenPlayer.getActivity();
            if (activity != null) {
                UIHelper.INSTANCE.popCurrentPage(activity, "FullScreenPlayer");
            }
        }
        return Unit.INSTANCE;
    }

    @Override // com.lagradost.cloudstream3.ui.player.AbstractPlayerFragment, androidx.fragment.app.Fragment
    public void onStop() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            BackPressedCallbackHelper.INSTANCE.detachBackPressedCallback(activity, "FullScreenPlayer");
        }
        super.onStop();
    }

    @Override // com.lagradost.cloudstream3.ui.player.AbstractPlayerFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        PlayerView playerHostView = getPlayerHostView();
        if (playerHostView != null) {
            playerHostView.exitFullscreen();
        }
        super.onDestroy();
    }

    private final void setPlayBackSpeed(float speed) {
        MaterialButton materialButton;
        try {
            DataStoreHelper.INSTANCE.setPlayBackSpeed(speed);
            PlayerCustomLayoutBinding playerCustomLayoutBinding = this.playerBinding;
            if (playerCustomLayoutBinding != null && (materialButton = playerCustomLayoutBinding.playerSpeedBtt) != null) {
                String string = getString(R.string.player_speed_text_format);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                String str = String.format(string, Arrays.copyOf(new Object[]{Float.valueOf(speed)}, 1));
                Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                materialButton.setText(StringsKt.replace$default(str, ".0x", "x", false, 4, (Object) null));
            }
        } catch (Exception e) {
            ArchComponentExtKt.logError(e);
        }
        getPlayer().setPlaybackSpeed(speed);
    }

    private final void skipOp() {
        IPlayer.CC.seekTime$default(getPlayer(), 85000L, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v6, types: [T, com.lagradost.cloudstream3.ui.player.SubtitleOffsetItemAdapter] */
    public final void showSubtitleOffsetDialog() {
        Context context = getContext();
        if (context == null) {
            return;
        }
        getPlayer().handleEvent(CSPlayerEvent.Pause, PlayerEventSource.UI);
        final SubtitleOffsetBinding subtitleOffsetBindingInflate = SubtitleOffsetBinding.inflate(LayoutInflater.from(context), null, false);
        Intrinsics.checkNotNullExpressionValue(subtitleOffsetBindingInflate, "inflate(...)");
        final Dialog dialog = new Dialog(context, R.style.DialogFullscreenPlayer);
        dialog.setContentView(subtitleOffsetBindingInflate.getRoot());
        setSelectSubtitlesDialog(dialog);
        dialog.show();
        boolean z = context.getResources().getConfiguration().orientation == 1;
        UIHelper uIHelper = UIHelper.INSTANCE;
        LinearLayout root = subtitleOffsetBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        UIHelper.fixSystemBarsPadding$default(uIHelper, root, null, null, false, false, false, false, false, z, 254, null);
        final Ref.LongRef longRef = new Ref.LongRef();
        longRef.element = getSubtitleDelay();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        EditText subtitleOffsetInput = subtitleOffsetBindingInflate.subtitleOffsetInput;
        Intrinsics.checkNotNullExpressionValue(subtitleOffsetInput, "subtitleOffsetInput");
        subtitleOffsetInput.addTextChangedListener(new TextWatcher() { // from class: com.lagradost.cloudstream3.ui.player.FullScreenPlayer$showSubtitleOffsetDialog$lambda$1$$inlined$doOnTextChanged$1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence text, int start, int before, int count) {
                String string;
                Long longOrNull;
                UiText uiTextTxt;
                if (text == null || (string = text.toString()) == null || (longOrNull = StringsKt.toLongOrNull(string)) == null) {
                    return;
                }
                long jLongValue = longOrNull.longValue();
                longRef.element = jLongValue;
                Long position = this.getPlayer().getPosition();
                long jLongValue2 = (position != null ? position.longValue() : 0L) - longRef.element;
                SubtitleOffsetItemAdapter subtitleOffsetItemAdapter = (SubtitleOffsetItemAdapter) objectRef.element;
                if (subtitleOffsetItemAdapter != null) {
                    subtitleOffsetItemAdapter.updateTime(jLongValue2);
                }
                SubtitleOffsetItemAdapter subtitleOffsetItemAdapter2 = (SubtitleOffsetItemAdapter) objectRef.element;
                if (subtitleOffsetItemAdapter2 != null) {
                    subtitleOffsetBindingInflate.subtitleOffsetRecyclerview.scrollToPosition(subtitleOffsetItemAdapter2.getLatestActiveItem(jLongValue2));
                }
                if (jLongValue > 0) {
                    uiTextTxt = TextUtilKt.txt(R.string.subtitle_offset_extra_hint_later_format, Long.valueOf(jLongValue));
                } else if (jLongValue < 0) {
                    uiTextTxt = TextUtilKt.txt(R.string.subtitle_offset_extra_hint_before_format, Long.valueOf(-jLongValue));
                } else {
                    uiTextTxt = TextUtilKt.txt(R.string.subtitle_offset_extra_hint_none_format, new Object[0]);
                }
                TextUtilKt.setText(subtitleOffsetBindingInflate.subtitleOffsetSubTitle, uiTextTxt);
            }
        });
        EditText editText = subtitleOffsetBindingInflate.subtitleOffsetInput;
        Editable.Factory factory = Editable.Factory.getInstance();
        editText.setText(factory != null ? factory.newEditable(String.valueOf(longRef.element)) : null);
        List mutableList = CollectionsKt.toMutableList((Collection) getPlayer().getSubtitleCues());
        RecyclerView subtitleOffsetRecyclerview = subtitleOffsetBindingInflate.subtitleOffsetRecyclerview;
        Intrinsics.checkNotNullExpressionValue(subtitleOffsetRecyclerview, "subtitleOffsetRecyclerview");
        List list = mutableList;
        subtitleOffsetRecyclerview.setVisibility(!list.isEmpty() ? 0 : 8);
        TextView noSubtitlesLoadedNotice = subtitleOffsetBindingInflate.noSubtitlesLoadedNotice;
        Intrinsics.checkNotNullExpressionValue(noSubtitlesLoadedNotice, "noSubtitlesLoadedNotice");
        noSubtitlesLoadedNotice.setVisibility(mutableList.isEmpty() ? 0 : 8);
        Long position = getPlayer().getPosition();
        long jLongValue = (position != null ? position.longValue() : 0L) - longRef.element;
        ?? subtitleOffsetItemAdapter = new SubtitleOffsetItemAdapter(jLongValue, new Function1() { // from class: com.lagradost.cloudstream3.ui.player.FullScreenPlayer$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return FullScreenPlayer.showSubtitleOffsetDialog$lambda$1$1(this.f$0, subtitleOffsetBindingInflate, (SubtitleCue) obj);
            }
        });
        BaseAdapter.submitList$default((BaseAdapter) subtitleOffsetItemAdapter, list, null, 2, null);
        objectRef.element = subtitleOffsetItemAdapter;
        subtitleOffsetBindingInflate.subtitleOffsetRecyclerview.setAdapter((RecyclerView.Adapter) objectRef.element);
        RecyclerView.ItemAnimator itemAnimator = subtitleOffsetBindingInflate.subtitleOffsetRecyclerview.getItemAnimator();
        SimpleItemAnimator simpleItemAnimator = itemAnimator instanceof SimpleItemAnimator ? (SimpleItemAnimator) itemAnimator : null;
        if (simpleItemAnimator != null) {
            simpleItemAnimator.setSupportsChangeAnimations(false);
        }
        subtitleOffsetBindingInflate.subtitleOffsetRecyclerview.scrollToPosition(((SubtitleOffsetItemAdapter) objectRef.element).getLatestActiveItem(jLongValue));
        final long j = 100;
        subtitleOffsetBindingInflate.subtitleOffsetAdd.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.FullScreenPlayer$$ExternalSyntheticLambda22
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FullScreenPlayer.showSubtitleOffsetDialog$lambda$1$changeBy(subtitleOffsetBindingInflate, j);
            }
        });
        final long j2 = 1000;
        subtitleOffsetBindingInflate.subtitleOffsetAddMore.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.FullScreenPlayer$$ExternalSyntheticLambda33
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FullScreenPlayer.showSubtitleOffsetDialog$lambda$1$changeBy(subtitleOffsetBindingInflate, j2);
            }
        });
        subtitleOffsetBindingInflate.subtitleOffsetSubtract.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.FullScreenPlayer$$ExternalSyntheticLambda37
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FullScreenPlayer.showSubtitleOffsetDialog$lambda$1$5(j, subtitleOffsetBindingInflate, view);
            }
        });
        subtitleOffsetBindingInflate.subtitleOffsetSubtractMore.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.FullScreenPlayer$$ExternalSyntheticLambda38
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FullScreenPlayer.showSubtitleOffsetDialog$lambda$1$6(j2, subtitleOffsetBindingInflate, view);
            }
        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.lagradost.cloudstream3.ui.player.FullScreenPlayer$$ExternalSyntheticLambda39
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                FullScreenPlayer.showSubtitleOffsetDialog$lambda$1$7(this.f$0, dialogInterface);
            }
        });
        subtitleOffsetBindingInflate.applyBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.FullScreenPlayer$$ExternalSyntheticLambda40
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FullScreenPlayer.showSubtitleOffsetDialog$lambda$1$8(this.f$0, longRef, dialog, view);
            }
        });
        subtitleOffsetBindingInflate.resetBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.FullScreenPlayer$$ExternalSyntheticLambda41
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FullScreenPlayer.showSubtitleOffsetDialog$lambda$1$9(this.f$0, dialog, view);
            }
        });
        subtitleOffsetBindingInflate.cancelBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.FullScreenPlayer$$ExternalSyntheticLambda42
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FullScreenPlayer.showSubtitleOffsetDialog$lambda$1$10(this.f$0, dialog, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showSubtitleOffsetDialog$lambda$1$1(FullScreenPlayer fullScreenPlayer, SubtitleOffsetBinding subtitleOffsetBinding, SubtitleCue subtitleCue) {
        Intrinsics.checkNotNullParameter(subtitleCue, "subtitleCue");
        Long position = fullScreenPlayer.getPlayer().getPosition();
        long jLongValue = position != null ? position.longValue() : 0L;
        EditText editText = subtitleOffsetBinding.subtitleOffsetInput;
        Editable.Factory factory = Editable.Factory.getInstance();
        editText.setText(factory != null ? factory.newEditable(String.valueOf(jLongValue - subtitleCue.getStartTimeMs())) : null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showSubtitleOffsetDialog$lambda$1$changeBy(SubtitleOffsetBinding subtitleOffsetBinding, long j) {
        String string;
        Long longOrNull;
        Editable text = subtitleOffsetBinding.subtitleOffsetInput.getText();
        long jLongValue = ((text == null || (string = text.toString()) == null || (longOrNull = StringsKt.toLongOrNull(string)) == null) ? 0L : longOrNull.longValue()) + j;
        EditText editText = subtitleOffsetBinding.subtitleOffsetInput;
        Editable.Factory factory = Editable.Factory.getInstance();
        editText.setText(factory != null ? factory.newEditable(String.valueOf(jLongValue)) : null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showSubtitleOffsetDialog$lambda$1$5(long j, SubtitleOffsetBinding subtitleOffsetBinding, View view) {
        showSubtitleOffsetDialog$lambda$1$changeBy(subtitleOffsetBinding, -j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showSubtitleOffsetDialog$lambda$1$6(long j, SubtitleOffsetBinding subtitleOffsetBinding, View view) {
        showSubtitleOffsetDialog$lambda$1$changeBy(subtitleOffsetBinding, -j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showSubtitleOffsetDialog$lambda$1$7(FullScreenPlayer fullScreenPlayer, DialogInterface dialogInterface) {
        fullScreenPlayer.setSelectSubtitlesDialog(null);
        FragmentActivity activity = fullScreenPlayer.getActivity();
        if (activity != null) {
            UIHelper.INSTANCE.hideSystemUI(activity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showSubtitleOffsetDialog$lambda$1$8(FullScreenPlayer fullScreenPlayer, Ref.LongRef longRef, Dialog dialog, View view) {
        fullScreenPlayer.setSelectSubtitlesDialog(null);
        fullScreenPlayer.setSubtitleDelay(longRef.element);
        UIHelper.INSTANCE.dismissSafe(dialog, fullScreenPlayer.getActivity());
        IPlayer.CC.seekTime$default(fullScreenPlayer.getPlayer(), 1L, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showSubtitleOffsetDialog$lambda$1$9(FullScreenPlayer fullScreenPlayer, Dialog dialog, View view) {
        fullScreenPlayer.setSelectSubtitlesDialog(null);
        fullScreenPlayer.setSubtitleDelay(0L);
        UIHelper.INSTANCE.dismissSafe(dialog, fullScreenPlayer.getActivity());
        IPlayer.CC.seekTime$default(fullScreenPlayer.getPlayer(), 1L, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showSubtitleOffsetDialog$lambda$1$10(FullScreenPlayer fullScreenPlayer, Dialog dialog, View view) {
        fullScreenPlayer.setSelectSubtitlesDialog(null);
        UIHelper.INSTANCE.dismissSafe(dialog, fullScreenPlayer.getActivity());
    }

    public final void updateSpeedDialogBinding(SpeedDialogBinding binding) {
        Intrinsics.checkNotNullParameter(binding, "binding");
        float playBackSpeed = getPlayer().getPlayBackSpeed();
        TextView textView = binding.speedText;
        String str = String.format("%.2fx", Arrays.copyOf(new Object[]{Float.valueOf(playBackSpeed)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        textView.setText(StringsKt.replace$default(str, ".0x", "x", false, 4, (Object) null));
        binding.speedBar.setValue(MathKt.roundToInt(RangesKt.coerceIn(playBackSpeed, 0.1f, 2.0f) / binding.speedBar.getStepSize()) * binding.speedBar.getStepSize());
    }

    private final void showSpeedDialog() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        final boolean isPlaying = getPlayer().getIsPlaying();
        getPlayer().handleEvent(CSPlayerEvent.Pause, PlayerEventSource.UI);
        FragmentActivity fragmentActivity = activity;
        final SpeedDialogBinding speedDialogBindingInflate = SpeedDialogBinding.inflate(LayoutInflater.from(fragmentActivity));
        Intrinsics.checkNotNullExpressionValue(speedDialogBindingInflate, "inflate(...)");
        updateSpeedDialogBinding(speedDialogBindingInflate);
        Pair[] pairArr = {TuplesKt.to(speedDialogBindingInflate.speed25, Float.valueOf(0.25f)), TuplesKt.to(speedDialogBindingInflate.speed100, Float.valueOf(1.0f)), TuplesKt.to(speedDialogBindingInflate.speed125, Float.valueOf(1.25f)), TuplesKt.to(speedDialogBindingInflate.speed150, Float.valueOf(1.5f)), TuplesKt.to(speedDialogBindingInflate.speed200, Float.valueOf(2.0f))};
        for (int i = 0; i < 5; i++) {
            Pair pair = pairArr[i];
            Object objComponent1 = pair.component1();
            Intrinsics.checkNotNullExpressionValue(objComponent1, "component1(...)");
            final float fFloatValue = ((Number) pair.component2()).floatValue();
            ((MaterialButton) objComponent1).setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.FullScreenPlayer$$ExternalSyntheticLambda26
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FullScreenPlayer.showSpeedDialog$lambda$0(this.f$0, fFloatValue, speedDialogBindingInflate, view);
                }
            });
        }
        speedDialogBindingInflate.speedMinus.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.FullScreenPlayer$$ExternalSyntheticLambda27
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FullScreenPlayer.showSpeedDialog$lambda$1(this.f$0, speedDialogBindingInflate, view);
            }
        });
        speedDialogBindingInflate.speedPlus.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.FullScreenPlayer$$ExternalSyntheticLambda28
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FullScreenPlayer.showSpeedDialog$lambda$2(this.f$0, speedDialogBindingInflate, view);
            }
        });
        speedDialogBindingInflate.speedBar.addOnChangeListener(new Slider.OnChangeListener() { // from class: com.lagradost.cloudstream3.ui.player.FullScreenPlayer$$ExternalSyntheticLambda29
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.android.material.slider.BaseOnChangeListener
            public final void onValueChange(Slider slider, float f, boolean z) {
                FullScreenPlayer.showSpeedDialog$lambda$3(this.f$0, speedDialogBindingInflate, slider, f, z);
            }
        });
        DialogInterface.OnDismissListener onDismissListener = new DialogInterface.OnDismissListener() { // from class: com.lagradost.cloudstream3.ui.player.FullScreenPlayer$$ExternalSyntheticLambda30
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                FullScreenPlayer.showSpeedDialog$lambda$4(this.f$0, isPlaying, dialogInterface);
            }
        };
        AlertDialog.Builder view = new AlertDialog.Builder(fragmentActivity, R.style.AlertDialogCustom).setView(speedDialogBindingInflate.getRoot());
        view.setOnDismissListener(onDismissListener);
        AlertDialog alertDialogCreate = view.create();
        Intrinsics.checkNotNullExpressionValue(alertDialogCreate, "create(...)");
        setSelectSpeedDialog(alertDialogCreate);
        alertDialogCreate.show();
    }

    static final void showSpeedDialog$lambda$0(FullScreenPlayer fullScreenPlayer, float f, SpeedDialogBinding speedDialogBinding, View view) {
        fullScreenPlayer.setPlayBackSpeed(f);
        fullScreenPlayer.updateSpeedDialogBinding(speedDialogBinding);
    }

    static final void showSpeedDialog$lambda$1(FullScreenPlayer fullScreenPlayer, SpeedDialogBinding speedDialogBinding, View view) {
        fullScreenPlayer.setPlayBackSpeed(Math.max(fullScreenPlayer.getPlayer().getPlayBackSpeed() - 0.1f, 0.1f));
        fullScreenPlayer.updateSpeedDialogBinding(speedDialogBinding);
    }

    static final void showSpeedDialog$lambda$2(FullScreenPlayer fullScreenPlayer, SpeedDialogBinding speedDialogBinding, View view) {
        fullScreenPlayer.setPlayBackSpeed(Math.min(fullScreenPlayer.getPlayer().getPlayBackSpeed() + 0.1f, 2.0f));
        fullScreenPlayer.updateSpeedDialogBinding(speedDialogBinding);
    }

    static final void showSpeedDialog$lambda$3(FullScreenPlayer fullScreenPlayer, SpeedDialogBinding speedDialogBinding, Slider slider, float f, boolean z) {
        Intrinsics.checkNotNullParameter(slider, "<unused var>");
        if (z) {
            fullScreenPlayer.setPlayBackSpeed(f);
            fullScreenPlayer.updateSpeedDialogBinding(speedDialogBinding);
        }
    }

    static final void showSpeedDialog$lambda$4(FullScreenPlayer fullScreenPlayer, boolean z, DialogInterface dialogInterface) {
        FragmentActivity activity = fullScreenPlayer.getActivity();
        if (activity != null) {
            UIHelper.INSTANCE.hideSystemUI(activity);
        }
        if (z) {
            fullScreenPlayer.getPlayer().handleEvent(CSPlayerEvent.Play, PlayerEventSource.UI);
        }
        fullScreenPlayer.setSelectSpeedDialog(null);
    }

    private final void onClickChange() {
        LinearLayout linearLayout;
        PlayerCustomLayoutBinding playerCustomLayoutBinding;
        ImageView imageView;
        boolean z = this.isShowing;
        this.isShowing = !z;
        if (!z) {
            autoHide();
        }
        FragmentActivity activity = getActivity();
        if (activity != null) {
            UIHelper.INSTANCE.hideSystemUI(activity);
        }
        animateLayoutChanges();
        PlayerCustomLayoutBinding playerCustomLayoutBinding2 = this.playerBinding;
        if (playerCustomLayoutBinding2 == null || (linearLayout = playerCustomLayoutBinding2.playerEpisodeOverlay) == null || linearLayout.getVisibility() != 8 || (playerCustomLayoutBinding = this.playerBinding) == null || (imageView = playerCustomLayoutBinding.playerPausePlay) == null) {
            return;
        }
        imageView.requestFocus();
    }

    private final void toggleLock() {
        PlayerGestureHelper gestureHelper;
        PlayerCustomLayoutBinding playerCustomLayoutBinding;
        FrameLayout frameLayout;
        if (!this.isShowing) {
            onClickChange();
        }
        this.isLocked = !this.isLocked;
        PlayerView playerHostView = getPlayerHostView();
        if (playerHostView != null) {
            playerHostView.setLocked(this.isLocked);
        }
        updateOrientation(true);
        if (this.isLocked && this.isShowing && (playerCustomLayoutBinding = this.playerBinding) != null && (frameLayout = playerCustomLayoutBinding.playerHolder) != null) {
            frameLayout.postDelayed(new Runnable() { // from class: com.lagradost.cloudstream3.ui.player.FullScreenPlayer$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    FullScreenPlayer.toggleLock$lambda$0(this.f$0);
                }
            }, 200L);
        }
        float f = this.isLocked ? 0.0f : 1.0f;
        PlayerView playerHostView2 = getPlayerHostView();
        if (playerHostView2 != null && (gestureHelper = playerHostView2.getGestureHelper()) != null) {
            gestureHelper.animateCenterControls(f);
        }
        PlayerCustomLayoutBinding playerCustomLayoutBinding2 = this.playerBinding;
        if (playerCustomLayoutBinding2 != null) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(playerCustomLayoutBinding2.playerVideoTitleHolder.getAlpha(), f);
            alphaAnimation.setDuration(100L);
            alphaAnimation.setFillAfter(true);
            updateUIVisibility();
            AlphaAnimation alphaAnimation2 = alphaAnimation;
            playerCustomLayoutBinding2.downloadBothHeader.startAnimation(alphaAnimation2);
            if (this.hasEpisodes) {
                playerCustomLayoutBinding2.playerEpisodesButton.startAnimation(alphaAnimation2);
            }
            playerCustomLayoutBinding2.playerVideoTitleRez.startAnimation(alphaAnimation2);
            playerCustomLayoutBinding2.playerVideoInfo.startAnimation(alphaAnimation2);
            playerCustomLayoutBinding2.playerEpisodeFiller.startAnimation(alphaAnimation2);
            playerCustomLayoutBinding2.playerVideoTitleHolder.startAnimation(alphaAnimation2);
            playerCustomLayoutBinding2.playerTopHolder.startAnimation(alphaAnimation2);
            playerCustomLayoutBinding2.playerLockHolder.startAnimation(alphaAnimation2);
            View shadowOverlay = playerCustomLayoutBinding2.shadowOverlay;
            Intrinsics.checkNotNullExpressionValue(shadowOverlay, "shadowOverlay");
            shadowOverlay.setVisibility(0);
            playerCustomLayoutBinding2.shadowOverlay.startAnimation(alphaAnimation2);
        }
        updateLockUI();
    }

    static final void toggleLock$lambda$0(FullScreenPlayer fullScreenPlayer) {
        if (fullScreenPlayer.isLocked && fullScreenPlayer.isShowing) {
            fullScreenPlayer.onClickChange();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:63:0x00dc  */
    public final void updateUIVisibility() {
        boolean z;
        boolean z2 = this.isLocked || !this.isShowing;
        Context context = getContext();
        boolean z3 = (context == null || PreferenceManager.getDefaultSharedPreferences(context).getInt(getString(R.string.prefer_limit_title_key), 0) >= 0) ? z2 : true;
        PlayerCustomLayoutBinding playerCustomLayoutBinding = this.playerBinding;
        if (playerCustomLayoutBinding != null) {
            LinearLayout playerLockHolder = playerCustomLayoutBinding.playerLockHolder;
            Intrinsics.checkNotNullExpressionValue(playerLockHolder, "playerLockHolder");
            playerLockHolder.setVisibility(z2 ? 8 : 0);
            ConstraintLayout playerVideoBar = playerCustomLayoutBinding.playerVideoBar;
            Intrinsics.checkNotNullExpressionValue(playerVideoBar, "playerVideoBar");
            playerVideoBar.setVisibility(z2 ? 8 : 0);
            FrameLayout playerPausePlayHolderHolder = playerCustomLayoutBinding.playerPausePlayHolderHolder;
            Intrinsics.checkNotNullExpressionValue(playerPausePlayHolderHolder, "playerPausePlayHolderHolder");
            playerPausePlayHolderHolder.setVisibility(z2 || getCurrentPlayerStatus() == CSPlayerLoading.IsBuffering ? 8 : 0);
            FrameLayout playerTopHolder = playerCustomLayoutBinding.playerTopHolder;
            Intrinsics.checkNotNullExpressionValue(playerTopHolder, "playerTopHolder");
            playerTopHolder.setVisibility(z2 ? 8 : 0);
            boolean z4 = !z2 && isThereEpisodes();
            LinearLayout playerEpisodesButtonRoot = playerCustomLayoutBinding.playerEpisodesButtonRoot;
            Intrinsics.checkNotNullExpressionValue(playerEpisodesButtonRoot, "playerEpisodesButtonRoot");
            playerEpisodesButtonRoot.setVisibility(z4 ? 0 : 8);
            ImageView playerEpisodesButton = playerCustomLayoutBinding.playerEpisodesButton;
            Intrinsics.checkNotNullExpressionValue(playerEpisodesButton, "playerEpisodesButton");
            playerEpisodesButton.setVisibility(z4 ? 0 : 8);
            LinearLayout playerVideoTitleHolder = playerCustomLayoutBinding.playerVideoTitleHolder;
            Intrinsics.checkNotNullExpressionValue(playerVideoTitleHolder, "playerVideoTitleHolder");
            playerVideoTitleHolder.setVisibility(z3 ? 8 : 0);
            TextView playerVideoTitleRez = playerCustomLayoutBinding.playerVideoTitleRez;
            Intrinsics.checkNotNullExpressionValue(playerVideoTitleRez, "playerVideoTitleRez");
            TextView textView = playerVideoTitleRez;
            if (z2) {
                z = true;
            } else {
                CharSequence text = playerCustomLayoutBinding.playerVideoTitleRez.getText();
                Intrinsics.checkNotNullExpressionValue(text, "getText(...)");
                if (StringsKt.isBlank(text)) {
                    z = true;
                } else {
                    z = false;
                }
            }
            textView.setVisibility(z ? 8 : 0);
            MaterialButton playerEpisodeFiller = playerCustomLayoutBinding.playerEpisodeFiller;
            Intrinsics.checkNotNullExpressionValue(playerEpisodeFiller, "playerEpisodeFiller");
            playerEpisodeFiller.setVisibility(z2 ? 8 : 0);
            ConstraintLayout playerCenterMenu = playerCustomLayoutBinding.playerCenterMenu;
            Intrinsics.checkNotNullExpressionValue(playerCenterMenu, "playerCenterMenu");
            playerCenterMenu.setVisibility(z2 ? 8 : 0);
            MaterialButton playerLock = playerCustomLayoutBinding.playerLock;
            Intrinsics.checkNotNullExpressionValue(playerLock, "playerLock");
            playerLock.setVisibility(!this.isShowing ? 8 : 0);
            LinearLayout playerGoBackHolder = playerCustomLayoutBinding.playerGoBackHolder;
            Intrinsics.checkNotNullExpressionValue(playerGoBackHolder, "playerGoBackHolder");
            playerGoBackHolder.setVisibility(z2 ? 8 : 0);
            MaterialButton playerSourcesBtt = playerCustomLayoutBinding.playerSourcesBtt;
            Intrinsics.checkNotNullExpressionValue(playerSourcesBtt, "playerSourcesBtt");
            playerSourcesBtt.setVisibility(z2 ? 8 : 0);
            View shadowOverlay = playerCustomLayoutBinding.shadowOverlay;
            Intrinsics.checkNotNullExpressionValue(shadowOverlay, "shadowOverlay");
            shadowOverlay.setVisibility(z2 ? 8 : 0);
            playerCustomLayoutBinding.playerSkipEpisode.setClickable(!z2);
        }
    }

    private final void updateLockUI() {
        Integer numValueOf;
        PlayerCustomLayoutBinding playerCustomLayoutBinding = this.playerBinding;
        if (playerCustomLayoutBinding != null) {
            playerCustomLayoutBinding.playerLock.setIconResource(this.isLocked ? R.drawable.video_locked : R.drawable.video_unlocked);
            if (this.isLocked) {
                Context context = getContext();
                numValueOf = context != null ? Integer.valueOf(UIHelper.INSTANCE.colorFromAttribute(context, R.attr.colorPrimary)) : null;
            } else {
                numValueOf = -1;
            }
            if (numValueOf != null) {
                playerCustomLayoutBinding.playerLock.setTextColor(numValueOf.intValue());
                playerCustomLayoutBinding.playerLock.setIconTint(ColorStateList.valueOf(numValueOf.intValue()));
                playerCustomLayoutBinding.playerLock.setRippleColor(ColorStateList.valueOf(Color.argb(50, (numValueOf.intValue() >> 16) & 255, (numValueOf.intValue() >> 8) & 255, numValueOf.intValue() & 255)));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void autoHide() {
        this.metadataVisibilityToken++;
        PlayerView playerHostView = getPlayerHostView();
        if (playerHostView != null) {
            PlayerView.scheduleAutoHide$default(playerHostView, 0L, 1, null);
        }
        scheduleMetadataVisibility();
    }

    @Override // com.lagradost.cloudstream3.ui.player.AbstractPlayerFragment, com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void onAutoHideUI() {
        if (getPlayer().getIsPlaying()) {
            onClickChange();
        }
    }

    protected final void hidePlayerUI() {
        if (this.isShowing) {
            this.isShowing = false;
            animateLayoutChanges();
        }
    }

    @Override // com.lagradost.cloudstream3.ui.player.AbstractPlayerFragment, com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public boolean isUIShowing() {
        return this.isShowing;
    }

    @Override // com.lagradost.cloudstream3.ui.player.AbstractPlayerFragment, com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void onSingleTap() {
        onClickChange();
    }

    @Override // com.lagradost.cloudstream3.ui.player.AbstractPlayerFragment, com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void onTouchDown() {
        if (this.isShowingEpisodeOverlay) {
            toggleEpisodesOverlay(false);
        }
    }

    @Override // com.lagradost.cloudstream3.ui.player.AbstractPlayerFragment, com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void onSeekPreviewText(String text) {
        TextView textView;
        PlayerCustomLayoutBinding playerCustomLayoutBinding = this.playerBinding;
        if (playerCustomLayoutBinding == null || (textView = playerCustomLayoutBinding.playerTimeText) == null) {
            return;
        }
        textView.setVisibility(text != null ? 0 : 8);
        if (text != null) {
            textView.setText(text);
        }
    }

    @Override // com.lagradost.cloudstream3.ui.player.AbstractPlayerFragment, com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void onHidePlayerUI() {
        hidePlayerUI();
    }

    @Override // com.lagradost.cloudstream3.ui.player.AbstractPlayerFragment, com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void onGestureEnd(boolean hadSwipe, boolean wasUiShowing) {
        if (!getPlayer().getIsPlaying() && hadSwipe && wasUiShowing && !this.isShowing) {
            this.isShowing = true;
            animateLayoutChanges();
        }
        autoHide();
    }

    @Override // com.lagradost.cloudstream3.ui.player.AbstractPlayerFragment, com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void playerStatusChanged() {
        super.playerStatusChanged();
        scheduleMetadataVisibility();
    }

    @Override // com.lagradost.cloudstream3.ui.player.AbstractPlayerFragment, com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void onHoldSpeedUp(boolean show) {
        if (show && this.isShowing) {
            onClickChange();
        }
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragment, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        final PlayerGestureHelper gestureHelper;
        androidx.media3.ui.PlayerView playerView;
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        PlayerView playerHostView = getPlayerHostView();
        if (playerHostView == null || (gestureHelper = playerHostView.getGestureHelper()) == null) {
            return;
        }
        final Matrix zoomMatrix = gestureHelper.getZoomMatrix();
        ValueAnimator matrixAnimation = gestureHelper.getMatrixAnimation();
        if ((matrixAnimation != null && matrixAnimation.isRunning()) || zoomMatrix == null || (playerView = getPlayerView()) == null) {
            return;
        }
        playerView.post(new Runnable() { // from class: com.lagradost.cloudstream3.ui.player.FullScreenPlayer$$ExternalSyntheticLambda31
            @Override // java.lang.Runnable
            public final void run() {
                FullScreenPlayer.onConfigurationChanged$lambda$0(gestureHelper, zoomMatrix, this);
            }
        });
    }

    static final void onConfigurationChanged$lambda$0(PlayerGestureHelper playerGestureHelper, Matrix matrix, FullScreenPlayer fullScreenPlayer) {
        playerGestureHelper.applyZoomMatrix(matrix, true);
        PlayerView playerHostView = fullScreenPlayer.getPlayerHostView();
        if (playerHostView != null) {
            playerHostView.requestUpdateBrightnessOverlayOnNextLayout();
        }
    }

    @Override // com.lagradost.cloudstream3.ui.player.AbstractPlayerFragment
    public void resize(PlayerResize resize, boolean showToast) {
        Intrinsics.checkNotNullParameter(resize, "resize");
        super.resize(resize, showToast);
        PlayerView playerHostView = getPlayerHostView();
        if (playerHostView != null) {
            playerHostView.requestUpdateBrightnessOverlayOnNextLayout();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:100:0x017a  */
    /* JADX WARN: Code duplicated, block: B:101:0x0184  */
    /* JADX WARN: Code duplicated, block: B:102:0x0188  */
    /* JADX WARN: Code duplicated, block: B:20:0x003b  */
    /* JADX WARN: Code duplicated, block: B:21:0x0040  */
    /* JADX WARN: Code duplicated, block: B:22:0x0045  */
    /* JADX WARN: Code duplicated, block: B:23:0x0050  */
    /* JADX WARN: Code duplicated, block: B:24:0x005b  */
    /* JADX WARN: Code duplicated, block: B:25:0x0066  */
    /* JADX WARN: Code duplicated, block: B:27:0x0072 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:29:0x007e  */
    /* JADX WARN: Code duplicated, block: B:30:0x0083  */
    /* JADX WARN: Code duplicated, block: B:38:0x009f  */
    /* JADX WARN: Code duplicated, block: B:39:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:99:0x0170  */
    private final Boolean handleKeyDownEvent(int keyCode) {
        ImageView imageView;
        ImageView imageView2;
        Context context;
        if (keyCode != 46) {
            if (keyCode != 47) {
                if (keyCode != 102) {
                    if (keyCode == 103) {
                        IPlayer.CC.handleEvent$default(getPlayer(), CSPlayerEvent.NextEpisode, null, 2, null);
                    } else {
                        switch (keyCode) {
                            case 7:
                                nextResize();
                                break;
                            case 19:
                            case 20:
                                if (this.isShowing || this.isShowingEpisodeOverlay) {
                                    return null;
                                }
                                onClickChange();
                                break;
                            case 21:
                                if (!this.isShowing && !this.isLocked && !this.isShowingEpisodeOverlay) {
                                    IPlayer.CC.seekTime$default(getPlayer(), -this.androidTVInterfaceOffSeekTime, null, 2, null);
                                    return true;
                                }
                                PlayerCustomLayoutBinding playerCustomLayoutBinding = this.playerBinding;
                                if (playerCustomLayoutBinding == null || (imageView = playerCustomLayoutBinding.playerPausePlay) == null || !imageView.isFocused()) {
                                    return null;
                                }
                                IPlayer.CC.seekTime$default(getPlayer(), -this.androidTVInterfaceOnSeekTime, null, 2, null);
                                return true;
                            case 22:
                                if (!this.isShowing && !this.isLocked && !this.isShowingEpisodeOverlay) {
                                    IPlayer.CC.seekTime$default(getPlayer(), this.androidTVInterfaceOffSeekTime, null, 2, null);
                                } else {
                                    PlayerCustomLayoutBinding playerCustomLayoutBinding2 = this.playerBinding;
                                    if (playerCustomLayoutBinding2 == null || (imageView2 = playerCustomLayoutBinding2.playerPausePlay) == null || !imageView2.isFocused()) {
                                        return null;
                                    }
                                    IPlayer.CC.seekTime$default(getPlayer(), this.androidTVInterfaceOnSeekTime, null, 2, null);
                                }
                                break;
                            case 23:
                            case 66:
                                if (this.isShowing || isDialogOpen()) {
                                    return null;
                                }
                                if (this.timestampShowState) {
                                    IPlayer.CC.handleEvent$default(getPlayer(), CSPlayerEvent.SkipCurrentChapter, null, 2, null);
                                } else if (!this.isLocked) {
                                    IPlayer.CC.handleEvent$default(getPlayer(), CSPlayerEvent.PlayPauseToggle, null, 2, null);
                                }
                                onClickChange();
                                break;
                            case 24:
                            case 25:
                                PlayerView playerHostView = getPlayerHostView();
                                if (playerHostView == null || !playerHostView.handleVolumeKey(keyCode)) {
                                    return null;
                                }
                                break;
                            case 36:
                                onClickChange();
                                break;
                            case 50:
                                IPlayer.CC.handleEvent$default(getPlayer(), CSPlayerEvent.SkipCurrentChapter, null, 2, null);
                                break;
                            case 62:
                            case 85:
                            case 160:
                                IPlayer.CC.handleEvent$default(getPlayer(), CSPlayerEvent.PlayPauseToggle, null, 2, null);
                                break;
                            case 82:
                            case 176:
                                if (this.isLocked || !isThereEpisodes()) {
                                    return null;
                                }
                                toggleEpisodesOverlay(true);
                                break;
                            case 108:
                                IPlayer.CC.handleEvent$default(getPlayer(), CSPlayerEvent.Play, null, 2, null);
                                break;
                            case 151:
                                toggleLock();
                                break;
                            case 152:
                                context = getContext();
                                if (GeneratorPlayer.INSTANCE.getSubsProvidersIsActive() && context != null) {
                                    openOnlineSubPicker(context, null, new Function0() { // from class: com.lagradost.cloudstream3.ui.player.FullScreenPlayer$$ExternalSyntheticLambda1
                                        @Override // kotlin.jvm.functions.Function0
                                        public final Object invoke() {
                                            return Unit.INSTANCE;
                                        }
                                    });
                                }
                                break;
                            case 153:
                                showMirrorsDialogue();
                                break;
                            case 164:
                                IPlayer.CC.handleEvent$default(getPlayer(), CSPlayerEvent.ToggleMute, null, 2, null);
                                break;
                            case 166:
                                IPlayer.CC.handleEvent$default(getPlayer(), CSPlayerEvent.NextEpisode, null, 2, null);
                                break;
                            case 167:
                                IPlayer.CC.handleEvent$default(getPlayer(), CSPlayerEvent.PrevEpisode, null, 2, null);
                                break;
                            case 272:
                                IPlayer.CC.handleEvent$default(getPlayer(), CSPlayerEvent.SeekForward, null, 2, null);
                                break;
                            case 273:
                                IPlayer.CC.handleEvent$default(getPlayer(), CSPlayerEvent.SeekBack, null, 2, null);
                                break;
                            default:
                                switch (keyCode) {
                                    case 10:
                                        showSpeedDialog();
                                        break;
                                    case 11:
                                        skipOp();
                                        break;
                                    case 12:
                                        IPlayer.CC.handleEvent$default(getPlayer(), CSPlayerEvent.SkipCurrentChapter, null, 2, null);
                                        break;
                                    default:
                                        switch (keyCode) {
                                            case 14:
                                                toggleLock();
                                                break;
                                            case 15:
                                                context = getContext();
                                                if (GeneratorPlayer.INSTANCE.getSubsProvidersIsActive()) {
                                                    openOnlineSubPicker(context, null, new Function0() { // from class: com.lagradost.cloudstream3.ui.player.FullScreenPlayer$$ExternalSyntheticLambda1
                                                        @Override // kotlin.jvm.functions.Function0
                                                        public final Object invoke() {
                                                            return Unit.INSTANCE;
                                                        }
                                                    });
                                                }
                                                break;
                                            case 16:
                                                showMirrorsDialogue();
                                                break;
                                            default:
                                                switch (keyCode) {
                                                    case 29:
                                                        IPlayer.CC.handleEvent$default(getPlayer(), CSPlayerEvent.SeekBack, null, 2, null);
                                                        break;
                                                    case 30:
                                                        IPlayer.CC.handleEvent$default(getPlayer(), CSPlayerEvent.PrevEpisode, null, 2, null);
                                                        break;
                                                    case 31:
                                                        skipOp();
                                                        break;
                                                    case 32:
                                                        IPlayer.CC.handleEvent$default(getPlayer(), CSPlayerEvent.SeekForward, null, 2, null);
                                                        break;
                                                    case 33:
                                                        showSpeedDialog();
                                                        break;
                                                    default:
                                                        switch (keyCode) {
                                                            case 40:
                                                                toggleLock();
                                                                break;
                                                            case 41:
                                                                IPlayer.CC.handleEvent$default(getPlayer(), CSPlayerEvent.ToggleMute, null, 2, null);
                                                                break;
                                                            case 42:
                                                                IPlayer.CC.handleEvent$default(getPlayer(), CSPlayerEvent.NextEpisode, null, 2, null);
                                                                break;
                                                            case 43:
                                                                context = getContext();
                                                                if (GeneratorPlayer.INSTANCE.getSubsProvidersIsActive()) {
                                                                    openOnlineSubPicker(context, null, new Function0() { // from class: com.lagradost.cloudstream3.ui.player.FullScreenPlayer$$ExternalSyntheticLambda1
                                                                        @Override // kotlin.jvm.functions.Function0
                                                                        public final Object invoke() {
                                                                            return Unit.INSTANCE;
                                                                        }
                                                                    });
                                                                }
                                                                break;
                                                            case 44:
                                                                IPlayer.CC.handleEvent$default(getPlayer(), CSPlayerEvent.PlayPauseToggle, null, 2, null);
                                                                break;
                                                            default:
                                                                switch (keyCode) {
                                                                    case 87:
                                                                        IPlayer.CC.handleEvent$default(getPlayer(), CSPlayerEvent.NextEpisode, null, 2, null);
                                                                        break;
                                                                    case 88:
                                                                        IPlayer.CC.handleEvent$default(getPlayer(), CSPlayerEvent.PrevEpisode, null, 2, null);
                                                                        break;
                                                                    case 89:
                                                                        IPlayer.CC.handleEvent$default(getPlayer(), CSPlayerEvent.SeekBack, null, 2, null);
                                                                        break;
                                                                    case 90:
                                                                        IPlayer.CC.handleEvent$default(getPlayer(), CSPlayerEvent.SeekForward, null, 2, null);
                                                                        break;
                                                                    default:
                                                                        switch (keyCode) {
                                                                            case 125:
                                                                                IPlayer.CC.handleEvent$default(getPlayer(), CSPlayerEvent.SeekForward, null, 2, null);
                                                                                break;
                                                                            case 126:
                                                                                IPlayer.CC.handleEvent$default(getPlayer(), CSPlayerEvent.Play, null, 2, null);
                                                                                break;
                                                                            case 127:
                                                                                IPlayer.CC.handleEvent$default(getPlayer(), CSPlayerEvent.Pause, null, 2, null);
                                                                                break;
                                                                            default:
                                                                                switch (keyCode) {
                                                                                    case 144:
                                                                                        nextResize();
                                                                                        break;
                                                                                    case 145:
                                                                                        IPlayer.CC.handleEvent$default(getPlayer(), CSPlayerEvent.PrevEpisode, null, 2, null);
                                                                                        break;
                                                                                    case 146:
                                                                                        IPlayer.CC.handleEvent$default(getPlayer(), CSPlayerEvent.NextEpisode, null, 2, null);
                                                                                        break;
                                                                                    case 147:
                                                                                        showSpeedDialog();
                                                                                        break;
                                                                                    case 148:
                                                                                        skipOp();
                                                                                        break;
                                                                                    case 149:
                                                                                        IPlayer.CC.handleEvent$default(getPlayer(), CSPlayerEvent.SkipCurrentChapter, null, 2, null);
                                                                                        break;
                                                                                    default:
                                                                                        return null;
                                                                                }
                                                                                break;
                                                                        }
                                                                        break;
                                                                }
                                                                break;
                                                        }
                                                        break;
                                                }
                                                break;
                                        }
                                        break;
                                }
                                break;
                        }
                    }
                } else {
                    IPlayer.CC.handleEvent$default(getPlayer(), CSPlayerEvent.PrevEpisode, null, 2, null);
                }
            } else {
                showMirrorsDialogue();
            }
        } else {
            nextResize();
        }
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0028  */
    /* JADX WARN: Code duplicated, block: B:20:0x002c A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:22:0x002e  */
    private final boolean handleKeyEvent(KeyEvent event, boolean hasNavigated) {
        Boolean boolHandleKeyDownEvent;
        if (hasNavigated) {
            autoHide();
            return false;
        }
        int keyCode = event.getKeyCode();
        if (event.getAction() == 0 && (boolHandleKeyDownEvent = handleKeyDownEvent(keyCode)) != null) {
            return boolHandleKeyDownEvent.booleanValue();
        }
        if (keyCode == 19 || keyCode == 20) {
            if (this.isShowing) {
                return true;
            }
            autoHide();
        } else {
            switch (keyCode) {
                case 268:
                case 269:
                case 270:
                case 271:
                    if (this.isShowing) {
                        autoHide();
                        break;
                    } else {
                        return true;
                    }
                default:
                    return false;
            }
        }
        return false;
    }

    protected final void uiReset() {
        PlayerGestureHelper gestureHelper;
        PlayerGestureHelper gestureHelper2;
        FrameLayout frameLayout;
        this.metadataVisibilityToken++;
        PlayerCustomLayoutBinding playerCustomLayoutBinding = this.playerBinding;
        if (playerCustomLayoutBinding != null && (frameLayout = playerCustomLayoutBinding.playerMetadataScrim) != null) {
            frameLayout.animate().cancel();
            frameLayout.setAlpha(0.0f);
            frameLayout.setVisibility(8);
        }
        this.isShowing = false;
        toggleEpisodesOverlay(false);
        PlayerCustomLayoutBinding playerCustomLayoutBinding2 = this.playerBinding;
        if (playerCustomLayoutBinding2 != null) {
            MaterialButton playerSkipEpisode = playerCustomLayoutBinding2.playerSkipEpisode;
            Intrinsics.checkNotNullExpressionValue(playerSkipEpisode, "playerSkipEpisode");
            playerSkipEpisode.setVisibility(8);
            LinearLayout playerGoForwardRoot = playerCustomLayoutBinding2.playerGoForwardRoot;
            Intrinsics.checkNotNullExpressionValue(playerGoForwardRoot, "playerGoForwardRoot");
            playerGoForwardRoot.setVisibility(8);
            MaterialButton playerTracksBtt = playerCustomLayoutBinding2.playerTracksBtt;
            Intrinsics.checkNotNullExpressionValue(playerTracksBtt, "playerTracksBtt");
            playerTracksBtt.setVisibility(8);
            MaterialButton playerSkipOp = playerCustomLayoutBinding2.playerSkipOp;
            Intrinsics.checkNotNullExpressionValue(playerSkipOp, "playerSkipOp");
            playerSkipOp.setVisibility(8);
            View shadowOverlay = playerCustomLayoutBinding2.shadowOverlay;
            Intrinsics.checkNotNullExpressionValue(shadowOverlay, "shadowOverlay");
            shadowOverlay.setVisibility(8);
        }
        updateLockUI();
        updateUIVisibility();
        animateLayoutChanges();
        PlayerView playerHostView = getPlayerHostView();
        if (playerHostView != null && (gestureHelper2 = playerHostView.getGestureHelper()) != null) {
            gestureHelper2.resetFastForwardText();
        }
        PlayerView playerHostView2 = getPlayerHostView();
        if (playerHostView2 == null || (gestureHelper = playerHostView2.getGestureHelper()) == null) {
            return;
        }
        gestureHelper.resetRewindText();
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        outState.putLong("subtitle_delay", getSubtitleDelay());
        super.onSaveInstanceState(outState);
    }

    @Override // com.lagradost.cloudstream3.ui.player.AbstractPlayerFragment, com.lagradost.cloudstream3.ui.BaseFragment, com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void onBindingCreated(FragmentPlayerBinding binding, Bundle savedInstanceState) {
        QualityDataHelper.QualityProfileType qualityProfileType;
        int id;
        Intrinsics.checkNotNullParameter(binding, "binding");
        this.playerBinding = PlayerCustomLayoutBinding.bind(binding.getRoot().findViewById(R.id.player_holder));
        super.onBindingCreated(binding, savedInstanceState);
        PlayerView playerHostView = getPlayerHostView();
        if (playerHostView != null) {
            playerHostView.setFullScreen(true);
        }
        PlayerView playerHostView2 = getPlayerHostView();
        Object obj = null;
        if (playerHostView2 != null) {
            PlayerCustomLayoutBinding playerCustomLayoutBinding = this.playerBinding;
            playerHostView2.setVideoOutline(playerCustomLayoutBinding != null ? playerCustomLayoutBinding.videoOutline : null);
        }
        PlayerView playerHostView3 = getPlayerHostView();
        if (playerHostView3 != null) {
            playerHostView3.requestUpdateBrightnessOverlayOnNextLayout();
        }
        Intrinsics.checkNotNullExpressionValue(binding.getRoot(), "getRoot(...)");
        setPlayBackSpeed(DataStoreHelper.INSTANCE.getPlayBackSpeed());
        if (savedInstanceState != null) {
            setSubtitleDelay(savedInstanceState.getLong("subtitle_delay"));
        }
        setupKeyEventListener();
        try {
            Context context = getContext();
            if (context != null) {
                SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                this.androidTVInterfaceOffSeekTime = ((long) defaultSharedPreferences.getInt(context.getString(R.string.android_tv_interface_off_seek_key), 10)) * 1000;
                this.androidTVInterfaceOnSeekTime = ((long) defaultSharedPreferences.getInt(context.getString(R.string.android_tv_interface_on_seek_key), 10)) * 1000;
                this.playBackSpeedEnabled = defaultSharedPreferences.getBoolean(context.getString(R.string.playback_speed_enabled_key), false);
                this.playerRotateEnabled = defaultSharedPreferences.getBoolean(context.getString(R.string.rotate_video_key), false);
                this.playerResizeEnabled = defaultSharedPreferences.getBoolean(context.getString(R.string.player_resize_enabled_key), true);
                this.hideControlsNames = defaultSharedPreferences.getBoolean(context.getString(R.string.hide_player_control_names_key), false);
                List<QualityDataHelper.QualityProfile> profiles = QualityDataHelper.INSTANCE.getProfiles();
                if (AppContextUtils.INSTANCE.isUsingMobileData(context)) {
                    qualityProfileType = QualityDataHelper.QualityProfileType.Data;
                } else {
                    qualityProfileType = QualityDataHelper.QualityProfileType.WiFi;
                }
                for (Object obj2 : profiles) {
                    if (((QualityDataHelper.QualityProfile) obj2).getTypes().contains(qualityProfileType)) {
                        obj = obj2;
                        break;
                    }
                }
                QualityDataHelper.QualityProfile qualityProfile = (QualityDataHelper.QualityProfile) obj;
                if (qualityProfile != null) {
                    id = qualityProfile.getId();
                } else {
                    QualityDataHelper.QualityProfile qualityProfile2 = (QualityDataHelper.QualityProfile) CollectionsKt.firstOrNull((List) profiles);
                    id = qualityProfile2 != null ? qualityProfile2.getId() : this.currentQualityProfile;
                }
                this.currentQualityProfile = id;
            }
            PlayerCustomLayoutBinding playerCustomLayoutBinding2 = this.playerBinding;
            if (playerCustomLayoutBinding2 != null) {
                MaterialButton playerSpeedBtt = playerCustomLayoutBinding2.playerSpeedBtt;
                Intrinsics.checkNotNullExpressionValue(playerSpeedBtt, "playerSpeedBtt");
                playerSpeedBtt.setVisibility(this.playBackSpeedEnabled ? 0 : 8);
                MaterialButton playerResizeBtt = playerCustomLayoutBinding2.playerResizeBtt;
                Intrinsics.checkNotNullExpressionValue(playerResizeBtt, "playerResizeBtt");
                playerResizeBtt.setVisibility(this.playerResizeEnabled ? 0 : 8);
                MaterialButton playerRotateBtt = playerCustomLayoutBinding2.playerRotateBtt;
                Intrinsics.checkNotNullExpressionValue(playerRotateBtt, "playerRotateBtt");
                playerRotateBtt.setVisibility(Globals.INSTANCE.isLayout(6) ? false : this.playerRotateEnabled ? 0 : 8);
                if (this.hideControlsNames) {
                    hideControlsNames(playerCustomLayoutBinding2);
                }
            }
        } catch (Exception e) {
            ArchComponentExtKt.logError(e);
        }
        final PlayerCustomLayoutBinding playerCustomLayoutBinding3 = this.playerBinding;
        if (playerCustomLayoutBinding3 != null) {
            if (Globals.INSTANCE.isLayout(6)) {
                for (Map.Entry entry : MapsKt.mapOf(TuplesKt.to(playerCustomLayoutBinding3.playerGoBack, playerCustomLayoutBinding3.playerGoBackText), TuplesKt.to(playerCustomLayoutBinding3.playerRestart, playerCustomLayoutBinding3.playerRestartText), TuplesKt.to(playerCustomLayoutBinding3.playerGoForward, playerCustomLayoutBinding3.playerGoForwardText), TuplesKt.to(playerCustomLayoutBinding3.downloadHeaderToggle, playerCustomLayoutBinding3.downloadHeaderToggleText), TuplesKt.to(playerCustomLayoutBinding3.playerEpisodesButton, playerCustomLayoutBinding3.playerEpisodesButtonText)).entrySet()) {
                    Object key = entry.getKey();
                    Intrinsics.checkNotNullExpressionValue(key, "component1(...)");
                    final ImageView imageView = (ImageView) key;
                    Object value = entry.getValue();
                    Intrinsics.checkNotNullExpressionValue(value, "component2(...)");
                    final TextView textView = (TextView) value;
                    imageView.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.lagradost.cloudstream3.ui.player.FullScreenPlayer$$ExternalSyntheticLambda7
                        @Override // android.view.View.OnFocusChangeListener
                        public final void onFocusChange(View view, boolean z) {
                            FullScreenPlayer.onBindingCreated$lambda$3$0$0(textView, imageView, this, view, z);
                        }
                    });
                }
            }
            playerCustomLayoutBinding3.skipChapterButton.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.FullScreenPlayer$$ExternalSyntheticLambda16
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FullScreenPlayer.onBindingCreated$lambda$3$1(playerCustomLayoutBinding3, this, view);
                }
            });
            playerCustomLayoutBinding3.playerRotateBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.FullScreenPlayer$$ExternalSyntheticLambda17
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FullScreenPlayer.onBindingCreated$lambda$3$2(this.f$0, view);
                }
            });
            playerCustomLayoutBinding3.playerResizeBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.FullScreenPlayer$$ExternalSyntheticLambda18
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FullScreenPlayer.onBindingCreated$lambda$3$3(this.f$0, view);
                }
            });
            playerCustomLayoutBinding3.playerSpeedBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.FullScreenPlayer$$ExternalSyntheticLambda19
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FullScreenPlayer.onBindingCreated$lambda$3$4(this.f$0, view);
                }
            });
            playerCustomLayoutBinding3.playerSkipOp.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.FullScreenPlayer$$ExternalSyntheticLambda20
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FullScreenPlayer.onBindingCreated$lambda$3$5(this.f$0, view);
                }
            });
            playerCustomLayoutBinding3.playerSkipEpisode.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.FullScreenPlayer$$ExternalSyntheticLambda21
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FullScreenPlayer.onBindingCreated$lambda$3$6(this.f$0, view);
                }
            });
            playerCustomLayoutBinding3.playerGoForward.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.FullScreenPlayer$$ExternalSyntheticLambda23
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FullScreenPlayer.onBindingCreated$lambda$3$7(this.f$0, view);
                }
            });
            playerCustomLayoutBinding3.playerRestart.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.FullScreenPlayer$$ExternalSyntheticLambda24
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FullScreenPlayer.onBindingCreated$lambda$3$8(this.f$0, view);
                }
            });
            playerCustomLayoutBinding3.playerLock.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.FullScreenPlayer$$ExternalSyntheticLambda25
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FullScreenPlayer.onBindingCreated$lambda$3$9(this.f$0, view);
                }
            });
            playerCustomLayoutBinding3.playerSubtitleOffsetBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.FullScreenPlayer$$ExternalSyntheticLambda8
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.showSubtitleOffsetDialog();
                }
            });
            playerCustomLayoutBinding3.playerGoBack.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.FullScreenPlayer$$ExternalSyntheticLambda9
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FullScreenPlayer.onBindingCreated$lambda$3$11(this.f$0, view);
                }
            });
            playerCustomLayoutBinding3.playerSourcesBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.FullScreenPlayer$$ExternalSyntheticLambda10
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.showMirrorsDialogue();
                }
            });
            playerCustomLayoutBinding3.playerTracksBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.FullScreenPlayer$$ExternalSyntheticLambda12
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.showTracksDialogue();
                }
            });
            playerCustomLayoutBinding3.playerControlsScroll.setOnScrollChangeListener(new View.OnScrollChangeListener() { // from class: com.lagradost.cloudstream3.ui.player.FullScreenPlayer$$ExternalSyntheticLambda13
                @Override // android.view.View.OnScrollChangeListener
                public final void onScrollChange(View view, int i, int i2, int i3, int i4) {
                    this.f$0.autoHide();
                }
            });
            playerCustomLayoutBinding3.exoProgress.registerPlayerView(getPlayerView());
            playerCustomLayoutBinding3.exoProgress.setOnTouchListener(new View.OnTouchListener() { // from class: com.lagradost.cloudstream3.ui.player.FullScreenPlayer$$ExternalSyntheticLambda14
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    return FullScreenPlayer.onBindingCreated$lambda$3$15(this.f$0, view, motionEvent);
                }
            });
            playerCustomLayoutBinding3.playerEpisodesButton.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.FullScreenPlayer$$ExternalSyntheticLambda15
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.toggleEpisodesOverlay(true);
                }
            });
        }
        try {
            uiReset();
        } catch (Exception e2) {
            ArchComponentExtKt.logError(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$3$0$0(TextView textView, ImageView imageView, FullScreenPlayer fullScreenPlayer, View view, boolean z) {
        if (!z) {
            textView.setSelected(false);
            textView.setVisibility(8);
            return;
        }
        if (imageView.getId() == R.id.player_episodes_button) {
            fullScreenPlayer.toggleEpisodesOverlay(true);
        } else {
            fullScreenPlayer.toggleEpisodesOverlay(false);
        }
        textView.setSelected(true);
        textView.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$3$1(PlayerCustomLayoutBinding playerCustomLayoutBinding, FullScreenPlayer fullScreenPlayer, View view) {
        if (playerCustomLayoutBinding.skipChapterButton.hasFocus()) {
            playerCustomLayoutBinding.playerPausePlay.requestFocus();
        }
        IPlayer.CC.handleEvent$default(fullScreenPlayer.getPlayer(), CSPlayerEvent.SkipCurrentChapter, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$3$2(FullScreenPlayer fullScreenPlayer, View view) {
        fullScreenPlayer.autoHide();
        fullScreenPlayer.toggleRotate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$3$3(FullScreenPlayer fullScreenPlayer, View view) {
        fullScreenPlayer.autoHide();
        fullScreenPlayer.nextResize();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$3$4(FullScreenPlayer fullScreenPlayer, View view) {
        fullScreenPlayer.autoHide();
        fullScreenPlayer.showSpeedDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$3$5(FullScreenPlayer fullScreenPlayer, View view) {
        fullScreenPlayer.autoHide();
        fullScreenPlayer.skipOp();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$3$6(FullScreenPlayer fullScreenPlayer, View view) {
        fullScreenPlayer.autoHide();
        IPlayer.CC.handleEvent$default(fullScreenPlayer.getPlayer(), CSPlayerEvent.NextEpisode, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$3$7(FullScreenPlayer fullScreenPlayer, View view) {
        fullScreenPlayer.autoHide();
        IPlayer.CC.handleEvent$default(fullScreenPlayer.getPlayer(), CSPlayerEvent.NextEpisode, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$3$8(FullScreenPlayer fullScreenPlayer, View view) {
        fullScreenPlayer.autoHide();
        IPlayer.CC.handleEvent$default(fullScreenPlayer.getPlayer(), CSPlayerEvent.Restart, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$3$9(FullScreenPlayer fullScreenPlayer, View view) {
        fullScreenPlayer.autoHide();
        fullScreenPlayer.toggleLock();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$3$11(FullScreenPlayer fullScreenPlayer, View view) {
        FragmentActivity activity = fullScreenPlayer.getActivity();
        if (activity != null) {
            UIHelper.INSTANCE.popCurrentPage(activity, "FullScreenPlayer");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onBindingCreated$lambda$3$15(FullScreenPlayer fullScreenPlayer, View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action != 1) {
                if (action != 2) {
                    if (action != 3 && action != 12) {
                        return false;
                    }
                }
            }
            fullScreenPlayer.autoHide();
            return false;
        }
        PlayerView playerHostView = fullScreenPlayer.getPlayerHostView();
        if (playerHostView == null) {
            return false;
        }
        playerHostView.cancelAutoHide();
        return false;
    }

    private final void toggleRotate() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            toggleOrientationWithSensor(activity);
            this.rotatedManually = true;
        }
    }

    private static final void hideControlsNames$iterate(LinearLayout linearLayout) {
        for (View view : ViewGroupKt.getChildren(linearLayout)) {
            if (view instanceof MaterialButton) {
                MaterialButton materialButton = (MaterialButton) view;
                materialButton.setTextSize(0.0f);
                materialButton.setIconPadding(0);
                materialButton.setIconGravity(2);
                materialButton.setPadding(0, 0, 0, 0);
            } else if (view instanceof LinearLayout) {
                hideControlsNames$iterate((LinearLayout) view);
            }
        }
    }

    private final void hideControlsNames(PlayerCustomLayoutBinding playerCustomLayoutBinding) {
        ViewParent parent = playerCustomLayoutBinding.playerLockHolder.getParent();
        Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.widget.LinearLayout");
        hideControlsNames$iterate((LinearLayout) parent);
    }

    @Override // com.lagradost.cloudstream3.ui.player.AbstractPlayerFragment, com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void playerDimensionsLoaded(int width, int height) {
        if (!Globals.INSTANCE.isLayout(6) && width > 0 && height > 0) {
            updateOrientation$default(this, false, 1, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void toggleEpisodesOverlay(boolean show) {
        if (show && !this.isShowingEpisodeOverlay) {
            this.previousPlayStatus = getPlayer().getIsPlaying();
            IPlayer.CC.handleEvent$default(getPlayer(), CSPlayerEvent.Pause, null, 2, null);
            showEpisodesOverlay();
            this.isShowingEpisodeOverlay = true;
            animateEpisodesOverlay(true);
            return;
        }
        if (this.isShowingEpisodeOverlay) {
            if (this.previousPlayStatus) {
                IPlayer.CC.handleEvent$default(getPlayer(), CSPlayerEvent.Play, null, 2, null);
            }
            this.isShowingEpisodeOverlay = false;
            animateEpisodesOverlay(false);
        }
    }

    private final void animateEpisodesOverlay(final boolean show) {
        LinearLayout linearLayout;
        LinearLayout linearLayout2;
        PlayerCustomLayoutBinding playerCustomLayoutBinding = this.playerBinding;
        if (playerCustomLayoutBinding == null || (linearLayout = playerCustomLayoutBinding.playerEpisodeOverlay) == null) {
            return;
        }
        linearLayout.animate().cancel();
        ViewParent parent = linearLayout.getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup != null) {
            viewGroup.setLayoutTransition(null);
        }
        float f = 50.0f * linearLayout.getResources().getDisplayMetrics().density;
        linearLayout.setTranslationX(show ? f : 0.0f);
        PlayerCustomLayoutBinding playerCustomLayoutBinding2 = this.playerBinding;
        if (playerCustomLayoutBinding2 != null && (linearLayout2 = playerCustomLayoutBinding2.playerEpisodeOverlay) != null) {
            linearLayout2.setVisibility(0);
        }
        ViewPropertyAnimator viewPropertyAnimatorAnimate = linearLayout.animate();
        if (show) {
            f = 0.0f;
        }
        viewPropertyAnimatorAnimate.translationX(f).alpha(show ? 1.0f : 0.0f).setDuration(300L).setInterpolator(new AccelerateDecelerateInterpolator()).withEndAction(new Runnable() { // from class: com.lagradost.cloudstream3.ui.player.FullScreenPlayer$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                FullScreenPlayer.animateEpisodesOverlay$lambda$0$0(show, this);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void animateEpisodesOverlay$lambda$0$0(boolean z, FullScreenPlayer fullScreenPlayer) {
        PlayerCustomLayoutBinding playerCustomLayoutBinding;
        LinearLayout linearLayout;
        if (z || (playerCustomLayoutBinding = fullScreenPlayer.playerBinding) == null || (linearLayout = playerCustomLayoutBinding.playerEpisodeOverlay) == null) {
            return;
        }
        linearLayout.setVisibility(8);
    }
}
