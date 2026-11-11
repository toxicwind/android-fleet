package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class FragmentEasterEggMonkeBinding implements ViewBinding {
    public final FrameLayout frame;
    public final AppCompatImageView monke;
    private final FrameLayout rootView;

    private FragmentEasterEggMonkeBinding(FrameLayout frameLayout, FrameLayout frameLayout2, AppCompatImageView appCompatImageView) {
        this.rootView = frameLayout;
        this.frame = frameLayout2;
        this.monke = appCompatImageView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static FragmentEasterEggMonkeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentEasterEggMonkeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_easter_egg_monke, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentEasterEggMonkeBinding bind(View view) {
        FrameLayout frameLayout = (FrameLayout) view;
        int i = R.id.monke;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            return new FragmentEasterEggMonkeBinding(frameLayout, frameLayout, appCompatImageView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
