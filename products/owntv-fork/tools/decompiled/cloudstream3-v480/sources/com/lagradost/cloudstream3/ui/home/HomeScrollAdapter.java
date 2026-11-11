package com.lagradost.cloudstream3.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.lagradost.cloudstream3.LoadResponse;
import com.lagradost.cloudstream3.databinding.HomeScrollViewBinding;
import com.lagradost.cloudstream3.databinding.HomeScrollViewTvBinding;
import com.lagradost.cloudstream3.ui.BaseAdapter;
import com.lagradost.cloudstream3.ui.BaseDiffCallback;
import com.lagradost.cloudstream3.ui.NoStateAdapter;
import com.lagradost.cloudstream3.ui.ViewHolderState;
import com.lagradost.cloudstream3.ui.result.ResultFragment;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import com.lagradost.cloudstream3.utils.ImageLoader;
import com.lagradost.cloudstream3.utils.PercentageCropImageView;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HomeScrollAdapter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B'\u0012\u001e\u0010\u0003\u001a\u001a\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u0004¢\u0006\u0004\b\b\u0010\tJ\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0016\u0010\u0017\u001a\u00020\u00072\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H\u0016J&\u0010\u0019\u001a\u00020\u00072\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0006H\u0016R)\u0010\u0003\u001a\u001a\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u001c"}, d2 = {"Lcom/lagradost/cloudstream3/ui/home/HomeScrollAdapter;", "Lcom/lagradost/cloudstream3/ui/NoStateAdapter;", "Lcom/lagradost/cloudstream3/LoadResponse;", "callback", "Lkotlin/Function3;", "Landroid/view/View;", "", "", "<init>", "(Lkotlin/jvm/functions/Function3;)V", "getCallback", "()Lkotlin/jvm/functions/Function3;", "hasMoreItems", "", "getHasMoreItems", "()Z", "setHasMoreItems", "(Z)V", "onCreateContent", "Lcom/lagradost/cloudstream3/ui/ViewHolderState;", "", "parent", "Landroid/view/ViewGroup;", "onClearView", "holder", "onBindContent", "item", "position", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class HomeScrollAdapter extends NoStateAdapter<LoadResponse> {
    private final Function3<View, Integer, LoadResponse, Unit> callback;
    private boolean hasMoreItems;

    public final Function3<View, Integer, LoadResponse, Unit> getCallback() {
        return this.callback;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public HomeScrollAdapter(Function3<? super View, ? super Integer, ? super LoadResponse, Unit> callback) {
        super(new BaseDiffCallback(new Function2() { // from class: com.lagradost.cloudstream3.ui.home.HomeScrollAdapter$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(HomeScrollAdapter._init_$lambda$0((LoadResponse) obj, (LoadResponse) obj2));
            }
        }, null, 2, null));
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.callback = callback;
    }

    static final boolean _init_$lambda$0(LoadResponse a, LoadResponse b) {
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        return Intrinsics.areEqual(a.getUniqueUrl(), b.getUniqueUrl()) && Intrinsics.areEqual(a.getName(), b.getName());
    }

    public final boolean getHasMoreItems() {
        return this.hasMoreItems;
    }

    public final void setHasMoreItems(boolean z) {
        this.hasMoreItems = z;
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public ViewHolderState<Object> onCreateContent(ViewGroup parent) {
        HomeScrollViewBinding homeScrollViewBinding;
        Intrinsics.checkNotNullParameter(parent, "parent");
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(parent.getContext());
        if (Globals.INSTANCE.isLayout(6)) {
            HomeScrollViewTvBinding homeScrollViewTvBindingInflate = HomeScrollViewTvBinding.inflate(layoutInflaterFrom, parent, false);
            Intrinsics.checkNotNull(homeScrollViewTvBindingInflate);
            homeScrollViewBinding = homeScrollViewTvBindingInflate;
        } else {
            HomeScrollViewBinding homeScrollViewBindingInflate = HomeScrollViewBinding.inflate(layoutInflaterFrom, parent, false);
            Intrinsics.checkNotNull(homeScrollViewBindingInflate);
            homeScrollViewBinding = homeScrollViewBindingInflate;
        }
        return new ViewHolderState<>(homeScrollViewBinding);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public void onClearView(ViewHolderState<Object> holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        ViewBinding view = holder.getView();
        if (view instanceof HomeScrollViewBinding) {
            BaseAdapter.INSTANCE.clearImage(((HomeScrollViewBinding) view).homeScrollPreview);
        } else if (view instanceof HomeScrollViewTvBinding) {
            BaseAdapter.INSTANCE.clearImage(((HomeScrollViewTvBinding) view).homeScrollPreview);
        }
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public void onBindContent(ViewHolderState<Object> holder, final LoadResponse item, final int position) {
        String strJoinToString$default;
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        ViewBinding view = holder.getView();
        String backgroundPosterUrl = item.getBackgroundPosterUrl();
        if (backgroundPosterUrl == null) {
            backgroundPosterUrl = item.getPosterUrl();
        }
        String str = backgroundPosterUrl;
        if (view instanceof HomeScrollViewBinding) {
            ImageLoader imageLoader = ImageLoader.INSTANCE;
            HomeScrollViewBinding homeScrollViewBinding = (HomeScrollViewBinding) view;
            ImageView homeScrollPreview = homeScrollViewBinding.homeScrollPreview;
            Intrinsics.checkNotNullExpressionValue(homeScrollPreview, "homeScrollPreview");
            ImageLoader.loadImage$default(imageLoader, homeScrollPreview, str, (Map) null, (Function1) null, 6, (Object) null);
            TextView textView = homeScrollViewBinding.homeScrollPreviewTags;
            List<String> tags = item.getTags();
            if (tags == null || (strJoinToString$default = CollectionsKt.joinToString$default(tags, " • ", null, null, 0, null, null, 62, null)) == null) {
                strJoinToString$default = "";
            }
            textView.setText(strJoinToString$default);
            Intrinsics.checkNotNull(textView);
            TextView textView2 = textView;
            List<String> tags2 = item.getTags();
            textView2.setVisibility(tags2 == null || tags2.isEmpty() ? 8 : 0);
            textView.setMaxLines(2);
            homeScrollViewBinding.homeScrollPreviewTitle.setText(AppContextUtils.INSTANCE.html(item.getName()));
            ResultFragment resultFragment = ResultFragment.INSTANCE;
            String logoUrl = item.getLogoUrl();
            Map<String, String> posterHeaders = item.getPosterHeaders();
            TextView homeScrollPreviewTitle = homeScrollViewBinding.homeScrollPreviewTitle;
            Intrinsics.checkNotNullExpressionValue(homeScrollPreviewTitle, "homeScrollPreviewTitle");
            ImageView homePreviewLogo = homeScrollViewBinding.homePreviewLogo;
            Intrinsics.checkNotNullExpressionValue(homePreviewLogo, "homePreviewLogo");
            resultFragment.bindLogo(logoUrl, posterHeaders, homePreviewLogo, homeScrollPreviewTitle);
            return;
        }
        if (view instanceof HomeScrollViewTvBinding) {
            HomeScrollViewTvBinding homeScrollViewTvBinding = (HomeScrollViewTvBinding) view;
            homeScrollViewTvBinding.homeScrollPreview.setFocusable(false);
            homeScrollViewTvBinding.homeScrollPreview.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.home.HomeScrollAdapter$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    HomeScrollAdapter.onBindContent$lambda$1(this.f$0, position, item, view2);
                }
            });
            ImageLoader imageLoader2 = ImageLoader.INSTANCE;
            PercentageCropImageView homeScrollPreview2 = homeScrollViewTvBinding.homeScrollPreview;
            Intrinsics.checkNotNullExpressionValue(homeScrollPreview2, "homeScrollPreview");
            ImageLoader.loadImage$default(imageLoader2, homeScrollPreview2, str, (Map) null, (Function1) null, 6, (Object) null);
        }
    }

    static final void onBindContent$lambda$1(HomeScrollAdapter homeScrollAdapter, int i, LoadResponse loadResponse, View view) {
        Function3<View, Integer, LoadResponse, Unit> function3 = homeScrollAdapter.callback;
        if (view == null) {
            return;
        }
        function3.invoke(view, Integer.valueOf(i), loadResponse);
    }
}
