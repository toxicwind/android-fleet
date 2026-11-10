package androidx.core.view;

import android.view.View;
import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes.dex */
public interface NestedScrollingParent2 extends NestedScrollingParent {
    void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed, int type);

    void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type);

    void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes, int type);

    boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes, int type);

    void onStopNestedScroll(@NonNull View target, int type);
}
