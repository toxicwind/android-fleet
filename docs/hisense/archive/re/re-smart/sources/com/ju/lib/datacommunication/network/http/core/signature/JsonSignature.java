package com.ju.lib.datacommunication.network.http.core.signature;

import android.text.TextUtils;
import android.util.Base64;
import b.a.a.a.a;
import com.ju.lib.datacommunication.network.http.address.ThirdDynamicAddressManager;
import com.ju.lib.datacommunication.network.http.core.HiHttpClient;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

/* JADX INFO: loaded from: classes2.dex */
public final class JsonSignature implements ISignature {
    public static final String COMMON_KEY = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAK26F0+yIWMxpW4WW7vsrwLl8kp8isCarBGv54xOK468ZD6FbOMZAOSj8JBr0IpUzv5w+hURR6W4oINsI4o5CEMCAwEAAQ==";
    public static final String EDUCATION_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC84rAAUNyDJsGC4fP81oDLNnl0\nAgKEGCc0YNj5b/F4yh/yFKDGpJggSiwRJaU04FvN99lb+J3LljqnjsCE+V3/s9af\n6kqvDFm3rhNSxvtrzPkmtU6Vulvbw8VFkAyyZ7IFUTtNyePU4KMRWqR82nMuhr5b\nisU0Hgx0yWqBd6/0kQIDAQAB";
    public static final String SIGN_ALGORITHMS = "MD5withRSA";
    public static final String TAG;
    public String appSecret;
    public final byte[] key;
    public int keyPos;
    public final String mJsonKey;
    public int verifyType;

    static {
        StringBuilder sbA = a.a(HiHttpClient.HTTP_COMMON_TAG);
        sbA.append(JsonSignature.class.getSimpleName());
        TAG = sbA.toString();
    }

