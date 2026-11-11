package com.google.android.gms.internal.cast;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzyp implements zzyv {
    private final zzyl zza;
    private final zzzh zzb;
    private final boolean zzc;
    private final zzwx zzd;

    private zzyp(zzzh zzzhVar, zzwx zzwxVar, zzyl zzylVar) {
        this.zzb = zzzhVar;
        this.zzc = zzylVar instanceof zzxg;
        this.zzd = zzwxVar;
        this.zza = zzylVar;
    }

    static zzyp zzi(zzzh zzzhVar, zzwx zzwxVar, zzyl zzylVar) {
        return new zzyp(zzzhVar, zzwxVar, zzylVar);
    }

    @Override // com.google.android.gms.internal.cast.zzyv
    public final int zza(Object obj) {
        int iZzb = ((zzxi) obj).zzc.zzb();
        return this.zzc ? iZzb + ((zzxg) obj).zzb.zzc() : iZzb;
    }

    @Override // com.google.android.gms.internal.cast.zzyv
    public final int zzb(Object obj) {
        int iHashCode = ((zzxi) obj).zzc.hashCode();
        return this.zzc ? (iHashCode * 53) + ((zzxg) obj).zzb.zza.hashCode() : iHashCode;
    }

    @Override // com.google.android.gms.internal.cast.zzyv
    public final Object zzc() {
        zzyl zzylVar = this.zza;
        return zzylVar instanceof zzxi ? ((zzxi) zzylVar).zzA() : zzylVar.zzN().zzt();
    }

    @Override // com.google.android.gms.internal.cast.zzyv
    public final void zzd(Object obj) {
        this.zzb.zza(obj);
        this.zzd.zza(obj);
    }

    @Override // com.google.android.gms.internal.cast.zzyv
    public final void zze(Object obj, Object obj2) {
        zzyx.zzo(this.zzb, obj, obj2);
        if (this.zzc) {
            zzyx.zzn(this.zzd, obj, obj2);
        }
    }

    @Override // com.google.android.gms.internal.cast.zzyv
    public final void zzf(Object obj, zzzt zzztVar) throws IOException {
        Iterator itZze = ((zzxg) obj).zzb.zze();
        while (itZze.hasNext()) {
            Map.Entry entry = (Map.Entry) itZze.next();
            zzxa zzxaVar = (zzxa) entry.getKey();
            if (zzxaVar.zzc() != zzzs.MESSAGE || zzxaVar.zze() || zzxaVar.zzd()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
            if (entry instanceof zzxs) {
                zzztVar.zzv(zzxaVar.zza(), ((zzxs) entry).zza().zzb());
            } else {
                zzztVar.zzv(zzxaVar.zza(), entry.getValue());
            }
        }
        ((zzxi) obj).zzc.zzh(zzztVar);
    }

    @Override // com.google.android.gms.internal.cast.zzyv
    public final boolean zzg(Object obj, Object obj2) {
        if (!((zzxi) obj).zzc.equals(((zzxi) obj2).zzc)) {
            return false;
        }
        if (this.zzc) {
            return ((zzxg) obj).zzb.equals(((zzxg) obj2).zzb);
        }
        return true;
    }

    @Override // com.google.android.gms.internal.cast.zzyv
    public final boolean zzh(Object obj) {
        return ((zzxg) obj).zzb.zzh();
    }
}
