package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.MaterialToolbar;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class FragmentPluginsBinding implements ViewBinding {
    public final CoordinatorLayout extensionsRoot;
    public final RecyclerView pluginRecyclerView;
    private final CoordinatorLayout rootView;
    public final MaterialToolbar settingsToolbar;
    public final TvtypesChipsScrollBinding tvtypesChipsScroll;

    private FragmentPluginsBinding(CoordinatorLayout coordinatorLayout, CoordinatorLayout coordinatorLayout2, RecyclerView recyclerView, MaterialToolbar materialToolbar, TvtypesChipsScrollBinding tvtypesChipsScrollBinding) {
        this.rootView = coordinatorLayout;
        this.extensionsRoot = coordinatorLayout2;
        this.pluginRecyclerView = recyclerView;
        this.settingsToolbar = materialToolbar;
        this.tvtypesChipsScroll = tvtypesChipsScrollBinding;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CoordinatorLayout getRoot() {
        return this.rootView;
    }

    public static FragmentPluginsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentPluginsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_plugins, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentPluginsBinding bind(View view) {
        View viewFindChildViewById;
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) view;
        int i = R.id.plugin_recycler_view;
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
        if (recyclerView != null) {
            i = R.id.settings_toolbar;
            MaterialToolbar materialToolbar = (MaterialToolbar) ViewBindings.findChildViewById(view, i);
            if (materialToolbar != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = R.id.tvtypes_chips_scroll))) != null) {
                return new FragmentPluginsBinding(coordinatorLayout, coordinatorLayout, recyclerView, materialToolbar, TvtypesChipsScrollBinding.bind(viewFindChildViewById));
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
