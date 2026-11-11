package com.lagradost.cloudstream3.ui.library;

import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;

/* JADX INFO: compiled from: ViewpagerAdapter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0016¨\u0006\u0007"}, d2 = {"com/lagradost/cloudstream3/ui/library/ViewpagerAdapter$onBindContent$1$3", "Landroidx/recyclerview/widget/RecyclerView$OnFlingListener;", "onFling", "", "velocityX", "", "velocityY", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ViewpagerAdapter$onBindContent$1$3 extends RecyclerView.OnFlingListener {
    final /* synthetic */ ViewpagerAdapter this$0;

    ViewpagerAdapter$onBindContent$1$3(ViewpagerAdapter viewpagerAdapter) {
        this.this$0 = viewpagerAdapter;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnFlingListener
    public boolean onFling(int velocityX, int velocityY) {
        this.this$0.getScrollCallback().invoke(Boolean.valueOf(velocityY > 0));
        return false;
    }
}
