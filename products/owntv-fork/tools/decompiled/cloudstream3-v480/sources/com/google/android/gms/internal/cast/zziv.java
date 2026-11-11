package com.google.android.gms.internal.cast;

import java.util.Iterator;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zziv {
    private static final zziq zza = new zzit();
    private static final zzip zzb = new zziu();

    public static zzin zza(Set set) {
        zzin zzinVar = new zzin(zza, null);
        zzip zzipVar = zzb;
        Iterator it = set.iterator();
        while (it.hasNext()) {
            zzinVar.zzd((zzie) it.next());
        }
        return zzinVar;
    }
}
