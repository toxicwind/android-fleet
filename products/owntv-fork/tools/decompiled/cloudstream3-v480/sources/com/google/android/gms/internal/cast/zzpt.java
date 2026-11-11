package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzpt extends zzxi implements zzym {
    private static final zzpt zzb;
    private int zzd;
    private zzrc zze;
    private boolean zzf;
    private long zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private int zzk;
    private int zzl;
    private zztl zzm;
    private int zzn;
    private int zzo;
    private boolean zzp;
    private int zzq;
    private int zzr;
    private boolean zzs;

    static {
        zzpt zzptVar = new zzpt();
        zzb = zzptVar;
        zzxi.zzJ(zzpt.class, zzptVar);
    }

    private zzpt() {
    }

    public static zzps zza() {
        return (zzps) zzb.zzy();
    }

    public static zzps zzc(zzpt zzptVar) {
        zzxf zzxfVarZzy = zzb.zzy();
        zzxfVarZzy.zzq(zzptVar);
        return (zzps) zzxfVarZzy;
    }

    public static zzpt zze() {
        return zzb;
    }

    static /* synthetic */ void zzf(zzpt zzptVar, zzrc zzrcVar) {
        zzrcVar.getClass();
        zzptVar.zze = zzrcVar;
        zzptVar.zzd |= 1;
    }

    static /* synthetic */ void zzg(zzpt zzptVar, int i) {
        zzptVar.zzd |= 1024;
        zzptVar.zzo = i;
    }

    static /* synthetic */ void zzh(zzpt zzptVar, int i) {
        zzptVar.zzd |= 128;
        zzptVar.zzl = i;
    }

    static /* synthetic */ void zzi(zzpt zzptVar, boolean z) {
        zzptVar.zzd |= 2048;
        zzptVar.zzp = z;
    }

    static /* synthetic */ void zzj(zzpt zzptVar, boolean z) {
        zzptVar.zzd |= 16384;
        zzptVar.zzs = z;
    }

    static /* synthetic */ void zzk(zzpt zzptVar, boolean z) {
        zzptVar.zzd |= 2;
        zzptVar.zzf = z;
    }

    static /* synthetic */ void zzl(zzpt zzptVar, int i) {
        zzptVar.zzd |= 64;
        zzptVar.zzk = i;
    }

    static /* synthetic */ void zzm(zzpt zzptVar, long j) {
        zzptVar.zzd |= 4;
        zzptVar.zzg = j;
    }

    static /* synthetic */ void zzn(zzpt zzptVar, int i) {
        zzptVar.zzd |= 8192;
        zzptVar.zzr = i;
    }

    static /* synthetic */ void zzo(zzpt zzptVar, int i) {
        zzptVar.zzd |= 4096;
        zzptVar.zzq = i;
    }

    @Override // com.google.android.gms.internal.cast.zzxi
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzG(zzb, "\u0001\u000f\u0000\u0001\u0001\u000f\u000f\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဇ\u0001\u0003စ\u0002\u0004ဆ\u0003\u0005᠌\u0004\u0006᠌\u0005\u0007င\u0006\bင\u0007\tဉ\b\n᠌\t\u000bင\n\fဇ\u000b\rင\f\u000eင\r\u000fဇ\u000e", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi", zzkx.zza(), "zzj", zzkv.zza(), "zzk", "zzl", "zzm", "zzn", zzlz.zza(), "zzo", "zzp", "zzq", "zzr", "zzs"});
        }
        if (i2 == 3) {
            return new zzpt();
        }
        zzuw zzuwVar = null;
        if (i2 == 4) {
            return new zzps(zzuwVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
