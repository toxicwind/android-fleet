package com.google.android.gms.internal.measurement;

import b.a.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class zzis extends zziv {
    public final int zzc;

    public zzis(byte[] bArr, int i, int i2) {
        super(bArr);
        zzix.zzj(0, i2, bArr.length);
        this.zzc = i2;
    }

    @Override // com.google.android.gms.internal.measurement.zziv, com.google.android.gms.internal.measurement.zzix
    public final byte zza(int i) {
        int i2 = this.zzc;
        if (((i2 - (i + 1)) | i) >= 0) {
            return this.zza[i];
        }
        if (i < 0) {
            throw new ArrayIndexOutOfBoundsException(a.a(22, "Index < 0: ", i));
        }
        StringBuilder sb = new StringBuilder(40);
        sb.append("Index > length: ");
        sb.append(i);
        sb.append(", ");
        sb.append(i2);
        throw new ArrayIndexOutOfBoundsException(sb.toString());
    }

    @Override // com.google.android.gms.internal.measurement.zziv, com.google.android.gms.internal.measurement.zzix
    public final byte zzb(int i) {
        return this.zza[i];
    }

    @Override // com.google.android.gms.internal.measurement.zziv
    public final int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.measurement.zziv, com.google.android.gms.internal.measurement.zzix
    public final int zzd() {
        return this.zzc;
    }
}
