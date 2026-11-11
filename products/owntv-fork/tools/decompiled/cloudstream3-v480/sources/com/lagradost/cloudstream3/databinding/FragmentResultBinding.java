package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.ui.download.button.DownloadButton;

/* JADX INFO: loaded from: classes6.dex */
public final class FragmentResultBinding implements ViewBinding {
    public final ImageView backgroundPosterWatermarkBadge;
    public final DownloadButton downloadButton;
    public final ImageView episodePlayIcon;
    public final FragmentTrailerBinding fragmentTrailer;
    public final LinearLayout progressGroup;
    public final MaterialButton resultAddSync;
    public final ImageView resultBatchDownloadButton;
    public final RecyclerView resultCastItems;
    public final TextView resultCastText;
    public final TextView resultComingSoon;
    public final LinearLayout resultDataHolder;
    public final TextView resultDescription;
    public final MaterialButton resultDubSelect;
    public final ShimmerFrameLayout resultEpisodeLoading;
    public final MaterialButton resultEpisodeSelect;
    public final RecyclerView resultEpisodes;
    public final TextView resultEpisodesText;
    public final TextView resultErrorText;
    public final LinearLayout resultFinishLoading;
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
    public final MaterialButton resultNextSeriesButton;
    public final TextView resultNoEpisodes;
    public final MaterialButton resultPlayMovie;
    public final LinearLayout resultPlayParent;
    public final ImageView resultPoster;
    public final ImageView resultPosterBackground;
    public final FrameLayout resultPosterBackgroundHolder;
    public final CardView resultPosterHolder;
    public final MaterialButton resultReloadConnectionOpenInBrowser;
    public final MaterialButton resultReloadConnectionerror;
    public final LinearLayout resultResumeParent;
    public final FrameLayout resultResumeProgressHolder;
    public final MaterialButton resultResumeSeriesButton;
    public final CircularProgressIndicator resultResumeSeriesProgress;
    public final TextView resultResumeSeriesProgressText;
    public final TextView resultResumeSeriesTitle;
    public final FrameLayout resultRoot;
    public final NestedScrollView resultScroll;
    public final MaterialButton resultSeasonButton;
    public final FrameLayout resultSmallscreenHolder;
    public final MaterialButton resultSortButton;
    public final ChipGroup resultTag;
    public final TextView resultTagHolder;
    public final TextView resultTitle;
    public final FrameLayout resultTopHolder;
    public final TextView resultVpn;
    private final FrameLayout rootView;
    public final FrameLayout watchProgressContainer;

