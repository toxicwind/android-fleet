package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class StandardToolbarBinding implements ViewBinding {
    private final AppBarLayout rootView;
    public final MaterialToolbar settingsToolbar;

    private StandardToolbarBinding(AppBarLayout appBarLayout, MaterialToolbar materialToolbar) {
        this.rootView = appBarLayout;
        this.settingsToolbar = materialToolbar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public AppBarLayout getRoot() {
        return this.rootView;
    }

    public static StandardToolbarBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static StandardToolbarBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.standard_toolbar, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static StandardToolbarBinding bind(View view) {
        int i = R.id.settings_toolbar;
        MaterialToolbar materialToolbar = (MaterialToolbar) ViewBindings.findChildViewById(view, i);
        if (materialToolbar != null) {
            return new StandardToolbarBinding((AppBarLayout) view, materialToolbar);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
