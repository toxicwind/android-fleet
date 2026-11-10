package androidx.core.view;

import android.graphics.Rect;
import android.os.Build;
import android.view.DisplayCutout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.graphics.Insets;
import androidx.core.os.BuildCompat;
import androidx.core.util.ObjectsCompat;
import b.a.a.a.a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class DisplayCutoutCompat {
    public final Object mDisplayCutout;

    public DisplayCutoutCompat(Rect safeInsets, List<Rect> boundingRects) {
        this.mDisplayCutout = Build.VERSION.SDK_INT >= 28 ? new DisplayCutout(safeInsets, boundingRects) : null;
    }

    public static DisplayCutout constructDisplayCutout(@NonNull Insets safeInsets, @Nullable Rect boundLeft, @Nullable Rect boundTop, @Nullable Rect boundRight, @Nullable Rect boundBottom, @NonNull Insets waterfallInsets) {
        if (BuildCompat.isAtLeastR()) {
            return new DisplayCutout(safeInsets.toPlatformInsets(), boundLeft, boundTop, boundRight, boundBottom, waterfallInsets.toPlatformInsets());
        }
        int i = Build.VERSION.SDK_INT;
        if (i >= 29) {
            return new DisplayCutout(safeInsets.toPlatformInsets(), boundLeft, boundTop, boundRight, boundBottom);
        }
        if (i < 28) {
            return null;
        }
        Rect rect = new Rect(safeInsets.left, safeInsets.top, safeInsets.right, safeInsets.bottom);
        ArrayList arrayList = new ArrayList();
        if (boundLeft != null) {
            arrayList.add(boundLeft);
        }
        if (boundTop != null) {
            arrayList.add(boundTop);
        }
        if (boundRight != null) {
            arrayList.add(boundRight);
        }
        if (boundBottom != null) {
            arrayList.add(boundBottom);
        }
        return new DisplayCutout(rect, arrayList);
    }

    public static DisplayCutoutCompat wrap(Object displayCutout) {
        if (displayCutout == null) {
            return null;
        }
        return new DisplayCutoutCompat(displayCutout);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || DisplayCutoutCompat.class != o.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(this.mDisplayCutout, ((DisplayCutoutCompat) o).mDisplayCutout);
    }

    @NonNull
    public List<Rect> getBoundingRects() {
        return Build.VERSION.SDK_INT >= 28 ? ((DisplayCutout) this.mDisplayCutout).getBoundingRects() : Collections.emptyList();
    }

    public int getSafeInsetBottom() {
        if (Build.VERSION.SDK_INT >= 28) {
            return ((DisplayCutout) this.mDisplayCutout).getSafeInsetBottom();
        }
        return 0;
    }

    public int getSafeInsetLeft() {
        if (Build.VERSION.SDK_INT >= 28) {
            return ((DisplayCutout) this.mDisplayCutout).getSafeInsetLeft();
        }
        return 0;
    }

    public int getSafeInsetRight() {
        if (Build.VERSION.SDK_INT >= 28) {
            return ((DisplayCutout) this.mDisplayCutout).getSafeInsetRight();
        }
        return 0;
    }

    public int getSafeInsetTop() {
        if (Build.VERSION.SDK_INT >= 28) {
            return ((DisplayCutout) this.mDisplayCutout).getSafeInsetTop();
        }
        return 0;
    }

    @NonNull
    public Insets getWaterfallInsets() {
        return BuildCompat.isAtLeastR() ? Insets.toCompatInsets(((DisplayCutout) this.mDisplayCutout).getWaterfallInsets()) : Insets.NONE;
    }

    public int hashCode() {
        Object obj = this.mDisplayCutout;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public String toString() {
        return a.a(a.a("DisplayCutoutCompat{"), this.mDisplayCutout, "}");
    }

    @RequiresApi(api = 28)
    public DisplayCutout unwrap() {
        return (DisplayCutout) this.mDisplayCutout;
    }

    public DisplayCutoutCompat(@NonNull Insets safeInsets, @Nullable Rect boundLeft, @Nullable Rect boundTop, @Nullable Rect boundRight, @Nullable Rect boundBottom, @NonNull Insets waterfallInsets) {
        this.mDisplayCutout = constructDisplayCutout(safeInsets, boundLeft, boundTop, boundRight, boundBottom, waterfallInsets);
    }

    public DisplayCutoutCompat(Object displayCutout) {
        this.mDisplayCutout = displayCutout;
    }
}
