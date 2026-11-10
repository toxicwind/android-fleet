package com.hisense.uifac.util;

import android.content.Context;
import android.util.Log;
import com.hisense.tvcm.FacPropertyDemo;
import com.hisense.uifac.R;

/* JADX INFO: loaded from: classes.dex */
public class FacCountryUtils {
    private static Context mContext = null;
    private static String[] mCountryArray = null;
    private static int naResId = 2130837638;

    public static void setContext(Context context) {
        mContext = context;
    }

    public static void loadCountry() {
        if (FacPropertyDemo.HIFAC_MARKET == FacPropertyDemo.MarketType.US) {
            naResId = R.array.factorymenu_country;
        }
        if (FacPropertyDemo.HIFAC_MARKET_MONITOR) {
            naResId = R.array.factorymenu_local_monitor;
        }
        mCountryArray = mContext.getResources().getStringArray(naResId);
    }

    public static int indexOfCountry(int i) {
        Log.d("Fac_FacCountryUtils", "indexOfCountry key = " + i);
        String[] strArr = mCountryArray;
        if (strArr != null && strArr.length > 0) {
            int i2 = 0;
            while (true) {
                String[] strArr2 = mCountryArray;
                if (i2 >= strArr2.length) {
                    break;
                }
                String[] strArrSplit = strArr2[i2].split(" ");
                if (strArrSplit != null) {
                    Log.d("Fac_FacCountryUtils", "indexOfCountry contryContent[0] = " + strArrSplit[0]);
                    Integer numValueOf = Integer.valueOf(strArrSplit[0]);
                    Log.d("Fac_FacCountryUtils", "indexOfCountry countryKey = " + numValueOf);
                    if (numValueOf.intValue() == i) {
                        return i2;
                    }
                }
                i2++;
            }
        }
        return 0;
    }

    public static int keyByPosition(int i) {
        Log.d("Fac_FacCountryUtils", "keyByPosition key = " + i);
        String[] strArr = mCountryArray;
        if (strArr == null || strArr.length <= i) {
            return 0;
        }
        String strSubstring = mCountryArray[i].substring(0, strArr[i].indexOf(" "));
        Log.d("Fac_FacCountryUtils", "keyByPosition keyStr = " + strSubstring);
        return Integer.valueOf(strSubstring).intValue();
    }
}
