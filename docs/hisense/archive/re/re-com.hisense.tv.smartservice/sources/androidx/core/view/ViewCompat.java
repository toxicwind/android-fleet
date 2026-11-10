package androidx.core.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.KeyEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeProvider;
import androidx.annotation.FloatRange;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.UiThread;
import androidx.collection.SimpleArrayMap;
import androidx.core.R;
import androidx.core.util.Preconditions;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeProviderCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import b.a.a.a.a;
import com.google.common.collect.LinkedHashMultimap;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"PrivateConstructorForUtilityClass"})
public class ViewCompat {
    public static final int ACCESSIBILITY_LIVE_REGION_ASSERTIVE = 2;
    public static final int ACCESSIBILITY_LIVE_REGION_NONE = 0;
    public static final int ACCESSIBILITY_LIVE_REGION_POLITE = 1;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_AUTO = 0;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_NO = 2;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS = 4;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_YES = 1;

    @Deprecated
    public static final int LAYER_TYPE_HARDWARE = 2;

    @Deprecated
    public static final int LAYER_TYPE_NONE = 0;

    @Deprecated
    public static final int LAYER_TYPE_SOFTWARE = 1;
    public static final int LAYOUT_DIRECTION_INHERIT = 2;
    public static final int LAYOUT_DIRECTION_LOCALE = 3;
    public static final int LAYOUT_DIRECTION_LTR = 0;
    public static final int LAYOUT_DIRECTION_RTL = 1;

    @Deprecated
    public static final int MEASURED_HEIGHT_STATE_SHIFT = 16;

    @Deprecated
    public static final int MEASURED_SIZE_MASK = 16777215;

    @Deprecated
    public static final int MEASURED_STATE_MASK = -16777216;

    @Deprecated
    public static final int MEASURED_STATE_TOO_SMALL = 16777216;

    @Deprecated
    public static final int OVER_SCROLL_ALWAYS = 0;

    @Deprecated
    public static final int OVER_SCROLL_IF_CONTENT_SCROLLS = 1;

