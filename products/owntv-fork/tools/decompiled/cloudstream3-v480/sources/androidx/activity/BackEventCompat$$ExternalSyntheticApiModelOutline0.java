package androidx.activity;

import android.content.Context;
import android.graphics.RenderNode;
import android.hardware.biometrics.BiometricPrompt;
import android.media.ApplicationMediaCapabilities;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.view.WindowInsetsAnimationControlListener;
import android.view.WindowInsetsAnimationController;
import android.view.inspector.InspectionCompanion;
import android.window.BackEvent;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import javax.crypto.KeyAgreement;

/* JADX INFO: compiled from: D8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class BackEventCompat$$ExternalSyntheticApiModelOutline0 {
    public static /* synthetic */ RenderNode m(String str) {
        return new RenderNode(str);
    }

    public static /* synthetic */ BiometricPrompt.CryptoObject m(KeyAgreement keyAgreement) {
        return new BiometricPrompt.CryptoObject(keyAgreement);
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ ApplicationMediaCapabilities.Builder m2m() {
        return new ApplicationMediaCapabilities.Builder();
    }

    public static /* synthetic */ MediaSession m(Context context, String str, Bundle bundle) {
        return new MediaSession(context, str, bundle);
    }

    public static /* bridge */ /* synthetic */ WindowInsetsAnimationControlListener m(Object obj) {
        return (WindowInsetsAnimationControlListener) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ WindowInsetsAnimationController m5m(Object obj) {
        return (WindowInsetsAnimationController) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ InspectionCompanion.UninitializedPropertyMapException m6m() {
        return new InspectionCompanion.UninitializedPropertyMapException();
    }

    public static /* synthetic */ BackEvent m(float f, float f2, float f3, int i) {
        return new BackEvent(f, f2, f3, i);
    }

    public static /* synthetic */ BackEvent m(float f, float f2, float f3, int i, long j) {
        return new BackEvent(f, f2, f3, i, j);
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ OnBackInvokedCallback m7m(Object obj) {
        return (OnBackInvokedCallback) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ OnBackInvokedDispatcher m8m(Object obj) {
        return (OnBackInvokedDispatcher) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ void m10m() {
    }
}
