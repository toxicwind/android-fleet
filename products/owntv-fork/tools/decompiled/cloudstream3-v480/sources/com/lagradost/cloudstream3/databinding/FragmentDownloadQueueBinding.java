package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.MaterialToolbar;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class FragmentDownloadQueueBinding implements ViewBinding {
    public final RecyclerView downloadQueueList;
    public final FrameLayout downloadQueueRoot;
    public final MaterialToolbar downloadQueueToolbar;
    private final FrameLayout rootView;
    public final TextView textNoQueue;

    private FragmentDownloadQueueBinding(FrameLayout frameLayout, RecyclerView recyclerView, FrameLayout frameLayout2, MaterialToolbar materialToolbar, TextView textView) {
        this.rootView = frameLayout;
        this.downloadQueueList = recyclerView;
        this.downloadQueueRoot = frameLayout2;
        this.downloadQueueToolbar = materialToolbar;
        this.textNoQueue = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static FragmentDownloadQueueBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentDownloadQueueBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_download_queue, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentDownloadQueueBinding bind(View view) {
        int i = R.id.download_queue_list;
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
        if (recyclerView != null) {
            FrameLayout frameLayout = (FrameLayout) view;
            i = R.id.download_queue_toolbar;
            MaterialToolbar materialToolbar = (MaterialToolbar) ViewBindings.findChildViewById(view, i);
            if (materialToolbar != null) {
                i = R.id.text_no_queue;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView != null) {
                    return new FragmentDownloadQueueBinding(frameLayout, recyclerView, frameLayout, materialToolbar, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
