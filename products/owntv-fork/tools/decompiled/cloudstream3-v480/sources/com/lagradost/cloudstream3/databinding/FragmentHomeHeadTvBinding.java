package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class FragmentHomeHeadTvBinding implements ViewBinding {
    public final ImageView homeBackgroundPosterWatermarkBadgeHolder;
    public final ImageView homeBookmarkParentItemMoreInfo;
    public final FrameLayout homeBookmarkParentItemTitle;
    public final RecyclerView homeBookmarkedChildRecyclerview;
    public final LinearLayout homeBookmarkedHolder;
    public final LinearLayout homeHeader;
    public final View homeNonePadding;
    public final Chip homePlanToWatchBtt;
    public final TextView homePreviewCast;
    public final TextView homePreviewDescription;
    public final TextView homePreviewDuration;
    public final View homePreviewHiddenNextFocus;
    public final View homePreviewHiddenPrevFocus;
    public final CardView homePreviewInfoBtt;
    public final TextView homePreviewScore;
    public final ChipGroup homePreviewTags;
    public final TextView homePreviewText;
    public final ViewPager2 homePreviewViewpager;
    public final LinearLayout homePreviewViewpagerText;
    public final TextView homePreviewYear;
    public final Chip homeTypeCompletedBtt;
    public final Chip homeTypeDroppedBtt;
    public final ChipGroup homeTypeHolder;
    public final Chip homeTypeOnHoldBtt;
    public final Chip homeTypeWatchingBtt;
    public final RecyclerView homeWatchChildRecyclerview;
    public final LinearLayout homeWatchHolder;
    public final TextView homeWatchParentItemTitle;
    public final HorizontalScrollView horizontalScrollChips;
    private final LinearLayout rootView;

    private FragmentHomeHeadTvBinding(LinearLayout linearLayout, ImageView imageView, ImageView imageView2, FrameLayout frameLayout, RecyclerView recyclerView, LinearLayout linearLayout2, LinearLayout linearLayout3, View view, Chip chip, TextView textView, TextView textView2, TextView textView3, View view2, View view3, CardView cardView, TextView textView4, ChipGroup chipGroup, TextView textView5, ViewPager2 viewPager2, LinearLayout linearLayout4, TextView textView6, Chip chip2, Chip chip3, ChipGroup chipGroup2, Chip chip4, Chip chip5, RecyclerView recyclerView2, LinearLayout linearLayout5, TextView textView7, HorizontalScrollView horizontalScrollView) {
        this.rootView = linearLayout;
        this.homeBackgroundPosterWatermarkBadgeHolder = imageView;
        this.homeBookmarkParentItemMoreInfo = imageView2;
        this.homeBookmarkParentItemTitle = frameLayout;
        this.homeBookmarkedChildRecyclerview = recyclerView;
        this.homeBookmarkedHolder = linearLayout2;
        this.homeHeader = linearLayout3;
        this.homeNonePadding = view;
        this.homePlanToWatchBtt = chip;
        this.homePreviewCast = textView;
        this.homePreviewDescription = textView2;
        this.homePreviewDuration = textView3;
        this.homePreviewHiddenNextFocus = view2;
        this.homePreviewHiddenPrevFocus = view3;
        this.homePreviewInfoBtt = cardView;
        this.homePreviewScore = textView4;
        this.homePreviewTags = chipGroup;
        this.homePreviewText = textView5;
        this.homePreviewViewpager = viewPager2;
        this.homePreviewViewpagerText = linearLayout4;
        this.homePreviewYear = textView6;
        this.homeTypeCompletedBtt = chip2;
        this.homeTypeDroppedBtt = chip3;
        this.homeTypeHolder = chipGroup2;
        this.homeTypeOnHoldBtt = chip4;
        this.homeTypeWatchingBtt = chip5;
        this.homeWatchChildRecyclerview = recyclerView2;
        this.homeWatchHolder = linearLayout5;
        this.homeWatchParentItemTitle = textView7;
        this.horizontalScrollChips = horizontalScrollView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentHomeHeadTvBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentHomeHeadTvBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_home_head_tv, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentHomeHeadTvBinding bind(View view) {
        View viewFindChildViewById;
        View viewFindChildViewById2;
        int i = R.id.home_background_poster_watermark_badge_holder;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.home_bookmark_parent_item_more_info;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
            if (imageView2 != null) {
                i = R.id.home_bookmark_parent_item_title;
                FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
                if (frameLayout != null) {
                    i = R.id.home_bookmarked_child_recyclerview;
                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                    if (recyclerView != null) {
                        i = R.id.home_bookmarked_holder;
                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
                        if (linearLayout != null) {
                            LinearLayout linearLayout2 = (LinearLayout) view;
                            i = R.id.home_none_padding;
                            View viewFindChildViewById3 = ViewBindings.findChildViewById(view, i);
                            if (viewFindChildViewById3 != null) {
                                i = R.id.home_plan_to_watch_btt;
                                Chip chip = (Chip) ViewBindings.findChildViewById(view, i);
                                if (chip != null) {
                                    i = R.id.home_preview_cast;
                                    TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                                    if (textView != null) {
                                        i = R.id.home_preview_description;
                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                                        if (textView2 != null) {
                                            i = R.id.home_preview_duration;
                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                                            if (textView3 != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = R.id.home_preview_hidden_next_focus))) != null && (viewFindChildViewById2 = ViewBindings.findChildViewById(view, (i = R.id.home_preview_hidden_prev_focus))) != null) {
                                                i = R.id.home_preview_info_btt;
                                                CardView cardView = (CardView) ViewBindings.findChildViewById(view, i);
                                                if (cardView != null) {
                                                    i = R.id.home_preview_score;
                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, i);
                                                    if (textView4 != null) {
                                                        i = R.id.home_preview_tags;
                                                        ChipGroup chipGroup = (ChipGroup) ViewBindings.findChildViewById(view, i);
                                                        if (chipGroup != null) {
                                                            i = R.id.home_preview_text;
                                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, i);
                                                            if (textView5 != null) {
                                                                i = R.id.home_preview_viewpager;
                                                                ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(view, i);
                                                                if (viewPager2 != null) {
                                                                    i = R.id.home_preview_viewpager_text;
                                                                    LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                                    if (linearLayout3 != null) {
                                                                        i = R.id.home_preview_year;
                                                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                        if (textView6 != null) {
                                                                            i = R.id.home_type_completed_btt;
                                                                            Chip chip2 = (Chip) ViewBindings.findChildViewById(view, i);
                                                                            if (chip2 != null) {
                                                                                i = R.id.home_type_dropped_btt;
                                                                                Chip chip3 = (Chip) ViewBindings.findChildViewById(view, i);
                                                                                if (chip3 != null) {
                                                                                    i = R.id.home_type_holder;
                                                                                    ChipGroup chipGroup2 = (ChipGroup) ViewBindings.findChildViewById(view, i);
                                                                                    if (chipGroup2 != null) {
                                                                                        i = R.id.home_type_on_hold_btt;
                                                                                        Chip chip4 = (Chip) ViewBindings.findChildViewById(view, i);
                                                                                        if (chip4 != null) {
                                                                                            i = R.id.home_type_watching_btt;
                                                                                            Chip chip5 = (Chip) ViewBindings.findChildViewById(view, i);
                                                                                            if (chip5 != null) {
                                                                                                i = R.id.home_watch_child_recyclerview;
                                                                                                RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(view, i);
                                                                                                if (recyclerView2 != null) {
                                                                                                    i = R.id.home_watch_holder;
                                                                                                    LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                                                                    if (linearLayout4 != null) {
                                                                                                        i = R.id.home_watch_parent_item_title;
                                                                                                        TextView textView7 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                        if (textView7 != null) {
                                                                                                            i = R.id.horizontal_scroll_chips;
                                                                                                            HorizontalScrollView horizontalScrollView = (HorizontalScrollView) ViewBindings.findChildViewById(view, i);
                                                                                                            if (horizontalScrollView != null) {
                                                                                                                return new FragmentHomeHeadTvBinding(linearLayout2, imageView, imageView2, frameLayout, recyclerView, linearLayout, linearLayout2, viewFindChildViewById3, chip, textView, textView2, textView3, viewFindChildViewById, viewFindChildViewById2, cardView, textView4, chipGroup, textView5, viewPager2, linearLayout3, textView6, chip2, chip3, chipGroup2, chip4, chip5, recyclerView2, linearLayout4, textView7, horizontalScrollView);
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
