package androidx.core.view;

import android.view.View;
import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes.dex */
public interface NestedScrollingParent {
    int getNestedScrollAxes();

    boolean onNestedFling(@NonNull View target, float velocityX, float velocityY, boolean consumed);

    boolean onNestedPreFling(@NonNull View target, float velocityX, float velocityY);

    void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed);

    void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed);

    void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes);

    boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes);

    void onStopNestedScroll(@NonNull View target);
}
