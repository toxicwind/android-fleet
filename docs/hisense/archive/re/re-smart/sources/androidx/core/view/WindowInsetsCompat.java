package androidx.core.view;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.WindowInsets;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.Insets;
import androidx.core.util.ObjectsCompat;
import b.a.a.a.a;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class WindowInsetsCompat {

    @NonNull
    public static final WindowInsetsCompat CONSUMED;
    public static final String TAG = "WindowInsetsCompat";
    public final Impl mImpl;

    @RequiresApi(21)
    static class Api21ReflectionHolder {
        public static Field sContentInsets;
        public static boolean sReflectionSucceeded;
        public static Field sStableInsets;
        public static Field sViewAttachInfoField;

        static {
            try {
                sViewAttachInfoField = View.class.getDeclaredField("mAttachInfo");
                sViewAttachInfoField.setAccessible(true);
                Class<?> cls = Class.forName("android.view.View$AttachInfo");
                sStableInsets = cls.getDeclaredField("mStableInsets");
                sStableInsets.setAccessible(true);
                sContentInsets = cls.getDeclaredField("mContentInsets");
                sContentInsets.setAccessible(true);
                sReflectionSucceeded = true;
            } catch (ReflectiveOperationException e2) {
                StringBuilder sbA = a.a("Failed to get visible insets from AttachInfo ");
                sbA.append(e2.getMessage());
                Log.w(WindowInsetsCompat.TAG, sbA.toString(), e2);
            }
        }

        @Nullable
        public static WindowInsetsCompat getRootWindowInsets(@NonNull View v) {
            if (sReflectionSucceeded && v.isAttachedToWindow()) {
                try {
                    Object obj = sViewAttachInfoField.get(v.getRootView());
                    if (obj != null) {
                        Rect rect = (Rect) sStableInsets.get(obj);
                        Rect rect2 = (Rect) sContentInsets.get(obj);
                        if (rect != null && rect2 != null) {
                            WindowInsetsCompat windowInsetsCompatBuild = new Builder().setStableInsets(Insets.of(rect)).setSystemWindowInsets(Insets.of(rect2)).build();
                            windowInsetsCompatBuild.setRootWindowInsets(windowInsetsCompatBuild);
                            windowInsetsCompatBuild.copyRootViewBounds(v.getRootView());
                            return windowInsetsCompatBuild;
                        }
                    }
                } catch (IllegalAccessException e2) {
                    StringBuilder sbA = a.a("Failed to get insets from AttachInfo. ");
                    sbA.append(e2.getMessage());
                    Log.w(WindowInsetsCompat.TAG, sbA.toString(), e2);
                }
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(30)
    static class BuilderImpl30 extends BuilderImpl29 {
        public BuilderImpl30() {
        }

        @Override // androidx.core.view.WindowInsetsCompat.BuilderImpl
        public void setInsets(int typeMask, @NonNull Insets insets) {
            this.mPlatBuilder.setInsets(TypeImpl30.toPlatformType(typeMask), insets.toPlatformInsets());
        }

        @Override // androidx.core.view.WindowInsetsCompat.BuilderImpl
        public void setInsetsIgnoringVisibility(int typeMask, @NonNull Insets insets) {
            this.mPlatBuilder.setInsetsIgnoringVisibility(TypeImpl30.toPlatformType(typeMask), insets.toPlatformInsets());
        }

        @Override // androidx.core.view.WindowInsetsCompat.BuilderImpl
        public void setVisible(int typeMask, boolean visible) {
            this.mPlatBuilder.setVisible(TypeImpl30.toPlatformType(typeMask), visible);
        }

        public BuilderImpl30(@NonNull WindowInsetsCompat insets) {
            super(insets);
        }
    }

    private static class Impl {

        @NonNull
        public static final WindowInsetsCompat CONSUMED = new Builder().build().consumeDisplayCutout().consumeStableInsets().consumeSystemWindowInsets();
        public final WindowInsetsCompat mHost;

        public Impl(@NonNull WindowInsetsCompat host) {
            this.mHost = host;
        }

        @NonNull
        public WindowInsetsCompat consumeDisplayCutout() {
            return this.mHost;
        }

        @NonNull
        public WindowInsetsCompat consumeStableInsets() {
            return this.mHost;
        }

        @NonNull
        public WindowInsetsCompat consumeSystemWindowInsets() {
            return this.mHost;
        }

        public void copyRootViewBounds(@NonNull View rootView) {
        }

        public void copyWindowDataInto(@NonNull WindowInsetsCompat other) {
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Impl)) {
                return false;
            }
            Impl impl = (Impl) o;
            return isRound() == impl.isRound() && isConsumed() == impl.isConsumed() && ObjectsCompat.equals(getSystemWindowInsets(), impl.getSystemWindowInsets()) && ObjectsCompat.equals(getStableInsets(), impl.getStableInsets()) && ObjectsCompat.equals(getDisplayCutout(), impl.getDisplayCutout());
        }

        @Nullable
        public DisplayCutoutCompat getDisplayCutout() {
            return null;
        }

        @NonNull
        public Insets getInsets(int typeMask) {
            return Insets.NONE;
        }

        @NonNull
        public Insets getInsetsIgnoringVisibility(int typeMask) {
            if ((typeMask & 8) == 0) {
                return Insets.NONE;
            }
            throw new IllegalArgumentException("Unable to query the maximum insets for IME");
        }

        @NonNull
        public Insets getMandatorySystemGestureInsets() {
            return getSystemWindowInsets();
        }

        @NonNull
        public Insets getStableInsets() {
            return Insets.NONE;
        }

        @NonNull
        public Insets getSystemGestureInsets() {
            return getSystemWindowInsets();
        }

        @NonNull
        public Insets getSystemWindowInsets() {
            return Insets.NONE;
        }

        @NonNull
        public Insets getTappableElementInsets() {
            return getSystemWindowInsets();
        }

        public int hashCode() {
            return ObjectsCompat.hash(Boolean.valueOf(isRound()), Boolean.valueOf(isConsumed()), getSystemWindowInsets(), getStableInsets(), getDisplayCutout());
        }

        @NonNull
        public WindowInsetsCompat inset(int left, int top, int right, int bottom) {
            return CONSUMED;
        }

        public boolean isConsumed() {
            return false;
        }

        public boolean isRound() {
            return false;
        }

        public boolean isVisible(int typeMask) {
            return true;
        }

        public void setOverriddenInsets(Insets[] insetsTypeMask) {
        }

        public void setRootViewData(@NonNull Insets visibleInsets) {
        }

        public void setRootWindowInsets(@Nullable WindowInsetsCompat rootWindowInsets) {
        }

        public void setStableInsets(Insets stableInsets) {
        }
    }

    @RequiresApi(20)
    private static class Impl20 extends Impl {
        public static Class<?> sAttachInfoClass = null;
        public static Field sAttachInfoField = null;
        public static Method sGetViewRootImplMethod = null;
        public static Class<?> sViewRootImplClass = null;
        public static Field sVisibleInsetsField = null;
        public static boolean sVisibleRectReflectionFetched = false;
        public Insets[] mOverriddenInsets;

        @NonNull
        public final WindowInsets mPlatformInsets;
        public Insets mRootViewVisibleInsets;
        public WindowInsetsCompat mRootWindowInsets;
        public Insets mSystemWindowInsets;

        public Impl20(@NonNull WindowInsetsCompat host, @NonNull WindowInsets insets) {
            super(host);
            this.mSystemWindowInsets = null;
            this.mPlatformInsets = insets;
        }

        private Insets getRootStableInsets() {
            WindowInsetsCompat windowInsetsCompat = this.mRootWindowInsets;
            return windowInsetsCompat != null ? windowInsetsCompat.getStableInsets() : Insets.NONE;
        }

        @Nullable
        private Insets getVisibleInsets(@NonNull View rootView) {
            if (Build.VERSION.SDK_INT >= 30) {
                throw new UnsupportedOperationException("getVisibleInsets() should not be called on API >= 30. Use WindowInsets.isVisible() instead.");
            }
            if (!sVisibleRectReflectionFetched) {
                loadReflectionField();
            }
            Method method = sGetViewRootImplMethod;
            if (method != null && sAttachInfoClass != null && sVisibleInsetsField != null) {
                try {
                    Object objInvoke = method.invoke(rootView, new Object[0]);
                    if (objInvoke == null) {
                        Log.w(WindowInsetsCompat.TAG, "Failed to get visible insets. getViewRootImpl() returned null from the provided view. This means that the view is either not attached or the method has been overridden", new NullPointerException());
                        return null;
                    }
                    Rect rect = (Rect) sVisibleInsetsField.get(sAttachInfoField.get(objInvoke));
                    if (rect != null) {
                        return Insets.of(rect);
                    }
                    return null;
                } catch (ReflectiveOperationException e2) {
                    StringBuilder sbA = a.a("Failed to get visible insets. (Reflection error). ");
                    sbA.append(e2.getMessage());
                    Log.e(WindowInsetsCompat.TAG, sbA.toString(), e2);
                }
            }
            return null;
        }

        @SuppressLint({"PrivateApi"})
        public static void loadReflectionField() {
            try {
                sGetViewRootImplMethod = View.class.getDeclaredMethod("getViewRootImpl", new Class[0]);
                sViewRootImplClass = Class.forName("android.view.ViewRootImpl");
                sAttachInfoClass = Class.forName("android.view.View$AttachInfo");
                sVisibleInsetsField = sAttachInfoClass.getDeclaredField("mVisibleInsets");
                sAttachInfoField = sViewRootImplClass.getDeclaredField("mAttachInfo");
                sVisibleInsetsField.setAccessible(true);
                sAttachInfoField.setAccessible(true);
            } catch (ReflectiveOperationException e2) {
                StringBuilder sbA = a.a("Failed to get visible insets. (Reflection error). ");
                sbA.append(e2.getMessage());
                Log.e(WindowInsetsCompat.TAG, sbA.toString(), e2);
            }
            sVisibleRectReflectionFetched = true;
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        public void copyRootViewBounds(@NonNull View rootView) {
            Insets visibleInsets = getVisibleInsets(rootView);
            if (visibleInsets == null) {
                visibleInsets = Insets.NONE;
            }
            setRootViewData(visibleInsets);
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        public void copyWindowDataInto(@NonNull WindowInsetsCompat other) {
            other.setRootWindowInsets(this.mRootWindowInsets);
            other.setRootViewData(this.mRootViewVisibleInsets);
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        public boolean equals(Object o) {
            if (super.equals(o)) {
                return Objects.equals(this.mRootViewVisibleInsets, ((Impl20) o).mRootViewVisibleInsets);
            }
            return false;
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        @NonNull
        public Insets getInsets(int typeMask) {
            return getInsets(typeMask, false);
        }

        @NonNull
        public Insets getInsetsForType(int type, boolean ignoreVisibility) {
            Insets stableInsets;
            int i;
            if (type == 1) {
                return ignoreVisibility ? Insets.of(0, Math.max(getRootStableInsets().top, getSystemWindowInsets().top), 0, 0) : Insets.of(0, getSystemWindowInsets().top, 0, 0);
            }
            if (type == 2) {
                if (ignoreVisibility) {
                    Insets rootStableInsets = getRootStableInsets();
                    Insets stableInsets2 = getStableInsets();
                    return Insets.of(Math.max(rootStableInsets.left, stableInsets2.left), 0, Math.max(rootStableInsets.right, stableInsets2.right), Math.max(rootStableInsets.bottom, stableInsets2.bottom));
                }
                Insets systemWindowInsets = getSystemWindowInsets();
                WindowInsetsCompat windowInsetsCompat = this.mRootWindowInsets;
                stableInsets = windowInsetsCompat != null ? windowInsetsCompat.getStableInsets() : null;
                int iMin = systemWindowInsets.bottom;
                if (stableInsets != null) {
                    iMin = Math.min(iMin, stableInsets.bottom);
                }
                return Insets.of(systemWindowInsets.left, 0, systemWindowInsets.right, iMin);
            }
            if (type != 8) {
                if (type == 16) {
                    return getSystemGestureInsets();
                }
                if (type == 32) {
                    return getMandatorySystemGestureInsets();
                }
                if (type == 64) {
                    return getTappableElementInsets();
                }
                if (type != 128) {
                    return Insets.NONE;
                }
                WindowInsetsCompat windowInsetsCompat2 = this.mRootWindowInsets;
                DisplayCutoutCompat displayCutout = windowInsetsCompat2 != null ? windowInsetsCompat2.getDisplayCutout() : getDisplayCutout();
                return displayCutout != null ? Insets.of(displayCutout.getSafeInsetLeft(), displayCutout.getSafeInsetTop(), displayCutout.getSafeInsetRight(), displayCutout.getSafeInsetBottom()) : Insets.NONE;
            }
            Insets[] insetsArr = this.mOverriddenInsets;
            stableInsets = insetsArr != null ? insetsArr[Type.indexOf(8)] : null;
            if (stableInsets != null) {
                return stableInsets;
            }
            Insets systemWindowInsets2 = getSystemWindowInsets();
            Insets rootStableInsets2 = getRootStableInsets();
            int i2 = systemWindowInsets2.bottom;
            if (i2 > rootStableInsets2.bottom) {
                return Insets.of(0, 0, 0, i2);
            }
            Insets insets = this.mRootViewVisibleInsets;
            return (insets == null || insets.equals(Insets.NONE) || (i = this.mRootViewVisibleInsets.bottom) <= rootStableInsets2.bottom) ? Insets.NONE : Insets.of(0, 0, 0, i);
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        @NonNull
        public Insets getInsetsIgnoringVisibility(int typeMask) {
            return getInsets(typeMask, true);
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        @NonNull
        public final Insets getSystemWindowInsets() {
            if (this.mSystemWindowInsets == null) {
                this.mSystemWindowInsets = Insets.of(this.mPlatformInsets.getSystemWindowInsetLeft(), this.mPlatformInsets.getSystemWindowInsetTop(), this.mPlatformInsets.getSystemWindowInsetRight(), this.mPlatformInsets.getSystemWindowInsetBottom());
            }
            return this.mSystemWindowInsets;
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        @NonNull
        public WindowInsetsCompat inset(int left, int top, int right, int bottom) {
            BuilderImpl builderImpl29;
            WindowInsetsCompat windowInsetsCompat = WindowInsetsCompat.toWindowInsetsCompat(this.mPlatformInsets);
            int i = Build.VERSION.SDK_INT;
            if (i >= 30) {
                builderImpl29 = new BuilderImpl30(windowInsetsCompat);
            } else {
                builderImpl29 = i >= 29 ? new BuilderImpl29(windowInsetsCompat) : new BuilderImpl20(windowInsetsCompat);
            }
            builderImpl29.setSystemWindowInsets(WindowInsetsCompat.insetInsets(getSystemWindowInsets(), left, top, right, bottom));
            builderImpl29.setStableInsets(WindowInsetsCompat.insetInsets(getStableInsets(), left, top, right, bottom));
            return builderImpl29.build();
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        public boolean isRound() {
            return this.mPlatformInsets.isRound();
        }

        public boolean isTypeVisible(final int type) {
            if (type != 1 && type != 2) {
                if (type == 4) {
                    return false;
                }
                if (type != 8 && type != 128) {
                    return true;
                }
            }
            return !getInsetsForType(type, false).equals(Insets.NONE);
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        @SuppressLint({"WrongConstant"})
        public boolean isVisible(final int typeMask) {
            for (int i = 1; i <= 256; i <<= 1) {
                if ((typeMask & i) != 0 && !isTypeVisible(i)) {
                    return false;
                }
            }
            return true;
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        public void setOverriddenInsets(Insets[] insetsTypeMask) {
            this.mOverriddenInsets = insetsTypeMask;
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        public void setRootViewData(@NonNull Insets visibleInsets) {
            this.mRootViewVisibleInsets = visibleInsets;
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        public void setRootWindowInsets(@Nullable WindowInsetsCompat rootWindowInsets) {
            this.mRootWindowInsets = rootWindowInsets;
        }

        @NonNull
        @SuppressLint({"WrongConstant"})
        private Insets getInsets(final int typeMask, final boolean ignoreVisibility) {
            Insets insetsMax = Insets.NONE;
            for (int i = 1; i <= 256; i <<= 1) {
                if ((typeMask & i) != 0) {
                    insetsMax = Insets.max(insetsMax, getInsetsForType(i, ignoreVisibility));
                }
            }
            return insetsMax;
        }

        public Impl20(@NonNull WindowInsetsCompat host, @NonNull Impl20 other) {
            WindowInsets windowInsets = new WindowInsets(other.mPlatformInsets);
            super(host);
            this.mSystemWindowInsets = null;
            this.mPlatformInsets = windowInsets;
        }
    }

    @RequiresApi(28)
    private static class Impl28 extends Impl21 {
        public Impl28(@NonNull WindowInsetsCompat host, @NonNull WindowInsets insets) {
            super(host, insets);
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        @NonNull
        public WindowInsetsCompat consumeDisplayCutout() {
            return WindowInsetsCompat.toWindowInsetsCompat(this.mPlatformInsets.consumeDisplayCutout());
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl20, androidx.core.view.WindowInsetsCompat.Impl
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Impl28)) {
                return false;
            }
            Impl28 impl28 = (Impl28) o;
            return Objects.equals(this.mPlatformInsets, impl28.mPlatformInsets) && Objects.equals(this.mRootViewVisibleInsets, impl28.mRootViewVisibleInsets);
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        @Nullable
        public DisplayCutoutCompat getDisplayCutout() {
            return DisplayCutoutCompat.wrap(this.mPlatformInsets.getDisplayCutout());
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        public int hashCode() {
            return this.mPlatformInsets.hashCode();
        }

        public Impl28(@NonNull WindowInsetsCompat host, @NonNull Impl28 other) {
            super(host, other);
        }
    }

    @RequiresApi(30)
    private static class Impl30 extends Impl29 {

        @NonNull
        public static final WindowInsetsCompat CONSUMED = WindowInsetsCompat.toWindowInsetsCompat(WindowInsets.CONSUMED);

        public Impl30(@NonNull WindowInsetsCompat host, @NonNull WindowInsets insets) {
            super(host, insets);
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl20, androidx.core.view.WindowInsetsCompat.Impl
        public final void copyRootViewBounds(@NonNull View rootView) {
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl20, androidx.core.view.WindowInsetsCompat.Impl
        @NonNull
        public Insets getInsets(int typeMask) {
            return Insets.toCompatInsets(this.mPlatformInsets.getInsets(TypeImpl30.toPlatformType(typeMask)));
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl20, androidx.core.view.WindowInsetsCompat.Impl
        @NonNull
        public Insets getInsetsIgnoringVisibility(int typeMask) {
            return Insets.toCompatInsets(this.mPlatformInsets.getInsetsIgnoringVisibility(TypeImpl30.toPlatformType(typeMask)));
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl20, androidx.core.view.WindowInsetsCompat.Impl
        public boolean isVisible(int typeMask) {
            return this.mPlatformInsets.isVisible(TypeImpl30.toPlatformType(typeMask));
        }

        public Impl30(@NonNull WindowInsetsCompat host, @NonNull Impl30 other) {
            super(host, other);
        }
    }

    public static final class Type {
        public static final int CAPTION_BAR = 4;
        public static final int DISPLAY_CUTOUT = 128;
        public static final int FIRST = 1;
        public static final int IME = 8;
        public static final int LAST = 256;
        public static final int MANDATORY_SYSTEM_GESTURES = 32;
        public static final int NAVIGATION_BARS = 2;
        public static final int SIZE = 9;
        public static final int STATUS_BARS = 1;
        public static final int SYSTEM_GESTURES = 16;
        public static final int TAPPABLE_ELEMENT = 64;
        public static final int WINDOW_DECOR = 256;

        @Retention(RetentionPolicy.SOURCE)
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public @interface InsetsType {
        }

        @SuppressLint({"WrongConstant"})
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public static int all() {
            return -1;
        }

        public static int captionBar() {
            return 4;
        }

        public static int displayCutout() {
            return 128;
        }

        public static int ime() {
            return 8;
        }

        public static int indexOf(int type) {
            if (type == 1) {
                return 0;
            }
            if (type == 2) {
                return 1;
            }
            if (type == 4) {
                return 2;
            }
            if (type == 8) {
                return 3;
            }
            if (type == 16) {
                return 4;
            }
            if (type == 32) {
                return 5;
            }
            if (type == 64) {
                return 6;
            }
            if (type == 128) {
                return 7;
            }
            if (type == 256) {
                return 8;
            }
            throw new IllegalArgumentException(a.a("type needs to be >= FIRST and <= LAST, type=", type));
        }

        public static int mandatorySystemGestures() {
            return 32;
        }

        public static int navigationBars() {
            return 2;
        }

        public static int statusBars() {
            return 1;
        }

        public static int systemBars() {
            return 7;
        }

        public static int systemGestures() {
            return 16;
        }

        public static int tappableElement() {
            return 64;
        }
    }

    @RequiresApi(30)
    private static final class TypeImpl30 {
        public static int toPlatformType(final int typeMask) {
            int iStatusBars;
            int i = 0;
            for (int i2 = 1; i2 <= 256; i2 <<= 1) {
                if ((typeMask & i2) != 0) {
                    if (i2 == 1) {
                        iStatusBars = WindowInsets.Type.statusBars();
                    } else if (i2 == 2) {
                        iStatusBars = WindowInsets.Type.navigationBars();
                    } else if (i2 == 4) {
                        iStatusBars = WindowInsets.Type.captionBar();
                    } else if (i2 == 8) {
                        iStatusBars = WindowInsets.Type.ime();
                    } else if (i2 == 16) {
                        iStatusBars = WindowInsets.Type.systemGestures();
                    } else if (i2 == 32) {
                        iStatusBars = WindowInsets.Type.mandatorySystemGestures();
                    } else if (i2 == 64) {
                        iStatusBars = WindowInsets.Type.tappableElement();
                    } else if (i2 == 128) {
                        iStatusBars = WindowInsets.Type.displayCutout();
                    }
                    i |= iStatusBars;
                }
            }
            return i;
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 30) {
            CONSUMED = Impl30.CONSUMED;
        } else {
            CONSUMED = Impl.CONSUMED;
        }
    }

    @RequiresApi(20)
    public WindowInsetsCompat(@NonNull WindowInsets insets) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 30) {
            this.mImpl = new Impl30(this, insets);
            return;
        }
        if (i >= 29) {
            this.mImpl = new Impl29(this, insets);
        } else if (i >= 28) {
            this.mImpl = new Impl28(this, insets);
        } else {
            this.mImpl = new Impl21(this, insets);
        }
    }

    public static Insets insetInsets(@NonNull Insets insets, int left, int top, int right, int bottom) {
        int iMax = Math.max(0, insets.left - left);
        int iMax2 = Math.max(0, insets.top - top);
        int iMax3 = Math.max(0, insets.right - right);
        int iMax4 = Math.max(0, insets.bottom - bottom);
        return (iMax == left && iMax2 == top && iMax3 == right && iMax4 == bottom) ? insets : Insets.of(iMax, iMax2, iMax3, iMax4);
    }

    @NonNull
    @RequiresApi(20)
    public static WindowInsetsCompat toWindowInsetsCompat(@NonNull WindowInsets insets) {
        return toWindowInsetsCompat(insets, null);
    }

    @NonNull
    @Deprecated
    public WindowInsetsCompat consumeDisplayCutout() {
        return this.mImpl.consumeDisplayCutout();
    }

    @NonNull
    @Deprecated
    public WindowInsetsCompat consumeStableInsets() {
        return this.mImpl.consumeStableInsets();
    }

    @NonNull
    @Deprecated
    public WindowInsetsCompat consumeSystemWindowInsets() {
        return this.mImpl.consumeSystemWindowInsets();
    }

    public void copyRootViewBounds(@NonNull View rootView) {
        this.mImpl.copyRootViewBounds(rootView);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof WindowInsetsCompat) {
            return ObjectsCompat.equals(this.mImpl, ((WindowInsetsCompat) o).mImpl);
        }
        return false;
    }

    @Nullable
    public DisplayCutoutCompat getDisplayCutout() {
        return this.mImpl.getDisplayCutout();
    }

    @NonNull
    public Insets getInsets(int typeMask) {
        return this.mImpl.getInsets(typeMask);
    }

    @NonNull
    public Insets getInsetsIgnoringVisibility(int typeMask) {
        return this.mImpl.getInsetsIgnoringVisibility(typeMask);
    }

    @NonNull
    @Deprecated
    public Insets getMandatorySystemGestureInsets() {
        return this.mImpl.getMandatorySystemGestureInsets();
    }

    @Deprecated
    public int getStableInsetBottom() {
        return this.mImpl.getStableInsets().bottom;
    }

    @Deprecated
    public int getStableInsetLeft() {
        return this.mImpl.getStableInsets().left;
    }

    @Deprecated
    public int getStableInsetRight() {
        return this.mImpl.getStableInsets().right;
    }

    @Deprecated
    public int getStableInsetTop() {
        return this.mImpl.getStableInsets().top;
    }

    @NonNull
    @Deprecated
    public Insets getStableInsets() {
        return this.mImpl.getStableInsets();
    }

    @NonNull
    @Deprecated
    public Insets getSystemGestureInsets() {
        return this.mImpl.getSystemGestureInsets();
    }

    @Deprecated
    public int getSystemWindowInsetBottom() {
        return this.mImpl.getSystemWindowInsets().bottom;
    }

    @Deprecated
    public int getSystemWindowInsetLeft() {
        return this.mImpl.getSystemWindowInsets().left;
    }

    @Deprecated
    public int getSystemWindowInsetRight() {
        return this.mImpl.getSystemWindowInsets().right;
    }

    @Deprecated
    public int getSystemWindowInsetTop() {
        return this.mImpl.getSystemWindowInsets().top;
    }

    @NonNull
    @Deprecated
    public Insets getSystemWindowInsets() {
        return this.mImpl.getSystemWindowInsets();
    }

    @NonNull
    @Deprecated
    public Insets getTappableElementInsets() {
        return this.mImpl.getTappableElementInsets();
    }

    public boolean hasInsets() {
        return (getInsets(-1).equals(Insets.NONE) && getInsetsIgnoringVisibility(-9).equals(Insets.NONE) && getDisplayCutout() == null) ? false : true;
    }

    @Deprecated
    public boolean hasStableInsets() {
        return !this.mImpl.getStableInsets().equals(Insets.NONE);
    }

    @Deprecated
    public boolean hasSystemWindowInsets() {
        return !this.mImpl.getSystemWindowInsets().equals(Insets.NONE);
    }

    public int hashCode() {
        Impl impl = this.mImpl;
        if (impl == null) {
            return 0;
        }
        return impl.hashCode();
    }

    @NonNull
    public WindowInsetsCompat inset(@NonNull Insets insets) {
        return inset(insets.left, insets.top, insets.right, insets.bottom);
    }

    public boolean isConsumed() {
        return this.mImpl.isConsumed();
    }

    public boolean isRound() {
        return this.mImpl.isRound();
    }

    public boolean isVisible(int typeMask) {
        return this.mImpl.isVisible(typeMask);
    }

    @NonNull
    @Deprecated
    public WindowInsetsCompat replaceSystemWindowInsets(int left, int top, int right, int bottom) {
        return new Builder(this).setSystemWindowInsets(Insets.of(left, top, right, bottom)).build();
    }

    public void setOverriddenInsets(Insets[] insetsTypeMask) {
        this.mImpl.setOverriddenInsets(insetsTypeMask);
    }

    public void setRootViewData(@NonNull Insets visibleInsets) {
        this.mImpl.setRootViewData(visibleInsets);
    }

    public void setRootWindowInsets(@Nullable WindowInsetsCompat rootWindowInsets) {
        this.mImpl.setRootWindowInsets(rootWindowInsets);
    }

    public void setStableInsets(@Nullable Insets stableInsets) {
        this.mImpl.setStableInsets(stableInsets);
    }

    @Nullable
    @RequiresApi(20)
    public WindowInsets toWindowInsets() {
        Impl impl = this.mImpl;
        if (impl instanceof Impl20) {
            return ((Impl20) impl).mPlatformInsets;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(api = 20)
    static class BuilderImpl20 extends BuilderImpl {
        public static Constructor<WindowInsets> sConstructor = null;
        public static boolean sConstructorFetched = false;
        public static Field sConsumedField = null;
        public static boolean sConsumedFieldFetched = false;
        public WindowInsets mPlatformInsets;
        public Insets mStableInsets;

        public BuilderImpl20() {
            this.mPlatformInsets = createWindowInsetsInstance();
        }

        @Nullable
        public static WindowInsets createWindowInsetsInstance() {
            if (!sConsumedFieldFetched) {
                try {
                    sConsumedField = WindowInsets.class.getDeclaredField("CONSUMED");
                } catch (ReflectiveOperationException e2) {
                    Log.i(WindowInsetsCompat.TAG, "Could not retrieve WindowInsets.CONSUMED field", e2);
                }
                sConsumedFieldFetched = true;
            }
            Field field = sConsumedField;
            if (field != null) {
                try {
                    WindowInsets windowInsets = (WindowInsets) field.get(null);
                    if (windowInsets != null) {
                        return new WindowInsets(windowInsets);
                    }
                } catch (ReflectiveOperationException e3) {
                    Log.i(WindowInsetsCompat.TAG, "Could not get value from WindowInsets.CONSUMED field", e3);
                }
            }
            if (!sConstructorFetched) {
                try {
                    sConstructor = WindowInsets.class.getConstructor(Rect.class);
                } catch (ReflectiveOperationException e4) {
                    Log.i(WindowInsetsCompat.TAG, "Could not retrieve WindowInsets(Rect) constructor", e4);
                }
                sConstructorFetched = true;
            }
            Constructor<WindowInsets> constructor = sConstructor;
            if (constructor != null) {
                try {
                    return constructor.newInstance(new Rect());
                } catch (ReflectiveOperationException e5) {
                    Log.i(WindowInsetsCompat.TAG, "Could not invoke WindowInsets(Rect) constructor", e5);
                }
            }
            return null;
        }

        @Override // androidx.core.view.WindowInsetsCompat.BuilderImpl
        @NonNull
        public WindowInsetsCompat build() {
            applyInsetTypes();
            WindowInsetsCompat windowInsetsCompat = WindowInsetsCompat.toWindowInsetsCompat(this.mPlatformInsets);
            windowInsetsCompat.setOverriddenInsets(this.mInsetsTypeMask);
            windowInsetsCompat.setStableInsets(this.mStableInsets);
            return windowInsetsCompat;
        }

        @Override // androidx.core.view.WindowInsetsCompat.BuilderImpl
        public void setStableInsets(@Nullable Insets insets) {
            this.mStableInsets = insets;
        }

        @Override // androidx.core.view.WindowInsetsCompat.BuilderImpl
        public void setSystemWindowInsets(@NonNull Insets insets) {
            WindowInsets windowInsets = this.mPlatformInsets;
            if (windowInsets != null) {
                this.mPlatformInsets = windowInsets.replaceSystemWindowInsets(insets.left, insets.top, insets.right, insets.bottom);
            }
        }

        public BuilderImpl20(@NonNull WindowInsetsCompat insets) {
            super(insets);
            this.mPlatformInsets = insets.toWindowInsets();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(api = 29)
    static class BuilderImpl29 extends BuilderImpl {
        public final WindowInsets.Builder mPlatBuilder;

        public BuilderImpl29() {
            this.mPlatBuilder = new WindowInsets.Builder();
        }

        @Override // androidx.core.view.WindowInsetsCompat.BuilderImpl
        @NonNull
        public WindowInsetsCompat build() {
            applyInsetTypes();
            WindowInsetsCompat windowInsetsCompat = WindowInsetsCompat.toWindowInsetsCompat(this.mPlatBuilder.build());
            windowInsetsCompat.setOverriddenInsets(this.mInsetsTypeMask);
            return windowInsetsCompat;
        }

        @Override // androidx.core.view.WindowInsetsCompat.BuilderImpl
        public void setDisplayCutout(@Nullable DisplayCutoutCompat displayCutout) {
            this.mPlatBuilder.setDisplayCutout(displayCutout != null ? displayCutout.unwrap() : null);
        }

        @Override // androidx.core.view.WindowInsetsCompat.BuilderImpl
        public void setMandatorySystemGestureInsets(@NonNull Insets insets) {
            this.mPlatBuilder.setMandatorySystemGestureInsets(insets.toPlatformInsets());
        }

        @Override // androidx.core.view.WindowInsetsCompat.BuilderImpl
        public void setStableInsets(@NonNull Insets insets) {
            this.mPlatBuilder.setStableInsets(insets.toPlatformInsets());
        }

        @Override // androidx.core.view.WindowInsetsCompat.BuilderImpl
        public void setSystemGestureInsets(@NonNull Insets insets) {
            this.mPlatBuilder.setSystemGestureInsets(insets.toPlatformInsets());
        }

        @Override // androidx.core.view.WindowInsetsCompat.BuilderImpl
        public void setSystemWindowInsets(@NonNull Insets insets) {
            this.mPlatBuilder.setSystemWindowInsets(insets.toPlatformInsets());
        }

        @Override // androidx.core.view.WindowInsetsCompat.BuilderImpl
        public void setTappableElementInsets(@NonNull Insets insets) {
            this.mPlatBuilder.setTappableElementInsets(insets.toPlatformInsets());
        }

        public BuilderImpl29(@NonNull WindowInsetsCompat insets) {
            WindowInsets.Builder builder;
            super(insets);
            WindowInsets windowInsets = insets.toWindowInsets();
            if (windowInsets != null) {
                builder = new WindowInsets.Builder(windowInsets);
            } else {
                builder = new WindowInsets.Builder();
            }
            this.mPlatBuilder = builder;
        }
    }

    @RequiresApi(21)
    private static class Impl21 extends Impl20 {
        public Insets mStableInsets;

        public Impl21(@NonNull WindowInsetsCompat host, @NonNull WindowInsets insets) {
            super(host, insets);
            this.mStableInsets = null;
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        @NonNull
        public WindowInsetsCompat consumeStableInsets() {
            return WindowInsetsCompat.toWindowInsetsCompat(this.mPlatformInsets.consumeStableInsets());
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        @NonNull
        public WindowInsetsCompat consumeSystemWindowInsets() {
            return WindowInsetsCompat.toWindowInsetsCompat(this.mPlatformInsets.consumeSystemWindowInsets());
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        @NonNull
        public final Insets getStableInsets() {
            if (this.mStableInsets == null) {
                this.mStableInsets = Insets.of(this.mPlatformInsets.getStableInsetLeft(), this.mPlatformInsets.getStableInsetTop(), this.mPlatformInsets.getStableInsetRight(), this.mPlatformInsets.getStableInsetBottom());
            }
            return this.mStableInsets;
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        public boolean isConsumed() {
            return this.mPlatformInsets.isConsumed();
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        public void setStableInsets(@Nullable Insets stableInsets) {
            this.mStableInsets = stableInsets;
        }

        public Impl21(@NonNull WindowInsetsCompat host, @NonNull Impl21 other) {
            super(host, other);
            this.mStableInsets = null;
            this.mStableInsets = other.mStableInsets;
        }
    }

    @NonNull
    @RequiresApi(20)
    public static WindowInsetsCompat toWindowInsetsCompat(@NonNull WindowInsets insets, @Nullable View view) {
        if (insets == null) {
            throw new NullPointerException();
        }
        WindowInsetsCompat windowInsetsCompat = new WindowInsetsCompat(insets);
        if (view != null && view.isAttachedToWindow()) {
            windowInsetsCompat.setRootWindowInsets(ViewCompat.getRootWindowInsets(view));
            windowInsetsCompat.copyRootViewBounds(view.getRootView());
        }
        return windowInsetsCompat;
    }

    @NonNull
    public WindowInsetsCompat inset(@IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG) int left, @IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG) int top, @IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG) int right, @IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG) int bottom) {
        return this.mImpl.inset(left, top, right, bottom);
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class BuilderImpl {
        public final WindowInsetsCompat mInsets;
        public Insets[] mInsetsTypeMask;

        public BuilderImpl() {
            this.mInsets = new WindowInsetsCompat((WindowInsetsCompat) null);
        }

        public final void applyInsetTypes() {
            Insets[] insetsArr = this.mInsetsTypeMask;
            if (insetsArr != null) {
                Insets insets = insetsArr[Type.indexOf(1)];
                Insets insets2 = this.mInsetsTypeMask[Type.indexOf(2)];
                if (insets2 == null) {
                    insets2 = this.mInsets.getInsets(2);
                }
                if (insets == null) {
                    insets = this.mInsets.getInsets(1);
                }
                setSystemWindowInsets(Insets.max(insets, insets2));
                Insets insets3 = this.mInsetsTypeMask[Type.indexOf(16)];
                if (insets3 != null) {
                    setSystemGestureInsets(insets3);
                }
                Insets insets4 = this.mInsetsTypeMask[Type.indexOf(32)];
                if (insets4 != null) {
                    setMandatorySystemGestureInsets(insets4);
                }
                Insets insets5 = this.mInsetsTypeMask[Type.indexOf(64)];
                if (insets5 != null) {
                    setTappableElementInsets(insets5);
                }
            }
        }

        @NonNull
        public WindowInsetsCompat build() {
            applyInsetTypes();
            return this.mInsets;
        }

        public void setDisplayCutout(@Nullable DisplayCutoutCompat displayCutout) {
        }

        public void setInsets(int typeMask, @NonNull Insets insets) {
            if (this.mInsetsTypeMask == null) {
                this.mInsetsTypeMask = new Insets[9];
            }
            for (int i = 1; i <= 256; i <<= 1) {
                if ((typeMask & i) != 0) {
                    this.mInsetsTypeMask[Type.indexOf(i)] = insets;
                }
            }
        }

        public void setInsetsIgnoringVisibility(int typeMask, @NonNull Insets insets) {
            if (typeMask == 8) {
                throw new IllegalArgumentException("Ignoring visibility inset not available for IME");
            }
        }

        public void setMandatorySystemGestureInsets(@NonNull Insets insets) {
        }

        public void setStableInsets(@NonNull Insets insets) {
        }

        public void setSystemGestureInsets(@NonNull Insets insets) {
        }

        public void setSystemWindowInsets(@NonNull Insets insets) {
        }

        public void setTappableElementInsets(@NonNull Insets insets) {
        }

        public void setVisible(int typeMask, boolean visible) {
        }

        public BuilderImpl(@NonNull WindowInsetsCompat insets) {
            this.mInsets = insets;
        }
    }

    @RequiresApi(29)
    private static class Impl29 extends Impl28 {
        public Insets mMandatorySystemGestureInsets;
        public Insets mSystemGestureInsets;
        public Insets mTappableElementInsets;

        public Impl29(@NonNull WindowInsetsCompat host, @NonNull WindowInsets insets) {
            super(host, insets);
            this.mSystemGestureInsets = null;
            this.mMandatorySystemGestureInsets = null;
            this.mTappableElementInsets = null;
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        @NonNull
        public Insets getMandatorySystemGestureInsets() {
            if (this.mMandatorySystemGestureInsets == null) {
                this.mMandatorySystemGestureInsets = Insets.toCompatInsets(this.mPlatformInsets.getMandatorySystemGestureInsets());
            }
            return this.mMandatorySystemGestureInsets;
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        @NonNull
        public Insets getSystemGestureInsets() {
            if (this.mSystemGestureInsets == null) {
                this.mSystemGestureInsets = Insets.toCompatInsets(this.mPlatformInsets.getSystemGestureInsets());
            }
            return this.mSystemGestureInsets;
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        @NonNull
        public Insets getTappableElementInsets() {
            if (this.mTappableElementInsets == null) {
                this.mTappableElementInsets = Insets.toCompatInsets(this.mPlatformInsets.getTappableElementInsets());
            }
            return this.mTappableElementInsets;
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl20, androidx.core.view.WindowInsetsCompat.Impl
        @NonNull
        public WindowInsetsCompat inset(int left, int top, int right, int bottom) {
            return WindowInsetsCompat.toWindowInsetsCompat(this.mPlatformInsets.inset(left, top, right, bottom));
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl21, androidx.core.view.WindowInsetsCompat.Impl
        public void setStableInsets(@Nullable Insets stableInsets) {
        }

        public Impl29(@NonNull WindowInsetsCompat host, @NonNull Impl29 other) {
            super(host, other);
            this.mSystemGestureInsets = null;
            this.mMandatorySystemGestureInsets = null;
            this.mTappableElementInsets = null;
        }
    }

    @NonNull
    @Deprecated
    public WindowInsetsCompat replaceSystemWindowInsets(@NonNull Rect systemWindowInsets) {
        return new Builder(this).setSystemWindowInsets(Insets.of(systemWindowInsets)).build();
    }

    public static final class Builder {
        public final BuilderImpl mImpl;

        public Builder() {
            int i = Build.VERSION.SDK_INT;
            if (i >= 30) {
                this.mImpl = new BuilderImpl30();
            } else if (i >= 29) {
                this.mImpl = new BuilderImpl29();
            } else {
                this.mImpl = new BuilderImpl20();
            }
        }

        @NonNull
        public WindowInsetsCompat build() {
            return this.mImpl.build();
        }

        @NonNull
        public Builder setDisplayCutout(@Nullable DisplayCutoutCompat displayCutout) {
            this.mImpl.setDisplayCutout(displayCutout);
            return this;
        }

        @NonNull
        public Builder setInsets(int typeMask, @NonNull Insets insets) {
            this.mImpl.setInsets(typeMask, insets);
            return this;
        }

        @NonNull
        public Builder setInsetsIgnoringVisibility(int typeMask, @NonNull Insets insets) {
            this.mImpl.setInsetsIgnoringVisibility(typeMask, insets);
            return this;
        }

        @NonNull
        @Deprecated
        public Builder setMandatorySystemGestureInsets(@NonNull Insets insets) {
            this.mImpl.setMandatorySystemGestureInsets(insets);
            return this;
        }

        @NonNull
        @Deprecated
        public Builder setStableInsets(@NonNull Insets insets) {
            this.mImpl.setStableInsets(insets);
            return this;
        }

        @NonNull
        @Deprecated
        public Builder setSystemGestureInsets(@NonNull Insets insets) {
            this.mImpl.setSystemGestureInsets(insets);
            return this;
        }

        @NonNull
        @Deprecated
        public Builder setSystemWindowInsets(@NonNull Insets insets) {
            this.mImpl.setSystemWindowInsets(insets);
            return this;
        }

        @NonNull
        @Deprecated
        public Builder setTappableElementInsets(@NonNull Insets insets) {
            this.mImpl.setTappableElementInsets(insets);
            return this;
        }

        @NonNull
        public Builder setVisible(int typeMask, boolean visible) {
            this.mImpl.setVisible(typeMask, visible);
            return this;
        }

        public Builder(@NonNull WindowInsetsCompat insets) {
            int i = Build.VERSION.SDK_INT;
            if (i >= 30) {
                this.mImpl = new BuilderImpl30(insets);
            } else if (i >= 29) {
                this.mImpl = new BuilderImpl29(insets);
            } else {
                this.mImpl = new BuilderImpl20(insets);
            }
        }
    }

    public WindowInsetsCompat(@Nullable final WindowInsetsCompat src) {
        if (src != null) {
            Impl impl = src.mImpl;
            if (Build.VERSION.SDK_INT >= 30 && (impl instanceof Impl30)) {
                this.mImpl = new Impl30(this, (Impl30) impl);
            } else if (Build.VERSION.SDK_INT >= 29 && (impl instanceof Impl29)) {
                this.mImpl = new Impl29(this, (Impl29) impl);
            } else if (Build.VERSION.SDK_INT >= 28 && (impl instanceof Impl28)) {
                this.mImpl = new Impl28(this, (Impl28) impl);
            } else {
                int i = Build.VERSION.SDK_INT;
                if (impl instanceof Impl21) {
                    this.mImpl = new Impl21(this, (Impl21) impl);
                } else if (impl instanceof Impl20) {
                    this.mImpl = new Impl20(this, (Impl20) impl);
                } else {
                    this.mImpl = new Impl(this);
                }
            }
            impl.copyWindowDataInto(this);
            return;
        }
        this.mImpl = new Impl(this);
    }
}
