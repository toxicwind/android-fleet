package androidx.core.view;

import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public interface NestedScrollingChild {
    boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed);

    boolean dispatchNestedPreFling(float velocityX, float velocityY);

    boolean dispatchNestedPreScroll(int dx, int dy, @Nullable int[] consumed, @Nullable int[] offsetInWindow);

    boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @Nullable int[] offsetInWindow);

    boolean hasNestedScrollingParent();

    boolean isNestedScrollingEnabled();

    void setNestedScrollingEnabled(boolean enabled);

    boolean startNestedScroll(int axes);

    void stopNestedScroll();
}
