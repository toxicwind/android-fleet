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
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class ChromecastSubtitleSettingsBinding implements ViewBinding {
    public final MaterialButton applyBtt;
    public final MaterialButton cancelBtt;
    private final CoordinatorLayout rootView;
    public final TextView subsBackgroundColor;
    public final TextView subsEdgeType;
    public final TextView subsFont;
    public final TextView subsFontSize;
    public final TextView subsOutlineColor;
    public final CoordinatorLayout subsRoot;
    public final TextView subsTextColor;
    public final TextView subsWindowColor;
    public final SubtitleView subtitleText;

    private ChromecastSubtitleSettingsBinding(CoordinatorLayout coordinatorLayout, MaterialButton materialButton, MaterialButton materialButton2, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, CoordinatorLayout coordinatorLayout2, TextView textView6, TextView textView7, SubtitleView subtitleView) {
        this.rootView = coordinatorLayout;
        this.applyBtt = materialButton;
        this.cancelBtt = materialButton2;
        this.subsBackgroundColor = textView;
        this.subsEdgeType = textView2;
        this.subsFont = textView3;
        this.subsFontSize = textView4;
        this.subsOutlineColor = textView5;
        this.subsRoot = coordinatorLayout2;
        this.subsTextColor = textView6;
        this.subsWindowColor = textView7;
        this.subtitleText = subtitleView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CoordinatorLayout getRoot() {
        return this.rootView;
    }

    public static ChromecastSubtitleSettingsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ChromecastSubtitleSettingsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.chromecast_subtitle_settings, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ChromecastSubtitleSettingsBinding bind(View view) {
        int i = R.id.apply_btt;
        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
        if (materialButton != null) {
            i = R.id.cancel_btt;
            MaterialButton materialButton2 = (MaterialButton) ViewBindings.findChildViewById(view, i);
            if (materialButton2 != null) {
                i = R.id.subs_background_color;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView != null) {
                    i = R.id.subs_edge_type;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView2 != null) {
                        i = R.id.subs_font;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                        if (textView3 != null) {
                            i = R.id.subs_font_size;
                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, i);
                            if (textView4 != null) {
                                i = R.id.subs_outline_color;
                                TextView textView5 = (TextView) ViewBindings.findChildViewById(view, i);
                                if (textView5 != null) {
                                    CoordinatorLayout coordinatorLayout = (CoordinatorLayout) view;
                                    i = R.id.subs_text_color;
                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(view, i);
                                    if (textView6 != null) {
                                        i = R.id.subs_window_color;
                                        TextView textView7 = (TextView) ViewBindings.findChildViewById(view, i);
                                        if (textView7 != null) {
                                            i = R.id.subtitle_text;
                                            SubtitleView subtitleView = (SubtitleView) ViewBindings.findChildViewById(view, i);
                                            if (subtitleView != null) {
                                                return new ChromecastSubtitleSettingsBinding(coordinatorLayout, materialButton, materialButton2, textView, textView2, textView3, textView4, textView5, coordinatorLayout, textView6, textView7, subtitleView);
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
