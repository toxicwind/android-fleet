package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.ui.AutofitRecyclerView;

/* JADX INFO: loaded from: classes6.dex */
public final class ResultRecommendationsBinding implements ViewBinding {
    public final MaterialButton resultRecommendationsFilterButton;
    public final AutofitRecyclerView resultRecommendationsList;
    private final NestedScrollView rootView;

    private ResultRecommendationsBinding(NestedScrollView nestedScrollView, MaterialButton materialButton, AutofitRecyclerView autofitRecyclerView) {
        this.rootView = nestedScrollView;
        this.resultRecommendationsFilterButton = materialButton;
        this.resultRecommendationsList = autofitRecyclerView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public NestedScrollView getRoot() {
        return this.rootView;
    }

    public static ResultRecommendationsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ResultRecommendationsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.result_recommendations, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ResultRecommendationsBinding bind(View view) {
        int i = R.id.result_recommendations_filter_button;
        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
        if (materialButton != null) {
            i = R.id.result_recommendations_list;
            AutofitRecyclerView autofitRecyclerView = (AutofitRecyclerView) ViewBindings.findChildViewById(view, i);
            if (autofitRecyclerView != null) {
                return new ResultRecommendationsBinding((NestedScrollView) view, materialButton, autofitRecyclerView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
