package androidx.core.view;

import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public interface NestedScrollingChild2 extends NestedScrollingChild {
    boolean dispatchNestedPreScroll(int dx, int dy, @Nullable int[] consumed, @Nullable int[] offsetInWindow, int type);

    boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @Nullable int[] offsetInWindow, int type);

    boolean hasNestedScrollingParent(int type);

    boolean startNestedScroll(int axes, int type);

    void stopNestedScroll(int type);
}
