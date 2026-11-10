package com.hisense.support.tv;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import com.mediatek.twoworlds.tv.MtkTvConfigBase;
import com.mediatek.twoworlds.tv.common.MtkTvConfigTypeBase;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class HisenseCustomerConfig {
    public static final String COUNTRY_URI = "content://com.google.android.tungsten.setupwraith/setup_provider";
    public static final String SELECTED_COUNTRY = "manually_selected_country";
    public static final String TAG = "HisenseCustomerConfig";

    private int getCountryIndex() {
        return new MtkTvConfigBase().getConfigValue(MtkTvConfigTypeBase.CFG_COUNTRY_COUNTRY);
    }

    public static int setCountryIndexToE2prom(Context context) {
        Log.d(TAG, "setCountryIndex, set Locale Country to E2prom start!");
        int configValue = -1;
        try {
            Cursor cursorQuery = context.getContentResolver().query(Uri.parse(COUNTRY_URI), null, null, null);
            if (cursorQuery == null || cursorQuery.getCount() == 0) {
                Log.e(TAG, "cursor is null, or cursor.getCount() is 0!");
            } else {
                cursorQuery.moveToFirst();
                String string = cursorQuery.getString(cursorQuery.getColumnIndex(SELECTED_COUNTRY));
                int index = HisenseCountryLanInfo.CountryCode.getIndex(string);
                configValue = new MtkTvConfigBase().setConfigValue(MtkTvConfigTypeBase.CFG_COUNTRY_COUNTRY, index);
                Log.d(TAG, "set country:" + string + "  Index:" + index + " to EEprom, ret: " + configValue);
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
        }
        return configValue;
    }

    public static int setLanguageIndexToE2prom(Context context) {
        int indexByLocaleLanguage;
        Log.d(TAG, "set Locale Language to E2prom start!");
        try {
            Locale locale = context.getResources().getConfiguration().locale;
            if (locale != null) {
                String language = locale.getLanguage();
                Log.d(TAG, "get Locale Language is " + language);
                if (language.equals("zh")) {
                    indexByLocaleLanguage = locale.equals(Locale.SIMPLIFIED_CHINESE) ? 7 : 16;
                } else {
                    indexByLocaleLanguage = HisenseCountryLanInfo.LanguageCode.getIndexByLocaleLanguage(language);
                }
                Log.d(TAG, "get Language Index of Locale Language is " + indexByLocaleLanguage);
                if (indexByLocaleLanguage > 0) {
                    Log.d(TAG, "set languageIndex to E2prom, ret is -1");
                } else {
                    Log.e(TAG, "get Language Index of Locale Language error!");
                }
            } else {
                Log.e(TAG, "locale is null!");
            }
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
        }
        Log.d(TAG, "set Locale Language to E2prom end!");
        return -1;
    }

    public static int setMtkCountry(String str, int i) {
        if (str == null) {
            return -1;
        }
        try {
            String countryStr = HisenseCountryLanInfo.CountryCode.getCountryStr(Integer.valueOf(str).intValue());
            if (countryStr != null) {
                Log.d(TAG, "set country to MTK, countryStr is " + countryStr);
            } else {
                Log.e(TAG, "countryStr is null");
            }
            return -1;
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public void updateCountry(Context context) {
        Log.d(TAG, "updateCountry");
        ContentValues contentValues = new ContentValues();
        String name = HisenseCountryLanInfo.CountryCode.getName(getCountryIndex());
        Log.d(TAG, "updateCountry countryName = " + name);
        Uri uri = Uri.parse(COUNTRY_URI);
        contentValues.put(SELECTED_COUNTRY, name);
        Cursor cursorQuery = context.getContentResolver().query(uri, null, null, null);
        if (cursorQuery != null) {
            if (cursorQuery.getCount() == 1) {
                context.getContentResolver().update(uri, contentValues, null, null);
            } else {
                context.getContentResolver().insert(uri, contentValues);
            }
            cursorQuery.close();
        }
    }
}
