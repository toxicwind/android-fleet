package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzuj extends zzxi implements zzym {
    private static final zzuj zzb;
    private int zzd;
    private int zze;
    private int zzf;
    private zzpl zzg;

    static {
        zzuj zzujVar = new zzuj();
        zzb = zzujVar;
        zzxi.zzJ(zzuj.class, zzujVar);
    }

    private zzuj() {
    }

    @Override // com.google.android.gms.internal.cast.zzxi
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzG(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001᠌\u0000\u0002ဋ\u0001\u0003ဉ\u0002", new Object[]{"zzd", "zze", zzob.zza(), "zzf", "zzg"});
        }
        if (i2 == 3) {
            return new zzuj();
        }
        zzuw zzuwVar = null;
        if (i2 == 4) {
            return new zzui(zzuwVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
