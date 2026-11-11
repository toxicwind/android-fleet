package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzup extends zzxi implements zzym {
    private static final zzup zzb;
    private int zzd;
    private int zze;
    private long zzf;
    private long zzg;

    static {
        zzup zzupVar = new zzup();
        zzb = zzupVar;
        zzxi.zzJ(zzup.class, zzupVar);
    }

    private zzup() {
    }

    @Override // com.google.android.gms.internal.cast.zzxi
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzG(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001᠌\u0000\u0002ဂ\u0001\u0003ဂ\u0002", new Object[]{"zzd", "zze", zzoz.zza(), "zzf", "zzg"});
        }
        if (i2 == 3) {
            return new zzup();
        }
        zzuw zzuwVar = null;
        if (i2 == 4) {
            return new zzuo(zzuwVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
