package com.google.android.gms.internal.cast;

import android.text.TextUtils;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.DefaultClock;
import j$.util.DesugarCollections;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzz {
    public static final /* synthetic */ int zzc = 0;
    private static final Logger zzd = new Logger("SessionFlowSummary");
    private static final String zze = "22.1.0";
    private static long zzf = System.currentTimeMillis();
    CastSession zza;
    private final zzh zzl;
    private final String zzm;
    private final long zzo;
    private String zzp;
    private String zzq;
    private zzr zzr;
    private String zzs;
    private String zzt;
    private String zzu;
    private String zzv;
    private String zzw;
    private String zzx;
    private int zzy;
    private final zzgr zzg = zzgu.zza(new zzgr() { // from class: com.google.android.gms.internal.cast.zzy
        @Override // com.google.android.gms.internal.cast.zzgr
        public final Object zza() {
            int i = zzz.zzc;
            return ((CastContext) Preconditions.checkNotNull(CastContext.getSharedInstance())).getCastOptions().getReceiverApplicationId();
        }
    });
    private final List zzh = DesugarCollections.synchronizedList(new ArrayList());
    private final List zzi = DesugarCollections.synchronizedList(new ArrayList());
    private final List zzj = DesugarCollections.synchronizedList(new ArrayList());
    private final Map zzk = DesugarCollections.synchronizedMap(new HashMap());
    public int zzb = 0;
    private final long zzn = DefaultClock.getInstance().currentTimeMillis();

    private zzz(zzh zzhVar, String str) {
        this.zzl = zzhVar;
        this.zzm = str;
        long j = zzf;
        zzf = 1 + j;
        this.zzo = j;
    }

    public static zzz zza(zzh zzhVar, String str) {
        return new zzz(zzhVar, str);
    }

    final void zzb(zzbz zzbzVar) {
        zzbzVar.zzb(this.zzn);
        this.zzj.add(zzbzVar);
    }

    final void zzc(zzcb zzcbVar) {
        zzcbVar.zzb(this.zzn);
        this.zzh.add(zzcbVar);
    }

    final void zzd(zzab zzabVar) {
        zzabVar.zzb(this.zzn);
        this.zzi.add(zzabVar);
    }

    final void zze() {
        this.zzy++;
    }

    public final void zzf() {
        long jLongValue;
        CastSession castSession = this.zza;
        if (castSession != null) {
            castSession.zzj(null);
            this.zza = null;
        }
        long j = this.zzo;
        zzqd zzqdVarZzc = zzqe.zzc();
        zzqdVarZzc.zzn(j);
        String str = this.zzq;
        if (str != null) {
            zzqdVarZzc.zzk(str);
        }
        zztu zztuVarZza = zztv.zza();
        if (!TextUtils.isEmpty(this.zzs)) {
            zzqdVarZzc.zzg(this.zzs);
            zztuVarZza.zzc(this.zzs);
        }
        if (!TextUtils.isEmpty(this.zzt)) {
            zztuVarZza.zzd(this.zzt);
        }
        if (!TextUtils.isEmpty(this.zzu)) {
            zztuVarZza.zze(this.zzu);
        }
        if (!TextUtils.isEmpty(this.zzv)) {
            zztuVarZza.zza(this.zzv);
        }
        if (!TextUtils.isEmpty(this.zzw)) {
            zztuVarZza.zzb(this.zzw);
        }
        if (!TextUtils.isEmpty(this.zzx)) {
            zztuVarZza.zzf(this.zzx);
        }
        zztuVarZza.zzg(zzbx.zza(this.zzb));
        zzqdVarZzc.zzj((zztv) zztuVarZza.zzr());
        zzpo zzpoVarZza = zzpp.zza();
        zzpoVarZza.zzb(zze);
        zzpoVarZza.zza(this.zzm);
        zzqdVarZzc.zzb((zzpp) zzpoVarZza.zzr());
        zzgr zzgrVar = this.zzg;
        zzql zzqlVarZza = zzqm.zza();
        String str2 = (String) zzgrVar.zza();
        if (str2 != null) {
            zzrb zzrbVarZza = zzrc.zza();
            zzrbVarZza.zza(str2);
            zzqlVarZza.zzf((zzrc) zzrbVarZza.zzr());
        }
        String str3 = this.zzp;
        if (str3 != null) {
            try {
                String strReplace = str3.replace("-", "");
                jLongValue = new BigInteger(strReplace.substring(0, Math.min(16, strReplace.length())), 16).longValue();
            } catch (NumberFormatException e) {
                zzd.w(e, "receiverSessionId %s is not valid for hash", str3);
                jLongValue = 0;
            }
            zzqlVarZza.zzh(jLongValue);
        }
        List list = this.zzh;
        if (!list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(((zzcb) it.next()).zza());
            }
            zzqlVarZza.zza(arrayList);
        }
        List list2 = this.zzi;
        if (!list2.isEmpty()) {
            ArrayList arrayList2 = new ArrayList();
            Iterator it2 = list2.iterator();
            while (it2.hasNext()) {
                arrayList2.add(((zzab) it2.next()).zza());
            }
            zzqlVarZza.zzd(arrayList2);
        }
        List list3 = this.zzj;
        if (!list3.isEmpty()) {
            ArrayList arrayList3 = new ArrayList();
            Iterator it3 = list3.iterator();
            while (it3.hasNext()) {
                arrayList3.add(((zzbz) it3.next()).zza());
            }
            zzqlVarZza.zzb(arrayList3);
        }
        if (this.zzr != null) {
            ArrayList arrayList4 = new ArrayList();
            arrayList4.add(this.zzr.zza());
            zzqlVarZza.zzc(arrayList4);
        }
        Map map = this.zzk;
        if (!map.isEmpty()) {
            ArrayList arrayList5 = new ArrayList();
            Iterator it4 = map.values().iterator();
            while (it4.hasNext()) {
                arrayList5.add(((zzad) it4.next()).zza());
            }
            zzqlVarZza.zze(arrayList5);
        }
        zzqlVarZza.zzg(this.zzy);
        zzqdVarZzc.zzm((zzqm) zzqlVarZza.zzr());
        this.zzl.zzf((zzqe) zzqdVarZzc.zzr(), 233);
    }

    final void zzg(CastSession castSession) {
        if (castSession == null) {
            zzj(2);
            return;
        }
        CastDevice castDevice = castSession.getCastDevice();
        if (castDevice == null) {
            zzj(3);
            return;
        }
        this.zza = castSession;
        String str = this.zzq;
        if (str != null) {
            if (TextUtils.equals(str, castDevice.zzd())) {
                return;
            }
            zzj(5);
            return;
        }
        this.zzq = castDevice.zzd();
        this.zzs = castDevice.getModelName();
        this.zzb = castDevice.zzb();
        com.google.android.gms.cast.internal.zzaa zzaaVarZzc = castDevice.zzc();
        if (zzaaVarZzc != null) {
            this.zzt = zzaaVarZzc.zzc();
            this.zzu = zzaaVarZzc.zzd();
            this.zzv = zzaaVarZzc.zza();
            this.zzw = zzaaVarZzc.zzb();
            this.zzx = zzaaVarZzc.zze();
        }
        castSession.zzm();
    }

    final void zzh(String str) {
        String str2 = this.zzp;
        if (str2 == null) {
            this.zzp = str;
        } else {
            if (TextUtils.equals(str, str2)) {
                return;
            }
            zzj(4);
        }
    }

    final void zzi(zzr zzrVar) {
        zzr zzrVar2 = this.zzr;
        if (zzrVar2 == null || !zzrVar2.zzc()) {
            zzrVar.zzb(this.zzn);
            this.zzr = zzrVar;
        }
    }

    public final void zzj(int i) {
        Map map = this.zzk;
        Integer numValueOf = Integer.valueOf(i - 1);
        zzad zzadVar = (zzad) map.get(numValueOf);
        if (zzadVar != null) {
            zzadVar.zzb();
            return;
        }
        zzad zzadVar2 = new zzad(new zzac(i));
        zzadVar2.zzc(this.zzn);
        map.put(numValueOf, zzadVar2);
    }
}
