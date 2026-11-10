package com.google.android.gms.internal.measurement;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class zzfw extends zzjx<zzfw, zzfv> implements zzld {
    public static final zzfw zza;
    public zzke<zzfy> zze = zzjx.zzbA();

    static {
        zzfw zzfwVar = new zzfw();
        zza = zzfwVar;
        zzjx.zza.put(zzfw.class, zzfwVar);
    }

    public static zzfv zza() {
        return zza.zzbu();
    }

    public static /* synthetic */ void zze(zzfw zzfwVar, zzfy zzfyVar) {
        zzfyVar.getClass();
        zzke<zzfy> zzkeVar = zzfwVar.zze;
        if (!zzkeVar.zzc()) {
            zzfwVar.zze = zzjx.zzbB(zzkeVar);
        }
        zzfwVar.zze.add(zzfyVar);
    }

    public final zzfy zzc(int i) {
        return this.zze.get(0);
    }

    public final List<zzfy> zzd() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.measurement.zzjx
    public final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzjx.zzbF(zza, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zze", zzfy.class});
        }
        if (i2 == 3) {
            return new zzfw();
        }
        zzff zzffVar = null;
        if (i2 == 4) {
            return new zzfv(zzffVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zza;
    }
}
