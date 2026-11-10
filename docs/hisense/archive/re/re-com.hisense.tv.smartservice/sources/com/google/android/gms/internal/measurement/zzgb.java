package com.google.android.gms.internal.measurement;

/* JADX INFO: loaded from: classes.dex */
public final class zzgb extends zzjx<zzgb, zzfz> implements zzld {
    public static final zzgb zza;
    public int zze;
    public int zzf = 1;
    public zzke<zzfq> zzg = zzjx.zzbA();

    static {
        zzgb zzgbVar = new zzgb();
        zza = zzgbVar;
        zzjx.zza.put(zzgb.class, zzgbVar);
    }

    public static zzfz zza() {
        return zza.zzbu();
    }

    public static /* synthetic */ void zzc(zzgb zzgbVar, zzfq zzfqVar) {
        zzfqVar.getClass();
        zzke<zzfq> zzkeVar = zzgbVar.zzg;
        if (!zzkeVar.zzc()) {
            zzgbVar.zzg = zzjx.zzbB(zzkeVar);
        }
        zzgbVar.zzg.add(zzfqVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzjx
    public final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzjx.zzbF(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001ဌ\u0000\u0002\u001b", new Object[]{"zze", "zzf", zzga.zza, "zzg", zzfq.class});
        }
        if (i2 == 3) {
            return new zzgb();
        }
        zzff zzffVar = null;
        if (i2 == 4) {
            return new zzfz(zzffVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zza;
    }
}
