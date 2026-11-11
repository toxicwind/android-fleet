package com.google.android.gms.cast.framework.media.internal;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import androidx.media3.common.C;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.ReconnectionService;
import com.google.android.gms.cast.framework.SessionManager;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import com.google.android.gms.cast.framework.media.ImagePicker;
import com.google.android.gms.cast.framework.media.MediaIntentReceiver;
import com.google.android.gms.cast.framework.media.NotificationAction;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.internal.cast.zzbf;
import com.google.android.gms.internal.cast.zzeo;
import com.google.android.gms.internal.cast.zzet;
import java.util.List;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzu {
    public static final /* synthetic */ int zza = 0;
    private static final Logger zzb = new Logger("MediaSessionManager");
    private final Context zzc;
    private final CastOptions zzd;
    private final zzbf zze;
    private final SessionManager zzf;
    private final NotificationOptions zzg;
    private final ComponentName zzh;
    private final ComponentName zzi;
    private final zzb zzj;
    private final zzb zzk;
    private final zzn zzl;
    private final Handler zzm;
    private final Runnable zzn;
    private final RemoteMediaClient.Callback zzo;
    private RemoteMediaClient zzp;
    private CastDevice zzq;
    private MediaSessionCompat zzr;
    private MediaSessionCompat.Callback zzs;
    private boolean zzt;
    private PlaybackStateCompat.CustomAction zzu;
    private PlaybackStateCompat.CustomAction zzv;
    private PlaybackStateCompat.CustomAction zzw;
    private PlaybackStateCompat.CustomAction zzx;

    /* JADX WARN: Multi-variable type inference failed */
    public zzu(Context context, CastOptions castOptions, zzbf zzbfVar) {
        this.zzc = context;
        this.zzd = castOptions;
        this.zze = zzbfVar;
        CastContext sharedInstance = CastContext.getSharedInstance();
        Object[] objArr = 0;
        this.zzf = sharedInstance != null ? sharedInstance.getSessionManager() : null;
        CastMediaOptions castMediaOptions = castOptions.getCastMediaOptions();
        this.zzg = castMediaOptions == null ? null : castMediaOptions.getNotificationOptions();
        this.zzo = new zzs(this, objArr == true ? 1 : 0);
        String expandedControllerActivityClassName = castMediaOptions == null ? null : castMediaOptions.getExpandedControllerActivityClassName();
        this.zzh = !TextUtils.isEmpty(expandedControllerActivityClassName) ? new ComponentName(context, expandedControllerActivityClassName) : null;
        String mediaIntentReceiverClassName = castMediaOptions == null ? null : castMediaOptions.getMediaIntentReceiverClassName();
        this.zzi = !TextUtils.isEmpty(mediaIntentReceiverClassName) ? new ComponentName(context, mediaIntentReceiverClassName) : null;
        zzb zzbVar = new zzb(context);
        this.zzj = zzbVar;
        zzbVar.zzc(new zzp(this));
        zzb zzbVar2 = new zzb(context);
        this.zzk = zzbVar2;
        zzbVar2.zzc(new zzq(this));
        this.zzm = new zzet(Looper.getMainLooper());
        this.zzl = zzn.zze(castOptions) ? new zzn(context) : null;
        this.zzn = new Runnable() { // from class: com.google.android.gms.cast.framework.media.internal.zzo
            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zzq(false);
            }
        };
    }

    private final long zzm(String str, int i, Bundle bundle) {
        long j;
        int iHashCode = str.hashCode();
        if (iHashCode != -945151566) {
            if (iHashCode != -945080078) {
                if (iHashCode == 235550565 && str.equals(MediaIntentReceiver.ACTION_TOGGLE_PLAYBACK)) {
                    int i2 = 3;
                    if (i == 3) {
                        j = 514;
                    } else {
                        i2 = i;
                        j = 512;
                    }
                    if (i2 != 2) {
                        return j;
                    }
                    return 516L;
                }
            } else if (str.equals(MediaIntentReceiver.ACTION_SKIP_PREV)) {
                RemoteMediaClient remoteMediaClient = this.zzp;
                if (remoteMediaClient != null && remoteMediaClient.zzu()) {
                    return 16L;
                }
                bundle.putBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_PREVIOUS", true);
                return 0L;
            }
        } else if (str.equals(MediaIntentReceiver.ACTION_SKIP_NEXT)) {
            RemoteMediaClient remoteMediaClient2 = this.zzp;
            if (remoteMediaClient2 != null && remoteMediaClient2.zzt()) {
                return 32L;
            }
            bundle.putBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_NEXT", true);
        }
        return 0L;
    }

    private final Uri zzn(MediaMetadata mediaMetadata, int i) {
        WebImage webImageOnPickImage;
        CastMediaOptions castMediaOptions = this.zzd.getCastMediaOptions();
        ImagePicker imagePicker = castMediaOptions == null ? null : castMediaOptions.getImagePicker();
        if (imagePicker != null) {
            webImageOnPickImage = imagePicker.onPickImage(mediaMetadata, i);
        } else {
            webImageOnPickImage = mediaMetadata.hasImages() ? mediaMetadata.getImages().get(0) : null;
        }
        if (webImageOnPickImage == null) {
            return null;
        }
        return webImageOnPickImage.getUrl();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:37:0x00d3 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:38:0x00d5  */
    /* JADX WARN: Code duplicated, block: B:39:0x00e7  */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final void zzp(PlaybackStateCompat.Builder builder, String str, NotificationAction notificationAction) {
        PlaybackStateCompat.CustomAction customActionBuild;
        NotificationOptions notificationOptions;
        NotificationOptions notificationOptions2;
        NotificationOptions notificationOptions3;
        NotificationOptions notificationOptions4;
        switch (str.hashCode()) {
            case -1699820260:
                if (str.equals(MediaIntentReceiver.ACTION_REWIND)) {
                    if (this.zzv == null && (notificationOptions = this.zzg) != null) {
                        long skipStepMs = notificationOptions.getSkipStepMs();
                        this.zzv = new PlaybackStateCompat.CustomAction.Builder(MediaIntentReceiver.ACTION_REWIND, this.zzc.getResources().getString(zzv.zzd(notificationOptions, skipStepMs)), zzv.zzc(notificationOptions, skipStepMs)).build();
                    }
                    customActionBuild = this.zzv;
                } else if (notificationAction == null) {
                    customActionBuild = null;
                } else {
                    customActionBuild = new PlaybackStateCompat.CustomAction.Builder(str, notificationAction.getContentDescription(), notificationAction.getIconResId()).build();
                }
                break;
            case -668151673:
                if (str.equals(MediaIntentReceiver.ACTION_STOP_CASTING)) {
                    if (this.zzw == null && (notificationOptions2 = this.zzg) != null) {
                        this.zzw = new PlaybackStateCompat.CustomAction.Builder(MediaIntentReceiver.ACTION_STOP_CASTING, this.zzc.getResources().getString(notificationOptions2.zza()), notificationOptions2.getDisconnectDrawableResId()).build();
                    }
                    customActionBuild = this.zzw;
                } else if (notificationAction == null) {
                    customActionBuild = null;
                } else {
                    customActionBuild = new PlaybackStateCompat.CustomAction.Builder(str, notificationAction.getContentDescription(), notificationAction.getIconResId()).build();
                }
                break;
            case -124479363:
                if (str.equals(MediaIntentReceiver.ACTION_DISCONNECT)) {
                    if (this.zzx == null && (notificationOptions3 = this.zzg) != null) {
                        this.zzx = new PlaybackStateCompat.CustomAction.Builder(MediaIntentReceiver.ACTION_DISCONNECT, this.zzc.getResources().getString(notificationOptions3.zza()), notificationOptions3.getDisconnectDrawableResId()).build();
                    }
                    customActionBuild = this.zzx;
                } else if (notificationAction == null) {
                    customActionBuild = null;
                } else {
                    customActionBuild = new PlaybackStateCompat.CustomAction.Builder(str, notificationAction.getContentDescription(), notificationAction.getIconResId()).build();
                }
                break;
            case 1362116196:
                if (str.equals(MediaIntentReceiver.ACTION_FORWARD)) {
                    if (this.zzu == null && (notificationOptions4 = this.zzg) != null) {
                        long skipStepMs2 = notificationOptions4.getSkipStepMs();
                        this.zzu = new PlaybackStateCompat.CustomAction.Builder(MediaIntentReceiver.ACTION_FORWARD, this.zzc.getResources().getString(zzv.zzb(notificationOptions4, skipStepMs2)), zzv.zza(notificationOptions4, skipStepMs2)).build();
                    }
                    customActionBuild = this.zzu;
                } else if (notificationAction == null) {
                    customActionBuild = null;
                } else {
                    customActionBuild = new PlaybackStateCompat.CustomAction.Builder(str, notificationAction.getContentDescription(), notificationAction.getIconResId()).build();
                }
                break;
            default:
                if (notificationAction == null) {
                    customActionBuild = null;
                } else {
                    customActionBuild = new PlaybackStateCompat.CustomAction.Builder(str, notificationAction.getContentDescription(), notificationAction.getIconResId()).build();
                }
                break;
        }
        if (customActionBuild != null) {
            builder.addCustomAction(customActionBuild);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresNonNull({"appContext", "handler", "options"})
    public final void zzq(boolean z) {
        if (this.zzd.getEnableReconnectionService()) {
            Runnable runnable = this.zzn;
            if (runnable != null) {
                this.zzm.removeCallbacks(runnable);
            }
            Context context = this.zzc;
            Intent intent = new Intent(context, (Class<?>) ReconnectionService.class);
            intent.setPackage(context.getPackageName());
            try {
                context.startService(intent);
            } catch (IllegalStateException unused) {
                if (z) {
                    this.zzm.postDelayed(this.zzn, 1000L);
                }
            }
        }
    }

    private final void zzr() {
        zzn zznVar = this.zzl;
        if (zznVar != null) {
            zzb.d("Stopping media notification.", new Object[0]);
            zznVar.zzc();
        }
    }

    private final void zzs() {
        if (this.zzd.getEnableReconnectionService()) {
            this.zzm.removeCallbacks(this.zzn);
            Context context = this.zzc;
            Intent intent = new Intent(context, (Class<?>) ReconnectionService.class);
            intent.setPackage(context.getPackageName());
            context.stopService(intent);
        }
    }

    private final void zzt(int i, MediaInfo mediaInfo) {
        PlaybackStateCompat playbackStateCompatBuild;
        MediaSessionCompat mediaSessionCompat;
        MediaMetadata metadata;
        PendingIntent activity;
        MediaSessionCompat mediaSessionCompat2 = this.zzr;
        if (mediaSessionCompat2 == null) {
            return;
        }
        Bundle bundle = new Bundle();
        PlaybackStateCompat.Builder builder = new PlaybackStateCompat.Builder();
        RemoteMediaClient remoteMediaClient = this.zzp;
        if (remoteMediaClient == null || this.zzl == null) {
            playbackStateCompatBuild = builder.build();
        } else {
            builder.setState(i, (remoteMediaClient.zza() == 0 || remoteMediaClient.isLiveStream()) ? 0L : remoteMediaClient.getApproximateStreamPosition(), 1.0f);
            if (i == 0) {
                playbackStateCompatBuild = builder.build();
            } else {
                NotificationOptions notificationOptions = this.zzg;
                com.google.android.gms.cast.framework.media.zzg zzgVarZzm = notificationOptions != null ? notificationOptions.zzm() : null;
                RemoteMediaClient remoteMediaClient2 = this.zzp;
                long jZzm = (remoteMediaClient2 == null || remoteMediaClient2.isLiveStream() || this.zzp.isPlayingAd()) ? 0L : 256L;
                if (zzgVarZzm != null) {
                    List<NotificationAction> listZzf = zzv.zzf(zzgVarZzm);
                    if (listZzf != null) {
                        for (NotificationAction notificationAction : listZzf) {
                            String action = notificationAction.getAction();
                            if (zzu(action)) {
                                jZzm |= zzm(action, i, bundle);
                            } else {
                                zzp(builder, action, notificationAction);
                            }
                        }
                    }
                } else if (notificationOptions != null) {
                    for (String str : notificationOptions.getActions()) {
                        if (zzu(str)) {
                            jZzm |= zzm(str, i, bundle);
                        } else {
                            zzp(builder, str, null);
                        }
                    }
                }
                playbackStateCompatBuild = builder.setActions(jZzm).build();
            }
        }
        mediaSessionCompat2.setPlaybackState(playbackStateCompatBuild);
        NotificationOptions notificationOptions2 = this.zzg;
        if (notificationOptions2 != null && notificationOptions2.zzp()) {
            bundle.putBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_PREVIOUS", true);
        }
        if (notificationOptions2 != null && notificationOptions2.zzo()) {
            bundle.putBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_NEXT", true);
        }
        if (bundle.containsKey("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_PREVIOUS") || bundle.containsKey("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_NEXT")) {
            mediaSessionCompat2.setExtras(bundle);
        }
        if (i == 0) {
            mediaSessionCompat2.setMetadata(new MediaMetadataCompat.Builder().build());
            return;
        }
        if (this.zzp != null) {
            ComponentName componentName = this.zzh;
            if (componentName == null) {
                activity = null;
            } else {
                Intent intent = new Intent();
                intent.setComponent(componentName);
                activity = PendingIntent.getActivity(this.zzc, 0, intent, zzeo.zza | C.BUFFER_FLAG_FIRST_SAMPLE);
            }
            if (activity != null) {
                mediaSessionCompat2.setSessionActivity(activity);
            }
        }
        if (this.zzp == null || (mediaSessionCompat = this.zzr) == null || mediaInfo == null || (metadata = mediaInfo.getMetadata()) == null) {
            return;
        }
        RemoteMediaClient remoteMediaClient3 = this.zzp;
        long streamDuration = (remoteMediaClient3 == null || !remoteMediaClient3.isLiveStream()) ? mediaInfo.getStreamDuration() : 0L;
        String string = metadata.getString(MediaMetadata.KEY_TITLE);
        String string2 = metadata.getString(MediaMetadata.KEY_SUBTITLE);
        MediaMetadataCompat.Builder builderPutLong = zzo().putLong("android.media.metadata.DURATION", streamDuration);
        if (string != null) {
            builderPutLong.putString("android.media.metadata.TITLE", string);
            builderPutLong.putString("android.media.metadata.DISPLAY_TITLE", string);
        }
        if (string2 != null) {
            builderPutLong.putString("android.media.metadata.DISPLAY_SUBTITLE", string2);
        }
        mediaSessionCompat.setMetadata(builderPutLong.build());
        Uri uriZzn = zzn(metadata, 0);
        if (uriZzn != null) {
            this.zzj.zzd(uriZzn);
        } else {
            zzj(null, 0);
        }
        Uri uriZzn2 = zzn(metadata, 3);
        if (uriZzn2 != null) {
            this.zzk.zzd(uriZzn2);
        } else {
            zzj(null, 3);
        }
    }

    private static final boolean zzu(String str) {
        return TextUtils.equals(str, MediaIntentReceiver.ACTION_TOGGLE_PLAYBACK) || TextUtils.equals(str, MediaIntentReceiver.ACTION_SKIP_PREV) || TextUtils.equals(str, MediaIntentReceiver.ACTION_SKIP_NEXT);
    }

    public final void zzi(int i) {
        AudioManager audioManager;
        if (this.zzt) {
            this.zzt = false;
            RemoteMediaClient remoteMediaClient = this.zzp;
            if (remoteMediaClient != null) {
                remoteMediaClient.unregisterCallback(this.zzo);
            }
            if (!PlatformVersion.isAtLeastLollipop() && (audioManager = (AudioManager) this.zzc.getSystemService("audio")) != null) {
                audioManager.abandonAudioFocus(null);
            }
            this.zze.zzt(null);
            zzb zzbVar = this.zzj;
            if (zzbVar != null) {
                zzbVar.zza();
            }
            zzb zzbVar2 = this.zzk;
            if (zzbVar2 != null) {
                zzbVar2.zza();
            }
            MediaSessionCompat mediaSessionCompat = this.zzr;
            if (mediaSessionCompat != null) {
                mediaSessionCompat.setCallback(null);
                this.zzr.setMetadata(new MediaMetadataCompat.Builder().build());
                zzt(0, null);
            }
            MediaSessionCompat mediaSessionCompat2 = this.zzr;
            if (mediaSessionCompat2 != null) {
                mediaSessionCompat2.setActive(false);
                this.zzr.release();
                this.zzr = null;
            }
            this.zzp = null;
            this.zzq = null;
            this.zzs = null;
            zzr();
            if (i == 0) {
                zzs();
            }
        }
    }

    final void zzj(Bitmap bitmap, int i) {
        MediaSessionCompat mediaSessionCompat = this.zzr;
        if (mediaSessionCompat == null) {
            return;
        }
        if (bitmap == null || bitmap.getWidth() <= 1 || bitmap.getHeight() <= 1) {
            bitmap = Bitmap.createBitmap(2, 2, Bitmap.Config.ARGB_8888);
            bitmap.eraseColor(0);
        }
        mediaSessionCompat.setMetadata(zzo().putBitmap(i == 0 ? "android.media.metadata.DISPLAY_ICON" : "android.media.metadata.ALBUM_ART", bitmap).build());
    }

    public final void zzk(CastDevice castDevice) {
        zzb.i("update Cast device to %s", castDevice);
        this.zzq = castDevice;
        zzl(false);
    }

    public final void zzl(boolean z) {
        MediaQueueItem loadingItem;
        RemoteMediaClient remoteMediaClient = this.zzp;
        if (remoteMediaClient == null) {
            return;
        }
        int iZza = remoteMediaClient.zza();
        MediaInfo mediaInfo = remoteMediaClient.getMediaInfo();
        if (remoteMediaClient.isLoadingNextItem() && (loadingItem = remoteMediaClient.getLoadingItem()) != null && loadingItem.getMedia() != null) {
            mediaInfo = loadingItem.getMedia();
        }
        zzt(iZza, mediaInfo);
        if (!remoteMediaClient.hasMediaSession()) {
            zzr();
            zzs();
        } else if (iZza != 0) {
            zzn zznVar = this.zzl;
            if (zznVar != null) {
                zzb.d("Update media notification.", new Object[0]);
                zznVar.zzd(this.zzq, this.zzp, this.zzr, z);
            }
            if (remoteMediaClient.isLoadingNextItem()) {
                return;
            }
            zzq(true);
        }
    }

    private final MediaMetadataCompat.Builder zzo() {
        MediaSessionCompat mediaSessionCompat = this.zzr;
        MediaMetadataCompat metadata = mediaSessionCompat == null ? null : mediaSessionCompat.getController().getMetadata();
        return metadata == null ? new MediaMetadataCompat.Builder() : new MediaMetadataCompat.Builder(metadata);
    }

    public final void zzh(RemoteMediaClient remoteMediaClient, CastDevice castDevice) {
        ComponentName componentName;
        AudioManager audioManager;
        CastOptions castOptions = this.zzd;
        CastMediaOptions castMediaOptions = castOptions == null ? null : castOptions.getCastMediaOptions();
        if (this.zzt || castOptions == null || castMediaOptions == null || this.zzg == null || remoteMediaClient == null || castDevice == null || (componentName = this.zzi) == null) {
            zzb.d("skip attaching media session", new Object[0]);
            return;
        }
        this.zzp = remoteMediaClient;
        remoteMediaClient.registerCallback(this.zzo);
        this.zzq = castDevice;
        if (!PlatformVersion.isAtLeastLollipop() && (audioManager = (AudioManager) this.zzc.getSystemService("audio")) != null) {
            audioManager.requestAudioFocus(null, 3, 3);
        }
        Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
        intent.setComponent(componentName);
        Context context = this.zzc;
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, intent, zzeo.zza);
        if (castMediaOptions.getMediaSessionEnabled()) {
            MediaSessionCompat mediaSessionCompat = new MediaSessionCompat(context, "CastMediaSession", componentName, broadcast);
            this.zzr = mediaSessionCompat;
            zzt(0, null);
            CastDevice castDevice2 = this.zzq;
            if (castDevice2 != null && !TextUtils.isEmpty(castDevice2.getFriendlyName())) {
                mediaSessionCompat.setMetadata(new MediaMetadataCompat.Builder().putString("android.media.metadata.ALBUM_ARTIST", context.getResources().getString(R.string.cast_casting_to_device, this.zzq.getFriendlyName())).build());
            }
            zzr zzrVar = new zzr(this);
            this.zzs = zzrVar;
            mediaSessionCompat.setCallback(zzrVar);
            mediaSessionCompat.setActive(true);
            this.zze.zzt(mediaSessionCompat);
        }
        this.zzt = true;
        zzl(false);
    }
}
