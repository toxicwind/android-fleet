package com.google.android.gms.internal.measurement;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class zzex extends zzjx<zzex, zzet> implements zzld {
    public static final zzex zza;
    public int zze;
    public int zzf;
    public boolean zzh;
    public String zzg = "";
    public zzke<String> zzi = zzjx.zzbA();

    static {
        zzex zzexVar = new zzex();
        zza = zzexVar;
        zzjx.zza.put(zzex.class, zzexVar);
    }

    public static zzex zzc() {
        return zza;
    }

    public final int zza() {
        return this.zzi.size();
    }

    public final String zzd() {
        return this.zzg;
    }

    public final List<String> zze() {
        return this.zzi;
    }

    public final boolean zzf() {
        return this.zzh;
    }

    public final boolean zzg() {
        return (this.zze & 4) != 0;
    }

    public final boolean zzh() {
        return (this.zze & 2) != 0;
    }

    public final boolean zzi() {
        return (this.zze & 1) != 0;
    }

    public final int zzj() {
        int iZza = zzew.zza(this.zzf);
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
            return zzjx.zzbF(zza, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001ဌ\u0000\u0002ဈ\u0001\u0003ဇ\u0002\u0004\u001a", new Object[]{"zze", "zzf", zzev.zza, "zzg", "zzh", "zzi"});
        }
        if (i2 == 3) {
            return new zzex();
        }
        if (i2 == 4) {
            return new zzet(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zza;
    }
}
