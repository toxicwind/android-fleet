package com.google.android.gms.internal.measurement;

import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
public final class zziq implements Comparator<zzix> {
    @Override // java.util.Comparator
    public final /* synthetic */ int compare(zzix zzixVar, zzix zzixVar2) {
        zzix zzixVar3 = zzixVar;
        zzix zzixVar4 = zzixVar2;
        zzio zzioVar = new zzio(zzixVar3);
        zzio zzioVar2 = new zzio(zzixVar4);
        while (zzioVar.hasNext() && zzioVar2.hasNext()) {
            int iZza = zzip.zza(zzioVar.zza() & 255, zzioVar2.zza() & 255);
            if (iZza != 0) {
                return iZza;
            }
        }
        return zzip.zza(zzixVar3.zzd(), zzixVar4.zzd());
    }
}
