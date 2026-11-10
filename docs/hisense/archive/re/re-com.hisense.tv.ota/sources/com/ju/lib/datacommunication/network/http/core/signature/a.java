package com.ju.lib.datacommunication.network.http.core.signature;

import android.text.TextUtils;
import android.util.Base64;
import java.io.UnsupportedEncodingException;
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
import javax.crypto.Cipher;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile PublicKey f96a;

    public static String a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        String str3 = str + str2;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            return Base64.encodeToString(messageDigest.digest(str3.getBytes("UTF-8")), 2);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str;
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return str;
        }
    }

    public static String b(Map map, String str) {
        if (map != null && !map.isEmpty()) {
            HashMap map2 = new HashMap();
            map2.putAll(map);
            try {
                String str2 = h(map2) + str;
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                messageDigest.reset();
                return Base64.encodeToString(messageDigest.digest(str2.getBytes("UTF-8")), 2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String c(Map map, Key key) {
        if (map != null && !map.isEmpty() && key != null) {
            HashMap map2 = new HashMap();
            map2.putAll(map);
            try {
                return e(h(map2), key);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Map d(Map map, Key key, String str) {
        if (map != null && !map.isEmpty() && key != null) {
            HashMap map2 = new HashMap();
            map2.putAll(map);
            map2.remove(str);
            try {
                map2.put(str, e(h(map2), key));
                return map2;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    public static String e(String str, Key key) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(1, key);
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            return Base64.encodeToString(cipher.doFinal(messageDigest.digest(str.getBytes("UTF-8"))), 2);
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    private static PublicKey f() {
        return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode("MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAK26F0+yIWMxpW4WW7vsrwLl8kp8isCarBGv54xOK468ZD6FbOMZAOSj8JBr0IpUzv5w+hURR6W4oINsI4o5CEMCAwEAAQ==", 0)));
    }

    public static PublicKey g() {
        if (f96a == null) {
            synchronized (a.class) {
                if (f96a == null) {
                    try {
                        f96a = f();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return f96a;
    }

    public static String h(Map map) {
        StringBuffer stringBuffer = new StringBuffer();
        ArrayList arrayList = new ArrayList(map.keySet());
        Collections.sort(arrayList);
        for (int i = 0; i < arrayList.size(); i++) {
            String str = (String) arrayList.get(i);
            String str2 = (String) map.get(str);
            if (!TextUtils.isEmpty(str2)) {
                stringBuffer.append("&" + str + "=" + str2);
            }
        }
        String string = stringBuffer.toString();
        return string.startsWith("&") ? string.replaceFirst("&", "") : string;
    }
}
