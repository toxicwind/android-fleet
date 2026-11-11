package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.MaterialToolbar;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class FragmentChildDownloadsBinding implements ViewBinding {
    public final ImageButton btnCancel;
    public final Button btnDelete;
    public final Button btnToggleAll;
    public final RecyclerView downloadChildList;
    public final CoordinatorLayout downloadChildRoot;
    public final MaterialToolbar downloadChildToolbar;
    public final LinearLayout downloadDeleteAppbar;
    private final CoordinatorLayout rootView;
    public final TextView selectItemsText;

    private FragmentChildDownloadsBinding(CoordinatorLayout coordinatorLayout, ImageButton imageButton, Button button, Button button2, RecyclerView recyclerView, CoordinatorLayout coordinatorLayout2, MaterialToolbar materialToolbar, LinearLayout linearLayout, TextView textView) {
        this.rootView = coordinatorLayout;
        this.btnCancel = imageButton;
        this.btnDelete = button;
        this.btnToggleAll = button2;
        this.downloadChildList = recyclerView;
        this.downloadChildRoot = coordinatorLayout2;
        this.downloadChildToolbar = materialToolbar;
        this.downloadDeleteAppbar = linearLayout;
        this.selectItemsText = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CoordinatorLayout getRoot() {
        return this.rootView;
    }

    public static FragmentChildDownloadsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentChildDownloadsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_child_downloads, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentChildDownloadsBinding bind(View view) {
        int i = R.id.btnCancel;
        ImageButton imageButton = (ImageButton) ViewBindings.findChildViewById(view, i);
        if (imageButton != null) {
            i = R.id.btnDelete;
            Button button = (Button) ViewBindings.findChildViewById(view, i);
            if (button != null) {
                i = R.id.btnToggleAll;
                Button button2 = (Button) ViewBindings.findChildViewById(view, i);
                if (button2 != null) {
                    i = R.id.download_child_list;
                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                    if (recyclerView != null) {
                        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) view;
                        i = R.id.download_child_toolbar;
                        MaterialToolbar materialToolbar = (MaterialToolbar) ViewBindings.findChildViewById(view, i);
                        if (materialToolbar != null) {
                            i = R.id.download_delete_appbar;
                            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
                            if (linearLayout != null) {
                                i = R.id.selectItemsText;
                                TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                                if (textView != null) {
                                    return new FragmentChildDownloadsBinding(coordinatorLayout, imageButton, button, button2, recyclerView, coordinatorLayout, materialToolbar, linearLayout, textView);
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
