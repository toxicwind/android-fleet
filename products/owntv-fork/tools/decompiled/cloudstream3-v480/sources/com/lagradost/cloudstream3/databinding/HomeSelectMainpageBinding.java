package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class HomeSelectMainpageBinding implements ViewBinding {
    public final MaterialButton applyBtt;
    public final LinearLayout applyBttHolder;
    public final MaterialButton cancelBtt;
    public final ListView listview1;
    private final FrameLayout rootView;
    public final TvtypesChipsScrollBinding tvtypesChipsScroll;

    private HomeSelectMainpageBinding(FrameLayout frameLayout, MaterialButton materialButton, LinearLayout linearLayout, MaterialButton materialButton2, ListView listView, TvtypesChipsScrollBinding tvtypesChipsScrollBinding) {
        this.rootView = frameLayout;
        this.applyBtt = materialButton;
        this.applyBttHolder = linearLayout;
        this.cancelBtt = materialButton2;
        this.listview1 = listView;
        this.tvtypesChipsScroll = tvtypesChipsScrollBinding;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static HomeSelectMainpageBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HomeSelectMainpageBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.home_select_mainpage, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static HomeSelectMainpageBinding bind(View view) {
        View viewFindChildViewById;
        int i = R.id.apply_btt;
        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
        if (materialButton != null) {
            i = R.id.apply_btt_holder;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
            if (linearLayout != null) {
                i = R.id.cancel_btt;
                MaterialButton materialButton2 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                if (materialButton2 != null) {
                    i = R.id.listview1;
                    ListView listView = (ListView) ViewBindings.findChildViewById(view, i);
                    if (listView != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = R.id.tvtypes_chips_scroll))) != null) {
                        return new HomeSelectMainpageBinding((FrameLayout) view, materialButton, linearLayout, materialButton2, listView, TvtypesChipsScrollBinding.bind(viewFindChildViewById));
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
