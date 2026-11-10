package com.hmct.cloud.sdk.utils;

import com.google.common.base.Ascii;
import com.ju.lib.datareport.dynamic.DynamicDomainHelper;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: loaded from: classes2.dex */
public class Md5 extends Struct {
    public static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    public long count = 0;
    public MessageDigest digest;
    public byte[] ret;

    public Md5() {
        try {
            this.digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException unused) {
        }
        init();
    }

    public static byte[] digest(byte[] bArr) {
        MessageDigest md5 = getInstance();
        md5.update(bArr);
        return md5.digest();
    }

    public static MessageDigest getInstance() {
        try {
            return MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException unused) {
            return null;
        }
    }

    public static String md5(String str) {
        try {
            byte[] bArrDigest = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder(bArrDigest.length << 1);
            for (byte b2 : bArrDigest) {
                int i = b2 & 255;
                if (i < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(i));
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e2);
        } catch (NoSuchAlgorithmException e3) {
            throw new RuntimeException("Huh, MD5 should be supported?", e3);
        }
    }

    public static String md5sum(String str) {
        byte[] bArr = new byte[1024];
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            while (true) {
                int i = fileInputStream.read(bArr);
                if (i <= 0) {
                    fileInputStream.close();
                    return toHexString(messageDigest.digest());
                }
                messageDigest.update(bArr, 0, i);
            }
        } catch (Exception unused) {
            System.out.println(DynamicDomainHelper.DYNAMIC_ERROR);
            return null;
        }
    }

    public static String toHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length << 1);
        for (int i = 0; i < bArr.length; i++) {
            sb.append(HEX_DIGITS[(bArr[i] & 240) >>> 4]);
            sb.append(HEX_DIGITS[bArr[i] & Ascii.SI]);
        }
        return sb.toString();
    }

    public void append(byte b2) {
        this.digest.update(b2);
        this.count++;
    }

    public byte[] finish() {
        if (this.ret == null) {
            this.ret = this.digest.digest();
        }
        return this.ret;
    }

    @Override // com.hmct.cloud.sdk.utils.Struct
    public void init() {
    }

    public void append(byte[] bArr) {
        this.digest.update(bArr);
        this.count += (long) bArr.length;
    }

    public void append(byte[] bArr, int i, int i2) {
        this.digest.update(bArr, i, i2);
        this.count++;
    }
}
