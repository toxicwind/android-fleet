package com.lagradost.cloudstream3.ui.library;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.viewbinding.ViewBinding;
import com.lagradost.cloudstream3.databinding.SearchResultGridExpandedBinding;
import com.lagradost.cloudstream3.syncproviders.SyncAPI;
import com.lagradost.cloudstream3.ui.AutofitRecyclerView;
import com.lagradost.cloudstream3.ui.BaseAdapter;
import com.lagradost.cloudstream3.ui.BaseDiffCallback;
import com.lagradost.cloudstream3.ui.NoStateAdapter;
import com.lagradost.cloudstream3.ui.ViewHolderState;
import com.lagradost.cloudstream3.ui.search.SearchClickCallback;
import com.lagradost.cloudstream3.ui.search.SearchResultBuilder;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: PageAdapter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B#\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0004\b\t\u0010\nJ\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0016\u0010\u0016\u001a\u00020\b2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0016J&\u0010\u0018\u001a\u00020\b2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u000eH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001b"}, d2 = {"Lcom/lagradost/cloudstream3/ui/library/PageAdapter;", "Lcom/lagradost/cloudstream3/ui/NoStateAdapter;", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$LibraryItem;", "resView", "Lcom/lagradost/cloudstream3/ui/AutofitRecyclerView;", "clickCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/ui/search/SearchClickCallback;", "", "<init>", "(Lcom/lagradost/cloudstream3/ui/AutofitRecyclerView;Lkotlin/jvm/functions/Function1;)V", "getClickCallback", "()Lkotlin/jvm/functions/Function1;", "coverHeight", "", "getCoverHeight", "()I", "onCreateContent", "Lcom/lagradost/cloudstream3/ui/ViewHolderState;", "", "parent", "Landroid/view/ViewGroup;", "onClearView", "holder", "onBindContent", "item", "position", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class PageAdapter extends NoStateAdapter<SyncAPI.LibraryItem> {
    private final Function1<SearchClickCallback, Unit> clickCallback;
    private final AutofitRecyclerView resView;

    public final Function1<SearchClickCallback, Unit> getClickCallback() {
        return this.clickCallback;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public PageAdapter(AutofitRecyclerView resView, Function1<? super SearchClickCallback, Unit> clickCallback) {
        super(new BaseDiffCallback(new Function2() { // from class: com.lagradost.cloudstream3.ui.library.PageAdapter$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(PageAdapter._init_$lambda$0((SyncAPI.LibraryItem) obj, (SyncAPI.LibraryItem) obj2));
            }
        }, null, 2, null));
        Intrinsics.checkNotNullParameter(resView, "resView");
        Intrinsics.checkNotNullParameter(clickCallback, "clickCallback");
        this.resView = resView;
        this.clickCallback = clickCallback;
    }

    static final boolean _init_$lambda$0(SyncAPI.LibraryItem a, SyncAPI.LibraryItem b) {
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        if (a.getId() == null && b.getId() == null) {
            return Intrinsics.areEqual(a.getName(), b.getName()) && Intrinsics.areEqual(a.getUrl(), b.getUrl());
        }
        return Intrinsics.areEqual(a.getId(), b.getId());
    }

    private final int getCoverHeight() {
        return MathKt.roundToInt(((double) this.resView.getItemWidth()) / 0.68d);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public ViewHolderState<Object> onCreateContent(ViewGroup parent) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        SearchResultGridExpandedBinding searchResultGridExpandedBindingInflate = SearchResultGridExpandedBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(searchResultGridExpandedBindingInflate, "inflate(...)");
        return new ViewHolderState<>(searchResultGridExpandedBindingInflate);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public void onClearView(ViewHolderState<Object> holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        ViewBinding view = holder.getView();
        if (view instanceof SearchResultGridExpandedBinding) {
            BaseAdapter.INSTANCE.clearImage(((SearchResultGridExpandedBinding) view).imageView);
        }
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public void onBindContent(ViewHolderState<Object> holder, SyncAPI.LibraryItem item, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        ViewBinding view = holder.getView();
        SearchResultGridExpandedBinding searchResultGridExpandedBinding = view instanceof SearchResultGridExpandedBinding ? (SearchResultGridExpandedBinding) view : null;
        if (searchResultGridExpandedBinding == null) {
            return;
        }
        View itemView = holder.itemView;
        Intrinsics.checkNotNullExpressionValue(itemView, "itemView");
        SearchResultBuilder.bind$default(SearchResultBuilder.INSTANCE, this.clickCallback, item, position, itemView, null, null, null, 112, null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, getCoverHeight());
        if (layoutParams.height != searchResultGridExpandedBinding.imageView.getLayoutParams().height || layoutParams.width != searchResultGridExpandedBinding.imageView.getLayoutParams().width) {
            searchResultGridExpandedBinding.imageView.setLayoutParams(layoutParams);
        }
        Integer episodesCompleted = item.getEpisodesCompleted();
        boolean z = (episodesCompleted == null || episodesCompleted.intValue() <= 0 || item.getEpisodesTotal() == null) ? false : true;
        ContentLoadingProgressBar watchProgress = searchResultGridExpandedBinding.watchProgress;
        Intrinsics.checkNotNullExpressionValue(watchProgress, "watchProgress");
        watchProgress.setVisibility(z ? 0 : 8);
        if (z) {
            searchResultGridExpandedBinding.watchProgress.setMax(item.getEpisodesTotal().intValue());
            searchResultGridExpandedBinding.watchProgress.setProgress(item.getEpisodesCompleted().intValue());
        }
        searchResultGridExpandedBinding.imageText.setText(item.getName());
    }
}
