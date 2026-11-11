package com.lagradost.cloudstream3.ui.home;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.lagradost.cloudstream3.HomePageList;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.databinding.HomepageParentBinding;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.ui.BaseAdapter;
import com.lagradost.cloudstream3.ui.BaseAdapterKt;
import com.lagradost.cloudstream3.ui.BaseDiffCallback;
import com.lagradost.cloudstream3.ui.ViewHolderState;
import com.lagradost.cloudstream3.ui.result.LinearListLayoutKt;
import com.lagradost.cloudstream3.ui.search.SearchClickCallback;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import java.util.Collection;
import java.util.Comparator;
import java.util.WeakHashMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: HomeParentItemAdapter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000 \u001e2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u001e\u001fBO\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007\u0012\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\t0\u0007\u0012\u0016\b\u0002\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007¢\u0006\u0004\b\r\u0010\u000eJ\"\u0010\u000f\u001a\u00020\t2\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J&\u0010\u0014\u001a\u00020\t2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\u00162\u0006\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0005H\u0016J&\u0010\u0019\u001a\u00020\t2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\u00162\u0006\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0005H\u0016J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\t0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/lagradost/cloudstream3/ui/home/ParentItemAdapter;", "Lcom/lagradost/cloudstream3/ui/BaseAdapter;", "Lcom/lagradost/cloudstream3/ui/home/HomeViewModel$ExpandableHomepageList;", "Landroid/os/Bundle;", "id", "", "clickCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/ui/search/SearchClickCallback;", "", "moreInfoClickCallback", "expandCallback", "", "<init>", "(ILkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "submitList", "list", "", "commitCallback", "Ljava/lang/Runnable;", "onUpdateContent", "holder", "Lcom/lagradost/cloudstream3/ui/ViewHolderState;", "item", "position", "onBindContent", "onCreateContent", "Lcom/lagradost/cloudstream3/ui/home/ParentItemAdapter$ParentItemHolder;", "parent", "Landroid/view/ViewGroup;", "Companion", "ParentItemHolder", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class ParentItemAdapter extends BaseAdapter<HomeViewModel.ExpandableHomepageList, Bundle> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Pair<WeakHashMap<Context, RecyclerView.RecycledViewPool>, Function1<RecyclerView.RecycledViewPool, Unit>> sharedPool = BaseAdapterKt.newSharedPool(new Function1() { // from class: com.lagradost.cloudstream3.ui.home.ParentItemAdapter$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ParentItemAdapter.sharedPool$lambda$0((RecyclerView.RecycledViewPool) obj);
        }
    });
    private final Function1<SearchClickCallback, Unit> clickCallback;
    private final Function1<String, Unit> expandCallback;
    private final Function1<HomeViewModel.ExpandableHomepageList, Unit> moreInfoClickCallback;

    public /* synthetic */ ParentItemAdapter(int i, Function1 function1, Function1 function2, Function1 function3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, function1, function2, (i2 & 8) != 0 ? null : function3);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ParentItemAdapter(int i, Function1<? super SearchClickCallback, Unit> clickCallback, Function1<? super HomeViewModel.ExpandableHomepageList, Unit> moreInfoClickCallback, Function1<? super String, Unit> function1) {
        super(i, new BaseDiffCallback(new Function2() { // from class: com.lagradost.cloudstream3.ui.home.ParentItemAdapter$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ParentItemAdapter._init_$lambda$0((HomeViewModel.ExpandableHomepageList) obj, (HomeViewModel.ExpandableHomepageList) obj2));
            }
        }, new Function2() { // from class: com.lagradost.cloudstream3.ui.home.ParentItemAdapter$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ParentItemAdapter._init_$lambda$1((HomeViewModel.ExpandableHomepageList) obj, (HomeViewModel.ExpandableHomepageList) obj2));
            }
        }));
        Intrinsics.checkNotNullParameter(clickCallback, "clickCallback");
        Intrinsics.checkNotNullParameter(moreInfoClickCallback, "moreInfoClickCallback");
        this.clickCallback = clickCallback;
        this.moreInfoClickCallback = moreInfoClickCallback;
        this.expandCallback = function1;
    }

    static final boolean _init_$lambda$0(HomeViewModel.ExpandableHomepageList a, HomeViewModel.ExpandableHomepageList b) {
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        return Intrinsics.areEqual(a.getList().getName(), b.getList().getName());
    }

    static final boolean _init_$lambda$1(HomeViewModel.ExpandableHomepageList a, HomeViewModel.ExpandableHomepageList b) {
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        return Intrinsics.areEqual(a.getList().getList(), b.getList().getList());
    }

    /* JADX INFO: compiled from: HomeParentItemAdapter.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R:\u0010\u0004\u001a+\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\b\u000b0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/lagradost/cloudstream3/ui/home/ParentItemAdapter$Companion;", "", "<init>", "()V", "sharedPool", "Lkotlin/Pair;", "Ljava/util/WeakHashMap;", "Landroid/content/Context;", "Landroidx/recyclerview/widget/RecyclerView$RecycledViewPool;", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "getSharedPool", "()Lkotlin/Pair;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Pair<WeakHashMap<Context, RecyclerView.RecycledViewPool>, Function1<RecyclerView.RecycledViewPool, Unit>> getSharedPool() {
            return ParentItemAdapter.sharedPool;
        }
    }

    static final Unit sharedPool$lambda$0(RecyclerView.RecycledViewPool newSharedPool) {
        Intrinsics.checkNotNullParameter(newSharedPool, "$this$newSharedPool");
        newSharedPool.setMaxRecycledViews(268435456, 4);
        return Unit.INSTANCE;
    }

    /* JADX INFO: compiled from: HomeParentItemAdapter.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\t\u001a\u00020\u0002H\u0016J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0002H\u0016J\t\u0010\r\u001a\u00020\u0004HÆ\u0003J\u0013\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0004HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0017"}, d2 = {"Lcom/lagradost/cloudstream3/ui/home/ParentItemAdapter$ParentItemHolder;", "Lcom/lagradost/cloudstream3/ui/ViewHolderState;", "Landroid/os/Bundle;", "binding", "Landroidx/viewbinding/ViewBinding;", "<init>", "(Landroidx/viewbinding/ViewBinding;)V", "getBinding", "()Landroidx/viewbinding/ViewBinding;", "save", "restore", "", "state", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class ParentItemHolder extends ViewHolderState<Bundle> {
        private final ViewBinding binding;

        public static /* synthetic */ ParentItemHolder copy$default(ParentItemHolder parentItemHolder, ViewBinding viewBinding, int i, Object obj) {
            if ((i & 1) != 0) {
                viewBinding = parentItemHolder.binding;
            }
            return parentItemHolder.copy(viewBinding);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final ViewBinding getBinding() {
            return this.binding;
        }

        public final ParentItemHolder copy(ViewBinding binding) {
            Intrinsics.checkNotNullParameter(binding, "binding");
            return new ParentItemHolder(binding);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof ParentItemHolder) && Intrinsics.areEqual(this.binding, ((ParentItemHolder) other).binding);
        }

        public int hashCode() {
            return this.binding.hashCode();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ViewHolder
        public String toString() {
            return "ParentItemHolder(binding=" + this.binding + ')';
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ParentItemHolder(ViewBinding binding) {
            super(binding);
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.binding = binding;
        }

        public final ViewBinding getBinding() {
            return this.binding;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.lagradost.cloudstream3.ui.ViewHolderState
        public Bundle save() {
            RecyclerView.LayoutManager layoutManager;
            Bundle bundle = new Bundle();
            ViewBinding viewBinding = this.binding;
            HomepageParentBinding homepageParentBinding = viewBinding instanceof HomepageParentBinding ? (HomepageParentBinding) viewBinding : null;
            RecyclerView recyclerView = homepageParentBinding != null ? homepageParentBinding.homeChildRecyclerview : null;
            bundle.putParcelable(ES6Iterator.VALUE_PROPERTY, (recyclerView == null || (layoutManager = recyclerView.getLayoutManager()) == null) ? null : layoutManager.onSaveInstanceState());
            RecyclerView.Adapter adapter = recyclerView != null ? recyclerView.getAdapter() : null;
            BaseAdapter baseAdapter = adapter instanceof BaseAdapter ? (BaseAdapter) adapter : null;
            if (baseAdapter != null) {
                baseAdapter.save(recyclerView);
            }
            return bundle;
        }

        @Override // com.lagradost.cloudstream3.ui.ViewHolderState
        public void restore(Bundle state) {
            RecyclerView recyclerView;
            RecyclerView.LayoutManager layoutManager;
            Intrinsics.checkNotNullParameter(state, "state");
            ViewBinding viewBinding = this.binding;
            HomepageParentBinding homepageParentBinding = viewBinding instanceof HomepageParentBinding ? (HomepageParentBinding) viewBinding : null;
            if (homepageParentBinding == null || (recyclerView = homepageParentBinding.homeChildRecyclerview) == null || (layoutManager = recyclerView.getLayoutManager()) == null) {
                return;
            }
            layoutManager.onRestoreInstanceState((Parcelable) (Build.VERSION.SDK_INT < 33 ? state.getParcelable(ES6Iterator.VALUE_PROPERTY) : state.getParcelable(ES6Iterator.VALUE_PROPERTY, Parcelable.class)));
        }
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public void submitList(Collection<? extends HomeViewModel.ExpandableHomepageList> list, Runnable commitCallback) {
        super.submitList(list != null ? CollectionsKt.sortedWith(list, new Comparator() { // from class: com.lagradost.cloudstream3.ui.home.ParentItemAdapter$submitList$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(Boolean.valueOf(((HomeViewModel.ExpandableHomepageList) t).getList().getList().isEmpty()), Boolean.valueOf(((HomeViewModel.ExpandableHomepageList) t2).getList().getList().isEmpty()));
            }
        }) : null, commitCallback);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public void onUpdateContent(ViewHolderState<Bundle> holder, HomeViewModel.ExpandableHomepageList item, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        ViewBinding view = holder.getView();
        if (view instanceof HomepageParentBinding) {
            RecyclerView.Adapter adapter = ((HomepageParentBinding) view).homeChildRecyclerview.getAdapter();
            HomeChildItemAdapter homeChildItemAdapter = adapter instanceof HomeChildItemAdapter ? (HomeChildItemAdapter) adapter : null;
            if (homeChildItemAdapter != null) {
                BaseAdapter.submitList$default(homeChildItemAdapter, item.getList().getList(), null, 2, null);
            }
        }
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public void onBindContent(ViewHolderState<Bundle> holder, final HomeViewModel.ExpandableHomepageList item, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        int i = R.id.nav_rail_view;
        ViewBinding view = holder.getView();
        if (view instanceof HomepageParentBinding) {
            HomePageList list = item.getList();
            HomepageParentBinding homepageParentBinding = (HomepageParentBinding) view;
            RecyclerView.Adapter adapter = homepageParentBinding.homeChildRecyclerview.getAdapter();
            HomeChildItemAdapter homeChildItemAdapter = adapter instanceof HomeChildItemAdapter ? (HomeChildItemAdapter) adapter : null;
            if (homeChildItemAdapter == null) {
                RecyclerView homeChildRecyclerview = homepageParentBinding.homeChildRecyclerview;
                Intrinsics.checkNotNullExpressionValue(homeChildRecyclerview, "homeChildRecyclerview");
                BaseAdapterKt.setRecycledViewPool(homeChildRecyclerview, HomeChildItemAdapter.INSTANCE.getSharedPool());
                RecyclerView recyclerView = homepageParentBinding.homeChildRecyclerview;
                HomeChildItemAdapter homeChildItemAdapter2 = new HomeChildItemAdapter(getId() + position + 100, Integer.valueOf(homepageParentBinding.homeChildRecyclerview.getNextFocusUpId()), Integer.valueOf(homepageParentBinding.homeChildRecyclerview.getNextFocusDownId()), this.clickCallback);
                homeChildItemAdapter2.setHorizontal(list.isHorizontalImages());
                homeChildItemAdapter2.setHasNext(item.getHasNext());
                BaseAdapter.submitList$default(homeChildItemAdapter2, item.getList().getList(), null, 2, null);
                recyclerView.setAdapter(homeChildItemAdapter2);
            } else {
                homeChildItemAdapter.setHorizontal(list.isHorizontalImages());
                homeChildItemAdapter.setHasNext(item.getHasNext());
                homeChildItemAdapter.setClickCallback(this.clickCallback);
                homeChildItemAdapter.setNextFocusUp(Integer.valueOf(homepageParentBinding.homeChildRecyclerview.getNextFocusUpId()));
                homeChildItemAdapter.setNextFocusDown(Integer.valueOf(homepageParentBinding.homeChildRecyclerview.getNextFocusDownId()));
                BaseAdapter.submitIncomparableList$default(homeChildItemAdapter, item.getList().getList(), null, 2, null);
            }
            LinearListLayoutKt.setLinearListLayout$default(homepageParentBinding.homeChildRecyclerview, true, i, -2, 0, 0, 24, null);
            homepageParentBinding.homeChildMoreInfo.setText(list.getName());
            homepageParentBinding.homeChildRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener(item, this) { // from class: com.lagradost.cloudstream3.ui.home.ParentItemAdapter$onBindContent$1$3
                private int expandCount;
                private final String name;
                final /* synthetic */ ParentItemAdapter this$0;

                {
                    this.this$0 = this;
                    this.name = item.getList().getName();
                }

                public final int getExpandCount() {
                    return this.expandCount;
                }

                public final void setExpandCount(int i2) {
                    this.expandCount = i2;
                }

                public final String getName() {
                    return this.name;
                }

                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrollStateChanged(RecyclerView recyclerView2, int newState) {
                    Intrinsics.checkNotNullParameter(recyclerView2, "recyclerView");
                    super.onScrollStateChanged(recyclerView2, newState);
                    RecyclerView.Adapter adapter2 = recyclerView2.getAdapter();
                    if (adapter2 instanceof HomeChildItemAdapter) {
                        HomeChildItemAdapter homeChildItemAdapter3 = (HomeChildItemAdapter) adapter2;
                        int itemCount = homeChildItemAdapter3.getItemCount();
                        boolean hasNext = homeChildItemAdapter3.getHasNext();
                        if (AppContextUtils.INSTANCE.isRecyclerScrollable(recyclerView2) || !hasNext || this.expandCount == itemCount) {
                            return;
                        }
                        this.expandCount = itemCount;
                        Function1 function1 = this.this$0.expandCallback;
                        if (function1 != null) {
                            function1.invoke(this.name);
                        }
                    }
                }
            });
            if (Globals.INSTANCE.isLayout(1)) {
                homepageParentBinding.homeChildMoreInfo.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.home.ParentItemAdapter$$ExternalSyntheticLambda3
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        ParentItemAdapter.onBindContent$lambda$0$2(this.f$0, item, view2);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindContent$lambda$0$2(ParentItemAdapter parentItemAdapter, HomeViewModel.ExpandableHomepageList expandableHomepageList, View view) {
        parentItemAdapter.moreInfoClickCallback.invoke(expandableHomepageList);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public ViewHolderState<Bundle> onCreateContent(ViewGroup parent) {
        int i;
        HomepageParentBinding homepageParentBindingInflate;
        Intrinsics.checkNotNullParameter(parent, "parent");
        if (Globals.INSTANCE.isLayout(2)) {
            i = R.layout.homepage_parent_tv;
        } else {
            i = Globals.INSTANCE.isLayout(4) ? R.layout.homepage_parent_emulator : R.layout.homepage_parent;
        }
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(parent.getContext());
        try {
            homepageParentBindingInflate = HomepageParentBinding.bind(layoutInflaterFrom.inflate(i, parent, false));
        } catch (Throwable th) {
            ArchComponentExtKt.logError(th);
            homepageParentBindingInflate = HomepageParentBinding.inflate(layoutInflaterFrom);
        }
        Intrinsics.checkNotNull(homepageParentBindingInflate);
        return new ParentItemHolder(homepageParentBindingInflate);
    }
}
