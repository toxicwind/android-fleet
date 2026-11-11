package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class LoadingLineShortCenterBinding implements ViewBinding {
    private final CardView rootView;

    private LoadingLineShortCenterBinding(CardView cardView) {
        this.rootView = cardView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CardView getRoot() {
        return this.rootView;
    }

    public static LoadingLineShortCenterBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LoadingLineShortCenterBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.loading_line_short_center, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LoadingLineShortCenterBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        return new LoadingLineShortCenterBinding((CardView) view);
    }
}
