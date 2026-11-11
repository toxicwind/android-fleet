package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.ui.player.live.LivePreviewTimeBar;

/* JADX INFO: loaded from: classes6.dex */
public final class TrailerCustomLayoutBinding implements ViewBinding {
    public final LinearLayout bottomPlayerBar;
    public final LinearLayout downloadBothHeader;
    public final CardView downloadHeader;
    public final ImageView downloadHeaderToggle;
    public final TextView downloadHeaderToggleText;
    public final LinearProgressIndicator downloadedProgress;
    public final TextView downloadedProgressSpeedText;
    public final TextView downloadedProgressText;
    public final TextView exoDuration;
    public final TextView exoFfwdText;
    public final ImageButton exoNext;
    public final ImageButton exoPause;
    public final ImageButton exoPlay;
    public final TextView exoPosition;
    public final ImageButton exoPrev;
    public final LivePreviewTimeBar exoProgress;
    public final ImageButton exoRepeatToggle;
    public final TextView exoRewText;
    public final ImageButton exoVr;
    public final ImageView offlinePin;
    public final FrameLayout piphide;
    public final ProgressBar playerBuffering;
    public final ConstraintLayout playerCenterMenu;
    public final HorizontalScrollView playerControlsScroll;
    public final MaterialButton playerEpisodeFiller;
    public final FrameLayout playerEpisodeFillerHolder;
    public final RecyclerView playerEpisodeList;
    public final LinearLayout playerEpisodeOverlay;
    public final TextView playerEpisodeOverlayTitle;
    public final ImageView playerEpisodesButton;
    public final LinearLayout playerEpisodesButtonRoot;
    public final TextView playerEpisodesButtonText;
    public final ImageButton playerFfwd;
    public final FrameLayout playerFfwdHolder;
    public final ImageView playerFullscreen;
    public final ImageView playerGoBack;
    public final LinearLayout playerGoBackHolder;
    public final LinearLayout playerGoBackRoot;
    public final TextView playerGoBackText;
    public final ImageView playerGoForward;
    public final LinearLayout playerGoForwardRoot;
    public final TextView playerGoForwardText;
    public final FrameLayout playerHolder;
    public final FrameLayout playerIntroPlay;
    public final MaterialButton playerLock;
    public final LinearLayout playerLockHolder;
    public final LinearLayout playerMetadataOverlay;
    public final FrameLayout playerMetadataScrim;
    public final ImageView playerMovieLogo;
    public final TextView playerMovieMeta;
    public final TextView playerMovieOverview;
    public final TextView playerMovieTitle;
    public final ImageView playerOpenSource;
    public final ImageView playerPausePlay;
    public final FrameLayout playerPausePlayHolderHolder;
    public final RelativeLayout playerProgressbarLeftHolder;
    public final ImageView playerProgressbarLeftIcon;
    public final ProgressBar playerProgressbarLeftLevel1;
    public final ProgressBar playerProgressbarLeftLevel2;
    public final RelativeLayout playerProgressbarRightHolder;
    public final ImageView playerProgressbarRightIcon;
    public final ProgressBar playerProgressbarRightLevel1;
    public final ProgressBar playerProgressbarRightLevel2;
    public final MaterialButton playerResizeBtt;
    public final ImageView playerRestart;
    public final LinearLayout playerRestartRoot;
    public final TextView playerRestartText;
    public final ImageButton playerRew;
    public final FrameLayout playerRewHolder;
    public final MaterialButton playerRotateBtt;
    public final MaterialButton playerSkipEpisode;
    public final MaterialButton playerSkipOp;
    public final MaterialButton playerSourcesBtt;
    public final MaterialButton playerSpeedBtt;
    public final MaterialButton playerSpeedupButton;
    public final MaterialButton playerSubtitleOffsetBtt;
    public final TextView playerTimeText;
    public final FrameLayout playerTopHolder;
    public final MaterialButton playerTracksBtt;
    public final ConstraintLayout playerVideoBar;
    public final ConstraintLayout playerVideoHolder;
    public final TextView playerVideoInfo;
    public final TextView playerVideoTitle;
    public final LinearLayout playerVideoTitleHolder;
    public final TextView playerVideoTitleRez;
    public final FrameLayout previewFrameLayout;
    public final ImageView previewImageView;
    private final FrameLayout rootView;
    public final View shadowOverlay;
    public final MaterialButton skipChapterButton;
    public final FrameLayout subtitleHolder;
    public final TextView timeLeft;
    public final TextView timeLive;
    public final ImageView videoOutline;

