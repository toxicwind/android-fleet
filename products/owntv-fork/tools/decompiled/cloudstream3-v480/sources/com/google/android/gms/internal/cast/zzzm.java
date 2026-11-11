package com.google.android.gms.internal.cast;

import sun.misc.Unsafe;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzzm extends zzzn {
    zzzm(Unsafe unsafe) {
        super(unsafe);
    }

    @Override // com.google.android.gms.internal.cast.zzzn
    public final double zza(Object obj, long j) {
        return Double.longBitsToDouble(this.zza.getLong(obj, j));
    }

    @Override // com.google.android.gms.internal.cast.zzzn
    public final float zzb(Object obj, long j) {
        return Float.intBitsToFloat(this.zza.getInt(obj, j));
    }

    /* JADX WARN: Failed to inline method: com.google.android.gms.internal.cast.zzzo.zzi(java.lang.Object, long, boolean):void */
    /* JADX WARN: Failed to inline method: com.google.android.gms.internal.cast.zzzo.zzj(java.lang.Object, long, boolean):void */
    /* JADX WARN: Unknown register number '(r4v0 boolean)' in method call: com.google.android.gms.internal.cast.zzzo.zzi(java.lang.Object, long, boolean):void */
    /* JADX WARN: Unknown register number '(r4v0 boolean)' in method call: com.google.android.gms.internal.cast.zzzo.zzj(java.lang.Object, long, boolean):void */
    @Override // com.google.android.gms.internal.cast.zzzn
    public final void zzc(Object obj, long j, boolean z) {
        if (zzzo.zzb) {
            zzzo.zzi(obj, j, z);
        } else {
            zzzo.zzj(obj, j, z);
        }
    }

    @Override // com.google.android.gms.internal.cast.zzzn
    public final void zzd(Object obj, long j, byte b) {
        if (zzzo.zzb) {
            zzzo.zzD(obj, j, b);
        } else {
            zzzo.zzE(obj, j, b);
        }
    }

    @Override // com.google.android.gms.internal.cast.zzzn
    public final void zze(Object obj, long j, double d) {
        this.zza.putLong(obj, j, Double.doubleToLongBits(d));
    }

    @Override // com.google.android.gms.internal.cast.zzzn
    public final void zzf(Object obj, long j, float f) {
        this.zza.putInt(obj, j, Float.floatToIntBits(f));
    }

    @Override // com.google.android.gms.internal.cast.zzzn
    public final boolean zzg(Object obj, long j) {
        return zzzo.zzb ? zzzo.zzt(obj, j) : zzzo.zzu(obj, j);
    }
}
