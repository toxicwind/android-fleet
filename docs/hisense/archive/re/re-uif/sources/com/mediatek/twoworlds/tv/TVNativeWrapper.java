package com.mediatek.twoworlds.tv;

import android.util.Log;
import com.mediatek.twoworlds.tv.model.MtkTvATSCScanParaBase;
import com.mediatek.twoworlds.tv.model.MtkTvAudioApdBase;
import com.mediatek.twoworlds.tv.model.MtkTvAudioInfoBase;
import com.mediatek.twoworlds.tv.model.MtkTvBisskeyHeader;
import com.mediatek.twoworlds.tv.model.MtkTvBisskeyInfoBase;
import com.mediatek.twoworlds.tv.model.MtkTvCecActiveSourceBase;
import com.mediatek.twoworlds.tv.model.MtkTvCecDevDiscoveryInfoBase;
import com.mediatek.twoworlds.tv.model.MtkTvCecRecordSouceInfoBase;
import com.mediatek.twoworlds.tv.model.MtkTvCecTimeInfoBase;
import com.mediatek.twoworlds.tv.model.MtkTvChannelInfoBase;
import com.mediatek.twoworlds.tv.model.MtkTvChannelQuery;
import com.mediatek.twoworlds.tv.model.MtkTvDvbScanParaBase;
import com.mediatek.twoworlds.tv.model.MtkTvDvbcManualScanParaBase;
import com.mediatek.twoworlds.tv.model.MtkTvDvbcScanParaBase;
import com.mediatek.twoworlds.tv.model.MtkTvDvbsConfigInfoBase;
import com.mediatek.twoworlds.tv.model.MtkTvDvbsSatelliteSettingBase;
import com.mediatek.twoworlds.tv.model.MtkTvEASParaBase;
import com.mediatek.twoworlds.tv.model.MtkTvEventInfoBase;
import com.mediatek.twoworlds.tv.model.MtkTvExternalUIStatusBase;
import com.mediatek.twoworlds.tv.model.MtkTvFavoritelistInfoBase;
import com.mediatek.twoworlds.tv.model.MtkTvFreqChgParamBase;
import com.mediatek.twoworlds.tv.model.MtkTvGamCmdInfoBase;
import com.mediatek.twoworlds.tv.model.MtkTvGingaAppExtendedDataBase;
import com.mediatek.twoworlds.tv.model.MtkTvGingaAppInfoBase;
import com.mediatek.twoworlds.tv.model.MtkTvGingaWebServiceClientInfoBase;
import com.mediatek.twoworlds.tv.model.MtkTvISDBScanParaBase;
import com.mediatek.twoworlds.tv.model.MtkTvNTSCScanParaBase;
import com.mediatek.twoworlds.tv.model.MtkTvOpenVCHIPInfoBase;
import com.mediatek.twoworlds.tv.model.MtkTvOpenVCHIPParaBase;
import com.mediatek.twoworlds.tv.model.MtkTvOpenVCHIPSettingInfoBase;
import com.mediatek.twoworlds.tv.model.MtkTvParserIniInfoBase;
import com.mediatek.twoworlds.tv.model.MtkTvPipPopFucusInfoBase;
import com.mediatek.twoworlds.tv.model.MtkTvRatingConvert2Goo;
import com.mediatek.twoworlds.tv.model.MtkTvRectangle;
import com.mediatek.twoworlds.tv.model.MtkTvRegionCapability;
import com.mediatek.twoworlds.tv.model.MtkTvScreenModeOverscan;
import com.mediatek.twoworlds.tv.model.MtkTvSrcVideoResolution;
import com.mediatek.twoworlds.tv.model.MtkTvTeletextPageBase;
import com.mediatek.twoworlds.tv.model.MtkTvTeletextTopBlockBase;
import com.mediatek.twoworlds.tv.model.MtkTvTeletextTopGroupBase;
import com.mediatek.twoworlds.tv.model.MtkTvTeletextTopPageBase;
import com.mediatek.twoworlds.tv.model.MtkTvTimeRawDataBase;
import com.mediatek.twoworlds.tv.model.MtkTvUSTvRatingSettingInfoBase;
import com.mediatek.twoworlds.tv.model.MtkTvUpgradeDeliveryTypeBase;
import com.mediatek.twoworlds.tv.model.MtkTvUpgradeFirmwareInfoBase;
import com.mediatek.twoworlds.tv.model.MtkTvVideoInfoBase;
import com.mediatek.twoworlds.tv.model.MtkTvVideoStreamInfoBase;
import com.mediatek.twoworlds.tv.model.TvProviderAudioTrackBase;
import com.mediatek.twoworlds.tv.model.TvProviderChannelInfoBase;
import java.util.List;
import mediatek.sysprop.VendorProperties;

/* JADX INFO: loaded from: classes.dex */
public class TVNativeWrapper {
    private static final String TAG = "TVNativeWrapper(Emu)";
    static boolean mIsregisterCallback = false;

    protected static boolean is_emulator() {
        return !VendorProperties.mtk_inside().orElse(Boolean.FALSE).booleanValue();
    }

    protected static int registerDefaultCallback_native() {
        if (mIsregisterCallback || is_emulator()) {
            Log.d(TAG, "registerDefaultCallback_native called");
            return 0;
        }
        mIsregisterCallback = true;
        return TVNative.registerDefaultCallback_native();
    }

    protected static int unregisterDefaultCallback_native() {
        if (!mIsregisterCallback || is_emulator()) {
            Log.d(TAG, "unregisterDefaultCallback_native called");
            return 0;
        }
        mIsregisterCallback = false;
        return TVNative.unregisterDefaultCallback_native();
    }

    protected static int HighLevel_native(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        if (is_emulator()) {
            Log.d(TAG, "HighLevel_native called");
            return 0;
        }
        return TVNative.HighLevel_native(i, i2, i3, i4, i5, i6, i7);
    }

    protected static int launchHbbtv_native(String str, String str2, String str3) {
        if (is_emulator()) {
            Log.d(TAG, "launchHbbtv_native called");
            return 0;
        }
        return TVNative.launchHbbtv_native(str, str2, str3);
    }

