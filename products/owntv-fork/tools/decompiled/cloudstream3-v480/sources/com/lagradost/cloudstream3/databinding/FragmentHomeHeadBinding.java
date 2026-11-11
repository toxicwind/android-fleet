package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class FragmentHomeHeadBinding implements ViewBinding {
    public final CardView alternateHomeHeadProfileCard;
    public final LinearLayout alternateHomeHeadProfilePadding;
    public final ImageView alternateHomeHeadProfilePic;
    public final LinearLayout alternativeAccountPadding;
    public final FrameLayout homeBookmarkParentItemTitle;
    public final RecyclerView homeBookmarkedChildRecyclerview;
    public final LinearLayout homeBookmarkedHolder;
    public final CardView homeHeadProfileCard;
    public final LinearLayout homeHeadProfilePadding;
    public final ImageView homeHeadProfilePic;
    public final LinearLayout homeHeader;
    public final View homeNonePadding;
    public final LinearLayout homePadding;
    public final Chip homePlanToWatchBtt;
    public final TextView homePreviewBookmark;
    public final TextView homePreviewInfo;
    public final MaterialButton homePreviewPlay;
    public final LinearLayout homePreviewTitleHolder;
    public final ViewPager2 homePreviewViewpager;
    public final FrameLayout homePreviewViewpagerText;
    public final SearchView homeSearch;
    public final Chip homeTypeCompletedBtt;
    public final Chip homeTypeDroppedBtt;
    public final Chip homeTypeOnHoldBtt;
    public final Chip homeTypeWatchingBtt;
    public final RecyclerView homeWatchChildRecyclerview;
    public final LinearLayout homeWatchHolder;
    public final TextView homeWatchParentItemTitle;
    private final LinearLayout rootView;

    private FragmentHomeHeadBinding(LinearLayout linearLayout, CardView cardView, LinearLayout linearLayout2, ImageView imageView, LinearLayout linearLayout3, FrameLayout frameLayout, RecyclerView recyclerView, LinearLayout linearLayout4, CardView cardView2, LinearLayout linearLayout5, ImageView imageView2, LinearLayout linearLayout6, View view, LinearLayout linearLayout7, Chip chip, TextView textView, TextView textView2, MaterialButton materialButton, LinearLayout linearLayout8, ViewPager2 viewPager2, FrameLayout frameLayout2, SearchView searchView, Chip chip2, Chip chip3, Chip chip4, Chip chip5, RecyclerView recyclerView2, LinearLayout linearLayout9, TextView textView3) {
        this.rootView = linearLayout;
        this.alternateHomeHeadProfileCard = cardView;
        this.alternateHomeHeadProfilePadding = linearLayout2;
        this.alternateHomeHeadProfilePic = imageView;
        this.alternativeAccountPadding = linearLayout3;
        this.homeBookmarkParentItemTitle = frameLayout;
        this.homeBookmarkedChildRecyclerview = recyclerView;
        this.homeBookmarkedHolder = linearLayout4;
        this.homeHeadProfileCard = cardView2;
        this.homeHeadProfilePadding = linearLayout5;
        this.homeHeadProfilePic = imageView2;
        this.homeHeader = linearLayout6;
        this.homeNonePadding = view;
        this.homePadding = linearLayout7;
        this.homePlanToWatchBtt = chip;
        this.homePreviewBookmark = textView;
        this.homePreviewInfo = textView2;
        this.homePreviewPlay = materialButton;
        this.homePreviewTitleHolder = linearLayout8;
        this.homePreviewViewpager = viewPager2;
        this.homePreviewViewpagerText = frameLayout2;
        this.homeSearch = searchView;
        this.homeTypeCompletedBtt = chip2;
        this.homeTypeDroppedBtt = chip3;
        this.homeTypeOnHoldBtt = chip4;
        this.homeTypeWatchingBtt = chip5;
        this.homeWatchChildRecyclerview = recyclerView2;
        this.homeWatchHolder = linearLayout9;
        this.homeWatchParentItemTitle = textView3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentHomeHeadBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentHomeHeadBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_home_head, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentHomeHeadBinding bind(View view) {
        int i = R.id.alternate_home_head_profile_card;
        CardView cardView = (CardView) ViewBindings.findChildViewById(view, i);
        if (cardView != null) {
            i = R.id.alternate_home_head_profile_padding;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
            if (linearLayout != null) {
                i = R.id.alternate_home_head_profile_pic;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                if (imageView != null) {
                    i = R.id.alternative_account_padding;
                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                    if (linearLayout2 != null) {
                        i = R.id.home_bookmark_parent_item_title;
                        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
                        if (frameLayout != null) {
                            i = R.id.home_bookmarked_child_recyclerview;
                            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                            if (recyclerView != null) {
                                i = R.id.home_bookmarked_holder;
                                LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                if (linearLayout3 != null) {
                                    i = R.id.home_head_profile_card;
                                    CardView cardView2 = (CardView) ViewBindings.findChildViewById(view, i);
                                    if (cardView2 != null) {
                                        i = R.id.home_head_profile_padding;
                                        LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                        if (linearLayout4 != null) {
                                            i = R.id.home_head_profile_pic;
                                            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
                                            if (imageView2 != null) {
                                                LinearLayout linearLayout5 = (LinearLayout) view;
                                                i = R.id.home_none_padding;
                                                View viewFindChildViewById = ViewBindings.findChildViewById(view, i);
                                                if (viewFindChildViewById != null) {
                                                    i = R.id.home_padding;
                                                    LinearLayout linearLayout6 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                    if (linearLayout6 != null) {
                                                        i = R.id.home_plan_to_watch_btt;
                                                        Chip chip = (Chip) ViewBindings.findChildViewById(view, i);
                                                        if (chip != null) {
                                                            i = R.id.home_preview_bookmark;
                                                            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                                                            if (textView != null) {
                                                                i = R.id.home_preview_info;
                                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                if (textView2 != null) {
                                                                    i = R.id.home_preview_play;
                                                                    MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
                                                                    if (materialButton != null) {
                                                                        i = R.id.home_preview_title_holder;
                                                                        LinearLayout linearLayout7 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                                        if (linearLayout7 != null) {
                                                                            i = R.id.home_preview_viewpager;
                                                                            ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(view, i);
                                                                            if (viewPager2 != null) {
                                                                                i = R.id.home_preview_viewpager_text;
                                                                                FrameLayout frameLayout2 = (FrameLayout) ViewBindings.findChildViewById(view, i);
                                                                                if (frameLayout2 != null) {
                                                                                    i = R.id.home_search;
                                                                                    SearchView searchView = (SearchView) ViewBindings.findChildViewById(view, i);
                                                                                    if (searchView != null) {
                                                                                        i = R.id.home_type_completed_btt;
                                                                                        Chip chip2 = (Chip) ViewBindings.findChildViewById(view, i);
                                                                                        if (chip2 != null) {
                                                                                            i = R.id.home_type_dropped_btt;
                                                                                            Chip chip3 = (Chip) ViewBindings.findChildViewById(view, i);
                                                                                            if (chip3 != null) {
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
                                                                                                            LinearLayout linearLayout8 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                                                                            if (linearLayout8 != null) {
                                                                                                                i = R.id.home_watch_parent_item_title;
                                                                                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                if (textView3 != null) {
                                                                                                                    return new FragmentHomeHeadBinding(linearLayout5, cardView, linearLayout, imageView, linearLayout2, frameLayout, recyclerView, linearLayout3, cardView2, linearLayout4, imageView2, linearLayout5, viewFindChildViewById, linearLayout6, chip, textView, textView2, materialButton, linearLayout7, viewPager2, frameLayout2, searchView, chip2, chip3, chip4, chip5, recyclerView2, linearLayout8, textView3);
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
