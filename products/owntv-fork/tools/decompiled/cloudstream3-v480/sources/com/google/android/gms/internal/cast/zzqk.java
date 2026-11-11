package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzqk extends zzxi implements zzym {
    private static final zzqk zzb;
    private int zzd;
    private int zze;
    private int zzf;
    private int zzg;
    private boolean zzh;
    private long zzi;

    static {
        zzqk zzqkVar = new zzqk();
        zzb = zzqkVar;
        zzxi.zzJ(zzqk.class, zzqkVar);
    }

    private zzqk() {
    }

    public static zzqj zza() {
        return (zzqj) zzb.zzy();
    }

    static /* synthetic */ void zzd(zzqk zzqkVar, boolean z) {
        zzqkVar.zzd |= 8;
        zzqkVar.zzh = z;
    }

    static /* synthetic */ void zze(zzqk zzqkVar, int i) {
        zzqkVar.zzd |= 4;
        zzqkVar.zzg = i;
    }

    static /* synthetic */ void zzf(zzqk zzqkVar, long j) {
        zzqkVar.zzd |= 16;
        zzqkVar.zzi = j;
    }

    static /* synthetic */ void zzg(zzqk zzqkVar, int i) {
        zzqkVar.zzd |= 2;
        zzqkVar.zzf = i;
    }

    static /* synthetic */ void zzh(zzqk zzqkVar, int i) {
        zzqkVar.zze = i - 1;
        zzqkVar.zzd |= 1;
    }

    @Override // com.google.android.gms.internal.cast.zzxi
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzG(zzb, "\u0001\u0005\u0000\u0001\u0001\u0006\u0005\u0000\u0000\u0000\u0001᠌\u0000\u0002င\u0001\u0003င\u0002\u0004ဇ\u0003\u0006ဂ\u0004", new Object[]{"zzd", "zze", zzoh.zza(), "zzf", "zzg", "zzh", "zzi"});
        }
        if (i2 == 3) {
            return new zzqk();
        }
        zzuw zzuwVar = null;
        if (i2 == 4) {
            return new zzqj(zzuwVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
