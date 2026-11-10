package com.ju.lib.utils.encryption;

import com.ju.lib.utils.log.LogUtil;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: loaded from: classes2.dex */
public class EncryptionUitls {
    public static String MD5Encrypt(String str) {
        return encrypt(str, "MD5");
    }

    public static String SHA256Encrypt(String str) {
        return encrypt(str, "SHA-256");
    }

    public static String bytes2Hex(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & 255);
            if (hexString.length() == 1) {
                stringBuffer.append(0);
            }
            stringBuffer.append(hexString);
        }
        return stringBuffer.toString();
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0022  */
    public static String encrypt(String str, String str2) {
        if (str == null) {
            LogUtil.w("EncryptionUitls", "Encrypt() strSrc is null");
            return "";
        }
        byte[] bytes = str.getBytes(Charset.defaultCharset());
        if (str2 != null) {
            try {
                if (str2.equals("")) {
                    str2 = "SHA-256";
                }
            } catch (NoSuchAlgorithmException unused) {
                return "";
            }
        } else {
            str2 = "SHA-256";
        }
        MessageDigest messageDigest = MessageDigest.getInstance(str2);
        messageDigest.update(bytes);
        return bytes2Hex(messageDigest.digest());
    }
}
