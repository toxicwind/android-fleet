package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzgp extends zzgn {
    private final Object zza;

    zzgp(Object obj) {
        this.zza = obj;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzgp) {
            return this.zza.equals(((zzgp) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode() + 1502476572;
    }

    public final String toString() {
        return "Optional.of(" + this.zza.toString() + ")";
    }

    @Override // com.google.android.gms.internal.cast.zzgn
    public final Object zza(Object obj) {
        zzgo.zzc(obj, "use Optional.orNull() instead of Optional.or(null)");
        return this.zza;
    }
}
