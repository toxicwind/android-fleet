package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class ExtraBrightnessOverlayBinding implements ViewBinding {
    public final View extraBrightnessOverlay;
    private final View rootView;

    private ExtraBrightnessOverlayBinding(View view, View view2) {
        this.rootView = view;
        this.extraBrightnessOverlay = view2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public View getRoot() {
        return this.rootView;
    }

    public static ExtraBrightnessOverlayBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ExtraBrightnessOverlayBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.extra_brightness_overlay, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ExtraBrightnessOverlayBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        return new ExtraBrightnessOverlayBinding(view, view);
    }
}
