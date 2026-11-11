package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.media3.ui.SubtitleView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class SubtitleSettingsBinding implements ViewBinding {
    public final MaterialButton applyBtt;
    public final MaterialButton cancelBtt;
    private final CoordinatorLayout rootView;
    public final TextView subsAutoSelectLanguage;
    public final TextView subsBackgroundColor;
    public final TextView subsBackgroundRadius;
    public final TextView subsDownloadLanguages;
    public final TextView subsEdgeSize;
    public final TextView subsEdgeType;
    public final TextView subsFont;
    public final TextView subsFontSize;
    public final TextView subsImportText;
    public final TextView subsOutlineColor;
    public final CoordinatorLayout subsRoot;
    public final TextView subsSubtitleAlignment;
    public final TextView subsSubtitleElevation;
    public final TextView subsTextColor;
    public final TextView subsWindowColor;
    public final SubtitleView subtitleText;
    public final SwitchMaterial subtitlesBold;
    public final SwitchMaterial subtitlesFilterSubLang;
    public final SwitchMaterial subtitlesItalic;
    public final SwitchMaterial subtitlesRemoveBloat;
    public final SwitchMaterial subtitlesRemoveCaptions;
    public final SwitchMaterial subtitlesUppercase;

    private SubtitleSettingsBinding(CoordinatorLayout coordinatorLayout, MaterialButton materialButton, MaterialButton materialButton2, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, CoordinatorLayout coordinatorLayout2, TextView textView11, TextView textView12, TextView textView13, TextView textView14, SubtitleView subtitleView, SwitchMaterial switchMaterial, SwitchMaterial switchMaterial2, SwitchMaterial switchMaterial3, SwitchMaterial switchMaterial4, SwitchMaterial switchMaterial5, SwitchMaterial switchMaterial6) {
        this.rootView = coordinatorLayout;
        this.applyBtt = materialButton;
        this.cancelBtt = materialButton2;
        this.subsAutoSelectLanguage = textView;
        this.subsBackgroundColor = textView2;
        this.subsBackgroundRadius = textView3;
        this.subsDownloadLanguages = textView4;
        this.subsEdgeSize = textView5;
        this.subsEdgeType = textView6;
        this.subsFont = textView7;
        this.subsFontSize = textView8;
        this.subsImportText = textView9;
        this.subsOutlineColor = textView10;
        this.subsRoot = coordinatorLayout2;
        this.subsSubtitleAlignment = textView11;
        this.subsSubtitleElevation = textView12;
        this.subsTextColor = textView13;
        this.subsWindowColor = textView14;
        this.subtitleText = subtitleView;
        this.subtitlesBold = switchMaterial;
        this.subtitlesFilterSubLang = switchMaterial2;
        this.subtitlesItalic = switchMaterial3;
        this.subtitlesRemoveBloat = switchMaterial4;
        this.subtitlesRemoveCaptions = switchMaterial5;
        this.subtitlesUppercase = switchMaterial6;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CoordinatorLayout getRoot() {
        return this.rootView;
    }

    public static SubtitleSettingsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static SubtitleSettingsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.subtitle_settings, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static SubtitleSettingsBinding bind(View view) {
        int i = R.id.apply_btt;
        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
        if (materialButton != null) {
            i = R.id.cancel_btt;
            MaterialButton materialButton2 = (MaterialButton) ViewBindings.findChildViewById(view, i);
            if (materialButton2 != null) {
                i = R.id.subs_auto_select_language;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView != null) {
                    i = R.id.subs_background_color;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView2 != null) {
                        i = R.id.subs_background_radius;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                        if (textView3 != null) {
                            i = R.id.subs_download_languages;
                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, i);
                            if (textView4 != null) {
                                i = R.id.subs_edge_size;
                                TextView textView5 = (TextView) ViewBindings.findChildViewById(view, i);
                                if (textView5 != null) {
                                    i = R.id.subs_edge_type;
                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(view, i);
                                    if (textView6 != null) {
                                        i = R.id.subs_font;
                                        TextView textView7 = (TextView) ViewBindings.findChildViewById(view, i);
                                        if (textView7 != null) {
                                            i = R.id.subs_font_size;
                                            TextView textView8 = (TextView) ViewBindings.findChildViewById(view, i);
                                            if (textView8 != null) {
                                                i = R.id.subs_import_text;
                                                TextView textView9 = (TextView) ViewBindings.findChildViewById(view, i);
                                                if (textView9 != null) {
                                                    i = R.id.subs_outline_color;
                                                    TextView textView10 = (TextView) ViewBindings.findChildViewById(view, i);
                                                    if (textView10 != null) {
                                                        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) view;
                                                        i = R.id.subs_subtitle_alignment;
                                                        TextView textView11 = (TextView) ViewBindings.findChildViewById(view, i);
                                                        if (textView11 != null) {
                                                            i = R.id.subs_subtitle_elevation;
                                                            TextView textView12 = (TextView) ViewBindings.findChildViewById(view, i);
                                                            if (textView12 != null) {
                                                                i = R.id.subs_text_color;
                                                                TextView textView13 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                if (textView13 != null) {
                                                                    i = R.id.subs_window_color;
                                                                    TextView textView14 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                    if (textView14 != null) {
                                                                        i = R.id.subtitle_text;
                                                                        SubtitleView subtitleView = (SubtitleView) ViewBindings.findChildViewById(view, i);
                                                                        if (subtitleView != null) {
                                                                            i = R.id.subtitles_bold;
                                                                            SwitchMaterial switchMaterial = (SwitchMaterial) ViewBindings.findChildViewById(view, i);
                                                                            if (switchMaterial != null) {
                                                                                i = R.id.subtitles_filter_sub_lang;
                                                                                SwitchMaterial switchMaterial2 = (SwitchMaterial) ViewBindings.findChildViewById(view, i);
                                                                                if (switchMaterial2 != null) {
                                                                                    i = R.id.subtitles_italic;
                                                                                    SwitchMaterial switchMaterial3 = (SwitchMaterial) ViewBindings.findChildViewById(view, i);
                                                                                    if (switchMaterial3 != null) {
                                                                                        i = R.id.subtitles_remove_bloat;
                                                                                        SwitchMaterial switchMaterial4 = (SwitchMaterial) ViewBindings.findChildViewById(view, i);
                                                                                        if (switchMaterial4 != null) {
                                                                                            i = R.id.subtitles_remove_captions;
                                                                                            SwitchMaterial switchMaterial5 = (SwitchMaterial) ViewBindings.findChildViewById(view, i);
                                                                                            if (switchMaterial5 != null) {
                                                                                                i = R.id.subtitles_uppercase;
                                                                                                SwitchMaterial switchMaterial6 = (SwitchMaterial) ViewBindings.findChildViewById(view, i);
                                                                                                if (switchMaterial6 != null) {
                                                                                                    return new SubtitleSettingsBinding(coordinatorLayout, materialButton, materialButton2, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, coordinatorLayout, textView11, textView12, textView13, textView14, subtitleView, switchMaterial, switchMaterial2, switchMaterial3, switchMaterial4, switchMaterial5, switchMaterial6);
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
