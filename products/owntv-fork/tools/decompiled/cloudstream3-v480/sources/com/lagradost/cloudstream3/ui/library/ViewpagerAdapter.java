package com.lagradost.cloudstream3.ui.library;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.google.android.material.appbar.AppBarLayout;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.databinding.LibraryViewpagerPageBinding;
import com.lagradost.cloudstream3.syncproviders.SyncAPI;
import com.lagradost.cloudstream3.ui.AutofitRecyclerView;
import com.lagradost.cloudstream3.ui.BaseAdapter;
import com.lagradost.cloudstream3.ui.BaseDiffCallback;
import com.lagradost.cloudstream3.ui.ViewHolderState;
import com.lagradost.cloudstream3.ui.search.SearchClickCallback;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.utils.UIHelper;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ViewpagerAdapter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B>\u0012!\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005\u0012\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\n0\u0005¢\u0006\u0004\b\r\u0010\u000eJ\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J&\u0010\u0016\u001a\u00020\n2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u00132\u0006\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J&\u0010\u001b\u001a\u00020\n2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u00132\u0006\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u001aH\u0016R,\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001d\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\n0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010¨\u0006\u001c"}, d2 = {"Lcom/lagradost/cloudstream3/ui/library/ViewpagerAdapter;", "Lcom/lagradost/cloudstream3/ui/BaseAdapter;", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$Page;", "Landroid/os/Bundle;", "scrollCallback", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "isScrollingDown", "", "clickCallback", "Lcom/lagradost/cloudstream3/ui/search/SearchClickCallback;", "<init>", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "getScrollCallback", "()Lkotlin/jvm/functions/Function1;", "getClickCallback", "onCreateContent", "Lcom/lagradost/cloudstream3/ui/ViewHolderState;", "parent", "Landroid/view/ViewGroup;", "onUpdateContent", "holder", "item", "position", "", "onBindContent", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ViewpagerAdapter extends BaseAdapter<SyncAPI.Page, Bundle> {
    private final Function1<SearchClickCallback, Unit> clickCallback;
    private final Function1<Boolean, Unit> scrollCallback;

    public final Function1<Boolean, Unit> getScrollCallback() {
        return this.scrollCallback;
    }

    public final Function1<SearchClickCallback, Unit> getClickCallback() {
        return this.clickCallback;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ViewpagerAdapter(Function1<? super Boolean, Unit> scrollCallback, Function1<? super SearchClickCallback, Unit> clickCallback) {
        super(-1891677871, new BaseDiffCallback(new Function2() { // from class: com.lagradost.cloudstream3.ui.library.ViewpagerAdapter$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ViewpagerAdapter._init_$lambda$0((SyncAPI.Page) obj, (SyncAPI.Page) obj2));
            }
        }, new Function2() { // from class: com.lagradost.cloudstream3.ui.library.ViewpagerAdapter$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ViewpagerAdapter._init_$lambda$1((SyncAPI.Page) obj, (SyncAPI.Page) obj2));
            }
        }));
        Intrinsics.checkNotNullParameter(scrollCallback, "scrollCallback");
        Intrinsics.checkNotNullParameter(clickCallback, "clickCallback");
        this.scrollCallback = scrollCallback;
        this.clickCallback = clickCallback;
    }

    static final boolean _init_$lambda$0(SyncAPI.Page a, SyncAPI.Page b) {
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        return Intrinsics.areEqual(a.getTitle(), b.getTitle());
    }

    static final boolean _init_$lambda$1(SyncAPI.Page a, SyncAPI.Page b) {
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        return Intrinsics.areEqual(a.getItems(), b.getItems()) && Intrinsics.areEqual(a.getTitle(), b.getTitle());
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public ViewHolderState<Bundle> onCreateContent(ViewGroup parent) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        LibraryViewpagerPageBinding libraryViewpagerPageBindingInflate = LibraryViewpagerPageBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(libraryViewpagerPageBindingInflate, "inflate(...)");
        return new ViewpagerAdapterViewHolderState(libraryViewpagerPageBindingInflate);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public void onUpdateContent(ViewHolderState<Bundle> holder, SyncAPI.Page item, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        ViewBinding view = holder.getView();
        if (view instanceof LibraryViewpagerPageBinding) {
            LibraryViewpagerPageBinding libraryViewpagerPageBinding = (LibraryViewpagerPageBinding) view;
            RecyclerView.Adapter adapter = libraryViewpagerPageBinding.pageRecyclerview.getAdapter();
            PageAdapter pageAdapter = adapter instanceof PageAdapter ? (PageAdapter) adapter : null;
            if (pageAdapter != null) {
                BaseAdapter.submitList$default(pageAdapter, item.getItems(), null, 2, null);
            }
            libraryViewpagerPageBinding.pageRecyclerview.scrollToPosition(0);
        }
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public void onBindContent(ViewHolderState<Bundle> holder, final SyncAPI.Page item, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        final ViewBinding view = holder.getView();
        if (view instanceof LibraryViewpagerPageBinding) {
            LibraryViewpagerPageBinding libraryViewpagerPageBinding = (LibraryViewpagerPageBinding) view;
            libraryViewpagerPageBinding.pageRecyclerview.setTag(Integer.valueOf(position));
            final AutofitRecyclerView autofitRecyclerView = libraryViewpagerPageBinding.pageRecyclerview;
            UIHelper uIHelper = UIHelper.INSTANCE;
            Context context = libraryViewpagerPageBinding.getRoot().getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            autofitRecyclerView.setSpanCount(UIHelper.getSpanCount$default(uIHelper, context, false, 1, null));
            if (autofitRecyclerView.getAdapter() == null) {
                Intrinsics.checkNotNull(autofitRecyclerView);
                final AutofitRecyclerView autofitRecyclerView2 = autofitRecyclerView;
                if (autofitRecyclerView2.isAttachedToWindow()) {
                    PageAdapter pageAdapter = new PageAdapter(autofitRecyclerView, getClickCallback());
                    BaseAdapter.submitList$default(pageAdapter, item.getItems(), null, 2, null);
                    autofitRecyclerView.setAdapter(pageAdapter);
                } else {
                    autofitRecyclerView2.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.lagradost.cloudstream3.ui.library.ViewpagerAdapter$onBindContent$lambda$0$$inlined$doOnAttach$1
                        @Override // android.view.View.OnAttachStateChangeListener
                        public void onViewDetachedFromWindow(View view2) {
                        }

                        @Override // android.view.View.OnAttachStateChangeListener
                        public void onViewAttachedToWindow(View view2) {
                            autofitRecyclerView2.removeOnAttachStateChangeListener(this);
                            AutofitRecyclerView autofitRecyclerView3 = autofitRecyclerView;
                            Intrinsics.checkNotNull(autofitRecyclerView);
                            PageAdapter pageAdapter2 = new PageAdapter(autofitRecyclerView, this.getClickCallback());
                            BaseAdapter.submitList$default(pageAdapter2, item.getItems(), null, 2, null);
                            autofitRecyclerView3.setAdapter(pageAdapter2);
                        }
                    });
                }
            } else {
                RecyclerView.Adapter adapter = autofitRecyclerView.getAdapter();
                PageAdapter pageAdapter2 = adapter instanceof PageAdapter ? (PageAdapter) adapter : null;
                if (pageAdapter2 != null) {
                    BaseAdapter.submitList$default(pageAdapter2, item.getItems(), null, 2, null);
                }
            }
            autofitRecyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() { // from class: com.lagradost.cloudstream3.ui.library.ViewpagerAdapter$$ExternalSyntheticLambda0
                @Override // android.view.View.OnScrollChangeListener
                public final void onScrollChange(View view2, int i, int i2, int i3, int i4) {
                    ViewpagerAdapter.onBindContent$lambda$0$1(view, this, view2, i, i2, i3, i4);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindContent$lambda$0$1(ViewBinding viewBinding, ViewpagerAdapter viewpagerAdapter, View view, int i, int i2, int i3, int i4) {
        AppBarLayout appBarLayout;
        int i5 = i2 - i4;
        if (Globals.INSTANCE.isLayout(6) && (appBarLayout = (AppBarLayout) ((LibraryViewpagerPageBinding) viewBinding).getRoot().getRootView().findViewById(R.id.search_bar)) != null) {
            if (i5 <= 0) {
                appBarLayout.setExpanded(true);
            } else {
                appBarLayout.setExpanded(false);
            }
        }
        if (i5 == 0) {
            return;
        }
        viewpagerAdapter.scrollCallback.invoke(Boolean.valueOf(i5 > 0));
    }
}
