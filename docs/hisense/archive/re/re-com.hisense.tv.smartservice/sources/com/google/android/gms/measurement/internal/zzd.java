package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import androidx.annotation.WorkerThread;
import androidx.collection.ArrayMap;
import b.a.a.a.a;
import com.google.android.gms.common.internal.Preconditions;
import com.hisense.support.tv.HisenseCountryLanInfo;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class zzd extends zze {
    public final Map<String, Long> zza;
    public final Map<String, Integer> zzb;
    public long zzc;

    public zzd(zzfv zzfvVar) {
        super(zzfvVar);
        this.zzb = new ArrayMap();
        this.zza = new ArrayMap();
    }

    public static /* synthetic */ void zza(zzd zzdVar, String str, long j) {
        zzdVar.zzg();
        Preconditions.checkNotEmpty(str);
        if (zzdVar.zzb.isEmpty()) {
            zzdVar.zzc = j;
        }
        Integer num = zzdVar.zzb.get(str);
        if (num != null) {
            zzdVar.zzb.put(str, Integer.valueOf(num.intValue() + 1));
        } else if (zzdVar.zzb.size() >= 100) {
            a.c(zzdVar.zzs, "Too many ads visible");
        } else {
            zzdVar.zzb.put(str, 1);
            zzdVar.zza.put(str, Long.valueOf(j));
        }
    }

    public static /* synthetic */ void zzb(zzd zzdVar, String str, long j) {
        zzdVar.zzg();
        Preconditions.checkNotEmpty(str);
        Integer num = zzdVar.zzb.get(str);
        if (num == null) {
            a.a(zzdVar.zzs, "Call to endAdUnitExposure for unknown ad unit id", str);
            return;
        }
        zzih zzihVarZzj = zzdVar.zzs.zzs().zzj(false);
        int iIntValue = num.intValue() - 1;
        if (iIntValue != 0) {
            zzdVar.zzb.put(str, Integer.valueOf(iIntValue));
            return;
        }
        zzdVar.zzb.remove(str);
        Long l = zzdVar.zza.get(str);
        if (l == null) {
            a.a(zzdVar.zzs, "First ad unit exposure time was never set");
        } else {
            long jLongValue = l.longValue();
            zzdVar.zza.remove(str);
            zzdVar.zzi(str, j - jLongValue, zzihVarZzj);
        }
        if (zzdVar.zzb.isEmpty()) {
            long j2 = zzdVar.zzc;
            if (j2 == 0) {
                a.a(zzdVar.zzs, "First ad exposure time was never set");
            } else {
                zzdVar.zzh(j - j2, zzihVarZzj);
                zzdVar.zzc = 0L;
            }
        }
    }

    @WorkerThread
    private final void zzh(long j, zzih zzihVar) {
        if (zzihVar == null) {
            a.b(this.zzs, "Not logging ad exposure. No active activity");
            return;
        }
        if (j < 1000) {
            this.zzs.zzay().zzj().zzb("Not logging ad exposure. Less than 1000 ms. exposure", Long.valueOf(j));
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putLong("_xt", j);
        zzkz.zzJ(zzihVar, bundle, true);
        this.zzs.zzq().zzG(HisenseCountryLanInfo.LOCALE_AMH_CODE, "_xa", bundle);
    }

    @WorkerThread
    private final void zzi(String str, long j, zzih zzihVar) {
        if (zzihVar == null) {
            a.b(this.zzs, "Not logging ad unit exposure. No active activity");
            return;
        }
        if (j < 1000) {
            this.zzs.zzay().zzj().zzb("Not logging ad unit exposure. Less than 1000 ms. exposure", Long.valueOf(j));
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("_ai", str);
        bundle.putLong("_xt", j);
        zzkz.zzJ(zzihVar, bundle, true);
        this.zzs.zzq().zzG(HisenseCountryLanInfo.LOCALE_AMH_CODE, "_xu", bundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public final void zzj(long j) {
        Iterator<String> it = this.zza.keySet().iterator();
        while (it.hasNext()) {
            this.zza.put(it.next(), Long.valueOf(j));
        }
        if (this.zza.isEmpty()) {
            return;
        }
        this.zzc = j;
    }

    public final void zzd(String str, long j) {
        if (str == null || str.length() == 0) {
            a.a(this.zzs, "Ad unit id must be a non-empty string");
        } else {
            this.zzs.zzaz().zzp(new zza(this, str, j));
        }
    }

    public final void zze(String str, long j) {
        if (str == null || str.length() == 0) {
            a.a(this.zzs, "Ad unit id must be a non-empty string");
        } else {
            this.zzs.zzaz().zzp(new zzb(this, str, j));
        }
    }

    @WorkerThread
    public final void zzf(long j) {
        zzih zzihVarZzj = this.zzs.zzs().zzj(false);
        for (String str : this.zza.keySet()) {
            zzi(str, j - this.zza.get(str).longValue(), zzihVarZzj);
        }
        if (!this.zza.isEmpty()) {
            zzh(j - this.zzc, zzihVarZzj);
        }
        zzj(j);
    }
}
