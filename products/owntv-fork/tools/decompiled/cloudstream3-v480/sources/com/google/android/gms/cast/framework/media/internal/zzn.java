package com.google.android.gms.cast.framework.media.internal;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;
import androidx.media3.common.C;
import androidx.media3.common.util.Util$$ExternalSyntheticApiModelOutline0;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import com.google.android.gms.cast.framework.media.ImageHints;
import com.google.android.gms.cast.framework.media.ImagePicker;
import com.google.android.gms.cast.framework.media.MediaIntentReceiver;
import com.google.android.gms.cast.framework.media.NotificationAction;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.internal.cast.zzeo;
import com.google.android.gms.internal.cast.zzpb;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzn {
    private static final Logger zza = new Logger("MediaNotificationProxy");
    private final Context zzb;
    private final NotificationManager zzc;
    private final CastContext zzd;
    private final NotificationOptions zze;
    private final ImagePicker zzf;
    private final ComponentName zzg;
    private final ComponentName zzh;
    private List zzi = new ArrayList();
    private int[] zzj;
    private final long zzk;
    private final zzb zzl;
    private final ImageHints zzm;
    private final Resources zzn;
    private zzl zzo;
    private zzm zzp;
    private Notification zzq;
    private NotificationCompat.Action zzr;
    private NotificationCompat.Action zzs;
    private NotificationCompat.Action zzt;
    private NotificationCompat.Action zzu;
    private NotificationCompat.Action zzv;
    private NotificationCompat.Action zzw;
    private NotificationCompat.Action zzx;
    private NotificationCompat.Action zzy;

    zzn(Context context) {
        this.zzb = context;
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        this.zzc = notificationManager;
        CastContext castContext = (CastContext) Preconditions.checkNotNull(CastContext.getSharedInstance());
        this.zzd = castContext;
        CastMediaOptions castMediaOptions = (CastMediaOptions) Preconditions.checkNotNull(((CastOptions) Preconditions.checkNotNull(castContext.getCastOptions())).getCastMediaOptions());
        NotificationOptions notificationOptions = (NotificationOptions) Preconditions.checkNotNull(castMediaOptions.getNotificationOptions());
        this.zze = notificationOptions;
        this.zzf = castMediaOptions.getImagePicker();
        Resources resources = context.getResources();
        this.zzn = resources;
        this.zzg = new ComponentName(context.getApplicationContext(), castMediaOptions.getMediaIntentReceiverClassName());
        if (TextUtils.isEmpty(notificationOptions.getTargetActivityClassName())) {
            this.zzh = null;
        } else {
            this.zzh = new ComponentName(context.getApplicationContext(), notificationOptions.getTargetActivityClassName());
        }
        this.zzk = notificationOptions.getSkipStepMs();
        int dimensionPixelSize = resources.getDimensionPixelSize(notificationOptions.zze());
        ImageHints imageHints = new ImageHints(1, dimensionPixelSize, dimensionPixelSize);
        this.zzm = imageHints;
        this.zzl = new zzb(context.getApplicationContext(), imageHints);
        if (PlatformVersion.isAtLeastO() && notificationManager != null) {
            NotificationChannel notificationChannelM = Util$$ExternalSyntheticApiModelOutline0.m("cast_media_notification", ((Context) Preconditions.checkNotNull(context)).getResources().getString(R.string.media_notification_channel_name), 2);
            notificationChannelM.setShowBadge(false);
            notificationManager.createNotificationChannel(notificationChannelM);
        }
        com.google.android.gms.internal.cast.zzp.zzd(zzpb.CAF_MEDIA_NOTIFICATION_PROXY);
    }

    static boolean zze(CastOptions castOptions) {
        NotificationOptions notificationOptions;
        CastMediaOptions castMediaOptions = castOptions.getCastMediaOptions();
        if (castMediaOptions == null || (notificationOptions = castMediaOptions.getNotificationOptions()) == null) {
            return false;
        }
        com.google.android.gms.cast.framework.media.zzg zzgVarZzm = notificationOptions.zzm();
        if (zzgVarZzm == null) {
            return true;
        }
        List listZzf = zzv.zzf(zzgVarZzm);
        int[] iArrZzg = zzv.zzg(zzgVarZzm);
        int size = listZzf == null ? 0 : listZzf.size();
        if (listZzf == null || listZzf.isEmpty()) {
            zza.e("NotificationActionsProvider".concat(" doesn't provide any action."), new Object[0]);
        } else if (listZzf.size() > 5) {
            zza.e("NotificationActionsProvider".concat(" provides more than 5 actions."), new Object[0]);
        } else {
            if (iArrZzg != null && (iArrZzg.length) != 0) {
                for (int i : iArrZzg) {
                    if (i < 0 || i >= size) {
                        zza.e("NotificationActionsProvider".concat("provides a compact view action whose index is out of bounds."), new Object[0]);
                    }
                }
                return true;
            }
            zza.e("NotificationActionsProvider".concat(" doesn't provide any actions for compact view."), new Object[0]);
        }
        return false;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final NotificationCompat.Action zzf(String str) {
        int pauseDrawableResId;
        int iZzf;
        PendingIntent broadcast = null;
        switch (str.hashCode()) {
            case -1699820260:
                if (str.equals(MediaIntentReceiver.ACTION_REWIND)) {
                    long j = this.zzk;
                    if (this.zzw == null) {
                        Intent intent = new Intent(MediaIntentReceiver.ACTION_REWIND);
                        intent.setComponent(this.zzg);
                        intent.putExtra(MediaIntentReceiver.EXTRA_SKIP_STEP_MS, j);
                        PendingIntent broadcast2 = PendingIntent.getBroadcast(this.zzb, 0, intent, 134217728 | zzeo.zza);
                        NotificationOptions notificationOptions = this.zze;
                        this.zzw = new NotificationCompat.Action.Builder(zzv.zzc(notificationOptions, j), this.zzn.getString(zzv.zzd(notificationOptions, j)), broadcast2).build();
                    }
                    return this.zzw;
                }
                break;
            case -945151566:
                if (str.equals(MediaIntentReceiver.ACTION_SKIP_NEXT)) {
                    boolean z = this.zzo.zzf;
                    if (this.zzt == null) {
                        if (z) {
                            Intent intent2 = new Intent(MediaIntentReceiver.ACTION_SKIP_NEXT);
                            intent2.setComponent(this.zzg);
                            broadcast = PendingIntent.getBroadcast(this.zzb, 0, intent2, zzeo.zza);
                        }
                        NotificationOptions notificationOptions2 = this.zze;
                        this.zzt = new NotificationCompat.Action.Builder(notificationOptions2.getSkipNextDrawableResId(), this.zzn.getString(notificationOptions2.zzk()), broadcast).build();
                    }
                    return this.zzt;
                }
                break;
            case -945080078:
                if (str.equals(MediaIntentReceiver.ACTION_SKIP_PREV)) {
                    boolean z2 = this.zzo.zzg;
                    if (this.zzu == null) {
                        if (z2) {
                            Intent intent3 = new Intent(MediaIntentReceiver.ACTION_SKIP_PREV);
                            intent3.setComponent(this.zzg);
                            broadcast = PendingIntent.getBroadcast(this.zzb, 0, intent3, zzeo.zza);
                        }
                        NotificationOptions notificationOptions3 = this.zze;
                        this.zzu = new NotificationCompat.Action.Builder(notificationOptions3.getSkipPrevDrawableResId(), this.zzn.getString(notificationOptions3.zzl()), broadcast).build();
                    }
                    return this.zzu;
                }
                break;
            case -668151673:
                if (str.equals(MediaIntentReceiver.ACTION_STOP_CASTING)) {
                    if (this.zzy == null) {
                        Intent intent4 = new Intent(MediaIntentReceiver.ACTION_STOP_CASTING);
                        intent4.setComponent(this.zzg);
                        PendingIntent broadcast3 = PendingIntent.getBroadcast(this.zzb, 0, intent4, zzeo.zza);
                        NotificationOptions notificationOptions4 = this.zze;
                        this.zzy = new NotificationCompat.Action.Builder(notificationOptions4.getDisconnectDrawableResId(), this.zzn.getString(notificationOptions4.zza()), broadcast3).build();
                    }
                    return this.zzy;
                }
                break;
            case -124479363:
                if (str.equals(MediaIntentReceiver.ACTION_DISCONNECT)) {
                    if (this.zzx == null) {
                        Intent intent5 = new Intent(MediaIntentReceiver.ACTION_DISCONNECT);
                        intent5.setComponent(this.zzg);
                        PendingIntent broadcast4 = PendingIntent.getBroadcast(this.zzb, 0, intent5, zzeo.zza);
                        NotificationOptions notificationOptions5 = this.zze;
                        this.zzx = new NotificationCompat.Action.Builder(notificationOptions5.getDisconnectDrawableResId(), this.zzn.getString(notificationOptions5.zza(), ""), broadcast4).build();
                    }
                    return this.zzx;
                }
                break;
            case 235550565:
                if (str.equals(MediaIntentReceiver.ACTION_TOGGLE_PLAYBACK)) {
                    zzl zzlVar = this.zzo;
                    int i = zzlVar.zzc;
                    if (!zzlVar.zzb) {
                        if (this.zzr == null) {
                            Intent intent6 = new Intent(MediaIntentReceiver.ACTION_TOGGLE_PLAYBACK);
                            intent6.setComponent(this.zzg);
                            PendingIntent broadcast5 = PendingIntent.getBroadcast(this.zzb, 0, intent6, zzeo.zza);
                            NotificationOptions notificationOptions6 = this.zze;
                            this.zzr = new NotificationCompat.Action.Builder(notificationOptions6.getPlayDrawableResId(), this.zzn.getString(notificationOptions6.zzg()), broadcast5).build();
                        }
                        return this.zzr;
                    }
                    if (this.zzs == null) {
                        NotificationOptions notificationOptions7 = this.zze;
                        if (i == 2) {
                            pauseDrawableResId = notificationOptions7.getStopLiveStreamDrawableResId();
                            iZzf = notificationOptions7.getStopLiveStreamTitleResId();
                        } else {
                            pauseDrawableResId = notificationOptions7.getPauseDrawableResId();
                            iZzf = notificationOptions7.zzf();
                        }
                        Intent intent7 = new Intent(MediaIntentReceiver.ACTION_TOGGLE_PLAYBACK);
                        intent7.setComponent(this.zzg);
                        this.zzs = new NotificationCompat.Action.Builder(pauseDrawableResId, this.zzn.getString(iZzf), PendingIntent.getBroadcast(this.zzb, 0, intent7, zzeo.zza)).build();
                    }
                    return this.zzs;
                }
                break;
            case 1362116196:
                if (str.equals(MediaIntentReceiver.ACTION_FORWARD)) {
                    long j2 = this.zzk;
                    if (this.zzv == null) {
                        Intent intent8 = new Intent(MediaIntentReceiver.ACTION_FORWARD);
                        intent8.setComponent(this.zzg);
                        intent8.putExtra(MediaIntentReceiver.EXTRA_SKIP_STEP_MS, j2);
                        PendingIntent broadcast6 = PendingIntent.getBroadcast(this.zzb, 0, intent8, 134217728 | zzeo.zza);
                        NotificationOptions notificationOptions8 = this.zze;
                        this.zzv = new NotificationCompat.Action.Builder(zzv.zza(notificationOptions8, j2), this.zzn.getString(zzv.zzb(notificationOptions8, j2)), broadcast6).build();
                    }
                    return this.zzv;
                }
                break;
        }
        zza.e("Action: %s is not a pre-defined action.", str);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzg() {
        Bitmap bitmap;
        PendingIntent pendingIntent;
        NotificationCompat.Action actionZzf;
        NotificationManager notificationManager = this.zzc;
        if (notificationManager == null || this.zzo == null) {
            return;
        }
        zzm zzmVar = this.zzp;
        if (zzmVar == null || (bitmap = zzmVar.zzb) == null || bitmap.getWidth() <= 1 || bitmap.getHeight() <= 1) {
            bitmap = null;
        }
        Context context = this.zzb;
        NotificationCompat.Builder largeIcon = new NotificationCompat.Builder(context, "cast_media_notification").setLargeIcon(bitmap);
        NotificationOptions notificationOptions = this.zze;
        NotificationCompat.Builder visibility = largeIcon.setSmallIcon(notificationOptions.getSmallIconDrawableResId()).setContentTitle(this.zzo.zzd).setContentText(this.zzn.getString(notificationOptions.getCastingToDeviceStringResId(), this.zzo.zze)).setOngoing(true).setShowWhen(false).setVisibility(1);
        ComponentName componentName = this.zzh;
        if (componentName == null) {
            pendingIntent = null;
        } else {
            Intent intent = new Intent();
            intent.putExtra("targetActivity", componentName);
            intent.setAction(componentName.flattenToString());
            intent.setComponent(componentName);
            TaskStackBuilder taskStackBuilderCreate = TaskStackBuilder.create(context);
            taskStackBuilderCreate.addNextIntentWithParentStack(intent);
            pendingIntent = taskStackBuilderCreate.getPendingIntent(1, zzeo.zza | C.BUFFER_FLAG_FIRST_SAMPLE);
        }
        if (pendingIntent != null) {
            visibility.setContentIntent(pendingIntent);
        }
        com.google.android.gms.cast.framework.media.zzg zzgVarZzm = notificationOptions.zzm();
        if (zzgVarZzm != null) {
            zza.d("actionsProvider != null", new Object[0]);
            int[] iArrZzg = zzv.zzg(zzgVarZzm);
            this.zzj = iArrZzg != null ? (int[]) iArrZzg.clone() : null;
            List<NotificationAction> listZzf = zzv.zzf(zzgVarZzm);
            this.zzi = new ArrayList();
            if (listZzf != null) {
                for (NotificationAction notificationAction : listZzf) {
                    String action = notificationAction.getAction();
                    if (action.equals(MediaIntentReceiver.ACTION_TOGGLE_PLAYBACK) || action.equals(MediaIntentReceiver.ACTION_SKIP_NEXT) || action.equals(MediaIntentReceiver.ACTION_SKIP_PREV) || action.equals(MediaIntentReceiver.ACTION_FORWARD) || action.equals(MediaIntentReceiver.ACTION_REWIND) || action.equals(MediaIntentReceiver.ACTION_STOP_CASTING) || action.equals(MediaIntentReceiver.ACTION_DISCONNECT)) {
                        actionZzf = zzf(notificationAction.getAction());
                    } else {
                        Intent intent2 = new Intent(notificationAction.getAction());
                        intent2.setComponent(this.zzg);
                        actionZzf = new NotificationCompat.Action.Builder(notificationAction.getIconResId(), notificationAction.getContentDescription(), PendingIntent.getBroadcast(context, 0, intent2, zzeo.zza)).build();
                    }
                    if (actionZzf != null) {
                        this.zzi.add(actionZzf);
                    }
                }
            }
        } else {
            zza.d("actionsProvider == null", new Object[0]);
            this.zzi = new ArrayList();
            Iterator<String> it = notificationOptions.getActions().iterator();
            while (it.hasNext()) {
                NotificationCompat.Action actionZzf2 = zzf(it.next());
                if (actionZzf2 != null) {
                    this.zzi.add(actionZzf2);
                }
            }
            this.zzj = (int[]) notificationOptions.getCompatActionIndices().clone();
        }
        Iterator it2 = this.zzi.iterator();
        while (it2.hasNext()) {
            visibility.addAction((NotificationCompat.Action) it2.next());
        }
        androidx.media.app.NotificationCompat.MediaStyle mediaStyle = new androidx.media.app.NotificationCompat.MediaStyle();
        int[] iArr = this.zzj;
        if (iArr != null) {
            mediaStyle.setShowActionsInCompactView(iArr);
        }
        MediaSessionCompat.Token token = this.zzo.zza;
        if (token != null) {
            mediaStyle.setMediaSession(token);
        }
        visibility.setStyle(mediaStyle);
        Notification notificationBuild = visibility.build();
        this.zzq = notificationBuild;
        notificationManager.notify("castMediaNotification", 1, notificationBuild);
    }

    final void zzc() {
        this.zzl.zza();
        NotificationManager notificationManager = this.zzc;
        if (notificationManager != null) {
            notificationManager.cancel("castMediaNotification", 1);
        }
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0051  */
    final void zzd(CastDevice castDevice, RemoteMediaClient remoteMediaClient, MediaSessionCompat mediaSessionCompat, boolean z) {
        MediaInfo mediaInfo;
        MediaMetadata metadata;
        boolean z2;
        boolean z3;
        WebImage webImageOnPickImage;
        zzl zzlVar;
        if (castDevice == null || remoteMediaClient == null || mediaSessionCompat == null || (mediaInfo = remoteMediaClient.getMediaInfo()) == null || (metadata = mediaInfo.getMetadata()) == null) {
            return;
        }
        MediaStatus mediaStatus = remoteMediaClient.getMediaStatus();
        if (mediaStatus == null) {
            z2 = false;
            z3 = z2;
        } else {
            int queueRepeatMode = mediaStatus.getQueueRepeatMode();
            if (queueRepeatMode == 1 || queueRepeatMode == 2 || queueRepeatMode == 3) {
                z2 = true;
                z3 = z2;
            } else {
                Integer indexById = mediaStatus.getIndexById(mediaStatus.getCurrentItemId());
                if (indexById != null) {
                    boolean z4 = indexById.intValue() > 0;
                    z2 = indexById.intValue() < mediaStatus.getQueueItemCount() + (-1);
                    z3 = z4;
                } else {
                    z2 = false;
                    z3 = z2;
                }
            }
        }
        zzl zzlVar2 = new zzl(remoteMediaClient.getPlayerState() == 2, mediaInfo.getStreamType(), metadata.getString(MediaMetadata.KEY_TITLE), castDevice.getFriendlyName(), mediaSessionCompat.getSessionToken(), z2, z3);
        if (z || (zzlVar = this.zzo) == null || zzlVar2.zzb != zzlVar.zzb || zzlVar2.zzc != zzlVar.zzc || !CastUtils.zze(zzlVar2.zzd, zzlVar.zzd) || !CastUtils.zze(zzlVar2.zze, zzlVar.zze) || zzlVar2.zzf != zzlVar.zzf || zzlVar2.zzg != zzlVar.zzg) {
            this.zzo = zzlVar2;
            zzg();
        }
        ImagePicker imagePicker = this.zzf;
        if (imagePicker != null) {
            webImageOnPickImage = imagePicker.onPickImage(metadata, this.zzm);
        } else {
            webImageOnPickImage = metadata.hasImages() ? metadata.getImages().get(0) : null;
        }
        zzm zzmVar = new zzm(webImageOnPickImage);
        zzm zzmVar2 = this.zzp;
        if (zzmVar2 == null || !CastUtils.zze(zzmVar.zza, zzmVar2.zza)) {
            zzb zzbVar = this.zzl;
            zzbVar.zzc(new zzk(this, zzmVar));
            zzbVar.zzd(zzmVar.zza);
        }
    }
}
