package com.google.android.gms.cast.framework.media.widget;

import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.gms.cast.AdBreakClipInfo;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.SessionManager;
import com.google.android.gms.cast.framework.SessionManagerListener;
import com.google.android.gms.cast.framework.media.ImageHints;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.internal.zzv;
import com.google.android.gms.cast.framework.media.uicontroller.UIMediaController;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.internal.cast.zzda;
import com.google.android.gms.internal.cast.zzdd;
import com.google.android.gms.internal.cast.zzde;
import com.google.android.gms.internal.cast.zzdf;
import com.google.android.gms.internal.cast.zzdg;
import com.google.android.gms.internal.cast.zzpb;
import java.util.Timer;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public abstract class ExpandedControllerActivity extends AppCompatActivity implements ControlButtonsContainer {
    private ImageView zzA;
    private int[] zzB;
    private final ImageView[] zzC = new ImageView[4];
    private View zzD;
    private View zzE;
    private ImageView zzF;
    private TextView zzG;
    private TextView zzH;
    private TextView zzI;
    private TextView zzJ;
    private UIMediaController zzK;
    private SessionManager zzL;
    private Cast.Listener zzM;
    private boolean zzN;
    private Timer zzO;
    private String zzP;
    final SessionManagerListener zza;
    final RemoteMediaClient.Listener zzb;
    com.google.android.gms.cast.framework.media.internal.zzb zzc;
    boolean zzd;
    private int zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private int zzk;
    private int zzl;
    private int zzm;
    private int zzn;
    private int zzo;
    private int zzp;
    private int zzq;
    private int zzr;
    private int zzs;
    private int zzt;
    private int zzu;
    private int zzv;
    private TextView zzw;
    private SeekBar zzx;
    private CastSeekBar zzy;
    private ImageView zzz;

    public ExpandedControllerActivity() {
        zzp zzpVar = null;
        this.zza = new zzo(this, zzpVar);
        this.zzb = new zzn(this, zzpVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final RemoteMediaClient zzj() {
        CastSession currentCastSession = this.zzL.getCurrentCastSession();
        if (currentCastSession == null || !currentCastSession.isConnected()) {
            return null;
        }
        return currentCastSession.getRemoteMediaClient();
    }

    private final void zzk(String str) {
        this.zzc.zzd(Uri.parse(str));
        this.zzE.setVisibility(8);
    }

    private final void zzl(View view, int i, int i2, UIMediaController uIMediaController) {
        ImageView imageView = (ImageView) view.findViewById(i);
        if (i2 == R.id.cast_button_type_empty) {
            imageView.setVisibility(4);
            return;
        }
        if (i2 == R.id.cast_button_type_play_pause_toggle) {
            imageView.setBackgroundResource(this.zze);
            Drawable drawableZzb = zzq.zzb(this, this.zzs, this.zzg);
            Drawable drawableZzb2 = zzq.zzb(this, this.zzs, this.zzf);
            Drawable drawableZzb3 = zzq.zzb(this, this.zzs, this.zzh);
            imageView.setImageDrawable(drawableZzb2);
            uIMediaController.bindImageViewToPlayPauseToggle(imageView, drawableZzb2, drawableZzb, drawableZzb3, null, false);
            return;
        }
        if (i2 == R.id.cast_button_type_skip_previous) {
            imageView.setBackgroundResource(this.zze);
            imageView.setImageDrawable(zzq.zzb(this, this.zzs, this.zzi));
            imageView.setContentDescription(getResources().getString(R.string.cast_skip_prev));
            uIMediaController.bindViewToSkipPrev(imageView, 0);
            return;
        }
        if (i2 == R.id.cast_button_type_skip_next) {
            imageView.setBackgroundResource(this.zze);
            imageView.setImageDrawable(zzq.zzb(this, this.zzs, this.zzj));
            imageView.setContentDescription(getResources().getString(R.string.cast_skip_next));
            uIMediaController.bindViewToSkipNext(imageView, 0);
            return;
        }
        if (i2 == R.id.cast_button_type_rewind_30_seconds) {
            imageView.setBackgroundResource(this.zze);
            imageView.setImageDrawable(zzq.zzb(this, this.zzs, this.zzk));
            imageView.setContentDescription(getResources().getString(R.string.cast_rewind_30));
            uIMediaController.bindViewToRewind(imageView, 30000L);
            return;
        }
        if (i2 == R.id.cast_button_type_forward_30_seconds) {
            imageView.setBackgroundResource(this.zze);
            imageView.setImageDrawable(zzq.zzb(this, this.zzs, this.zzl));
            imageView.setContentDescription(getResources().getString(R.string.cast_forward_30));
            uIMediaController.bindViewToForward(imageView, 30000L);
            return;
        }
        if (i2 == R.id.cast_button_type_mute_toggle) {
            imageView.setBackgroundResource(this.zze);
            imageView.setImageDrawable(zzq.zzb(this, this.zzs, this.zzm));
            uIMediaController.bindImageViewToMuteToggle(imageView);
        } else if (i2 == R.id.cast_button_type_closed_caption) {
            imageView.setBackgroundResource(this.zze);
            imageView.setImageDrawable(zzq.zzb(this, this.zzs, this.zzn));
            uIMediaController.bindViewToClosedCaption(imageView);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzm(RemoteMediaClient remoteMediaClient) {
        MediaStatus mediaStatus;
        if (this.zzd || (mediaStatus = remoteMediaClient.getMediaStatus()) == null || remoteMediaClient.isBuffering()) {
            return;
        }
        this.zzI.setVisibility(8);
        this.zzJ.setVisibility(8);
        AdBreakClipInfo currentAdBreakClip = mediaStatus.getCurrentAdBreakClip();
        if (currentAdBreakClip == null || currentAdBreakClip.getWhenSkippableInMs() == -1) {
            return;
        }
        if (!this.zzN) {
            zzk zzkVar = new zzk(this, remoteMediaClient);
            Timer timer = new Timer();
            this.zzO = timer;
            timer.scheduleAtFixedRate(zzkVar, 0L, 500L);
            this.zzN = true;
        }
        float whenSkippableInMs = currentAdBreakClip.getWhenSkippableInMs() - remoteMediaClient.getApproximateAdBreakClipPositionMs();
        if (whenSkippableInMs > 0.0f) {
            this.zzJ.setVisibility(0);
            this.zzJ.setText(getResources().getString(R.string.cast_expanded_controller_skip_ad_text, Integer.valueOf((int) Math.ceil(whenSkippableInMs / 1000.0f))));
            this.zzI.setClickable(false);
        } else {
            if (this.zzN) {
                this.zzO.cancel();
                this.zzN = false;
            }
            this.zzI.setVisibility(0);
            this.zzI.setClickable(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzn() {
        CastDevice castDevice;
        CastSession currentCastSession = this.zzL.getCurrentCastSession();
        if (currentCastSession != null && (castDevice = currentCastSession.getCastDevice()) != null) {
            String friendlyName = castDevice.getFriendlyName();
            if (!TextUtils.isEmpty(friendlyName)) {
                this.zzw.setText(getResources().getString(R.string.cast_casting_to_device, friendlyName));
                return;
            }
        }
        this.zzw.setText("");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzo() {
        MediaInfo mediaInfo;
        MediaMetadata metadata;
        ActionBar supportActionBar;
        RemoteMediaClient remoteMediaClientZzj = zzj();
        if (remoteMediaClientZzj == null || !remoteMediaClientZzj.hasMediaSession() || (mediaInfo = remoteMediaClientZzj.getMediaInfo()) == null || (metadata = mediaInfo.getMetadata()) == null || (supportActionBar = getSupportActionBar()) == null) {
            return;
        }
        supportActionBar.setTitle(metadata.getString(MediaMetadata.KEY_TITLE));
        String strZze = zzv.zze(metadata);
        if (strZze != null) {
            supportActionBar.setSubtitle(strZze);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzp() {
        MediaStatus mediaStatus;
        String string;
        Drawable drawable;
        Bitmap bitmap;
        Bitmap bitmapZza;
        RemoteMediaClient remoteMediaClientZzj = zzj();
        if (remoteMediaClientZzj == null || (mediaStatus = remoteMediaClientZzj.getMediaStatus()) == null) {
            return;
        }
        String imageUrl = null;
        if (!mediaStatus.isPlayingAd()) {
            this.zzJ.setVisibility(8);
            this.zzI.setVisibility(8);
            this.zzD.setVisibility(8);
            this.zzA.setVisibility(8);
            this.zzA.setImageBitmap(null);
            return;
        }
        if (this.zzA.getVisibility() == 8 && (drawable = this.zzz.getDrawable()) != null && (drawable instanceof BitmapDrawable) && (bitmap = ((BitmapDrawable) drawable).getBitmap()) != null && (bitmapZza = zzq.zza(this, bitmap, 0.25f, 7.5f)) != null) {
            this.zzA.setImageBitmap(bitmapZza);
            this.zzA.setVisibility(0);
        }
        AdBreakClipInfo currentAdBreakClip = mediaStatus.getCurrentAdBreakClip();
        if (currentAdBreakClip != null) {
            String title = currentAdBreakClip.getTitle();
            imageUrl = currentAdBreakClip.getImageUrl();
            string = title;
        } else {
            string = null;
        }
        if (!TextUtils.isEmpty(imageUrl)) {
            zzk(imageUrl);
        } else if (TextUtils.isEmpty(this.zzP)) {
            this.zzG.setVisibility(0);
            this.zzE.setVisibility(0);
            this.zzF.setVisibility(8);
        } else {
            zzk(this.zzP);
        }
        TextView textView = this.zzH;
        if (TextUtils.isEmpty(string)) {
            string = getResources().getString(R.string.cast_ad_label);
        }
        textView.setText(string);
        boolean zIsAtLeastM = PlatformVersion.isAtLeastM();
        TextView textView2 = this.zzH;
        if (zIsAtLeastM) {
            textView2.setTextAppearance(this.zzt);
        } else {
            textView2.setTextAppearance(this, this.zzt);
        }
        this.zzD.setVisibility(0);
        zzm(remoteMediaClientZzj);
    }

    @Override // com.google.android.gms.cast.framework.media.widget.ControlButtonsContainer
    public final ImageView getButtonImageViewAt(int i) throws IndexOutOfBoundsException {
        return this.zzC[i];
    }

    @Override // com.google.android.gms.cast.framework.media.widget.ControlButtonsContainer
    public final int getButtonSlotCount() {
        return 4;
    }

    @Override // com.google.android.gms.cast.framework.media.widget.ControlButtonsContainer
    public final int getButtonTypeAt(int i) throws IndexOutOfBoundsException {
        return this.zzB[i];
    }

    @Deprecated
    public SeekBar getSeekBar() {
        return this.zzx;
    }

    public TextView getStatusTextView() {
        return this.zzw;
    }

    @Override // com.google.android.gms.cast.framework.media.widget.ControlButtonsContainer
    public UIMediaController getUIMediaController() {
        return this.zzK;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        SessionManager sessionManager = CastContext.getSharedInstance(this).getSessionManager();
        this.zzL = sessionManager;
        if (sessionManager.getCurrentCastSession() == null) {
            finish();
        }
        UIMediaController uIMediaController = new UIMediaController(this);
        this.zzK = uIMediaController;
        uIMediaController.setPostRemoteMediaClientListener(this.zzb);
        setContentView(R.layout.cast_expanded_controller_activity);
        TypedArray typedArrayObtainStyledAttributes = obtainStyledAttributes(new int[]{androidx.appcompat.R.attr.selectableItemBackgroundBorderless});
        this.zze = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        zzp zzpVar = null;
        TypedArray typedArrayObtainStyledAttributes2 = obtainStyledAttributes(null, R.styleable.CastExpandedController, R.attr.castExpandedControllerStyle, R.style.CastExpandedController);
        this.zzs = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castButtonColor, 0);
        this.zzf = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castPlayButtonDrawable, 0);
        this.zzg = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castPauseButtonDrawable, 0);
        this.zzh = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castStopButtonDrawable, 0);
        this.zzi = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castSkipPreviousButtonDrawable, 0);
        this.zzj = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castSkipNextButtonDrawable, 0);
        this.zzk = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castRewind30ButtonDrawable, 0);
        this.zzl = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castForward30ButtonDrawable, 0);
        this.zzm = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castMuteToggleButtonDrawable, 0);
        this.zzn = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castClosedCaptionsButtonDrawable, 0);
        int resourceId = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castControlButtons, 0);
        if (resourceId != 0) {
            TypedArray typedArrayObtainTypedArray = getResources().obtainTypedArray(resourceId);
            Preconditions.checkArgument(typedArrayObtainTypedArray.length() == 4);
            this.zzB = new int[typedArrayObtainTypedArray.length()];
            for (int i = 0; i < typedArrayObtainTypedArray.length(); i++) {
                this.zzB[i] = typedArrayObtainTypedArray.getResourceId(i, 0);
            }
            typedArrayObtainTypedArray.recycle();
        } else {
            this.zzB = new int[]{R.id.cast_button_type_empty, R.id.cast_button_type_empty, R.id.cast_button_type_empty, R.id.cast_button_type_empty};
        }
        this.zzr = typedArrayObtainStyledAttributes2.getColor(R.styleable.CastExpandedController_castExpandedControllerLoadingIndicatorColor, 0);
        this.zzo = getResources().getColor(typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castAdLabelColor, 0));
        this.zzp = getResources().getColor(typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castAdInProgressTextColor, 0));
        this.zzq = getResources().getColor(typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castAdLabelTextColor, 0));
        this.zzt = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castAdLabelTextAppearance, 0);
        this.zzu = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castAdInProgressLabelTextAppearance, 0);
        this.zzv = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castAdInProgressText, 0);
        int resourceId2 = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castDefaultAdPosterUrl, 0);
        if (resourceId2 != 0) {
            this.zzP = getApplicationContext().getResources().getString(resourceId2);
        }
        typedArrayObtainStyledAttributes2.recycle();
        View viewFindViewById = findViewById(R.id.expanded_controller_layout);
        UIMediaController uIMediaController2 = this.zzK;
        this.zzz = (ImageView) viewFindViewById.findViewById(R.id.background_image_view);
        this.zzA = (ImageView) viewFindViewById.findViewById(R.id.blurred_background_image_view);
        View viewFindViewById2 = viewFindViewById.findViewById(R.id.background_place_holder_image_view);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        uIMediaController2.zzb(this.zzz, new ImageHints(4, displayMetrics.widthPixels, displayMetrics.heightPixels), viewFindViewById2, new zzm(this, zzpVar));
        this.zzw = (TextView) viewFindViewById.findViewById(R.id.status_text);
        ProgressBar progressBar = (ProgressBar) viewFindViewById.findViewById(R.id.loading_indicator);
        Drawable indeterminateDrawable = progressBar.getIndeterminateDrawable();
        int i2 = this.zzr;
        if (i2 != 0) {
            indeterminateDrawable.setColorFilter(i2, PorterDuff.Mode.SRC_IN);
        }
        uIMediaController2.bindViewToLoadingIndicator(progressBar);
        TextView textView = (TextView) viewFindViewById.findViewById(R.id.start_text);
        TextView textView2 = (TextView) viewFindViewById.findViewById(R.id.end_text);
        this.zzx = (SeekBar) viewFindViewById.findViewById(R.id.seek_bar);
        CastSeekBar castSeekBar = (CastSeekBar) viewFindViewById.findViewById(R.id.cast_seek_bar);
        this.zzy = castSeekBar;
        uIMediaController2.bindSeekBar(castSeekBar, 1000L);
        uIMediaController2.bindViewToUIController(textView, new zzdf(textView, uIMediaController2.zza()));
        uIMediaController2.bindViewToUIController(textView2, new zzdd(textView2, uIMediaController2.zza()));
        View viewFindViewById3 = viewFindViewById.findViewById(R.id.live_indicators);
        uIMediaController2.bindViewToUIController(viewFindViewById3, new zzde(viewFindViewById3, uIMediaController2.zza()));
        RelativeLayout relativeLayout = (RelativeLayout) viewFindViewById.findViewById(R.id.tooltip_container);
        zzda zzdgVar = new zzdg(relativeLayout, this.zzy, uIMediaController2.zza());
        uIMediaController2.bindViewToUIController(relativeLayout, zzdgVar);
        uIMediaController2.zzf(zzdgVar);
        ImageView[] imageViewArr = this.zzC;
        imageViewArr[0] = (ImageView) viewFindViewById.findViewById(R.id.button_0);
        imageViewArr[1] = (ImageView) viewFindViewById.findViewById(R.id.button_1);
        imageViewArr[2] = (ImageView) viewFindViewById.findViewById(R.id.button_2);
        imageViewArr[3] = (ImageView) viewFindViewById.findViewById(R.id.button_3);
        zzl(viewFindViewById, R.id.button_0, this.zzB[0], uIMediaController2);
        zzl(viewFindViewById, R.id.button_1, this.zzB[1], uIMediaController2);
        zzl(viewFindViewById, R.id.button_play_pause_toggle, R.id.cast_button_type_play_pause_toggle, uIMediaController2);
        zzl(viewFindViewById, R.id.button_2, this.zzB[2], uIMediaController2);
        zzl(viewFindViewById, R.id.button_3, this.zzB[3], uIMediaController2);
        View viewFindViewById4 = findViewById(R.id.ad_container);
        this.zzD = viewFindViewById4;
        this.zzF = (ImageView) viewFindViewById4.findViewById(R.id.ad_image_view);
        this.zzE = this.zzD.findViewById(R.id.ad_background_image_view);
        TextView textView3 = (TextView) this.zzD.findViewById(R.id.ad_label);
        this.zzH = textView3;
        textView3.setTextColor(this.zzq);
        this.zzH.setBackgroundColor(this.zzo);
        this.zzG = (TextView) this.zzD.findViewById(R.id.ad_in_progress_label);
        this.zzJ = (TextView) findViewById(R.id.ad_skip_text);
        TextView textView4 = (TextView) findViewById(R.id.ad_skip_button);
        this.zzI = textView4;
        textView4.setOnClickListener(new zzi(this));
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setHomeAsUpIndicator(R.drawable.quantum_ic_keyboard_arrow_down_white_36);
        }
        zzn();
        zzo();
        if (this.zzG != null && this.zzv != 0) {
            boolean zIsAtLeastM = PlatformVersion.isAtLeastM();
            TextView textView5 = this.zzG;
            if (zIsAtLeastM) {
                textView5.setTextAppearance(this.zzu);
            } else {
                textView5.setTextAppearance(getApplicationContext(), this.zzu);
            }
            this.zzG.setTextColor(this.zzp);
            this.zzG.setText(this.zzv);
        }
        com.google.android.gms.cast.framework.media.internal.zzb zzbVar = new com.google.android.gms.cast.framework.media.internal.zzb(getApplicationContext(), new ImageHints(-1, this.zzF.getWidth(), this.zzF.getHeight()));
        this.zzc = zzbVar;
        zzbVar.zzc(new zzh(this));
        com.google.android.gms.internal.cast.zzp.zzd(zzpb.CAF_EXPANDED_CONTROLLER);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        this.zzc.zza();
        UIMediaController uIMediaController = this.zzK;
        if (uIMediaController != null) {
            uIMediaController.setPostRemoteMediaClientListener(null);
            this.zzK.dispose();
        }
        super.onDestroy();
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return true;
        }
        finish();
        return true;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        SessionManager sessionManager = this.zzL;
        if (sessionManager == null) {
            return;
        }
        CastSession currentCastSession = sessionManager.getCurrentCastSession();
        Cast.Listener listener = this.zzM;
        if (listener != null && currentCastSession != null) {
            currentCastSession.removeCastListener(listener);
            this.zzM = null;
        }
        this.zzL.removeSessionManagerListener(this.zza, CastSession.class);
        super.onPause();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        SessionManager sessionManager = this.zzL;
        if (sessionManager == null) {
            return;
        }
        sessionManager.addSessionManagerListener(this.zza, CastSession.class);
        CastSession currentCastSession = this.zzL.getCurrentCastSession();
        if (currentCastSession == null || !(currentCastSession.isConnected() || currentCastSession.isConnecting())) {
            finish();
        } else {
            zzl zzlVar = new zzl(this);
            this.zzM = zzlVar;
            currentCastSession.addCastListener(zzlVar);
        }
        RemoteMediaClient remoteMediaClientZzj = zzj();
        boolean z = true;
        if (remoteMediaClientZzj != null && remoteMediaClientZzj.hasMediaSession()) {
            z = false;
        }
        this.zzd = z;
        zzn();
        zzp();
        super.onResume();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            int systemUiVisibility = getWindow().getDecorView().getSystemUiVisibility();
            int i = systemUiVisibility ^ 2;
            if (PlatformVersion.isAtLeastJellyBean()) {
                i = systemUiVisibility ^ 6;
            }
            if (PlatformVersion.isAtLeastKitKat()) {
                i ^= 4096;
            }
            getWindow().getDecorView().setSystemUiVisibility(i);
            setImmersive(true);
        }
    }
}
