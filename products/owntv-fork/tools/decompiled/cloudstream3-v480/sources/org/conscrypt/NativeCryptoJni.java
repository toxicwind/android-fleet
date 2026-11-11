package org.conscrypt;

/* JADX INFO: loaded from: classes8.dex */
class NativeCryptoJni {
    public static void init() {
        if ("com.google.android.gms.org.conscrypt".equals(NativeCrypto.class.getPackage().getName())) {
            System.loadLibrary("conscrypt_gmscore_jni");
        } else {
            System.loadLibrary("conscrypt_jni");
        }
    }

    private NativeCryptoJni() {
    }
}
