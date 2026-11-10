package com.ju.lib.datacommunication.network.http.address;

import b.a.a.a.a;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes2.dex */
public class EncryptAES {
    public static final String D_A_K = "D5B6D8584F94B432";
    public static final String D_A_V = "205681D89D731A8F";
    public Cipher dec;
    public Cipher enc;
    public IvParameterSpec ivSpec;
    public SecretKeySpec keySpec;

    public static void dump(String str, byte[] bArr) {
        String strByteArr2Str;
        try {
            strByteArr2Str = ByteUtils.byteArr2Str(bArr, 16);
        } catch (Exception e2) {
            e2.printStackTrace();
            strByteArr2Str = "";
        }
        PrintStream printStream = System.out;
        StringBuilder sbB = a.b(str, "=");
        sbB.append(strByteArr2Str.toUpperCase());
        printStream.println(sbB.toString());
    }

    private void initDecryptor() throws InvalidKeyException, InvalidAlgorithmParameterException {
        if (this.dec == null) {
            this.dec = Cipher.getInstance("AES/CBC/PKCS5Padding");
            this.dec.init(2, this.keySpec, this.ivSpec);
        }
    }

    private void initEncryptor() throws InvalidKeyException, InvalidAlgorithmParameterException {
        if (this.enc == null) {
            this.enc = Cipher.getInstance("AES/CBC/PKCS5Padding");
            this.enc.init(1, this.keySpec, this.ivSpec);
        }
    }

    public static void main(String[] strArr) throws InvalidKeyException, UnsupportedEncodingException, InvalidAlgorithmParameterException {
        System.out.println("=========AES/CBC/PKCS5Padding=========");
        byte[] bytes = "D5B6D8584F94B432".getBytes("UTF-8");
        dump("key", bytes);
        byte[] bytes2 = "205681D89D731A8F".getBytes("UTF-8");
        dump("iv", bytes2);
        byte[] bytes3 = "asdfa57945ojfasdfg**)9af".getBytes("UTF-8");
        dump("indata", bytes3);
        EncryptAES encryptAES = new EncryptAES();
        encryptAES.init(bytes, bytes2);
        byte[] bArrEncrypt = encryptAES.encrypt(bytes3);
        dump("outdata", bArrEncrypt);
        String upperCase = ByteUtils.byteArr2Str(bArrEncrypt, 16).toUpperCase();
        System.out.println(upperCase);
        byte[] bArrDecrypt = encryptAES.decrypt(ByteUtils.string2ByteArray(upperCase));
        dump("indata1", bArrDecrypt);
        System.out.println(new String(bArrDecrypt, "UTF-8"));
    }

    public void decrypt(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, InvalidAlgorithmParameterException, ShortBufferException {
        initDecryptor();
        this.dec.doFinal(bArr, i, i2, bArr2, i3);
    }

    public void encrypt(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, InvalidAlgorithmParameterException, ShortBufferException {
        initEncryptor();
        this.enc.doFinal(bArr, i, i2, bArr2, i3);
    }

    public int getCipherLen(int i) {
        int i2 = i % 16;
        return i2 == 0 ? i + 16 : (i - i2) + 16;
    }

    public int getPlainLen(int i) {
        return i;
    }

    public void init(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        this.keySpec = new SecretKeySpec(bArr, i, i2, "AES");
        this.ivSpec = new IvParameterSpec(bArr2, i3, 16);
    }

    public byte[] decrypt(byte[] bArr, int i, int i2) throws InvalidKeyException, InvalidAlgorithmParameterException {
        initDecryptor();
        return this.dec.doFinal(bArr, i, i2);
    }

    public byte[] encrypt(byte[] bArr, int i, int i2) throws InvalidKeyException, InvalidAlgorithmParameterException {
        initEncryptor();
        return this.enc.doFinal(bArr, i, i2);
    }

    public void init(byte[] bArr, byte[] bArr2) {
        this.keySpec = new SecretKeySpec(bArr, "AES");
        this.ivSpec = new IvParameterSpec(bArr2);
    }

    public byte[] decrypt(byte[] bArr) throws InvalidKeyException, InvalidAlgorithmParameterException {
        initDecryptor();
        return this.dec.doFinal(bArr);
    }

    public byte[] encrypt(byte[] bArr) throws InvalidKeyException, InvalidAlgorithmParameterException {
        initEncryptor();
        return this.enc.doFinal(bArr);
    }
}
