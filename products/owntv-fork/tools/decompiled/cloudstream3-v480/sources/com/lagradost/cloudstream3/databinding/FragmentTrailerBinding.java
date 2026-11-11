package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.media3.ui.PlayerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class FragmentTrailerBinding implements ViewBinding {
    public final ConstraintLayout playerBackground;
    public final PlayerView playerView;
    private final ConstraintLayout rootView;

    private FragmentTrailerBinding(ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, PlayerView playerView) {
        this.rootView = constraintLayout;
        this.playerBackground = constraintLayout2;
        this.playerView = playerView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentTrailerBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentTrailerBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_trailer, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentTrailerBinding bind(View view) {
        ConstraintLayout constraintLayout = (ConstraintLayout) view;
        int i = R.id.player_view;
        PlayerView playerView = (PlayerView) ViewBindings.findChildViewById(view, i);
        if (playerView != null) {
            return new FragmentTrailerBinding(constraintLayout, constraintLayout, playerView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