    @Deprecated
    public static final int OVER_SCROLL_NEVER = 2;
    public static final int SCROLL_AXIS_HORIZONTAL = 1;
    public static final int SCROLL_AXIS_NONE = 0;
    public static final int SCROLL_AXIS_VERTICAL = 2;
    public static final int SCROLL_INDICATOR_BOTTOM = 2;
    public static final int SCROLL_INDICATOR_END = 32;
    public static final int SCROLL_INDICATOR_LEFT = 4;
    public static final int SCROLL_INDICATOR_RIGHT = 8;
    public static final int SCROLL_INDICATOR_START = 16;
    public static final int SCROLL_INDICATOR_TOP = 1;
    public static final String TAG = "ViewCompat";
    public static final int TYPE_NON_TOUCH = 1;
    public static final int TYPE_TOUCH = 0;
    public static Field sAccessibilityDelegateField;
    public static Method sChildrenDrawingOrderMethod;
    public static Method sDispatchFinishTemporaryDetach;
    public static Method sDispatchStartTemporaryDetach;
    public static Field sMinHeightField;
    public static boolean sMinHeightFieldFetched;
    public static Field sMinWidthField;
    public static boolean sMinWidthFieldFetched;
    public static boolean sTempDetachBound;
    public static ThreadLocal<Rect> sThreadLocalRect;
    public static WeakHashMap<View, String> sTransitionNameMap;
    public static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);
    public static WeakHashMap<View, ViewPropertyAnimatorCompat> sViewPropertyAnimatorMap = null;
    public static boolean sAccessibilityDelegateCheckFailed = false;
    public static final int[] ACCESSIBILITY_ACTIONS_RESOURCE_IDS = {R.id.accessibility_custom_action_0, R.id.accessibility_custom_action_1, R.id.accessibility_custom_action_2, R.id.accessibility_custom_action_3, R.id.accessibility_custom_action_4, R.id.accessibility_custom_action_5, R.id.accessibility_custom_action_6, R.id.accessibility_custom_action_7, R.id.accessibility_custom_action_8, R.id.accessibility_custom_action_9, R.id.accessibility_custom_action_10, R.id.accessibility_custom_action_11, R.id.accessibility_custom_action_12, R.id.accessibility_custom_action_13, R.id.accessibility_custom_action_14, R.id.accessibility_custom_action_15, R.id.accessibility_custom_action_16, R.id.accessibility_custom_action_17, R.id.accessibility_custom_action_18, R.id.accessibility_custom_action_19, R.id.accessibility_custom_action_20, R.id.accessibility_custom_action_21, R.id.accessibility_custom_action_22, R.id.accessibility_custom_action_23, R.id.accessibility_custom_action_24, R.id.accessibility_custom_action_25, R.id.accessibility_custom_action_26, R.id.accessibility_custom_action_27, R.id.accessibility_custom_action_28, R.id.accessibility_custom_action_29, R.id.accessibility_custom_action_30, R.id.accessibility_custom_action_31};
    public static final OnReceiveContentViewBehavior NO_OP_ON_RECEIVE_CONTENT_VIEW_BEHAVIOR = new OnReceiveContentViewBehavior() { // from class: androidx.core.view.ViewCompat.1
        @Override // androidx.core.view.OnReceiveContentViewBehavior
        public ContentInfoCompat onReceiveContent(@NonNull ContentInfoCompat payload) {
            return payload;
        }
    };
    public static AccessibilityPaneVisibilityManager sAccessibilityPaneVisibilityManager = new AccessibilityPaneVisibilityManager();

    static class AccessibilityPaneVisibilityManager implements ViewTreeObserver.OnGlobalLayoutListener, View.OnAttachStateChangeListener {
        public WeakHashMap<View, Boolean> mPanesToVisible = new WeakHashMap<>();

        @RequiresApi(19)
        private void checkPaneVisibility(View pane, boolean oldVisibility) {
            boolean z = pane.getVisibility() == 0;
            if (oldVisibility != z) {
                ViewCompat.notifyViewAccessibilityStateChangedIfNeeded(pane, z ? 16 : 32);
                this.mPanesToVisible.put(pane, Boolean.valueOf(z));
            }
        }

        @RequiresApi(19)
        private void registerForLayoutCallback(View view) {
            view.getViewTreeObserver().addOnGlobalLayoutListener(this);
        }

        @RequiresApi(19)
        private void unregisterForLayoutCallback(View view) {
            view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }

        @RequiresApi(19)
        public void addAccessibilityPane(View pane) {
            this.mPanesToVisible.put(pane, Boolean.valueOf(pane.getVisibility() == 0));
            pane.addOnAttachStateChangeListener(this);
            if (pane.isAttachedToWindow()) {
                registerForLayoutCallback(pane);
            }
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        @RequiresApi(19)
        public void onGlobalLayout() {
            if (Build.VERSION.SDK_INT < 28) {
                for (Map.Entry<View, Boolean> entry : this.mPanesToVisible.entrySet()) {
                    checkPaneVisibility(entry.getKey(), entry.getValue().booleanValue());
                }
            }
        }

        @Override // android.view.View.OnAttachStateChangeListener
        @RequiresApi(19)
        public void onViewAttachedToWindow(View view) {
            registerForLayoutCallback(view);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
        }

        @RequiresApi(19)
        public void removeAccessibilityPane(View pane) {
            this.mPanesToVisible.remove(pane);
            pane.removeOnAttachStateChangeListener(this);
            unregisterForLayoutCallback(pane);
        }
    }

    @RequiresApi(21)
    private static class Api21Impl {
        public static void callCompatInsetAnimationCallback(@NonNull final WindowInsets insets, @NonNull final View v) {
            View.OnApplyWindowInsetsListener onApplyWindowInsetsListener = (View.OnApplyWindowInsetsListener) v.getTag(R.id.tag_window_insets_animation_callback);
            if (onApplyWindowInsetsListener != null) {
                onApplyWindowInsetsListener.onApplyWindowInsets(v, insets);
            }
        }

        public static WindowInsetsCompat computeSystemWindowInsets(@NonNull View v, @NonNull WindowInsetsCompat insets, @NonNull Rect outLocalInsets) {
            WindowInsets windowInsets = insets.toWindowInsets();
            if (windowInsets != null) {
                return WindowInsetsCompat.toWindowInsetsCompat(v.computeSystemWindowInsets(windowInsets, outLocalInsets), v);
            }
            outLocalInsets.setEmpty();
            return insets;
        }

        @Nullable
        public static WindowInsetsCompat getRootWindowInsets(@NonNull View v) {
            return WindowInsetsCompat.Api21ReflectionHolder.getRootWindowInsets(v);
        }

        public static void setOnApplyWindowInsetsListener(@NonNull final View v, @Nullable final OnApplyWindowInsetsListener listener) {
            if (Build.VERSION.SDK_INT < 30) {
                v.setTag(R.id.tag_on_apply_window_listener, listener);
            }
            if (listener == null) {
                v.setOnApplyWindowInsetsListener((View.OnApplyWindowInsetsListener) v.getTag(R.id.tag_window_insets_animation_callback));
            } else {
                v.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: androidx.core.view.ViewCompat.Api21Impl.1
                    public WindowInsetsCompat mLastInsets = null;

                    @Override // android.view.View.OnApplyWindowInsetsListener
                    public WindowInsets onApplyWindowInsets(final View view, final WindowInsets insets) {
                        WindowInsetsCompat windowInsetsCompat = WindowInsetsCompat.toWindowInsetsCompat(insets, view);
                        if (Build.VERSION.SDK_INT < 30) {
                            Api21Impl.callCompatInsetAnimationCallback(insets, v);
                            if (windowInsetsCompat.equals(this.mLastInsets)) {
                                return listener.onApplyWindowInsets(view, windowInsetsCompat).toWindowInsets();
                            }
                        }
                        this.mLastInsets = windowInsetsCompat;
                        WindowInsetsCompat windowInsetsCompatOnApplyWindowInsets = listener.onApplyWindowInsets(view, windowInsetsCompat);
                        if (Build.VERSION.SDK_INT >= 30) {
                            return windowInsetsCompatOnApplyWindowInsets.toWindowInsets();
                        }
                        ViewCompat.requestApplyInsets(view);
                        return windowInsetsCompatOnApplyWindowInsets.toWindowInsets();
                    }
                });
            }
        }
    }

    @RequiresApi(23)
    private static class Api23Impl {
        @Nullable
        public static WindowInsetsCompat getRootWindowInsets(@NonNull View v) {
            WindowInsets rootWindowInsets = v.getRootWindowInsets();
            if (rootWindowInsets == null) {
                return null;
            }
            WindowInsetsCompat windowInsetsCompat = WindowInsetsCompat.toWindowInsetsCompat(rootWindowInsets);
            windowInsetsCompat.setRootWindowInsets(windowInsetsCompat);
            windowInsetsCompat.copyRootViewBounds(v.getRootView());
            return windowInsetsCompat;
        }
    }

    @RequiresApi(29)
    private static class Api29Impl {
        public static void saveAttributeDataForStyleable(@NonNull View view, @NonNull Context context, @NonNull int[] styleable, @Nullable AttributeSet attrs, @NonNull TypedArray t, int defStyleAttr, int defStyleRes) {
            view.saveAttributeDataForStyleable(context, styleable, attrs, t, defStyleAttr, defStyleRes);
        }
    }

    @RequiresApi(30)
    private static class Api30Impl {
        @Nullable
        public static WindowInsetsControllerCompat getWindowInsetsController(@NonNull View view) {
            WindowInsetsController windowInsetsController = view.getWindowInsetsController();
            if (windowInsetsController != null) {
                return new WindowInsetsControllerCompat(windowInsetsController);
            }
            return null;
        }
    }

    @RequiresApi(28)
    static class CompatImplApi28 {
        public static void addOnUnhandledKeyEventListener(@NonNull View v, @NonNull final OnUnhandledKeyEventListenerCompat listener) {
            SimpleArrayMap simpleArrayMap = (SimpleArrayMap) v.getTag(R.id.tag_unhandled_key_listeners);
            if (simpleArrayMap == null) {
                simpleArrayMap = new SimpleArrayMap();
                v.setTag(R.id.tag_unhandled_key_listeners, simpleArrayMap);
            }
            View.OnUnhandledKeyEventListener onUnhandledKeyEventListener = new View.OnUnhandledKeyEventListener() { // from class: androidx.core.view.ViewCompat.CompatImplApi28.1
                @Override // android.view.View.OnUnhandledKeyEventListener
                public boolean onUnhandledKeyEvent(View v2, KeyEvent event) {
                    return listener.onUnhandledKeyEvent(v2, event);
                }
            };
            simpleArrayMap.put(listener, onUnhandledKeyEventListener);
            v.addOnUnhandledKeyEventListener(onUnhandledKeyEventListener);
        }

        public static void removeOnUnhandledKeyEventListener(@NonNull View v, @NonNull OnUnhandledKeyEventListenerCompat listener) {
            View.OnUnhandledKeyEventListener onUnhandledKeyEventListener;
            SimpleArrayMap simpleArrayMap = (SimpleArrayMap) v.getTag(R.id.tag_unhandled_key_listeners);
            if (simpleArrayMap == null || (onUnhandledKeyEventListener = (View.OnUnhandledKeyEventListener) simpleArrayMap.get(listener)) == null) {
                return;
            }
            v.removeOnUnhandledKeyEventListener(onUnhandledKeyEventListener);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public @interface FocusDirection {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public @interface FocusRealDirection {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public @interface FocusRelativeDirection {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public @interface NestedScrollType {
    }

    public interface OnUnhandledKeyEventListenerCompat {
        boolean onUnhandledKeyEvent(View v, KeyEvent event);
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public @interface ScrollAxis {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public @interface ScrollIndicators {
    }

    static class UnhandledKeyEventManager {
        public static final ArrayList<WeakReference<View>> sViewsWithListeners = new ArrayList<>();

        @Nullable
        public WeakHashMap<View, Boolean> mViewsContainingListeners = null;
        public SparseArray<WeakReference<View>> mCapturedKeys = null;
        public WeakReference<KeyEvent> mLastDispatchedPreViewKeyEvent = null;

        public static UnhandledKeyEventManager at(View root) {
            UnhandledKeyEventManager unhandledKeyEventManager = (UnhandledKeyEventManager) root.getTag(R.id.tag_unhandled_key_event_manager);
            if (unhandledKeyEventManager != null) {
                return unhandledKeyEventManager;
            }
            UnhandledKeyEventManager unhandledKeyEventManager2 = new UnhandledKeyEventManager();
            root.setTag(R.id.tag_unhandled_key_event_manager, unhandledKeyEventManager2);
            return unhandledKeyEventManager2;
        }

        @Nullable
        private View dispatchInOrder(View view, KeyEvent event) {
            WeakHashMap<View, Boolean> weakHashMap = this.mViewsContainingListeners;
            if (weakHashMap != null && weakHashMap.containsKey(view)) {
                if (view instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view;
                    for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                        View viewDispatchInOrder = dispatchInOrder(viewGroup.getChildAt(childCount), event);
                        if (viewDispatchInOrder != null) {
                            return viewDispatchInOrder;
                        }
                    }
                }
                if (onUnhandledKeyEvent(view, event)) {
                    return view;
                }
            }
            return null;
        }

        private SparseArray<WeakReference<View>> getCapturedKeys() {
            if (this.mCapturedKeys == null) {
                this.mCapturedKeys = new SparseArray<>();
            }
            return this.mCapturedKeys;
        }

        private boolean onUnhandledKeyEvent(@NonNull View v, @NonNull KeyEvent event) {
            ArrayList arrayList = (ArrayList) v.getTag(R.id.tag_unhandled_key_listeners);
            if (arrayList == null) {
                return false;
            }
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                if (((OnUnhandledKeyEventListenerCompat) arrayList.get(size)).onUnhandledKeyEvent(v, event)) {
                    return true;
                }
            }
            return false;
        }

        private void recalcViewsWithUnhandled() {
            WeakHashMap<View, Boolean> weakHashMap = this.mViewsContainingListeners;
            if (weakHashMap != null) {
                weakHashMap.clear();
            }
            if (sViewsWithListeners.isEmpty()) {
                return;
            }
            synchronized (sViewsWithListeners) {
                if (this.mViewsContainingListeners == null) {
                    this.mViewsContainingListeners = new WeakHashMap<>();
                }
                for (int size = sViewsWithListeners.size() - 1; size >= 0; size--) {
                    View view = sViewsWithListeners.get(size).get();
                    if (view == null) {
                        sViewsWithListeners.remove(size);
                    } else {
                        this.mViewsContainingListeners.put(view, Boolean.TRUE);
                        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
                            this.mViewsContainingListeners.put((View) parent, Boolean.TRUE);
                        }
                    }
                }
            }
        }

        public static void registerListeningView(View v) {
            synchronized (sViewsWithListeners) {
                Iterator<WeakReference<View>> it = sViewsWithListeners.iterator();
                while (it.hasNext()) {
                    if (it.next().get() == v) {
                        return;
                    }
                }
                sViewsWithListeners.add(new WeakReference<>(v));
            }
        }

        public static void unregisterListeningView(View v) {
            synchronized (sViewsWithListeners) {
                for (int i = 0; i < sViewsWithListeners.size(); i++) {
                    if (sViewsWithListeners.get(i).get() == v) {
                        sViewsWithListeners.remove(i);
                        return;
                    }
                }
            }
        }

        public boolean dispatch(View root, KeyEvent event) {
            if (event.getAction() == 0) {
                recalcViewsWithUnhandled();
            }
            View viewDispatchInOrder = dispatchInOrder(root, event);
            if (event.getAction() == 0) {
                int keyCode = event.getKeyCode();
                if (viewDispatchInOrder != null && !KeyEvent.isModifierKey(keyCode)) {
                    getCapturedKeys().put(keyCode, new WeakReference<>(viewDispatchInOrder));
                }
            }
            return viewDispatchInOrder != null;
        }

        public boolean preDispatch(KeyEvent event) {
            int iIndexOfKey;
            WeakReference<KeyEvent> weakReference = this.mLastDispatchedPreViewKeyEvent;
            if (weakReference != null && weakReference.get() == event) {
                return false;
            }
            this.mLastDispatchedPreViewKeyEvent = new WeakReference<>(event);
            WeakReference<View> weakReferenceValueAt = null;
            SparseArray<WeakReference<View>> capturedKeys = getCapturedKeys();
            if (event.getAction() == 1 && (iIndexOfKey = capturedKeys.indexOfKey(event.getKeyCode())) >= 0) {
                weakReferenceValueAt = capturedKeys.valueAt(iIndexOfKey);
                capturedKeys.removeAt(iIndexOfKey);
            }
            if (weakReferenceValueAt == null) {
                weakReferenceValueAt = capturedKeys.get(event.getKeyCode());
            }
            if (weakReferenceValueAt == null) {
                return false;
            }
            View view = weakReferenceValueAt.get();
            if (view != null && ViewCompat.isAttachedToWindow(view)) {
                onUnhandledKeyEvent(view, event);
            }
            return true;
        }
    }

    @Deprecated
    public ViewCompat() {
    }

    public static AccessibilityViewProperty<Boolean> accessibilityHeadingProperty() {
        return new AccessibilityViewProperty<Boolean>(R.id.tag_accessibility_heading, Boolean.class, 28) { // from class: androidx.core.view.ViewCompat.5
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
            @RequiresApi(28)
            public Boolean frameworkGet(View view) {
                return Boolean.valueOf(view.isAccessibilityHeading());
            }

            @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
            @RequiresApi(28)
            public void frameworkSet(View view, Boolean value) {
                view.setAccessibilityHeading(value.booleanValue());
            }

            @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
            public boolean shouldUpdate(Boolean oldValue, Boolean newValue) {
                return !booleanNullToFalseEquals(oldValue, newValue);
            }
        };
    }

    public static int addAccessibilityAction(@NonNull View view, @NonNull CharSequence label, @NonNull AccessibilityViewCommand command) {
        int availableActionIdFromResources = getAvailableActionIdFromResources(view, label);
        if (availableActionIdFromResources != -1) {
            addAccessibilityAction(view, new AccessibilityNodeInfoCompat.AccessibilityActionCompat(availableActionIdFromResources, label, command));
        }
        return availableActionIdFromResources;
    }

    public static void addKeyboardNavigationClusters(@NonNull View view, @NonNull Collection<View> views, int direction) {
        int i = Build.VERSION.SDK_INT;
        view.addKeyboardNavigationClusters(views, direction);
    }

    public static void addOnUnhandledKeyEventListener(@NonNull View v, @NonNull final OnUnhandledKeyEventListenerCompat listener) {
        if (Build.VERSION.SDK_INT >= 28) {
            CompatImplApi28.addOnUnhandledKeyEventListener(v, listener);
            return;
        }
        ArrayList arrayList = (ArrayList) v.getTag(R.id.tag_unhandled_key_listeners);
        if (arrayList == null) {
            arrayList = new ArrayList();
            v.setTag(R.id.tag_unhandled_key_listeners, arrayList);
        }
        arrayList.add(listener);
        if (arrayList.size() == 1) {
            UnhandledKeyEventManager.registerListeningView(v);
        }
    }

    @NonNull
    public static ViewPropertyAnimatorCompat animate(@NonNull View view) {
        if (sViewPropertyAnimatorMap == null) {
            sViewPropertyAnimatorMap = new WeakHashMap<>();
        }
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = sViewPropertyAnimatorMap.get(view);
        if (viewPropertyAnimatorCompat != null) {
            return viewPropertyAnimatorCompat;
        }
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat2 = new ViewPropertyAnimatorCompat(view);
        sViewPropertyAnimatorMap.put(view, viewPropertyAnimatorCompat2);
        return viewPropertyAnimatorCompat2;
    }

    public static void bindTempDetach() {
        try {
            sDispatchStartTemporaryDetach = View.class.getDeclaredMethod("dispatchStartTemporaryDetach", new Class[0]);
            sDispatchFinishTemporaryDetach = View.class.getDeclaredMethod("dispatchFinishTemporaryDetach", new Class[0]);
        } catch (NoSuchMethodException e2) {
            Log.e(TAG, "Couldn't find method", e2);
        }
        sTempDetachBound = true;
    }

    @Deprecated
    public static boolean canScrollHorizontally(View view, int direction) {
        return view.canScrollHorizontally(direction);
    }

    @Deprecated
    public static boolean canScrollVertically(View view, int direction) {
        return view.canScrollVertically(direction);
    }

    public static void cancelDragAndDrop(@NonNull View v) {
        int i = Build.VERSION.SDK_INT;
        v.cancelDragAndDrop();
    }

    @Deprecated
    public static int combineMeasuredStates(int curState, int newState) {
        return View.combineMeasuredStates(curState, newState);
    }

    public static void compatOffsetLeftAndRight(View view, int offset) {
        view.offsetLeftAndRight(offset);
        if (view.getVisibility() == 0) {
            float translationY = view.getTranslationY();
            view.setTranslationY(1.0f + translationY);
            view.setTranslationY(translationY);
            Object parent = view.getParent();
            if (parent instanceof View) {
                tickleInvalidationFlag((View) parent);
            }
        }
    }

    public static void compatOffsetTopAndBottom(View view, int offset) {
        view.offsetTopAndBottom(offset);
        if (view.getVisibility() == 0) {
            float translationY = view.getTranslationY();
            view.setTranslationY(1.0f + translationY);
            view.setTranslationY(translationY);
            Object parent = view.getParent();
            if (parent instanceof View) {
                tickleInvalidationFlag((View) parent);
            }
        }
    }

    @NonNull
    public static WindowInsetsCompat computeSystemWindowInsets(@NonNull View view, @NonNull WindowInsetsCompat insets, @NonNull Rect outLocalInsets) {
        int i = Build.VERSION.SDK_INT;
        return Api21Impl.computeSystemWindowInsets(view, insets, outLocalInsets);
    }

    @NonNull
    public static WindowInsetsCompat dispatchApplyWindowInsets(@NonNull View view, @NonNull WindowInsetsCompat insets) {
        int i = Build.VERSION.SDK_INT;
        WindowInsets windowInsets = insets.toWindowInsets();
        if (windowInsets != null) {
            WindowInsets windowInsetsDispatchApplyWindowInsets = view.dispatchApplyWindowInsets(windowInsets);
            if (!windowInsetsDispatchApplyWindowInsets.equals(windowInsets)) {
                return WindowInsetsCompat.toWindowInsetsCompat(windowInsetsDispatchApplyWindowInsets, view);
            }
        }
        return insets;
    }

    public static void dispatchFinishTemporaryDetach(@NonNull View view) {
        int i = Build.VERSION.SDK_INT;
        view.dispatchFinishTemporaryDetach();
    }

    public static boolean dispatchNestedFling(@NonNull View view, float velocityX, float velocityY, boolean consumed) {
        int i = Build.VERSION.SDK_INT;
        return view.dispatchNestedFling(velocityX, velocityY, consumed);
    }

    public static boolean dispatchNestedPreFling(@NonNull View view, float velocityX, float velocityY) {
        int i = Build.VERSION.SDK_INT;
        return view.dispatchNestedPreFling(velocityX, velocityY);
    }

    public static boolean dispatchNestedPreScroll(@NonNull View view, int dx, int dy, @Nullable int[] consumed, @Nullable int[] offsetInWindow) {
        int i = Build.VERSION.SDK_INT;
        return view.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    }

    public static boolean dispatchNestedScroll(@NonNull View view, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @Nullable int[] offsetInWindow) {
        int i = Build.VERSION.SDK_INT;
        return view.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    public static void dispatchStartTemporaryDetach(@NonNull View view) {
        int i = Build.VERSION.SDK_INT;
        view.dispatchStartTemporaryDetach();
    }

    @UiThread
    public static boolean dispatchUnhandledKeyEventBeforeCallback(View root, KeyEvent evt) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        return UnhandledKeyEventManager.at(root).dispatch(root, evt);
    }

    @UiThread
    public static boolean dispatchUnhandledKeyEventBeforeHierarchy(View root, KeyEvent evt) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        return UnhandledKeyEventManager.at(root).preDispatch(evt);
    }

    public static void enableAccessibleClickableSpanSupport(View view) {
        int i = Build.VERSION.SDK_INT;
        getOrCreateAccessibilityDelegateCompat(view);
    }

    public static int generateViewId() {
        int i = Build.VERSION.SDK_INT;
        return View.generateViewId();
    }

    @Nullable
    public static AccessibilityDelegateCompat getAccessibilityDelegate(@NonNull View view) {
        View.AccessibilityDelegate accessibilityDelegateInternal = getAccessibilityDelegateInternal(view);
        if (accessibilityDelegateInternal == null) {
            return null;
        }
        return accessibilityDelegateInternal instanceof AccessibilityDelegateCompat.AccessibilityDelegateAdapter ? ((AccessibilityDelegateCompat.AccessibilityDelegateAdapter) accessibilityDelegateInternal).mCompat : new AccessibilityDelegateCompat(accessibilityDelegateInternal);
    }

    @Nullable
    public static View.AccessibilityDelegate getAccessibilityDelegateInternal(@NonNull View v) {
        return Build.VERSION.SDK_INT >= 29 ? v.getAccessibilityDelegate() : getAccessibilityDelegateThroughReflection(v);
    }

    @Nullable
    public static View.AccessibilityDelegate getAccessibilityDelegateThroughReflection(@NonNull View v) {
        if (sAccessibilityDelegateCheckFailed) {
            return null;
        }
        if (sAccessibilityDelegateField == null) {
            try {
                sAccessibilityDelegateField = View.class.getDeclaredField("mAccessibilityDelegate");
                sAccessibilityDelegateField.setAccessible(true);
            } catch (Throwable unused) {
                sAccessibilityDelegateCheckFailed = true;
                return null;
            }
        }
        try {
            Object obj = sAccessibilityDelegateField.get(v);
            if (obj instanceof View.AccessibilityDelegate) {
                return (View.AccessibilityDelegate) obj;
            }
            return null;
        } catch (Throwable unused2) {
            sAccessibilityDelegateCheckFailed = true;
            return null;
        }
    }

    public static int getAccessibilityLiveRegion(@NonNull View view) {
        int i = Build.VERSION.SDK_INT;
        return view.getAccessibilityLiveRegion();
    }

    public static AccessibilityNodeProviderCompat getAccessibilityNodeProvider(@NonNull View view) {
        int i = Build.VERSION.SDK_INT;
        AccessibilityNodeProvider accessibilityNodeProvider = view.getAccessibilityNodeProvider();
        if (accessibilityNodeProvider != null) {
            return new AccessibilityNodeProviderCompat(accessibilityNodeProvider);
        }
        return null;
    }

    @UiThread
    public static CharSequence getAccessibilityPaneTitle(View view) {
        return paneTitleProperty().get(view);
    }

    public static List<AccessibilityNodeInfoCompat.AccessibilityActionCompat> getActionList(View view) {
        ArrayList arrayList = (ArrayList) view.getTag(R.id.tag_accessibility_actions);
        if (arrayList != null) {
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        view.setTag(R.id.tag_accessibility_actions, arrayList2);
        return arrayList2;
    }

    @Deprecated
    public static float getAlpha(View view) {
        return view.getAlpha();
    }

    public static int getAvailableActionIdFromResources(View view, @NonNull CharSequence label) {
        List<AccessibilityNodeInfoCompat.AccessibilityActionCompat> actionList = getActionList(view);
        for (int i = 0; i < actionList.size(); i++) {
            if (TextUtils.equals(label, actionList.get(i).getLabel())) {
                return actionList.get(i).getId();
            }
        }
        int i2 = -1;
        int i3 = 0;
        while (true) {
            int[] iArr = ACCESSIBILITY_ACTIONS_RESOURCE_IDS;
            if (i3 >= iArr.length || i2 != -1) {
                break;
            }
            int i4 = iArr[i3];
            boolean z = true;
            for (int i5 = 0; i5 < actionList.size(); i5++) {
                z &= actionList.get(i5).getId() != i4;
            }
            if (z) {
                i2 = i4;
            }
            i3++;
        }
        return i2;
    }

    public static ColorStateList getBackgroundTintList(@NonNull View view) {
        int i = Build.VERSION.SDK_INT;
        return view.getBackgroundTintList();
    }

    public static PorterDuff.Mode getBackgroundTintMode(@NonNull View view) {
        int i = Build.VERSION.SDK_INT;
        return view.getBackgroundTintMode();
    }

    @Nullable
    public static Rect getClipBounds(@NonNull View view) {
        int i = Build.VERSION.SDK_INT;
        return view.getClipBounds();
    }

    @Nullable
    public static Display getDisplay(@NonNull View view) {
        int i = Build.VERSION.SDK_INT;
        return view.getDisplay();
    }

    public static float getElevation(@NonNull View view) {
        int i = Build.VERSION.SDK_INT;
        return view.getElevation();
    }

    public static Rect getEmptyTempRect() {
        if (sThreadLocalRect == null) {
            sThreadLocalRect = new ThreadLocal<>();
        }
        Rect rect = sThreadLocalRect.get();
        if (rect == null) {
            rect = new Rect();
            sThreadLocalRect.set(rect);
        }
        rect.setEmpty();
        return rect;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static OnReceiveContentViewBehavior getFallback(@NonNull View view) {
        return view instanceof OnReceiveContentViewBehavior ? (OnReceiveContentViewBehavior) view : NO_OP_ON_RECEIVE_CONTENT_VIEW_BEHAVIOR;
    }

    public static boolean getFitsSystemWindows(@NonNull View v) {
        int i = Build.VERSION.SDK_INT;
        return v.getFitsSystemWindows();
    }

    public static int getImportantForAccessibility(@NonNull View view) {
        int i = Build.VERSION.SDK_INT;
        return view.getImportantForAccessibility();
    }

    @SuppressLint({"InlinedApi"})
    public static int getImportantForAutofill(@NonNull View v) {
        int i = Build.VERSION.SDK_INT;
        return v.getImportantForAutofill();
    }

    public static int getLabelFor(@NonNull View view) {
        int i = Build.VERSION.SDK_INT;
        return view.getLabelFor();
    }

    @Deprecated
    public static int getLayerType(View view) {
        return view.getLayerType();
    }

    public static int getLayoutDirection(@NonNull View view) {
        int i = Build.VERSION.SDK_INT;
        return view.getLayoutDirection();
    }

    @Nullable
    @Deprecated
    public static Matrix getMatrix(View view) {
        return view.getMatrix();
    }

    @Deprecated
    public static int getMeasuredHeightAndState(View view) {
        return view.getMeasuredHeightAndState();
    }

    @Deprecated
    public static int getMeasuredState(View view) {
        return view.getMeasuredState();
    }

    @Deprecated
    public static int getMeasuredWidthAndState(View view) {
        return view.getMeasuredWidthAndState();
    }

    public static int getMinimumHeight(@NonNull View view) {
        int i = Build.VERSION.SDK_INT;
        return view.getMinimumHeight();
    }

    public static int getMinimumWidth(@NonNull View view) {
        int i = Build.VERSION.SDK_INT;
        return view.getMinimumWidth();
    }

    public static int getNextClusterForwardId(@NonNull View view) {
        int i = Build.VERSION.SDK_INT;
        return view.getNextClusterForwardId();
    }

    @Nullable
    public static String[] getOnReceiveContentMimeTypes(@NonNull View view) {
        return (String[]) view.getTag(R.id.tag_on_receive_content_mime_types);
    }

    public static AccessibilityDelegateCompat getOrCreateAccessibilityDelegateCompat(@NonNull View v) {
        AccessibilityDelegateCompat accessibilityDelegate = getAccessibilityDelegate(v);
        if (accessibilityDelegate == null) {
            accessibilityDelegate = new AccessibilityDelegateCompat(AccessibilityDelegateCompat.DEFAULT_DELEGATE);
        }
        setAccessibilityDelegate(v, accessibilityDelegate);
        return accessibilityDelegate;
    }

    @Deprecated
    public static int getOverScrollMode(View v) {
        return v.getOverScrollMode();
    }

    @Px
    public static int getPaddingEnd(@NonNull View view) {
        int i = Build.VERSION.SDK_INT;
        return view.getPaddingEnd();
    }

    @Px
    public static int getPaddingStart(@NonNull View view) {
        int i = Build.VERSION.SDK_INT;
        return view.getPaddingStart();
    }

    public static ViewParent getParentForAccessibility(@NonNull View view) {
        int i = Build.VERSION.SDK_INT;
        return view.getParentForAccessibility();
    }

    @Deprecated
    public static float getPivotX(View view) {
        return view.getPivotX();
    }

    @Deprecated
    public static float getPivotY(View view) {
        return view.getPivotY();
    }

    @Nullable
    public static WindowInsetsCompat getRootWindowInsets(@NonNull View view) {
        int i = Build.VERSION.SDK_INT;
        return Api23Impl.getRootWindowInsets(view);
    }

    @Deprecated
    public static float getRotation(View view) {
        return view.getRotation();
    }

    @Deprecated
    public static float getRotationX(View view) {
        return view.getRotationX();
    }

    @Deprecated
    public static float getRotationY(View view) {
        return view.getRotationY();
    }

    @Deprecated
    public static float getScaleX(View view) {
        return view.getScaleX();
    }

    @Deprecated
    public static float getScaleY(View view) {
        return view.getScaleY();
    }

    public static int getScrollIndicators(@NonNull View view) {
        int i = Build.VERSION.SDK_INT;
        return view.getScrollIndicators();
    }

    @Nullable
    @UiThread
    public static final CharSequence getStateDescription(@NonNull View view) {
        return stateDescriptionProperty().get(view);
    }

    @NonNull
    public static List<Rect> getSystemGestureExclusionRects(@NonNull View view) {
        return Build.VERSION.SDK_INT >= 29 ? view.getSystemGestureExclusionRects() : Collections.emptyList();
    }

    @Nullable
    public static String getTransitionName(@NonNull View view) {
        int i = Build.VERSION.SDK_INT;
        return view.getTransitionName();
    }

    @Deprecated
    public static float getTranslationX(View view) {
        return view.getTranslationX();
    }

    @Deprecated
    public static float getTranslationY(View view) {
        return view.getTranslationY();
    }

    public static float getTranslationZ(@NonNull View view) {
        int i = Build.VERSION.SDK_INT;
        return view.getTranslationZ();
    }

    @Nullable
    public static WindowInsetsControllerCompat getWindowInsetsController(@NonNull View view) {
        if (Build.VERSION.SDK_INT >= 30) {
            return Api30Impl.getWindowInsetsController(view);
        }
        for (Context context = view.getContext(); context instanceof ContextWrapper; context = ((ContextWrapper) context).getBaseContext()) {
            if (context instanceof Activity) {
                Window window = ((Activity) context).getWindow();
                if (window != null) {
                    return WindowCompat.getInsetsController(window, view);
                }
                return null;
            }
        }
        return null;
    }

    public static int getWindowSystemUiVisibility(@NonNull View view) {
        int i = Build.VERSION.SDK_INT;
        return view.getWindowSystemUiVisibility();
    }

    @Deprecated
    public static float getX(View view) {
        return view.getX();
    }

    @Deprecated
    public static float getY(View view) {
        return view.getY();
    }

    public static float getZ(@NonNull View view) {
        int i = Build.VERSION.SDK_INT;
        return view.getZ();
    }

    public static boolean hasAccessibilityDelegate(@NonNull View view) {
        return getAccessibilityDelegateInternal(view) != null;
    }

    public static boolean hasExplicitFocusable(@NonNull View view) {
        int i = Build.VERSION.SDK_INT;
        return view.hasExplicitFocusable();
    }

    public static boolean hasNestedScrollingParent(@NonNull View view) {
        int i = Build.VERSION.SDK_INT;
        return view.hasNestedScrollingParent();
    }

    public static boolean hasOnClickListeners(@NonNull View view) {
        int i = Build.VERSION.SDK_INT;
        return view.hasOnClickListeners();
    }

    public static boolean hasOverlappingRendering(@NonNull View view) {
        int i = Build.VERSION.SDK_INT;
        return view.hasOverlappingRendering();
    }

    public static boolean hasTransientState(@NonNull View view) {
        int i = Build.VERSION.SDK_INT;
        return view.hasTransientState();
    }

    @UiThread
    public static boolean isAccessibilityHeading(View view) {
        Boolean bool = accessibilityHeadingProperty().get(view);
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public static boolean isAttachedToWindow(@NonNull View view) {
        int i = Build.VERSION.SDK_INT;
        return view.isAttachedToWindow();
    }

    public static boolean isFocusedByDefault(@NonNull View view) {
        int i = Build.VERSION.SDK_INT;
        return view.isFocusedByDefault();
    }

    public static boolean isImportantForAccessibility(@NonNull View view) {
        int i = Build.VERSION.SDK_INT;
        return view.isImportantForAccessibility();
    }

    public static boolean isImportantForAutofill(@NonNull View v) {
        int i = Build.VERSION.SDK_INT;
        return v.isImportantForAutofill();
    }

    public static boolean isInLayout(@NonNull View view) {
        int i = Build.VERSION.SDK_INT;
        return view.isInLayout();
    }

    public static boolean isKeyboardNavigationCluster(@NonNull View view) {
        int i = Build.VERSION.SDK_INT;
        return view.isKeyboardNavigationCluster();
    }

    public static boolean isLaidOut(@NonNull View view) {
        int i = Build.VERSION.SDK_INT;
        return view.isLaidOut();
    }

    public static boolean isLayoutDirectionResolved(@NonNull View view) {
        int i = Build.VERSION.SDK_INT;
        return view.isLayoutDirectionResolved();
    }

    public static boolean isNestedScrollingEnabled(@NonNull View view) {
        int i = Build.VERSION.SDK_INT;
        return view.isNestedScrollingEnabled();
    }

    @Deprecated
    public static boolean isOpaque(View view) {
        return view.isOpaque();
    }

    public static boolean isPaddingRelative(@NonNull View view) {
        int i = Build.VERSION.SDK_INT;
        return view.isPaddingRelative();
    }

    @UiThread
    public static boolean isScreenReaderFocusable(View view) {
        Boolean bool = screenReaderFocusableProperty().get(view);
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    @Deprecated
    public static void jumpDrawablesToCurrentState(View v) {
        v.jumpDrawablesToCurrentState();
    }

    public static View keyboardNavigationClusterSearch(@NonNull View view, View currentCluster, int direction) {
        int i = Build.VERSION.SDK_INT;
        return view.keyboardNavigationClusterSearch(currentCluster, direction);
    }

    @RequiresApi(19)
    public static void notifyViewAccessibilityStateChangedIfNeeded(View view, int changeType) {
        AccessibilityManager accessibilityManager = (AccessibilityManager) view.getContext().getSystemService("accessibility");
        if (accessibilityManager.isEnabled()) {
            boolean z = getAccessibilityPaneTitle(view) != null && view.getVisibility() == 0;
            if (getAccessibilityLiveRegion(view) != 0 || z) {
                AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain();
                accessibilityEventObtain.setEventType(z ? 32 : 2048);
                accessibilityEventObtain.setContentChangeTypes(changeType);
                if (z) {
                    accessibilityEventObtain.getText().add(getAccessibilityPaneTitle(view));
                    setViewImportanceForAccessibilityIfNeeded(view);
                }
                view.sendAccessibilityEventUnchecked(accessibilityEventObtain);
                return;
            }
            if (changeType == 32) {
                AccessibilityEvent accessibilityEventObtain2 = AccessibilityEvent.obtain();
                view.onInitializeAccessibilityEvent(accessibilityEventObtain2);
                accessibilityEventObtain2.setEventType(32);
                accessibilityEventObtain2.setContentChangeTypes(changeType);
                accessibilityEventObtain2.setSource(view);
                view.onPopulateAccessibilityEvent(accessibilityEventObtain2);
                accessibilityEventObtain2.getText().add(getAccessibilityPaneTitle(view));
                accessibilityManager.sendAccessibilityEvent(accessibilityEventObtain2);
                return;
            }
            if (view.getParent() != null) {
                try {
                    view.getParent().notifySubtreeAccessibilityStateChanged(view, view, changeType);
                } catch (AbstractMethodError e2) {
                    Log.e(TAG, view.getParent().getClass().getSimpleName() + " does not fully implement ViewParent", e2);
                }
            }
        }
    }

    public static void offsetLeftAndRight(@NonNull View view, int offset) {
        int i = Build.VERSION.SDK_INT;
        view.offsetLeftAndRight(offset);
    }

    public static void offsetTopAndBottom(@NonNull View view, int offset) {
        int i = Build.VERSION.SDK_INT;
        view.offsetTopAndBottom(offset);
    }

    @NonNull
    public static WindowInsetsCompat onApplyWindowInsets(@NonNull View view, @NonNull WindowInsetsCompat insets) {
        int i = Build.VERSION.SDK_INT;
        WindowInsets windowInsets = insets.toWindowInsets();
        if (windowInsets != null) {
            WindowInsets windowInsetsOnApplyWindowInsets = view.onApplyWindowInsets(windowInsets);
            if (!windowInsetsOnApplyWindowInsets.equals(windowInsets)) {
                return WindowInsetsCompat.toWindowInsetsCompat(windowInsetsOnApplyWindowInsets, view);
            }
        }
        return insets;
    }

    @Deprecated
    public static void onInitializeAccessibilityEvent(View v, AccessibilityEvent event) {
        v.onInitializeAccessibilityEvent(event);
    }

    public static void onInitializeAccessibilityNodeInfo(@NonNull View v, AccessibilityNodeInfoCompat info) {
        v.onInitializeAccessibilityNodeInfo(info.unwrap());
    }

    @Deprecated
    public static void onPopulateAccessibilityEvent(View v, AccessibilityEvent event) {
        v.onPopulateAccessibilityEvent(event);
    }

    public static AccessibilityViewProperty<CharSequence> paneTitleProperty() {
        return new AccessibilityViewProperty<CharSequence>(R.id.tag_accessibility_pane_title, CharSequence.class, 8, 28) { // from class: androidx.core.view.ViewCompat.3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
            @RequiresApi(28)
            public CharSequence frameworkGet(View view) {
                return view.getAccessibilityPaneTitle();
            }

            @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
            @RequiresApi(28)
            public void frameworkSet(View view, CharSequence value) {
                view.setAccessibilityPaneTitle(value);
            }

            @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
            public boolean shouldUpdate(CharSequence oldValue, CharSequence newValue) {
                return !TextUtils.equals(oldValue, newValue);
            }
        };
    }

    public static boolean performAccessibilityAction(@NonNull View view, int action, Bundle arguments) {
        int i = Build.VERSION.SDK_INT;
        return view.performAccessibilityAction(action, arguments);
    }

    @Nullable
    public static ContentInfoCompat performReceiveContent(@NonNull View view, @NonNull ContentInfoCompat payload) {
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "performReceiveContent: " + payload + ", view=" + view.getClass().getSimpleName() + "[" + view.getId() + "]");
        }
        OnReceiveContentListener onReceiveContentListener = (OnReceiveContentListener) view.getTag(R.id.tag_on_receive_content_listener);
        if (onReceiveContentListener == null) {
            return getFallback(view).onReceiveContent(payload);
        }
        ContentInfoCompat contentInfoCompatOnReceiveContent = onReceiveContentListener.onReceiveContent(view, payload);
        if (contentInfoCompatOnReceiveContent == null) {
            return null;
        }
        return getFallback(view).onReceiveContent(contentInfoCompatOnReceiveContent);
    }

    public static void postInvalidateOnAnimation(@NonNull View view) {
        int i = Build.VERSION.SDK_INT;
        view.postInvalidateOnAnimation();
    }

    public static void postOnAnimation(@NonNull View view, Runnable action) {
        int i = Build.VERSION.SDK_INT;
        view.postOnAnimation(action);
    }

    public static void postOnAnimationDelayed(@NonNull View view, Runnable action, long delayMillis) {
        int i = Build.VERSION.SDK_INT;
        view.postOnAnimationDelayed(action, delayMillis);
    }

    public static void removeAccessibilityAction(@NonNull View view, int actionId) {
        int i = Build.VERSION.SDK_INT;
        removeActionWithId(actionId, view);
        notifyViewAccessibilityStateChangedIfNeeded(view, 0);
    }

    public static void removeActionWithId(int actionId, View view) {
        List<AccessibilityNodeInfoCompat.AccessibilityActionCompat> actionList = getActionList(view);
        for (int i = 0; i < actionList.size(); i++) {
            if (actionList.get(i).getId() == actionId) {
                actionList.remove(i);
                return;
            }
        }
    }

    public static void removeOnUnhandledKeyEventListener(@NonNull View v, @NonNull OnUnhandledKeyEventListenerCompat listener) {
        if (Build.VERSION.SDK_INT >= 28) {
            CompatImplApi28.removeOnUnhandledKeyEventListener(v, listener);
            return;
        }
        ArrayList arrayList = (ArrayList) v.getTag(R.id.tag_unhandled_key_listeners);
        if (arrayList != null) {
            arrayList.remove(listener);
            if (arrayList.size() == 0) {
                UnhandledKeyEventManager.unregisterListeningView(v);
            }
        }
    }

    public static void replaceAccessibilityAction(@NonNull View view, @NonNull AccessibilityNodeInfoCompat.AccessibilityActionCompat replacedAction, @Nullable CharSequence label, @Nullable AccessibilityViewCommand command) {
        if (command == null && label == null) {
            removeAccessibilityAction(view, replacedAction.getId());
        } else {
            addAccessibilityAction(view, replacedAction.createReplacementAction(label, command));
        }
    }

    public static void requestApplyInsets(@NonNull View view) {
        int i = Build.VERSION.SDK_INT;
        view.requestApplyInsets();
    }

    @NonNull
    public static <T extends View> T requireViewById(@NonNull View view, @IdRes int i) {
        if (Build.VERSION.SDK_INT >= 28) {
            return (T) view.requireViewById(i);
        }
        T t = (T) view.findViewById(i);
        if (t != null) {
            return t;
        }
        throw new IllegalArgumentException("ID does not reference a View inside this View");
    }

    @Deprecated
    public static int resolveSizeAndState(int size, int measureSpec, int childMeasuredState) {
        return View.resolveSizeAndState(size, measureSpec, childMeasuredState);
    }

    public static boolean restoreDefaultFocus(@NonNull View view) {
        int i = Build.VERSION.SDK_INT;
        return view.restoreDefaultFocus();
    }

    public static void saveAttributeDataForStyleable(@NonNull View view, @NonNull @SuppressLint({"ContextFirst"}) Context context, @NonNull int[] styleable, @Nullable AttributeSet attrs, @NonNull TypedArray t, int defStyleAttr, int defStyleRes) {
        if (Build.VERSION.SDK_INT >= 29) {
            view.saveAttributeDataForStyleable(context, styleable, attrs, t, defStyleAttr, defStyleRes);
        }
    }

    public static AccessibilityViewProperty<Boolean> screenReaderFocusableProperty() {
        return new AccessibilityViewProperty<Boolean>(R.id.tag_screen_reader_focusable, Boolean.class, 28) { // from class: androidx.core.view.ViewCompat.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
            @RequiresApi(28)
            public Boolean frameworkGet(View view) {
                return Boolean.valueOf(view.isScreenReaderFocusable());
            }

            @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
            @RequiresApi(28)
            public void frameworkSet(View view, Boolean value) {
                view.setScreenReaderFocusable(value.booleanValue());
            }

            @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
            public boolean shouldUpdate(Boolean oldValue, Boolean newValue) {
                return !booleanNullToFalseEquals(oldValue, newValue);
            }
        };
    }

    public static void setAccessibilityDelegate(@NonNull View v, AccessibilityDelegateCompat delegate) {
        if (delegate == null && (getAccessibilityDelegateInternal(v) instanceof AccessibilityDelegateCompat.AccessibilityDelegateAdapter)) {
            delegate = new AccessibilityDelegateCompat(AccessibilityDelegateCompat.DEFAULT_DELEGATE);
        }
        v.setAccessibilityDelegate(delegate == null ? null : delegate.getBridge());
    }

    @UiThread
    public static void setAccessibilityHeading(View view, boolean isHeading) {
        accessibilityHeadingProperty().set(view, Boolean.valueOf(isHeading));
    }

    public static void setAccessibilityLiveRegion(@NonNull View view, int mode) {
        int i = Build.VERSION.SDK_INT;
        view.setAccessibilityLiveRegion(mode);
    }

    @UiThread
    public static void setAccessibilityPaneTitle(View view, CharSequence accessibilityPaneTitle) {
        int i = Build.VERSION.SDK_INT;
        paneTitleProperty().set(view, accessibilityPaneTitle);
        if (accessibilityPaneTitle != null) {
            sAccessibilityPaneVisibilityManager.addAccessibilityPane(view);
        } else {
            sAccessibilityPaneVisibilityManager.removeAccessibilityPane(view);
        }
    }

    @Deprecated
    public static void setActivated(View view, boolean activated) {
        view.setActivated(activated);
    }

    @Deprecated
    public static void setAlpha(View view, @FloatRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, to = LinkedHashMultimap.VALUE_SET_LOAD_FACTOR) float value) {
        view.setAlpha(value);
    }

    public static void setAutofillHints(@NonNull View v, @Nullable String... autofillHints) {
        int i = Build.VERSION.SDK_INT;
        v.setAutofillHints(autofillHints);
    }

    public static void setBackground(@NonNull View view, @Nullable Drawable background) {
        int i = Build.VERSION.SDK_INT;
        view.setBackground(background);
    }

    public static void setBackgroundTintList(@NonNull View view, ColorStateList tintList) {
        int i = Build.VERSION.SDK_INT;
        view.setBackgroundTintList(tintList);
        int i2 = Build.VERSION.SDK_INT;
    }

    public static void setBackgroundTintMode(@NonNull View view, PorterDuff.Mode mode) {
        int i = Build.VERSION.SDK_INT;
        view.setBackgroundTintMode(mode);
        int i2 = Build.VERSION.SDK_INT;
    }

    @Deprecated
    public static void setChildrenDrawingOrderEnabled(ViewGroup viewGroup, boolean enabled) {
        if (sChildrenDrawingOrderMethod == null) {
            try {
                sChildrenDrawingOrderMethod = ViewGroup.class.getDeclaredMethod("setChildrenDrawingOrderEnabled", Boolean.TYPE);
            } catch (NoSuchMethodException e2) {
                Log.e(TAG, "Unable to find childrenDrawingOrderEnabled", e2);
            }
            sChildrenDrawingOrderMethod.setAccessible(true);
        }
        try {
            sChildrenDrawingOrderMethod.invoke(viewGroup, Boolean.valueOf(enabled));
        } catch (IllegalAccessException e3) {
            Log.e(TAG, "Unable to invoke childrenDrawingOrderEnabled", e3);
        } catch (IllegalArgumentException e4) {
            Log.e(TAG, "Unable to invoke childrenDrawingOrderEnabled", e4);
        } catch (InvocationTargetException e5) {
            Log.e(TAG, "Unable to invoke childrenDrawingOrderEnabled", e5);
        }
    }

    public static void setClipBounds(@NonNull View view, Rect clipBounds) {
        int i = Build.VERSION.SDK_INT;
        view.setClipBounds(clipBounds);
    }

    public static void setElevation(@NonNull View view, float elevation) {
        int i = Build.VERSION.SDK_INT;
        view.setElevation(elevation);
    }

    @Deprecated
    public static void setFitsSystemWindows(View view, boolean fitSystemWindows) {
        view.setFitsSystemWindows(fitSystemWindows);
    }

    public static void setFocusedByDefault(@NonNull View view, boolean isFocusedByDefault) {
        int i = Build.VERSION.SDK_INT;
        view.setFocusedByDefault(isFocusedByDefault);
    }

    public static void setHasTransientState(@NonNull View view, boolean hasTransientState) {
        int i = Build.VERSION.SDK_INT;
        view.setHasTransientState(hasTransientState);
    }

    public static void setImportantForAccessibility(@NonNull View view, int mode) {
        int i = Build.VERSION.SDK_INT;
        view.setImportantForAccessibility(mode);
    }

    public static void setImportantForAutofill(@NonNull View v, int mode) {
        int i = Build.VERSION.SDK_INT;
        v.setImportantForAutofill(mode);
    }

    public static void setKeyboardNavigationCluster(@NonNull View view, boolean isCluster) {
        int i = Build.VERSION.SDK_INT;
        view.setKeyboardNavigationCluster(isCluster);
    }

    public static void setLabelFor(@NonNull View view, @IdRes int labeledId) {
        int i = Build.VERSION.SDK_INT;
        view.setLabelFor(labeledId);
    }

    public static void setLayerPaint(@NonNull View view, Paint paint) {
        int i = Build.VERSION.SDK_INT;
        view.setLayerPaint(paint);
    }

    @Deprecated
    public static void setLayerType(View view, int layerType, Paint paint) {
        view.setLayerType(layerType, paint);
    }

    public static void setLayoutDirection(@NonNull View view, int layoutDirection) {
        int i = Build.VERSION.SDK_INT;
        view.setLayoutDirection(layoutDirection);
    }

    public static void setNestedScrollingEnabled(@NonNull View view, boolean enabled) {
        int i = Build.VERSION.SDK_INT;
        view.setNestedScrollingEnabled(enabled);
    }

    public static void setNextClusterForwardId(@NonNull View view, int nextClusterForwardId) {
        int i = Build.VERSION.SDK_INT;
        view.setNextClusterForwardId(nextClusterForwardId);
    }

    public static void setOnApplyWindowInsetsListener(@NonNull final View v, @Nullable final OnApplyWindowInsetsListener listener) {
        int i = Build.VERSION.SDK_INT;
        Api21Impl.setOnApplyWindowInsetsListener(v, listener);
    }

    public static void setOnReceiveContentListener(@NonNull View view, @Nullable String[] mimeTypes, @Nullable OnReceiveContentListener listener) {
        if (mimeTypes == null || mimeTypes.length == 0) {
            mimeTypes = null;
        }
        boolean z = false;
        if (listener != null) {
            Preconditions.checkArgument(mimeTypes != null, "When the listener is set, MIME types must also be set");
        }
        if (mimeTypes != null) {
            for (String str : mimeTypes) {
                if (str.startsWith("*")) {
                    z = true;
                    break;
                }
            }
            StringBuilder sbA = a.a("A MIME type set here must not start with *: ");
            sbA.append(Arrays.toString(mimeTypes));
            Preconditions.checkArgument(!z, sbA.toString());
        }
        view.setTag(R.id.tag_on_receive_content_mime_types, mimeTypes);
        view.setTag(R.id.tag_on_receive_content_listener, listener);
    }

    @Deprecated
    public static void setOverScrollMode(View v, int overScrollMode) {
        v.setOverScrollMode(overScrollMode);
    }

    public static void setPaddingRelative(@NonNull View view, @Px int start, @Px int top, @Px int end, @Px int bottom) {
        int i = Build.VERSION.SDK_INT;
        view.setPaddingRelative(start, top, end, bottom);
    }

    @Deprecated
    public static void setPivotX(View view, float value) {
        view.setPivotX(value);
    }

    @Deprecated
    public static void setPivotY(View view, float value) {
        view.setPivotY(value);
    }

    public static void setPointerIcon(@NonNull View view, PointerIconCompat pointerIcon) {
        int i = Build.VERSION.SDK_INT;
        view.setPointerIcon((PointerIcon) (pointerIcon != null ? pointerIcon.getPointerIcon() : null));
    }

    @Deprecated
    public static void setRotation(View view, float value) {
        view.setRotation(value);
    }

    @Deprecated
    public static void setRotationX(View view, float value) {
        view.setRotationX(value);
    }

    @Deprecated
    public static void setRotationY(View view, float value) {
        view.setRotationY(value);
    }

    @Deprecated
    public static void setSaveFromParentEnabled(View v, boolean enabled) {
        v.setSaveFromParentEnabled(enabled);
    }

    @Deprecated
    public static void setScaleX(View view, float value) {
        view.setScaleX(value);
    }

    @Deprecated
    public static void setScaleY(View view, float value) {
        view.setScaleY(value);
    }

    @UiThread
    public static void setScreenReaderFocusable(View view, boolean screenReaderFocusable) {
        screenReaderFocusableProperty().set(view, Boolean.valueOf(screenReaderFocusable));
    }

    public static void setScrollIndicators(@NonNull View view, int indicators) {
        int i = Build.VERSION.SDK_INT;
        view.setScrollIndicators(indicators);
    }

    @UiThread
    public static void setStateDescription(@NonNull View view, @Nullable CharSequence stateDescription) {
        int i = Build.VERSION.SDK_INT;
        stateDescriptionProperty().set(view, stateDescription);
    }

    public static void setSystemGestureExclusionRects(@NonNull View view, @NonNull List<Rect> rects) {
        if (Build.VERSION.SDK_INT >= 29) {
            view.setSystemGestureExclusionRects(rects);
        }
    }

    public static void setTooltipText(@NonNull View view, @Nullable CharSequence tooltipText) {
        int i = Build.VERSION.SDK_INT;
        view.setTooltipText(tooltipText);
    }

    public static void setTransitionName(@NonNull View view, String transitionName) {
        int i = Build.VERSION.SDK_INT;
        view.setTransitionName(transitionName);
    }

    @Deprecated
    public static void setTranslationX(View view, float value) {
        view.setTranslationX(value);
    }

    @Deprecated
    public static void setTranslationY(View view, float value) {
        view.setTranslationY(value);
    }

    public static void setTranslationZ(@NonNull View view, float translationZ) {
        int i = Build.VERSION.SDK_INT;
        view.setTranslationZ(translationZ);
    }

    public static void setViewImportanceForAccessibilityIfNeeded(View view) {
        if (getImportantForAccessibility(view) == 0) {
            int i = Build.VERSION.SDK_INT;
            view.setImportantForAccessibility(1);
        }
        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
            if (getImportantForAccessibility((View) parent) == 4) {
                int i2 = Build.VERSION.SDK_INT;
                view.setImportantForAccessibility(2);
                return;
            }
        }
    }

    public static void setWindowInsetsAnimationCallback(@NonNull View view, @Nullable final WindowInsetsAnimationCompat.Callback callback) {
        WindowInsetsAnimationCompat.setCallback(view, callback);
    }

    @Deprecated
    public static void setX(View view, float value) {
        view.setX(value);
    }

    @Deprecated
    public static void setY(View view, float value) {
        view.setY(value);
    }

    public static void setZ(@NonNull View view, float z) {
        int i = Build.VERSION.SDK_INT;
        view.setZ(z);
    }

    public static boolean startDragAndDrop(@NonNull View v, ClipData data, View.DragShadowBuilder shadowBuilder, Object localState, int flags) {
        int i = Build.VERSION.SDK_INT;
        return v.startDragAndDrop(data, shadowBuilder, localState, flags);
    }

    public static boolean startNestedScroll(@NonNull View view, int axes) {
        int i = Build.VERSION.SDK_INT;
        return view.startNestedScroll(axes);
    }

    public static AccessibilityViewProperty<CharSequence> stateDescriptionProperty() {
        return new AccessibilityViewProperty<CharSequence>(R.id.tag_state_description, CharSequence.class, 64, 30) { // from class: androidx.core.view.ViewCompat.4
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
            @RequiresApi(30)
            public CharSequence frameworkGet(View view) {
                return view.getStateDescription();
            }

            @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
            @RequiresApi(30)
            public void frameworkSet(View view, CharSequence value) {
                view.setStateDescription(value);
            }

            @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
            public boolean shouldUpdate(CharSequence oldValue, CharSequence newValue) {
                return !TextUtils.equals(oldValue, newValue);
            }
        };
    }

    public static void stopNestedScroll(@NonNull View view) {
        int i = Build.VERSION.SDK_INT;
        view.stopNestedScroll();
    }

    public static void tickleInvalidationFlag(View view) {
        float translationY = view.getTranslationY();
        view.setTranslationY(1.0f + translationY);
        view.setTranslationY(translationY);
    }

    public static void updateDragShadow(@NonNull View v, View.DragShadowBuilder shadowBuilder) {
        int i = Build.VERSION.SDK_INT;
        v.updateDragShadow(shadowBuilder);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean dispatchNestedPreScroll(@NonNull View view, int dx, int dy, @Nullable int[] consumed, @Nullable int[] offsetInWindow, int type) {
        if (view instanceof NestedScrollingChild2) {
            return ((NestedScrollingChild2) view).dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow, type);
        }
        if (type == 0) {
            return dispatchNestedPreScroll(view, dx, dy, consumed, offsetInWindow);
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void dispatchNestedScroll(@NonNull View view, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @Nullable int[] offsetInWindow, int type, @NonNull int[] consumed) {
        if (view instanceof NestedScrollingChild3) {
            ((NestedScrollingChild3) view).dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow, type, consumed);
        } else {
            dispatchNestedScroll(view, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow, type);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean hasNestedScrollingParent(@NonNull View view, int type) {
        if (view instanceof NestedScrollingChild2) {
            ((NestedScrollingChild2) view).hasNestedScrollingParent(type);
            return false;
        }
        if (type == 0) {
            return hasNestedScrollingParent(view);
        }
        return false;
    }

    public static void postInvalidateOnAnimation(@NonNull View view, int left, int top, int right, int bottom) {
        int i = Build.VERSION.SDK_INT;
        view.postInvalidateOnAnimation(left, top, right, bottom);
    }

    public static void setScrollIndicators(@NonNull View view, int indicators, int mask) {
        int i = Build.VERSION.SDK_INT;
        view.setScrollIndicators(indicators, mask);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean startNestedScroll(@NonNull View view, int axes, int type) {
        if (view instanceof NestedScrollingChild2) {
            return ((NestedScrollingChild2) view).startNestedScroll(axes, type);
        }
        if (type == 0) {
            return startNestedScroll(view, axes);
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void stopNestedScroll(@NonNull View view, int type) {
        if (view instanceof NestedScrollingChild2) {
            ((NestedScrollingChild2) view).stopNestedScroll(type);
        } else if (type == 0) {
            int i = Build.VERSION.SDK_INT;
            view.stopNestedScroll();
        }
    }

    public static void addAccessibilityAction(@NonNull View view, @NonNull AccessibilityNodeInfoCompat.AccessibilityActionCompat action) {
        int i = Build.VERSION.SDK_INT;
        getOrCreateAccessibilityDelegateCompat(view);
        removeActionWithId(action.getId(), view);
        getActionList(view).add(action);
        notifyViewAccessibilityStateChangedIfNeeded(view, 0);
    }

    static abstract class AccessibilityViewProperty<T> {
        public final int mContentChangeType;
        public final int mFrameworkMinimumSdk;
        public final int mTagKey;
        public final Class<T> mType;

        public AccessibilityViewProperty(int tagKey, Class<T> type, int frameworkMinimumSdk) {
            this.mTagKey = tagKey;
            this.mType = type;
            this.mContentChangeType = 0;
            this.mFrameworkMinimumSdk = frameworkMinimumSdk;
        }

        private boolean extrasAvailable() {
            int i = Build.VERSION.SDK_INT;
            return true;
        }

        private boolean frameworkAvailable() {
            return Build.VERSION.SDK_INT >= this.mFrameworkMinimumSdk;
        }

        public boolean booleanNullToFalseEquals(Boolean a2, Boolean b2) {
            return (a2 == null ? false : a2.booleanValue()) == (b2 == null ? false : b2.booleanValue());
        }

        public abstract T frameworkGet(View view);

        public abstract void frameworkSet(View view, T value);

        public T get(View view) {
            if (frameworkAvailable()) {
                return frameworkGet(view);
            }
            int i = Build.VERSION.SDK_INT;
            T t = (T) view.getTag(this.mTagKey);
            if (this.mType.isInstance(t)) {
                return t;
            }
            return null;
        }

        public void set(View view, T value) {
            if (frameworkAvailable()) {
                frameworkSet(view, value);
                return;
            }
            int i = Build.VERSION.SDK_INT;
            if (shouldUpdate(get(view), value)) {
                ViewCompat.getOrCreateAccessibilityDelegateCompat(view);
                view.setTag(this.mTagKey, value);
                ViewCompat.notifyViewAccessibilityStateChangedIfNeeded(view, this.mContentChangeType);
            }
        }

        public boolean shouldUpdate(T oldValue, T newValue) {
            return !newValue.equals(oldValue);
        }

        public AccessibilityViewProperty(int tagKey, Class<T> type, int contentChangeType, int frameworkMinimumSdk) {
            this.mTagKey = tagKey;
            this.mType = type;
            this.mContentChangeType = contentChangeType;
            this.mFrameworkMinimumSdk = frameworkMinimumSdk;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean dispatchNestedScroll(@NonNull View view, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @Nullable int[] offsetInWindow, int type) {
        if (view instanceof NestedScrollingChild2) {
            return ((NestedScrollingChild2) view).dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow, type);
        }
        if (type == 0) {
            return dispatchNestedScroll(view, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
        }
        return false;
    }
}
