package com.google.android.gms.internal.measurement;

import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
public class zziv extends zziu {
    public final byte[] zza;

    public zziv(byte[] bArr) {
        if (bArr == null) {
            throw null;
        }
        this.zza = bArr;
    }

    @Override // com.google.android.gms.internal.measurement.zzix
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzix) || zzd() != ((zzix) obj).zzd()) {
            return false;
        }
        if (zzd() == 0) {
            return true;
        }
        if (!(obj instanceof zziv)) {
            return obj.equals(this);
        }
        zziv zzivVar = (zziv) obj;
        int iZzk = zzk();
        int iZzk2 = zzivVar.zzk();
        if (iZzk != 0 && iZzk2 != 0 && iZzk != iZzk2) {
            return false;
        }
        int iZzd = zzd();
        if (iZzd > zzivVar.zzd()) {
            int iZzd2 = zzd();
            StringBuilder sb = new StringBuilder(40);
            sb.append("Length too large: ");
            sb.append(iZzd);
            sb.append(iZzd2);
            throw new IllegalArgumentException(sb.toString());
        }
        if (iZzd > zzivVar.zzd()) {
            int iZzd3 = zzivVar.zzd();
            StringBuilder sb2 = new StringBuilder(59);
            sb2.append("Ran off end of other: 0, ");
            sb2.append(iZzd);
            sb2.append(", ");
            sb2.append(iZzd3);
            throw new IllegalArgumentException(sb2.toString());
        }
        byte[] bArr = this.zza;
        byte[] bArr2 = zzivVar.zza;
        zzivVar.zzc();
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

    @Override // com.google.android.gms.internal.measurement.zzix
    public byte zza(int i) {
        return this.zza[i];
    }

    @Override // com.google.android.gms.internal.measurement.zzix
    public byte zzb(int i) {
        return this.zza[i];
    }

    public int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.measurement.zzix
    public int zzd() {
        return this.zza.length;
    }

    @Override // com.google.android.gms.internal.measurement.zzix
    public final int zze(int i, int i2, int i3) {
        return zzkf.zzd(i, this.zza, 0, i3);
    }

    @Override // com.google.android.gms.internal.measurement.zzix
    public final zzix zzf(int i, int i2) {
        int iZzj = zzix.zzj(0, i2, zzd());
        return iZzj == 0 ? zzix.zzb : new zzis(this.zza, 0, iZzj);
    }

    @Override // com.google.android.gms.internal.measurement.zzix
    public final String zzg(Charset charset) {
        return new String(this.zza, 0, zzd(), charset);
    }

    @Override // com.google.android.gms.internal.measurement.zzix
    public final void zzh(zzin zzinVar) {
        ((zzjc) zzinVar).zzc(this.zza, 0, zzd());
    }

    @Override // com.google.android.gms.internal.measurement.zzix
    public final boolean zzi() {
        return zzmq.zzf(this.zza, 0, zzd());
    }
}
