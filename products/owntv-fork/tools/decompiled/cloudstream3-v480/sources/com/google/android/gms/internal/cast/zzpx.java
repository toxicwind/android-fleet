package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzpx extends zzxi implements zzym {
    private static final zzpx zzb;
    private zzxn zzd = zzxi.zzD();

    static {
        zzpx zzpxVar = new zzpx();
        zzb = zzpxVar;
        zzxi.zzJ(zzpx.class, zzpxVar);
    }

    private zzpx() {
    }

    @Override // com.google.android.gms.internal.cast.zzxi
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzG(zzb, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001a", new Object[]{"zzd"});
        }
        if (i2 == 3) {
            return new zzpx();
        }
        zzuw zzuwVar = null;
        if (i2 == 4) {
            return new zzpw(zzuwVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
