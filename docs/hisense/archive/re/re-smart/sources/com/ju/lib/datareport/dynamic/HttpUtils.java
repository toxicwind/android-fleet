package com.ju.lib.datareport.dynamic;

import android.util.Base64;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: loaded from: classes2.dex */
public class HttpUtils {
    public static final String TAG = "HttpUtils";

    public static String getApiGatewaySign(String str, String str2) {
        return Base64.encodeToString(stringToMD5Bytes(str + str2), 0);
    }

    public static byte[] stringToMD5Bytes(String str) {
        try {
            return MessageDigest.getInstance("md5").digest(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return null;
        } catch (NoSuchAlgorithmException e3) {
            e3.printStackTrace();
            return null;
        }
    }
}
