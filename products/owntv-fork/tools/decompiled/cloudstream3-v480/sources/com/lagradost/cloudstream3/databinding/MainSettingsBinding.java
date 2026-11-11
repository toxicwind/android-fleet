package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class MainSettingsBinding implements ViewBinding {
    public final TextView appVersion;
    public final LinearLayout appVersionInfo;
    public final TextView buildDate;
    public final TextView commitHash;
    public final TextView delimiter0;
    public final TextView delimiter1;
    private final ConstraintLayout rootView;
    public final TextView settingsCredits;
    public final TextView settingsExtensions;
    public final TextView settingsGeneral;
    public final TextView settingsPlayer;
    public final LinearLayout settingsProfile;
    public final ImageView settingsProfilePic;
    public final TextView settingsProfileText;
    public final TextView settingsProviders;
    public final TextView settingsUi;
    public final TextView settingsUpdates;

    private MainSettingsBinding(ConstraintLayout constraintLayout, TextView textView, LinearLayout linearLayout, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, LinearLayout linearLayout2, ImageView imageView, TextView textView10, TextView textView11, TextView textView12, TextView textView13) {
        this.rootView = constraintLayout;
        this.appVersion = textView;
        this.appVersionInfo = linearLayout;
        this.buildDate = textView2;
        this.commitHash = textView3;
        this.delimiter0 = textView4;
        this.delimiter1 = textView5;
        this.settingsCredits = textView6;
        this.settingsExtensions = textView7;
        this.settingsGeneral = textView8;
        this.settingsPlayer = textView9;
        this.settingsProfile = linearLayout2;
        this.settingsProfilePic = imageView;
        this.settingsProfileText = textView10;
        this.settingsProviders = textView11;
        this.settingsUi = textView12;
        this.settingsUpdates = textView13;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static MainSettingsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static MainSettingsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.main_settings, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static MainSettingsBinding bind(View view) {
        int i = R.id.app_version;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
        if (textView != null) {
            i = R.id.app_version_info;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
            if (linearLayout != null) {
                i = R.id.build_date;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView2 != null) {
                    i = R.id.commit_hash;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView3 != null) {
                        i = R.id.delimiter0;
                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, i);
                        if (textView4 != null) {
                            i = R.id.delimiter1;
                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, i);
                            if (textView5 != null) {
                                i = R.id.settings_credits;
                                TextView textView6 = (TextView) ViewBindings.findChildViewById(view, i);
                                if (textView6 != null) {
                                    i = R.id.settings_extensions;
                                    TextView textView7 = (TextView) ViewBindings.findChildViewById(view, i);
                                    if (textView7 != null) {
                                        i = R.id.settings_general;
                                        TextView textView8 = (TextView) ViewBindings.findChildViewById(view, i);
                                        if (textView8 != null) {
                                            i = R.id.settings_player;
                                            TextView textView9 = (TextView) ViewBindings.findChildViewById(view, i);
                                            if (textView9 != null) {
                                                i = R.id.settings_profile;
                                                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                if (linearLayout2 != null) {
                                                    i = R.id.settings_profile_pic;
                                                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                                                    if (imageView != null) {
                                                        i = R.id.settings_profile_text;
                                                        TextView textView10 = (TextView) ViewBindings.findChildViewById(view, i);
                                                        if (textView10 != null) {
                                                            i = R.id.settings_providers;
                                                            TextView textView11 = (TextView) ViewBindings.findChildViewById(view, i);
                                                            if (textView11 != null) {
                                                                i = R.id.settings_ui;
                                                                TextView textView12 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                if (textView12 != null) {
                                                                    i = R.id.settings_updates;
                                                                    TextView textView13 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                    if (textView13 != null) {
                                                                        return new MainSettingsBinding((ConstraintLayout) view, textView, linearLayout, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, linearLayout2, imageView, textView10, textView11, textView12, textView13);
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
