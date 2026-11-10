package com.ju.lib.datacommunication.network.http.core.signature;

import com.google.common.base.Ascii;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes2.dex */
public class Secret {
    public static final char[] HEX = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    public final byte[] mKey;

    public Secret(byte[] bArr) {
        this.mKey = bArr;
    }

    public static String byteArrayToHexString(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = i * 2;
            byte b2 = bArr[i];
            char[] cArr2 = HEX;
            cArr[i2] = cArr2[(b2 >> 4) & 15];
            cArr[i2 + 1] = cArr2[b2 & Ascii.SI];
        }
        return new String(cArr);
    }

    private Cipher getCipher(int i) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(this.mKey, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec("eefd3f6980a10330".getBytes("UTF-8"));
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(i, secretKeySpec, ivParameterSpec);
        return cipher;
    }

    public static byte[] hexStringToByteArray(String str) {
        char[] charArray = str.toCharArray();
        byte[] bArr = new byte[str.length() / 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) (Character.digit(charArray[i2 + 1], 16) | (Character.digit(charArray[i2], 16) << 4));
        }
        return bArr;
    }

    public String decrypt(String str) {
        return new String(getCipher(2).doFinal(hexStringToByteArray(str)), "UTF-8");
    }

    public String encrypt(String str) {
        return byteArrayToHexString(getCipher(1).doFinal(str.getBytes("UTF-8")));
    }
}
