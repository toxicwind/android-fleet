package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class RepositoryItemBinding implements ViewBinding {
    public final ImageView actionButton;
    public final ImageView actionSettings;
    public final ImageView entryIcon;
    public final TextView extFilesize;
    public final TextView extVersion;
    public final TextView extVotes;
    public final TextView langIcon;
    public final TextView mainText;
    public final TextView nsfwMarker;
    public final LinearLayout repositoryItemRoot;
    public final MaterialButton repositoryNameText;
    private final LinearLayout rootView;
    public final TextView subText;

    private RepositoryItemBinding(LinearLayout linearLayout, ImageView imageView, ImageView imageView2, ImageView imageView3, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, LinearLayout linearLayout2, MaterialButton materialButton, TextView textView7) {
        this.rootView = linearLayout;
        this.actionButton = imageView;
        this.actionSettings = imageView2;
        this.entryIcon = imageView3;
        this.extFilesize = textView;
        this.extVersion = textView2;
        this.extVotes = textView3;
        this.langIcon = textView4;
        this.mainText = textView5;
        this.nsfwMarker = textView6;
        this.repositoryItemRoot = linearLayout2;
        this.repositoryNameText = materialButton;
        this.subText = textView7;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static RepositoryItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static RepositoryItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.repository_item, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RepositoryItemBinding bind(View view) {
        int i = R.id.action_button;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.action_settings;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
            if (imageView2 != null) {
                i = R.id.entry_icon;
                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, i);
                if (imageView3 != null) {
                    i = R.id.ext_filesize;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView != null) {
                        i = R.id.ext_version;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                        if (textView2 != null) {
                            i = R.id.ext_votes;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                            if (textView3 != null) {
                                i = R.id.lang_icon;
                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, i);
                                if (textView4 != null) {
                                    i = R.id.main_text;
                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(view, i);
                                    if (textView5 != null) {
                                        i = R.id.nsfw_marker;
                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(view, i);
                                        if (textView6 != null) {
                                            LinearLayout linearLayout = (LinearLayout) view;
                                            i = R.id.repository_name_text;
                                            MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
                                            if (materialButton != null) {
                                                i = R.id.sub_text;
                                                TextView textView7 = (TextView) ViewBindings.findChildViewById(view, i);
                                                if (textView7 != null) {
                                                    return new RepositoryItemBinding(linearLayout, imageView, imageView2, imageView3, textView, textView2, textView3, textView4, textView5, textView6, linearLayout, materialButton, textView7);
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
