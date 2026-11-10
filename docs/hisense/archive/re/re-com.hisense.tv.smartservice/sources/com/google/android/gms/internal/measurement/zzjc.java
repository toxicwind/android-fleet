package com.google.android.gms.internal.measurement;

/* JADX INFO: loaded from: classes.dex */
public final class zzjc extends zzje {
    public final byte[] zzb;
    public final int zzc;
    public int zzd;

    public zzjc(byte[] bArr, int i, int i2) {
        super(null);
        if (bArr == null) {
            throw new NullPointerException("buffer");
        }
        int length = bArr.length;
        if (((length - i2) | i2) < 0) {
            throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", Integer.valueOf(length), 0, Integer.valueOf(i2)));
        }
        this.zzb = bArr;
        this.zzd = 0;
        this.zzc = i2;
    }

    @Override // com.google.android.gms.internal.measurement.zzje
    public final int zza() {
        return this.zzc - this.zzd;
    }

    @Override // com.google.android.gms.internal.measurement.zzje
    public final void zzb(byte b2) throws zzjd {
        try {
            byte[] bArr = this.zzb;
            int i = this.zzd;
            this.zzd = i + 1;
            bArr[i] = b2;
        } catch (IndexOutOfBoundsException e2) {
            throw new zzjd(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1), e2);
        }
    }

    public final void zzc(byte[] bArr, int i, int i2) {
        try {
            System.arraycopy(bArr, 0, this.zzb, this.zzd, i2);
            this.zzd += i2;
        } catch (IndexOutOfBoundsException e2) {
            throw new zzjd(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), Integer.valueOf(i2)), e2);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzje
    public final void zzd(int i, boolean z) throws zzjd {
        zzq(i << 3);
        zzb(z ? (byte) 1 : (byte) 0);
    }

    @Override // com.google.android.gms.internal.measurement.zzje
    public final void zze(int i, zzix zzixVar) {
        zzq((i << 3) | 2);
        zzq(zzixVar.zzd());
        zzixVar.zzh(this);
    }

    @Override // com.google.android.gms.internal.measurement.zzje
    public final void zzf(int i, int i2) throws zzjd {
        zzq((i << 3) | 5);
        zzg(i2);
    }

    @Override // com.google.android.gms.internal.measurement.zzje
    public final void zzg(int i) throws zzjd {
        try {
            byte[] bArr = this.zzb;
            int i2 = this.zzd;
            int i3 = i2 + 1;
            this.zzd = i3;
            bArr[i2] = (byte) (i & 255);
            int i4 = i3 + 1;
            this.zzd = i4;
            bArr[i3] = (byte) ((i >> 8) & 255);
            int i5 = i4 + 1;
            this.zzd = i5;
            bArr[i4] = (byte) ((i >> 16) & 255);
            this.zzd = i5 + 1;
            bArr[i5] = (byte) ((i >> 24) & 255);
        } catch (IndexOutOfBoundsException e2) {
            throw new zzjd(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1), e2);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzje
    public final void zzh(int i, long j) throws zzjd {
        zzq((i << 3) | 1);
        zzi(j);
    }

    @Override // com.google.android.gms.internal.measurement.zzje
    public final void zzi(long j) throws zzjd {
        try {
            byte[] bArr = this.zzb;
            int i = this.zzd;
            int i2 = i + 1;
            this.zzd = i2;
            bArr[i] = (byte) (((int) j) & 255);
            int i3 = i2 + 1;
            this.zzd = i3;
            bArr[i2] = (byte) (((int) (j >> 8)) & 255);
            int i4 = i3 + 1;
            this.zzd = i4;
            bArr[i3] = (byte) (((int) (j >> 16)) & 255);
            int i5 = i4 + 1;
            this.zzd = i5;
            bArr[i4] = (byte) (((int) (j >> 24)) & 255);
            int i6 = i5 + 1;
            this.zzd = i6;
            bArr[i5] = (byte) (((int) (j >> 32)) & 255);
            int i7 = i6 + 1;
            this.zzd = i7;
            bArr[i6] = (byte) (((int) (j >> 40)) & 255);
            int i8 = i7 + 1;
            this.zzd = i8;
            bArr[i7] = (byte) (((int) (j >> 48)) & 255);
            this.zzd = i8 + 1;
            bArr[i8] = (byte) (((int) (j >> 56)) & 255);
        } catch (IndexOutOfBoundsException e2) {
            throw new zzjd(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1), e2);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzje
    public final void zzj(int i, int i2) throws zzjd {
        zzq(i << 3);
        zzk(i2);
    }

    @Override // com.google.android.gms.internal.measurement.zzje
    public final void zzk(int i) throws zzjd {
        if (i >= 0) {
            zzq(i);
        } else {
            zzs(i);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzje
    public final void zzl(byte[] bArr, int i, int i2) {
        zzc(bArr, 0, i2);
    }

    @Override // com.google.android.gms.internal.measurement.zzje
    public final void zzm(int i, String str) throws zzjd {
        zzq((i << 3) | 2);
        zzn(str);
    }

    public final void zzn(String str) throws zzjd {
        int i = this.zzd;
        try {
            int iZzA = zzje.zzA(str.length() * 3);
            int iZzA2 = zzje.zzA(str.length());
            if (iZzA2 != iZzA) {
                zzq(zzmq.zzc(str));
                byte[] bArr = this.zzb;
                int i2 = this.zzd;
                this.zzd = zzmq.zzb(str, bArr, i2, this.zzc - i2);
                return;
            }
            int i3 = i + iZzA2;
            this.zzd = i3;
            int iZzb = zzmq.zzb(str, this.zzb, i3, this.zzc - i3);
            this.zzd = i;
            zzq((iZzb - i) - iZzA2);
            this.zzd = iZzb;
        } catch (zzmp e2) {
            this.zzd = i;
            zzE(str, e2);
        } catch (IndexOutOfBoundsException e3) {
            throw new zzjd(e3);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzje
    public final void zzo(int i, int i2) {
        zzq((i << 3) | i2);
    }

    @Override // com.google.android.gms.internal.measurement.zzje
    public final void zzp(int i, int i2) {
        zzq(i << 3);
        zzq(i2);
    }

    @Override // com.google.android.gms.internal.measurement.zzje
    public final void zzq(int i) {
        if (zzje.zzc) {
            int i2 = zzij.zza;
        }
        while ((i & (-128)) != 0) {
            try {
                byte[] bArr = this.zzb;
                int i3 = this.zzd;
                this.zzd = i3 + 1;
                bArr[i3] = (byte) ((i & 127) | 128);
                i >>>= 7;
            } catch (IndexOutOfBoundsException e2) {
                throw new zzjd(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1), e2);
            }
        }
        byte[] bArr2 = this.zzb;
        int i4 = this.zzd;
        this.zzd = i4 + 1;
        bArr2[i4] = (byte) i;
    }

    @Override // com.google.android.gms.internal.measurement.zzje
    public final void zzr(int i, long j) throws zzjd {
        zzq(i << 3);
        zzs(j);
    }

    @Override // com.google.android.gms.internal.measurement.zzje
    public final void zzs(long j) throws zzjd {
        if (zzje.zzc && this.zzc - this.zzd >= 10) {
            while ((j & (-128)) != 0) {
                byte[] bArr = this.zzb;
                int i = this.zzd;
                this.zzd = i + 1;
                zzml.zzn(bArr, i, (byte) ((((int) j) & 127) | 128));
                j >>>= 7;
            }
            byte[] bArr2 = this.zzb;
            int i2 = this.zzd;
            this.zzd = i2 + 1;
            zzml.zzn(bArr2, i2, (byte) j);
            return;
        }
        while ((j & (-128)) != 0) {
            try {
                byte[] bArr3 = this.zzb;
                int i3 = this.zzd;
                this.zzd = i3 + 1;
                bArr3[i3] = (byte) ((((int) j) & 127) | 128);
                j >>>= 7;
            } catch (IndexOutOfBoundsException e2) {
                throw new zzjd(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1), e2);
            }
        }
        byte[] bArr4 = this.zzb;
        int i4 = this.zzd;
        this.zzd = i4 + 1;
        bArr4[i4] = (byte) j;
    }
}
