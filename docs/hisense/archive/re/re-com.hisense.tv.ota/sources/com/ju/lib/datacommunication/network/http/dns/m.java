package com.ju.lib.datacommunication.network.http.dns;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes.dex */
class m {
    private static final char[] b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final byte[] f119a;

    public m(byte[] bArr) {
        this.f119a = bArr;
    }

    private static String a(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = i * 2;
            byte b2 = bArr[i];
            char[] cArr2 = b;
            cArr[i2] = cArr2[(b2 >> 4) & 15];
            cArr[i2 + 1] = cArr2[b2 & 15];
        }
        return new String(cArr);
    }

    private Cipher d(int i) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(this.f119a, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec("eefd3f6980a10330".getBytes("UTF-8"));
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(i, secretKeySpec, ivParameterSpec);
        return cipher;
    }

    private static byte[] e(String str) {
        char[] charArray = str.toCharArray();
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) (Character.digit(charArray[i2 + 1], 16) | (Character.digit(charArray[i2], 16) << 4));
        }
        return bArr;
    }

    public String b(String str) {
        return new String(d(2).doFinal(e(str)), "UTF-8");
    }

    public String c(String str) {
        return a(d(1).doFinal(str.getBytes("UTF-8")));
    }
}
