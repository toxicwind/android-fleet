package com.mediatek.LedFactory;

/* JADX INFO: loaded from: classes.dex */
public class LedFactory {
    private native int nativeGetMuteStatus(int i);

    private native int nativeLedFactoryInit();

    private native int nativeSetLedMode(int i);

    static {
        try {
            System.loadLibrary("ledfapi");
            System.out.println("System.loadLibrary: successfully");
        } catch (UnsatisfiedLinkError e) {
            System.out.println("System.loadLibrary" + e);
            e.printStackTrace(System.out);
        }
    }

    public int initLedFactory() {
        System.out.println("initLedFactory");
        return nativeLedFactoryInit();
    }

    public int setMicLed(int i) {
        System.out.println("setMicLed");
        return nativeSetLedMode(i);
    }

    public int GetMicMuteStatus(int i) {
        System.out.println("GetMicMuteStatus");
        return nativeGetMuteStatus(i);
    }
}
