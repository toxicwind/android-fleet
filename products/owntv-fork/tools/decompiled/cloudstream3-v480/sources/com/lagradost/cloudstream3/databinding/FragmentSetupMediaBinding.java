package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class FragmentSetupMediaBinding implements ViewBinding {
    public final LinearLayout applyBttHolder;
    public final ListView listview1;
    public final MaterialButton nextBtt;
    public final MaterialButton prevBtt;
    private final LinearLayout rootView;
    public final LinearLayout setupRoot;

    private FragmentSetupMediaBinding(LinearLayout linearLayout, LinearLayout linearLayout2, ListView listView, MaterialButton materialButton, MaterialButton materialButton2, LinearLayout linearLayout3) {
        this.rootView = linearLayout;
        this.applyBttHolder = linearLayout2;
        this.listview1 = listView;
        this.nextBtt = materialButton;
        this.prevBtt = materialButton2;
        this.setupRoot = linearLayout3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentSetupMediaBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentSetupMediaBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_setup_media, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentSetupMediaBinding bind(View view) {
        int i = R.id.apply_btt_holder;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
        if (linearLayout != null) {
            i = R.id.listview1;
            ListView listView = (ListView) ViewBindings.findChildViewById(view, i);
            if (listView != null) {
                i = R.id.next_btt;
                MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
                if (materialButton != null) {
                    i = R.id.prev_btt;
                    MaterialButton materialButton2 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                    if (materialButton2 != null) {
                        LinearLayout linearLayout2 = (LinearLayout) view;
                        return new FragmentSetupMediaBinding(linearLayout2, linearLayout, listView, materialButton, materialButton2, linearLayout2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
