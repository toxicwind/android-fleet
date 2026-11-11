package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class FragmentExtensionsBinding implements ViewBinding {
    public final ExtendedFloatingActionButton addRepoButton;
    public final ImageView addRepoButtonImageview;
    public final LinearLayout addRepoButtonImageviewHolder;
    public final LinearLayout blankRepoScreen;
    public final CoordinatorLayout extensionsRoot;
    public final View pluginDisabled;
    public final TextView pluginDisabledTxt;
    public final View pluginDownload;
    public final TextView pluginDownloadTxt;
    public final View pluginNotDownloaded;
    public final TextView pluginNotDownloadedTxt;
    public final RecyclerView pluginRecyclerView;
    public final LinearLayout pluginStorageAppbar;
    public final RecyclerView repoRecyclerView;
    private final CoordinatorLayout rootView;
    public final MaterialToolbar settingsToolbar;

    private FragmentExtensionsBinding(CoordinatorLayout coordinatorLayout, ExtendedFloatingActionButton extendedFloatingActionButton, ImageView imageView, LinearLayout linearLayout, LinearLayout linearLayout2, CoordinatorLayout coordinatorLayout2, View view, TextView textView, View view2, TextView textView2, View view3, TextView textView3, RecyclerView recyclerView, LinearLayout linearLayout3, RecyclerView recyclerView2, MaterialToolbar materialToolbar) {
        this.rootView = coordinatorLayout;
        this.addRepoButton = extendedFloatingActionButton;
        this.addRepoButtonImageview = imageView;
        this.addRepoButtonImageviewHolder = linearLayout;
        this.blankRepoScreen = linearLayout2;
        this.extensionsRoot = coordinatorLayout2;
        this.pluginDisabled = view;
        this.pluginDisabledTxt = textView;
        this.pluginDownload = view2;
        this.pluginDownloadTxt = textView2;
        this.pluginNotDownloaded = view3;
        this.pluginNotDownloadedTxt = textView3;
        this.pluginRecyclerView = recyclerView;
        this.pluginStorageAppbar = linearLayout3;
        this.repoRecyclerView = recyclerView2;
        this.settingsToolbar = materialToolbar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CoordinatorLayout getRoot() {
        return this.rootView;
    }

    public static FragmentExtensionsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentExtensionsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_extensions, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentExtensionsBinding bind(View view) {
        View viewFindChildViewById;
        View viewFindChildViewById2;
        int i = R.id.add_repo_button;
        ExtendedFloatingActionButton extendedFloatingActionButton = (ExtendedFloatingActionButton) ViewBindings.findChildViewById(view, i);
        if (extendedFloatingActionButton != null) {
            i = R.id.add_repo_button_imageview;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
            if (imageView != null) {
                i = R.id.add_repo_button_imageview_holder;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
                if (linearLayout != null) {
                    i = R.id.blank_repo_screen;
                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                    if (linearLayout2 != null) {
                        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) view;
                        i = R.id.plugin_disabled;
                        View viewFindChildViewById3 = ViewBindings.findChildViewById(view, i);
                        if (viewFindChildViewById3 != null) {
                            i = R.id.plugin_disabled_txt;
                            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                            if (textView != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = R.id.plugin_download))) != null) {
                                i = R.id.plugin_download_txt;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                                if (textView2 != null && (viewFindChildViewById2 = ViewBindings.findChildViewById(view, (i = R.id.plugin_not_downloaded))) != null) {
                                    i = R.id.plugin_not_downloaded_txt;
                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                                    if (textView3 != null) {
                                        i = R.id.plugin_recycler_view;
                                        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                                        if (recyclerView != null) {
                                            i = R.id.plugin_storage_appbar;
                                            LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                            if (linearLayout3 != null) {
                                                i = R.id.repo_recycler_view;
                                                RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(view, i);
                                                if (recyclerView2 != null) {
                                                    i = R.id.settings_toolbar;
                                                    MaterialToolbar materialToolbar = (MaterialToolbar) ViewBindings.findChildViewById(view, i);
                                                    if (materialToolbar != null) {
                                                        return new FragmentExtensionsBinding(coordinatorLayout, extendedFloatingActionButton, imageView, linearLayout, linearLayout2, coordinatorLayout, viewFindChildViewById3, textView, viewFindChildViewById, textView2, viewFindChildViewById2, textView3, recyclerView, linearLayout3, recyclerView2, materialToolbar);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
