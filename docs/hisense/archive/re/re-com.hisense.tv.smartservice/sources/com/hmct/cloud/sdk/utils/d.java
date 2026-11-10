package com.hmct.cloud.sdk.utils;

import android.text.TextUtils;
import android.util.Base64;
import com.ju.lib.datacommunication.network.http.address.ThirdDynamicAddressManager;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

/* JADX INFO: loaded from: classes2.dex */
public final class d {
    public static String a(String str, Key key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(2, key);
        return a(cipher.doFinal(Base64.decode(str, 0)));
    }

    public static String b(String str, PublicKey publicKey) {
        try {
            String strA = new e.b.d(str).a(ThirdDynamicAddressManager.SIGN_KEY, "");
            if (TextUtils.isEmpty(strA)) {
                SDKUtil.LogD("HttpHandler", "signatureServer=null==>" + str);
                return "{\"response\":{\"resultCode\":1,\"errorCode\":\"900001\",\"errorDesc\":\"signatureError\",\"resultcode\":1,\"errorcode\":\"900001\",\"errordesc\":\"signatureError\"}}";
            }
            String strReplaceAll = Pattern.compile(",(\\s*)\"signatureServer\":(\\s*)\"(.*?)\"").matcher(str).replaceAll("");
            if (publicKey != null) {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                messageDigest.reset();
                if (a(strA, (Key) publicKey).equals(a(messageDigest.digest(strReplaceAll.getBytes())))) {
                    return strReplaceAll;
                }
            }
            return "{\"response\":{\"resultCode\":1,\"errorCode\":\"900001\",\"errorDesc\":\"signatureError\",\"resultcode\":1,\"errorcode\":\"900001\",\"errordesc\":\"signatureError\"}}";
        } catch (e.b.b e2) {
            e2.printStackTrace();
            return "{\"response\":{\"resultCode\":1,\"errorCode\":\"900001\",\"errorDesc\":\"signatureError\",\"resultcode\":1,\"errorcode\":\"900001\",\"errordesc\":\"signatureError\"}}";
        } catch (NoSuchAlgorithmException e3) {
            e3.printStackTrace();
            return "{\"response\":{\"resultCode\":1,\"errorCode\":\"900001\",\"errorDesc\":\"signatureError\",\"resultcode\":1,\"errorcode\":\"900001\",\"errordesc\":\"signatureError\"}}";
        } catch (Exception e4) {
            e4.printStackTrace();
            return "{\"response\":{\"resultCode\":1,\"errorCode\":\"900001\",\"errorDesc\":\"signatureError\",\"resultcode\":1,\"errorcode\":\"900001\",\"errordesc\":\"signatureError\"}}";
        }
    }

    public static String c(String str, PublicKey publicKey) {
        try {
            String strA = new e.b.d(str).a("signature", "");
            if (TextUtils.isEmpty(strA)) {
                SDKUtil.LogD("HttpHandler", "signatureServer=null==>" + str);
                return "{\"response\":{\"resultCode\":1,\"errorCode\":\"900001\",\"errorDesc\":\"signatureError\",\"resultcode\":1,\"errorcode\":\"900001\",\"errordesc\":\"signatureError\"}}";
            }
            String strReplaceAll = Pattern.compile("(\\s*)\"signature\":(\\s*)\"(.*?)\",").matcher(str).replaceAll("");
            if (publicKey != null) {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                messageDigest.reset();
                if (a(strA, (Key) publicKey).equals(a(messageDigest.digest(strReplaceAll.getBytes())))) {
                    return strReplaceAll;
                }
            }
            return "{\"response\":{\"resultCode\":1,\"errorCode\":\"900001\",\"errorDesc\":\"signatureError\",\"resultcode\":1,\"errorcode\":\"900001\",\"errordesc\":\"signatureError\"}}";
        } catch (e.b.b e2) {
            e2.printStackTrace();
            return "{\"response\":{\"resultCode\":1,\"errorCode\":\"900001\",\"errorDesc\":\"signatureError\",\"resultcode\":1,\"errorcode\":\"900001\",\"errordesc\":\"signatureError\"}}";
        } catch (NoSuchAlgorithmException e3) {
            e3.printStackTrace();
            return "{\"response\":{\"resultCode\":1,\"errorCode\":\"900001\",\"errorDesc\":\"signatureError\",\"resultcode\":1,\"errorcode\":\"900001\",\"errordesc\":\"signatureError\"}}";
        } catch (Exception e4) {
            e4.printStackTrace();
            return "{\"response\":{\"resultCode\":1,\"errorCode\":\"900001\",\"errorDesc\":\"signatureError\",\"resultcode\":1,\"errorcode\":\"900001\",\"errordesc\":\"signatureError\"}}";
        }
    }

