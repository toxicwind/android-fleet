package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzqg extends zzxi implements zzym {
    private static final zzqg zzb;
    private int zzd;
    private int zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;

    static {
        zzqg zzqgVar = new zzqg();
        zzb = zzqgVar;
        zzxi.zzJ(zzqg.class, zzqgVar);
    }

    private zzqg() {
    }

    public static zzqf zza() {
        return (zzqf) zzb.zzy();
    }

    static /* synthetic */ void zzd(zzqg zzqgVar, int i) {
        zzqgVar.zzd |= 16;
        zzqgVar.zzi = i;
    }

    static /* synthetic */ void zze(zzqg zzqgVar, int i) {
        zzqgVar.zzd |= 2;
        zzqgVar.zzf = i;
    }

    static /* synthetic */ void zzf(zzqg zzqgVar, int i) {
        zzqgVar.zzd |= 8;
        zzqgVar.zzh = i;
    }

    static /* synthetic */ void zzg(zzqg zzqgVar, int i) {
        zzqgVar.zzd |= 4;
        zzqgVar.zzg = i;
    }

    static /* synthetic */ void zzh(zzqg zzqgVar, int i) {
        zzqgVar.zze = i - 1;
        zzqgVar.zzd |= 1;
    }

    @Override // com.google.android.gms.internal.cast.zzxi
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzG(zzb, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001᠌\u0000\u0002င\u0001\u0003င\u0002\u0004င\u0003\u0005င\u0004", new Object[]{"zzd", "zze", zzod.zza(), "zzf", "zzg", "zzh", "zzi"});
        }
        if (i2 == 3) {
            return new zzqg();
        }
        zzuw zzuwVar = null;
        if (i2 == 4) {
            return new zzqf(zzuwVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
