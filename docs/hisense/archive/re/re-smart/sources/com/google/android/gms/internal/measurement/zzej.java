package com.google.android.gms.internal.measurement;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class zzej extends zzjx<zzej, zzei> implements zzld {
    public static final zzej zza;
    public int zze;
    public int zzf;
    public String zzg = "";
    public zzke<zzel> zzh = zzjx.zzbA();
    public boolean zzi;
    public zzeq zzj;
    public boolean zzk;
    public boolean zzl;
    public boolean zzm;

    static {
        zzej zzejVar = new zzej();
        zza = zzejVar;
        zzjx.zza.put(zzej.class, zzejVar);
    }

    public static zzei zzc() {
        return zza.zzbu();
    }

    public static /* synthetic */ void zzi(zzej zzejVar, String str) {
        zzejVar.zze |= 2;
        zzejVar.zzg = str;
    }

    public static /* synthetic */ void zzj(zzej zzejVar, int i, zzel zzelVar) {
        zzelVar.getClass();
        zzke<zzel> zzkeVar = zzejVar.zzh;
        if (!zzkeVar.zzc()) {
            zzejVar.zzh = zzjx.zzbB(zzkeVar);
        }
        zzejVar.zzh.set(i, zzelVar);
    }

    public final int zza() {
        return this.zzh.size();
    }

    public final int zzb() {
        return this.zzf;
    }

    public final zzel zze(int i) {
        return this.zzh.get(i);
    }

    public final zzeq zzf() {
        zzeq zzeqVar = this.zzj;
        return zzeqVar == null ? zzeq.zza : zzeqVar;
    }

    public final String zzg() {
        return this.zzg;
    }

    public final List<zzel> zzh() {
        return this.zzh;
    }

    public final boolean zzk() {
        return this.zzk;
    }

    @Override // com.google.android.gms.internal.measurement.zzjx
    public final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzjx.zzbF(zza, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0001\u0000\u0001င\u0000\u0002ဈ\u0001\u0003\u001b\u0004ဇ\u0002\u0005ဉ\u0003\u0006ဇ\u0004\u0007ဇ\u0005\bဇ\u0006", new Object[]{"zze", "zzf", "zzg", "zzh", zzel.class, "zzi", "zzj", "zzk", "zzl", "zzm"});
        }
        if (i2 == 3) {
            return new zzej();
        }
        zzef zzefVar = null;
        if (i2 == 4) {
            return new zzei(zzefVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zza;
    }

    public final boolean zzm() {
        return this.zzl;
    }

    public final boolean zzn() {
        return this.zzm;
    }

    public final boolean zzo() {
        return (this.zze & 8) != 0;
    }

    public final boolean zzp() {
        return (this.zze & 1) != 0;
    }

    public final boolean zzq() {
        return (this.zze & 64) != 0;
    }
}
