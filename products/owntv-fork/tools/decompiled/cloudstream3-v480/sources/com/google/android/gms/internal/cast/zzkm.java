package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzkm extends zzxi implements zzym {
    private static final zzkm zzb;
    private int zzd;
    private int zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private int zzk;
    private int zzl;
    private int zzm;
    private int zzn;
    private int zzo;
    private boolean zzp;

    static {
        zzkm zzkmVar = new zzkm();
        zzb = zzkmVar;
        zzxi.zzJ(zzkm.class, zzkmVar);
    }

    private zzkm() {
    }

    @Override // com.google.android.gms.internal.cast.zzxi
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzG(zzb, "\u0001\f\u0000\u0001\u0001\f\f\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001\u0003᠌\u0002\u0004᠌\u0003\u0005᠌\u0004\u0006᠌\u0005\u0007᠌\u0006\b᠌\u0007\tင\b\nင\t\u000bင\n\fဇ\u000b", new Object[]{"zzd", "zze", "zzf", "zzg", zzki.zza, "zzh", zzkj.zza, "zzi", zzkg.zza, "zzj", zzkk.zza, "zzk", zzkl.zza, "zzl", zzkf.zza, "zzm", "zzn", "zzo", "zzp"});
        }
        if (i2 == 3) {
            return new zzkm();
        }
        zzkr zzkrVar = null;
        if (i2 == 4) {
            return new zzkh(zzkrVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
