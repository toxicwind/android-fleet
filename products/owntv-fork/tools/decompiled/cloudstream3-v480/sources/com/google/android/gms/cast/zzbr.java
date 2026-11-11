package com.google.android.gms.cast;

import android.content.Context;
import android.os.Handler;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ApiMetadata;
import com.google.android.gms.common.api.ComplianceOptions;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RegistrationMethods;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.cast.zzet;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzbr extends GoogleApi implements zzr {
    public static final /* synthetic */ int zzf = 0;
    private static final Logger zzg = new Logger("CastClient");
    private static final Api.AbstractClientBuilder zzh;
    private static final Api zzi;
    final zzbq zza;
    TaskCompletionSource zzb;
    TaskCompletionSource zzc;
    final Map zzd;
    final Map zze;
    private Handler zzj;
    private boolean zzk;
    private boolean zzl;
    private final AtomicLong zzm;
    private final Object zzn;
    private final Object zzo;
    private ApplicationMetadata zzp;
    private String zzq;
    private double zzr;
    private boolean zzs;
    private int zzt;
    private int zzu;
    private zzat zzv;
    private final CastDevice zzw;
    private final Cast.Listener zzx;
    private final List zzy;
    private int zzz;

    static {
        zzbi zzbiVar = new zzbi();
        zzh = zzbiVar;
        zzi = new Api("Cast.API_CXLESS", zzbiVar, com.google.android.gms.cast.internal.zzal.zzb);
    }

    zzbr(Context context, Cast.CastOptions castOptions) {
        super(context, (Api<Cast.CastOptions>) zzi, castOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
        this.zza = new zzbq(this);
        this.zzn = new Object();
        this.zzo = new Object();
        this.zzy = DesugarCollections.synchronizedList(new ArrayList());
        Preconditions.checkNotNull(context, "context cannot be null");
        Preconditions.checkNotNull(castOptions, "CastOptions cannot be null");
        this.zzx = castOptions.zzb;
        this.zzw = castOptions.zza;
        this.zzd = new HashMap();
        this.zze = new HashMap();
        this.zzm = new AtomicLong(0L);
        this.zzz = 1;
        zzn();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void zzA(zzbr zzbrVar, String str, String str2, String str3, com.google.android.gms.cast.internal.zzy zzyVar, TaskCompletionSource taskCompletionSource) {
        long jIncrementAndGet = zzbrVar.zzm.incrementAndGet();
        zzbrVar.zzR();
        try {
            zzbrVar.zzd.put(Long.valueOf(jIncrementAndGet), taskCompletionSource);
            ((com.google.android.gms.cast.internal.zzah) zzyVar.getService()).zzm(str2, str3, jIncrementAndGet, ApiMetadata.fromComplianceOptions(ComplianceOptions.newBuilder(zzyVar.getContext()).build()));
        } catch (RemoteException e) {
            zzbrVar.zzd.remove(Long.valueOf(jIncrementAndGet));
            taskCompletionSource.setException(e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void zzB(zzbr zzbrVar, String str, String str2, zzbs zzbsVar, com.google.android.gms.cast.internal.zzy zzyVar, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzbrVar.zzR();
        ((com.google.android.gms.cast.internal.zzah) zzyVar.getService()).zzg(str, str2, null, ApiMetadata.fromComplianceOptions(ComplianceOptions.newBuilder(zzyVar.getContext()).build()));
        zzbrVar.zzT(taskCompletionSource);
    }

    static /* bridge */ /* synthetic */ void zzG(zzbr zzbrVar) {
        zzbrVar.zzt = -1;
        zzbrVar.zzu = -1;
        zzbrVar.zzp = null;
        zzbrVar.zzq = null;
        zzbrVar.zzr = 0.0d;
        zzbrVar.zzn();
        zzbrVar.zzs = false;
        zzbrVar.zzv = null;
    }

    static /* bridge */ /* synthetic */ void zzH(zzbr zzbrVar, com.google.android.gms.cast.internal.zza zzaVar) {
        boolean z;
        String strZza = zzaVar.zza();
        if (CastUtils.zze(strZza, zzbrVar.zzq)) {
            z = false;
        } else {
            zzbrVar.zzq = strZza;
            z = true;
        }
        zzg.d("hasChanged=%b, mFirstApplicationStatusUpdate=%b", Boolean.valueOf(z), Boolean.valueOf(zzbrVar.zzl));
        Cast.Listener listener = zzbrVar.zzx;
        if (listener != null && (z || zzbrVar.zzl)) {
            listener.onApplicationStatusChanged();
        }
        zzbrVar.zzl = false;
    }

    static /* bridge */ /* synthetic */ void zzI(zzbr zzbrVar, com.google.android.gms.cast.internal.zzac zzacVar) {
        boolean z;
        boolean z2;
        boolean z3;
        ApplicationMetadata applicationMetadataZze = zzacVar.zze();
        if (!CastUtils.zze(applicationMetadataZze, zzbrVar.zzp)) {
            zzbrVar.zzp = applicationMetadataZze;
            zzbrVar.zzx.onApplicationMetadataChanged(applicationMetadataZze);
        }
        double dZzb = zzacVar.zzb();
        if (Double.isNaN(dZzb) || Math.abs(dZzb - zzbrVar.zzr) <= 1.0E-7d) {
            z = false;
        } else {
            zzbrVar.zzr = dZzb;
            z = true;
        }
        boolean zZzg = zzacVar.zzg();
        if (zZzg != zzbrVar.zzs) {
            zzbrVar.zzs = zZzg;
            z = true;
        }
        Logger logger = zzg;
        logger.d("hasVolumeChanged=%b, mFirstDeviceStatusUpdate=%b", Boolean.valueOf(z), Boolean.valueOf(zzbrVar.zzk));
        Cast.Listener listener = zzbrVar.zzx;
        if (listener != null && (z || zzbrVar.zzk)) {
            listener.onVolumeChanged();
        }
        Double.isNaN(zzacVar.zza());
        int iZzc = zzacVar.zzc();
        if (iZzc != zzbrVar.zzt) {
            zzbrVar.zzt = iZzc;
            z2 = true;
        } else {
            z2 = false;
        }
        logger.d("hasActiveInputChanged=%b, mFirstDeviceStatusUpdate=%b", Boolean.valueOf(z2), Boolean.valueOf(zzbrVar.zzk));
        if (listener != null && (z2 || zzbrVar.zzk)) {
            listener.onActiveInputStateChanged(zzbrVar.zzt);
        }
        int iZzd = zzacVar.zzd();
        if (iZzd != zzbrVar.zzu) {
            zzbrVar.zzu = iZzd;
            z3 = true;
        } else {
            z3 = false;
        }
        logger.d("hasStandbyStateChanged=%b, mFirstDeviceStatusUpdate=%b", Boolean.valueOf(z3), Boolean.valueOf(zzbrVar.zzk));
        if (listener != null && (z3 || zzbrVar.zzk)) {
            listener.onStandbyStateChanged(zzbrVar.zzu);
        }
        if (!CastUtils.zze(zzbrVar.zzv, zzacVar.zzf())) {
            zzbrVar.zzv = zzacVar.zzf();
        }
        zzbrVar.zzk = false;
    }

    static /* bridge */ /* synthetic */ void zzL(zzbr zzbrVar, Cast.ApplicationConnectionResult applicationConnectionResult) {
        synchronized (zzbrVar.zzn) {
            TaskCompletionSource taskCompletionSource = zzbrVar.zzb;
            if (taskCompletionSource != null) {
                taskCompletionSource.setResult(applicationConnectionResult);
            }
            zzbrVar.zzb = null;
        }
    }

    static /* bridge */ /* synthetic */ void zzM(zzbr zzbrVar, long j, int i) {
        TaskCompletionSource taskCompletionSource;
        Map map = zzbrVar.zzd;
        synchronized (map) {
            Long lValueOf = Long.valueOf(j);
            taskCompletionSource = (TaskCompletionSource) map.get(lValueOf);
            map.remove(lValueOf);
        }
        if (taskCompletionSource != null) {
            if (i == 0) {
                taskCompletionSource.setResult(null);
            } else {
                taskCompletionSource.setException(zzP(i));
            }
        }
    }

    static /* bridge */ /* synthetic */ void zzN(zzbr zzbrVar, int i) {
        synchronized (zzbrVar.zzo) {
            TaskCompletionSource taskCompletionSource = zzbrVar.zzc;
            if (taskCompletionSource == null) {
                return;
            }
            if (i == 0) {
                taskCompletionSource.setResult(new Status(0));
            } else {
                taskCompletionSource.setException(zzP(i));
            }
            zzbrVar.zzc = null;
        }
    }

    private static ApiException zzP(int i) {
        return ApiExceptionUtil.fromStatus(new Status(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Task zzQ(com.google.android.gms.cast.internal.zzaj zzajVar) {
        return doUnregisterEventListener((ListenerHolder.ListenerKey) Preconditions.checkNotNull(registerListener(zzajVar, "castDeviceControllerListenerKey").getListenerKey(), "Key must not be null"), 8415);
    }

    private final void zzR() {
        Preconditions.checkState(zzl(), "Not connected to device");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzS() {
        zzg.d("removing all MessageReceivedCallbacks", new Object[0]);
        Map map = this.zze;
        synchronized (map) {
            map.clear();
        }
    }

    private final void zzT(TaskCompletionSource taskCompletionSource) {
        synchronized (this.zzn) {
            if (this.zzb != null) {
                zzU(2477);
            }
            this.zzb = taskCompletionSource;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzU(int i) {
        synchronized (this.zzn) {
            TaskCompletionSource taskCompletionSource = this.zzb;
            if (taskCompletionSource != null) {
                taskCompletionSource.setException(zzP(i));
            }
            this.zzb = null;
        }
    }

    private final void zzV() {
        Preconditions.checkState(this.zzz != 1, "Not active connection");
    }

    static /* bridge */ /* synthetic */ Handler zzo(zzbr zzbrVar) {
        if (zzbrVar.zzj == null) {
            zzbrVar.zzj = new zzet(zzbrVar.getLooper());
        }
        return zzbrVar.zzj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void zzu(zzbr zzbrVar, String str, Cast.MessageReceivedCallback messageReceivedCallback, com.google.android.gms.cast.internal.zzy zzyVar, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzbrVar.zzV();
        ApiMetadata apiMetadataFromComplianceOptions = ApiMetadata.fromComplianceOptions(ComplianceOptions.newBuilder(zzyVar.getContext()).build());
        ((com.google.android.gms.cast.internal.zzah) zzyVar.getService()).zzr(str, apiMetadataFromComplianceOptions);
        if (messageReceivedCallback != null) {
            ((com.google.android.gms.cast.internal.zzah) zzyVar.getService()).zzk(str, apiMetadataFromComplianceOptions);
        }
        taskCompletionSource.setResult(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void zzv(zzbr zzbrVar, String str, LaunchOptions launchOptions, com.google.android.gms.cast.internal.zzy zzyVar, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzbrVar.zzR();
        ((com.google.android.gms.cast.internal.zzah) zzyVar.getService()).zzh(str, launchOptions, ApiMetadata.fromComplianceOptions(ComplianceOptions.newBuilder(zzyVar.getContext()).build()));
        zzbrVar.zzT(taskCompletionSource);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void zzw(zzbr zzbrVar, Cast.MessageReceivedCallback messageReceivedCallback, String str, com.google.android.gms.cast.internal.zzy zzyVar, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzbrVar.zzV();
        if (messageReceivedCallback != null) {
            ((com.google.android.gms.cast.internal.zzah) zzyVar.getService()).zzr(str, ApiMetadata.fromComplianceOptions(ComplianceOptions.newBuilder(zzyVar.getContext()).build()));
        }
        taskCompletionSource.setResult(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void zzx(zzbr zzbrVar, String str, com.google.android.gms.cast.internal.zzy zzyVar, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzbrVar.zzR();
        ((com.google.android.gms.cast.internal.zzah) zzyVar.getService()).zzp(str, ApiMetadata.fromComplianceOptions(ComplianceOptions.newBuilder(zzyVar.getContext()).build()));
        synchronized (zzbrVar.zzo) {
            if (zzbrVar.zzc != null) {
                taskCompletionSource.setException(zzP(2001));
            } else {
                zzbrVar.zzc = taskCompletionSource;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void zzy(zzbr zzbrVar, boolean z, com.google.android.gms.cast.internal.zzy zzyVar, TaskCompletionSource taskCompletionSource) throws RemoteException {
        ((com.google.android.gms.cast.internal.zzah) zzyVar.getService()).zzn(z, zzbrVar.zzr, zzbrVar.zzs, ApiMetadata.fromComplianceOptions(ComplianceOptions.newBuilder(zzyVar.getContext()).build()));
        taskCompletionSource.setResult(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void zzz(zzbr zzbrVar, double d, com.google.android.gms.cast.internal.zzy zzyVar, TaskCompletionSource taskCompletionSource) throws RemoteException {
        ((com.google.android.gms.cast.internal.zzah) zzyVar.getService()).zzo(d, zzbrVar.zzr, zzbrVar.zzs, ApiMetadata.fromComplianceOptions(ComplianceOptions.newBuilder(zzyVar.getContext()).build()));
        taskCompletionSource.setResult(null);
    }

    @Override // com.google.android.gms.cast.zzr
    public final double zza() {
        zzR();
        return this.zzr;
    }

    @Override // com.google.android.gms.cast.zzr
    public final int zzb() {
        zzR();
        return this.zzt;
    }

    @Override // com.google.android.gms.cast.zzr
    public final int zzc() {
        zzR();
        return this.zzu;
    }

    @Override // com.google.android.gms.cast.zzr
    public final ApplicationMetadata zzd() {
        zzR();
        return this.zzp;
    }

    @Override // com.google.android.gms.cast.zzr
    public final Task zze() {
        ListenerHolder listenerHolderRegisterListener = registerListener(this.zza, "castDeviceControllerListenerKey");
        RegistrationMethods.Builder builder = RegistrationMethods.builder();
        RemoteCall remoteCall = new RemoteCall() { // from class: com.google.android.gms.cast.zzbc
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                com.google.android.gms.cast.internal.zzy zzyVar = (com.google.android.gms.cast.internal.zzy) obj;
                ComplianceOptions complianceOptionsBuild = ComplianceOptions.newBuilder(zzyVar.getContext()).build();
                ((com.google.android.gms.cast.internal.zzah) zzyVar.getService()).zzj(this.zza.zza, ApiMetadata.fromComplianceOptions(complianceOptionsBuild));
                ((com.google.android.gms.cast.internal.zzah) zzyVar.getService()).zze(ApiMetadata.fromComplianceOptions(complianceOptionsBuild));
                ((TaskCompletionSource) obj2).setResult(null);
            }
        };
        RemoteCall remoteCall2 = new RemoteCall() { // from class: com.google.android.gms.cast.zzbd
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                com.google.android.gms.cast.internal.zzy zzyVar = (com.google.android.gms.cast.internal.zzy) obj;
                int i = zzbr.zzf;
                ((com.google.android.gms.cast.internal.zzah) zzyVar.getService()).zzq(ApiMetadata.fromComplianceOptions(ComplianceOptions.newBuilder(zzyVar.getContext()).build()));
                ((TaskCompletionSource) obj2).setResult(true);
            }
        };
        this.zzz = 2;
        return doRegisterEventListener(builder.withHolder(listenerHolderRegisterListener).register(remoteCall).unregister(remoteCall2).setFeatures(zzav.zzb).setMethodKey(8428).build());
    }

    @Override // com.google.android.gms.cast.zzr
    public final Task zzf() {
        Task taskDoWrite = doWrite(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.cast.zzay
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                com.google.android.gms.cast.internal.zzy zzyVar = (com.google.android.gms.cast.internal.zzy) obj;
                int i = zzbr.zzf;
                ((com.google.android.gms.cast.internal.zzah) zzyVar.getService()).zzf(ApiMetadata.fromComplianceOptions(ComplianceOptions.newBuilder(zzyVar.getContext()).build()));
                ((TaskCompletionSource) obj2).setResult(null);
            }
        }).setMethodKey(8403).build());
        zzS();
        zzQ(this.zza);
        return taskDoWrite;
    }

    @Override // com.google.android.gms.cast.zzr
    public final Task zzg(final String str) {
        final Cast.MessageReceivedCallback messageReceivedCallback;
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Channel namespace cannot be null or empty");
        }
        Map map = this.zze;
        synchronized (map) {
            messageReceivedCallback = (Cast.MessageReceivedCallback) map.remove(str);
        }
        return doWrite(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.cast.zzaz
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                zzbr.zzw(this.zza, messageReceivedCallback, str, (com.google.android.gms.cast.internal.zzy) obj, (TaskCompletionSource) obj2);
            }
        }).setMethodKey(8414).build());
    }

    @Override // com.google.android.gms.cast.zzr
    public final Task zzh(final String str, final String str2) {
        CastUtils.throwIfInvalidNamespace(str);
        if (TextUtils.isEmpty(str2)) {
            throw new IllegalArgumentException("The message payload cannot be null or empty");
        }
        if (str2.length() <= 524288) {
            final String str3 = null;
            return doWrite(TaskApiCall.builder().run(new RemoteCall(str3, str, str2) { // from class: com.google.android.gms.cast.zzbe
                public final /* synthetic */ String zzb;
                public final /* synthetic */ String zzc;

                {
                    this.zzb = str;
                    this.zzc = str2;
                }

                @Override // com.google.android.gms.common.api.internal.RemoteCall
                public final void accept(Object obj, Object obj2) {
                    zzbr.zzA(this.zza, null, this.zzb, this.zzc, (com.google.android.gms.cast.internal.zzy) obj, (TaskCompletionSource) obj2);
                }
            }).setMethodKey(8405).build());
        }
        zzg.w("Message send failed. Message exceeds maximum size", new Object[0]);
        throw new IllegalArgumentException("Message exceeds maximum size524288");
    }

    @Override // com.google.android.gms.cast.zzr
    public final Task zzi(final String str, final Cast.MessageReceivedCallback messageReceivedCallback) {
        CastUtils.throwIfInvalidNamespace(str);
        if (messageReceivedCallback != null) {
            Map map = this.zze;
            synchronized (map) {
                map.put(str, messageReceivedCallback);
            }
        }
        return doWrite(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.cast.zzbf
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                zzbr.zzu(this.zza, str, messageReceivedCallback, (com.google.android.gms.cast.internal.zzy) obj, (TaskCompletionSource) obj2);
            }
        }).setMethodKey(8413).build());
    }

    @Override // com.google.android.gms.cast.zzr
    public final String zzj() {
        zzR();
        return this.zzq;
    }

    @Override // com.google.android.gms.cast.zzr
    public final void zzk(zzq zzqVar) {
        Preconditions.checkNotNull(zzqVar);
        this.zzy.add(zzqVar);
    }

    @Override // com.google.android.gms.cast.zzr
    public final boolean zzl() {
        return this.zzz == 3;
    }

    @Override // com.google.android.gms.cast.zzr
    public final boolean zzm() {
        zzR();
        return this.zzs;
    }

    @RequiresNonNull({"device"})
    final double zzn() {
        CastDevice castDevice = this.zzw;
        if (castDevice.hasCapability(2048)) {
            return 0.02d;
        }
        return (!castDevice.hasCapability(4) || castDevice.hasCapability(1) || "Chromecast Audio".equals(castDevice.getModelName())) ? 0.05d : 0.02d;
    }
}
