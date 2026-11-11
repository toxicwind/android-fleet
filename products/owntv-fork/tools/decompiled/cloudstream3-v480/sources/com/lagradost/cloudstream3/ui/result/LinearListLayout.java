package com.lagradost.cloudstream3.ui.result;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.FocusDirection;
import com.lagradost.cloudstream3.actions.temp.CloudStreamPackage;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.ui.settings.Globals;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LinearListLayout.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u0015\u001a\u00020\u0016J\u0006\u0010\u0017\u001a\u00020\u0016J\u0014\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019H\u0002J\u0019\u0010\u001b\u001a\u0004\u0018\u00010\u00072\b\u0010\u001c\u001a\u0004\u0018\u00010\u0019H\u0002¢\u0006\u0002\u0010\u001dJ\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001f\u001a\u00020\u0007H\u0002J\u001c\u0010 \u001a\u0004\u0018\u00010\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u00192\u0006\u0010!\u001a\u00020\"H\u0002J\u0010\u0010#\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u0019J\u001a\u0010$\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010!\u001a\u00020\u0007H\u0016J0\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u00192\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020&2\u0006\u0010-\u001a\u00020&H\u0016R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\u000bR\u001a\u0010\u000f\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000bR\u001a\u0010\u0012\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\t\"\u0004\b\u0014\u0010\u000b¨\u0006."}, d2 = {"Lcom/lagradost/cloudstream3/ui/result/LinearListLayout;", "Landroidx/recyclerview/widget/LinearLayoutManager;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "nextFocusLeft", "", "getNextFocusLeft", "()I", "setNextFocusLeft", "(I)V", "nextFocusRight", "getNextFocusRight", "setNextFocusRight", "nextFocusUp", "getNextFocusUp", "setNextFocusUp", "nextFocusDown", "getNextFocusDown", "setNextFocusDown", "setHorizontal", "", "setVertical", "getCorrectParent", "Landroid/view/View;", "focused", "getPosition", "view", "(Landroid/view/View;)Ljava/lang/Integer;", "getViewFromPos", CloudStreamPackage.POSITION_EXTRA, "getNextDirection", "direction", "Lcom/lagradost/cloudstream3/FocusDirection;", "redirectRecycleToFirstItem", "onInterceptFocusSearch", "requestChildRectangleOnScreen", "", "parent", "Landroidx/recyclerview/widget/RecyclerView;", "child", "rect", "Landroid/graphics/Rect;", "immediate", "focusedChildVisible", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class LinearListLayout extends LinearLayoutManager {
    private int nextFocusDown;
    private int nextFocusLeft;
    private int nextFocusRight;
    private int nextFocusUp;

    /* JADX INFO: compiled from: LinearListLayout.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[FocusDirection.values().length];
            try {
                iArr[FocusDirection.Start.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[FocusDirection.End.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[FocusDirection.Up.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[FocusDirection.Down.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public LinearListLayout(Context context) {
        super(context);
        this.nextFocusLeft = -1;
        this.nextFocusRight = -1;
        this.nextFocusUp = -1;
        this.nextFocusDown = -1;
    }

    public final int getNextFocusLeft() {
        return this.nextFocusLeft;
    }

    public final void setNextFocusLeft(int i) {
        this.nextFocusLeft = i;
    }

    public final int getNextFocusRight() {
        return this.nextFocusRight;
    }

    public final void setNextFocusRight(int i) {
        this.nextFocusRight = i;
    }

    public final int getNextFocusUp() {
        return this.nextFocusUp;
    }

    public final void setNextFocusUp(int i) {
        this.nextFocusUp = i;
    }

    public final int getNextFocusDown() {
        return this.nextFocusDown;
    }

    public final void setNextFocusDown(int i) {
        this.nextFocusDown = i;
    }

    public final void setHorizontal() {
        setOrientation(0);
    }

    public final void setVertical() {
        setOrientation(1);
    }

    private final View getCorrectParent(View focused) {
        if (focused == null) {
            return null;
        }
        ArrayList arrayListArrayListOf = CollectionsKt.arrayListOf(focused);
        while (focused != null && !(focused instanceof RecyclerView)) {
            Object parent = focused.getParent();
            focused = parent instanceof View ? (View) parent : null;
            if (focused != null) {
                arrayListArrayListOf.add(focused);
            } else {
                focused = null;
            }
        }
        return (View) CollectionsKt.getOrNull(arrayListArrayListOf, arrayListArrayListOf.size() - 2);
    }

    private final Integer getPosition(View view) {
        ViewGroup.LayoutParams layoutParams = view != null ? view.getLayoutParams() : null;
        RecyclerView.LayoutParams layoutParams2 = layoutParams instanceof RecyclerView.LayoutParams ? (RecyclerView.LayoutParams) layoutParams : null;
        if (layoutParams2 != null) {
            return Integer.valueOf(layoutParams2.getAbsoluteAdapterPosition());
        }
        return null;
    }

    private final View getViewFromPos(int pos) {
        int childCount = getChildCount();
        int i = 0;
        while (true) {
            if (i >= childCount) {
                return null;
            }
            View childAt = getChildAt(i);
            ViewGroup.LayoutParams layoutParams = childAt != null ? childAt.getLayoutParams() : null;
            RecyclerView.LayoutParams layoutParams2 = layoutParams instanceof RecyclerView.LayoutParams ? (RecyclerView.LayoutParams) layoutParams : null;
            if (layoutParams2 != null && layoutParams2.getAbsoluteAdapterPosition() == pos) {
                return childAt;
            }
            i++;
        }
    }

    private final View getNextDirection(View focused, FocusDirection direction) {
        int i;
        int i2 = WhenMappings.$EnumSwitchMapping$0[direction.ordinal()];
        if (i2 == 1) {
            i = isLayoutRTL() ? this.nextFocusRight : this.nextFocusLeft;
        } else if (i2 == 2) {
            i = isLayoutRTL() ? this.nextFocusLeft : this.nextFocusRight;
        } else if (i2 == 3) {
            i = this.nextFocusUp;
        } else {
            if (i2 != 4) {
                throw new NoWhenBranchMatchedException();
            }
            i = this.nextFocusDown;
        }
        int i3 = i;
        if (i3 == -2) {
            return focused;
        }
        if (i3 == -1) {
            return null;
        }
        CommonActivity commonActivity = CommonActivity.INSTANCE;
        Activity activity = CommonActivity.INSTANCE.getActivity();
        View view = activity != null ? activity : focused;
        if (focused == null) {
            return null;
        }
        return CommonActivity.continueGetNextFocus$default(commonActivity, view, focused, direction, i3, 0, 16, null);
    }

    public final View redirectRecycleToFirstItem(View focused) {
        View viewFindViewByPosition;
        Intrinsics.checkNotNullParameter(focused, "focused");
        if (focused instanceof RecyclerView) {
            RecyclerView.LayoutManager layoutManager = ((RecyclerView) focused).getLayoutManager();
            LinearListLayout linearListLayout = layoutManager instanceof LinearListLayout ? (LinearListLayout) layoutManager : null;
            if (linearListLayout != null && (viewFindViewByPosition = linearListLayout.findViewByPosition(linearListLayout.findFirstVisibleItemPosition())) != null) {
                return viewFindViewByPosition;
            }
        }
        return focused;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public View onInterceptFocusSearch(View focused, int direction) {
        View nextDirection;
        View nextDirection2;
        View nextDirection3;
        View nextDirection4;
        Intrinsics.checkNotNullParameter(focused, "focused");
        int i = 1;
        if (getOrientation() == 0) {
            if (direction == 130 && (nextDirection4 = getNextDirection(focused, FocusDirection.Down)) != null) {
                return redirectRecycleToFirstItem(nextDirection4);
            }
            if (direction == 33 && (nextDirection3 = getNextDirection(focused, FocusDirection.Up)) != null) {
                return redirectRecycleToFirstItem(nextDirection3);
            }
            if (direction == 33 || direction == 130) {
                ViewParent parent = focused.getParent();
                RecyclerView recyclerView = parent instanceof RecyclerView ? (RecyclerView) parent : null;
                if (recyclerView != null) {
                    recyclerView.focusSearch(direction);
                }
                return null;
            }
            i = direction != 66 ? -1 : 1;
            if (isLayoutRTL()) {
                i = -i;
            }
        } else {
            if (direction == 66 && (nextDirection2 = getNextDirection(focused, FocusDirection.End)) != null) {
                return nextDirection2;
            }
            if (direction == 17 && (nextDirection = getNextDirection(focused, FocusDirection.Start)) != null) {
                return nextDirection;
            }
            if (direction == 17 || direction == 66) {
                ViewParent parent2 = focused.getParent();
                RecyclerView recyclerView2 = parent2 instanceof RecyclerView ? (RecyclerView) parent2 : null;
                if (recyclerView2 != null) {
                    recyclerView2.focusSearch(direction);
                }
                return null;
            }
            if (direction != 130) {
                i = -1;
            }
        }
        try {
            Integer position = getPosition(getCorrectParent(focused));
            if (position == null) {
                return null;
            }
            int iIntValue = i + position.intValue();
            if (iIntValue >= getItemCount()) {
                return getNextDirection(focused, getOrientation() == 0 ? FocusDirection.End : FocusDirection.Down);
            }
            if (iIntValue < 0) {
                return getNextDirection(focused, getOrientation() == 0 ? FocusDirection.Start : FocusDirection.Up);
            }
            View viewFromPos = getViewFromPos(iIntValue);
            if (viewFromPos != null) {
                return viewFromPos;
            }
            LinearListLayout linearListLayout = this;
            scrollToPosition(iIntValue);
            return null;
        } catch (Exception e) {
            ArchComponentExtKt.logError(e);
            return null;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean requestChildRectangleOnScreen(RecyclerView parent, View child, Rect rect, boolean immediate, boolean focusedChildVisible) {
        int decoratedLeft;
        int paddingLeft;
        Intrinsics.checkNotNullParameter(parent, "parent");
        Intrinsics.checkNotNullParameter(child, "child");
        Intrinsics.checkNotNullParameter(rect, "rect");
        if (Globals.INSTANCE.isLayout(2) && getOrientation() == 0) {
            if (isLayoutRTL()) {
                decoratedLeft = getDecoratedRight(child);
                paddingLeft = parent.getWidth() - parent.getPaddingRight();
            } else {
                decoratedLeft = getDecoratedLeft(child);
                paddingLeft = parent.getPaddingLeft();
            }
            int i = decoratedLeft - paddingLeft;
            if (i == 0) {
                return false;
            }
            if (immediate) {
                parent.scrollBy(i, 0);
                return true;
            }
            parent.smoothScrollBy(i, 0);
            return true;
        }
        return super.requestChildRectangleOnScreen(parent, child, rect, immediate, focusedChildVisible);
    }
}
