package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.widget.SearchView;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.ui.AutofitRecyclerView;

/* JADX INFO: loaded from: classes6.dex */
public final class QuickSearchBinding implements ViewBinding {
    public final SearchView quickSearch;
    public final AutofitRecyclerView quickSearchAutofitResults;
    public final ImageView quickSearchBack;
    public final ContentLoadingProgressBar quickSearchLoadingBar;
    public final RecyclerView quickSearchMasterRecycler;
    public final LinearLayout quickSearchRoot;
    private final LinearLayout rootView;

    private QuickSearchBinding(LinearLayout linearLayout, SearchView searchView, AutofitRecyclerView autofitRecyclerView, ImageView imageView, ContentLoadingProgressBar contentLoadingProgressBar, RecyclerView recyclerView, LinearLayout linearLayout2) {
        this.rootView = linearLayout;
        this.quickSearch = searchView;
        this.quickSearchAutofitResults = autofitRecyclerView;
        this.quickSearchBack = imageView;
        this.quickSearchLoadingBar = contentLoadingProgressBar;
        this.quickSearchMasterRecycler = recyclerView;
        this.quickSearchRoot = linearLayout2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static QuickSearchBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static QuickSearchBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.quick_search, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static QuickSearchBinding bind(View view) {
        int i = R.id.quick_search;
        SearchView searchView = (SearchView) ViewBindings.findChildViewById(view, i);
        if (searchView != null) {
            i = R.id.quick_search_autofit_results;
            AutofitRecyclerView autofitRecyclerView = (AutofitRecyclerView) ViewBindings.findChildViewById(view, i);
            if (autofitRecyclerView != null) {
                i = R.id.quick_search_back;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                if (imageView != null) {
                    i = R.id.quick_search_loading_bar;
                    ContentLoadingProgressBar contentLoadingProgressBar = (ContentLoadingProgressBar) ViewBindings.findChildViewById(view, i);
                    if (contentLoadingProgressBar != null) {
                        i = R.id.quick_search_master_recycler;
                        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                        if (recyclerView != null) {
                            LinearLayout linearLayout = (LinearLayout) view;
                            return new QuickSearchBinding(linearLayout, searchView, autofitRecyclerView, imageView, contentLoadingProgressBar, recyclerView, linearLayout);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
