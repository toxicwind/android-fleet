package com.lagradost.cloudstream3.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CenterZoomLayoutManager.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B-\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nB\u0013\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\t\u0010\u000bB#\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\f\u001a\u00020\u0007\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\t\u0010\u000fJ\u0010\u0010\u0018\u001a\u00020\u00122\b\b\u0002\u0010\u0019\u001a\u00020\u000eJ\u001a\u0010\u001a\u001a\u00020\u00122\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00120\u0011J\u0006\u0010\u001c\u001a\u00020\u0012J\u0012\u0010\u001d\u001a\u00020\u00122\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J+\u0010\"\u001a\u00020\u00122\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00072\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\u0002\u0010$J$\u0010%\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u00072\n\u0010'\u001a\u00060(R\u00020)2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016R\u001c\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0016X\u0082D¢\u0006\u0002\n\u0000R\u001c\u0010 \u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010!\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0014¨\u0006*"}, d2 = {"Lcom/lagradost/cloudstream3/widget/CenterZoomLayoutManager;", "Landroidx/recyclerview/widget/LinearLayoutManager;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "defStyleRes", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "(Landroid/content/Context;)V", "orientation", "reverseLayout", "", "(Landroid/content/Context;IZ)V", "itemListener", "Lkotlin/Function1;", "", "lastViewIndex", "Ljava/lang/Integer;", "mShrinkAmount", "", "mShrinkDistance", "updateSize", "forceUpdate", "setOnSizeListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "removeOnSizeListener", "onLayoutCompleted", "state", "Landroidx/recyclerview/widget/RecyclerView$State;", "waitForSnap", "snapChild", "snap", "callback", "(Ljava/lang/Integer;Lkotlin/jvm/functions/Function1;)V", "scrollHorizontallyBy", "dx", "recycler", "Landroidx/recyclerview/widget/RecyclerView$Recycler;", "Landroidx/recyclerview/widget/RecyclerView;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CenterZoomLayoutManager extends LinearLayoutManager {
    private Function1<? super Integer, Unit> itemListener;
    private Integer lastViewIndex;
    private final float mShrinkAmount;
    private final float mShrinkDistance;
    private Integer snapChild;
    private Function1<? super Integer, Unit> waitForSnap;

    public CenterZoomLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mShrinkAmount = 0.15f;
        this.mShrinkDistance = 0.9f;
    }

    public CenterZoomLayoutManager(Context context) {
        super(context);
        this.mShrinkAmount = 0.15f;
        this.mShrinkDistance = 0.9f;
    }

    public CenterZoomLayoutManager(Context context, int i, boolean z) {
        super(context, i, z);
        this.mShrinkAmount = 0.15f;
        this.mShrinkDistance = 0.9f;
    }

    public static /* synthetic */ void updateSize$default(CenterZoomLayoutManager centerZoomLayoutManager, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        centerZoomLayoutManager.updateSize(z);
    }

    public final void updateSize(boolean forceUpdate) {
        float fMin;
        Throwable th;
        Integer num;
        float width = getWidth() / 2.0f;
        float f = this.mShrinkDistance * width;
        float f2 = 1.0f - this.mShrinkAmount;
        int childCount = getChildCount();
        Integer numValueOf = null;
        float f3 = 0.0f;
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt != null) {
                try {
                    fMin = (((f2 - 1.0f) * (Math.min(f, Math.abs(width - ((getDecoratedRight(childAt) + getDecoratedLeft(childAt)) / 2.0f))) - 0.0f)) / (f - 0.0f)) + 1.0f;
                    childAt.setScaleX(fMin);
                    childAt.setScaleY(fMin);
                    if (fMin > f3 && (num = (Integer) childAt.getTag()) != null) {
                        try {
                            numValueOf = Integer.valueOf(num.intValue());
                        } catch (Throwable th2) {
                            th = th2;
                            ArchComponentExtKt.logError(th);
                        }
                        f3 = fMin;
                    }
                } catch (Throwable th3) {
                    fMin = f3;
                    th = th3;
                }
            }
        }
        if (numValueOf != null) {
            int iIntValue = numValueOf.intValue();
            Integer num2 = this.lastViewIndex;
            if (num2 == null || num2.intValue() != iIntValue || forceUpdate) {
                this.lastViewIndex = Integer.valueOf(iIntValue);
                Function1<? super Integer, Unit> function1 = this.itemListener;
                if (function1 != null) {
                    function1.invoke(Integer.valueOf(iIntValue));
                }
            }
        }
    }

    public final void setOnSizeListener(Function1<? super Integer, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.lastViewIndex = null;
        this.itemListener = listener;
    }

    public final void removeOnSizeListener() {
        this.itemListener = null;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutCompleted(RecyclerView.State state) {
        int[] iArrCalculateDistanceToFinalSnap;
        super.onLayoutCompleted(state);
        if (this.waitForSnap != null) {
            Integer num = this.snapChild;
            View childAt = getChildAt(num != null ? num.intValue() : 1);
            if (childAt != null && (iArrCalculateDistanceToFinalSnap = new LinearSnapHelper().calculateDistanceToFinalSnap(this, childAt)) != null) {
                int i = iArrCalculateDistanceToFinalSnap[0];
                Function1<? super Integer, Unit> function1 = this.waitForSnap;
                if (function1 != null) {
                    function1.invoke(Integer.valueOf(i));
                }
                this.waitForSnap = null;
            }
        }
        updateSize$default(this, false, 1, null);
    }

    public static /* synthetic */ void snap$default(CenterZoomLayoutManager centerZoomLayoutManager, Integer num, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            num = null;
        }
        centerZoomLayoutManager.snap(num, function1);
    }

    public final void snap(Integer snap, Function1<? super Integer, Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.waitForSnap = callback;
        this.snapChild = snap;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        Intrinsics.checkNotNullParameter(recycler, "recycler");
        Intrinsics.checkNotNullParameter(state, "state");
        if (getOrientation() != 0) {
            return 0;
        }
        int iScrollHorizontallyBy = super.scrollHorizontallyBy(dx, recycler, state);
        updateSize$default(this, false, 1, null);
        return iScrollHorizontallyBy;
    }
}