    public JsonSignature(String str, String str2, int i, int i2) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("SecretKey is empty! ");
        }
        if (TextUtils.isEmpty(str2)) {
            throw new IllegalArgumentException("JsonKey is empty! ");
        }
        this.key = Base64.decode(str, 0);
        this.mJsonKey = str2;
        this.keyPos = i;
        this.verifyType = i2;
    }

    private boolean checkWithAppSecret(String str, String str2) {
        String strEncodeToString;
        StringBuilder sbA = a.a(str);
        sbA.append(this.appSecret);
        String string = sbA.toString();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            strEncodeToString = Base64.encodeToString(messageDigest.digest(string.getBytes("UTF-8")), 2);
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            strEncodeToString = null;
        } catch (NoSuchAlgorithmException e3) {
            e3.printStackTrace();
            strEncodeToString = null;
        }
        if (str2.equals(strEncodeToString)) {
            return true;
        }
        if (HttpLog.isHttpStackLogAble()) {
            HttpLog.i(TAG, "checkWithAppSecret(), sign = ", string, "  , ----- encodeString = ", strEncodeToString);
        }
        return false;
    }

    public static final JsonSignature createAppStoreSignature() {
        return new JsonSignature("MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAK26F0+yIWMxpW4WW7vsrwLl8kp8isCarBGv54xOK468ZD6FbOMZAOSj8JBr0IpUzv5w+hURR6W4oINsI4o5CEMCAwEAAQ==", "signature", 1, 0);
    }

    public static final JsonSignature createEducationSignature() {
        return new JsonSignature("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC84rAAUNyDJsGC4fP81oDLNnl0\nAgKEGCc0YNj5b/F4yh/yFKDGpJggSiwRJaU04FvN99lb+J3LljqnjsCE+V3/s9af\n6kqvDFm3rhNSxvtrzPkmtU6Vulvbw8VFkAyyZ7IFUTtNyePU4KMRWqR82nMuhr5b\nisU0Hgx0yWqBd6/0kQIDAQAB", "signature", 0, 0);
    }

    public static final JsonSignature createGoSignature() {
        return new JsonSignature("MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAK26F0+yIWMxpW4WW7vsrwLl8kp8isCarBGv54xOK468ZD6FbOMZAOSj8JBr0IpUzv5w+hURR6W4oINsI4o5CEMCAwEAAQ==", ThirdDynamicAddressManager.SIGN_KEY, 0, 1);
    }

    public static final JsonSignature createJsonSignature() {
        return new JsonSignature("MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAK26F0+yIWMxpW4WW7vsrwLl8kp8isCarBGv54xOK468ZD6FbOMZAOSj8JBr0IpUzv5w+hURR6W4oINsI4o5CEMCAwEAAQ==", ThirdDynamicAddressManager.SIGN_KEY, 0, 0);
    }

    @Deprecated
    public static final JsonSignature crerateEducationSignature() {
        return new JsonSignature("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC84rAAUNyDJsGC4fP81oDLNnl0\nAgKEGCc0YNj5b/F4yh/yFKDGpJggSiwRJaU04FvN99lb+J3LljqnjsCE+V3/s9af\n6kqvDFm3rhNSxvtrzPkmtU6Vulvbw8VFkAyyZ7IFUTtNyePU4KMRWqR82nMuhr5b\nisU0Hgx0yWqBd6/0kQIDAQAB", "signature", 0, 0);
    }

    private String decypt(String str, Key key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(2, key);
        return getString(cipher.doFinal(Base64.decode(str, 0)));
    }

    public static boolean doCheck(String str, String str2, PublicKey publicKey, String str3) {
        try {
            Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
            signature.initVerify(publicKey);
            signature.update(str.getBytes(str3));
            boolean zVerify = signature.verify(Base64.decode(str2, 0));
            if (HttpLog.isHttpStackLogAble()) {
                HttpLog.i(TAG, "doCheck(), doCheck = ", Boolean.valueOf(zVerify), " , ----- sign = ", str2);
            }
            return zVerify;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private String getString(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b2 : bArr) {
            sb.append((int) b2);
        }
        return sb.toString();
    }

    private String verifySignerWithMD5(String str) throws SignatureException {
        try {
            String str2 = ",\\s*\"" + this.mJsonKey + "\":\\s*\"(.*?)\"";
            if (this.keyPos == 1) {
                str2 = "\\s*\"" + this.mJsonKey + "\":\\s*\"(.*?)\",";
            }
            String strGroup = null;
            Matcher matcher = Pattern.compile(str2).matcher(str);
            while (matcher.find()) {
                strGroup = matcher.group(1);
            }
            if (!TextUtils.isEmpty(strGroup)) {
                String strReplaceAll = matcher.replaceAll("");
                if (checkWithAppSecret(strReplaceAll, strGroup)) {
                    return strReplaceAll;
                }
                throw new SignatureException("Signature verify error! ");
            }
            throw new SignatureException("Signature verify error ! " + this.mJsonKey + " is null");
        } catch (Exception e2) {
            throw new SignatureException("Signature verify error! ", e2);
        }
    }

    private String verifySignerWithRSA(String str) throws SignatureException {
        try {
            PublicKey publicKeyGeneratePublic = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(this.key));
            if (publicKeyGeneratePublic == null) {
                throw new SignatureException("PublicKey is null! ");
            }
            String str2 = ",\\s*\"" + this.mJsonKey + "\":\\s*\"(.*?)\"";
            if (this.keyPos == 1) {
                str2 = "\\s*\"" + this.mJsonKey + "\":\\s*\"(.*?)\",";
            }
            String strGroup = null;
            Matcher matcher = Pattern.compile(str2).matcher(str);
            while (matcher.find()) {
                strGroup = matcher.group(1);
            }
            if (TextUtils.isEmpty(strGroup)) {
                throw new SignatureException("Signature verify error ! " + this.mJsonKey + " is null");
            }
            String strReplaceAll = matcher.replaceAll("");
            if (this.verifyType != 1) {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                messageDigest.reset();
                if (decypt(strGroup, publicKeyGeneratePublic).equals(getString(messageDigest.digest(strReplaceAll.getBytes("UTF-8"))))) {
                    return strReplaceAll;
                }
                if (HttpLog.isHttpStackLogAble()) {
                    HttpLog.i(TAG, "verifySignerWithRSA(), signature = ", strGroup);
                }
            } else if (doCheck(strReplaceAll, strGroup, publicKeyGeneratePublic, "utf-8")) {
                return strReplaceAll;
            }
            throw new SignatureException("Signature verify error! ");
        } catch (Exception e2) {
            throw new SignatureException("Signature verify error! ", e2);
        }
    }

    @Override // com.ju.lib.datacommunication.network.http.core.signature.ISignature
    public final String verifySigner(String str) {
        return this.appSecret != null ? verifySignerWithMD5(str) : verifySignerWithRSA(str);
    }

    public static final JsonSignature createJsonSignature(String str) {
        return new JsonSignature("MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAK26F0+yIWMxpW4WW7vsrwLl8kp8isCarBGv54xOK468ZD6FbOMZAOSj8JBr0IpUzv5w+hURR6W4oINsI4o5CEMCAwEAAQ==", ThirdDynamicAddressManager.SIGN_KEY, 0, 0, str);
    }

    @Override // com.ju.lib.datacommunication.network.http.core.signature.ISignature
    public String verifySigner(String str, String str2) throws SignatureException {
        if (this.appSecret != null) {
            verifySignerWithMD5(str, str2);
            return str;
        }
        verifySignerWithRSA(str, str2);
        return str;
    }

    public JsonSignature(String str, String str2, int i, int i2, String str3) {
        if (!TextUtils.isEmpty(str)) {
            if (!TextUtils.isEmpty(str2)) {
                this.key = Base64.decode(str, 0);
                this.mJsonKey = str2;
                this.keyPos = i;
                this.verifyType = i2;
                this.appSecret = str3;
                return;
            }
            throw new IllegalArgumentException("JsonKey is empty! ");
        }
        throw new IllegalArgumentException("SecretKey is empty! ");
    }

    private String verifySignerWithMD5(String str, String str2) throws SignatureException {
        try {
            if (!TextUtils.isEmpty(str2)) {
                if (checkWithAppSecret(str, str2)) {
                    return str;
                }
                throw new SignatureException("Signature verify error! ");
            }
            throw new SignatureException("Signature verify error ! sign is null");
        } catch (Exception e2) {
            throw new SignatureException("Signature verify error! ", e2);
        }
    }

    private String verifySignerWithRSA(String str, String str2) throws SignatureException {
        try {
            PublicKey publicKeyGeneratePublic = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(this.key));
            if (publicKeyGeneratePublic != null) {
                if (!TextUtils.isEmpty(str2)) {
                    if (this.verifyType == 1) {
                        if (doCheck(str, str2, publicKeyGeneratePublic, "utf-8")) {
                            return str;
                        }
                    } else {
                        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                        messageDigest.reset();
                        if (decypt(str2, publicKeyGeneratePublic).equals(getString(messageDigest.digest(str.getBytes("UTF-8"))))) {
                            return str;
                        }
                        if (HttpLog.isHttpStackLogAble()) {
                            HttpLog.i(TAG, "verifySignerWithRSA(), sign = ", str2);
                        }
                    }
                    throw new SignatureException("Signature verify error! ");
                }
                throw new SignatureException("Signature verify error ! sign is null");
            }
            throw new SignatureException("PublicKey is null! ");
        } catch (Exception e2) {
            throw new SignatureException("Signature verify error! ", e2);
        }
    }
}
