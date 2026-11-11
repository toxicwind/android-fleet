package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzso extends zzxi implements zzym {
    private static final zzso zzb;
    private int zzd;
    private int zze;
    private String zzf = "";

    static {
        zzso zzsoVar = new zzso();
        zzb = zzsoVar;
        zzxi.zzJ(zzso.class, zzsoVar);
    }

    private zzso() {
    }

    @Override // com.google.android.gms.internal.cast.zzxi
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzG(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001င\u0000\u0002ဈ\u0001", new Object[]{"zzd", "zze", "zzf"});
        }
        if (i2 == 3) {
            return new zzso();
        }
        zzuw zzuwVar = null;
        if (i2 == 4) {
            return new zzsn(zzuwVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
