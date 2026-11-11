package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class LogcatBinding implements ViewBinding {
    public final HorizontalScrollView applyBttHolder;
    public final MaterialButton clearBtt;
    public final MaterialButton closeBtt;
    public final MaterialButton copyBtt;
    public final RecyclerView logcatRecyclerView;
    private final LinearLayout rootView;
    public final MaterialButton saveBtt;

    private LogcatBinding(LinearLayout linearLayout, HorizontalScrollView horizontalScrollView, MaterialButton materialButton, MaterialButton materialButton2, MaterialButton materialButton3, RecyclerView recyclerView, MaterialButton materialButton4) {
        this.rootView = linearLayout;
        this.applyBttHolder = horizontalScrollView;
        this.clearBtt = materialButton;
        this.closeBtt = materialButton2;
        this.copyBtt = materialButton3;
        this.logcatRecyclerView = recyclerView;
        this.saveBtt = materialButton4;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static LogcatBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LogcatBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.logcat, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LogcatBinding bind(View view) {
        int i = R.id.apply_btt_holder;
        HorizontalScrollView horizontalScrollView = (HorizontalScrollView) ViewBindings.findChildViewById(view, i);
        if (horizontalScrollView != null) {
            i = R.id.clear_btt;
            MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
            if (materialButton != null) {
                i = R.id.close_btt;
                MaterialButton materialButton2 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                if (materialButton2 != null) {
                    i = R.id.copy_btt;
                    MaterialButton materialButton3 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                    if (materialButton3 != null) {
                        i = R.id.logcat_recycler_view;
                        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                        if (recyclerView != null) {
                            i = R.id.save_btt;
                            MaterialButton materialButton4 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                            if (materialButton4 != null) {
                                return new LogcatBinding((LinearLayout) view, horizontalScrollView, materialButton, materialButton2, materialButton3, recyclerView, materialButton4);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
