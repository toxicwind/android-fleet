package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.ui.settings.testing.TestView;

/* JADX INFO: loaded from: classes6.dex */
public final class FragmentTestingBinding implements ViewBinding {
    public final TestView providerTest;
    public final AppBarLayout providerTestAppbar;
    public final RecyclerView providerTestRecyclerView;
    private final CoordinatorLayout rootView;
    public final MaterialToolbar settingsToolbar;

    private FragmentTestingBinding(CoordinatorLayout coordinatorLayout, TestView testView, AppBarLayout appBarLayout, RecyclerView recyclerView, MaterialToolbar materialToolbar) {
        this.rootView = coordinatorLayout;
        this.providerTest = testView;
        this.providerTestAppbar = appBarLayout;
        this.providerTestRecyclerView = recyclerView;
        this.settingsToolbar = materialToolbar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CoordinatorLayout getRoot() {
        return this.rootView;
    }

    public static FragmentTestingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentTestingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_testing, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentTestingBinding bind(View view) {
        int i = R.id.provider_test;
        TestView testView = (TestView) ViewBindings.findChildViewById(view, i);
        if (testView != null) {
            i = R.id.provider_test_appbar;
            AppBarLayout appBarLayout = (AppBarLayout) ViewBindings.findChildViewById(view, i);
            if (appBarLayout != null) {
                i = R.id.provider_test_recycler_view;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                if (recyclerView != null) {
                    i = R.id.settings_toolbar;
                    MaterialToolbar materialToolbar = (MaterialToolbar) ViewBindings.findChildViewById(view, i);
                    if (materialToolbar != null) {
                        return new FragmentTestingBinding((CoordinatorLayout) view, testView, appBarLayout, recyclerView, materialToolbar);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
