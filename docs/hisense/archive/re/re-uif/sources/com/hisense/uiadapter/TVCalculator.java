package com.hisense.uiadapter;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class TVCalculator {
    private static TVCalculator mTVCalculator;

    public static TVCalculator getInstance() {
        if (mTVCalculator == null) {
            mTVCalculator = new TVCalculator();
        }
        return mTVCalculator;
    }

    public int convertDataRange(int i, int i2, int i3, int i4, int i5) {
        int i6;
        if (i5 < i) {
            System.out.println("data out of range!");
            return i;
        }
        if (i5 > i2) {
            System.out.println("data out of range!!");
            return i2;
        }
        if ((i != i3 || i2 != i4) && i4 != i3 && i2 != i) {
            int i7 = i2 - i;
            int i8 = i4 - i3;
            if (i7 > i8) {
                int i9 = i7 / i8;
                Log.i("Fac_TVCalculator", "**step = " + i9);
                i6 = (i5 - i) / i9;
            } else {
                int i10 = i8 / i7;
                Log.i("Fac_TVCalculator", "**step = " + i10);
                i6 = (i5 - i) * i10;
            }
            i5 = i6 + i3;
            Log.i("Fac_TVCalculator", "**rValue = " + i5);
            if (i5 < i3) {
                System.out.println("after convert, data out of range!");
                return i3;
            }
            if (i5 > i4) {
                System.out.println("after convert, data out of range!!");
                return i4;
            }
        }
        return i5;
    }
}
