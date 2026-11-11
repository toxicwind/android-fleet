package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzgj extends zzgn {
    static final zzgj zza = new zzgj();

    private zzgj() {
    }

    public final boolean equals(Object obj) {
        return this == obj;
    }

    public final int hashCode() {
        return 2040732332;
    }

    public final String toString() {
        return "Optional.absent()";
    }

    @Override // com.google.android.gms.internal.cast.zzgn
    public final Object zza(Object obj) {
        zzgo.zzc(obj, "use Optional.orNull() instead of Optional.or(null)");
        return obj;
    }
}
