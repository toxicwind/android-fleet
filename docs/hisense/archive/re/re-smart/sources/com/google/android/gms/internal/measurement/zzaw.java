package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class zzaw {
    public final List<zzbl> zza = new ArrayList();

    public abstract zzap zza(String str, zzg zzgVar, List<zzap> list);

    public final zzap zzb(String str) {
        if (!this.zza.contains(zzh.zze(str))) {
            throw new IllegalArgumentException("Command not supported");
        }
        String strValueOf = String.valueOf(str);
        throw new UnsupportedOperationException(strValueOf.length() != 0 ? "Command not implemented: ".concat(strValueOf) : new String("Command not implemented: "));
    }
}
