package com.mediatek.twoworlds.tv;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import b.a.a.a.a;
import com.mediatek.twoworlds.tv.common.MtkTvConfigTypeBase;
import com.mediatek.twoworlds.tv.model.MtkTvRatingConvert2Goo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes2.dex */
public class VersionPrintingHelper {
    public static final String TAG = "VersionPrintingHelper";
    public final int mPrintCount = 3;

    public VersionPrintingHelper(Context context) {
        Log.i(TAG, "new VersionPrintingHelper...\n");
    }

    private void checkError(Process process) {
        InputStreamReader inputStreamReader = new InputStreamReader(process.getErrorStream());
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        while (true) {
            try {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                } else {
                    Log.i(TAG, line);
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        bufferedReader.close();
        try {
            inputStreamReader.close();
            try {
                bufferedReader.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            inputStreamReader.close();
        } catch (IOException e4) {
            e4.printStackTrace();
        }
    }

    private String getDateInfo(String str) {
        Date date = new Date(Long.parseLong(str));
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Shanghai");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss ");
        simpleDateFormat.setTimeZone(timeZone);
        String str2 = simpleDateFormat.format(date);
        Log.i(TAG, "data time is " + str2);
        int i = Integer.parseInt(str2.substring(0, 4));
        Log.i(TAG, "current year is " + i);
        String str3 = TAG;
        StringBuilder sbA = a.a("current year char is ");
        char c2 = (char) (((i + (-2010)) % 26) + 65);
        sbA.append(c2);
        Log.i(str3, sbA.toString());
        StringBuilder sbA2 = a.a("" + c2);
        sbA2.append(str2.substring(5, 7));
        sbA2.append(str2.substring(8, 10));
        return sbA2.toString();
    }

    private void print2Console(String str) {
        Process processExec;
        try {
            Log.i(TAG, "echo 'hs>>" + str + "' >> /dev/ttyMT0");
            processExec = Runtime.getRuntime().exec(new String[]{"sh", "-c", "echo 'hs>>" + str + "' >> /dev/ttyMT0"});
        } catch (IOException e2) {
            e2.printStackTrace();
            processExec = null;
        }
        checkError(processExec);
        try {
            processExec.waitFor();
        } catch (InterruptedException e3) {
            e3.printStackTrace();
        }
    }

    public long getBuildTimeValue() {
        long j = Build.TIME;
        String str = android.os.SystemProperties.get("ro.build.date.utc");
        long j2 = (str == null || str.length() == 0) ? 0L : Long.parseLong(str) * 1000;
        return j >= j2 ? j : j2;
    }

    public char getCompileHour() {
        Date date = new Date(Build.TIME);
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Shanghai");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH");
        simpleDateFormat.setTimeZone(timeZone);
        String str = TAG;
        StringBuilder sbA = a.a("getCompileHour, sdf.format(d): ");
        sbA.append(simpleDateFormat.format(date));
        Log.i(str, sbA.toString());
        int i = Integer.parseInt(simpleDateFormat.format(date));
        Log.i(TAG, "getCompileHour, time: " + i);
        char[] cArr = new char[24];
        for (int i2 = 0; i2 <= 23; i2++) {
            cArr[i2] = (char) (i2 + 65);
        }
        return cArr[i];
    }

    public String getSoftwareVersion(String str, String str2) {
        String str3;
        StringBuffer stringBuffer = new StringBuffer(str);
        int i = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (i2 > 0) {
                char cCharAt = str.charAt(i2 - 1);
                char cCharAt2 = str.charAt(i2);
                if (Character.isDigit(cCharAt) && Character.isUpperCase(cCharAt2)) {
                    stringBuffer.insert(i2 + i, ".");
                    i++;
                }
            }
        }
        int iIndexOf = stringBuffer.indexOf(".");
        String strSubstring = "00";
        if (-1 != iIndexOf) {
            str = stringBuffer.substring(0, iIndexOf);
            int i3 = 0;
            for (int i4 = 0; i4 < str.length(); i4++) {
                if (Character.isUpperCase(str.charAt(i4))) {
                    i3++;
                }
            }
            if (i3 >= 2) {
                str = str.replaceAll("L", "").replaceAll(MtkTvRatingConvert2Goo.SUB_RATING_STR_D, "");
            }
            Log.i(TAG, "prefixStr: " + str);
            String strSubstring2 = stringBuffer.substring(iIndexOf + 1, stringBuffer.length());
            int iIndexOf2 = strSubstring2.indexOf(".");
            if (iIndexOf2 != -1) {
                strSubstring2 = strSubstring2.substring(0, iIndexOf2);
                Log.i(TAG, "func1Str: " + strSubstring2);
                strSubstring = stringBuffer.substring(iIndexOf + 2 + iIndexOf2, stringBuffer.length());
                Log.i(TAG, "func2Str: " + strSubstring);
            } else {
                Log.i(TAG, "func1Str: " + strSubstring2);
                Log.i(TAG, "func2Str: 00");
            }
            str3 = strSubstring;
            strSubstring = strSubstring2;
        } else {
            Log.i(TAG, "prefixStr: " + str);
            Log.i(TAG, "func1Str: 00");
            Log.i(TAG, "func2Str: 00");
            str3 = "00";
        }
        int iIndexOf3 = str2.indexOf(".");
        String strSubstring3 = str2.substring(0, iIndexOf3);
        Log.i(TAG, "derivedModels: " + strSubstring3);
        String strSubstring4 = str2.substring(iIndexOf3 + 1, str2.length());
        Log.i(TAG, "versionNumber: " + strSubstring4);
        String str4 = android.os.SystemProperties.get("ro.vt.build.softdate");
        Log.i(TAG, "formatDate: " + str4);
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(".");
        sb.append(strSubstring3);
        a.a(sb, ".", strSubstring, ".", str3);
        sb.append(".");
        sb.append(strSubstring4);
        sb.append(".");
        sb.append(str4);
        return sb.toString();
    }

