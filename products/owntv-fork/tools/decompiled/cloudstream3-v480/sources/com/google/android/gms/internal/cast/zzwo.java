package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzwo extends zzwq {
    private int zzb;
    private int zzc;
    private int zzd;

    /* synthetic */ zzwo(byte[] bArr, int i, int i2, boolean z, zzwp zzwpVar) {
        super(null);
        this.zzd = Integer.MAX_VALUE;
        this.zzb = 0;
    }

    public final int zza(int i) throws zzxq {
        int i2 = this.zzd;
        this.zzd = 0;
        int i3 = this.zzb + this.zzc;
        this.zzb = i3;
        if (i3 <= 0) {
            this.zzc = 0;
            return i2;
        }
        this.zzc = i3;
        this.zzb = 0;
        return i2;
    }
}
