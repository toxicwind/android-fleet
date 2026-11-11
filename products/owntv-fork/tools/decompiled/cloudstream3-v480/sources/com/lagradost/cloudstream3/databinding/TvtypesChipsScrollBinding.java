package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class TvtypesChipsScrollBinding implements ViewBinding {
    private final HorizontalScrollView rootView;
    public final HorizontalScrollView tvTypesScrollView;
    public final TvtypesChipsBinding tvtypesChips;

    private TvtypesChipsScrollBinding(HorizontalScrollView horizontalScrollView, HorizontalScrollView horizontalScrollView2, TvtypesChipsBinding tvtypesChipsBinding) {
        this.rootView = horizontalScrollView;
        this.tvTypesScrollView = horizontalScrollView2;
        this.tvtypesChips = tvtypesChipsBinding;
    }

    @Override // androidx.viewbinding.ViewBinding
    public HorizontalScrollView getRoot() {
        return this.rootView;
    }

    public static TvtypesChipsScrollBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static TvtypesChipsScrollBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.tvtypes_chips_scroll, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static TvtypesChipsScrollBinding bind(View view) {
        HorizontalScrollView horizontalScrollView = (HorizontalScrollView) view;
        int i = R.id.tvtypes_chips;
        View viewFindChildViewById = ViewBindings.findChildViewById(view, i);
        if (viewFindChildViewById != null) {
            return new TvtypesChipsScrollBinding(horizontalScrollView, horizontalScrollView, TvtypesChipsBinding.bind(viewFindChildViewById));
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
