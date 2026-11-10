package com.mediatek.LedFactory;

/* JADX INFO: loaded from: classes2.dex */
public class LedFactory {
    static {
        try {
            System.loadLibrary("ledfapi");
            System.out.println("System.loadLibrary: successfully");
        } catch (UnsatisfiedLinkError e2) {
            System.out.println("System.loadLibrary" + e2);
            e2.printStackTrace(System.out);
        }
    }

    public LedFactory() {
    }

    private native int nativeGetMuteStatus(int i);

    private native int nativeLedFactoryInit();

    private native int nativeSetLedMode(int i);

    public int GetMicMuteStatus(int i) {
        System.out.println("GetMicMuteStatus");
        return nativeGetMuteStatus(i);
    }

    public int MuteMonitor(int i) {
        return 0;
    }

    public int deinitLedFactory() {
        return 0;
    }

    public int initLedFactory() {
        System.out.println("initLedFactory");
        return nativeLedFactoryInit();
    }

    public int setMicLed(int i) {
        System.out.println("setMicLed");
        return nativeSetLedMode(i);
    }

    public LedFactory(int i) {
    }
}
