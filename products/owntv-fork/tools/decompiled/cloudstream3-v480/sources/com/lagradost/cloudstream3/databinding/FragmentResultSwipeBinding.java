package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.mediarouter.app.MediaRouteButton;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.discord.panels.OverlappingPanelsLayout;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class FragmentResultSwipeBinding implements ViewBinding {
    public final FrameLayout centerPanel;
    public final FrameLayout endPanel;
    public final FragmentResultBinding fragmentResult;
    public final MediaRouteButton mediaRouteButton;
    public final LinearLayout mediaRouteButtonHolder;
    public final ImageView resultBack;
    public final ExtendedFloatingActionButton resultBookmarkFab;
    public final ImageView resultFavorite;
    public final FrameLayout resultFullscreenHolder;
    public final FrameLayout resultMainHolder;
    public final ImageView resultMiniSync;
    public final ImageView resultOpenInBrowser;
    public final OverlappingPanelsLayout resultOverlappingPanels;
    public final ResultRecommendationsBinding resultRecommendations;
    public final ImageView resultRecommendationsBtt;
    public final ImageView resultSearch;
    public final ImageView resultShare;
    public final ImageView resultSubscribe;
    public final ResultSyncBinding resultSync;
    public final FrameLayout resultTopBar;
    private final LinearLayout rootView;
    public final FrameLayout startPanel;

    private FragmentResultSwipeBinding(LinearLayout linearLayout, FrameLayout frameLayout, FrameLayout frameLayout2, FragmentResultBinding fragmentResultBinding, MediaRouteButton mediaRouteButton, LinearLayout linearLayout2, ImageView imageView, ExtendedFloatingActionButton extendedFloatingActionButton, ImageView imageView2, FrameLayout frameLayout3, FrameLayout frameLayout4, ImageView imageView3, ImageView imageView4, OverlappingPanelsLayout overlappingPanelsLayout, ResultRecommendationsBinding resultRecommendationsBinding, ImageView imageView5, ImageView imageView6, ImageView imageView7, ImageView imageView8, ResultSyncBinding resultSyncBinding, FrameLayout frameLayout5, FrameLayout frameLayout6) {
        this.rootView = linearLayout;
        this.centerPanel = frameLayout;
        this.endPanel = frameLayout2;
        this.fragmentResult = fragmentResultBinding;
        this.mediaRouteButton = mediaRouteButton;
        this.mediaRouteButtonHolder = linearLayout2;
        this.resultBack = imageView;
        this.resultBookmarkFab = extendedFloatingActionButton;
        this.resultFavorite = imageView2;
        this.resultFullscreenHolder = frameLayout3;
        this.resultMainHolder = frameLayout4;
        this.resultMiniSync = imageView3;
        this.resultOpenInBrowser = imageView4;
        this.resultOverlappingPanels = overlappingPanelsLayout;
        this.resultRecommendations = resultRecommendationsBinding;
        this.resultRecommendationsBtt = imageView5;
        this.resultSearch = imageView6;
        this.resultShare = imageView7;
        this.resultSubscribe = imageView8;
        this.resultSync = resultSyncBinding;
        this.resultTopBar = frameLayout5;
        this.startPanel = frameLayout6;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentResultSwipeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentResultSwipeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_result_swipe, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentResultSwipeBinding bind(View view) {
        View viewFindChildViewById;
        View viewFindChildViewById2;
        View viewFindChildViewById3;
        int i = R.id.center_panel;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
        if (frameLayout != null) {
            i = R.id.end_panel;
            FrameLayout frameLayout2 = (FrameLayout) ViewBindings.findChildViewById(view, i);
            if (frameLayout2 != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = R.id.fragment_result))) != null) {
                FragmentResultBinding fragmentResultBindingBind = FragmentResultBinding.bind(viewFindChildViewById);
                i = R.id.media_route_button;
                MediaRouteButton mediaRouteButton = (MediaRouteButton) ViewBindings.findChildViewById(view, i);
                if (mediaRouteButton != null) {
                    i = R.id.media_route_button_holder;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
                    if (linearLayout != null) {
                        i = R.id.result_back;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                        if (imageView != null) {
                            i = R.id.result_bookmark_fab;
                            ExtendedFloatingActionButton extendedFloatingActionButton = (ExtendedFloatingActionButton) ViewBindings.findChildViewById(view, i);
                            if (extendedFloatingActionButton != null) {
                                i = R.id.result_favorite;
                                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
                                if (imageView2 != null) {
                                    i = R.id.result_fullscreen_holder;
                                    FrameLayout frameLayout3 = (FrameLayout) ViewBindings.findChildViewById(view, i);
                                    if (frameLayout3 != null) {
                                        i = R.id.result_main_holder;
                                        FrameLayout frameLayout4 = (FrameLayout) ViewBindings.findChildViewById(view, i);
                                        if (frameLayout4 != null) {
                                            i = R.id.result_mini_sync;
                                            ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, i);
                                            if (imageView3 != null) {
                                                i = R.id.result_open_in_browser;
                                                ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                if (imageView4 != null) {
                                                    i = R.id.result_overlapping_panels;
                                                    OverlappingPanelsLayout overlappingPanelsLayout = (OverlappingPanelsLayout) ViewBindings.findChildViewById(view, i);
                                                    if (overlappingPanelsLayout != null && (viewFindChildViewById2 = ViewBindings.findChildViewById(view, (i = R.id.result_recommendations))) != null) {
                                                        ResultRecommendationsBinding resultRecommendationsBindingBind = ResultRecommendationsBinding.bind(viewFindChildViewById2);
                                                        i = R.id.result_recommendations_btt;
                                                        ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                        if (imageView5 != null) {
                                                            i = R.id.result_search;
                                                            ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                            if (imageView6 != null) {
                                                                i = R.id.result_share;
                                                                ImageView imageView7 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                                if (imageView7 != null) {
                                                                    i = R.id.result_subscribe;
                                                                    ImageView imageView8 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                                    if (imageView8 != null && (viewFindChildViewById3 = ViewBindings.findChildViewById(view, (i = R.id.result_sync))) != null) {
                                                                        ResultSyncBinding resultSyncBindingBind = ResultSyncBinding.bind(viewFindChildViewById3);
                                                                        i = R.id.result_top_bar;
                                                                        FrameLayout frameLayout5 = (FrameLayout) ViewBindings.findChildViewById(view, i);
                                                                        if (frameLayout5 != null) {
                                                                            i = R.id.start_panel;
                                                                            FrameLayout frameLayout6 = (FrameLayout) ViewBindings.findChildViewById(view, i);
                                                                            if (frameLayout6 != null) {
                                                                                return new FragmentResultSwipeBinding((LinearLayout) view, frameLayout, frameLayout2, fragmentResultBindingBind, mediaRouteButton, linearLayout, imageView, extendedFloatingActionButton, imageView2, frameLayout3, frameLayout4, imageView3, imageView4, overlappingPanelsLayout, resultRecommendationsBindingBind, imageView5, imageView6, imageView7, imageView8, resultSyncBindingBind, frameLayout5, frameLayout6);
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
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
