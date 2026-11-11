package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.ui.AutofitRecyclerView;

/* JADX INFO: loaded from: classes6.dex */
public final class LibraryViewpagerPageBinding implements ViewBinding {
    public final AutofitRecyclerView pageRecyclerview;
    private final AutofitRecyclerView rootView;

    private LibraryViewpagerPageBinding(AutofitRecyclerView autofitRecyclerView, AutofitRecyclerView autofitRecyclerView2) {
        this.rootView = autofitRecyclerView;
        this.pageRecyclerview = autofitRecyclerView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public AutofitRecyclerView getRoot() {
        return this.rootView;
    }

    public static LibraryViewpagerPageBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LibraryViewpagerPageBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.library_viewpager_page, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LibraryViewpagerPageBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        AutofitRecyclerView autofitRecyclerView = (AutofitRecyclerView) view;
        return new LibraryViewpagerPageBinding(autofitRecyclerView, autofitRecyclerView);
    }
}
