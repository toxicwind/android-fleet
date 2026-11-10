package com.google.android.gms.internal.measurement;

import com.google.common.base.Ascii;

/* JADX INFO: loaded from: classes.dex */
public final class zzil {
    public static int zza(byte[] bArr, int i, zzik zzikVar) {
        int iZzj = zzj(bArr, i, zzikVar);
        int i2 = zzikVar.zza;
        if (i2 < 0) {
            throw zzkh.zzd();
        }
        if (i2 > bArr.length - iZzj) {
            throw zzkh.zzf();
        }
        if (i2 == 0) {
            zzikVar.zzc = zzix.zzb;
            return iZzj;
        }
        zzikVar.zzc = zzix.zzl(bArr, iZzj, i2);
        return iZzj + i2;
    }

    public static int zzb(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    public static int zzc(zzln zzlnVar, byte[] bArr, int i, int i2, int i3, zzik zzikVar) {
        zzlf zzlfVar = (zzlf) zzlnVar;
        Object objZze = zzlfVar.zze();
        int iZzc = zzlfVar.zzc(objZze, bArr, i, i2, i3, zzikVar);
        zzlfVar.zzf(objZze);
        zzikVar.zzc = objZze;
        return iZzc;
    }

    public static int zzd(zzln zzlnVar, byte[] bArr, int i, int i2, zzik zzikVar) {
        int iZzk = i + 1;
        int i3 = bArr[i];
        if (i3 < 0) {
            iZzk = zzk(i3, bArr, iZzk, zzikVar);
            i3 = zzikVar.zza;
        }
        int i4 = iZzk;
        if (i3 < 0 || i3 > i2 - i4) {
            throw zzkh.zzf();
        }
        Object objZze = zzlnVar.zze();
        int i5 = i3 + i4;
        zzlnVar.zzh(objZze, bArr, i4, i5, zzikVar);
        zzlnVar.zzf(objZze);
        zzikVar.zzc = objZze;
        return i5;
    }

    public static int zze(zzln<?> zzlnVar, int i, byte[] bArr, int i2, int i3, zzke<?> zzkeVar, zzik zzikVar) {
        int iZzd = zzd(zzlnVar, bArr, i2, i3, zzikVar);
        zzkeVar.add(zzikVar.zzc);
        while (iZzd < i3) {
            int iZzj = zzj(bArr, iZzd, zzikVar);
            if (i != zzikVar.zza) {
                break;
            }
            iZzd = zzd(zzlnVar, bArr, iZzj, i3, zzikVar);
            zzkeVar.add(zzikVar.zzc);
        }
        return iZzd;
    }

    public static int zzf(byte[] bArr, int i, zzke<?> zzkeVar, zzik zzikVar) {
        zzjy zzjyVar = (zzjy) zzkeVar;
        int iZzj = zzj(bArr, i, zzikVar);
        int i2 = zzikVar.zza + iZzj;
        while (iZzj < i2) {
            iZzj = zzj(bArr, iZzj, zzikVar);
            zzjyVar.zzh(zzikVar.zza);
        }
        if (iZzj == i2) {
            return iZzj;
        }
        throw zzkh.zzf();
    }

    public static int zzg(byte[] bArr, int i, zzik zzikVar) {
        int iZzj = zzj(bArr, i, zzikVar);
        int i2 = zzikVar.zza;
        if (i2 < 0) {
            throw zzkh.zzd();
        }
        if (i2 == 0) {
            zzikVar.zzc = "";
            return iZzj;
        }
        zzikVar.zzc = new String(bArr, iZzj, i2, zzkf.zzb);
        return iZzj + i2;
    }

    public static int zzh(byte[] bArr, int i, zzik zzikVar) {
        int iZzj = zzj(bArr, i, zzikVar);
        int i2 = zzikVar.zza;
        if (i2 < 0) {
            throw zzkh.zzd();
        }
        if (i2 == 0) {
            zzikVar.zzc = "";
            return iZzj;
        }
        zzikVar.zzc = zzmq.zzd(bArr, iZzj, i2);
        return iZzj + i2;
    }

    public static int zzi(int i, byte[] bArr, int i2, int i3, zzmc zzmcVar, zzik zzikVar) {
        if ((i >>> 3) == 0) {
            throw zzkh.zzb();
        }
        int i4 = i & 7;
        if (i4 == 0) {
            int iZzm = zzm(bArr, i2, zzikVar);
            zzmcVar.zzh(i, Long.valueOf(zzikVar.zzb));
            return iZzm;
        }
        if (i4 == 1) {
            zzmcVar.zzh(i, Long.valueOf(zzn(bArr, i2)));
            return i2 + 8;
        }
        if (i4 == 2) {
            int iZzj = zzj(bArr, i2, zzikVar);
            int i5 = zzikVar.zza;
            if (i5 < 0) {
                throw zzkh.zzd();
            }
            if (i5 > bArr.length - iZzj) {
                throw zzkh.zzf();
            }
            if (i5 == 0) {
                zzmcVar.zzh(i, zzix.zzb);
            } else {
                zzmcVar.zzh(i, zzix.zzl(bArr, iZzj, i5));
            }
            return iZzj + i5;
        }
        if (i4 != 3) {
            if (i4 != 5) {
                throw zzkh.zzb();
            }
            zzmcVar.zzh(i, Integer.valueOf(zzb(bArr, i2)));
            return i2 + 4;
        }
        int i6 = (i & (-8)) | 4;
        zzmc zzmcVarZze = zzmc.zze();
        int i7 = 0;
        while (i2 < i3) {
            int iZzj2 = zzj(bArr, i2, zzikVar);
            int i8 = zzikVar.zza;
            if (i8 == i6) {
                i7 = i8;
                i2 = iZzj2;
                break;
            }
            i7 = i8;
            i2 = zzi(i8, bArr, iZzj2, i3, zzmcVarZze, zzikVar);
        }
        if (i2 > i3 || i7 != i6) {
            throw zzkh.zze();
        }
        zzmcVar.zzh(i, zzmcVarZze);
        return i2;
    }

    public static int zzj(byte[] bArr, int i, zzik zzikVar) {
        int i2 = i + 1;
        byte b2 = bArr[i];
        if (b2 < 0) {
            return zzk(b2, bArr, i2, zzikVar);
        }
        zzikVar.zza = b2;
        return i2;
    }

    public static int zzk(int i, byte[] bArr, int i2, zzik zzikVar) {
        int i3 = i & 127;
        int i4 = i2 + 1;
        byte b2 = bArr[i2];
        if (b2 >= 0) {
            zzikVar.zza = i3 | (b2 << 7);
            return i4;
        }
        int i5 = i3 | ((b2 & Ascii.DEL) << 7);
        int i6 = i4 + 1;
        byte b3 = bArr[i4];
        if (b3 >= 0) {
            zzikVar.zza = i5 | (b3 << Ascii.SO);
            return i6;
        }
        int i7 = i5 | ((b3 & Ascii.DEL) << 14);
        int i8 = i6 + 1;
        byte b4 = bArr[i6];
        if (b4 >= 0) {
            zzikVar.zza = i7 | (b4 << Ascii.NAK);
            return i8;
        }
        int i9 = i7 | ((b4 & Ascii.DEL) << 21);
        int i10 = i8 + 1;
        byte b5 = bArr[i8];
        if (b5 >= 0) {
            zzikVar.zza = i9 | (b5 << Ascii.FS);
            return i10;
        }
        int i11 = i9 | ((b5 & Ascii.DEL) << 28);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] >= 0) {
                zzikVar.zza = i11;
                return i12;
            }
            i10 = i12;
        }
    }

    public static int zzl(int i, byte[] bArr, int i2, int i3, zzke<?> zzkeVar, zzik zzikVar) {
        zzjy zzjyVar = (zzjy) zzkeVar;
        int iZzj = zzj(bArr, i2, zzikVar);
        zzjyVar.zzh(zzikVar.zza);
        while (iZzj < i3) {
            int iZzj2 = zzj(bArr, iZzj, zzikVar);
            if (i != zzikVar.zza) {
                break;
            }
            iZzj = zzj(bArr, iZzj2, zzikVar);
            zzjyVar.zzh(zzikVar.zza);
        }
        return iZzj;
    }

    public static int zzm(byte[] bArr, int i, zzik zzikVar) {
        int i2 = i + 1;
        long j = bArr[i];
        if (j >= 0) {
            zzikVar.zzb = j;
            return i2;
        }
        int i3 = i2 + 1;
        byte b2 = bArr[i2];
        long j2 = (j & 127) | (((long) (b2 & Ascii.DEL)) << 7);
        int i4 = 7;
        while (b2 < 0) {
            int i5 = i3 + 1;
            byte b3 = bArr[i3];
            i4 += 7;
            j2 |= ((long) (b3 & Ascii.DEL)) << i4;
            b2 = b3;
            i3 = i5;
        }
        zzikVar.zzb = j2;
        return i3;
    }

    public static long zzn(byte[] bArr, int i) {
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }
}