    protected static void setSignalMonitor_native(boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "setSignalMonitor_native called");
        } else {
            TVNative.setSignalMonitor_native(z);
        }
    }

    protected static int getWakeUpReason_native() {
        if (is_emulator()) {
            Log.d(TAG, "getWakeUpReason called");
            return 5;
        }
        return TVNative.getWakeUpReason_native();
    }

    protected static int getWakeUpIrKey_native() {
        if (is_emulator()) {
            Log.d(TAG, "getWakeUpIrKey_native called");
            return 0;
        }
        return TVNative.getWakeUpIrKey_native();
    }

    protected static boolean checkPCLWakeupReasonToBGM_native() {
        if (is_emulator()) {
            Log.d(TAG, "checkPCLWakeupReasonToBGM called");
            return false;
        }
        return TVNative.checkPCLWakeupReasonToBGM_native();
    }

    protected static int getPclWakeupSetup_native() {
        if (is_emulator()) {
            Log.d(TAG, "getPclWakeupSetup_native called");
            return 0;
        }
        return TVNative.getPclWakeupSetup_native();
    }

    protected static int setPclWakeupSetup_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "setPclWakeupSetup_native called");
            return 0;
        }
        return TVNative.setPclWakeupSetup_native(i);
    }

    protected static void saveTimeStamp_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "saveTimeStamp_native called");
        } else {
            TVNative.saveTimeStamp_native(str);
        }
    }

    protected static void setLinuxKeyReceive_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "setLinuxKeyReceive_native called");
        } else {
            TVNative.setLinuxKeyReceive_native(i);
        }
    }

    protected static boolean isBGMapplicable_native(boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "isBGMapplicable_native called");
            return false;
        }
        return TVNative.isBGMapplicable_native(z);
    }

    protected static int setLinuxBGMStart_native() {
        if (is_emulator()) {
            Log.d(TAG, "setLinuxBGMStart_native called");
            return 0;
        }
        return TVNative.setLinuxBGMStart_native();
    }

    protected static int setScreenOutputDispRect_native(String str, MtkTvRectangle mtkTvRectangle) {
        if (is_emulator()) {
            Log.d(TAG, "setScreenOutputDispRect_native called");
            return 0;
        }
        return TVNative.setScreenOutputDispRect_native(str, mtkTvRectangle);
    }

    protected static int getScreenOutputDispRect_native(String str, MtkTvRectangle mtkTvRectangle) {
        if (is_emulator()) {
            Log.d(TAG, "getScreenOutputDispRect_native called");
            return 0;
        }
        return TVNative.getScreenOutputDispRect_native(str, mtkTvRectangle);
    }

    protected static int setScreenSourceRect_native(String str, MtkTvRectangle mtkTvRectangle) {
        if (is_emulator()) {
            Log.d(TAG, "setScreenSourceRect_native called");
            return 0;
        }
        return TVNative.setScreenSourceRect_native(str, mtkTvRectangle);
    }

    protected static int getScreenSourceRect_native(String str, MtkTvRectangle mtkTvRectangle) {
        if (is_emulator()) {
            Log.d(TAG, "getScreenSourceRect_native called");
            return 0;
        }
        return TVNative.getScreenSourceRect_native(str, mtkTvRectangle);
    }

    protected static int getVideoSrcRegionCapability_native(String str, MtkTvRegionCapability mtkTvRegionCapability) {
        if (is_emulator()) {
            Log.d(TAG, "getVideoSrcRegionCapability_native");
            return 0;
        }
        return TVNative.getVideoSrcRegionCapability_native(str, mtkTvRegionCapability);
    }

    protected static int getDispRegionCapability_native(String str, MtkTvRegionCapability mtkTvRegionCapability) {
        if (is_emulator()) {
            Log.d(TAG, "getDispRegionCapability_native");
            return 0;
        }
        return TVNative.getDispRegionCapability_native(str, mtkTvRegionCapability);
    }

    protected static int getSrcVideoResolution_native(String str, MtkTvSrcVideoResolution mtkTvSrcVideoResolution) {
        if (is_emulator()) {
            Log.d(TAG, "getSrcVideoResolution_native");
            return 0;
        }
        return TVNative.getSrcVideoResolution_native(str, mtkTvSrcVideoResolution);
    }

    protected static void resetPub_native() {
        if (is_emulator()) {
            Log.d(TAG, "resetPub_native called");
        } else {
            TVNative.resetPub_native();
        }
    }

    protected static void resetPri_native() {
        if (is_emulator()) {
            Log.d(TAG, "resetPri_native called");
        } else {
            TVNative.resetPri_native();
        }
    }

    protected static void resetFac_native() {
        if (is_emulator()) {
            Log.d(TAG, "resetFac_native called");
        } else {
            TVNative.resetFac_native();
        }
    }

    protected static String getSysVersion_native(int i, String str) {
        if (is_emulator()) {
            Log.d(TAG, "getSysVersion_native called");
            return "";
        }
        return TVNative.getSysVersion_native(i, str);
    }

    protected static String getSerialNumber_native() {
        if (is_emulator()) {
            Log.d(TAG, "getSerialNumber_native called");
            return "";
        }
        return TVNative.getSerialNumber_native();
    }

    protected static String getMacAddress_native() {
        if (is_emulator()) {
            Log.d(TAG, "getMacAddress_native called");
            return "";
        }
        return TVNative.getMacAddress_native();
    }

    protected static boolean setOSDOpacity_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "setOSDOpacity_native called");
            return false;
        }
        return TVNative.setOSDOpacity_native(i);
    }

    protected static void SendKeyClick_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "SendKeyClick_native called");
        } else {
            TVNative.SendKeyClick_native(i);
        }
    }

    protected static void SendKey_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "SendKey_native called");
        } else {
            TVNative.SendKey_native(i, i2);
        }
    }

    protected static void SendMouseMove_native(int i, int i2, int i3, int i4) {
        if (is_emulator()) {
            Log.d(TAG, "SendMouseMove_native called");
        } else {
            TVNative.SendMouseMove_native(i, i2, i3, i4);
        }
    }

    protected static void SendMouseButton_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "SendMouseButton_native called");
        } else {
            TVNative.SendMouseButton_native(i, i2);
        }
    }

    protected static int getMinMaxConfigValue_native(int i, String str) {
        if (is_emulator()) {
            Log.d(TAG, "getMinMaxConfigValue_native called");
            return 0;
        }
        return TVNative.getMinMaxConfigValue_native(i, str);
    }

    protected static int getConfigValue_native(int i, String str) {
        if (is_emulator()) {
            Log.d(TAG, "getConfigValue_native called");
            return 0;
        }
        return TVNative.getConfigValue_native(i, str);
    }

    protected static int setConfigValue_native(int i, String str, int i2, int i3) {
        if (is_emulator()) {
            Log.d(TAG, "setConfigValue_native called");
            return 0;
        }
        return TVNative.setConfigValue_native(i, str, i2, i3);
    }

    protected static int isConfigVisible_native(int i, String str) {
        if (is_emulator()) {
            Log.d(TAG, "isConfigVisible_native called");
            return 0;
        }
        return TVNative.isConfigVisible_native(i, str);
    }

    protected static int isConfigEnabled_native(int i, String str) {
        if (is_emulator()) {
            Log.d(TAG, "isConfigEnabled_native called");
            return 0;
        }
        return TVNative.isConfigEnabled_native(i, str);
    }

    protected static int isConfigItemsEnabled_native(int i, String str, MtkTvConfigBase mtkTvConfigBase) {
        if (is_emulator()) {
            Log.d(TAG, "isConfigItemsEnabled_native called");
            return 0;
        }
        return TVNative.isConfigItemsEnabled_native(i, str, mtkTvConfigBase);
    }

    protected static int resetConfigValues_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "resetConfigValues_native called");
            return 0;
        }
        return TVNative.resetConfigValues_native(i);
    }

    protected static int setPipConfig_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "setPipConfig_native called");
            return 0;
        }
        return TVNative.setPipConfig_native(i);
    }

    protected static String getConfigString_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "getConfigString_native called");
            return "";
        }
        return TVNative.getConfigString_native(str);
    }

    protected static int setConfigString_native(String str, String str2) {
        if (is_emulator()) {
            Log.d(TAG, "setConfigString_native called");
            return 0;
        }
        return TVNative.setConfigString_native(str, str2);
    }

    protected static int setConfigStringEx_native(String str, String str2, int i) {
        if (is_emulator()) {
            Log.d(TAG, "setConfigStringEx_native called");
            return 0;
        }
        return TVNative.setConfigStringEx_native(str, str2, i);
    }

    protected static int addConfigListener_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "addConfigListener_native called");
            return 0;
        }
        return TVNative.addConfigListener_native(str);
    }

    protected static int removeConfigListener_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "removeConfigListener_native called");
            return 0;
        }
        return TVNative.removeConfigListener_native(str);
    }

    protected static int getInputSourceTotalNumber_native() {
        if (is_emulator()) {
            Log.d(TAG, "getInputSourceTotalNumber_native called");
            return 0;
        }
        return TVNative.getInputSourceTotalNumber_native();
    }

    protected static int getInputSourceRecbyidx_native(int i, MtkTvInputSourceBase.InputSourceRecord inputSourceRecord) {
        if (is_emulator()) {
            Log.d(TAG, "getInputSourceRecbyidx_native called");
            return 0;
        }
        return TVNative.getInputSourceRecbyidx_native(i, inputSourceRecord);
    }

    protected static String getCurrentInputSourceName_native() {
        if (is_emulator()) {
            Log.d(TAG, "getCurrentInputSourceName_native called");
            return null;
        }
        return TVNative.getCurrentInputSourceName_native();
    }

    protected static String getCurrentInputSourceName_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "getCurrentInputSourceName_native called");
            return null;
        }
        return TVNative.getCurrentInputSourceName_native(str);
    }

    protected static String getInputSourceNamebySourceid_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getInputSourceNamebySourceid_native called");
            return null;
        }
        return TVNative.getInputSourceNamebySourceid_native(i);
    }

    protected static int changeInputSourcebySourceid_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "changeInputSourcebySourceid_native called");
            return 0;
        }
        return TVNative.changeInputSourcebySourceid_native(i);
    }

    protected static int changeInputSourcebySourceid_native(int i, String str) {
        if (is_emulator()) {
            Log.d(TAG, "changeInputSourcebySourceid_native called");
            return 0;
        }
        return TVNative.changeInputSourcebySourceid_native(i, str);
    }

    protected static boolean isBlockEx_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "isBlockEx_native called");
            return false;
        }
        return TVNative.isBlockEx_native(i);
    }

    protected static boolean isBlock_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "isBlock_native called");
            return false;
        }
        return TVNative.isBlock_native(i);
    }

    protected static int block_native(int i, boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "block_native called");
            return 0;
        }
        return TVNative.block_native(i, z);
    }

    protected static int temp_unblock_native(int i, boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "temp_unblock_native called");
            return 0;
        }
        return TVNative.temp_unblock_native(i, z);
    }

    protected static boolean isAutoDetectPlugStatus_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "isAutoDetectPlugStatus_native called");
            return false;
        }
        return TVNative.isAutoDetectPlugStatus_native(i);
    }

    protected static int setAutoDetectPlugStatus_native(int i, boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "setAutoDetectPlugStatus_native called");
            return 0;
        }
        return TVNative.setAutoDetectPlugStatus_native(i, z);
    }

    protected static int getInputLabelIdx_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getInputLabelIdx_native called");
            return 0;
        }
        return TVNative.getInputLabelIdx_native(i);
    }

    protected static int setInputLabelIdx_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "setInputLabelIdx_native called");
            return 0;
        }
        return TVNative.setInputLabelIdx_native(i, i2);
    }

    protected static String getInputLabelUserDefName_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getInputLabelUserDefName_native called");
            return null;
        }
        return TVNative.getInputLabelUserDefName_native(i);
    }

    protected static int setInputLabelUserDefName_native(int i, String str) {
        if (is_emulator()) {
            Log.d(TAG, "setInputLabelUserDefName_native called");
            return 0;
        }
        return TVNative.setInputLabelUserDefName_native(i, str);
    }

    protected static boolean getExternalDeviceHasSignal_native(MtkTvInputSourceBase.InputDeviceType inputDeviceType, int i) {
        if (is_emulator()) {
            Log.d(TAG, "getExternalDeviceHasSignal_native called");
            return false;
        }
        return TVNative.getExternalDeviceHasSignal_native(inputDeviceType, i);
    }

    public static int inputSyncStop_native(String str, boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "inputSyncStop_native called...\n");
            return 0;
        }
        return TVNative.inputSyncStop_native(str, z);
    }

    protected static boolean queryConflict_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "queryConflict_native called");
            return false;
        }
        return TVNative.queryConflict_native(i, i2);
    }

    protected static int getMhlPortNum_native() {
        if (is_emulator()) {
            Log.d(TAG, "getMhlPortNum_native called");
            return 0;
        }
        return TVNative.getMhlPortNum_native();
    }

    protected static int setScartAutoJump_native(boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "setScartAutoJump_native called");
            return 0;
        }
        return TVNative.setScartAutoJump_native(z);
    }

    protected static int getPOPTunerFocus_native() {
        if (is_emulator()) {
            Log.d(TAG, "getPOPTunerFocus_native called");
            return 0;
        }
        return TVNative.getPOPTunerFocus_native();
    }

    protected static int setAudioFocusbySourceid_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "setAudioFocusbySourceid_native called");
            return 0;
        }
        return TVNative.setAudioFocusbySourceid_native(i);
    }

    protected static int setAudioFocus_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "setAudioFocus_native called");
            return 0;
        }
        return TVNative.setAudioFocus_native(str);
    }

    protected static int getAudioFocus_native() {
        if (is_emulator()) {
            Log.d(TAG, "getAudioFocus_native called");
            return 0;
        }
        return TVNative.getAudioFocus_native();
    }

    protected static boolean isTvRunning_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "setAudioFocus_native called");
            return false;
        }
        return TVNative.isTvRunning_native(str);
    }

    protected static int onlyChgFocus_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "onlyChgFocus_native called");
            return 0;
        }
        return TVNative.onlyChgFocus_native(str);
    }

    protected static int setNewTvMode_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "setNewTvMode_native called");
            return 0;
        }
        return TVNative.setNewTvMode_native(i);
    }

    protected static boolean isMultiviewInputsourceAvailable_native(int i, String str) {
        if (is_emulator()) {
            Log.d(TAG, "isMultiviewInputsourceAvailable_native called");
            return false;
        }
        return TVNative.isMultiviewInputsourceAvailable_native(i, str);
    }

    protected static int startMainVideo_native(int i, MtkTvMultiViewBase.Region_Info_T region_Info_T) {
        if (is_emulator()) {
            Log.d(TAG, "startMainVideo_native called");
            return 0;
        }
        return TVNative.startMainVideo_native(i, region_Info_T);
    }

    protected static int startSubVideo_native(int i, MtkTvMultiViewBase.Region_Info_T region_Info_T) {
        if (is_emulator()) {
            Log.d(TAG, "startSubVideo_native called");
            return 0;
        }
        return TVNative.startSubVideo_native(i, region_Info_T);
    }

    protected static int stopMainVideo_native() {
        if (is_emulator()) {
            Log.d(TAG, "stopMainVideo_native called");
            return 0;
        }
        return TVNative.stopMainVideo_native();
    }

    protected static int stopSubVideo_native() {
        if (is_emulator()) {
            Log.d(TAG, "stopSubVideo_native called");
            return 0;
        }
        return TVNative.stopSubVideo_native();
    }

    protected static int setChgSource_native(boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "setChgSource_native called");
            return 0;
        }
        return TVNative.setChgSource_native(z);
    }

    protected static int getChgSource_native() {
        if (is_emulator()) {
            Log.d(TAG, "getChgSource_native called");
            return 0;
        }
        return TVNative.getChgSource_native();
    }

    protected static int incrVolume_native() {
        if (is_emulator()) {
            Log.d(TAG, "incrVolume_native called");
            return 0;
        }
        return TVNative.incrVolume_native();
    }

    protected static int incrVolume_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "incrVolume_native called");
            return 0;
        }
        return TVNative.incrVolume_native(i);
    }

    protected static int decrVolume_native() {
        if (is_emulator()) {
            Log.d(TAG, "decrVolume_native called");
            return 0;
        }
        return TVNative.decrVolume_native();
    }

    protected static int decrVolume_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "decrVolume_native called");
            return 0;
        }
        return TVNative.decrVolume_native(i);
    }

    protected static int setVolume_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "setVolume_native called");
            return 0;
        }
        return TVNative.setVolume_native(i);
    }

    protected static int setVolume_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "setVolume_native called");
            return 0;
        }
        return TVNative.setVolume_native(i, i2);
    }

    protected static int getVolume_native() {
        if (is_emulator()) {
            Log.d(TAG, "getVolume_native called");
            return 0;
        }
        return TVNative.getVolume_native();
    }

    protected static int getVolume_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getVolume_native called");
            return 0;
        }
        return TVNative.getVolume_native(i);
    }

    protected static int setMute_native(boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "setMute_native called");
            return 0;
        }
        return TVNative.setMute_native(z);
    }

    protected static int setMute_native(int i, boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "setMute_native called");
            return 0;
        }
        return TVNative.setMute_native(i, z);
    }

    protected static boolean getMute_native() {
        if (is_emulator()) {
            Log.d(TAG, "setMute_native called");
            return false;
        }
        return TVNative.getMute_native();
    }

    protected static boolean getMute_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "setMute_native called");
            return false;
        }
        return TVNative.getMute_native(i);
    }

    protected static int channelSelect_native(MtkTvChannelInfoBase mtkTvChannelInfoBase, boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "channelSelect_native called...\n");
            return 0;
        }
        return TVNative.channelSelect_native(mtkTvChannelInfoBase, z);
    }

    protected static int channelSelect_native(MtkTvChannelInfoBase mtkTvChannelInfoBase, boolean z, int i) {
        if (is_emulator()) {
            Log.d(TAG, "channelSelect_native called...\n");
            return 0;
        }
        return TVNative.channelSelect_native(mtkTvChannelInfoBase, z, i);
    }

    protected static int channelSelectByKeys_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "channelSelectByKeys_native called...\n");
            return 0;
        }
        return TVNative.channelSelectByKeys_native(i);
    }

    protected static int channelSelectByChannelId_native(int i, boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "channelSelectByChannelId_native called...\n");
            return 0;
        }
        return TVNative.channelSelectByChannelId_native(i, z);
    }

    protected static int channelSelectSilently_native(MtkTvChannelInfoBase mtkTvChannelInfoBase, int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "channelSelectSilently_native called...\n");
            return 0;
        }
        return TVNative.channelSelectSilently_native(mtkTvChannelInfoBase, i, i2);
    }

    protected static int channelSelectByChannelNumber_native(int i, int i2, boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "channelSelectByChannelNumber_native called...\n");
            return 0;
        }
        return TVNative.channelSelectByChannelNumber_native(i, i2, z);
    }

    public static int syncStop_native(String str, boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "syncStop_native called...\n");
            return 0;
        }
        return TVNative.syncStop_native(str, z);
    }

    public static boolean isSignalLoss_native() {
        if (is_emulator()) {
            Log.d(TAG, "isSignalLoss_native called...\n");
            return false;
        }
        return TVNative.isSignalLoss_native();
    }

    public static int getSignalLevel_native() {
        if (is_emulator()) {
            Log.d(TAG, "getSignalLevel_native called...\n");
            return 0;
        }
        return TVNative.getSignalLevel_native();
    }

    public static int getSignalQuality_native() {
        if (is_emulator()) {
            Log.d(TAG, "getSignalQuality_native called...\n");
            return 0;
        }
        return TVNative.getSignalQuality_native();
    }

    public static boolean isOrigScrambleStrm_native(String str, int i) {
        if (is_emulator()) {
            Log.d(TAG, "isOrigScrambleStrm_native called...\n");
            return false;
        }
        return TVNative.isOrigScrambleStrm_native(str, i);
    }

    protected static int switchInputToTvSource_native() {
        if (is_emulator()) {
            Log.d(TAG, "switchInputToTvSource_native called...\n");
            return 0;
        }
        return TVNative.switchInputToTvSource_native();
    }

    protected static boolean isCamInstalled_native() {
        if (is_emulator()) {
            Log.d(TAG, "isCamInstalled_native called...\n");
            return false;
        }
        return TVNative.isCamInstalled_native();
    }

    protected static boolean isSupport4DigitsChannelNo_native() {
        if (is_emulator()) {
            Log.d(TAG, "isSupport4DigitsChannelNo_native called...\n");
            return false;
        }
        return TVNative.isSupport4DigitsChannelNo_native();
    }

    protected static boolean isDigitKeysHandled_native() {
        if (is_emulator()) {
            Log.d(TAG, "isDigitKeysHandled_native called...\n");
            return true;
        }
        return TVNative.isDigitKeysHandled_native();
    }

    protected static void resetDigitKeysHandledFlag_native() {
        if (is_emulator()) {
            Log.d(TAG, "resetDigitKeysHandledFlag_native called...\n");
        } else {
            TVNative.resetDigitKeysHandledFlag_native();
        }
    }

    public static int getConnectAttr_native(String str, int i) {
        if (is_emulator()) {
            Log.d(TAG, "getConnectAttr_native called...\n");
            return -1;
        }
        return TVNative.getConnectAttr_native(str, i);
    }

    public static int changeFreq_native(String str, MtkTvFreqChgParamBase mtkTvFreqChgParamBase) {
        if (is_emulator()) {
            Log.d(TAG, "changeFreq_native called...\n");
            return 0;
        }
        return TVNative.changeFreq_native(str, mtkTvFreqChgParamBase);
    }

    public static int transferExternalUIStatus_native(MtkTvExternalUIStatusBase mtkTvExternalUIStatusBase) {
        if (is_emulator()) {
            Log.d(TAG, "transferExternalUIStatus_native called...\n");
            return 0;
        }
        return TVNative.transferExternalUIStatus_native(mtkTvExternalUIStatusBase);
    }

    protected static int getCurrentChannel_native(MtkTvChannelInfoBase mtkTvChannelInfoBase) {
        if (is_emulator()) {
            Log.d(TAG, "getCurrentChannel_native called...\n");
            return 0;
        }
        return TVNative.getCurrentChannel_native(mtkTvChannelInfoBase);
    }

    protected static MtkTvChannelInfoBase getCurrentChannelFromNav_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getCurrentChannelFromNav_native called...\n");
            return null;
        }
        return TVNative.getCurrentChannelFromNav_native(i);
    }

    protected static int getCurrentChannelId_native() {
        if (is_emulator()) {
            Log.d(TAG, "getCurrentChannelId_native called...\n");
            return 0;
        }
        return TVNative.getCurrentChannelId_native();
    }

    protected static int getChannelType_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getChannelType_native called...\n");
            return 0;
        }
        return TVNative.getChannelType_native(i);
    }

    protected static int cleanChannelList_native(int i, boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "cleanChannelList_native called...\n");
            return 0;
        }
        return TVNative.cleanChannelList_native(i, z);
    }

    protected static int createSnapshot_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "createSnapshot_native called...\n");
            return 0;
        }
        return TVNative.createSnapshot_native(i);
    }

    protected static int restoreSnapshot_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "restoreSnapshot_native called...\n");
            return 0;
        }
        return TVNative.restoreSnapshot_native(i);
    }

    protected static int freeSnapshot_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "freeSnapshot_native called...\n");
            return 0;
        }
        return TVNative.freeSnapshot_native(i);
    }

    protected static int setCurrentChannel_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "setCurrentChannel_native called...\n");
            return 0;
        }
        return TVNative.setCurrentChannel_native(i);
    }

    protected static int set2ndCurrentChannelBySvlId_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "set2ndCurrentChannelBySvlId_native called...\n");
            return 0;
        }
        return TVNative.set2ndCurrentChannelBySvlId_native(i, i2);
    }

    protected static int setCurrentChannelBySvlId_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "setCurrentChannelBySvlId_native called...\n");
            return 0;
        }
        return TVNative.setCurrentChannelBySvlId_native(i, i2);
    }

    protected static int getChannelList_native(int i, List<MtkTvChannelInfoBase> list) {
        if (is_emulator()) {
            Log.d(TAG, "getChannelList_native called...\n");
            return 0;
        }
        return TVNative.getChannelList_native(i, list);
    }

    protected static int getChannelInfoByChannelId_native(int i, MtkTvChannelInfoBase mtkTvChannelInfoBase) {
        if (is_emulator()) {
            Log.d(TAG, "getChannelInfoByChannelId_native called...\n");
            return 0;
        }
        return TVNative.getChannelInfoByChannelId_native(i, mtkTvChannelInfoBase);
    }

    protected static int setChannelList_native(int i, int i2, List<MtkTvChannelInfoBase> list) {
        if (is_emulator()) {
            Log.d(TAG, "setChannelList_native called...\n");
            return 0;
        }
        return TVNative.setChannelList_native(i, i2, list);
    }

    protected static int setChannelListWithoutNotify_native(int i, int i2, List<MtkTvChannelInfoBase> list) {
        if (is_emulator()) {
            Log.d(TAG, "setChannelListWithoutNotify_native called...\n");
            return 0;
        }
        return TVNative.setChannelListWithoutNotify_native(i, i2, list);
    }

    protected static int swapChannelInfo_native(int i, int i2, int i3) {
        if (is_emulator()) {
            Log.d(TAG, "swapChannelInfo_native called...\n");
            return 0;
        }
        return TVNative.swapChannelInfo_native(i, i2, i3);
    }

    protected static int swapDigitalFavorites_native(int i, int i2, int i3, int i4) {
        if (is_emulator()) {
            Log.d(TAG, "swapDigitalFavorites_native called...\n");
            return 0;
        }
        return TVNative.swapDigitalFavorites_native(i, i2, i3, i4);
    }

    protected static int getChannelRFInfo_native(int i, int i2, int i3) {
        if (is_emulator()) {
            Log.d(TAG, "queryCurrentChannelRFInfo_native called...\n");
            return 0;
        }
        return TVNative.getChannelRFInfo_native(i, i2, i3);
    }

    protected static int getDigitalFavoritesList_native(int i, int i2, int i3, List<MtkTvChannelInfoBase> list) {
        if (is_emulator()) {
            Log.d(TAG, "getDigitalFavoritesList_native called...\n");
            return 0;
        }
        return TVNative.getDigitalFavoritesList_native(i, i2, i3, list);
    }

    protected static int setDigitalFavoritesList_native(int i, int i2, int i3, List<MtkTvChannelInfoBase> list, int i4) {
        if (is_emulator()) {
            Log.d(TAG, "setDigitalFavoritesList_native called...\n");
            return 0;
        }
        return TVNative.setDigitalFavoritesList_native(i, i2, i3, list, i4);
    }

    protected static int getFavoriteIdx_native(int i, int i2, int i3, int i4) {
        if (is_emulator()) {
            Log.d(TAG, "getChannelListByFilter_native called...\n");
            return 0;
        }
        return TVNative.getFavoriteIdx_native(i, i2, i3, i4);
    }

    protected static int getChannelListByFavoriteIdx_native(int i, int i2, int i3, boolean z, int i4, int i5, int i6, List<MtkTvChannelInfoBase> list) {
        if (is_emulator()) {
            Log.d(TAG, "getChannelListByFilter_native called...\n");
            return 0;
        }
        return TVNative.getChannelListByFavoriteIdx_native(i, i2, i3, z, i4, i5, i6, list);
    }

    protected static int getChannelListByFilter_native(int i, int i2, int i3, int i4, int i5, List<MtkTvChannelInfoBase> list) {
        if (is_emulator()) {
            Log.d(TAG, "getChannelListByFilter_native called...\n");
            return 0;
        }
        return TVNative.getChannelListByFilter_native(i, i2, i3, i4, i5, list);
    }

    protected static int getChannelCountByFilter_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "getChannelCountByFilter_native called...\n");
            return 0;
        }
        return TVNative.getChannelCountByFilter_native(i, i2);
    }

    protected static int getChannelListByQueryInfo_native(int i, MtkTvChannelQuery mtkTvChannelQuery, List<MtkTvChannelInfoBase> list, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "getChannelListByQueryInfo_native called...\n");
            return 0;
        }
        return TVNative.getChannelListByQueryInfo_native(i, mtkTvChannelQuery, list, i2);
    }

    protected static int getChannelCountByQueryInfo_native(int i, MtkTvChannelQuery mtkTvChannelQuery) {
        if (is_emulator()) {
            Log.d(TAG, "getChannelCountByQueryInfo_native called...\n");
            return 0;
        }
        return TVNative.getChannelCountByQueryInfo_native(i, mtkTvChannelQuery);
    }

    protected static int getChannelCountByBrdcst_native(int i, int i2, int i3) {
        if (is_emulator()) {
            Log.d(TAG, "getChannelCountByMask_native called...\n");
            return 0;
        }
        return TVNative.getChannelCountByBrdcst_native(i, i2, i3);
    }

    protected static int getChannelListByBrdcst_native(int i, int i2, int i3, int i4, List<MtkTvChannelInfoBase> list) {
        if (is_emulator()) {
            Log.d(TAG, "getChannelCountByMask_native called...\n");
            return 0;
        }
        return TVNative.getChannelListByBrdcst_native(i, i2, i3, i4, list);
    }

    protected static int getChannelCountByMask_native(int i, int i2, int i3, int i4) {
        if (is_emulator()) {
            Log.d(TAG, "getChannelCountByMask_native called...\n");
            return 0;
        }
        return TVNative.getChannelCountByMask_native(i, i2, i3, i4);
    }

    protected static int getChannelListByMask_native(int i, int i2, int i3, int i4, int i5, int i6, int i7, List<MtkTvChannelInfoBase> list) {
        if (is_emulator()) {
            Log.d(TAG, "getChannelListByMask_native called...\n");
            return 0;
        }
        return TVNative.getChannelListByMask_native(i, i2, i3, i4, i5, i6, i7, list);
    }

    protected static int getTvproviderBySvlId_native(int i, List<TvProviderChannelInfoBase> list) {
        if (is_emulator()) {
            Log.d(TAG, "getTvproviderBySvlId_native called...\n");
            return 0;
        }
        return TVNative.getTvproviderBySvlId_native(i, list);
    }

    protected static int getAllTvprovider_native(List<TvProviderChannelInfoBase> list) {
        if (is_emulator()) {
            Log.d(TAG, "getAllTvprovider_native called...\n");
            return 0;
        }
        return TVNative.getAllTvprovider_native(list);
    }

    protected static int getTvproviderBySvlRecId_native(int i, int i2, TvProviderChannelInfoBase tvProviderChannelInfoBase) {
        if (is_emulator()) {
            Log.d(TAG, "getTvproviderBySvlRecId_native called...\n");
            return 0;
        }
        return TVNative.getTvproviderBySvlRecId_native(i, i2, tvProviderChannelInfoBase);
    }

    protected static MtkTvChannelInfoBase getChannelInfoBySvlRecId_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "getChannelInfoBySvlRecId_native called...\n");
            return null;
        }
        return TVNative.getChannelInfoBySvlRecId_native(i, i2);
    }

    protected static int deleteChannelByBrdcstType_native(int i, int i2, boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "deleteChannelByBrdcstType called...\n");
            return 0;
        }
        return TVNative.deleteChannelByBrdcstType_native(i, i2, z);
    }

    protected static boolean isChannelNumberExsit_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "isChannelNumberExsit_native called...\n");
            return true;
        }
        return TVNative.isChannelNumberExsit_native(i, i2);
    }

    protected static int getTvproviderOcl_native(List<TvProviderChannelInfoBase> list) {
        if (is_emulator()) {
            Log.d(TAG, "getTvproviderOcl_native called...\n");
            return 0;
        }
        return TVNative.getTvproviderOcl_native(list);
    }

    protected static int favoriteListInsertMove_native(int i, int i2, int i3, int i4) {
        if (is_emulator()) {
            Log.d(TAG, "favoriteListInsertMove called...\n");
            return 0;
        }
        return TVNative.favoriteListInsertMove_native(i, i2, i3, i4);
    }

    protected static int favoriteListAddTail_native(int i, int i2, int i3) {
        if (is_emulator()) {
            Log.d(TAG, "favoriteListAddTail called...\n");
            return 0;
        }
        return TVNative.favoriteListAddTail_native(i, i2, i3);
    }

    protected static int oneChannelListArrange_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "getTvproviderOcl_native called... svlIdT=\n" + i + ", svlIdC=" + i2);
            return 0;
        }
        return TVNative.oneChannelListArrange_native(i, i2);
    }

    protected static int getChannelListMode_native() {
        if (is_emulator()) {
            Log.d(TAG, "getChannelListMode_native called...\n");
            return 0;
        }
        return TVNative.getChannelListMode_native();
    }

    protected static int getChannelPumpVer_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getChannelPumpVer_native called. svlId = " + i);
            return 0;
        }
        return TVNative.getChannelPumpVer_native(i);
    }

    protected static int setChannelPumpVer_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "setChannelPumpVer_native called. svlId = " + i + ", pumpVer = " + i2);
            return 0;
        }
        return TVNative.setChannelPumpVer_native(i, i2);
    }

    protected static int oneChannelListAddToMax_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "oneChannelListAddToMax_native called. svlId = " + i + ", svlRecId = " + i2);
            return 0;
        }
        return TVNative.oneChannelListAddToMax_native(i, i2);
    }

    protected static int getFavoritelistByFilter_native(List<MtkTvFavoritelistInfoBase> list) {
        if (is_emulator()) {
            Log.d(TAG, "getFavoritelistByFilter_native called...\n");
            return 0;
        }
        return TVNative.getFavoritelistByFilter_native(list);
    }

    protected static int addFavoritelistChannel_native() {
        if (is_emulator()) {
            Log.d(TAG, "addFavoritelistChannel_native called...\n");
            return 0;
        }
        return TVNative.addFavoritelistChannel_native();
    }

    protected static int addFavoritelistChannelByIndex_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "addFavoritelistChannelByIndex_native called...\n");
            return 0;
        }
        return TVNative.addFavoritelistChannelByIndex_native(i);
    }

    protected static int removeFavoritelistChannelByIndexWithoutShowFavIcon_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "removeFavoritelistChannelByIndexWithoutShowFavIcon_native called...\n");
            return 0;
        }
        return TVNative.removeFavoritelistChannelByIndexWithoutShowFavIcon_native(i);
    }

    protected static int removeFavoritelistChannel_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "removeFavoritelistChannel_native called...\n");
            return 0;
        }
        return TVNative.removeFavoritelistChannel_native(i);
    }

    protected static int storeFavoritelistChannel_native() {
        if (is_emulator()) {
            Log.d(TAG, "storeFavoritelistChannel_native called...\n");
            return 0;
        }
        return TVNative.storeFavoritelistChannel_native();
    }

    protected static int swapFavoritelistByIndex_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "swapFavoritelistByIndex_native called...\n");
            return 0;
        }
        return TVNative.swapFavoritelistByIndex_native(i, i2);
    }

    protected static int satllistLockDatabase_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "satllistLockDatabase_native called...\n");
            return 0;
        }
        return TVNative.satllistLockDatabase_native(i);
    }

    protected static int satllistUnLockDatabase_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "satllistUnLockDatabase_native called...\n");
            return 0;
        }
        return TVNative.satllistUnLockDatabase_native(i);
    }

    protected static int satllistReadLockDatabase_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "satllistReadLockDatabase_native called...\n");
            return 0;
        }
        return TVNative.satllistReadLockDatabase_native(i);
    }

    protected static int satllistReadUnLockDatabase_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "satllistReadUnLockDatabase_native called...\n");
            return 0;
        }
        return TVNative.satllistReadUnLockDatabase_native(i);
    }

    protected static int satllistCleanDatabase_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "satllistCleanDatabase_native called...\n");
            return 0;
        }
        return TVNative.satllistCleanDatabase_native(i);
    }

    protected static int getSatlRecord_native(int i, int i2, List<MtkTvDvbsConfigInfoBase> list) {
        if (is_emulator()) {
            Log.d(TAG, "getSatlRecordx_native called...\n");
            return 0;
        }
        return TVNative.getSatlRecord_native(i, i2, list);
    }

    protected static int getSatlRecordByRecIdx_native(int i, int i2, List<MtkTvDvbsConfigInfoBase> list) {
        if (is_emulator()) {
            Log.d(TAG, "getSatlRecordByRecIdx_native called...\n");
            return 0;
        }
        return TVNative.getSatlRecordByRecIdx_native(i, i2, list);
    }

    protected static int getSatlNumRecs_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getSatlNumRecs_native called...\n");
            return 0;
        }
        return TVNative.getSatlNumRecs_native(i);
    }

    protected static int updateSatlRecord_native(int i, MtkTvDvbsConfigInfoBase mtkTvDvbsConfigInfoBase, boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "updateSatlRecord_native called...\n");
            return 0;
        }
        return TVNative.updateSatlRecord_native(i, mtkTvDvbsConfigInfoBase, z);
    }

    protected static int getRecordsNumber_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getRecordsNumber_native called...\n");
            return 0;
        }
        return TVNative.getRecordsNumber_native(i);
    }

    protected static int setBisskeyInfo_native(int i, MtkTvBisskeyInfoBase mtkTvBisskeyInfoBase, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "setBisskeyInfo_native called...\n");
            return 0;
        }
        return TVNative.setBisskeyInfo_native(i, mtkTvBisskeyInfoBase, i2);
    }

    protected static MtkTvBisskeyInfoBase getRecordByBslRecId_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "getRecordByBslRecId_native called...\n");
            return null;
        }
        return TVNative.getRecordByBslRecId_native(i, i2);
    }

    protected static int getRecordNumByHeader_native(int i, MtkTvBisskeyHeader mtkTvBisskeyHeader) {
        if (is_emulator()) {
            Log.d(TAG, "getRecordNumByHeader_native called...\n");
            return 0;
        }
        return TVNative.getRecordNumByHeader_native(i, mtkTvBisskeyHeader);
    }

    protected static MtkTvBisskeyInfoBase getRecordByHeader_native(int i, MtkTvBisskeyHeader mtkTvBisskeyHeader, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "getRecordByHeader_native called...\n");
            return null;
        }
        return TVNative.getRecordByHeader_native(i, mtkTvBisskeyHeader, i2);
    }

    protected static MtkTvBisskeyInfoBase getRecordByIndex_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "getRecordByIndex_native called...\n");
            return null;
        }
        return TVNative.getRecordByIndex_native(i, i2);
    }

    protected static int bisskeyClean_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "bisskeyClean_native called...\n");
            return 0;
        }
        return TVNative.bisskeyClean_native(i);
    }

    protected static int bisskeyIsSameRecord_native(MtkTvBisskeyInfoBase mtkTvBisskeyInfoBase, MtkTvBisskeyInfoBase mtkTvBisskeyInfoBase2) {
        if (is_emulator()) {
            Log.d(TAG, "bisskeyIsSameRecord_native called...\n");
            return 0;
        }
        return TVNative.bisskeyIsSameRecord_native(mtkTvBisskeyInfoBase, mtkTvBisskeyInfoBase2);
    }

    protected static MtkTvBisskeyInfoBase bisskeyGetDefaultRecord_native() {
        if (is_emulator()) {
            Log.d(TAG, "bisskeyGetDefaultRecord_native called...\n");
            return null;
        }
        return TVNative.bisskeyGetDefaultRecord_native();
    }

    protected static int bisskeySetKeyForCurrentChannel_native() {
        if (is_emulator()) {
            Log.d(TAG, "bisskeySetKeyForCurrentChannel_native called...\n");
            return 0;
        }
        return TVNative.bisskeySetKeyForCurrentChannel_native();
    }

    protected static int getCecDevInfo_native(int i, MtkTvCecBase.CecDevInfo cecDevInfo) {
        if (is_emulator()) {
            Log.d(TAG, "getCecDevInfo_native called...\n");
            return 0;
        }
        return TVNative.getCecDevInfo_native(i, cecDevInfo);
    }

    protected static int getCecDevListInfo_native(List<MtkTvCecBase.CecDevInfo> list) {
        if (is_emulator()) {
            Log.d(TAG, "getCecDevListInfo_native called...\n");
            return 0;
        }
        return TVNative.getCecDevListInfo_native(list);
    }

    protected static int getActiveSourceInfo_native(MtkTvCecActiveSourceBase mtkTvCecActiveSourceBase) {
        if (is_emulator()) {
            Log.d(TAG, "getActiveSourceInfo_native called...\n");
            return 0;
        }
        return TVNative.getActiveSourceInfo_native(mtkTvCecActiveSourceBase);
    }

    protected static int setStandby_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "setStandby_native called...\n");
            return 0;
        }
        return TVNative.setStandby_native(i);
    }

    protected static int setUserCtrlPressed_native(MtkTvCecBase.CecUserCtrlInfo cecUserCtrlInfo) {
        if (is_emulator()) {
            Log.d(TAG, "setUserCtrlPressed_native called...\n");
            return 0;
        }
        return TVNative.setUserCtrlPressed_native(cecUserCtrlInfo);
    }

    protected static int setUserCtrlReleased_native(MtkTvCecBase.CecUserCtrlInfo cecUserCtrlInfo) {
        if (is_emulator()) {
            Log.d(TAG, "setUserCtrlReleased_native called...\n");
            return 0;
        }
        return TVNative.setUserCtrlReleased_native(cecUserCtrlInfo);
    }

    protected static int setSystemAudioModeRequest_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "setSystemAudioModeRequest_native called...\n");
            return 0;
        }
        return TVNative.setSystemAudioModeRequest_native(i);
    }

    protected static int setTimer_native(int i, MtkTvCecTimeInfoBase mtkTvCecTimeInfoBase) {
        if (is_emulator()) {
            Log.d(TAG, "setTimer_native called...\n");
            return 0;
        }
        return TVNative.setTimer_native(i, mtkTvCecTimeInfoBase);
    }

    protected static int setRecordOn_native(int i, MtkTvCecRecordSouceInfoBase mtkTvCecRecordSouceInfoBase) {
        if (is_emulator()) {
            Log.d(TAG, "setRecordOn_native called...\n");
            return 0;
        }
        return TVNative.setRecordOn_native(i, mtkTvCecRecordSouceInfoBase);
    }

    protected static int setRecordOff_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "setRecordOff_native called...\n");
            return 0;
        }
        return TVNative.setRecordOff_native(i);
    }

    protected static int setVendorCmdWithId_native(MtkTvCecBase.CecVndrCmdWithIdInfo cecVndrCmdWithIdInfo) {
        if (is_emulator()) {
            Log.d(TAG, "setVendorCmdWithId_native called...\n");
            return 0;
        }
        return TVNative.setVendorCmdWithId_native(cecVndrCmdWithIdInfo);
    }

    protected static int isDeviceExist_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "isDeviceExist_native called...\n");
            return 0;
        }
        return TVNative.isDeviceExist_native(i);
    }

    protected static int notifyCecCompInfo_native(String str, int i, String str2) {
        if (is_emulator()) {
            Log.d(TAG, "notifyCecCompInfo_native called...\n");
            return 0;
        }
        return TVNative.notifyCecCompInfo_native(str, i, str2);
    }

    protected static int discoveryDevice_native(MtkTvCecDevDiscoveryInfoBase mtkTvCecDevDiscoveryInfoBase) {
        if (is_emulator()) {
            Log.d(TAG, "discoveryDevice_native called...\n");
            return 0;
        }
        return TVNative.discoveryDevice_native(mtkTvCecDevDiscoveryInfoBase);
    }

    protected static int powerOnDeviceByLogicAddr_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "powerOnDeviceByLogicAddr_native called...\n");
            return 0;
        }
        return TVNative.powerOnDeviceByLogicAddr_native(i);
    }

    protected static int setStandbyToAll_native(boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "setStandbyToAll_native called...\n");
            return 0;
        }
        return TVNative.setStandbyToAll_native(z);
    }

    public static int getEventListByChannelId_native(int i, long j, long j2, int i2, List<MtkTvEventInfoBase> list) {
        if (is_emulator()) {
            Log.d(TAG, "getEventListByChannelId_native called...\n");
            return 0;
        }
        return TVNative.getEventListByChannelId_native(i, j, j2, i2, list);
    }

    protected static void setEventCallback_native(boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "setEventCallback_native called...\n");
        } else {
            TVNative.setEventCallback_native(z);
        }
    }

    protected static void getEventInfoByEventId_native(int i, int i2, MtkTvEventInfoBase mtkTvEventInfoBase) {
        if (is_emulator()) {
            Log.d(TAG, "getEventInfoByEventId_native called...\n");
        } else {
            TVNative.getEventInfoByEventId_native(i, i2, mtkTvEventInfoBase);
        }
    }

    protected static void setCurrentActiveWindowsInfo_native(List<MtkTvChannelInfoBase> list, long j) {
        if (is_emulator()) {
            Log.d(TAG, "setCurrentActiveWindowsInfo_native called...\n");
        } else {
            TVNative.setCurrentActiveWindowsInfo_native(list, j);
        }
    }

    protected static void setCurrentActiveWindowsInfoEx_native(List<MtkTvChannelInfoBase> list, long j, long j2) {
        if (is_emulator()) {
            Log.d(TAG, "setCurrentActiveWindowsInfoEx_native called...\n");
        } else {
            TVNative.setCurrentActiveWindowsInfoEx_native(list, j, j2);
        }
    }

    protected static int clearActiveWindow_native() {
        if (is_emulator()) {
            Log.d(TAG, "clearActiveWindow_native called...\n");
            return 0;
        }
        return TVNative.clearActiveWindow_native();
    }

    protected static boolean checkEventBlock_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "checkEventBlock called...\n");
            return false;
        }
        return TVNative.checkEventBlock_native(i, i2);
    }

    protected static int getEventRatingMapById_native(int i, int i2, MtkTvRatingConvert2Goo mtkTvRatingConvert2Goo) {
        if (is_emulator()) {
            Log.d(TAG, "getEventRatingMapById_native called...\n");
            return 0;
        }
        return TVNative.getEventRatingMapById_native(i, i2, mtkTvRatingConvert2Goo);
    }

    protected static int[] getCurrentActiveWinChannelList_native() {
        if (is_emulator()) {
            Log.d(TAG, "getCurrentActiveWinChannelList called...\n");
            return null;
        }
        return TVNative.getCurrentActiveWinChannelList_native();
    }

    protected static long getCurrentActiveWinStartTime_native() {
        if (is_emulator()) {
            Log.d(TAG, "getCurrentActiveWinStartTime called...\n");
            return 0L;
        }
        return TVNative.getCurrentActiveWinStartTime_native();
    }

    protected static long getCurrentActiveWinEndTime_native() {
        if (is_emulator()) {
            Log.d(TAG, "getCurrentActiveWinEndTime called...\n");
            return 0L;
        }
        return TVNative.getCurrentActiveWinEndTime_native();
    }

    protected static int getPFEventInfoByChannel_native(int i, boolean z, MtkTvEventInfoBase mtkTvEventInfoBase) {
        if (is_emulator()) {
            Log.d(TAG, "getPFEventInfoByChannel called...\n");
            return 0;
        }
        return TVNative.getPFEventInfoByChannel_native(i, z, mtkTvEventInfoBase);
    }

    protected static int getBookingCount_native() {
        if (is_emulator()) {
            Log.d(TAG, "getBookingCount_native called...\n");
            return 0;
        }
        return TVNative.getBookingCount_native();
    }

    protected static int getBookingID(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getBookingID called...\n");
            return -1;
        }
        return TVNative.getBookingID_native(i);
    }

    protected static int getBookingChannelId(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getBookingChannelId called...\n");
            return -1;
        }
        return TVNative.getBookingChannelId_native(i);
    }

    protected static int setBookingChannelId(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "setBookingChannelId called...\n");
            return -1;
        }
        return TVNative.setBookingChannelId_native(i, i2);
    }

    protected static int getBookingDeviceIndex(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getBookingDeviceIndex called...\n");
            return -1;
        }
        return TVNative.getBookingDeviceIndex_native(i);
    }

    protected static int setBookingDeviceIndex(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "setBookingDeviceIndex called...\n");
            return -1;
        }
        return TVNative.setBookingDeviceIndex_native(i, i2);
    }

    protected static String getBookingEventTitle(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getBookingEventTitle called...\n");
            return "";
        }
        return TVNative.getBookingEventTitle_native(i);
    }

    protected static int setBookingEventTitle(int i, String str) {
        if (is_emulator()) {
            Log.d(TAG, "setBookingEventTitle called...\n");
            return 0;
        }
        return TVNative.setBookingEventTitle_native(i, str);
    }

    protected static int getBookingGenre(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getBookingGenre called...\n");
            return -1;
        }
        return TVNative.getBookingGenre_native(i);
    }

    protected static int setBookingGenre(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "setBookingGenre called...\n");
            return -1;
        }
        return TVNative.setBookingGenre_native(i, i2);
    }

    protected static int getBookingInfoData(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getBookingInfoData called...\n");
            return -1;
        }
        return TVNative.getBookingInfoData_native(i);
    }

    protected static int setBookingInfoData(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "setBookingInfoData called...\n");
            return -1;
        }
        return TVNative.setBookingInfoData_native(i, i2);
    }

    protected static int getBookingRecordDelay(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getBookingRecordDelay called...\n");
            return -1;
        }
        return TVNative.getBookingRecordDelay_native(i);
    }

    protected static int setBookingRecordDelay(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "setBookingRecordDelay called...\n");
            return -1;
        }
        return TVNative.setBookingRecordDelay_native(i, i2);
    }

    protected static long getBookingRecordDuration(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getBookingRecordDuration called...\n");
            return -1L;
        }
        return TVNative.getBookingRecordDuration_native(i);
    }

    protected static int setBookingRecordDuration(int i, long j) {
        if (is_emulator()) {
            Log.d(TAG, "setBookingRecordDuration called...\n");
            return -1;
        }
        return TVNative.setBookingRecordDuration_native(i, j);
    }

    protected static int getBookingRecordMode(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getBookingRecordMode called...\n");
            return -1;
        }
        return TVNative.getBookingRecordMode_native(i);
    }

    protected static int setBookingRecordMode(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "setBookingRecordMode called...\n");
            return -1;
        }
        return TVNative.setBookingRecordMode_native(i, i2);
    }

    protected static int getBookingRepeatMode(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getBookingRepeatMode called...\n");
            return -1;
        }
        return TVNative.getBookingRepeatMode_native(i);
    }

    protected static int setBookingRepeatMode(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "setBookingRepeatMode called...\n");
            return -1;
        }
        return TVNative.setBookingRepeatMode_native(i, i2);
    }

    protected static int getBookingSourceType(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getBookingSourceType called...\n");
            return -1;
        }
        return TVNative.getBookingSourceType_native(i);
    }

    protected static int setBookingSourceType(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "setBookingSourceType called...\n");
            return -1;
        }
        return TVNative.setBookingSourceType_native(i, i2);
    }

    protected static int getBookingTunerType(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getBookingTunerType called...\n");
            return -1;
        }
        return TVNative.getBookingTunerType_native(i);
    }

    protected static int setBookingTunerType(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "setBookingTunerType called...\n");
            return -1;
        }
        return TVNative.setBookingTunerType_native(i, i2);
    }

    protected static long getBookingStartTime(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getBookingDeviceIndex called...\n");
            return -1L;
        }
        return TVNative.getBookingStartTime_native(i);
    }

    protected static int setBookingStartTime(int i, long j) {
        if (is_emulator()) {
            Log.d(TAG, "setBookingStartTime called...\n");
            return -1;
        }
        return TVNative.setBookingStartTime_native(i, j);
    }

    protected static int getBookingType(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getBookingType called...\n");
            return -1;
        }
        return TVNative.getBookingType_native(i);
    }

    protected static int setBookingType(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "setBookingType called...\n");
            return -1;
        }
        return TVNative.setBookingType_native(i, i2);
    }

    protected static int getBookingEventId(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getBookingEventId called...\n");
            return -1;
        }
        return TVNative.getBookingEventId_native(i);
    }

    protected static int setBookingEventId(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "setBookingEventId called...\n");
            return -1;
        }
        return TVNative.setBookingEventId_native(i, i2);
    }

    protected static String getBookingUrlInfo(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getBookingUrlInfo called...\n");
            return "";
        }
        return TVNative.getBookingUrlInfo_native(i);
    }

    protected static int setBookingUrlInfo(int i, String str) {
        if (is_emulator()) {
            Log.d(TAG, "setBookingUrlInfo called...\n");
            return 0;
        }
        return TVNative.setBookingUrlInfo_native(i, str);
    }

    protected static boolean addBooking_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "addBooking_native called...\n");
            return true;
        }
        return TVNative.addBooking_native(i, i2);
    }

    protected static int delBooking_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "delBooking_native called...\n");
            return -1;
        }
        return TVNative.delBooking_native(i);
    }

    protected static boolean addBooking_native(int i, long j, int i2, String str) {
        if (is_emulator()) {
            Log.d(TAG, "addBooking_native called...\n");
            return true;
        }
        return TVNative.addBooking_native(i, j, i2, str);
    }

    protected static void updateBooking_native() {
        if (is_emulator()) {
            Log.d(TAG, "updateBooking_native called...\n");
        } else {
            TVNative.updateBooking_native();
        }
    }

    protected static String getRecordingFile_native() {
        if (is_emulator()) {
            Log.d(TAG, "getRecordingFile_native called...\n");
            return "";
        }
        return TVNative.getRecordingFile_native();
    }

    protected static String getRecordingFileByHandle_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getRecordingFile_native called...\n");
            return "";
        }
        return TVNative.getRecordingFileByHandle_native(i);
    }

    protected static boolean setRecordRegisterFile_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "setRecordRegisterFile_native called...\n");
            return true;
        }
        return TVNative.setRecordRegisterFile_native(str);
    }

    protected static boolean checkRecordRegisterFile_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "checkRecordRegisterFile_native called...\n");
            return true;
        }
        return TVNative.checkRecordRegisterFile_native(str);
    }

    protected static boolean SetRecordDisk(String str) {
        if (is_emulator()) {
            Log.d(TAG, "SetRecordDisk called...\n");
            return true;
        }
        return TVNative.SetRecordDisk_native(str);
    }

    protected static String getRecordDisk() {
        if (is_emulator()) {
            Log.d(TAG, "getPvrBrowserItemTime_native called...\n");
            return "";
        }
        return TVNative.GetRecordDisk_native();
    }

    protected static boolean SetRecordDiskByHandle(int i, String str) {
        if (is_emulator()) {
            Log.d(TAG, "SetRecordDisk called...\n");
            return true;
        }
        return TVNative.SetRecordDiskByHandle_native(i, str);
    }

    protected static boolean recordStartSpeedTest(String str, int i) {
        if (is_emulator()) {
            Log.d(TAG, "recordStartSpeedTest called...\n");
            return true;
        }
        return TVNative.recordStartSpeedTest_native(str, i);
    }

    protected static long getStorageFreeSize_native() {
        if (is_emulator()) {
            Log.d(TAG, "getStorageFreeSize called...\n");
            return 0L;
        }
        return TVNative.getStorageFreeSize_native();
    }

    protected static int getRecordingPosition() {
        if (is_emulator()) {
            Log.d(TAG, "getRecordingPosition called...\n");
            return 0;
        }
        return TVNative.getRecordingPosition();
    }

    protected static int getRecordingPositionByHandle(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getRecordingPosition called...\n");
            return 0;
        }
        return TVNative.getRecordingPositionByHandle(i);
    }

    protected static long getStorageSize_native() {
        if (is_emulator()) {
            Log.d(TAG, "getStorageSize called...\n");
            return 0L;
        }
        return TVNative.getStorageSize_native();
    }

    protected static int setRecordModeByHandle_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "setRecordModeByHandle called...\n");
            return 0;
        }
        Log.d(TAG, "setRecordModeByHandle \n");
        return TVNative.setRecordModeByHandle_native(i, i2);
    }

    protected static int getRecordModeByHandle_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getRecordModeByHandle called...\n");
            return 0;
        }
        return TVNative.getRecordModeByHandle_native(i);
    }

    protected static int recordOpenPVR(int i) {
        if (is_emulator()) {
            Log.d(TAG, "recordStartPVR called...\n");
            return 0;
        }
        return TVNative.recordOpenPVR(i);
    }

    protected static int recordStartPVR(int i) {
        if (is_emulator()) {
            Log.d(TAG, "recordStartPVR called...\n");
            return 0;
        }
        return TVNative.recordStartPVR(i);
    }

    protected static int recordStartPVRByHandle(int i) {
        if (is_emulator()) {
            Log.d(TAG, "recordStartPVR called...\n");
            return 0;
        }
        return TVNative.recordStartPVRByHandle(i);
    }

    protected static int recordStopPVR() {
        if (is_emulator()) {
            Log.d(TAG, "recordStopPVR called...\n");
            return 0;
        }
        return TVNative.recordStopPVR();
    }

    protected static int recordGetPVRStatus() {
        if (is_emulator()) {
            Log.d(TAG, "recordGetPVRStatus called...\n");
            return 0;
        }
        return TVNative.recordGetPVRStatus();
    }

    protected static int recordGetPVRStatusByHandle(int i) {
        if (is_emulator()) {
            Log.d(TAG, "recordGetPVRStatus called...\n");
            return 0;
        }
        return TVNative.recordGetPVRStatusByHandle(i);
    }

    protected static int recordGetPVRSrcType() {
        if (is_emulator()) {
            Log.d(TAG, "recordGetPVRSrcType called...\n");
            return 0;
        }
        return TVNative.recordGetPVRSrcType();
    }

    protected static int recordGetPVRSrcTypeByHandle(int i) {
        if (is_emulator()) {
            Log.d(TAG, "recordGetPVRSrcType called...\n");
            return 0;
        }
        return TVNative.recordGetPVRSrcTypeByHandle(i);
    }

    protected static int recordClosePVR(int i) {
        if (is_emulator()) {
            Log.d(TAG, "recordClosePVR called...\n");
            return 0;
        }
        return TVNative.recordClosePVR(i);
    }

    protected static int recordStopPVRByHandle(int i) {
        if (is_emulator()) {
            Log.d(TAG, "recordStopPVR called...\n");
            return 0;
        }
        return TVNative.recordStopPVRByHandle(i);
    }

    protected static int recordgetErrorID() {
        if (is_emulator()) {
            Log.d(TAG, "recordgetErrorID called...\n");
            return 0;
        }
        return TVNative.recordgetErrorID();
    }

    protected static long recordGetRecordingFilesize() {
        if (is_emulator()) {
            Log.d(TAG, "recordGetRecordingFilesize called...\n");
            return 0L;
        }
        return TVNative.recordGetRecordingFilesize();
    }

    protected static int recordOpenPVREx(int i, boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "recordStartPVREx called...\n");
            return 0;
        }
        return TVNative.recordOpenPVREx(i, z);
    }

    protected static int recordPVRSelectSvc(int i, int i2, int i3) {
        if (is_emulator()) {
            Log.d(TAG, "recordPVRSelectSvc called...\n");
            return 0;
        }
        return TVNative.recordPVRSelectSvc(i, i2, i3);
    }

    protected static int setFileLockChannel(String str, int i) {
        if (is_emulator()) {
            Log.d(TAG, "setFileLockChannel called...\n");
            return 0;
        }
        return TVNative.setFileLockChannel(str, i);
    }

    protected static int setFileLockInput(String str, int i) {
        if (is_emulator()) {
            Log.d(TAG, "setFileLockInput called...\n");
            return 0;
        }
        return TVNative.setFileLockInput(str, i);
    }

    protected static MtkTvTimeshiftBase.TimeshiftRecordStatus getRecordStatus_native() {
        if (is_emulator()) {
            Log.d(TAG, "getRecordStatus_native called...\n");
            return MtkTvTimeshiftBase.TimeshiftRecordStatus.TIMESHIFT_RECORD_UNKNOWN;
        }
        return TVNative.getRecordStatus_native();
    }

    protected static int stopTimeshift_native(MtkTvTimeshiftBase.TimeshiftStopFlag timeshiftStopFlag) {
        if (is_emulator()) {
            Log.d(TAG, "getRecordStatus_native called...\n");
            return 0;
        }
        return TVNative.stopTimeshift_native(timeshiftStopFlag);
    }

    protected static int setAutoRecord_native(boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "setAutoRecord_native called...\n");
            return 0;
        }
        return TVNative.setAutoRecord_native(z);
    }

    protected static int setPlaybackSpeed_native(MtkTvTimeshiftBase.TimeshiftPlaybackSpeed timeshiftPlaybackSpeed) {
        if (is_emulator()) {
            Log.d(TAG, "setPlaybackSpeed_native called...\n");
            return 0;
        }
        return TVNative.setPlaybackSpeed_native(timeshiftPlaybackSpeed);
    }

    protected static int setPlaybackPause_native() {
        if (is_emulator()) {
            Log.d(TAG, "setPlaybackPause_native called...\n");
            return 0;
        }
        return TVNative.setPlaybackPause_native();
    }

    protected static int setPlaybackResume_native() {
        if (is_emulator()) {
            Log.d(TAG, "setPlaybackResume_native called...\n");
            return 0;
        }
        return TVNative.setPlaybackResume_native();
    }

    protected static int seekTo_native(long j) {
        if (is_emulator()) {
            Log.d(TAG, "seekTo_native called...\n");
            return 0;
        }
        return TVNative.seekTo_native(j);
    }

    protected static long getStartPosition_native() {
        if (is_emulator()) {
            Log.d(TAG, "getStartPosition_native called...\n");
            return 0L;
        }
        return TVNative.getStartPosition_native();
    }

    protected static long getCurrentPosition_native() {
        if (is_emulator()) {
            Log.d(TAG, "getCurrentPosition_native called...\n");
            return 0L;
        }
        return TVNative.getCurrentPosition_native();
    }

    protected static int getTimeshiftRegisterDeviceSchedule_native() {
        if (is_emulator()) {
            Log.d(TAG, "getRegisterDeviceSchedule_native called...\n");
            return 0;
        }
        return TVNative.getTimeshiftRegisterDeviceSchedule_native();
    }

    protected static long getTimeshiftDuration_native() {
        if (is_emulator()) {
            Log.d(TAG, "getTimeshiftDuration_native called...\n");
            return 0L;
        }
        return TVNative.getTimeshiftDuration_native();
    }

    protected static long getTimeshiftPosition_native() {
        if (is_emulator()) {
            Log.d(TAG, "getTimeshiftPosition_native called...\n");
            return 0L;
        }
        return TVNative.getTimeshiftPosition_native();
    }

    protected static int registerTimeshiftDevice_native(String str, long j) {
        if (is_emulator()) {
            Log.d(TAG, "registerTimeshiftDevice called...\n");
            return 0;
        }
        return TVNative.registerTimeshiftDevice_native(str, j);
    }

    protected static int startTimeshift_native() {
        if (is_emulator()) {
            Log.d(TAG, "startTimeshift_native called...\n");
            return 0;
        }
        return TVNative.startTimeshift_native();
    }

    protected static int stopTimeshift_native() {
        if (is_emulator()) {
            Log.d(TAG, "stopTimeshift_native called...\n");
            return 0;
        }
        return TVNative.stopTimeshift_native();
    }

    protected static int getTimeshiftDeviceStatus_native() {
        if (is_emulator()) {
            Log.d(TAG, "getTimeshiftDeviceStatus_native called...\n");
            return 0;
        }
        return TVNative.getTimeshiftDeviceStatus_native();
    }

    protected static int getTimeshiftErrorID_native() {
        if (is_emulator()) {
            Log.d(TAG, "getTimeshiftErrorID_native called...\n");
            return 0;
        }
        return TVNative.getTimeshiftErrorID_native();
    }

    protected static boolean playTimeshift_native() {
        if (is_emulator()) {
            Log.d(TAG, "playTimeshift_native called...\n");
            return true;
        }
        return TVNative.playTimeshift_native();
    }

    protected static boolean pauseTimeshift_native() {
        if (is_emulator()) {
            Log.d(TAG, "pauseTimeshift_native called...\n");
            return true;
        }
        return TVNative.pauseTimeshift_native();
    }

    protected static int seekTimeshift_native(long j) {
        if (is_emulator()) {
            Log.d(TAG, "seekTimeshift_native called...\n");
            return 0;
        }
        return TVNative.seekTimeshift_native(j);
    }

    protected static int trickPlayTimeshift_native(double d) {
        if (is_emulator()) {
            Log.d(TAG, "trickPlayTimeshift_native called...\n");
            return 0;
        }
        return TVNative.trickPlayTimeshift_native(d);
    }

    protected static long getPvrBrowserItemChannelId_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getPvrBrowserItemChannelId_native called...\n");
            return 0L;
        }
        return TVNative.getPvrBrowserItemChannelId_native(i);
    }

    protected static long getPvrBrowserItemChannelId_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "getPvrBrowserItemChannelId_native called...\n");
            return 0L;
        }
        return TVNative.getPvrBrowserItemChannelId_native(str);
    }

    protected static String getPvrBrowserItemChannelName_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "getPvrBrowserItemChannelName_native called...\n");
            return "";
        }
        return TVNative.getPvrBrowserItemChannelName_native(str);
    }

    protected static long getPvrBrowserItemDuration_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "getPvrBrowserItemDuration_native called...\n");
            return 0L;
        }
        return TVNative.getPvrBrowserItemDuration_native(str);
    }

    protected static long getPvrBrowserItemStopReason_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "getPvrBrowserItemStopReason_native called...\n");
            return 0L;
        }
        return TVNative.getPvrBrowserItemStopReason_native(str);
    }

    protected static String getPvrBrowserItemProgramName_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "getPvrBrowserItemProgramName_native called...\n");
            return "";
        }
        return TVNative.getPvrBrowserItemProgramName_native(str);
    }

    protected static long getPvrBrowserItemStartTime_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "getPvrBrowserItemStartTime_native called...\n");
            return 0L;
        }
        return TVNative.getPvrBrowserItemStartTime_native(str);
    }

    protected static long getPvrBrowserItemStartTime_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getPvrBrowserItemStartTime_native called...\n");
            return 0L;
        }
        return TVNative.getPvrBrowserItemStartTime_native(i);
    }

    protected static int getPvrBrowserItemCount_native() {
        if (is_emulator()) {
            Log.d(TAG, "getPvrBrowserCount_native called...\n");
            return 0;
        }
        return TVNative.getPvrBrowserItemCount_native();
    }

    protected static String getPvrBrowserItemPath_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getPvrBrowserItemPath_native called...\n");
            return "";
        }
        return TVNative.getPvrBrowserItemPath_native(i);
    }

    protected static int getPvrBrowserItemMajorChannelNum_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getPvrBrowserItemMajorChannelNum_native called...\n");
            return 0;
        }
        return TVNative.getPvrBrowserItemMajorChannelNum_native(i);
    }

    protected static int getPvrBrowserItemMinorChannelNum_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getPvrBrowserItemMinorChannelNum_native called...\n");
            return 0;
        }
        return TVNative.getPvrBrowserItemMinorChannelNum_native(i);
    }

    protected static String getPvrBrowserItemChannelName_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getPvrBrowserItemChannelName_native called...\n");
            return "";
        }
        return TVNative.getPvrBrowserItemChannelName_native(i);
    }

    protected static long getPvrBrowserItemDuration_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getPvrBrowserItemDuration_native called...\n");
            return 0L;
        }
        return TVNative.getPvrBrowserItemDuration_native(i);
    }

    protected static long getPvrBrowserItemStopReason_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getPvrBrowserItemStopReason_native called...\n");
            return 0L;
        }
        return TVNative.getPvrBrowserItemStopReason_native(i);
    }

    protected static String getPvrBrowserItemProgramName_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getPvrBrowserItemProgramName_native called...\n");
            return "";
        }
        return TVNative.getPvrBrowserItemProgramName_native(i);
    }

    protected static String getPvrBrowserItemDate_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getPvrBrowserItemDate_native called...\n");
            return "";
        }
        return TVNative.getPvrBrowserItemDate_native(i);
    }

    protected static String getPvrBrowserItemWeek_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getPvrBrowserItemWeek_native called...\n");
            return "";
        }
        return TVNative.getPvrBrowserItemWeek_native(i);
    }

    protected static String getPvrBrowserItemTime_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getPvrBrowserItemTime_native called...\n");
            return "";
        }
        return TVNative.getPvrBrowserItemTime_native(i);
    }

    protected static int getPvrBrowserRecordingFileCount_native() {
        if (is_emulator()) {
            Log.d(TAG, "getPvrBrowserRecordingFileCount_native called...\n");
            return 0;
        }
        return TVNative.getPvrBrowserRecordingFileCount_native();
    }

    protected static String getPvrBrowserRecordingFileName_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getPvrBrowserItemTime_native called...\n");
            return "";
        }
        return TVNative.getPvrBrowserRecordingFileName_native(i);
    }

    protected static int deletePvrBrowserFileByIndex_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "deletePvrBrowserFileByIndex_native called...\n");
            return 0;
        }
        return TVNative.deletePvrBrowserFileByIndex_native(i);
    }

    protected static int getPvrBrowserItemMajorChannelNum_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "getPvrBrowserItemMajorChannelNum_native called...\n");
            return 0;
        }
        return TVNative.getPvrBrowserItemMajorChannelNum_native(str);
    }

    protected static int getPvrBrowserItemMinorChannelNum_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "getPvrBrowserItemMinorChannelNum_native called...\n");
            return 0;
        }
        return TVNative.getPvrBrowserItemMinorChannelNum_native(str);
    }

    protected static long getPvrBrowserItemFirstRatingRange_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getPvrBrowserItemFirstRatingRange_native called...\n");
            return 0L;
        }
        return TVNative.getPvrBrowserItemFirstRatingRange_native(i);
    }

    protected static long getPvrBrowserItemFirstRatingRange_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "getPvrBrowserItemFirstRatingRange_native called...\n");
            return 0L;
        }
        return TVNative.getPvrBrowserItemFirstRatingRange_native(str);
    }

    protected static long getPvrBrowserItemRentention_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getPvrBrowserItemRentention_native called...\n");
            return 0L;
        }
        return TVNative.getPvrBrowserItemRentention_native(i);
    }

    protected static long getPvrBrowserItemRentention_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "getPvrBrowserItemRentention_native called...\n");
            return 0L;
        }
        return TVNative.getPvrBrowserItemRentention_native(str);
    }

    protected static String getPvrBrowserItemDate_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "getPvrBrowserItemDate_native called...\n");
            return "";
        }
        return TVNative.getPvrBrowserItemDate_native(str);
    }

    protected static String getPvrBrowserItemWeek_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "getPvrBrowserItemWeek_native called...\n");
            return "";
        }
        return TVNative.getPvrBrowserItemWeek_native(str);
    }

    protected static String getPvrBrowserItemTime_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "getPvrBrowserItemTime_native called...\n");
            return "";
        }
        return TVNative.getPvrBrowserItemTime_native(str);
    }

    protected static int getPvrBrowserItemChannelLock_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getPvrBrowserItemChannelLock called...\n");
            return 0;
        }
        return TVNative.getPvrBrowserItemChannelLock_native(i);
    }

    protected static int getPvrBrowserItemChannelLock_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, " getPvrBrowserItemChannelLock called...\n");
            return 0;
        }
        return TVNative.getPvrBrowserItemChannelLock_native(str);
    }

    protected static int getPvrBrowserItemInputLock_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getPvrBrowserItemInputLock called...\n");
            return 0;
        }
        return TVNative.getPvrBrowserItemInputLock_native(i);
    }

    protected static int getPvrBrowserItemInputLock_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, " getPvrBrowserItemInputLock called...\n");
            return 0;
        }
        return TVNative.getPvrBrowserItemInputLock_native(str);
    }

    protected static long getUtcTime_native() {
        if (is_emulator()) {
            Log.d(TAG, "getUtcTime_native called...\n");
            return 0L;
        }
        return TVNative.getUtcTime_native();
    }

    protected static long getBroadcastTime_native(MtkTvTimeFormatBase mtkTvTimeFormatBase) {
        if (is_emulator()) {
            Log.d(TAG, "getBroadcastTime_native called...\n");
            return 0L;
        }
        return TVNative.getBroadcastTime_native(mtkTvTimeFormatBase);
    }

    protected static boolean isReceivedTOT_native() {
        if (is_emulator()) {
            Log.d(TAG, "isReceivedTOT_native called...\n");
            return false;
        }
        return TVNative.isReceivedTOT_native();
    }

    protected static int convertUTCSecToDTG_native(long j, MtkTvTimeFormatBase mtkTvTimeFormatBase) {
        if (is_emulator()) {
            Log.d(TAG, "convertUTCSecToDTG_native called...\n");
            return 0;
        }
        return TVNative.convertUTCSecToDTG_native(j, mtkTvTimeFormatBase);
    }

    protected static void setUtcTime_native(long j) {
        if (is_emulator()) {
            Log.d(TAG, "setUtcTime_native called...\n");
        } else {
            TVNative.setUtcTime_native(j);
        }
    }

    protected static boolean isTimeEditable_native() {
        if (is_emulator()) {
            Log.d(TAG, "isTimeEditable_native called...\n");
            return true;
        }
        return TVNative.isTimeEditable_native();
    }

    protected static int convertTime_native(int i, MtkTvTimeFormatBase mtkTvTimeFormatBase, MtkTvTimeFormatBase mtkTvTimeFormatBase2) {
        if (is_emulator()) {
            Log.d(TAG, "convertTime_native called...\n");
            return 0;
        }
        return TVNative.convertTime_native(i, mtkTvTimeFormatBase, mtkTvTimeFormatBase2);
    }

    protected static MtkTvTimeRawDataBase getBrdcstRawData_native() {
        if (is_emulator()) {
            Log.d(TAG, "getBrdcstRawData_native called...\n");
            return null;
        }
        return TVNative.getBrdcstRawData_native();
    }

    protected static long getTimeZone_native() {
        if (is_emulator()) {
            Log.d(TAG, "getTimeZone_native called...\n");
            return 0L;
        }
        return TVNative.getTimeZone_native();
    }

    protected static void setTimeZone_native(long j) {
        if (is_emulator()) {
            Log.d(TAG, "setTimeZone_native called...\n");
        } else {
            TVNative.setTimeZone_native(j);
        }
    }

    protected static boolean isTimeZoneEditable_native() {
        if (is_emulator()) {
            Log.d(TAG, "isTimeZoneEditable_native called...\n");
            return true;
        }
        return TVNative.isTimeZoneEditable_native();
    }

    protected static long getTimeOffset_native() {
        if (is_emulator()) {
            Log.d(TAG, "getTimeOffset_native called...\n");
            return 0L;
        }
        return TVNative.getTimeOffset_native();
    }

    protected static int slideForOnePartChannelId_native(int i, int i2, int i3) {
        if (is_emulator()) {
            Log.d(TAG, "getChannelListByMask_native called...\n");
            return 0;
        }
        return TVNative.slideForOnePartChannelId_native(i, i2, i3);
    }

    protected static void setTimeOffset_native(long j) {
        if (is_emulator()) {
            Log.d(TAG, "setTimeOffset_native called...\n");
        } else {
            TVNative.setTimeOffset_native(j);
        }
    }

    protected static boolean isTimeOffsetEditable_native() {
        if (is_emulator()) {
            Log.d(TAG, "isTimeOffsetEditable_native called...\n");
            return true;
        }
        return TVNative.isTimeOffsetEditable_native();
    }

    protected static int getSleepTimer_native(boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "getSleepTimer_native called...\n");
            return 0;
        }
        return TVNative.getSleepTimer_native(z);
    }

    protected static void setSleepTimer_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "setSleepTimer_native called...\n");
        } else {
            TVNative.setSleepTimer_native(i);
        }
    }

    protected static void convertMillisToLocalTime_native(long j, MtkTvTimeFormatBase mtkTvTimeFormatBase) {
        if (is_emulator()) {
            Log.d(TAG, "convertMillisToLocalTime_native called...\n");
        } else {
            TVNative.convertMillisToLocalTime_native(j, mtkTvTimeFormatBase);
        }
    }

    protected static long convertLocalTimeToMillis_native(MtkTvTimeFormatBase mtkTvTimeFormatBase) {
        if (is_emulator()) {
            Log.d(TAG, "convertLocalTimeToMillis_native called...\n");
            return 0L;
        }
        return TVNative.convertLocalTimeToMillis_native(mtkTvTimeFormatBase);
    }

    protected static long convertDTGToSeconds_native(MtkTvTimeFormatBase mtkTvTimeFormatBase) {
        if (is_emulator()) {
            Log.d(TAG, "convertDTGToSeconds_native called...\n");
            return 0L;
        }
        return TVNative.convertDTGToSeconds_native(mtkTvTimeFormatBase);
    }

    protected static int getSleepTimerRemainingTime_native() {
        if (is_emulator()) {
            Log.d(TAG, "getSleepTimerRemainingTime_native called...\n");
            return 0;
        }
        return TVNative.getSleepTimerRemainingTime_native();
    }

    protected static int getTimeSyncSource_native() {
        if (is_emulator()) {
            Log.d(TAG, "getTimeSyncSource_native called...\n");
            return 0;
        }
        return TVNative.getTimeSyncSource_native();
    }

    protected static void setTimeSyncSource_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "setTimeSyncSource_native called...\n");
        } else {
            TVNative.setTimeSyncSource_native(i);
        }
    }

    protected static void setLocalTimeZone_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "setLocalTimeZone_native called...\n");
        } else {
            TVNative.setLocalTimeZone_native(str);
        }
    }

    protected static void softKeyboardInit_native() {
        if (is_emulator()) {
            Log.d(TAG, "softKeyboardInit_native called...\n");
        } else {
            TVNative.softKeyboardInit_native();
        }
    }

    protected static void notifySoftKeyboardStatusChange_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "notifySoftKeyboardStatusChange_native called...\n");
        } else {
            TVNative.notifySoftKeyboardStatusChange_native(i);
        }
    }

    protected static void notifyTextChange_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "notifyTextChange_native called...\n");
        } else {
            TVNative.notifyTextChange_native(str);
        }
    }

    protected static boolean isScaning_native() {
        if (is_emulator()) {
            Log.d(TAG, "isScaning_native called...\n");
            return false;
        }
        return TVNative.isScaning_native();
    }

    protected static int startScan_native(int i, int i2, boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "startScan_native called...\n");
            return 0;
        }
        return TVNative.startScan_native(i, i2, z);
    }

    protected static int cancelScan_native() {
        if (is_emulator()) {
            Log.d(TAG, "cancelScan_native called...\n");
            return 0;
        }
        return TVNative.cancelScan_native();
    }

    protected static int setDvbScanParas(MtkTvDvbScanParaBase mtkTvDvbScanParaBase) {
        if (is_emulator()) {
            Log.d(TAG, "setDvbScanParas called...\n");
            return 0;
        }
        return TVNative.setDvbScanParas(mtkTvDvbScanParaBase);
    }

    protected static int setDvbcManualScanParas(MtkTvDvbcManualScanParaBase mtkTvDvbcManualScanParaBase) {
        if (is_emulator()) {
            Log.d(TAG, "setDvbcManualScanParas called...\n");
            return 0;
        }
        return TVNative.setDvbcManualScanParas(mtkTvDvbcManualScanParaBase);
    }

    protected static int setDvbcScanParas(MtkTvDvbcScanParaBase mtkTvDvbcScanParaBase) {
        if (is_emulator()) {
            Log.d(TAG, "setDvbcScanParas called...\n");
            return 0;
        }
        return TVNative.setDvbcScanParas(mtkTvDvbcScanParaBase);
    }

    protected static int setATSCScanParas(MtkTvATSCScanParaBase mtkTvATSCScanParaBase) {
        if (is_emulator()) {
            Log.d(TAG, "setATSCScanParas called...\n");
            return 0;
        }
        return TVNative.setATSCScanParas(mtkTvATSCScanParaBase);
    }

    protected static int setNTSCScanParas(MtkTvNTSCScanParaBase mtkTvNTSCScanParaBase) {
        if (is_emulator()) {
            Log.d(TAG, "setNTSCScanParas called...\n");
            return 0;
        }
        return TVNative.setNTSCScanParas(mtkTvNTSCScanParaBase);
    }

    protected static int setISDBScanParas(MtkTvISDBScanParaBase mtkTvISDBScanParaBase) {
        if (is_emulator()) {
            Log.d(TAG, "setISDBScanParas called...\n");
            return 0;
        }
        return TVNative.setISDBScanParas(mtkTvISDBScanParaBase);
    }

    protected static int getMarketRegion() {
        if (is_emulator()) {
            Log.d(TAG, "getMarketRegion called...\n");
            return 0;
        }
        return TVNative.getMarketRegion();
    }

    protected static int setSatelliteSetting(MtkTvDvbsSatelliteSettingBase mtkTvDvbsSatelliteSettingBase) {
        if (is_emulator()) {
            Log.d(TAG, "setSatelliteSetting called...\n");
            return 0;
        }
        return TVNative.setSatelliteSetting(mtkTvDvbsSatelliteSettingBase);
    }

    protected static int getUIMode() {
        if (is_emulator()) {
            Log.d(TAG, "getUIMode called...\n");
            return 0;
        }
        return TVNative.getUIMode();
    }

    protected static int ScanPalSecamExchangeData_native(int[] iArr) {
        if (is_emulator()) {
            Log.d(TAG, "ScanPalSecamExchangeData called...\n");
            return 0;
        }
        return TVNative.ScanPalSecamExchangeData_native(iArr);
    }

    protected static int ScanDvbcExchangeData_native(int[] iArr) {
        if (is_emulator()) {
            Log.d(TAG, "ScanDvbcExchangeData called...\n");
            return 0;
        }
        return TVNative.ScanDvbcExchangeData_native(iArr);
    }

    protected static String ScanDvbcGetStrData_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "ScanDvbcGetStrData called...\n");
            return null;
        }
        return TVNative.ScanDvbcGetStrData_native(i);
    }

    protected static int ScanATSCExchangeData_native(int[] iArr) {
        if (is_emulator()) {
            Log.d(TAG, "ScanATSCExchangeData called...\n");
            return 0;
        }
        return TVNative.ScanATSCExchangeData_native(iArr);
    }

    protected static int ScanISDBExchangeData_native(int[] iArr) {
        if (is_emulator()) {
            Log.d(TAG, "ScanISDBExchangeData called...\n");
            return 0;
        }
        return TVNative.ScanISDBExchangeData_native(iArr);
    }

    protected static int ScanCeExchangeData_native(int[] iArr) {
        if (is_emulator()) {
            Log.d(TAG, "ScanCeExchangeData called... \n");
            return 0;
        }
        return TVNative.ScanCeExchangeData_native(iArr);
    }

    protected static int ScanDtmbExchangeData_native(int[] iArr) {
        if (is_emulator()) {
            Log.d(TAG, "ScanDtmbExchangeData called... \n");
            return 0;
        }
        return TVNative.ScanDtmbExchangeData_native(iArr);
    }

    protected static int ScanDvbtExchangeData_native(int[] iArr) {
        if (is_emulator()) {
            Log.d(TAG, "ScanDvbtExchangeData called...\n");
            return 0;
        }
        return TVNative.ScanDvbtExchangeData_native(iArr);
    }

    protected static int ScanDvbsExchangeData_native(int[] iArr) {
        if (is_emulator()) {
            Log.d(TAG, "ScanDvbsExchangeData called...\n");
            return 0;
        }
        return TVNative.ScanDvbsExchangeData_native(iArr);
    }

    protected static String ScanDvbsgetUserMessage_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "ScanDvbsgetUserMessage_native called");
            return null;
        }
        return TVNative.ScanDvbsgetUserMessage_native(str);
    }

    protected static int startManualDetect_native() {
        if (is_emulator()) {
            Log.d(TAG, "startManualDetect_native called...");
            return 0;
        }
        return TVNative.startManualDetect_native();
    }

    protected static int getScheduleInfo_native(MtkTvOADBase mtkTvOADBase) {
        if (is_emulator()) {
            Log.d(TAG, "getScheduleInfo_native called...");
            return 0;
        }
        return TVNative.getScheduleInfo_native(mtkTvOADBase);
    }

    protected static int getDataInfo_native(MtkTvOADBase mtkTvOADBase, int i) {
        if (is_emulator()) {
            Log.d(TAG, "getDataInfo_native called...");
            return 0;
        }
        return TVNative.getDataInfo_native(mtkTvOADBase, i);
    }

    protected static int stopManualDetect_native() {
        if (is_emulator()) {
            Log.d(TAG, "stopManualDetect_native called...");
            return 0;
        }
        return TVNative.stopManualDetect_native();
    }

    protected static int startDownload_native() {
        if (is_emulator()) {
            Log.d(TAG, "startDownload_native called...");
            return 0;
        }
        return TVNative.startDownload_native();
    }

    protected static int stopDownload_native() {
        if (is_emulator()) {
            Log.d(TAG, "stopDownload_native called...");
            return 0;
        }
        return TVNative.stopDownload_native();
    }

    protected static int setAutoDownload_native(boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "setAutoDownload_native called...");
            return 0;
        }
        return TVNative.setAutoDownload_native(z);
    }

    protected static int startFlash_native() {
        if (is_emulator()) {
            Log.d(TAG, "startFlash_native called...");
            return 0;
        }
        return TVNative.startFlash_native();
    }

    protected static int acceptRestart_native() {
        if (is_emulator()) {
            Log.d(TAG, "acceptRestart_native called...");
            return 0;
        }
        return TVNative.acceptRestart_native();
    }

    protected static int remindMeLater_native() {
        if (is_emulator()) {
            Log.d(TAG, "remindMeLater_native called...");
            return 0;
        }
        return TVNative.remindMeLater_native();
    }

    protected static int acceptSchedule_native() {
        if (is_emulator()) {
            Log.d(TAG, "acceptSchedule_native called...");
            return 0;
        }
        return TVNative.acceptSchedule_native();
    }

    protected static int startJumpChannel_native() {
        if (is_emulator()) {
            Log.d(TAG, "startJumpChannel_native called...");
            return 0;
        }
        return TVNative.startJumpChannel_native();
    }

    protected static int getPowerOnStatus_native() {
        if (is_emulator()) {
            Log.d(TAG, "getPowerOnStatus_native called...");
            return 0;
        }
        return TVNative.getPowerOnStatus_native();
    }

    protected static int setPkgPathname_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "setPkgPathname_native called...");
            return 0;
        }
        return TVNative.setPkgPathname_native(str);
    }

    protected static int clearOadVersion_native() {
        if (is_emulator()) {
            Log.d(TAG, "clearOadVersion_native called...");
            return 0;
        }
        return TVNative.clearOadVersion_native();
    }

    protected static void getPfgInfo_native(MtkTvMHEG5Base mtkTvMHEG5Base) {
        if (is_emulator()) {
            Log.d(TAG, "getPfgString called...\n");
        } else {
            TVNative.getPfgInfo_native(mtkTvMHEG5Base);
        }
    }

    protected static void setPfgResult_native(boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "setPfgResult called...\n");
        } else {
            TVNative.setPfgResult_native(z);
        }
    }

    protected static void setHbbtvStatus_native(boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "setHbbtvStatus_native called...\n");
        } else {
            TVNative.setHbbtvStatus_native(z);
        }
    }

    protected static void setMheg5Enable_native() {
        if (is_emulator()) {
            Log.d(TAG, "setMheg5Enable_native called...\n");
        } else {
            TVNative.setMheg5Enable_native();
        }
    }

    protected static void setMheg5Disable_native() {
        if (is_emulator()) {
            Log.d(TAG, "setMheg5Disable_native called...\n");
        } else {
            TVNative.setMheg5Disable_native();
        }
    }

    protected static void getInternalScrnMode_native(MtkTvMHEG5Base mtkTvMHEG5Base) {
        if (is_emulator()) {
            Log.d(TAG, "getInternalScrnMode called...\n");
        } else {
            TVNative.getInternalScrnMode_native(mtkTvMHEG5Base);
        }
    }

    protected static void setMheg5Status_native(int i, int i2, int i3, int i4) {
        if (is_emulator()) {
            Log.d(TAG, "setMheg5Status called...\n");
        } else {
            TVNative.setMheg5Status_native(i, i2, i3, i4);
        }
    }

    protected static void createMonitorInst_native(byte b) {
        if (is_emulator()) {
            Log.d(TAG, "createMonitorInst_native called...\n");
        } else {
            TVNative.createMonitorInst_native(b);
        }
    }

    protected static void deleteMonitorInst_native(byte b) {
        if (is_emulator()) {
            Log.d(TAG, "deleteMonitorInst_native called...\n");
        } else {
            TVNative.deleteMonitorInst_native(b);
        }
    }

    protected static void getInfo_native(byte b, int i, int[] iArr) {
        if (is_emulator()) {
            Log.d(TAG, "getInfo_native called...\n");
        } else {
            TVNative.getInfo_native(b, i, iArr);
        }
    }

    protected static void setInfo_native(byte b, int i, int[] iArr) {
        if (is_emulator()) {
            Log.d(TAG, "setInfo_native called...\n");
        } else {
            TVNative.setInfo_native(b, i, iArr);
        }
    }

    protected static int enter_native() {
        if (is_emulator()) {
            Log.d(TAG, "enter called...\n");
            return 0;
        }
        return TVNative.enter_native();
    }

    protected static boolean isDisplayBanner_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "isDisplayBanner called...\n");
            return true;
        }
        return TVNative.isDisplayBanner_native(i, i2);
    }

    protected static boolean isDisplayFrmCH_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "isDisplayFrmCH called...\n");
            return true;
        }
        return TVNative.isDisplayFrmCH_native(i, i2);
    }

    protected static boolean isDisplayFrmInfo_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "isDisplayFrmInfo called...\n");
            return true;
        }
        return TVNative.isDisplayFrmInfo_native(i, i2);
    }

    protected static boolean isDisplayFrmDetail_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "isDisplayFrmDetail called...\n");
            return true;
        }
        return TVNative.isDisplayFrmDetail_native(i, i2);
    }

    protected static boolean isDisplayIptsLockIcon_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "isDisplayIptsLockIcon called...\n");
            return true;
        }
        return TVNative.isDisplayIptsLockIcon_native(i, i2);
    }

    protected static boolean isDisplayTVLockIcon_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "isDisplayTVLockIcon called...\n");
            return true;
        }
        return TVNative.isDisplayTVLockIcon_native(i, i2);
    }

    protected static boolean isDisplayFavIcon_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "isDisplayFavIcon called...\n");
            return true;
        }
        return TVNative.isDisplayFavIcon_native(i, i2);
    }

    protected static boolean isDisplayCaptionIcon_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "isDisplayCaptionIcon called...\n");
            return true;
        }
        return TVNative.isDisplayCaptionIcon_native(i, i2);
    }

    protected static boolean isDisplayTtxIcon_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "isDisplayTtxIcon called...\n");
            return true;
        }
        return TVNative.isDisplayTtxIcon_native(i, i2);
    }

    protected static boolean isDisplayProgDetailUpIcon_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "isDisplayProgDetailUpIcon called...\n");
            return true;
        }
        return TVNative.isDisplayProgDetailUpIcon_native(i, i2);
    }

    protected static boolean isDisplayProgDetailDownIcon_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "isDisplayProgDetailDownIcon called...\n");
            return true;
        }
        return TVNative.isDisplayProgDetailDownIcon_native(i, i2);
    }

    protected static boolean isDisplayLogoIcon_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "isDisplayLogoIcon called...\n");
            return true;
        }
        return TVNative.isDisplayLogoIcon_native(i, i2);
    }

    protected static boolean isDisplayGingaIcon_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "isDisplayGingaIcon called...\n");
            return true;
        }
        return TVNative.isDisplayGingaIcon_native(i, i2);
    }

    protected static boolean isDisplayADEyeIcon_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "isDisplayADEyeIcon called...\n");
            return true;
        }
        return TVNative.isDisplayADEyeIcon_native(i, i2);
    }

    protected static boolean isDisplayADEarIcon_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "isDisplayADEarIcon called...\n");
            return true;
        }
        return TVNative.isDisplayADEarIcon_native(i, i2);
    }

    protected static boolean isDisplayADAtmos_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "isDisplayADAtmos called...\n");
            return true;
        }
        return TVNative.isDisplayADAtmos_native(i, i2);
    }

    protected static String getChannelNumber_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "getChannelNumber called...\n");
            return null;
        }
        return TVNative.getChannelNumber_native(i, i2);
    }

    protected static String getChannelName_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "getChannelName called...\n");
            return null;
        }
        return TVNative.getChannelName_native(i, i2);
    }

    protected static String getTVSrc_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "getTVSrc called...\n");
            return null;
        }
        return TVNative.getTVSrc_native(i, i2);
    }

    protected static String getTimer_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "getTimer called...\n");
            return null;
        }
        return TVNative.getTimer_native(i, i2);
    }

    protected static String getMsg_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "getMsg called...\n");
            return null;
        }
        return TVNative.getMsg_native(i, i2);
    }

    protected static String getProgTitle_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "getProgTitle called...\n");
            return null;
        }
        return TVNative.getProgTitle_native(i, i2);
    }

    protected static String getProgCategory_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "getProgCategory called...\n");
            return null;
        }
        return TVNative.getProgCategory_native(i, i2);
    }

    protected static String getProgCategoryIdx_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "getProgCategoryIdx called...\n");
            return null;
        }
        return TVNative.getProgCategoryIdx_native(i, i2);
    }

    protected static String getProgSecondaryTitle_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "getProgSecondaryTitle called...\n");
            return null;
        }
        return TVNative.getProgSecondaryTitle_native(i, i2);
    }

    protected static String getNextProgSecondaryTitle_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "getNextProgSecondaryTitle called...\n");
            return null;
        }
        return TVNative.getNextProgSecondaryTitle_native(i, i2);
    }

    protected static String getChannelLogo_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "getChannelLogo called...\n");
            return null;
        }
        return TVNative.getChannelLogo_native(i, i2);
    }

    protected static String getNextProgDetail_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "getNextProgDetail called...\n");
            return null;
        }
        return TVNative.getNextProgDetail_native(i, i2);
    }

    protected static String getNextProgCategoryIdx_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "getNextProgCategoryIdx called...\n");
            return null;
        }
        return TVNative.getNextProgCategoryIdx_native(i, i2);
    }

    protected static String getNextRating_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "getNextRating called...\n");
            return null;
        }
        return TVNative.getNextRating_native(i, i2);
    }

    protected static String getProgTime_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "getProgTime called...\n");
            return null;
        }
        return TVNative.getProgTime_native(i, i2);
    }

    protected static String getNextProgTitle_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "getNextProgTitle called...\n");
            return null;
        }
        return TVNative.getNextProgTitle_native(i, i2);
    }

    protected static String getNextProgTime_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "getNextProgTime called...\n");
            return null;
        }
        return TVNative.getNextProgTime_native(i, i2);
    }

    protected static String getProgDetail_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "getProgDetail called...\n");
            return null;
        }
        return TVNative.getProgDetail_native(i, i2);
    }

    protected static String getProgDetailPageIdx_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "getProgDetailPageIdx called...\n");
            return null;
        }
        return TVNative.getProgDetailPageIdx_native(i, i2);
    }

    protected static String getRating_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "getRating called...\n");
            return null;
        }
        return TVNative.getRating_native(i, i2);
    }

    protected static String getCaption_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "getCaption called...\n");
            return null;
        }
        return TVNative.getCaption_native(i, i2);
    }

    protected static String getIptsCC_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "getIptsCC called...\n");
            return null;
        }
        return TVNative.getIptsCC_native(i, i2);
    }

    protected static String getIptsRating_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "getIptsRating called...\n");
            return null;
        }
        return TVNative.getIptsRating_native(i, i2);
    }

    protected static String getAudioInfo_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "getAudioInfo called...\n");
            return null;
        }
        return TVNative.getAudioInfo_native(i, i2);
    }

    protected static String getVideoInfo_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "getVideoInfo called...\n");
            return null;
        }
        return TVNative.getVideoInfo_native(i, i2);
    }

    protected static String getIptsName_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "getIptsName called...\n");
            return null;
        }
        return TVNative.getIptsName_native(i, i2);
    }

    protected static String getIptsRslt_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "getIptsRslt called...\n");
            return null;
        }
        return TVNative.getIptsRslt_native(i, i2);
    }

    protected static String getIptChannelNumber_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "getIptChannelNumber called...\n");
            return null;
        }
        return TVNative.getIptChannelNumber_native(i, i2);
    }

    protected static int setFreeze_native(String str, boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "setFreeze_native called ...\n");
            return 0;
        }
        return TVNative.setFreeze_native(str, z);
    }

    protected static boolean isFreeze_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "isFreeze_native called ...\n");
            return false;
        }
        return TVNative.isFreeze_native(str);
    }

    protected static int setNextAudioLang_native() {
        if (is_emulator()) {
            Log.d(TAG, "setNextAudioLang_native...\n");
            return 0;
        }
        return TVNative.setNextAudioLang_native();
    }

    protected static void getAudioLang_native(MtkTvAVModeBase mtkTvAVModeBase) {
        if (is_emulator()) {
            Log.d(TAG, "getAudioLang_native...\n");
        } else {
            TVNative.getAudioLang_native(mtkTvAVModeBase);
        }
    }

    protected static void getAudioAvailableRecord_native(List<TvProviderAudioTrackBase> list) {
        if (is_emulator()) {
            Log.d(TAG, "getAudioAvailableRecord_native...\n");
        } else {
            TVNative.getAudioAvailableRecord_native(list);
        }
    }

    protected static int setVideoInfoValue_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "setVideoBackLight_native...\n");
            return 0;
        }
        return TVNative.setVideoInfoValue_native(i, i2);
    }

    protected static int getVideoInfoValue_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getVideoBackLight_native...\n");
            return 0;
        }
        return TVNative.getVideoInfoValue_native(i);
    }

    protected static int setVideoInfoData_native(int i, MtkTvVideoInfoBase mtkTvVideoInfoBase) {
        if (is_emulator()) {
            Log.d(TAG, "setVideoBackLight_native...\n");
            return 0;
        }
        return TVNative.setVideoInfoData_native(i, mtkTvVideoInfoBase);
    }

    protected static int getVideoInfoData_native(int i, MtkTvVideoInfoBase mtkTvVideoInfoBase) {
        if (is_emulator()) {
            Log.d(TAG, "getVideoBackLight_native...\n");
            return 0;
        }
        return TVNative.getVideoInfoData_native(i, mtkTvVideoInfoBase);
    }

    protected static int setAudioInfoValue_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "setAudioInfoValue_native...\n");
            return 0;
        }
        return TVNative.setAudioInfoValue_native(i, i2);
    }

    protected static int getAudioInfoValue_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getAudioInfoValue_native...\n");
            return 0;
        }
        return TVNative.getAudioInfoValue_native(i);
    }

    protected static int setAudioInfoData_native(int i, MtkTvAudioInfoBase mtkTvAudioInfoBase) {
        if (is_emulator()) {
            Log.d(TAG, "setAudioInfoData_native...\n");
            return 0;
        }
        return TVNative.setAudioInfoData_native(i, mtkTvAudioInfoBase);
    }

    protected static int getAudioInfoData_native(int i, MtkTvAudioInfoBase mtkTvAudioInfoBase) {
        if (is_emulator()) {
            Log.d(TAG, "getAudioInfoData_native...\n");
            return 0;
        }
        return TVNative.getAudioInfoData_native(i, mtkTvAudioInfoBase);
    }

    protected static void getInputSourceAudioAvailableRecord_native(List<TvProviderAudioTrackBase> list) {
        if (is_emulator()) {
            Log.d(TAG, "getInputSourceAudioAvailableRecord_native...\n");
        } else {
            TVNative.getInputSourceAudioAvailableRecord_native(list);
        }
    }

    protected static int getCurrentAudio_native(TvProviderAudioTrackBase tvProviderAudioTrackBase) {
        if (is_emulator()) {
            Log.d(TAG, "getCurrentAudio_native...\n");
            return 0;
        }
        return TVNative.getCurrentAudio_native(tvProviderAudioTrackBase);
    }

    protected static int getCurrentVideoStreamInfo_native(MtkTvVideoStreamInfoBase mtkTvVideoStreamInfoBase) {
        if (is_emulator()) {
            Log.d(TAG, "getCurrentVideoStreamInfo_native...\n");
            return 0;
        }
        return TVNative.getCurrentVideoStreamInfo_native(mtkTvVideoStreamInfoBase);
    }

    protected static int getApdText_native(int i, MtkTvAudioApdBase mtkTvAudioApdBase) {
        if (is_emulator()) {
            Log.d(TAG, "getCurrentAudio_native...\n");
            return 0;
        }
        return TVNative.getApdText_native(i, mtkTvAudioApdBase);
    }

    protected static int getCurrentAd_native(TvProviderAudioTrackBase tvProviderAudioTrackBase) {
        if (is_emulator()) {
            Log.d(TAG, "getCurrentAudio_native...\n");
            return 0;
        }
        return TVNative.getCurrentAd_native(tvProviderAudioTrackBase);
    }

    protected static int getAudioDecType_native() {
        if (is_emulator()) {
            Log.d(TAG, "getAudioDecType_native...\n");
            return 0;
        }
        return TVNative.getAudioDecType_native();
    }

    protected static int getInputSourceCurrentAudio_native(TvProviderAudioTrackBase tvProviderAudioTrackBase) {
        if (is_emulator()) {
            Log.d(TAG, "getHDMICurrentAudio_native...\n");
            return 0;
        }
        return TVNative.getInputSourceCurrentAudio_native(tvProviderAudioTrackBase);
    }

    protected static int selectAudioById_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "selectAudioById_native...\n");
            return 0;
        }
        return TVNative.selectAudioById_native(i);
    }

    protected static int unselectAudio_native() {
        if (is_emulator()) {
            Log.d(TAG, "unselectAudio_native...\n");
            return 0;
        }
        return TVNative.unselectAudio_native();
    }

    protected static int selectMainSubAudioById_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "selectMainSubAudioById_native...\n");
            return 0;
        }
        return TVNative.selectMainSubAudioById_native(i, i2);
    }

    protected static int selectDefaultAudio_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "selectDefaultAudio_native Emulator called");
            return 0;
        }
        Log.d(TAG, "selectDefaultAudio_native  called");
        return TVNative.selectDefaultAudio_native(str);
    }

    protected static int getSlotNum_navtive() {
        if (is_emulator()) {
            Log.d(TAG, "getSlotNum_navtive called...");
            return 0;
        }
        return TVNative.getSlotNum_navtive();
    }

    protected static int getMenuListID_navtive() {
        if (is_emulator()) {
            Log.d(TAG, "getMenuListID_navtive called...");
            return 0;
        }
        return TVNative.getMenuListID_navtive();
    }

    protected static int getEnqID_navtive() {
        if (is_emulator()) {
            Log.d(TAG, "getEnqID_navtive called...");
            return 0;
        }
        return TVNative.getEnqID_navtive();
    }

    protected static String getCamName_navtive(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getCamName_navtive called...");
            return "";
        }
        return TVNative.getCamName_navtive(i);
    }

    protected static String getCamID_navtive(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getCamID_navtive called...");
            return "";
        }
        return TVNative.getCamID_navtive(i);
    }

    protected static boolean getSlotActive_navtive(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getSlotActive_navtive called...");
            return false;
        }
        return TVNative.getSlotActive_navtive(i);
    }

    protected static int setMenuAnswer_navtive(int i, int i2, int i3) {
        if (is_emulator()) {
            Log.d(TAG, "setMenuAnswer_navtive called...");
            return 0;
        }
        return TVNative.setMenuAnswer_navtive(i, i2, i3);
    }

    protected static int setEnqAnswer_navtive(int i, int i2, int i3, String str) {
        if (is_emulator()) {
            Log.d(TAG, "setEnqAnswer_navtive called...");
            return 0;
        }
        return TVNative.setEnqAnswer_navtive(i, i2, i3, str);
    }

    protected static void setMMIClose_navtive(int i) {
        if (is_emulator()) {
            Log.d(TAG, "setMMIClose_navtive called...");
        } else {
            TVNative.setMMIClose_navtive(i);
        }
    }

    protected static void setMMICloseDone_navtive(int i) {
        if (is_emulator()) {
            Log.d(TAG, "setMMICloseDone_navtive called...");
        } else {
            TVNative.setMMICloseDone_navtive(i);
        }
    }

    protected static void enterMMI_navtive(int i) {
        if (is_emulator()) {
            Log.d(TAG, "enterMMI_navtive called...");
        } else {
            TVNative.enterMMI_navtive(i);
        }
    }

    protected static int startCamScan_navtive(int i, boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "startCamScan_navtive called...");
            return 0;
        }
        return TVNative.startCamScan_navtive(i, z);
    }

    protected static int cancelCamScan_navtive(int i) {
        if (is_emulator()) {
            Log.d(TAG, "cancelCamScan_navtive called...");
            return 0;
        }
        Log.d(TAG, "cancelCamScan_navtive called...");
        return TVNative.cancelCamScan_navtive(i);
    }

    public static int updateCIKey_navtive() {
        if (is_emulator()) {
            Log.d(TAG, "updateCIKey_navtive called ...\n");
            return 0;
        }
        return TVNative.updateCIKey_navtive();
    }

    public static int updateCIKeyEx_navtive(int i) {
        if (is_emulator()) {
            Log.d(TAG, "updateCIKeyEx_navtive called ...\n");
            return 0;
        }
        Log.d(TAG, "updateCIKeyEx_navtive ...\n");
        return TVNative.updateCIKeyEx_navtive(i);
    }

    public static int eraseCIKey_navtive() {
        if (is_emulator()) {
            Log.d(TAG, "eraseCIKey_navtive called ...\n");
            return 0;
        }
        return TVNative.eraseCIKey_navtive();
    }

    public static String getCIKeyinfo_navtive() {
        if (is_emulator()) {
            Log.d(TAG, "getCIKeyinfo_navtive called ...\n");
            return null;
        }
        return TVNative.getCIKeyinfo_navtive();
    }

    public static boolean getCIKeyStatus_navtive() {
        if (is_emulator()) {
            Log.d(TAG, "getCIKeyStatus_navtive called ...\n");
            return false;
        }
        Log.d(TAG, "getCIKeyStatus_navtive called ...\n");
        return TVNative.getCIKeyStatus_navtive();
    }

    public static int getCIHostID_navtive(int[] iArr) {
        if (is_emulator()) {
            Log.d(TAG, "getCIHostID_navtive called ...\n");
            return 0;
        }
        Log.d(TAG, "getCIHostID_navtive called ...\n");
        return TVNative.getCIHostID_navtive(iArr);
    }

    protected static int setCamPinCode_navtive(int i, String str) {
        if (is_emulator()) {
            Log.d(TAG, "setCamPinCode_navtive called...");
            return 0;
        }
        return TVNative.setCamPinCode_navtive(i, str);
    }

    protected static String getCamPinCode_navtive(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getCamPinCode_navtive called...");
            return null;
        }
        Log.d(TAG, "getCamPinCode_navtive called...");
        return TVNative.getCamPinCode_navtive(i);
    }

    protected static int recordgetErrorIDByHandle(int i) {
        if (is_emulator()) {
            Log.d(TAG, "recordgetErrorID called...\n");
            return 0;
        }
        return TVNative.recordgetErrorIDByhandle(i);
    }

    protected static int getCamPinCaps_navtive() {
        if (is_emulator()) {
            Log.d(TAG, "getEnqID_navtive called...");
            return 0;
        }
        return TVNative.getCamPinCaps_navtive();
    }

    protected static int getCamRatingValue_navtive(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getCamRatingValue_navtive called...");
            return 0;
        }
        return TVNative.getCamRatingValue_navtive(i);
    }

    protected static int getCaSystemIDMatch_navtive(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getCaSystemIDMatch_navtive called...");
            return 0;
        }
        return TVNative.getCaSystemIDMatch_navtive(i);
    }

    protected static int getProfileValid_navtive(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getProfileValid_navtive called...");
            return 0;
        }
        Log.d(TAG, "getProfileValid_navtive called...");
        return TVNative.getProfileValid_navtive(i);
    }

    protected static int getProfileSupport_navtive(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "getProfileSupport_navtive called...");
            return 0;
        }
        Log.d(TAG, "getProfileSupport_navtive called...");
        return TVNative.getProfileSupport_navtive(i, i2);
    }

    protected static int getProfileResourceIsOpen_navtive(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getProfileResourceIsOpen_navtive called...");
            return 0;
        }
        Log.d(TAG, "getProfileResourceIsOpen_navtive called...");
        return TVNative.getProfileResourceIsOpen_navtive(i);
    }

    protected static String getProfileName_navtive(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getProfileName_navtive called...");
            return null;
        }
        Log.d(TAG, "getProfileName_navtive called...");
        return TVNative.getProfileName_navtive(i);
    }

    protected static String getProfileISO639LangCode_navtive(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getProfileISO639LangCode_navtive called...");
            return "";
        }
        Log.d(TAG, "getProfileISO639LangCode_navtive called...");
        return TVNative.getProfileISO639LangCode_navtive(i);
    }

    protected static int getSasItvState_navtive(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getSasItvState_navtive called...");
            return 0;
        }
        Log.d(TAG, "getSasItvState_navtive called...");
        return TVNative.getSasItvState_navtive(i);
    }

    protected static int setSasForceItvExit_navtive(int i) {
        if (is_emulator()) {
            Log.d(TAG, "setSasForceItvExit_navtive called...");
            return 0;
        }
        Log.d(TAG, "setSasForceItvExit_navtive called...");
        return TVNative.setSasForceItvExit_navtive(i);
    }

    protected static int getSasCbctState_navtive(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getSasCbctState_navtive called...");
            return 0;
        }
        Log.d(TAG, "getSasCbctState_navtive called...");
        return TVNative.getSasCbctState_navtive(i);
    }

    protected static int getHostTuneStatus_navtive(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getHostTuneStatus_navtive called...");
            return 0;
        }
        Log.d(TAG, "getHostTuneStatus_navtive called...");
        return TVNative.getHostTuneStatus_navtive(i);
    }

    protected static int getHostTuneBrdcstStatus_navtive(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getHostTuneBrdcstStatus_navtive called...");
            return 0;
        }
        Log.d(TAG, "getHostTuneBrdcstStatus_navtive called...");
        return TVNative.getHostTuneBrdcstStatus_navtive(i);
    }

    protected static int setScanComplete_navtive(int i) {
        if (is_emulator()) {
            Log.d(TAG, "setScanComplete_navtive called...");
            return 0;
        }
        Log.d(TAG, "setScanComplete_navtive called...");
        return TVNative.setScanComplete_navtive(i);
    }

    protected static int getHostQuietTuneStatus_navtive(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getHostQuietTuneStatus_navtive called...");
            return 0;
        }
        Log.d(TAG, "getHostQuietTuneStatus_navtive called...");
        return TVNative.getHostQuietTuneStatus_navtive(i);
    }

    protected static int setHDSConfirm_navtive(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "setHDSConfirm_navtive called...");
            return 0;
        }
        Log.d(TAG, "setHDSConfirm_navtive called...");
        return TVNative.setHDSConfirm_navtive(i, i2);
    }

    protected static int getCamScanReqTypeACON_navtive(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getCamScanReqTypeACON_navtive called...");
            return 0;
        }
        Log.d(TAG, "getCamScanReqTypeACON_navtive called...");
        return TVNative.getCamScanReqTypeACON_navtive(i);
    }

    protected static String getVirtualChannelInfo_navtive(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getVirtualChannelInfo_navtive called...");
            return null;
        }
        return TVNative.getVirtualChannelInfo_navtive(i);
    }

    protected static int ISDBCCEnable_native(boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "ISDBCCEnable_native called ...\n");
            return 0;
        }
        return TVNative.ISDBCCEnable_native(z);
    }

    protected static int ISDBCCNextStream_native() {
        if (is_emulator()) {
            Log.d(TAG, "ISDBCCNextStream_native called ...\n");
            return 0;
        }
        return TVNative.ISDBCCNextStream_native();
    }

    protected static int ISDBCCGetCCString_native() {
        if (is_emulator()) {
            Log.d(TAG, "ISDBCCGetCCString_native called ...\n");
            return 0;
        }
        return TVNative.ISDBCCGetCCString_native();
    }

    protected static boolean checkPWD_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "checkPWD_native called ...\n");
            return false;
        }
        return TVNative.checkPWD_native(str);
    }

    protected static int PWDShow_native() {
        if (is_emulator()) {
            Log.d(TAG, "PWDShow_native is called ... \n");
            return 0;
        }
        return TVNative.PWDShow_native();
    }

    protected static int getScrnSvrMsgID_native() {
        if (is_emulator()) {
            Log.d(TAG, "getScrnSvrMsgID_native is called ... \n");
            return 0;
        }
        return TVNative.getScrnSvrMsgID_native();
    }

    protected static int getUSTvRatingSettingInfo_native(MtkTvUSTvRatingSettingInfoBase mtkTvUSTvRatingSettingInfoBase) {
        if (is_emulator()) {
            Log.d(TAG, "getUSTvRatingSettingInfo_native called...\n");
            return 0;
        }
        return TVNative.getUSTvRatingSettingInfo_native(mtkTvUSTvRatingSettingInfoBase);
    }

    protected static int setUSTvRatingSettingInfo_native(MtkTvUSTvRatingSettingInfoBase mtkTvUSTvRatingSettingInfoBase) {
        if (is_emulator()) {
            Log.d(TAG, "setUSTvRatingSettingInfo_native called...\n");
            return 0;
        }
        return TVNative.setUSTvRatingSettingInfo_native(mtkTvUSTvRatingSettingInfoBase);
    }

    protected static int getUSMovieRatingSettingInfo_native() {
        if (is_emulator()) {
            Log.d(TAG, "getUSMovieRatingSettingInfo_native called...\n");
            return 0;
        }
        return TVNative.getUSMovieRatingSettingInfo_native();
    }

    protected static int setUSMovieRatingSettingInfo_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "setUSMovieRatingSettingInfo_native called...\n");
            return 0;
        }
        return TVNative.setUSMovieRatingSettingInfo_native(i);
    }

    protected static int getCANEngRatingSettingInfo_native() {
        if (is_emulator()) {
            Log.d(TAG, "getCANEngRatingSettingInfo_native called...\n");
            return 0;
        }
        return TVNative.getCANEngRatingSettingInfo_native();
    }

    protected static int setCANEngRatingSettingInfo_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "setCANEngRatingSettingInfo_native called...\n");
            return 0;
        }
        return TVNative.setCANEngRatingSettingInfo_native(i);
    }

    protected static int getCANFreRatingSettingInfo_native() {
        if (is_emulator()) {
            Log.d(TAG, "getCANFreRatingSettingInfo_native called...\n");
            return 0;
        }
        return TVNative.getCANFreRatingSettingInfo_native();
    }

    protected static int setCANFreRatingSettingInfo_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "setCANFreRatingSettingInfo_native called...\n");
            return 0;
        }
        return TVNative.setCANFreRatingSettingInfo_native(i);
    }

    protected static boolean getBlockUnrated_native() {
        if (is_emulator()) {
            Log.d(TAG, "getBlockUnrated_native called...\n");
            return false;
        }
        return TVNative.getBlockUnrated_native();
    }

    protected static int setBlockUnrated_native(boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "setBlockUnrated_native called...\n");
            return 0;
        }
        return TVNative.setBlockUnrated_native(z);
    }

    protected static boolean getRatingEnable_native() {
        if (is_emulator()) {
            Log.d(TAG, "getRatingEnable_native called...\n");
            return false;
        }
        return TVNative.getRatingEnable_native();
    }

    protected static int setRatingEnable_native(boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "setRatingEnable_native called...\n");
            return 0;
        }
        return TVNative.setRatingEnable_native(z);
    }

    protected static boolean isOpenVCHIPInfoAvailable_native() {
        if (is_emulator()) {
            Log.d(TAG, "isOpenVCHIPInfoAvailable_native called...\n");
            return false;
        }
        return TVNative.isOpenVCHIPInfoAvailable_native();
    }

    protected static int getOpenVCHIPInfo_native(MtkTvOpenVCHIPParaBase mtkTvOpenVCHIPParaBase, MtkTvOpenVCHIPInfoBase mtkTvOpenVCHIPInfoBase) {
        if (is_emulator()) {
            Log.d(TAG, "getOpenVCHIPInfo_native called...\n");
            return 0;
        }
        return TVNative.getOpenVCHIPInfo_native(mtkTvOpenVCHIPParaBase, mtkTvOpenVCHIPInfoBase);
    }

    protected static int getOpenVCHIPSettingInfo_native(MtkTvOpenVCHIPSettingInfoBase mtkTvOpenVCHIPSettingInfoBase) {
        if (is_emulator()) {
            Log.d(TAG, "getOpenVCHIPSettingInfo_native called...\n");
            return 0;
        }
        return TVNative.getOpenVCHIPSettingInfo_native(mtkTvOpenVCHIPSettingInfoBase);
    }

    protected static int setOpenVCHIPSettingInfo_native(MtkTvOpenVCHIPSettingInfoBase mtkTvOpenVCHIPSettingInfoBase) {
        if (is_emulator()) {
            Log.d(TAG, "setOpenVCHIPSettingInfo_native called...\n");
            return 0;
        }
        return TVNative.setOpenVCHIPSettingInfo_native(mtkTvOpenVCHIPSettingInfoBase);
    }

    protected static int setAtscStorage_native(boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "setAtscStorage_native called...\n");
            return 0;
        }
        return TVNative.setAtscStorage_native(z);
    }

    protected static boolean getUSTVGrougByChild_native() {
        if (is_emulator()) {
            Log.d(TAG, "isCamInstalled_native called...\n");
            return false;
        }
        return TVNative.getUSTVGrougByChild_native();
    }

    protected static int getISDBContentRatingSetting_native() {
        if (is_emulator()) {
            Log.d(TAG, "getISDBContentRatingSetting_native called...\n");
            return 0;
        }
        return TVNative.getISDBContentRatingSetting_native();
    }

    protected static int setISDBContentRatingSetting_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "setISDBContentRatingSetting_native called...\n");
            return 0;
        }
        return TVNative.setISDBContentRatingSetting_native(i);
    }

    protected static int getISDBAgeRatingSetting_native() {
        if (is_emulator()) {
            Log.d(TAG, "getISDBAgeRatingSetting_native called...\n");
            return 0;
        }
        return TVNative.getISDBAgeRatingSetting_native();
    }

    protected static int setISDBAgeRatingSetting_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "setISDBAgeRatingSetting_native called...\n");
            return 0;
        }
        return TVNative.setISDBAgeRatingSetting_native(i);
    }

    protected static int getDVBAgeRatingSetting_native() {
        if (is_emulator()) {
            Log.d(TAG, "getDVBAgeRatingSetting_native called...\n");
            return 0;
        }
        return TVNative.getDVBAgeRatingSetting_native();
    }

    protected static int setDVBAgeRatingSetting_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "setDVBAgeRatingSetting_native called...\n");
            return 0;
        }
        return TVNative.setDVBAgeRatingSetting_native(i);
    }

    public static int getCrntRatingInfo_native(MtkTvRatingConvert2Goo mtkTvRatingConvert2Goo) {
        if (is_emulator()) {
            Log.d(TAG, "getCrntRatingInfo_native called...\n");
            return 0;
        }
        return TVNative.getCrntRatingInfo_native(mtkTvRatingConvert2Goo);
    }

    protected static int[] loadEvents_native(int i, long j, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "loadEvents_native called ...\n");
            return null;
        }
        return TVNative.loadEvents_native(i, j, i2);
    }

    protected static void getEvent_native(int i, MtkTvEventInfoBase mtkTvEventInfoBase) {
        if (is_emulator()) {
            Log.d(TAG, "getEvent_native called ...\n");
        } else {
            TVNative.getEvent_native(i, mtkTvEventInfoBase);
        }
    }

    protected static int freeEvent_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "freeEvent_native called ...\n");
            return 0;
        }
        return TVNative.freeEvent_native(i);
    }

    protected static boolean checkAtscEventBlock_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "checkAtscEventBlock_native called ...\n");
            return false;
        }
        return TVNative.checkAtscEventBlock_native(i, i2);
    }

    protected static int loadEventByEIT_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "loadEventByEIT_native called ...\n");
            return 0;
        }
        return TVNative.loadEventByEIT_native(i, i2);
    }

    protected static int getEventNumberInEIT_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getEventNumberInEIT_native called ...\n");
            return 0;
        }
        return TVNative.getEventNumberInEIT_native(i);
    }

    protected static void getEventByIndex_native(int i, int i2, MtkTvEventInfoBase mtkTvEventInfoBase) {
        if (is_emulator()) {
            Log.d(TAG, "getEventByIndex_native called ...\n");
        } else {
            TVNative.getEventByIndex_native(i, i2, mtkTvEventInfoBase);
        }
    }

    protected static String getEventDetailByIndex_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "getEventDetailByIndex_native called ...\n");
            return null;
        }
        return TVNative.getEventDetailByIndex_native(i, i2);
    }

    protected static int getEventRatingMapByIndex_native(int i, int i2, MtkTvRatingConvert2Goo mtkTvRatingConvert2Goo) {
        if (is_emulator()) {
            Log.d(TAG, "getEventRatingMapByIndex_native called ...\n");
            return 0;
        }
        return TVNative.getEventRatingMapByIndex_native(i, i2, mtkTvRatingConvert2Goo);
    }

    protected static int[] checkEITStauts_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "checkEITStauts_native called ...\n");
            return null;
        }
        return TVNative.checkEITStauts_native(i, i2);
    }

    protected static int getCurrentTeletextPage_native(MtkTvTeletextPageBase mtkTvTeletextPageBase) {
        if (is_emulator()) {
            Log.d(TAG, "getCurrentTeletextPage_native called ...\n");
            return 0;
        }
        return TVNative.getCurrentTeletextPage_native(mtkTvTeletextPageBase);
    }

    protected static int setTeletextPage_native(MtkTvTeletextPageBase mtkTvTeletextPageBase) {
        if (is_emulator()) {
            Log.d(TAG, "setTeletextPage_native called ...\n");
            return 0;
        }
        return TVNative.setTeletextPage_native(mtkTvTeletextPageBase);
    }

    protected static boolean teletextHasTopInfo_native() {
        if (is_emulator()) {
            Log.d(TAG, "teletextHasTopInfo_native called ...\n");
            return false;
        }
        return TVNative.teletextHasTopInfo_native();
    }

    protected static int getTeletextTopBlockList_native(List<MtkTvTeletextTopBlockBase> list) {
        if (is_emulator()) {
            Log.d(TAG, "getTeletextTopBlockList_native called ...\n");
            return 0;
        }
        return TVNative.getTeletextTopBlockList_native(list);
    }

    protected static int getTeletextTopGroupList_native(MtkTvTeletextTopBlockBase mtkTvTeletextTopBlockBase, List<MtkTvTeletextTopGroupBase> list) {
        if (is_emulator()) {
            Log.d(TAG, "getTeletextTopGroupList_native called ...\n");
            return 0;
        }
        return TVNative.getTeletextTopGroupList_native(mtkTvTeletextTopBlockBase, list);
    }

    protected static int getTeletextTopPageList_native(MtkTvTeletextTopGroupBase mtkTvTeletextTopGroupBase, List<MtkTvTeletextTopPageBase> list) {
        if (is_emulator()) {
            Log.d(TAG, "getTeletextTopPageList_native called ...\n");
            return 0;
        }
        return TVNative.getTeletextTopPageList_native(mtkTvTeletextTopGroupBase, list);
    }

    protected static int[] getAllScreenMode_native() {
        if (is_emulator()) {
            Log.d(TAG, "getAllScreenMode_native called ...\n");
            return null;
        }
        return TVNative.getAllScreenMode_native();
    }

    protected static int setScreenMode_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "setScreenMode_native called ...\n");
            return 0;
        }
        return TVNative.setScreenMode_native(i);
    }

    protected static int getScreenMode_native() {
        if (is_emulator()) {
            Log.d(TAG, "getScreenMode_native called ...\n");
            return 0;
        }
        return TVNative.getScreenMode_native();
    }

    protected static boolean isZoomEnable_native() {
        if (is_emulator()) {
            Log.d(TAG, "isZoomEnable_native called ...\n");
            return false;
        }
        return TVNative.isZoomEnable_native();
    }

    protected static int setCrntOverscan_native(int i, int i2, int i3, int i4) {
        if (is_emulator()) {
            Log.d(TAG, "setCrntOverscan_native called ...\n");
            return 0;
        }
        return TVNative.setCrntOverscan_native(i, i2, i3, i4);
    }

    protected static int getCrntOverscan_native(MtkTvScreenModeOverscan mtkTvScreenModeOverscan) {
        if (is_emulator()) {
            Log.d(TAG, "getCrntOverscan_native called ...\n");
            return 0;
        }
        return TVNative.getCrntOverscan_native(mtkTvScreenModeOverscan);
    }

    protected static int updateOverscanIni_native() {
        if (is_emulator()) {
            Log.d(TAG, "updateOverscanIni_native called ...\n");
            return 0;
        }
        return TVNative.updateOverscanIni_native();
    }

    protected static int nextStream_native() {
        if (is_emulator()) {
            Log.d(TAG, "nextStream_native called ...\n");
            return 0;
        }
        return TVNative.nextStream_native();
    }

    protected static int playStream_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "playStream_native called ...\n");
            return 0;
        }
        return TVNative.playStream_native(i);
    }

    protected static int SubtitleGetTracks_native(int[] iArr) {
        if (is_emulator()) {
            Log.d(TAG, "SubtitleGetTracks_native called ...\n");
            return 0;
        }
        return TVNative.SubtitleGetTracks_native(iArr);
    }

    protected static int[] getAllSoundEffect_native() {
        if (is_emulator()) {
            Log.d(TAG, "getAllSoundEffect_native called ...\n");
            return null;
        }
        return TVNative.getAllSoundEffect_native();
    }

    protected static String isdbCDTGetChannelLogo_native(int i, int i2, int i3) {
        if (is_emulator()) {
            Log.d(TAG, "isdbCDTGetChannelLogo_native called ...\n");
            return null;
        }
        return TVNative.isdbCDTGetChannelLogo_native(i, i2, i3);
    }

    protected static int setSoundEffect_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "setSoundEffect_native called ...\n");
            return 0;
        }
        return TVNative.setSoundEffect_native(i);
    }

    protected static int getSoundEffect_native() {
        if (is_emulator()) {
            Log.d(TAG, "getSoundEffect_native called ...\n");
            return 0;
        }
        return TVNative.getSoundEffect_native();
    }

    protected static int[] getAllPictureMode_native() {
        if (is_emulator()) {
            Log.d(TAG, "getAllPictureMode_native called ...\n");
            return null;
        }
        return TVNative.getAllPictureMode_native();
    }

    protected static int setPictureMode_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "setPictureMode_native called ...\n");
            return 0;
        }
        return TVNative.setPictureMode_native(i);
    }

    protected static int getPictureMode_native() {
        if (is_emulator()) {
            Log.d(TAG, "getPictureMode_native called ...\n");
            return 0;
        }
        return TVNative.getPictureMode_native();
    }

    protected static int setEASAndroidLaunchStatus_native(boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "setEASAndroidLaunchStatus_native called ...\n");
            return 0;
        }
        return TVNative.setEASAndroidLaunchStatus_native(z);
    }

    protected static boolean getEASAndroidLaunchStatus_native() {
        if (is_emulator()) {
            Log.d(TAG, "getEASAndroidLaunchStatus_native called ...\n");
            return false;
        }
        return TVNative.getEASAndroidLaunchStatus_native();
    }

    protected static int analogCCEnable_native(boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "analogCCEnable_native called ...\n");
            return 0;
        }
        return TVNative.analogCCEnable_native(z);
    }

    protected static int analogCCNextStream_native() {
        if (is_emulator()) {
            Log.d(TAG, "analogCCNextStream_native called ...\n");
            return 0;
        }
        return TVNative.analogCCNextStream_native();
    }

    protected static int analogCCGetCcIndex_native() {
        if (is_emulator()) {
            Log.d(TAG, "analogCCGetCcIndex_native called ...\n");
            return 0;
        }
        return TVNative.analogCCGetCcIndex_native();
    }

    protected static int analogCCSetCcVisible_native(boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "analogCCSetCcVisible_native called ...\n");
            return 0;
        }
        return TVNative.analogCCSetCcVisible_native(z);
    }

    protected static int atscCCEnable_native(boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "atscCCEnable_native called ...\n");
            return 0;
        }
        return TVNative.atscCCEnable_native(z);
    }

    protected static int atscCCNextStream_native() {
        if (is_emulator()) {
            Log.d(TAG, "atscCCNextStream_native called ...\n");
            return 0;
        }
        return TVNative.atscCCNextStream_native();
    }

    protected static int atscCCGetCCIndex_native() {
        if (is_emulator()) {
            Log.d(TAG, "atscCCGetCCIndex_native called ...\n");
            return 0;
        }
        return TVNative.atscCCGetCCIndex_native();
    }

    protected static int atscCCSetCcVisible_native(boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "atscCCSetCcVisible_native called ...\n");
            return 0;
        }
        return TVNative.atscCCSetCcVisible_native(z);
    }

    protected static int atscCCDemoSet_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "atscCCDemoSet_native called ...\n");
            return 0;
        }
        return TVNative.atscCCDemoSet_native(i, i2);
    }

    protected static int hbbtvExchangeData_native(int[] iArr) {
        if (is_emulator()) {
            Log.d(TAG, "hbbtvExchangeData_native called...\n");
            return 0;
        }
        return TVNative.hbbtvExchangeData_native(iArr);
    }

    protected static int hbbtvSetAudioDescription_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "hbbtvExchangeData_native called...\n");
            return 0;
        }
        return TVNative.hbbtvSetAudioDescription_native(i);
    }

    protected static int hbbtvSetDefaultAudioLang_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "hbbtvSetDefaultAudioLang_native called...\n");
            return 0;
        }
        return TVNative.hbbtvSetDefaultAudioLang_native(str);
    }

    protected static int hbbtvSetDefaultSubtitleLang_native(String str, int i) {
        if (is_emulator()) {
            Log.d(TAG, "hbbtvSetDefaultSubtitleLang_native called...\n");
            return 0;
        }
        return TVNative.hbbtvSetDefaultSubtitleLang_native(str, i);
    }

    protected static int hbbtvStmGetAudioCount_native() {
        if (is_emulator()) {
            Log.d(TAG, "hbbtvStmGetAudioCount_native called...\n");
            return 0;
        }
        return TVNative.hbbtvStmGetAudioCount_native();
    }

    protected static String hbbtvStmGetAudioLang_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "hbbtvStmGetAudioLang_native called...\n");
            return "";
        }
        return TVNative.hbbtvStmGetAudioLang_native(i);
    }

    protected static int hbbtvStmGetAudio_native() {
        if (is_emulator()) {
            Log.d(TAG, "hbbtvStmGetAudio_native called...\n");
            return 0;
        }
        return TVNative.hbbtvStmGetAudio_native();
    }

    protected static int hbbtvStmSetAudioIndex_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "hbbtvStmSetAudioIndex_native called...\n");
            return 0;
        }
        return TVNative.hbbtvStmSetAudioIndex_native(i);
    }

    protected static int startApplication_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "startApplication_native called ...\n");
            return 0;
        }
        return TVNative.startApplication_native(str);
    }

    protected static int stopApplication_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "stopApplication_native called ...\n");
            return 0;
        }
        return TVNative.stopApplication_native(str);
    }

    protected static int getApplicationInfo_native() {
        if (is_emulator()) {
            Log.d(TAG, "getApplicationInfo_native called ...\n");
            return 0;
        }
        return TVNative.getApplicationInfo_native();
    }

    protected static boolean getGingaScreenModeisEnable_native() {
        if (is_emulator()) {
            Log.d(TAG, "getGingaScreenModeisEnable_native called ...\n");
            return false;
        }
        return TVNative.getGingaScreenModeisEnable_native();
    }

    protected static int startGinga_native() {
        if (is_emulator()) {
            Log.d(TAG, "startGinga_native called ...\n");
            return 0;
        }
        return TVNative.startGinga_native();
    }

    protected static int stopGinga_native() {
        if (is_emulator()) {
            Log.d(TAG, "stopGinga_native called ...\n");
            return 0;
        }
        return TVNative.stopGinga_native();
    }

    protected static int warningStartCC_native(boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "warningStartCC_native called ...\n");
            return 0;
        }
        return TVNative.warningStartCC_native(z);
    }

    protected static int warningStartGingaApp_native(boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "warningStartGingaApp_native called ...\n");
            return 0;
        }
        return TVNative.warningStartGingaApp_native(z);
    }

    protected static int getApplicationInfoList_native(List<MtkTvGingaAppInfoBase> list) {
        if (is_emulator()) {
            Log.d(TAG, "getApplicationInfoList_native called ...\n");
            return 0;
        }
        return TVNative.getApplicationInfoList_native(list);
    }

    protected static int getWebServiceClient_native(List<MtkTvGingaWebServiceClientInfoBase> list) {
        if (is_emulator()) {
            Log.d(TAG, "getWebServiceClient_native called ...\n");
            return 0;
        }
        return TVNative.getWebServiceClient_native(list);
    }

    protected static int markWebServiceTrustClient_native(String str, boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "markWebServiceTrustClient_native called ...\n");
            return 0;
        }
        return TVNative.markWebServiceTrustClient_native(str, z);
    }

    protected static int removeWebServiceClient_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "removeWebServiceClient_native called ...\n");
            return 0;
        }
        return TVNative.removeWebServiceClient_native(str);
    }

    protected static int ackDialog_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "ackDialog_native called ...\n");
            return 0;
        }
        return TVNative.ackDialog_native(i);
    }

    protected static int ackTrustDialog_native(boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "ackTrustDialog_native called ...\n");
            return 0;
        }
        return TVNative.ackTrustDialog_native(z);
    }

    protected static int ackChooseAppDialog_native(String str, String str2, boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "ackChooseAppDialog_native called ...\n");
            return 0;
        }
        return TVNative.ackChooseAppDialog_native(str, str2, z);
    }

    protected static int getAppExtendedData_native(String str, MtkTvGingaAppExtendedDataBase mtkTvGingaAppExtendedDataBase) {
        if (is_emulator()) {
            Log.d(TAG, "getAppExtendedData_native called ...\n");
            return 0;
        }
        return TVNative.getAppExtendedData_native(str, mtkTvGingaAppExtendedDataBase);
    }

    protected static int startUpgrade_native(MtkTvUpgradeDeliveryTypeBase mtkTvUpgradeDeliveryTypeBase, boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "startUpgrade_native called ...");
            return 0;
        }
        return TVNative.startUpgrade_native(mtkTvUpgradeDeliveryTypeBase, z);
    }

    protected static int triggerUpgrade_native(MtkTvUpgradeDeliveryTypeBase mtkTvUpgradeDeliveryTypeBase) {
        if (is_emulator()) {
            Log.d(TAG, "triggerUpgrade_native called ...");
            return 0;
        }
        return TVNative.triggerUpgrade_native(mtkTvUpgradeDeliveryTypeBase);
    }

    protected static int getFirmwareInfo_native(MtkTvUpgradeFirmwareInfoBase mtkTvUpgradeFirmwareInfoBase) {
        if (is_emulator()) {
            Log.d(TAG, "triggerUpgrade_native called ...");
            return 0;
        }
        return TVNative.getFirmwareInfo_native(mtkTvUpgradeFirmwareInfoBase);
    }

    protected static int startDownloadFirmware_native(MtkTvUpgradeDeliveryTypeBase mtkTvUpgradeDeliveryTypeBase, String str, String str2) {
        if (is_emulator()) {
            Log.d(TAG, "startDownloadFirmware_native called ...");
            return 0;
        }
        return TVNative.startDownloadFirmware_native(mtkTvUpgradeDeliveryTypeBase, str, str2);
    }

    protected static int cancelDownloadFirmware_native(MtkTvUpgradeDeliveryTypeBase mtkTvUpgradeDeliveryTypeBase) {
        if (is_emulator()) {
            Log.d(TAG, "cancelDownloadFirmware_native called ...");
            return 0;
        }
        return TVNative.cancelDownloadFirmware_native(mtkTvUpgradeDeliveryTypeBase);
    }

    protected static int startRebootUpgrade_native(MtkTvUpgradeDeliveryTypeBase mtkTvUpgradeDeliveryTypeBase) {
        if (is_emulator()) {
            Log.d(TAG, "startRebootUpgrade_native called ...");
            return 0;
        }
        return TVNative.startRebootUpgrade_native(mtkTvUpgradeDeliveryTypeBase);
    }

    protected static int queryUpgradeResult_native(MtkTvUpgradeDeliveryTypeBase mtkTvUpgradeDeliveryTypeBase) {
        if (is_emulator()) {
            Log.d(TAG, "queryUpgradeResult_native called ...");
            return 0;
        }
        return TVNative.queryUpgradeResult_native(mtkTvUpgradeDeliveryTypeBase);
    }

    protected static int popEnterNextTvMode_native() {
        if (is_emulator()) {
            Log.d(TAG, "popEnterNextTvMode_native Emulator called");
            return 0;
        }
        return TVNative.popEnterNextTvMode_native();
    }

    protected static int popSwapWindow_native() {
        if (is_emulator()) {
            Log.d(TAG, "popSwapWindow_native Emulator called");
            return 0;
        }
        return TVNative.popSwapWindow_native();
    }

    protected static int popSwitchAudioFocus_native() {
        if (is_emulator()) {
            Log.d(TAG, "popSwitchAudioFocus_native Emulator called");
            return 0;
        }
        return TVNative.popSwitchAudioFocus_native();
    }

    protected static int popNextPipWindowSize_native() {
        if (is_emulator()) {
            Log.d(TAG, "popNextPipWindowSize_native Emulator called");
            return 0;
        }
        return TVNative.popNextPipWindowSize_native();
    }

    protected static int popNextPipWindowPosition_native() {
        if (is_emulator()) {
            Log.d(TAG, "popNextPipWindowPosition_native Emulator called");
            return 0;
        }
        return TVNative.popNextPipWindowPosition_native();
    }

    protected static boolean popGetPipFocusId_native() {
        if (is_emulator()) {
            Log.d(TAG, "popGetPipFocusId_native Emulator called");
            return true;
        }
        Log.d(TAG, "popGetPipFocusId_native  called");
        return TVNative.popGetPipFocusId_native();
    }

    protected static int popGetFocusInfo_native(MtkTvPipPopFucusInfoBase mtkTvPipPopFucusInfoBase) {
        if (is_emulator()) {
            Log.d(TAG, "popGetFocusInfo_native Emulator called");
            return 0;
        }
        Log.d(TAG, "popGetFocusInfo_native  called");
        return TVNative.popGetFocusInfo_native(mtkTvPipPopFucusInfoBase);
    }

    protected static int AppTVsaveTimestamp_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "AppTVsaveTimestamp_native Emulator called");
            return 0;
        }
        Log.d(TAG, "AppTVsaveTimestamp_native  called");
        return TVNative.AppTVsaveTimestamp_native(str);
    }

    protected static int AppTVunlockService_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "AppTVunlockService_native Emulator called");
            return 0;
        }
        Log.d(TAG, "AppTVunlockService_native  called");
        return TVNative.AppTVunlockService_native(str);
    }

    protected static int AppTVunblockSvc_native(String str, boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "AppTVunblockSvc_native Emulator called");
            return 0;
        }
        Log.d(TAG, "AppTVunblockSvc_native  called");
        return TVNative.AppTVunblockSvc_native(str, z);
    }

    protected static int AppTVsetVideoMute_native(String str, boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "AppTVsetVideoMute_native Emulator called");
            return 0;
        }
        Log.d(TAG, "AppTVsetVideoMute_native  called");
        return TVNative.AppTVsetVideoMute_native(str, z);
    }

    protected static int AppTVGetCapabilityEx_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "AppTVGetCapabilityEx_native Emulator called");
            return 0;
        }
        Log.d(TAG, "AppTVGetCapabilityEx_native  called");
        return TVNative.AppTVGetCapabilityEx_native(i);
    }

    protected static boolean isCaptureLogo_native() {
        if (is_emulator()) {
            Log.d(TAG, "isCaptureLogo Emulator called");
            return false;
        }
        Log.d(TAG, "isCaptureLogo  called");
        return TVNative.isCaptureLogo_native();
    }

    protected static int tunerFacQuery_navtive(boolean z, List<String> list, List<String> list2) {
        if (is_emulator()) {
            Log.d(TAG, "tunerFacQuery Emulator called");
            return 0;
        }
        Log.d(TAG, "tunerFacQuery  called");
        return TVNative.tunerFacQuery_navtive(z, list, list2);
    }

    protected static boolean getAdbStatus_native() {
        if (is_emulator()) {
            Log.d(TAG, "getAdbStatus Emulator called");
            return false;
        }
        Log.d(TAG, "getAdbStatus  called");
        return TVNative.getAdbStatus_native();
    }

    protected static int setAdbStatus_native(boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "setAdbStatus Emulator called");
            return 0;
        }
        Log.d(TAG, "setAdbStatus  called");
        return TVNative.setAdbStatus_native(z);
    }

    protected static int AppTVgetMatchedChannel_native(int i, boolean z, int i2, int i3) {
        if (is_emulator()) {
            Log.d(TAG, "AppTVgetMatchedChannel_native Emulator called");
            return 0;
        }
        Log.d(TAG, "AppTVgetMatchedChannel_native  called");
        return TVNative.AppTVgetMatchedChannel_native(i, z, i2, i3);
    }

    protected static int AppTVsetFinetuneFreq_native(String str, int i, boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "AppTVsetFinetuneFreq_native Emulator called");
            return 0;
        }
        Log.d(TAG, "AppTVsetFinetuneFreq_native  called");
        return TVNative.AppTVsetFinetuneFreq_native(str, i, z);
    }

    protected static int AppTVchangeFreq_native(String str, MtkTvFreqChgParamBase mtkTvFreqChgParamBase) {
        if (is_emulator()) {
            Log.d(TAG, "AppTVchangeFreq_native Emulator called");
            return 0;
        }
        Log.d(TAG, "AppTVchangeFreq_native  called");
        return TVNative.AppTVchangeFreq_native(str, mtkTvFreqChgParamBase);
    }

    protected static int AppTVsetAutoClockPhasePostion_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "AppTVsetAutoClockPhasePostion_native Emulator called");
            return 0;
        }
        Log.d(TAG, "AppTVsetAutoClockPhasePostion_native  called");
        return TVNative.AppTVsetAutoClockPhasePostion_native(str);
    }

    protected static boolean AppTVAutoClockPhasePostionCondSuccess_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "AppTVAutoClockPhasePostionCondSuccess_native Emulator called");
            return true;
        }
        Log.d(TAG, "AutoClockPhasePostionCondSuccess_native  called");
        return TVNative.AppTVAutoClockPhasePostionCondSuccess_native(str);
    }

    protected static int AppTVsetAutoColor_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "AppTVsetAutoColor_native Emulator called");
            return 0;
        }
        Log.d(TAG, "AppTVsetAutoColor_native  called");
        return TVNative.AppTVsetAutoColor_native(str);
    }

    protected static boolean AppTVAutoColorCondSuccess_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "AppTVAutoColorCondSuccess_native Emulator called");
            return true;
        }
        Log.d(TAG, "AppTVAutoColorCondSuccess_native  called");
        return TVNative.AppTVAutoColorCondSuccess_native(str);
    }

    protected static int AppTVupdatedSysStatus_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "AppTVupdatedSysStatus_native Emulator called");
            return 0;
        }
        Log.d(TAG, "AppTVupdatedSysStatus_native  called");
        return TVNative.AppTVupdatedSysStatus_native(i);
    }

    protected static int AppTVupdatedSysStatus_native(String str, int i) {
        if (is_emulator()) {
            Log.d(TAG, "AppTVupdatedSysStatus_native Emulator called");
            return 0;
        }
        Log.d(TAG, "AppTVupdatedSysStatus_native  called");
        return TVNative.AppTVupdatedSysStatus_native(str, i);
    }

    protected static int AppTV_swapTmpUnlockInfo_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "AppTV_swapTmpUnlockInfo_native Emulator called");
            return 0;
        }
        Log.d(TAG, "AppTV_swapTmpUnlockInfo_native\tcalled");
        return TVNative.AppTV_swapTmpUnlockInfo_native(i);
    }

    protected static int getEASCurrentStatus_native(MtkTvEASParaBase mtkTvEASParaBase) {
        if (is_emulator()) {
            Log.d(TAG, "getEASCurrentStatus called...\n");
            return 0;
        }
        return TVNative.getEASCurrentStatus_native(mtkTvEASParaBase);
    }

    protected static int GetConnAttrBER_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "GetConnAttrBER_native called...\n");
            return 0;
        }
        return TVNative.GetConnAttrBER_native(str);
    }

    protected static int GetSignalBER_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "GetSignalBER_native called...\n");
            return 0;
        }
        return TVNative.GetSignalBER_native(str);
    }

    protected static long GetConnAttrDBMSNR_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "GetConnAttrDBMSNR_native called...\n");
            return 0L;
        }
        return TVNative.GetConnAttrDBMSNR_native(str);
    }

    protected static long GetConnAttrUEC_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "GetConnAttrUEC_native called...\n");
            return 0L;
        }
        return TVNative.GetConnAttrUEC_native(str);
    }

    protected static long GetSymRate_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "GetSymRate_native called...\n");
            return 0L;
        }
        return TVNative.GetSymRate_native(str);
    }

    protected static int GetModulation_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "GetModulation_native called...\n");
            return 0;
        }
        return TVNative.GetModulation_native(str);
    }

    protected static long GetConnAtrrAGC_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "GetConnAtrrAGC_native called...\n");
            return 0L;
        }
        return TVNative.GetConnAtrrAGC_native(str);
    }

    protected static int GetVideoSrcTag3DType_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "GetVideoSrcTag3DType_native called...\n");
            return 0;
        }
        return TVNative.GetVideoSrcTag3DType_native(str);
    }

    protected static boolean VideoSrcIsProgressive_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "VideoSrcIsProgressive_native called...\n");
            return true;
        }
        return TVNative.VideoSrcIsProgressive_native(str);
    }

    protected static boolean setWifiWolCtl_native(boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "setWifiWolCtl_native called...\n");
            return true;
        }
        return TVNative.setWifiWolCtl_native(z);
    }

    protected static boolean getWifiWolCtl_native() {
        if (is_emulator()) {
            Log.d(TAG, "getWifiWolCtl_native called...\n");
            return true;
        }
        return TVNative.getWifiWolCtl_native();
    }

    protected static boolean setEthernetWolCtl_native(boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "setEthernetWolCtl called...\n");
            return true;
        }
        return TVNative.setEthernetWolCtl_native(z);
    }

    protected static boolean getEthernetWolCtl_native() {
        if (is_emulator()) {
            Log.d(TAG, "getWifiWolCtl_native called...\n");
            return true;
        }
        return TVNative.getEthernetWolCtl_native();
    }

    public static int sbAtscGetMajorChannelNum_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "sbAtscGetMajorChannelNum_native called...\n");
            return 0;
        }
        return TVNative.sbAtscGetMajorChannelNum_native(i);
    }

    public static int sbAtscGetMinorChannelNum_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "sbAtscGetMinorChannelNum_native called...\n");
            return 0;
        }
        return TVNative.sbAtscGetMinorChannelNum_native(i);
    }

    public static int sbIsdbGetMajorNumber_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "sbIsdbGetMajorNumber_native called...\n");
            return 0;
        }
        return TVNative.sbIsdbGetMajorNumber_native(i);
    }

    public static int sbIsdbGetMinorNumber_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "sbIsdbGetMinorNumber_native called...\n");
            return 0;
        }
        return TVNative.sbIsdbGetMinorNumber_native(i);
    }

    public static int sbIsdbGetChannelIndex_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "sbIsdbGetChannelIndex_native called...\n");
            return 0;
        }
        return TVNative.sbIsdbGetChannelIndex_native(i);
    }

    public static String GetCurrentChannelServiceText_native() {
        if (is_emulator()) {
            Log.d(TAG, "GetCurrentChannelServiceText_native called...\n");
            return new String();
        }
        return TVNative.GetCurrentChannelServiceText_native();
    }

    public static int queryGpioStatus_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "queryGpioStatus_native called...\n");
            return 0;
        }
        return TVNative.queryGpioStatus_native(i, i2);
    }

    public static int applyGpioStatus_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "applyGpioStatus_native called...\n");
            return 0;
        }
        return TVNative.applyGpioStatus_native(i, i2);
    }

    public static int openUARTSerial_native(int i, int[] iArr, int[] iArr2) {
        if (is_emulator()) {
            Log.d(TAG, "openUARTSerial_native called...\n");
            return 0;
        }
        return TVNative.openUARTSerial_native(i, iArr, iArr2);
    }

    public static int closeUARTSerial_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "closeUARTSerial_native called...\n");
            return 0;
        }
        return TVNative.closeUARTSerial_native(i);
    }

    public static int getUARTSerialSetting_native(int i, int[] iArr) {
        if (is_emulator()) {
            Log.d(TAG, "getUARTSerialSetting_native called...\n");
            return 0;
        }
        return TVNative.getUARTSerialSetting_native(i, iArr);
    }

    public static int setUARTSerialSetting_native(int i, int[] iArr) {
        if (is_emulator()) {
            Log.d(TAG, "setUARTSerialSetting_native called...\n");
            return 0;
        }
        return TVNative.setUARTSerialSetting_native(i, iArr);
    }

    public static int getUARTSerialOperationMode_native(int i, int[] iArr) {
        if (is_emulator()) {
            Log.d(TAG, "getUARTSerialOperationMode_native called...\n");
            return 0;
        }
        return TVNative.getUARTSerialOperationMode_native(i, iArr);
    }

    public static int setUARTSerialOperationMode_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "setUARTSerialOperationMode_native called...\n");
            return 0;
        }
        return TVNative.setUARTSerialOperationMode_native(i, i2);
    }

    public static int outputUARTSerial_native(int i, byte[] bArr) {
        if (is_emulator()) {
            Log.d(TAG, "outputUARTSerial_native called...\n");
            return 0;
        }
        return TVNative.outputUARTSerial_native(i, bArr);
    }

    public static int factoryWriteKey_native(String str, String str2) {
        if (is_emulator()) {
            Log.d(TAG, "factoryWriteKey_native called...\n");
            return 0;
        }
        return TVNative.factoryWriteKey_native(str, str2);
    }

    public static int factoryWriteKeyFinish_native() {
        if (is_emulator()) {
            Log.d(TAG, "factoryWriteKeyFinish_native called...\n");
            return 0;
        }
        return TVNative.factoryWriteKeyFinish_native();
    }

    public static int factoryCheckKey_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "factoryCheckKey_native called...\n");
            return 0;
        }
        return TVNative.factoryCheckKey_native(str);
    }

    public static boolean menuIsTvBlock_native() {
        if (is_emulator()) {
            Log.d(TAG, "menuIsTvBlock_native called...\n");
            return true;
        }
        return TVNative.menuIsTvBlock_native();
    }

    protected static int createSatlSnapshot_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "createSatlSnapshot_native called...\n");
            return 0;
        }
        return TVNative.createSatlSnapshot_native(i);
    }

    protected static int restoreSatlSnapshot_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "restoreSatlSnapshot_native called...\n");
            return 0;
        }
        return TVNative.restoreSatlSnapshot_native(i);
    }

    protected static int freeSatlSnapshot_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "freeSatlSnapshot_native called...\n");
            return 0;
        }
        return TVNative.freeSatlSnapshot_native(i);
    }

    public static String GetDrmRegistrationCode_native() {
        if (is_emulator()) {
            Log.d(TAG, "GetDrmRegistrationCode_native called...\n");
            return new String();
        }
        return TVNative.GetDrmRegistrationCode_native();
    }

    public static String SetDrmDeactivation_native() {
        if (is_emulator()) {
            Log.d(TAG, "SetDrmDeactivation_native called...\n");
            return new String();
        }
        return TVNative.SetDrmDeactivation_native();
    }

    public static long GetDrmUiHelpInfo_native() {
        if (is_emulator()) {
            Log.d(TAG, "GetDrmUiHelpInfo_native called...\n");
            return 0L;
        }
        return TVNative.GetDrmUiHelpInfo_native();
    }

    public static boolean GetDivXPlusSupport_native() {
        if (is_emulator()) {
            Log.d(TAG, "GetDivXPlusSupport_native called...\n");
            return false;
        }
        return TVNative.GetDivXPlusSupport_native();
    }

    public static boolean GetDivXHDSupport_native() {
        if (is_emulator()) {
            Log.d(TAG, "GetDivXHDSupport_native called...\n");
            return false;
        }
        return TVNative.GetDivXHDSupport_native();
    }

    public static void rpcCloseClient_native() {
        if (is_emulator()) {
            Log.d(TAG, "rpcCloseClient_native called...\n");
        } else {
            Log.d(TAG, "TVNative.rpcCloseClient_nativeinvoked");
            TVNative.rpcCloseClient_native();
        }
    }

    public static int sifWriteMultipleSubAddr(int i, int i2, byte b, byte[] bArr, byte[] bArr2) {
        if (is_emulator()) {
            Log.d(TAG, "sifWriteMultipleSubAddr called...\n");
            return 0;
        }
        return TVNative.sifWriteMultipleSubAddr_native(i, i2, b, bArr, bArr2);
    }

    public static int sifReadMultipleSubAddr(int i, int i2, byte b, byte[] bArr, byte[] bArr2) {
        if (is_emulator()) {
            Log.d(TAG, "sifReadMultipleSubAddr called...\n");
            return 0;
        }
        return TVNative.sifReadMultipleSubAddr_native(i, i2, b, bArr, bArr2);
    }

    public static int setSystemProperties_native(String str, String str2) {
        if (is_emulator()) {
            Log.d(TAG, "setSystemProperties_native called...\n");
            return 0;
        }
        return TVNative.setSystemProperties_native(str, str2);
    }

    public static String getFileBasePath_native() {
        if (is_emulator()) {
            Log.d(TAG, "getFileBasePath_native called...\n");
            return new String();
        }
        return TVNative.getFileBasePath_native();
    }

    protected static MtkTvParserIniInfoBase getIntConfigData_native(String str, String str2) {
        if (is_emulator()) {
            Log.d(TAG, "getIntConfigData_native called...\n");
            return null;
        }
        return TVNative.getIntConfigData_native(str, str2);
    }

    protected static MtkTvParserIniInfoBase getStringConfigData_native(String str, String str2) {
        if (is_emulator()) {
            Log.d(TAG, "getStringConfigData_native called...\n");
            return null;
        }
        return TVNative.getStringConfigData_native(str, str2);
    }

    protected static int[] getConfigIntArrayValue_native(int i, int i2, String str) {
        if (is_emulator()) {
            Log.d(TAG, "getConfigIntArrayValue_native called");
            return null;
        }
        return TVNative.getConfigIntArrayValue_native(i, i2, str);
    }

    protected static int setConfigIntArrayValue_native(String str, int[] iArr, int i) {
        if (is_emulator()) {
            Log.d(TAG, "setConfigIntArrayValue_native called");
            return -1;
        }
        return TVNative.setConfigIntArrayValue_native(str, iArr, i);
    }

    public static int factoryCleanKey_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "factoryCleanKey_native called...\n");
            return 0;
        }
        return TVNative.factoryCleanKey_native(str);
    }

    public static int factoryRemoveFile_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "factoryRemoveFile_native called...\n");
            return 0;
        }
        return TVNative.factoryRemoveFile_native(str);
    }

    public static int factoryWriteFile_native(String str, String str2) {
        if (is_emulator()) {
            Log.d(TAG, "factoryWriteFile_native called...\n");
            return 0;
        }
        return TVNative.factoryWriteFile_native(str, str2);
    }

    public static int factoryWriteFileAppend_native(String str, String str2, int i) {
        if (is_emulator()) {
            Log.d(TAG, "factoryWriteFileAppend_native called...\n");
            return 0;
        }
        return TVNative.factoryWriteFileAppend_native(str, str2, i);
    }

    public static String factoryReadFile_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "factoryReadFile_native called...\n");
            return new String();
        }
        return TVNative.factoryReadFile_native(str);
    }

    public static int factoryRemovePlayReady25Key_native() {
        if (is_emulator()) {
            Log.d(TAG, "factoryRemovePlayReady25Key_native called...\n");
            return 0;
        }
        return TVNative.factoryRemovePlayReady25Key_native();
    }

    public static int factoryWriteLog_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "factoryWriteLog_native called...\n");
            return 0;
        }
        return TVNative.factoryWriteLog_native(str);
    }

    public static String factoryReadLog_native() {
        if (is_emulator()) {
            Log.d(TAG, "factoryReadLog_native called...\n");
            return new String();
        }
        return TVNative.factoryReadLog_native();
    }

    protected static void startLanB2BWorkThread_native() {
        if (is_emulator()) {
            Log.d(TAG, "[B2B]startLanB2BWorkThread_native called");
        } else {
            TVNative.startLanB2BWorkThread_native();
            Log.v(TAG, "[B2B] TVNative.startLanB2BWorkThread_native called");
        }
    }

    protected static void startTestB2BHWPortWorkThread_native() {
        if (is_emulator()) {
            Log.d(TAG, "[B2B]startTestB2BHWPortWorkThread_native called");
        } else {
            TVNative.startTestB2BHWPortWorkThread_native();
            Log.v(TAG, "[B2B] TVNative.startTestB2BHWPortWorkThread called");
        }
    }

    protected static int ChangeModeforBuiltInVideoUpgrade_native() {
        if (is_emulator()) {
            Log.d(TAG, "[Storemode]ChangeModeforBuiltInVedioUpgrade_native called");
            return -1;
        }
        Log.v(TAG, "[Storemode] TVNative.startLanB2BWorkThread_native called");
        return TVNative.ChangeModeforBuiltInVideoUpgrade_native();
    }

    protected static void endLanB2BWorkThread_native() {
        if (is_emulator()) {
            Log.d(TAG, "[B2B]endLanB2BWorkThread_native called");
        } else {
            TVNative.endLanB2BWorkThread_native();
            Log.v(TAG, "[B2B] TVNative.endLanB2BWorkThread_native called");
        }
    }

    protected static void startSerialWorkThread_native() {
        if (is_emulator()) {
            Log.d(TAG, "startSerialWorkThread_native called");
        } else {
            TVNative.startSerialWorkThread_native();
            Log.v(TAG, " TVNative.startSerialWorkThread_native called");
        }
    }

    protected static int writeToExternalControl_native(int i, byte[] bArr, int i2, int i3) {
        if (is_emulator()) {
            Log.d(TAG, "getTvKeyName_native called");
            return -1;
        }
        return TVNative.writeToExternalControl_native(i, bArr, i2, i3);
    }

    protected static int getSelinuxEnvironment_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "getSelinuxEnvironment_native called");
            return 0;
        }
        return TVNative.getSelinuxEnvironment_native(str);
    }

    public static int isfDataExport_native(String str, String str2) {
        if (is_emulator()) {
            Log.d(TAG, "isfDataOperation_native called...\n");
            return -1;
        }
        return TVNative.isfDataExport_native(str, str2);
    }

    protected static String isfDataSearch_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "isfDataOperation_native called...\n");
            return "";
        }
        return TVNative.isfDataSearch_native(str);
    }

    protected static int isfDataImport_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "isfDataOperation_native called...\n");
            return -1;
        }
        return TVNative.isfDataImport_native(str);
    }

    public static boolean doUnprotectedClear() {
        MtkTvUtil.getInstance().resetPri();
        return true;
    }

    public static void doresetPub() {
        MtkTvUtil.getInstance().resetPub();
    }

    public static int setAndroidWorldInfoToLinux_native(int i, int i2) {
        return TVNativeWrapperCustom.setAndroidWorldInfoToLinux_native(i, i2);
    }

    public static boolean getEarcCaps_native() {
        if (is_emulator()) {
            Log.d(TAG, "getEarcCaps_native called...\n");
            return false;
        }
        return TVNative.getEarcCaps_native();
    }

    public static int setEarcEnable_native(boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "setEarcEnable_native called...\n");
            return 0;
        }
        return TVNative.setEarcEnable_native(z);
    }

    public static boolean getEarcStatus_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getEarcStatus_native called...\n");
            return false;
        }
        return TVNative.getEarcStatus_native(i);
    }

    protected static int drmpKeyUpdate_native() {
        if (is_emulator()) {
            Log.d(TAG, "drmpKeyUpdate_native called...\n");
            return 0;
        }
        return TVNative.drmpKeyUpdate_native();
    }

    protected static int drmpInitQDAForQdc_native() {
        if (is_emulator()) {
            Log.d(TAG, "drmpInitQDAForQdc_native called...\n");
            return 0;
        }
        return TVNative.drmpInitQDAForQdc_native();
    }

    protected static int drmpQdaFactoryReset_native() {
        if (is_emulator()) {
            Log.d(TAG, "drmpQdaFactoryReset_native called...\n");
            return 0;
        }
        return TVNative.drmpQdaFactoryReset_native();
    }

    protected static int drmpQdaForceCleanup_native() {
        if (is_emulator()) {
            Log.d(TAG, "drmpQdaForceCleanup_native called...\n");
            return 0;
        }
        return TVNative.drmpQdaForceCleanup_native();
    }

    protected static int drmpQdaFactoryResetWithReturn_native() {
        if (is_emulator()) {
            Log.d(TAG, "drmpQdaFactoryResetWithReturn_native called...\n");
            return 0;
        }
        return TVNative.drmpQdaFactoryResetWithReturn_native();
    }

    protected static int drmpQdaStop_native() {
        if (is_emulator()) {
            Log.d(TAG, "drmpQdaStop_native called...\n");
            return 0;
        }
        return TVNative.drmpQdaStop_native();
    }

    protected static byte[] getDrmpDeviceID_native() {
        if (is_emulator()) {
            Log.d(TAG, "getDrmpDeviceID_native called...\n");
            return null;
        }
        return TVNative.getDrmpDeviceID_native();
    }

    protected static int fvpMtdtOperator_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "fvpMtdtOperator_native called...");
            return 0;
        }
        return TVNative.fvpMtdtOperator_native(str);
    }

    public static String getCmdExecResult_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "getCmdExecResult_native called...\n");
            return new String();
        }
        return TVNative.getCmdExecResult_native(str);
    }

    public static int checkWideVineKeyBox_native() {
        if (is_emulator()) {
            Log.d(TAG, "checkWideVineKeyBox_native called...\n");
            return -1;
        }
        return TVNative.checkWideVineKeyBox_native();
    }

    public static int checkPlayready40KeyBox_native() {
        if (is_emulator()) {
            Log.d(TAG, "checkPlayready40KeyBox_native called...\n");
            return -1;
        }
        return TVNative.checkPlayready40KeyBox_native();
    }

    public static int factoryWriteAttestationKey_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "factoryWriteAttestationKey_native called...\n");
            return -1;
        }
        return TVNative.factoryWriteAttestationKey_native(str);
    }

    public static int mountPerm_native() {
        if (is_emulator()) {
            Log.d(TAG, "mountPerm_native called...\n");
            return -1;
        }
        return TVNative.mountPerm_native();
    }

    public static int unmountPerm_native() {
        if (is_emulator()) {
            Log.d(TAG, "unmountPerm_native called...\n");
            return -1;
        }
        return TVNative.unmountPerm_native();
    }

    public static int checkQDAKey_native() {
        if (is_emulator()) {
            Log.d(TAG, "checkQDAKey_native called...\n");
            return -1;
        }
        return TVNative.checkQDAKey_native();
    }

    public static int installQDAKey_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "installQDAKey_native called...\n");
            return -1;
        }
        return TVNative.installQDAKey_native(str);
    }

    public static int checkFairPlayKey_native() {
        if (is_emulator()) {
            Log.d(TAG, "checkFairPlayKey_native called...\n");
            return -1;
        }
        return TVNative.checkFairPlayKey_native();
    }

    protected static int GamSendcmd_native(MtkTvGamCmdInfoBase mtkTvGamCmdInfoBase) {
        if (is_emulator()) {
            Log.d(TAG, "GamSendcmd_native called...\n");
            return 0;
        }
        return TVNative.GamSendcmd_native(mtkTvGamCmdInfoBase);
    }
}
