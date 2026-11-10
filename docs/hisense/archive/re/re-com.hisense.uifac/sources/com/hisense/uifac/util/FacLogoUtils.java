package com.hisense.uifac.util;

import android.content.Context;
import android.util.Log;
import com.hisense.tvcm.FacPropertyDemo;
import com.hisense.uifac.R;
import com.mediatek.twoworlds.tv.MtkTvScanDvbsBase;

/* JADX INFO: loaded from: classes.dex */
public class FacLogoUtils {
    private static String TAG = "Fac_FacLogoUtils";
    private static Context mContext;
    private static String[] mLogoArray;

    public static void initLogoArray(Context context) {
        mContext = context;
        if (FacPropertyDemo.HIFAC_MARKET_MONITOR) {
            mLogoArray = mContext.getResources().getStringArray(R.array.factorymenu_global_logo);
        } else if (FacPropertyDemo.HIFAC_PRODUCT_OEM) {
            mLogoArray = mContext.getResources().getStringArray(R.array.factorymenu_us_oem_logo);
        } else if (FacPropertyDemo.HIFAC_MARKET_KO) {
            mLogoArray = mContext.getResources().getStringArray(R.array.facmenu_array_for_kor);
        }
    }

    public static int getLogoUIIndexByKey(int i) {
        Log.i(TAG, "getLogoKeyByStr key = " + i);
        String[] strArr = mLogoArray;
        if (strArr == null || strArr.length <= 0) {
            Log.i(TAG, "getLogoKeyByStr key error = " + i);
            return 0;
        }
        int i2 = 0;
        while (true) {
            String[] strArr2 = mLogoArray;
            if (i2 >= strArr2.length) {
                return 0;
            }
            String str = strArr2[i2];
            Log.i(TAG, "getLogoKeyByStr logoInfo = " + str);
            int iIndexOf = str.indexOf(MtkTvScanDvbsBase.TVAPI_DVBS_LINK_STRING);
            Log.i(TAG, "getLogoKeyByStr j = " + iIndexOf);
            String strSubstring = str.substring(0, iIndexOf);
            Log.i(TAG, "getLogoKeyByStr logoKey = " + strSubstring);
            if (Integer.valueOf(strSubstring).intValue() == i) {
                return i2;
            }
            i2++;
        }
    }

    public static int getLogokeyByUIIndex(int i) {
        Log.i(TAG, "getLogokeyByUIIndex index = " + i);
        String[] strArr = mLogoArray;
        if (strArr == null || strArr.length <= 0) {
            Log.i(TAG, "getLogokeyByUIIndex index error = " + i);
            return 0;
        }
        String str = strArr[i];
        Log.i(TAG, "getLogokeyByUIIndex logoInfo = " + str);
        int iIndexOf = str.indexOf(MtkTvScanDvbsBase.TVAPI_DVBS_LINK_STRING);
        Log.i(TAG, "getLogokeyByUIIndex j = " + iIndexOf);
        String strSubstring = str.substring(0, iIndexOf);
        Log.i(TAG, "getLogokeyByUIIndex logoKey = " + strSubstring);
        return Integer.valueOf(strSubstring).intValue();
    }
}
