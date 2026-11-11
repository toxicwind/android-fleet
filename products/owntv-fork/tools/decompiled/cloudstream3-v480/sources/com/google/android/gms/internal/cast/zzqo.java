package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzqo extends zzxi implements zzym {
    private static final zzqo zzb;
    private int zzd;
    private int zze;
    private int zzf;
    private int zzg;
    private int zzh;

    static {
        zzqo zzqoVar = new zzqo();
        zzb = zzqoVar;
        zzxi.zzJ(zzqo.class, zzqoVar);
    }

    private zzqo() {
    }

    public static zzqn zza() {
        return (zzqn) zzb.zzy();
    }

    static /* synthetic */ void zzd(zzqo zzqoVar, int i) {
        zzqoVar.zzd |= 2;
        zzqoVar.zzf = i;
    }

    static /* synthetic */ void zze(zzqo zzqoVar, int i) {
        zzqoVar.zzd |= 4;
        zzqoVar.zzg = i;
    }

    static /* synthetic */ void zzf(zzqo zzqoVar, int i) {
        zzqoVar.zzd |= 8;
        zzqoVar.zzh = i;
    }

    static /* synthetic */ void zzg(zzqo zzqoVar, int i) {
        zzqoVar.zze = i - 1;
        zzqoVar.zzd |= 1;
    }

    @Override // com.google.android.gms.internal.cast.zzxi
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzG(zzb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001᠌\u0000\u0002င\u0001\u0003င\u0002\u0004င\u0003", new Object[]{"zzd", "zze", zzoj.zza(), "zzf", "zzg", "zzh"});
        }
        if (i2 == 3) {
            return new zzqo();
        }
        zzuw zzuwVar = null;
        if (i2 == 4) {
            return new zzqn(zzuwVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
