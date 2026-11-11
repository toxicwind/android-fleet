package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzrc extends zzxi implements zzym {
    private static final zzrc zzb;
    private int zzd;
    private String zze = "";
    private String zzf = "";

    static {
        zzrc zzrcVar = new zzrc();
        zzb = zzrcVar;
        zzxi.zzJ(zzrc.class, zzrcVar);
    }

    private zzrc() {
    }

    public static zzrb zza() {
        return (zzrb) zzb.zzy();
    }

    static /* synthetic */ void zzd(zzrc zzrcVar, String str) {
        str.getClass();
        zzrcVar.zzd |= 1;
        zzrcVar.zze = str;
    }

    @Override // com.google.android.gms.internal.cast.zzxi
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzG(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001", new Object[]{"zzd", "zze", "zzf"});
        }
        if (i2 == 3) {
            return new zzrc();
        }
        zzuw zzuwVar = null;
        if (i2 == 4) {
            return new zzrb(zzuwVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
