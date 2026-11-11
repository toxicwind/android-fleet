package com.google.android.gms.internal.cast;

import java.util.Comparator;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzjr implements Comparator {
    zzjr() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        zzjx zzjxVarZza = zzjx.zza(obj);
        zzjx zzjxVarZza2 = zzjx.zza(obj2);
        if (zzjxVarZza != zzjxVarZza2) {
            return zzjxVarZza.compareTo(zzjxVarZza2);
        }
        int iOrdinal = zzjxVarZza.ordinal();
        if (iOrdinal == 0) {
            return ((Boolean) obj).compareTo((Boolean) obj2);
        }
        if (iOrdinal == 1) {
            return ((String) obj).compareTo((String) obj2);
        }
        if (iOrdinal == 2) {
            return ((Long) obj).compareTo((Long) obj2);
        }
        if (iOrdinal == 3) {
            return ((Double) obj).compareTo((Double) obj2);
        }
        throw null;
    }
}
