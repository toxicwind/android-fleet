package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class SubtitleOffsetBinding implements ViewBinding {
    public final MaterialButton applyBtt;
    public final MaterialButton cancelBtt;
    public final TextView noSubtitlesLoadedNotice;
    public final MaterialButton resetBtt;
    private final LinearLayout rootView;
    public final ImageView subtitleOffsetAdd;
    public final ImageView subtitleOffsetAddMore;
    public final EditText subtitleOffsetInput;
    public final RecyclerView subtitleOffsetRecyclerview;
    public final TextView subtitleOffsetSubTitle;
    public final ImageView subtitleOffsetSubtract;
    public final ImageView subtitleOffsetSubtractMore;

    private SubtitleOffsetBinding(LinearLayout linearLayout, MaterialButton materialButton, MaterialButton materialButton2, TextView textView, MaterialButton materialButton3, ImageView imageView, ImageView imageView2, EditText editText, RecyclerView recyclerView, TextView textView2, ImageView imageView3, ImageView imageView4) {
        this.rootView = linearLayout;
        this.applyBtt = materialButton;
        this.cancelBtt = materialButton2;
        this.noSubtitlesLoadedNotice = textView;
        this.resetBtt = materialButton3;
        this.subtitleOffsetAdd = imageView;
        this.subtitleOffsetAddMore = imageView2;
        this.subtitleOffsetInput = editText;
        this.subtitleOffsetRecyclerview = recyclerView;
        this.subtitleOffsetSubTitle = textView2;
        this.subtitleOffsetSubtract = imageView3;
        this.subtitleOffsetSubtractMore = imageView4;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static SubtitleOffsetBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static SubtitleOffsetBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.subtitle_offset, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static SubtitleOffsetBinding bind(View view) {
        int i = R.id.apply_btt;
        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
        if (materialButton != null) {
            i = R.id.cancel_btt;
            MaterialButton materialButton2 = (MaterialButton) ViewBindings.findChildViewById(view, i);
            if (materialButton2 != null) {
                i = R.id.no_subtitles_loaded_notice;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView != null) {
                    i = R.id.reset_btt;
                    MaterialButton materialButton3 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                    if (materialButton3 != null) {
                        i = R.id.subtitle_offset_add;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                        if (imageView != null) {
                            i = R.id.subtitle_offset_add_more;
                            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
                            if (imageView2 != null) {
                                i = R.id.subtitle_offset_input;
                                EditText editText = (EditText) ViewBindings.findChildViewById(view, i);
                                if (editText != null) {
                                    i = R.id.subtitle_offset_recyclerview;
                                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                                    if (recyclerView != null) {
                                        i = R.id.subtitle_offset_sub_title;
                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                                        if (textView2 != null) {
                                            i = R.id.subtitle_offset_subtract;
                                            ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, i);
                                            if (imageView3 != null) {
                                                i = R.id.subtitle_offset_subtract_more;
                                                ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                if (imageView4 != null) {
                                                    return new SubtitleOffsetBinding((LinearLayout) view, materialButton, materialButton2, textView, materialButton3, imageView, imageView2, editText, recyclerView, textView2, imageView3, imageView4);
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
