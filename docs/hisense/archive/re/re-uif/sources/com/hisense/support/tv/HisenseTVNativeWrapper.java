package com.hisense.support.tv;

import com.mediatek.twoworlds.tv.TVNativeWrapper;

/* JADX INFO: loaded from: classes.dex */
public class HisenseTVNativeWrapper extends TVNativeWrapper {
    protected static int getConfigValue_native(int i, String str) {
        return TVNativeWrapper.getConfigValue_native(i, str);
    }

    protected static int writeToExternalControl_native(int i, byte[] bArr, int i2, int i3) {
        return TVNativeWrapper.writeToExternalControl_native(i, bArr, i2, i3);
    }

    protected static void startSerialWorkThread_native() {
        TVNativeWrapper.startSerialWorkThread_native();
    }

    protected static void startTestB2BHWPortWorkThread_native() {
        TVNativeWrapper.startTestB2BHWPortWorkThread_native();
    }

    protected static boolean setWifiWolCtl_native(boolean z) {
        return TVNativeWrapper.setWifiWolCtl_native(z);
    }

    protected static boolean setEthernetWolCtl_native(boolean z) {
        return TVNativeWrapper.setEthernetWolCtl_native(z);
    }

    protected static int setConfigString_native(String str, String str2) {
        return TVNativeWrapper.setConfigString_native(str, str2);
    }

    protected static String getConfigString_native(String str) {
        return TVNativeWrapper.getConfigString_native(str);
    }

    protected static int setConfigValue_native(int i, String str, int i2, int i3) {
        return TVNativeWrapper.setConfigValue_native(i, str, i2, i3);
    }

    protected static int[] getConfigIntArrayValue_native(int i, int i2, String str) {
        return TVNativeWrapper.getConfigIntArrayValue_native(i, i2, str);
    }

    protected static int setConfigIntArrayValue_native(String str, int[] iArr, int i) {
        return TVNativeWrapper.setConfigIntArrayValue_native(str, iArr, i);
    }

    public static void doresetPub() {
        TVNativeWrapper.doresetPub();
    }

    public static int factoryWriteKey_native(String str, String str2) {
        return TVNativeWrapper.factoryWriteKey_native(str, str2);
    }

    public static int factoryCheckKey_native(String str) {
        return TVNativeWrapper.factoryCheckKey_native(str);
    }

    public static int factoryRemoveFile_native(String str) {
        return TVNativeWrapper.factoryRemoveFile_native(str);
    }

    public static int factoryWriteFile_native(String str, String str2) {
        return TVNativeWrapper.factoryWriteFile_native(str, str2);
    }

    public static int checkWideVineKeyBox_native() {
        return TVNativeWrapper.checkWideVineKeyBox_native();
    }

    public static int checkPlayready40KeyBox_native() {
        return TVNativeWrapper.checkPlayready40KeyBox_native();
    }

    public static int factoryWriteAttestationKey_native(String str) {
        return TVNativeWrapper.factoryWriteAttestationKey_native(str);
    }

    public static int mountPerm_native() {
        return TVNativeWrapper.mountPerm_native();
    }

    public static int unmountPerm_native() {
        return TVNativeWrapper.unmountPerm_native();
    }

    public static int checkQDAKey() {
        return TVNativeWrapper.checkQDAKey_native();
    }

    public static int installQDAKey(String str) {
        return TVNativeWrapper.installQDAKey_native(str);
    }
}
