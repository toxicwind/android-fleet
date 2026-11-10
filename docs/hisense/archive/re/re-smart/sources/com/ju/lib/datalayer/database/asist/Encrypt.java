package com.ju.lib.datalayer.database.asist;

import com.google.common.base.Ascii;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: loaded from: classes2.dex */
public final class Encrypt {
    public static char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static byte[] getEncodeBytes(String str, String str2) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str2);
            messageDigest.update(str.getBytes("UTF-8"));
            return messageDigest.digest();
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return null;
        } catch (NoSuchAlgorithmException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static String getEncodeString(String str, String str2) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes("UTF-8"));
            byte[] bArrDigest = messageDigest.digest();
            char[] cArr = new char[bArrDigest.length * 2];
            int i = 0;
            for (byte b2 : bArrDigest) {
                int i2 = i + 1;
                cArr[i] = hexDigits[(b2 >>> 4) & 15];
                i = i2 + 1;
                cArr[i2] = hexDigits[b2 & Ascii.SI];
            }
            return new String(cArr);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String getMD2EncString(String str) {
        return getEncodeString(str, "MD2");
    }

    public static String getMD5EncString(String str) {
        return getEncodeString(str, "MD5");
    }

    public static String getSHA1EncString(String str) {
        return getEncodeString(str, "SHA-1");
    }

    public static String getSHA256EncString(String str) {
        return getEncodeString(str, "SHA-256");
    }

    public static String getSHA384EncString(String str) {
        return getEncodeString(str, "SHA-384");
    }

    public static String getSHA512EncString(String str) {
        return getEncodeString(str, "SHA-512");
    }
}
