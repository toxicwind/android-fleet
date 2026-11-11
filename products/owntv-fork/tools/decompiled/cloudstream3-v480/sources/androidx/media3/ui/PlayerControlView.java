package androidx.media3.ui;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.core.content.res.ResourcesCompat;
import androidx.emoji2.text.ConcurrencyHelpers$$ExternalSyntheticLambda0;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.C;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Metadata;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.TrackSelectionOverride;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.ViewProvider;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.RepeatModeUtil;
import androidx.media3.common.util.Util;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import j$.util.Objects;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;

/* JADX INFO: loaded from: classes5.dex */
public class PlayerControlView extends FrameLayout {
    public static final int DEFAULT_REPEAT_TOGGLE_MODES = 0;
    public static final int DEFAULT_SHOW_TIMEOUT_MS = 5000;
    public static final int DEFAULT_TIME_BAR_MIN_UPDATE_INTERVAL_MS = 200;
    private static final int MAX_UPDATE_INTERVAL_MS = 1000;
    public static final int MAX_WINDOWS_FOR_MULTI_WINDOW_TIME_BAR = 100;
    private static final float[] PLAYBACK_SPEEDS;
    private static final int SETTINGS_AUDIO_TRACK_SELECTION_POSITION = 1;
    private static final int SETTINGS_PLAYBACK_SPEED_POSITION = 0;
    private static final String TAG = "PlayerControlView";
    private long[] adGroupTimesMs;
    private final View audioTrackButton;
    private final AudioTrackSelectionAdapter audioTrackSelectionAdapter;
    private final float buttonAlphaDisabled;
    private final float buttonAlphaEnabled;
    private final ComponentListener componentListener;
    private final Class<?> compositionPlayerClazz;
    private final Method compositionPlayerIsScrubbingModeEnabledMethod;
    private final Method compositionPlayerSetScrubbingModeEnabledMethod;
    private final PlayerControlViewLayoutManager controlViewLayoutManager;
    private long currentWindowOffset;
    private final TextView durationView;
    private final Class<?> exoplayerClazz;
    private long[] extraAdGroupTimesMs;
    private boolean[] extraPlayedAdGroups;
    private final View fastForwardButton;
    private final TextView fastForwardButtonTextView;
    private final StringBuilder formatBuilder;
    private final Formatter formatter;
    private final ImageView fullscreenButton;
    private final String fullscreenEnterContentDescription;
    private final Drawable fullscreenEnterDrawable;
    private final String fullscreenExitContentDescription;
    private final Drawable fullscreenExitDrawable;
    private final Handler handler;
    private boolean isAttachedToWindow;
    private boolean isFullscreen;
    private final Method isScrubbingModeEnabledMethod;
    private final ImageView minimalFullscreenButton;
    private boolean multiWindowTimeBar;
    private boolean needToHideBars;
    private final ImageView nextButton;
    private OnFullScreenModeChangedListener onFullScreenModeChangedListener;
    private final Drawable pauseButtonDrawable;
    private final Timeline.Period period;
    private final Drawable playButtonDrawable;
    private final ImageView playPauseButton;
    private final PlaybackSpeedAdapter playbackSpeedAdapter;
    private final View playbackSpeedButton;
    private boolean[] playedAdGroups;
    private Player player;
    private final TextView positionView;
    private final ImageView previousButton;
    private ProgressUpdateListener progressUpdateListener;
    private final String repeatAllButtonContentDescription;
    private final Drawable repeatAllButtonDrawable;
    private final String repeatOffButtonContentDescription;
    private final Drawable repeatOffButtonDrawable;
    private final String repeatOneButtonContentDescription;
    private final Drawable repeatOneButtonDrawable;
    private final ImageView repeatToggleButton;
    private int repeatToggleModes;
    private final Resources resources;
    private final View rewindButton;
    private final TextView rewindButtonTextView;
    private boolean scrubbing;
    private final Method setScrubbingModeEnabledMethod;
    private final SettingsAdapter settingsAdapter;
    private final View settingsButton;
    private final RecyclerView settingsView;
    private final PopupWindow settingsWindow;
    private final int settingsWindowMargin;
    private boolean showMultiWindowTimeBar;
    private boolean showPlayButtonIfSuppressed;
    private int showTimeoutMs;
    private final ImageView shuffleButton;
    private final Drawable shuffleOffButtonDrawable;
    private final String shuffleOffContentDescription;
    private final Drawable shuffleOnButtonDrawable;
    private final String shuffleOnContentDescription;
    private final ImageView subtitleButton;
    private final Drawable subtitleOffButtonDrawable;
    private final String subtitleOffContentDescription;
    private final Drawable subtitleOnButtonDrawable;
    private final String subtitleOnContentDescription;
    private final TextTrackSelectionAdapter textTrackSelectionAdapter;
    private final TimeBar timeBar;
    private int timeBarMinUpdateIntervalMs;
    private boolean timeBarScrubbingEnabled;
    private final TrackNameProvider trackNameProvider;
    private final Runnable updateProgressAction;
    private final CopyOnWriteArrayList<VisibilityListener> visibilityListeners;
    private final ImageView vrButton;
    private final Timeline.Window window;

    @Deprecated
    public interface OnFullScreenModeChangedListener {
        void onFullScreenModeChanged(boolean z);
    }

    public interface ProgressUpdateListener {
        void onProgressUpdate(long j, long j2);
    }

    @Deprecated
    public interface VisibilityListener {
        void onVisibilityChange(int i);
    }

    private static boolean isHandledMediaKey(int i) {
        return i == 90 || i == 89 || i == 85 || i == 79 || i == 126 || i == 127 || i == 87 || i == 88;
    }

    static {
        MediaLibraryInfo.registerModule("media3.ui");
        PLAYBACK_SPEEDS = new float[]{0.25f, 0.5f, 0.75f, 1.0f, 1.25f, 1.5f, 2.0f};
    }

    public PlayerControlView(Context context) {
        this(context, null);
    }

