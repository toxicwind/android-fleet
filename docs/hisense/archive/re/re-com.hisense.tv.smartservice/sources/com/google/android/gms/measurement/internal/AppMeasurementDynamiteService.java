package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;
import b.a.a.a.a;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.firebase.FirebaseOptions;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@DynamiteApi
public class AppMeasurementDynamiteService extends com.google.android.gms.internal.measurement.zzcb {

    @VisibleForTesting
    public zzfv zza = null;

    @GuardedBy("listenerMap")
    public final Map<Integer, zzgw> zzb = new ArrayMap();

    private final void zzb() {
        if (this.zza == null) {
            throw new IllegalStateException("Attempting to perform action before initialize.");
        }
    }

    private final void zzc(com.google.android.gms.internal.measurement.zzcf zzcfVar, String str) {
        zzb();
        this.zza.zzv().zzU(zzcfVar, str);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void beginAdUnitExposure(@NonNull String str, long j) {
        zzb();
        this.zza.zzd().zzd(str, j);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void clearConditionalUserProperty(@NonNull String str, @NonNull String str2, @NonNull Bundle bundle) {
        zzb();
        this.zza.zzq().zzz(str, str2, bundle);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void clearMeasurementEnabled(long j) {
        zzb();
        this.zza.zzq().zzV(null);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void endAdUnitExposure(@NonNull String str, long j) {
        zzb();
        this.zza.zzd().zze(str, j);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void generateEventId(com.google.android.gms.internal.measurement.zzcf zzcfVar) {
        zzb();
        long jZzq = this.zza.zzv().zzq();
        zzb();
        this.zza.zzv().zzT(zzcfVar, jZzq);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void getAppInstanceId(com.google.android.gms.internal.measurement.zzcf zzcfVar) {
        zzb();
        this.zza.zzaz().zzp(new zzh(this, zzcfVar));
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void getCachedAppInstanceId(com.google.android.gms.internal.measurement.zzcf zzcfVar) {
        zzb();
        String strZzo = this.zza.zzq().zzo();
        zzb();
        this.zza.zzv().zzU(zzcfVar, strZzo);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void getConditionalUserProperties(String str, String str2, com.google.android.gms.internal.measurement.zzcf zzcfVar) {
        zzb();
        this.zza.zzaz().zzp(new zzl(this, zzcfVar, str, str2));
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void getCurrentScreenClass(com.google.android.gms.internal.measurement.zzcf zzcfVar) {
        zzb();
        String strZzp = this.zza.zzq().zzp();
        zzb();
        this.zza.zzv().zzU(zzcfVar, strZzp);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void getCurrentScreenName(com.google.android.gms.internal.measurement.zzcf zzcfVar) {
        zzb();
        String strZzq = this.zza.zzq().zzq();
        zzb();
        this.zza.zzv().zzU(zzcfVar, strZzq);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void getGmpAppId(com.google.android.gms.internal.measurement.zzcf zzcfVar) {
        String strZzc;
        zzb();
        zzia zziaVarZzq = this.zza.zzq();
        if (zziaVarZzq.zzs.zzw() != null) {
            strZzc = zziaVarZzq.zzs.zzw();
        } else {
            try {
                strZzc = zzig.zzc(zziaVarZzq.zzs.zzau(), FirebaseOptions.APP_ID_RESOURCE_NAME, zziaVarZzq.zzs.zzz());
            } catch (IllegalStateException e2) {
                a.a(zziaVarZzq.zzs, "getGoogleAppId failed with exception", e2);
                strZzc = null;
            }
        }
        zzb();
        this.zza.zzv().zzU(zzcfVar, strZzc);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void getMaxUserProperties(String str, com.google.android.gms.internal.measurement.zzcf zzcfVar) {
        zzb();
        this.zza.zzq().zzh(str);
        zzb();
        this.zza.zzv().zzS(zzcfVar, 25);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void getTestFlag(com.google.android.gms.internal.measurement.zzcf zzcfVar, int i) {
        zzb();
        if (i == 0) {
            this.zza.zzv().zzU(zzcfVar, this.zza.zzq().zzr());
            return;
        }
        if (i == 1) {
            this.zza.zzv().zzT(zzcfVar, this.zza.zzq().zzm().longValue());
            return;
        }
        if (i != 2) {
            if (i == 3) {
                this.zza.zzv().zzS(zzcfVar, this.zza.zzq().zzl().intValue());
                return;
            } else {
                if (i != 4) {
                    return;
                }
                this.zza.zzv().zzO(zzcfVar, this.zza.zzq().zzi().booleanValue());
                return;
            }
        }
        zzkz zzkzVarZzv = this.zza.zzv();
        double dDoubleValue = this.zza.zzq().zzj().doubleValue();
        Bundle bundle = new Bundle();
        bundle.putDouble("r", dDoubleValue);
        try {
            zzcfVar.zzd(bundle);
        } catch (RemoteException e2) {
            zzkzVarZzv.zzs.zzay().zzk().zzb("Error returning double value to wrapper", e2);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void getUserProperties(String str, String str2, boolean z, com.google.android.gms.internal.measurement.zzcf zzcfVar) {
        zzb();
        this.zza.zzaz().zzp(new zzj(this, zzcfVar, str, str2, z));
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void initForTests(@NonNull Map map) {
        zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void initialize(IObjectWrapper iObjectWrapper, com.google.android.gms.internal.measurement.zzcl zzclVar, long j) {
        zzfv zzfvVar = this.zza;
        if (zzfvVar != null) {
            a.c(zzfvVar, "Attempting to initialize multiple times");
            return;
        }
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        Preconditions.checkNotNull(context);
        this.zza = zzfv.zzp(context, zzclVar, Long.valueOf(j));
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void isDataCollectionEnabled(com.google.android.gms.internal.measurement.zzcf zzcfVar) {
        zzb();
        this.zza.zzaz().zzp(new zzm(this, zzcfVar));
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void logEvent(@NonNull String str, @NonNull String str2, @NonNull Bundle bundle, boolean z, boolean z2, long j) {
        zzb();
        this.zza.zzq().zzE(str, str2, bundle, z, z2, j);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void logEventAndBundle(String str, String str2, Bundle bundle, com.google.android.gms.internal.measurement.zzcf zzcfVar, long j) {
        zzb();
        Preconditions.checkNotEmpty(str2);
        (bundle != null ? new Bundle(bundle) : new Bundle()).putString("_o", "app");
        this.zza.zzaz().zzp(new zzi(this, zzcfVar, new zzat(str2, new zzar(bundle), "app", j), str));
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void logHealthData(int i, @NonNull String str, @NonNull IObjectWrapper iObjectWrapper, @NonNull IObjectWrapper iObjectWrapper2, @NonNull IObjectWrapper iObjectWrapper3) {
        zzb();
        this.zza.zzay().zzt(i, true, false, str, iObjectWrapper == null ? null : ObjectWrapper.unwrap(iObjectWrapper), iObjectWrapper2 == null ? null : ObjectWrapper.unwrap(iObjectWrapper2), iObjectWrapper3 != null ? ObjectWrapper.unwrap(iObjectWrapper3) : null);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void onActivityCreated(@NonNull IObjectWrapper iObjectWrapper, @NonNull Bundle bundle, long j) {
        zzb();
        zzhz zzhzVar = this.zza.zzq().zza;
        if (zzhzVar != null) {
            this.zza.zzq().zzA();
            zzhzVar.onActivityCreated((Activity) ObjectWrapper.unwrap(iObjectWrapper), bundle);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void onActivityDestroyed(@NonNull IObjectWrapper iObjectWrapper, long j) {
        zzb();
        zzhz zzhzVar = this.zza.zzq().zza;
        if (zzhzVar != null) {
            this.zza.zzq().zzA();
            zzhzVar.onActivityDestroyed((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void onActivityPaused(@NonNull IObjectWrapper iObjectWrapper, long j) {
        zzb();
        zzhz zzhzVar = this.zza.zzq().zza;
        if (zzhzVar != null) {
            this.zza.zzq().zzA();
            zzhzVar.onActivityPaused((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void onActivityResumed(@NonNull IObjectWrapper iObjectWrapper, long j) {
        zzb();
        zzhz zzhzVar = this.zza.zzq().zza;
        if (zzhzVar != null) {
            this.zza.zzq().zzA();
            zzhzVar.onActivityResumed((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void onActivitySaveInstanceState(IObjectWrapper iObjectWrapper, com.google.android.gms.internal.measurement.zzcf zzcfVar, long j) {
        zzb();
        zzhz zzhzVar = this.zza.zzq().zza;
        Bundle bundle = new Bundle();
        if (zzhzVar != null) {
            this.zza.zzq().zzA();
            zzhzVar.onActivitySaveInstanceState((Activity) ObjectWrapper.unwrap(iObjectWrapper), bundle);
        }
        try {
            zzcfVar.zzd(bundle);
        } catch (RemoteException e2) {
            this.zza.zzay().zzk().zzb("Error returning bundle value to wrapper", e2);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void onActivityStarted(@NonNull IObjectWrapper iObjectWrapper, long j) {
        zzb();
        if (this.zza.zzq().zza != null) {
            this.zza.zzq().zzA();
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void onActivityStopped(@NonNull IObjectWrapper iObjectWrapper, long j) {
        zzb();
        if (this.zza.zzq().zza != null) {
            this.zza.zzq().zzA();
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void performAction(Bundle bundle, com.google.android.gms.internal.measurement.zzcf zzcfVar, long j) {
        zzb();
        zzcfVar.zzd(null);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void registerOnMeasurementEventListener(com.google.android.gms.internal.measurement.zzci zzciVar) {
        zzgw zzoVar;
        zzb();
        synchronized (this.zzb) {
            zzoVar = this.zzb.get(Integer.valueOf(zzciVar.zzd()));
            if (zzoVar == null) {
                zzoVar = new zzo(this, zzciVar);
                this.zzb.put(Integer.valueOf(zzciVar.zzd()), zzoVar);
            }
        }
        this.zza.zzq().zzJ(zzoVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void resetAnalyticsData(long j) {
        zzb();
        this.zza.zzq().zzK(j);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void setConditionalUserProperty(@NonNull Bundle bundle, long j) {
        zzb();
        if (bundle == null) {
            a.a(this.zza, "Conditional user property must not be null");
        } else {
            this.zza.zzq().zzQ(bundle, j);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void setConsent(@NonNull Bundle bundle, long j) {
        zzb();
        this.zza.zzq().zzT(bundle, j);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void setConsentThirdParty(@NonNull Bundle bundle, long j) {
        zzb();
        this.zza.zzq().zzR(bundle, -20, j);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void setCurrentScreen(@NonNull IObjectWrapper iObjectWrapper, @NonNull String str, @NonNull String str2, long j) {
        zzb();
        this.zza.zzs().zzw((Activity) ObjectWrapper.unwrap(iObjectWrapper), str, str2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void setDataCollectionEnabled(boolean z) {
        zzb();
        zzia zziaVarZzq = this.zza.zzq();
        zziaVarZzq.zza();
        zziaVarZzq.zzs.zzaz().zzp(new zzhc(zziaVarZzq, z));
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void setDefaultEventParameters(@NonNull Bundle bundle) {
        zzb();
        final zzia zziaVarZzq = this.zza.zzq();
        final Bundle bundle2 = bundle == null ? null : new Bundle(bundle);
        zziaVarZzq.zzs.zzaz().zzp(new Runnable() { // from class: com.google.android.gms.measurement.internal.zzha
            @Override // java.lang.Runnable
            public final void run() {
                zziaVarZzq.zzC(bundle2);
            }
        });
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void setEventInterceptor(com.google.android.gms.internal.measurement.zzci zzciVar) {
        zzb();
        zzn zznVar = new zzn(this, zzciVar);
        if (this.zza.zzaz().zzs()) {
            this.zza.zzq().zzU(zznVar);
        } else {
            this.zza.zzaz().zzp(new zzk(this, zznVar));
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void setInstanceIdProvider(com.google.android.gms.internal.measurement.zzck zzckVar) {
        zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void setMeasurementEnabled(boolean z, long j) {
        zzb();
        this.zza.zzq().zzV(Boolean.valueOf(z));
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void setMinimumSessionDuration(long j) {
        zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void setSessionTimeoutDuration(long j) {
        zzb();
        zzia zziaVarZzq = this.zza.zzq();
        zziaVarZzq.zzs.zzaz().zzp(new zzhe(zziaVarZzq, j));
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void setUserId(@NonNull String str, long j) {
        zzb();
        if (str == null || str.length() != 0) {
            this.zza.zzq().zzY(null, "_id", str, true, j);
        } else {
            a.c(this.zza, "User ID must be non-empty");
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void setUserProperty(@NonNull String str, @NonNull String str2, @NonNull IObjectWrapper iObjectWrapper, boolean z, long j) {
        zzb();
        this.zza.zzq().zzY(str, str2, ObjectWrapper.unwrap(iObjectWrapper), z, j);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void unregisterOnMeasurementEventListener(com.google.android.gms.internal.measurement.zzci zzciVar) {
        zzgw zzgwVarRemove;
        zzb();
        synchronized (this.zzb) {
            zzgwVarRemove = this.zzb.remove(Integer.valueOf(zzciVar.zzd()));
        }
        if (zzgwVarRemove == null) {
            zzgwVarRemove = new zzo(this, zzciVar);
        }
        this.zza.zzq().zzaa(zzgwVarRemove);
    }
}
