package com.google.android.gms.common.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.common.net.InetAddresses;

/* JADX INFO: loaded from: classes.dex */
@KeepForSdk
public final class HexDumpUtils {
    @Nullable
    @KeepForSdk
    public static String dump(@NonNull byte[] bArr, int i, int i2, boolean z) {
        int length;
        if (bArr == null || (length = bArr.length) == 0 || i < 0 || i2 <= 0 || i + i2 > length) {
            return null;
        }
        StringBuilder sb = new StringBuilder(((i2 + 15) / 16) * (z ? 75 : 57));
        int i3 = i;
        int i4 = i2;
        int i5 = 0;
        int i6 = 0;
        while (i4 > 0) {
            if (i5 == 0) {
                if (i2 < 65536) {
                    sb.append(String.format("%04X:", Integer.valueOf(i3)));
                } else {
                    sb.append(String.format("%08X:", Integer.valueOf(i3)));
                }
                i6 = i3;
            } else if (i5 == 8) {
                sb.append(" -");
            }
            sb.append(String.format(" %02X", Integer.valueOf(bArr[i3] & 255)));
            i4--;
            i5++;
            if (z && (i5 == 16 || i4 == 0)) {
                int i7 = 16 - i5;
                if (i7 > 0) {
                    for (int i8 = 0; i8 < i7; i8++) {
                        sb.append("   ");
                    }
                }
                if (i7 >= 8) {
                    sb.append("  ");
                }
                sb.append("  ");
                for (int i9 = 0; i9 < i5; i9++) {
                    char c2 = (char) bArr[i6 + i9];
                    char c3 = InetAddresses.IPV4_DELIMITER;
                    if (c2 >= ' ' && c2 <= '~') {
                        c3 = c2;
                    }
                    sb.append(c3);
                }
            }
            if (i5 == 16 || i4 == 0) {
                sb.append('\n');
                i5 = 0;
            }
            i3++;
        }
        return sb.toString();
    }
}
