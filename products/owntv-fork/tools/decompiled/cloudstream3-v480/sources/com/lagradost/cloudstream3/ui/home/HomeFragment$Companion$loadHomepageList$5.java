package com.lagradost.cloudstream3.ui.home;

import androidx.recyclerview.widget.RecyclerView;
import com.lagradost.cloudstream3.ui.search.SearchAdapter;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import com.lagradost.cloudstream3.utils.Coroutines;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HomeFragment.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0003H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0011"}, d2 = {"com/lagradost/cloudstream3/ui/home/HomeFragment$Companion$loadHomepageList$5", "Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;", "expandCount", "", "getExpandCount", "()I", "setExpandCount", "(I)V", "name", "", "getName", "()Ljava/lang/String;", "onScrollStateChanged", "", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "newState", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class HomeFragment$Companion$loadHomepageList$5 extends RecyclerView.OnScrollListener {
    final /* synthetic */ Function2<String, Continuation<? super HomeViewModel.ExpandableHomepageList>, Object> $expandCallback;
    private int expandCount;
    private final String name;

    /* JADX WARN: Multi-variable type inference failed */
    HomeFragment$Companion$loadHomepageList$5(HomeViewModel.ExpandableHomepageList expandableHomepageList, Function2<? super String, ? super Continuation<? super HomeViewModel.ExpandableHomepageList>, ? extends Object> function2) {
        this.$expandCallback = function2;
        this.name = expandableHomepageList.getList().getName();
    }

    public final int getExpandCount() {
        return this.expandCount;
    }

    public final void setExpandCount(int i) {
        this.expandCount = i;
    }

    public final String getName() {
        return this.name;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onScrollStateChanged(recyclerView, newState);
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter instanceof SearchAdapter) {
            SearchAdapter searchAdapter = (SearchAdapter) adapter;
            int itemCount = searchAdapter.getItemCount();
            boolean hasNext = searchAdapter.getHasNext();
            if (AppContextUtils.INSTANCE.isRecyclerScrollable(recyclerView) || !hasNext || this.expandCount == itemCount) {
                return;
            }
            this.expandCount = itemCount;
            Coroutines.INSTANCE.ioSafe(this, new HomeFragment$Companion$loadHomepageList$5$onScrollStateChanged$1(this.$expandCallback, this, recyclerView, null));
        }
    }
}
