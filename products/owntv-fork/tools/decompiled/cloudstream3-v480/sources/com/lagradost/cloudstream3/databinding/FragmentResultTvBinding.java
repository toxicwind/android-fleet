package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.ui.AutofitRecyclerView;
import com.lagradost.cloudstream3.ui.MaxRecyclerView;
import com.lagradost.cloudstream3.utils.PercentageCropImageView;

/* JADX INFO: loaded from: classes6.dex */
public final class FragmentResultTvBinding implements ViewBinding {
    public final PercentageCropImageView backgroundPoster;
    public final FrameLayout backgroundPosterHolder;
    public final ImageView backgroundPosterWatermarkBadgeHolder;
    public final LinearLayout episodeHolderTv;
    public final ImageView episodePlayIcon;
    public final ConstraintLayout episodesShadow;
    public final ImageView episodesShadowBackground;
    public final View redirectToEpisodes;
    public final View redirectToPlay;
    public final MaterialButton resultAddSync;
    public final LinearLayout resultBookmark;
    public final MaterialButton resultBookmarkButton;
    public final TextView resultBookmarkText;
    public final RecyclerView resultCastItems;
    public final TextView resultCastText;
    public final TextView resultComingSoon;
    public final LinearLayout resultDataHolder;
    public final TextView resultDescription;
    public final MaxRecyclerView resultDubSelection;
    public final RecyclerView resultEpisodes;
    public final LinearLayout resultEpisodesShow;
    public final MaterialButton resultEpisodesShowButton;
    public final TextView resultEpisodesShowText;
    public final TextView resultEpisodesText;
    public final TextView resultErrorText;
    public final LinearLayout resultFavorite;
    public final MaterialButton resultFavoriteButton;
    public final TextView resultFavoriteText;
    public final NestedScrollView resultFinishLoading;
    public final TextView resultInfo;
    public final ShimmerFrameLayout resultLoading;
    public final LinearLayout resultLoadingError;
    public final MaterialButton resultMetaContentRating;
    public final TextView resultMetaDuration;
    public final TextView resultMetaRating;
    public final MaterialButton resultMetaSite;
    public final TextView resultMetaStatus;
    public final TextView resultMetaType;
    public final TextView resultMetaYear;
    public final LinearLayout resultMovieParent;
    public final TextView resultNextAiring;
    public final LinearLayout resultNextAiringHolder;
    public final TextView resultNextAiringTime;
    public final TextView resultNoEpisodes;
    public final LinearLayout resultPlayMovie;
    public final MaterialButton resultPlayMovieButton;
    public final TextView resultPlayMovieText;
    public final LinearLayout resultPlayParent;
    public final LinearLayout resultPlaySeries;
    public final MaterialButton resultPlaySeriesButton;
    public final TextView resultPlaySeriesText;
    public final LinearLayout resultPlayTrailer;
    public final MaterialButton resultPlayTrailerButton;
    public final TextView resultPlayTrailerText;
    public final ImageView resultPoster;
    public final CardView resultPosterHolder;
    public final MaxRecyclerView resultRangeSelection;
    public final RecyclerView resultRecommendationsFilterSelection;
    public final LinearLayout resultRecommendationsHolder;
    public final AutofitRecyclerView resultRecommendationsList;
    public final MaterialButton resultReloadConnectionerror;
    public final LinearLayout resultResumeProgressHolder;
    public final LinearLayout resultResumeSeries;
    public final MaterialButton resultResumeSeriesButton;
    public final CircularProgressIndicator resultResumeSeriesProgress;
    public final TextView resultResumeSeriesProgressText;
    public final TextView resultResumeSeriesText;
    public final TextView resultResumeSeriesTitle;
    public final FrameLayout resultRoot;
    public final LinearLayout resultSearch;
    public final MaterialButton resultSearchButton;
    public final TextView resultSearchText;
    public final MaxRecyclerView resultSeasonSelection;
    public final LinearLayout resultSubscribe;
    public final MaterialButton resultSubscribeButton;
    public final TextView resultSubscribeText;
    public final ChipGroup resultTag;
    public final TextView resultTitle;
    public final TextView resultTvComingSoon;
    public final TextView resultVpn;
    public final LinearLayout rightLayout;
    private final FrameLayout rootView;
    public final LinearLayout seriesHolder;
    public final Space shadowSpace1;
    public final Space shadowSpace2;
    public final View temporaryNoFocus;
    public final FrameLayout watchProgressContainer;