    public void showVersionInfo() {
        String softwareVersion;
        Log.i(TAG, "showVersionInfo...\n");
        String str = android.os.SystemProperties.get("ro.vt.product.model");
        String str2 = android.os.SystemProperties.get("ro.vt.build.softversion");
        Log.i(TAG, "productName: " + str);
        Log.i(TAG, "softVersion: " + str2);
        if (str2 == null || str2.length() == 0) {
            softwareVersion = "";
        } else {
            softwareVersion = getSoftwareVersion(str, str2);
            Log.i(TAG, "ownSoftVersion: " + softwareVersion);
        }
        String strA = a.a("swver-", softwareVersion, "\r\n");
        for (int i = 0; i < 3; i++) {
            print2Console(strA);
        }
        MtkTvConfig.getInstance().getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_PROTECT_FLAG);
        try {
            Thread.sleep(200L);
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
        for (int i2 = 0; i2 < 3; i2++) {
            print2Console("in-clear\r\n");
        }
        Log.e(TAG, "CFG_FACTORY_TYPE_PROTECTED_CLEAN: 1");
        try {
            Thread.sleep(200L);
        } catch (InterruptedException e3) {
            e3.printStackTrace();
        }
        for (int i3 = 0; i3 < 3; i3++) {
            print2Console("clear-ok\r\n");
        }
        int configValue = MtkTvConfig.getInstance().getConfigValue(MtkTvConfigTypeBase.CFG_SYSTEM_UPGRADE_FLAG);
        Log.i(TAG, "upgrade_flag: " + configValue);
        if (configValue == 1) {
            int configValue2 = MtkTvConfig.getInstance().setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_TOFAC_MODE, 1);
            Log.i(TAG, "relValue: " + configValue2);
        }
    }

    public VersionPrintingHelper() {
        Log.i(TAG, "new VersionPrintingHelper...\n");
    }
}
