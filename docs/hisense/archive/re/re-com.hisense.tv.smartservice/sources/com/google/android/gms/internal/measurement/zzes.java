package com.google.android.gms.internal.measurement;

/* JADX INFO: loaded from: classes.dex */
public final class zzes extends zzjx<zzes, zzer> implements zzld {
    public static final zzes zza;
    public int zze;
    public int zzf;
    public String zzg = "";
    public zzel zzh;
    public boolean zzi;
    public boolean zzj;
    public boolean zzk;

    static {
        zzes zzesVar = new zzes();
        zza = zzesVar;
        zzjx.zza.put(zzes.class, zzesVar);
    }

    public static zzer zzc() {
        return zza.zzbu();
    }

    public static /* synthetic */ void zzf(zzes zzesVar, String str) {
        zzesVar.zze |= 2;
        zzesVar.zzg = str;
    }

    public final int zza() {
        return this.zzf;
    }

    public final zzel zzb() {
        zzel zzelVar = this.zzh;
        return zzelVar == null ? zzel.zza : zzelVar;
    }

    public final String zze() {
        return this.zzg;
    }

    public final boolean zzg() {
        return this.zzi;
    }

    public final boolean zzh() {
        return this.zzj;
    }

    public final boolean zzi() {
        return this.zzk;
    }

    public final boolean zzj() {
        return (this.zze & 1) != 0;
    }

    public final boolean zzk() {
        return (this.zze & 32) != 0;
    }

    @Override // com.google.android.gms.internal.measurement.zzjx
    public final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzjx.zzbF(zza, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001င\u0000\u0002ဈ\u0001\u0003ဉ\u0002\u0004ဇ\u0003\u0005ဇ\u0004\u0006ဇ\u0005", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk"});
        }
        if (i2 == 3) {
            return new zzes();
        }
        zzef zzefVar = null;
        if (i2 == 4) {
            return new zzer(zzefVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zza;
    }
}
