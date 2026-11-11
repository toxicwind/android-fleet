package com.google.android.gms.cast;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.text.TextUtils;
import android.view.Display;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.media3.common.C;
import androidx.media3.common.util.Util$$ExternalSyntheticApiModelOutline0;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.internal.cast.zzeo;
import com.google.android.gms.internal.cast.zzet;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
@Deprecated
public abstract class CastRemoteDisplayLocalService extends Service {
    private static final Logger zza = new Logger("CastRDLocalService");
    private static final int zzb = R.id.cast_notification_id;
    private static final Object zzc = new Object();
    private static final AtomicBoolean zzd = new AtomicBoolean(false);
    private static CastRemoteDisplayLocalService zze;
    private String zzf;
    private WeakReference zzg;
    private zzao zzh;
    private NotificationSettings zzi;
    private Notification zzj;
    private boolean zzk;
    private PendingIntent zzl;
    private CastDevice zzm;
    private Display zzn;
    private Context zzo;
    private ServiceConnection zzp;
    private Handler zzq;
    private MediaRouter zzr;
    private CastRemoteDisplayClient zzt;
    private boolean zzs = false;
    private final MediaRouter.Callback zzu = new zzaf(this);
    private final IBinder zzv = new zzan(this);

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
    public interface Callbacks {
        void onRemoteDisplayMuteStateChanged(boolean z);

        void onRemoteDisplaySessionEnded(CastRemoteDisplayLocalService castRemoteDisplayLocalService);

        void onRemoteDisplaySessionError(Status status);

        void onRemoteDisplaySessionStarted(CastRemoteDisplayLocalService castRemoteDisplayLocalService);

        void onServiceCreated(CastRemoteDisplayLocalService castRemoteDisplayLocalService);
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
    public static final class NotificationSettings {
        private Notification zza;
        private PendingIntent zzb;
        private String zzc;
        private String zzd;

        /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
        public static final class Builder {
            private final NotificationSettings zza = new NotificationSettings(null);

            public NotificationSettings build() {
                NotificationSettings notificationSettings = this.zza;
                if (notificationSettings.zza != null) {
                    if (!TextUtils.isEmpty(notificationSettings.zzc)) {
                        throw new IllegalArgumentException("notificationTitle requires using the default notification");
                    }
                    if (!TextUtils.isEmpty(notificationSettings.zzd)) {
                        throw new IllegalArgumentException("notificationText requires using the default notification");
                    }
                    if (notificationSettings.zzb != null) {
                        throw new IllegalArgumentException("notificationPendingIntent requires using the default notification");
                    }
                } else if (TextUtils.isEmpty(notificationSettings.zzc) && TextUtils.isEmpty(notificationSettings.zzd) && notificationSettings.zzb == null) {
                    throw new IllegalArgumentException("At least an argument must be provided");
                }
                return notificationSettings;
            }

            public Builder setNotification(Notification notification) {
                this.zza.zza = notification;
                return this;
            }

            public Builder setNotificationPendingIntent(PendingIntent pendingIntent) {
                this.zza.zzb = pendingIntent;
                return this;
            }

            public Builder setNotificationText(String str) {
                this.zza.zzd = str;
                return this;
            }

            public Builder setNotificationTitle(String str) {
                this.zza.zzc = str;
                return this;
            }
        }

        private NotificationSettings() {
            throw null;
        }

        /* synthetic */ NotificationSettings(NotificationSettings notificationSettings, zzap zzapVar) {
            this.zza = notificationSettings.zza;
            this.zzb = notificationSettings.zzb;
            this.zzc = notificationSettings.zzc;
            this.zzd = notificationSettings.zzd;
        }

