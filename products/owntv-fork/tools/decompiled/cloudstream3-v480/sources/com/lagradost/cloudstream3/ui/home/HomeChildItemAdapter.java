package com.lagradost.cloudstream3.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.cardview.widget.CardView;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.SearchResponse;
import com.lagradost.cloudstream3.databinding.HomeResultGridBinding;
import com.lagradost.cloudstream3.databinding.HomeResultGridExpandedBinding;
import com.lagradost.cloudstream3.ui.BaseAdapter;
import com.lagradost.cloudstream3.ui.BaseAdapterKt;
import com.lagradost.cloudstream3.ui.BaseDiffCallback;
import com.lagradost.cloudstream3.ui.ViewHolderState;
import com.lagradost.cloudstream3.ui.search.SearchClickCallback;
import com.lagradost.cloudstream3.ui.search.SearchResultBuilder;
import com.lagradost.cloudstream3.utils.UIHelper;
import java.util.WeakHashMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: HomeChildItemAdapter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u001f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0016\u0018\u0000 42\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u00014B;\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\u0004\b\f\u0010\rJ\b\u0010!\u001a\u00020\u000bH\u0002J\u0016\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00030+2\u0006\u0010,\u001a\u00020-H\u0016J\u001e\u0010.\u001a\u00020\u000b2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00030+2\u0006\u00100\u001a\u00020\u0003H\u0004J&\u00101\u001a\u00020\u000b2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00030+2\u0006\u00102\u001a\u00020\u00022\u0006\u00103\u001a\u00020\u0005H\u0016R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0012\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0012\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R&\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR$\u0010\u001f\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u0003@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u001b\"\u0004\b \u0010\u001dR\u001a\u0010\"\u001a\u00020\u0005X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001a\u0010'\u001a\u00020\u0005X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010$\"\u0004\b)\u0010&¨\u00065"}, d2 = {"Lcom/lagradost/cloudstream3/ui/home/HomeChildItemAdapter;", "Lcom/lagradost/cloudstream3/ui/BaseAdapter;", "Lcom/lagradost/cloudstream3/SearchResponse;", "", "id", "", "nextFocusUp", "nextFocusDown", "clickCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/ui/search/SearchClickCallback;", "", "<init>", "(ILjava/lang/Integer;Ljava/lang/Integer;Lkotlin/jvm/functions/Function1;)V", "getNextFocusUp", "()Ljava/lang/Integer;", "setNextFocusUp", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getNextFocusDown", "setNextFocusDown", "getClickCallback", "()Lkotlin/jvm/functions/Function1;", "setClickCallback", "(Lkotlin/jvm/functions/Function1;)V", "hasNext", "getHasNext", "()Z", "setHasNext", "(Z)V", ES6Iterator.VALUE_PROPERTY, "isHorizontal", "setHorizontal", "updateCachedPosterSize", "setWidth", "getSetWidth", "()I", "setSetWidth", "(I)V", "setHeight", "getSetHeight", "setSetHeight", "onCreateContent", "Lcom/lagradost/cloudstream3/ui/ViewHolderState;", "parent", "Landroid/view/ViewGroup;", "applyBinding", "holder", "isFirstItem", "onBindContent", "item", "position", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class HomeChildItemAdapter extends BaseAdapter<SearchResponse, Boolean> {
    private static int maxPosterSize;
    private static int minPosterSize;
    private Function1<? super SearchClickCallback, Unit> clickCallback;
    private boolean hasNext;
    private boolean isHorizontal;
    private Integer nextFocusDown;
    private Integer nextFocusUp;
    private int setHeight;
    private int setWidth;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Pair<WeakHashMap<Context, RecyclerView.RecycledViewPool>, Function1<RecyclerView.RecycledViewPool, Unit>> sharedPool = BaseAdapterKt.newSharedPool(new Function1() { // from class: com.lagradost.cloudstream3.ui.home.HomeChildItemAdapter$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return HomeChildItemAdapter.sharedPool$lambda$0((RecyclerView.RecycledViewPool) obj);
        }
    });

    public /* synthetic */ HomeChildItemAdapter(int i, Integer num, Integer num2, Function1 function1, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? null : num, (i2 & 4) != 0 ? null : num2, function1);
    }

    public final Integer getNextFocusUp() {
        return this.nextFocusUp;
    }

    public final void setNextFocusUp(Integer num) {
        this.nextFocusUp = num;
    }

    public final Integer getNextFocusDown() {
        return this.nextFocusDown;
    }

    public final void setNextFocusDown(Integer num) {
        this.nextFocusDown = num;
    }

    public final Function1<SearchClickCallback, Unit> getClickCallback() {
        return this.clickCallback;
    }

    public final void setClickCallback(Function1<? super SearchClickCallback, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.clickCallback = function1;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeChildItemAdapter(int i, Integer num, Integer num2, Function1<? super SearchClickCallback, Unit> clickCallback) {
        super(i, new BaseDiffCallback(new Function2() { // from class: com.lagradost.cloudstream3.ui.home.HomeChildItemAdapter$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(HomeChildItemAdapter._init_$lambda$0((SearchResponse) obj, (SearchResponse) obj2));
            }
        }, new Function2() { // from class: com.lagradost.cloudstream3.ui.home.HomeChildItemAdapter$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(HomeChildItemAdapter._init_$lambda$1((SearchResponse) obj, (SearchResponse) obj2));
            }
        }));
        Intrinsics.checkNotNullParameter(clickCallback, "clickCallback");
        this.nextFocusUp = num;
        this.nextFocusDown = num2;
        this.clickCallback = clickCallback;
        updateCachedPosterSize();
    }

    static final boolean _init_$lambda$0(SearchResponse a, SearchResponse b) {
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        return Intrinsics.areEqual(a.getUrl(), b.getUrl()) && Intrinsics.areEqual(a.getName(), b.getName());
    }

    static final boolean _init_$lambda$1(SearchResponse a, SearchResponse b) {
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        return Intrinsics.areEqual(a, b);
    }

    public final boolean getHasNext() {
        return this.hasNext;
    }

    public final void setHasNext(boolean z) {
        this.hasNext = z;
    }

    /* JADX INFO: renamed from: isHorizontal, reason: from getter */
    public final boolean getIsHorizontal() {
        return this.isHorizontal;
    }

    public final void setHorizontal(boolean z) {
        this.isHorizontal = z;
        updateCachedPosterSize();
    }

    private final void updateCachedPosterSize() {
        int i;
        int i2;
        boolean z = this.isHorizontal;
        if (!z) {
            i = minPosterSize;
        } else {
            i = maxPosterSize;
        }
        this.setWidth = i;
        if (!z) {
            i2 = maxPosterSize;
        } else {
            i2 = minPosterSize;
        }
        this.setHeight = i2;
    }

    protected final int getSetWidth() {
        return this.setWidth;
    }

    protected final void setSetWidth(int i) {
        this.setWidth = i;
    }

    protected final int getSetHeight() {
        return this.setHeight;
    }

    protected final void setSetHeight(int i) {
        this.setHeight = i;
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public ViewHolderState<Boolean> onCreateContent(ViewGroup parent) {
        HomeResultGridBinding homeResultGridBinding;
        Intrinsics.checkNotNullParameter(parent, "parent");
        boolean zIsBottomLayout = UIHelper.INSTANCE.isBottomLayout(parent.getContext());
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(parent.getContext());
        if (zIsBottomLayout) {
            HomeResultGridExpandedBinding homeResultGridExpandedBindingInflate = HomeResultGridExpandedBinding.inflate(layoutInflaterFrom, parent, false);
            Intrinsics.checkNotNullExpressionValue(homeResultGridExpandedBindingInflate, "inflate(...)");
            homeResultGridBinding = homeResultGridExpandedBindingInflate;
        } else {
            HomeResultGridBinding homeResultGridBindingInflate = HomeResultGridBinding.inflate(layoutInflaterFrom, parent, false);
            Intrinsics.checkNotNullExpressionValue(homeResultGridBindingInflate, "inflate(...)");
            homeResultGridBinding = homeResultGridBindingInflate;
        }
        return new HomeScrollViewHolderState(homeResultGridBinding);
    }

    /* JADX INFO: compiled from: HomeChildItemAdapter.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u00072\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u000f¢\u0006\u0002\u0010\u001aJ\u001e\u0010\u001b\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\u001f\u001a\u00020\u000fR:\u0010\u0004\u001a+\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\b\u000b0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0011\"\u0004\b\u0016\u0010\u0013¨\u0006 "}, d2 = {"Lcom/lagradost/cloudstream3/ui/home/HomeChildItemAdapter$Companion;", "", "<init>", "()V", "sharedPool", "Lkotlin/Pair;", "Ljava/util/WeakHashMap;", "Landroid/content/Context;", "Landroidx/recyclerview/widget/RecyclerView$RecycledViewPool;", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "getSharedPool", "()Lkotlin/Pair;", "minPosterSize", "", "getMinPosterSize", "()I", "setMinPosterSize", "(I)V", "maxPosterSize", "getMaxPosterSize", "setMaxPosterSize", "updatePosterSize", "context", ES6Iterator.VALUE_PROPERTY, "(Landroid/content/Context;Ljava/lang/Integer;)V", "updateLayoutParms", TtmlNode.TAG_LAYOUT, "Landroid/widget/FrameLayout;", "width", "height", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Pair<WeakHashMap<Context, RecyclerView.RecycledViewPool>, Function1<RecyclerView.RecycledViewPool, Unit>> getSharedPool() {
            return HomeChildItemAdapter.sharedPool;
        }

        public final int getMinPosterSize() {
            return HomeChildItemAdapter.minPosterSize;
        }

        public final void setMinPosterSize(int i) {
            HomeChildItemAdapter.minPosterSize = i;
        }

        public final int getMaxPosterSize() {
            return HomeChildItemAdapter.maxPosterSize;
        }

        public final void setMaxPosterSize(int i) {
            HomeChildItemAdapter.maxPosterSize = i;
        }

        public static /* synthetic */ void updatePosterSize$default(Companion companion, Context context, Integer num, int i, Object obj) {
            if ((i & 2) != 0) {
                num = null;
            }
            companion.updatePosterSize(context, num);
        }

        public final void updatePosterSize(Context context, Integer value) {
            int iIntValue;
            Intrinsics.checkNotNullParameter(context, "context");
            if (value != null) {
                iIntValue = value.intValue();
            } else {
                SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                Integer numValueOf = defaultSharedPreferences != null ? Integer.valueOf(defaultSharedPreferences.getInt(context.getString(R.string.poster_size_key), 0)) : null;
                iIntValue = numValueOf != null ? numValueOf.intValue() : 0;
            }
            float f = (iIntValue * 0.1f) + 1.0f;
            setMinPosterSize((int) (UIHelper.INSTANCE.getToPx(114) * f));
            setMaxPosterSize((int) (UIHelper.INSTANCE.getToPx(180) * f));
        }

        public final void updateLayoutParms(FrameLayout layout, int width, int height) {
            Intrinsics.checkNotNullParameter(layout, "layout");
            ViewGroup.LayoutParams layoutParams = layout.getLayoutParams();
            if (layoutParams.height == height && layoutParams.width == width) {
                return;
            }
            layoutParams.width = width;
            layoutParams.height = height;
            layout.setLayoutParams(layoutParams);
        }
    }

    static final Unit sharedPool$lambda$0(RecyclerView.RecycledViewPool newSharedPool) {
        Intrinsics.checkNotNullParameter(newSharedPool, "$this$newSharedPool");
        newSharedPool.setMaxRecycledViews(268435456, 20);
        return Unit.INSTANCE;
    }

    protected final void applyBinding(ViewHolderState<Boolean> holder, boolean isFirstItem) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        ViewBinding view = holder.getView();
        if (view instanceof HomeResultGridBinding) {
            Companion companion = INSTANCE;
            CardView backgroundCard = ((HomeResultGridBinding) view).backgroundCard;
            Intrinsics.checkNotNullExpressionValue(backgroundCard, "backgroundCard");
            companion.updateLayoutParms(backgroundCard, this.setWidth, this.setHeight);
            return;
        }
        if (view instanceof HomeResultGridExpandedBinding) {
            Companion companion2 = INSTANCE;
            HomeResultGridExpandedBinding homeResultGridExpandedBinding = (HomeResultGridExpandedBinding) view;
            CardView backgroundCard2 = homeResultGridExpandedBinding.backgroundCard;
            Intrinsics.checkNotNullExpressionValue(backgroundCard2, "backgroundCard");
            companion2.updateLayoutParms(backgroundCard2, this.setWidth, this.setHeight);
            if (isFirstItem) {
                homeResultGridExpandedBinding.backgroundCard.setNextFocusLeftId(R.id.nav_rail_view);
            }
        }
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public void onBindContent(final ViewHolderState<Boolean> holder, SearchResponse item, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        applyBinding(holder, position == 0);
        SearchResultBuilder searchResultBuilder = SearchResultBuilder.INSTANCE;
        Function1 function1 = new Function1() { // from class: com.lagradost.cloudstream3.ui.home.HomeChildItemAdapter$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HomeChildItemAdapter.onBindContent$lambda$0(holder, this, (SearchClickCallback) obj);
            }
        };
        View itemView = holder.itemView;
        Intrinsics.checkNotNullExpressionValue(itemView, "itemView");
        SearchResultBuilder.bind$default(searchResultBuilder, function1, item, position, itemView, this.nextFocusUp, this.nextFocusDown, null, 64, null);
        holder.itemView.setTag(Integer.valueOf(position));
    }

    static final Unit onBindContent$lambda$0(ViewHolderState viewHolderState, HomeChildItemAdapter homeChildItemAdapter, SearchClickCallback click) {
        Intrinsics.checkNotNullParameter(click, "click");
        if (click.getAction() == 0) {
            HomeScrollViewHolderState homeScrollViewHolderState = viewHolderState instanceof HomeScrollViewHolderState ? (HomeScrollViewHolderState) viewHolderState : null;
            if (homeScrollViewHolderState != null) {
                homeScrollViewHolderState.setWasFocused(true);
            }
        }
        homeChildItemAdapter.clickCallback.invoke(click);
        return Unit.INSTANCE;
    }
}