    private FragmentResultTvBinding(FrameLayout frameLayout, PercentageCropImageView percentageCropImageView, FrameLayout frameLayout2, ImageView imageView, LinearLayout linearLayout, ImageView imageView2, ConstraintLayout constraintLayout, ImageView imageView3, View view, View view2, MaterialButton materialButton, LinearLayout linearLayout2, MaterialButton materialButton2, TextView textView, RecyclerView recyclerView, TextView textView2, TextView textView3, LinearLayout linearLayout3, TextView textView4, MaxRecyclerView maxRecyclerView, RecyclerView recyclerView2, LinearLayout linearLayout4, MaterialButton materialButton3, TextView textView5, TextView textView6, TextView textView7, LinearLayout linearLayout5, MaterialButton materialButton4, TextView textView8, NestedScrollView nestedScrollView, TextView textView9, ShimmerFrameLayout shimmerFrameLayout, LinearLayout linearLayout6, MaterialButton materialButton5, TextView textView10, TextView textView11, MaterialButton materialButton6, TextView textView12, TextView textView13, TextView textView14, LinearLayout linearLayout7, TextView textView15, LinearLayout linearLayout8, TextView textView16, TextView textView17, LinearLayout linearLayout9, MaterialButton materialButton7, TextView textView18, LinearLayout linearLayout10, LinearLayout linearLayout11, MaterialButton materialButton8, TextView textView19, LinearLayout linearLayout12, MaterialButton materialButton9, TextView textView20, ImageView imageView4, CardView cardView, MaxRecyclerView maxRecyclerView2, RecyclerView recyclerView3, LinearLayout linearLayout13, AutofitRecyclerView autofitRecyclerView, MaterialButton materialButton10, LinearLayout linearLayout14, LinearLayout linearLayout15, MaterialButton materialButton11, CircularProgressIndicator circularProgressIndicator, TextView textView21, TextView textView22, TextView textView23, FrameLayout frameLayout3, LinearLayout linearLayout16, MaterialButton materialButton12, TextView textView24, MaxRecyclerView maxRecyclerView3, LinearLayout linearLayout17, MaterialButton materialButton13, TextView textView25, ChipGroup chipGroup, TextView textView26, TextView textView27, TextView textView28, LinearLayout linearLayout18, LinearLayout linearLayout19, Space space, Space space2, View view3, FrameLayout frameLayout4) {
        this.rootView = frameLayout;
        this.backgroundPoster = percentageCropImageView;
        this.backgroundPosterHolder = frameLayout2;
        this.backgroundPosterWatermarkBadgeHolder = imageView;
        this.episodeHolderTv = linearLayout;
        this.episodePlayIcon = imageView2;
        this.episodesShadow = constraintLayout;
        this.episodesShadowBackground = imageView3;
        this.redirectToEpisodes = view;
        this.redirectToPlay = view2;
        this.resultAddSync = materialButton;
        this.resultBookmark = linearLayout2;
        this.resultBookmarkButton = materialButton2;
        this.resultBookmarkText = textView;
        this.resultCastItems = recyclerView;
        this.resultCastText = textView2;
        this.resultComingSoon = textView3;
        this.resultDataHolder = linearLayout3;
        this.resultDescription = textView4;
        this.resultDubSelection = maxRecyclerView;
        this.resultEpisodes = recyclerView2;
        this.resultEpisodesShow = linearLayout4;
        this.resultEpisodesShowButton = materialButton3;
        this.resultEpisodesShowText = textView5;
        this.resultEpisodesText = textView6;
        this.resultErrorText = textView7;
        this.resultFavorite = linearLayout5;
        this.resultFavoriteButton = materialButton4;
        this.resultFavoriteText = textView8;
        this.resultFinishLoading = nestedScrollView;
        this.resultInfo = textView9;
        this.resultLoading = shimmerFrameLayout;
        this.resultLoadingError = linearLayout6;
        this.resultMetaContentRating = materialButton5;
        this.resultMetaDuration = textView10;
        this.resultMetaRating = textView11;
        this.resultMetaSite = materialButton6;
        this.resultMetaStatus = textView12;
        this.resultMetaType = textView13;
        this.resultMetaYear = textView14;
        this.resultMovieParent = linearLayout7;
        this.resultNextAiring = textView15;
        this.resultNextAiringHolder = linearLayout8;
        this.resultNextAiringTime = textView16;
        this.resultNoEpisodes = textView17;
        this.resultPlayMovie = linearLayout9;
        this.resultPlayMovieButton = materialButton7;
        this.resultPlayMovieText = textView18;
        this.resultPlayParent = linearLayout10;
        this.resultPlaySeries = linearLayout11;
        this.resultPlaySeriesButton = materialButton8;
        this.resultPlaySeriesText = textView19;
        this.resultPlayTrailer = linearLayout12;
        this.resultPlayTrailerButton = materialButton9;
        this.resultPlayTrailerText = textView20;
        this.resultPoster = imageView4;
        this.resultPosterHolder = cardView;
        this.resultRangeSelection = maxRecyclerView2;
        this.resultRecommendationsFilterSelection = recyclerView3;
        this.resultRecommendationsHolder = linearLayout13;
        this.resultRecommendationsList = autofitRecyclerView;
        this.resultReloadConnectionerror = materialButton10;
        this.resultResumeProgressHolder = linearLayout14;
        this.resultResumeSeries = linearLayout15;
        this.resultResumeSeriesButton = materialButton11;
        this.resultResumeSeriesProgress = circularProgressIndicator;
        this.resultResumeSeriesProgressText = textView21;
        this.resultResumeSeriesText = textView22;
        this.resultResumeSeriesTitle = textView23;
        this.resultRoot = frameLayout3;
        this.resultSearch = linearLayout16;
        this.resultSearchButton = materialButton12;
        this.resultSearchText = textView24;
        this.resultSeasonSelection = maxRecyclerView3;
        this.resultSubscribe = linearLayout17;
        this.resultSubscribeButton = materialButton13;
        this.resultSubscribeText = textView25;
        this.resultTag = chipGroup;
        this.resultTitle = textView26;
        this.resultTvComingSoon = textView27;
        this.resultVpn = textView28;
        this.rightLayout = linearLayout18;
        this.seriesHolder = linearLayout19;
        this.shadowSpace1 = space;
        this.shadowSpace2 = space2;
        this.temporaryNoFocus = view3;
        this.watchProgressContainer = frameLayout4;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static FragmentResultTvBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentResultTvBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_result_tv, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentResultTvBinding bind(View view) {
        View viewFindChildViewById;
        View viewFindChildViewById2;
        View viewFindChildViewById3;
        int i = R.id.background_poster;
        PercentageCropImageView percentageCropImageView = (PercentageCropImageView) ViewBindings.findChildViewById(view, i);
        if (percentageCropImageView != null) {
            i = R.id.background_poster_holder;
            FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
            if (frameLayout != null) {
                i = R.id.background_poster_watermark_badge_holder;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                if (imageView != null) {
                    i = R.id.episode_holder_tv;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
                    if (linearLayout != null) {
                        i = R.id.episode_play_icon;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
                        if (imageView2 != null) {
                            i = R.id.episodes_shadow;
                            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                            if (constraintLayout != null) {
                                i = R.id.episodes_shadow_background;
                                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, i);
                                if (imageView3 != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = R.id.redirect_to_episodes))) != null && (viewFindChildViewById2 = ViewBindings.findChildViewById(view, (i = R.id.redirect_to_play))) != null) {
                                    i = R.id.result_add_sync;
                                    MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
                                    if (materialButton != null) {
                                        i = R.id.result_bookmark;
                                        LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                        if (linearLayout2 != null) {
                                            i = R.id.result_bookmark_Button;
                                            MaterialButton materialButton2 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                                            if (materialButton2 != null) {
                                                i = R.id.result_bookmark_text;
                                                TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                                                if (textView != null) {
                                                    i = R.id.result_cast_items;
                                                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                                                    if (recyclerView != null) {
                                                        i = R.id.result_cast_text;
                                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                                                        if (textView2 != null) {
                                                            i = R.id.result_coming_soon;
                                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                                                            if (textView3 != null) {
                                                                i = R.id.result_data_holder;
                                                                LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                                if (linearLayout3 != null) {
                                                                    i = R.id.result_description;
                                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                    if (textView4 != null) {
                                                                        i = R.id.result_dub_selection;
                                                                        MaxRecyclerView maxRecyclerView = (MaxRecyclerView) ViewBindings.findChildViewById(view, i);
                                                                        if (maxRecyclerView != null) {
                                                                            i = R.id.result_episodes;
                                                                            RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(view, i);
                                                                            if (recyclerView2 != null) {
                                                                                i = R.id.result_episodes_show;
                                                                                LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                                                if (linearLayout4 != null) {
                                                                                    i = R.id.result_episodes_show_button;
                                                                                    MaterialButton materialButton3 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                                                                                    if (materialButton3 != null) {
                                                                                        i = R.id.result_episodes_show_text;
                                                                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                        if (textView5 != null) {
                                                                                            i = R.id.result_episodes_text;
                                                                                            TextView textView6 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                            if (textView6 != null) {
                                                                                                i = R.id.result_error_text;
                                                                                                TextView textView7 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                if (textView7 != null) {
                                                                                                    i = R.id.result_favorite;
                                                                                                    LinearLayout linearLayout5 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                                                                    if (linearLayout5 != null) {
                                                                                                        i = R.id.result_favorite_Button;
                                                                                                        MaterialButton materialButton4 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                                                                                                        if (materialButton4 != null) {
                                                                                                            i = R.id.result_favorite_Text;
                                                                                                            TextView textView8 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                            if (textView8 != null) {
                                                                                                                i = R.id.result_finish_loading;
                                                                                                                NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(view, i);
                                                                                                                if (nestedScrollView != null) {
                                                                                                                    i = R.id.result_info;
                                                                                                                    TextView textView9 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                    if (textView9 != null) {
                                                                                                                        i = R.id.result_loading;
                                                                                                                        ShimmerFrameLayout shimmerFrameLayout = (ShimmerFrameLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                        if (shimmerFrameLayout != null) {
                                                                                                                            i = R.id.result_loading_error;
                                                                                                                            LinearLayout linearLayout6 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                            if (linearLayout6 != null) {
                                                                                                                                i = R.id.result_meta_content_rating;
                                                                                                                                MaterialButton materialButton5 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                                                                                                                                if (materialButton5 != null) {
                                                                                                                                    i = R.id.result_meta_duration;
                                                                                                                                    TextView textView10 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                    if (textView10 != null) {
                                                                                                                                        i = R.id.result_meta_rating;
                                                                                                                                        TextView textView11 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                        if (textView11 != null) {
                                                                                                                                            i = R.id.result_meta_site;
                                                                                                                                            MaterialButton materialButton6 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                                                                                                                                            if (materialButton6 != null) {
                                                                                                                                                i = R.id.result_meta_status;
                                                                                                                                                TextView textView12 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                if (textView12 != null) {
                                                                                                                                                    i = R.id.result_meta_type;
                                                                                                                                                    TextView textView13 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                    if (textView13 != null) {
                                                                                                                                                        i = R.id.result_meta_year;
                                                                                                                                                        TextView textView14 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                        if (textView14 != null) {
                                                                                                                                                            i = R.id.result_movie_parent;
                                                                                                                                                            LinearLayout linearLayout7 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                                                            if (linearLayout7 != null) {
                                                                                                                                                                i = R.id.result_next_airing;
                                                                                                                                                                TextView textView15 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                if (textView15 != null) {
                                                                                                                                                                    i = R.id.result_next_airing_holder;
                                                                                                                                                                    LinearLayout linearLayout8 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                    if (linearLayout8 != null) {
                                                                                                                                                                        i = R.id.result_next_airing_time;
                                                                                                                                                                        TextView textView16 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                        if (textView16 != null) {
                                                                                                                                                                            i = R.id.result_no_episodes;
                                                                                                                                                                            TextView textView17 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                            if (textView17 != null) {
                                                                                                                                                                                i = R.id.result_play_movie;
                                                                                                                                                                                LinearLayout linearLayout9 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                if (linearLayout9 != null) {
                                                                                                                                                                                    i = R.id.result_play_movie_button;
                                                                                                                                                                                    MaterialButton materialButton7 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                    if (materialButton7 != null) {
                                                                                                                                                                                        i = R.id.result_play_movie_text;
                                                                                                                                                                                        TextView textView18 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                        if (textView18 != null) {
                                                                                                                                                                                            i = R.id.result_play_parent;
                                                                                                                                                                                            LinearLayout linearLayout10 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                            if (linearLayout10 != null) {
                                                                                                                                                                                                i = R.id.result_play_series;
                                                                                                                                                                                                LinearLayout linearLayout11 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                if (linearLayout11 != null) {
                                                                                                                                                                                                    i = R.id.result_play_series_button;
                                                                                                                                                                                                    MaterialButton materialButton8 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                    if (materialButton8 != null) {
                                                                                                                                                                                                        i = R.id.result_play_series_text;
                                                                                                                                                                                                        TextView textView19 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                        if (textView19 != null) {
                                                                                                                                                                                                            i = R.id.result_play_trailer;
                                                                                                                                                                                                            LinearLayout linearLayout12 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                            if (linearLayout12 != null) {
                                                                                                                                                                                                                i = R.id.result_play_trailer_button;
                                                                                                                                                                                                                MaterialButton materialButton9 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                if (materialButton9 != null) {
                                                                                                                                                                                                                    i = R.id.result_play_trailer_text;
                                                                                                                                                                                                                    TextView textView20 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                    if (textView20 != null) {
                                                                                                                                                                                                                        i = R.id.result_poster;
                                                                                                                                                                                                                        ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                        if (imageView4 != null) {
                                                                                                                                                                                                                            i = R.id.result_poster_holder;
                                                                                                                                                                                                                            CardView cardView = (CardView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                            if (cardView != null) {
                                                                                                                                                                                                                                i = R.id.result_range_selection;
                                                                                                                                                                                                                                MaxRecyclerView maxRecyclerView2 = (MaxRecyclerView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                if (maxRecyclerView2 != null) {
                                                                                                                                                                                                                                    i = R.id.result_recommendations_filter_selection;
                                                                                                                                                                                                                                    RecyclerView recyclerView3 = (RecyclerView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                    if (recyclerView3 != null) {
                                                                                                                                                                                                                                        i = R.id.result_recommendations_holder;
                                                                                                                                                                                                                                        LinearLayout linearLayout13 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                        if (linearLayout13 != null) {
                                                                                                                                                                                                                                            i = R.id.result_recommendations_list;
                                                                                                                                                                                                                                            AutofitRecyclerView autofitRecyclerView = (AutofitRecyclerView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                            if (autofitRecyclerView != null) {
                                                                                                                                                                                                                                                i = R.id.result_reload_connectionerror;
                                                                                                                                                                                                                                                MaterialButton materialButton10 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                if (materialButton10 != null) {
                                                                                                                                                                                                                                                    i = R.id.result_resume_progress_holder;
                                                                                                                                                                                                                                                    LinearLayout linearLayout14 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                    if (linearLayout14 != null) {
                                                                                                                                                                                                                                                        i = R.id.result_resume_series;
                                                                                                                                                                                                                                                        LinearLayout linearLayout15 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                        if (linearLayout15 != null) {
                                                                                                                                                                                                                                                            i = R.id.result_resume_series_button;
                                                                                                                                                                                                                                                            MaterialButton materialButton11 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                            if (materialButton11 != null) {
                                                                                                                                                                                                                                                                i = R.id.result_resume_series_progress;
                                                                                                                                                                                                                                                                CircularProgressIndicator circularProgressIndicator = (CircularProgressIndicator) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                if (circularProgressIndicator != null) {
                                                                                                                                                                                                                                                                    i = R.id.result_resume_series_progress_text;
                                                                                                                                                                                                                                                                    TextView textView21 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                    if (textView21 != null) {
                                                                                                                                                                                                                                                                        i = R.id.result_resume_series_text;
                                                                                                                                                                                                                                                                        TextView textView22 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                        if (textView22 != null) {
                                                                                                                                                                                                                                                                            i = R.id.result_resume_series_title;
                                                                                                                                                                                                                                                                            TextView textView23 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                            if (textView23 != null) {
                                                                                                                                                                                                                                                                                FrameLayout frameLayout2 = (FrameLayout) view;
                                                                                                                                                                                                                                                                                i = R.id.result_search;
                                                                                                                                                                                                                                                                                LinearLayout linearLayout16 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                                if (linearLayout16 != null) {
                                                                                                                                                                                                                                                                                    i = R.id.result_search_Button;
                                                                                                                                                                                                                                                                                    MaterialButton materialButton12 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                                    if (materialButton12 != null) {
                                                                                                                                                                                                                                                                                        i = R.id.result_search_text;
                                                                                                                                                                                                                                                                                        TextView textView24 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                                        if (textView24 != null) {
                                                                                                                                                                                                                                                                                            i = R.id.result_season_selection;
                                                                                                                                                                                                                                                                                            MaxRecyclerView maxRecyclerView3 = (MaxRecyclerView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                                            if (maxRecyclerView3 != null) {
                                                                                                                                                                                                                                                                                                i = R.id.result_subscribe;
                                                                                                                                                                                                                                                                                                LinearLayout linearLayout17 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                                                if (linearLayout17 != null) {
                                                                                                                                                                                                                                                                                                    i = R.id.result_subscribe_Button;
                                                                                                                                                                                                                                                                                                    MaterialButton materialButton13 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                                                    if (materialButton13 != null) {
                                                                                                                                                                                                                                                                                                        i = R.id.result_subscribe_text;
                                                                                                                                                                                                                                                                                                        TextView textView25 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                                                        if (textView25 != null) {
                                                                                                                                                                                                                                                                                                            i = R.id.result_tag;
                                                                                                                                                                                                                                                                                                            ChipGroup chipGroup = (ChipGroup) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                                                            if (chipGroup != null) {
                                                                                                                                                                                                                                                                                                                i = R.id.result_title;
                                                                                                                                                                                                                                                                                                                TextView textView26 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                                                                if (textView26 != null) {
                                                                                                                                                                                                                                                                                                                    i = R.id.result_tv_coming_soon;
                                                                                                                                                                                                                                                                                                                    TextView textView27 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                                                                    if (textView27 != null) {
                                                                                                                                                                                                                                                                                                                        i = R.id.result_vpn;
                                                                                                                                                                                                                                                                                                                        TextView textView28 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                                                                        if (textView28 != null) {
                                                                                                                                                                                                                                                                                                                            i = R.id.right_layout;
                                                                                                                                                                                                                                                                                                                            LinearLayout linearLayout18 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                                                                            if (linearLayout18 != null) {
                                                                                                                                                                                                                                                                                                                                i = R.id.series_holder;
                                                                                                                                                                                                                                                                                                                                LinearLayout linearLayout19 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                                                                                if (linearLayout19 != null) {
                                                                                                                                                                                                                                                                                                                                    i = R.id.shadow_space_1;
                                                                                                                                                                                                                                                                                                                                    Space space = (Space) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                                                                                    if (space != null) {
                                                                                                                                                                                                                                                                                                                                        i = R.id.shadow_space_2;
                                                                                                                                                                                                                                                                                                                                        Space space2 = (Space) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                                                                                        if (space2 != null && (viewFindChildViewById3 = ViewBindings.findChildViewById(view, (i = R.id.temporary_no_focus))) != null) {
                                                                                                                                                                                                                                                                                                                                            i = R.id.watch_progress_container;
                                                                                                                                                                                                                                                                                                                                            FrameLayout frameLayout3 = (FrameLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                                                                                            if (frameLayout3 != null) {
                                                                                                                                                                                                                                                                                                                                                return new FragmentResultTvBinding(frameLayout2, percentageCropImageView, frameLayout, imageView, linearLayout, imageView2, constraintLayout, imageView3, viewFindChildViewById, viewFindChildViewById2, materialButton, linearLayout2, materialButton2, textView, recyclerView, textView2, textView3, linearLayout3, textView4, maxRecyclerView, recyclerView2, linearLayout4, materialButton3, textView5, textView6, textView7, linearLayout5, materialButton4, textView8, nestedScrollView, textView9, shimmerFrameLayout, linearLayout6, materialButton5, textView10, textView11, materialButton6, textView12, textView13, textView14, linearLayout7, textView15, linearLayout8, textView16, textView17, linearLayout9, materialButton7, textView18, linearLayout10, linearLayout11, materialButton8, textView19, linearLayout12, materialButton9, textView20, imageView4, cardView, maxRecyclerView2, recyclerView3, linearLayout13, autofitRecyclerView, materialButton10, linearLayout14, linearLayout15, materialButton11, circularProgressIndicator, textView21, textView22, textView23, frameLayout2, linearLayout16, materialButton12, textView24, maxRecyclerView3, linearLayout17, materialButton13, textView25, chipGroup, textView26, textView27, textView28, linearLayout18, linearLayout19, space, space2, viewFindChildViewById3, frameLayout3);
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
