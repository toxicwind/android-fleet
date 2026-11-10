package com.ju.lib.datareport;

import com.ju.lib.utils.log.LogUtil;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes2.dex */
public class AES {
    public static final String TAG = "AES";
    public final String mDak;
    public final String mDav;
    public Cipher mDecodeCipher;
    public Cipher mEncodeCipher;

    public AES(String str, String str2) {
        this.mDak = str;
        this.mDav = str2;
    }

    private Cipher getCipher(int i) {
        return getCipher(i, this.mDak, this.mDav);
    }

    private Cipher getDecodeCipher() {
        if (this.mDecodeCipher == null) {
            this.mDecodeCipher = getCipher(2);
        }
        return this.mDecodeCipher;
    }

    private Cipher getEncodeCipher() {
        if (this.mEncodeCipher == null) {
            this.mEncodeCipher = getCipher(1);
        }
        return this.mEncodeCipher;
    }

    public byte[] decrypt(byte[] bArr) {
        try {
            byte[] bArrDoFinal = getDecodeCipher().doFinal(bArr);
            LogUtil.i(TAG, "decrypt ok! ");
            return bArrDoFinal;
        } catch (Exception e2) {
            throw new RuntimeException("decrypt error! ", e2);
        }
    }

    public byte[] encrypt(byte[] bArr) {
        try {
            byte[] bArrDoFinal = getEncodeCipher().doFinal(bArr);
            LogUtil.i(TAG, "encrypt ok! ");
            return bArrDoFinal;
        } catch (Exception e2) {
            throw new RuntimeException("encrypt error! ", e2);
        }
    }

    private Cipher getCipher(int i, String str, String str2) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(str.getBytes("UTF-8"), "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(str2.getBytes("UTF-8"));
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(i, secretKeySpec, ivParameterSpec);
        return cipher;
    }
}
