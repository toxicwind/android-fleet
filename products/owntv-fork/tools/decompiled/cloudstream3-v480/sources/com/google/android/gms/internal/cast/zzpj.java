package com.google.android.gms.internal.cast;

import java.util.Iterator;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzpj extends zzxi implements zzym {
    private static final zzpj zzb;
    private int zzd;
    private zzpp zze;
    private zzsc zzf;
    private zzxn zzg = zzD();
    private zzxl zzh = zzB();

    static {
        zzpj zzpjVar = new zzpj();
        zzb = zzpjVar;
        zzxi.zzJ(zzpj.class, zzpjVar);
    }

    private zzpj() {
    }

    public static zzpi zza() {
        return (zzpi) zzb.zzy();
    }

    static /* synthetic */ void zzd(zzpj zzpjVar, Iterable iterable) {
        zzxl zzxlVar = zzpjVar.zzh;
        if (!zzxlVar.zzc()) {
            int size = zzxlVar.size();
            zzpjVar.zzh = zzxlVar.zzd(size + size);
        }
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            zzpjVar.zzh.zzh(((zzpb) it.next()).zza());
        }
    }

    static /* synthetic */ void zze(zzpj zzpjVar, zzpp zzppVar) {
        zzppVar.getClass();
        zzpjVar.zze = zzppVar;
        zzpjVar.zzd |= 1;
    }

    @Override // com.google.android.gms.internal.cast.zzxi
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzG(zzb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0002\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003\u001b\u0004ࠞ", new Object[]{"zzd", "zze", "zzf", "zzg", zzrw.class, "zzh", zzpb.zzb()});
        }
        if (i2 == 3) {
            return new zzpj();
        }
        zzuw zzuwVar = null;
        if (i2 == 4) {
            return new zzpi(zzuwVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