    public PlayerControlView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PlayerControlView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, attributeSet);
    }

    /* JADX WARN: Code duplicated, block: B:100:0x047f  */
    /* JADX WARN: Code duplicated, block: B:101:0x048b  */
    /* JADX WARN: Code duplicated, block: B:105:0x05fc  */
    /* JADX WARN: Code duplicated, block: B:44:0x0283  */
    /* JADX WARN: Code duplicated, block: B:47:0x02b6  */
    /* JADX WARN: Code duplicated, block: B:50:0x02c5  */
    /* JADX WARN: Code duplicated, block: B:53:0x02d4  */
    /* JADX WARN: Code duplicated, block: B:56:0x02e9  */
    /* JADX WARN: Code duplicated, block: B:57:0x0305 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:58:0x0307  */
    /* JADX WARN: Code duplicated, block: B:59:0x0351  */
    /* JADX WARN: Code duplicated, block: B:62:0x036f  */
    /* JADX WARN: Code duplicated, block: B:65:0x038c  */
    /* JADX WARN: Code duplicated, block: B:68:0x039d  */
    /* JADX WARN: Code duplicated, block: B:71:0x03b7  */
    /* JADX WARN: Code duplicated, block: B:74:0x03db  */
    /* JADX WARN: Code duplicated, block: B:75:0x03ea  */
    /* JADX WARN: Code duplicated, block: B:77:0x03ed  */
    /* JADX WARN: Code duplicated, block: B:78:0x03f5  */
    /* JADX WARN: Code duplicated, block: B:81:0x03fd  */
    /* JADX WARN: Code duplicated, block: B:84:0x0414  */
    /* JADX WARN: Code duplicated, block: B:85:0x0423  */
    /* JADX WARN: Code duplicated, block: B:87:0x0426  */
    /* JADX WARN: Code duplicated, block: B:88:0x042e  */
    /* JADX WARN: Code duplicated, block: B:91:0x0436  */
    /* JADX WARN: Code duplicated, block: B:94:0x0447  */
    /* JADX WARN: Code duplicated, block: B:97:0x0458  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v11, types: [android.view.View, android.widget.TextView] */
    /* JADX WARN: Type inference failed for: r8v3, types: [androidx.media3.ui.TimeBar] */
    /* JADX WARN: Type inference failed for: r8v30 */
    /* JADX WARN: Type inference failed for: r8v33 */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v5 */
    public PlayerControlView(Context context, AttributeSet attributeSet, int i, AttributeSet attributeSet2) throws NoSuchMethodException {
        int i2;
        final PlayerControlView playerControlView;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        boolean z7;
        boolean z8;
        int i10;
        int i11;
        int i12;
        String str;
        Class<?> cls;
        Method method;
        Method method2;
        boolean z9;
        Method method3;
        Method method4;
        Class<?> cls2;
        Method method5;
        ImageView imageView;
        View viewFindViewById;
        View viewFindViewById2;
        View viewFindViewById3;
        TimeBar timeBar;
        View viewFindViewById4;
        Context context2;
        final PlayerControlView playerControlView2;
        ?? r8;
        TimeBar timeBar2;
        Resources resources;
        ImageView imageView2;
        ImageView imageView3;
        ImageView imageView4;
        Typeface font;
        ImageView imageView5;
        TextView textView;
        ?? r6;
        View view;
        ImageView imageView6;
        TextView textView2;
        View view2;
        ImageView imageView7;
        ImageView imageView8;
        ImageView imageView9;
        boolean z10;
        Class<?>[] clsArr;
        super(context, attributeSet, i);
        int i13 = R.layout.exo_player_control_view;
        int resourceId = R.drawable.exo_styled_controls_play;
        int resourceId2 = R.drawable.exo_styled_controls_pause;
        int i14 = R.drawable.exo_styled_controls_next;
        int i15 = R.drawable.exo_styled_controls_simple_fastforward;
        int resourceId3 = R.drawable.exo_styled_controls_previous;
        int resourceId4 = R.drawable.exo_styled_controls_simple_rewind;
        int i16 = R.drawable.exo_styled_controls_fullscreen_exit;
        int i17 = R.drawable.exo_styled_controls_fullscreen_enter;
        int i18 = R.drawable.exo_styled_controls_repeat_off;
        int i19 = R.drawable.exo_styled_controls_repeat_one;
        int i20 = R.drawable.exo_styled_controls_repeat_all;
        String str2 = "isScrubbingModeEnabled";
        int i21 = R.drawable.exo_styled_controls_shuffle_on;
        int i22 = R.drawable.exo_styled_controls_shuffle_off;
        int resourceId5 = R.drawable.exo_styled_controls_subtitle_on;
        int resourceId6 = R.drawable.exo_styled_controls_subtitle_off;
        int i23 = R.drawable.exo_styled_controls_vr;
        this.showPlayButtonIfSuppressed = true;
        this.showTimeoutMs = 5000;
        this.repeatToggleModes = 0;
        this.timeBarMinUpdateIntervalMs = 200;
        if (attributeSet2 != null) {
            TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet2, R.styleable.PlayerControlView, i, 0);
            try {
                int resourceId7 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.PlayerControlView_controller_layout_id, i13);
                resourceId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.PlayerControlView_play_icon, resourceId);
                resourceId2 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.PlayerControlView_pause_icon, resourceId2);
                int resourceId8 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.PlayerControlView_next_icon, i14);
                int resourceId9 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.PlayerControlView_fastforward_icon, i15);
                resourceId3 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.PlayerControlView_previous_icon, resourceId3);
                resourceId4 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.PlayerControlView_rewind_icon, resourceId4);
                int resourceId10 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.PlayerControlView_fullscreen_exit_icon, i16);
                int resourceId11 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.PlayerControlView_fullscreen_enter_icon, i17);
                int resourceId12 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.PlayerControlView_repeat_off_icon, i18);
                int resourceId13 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.PlayerControlView_repeat_one_icon, i19);
                int resourceId14 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.PlayerControlView_repeat_all_icon, i20);
                int resourceId15 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.PlayerControlView_shuffle_on_icon, i21);
                int resourceId16 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.PlayerControlView_shuffle_off_icon, i22);
                resourceId5 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.PlayerControlView_subtitle_on_icon, resourceId5);
                resourceId6 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.PlayerControlView_subtitle_off_icon, resourceId6);
                int resourceId17 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.PlayerControlView_vr_icon, i23);
                playerControlView = this;
                playerControlView.showTimeoutMs = typedArrayObtainStyledAttributes.getInt(R.styleable.PlayerControlView_show_timeout, playerControlView.showTimeoutMs);
                playerControlView.repeatToggleModes = getRepeatToggleModes(typedArrayObtainStyledAttributes, playerControlView.repeatToggleModes);
                boolean z11 = typedArrayObtainStyledAttributes.getBoolean(R.styleable.PlayerControlView_show_rewind_button, true);
                boolean z12 = typedArrayObtainStyledAttributes.getBoolean(R.styleable.PlayerControlView_show_fastforward_button, true);
                boolean z13 = typedArrayObtainStyledAttributes.getBoolean(R.styleable.PlayerControlView_show_previous_button, true);
                boolean z14 = typedArrayObtainStyledAttributes.getBoolean(R.styleable.PlayerControlView_show_next_button, true);
                boolean z15 = typedArrayObtainStyledAttributes.getBoolean(R.styleable.PlayerControlView_show_shuffle_button, false);
                boolean z16 = typedArrayObtainStyledAttributes.getBoolean(R.styleable.PlayerControlView_show_subtitle_button, false);
                boolean z17 = typedArrayObtainStyledAttributes.getBoolean(R.styleable.PlayerControlView_show_vr_button, false);
                playerControlView.timeBarScrubbingEnabled = typedArrayObtainStyledAttributes.getBoolean(R.styleable.PlayerControlView_time_bar_scrubbing_enabled, false);
                playerControlView.setTimeBarMinUpdateInterval(typedArrayObtainStyledAttributes.getInt(R.styleable.PlayerControlView_time_bar_min_update_interval, playerControlView.timeBarMinUpdateIntervalMs));
                boolean z18 = typedArrayObtainStyledAttributes.getBoolean(R.styleable.PlayerControlView_animation_enabled, true);
                typedArrayObtainStyledAttributes.recycle();
                i4 = resourceId9;
                i5 = resourceId10;
                i6 = resourceId11;
                i7 = resourceId12;
                i8 = resourceId13;
                i9 = resourceId14;
                i2 = resourceId7;
                z = z11;
                z2 = z12;
                z3 = z13;
                z4 = z14;
                z6 = z15;
                z7 = z16;
                z8 = z17;
                i10 = resourceId16;
                z5 = z18;
                i3 = resourceId8;
                i11 = resourceId17;
                i12 = resourceId15;
            } catch (Throwable th) {
                typedArrayObtainStyledAttributes.recycle();
                throw th;
            }
        } else {
            i2 = i13;
            playerControlView = this;
            i3 = i14;
            i4 = i15;
            i5 = i16;
            i6 = i17;
            i7 = i18;
            i8 = i19;
            i9 = i20;
            z = true;
            z2 = true;
            z3 = true;
            z4 = true;
            z5 = true;
            z6 = false;
            z7 = false;
            z8 = false;
            i10 = i22;
            i11 = i23;
            i12 = i21;
        }
        int i24 = resourceId3;
        int i25 = resourceId4;
        int i26 = resourceId5;
        int i27 = resourceId6;
        LayoutInflater.from(context).inflate(i2, playerControlView);
        playerControlView.setDescendantFocusability(262144);
        playerControlView.componentListener = new ComponentListener();
        playerControlView.visibilityListeners = new CopyOnWriteArrayList<>();
        playerControlView.period = new Timeline.Period();
        playerControlView.window = new Timeline.Window();
        StringBuilder sb = new StringBuilder();
        playerControlView.formatBuilder = sb;
        int i28 = resourceId;
        playerControlView.formatter = new Formatter(sb, Locale.getDefault());
        playerControlView.adGroupTimesMs = new long[0];
        playerControlView.playedAdGroups = new boolean[0];
        playerControlView.extraAdGroupTimesMs = new long[0];
        playerControlView.extraPlayedAdGroups = new boolean[0];
        playerControlView.updateProgressAction = new Runnable() { // from class: androidx.media3.ui.PlayerControlView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.updateProgress();
            }
        };
        try {
            cls = Class.forName("androidx.media3.exoplayer.ExoPlayer");
            try {
                str = "setScrubbingModeEnabled";
                try {
                    try {
                        try {
                            method = cls.getMethod(str, Boolean.TYPE);
                            str2 = str2;
                            try {
                                method2 = cls.getMethod(str2, null);
                            } catch (ClassNotFoundException | NoSuchMethodException unused) {
                                method2 = null;
                            }
                        } catch (ClassNotFoundException | NoSuchMethodException unused2) {
                            method = null;
                            method2 = null;
                            playerControlView.exoplayerClazz = cls;
                            playerControlView.setScrubbingModeEnabledMethod = method;
                            playerControlView.isScrubbingModeEnabledMethod = method2;
                            cls2 = Class.forName("androidx.media3.transformer.CompositionPlayer");
                            z9 = true;
                            clsArr = new Class[1];
                            try {
                                clsArr[0] = Boolean.TYPE;
                                method4 = cls2.getMethod(str, clsArr);
                                method3 = null;
                                try {
                                    method5 = cls2.getMethod(str2, null);
                                } catch (ClassNotFoundException | NoSuchMethodException unused3) {
                                    method5 = method3;
                                }
                            } catch (ClassNotFoundException | NoSuchMethodException unused4) {
                                method3 = null;
                                method4 = method3;
                                method5 = method3;
                                playerControlView.compositionPlayerClazz = cls2;
                                playerControlView.compositionPlayerSetScrubbingModeEnabledMethod = method4;
                                playerControlView.compositionPlayerIsScrubbingModeEnabledMethod = method5;
                                playerControlView.durationView = (TextView) playerControlView.findViewById(R.id.exo_duration);
                                playerControlView.positionView = (TextView) playerControlView.findViewById(R.id.exo_position);
                                imageView = (ImageView) playerControlView.findViewById(R.id.exo_subtitle);
                                playerControlView.subtitleButton = imageView;
                                if (imageView != null) {
                                    imageView.setOnClickListener(playerControlView.componentListener);
                                }
                                ImageView imageView10 = (ImageView) playerControlView.findViewById(R.id.exo_fullscreen);
                                playerControlView.fullscreenButton = imageView10;
                                initializeFullscreenButton(imageView10, new View.OnClickListener() { // from class: androidx.media3.ui.PlayerControlView$$ExternalSyntheticLambda1
                                    @Override // android.view.View.OnClickListener
                                    public final void onClick(View view3) {
                                        this.f$0.onFullscreenButtonClicked(view3);
                                    }
                                });
                                ImageView imageView11 = (ImageView) playerControlView.findViewById(R.id.exo_minimal_fullscreen);
                                playerControlView.minimalFullscreenButton = imageView11;
                                initializeFullscreenButton(imageView11, new View.OnClickListener() { // from class: androidx.media3.ui.PlayerControlView$$ExternalSyntheticLambda1
                                    @Override // android.view.View.OnClickListener
                                    public final void onClick(View view3) {
                                        this.f$0.onFullscreenButtonClicked(view3);
                                    }
                                });
                                viewFindViewById = playerControlView.findViewById(R.id.exo_settings);
                                playerControlView.settingsButton = viewFindViewById;
                                if (viewFindViewById != null) {
                                    viewFindViewById.setOnClickListener(playerControlView.componentListener);
                                }
                                viewFindViewById2 = playerControlView.findViewById(R.id.exo_playback_speed);
                                playerControlView.playbackSpeedButton = viewFindViewById2;
                                if (viewFindViewById2 != null) {
                                    viewFindViewById2.setOnClickListener(playerControlView.componentListener);
                                }
                                viewFindViewById3 = playerControlView.findViewById(R.id.exo_audio_track);
                                playerControlView.audioTrackButton = viewFindViewById3;
                                if (viewFindViewById3 != null) {
                                    viewFindViewById3.setOnClickListener(playerControlView.componentListener);
                                }
                                timeBar = (TimeBar) playerControlView.findViewById(R.id.exo_progress);
                                viewFindViewById4 = playerControlView.findViewById(R.id.exo_progress_placeholder);
                                if (timeBar != null) {
                                    playerControlView.timeBar = timeBar;
                                    context2 = context;
                                    playerControlView2 = playerControlView;
                                    r8 = method3;
                                } else if (viewFindViewById4 != null) {
                                    r8 = method3;
                                    playerControlView2 = this;
                                    context2 = context;
                                    DefaultTimeBar defaultTimeBar = new DefaultTimeBar(context2, null, 0, attributeSet2, R.style.ExoStyledControls_TimeBar);
                                    defaultTimeBar.setId(R.id.exo_progress);
                                    defaultTimeBar.setLayoutParams(viewFindViewById4.getLayoutParams());
                                    ViewGroup viewGroup = (ViewGroup) viewFindViewById4.getParent();
                                    int iIndexOfChild = viewGroup.indexOfChild(viewFindViewById4);
                                    viewGroup.removeView(viewFindViewById4);
                                    viewGroup.addView(defaultTimeBar, iIndexOfChild);
                                    playerControlView2.timeBar = defaultTimeBar;
                                } else {
                                    context2 = context;
                                    playerControlView2 = playerControlView;
                                    ?? r9 = method3;
                                    playerControlView2.timeBar = r9;
                                    r8 = r9;
                                }
                                timeBar2 = playerControlView2.timeBar;
                                if (timeBar2 != null) {
                                    timeBar2.addListener(playerControlView2.componentListener);
                                }
                                playerControlView2.handler = Util.createHandlerForCurrentLooper();
                                resources = context2.getResources();
                                playerControlView2.resources = resources;
                                imageView2 = (ImageView) playerControlView2.findViewById(R.id.exo_play_pause);
                                playerControlView2.playPauseButton = imageView2;
                                if (imageView2 != null) {
                                    imageView2.setOnClickListener(playerControlView2.componentListener);
                                }
                                imageView3 = (ImageView) playerControlView2.findViewById(R.id.exo_prev);
                                playerControlView2.previousButton = imageView3;
                                if (imageView3 != null) {
                                    imageView3.setImageDrawable(Util.getDrawable(context2, resources, i24));
                                    imageView3.setOnClickListener(playerControlView2.componentListener);
                                }
                                imageView4 = (ImageView) playerControlView2.findViewById(R.id.exo_next);
                                playerControlView2.nextButton = imageView4;
                                if (imageView4 != null) {
                                    imageView4.setImageDrawable(Util.getDrawable(context2, resources, i3));
                                    imageView4.setOnClickListener(playerControlView2.componentListener);
                                }
                                font = ResourcesCompat.getFont(context2, R.font.roboto_medium_numbers);
                                imageView5 = (ImageView) playerControlView2.findViewById(R.id.exo_rew);
                                textView = (TextView) playerControlView2.findViewById(R.id.exo_rew_with_amount);
                                if (imageView5 != null) {
                                    imageView5.setImageDrawable(Util.getDrawable(context2, resources, i25));
                                    playerControlView2.rewindButton = imageView5;
                                    playerControlView2.rewindButtonTextView = null;
                                } else {
                                    r6 = r8;
                                    if (textView != null) {
                                        textView.setTypeface(font);
                                        playerControlView2.rewindButtonTextView = textView;
                                        playerControlView2.rewindButton = textView;
                                    } else {
                                        playerControlView2.rewindButtonTextView = r6;
                                        playerControlView2.rewindButton = r6;
                                    }
                                }
                                view = playerControlView2.rewindButton;
                                if (view != null) {
                                    view.setOnClickListener(playerControlView2.componentListener);
                                }
                                imageView6 = (ImageView) playerControlView2.findViewById(R.id.exo_ffwd);
                                textView2 = (TextView) playerControlView2.findViewById(R.id.exo_ffwd_with_amount);
                                if (imageView6 != null) {
                                    imageView6.setImageDrawable(Util.getDrawable(context2, resources, i4));
                                    playerControlView2.fastForwardButton = imageView6;
                                    playerControlView2.fastForwardButtonTextView = null;
                                } else if (textView2 != null) {
                                    textView2.setTypeface(font);
                                    playerControlView2.fastForwardButtonTextView = textView2;
                                    playerControlView2.fastForwardButton = textView2;
                                } else {
                                    playerControlView2.fastForwardButtonTextView = null;
                                    playerControlView2.fastForwardButton = null;
                                }
                                view2 = playerControlView2.fastForwardButton;
                                if (view2 != null) {
                                    view2.setOnClickListener(playerControlView2.componentListener);
                                }
                                imageView7 = (ImageView) playerControlView2.findViewById(R.id.exo_repeat_toggle);
                                playerControlView2.repeatToggleButton = imageView7;
                                if (imageView7 != null) {
                                    imageView7.setOnClickListener(playerControlView2.componentListener);
                                }
                                imageView8 = (ImageView) playerControlView2.findViewById(R.id.exo_shuffle);
                                playerControlView2.shuffleButton = imageView8;
                                if (imageView8 != null) {
                                    imageView8.setOnClickListener(playerControlView2.componentListener);
                                }
                                playerControlView2.buttonAlphaEnabled = resources.getInteger(R.integer.exo_media_button_opacity_percentage_enabled) / 100.0f;
                                playerControlView2.buttonAlphaDisabled = resources.getInteger(R.integer.exo_media_button_opacity_percentage_disabled) / 100.0f;
                                imageView9 = (ImageView) playerControlView2.findViewById(R.id.exo_vr);
                                playerControlView2.vrButton = imageView9;
                                if (imageView9 != null) {
                                    imageView9.setImageDrawable(Util.getDrawable(context2, resources, i11));
                                    z10 = false;
                                    playerControlView2.updateButton(false, imageView9);
                                } else {
                                    z10 = false;
                                }
                                PlayerControlViewLayoutManager playerControlViewLayoutManager = new PlayerControlViewLayoutManager(playerControlView2);
                                playerControlView2.controlViewLayoutManager = playerControlViewLayoutManager;
                                playerControlViewLayoutManager.setAnimationEnabled(z5);
                                String string = resources.getString(R.string.exo_controls_playback_speed);
                                boolean z19 = z10;
                                Drawable drawable = Util.getDrawable(context2, resources, R.drawable.exo_styled_controls_speed);
                                String[] strArr = {string, resources.getString(R.string.exo_track_selection_title_audio)};
                                Drawable drawable2 = Util.getDrawable(context2, resources, R.drawable.exo_styled_controls_audiotrack);
                                Drawable[] drawableArr = new Drawable[2];
                                drawableArr[z19 ? 1 : 0] = drawable;
                                drawableArr[z9] = drawable2;
                                SettingsAdapter settingsAdapter = playerControlView2.new SettingsAdapter(strArr, drawableArr);
                                playerControlView2.settingsAdapter = settingsAdapter;
                                playerControlView2.settingsWindowMargin = resources.getDimensionPixelSize(R.dimen.exo_settings_offset);
                                RecyclerView recyclerView = (RecyclerView) LayoutInflater.from(context2).inflate(R.layout.exo_styled_settings_list, (ViewGroup) null);
                                playerControlView2.settingsView = recyclerView;
                                recyclerView.setAdapter(settingsAdapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(playerControlView2.getContext()));
                                boolean z20 = z9;
                                PopupWindow popupWindow = new PopupWindow(recyclerView, -2, -2, z20);
                                playerControlView2.settingsWindow = popupWindow;
                                popupWindow.setOnDismissListener(playerControlView2.componentListener);
                                playerControlView2.needToHideBars = z20;
                                playerControlView2.trackNameProvider = new DefaultTrackNameProvider(playerControlView2.getResources());
                                playerControlView2.subtitleOnButtonDrawable = Util.getDrawable(context2, resources, i26);
                                playerControlView2.subtitleOffButtonDrawable = Util.getDrawable(context2, resources, i27);
                                playerControlView2.subtitleOnContentDescription = resources.getString(R.string.exo_controls_cc_enabled_description);
                                playerControlView2.subtitleOffContentDescription = resources.getString(R.string.exo_controls_cc_disabled_description);
                                playerControlView2.textTrackSelectionAdapter = new TextTrackSelectionAdapter();
                                playerControlView2.audioTrackSelectionAdapter = new AudioTrackSelectionAdapter();
                                playerControlView2.playbackSpeedAdapter = playerControlView2.new PlaybackSpeedAdapter(resources.getStringArray(R.array.exo_controls_playback_speeds), PLAYBACK_SPEEDS);
                                playerControlView2.playButtonDrawable = Util.getDrawable(context2, resources, i28);
                                playerControlView2.pauseButtonDrawable = Util.getDrawable(context2, resources, resourceId2);
                                playerControlView2.fullscreenExitDrawable = Util.getDrawable(context2, resources, i5);
                                playerControlView2.fullscreenEnterDrawable = Util.getDrawable(context2, resources, i6);
                                playerControlView2.repeatOffButtonDrawable = Util.getDrawable(context2, resources, i7);
                                playerControlView2.repeatOneButtonDrawable = Util.getDrawable(context2, resources, i8);
                                playerControlView2.repeatAllButtonDrawable = Util.getDrawable(context2, resources, i9);
                                playerControlView2.shuffleOnButtonDrawable = Util.getDrawable(context2, resources, i12);
                                playerControlView2.shuffleOffButtonDrawable = Util.getDrawable(context2, resources, i10);
                                playerControlView2.fullscreenExitContentDescription = resources.getString(R.string.exo_controls_fullscreen_exit_description);
                                playerControlView2.fullscreenEnterContentDescription = resources.getString(R.string.exo_controls_fullscreen_enter_description);
                                playerControlView2.repeatOffButtonContentDescription = resources.getString(R.string.exo_controls_repeat_off_description);
                                playerControlView2.repeatOneButtonContentDescription = resources.getString(R.string.exo_controls_repeat_one_description);
                                playerControlView2.repeatAllButtonContentDescription = resources.getString(R.string.exo_controls_repeat_all_description);
                                playerControlView2.shuffleOnContentDescription = resources.getString(R.string.exo_controls_shuffle_on_description);
                                playerControlView2.shuffleOffContentDescription = resources.getString(R.string.exo_controls_shuffle_off_description);
                                playerControlViewLayoutManager.setShowButton((ViewGroup) playerControlView2.findViewById(R.id.exo_bottom_bar), true);
                                playerControlViewLayoutManager.setShowButton(playerControlView2.fastForwardButton, z2);
                                playerControlViewLayoutManager.setShowButton(playerControlView2.rewindButton, z);
                                playerControlViewLayoutManager.setShowButton(imageView3, z3);
                                playerControlViewLayoutManager.setShowButton(imageView4, z4);
                                playerControlViewLayoutManager.setShowButton(imageView8, z6);
                                playerControlViewLayoutManager.setShowButton(imageView, z7);
                                playerControlViewLayoutManager.setShowButton(imageView9, z8);
                                playerControlViewLayoutManager.setShowButton(imageView7, playerControlView2.repeatToggleModes == 0 ? z19 ? 1 : 0 : true);
                                playerControlView2.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: androidx.media3.ui.PlayerControlView$$ExternalSyntheticLambda2
                                    @Override // android.view.View.OnLayoutChangeListener
                                    public final void onLayoutChange(View view3, int i29, int i30, int i31, int i32, int i33, int i34, int i35, int i36) {
                                        this.f$0.onLayoutChange(view3, i29, i30, i31, i32, i33, i34, i35, i36);
                                    }
                                });
                            }
                            playerControlView.compositionPlayerClazz = cls2;
                            playerControlView.compositionPlayerSetScrubbingModeEnabledMethod = method4;
                            playerControlView.compositionPlayerIsScrubbingModeEnabledMethod = method5;
                            playerControlView.durationView = (TextView) playerControlView.findViewById(R.id.exo_duration);
                            playerControlView.positionView = (TextView) playerControlView.findViewById(R.id.exo_position);
                            imageView = (ImageView) playerControlView.findViewById(R.id.exo_subtitle);
                            playerControlView.subtitleButton = imageView;
                            if (imageView != null) {
                                imageView.setOnClickListener(playerControlView.componentListener);
                            }
                            ImageView imageView12 = (ImageView) playerControlView.findViewById(R.id.exo_fullscreen);
                            playerControlView.fullscreenButton = imageView12;
                            initializeFullscreenButton(imageView12, new View.OnClickListener() { // from class: androidx.media3.ui.PlayerControlView$$ExternalSyntheticLambda1
                                @Override // android.view.View.OnClickListener
                                public final void onClick(View view3) {
                                    this.f$0.onFullscreenButtonClicked(view3);
                                }
                            });
                            ImageView imageView13 = (ImageView) playerControlView.findViewById(R.id.exo_minimal_fullscreen);
                            playerControlView.minimalFullscreenButton = imageView13;
                            initializeFullscreenButton(imageView13, new View.OnClickListener() { // from class: androidx.media3.ui.PlayerControlView$$ExternalSyntheticLambda1
                                @Override // android.view.View.OnClickListener
                                public final void onClick(View view3) {
                                    this.f$0.onFullscreenButtonClicked(view3);
                                }
                            });
                            viewFindViewById = playerControlView.findViewById(R.id.exo_settings);
                            playerControlView.settingsButton = viewFindViewById;
                            if (viewFindViewById != null) {
                                viewFindViewById.setOnClickListener(playerControlView.componentListener);
                            }
                            viewFindViewById2 = playerControlView.findViewById(R.id.exo_playback_speed);
                            playerControlView.playbackSpeedButton = viewFindViewById2;
                            if (viewFindViewById2 != null) {
                                viewFindViewById2.setOnClickListener(playerControlView.componentListener);
                            }
                            viewFindViewById3 = playerControlView.findViewById(R.id.exo_audio_track);
                            playerControlView.audioTrackButton = viewFindViewById3;
                            if (viewFindViewById3 != null) {
                                viewFindViewById3.setOnClickListener(playerControlView.componentListener);
                            }
                            timeBar = (TimeBar) playerControlView.findViewById(R.id.exo_progress);
                            viewFindViewById4 = playerControlView.findViewById(R.id.exo_progress_placeholder);
                            if (timeBar != null) {
                                playerControlView.timeBar = timeBar;
                                context2 = context;
                                playerControlView2 = playerControlView;
                                r8 = method3;
                            } else if (viewFindViewById4 != null) {
                                r8 = method3;
                                playerControlView2 = this;
                                context2 = context;
                                DefaultTimeBar defaultTimeBar2 = new DefaultTimeBar(context2, null, 0, attributeSet2, R.style.ExoStyledControls_TimeBar);
                                defaultTimeBar2.setId(R.id.exo_progress);
                                defaultTimeBar2.setLayoutParams(viewFindViewById4.getLayoutParams());
                                ViewGroup viewGroup2 = (ViewGroup) viewFindViewById4.getParent();
                                int iIndexOfChild2 = viewGroup2.indexOfChild(viewFindViewById4);
                                viewGroup2.removeView(viewFindViewById4);
                                viewGroup2.addView(defaultTimeBar2, iIndexOfChild2);
                                playerControlView2.timeBar = defaultTimeBar2;
                            } else {
                                context2 = context;
                                playerControlView2 = playerControlView;
                                ?? r10 = method3;
                                playerControlView2.timeBar = r10;
                                r8 = r10;
                            }
                            timeBar2 = playerControlView2.timeBar;
                            if (timeBar2 != null) {
                                timeBar2.addListener(playerControlView2.componentListener);
                            }
                            playerControlView2.handler = Util.createHandlerForCurrentLooper();
                            resources = context2.getResources();
                            playerControlView2.resources = resources;
                            imageView2 = (ImageView) playerControlView2.findViewById(R.id.exo_play_pause);
                            playerControlView2.playPauseButton = imageView2;
                            if (imageView2 != null) {
                                imageView2.setOnClickListener(playerControlView2.componentListener);
                            }
                            imageView3 = (ImageView) playerControlView2.findViewById(R.id.exo_prev);
                            playerControlView2.previousButton = imageView3;
                            if (imageView3 != null) {
                                imageView3.setImageDrawable(Util.getDrawable(context2, resources, i24));
                                imageView3.setOnClickListener(playerControlView2.componentListener);
                            }
                            imageView4 = (ImageView) playerControlView2.findViewById(R.id.exo_next);
                            playerControlView2.nextButton = imageView4;
                            if (imageView4 != null) {
                                imageView4.setImageDrawable(Util.getDrawable(context2, resources, i3));
                                imageView4.setOnClickListener(playerControlView2.componentListener);
                            }
                            font = ResourcesCompat.getFont(context2, R.font.roboto_medium_numbers);
                            imageView5 = (ImageView) playerControlView2.findViewById(R.id.exo_rew);
                            textView = (TextView) playerControlView2.findViewById(R.id.exo_rew_with_amount);
                            if (imageView5 != null) {
                                imageView5.setImageDrawable(Util.getDrawable(context2, resources, i25));
                                playerControlView2.rewindButton = imageView5;
                                playerControlView2.rewindButtonTextView = null;
                            } else {
                                r6 = r8;
                                if (textView != null) {
                                    textView.setTypeface(font);
                                    playerControlView2.rewindButtonTextView = textView;
                                    playerControlView2.rewindButton = textView;
                                } else {
                                    playerControlView2.rewindButtonTextView = r6;
                                    playerControlView2.rewindButton = r6;
                                }
                            }
                            view = playerControlView2.rewindButton;
                            if (view != null) {
                                view.setOnClickListener(playerControlView2.componentListener);
                            }
                            imageView6 = (ImageView) playerControlView2.findViewById(R.id.exo_ffwd);
                            textView2 = (TextView) playerControlView2.findViewById(R.id.exo_ffwd_with_amount);
                            if (imageView6 != null) {
                                imageView6.setImageDrawable(Util.getDrawable(context2, resources, i4));
                                playerControlView2.fastForwardButton = imageView6;
                                playerControlView2.fastForwardButtonTextView = null;
                            } else if (textView2 != null) {
                                textView2.setTypeface(font);
                                playerControlView2.fastForwardButtonTextView = textView2;
                                playerControlView2.fastForwardButton = textView2;
                            } else {
                                playerControlView2.fastForwardButtonTextView = null;
                                playerControlView2.fastForwardButton = null;
                            }
                            view2 = playerControlView2.fastForwardButton;
                            if (view2 != null) {
                                view2.setOnClickListener(playerControlView2.componentListener);
                            }
                            imageView7 = (ImageView) playerControlView2.findViewById(R.id.exo_repeat_toggle);
                            playerControlView2.repeatToggleButton = imageView7;
                            if (imageView7 != null) {
                                imageView7.setOnClickListener(playerControlView2.componentListener);
                            }
                            imageView8 = (ImageView) playerControlView2.findViewById(R.id.exo_shuffle);
                            playerControlView2.shuffleButton = imageView8;
                            if (imageView8 != null) {
                                imageView8.setOnClickListener(playerControlView2.componentListener);
                            }
                            playerControlView2.buttonAlphaEnabled = resources.getInteger(R.integer.exo_media_button_opacity_percentage_enabled) / 100.0f;
                            playerControlView2.buttonAlphaDisabled = resources.getInteger(R.integer.exo_media_button_opacity_percentage_disabled) / 100.0f;
                            imageView9 = (ImageView) playerControlView2.findViewById(R.id.exo_vr);
                            playerControlView2.vrButton = imageView9;
                            if (imageView9 != null) {
                                imageView9.setImageDrawable(Util.getDrawable(context2, resources, i11));
                                z10 = false;
                                playerControlView2.updateButton(false, imageView9);
                            } else {
                                z10 = false;
                            }
                            PlayerControlViewLayoutManager playerControlViewLayoutManager2 = new PlayerControlViewLayoutManager(playerControlView2);
                            playerControlView2.controlViewLayoutManager = playerControlViewLayoutManager2;
                            playerControlViewLayoutManager2.setAnimationEnabled(z5);
                            String string2 = resources.getString(R.string.exo_controls_playback_speed);
                            boolean z110 = z10;
                            Drawable drawable3 = Util.getDrawable(context2, resources, R.drawable.exo_styled_controls_speed);
                            String[] strArr2 = {string2, resources.getString(R.string.exo_track_selection_title_audio)};
                            Drawable drawable4 = Util.getDrawable(context2, resources, R.drawable.exo_styled_controls_audiotrack);
                            Drawable[] drawableArr2 = new Drawable[2];
                            drawableArr2[z110 ? 1 : 0] = drawable3;
                            drawableArr2[z9] = drawable4;
                            SettingsAdapter settingsAdapter2 = playerControlView2.new SettingsAdapter(strArr2, drawableArr2);
                            playerControlView2.settingsAdapter = settingsAdapter2;
                            playerControlView2.settingsWindowMargin = resources.getDimensionPixelSize(R.dimen.exo_settings_offset);
                            RecyclerView recyclerView2 = (RecyclerView) LayoutInflater.from(context2).inflate(R.layout.exo_styled_settings_list, (ViewGroup) null);
                            playerControlView2.settingsView = recyclerView2;
                            recyclerView2.setAdapter(settingsAdapter2);
                            recyclerView2.setLayoutManager(new LinearLayoutManager(playerControlView2.getContext()));
                            boolean z21 = z9;
                            PopupWindow popupWindow2 = new PopupWindow(recyclerView2, -2, -2, z21);
                            playerControlView2.settingsWindow = popupWindow2;
                            popupWindow2.setOnDismissListener(playerControlView2.componentListener);
                            playerControlView2.needToHideBars = z21;
                            playerControlView2.trackNameProvider = new DefaultTrackNameProvider(playerControlView2.getResources());
                            playerControlView2.subtitleOnButtonDrawable = Util.getDrawable(context2, resources, i26);
                            playerControlView2.subtitleOffButtonDrawable = Util.getDrawable(context2, resources, i27);
                            playerControlView2.subtitleOnContentDescription = resources.getString(R.string.exo_controls_cc_enabled_description);
                            playerControlView2.subtitleOffContentDescription = resources.getString(R.string.exo_controls_cc_disabled_description);
                            playerControlView2.textTrackSelectionAdapter = new TextTrackSelectionAdapter();
                            playerControlView2.audioTrackSelectionAdapter = new AudioTrackSelectionAdapter();
                            playerControlView2.playbackSpeedAdapter = playerControlView2.new PlaybackSpeedAdapter(resources.getStringArray(R.array.exo_controls_playback_speeds), PLAYBACK_SPEEDS);
                            playerControlView2.playButtonDrawable = Util.getDrawable(context2, resources, i28);
                            playerControlView2.pauseButtonDrawable = Util.getDrawable(context2, resources, resourceId2);
                            playerControlView2.fullscreenExitDrawable = Util.getDrawable(context2, resources, i5);
                            playerControlView2.fullscreenEnterDrawable = Util.getDrawable(context2, resources, i6);
                            playerControlView2.repeatOffButtonDrawable = Util.getDrawable(context2, resources, i7);
                            playerControlView2.repeatOneButtonDrawable = Util.getDrawable(context2, resources, i8);
                            playerControlView2.repeatAllButtonDrawable = Util.getDrawable(context2, resources, i9);
                            playerControlView2.shuffleOnButtonDrawable = Util.getDrawable(context2, resources, i12);
                            playerControlView2.shuffleOffButtonDrawable = Util.getDrawable(context2, resources, i10);
                            playerControlView2.fullscreenExitContentDescription = resources.getString(R.string.exo_controls_fullscreen_exit_description);
                            playerControlView2.fullscreenEnterContentDescription = resources.getString(R.string.exo_controls_fullscreen_enter_description);
                            playerControlView2.repeatOffButtonContentDescription = resources.getString(R.string.exo_controls_repeat_off_description);
                            playerControlView2.repeatOneButtonContentDescription = resources.getString(R.string.exo_controls_repeat_one_description);
                            playerControlView2.repeatAllButtonContentDescription = resources.getString(R.string.exo_controls_repeat_all_description);
                            playerControlView2.shuffleOnContentDescription = resources.getString(R.string.exo_controls_shuffle_on_description);
                            playerControlView2.shuffleOffContentDescription = resources.getString(R.string.exo_controls_shuffle_off_description);
                            playerControlViewLayoutManager2.setShowButton((ViewGroup) playerControlView2.findViewById(R.id.exo_bottom_bar), true);
                            playerControlViewLayoutManager2.setShowButton(playerControlView2.fastForwardButton, z2);
                            playerControlViewLayoutManager2.setShowButton(playerControlView2.rewindButton, z);
                            playerControlViewLayoutManager2.setShowButton(imageView3, z3);
                            playerControlViewLayoutManager2.setShowButton(imageView4, z4);
                            playerControlViewLayoutManager2.setShowButton(imageView8, z6);
                            playerControlViewLayoutManager2.setShowButton(imageView, z7);
                            playerControlViewLayoutManager2.setShowButton(imageView9, z8);
                            playerControlViewLayoutManager2.setShowButton(imageView7, playerControlView2.repeatToggleModes == 0 ? z110 ? 1 : 0 : true);
                            playerControlView2.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: androidx.media3.ui.PlayerControlView$$ExternalSyntheticLambda2
                                @Override // android.view.View.OnLayoutChangeListener
                                public final void onLayoutChange(View view3, int i29, int i30, int i31, int i32, int i33, int i34, int i35, int i36) {
                                    this.f$0.onLayoutChange(view3, i29, i30, i31, i32, i33, i34, i35, i36);
                                }
                            });
                        }
                        clsArr = new Class[1];
                        try {
                            clsArr[0] = Boolean.TYPE;
                            method4 = cls2.getMethod(str, clsArr);
                            method3 = null;
                            method5 = cls2.getMethod(str2, null);
                        } catch (ClassNotFoundException unused5) {
                        } catch (NoSuchMethodException unused6) {
                        }
                    } catch (ClassNotFoundException | NoSuchMethodException unused7) {
                        method3 = null;
                    }
                    cls2 = Class.forName("androidx.media3.transformer.CompositionPlayer");
                    z9 = true;
                } catch (ClassNotFoundException | NoSuchMethodException unused8) {
                    z9 = true;
                    method3 = null;
                    method4 = null;
                    cls2 = null;
                }
            } catch (ClassNotFoundException | NoSuchMethodException unused9) {
                str = "setScrubbingModeEnabled";
            }
        } catch (ClassNotFoundException | NoSuchMethodException unused10) {
            str = "setScrubbingModeEnabled";
            cls = null;
        }
        playerControlView.exoplayerClazz = cls;
        playerControlView.setScrubbingModeEnabledMethod = method;
        playerControlView.isScrubbingModeEnabledMethod = method2;
        playerControlView.compositionPlayerClazz = cls2;
        playerControlView.compositionPlayerSetScrubbingModeEnabledMethod = method4;
        playerControlView.compositionPlayerIsScrubbingModeEnabledMethod = method5;
        playerControlView.durationView = (TextView) playerControlView.findViewById(R.id.exo_duration);
        playerControlView.positionView = (TextView) playerControlView.findViewById(R.id.exo_position);
        imageView = (ImageView) playerControlView.findViewById(R.id.exo_subtitle);
        playerControlView.subtitleButton = imageView;
        if (imageView != null) {
            imageView.setOnClickListener(playerControlView.componentListener);
        }
        ImageView imageView14 = (ImageView) playerControlView.findViewById(R.id.exo_fullscreen);
        playerControlView.fullscreenButton = imageView14;
        initializeFullscreenButton(imageView14, new View.OnClickListener() { // from class: androidx.media3.ui.PlayerControlView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view3) {
                this.f$0.onFullscreenButtonClicked(view3);
            }
        });
        ImageView imageView15 = (ImageView) playerControlView.findViewById(R.id.exo_minimal_fullscreen);
        playerControlView.minimalFullscreenButton = imageView15;
        initializeFullscreenButton(imageView15, new View.OnClickListener() { // from class: androidx.media3.ui.PlayerControlView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view3) {
                this.f$0.onFullscreenButtonClicked(view3);
            }
        });
        viewFindViewById = playerControlView.findViewById(R.id.exo_settings);
        playerControlView.settingsButton = viewFindViewById;
        if (viewFindViewById != null) {
            viewFindViewById.setOnClickListener(playerControlView.componentListener);
        }
        viewFindViewById2 = playerControlView.findViewById(R.id.exo_playback_speed);
        playerControlView.playbackSpeedButton = viewFindViewById2;
        if (viewFindViewById2 != null) {
            viewFindViewById2.setOnClickListener(playerControlView.componentListener);
        }
        viewFindViewById3 = playerControlView.findViewById(R.id.exo_audio_track);
        playerControlView.audioTrackButton = viewFindViewById3;
        if (viewFindViewById3 != null) {
            viewFindViewById3.setOnClickListener(playerControlView.componentListener);
        }
        timeBar = (TimeBar) playerControlView.findViewById(R.id.exo_progress);
        viewFindViewById4 = playerControlView.findViewById(R.id.exo_progress_placeholder);
        if (timeBar != null) {
            playerControlView.timeBar = timeBar;
            context2 = context;
            playerControlView2 = playerControlView;
            r8 = method3;
        } else if (viewFindViewById4 != null) {
            r8 = method3;
            playerControlView2 = this;
            context2 = context;
            DefaultTimeBar defaultTimeBar3 = new DefaultTimeBar(context2, null, 0, attributeSet2, R.style.ExoStyledControls_TimeBar);
            defaultTimeBar3.setId(R.id.exo_progress);
            defaultTimeBar3.setLayoutParams(viewFindViewById4.getLayoutParams());
            ViewGroup viewGroup3 = (ViewGroup) viewFindViewById4.getParent();
            int iIndexOfChild3 = viewGroup3.indexOfChild(viewFindViewById4);
            viewGroup3.removeView(viewFindViewById4);
            viewGroup3.addView(defaultTimeBar3, iIndexOfChild3);
            playerControlView2.timeBar = defaultTimeBar3;
        } else {
            context2 = context;
            playerControlView2 = playerControlView;
            ?? r11 = method3;
            playerControlView2.timeBar = r11;
            r8 = r11;
        }
        timeBar2 = playerControlView2.timeBar;
        if (timeBar2 != null) {
            timeBar2.addListener(playerControlView2.componentListener);
        }
        playerControlView2.handler = Util.createHandlerForCurrentLooper();
        resources = context2.getResources();
        playerControlView2.resources = resources;
        imageView2 = (ImageView) playerControlView2.findViewById(R.id.exo_play_pause);
        playerControlView2.playPauseButton = imageView2;
        if (imageView2 != null) {
            imageView2.setOnClickListener(playerControlView2.componentListener);
        }
        imageView3 = (ImageView) playerControlView2.findViewById(R.id.exo_prev);
        playerControlView2.previousButton = imageView3;
        if (imageView3 != null) {
            imageView3.setImageDrawable(Util.getDrawable(context2, resources, i24));
            imageView3.setOnClickListener(playerControlView2.componentListener);
        }
        imageView4 = (ImageView) playerControlView2.findViewById(R.id.exo_next);
        playerControlView2.nextButton = imageView4;
        if (imageView4 != null) {
            imageView4.setImageDrawable(Util.getDrawable(context2, resources, i3));
            imageView4.setOnClickListener(playerControlView2.componentListener);
        }
        font = ResourcesCompat.getFont(context2, R.font.roboto_medium_numbers);
        imageView5 = (ImageView) playerControlView2.findViewById(R.id.exo_rew);
        textView = (TextView) playerControlView2.findViewById(R.id.exo_rew_with_amount);
        if (imageView5 != null) {
            imageView5.setImageDrawable(Util.getDrawable(context2, resources, i25));
            playerControlView2.rewindButton = imageView5;
            playerControlView2.rewindButtonTextView = null;
        } else {
            r6 = r8;
            if (textView != null) {
                textView.setTypeface(font);
                playerControlView2.rewindButtonTextView = textView;
                playerControlView2.rewindButton = textView;
            } else {
                playerControlView2.rewindButtonTextView = r6;
                playerControlView2.rewindButton = r6;
            }
        }
        view = playerControlView2.rewindButton;
        if (view != null) {
            view.setOnClickListener(playerControlView2.componentListener);
        }
        imageView6 = (ImageView) playerControlView2.findViewById(R.id.exo_ffwd);
        textView2 = (TextView) playerControlView2.findViewById(R.id.exo_ffwd_with_amount);
        if (imageView6 != null) {
            imageView6.setImageDrawable(Util.getDrawable(context2, resources, i4));
            playerControlView2.fastForwardButton = imageView6;
            playerControlView2.fastForwardButtonTextView = null;
        } else if (textView2 != null) {
            textView2.setTypeface(font);
            playerControlView2.fastForwardButtonTextView = textView2;
            playerControlView2.fastForwardButton = textView2;
        } else {
            playerControlView2.fastForwardButtonTextView = null;
            playerControlView2.fastForwardButton = null;
        }
        view2 = playerControlView2.fastForwardButton;
        if (view2 != null) {
            view2.setOnClickListener(playerControlView2.componentListener);
        }
        imageView7 = (ImageView) playerControlView2.findViewById(R.id.exo_repeat_toggle);
        playerControlView2.repeatToggleButton = imageView7;
        if (imageView7 != null) {
            imageView7.setOnClickListener(playerControlView2.componentListener);
        }
        imageView8 = (ImageView) playerControlView2.findViewById(R.id.exo_shuffle);
        playerControlView2.shuffleButton = imageView8;
        if (imageView8 != null) {
            imageView8.setOnClickListener(playerControlView2.componentListener);
        }
        playerControlView2.buttonAlphaEnabled = resources.getInteger(R.integer.exo_media_button_opacity_percentage_enabled) / 100.0f;
        playerControlView2.buttonAlphaDisabled = resources.getInteger(R.integer.exo_media_button_opacity_percentage_disabled) / 100.0f;
        imageView9 = (ImageView) playerControlView2.findViewById(R.id.exo_vr);
        playerControlView2.vrButton = imageView9;
        if (imageView9 != null) {
            imageView9.setImageDrawable(Util.getDrawable(context2, resources, i11));
            z10 = false;
            playerControlView2.updateButton(false, imageView9);
        } else {
            z10 = false;
        }
        PlayerControlViewLayoutManager playerControlViewLayoutManager3 = new PlayerControlViewLayoutManager(playerControlView2);
        playerControlView2.controlViewLayoutManager = playerControlViewLayoutManager3;
        playerControlViewLayoutManager3.setAnimationEnabled(z5);
        String string3 = resources.getString(R.string.exo_controls_playback_speed);
        boolean z111 = z10;
        Drawable drawable5 = Util.getDrawable(context2, resources, R.drawable.exo_styled_controls_speed);
        String[] strArr3 = {string3, resources.getString(R.string.exo_track_selection_title_audio)};
        Drawable drawable6 = Util.getDrawable(context2, resources, R.drawable.exo_styled_controls_audiotrack);
        Drawable[] drawableArr3 = new Drawable[2];
        drawableArr3[z111 ? 1 : 0] = drawable5;
        drawableArr3[z9] = drawable6;
        SettingsAdapter settingsAdapter3 = playerControlView2.new SettingsAdapter(strArr3, drawableArr3);
        playerControlView2.settingsAdapter = settingsAdapter3;
        playerControlView2.settingsWindowMargin = resources.getDimensionPixelSize(R.dimen.exo_settings_offset);
        RecyclerView recyclerView3 = (RecyclerView) LayoutInflater.from(context2).inflate(R.layout.exo_styled_settings_list, (ViewGroup) null);
        playerControlView2.settingsView = recyclerView3;
        recyclerView3.setAdapter(settingsAdapter3);
        recyclerView3.setLayoutManager(new LinearLayoutManager(playerControlView2.getContext()));
        boolean z22 = z9;
        PopupWindow popupWindow3 = new PopupWindow(recyclerView3, -2, -2, z22);
        playerControlView2.settingsWindow = popupWindow3;
        popupWindow3.setOnDismissListener(playerControlView2.componentListener);
        playerControlView2.needToHideBars = z22;
        playerControlView2.trackNameProvider = new DefaultTrackNameProvider(playerControlView2.getResources());
        playerControlView2.subtitleOnButtonDrawable = Util.getDrawable(context2, resources, i26);
        playerControlView2.subtitleOffButtonDrawable = Util.getDrawable(context2, resources, i27);
        playerControlView2.subtitleOnContentDescription = resources.getString(R.string.exo_controls_cc_enabled_description);
        playerControlView2.subtitleOffContentDescription = resources.getString(R.string.exo_controls_cc_disabled_description);
        playerControlView2.textTrackSelectionAdapter = new TextTrackSelectionAdapter();
        playerControlView2.audioTrackSelectionAdapter = new AudioTrackSelectionAdapter();
        playerControlView2.playbackSpeedAdapter = playerControlView2.new PlaybackSpeedAdapter(resources.getStringArray(R.array.exo_controls_playback_speeds), PLAYBACK_SPEEDS);
        playerControlView2.playButtonDrawable = Util.getDrawable(context2, resources, i28);
        playerControlView2.pauseButtonDrawable = Util.getDrawable(context2, resources, resourceId2);
        playerControlView2.fullscreenExitDrawable = Util.getDrawable(context2, resources, i5);
        playerControlView2.fullscreenEnterDrawable = Util.getDrawable(context2, resources, i6);
        playerControlView2.repeatOffButtonDrawable = Util.getDrawable(context2, resources, i7);
        playerControlView2.repeatOneButtonDrawable = Util.getDrawable(context2, resources, i8);
        playerControlView2.repeatAllButtonDrawable = Util.getDrawable(context2, resources, i9);
        playerControlView2.shuffleOnButtonDrawable = Util.getDrawable(context2, resources, i12);
        playerControlView2.shuffleOffButtonDrawable = Util.getDrawable(context2, resources, i10);
        playerControlView2.fullscreenExitContentDescription = resources.getString(R.string.exo_controls_fullscreen_exit_description);
        playerControlView2.fullscreenEnterContentDescription = resources.getString(R.string.exo_controls_fullscreen_enter_description);
        playerControlView2.repeatOffButtonContentDescription = resources.getString(R.string.exo_controls_repeat_off_description);
        playerControlView2.repeatOneButtonContentDescription = resources.getString(R.string.exo_controls_repeat_one_description);
        playerControlView2.repeatAllButtonContentDescription = resources.getString(R.string.exo_controls_repeat_all_description);
        playerControlView2.shuffleOnContentDescription = resources.getString(R.string.exo_controls_shuffle_on_description);
        playerControlView2.shuffleOffContentDescription = resources.getString(R.string.exo_controls_shuffle_off_description);
        playerControlViewLayoutManager3.setShowButton((ViewGroup) playerControlView2.findViewById(R.id.exo_bottom_bar), true);
        playerControlViewLayoutManager3.setShowButton(playerControlView2.fastForwardButton, z2);
        playerControlViewLayoutManager3.setShowButton(playerControlView2.rewindButton, z);
        playerControlViewLayoutManager3.setShowButton(imageView3, z3);
        playerControlViewLayoutManager3.setShowButton(imageView4, z4);
        playerControlViewLayoutManager3.setShowButton(imageView8, z6);
        playerControlViewLayoutManager3.setShowButton(imageView, z7);
        playerControlViewLayoutManager3.setShowButton(imageView9, z8);
        playerControlViewLayoutManager3.setShowButton(imageView7, playerControlView2.repeatToggleModes == 0 ? z111 ? 1 : 0 : true);
        playerControlView2.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: androidx.media3.ui.PlayerControlView$$ExternalSyntheticLambda2
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view3, int i29, int i30, int i31, int i32, int i33, int i34, int i35, int i36) {
                this.f$0.onLayoutChange(view3, i29, i30, i31, i32, i33, i34, i35, i36);
            }
        });
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        boolean z = true;
        Preconditions.checkState(Looper.myLooper() == Looper.getMainLooper());
        if (player != null && player.getApplicationLooper() != Looper.getMainLooper()) {
            z = false;
        }
        Preconditions.checkArgument(z);
        Player player2 = this.player;
        if (player2 == player) {
            return;
        }
        if (player2 != null) {
            player2.removeListener(this.componentListener);
        }
        this.player = player;
        if (player != null) {
            player.addListener(this.componentListener);
        }
        updateAll();
    }

    @Deprecated
    public void setShowMultiWindowTimeBar(boolean z) {
        this.showMultiWindowTimeBar = z;
        updateTimeline();
    }

    public void setShowPlayButtonIfPlaybackIsSuppressed(boolean z) {
        this.showPlayButtonIfSuppressed = z;
        updatePlayPauseButton();
    }

    public void setExtraAdGroupMarkers(long[] jArr, boolean[] zArr) {
        if (jArr == null) {
            this.extraAdGroupTimesMs = new long[0];
            this.extraPlayedAdGroups = new boolean[0];
        } else {
            Preconditions.checkNotNull(zArr);
            Preconditions.checkArgument(jArr.length == zArr.length);
            this.extraAdGroupTimesMs = jArr;
            this.extraPlayedAdGroups = zArr;
        }
        updateTimeline();
    }

    @Deprecated
    public void addVisibilityListener(VisibilityListener visibilityListener) {
        Preconditions.checkNotNull(visibilityListener);
        this.visibilityListeners.add(visibilityListener);
    }

    @Deprecated
    public void removeVisibilityListener(VisibilityListener visibilityListener) {
        this.visibilityListeners.remove(visibilityListener);
    }

    public void setProgressUpdateListener(ProgressUpdateListener progressUpdateListener) {
        this.progressUpdateListener = progressUpdateListener;
    }

    public void setShowRewindButton(boolean z) {
        this.controlViewLayoutManager.setShowButton(this.rewindButton, z);
        updateNavigation();
    }

    public void setShowFastForwardButton(boolean z) {
        this.controlViewLayoutManager.setShowButton(this.fastForwardButton, z);
        updateNavigation();
    }

    public void setShowPreviousButton(boolean z) {
        this.controlViewLayoutManager.setShowButton(this.previousButton, z);
        updateNavigation();
    }

    public void setShowNextButton(boolean z) {
        this.controlViewLayoutManager.setShowButton(this.nextButton, z);
        updateNavigation();
    }

    public int getShowTimeoutMs() {
        return this.showTimeoutMs;
    }

    public void setShowTimeoutMs(int i) {
        this.showTimeoutMs = i;
        if (isFullyVisible()) {
            this.controlViewLayoutManager.resetHideCallbacks();
        }
    }

    public int getRepeatToggleModes() {
        return this.repeatToggleModes;
    }

    public void setRepeatToggleModes(int i) {
        this.repeatToggleModes = i;
        Player player = this.player;
        if (player != null && player.isCommandAvailable(15)) {
            int repeatMode = this.player.getRepeatMode();
            if (i == 0 && repeatMode != 0) {
                this.player.setRepeatMode(0);
            } else if (i == 1 && repeatMode == 2) {
                this.player.setRepeatMode(1);
            } else if (i == 2 && repeatMode == 1) {
                this.player.setRepeatMode(2);
            }
        }
        this.controlViewLayoutManager.setShowButton(this.repeatToggleButton, i != 0);
        updateRepeatModeButton();
    }

    public boolean getShowShuffleButton() {
        return this.controlViewLayoutManager.getShowButton(this.shuffleButton);
    }

    public void setShowShuffleButton(boolean z) {
        this.controlViewLayoutManager.setShowButton(this.shuffleButton, z);
        updateShuffleButton();
    }

    public boolean getShowSubtitleButton() {
        return this.controlViewLayoutManager.getShowButton(this.subtitleButton);
    }

    public void setShowSubtitleButton(boolean z) {
        this.controlViewLayoutManager.setShowButton(this.subtitleButton, z);
    }

    public void setMediaRouteButtonViewProvider(ViewProvider viewProvider) {
        final View viewFindViewById = findViewById(R.id.exo_media_route_button_placeholder);
        if (viewFindViewById == null) {
            throw new IllegalStateException("The media route button placeholder is missing.");
        }
        if (viewProvider == null) {
            viewFindViewById.setVisibility(8);
            return;
        }
        final ViewGroup viewGroup = (ViewGroup) viewFindViewById.getParent();
        if (viewGroup == null) {
            throw new IllegalStateException("The media route button placeholder has no parent view.");
        }
        ListenableFuture<View> view = viewProvider.getView(viewGroup);
        FutureCallback<View> futureCallback = new FutureCallback<View>() { // from class: androidx.media3.ui.PlayerControlView.1
            @Override // com.google.common.util.concurrent.FutureCallback
            public void onSuccess(View view2) {
                ViewGroup.LayoutParams layoutParams = viewFindViewById.getLayoutParams();
                if (layoutParams == null) {
                    throw new IllegalStateException("The media route button placeholder missing layout params.");
                }
                view2.setId(R.id.exo_media_route_button_placeholder);
                view2.setLayoutParams(layoutParams);
                int iIndexOfChild = viewGroup.indexOfChild(viewFindViewById);
                viewGroup.removeView(viewFindViewById);
                viewGroup.addView(view2, iIndexOfChild);
                view2.setVisibility(0);
                PlayerControlView.this.controlViewLayoutManager.setShowButton(view2, true);
            }

            @Override // com.google.common.util.concurrent.FutureCallback
            public void onFailure(Throwable th) {
                viewFindViewById.setVisibility(8);
            }
        };
        Handler handler = this.handler;
        Objects.requireNonNull(handler);
        Futures.addCallback(view, futureCallback, new ConcurrencyHelpers$$ExternalSyntheticLambda0(handler));
    }

    public boolean getShowVrButton() {
        return this.controlViewLayoutManager.getShowButton(this.vrButton);
    }

    public void setShowVrButton(boolean z) {
        this.controlViewLayoutManager.setShowButton(this.vrButton, z);
    }

    public void setVrButtonListener(View.OnClickListener onClickListener) {
        ImageView imageView = this.vrButton;
        if (imageView != null) {
            imageView.setOnClickListener(onClickListener);
            updateButton(onClickListener != null, this.vrButton);
        }
    }

    public void setAnimationEnabled(boolean z) {
        this.controlViewLayoutManager.setAnimationEnabled(z);
    }

    public boolean isAnimationEnabled() {
        return this.controlViewLayoutManager.isAnimationEnabled();
    }

    public void setTimeBarScrubbingEnabled(boolean z) {
        this.timeBarScrubbingEnabled = z;
    }

    public void setTimeBarMinUpdateInterval(int i) {
        this.timeBarMinUpdateIntervalMs = Util.constrainValue(i, 16, 1000);
    }

    @Deprecated
    public void setOnFullScreenModeChangedListener(OnFullScreenModeChangedListener onFullScreenModeChangedListener) {
        this.onFullScreenModeChangedListener = onFullScreenModeChangedListener;
        updateFullscreenButtonVisibility(this.fullscreenButton, onFullScreenModeChangedListener != null);
        updateFullscreenButtonVisibility(this.minimalFullscreenButton, onFullScreenModeChangedListener != null);
    }

    public void show() {
        this.controlViewLayoutManager.show();
    }

    public void hide() {
        this.controlViewLayoutManager.hide();
    }

    public void hideImmediately() {
        this.controlViewLayoutManager.hideImmediately();
    }

    public boolean isFullyVisible() {
        return this.controlViewLayoutManager.isFullyVisible();
    }

    public boolean isVisible() {
        return getVisibility() == 0;
    }

    void notifyOnVisibilityChange() {
        Iterator<VisibilityListener> it = this.visibilityListeners.iterator();
        while (it.hasNext()) {
            it.next().onVisibilityChange(getVisibility());
        }
    }

    void updateAll() {
        updatePlayPauseButton();
        updateNavigation();
        updateRepeatModeButton();
        updateShuffleButton();
        updateTrackLists();
        updatePlaybackSpeedList();
        updateTimeline();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePlayPauseButton() {
        int i;
        if (isVisible() && this.isAttachedToWindow && this.playPauseButton != null) {
            boolean zShouldShowPlayButton = Util.shouldShowPlayButton(this.player, this.showPlayButtonIfSuppressed);
            Drawable drawable = zShouldShowPlayButton ? this.playButtonDrawable : this.pauseButtonDrawable;
            if (zShouldShowPlayButton) {
                i = R.string.exo_controls_play_description;
            } else {
                i = R.string.exo_controls_pause_description;
            }
            this.playPauseButton.setImageDrawable(drawable);
            this.playPauseButton.setContentDescription(this.resources.getString(i));
            updateButton(Util.shouldEnablePlayPauseButton(this.player), this.playPauseButton);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateNavigation() {
        boolean zIsCommandAvailable;
        boolean zIsCommandAvailable2;
        boolean zIsCommandAvailable3;
        boolean zIsCommandAvailable4;
        boolean zIsCommandAvailable5;
        if (isVisible() && this.isAttachedToWindow) {
            Player player = this.player;
            if (player != null) {
                if (this.showMultiWindowTimeBar && canShowMultiWindowTimeBar(player, this.window)) {
                    zIsCommandAvailable = player.isCommandAvailable(10);
                } else {
                    zIsCommandAvailable = player.isCommandAvailable(5);
                }
                zIsCommandAvailable3 = player.isCommandAvailable(7);
                zIsCommandAvailable4 = player.isCommandAvailable(11);
                zIsCommandAvailable5 = player.isCommandAvailable(12);
                zIsCommandAvailable2 = player.isCommandAvailable(9);
            } else {
                zIsCommandAvailable = false;
                zIsCommandAvailable2 = false;
                zIsCommandAvailable3 = false;
                zIsCommandAvailable4 = false;
                zIsCommandAvailable5 = false;
            }
            if (zIsCommandAvailable4) {
                updateRewindButton();
            }
            if (zIsCommandAvailable5) {
                updateFastForwardButton();
            }
            updateButton(zIsCommandAvailable3, this.previousButton);
            updateButton(zIsCommandAvailable4, this.rewindButton);
            updateButton(zIsCommandAvailable5, this.fastForwardButton);
            updateButton(zIsCommandAvailable2, this.nextButton);
            TimeBar timeBar = this.timeBar;
            if (timeBar != null) {
                timeBar.setEnabled(zIsCommandAvailable);
            }
        }
    }

    private void updateRewindButton() {
        Player player = this.player;
        int seekBackIncrement = (int) ((player != null ? player.getSeekBackIncrement() : 5000L) / 1000);
        TextView textView = this.rewindButtonTextView;
        if (textView != null) {
            textView.setText(String.valueOf(seekBackIncrement));
        }
        View view = this.rewindButton;
        if (view != null) {
            view.setContentDescription(this.resources.getQuantityString(R.plurals.exo_controls_rewind_by_amount_description, seekBackIncrement, Integer.valueOf(seekBackIncrement)));
        }
    }

    private void updateFastForwardButton() {
        Player player = this.player;
        int seekForwardIncrement = (int) ((player != null ? player.getSeekForwardIncrement() : 15000L) / 1000);
        TextView textView = this.fastForwardButtonTextView;
        if (textView != null) {
            textView.setText(String.valueOf(seekForwardIncrement));
        }
        View view = this.fastForwardButton;
        if (view != null) {
            view.setContentDescription(this.resources.getQuantityString(R.plurals.exo_controls_fastforward_by_amount_description, seekForwardIncrement, Integer.valueOf(seekForwardIncrement)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateRepeatModeButton() {
        ImageView imageView;
        if (isVisible() && this.isAttachedToWindow && (imageView = this.repeatToggleButton) != null) {
            if (this.repeatToggleModes == 0) {
                updateButton(false, imageView);
                return;
            }
            Player player = this.player;
            if (player == null || !player.isCommandAvailable(15)) {
                updateButton(false, this.repeatToggleButton);
                this.repeatToggleButton.setImageDrawable(this.repeatOffButtonDrawable);
                this.repeatToggleButton.setContentDescription(this.repeatOffButtonContentDescription);
                return;
            }
            updateButton(true, this.repeatToggleButton);
            int repeatMode = player.getRepeatMode();
            if (repeatMode == 0) {
                this.repeatToggleButton.setImageDrawable(this.repeatOffButtonDrawable);
                this.repeatToggleButton.setContentDescription(this.repeatOffButtonContentDescription);
            } else if (repeatMode == 1) {
                this.repeatToggleButton.setImageDrawable(this.repeatOneButtonDrawable);
                this.repeatToggleButton.setContentDescription(this.repeatOneButtonContentDescription);
            } else {
                if (repeatMode != 2) {
                    return;
                }
                this.repeatToggleButton.setImageDrawable(this.repeatAllButtonDrawable);
                this.repeatToggleButton.setContentDescription(this.repeatAllButtonContentDescription);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateShuffleButton() {
        ImageView imageView;
        String str;
        if (isVisible() && this.isAttachedToWindow && (imageView = this.shuffleButton) != null) {
            Player player = this.player;
            if (!this.controlViewLayoutManager.getShowButton(imageView)) {
                updateButton(false, this.shuffleButton);
                return;
            }
            if (player == null || !player.isCommandAvailable(14)) {
                updateButton(false, this.shuffleButton);
                this.shuffleButton.setImageDrawable(this.shuffleOffButtonDrawable);
                this.shuffleButton.setContentDescription(this.shuffleOffContentDescription);
                return;
            }
            updateButton(true, this.shuffleButton);
            this.shuffleButton.setImageDrawable(player.getShuffleModeEnabled() ? this.shuffleOnButtonDrawable : this.shuffleOffButtonDrawable);
            ImageView imageView2 = this.shuffleButton;
            if (player.getShuffleModeEnabled()) {
                str = this.shuffleOnContentDescription;
            } else {
                str = this.shuffleOffContentDescription;
            }
            imageView2.setContentDescription(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTrackLists() {
        initTrackSelectionAdapter();
        updateButton(this.textTrackSelectionAdapter.getItemCount() > 0, this.subtitleButton);
        updateSettingsButton();
    }

    private void initTrackSelectionAdapter() {
        this.textTrackSelectionAdapter.clear();
        this.audioTrackSelectionAdapter.clear();
        Player player = this.player;
        if (player != null && player.isCommandAvailable(30) && this.player.isCommandAvailable(29)) {
            Tracks currentTracks = this.player.getCurrentTracks();
            this.audioTrackSelectionAdapter.init(gatherSupportedTrackInfosOfType(currentTracks, 1));
            boolean showButton = this.controlViewLayoutManager.getShowButton(this.subtitleButton);
            TextTrackSelectionAdapter textTrackSelectionAdapter = this.textTrackSelectionAdapter;
            if (showButton) {
                textTrackSelectionAdapter.init(gatherSupportedTrackInfosOfType(currentTracks, 3));
            } else {
                textTrackSelectionAdapter.init(ImmutableList.of());
            }
        }
    }

    private ImmutableList<TrackInformation> gatherSupportedTrackInfosOfType(Tracks tracks, int i) {
        ImmutableList.Builder builder = new ImmutableList.Builder();
        ImmutableList<Tracks.Group> groups = tracks.getGroups();
        for (int i2 = 0; i2 < groups.size(); i2++) {
            Tracks.Group group = groups.get(i2);
            if (group.getType() == i) {
                for (int i3 = 0; i3 < group.length; i3++) {
                    if (group.isTrackSupported(i3)) {
                        Format trackFormat = group.getTrackFormat(i3);
                        if ((trackFormat.selectionFlags & 2) == 0) {
                            builder.add(new TrackInformation(tracks, i2, i3, this.trackNameProvider.getTrackName(trackFormat)));
                        }
                    }
                }
            }
        }
        return builder.build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:44:0x00b4  */
    /* JADX WARN: Code duplicated, block: B:46:0x00b9  */
    /* JADX WARN: Code duplicated, block: B:48:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:49:0x00be  */
    /* JADX WARN: Code duplicated, block: B:61:0x0120  */
    /* JADX WARN: Code duplicated, block: B:79:0x00e4 A[SYNTHETIC] */
    public void updateTimeline() {
        Timeline currentTimeline;
        int i;
        long jMsToUs;
        long j;
        long positionInWindowUs;
        long[] jArr;
        int length;
        Player player = this.player;
        if (player == null) {
            return;
        }
        boolean z = true;
        this.multiWindowTimeBar = this.showMultiWindowTimeBar && canShowMultiWindowTimeBar(player, this.window);
        long j2 = 0;
        this.currentWindowOffset = 0L;
        if (player.isCommandAvailable(17)) {
            currentTimeline = player.getCurrentTimeline();
        } else {
            currentTimeline = Timeline.EMPTY;
        }
        boolean zIsEmpty = currentTimeline.isEmpty();
        long j3 = C.TIME_UNSET;
        if (!zIsEmpty) {
            int currentMediaItemIndex = player.getCurrentMediaItemIndex();
            boolean z2 = this.multiWindowTimeBar;
            int i2 = z2 ? 0 : currentMediaItemIndex;
            int windowCount = z2 ? currentTimeline.getWindowCount() - 1 : currentMediaItemIndex;
            i = 0;
            long j4 = 0;
            while (i2 <= windowCount) {
                if (i2 == currentMediaItemIndex) {
                    this.currentWindowOffset = Util.usToMs(j4);
                }
                currentTimeline.getWindow(i2, this.window);
                if (this.window.durationUs == j3) {
                    Preconditions.checkState(this.multiWindowTimeBar ^ z);
                    break;
                }
                for (int i3 = this.window.firstPeriodIndex; i3 <= this.window.lastPeriodIndex; i3++) {
                    currentTimeline.getPeriod(i3, this.period);
                    int removedAdGroupCount = this.period.getRemovedAdGroupCount();
                    int adGroupCount = this.period.getAdGroupCount();
                    while (removedAdGroupCount < adGroupCount) {
                        long j5 = j2;
                        long adGroupTimeUs = this.period.getAdGroupTimeUs(removedAdGroupCount);
                        if (adGroupTimeUs != Long.MIN_VALUE) {
                            j = j3;
                            positionInWindowUs = adGroupTimeUs + this.period.getPositionInWindowUs();
                            if (positionInWindowUs >= j5) {
                                jArr = this.adGroupTimesMs;
                                if (i == jArr.length) {
                                    if (jArr.length == 0) {
                                        length = 1;
                                    } else {
                                        length = jArr.length * 2;
                                    }
                                    this.adGroupTimesMs = Arrays.copyOf(jArr, length);
                                    this.playedAdGroups = Arrays.copyOf(this.playedAdGroups, length);
                                }
                                this.adGroupTimesMs[i] = Util.usToMs(positionInWindowUs + j4);
                                this.playedAdGroups[i] = this.period.hasPlayedAdGroup(removedAdGroupCount);
                                i++;
                            }
                        } else if (this.period.durationUs == j3) {
                            j = j3;
                        } else {
                            adGroupTimeUs = this.period.durationUs;
                            j = j3;
                            positionInWindowUs = adGroupTimeUs + this.period.getPositionInWindowUs();
                            if (positionInWindowUs >= j5) {
                                jArr = this.adGroupTimesMs;
                                if (i == jArr.length) {
                                    if (jArr.length == 0) {
                                        length = 1;
                                    } else {
                                        length = jArr.length * 2;
                                    }
                                    this.adGroupTimesMs = Arrays.copyOf(jArr, length);
                                    this.playedAdGroups = Arrays.copyOf(this.playedAdGroups, length);
                                }
                                this.adGroupTimesMs[i] = Util.usToMs(positionInWindowUs + j4);
                                this.playedAdGroups[i] = this.period.hasPlayedAdGroup(removedAdGroupCount);
                                i++;
                            }
                        }
                        removedAdGroupCount++;
                        j2 = j5;
                        j3 = j;
                    }
                }
                j4 += this.window.durationUs;
                i2++;
                j2 = j2;
                z = true;
            }
            jMsToUs = j4;
        } else if (player.isCommandAvailable(16)) {
            long contentDuration = player.getContentDuration();
            if (contentDuration != C.TIME_UNSET) {
                jMsToUs = Util.msToUs(contentDuration);
                i = 0;
            } else {
                i = 0;
                jMsToUs = 0;
            }
        } else {
            i = 0;
            jMsToUs = 0;
        }
        long jUsToMs = Util.usToMs(jMsToUs);
        TextView textView = this.durationView;
        if (textView != null) {
            textView.setText(Util.getStringForTime(this.formatBuilder, this.formatter, jUsToMs));
        }
        TimeBar timeBar = this.timeBar;
        if (timeBar != null) {
            timeBar.setDuration(jUsToMs);
            int length2 = this.extraAdGroupTimesMs.length;
            int i4 = i + length2;
            long[] jArr2 = this.adGroupTimesMs;
            if (i4 > jArr2.length) {
                this.adGroupTimesMs = Arrays.copyOf(jArr2, i4);
                this.playedAdGroups = Arrays.copyOf(this.playedAdGroups, i4);
            }
            System.arraycopy(this.extraAdGroupTimesMs, 0, this.adGroupTimesMs, i, length2);
            System.arraycopy(this.extraPlayedAdGroups, 0, this.playedAdGroups, i, length2);
            this.timeBar.setAdGroupTimesMs(this.adGroupTimesMs, this.playedAdGroups, i4);
        }
        updateProgress();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateProgress() {
        long contentPosition;
        long contentBufferedPosition;
        if (isVisible() && this.isAttachedToWindow) {
            Player player = this.player;
            if (player == null || !player.isCommandAvailable(16)) {
                contentPosition = 0;
                contentBufferedPosition = 0;
            } else {
                contentPosition = this.currentWindowOffset + player.getContentPosition();
                contentBufferedPosition = this.currentWindowOffset + player.getContentBufferedPosition();
            }
            TextView textView = this.positionView;
            if (textView != null && !this.scrubbing) {
                textView.setText(Util.getStringForTime(this.formatBuilder, this.formatter, contentPosition));
            }
            TimeBar timeBar = this.timeBar;
            if (timeBar != null) {
                timeBar.setPosition(contentPosition);
                this.timeBar.setBufferedPosition(isScrubbingModeEnabled(player) ? contentPosition : contentBufferedPosition);
            }
            ProgressUpdateListener progressUpdateListener = this.progressUpdateListener;
            if (progressUpdateListener != null) {
                progressUpdateListener.onProgressUpdate(contentPosition, contentBufferedPosition);
            }
            removeCallbacks(this.updateProgressAction);
            int playbackState = player == null ? 1 : player.getPlaybackState();
            if (player == null || !player.isPlaying()) {
                if (playbackState == 4 || playbackState == 1) {
                    return;
                }
                postDelayed(this.updateProgressAction, 1000L);
                return;
            }
            TimeBar timeBar2 = this.timeBar;
            long jMin = Math.min(timeBar2 != null ? timeBar2.getPreferredUpdateDelay() : 1000L, 1000 - (contentPosition % 1000));
            float f = player.getPlaybackParameters().speed;
            postDelayed(this.updateProgressAction, Util.constrainValue(f > 0.0f ? (long) (jMin / f) : 1000L, this.timeBarMinUpdateIntervalMs, 1000L));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePlaybackSpeedList() {
        Player player = this.player;
        if (player == null) {
            return;
        }
        this.playbackSpeedAdapter.updateSelectedIndex(player.getPlaybackParameters().speed);
        this.settingsAdapter.setSubTextAtPosition(0, this.playbackSpeedAdapter.getSelectedText());
        updateSettingsButton();
    }

    private void updateSettingsButton() {
        updateButton(this.settingsAdapter.hasSettingsToShow(), this.settingsButton);
    }

    private void updateSettingsWindowSize() {
        this.settingsView.measure(0, 0);
        this.settingsWindow.setWidth(Math.min(this.settingsView.getMeasuredWidth(), getWidth() - (this.settingsWindowMargin * 2)));
        this.settingsWindow.setHeight(Math.min(getHeight() - (this.settingsWindowMargin * 2), this.settingsView.getMeasuredHeight()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void displaySettingsWindow(RecyclerView.Adapter<?> adapter, View view) {
        this.settingsView.setAdapter(adapter);
        updateSettingsWindowSize();
        this.needToHideBars = false;
        this.settingsWindow.dismiss();
        this.needToHideBars = true;
        this.settingsWindow.showAsDropDown(view, (getWidth() - this.settingsWindow.getWidth()) - this.settingsWindowMargin, (-this.settingsWindow.getHeight()) - this.settingsWindowMargin);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPlaybackSpeed(float f) {
        Player player = this.player;
        if (player == null || !player.isCommandAvailable(13)) {
            return;
        }
        Player player2 = this.player;
        player2.setPlaybackParameters(player2.getPlaybackParameters().withSpeed(f));
    }

    void requestPlayPauseFocus() {
        ImageView imageView = this.playPauseButton;
        if (imageView != null) {
            imageView.requestFocus();
        }
    }

    private void updateButton(boolean z, View view) {
        if (view == null) {
            return;
        }
        view.setEnabled(z);
        view.setAlpha(z ? this.buttonAlphaEnabled : this.buttonAlphaDisabled);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void seekToTimeBarPosition(Player player, long j) {
        if (this.multiWindowTimeBar) {
            if (player.isCommandAvailable(17) && player.isCommandAvailable(10)) {
                Timeline currentTimeline = player.getCurrentTimeline();
                int windowCount = currentTimeline.getWindowCount();
                int i = 0;
                while (true) {
                    long durationMs = currentTimeline.getWindow(i, this.window).getDurationMs();
                    if (j < durationMs) {
                        break;
                    }
                    if (i == windowCount - 1) {
                        j = durationMs;
                        break;
                    } else {
                        j -= durationMs;
                        i++;
                    }
                }
                player.seekTo(i, j);
            }
        } else if (player.isCommandAvailable(5)) {
            player.seekTo(j);
        }
        updateProgress();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onFullscreenButtonClicked(View view) {
        updateIsFullscreen(!this.isFullscreen);
    }

    public void updateIsFullscreen(boolean z) {
        if (this.isFullscreen == z) {
            return;
        }
        this.isFullscreen = z;
        updateFullscreenButtonForState(this.fullscreenButton, z);
        updateFullscreenButtonForState(this.minimalFullscreenButton, z);
        OnFullScreenModeChangedListener onFullScreenModeChangedListener = this.onFullScreenModeChangedListener;
        if (onFullScreenModeChangedListener != null) {
            onFullScreenModeChangedListener.onFullScreenModeChanged(z);
        }
    }

    private void updateFullscreenButtonForState(ImageView imageView, boolean z) {
        if (imageView == null) {
            return;
        }
        if (z) {
            imageView.setImageDrawable(this.fullscreenExitDrawable);
            imageView.setContentDescription(this.fullscreenExitContentDescription);
        } else {
            imageView.setImageDrawable(this.fullscreenEnterDrawable);
            imageView.setContentDescription(this.fullscreenEnterContentDescription);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSettingViewClicked(int i) {
        if (i == 0) {
            displaySettingsWindow(this.playbackSpeedAdapter, (View) Preconditions.checkNotNull(this.settingsButton));
        } else if (i == 1) {
            displaySettingsWindow(this.audioTrackSelectionAdapter, (View) Preconditions.checkNotNull(this.settingsButton));
        } else {
            this.settingsWindow.dismiss();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.controlViewLayoutManager.onAttachedToWindow();
        this.isAttachedToWindow = true;
        if (isFullyVisible()) {
            this.controlViewLayoutManager.resetHideCallbacks();
        }
        updateAll();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.controlViewLayoutManager.onDetachedFromWindow();
        this.isAttachedToWindow = false;
        removeCallbacks(this.updateProgressAction);
        this.controlViewLayoutManager.removeHideCallbacks();
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return dispatchMediaKeyEvent(keyEvent) || super.dispatchKeyEvent(keyEvent);
    }

    public boolean dispatchMediaKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        Player player = this.player;
        if (player == null || !isHandledMediaKey(keyCode)) {
            return false;
        }
        if (keyEvent.getAction() != 0) {
            return true;
        }
        if (keyCode == 90) {
            if (player.getPlaybackState() == 4 || !player.isCommandAvailable(12)) {
                return true;
            }
            player.seekForward();
            return true;
        }
        if (keyCode == 89 && player.isCommandAvailable(11)) {
            player.seekBack();
            return true;
        }
        if (keyEvent.getRepeatCount() != 0) {
            return true;
        }
        if (keyCode == 79 || keyCode == 85) {
            Util.handlePlayPauseButtonAction(player, this.showPlayButtonIfSuppressed);
            return true;
        }
        if (keyCode == 87) {
            if (!player.isCommandAvailable(9)) {
                return true;
            }
            player.seekToNext();
            return true;
        }
        if (keyCode == 88) {
            if (!player.isCommandAvailable(7)) {
                return true;
            }
            player.seekToPrevious();
            return true;
        }
        if (keyCode == 126) {
            Util.handlePlayButtonAction(player);
            return true;
        }
        if (keyCode != 127) {
            return true;
        }
        Util.handlePauseButtonAction(player);
        return true;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.controlViewLayoutManager.onLayout(z, i, i2, i3, i4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        int i9 = i4 - i2;
        int i10 = i8 - i6;
        if (!(i3 - i == i7 - i5 && i9 == i10) && this.settingsWindow.isShowing()) {
            updateSettingsWindowSize();
            this.settingsWindow.update(view, (getWidth() - this.settingsWindow.getWidth()) - this.settingsWindowMargin, (-this.settingsWindow.getHeight()) - this.settingsWindowMargin, -1, -1);
        }
    }

    private static boolean canShowMultiWindowTimeBar(Player player, Timeline.Window window) {
        Timeline currentTimeline;
        int windowCount;
        if (!player.isCommandAvailable(17) || (windowCount = (currentTimeline = player.getCurrentTimeline()).getWindowCount()) <= 1 || windowCount > 100) {
            return false;
        }
        for (int i = 0; i < windowCount; i++) {
            if (currentTimeline.getWindow(i, window).durationUs == C.TIME_UNSET) {
                return false;
            }
        }
        return true;
    }

    private static void initializeFullscreenButton(View view, View.OnClickListener onClickListener) {
        if (view == null) {
            return;
        }
        view.setVisibility(8);
        view.setOnClickListener(onClickListener);
    }

    private static void updateFullscreenButtonVisibility(View view, boolean z) {
        if (view == null) {
            return;
        }
        if (z) {
            view.setVisibility(0);
        } else {
            view.setVisibility(8);
        }
    }

    private static int getRepeatToggleModes(TypedArray typedArray, int i) {
        return typedArray.getInt(R.styleable.PlayerControlView_repeat_toggle_modes, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @EnsuresNonNullIf(expression = {"#1"}, result = true)
    public boolean isScrubbingModeEnabled(Player player) {
        try {
            if (isExoPlayer(player) && ((Boolean) Preconditions.checkNotNull(((Method) Preconditions.checkNotNull(this.isScrubbingModeEnabledMethod)).invoke(player, null))).booleanValue()) {
                return true;
            }
            return isCompositionPlayer(player) && ((Boolean) Preconditions.checkNotNull(((Method) Preconditions.checkNotNull(this.compositionPlayerIsScrubbingModeEnabledMethod)).invoke(player, null))).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @EnsuresNonNullIf(expression = {"#1"}, result = true)
    public boolean isExoPlayer(Player player) {
        Class<?> cls;
        return (player == null || (cls = this.exoplayerClazz) == null || !cls.isAssignableFrom(player.getClass())) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @EnsuresNonNullIf(expression = {"#1"}, result = true)
    public boolean isCompositionPlayer(Player player) {
        Class<?> cls;
        return (player == null || (cls = this.compositionPlayerClazz) == null || !cls.isAssignableFrom(player.getClass())) ? false : true;
    }

    private final class ComponentListener implements Player.Listener, TimeBar.OnScrubListener, View.OnClickListener, PopupWindow.OnDismissListener {
        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onAudioAttributesChanged(AudioAttributes audioAttributes) {
            Player.Listener.CC.$default$onAudioAttributesChanged(this, audioAttributes);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onAudioSessionIdChanged(int i) {
            Player.Listener.CC.$default$onAudioSessionIdChanged(this, i);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onAvailableCommandsChanged(Player.Commands commands) {
            Player.Listener.CC.$default$onAvailableCommandsChanged(this, commands);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onCues(CueGroup cueGroup) {
            Player.Listener.CC.$default$onCues(this, cueGroup);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onCues(List list) {
            Player.Listener.CC.$default$onCues(this, list);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onDeviceInfoChanged(DeviceInfo deviceInfo) {
            Player.Listener.CC.$default$onDeviceInfoChanged(this, deviceInfo);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onDeviceVolumeChanged(int i, boolean z) {
            Player.Listener.CC.$default$onDeviceVolumeChanged(this, i, z);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onIsLoadingChanged(boolean z) {
            Player.Listener.CC.$default$onIsLoadingChanged(this, z);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onIsPlayingChanged(boolean z) {
            Player.Listener.CC.$default$onIsPlayingChanged(this, z);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onLoadingChanged(boolean z) {
            Player.Listener.CC.$default$onLoadingChanged(this, z);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onMaxSeekToPreviousPositionChanged(long j) {
            Player.Listener.CC.$default$onMaxSeekToPreviousPositionChanged(this, j);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onMediaItemTransition(MediaItem mediaItem, int i) {
            Player.Listener.CC.$default$onMediaItemTransition(this, mediaItem, i);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onMediaMetadataChanged(MediaMetadata mediaMetadata) {
            Player.Listener.CC.$default$onMediaMetadataChanged(this, mediaMetadata);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onMetadata(Metadata metadata) {
            Player.Listener.CC.$default$onMetadata(this, metadata);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onPlayWhenReadyChanged(boolean z, int i) {
            Player.Listener.CC.$default$onPlayWhenReadyChanged(this, z, i);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
            Player.Listener.CC.$default$onPlaybackParametersChanged(this, playbackParameters);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onPlaybackStateChanged(int i) {
            Player.Listener.CC.$default$onPlaybackStateChanged(this, i);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onPlaybackSuppressionReasonChanged(int i) {
            Player.Listener.CC.$default$onPlaybackSuppressionReasonChanged(this, i);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onPlayerError(PlaybackException playbackException) {
            Player.Listener.CC.$default$onPlayerError(this, playbackException);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onPlayerErrorChanged(PlaybackException playbackException) {
            Player.Listener.CC.$default$onPlayerErrorChanged(this, playbackException);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onPlayerStateChanged(boolean z, int i) {
            Player.Listener.CC.$default$onPlayerStateChanged(this, z, i);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onPlaylistMetadataChanged(MediaMetadata mediaMetadata) {
            Player.Listener.CC.$default$onPlaylistMetadataChanged(this, mediaMetadata);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onPositionDiscontinuity(int i) {
            Player.Listener.CC.$default$onPositionDiscontinuity(this, i);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onPositionDiscontinuity(Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i) {
            Player.Listener.CC.$default$onPositionDiscontinuity(this, positionInfo, positionInfo2, i);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onRenderedFirstFrame() {
            Player.Listener.CC.$default$onRenderedFirstFrame(this);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onRepeatModeChanged(int i) {
            Player.Listener.CC.$default$onRepeatModeChanged(this, i);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onSeekBackIncrementChanged(long j) {
            Player.Listener.CC.$default$onSeekBackIncrementChanged(this, j);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onSeekForwardIncrementChanged(long j) {
            Player.Listener.CC.$default$onSeekForwardIncrementChanged(this, j);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onShuffleModeEnabledChanged(boolean z) {
            Player.Listener.CC.$default$onShuffleModeEnabledChanged(this, z);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onSkipSilenceEnabledChanged(boolean z) {
            Player.Listener.CC.$default$onSkipSilenceEnabledChanged(this, z);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onSurfaceSizeChanged(int i, int i2) {
            Player.Listener.CC.$default$onSurfaceSizeChanged(this, i, i2);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onTimelineChanged(Timeline timeline, int i) {
            Player.Listener.CC.$default$onTimelineChanged(this, timeline, i);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onTrackSelectionParametersChanged(TrackSelectionParameters trackSelectionParameters) {
            Player.Listener.CC.$default$onTrackSelectionParametersChanged(this, trackSelectionParameters);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onTracksChanged(Tracks tracks) {
            Player.Listener.CC.$default$onTracksChanged(this, tracks);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onVideoSizeChanged(VideoSize videoSize) {
            Player.Listener.CC.$default$onVideoSizeChanged(this, videoSize);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onVolumeChanged(float f) {
            Player.Listener.CC.$default$onVolumeChanged(this, f);
        }

        private ComponentListener() {
        }

        @Override // androidx.media3.common.Player.Listener
        public void onEvents(Player player, Player.Events events) {
            if (events.containsAny(4, 5, 13)) {
                PlayerControlView.this.updatePlayPauseButton();
            }
            if (events.containsAny(4, 5, 7, 13)) {
                PlayerControlView.this.updateProgress();
            }
            if (events.containsAny(8, 13)) {
                PlayerControlView.this.updateRepeatModeButton();
            }
            if (events.containsAny(9, 13)) {
                PlayerControlView.this.updateShuffleButton();
            }
            if (events.containsAny(8, 9, 11, 0, 16, 17, 13)) {
                PlayerControlView.this.updateNavigation();
            }
            if (events.containsAny(11, 0, 13)) {
                PlayerControlView.this.updateTimeline();
            }
            if (events.containsAny(12, 13)) {
                PlayerControlView.this.updatePlaybackSpeedList();
            }
            if (events.containsAny(2, 13)) {
                PlayerControlView.this.updateTrackLists();
            }
        }

        @Override // androidx.media3.ui.TimeBar.OnScrubListener
        public void onScrubStart(TimeBar timeBar, long j) {
            PlayerControlView.this.scrubbing = true;
            if (PlayerControlView.this.positionView != null) {
                PlayerControlView.this.positionView.setText(Util.getStringForTime(PlayerControlView.this.formatBuilder, PlayerControlView.this.formatter, j));
            }
            PlayerControlView.this.controlViewLayoutManager.removeHideCallbacks();
            if (PlayerControlView.this.player != null && PlayerControlView.this.timeBarScrubbingEnabled) {
                PlayerControlView playerControlView = PlayerControlView.this;
                boolean zIsExoPlayer = playerControlView.isExoPlayer(playerControlView.player);
                PlayerControlView playerControlView2 = PlayerControlView.this;
                if (zIsExoPlayer) {
                    try {
                        try {
                            ((Method) Preconditions.checkNotNull(playerControlView2.setScrubbingModeEnabledMethod)).invoke(PlayerControlView.this.player, true);
                        } catch (InvocationTargetException e) {
                            e = e;
                            throw new RuntimeException(e);
                        }
                    } catch (IllegalAccessException | InvocationTargetException e2) {
                        e = e2;
                    }
                } else {
                    boolean zIsCompositionPlayer = playerControlView2.isCompositionPlayer(playerControlView2.player);
                    PlayerControlView playerControlView3 = PlayerControlView.this;
                    if (zIsCompositionPlayer) {
                        try {
                            try {
                                ((Method) Preconditions.checkNotNull(playerControlView3.compositionPlayerSetScrubbingModeEnabledMethod)).invoke(PlayerControlView.this.player, true);
                            } catch (InvocationTargetException e3) {
                                e = e3;
                                throw new RuntimeException(e);
                            }
                        } catch (IllegalAccessException | InvocationTargetException e4) {
                            e = e4;
                        }
                    } else {
                        Log.w(PlayerControlView.TAG, "Time bar scrubbing is enabled, but player is not an ExoPlayer or CompositionPlayer instance, so ignoring (because we can't enable scrubbing mode). player.class=" + ((Player) Preconditions.checkNotNull(playerControlView3.player)).getClass());
                    }
                }
            }
            PlayerControlView playerControlView4 = PlayerControlView.this;
            if (playerControlView4.isScrubbingModeEnabled(playerControlView4.player)) {
                PlayerControlView playerControlView5 = PlayerControlView.this;
                playerControlView5.seekToTimeBarPosition(playerControlView5.player, j);
            }
        }

        @Override // androidx.media3.ui.TimeBar.OnScrubListener
        public void onScrubMove(TimeBar timeBar, long j) {
            if (PlayerControlView.this.positionView != null) {
                PlayerControlView.this.positionView.setText(Util.getStringForTime(PlayerControlView.this.formatBuilder, PlayerControlView.this.formatter, j));
            }
            PlayerControlView playerControlView = PlayerControlView.this;
            if (playerControlView.isScrubbingModeEnabled(playerControlView.player)) {
                PlayerControlView playerControlView2 = PlayerControlView.this;
                playerControlView2.seekToTimeBarPosition(playerControlView2.player, j);
            }
        }

        @Override // androidx.media3.ui.TimeBar.OnScrubListener
        public void onScrubStop(TimeBar timeBar, long j, boolean z) {
            PlayerControlView.this.scrubbing = false;
            if (PlayerControlView.this.player != null) {
                if (!z) {
                    PlayerControlView playerControlView = PlayerControlView.this;
                    playerControlView.seekToTimeBarPosition(playerControlView.player, j);
                }
                PlayerControlView playerControlView2 = PlayerControlView.this;
                boolean zIsExoPlayer = playerControlView2.isExoPlayer(playerControlView2.player);
                PlayerControlView playerControlView3 = PlayerControlView.this;
                if (zIsExoPlayer) {
                    try {
                        try {
                            ((Method) Preconditions.checkNotNull(playerControlView3.setScrubbingModeEnabledMethod)).invoke(PlayerControlView.this.player, false);
                        } catch (InvocationTargetException e) {
                            e = e;
                            throw new RuntimeException(e);
                        }
                    } catch (IllegalAccessException | InvocationTargetException e2) {
                        e = e2;
                    }
                } else if (playerControlView3.isCompositionPlayer(playerControlView3.player)) {
                    try {
                        try {
                            ((Method) Preconditions.checkNotNull(PlayerControlView.this.compositionPlayerSetScrubbingModeEnabledMethod)).invoke(PlayerControlView.this.player, false);
                        } catch (InvocationTargetException e3) {
                            e = e3;
                            throw new RuntimeException(e);
                        }
                    } catch (IllegalAccessException | InvocationTargetException e4) {
                        e = e4;
                    }
                }
            }
            PlayerControlView.this.controlViewLayoutManager.resetHideCallbacks();
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            if (PlayerControlView.this.needToHideBars) {
                PlayerControlView.this.controlViewLayoutManager.resetHideCallbacks();
            }
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Player player = PlayerControlView.this.player;
            if (player == null) {
                return;
            }
            PlayerControlView.this.controlViewLayoutManager.resetHideCallbacks();
            if (PlayerControlView.this.nextButton != view) {
                if (PlayerControlView.this.previousButton != view) {
                    if (PlayerControlView.this.fastForwardButton != view) {
                        if (PlayerControlView.this.rewindButton != view) {
                            ImageView imageView = PlayerControlView.this.playPauseButton;
                            PlayerControlView playerControlView = PlayerControlView.this;
                            if (imageView == view) {
                                Util.handlePlayPauseButtonAction(player, playerControlView.showPlayButtonIfSuppressed);
                                return;
                            }
                            if (playerControlView.repeatToggleButton != view) {
                                if (PlayerControlView.this.shuffleButton != view) {
                                    View view2 = PlayerControlView.this.settingsButton;
                                    PlayerControlView playerControlView2 = PlayerControlView.this;
                                    if (view2 == view) {
                                        playerControlView2.controlViewLayoutManager.removeHideCallbacks();
                                        PlayerControlView playerControlView3 = PlayerControlView.this;
                                        playerControlView3.displaySettingsWindow(playerControlView3.settingsAdapter, PlayerControlView.this.settingsButton);
                                        return;
                                    }
                                    View view3 = playerControlView2.playbackSpeedButton;
                                    PlayerControlView playerControlView4 = PlayerControlView.this;
                                    if (view3 == view) {
                                        playerControlView4.controlViewLayoutManager.removeHideCallbacks();
                                        PlayerControlView playerControlView5 = PlayerControlView.this;
                                        playerControlView5.displaySettingsWindow(playerControlView5.playbackSpeedAdapter, PlayerControlView.this.playbackSpeedButton);
                                        return;
                                    }
                                    View view4 = playerControlView4.audioTrackButton;
                                    PlayerControlView playerControlView6 = PlayerControlView.this;
                                    if (view4 == view) {
                                        playerControlView6.controlViewLayoutManager.removeHideCallbacks();
                                        PlayerControlView playerControlView7 = PlayerControlView.this;
                                        playerControlView7.displaySettingsWindow(playerControlView7.audioTrackSelectionAdapter, PlayerControlView.this.audioTrackButton);
                                        return;
                                    } else {
                                        if (playerControlView6.subtitleButton == view) {
                                            PlayerControlView.this.controlViewLayoutManager.removeHideCallbacks();
                                            PlayerControlView playerControlView8 = PlayerControlView.this;
                                            playerControlView8.displaySettingsWindow(playerControlView8.textTrackSelectionAdapter, PlayerControlView.this.subtitleButton);
                                            return;
                                        }
                                        return;
                                    }
                                }
                                if (player.isCommandAvailable(14)) {
                                    player.setShuffleModeEnabled(!player.getShuffleModeEnabled());
                                    return;
                                }
                                return;
                            }
                            if (player.isCommandAvailable(15)) {
                                player.setRepeatMode(RepeatModeUtil.getNextRepeatMode(player.getRepeatMode(), PlayerControlView.this.repeatToggleModes));
                                return;
                            }
                            return;
                        }
                        if (player.isCommandAvailable(11)) {
                            player.seekBack();
                            return;
                        }
                        return;
                    }
                    if (player.getPlaybackState() == 4 || !player.isCommandAvailable(12)) {
                        return;
                    }
                    player.seekForward();
                    return;
                }
                if (player.isCommandAvailable(7)) {
                    player.seekToPrevious();
                    return;
                }
                return;
            }
            if (player.isCommandAvailable(9)) {
                player.seekToNext();
            }
        }
    }

    private class SettingsAdapter extends RecyclerView.Adapter<SettingViewHolder> {
        private final Drawable[] iconIds;
        private final String[] mainTexts;
        private final String[] subTexts;

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public long getItemId(int i) {
            return i;
        }

        public SettingsAdapter(String[] strArr, Drawable[] drawableArr) {
            this.mainTexts = strArr;
            this.subTexts = new String[strArr.length];
            this.iconIds = drawableArr;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public SettingViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return PlayerControlView.this.new SettingViewHolder(LayoutInflater.from(PlayerControlView.this.getContext()).inflate(R.layout.exo_styled_settings_list_item, viewGroup, false));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(SettingViewHolder settingViewHolder, int i) {
            if (shouldShowSetting(i)) {
                settingViewHolder.itemView.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            } else {
                settingViewHolder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
            }
            settingViewHolder.mainTextView.setText(this.mainTexts[i]);
            if (this.subTexts[i] == null) {
                settingViewHolder.subTextView.setVisibility(8);
            } else {
                settingViewHolder.subTextView.setText(this.subTexts[i]);
            }
            if (this.iconIds[i] == null) {
                settingViewHolder.iconView.setVisibility(8);
            } else {
                settingViewHolder.iconView.setImageDrawable(this.iconIds[i]);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.mainTexts.length;
        }

        public void setSubTextAtPosition(int i, String str) {
            this.subTexts[i] = str;
        }

        public boolean hasSettingsToShow() {
            return shouldShowSetting(1) || shouldShowSetting(0);
        }

        private boolean shouldShowSetting(int i) {
            if (PlayerControlView.this.player == null) {
                return false;
            }
            if (i == 0) {
                return PlayerControlView.this.player.isCommandAvailable(13);
            }
            if (i != 1) {
                return true;
            }
            return PlayerControlView.this.player.isCommandAvailable(30) && PlayerControlView.this.player.isCommandAvailable(29);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    final class SettingViewHolder extends RecyclerView.ViewHolder {
        private final ImageView iconView;
        private final TextView mainTextView;
        private final TextView subTextView;

        public SettingViewHolder(View view) {
            super(view);
            if (Build.VERSION.SDK_INT < 26) {
                view.setFocusable(true);
            }
            this.mainTextView = (TextView) view.findViewById(R.id.exo_main_text);
            this.subTextView = (TextView) view.findViewById(R.id.exo_sub_text);
            this.iconView = (ImageView) view.findViewById(R.id.exo_icon);
            view.setOnClickListener(new View.OnClickListener() { // from class: androidx.media3.ui.PlayerControlView$SettingViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f$0.m7878x7eeeb754(view2);
                }
            });
        }

        /* JADX INFO: renamed from: lambda$new$0$androidx-media3-ui-PlayerControlView$SettingViewHolder, reason: not valid java name */
        /* synthetic */ void m7878x7eeeb754(View view) {
            PlayerControlView.this.onSettingViewClicked(getBindingAdapterPosition());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    final class PlaybackSpeedAdapter extends RecyclerView.Adapter<SubSettingViewHolder> {
        private final String[] playbackSpeedTexts;
        private final float[] playbackSpeeds;
        private int selectedIndex;

        public PlaybackSpeedAdapter(String[] strArr, float[] fArr) {
            this.playbackSpeedTexts = strArr;
            this.playbackSpeeds = fArr;
        }

        public void updateSelectedIndex(float f) {
            int i = 0;
            float f2 = Float.MAX_VALUE;
            int i2 = 0;
            while (true) {
                float[] fArr = this.playbackSpeeds;
                if (i < fArr.length) {
                    float fAbs = Math.abs(f - fArr[i]);
                    if (fAbs < f2) {
                        i2 = i;
                        f2 = fAbs;
                    }
                    i++;
                } else {
                    this.selectedIndex = i2;
                    return;
                }
            }
        }

        public String getSelectedText() {
            return this.playbackSpeedTexts[this.selectedIndex];
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public SubSettingViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new SubSettingViewHolder(LayoutInflater.from(PlayerControlView.this.getContext()).inflate(R.layout.exo_styled_sub_settings_list_item, viewGroup, false));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(SubSettingViewHolder subSettingViewHolder, final int i) {
            if (i < this.playbackSpeedTexts.length) {
                subSettingViewHolder.textView.setText(this.playbackSpeedTexts[i]);
            }
            if (i == this.selectedIndex) {
                subSettingViewHolder.itemView.setSelected(true);
                subSettingViewHolder.checkView.setVisibility(0);
            } else {
                subSettingViewHolder.itemView.setSelected(false);
                subSettingViewHolder.checkView.setVisibility(4);
            }
            subSettingViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: androidx.media3.ui.PlayerControlView$PlaybackSpeedAdapter$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.m7877x9de2ddb7(i, view);
                }
            });
        }

        /* JADX INFO: renamed from: lambda$onBindViewHolder$0$androidx-media3-ui-PlayerControlView$PlaybackSpeedAdapter, reason: not valid java name */
        /* synthetic */ void m7877x9de2ddb7(int i, View view) {
            if (i != this.selectedIndex) {
                PlayerControlView.this.setPlaybackSpeed(this.playbackSpeeds[i]);
            }
            PlayerControlView.this.settingsWindow.dismiss();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.playbackSpeedTexts.length;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static final class TrackInformation {
        public final Tracks.Group trackGroup;
        public final int trackIndex;
        public final String trackName;

        public TrackInformation(Tracks tracks, int i, int i2, String str) {
            this.trackGroup = tracks.getGroups().get(i);
            this.trackIndex = i2;
            this.trackName = str;
        }

        public boolean isSelected() {
            return this.trackGroup.isTrackSelected(this.trackIndex);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    final class TextTrackSelectionAdapter extends TrackSelectionAdapter {
        @Override // androidx.media3.ui.PlayerControlView.TrackSelectionAdapter
        public void onTrackSelection(String str) {
        }

        private TextTrackSelectionAdapter() {
            super();
        }

        @Override // androidx.media3.ui.PlayerControlView.TrackSelectionAdapter
        public void init(List<TrackInformation> list) {
            boolean z = false;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).isSelected()) {
                    z = true;
                    break;
                }
            }
            if (PlayerControlView.this.subtitleButton != null) {
                ImageView imageView = PlayerControlView.this.subtitleButton;
                PlayerControlView playerControlView = PlayerControlView.this;
                imageView.setImageDrawable(z ? playerControlView.subtitleOnButtonDrawable : playerControlView.subtitleOffButtonDrawable);
                ImageView imageView2 = PlayerControlView.this.subtitleButton;
                PlayerControlView playerControlView2 = PlayerControlView.this;
                imageView2.setContentDescription(z ? playerControlView2.subtitleOnContentDescription : playerControlView2.subtitleOffContentDescription);
            }
            this.tracks = list;
        }

        @Override // androidx.media3.ui.PlayerControlView.TrackSelectionAdapter
        public void onBindViewHolderAtZeroPosition(SubSettingViewHolder subSettingViewHolder) {
            boolean z;
            subSettingViewHolder.textView.setText(R.string.exo_track_selection_none);
            int i = 0;
            while (true) {
                if (i >= this.tracks.size()) {
                    z = true;
                    break;
                } else {
                    if (this.tracks.get(i).isSelected()) {
                        z = false;
                        break;
                    }
                    i++;
                }
            }
            subSettingViewHolder.checkView.setVisibility(z ? 0 : 4);
            subSettingViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: androidx.media3.ui.PlayerControlView$TextTrackSelectionAdapter$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.m7879x7bd5d809(view);
                }
            });
        }

        /* JADX INFO: renamed from: lambda$onBindViewHolderAtZeroPosition$0$androidx-media3-ui-PlayerControlView$TextTrackSelectionAdapter, reason: not valid java name */
        /* synthetic */ void m7879x7bd5d809(View view) {
            if (PlayerControlView.this.player == null || !PlayerControlView.this.player.isCommandAvailable(29)) {
                return;
            }
            PlayerControlView.this.player.setTrackSelectionParameters(PlayerControlView.this.player.getTrackSelectionParameters().buildUpon().clearOverridesOfType(3).setIgnoredTextSelectionFlags(-3).setPreferredTextLanguage(null).setPreferredTextRoleFlags(0).build());
            PlayerControlView.this.settingsWindow.dismiss();
        }

        @Override // androidx.media3.ui.PlayerControlView.TrackSelectionAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(SubSettingViewHolder subSettingViewHolder, int i) {
            super.onBindViewHolder(subSettingViewHolder, i);
            if (i > 0) {
                subSettingViewHolder.checkView.setVisibility(this.tracks.get(i + (-1)).isSelected() ? 0 : 4);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    final class AudioTrackSelectionAdapter extends TrackSelectionAdapter {
        private AudioTrackSelectionAdapter() {
            super();
        }

        @Override // androidx.media3.ui.PlayerControlView.TrackSelectionAdapter
        public void onBindViewHolderAtZeroPosition(SubSettingViewHolder subSettingViewHolder) {
            subSettingViewHolder.textView.setText(R.string.exo_track_selection_auto);
            subSettingViewHolder.checkView.setVisibility(hasSelectionOverride(((Player) Preconditions.checkNotNull(PlayerControlView.this.player)).getTrackSelectionParameters()) ? 4 : 0);
            subSettingViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: androidx.media3.ui.PlayerControlView$AudioTrackSelectionAdapter$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.m7876xa84b12b0(view);
                }
            });
        }

        /* JADX INFO: renamed from: lambda$onBindViewHolderAtZeroPosition$0$androidx-media3-ui-PlayerControlView$AudioTrackSelectionAdapter, reason: not valid java name */
        /* synthetic */ void m7876xa84b12b0(View view) {
            if (PlayerControlView.this.player == null || !PlayerControlView.this.player.isCommandAvailable(29)) {
                return;
            }
            ((Player) Util.castNonNull(PlayerControlView.this.player)).setTrackSelectionParameters(PlayerControlView.this.player.getTrackSelectionParameters().buildUpon().clearOverridesOfType(1).setTrackTypeDisabled(1, false).build());
            PlayerControlView.this.settingsAdapter.setSubTextAtPosition(1, PlayerControlView.this.getResources().getString(R.string.exo_track_selection_auto));
            PlayerControlView.this.settingsWindow.dismiss();
        }

        private boolean hasSelectionOverride(TrackSelectionParameters trackSelectionParameters) {
            for (int i = 0; i < this.tracks.size(); i++) {
                if (trackSelectionParameters.overrides.containsKey(this.tracks.get(i).trackGroup.getMediaTrackGroup())) {
                    return true;
                }
            }
            return false;
        }

        @Override // androidx.media3.ui.PlayerControlView.TrackSelectionAdapter
        public void onTrackSelection(String str) {
            PlayerControlView.this.settingsAdapter.setSubTextAtPosition(1, str);
        }

        @Override // androidx.media3.ui.PlayerControlView.TrackSelectionAdapter
        public void init(List<TrackInformation> list) {
            this.tracks = list;
            TrackSelectionParameters trackSelectionParameters = ((Player) Preconditions.checkNotNull(PlayerControlView.this.player)).getTrackSelectionParameters();
            if (list.isEmpty()) {
                PlayerControlView.this.settingsAdapter.setSubTextAtPosition(1, PlayerControlView.this.getResources().getString(R.string.exo_track_selection_none));
                return;
            }
            if (!hasSelectionOverride(trackSelectionParameters)) {
                PlayerControlView.this.settingsAdapter.setSubTextAtPosition(1, PlayerControlView.this.getResources().getString(R.string.exo_track_selection_auto));
                return;
            }
            for (int i = 0; i < list.size(); i++) {
                TrackInformation trackInformation = list.get(i);
                if (trackInformation.isSelected()) {
                    PlayerControlView.this.settingsAdapter.setSubTextAtPosition(1, trackInformation.trackName);
                    return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    abstract class TrackSelectionAdapter extends RecyclerView.Adapter<SubSettingViewHolder> {
        protected List<TrackInformation> tracks = new ArrayList();

        public abstract void init(List<TrackInformation> list);

        protected abstract void onBindViewHolderAtZeroPosition(SubSettingViewHolder subSettingViewHolder);

        protected abstract void onTrackSelection(String str);

        protected TrackSelectionAdapter() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public SubSettingViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new SubSettingViewHolder(LayoutInflater.from(PlayerControlView.this.getContext()).inflate(R.layout.exo_styled_sub_settings_list_item, viewGroup, false));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(SubSettingViewHolder subSettingViewHolder, int i) {
            final Player player = PlayerControlView.this.player;
            if (player == null) {
                return;
            }
            if (i == 0) {
                onBindViewHolderAtZeroPosition(subSettingViewHolder);
                return;
            }
            final TrackInformation trackInformation = this.tracks.get(i - 1);
            final TrackGroup mediaTrackGroup = trackInformation.trackGroup.getMediaTrackGroup();
            boolean z = player.getTrackSelectionParameters().overrides.get(mediaTrackGroup) != null && trackInformation.isSelected();
            subSettingViewHolder.textView.setText(trackInformation.trackName);
            subSettingViewHolder.checkView.setVisibility(z ? 0 : 4);
            subSettingViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: androidx.media3.ui.PlayerControlView$TrackSelectionAdapter$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.m7880x45c3fb1a(player, mediaTrackGroup, trackInformation, view);
                }
            });
        }

        /* JADX INFO: renamed from: lambda$onBindViewHolder$0$androidx-media3-ui-PlayerControlView$TrackSelectionAdapter, reason: not valid java name */
        /* synthetic */ void m7880x45c3fb1a(Player player, TrackGroup trackGroup, TrackInformation trackInformation, View view) {
            if (player.isCommandAvailable(29)) {
                player.setTrackSelectionParameters(player.getTrackSelectionParameters().buildUpon().setOverrideForType(new TrackSelectionOverride(trackGroup, ImmutableList.of(Integer.valueOf(trackInformation.trackIndex)))).setTrackTypeDisabled(trackInformation.trackGroup.getType(), false).build());
                onTrackSelection(trackInformation.trackName);
                PlayerControlView.this.settingsWindow.dismiss();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            if (this.tracks.isEmpty()) {
                return 0;
            }
            return this.tracks.size() + 1;
        }

        protected void clear() {
            this.tracks = Collections.EMPTY_LIST;
        }
    }

    private static class SubSettingViewHolder extends RecyclerView.ViewHolder {
        public final View checkView;
        public final TextView textView;

        public SubSettingViewHolder(View view) {
            super(view);
            if (Build.VERSION.SDK_INT < 26) {
                view.setFocusable(true);
            }
            this.textView = (TextView) view.findViewById(R.id.exo_text);
            this.checkView = view.findViewById(R.id.exo_check);
        }
    }
}
