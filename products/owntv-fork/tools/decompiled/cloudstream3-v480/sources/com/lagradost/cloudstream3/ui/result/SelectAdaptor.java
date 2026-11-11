package com.lagradost.cloudstream3.ui.result;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.google.android.material.button.MaterialButton;
import com.lagradost.cloudstream3.databinding.ResultSelectionBinding;
import com.lagradost.cloudstream3.ui.BaseDiffCallback;
import com.lagradost.cloudstream3.ui.NoStateAdapter;
import com.lagradost.cloudstream3.ui.ViewHolderState;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.utils.TextUtilKt;
import com.lagradost.cloudstream3.utils.UiText;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SelectAdaptor.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u001a\u0012\u0016\u0012\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u00020\u00040\u0002j\u0002`\u00050\u0001B\u001b\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nJ\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J8\u0010\u0013\u001a\u00020\b2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\u00102\u0018\u0010\u0015\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u00020\u00040\u0002j\u0002`\u00052\u0006\u0010\u0016\u001a\u00020\u000eH\u0016J\u0016\u0010\u0017\u001a\u00020\b2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\u0010H\u0016J\u0018\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u000e2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bR\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/lagradost/cloudstream3/ui/result/SelectAdaptor;", "Lcom/lagradost/cloudstream3/ui/NoStateAdapter;", "Lkotlin/Pair;", "Lcom/lagradost/cloudstream3/utils/UiText;", "", "Lcom/lagradost/cloudstream3/ui/result/SelectData;", "callback", "Lkotlin/Function1;", "", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "getCallback", "()Lkotlin/jvm/functions/Function1;", "selectedIndex", "", "onCreateContent", "Lcom/lagradost/cloudstream3/ui/ViewHolderState;", "parent", "Landroid/view/ViewGroup;", "onBindContent", "holder", "item", "position", "onViewDetachedFromWindow", "select", "newIndex", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SelectAdaptor extends NoStateAdapter<Pair<? extends UiText, ? extends Object>> {
    private final Function1<Object, Unit> callback;
    private int selectedIndex;

    public final Function1<Object, Unit> getCallback() {
        return this.callback;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SelectAdaptor(Function1<Object, Unit> callback) {
        super(new BaseDiffCallback(new Function2() { // from class: com.lagradost.cloudstream3.ui.result.SelectAdaptor$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(SelectAdaptor._init_$lambda$0((Pair) obj, (Pair) obj2));
            }
        }, new Function2() { // from class: com.lagradost.cloudstream3.ui.result.SelectAdaptor$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(SelectAdaptor._init_$lambda$1((Pair) obj, (Pair) obj2));
            }
        }));
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.callback = callback;
        this.selectedIndex = -1;
    }

    static final boolean _init_$lambda$0(Pair a, Pair b) {
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        return Intrinsics.areEqual(a.getSecond(), b.getSecond());
    }

    static final boolean _init_$lambda$1(Pair a, Pair b) {
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        return Intrinsics.areEqual(a, b);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public ViewHolderState<Object> onCreateContent(ViewGroup parent) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ResultSelectionBinding resultSelectionBindingInflate = ResultSelectionBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(resultSelectionBindingInflate, "inflate(...)");
        return new ViewHolderState<>(resultSelectionBindingInflate);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public void onBindContent(ViewHolderState<Object> holder, final Pair<? extends UiText, ? extends Object> item, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        ViewBinding view = holder.getView();
        if (view instanceof ResultSelectionBinding) {
            MaterialButton root = ((ResultSelectionBinding) view).getRoot();
            if (Globals.INSTANCE.isLayout(2)) {
                root.setFocusable(true);
                root.setFocusableInTouchMode(true);
            }
            root.setSelected(position == this.selectedIndex);
            TextUtilKt.setText(root, item.getFirst());
            root.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.SelectAdaptor$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    SelectAdaptor.onBindContent$lambda$0$0(this.f$0, item, view2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindContent$lambda$0$0(SelectAdaptor selectAdaptor, Pair pair, View view) {
        selectAdaptor.callback.invoke(pair.getSecond());
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewDetachedFromWindow(ViewHolderState<Object> holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (holder.itemView.hasFocus()) {
            holder.itemView.clearFocus();
        }
    }

    public final void select(int newIndex, RecyclerView recyclerView) {
        int i;
        if (recyclerView == null || newIndex == (i = this.selectedIndex)) {
            return;
        }
        this.selectedIndex = newIndex;
        notifyItemChanged(newIndex);
        notifyItemChanged(i);
    }
}
