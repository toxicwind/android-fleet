package com.lagradost.cloudstream3.ui.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import com.google.android.material.button.MaterialButton;
import com.lagradost.cloudstream3.databinding.SearchSuggestionFooterBinding;
import com.lagradost.cloudstream3.databinding.SearchSuggestionItemBinding;
import com.lagradost.cloudstream3.ui.BaseDiffCallback;
import com.lagradost.cloudstream3.ui.NoStateAdapter;
import com.lagradost.cloudstream3.ui.ViewHolderState;
import com.lagradost.cloudstream3.ui.settings.Globals;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchSuggestionAdapter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001b\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0004\b\u0007\u0010\bJ\"\u0010\r\u001a\u00020\u00062\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J&\u0010\u0017\u001a\u00020\u00062\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\nH\u0016J\u0016\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0016\u0010\u001c\u001a\u00020\u00062\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H\u0016R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\nX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u001d"}, d2 = {"Lcom/lagradost/cloudstream3/ui/search/SearchSuggestionAdapter;", "Lcom/lagradost/cloudstream3/ui/NoStateAdapter;", "", "clickCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/ui/search/SearchSuggestionCallback;", "", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "footers", "", "getFooters", "()I", "submitList", "list", "", "commitCallback", "Ljava/lang/Runnable;", "onCreateContent", "Lcom/lagradost/cloudstream3/ui/ViewHolderState;", "", "parent", "Landroid/view/ViewGroup;", "onBindContent", "holder", "item", "position", "onCreateFooter", "onBindFooter", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SearchSuggestionAdapter extends NoStateAdapter<String> {
    private final Function1<SearchSuggestionCallback, Unit> clickCallback;
    private final int footers;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SearchSuggestionAdapter(Function1<? super SearchSuggestionCallback, Unit> clickCallback) {
        super(new BaseDiffCallback(new Function2() { // from class: com.lagradost.cloudstream3.ui.search.SearchSuggestionAdapter$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(SearchSuggestionAdapter._init_$lambda$0((String) obj, (String) obj2));
            }
        }, null, 2, null));
        Intrinsics.checkNotNullParameter(clickCallback, "clickCallback");
        this.clickCallback = clickCallback;
        this.footers = 1;
    }

    static final boolean _init_$lambda$0(String a, String b) {
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        return Intrinsics.areEqual(a, b);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public int getFooters() {
        return this.footers;
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public void submitList(Collection<String> list, Runnable commitCallback) {
        super.submitList(list, commitCallback);
        if (getFooters() > 0) {
            notifyItemChanged(getItemCount() - 1);
        }
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public ViewHolderState<Object> onCreateContent(ViewGroup parent) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        SearchSuggestionItemBinding searchSuggestionItemBindingInflate = SearchSuggestionItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(searchSuggestionItemBindingInflate, "inflate(...)");
        return new ViewHolderState<>(searchSuggestionItemBindingInflate);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public void onBindContent(ViewHolderState<Object> holder, final String item, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        ViewBinding view = holder.getView();
        SearchSuggestionItemBinding searchSuggestionItemBinding = view instanceof SearchSuggestionItemBinding ? (SearchSuggestionItemBinding) view : null;
        if (searchSuggestionItemBinding == null) {
            return;
        }
        searchSuggestionItemBinding.suggestionText.setText(item);
        searchSuggestionItemBinding.suggestionItem.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.search.SearchSuggestionAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SearchSuggestionAdapter.onBindContent$lambda$0$0(this.f$0, item, view2);
            }
        });
        searchSuggestionItemBinding.suggestionFill.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.search.SearchSuggestionAdapter$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SearchSuggestionAdapter.onBindContent$lambda$0$1(this.f$0, item, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindContent$lambda$0$0(SearchSuggestionAdapter searchSuggestionAdapter, String str, View view) {
        searchSuggestionAdapter.clickCallback.invoke(new SearchSuggestionCallback(str, 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindContent$lambda$0$1(SearchSuggestionAdapter searchSuggestionAdapter, String str, View view) {
        searchSuggestionAdapter.clickCallback.invoke(new SearchSuggestionCallback(str, 1));
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public ViewHolderState<Object> onCreateFooter(ViewGroup parent) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        SearchSuggestionFooterBinding searchSuggestionFooterBindingInflate = SearchSuggestionFooterBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(searchSuggestionFooterBindingInflate, "inflate(...)");
        return new ViewHolderState<>(searchSuggestionFooterBindingInflate);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public void onBindFooter(ViewHolderState<Object> holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        ViewBinding view = holder.getView();
        SearchSuggestionFooterBinding searchSuggestionFooterBinding = view instanceof SearchSuggestionFooterBinding ? (SearchSuggestionFooterBinding) view : null;
        if (searchSuggestionFooterBinding == null) {
            return;
        }
        MaterialButton materialButton = searchSuggestionFooterBinding.clearSuggestionsButton;
        Intrinsics.checkNotNull(materialButton);
        materialButton.setVisibility(getImmutableCurrentList().isEmpty() ? 8 : 0);
        if (Globals.INSTANCE.isLayout(6)) {
            materialButton.setFocusable(true);
            materialButton.setFocusableInTouchMode(true);
        }
        materialButton.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.search.SearchSuggestionAdapter$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SearchSuggestionAdapter.onBindFooter$lambda$0$0(this.f$0, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindFooter$lambda$0$0(SearchSuggestionAdapter searchSuggestionAdapter, View view) {
        searchSuggestionAdapter.clickCallback.invoke(new SearchSuggestionCallback("", 2));
    }
}
