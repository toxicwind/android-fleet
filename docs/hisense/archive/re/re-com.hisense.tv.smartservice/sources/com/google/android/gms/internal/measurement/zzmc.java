package com.google.android.gms.internal.measurement;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class zzmc {
    public static final zzmc zza = new zzmc(0, new int[0], new Object[0], false);
    public int zzb;
    public int[] zzc;
    public Object[] zzd;
    public int zze;
    public boolean zzf;

    public zzmc() {
        this(0, new int[8], new Object[8], true);
    }

    public zzmc(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zze = -1;
        this.zzb = i;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzf = z;
    }

    public static zzmc zzc() {
        return zza;
    }

    public static zzmc zzd(zzmc zzmcVar, zzmc zzmcVar2) {
        int i = zzmcVar.zzb + zzmcVar2.zzb;
        int[] iArrCopyOf = Arrays.copyOf(zzmcVar.zzc, i);
        System.arraycopy(zzmcVar2.zzc, 0, iArrCopyOf, zzmcVar.zzb, zzmcVar2.zzb);
        Object[] objArrCopyOf = Arrays.copyOf(zzmcVar.zzd, i);
        System.arraycopy(zzmcVar2.zzd, 0, objArrCopyOf, zzmcVar.zzb, zzmcVar2.zzb);
        return new zzmc(i, iArrCopyOf, objArrCopyOf, true);
    }

    public static zzmc zze() {
        return new zzmc(0, new int[8], new Object[8], true);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzmc)) {
            return false;
        }
        zzmc zzmcVar = (zzmc) obj;
        int i = this.zzb;
        if (i == zzmcVar.zzb) {
            int[] iArr = this.zzc;
            int[] iArr2 = zzmcVar.zzc;
            for (int i2 = 0; i2 < i; i2++) {
                if (iArr[i2] == iArr2[i2]) {
                }
            }
            Object[] objArr = this.zzd;
            Object[] objArr2 = zzmcVar.zzd;
            int i3 = this.zzb;
            for (int i4 = 0; i4 < i3; i4++) {
                if (objArr[i4].equals(objArr2[i4])) {
                }
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i = this.zzb;
        int i2 = (i + 527) * 31;
        int[] iArr = this.zzc;
        int iHashCode = 17;
        int i3 = 17;
        for (int i4 = 0; i4 < i; i4++) {
            i3 = (i3 * 31) + iArr[i4];
        }
        int i5 = (i2 + i3) * 31;
        Object[] objArr = this.zzd;
        int i6 = this.zzb;
        for (int i7 = 0; i7 < i6; i7++) {
            iHashCode = (iHashCode * 31) + objArr[i7].hashCode();
        }
        return i5 + iHashCode;
    }

    public final int zza() {
        int iZzA;
        int iZzB;
        int iZzA2;
        int i = this.zze;
        if (i != -1) {
            return i;
        }
        int iZzA3 = 0;
        for (int i2 = 0; i2 < this.zzb; i2++) {
            int i3 = this.zzc[i2];
            int i4 = i3 >>> 3;
            int i5 = i3 & 7;
            if (i5 != 0) {
                if (i5 != 1) {
                    if (i5 == 2) {
                        zzix zzixVar = (zzix) this.zzd[i2];
                        int iZzA4 = zzje.zzA(i4 << 3);
                        int iZzd = zzixVar.zzd();
                        iZzA3 = zzje.zzA(iZzd) + iZzd + iZzA4 + iZzA3;
                    } else if (i5 == 3) {
                        int iZzz = zzje.zzz(i4);
                        iZzA = iZzz + iZzz;
                        iZzB = ((zzmc) this.zzd[i2]).zza();
                    } else {
                        if (i5 != 5) {
                            throw new IllegalStateException(zzkh.zza());
                        }
                        ((Integer) this.zzd[i2]).intValue();
                        iZzA2 = zzje.zzA(i4 << 3) + 4;
                    }
                } else {
                    ((Long) this.zzd[i2]).longValue();
                    iZzA2 = zzje.zzA(i4 << 3) + 8;
                }
                iZzA3 = iZzA2 + iZzA3;
            } else {
                long jLongValue = ((Long) this.zzd[i2]).longValue();
                iZzA = zzje.zzA(i4 << 3);
                iZzB = zzje.zzB(jLongValue);
            }
            iZzA3 = iZzB + iZzA + iZzA3;
        }
        this.zze = iZzA3;
        return iZzA3;
    }

    public final int zzb() {
        int i = this.zze;
        if (i != -1) {
            return i;
        }
        int iZzA = 0;
        for (int i2 = 0; i2 < this.zzb; i2++) {
            int i3 = this.zzc[i2];
            zzix zzixVar = (zzix) this.zzd[i2];
            int iZzA2 = zzje.zzA(8);
            int iZzd = zzixVar.zzd();
            iZzA += zzje.zzA(iZzd) + iZzd + zzje.zzA(24) + zzje.zzA(i3 >>> 3) + zzje.zzA(16) + iZzA2 + iZzA2;
        }
        this.zze = iZzA;
        return iZzA;
    }

    public final void zzf() {
        this.zzf = false;
    }

    public final void zzg(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.zzb; i2++) {
            zzle.zzb(sb, i, String.valueOf(this.zzc[i2] >>> 3), this.zzd[i2]);
        }
    }

    public final void zzh(int i, Object obj) {
        if (!this.zzf) {
            throw new UnsupportedOperationException();
        }
        int i2 = this.zzb;
        int[] iArr = this.zzc;
        if (i2 == iArr.length) {
            int i3 = i2 + (i2 < 4 ? 8 : i2 >> 1);
            this.zzc = Arrays.copyOf(iArr, i3);
            this.zzd = Arrays.copyOf(this.zzd, i3);
        }
        int[] iArr2 = this.zzc;
        int i4 = this.zzb;
        iArr2[i4] = i;
        this.zzd[i4] = obj;
        this.zzb = i4 + 1;
    }

    public final void zzi(zzjf zzjfVar) {
        if (this.zzb != 0) {
            for (int i = 0; i < this.zzb; i++) {
                int i2 = this.zzc[i];
                Object obj = this.zzd[i];
                int i3 = i2 >>> 3;
                int i4 = i2 & 7;
                if (i4 == 0) {
                    zzjfVar.zzt(i3, ((Long) obj).longValue());
                } else if (i4 == 1) {
                    zzjfVar.zzm(i3, ((Long) obj).longValue());
                } else if (i4 == 2) {
                    zzjfVar.zzd(i3, (zzix) obj);
                } else if (i4 == 3) {
                    zzjfVar.zzE(i3);
                    ((zzmc) obj).zzi(zzjfVar);
                    zzjfVar.zzh(i3);
                } else {
                    if (i4 != 5) {
                        throw new RuntimeException(zzkh.zza());
                    }
                    zzjfVar.zzk(i3, ((Integer) obj).intValue());
                }
            }
        }
    }
}
