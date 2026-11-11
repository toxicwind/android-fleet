package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zztv extends zzxi implements zzym {
    private static final zztv zzb;
    private int zzd;
    private String zze = "";
    private String zzf = "";
    private String zzg = "";
    private String zzh = "";
    private String zzi = "";
    private String zzj = "";
    private boolean zzk;
    private int zzl;

    static {
        zztv zztvVar = new zztv();
        zzb = zztvVar;
        zzxi.zzJ(zztv.class, zztvVar);
    }

    private zztv() {
    }

    public static zztu zza() {
        return (zztu) zzb.zzy();
    }

    static /* synthetic */ void zzd(zztv zztvVar, String str) {
        str.getClass();
        zztvVar.zzd |= 8;
        zztvVar.zzh = str;
    }

    static /* synthetic */ void zze(zztv zztvVar, String str) {
        str.getClass();
        zztvVar.zzd |= 16;
        zztvVar.zzi = str;
    }

    static /* synthetic */ void zzf(zztv zztvVar, String str) {
        str.getClass();
        zztvVar.zzd |= 1;
        zztvVar.zze = str;
    }

    static /* synthetic */ void zzg(zztv zztvVar, String str) {
        str.getClass();
        zztvVar.zzd |= 2;
        zztvVar.zzf = str;
    }

    static /* synthetic */ void zzh(zztv zztvVar, String str) {
        str.getClass();
        zztvVar.zzd |= 4;
        zztvVar.zzg = str;
    }

    static /* synthetic */ void zzi(zztv zztvVar, String str) {
        str.getClass();
        zztvVar.zzd |= 32;
        zztvVar.zzj = str;
    }

    static /* synthetic */ void zzj(zztv zztvVar, int i) {
        zztvVar.zzl = i - 1;
        zztvVar.zzd |= 128;
    }

    @Override // com.google.android.gms.internal.cast.zzxi
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzG(zzb, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဈ\u0004\u0006ဈ\u0005\u0007ဇ\u0006\b᠌\u0007", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", zzld.zza()});
        }
        if (i2 == 3) {
            return new zztv();
        }
        zzuw zzuwVar = null;
        if (i2 == 4) {
            return new zztu(zzuwVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
