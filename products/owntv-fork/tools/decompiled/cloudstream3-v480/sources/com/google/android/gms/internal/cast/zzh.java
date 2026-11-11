package com.google.android.gms.internal.cast;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transformer;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.cct.CCTDestination;
import com.google.android.datatransport.runtime.TransportRuntime;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.SessionManager;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.api.ApiMetadata;
import com.google.android.gms.common.api.ComplianceOptions;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.checkerframework.dataflow.qual.Pure;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzh {
    Transport zzb;
    private final Context zzd;
    private final com.google.android.gms.cast.internal.zzn zze;
    private final SessionManager zzf;
    private final zzbn zzg;
    private final zzaf zzh;
    private Long zzj;
    private final ExecutorService zzk;
    private zzbw zzl;
    private static final Logger zzc = new Logger("ClientCastAnalytics");
    public static boolean zza = true;
    private int zzm = 1;
    private final String zzi = UUID.randomUUID().toString();

    private zzh(Context context, com.google.android.gms.cast.internal.zzn zznVar, SessionManager sessionManager, zzbn zzbnVar, zzaf zzafVar) {
        this.zzd = context;
        this.zze = zznVar;
        this.zzf = sessionManager;
        this.zzg = zzbnVar;
        this.zzh = zzafVar;
        zzes.zza();
        this.zzk = Executors.unconfigurableExecutorService(Executors.newCachedThreadPool());
    }

    public static zzh zza(Context context, com.google.android.gms.cast.internal.zzn zznVar, SessionManager sessionManager, zzbn zzbnVar, zzaf zzafVar) {
        return new zzh(context, zznVar, sessionManager, zzbnVar, zzafVar);
    }

    /* JADX WARN: Code duplicated, block: B:8:0x0021  */
    public static /* synthetic */ void zzb(zzh zzhVar, String str, int i, SharedPreferences sharedPreferences, Bundle bundle) {
        zzx zzxVar;
        SessionManager sessionManager = zzhVar.zzf;
        Preconditions.checkNotNull(sessionManager);
        zzbn zzbnVar = zzhVar.zzg;
        if (i == 3) {
            zzxVar = new zzx(zzhVar, zzhVar.zzh, str);
            sessionManager.addSessionManagerListener(new zzu(zzxVar), CastSession.class);
            if (zzbnVar != null) {
                zzbnVar.zzl(new zzv(zzxVar));
            }
        } else if (i == 2) {
            i = 2;
            zzxVar = new zzx(zzhVar, zzhVar.zzh, str);
            sessionManager.addSessionManagerListener(new zzu(zzxVar), CastSession.class);
            if (zzbnVar != null) {
                zzbnVar.zzl(new zzv(zzxVar));
            }
        }
        if (i == 1 || i == 2) {
            zzl zzlVar = new zzl(sharedPreferences, zzhVar, zzhVar.zzh, bundle, str);
            sessionManager.addSessionManagerListener(new zzj(zzlVar), CastSession.class);
            if (zzbnVar != null) {
                zzbnVar.zzl(new zzk(zzlVar));
            }
        }
    }

    public static /* synthetic */ void zzd(final zzh zzhVar, final zzqe zzqeVar, final int i) {
        zzbw zzbwVar = zzhVar.zzl;
        if (zzbwVar == null) {
            return;
        }
        zzbwVar.zza().addOnSuccessListener(new OnSuccessListener() { // from class: com.google.android.gms.internal.cast.zzg
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                zzh.zze(this.zza, zzqeVar, i, (Boolean) obj);
            }
        });
    }

    public static /* synthetic */ void zze(zzh zzhVar, zzqe zzqeVar, int i, Boolean bool) {
        if (bool.booleanValue()) {
            zzqd zzqdVarZzd = zzqe.zzd(zzqeVar);
            String str = zzhVar.zzi;
            zzqdVarZzd.zzl(str);
            zzqdVarZzd.zzf(str);
            Long l = zzhVar.zzj;
            if (l != null) {
                zzqdVarZzd.zzi((int) l.longValue());
            }
            zzqe zzqeVar2 = (zzqe) zzqdVarZzd.zzr();
            int i2 = zzhVar.zzm;
            int i3 = i2 - 1;
            Event eventOfTelemetry = null;
            if (i2 == 0) {
                throw null;
            }
            if (i3 == 0) {
                eventOfTelemetry = Event.ofTelemetry(i - 1, zzqeVar2);
            } else if (i3 == 1) {
                eventOfTelemetry = Event.ofData(i - 1, zzqeVar2);
            }
            zzc.d("analytics event: %s", eventOfTelemetry);
            Preconditions.checkNotNull(eventOfTelemetry);
            Transport transport = zzhVar.zzb;
            if (transport != null) {
                transport.send(eventOfTelemetry);
            }
        }
    }

    public final void zzc(Bundle bundle) {
        final int i;
        if (bundle.containsKey("com.google.android.gms.cast.FLAG_CLIENT_SESSION_ANALYTICS_MODE")) {
            i = bundle.getInt("com.google.android.gms.cast.FLAG_CLIENT_SESSION_ANALYTICS_MODE", 0);
        } else {
            i = (bundle.containsKey("com.google.android.gms.cast.FLAG_CLIENT_SESSION_ANALYTICS_ENABLED") && bundle.getBoolean("com.google.android.gms.cast.FLAG_CLIENT_SESSION_ANALYTICS_ENABLED", false)) ? 1 : 0;
        }
        boolean z = bundle.getBoolean("com.google.android.gms.cast.FLAG_CLIENT_FEATURE_USAGE_ANALYTICS_ENABLED", false);
        boolean z2 = bundle.getBoolean("com.google.android.gms.cast.FLAG_CLIENT_ANALYTICS_ENABLED", false);
        zza = z2;
        if (i == 0) {
            if (!z && !z2) {
                return;
            } else {
                i = 0;
            }
        }
        long j = bundle.getLong("com.google.android.gms.cast.FLAG_ANALYTICS_CONSENT_TIMEOUT_SECONDS", 5L);
        Context context = this.zzd;
        this.zzl = new zzbw(context, j);
        final String packageName = context.getPackageName();
        String str = String.format(Locale.ROOT, "%s.%s", packageName, "client_cast_analytics_data");
        this.zzm = bundle.getLong("com.google.android.gms.cast.FLAG_FIRELOG_UPLOAD_MODE") == 0 ? 1 : 2;
        TransportRuntime.initialize(context);
        this.zzb = TransportRuntime.getInstance().newFactory(CCTDestination.INSTANCE).getTransport("CAST_SENDER_SDK", zzqe.class, Encoding.of("proto"), new Transformer() { // from class: com.google.android.gms.internal.cast.zze
            @Override // com.google.android.datatransport.Transformer
            public final Object apply(Object obj) {
                return ((zzqe) obj).zzP();
            }
        });
        if (bundle.containsKey("com.google.android.gms.cast.FLAG_ANALYTICS_LOGGING_BUCKET_SIZE")) {
            this.zzj = Long.valueOf(bundle.getLong("com.google.android.gms.cast.FLAG_ANALYTICS_LOGGING_BUCKET_SIZE"));
        }
        final SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(str, 0);
        if (i != 0) {
            final com.google.android.gms.cast.internal.zzn zznVar = this.zze;
            final String[] strArr = {"com.google.android.gms.cast.DICTIONARY_CAST_STATUS_CODES_TO_APP_SESSION_ERROR", "com.google.android.gms.cast.DICTIONARY_CAST_STATUS_CODES_TO_APP_SESSION_CHANGE_REASON"};
            zznVar.doRead(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.cast.internal.zze
                /* JADX WARN: Multi-variable type inference failed */
                @Override // com.google.android.gms.common.api.internal.RemoteCall
                public final void accept(Object obj, Object obj2) throws RemoteException {
                    zzo zzoVar = (zzo) obj;
                    zzl zzlVar = new zzl(zznVar, (TaskCompletionSource) obj2);
                    ComplianceOptions complianceOptionsBuild = ComplianceOptions.newBuilder(zzoVar.getContext()).build();
                    ((zzak) zzoVar.getService()).zzh(zzlVar, strArr, ApiMetadata.fromComplianceOptions(complianceOptionsBuild));
                }
            }).setFeatures(com.google.android.gms.cast.zzav.zzg).setAutoResolveMissingFeatures(false).setMethodKey(8426).build()).addOnSuccessListener(new OnSuccessListener() { // from class: com.google.android.gms.internal.cast.zzd
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    zzh.zzb(this.zza, packageName, i, sharedPreferences, (Bundle) obj);
                }
            });
        }
        if (z) {
            Preconditions.checkNotNull(sharedPreferences);
            zzp.zza(sharedPreferences, this, packageName).zze();
            zzp.zzd(zzpb.CAST_CONTEXT);
        }
        if (zza) {
            zzs.zza(this, packageName);
        }
    }

    @Pure
    public final void zzf(final zzqe zzqeVar, final int i) {
        this.zzk.execute(new Runnable() { // from class: com.google.android.gms.internal.cast.zzf
            @Override // java.lang.Runnable
            public final void run() {
                zzh.zzd(this.zza, zzqeVar, i);
            }
        });
    }
}
