package com.ju.lib.datacommunication.network.http.core.signature;

import android.util.Base64;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

/* JADX INFO: loaded from: classes2.dex */
public final class XmlSignature implements ISignature {
    public static final byte[] KEY = Base64.decode("MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAK26F0+yIWMxpW4WW7vsrwLl8kp8isCarBGv54xOK468ZD6FbOMZAOSj8JBr0IpUzv5w+hURR6W4oINsI4o5CEMCAwEAAQ==", 0);

    private String decypt(String str, Key key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(2, key);
        return getString(cipher.doFinal(Base64.decode(str, 0)));
    }

    private PublicKey getPublicKey() {
        return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(KEY));
    }

    private String getString(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b2 : bArr) {
            sb.append((int) b2);
        }
        return sb.toString();
    }

    private boolean verifySignature(PublicKey publicKey, String str, String str2, String str3) throws NoSuchAlgorithmException {
        String strReplace = str.replace(str2, "");
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.reset();
        return decypt(str3, publicKey).equals(getString(messageDigest.digest(strReplace.getBytes("UTF-8"))));
    }

    @Override // com.ju.lib.datacommunication.network.http.core.signature.ISignature
    public String verifySigner(String str) throws SignatureException {
        Matcher matcher = Pattern.compile("<signatureServer>(.*?)</signatureServer>").matcher(str);
        String strGroup = null;
        String strGroup2 = null;
        while (matcher.find()) {
            strGroup2 = matcher.group();
            strGroup = matcher.group(1);
        }
        if (strGroup == null) {
            throw new SignatureException("Signature is empty! ");
        }
        try {
            if (verifySignature(getPublicKey(), str, strGroup2, strGroup)) {
                return str.replace(strGroup2, "");
            }
            throw new SignatureException("Signature verify error! ");
        } catch (Exception e2) {
            throw new SignatureException("Signature verify error! ", e2);
        }
    }

    private boolean verifySignature(PublicKey publicKey, String str, String str2) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.reset();
        return decypt(str2, publicKey).equals(getString(messageDigest.digest(str.getBytes("UTF-8"))));
    }

    @Override // com.ju.lib.datacommunication.network.http.core.signature.ISignature
    public String verifySigner(String str, String str2) throws SignatureException {
        if (str == null) {
            throw new SignatureException("src is empty! ");
        }
        if (str2 != null) {
            try {
                if (verifySignature(getPublicKey(), str, str2)) {
                    return str;
                }
                throw new SignatureException("Signature verify error! ");
            } catch (Exception e2) {
                throw new SignatureException("Signature verify error! ", e2);
            }
        }
        throw new SignatureException("Signature is empty! ");
    }
}
