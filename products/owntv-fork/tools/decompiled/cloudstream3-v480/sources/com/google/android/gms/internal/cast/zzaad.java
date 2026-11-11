package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzaad extends zzxi implements zzym {
    private static final zzaad zzb;
    private int zzd;
    private double zze;
    private int zzf;
    private int zzg;

    static {
        zzaad zzaadVar = new zzaad();
        zzb = zzaadVar;
        zzxi.zzJ(zzaad.class, zzaadVar);
    }

    private zzaad() {
    }

    @Override // com.google.android.gms.internal.cast.zzxi
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzG(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001က\u0000\u0002᠌\u0001\u0003᠌\u0002", new Object[]{"zzd", "zze", "zzf", zzaab.zza, "zzg", zzaaa.zza});
        }
        if (i2 == 3) {
            return new zzaad();
        }
        zzaac zzaacVar = null;
        if (i2 == 4) {
            return new zzzz(zzaacVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
