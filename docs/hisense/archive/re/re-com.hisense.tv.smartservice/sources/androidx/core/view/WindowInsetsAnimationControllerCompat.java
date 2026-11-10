package androidx.core.view;

import android.os.Build;
import android.view.WindowInsetsAnimationController;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.graphics.Insets;
import b.a.a.a.a;
import com.google.common.collect.LinkedHashMultimap;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

/* JADX INFO: loaded from: classes.dex */
public final class WindowInsetsAnimationControllerCompat {
    public final Impl mImpl;

    private static class Impl {
        public void finish(boolean shown) {
        }

        public float getCurrentAlpha() {
            return 0.0f;
        }

        @FloatRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, to = LinkedHashMultimap.VALUE_SET_LOAD_FACTOR)
        public float getCurrentFraction() {
            return 0.0f;
        }

        @NonNull
        public Insets getCurrentInsets() {
            return Insets.NONE;
        }

        @NonNull
        public Insets getHiddenStateInsets() {
            return Insets.NONE;
        }

        @NonNull
        public Insets getShownStateInsets() {
            return Insets.NONE;
        }

        public int getTypes() {
            return 0;
        }

        public boolean isCancelled() {
            return true;
        }

        public boolean isFinished() {
            return false;
        }

        public boolean isReady() {
            return false;
        }

        public void setInsetsAndAlpha(@Nullable Insets insets, @FloatRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, to = LinkedHashMultimap.VALUE_SET_LOAD_FACTOR) float alpha, @FloatRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, to = LinkedHashMultimap.VALUE_SET_LOAD_FACTOR) float fraction) {
        }
    }

    @RequiresApi(30)
    private static class Impl30 extends Impl {
        public final WindowInsetsAnimationController mController;

        public Impl30(@NonNull WindowInsetsAnimationController controller) {
            this.mController = controller;
        }

        @Override // androidx.core.view.WindowInsetsAnimationControllerCompat.Impl
        public void finish(boolean shown) {
            this.mController.finish(shown);
        }

        @Override // androidx.core.view.WindowInsetsAnimationControllerCompat.Impl
        public float getCurrentAlpha() {
            return this.mController.getCurrentAlpha();
        }

        @Override // androidx.core.view.WindowInsetsAnimationControllerCompat.Impl
        public float getCurrentFraction() {
            return this.mController.getCurrentFraction();
        }

        @Override // androidx.core.view.WindowInsetsAnimationControllerCompat.Impl
        @NonNull
        public Insets getCurrentInsets() {
            return Insets.toCompatInsets(this.mController.getCurrentInsets());
        }

        @Override // androidx.core.view.WindowInsetsAnimationControllerCompat.Impl
        @NonNull
        public Insets getHiddenStateInsets() {
            return Insets.toCompatInsets(this.mController.getHiddenStateInsets());
        }

        @Override // androidx.core.view.WindowInsetsAnimationControllerCompat.Impl
        @NonNull
        public Insets getShownStateInsets() {
            return Insets.toCompatInsets(this.mController.getShownStateInsets());
        }

        @Override // androidx.core.view.WindowInsetsAnimationControllerCompat.Impl
        public int getTypes() {
            return this.mController.getTypes();
        }

        @Override // androidx.core.view.WindowInsetsAnimationControllerCompat.Impl
        public boolean isCancelled() {
            return this.mController.isCancelled();
        }

        @Override // androidx.core.view.WindowInsetsAnimationControllerCompat.Impl
        public boolean isFinished() {
            return this.mController.isFinished();
        }

        @Override // androidx.core.view.WindowInsetsAnimationControllerCompat.Impl
        public boolean isReady() {
            return this.mController.isReady();
        }

        @Override // androidx.core.view.WindowInsetsAnimationControllerCompat.Impl
        public void setInsetsAndAlpha(@Nullable Insets insets, float alpha, float fraction) {
            this.mController.setInsetsAndAlpha(insets == null ? null : insets.toPlatformInsets(), alpha, fraction);
        }
    }

    public WindowInsetsAnimationControllerCompat() {
        if (Build.VERSION.SDK_INT < 30) {
            this.mImpl = new Impl();
            return;
        }
        StringBuilder sbA = a.a("On API 30+, the constructor taking a ");
        sbA.append(WindowInsetsAnimationController.class.getSimpleName());
        sbA.append(" as parameter");
        throw new UnsupportedOperationException(sbA.toString());
    }

    public void finish(boolean shown) {
        this.mImpl.finish(shown);
    }

    public float getCurrentAlpha() {
        return this.mImpl.getCurrentAlpha();
    }

    @FloatRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, to = LinkedHashMultimap.VALUE_SET_LOAD_FACTOR)
    public float getCurrentFraction() {
        return this.mImpl.getCurrentFraction();
    }

    @NonNull
    public Insets getCurrentInsets() {
        return this.mImpl.getCurrentInsets();
    }

    @NonNull
    public Insets getHiddenStateInsets() {
        return this.mImpl.getHiddenStateInsets();
    }

    @NonNull
    public Insets getShownStateInsets() {
        return this.mImpl.getShownStateInsets();
    }

    public int getTypes() {
        return this.mImpl.getTypes();
    }

    public boolean isCancelled() {
        return this.mImpl.isCancelled();
    }

    public boolean isFinished() {
        return this.mImpl.isFinished();
    }

    public boolean isReady() {
        return (isFinished() || isCancelled()) ? false : true;
    }

    public void setInsetsAndAlpha(@Nullable Insets insets, @FloatRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, to = LinkedHashMultimap.VALUE_SET_LOAD_FACTOR) float alpha, @FloatRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, to = LinkedHashMultimap.VALUE_SET_LOAD_FACTOR) float fraction) {
        this.mImpl.setInsetsAndAlpha(insets, alpha, fraction);
    }

    @RequiresApi(30)
    public WindowInsetsAnimationControllerCompat(@NonNull WindowInsetsAnimationController controller) {
        this.mImpl = new Impl30(controller);
    }
}
