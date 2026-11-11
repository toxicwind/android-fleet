package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.fragment.app.FragmentContainerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigationrail.NavigationRailView;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class ActivityMainTvBinding implements ViewBinding {
    public final LinearLayout castMiniControllerHolder;
    public final ImageView focusOutline;
    public final FrameLayout homeRoot;
    public final FragmentContainerView navHostFragment;
    public final NavigationRailView navRailView;
    public final BottomNavigationView navView;
    private final FrameLayout rootView;

    private ActivityMainTvBinding(FrameLayout frameLayout, LinearLayout linearLayout, ImageView imageView, FrameLayout frameLayout2, FragmentContainerView fragmentContainerView, NavigationRailView navigationRailView, BottomNavigationView bottomNavigationView) {
        this.rootView = frameLayout;
        this.castMiniControllerHolder = linearLayout;
        this.focusOutline = imageView;
        this.homeRoot = frameLayout2;
        this.navHostFragment = fragmentContainerView;
        this.navRailView = navigationRailView;
        this.navView = bottomNavigationView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMainTvBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityMainTvBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.activity_main_tv, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityMainTvBinding bind(View view) {
        int i = R.id.cast_mini_controller_holder;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
        if (linearLayout != null) {
            i = R.id.focus_outline;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
            if (imageView != null) {
                FrameLayout frameLayout = (FrameLayout) view;
                i = R.id.nav_host_fragment;
                FragmentContainerView fragmentContainerView = (FragmentContainerView) ViewBindings.findChildViewById(view, i);
                if (fragmentContainerView != null) {
                    i = R.id.nav_rail_view;
                    NavigationRailView navigationRailView = (NavigationRailView) ViewBindings.findChildViewById(view, i);
                    if (navigationRailView != null) {
                        i = R.id.nav_view;
                        BottomNavigationView bottomNavigationView = (BottomNavigationView) ViewBindings.findChildViewById(view, i);
                        if (bottomNavigationView != null) {
                            return new ActivityMainTvBinding(frameLayout, linearLayout, imageView, frameLayout, fragmentContainerView, navigationRailView, bottomNavigationView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
