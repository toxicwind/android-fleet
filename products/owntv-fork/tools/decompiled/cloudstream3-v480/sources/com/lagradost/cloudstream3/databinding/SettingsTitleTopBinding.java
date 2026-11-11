package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class SettingsTitleTopBinding implements ViewBinding {
    public final FrameLayout listContainer;
    private final CoordinatorLayout rootView;
    public final CoordinatorLayout settingsTopRoot;

    private SettingsTitleTopBinding(CoordinatorLayout coordinatorLayout, FrameLayout frameLayout, CoordinatorLayout coordinatorLayout2) {
        this.rootView = coordinatorLayout;
        this.listContainer = frameLayout;
        this.settingsTopRoot = coordinatorLayout2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CoordinatorLayout getRoot() {
        return this.rootView;
    }

    public static SettingsTitleTopBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static SettingsTitleTopBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.settings_title_top, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static SettingsTitleTopBinding bind(View view) {
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, android.R.id.list_container);
        if (frameLayout != null) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) view;
            return new SettingsTitleTopBinding(coordinatorLayout, frameLayout, coordinatorLayout);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(android.R.id.list_container)));
    }
}
