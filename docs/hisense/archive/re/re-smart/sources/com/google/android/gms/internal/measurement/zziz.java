package com.google.android.gms.internal.measurement;

/* JADX INFO: loaded from: classes.dex */
public final class zziz extends zzja {
    public final byte[] zzb;
    public int zzc;
    public int zzd;
    public int zze;

    public /* synthetic */ zziz(byte[] bArr, int i, int i2, boolean z, zziy zziyVar) {
        super(null);
        this.zze = Integer.MAX_VALUE;
        this.zzb = bArr;
        this.zzc = 0;
    }

    public final int zza(int i) {
        int i2 = this.zze;
        this.zze = 0;
        int i3 = this.zzc + this.zzd;
        this.zzc = i3;
        if (i3 > 0) {
            this.zzd = i3;
            this.zzc = 0;
        } else {
            this.zzd = 0;
        }
        return i2;
    }
}
