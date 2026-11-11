package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.appcompat.widget.SearchView;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.ui.AutofitRecyclerView;

/* JADX INFO: loaded from: classes6.dex */
public final class FragmentSearchTvBinding implements ViewBinding {
    public final SearchView mainSearch;
    private final FrameLayout rootView;
    public final AutofitRecyclerView searchAutofitResults;
    public final ImageView searchFilter;
    public final RecyclerView searchHistoryRecycler;
    public final ContentLoadingProgressBar searchLoadingBar;
    public final RecyclerView searchMasterRecycler;
    public final FrameLayout searchRoot;
    public final RecyclerView searchSuggestionsRecycler;
    public final TvtypesChipsScrollBinding tvtypesChipsScroll;
    public final ImageView voiceSearch;

    private FragmentSearchTvBinding(FrameLayout frameLayout, SearchView searchView, AutofitRecyclerView autofitRecyclerView, ImageView imageView, RecyclerView recyclerView, ContentLoadingProgressBar contentLoadingProgressBar, RecyclerView recyclerView2, FrameLayout frameLayout2, RecyclerView recyclerView3, TvtypesChipsScrollBinding tvtypesChipsScrollBinding, ImageView imageView2) {
        this.rootView = frameLayout;
        this.mainSearch = searchView;
        this.searchAutofitResults = autofitRecyclerView;
        this.searchFilter = imageView;
        this.searchHistoryRecycler = recyclerView;
        this.searchLoadingBar = contentLoadingProgressBar;
        this.searchMasterRecycler = recyclerView2;
        this.searchRoot = frameLayout2;
        this.searchSuggestionsRecycler = recyclerView3;
        this.tvtypesChipsScroll = tvtypesChipsScrollBinding;
        this.voiceSearch = imageView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static FragmentSearchTvBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentSearchTvBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_search_tv, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentSearchTvBinding bind(View view) {
        View viewFindChildViewById;
        int i = R.id.main_search;
        SearchView searchView = (SearchView) ViewBindings.findChildViewById(view, i);
        if (searchView != null) {
            i = R.id.search_autofit_results;
            AutofitRecyclerView autofitRecyclerView = (AutofitRecyclerView) ViewBindings.findChildViewById(view, i);
            if (autofitRecyclerView != null) {
                i = R.id.search_filter;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                if (imageView != null) {
                    i = R.id.search_history_recycler;
                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                    if (recyclerView != null) {
                        i = R.id.search_loading_bar;
                        ContentLoadingProgressBar contentLoadingProgressBar = (ContentLoadingProgressBar) ViewBindings.findChildViewById(view, i);
                        if (contentLoadingProgressBar != null) {
                            i = R.id.search_master_recycler;
                            RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(view, i);
                            if (recyclerView2 != null) {
                                FrameLayout frameLayout = (FrameLayout) view;
                                i = R.id.search_suggestions_recycler;
                                RecyclerView recyclerView3 = (RecyclerView) ViewBindings.findChildViewById(view, i);
                                if (recyclerView3 != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = R.id.tvtypes_chips_scroll))) != null) {
                                    TvtypesChipsScrollBinding tvtypesChipsScrollBindingBind = TvtypesChipsScrollBinding.bind(viewFindChildViewById);
                                    i = R.id.voice_search;
                                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
                                    if (imageView2 != null) {
                                        return new FragmentSearchTvBinding(frameLayout, searchView, autofitRecyclerView, imageView, recyclerView, contentLoadingProgressBar, recyclerView2, frameLayout, recyclerView3, tvtypesChipsScrollBindingBind, imageView2);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
