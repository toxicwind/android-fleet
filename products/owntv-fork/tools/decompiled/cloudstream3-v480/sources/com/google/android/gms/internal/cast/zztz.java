package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zztz extends zzxi implements zzym {
    private static final zztz zzb;
    private int zzd;
    private zzrc zzf;
    private boolean zzg;
    private boolean zzh;
    private boolean zzi;
    private boolean zzj;
    private long zzk;
    private long zzl;
    private int zzm;
    private int zzn;
    private String zze = "";
    private zzxl zzo = zzB();

    static {
        zztz zztzVar = new zztz();
        zzb = zztzVar;
        zzxi.zzJ(zztz.class, zztzVar);
    }

    private zztz() {
    }

    @Override // com.google.android.gms.internal.cast.zzxi
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzG(zzb, "\u0001\u000b\u0000\u0001\u0001\u000b\u000b\u0000\u0001\u0000\u0001ဉ\u0001\u0002ဇ\u0002\u0003ဇ\u0003\u0004ဇ\u0004\u0005ဂ\u0006\u0006ဂ\u0007\u0007င\b\bင\t\t'\nဈ\u0000\u000bဇ\u0005", new Object[]{"zzd", "zzf", "zzg", "zzh", "zzi", "zzk", "zzl", "zzm", "zzn", "zzo", "zze", "zzj"});
        }
        if (i2 == 3) {
            return new zztz();
        }
        zzuw zzuwVar = null;
        if (i2 == 4) {
            return new zzty(zzuwVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
