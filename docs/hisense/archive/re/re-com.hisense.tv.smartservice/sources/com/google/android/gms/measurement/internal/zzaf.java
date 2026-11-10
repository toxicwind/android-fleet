package com.google.android.gms.measurement.internal;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.Size;
import androidx.annotation.WorkerThread;
import b.a.a.a.a;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.ProcessUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.mediatek.twoworlds.tv.MtkTvScanDvbsBase;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class zzaf extends zzgo {
    public Boolean zza;
    public zzae zzb;
    public Boolean zzc;

    public zzaf(zzfv zzfvVar) {
        super(zzfvVar);
        this.zzb = new zzae() { // from class: com.google.android.gms.measurement.internal.zzad
            @Override // com.google.android.gms.measurement.internal.zzae
            public final String zza(String str, String str2) {
                return null;
            }
        };
    }

    public static final long zzA() {
        return zzdy.zzC.zza(null).longValue();
    }

    private final String zzB(String str, String str2) {
        try {
            String str3 = (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class, String.class).invoke(null, str, "");
            Preconditions.checkNotNull(str3);
            return str3;
        } catch (ClassNotFoundException e2) {
            a.a(this.zzs, "Could not find SystemProperties class", e2);
            return "";
        } catch (IllegalAccessException e3) {
            a.a(this.zzs, "Could not access SystemProperties.get()", e3);
            return "";
        } catch (NoSuchMethodException e4) {
            a.a(this.zzs, "Could not find SystemProperties.get() method", e4);
            return "";
        } catch (InvocationTargetException e5) {
            a.a(this.zzs, "SystemProperties.get() threw an exception", e5);
            return "";
        }
    }

    public static final long zzz() {
        return zzdy.zzc.zza(null).longValue();
    }

    @WorkerThread
    public final double zza(String str, zzdx<Double> zzdxVar) {
        if (str == null) {
            return zzdxVar.zza(null).doubleValue();
        }
        String strZza = this.zzb.zza(str, zzdxVar.zzb());
        if (TextUtils.isEmpty(strZza)) {
            return zzdxVar.zza(null).doubleValue();
        }
        try {
            return zzdxVar.zza(Double.valueOf(Double.parseDouble(strZza))).doubleValue();
        } catch (NumberFormatException unused) {
            return zzdxVar.zza(null).doubleValue();
        }
    }

    public final int zzb(@Size(min = 1) String str) {
        return zzf(str, zzdy.zzG, 500, MtkTvScanDvbsBase.EXCHANGE_TYPE_SB_GET);
    }

    public final int zzc() {
        zzkz zzkzVarZzv = this.zzs.zzv();
        Boolean boolZzj = zzkzVarZzv.zzs.zzt().zzj();
        if (zzkzVarZzv.zzm() < 201500) {
            return (boolZzj == null || boolZzj.booleanValue()) ? 25 : 100;
        }
        return 100;
    }

    public final int zzd(@Size(min = 1) String str) {
        return zzf(str, zzdy.zzH, 25, 100);
    }

    @WorkerThread
    public final int zze(String str, zzdx<Integer> zzdxVar) {
        if (str == null) {
            return zzdxVar.zza(null).intValue();
        }
        String strZza = this.zzb.zza(str, zzdxVar.zzb());
        if (TextUtils.isEmpty(strZza)) {
            return zzdxVar.zza(null).intValue();
        }
        try {
            return zzdxVar.zza(Integer.valueOf(Integer.parseInt(strZza))).intValue();
        } catch (NumberFormatException unused) {
            return zzdxVar.zza(null).intValue();
        }
    }

    @WorkerThread
    public final int zzf(String str, zzdx<Integer> zzdxVar, int i, int i2) {
        return Math.max(Math.min(zze(str, zzdxVar), i2), i);
    }

    public final long zzh() {
        this.zzs.zzaw();
        return 46000L;
    }

    @WorkerThread
    public final long zzi(String str, zzdx<Long> zzdxVar) {
        if (str == null) {
            return zzdxVar.zza(null).longValue();
        }
        String strZza = this.zzb.zza(str, zzdxVar.zzb());
        if (TextUtils.isEmpty(strZza)) {
            return zzdxVar.zza(null).longValue();
        }
        try {
            return zzdxVar.zza(Long.valueOf(Long.parseLong(strZza))).longValue();
        } catch (NumberFormatException unused) {
            return zzdxVar.zza(null).longValue();
        }
    }

    @VisibleForTesting
    public final Bundle zzj() {
        try {
            if (this.zzs.zzau().getPackageManager() == null) {
                this.zzs.zzay().zzd().zza("Failed to load metadata: PackageManager is null");
                return null;
            }
            ApplicationInfo applicationInfo = Wrappers.packageManager(this.zzs.zzau()).getApplicationInfo(this.zzs.zzau().getPackageName(), 128);
            if (applicationInfo != null) {
                return applicationInfo.metaData;
            }
            this.zzs.zzay().zzd().zza("Failed to load metadata: ApplicationInfo is null");
            return null;
        } catch (PackageManager.NameNotFoundException e2) {
            a.a(this.zzs, "Failed to load metadata: Package name not found", e2);
            return null;
        }
    }

    @VisibleForTesting
    public final Boolean zzk(@Size(min = 1) String str) {
        Preconditions.checkNotEmpty(str);
        Bundle bundleZzj = zzj();
        if (bundleZzj == null) {
            a.a(this.zzs, "Failed to load metadata: Metadata bundle is null");
            return null;
        }
        if (bundleZzj.containsKey(str)) {
            return Boolean.valueOf(bundleZzj.getBoolean(str));
        }
        return null;
    }

    public final String zzl() {
        return zzB("debug.firebase.analytics.app", "");
    }

    public final String zzm() {
        return zzB("debug.deferred.deeplink", "");
    }

    public final String zzn() {
        this.zzs.zzaw();
        return "FA";
    }

    @WorkerThread
    public final String zzo(String str, zzdx<String> zzdxVar) {
        return str == null ? zzdxVar.zza(null) : zzdxVar.zza(this.zzb.zza(str, zzdxVar.zzb()));
    }

    /* JADX WARN: Code duplicated, block: B:12:0x003a A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:13:0x003b A[Catch: NotFoundException -> 0x0040, TRY_LEAVE, TryCatch #0 {NotFoundException -> 0x0040, blocks: (B:10:0x0026, B:13:0x003b), top: B:18:0x0026 }] */
    /* JADX WARN: Code duplicated, block: B:18:0x0026 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @VisibleForTesting
    public final List<String> zzp(@Size(min = 1) String str) {
        Integer numValueOf;
        String[] stringArray;
        Preconditions.checkNotEmpty("analytics.safelisted_events");
        Bundle bundleZzj = zzj();
        if (bundleZzj != null) {
            if (bundleZzj.containsKey("analytics.safelisted_events")) {
                numValueOf = Integer.valueOf(bundleZzj.getInt("analytics.safelisted_events"));
            }
            if (numValueOf != null) {
                try {
                    stringArray = this.zzs.zzau().getResources().getStringArray(numValueOf.intValue());
                    if (stringArray == null) {
                        return null;
                    }
                    return Arrays.asList(stringArray);
                } catch (Resources.NotFoundException e2) {
                    a.a(this.zzs, "Failed to load string array from metadata: resource not found", e2);
                }
            }
            return null;
        }
        a.a(this.zzs, "Failed to load metadata: Metadata bundle is null");
        numValueOf = null;
        if (numValueOf != null) {
            stringArray = this.zzs.zzau().getResources().getStringArray(numValueOf.intValue());
            if (stringArray == null) {
                return null;
            }
            return Arrays.asList(stringArray);
        }
        return null;
    }

    public final void zzq(zzae zzaeVar) {
        this.zzb = zzaeVar;
    }

    public final boolean zzr() {
        Boolean boolZzk = zzk("google_analytics_adid_collection_enabled");
        return boolZzk == null || boolZzk.booleanValue();
    }

    @WorkerThread
    public final boolean zzs(String str, zzdx<Boolean> zzdxVar) {
        if (str == null) {
            return zzdxVar.zza(null).booleanValue();
        }
        String strZza = this.zzb.zza(str, zzdxVar.zzb());
        if (TextUtils.isEmpty(strZza)) {
            return zzdxVar.zza(null).booleanValue();
        }
        return zzdxVar.zza(Boolean.valueOf(this.zzs.zzf().zzs(null, zzdy.zzaB) ? "1".equals(strZza) : Boolean.parseBoolean(strZza))).booleanValue();
    }

    public final boolean zzt(String str) {
        return "1".equals(this.zzb.zza(str, "gaia_collection_enabled"));
    }

    public final boolean zzu() {
        Boolean boolZzk = zzk("google_analytics_automatic_screen_reporting_enabled");
        return boolZzk == null || boolZzk.booleanValue();
    }

    public final boolean zzv() {
        this.zzs.zzaw();
        Boolean boolZzk = zzk("firebase_analytics_collection_deactivated");
        return boolZzk != null && boolZzk.booleanValue();
    }

    public final boolean zzw(String str) {
        return "1".equals(this.zzb.zza(str, "measurement.event_sampling_enabled"));
    }

    @WorkerThread
    public final boolean zzx() {
        if (this.zza == null) {
            this.zza = zzk("app_measurement_lite");
            if (this.zza == null) {
                this.zza = false;
            }
        }
        return this.zza.booleanValue() || !this.zzs.zzN();
    }

    public final boolean zzy() {
        if (this.zzc == null) {
            synchronized (this) {
                if (this.zzc == null) {
                    ApplicationInfo applicationInfo = this.zzs.zzau().getApplicationInfo();
                    String myProcessName = ProcessUtils.getMyProcessName();
                    if (applicationInfo != null) {
                        String str = applicationInfo.processName;
                        boolean z = false;
                        if (str != null && str.equals(myProcessName)) {
                            z = true;
                        }
                        this.zzc = Boolean.valueOf(z);
                    }
                    if (this.zzc == null) {
                        this.zzc = Boolean.TRUE;
                        this.zzs.zzay().zzd().zza("My process not in the list of running processes");
                    }
                }
            }
        }
        return this.zzc.booleanValue();
    }
}
