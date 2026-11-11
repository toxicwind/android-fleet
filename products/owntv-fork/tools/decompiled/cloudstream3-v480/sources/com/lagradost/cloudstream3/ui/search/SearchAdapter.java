package com.lagradost.cloudstream3.ui.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.lagradost.cloudstream3.SearchResponse;
import com.lagradost.cloudstream3.databinding.SearchResultGridBinding;
import com.lagradost.cloudstream3.databinding.SearchResultGridExpandedBinding;
import com.lagradost.cloudstream3.ui.AutofitRecyclerView;
import com.lagradost.cloudstream3.ui.BaseAdapter;
import com.lagradost.cloudstream3.ui.BaseAdapterKt;
import com.lagradost.cloudstream3.ui.BaseDiffCallback;
import com.lagradost.cloudstream3.ui.NoStateAdapter;
import com.lagradost.cloudstream3.ui.ViewHolderState;
import com.lagradost.cloudstream3.utils.UIHelper;
import java.util.WeakHashMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: SearchAdaptor.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \"2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\"B-\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0004\b\u000b\u0010\fJ\u0016\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u0016\u0010\u001d\u001a\u00020\n2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019H\u0016J&\u0010\u001f\u001a\u00020\n2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010 \u001a\u00020\u00022\u0006\u0010!\u001a\u00020\u0015H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\u00020\u00158BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017¨\u0006#"}, d2 = {"Lcom/lagradost/cloudstream3/ui/search/SearchAdapter;", "Lcom/lagradost/cloudstream3/ui/NoStateAdapter;", "Lcom/lagradost/cloudstream3/SearchResponse;", "resView", "Lcom/lagradost/cloudstream3/ui/AutofitRecyclerView;", "isHorizontal", "", "clickCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/ui/search/SearchClickCallback;", "", "<init>", "(Lcom/lagradost/cloudstream3/ui/AutofitRecyclerView;ZLkotlin/jvm/functions/Function1;)V", "hasNext", "getHasNext", "()Z", "setHasNext", "(Z)V", "coverRatio", "", "coverHeight", "", "getCoverHeight", "()I", "onCreateContent", "Lcom/lagradost/cloudstream3/ui/ViewHolderState;", "", "parent", "Landroid/view/ViewGroup;", "onClearView", "holder", "onBindContent", "item", "position", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SearchAdapter extends NoStateAdapter<SearchResponse> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Pair<WeakHashMap<Context, RecyclerView.RecycledViewPool>, Function1<RecyclerView.RecycledViewPool, Unit>> sharedPool = BaseAdapterKt.newSharedPool(new Function1() { // from class: com.lagradost.cloudstream3.ui.search.SearchAdapter$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return SearchAdapter.sharedPool$lambda$0((RecyclerView.RecycledViewPool) obj);
        }
    });
    private final Function1<SearchClickCallback, Unit> clickCallback;
    private final double coverRatio;
    private boolean hasNext;
    private final boolean isHorizontal;
    private final AutofitRecyclerView resView;

    public /* synthetic */ SearchAdapter(AutofitRecyclerView autofitRecyclerView, boolean z, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(autofitRecyclerView, (i & 2) != 0 ? false : z, function1);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SearchAdapter(AutofitRecyclerView resView, boolean z, Function1<? super SearchClickCallback, Unit> clickCallback) {
        super(new BaseDiffCallback(new Function2() { // from class: com.lagradost.cloudstream3.ui.search.SearchAdapter$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(SearchAdapter._init_$lambda$0((SearchResponse) obj, (SearchResponse) obj2));
            }
        }, null, 2, null));
        Intrinsics.checkNotNullParameter(resView, "resView");
        Intrinsics.checkNotNullParameter(clickCallback, "clickCallback");
        this.resView = resView;
        this.isHorizontal = z;
        this.clickCallback = clickCallback;
        this.coverRatio = z ? 1.8d : 0.68d;
    }

    static final boolean _init_$lambda$0(SearchResponse a, SearchResponse b) {
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        if (a.getId() != null || b.getId() != null) {
            return Intrinsics.areEqual(a.getId(), b.getId());
        }
        return Intrinsics.areEqual(a.getName(), b.getName());
    }

    /* JADX INFO: compiled from: SearchAdaptor.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R:\u0010\u0004\u001a+\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\b\u000b0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/lagradost/cloudstream3/ui/search/SearchAdapter$Companion;", "", "<init>", "()V", "sharedPool", "Lkotlin/Pair;", "Ljava/util/WeakHashMap;", "Landroid/content/Context;", "Landroidx/recyclerview/widget/RecyclerView$RecycledViewPool;", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "getSharedPool", "()Lkotlin/Pair;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Pair<WeakHashMap<Context, RecyclerView.RecycledViewPool>, Function1<RecyclerView.RecycledViewPool, Unit>> getSharedPool() {
            return SearchAdapter.sharedPool;
        }
    }

    static final Unit sharedPool$lambda$0(RecyclerView.RecycledViewPool newSharedPool) {
        Intrinsics.checkNotNullParameter(newSharedPool, "$this$newSharedPool");
        newSharedPool.setMaxRecycledViews(268435456, 10);
        return Unit.INSTANCE;
    }

    public final boolean getHasNext() {
        return this.hasNext;
    }

    public final void setHasNext(boolean z) {
        this.hasNext = z;
    }

    private final int getCoverHeight() {
        return MathKt.roundToInt(((double) this.resView.getItemWidth()) / this.coverRatio);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public ViewHolderState<Object> onCreateContent(ViewGroup parent) {
        SearchResultGridBinding searchResultGridBinding;
        Intrinsics.checkNotNullParameter(parent, "parent");
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(parent.getContext());
        if (UIHelper.INSTANCE.isBottomLayout(parent.getContext())) {
            SearchResultGridExpandedBinding searchResultGridExpandedBindingInflate = SearchResultGridExpandedBinding.inflate(layoutInflaterFrom, parent, false);
            Intrinsics.checkNotNullExpressionValue(searchResultGridExpandedBindingInflate, "inflate(...)");
            searchResultGridBinding = searchResultGridExpandedBindingInflate;
        } else {
            SearchResultGridBinding searchResultGridBindingInflate = SearchResultGridBinding.inflate(layoutInflaterFrom, parent, false);
            Intrinsics.checkNotNullExpressionValue(searchResultGridBindingInflate, "inflate(...)");
            searchResultGridBinding = searchResultGridBindingInflate;
        }
        return new ViewHolderState<>(searchResultGridBinding);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public void onClearView(ViewHolderState<Object> holder) {
        ImageView imageView;
        Intrinsics.checkNotNullParameter(holder, "holder");
        BaseAdapter.Companion companion = BaseAdapter.INSTANCE;
        ViewBinding view = holder.getView();
        if (view instanceof SearchResultGridExpandedBinding) {
            imageView = ((SearchResultGridExpandedBinding) view).imageView;
        } else {
            imageView = view instanceof SearchResultGridBinding ? ((SearchResultGridBinding) view).imageView : null;
        }
        companion.clearImage(imageView);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public void onBindContent(ViewHolderState<Object> holder, SearchResponse item, int position) {
        ImageView imageView;
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        ViewBinding view = holder.getView();
        if (view instanceof SearchResultGridExpandedBinding) {
            imageView = ((SearchResultGridExpandedBinding) view).imageView;
        } else {
            imageView = view instanceof SearchResultGridBinding ? ((SearchResultGridBinding) view).imageView : null;
        }
        if (imageView != null) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, getCoverHeight());
            if (imageView.getLayoutParams().width != layoutParams.width || imageView.getLayoutParams().height != layoutParams.height) {
                imageView.setLayoutParams(layoutParams);
            }
        }
        SearchResultBuilder searchResultBuilder = SearchResultBuilder.INSTANCE;
        Function1<SearchClickCallback, Unit> function1 = this.clickCallback;
        View root = holder.getView().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        SearchResultBuilder.bind$default(searchResultBuilder, function1, item, position, root, null, null, null, 112, null);
    }
}
