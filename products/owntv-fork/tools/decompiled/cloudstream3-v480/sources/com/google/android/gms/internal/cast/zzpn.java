package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzpn extends zzxi implements zzym {
    private static final zzpn zzb;
    private int zzd;
    private int zze;
    private boolean zzf;
    private int zzg;
    private boolean zzh;
    private zzxn zzi = zzD();
    private zzxn zzj = zzD();
    private String zzk = "";

    static {
        zzpn zzpnVar = new zzpn();
        zzb = zzpnVar;
        zzxi.zzJ(zzpn.class, zzpnVar);
    }

    private zzpn() {
    }

    @Override // com.google.android.gms.internal.cast.zzxi
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzG(zzb, "\u0001\u0007\u0000\u0001\u0001\t\u0007\u0000\u0002\u0000\u0001᠌\u0000\u0002ဇ\u0001\u0003᠌\u0002\u0004ဇ\u0003\u0007\u001b\b\u001b\tဈ\u0004", new Object[]{"zzd", "zze", zzkt.zza(), "zzf", "zzg", zzlx.zza(), "zzh", "zzi", zzso.class, "zzj", zzso.class, "zzk"});
        }
        if (i2 == 3) {
            return new zzpn();
        }
        zzuw zzuwVar = null;
        if (i2 == 4) {
            return new zzpm(zzuwVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
