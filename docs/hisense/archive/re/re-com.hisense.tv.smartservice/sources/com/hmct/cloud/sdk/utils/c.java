package com.hmct.cloud.sdk.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes2.dex */
public final class c {
    static {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    }

    public static String a(String str) {
        try {
            byte[] bytes = "D5B6D8584F94B432".getBytes("UTF-8");
            byte[] bytes2 = "205681D89D731A8F".getBytes("UTF-8");
            byte[] bArrB = com.hmct.cloud.sdk.a.a.B(str);
            SecretKeySpec secretKeySpec = new SecretKeySpec(bytes, "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(bytes2);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(2, secretKeySpec, ivParameterSpec);
            return new String(cipher.doFinal(bArrB), "UTF-8");
        } catch (Exception unused) {
            return null;
        }
    }
}
