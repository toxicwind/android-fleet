package androidx.core.view;

import android.R;
import android.os.Build;
import android.os.CancellationSignal;
import android.view.View;
import android.view.Window;
import android.view.WindowInsetsAnimationControlListener;
import android.view.WindowInsetsAnimationController;
import android.view.WindowInsetsController;
import android.view.animation.Interpolator;
import android.view.inputmethod.InputMethodManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.collection.SimpleArrayMap;

/* JADX INFO: loaded from: classes.dex */
public final class WindowInsetsControllerCompat {
    public static final int BEHAVIOR_SHOW_BARS_BY_SWIPE = 1;
    public static final int BEHAVIOR_SHOW_BARS_BY_TOUCH = 0;
    public static final int BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE = 2;
    public final Impl mImpl;

    private static class Impl {
        public void addOnControllableInsetsChangedListener(OnControllableInsetsChangedListener listener) {
        }

        public void controlWindowInsetsAnimation(int types, long durationMillis, Interpolator interpolator, CancellationSignal cancellationSignal, WindowInsetsAnimationControlListenerCompat listener) {
        }

        public int getSystemBarsBehavior() {
            return 0;
        }

        public void hide(int types) {
        }

        public boolean isAppearanceLightNavigationBars() {
            return false;
        }

        public boolean isAppearanceLightStatusBars() {
            return false;
        }

        public void removeOnControllableInsetsChangedListener(@NonNull OnControllableInsetsChangedListener listener) {
        }

        public void setAppearanceLightNavigationBars(boolean isLight) {
        }

        public void setAppearanceLightStatusBars(boolean isLight) {
        }

        public void setSystemBarsBehavior(int behavior) {
        }

        public void show(int types) {
        }
    }

    @RequiresApi(20)
    private static class Impl20 extends Impl {

        @Nullable
        public final View mView;

        @NonNull
        public final Window mWindow;

        public Impl20(@NonNull Window window, @Nullable View view) {
            this.mWindow = window;
            this.mView = view;
        }

        private void hideForType(int type) {
            if (type == 1) {
                setSystemUiFlag(4);
            } else if (type == 2) {
                setSystemUiFlag(2);
            } else {
                if (type != 8) {
                    return;
                }
                ((InputMethodManager) this.mWindow.getContext().getSystemService("input_method")).hideSoftInputFromWindow(this.mWindow.getDecorView().getWindowToken(), 0);
            }
        }

