package com.hmct.cloud.sdk.utils;

import android.text.TextUtils;
import com.google.common.base.Ascii;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes2.dex */
public class AESUtil {
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

    public static String encrypt(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec("D5B6D8584F94B432".getBytes("UTF-8"), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec("205681D89D731A8F".getBytes("UTF-8"));
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(1, secretKeySpec, ivParameterSpec);
            return byte2string(cipher.doFinal(str.getBytes("UTF-8")));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public String Encrypt(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes("UTF-8"), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(str3.getBytes("UTF-8"));
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(1, secretKeySpec, ivParameterSpec);
            return byte2string(cipher.doFinal(str.getBytes("UTF-8")));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
