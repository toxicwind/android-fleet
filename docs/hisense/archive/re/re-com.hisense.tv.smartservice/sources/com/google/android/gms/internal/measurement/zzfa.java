package com.google.android.gms.internal.measurement;

/* JADX INFO: loaded from: classes.dex */
public final class zzfa extends zzjx<zzfa, zzez> implements zzld {
    public static final zzfa zza;
    public int zze;
    public String zzf = "";
    public boolean zzg;
    public boolean zzh;
    public int zzi;

    static {
        zzfa zzfaVar = new zzfa();
        zza = zzfaVar;
        zzjx.zza.put(zzfa.class, zzfaVar);
    }

    public static /* synthetic */ void zzd(zzfa zzfaVar, String str) {
        str.getClass();
        zzfaVar.zze |= 1;
        zzfaVar.zzf = str;
    }

    public final int zza() {
        return this.zzi;
    }

    public final String zzc() {
        return this.zzf;
    }

    public final boolean zze() {
        return this.zzg;
    }

    public final boolean zzf() {
        return this.zzh;
    }

    public final boolean zzg() {
        return (this.zze & 2) != 0;
    }

    public final boolean zzh() {
        return (this.zze & 4) != 0;
    }

    public final boolean zzi() {
        return (this.zze & 8) != 0;
    }

    @Override // com.google.android.gms.internal.measurement.zzjx
    public final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzjx.zzbF(zza, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဇ\u0001\u0003ဇ\u0002\u0004င\u0003", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi"});
        }
        if (i2 == 3) {
            return new zzfa();
        }
        zzey zzeyVar = null;
        if (i2 == 4) {
            return new zzez(zzeyVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zza;
    }
}
