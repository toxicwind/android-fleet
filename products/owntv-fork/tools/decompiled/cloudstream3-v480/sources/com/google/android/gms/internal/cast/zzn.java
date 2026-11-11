package com.google.android.gms.internal.cast;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.Preconditions;
import java.math.BigInteger;
import java.util.Map;
import org.checkerframework.dataflow.qual.Pure;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzn {
    private static final Logger zza = new Logger("ApplicationAnalyticsUtils");
    private static final String zzb = "22.1.0";
    private final String zzc;
    private final Map zzd;
    private final Map zze;

    public zzn(Bundle bundle, String str) {
        this.zzc = str;
        this.zzd = zzah.zza(bundle, "com.google.android.gms.cast.DICTIONARY_CAST_STATUS_CODES_TO_APP_SESSION_ERROR");
        this.zze = zzah.zza(bundle, "com.google.android.gms.cast.DICTIONARY_CAST_STATUS_CODES_TO_APP_SESSION_CHANGE_REASON");
    }

    private final zzqd zzh(zzm zzmVar) {
        long jLongValue;
        zzqd zzqdVarZzc = zzqe.zzc();
        zzqdVarZzc.zzn(zzmVar.zzd);
        int i = zzmVar.zze;
        zzmVar.zze = i + 1;
        zzqdVarZzc.zzh(i);
        String str = zzmVar.zzc;
        if (str != null) {
            zzqdVarZzc.zzk(str);
        }
        zztu zztuVarZza = zztv.zza();
        if (!TextUtils.isEmpty(zzmVar.zzh)) {
            zzqdVarZzc.zzg(zzmVar.zzh);
            zztuVarZza.zzc(zzmVar.zzh);
        }
        if (!TextUtils.isEmpty(zzmVar.zzi)) {
            zztuVarZza.zzd(zzmVar.zzi);
        }
        if (!TextUtils.isEmpty(zzmVar.zzj)) {
            zztuVarZza.zze(zzmVar.zzj);
        }
        if (!TextUtils.isEmpty(zzmVar.zzk)) {
            zztuVarZza.zza(zzmVar.zzk);
        }
        if (!TextUtils.isEmpty(zzmVar.zzl)) {
            zztuVarZza.zzb(zzmVar.zzl);
        }
        if (!TextUtils.isEmpty(zzmVar.zzm)) {
            zztuVarZza.zzf(zzmVar.zzm);
        }
        zztuVarZza.zzg(zzbx.zza(zzmVar.zzn));
        zzqdVarZzc.zzj((zztv) zztuVarZza.zzr());
        zzpo zzpoVarZza = zzpp.zza();
        zzpoVarZza.zzb(zzb);
        zzpoVarZza.zza(this.zzc);
        zzqdVarZzc.zzb((zzpp) zzpoVarZza.zzr());
        zzps zzpsVarZza = zzpt.zza();
        if (zzmVar.zzb != null) {
            zzrb zzrbVarZza = zzrc.zza();
            zzrbVarZza.zza(zzmVar.zzb);
            zzpsVarZza.zza((zzrc) zzrbVarZza.zzr());
        }
        zzpsVarZza.zzf(false);
        String str2 = zzmVar.zzf;
        if (str2 != null) {
            try {
                String strReplace = str2.replace("-", "");
                jLongValue = new BigInteger(strReplace.substring(0, Math.min(16, strReplace.length())), 16).longValue();
            } catch (NumberFormatException e) {
                zza.w(e, "receiverSessionId %s is not valid for hash", str2);
                jLongValue = 0;
            }
            zzpsVarZza.zzh(jLongValue);
        }
        zzpsVarZza.zzb(zzmVar.zzg);
        zzpsVarZza.zzd(zzmVar.zzd());
        zzpsVarZza.zze(zzmVar.zzo);
        zzqdVarZzc.zzd(zzpsVarZza);
        return zzqdVarZzc;
    }

    private static void zzi(zzqd zzqdVar, boolean z) {
        zzps zzpsVarZzc = zzpt.zzc(zzqdVar.zza());
        zzpsVarZzc.zzf(z);
        zzqdVar.zzd(zzpsVarZzc);
    }

    public final zzqe zza(zzm zzmVar) {
        return (zzqe) zzh(zzmVar).zzr();
    }

    public final zzqe zzb(zzm zzmVar, boolean z) {
        zzqd zzqdVarZzh = zzh(zzmVar);
        zzi(zzqdVarZzh, z);
        return (zzqe) zzqdVarZzh.zzr();
    }

    public final zzqe zzc(zzm zzmVar) {
        zzqd zzqdVarZzh = zzh(zzmVar);
        zzps zzpsVarZzc = zzpt.zzc(zzqdVarZzh.zza());
        zzpsVarZzc.zzg(10);
        zzqdVarZzh.zze((zzpt) zzpsVarZzc.zzr());
        zzi(zzqdVarZzh, true);
        return (zzqe) zzqdVarZzh.zzr();
    }

    public final zzqe zzd(zzm zzmVar) {
        zzqd zzqdVarZzh = zzh(zzmVar);
        if (zzmVar.zzp == 1) {
            zzps zzpsVarZzc = zzpt.zzc(zzqdVarZzh.zza());
            zzpsVarZzc.zzg(17);
            zzqdVarZzh.zze((zzpt) zzpsVarZzc.zzr());
        }
        return (zzqe) zzqdVarZzh.zzr();
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0051  */
    /* JADX WARN: Code duplicated, block: B:8:0x002c  */
    @Pure
    public final zzqe zze(zzm zzmVar, int i) {
        int iIntValue;
        int iIntValue2;
        zzqd zzqdVarZzh = zzh(zzmVar);
        zzps zzpsVarZzc = zzpt.zzc(zzqdVarZzh.zza());
        Map map = this.zze;
        if (map != null) {
            Integer numValueOf = Integer.valueOf(i);
            if (map.containsKey(numValueOf)) {
                iIntValue = ((Integer) Preconditions.checkNotNull((Integer) map.get(numValueOf))).intValue();
            } else {
                iIntValue = i + 10000;
            }
        } else {
            iIntValue = i + 10000;
        }
        zzpsVarZzc.zzg(iIntValue);
        Map map2 = this.zzd;
        if (map2 != null) {
            Integer numValueOf2 = Integer.valueOf(i);
            if (map2.containsKey(numValueOf2)) {
                iIntValue2 = ((Integer) Preconditions.checkNotNull((Integer) map2.get(numValueOf2))).intValue();
            } else {
                iIntValue2 = i + 10000;
            }
        } else {
            iIntValue2 = i + 10000;
        }
        zzpsVarZzc.zzc(iIntValue2);
        zzqdVarZzh.zze((zzpt) zzpsVarZzc.zzr());
        return (zzqe) zzqdVarZzh.zzr();
    }

    public final zzqe zzf(zzm zzmVar, int i, int i2) {
        zzqd zzqdVarZzh = zzh(zzmVar);
        zzps zzpsVarZzc = zzpt.zzc(zzqdVarZzh.zza());
        zzpsVarZzc.zzj(i);
        zzpsVarZzc.zzi(i2);
        zzqdVarZzh.zze((zzpt) zzpsVarZzc.zzr());
        return (zzqe) zzqdVarZzh.zzr();
    }

    public final zzqe zzg(zzm zzmVar, int i) {
        zzqd zzqdVarZzh = zzh(zzmVar);
        zzps zzpsVarZzc = zzpt.zzc(zzqdVarZzh.zza());
        zzpsVarZzc.zzj(i);
        zzqdVarZzh.zze((zzpt) zzpsVarZzc.zzr());
        return (zzqe) zzqdVarZzh.zzr();
    }
}
