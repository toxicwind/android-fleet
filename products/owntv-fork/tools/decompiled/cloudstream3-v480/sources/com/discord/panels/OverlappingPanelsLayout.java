package com.discord.panels;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.core.app.NotificationCompat;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference0;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

/* JADX INFO: compiled from: OverlappingPanelsLayout.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000¤\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b'\n\u0002\u0010\u0011\n\u0002\b\u0010\n\u0002\u0010\t\n\u0002\b\u0006\b\u0016\u0018\u0000 w2\u00020\u0001:\u0005wxyz{B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u001b\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B%\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0018\u0010:\u001a\u00020\u000e2\u0006\u0010;\u001a\u00020\u000e2\u0006\u0010<\u001a\u00020=H\u0002J\u0018\u0010>\u001a\u00020\u000e2\u0006\u0010?\u001a\u00020\u000e2\u0006\u0010<\u001a\u00020=H\u0002J\u0006\u0010@\u001a\u00020)J\u0012\u0010@\u001a\u00020)2\b\b\u0002\u0010A\u001a\u00020!H\u0002J\u0018\u0010B\u001a\u00020\u001a2\u0006\u0010C\u001a\u00020\u000e2\u0006\u0010D\u001a\u00020\u000eH\u0002J\b\u0010E\u001a\u00020,H\u0002J\b\u0010F\u001a\u00020\u0017H\u0002J\u0010\u0010G\u001a\u00020\u000e2\u0006\u0010H\u001a\u00020\u000eH\u0002J\b\u0010I\u001a\u00020,H\u0002J\b\u0010J\u001a\u00020\u0017H\u0002J\u0006\u0010K\u001a\u00020,J\u0018\u0010L\u001a\u00020\u001a2\u0006\u0010C\u001a\u00020\u000e2\u0006\u0010D\u001a\u00020\u000eH\u0002J\u0010\u0010M\u001a\u00020\u000e2\u0006\u0010<\u001a\u00020=H\u0002J\u0018\u0010N\u001a\u00020)2\u0006\u0010C\u001a\u00020\u000e2\u0006\u0010D\u001a\u00020\u000eH\u0002J\u000e\u0010O\u001a\u00020)2\u0006\u0010\u0019\u001a\u00020\u001aJ\b\u0010P\u001a\u00020)H\u0002J\u000e\u0010Q\u001a\u00020)2\u0006\u00100\u001a\u00020\u001aJ\b\u0010R\u001a\u00020)H\u0002J\b\u0010S\u001a\u00020)H\u0002J\u0012\u0010T\u001a\u00020)2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002J\u0010\u0010U\u001a\u00020!2\u0006\u0010<\u001a\u00020=H\u0002J\u0010\u0010V\u001a\u00020!2\u0006\u0010<\u001a\u00020=H\u0002J\u0010\u0010W\u001a\u00020!2\u0006\u0010<\u001a\u00020=H\u0016J0\u0010X\u001a\u00020)2\u0006\u0010Y\u001a\u00020!2\u0006\u0010Z\u001a\u00020\t2\u0006\u0010[\u001a\u00020\t2\u0006\u0010\\\u001a\u00020\t2\u0006\u0010]\u001a\u00020\tH\u0014J\u0010\u0010^\u001a\u00020!2\u0006\u0010<\u001a\u00020=H\u0016J\u0006\u0010_\u001a\u00020)J\u0012\u0010_\u001a\u00020)2\b\b\u0002\u0010A\u001a\u00020!H\u0002J\u0010\u0010`\u001a\u00020)2\u0006\u0010a\u001a\u00020,H\u0002J\u0006\u0010b\u001a\u00020)J\u0012\u0010b\u001a\u00020)2\b\b\u0002\u0010A\u001a\u00020!H\u0002J\u001f\u0010c\u001a\u00020)2\u0012\u0010d\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001d0e\"\u00020\u001d¢\u0006\u0002\u0010fJ\u001f\u0010g\u001a\u00020)2\u0012\u0010d\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001d0e\"\u00020\u001d¢\u0006\u0002\u0010fJ\b\u0010h\u001a\u00020)H\u0002J\b\u0010i\u001a\u00020)H\u0002J\u0014\u0010j\u001a\u00020)2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013J\u000e\u0010k\u001a\u00020)2\u0006\u0010l\u001a\u00020\u0017J\u000e\u0010m\u001a\u00020)2\u0006\u0010l\u001a\u00020\u0017J\u000e\u0010n\u001a\u00020)2\u0006\u0010o\u001a\u00020!J\u0010\u0010p\u001a\u00020!2\u0006\u0010<\u001a\u00020=H\u0002J\u0010\u0010q\u001a\u00020)2\u0006\u0010<\u001a\u00020=H\u0002J\u0010\u0010r\u001a\u00020)2\u0006\u0010<\u001a\u00020=H\u0002J\u0010\u0010s\u001a\u00020)2\u0006\u0010D\u001a\u00020\u000eH\u0002J$\u0010t\u001a\u00020)2\u0006\u0010D\u001a\u00020\u000e2\b\b\u0002\u0010A\u001a\u00020!2\b\b\u0002\u0010u\u001a\u00020vH\u0002R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u001b\u001a\u0012\u0012\u0004\u0012\u00020\u001d0\u001cj\b\u0012\u0004\u0012\u00020\u001d`\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020!X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010'\u001a\n\u0012\u0004\u0012\u00020)\u0018\u00010(X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020,X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u00101\u001a\u0012\u0012\u0004\u0012\u00020\u001d0\u001cj\b\u0012\u0004\u0012\u00020\u001d`\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u00102\u001a\u0004\u0018\u000103X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00105\u001a\u0004\u0018\u000106X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006|"}, d2 = {"Lcom/discord/panels/OverlappingPanelsLayout;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "centerPanel", "Landroid/view/View;", "centerPanelAnimationEndX", "", "centerPanelDiffX", "centerPanelXAnimator", "Landroid/animation/ValueAnimator;", "childGestureRegions", "", "Landroid/graphics/Rect;", "endPanel", "endPanelLockState", "Lcom/discord/panels/OverlappingPanelsLayout$LockState;", "endPanelOpenedCenterPanelX", "endPanelState", "Lcom/discord/panels/PanelState;", "endPanelStateListeners", "Ljava/util/ArrayList;", "Lcom/discord/panels/OverlappingPanelsLayout$PanelStateListener;", "Lkotlin/collections/ArrayList;", "homeGestureFromBottomThreshold", "isHomeSystemGesture", "", "isLeftToRight", "isScrollingHorizontally", "isSystemGestureNavigationPossible", "minFlingPxPerSecond", "nonFullScreenSidePanelWidth", "pendingUpdate", "Lkotlin/Function0;", "", "scrollingSlopPx", "selectedPanel", "Lcom/discord/panels/OverlappingPanelsLayout$Panel;", "startPanel", "startPanelLockState", "startPanelOpenedCenterPanelX", "startPanelState", "startPanelStateListeners", "swipeDirection", "Lcom/discord/panels/OverlappingPanelsLayout$SwipeDirection;", "useFullWidthForStartPanel", "velocityTracker", "Landroid/view/VelocityTracker;", "wasActionDownOnClosedCenterPanel", "xFromInterceptActionDown", "yFromInterceptActionDown", "calculateDistanceX", "startX", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "calculateDistanceY", "startY", "closePanels", "isFling", "getEndPanelState", "previousX", "x", "getLeftPanel", "getLeftPanelLockState", "getNormalizedX", "targetedX", "getRightPanel", "getRightPanelLockState", "getSelectedPanel", "getStartPanelState", "getTargetedX", "handleCenterPanelX", "handleEndPanelState", "handleEndPanelWidthUpdate", "handleStartPanelState", "handleStartPanelWidthUpdate", "initPanels", "initialize", "isTouchingCenterPanelWhileSidePanelOpen", "isTouchingChildGestureRegion", "onInterceptTouchEvent", "onLayout", "changed", TtmlNode.LEFT, "top", TtmlNode.RIGHT, "bottom", "onTouchEvent", "openEndPanel", "openPanel", "panel", "openStartPanel", "registerEndPanelStateListeners", "panelStateListenerArgs", "", "([Lcom/discord/panels/OverlappingPanelsLayout$PanelStateListener;)V", "registerStartPanelStateListeners", "resetEndPanelWidth", "resetStartPanelWidth", "setChildGestureRegions", "setEndPanelLockState", "lockState", "setStartPanelLockState", "setStartPanelUseFullPortraitWidth", "useFullPortraitWidth", "shouldHandleActionMoveEvent", "snapOpenOrClose", "translateCenterPanel", "updateCenterPanelX", "updateCenterPanelXWithAnimation", "animationDurationMs", "", "Companion", "LockState", "Panel", "PanelStateListener", "SwipeDirection", "overlapping_panels_release"}, k = 1, mv = {1, 1, 16})
public class OverlappingPanelsLayout extends FrameLayout {
    private static final long SIDE_PANEL_CLOSE_DURATION_MS = 200;
    private static final long SIDE_PANEL_OPEN_DURATION_MS = 250;
    private HashMap _$_findViewCache;
    private View centerPanel;
    private float centerPanelAnimationEndX;
    private float centerPanelDiffX;
    private ValueAnimator centerPanelXAnimator;
    private List<Rect> childGestureRegions;
    private View endPanel;
    private LockState endPanelLockState;
    private float endPanelOpenedCenterPanelX;
    private PanelState endPanelState;
    private final ArrayList<PanelStateListener> endPanelStateListeners;
    private float homeGestureFromBottomThreshold;
    private boolean isHomeSystemGesture;
    private boolean isLeftToRight;
    private boolean isScrollingHorizontally;
    private final boolean isSystemGestureNavigationPossible;
    private float minFlingPxPerSecond;
    private int nonFullScreenSidePanelWidth;
    private Function0<Unit> pendingUpdate;
    private float scrollingSlopPx;
    private Panel selectedPanel;
    private View startPanel;
    private LockState startPanelLockState;
    private float startPanelOpenedCenterPanelX;
    private PanelState startPanelState;
    private final ArrayList<PanelStateListener> startPanelStateListeners;
    private SwipeDirection swipeDirection;
    private boolean useFullWidthForStartPanel;
    private VelocityTracker velocityTracker;
    private boolean wasActionDownOnClosedCenterPanel;
    private float xFromInterceptActionDown;
    private float yFromInterceptActionDown;

    /* JADX INFO: compiled from: OverlappingPanelsLayout.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/discord/panels/OverlappingPanelsLayout$LockState;", "", "(Ljava/lang/String;I)V", "OPEN", "CLOSE", "UNLOCKED", "overlapping_panels_release"}, k = 1, mv = {1, 1, 16})
    public enum LockState {
        OPEN,
        CLOSE,
        UNLOCKED
    }

    /* JADX INFO: compiled from: OverlappingPanelsLayout.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/discord/panels/OverlappingPanelsLayout$Panel;", "", "(Ljava/lang/String;I)V", "START", "CENTER", "END", "overlapping_panels_release"}, k = 1, mv = {1, 1, 16})
    public enum Panel {
        START,
        CENTER,
        END
    }

    /* JADX INFO: compiled from: OverlappingPanelsLayout.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/discord/panels/OverlappingPanelsLayout$PanelStateListener;", "", "onPanelStateChange", "", "panelState", "Lcom/discord/panels/PanelState;", "overlapping_panels_release"}, k = 1, mv = {1, 1, 16})
    public interface PanelStateListener {
        void onPanelStateChange(PanelState panelState);
    }

    /* JADX INFO: compiled from: OverlappingPanelsLayout.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/discord/panels/OverlappingPanelsLayout$SwipeDirection;", "", "(Ljava/lang/String;I)V", "LEFT", "RIGHT", "overlapping_panels_release"}, k = 1, mv = {1, 1, 16})
    public enum SwipeDirection {
        LEFT,
        RIGHT
    }

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Panel.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[Panel.START.ordinal()] = 1;
            iArr[Panel.END.ordinal()] = 2;
            iArr[Panel.CENTER.ordinal()] = 3;
        }
    }

    public void _$_clearFindViewByIdCache() {
        HashMap map = this._$_findViewCache;
        if (map != null) {
            map.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View viewFindViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    public static final /* synthetic */ View access$getCenterPanel$p(OverlappingPanelsLayout overlappingPanelsLayout) {
        View view = overlappingPanelsLayout.centerPanel;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("centerPanel");
        }
        return view;
    }

    public static final /* synthetic */ View access$getStartPanel$p(OverlappingPanelsLayout overlappingPanelsLayout) {
        View view = overlappingPanelsLayout.startPanel;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("startPanel");
        }
        return view;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OverlappingPanelsLayout(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.isLeftToRight = true;
        this.startPanelOpenedCenterPanelX = FloatCompanionObject.INSTANCE.getMIN_VALUE();
        this.endPanelOpenedCenterPanelX = FloatCompanionObject.INSTANCE.getMAX_VALUE();
        this.startPanelStateListeners = new ArrayList<>();
        this.endPanelStateListeners = new ArrayList<>();
        this.selectedPanel = Panel.CENTER;
        this.startPanelLockState = LockState.UNLOCKED;
        this.endPanelLockState = LockState.UNLOCKED;
        this.startPanelState = PanelState.Closed.INSTANCE;
        this.endPanelState = PanelState.Closed.INSTANCE;
        this.childGestureRegions = CollectionsKt.emptyList();
        this.isSystemGestureNavigationPossible = Build.VERSION.SDK_INT >= 29;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OverlappingPanelsLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.isLeftToRight = true;
        this.startPanelOpenedCenterPanelX = FloatCompanionObject.INSTANCE.getMIN_VALUE();
        this.endPanelOpenedCenterPanelX = FloatCompanionObject.INSTANCE.getMAX_VALUE();
        this.startPanelStateListeners = new ArrayList<>();
        this.endPanelStateListeners = new ArrayList<>();
        this.selectedPanel = Panel.CENTER;
        this.startPanelLockState = LockState.UNLOCKED;
        this.endPanelLockState = LockState.UNLOCKED;
        this.startPanelState = PanelState.Closed.INSTANCE;
        this.endPanelState = PanelState.Closed.INSTANCE;
        this.childGestureRegions = CollectionsKt.emptyList();
        this.isSystemGestureNavigationPossible = Build.VERSION.SDK_INT >= 29;
        initialize(attributeSet);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ OverlappingPanelsLayout(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 2) != 0) {
            attributeSet = null;
        }
        this(context, attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OverlappingPanelsLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.isLeftToRight = true;
        this.startPanelOpenedCenterPanelX = FloatCompanionObject.INSTANCE.getMIN_VALUE();
        this.endPanelOpenedCenterPanelX = FloatCompanionObject.INSTANCE.getMAX_VALUE();
        this.startPanelStateListeners = new ArrayList<>();
        this.endPanelStateListeners = new ArrayList<>();
        this.selectedPanel = Panel.CENTER;
        this.startPanelLockState = LockState.UNLOCKED;
        this.endPanelLockState = LockState.UNLOCKED;
        this.startPanelState = PanelState.Closed.INSTANCE;
        this.endPanelState = PanelState.Closed.INSTANCE;
        this.childGestureRegions = CollectionsKt.emptyList();
        this.isSystemGestureNavigationPossible = Build.VERSION.SDK_INT >= 29;
        initialize(attributeSet);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ OverlappingPanelsLayout(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i2 & 2) != 0) {
            attributeSet = null;
        }
        this(context, attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    private final void initialize(AttributeSet attrs) {
        int i;
        LocaleProvider localeProvider = LocaleProvider.INSTANCE;
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        this.isLeftToRight = TextUtils.getLayoutDirectionFromLocale(localeProvider.getPrimaryLocale(context)) == 0;
        this.scrollingSlopPx = getResources().getDimension(R.dimen.overlapping_panels_scroll_slop);
        this.homeGestureFromBottomThreshold = getResources().getDimension(R.dimen.overlapping_panels_home_gesture_from_bottom_threshold);
        this.minFlingPxPerSecond = getResources().getDimension(R.dimen.overlapping_panels_min_fling_dp_per_second);
        Resources resources = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        if (resources.getConfiguration().orientation == 1) {
            Resources resources2 = getResources();
            Intrinsics.checkExpressionValueIsNotNull(resources2, "resources");
            i = resources2.getDisplayMetrics().widthPixels;
        } else {
            Resources resources3 = getResources();
            Intrinsics.checkExpressionValueIsNotNull(resources3, "resources");
            i = resources3.getDisplayMetrics().heightPixels;
        }
        this.nonFullScreenSidePanelWidth = (int) ((i - getResources().getDimension(R.dimen.overlapping_panels_margin_between_panels)) - getResources().getDimension(R.dimen.overlapping_panels_closed_center_panel_visible_width));
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attrs, R.styleable.OverlappingPanelsLayout, 0, 0);
        try {
            this.nonFullScreenSidePanelWidth = Math.min(this.nonFullScreenSidePanelWidth, (int) typedArrayObtainStyledAttributes.getDimension(R.styleable.OverlappingPanelsLayout_maxSidePanelNonFullScreenWidth, 2.1474836E9f));
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (getChildCount() == 3 && this.centerPanel == null) {
            initPanels();
        }
    }

    /* JADX INFO: renamed from: com.discord.panels.OverlappingPanelsLayout$onLayout$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: OverlappingPanelsLayout.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    final /* synthetic */ class C06831 extends MutablePropertyReference0 {
        C06831(OverlappingPanelsLayout overlappingPanelsLayout) {
            super(overlappingPanelsLayout);
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        public String getName() {
            return "centerPanel";
        }

        @Override // kotlin.jvm.internal.CallableReference
        public KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinClass(OverlappingPanelsLayout.class);
        }

        @Override // kotlin.jvm.internal.CallableReference
        public String getSignature() {
            return "getCenterPanel()Landroid/view/View;";
        }

        @Override // kotlin.reflect.KProperty0
        public Object get() {
            return OverlappingPanelsLayout.access$getCenterPanel$p((OverlappingPanelsLayout) this.receiver);
        }

        @Override // kotlin.reflect.KMutableProperty0
        public void set(Object obj) {
            ((OverlappingPanelsLayout) this.receiver).centerPanel = (View) obj;
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        int actionMasked = event.getActionMasked();
        boolean z = false;
        if (actionMasked == 0) {
            this.isScrollingHorizontally = false;
            this.wasActionDownOnClosedCenterPanel = isTouchingCenterPanelWhileSidePanelOpen(event);
            View view = this.centerPanel;
            if (view == null) {
                Intrinsics.throwUninitializedPropertyAccessException("centerPanel");
            }
            this.centerPanelDiffX = view.getX() - event.getRawX();
            this.xFromInterceptActionDown = event.getX();
            float y = event.getY();
            this.yFromInterceptActionDown = y;
            Resources resources = getResources();
            Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
            if (Math.abs(y - resources.getDisplayMetrics().heightPixels) < this.homeGestureFromBottomThreshold && this.isSystemGestureNavigationPossible) {
                z = true;
            }
            this.isHomeSystemGesture = z;
            VelocityTracker velocityTracker = this.velocityTracker;
            if (velocityTracker == null) {
                VelocityTracker velocityTrackerObtain = VelocityTracker.obtain();
                this.velocityTracker = velocityTrackerObtain;
                if (velocityTrackerObtain != null) {
                    velocityTrackerObtain.addMovement(event);
                }
            } else if (velocityTracker != null) {
                velocityTracker.clear();
            }
            return this.wasActionDownOnClosedCenterPanel;
        }
        if (actionMasked != 1) {
            if (actionMasked == 2) {
                if (this.isScrollingHorizontally) {
                    return true;
                }
                float fCalculateDistanceX = calculateDistanceX(this.xFromInterceptActionDown, event);
                float fCalculateDistanceY = calculateDistanceY(this.yFromInterceptActionDown, event);
                boolean zIsTouchingChildGestureRegion = isTouchingChildGestureRegion(event);
                if (Math.abs(fCalculateDistanceX) <= this.scrollingSlopPx || Math.abs(fCalculateDistanceX) <= Math.abs(fCalculateDistanceY) || zIsTouchingChildGestureRegion) {
                    return false;
                }
                this.isScrollingHorizontally = true;
                return true;
            }
            if (actionMasked != 3) {
                return this.wasActionDownOnClosedCenterPanel;
            }
        }
        VelocityTracker velocityTracker2 = this.velocityTracker;
        if (velocityTracker2 != null) {
            velocityTracker2.recycle();
        }
        this.velocityTracker = null;
        return this.isScrollingHorizontally || this.wasActionDownOnClosedCenterPanel;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        if (this.isHomeSystemGesture) {
            return false;
        }
        int actionMasked = event.getActionMasked();
        if (actionMasked != 0) {
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    if (isTouchingChildGestureRegion(event)) {
                        return false;
                    }
                    float fCalculateDistanceX = calculateDistanceX(this.xFromInterceptActionDown, event);
                    if (Math.abs(fCalculateDistanceX) > this.scrollingSlopPx && this.swipeDirection == null) {
                        this.swipeDirection = fCalculateDistanceX > 0.0f ? SwipeDirection.RIGHT : SwipeDirection.LEFT;
                    }
                    VelocityTracker velocityTracker = this.velocityTracker;
                    if (velocityTracker != null) {
                        velocityTracker.addMovement(event);
                    }
                    if (shouldHandleActionMoveEvent(event)) {
                        translateCenterPanel(event);
                    }
                    return true;
                }
                if (actionMasked != 3) {
                    return false;
                }
            }
            if (this.wasActionDownOnClosedCenterPanel && Math.abs(event.getX() - this.xFromInterceptActionDown) < this.scrollingSlopPx && !this.isScrollingHorizontally) {
                closePanels();
            } else {
                VelocityTracker velocityTracker2 = this.velocityTracker;
                if (velocityTracker2 != null) {
                    velocityTracker2.addMovement(event);
                }
                snapOpenOrClose(event);
            }
            this.wasActionDownOnClosedCenterPanel = false;
            this.isScrollingHorizontally = false;
            this.swipeDirection = null;
        }
        return true;
    }

    public final void openStartPanel() {
        openStartPanel(false);
    }

    public final void openEndPanel() {
        openEndPanel(false);
    }

    public final void closePanels() {
        closePanels(false);
    }

    public final void setStartPanelLockState(LockState lockState) {
        Intrinsics.checkParameterIsNotNull(lockState, "lockState");
        this.startPanelLockState = lockState;
        if (lockState == LockState.OPEN) {
            openStartPanel();
        }
    }

    public final void setEndPanelLockState(LockState lockState) {
        Intrinsics.checkParameterIsNotNull(lockState, "lockState");
        this.endPanelLockState = lockState;
        if (lockState == LockState.OPEN) {
            openEndPanel();
        }
    }

    public final void setStartPanelUseFullPortraitWidth(boolean useFullPortraitWidth) {
        this.useFullWidthForStartPanel = useFullPortraitWidth;
        resetStartPanelWidth();
    }

    public final void setChildGestureRegions(List<Rect> childGestureRegions) {
        Intrinsics.checkParameterIsNotNull(childGestureRegions, "childGestureRegions");
        this.childGestureRegions = childGestureRegions;
    }

    public final void registerStartPanelStateListeners(PanelStateListener... panelStateListenerArgs) {
        Intrinsics.checkParameterIsNotNull(panelStateListenerArgs, "panelStateListenerArgs");
        for (PanelStateListener panelStateListener : panelStateListenerArgs) {
            this.startPanelStateListeners.add(panelStateListener);
        }
    }

    public final void registerEndPanelStateListeners(PanelStateListener... panelStateListenerArgs) {
        Intrinsics.checkParameterIsNotNull(panelStateListenerArgs, "panelStateListenerArgs");
        for (PanelStateListener panelStateListener : panelStateListenerArgs) {
            this.endPanelStateListeners.add(panelStateListener);
        }
    }

    public final Panel getSelectedPanel() {
        return this.selectedPanel;
    }

    public final void handleStartPanelState(PanelState startPanelState) {
        Intrinsics.checkParameterIsNotNull(startPanelState, "startPanelState");
        PanelState panelState = this.startPanelState;
        if (Intrinsics.areEqual(startPanelState, PanelState.Opened.INSTANCE) && !Intrinsics.areEqual(panelState, PanelState.Opened.INSTANCE)) {
            openStartPanel();
        } else if (Intrinsics.areEqual(startPanelState, PanelState.Closed.INSTANCE) && Intrinsics.areEqual(panelState, PanelState.Opened.INSTANCE)) {
            closePanels();
        }
        this.startPanelState = startPanelState;
    }

    public final void handleEndPanelState(PanelState endPanelState) {
        Intrinsics.checkParameterIsNotNull(endPanelState, "endPanelState");
        PanelState panelState = this.endPanelState;
        if (Intrinsics.areEqual(endPanelState, PanelState.Opened.INSTANCE) && !Intrinsics.areEqual(panelState, PanelState.Opened.INSTANCE)) {
            openEndPanel();
        } else if ((endPanelState instanceof PanelState.Closed) && Intrinsics.areEqual(panelState, PanelState.Opened.INSTANCE)) {
            closePanels();
        }
        this.endPanelState = endPanelState;
    }

    /* JADX INFO: renamed from: com.discord.panels.OverlappingPanelsLayout$resetStartPanelWidth$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: OverlappingPanelsLayout.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    final /* synthetic */ class C06881 extends MutablePropertyReference0 {
        C06881(OverlappingPanelsLayout overlappingPanelsLayout) {
            super(overlappingPanelsLayout);
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        public String getName() {
            return "startPanel";
        }

        @Override // kotlin.jvm.internal.CallableReference
        public KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinClass(OverlappingPanelsLayout.class);
        }

        @Override // kotlin.jvm.internal.CallableReference
        public String getSignature() {
            return "getStartPanel()Landroid/view/View;";
        }

        @Override // kotlin.reflect.KProperty0
        public Object get() {
            return OverlappingPanelsLayout.access$getStartPanel$p((OverlappingPanelsLayout) this.receiver);
        }

        @Override // kotlin.reflect.KMutableProperty0
        public void set(Object obj) {
            ((OverlappingPanelsLayout) this.receiver).startPanel = (View) obj;
        }
    }

    private final void resetStartPanelWidth() {
        if (this.startPanel != null) {
            View view = this.startPanel;
            if (view == null) {
                Intrinsics.throwUninitializedPropertyAccessException("startPanel");
            }
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.width = this.useFullWidthForStartPanel ? -1 : this.nonFullScreenSidePanelWidth;
            View view2 = this.startPanel;
            if (view2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("startPanel");
            }
            view2.setLayoutParams(layoutParams);
        }
    }

    private final void resetEndPanelWidth() {
        View view = this.endPanel;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("endPanel");
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = this.nonFullScreenSidePanelWidth;
        View view2 = this.endPanel;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("endPanel");
        }
        view2.setLayoutParams(layoutParams);
    }

    private final void openPanel(Panel panel) {
        int i = WhenMappings.$EnumSwitchMapping$0[panel.ordinal()];
        if (i == 1) {
            openStartPanel(false);
        } else if (i == 2) {
            openEndPanel(false);
        } else {
            if (i != 3) {
                return;
            }
            closePanels(false);
        }
    }

    static /* synthetic */ void openStartPanel$default(OverlappingPanelsLayout overlappingPanelsLayout, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: openStartPanel");
        }
        if ((i & 1) != 0) {
            z = false;
        }
        overlappingPanelsLayout.openStartPanel(z);
    }

    /* JADX INFO: renamed from: com.discord.panels.OverlappingPanelsLayout$openStartPanel$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: OverlappingPanelsLayout.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    final /* synthetic */ class C06861 extends MutablePropertyReference0 {
        C06861(OverlappingPanelsLayout overlappingPanelsLayout) {
            super(overlappingPanelsLayout);
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        public String getName() {
            return "centerPanel";
        }

        @Override // kotlin.jvm.internal.CallableReference
        public KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinClass(OverlappingPanelsLayout.class);
        }

        @Override // kotlin.jvm.internal.CallableReference
        public String getSignature() {
            return "getCenterPanel()Landroid/view/View;";
        }

        @Override // kotlin.reflect.KProperty0
        public Object get() {
            return OverlappingPanelsLayout.access$getCenterPanel$p((OverlappingPanelsLayout) this.receiver);
        }

        @Override // kotlin.reflect.KMutableProperty0
        public void set(Object obj) {
            ((OverlappingPanelsLayout) this.receiver).centerPanel = (View) obj;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void openStartPanel(final boolean isFling) {
        if (this.centerPanel == null) {
            this.pendingUpdate = new Function0<Unit>() { // from class: com.discord.panels.OverlappingPanelsLayout.openStartPanel.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    OverlappingPanelsLayout.this.openStartPanel(isFling);
                }
            };
            return;
        }
        LockState lockState = this.startPanelLockState;
        LockState lockState2 = LockState.OPEN;
        float f = this.startPanelOpenedCenterPanelX;
        if (lockState == lockState2) {
            updateCenterPanelX(f);
        } else {
            updateCenterPanelXWithAnimation(f, isFling, SIDE_PANEL_OPEN_DURATION_MS);
        }
    }

    static /* synthetic */ void openEndPanel$default(OverlappingPanelsLayout overlappingPanelsLayout, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: openEndPanel");
        }
        if ((i & 1) != 0) {
            z = false;
        }
        overlappingPanelsLayout.openEndPanel(z);
    }

    /* JADX INFO: renamed from: com.discord.panels.OverlappingPanelsLayout$openEndPanel$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: OverlappingPanelsLayout.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    final /* synthetic */ class C06841 extends MutablePropertyReference0 {
        C06841(OverlappingPanelsLayout overlappingPanelsLayout) {
            super(overlappingPanelsLayout);
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        public String getName() {
            return "centerPanel";
        }

        @Override // kotlin.jvm.internal.CallableReference
        public KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinClass(OverlappingPanelsLayout.class);
        }

        @Override // kotlin.jvm.internal.CallableReference
        public String getSignature() {
            return "getCenterPanel()Landroid/view/View;";
        }

        @Override // kotlin.reflect.KProperty0
        public Object get() {
            return OverlappingPanelsLayout.access$getCenterPanel$p((OverlappingPanelsLayout) this.receiver);
        }

        @Override // kotlin.reflect.KMutableProperty0
        public void set(Object obj) {
            ((OverlappingPanelsLayout) this.receiver).centerPanel = (View) obj;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void openEndPanel(final boolean isFling) {
        if (this.centerPanel == null) {
            this.pendingUpdate = new Function0<Unit>() { // from class: com.discord.panels.OverlappingPanelsLayout.openEndPanel.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    OverlappingPanelsLayout.this.openEndPanel(isFling);
                }
            };
        } else {
            updateCenterPanelXWithAnimation(this.endPanelOpenedCenterPanelX, isFling, SIDE_PANEL_OPEN_DURATION_MS);
        }
    }

    static /* synthetic */ void closePanels$default(OverlappingPanelsLayout overlappingPanelsLayout, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: closePanels");
        }
        if ((i & 1) != 0) {
            z = false;
        }
        overlappingPanelsLayout.closePanels(z);
    }

    /* JADX INFO: renamed from: com.discord.panels.OverlappingPanelsLayout$closePanels$1, reason: invalid class name */
    /* JADX INFO: compiled from: OverlappingPanelsLayout.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    final /* synthetic */ class AnonymousClass1 extends MutablePropertyReference0 {
        AnonymousClass1(OverlappingPanelsLayout overlappingPanelsLayout) {
            super(overlappingPanelsLayout);
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        public String getName() {
            return "centerPanel";
        }

        @Override // kotlin.jvm.internal.CallableReference
        public KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinClass(OverlappingPanelsLayout.class);
        }

        @Override // kotlin.jvm.internal.CallableReference
        public String getSignature() {
            return "getCenterPanel()Landroid/view/View;";
        }

        @Override // kotlin.reflect.KProperty0
        public Object get() {
            return OverlappingPanelsLayout.access$getCenterPanel$p((OverlappingPanelsLayout) this.receiver);
        }

        @Override // kotlin.reflect.KMutableProperty0
        public void set(Object obj) {
            ((OverlappingPanelsLayout) this.receiver).centerPanel = (View) obj;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void closePanels(final boolean isFling) {
        if (this.centerPanel == null) {
            this.pendingUpdate = new Function0<Unit>() { // from class: com.discord.panels.OverlappingPanelsLayout.closePanels.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    OverlappingPanelsLayout.this.closePanels(isFling);
                }
            };
        } else {
            updateCenterPanelXWithAnimation(0.0f, isFling, 200L);
        }
    }

    private final void snapOpenOrClose(MotionEvent event) {
        float targetedX = getTargetedX(event);
        VelocityTracker velocityTracker = this.velocityTracker;
        if (velocityTracker != null) {
            velocityTracker.computeCurrentVelocity(1000);
        }
        VelocityTracker velocityTracker2 = this.velocityTracker;
        float xVelocity = velocityTracker2 != null ? velocityTracker2.getXVelocity() : FloatCompanionObject.INSTANCE.getMIN_VALUE();
        boolean z = false;
        boolean z2 = Math.abs(xVelocity) > this.minFlingPxPerSecond;
        if (!this.isLeftToRight ? xVelocity < 0.0f : xVelocity > 0.0f) {
            z = true;
        }
        if (z2) {
            Panel panel = this.selectedPanel;
            if (z) {
                if (panel == Panel.END) {
                    closePanels(true);
                    return;
                } else if (this.selectedPanel == Panel.CENTER) {
                    openStartPanel(true);
                    return;
                }
            } else if (panel == Panel.START) {
                closePanels(true);
                return;
            } else if (this.selectedPanel == Panel.CENTER) {
                openEndPanel(true);
                return;
            }
        }
        float fMax = Math.max(this.startPanelOpenedCenterPanelX, this.endPanelOpenedCenterPanelX);
        float fMin = Math.min(this.startPanelOpenedCenterPanelX, this.endPanelOpenedCenterPanelX);
        if (targetedX > fMax / 2.0f) {
            openPanel(getLeftPanel());
        } else if (targetedX < fMin / 2.0f) {
            openPanel(getRightPanel());
        } else {
            closePanels();
        }
    }

    private final Panel getLeftPanel() {
        return this.isLeftToRight ? Panel.START : Panel.END;
    }

    private final Panel getRightPanel() {
        return this.isLeftToRight ? Panel.END : Panel.START;
    }

    private final LockState getLeftPanelLockState() {
        return this.isLeftToRight ? this.startPanelLockState : this.endPanelLockState;
    }

    private final LockState getRightPanelLockState() {
        return this.isLeftToRight ? this.endPanelLockState : this.startPanelLockState;
    }

    private final void translateCenterPanel(MotionEvent event) {
        updateCenterPanelX(getNormalizedX(getTargetedX(event)));
    }

    private final float getNormalizedX(float targetedX) {
        if (this.startPanelLockState == LockState.OPEN) {
            return this.startPanelOpenedCenterPanelX;
        }
        if (this.endPanelLockState == LockState.OPEN) {
            return this.endPanelOpenedCenterPanelX;
        }
        float fMin = 0.0f;
        float fMax = (getLeftPanelLockState() == LockState.CLOSE || (this.selectedPanel == Panel.CENTER && this.swipeDirection == SwipeDirection.LEFT)) ? 0.0f : Math.max(this.startPanelOpenedCenterPanelX, this.endPanelOpenedCenterPanelX);
        if (getRightPanelLockState() != LockState.CLOSE && (this.selectedPanel != Panel.CENTER || this.swipeDirection != SwipeDirection.RIGHT)) {
            fMin = Math.min(this.startPanelOpenedCenterPanelX, this.endPanelOpenedCenterPanelX);
        }
        if (targetedX > fMax) {
            return fMax;
        }
        return targetedX < fMin ? fMin : targetedX;
    }

    private final boolean shouldHandleActionMoveEvent(MotionEvent event) {
        float normalizedX = getNormalizedX(getTargetedX(event));
        View view = this.centerPanel;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("centerPanel");
        }
        float fAbs = Math.abs(normalizedX - view.getX());
        Resources resources = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        return normalizedX == 0.0f || normalizedX == this.startPanelOpenedCenterPanelX || normalizedX == this.endPanelOpenedCenterPanelX || ((fAbs > resources.getDisplayMetrics().density ? 1 : (fAbs == resources.getDisplayMetrics().density ? 0 : -1)) > 0);
    }

    private final float getTargetedX(MotionEvent event) {
        return event.getRawX() + this.centerPanelDiffX;
    }

    private final float calculateDistanceX(float startX, MotionEvent event) {
        return event.getX() - startX;
    }

    private final float calculateDistanceY(float startY, MotionEvent event) {
        return event.getY() - startY;
    }

    static /* synthetic */ void updateCenterPanelXWithAnimation$default(OverlappingPanelsLayout overlappingPanelsLayout, float f, boolean z, long j, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: updateCenterPanelXWithAnimation");
        }
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            j = SIDE_PANEL_OPEN_DURATION_MS;
        }
        overlappingPanelsLayout.updateCenterPanelXWithAnimation(f, z, j);
    }

    private final void updateCenterPanelXWithAnimation(float x, boolean isFling, long animationDurationMs) {
        View view = this.centerPanel;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("centerPanel");
        }
        float x2 = view.getX();
        ValueAnimator valueAnimator = this.centerPanelXAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        float normalizedX = getNormalizedX(x);
        this.centerPanelAnimationEndX = normalizedX;
        if (isFling) {
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(x2, normalizedX);
            valueAnimatorOfFloat.setInterpolator(new LinearOutSlowInInterpolator());
            valueAnimatorOfFloat.setDuration(animationDurationMs);
            this.centerPanelXAnimator = valueAnimatorOfFloat;
            if (valueAnimatorOfFloat != null) {
                valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.discord.panels.OverlappingPanelsLayout.updateCenterPanelXWithAnimation.2
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public final void onAnimationUpdate(ValueAnimator animator) {
                        OverlappingPanelsLayout overlappingPanelsLayout = OverlappingPanelsLayout.this;
                        Intrinsics.checkExpressionValueIsNotNull(animator, "animator");
                        Object animatedValue = animator.getAnimatedValue();
                        if (animatedValue == null) {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.Float");
                        }
                        overlappingPanelsLayout.updateCenterPanelX(((Float) animatedValue).floatValue());
                    }
                });
            }
        } else {
            ValueAnimator valueAnimatorOfFloat2 = ValueAnimator.ofFloat(x2, normalizedX);
            valueAnimatorOfFloat2.setInterpolator(new FastOutSlowInInterpolator());
            valueAnimatorOfFloat2.setDuration(animationDurationMs);
            this.centerPanelXAnimator = valueAnimatorOfFloat2;
            if (valueAnimatorOfFloat2 != null) {
                valueAnimatorOfFloat2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.discord.panels.OverlappingPanelsLayout.updateCenterPanelXWithAnimation.4
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public final void onAnimationUpdate(ValueAnimator animator) {
                        OverlappingPanelsLayout overlappingPanelsLayout = OverlappingPanelsLayout.this;
                        Intrinsics.checkExpressionValueIsNotNull(animator, "animator");
                        Object animatedValue = animator.getAnimatedValue();
                        if (animatedValue == null) {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.Float");
                        }
                        overlappingPanelsLayout.updateCenterPanelX(((Float) animatedValue).floatValue());
                    }
                });
            }
        }
        ValueAnimator valueAnimator2 = this.centerPanelXAnimator;
        if (valueAnimator2 != null) {
            valueAnimator2.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateCenterPanelX(float x) {
        View view = this.centerPanel;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("centerPanel");
        }
        float x2 = view.getX();
        View view2 = this.centerPanel;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("centerPanel");
        }
        view2.setX(x);
        handleCenterPanelX(x2, x);
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0021  */
    /* JADX WARN: Code duplicated, block: B:14:0x0025  */
    /* JADX WARN: Code duplicated, block: B:16:0x0029  */
    /* JADX WARN: Code duplicated, block: B:19:0x0034  */
    /* JADX WARN: Code duplicated, block: B:31:0x0056  */
    /* JADX WARN: Code duplicated, block: B:33:0x005a  */
    /* JADX WARN: Code duplicated, block: B:35:0x005e  */
    /* JADX WARN: Code duplicated, block: B:38:0x0069  */
    private final void handleCenterPanelX(float previousX, float x) {
        int i;
        View view;
        int i2;
        View view2;
        View view3 = this.startPanel;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("startPanel");
        }
        if (this.isLeftToRight) {
            View view4 = this.centerPanel;
            if (view4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("centerPanel");
            }
            if (view4.getX() <= 0.0f) {
                if (!this.isLeftToRight) {
                    view = this.centerPanel;
                    if (view == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("centerPanel");
                    }
                    if (view.getX() < 0.0f) {
                        i = 0;
                    }
                }
                i = 4;
            } else {
                i = 0;
            }
        } else {
            if (!this.isLeftToRight) {
                view = this.centerPanel;
                if (view == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("centerPanel");
                }
                if (view.getX() < 0.0f) {
                    i = 0;
                }
            }
            i = 4;
        }
        view3.setVisibility(i);
        View view5 = this.endPanel;
        if (view5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("endPanel");
        }
        if (this.isLeftToRight) {
            View view6 = this.centerPanel;
            if (view6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("centerPanel");
            }
            if (view6.getX() >= 0.0f) {
                if (!this.isLeftToRight) {
                    view2 = this.centerPanel;
                    if (view2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("centerPanel");
                    }
                    if (view2.getX() > 0.0f) {
                        i2 = 0;
                    }
                }
                i2 = 4;
            } else {
                i2 = 0;
            }
        } else {
            if (!this.isLeftToRight) {
                view2 = this.centerPanel;
                if (view2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("centerPanel");
                }
                if (view2.getX() > 0.0f) {
                    i2 = 0;
                }
            }
            i2 = 4;
        }
        view5.setVisibility(i2);
        if (x == 0.0f) {
            this.selectedPanel = Panel.CENTER;
        } else if (x == this.startPanelOpenedCenterPanelX) {
            this.selectedPanel = Panel.START;
        } else if (x == this.endPanelOpenedCenterPanelX) {
            this.selectedPanel = Panel.END;
        }
        boolean z = x == this.endPanelOpenedCenterPanelX || x == this.startPanelOpenedCenterPanelX;
        View view7 = this.centerPanel;
        if (view7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("centerPanel");
        }
        ViewExtensionsKt.setEnabledAlpha(view7, !z, 0.5f);
        View view8 = this.centerPanel;
        if (view8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("centerPanel");
        }
        view8.setImportantForAccessibility(z ? 4 : 0);
        boolean z2 = x == 0.0f || z;
        View view9 = this.centerPanel;
        if (view9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("centerPanel");
        }
        view9.setElevation(z2 ? 0.0f : getResources().getDimension(R.dimen.overlapping_panels_center_panel_non_resting_elevation));
        this.startPanelState = getStartPanelState(previousX, x);
        Iterator<PanelStateListener> it = this.startPanelStateListeners.iterator();
        while (it.hasNext()) {
            it.next().onPanelStateChange(this.startPanelState);
        }
        this.endPanelState = getEndPanelState(previousX, x);
        Iterator<PanelStateListener> it2 = this.endPanelStateListeners.iterator();
        while (it2.hasNext()) {
            it2.next().onPanelStateChange(this.endPanelState);
        }
    }

    private final PanelState getStartPanelState(float previousX, float x) {
        boolean z = this.isLeftToRight;
        if (z && x <= 0.0f) {
            return PanelState.Closed.INSTANCE;
        }
        if (!z && x >= 0.0f) {
            return PanelState.Closed.INSTANCE;
        }
        if (x == this.startPanelOpenedCenterPanelX) {
            return PanelState.Opened.INSTANCE;
        }
        if (z && x > previousX) {
            return PanelState.Opening.INSTANCE;
        }
        if (!z && x < previousX) {
            return PanelState.Opening.INSTANCE;
        }
        return PanelState.Closing.INSTANCE;
    }

    private final PanelState getEndPanelState(float previousX, float x) {
        boolean z = this.isLeftToRight;
        if (z && x >= 0.0f) {
            return PanelState.Closed.INSTANCE;
        }
        if (!z && x <= 0.0f) {
            return PanelState.Closed.INSTANCE;
        }
        if (x == this.endPanelOpenedCenterPanelX) {
            return PanelState.Opened.INSTANCE;
        }
        if (z && x < previousX) {
            return PanelState.Opening.INSTANCE;
        }
        if (!z && x > previousX) {
            return PanelState.Opening.INSTANCE;
        }
        return PanelState.Closing.INSTANCE;
    }

    private final void initPanels() {
        View childAt = getChildAt(0);
        Intrinsics.checkExpressionValueIsNotNull(childAt, "getChildAt(0)");
        this.startPanel = childAt;
        View childAt2 = getChildAt(1);
        Intrinsics.checkExpressionValueIsNotNull(childAt2, "getChildAt(1)");
        this.centerPanel = childAt2;
        View childAt3 = getChildAt(2);
        Intrinsics.checkExpressionValueIsNotNull(childAt3, "getChildAt(2)");
        this.endPanel = childAt3;
        View view = this.startPanel;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("startPanel");
        }
        view.setVisibility(4);
        View view2 = this.startPanel;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("startPanel");
        }
        view2.setElevation(0.0f);
        View view3 = this.centerPanel;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("centerPanel");
        }
        view3.setVisibility(0);
        View view4 = this.centerPanel;
        if (view4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("centerPanel");
        }
        view4.setElevation(0.0f);
        View view5 = this.endPanel;
        if (view5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("endPanel");
        }
        view5.setVisibility(4);
        View view6 = this.endPanel;
        if (view6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("endPanel");
        }
        view6.setElevation(0.0f);
        resetStartPanelWidth();
        resetEndPanelWidth();
        handleStartPanelWidthUpdate();
        handleEndPanelWidthUpdate();
        Function0<Unit> function0 = this.pendingUpdate;
        if (function0 != null) {
            function0.invoke();
        }
        this.pendingUpdate = null;
        View view7 = this.startPanel;
        if (view7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("startPanel");
        }
        view7.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.discord.panels.OverlappingPanelsLayout.initPanels.1
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view8, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                if (!OverlappingPanelsLayout.this.isLeftToRight || i3 == i7) {
                    if (OverlappingPanelsLayout.this.isLeftToRight || i == i5) {
                        return;
                    }
                    OverlappingPanelsLayout.this.handleStartPanelWidthUpdate();
                    return;
                }
                OverlappingPanelsLayout.this.handleStartPanelWidthUpdate();
            }
        });
        View view8 = this.endPanel;
        if (view8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("endPanel");
        }
        view8.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.discord.panels.OverlappingPanelsLayout.initPanels.2
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view9, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                if (!OverlappingPanelsLayout.this.isLeftToRight || i == i5) {
                    if (OverlappingPanelsLayout.this.isLeftToRight || i3 == i7) {
                        return;
                    }
                    OverlappingPanelsLayout.this.handleEndPanelWidthUpdate();
                    return;
                }
                OverlappingPanelsLayout.this.handleEndPanelWidthUpdate();
            }
        });
    }

    private final boolean isTouchingCenterPanelWhileSidePanelOpen(MotionEvent event) {
        float x = event.getX();
        View view = this.centerPanel;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("centerPanel");
        }
        float x2 = view.getX();
        float fMax = Math.max(this.startPanelOpenedCenterPanelX, this.endPanelOpenedCenterPanelX);
        float fMin = Math.min(this.startPanelOpenedCenterPanelX, this.endPanelOpenedCenterPanelX);
        View view2 = this.centerPanel;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("centerPanel");
        }
        return (((x2 > fMax ? 1 : (x2 == fMax ? 0 : -1)) == 0) && ((x > fMax ? 1 : (x == fMax ? 0 : -1)) > 0)) || (((x2 > fMin ? 1 : (x2 == fMin ? 0 : -1)) == 0) && ((x > (view2.getWidth() + fMin) ? 1 : (x == (view2.getWidth() + fMin) ? 0 : -1)) < 0));
    }

    private final boolean isTouchingChildGestureRegion(MotionEvent event) {
        float rawX = event.getRawX();
        float rawY = event.getRawY();
        Iterator<T> it = this.childGestureRegions.iterator();
        while (true) {
            boolean z = false;
            if (!it.hasNext()) {
                return false;
            }
            Rect rect = (Rect) it.next();
            boolean z2 = rawX >= ((float) rect.left) && rawX <= ((float) rect.right);
            if (rawY <= rect.bottom && rawY >= rect.top) {
                z = true;
            }
            if (z2 && z) {
                return true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleStartPanelWidthUpdate() {
        float f = this.startPanelOpenedCenterPanelX;
        float dimension = getResources().getDimension(R.dimen.overlapping_panels_margin_between_panels);
        View view = this.startPanel;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("startPanel");
        }
        float width = view.getWidth() + dimension;
        this.startPanelOpenedCenterPanelX = width;
        if (!this.isLeftToRight) {
            width = -width;
        }
        this.startPanelOpenedCenterPanelX = width;
        View view2 = this.centerPanel;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("centerPanel");
        }
        if (view2.getX() == f || this.centerPanelAnimationEndX == f) {
            openStartPanel();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleEndPanelWidthUpdate() {
        float f = this.endPanelOpenedCenterPanelX;
        float dimension = getResources().getDimension(R.dimen.overlapping_panels_margin_between_panels);
        View view = this.endPanel;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("endPanel");
        }
        float f2 = -(view.getWidth() + dimension);
        this.endPanelOpenedCenterPanelX = f2;
        if (!this.isLeftToRight) {
            f2 = -f2;
        }
        this.endPanelOpenedCenterPanelX = f2;
        View view2 = this.centerPanel;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("centerPanel");
        }
        if (view2.getX() == f || this.centerPanelAnimationEndX == f) {
            openEndPanel();
        }
    }
}
