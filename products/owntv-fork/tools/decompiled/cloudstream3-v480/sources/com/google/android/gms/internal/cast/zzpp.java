package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzpp extends zzxi implements zzym {
    private static final zzpp zzb;
    private int zzd;
    private String zze = "";
    private String zzf = "";

    static {
        zzpp zzppVar = new zzpp();
        zzb = zzppVar;
        zzxi.zzJ(zzpp.class, zzppVar);
    }

    private zzpp() {
    }

    public static zzpo zza() {
        return (zzpo) zzb.zzy();
    }

    static /* synthetic */ void zzd(zzpp zzppVar, String str) {
        str.getClass();
        zzppVar.zzd |= 1;
        zzppVar.zze = str;
    }

    static /* synthetic */ void zze(zzpp zzppVar, String str) {
        str.getClass();
        zzppVar.zzd |= 2;
        zzppVar.zzf = str;
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
            return new zzpp();
        }
        zzuw zzuwVar = null;
        if (i2 == 4) {
            return new zzpo(zzuwVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
