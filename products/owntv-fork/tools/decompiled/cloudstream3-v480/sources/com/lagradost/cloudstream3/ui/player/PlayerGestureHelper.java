package com.lagradost.cloudstream3.ui.player;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Insets;
import android.graphics.Matrix;
import android.media.AudioManager;
import android.media.audiofx.LoudnessEnhancer;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.media3.common.Player;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.preference.PreferenceManager;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.utils.DataStoreHelper;
import com.lagradost.cloudstream3.utils.UIHelper;
import com.lagradost.cloudstream3.utils.Vector2;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.mozilla.javascript.ES6Iterator;
import org.slf4j.Marker;

/* JADX INFO: compiled from: PlayerGestureHelper.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000e\n\u0002\b\r\b\u0007\u0018\u0000 Î\u00012\u00020\u0001:\u0004Î\u0001Ï\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u008f\u0001\u001a\u00030\u0090\u0001J\b\u0010\u0091\u0001\u001a\u00030\u0090\u0001J\b\u0010\u0092\u0001\u001a\u00030\u0090\u0001J\b\u0010\u0093\u0001\u001a\u00030\u0090\u0001J\u0011\u0010\u0094\u0001\u001a\u00030\u0090\u00012\u0007\u0010\u0095\u0001\u001a\u00020\u000bJ\b\u0010\u0096\u0001\u001a\u00030\u0090\u0001J\u0010\u0010\u0097\u0001\u001a\u00020\u000b2\u0007\u0010\u0098\u0001\u001a\u00020bJ\u001a\u0010\u0099\u0001\u001a\u00030\u0090\u00012\u0007\u0010\u009a\u0001\u001a\u00020\u00102\u0007\u0010\u009b\u0001\u001a\u00020\u000bJ\u000f\u0010\u009c\u0001\u001a\u0004\u0018\u00010\u0010¢\u0006\u0003\u0010\u009d\u0001J\u0011\u0010\u009e\u0001\u001a\u00030\u0090\u00012\u0007\u0010\u009f\u0001\u001a\u00020\u0010J\u0011\u0010 \u0001\u001a\u00030\u0090\u00012\u0007\u0010¡\u0001\u001a\u00020\u0010J\u0007\u0010¢\u0001\u001a\u00020xJ\u001a\u0010£\u0001\u001a\u00030\u0090\u00012\u0007\u0010¤\u0001\u001a\u00020x2\u0007\u0010¥\u0001\u001a\u00020\u000bJ\b\u0010¦\u0001\u001a\u00030\u0090\u0001J\b\u0010§\u0001\u001a\u00030\u0090\u0001J\u0013\u0010¨\u0001\u001a\u00030\u0090\u00012\u0007\u0010©\u0001\u001a\u00020\u0007H\u0002J<\u0010ª\u0001\u001a\u00020\u000b2\b\u0010«\u0001\u001a\u00030¬\u00012\u0007\u0010©\u0001\u001a\u00020\u00072\u000f\u0010\u00ad\u0001\u001a\n\u0012\u0005\u0012\u00030\u0090\u00010®\u00012\u000f\u0010¯\u0001\u001a\n\u0012\u0005\u0012\u00030\u0090\u00010®\u0001J\b\u0010°\u0001\u001a\u00030\u0090\u0001J\b\u0010±\u0001\u001a\u00030\u0090\u0001J\b\u0010²\u0001\u001a\u00030\u0090\u0001J\b\u0010³\u0001\u001a\u00030\u0090\u0001J\b\u0010´\u0001\u001a\u00030\u0090\u0001J\u0011\u0010µ\u0001\u001a\u00030\u0090\u00012\u0007\u0010¶\u0001\u001a\u00020\u0010J\b\u0010·\u0001\u001a\u00030\u0090\u0001J\b\u0010¸\u0001\u001a\u00030\u0090\u0001J2\u0010¹\u0001\u001a\u00020\u000b2\u0007\u0010º\u0001\u001a\u00020\u00102\u0007\u0010»\u0001\u001a\u00020b2\u0006\u0010I\u001a\u00020\u000b2\u000f\u0010¼\u0001\u001a\n\u0012\u0005\u0012\u00030\u0090\u00010®\u0001J2\u0010½\u0001\u001a\u0004\u0018\u00010Z2\t\u0010¾\u0001\u001a\u0004\u0018\u00010Z2\t\u0010¿\u0001\u001a\u0004\u0018\u00010N2\t\u0010À\u0001\u001a\u0004\u0018\u00010NH\u0002¢\u0006\u0003\u0010Á\u0001J\u001e\u0010Â\u0001\u001a\u00030Ã\u00012\u0007\u0010Ä\u0001\u001a\u00020Z2\t\b\u0002\u0010Å\u0001\u001a\u00020bH\u0002J\u0013\u0010Æ\u0001\u001a\u00030Ã\u00012\u0007\u0010Ç\u0001\u001a\u00020ZH\u0002J\b\u0010È\u0001\u001a\u00030\u0090\u0001J\u001b\u0010É\u0001\u001a\u00020\u000b2\u0007\u0010Ê\u0001\u001a\u00020\u00102\u0007\u0010Ë\u0001\u001a\u00020\u0010H\u0002J\u001c\u0010Ì\u0001\u001a\u00020\u000b2\u0007\u0010Í\u0001\u001a\u00020-2\b\u0010«\u0001\u001a\u00030¬\u0001H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00078BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\f\"\u0004\b\u0016\u0010\u000eR\u001a\u0010\u0017\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\f\"\u0004\b\u0019\u0010\u000eR\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001e\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0012\"\u0004\b \u0010\u0014R\u001a\u0010!\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0012\"\u0004\b#\u0010\u0014R\u001a\u0010$\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\f\"\u0004\b%\u0010\u000eR\u001a\u0010&\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\f\"\u0004\b(\u0010\u000eR\u001a\u0010)\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\f\"\u0004\b+\u0010\u000eR\u001c\u0010,\u001a\u0004\u0018\u00010-X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u0010\u00102\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u00103\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\f\"\u0004\b5\u0010\u000eR\u001a\u00106\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\f\"\u0004\b8\u0010\u000eR\u001a\u00109\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010\f\"\u0004\b;\u0010\u000eR\u001a\u0010<\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\f\"\u0004\b>\u0010\u000eR\u0011\u0010?\u001a\u00020@¢\u0006\b\n\u0000\u001a\u0004\bA\u0010BR\u001a\u0010C\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010\f\"\u0004\bE\u0010\u000eR\u0011\u0010F\u001a\u00020\u001d¢\u0006\b\n\u0000\u001a\u0004\bG\u0010HR\u001a\u0010I\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010\f\"\u0004\bJ\u0010\u000eR\u001e\u0010L\u001a\u00020\u000b2\u0006\u0010K\u001a\u00020\u000b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\bL\u0010\fR\u0010\u0010M\u001a\u0004\u0018\u00010NX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010O\u001a\u0004\u0018\u00010NX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010P\u001a\u0004\u0018\u00010QX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bR\u0010S\"\u0004\bT\u0010UR\u001c\u0010V\u001a\u0004\u0018\u00010QX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bW\u0010S\"\u0004\bX\u0010UR\u0012\u0010Y\u001a\u0004\u0018\u00010ZX\u0082\u000e¢\u0006\u0004\n\u0002\u0010[R\u0012\u0010\\\u001a\u0004\u0018\u00010ZX\u0082\u000e¢\u0006\u0004\n\u0002\u0010[R\u001a\u0010]\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b^\u0010\f\"\u0004\b_\u0010\u000eR\u0014\u0010`\u001a\b\u0012\u0004\u0012\u00020b0aX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010c\u001a\b\u0012\u0004\u0012\u00020b0aX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010d\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\be\u0010\f\"\u0004\bf\u0010\u000eR\u001a\u0010g\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bh\u0010\f\"\u0004\bi\u0010\u000eR\u001a\u0010j\u001a\u00020ZX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bk\u0010l\"\u0004\bm\u0010nR\u000e\u0010o\u001a\u00020bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010p\u001a\u00020bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010q\u001a\u00020ZX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\br\u0010l\"\u0004\bs\u0010nR\u001c\u0010t\u001a\u0004\u0018\u00010-X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bu\u0010/\"\u0004\bv\u00101R\u001c\u0010w\u001a\u0004\u0018\u00010xX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\by\u0010z\"\u0004\b{\u0010|R\u001c\u0010}\u001a\u0004\u0018\u00010xX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b~\u0010z\"\u0004\b\u007f\u0010|R\"\u0010\u0080\u0001\u001a\u0005\u0018\u00010\u0081\u0001X\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u0082\u0001\u0010\u0083\u0001\"\u0006\b\u0084\u0001\u0010\u0085\u0001R\u0012\u0010\u0086\u0001\u001a\u0005\u0018\u00010\u0087\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R!\u0010\u0088\u0001\u001a\u0004\u0018\u00010NX\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u0089\u0001\u0010\u008a\u0001\"\u0006\b\u008b\u0001\u0010\u008c\u0001R\u0012\u0010\u008d\u0001\u001a\u0005\u0018\u00010\u008e\u0001X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006Ð\u0001"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/PlayerGestureHelper;", "", "playerView", "Lcom/lagradost/cloudstream3/ui/player/PlayerView;", "<init>", "(Lcom/lagradost/cloudstream3/ui/player/PlayerView;)V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "isFullScreen", "", "()Z", "setFullScreen", "(Z)V", "currentRequestedVolume", "", "getCurrentRequestedVolume", "()F", "setCurrentRequestedVolume", "(F)V", "isVolumeLocked", "setVolumeLocked", "hasShownVolumeToast", "getHasShownVolumeToast", "setHasShownVolumeToast", "loudnessEnhancer", "Landroid/media/audiofx/LoudnessEnhancer;", "progressBarLeftHideRunnable", "Ljava/lang/Runnable;", "currentRequestedBrightness", "getCurrentRequestedBrightness", "setCurrentRequestedBrightness", "currentExtraBrightness", "getCurrentExtraBrightness", "setCurrentExtraBrightness", "isBrightnessLocked", "setBrightnessLocked", "hasShownBrightnessToast", "getHasShownBrightnessToast", "setHasShownBrightnessToast", "useTrueSystemBrightness", "getUseTrueSystemBrightness", "setUseTrueSystemBrightness", "brightnessOverlay", "Landroid/view/View;", "getBrightnessOverlay", "()Landroid/view/View;", "setBrightnessOverlay", "(Landroid/view/View;)V", "progressBarRightHideRunnable", "swipeVerticalEnabled", "getSwipeVerticalEnabled", "setSwipeVerticalEnabled", "swipeHorizontalEnabled", "getSwipeHorizontalEnabled", "setSwipeHorizontalEnabled", "extraBrightnessEnabled", "getExtraBrightnessEnabled", "setExtraBrightnessEnabled", "speedupEnabled", "getSpeedupEnabled", "setSpeedupEnabled", "holdHandler", "Landroid/os/Handler;", "getHoldHandler", "()Landroid/os/Handler;", "hasTriggeredSpeedUp", "getHasTriggeredSpeedUp", "setHasTriggeredSpeedUp", "holdRunnable", "getHoldRunnable", "()Ljava/lang/Runnable;", "isLocked", "setLocked", ES6Iterator.VALUE_PROPERTY, "isCurrentTouchValid", "currentTouchStart", "Lcom/lagradost/cloudstream3/utils/Vector2;", "currentTouchLast", "currentTouchAction", "Lcom/lagradost/cloudstream3/ui/player/PlayerGestureHelper$TouchAction;", "getCurrentTouchAction", "()Lcom/lagradost/cloudstream3/ui/player/PlayerGestureHelper$TouchAction;", "setCurrentTouchAction", "(Lcom/lagradost/cloudstream3/ui/player/PlayerGestureHelper$TouchAction;)V", "currentLastTouchAction", "getCurrentLastTouchAction", "setCurrentLastTouchAction", "currentTouchStartPlayerTime", "", "Ljava/lang/Long;", "currentTouchStartTime", "uiShowingBeforeGesture", "getUiShowingBeforeGesture", "setUiShowingBeforeGesture", "brightnessIcons", "", "", "volumeIcons", "doubleTapEnabled", "getDoubleTapEnabled", "setDoubleTapEnabled", "doubleTapPauseEnabled", "getDoubleTapPauseEnabled", "setDoubleTapPauseEnabled", "fastForwardTime", "getFastForwardTime", "()J", "setFastForwardTime", "(J)V", "doubleTapToken", "tapCount", "lastTouchEndTime", "getLastTouchEndTime", "setLastTouchEndTime", "videoOutline", "getVideoOutline", "setVideoOutline", "zoomMatrix", "Landroid/graphics/Matrix;", "getZoomMatrix", "()Landroid/graphics/Matrix;", "setZoomMatrix", "(Landroid/graphics/Matrix;)V", "desiredMatrix", "getDesiredMatrix", "setDesiredMatrix", "matrixAnimation", "Landroid/animation/ValueAnimator;", "getMatrixAnimation", "()Landroid/animation/ValueAnimator;", "setMatrixAnimation", "(Landroid/animation/ValueAnimator;)V", "scaleGestureDetector", "Landroid/view/ScaleGestureDetector;", "lastPan", "getLastPan", "()Lcom/lagradost/cloudstream3/utils/Vector2;", "setLastPan", "(Lcom/lagradost/cloudstream3/utils/Vector2;)V", "overlayLayoutListener", "Landroid/view/View$OnLayoutChangeListener;", "initialize", "", "release", "setupKeyEventListener", "releaseKeyEventListener", "showOrHideSpeedUp", "show", "verifyVolume", "handleVolumeKey", "keyCode", "handleVolumeAdjustment", "delta", "fromButton", "getBrightness", "()Ljava/lang/Float;", "setBrightness", "brightness", "handleBrightnessAdjustment", "verticalAddition", "currentZoomMatrix", "applyZoomMatrix", "newMatrix", "animation", "clearZoomState", "resetZoomToDefault", "createScaleGestureDetector", "ctx", "handleZoomPanGesture", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "onFirstPointerDown", "Lkotlin/Function0;", "onGestureEnd", "updateBrightnessOverlayBounds", "requestUpdateBrightnessOverlayOnNextLayout", "releaseOverlayLayoutListener", "resetRewindText", "resetFastForwardText", "animateCenterControls", "fadeTo", "rewind", "fastForward", "onTapDetected", "x", "viewWidth", "onSingleTap", "calculateNewTime", "startTime", "touchStart", "touchEnd", "(Ljava/lang/Long;Lcom/lagradost/cloudstream3/utils/Vector2;Lcom/lagradost/cloudstream3/utils/Vector2;)Ljava/lang/Long;", "forceLetters", "", "inp", "letters", "convertTimeToString", "sec", "setupTouchGestures", "isValidTouch", "rawX", "rawY", "handleGesture", "view", "Companion", "TouchAction", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class PlayerGestureHelper {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final long DOUBLE_TAP_MAXIMUM_HOLD_TIME = 200;
    public static final long DOUBLE_TAP_MINIMUM_TIME_BETWEEN = 200;
    public static final double DOUBLE_TAP_PAUSE_PERCENTAGE = 0.15d;
    public static final float HORIZONTAL_MULTIPLIER = 2.0f;
    public static final float MAXIMUM_ZOOM = 4.0f;
    public static final float MINIMUM_HORIZONTAL_SWIPE = 2.0f;
    public static final long MINIMUM_SEEK_TIME = 7000;
    public static final float MINIMUM_VERTICAL_SWIPE = 2.0f;
    public static final float MINIMUM_ZOOM = 0.95f;
    public static final float VERTICAL_MULTIPLIER = 2.0f;
    public static final float ZOOM_SNAP_SENSITIVITY = 0.07f;
    private final List<Integer> brightnessIcons;
    private View brightnessOverlay;
    private float currentExtraBrightness;
    private TouchAction currentLastTouchAction;
    private float currentRequestedBrightness;
    private float currentRequestedVolume;
    private TouchAction currentTouchAction;
    private Vector2 currentTouchLast;
    private Vector2 currentTouchStart;
    private Long currentTouchStartPlayerTime;
    private Long currentTouchStartTime;
    private Matrix desiredMatrix;
    private boolean doubleTapEnabled;
    private boolean doubleTapPauseEnabled;
    private int doubleTapToken;
    private boolean extraBrightnessEnabled;
    private long fastForwardTime;
    private boolean hasShownBrightnessToast;
    private boolean hasShownVolumeToast;
    private boolean hasTriggeredSpeedUp;
    private final Handler holdHandler;
    private final Runnable holdRunnable;
    private boolean isBrightnessLocked;
    private boolean isCurrentTouchValid;
    private boolean isFullScreen;
    private boolean isLocked;
    private boolean isVolumeLocked;
    private Vector2 lastPan;
    private long lastTouchEndTime;
    private LoudnessEnhancer loudnessEnhancer;
    private ValueAnimator matrixAnimation;
    private View.OnLayoutChangeListener overlayLayoutListener;
    private final PlayerView playerView;
    private Runnable progressBarLeftHideRunnable;
    private Runnable progressBarRightHideRunnable;
    private ScaleGestureDetector scaleGestureDetector;
    private boolean speedupEnabled;
    private boolean swipeHorizontalEnabled;
    private boolean swipeVerticalEnabled;
    private int tapCount;
    private boolean uiShowingBeforeGesture;
    private boolean useTrueSystemBrightness;
    private View videoOutline;
    private final List<Integer> volumeIcons;
    private Matrix zoomMatrix;

    /* JADX INFO: compiled from: PlayerGestureHelper.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/PlayerGestureHelper$TouchAction;", "", "<init>", "(Ljava/lang/String;I)V", "Brightness", "Volume", "Time", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public enum TouchAction {
        Brightness,
        Volume,
        Time;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<TouchAction> getEntries() {
            return $ENTRIES;
        }
    }

    /* JADX INFO: compiled from: PlayerGestureHelper.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TouchAction.values().length];
            try {
                iArr[TouchAction.Time.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[TouchAction.Brightness.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[TouchAction.Volume.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public PlayerGestureHelper(PlayerView playerView) {
        Intrinsics.checkNotNullParameter(playerView, "playerView");
        this.playerView = playerView;
        this.currentRequestedBrightness = 1.0f;
        this.useTrueSystemBrightness = true;
        this.swipeVerticalEnabled = true;
        this.holdHandler = new Handler(Looper.getMainLooper());
        this.holdRunnable = new Runnable() { // from class: com.lagradost.cloudstream3.ui.player.PlayerGestureHelper$$ExternalSyntheticLambda15
            @Override // java.lang.Runnable
            public final void run() {
                PlayerGestureHelper.holdRunnable$lambda$0(this.f$0);
            }
        };
        this.brightnessIcons = CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(R.drawable.sun_1), Integer.valueOf(R.drawable.sun_2), Integer.valueOf(R.drawable.sun_3), Integer.valueOf(R.drawable.sun_4), Integer.valueOf(R.drawable.sun_5), Integer.valueOf(R.drawable.sun_6), Integer.valueOf(R.drawable.sun_7)});
        this.volumeIcons = CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(R.drawable.ic_baseline_volume_mute_24), Integer.valueOf(R.drawable.ic_baseline_volume_down_24), Integer.valueOf(R.drawable.ic_baseline_volume_up_24)});
        this.fastForwardTime = 10000L;
    }

    /* JADX INFO: compiled from: PlayerGestureHelper.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0012\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00132\u0006\u0010\u0014\u001a\u00020\u0015R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/PlayerGestureHelper$Companion;", "", "<init>", "()V", "MINIMUM_SEEK_TIME", "", "MINIMUM_VERTICAL_SWIPE", "", "MINIMUM_HORIZONTAL_SWIPE", "VERTICAL_MULTIPLIER", "HORIZONTAL_MULTIPLIER", "DOUBLE_TAP_MAXIMUM_HOLD_TIME", "DOUBLE_TAP_MINIMUM_TIME_BETWEEN", "DOUBLE_TAP_PAUSE_PERCENTAGE", "", "MINIMUM_ZOOM", "ZOOM_SNAP_SENSITIVITY", "MAXIMUM_ZOOM", "matrixToTranslationAndScale", "Lkotlin/Triple;", "matrix", "Landroid/graphics/Matrix;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Triple<Float, Float, Float> matrixToTranslationAndScale(Matrix matrix) {
            Intrinsics.checkNotNullParameter(matrix, "matrix");
            float[] fArr = {0.0f, 0.0f, 1.0f, 1.0f};
            matrix.mapPoints(fArr);
            float f = fArr[0];
            return new Triple<>(Float.valueOf(f), Float.valueOf(fArr[1]), Float.valueOf(fArr[2] - f));
        }
    }

    private final Context getContext() {
        Context context = this.playerView.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        return context;
    }

    /* JADX INFO: renamed from: isFullScreen, reason: from getter */
    public final boolean getIsFullScreen() {
        return this.isFullScreen;
    }

    public final void setFullScreen(boolean z) {
        this.isFullScreen = z;
    }

    public final float getCurrentRequestedVolume() {
        return this.currentRequestedVolume;
    }

    public final void setCurrentRequestedVolume(float f) {
        this.currentRequestedVolume = f;
    }

    /* JADX INFO: renamed from: isVolumeLocked, reason: from getter */
    public final boolean getIsVolumeLocked() {
        return this.isVolumeLocked;
    }

    public final void setVolumeLocked(boolean z) {
        this.isVolumeLocked = z;
    }

    public final boolean getHasShownVolumeToast() {
        return this.hasShownVolumeToast;
    }

    public final void setHasShownVolumeToast(boolean z) {
        this.hasShownVolumeToast = z;
    }

    public final float getCurrentRequestedBrightness() {
        return this.currentRequestedBrightness;
    }

    public final void setCurrentRequestedBrightness(float f) {
        this.currentRequestedBrightness = f;
    }

    public final float getCurrentExtraBrightness() {
        return this.currentExtraBrightness;
    }

    public final void setCurrentExtraBrightness(float f) {
        this.currentExtraBrightness = f;
    }

    /* JADX INFO: renamed from: isBrightnessLocked, reason: from getter */
    public final boolean getIsBrightnessLocked() {
        return this.isBrightnessLocked;
    }

    public final void setBrightnessLocked(boolean z) {
        this.isBrightnessLocked = z;
    }

    public final boolean getHasShownBrightnessToast() {
        return this.hasShownBrightnessToast;
    }

    public final void setHasShownBrightnessToast(boolean z) {
        this.hasShownBrightnessToast = z;
    }

    public final boolean getUseTrueSystemBrightness() {
        return this.useTrueSystemBrightness;
    }

    public final void setUseTrueSystemBrightness(boolean z) {
        this.useTrueSystemBrightness = z;
    }

    public final View getBrightnessOverlay() {
        return this.brightnessOverlay;
    }

    public final void setBrightnessOverlay(View view) {
        this.brightnessOverlay = view;
    }

    public final boolean getSwipeVerticalEnabled() {
        return this.swipeVerticalEnabled;
    }

    public final void setSwipeVerticalEnabled(boolean z) {
        this.swipeVerticalEnabled = z;
    }

    public final boolean getSwipeHorizontalEnabled() {
        return this.swipeHorizontalEnabled;
    }

    public final void setSwipeHorizontalEnabled(boolean z) {
        this.swipeHorizontalEnabled = z;
    }

    public final boolean getExtraBrightnessEnabled() {
        return this.extraBrightnessEnabled;
    }

    public final void setExtraBrightnessEnabled(boolean z) {
        this.extraBrightnessEnabled = z;
    }

    public final boolean getSpeedupEnabled() {
        return this.speedupEnabled;
    }

    public final void setSpeedupEnabled(boolean z) {
        this.speedupEnabled = z;
    }

    public final Handler getHoldHandler() {
        return this.holdHandler;
    }

    public final boolean getHasTriggeredSpeedUp() {
        return this.hasTriggeredSpeedUp;
    }

    public final void setHasTriggeredSpeedUp(boolean z) {
        this.hasTriggeredSpeedUp = z;
    }

    public final Runnable getHoldRunnable() {
        return this.holdRunnable;
    }

    static final void holdRunnable$lambda$0(PlayerGestureHelper playerGestureHelper) {
        playerGestureHelper.playerView.getPlayer().setPlaybackSpeed(2.0f);
        playerGestureHelper.showOrHideSpeedUp(true);
        PlayerView.Callbacks callbacks = playerGestureHelper.playerView.getCallbacks();
        if (callbacks != null) {
            callbacks.onHoldSpeedUp(true);
        }
        playerGestureHelper.hasTriggeredSpeedUp = true;
    }

    /* JADX INFO: renamed from: isLocked, reason: from getter */
    public final boolean getIsLocked() {
        return this.isLocked;
    }

    public final void setLocked(boolean z) {
        this.isLocked = z;
    }

    /* JADX INFO: renamed from: isCurrentTouchValid, reason: from getter */
    public final boolean getIsCurrentTouchValid() {
        return this.isCurrentTouchValid;
    }

    public final TouchAction getCurrentTouchAction() {
        return this.currentTouchAction;
    }

    public final void setCurrentTouchAction(TouchAction touchAction) {
        this.currentTouchAction = touchAction;
    }

    public final TouchAction getCurrentLastTouchAction() {
        return this.currentLastTouchAction;
    }

    public final void setCurrentLastTouchAction(TouchAction touchAction) {
        this.currentLastTouchAction = touchAction;
    }

    public final boolean getUiShowingBeforeGesture() {
        return this.uiShowingBeforeGesture;
    }

    public final void setUiShowingBeforeGesture(boolean z) {
        this.uiShowingBeforeGesture = z;
    }

    public final boolean getDoubleTapEnabled() {
        return this.doubleTapEnabled;
    }

    public final void setDoubleTapEnabled(boolean z) {
        this.doubleTapEnabled = z;
    }

    public final boolean getDoubleTapPauseEnabled() {
        return this.doubleTapPauseEnabled;
    }

    public final void setDoubleTapPauseEnabled(boolean z) {
        this.doubleTapPauseEnabled = z;
    }

    public final long getFastForwardTime() {
        return this.fastForwardTime;
    }

    public final void setFastForwardTime(long j) {
        this.fastForwardTime = j;
    }

    public final long getLastTouchEndTime() {
        return this.lastTouchEndTime;
    }

    public final void setLastTouchEndTime(long j) {
        this.lastTouchEndTime = j;
    }

    public final View getVideoOutline() {
        return this.videoOutline;
    }

    public final void setVideoOutline(View view) {
        this.videoOutline = view;
    }

    public final Matrix getZoomMatrix() {
        return this.zoomMatrix;
    }

    public final void setZoomMatrix(Matrix matrix) {
        this.zoomMatrix = matrix;
    }

    public final Matrix getDesiredMatrix() {
        return this.desiredMatrix;
    }

    public final void setDesiredMatrix(Matrix matrix) {
        this.desiredMatrix = matrix;
    }

    public final ValueAnimator getMatrixAnimation() {
        return this.matrixAnimation;
    }

    public final void setMatrixAnimation(ValueAnimator valueAnimator) {
        this.matrixAnimation = valueAnimator;
    }

    public final Vector2 getLastPan() {
        return this.lastPan;
    }

    public final void setLastPan(Vector2 vector2) {
        this.lastPan = vector2;
    }

    public final void initialize() {
        try {
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            this.swipeVerticalEnabled = defaultSharedPreferences.getBoolean(getContext().getString(R.string.swipe_vertical_enabled_key), true);
            this.swipeHorizontalEnabled = defaultSharedPreferences.getBoolean(getContext().getString(R.string.swipe_enabled_key), true);
            this.extraBrightnessEnabled = defaultSharedPreferences.getBoolean(getContext().getString(R.string.extra_brightness_key), false);
            this.speedupEnabled = defaultSharedPreferences.getBoolean(getContext().getString(R.string.speedup_key), false);
            this.doubleTapEnabled = defaultSharedPreferences.getBoolean(getContext().getString(R.string.double_tap_enabled_key), false);
            this.doubleTapPauseEnabled = defaultSharedPreferences.getBoolean(getContext().getString(R.string.double_tap_pause_enabled_key), false);
            this.fastForwardTime = ((long) defaultSharedPreferences.getInt(getContext().getString(R.string.double_tap_seek_time_key), 10)) * 1000;
        } catch (Exception unused) {
        }
        ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.ui.player.PlayerGestureHelper$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PlayerGestureHelper.initialize$lambda$0(this.f$0);
            }
        });
        setupTouchGestures();
    }

    static final Unit initialize$lambda$0(PlayerGestureHelper playerGestureHelper) {
        int identifier = playerGestureHelper.getContext().getResources().getIdentifier("exo_content_frame", "id", playerGestureHelper.getContext().getPackageName());
        androidx.media3.ui.PlayerView exoPlayerView = playerGestureHelper.playerView.getExoPlayerView();
        ViewGroup viewGroup = exoPlayerView != null ? (ViewGroup) exoPlayerView.findViewById(identifier) : null;
        if (viewGroup != null) {
            View view = playerGestureHelper.brightnessOverlay;
            if (view != null) {
                ViewParent parent = view.getParent();
                ViewGroup viewGroup2 = parent instanceof ViewGroup ? (ViewGroup) parent : null;
                if (viewGroup2 != null) {
                    viewGroup2.removeView(view);
                }
            }
            View viewInflate = LayoutInflater.from(playerGestureHelper.getContext()).inflate(R.layout.extra_brightness_overlay, viewGroup, false);
            playerGestureHelper.brightnessOverlay = viewInflate;
            viewGroup.addView(viewInflate);
        }
        return Unit.INSTANCE;
    }

    public final void release() {
        ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.ui.player.PlayerGestureHelper$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PlayerGestureHelper.release$lambda$0(this.f$0);
            }
        });
        this.brightnessOverlay = null;
        LoudnessEnhancer loudnessEnhancer = this.loudnessEnhancer;
        if (loudnessEnhancer != null) {
            loudnessEnhancer.release();
        }
        this.loudnessEnhancer = null;
        this.holdHandler.removeCallbacksAndMessages(null);
        clearZoomState();
        releaseOverlayLayoutListener();
    }

    static final Unit release$lambda$0(PlayerGestureHelper playerGestureHelper) {
        View view = playerGestureHelper.brightnessOverlay;
        if (view != null) {
            ViewParent parent = view.getParent();
            ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
            if (viewGroup != null) {
                viewGroup.removeView(view);
                return Unit.INSTANCE;
            }
        }
        return null;
    }

    public final void setupKeyEventListener() {
        CommonActivity.INSTANCE.setKeyEventListener(new Function1() { // from class: com.lagradost.cloudstream3.ui.player.PlayerGestureHelper$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(PlayerGestureHelper.setupKeyEventListener$lambda$0(this.f$0, (Pair) obj));
            }
        });
    }

    static final boolean setupKeyEventListener$lambda$0(PlayerGestureHelper playerGestureHelper, Pair pair) {
        Intrinsics.checkNotNullParameter(pair, "<destruct>");
        KeyEvent keyEvent = (KeyEvent) pair.component1();
        if (keyEvent == null || keyEvent.getAction() != 0) {
            return false;
        }
        return playerGestureHelper.handleVolumeKey(keyEvent.getKeyCode());
    }

    public final void releaseKeyEventListener() {
        CommonActivity.INSTANCE.setKeyEventListener(null);
    }

    public final void showOrHideSpeedUp(final boolean show) {
        final View playerSpeedupButton$app = this.playerView.getPlayerSpeedupButton();
        if (playerSpeedupButton$app != null) {
            playerSpeedupButton$app.clearAnimation();
            playerSpeedupButton$app.setAlpha(show ? 0.0f : 1.0f);
            playerSpeedupButton$app.setVisibility(show ? 0 : 8);
            playerSpeedupButton$app.animate().alpha(show ? 1.0f : 0.0f).setDuration(200L).withEndAction(new Runnable() { // from class: com.lagradost.cloudstream3.ui.player.PlayerGestureHelper$$ExternalSyntheticLambda18
                @Override // java.lang.Runnable
                public final void run() {
                    PlayerGestureHelper.showOrHideSpeedUp$lambda$0$0(show, playerSpeedupButton$app);
                }
            }).start();
        }
    }

    public final void verifyVolume() {
        Context context = getContext();
        Activity activity = context instanceof Activity ? (Activity) context : null;
        Object systemService = activity != null ? activity.getSystemService("audio") : null;
        AudioManager audioManager = systemService instanceof AudioManager ? (AudioManager) systemService : null;
        if (audioManager != null) {
            int streamVolume = audioManager.getStreamVolume(3);
            int streamMaxVolume = audioManager.getStreamMaxVolume(3);
            if (streamVolume < streamMaxVolume || this.currentRequestedVolume <= 1.0f) {
                this.currentRequestedVolume = streamVolume / streamMaxVolume;
                LoudnessEnhancer loudnessEnhancer = this.loudnessEnhancer;
                if (loudnessEnhancer != null) {
                    loudnessEnhancer.release();
                }
                this.loudnessEnhancer = null;
            }
        }
    }

    public final boolean handleVolumeKey(int keyCode) {
        if (!Globals.INSTANCE.isLayout(5) || !this.isFullScreen) {
            return false;
        }
        if (keyCode != 24 && keyCode != 25) {
            return false;
        }
        verifyVolume();
        float f = this.currentRequestedVolume;
        if (f <= 1.0f) {
            this.hasShownVolumeToast = false;
        }
        this.isVolumeLocked = f < 1.0f;
        handleVolumeAdjustment(keyCode == 24 ? 0.05f : -0.05f, true);
        return true;
    }

    public final void handleVolumeAdjustment(float delta, boolean fromButton) {
        double d;
        boolean z;
        ProgressBar playerProgressbarLeftLevel1;
        ProgressBar playerProgressbarLeftLevel2;
        ImageView playerProgressbarLeftIcon;
        Context context = getContext();
        Activity activity = context instanceof Activity ? (Activity) context : null;
        Object systemService = activity != null ? activity.getSystemService("audio") : null;
        AudioManager audioManager = systemService instanceof AudioManager ? (AudioManager) systemService : null;
        if (audioManager == null) {
            return;
        }
        int streamVolume = audioManager.getStreamVolume(3);
        int streamMaxVolume = audioManager.getStreamMaxVolume(3);
        float f = this.currentRequestedVolume;
        boolean z2 = this.isVolumeLocked;
        float f2 = f + delta;
        float fCoerceIn = RangesKt.coerceIn(f2, 0.0f, z2 ? 1.0f : 2.0f);
        int iCoerceIn = RangesKt.coerceIn(MathKt.roundToInt(streamMaxVolume * fCoerceIn), 0, streamMaxVolume);
        if (fromButton) {
            if (f <= 1.0f && fCoerceIn > 1.0f && !this.hasShownVolumeToast) {
                CommonActivity.showToast$default(CommonActivity.INSTANCE, R.string.volume_exceeded_100, (Integer) null, 2, (Object) null);
                this.hasShownVolumeToast = true;
            }
            d = 1.0d;
        } else {
            d = 1.0d;
            if (f2 > 1.0d && z2 && !this.hasShownVolumeToast) {
                CommonActivity.showToast$default(CommonActivity.INSTANCE, R.string.slide_up_again_to_exceed_100, (Integer) null, 2, (Object) null);
                this.hasShownVolumeToast = true;
            }
        }
        if (iCoerceIn != streamVolume) {
            audioManager.setStreamVolume(3, iCoerceIn, 0);
        }
        LoudnessEnhancer loudnessEnhancer = this.loudnessEnhancer;
        if (fCoerceIn > 1.0f) {
            int i = (int) ((fCoerceIn - 1.0f) * 1000.0f);
            if (loudnessEnhancer != null) {
                loudnessEnhancer.setTargetGain(i);
            } else {
                androidx.media3.ui.PlayerView exoPlayerView = this.playerView.getExoPlayerView();
                Player player = exoPlayerView != null ? exoPlayerView.getPlayer() : null;
                ExoPlayer exoPlayer = player instanceof ExoPlayer ? (ExoPlayer) player : null;
                Integer numValueOf = exoPlayer != null ? Integer.valueOf(exoPlayer.getAudioSessionId()) : null;
                if (numValueOf != null && numValueOf.intValue() != -1) {
                    try {
                        LoudnessEnhancer loudnessEnhancer2 = new LoudnessEnhancer(numValueOf.intValue());
                        loudnessEnhancer2.setTargetGain(i);
                        loudnessEnhancer2.setEnabled(true);
                        this.loudnessEnhancer = loudnessEnhancer2;
                    } catch (Throwable th) {
                        ArchComponentExtKt.logError(th);
                        z = true;
                    }
                }
            }
        } else {
            if (loudnessEnhancer != null) {
                loudnessEnhancer.release();
            }
            this.loudnessEnhancer = null;
        }
        z = false;
        this.currentRequestedVolume = fCoerceIn;
        final RelativeLayout playerProgressbarLeftHolder = this.playerView.getPlayerProgressbarLeftHolder();
        if (playerProgressbarLeftHolder == null || (playerProgressbarLeftLevel1 = this.playerView.getPlayerProgressbarLeftLevel1()) == null || (playerProgressbarLeftLevel2 = this.playerView.getPlayerProgressbarLeftLevel2()) == null || (playerProgressbarLeftIcon = this.playerView.getPlayerProgressbarLeftIcon()) == null) {
            return;
        }
        if (fCoerceIn > 1.0f) {
            playerProgressbarLeftLevel2.setProgressTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(), z ? R.color.colorPrimaryRed : R.color.colorPrimaryOrange)));
        }
        playerProgressbarLeftLevel1.setMax(100000);
        playerProgressbarLeftLevel1.setProgress(RangesKt.coerceIn((int) (100000.0f * fCoerceIn), 2000, 100000));
        playerProgressbarLeftLevel2.setMax(100000);
        playerProgressbarLeftLevel2.setProgress(fCoerceIn > 1.0f ? RangesKt.coerceIn((int) ((((double) fCoerceIn) - d) * 100000.0d), 2000, 100000) : 0);
        playerProgressbarLeftLevel2.setVisibility(fCoerceIn > 1.0f ? 0 : 8);
        playerProgressbarLeftIcon.setImageResource(this.volumeIcons.get(RangesKt.coerceIn(MathKt.roundToInt(fCoerceIn * CollectionsKt.getLastIndex(this.volumeIcons)), 0, CollectionsKt.getLastIndex(this.volumeIcons))).intValue());
        RelativeLayout relativeLayout = playerProgressbarLeftHolder;
        if (relativeLayout.getVisibility() != 0 || playerProgressbarLeftHolder.getAlpha() < 1.0f) {
            playerProgressbarLeftHolder.animate().cancel();
            playerProgressbarLeftHolder.setAlpha(1.0f);
            relativeLayout.setVisibility(0);
        }
        Runnable runnable = this.progressBarLeftHideRunnable;
        if (runnable != null) {
            playerProgressbarLeftHolder.removeCallbacks(runnable);
        }
        Runnable runnable2 = new Runnable() { // from class: com.lagradost.cloudstream3.ui.player.PlayerGestureHelper$$ExternalSyntheticLambda16
            @Override // java.lang.Runnable
            public final void run() {
                PlayerGestureHelper.handleVolumeAdjustment$lambda$2(playerProgressbarLeftHolder);
            }
        };
        this.progressBarLeftHideRunnable = runnable2;
        playerProgressbarLeftHolder.postDelayed(runnable2, 1500L);
    }

    static final void handleVolumeAdjustment$lambda$2(final RelativeLayout relativeLayout) {
        relativeLayout.animate().cancel();
        relativeLayout.animate().alpha(0.0f).setDuration(300L).withEndAction(new Runnable() { // from class: com.lagradost.cloudstream3.ui.player.PlayerGestureHelper$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                PlayerGestureHelper.handleVolumeAdjustment$lambda$2$0(relativeLayout);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void handleVolumeAdjustment$lambda$2$0(RelativeLayout relativeLayout) {
        relativeLayout.setVisibility(8);
    }

    public final Float getBrightness() {
        Window window;
        WindowManager.LayoutParams attributes;
        f = null;
        f = null;
        f = null;
        f = null;
        Float f = null;
        if (this.useTrueSystemBrightness) {
            try {
                Context context = getContext();
                Activity activity = context instanceof Activity ? (Activity) context : null;
                return Float.valueOf(Settings.System.getInt(activity != null ? activity.getContentResolver() : null, "screen_brightness") / 255.0f);
            } catch (Exception unused) {
                this.useTrueSystemBrightness = false;
                return getBrightness();
            }
        }
        try {
            Context context2 = getContext();
            Activity activity2 = context2 instanceof Activity ? (Activity) context2 : null;
            if (activity2 != null && (window = activity2.getWindow()) != null && (attributes = window.getAttributes()) != null) {
                Float fValueOf = Float.valueOf(attributes.screenBrightness);
                if (fValueOf.floatValue() >= 0.0f) {
                    f = fValueOf;
                }
            }
        } catch (Exception e) {
            ArchComponentExtKt.logError(e);
        }
        return f;
    }

    public final void setBrightness(float brightness) {
        Window window;
        WindowManager.LayoutParams attributes;
        Window window2;
        if (this.useTrueSystemBrightness) {
            try {
                Context context = getContext();
                Activity activity = context instanceof Activity ? (Activity) context : null;
                Settings.System.putInt(activity != null ? activity.getContentResolver() : null, "screen_brightness_mode", 0);
                Context context2 = getContext();
                Activity activity2 = context2 instanceof Activity ? (Activity) context2 : null;
                Boolean.valueOf(Settings.System.putInt(activity2 != null ? activity2.getContentResolver() : null, "screen_brightness", Math.min(1, (int) (RangesKt.coerceIn(brightness, 0.0f, 1.0f) * 255.0f))));
                return;
            } catch (Exception unused) {
                this.useTrueSystemBrightness = false;
                setBrightness(brightness);
                Unit unit = Unit.INSTANCE;
                return;
            }
        }
        try {
            Context context3 = getContext();
            Activity activity3 = context3 instanceof Activity ? (Activity) context3 : null;
            if (activity3 != null && (window = activity3.getWindow()) != null && (attributes = window.getAttributes()) != null) {
                attributes.screenBrightness = RangesKt.coerceIn(brightness, 0.004f, 1.0f);
                Context context4 = getContext();
                Activity activity4 = context4 instanceof Activity ? (Activity) context4 : null;
                if (activity4 == null || (window2 = activity4.getWindow()) == null) {
                    return;
                }
                window2.setAttributes(attributes);
            }
        } catch (Exception e) {
            ArchComponentExtKt.logError(e);
        }
    }

    public final void handleBrightnessAdjustment(float verticalAddition) {
        ProgressBar playerProgressbarRightLevel1;
        ProgressBar playerProgressbarRightLevel2;
        ImageView playerProgressbarRightIcon;
        float f = this.currentRequestedBrightness;
        float f2 = verticalAddition + f;
        float fMin = 0.0f;
        float fCoerceIn = RangesKt.coerceIn(f2, 0.0f, (!this.extraBrightnessEnabled || this.isBrightnessLocked) ? 1.0f : 2.0f);
        if (this.extraBrightnessEnabled && this.isBrightnessLocked && f2 > 1.0f && !this.hasShownBrightnessToast) {
            CommonActivity.showToast$default(CommonActivity.INSTANCE, R.string.slide_up_again_to_exceed_100, (Integer) null, 2, (Object) null);
            this.hasShownBrightnessToast = true;
        }
        this.currentRequestedBrightness = fCoerceIn;
        if (f != fCoerceIn) {
            setBrightness(fCoerceIn);
        }
        if (this.extraBrightnessEnabled && fCoerceIn > 1.0f) {
            fMin = Math.min(2.0f, fCoerceIn) - 1.0f;
        }
        this.currentExtraBrightness = fMin;
        View view = this.brightnessOverlay;
        if (view != null) {
            view.setAlpha(fMin);
        }
        PlayerView.Callbacks callbacks = this.playerView.getCallbacks();
        if (callbacks != null) {
            callbacks.onBrightnessExtra(this.currentExtraBrightness);
        }
        final RelativeLayout playerProgressbarRightHolder = this.playerView.getPlayerProgressbarRightHolder();
        if (playerProgressbarRightHolder == null || (playerProgressbarRightLevel1 = this.playerView.getPlayerProgressbarRightLevel1()) == null || (playerProgressbarRightLevel2 = this.playerView.getPlayerProgressbarRightLevel2()) == null || (playerProgressbarRightIcon = this.playerView.getPlayerProgressbarRightIcon()) == null) {
            return;
        }
        playerProgressbarRightLevel1.setMax(100000);
        playerProgressbarRightLevel1.setProgress(Math.max(2000, (int) (Math.min(1.0f, fCoerceIn) * 100000.0f)));
        if (this.extraBrightnessEnabled) {
            playerProgressbarRightLevel2.setMax(100000);
            playerProgressbarRightLevel2.setProgress(RangesKt.coerceIn((int) (this.currentExtraBrightness * 100000.0f), 2000, 100000));
            playerProgressbarRightLevel2.setVisibility(fCoerceIn > 1.0f ? 0 : 8);
        }
        List<Integer> list = this.brightnessIcons;
        playerProgressbarRightIcon.setImageResource(list.get(Math.min(CollectionsKt.getLastIndex(list), Math.max(0, (int) Math.rint(fCoerceIn * CollectionsKt.getLastIndex(this.brightnessIcons))))).intValue());
        RelativeLayout relativeLayout = playerProgressbarRightHolder;
        if (relativeLayout.getVisibility() != 0 || playerProgressbarRightHolder.getAlpha() < 1.0f) {
            playerProgressbarRightHolder.animate().cancel();
            playerProgressbarRightHolder.setAlpha(1.0f);
            relativeLayout.setVisibility(0);
        }
        Runnable runnable = this.progressBarRightHideRunnable;
        if (runnable != null) {
            playerProgressbarRightHolder.removeCallbacks(runnable);
        }
        Runnable runnable2 = new Runnable() { // from class: com.lagradost.cloudstream3.ui.player.PlayerGestureHelper$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() {
                PlayerGestureHelper.handleBrightnessAdjustment$lambda$1(playerProgressbarRightHolder);
            }
        };
        this.progressBarRightHideRunnable = runnable2;
        playerProgressbarRightHolder.postDelayed(runnable2, 1500L);
    }

    static final void handleBrightnessAdjustment$lambda$1(final RelativeLayout relativeLayout) {
        relativeLayout.animate().cancel();
        relativeLayout.animate().alpha(0.0f).setDuration(300L).withEndAction(new Runnable() { // from class: com.lagradost.cloudstream3.ui.player.PlayerGestureHelper$$ExternalSyntheticLambda14
            @Override // java.lang.Runnable
            public final void run() {
                PlayerGestureHelper.handleBrightnessAdjustment$lambda$1$0(relativeLayout);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void handleBrightnessAdjustment$lambda$1$0(RelativeLayout relativeLayout) {
        relativeLayout.setVisibility(8);
    }

    public final Matrix currentZoomMatrix() {
        Matrix matrix = this.zoomMatrix;
        if (matrix != null) {
            return matrix;
        }
        androidx.media3.ui.PlayerView exoPlayerView = this.playerView.getExoPlayerView();
        View videoSurfaceView = exoPlayerView != null ? exoPlayerView.getVideoSurfaceView() : null;
        if (exoPlayerView == null || videoSurfaceView == null || exoPlayerView.getResizeMode() != 4) {
            return new Matrix();
        }
        float width = videoSurfaceView.getWidth();
        float height = videoSurfaceView.getHeight();
        float screenWidthWithOrientation = CommonActivity.INSTANCE.getScreenWidthWithOrientation();
        float screenHeightWithOrientation = CommonActivity.INSTANCE.getScreenHeightWithOrientation();
        if (width <= 1.0f || height <= 1.0f || screenWidthWithOrientation <= 1.0f || screenHeightWithOrientation <= 1.0f) {
            return new Matrix();
        }
        float f = (screenHeightWithOrientation * width) / (screenWidthWithOrientation * height);
        float fMax = Math.max(f, 1.0f / f);
        Matrix matrix2 = new Matrix();
        matrix2.postScale(fMax, fMax);
        return matrix2;
    }

    public final void applyZoomMatrix(Matrix newMatrix, boolean animation) {
        Intrinsics.checkNotNullParameter(newMatrix, "newMatrix");
        androidx.media3.ui.PlayerView exoPlayerView = this.playerView.getExoPlayerView();
        if (exoPlayerView == null) {
            return;
        }
        if (!animation) {
            ValueAnimator valueAnimator = this.matrixAnimation;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
            this.matrixAnimation = null;
        }
        Triple<Float, Float, Float> tripleMatrixToTranslationAndScale = INSTANCE.matrixToTranslationAndScale(newMatrix);
        float fFloatValue = tripleMatrixToTranslationAndScale.component1().floatValue();
        float fFloatValue2 = tripleMatrixToTranslationAndScale.component2().floatValue();
        float fFloatValue3 = tripleMatrixToTranslationAndScale.component3().floatValue();
        if (exoPlayerView.getResizeMode() == 0) {
            exoPlayerView.setResizeMode(4);
        }
        View videoSurfaceView = exoPlayerView.getVideoSurfaceView();
        if (videoSurfaceView == null) {
            return;
        }
        float width = videoSurfaceView.getWidth();
        float height = videoSurfaceView.getHeight();
        float screenWidthWithOrientation = CommonActivity.INSTANCE.getScreenWidthWithOrientation();
        float screenHeightWithOrientation = CommonActivity.INSTANCE.getScreenHeightWithOrientation();
        if (width <= 1.0f || height <= 1.0f || screenWidthWithOrientation <= 1.0f || screenHeightWithOrientation <= 1.0f || fFloatValue3 <= 0.01f) {
            return;
        }
        float f = (screenHeightWithOrientation * width) / (screenWidthWithOrientation * height);
        float fMin = Math.min(f, 1.0f / f);
        float f2 = fFloatValue3 * fMin;
        float fMax = Math.max(0.0f, (width * f2) - screenWidthWithOrientation) * 0.5f;
        float fMax2 = Math.max(0.0f, (height * f2) - screenHeightWithOrientation) * 0.5f;
        float fCoerceIn = RangesKt.coerceIn(fFloatValue, -fMax, fMax);
        float fCoerceIn2 = RangesKt.coerceIn(fFloatValue2, -fMax2, fMax2);
        newMatrix.postTranslate(fCoerceIn - fFloatValue, fCoerceIn2 - fFloatValue2);
        this.zoomMatrix = newMatrix;
        if (!animation) {
            if (Math.abs(f2 - 1.0f) < 0.07f) {
                View view = this.videoOutline;
                if (view != null) {
                    view.setVisibility(0);
                }
                Matrix matrix = new Matrix();
                float f3 = 1.0f / fMin;
                matrix.setScale(f3, f3);
                this.desiredMatrix = matrix;
            } else {
                View view2 = this.videoOutline;
                if (fFloatValue3 < 1.0f) {
                    if (view2 != null) {
                        view2.setVisibility(8);
                    }
                    this.desiredMatrix = new Matrix();
                } else {
                    if (view2 != null) {
                        view2.setVisibility(8);
                    }
                    this.desiredMatrix = null;
                }
            }
        }
        videoSurfaceView.setScaleX(f2);
        videoSurfaceView.setScaleY(f2);
        videoSurfaceView.setTranslationX(fCoerceIn);
        videoSurfaceView.setTranslationY(fCoerceIn2);
        updateBrightnessOverlayBounds();
    }

    public final void clearZoomState() {
        View videoSurfaceView;
        ValueAnimator valueAnimator = this.matrixAnimation;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        this.matrixAnimation = null;
        this.zoomMatrix = null;
        this.desiredMatrix = null;
        this.scaleGestureDetector = null;
        this.lastPan = null;
        androidx.media3.ui.PlayerView exoPlayerView = this.playerView.getExoPlayerView();
        if (exoPlayerView == null || (videoSurfaceView = exoPlayerView.getVideoSurfaceView()) == null) {
            return;
        }
        videoSurfaceView.setScaleX(1.0f);
        videoSurfaceView.setScaleY(1.0f);
        videoSurfaceView.setTranslationX(0.0f);
        videoSurfaceView.setTranslationY(0.0f);
    }

    public final void resetZoomToDefault() {
        if (this.zoomMatrix != null) {
            clearZoomState();
            this.playerView.resize(PlayerResize.Fit, false);
        }
    }

    private final void createScaleGestureDetector(Context ctx) {
        this.scaleGestureDetector = new ScaleGestureDetector(ctx, new ScaleGestureDetector.SimpleOnScaleGestureListener() { // from class: com.lagradost.cloudstream3.ui.player.PlayerGestureHelper.createScaleGestureDetector.1
            @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
            public boolean onScale(ScaleGestureDetector detector) {
                Intrinsics.checkNotNullParameter(detector, "detector");
                Matrix matrixCurrentZoomMatrix = PlayerGestureHelper.this.currentZoomMatrix();
                float fFloatValue = PlayerGestureHelper.INSTANCE.matrixToTranslationAndScale(matrixCurrentZoomMatrix).component3().floatValue();
                float fCoerceIn = RangesKt.coerceIn(detector.getScaleFactor() * fFloatValue, 0.95f, 4.0f) / fFloatValue;
                matrixCurrentZoomMatrix.postScale(fCoerceIn, fCoerceIn, detector.getFocusX() - (CommonActivity.INSTANCE.getScreenWidthWithOrientation() * 0.5f), detector.getFocusY() - (CommonActivity.INSTANCE.getScreenHeightWithOrientation() * 0.5f));
                PlayerGestureHelper.this.applyZoomMatrix(matrixCurrentZoomMatrix, false);
                return true;
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:25:0x0082  */
    /* JADX WARN: Code duplicated, block: B:27:0x0089  */
    /* JADX WARN: Code duplicated, block: B:30:0x0092  */
    /* JADX WARN: Code duplicated, block: B:33:0x00b2  */
    /* JADX WARN: Code duplicated, block: B:34:0x00b4  */
    public final boolean handleZoomPanGesture(MotionEvent event, Context ctx, Function0<Unit> onFirstPointerDown, Function0<Unit> onGestureEnd) {
        View view;
        ValueAnimator valueAnimator;
        ValueAnimator valueAnimatorOfFloat;
        Matrix matrixCurrentZoomMatrix;
        Matrix matrix;
        final PlayerGestureHelper playerGestureHelper;
        Intrinsics.checkNotNullParameter(event, "event");
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        Intrinsics.checkNotNullParameter(onFirstPointerDown, "onFirstPointerDown");
        Intrinsics.checkNotNullParameter(onGestureEnd, "onGestureEnd");
        if (this.scaleGestureDetector == null) {
            createScaleGestureDetector(ctx);
        }
        ScaleGestureDetector scaleGestureDetector = this.scaleGestureDetector;
        if (scaleGestureDetector != null) {
            scaleGestureDetector.onTouchEvent(event);
        }
        int actionMasked = event.getActionMasked();
        if (actionMasked == 1) {
            this.lastPan = null;
            view = this.videoOutline;
            if (view != null) {
                view.setVisibility(8);
            }
            valueAnimator = this.matrixAnimation;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
            this.matrixAnimation = null;
            valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            valueAnimatorOfFloat.setStartDelay(0L);
            valueAnimatorOfFloat.setDuration(200L);
            matrixCurrentZoomMatrix = currentZoomMatrix();
            matrix = this.desiredMatrix;
            if (matrix == null) {
                playerGestureHelper = this;
            } else {
                Companion companion = INSTANCE;
                Triple<Float, Float, Float> tripleMatrixToTranslationAndScale = companion.matrixToTranslationAndScale(matrixCurrentZoomMatrix);
                final float fFloatValue = tripleMatrixToTranslationAndScale.component1().floatValue();
                final float fFloatValue2 = tripleMatrixToTranslationAndScale.component2().floatValue();
                final float fFloatValue3 = tripleMatrixToTranslationAndScale.component3().floatValue();
                Triple<Float, Float, Float> tripleMatrixToTranslationAndScale2 = companion.matrixToTranslationAndScale(matrix);
                final float fFloatValue4 = tripleMatrixToTranslationAndScale2.component1().floatValue();
                final float fFloatValue5 = tripleMatrixToTranslationAndScale2.component2().floatValue();
                final float fFloatValue6 = tripleMatrixToTranslationAndScale2.component3().floatValue();
                playerGestureHelper = this;
                valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.lagradost.cloudstream3.ui.player.PlayerGestureHelper$$ExternalSyntheticLambda17
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                        PlayerGestureHelper.handleZoomPanGesture$lambda$0$0(fFloatValue3, fFloatValue6, fFloatValue, fFloatValue4, fFloatValue2, fFloatValue5, playerGestureHelper, valueAnimator2);
                    }
                });
                valueAnimatorOfFloat.start();
            }
            playerGestureHelper.matrixAnimation = valueAnimatorOfFloat;
            onGestureEnd.invoke();
        } else if (actionMasked != 2) {
            if (actionMasked == 3) {
                this.lastPan = null;
                view = this.videoOutline;
                if (view != null) {
                    view.setVisibility(8);
                }
                valueAnimator = this.matrixAnimation;
                if (valueAnimator != null) {
                    valueAnimator.cancel();
                }
                this.matrixAnimation = null;
                valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
                valueAnimatorOfFloat.setStartDelay(0L);
                valueAnimatorOfFloat.setDuration(200L);
                matrixCurrentZoomMatrix = currentZoomMatrix();
                matrix = this.desiredMatrix;
                if (matrix == null) {
                    playerGestureHelper = this;
                } else {
                    Companion companion2 = INSTANCE;
                    Triple<Float, Float, Float> tripleMatrixToTranslationAndScale3 = companion2.matrixToTranslationAndScale(matrixCurrentZoomMatrix);
                    final float fFloatValue7 = tripleMatrixToTranslationAndScale3.component1().floatValue();
                    final float fFloatValue8 = tripleMatrixToTranslationAndScale3.component2().floatValue();
                    final float fFloatValue9 = tripleMatrixToTranslationAndScale3.component3().floatValue();
                    Triple<Float, Float, Float> tripleMatrixToTranslationAndScale4 = companion2.matrixToTranslationAndScale(matrix);
                    final float fFloatValue10 = tripleMatrixToTranslationAndScale4.component1().floatValue();
                    final float fFloatValue11 = tripleMatrixToTranslationAndScale4.component2().floatValue();
                    final float fFloatValue12 = tripleMatrixToTranslationAndScale4.component3().floatValue();
                    playerGestureHelper = this;
                    valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.lagradost.cloudstream3.ui.player.PlayerGestureHelper$$ExternalSyntheticLambda17
                        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                        public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                            PlayerGestureHelper.handleZoomPanGesture$lambda$0$0(fFloatValue9, fFloatValue12, fFloatValue7, fFloatValue10, fFloatValue8, fFloatValue11, playerGestureHelper, valueAnimator2);
                        }
                    });
                    valueAnimatorOfFloat.start();
                }
                playerGestureHelper.matrixAnimation = valueAnimatorOfFloat;
                onGestureEnd.invoke();
            } else if (actionMasked == 5) {
                onFirstPointerDown.invoke();
            } else if (actionMasked == 6) {
                this.lastPan = null;
                view = this.videoOutline;
                if (view != null) {
                    view.setVisibility(8);
                }
                valueAnimator = this.matrixAnimation;
                if (valueAnimator != null) {
                    valueAnimator.cancel();
                }
                this.matrixAnimation = null;
                valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
                valueAnimatorOfFloat.setStartDelay(0L);
                valueAnimatorOfFloat.setDuration(200L);
                matrixCurrentZoomMatrix = currentZoomMatrix();
                matrix = this.desiredMatrix;
                if (matrix == null) {
                    playerGestureHelper = this;
                } else {
                    Companion companion3 = INSTANCE;
                    Triple<Float, Float, Float> tripleMatrixToTranslationAndScale5 = companion3.matrixToTranslationAndScale(matrixCurrentZoomMatrix);
                    final float fFloatValue13 = tripleMatrixToTranslationAndScale5.component1().floatValue();
                    final float fFloatValue14 = tripleMatrixToTranslationAndScale5.component2().floatValue();
                    final float fFloatValue15 = tripleMatrixToTranslationAndScale5.component3().floatValue();
                    Triple<Float, Float, Float> tripleMatrixToTranslationAndScale6 = companion3.matrixToTranslationAndScale(matrix);
                    final float fFloatValue16 = tripleMatrixToTranslationAndScale6.component1().floatValue();
                    final float fFloatValue17 = tripleMatrixToTranslationAndScale6.component2().floatValue();
                    final float fFloatValue18 = tripleMatrixToTranslationAndScale6.component3().floatValue();
                    playerGestureHelper = this;
                    valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.lagradost.cloudstream3.ui.player.PlayerGestureHelper$$ExternalSyntheticLambda17
                        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                        public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                            PlayerGestureHelper.handleZoomPanGesture$lambda$0$0(fFloatValue15, fFloatValue18, fFloatValue13, fFloatValue16, fFloatValue14, fFloatValue17, playerGestureHelper, valueAnimator2);
                        }
                    });
                    valueAnimatorOfFloat.start();
                }
                playerGestureHelper.matrixAnimation = valueAnimatorOfFloat;
                onGestureEnd.invoke();
            }
        } else if (event.getPointerCount() >= 2) {
            Vector2 vector2 = new Vector2((event.getX(0) + event.getX(1)) / 2.0f, (event.getY(0) + event.getY(1)) / 2.0f);
            Vector2 vector3 = this.lastPan;
            if (vector3 != null) {
                Matrix matrixCurrentZoomMatrix2 = currentZoomMatrix();
                matrixCurrentZoomMatrix2.postTranslate(vector2.getX() - vector3.getX(), vector2.getY() - vector3.getY());
                applyZoomMatrix(matrixCurrentZoomMatrix2, false);
            }
            this.lastPan = vector2;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void handleZoomPanGesture$lambda$0$0(float f, float f2, float f3, float f4, float f5, float f6, PlayerGestureHelper playerGestureHelper, ValueAnimator anim) {
        Intrinsics.checkNotNullParameter(anim, "anim");
        Object animatedValue = anim.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Float");
        float fFloatValue = ((Float) animatedValue).floatValue();
        float f7 = 1.0f - fFloatValue;
        Matrix matrix = new Matrix();
        float f8 = (f * f7) + (f2 * fFloatValue);
        matrix.setScale(f8, f8);
        matrix.postTranslate((f3 * f7) + (f4 * fFloatValue), (f5 * f7) + (f6 * fFloatValue));
        playerGestureHelper.applyZoomMatrix(matrix, true);
    }

    public final void updateBrightnessOverlayBounds() {
        androidx.media3.ui.PlayerView exoPlayerView;
        View videoSurfaceView;
        View view = this.brightnessOverlay;
        if (view == null || (exoPlayerView = this.playerView.getExoPlayerView()) == null || (videoSurfaceView = exoPlayerView.getVideoSurfaceView()) == null) {
            return;
        }
        float width = videoSurfaceView.getWidth();
        float height = videoSurfaceView.getHeight();
        float scaleX = videoSurfaceView.getScaleX();
        float scaleY = videoSurfaceView.getScaleY();
        if (width <= 0.0f || height <= 0.0f) {
            return;
        }
        float pivotX = videoSurfaceView.getPivotX() == 0.0f ? width * 0.5f : videoSurfaceView.getPivotX();
        float pivotY = videoSurfaceView.getPivotY() == 0.0f ? 0.5f * height : videoSurfaceView.getPivotY();
        float x = videoSurfaceView.getX();
        float y = videoSurfaceView.getY();
        float f = pivotX;
        Pair<Float, Float> pairUpdateBrightnessOverlayBounds$transform = updateBrightnessOverlayBounds$transform(x, f, scaleX, y, pivotY, scaleY, 0.0f, 0.0f);
        Pair<Float, Float> pairUpdateBrightnessOverlayBounds$transform2 = updateBrightnessOverlayBounds$transform(x, pivotX, scaleX, y, pivotY, scaleY, width, 0.0f);
        Pair<Float, Float> pairUpdateBrightnessOverlayBounds$transform3 = updateBrightnessOverlayBounds$transform(x, f, scaleX, y, pivotY, scaleY, 0.0f, height);
        Pair<Float, Float> pairUpdateBrightnessOverlayBounds$transform4 = updateBrightnessOverlayBounds$transform(x, pivotX, scaleX, y, pivotY, scaleY, width, height);
        float fMin = Math.min(Math.min(pairUpdateBrightnessOverlayBounds$transform.getFirst().floatValue(), pairUpdateBrightnessOverlayBounds$transform2.getFirst().floatValue()), Math.min(pairUpdateBrightnessOverlayBounds$transform3.getFirst().floatValue(), pairUpdateBrightnessOverlayBounds$transform4.getFirst().floatValue()));
        float fMax = Math.max(Math.max(pairUpdateBrightnessOverlayBounds$transform.getFirst().floatValue(), pairUpdateBrightnessOverlayBounds$transform2.getFirst().floatValue()), Math.max(pairUpdateBrightnessOverlayBounds$transform3.getFirst().floatValue(), pairUpdateBrightnessOverlayBounds$transform4.getFirst().floatValue()));
        float fMin2 = Math.min(Math.min(pairUpdateBrightnessOverlayBounds$transform.getSecond().floatValue(), pairUpdateBrightnessOverlayBounds$transform2.getSecond().floatValue()), Math.min(pairUpdateBrightnessOverlayBounds$transform3.getSecond().floatValue(), pairUpdateBrightnessOverlayBounds$transform4.getSecond().floatValue()));
        float fMax2 = Math.max(Math.max(pairUpdateBrightnessOverlayBounds$transform.getSecond().floatValue(), pairUpdateBrightnessOverlayBounds$transform2.getSecond().floatValue()), Math.max(pairUpdateBrightnessOverlayBounds$transform3.getSecond().floatValue(), pairUpdateBrightnessOverlayBounds$transform4.getSecond().floatValue()));
        int iCoerceAtLeast = RangesKt.coerceAtLeast((int) Math.ceil(fMax - fMin), 0);
        int iCoerceAtLeast2 = RangesKt.coerceAtLeast((int) Math.ceil(fMax2 - fMin2), 0);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            view.setLayoutParams(new ViewGroup.LayoutParams(iCoerceAtLeast, iCoerceAtLeast2));
        } else if (layoutParams.width != iCoerceAtLeast || layoutParams.height != iCoerceAtLeast2) {
            layoutParams.width = iCoerceAtLeast;
            layoutParams.height = iCoerceAtLeast2;
            view.setLayoutParams(layoutParams);
        }
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
        view.setX(fMin);
        view.setY(fMin2);
    }

    private static final Pair<Float, Float> updateBrightnessOverlayBounds$transform(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        return new Pair<>(Float.valueOf(f + f2 + ((f7 - f2) * f3)), Float.valueOf(f4 + f5 + ((f8 - f5) * f6)));
    }

    public final void requestUpdateBrightnessOverlayOnNextLayout() {
        androidx.media3.ui.PlayerView exoPlayerView = this.playerView.getExoPlayerView();
        if (exoPlayerView == null) {
            return;
        }
        View.OnLayoutChangeListener onLayoutChangeListener = this.overlayLayoutListener;
        if (onLayoutChangeListener != null) {
            exoPlayerView.removeOnLayoutChangeListener(onLayoutChangeListener);
        }
        View.OnLayoutChangeListener onLayoutChangeListener2 = new View.OnLayoutChangeListener() { // from class: com.lagradost.cloudstream3.ui.player.PlayerGestureHelper$$ExternalSyntheticLambda8
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.ui.player.PlayerGestureHelper$$ExternalSyntheticLambda13
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return PlayerGestureHelper.requestUpdateBrightnessOverlayOnNextLayout$lambda$1$0(this.f$0);
                    }
                });
            }
        };
        this.overlayLayoutListener = onLayoutChangeListener2;
        exoPlayerView.addOnLayoutChangeListener(onLayoutChangeListener2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit requestUpdateBrightnessOverlayOnNextLayout$lambda$1$0(PlayerGestureHelper playerGestureHelper) {
        playerGestureHelper.updateBrightnessOverlayBounds();
        return Unit.INSTANCE;
    }

    public final void releaseOverlayLayoutListener() {
        androidx.media3.ui.PlayerView exoPlayerView;
        View.OnLayoutChangeListener onLayoutChangeListener = this.overlayLayoutListener;
        if (onLayoutChangeListener != null && (exoPlayerView = this.playerView.getExoPlayerView()) != null) {
            exoPlayerView.removeOnLayoutChangeListener(onLayoutChangeListener);
        }
        this.overlayLayoutListener = null;
    }

    public final void resetRewindText() {
        TextView exoRewText$app = this.playerView.getExoRewText();
        if (exoRewText$app != null) {
            String string = getContext().getString(R.string.rew_text_regular_format);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            String str = String.format(string, Arrays.copyOf(new Object[]{Long.valueOf(this.fastForwardTime / 1000)}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
            exoRewText$app.setText(str);
        }
    }

    public final void resetFastForwardText() {
        TextView exoFfwdText$app = this.playerView.getExoFfwdText();
        if (exoFfwdText$app != null) {
            String string = getContext().getString(R.string.ffw_text_regular_format);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            String str = String.format(string, Arrays.copyOf(new Object[]{Long.valueOf(this.fastForwardTime / 1000)}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
            exoFfwdText$app.setText(str);
        }
    }

    private static final AlphaAnimation animateCenterControls$makeAnim(float f, float f2) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(f, f2);
        alphaAnimation.setDuration(100L);
        alphaAnimation.setFillAfter(true);
        return alphaAnimation;
    }

    public final void animateCenterControls(float fadeTo) {
        float f = fadeTo > 0.5f ? 0.0f : 1.0f;
        View playerRewHolder$app = this.playerView.getPlayerRewHolder();
        if (playerRewHolder$app != null) {
            playerRewHolder$app.setAlpha(1.0f);
            playerRewHolder$app.startAnimation(animateCenterControls$makeAnim(f, fadeTo));
        }
        View playerFfwdHolder$app = this.playerView.getPlayerFfwdHolder();
        if (playerFfwdHolder$app != null) {
            playerFfwdHolder$app.setAlpha(1.0f);
            playerFfwdHolder$app.startAnimation(animateCenterControls$makeAnim(f, fadeTo));
        }
        ImageView playerPausePlay = this.playerView.getPlayerPausePlay();
        if (playerPausePlay != null) {
            playerPausePlay.startAnimation(animateCenterControls$makeAnim(f, fadeTo));
        }
    }

    public final void rewind() {
        boolean z;
        boolean z2;
        try {
            View playerRewHolder$app = this.playerView.getPlayerRewHolder();
            if (playerRewHolder$app == null) {
                return;
            }
            View playerRew$app = this.playerView.getPlayerRew();
            TextView exoRewText$app = this.playerView.getExoRewText();
            PlayerView.Callbacks callbacks = this.playerView.getCallbacks();
            boolean zIsUIShowing = callbacks != null ? callbacks.isUIShowing() : false;
            View playerCenterMenu$app = this.playerView.getPlayerCenterMenu();
            if (playerCenterMenu$app != null) {
                z = playerCenterMenu$app.getVisibility() == 8;
            } else {
                z = false;
            }
            View playerVideoHolder$app = this.playerView.getPlayerVideoHolder();
            if (playerVideoHolder$app != null) {
                z2 = playerVideoHolder$app.getVisibility() == 0;
            } else {
                z2 = true;
            }
            if (!zIsUIShowing) {
                View playerCenterMenu$app2 = this.playerView.getPlayerCenterMenu();
                if (playerCenterMenu$app2 != null) {
                    playerCenterMenu$app2.setVisibility(0);
                }
                View playerVideoHolder$app2 = this.playerView.getPlayerVideoHolder();
                if (playerVideoHolder$app2 != null) {
                    playerVideoHolder$app2.setVisibility(0);
                }
            }
            playerRewHolder$app.setAlpha(1.0f);
            if (playerRew$app != null) {
                playerRew$app.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.rotate_left));
            }
            Animation animationLoadAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.go_left);
            animationLoadAnimation.setAnimationListener(new AnimationAnimationListenerC08621(exoRewText$app, this, zIsUIShowing, z, z2, playerRewHolder$app));
            if (exoRewText$app != null) {
                exoRewText$app.startAnimation(animationLoadAnimation);
            }
            if (exoRewText$app != null) {
                String string = getContext().getString(R.string.rew_text_format);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                String str = String.format(string, Arrays.copyOf(new Object[]{Long.valueOf(this.fastForwardTime / 1000)}, 1));
                Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                exoRewText$app.setText(str);
            }
            IPlayer.CC.seekTime$default(this.playerView.getPlayer(), -this.fastForwardTime, null, 2, null);
        } catch (Exception e) {
            ArchComponentExtKt.logError(e);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.player.PlayerGestureHelper$rewind$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PlayerGestureHelper.kt */
    @Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0007\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\b"}, d2 = {"com/lagradost/cloudstream3/ui/player/PlayerGestureHelper$rewind$1", "Landroid/view/animation/Animation$AnimationListener;", "onAnimationStart", "", "animation", "Landroid/view/animation/Animation;", "onAnimationRepeat", "onAnimationEnd", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class AnimationAnimationListenerC08621 implements Animation.AnimationListener {
        final /* synthetic */ boolean $prevCenterMenuGone;
        final /* synthetic */ boolean $prevVideoHolderVisible;
        final /* synthetic */ View $rewHolder;
        final /* synthetic */ TextView $rewText;
        final /* synthetic */ boolean $wasShowing;
        final /* synthetic */ PlayerGestureHelper this$0;

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }

        AnimationAnimationListenerC08621(TextView textView, PlayerGestureHelper playerGestureHelper, boolean z, boolean z2, boolean z3, View view) {
            this.$rewText = textView;
            this.this$0 = playerGestureHelper;
            this.$wasShowing = z;
            this.$prevCenterMenuGone = z2;
            this.$prevVideoHolderVisible = z3;
            this.$rewHolder = view;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            TextView textView = this.$rewText;
            if (textView != null) {
                final PlayerGestureHelper playerGestureHelper = this.this$0;
                final boolean z = this.$wasShowing;
                final boolean z2 = this.$prevCenterMenuGone;
                final boolean z3 = this.$prevVideoHolderVisible;
                final View view = this.$rewHolder;
                textView.post(new Runnable() { // from class: com.lagradost.cloudstream3.ui.player.PlayerGestureHelper$rewind$1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        PlayerGestureHelper.AnimationAnimationListenerC08621.onAnimationEnd$lambda$0(playerGestureHelper, z, z2, z3, view);
                    }
                });
            }
        }

        static final void onAnimationEnd$lambda$0(PlayerGestureHelper playerGestureHelper, boolean z, boolean z2, boolean z3, View view) {
            playerGestureHelper.resetRewindText();
            if (z) {
                return;
            }
            PlayerView.Callbacks callbacks = playerGestureHelper.playerView.getCallbacks();
            if (callbacks != null ? callbacks.isUIShowing() : false) {
                return;
            }
            View playerCenterMenu$app = playerGestureHelper.playerView.getPlayerCenterMenu();
            if (playerCenterMenu$app != null) {
                playerCenterMenu$app.setVisibility(z2 ? 8 : 0);
            }
            View playerVideoHolder$app = playerGestureHelper.playerView.getPlayerVideoHolder();
            if (playerVideoHolder$app != null) {
                playerVideoHolder$app.setVisibility(z3 ? 0 : 8);
            }
            view.setAlpha(0.0f);
        }
    }

    public final void fastForward() {
        boolean z;
        boolean z2;
        try {
            View playerFfwdHolder$app = this.playerView.getPlayerFfwdHolder();
            if (playerFfwdHolder$app == null) {
                return;
            }
            View playerFfwd$app = this.playerView.getPlayerFfwd();
            TextView exoFfwdText$app = this.playerView.getExoFfwdText();
            PlayerView.Callbacks callbacks = this.playerView.getCallbacks();
            boolean zIsUIShowing = callbacks != null ? callbacks.isUIShowing() : false;
            View playerCenterMenu$app = this.playerView.getPlayerCenterMenu();
            if (playerCenterMenu$app != null) {
                z = playerCenterMenu$app.getVisibility() == 8;
            } else {
                z = false;
            }
            View playerVideoHolder$app = this.playerView.getPlayerVideoHolder();
            if (playerVideoHolder$app != null) {
                z2 = playerVideoHolder$app.getVisibility() == 0;
            } else {
                z2 = true;
            }
            if (!zIsUIShowing) {
                View playerCenterMenu$app2 = this.playerView.getPlayerCenterMenu();
                if (playerCenterMenu$app2 != null) {
                    playerCenterMenu$app2.setVisibility(0);
                }
                View playerVideoHolder$app2 = this.playerView.getPlayerVideoHolder();
                if (playerVideoHolder$app2 != null) {
                    playerVideoHolder$app2.setVisibility(0);
                }
            }
            playerFfwdHolder$app.setAlpha(1.0f);
            if (playerFfwd$app != null) {
                playerFfwd$app.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.rotate_right));
            }
            Animation animationLoadAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.go_right);
            animationLoadAnimation.setAnimationListener(new AnimationAnimationListenerC08611(exoFfwdText$app, this, zIsUIShowing, z, z2, playerFfwdHolder$app));
            if (exoFfwdText$app != null) {
                exoFfwdText$app.startAnimation(animationLoadAnimation);
            }
            if (exoFfwdText$app != null) {
                String string = getContext().getString(R.string.ffw_text_format);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                String str = String.format(string, Arrays.copyOf(new Object[]{Long.valueOf(this.fastForwardTime / 1000)}, 1));
                Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                exoFfwdText$app.setText(str);
            }
            IPlayer.CC.seekTime$default(this.playerView.getPlayer(), this.fastForwardTime, null, 2, null);
        } catch (Exception e) {
            ArchComponentExtKt.logError(e);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.player.PlayerGestureHelper$fastForward$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PlayerGestureHelper.kt */
    @Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0007\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\b"}, d2 = {"com/lagradost/cloudstream3/ui/player/PlayerGestureHelper$fastForward$1", "Landroid/view/animation/Animation$AnimationListener;", "onAnimationStart", "", "animation", "Landroid/view/animation/Animation;", "onAnimationRepeat", "onAnimationEnd", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class AnimationAnimationListenerC08611 implements Animation.AnimationListener {
        final /* synthetic */ View $ffwdHolder;
        final /* synthetic */ TextView $ffwdText;
        final /* synthetic */ boolean $prevCenterMenuGone;
        final /* synthetic */ boolean $prevVideoHolderVisible;
        final /* synthetic */ boolean $wasShowing;
        final /* synthetic */ PlayerGestureHelper this$0;

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }

        AnimationAnimationListenerC08611(TextView textView, PlayerGestureHelper playerGestureHelper, boolean z, boolean z2, boolean z3, View view) {
            this.$ffwdText = textView;
            this.this$0 = playerGestureHelper;
            this.$wasShowing = z;
            this.$prevCenterMenuGone = z2;
            this.$prevVideoHolderVisible = z3;
            this.$ffwdHolder = view;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            TextView textView = this.$ffwdText;
            if (textView != null) {
                final PlayerGestureHelper playerGestureHelper = this.this$0;
                final boolean z = this.$wasShowing;
                final boolean z2 = this.$prevCenterMenuGone;
                final boolean z3 = this.$prevVideoHolderVisible;
                final View view = this.$ffwdHolder;
                textView.post(new Runnable() { // from class: com.lagradost.cloudstream3.ui.player.PlayerGestureHelper$fastForward$1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        PlayerGestureHelper.AnimationAnimationListenerC08611.onAnimationEnd$lambda$0(playerGestureHelper, z, z2, z3, view);
                    }
                });
            }
        }

        static final void onAnimationEnd$lambda$0(PlayerGestureHelper playerGestureHelper, boolean z, boolean z2, boolean z3, View view) {
            playerGestureHelper.resetFastForwardText();
            if (z) {
                return;
            }
            PlayerView.Callbacks callbacks = playerGestureHelper.playerView.getCallbacks();
            if (callbacks != null ? callbacks.isUIShowing() : false) {
                return;
            }
            View playerCenterMenu$app = playerGestureHelper.playerView.getPlayerCenterMenu();
            if (playerCenterMenu$app != null) {
                playerCenterMenu$app.setVisibility(z2 ? 8 : 0);
            }
            View playerVideoHolder$app = playerGestureHelper.playerView.getPlayerVideoHolder();
            if (playerVideoHolder$app != null) {
                playerVideoHolder$app.setVisibility(z3 ? 0 : 8);
            }
            view.setAlpha(0.0f);
        }
    }

    public final boolean onTapDetected(float x, int viewWidth, boolean isLocked, final Function0<Unit> onSingleTap) {
        Intrinsics.checkNotNullParameter(onSingleTap, "onSingleTap");
        if (!this.doubleTapEnabled && !this.doubleTapPauseEnabled) {
            onSingleTap.invoke();
            return false;
        }
        long jCurrentTimeMillis = System.currentTimeMillis() - this.lastTouchEndTime;
        if (!isLocked && jCurrentTimeMillis < 200) {
            this.tapCount++;
            this.doubleTapToken++;
            if (this.doubleTapPauseEnabled) {
                double d = x;
                double d2 = viewWidth / 2.0f;
                double d3 = ((double) viewWidth) * 0.15d;
                if (d < d2 - d3) {
                    if (this.doubleTapEnabled) {
                        rewind();
                    }
                } else if (d > d2 + d3) {
                    if (this.doubleTapEnabled) {
                        fastForward();
                    }
                } else {
                    this.playerView.getPlayer().handleEvent(CSPlayerEvent.PlayPauseToggle, PlayerEventSource.UI);
                }
            } else if (this.doubleTapEnabled) {
                if (x < viewWidth / 2.0f) {
                    rewind();
                } else {
                    fastForward();
                }
            }
            return true;
        }
        this.tapCount = 0;
        final int i = this.doubleTapToken + 1;
        this.doubleTapToken = i;
        FrameLayout playerHolder = this.playerView.getPlayerHolder();
        if (playerHolder != null) {
            playerHolder.postDelayed(new Runnable() { // from class: com.lagradost.cloudstream3.ui.player.PlayerGestureHelper$$ExternalSyntheticLambda6
                @Override // java.lang.Runnable
                public final void run() {
                    PlayerGestureHelper.onTapDetected$lambda$0(i, this, onSingleTap);
                }
            }, 200L);
        }
        return false;
    }

    static final void onTapDetected$lambda$0(int i, PlayerGestureHelper playerGestureHelper, Function0 function0) {
        if (i == playerGestureHelper.doubleTapToken) {
            function0.invoke();
        }
    }

    private final Long calculateNewTime(Long startTime, Vector2 touchStart, Vector2 touchEnd) {
        if (touchStart != null && touchEnd != null && startTime != null) {
            float x = ((touchEnd.getX() - touchStart.getX()) * 2.0f) / CommonActivity.INSTANCE.getScreenWidthWithOrientation();
            Long duration = this.playerView.getPlayer().getDuration();
            if (duration != null) {
                long jLongValue = duration.longValue();
                return Long.valueOf(Math.max(Math.min(startTime.longValue() + ((long) (jLongValue * x * x * (x < 0.0f ? -1 : 1))), jLongValue), 0L));
            }
        }
        return null;
    }

    static /* synthetic */ String forceLetters$default(PlayerGestureHelper playerGestureHelper, long j, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 2;
        }
        return playerGestureHelper.forceLetters(j, i);
    }

    private final String forceLetters(long inp, int letters) {
        int length = letters - String.valueOf(inp).length();
        if (length <= 0) {
            return String.valueOf(inp);
        }
        return StringsKt.repeat("0", length) + inp;
    }

    private final String convertTimeToString(long sec) {
        String str;
        long j = sec % 60;
        long jCeil = (int) Math.ceil((sec - j) / 60.0d);
        long j2 = jCeil % 60;
        long jCeil2 = (long) Math.ceil((jCeil - j2) / 60.0d);
        StringBuilder sb = new StringBuilder();
        String str2 = "";
        if (jCeil2 > 0) {
            str = forceLetters$default(this, jCeil2, 0, 2, null) + AbstractJsonLexerKt.COLON;
        } else {
            str = "";
        }
        sb.append(str);
        if (j2 >= 0 || jCeil2 >= 0) {
            str2 = forceLetters$default(this, j2, 0, 2, null) + AbstractJsonLexerKt.COLON;
        }
        sb.append(str2);
        sb.append(forceLetters$default(this, j, 0, 2, null));
        return sb.toString();
    }

    public final void setupTouchGestures() {
        FrameLayout playerHolder = this.playerView.getPlayerHolder();
        if (playerHolder == null) {
            return;
        }
        playerHolder.setOnTouchListener(new View.OnTouchListener() { // from class: com.lagradost.cloudstream3.ui.player.PlayerGestureHelper$$ExternalSyntheticLambda22
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f$0.handleGesture(view, motionEvent);
            }
        });
    }

    private final boolean isValidTouch(float rawX, float rawY) {
        if (Build.VERSION.SDK_INT < 30) {
            return rawY > ((float) UIHelper.INSTANCE.getStatusBarHeight(getContext())) && rawX < ((float) CommonActivity.INSTANCE.getScreenWidthWithOrientation());
        }
        FrameLayout playerHolder = this.playerView.getPlayerHolder();
        if (playerHolder == null) {
            return true;
        }
        Insets insetsIgnoringVisibility = playerHolder.getRootWindowInsets().getInsetsIgnoringVisibility(WindowInsets.Type.systemBars());
        Intrinsics.checkNotNullExpressionValue(insetsIgnoringVisibility, "getInsetsIgnoringVisibility(...)");
        return ((rawY > ((float) insetsIgnoringVisibility.top) ? 1 : (rawY == ((float) insetsIgnoringVisibility.top) ? 0 : -1)) > 0 && (rawY > ((float) (CommonActivity.INSTANCE.getScreenHeightWithOrientation() - insetsIgnoringVisibility.bottom)) ? 1 : (rawY == ((float) (CommonActivity.INSTANCE.getScreenHeightWithOrientation() - insetsIgnoringVisibility.bottom)) ? 0 : -1)) < 0) && ((rawX > ((float) insetsIgnoringVisibility.left) ? 1 : (rawX == ((float) insetsIgnoringVisibility.left) ? 0 : -1)) > 0 && (rawX > ((float) (CommonActivity.INSTANCE.getScreenWidthWithOrientation() - insetsIgnoringVisibility.right)) ? 1 : (rawX == ((float) (CommonActivity.INSTANCE.getScreenWidthWithOrientation() - insetsIgnoringVisibility.right)) ? 0 : -1)) < 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean handleGesture(View view, MotionEvent event) {
        Long l;
        Long lCalculateNewTime;
        PlayerView.Callbacks callbacks;
        Long lCalculateNewTime2;
        String str;
        Vector2 vector2 = new Vector2(event.getX(), event.getY());
        Vector2 vector3 = this.currentTouchStart;
        if ((event.getPointerCount() >= 2 || this.lastPan != null) && this.isFullScreen && !this.isLocked && !this.hasTriggeredSpeedUp && this.currentTouchAction == null) {
            this.holdHandler.removeCallbacks(this.holdRunnable);
            this.isCurrentTouchValid = false;
            Context context = view.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            return handleZoomPanGesture(event, context, new Function0() { // from class: com.lagradost.cloudstream3.ui.player.PlayerGestureHelper$$ExternalSyntheticLambda19
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return PlayerGestureHelper.handleGesture$lambda$0(this.f$0);
                }
            }, new Function0() { // from class: com.lagradost.cloudstream3.ui.player.PlayerGestureHelper$$ExternalSyntheticLambda20
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return PlayerGestureHelper.handleGesture$lambda$1(this.f$0);
                }
            });
        }
        int actionMasked = event.getActionMasked();
        if (actionMasked == 0) {
            boolean zIsValidTouch = isValidTouch(event.getRawX(), event.getRawY());
            this.isCurrentTouchValid = zIsValidTouch;
            if (zIsValidTouch) {
                PlayerView.Callbacks callbacks2 = this.playerView.getCallbacks();
                if (callbacks2 != null) {
                    callbacks2.onTouchDown();
                }
                this.hasTriggeredSpeedUp = false;
                if (this.speedupEnabled && this.playerView.getPlayer().getIsPlaying() && !this.isLocked) {
                    this.holdHandler.postDelayed(this.holdRunnable, 500L);
                }
                float f = this.currentRequestedVolume;
                this.isVolumeLocked = f < 1.0f;
                if (f <= 1.0f) {
                    this.hasShownVolumeToast = false;
                }
                float f2 = this.currentRequestedBrightness;
                this.isBrightnessLocked = f2 < 1.0f;
                if (f2 <= 1.0f) {
                    this.hasShownBrightnessToast = false;
                }
                this.currentTouchStartTime = Long.valueOf(System.currentTimeMillis());
                this.currentTouchStart = vector2;
                this.currentTouchLast = vector2;
                this.currentTouchStartPlayerTime = this.playerView.getPlayer().getPosition();
                Float brightness = getBrightness();
                if (brightness != null) {
                    this.currentRequestedBrightness = brightness.floatValue() + this.currentExtraBrightness;
                }
                verifyVolume();
            }
            return true;
        }
        if (actionMasked != 1) {
            if (actionMasked == 2) {
                if (this.hasTriggeredSpeedUp || !this.isCurrentTouchValid) {
                    return true;
                }
                if (this.currentTouchAction == null && vector3 != null) {
                    Vector2 vector2Minus = vector3.minus(vector2);
                    if (this.swipeVerticalEnabled && Math.abs((vector2Minus.getY() * 100.0f) / CommonActivity.INSTANCE.getScreenHeightWithOrientation()) > 2.0f) {
                        this.holdHandler.removeCallbacks(this.holdRunnable);
                        PlayerView.Callbacks callbacks3 = this.playerView.getCallbacks();
                        this.uiShowingBeforeGesture = callbacks3 != null ? callbacks3.isUIShowing() : false;
                        PlayerView.Callbacks callbacks4 = this.playerView.getCallbacks();
                        if (callbacks4 != null) {
                            callbacks4.onHidePlayerUI();
                        }
                        this.currentTouchAction = vector3.getX() >= ((float) view.getWidth()) / 2.0f ? TouchAction.Volume : TouchAction.Brightness;
                    }
                    if (this.swipeHorizontalEnabled && !this.isLocked && Math.abs((vector2Minus.getX() * 100.0f) / CommonActivity.INSTANCE.getScreenHeightWithOrientation()) > 2.0f) {
                        this.holdHandler.removeCallbacks(this.holdRunnable);
                        this.currentTouchAction = TouchAction.Time;
                    }
                }
                Vector2 vector4 = this.currentTouchLast;
                if (vector4 != null) {
                    float y = (vector4.minus(vector2).getY() * 2.0f) / view.getHeight();
                    TouchAction touchAction = this.currentTouchAction;
                    int i = touchAction == null ? -1 : WhenMappings.$EnumSwitchMapping$0[touchAction.ordinal()];
                    if (i != -1) {
                        if (i == 1) {
                            Long l2 = this.currentTouchStartPlayerTime;
                            Long lValueOf = l2 != null ? Long.valueOf((l2.longValue() / 1000) * 1000) : null;
                            if (lValueOf != null && (lCalculateNewTime2 = calculateNewTime(lValueOf, vector3, vector2)) != null) {
                                long jLongValue = lCalculateNewTime2.longValue();
                                long jLongValue2 = jLongValue - lValueOf.longValue();
                                PlayerView.Callbacks callbacks5 = this.playerView.getCallbacks();
                                if (callbacks5 != null) {
                                    StringBuilder sb = new StringBuilder();
                                    sb.append(convertTimeToString(jLongValue / 1000));
                                    sb.append(" [");
                                    if (Math.abs(jLongValue2) < 1000) {
                                        str = "";
                                    } else {
                                        str = jLongValue2 > 0 ? Marker.ANY_NON_NULL_MARKER : "-";
                                    }
                                    sb.append(str);
                                    sb.append(convertTimeToString(Math.abs(jLongValue2 / 1000)));
                                    sb.append(AbstractJsonLexerKt.END_LIST);
                                    callbacks5.onSeekPreviewText(sb.toString());
                                }
                            }
                        } else if (i != 2) {
                            if (i != 3) {
                                throw new NoWhenBranchMatchedException();
                            }
                            if (!this.isLocked) {
                                handleVolumeAdjustment(y, false);
                            }
                        } else if (!this.isLocked) {
                            handleBrightnessAdjustment(y);
                        }
                    }
                    if (this.currentTouchAction != TouchAction.Time && (callbacks = this.playerView.getCallbacks()) != null) {
                        callbacks.onSeekPreviewText(null);
                    }
                }
                this.currentTouchLast = vector2;
                return true;
            }
            if (actionMasked != 3) {
                return false;
            }
        }
        this.holdHandler.removeCallbacks(this.holdRunnable);
        if (this.hasTriggeredSpeedUp) {
            this.playerView.getPlayer().setPlaybackSpeed(DataStoreHelper.INSTANCE.getPlayBackSpeed());
            showOrHideSpeedUp(false);
            PlayerView.Callbacks callbacks6 = this.playerView.getCallbacks();
            if (callbacks6 != null) {
                callbacks6.onHoldSpeedUp(false);
            }
            this.hasTriggeredSpeedUp = false;
        }
        if (this.isCurrentTouchValid) {
            if (this.swipeHorizontalEnabled && this.currentTouchAction == TouchAction.Time && !this.isLocked && (l = this.currentTouchStartPlayerTime) != null && (lCalculateNewTime = calculateNewTime(l, vector3, vector2)) != null) {
                long jLongValue3 = lCalculateNewTime.longValue();
                if (Math.abs(jLongValue3 - l.longValue()) > MINIMUM_SEEK_TIME) {
                    this.playerView.getPlayer().seekTo(jLongValue3, PlayerEventSource.UI);
                }
            }
            Long l3 = this.currentTouchStartTime;
            Long lValueOf2 = l3 != null ? Long.valueOf(System.currentTimeMillis() - l3.longValue()) : null;
            if (this.currentTouchAction == null && this.currentLastTouchAction == null && !this.hasTriggeredSpeedUp && (lValueOf2 == null || lValueOf2.longValue() < 200)) {
                onTapDetected(vector2.getX(), view.getWidth(), this.isLocked, new Function0() { // from class: com.lagradost.cloudstream3.ui.player.PlayerGestureHelper$$ExternalSyntheticLambda21
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return PlayerGestureHelper.handleGesture$lambda$6(this.f$0);
                    }
                });
            }
        }
        PlayerView.Callbacks callbacks7 = this.playerView.getCallbacks();
        if (callbacks7 != null) {
            callbacks7.onSeekPreviewText(null);
        }
        boolean z = (this.currentTouchAction == null && this.currentLastTouchAction == null) ? false : true;
        PlayerView.Callbacks callbacks8 = this.playerView.getCallbacks();
        if (callbacks8 != null) {
            callbacks8.onGestureEnd(z, this.uiShowingBeforeGesture);
        }
        this.lastTouchEndTime = System.currentTimeMillis();
        this.isCurrentTouchValid = false;
        this.currentTouchStart = null;
        this.currentLastTouchAction = this.currentTouchAction;
        this.currentTouchAction = null;
        this.currentTouchStartPlayerTime = null;
        this.currentTouchLast = null;
        this.currentTouchStartTime = null;
        this.uiShowingBeforeGesture = false;
        return true;
    }

    static final Unit handleGesture$lambda$0(PlayerGestureHelper playerGestureHelper) {
        PlayerView.Callbacks callbacks = playerGestureHelper.playerView.getCallbacks();
        playerGestureHelper.uiShowingBeforeGesture = callbacks != null ? callbacks.isUIShowing() : false;
        PlayerView.Callbacks callbacks2 = playerGestureHelper.playerView.getCallbacks();
        if (callbacks2 != null) {
            callbacks2.onHidePlayerUI();
        }
        return Unit.INSTANCE;
    }

    static final Unit handleGesture$lambda$1(PlayerGestureHelper playerGestureHelper) {
        playerGestureHelper.currentTouchStart = null;
        playerGestureHelper.currentLastTouchAction = null;
        playerGestureHelper.currentTouchAction = null;
        playerGestureHelper.currentTouchStartPlayerTime = null;
        playerGestureHelper.currentTouchLast = null;
        playerGestureHelper.currentTouchStartTime = null;
        return Unit.INSTANCE;
    }

    static final Unit handleGesture$lambda$6(PlayerGestureHelper playerGestureHelper) {
        PlayerView.Callbacks callbacks = playerGestureHelper.playerView.getCallbacks();
        if (callbacks != null) {
            callbacks.onSingleTap();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showOrHideSpeedUp$lambda$0$0(boolean z, View view) {
        if (z) {
            return;
        }
        view.setVisibility(8);
    }
}
