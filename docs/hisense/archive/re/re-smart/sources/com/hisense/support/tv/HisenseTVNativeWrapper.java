package com.hisense.support.tv;

import com.mediatek.twoworlds.tv.TVNativeWrapper;

/* JADX INFO: loaded from: classes2.dex */
public class HisenseTVNativeWrapper extends TVNativeWrapper {
    public static int ChangeModeforBuiltInVideoUpgrade_native() {
        return TVNativeWrapper.ChangeModeforBuiltInVideoUpgrade_native();
    }

    public static int checkFairPlayKey() {
        return TVNativeWrapper.checkFairPlayKey_native();
    }

    public static int checkPlayready40KeyBox_native() {
        return TVNativeWrapper.checkPlayready40KeyBox_native();
    }

    public static int checkQDAKey() {
        return TVNativeWrapper.checkQDAKey_native();
    }

    public static int checkWideVineKeyBox_native() {
        return TVNativeWrapper.checkWideVineKeyBox_native();
    }

    public static boolean doUnprotectedClear() {
        TVNativeWrapper.doUnprotectedClear();
        return true;
    }

    public static void doresetPub() {
        TVNativeWrapper.doresetPub();
    }

    public static void endLanB2BWorkThread_native() {
        TVNativeWrapper.endLanB2BWorkThread_native();
    }

    public static int factoryCheanKey_native(String str) {
        return TVNativeWrapper.factoryCleanKey_native(str);
    }

    public static int factoryCheckKey(String str) {
        return TVNativeWrapper.factoryCheckKey_native(str);
    }

    public static int factoryCheckKey_native(String str) {
        return TVNativeWrapper.factoryCheckKey_native(str);
    }

    public static String factoryReadFile_native(String str) {
        return TVNativeWrapper.factoryReadFile_native(str);
    }

    public static int factoryRemoveFile_native(String str) {
        return TVNativeWrapper.factoryRemoveFile_native(str);
    }

    public static int factoryWriteAttestationKey_native(String str) {
        return TVNativeWrapper.factoryWriteAttestationKey_native(str);
    }

    public static int factoryWriteFileAppend_native(String str, String str2, int i) {
        return TVNativeWrapper.factoryWriteFileAppend_native(str, str2, i);
    }

    public static int factoryWriteFile_native(String str, String str2) {
        return TVNativeWrapper.factoryWriteFile_native(str, str2);
    }

    public static int factoryWriteKeyFinish_native() {
        return TVNativeWrapper.factoryWriteKeyFinish_native();
    }

    public static int factoryWriteKey_native(String str, String str2) {
        return TVNativeWrapper.factoryWriteKey_native(str, str2);
    }

    public static String getCmdExecResult_native(String str) {
        return TVNativeWrapper.getCmdExecResult_native(str);
    }

    public static int[] getConfigIntArrayValue_native(int i, int i2, String str) {
        return TVNativeWrapper.getConfigIntArrayValue_native(i, i2, str);
    }

    public static String getConfigString_native(String str) {
        return TVNativeWrapper.getConfigString_native(str);
    }

    public static int getConfigValue_native(int i, String str) {
        return TVNativeWrapper.getConfigValue_native(i, str);
    }

    public static int getSelinuxEnvironment_native(String str) {
        return TVNativeWrapper.getSelinuxEnvironment_native(str);
    }

    public static int installQDAKey(String str) {
        return TVNativeWrapper.installQDAKey_native(str);
    }

    public static int isfDataExport_native(String str, String str2) {
        return TVNativeWrapper.isfDataExport_native(str, str2);
    }

    public static int isfDataImport_native(String str) {
        return TVNativeWrapper.isfDataImport_native(str);
    }

    public static String isfDataSearch_native(String str) {
        return TVNativeWrapper.isfDataSearch_native(str);
    }

    public static int mountPerm_native() {
        return TVNativeWrapper.mountPerm_native();
    }

    public static int resetConfigValues_native(int i) {
        return TVNativeWrapper.resetConfigValues_native(i);
    }

    public static int setConfigIntArrayValue_native(String str, int[] iArr, int i) {
        return TVNativeWrapper.setConfigIntArrayValue_native(str, iArr, i);
    }

    public static int setConfigString_native(String str, String str2) {
        return TVNativeWrapper.setConfigString_native(str, str2);
    }

    public static int setConfigValue_native(int i, String str, int i2, int i3) {
        return TVNativeWrapper.setConfigValue_native(i, str, i2, i3);
    }

    public static boolean setEthernetWolCtl_native(boolean z) {
        return TVNativeWrapper.setEthernetWolCtl_native(z);
    }

    public static boolean setWifiWolCtl_native(boolean z) {
        return TVNativeWrapper.setWifiWolCtl_native(z);
    }

    public static void startLanB2BWorkThread_native() {
        TVNativeWrapper.startLanB2BWorkThread_native();
    }

    public static void startSerialWorkThread_native() {
        TVNativeWrapper.startSerialWorkThread_native();
    }

    public static void startTestB2BHWPortWorkThread_native() {
        TVNativeWrapper.startTestB2BHWPortWorkThread_native();
    }

    public static int unmountPerm_native() {
        return TVNativeWrapper.unmountPerm_native();
    }

    public static int writeToExternalControl_native(int i, byte[] bArr, int i2, int i3) {
        return TVNativeWrapper.writeToExternalControl_native(i, bArr, i2, i3);
    }
}
