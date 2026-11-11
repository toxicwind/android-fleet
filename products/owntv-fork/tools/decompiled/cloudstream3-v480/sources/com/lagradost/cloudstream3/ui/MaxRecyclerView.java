package com.lagradost.cloudstream3.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.view.ViewGroupKt;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CustomRecyclerViews.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\f\u0010\r\u001a\u00020\u000e*\u00020\u000fH\u0002J\u0010\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000fH\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/lagradost/cloudstream3/ui/MaxRecyclerView;", "Landroidx/recyclerview/widget/RecyclerView;", "ctx", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "biggestObserved", "", "orientation", "isHorizontal", "", "updateMaxSize", "", "Landroid/view/View;", "onChildAttachedToWindow", "child", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class MaxRecyclerView extends RecyclerView {
    private int biggestObserved;
    private final boolean isHorizontal;
    private final int orientation;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MaxRecyclerView(Context ctx, AttributeSet attrs) {
        super(ctx, attrs);
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        int i = RecyclerView.LayoutManager.getProperties(getContext(), attrs, 0, 0).orientation;
        this.orientation = i;
        this.isHorizontal = i == 0;
    }

    private final void updateMaxSize(View view) {
        boolean z = this.isHorizontal;
        int i = this.biggestObserved;
        if (z) {
            view.setMinimumHeight(i);
        } else {
            view.setMinimumWidth(i);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void onChildAttachedToWindow(View child) {
        Intrinsics.checkNotNullParameter(child, "child");
        child.measure(0, 0);
        int measuredHeight = this.isHorizontal ? child.getMeasuredHeight() : child.getMeasuredWidth();
        if (measuredHeight > this.biggestObserved) {
            this.biggestObserved = measuredHeight;
            Iterator<View> it = ViewGroupKt.getChildren(this).iterator();
            while (it.hasNext()) {
                updateMaxSize(it.next());
            }
        } else {
            updateMaxSize(child);
        }
        super.onChildAttachedToWindow(child);
    }
}
