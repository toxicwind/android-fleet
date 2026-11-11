package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class PlayerSelectSourcePriorityBinding implements ViewBinding {
    public final LinearLayout applyBttHolder;
    public final MaterialButton closeBtt;
    public final ImageView helpBtt;
    public final EditText profileTextEditable;
    private final LinearLayout rootView;
    public final MaterialButton saveBtt;
    public final RecyclerView sortQualities;
    public final RecyclerView sortSources;
    public final LinearLayout sortSourcesHolder;
    public final LinearLayout sortSubtitlesHolder;
    public final LinearLayout subtitlesClickSettings;

    private PlayerSelectSourcePriorityBinding(LinearLayout linearLayout, LinearLayout linearLayout2, MaterialButton materialButton, ImageView imageView, EditText editText, MaterialButton materialButton2, RecyclerView recyclerView, RecyclerView recyclerView2, LinearLayout linearLayout3, LinearLayout linearLayout4, LinearLayout linearLayout5) {
        this.rootView = linearLayout;
        this.applyBttHolder = linearLayout2;
        this.closeBtt = materialButton;
        this.helpBtt = imageView;
        this.profileTextEditable = editText;
        this.saveBtt = materialButton2;
        this.sortQualities = recyclerView;
        this.sortSources = recyclerView2;
        this.sortSourcesHolder = linearLayout3;
        this.sortSubtitlesHolder = linearLayout4;
        this.subtitlesClickSettings = linearLayout5;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static PlayerSelectSourcePriorityBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PlayerSelectSourcePriorityBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.player_select_source_priority, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static PlayerSelectSourcePriorityBinding bind(View view) {
        int i = R.id.apply_btt_holder;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
        if (linearLayout != null) {
            i = R.id.close_btt;
            MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
            if (materialButton != null) {
                i = R.id.help_btt;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                if (imageView != null) {
                    i = R.id.profile_text_editable;
                    EditText editText = (EditText) ViewBindings.findChildViewById(view, i);
                    if (editText != null) {
                        i = R.id.save_btt;
                        MaterialButton materialButton2 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                        if (materialButton2 != null) {
                            i = R.id.sort_qualities;
                            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                            if (recyclerView != null) {
                                i = R.id.sort_sources;
                                RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(view, i);
                                if (recyclerView2 != null) {
                                    i = R.id.sort_sources_holder;
                                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                    if (linearLayout2 != null) {
                                        i = R.id.sort_subtitles_holder;
                                        LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                        if (linearLayout3 != null) {
                                            i = R.id.subtitles_click_settings;
                                            LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                            if (linearLayout4 != null) {
                                                return new PlayerSelectSourcePriorityBinding((LinearLayout) view, linearLayout, materialButton, imageView, editText, materialButton2, recyclerView, recyclerView2, linearLayout2, linearLayout3, linearLayout4);
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
