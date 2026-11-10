package androidx.core.app;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.util.Pair;

/* JADX INFO: loaded from: classes.dex */
public class ActivityOptionsCompat {
    public static final String EXTRA_USAGE_TIME_REPORT = "android.activity.usage_time";
    public static final String EXTRA_USAGE_TIME_REPORT_PACKAGES = "android.usage_time_packages";

    @RequiresApi(16)
    private static class ActivityOptionsCompatImpl extends ActivityOptionsCompat {
        public final ActivityOptions mActivityOptions;

        public ActivityOptionsCompatImpl(ActivityOptions activityOptions) {
            this.mActivityOptions = activityOptions;
        }

        @Override // androidx.core.app.ActivityOptionsCompat
        public Rect getLaunchBounds() {
            int i = Build.VERSION.SDK_INT;
            return this.mActivityOptions.getLaunchBounds();
        }

        @Override // androidx.core.app.ActivityOptionsCompat
        public void requestUsageTimeReport(@NonNull PendingIntent receiver) {
            int i = Build.VERSION.SDK_INT;
            this.mActivityOptions.requestUsageTimeReport(receiver);
        }

        @Override // androidx.core.app.ActivityOptionsCompat
        @NonNull
        public ActivityOptionsCompat setLaunchBounds(@Nullable Rect screenSpacePixelRect) {
            int i = Build.VERSION.SDK_INT;
            return new ActivityOptionsCompatImpl(this.mActivityOptions.setLaunchBounds(screenSpacePixelRect));
        }

        @Override // androidx.core.app.ActivityOptionsCompat
        public Bundle toBundle() {
            return this.mActivityOptions.toBundle();
        }

        @Override // androidx.core.app.ActivityOptionsCompat
        public void update(@NonNull ActivityOptionsCompat otherOptions) {
            if (otherOptions instanceof ActivityOptionsCompatImpl) {
                this.mActivityOptions.update(((ActivityOptionsCompatImpl) otherOptions).mActivityOptions);
            }
        }
    }

    @NonNull
    public static ActivityOptionsCompat makeBasic() {
        int i = Build.VERSION.SDK_INT;
        return new ActivityOptionsCompatImpl(ActivityOptions.makeBasic());
    }

    @NonNull
    public static ActivityOptionsCompat makeClipRevealAnimation(@NonNull View source, int startX, int startY, int width, int height) {
        int i = Build.VERSION.SDK_INT;
        return new ActivityOptionsCompatImpl(ActivityOptions.makeClipRevealAnimation(source, startX, startY, width, height));
    }

    @NonNull
    public static ActivityOptionsCompat makeCustomAnimation(@NonNull Context context, int enterResId, int exitResId) {
        int i = Build.VERSION.SDK_INT;
        return new ActivityOptionsCompatImpl(ActivityOptions.makeCustomAnimation(context, enterResId, exitResId));
    }

    @NonNull
    public static ActivityOptionsCompat makeScaleUpAnimation(@NonNull View source, int startX, int startY, int startWidth, int startHeight) {
        int i = Build.VERSION.SDK_INT;
        return new ActivityOptionsCompatImpl(ActivityOptions.makeScaleUpAnimation(source, startX, startY, startWidth, startHeight));
    }

    @NonNull
    public static ActivityOptionsCompat makeSceneTransitionAnimation(@NonNull Activity activity, @NonNull View sharedElement, @NonNull String sharedElementName) {
        int i = Build.VERSION.SDK_INT;
        return new ActivityOptionsCompatImpl(ActivityOptions.makeSceneTransitionAnimation(activity, sharedElement, sharedElementName));
    }

    @NonNull
    public static ActivityOptionsCompat makeTaskLaunchBehind() {
        int i = Build.VERSION.SDK_INT;
        return new ActivityOptionsCompatImpl(ActivityOptions.makeTaskLaunchBehind());
    }

    @NonNull
    public static ActivityOptionsCompat makeThumbnailScaleUpAnimation(@NonNull View source, @NonNull Bitmap thumbnail, int startX, int startY) {
        int i = Build.VERSION.SDK_INT;
        return new ActivityOptionsCompatImpl(ActivityOptions.makeThumbnailScaleUpAnimation(source, thumbnail, startX, startY));
    }

    @Nullable
    public Rect getLaunchBounds() {
        return null;
    }

    public void requestUsageTimeReport(@NonNull PendingIntent receiver) {
    }

    @NonNull
    public ActivityOptionsCompat setLaunchBounds(@Nullable Rect screenSpacePixelRect) {
        return this;
    }

    @Nullable
    public Bundle toBundle() {
        return null;
    }

    public void update(@NonNull ActivityOptionsCompat otherOptions) {
    }

    @NonNull
    public static ActivityOptionsCompat makeSceneTransitionAnimation(@NonNull Activity activity, Pair<View, String>... sharedElements) {
        int i = Build.VERSION.SDK_INT;
        android.util.Pair[] pairArr = null;
        if (sharedElements != null) {
            pairArr = new android.util.Pair[sharedElements.length];
            for (int i2 = 0; i2 < sharedElements.length; i2++) {
                pairArr[i2] = android.util.Pair.create(sharedElements[i2].first, sharedElements[i2].second);
            }
        }
        return new ActivityOptionsCompatImpl(ActivityOptions.makeSceneTransitionAnimation(activity, pairArr));
    }
}
