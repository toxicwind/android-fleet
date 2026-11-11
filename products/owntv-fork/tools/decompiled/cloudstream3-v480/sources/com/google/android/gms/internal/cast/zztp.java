package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zztp extends zzxi implements zzym {
    private static final zztp zzb;
    private int zzd;
    private long zze;
    private long zzf;
    private zzxn zzg = zzD();

    static {
        zztp zztpVar = new zztp();
        zzb = zztpVar;
        zzxi.zzJ(zztp.class, zztpVar);
    }

    private zztp() {
    }

    @Override // com.google.android.gms.internal.cast.zzxi
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzG(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001ဂ\u0000\u0002ဂ\u0001\u0003\u001b", new Object[]{"zzd", "zze", "zzf", "zzg", zztr.class});
        }
        if (i2 == 3) {
            return new zztp();
        }
        zzuw zzuwVar = null;
        if (i2 == 4) {
            return new zzto(zzuwVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
