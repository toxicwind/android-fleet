package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import com.google.android.material.button.MaterialButton;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class SearchHistoryFooterBinding implements ViewBinding {
    private final MaterialButton rootView;
    public final MaterialButton searchClearCallHistory;

    private SearchHistoryFooterBinding(MaterialButton materialButton, MaterialButton materialButton2) {
        this.rootView = materialButton;
        this.searchClearCallHistory = materialButton2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public MaterialButton getRoot() {
        return this.rootView;
    }

    public static SearchHistoryFooterBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static SearchHistoryFooterBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.search_history_footer, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static SearchHistoryFooterBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        MaterialButton materialButton = (MaterialButton) view;
        return new SearchHistoryFooterBinding(materialButton, materialButton);
    }
}
