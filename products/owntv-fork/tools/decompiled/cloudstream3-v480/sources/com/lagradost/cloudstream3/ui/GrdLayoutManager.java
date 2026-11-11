package com.lagradost.cloudstream3.ui;

import android.content.Context;
import android.view.View;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CustomRecyclerViews.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J.\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u00052\n\u0010\u000e\u001a\u00060\u000fR\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001a\u0010\u0013\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u0005H\u0016J\u0018\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0005H\u0002J\u0010\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0005H\u0002J\u0018\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u0005H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u001c"}, d2 = {"Lcom/lagradost/cloudstream3/ui/GrdLayoutManager;", "Landroidx/recyclerview/widget/GridLayoutManager;", "context", "Landroid/content/Context;", "spanCount", "", "<init>", "(Landroid/content/Context;I)V", "getContext", "()Landroid/content/Context;", "onFocusSearchFailed", "Landroid/view/View;", "focused", "focusDirection", "recycler", "Landroidx/recyclerview/widget/RecyclerView$Recycler;", "Landroidx/recyclerview/widget/RecyclerView;", "state", "Landroidx/recyclerview/widget/RecyclerView$State;", "onInterceptFocusSearch", "direction", "getNextViewPos", "fromPos", "calcOffsetToNextView", "hitBorder", "", TypedValues.TransitionType.S_FROM, TypedValues.CycleType.S_WAVE_OFFSET, "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class GrdLayoutManager extends GridLayoutManager {
    private final Context context;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GrdLayoutManager(Context context, int i) {
        super(context, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    public final Context getContext() {
        return this.context;
    }

    @Override // androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public View onFocusSearchFailed(View focused, int focusDirection, RecyclerView.Recycler recycler, RecyclerView.State state) {
        Intrinsics.checkNotNullParameter(focused, "focused");
        Intrinsics.checkNotNullParameter(recycler, "recycler");
        Intrinsics.checkNotNullParameter(state, "state");
        try {
            return findViewByPosition(getNextViewPos(getPosition(focused), focusDirection));
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public View onInterceptFocusSearch(View focused, int direction) {
        Intrinsics.checkNotNullParameter(focused, "focused");
        try {
            return findViewByPosition(getNextViewPos(getPosition(focused), direction));
        } catch (Exception unused) {
            return null;
        }
    }

    private final int getNextViewPos(int fromPos, int direction) {
        int iCalcOffsetToNextView = calcOffsetToNextView(direction);
        return hitBorder(fromPos, iCalcOffsetToNextView) ? fromPos : fromPos + iCalcOffsetToNextView;
    }

    private final int calcOffsetToNextView(int direction) {
        int spanCount = getSpanCount();
        int orientation = getOrientation();
        if (isLayoutRTL()) {
            if (direction == 17) {
                direction = 66;
            } else if (direction == 66) {
                direction = 17;
            }
        }
        if (orientation != 0) {
            if (orientation != 1) {
                return 0;
            }
            if (direction == 17) {
                return -1;
            }
            if (direction == 33) {
                return -spanCount;
            }
            if (direction == 66) {
                return 1;
            }
            if (direction != 130) {
                return 0;
            }
        } else {
            if (direction == 17) {
                return -spanCount;
            }
            if (direction == 33) {
                return -1;
            }
            if (direction != 66) {
                return direction != 130 ? 0 : 1;
            }
        }
        return spanCount;
    }

    private final boolean hitBorder(int from, int offset) {
        int spanCount = getSpanCount();
        if (Math.abs(offset) == 1) {
            int i = (from % spanCount) + offset;
            return i < 0 || i >= spanCount;
        }
        int i2 = from + offset;
        return spanCount <= i2 && i2 < 0;
    }
}
