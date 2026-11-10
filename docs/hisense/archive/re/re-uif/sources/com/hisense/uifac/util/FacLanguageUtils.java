package com.hisense.uifac.util;

import android.content.Context;
import android.util.Log;
import com.hisense.tvcm.FacPropertyDemo;
import com.hisense.uifac.R;
import com.mediatek.twoworlds.tv.MtkTvScanDvbsBase;
import com.mediatek.twoworlds.tv.common.MtkTvConfigTypeBase;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class FacLanguageUtils {
    private static HashMap<String, String> mStrLanMap = new HashMap<>();
    private static Context mContext = null;
    private static String[] mLanguageArray = null;

    public static void initLanguage() {
        Log.d("Fac_FacLanguageUtils", "initLanguage");
        mStrLanMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_ENG, "en-US");
        mStrLanMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_FRE, "fr-CA");
        mStrLanMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_SPA, "es-US");
        mStrLanMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_CHI, "zh-CN");
        mStrLanMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_ZHO, "zh-HK");
        mStrLanMap.put(MtkTvConfigTypeBase.S639_CFG_LANG_GER, "de-DE");
    }

    public static void initLanguageArray(Context context) {
        mContext = context;
        if (FacPropertyDemo.HIFAC_MARKET_MONITOR) {
            mLanguageArray = mContext.getResources().getStringArray(R.array.factorymenu_monitor_language);
        }
        if (FacPropertyDemo.HIFAC_MARKET_HK) {
            mLanguageArray = mContext.getResources().getStringArray(R.array.factorymenu_hk_language);
        }
    }

    public static int getLanguageUIIndexByKey(int i) {
        Log.i("Fac_FacLanguageUtils", "getLanguageKeyByStr key = " + i);
        String[] strArr = mLanguageArray;
        if (strArr != null && strArr.length > 0) {
            int i2 = 0;
            while (true) {
                String[] strArr2 = mLanguageArray;
                if (i2 >= strArr2.length) {
                    break;
                }
                String str = strArr2[i2];
                Log.i("Fac_FacLanguageUtils", "getLanguageKeyByStr languageInfo = " + str);
                int iIndexOf = str.indexOf(MtkTvScanDvbsBase.TVAPI_DVBS_LINK_STRING);
                Log.i("Fac_FacLanguageUtils", "getLanguageKeyByStr j = " + iIndexOf);
                String strSubstring = str.substring(0, iIndexOf);
                Log.i("Fac_FacLanguageUtils", "getLanguageKeyByStr languageKey = " + strSubstring);
                if (Integer.valueOf(strSubstring).intValue() == i) {
                    return i2;
                }
                i2++;
            }
        }
        return 0;
    }

    public static int getLanguagekeyByUIIndex(int i) {
        Log.i("Fac_FacLanguageUtils", "getLanguagekeyByUIIndex index = " + i);
        String[] strArr = mLanguageArray;
        if (strArr == null || strArr.length <= 0) {
            return 0;
        }
        String str = strArr[i];
        Log.i("Fac_FacLanguageUtils", "getLanguagekeyByUIIndex languageInfo = " + str);
        int iIndexOf = str.indexOf(MtkTvScanDvbsBase.TVAPI_DVBS_LINK_STRING);
        Log.i("Fac_FacLanguageUtils", "getLanguagekeyByUIIndex j = " + iIndexOf);
        String strSubstring = str.substring(0, iIndexOf);
        Log.i("Fac_FacLanguageUtils", "getLanguagekeyByUIIndex languageKey = " + strSubstring);
        return Integer.valueOf(strSubstring).intValue();
    }
}
