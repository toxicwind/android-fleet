package com.hmct.cloud.sdk.utils;

import android.text.TextUtils;
import android.util.Log;
import com.google.common.base.Ascii;
import com.hisense.support.tv.HisenseCountryLanInfo;
import com.ju.lib.datacommunication.network.http.core.signature.HiCloudKey;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class SDKUtil {
    public static void LogD(String str, String str2) {
        if (Log.isLoggable(str, 3)) {
            Log.d(str, str2);
        }
    }

    public static String byte2HEX(byte b2) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        return new String(new char[]{cArr[(b2 >>> 4) & 15], cArr[b2 & Ascii.SI]});
    }

    public static String byte2string(byte[] bArr) {
        String string = "";
        for (byte b2 : bArr) {
            StringBuilder sbA = b.a.a.a.a.a(string);
            sbA.append(byte2HEX(b2));
            string = sbA.toString();
        }
        return string;
    }

    public static String getLocaleLanguage() {
        Locale locale = Locale.getDefault();
        String language = locale.getLanguage();
        String strA = b.a.a.a.a.a(language, "_", locale.getCountry());
        if (strA.equals(Locale.SIMPLIFIED_CHINESE.toString())) {
            return "0";
        }
        if (strA.equals(Locale.TRADITIONAL_CHINESE.toString())) {
            return "8";
        }
        if (language.equalsIgnoreCase(Locale.ENGLISH.toString())) {
            return "1";
        }
        if (language.equalsIgnoreCase(Locale.FRENCH.toString())) {
            return Constants.LANGUAGE_FRENCH;
        }
        return language.equalsIgnoreCase(HisenseCountryLanInfo.LOCALE_SPA_CODE) ? "6" : "0";
    }

    public static Map<String, String> getMapFromJson(String str) {
        HashMap map = new HashMap();
        try {
            e.b.d dVar = new e.b.d(str);
            Iterator<String> itA = dVar.a();
            while (itA.hasNext()) {
                String next = itA.next();
                Object objA = dVar.a(next);
                if (objA != null) {
                    map.put(next, (String) objA);
                } else {
                    map.put(next, "");
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return map;
    }

    public static boolean isRegularBcode(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            return URLEncoder.encode(str, "UTF-8").length() == str.length();
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
    }

    public static byte[] md5Encode(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        byte[] bytes = str.getBytes();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bytes);
            return messageDigest.digest();
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static byte[] rc4_crypt(byte[] bArr, byte[] bArr2) {
        char[] cArr = new char[256];
        rc4_init(cArr, bArr, bArr.length);
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < bArr2.length; i3++) {
            i = (i + 1) % 256;
            i2 = (i2 + cArr[i]) % 256;
            bArr2[i3] = (byte) (cArr[(cArr[i] + cArr[i2]) % 256] ^ bArr2[i3]);
        }
        return bArr2;
    }

    public static void rc4_init(char[] cArr, byte[] bArr, int i) {
        char[] cArr2 = new char[256];
        for (int i2 = 0; i2 < 256; i2++) {
            cArr[i2] = (char) i2;
            cArr2[i2] = (char) bArr[i2 % i];
        }
        int i3 = 0;
        for (int i4 = 0; i4 < 256; i4++) {
            i3 = ((i3 + cArr[i4]) + cArr2[i4]) % 256;
            char c2 = cArr[i4];
            cArr[i4] = cArr[i3];
            cArr[i3] = c2;
        }
    }

    public static String toUTF_8(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return new String(str.getBytes(), "UTF-8");
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static boolean verifySignature(String str) {
        return !"<?xml version=\"1.0\" encoding=\"utf-8\"?><response><resultCode>1</resultCode><errorCode>900001</errorCode><errorDesc>signatureError</errorDesc><error_code>900001</error_code><error_name>signatureError</error_name></response>".equals(d.a(str, HiCloudKey.getPublicKey()));
    }

    public static boolean verifySignatureJson_signature(String str) {
        return !"{\"response\":{\"resultCode\":1,\"errorCode\":\"900001\",\"errorDesc\":\"signatureError\",\"resultcode\":1,\"errorcode\":\"900001\",\"errordesc\":\"signatureError\"}}".equals(d.c(str, HiCloudKey.getPublicKey()));
    }

    public static boolean verifySignatureJson_signatureServer(String str) {
        return !"{\"response\":{\"resultCode\":1,\"errorCode\":\"900001\",\"errorDesc\":\"signatureError\",\"resultcode\":1,\"errorcode\":\"900001\",\"errordesc\":\"signatureError\"}}".equals(d.b(str, HiCloudKey.getPublicKey()));
    }

    public static boolean verifySignatureJson_signature_edu(String str) {
        return !"{\"response\":{\"resultCode\":1,\"errorCode\":\"900001\",\"errorDesc\":\"signatureError\",\"resultcode\":1,\"errorcode\":\"900001\",\"errordesc\":\"signatureError\"}}".equals(d.d(str, HiCloudKey.getPublicKey_edu()));
    }
}
