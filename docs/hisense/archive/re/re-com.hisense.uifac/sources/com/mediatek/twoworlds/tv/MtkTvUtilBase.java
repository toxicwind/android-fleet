package com.mediatek.twoworlds.tv;

import android.util.Log;
import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;
import com.mediatek.twoworlds.tv.model.MtkTvRectangle;
import com.mediatek.twoworlds.tv.model.MtkTvRegionCapability;
import com.mediatek.twoworlds.tv.model.MtkTvSrcVideoResolution;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvUtilBase {
    private static final String PERSIST_KEY = "sys.mtk.keyDisable";
    public static final int PROP_VALUE_MAX = 91;
    public static final String TAG = "MtkTvUtil";
    private MtkTvRectangle dispRctangle = new MtkTvRectangle(0.0f, 0.0f, 0.0f, 0.0f);
    private MtkTvRectangle sourceRectangle = new MtkTvRectangle(0.0f, 0.0f, 0.0f, 0.0f);
    private MtkTvRegionCapability videoSrcRegionCapability = new MtkTvRegionCapability();
    private MtkTvRegionCapability disRegionCapability = new MtkTvRegionCapability();
    private MtkTvSrcVideoResolution srcVideoResolution = new MtkTvSrcVideoResolution();

    public int nativeAppPause(int i) {
        return i;
    }

    public int nativeAppResume(int i) {
        return i;
    }

    public MtkTvUtilBase() {
        Log.d(TAG, "MtkTvUtil object created");
    }

    public static int getWakeUpReason() {
        int wakeUpReason_native = TVNativeWrapper.getWakeUpReason_native();
        Log.d(TAG, "getWakeUpReason = " + wakeUpReason_native);
        return wakeUpReason_native;
    }

    public static int getWakeUpIrKey() {
        int wakeUpIrKey_native = TVNativeWrapper.getWakeUpIrKey_native();
        Log.d(TAG, "getWakeUpIrKey = " + wakeUpIrKey_native);
        return wakeUpIrKey_native;
    }

    public static boolean checkPCLWakeupReasonToBGM() {
        boolean zCheckPCLWakeupReasonToBGM_native = TVNativeWrapper.checkPCLWakeupReasonToBGM_native();
        Log.d(TAG, "checkPCLWakeupReasonToBGM = " + zCheckPCLWakeupReasonToBGM_native);
        return zCheckPCLWakeupReasonToBGM_native;
    }

    public static int getPclWakeupSetup() {
        int pclWakeupSetup_native = TVNativeWrapper.getPclWakeupSetup_native();
        Log.d(TAG, "getPclWakeupSetup, setup=" + pclWakeupSetup_native);
        return pclWakeupSetup_native;
    }

    public static int setPclWakeupSetup(int i) {
        Log.d(TAG, "setPclWakeupSetup, setup=" + i + ", ret=" + TVNativeWrapper.setPclWakeupSetup_native(i));
        return i;
    }

    public static void saveTimeStamp(String str) {
        TVNativeWrapper.saveTimeStamp_native(str);
    }

    public static int setKeyRouteSwitch(boolean z) {
        return setKeyRouteSwitchFromTVRemoteService(z);
    }

    public static int setKeyRouteSwitchFromTVRemoteService(boolean z) {
        Log.d(TAG, "setKeyRouteSwitchFromTVRemoteService " + z);
        if (z) {
            setSystemProperties(PERSIST_KEY, "0");
            TVNativeWrapper.setLinuxKeyReceive_native(0);
        } else {
            setSystemProperties(PERSIST_KEY, "1");
            TVNativeWrapper.setLinuxKeyReceive_native(1);
        }
        return 0;
    }

    public static boolean isBGMapplicable(boolean z) {
        boolean zIsBGMapplicable_native = TVNativeWrapper.isBGMapplicable_native(z);
        Log.d(TAG, "isBGMapplicable = " + zIsBGMapplicable_native);
        return zIsBGMapplicable_native;
    }

    public static int setLinuxBGMStart() {
        int linuxBGMStart_native = TVNativeWrapper.setLinuxBGMStart_native();
        Log.d(TAG, "setLinuxBGMStart = " + linuxBGMStart_native);
        return linuxBGMStart_native;
    }

    public static int setWakeUpReason(int i) {
        int iHighLevel_native = TVNativeWrapper.HighLevel_native(300, i, 0, 0, 0, 0, 0);
        Log.d(TAG, "setWakeUpReason = " + iHighLevel_native);
        return iHighLevel_native;
    }

    public static int getBroadcastMwPowerState() {
        int iHighLevel_native = TVNativeWrapper.HighLevel_native(301, 0, 0, 0, 0, 0, 0);
        Log.d(TAG, "getBroadcastMwPowerState = " + iHighLevel_native);
        return iHighLevel_native;
    }

    public int setScreenOutputDispRect(String str, MtkTvRectangle mtkTvRectangle) {
        return TVNativeWrapper.setScreenOutputDispRect_native(str, mtkTvRectangle);
    }

    public MtkTvRectangle getScreenOutputDispRect(String str) {
        TVNativeWrapper.getScreenOutputDispRect_native(str, this.dispRctangle);
        return this.dispRctangle;
    }

    public int setScreenSourceRect(String str, MtkTvRectangle mtkTvRectangle) {
        return TVNativeWrapper.setScreenSourceRect_native(str, mtkTvRectangle);
    }

    public MtkTvRectangle getScreenSourceRect(String str) {
        TVNativeWrapper.getScreenSourceRect_native(str, this.sourceRectangle);
        return this.sourceRectangle;
    }

    public MtkTvRegionCapability getVideoSrcRegionCapability(String str) {
        TVNativeWrapper.getVideoSrcRegionCapability_native(str, this.videoSrcRegionCapability);
        return this.videoSrcRegionCapability;
    }

    public MtkTvSrcVideoResolution getSrcVideoResolution(String str) {
        TVNativeWrapper.getSrcVideoResolution_native(str, this.srcVideoResolution);
        Log.d(TAG, " getSrcVideoResolution Here path:" + str + " return " + this.srcVideoResolution.toString());
        return this.srcVideoResolution;
    }

    public MtkTvRegionCapability getDispRegionCapability(String str) {
        TVNativeWrapper.getDispRegionCapability_native(str, this.disRegionCapability);
        return this.disRegionCapability;
    }

    public void resetPub() {
        Log.d(TAG, "resetPub entered");
        TVNativeWrapper.resetPub_native();
        Log.d(TAG, "resetPub exit");
    }

    public void resetPri() {
        Log.d(TAG, "resetPri entered");
        TVNativeWrapper.resetPri_native();
        Log.d(TAG, "resetPri exit");
    }

    public void resetFac() {
        Log.d(TAG, "resetFac entered");
        TVNativeWrapper.resetFac_native();
        Log.d(TAG, "resetFac exit");
    }

    public String getSysVersion(int i, String str) {
        Log.d(TAG, "getSysVersion entered");
        String sysVersion_native = TVNativeWrapper.getSysVersion_native(i, str);
        Log.d(TAG, "getSysVersion EXIT: version:" + sysVersion_native);
        return sysVersion_native;
    }

    public boolean isCaptureLogo() {
        Log.d(TAG, "isCaptureLogo entered");
        return TVNativeWrapper.isCaptureLogo_native();
    }

    public int tunerFacQuery(boolean z, List<String> list, List<String> list2) {
        Log.d(TAG, "tunerFacQuery");
        return TVNativeWrapper.tunerFacQuery_navtive(z, list, list2);
    }

    public static boolean getAdbStatus() {
        boolean adbStatus_native = TVNativeWrapper.getAdbStatus_native();
        Log.d(TAG, "getAdbStatus = " + adbStatus_native);
        return adbStatus_native;
    }

    public static int setAdbStatus(boolean z) {
        int adbStatus_native = TVNativeWrapper.setAdbStatus_native(z);
        Log.d(TAG, "setAdbStatus, isAdbEnabled = " + z + ", ret = " + adbStatus_native);
        return adbStatus_native;
    }

    public static int setOSDPlaneEnable(int i, int i2) {
        return TVNativeWrapper.HighLevel_native(112, i, i2, 0, 0, 0, 0);
    }

    public static void rpcCloseClient() {
        Log.d(TAG, "mtktvuitlbase.javarpcCloseClient invoked");
        TVNativeWrapper.rpcCloseClient_native();
    }

    public static int sifInit() {
        return TVNativeWrapper.HighLevel_native(115, 0, 0, 0, 0, 0, 0);
    }

    public static int sifStop() {
        return TVNativeWrapper.HighLevel_native(115, 1, 0, 0, 0, 0, 0);
    }

    public static int sifWriteMultipleSubAddr(int i, int i2, byte b, byte[] bArr, byte[] bArr2) {
        return TVNativeWrapper.sifWriteMultipleSubAddr(i, i2, b, bArr, bArr2);
    }

    public static int sifReadMultipleSubAddr(int i, int i2, byte b, byte[] bArr, byte[] bArr2) {
        return TVNativeWrapper.sifReadMultipleSubAddr(i, i2, b, bArr, bArr2);
    }

    public static int setLocalDimming(boolean z) {
        return TVNativeWrapper.HighLevel_native(116, z ? 1 : 0, 0, 0, 0, 0, 0);
    }

    public static boolean setOpacity(int i) {
        return TVNativeWrapper.setOSDOpacity_native(i);
    }

    public static int setSystemProperties(String str, String str2) {
        if (str2 == null || str2.length() <= 91) {
            return TVNativeWrapper.setSystemProperties_native(str, str2);
        }
        return -1;
    }

    public static int replaceOSDPLA2Color(boolean z, int i) {
        return TVNativeWrapper.HighLevel_native(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_RANGE_AB_DONE, z ? 1 : 0, i, 0, 0, 0, 0);
    }

    public static int setMmMode(boolean z) {
        return TVNativeWrapper.HighLevel_native(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_VIDEO_PRESENT, z ? 1 : 0, 0, 0, 0, 0, 0);
    }

    public static int setDapOnOff(boolean z) {
        return TVNativeWrapper.HighLevel_native(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_VS_BIT_RATE, z ? 1 : 0, 0, 0, 0, 0, 0);
    }
}
