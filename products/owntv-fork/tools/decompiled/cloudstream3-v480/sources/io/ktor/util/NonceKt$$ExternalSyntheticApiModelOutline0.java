package io.ktor.util;

import android.content.res.loader.ResourcesLoader;
import android.graphics.drawable.ColorStateListDrawable;
import android.util.CloseGuard;
import java.security.cert.PKIXRevocationChecker;
import java.security.interfaces.EdECPrivateKey;
import java.security.interfaces.XECPrivateKey;
import java.security.spec.DSAGenParameterSpec;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import javax.net.ssl.ExtendedSSLSession;
import javax.net.ssl.SNIHostName;
import javax.net.ssl.SNIMatcher;
import javax.net.ssl.SNIServerName;
import javax.net.ssl.X509ExtendedTrustManager;

/* JADX INFO: compiled from: D8$$SyntheticClass */
/* JADX INFO: loaded from: classes7.dex */
public final /* synthetic */ class NonceKt$$ExternalSyntheticApiModelOutline0 {
    public static /* synthetic */ ResourcesLoader m() {
        return new ResourcesLoader();
    }

    public static /* bridge */ /* synthetic */ ColorStateListDrawable m(Object obj) {
        return (ColorStateListDrawable) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ CloseGuard m8836m() {
        return new CloseGuard();
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ CloseGuard m8837m(Object obj) {
        return (CloseGuard) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ EdECPrivateKey m8840m(Object obj) {
        return (EdECPrivateKey) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ XECPrivateKey m8841m(Object obj) {
        return (XECPrivateKey) obj;
    }

    public static /* synthetic */ DSAGenParameterSpec m(int i, int i2) {
        return new DSAGenParameterSpec(i, i2);
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ CompletableFuture m8842m() {
        return new CompletableFuture();
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ CompletionException m8843m(Object obj) {
        return (CompletionException) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ ExtendedSSLSession m8844m(Object obj) {
        return (ExtendedSSLSession) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ SNIHostName m8845m(Object obj) {
        return (SNIHostName) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ SNIHostName m8846m(String str) {
        return new SNIHostName(str);
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ SNIMatcher m8847m(Object obj) {
        return (SNIMatcher) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ SNIServerName m8848m(Object obj) {
        return (SNIServerName) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ X509ExtendedTrustManager m8850m(Object obj) {
        return (X509ExtendedTrustManager) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ void m8851m() {
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ boolean m8853m(Object obj) {
        return obj instanceof ColorStateListDrawable;
    }

    /* JADX INFO: renamed from: m$1, reason: collision with other method in class */
    public static /* synthetic */ void m8857m$1() {
    }

    public static /* bridge */ /* synthetic */ boolean m$1(Object obj) {
        return obj instanceof EdECPrivateKey;
    }

    public static /* synthetic */ void m$2() {
    }

    public static /* bridge */ /* synthetic */ boolean m$2(Object obj) {
        return obj instanceof XECPrivateKey;
    }

    public static /* bridge */ /* synthetic */ boolean m$3(Object obj) {
        return obj instanceof CompletionException;
    }

    public static /* bridge */ /* synthetic */ boolean m$4(Object obj) {
        return obj instanceof ExtendedSSLSession;
    }

    public static /* bridge */ /* synthetic */ boolean m$5(Object obj) {
        return obj instanceof SNIHostName;
    }

    public static /* bridge */ /* synthetic */ boolean m$6(Object obj) {
        return obj instanceof X509ExtendedTrustManager;
    }

    public static /* bridge */ /* synthetic */ boolean m$7(Object obj) {
        return obj instanceof PKIXRevocationChecker;
    }
}