        private void showForType(int type) {
            if (type == 1) {
                unsetSystemUiFlag(4);
                unsetWindowFlag(1024);
                return;
            }
            if (type == 2) {
                unsetSystemUiFlag(2);
                return;
            }
            if (type != 8) {
                return;
            }
            final View currentFocus = this.mView;
            if (currentFocus == null || !(currentFocus.isInEditMode() || currentFocus.onCheckIsTextEditor())) {
                currentFocus = this.mWindow.getCurrentFocus();
            } else {
                currentFocus.requestFocus();
            }
            if (currentFocus == null) {
                currentFocus = this.mWindow.findViewById(R.id.content);
            }
            if (currentFocus == null || !currentFocus.hasWindowFocus()) {
                return;
            }
            currentFocus.post(new Runnable() { // from class: androidx.core.view.WindowInsetsControllerCompat.Impl20.1
                @Override // java.lang.Runnable
                public void run() {
                    ((InputMethodManager) currentFocus.getContext().getSystemService("input_method")).showSoftInput(currentFocus, 0);
                }
            });
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public void addOnControllableInsetsChangedListener(OnControllableInsetsChangedListener listener) {
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public void controlWindowInsetsAnimation(int types, long durationMillis, Interpolator interpolator, CancellationSignal cancellationSignal, WindowInsetsAnimationControlListenerCompat listener) {
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public int getSystemBarsBehavior() {
            return 0;
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public void hide(int typeMask) {
            for (int i = 1; i <= 256; i <<= 1) {
                if ((typeMask & i) != 0) {
                    hideForType(i);
                }
            }
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public void removeOnControllableInsetsChangedListener(@NonNull OnControllableInsetsChangedListener listener) {
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public void setSystemBarsBehavior(int behavior) {
            if (behavior == 0) {
                unsetSystemUiFlag(6144);
                return;
            }
            if (behavior == 1) {
                unsetSystemUiFlag(4096);
                setSystemUiFlag(2048);
            } else {
                if (behavior != 2) {
                    return;
                }
                unsetSystemUiFlag(2048);
                setSystemUiFlag(4096);
            }
        }

        public void setSystemUiFlag(int systemUiFlag) {
            View decorView = this.mWindow.getDecorView();
            decorView.setSystemUiVisibility(systemUiFlag | decorView.getSystemUiVisibility());
        }

        public void setWindowFlag(int windowFlag) {
            this.mWindow.addFlags(windowFlag);
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public void show(int typeMask) {
            for (int i = 1; i <= 256; i <<= 1) {
                if ((typeMask & i) != 0) {
                    showForType(i);
                }
            }
        }

        public void unsetSystemUiFlag(int systemUiFlag) {
            View decorView = this.mWindow.getDecorView();
            decorView.setSystemUiVisibility((~systemUiFlag) & decorView.getSystemUiVisibility());
        }

        public void unsetWindowFlag(int windowFlag) {
            this.mWindow.clearFlags(windowFlag);
        }
    }

    @RequiresApi(23)
    private static class Impl23 extends Impl20 {
        public Impl23(@NonNull Window window, @Nullable View view) {
            super(window, view);
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public boolean isAppearanceLightStatusBars() {
            return (this.mWindow.getDecorView().getSystemUiVisibility() & 8192) != 0;
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public void setAppearanceLightStatusBars(boolean isLight) {
            if (!isLight) {
                unsetSystemUiFlag(8192);
                return;
            }
            unsetWindowFlag(67108864);
            setWindowFlag(Integer.MIN_VALUE);
            setSystemUiFlag(8192);
        }
    }

    @RequiresApi(26)
    private static class Impl26 extends Impl23 {
        public Impl26(@NonNull Window window, @Nullable View view) {
            super(window, view);
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public boolean isAppearanceLightNavigationBars() {
            return (this.mWindow.getDecorView().getSystemUiVisibility() & 16) != 0;
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public void setAppearanceLightNavigationBars(boolean isLight) {
            if (!isLight) {
                unsetSystemUiFlag(16);
                return;
            }
            unsetWindowFlag(134217728);
            setWindowFlag(Integer.MIN_VALUE);
            setSystemUiFlag(16);
        }
    }

    @RequiresApi(30)
    private static class Impl30 extends Impl {
        public final WindowInsetsControllerCompat mCompatController;
        public final WindowInsetsController mInsetsController;
        public final SimpleArrayMap<OnControllableInsetsChangedListener, WindowInsetsController.OnControllableInsetsChangedListener> mListeners;

        public Impl30(@NonNull Window window, @NonNull WindowInsetsControllerCompat compatController) {
            this(window.getInsetsController(), compatController);
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public void addOnControllableInsetsChangedListener(@NonNull final OnControllableInsetsChangedListener listener) {
            if (this.mListeners.containsKey(listener)) {
                return;
            }
            WindowInsetsController.OnControllableInsetsChangedListener onControllableInsetsChangedListener = new WindowInsetsController.OnControllableInsetsChangedListener() { // from class: androidx.core.view.WindowInsetsControllerCompat.Impl30.2
                @Override // android.view.WindowInsetsController.OnControllableInsetsChangedListener
                public void onControllableInsetsChanged(@NonNull WindowInsetsController controller, int typeMask) {
                    Impl30 impl30 = Impl30.this;
                    if (impl30.mInsetsController == controller) {
                        listener.onControllableInsetsChanged(impl30.mCompatController, typeMask);
                    }
                }
            };
            this.mListeners.put(listener, onControllableInsetsChangedListener);
            this.mInsetsController.addOnControllableInsetsChangedListener(onControllableInsetsChangedListener);
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public void controlWindowInsetsAnimation(int types, long durationMillis, @Nullable Interpolator interpolator, @Nullable CancellationSignal cancellationSignal, @NonNull final WindowInsetsAnimationControlListenerCompat listener) {
            this.mInsetsController.controlWindowInsetsAnimation(types, durationMillis, interpolator, cancellationSignal, new WindowInsetsAnimationControlListener() { // from class: androidx.core.view.WindowInsetsControllerCompat.Impl30.1
                public WindowInsetsAnimationControllerCompat mCompatAnimController = null;

                @Override // android.view.WindowInsetsAnimationControlListener
                public void onCancelled(@Nullable WindowInsetsAnimationController controller) {
                    listener.onCancelled(controller == null ? null : this.mCompatAnimController);
                }

                @Override // android.view.WindowInsetsAnimationControlListener
                public void onFinished(@NonNull WindowInsetsAnimationController controller) {
                    listener.onFinished(this.mCompatAnimController);
                }

                @Override // android.view.WindowInsetsAnimationControlListener
                public void onReady(@NonNull WindowInsetsAnimationController controller, int types2) {
                    this.mCompatAnimController = new WindowInsetsAnimationControllerCompat(controller);
                    listener.onReady(this.mCompatAnimController, types2);
                }
            });
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public int getSystemBarsBehavior() {
            return this.mInsetsController.getSystemBarsBehavior();
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public void hide(int types) {
            this.mInsetsController.hide(types);
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public boolean isAppearanceLightNavigationBars() {
            return (this.mInsetsController.getSystemBarsAppearance() & 16) != 0;
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public boolean isAppearanceLightStatusBars() {
            return (this.mInsetsController.getSystemBarsAppearance() & 8) != 0;
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public void removeOnControllableInsetsChangedListener(@NonNull OnControllableInsetsChangedListener listener) {
            WindowInsetsController.OnControllableInsetsChangedListener onControllableInsetsChangedListenerRemove = this.mListeners.remove(listener);
            if (onControllableInsetsChangedListenerRemove != null) {
                this.mInsetsController.removeOnControllableInsetsChangedListener(onControllableInsetsChangedListenerRemove);
            }
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public void setAppearanceLightNavigationBars(boolean isLight) {
            if (isLight) {
                this.mInsetsController.setSystemBarsAppearance(16, 16);
            } else {
                this.mInsetsController.setSystemBarsAppearance(0, 16);
            }
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public void setAppearanceLightStatusBars(boolean isLight) {
            if (isLight) {
                this.mInsetsController.setSystemBarsAppearance(8, 8);
            } else {
                this.mInsetsController.setSystemBarsAppearance(0, 8);
            }
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public void setSystemBarsBehavior(int behavior) {
            this.mInsetsController.setSystemBarsBehavior(behavior);
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public void show(int types) {
            this.mInsetsController.show(types);
        }

        public Impl30(@NonNull WindowInsetsController insetsController, @NonNull WindowInsetsControllerCompat compatController) {
            this.mListeners = new SimpleArrayMap<>();
            this.mInsetsController = insetsController;
            this.mCompatController = compatController;
        }
    }

    public interface OnControllableInsetsChangedListener {
        void onControllableInsetsChanged(@NonNull WindowInsetsControllerCompat controller, int typeMask);
    }

    @RequiresApi(30)
    public WindowInsetsControllerCompat(@NonNull WindowInsetsController insetsController) {
        if (Build.VERSION.SDK_INT >= 30) {
            this.mImpl = new Impl30(insetsController, this);
        } else {
            this.mImpl = new Impl();
        }
    }

    @NonNull
    @RequiresApi(30)
    public static WindowInsetsControllerCompat toWindowInsetsControllerCompat(@NonNull WindowInsetsController insetsController) {
        return new WindowInsetsControllerCompat(insetsController);
    }

    public void addOnControllableInsetsChangedListener(@NonNull OnControllableInsetsChangedListener listener) {
        this.mImpl.addOnControllableInsetsChangedListener(listener);
    }

    public void controlWindowInsetsAnimation(int types, long durationMillis, @Nullable Interpolator interpolator, @Nullable CancellationSignal cancellationSignal, @NonNull WindowInsetsAnimationControlListenerCompat listener) {
        this.mImpl.controlWindowInsetsAnimation(types, durationMillis, interpolator, cancellationSignal, listener);
    }

    public int getSystemBarsBehavior() {
        return this.mImpl.getSystemBarsBehavior();
    }

    public void hide(int types) {
        this.mImpl.hide(types);
    }

    public boolean isAppearanceLightNavigationBars() {
        return this.mImpl.isAppearanceLightNavigationBars();
    }

    public boolean isAppearanceLightStatusBars() {
        return this.mImpl.isAppearanceLightStatusBars();
    }

    public void removeOnControllableInsetsChangedListener(@NonNull OnControllableInsetsChangedListener listener) {
        this.mImpl.removeOnControllableInsetsChangedListener(listener);
    }

    public void setAppearanceLightNavigationBars(boolean isLight) {
        this.mImpl.setAppearanceLightNavigationBars(isLight);
    }

    public void setAppearanceLightStatusBars(boolean isLight) {
        this.mImpl.setAppearanceLightStatusBars(isLight);
    }

    public void setSystemBarsBehavior(int behavior) {
        this.mImpl.setSystemBarsBehavior(behavior);
    }

    public void show(int types) {
        this.mImpl.show(types);
    }

    public WindowInsetsControllerCompat(@NonNull Window window, @NonNull View view) {
        if (Build.VERSION.SDK_INT >= 30) {
            this.mImpl = new Impl30(window.getInsetsController(), this);
        } else {
            this.mImpl = new Impl26(window, view);
        }
    }
}
