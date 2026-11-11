package com.lagradost.cloudstream3.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.databinding.HomeRemoveGridBinding;
import com.lagradost.cloudstream3.databinding.HomeRemoveGridExpandedBinding;
import com.lagradost.cloudstream3.ui.BaseAdapter;
import com.lagradost.cloudstream3.ui.ViewHolderState;
import com.lagradost.cloudstream3.ui.search.SearchClickCallback;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.utils.UIHelper;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HomeChildItemAdapter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001BG\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0004\b\u000b\u0010\fJ\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0016\u0010\u0015\u001a\u00020\b2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0016J\u0016\u0010\u0017\u001a\u00020\b2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0016R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0018"}, d2 = {"Lcom/lagradost/cloudstream3/ui/home/ResumeItemAdapter;", "Lcom/lagradost/cloudstream3/ui/home/HomeChildItemAdapter;", "nextFocusUp", "", "nextFocusDown", "clickCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/ui/search/SearchClickCallback;", "", "removeCallback", "Landroid/view/View;", "<init>", "(Ljava/lang/Integer;Ljava/lang/Integer;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "footers", "getFooters", "()I", "onCreateFooter", "Lcom/lagradost/cloudstream3/ui/ViewHolderState;", "", "parent", "Landroid/view/ViewGroup;", "onClearView", "holder", "onBindFooter", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ResumeItemAdapter extends HomeChildItemAdapter {
    private final int footers;
    private final Function1<View, Unit> removeCallback;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ResumeItemAdapter(Integer num, Integer num2, Function1<? super SearchClickCallback, Unit> clickCallback, Function1<? super View, Unit> removeCallback) {
        super(56317538, num, num2, clickCallback);
        Intrinsics.checkNotNullParameter(clickCallback, "clickCallback");
        Intrinsics.checkNotNullParameter(removeCallback, "removeCallback");
        this.removeCallback = removeCallback;
        this.footers = Globals.INSTANCE.isLayout(6) ? 1 : 0;
    }

    public /* synthetic */ ResumeItemAdapter(Integer num, Integer num2, Function1 function1, Function1 function2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : num2, function1, function2);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public int getFooters() {
        return this.footers;
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public ViewHolderState<Boolean> onCreateFooter(ViewGroup parent) {
        HomeRemoveGridBinding homeRemoveGridBinding;
        Intrinsics.checkNotNullParameter(parent, "parent");
        boolean zIsBottomLayout = UIHelper.INSTANCE.isBottomLayout(parent.getContext());
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(parent.getContext());
        if (zIsBottomLayout) {
            HomeRemoveGridExpandedBinding homeRemoveGridExpandedBindingInflate = HomeRemoveGridExpandedBinding.inflate(layoutInflaterFrom, parent, false);
            Intrinsics.checkNotNullExpressionValue(homeRemoveGridExpandedBindingInflate, "inflate(...)");
            homeRemoveGridBinding = homeRemoveGridExpandedBindingInflate;
        } else {
            HomeRemoveGridBinding homeRemoveGridBindingInflate = HomeRemoveGridBinding.inflate(layoutInflaterFrom, parent, false);
            Intrinsics.checkNotNullExpressionValue(homeRemoveGridBindingInflate, "inflate(...)");
            homeRemoveGridBinding = homeRemoveGridBindingInflate;
        }
        return new HomeScrollViewHolderState(homeRemoveGridBinding);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public void onClearView(ViewHolderState<Boolean> holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        BaseAdapter.INSTANCE.clearImage((ImageView) holder.getView().getRoot().findViewById(R.id.imageView));
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public void onBindFooter(ViewHolderState<Boolean> holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        applyBinding(holder, false);
        ViewBinding view = holder.getView();
        if (view instanceof HomeRemoveGridBinding) {
            HomeChildItemAdapter.Companion companion = HomeChildItemAdapter.INSTANCE;
            CardView backgroundCard = ((HomeRemoveGridBinding) view).backgroundCard;
            Intrinsics.checkNotNullExpressionValue(backgroundCard, "backgroundCard");
            companion.updateLayoutParms(backgroundCard, getSetWidth(), getSetHeight());
        } else if (view instanceof HomeRemoveGridExpandedBinding) {
            HomeChildItemAdapter.Companion companion2 = HomeChildItemAdapter.INSTANCE;
            CardView backgroundCard2 = ((HomeRemoveGridExpandedBinding) view).backgroundCard;
            Intrinsics.checkNotNullExpressionValue(backgroundCard2, "backgroundCard");
            companion2.updateLayoutParms(backgroundCard2, getSetWidth(), getSetHeight());
        }
        View view2 = holder.itemView;
        if (Globals.INSTANCE.isLayout(2)) {
            view2.setFocusableInTouchMode(true);
            view2.setFocusable(true);
        }
        Integer nextFocusUp = getNextFocusUp();
        if (nextFocusUp != null) {
            view2.setNextFocusUpId(nextFocusUp.intValue());
        }
        Integer nextFocusDown = getNextFocusDown();
        if (nextFocusDown != null) {
            view2.setNextFocusDownId(nextFocusDown.intValue());
        }
        view2.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.home.ResumeItemAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view3) {
                ResumeItemAdapter.onBindFooter$lambda$0$2(this.f$0, view3);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindFooter$lambda$0$2(ResumeItemAdapter resumeItemAdapter, View view) {
        Function1<View, Unit> function1 = resumeItemAdapter.removeCallback;
        if (view == null) {
            return;
        }
        function1.invoke(view);
    }
}
