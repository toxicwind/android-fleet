package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.measurement.dynamite.ModuleDescriptor;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.FirebaseOptions;
import com.hisense.support.tv.HisenseCountryLanInfo;
import com.ju.lib.utils.base.LoopLogic;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: classes.dex */
public final class zzee {
    public static volatile zzee zzc;
    public final Clock zza;
    public final ExecutorService zzb;
    public final String zzd;
    public final AppMeasurementSdk zze;

    @GuardedBy("listenerList")
    public final List<Pair<com.google.android.gms.measurement.internal.zzgw, zzdv>> zzf;
    public int zzg;
    public boolean zzh;
    public final String zzi;
    public volatile zzcc zzj;

    public zzee(Context context, String str, String str2, String str3, Bundle bundle) {
        if (str == null || !zzV(str2, str3)) {
            this.zzd = "FA";
        } else {
            this.zzd = str;
        }
        this.zza = DefaultClock.zza;
        this.zzb = zzbx.zzb.zzb(new zzdi(this), 1);
        this.zze = new AppMeasurementSdk(this);
        this.zzf = new ArrayList();
        try {
            if (com.google.android.gms.measurement.internal.zzig.zzc(context, FirebaseOptions.APP_ID_RESOURCE_NAME, com.google.android.gms.measurement.internal.zzfn.zza(context)) != null && !zzR()) {
                this.zzi = null;
                this.zzh = true;
                Log.w(this.zzd, "Disabling data collection. Found google_app_id in strings.xml but Google Analytics for Firebase is missing. Remove this value or add Google Analytics for Firebase to resume data collection.");
                return;
            }
        } catch (IllegalStateException unused) {
        }
        if (zzV(str2, str3)) {
            this.zzi = str2;
        } else {
            this.zzi = HisenseCountryLanInfo.LOCALE_PER_CODE;
            if (str2 == null || str3 == null) {
                if ((str3 == null) ^ (str2 == null)) {
                    Log.w(this.zzd, "Specified origin or custom app id is null. Both parameters will be ignored.");
                }
            } else {
                Log.v(this.zzd, "Deferring to Google Analytics for Firebase for event data collection. https://goo.gl/J1sWQy");
            }
        }
        this.zzb.execute(new zzcx(this, str2, str3, context, bundle));
        Application application = (Application) context.getApplicationContext();
        if (application == null) {
            Log.w(this.zzd, "Unable to register lifecycle notifications. Application null.");
        } else {
            application.registerActivityLifecycleCallbacks(new zzed(this));
        }
    }

