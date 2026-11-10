package com.ju.lib.datacommunication.network.http.core.signature;

import android.text.TextUtils;
import android.util.Base64;
import b.a.a.a.a;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/* JADX INFO: loaded from: classes2.dex */
public class HiCloudKey {
    public static final byte[] KEYS = {105, 108, 111, 118, 101, 36, 38, 104, 97, 116, 101, 119, 111, 114, 107, 44, 53, 49, 56};
    public static final String MD5KEy = "222701ff322c96519c5fe52098741caba88b81f77a7d00ab80a7ed09b9b4116b559725c3c88819d3d0d81da5502ca08b";
    public static volatile PublicKey publicKey = null;
    public static final String publicKeyString = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAK26F0+yIWMxpW4WW7vsrwLl8kp8isCarBGv54xOK468ZD6FbOMZAOSj8JBr0IpUzv5w+hURR6W4oINsI4o5CEMCAwEAAQ==";
    public static final String publicKeyString_edu = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC84rAAUNyDJsGC4fP81oDLNnl0\nAgKEGCc0YNj5b/F4yh/yFKDGpJggSiwRJaU04FvN99lb+J3LljqnjsCE+V3/s9af\n6kqvDFm3rhNSxvtrzPkmtU6Vulvbw8VFkAyyZ7IFUTtNyePU4KMRWqR82nMuhr5b\nisU0Hgx0yWqBd6/0kQIDAQAB";
    public static volatile PublicKey publicKey_edu;

    public static String decyptRSA(String str, Key key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(2, key);
        return getString(cipher.doFinal(Base64.decode(str, 0)));
    }

    public static Map<String, String> encyptMapMD5(Map<String, String> map, String str) {
        return encyptMapMD5WithAppSecret(map, str, getMD5KEY());
    }

    public static Map<String, String> encyptMapMD5WithAppSecret(Map<String, String> map, String str, String str2) {
        if (map != null && !map.isEmpty()) {
            HashMap map2 = new HashMap();
            map2.putAll(map);
            map2.remove(str);
            try {
                String str3 = getSignData(map2) + str2;
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                messageDigest.reset();
                map2.put(str, Base64.encodeToString(messageDigest.digest(str3.getBytes("UTF-8")), 2));
                return map2;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return map;
    }

    public static Map<String, String> encyptMapRSA(Map<String, String> map, Key key, String str) {
        if (map != null && !map.isEmpty() && key != null) {
            HashMap map2 = new HashMap();
            map2.putAll(map);
            map2.remove(str);
            try {
                map2.put(str, encyptRSA(getSignData(map2), key));
                return map2;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return map;
    }

    public static String encyptRSA(String str, Key key) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(1, key);
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            return Base64.encodeToString(cipher.doFinal(messageDigest.digest(str.getBytes("UTF-8"))), 2);
        } catch (Exception e2) {
            e2.printStackTrace();
            return str;
        }
    }

    public static String encyptStringMD5(String str) {
        return encyptMapMD5WithAppSecret(str, getMD5KEY());
    }

    public static String getMD5KEY() {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(KEYS);
            return new Secret(messageDigest.digest()).decrypt(MD5KEy);
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return null;
        } catch (InvalidAlgorithmParameterException e3) {
            e3.printStackTrace();
            return null;
        } catch (InvalidKeyException e4) {
            e4.printStackTrace();
            return null;
        } catch (NoSuchAlgorithmException e5) {
            throw new RuntimeException(e5);
        } catch (BadPaddingException e6) {
            e6.printStackTrace();
            return null;
        } catch (IllegalBlockSizeException e7) {
            e7.printStackTrace();
            return null;
        } catch (NoSuchPaddingException e8) {
            e8.printStackTrace();
            return null;
        }
    }

    public static PublicKey getPublic() {
        return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode("MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAK26F0+yIWMxpW4WW7vsrwLl8kp8isCarBGv54xOK468ZD6FbOMZAOSj8JBr0IpUzv5w+hURR6W4oINsI4o5CEMCAwEAAQ==", 0)));
    }

    public static PublicKey getPublicKey() {
        if (publicKey == null) {
            synchronized (HiCloudKey.class) {
                if (publicKey == null) {
                    try {
                        publicKey = getPublic();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
        return publicKey;
    }

    public static PublicKey getPublicKey_edu() {
        if (publicKey_edu == null) {
            synchronized (HiCloudKey.class) {
                if (publicKey_edu == null) {
                    try {
                        publicKey_edu = getPublic_edu();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
        return publicKey_edu;
    }

    public static PublicKey getPublic_edu() {
        return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC84rAAUNyDJsGC4fP81oDLNnl0\nAgKEGCc0YNj5b/F4yh/yFKDGpJggSiwRJaU04FvN99lb+J3LljqnjsCE+V3/s9af\n6kqvDFm3rhNSxvtrzPkmtU6Vulvbw8VFkAyyZ7IFUTtNyePU4KMRWqR82nMuhr5b\nisU0Hgx0yWqBd6/0kQIDAQAB", 0)));
    }

    public static String getSignData(Map<String, String> map) {
        StringBuffer stringBuffer = new StringBuffer();
        ArrayList arrayList = new ArrayList(map.keySet());
        Collections.sort(arrayList);
        for (int i = 0; i < arrayList.size(); i++) {
            String str = (String) arrayList.get(i);
            String str2 = map.get(str);
            if (!TextUtils.isEmpty(str2)) {
                stringBuffer.append("&" + str + "=" + str2);
            }
        }
        String string = stringBuffer.toString();
        return string.startsWith("&") ? string.replaceFirst("&", "") : string;
    }

    public static String getString(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b2 : bArr) {
            stringBuffer.append((int) b2);
        }
        return stringBuffer.toString();
    }

    public static String encyptMapMD5(Map<String, String> map) {
        return encyptMapMD5WithAppSecret(map, getMD5KEY());
    }

    public static String encyptMapRSA(Map<String, String> map, Key key) {
        if (map != null && !map.isEmpty() && key != null) {
            HashMap map2 = new HashMap();
            map2.putAll(map);
            try {
                return encyptRSA(getSignData(map2), key);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public static String encyptMapMD5WithAppSecret(Map<String, String> map, String str) {
        if (map != null && !map.isEmpty()) {
            HashMap map2 = new HashMap();
            map2.putAll(map);
            try {
                String str2 = getSignData(map2) + str;
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                messageDigest.reset();
                return Base64.encodeToString(messageDigest.digest(str2.getBytes("UTF-8")), 2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public static String encyptMapMD5WithAppSecret(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        String strA = a.a(str, str2);
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            return Base64.encodeToString(messageDigest.digest(strA.getBytes("UTF-8")), 2);
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return str;
        } catch (NoSuchAlgorithmException e3) {
            e3.printStackTrace();
            return str;
        }
    }
}
