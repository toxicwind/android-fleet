package com.ju.lib.datacommunication.network.http.g;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes.dex */
public class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Cipher f133a;
    private SecretKeySpec b;
    private IvParameterSpec c;

    private void c() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        if (this.f133a == null) {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            this.f133a = cipher;
            cipher.init(2, this.b, this.c);
        }
    }

    protected byte[] a(byte[] bArr) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        c();
        return this.f133a.doFinal(bArr);
    }

    public void b(byte[] bArr, byte[] bArr2) {
        this.b = new SecretKeySpec(bArr, "AES");
        this.c = new IvParameterSpec(bArr2);
    }
}
