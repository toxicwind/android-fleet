package androidx.core.view;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes.dex */
public class NestedScrollingParentHelper {
    public int mNestedScrollAxesNonTouch;
    public int mNestedScrollAxesTouch;

    public NestedScrollingParentHelper(@NonNull ViewGroup viewGroup) {
    }

    public int getNestedScrollAxes() {
        return this.mNestedScrollAxesTouch | this.mNestedScrollAxesNonTouch;
    }

    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes) {
        onNestedScrollAccepted(child, target, axes, 0);
    }

    public void onStopNestedScroll(@NonNull View target) {
        onStopNestedScroll(target, 0);
    }

    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes, int type) {
        if (type == 1) {
            this.mNestedScrollAxesNonTouch = axes;
        } else {
            this.mNestedScrollAxesTouch = axes;
        }
    }

    public void onStopNestedScroll(@NonNull View target, int type) {
        if (type == 1) {
            this.mNestedScrollAxesNonTouch = 0;
        } else {
            this.mNestedScrollAxesTouch = 0;
        }
    }
}