        /* synthetic */ NotificationSettings(zzap zzapVar) {
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
    public static class Options {
        int zza = 2;

        public int getConfigPreset() {
            return this.zza;
        }

        public void setConfigPreset(int i) {
            this.zza = i;
        }
    }

    public static CastRemoteDisplayLocalService getInstance() {
        CastRemoteDisplayLocalService castRemoteDisplayLocalService;
        synchronized (zzc) {
            castRemoteDisplayLocalService = zze;
        }
        return castRemoteDisplayLocalService;
    }

    protected static void setDebugEnabled() {
        zza.zzb(true);
    }

    public static void startService(Context context, Class<? extends CastRemoteDisplayLocalService> cls, String str, CastDevice castDevice, NotificationSettings notificationSettings, Callbacks callbacks) {
        startServiceWithOptions(context, cls, str, castDevice, new Options(), notificationSettings, callbacks);
    }

    public static void startServiceWithOptions(Context context, Class<? extends CastRemoteDisplayLocalService> cls, String str, CastDevice castDevice, Options options, NotificationSettings notificationSettings, Callbacks callbacks) {
        Logger logger = zza;
        logger.d("Starting Service", new Object[0]);
        synchronized (zzc) {
            if (zze != null) {
                logger.w("An existing service had not been stopped before starting one", new Object[0]);
                zzw(true);
            }
        }
        try {
            ServiceInfo serviceInfo = context.getPackageManager().getServiceInfo(new ComponentName(context, cls), 128);
            if (serviceInfo != null && serviceInfo.exported) {
                throw new IllegalStateException("The service must not be exported, verify the manifest configuration");
            }
            Preconditions.checkNotNull(context, "activityContext is required.");
            Preconditions.checkNotNull(cls, "serviceClass is required.");
            Preconditions.checkNotNull(str, "applicationId is required.");
            Preconditions.checkNotNull(castDevice, "device is required.");
            Preconditions.checkNotNull(options, "options is required.");
            Preconditions.checkNotNull(notificationSettings, "notificationSettings is required.");
            Preconditions.checkNotNull(callbacks, "callbacks is required.");
            if (notificationSettings.zza == null && notificationSettings.zzb == null) {
                throw new IllegalArgumentException("notificationSettings: Either the notification or the notificationPendingIntent must be provided");
            }
            if (zzd.getAndSet(true)) {
                logger.e("Service is already being started, startService has been called twice", new Object[0]);
                return;
            }
            Intent intent = new Intent(context, cls);
            context.startService(intent);
            ConnectionTracker.getInstance().bindService(context, intent, new zzah(str, castDevice, options, notificationSettings, context, callbacks), 64);
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalStateException("Service not found, did you forget to configure it in the manifest?", e);
        }
    }

    public static void stopService() {
        zzw(false);
    }

    static /* bridge */ /* synthetic */ void zzl(CastRemoteDisplayLocalService castRemoteDisplayLocalService, Display display) {
        if (display == null) {
            zza.e("Cast Remote Display session created without display", new Object[0]);
            return;
        }
        castRemoteDisplayLocalService.zzn = display;
        if (castRemoteDisplayLocalService.zzk) {
            Notification notificationZzu = castRemoteDisplayLocalService.zzu(true);
            castRemoteDisplayLocalService.zzj = notificationZzu;
            castRemoteDisplayLocalService.startForeground(zzb, notificationZzu);
        }
        Callbacks callbacks = (Callbacks) castRemoteDisplayLocalService.zzg.get();
        if (callbacks != null) {
            callbacks.onRemoteDisplaySessionStarted(castRemoteDisplayLocalService);
        }
        Preconditions.checkNotNull(castRemoteDisplayLocalService.zzn, "display is required.");
        castRemoteDisplayLocalService.onCreatePresentation(castRemoteDisplayLocalService.zzn);
    }

    static /* bridge */ /* synthetic */ void zzo(CastRemoteDisplayLocalService castRemoteDisplayLocalService) {
        Callbacks callbacks = (Callbacks) castRemoteDisplayLocalService.zzg.get();
        if (callbacks != null) {
            callbacks.onRemoteDisplaySessionError(new Status(CastStatusCodes.ERROR_SERVICE_CREATION_FAILED));
        }
        stopService();
    }

    static /* bridge */ /* synthetic */ void zzq(CastRemoteDisplayLocalService castRemoteDisplayLocalService, NotificationSettings notificationSettings) {
        Preconditions.checkMainThread("updateNotificationSettingsInternal must be called on the main thread");
        if (castRemoteDisplayLocalService.zzi == null) {
            throw new IllegalStateException("No current notification settings to update");
        }
        if (!castRemoteDisplayLocalService.zzk) {
            Preconditions.checkNotNull(notificationSettings.zza, "notification is required.");
            Notification notification = notificationSettings.zza;
            castRemoteDisplayLocalService.zzj = notification;
            castRemoteDisplayLocalService.zzi.zza = notification;
        } else {
            if (notificationSettings.zza != null) {
                throw new IllegalStateException("Current mode is default notification, notification attribute must not be provided");
            }
            if (notificationSettings.zzb != null) {
                castRemoteDisplayLocalService.zzi.zzb = notificationSettings.zzb;
            }
            if (!TextUtils.isEmpty(notificationSettings.zzc)) {
                castRemoteDisplayLocalService.zzi.zzc = notificationSettings.zzc;
            }
            if (!TextUtils.isEmpty(notificationSettings.zzd)) {
                castRemoteDisplayLocalService.zzi.zzd = notificationSettings.zzd;
            }
            castRemoteDisplayLocalService.zzj = castRemoteDisplayLocalService.zzu(true);
        }
        castRemoteDisplayLocalService.startForeground(zzb, castRemoteDisplayLocalService.zzj);
    }

    static /* bridge */ /* synthetic */ boolean zzt(CastRemoteDisplayLocalService castRemoteDisplayLocalService, String str, CastDevice castDevice, Options options, NotificationSettings notificationSettings, Context context, ServiceConnection serviceConnection, Callbacks callbacks) {
        castRemoteDisplayLocalService.zzv("startRemoteDisplaySession");
        Preconditions.checkMainThread("Starting the Cast Remote Display must be done on the main thread");
        synchronized (zzc) {
            if (zze != null) {
                zza.w("An existing service had not been stopped before starting one", new Object[0]);
                return false;
            }
            zze = castRemoteDisplayLocalService;
            castRemoteDisplayLocalService.zzg = new WeakReference(callbacks);
            castRemoteDisplayLocalService.zzf = str;
            castRemoteDisplayLocalService.zzm = castDevice;
            castRemoteDisplayLocalService.zzo = context;
            castRemoteDisplayLocalService.zzp = serviceConnection;
            if (castRemoteDisplayLocalService.zzr == null) {
                castRemoteDisplayLocalService.zzr = MediaRouter.getInstance(castRemoteDisplayLocalService.getApplicationContext());
            }
            Preconditions.checkNotNull(castRemoteDisplayLocalService.zzf, "applicationId is required.");
            MediaRouteSelector mediaRouteSelectorBuild = new MediaRouteSelector.Builder().addControlCategory(CastMediaControlIntent.categoryForCast(castRemoteDisplayLocalService.zzf)).build();
            castRemoteDisplayLocalService.zzv("addMediaRouterCallback");
            castRemoteDisplayLocalService.zzr.addCallback(mediaRouteSelectorBuild, castRemoteDisplayLocalService.zzu, 4);
            castRemoteDisplayLocalService.zzj = notificationSettings.zza;
            zzap zzapVar = null;
            castRemoteDisplayLocalService.zzh = new zzao(zzapVar);
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.google.android.gms.cast.remote_display.ACTION_NOTIFICATION_DISCONNECT");
            intentFilter.addAction("com.google.android.gms.cast.remote_display.ACTION_SESSION_ENDED");
            if (PlatformVersion.isAtLeastT()) {
                castRemoteDisplayLocalService.registerReceiver(castRemoteDisplayLocalService.zzh, intentFilter, 4);
            } else {
                ContextCompat.registerReceiver(castRemoteDisplayLocalService, castRemoteDisplayLocalService.zzh, intentFilter, 2);
            }
            NotificationSettings notificationSettings2 = new NotificationSettings(notificationSettings, zzapVar);
            castRemoteDisplayLocalService.zzi = notificationSettings2;
            if (notificationSettings2.zza == null) {
                castRemoteDisplayLocalService.zzk = true;
                castRemoteDisplayLocalService.zzj = castRemoteDisplayLocalService.zzu(false);
            } else {
                castRemoteDisplayLocalService.zzk = false;
                castRemoteDisplayLocalService.zzj = castRemoteDisplayLocalService.zzi.zza;
            }
            castRemoteDisplayLocalService.startForeground(zzb, castRemoteDisplayLocalService.zzj);
            castRemoteDisplayLocalService.zzv("startRemoteDisplay");
            Intent intent = new Intent("com.google.android.gms.cast.remote_display.ACTION_SESSION_ENDED");
            Preconditions.checkNotNull(castRemoteDisplayLocalService.zzo, "activityContext is required.");
            intent.setPackage(castRemoteDisplayLocalService.zzo.getPackageName());
            PendingIntent broadcast = PendingIntent.getBroadcast(castRemoteDisplayLocalService, 0, intent, zzeo.zza);
            zzak zzakVar = new zzak(castRemoteDisplayLocalService);
            Preconditions.checkNotNull(castRemoteDisplayLocalService.zzf, "applicationId is required.");
            castRemoteDisplayLocalService.zzt.zze(castDevice, castRemoteDisplayLocalService.zzf, options.getConfigPreset(), broadcast, zzakVar).addOnCompleteListener(new zzal(castRemoteDisplayLocalService));
            Callbacks callbacks2 = (Callbacks) castRemoteDisplayLocalService.zzg.get();
            if (callbacks2 == null) {
                return true;
            }
            callbacks2.onServiceCreated(castRemoteDisplayLocalService);
            return true;
        }
    }

    private final Notification zzu(boolean z) {
        int i;
        int i2;
        zzv("createDefaultNotification");
        String str = this.zzi.zzc;
        String string = this.zzi.zzd;
        if (z) {
            i = R.string.cast_notification_connected_message;
            i2 = R.drawable.cast_ic_notification_on;
        } else {
            i = R.string.cast_notification_connecting_message;
            i2 = R.drawable.cast_ic_notification_connecting;
        }
        if (TextUtils.isEmpty(str)) {
            str = (String) getPackageManager().getApplicationLabel(getApplicationInfo());
        }
        if (TextUtils.isEmpty(string)) {
            string = getString(i, new Object[]{this.zzm.getFriendlyName()});
        }
        NotificationCompat.Builder ongoing = new NotificationCompat.Builder(this, "cast_remote_display_local_service").setContentTitle(str).setContentText(string).setContentIntent(this.zzi.zzb).setSmallIcon(i2).setOngoing(true);
        String string2 = getString(R.string.cast_notification_disconnect);
        if (this.zzl == null) {
            Preconditions.checkNotNull(this.zzo, "activityContext is required.");
            Intent intent = new Intent("com.google.android.gms.cast.remote_display.ACTION_NOTIFICATION_DISCONNECT");
            intent.setPackage(this.zzo.getPackageName());
            this.zzl = PendingIntent.getBroadcast(this, 0, intent, zzeo.zza | C.BUFFER_FLAG_FIRST_SAMPLE);
        }
        return ongoing.addAction(android.R.drawable.ic_menu_close_clear_cancel, string2, this.zzl).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzv(String str) {
        zza.d("[Instance: %s] %s", this, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzw(boolean z) {
        Logger logger = zza;
        logger.d("Stopping Service", new Object[0]);
        zzd.set(false);
        synchronized (zzc) {
            CastRemoteDisplayLocalService castRemoteDisplayLocalService = zze;
            if (castRemoteDisplayLocalService == null) {
                logger.e("Service is already being stopped", new Object[0]);
                return;
            }
            zze = null;
            if (castRemoteDisplayLocalService.zzq != null) {
                if (Looper.myLooper() != Looper.getMainLooper()) {
                    castRemoteDisplayLocalService.zzq.post(new zzai(castRemoteDisplayLocalService, z));
                } else {
                    castRemoteDisplayLocalService.zzx(z);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzx(boolean z) {
        zzv("Stopping Service");
        Preconditions.checkMainThread("stopServiceInstanceInternal must be called on the main thread");
        if (!z && this.zzr != null) {
            zzv("Setting default route");
            MediaRouter mediaRouter = this.zzr;
            mediaRouter.selectRoute(mediaRouter.getDefaultRoute());
        }
        if (this.zzh != null) {
            zzv("Unregistering notification receiver");
            unregisterReceiver(this.zzh);
        }
        zzv("stopRemoteDisplaySession");
        zzv("stopRemoteDisplay");
        this.zzt.stopRemoteDisplay().addOnCompleteListener(new zzam(this));
        Callbacks callbacks = (Callbacks) this.zzg.get();
        if (callbacks != null) {
            callbacks.onRemoteDisplaySessionEnded(this);
        }
        onDismissPresentation();
        zzv("Stopping the remote display Service");
        stopForeground(true);
        stopSelf();
        if (this.zzr != null) {
            Preconditions.checkMainThread("CastRemoteDisplayLocalService calls must be done on the main thread");
            zzv("removeMediaRouterCallback");
            this.zzr.removeCallback(this.zzu);
        }
        Context context = this.zzo;
        ServiceConnection serviceConnection = this.zzp;
        if (context != null && serviceConnection != null) {
            try {
                ConnectionTracker.getInstance().unbindService(context, serviceConnection);
            } catch (IllegalArgumentException unused) {
                zzv("No need to unbind service, already unbound");
            }
        }
        this.zzp = null;
        this.zzo = null;
        this.zzf = null;
        this.zzj = null;
        this.zzn = null;
    }

    protected Display getCastRemoteDisplay() {
        return this.zzn;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        zzv("onBind");
        return this.zzv;
    }

    @Override // android.app.Service
    public void onCreate() {
        zzv("onCreate");
        super.onCreate();
        zzet zzetVar = new zzet(getMainLooper());
        this.zzq = zzetVar;
        zzetVar.postDelayed(new zzag(this), 100L);
        if (this.zzt == null) {
            this.zzt = CastRemoteDisplay.getClient(this);
        }
        if (PlatformVersion.isAtLeastO()) {
            NotificationManager notificationManager = (NotificationManager) getSystemService(NotificationManager.class);
            Util$$ExternalSyntheticApiModelOutline0.m7417m();
            NotificationChannel notificationChannelM = Util$$ExternalSyntheticApiModelOutline0.m("cast_remote_display_local_service", getString(R.string.cast_notification_default_channel_name), 2);
            notificationChannelM.setShowBadge(false);
            notificationManager.createNotificationChannel(notificationChannelM);
        }
    }

    public abstract void onCreatePresentation(Display display);

    public abstract void onDismissPresentation();

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        zzv("onStartCommand");
        this.zzs = true;
        return 2;
    }

    @Deprecated
    public void updateNotificationSettings(NotificationSettings notificationSettings) {
        if (PlatformVersion.isAtLeastS()) {
            return;
        }
        Preconditions.checkNotNull(notificationSettings, "notificationSettings is required.");
        Preconditions.checkNotNull(this.zzq, "Service is not ready yet.");
        this.zzq.post(new zzaj(this, notificationSettings));
    }
}
