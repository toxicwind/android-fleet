package com.google.android.gms.internal.measurement;

import com.google.common.base.Ascii;

/* JADX INFO: loaded from: classes.dex */
public final class zzmm {
    public static /* synthetic */ void zza(byte b2, byte b3, byte b4, byte b5, char[] cArr, int i) throws zzkh {
        if (!zze(b3)) {
            if ((((b3 + 112) + (b2 << Ascii.FS)) >> 30) == 0 && !zze(b4) && !zze(b5)) {
                int i2 = ((b2 & 7) << 18) | ((b3 & 63) << 12) | ((b4 & 63) << 6) | (b5 & 63);
                cArr[i] = (char) ((i2 >>> 10) + 55232);
                cArr[i + 1] = (char) ((i2 & 1023) + 56320);
                return;
            }
        }
        throw zzkh.zzc();
    }

    public static /* synthetic */ void zzb(byte b2, byte b3, char[] cArr, int i) throws zzkh {
        if (b2 < -62 || zze(b3)) {
            throw zzkh.zzc();
        }
        cArr[i] = (char) (((b2 & Ascii.US) << 6) | (b3 & 63));
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0013 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:11:0x0015  */
    /* JADX WARN: Code duplicated, block: B:12:0x0016 A[PHI: r2
      0x0016: PHI (r2v3 byte) = (r2v2 byte), (r2v9 byte) binds: [B:9:0x0011, B:11:0x0015] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:14:0x001c  */
    public static /* synthetic */ void zzc(byte b2, byte b3, byte b4, char[] cArr, int i) throws zzkh {
        if (!zze(b3)) {
            if (b2 != -32) {
                if (b2 != -19) {
                    if (!zze(b4)) {
                        cArr[i] = (char) (((b2 & Ascii.SI) << 12) | ((b3 & 63) << 6) | (b4 & 63));
                        return;
                    }
                } else if (b3 < -96) {
                    b2 = -19;
                    if (!zze(b4)) {
                        cArr[i] = (char) (((b2 & Ascii.SI) << 12) | ((b3 & 63) << 6) | (b4 & 63));
                        return;
                    }
                }
            } else if (b3 >= -96) {
                b2 = -32;
                if (b2 != -19) {
                    if (!zze(b4)) {
                        cArr[i] = (char) (((b2 & Ascii.SI) << 12) | ((b3 & 63) << 6) | (b4 & 63));
                        return;
                    }
                } else if (b3 < -96) {
                    b2 = -19;
                    if (!zze(b4)) {
                        cArr[i] = (char) (((b2 & Ascii.SI) << 12) | ((b3 & 63) << 6) | (b4 & 63));
                        return;
                    }
                }
            }
        }
        throw zzkh.zzc();
    }

    public static /* synthetic */ boolean zzd(byte b2) {
        return b2 >= 0;
    }

    public static boolean zze(byte b2) {
        return b2 > -65;
    }
}
