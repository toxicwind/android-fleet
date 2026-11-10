package com.hisense.uifac.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: loaded from: classes.dex */
public class MD5Util {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.io.FileInputStream] */
    public static String getMD5(String str) throws Throwable {
        FileInputStream fileInputStream;
        BigInteger bigInteger;
        ?? r1 = 0;
        String string = null;
        try {
            try {
                try {
                    byte[] bArr = new byte[8192];
                    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                    fileInputStream = new FileInputStream(new File(str));
                    while (true) {
                        try {
                            int i = fileInputStream.read(bArr);
                            if (i == -1) {
                                break;
                            }
                            messageDigest.update(bArr, 0, i);
                        } catch (IOException e) {
                            e = e;
                            e.printStackTrace();
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            bigInteger = null;
                        } catch (NoSuchAlgorithmException e2) {
                            e = e2;
                            e.printStackTrace();
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            bigInteger = null;
                        }
                    }
                    fileInputStream.close();
                    bigInteger = new BigInteger(1, messageDigest.digest());
                    try {
                        fileInputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                } catch (IOException e4) {
                    e4.printStackTrace();
                    bigInteger = null;
                }
            } catch (IOException e5) {
                e = e5;
                fileInputStream = null;
            } catch (NoSuchAlgorithmException e6) {
                e = e6;
                fileInputStream = null;
            } catch (Throwable th) {
                th = th;
                if (r1 != 0) {
                    try {
                        r1.close();
                    } catch (IOException e7) {
                        e7.printStackTrace();
                    }
                }
                throw th;
            }
            if (bigInteger != null) {
                string = bigInteger.toString(16);
                while (string.length() < 32) {
                    string = "0" + string;
                }
            }
            return string;
        } catch (Throwable th2) {
            th = th2;
            r1 = str;
        }
    }
}
