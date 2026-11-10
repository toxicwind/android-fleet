package com.google.android.gms.internal.measurement;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class zzeh extends zzjx<zzeh, zzeg> implements zzld {
    public static final zzeh zza;
    public int zze;
    public int zzf;
    public zzke<zzes> zzg = zzjx.zzbA();
    public zzke<zzej> zzh = zzjx.zzbA();
    public boolean zzi;
    public boolean zzj;

    static {
        zzeh zzehVar = new zzeh();
        zza = zzehVar;
        zzjx.zza.put(zzeh.class, zzehVar);
    }

    public static /* synthetic */ void zzi(zzeh zzehVar, int i, zzes zzesVar) {
        zzesVar.getClass();
        zzke<zzes> zzkeVar = zzehVar.zzg;
        if (!zzkeVar.zzc()) {
            zzehVar.zzg = zzjx.zzbB(zzkeVar);
        }
        zzehVar.zzg.set(i, zzesVar);
    }

    public static /* synthetic */ void zzj(zzeh zzehVar, int i, zzej zzejVar) {
        zzejVar.getClass();
        zzke<zzej> zzkeVar = zzehVar.zzh;
        if (!zzkeVar.zzc()) {
            zzehVar.zzh = zzjx.zzbB(zzkeVar);
        }
        zzehVar.zzh.set(i, zzejVar);
    }

    public final int zza() {
        return this.zzf;
    }

    public final int zzb() {
        return this.zzh.size();
    }

    public final int zzc() {
        return this.zzg.size();
    }

    public final zzej zze(int i) {
        return this.zzh.get(i);
    }

    public final zzes zzf(int i) {
        return this.zzg.get(i);
    }

    public final List<zzej> zzg() {
        return this.zzh;
    }

    public final List<zzes> zzh() {
        return this.zzg;
    }

    public final boolean zzk() {
        return (this.zze & 1) != 0;
    }

    @Override // com.google.android.gms.internal.measurement.zzjx
    public final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzjx.zzbF(zza, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0002\u0000\u0001င\u0000\u0002\u001b\u0003\u001b\u0004ဇ\u0001\u0005ဇ\u0002", new Object[]{"zze", "zzf", "zzg", zzes.class, "zzh", zzej.class, "zzi", "zzj"});
        }
        if (i2 == 3) {
            return new zzeh();
        }
        zzef zzefVar = null;
        if (i2 == 4) {
            return new zzeg(zzefVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zza;
    }
}
