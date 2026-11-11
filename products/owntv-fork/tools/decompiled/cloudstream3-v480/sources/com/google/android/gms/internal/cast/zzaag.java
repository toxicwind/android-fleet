package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzaag extends zzxi implements zzym {
    private static final zzaag zzb;
    private zzxn zzd = zzD();
    private zzxn zze = zzD();
    private zzxn zzf = zzD();
    private zzxn zzg = zzD();

    static {
        zzaag zzaagVar = new zzaag();
        zzb = zzaagVar;
        zzxi.zzJ(zzaag.class, zzaagVar);
    }

    private zzaag() {
    }

    @Override // com.google.android.gms.internal.cast.zzxi
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzG(zzb, "\u0001\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0004\u0000\u0001\u001b\u0002\u001b\u0003\u001b\u0004\u001b", new Object[]{"zzd", zzaad.class, "zze", zzzy.class, "zzf", zzaad.class, "zzg", zzzy.class});
        }
        if (i2 == 3) {
            return new zzaag();
        }
        zzaaf zzaafVar = null;
        if (i2 == 4) {
            return new zzaae(zzaafVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
