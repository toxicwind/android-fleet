package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzph extends zzxi implements zzym {
    private static final zzph zzb;
    private int zzd;
    private String zze = "";
    private String zzf = "";
    private zzxl zzg = zzB();

    static {
        zzph zzphVar = new zzph();
        zzb = zzphVar;
        zzxi.zzJ(zzph.class, zzphVar);
    }

    private zzph() {
    }

    @Override // com.google.android.gms.internal.cast.zzxi
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzG(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ࠞ", new Object[]{"zzd", "zze", "zzf", "zzg", zzpb.zzb()});
        }
        if (i2 == 3) {
            return new zzph();
        }
        zzuw zzuwVar = null;
        if (i2 == 4) {
            return new zzpg(zzuwVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
