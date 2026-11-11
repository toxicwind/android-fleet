package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.media3.ui.PlayerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class FragmentPlayerBinding implements ViewBinding {
    public final ProgressBar mainLoad;
    public final MaterialButton overlayLoadingSkipButton;
    public final ConstraintLayout playerBackground;
    public final ImageView playerLoadingGoBack;
    public final FrameLayout playerLoadingOverlay;
    public final PlayerView playerView;
    private final ConstraintLayout rootView;
    public final FrameLayout videoGoBackHolderHolder;

    private FragmentPlayerBinding(ConstraintLayout constraintLayout, ProgressBar progressBar, MaterialButton materialButton, ConstraintLayout constraintLayout2, ImageView imageView, FrameLayout frameLayout, PlayerView playerView, FrameLayout frameLayout2) {
        this.rootView = constraintLayout;
        this.mainLoad = progressBar;
        this.overlayLoadingSkipButton = materialButton;
        this.playerBackground = constraintLayout2;
        this.playerLoadingGoBack = imageView;
        this.playerLoadingOverlay = frameLayout;
        this.playerView = playerView;
        this.videoGoBackHolderHolder = frameLayout2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentPlayerBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentPlayerBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_player, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentPlayerBinding bind(View view) {
        int i = R.id.main_load;
        ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(view, i);
        if (progressBar != null) {
            i = R.id.overlay_loading_skip_button;
            MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
            if (materialButton != null) {
                ConstraintLayout constraintLayout = (ConstraintLayout) view;
                i = R.id.player_loading_go_back;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                if (imageView != null) {
                    i = R.id.player_loading_overlay;
                    FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
                    if (frameLayout != null) {
                        i = R.id.player_view;
                        PlayerView playerView = (PlayerView) ViewBindings.findChildViewById(view, i);
                        if (playerView != null) {
                            i = R.id.video_go_back_holder_holder;
                            FrameLayout frameLayout2 = (FrameLayout) ViewBindings.findChildViewById(view, i);
                            if (frameLayout2 != null) {
                                return new FragmentPlayerBinding(constraintLayout, progressBar, materialButton, constraintLayout, imageView, frameLayout, playerView, frameLayout2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