    public static final boolean zzR() {
        try {
            Class.forName("com.google.firebase.analytics.FirebaseAnalytics");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzS(Exception exc, boolean z, boolean z2) {
        this.zzh |= z;
        if (z) {
            Log.w(this.zzd, "Data collection startup failed. No data will be collected.", exc);
            return;
        }
        if (z2) {
            zzA(5, "Error with data collection. Data lost.", exc, null, null);
        }
        Log.w(this.zzd, "Error with data collection. Data lost.", exc);
    }

    private final void zzT(String str, String str2, Bundle bundle, boolean z, boolean z2, Long l) {
        this.zzb.execute(new zzdr(this, l, str, str2, bundle, z, z2));
    }

    private final void zzU(zzdt zzdtVar) {
        this.zzb.execute(zzdtVar);
    }

    public static final boolean zzV(String str, String str2) {
        return (str2 == null || str == null || zzR()) ? false : true;
    }

    public static zzee zzg(Context context, String str, String str2, String str3, Bundle bundle) {
        Preconditions.checkNotNull(context);
        if (zzc == null) {
            synchronized (zzee.class) {
                if (zzc == null) {
                    zzc = new zzee(context, str, str2, str3, bundle);
                }
            }
        }
        return zzc;
    }

    public static void zzt(zzee zzeeVar, zzdt zzdtVar) {
        zzeeVar.zzb.execute(zzdtVar);
    }

    public final void zzA(int i, String str, Object obj, Object obj2, Object obj3) {
        this.zzb.execute(new zzdg(this, false, 5, str, obj, null, null));
    }

    public final void zzB(com.google.android.gms.measurement.internal.zzgw zzgwVar) {
        Preconditions.checkNotNull(zzgwVar);
        synchronized (this.zzf) {
            for (int i = 0; i < this.zzf.size(); i++) {
                if (zzgwVar.equals(this.zzf.get(i).first)) {
                    Log.w(this.zzd, "OnEventListener already registered.");
                    return;
                }
            }
            zzdv zzdvVar = new zzdv(zzgwVar);
            this.zzf.add(new Pair<>(zzgwVar, zzdvVar));
            if (this.zzj != null) {
                try {
                    this.zzj.registerOnMeasurementEventListener(zzdvVar);
                    return;
                } catch (BadParcelableException | NetworkOnMainThreadException | RemoteException | IllegalArgumentException | IllegalStateException | NullPointerException | SecurityException | UnsupportedOperationException unused) {
                    Log.w(this.zzd, "Failed to register event listener on calling thread. Trying again on the dynamite thread.");
                }
            }
            this.zzb.execute(new zzdp(this, zzdvVar));
        }
    }

    public final void zzC() {
        this.zzb.execute(new zzcv(this));
    }

    public final void zzD(Bundle bundle) {
        this.zzb.execute(new zzcn(this, bundle));
    }

    public final void zzE(Bundle bundle) {
        this.zzb.execute(new zzct(this, bundle));
    }

    public final void zzF(Bundle bundle) {
        this.zzb.execute(new zzcu(this, bundle));
    }

    public final void zzG(Activity activity, String str, String str2) {
        this.zzb.execute(new zzcr(this, activity, str, str2));
    }

    public final void zzH(boolean z) {
        this.zzb.execute(new zzdm(this, z));
    }

    public final void zzI(Bundle bundle) {
        this.zzb.execute(new zzdn(this, bundle));
    }

    public final void zzJ(com.google.android.gms.measurement.internal.zzgv zzgvVar) {
        zzdu zzduVar = new zzdu(zzgvVar);
        if (this.zzj != null) {
            try {
                this.zzj.setEventInterceptor(zzduVar);
                return;
            } catch (BadParcelableException | NetworkOnMainThreadException | RemoteException | IllegalArgumentException | IllegalStateException | NullPointerException | SecurityException | UnsupportedOperationException unused) {
                Log.w(this.zzd, "Failed to set event interceptor on calling thread. Trying again on the dynamite thread.");
            }
        }
        this.zzb.execute(new zzdo(this, zzduVar));
    }

    public final void zzK(Boolean bool) {
        this.zzb.execute(new zzcs(this, bool));
    }

    public final void zzL(long j) {
        this.zzb.execute(new zzcw(this, j));
    }

    public final void zzM(String str) {
        this.zzb.execute(new zzcq(this, str));
    }

    public final void zzN(String str, String str2, Object obj, boolean z) {
        this.zzb.execute(new zzds(this, str, str2, obj, z));
    }

    public final void zzO(com.google.android.gms.measurement.internal.zzgw zzgwVar) {
        Pair<com.google.android.gms.measurement.internal.zzgw, zzdv> pair;
        Preconditions.checkNotNull(zzgwVar);
        synchronized (this.zzf) {
            int i = 0;
            while (true) {
                if (i >= this.zzf.size()) {
                    pair = null;
                    break;
                } else {
                    if (zzgwVar.equals(this.zzf.get(i).first)) {
                        pair = this.zzf.get(i);
                        break;
                    }
                    i++;
                }
            }
            if (pair == null) {
                Log.w(this.zzd, "OnEventListener had not been registered.");
                return;
            }
            this.zzf.remove(pair);
            zzdv zzdvVar = (zzdv) pair.second;
            if (this.zzj != null) {
                try {
                    this.zzj.unregisterOnMeasurementEventListener(zzdvVar);
                    return;
                } catch (BadParcelableException | NetworkOnMainThreadException | RemoteException | IllegalArgumentException | IllegalStateException | NullPointerException | SecurityException | UnsupportedOperationException unused) {
                    Log.w(this.zzd, "Failed to unregister event listener on calling thread. Trying again on the dynamite thread.");
                }
            }
            this.zzb.execute(new zzdq(this, zzdvVar));
        }
    }

    public final int zza(String str) {
        zzbz zzbzVar = new zzbz();
        this.zzb.execute(new zzdj(this, str, zzbzVar));
        Integer num = (Integer) zzbz.zze(zzbzVar.zzb(10000L), Integer.class);
        if (num == null) {
            return 25;
        }
        return num.intValue();
    }

    public final long zzb() {
        zzbz zzbzVar = new zzbz();
        this.zzb.execute(new zzdc(this, zzbzVar));
        Long l = (Long) zzbz.zze(zzbzVar.zzb(500L), Long.class);
        if (l != null) {
            return l.longValue();
        }
        long jNextLong = new Random(System.nanoTime() ^ this.zza.currentTimeMillis()).nextLong();
        int i = this.zzg + 1;
        this.zzg = i;
        return jNextLong + ((long) i);
    }

    public final Bundle zzc(Bundle bundle, boolean z) {
        zzbz zzbzVar = new zzbz();
        this.zzb.execute(new zzdh(this, bundle, zzbzVar));
        if (z) {
            return zzbzVar.zzb(5000L);
        }
        return null;
    }

    public final AppMeasurementSdk zzd() {
        return this.zze;
    }

    public final zzcc zzf(Context context, boolean z) {
        try {
            return zzcb.asInterface(DynamiteModule.load(context, DynamiteModule.PREFER_HIGHEST_OR_LOCAL_VERSION, ModuleDescriptor.MODULE_ID).instantiate("com.google.android.gms.measurement.internal.AppMeasurementDynamiteService"));
        } catch (DynamiteModule.LoadingException e2) {
            zzS(e2, true, false);
            return null;
        }
    }

    public final Object zzh(int i) {
        zzbz zzbzVar = new zzbz();
        this.zzb.execute(new zzdl(this, zzbzVar, i));
        return zzbz.zze(zzbzVar.zzb(LoopLogic.DEFAULT_OVER_TIME_DURATION), Object.class);
    }

    public final String zzj() {
        return this.zzi;
    }

    @WorkerThread
    public final String zzk() {
        zzbz zzbzVar = new zzbz();
        this.zzb.execute(new zzdk(this, zzbzVar));
        return zzbzVar.zzc(120000L);
    }

    public final String zzl() {
        zzbz zzbzVar = new zzbz();
        this.zzb.execute(new zzdb(this, zzbzVar));
        return zzbzVar.zzc(50L);
    }

    public final String zzm() {
        zzbz zzbzVar = new zzbz();
        this.zzb.execute(new zzde(this, zzbzVar));
        return zzbzVar.zzc(500L);
    }

    public final String zzn() {
        zzbz zzbzVar = new zzbz();
        this.zzb.execute(new zzdd(this, zzbzVar));
        return zzbzVar.zzc(500L);
    }

    public final String zzo() {
        zzbz zzbzVar = new zzbz();
        this.zzb.execute(new zzda(this, zzbzVar));
        return zzbzVar.zzc(500L);
    }

    public final List<Bundle> zzp(String str, String str2) {
        zzbz zzbzVar = new zzbz();
        this.zzb.execute(new zzcp(this, str, str2, zzbzVar));
        List<Bundle> list = (List) zzbz.zze(zzbzVar.zzb(5000L), List.class);
        return list == null ? Collections.emptyList() : list;
    }

    public final Map<String, Object> zzq(String str, String str2, boolean z) {
        zzbz zzbzVar = new zzbz();
        this.zzb.execute(new zzdf(this, str, str2, z, zzbzVar));
        Bundle bundleZzb = zzbzVar.zzb(5000L);
        if (bundleZzb == null || bundleZzb.size() == 0) {
            return Collections.emptyMap();
        }
        HashMap map = new HashMap(bundleZzb.size());
        for (String str3 : bundleZzb.keySet()) {
            Object obj = bundleZzb.get(str3);
            if ((obj instanceof Double) || (obj instanceof Long) || (obj instanceof String)) {
                map.put(str3, obj);
            }
        }
        return map;
    }

    public final void zzu(String str) {
        this.zzb.execute(new zzcy(this, str));
    }

    public final void zzv(String str, String str2, Bundle bundle) {
        this.zzb.execute(new zzco(this, str, str2, bundle));
    }

    public final void zzw(String str) {
        this.zzb.execute(new zzcz(this, str));
    }

    public final void zzx(@NonNull String str, Bundle bundle) {
        zzT(null, str, bundle, false, true, null);
    }

    public final void zzy(String str, String str2, Bundle bundle) {
        zzT(str, str2, bundle, true, true, null);
    }

    public final void zzz(String str, String str2, Bundle bundle, long j) {
        zzT(str, str2, bundle, true, false, Long.valueOf(j));
    }
}
