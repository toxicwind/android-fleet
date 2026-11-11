package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class FragmentSetupLanguageBinding implements ViewBinding {
    public final ImageView appIconImage;
    public final LinearLayout applyBttHolder;
    public final ListView listview1;
    public final MaterialButton nextBtt;
    private final LinearLayout rootView;
    public final LinearLayout setupRoot;
    public final MaterialButton skipBtt;

    private FragmentSetupLanguageBinding(LinearLayout linearLayout, ImageView imageView, LinearLayout linearLayout2, ListView listView, MaterialButton materialButton, LinearLayout linearLayout3, MaterialButton materialButton2) {
        this.rootView = linearLayout;
        this.appIconImage = imageView;
        this.applyBttHolder = linearLayout2;
        this.listview1 = listView;
        this.nextBtt = materialButton;
        this.setupRoot = linearLayout3;
        this.skipBtt = materialButton2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentSetupLanguageBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentSetupLanguageBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_setup_language, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentSetupLanguageBinding bind(View view) {
        int i = R.id.app_icon_image;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.apply_btt_holder;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
            if (linearLayout != null) {
                i = R.id.listview1;
                ListView listView = (ListView) ViewBindings.findChildViewById(view, i);
                if (listView != null) {
                    i = R.id.next_btt;
                    MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
                    if (materialButton != null) {
                        LinearLayout linearLayout2 = (LinearLayout) view;
                        i = R.id.skip_btt;
                        MaterialButton materialButton2 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                        if (materialButton2 != null) {
                            return new FragmentSetupLanguageBinding(linearLayout2, imageView, linearLayout, listView, materialButton, linearLayout2, materialButton2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