    private FragmentResultBinding(FrameLayout frameLayout, ImageView imageView, DownloadButton downloadButton, ImageView imageView2, FragmentTrailerBinding fragmentTrailerBinding, LinearLayout linearLayout, MaterialButton materialButton, ImageView imageView3, RecyclerView recyclerView, TextView textView, TextView textView2, LinearLayout linearLayout2, TextView textView3, MaterialButton materialButton2, ShimmerFrameLayout shimmerFrameLayout, MaterialButton materialButton3, RecyclerView recyclerView2, TextView textView4, TextView textView5, LinearLayout linearLayout3, TextView textView6, ShimmerFrameLayout shimmerFrameLayout2, LinearLayout linearLayout4, MaterialButton materialButton4, TextView textView7, TextView textView8, MaterialButton materialButton5, TextView textView9, TextView textView10, TextView textView11, LinearLayout linearLayout5, TextView textView12, LinearLayout linearLayout6, TextView textView13, MaterialButton materialButton6, TextView textView14, MaterialButton materialButton7, LinearLayout linearLayout7, ImageView imageView4, ImageView imageView5, FrameLayout frameLayout2, CardView cardView, MaterialButton materialButton8, MaterialButton materialButton9, LinearLayout linearLayout8, FrameLayout frameLayout3, MaterialButton materialButton10, CircularProgressIndicator circularProgressIndicator, TextView textView15, TextView textView16, FrameLayout frameLayout4, NestedScrollView nestedScrollView, MaterialButton materialButton11, FrameLayout frameLayout5, MaterialButton materialButton12, ChipGroup chipGroup, TextView textView17, TextView textView18, FrameLayout frameLayout6, TextView textView19, FrameLayout frameLayout7) {
        this.rootView = frameLayout;
        this.backgroundPosterWatermarkBadge = imageView;
        this.downloadButton = downloadButton;
        this.episodePlayIcon = imageView2;
        this.fragmentTrailer = fragmentTrailerBinding;
        this.progressGroup = linearLayout;
        this.resultAddSync = materialButton;
        this.resultBatchDownloadButton = imageView3;
        this.resultCastItems = recyclerView;
        this.resultCastText = textView;
        this.resultComingSoon = textView2;
        this.resultDataHolder = linearLayout2;
        this.resultDescription = textView3;
        this.resultDubSelect = materialButton2;
        this.resultEpisodeLoading = shimmerFrameLayout;
        this.resultEpisodeSelect = materialButton3;
        this.resultEpisodes = recyclerView2;
        this.resultEpisodesText = textView4;
        this.resultErrorText = textView5;
        this.resultFinishLoading = linearLayout3;
        this.resultInfo = textView6;
        this.resultLoading = shimmerFrameLayout2;
        this.resultLoadingError = linearLayout4;
        this.resultMetaContentRating = materialButton4;
        this.resultMetaDuration = textView7;
        this.resultMetaRating = textView8;
        this.resultMetaSite = materialButton5;
        this.resultMetaStatus = textView9;
        this.resultMetaType = textView10;
        this.resultMetaYear = textView11;
        this.resultMovieParent = linearLayout5;
        this.resultNextAiring = textView12;
        this.resultNextAiringHolder = linearLayout6;
        this.resultNextAiringTime = textView13;
        this.resultNextSeriesButton = materialButton6;
        this.resultNoEpisodes = textView14;
        this.resultPlayMovie = materialButton7;
        this.resultPlayParent = linearLayout7;
        this.resultPoster = imageView4;
        this.resultPosterBackground = imageView5;
        this.resultPosterBackgroundHolder = frameLayout2;
        this.resultPosterHolder = cardView;
        this.resultReloadConnectionOpenInBrowser = materialButton8;
        this.resultReloadConnectionerror = materialButton9;
        this.resultResumeParent = linearLayout8;
        this.resultResumeProgressHolder = frameLayout3;
        this.resultResumeSeriesButton = materialButton10;
        this.resultResumeSeriesProgress = circularProgressIndicator;
        this.resultResumeSeriesProgressText = textView15;
        this.resultResumeSeriesTitle = textView16;
        this.resultRoot = frameLayout4;
        this.resultScroll = nestedScrollView;
        this.resultSeasonButton = materialButton11;
        this.resultSmallscreenHolder = frameLayout5;
        this.resultSortButton = materialButton12;
        this.resultTag = chipGroup;
        this.resultTagHolder = textView17;
        this.resultTitle = textView18;
        this.resultTopHolder = frameLayout6;
        this.resultVpn = textView19;
        this.watchProgressContainer = frameLayout7;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static FragmentResultBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentResultBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_result, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentResultBinding bind(View view) {
        View viewFindChildViewById;
        int i = R.id.background_poster_watermark_badge;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.download_button;
            DownloadButton downloadButton = (DownloadButton) ViewBindings.findChildViewById(view, i);
            if (downloadButton != null) {
                i = R.id.episode_play_icon;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
                if (imageView2 != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = R.id.fragment_trailer))) != null) {
                    FragmentTrailerBinding fragmentTrailerBindingBind = FragmentTrailerBinding.bind(viewFindChildViewById);
                    i = R.id.progress_group;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
                    if (linearLayout != null) {
                        i = R.id.result_add_sync;
                        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
                        if (materialButton != null) {
                            i = R.id.result_batch_download_button;
                            ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, i);
                            if (imageView3 != null) {
                                i = R.id.result_cast_items;
                                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                                if (recyclerView != null) {
                                    i = R.id.result_cast_text;
                                    TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                                    if (textView != null) {
                                        i = R.id.result_coming_soon;
                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                                        if (textView2 != null) {
                                            i = R.id.result_data_holder;
                                            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                            if (linearLayout2 != null) {
                                                i = R.id.result_description;
                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                                                if (textView3 != null) {
                                                    i = R.id.result_dub_select;
                                                    MaterialButton materialButton2 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                                                    if (materialButton2 != null) {
                                                        i = R.id.result_episode_loading;
                                                        ShimmerFrameLayout shimmerFrameLayout = (ShimmerFrameLayout) ViewBindings.findChildViewById(view, i);
                                                        if (shimmerFrameLayout != null) {
                                                            i = R.id.result_episode_select;
                                                            MaterialButton materialButton3 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                                                            if (materialButton3 != null) {
                                                                i = R.id.result_episodes;
                                                                RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(view, i);
                                                                if (recyclerView2 != null) {
                                                                    i = R.id.result_episodes_text;
                                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                    if (textView4 != null) {
                                                                        i = R.id.result_error_text;
                                                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                        if (textView5 != null) {
                                                                            i = R.id.result_finish_loading;
                                                                            LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                                            if (linearLayout3 != null) {
                                                                                i = R.id.result_info;
                                                                                TextView textView6 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                if (textView6 != null) {
                                                                                    i = R.id.result_loading;
                                                                                    ShimmerFrameLayout shimmerFrameLayout2 = (ShimmerFrameLayout) ViewBindings.findChildViewById(view, i);
                                                                                    if (shimmerFrameLayout2 != null) {
                                                                                        i = R.id.result_loading_error;
                                                                                        LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                                                        if (linearLayout4 != null) {
                                                                                            i = R.id.result_meta_content_rating;
                                                                                            MaterialButton materialButton4 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                                                                                            if (materialButton4 != null) {
                                                                                                i = R.id.result_meta_duration;
                                                                                                TextView textView7 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                if (textView7 != null) {
                                                                                                    i = R.id.result_meta_rating;
                                                                                                    TextView textView8 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                    if (textView8 != null) {
                                                                                                        i = R.id.result_meta_site;
                                                                                                        MaterialButton materialButton5 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                                                                                                        if (materialButton5 != null) {
                                                                                                            i = R.id.result_meta_status;
                                                                                                            TextView textView9 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                            if (textView9 != null) {
                                                                                                                i = R.id.result_meta_type;
                                                                                                                TextView textView10 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                if (textView10 != null) {
                                                                                                                    i = R.id.result_meta_year;
                                                                                                                    TextView textView11 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                    if (textView11 != null) {
                                                                                                                        i = R.id.result_movie_parent;
                                                                                                                        LinearLayout linearLayout5 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                        if (linearLayout5 != null) {
                                                                                                                            i = R.id.result_next_airing;
                                                                                                                            TextView textView12 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                            if (textView12 != null) {
                                                                                                                                i = R.id.result_next_airing_holder;
                                                                                                                                LinearLayout linearLayout6 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                                if (linearLayout6 != null) {
                                                                                                                                    i = R.id.result_next_airing_time;
                                                                                                                                    TextView textView13 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                    if (textView13 != null) {
                                                                                                                                        i = R.id.result_next_series_button;
                                                                                                                                        MaterialButton materialButton6 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                                                                                                                                        if (materialButton6 != null) {
                                                                                                                                            i = R.id.result_no_episodes;
                                                                                                                                            TextView textView14 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                            if (textView14 != null) {
                                                                                                                                                i = R.id.result_play_movie;
                                                                                                                                                MaterialButton materialButton7 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                                                                                                                                                if (materialButton7 != null) {
                                                                                                                                                    i = R.id.result_play_parent;
                                                                                                                                                    LinearLayout linearLayout7 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                                                    if (linearLayout7 != null) {
                                                                                                                                                        i = R.id.result_poster;
                                                                                                                                                        ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                        if (imageView4 != null) {
                                                                                                                                                            i = R.id.result_poster_background;
                                                                                                                                                            ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                            if (imageView5 != null) {
                                                                                                                                                                i = R.id.result_poster_background_holder;
                                                                                                                                                                FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                if (frameLayout != null) {
                                                                                                                                                                    i = R.id.result_poster_holder;
                                                                                                                                                                    CardView cardView = (CardView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                    if (cardView != null) {
                                                                                                                                                                        i = R.id.result_reload_connection_open_in_browser;
                                                                                                                                                                        MaterialButton materialButton8 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                        if (materialButton8 != null) {
                                                                                                                                                                            i = R.id.result_reload_connectionerror;
                                                                                                                                                                            MaterialButton materialButton9 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                            if (materialButton9 != null) {
                                                                                                                                                                                i = R.id.result_resume_parent;
                                                                                                                                                                                LinearLayout linearLayout8 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                if (linearLayout8 != null) {
                                                                                                                                                                                    i = R.id.result_resume_progress_holder;
                                                                                                                                                                                    FrameLayout frameLayout2 = (FrameLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                    if (frameLayout2 != null) {
                                                                                                                                                                                        i = R.id.result_resume_series_button;
                                                                                                                                                                                        MaterialButton materialButton10 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                        if (materialButton10 != null) {
                                                                                                                                                                                            i = R.id.result_resume_series_progress;
                                                                                                                                                                                            CircularProgressIndicator circularProgressIndicator = (CircularProgressIndicator) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                            if (circularProgressIndicator != null) {
                                                                                                                                                                                                i = R.id.result_resume_series_progress_text;
                                                                                                                                                                                                TextView textView15 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                if (textView15 != null) {
                                                                                                                                                                                                    i = R.id.result_resume_series_title;
                                                                                                                                                                                                    TextView textView16 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                    if (textView16 != null) {
                                                                                                                                                                                                        FrameLayout frameLayout3 = (FrameLayout) view;
                                                                                                                                                                                                        i = R.id.result_scroll;
                                                                                                                                                                                                        NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                        if (nestedScrollView != null) {
                                                                                                                                                                                                            i = R.id.result_season_button;
                                                                                                                                                                                                            MaterialButton materialButton11 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                            if (materialButton11 != null) {
                                                                                                                                                                                                                i = R.id.result_smallscreen_holder;
                                                                                                                                                                                                                FrameLayout frameLayout4 = (FrameLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                if (frameLayout4 != null) {
                                                                                                                                                                                                                    i = R.id.result_sort_button;
                                                                                                                                                                                                                    MaterialButton materialButton12 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                    if (materialButton12 != null) {
                                                                                                                                                                                                                        i = R.id.result_tag;
                                                                                                                                                                                                                        ChipGroup chipGroup = (ChipGroup) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                        if (chipGroup != null) {
                                                                                                                                                                                                                            i = R.id.result_tag_holder;
                                                                                                                                                                                                                            TextView textView17 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                            if (textView17 != null) {
                                                                                                                                                                                                                                i = R.id.result_title;
                                                                                                                                                                                                                                TextView textView18 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                if (textView18 != null) {
                                                                                                                                                                                                                                    i = R.id.result_top_holder;
                                                                                                                                                                                                                                    FrameLayout frameLayout5 = (FrameLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                    if (frameLayout5 != null) {
                                                                                                                                                                                                                                        i = R.id.result_vpn;
                                                                                                                                                                                                                                        TextView textView19 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                        if (textView19 != null) {
                                                                                                                                                                                                                                            i = R.id.watch_progress_container;
                                                                                                                                                                                                                                            FrameLayout frameLayout6 = (FrameLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                            if (frameLayout6 != null) {
                                                                                                                                                                                                                                                return new FragmentResultBinding(frameLayout3, imageView, downloadButton, imageView2, fragmentTrailerBindingBind, linearLayout, materialButton, imageView3, recyclerView, textView, textView2, linearLayout2, textView3, materialButton2, shimmerFrameLayout, materialButton3, recyclerView2, textView4, textView5, linearLayout3, textView6, shimmerFrameLayout2, linearLayout4, materialButton4, textView7, textView8, materialButton5, textView9, textView10, textView11, linearLayout5, textView12, linearLayout6, textView13, materialButton6, textView14, materialButton7, linearLayout7, imageView4, imageView5, frameLayout, cardView, materialButton8, materialButton9, linearLayout8, frameLayout2, materialButton10, circularProgressIndicator, textView15, textView16, frameLayout3, nestedScrollView, materialButton11, frameLayout4, materialButton12, chipGroup, textView17, textView18, frameLayout5, textView19, frameLayout6);
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
