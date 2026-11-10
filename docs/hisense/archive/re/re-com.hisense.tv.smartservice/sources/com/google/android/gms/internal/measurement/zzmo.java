package com.google.android.gms.internal.measurement;

import com.google.common.base.Ascii;

/* JADX INFO: loaded from: classes.dex */
public final class zzmo extends zzmn {
    @Override // com.google.android.gms.internal.measurement.zzmn
    public final int zza(int i, byte[] bArr, int i2, int i3) {
        while (i2 < i3 && bArr[i2] >= 0) {
            i2++;
        }
        int iZza = 0;
        if (i2 < i3) {
            while (i2 < i3) {
                int i4 = i2 + 1;
                byte b2 = bArr[i2];
                if (b2 < 0) {
                    if (b2 < -32) {
                        if (i4 >= i3) {
                            return b2;
                        }
                        if (b2 < -62) {
                            return -1;
                        }
                        i2 = i4 + 1;
                        if (bArr[i4] > -65) {
                            return -1;
                        }
                    } else if (b2 < -16) {
                        if (i4 >= i3 - 1) {
                            iZza = zzmq.zza(bArr, i4, i3);
                            break;
                        }
                        int i5 = i4 + 1;
                        byte b3 = bArr[i4];
                        if (b3 > -65) {
                            return -1;
                        }
                        if (b2 == -32 && b3 < -96) {
                            return -1;
                        }
                        if (b2 == -19 && b3 >= -96) {
                            return -1;
                        }
                        i2 = i5 + 1;
                        if (bArr[i5] > -65) {
                            return -1;
                        }
                    } else {
                        if (i4 >= i3 - 2) {
                            iZza = zzmq.zza(bArr, i4, i3);
                            break;
                        }
                        int i6 = i4 + 1;
                        byte b4 = bArr[i4];
                        if (b4 > -65) {
                            return -1;
                        }
                        if ((((b4 + 112) + (b2 << Ascii.FS)) >> 30) != 0) {
                            return -1;
                        }
                        int i7 = i6 + 1;
                        if (bArr[i6] > -65) {
                            return -1;
                        }
                        i4 = i7 + 1;
                        if (bArr[i7] > -65) {
                            return -1;
                        }
                    }
                }
                i2 = i4;
            }
        }
        return iZza;
    }
}
