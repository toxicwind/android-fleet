package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import com.google.android.material.button.MaterialButton;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class SearchSuggestionFooterBinding implements ViewBinding {
    public final MaterialButton clearSuggestionsButton;
    private final MaterialButton rootView;

    private SearchSuggestionFooterBinding(MaterialButton materialButton, MaterialButton materialButton2) {
        this.rootView = materialButton;
        this.clearSuggestionsButton = materialButton2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public MaterialButton getRoot() {
        return this.rootView;
    }

    public static SearchSuggestionFooterBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static SearchSuggestionFooterBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.search_suggestion_footer, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static SearchSuggestionFooterBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        MaterialButton materialButton = (MaterialButton) view;
        return new SearchSuggestionFooterBinding(materialButton, materialButton);
    }
}
