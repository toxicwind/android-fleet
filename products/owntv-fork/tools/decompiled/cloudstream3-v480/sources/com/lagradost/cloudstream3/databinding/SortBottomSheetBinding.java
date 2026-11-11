package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class SortBottomSheetBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final ListView sortProviders;
    public final ListView sortSubtitles;
    public final LinearLayout sortSubtitlesHolder;

    private SortBottomSheetBinding(LinearLayout linearLayout, ListView listView, ListView listView2, LinearLayout linearLayout2) {
        this.rootView = linearLayout;
        this.sortProviders = listView;
        this.sortSubtitles = listView2;
        this.sortSubtitlesHolder = linearLayout2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static SortBottomSheetBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static SortBottomSheetBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.sort_bottom_sheet, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static SortBottomSheetBinding bind(View view) {
        int i = R.id.sort_providers;
        ListView listView = (ListView) ViewBindings.findChildViewById(view, i);
        if (listView != null) {
            i = R.id.sort_subtitles;
            ListView listView2 = (ListView) ViewBindings.findChildViewById(view, i);
            if (listView2 != null) {
                i = R.id.sort_subtitles_holder;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
                if (linearLayout != null) {
                    return new SortBottomSheetBinding((LinearLayout) view, listView, listView2, linearLayout);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