    public static String d(String str, PublicKey publicKey) {
        try {
            String strA = new e.b.d(str).a("signature", "");
            if (TextUtils.isEmpty(strA)) {
                SDKUtil.LogD("HttpHandler", "signatureServer=null==>" + str);
                return "{\"response\":{\"resultCode\":1,\"errorCode\":\"900001\",\"errorDesc\":\"signatureError\",\"resultcode\":1,\"errorcode\":\"900001\",\"errordesc\":\"signatureError\"}}";
            }
            String strReplaceAll = Pattern.compile(",(\\s*)\"signature\":(\\s*)\"(.*?)\"").matcher(str).replaceAll("");
            if (publicKey != null) {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                messageDigest.reset();
                if (a(strA, (Key) publicKey).equals(a(messageDigest.digest(strReplaceAll.getBytes())))) {
                    return strReplaceAll;
                }
            }
            return "{\"response\":{\"resultCode\":1,\"errorCode\":\"900001\",\"errorDesc\":\"signatureError\",\"resultcode\":1,\"errorcode\":\"900001\",\"errordesc\":\"signatureError\"}}";
        } catch (e.b.b e2) {
            e2.printStackTrace();
            return "{\"response\":{\"resultCode\":1,\"errorCode\":\"900001\",\"errorDesc\":\"signatureError\",\"resultcode\":1,\"errorcode\":\"900001\",\"errordesc\":\"signatureError\"}}";
        } catch (NoSuchAlgorithmException e3) {
            e3.printStackTrace();
            return "{\"response\":{\"resultCode\":1,\"errorCode\":\"900001\",\"errorDesc\":\"signatureError\",\"resultcode\":1,\"errorcode\":\"900001\",\"errordesc\":\"signatureError\"}}";
        } catch (Exception e4) {
            e4.printStackTrace();
            return "{\"response\":{\"resultCode\":1,\"errorCode\":\"900001\",\"errorDesc\":\"signatureError\",\"resultcode\":1,\"errorcode\":\"900001\",\"errordesc\":\"signatureError\"}}";
        }
    }

    public static String a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b2 : bArr) {
            stringBuffer.append((int) b2);
        }
        return stringBuffer.toString();
    }

    /* JADX WARN: Code duplicated, block: B:14:0x004a  */
    public static String a(String str, PublicKey publicKey) {
        boolean z;
        Matcher matcher = Pattern.compile("<signatureServer>(.*?)</signatureServer>").matcher(str);
        String strGroup = null;
        CharSequence charSequenceGroup = null;
        while (true) {
            z = true;
            if (!matcher.find()) {
                break;
            }
            charSequenceGroup = matcher.group();
            strGroup = matcher.group(1);
        }
        if (strGroup == null) {
            SDKUtil.LogD("HttpHandler", "signatureServer=null==>" + str);
            return "<?xml version=\"1.0\" encoding=\"utf-8\"?><response><resultCode>1</resultCode><errorCode>900001</errorCode><errorDesc>signatureError</errorDesc><error_code>900001</error_code><error_name>signatureError</error_name></response>";
        }
        if (publicKey != null) {
            try {
                String strReplace = str.replace(charSequenceGroup, "");
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                messageDigest.reset();
                if (!a(strGroup, (Key) publicKey).equals(a(messageDigest.digest(strReplace.getBytes())))) {
                    z = false;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                return "<?xml version=\"1.0\" encoding=\"utf-8\"?><response><resultCode>1</resultCode><errorCode>900001</errorCode><errorDesc>signatureError</errorDesc><error_code>900001</error_code><error_name>signatureError</error_name></response>";
            }
        } else {
            z = false;
        }
        return z ? str.replace(strGroup, "0") : "<?xml version=\"1.0\" encoding=\"utf-8\"?><response><resultCode>1</resultCode><errorCode>900001</errorCode><errorDesc>signatureError</errorDesc><error_code>900001</error_code><error_name>signatureError</error_name></response>";
    }
}
