package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class FragmentHomeTvBinding implements ViewBinding {
    public final ExtendedFloatingActionButton homeApiFab;
    public final LinearLayout homeApiHolder;
    public final MaterialButton homeChangeApi;
    public final FrameLayout homeLoading;
    public final LinearLayout homeLoadingError;
    public final ShimmerFrameLayout homeLoadingShimmer;
    public final RecyclerView homeMasterRecycler;
    public final ImageView homePreviewReloadProvider;
    public final ImageView homePreviewSearchButton;
    public final ExtendedFloatingActionButton homeRandom;
    public final ImageView homeRandomButtonTv;
    public final MaterialButton homeReloadConnectionGoToDownloads;
    public final MaterialButton homeReloadConnectionOpenInBrowser;
    public final MaterialButton homeReloadConnectionerror;
    public final FrameLayout homeRoot;
    public final ImageView homeSwitchAccount;
    public final TextView resultErrorText;
    private final FrameLayout rootView;

    private FragmentHomeTvBinding(FrameLayout frameLayout, ExtendedFloatingActionButton extendedFloatingActionButton, LinearLayout linearLayout, MaterialButton materialButton, FrameLayout frameLayout2, LinearLayout linearLayout2, ShimmerFrameLayout shimmerFrameLayout, RecyclerView recyclerView, ImageView imageView, ImageView imageView2, ExtendedFloatingActionButton extendedFloatingActionButton2, ImageView imageView3, MaterialButton materialButton2, MaterialButton materialButton3, MaterialButton materialButton4, FrameLayout frameLayout3, ImageView imageView4, TextView textView) {
        this.rootView = frameLayout;
        this.homeApiFab = extendedFloatingActionButton;
        this.homeApiHolder = linearLayout;
        this.homeChangeApi = materialButton;
        this.homeLoading = frameLayout2;
        this.homeLoadingError = linearLayout2;
        this.homeLoadingShimmer = shimmerFrameLayout;
        this.homeMasterRecycler = recyclerView;
        this.homePreviewReloadProvider = imageView;
        this.homePreviewSearchButton = imageView2;
        this.homeRandom = extendedFloatingActionButton2;
        this.homeRandomButtonTv = imageView3;
        this.homeReloadConnectionGoToDownloads = materialButton2;
        this.homeReloadConnectionOpenInBrowser = materialButton3;
        this.homeReloadConnectionerror = materialButton4;
        this.homeRoot = frameLayout3;
        this.homeSwitchAccount = imageView4;
        this.resultErrorText = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static FragmentHomeTvBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentHomeTvBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_home_tv, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentHomeTvBinding bind(View view) {
        int i = R.id.home_api_fab;
        ExtendedFloatingActionButton extendedFloatingActionButton = (ExtendedFloatingActionButton) ViewBindings.findChildViewById(view, i);
        if (extendedFloatingActionButton != null) {
            i = R.id.home_api_holder;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
            if (linearLayout != null) {
                i = R.id.home_change_api;
                MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
                if (materialButton != null) {
                    i = R.id.home_loading;
                    FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
                    if (frameLayout != null) {
                        i = R.id.home_loading_error;
                        LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                        if (linearLayout2 != null) {
                            i = R.id.home_loading_shimmer;
                            ShimmerFrameLayout shimmerFrameLayout = (ShimmerFrameLayout) ViewBindings.findChildViewById(view, i);
                            if (shimmerFrameLayout != null) {
                                i = R.id.home_master_recycler;
                                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                                if (recyclerView != null) {
                                    i = R.id.home_preview_reload_provider;
                                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                                    if (imageView != null) {
                                        i = R.id.home_preview_search_button;
                                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
                                        if (imageView2 != null) {
                                            i = R.id.home_random;
                                            ExtendedFloatingActionButton extendedFloatingActionButton2 = (ExtendedFloatingActionButton) ViewBindings.findChildViewById(view, i);
                                            if (extendedFloatingActionButton2 != null) {
                                                i = R.id.home_random_button_tv;
                                                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                if (imageView3 != null) {
                                                    i = R.id.home_reload_connection_go_to_downloads;
                                                    MaterialButton materialButton2 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                                                    if (materialButton2 != null) {
                                                        i = R.id.home_reload_connection_open_in_browser;
                                                        MaterialButton materialButton3 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                                                        if (materialButton3 != null) {
                                                            i = R.id.home_reload_connectionerror;
                                                            MaterialButton materialButton4 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                                                            if (materialButton4 != null) {
                                                                FrameLayout frameLayout2 = (FrameLayout) view;
                                                                i = R.id.home_switch_account;
                                                                ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                                if (imageView4 != null) {
                                                                    i = R.id.result_error_text;
                                                                    TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                                                                    if (textView != null) {
                                                                        return new FragmentHomeTvBinding(frameLayout2, extendedFloatingActionButton, linearLayout, materialButton, frameLayout, linearLayout2, shimmerFrameLayout, recyclerView, imageView, imageView2, extendedFloatingActionButton2, imageView3, materialButton2, materialButton3, materialButton4, frameLayout2, imageView4, textView);
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
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
