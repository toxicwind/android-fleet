package com.hisense.support.tv;

import android.content.Context;
import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;

/* JADX INFO: loaded from: classes2.dex */
public class HisenseTvAPITimeManagement extends HisenseTvAPIBase {
    public static HisenseTvAPITimeManagement hisenseTvAPITimeManagement;
    public int Grgday;
    public int Grgmonth;
    public int Grgyear;
    public int HSHDay;
    public int HSHMonth;
    public int HSHYear;
    public static final int[][] grgSumOfDays = {new int[]{0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365}, new int[]{0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335, 366}};
    public static final int[][] hshSumOfDays = {new int[]{0, 31, 62, 93, 124, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_BUF_FULL, 186, 216, 246, 276, 306, 336, 365}, new int[]{0, 31, 62, 93, 124, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_BUF_FULL, 186, 216, 246, 276, 306, 336, 366}};

    public HisenseTvAPITimeManagement(Context context) {
        super(context);
        this.HSHYear = 1970;
        this.HSHMonth = 1;
        this.HSHDay = 1;
        this.Grgyear = 1378;
        this.Grgmonth = 1;
        this.Grgday = 1;
    }

    public static HisenseTvAPITimeManagement getInstance(Context context) {
        HisenseTvAPITimeManagement hisenseTvAPITimeManagement2 = hisenseTvAPITimeManagement;
        if (hisenseTvAPITimeManagement2 != null) {
            return hisenseTvAPITimeManagement2;
        }
        hisenseTvAPITimeManagement = new HisenseTvAPITimeManagement(context);
        return hisenseTvAPITimeManagement;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v7 */
    public boolean ToGregorian() {
        int i;
        int i2;
        int i3;
        ?? GrgIsLeap;
        int i4 = this.Grgyear;
        int i5 = 0;
        if (i4 < 1378 || (i = this.Grgmonth) < 1 || (i2 = this.Grgday) < 1) {
            return false;
        }
        int i6 = i4 + 621;
        boolean zHshIsLeap = hshIsLeap(i4);
        boolean zGrgIsLeap = grgIsLeap(i6);
        int i7 = hshSumOfDays[zHshIsLeap ? 1 : 0][i - 1] + i2;
        if (i > 10 || (i == 10 && i7 > (zGrgIsLeap ? 1 : 0) + 286)) {
            i3 = i7 - ((zGrgIsLeap ? 1 : 0) + 286);
            i6++;
            GrgIsLeap = grgIsLeap(i6);
        } else {
            i3 = ((i7 + 79) + (hshIsLeap(i4 - 1) ? 1 : 0)) - (grgIsLeap(i6 + (-1)) ? 1 : 0);
            GrgIsLeap = zGrgIsLeap;
        }
        int i8 = 1;
        while (i8 <= 12) {
            int[][] iArr = grgSumOfDays;
            if (iArr[GrgIsLeap][i8] >= i3) {
                i5 = i3 - iArr[GrgIsLeap][i8 - 1];
                this.Grgyear = i6;
                this.Grgmonth = i8;
                this.Grgday = i5;
                return true;
            }
            i8++;
        }
        i8 = 0;
        this.Grgyear = i6;
        this.Grgmonth = i8;
        this.Grgday = i5;
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6 */
    public boolean Toshamsi() {
        int i;
        int i2;
        int i3;
        ?? HshIsLeap;
        int i4 = this.HSHYear;
        int i5 = 0;
        if (i4 < 2000 || (i = this.HSHMonth) < 1 || (i2 = this.HSHDay) < 1) {
            return false;
        }
        int i6 = i4 - 621;
        boolean zGrgIsLeap = grgIsLeap(i4);
        int i7 = i6 - 1;
        boolean zHshIsLeap = hshIsLeap(i7);
        int i8 = grgSumOfDays[zGrgIsLeap ? 1 : 0][i - 1] + i2;
        int i9 = (hshIsLeap(i7) && grgIsLeap(i4)) ? 80 : 79;
        if (i8 <= i9) {
            i3 = i8 + 286;
            i6--;
            if (zHshIsLeap && !zGrgIsLeap) {
                i3++;
                HshIsLeap = zHshIsLeap;
            }
        } else {
            i3 = i8 - i9;
            HshIsLeap = hshIsLeap(i6);
        }
        HshIsLeap = zHshIsLeap;
        HshIsLeap = zHshIsLeap;
        int i10 = 1;
        while (i10 <= 12) {
            int[][] iArr = hshSumOfDays;
            if (iArr[HshIsLeap][i10] >= i3) {
                i5 = i3 - iArr[HshIsLeap][i10 - 1];
                this.HSHYear = i6;
                this.HSHMonth = i10;
                this.HSHDay = i5;
                return true;
            }
            i10++;
        }
        i10 = 0;
        this.HSHYear = i6;
        this.HSHMonth = i10;
        this.HSHDay = i5;
        return true;
    }

    public boolean grgIsLeap(int i) {
        return i % 4 == 0 && (i % 100 != 0 || i % 400 == 0);
    }

    public int hshDayOfWeek(int i, int i2, int i3) {
        int i4 = (((i - 1376) + hshSumOfDays[0][i2 - 1]) + i3) - 1;
        for (int i5 = 1380; i5 < i; i5++) {
            if (hshIsLeap(i5)) {
                i4++;
            }
        }
        while (i < 1380) {
            if (hshIsLeap(i)) {
                i4--;
            }
            i++;
        }
        int i6 = i4 % 7;
        return i6 < 0 ? i6 + 7 : i6;
    }

    public boolean hshIsLeap(int i) {
        int i2 = (i - 474) % 128;
        int i3 = (i2 >= 30 ? 0 : 29) + i2;
        return ((int) ((((double) i3) - Math.floor((double) (i3 / 33))) - 1.0d)) % 4 == 0;
    }
}