    private TrailerCustomLayoutBinding(FrameLayout frameLayout, LinearLayout linearLayout, LinearLayout linearLayout2, CardView cardView, ImageView imageView, TextView textView, LinearProgressIndicator linearProgressIndicator, TextView textView2, TextView textView3, TextView textView4, TextView textView5, ImageButton imageButton, ImageButton imageButton2, ImageButton imageButton3, TextView textView6, ImageButton imageButton4, LivePreviewTimeBar livePreviewTimeBar, ImageButton imageButton5, TextView textView7, ImageButton imageButton6, ImageView imageView2, FrameLayout frameLayout2, ProgressBar progressBar, ConstraintLayout constraintLayout, HorizontalScrollView horizontalScrollView, MaterialButton materialButton, FrameLayout frameLayout3, RecyclerView recyclerView, LinearLayout linearLayout3, TextView textView8, ImageView imageView3, LinearLayout linearLayout4, TextView textView9, ImageButton imageButton7, FrameLayout frameLayout4, ImageView imageView4, ImageView imageView5, LinearLayout linearLayout5, LinearLayout linearLayout6, TextView textView10, ImageView imageView6, LinearLayout linearLayout7, TextView textView11, FrameLayout frameLayout5, FrameLayout frameLayout6, MaterialButton materialButton2, LinearLayout linearLayout8, LinearLayout linearLayout9, FrameLayout frameLayout7, ImageView imageView7, TextView textView12, TextView textView13, TextView textView14, ImageView imageView8, ImageView imageView9, FrameLayout frameLayout8, RelativeLayout relativeLayout, ImageView imageView10, ProgressBar progressBar2, ProgressBar progressBar3, RelativeLayout relativeLayout2, ImageView imageView11, ProgressBar progressBar4, ProgressBar progressBar5, MaterialButton materialButton3, ImageView imageView12, LinearLayout linearLayout10, TextView textView15, ImageButton imageButton8, FrameLayout frameLayout9, MaterialButton materialButton4, MaterialButton materialButton5, MaterialButton materialButton6, MaterialButton materialButton7, MaterialButton materialButton8, MaterialButton materialButton9, MaterialButton materialButton10, TextView textView16, FrameLayout frameLayout10, MaterialButton materialButton11, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, TextView textView17, TextView textView18, LinearLayout linearLayout11, TextView textView19, FrameLayout frameLayout11, ImageView imageView13, View view, MaterialButton materialButton12, FrameLayout frameLayout12, TextView textView20, TextView textView21, ImageView imageView14) {
        this.rootView = frameLayout;
        this.bottomPlayerBar = linearLayout;
        this.downloadBothHeader = linearLayout2;
        this.downloadHeader = cardView;
        this.downloadHeaderToggle = imageView;
        this.downloadHeaderToggleText = textView;
        this.downloadedProgress = linearProgressIndicator;
        this.downloadedProgressSpeedText = textView2;
        this.downloadedProgressText = textView3;
        this.exoDuration = textView4;
        this.exoFfwdText = textView5;
        this.exoNext = imageButton;
        this.exoPause = imageButton2;
        this.exoPlay = imageButton3;
        this.exoPosition = textView6;
        this.exoPrev = imageButton4;
        this.exoProgress = livePreviewTimeBar;
        this.exoRepeatToggle = imageButton5;
        this.exoRewText = textView7;
        this.exoVr = imageButton6;
        this.offlinePin = imageView2;
        this.piphide = frameLayout2;
        this.playerBuffering = progressBar;
        this.playerCenterMenu = constraintLayout;
        this.playerControlsScroll = horizontalScrollView;
        this.playerEpisodeFiller = materialButton;
        this.playerEpisodeFillerHolder = frameLayout3;
        this.playerEpisodeList = recyclerView;
        this.playerEpisodeOverlay = linearLayout3;
        this.playerEpisodeOverlayTitle = textView8;
        this.playerEpisodesButton = imageView3;
        this.playerEpisodesButtonRoot = linearLayout4;
        this.playerEpisodesButtonText = textView9;
        this.playerFfwd = imageButton7;
        this.playerFfwdHolder = frameLayout4;
        this.playerFullscreen = imageView4;
        this.playerGoBack = imageView5;
        this.playerGoBackHolder = linearLayout5;
        this.playerGoBackRoot = linearLayout6;
        this.playerGoBackText = textView10;
        this.playerGoForward = imageView6;
        this.playerGoForwardRoot = linearLayout7;
        this.playerGoForwardText = textView11;
        this.playerHolder = frameLayout5;
        this.playerIntroPlay = frameLayout6;
        this.playerLock = materialButton2;
        this.playerLockHolder = linearLayout8;
        this.playerMetadataOverlay = linearLayout9;
        this.playerMetadataScrim = frameLayout7;
        this.playerMovieLogo = imageView7;
        this.playerMovieMeta = textView12;
        this.playerMovieOverview = textView13;
        this.playerMovieTitle = textView14;
        this.playerOpenSource = imageView8;
        this.playerPausePlay = imageView9;
        this.playerPausePlayHolderHolder = frameLayout8;
        this.playerProgressbarLeftHolder = relativeLayout;
        this.playerProgressbarLeftIcon = imageView10;
        this.playerProgressbarLeftLevel1 = progressBar2;
        this.playerProgressbarLeftLevel2 = progressBar3;
        this.playerProgressbarRightHolder = relativeLayout2;
        this.playerProgressbarRightIcon = imageView11;
        this.playerProgressbarRightLevel1 = progressBar4;
        this.playerProgressbarRightLevel2 = progressBar5;
        this.playerResizeBtt = materialButton3;
        this.playerRestart = imageView12;
        this.playerRestartRoot = linearLayout10;
        this.playerRestartText = textView15;
        this.playerRew = imageButton8;
        this.playerRewHolder = frameLayout9;
        this.playerRotateBtt = materialButton4;
        this.playerSkipEpisode = materialButton5;
        this.playerSkipOp = materialButton6;
        this.playerSourcesBtt = materialButton7;
        this.playerSpeedBtt = materialButton8;
        this.playerSpeedupButton = materialButton9;
        this.playerSubtitleOffsetBtt = materialButton10;
        this.playerTimeText = textView16;
        this.playerTopHolder = frameLayout10;
        this.playerTracksBtt = materialButton11;
        this.playerVideoBar = constraintLayout2;
        this.playerVideoHolder = constraintLayout3;
        this.playerVideoInfo = textView17;
        this.playerVideoTitle = textView18;
        this.playerVideoTitleHolder = linearLayout11;
        this.playerVideoTitleRez = textView19;
        this.previewFrameLayout = frameLayout11;
        this.previewImageView = imageView13;
        this.shadowOverlay = view;
        this.skipChapterButton = materialButton12;
        this.subtitleHolder = frameLayout12;
        this.timeLeft = textView20;
        this.timeLive = textView21;
        this.videoOutline = imageView14;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static TrailerCustomLayoutBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static TrailerCustomLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.trailer_custom_layout, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static TrailerCustomLayoutBinding bind(View view) {
        View viewFindChildViewById;
        int i = R.id.bottom_player_bar;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
        if (linearLayout != null) {
            i = R.id.download_both_header;
            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, i);
            if (linearLayout2 != null) {
                i = R.id.download_header;
                CardView cardView = (CardView) ViewBindings.findChildViewById(view, i);
                if (cardView != null) {
                    i = R.id.download_header_toggle;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                    if (imageView != null) {
                        i = R.id.download_header_toggle_text;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                        if (textView != null) {
                            i = R.id.downloaded_progress;
                            LinearProgressIndicator linearProgressIndicator = (LinearProgressIndicator) ViewBindings.findChildViewById(view, i);
                            if (linearProgressIndicator != null) {
                                i = R.id.downloaded_progress_speed_text;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                                if (textView2 != null) {
                                    i = R.id.downloaded_progress_text;
                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                                    if (textView3 != null) {
                                        i = androidx.media3.ui.R.id.exo_duration;
                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, i);
                                        if (textView4 != null) {
                                            i = R.id.exo_ffwd_text;
                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, i);
                                            if (textView5 != null) {
                                                i = androidx.media3.ui.R.id.exo_next;
                                                ImageButton imageButton = (ImageButton) ViewBindings.findChildViewById(view, i);
                                                if (imageButton != null) {
                                                    i = androidx.media3.ui.R.id.exo_pause;
                                                    ImageButton imageButton2 = (ImageButton) ViewBindings.findChildViewById(view, i);
                                                    if (imageButton2 != null) {
                                                        i = androidx.media3.ui.R.id.exo_play;
                                                        ImageButton imageButton3 = (ImageButton) ViewBindings.findChildViewById(view, i);
                                                        if (imageButton3 != null) {
                                                            i = R.id.exo_position;
                                                            TextView textView6 = (TextView) ViewBindings.findChildViewById(view, i);
                                                            if (textView6 != null) {
                                                                i = androidx.media3.ui.R.id.exo_prev;
                                                                ImageButton imageButton4 = (ImageButton) ViewBindings.findChildViewById(view, i);
                                                                if (imageButton4 != null) {
                                                                    i = R.id.exo_progress;
                                                                    LivePreviewTimeBar livePreviewTimeBar = (LivePreviewTimeBar) ViewBindings.findChildViewById(view, i);
                                                                    if (livePreviewTimeBar != null) {
                                                                        i = androidx.media3.ui.R.id.exo_repeat_toggle;
                                                                        ImageButton imageButton5 = (ImageButton) ViewBindings.findChildViewById(view, i);
                                                                        if (imageButton5 != null) {
                                                                            i = R.id.exo_rew_text;
                                                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                            if (textView7 != null) {
                                                                                i = androidx.media3.ui.R.id.exo_vr;
                                                                                ImageButton imageButton6 = (ImageButton) ViewBindings.findChildViewById(view, i);
                                                                                if (imageButton6 != null) {
                                                                                    i = R.id.offline_pin;
                                                                                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                                                    if (imageView2 != null) {
                                                                                        i = R.id.piphide;
                                                                                        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
                                                                                        if (frameLayout != null) {
                                                                                            i = R.id.player_buffering;
                                                                                            ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(view, i);
                                                                                            if (progressBar != null) {
                                                                                                i = R.id.player_center_menu;
                                                                                                ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                                                                                                if (constraintLayout != null) {
                                                                                                    i = R.id.player_controls_scroll;
                                                                                                    HorizontalScrollView horizontalScrollView = (HorizontalScrollView) ViewBindings.findChildViewById(view, i);
                                                                                                    if (horizontalScrollView != null) {
                                                                                                        i = R.id.player_episode_filler;
                                                                                                        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
                                                                                                        if (materialButton != null) {
                                                                                                            i = R.id.player_episode_filler_holder;
                                                                                                            FrameLayout frameLayout2 = (FrameLayout) ViewBindings.findChildViewById(view, i);
                                                                                                            if (frameLayout2 != null) {
                                                                                                                i = R.id.player_episode_list;
                                                                                                                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                                                                                                                if (recyclerView != null) {
                                                                                                                    i = R.id.player_episode_overlay;
                                                                                                                    LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                    if (linearLayout3 != null) {
                                                                                                                        i = R.id.player_episode_overlay_title;
                                                                                                                        TextView textView8 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                        if (textView8 != null) {
                                                                                                                            i = R.id.player_episodes_button;
                                                                                                                            ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                                                                                            if (imageView3 != null) {
                                                                                                                                i = R.id.player_episodes_button_root;
                                                                                                                                LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                                if (linearLayout4 != null) {
                                                                                                                                    i = R.id.player_episodes_button_text;
                                                                                                                                    TextView textView9 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                    if (textView9 != null) {
                                                                                                                                        i = R.id.player_ffwd;
                                                                                                                                        ImageButton imageButton7 = (ImageButton) ViewBindings.findChildViewById(view, i);
                                                                                                                                        if (imageButton7 != null) {
                                                                                                                                            i = R.id.player_ffwd_holder;
                                                                                                                                            FrameLayout frameLayout3 = (FrameLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                                            if (frameLayout3 != null) {
                                                                                                                                                i = R.id.player_fullscreen;
                                                                                                                                                ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                if (imageView4 != null) {
                                                                                                                                                    i = R.id.player_go_back;
                                                                                                                                                    ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                    if (imageView5 != null) {
                                                                                                                                                        i = R.id.player_go_back_holder;
                                                                                                                                                        LinearLayout linearLayout5 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                                                        if (linearLayout5 != null) {
                                                                                                                                                            i = R.id.player_go_back_root;
                                                                                                                                                            LinearLayout linearLayout6 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                                                            if (linearLayout6 != null) {
                                                                                                                                                                i = R.id.player_go_back_text;
                                                                                                                                                                TextView textView10 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                if (textView10 != null) {
                                                                                                                                                                    i = R.id.player_go_forward;
                                                                                                                                                                    ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                    if (imageView6 != null) {
                                                                                                                                                                        i = R.id.player_go_forward_root;
                                                                                                                                                                        LinearLayout linearLayout7 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                        if (linearLayout7 != null) {
                                                                                                                                                                            i = R.id.player_go_forward_text;
                                                                                                                                                                            TextView textView11 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                            if (textView11 != null) {
                                                                                                                                                                                FrameLayout frameLayout4 = (FrameLayout) view;
                                                                                                                                                                                i = R.id.player_intro_play;
                                                                                                                                                                                FrameLayout frameLayout5 = (FrameLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                if (frameLayout5 != null) {
                                                                                                                                                                                    i = R.id.player_lock;
                                                                                                                                                                                    MaterialButton materialButton2 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                    if (materialButton2 != null) {
                                                                                                                                                                                        i = R.id.player_lock_holder;
                                                                                                                                                                                        LinearLayout linearLayout8 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                        if (linearLayout8 != null) {
                                                                                                                                                                                            i = R.id.player_metadata_overlay;
                                                                                                                                                                                            LinearLayout linearLayout9 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                            if (linearLayout9 != null) {
                                                                                                                                                                                                i = R.id.player_metadata_scrim;
                                                                                                                                                                                                FrameLayout frameLayout6 = (FrameLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                if (frameLayout6 != null) {
                                                                                                                                                                                                    i = R.id.player_movie_logo;
                                                                                                                                                                                                    ImageView imageView7 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                    if (imageView7 != null) {
                                                                                                                                                                                                        i = R.id.player_movie_meta;
                                                                                                                                                                                                        TextView textView12 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                        if (textView12 != null) {
                                                                                                                                                                                                            i = R.id.player_movie_overview;
                                                                                                                                                                                                            TextView textView13 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                            if (textView13 != null) {
                                                                                                                                                                                                                i = R.id.player_movie_title;
                                                                                                                                                                                                                TextView textView14 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                if (textView14 != null) {
                                                                                                                                                                                                                    i = R.id.player_open_source;
                                                                                                                                                                                                                    ImageView imageView8 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                    if (imageView8 != null) {
                                                                                                                                                                                                                        i = R.id.player_pause_play;
                                                                                                                                                                                                                        ImageView imageView9 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                        if (imageView9 != null) {
                                                                                                                                                                                                                            i = R.id.player_pause_play_holder_holder;
                                                                                                                                                                                                                            FrameLayout frameLayout7 = (FrameLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                            if (frameLayout7 != null) {
                                                                                                                                                                                                                                i = R.id.player_progressbar_left_holder;
                                                                                                                                                                                                                                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                if (relativeLayout != null) {
                                                                                                                                                                                                                                    i = R.id.player_progressbar_left_icon;
                                                                                                                                                                                                                                    ImageView imageView10 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                    if (imageView10 != null) {
                                                                                                                                                                                                                                        i = R.id.player_progressbar_left_level1;
                                                                                                                                                                                                                                        ProgressBar progressBar2 = (ProgressBar) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                        if (progressBar2 != null) {
                                                                                                                                                                                                                                            i = R.id.player_progressbar_left_level2;
                                                                                                                                                                                                                                            ProgressBar progressBar3 = (ProgressBar) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                            if (progressBar3 != null) {
                                                                                                                                                                                                                                                i = R.id.player_progressbar_right_holder;
                                                                                                                                                                                                                                                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                if (relativeLayout2 != null) {
                                                                                                                                                                                                                                                    i = R.id.player_progressbar_right_icon;
                                                                                                                                                                                                                                                    ImageView imageView11 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                    if (imageView11 != null) {
                                                                                                                                                                                                                                                        i = R.id.player_progressbar_right_level1;
                                                                                                                                                                                                                                                        ProgressBar progressBar4 = (ProgressBar) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                        if (progressBar4 != null) {
                                                                                                                                                                                                                                                            i = R.id.player_progressbar_right_level2;
                                                                                                                                                                                                                                                            ProgressBar progressBar5 = (ProgressBar) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                            if (progressBar5 != null) {
                                                                                                                                                                                                                                                                i = R.id.player_resize_btt;
                                                                                                                                                                                                                                                                MaterialButton materialButton3 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                if (materialButton3 != null) {
                                                                                                                                                                                                                                                                    i = R.id.player_restart;
                                                                                                                                                                                                                                                                    ImageView imageView12 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                    if (imageView12 != null) {
                                                                                                                                                                                                                                                                        i = R.id.player_restart_root;
                                                                                                                                                                                                                                                                        LinearLayout linearLayout10 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                        if (linearLayout10 != null) {
                                                                                                                                                                                                                                                                            i = R.id.player_restart_text;
                                                                                                                                                                                                                                                                            TextView textView15 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                            if (textView15 != null) {
                                                                                                                                                                                                                                                                                i = R.id.player_rew;
                                                                                                                                                                                                                                                                                ImageButton imageButton8 = (ImageButton) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                                if (imageButton8 != null) {
                                                                                                                                                                                                                                                                                    i = R.id.player_rew_holder;
                                                                                                                                                                                                                                                                                    FrameLayout frameLayout8 = (FrameLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                                    if (frameLayout8 != null) {
                                                                                                                                                                                                                                                                                        i = R.id.player_rotate_btt;
                                                                                                                                                                                                                                                                                        MaterialButton materialButton4 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                                        if (materialButton4 != null) {
                                                                                                                                                                                                                                                                                            i = R.id.player_skip_episode;
                                                                                                                                                                                                                                                                                            MaterialButton materialButton5 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                                            if (materialButton5 != null) {
                                                                                                                                                                                                                                                                                                i = R.id.player_skip_op;
                                                                                                                                                                                                                                                                                                MaterialButton materialButton6 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                                                if (materialButton6 != null) {
                                                                                                                                                                                                                                                                                                    i = R.id.player_sources_btt;
                                                                                                                                                                                                                                                                                                    MaterialButton materialButton7 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                                                    if (materialButton7 != null) {
                                                                                                                                                                                                                                                                                                        i = R.id.player_speed_btt;
                                                                                                                                                                                                                                                                                                        MaterialButton materialButton8 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                                                        if (materialButton8 != null) {
                                                                                                                                                                                                                                                                                                            i = R.id.player_speedup_button;
                                                                                                                                                                                                                                                                                                            MaterialButton materialButton9 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                                                            if (materialButton9 != null) {
                                                                                                                                                                                                                                                                                                                i = R.id.player_subtitle_offset_btt;
                                                                                                                                                                                                                                                                                                                MaterialButton materialButton10 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                                                                if (materialButton10 != null) {
                                                                                                                                                                                                                                                                                                                    i = R.id.player_time_text;
                                                                                                                                                                                                                                                                                                                    TextView textView16 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                                                                    if (textView16 != null) {
                                                                                                                                                                                                                                                                                                                        i = R.id.player_top_holder;
                                                                                                                                                                                                                                                                                                                        FrameLayout frameLayout9 = (FrameLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                                                                        if (frameLayout9 != null) {
                                                                                                                                                                                                                                                                                                                            i = R.id.player_tracks_btt;
                                                                                                                                                                                                                                                                                                                            MaterialButton materialButton11 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                                                                            if (materialButton11 != null) {
                                                                                                                                                                                                                                                                                                                                i = R.id.player_video_bar;
                                                                                                                                                                                                                                                                                                                                ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                                                                                if (constraintLayout2 != null) {
                                                                                                                                                                                                                                                                                                                                    i = R.id.player_video_holder;
                                                                                                                                                                                                                                                                                                                                    ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                                                                                    if (constraintLayout3 != null) {
                                                                                                                                                                                                                                                                                                                                        i = R.id.player_video_info;
                                                                                                                                                                                                                                                                                                                                        TextView textView17 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                                                                                        if (textView17 != null) {
                                                                                                                                                                                                                                                                                                                                            i = R.id.player_video_title;
                                                                                                                                                                                                                                                                                                                                            TextView textView18 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                                                                                            if (textView18 != null) {
                                                                                                                                                                                                                                                                                                                                                i = R.id.player_video_title_holder;
                                                                                                                                                                                                                                                                                                                                                LinearLayout linearLayout11 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                                                                                                if (linearLayout11 != null) {
                                                                                                                                                                                                                                                                                                                                                    i = R.id.player_video_title_rez;
                                                                                                                                                                                                                                                                                                                                                    TextView textView19 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                                                                                                    if (textView19 != null) {
                                                                                                                                                                                                                                                                                                                                                        i = R.id.previewFrameLayout;
                                                                                                                                                                                                                                                                                                                                                        FrameLayout frameLayout10 = (FrameLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                                                                                                        if (frameLayout10 != null) {
                                                                                                                                                                                                                                                                                                                                                            i = R.id.previewImageView;
                                                                                                                                                                                                                                                                                                                                                            ImageView imageView13 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                                                                                                            if (imageView13 != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = R.id.shadow_overlay))) != null) {
                                                                                                                                                                                                                                                                                                                                                                i = R.id.skip_chapter_button;
                                                                                                                                                                                                                                                                                                                                                                MaterialButton materialButton12 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                                                                                                                if (materialButton12 != null) {
                                                                                                                                                                                                                                                                                                                                                                    i = R.id.subtitle_holder;
                                                                                                                                                                                                                                                                                                                                                                    FrameLayout frameLayout11 = (FrameLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                                                                                                                    if (frameLayout11 != null) {
                                                                                                                                                                                                                                                                                                                                                                        i = R.id.time_left;
                                                                                                                                                                                                                                                                                                                                                                        TextView textView20 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                                                                                                                        if (textView20 != null) {
                                                                                                                                                                                                                                                                                                                                                                            i = R.id.time_live;
                                                                                                                                                                                                                                                                                                                                                                            TextView textView21 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                                                                                                                            if (textView21 != null) {
                                                                                                                                                                                                                                                                                                                                                                                i = R.id.video_outline;
                                                                                                                                                                                                                                                                                                                                                                                ImageView imageView14 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                                                                                                                                                if (imageView14 != null) {
                                                                                                                                                                                                                                                                                                                                                                                    return new TrailerCustomLayoutBinding(frameLayout4, linearLayout, linearLayout2, cardView, imageView, textView, linearProgressIndicator, textView2, textView3, textView4, textView5, imageButton, imageButton2, imageButton3, textView6, imageButton4, livePreviewTimeBar, imageButton5, textView7, imageButton6, imageView2, frameLayout, progressBar, constraintLayout, horizontalScrollView, materialButton, frameLayout2, recyclerView, linearLayout3, textView8, imageView3, linearLayout4, textView9, imageButton7, frameLayout3, imageView4, imageView5, linearLayout5, linearLayout6, textView10, imageView6, linearLayout7, textView11, frameLayout4, frameLayout5, materialButton2, linearLayout8, linearLayout9, frameLayout6, imageView7, textView12, textView13, textView14, imageView8, imageView9, frameLayout7, relativeLayout, imageView10, progressBar2, progressBar3, relativeLayout2, imageView11, progressBar4, progressBar5, materialButton3, imageView12, linearLayout10, textView15, imageButton8, frameLayout8, materialButton4, materialButton5, materialButton6, materialButton7, materialButton8, materialButton9, materialButton10, textView16, frameLayout9, materialButton11, constraintLayout2, constraintLayout3, textView17, textView18, linearLayout11, textView19, frameLayout10, imageView13, viewFindChildViewById, materialButton12, frameLayout11, textView20, textView21, imageView14);
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
