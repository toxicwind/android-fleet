package com.google.android.gms.internal.measurement;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class zzgt extends zzjx<zzgt, zzgp> implements zzld {
    public static final zzgt zza;
    public int zze;
    public int zzf;
    public zzke<zzgt> zzg = zzjx.zzbA();
    public String zzh = "";
    public String zzi = "";
    public boolean zzj;
    public double zzk;

    static {
        zzgt zzgtVar = new zzgt();
        zza = zzgtVar;
        zzjx.zza.put(zzgt.class, zzgtVar);
    }

    public final double zza() {
        return this.zzk;
    }

    public final String zzc() {
        return this.zzh;
    }

    public final String zzd() {
        return this.zzi;
    }

    public final List<zzgt> zze() {
        return this.zzg;
    }

    public final boolean zzf() {
        return this.zzj;
    }

    public final boolean zzg() {
        return (this.zze & 8) != 0;
    }

    public final boolean zzh() {
        return (this.zze & 16) != 0;
    }

    public final boolean zzi() {
        return (this.zze & 4) != 0;
    }

    public final int zzj() {
        int iZza = zzgs.zza(this.zzf);
        if (iZza == 0) {
            return 1;
        }
        return iZza;
    }

    @Override // com.google.android.gms.internal.measurement.zzjx
    public final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzjx.zzbF(zza, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0001\u0000\u0001ဌ\u0000\u0002\u001b\u0003ဈ\u0001\u0004ဈ\u0002\u0005ဇ\u0003\u0006က\u0004", new Object[]{"zze", "zzf", zzgr.zza, "zzg", zzgt.class, "zzh", "zzi", "zzj", "zzk"});
        }
        if (i2 == 3) {
            return new zzgt();
        }
        zzgi zzgiVar = null;
        if (i2 == 4) {
            return new zzgp(zzgiVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zza;
    }
}
