package com.google.android.gms.internal.cast;

import java.util.Collections;
import java.util.Comparator;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzjy {
    private static final Comparator zza = new zzjr();
    private static final zzjy zzb = new zzjy(new zzjw(Collections.EMPTY_LIST));
    private final zzjw zzc;

    private zzjy(zzjw zzjwVar) {
        this.zzc = zzjwVar;
    }

    public static zzjy zza() {
        return zzb;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof zzjy) && ((zzjy) obj).zzc.equals(this.zzc);
    }

    public final int hashCode() {
        return ~this.zzc.hashCode();
    }

    public final String toString() {
        return this.zzc.toString();
    }
}
