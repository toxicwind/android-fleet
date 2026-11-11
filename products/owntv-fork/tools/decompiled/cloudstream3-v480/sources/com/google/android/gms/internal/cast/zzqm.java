package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzqm extends zzxi implements zzym {
    private static final zzqm zzb;
    private int zzd;
    private zzrc zze;
    private long zzf;
    private int zzg;
    private zzxn zzh = zzD();
    private zzxn zzi = zzD();
    private zzxn zzj = zzD();
    private zzxn zzk = zzD();
    private zzxn zzl = zzD();
    private int zzm;

    static {
        zzqm zzqmVar = new zzqm();
        zzb = zzqmVar;
        zzxi.zzJ(zzqm.class, zzqmVar);
    }

    private zzqm() {
    }

    public static zzql zza() {
        return (zzql) zzb.zzy();
    }

    static /* synthetic */ void zzd(zzqm zzqmVar, Iterable iterable) {
        zzxn zzxnVar = zzqmVar.zzh;
        if (!zzxnVar.zzc()) {
            zzqmVar.zzh = zzxi.zzE(zzxnVar);
        }
        zzwa.zzt(iterable, zzqmVar.zzh);
    }

    static /* synthetic */ void zze(zzqm zzqmVar, Iterable iterable) {
        zzxn zzxnVar = zzqmVar.zzi;
        if (!zzxnVar.zzc()) {
            zzqmVar.zzi = zzxi.zzE(zzxnVar);
        }
        zzwa.zzt(iterable, zzqmVar.zzi);
    }

    static /* synthetic */ void zzf(zzqm zzqmVar, Iterable iterable) {
        zzxn zzxnVar = zzqmVar.zzl;
        if (!zzxnVar.zzc()) {
            zzqmVar.zzl = zzxi.zzE(zzxnVar);
        }
        zzwa.zzt(iterable, zzqmVar.zzl);
    }

    static /* synthetic */ void zzg(zzqm zzqmVar, Iterable iterable) {
        zzxn zzxnVar = zzqmVar.zzj;
        if (!zzxnVar.zzc()) {
            zzqmVar.zzj = zzxi.zzE(zzxnVar);
        }
        zzwa.zzt(iterable, zzqmVar.zzj);
    }

    static /* synthetic */ void zzh(zzqm zzqmVar, Iterable iterable) {
        zzxn zzxnVar = zzqmVar.zzk;
        if (!zzxnVar.zzc()) {
            zzqmVar.zzk = zzxi.zzE(zzxnVar);
        }
        zzwa.zzt(iterable, zzqmVar.zzk);
    }

    static /* synthetic */ void zzi(zzqm zzqmVar, zzrc zzrcVar) {
        zzrcVar.getClass();
        zzqmVar.zze = zzrcVar;
        zzqmVar.zzd |= 1;
    }

    static /* synthetic */ void zzj(zzqm zzqmVar, int i) {
        zzqmVar.zzd |= 8;
        zzqmVar.zzm = i;
    }

    static /* synthetic */ void zzk(zzqm zzqmVar, long j) {
        zzqmVar.zzd |= 2;
        zzqmVar.zzf = j;
    }

    @Override // com.google.android.gms.internal.cast.zzxi
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzG(zzb, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0005\u0000\u0001ဉ\u0000\u0002စ\u0001\u0003᠌\u0002\u0004\u001b\u0005\u001b\u0006\u001b\u0007\u001b\b\u001b\tင\u0003", new Object[]{"zzd", "zze", "zzf", "zzg", zzon.zza(), "zzh", zzqk.class, "zzi", zzqg.class, "zzj", zzqq.class, "zzk", zzqo.class, "zzl", zzqi.class, "zzm"});
        }
        if (i2 == 3) {
            return new zzqm();
        }
        zzuw zzuwVar = null;
        if (i2 == 4) {
            return new zzql(zzuwVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
