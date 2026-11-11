package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class ProviderListBinding implements ViewBinding {
    public final MaterialButton applyBtt;
    public final LinearLayout applyBttHolder;
    public final MaterialButton cancelBtt;
    public final ListView listview1;
    public final ListView listview2;
    private final LinearLayout rootView;
    public final SwitchMaterial toggle1;

    private ProviderListBinding(LinearLayout linearLayout, MaterialButton materialButton, LinearLayout linearLayout2, MaterialButton materialButton2, ListView listView, ListView listView2, SwitchMaterial switchMaterial) {
        this.rootView = linearLayout;
        this.applyBtt = materialButton;
        this.applyBttHolder = linearLayout2;
        this.cancelBtt = materialButton2;
        this.listview1 = listView;
        this.listview2 = listView2;
        this.toggle1 = switchMaterial;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ProviderListBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ProviderListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.provider_list, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ProviderListBinding bind(View view) {
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
                    if (listView != null) {
                        i = R.id.listview2;
                        ListView listView2 = (ListView) ViewBindings.findChildViewById(view, i);
                        if (listView2 != null) {
                            i = R.id.toggle1;
                            SwitchMaterial switchMaterial = (SwitchMaterial) ViewBindings.findChildViewById(view, i);
                            if (switchMaterial != null) {
                                return new ProviderListBinding((LinearLayout) view, materialButton, linearLayout, materialButton2, listView, listView2, switchMaterial);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
