package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.appcompat.widget.SearchView;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class DialogOnlineSubtitlesBinding implements ViewBinding {
    public final MaterialButton applyBtt;
    public final LinearLayout applyBttHolder;
    public final MaterialButton cancelBtt;
    private final LinearLayout rootView;
    public final ImageView searchFilter;
    public final ContentLoadingProgressBar searchLoadingBar;
    public final LinearLayout sortSubtitlesHolder;
    public final ListView subtitleAdapter;
    public final SearchView subtitlesSearch;
    public final MaterialButton yearBtt;

    private DialogOnlineSubtitlesBinding(LinearLayout linearLayout, MaterialButton materialButton, LinearLayout linearLayout2, MaterialButton materialButton2, ImageView imageView, ContentLoadingProgressBar contentLoadingProgressBar, LinearLayout linearLayout3, ListView listView, SearchView searchView, MaterialButton materialButton3) {
        this.rootView = linearLayout;
        this.applyBtt = materialButton;
        this.applyBttHolder = linearLayout2;
        this.cancelBtt = materialButton2;
        this.searchFilter = imageView;
        this.searchLoadingBar = contentLoadingProgressBar;
        this.sortSubtitlesHolder = linearLayout3;
        this.subtitleAdapter = listView;
        this.subtitlesSearch = searchView;
        this.yearBtt = materialButton3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static DialogOnlineSubtitlesBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogOnlineSubtitlesBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.dialog_online_subtitles, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogOnlineSubtitlesBinding bind(View view) {
        int i = R.id.apply_btt;
        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
        if (materialButton != null) {
            i = R.id.apply_btt_holder;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
            if (linearLayout != null) {
                i = R.id.cancel_btt;
                MaterialButton materialButton2 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                if (materialButton2 != null) {
                    i = R.id.search_filter;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                    if (imageView != null) {
                        i = R.id.search_loading_bar;
                        ContentLoadingProgressBar contentLoadingProgressBar = (ContentLoadingProgressBar) ViewBindings.findChildViewById(view, i);
                        if (contentLoadingProgressBar != null) {
                            i = R.id.sort_subtitles_holder;
                            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                            if (linearLayout2 != null) {
                                i = R.id.subtitle_adapter;
                                ListView listView = (ListView) ViewBindings.findChildViewById(view, i);
                                if (listView != null) {
                                    i = R.id.subtitles_search;
                                    SearchView searchView = (SearchView) ViewBindings.findChildViewById(view, i);
                                    if (searchView != null) {
                                        i = R.id.year_btt;
                                        MaterialButton materialButton3 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                                        if (materialButton3 != null) {
                                            return new DialogOnlineSubtitlesBinding((LinearLayout) view, materialButton, linearLayout, materialButton2, imageView, contentLoadingProgressBar, linearLayout2, listView, searchView, materialButton3);
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
