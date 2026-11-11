package com.google.android.gms.cast.framework;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.cast.zzbr;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ApiMetadata;
import com.google.android.gms.common.api.ComplianceOptions;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.cast.zzbs;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.checkerframework.dataflow.qual.Pure;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public class CastSession extends Session {
    public static final /* synthetic */ int zza = 0;
    private static final Logger zzb = new Logger("CastSession");
    private final Context zzc;
    private final Set zzd;
    private final zzam zze;
    private final CastOptions zzf;
    private final com.google.android.gms.internal.cast.zzbf zzg;
    private final com.google.android.gms.cast.framework.media.internal.zzu zzh;
    private com.google.android.gms.cast.zzr zzi;
    private RemoteMediaClient zzj;
    private CastDevice zzk;
    private Cast.ApplicationConnectionResult zzl;
    private zzu zzm;

    public CastSession(Context context, String str, String str2, CastOptions castOptions, com.google.android.gms.internal.cast.zzbf zzbfVar, com.google.android.gms.cast.framework.media.internal.zzu zzuVar) {
        super(context, str, str2);
        this.zzd = new HashSet();
        this.zzc = context.getApplicationContext();
        this.zzf = castOptions;
        this.zzg = zzbfVar;
        this.zzh = zzuVar;
        this.zze = com.google.android.gms.internal.cast.zzag.zzb(context, castOptions, zzn(), new zzx(this, null));
    }

    static /* bridge */ /* synthetic */ void zzh(CastSession castSession, int i) {
        castSession.zzh.zzi(i);
        com.google.android.gms.cast.zzr zzrVar = castSession.zzi;
        if (zzrVar != null) {
            zzrVar.zzf();
            castSession.zzi = null;
        }
        castSession.zzk = null;
        RemoteMediaClient remoteMediaClient = castSession.zzj;
        if (remoteMediaClient != null) {
            remoteMediaClient.zzs(null);
            castSession.zzj = null;
        }
        castSession.zzl = null;
    }

    static /* bridge */ /* synthetic */ void zzi(CastSession castSession, String str, Task task) {
        zzam zzamVar = castSession.zze;
        if (zzamVar == null) {
            return;
        }
        try {
            if (task.isSuccessful()) {
                Cast.ApplicationConnectionResult applicationConnectionResult = (Cast.ApplicationConnectionResult) task.getResult();
                castSession.zzl = applicationConnectionResult;
                if (applicationConnectionResult.getStatus() != null && applicationConnectionResult.getStatus().isSuccess()) {
                    zzb.d("%s() -> success result", str);
                    RemoteMediaClient remoteMediaClient = new RemoteMediaClient(new com.google.android.gms.cast.internal.zzar(null));
                    castSession.zzj = remoteMediaClient;
                    remoteMediaClient.zzs(castSession.zzi);
                    castSession.zzj.registerCallback(new zzt(castSession));
                    castSession.zzj.zzq();
                    castSession.zzh.zzh(castSession.zzj, castSession.getCastDevice());
                    zzamVar.zzf((ApplicationMetadata) Preconditions.checkNotNull(applicationConnectionResult.getApplicationMetadata()), applicationConnectionResult.getApplicationStatus(), (String) Preconditions.checkNotNull(applicationConnectionResult.getSessionId()), applicationConnectionResult.getWasLaunched());
                    return;
                }
                if (applicationConnectionResult.getStatus() != null) {
                    zzb.d("%s() -> failure result", str);
                    zzamVar.zzg(applicationConnectionResult.getStatus().getStatusCode());
                    return;
                }
            } else {
                Exception exception = task.getException();
                if (exception instanceof ApiException) {
                    zzamVar.zzg(((ApiException) exception).getStatusCode());
                    return;
                }
            }
            zzamVar.zzg(2476);
        } catch (RemoteException e) {
            zzb.d(e, "Unable to call %s on %s.", "methods", "zzam");
        }
    }

    private final void zzo(Bundle bundle) {
        CastDevice fromBundle = CastDevice.getFromBundle(bundle);
        this.zzk = fromBundle;
        if (fromBundle == null) {
            if (isResuming()) {
                notifyFailedToResumeSession(2153);
                return;
            } else {
                notifyFailedToStartSession(2151);
                return;
            }
        }
        com.google.android.gms.cast.zzr zzrVar = this.zzi;
        zzaa zzaaVar = null;
        if (zzrVar != null) {
            zzrVar.zzf();
            this.zzi = null;
        }
        zzb.d("Acquiring a connection to Google Play Services for %s", this.zzk);
        CastDevice castDevice = (CastDevice) Preconditions.checkNotNull(this.zzk);
        Bundle bundle2 = new Bundle();
        CastOptions castOptions = this.zzf;
        CastMediaOptions castMediaOptions = castOptions == null ? null : castOptions.getCastMediaOptions();
        NotificationOptions notificationOptions = castMediaOptions == null ? null : castMediaOptions.getNotificationOptions();
        boolean z = castMediaOptions != null && castMediaOptions.zza();
        bundle2.putBoolean("com.google.android.gms.cast.EXTRA_CAST_FRAMEWORK_NOTIFICATION_ENABLED", notificationOptions != null);
        bundle2.putBoolean("com.google.android.gms.cast.EXTRA_CAST_REMOTE_CONTROL_NOTIFICATION_ENABLED", z);
        com.google.android.gms.internal.cast.zzbf zzbfVar = this.zzg;
        bundle2.putBoolean("com.google.android.gms.cast.EXTRA_CAST_ALWAYS_FOLLOW_SESSION_ENABLED", zzbfVar.zzw());
        bundle2.putBoolean("com.google.android.gms.cast.EXTRA_USE_ROUTE_CONNECTION", zzbfVar.zzv());
        Cast.CastOptions.Builder builder = new Cast.CastOptions.Builder(castDevice, new zzy(this, zzaaVar));
        builder.zzc(bundle2);
        com.google.android.gms.cast.zzr zzrVarZza = Cast.zza(this.zzc, builder.build());
        zzrVarZza.zzk(new zzz(this, zzaaVar));
        this.zzi = zzrVarZza;
        zzrVarZza.zze();
    }

    public void addCastListener(Cast.Listener listener) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (listener != null) {
            this.zzd.add(listener);
        }
    }

    @Override // com.google.android.gms.cast.framework.Session
    protected void end(boolean z) {
        zzam zzamVar = this.zze;
        if (zzamVar != null) {
            try {
                zzamVar.zze(z, 0);
            } catch (RemoteException e) {
                zzb.d(e, "Unable to call %s on %s.", "disconnectFromDevice", "zzam");
            }
            notifySessionEnded(0);
        }
    }

    public int getActiveInputState() throws IllegalStateException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        com.google.android.gms.cast.zzr zzrVar = this.zzi;
        if (zzrVar == null || !zzrVar.zzl()) {
            return -1;
        }
        return zzrVar.zzb();
    }

    public Cast.ApplicationConnectionResult getApplicationConnectionResult() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return this.zzl;
    }

    public ApplicationMetadata getApplicationMetadata() throws IllegalStateException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        com.google.android.gms.cast.zzr zzrVar = this.zzi;
        if (zzrVar == null || !zzrVar.zzl()) {
            return null;
        }
        return zzrVar.zzd();
    }

    public String getApplicationStatus() throws IllegalStateException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        com.google.android.gms.cast.zzr zzrVar = this.zzi;
        if (zzrVar == null || !zzrVar.zzl()) {
            return null;
        }
        return zzrVar.zzj();
    }

    @Pure
    public CastDevice getCastDevice() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return this.zzk;
    }

    public RemoteMediaClient getRemoteMediaClient() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return this.zzj;
    }

    @Override // com.google.android.gms.cast.framework.Session
    public long getSessionRemainingTimeMs() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        RemoteMediaClient remoteMediaClient = this.zzj;
        if (remoteMediaClient == null) {
            return 0L;
        }
        return remoteMediaClient.getStreamDuration() - this.zzj.getApproximateStreamPosition();
    }

    public int getStandbyState() throws IllegalStateException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        com.google.android.gms.cast.zzr zzrVar = this.zzi;
        if (zzrVar == null || !zzrVar.zzl()) {
            return -1;
        }
        return zzrVar.zzc();
    }

    public double getVolume() throws IllegalStateException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        com.google.android.gms.cast.zzr zzrVar = this.zzi;
        if (zzrVar == null || !zzrVar.zzl()) {
            return 0.0d;
        }
        return zzrVar.zza();
    }

    public boolean isMute() throws IllegalStateException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        com.google.android.gms.cast.zzr zzrVar = this.zzi;
        return zzrVar != null && zzrVar.zzl() && zzrVar.zzm();
    }

    @Override // com.google.android.gms.cast.framework.Session
    protected void onResuming(Bundle bundle) {
        this.zzk = CastDevice.getFromBundle(bundle);
    }

    @Override // com.google.android.gms.cast.framework.Session
    protected void onStarting(Bundle bundle) {
        this.zzk = CastDevice.getFromBundle(bundle);
    }

    public void removeCastListener(Cast.Listener listener) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (listener != null) {
            this.zzd.remove(listener);
        }
    }

    public void removeMessageReceivedCallbacks(String str) throws IOException, IllegalArgumentException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        com.google.android.gms.cast.zzr zzrVar = this.zzi;
        if (zzrVar != null) {
            zzrVar.zzg(str);
        }
    }

    public void requestStatus() throws IllegalStateException, IOException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        com.google.android.gms.cast.zzr zzrVar = this.zzi;
        if (zzrVar != null) {
            ((zzbr) zzrVar).doWrite(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.cast.zzbg
                /* JADX WARN: Multi-variable type inference failed */
                @Override // com.google.android.gms.common.api.internal.RemoteCall
                public final void accept(Object obj, Object obj2) throws RemoteException {
                    com.google.android.gms.cast.internal.zzy zzyVar = (com.google.android.gms.cast.internal.zzy) obj;
                    int i = zzbr.zzf;
                    ((com.google.android.gms.cast.internal.zzah) zzyVar.getService()).zzl(ApiMetadata.fromComplianceOptions(ComplianceOptions.newBuilder(zzyVar.getContext()).build()));
                    ((TaskCompletionSource) obj2).setResult(null);
                }
            }).setMethodKey(8404).build());
        }
    }

    @Override // com.google.android.gms.cast.framework.Session
    protected void resume(Bundle bundle) {
        zzo(bundle);
    }

    public PendingResult<Status> sendMessage(String str, String str2) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        com.google.android.gms.cast.zzr zzrVar = this.zzi;
        return zzrVar == null ? PendingResults.immediatePendingResult(new Status(17)) : zzbs.zza(zzrVar.zzh(str, str2), new com.google.android.gms.internal.cast.zzbr() { // from class: com.google.android.gms.cast.framework.zzr
        }, new com.google.android.gms.internal.cast.zzbr() { // from class: com.google.android.gms.cast.framework.zzs
        });
    }

    public void setMessageReceivedCallbacks(String str, Cast.MessageReceivedCallback messageReceivedCallback) throws IllegalStateException, IOException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        com.google.android.gms.cast.zzr zzrVar = this.zzi;
        if (zzrVar == null || !zzrVar.zzl()) {
            return;
        }
        zzrVar.zzi(str, messageReceivedCallback);
    }

    public void setMute(final boolean z) throws IllegalStateException, IOException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        com.google.android.gms.cast.zzr zzrVar = this.zzi;
        if (zzrVar == null || !zzrVar.zzl()) {
            return;
        }
        final zzbr zzbrVar = (zzbr) zzrVar;
        zzbrVar.doWrite(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.cast.zzba
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                zzbr.zzy(zzbrVar, z, (com.google.android.gms.cast.internal.zzy) obj, (TaskCompletionSource) obj2);
            }
        }).setMethodKey(8412).build());
    }

    public void setVolume(final double d) throws IOException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        com.google.android.gms.cast.zzr zzrVar = this.zzi;
        if (zzrVar == null || !zzrVar.zzl()) {
            return;
        }
        if (Double.isInfinite(d) || Double.isNaN(d)) {
            throw new IllegalArgumentException("Volume cannot be " + d);
        }
        final zzbr zzbrVar = (zzbr) zzrVar;
        zzbrVar.doWrite(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.cast.zzax
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                zzbr.zzz(zzbrVar, d, (com.google.android.gms.cast.internal.zzy) obj, (TaskCompletionSource) obj2);
            }
        }).setMethodKey(8411).build());
    }

    @Override // com.google.android.gms.cast.framework.Session
    protected void start(Bundle bundle) {
        zzo(bundle);
    }

    public final com.google.android.gms.cast.framework.media.internal.zzu zze() {
        return this.zzh;
    }

    public final void zzj(zzu zzuVar) {
        this.zzm = zzuVar;
    }

    @Override // com.google.android.gms.cast.framework.Session
    protected final void zzk(Bundle bundle) {
        CastDevice castDevice;
        CastDevice castDevice2;
        CastDevice fromBundle = CastDevice.getFromBundle(bundle);
        if (fromBundle == null || fromBundle.equals(this.zzk)) {
            return;
        }
        boolean z = !TextUtils.isEmpty(fromBundle.getFriendlyName()) && ((castDevice2 = this.zzk) == null || !TextUtils.equals(castDevice2.getFriendlyName(), fromBundle.getFriendlyName()));
        this.zzk = fromBundle;
        zzb.d("update to device (%s) with name %s", fromBundle, true != z ? "unchanged" : "changed");
        if (!z || (castDevice = this.zzk) == null) {
            return;
        }
        com.google.android.gms.cast.framework.media.internal.zzu zzuVar = this.zzh;
        if (zzuVar != null) {
            zzuVar.zzk(castDevice);
        }
        Iterator it = new HashSet(this.zzd).iterator();
        while (it.hasNext()) {
            ((Cast.Listener) it.next()).onDeviceNameChanged();
        }
        zzu zzuVar2 = this.zzm;
        if (zzuVar2 != null) {
            zzuVar2.zzb();
        }
    }

    public final boolean zzl() {
        return this.zzg.zzw();
    }
}
