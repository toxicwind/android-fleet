package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.SearchView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class FragmentLibraryBinding implements ViewBinding {
    public final TextView emptyListTextview;
    public final GridView gridview;
    public final LinearLayout libraryLoadingOverlay;
    public final ShimmerFrameLayout libraryLoadingShimmer;
    public final ExtendedFloatingActionButton libraryRandom;
    public final ImageView libraryRandomButtonTv;
    public final CoordinatorLayout libraryRoot;
    public final ImageView librarySort;
    public final TabLayout libraryTabLayout;
    public final ImageView listSelector;
    public final SearchView mainSearch;
    public final ImageView providerSelector;
    private final CoordinatorLayout rootView;
    public final AppBarLayout searchBar;
    public final ExtendedFloatingActionButton sortFab;
    public final ViewPager2 viewpager;

    private FragmentLibraryBinding(CoordinatorLayout coordinatorLayout, TextView textView, GridView gridView, LinearLayout linearLayout, ShimmerFrameLayout shimmerFrameLayout, ExtendedFloatingActionButton extendedFloatingActionButton, ImageView imageView, CoordinatorLayout coordinatorLayout2, ImageView imageView2, TabLayout tabLayout, ImageView imageView3, SearchView searchView, ImageView imageView4, AppBarLayout appBarLayout, ExtendedFloatingActionButton extendedFloatingActionButton2, ViewPager2 viewPager2) {
        this.rootView = coordinatorLayout;
        this.emptyListTextview = textView;
        this.gridview = gridView;
        this.libraryLoadingOverlay = linearLayout;
        this.libraryLoadingShimmer = shimmerFrameLayout;
        this.libraryRandom = extendedFloatingActionButton;
        this.libraryRandomButtonTv = imageView;
        this.libraryRoot = coordinatorLayout2;
        this.librarySort = imageView2;
        this.libraryTabLayout = tabLayout;
        this.listSelector = imageView3;
        this.mainSearch = searchView;
        this.providerSelector = imageView4;
        this.searchBar = appBarLayout;
        this.sortFab = extendedFloatingActionButton2;
        this.viewpager = viewPager2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CoordinatorLayout getRoot() {
        return this.rootView;
    }

    public static FragmentLibraryBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentLibraryBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_library, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentLibraryBinding bind(View view) {
        int i = R.id.empty_list_textview;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
        if (textView != null) {
            i = R.id.gridview;
            GridView gridView = (GridView) ViewBindings.findChildViewById(view, i);
            if (gridView != null) {
                i = R.id.library_loading_overlay;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
                if (linearLayout != null) {
                    i = R.id.library_loading_shimmer;
                    ShimmerFrameLayout shimmerFrameLayout = (ShimmerFrameLayout) ViewBindings.findChildViewById(view, i);
                    if (shimmerFrameLayout != null) {
                        i = R.id.library_random;
                        ExtendedFloatingActionButton extendedFloatingActionButton = (ExtendedFloatingActionButton) ViewBindings.findChildViewById(view, i);
                        if (extendedFloatingActionButton != null) {
                            i = R.id.library_random_button_tv;
                            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                            if (imageView != null) {
                                CoordinatorLayout coordinatorLayout = (CoordinatorLayout) view;
                                i = R.id.library_sort;
                                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
                                if (imageView2 != null) {
                                    i = R.id.library_tab_layout;
                                    TabLayout tabLayout = (TabLayout) ViewBindings.findChildViewById(view, i);
                                    if (tabLayout != null) {
                                        i = R.id.list_selector;
                                        ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, i);
                                        if (imageView3 != null) {
                                            i = R.id.main_search;
                                            SearchView searchView = (SearchView) ViewBindings.findChildViewById(view, i);
                                            if (searchView != null) {
                                                i = R.id.provider_selector;
                                                ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                if (imageView4 != null) {
                                                    i = R.id.search_bar;
                                                    AppBarLayout appBarLayout = (AppBarLayout) ViewBindings.findChildViewById(view, i);
                                                    if (appBarLayout != null) {
                                                        i = R.id.sort_fab;
                                                        ExtendedFloatingActionButton extendedFloatingActionButton2 = (ExtendedFloatingActionButton) ViewBindings.findChildViewById(view, i);
                                                        if (extendedFloatingActionButton2 != null) {
                                                            i = R.id.viewpager;
                                                            ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(view, i);
                                                            if (viewPager2 != null) {
                                                                return new FragmentLibraryBinding(coordinatorLayout, textView, gridView, linearLayout, shimmerFrameLayout, extendedFloatingActionButton, imageView, coordinatorLayout, imageView2, tabLayout, imageView3, searchView, imageView4, appBarLayout, extendedFloatingActionButton2, viewPager2);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
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
