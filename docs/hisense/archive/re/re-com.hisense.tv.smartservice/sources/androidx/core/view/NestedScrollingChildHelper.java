package androidx.core.view;

import android.view.View;
import android.view.ViewParent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public class NestedScrollingChildHelper {
    public boolean mIsNestedScrollingEnabled;
    public ViewParent mNestedScrollingParentNonTouch;
    public ViewParent mNestedScrollingParentTouch;
    public int[] mTempNestedScrollConsumed;
    public final View mView;

    public NestedScrollingChildHelper(@NonNull View view) {
        this.mView = view;
    }

    private boolean dispatchNestedScrollInternal(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @Nullable int[] offsetInWindow, int type, @Nullable int[] consumed) {
        ViewParent nestedScrollingParentForType;
        int i;
        int i2;
        int[] iArr;
        if (!isNestedScrollingEnabled() || (nestedScrollingParentForType = getNestedScrollingParentForType(type)) == null) {
            return false;
        }
        if (dxConsumed == 0 && dyConsumed == 0 && dxUnconsumed == 0 && dyUnconsumed == 0) {
            if (offsetInWindow != null) {
                offsetInWindow[0] = 0;
                offsetInWindow[1] = 0;
            }
            return false;
        }
        if (offsetInWindow != null) {
            this.mView.getLocationInWindow(offsetInWindow);
            i = offsetInWindow[0];
            i2 = offsetInWindow[1];
        } else {
            i = 0;
            i2 = 0;
        }
        if (consumed == null) {
            int[] tempNestedScrollConsumed = getTempNestedScrollConsumed();
            tempNestedScrollConsumed[0] = 0;
            tempNestedScrollConsumed[1] = 0;
            iArr = tempNestedScrollConsumed;
        } else {
            iArr = consumed;
        }
        ViewParentCompat.onNestedScroll(nestedScrollingParentForType, this.mView, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type, iArr);
        if (offsetInWindow != null) {
            this.mView.getLocationInWindow(offsetInWindow);
            offsetInWindow[0] = offsetInWindow[0] - i;
            offsetInWindow[1] = offsetInWindow[1] - i2;
        }
        return true;
    }

    private ViewParent getNestedScrollingParentForType(int type) {
        if (type == 0) {
            return this.mNestedScrollingParentTouch;
        }
        if (type != 1) {
            return null;
        }
        return this.mNestedScrollingParentNonTouch;
    }

    private int[] getTempNestedScrollConsumed() {
        if (this.mTempNestedScrollConsumed == null) {
            this.mTempNestedScrollConsumed = new int[2];
        }
        return this.mTempNestedScrollConsumed;
    }

    private void setNestedScrollingParentForType(int type, ViewParent p) {
        if (type == 0) {
            this.mNestedScrollingParentTouch = p;
        } else {
            if (type != 1) {
                return;
            }
            this.mNestedScrollingParentNonTouch = p;
        }
    }

    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        ViewParent nestedScrollingParentForType;
        if (!isNestedScrollingEnabled() || (nestedScrollingParentForType = getNestedScrollingParentForType(0)) == null) {
            return false;
        }
        return ViewParentCompat.onNestedFling(nestedScrollingParentForType, this.mView, velocityX, velocityY, consumed);
    }

    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        ViewParent nestedScrollingParentForType;
        if (!isNestedScrollingEnabled() || (nestedScrollingParentForType = getNestedScrollingParentForType(0)) == null) {
            return false;
        }
        return ViewParentCompat.onNestedPreFling(nestedScrollingParentForType, this.mView, velocityX, velocityY);
    }

    public boolean dispatchNestedPreScroll(int dx, int dy, @Nullable int[] consumed, @Nullable int[] offsetInWindow) {
        return dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow, 0);
    }

    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @Nullable int[] offsetInWindow) {
        return dispatchNestedScrollInternal(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow, 0, null);
    }

    public boolean hasNestedScrollingParent() {
        return hasNestedScrollingParent(0);
    }

    public boolean isNestedScrollingEnabled() {
        return this.mIsNestedScrollingEnabled;
    }

    public void onDetachedFromWindow() {
        ViewCompat.stopNestedScroll(this.mView);
    }

    public void onStopNestedScroll(@NonNull View child) {
        ViewCompat.stopNestedScroll(this.mView);
    }

    public void setNestedScrollingEnabled(boolean enabled) {
        if (this.mIsNestedScrollingEnabled) {
            ViewCompat.stopNestedScroll(this.mView);
        }
        this.mIsNestedScrollingEnabled = enabled;
    }

    public boolean startNestedScroll(int axes) {
        return startNestedScroll(axes, 0);
    }

    public void stopNestedScroll() {
        stopNestedScroll(0);
    }

    public boolean dispatchNestedPreScroll(int dx, int dy, @Nullable int[] consumed, @Nullable int[] offsetInWindow, int type) {
        ViewParent nestedScrollingParentForType;
        int i;
        int i2;
        if (!isNestedScrollingEnabled() || (nestedScrollingParentForType = getNestedScrollingParentForType(type)) == null) {
            return false;
        }
        if (dx == 0 && dy == 0) {
            if (offsetInWindow != null) {
                offsetInWindow[0] = 0;
                offsetInWindow[1] = 0;
            }
            return false;
        }
        if (offsetInWindow != null) {
            this.mView.getLocationInWindow(offsetInWindow);
            i = offsetInWindow[0];
            i2 = offsetInWindow[1];
        } else {
            i = 0;
            i2 = 0;
        }
        if (consumed == null) {
            consumed = getTempNestedScrollConsumed();
        }
        consumed[0] = 0;
        consumed[1] = 0;
        ViewParentCompat.onNestedPreScroll(nestedScrollingParentForType, this.mView, dx, dy, consumed, type);
        if (offsetInWindow != null) {
            this.mView.getLocationInWindow(offsetInWindow);
            offsetInWindow[0] = offsetInWindow[0] - i;
            offsetInWindow[1] = offsetInWindow[1] - i2;
        }
        return (consumed[0] == 0 && consumed[1] == 0) ? false : true;
    }

    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @Nullable int[] offsetInWindow, int type) {
        return dispatchNestedScrollInternal(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow, type, null);
    }

    public boolean hasNestedScrollingParent(int type) {
        return getNestedScrollingParentForType(type) != null;
    }

    public boolean startNestedScroll(int axes, int type) {
        if (hasNestedScrollingParent(type)) {
            return true;
        }
        if (!isNestedScrollingEnabled()) {
            return false;
        }
        View view = this.mView;
        for (ViewParent parent = this.mView.getParent(); parent != null; parent = parent.getParent()) {
            if (ViewParentCompat.onStartNestedScroll(parent, view, this.mView, axes, type)) {
                setNestedScrollingParentForType(type, parent);
                ViewParentCompat.onNestedScrollAccepted(parent, view, this.mView, axes, type);
                return true;
            }
            if (parent instanceof View) {
                view = (View) parent;
            }
        }
        return false;
    }

    public void stopNestedScroll(int type) {
        ViewParent nestedScrollingParentForType = getNestedScrollingParentForType(type);
        if (nestedScrollingParentForType != null) {
            ViewParentCompat.onStopNestedScroll(nestedScrollingParentForType, this.mView, type);
            setNestedScrollingParentForType(type, null);
        }
    }

    public void dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @Nullable int[] offsetInWindow, int type, @Nullable int[] consumed) {
        dispatchNestedScrollInternal(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow, type, consumed);
    }
}
