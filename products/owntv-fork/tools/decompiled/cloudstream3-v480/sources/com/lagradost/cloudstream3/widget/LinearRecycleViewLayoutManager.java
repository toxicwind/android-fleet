package com.lagradost.cloudstream3.widget;

import android.content.Context;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LinearRecycleViewLayoutManager.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u001a\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0005H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0012"}, d2 = {"Lcom/lagradost/cloudstream3/widget/LinearRecycleViewLayoutManager;", "Landroidx/recyclerview/widget/LinearLayoutManager;", "context", "Landroid/content/Context;", "nextFocusUp", "", "nextFocusDown", "<init>", "(Landroid/content/Context;II)V", "getContext", "()Landroid/content/Context;", "getNextFocusUp", "()I", "getNextFocusDown", "onInterceptFocusSearch", "Landroid/view/View;", "focused", "direction", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class LinearRecycleViewLayoutManager extends LinearLayoutManager {
    private final Context context;
    private final int nextFocusDown;
    private final int nextFocusUp;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LinearRecycleViewLayoutManager(Context context, int i, int i2) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.nextFocusUp = i;
        this.nextFocusDown = i2;
    }

    public final Context getContext() {
        return this.context;
    }

    public final int getNextFocusUp() {
        return this.nextFocusUp;
    }

    public final int getNextFocusDown() {
        return this.nextFocusDown;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public View onInterceptFocusSearch(View focused, int direction) {
        View viewOnInterceptFocusSearch;
        Intrinsics.checkNotNullParameter(focused, "focused");
        try {
            int position = getPosition(focused);
            if (position == getItemCount() - 1 && direction == 130) {
                viewOnInterceptFocusSearch = focused.getRootView().findViewById(this.nextFocusDown);
            } else if (position == 0 && direction == 33) {
                viewOnInterceptFocusSearch = focused.getRootView().findViewById(this.nextFocusUp);
            } else {
                viewOnInterceptFocusSearch = super.onInterceptFocusSearch(focused, direction);
            }
            return viewOnInterceptFocusSearch == null ? super.onInterceptFocusSearch(focused, direction) : viewOnInterceptFocusSearch;
        } catch (Throwable unused) {
            return super.onInterceptFocusSearch(focused, direction);
        }
    }
}
