package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.slider.Slider;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class ResultSyncBinding implements ViewBinding {
    public final TextView homeParentItemTitle;
    public final ImageView resultSyncAddEpisode;
    public final ListView resultSyncCheck;
    public final EditText resultSyncCurrentEpisodes;
    public final ContentLoadingProgressBar resultSyncEpisodes;
    public final ScrollView resultSyncHolder;
    public final ShimmerFrameLayout resultSyncLoadingShimmer;
    public final TextView resultSyncMaxEpisodes;
    public final TextView resultSyncNames;
    public final Slider resultSyncRating;
    public final MaterialButton resultSyncScoreText;
    public final MaterialButton resultSyncSetScore;
    public final ImageView resultSyncSubEpisode;
    private final FrameLayout rootView;

    private ResultSyncBinding(FrameLayout frameLayout, TextView textView, ImageView imageView, ListView listView, EditText editText, ContentLoadingProgressBar contentLoadingProgressBar, ScrollView scrollView, ShimmerFrameLayout shimmerFrameLayout, TextView textView2, TextView textView3, Slider slider, MaterialButton materialButton, MaterialButton materialButton2, ImageView imageView2) {
        this.rootView = frameLayout;
        this.homeParentItemTitle = textView;
        this.resultSyncAddEpisode = imageView;
        this.resultSyncCheck = listView;
        this.resultSyncCurrentEpisodes = editText;
        this.resultSyncEpisodes = contentLoadingProgressBar;
        this.resultSyncHolder = scrollView;
        this.resultSyncLoadingShimmer = shimmerFrameLayout;
        this.resultSyncMaxEpisodes = textView2;
        this.resultSyncNames = textView3;
        this.resultSyncRating = slider;
        this.resultSyncScoreText = materialButton;
        this.resultSyncSetScore = materialButton2;
        this.resultSyncSubEpisode = imageView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static ResultSyncBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ResultSyncBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.result_sync, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ResultSyncBinding bind(View view) {
        int i = R.id.home_parent_item_title;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
        if (textView != null) {
            i = R.id.result_sync_add_episode;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
            if (imageView != null) {
                i = R.id.result_sync_check;
                ListView listView = (ListView) ViewBindings.findChildViewById(view, i);
                if (listView != null) {
                    i = R.id.result_sync_current_episodes;
                    EditText editText = (EditText) ViewBindings.findChildViewById(view, i);
                    if (editText != null) {
                        i = R.id.result_sync_episodes;
                        ContentLoadingProgressBar contentLoadingProgressBar = (ContentLoadingProgressBar) ViewBindings.findChildViewById(view, i);
                        if (contentLoadingProgressBar != null) {
                            i = R.id.result_sync_holder;
                            ScrollView scrollView = (ScrollView) ViewBindings.findChildViewById(view, i);
                            if (scrollView != null) {
                                i = R.id.result_sync_loading_shimmer;
                                ShimmerFrameLayout shimmerFrameLayout = (ShimmerFrameLayout) ViewBindings.findChildViewById(view, i);
                                if (shimmerFrameLayout != null) {
                                    i = R.id.result_sync_max_episodes;
                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                                    if (textView2 != null) {
                                        i = R.id.result_sync_names;
                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                                        if (textView3 != null) {
                                            i = R.id.result_sync_rating;
                                            Slider slider = (Slider) ViewBindings.findChildViewById(view, i);
                                            if (slider != null) {
                                                i = R.id.result_sync_score_text;
                                                MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
                                                if (materialButton != null) {
                                                    i = R.id.result_sync_set_score;
                                                    MaterialButton materialButton2 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                                                    if (materialButton2 != null) {
                                                        i = R.id.result_sync_sub_episode;
                                                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                        if (imageView2 != null) {
                                                            return new ResultSyncBinding((FrameLayout) view, textView, imageView, listView, editText, contentLoadingProgressBar, scrollView, shimmerFrameLayout, textView2, textView3, slider, materialButton, materialButton2, imageView2);
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
