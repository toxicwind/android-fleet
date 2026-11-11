package com.google.android.gms.internal.cast;

import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
class zzwl extends zzwk {
    protected final byte[] zza;

    zzwl(byte[] bArr) {
        super(null);
        bArr.getClass();
        this.zza = bArr;
    }

    @Override // com.google.android.gms.internal.cast.zzwn
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzwn) || zzd() != ((zzwn) obj).zzd()) {
            return false;
        }
        if (zzd() == 0) {
            return true;
        }
        if (!(obj instanceof zzwl)) {
            return obj.equals(this);
        }
        zzwl zzwlVar = (zzwl) obj;
        int iZzi = zzi();
        int iZzi2 = zzwlVar.zzi();
        if (iZzi != 0 && iZzi2 != 0 && iZzi != iZzi2) {
            return false;
        }
        int iZzd = zzd();
        if (iZzd > zzwlVar.zzd()) {
            throw new IllegalArgumentException("Length too large: " + iZzd + zzd());
        }
        if (iZzd > zzwlVar.zzd()) {
            throw new IllegalArgumentException("Ran off end of other: 0, " + iZzd + ", " + zzwlVar.zzd());
        }
        if (!(zzwlVar instanceof zzwl)) {
            return zzwlVar.zzf(0, iZzd).equals(zzf(0, iZzd));
        }
        byte[] bArr = this.zza;
        byte[] bArr2 = zzwlVar.zza;
        zzwlVar.zzc();
        int i = 0;
        int i2 = 0;
        while (i < iZzd) {
            if (bArr[i] != bArr2[i2]) {
                return false;
            }
            i++;
            i2++;
        }
        return true;
    }

    @Override // com.google.android.gms.internal.cast.zzwn
    public byte zza(int i) {
        return this.zza[i];
    }

    @Override // com.google.android.gms.internal.cast.zzwn
    byte zzb(int i) {
        return this.zza[i];
    }

    protected int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.cast.zzwn
    public int zzd() {
        return this.zza.length;
    }

    @Override // com.google.android.gms.internal.cast.zzwn
    protected final int zze(int i, int i2, int i3) {
        return zzxo.zzb(i, this.zza, 0, i3);
    }

    @Override // com.google.android.gms.internal.cast.zzwn
    public final zzwn zzf(int i, int i2) {
        zzh(0, i2, zzd());
        return i2 == 0 ? zzwn.zzb : new zzwi(this.zza, 0, i2);
    }

    @Override // com.google.android.gms.internal.cast.zzwn
    final void zzg(zzwf zzwfVar) throws IOException {
        ((zzwr) zzwfVar).zzc(this.zza, 0, zzd());
    }
}
