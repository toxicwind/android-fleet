package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class PlayerSelectSourceAndSubsBinding implements ViewBinding {
    public final MaterialButton applyBtt;
    public final LinearLayout applyBttHolder;
    public final MaterialButton cancelBtt;
    public final LinearLayout profilesClickSettings;
    private final LinearLayout rootView;
    public final ListView sortProviders;
    public final LinearLayout sortSourcesHolder;
    public final ListView sortSubtitles;
    public final LinearLayout sortSubtitlesHolder;
    public final ListView sortSubtitlesOptions;
    public final TextView sourceSettingsBtt;
    public final ImageView subtitleSettingsBtt;
    public final TextView subtitlesEncodingFormat;
    public final TextView subtitlesText;

    private PlayerSelectSourceAndSubsBinding(LinearLayout linearLayout, MaterialButton materialButton, LinearLayout linearLayout2, MaterialButton materialButton2, LinearLayout linearLayout3, ListView listView, LinearLayout linearLayout4, ListView listView2, LinearLayout linearLayout5, ListView listView3, TextView textView, ImageView imageView, TextView textView2, TextView textView3) {
        this.rootView = linearLayout;
        this.applyBtt = materialButton;
        this.applyBttHolder = linearLayout2;
        this.cancelBtt = materialButton2;
        this.profilesClickSettings = linearLayout3;
        this.sortProviders = listView;
        this.sortSourcesHolder = linearLayout4;
        this.sortSubtitles = listView2;
        this.sortSubtitlesHolder = linearLayout5;
        this.sortSubtitlesOptions = listView3;
        this.sourceSettingsBtt = textView;
        this.subtitleSettingsBtt = imageView;
        this.subtitlesEncodingFormat = textView2;
        this.subtitlesText = textView3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static PlayerSelectSourceAndSubsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PlayerSelectSourceAndSubsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.player_select_source_and_subs, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static PlayerSelectSourceAndSubsBinding bind(View view) {
        int i = R.id.apply_btt;
        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
        if (materialButton != null) {
            i = R.id.apply_btt_holder;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
            if (linearLayout != null) {
                i = R.id.cancel_btt;
                MaterialButton materialButton2 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                if (materialButton2 != null) {
                    i = R.id.profiles_click_settings;
                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                    if (linearLayout2 != null) {
                        i = R.id.sort_providers;
                        ListView listView = (ListView) ViewBindings.findChildViewById(view, i);
                        if (listView != null) {
                            i = R.id.sort_sources_holder;
                            LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                            if (linearLayout3 != null) {
                                i = R.id.sort_subtitles;
                                ListView listView2 = (ListView) ViewBindings.findChildViewById(view, i);
                                if (listView2 != null) {
                                    i = R.id.sort_subtitles_holder;
                                    LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                    if (linearLayout4 != null) {
                                        i = R.id.sort_subtitles_options;
                                        ListView listView3 = (ListView) ViewBindings.findChildViewById(view, i);
                                        if (listView3 != null) {
                                            i = R.id.source_settings_btt;
                                            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                                            if (textView != null) {
                                                i = R.id.subtitle_settings_btt;
                                                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                                                if (imageView != null) {
                                                    i = R.id.subtitles_encoding_format;
                                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                                                    if (textView2 != null) {
                                                        i = R.id.subtitles_text;
                                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                                                        if (textView3 != null) {
                                                            return new PlayerSelectSourceAndSubsBinding((LinearLayout) view, materialButton, linearLayout, materialButton2, linearLayout2, listView, linearLayout3, listView2, linearLayout4, listView3, textView, imageView, textView2, textView3);
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
