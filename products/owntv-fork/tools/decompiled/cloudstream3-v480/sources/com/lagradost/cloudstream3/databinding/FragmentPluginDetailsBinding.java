package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class FragmentPluginDetailsBinding implements ViewBinding {
    public final ImageView actionSettings;
    public final ImageView githubBtn;
    public final TextView pluginAuthor;
    public final TextView pluginDescription;
    public final ImageView pluginIcon;
    public final TextView pluginLang;
    public final TextView pluginName;
    public final TextView pluginSize;
    public final TextView pluginStatus;
    public final TextView pluginTypes;
    public final TextView pluginVersion;
    public final TextView pluginVotes;
    private final LinearLayout rootView;
    public final ImageView upvote;

    private FragmentPluginDetailsBinding(LinearLayout linearLayout, ImageView imageView, ImageView imageView2, TextView textView, TextView textView2, ImageView imageView3, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, ImageView imageView4) {
        this.rootView = linearLayout;
        this.actionSettings = imageView;
        this.githubBtn = imageView2;
        this.pluginAuthor = textView;
        this.pluginDescription = textView2;
        this.pluginIcon = imageView3;
        this.pluginLang = textView3;
        this.pluginName = textView4;
        this.pluginSize = textView5;
        this.pluginStatus = textView6;
        this.pluginTypes = textView7;
        this.pluginVersion = textView8;
        this.pluginVotes = textView9;
        this.upvote = imageView4;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentPluginDetailsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentPluginDetailsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_plugin_details, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentPluginDetailsBinding bind(View view) {
        int i = R.id.action_settings;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.github_btn;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
            if (imageView2 != null) {
                i = R.id.plugin_author;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView != null) {
                    i = R.id.plugin_description;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView2 != null) {
                        i = R.id.plugin_icon;
                        ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, i);
                        if (imageView3 != null) {
                            i = R.id.plugin_lang;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                            if (textView3 != null) {
                                i = R.id.plugin_name;
                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, i);
                                if (textView4 != null) {
                                    i = R.id.plugin_size;
                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(view, i);
                                    if (textView5 != null) {
                                        i = R.id.plugin_status;
                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(view, i);
                                        if (textView6 != null) {
                                            i = R.id.plugin_types;
                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(view, i);
                                            if (textView7 != null) {
                                                i = R.id.plugin_version;
                                                TextView textView8 = (TextView) ViewBindings.findChildViewById(view, i);
                                                if (textView8 != null) {
                                                    i = R.id.plugin_votes;
                                                    TextView textView9 = (TextView) ViewBindings.findChildViewById(view, i);
                                                    if (textView9 != null) {
                                                        i = R.id.upvote;
                                                        ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                        if (imageView4 != null) {
                                                            return new FragmentPluginDetailsBinding((LinearLayout) view, imageView, imageView2, textView, textView2, imageView3, textView3, textView4, textView5, textView6, textView7, textView8, textView9, imageView4);
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
