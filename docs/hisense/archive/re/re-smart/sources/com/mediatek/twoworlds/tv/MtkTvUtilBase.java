package com.mediatek.twoworlds.tv;

import android.util.Log;
import b.a.a.a.a;
import com.mediatek.twoworlds.tv.model.MtkTvRectangle;
import com.mediatek.twoworlds.tv.model.MtkTvRegionCapability;
import com.mediatek.twoworlds.tv.model.MtkTvSrcVideoResolution;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvUtilBase {
    public static final String PERSIST_KEY = "sys.mtk.keyDisable";
    public static final int PROP_VALUE_MAX = 91;
    public static final String TAG = "MtkTvUtil";
    public MtkTvRectangle dispRctangle = new MtkTvRectangle(0.0f, 0.0f, 0.0f, 0.0f);
    public MtkTvRectangle sourceRectangle = new MtkTvRectangle(0.0f, 0.0f, 0.0f, 0.0f);
    public MtkTvRegionCapability videoSrcRegionCapability = new MtkTvRegionCapability();
    public MtkTvRegionCapability disRegionCapability = new MtkTvRegionCapability();
    public MtkTvSrcVideoResolution srcVideoResolution = new MtkTvSrcVideoResolution();

    public MtkTvUtilBase() {
        Log.d(TAG, "MtkTvUtil object created");
    }

    public static boolean checkPCLWakeupReasonToBGM() {
        boolean zCheckPCLWakeupReasonToBGM_native = TVNativeWrapper.checkPCLWakeupReasonToBGM_native();
        a.a("checkPCLWakeupReasonToBGM = ", zCheckPCLWakeupReasonToBGM_native, TAG);
        return zCheckPCLWakeupReasonToBGM_native;
    }

    public static boolean getAdbStatus() {
        boolean adbStatus_native = TVNativeWrapper.getAdbStatus_native();
        a.a("getAdbStatus = ", adbStatus_native, TAG);
        return adbStatus_native;
    }

    public static int getBroadcastMwPowerState() {
        int iHighLevel_native = TVNativeWrapper.HighLevel_native(301, 0, 0, 0, 0, 0, 0);
        a.b("getBroadcastMwPowerState = ", iHighLevel_native, TAG);
        return iHighLevel_native;
    }

    public static int getPclWakeupSetup() {
        int pclWakeupSetup_native = TVNativeWrapper.getPclWakeupSetup_native();
        a.b("getPclWakeupSetup, setup=", pclWakeupSetup_native, TAG);
        return pclWakeupSetup_native;
    }

    public static int getWakeUpIrKey() {
        int wakeUpIrKey_native = TVNativeWrapper.getWakeUpIrKey_native();
        a.b("getWakeUpIrKey = ", wakeUpIrKey_native, TAG);
        return wakeUpIrKey_native;
    }

    public static int getWakeUpReason() {
        int wakeUpReason_native = TVNativeWrapper.getWakeUpReason_native();
        a.b("getWakeUpReason = ", wakeUpReason_native, TAG);
        return wakeUpReason_native;
    }

    public static boolean isBGMapplicable(boolean z) {
        boolean zIsBGMapplicable_native = TVNativeWrapper.isBGMapplicable_native(z);
        a.a("isBGMapplicable = ", zIsBGMapplicable_native, TAG);
        return zIsBGMapplicable_native;
    }

    public static int replaceOSDPLA2Color(boolean z, int i) {
        return TVNativeWrapper.HighLevel_native(120, z ? 1 : 0, i, 0, 0, 0, 0);
    }

    public static void rpcCloseClient() {
        Log.d(TAG, "mtktvuitlbase.javarpcCloseClient invoked");
        TVNativeWrapper.rpcCloseClient_native();
    }

    public static void saveTimeStamp(String str) {
        TVNativeWrapper.saveTimeStamp_native(str);
    }

    public static int setAdbStatus(boolean z) {
        int adbStatus_native = TVNativeWrapper.setAdbStatus_native(z);
        Log.d(TAG, "setAdbStatus, isAdbEnabled = " + z + ", ret = " + adbStatus_native);
        return adbStatus_native;
    }

    public static int setDapOnOff(boolean z) {
        return TVNativeWrapper.HighLevel_native(122, z ? 1 : 0, 0, 0, 0, 0, 0);
    }

    public static int setKeyRouteSwitch(boolean z) {
        setKeyRouteSwitchFromTVRemoteService(z);
        return 0;
    }

    public static int setKeyRouteSwitchFromTVRemoteService(boolean z) {
        a.a("setKeyRouteSwitchFromTVRemoteService ", z, TAG);
        if (z) {
            setSystemProperties(PERSIST_KEY, "0");
            TVNativeWrapper.setLinuxKeyReceive_native(0);
        } else {
            setSystemProperties(PERSIST_KEY, "1");
            TVNativeWrapper.setLinuxKeyReceive_native(1);
        }
        return 0;
    }

    public static int setLinuxBGMStart() {
        int linuxBGMStart_native = TVNativeWrapper.setLinuxBGMStart_native();
        a.b("setLinuxBGMStart = ", linuxBGMStart_native, TAG);
        return linuxBGMStart_native;
    }

    public static int setLocalDimming(boolean z) {
        return TVNativeWrapper.HighLevel_native(116, z ? 1 : 0, 0, 0, 0, 0, 0);
    }

    public static int setMmMode(boolean z) {
        return TVNativeWrapper.HighLevel_native(121, z ? 1 : 0, 0, 0, 0, 0, 0);
    }

    public static int setOSDPlaneEnable(int i, int i2) {
        return TVNativeWrapper.HighLevel_native(112, i, i2, 0, 0, 0, 0);
    }

    public static boolean setOpacity(int i) {
        return TVNativeWrapper.setOSDOpacity_native(i);
    }

    public static int setPclWakeupSetup(int i) {
        Log.d(TAG, "setPclWakeupSetup, setup=" + i + ", ret=" + TVNativeWrapper.setPclWakeupSetup_native(i));
        return i;
    }

    public static int setSystemProperties(String str, String str2) {
        if (str2 == null || str2.length() <= 91) {
            return TVNativeWrapper.setSystemProperties_native(str, str2);
        }
        return -1;
    }

    public static int setWakeUpReason(int i) {
        int iHighLevel_native = TVNativeWrapper.HighLevel_native(300, i, 0, 0, 0, 0, 0);
        a.b("setWakeUpReason = ", iHighLevel_native, TAG);
        return iHighLevel_native;
    }

    public static int sifInit() {
        return TVNativeWrapper.HighLevel_native(115, 0, 0, 0, 0, 0, 0);
    }

    public static int sifReadMultipleSubAddr(int i, int i2, byte b2, byte[] bArr, byte[] bArr2) {
        return TVNativeWrapper.sifReadMultipleSubAddr(i, i2, b2, bArr, bArr2);
    }

    public static int sifStop() {
        return TVNativeWrapper.HighLevel_native(115, 1, 0, 0, 0, 0, 0);
    }

    public static int sifWriteMultipleSubAddr(int i, int i2, byte b2, byte[] bArr, byte[] bArr2) {
        return TVNativeWrapper.sifWriteMultipleSubAddr(i, i2, b2, bArr, bArr2);
    }

    public MtkTvRegionCapability getDispRegionCapability(String str) {
        TVNativeWrapper.getDispRegionCapability_native(str, this.disRegionCapability);
        return this.disRegionCapability;
    }

    public MtkTvRectangle getScreenOutputDispRect(String str) {
        TVNativeWrapper.getScreenOutputDispRect_native(str, this.dispRctangle);
        return this.dispRctangle;
    }

    public MtkTvRectangle getScreenSourceRect(String str) {
        TVNativeWrapper.getScreenSourceRect_native(str, this.sourceRectangle);
        return this.sourceRectangle;
    }

    public MtkTvSrcVideoResolution getSrcVideoResolution(String str) {
        TVNativeWrapper.getSrcVideoResolution_native(str, this.srcVideoResolution);
        Log.d(TAG, " getSrcVideoResolution Here path:" + str + " return " + this.srcVideoResolution.toString());
        return this.srcVideoResolution;
    }

    public String getSysVersion(int i, String str) {
        Log.d(TAG, "getSysVersion entered");
        String sysVersion_native = TVNativeWrapper.getSysVersion_native(i, str);
        a.c("getSysVersion EXIT: version:", sysVersion_native, TAG);
        return sysVersion_native;
    }

    public MtkTvRegionCapability getVideoSrcRegionCapability(String str) {
        TVNativeWrapper.getVideoSrcRegionCapability_native(str, this.videoSrcRegionCapability);
        return this.videoSrcRegionCapability;
    }

    public boolean isCaptureLogo() {
        Log.d(TAG, "isCaptureLogo entered");
        return TVNativeWrapper.isCaptureLogo_native();
    }

    public int nativeAppPause(int i) {
        return i;
    }

    public int nativeAppResume(int i) {
        return i;
    }

    public void resetFac() {
        Log.d(TAG, "resetFac entered");
        TVNativeWrapper.resetFac_native();
        Log.d(TAG, "resetFac exit");
    }

    public void resetPri() {
        Log.d(TAG, "resetPri entered");
        TVNativeWrapper.resetPri_native();
        Log.d(TAG, "resetPri exit");
    }

    public void resetPub() {
        Log.d(TAG, "resetPub entered");
        TVNativeWrapper.resetPub_native();
        Log.d(TAG, "resetPub exit");
    }

    public int setScreenOutputDispRect(String str, MtkTvRectangle mtkTvRectangle) {
        return TVNativeWrapper.setScreenOutputDispRect_native(str, mtkTvRectangle);
    }

    public int setScreenSourceRect(String str, MtkTvRectangle mtkTvRectangle) {
        return TVNativeWrapper.setScreenSourceRect_native(str, mtkTvRectangle);
    }

    public int tunerFacQuery(boolean z, List<String> list, List<String> list2) {
        Log.d(TAG, "tunerFacQuery");
        return TVNativeWrapper.tunerFacQuery_navtive(z, list, list2);
    }
}
