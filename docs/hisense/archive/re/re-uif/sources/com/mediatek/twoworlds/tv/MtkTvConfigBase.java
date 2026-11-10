package com.mediatek.twoworlds.tv;

import android.util.Log;
import com.mediatek.twoworlds.tv.common.MtkTvConfigTypeBase;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvConfigBase {
    public static final int CFGR_CANT_INIT = -1;
    public static final int CFGR_INV_ARG = -3;
    public static final int CFGR_NOT_INIT = -2;
    public static final int CFGR_NOT_SUPPORT = -4;
    public static final int CFGR_NO_ACTION = 1;
    public static final int CFGR_OK = 0;
    public static final int CFGR_REC_NOT_FOUND = -5;
    public static final String TAG = "TV_MtkTvConfig";
    private String cfgItems = "";
    private int cfgItemsStatus = 0;

    public static int getMaxValue(int i) {
        return (short) ((i & (-65536)) >> 16);
    }

    public static int getMinValue(int i) {
        return (short) (i & 65535);
    }

    public int getMinMaxConfigValue(String str) {
        return getMinMaxConfigValue(-1, str);
    }

    private int getMinMaxConfigValue(int i, String str) {
        return TVNativeWrapper.getMinMaxConfigValue_native(i, str);
    }

    private static String getStringByInt(int i) {
        return String.valueOf(new char[]{(char) (i >> 24), (char) ((i >> 16) & 255), (char) ((i >> 8) & 255)});
    }

    private static int getIntByString(String str) {
        char[] charArray = str.toCharArray();
        if (charArray.length != 3) {
            return 0;
        }
        return (charArray[2] << '\b') | (charArray[0] << 24) | (charArray[1] << 16);
    }

    public String getCountry() {
        return getStringByInt(getConfigValue(MtkTvConfigTypeBase.CFG_COUNTRY_COUNTRY));
    }

    public int setCountry(String str) {
        int intByString = getIntByString(str);
        if (intByString == 0) {
            return -3;
        }
        return setConfigValue(MtkTvConfigTypeBase.CFG_COUNTRY_COUNTRY, intByString);
    }

    public String getLanguage() {
        return getStringByInt(getConfigValue(MtkTvConfigTypeBase.CFG_GUI_LANG_GUI_LANGUAGE));
    }

    public String getLanguage(String str) {
        return getStringByInt(getConfigValue(str));
    }

    public int setLanguage(String str) {
        int intByString = getIntByString(str);
        if (intByString == 0) {
            return -3;
        }
        return setConfigValue(MtkTvConfigTypeBase.CFG_GUI_LANG_GUI_LANGUAGE, intByString);
    }

    public int setLanguage(String str, String str2) {
        int intByString = getIntByString(str2);
        if (intByString == 0) {
            return -3;
        }
        return setConfigValue(str, intByString);
    }

    public int getConfigValue(String str) {
        return getConfigValue(-1, str);
    }

    public int getConfigValue(int i, String str) {
        return TVNativeWrapper.getConfigValue_native(i, str);
    }

    public int setConfigValue(String str, int i) {
        return setConfigValue(-1, str, i, 3);
    }

    public int setConfigValue(String str, int i, int i2) {
        return setConfigValue(-1, str, i, i2);
    }

    public int resetTotalUserWatchTime() {
        return setConfigValue(MtkTvConfigTypeBase.CFG_MISC_TOTAL_USER_WATCH_TIME, 0, 1);
    }

    public int setConfigValue(int i, String str, int i2, int i3) {
        return TVNativeWrapper.setConfigValue_native(i, str, i2, i3);
    }

    public int dumpChannelList(int i) {
        int configString = setConfigString(MtkTvConfigTypeBase.CFG_MISC_CH_PRESET_SET_PATH, "");
        return configString == 0 ? setConfigValue(MtkTvConfigTypeBase.CFG_MISC_CH_PRESET_DUMP, i, 3) : configString;
    }

    public int loadChannelList(int i) {
        int configString = setConfigString(MtkTvConfigTypeBase.CFG_MISC_CH_PRESET_SET_PATH, "");
        return configString == 0 ? setConfigValue(MtkTvConfigTypeBase.CFG_MISC_CH_PRESET_LOAD, i, 3) : configString;
    }

    public int dumpChannelListByPath(int i, String str) {
        if (str == null) {
            return -3;
        }
        int configString = setConfigString(MtkTvConfigTypeBase.CFG_MISC_CH_PRESET_SET_PATH, str);
        return configString == 0 ? setConfigValue(MtkTvConfigTypeBase.CFG_MISC_CH_PRESET_DUMP, i, 3) : configString;
    }

    public int loadChannelListByPath(int i, String str) {
        if (str == null) {
            return -3;
        }
        int configString = setConfigString(MtkTvConfigTypeBase.CFG_MISC_CH_PRESET_SET_PATH, str);
        return configString == 0 ? setConfigValue(MtkTvConfigTypeBase.CFG_MISC_CH_PRESET_LOAD, i, 3) : configString;
    }

    public int convertDrvValueToAppValue(String str, int i) {
        return setConfigValue(str, i, 4);
    }

    public int convertAppValueToDrvValue(String str, int i) {
        return setConfigValue(str, i, 5);
    }

    public int isConfigVisible(String str) {
        return isConfigVisible(-1, str);
    }

    public int isConfigVisible(int i, String str) {
        return TVNativeWrapper.isConfigVisible_native(i, str);
    }

    public int isConfigEnabled(String str) {
        return isConfigEnabled(-1, str);
    }

    public int isConfigEnabled(int i, String str) {
        return TVNativeWrapper.isConfigEnabled_native(i, str);
    }

    public Map<Integer, Boolean> isConfigItemsEnabled(String str) {
        return isConfigItemsEnabled(-1, str);
    }

    private Map<Integer, Boolean> isConfigItemsEnabled(int i, String str) {
        if (TVNativeWrapper.isConfigItemsEnabled_native(i, str, this) == -1) {
            return null;
        }
        String[] strArrSplit = this.cfgItems.split("\\|");
        HashMap map = new HashMap();
        for (int i2 = 0; i2 < strArrSplit.length; i2++) {
            try {
                Integer numValueOf = Integer.valueOf(strArrSplit[i2]);
                if (((1 << i2) & this.cfgItemsStatus) != 0) {
                    map.put(numValueOf, Boolean.TRUE);
                } else {
                    map.put(numValueOf, Boolean.FALSE);
                }
            } catch (Exception unused) {
                return map;
            }
        }
        return map;
    }

    public int resetConfigValues(int i) {
        return TVNativeWrapper.resetConfigValues_native(i);
    }

    public String getConfigString(String str) {
        return TVNativeWrapper.getConfigString_native(str);
    }

    public int setConfigString(String str, String str2) {
        return TVNativeWrapper.setConfigString_native(str, str2);
    }

    public int setConfigStringEx(String str, String str2, int i) {
        Log.d(TAG, "Enter setConfigStringEx\n");
        int configStringEx_native = TVNativeWrapper.setConfigStringEx_native(str, str2, i);
        Log.d(TAG, "Leave setConfigStringEx, return value = " + configStringEx_native + "\n");
        return configStringEx_native;
    }

    public int setPipConfig(int i) {
        return TVNativeWrapper.setPipConfig_native(i);
    }
}
