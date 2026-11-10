package com.hmct.cloud.sdk.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.google.common.base.Ascii;
import com.google.firebase.installations.Utils;
import com.mediatek.twoworlds.tv.MtkTvScanDvbsBase;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.UUID;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceConfigNew {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f2078a = "007";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static String f2079b = "02:00:00:00:00:00";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static String f2080c = "00:00:00:00:00:00";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static String f2081d = "0123456789ABCDEFGHJKLMNPQRSTUVWXYZ";

    public static String a(String str) {
        return TextUtils.isEmpty(str) ? "" : str.replaceAll("[^a-zA-Z0-9\\u4E00-\\u9FA5]", "").trim().toLowerCase();
    }

    public static String b(String str) {
        return !TextUtils.isEmpty(str) ? str.split(",")[0] : str;
    }

    public static final void c(String str) {
        SDKUtil.LogD("DeviceConfig", "[PhoneUtils] " + str);
    }

    public static String d(String str) throws IOException {
        StringBuffer stringBuffer = new StringBuffer(1000);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(str));
        char[] cArr = new char[1024];
        while (true) {
            int i = bufferedReader.read(cArr);
            if (i == -1) {
                bufferedReader.close();
                return stringBuffer.toString();
            }
            stringBuffer.append(String.valueOf(cArr, 0, i));
        }
    }

    public static String getBcCode(Context context) {
        Cursor cursorQuery = context.getContentResolver().query(Uri.parse("content://com.hmct.cloud.sdk.account/bcCode/"), null, null, null, null);
        String str = "";
        if (cursorQuery != null) {
            if (cursorQuery.moveToFirst()) {
                String string = cursorQuery.getString(cursorQuery.getColumnIndex("BcCode"));
                if (SDKUtil.isRegularBcode(string)) {
                    str = string;
                }
            }
            cursorQuery.close();
        }
        return str;
    }

    public static String getCVTEDeviceID(Context context) {
        StringBuilder sbB = b.a.a.a.a.b("863f00001009", "100900000000");
        sbB.append(getWiredMacAddress(context));
        return sbB.toString().toLowerCase();
    }

    public static String getChinaNetCenterDeviceID(Context context) {
        StringBuilder sbB = b.a.a.a.a.b("863f00001003", "100300000000");
        sbB.append(getWiredMacAddress(context));
        return sbB.toString().toLowerCase();
    }

    public static String getChinaNetPlayDeviceID(Context context) {
        StringBuilder sbB = b.a.a.a.a.b("863f00001007", "100700000000");
        sbB.append(getWiredMacAddress(context));
        return sbB.toString().toLowerCase();
    }

    public static String getDangBeiDeviceID(Context context) {
        StringBuilder sbB = b.a.a.a.a.b("863f00001025", "102500000000");
        sbB.append(getMacAddress(context));
        return sbB.toString().toLowerCase();
    }

    public static final String getDeviceId(Context context) {
        String strB;
        Log.i("DeviceConfig", "getDeviceId()");
        String prop = null;
        if ("1".equals(getProp("ro.his.androidtv"))) {
            String prop2 = getProp("ro.product.hitdeviceprefix");
            if (TextUtils.isEmpty(prop2)) {
                return null;
            }
            String prop3 = getProp("persist.sys.devicecode");
            if (TextUtils.isEmpty(prop3)) {
                return null;
            }
            return (prop2 + prop3).toLowerCase();
        }
        String prop4 = getProp("ro.product.hitdeviceprefix");
        if (TextUtils.isEmpty(prop4)) {
            if ("PPTV".equals(getProp("ro.product.manufacturer"))) {
                prop = getProp("persist.sys.ChannelROM");
                if (TextUtils.isEmpty(prop)) {
                    prop = getProp("ro.product.channel");
                }
            }
            if (!TextUtils.isEmpty(prop)) {
                prop4 = "863f000010" + prop + "00000000";
            }
            if (TextUtils.isEmpty(prop4)) {
                prop4 = "862fff00fffefff00000fffe";
                String strA = com.hmct.cloud.sdk.a.a.a(context);
                if (TextUtils.isEmpty(strA)) {
                    strA = a(context);
                    com.hmct.cloud.sdk.a.a.a(context, strA);
                }
                strB = strA;
            } else {
                strB = c(context);
            }
        } else {
            strB = prop4.substring(3, 6).equals(f2078a) ? b(context) : c(context);
        }
        return (prop4 + a(strB, Constants.LANGUAGE_FRENCH.equals(String.valueOf(prop4.charAt(2))) ? 32 : 8)).toLowerCase();
    }

    public static final String getDomainName() {
        String prop = getProp("ro.domain.name");
        if (TextUtils.isEmpty(prop)) {
            prop = getProp("sys.domain.name");
        }
        return TextUtils.isEmpty(prop) ? Constants.DEFAULT_DOMAINNAME : prop;
    }

    public static final String getFeatureCode() {
        return getProp("ro.product.hitdeviceprefix");
    }

    public static String getHeNanTelecomDeviceID(Context context) {
        StringBuilder sbB = b.a.a.a.a.b("863f00001002", "100200000000");
        sbB.append(getWiredMacAddress(context));
        return sbB.toString().toLowerCase();
    }

    public static String[] getIPTVDeviceIdAndSN(Context context) {
        if (context == null) {
            return null;
        }
        Cursor cursorQuery = context.getContentResolver().query(Uri.parse("content://com.hisense.hsmware.signon/deviceinfo/"), null, null, null, null);
        if (cursorQuery == null || !cursorQuery.moveToFirst() || cursorQuery.getCount() <= 0) {
            return null;
        }
        String[] strArr = new String[2];
        StringBuilder sb = new StringBuilder();
        String string = cursorQuery.getString(cursorQuery.getColumnIndex("OperatorPlatform"));
        String string2 = cursorQuery.getString(cursorQuery.getColumnIndex("Operator"));
        String string3 = cursorQuery.getString(cursorQuery.getColumnIndex("Servicenumber"));
        if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2) || TextUtils.isEmpty(string3)) {
            return null;
        }
        b.a.a.a.a.a(sb, "863f0000", string, string2, "00000000");
        sb.append(getWiredMacAddress(context));
        strArr[0] = sb.toString().toLowerCase();
        strArr[1] = b.a.a.a.a.a(string, string2, string3);
        strArr[1] = strArr[1].toLowerCase();
        return strArr;
    }

    public static String getJimiDeviceID(Context context) {
        StringBuilder sbB = b.a.a.a.a.b("863f00001029", "102900000000");
        sbB.append(getMacAddress(context));
        return sbB.toString().toLowerCase();
    }

    public static String getMacAddress(Context context) {
        String strB = com.hmct.cloud.sdk.a.a.b(context);
        if (!TextUtils.isEmpty(strB)) {
            return strB;
        }
        try {
            strB = d("/sys/class/net/eth0/address").substring(0, 17);
            Log.e("DeviceConfig", "mac_tmp" + strB);
        } catch (IOException e2) {
            Log.e("DeviceConfig", e2.getMessage());
            e2.printStackTrace();
        }
        if (TextUtils.isEmpty(strB) || "00:00:00:00:00:00".endsWith(strB)) {
            String wifiMacAddress = getWifiMacAddress(context);
            return !TextUtils.isEmpty(wifiMacAddress) ? wifiMacAddress : "";
        }
        com.hmct.cloud.sdk.a.a.b(context, strB);
        return strB;
    }

    public static String getModel() {
        String prop = getProp("ro.hmct.internal.model");
        if (!TextUtils.isEmpty(prop)) {
            return prop;
        }
        String prop2 = getProp("ro.hmct.ota.product");
        if (!TextUtils.isEmpty(prop2)) {
            return prop2;
        }
        String prop3 = getProp("ro.hmct.product.fullname");
        return TextUtils.isEmpty(prop3) ? Build.MODEL : prop3;
    }

    public static final String getPhoneDeviceId(String str, String str2) {
        String strReplace = ("86300b" + str).trim().replace(Ascii.CASE_MASK, '-');
        return ((strReplace.length() >= 24 ? strReplace.substring(0, 24) : c(strReplace, 24)) + str2).toLowerCase();
    }

    public static final String getPhoneFeatureCode() {
        String prop = getProp("ro.hmct.internal.model");
        if (TextUtils.isEmpty(prop)) {
            prop = getProp("ro.hmct.ota.product");
            if (TextUtils.isEmpty(prop)) {
                prop = getProp("ro.hmct.product.fullname");
                if (TextUtils.isEmpty(prop)) {
                    prop = Build.MODEL;
                    if (TextUtils.isEmpty(prop)) {
                        return null;
                    }
                }
            }
        }
        String strReplace = ("86300b" + prop).trim().replace(Ascii.CASE_MASK, '-');
        return strReplace.length() >= 24 ? strReplace.substring(0, 24) : c(strReplace, 24);
    }

    public static String getPhoneInfo(String str) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            String str2 = (String) cls.getMethod("get", String.class, String.class).invoke(cls, str, null);
            c(str + Utils.APP_ID_IDENTIFICATION_SUBSTRING + str2);
            if (str2 == null || str2.isEmpty()) {
                return null;
            }
            return str2;
        } catch (Exception e2) {
            c(e2.getMessage());
            return null;
        }
    }

    public static String getPhoneMeid(Context context, boolean z) {
        if (!z || Build.VERSION.SDK_INT > 28) {
            String strB = b(getPhoneInfo("gsm.phone.imei"));
            SDKUtil.LogD("DeviceConfig", "getPhoneMeid() 11 Imei = " + strB);
            return (TextUtils.isEmpty(strB) || strB.matches("[0]+")) ? "" : strB;
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        String strA = a(telephonyManager);
        if (strA == null || strA.matches("[0]+")) {
            strA = getPhoneInfo("gsm.phone.meid");
            c("getMeid return=" + strA);
        }
        if ((strA == null || strA.matches("[0]+")) && telephonyManager != null) {
            strA = telephonyManager.getDeviceId();
        }
        SDKUtil.LogD("DeviceConfig", "getPhoneMeid() 22 Imei = " + strA);
        return strA;
    }

    public static String getProduct() {
        String phoneInfo = getPhoneInfo("ro.hmct.ota.product");
        if (phoneInfo == null) {
            phoneInfo = getPhoneInfo("ro.ota.product");
        }
        return phoneInfo == null ? Build.MODEL : phoneInfo;
    }

    public static final String getProp(String str) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class).invoke(null, str);
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String getQiPoDeviceID(Context context) {
        StringBuilder sbB = b.a.a.a.a.b("863f00001027", "102700000000");
        sbB.append(getMacAddress(context));
        return sbB.toString().toLowerCase();
    }

    public static String getSerialPhoneNo(Context context, boolean z) {
        String str = getPhoneDeviceId(context, z) + "||";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            return getString(messageDigest.digest(str.getBytes()));
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String getSerialTVNo(Context context) {
        return Md5.md5(getDeviceId(context) + "|" + getWiredMacAddress(context) + "|" + getBcCode(context));
    }

    public static String getShafaGJDeviceID(Context context) {
        StringBuilder sbB = b.a.a.a.a.b("863f00001026", "102600000000");
        sbB.append(getMacAddress(context));
        return sbB.toString().toLowerCase();
    }

    public static String getString(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b2 : bArr) {
            stringBuffer.append((int) b2);
        }
        return stringBuffer.toString();
    }

    public static final String getThirdTVDeviceId(Context context) {
        return ("86200300fffefff00000fffe" + a(a(context), Constants.LANGUAGE_FRENCH.equals(String.valueOf("86200300fffefff00000fffe".charAt(2))) ? 32 : 8)).toLowerCase();
    }

    public static String getTmallBoxDeviceID(Context context) {
        StringBuilder sbB = b.a.a.a.a.b("863f00001001", "100100000000");
        sbB.append(getWiredMacAddress(context));
        return sbB.toString().toLowerCase();
    }

    public static String getUniqueID(Context context) {
        String strD;
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        if (TextUtils.isEmpty(string) || "9774d56d682e549c".equals(string)) {
            strD = null;
        } else {
            try {
                strD = UUID.nameUUIDFromBytes(string.getBytes("utf8")).toString();
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
                strD = null;
            }
        }
        if (TextUtils.isEmpty(strD)) {
            strD = com.hmct.cloud.sdk.a.a.d(context);
        }
        if (TextUtils.isEmpty(strD)) {
            strD = com.hmct.cloud.sdk.a.a.d(context);
        }
        return strD.replace(MtkTvScanDvbsBase.TVAPI_DVBS_LINK_STRING, "");
    }

    public static String getWifiMacAddress(Context context) {
        String strA;
        try {
            int i = Build.VERSION.SDK_INT;
            int i2 = Build.VERSION.SDK_INT;
            strA = a();
        } catch (IOException e2) {
            e2.printStackTrace();
            strA = null;
        }
        return strA == null ? "" : strA;
    }

    public static String getWiredMacAddress(Context context) {
        String wifiMacAddress;
        try {
            wifiMacAddress = d("/sys/class/net/eth0/address").substring(0, 17);
        } catch (IOException e2) {
            e2.printStackTrace();
            wifiMacAddress = null;
        }
        if (TextUtils.isEmpty(wifiMacAddress)) {
            wifiMacAddress = getWifiMacAddress(context);
        }
        return !TextUtils.isEmpty(wifiMacAddress) ? wifiMacAddress.replace(Utils.APP_ID_IDENTIFICATION_SUBSTRING, "").toLowerCase() : "";
    }

    public static String getZaoYangTelecomDeviceID(Context context) {
        StringBuilder sbB = b.a.a.a.a.b("863f00001006", "100600000000");
        sbB.append(getWiredMacAddress(context));
        return sbB.toString().toLowerCase();
    }

    public static final void setDevSerial(String str) {
        setProp("persist.sys.devicecode", str);
    }

    public static final void setProp(String str, String str2) {
        try {
            Class.forName("android.os.SystemProperties").getMethod("set", String.class, String.class).invoke(null, str, str2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static String c(Context context) {
        String hexString;
        String prop = getProp("ro.product.hitdeviceseq");
        if (TextUtils.isEmpty(prop)) {
            prop = getProp("sys.product.hitdeviceseq");
        }
        if (TextUtils.isEmpty(prop)) {
            hexString = getProp("ro.mos.uuid");
            if (TextUtils.isEmpty(hexString)) {
                String macAddress = getMacAddress(context);
                hexString = !TextUtils.isEmpty(macAddress) ? a(macAddress.replace(Utils.APP_ID_IDENTIFICATION_SUBSTRING, "").toLowerCase(), 8) : "00000000";
            }
        } else {
            String strA = a(prop, 9);
            int iIndexOf = f2081d.indexOf(strA.charAt(0));
            int iIndexOf2 = f2081d.indexOf(strA.charAt(1));
            int iIndexOf3 = f2081d.indexOf(strA.charAt(2));
            int iIndexOf4 = f2081d.indexOf(strA.charAt(3));
            int iIndexOf5 = f2081d.indexOf(strA.charAt(4));
            int iIndexOf6 = f2081d.indexOf(strA.charAt(5));
            long j = (((long) (((iIndexOf3 - 1) + ((iIndexOf2 - 1) * 31) + ((iIndexOf - 1) * 372)) * 34 * 34)) * 10000) + (((long) ((iIndexOf4 * 34) + iIndexOf5)) * 10000);
            hexString = Long.toHexString(j + ((long) ((f2081d.indexOf(strA.charAt(7)) * 10) + (f2081d.indexOf(strA.charAt(6)) * 100) + (iIndexOf6 * 1000) + f2081d.indexOf(strA.charAt(8)))));
            Log.i("DeviceConfig", "devicecod from getDeviceCodeBySeq ====" + hexString);
        }
        SDKUtil.LogD("DeviceConfig", "getDeviceCode:= " + hexString);
        return hexString;
    }

    public static String a(TelephonyManager telephonyManager) {
        int i = Build.VERSION.SDK_INT;
        String str = null;
        if (telephonyManager == null) {
            return null;
        }
        try {
            String str2 = (String) telephonyManager.getClass().getMethod("getImei", Integer.TYPE).invoke(telephonyManager, 0);
            try {
                c("getImei return=" + str2);
                return str2;
            } catch (Exception e2) {
                str = str2;
                e = e2;
                e.printStackTrace();
                c("Exception=" + e.toString());
                return str;
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    public static String b(Context context) {
        TelephonyManager telephonyManager;
        Log.i("DeviceConfig", "getSerialNO() Build.VERSION.SDK_INT = " + Build.VERSION.SDK_INT);
        if (Build.VERSION.SDK_INT > 28) {
            return com.hmct.cloud.sdk.a.a.d(context);
        }
        try {
            telephonyManager = (TelephonyManager) context.getSystemService("phone");
        } catch (Exception e2) {
            e2.printStackTrace();
            telephonyManager = null;
        }
        if (telephonyManager != null) {
            return telephonyManager.getDeviceId();
        }
        return null;
    }

    public static String a(Context context) {
        String macAddress = getMacAddress(context);
        if (!TextUtils.isEmpty(macAddress) && !f2079b.equals(macAddress) && !f2080c.equals(macAddress)) {
            String lowerCase = macAddress.replace(Utils.APP_ID_IDENTIFICATION_SUBSTRING, "").toLowerCase();
            SDKUtil.LogD("DeviceConfig", "22 getThirdDeviceCode:= " + lowerCase);
            return a(a(Build.BRAND) + a(Build.MODEL) + lowerCase, 32);
        }
        String strD = com.hmct.cloud.sdk.a.a.d(context);
        SDKUtil.LogD("DeviceConfig", "11 getThirdDeviceCode:= " + strD);
        return a(strD, 32).replaceFirst("000", "xyz");
    }

    public static final String getPhoneDeviceId(Context context, boolean z) {
        String strC;
        boolean z2;
        String strA;
        String strB;
        String prop = getProp("ro.product.hitdeviceprefix");
        Log.i("DeviceConfig", "getPhoneDeviceId() prefix = " + prop);
        if (!TextUtils.isEmpty(prop)) {
            return getDeviceId(context);
        }
        String prop2 = getProp("ro.hmct.internal.model");
        if (TextUtils.isEmpty(prop2)) {
            prop2 = getProp("ro.hmct.ota.product");
            if (TextUtils.isEmpty(prop2)) {
                prop2 = getProp("ro.hmct.product.fullname");
            }
        }
        if (TextUtils.isEmpty(prop2)) {
            strC = "862fff00fffefff00000fffe";
            z2 = false;
        } else {
            String strReplace = ("86300b" + prop2).trim().replace(Ascii.CASE_MASK, '-');
            if (strReplace.length() >= 24) {
                strC = strReplace.substring(0, 24);
            } else {
                strC = c(strReplace, 24);
            }
            z2 = true;
        }
        String strA2 = com.hmct.cloud.sdk.a.a.a(context);
        if (TextUtils.isEmpty(strA2)) {
            strA2 = getPhoneMeid(context, z);
            SDKUtil.LogD("DeviceConfig", "str1:= " + strA2 + ", permissionReadPhoneState = " + z + ", isHisensePhone = " + z2);
            if (TextUtils.isEmpty(strA2)) {
                String wifiMacAddress = getWifiMacAddress(context);
                SDKUtil.LogD("DeviceConfig", "getWifiMacAddress:= " + wifiMacAddress);
                if (!TextUtils.isEmpty(wifiMacAddress) && !f2079b.equals(wifiMacAddress) && !f2080c.equals(wifiMacAddress)) {
                    String lowerCase = wifiMacAddress.replace(Utils.APP_ID_IDENTIFICATION_SUBSTRING, "").toLowerCase();
                    SDKUtil.LogD("DeviceConfig", "deviceCode:= " + lowerCase);
                    strA = a(a(Build.BRAND) + a(Build.MODEL) + lowerCase, 32);
                    strA2 = strA;
                } else {
                    String uniqueID = getUniqueID(context);
                    SDKUtil.LogD("DeviceConfig", "getUUID():= " + uniqueID);
                    String str = "xyz" + uniqueID;
                    if (str.length() >= 32) {
                        strB = str.substring(0, 32);
                    } else {
                        strB = b(str, 32);
                    }
                    Log.i("DeviceConfig", "devicecod from formatDeviceCode=22===>>" + str);
                    strA2 = strB;
                }
            } else {
                SDKUtil.LogD("DeviceConfig", "deviceCode1123:= " + strA2);
                if (!z2) {
                    strA = a(strA2, 32);
                    strA2 = strA;
                }
            }
            com.hmct.cloud.sdk.a.a.a(context, strA2);
        }
        return (strC + strA2).toLowerCase();
    }

    public static String b(String str, int i) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int length = i - str.length(); length > 0; length--) {
            stringBuffer.append("0");
        }
        stringBuffer.append(str);
        return stringBuffer.toString();
    }

    public static String a(String str, int i) {
        String strB;
        if (str.length() >= i) {
            strB = str.substring(str.length() - i);
        } else {
            strB = b(str, i);
        }
        Log.i("DeviceConfig", "devicecod from formatDeviceCode==11==>>" + str);
        return strB;
    }

    public static String a() {
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (networkInterface.getName().equalsIgnoreCase("wlan0")) {
                    byte[] hardwareAddress = networkInterface.getHardwareAddress();
                    if (hardwareAddress == null) {
                        return "";
                    }
                    StringBuilder sb = new StringBuilder();
                    for (byte b2 : hardwareAddress) {
                        sb.append(String.format("%02X:", Byte.valueOf(b2)));
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    return sb.toString();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        Log.i("DeviceConfig", "getMacFromHardware() return default");
        return f2079b;
    }

    public static String c(String str, int i) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        for (int length = 24 - str.length(); length > 0; length--) {
            stringBuffer.append("0");
        }
        return stringBuffer.toString();
    }
}
