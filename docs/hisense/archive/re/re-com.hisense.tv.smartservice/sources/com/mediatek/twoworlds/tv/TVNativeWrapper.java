package com.mediatek.twoworlds.tv;

import android.util.Log;
import b.a.a.a.a;
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

/* JADX INFO: loaded from: classes2.dex */
public class TVNativeWrapper {
    public static final String TAG = "TVNativeWrapper(Emu)";
    public static boolean mIsregisterCallback = false;

    public static boolean AppTVAutoClockPhasePostionCondSuccess_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "AppTVAutoClockPhasePostionCondSuccess_native Emulator called");
            return true;
        }
        Log.d(TAG, "AutoClockPhasePostionCondSuccess_native  called");
        return TVNative.AppTVAutoClockPhasePostionCondSuccess_native(str);
    }

    public static boolean AppTVAutoColorCondSuccess_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "AppTVAutoColorCondSuccess_native Emulator called");
            return true;
        }
        Log.d(TAG, "AppTVAutoColorCondSuccess_native  called");
        return TVNative.AppTVAutoColorCondSuccess_native(str);
    }

    public static int AppTVGetCapabilityEx_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "AppTVGetCapabilityEx_native Emulator called");
            return 0;
        }
        Log.d(TAG, "AppTVGetCapabilityEx_native  called");
        return TVNative.AppTVGetCapabilityEx_native(i);
    }

    public static int AppTV_swapTmpUnlockInfo_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "AppTV_swapTmpUnlockInfo_native Emulator called");
            return 0;
        }
        Log.d(TAG, "AppTV_swapTmpUnlockInfo_native\tcalled");
        return TVNative.AppTV_swapTmpUnlockInfo_native(i);
    }

    public static int AppTVchangeFreq_native(String str, MtkTvFreqChgParamBase mtkTvFreqChgParamBase) {
        if (is_emulator()) {
            Log.d(TAG, "AppTVchangeFreq_native Emulator called");
            return 0;
        }
        Log.d(TAG, "AppTVchangeFreq_native  called");
        return TVNative.AppTVchangeFreq_native(str, mtkTvFreqChgParamBase);
    }

    public static int AppTVgetMatchedChannel_native(int i, boolean z, int i2, int i3) {
        if (is_emulator()) {
            Log.d(TAG, "AppTVgetMatchedChannel_native Emulator called");
            return 0;
        }
        Log.d(TAG, "AppTVgetMatchedChannel_native  called");
        return TVNative.AppTVgetMatchedChannel_native(i, z, i2, i3);
    }

    public static int AppTVsaveTimestamp_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "AppTVsaveTimestamp_native Emulator called");
            return 0;
        }
        Log.d(TAG, "AppTVsaveTimestamp_native  called");
        return TVNative.AppTVsaveTimestamp_native(str);
    }

    public static int AppTVsetAutoClockPhasePostion_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "AppTVsetAutoClockPhasePostion_native Emulator called");
            return 0;
        }
        Log.d(TAG, "AppTVsetAutoClockPhasePostion_native  called");
        return TVNative.AppTVsetAutoClockPhasePostion_native(str);
    }

    public static int AppTVsetAutoColor_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "AppTVsetAutoColor_native Emulator called");
            return 0;
        }
        Log.d(TAG, "AppTVsetAutoColor_native  called");
        return TVNative.AppTVsetAutoColor_native(str);
    }

    public static int AppTVsetFinetuneFreq_native(String str, int i, boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "AppTVsetFinetuneFreq_native Emulator called");
            return 0;
        }
        Log.d(TAG, "AppTVsetFinetuneFreq_native  called");
        return TVNative.AppTVsetFinetuneFreq_native(str, i, z);
    }

    public static int AppTVsetVideoMute_native(String str, boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "AppTVsetVideoMute_native Emulator called");
            return 0;
        }
        Log.d(TAG, "AppTVsetVideoMute_native  called");
        return TVNative.AppTVsetVideoMute_native(str, z);
    }

    public static int AppTVunblockSvc_native(String str, boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "AppTVunblockSvc_native Emulator called");
            return 0;
        }
        Log.d(TAG, "AppTVunblockSvc_native  called");
        return TVNative.AppTVunblockSvc_native(str, z);
    }

    public static int AppTVunlockService_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "AppTVunlockService_native Emulator called");
            return 0;
        }
        Log.d(TAG, "AppTVunlockService_native  called");
        return TVNative.AppTVunlockService_native(str);
    }

    public static int AppTVupdatedSysStatus_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "AppTVupdatedSysStatus_native Emulator called");
            return 0;
        }
        Log.d(TAG, "AppTVupdatedSysStatus_native  called");
        return TVNative.AppTVupdatedSysStatus_native(i);
    }

    public static int ChangeModeforBuiltInVideoUpgrade_native() {
        if (is_emulator()) {
            Log.d(TAG, "[Storemode]ChangeModeforBuiltInVedioUpgrade_native called");
            return -1;
        }
        Log.v(TAG, "[Storemode] TVNative.startLanB2BWorkThread_native called");
        return TVNative.ChangeModeforBuiltInVideoUpgrade_native();
    }

    public static int GamSendcmd_native(MtkTvGamCmdInfoBase mtkTvGamCmdInfoBase) {
        if (!is_emulator()) {
            return TVNative.GamSendcmd_native(mtkTvGamCmdInfoBase);
        }
        Log.d(TAG, "GamSendcmd_native called...\n");
        return 0;
    }

    public static long GetConnAtrrAGC_native(String str) {
        if (!is_emulator()) {
            return TVNative.GetConnAtrrAGC_native(str);
        }
        Log.d(TAG, "GetConnAtrrAGC_native called...\n");
        return 0L;
    }

    public static int GetConnAttrBER_native(String str) {
        if (!is_emulator()) {
            return TVNative.GetConnAttrBER_native(str);
        }
        Log.d(TAG, "GetConnAttrBER_native called...\n");
        return 0;
    }

    public static long GetConnAttrDBMSNR_native(String str) {
        if (!is_emulator()) {
            return TVNative.GetConnAttrDBMSNR_native(str);
        }
        Log.d(TAG, "GetConnAttrDBMSNR_native called...\n");
        return 0L;
    }

    public static long GetConnAttrUEC_native(String str) {
        if (!is_emulator()) {
            return TVNative.GetConnAttrUEC_native(str);
        }
        Log.d(TAG, "GetConnAttrUEC_native called...\n");
        return 0L;
    }

    public static String GetCurrentChannelServiceText_native() {
        if (!is_emulator()) {
            return TVNative.GetCurrentChannelServiceText_native();
        }
        Log.d(TAG, "GetCurrentChannelServiceText_native called...\n");
        return new String();
    }

    public static boolean GetDivXHDSupport_native() {
        if (!is_emulator()) {
            return TVNative.GetDivXHDSupport_native();
        }
        Log.d(TAG, "GetDivXHDSupport_native called...\n");
        return false;
    }

    public static boolean GetDivXPlusSupport_native() {
        if (!is_emulator()) {
            return TVNative.GetDivXPlusSupport_native();
        }
        Log.d(TAG, "GetDivXPlusSupport_native called...\n");
        return false;
    }

    public static String GetDrmRegistrationCode_native() {
        if (!is_emulator()) {
            return TVNative.GetDrmRegistrationCode_native();
        }
        Log.d(TAG, "GetDrmRegistrationCode_native called...\n");
        return new String();
    }

    public static long GetDrmUiHelpInfo_native() {
        if (!is_emulator()) {
            return TVNative.GetDrmUiHelpInfo_native();
        }
        Log.d(TAG, "GetDrmUiHelpInfo_native called...\n");
        return 0L;
    }

    public static int GetModulation_native(String str) {
        if (!is_emulator()) {
            return TVNative.GetModulation_native(str);
        }
        Log.d(TAG, "GetModulation_native called...\n");
        return 0;
    }

    public static int GetSignalBER_native(String str) {
        if (!is_emulator()) {
            return TVNative.GetSignalBER_native(str);
        }
        Log.d(TAG, "GetSignalBER_native called...\n");
        return 0;
    }

    public static long GetSymRate_native(String str) {
        if (!is_emulator()) {
            return TVNative.GetSymRate_native(str);
        }
        Log.d(TAG, "GetSymRate_native called...\n");
        return 0L;
    }

    public static int GetVideoSrcTag3DType_native(String str) {
        if (!is_emulator()) {
            return TVNative.GetVideoSrcTag3DType_native(str);
        }
        Log.d(TAG, "GetVideoSrcTag3DType_native called...\n");
        return 0;
    }

    public static int HighLevel_native(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        if (!is_emulator()) {
            return TVNative.HighLevel_native(i, i2, i3, i4, i5, i6, i7);
        }
        Log.d(TAG, "HighLevel_native called");
        return 0;
    }

    public static int ISDBCCEnable_native(boolean z) {
        if (!is_emulator()) {
            return TVNative.ISDBCCEnable_native(z);
        }
        Log.d(TAG, "ISDBCCEnable_native called ...\n");
        return 0;
    }

    public static int ISDBCCGetCCString_native() {
        if (!is_emulator()) {
            return TVNative.ISDBCCGetCCString_native();
        }
        Log.d(TAG, "ISDBCCGetCCString_native called ...\n");
        return 0;
    }

    public static int ISDBCCNextStream_native() {
        if (!is_emulator()) {
            return TVNative.ISDBCCNextStream_native();
        }
        Log.d(TAG, "ISDBCCNextStream_native called ...\n");
        return 0;
    }

    public static int PWDShow_native() {
        if (!is_emulator()) {
            return TVNative.PWDShow_native();
        }
        Log.d(TAG, "PWDShow_native is called ... \n");
        return 0;
    }

    public static int ScanATSCExchangeData_native(int[] iArr) {
        if (!is_emulator()) {
            return TVNative.ScanATSCExchangeData_native(iArr);
        }
        Log.d(TAG, "ScanATSCExchangeData called...\n");
        return 0;
    }

    public static int ScanCeExchangeData_native(int[] iArr) {
        if (!is_emulator()) {
            return TVNative.ScanCeExchangeData_native(iArr);
        }
        Log.d(TAG, "ScanCeExchangeData called... \n");
        return 0;
    }

    public static int ScanDtmbExchangeData_native(int[] iArr) {
        if (!is_emulator()) {
            return TVNative.ScanDtmbExchangeData_native(iArr);
        }
        Log.d(TAG, "ScanDtmbExchangeData called... \n");
        return 0;
    }

    public static int ScanDvbcExchangeData_native(int[] iArr) {
        if (!is_emulator()) {
            return TVNative.ScanDvbcExchangeData_native(iArr);
        }
        Log.d(TAG, "ScanDvbcExchangeData called...\n");
        return 0;
    }

    public static String ScanDvbcGetStrData_native(int i) {
        if (!is_emulator()) {
            return TVNative.ScanDvbcGetStrData_native(i);
        }
        Log.d(TAG, "ScanDvbcGetStrData called...\n");
        return null;
    }

    public static int ScanDvbsExchangeData_native(int[] iArr) {
        if (!is_emulator()) {
            return TVNative.ScanDvbsExchangeData_native(iArr);
        }
        Log.d(TAG, "ScanDvbsExchangeData called...\n");
        return 0;
    }

    public static String ScanDvbsgetUserMessage_native(String str) {
        if (!is_emulator()) {
            return TVNative.ScanDvbsgetUserMessage_native(str);
        }
        Log.d(TAG, "ScanDvbsgetUserMessage_native called");
        return null;
    }

    public static int ScanDvbtExchangeData_native(int[] iArr) {
        if (!is_emulator()) {
            return TVNative.ScanDvbtExchangeData_native(iArr);
        }
        Log.d(TAG, "ScanDvbtExchangeData called...\n");
        return 0;
    }

    public static int ScanISDBExchangeData_native(int[] iArr) {
        if (!is_emulator()) {
            return TVNative.ScanISDBExchangeData_native(iArr);
        }
        Log.d(TAG, "ScanISDBExchangeData called...\n");
        return 0;
    }

    public static int ScanPalSecamExchangeData_native(int[] iArr) {
        if (!is_emulator()) {
            return TVNative.ScanPalSecamExchangeData_native(iArr);
        }
        Log.d(TAG, "ScanPalSecamExchangeData called...\n");
        return 0;
    }

    public static void SendKeyClick_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "SendKeyClick_native called");
        } else {
            TVNative.SendKeyClick_native(i);
        }
    }

    public static void SendKey_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "SendKey_native called");
        } else {
            TVNative.SendKey_native(i, i2);
        }
    }

    public static void SendMouseButton_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "SendMouseButton_native called");
        } else {
            TVNative.SendMouseButton_native(i, i2);
        }
    }

    public static void SendMouseMove_native(int i, int i2, int i3, int i4) {
        if (is_emulator()) {
            Log.d(TAG, "SendMouseMove_native called");
        } else {
            TVNative.SendMouseMove_native(i, i2, i3, i4);
        }
    }

    public static String SetDrmDeactivation_native() {
        if (!is_emulator()) {
            return TVNative.SetDrmDeactivation_native();
        }
        Log.d(TAG, "SetDrmDeactivation_native called...\n");
        return new String();
    }

    public static boolean SetRecordDisk(String str) {
        if (!is_emulator()) {
            return TVNative.SetRecordDisk_native(str);
        }
        Log.d(TAG, "SetRecordDisk called...\n");
        return true;
    }

    public static boolean SetRecordDiskByHandle(int i, String str) {
        if (!is_emulator()) {
            return TVNative.SetRecordDiskByHandle_native(i, str);
        }
        Log.d(TAG, "SetRecordDisk called...\n");
        return true;
    }

    public static int SubtitleGetTracks_native(int[] iArr) {
        if (!is_emulator()) {
            return TVNative.SubtitleGetTracks_native(iArr);
        }
        Log.d(TAG, "SubtitleGetTracks_native called ...\n");
        return 0;
    }

    public static boolean VideoSrcIsProgressive_native(String str) {
        if (!is_emulator()) {
            return TVNative.VideoSrcIsProgressive_native(str);
        }
        Log.d(TAG, "VideoSrcIsProgressive_native called...\n");
        return true;
    }

    public static int acceptRestart_native() {
        if (!is_emulator()) {
            return TVNative.acceptRestart_native();
        }
        Log.d(TAG, "acceptRestart_native called...");
        return 0;
    }

    public static int acceptSchedule_native() {
        if (!is_emulator()) {
            return TVNative.acceptSchedule_native();
        }
        Log.d(TAG, "acceptSchedule_native called...");
        return 0;
    }

    public static int ackChooseAppDialog_native(String str, String str2, boolean z) {
        if (!is_emulator()) {
            return TVNative.ackChooseAppDialog_native(str, str2, z);
        }
        Log.d(TAG, "ackChooseAppDialog_native called ...\n");
        return 0;
    }

    public static int ackDialog_native(int i) {
        if (!is_emulator()) {
            return TVNative.ackDialog_native(i);
        }
        Log.d(TAG, "ackDialog_native called ...\n");
        return 0;
    }

    public static int ackTrustDialog_native(boolean z) {
        if (!is_emulator()) {
            return TVNative.ackTrustDialog_native(z);
        }
        Log.d(TAG, "ackTrustDialog_native called ...\n");
        return 0;
    }

    public static boolean addBooking_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.addBooking_native(i, i2);
        }
        Log.d(TAG, "addBooking_native called...\n");
        return true;
    }

    public static int addConfigListener_native(String str) {
        if (!is_emulator()) {
            return TVNative.addConfigListener_native(str);
        }
        Log.d(TAG, "addConfigListener_native called");
        return 0;
    }

    public static int addFavoritelistChannelByIndex_native(int i) {
        if (!is_emulator()) {
            return TVNative.addFavoritelistChannelByIndex_native(i);
        }
        Log.d(TAG, "addFavoritelistChannelByIndex_native called...\n");
        return 0;
    }

    public static int addFavoritelistChannel_native() {
        if (!is_emulator()) {
            return TVNative.addFavoritelistChannel_native();
        }
        Log.d(TAG, "addFavoritelistChannel_native called...\n");
        return 0;
    }

    public static int analogCCEnable_native(boolean z) {
        if (!is_emulator()) {
            return TVNative.analogCCEnable_native(z);
        }
        Log.d(TAG, "analogCCEnable_native called ...\n");
        return 0;
    }

    public static int analogCCGetCcIndex_native() {
        if (!is_emulator()) {
            return TVNative.analogCCGetCcIndex_native();
        }
        Log.d(TAG, "analogCCGetCcIndex_native called ...\n");
        return 0;
    }

    public static int analogCCNextStream_native() {
        if (!is_emulator()) {
            return TVNative.analogCCNextStream_native();
        }
        Log.d(TAG, "analogCCNextStream_native called ...\n");
        return 0;
    }

    public static int analogCCSetCcVisible_native(boolean z) {
        if (!is_emulator()) {
            return TVNative.analogCCSetCcVisible_native(z);
        }
        Log.d(TAG, "analogCCSetCcVisible_native called ...\n");
        return 0;
    }

    public static int applyGpioStatus_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.applyGpioStatus_native(i, i2);
        }
        Log.d(TAG, "applyGpioStatus_native called...\n");
        return 0;
    }

    public static int atscCCDemoSet_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.atscCCDemoSet_native(i, i2);
        }
        Log.d(TAG, "atscCCDemoSet_native called ...\n");
        return 0;
    }

    public static int atscCCEnable_native(boolean z) {
        if (!is_emulator()) {
            return TVNative.atscCCEnable_native(z);
        }
        Log.d(TAG, "atscCCEnable_native called ...\n");
        return 0;
    }

    public static int atscCCGetCCIndex_native() {
        if (!is_emulator()) {
            return TVNative.atscCCGetCCIndex_native();
        }
        Log.d(TAG, "atscCCGetCCIndex_native called ...\n");
        return 0;
    }

    public static int atscCCNextStream_native() {
        if (!is_emulator()) {
            return TVNative.atscCCNextStream_native();
        }
        Log.d(TAG, "atscCCNextStream_native called ...\n");
        return 0;
    }

    public static int atscCCSetCcVisible_native(boolean z) {
        if (!is_emulator()) {
            return TVNative.atscCCSetCcVisible_native(z);
        }
        Log.d(TAG, "atscCCSetCcVisible_native called ...\n");
        return 0;
    }

    public static int bisskeyClean_native(int i) {
        if (!is_emulator()) {
            return TVNative.bisskeyClean_native(i);
        }
        Log.d(TAG, "bisskeyClean_native called...\n");
        return 0;
    }

    public static MtkTvBisskeyInfoBase bisskeyGetDefaultRecord_native() {
        if (!is_emulator()) {
            return TVNative.bisskeyGetDefaultRecord_native();
        }
        Log.d(TAG, "bisskeyGetDefaultRecord_native called...\n");
        return null;
    }

    public static int bisskeyIsSameRecord_native(MtkTvBisskeyInfoBase mtkTvBisskeyInfoBase, MtkTvBisskeyInfoBase mtkTvBisskeyInfoBase2) {
        if (!is_emulator()) {
            return TVNative.bisskeyIsSameRecord_native(mtkTvBisskeyInfoBase, mtkTvBisskeyInfoBase2);
        }
        Log.d(TAG, "bisskeyIsSameRecord_native called...\n");
        return 0;
    }

    public static int bisskeySetKeyForCurrentChannel_native() {
        if (!is_emulator()) {
            return TVNative.bisskeySetKeyForCurrentChannel_native();
        }
        Log.d(TAG, "bisskeySetKeyForCurrentChannel_native called...\n");
        return 0;
    }

    public static int block_native(int i, boolean z) {
        if (!is_emulator()) {
            return TVNative.block_native(i, z);
        }
        Log.d(TAG, "block_native called");
        return 0;
    }

    public static int cancelCamScan_navtive(int i) {
        if (is_emulator()) {
            Log.d(TAG, "cancelCamScan_navtive called...");
            return 0;
        }
        Log.d(TAG, "cancelCamScan_navtive called...");
        return TVNative.cancelCamScan_navtive(i);
    }

    public static int cancelDownloadFirmware_native(MtkTvUpgradeDeliveryTypeBase mtkTvUpgradeDeliveryTypeBase) {
        if (!is_emulator()) {
            return TVNative.cancelDownloadFirmware_native(mtkTvUpgradeDeliveryTypeBase);
        }
        Log.d(TAG, "cancelDownloadFirmware_native called ...");
        return 0;
    }

    public static int cancelScan_native() {
        if (!is_emulator()) {
            return TVNative.cancelScan_native();
        }
        Log.d(TAG, "cancelScan_native called...\n");
        return 0;
    }

    public static int changeFreq_native(String str, MtkTvFreqChgParamBase mtkTvFreqChgParamBase) {
        if (!is_emulator()) {
            return TVNative.changeFreq_native(str, mtkTvFreqChgParamBase);
        }
        Log.d(TAG, "changeFreq_native called...\n");
        return 0;
    }

    public static int changeInputSourcebySourceid_native(int i) {
        if (!is_emulator()) {
            return TVNative.changeInputSourcebySourceid_native(i);
        }
        Log.d(TAG, "changeInputSourcebySourceid_native called");
        return 0;
    }

    public static int channelSelectByChannelId_native(int i, boolean z) {
        if (!is_emulator()) {
            return TVNative.channelSelectByChannelId_native(i, z);
        }
        Log.d(TAG, "channelSelectByChannelId_native called...\n");
        return 0;
    }

    public static int channelSelectByChannelNumber_native(int i, int i2, boolean z) {
        if (!is_emulator()) {
            return TVNative.channelSelectByChannelNumber_native(i, i2, z);
        }
        Log.d(TAG, "channelSelectByChannelNumber_native called...\n");
        return 0;
    }

    public static int channelSelectByKeys_native(int i) {
        if (!is_emulator()) {
            return TVNative.channelSelectByKeys_native(i);
        }
        Log.d(TAG, "channelSelectByKeys_native called...\n");
        return 0;
    }

    public static int channelSelectSilently_native(MtkTvChannelInfoBase mtkTvChannelInfoBase, int i, int i2) {
        if (!is_emulator()) {
            return TVNative.channelSelectSilently_native(mtkTvChannelInfoBase, i, i2);
        }
        Log.d(TAG, "channelSelectSilently_native called...\n");
        return 0;
    }

    public static int channelSelect_native(MtkTvChannelInfoBase mtkTvChannelInfoBase, boolean z) {
        if (!is_emulator()) {
            return TVNative.channelSelect_native(mtkTvChannelInfoBase, z);
        }
        Log.d(TAG, "channelSelect_native called...\n");
        return 0;
    }

    public static boolean checkAtscEventBlock_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.checkAtscEventBlock_native(i, i2);
        }
        Log.d(TAG, "checkAtscEventBlock_native called ...\n");
        return false;
    }

    public static int[] checkEITStauts_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.checkEITStauts_native(i, i2);
        }
        Log.d(TAG, "checkEITStauts_native called ...\n");
        return null;
    }

    public static boolean checkEventBlock_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.checkEventBlock_native(i, i2);
        }
        Log.d(TAG, "checkEventBlock called...\n");
        return false;
    }

    public static int checkFairPlayKey_native() {
        if (!is_emulator()) {
            return TVNative.checkFairPlayKey_native();
        }
        Log.d(TAG, "checkFairPlayKey_native called...\n");
        return -1;
    }

    public static boolean checkPCLWakeupReasonToBGM_native() {
        if (!is_emulator()) {
            return TVNative.checkPCLWakeupReasonToBGM_native();
        }
        Log.d(TAG, "checkPCLWakeupReasonToBGM called");
        return false;
    }

    public static boolean checkPWD_native(String str) {
        if (!is_emulator()) {
            return TVNative.checkPWD_native(str);
        }
        Log.d(TAG, "checkPWD_native called ...\n");
        return false;
    }

    public static int checkPlayready40KeyBox_native() {
        if (!is_emulator()) {
            return TVNative.checkPlayready40KeyBox_native();
        }
        Log.d(TAG, "checkPlayready40KeyBox_native called...\n");
        return -1;
    }

    public static int checkQDAKey_native() {
        if (!is_emulator()) {
            return TVNative.checkQDAKey_native();
        }
        Log.d(TAG, "checkQDAKey_native called...\n");
        return -1;
    }

    public static boolean checkRecordRegisterFile_native(String str) {
        if (!is_emulator()) {
            return TVNative.checkRecordRegisterFile_native(str);
        }
        Log.d(TAG, "checkRecordRegisterFile_native called...\n");
        return true;
    }

    public static int checkWideVineKeyBox_native() {
        if (!is_emulator()) {
            return TVNative.checkWideVineKeyBox_native();
        }
        Log.d(TAG, "checkWideVineKeyBox_native called...\n");
        return -1;
    }

    public static int cleanChannelList_native(int i, boolean z) {
        if (!is_emulator()) {
            return TVNative.cleanChannelList_native(i, z);
        }
        Log.d(TAG, "cleanChannelList_native called...\n");
        return 0;
    }

    public static int clearActiveWindow_native() {
        if (!is_emulator()) {
            return TVNative.clearActiveWindow_native();
        }
        Log.d(TAG, "clearActiveWindow_native called...\n");
        return 0;
    }

    public static int clearOadVersion_native() {
        if (!is_emulator()) {
            return TVNative.clearOadVersion_native();
        }
        Log.d(TAG, "clearOadVersion_native called...");
        return 0;
    }

    public static int closeUARTSerial_native(int i) {
        if (!is_emulator()) {
            return TVNative.closeUARTSerial_native(i);
        }
        Log.d(TAG, "closeUARTSerial_native called...\n");
        return 0;
    }

    public static long convertDTGToSeconds_native(MtkTvTimeFormatBase mtkTvTimeFormatBase) {
        if (!is_emulator()) {
            return TVNative.convertDTGToSeconds_native(mtkTvTimeFormatBase);
        }
        Log.d(TAG, "convertDTGToSeconds_native called...\n");
        return 0L;
    }

    public static long convertLocalTimeToMillis_native(MtkTvTimeFormatBase mtkTvTimeFormatBase) {
        if (!is_emulator()) {
            return TVNative.convertLocalTimeToMillis_native(mtkTvTimeFormatBase);
        }
        Log.d(TAG, "convertLocalTimeToMillis_native called...\n");
        return 0L;
    }

    public static void convertMillisToLocalTime_native(long j, MtkTvTimeFormatBase mtkTvTimeFormatBase) {
        if (is_emulator()) {
            Log.d(TAG, "convertMillisToLocalTime_native called...\n");
        } else {
            TVNative.convertMillisToLocalTime_native(j, mtkTvTimeFormatBase);
        }
    }

    public static int convertTime_native(int i, MtkTvTimeFormatBase mtkTvTimeFormatBase, MtkTvTimeFormatBase mtkTvTimeFormatBase2) {
        if (!is_emulator()) {
            return TVNative.convertTime_native(i, mtkTvTimeFormatBase, mtkTvTimeFormatBase2);
        }
        Log.d(TAG, "convertTime_native called...\n");
        return 0;
    }

    public static int convertUTCSecToDTG_native(long j, MtkTvTimeFormatBase mtkTvTimeFormatBase) {
        if (!is_emulator()) {
            return TVNative.convertUTCSecToDTG_native(j, mtkTvTimeFormatBase);
        }
        Log.d(TAG, "convertUTCSecToDTG_native called...\n");
        return 0;
    }

    public static void createMonitorInst_native(byte b2) {
        if (is_emulator()) {
            Log.d(TAG, "createMonitorInst_native called...\n");
        } else {
            TVNative.createMonitorInst_native(b2);
        }
    }

    public static int createSatlSnapshot_native(int i) {
        if (!is_emulator()) {
            return TVNative.createSatlSnapshot_native(i);
        }
        Log.d(TAG, "createSatlSnapshot_native called...\n");
        return 0;
    }

    public static int createSnapshot_native(int i) {
        if (!is_emulator()) {
            return TVNative.createSnapshot_native(i);
        }
        Log.d(TAG, "createSnapshot_native called...\n");
        return 0;
    }

    public static int decrVolume_native() {
        if (!is_emulator()) {
            return TVNative.decrVolume_native();
        }
        Log.d(TAG, "decrVolume_native called");
        return 0;
    }

    public static int delBooking_native(int i) {
        if (!is_emulator()) {
            return TVNative.delBooking_native(i);
        }
        Log.d(TAG, "delBooking_native called...\n");
        return -1;
    }

    public static int deleteChannelByBrdcstType_native(int i, int i2, boolean z) {
        if (!is_emulator()) {
            return TVNative.deleteChannelByBrdcstType_native(i, i2, z);
        }
        Log.d(TAG, "deleteChannelByBrdcstType called...\n");
        return 0;
    }

    public static void deleteMonitorInst_native(byte b2) {
        if (is_emulator()) {
            Log.d(TAG, "deleteMonitorInst_native called...\n");
        } else {
            TVNative.deleteMonitorInst_native(b2);
        }
    }

    public static int deletePvrBrowserFileByIndex_native(int i) {
        if (!is_emulator()) {
            return TVNative.deletePvrBrowserFileByIndex_native(i);
        }
        Log.d(TAG, "deletePvrBrowserFileByIndex_native called...\n");
        return 0;
    }

    public static int discoveryDevice_native(MtkTvCecDevDiscoveryInfoBase mtkTvCecDevDiscoveryInfoBase) {
        if (!is_emulator()) {
            return TVNative.discoveryDevice_native(mtkTvCecDevDiscoveryInfoBase);
        }
        Log.d(TAG, "discoveryDevice_native called...\n");
        return 0;
    }

    public static boolean doUnprotectedClear() {
        MtkTvUtil.getInstance().resetPri();
        return true;
    }

    public static void doresetPub() {
        MtkTvUtil.getInstance().resetPub();
    }

    public static int drmpInitQDAForQdc_native() {
        if (!is_emulator()) {
            return TVNative.drmpInitQDAForQdc_native();
        }
        Log.d(TAG, "drmpInitQDAForQdc_native called...\n");
        return 0;
    }

    public static int drmpKeyUpdate_native() {
        if (!is_emulator()) {
            return TVNative.drmpKeyUpdate_native();
        }
        Log.d(TAG, "drmpKeyUpdate_native called...\n");
        return 0;
    }

    public static int drmpQdaFactoryResetWithReturn_native() {
        if (!is_emulator()) {
            return TVNative.drmpQdaFactoryResetWithReturn_native();
        }
        Log.d(TAG, "drmpQdaFactoryResetWithReturn_native called...\n");
        return 0;
    }

    public static int drmpQdaFactoryReset_native() {
        if (!is_emulator()) {
            return TVNative.drmpQdaFactoryReset_native();
        }
        Log.d(TAG, "drmpQdaFactoryReset_native called...\n");
        return 0;
    }

    public static int drmpQdaForceCleanup_native() {
        if (!is_emulator()) {
            return TVNative.drmpQdaForceCleanup_native();
        }
        Log.d(TAG, "drmpQdaForceCleanup_native called...\n");
        return 0;
    }

    public static int drmpQdaStop_native() {
        if (!is_emulator()) {
            return TVNative.drmpQdaStop_native();
        }
        Log.d(TAG, "drmpQdaStop_native called...\n");
        return 0;
    }

    public static void endLanB2BWorkThread_native() {
        if (is_emulator()) {
            Log.d(TAG, "[B2B]endLanB2BWorkThread_native called");
        } else {
            TVNative.endLanB2BWorkThread_native();
            Log.v(TAG, "[B2B] TVNative.endLanB2BWorkThread_native called");
        }
    }

    public static void enterMMI_navtive(int i) {
        if (is_emulator()) {
            Log.d(TAG, "enterMMI_navtive called...");
        } else {
            TVNative.enterMMI_navtive(i);
        }
    }

    public static int enter_native() {
        if (!is_emulator()) {
            return TVNative.enter_native();
        }
        Log.d(TAG, "enter called...\n");
        return 0;
    }

    public static int eraseCIKey_navtive() {
        if (!is_emulator()) {
            return TVNative.eraseCIKey_navtive();
        }
        Log.d(TAG, "eraseCIKey_navtive called ...\n");
        return 0;
    }

    public static int factoryCheckKey_native(String str) {
        if (!is_emulator()) {
            return TVNative.factoryCheckKey_native(str);
        }
        Log.d(TAG, "factoryCheckKey_native called...\n");
        return 0;
    }

    public static int factoryCleanKey_native(String str) {
        if (!is_emulator()) {
            return TVNative.factoryCleanKey_native(str);
        }
        Log.d(TAG, "factoryCleanKey_native called...\n");
        return 0;
    }

    public static String factoryReadFile_native(String str) {
        if (!is_emulator()) {
            return TVNative.factoryReadFile_native(str);
        }
        Log.d(TAG, "factoryReadFile_native called...\n");
        return new String();
    }

    public static String factoryReadLog_native() {
        if (!is_emulator()) {
            return TVNative.factoryReadLog_native();
        }
        Log.d(TAG, "factoryReadLog_native called...\n");
        return new String();
    }

    public static int factoryRemoveFile_native(String str) {
        if (!is_emulator()) {
            return TVNative.factoryRemoveFile_native(str);
        }
        Log.d(TAG, "factoryRemoveFile_native called...\n");
        return 0;
    }

    public static int factoryRemovePlayReady25Key_native() {
        if (!is_emulator()) {
            return TVNative.factoryRemovePlayReady25Key_native();
        }
        Log.d(TAG, "factoryRemovePlayReady25Key_native called...\n");
        return 0;
    }

    public static int factoryWriteAttestationKey_native(String str) {
        if (!is_emulator()) {
            return TVNative.factoryWriteAttestationKey_native(str);
        }
        Log.d(TAG, "factoryWriteAttestationKey_native called...\n");
        return -1;
    }

    public static int factoryWriteFileAppend_native(String str, String str2, int i) {
        if (!is_emulator()) {
            return TVNative.factoryWriteFileAppend_native(str, str2, i);
        }
        Log.d(TAG, "factoryWriteFileAppend_native called...\n");
        return 0;
    }

    public static int factoryWriteFile_native(String str, String str2) {
        if (!is_emulator()) {
            return TVNative.factoryWriteFile_native(str, str2);
        }
        Log.d(TAG, "factoryWriteFile_native called...\n");
        return 0;
    }

    public static int factoryWriteKeyFinish_native() {
        if (!is_emulator()) {
            return TVNative.factoryWriteKeyFinish_native();
        }
        Log.d(TAG, "factoryWriteKeyFinish_native called...\n");
        return 0;
    }

    public static int factoryWriteKey_native(String str, String str2) {
        if (!is_emulator()) {
            return TVNative.factoryWriteKey_native(str, str2);
        }
        Log.d(TAG, "factoryWriteKey_native called...\n");
        return 0;
    }

    public static int factoryWriteLog_native(String str) {
        if (!is_emulator()) {
            return TVNative.factoryWriteLog_native(str);
        }
        Log.d(TAG, "factoryWriteLog_native called...\n");
        return 0;
    }

    public static int favoriteListAddTail_native(int i, int i2, int i3) {
        if (!is_emulator()) {
            return TVNative.favoriteListAddTail_native(i, i2, i3);
        }
        Log.d(TAG, "favoriteListAddTail called...\n");
        return 0;
    }

    public static int favoriteListInsertMove_native(int i, int i2, int i3, int i4) {
        if (!is_emulator()) {
            return TVNative.favoriteListInsertMove_native(i, i2, i3, i4);
        }
        Log.d(TAG, "favoriteListInsertMove called...\n");
        return 0;
    }

    public static int freeEvent_native(int i) {
        if (!is_emulator()) {
            return TVNative.freeEvent_native(i);
        }
        Log.d(TAG, "freeEvent_native called ...\n");
        return 0;
    }

    public static int freeSatlSnapshot_native(int i) {
        if (!is_emulator()) {
            return TVNative.freeSatlSnapshot_native(i);
        }
        Log.d(TAG, "freeSatlSnapshot_native called...\n");
        return 0;
    }

    public static int freeSnapshot_native(int i) {
        if (!is_emulator()) {
            return TVNative.freeSnapshot_native(i);
        }
        Log.d(TAG, "freeSnapshot_native called...\n");
        return 0;
    }

    public static int fvpMtdtOperator_native(String str) {
        if (!is_emulator()) {
            return TVNative.fvpMtdtOperator_native(str);
        }
        Log.d(TAG, "fvpMtdtOperator_native called...");
        return 0;
    }

    public static int getActiveSourceInfo_native(MtkTvCecActiveSourceBase mtkTvCecActiveSourceBase) {
        if (!is_emulator()) {
            return TVNative.getActiveSourceInfo_native(mtkTvCecActiveSourceBase);
        }
        Log.d(TAG, "getActiveSourceInfo_native called...\n");
        return 0;
    }

    public static boolean getAdbStatus_native() {
        if (is_emulator()) {
            Log.d(TAG, "getAdbStatus Emulator called");
            return false;
        }
        Log.d(TAG, "getAdbStatus  called");
        return TVNative.getAdbStatus_native();
    }

    public static int[] getAllPictureMode_native() {
        if (!is_emulator()) {
            return TVNative.getAllPictureMode_native();
        }
        Log.d(TAG, "getAllPictureMode_native called ...\n");
        return null;
    }

    public static int[] getAllScreenMode_native() {
        if (!is_emulator()) {
            return TVNative.getAllScreenMode_native();
        }
        Log.d(TAG, "getAllScreenMode_native called ...\n");
        return null;
    }

    public static int[] getAllSoundEffect_native() {
        if (!is_emulator()) {
            return TVNative.getAllSoundEffect_native();
        }
        Log.d(TAG, "getAllSoundEffect_native called ...\n");
        return null;
    }

    public static int getAllTvprovider_native(List<TvProviderChannelInfoBase> list) {
        if (!is_emulator()) {
            return TVNative.getAllTvprovider_native(list);
        }
        Log.d(TAG, "getAllTvprovider_native called...\n");
        return 0;
    }

    public static int getApdText_native(int i, MtkTvAudioApdBase mtkTvAudioApdBase) {
        if (!is_emulator()) {
            return TVNative.getApdText_native(i, mtkTvAudioApdBase);
        }
        Log.d(TAG, "getCurrentAudio_native...\n");
        return 0;
    }

    public static int getAppExtendedData_native(String str, MtkTvGingaAppExtendedDataBase mtkTvGingaAppExtendedDataBase) {
        if (!is_emulator()) {
            return TVNative.getAppExtendedData_native(str, mtkTvGingaAppExtendedDataBase);
        }
        Log.d(TAG, "getAppExtendedData_native called ...\n");
        return 0;
    }

    public static int getApplicationInfoList_native(List<MtkTvGingaAppInfoBase> list) {
        if (!is_emulator()) {
            return TVNative.getApplicationInfoList_native(list);
        }
        Log.d(TAG, "getApplicationInfoList_native called ...\n");
        return 0;
    }

    public static int getApplicationInfo_native() {
        if (!is_emulator()) {
            return TVNative.getApplicationInfo_native();
        }
        Log.d(TAG, "getApplicationInfo_native called ...\n");
        return 0;
    }

    public static void getAudioAvailableRecord_native(List<TvProviderAudioTrackBase> list) {
        if (is_emulator()) {
            Log.d(TAG, "getAudioAvailableRecord_native...\n");
        } else {
            TVNative.getAudioAvailableRecord_native(list);
        }
    }

    public static int getAudioDecType_native() {
        if (!is_emulator()) {
            return TVNative.getAudioDecType_native();
        }
        Log.d(TAG, "getAudioDecType_native...\n");
        return 0;
    }

    public static int getAudioFocus_native() {
        if (!is_emulator()) {
            return TVNative.getAudioFocus_native();
        }
        Log.d(TAG, "getAudioFocus_native called");
        return 0;
    }

    public static int getAudioInfoData_native(int i, MtkTvAudioInfoBase mtkTvAudioInfoBase) {
        if (!is_emulator()) {
            return TVNative.getAudioInfoData_native(i, mtkTvAudioInfoBase);
        }
        Log.d(TAG, "getAudioInfoData_native...\n");
        return 0;
    }

    public static int getAudioInfoValue_native(int i) {
        if (!is_emulator()) {
            return TVNative.getAudioInfoValue_native(i);
        }
        Log.d(TAG, "getAudioInfoValue_native...\n");
        return 0;
    }

    public static String getAudioInfo_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.getAudioInfo_native(i, i2);
        }
        Log.d(TAG, "getAudioInfo called...\n");
        return null;
    }

    public static void getAudioLang_native(MtkTvAVModeBase mtkTvAVModeBase) {
        if (is_emulator()) {
            Log.d(TAG, "getAudioLang_native...\n");
        } else {
            TVNative.getAudioLang_native(mtkTvAVModeBase);
        }
    }

    public static boolean getBlockUnrated_native() {
        if (!is_emulator()) {
            return TVNative.getBlockUnrated_native();
        }
        Log.d(TAG, "getBlockUnrated_native called...\n");
        return false;
    }

    public static int getBookingChannelId(int i) {
        if (!is_emulator()) {
            return TVNative.getBookingChannelId_native(i);
        }
        Log.d(TAG, "getBookingChannelId called...\n");
        return -1;
    }

    public static int getBookingCount_native() {
        if (!is_emulator()) {
            return TVNative.getBookingCount_native();
        }
        Log.d(TAG, "getBookingCount_native called...\n");
        return 0;
    }

    public static int getBookingDeviceIndex(int i) {
        if (!is_emulator()) {
            return TVNative.getBookingDeviceIndex_native(i);
        }
        Log.d(TAG, "getBookingDeviceIndex called...\n");
        return -1;
    }

    public static int getBookingEventId(int i) {
        if (!is_emulator()) {
            return TVNative.getBookingEventId_native(i);
        }
        Log.d(TAG, "getBookingEventId called...\n");
        return -1;
    }

    public static String getBookingEventTitle(int i) {
        if (!is_emulator()) {
            return TVNative.getBookingEventTitle_native(i);
        }
        Log.d(TAG, "getBookingEventTitle called...\n");
        return "";
    }

    public static int getBookingGenre(int i) {
        if (!is_emulator()) {
            return TVNative.getBookingGenre_native(i);
        }
        Log.d(TAG, "getBookingGenre called...\n");
        return -1;
    }

    public static int getBookingID(int i) {
        if (!is_emulator()) {
            return TVNative.getBookingID_native(i);
        }
        Log.d(TAG, "getBookingID called...\n");
        return -1;
    }

    public static int getBookingInfoData(int i) {
        if (!is_emulator()) {
            return TVNative.getBookingInfoData_native(i);
        }
        Log.d(TAG, "getBookingInfoData called...\n");
        return -1;
    }

    public static int getBookingRecordDelay(int i) {
        if (!is_emulator()) {
            return TVNative.getBookingRecordDelay_native(i);
        }
        Log.d(TAG, "getBookingRecordDelay called...\n");
        return -1;
    }

    public static long getBookingRecordDuration(int i) {
        if (!is_emulator()) {
            return TVNative.getBookingRecordDuration_native(i);
        }
        Log.d(TAG, "getBookingRecordDuration called...\n");
        return -1L;
    }

    public static int getBookingRecordMode(int i) {
        if (!is_emulator()) {
            return TVNative.getBookingRecordMode_native(i);
        }
        Log.d(TAG, "getBookingRecordMode called...\n");
        return -1;
    }

    public static int getBookingRepeatMode(int i) {
        if (!is_emulator()) {
            return TVNative.getBookingRepeatMode_native(i);
        }
        Log.d(TAG, "getBookingRepeatMode called...\n");
        return -1;
    }

    public static int getBookingSourceType(int i) {
        if (!is_emulator()) {
            return TVNative.getBookingSourceType_native(i);
        }
        Log.d(TAG, "getBookingSourceType called...\n");
        return -1;
    }

    public static long getBookingStartTime(int i) {
        if (!is_emulator()) {
            return TVNative.getBookingStartTime_native(i);
        }
        Log.d(TAG, "getBookingDeviceIndex called...\n");
        return -1L;
    }

    public static int getBookingTunerType(int i) {
        if (!is_emulator()) {
            return TVNative.getBookingTunerType_native(i);
        }
        Log.d(TAG, "getBookingTunerType called...\n");
        return -1;
    }

    public static int getBookingType(int i) {
        if (!is_emulator()) {
            return TVNative.getBookingType_native(i);
        }
        Log.d(TAG, "getBookingType called...\n");
        return -1;
    }

    public static String getBookingUrlInfo(int i) {
        if (!is_emulator()) {
            return TVNative.getBookingUrlInfo_native(i);
        }
        Log.d(TAG, "getBookingUrlInfo called...\n");
        return "";
    }

    public static MtkTvTimeRawDataBase getBrdcstRawData_native() {
        if (!is_emulator()) {
            return TVNative.getBrdcstRawData_native();
        }
        Log.d(TAG, "getBrdcstRawData_native called...\n");
        return null;
    }

    public static long getBroadcastTime_native(MtkTvTimeFormatBase mtkTvTimeFormatBase) {
        if (!is_emulator()) {
            return TVNative.getBroadcastTime_native(mtkTvTimeFormatBase);
        }
        Log.d(TAG, "getBroadcastTime_native called...\n");
        return 0L;
    }

    public static int getCANEngRatingSettingInfo_native() {
        if (!is_emulator()) {
            return TVNative.getCANEngRatingSettingInfo_native();
        }
        Log.d(TAG, "getCANEngRatingSettingInfo_native called...\n");
        return 0;
    }

    public static int getCANFreRatingSettingInfo_native() {
        if (!is_emulator()) {
            return TVNative.getCANFreRatingSettingInfo_native();
        }
        Log.d(TAG, "getCANFreRatingSettingInfo_native called...\n");
        return 0;
    }

    public static int getCIHostID_navtive(int[] iArr) {
        if (is_emulator()) {
            Log.d(TAG, "getCIHostID_navtive called ...\n");
            return 0;
        }
        Log.d(TAG, "getCIHostID_navtive called ...\n");
        return TVNative.getCIHostID_navtive(iArr);
    }

    public static boolean getCIKeyStatus_navtive() {
        if (is_emulator()) {
            Log.d(TAG, "getCIKeyStatus_navtive called ...\n");
            return false;
        }
        Log.d(TAG, "getCIKeyStatus_navtive called ...\n");
        return TVNative.getCIKeyStatus_navtive();
    }

    public static String getCIKeyinfo_navtive() {
        if (!is_emulator()) {
            return TVNative.getCIKeyinfo_navtive();
        }
        Log.d(TAG, "getCIKeyinfo_navtive called ...\n");
        return null;
    }

    public static int getCaSystemIDMatch_navtive(int i) {
        if (!is_emulator()) {
            return TVNative.getCaSystemIDMatch_navtive(i);
        }
        Log.d(TAG, "getCaSystemIDMatch_navtive called...");
        return 0;
    }

    public static String getCamID_navtive(int i) {
        if (!is_emulator()) {
            return TVNative.getCamID_navtive(i);
        }
        Log.d(TAG, "getCamID_navtive called...");
        return "";
    }

    public static String getCamName_navtive(int i) {
        if (!is_emulator()) {
            return TVNative.getCamName_navtive(i);
        }
        Log.d(TAG, "getCamName_navtive called...");
        return "";
    }

    public static int getCamPinCaps_navtive() {
        if (!is_emulator()) {
            return TVNative.getCamPinCaps_navtive();
        }
        Log.d(TAG, "getEnqID_navtive called...");
        return 0;
    }

    public static String getCamPinCode_navtive(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getCamPinCode_navtive called...");
            return null;
        }
        Log.d(TAG, "getCamPinCode_navtive called...");
        return TVNative.getCamPinCode_navtive(i);
    }

    public static int getCamRatingValue_navtive(int i) {
        if (!is_emulator()) {
            return TVNative.getCamRatingValue_navtive(i);
        }
        Log.d(TAG, "getCamRatingValue_navtive called...");
        return 0;
    }

    public static int getCamScanReqTypeACON_navtive(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getCamScanReqTypeACON_navtive called...");
            return 0;
        }
        Log.d(TAG, "getCamScanReqTypeACON_navtive called...");
        return TVNative.getCamScanReqTypeACON_navtive(i);
    }

    public static String getCaption_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.getCaption_native(i, i2);
        }
        Log.d(TAG, "getCaption called...\n");
        return null;
    }

    public static int getCecDevInfo_native(int i, MtkTvCecBase.CecDevInfo cecDevInfo) {
        if (!is_emulator()) {
            return TVNative.getCecDevInfo_native(i, cecDevInfo);
        }
        Log.d(TAG, "getCecDevInfo_native called...\n");
        return 0;
    }

    public static int getCecDevListInfo_native(List<MtkTvCecBase.CecDevInfo> list) {
        if (!is_emulator()) {
            return TVNative.getCecDevListInfo_native(list);
        }
        Log.d(TAG, "getCecDevListInfo_native called...\n");
        return 0;
    }

    public static int getChannelCountByBrdcst_native(int i, int i2, int i3) {
        if (!is_emulator()) {
            return TVNative.getChannelCountByBrdcst_native(i, i2, i3);
        }
        Log.d(TAG, "getChannelCountByMask_native called...\n");
        return 0;
    }

    public static int getChannelCountByFilter_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.getChannelCountByFilter_native(i, i2);
        }
        Log.d(TAG, "getChannelCountByFilter_native called...\n");
        return 0;
    }

    public static int getChannelCountByMask_native(int i, int i2, int i3, int i4) {
        if (!is_emulator()) {
            return TVNative.getChannelCountByMask_native(i, i2, i3, i4);
        }
        Log.d(TAG, "getChannelCountByMask_native called...\n");
        return 0;
    }

    public static int getChannelCountByQueryInfo_native(int i, MtkTvChannelQuery mtkTvChannelQuery) {
        if (!is_emulator()) {
            return TVNative.getChannelCountByQueryInfo_native(i, mtkTvChannelQuery);
        }
        Log.d(TAG, "getChannelCountByQueryInfo_native called...\n");
        return 0;
    }

    public static int getChannelInfoByChannelId_native(int i, MtkTvChannelInfoBase mtkTvChannelInfoBase) {
        if (!is_emulator()) {
            return TVNative.getChannelInfoByChannelId_native(i, mtkTvChannelInfoBase);
        }
        Log.d(TAG, "getChannelInfoByChannelId_native called...\n");
        return 0;
    }

    public static MtkTvChannelInfoBase getChannelInfoBySvlRecId_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.getChannelInfoBySvlRecId_native(i, i2);
        }
        Log.d(TAG, "getChannelInfoBySvlRecId_native called...\n");
        return null;
    }

    public static int getChannelListByBrdcst_native(int i, int i2, int i3, int i4, List<MtkTvChannelInfoBase> list) {
        if (!is_emulator()) {
            return TVNative.getChannelListByBrdcst_native(i, i2, i3, i4, list);
        }
        Log.d(TAG, "getChannelCountByMask_native called...\n");
        return 0;
    }

    public static int getChannelListByFavoriteIdx_native(int i, int i2, int i3, boolean z, int i4, int i5, int i6, List<MtkTvChannelInfoBase> list) {
        if (!is_emulator()) {
            return TVNative.getChannelListByFavoriteIdx_native(i, i2, i3, z, i4, i5, i6, list);
        }
        Log.d(TAG, "getChannelListByFilter_native called...\n");
        return 0;
    }

    public static int getChannelListByFilter_native(int i, int i2, int i3, int i4, int i5, List<MtkTvChannelInfoBase> list) {
        if (!is_emulator()) {
            return TVNative.getChannelListByFilter_native(i, i2, i3, i4, i5, list);
        }
        Log.d(TAG, "getChannelListByFilter_native called...\n");
        return 0;
    }

    public static int getChannelListByMask_native(int i, int i2, int i3, int i4, int i5, int i6, int i7, List<MtkTvChannelInfoBase> list) {
        if (!is_emulator()) {
            return TVNative.getChannelListByMask_native(i, i2, i3, i4, i5, i6, i7, list);
        }
        Log.d(TAG, "getChannelListByMask_native called...\n");
        return 0;
    }

    public static int getChannelListByQueryInfo_native(int i, MtkTvChannelQuery mtkTvChannelQuery, List<MtkTvChannelInfoBase> list, int i2) {
        if (!is_emulator()) {
            return TVNative.getChannelListByQueryInfo_native(i, mtkTvChannelQuery, list, i2);
        }
        Log.d(TAG, "getChannelListByQueryInfo_native called...\n");
        return 0;
    }

    public static int getChannelListMode_native() {
        if (!is_emulator()) {
            return TVNative.getChannelListMode_native();
        }
        Log.d(TAG, "getChannelListMode_native called...\n");
        return 0;
    }

    public static int getChannelList_native(int i, List<MtkTvChannelInfoBase> list) {
        if (!is_emulator()) {
            return TVNative.getChannelList_native(i, list);
        }
        Log.d(TAG, "getChannelList_native called...\n");
        return 0;
    }

    public static String getChannelLogo_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.getChannelLogo_native(i, i2);
        }
        Log.d(TAG, "getChannelLogo called...\n");
        return null;
    }

    public static String getChannelName_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.getChannelName_native(i, i2);
        }
        Log.d(TAG, "getChannelName called...\n");
        return null;
    }

    public static String getChannelNumber_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.getChannelNumber_native(i, i2);
        }
        Log.d(TAG, "getChannelNumber called...\n");
        return null;
    }

    public static int getChannelPumpVer_native(int i) {
        if (!is_emulator()) {
            return TVNative.getChannelPumpVer_native(i);
        }
        a.b("getChannelPumpVer_native called. svlId = ", i, TAG);
        return 0;
    }

    public static int getChannelRFInfo_native(int i, int i2, int i3) {
        if (!is_emulator()) {
            return TVNative.getChannelRFInfo_native(i, i2, i3);
        }
        Log.d(TAG, "queryCurrentChannelRFInfo_native called...\n");
        return 0;
    }

    public static int getChannelType_native(int i) {
        if (!is_emulator()) {
            return TVNative.getChannelType_native(i);
        }
        Log.d(TAG, "getChannelType_native called...\n");
        return 0;
    }

    public static int getChgSource_native() {
        if (!is_emulator()) {
            return TVNative.getChgSource_native();
        }
        Log.d(TAG, "getChgSource_native called");
        return 0;
    }

    public static String getCmdExecResult_native(String str) {
        if (!is_emulator()) {
            return TVNative.getCmdExecResult_native(str);
        }
        Log.d(TAG, "getCmdExecResult_native called...\n");
        return new String();
    }

    public static int[] getConfigIntArrayValue_native(int i, int i2, String str) {
        if (!is_emulator()) {
            return TVNative.getConfigIntArrayValue_native(i, i2, str);
        }
        Log.d(TAG, "getConfigIntArrayValue_native called");
        return null;
    }

    public static String getConfigString_native(String str) {
        if (!is_emulator()) {
            return TVNative.getConfigString_native(str);
        }
        Log.d(TAG, "getConfigString_native called");
        return "";
    }

    public static int getConfigValue_native(int i, String str) {
        if (!is_emulator()) {
            return TVNative.getConfigValue_native(i, str);
        }
        Log.d(TAG, "getConfigValue_native called");
        return 0;
    }

    public static int getConnectAttr_native(String str, int i) {
        if (!is_emulator()) {
            return TVNative.getConnectAttr_native(str, i);
        }
        Log.d(TAG, "getConnectAttr_native called...\n");
        return -1;
    }

    public static int getCrntOverscan_native(MtkTvScreenModeOverscan mtkTvScreenModeOverscan) {
        if (!is_emulator()) {
            return TVNative.getCrntOverscan_native(mtkTvScreenModeOverscan);
        }
        Log.d(TAG, "getCrntOverscan_native called ...\n");
        return 0;
    }

    public static int getCrntRatingInfo_native(MtkTvRatingConvert2Goo mtkTvRatingConvert2Goo) {
        if (!is_emulator()) {
            return TVNative.getCrntRatingInfo_native(mtkTvRatingConvert2Goo);
        }
        Log.d(TAG, "getCrntRatingInfo_native called...\n");
        return 0;
    }

    public static int[] getCurrentActiveWinChannelList_native() {
        if (!is_emulator()) {
            return TVNative.getCurrentActiveWinChannelList_native();
        }
        Log.d(TAG, "getCurrentActiveWinChannelList called...\n");
        return null;
    }

    public static long getCurrentActiveWinEndTime_native() {
        if (!is_emulator()) {
            return TVNative.getCurrentActiveWinEndTime_native();
        }
        Log.d(TAG, "getCurrentActiveWinEndTime called...\n");
        return 0L;
    }

    public static long getCurrentActiveWinStartTime_native() {
        if (!is_emulator()) {
            return TVNative.getCurrentActiveWinStartTime_native();
        }
        Log.d(TAG, "getCurrentActiveWinStartTime called...\n");
        return 0L;
    }

    public static int getCurrentAd_native(TvProviderAudioTrackBase tvProviderAudioTrackBase) {
        if (!is_emulator()) {
            return TVNative.getCurrentAd_native(tvProviderAudioTrackBase);
        }
        Log.d(TAG, "getCurrentAudio_native...\n");
        return 0;
    }

    public static int getCurrentAudio_native(TvProviderAudioTrackBase tvProviderAudioTrackBase) {
        if (!is_emulator()) {
            return TVNative.getCurrentAudio_native(tvProviderAudioTrackBase);
        }
        Log.d(TAG, "getCurrentAudio_native...\n");
        return 0;
    }

    public static MtkTvChannelInfoBase getCurrentChannelFromNav_native(int i) {
        if (!is_emulator()) {
            return TVNative.getCurrentChannelFromNav_native(i);
        }
        Log.d(TAG, "getCurrentChannelFromNav_native called...\n");
        return null;
    }

    public static int getCurrentChannelId_native() {
        if (!is_emulator()) {
            return TVNative.getCurrentChannelId_native();
        }
        Log.d(TAG, "getCurrentChannelId_native called...\n");
        return 0;
    }

    public static int getCurrentChannel_native(MtkTvChannelInfoBase mtkTvChannelInfoBase) {
        if (!is_emulator()) {
            return TVNative.getCurrentChannel_native(mtkTvChannelInfoBase);
        }
        Log.d(TAG, "getCurrentChannel_native called...\n");
        return 0;
    }

    public static String getCurrentInputSourceName_native() {
        if (!is_emulator()) {
            return TVNative.getCurrentInputSourceName_native();
        }
        Log.d(TAG, "getCurrentInputSourceName_native called");
        return null;
    }

    public static long getCurrentPosition_native() {
        if (!is_emulator()) {
            return TVNative.getCurrentPosition_native();
        }
        Log.d(TAG, "getCurrentPosition_native called...\n");
        return 0L;
    }

    public static int getCurrentTeletextPage_native(MtkTvTeletextPageBase mtkTvTeletextPageBase) {
        if (!is_emulator()) {
            return TVNative.getCurrentTeletextPage_native(mtkTvTeletextPageBase);
        }
        Log.d(TAG, "getCurrentTeletextPage_native called ...\n");
        return 0;
    }

    public static int getCurrentVideoStreamInfo_native(MtkTvVideoStreamInfoBase mtkTvVideoStreamInfoBase) {
        if (!is_emulator()) {
            return TVNative.getCurrentVideoStreamInfo_native(mtkTvVideoStreamInfoBase);
        }
        Log.d(TAG, "getCurrentVideoStreamInfo_native...\n");
        return 0;
    }

    public static int getDVBAgeRatingSetting_native() {
        if (!is_emulator()) {
            return TVNative.getDVBAgeRatingSetting_native();
        }
        Log.d(TAG, "getDVBAgeRatingSetting_native called...\n");
        return 0;
    }

    public static int getDataInfo_native(MtkTvOADBase mtkTvOADBase, int i) {
        if (!is_emulator()) {
            return TVNative.getDataInfo_native(mtkTvOADBase, i);
        }
        Log.d(TAG, "getDataInfo_native called...");
        return 0;
    }

    public static int getDigitalFavoritesList_native(int i, int i2, int i3, List<MtkTvChannelInfoBase> list) {
        if (!is_emulator()) {
            return TVNative.getDigitalFavoritesList_native(i, i2, i3, list);
        }
        Log.d(TAG, "getDigitalFavoritesList_native called...\n");
        return 0;
    }

    public static int getDispRegionCapability_native(String str, MtkTvRegionCapability mtkTvRegionCapability) {
        if (!is_emulator()) {
            return TVNative.getDispRegionCapability_native(str, mtkTvRegionCapability);
        }
        Log.d(TAG, "getDispRegionCapability_native");
        return 0;
    }

    public static byte[] getDrmpDeviceID_native() {
        if (!is_emulator()) {
            return TVNative.getDrmpDeviceID_native();
        }
        Log.d(TAG, "getDrmpDeviceID_native called...\n");
        return null;
    }

    public static boolean getEASAndroidLaunchStatus_native() {
        if (!is_emulator()) {
            return TVNative.getEASAndroidLaunchStatus_native();
        }
        Log.d(TAG, "getEASAndroidLaunchStatus_native called ...\n");
        return false;
    }

    public static int getEASCurrentStatus_native(MtkTvEASParaBase mtkTvEASParaBase) {
        if (!is_emulator()) {
            return TVNative.getEASCurrentStatus_native(mtkTvEASParaBase);
        }
        Log.d(TAG, "getEASCurrentStatus called...\n");
        return 0;
    }

    public static boolean getEarcCaps_native() {
        if (!is_emulator()) {
            return TVNative.getEarcCaps_native();
        }
        Log.d(TAG, "getEarcCaps_native called...\n");
        return false;
    }

    public static boolean getEarcStatus_native(int i) {
        if (!is_emulator()) {
            return TVNative.getEarcStatus_native(i);
        }
        Log.d(TAG, "getEarcStatus_native called...\n");
        return false;
    }

    public static int getEnqID_navtive() {
        if (!is_emulator()) {
            return TVNative.getEnqID_navtive();
        }
        Log.d(TAG, "getEnqID_navtive called...");
        return 0;
    }

    public static boolean getEthernetWolCtl_native() {
        if (!is_emulator()) {
            return TVNative.getEthernetWolCtl_native();
        }
        Log.d(TAG, "getWifiWolCtl_native called...\n");
        return true;
    }

    public static void getEventByIndex_native(int i, int i2, MtkTvEventInfoBase mtkTvEventInfoBase) {
        if (is_emulator()) {
            Log.d(TAG, "getEventByIndex_native called ...\n");
        } else {
            TVNative.getEventByIndex_native(i, i2, mtkTvEventInfoBase);
        }
    }

    public static String getEventDetailByIndex_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.getEventDetailByIndex_native(i, i2);
        }
        Log.d(TAG, "getEventDetailByIndex_native called ...\n");
        return null;
    }

    public static void getEventInfoByEventId_native(int i, int i2, MtkTvEventInfoBase mtkTvEventInfoBase) {
        if (is_emulator()) {
            Log.d(TAG, "getEventInfoByEventId_native called...\n");
        } else {
            TVNative.getEventInfoByEventId_native(i, i2, mtkTvEventInfoBase);
        }
    }

    public static int getEventListByChannelId_native(int i, long j, long j2, int i2, List<MtkTvEventInfoBase> list) {
        if (!is_emulator()) {
            return TVNative.getEventListByChannelId_native(i, j, j2, i2, list);
        }
        Log.d(TAG, "getEventListByChannelId_native called...\n");
        return 0;
    }

    public static int getEventNumberInEIT_native(int i) {
        if (!is_emulator()) {
            return TVNative.getEventNumberInEIT_native(i);
        }
        Log.d(TAG, "getEventNumberInEIT_native called ...\n");
        return 0;
    }

    public static int getEventRatingMapById_native(int i, int i2, MtkTvRatingConvert2Goo mtkTvRatingConvert2Goo) {
        if (!is_emulator()) {
            return TVNative.getEventRatingMapById_native(i, i2, mtkTvRatingConvert2Goo);
        }
        Log.d(TAG, "getEventRatingMapById_native called...\n");
        return 0;
    }

    public static int getEventRatingMapByIndex_native(int i, int i2, MtkTvRatingConvert2Goo mtkTvRatingConvert2Goo) {
        if (!is_emulator()) {
            return TVNative.getEventRatingMapByIndex_native(i, i2, mtkTvRatingConvert2Goo);
        }
        Log.d(TAG, "getEventRatingMapByIndex_native called ...\n");
        return 0;
    }

    public static void getEvent_native(int i, MtkTvEventInfoBase mtkTvEventInfoBase) {
        if (is_emulator()) {
            Log.d(TAG, "getEvent_native called ...\n");
        } else {
            TVNative.getEvent_native(i, mtkTvEventInfoBase);
        }
    }

    public static boolean getExternalDeviceHasSignal_native(MtkTvInputSourceBase.InputDeviceType inputDeviceType, int i) {
        if (!is_emulator()) {
            return TVNative.getExternalDeviceHasSignal_native(inputDeviceType, i);
        }
        Log.d(TAG, "getExternalDeviceHasSignal_native called");
        return false;
    }

    public static int getFavoriteIdx_native(int i, int i2, int i3, int i4) {
        if (!is_emulator()) {
            return TVNative.getFavoriteIdx_native(i, i2, i3, i4);
        }
        Log.d(TAG, "getChannelListByFilter_native called...\n");
        return 0;
    }

    public static int getFavoritelistByFilter_native(List<MtkTvFavoritelistInfoBase> list) {
        if (!is_emulator()) {
            return TVNative.getFavoritelistByFilter_native(list);
        }
        Log.d(TAG, "getFavoritelistByFilter_native called...\n");
        return 0;
    }

    public static String getFileBasePath_native() {
        if (!is_emulator()) {
            return TVNative.getFileBasePath_native();
        }
        Log.d(TAG, "getFileBasePath_native called...\n");
        return new String();
    }

    public static int getFirmwareInfo_native(MtkTvUpgradeFirmwareInfoBase mtkTvUpgradeFirmwareInfoBase) {
        if (!is_emulator()) {
            return TVNative.getFirmwareInfo_native(mtkTvUpgradeFirmwareInfoBase);
        }
        Log.d(TAG, "triggerUpgrade_native called ...");
        return 0;
    }

    public static boolean getGingaScreenModeisEnable_native() {
        if (!is_emulator()) {
            return TVNative.getGingaScreenModeisEnable_native();
        }
        Log.d(TAG, "getGingaScreenModeisEnable_native called ...\n");
        return false;
    }

    public static int getHostQuietTuneStatus_navtive(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getHostQuietTuneStatus_navtive called...");
            return 0;
        }
        Log.d(TAG, "getHostQuietTuneStatus_navtive called...");
        return TVNative.getHostQuietTuneStatus_navtive(i);
    }

    public static int getHostTuneBrdcstStatus_navtive(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getHostTuneBrdcstStatus_navtive called...");
            return 0;
        }
        Log.d(TAG, "getHostTuneBrdcstStatus_navtive called...");
        return TVNative.getHostTuneBrdcstStatus_navtive(i);
    }

    public static int getHostTuneStatus_navtive(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getHostTuneStatus_navtive called...");
            return 0;
        }
        Log.d(TAG, "getHostTuneStatus_navtive called...");
        return TVNative.getHostTuneStatus_navtive(i);
    }

    public static int getISDBAgeRatingSetting_native() {
        if (!is_emulator()) {
            return TVNative.getISDBAgeRatingSetting_native();
        }
        Log.d(TAG, "getISDBAgeRatingSetting_native called...\n");
        return 0;
    }

    public static int getISDBContentRatingSetting_native() {
        if (!is_emulator()) {
            return TVNative.getISDBContentRatingSetting_native();
        }
        Log.d(TAG, "getISDBContentRatingSetting_native called...\n");
        return 0;
    }

    public static void getInfo_native(byte b2, int i, int[] iArr) {
        if (is_emulator()) {
            Log.d(TAG, "getInfo_native called...\n");
        } else {
            TVNative.getInfo_native(b2, i, iArr);
        }
    }

    public static int getInputLabelIdx_native(int i) {
        if (!is_emulator()) {
            return TVNative.getInputLabelIdx_native(i);
        }
        Log.d(TAG, "getInputLabelIdx_native called");
        return 0;
    }

    public static String getInputLabelUserDefName_native(int i) {
        if (!is_emulator()) {
            return TVNative.getInputLabelUserDefName_native(i);
        }
        Log.d(TAG, "getInputLabelUserDefName_native called");
        return null;
    }

    public static void getInputSourceAudioAvailableRecord_native(List<TvProviderAudioTrackBase> list) {
        if (is_emulator()) {
            Log.d(TAG, "getInputSourceAudioAvailableRecord_native...\n");
        } else {
            TVNative.getInputSourceAudioAvailableRecord_native(list);
        }
    }

    public static int getInputSourceCurrentAudio_native(TvProviderAudioTrackBase tvProviderAudioTrackBase) {
        if (!is_emulator()) {
            return TVNative.getInputSourceCurrentAudio_native(tvProviderAudioTrackBase);
        }
        Log.d(TAG, "getHDMICurrentAudio_native...\n");
        return 0;
    }

    public static String getInputSourceNamebySourceid_native(int i) {
        if (!is_emulator()) {
            return TVNative.getInputSourceNamebySourceid_native(i);
        }
        Log.d(TAG, "getInputSourceNamebySourceid_native called");
        return null;
    }

    public static int getInputSourceRecbyidx_native(int i, MtkTvInputSourceBase.InputSourceRecord inputSourceRecord) {
        if (!is_emulator()) {
            return TVNative.getInputSourceRecbyidx_native(i, inputSourceRecord);
        }
        Log.d(TAG, "getInputSourceRecbyidx_native called");
        return 0;
    }

    public static int getInputSourceTotalNumber_native() {
        if (!is_emulator()) {
            return TVNative.getInputSourceTotalNumber_native();
        }
        Log.d(TAG, "getInputSourceTotalNumber_native called");
        return 0;
    }

    public static MtkTvParserIniInfoBase getIntConfigData_native(String str, String str2) {
        if (!is_emulator()) {
            return TVNative.getIntConfigData_native(str, str2);
        }
        Log.d(TAG, "getIntConfigData_native called...\n");
        return null;
    }

    public static void getInternalScrnMode_native(MtkTvMHEG5Base mtkTvMHEG5Base) {
        if (is_emulator()) {
            Log.d(TAG, "getInternalScrnMode called...\n");
        } else {
            TVNative.getInternalScrnMode_native(mtkTvMHEG5Base);
        }
    }

    public static String getIptChannelNumber_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.getIptChannelNumber_native(i, i2);
        }
        Log.d(TAG, "getIptChannelNumber called...\n");
        return null;
    }

    public static String getIptsCC_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.getIptsCC_native(i, i2);
        }
        Log.d(TAG, "getIptsCC called...\n");
        return null;
    }

    public static String getIptsName_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.getIptsName_native(i, i2);
        }
        Log.d(TAG, "getIptsName called...\n");
        return null;
    }

    public static String getIptsRating_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.getIptsRating_native(i, i2);
        }
        Log.d(TAG, "getIptsRating called...\n");
        return null;
    }

    public static String getIptsRslt_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.getIptsRslt_native(i, i2);
        }
        Log.d(TAG, "getIptsRslt called...\n");
        return null;
    }

    public static String getMacAddress_native() {
        if (!is_emulator()) {
            return TVNative.getMacAddress_native();
        }
        Log.d(TAG, "getMacAddress_native called");
        return "";
    }

    public static int getMarketRegion() {
        if (!is_emulator()) {
            return TVNative.getMarketRegion();
        }
        Log.d(TAG, "getMarketRegion called...\n");
        return 0;
    }

    public static int getMenuListID_navtive() {
        if (!is_emulator()) {
            return TVNative.getMenuListID_navtive();
        }
        Log.d(TAG, "getMenuListID_navtive called...");
        return 0;
    }

    public static int getMhlPortNum_native() {
        if (!is_emulator()) {
            return TVNative.getMhlPortNum_native();
        }
        Log.d(TAG, "getMhlPortNum_native called");
        return 0;
    }

    public static int getMinMaxConfigValue_native(int i, String str) {
        if (!is_emulator()) {
            return TVNative.getMinMaxConfigValue_native(i, str);
        }
        Log.d(TAG, "getMinMaxConfigValue_native called");
        return 0;
    }

    public static String getMsg_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.getMsg_native(i, i2);
        }
        Log.d(TAG, "getMsg called...\n");
        return null;
    }

    public static boolean getMute_native() {
        if (!is_emulator()) {
            return TVNative.getMute_native();
        }
        Log.d(TAG, "setMute_native called");
        return false;
    }

    public static String getNextProgCategoryIdx_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.getNextProgCategoryIdx_native(i, i2);
        }
        Log.d(TAG, "getNextProgCategoryIdx called...\n");
        return null;
    }

    public static String getNextProgDetail_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.getNextProgDetail_native(i, i2);
        }
        Log.d(TAG, "getNextProgDetail called...\n");
        return null;
    }

    public static String getNextProgSecondaryTitle_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.getNextProgSecondaryTitle_native(i, i2);
        }
        Log.d(TAG, "getNextProgSecondaryTitle called...\n");
        return null;
    }

    public static String getNextProgTime_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.getNextProgTime_native(i, i2);
        }
        Log.d(TAG, "getNextProgTime called...\n");
        return null;
    }

    public static String getNextProgTitle_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.getNextProgTitle_native(i, i2);
        }
        Log.d(TAG, "getNextProgTitle called...\n");
        return null;
    }

    public static String getNextRating_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.getNextRating_native(i, i2);
        }
        Log.d(TAG, "getNextRating called...\n");
        return null;
    }

    public static int getOpenVCHIPInfo_native(MtkTvOpenVCHIPParaBase mtkTvOpenVCHIPParaBase, MtkTvOpenVCHIPInfoBase mtkTvOpenVCHIPInfoBase) {
        if (!is_emulator()) {
            return TVNative.getOpenVCHIPInfo_native(mtkTvOpenVCHIPParaBase, mtkTvOpenVCHIPInfoBase);
        }
        Log.d(TAG, "getOpenVCHIPInfo_native called...\n");
        return 0;
    }

    public static int getOpenVCHIPSettingInfo_native(MtkTvOpenVCHIPSettingInfoBase mtkTvOpenVCHIPSettingInfoBase) {
        if (!is_emulator()) {
            return TVNative.getOpenVCHIPSettingInfo_native(mtkTvOpenVCHIPSettingInfoBase);
        }
        Log.d(TAG, "getOpenVCHIPSettingInfo_native called...\n");
        return 0;
    }

    public static int getPFEventInfoByChannel_native(int i, boolean z, MtkTvEventInfoBase mtkTvEventInfoBase) {
        if (!is_emulator()) {
            return TVNative.getPFEventInfoByChannel_native(i, z, mtkTvEventInfoBase);
        }
        Log.d(TAG, "getPFEventInfoByChannel called...\n");
        return 0;
    }

    public static int getPOPTunerFocus_native() {
        if (!is_emulator()) {
            return TVNative.getPOPTunerFocus_native();
        }
        Log.d(TAG, "getPOPTunerFocus_native called");
        return 0;
    }

    public static int getPclWakeupSetup_native() {
        if (!is_emulator()) {
            return TVNative.getPclWakeupSetup_native();
        }
        Log.d(TAG, "getPclWakeupSetup_native called");
        return 0;
    }

    public static void getPfgInfo_native(MtkTvMHEG5Base mtkTvMHEG5Base) {
        if (is_emulator()) {
            Log.d(TAG, "getPfgString called...\n");
        } else {
            TVNative.getPfgInfo_native(mtkTvMHEG5Base);
        }
    }

    public static int getPictureMode_native() {
        if (!is_emulator()) {
            return TVNative.getPictureMode_native();
        }
        Log.d(TAG, "getPictureMode_native called ...\n");
        return 0;
    }

    public static int getPowerOnStatus_native() {
        if (!is_emulator()) {
            return TVNative.getPowerOnStatus_native();
        }
        Log.d(TAG, "getPowerOnStatus_native called...");
        return 0;
    }

    public static String getProfileISO639LangCode_navtive(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getProfileISO639LangCode_navtive called...");
            return "";
        }
        Log.d(TAG, "getProfileISO639LangCode_navtive called...");
        return TVNative.getProfileISO639LangCode_navtive(i);
    }

    public static String getProfileName_navtive(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getProfileName_navtive called...");
            return null;
        }
        Log.d(TAG, "getProfileName_navtive called...");
        return TVNative.getProfileName_navtive(i);
    }

    public static int getProfileResourceIsOpen_navtive(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getProfileResourceIsOpen_navtive called...");
            return 0;
        }
        Log.d(TAG, "getProfileResourceIsOpen_navtive called...");
        return TVNative.getProfileResourceIsOpen_navtive(i);
    }

    public static int getProfileSupport_navtive(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "getProfileSupport_navtive called...");
            return 0;
        }
        Log.d(TAG, "getProfileSupport_navtive called...");
        return TVNative.getProfileSupport_navtive(i, i2);
    }

    public static int getProfileValid_navtive(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getProfileValid_navtive called...");
            return 0;
        }
        Log.d(TAG, "getProfileValid_navtive called...");
        return TVNative.getProfileValid_navtive(i);
    }

    public static String getProgCategoryIdx_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.getProgCategoryIdx_native(i, i2);
        }
        Log.d(TAG, "getProgCategoryIdx called...\n");
        return null;
    }

    public static String getProgCategory_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.getProgCategory_native(i, i2);
        }
        Log.d(TAG, "getProgCategory called...\n");
        return null;
    }

    public static String getProgDetailPageIdx_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.getProgDetailPageIdx_native(i, i2);
        }
        Log.d(TAG, "getProgDetailPageIdx called...\n");
        return null;
    }

    public static String getProgDetail_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.getProgDetail_native(i, i2);
        }
        Log.d(TAG, "getProgDetail called...\n");
        return null;
    }

    public static String getProgSecondaryTitle_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.getProgSecondaryTitle_native(i, i2);
        }
        Log.d(TAG, "getProgSecondaryTitle called...\n");
        return null;
    }

    public static String getProgTime_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.getProgTime_native(i, i2);
        }
        Log.d(TAG, "getProgTime called...\n");
        return null;
    }

    public static String getProgTitle_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.getProgTitle_native(i, i2);
        }
        Log.d(TAG, "getProgTitle called...\n");
        return null;
    }

    public static long getPvrBrowserItemChannelId_native(int i) {
        if (!is_emulator()) {
            return TVNative.getPvrBrowserItemChannelId_native(i);
        }
        Log.d(TAG, "getPvrBrowserItemChannelId_native called...\n");
        return 0L;
    }

    public static int getPvrBrowserItemChannelLock_native(int i) {
        if (!is_emulator()) {
            return TVNative.getPvrBrowserItemChannelLock_native(i);
        }
        Log.d(TAG, "getPvrBrowserItemChannelLock called...\n");
        return 0;
    }

    public static String getPvrBrowserItemChannelName_native(String str) {
        if (!is_emulator()) {
            return TVNative.getPvrBrowserItemChannelName_native(str);
        }
        Log.d(TAG, "getPvrBrowserItemChannelName_native called...\n");
        return "";
    }

    public static int getPvrBrowserItemCount_native() {
        if (!is_emulator()) {
            return TVNative.getPvrBrowserItemCount_native();
        }
        Log.d(TAG, "getPvrBrowserCount_native called...\n");
        return 0;
    }

    public static String getPvrBrowserItemDate_native(int i) {
        if (!is_emulator()) {
            return TVNative.getPvrBrowserItemDate_native(i);
        }
        Log.d(TAG, "getPvrBrowserItemDate_native called...\n");
        return "";
    }

    public static long getPvrBrowserItemDuration_native(String str) {
        if (!is_emulator()) {
            return TVNative.getPvrBrowserItemDuration_native(str);
        }
        Log.d(TAG, "getPvrBrowserItemDuration_native called...\n");
        return 0L;
    }

    public static long getPvrBrowserItemFirstRatingRange_native(int i) {
        if (!is_emulator()) {
            return TVNative.getPvrBrowserItemFirstRatingRange_native(i);
        }
        Log.d(TAG, "getPvrBrowserItemFirstRatingRange_native called...\n");
        return 0L;
    }

    public static int getPvrBrowserItemInputLock_native(int i) {
        if (!is_emulator()) {
            return TVNative.getPvrBrowserItemInputLock_native(i);
        }
        Log.d(TAG, "getPvrBrowserItemInputLock called...\n");
        return 0;
    }

    public static int getPvrBrowserItemMajorChannelNum_native(int i) {
        if (!is_emulator()) {
            return TVNative.getPvrBrowserItemMajorChannelNum_native(i);
        }
        Log.d(TAG, "getPvrBrowserItemMajorChannelNum_native called...\n");
        return 0;
    }

    public static int getPvrBrowserItemMinorChannelNum_native(int i) {
        if (!is_emulator()) {
            return TVNative.getPvrBrowserItemMinorChannelNum_native(i);
        }
        Log.d(TAG, "getPvrBrowserItemMinorChannelNum_native called...\n");
        return 0;
    }

    public static String getPvrBrowserItemPath_native(int i) {
        if (!is_emulator()) {
            return TVNative.getPvrBrowserItemPath_native(i);
        }
        Log.d(TAG, "getPvrBrowserItemPath_native called...\n");
        return "";
    }

    public static String getPvrBrowserItemProgramName_native(String str) {
        if (!is_emulator()) {
            return TVNative.getPvrBrowserItemProgramName_native(str);
        }
        Log.d(TAG, "getPvrBrowserItemProgramName_native called...\n");
        return "";
    }

    public static long getPvrBrowserItemRentention_native(int i) {
        if (!is_emulator()) {
            return TVNative.getPvrBrowserItemRentention_native(i);
        }
        Log.d(TAG, "getPvrBrowserItemRentention_native called...\n");
        return 0L;
    }

    public static long getPvrBrowserItemStartTime_native(String str) {
        if (!is_emulator()) {
            return TVNative.getPvrBrowserItemStartTime_native(str);
        }
        Log.d(TAG, "getPvrBrowserItemStartTime_native called...\n");
        return 0L;
    }

    public static long getPvrBrowserItemStopReason_native(String str) {
        if (!is_emulator()) {
            return TVNative.getPvrBrowserItemStopReason_native(str);
        }
        Log.d(TAG, "getPvrBrowserItemStopReason_native called...\n");
        return 0L;
    }

    public static String getPvrBrowserItemTime_native(int i) {
        if (!is_emulator()) {
            return TVNative.getPvrBrowserItemTime_native(i);
        }
        Log.d(TAG, "getPvrBrowserItemTime_native called...\n");
        return "";
    }

    public static String getPvrBrowserItemWeek_native(int i) {
        if (!is_emulator()) {
            return TVNative.getPvrBrowserItemWeek_native(i);
        }
        Log.d(TAG, "getPvrBrowserItemWeek_native called...\n");
        return "";
    }

    public static int getPvrBrowserRecordingFileCount_native() {
        if (!is_emulator()) {
            return TVNative.getPvrBrowserRecordingFileCount_native();
        }
        Log.d(TAG, "getPvrBrowserRecordingFileCount_native called...\n");
        return 0;
    }

    public static String getPvrBrowserRecordingFileName_native(int i) {
        if (!is_emulator()) {
            return TVNative.getPvrBrowserRecordingFileName_native(i);
        }
        Log.d(TAG, "getPvrBrowserItemTime_native called...\n");
        return "";
    }

    public static boolean getRatingEnable_native() {
        if (!is_emulator()) {
            return TVNative.getRatingEnable_native();
        }
        Log.d(TAG, "getRatingEnable_native called...\n");
        return false;
    }

    public static String getRating_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.getRating_native(i, i2);
        }
        Log.d(TAG, "getRating called...\n");
        return null;
    }

    public static MtkTvBisskeyInfoBase getRecordByBslRecId_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.getRecordByBslRecId_native(i, i2);
        }
        Log.d(TAG, "getRecordByBslRecId_native called...\n");
        return null;
    }

    public static MtkTvBisskeyInfoBase getRecordByHeader_native(int i, MtkTvBisskeyHeader mtkTvBisskeyHeader, int i2) {
        if (!is_emulator()) {
            return TVNative.getRecordByHeader_native(i, mtkTvBisskeyHeader, i2);
        }
        Log.d(TAG, "getRecordByHeader_native called...\n");
        return null;
    }

    public static MtkTvBisskeyInfoBase getRecordByIndex_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.getRecordByIndex_native(i, i2);
        }
        Log.d(TAG, "getRecordByIndex_native called...\n");
        return null;
    }

    public static String getRecordDisk() {
        if (!is_emulator()) {
            return TVNative.GetRecordDisk_native();
        }
        Log.d(TAG, "getPvrBrowserItemTime_native called...\n");
        return "";
    }

    public static int getRecordModeByHandle_native(int i) {
        if (!is_emulator()) {
            return TVNative.getRecordModeByHandle_native(i);
        }
        Log.d(TAG, "getRecordModeByHandle called...\n");
        return 0;
    }

    public static int getRecordNumByHeader_native(int i, MtkTvBisskeyHeader mtkTvBisskeyHeader) {
        if (!is_emulator()) {
            return TVNative.getRecordNumByHeader_native(i, mtkTvBisskeyHeader);
        }
        Log.d(TAG, "getRecordNumByHeader_native called...\n");
        return 0;
    }

    public static MtkTvTimeshiftBase.TimeshiftRecordStatus getRecordStatus_native() {
        if (!is_emulator()) {
            return TVNative.getRecordStatus_native();
        }
        Log.d(TAG, "getRecordStatus_native called...\n");
        return MtkTvTimeshiftBase.TimeshiftRecordStatus.TIMESHIFT_RECORD_UNKNOWN;
    }

    public static String getRecordingFileByHandle_native(int i) {
        if (!is_emulator()) {
            return TVNative.getRecordingFileByHandle_native(i);
        }
        Log.d(TAG, "getRecordingFile_native called...\n");
        return "";
    }

    public static String getRecordingFile_native() {
        if (!is_emulator()) {
            return TVNative.getRecordingFile_native();
        }
        Log.d(TAG, "getRecordingFile_native called...\n");
        return "";
    }

    public static int getRecordingPosition() {
        if (!is_emulator()) {
            return TVNative.getRecordingPosition();
        }
        Log.d(TAG, "getRecordingPosition called...\n");
        return 0;
    }

    public static int getRecordingPositionByHandle(int i) {
        if (!is_emulator()) {
            return TVNative.getRecordingPositionByHandle(i);
        }
        Log.d(TAG, "getRecordingPosition called...\n");
        return 0;
    }

    public static int getRecordsNumber_native(int i) {
        if (!is_emulator()) {
            return TVNative.getRecordsNumber_native(i);
        }
        Log.d(TAG, "getRecordsNumber_native called...\n");
        return 0;
    }

    public static int getSasCbctState_navtive(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getSasCbctState_navtive called...");
            return 0;
        }
        Log.d(TAG, "getSasCbctState_navtive called...");
        return TVNative.getSasCbctState_navtive(i);
    }

    public static int getSasItvState_navtive(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getSasItvState_navtive called...");
            return 0;
        }
        Log.d(TAG, "getSasItvState_navtive called...");
        return TVNative.getSasItvState_navtive(i);
    }

    public static int getSatlNumRecs_native(int i) {
        if (!is_emulator()) {
            return TVNative.getSatlNumRecs_native(i);
        }
        Log.d(TAG, "getSatlNumRecs_native called...\n");
        return 0;
    }

    public static int getSatlRecordByRecIdx_native(int i, int i2, List<MtkTvDvbsConfigInfoBase> list) {
        if (!is_emulator()) {
            return TVNative.getSatlRecordByRecIdx_native(i, i2, list);
        }
        Log.d(TAG, "getSatlRecordByRecIdx_native called...\n");
        return 0;
    }

    public static int getSatlRecord_native(int i, int i2, List<MtkTvDvbsConfigInfoBase> list) {
        if (!is_emulator()) {
            return TVNative.getSatlRecord_native(i, i2, list);
        }
        Log.d(TAG, "getSatlRecordx_native called...\n");
        return 0;
    }

    public static int getScheduleInfo_native(MtkTvOADBase mtkTvOADBase) {
        if (!is_emulator()) {
            return TVNative.getScheduleInfo_native(mtkTvOADBase);
        }
        Log.d(TAG, "getScheduleInfo_native called...");
        return 0;
    }

    public static int getScreenMode_native() {
        if (!is_emulator()) {
            return TVNative.getScreenMode_native();
        }
        Log.d(TAG, "getScreenMode_native called ...\n");
        return 0;
    }

    public static int getScreenOutputDispRect_native(String str, MtkTvRectangle mtkTvRectangle) {
        if (!is_emulator()) {
            return TVNative.getScreenOutputDispRect_native(str, mtkTvRectangle);
        }
        Log.d(TAG, "getScreenOutputDispRect_native called");
        return 0;
    }

    public static int getScreenSourceRect_native(String str, MtkTvRectangle mtkTvRectangle) {
        if (!is_emulator()) {
            return TVNative.getScreenSourceRect_native(str, mtkTvRectangle);
        }
        Log.d(TAG, "getScreenSourceRect_native called");
        return 0;
    }

    public static int getScrnSvrMsgID_native() {
        if (!is_emulator()) {
            return TVNative.getScrnSvrMsgID_native();
        }
        Log.d(TAG, "getScrnSvrMsgID_native is called ... \n");
        return 0;
    }

    public static int getSelinuxEnvironment_native(String str) {
        if (!is_emulator()) {
            return TVNative.getSelinuxEnvironment_native(str);
        }
        Log.d(TAG, "getSelinuxEnvironment_native called");
        return 0;
    }

    public static String getSerialNumber_native() {
        if (!is_emulator()) {
            return TVNative.getSerialNumber_native();
        }
        Log.d(TAG, "getSerialNumber_native called");
        return "";
    }

    public static int getSignalLevel_native() {
        if (!is_emulator()) {
            return TVNative.getSignalLevel_native();
        }
        Log.d(TAG, "getSignalLevel_native called...\n");
        return 0;
    }

    public static int getSignalQuality_native() {
        if (!is_emulator()) {
            return TVNative.getSignalQuality_native();
        }
        Log.d(TAG, "getSignalQuality_native called...\n");
        return 0;
    }

    public static int getSleepTimerRemainingTime_native() {
        if (!is_emulator()) {
            return TVNative.getSleepTimerRemainingTime_native();
        }
        Log.d(TAG, "getSleepTimerRemainingTime_native called...\n");
        return 0;
    }

    public static int getSleepTimer_native(boolean z) {
        if (!is_emulator()) {
            return TVNative.getSleepTimer_native(z);
        }
        Log.d(TAG, "getSleepTimer_native called...\n");
        return 0;
    }

    public static boolean getSlotActive_navtive(int i) {
        if (!is_emulator()) {
            return TVNative.getSlotActive_navtive(i);
        }
        Log.d(TAG, "getSlotActive_navtive called...");
        return false;
    }

    public static int getSlotNum_navtive() {
        if (!is_emulator()) {
            return TVNative.getSlotNum_navtive();
        }
        Log.d(TAG, "getSlotNum_navtive called...");
        return 0;
    }

    public static int getSoundEffect_native() {
        if (!is_emulator()) {
            return TVNative.getSoundEffect_native();
        }
        Log.d(TAG, "getSoundEffect_native called ...\n");
        return 0;
    }

    public static int getSrcVideoResolution_native(String str, MtkTvSrcVideoResolution mtkTvSrcVideoResolution) {
        if (!is_emulator()) {
            return TVNative.getSrcVideoResolution_native(str, mtkTvSrcVideoResolution);
        }
        Log.d(TAG, "getSrcVideoResolution_native");
        return 0;
    }

    public static long getStartPosition_native() {
        if (!is_emulator()) {
            return TVNative.getStartPosition_native();
        }
        Log.d(TAG, "getStartPosition_native called...\n");
        return 0L;
    }

    public static long getStorageFreeSize_native() {
        if (!is_emulator()) {
            return TVNative.getStorageFreeSize_native();
        }
        Log.d(TAG, "getStorageFreeSize called...\n");
        return 0L;
    }

    public static long getStorageSize_native() {
        if (!is_emulator()) {
            return TVNative.getStorageSize_native();
        }
        Log.d(TAG, "getStorageSize called...\n");
        return 0L;
    }

    public static MtkTvParserIniInfoBase getStringConfigData_native(String str, String str2) {
        if (!is_emulator()) {
            return TVNative.getStringConfigData_native(str, str2);
        }
        Log.d(TAG, "getStringConfigData_native called...\n");
        return null;
    }

    public static String getSysVersion_native(int i, String str) {
        if (!is_emulator()) {
            return TVNative.getSysVersion_native(i, str);
        }
        Log.d(TAG, "getSysVersion_native called");
        return "";
    }

    public static String getTVSrc_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.getTVSrc_native(i, i2);
        }
        Log.d(TAG, "getTVSrc called...\n");
        return null;
    }

    public static int getTeletextTopBlockList_native(List<MtkTvTeletextTopBlockBase> list) {
        if (!is_emulator()) {
            return TVNative.getTeletextTopBlockList_native(list);
        }
        Log.d(TAG, "getTeletextTopBlockList_native called ...\n");
        return 0;
    }

    public static int getTeletextTopGroupList_native(MtkTvTeletextTopBlockBase mtkTvTeletextTopBlockBase, List<MtkTvTeletextTopGroupBase> list) {
        if (!is_emulator()) {
            return TVNative.getTeletextTopGroupList_native(mtkTvTeletextTopBlockBase, list);
        }
        Log.d(TAG, "getTeletextTopGroupList_native called ...\n");
        return 0;
    }

    public static int getTeletextTopPageList_native(MtkTvTeletextTopGroupBase mtkTvTeletextTopGroupBase, List<MtkTvTeletextTopPageBase> list) {
        if (!is_emulator()) {
            return TVNative.getTeletextTopPageList_native(mtkTvTeletextTopGroupBase, list);
        }
        Log.d(TAG, "getTeletextTopPageList_native called ...\n");
        return 0;
    }

    public static long getTimeOffset_native() {
        if (!is_emulator()) {
            return TVNative.getTimeOffset_native();
        }
        Log.d(TAG, "getTimeOffset_native called...\n");
        return 0L;
    }

    public static int getTimeSyncSource_native() {
        if (!is_emulator()) {
            return TVNative.getTimeSyncSource_native();
        }
        Log.d(TAG, "getTimeSyncSource_native called...\n");
        return 0;
    }

    public static long getTimeZone_native() {
        if (!is_emulator()) {
            return TVNative.getTimeZone_native();
        }
        Log.d(TAG, "getTimeZone_native called...\n");
        return 0L;
    }

    public static String getTimer_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.getTimer_native(i, i2);
        }
        Log.d(TAG, "getTimer called...\n");
        return null;
    }

    public static int getTimeshiftDeviceStatus_native() {
        if (!is_emulator()) {
            return TVNative.getTimeshiftDeviceStatus_native();
        }
        Log.d(TAG, "getTimeshiftDeviceStatus_native called...\n");
        return 0;
    }

    public static long getTimeshiftDuration_native() {
        if (!is_emulator()) {
            return TVNative.getTimeshiftDuration_native();
        }
        Log.d(TAG, "getTimeshiftDuration_native called...\n");
        return 0L;
    }

    public static int getTimeshiftErrorID_native() {
        if (!is_emulator()) {
            return TVNative.getTimeshiftErrorID_native();
        }
        Log.d(TAG, "getTimeshiftErrorID_native called...\n");
        return 0;
    }

    public static long getTimeshiftPosition_native() {
        if (!is_emulator()) {
            return TVNative.getTimeshiftPosition_native();
        }
        Log.d(TAG, "getTimeshiftPosition_native called...\n");
        return 0L;
    }

    public static int getTimeshiftRegisterDeviceSchedule_native() {
        if (!is_emulator()) {
            return TVNative.getTimeshiftRegisterDeviceSchedule_native();
        }
        Log.d(TAG, "getRegisterDeviceSchedule_native called...\n");
        return 0;
    }

    public static int getTvproviderBySvlId_native(int i, List<TvProviderChannelInfoBase> list) {
        if (!is_emulator()) {
            return TVNative.getTvproviderBySvlId_native(i, list);
        }
        Log.d(TAG, "getTvproviderBySvlId_native called...\n");
        return 0;
    }

    public static int getTvproviderBySvlRecId_native(int i, int i2, TvProviderChannelInfoBase tvProviderChannelInfoBase) {
        if (!is_emulator()) {
            return TVNative.getTvproviderBySvlRecId_native(i, i2, tvProviderChannelInfoBase);
        }
        Log.d(TAG, "getTvproviderBySvlRecId_native called...\n");
        return 0;
    }

    public static int getTvproviderOcl_native(List<TvProviderChannelInfoBase> list) {
        if (!is_emulator()) {
            return TVNative.getTvproviderOcl_native(list);
        }
        Log.d(TAG, "getTvproviderOcl_native called...\n");
        return 0;
    }

    public static int getUARTSerialOperationMode_native(int i, int[] iArr) {
        if (!is_emulator()) {
            return TVNative.getUARTSerialOperationMode_native(i, iArr);
        }
        Log.d(TAG, "getUARTSerialOperationMode_native called...\n");
        return 0;
    }

    public static int getUARTSerialSetting_native(int i, int[] iArr) {
        if (!is_emulator()) {
            return TVNative.getUARTSerialSetting_native(i, iArr);
        }
        Log.d(TAG, "getUARTSerialSetting_native called...\n");
        return 0;
    }

    public static int getUIMode() {
        if (!is_emulator()) {
            return TVNative.getUIMode();
        }
        Log.d(TAG, "getUIMode called...\n");
        return 0;
    }

    public static int getUSMovieRatingSettingInfo_native() {
        if (!is_emulator()) {
            return TVNative.getUSMovieRatingSettingInfo_native();
        }
        Log.d(TAG, "getUSMovieRatingSettingInfo_native called...\n");
        return 0;
    }

    public static boolean getUSTVGrougByChild_native() {
        if (!is_emulator()) {
            return TVNative.getUSTVGrougByChild_native();
        }
        Log.d(TAG, "isCamInstalled_native called...\n");
        return false;
    }

    public static int getUSTvRatingSettingInfo_native(MtkTvUSTvRatingSettingInfoBase mtkTvUSTvRatingSettingInfoBase) {
        if (!is_emulator()) {
            return TVNative.getUSTvRatingSettingInfo_native(mtkTvUSTvRatingSettingInfoBase);
        }
        Log.d(TAG, "getUSTvRatingSettingInfo_native called...\n");
        return 0;
    }

    public static long getUtcTime_native() {
        if (!is_emulator()) {
            return TVNative.getUtcTime_native();
        }
        Log.d(TAG, "getUtcTime_native called...\n");
        return 0L;
    }

    public static int getVideoInfoData_native(int i, MtkTvVideoInfoBase mtkTvVideoInfoBase) {
        if (!is_emulator()) {
            return TVNative.getVideoInfoData_native(i, mtkTvVideoInfoBase);
        }
        Log.d(TAG, "getVideoBackLight_native...\n");
        return 0;
    }

    public static int getVideoInfoValue_native(int i) {
        if (!is_emulator()) {
            return TVNative.getVideoInfoValue_native(i);
        }
        Log.d(TAG, "getVideoBackLight_native...\n");
        return 0;
    }

    public static String getVideoInfo_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.getVideoInfo_native(i, i2);
        }
        Log.d(TAG, "getVideoInfo called...\n");
        return null;
    }

    public static int getVideoSrcRegionCapability_native(String str, MtkTvRegionCapability mtkTvRegionCapability) {
        if (!is_emulator()) {
            return TVNative.getVideoSrcRegionCapability_native(str, mtkTvRegionCapability);
        }
        Log.d(TAG, "getVideoSrcRegionCapability_native");
        return 0;
    }

    public static String getVirtualChannelInfo_navtive(int i) {
        if (!is_emulator()) {
            return TVNative.getVirtualChannelInfo_navtive(i);
        }
        Log.d(TAG, "getVirtualChannelInfo_navtive called...");
        return null;
    }

    public static int getVolume_native() {
        if (!is_emulator()) {
            return TVNative.getVolume_native();
        }
        Log.d(TAG, "getVolume_native called");
        return 0;
    }

    public static int getWakeUpIrKey_native() {
        if (!is_emulator()) {
            return TVNative.getWakeUpIrKey_native();
        }
        Log.d(TAG, "getWakeUpIrKey_native called");
        return 0;
    }

    public static int getWakeUpReason_native() {
        if (!is_emulator()) {
            return TVNative.getWakeUpReason_native();
        }
        Log.d(TAG, "getWakeUpReason called");
        return 5;
    }

    public static int getWebServiceClient_native(List<MtkTvGingaWebServiceClientInfoBase> list) {
        if (!is_emulator()) {
            return TVNative.getWebServiceClient_native(list);
        }
        Log.d(TAG, "getWebServiceClient_native called ...\n");
        return 0;
    }

    public static boolean getWifiWolCtl_native() {
        if (!is_emulator()) {
            return TVNative.getWifiWolCtl_native();
        }
        Log.d(TAG, "getWifiWolCtl_native called...\n");
        return true;
    }

    public static int hbbtvExchangeData_native(int[] iArr) {
        if (!is_emulator()) {
            return TVNative.hbbtvExchangeData_native(iArr);
        }
        Log.d(TAG, "hbbtvExchangeData_native called...\n");
        return 0;
    }

    public static int hbbtvSetAudioDescription_native(int i) {
        if (!is_emulator()) {
            return TVNative.hbbtvSetAudioDescription_native(i);
        }
        Log.d(TAG, "hbbtvExchangeData_native called...\n");
        return 0;
    }

    public static int hbbtvSetDefaultAudioLang_native(String str) {
        if (!is_emulator()) {
            return TVNative.hbbtvSetDefaultAudioLang_native(str);
        }
        Log.d(TAG, "hbbtvSetDefaultAudioLang_native called...\n");
        return 0;
    }

    public static int hbbtvSetDefaultSubtitleLang_native(String str, int i) {
        if (!is_emulator()) {
            return TVNative.hbbtvSetDefaultSubtitleLang_native(str, i);
        }
        Log.d(TAG, "hbbtvSetDefaultSubtitleLang_native called...\n");
        return 0;
    }

    public static int hbbtvStmGetAudioCount_native() {
        if (!is_emulator()) {
            return TVNative.hbbtvStmGetAudioCount_native();
        }
        Log.d(TAG, "hbbtvStmGetAudioCount_native called...\n");
        return 0;
    }

    public static String hbbtvStmGetAudioLang_native(int i) {
        if (!is_emulator()) {
            return TVNative.hbbtvStmGetAudioLang_native(i);
        }
        Log.d(TAG, "hbbtvStmGetAudioLang_native called...\n");
        return "";
    }

    public static int hbbtvStmGetAudio_native() {
        if (!is_emulator()) {
            return TVNative.hbbtvStmGetAudio_native();
        }
        Log.d(TAG, "hbbtvStmGetAudio_native called...\n");
        return 0;
    }

    public static int hbbtvStmSetAudioIndex_native(int i) {
        if (!is_emulator()) {
            return TVNative.hbbtvStmSetAudioIndex_native(i);
        }
        Log.d(TAG, "hbbtvStmSetAudioIndex_native called...\n");
        return 0;
    }

    public static int incrVolume_native() {
        if (!is_emulator()) {
            return TVNative.incrVolume_native();
        }
        Log.d(TAG, "incrVolume_native called");
        return 0;
    }

    public static int inputSyncStop_native(String str, boolean z) {
        if (!is_emulator()) {
            return TVNative.inputSyncStop_native(str, z);
        }
        Log.d(TAG, "inputSyncStop_native called...\n");
        return 0;
    }

    public static int installQDAKey_native(String str) {
        if (!is_emulator()) {
            return TVNative.installQDAKey_native(str);
        }
        Log.d(TAG, "installQDAKey_native called...\n");
        return -1;
    }

    public static boolean isAutoDetectPlugStatus_native(int i) {
        if (!is_emulator()) {
            return TVNative.isAutoDetectPlugStatus_native(i);
        }
        Log.d(TAG, "isAutoDetectPlugStatus_native called");
        return false;
    }

    public static boolean isBGMapplicable_native(boolean z) {
        if (!is_emulator()) {
            return TVNative.isBGMapplicable_native(z);
        }
        Log.d(TAG, "isBGMapplicable_native called");
        return false;
    }

    public static boolean isBlockEx_native(int i) {
        if (!is_emulator()) {
            return TVNative.isBlockEx_native(i);
        }
        Log.d(TAG, "isBlockEx_native called");
        return false;
    }

    public static boolean isBlock_native(int i) {
        if (!is_emulator()) {
            return TVNative.isBlock_native(i);
        }
        Log.d(TAG, "isBlock_native called");
        return false;
    }

    public static boolean isCamInstalled_native() {
        if (!is_emulator()) {
            return TVNative.isCamInstalled_native();
        }
        Log.d(TAG, "isCamInstalled_native called...\n");
        return false;
    }

    public static boolean isCaptureLogo_native() {
        if (is_emulator()) {
            Log.d(TAG, "isCaptureLogo Emulator called");
            return false;
        }
        Log.d(TAG, "isCaptureLogo  called");
        return TVNative.isCaptureLogo_native();
    }

    public static boolean isChannelNumberExsit_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.isChannelNumberExsit_native(i, i2);
        }
        Log.d(TAG, "isChannelNumberExsit_native called...\n");
        return true;
    }

    public static int isConfigEnabled_native(int i, String str) {
        if (!is_emulator()) {
            return TVNative.isConfigEnabled_native(i, str);
        }
        Log.d(TAG, "isConfigEnabled_native called");
        return 0;
    }

    public static int isConfigItemsEnabled_native(int i, String str, MtkTvConfigBase mtkTvConfigBase) {
        if (!is_emulator()) {
            return TVNative.isConfigItemsEnabled_native(i, str, mtkTvConfigBase);
        }
        Log.d(TAG, "isConfigItemsEnabled_native called");
        return 0;
    }

    public static int isConfigVisible_native(int i, String str) {
        if (!is_emulator()) {
            return TVNative.isConfigVisible_native(i, str);
        }
        Log.d(TAG, "isConfigVisible_native called");
        return 0;
    }

    public static int isDeviceExist_native(int i) {
        if (!is_emulator()) {
            return TVNative.isDeviceExist_native(i);
        }
        Log.d(TAG, "isDeviceExist_native called...\n");
        return 0;
    }

    public static boolean isDigitKeysHandled_native() {
        if (!is_emulator()) {
            return TVNative.isDigitKeysHandled_native();
        }
        Log.d(TAG, "isDigitKeysHandled_native called...\n");
        return true;
    }

    public static boolean isDisplayADAtmos_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.isDisplayADAtmos_native(i, i2);
        }
        Log.d(TAG, "isDisplayADAtmos called...\n");
        return true;
    }

    public static boolean isDisplayADEarIcon_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.isDisplayADEarIcon_native(i, i2);
        }
        Log.d(TAG, "isDisplayADEarIcon called...\n");
        return true;
    }

    public static boolean isDisplayADEyeIcon_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.isDisplayADEyeIcon_native(i, i2);
        }
        Log.d(TAG, "isDisplayADEyeIcon called...\n");
        return true;
    }

    public static boolean isDisplayBanner_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.isDisplayBanner_native(i, i2);
        }
        Log.d(TAG, "isDisplayBanner called...\n");
        return true;
    }

    public static boolean isDisplayCaptionIcon_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.isDisplayCaptionIcon_native(i, i2);
        }
        Log.d(TAG, "isDisplayCaptionIcon called...\n");
        return true;
    }

    public static boolean isDisplayFavIcon_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.isDisplayFavIcon_native(i, i2);
        }
        Log.d(TAG, "isDisplayFavIcon called...\n");
        return true;
    }

    public static boolean isDisplayFrmCH_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.isDisplayFrmCH_native(i, i2);
        }
        Log.d(TAG, "isDisplayFrmCH called...\n");
        return true;
    }

    public static boolean isDisplayFrmDetail_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.isDisplayFrmDetail_native(i, i2);
        }
        Log.d(TAG, "isDisplayFrmDetail called...\n");
        return true;
    }

    public static boolean isDisplayFrmInfo_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.isDisplayFrmInfo_native(i, i2);
        }
        Log.d(TAG, "isDisplayFrmInfo called...\n");
        return true;
    }

    public static boolean isDisplayGingaIcon_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.isDisplayGingaIcon_native(i, i2);
        }
        Log.d(TAG, "isDisplayGingaIcon called...\n");
        return true;
    }

    public static boolean isDisplayIptsLockIcon_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.isDisplayIptsLockIcon_native(i, i2);
        }
        Log.d(TAG, "isDisplayIptsLockIcon called...\n");
        return true;
    }

    public static boolean isDisplayLogoIcon_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.isDisplayLogoIcon_native(i, i2);
        }
        Log.d(TAG, "isDisplayLogoIcon called...\n");
        return true;
    }

    public static boolean isDisplayProgDetailDownIcon_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.isDisplayProgDetailDownIcon_native(i, i2);
        }
        Log.d(TAG, "isDisplayProgDetailDownIcon called...\n");
        return true;
    }

    public static boolean isDisplayProgDetailUpIcon_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.isDisplayProgDetailUpIcon_native(i, i2);
        }
        Log.d(TAG, "isDisplayProgDetailUpIcon called...\n");
        return true;
    }

    public static boolean isDisplayTVLockIcon_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.isDisplayTVLockIcon_native(i, i2);
        }
        Log.d(TAG, "isDisplayTVLockIcon called...\n");
        return true;
    }

    public static boolean isDisplayTtxIcon_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.isDisplayTtxIcon_native(i, i2);
        }
        Log.d(TAG, "isDisplayTtxIcon called...\n");
        return true;
    }

    public static boolean isFreeze_native(String str) {
        if (!is_emulator()) {
            return TVNative.isFreeze_native(str);
        }
        Log.d(TAG, "isFreeze_native called ...\n");
        return false;
    }

    public static boolean isMultiviewInputsourceAvailable_native(int i, String str) {
        if (!is_emulator()) {
            return TVNative.isMultiviewInputsourceAvailable_native(i, str);
        }
        Log.d(TAG, "isMultiviewInputsourceAvailable_native called");
        return false;
    }

    public static boolean isOpenVCHIPInfoAvailable_native() {
        if (!is_emulator()) {
            return TVNative.isOpenVCHIPInfoAvailable_native();
        }
        Log.d(TAG, "isOpenVCHIPInfoAvailable_native called...\n");
        return false;
    }

    public static boolean isOrigScrambleStrm_native(String str, int i) {
        if (!is_emulator()) {
            return TVNative.isOrigScrambleStrm_native(str, i);
        }
        Log.d(TAG, "isOrigScrambleStrm_native called...\n");
        return false;
    }

    public static boolean isReceivedTOT_native() {
        if (!is_emulator()) {
            return TVNative.isReceivedTOT_native();
        }
        Log.d(TAG, "isReceivedTOT_native called...\n");
        return false;
    }

    public static boolean isScaning_native() {
        if (!is_emulator()) {
            return TVNative.isScaning_native();
        }
        Log.d(TAG, "isScaning_native called...\n");
        return false;
    }

    public static boolean isSignalLoss_native() {
        if (!is_emulator()) {
            return TVNative.isSignalLoss_native();
        }
        Log.d(TAG, "isSignalLoss_native called...\n");
        return false;
    }

    public static boolean isSupport4DigitsChannelNo_native() {
        if (!is_emulator()) {
            return TVNative.isSupport4DigitsChannelNo_native();
        }
        Log.d(TAG, "isSupport4DigitsChannelNo_native called...\n");
        return false;
    }

    public static boolean isTimeEditable_native() {
        if (!is_emulator()) {
            return TVNative.isTimeEditable_native();
        }
        Log.d(TAG, "isTimeEditable_native called...\n");
        return true;
    }

    public static boolean isTimeOffsetEditable_native() {
        if (!is_emulator()) {
            return TVNative.isTimeOffsetEditable_native();
        }
        Log.d(TAG, "isTimeOffsetEditable_native called...\n");
        return true;
    }

    public static boolean isTimeZoneEditable_native() {
        if (!is_emulator()) {
            return TVNative.isTimeZoneEditable_native();
        }
        Log.d(TAG, "isTimeZoneEditable_native called...\n");
        return true;
    }

    public static boolean isTvRunning_native(String str) {
        if (!is_emulator()) {
            return TVNative.isTvRunning_native(str);
        }
        Log.d(TAG, "setAudioFocus_native called");
        return false;
    }

    public static boolean isZoomEnable_native() {
        if (!is_emulator()) {
            return TVNative.isZoomEnable_native();
        }
        Log.d(TAG, "isZoomEnable_native called ...\n");
        return false;
    }

    public static boolean is_emulator() {
        return !b.b.a.c.a.b().orElse(false).booleanValue();
    }

    public static String isdbCDTGetChannelLogo_native(int i, int i2, int i3) {
        if (!is_emulator()) {
            return TVNative.isdbCDTGetChannelLogo_native(i, i2, i3);
        }
        Log.d(TAG, "isdbCDTGetChannelLogo_native called ...\n");
        return null;
    }

    public static int isfDataExport_native(String str, String str2) {
        if (!is_emulator()) {
            return TVNative.isfDataExport_native(str, str2);
        }
        Log.d(TAG, "isfDataOperation_native called...\n");
        return -1;
    }

    public static int isfDataImport_native(String str) {
        if (!is_emulator()) {
            return TVNative.isfDataImport_native(str);
        }
        Log.d(TAG, "isfDataOperation_native called...\n");
        return -1;
    }

    public static String isfDataSearch_native(String str) {
        if (!is_emulator()) {
            return TVNative.isfDataSearch_native(str);
        }
        Log.d(TAG, "isfDataOperation_native called...\n");
        return "";
    }

    public static int launchHbbtv_native(String str, String str2, String str3) {
        if (!is_emulator()) {
            return TVNative.launchHbbtv_native(str, str2, str3);
        }
        Log.d(TAG, "launchHbbtv_native called");
        return 0;
    }

    public static int loadEventByEIT_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.loadEventByEIT_native(i, i2);
        }
        Log.d(TAG, "loadEventByEIT_native called ...\n");
        return 0;
    }

    public static int[] loadEvents_native(int i, long j, int i2) {
        if (!is_emulator()) {
            return TVNative.loadEvents_native(i, j, i2);
        }
        Log.d(TAG, "loadEvents_native called ...\n");
        return null;
    }

    public static int markWebServiceTrustClient_native(String str, boolean z) {
        if (!is_emulator()) {
            return TVNative.markWebServiceTrustClient_native(str, z);
        }
        Log.d(TAG, "markWebServiceTrustClient_native called ...\n");
        return 0;
    }

    public static boolean menuIsTvBlock_native() {
        if (!is_emulator()) {
            return TVNative.menuIsTvBlock_native();
        }
        Log.d(TAG, "menuIsTvBlock_native called...\n");
        return true;
    }

    public static int mountPerm_native() {
        if (!is_emulator()) {
            return TVNative.mountPerm_native();
        }
        Log.d(TAG, "mountPerm_native called...\n");
        return -1;
    }

    public static int nextStream_native() {
        if (!is_emulator()) {
            return TVNative.nextStream_native();
        }
        Log.d(TAG, "nextStream_native called ...\n");
        return 0;
    }

    public static int notifyCecCompInfo_native(String str, int i, String str2) {
        if (!is_emulator()) {
            return TVNative.notifyCecCompInfo_native(str, i, str2);
        }
        Log.d(TAG, "notifyCecCompInfo_native called...\n");
        return 0;
    }

    public static void notifySoftKeyboardStatusChange_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "notifySoftKeyboardStatusChange_native called...\n");
        } else {
            TVNative.notifySoftKeyboardStatusChange_native(i);
        }
    }

    public static void notifyTextChange_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "notifyTextChange_native called...\n");
        } else {
            TVNative.notifyTextChange_native(str);
        }
    }

    public static int oneChannelListAddToMax_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.oneChannelListAddToMax_native(i, i2);
        }
        Log.d(TAG, "oneChannelListAddToMax_native called. svlId = " + i + ", svlRecId = " + i2);
        return 0;
    }

    public static int oneChannelListArrange_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.oneChannelListArrange_native(i, i2);
        }
        Log.d(TAG, "getTvproviderOcl_native called... svlIdT=\n" + i + ", svlIdC=" + i2);
        return 0;
    }

    public static int onlyChgFocus_native(String str) {
        if (!is_emulator()) {
            return TVNative.onlyChgFocus_native(str);
        }
        Log.d(TAG, "onlyChgFocus_native called");
        return 0;
    }

    public static int openUARTSerial_native(int i, int[] iArr, int[] iArr2) {
        if (!is_emulator()) {
            return TVNative.openUARTSerial_native(i, iArr, iArr2);
        }
        Log.d(TAG, "openUARTSerial_native called...\n");
        return 0;
    }

    public static int outputUARTSerial_native(int i, byte[] bArr) {
        if (!is_emulator()) {
            return TVNative.outputUARTSerial_native(i, bArr);
        }
        Log.d(TAG, "outputUARTSerial_native called...\n");
        return 0;
    }

    public static boolean pauseTimeshift_native() {
        if (!is_emulator()) {
            return TVNative.pauseTimeshift_native();
        }
        Log.d(TAG, "pauseTimeshift_native called...\n");
        return true;
    }

    public static int playStream_native(int i) {
        if (!is_emulator()) {
            return TVNative.playStream_native(i);
        }
        Log.d(TAG, "playStream_native called ...\n");
        return 0;
    }

    public static boolean playTimeshift_native() {
        if (!is_emulator()) {
            return TVNative.playTimeshift_native();
        }
        Log.d(TAG, "playTimeshift_native called...\n");
        return true;
    }

    public static int popEnterNextTvMode_native() {
        if (!is_emulator()) {
            return TVNative.popEnterNextTvMode_native();
        }
        Log.d(TAG, "popEnterNextTvMode_native Emulator called");
        return 0;
    }

    public static int popGetFocusInfo_native(MtkTvPipPopFucusInfoBase mtkTvPipPopFucusInfoBase) {
        if (is_emulator()) {
            Log.d(TAG, "popGetFocusInfo_native Emulator called");
            return 0;
        }
        Log.d(TAG, "popGetFocusInfo_native  called");
        return TVNative.popGetFocusInfo_native(mtkTvPipPopFucusInfoBase);
    }

    public static boolean popGetPipFocusId_native() {
        if (is_emulator()) {
            Log.d(TAG, "popGetPipFocusId_native Emulator called");
            return true;
        }
        Log.d(TAG, "popGetPipFocusId_native  called");
        return TVNative.popGetPipFocusId_native();
    }

    public static int popNextPipWindowPosition_native() {
        if (!is_emulator()) {
            return TVNative.popNextPipWindowPosition_native();
        }
        Log.d(TAG, "popNextPipWindowPosition_native Emulator called");
        return 0;
    }

    public static int popNextPipWindowSize_native() {
        if (!is_emulator()) {
            return TVNative.popNextPipWindowSize_native();
        }
        Log.d(TAG, "popNextPipWindowSize_native Emulator called");
        return 0;
    }

    public static int popSwapWindow_native() {
        if (!is_emulator()) {
            return TVNative.popSwapWindow_native();
        }
        Log.d(TAG, "popSwapWindow_native Emulator called");
        return 0;
    }

    public static int popSwitchAudioFocus_native() {
        if (!is_emulator()) {
            return TVNative.popSwitchAudioFocus_native();
        }
        Log.d(TAG, "popSwitchAudioFocus_native Emulator called");
        return 0;
    }

    public static int powerOnDeviceByLogicAddr_native(int i) {
        if (!is_emulator()) {
            return TVNative.powerOnDeviceByLogicAddr_native(i);
        }
        Log.d(TAG, "powerOnDeviceByLogicAddr_native called...\n");
        return 0;
    }

    public static boolean queryConflict_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.queryConflict_native(i, i2);
        }
        Log.d(TAG, "queryConflict_native called");
        return false;
    }

    public static int queryGpioStatus_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.queryGpioStatus_native(i, i2);
        }
        Log.d(TAG, "queryGpioStatus_native called...\n");
        return 0;
    }

    public static int queryUpgradeResult_native(MtkTvUpgradeDeliveryTypeBase mtkTvUpgradeDeliveryTypeBase) {
        if (!is_emulator()) {
            return TVNative.queryUpgradeResult_native(mtkTvUpgradeDeliveryTypeBase);
        }
        Log.d(TAG, "queryUpgradeResult_native called ...");
        return 0;
    }

    public static int recordClosePVR(int i) {
        if (!is_emulator()) {
            return TVNative.recordClosePVR(i);
        }
        Log.d(TAG, "recordClosePVR called...\n");
        return 0;
    }

    public static int recordGetPVRSrcType() {
        if (!is_emulator()) {
            return TVNative.recordGetPVRSrcType();
        }
        Log.d(TAG, "recordGetPVRSrcType called...\n");
        return 0;
    }

    public static int recordGetPVRSrcTypeByHandle(int i) {
        if (!is_emulator()) {
            return TVNative.recordGetPVRSrcTypeByHandle(i);
        }
        Log.d(TAG, "recordGetPVRSrcType called...\n");
        return 0;
    }

    public static int recordGetPVRStatus() {
        if (!is_emulator()) {
            return TVNative.recordGetPVRStatus();
        }
        Log.d(TAG, "recordGetPVRStatus called...\n");
        return 0;
    }

    public static int recordGetPVRStatusByHandle(int i) {
        if (!is_emulator()) {
            return TVNative.recordGetPVRStatusByHandle(i);
        }
        Log.d(TAG, "recordGetPVRStatus called...\n");
        return 0;
    }

    public static long recordGetRecordingFilesize() {
        if (!is_emulator()) {
            return TVNative.recordGetRecordingFilesize();
        }
        Log.d(TAG, "recordGetRecordingFilesize called...\n");
        return 0L;
    }

    public static int recordOpenPVR(int i) {
        if (!is_emulator()) {
            return TVNative.recordOpenPVR(i);
        }
        Log.d(TAG, "recordStartPVR called...\n");
        return 0;
    }

    public static int recordOpenPVREx(int i, boolean z) {
        if (!is_emulator()) {
            return TVNative.recordOpenPVREx(i, z);
        }
        Log.d(TAG, "recordStartPVREx called...\n");
        return 0;
    }

    public static int recordPVRSelectSvc(int i, int i2, int i3) {
        if (!is_emulator()) {
            return TVNative.recordPVRSelectSvc(i, i2, i3);
        }
        Log.d(TAG, "recordPVRSelectSvc called...\n");
        return 0;
    }

    public static int recordStartPVR(int i) {
        if (!is_emulator()) {
            return TVNative.recordStartPVR(i);
        }
        Log.d(TAG, "recordStartPVR called...\n");
        return 0;
    }

    public static int recordStartPVRByHandle(int i) {
        if (!is_emulator()) {
            return TVNative.recordStartPVRByHandle(i);
        }
        Log.d(TAG, "recordStartPVR called...\n");
        return 0;
    }

    public static boolean recordStartSpeedTest(String str, int i) {
        if (!is_emulator()) {
            return TVNative.recordStartSpeedTest_native(str, i);
        }
        Log.d(TAG, "recordStartSpeedTest called...\n");
        return true;
    }

    public static int recordStopPVR() {
        if (!is_emulator()) {
            return TVNative.recordStopPVR();
        }
        Log.d(TAG, "recordStopPVR called...\n");
        return 0;
    }

    public static int recordStopPVRByHandle(int i) {
        if (!is_emulator()) {
            return TVNative.recordStopPVRByHandle(i);
        }
        Log.d(TAG, "recordStopPVR called...\n");
        return 0;
    }

    public static int recordgetErrorID() {
        if (!is_emulator()) {
            return TVNative.recordgetErrorID();
        }
        Log.d(TAG, "recordgetErrorID called...\n");
        return 0;
    }

    public static int recordgetErrorIDByHandle(int i) {
        if (!is_emulator()) {
            return TVNative.recordgetErrorIDByhandle(i);
        }
        Log.d(TAG, "recordgetErrorID called...\n");
        return 0;
    }

    public static int registerDefaultCallback_native() {
        if (mIsregisterCallback || is_emulator()) {
            Log.d(TAG, "registerDefaultCallback_native called");
            return 0;
        }
        mIsregisterCallback = true;
        return TVNative.registerDefaultCallback_native();
    }

    public static int registerTimeshiftDevice_native(String str, long j) {
        if (!is_emulator()) {
            return TVNative.registerTimeshiftDevice_native(str, j);
        }
        Log.d(TAG, "registerTimeshiftDevice called...\n");
        return 0;
    }

    public static int remindMeLater_native() {
        if (!is_emulator()) {
            return TVNative.remindMeLater_native();
        }
        Log.d(TAG, "remindMeLater_native called...");
        return 0;
    }

    public static int removeConfigListener_native(String str) {
        if (!is_emulator()) {
            return TVNative.removeConfigListener_native(str);
        }
        Log.d(TAG, "removeConfigListener_native called");
        return 0;
    }

    public static int removeFavoritelistChannelByIndexWithoutShowFavIcon_native(int i) {
        if (!is_emulator()) {
            return TVNative.removeFavoritelistChannelByIndexWithoutShowFavIcon_native(i);
        }
        Log.d(TAG, "removeFavoritelistChannelByIndexWithoutShowFavIcon_native called...\n");
        return 0;
    }

    public static int removeFavoritelistChannel_native(int i) {
        if (!is_emulator()) {
            return TVNative.removeFavoritelistChannel_native(i);
        }
        Log.d(TAG, "removeFavoritelistChannel_native called...\n");
        return 0;
    }

    public static int removeWebServiceClient_native(String str) {
        if (!is_emulator()) {
            return TVNative.removeWebServiceClient_native(str);
        }
        Log.d(TAG, "removeWebServiceClient_native called ...\n");
        return 0;
    }

    public static int resetConfigValues_native(int i) {
        if (!is_emulator()) {
            return TVNative.resetConfigValues_native(i);
        }
        Log.d(TAG, "resetConfigValues_native called");
        return 0;
    }

    public static void resetDigitKeysHandledFlag_native() {
        if (is_emulator()) {
            Log.d(TAG, "resetDigitKeysHandledFlag_native called...\n");
        } else {
            TVNative.resetDigitKeysHandledFlag_native();
        }
    }

    public static void resetFac_native() {
        if (is_emulator()) {
            Log.d(TAG, "resetFac_native called");
        } else {
            TVNative.resetFac_native();
        }
    }

    public static void resetPri_native() {
        if (is_emulator()) {
            Log.d(TAG, "resetPri_native called");
        } else {
            TVNative.resetPri_native();
        }
    }

    public static void resetPub_native() {
        if (is_emulator()) {
            Log.d(TAG, "resetPub_native called");
        } else {
            TVNative.resetPub_native();
        }
    }

    public static int restoreSatlSnapshot_native(int i) {
        if (!is_emulator()) {
            return TVNative.restoreSatlSnapshot_native(i);
        }
        Log.d(TAG, "restoreSatlSnapshot_native called...\n");
        return 0;
    }

    public static int restoreSnapshot_native(int i) {
        if (!is_emulator()) {
            return TVNative.restoreSnapshot_native(i);
        }
        Log.d(TAG, "restoreSnapshot_native called...\n");
        return 0;
    }

    public static void rpcCloseClient_native() {
        if (is_emulator()) {
            Log.d(TAG, "rpcCloseClient_native called...\n");
        } else {
            Log.d(TAG, "TVNative.rpcCloseClient_nativeinvoked");
            TVNative.rpcCloseClient_native();
        }
    }

    public static int satllistCleanDatabase_native(int i) {
        if (!is_emulator()) {
            return TVNative.satllistCleanDatabase_native(i);
        }
        Log.d(TAG, "satllistCleanDatabase_native called...\n");
        return 0;
    }

    public static int satllistLockDatabase_native(int i) {
        if (!is_emulator()) {
            return TVNative.satllistLockDatabase_native(i);
        }
        Log.d(TAG, "satllistLockDatabase_native called...\n");
        return 0;
    }

    public static int satllistReadLockDatabase_native(int i) {
        if (!is_emulator()) {
            return TVNative.satllistReadLockDatabase_native(i);
        }
        Log.d(TAG, "satllistReadLockDatabase_native called...\n");
        return 0;
    }

    public static int satllistReadUnLockDatabase_native(int i) {
        if (!is_emulator()) {
            return TVNative.satllistReadUnLockDatabase_native(i);
        }
        Log.d(TAG, "satllistReadUnLockDatabase_native called...\n");
        return 0;
    }

    public static int satllistUnLockDatabase_native(int i) {
        if (!is_emulator()) {
            return TVNative.satllistUnLockDatabase_native(i);
        }
        Log.d(TAG, "satllistUnLockDatabase_native called...\n");
        return 0;
    }

    public static void saveTimeStamp_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "saveTimeStamp_native called");
        } else {
            TVNative.saveTimeStamp_native(str);
        }
    }

    public static int sbAtscGetMajorChannelNum_native(int i) {
        if (!is_emulator()) {
            return TVNative.sbAtscGetMajorChannelNum_native(i);
        }
        Log.d(TAG, "sbAtscGetMajorChannelNum_native called...\n");
        return 0;
    }

    public static int sbAtscGetMinorChannelNum_native(int i) {
        if (!is_emulator()) {
            return TVNative.sbAtscGetMinorChannelNum_native(i);
        }
        Log.d(TAG, "sbAtscGetMinorChannelNum_native called...\n");
        return 0;
    }

    public static int sbIsdbGetChannelIndex_native(int i) {
        if (!is_emulator()) {
            return TVNative.sbIsdbGetChannelIndex_native(i);
        }
        Log.d(TAG, "sbIsdbGetChannelIndex_native called...\n");
        return 0;
    }

    public static int sbIsdbGetMajorNumber_native(int i) {
        if (!is_emulator()) {
            return TVNative.sbIsdbGetMajorNumber_native(i);
        }
        Log.d(TAG, "sbIsdbGetMajorNumber_native called...\n");
        return 0;
    }

    public static int sbIsdbGetMinorNumber_native(int i) {
        if (!is_emulator()) {
            return TVNative.sbIsdbGetMinorNumber_native(i);
        }
        Log.d(TAG, "sbIsdbGetMinorNumber_native called...\n");
        return 0;
    }

    public static int seekTimeshift_native(long j) {
        if (!is_emulator()) {
            return TVNative.seekTimeshift_native(j);
        }
        Log.d(TAG, "seekTimeshift_native called...\n");
        return 0;
    }

    public static int seekTo_native(long j) {
        if (!is_emulator()) {
            return TVNative.seekTo_native(j);
        }
        Log.d(TAG, "seekTo_native called...\n");
        return 0;
    }

    public static int selectAudioById_native(int i) {
        if (!is_emulator()) {
            return TVNative.selectAudioById_native(i);
        }
        Log.d(TAG, "selectAudioById_native...\n");
        return 0;
    }

    public static int selectDefaultAudio_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "selectDefaultAudio_native Emulator called");
            return 0;
        }
        Log.d(TAG, "selectDefaultAudio_native  called");
        return TVNative.selectDefaultAudio_native(str);
    }

    public static int selectMainSubAudioById_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.selectMainSubAudioById_native(i, i2);
        }
        Log.d(TAG, "selectMainSubAudioById_native...\n");
        return 0;
    }

    public static int set2ndCurrentChannelBySvlId_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.set2ndCurrentChannelBySvlId_native(i, i2);
        }
        Log.d(TAG, "set2ndCurrentChannelBySvlId_native called...\n");
        return 0;
    }

    public static int setATSCScanParas(MtkTvATSCScanParaBase mtkTvATSCScanParaBase) {
        if (!is_emulator()) {
            return TVNative.setATSCScanParas(mtkTvATSCScanParaBase);
        }
        Log.d(TAG, "setATSCScanParas called...\n");
        return 0;
    }

    public static int setAdbStatus_native(boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "setAdbStatus Emulator called");
            return 0;
        }
        Log.d(TAG, "setAdbStatus  called");
        return TVNative.setAdbStatus_native(z);
    }

    public static int setAndroidWorldInfoToLinux_native(int i, int i2) {
        return TVNativeWrapperCustom.setAndroidWorldInfoToLinux_native(i, i2);
    }

    public static int setAtscStorage_native(boolean z) {
        if (!is_emulator()) {
            return TVNative.setAtscStorage_native(z);
        }
        Log.d(TAG, "setAtscStorage_native called...\n");
        return 0;
    }

    public static int setAudioFocus_native(String str) {
        if (!is_emulator()) {
            return TVNative.setAudioFocus_native(str);
        }
        Log.d(TAG, "setAudioFocus_native called");
        return 0;
    }

    public static int setAudioFocusbySourceid_native(int i) {
        if (!is_emulator()) {
            return TVNative.setAudioFocusbySourceid_native(i);
        }
        Log.d(TAG, "setAudioFocusbySourceid_native called");
        return 0;
    }

    public static int setAudioInfoData_native(int i, MtkTvAudioInfoBase mtkTvAudioInfoBase) {
        if (!is_emulator()) {
            return TVNative.setAudioInfoData_native(i, mtkTvAudioInfoBase);
        }
        Log.d(TAG, "setAudioInfoData_native...\n");
        return 0;
    }

    public static int setAudioInfoValue_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.setAudioInfoValue_native(i, i2);
        }
        Log.d(TAG, "setAudioInfoValue_native...\n");
        return 0;
    }

    public static int setAutoDetectPlugStatus_native(int i, boolean z) {
        if (!is_emulator()) {
            return TVNative.setAutoDetectPlugStatus_native(i, z);
        }
        Log.d(TAG, "setAutoDetectPlugStatus_native called");
        return 0;
    }

    public static int setAutoDownload_native(boolean z) {
        if (!is_emulator()) {
            return TVNative.setAutoDownload_native(z);
        }
        Log.d(TAG, "setAutoDownload_native called...");
        return 0;
    }

    public static int setAutoRecord_native(boolean z) {
        if (!is_emulator()) {
            return TVNative.setAutoRecord_native(z);
        }
        Log.d(TAG, "setAutoRecord_native called...\n");
        return 0;
    }

    public static int setBisskeyInfo_native(int i, MtkTvBisskeyInfoBase mtkTvBisskeyInfoBase, int i2) {
        if (!is_emulator()) {
            return TVNative.setBisskeyInfo_native(i, mtkTvBisskeyInfoBase, i2);
        }
        Log.d(TAG, "setBisskeyInfo_native called...\n");
        return 0;
    }

    public static int setBlockUnrated_native(boolean z) {
        if (!is_emulator()) {
            return TVNative.setBlockUnrated_native(z);
        }
        Log.d(TAG, "setBlockUnrated_native called...\n");
        return 0;
    }

    public static int setBookingChannelId(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.setBookingChannelId_native(i, i2);
        }
        Log.d(TAG, "setBookingChannelId called...\n");
        return -1;
    }

    public static int setBookingDeviceIndex(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.setBookingDeviceIndex_native(i, i2);
        }
        Log.d(TAG, "setBookingDeviceIndex called...\n");
        return -1;
    }

    public static int setBookingEventId(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.setBookingEventId_native(i, i2);
        }
        Log.d(TAG, "setBookingEventId called...\n");
        return -1;
    }

    public static int setBookingEventTitle(int i, String str) {
        if (!is_emulator()) {
            return TVNative.setBookingEventTitle_native(i, str);
        }
        Log.d(TAG, "setBookingEventTitle called...\n");
        return 0;
    }

    public static int setBookingGenre(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.setBookingGenre_native(i, i2);
        }
        Log.d(TAG, "setBookingGenre called...\n");
        return -1;
    }

    public static int setBookingInfoData(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.setBookingInfoData_native(i, i2);
        }
        Log.d(TAG, "setBookingInfoData called...\n");
        return -1;
    }

    public static int setBookingRecordDelay(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.setBookingRecordDelay_native(i, i2);
        }
        Log.d(TAG, "setBookingRecordDelay called...\n");
        return -1;
    }

    public static int setBookingRecordDuration(int i, long j) {
        if (!is_emulator()) {
            return TVNative.setBookingRecordDuration_native(i, j);
        }
        Log.d(TAG, "setBookingRecordDuration called...\n");
        return -1;
    }

    public static int setBookingRecordMode(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.setBookingRecordMode_native(i, i2);
        }
        Log.d(TAG, "setBookingRecordMode called...\n");
        return -1;
    }

    public static int setBookingRepeatMode(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.setBookingRepeatMode_native(i, i2);
        }
        Log.d(TAG, "setBookingRepeatMode called...\n");
        return -1;
    }

    public static int setBookingSourceType(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.setBookingSourceType_native(i, i2);
        }
        Log.d(TAG, "setBookingSourceType called...\n");
        return -1;
    }

    public static int setBookingStartTime(int i, long j) {
        if (!is_emulator()) {
            return TVNative.setBookingStartTime_native(i, j);
        }
        Log.d(TAG, "setBookingStartTime called...\n");
        return -1;
    }

    public static int setBookingTunerType(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.setBookingTunerType_native(i, i2);
        }
        Log.d(TAG, "setBookingTunerType called...\n");
        return -1;
    }

    public static int setBookingType(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.setBookingType_native(i, i2);
        }
        Log.d(TAG, "setBookingType called...\n");
        return -1;
    }

    public static int setBookingUrlInfo(int i, String str) {
        if (!is_emulator()) {
            return TVNative.setBookingUrlInfo_native(i, str);
        }
        Log.d(TAG, "setBookingUrlInfo called...\n");
        return 0;
    }

    public static int setCANEngRatingSettingInfo_native(int i) {
        if (!is_emulator()) {
            return TVNative.setCANEngRatingSettingInfo_native(i);
        }
        Log.d(TAG, "setCANEngRatingSettingInfo_native called...\n");
        return 0;
    }

    public static int setCANFreRatingSettingInfo_native(int i) {
        if (!is_emulator()) {
            return TVNative.setCANFreRatingSettingInfo_native(i);
        }
        Log.d(TAG, "setCANFreRatingSettingInfo_native called...\n");
        return 0;
    }

    public static int setCamPinCode_navtive(int i, String str) {
        if (!is_emulator()) {
            return TVNative.setCamPinCode_navtive(i, str);
        }
        Log.d(TAG, "setCamPinCode_navtive called...");
        return 0;
    }

    public static int setChannelListWithoutNotify_native(int i, int i2, List<MtkTvChannelInfoBase> list) {
        if (!is_emulator()) {
            return TVNative.setChannelListWithoutNotify_native(i, i2, list);
        }
        Log.d(TAG, "setChannelListWithoutNotify_native called...\n");
        return 0;
    }

    public static int setChannelList_native(int i, int i2, List<MtkTvChannelInfoBase> list) {
        if (!is_emulator()) {
            return TVNative.setChannelList_native(i, i2, list);
        }
        Log.d(TAG, "setChannelList_native called...\n");
        return 0;
    }

    public static int setChannelPumpVer_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.setChannelPumpVer_native(i, i2);
        }
        Log.d(TAG, "setChannelPumpVer_native called. svlId = " + i + ", pumpVer = " + i2);
        return 0;
    }

    public static int setChgSource_native(boolean z) {
        if (!is_emulator()) {
            return TVNative.setChgSource_native(z);
        }
        Log.d(TAG, "setChgSource_native called");
        return 0;
    }

    public static int setConfigIntArrayValue_native(String str, int[] iArr, int i) {
        if (!is_emulator()) {
            return TVNative.setConfigIntArrayValue_native(str, iArr, i);
        }
        Log.d(TAG, "setConfigIntArrayValue_native called");
        return -1;
    }

    public static int setConfigStringEx_native(String str, String str2, int i) {
        if (!is_emulator()) {
            return TVNative.setConfigStringEx_native(str, str2, i);
        }
        Log.d(TAG, "setConfigStringEx_native called");
        return 0;
    }

    public static int setConfigString_native(String str, String str2) {
        if (!is_emulator()) {
            return TVNative.setConfigString_native(str, str2);
        }
        Log.d(TAG, "setConfigString_native called");
        return 0;
    }

    public static int setConfigValue_native(int i, String str, int i2, int i3) {
        if (!is_emulator()) {
            return TVNative.setConfigValue_native(i, str, i2, i3);
        }
        Log.d(TAG, "setConfigValue_native called");
        return 0;
    }

    public static int setCrntOverscan_native(int i, int i2, int i3, int i4) {
        if (!is_emulator()) {
            return TVNative.setCrntOverscan_native(i, i2, i3, i4);
        }
        Log.d(TAG, "setCrntOverscan_native called ...\n");
        return 0;
    }

    public static void setCurrentActiveWindowsInfoEx_native(List<MtkTvChannelInfoBase> list, long j, long j2) {
        if (is_emulator()) {
            Log.d(TAG, "setCurrentActiveWindowsInfoEx_native called...\n");
        } else {
            TVNative.setCurrentActiveWindowsInfoEx_native(list, j, j2);
        }
    }

    public static void setCurrentActiveWindowsInfo_native(List<MtkTvChannelInfoBase> list, long j) {
        if (is_emulator()) {
            Log.d(TAG, "setCurrentActiveWindowsInfo_native called...\n");
        } else {
            TVNative.setCurrentActiveWindowsInfo_native(list, j);
        }
    }

    public static int setCurrentChannelBySvlId_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.setCurrentChannelBySvlId_native(i, i2);
        }
        Log.d(TAG, "setCurrentChannelBySvlId_native called...\n");
        return 0;
    }

    public static int setCurrentChannel_native(int i) {
        if (!is_emulator()) {
            return TVNative.setCurrentChannel_native(i);
        }
        Log.d(TAG, "setCurrentChannel_native called...\n");
        return 0;
    }

    public static int setDVBAgeRatingSetting_native(int i) {
        if (!is_emulator()) {
            return TVNative.setDVBAgeRatingSetting_native(i);
        }
        Log.d(TAG, "setDVBAgeRatingSetting_native called...\n");
        return 0;
    }

    public static int setDigitalFavoritesList_native(int i, int i2, int i3, List<MtkTvChannelInfoBase> list, int i4) {
        if (!is_emulator()) {
            return TVNative.setDigitalFavoritesList_native(i, i2, i3, list, i4);
        }
        Log.d(TAG, "setDigitalFavoritesList_native called...\n");
        return 0;
    }

    public static int setDvbScanParas(MtkTvDvbScanParaBase mtkTvDvbScanParaBase) {
        if (!is_emulator()) {
            return TVNative.setDvbScanParas(mtkTvDvbScanParaBase);
        }
        Log.d(TAG, "setDvbScanParas called...\n");
        return 0;
    }

    public static int setDvbcManualScanParas(MtkTvDvbcManualScanParaBase mtkTvDvbcManualScanParaBase) {
        if (!is_emulator()) {
            return TVNative.setDvbcManualScanParas(mtkTvDvbcManualScanParaBase);
        }
        Log.d(TAG, "setDvbcManualScanParas called...\n");
        return 0;
    }

    public static int setDvbcScanParas(MtkTvDvbcScanParaBase mtkTvDvbcScanParaBase) {
        if (!is_emulator()) {
            return TVNative.setDvbcScanParas(mtkTvDvbcScanParaBase);
        }
        Log.d(TAG, "setDvbcScanParas called...\n");
        return 0;
    }

    public static int setEASAndroidLaunchStatus_native(boolean z) {
        if (!is_emulator()) {
            return TVNative.setEASAndroidLaunchStatus_native(z);
        }
        Log.d(TAG, "setEASAndroidLaunchStatus_native called ...\n");
        return 0;
    }

    public static int setEarcEnable_native(boolean z) {
        if (!is_emulator()) {
            return TVNative.setEarcEnable_native(z);
        }
        Log.d(TAG, "setEarcEnable_native called...\n");
        return 0;
    }

    public static int setEnqAnswer_navtive(int i, int i2, int i3, String str) {
        if (!is_emulator()) {
            return TVNative.setEnqAnswer_navtive(i, i2, i3, str);
        }
        Log.d(TAG, "setEnqAnswer_navtive called...");
        return 0;
    }

    public static boolean setEthernetWolCtl_native(boolean z) {
        if (!is_emulator()) {
            return TVNative.setEthernetWolCtl_native(z);
        }
        Log.d(TAG, "setEthernetWolCtl called...\n");
        return true;
    }

    public static void setEventCallback_native(boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "setEventCallback_native called...\n");
        } else {
            TVNative.setEventCallback_native(z);
        }
    }

    public static int setFileLockChannel(String str, int i) {
        if (!is_emulator()) {
            return TVNative.setFileLockChannel(str, i);
        }
        Log.d(TAG, "setFileLockChannel called...\n");
        return 0;
    }

    public static int setFileLockInput(String str, int i) {
        if (!is_emulator()) {
            return TVNative.setFileLockInput(str, i);
        }
        Log.d(TAG, "setFileLockInput called...\n");
        return 0;
    }

    public static int setFreeze_native(String str, boolean z) {
        if (!is_emulator()) {
            return TVNative.setFreeze_native(str, z);
        }
        Log.d(TAG, "setFreeze_native called ...\n");
        return 0;
    }

    public static int setHDSConfirm_navtive(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "setHDSConfirm_navtive called...");
            return 0;
        }
        Log.d(TAG, "setHDSConfirm_navtive called...");
        return TVNative.setHDSConfirm_navtive(i, i2);
    }

    public static void setHbbtvStatus_native(boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "setHbbtvStatus_native called...\n");
        } else {
            TVNative.setHbbtvStatus_native(z);
        }
    }

    public static int setISDBAgeRatingSetting_native(int i) {
        if (!is_emulator()) {
            return TVNative.setISDBAgeRatingSetting_native(i);
        }
        Log.d(TAG, "setISDBAgeRatingSetting_native called...\n");
        return 0;
    }

    public static int setISDBContentRatingSetting_native(int i) {
        if (!is_emulator()) {
            return TVNative.setISDBContentRatingSetting_native(i);
        }
        Log.d(TAG, "setISDBContentRatingSetting_native called...\n");
        return 0;
    }

    public static int setISDBScanParas(MtkTvISDBScanParaBase mtkTvISDBScanParaBase) {
        if (!is_emulator()) {
            return TVNative.setISDBScanParas(mtkTvISDBScanParaBase);
        }
        Log.d(TAG, "setISDBScanParas called...\n");
        return 0;
    }

    public static void setInfo_native(byte b2, int i, int[] iArr) {
        if (is_emulator()) {
            Log.d(TAG, "setInfo_native called...\n");
        } else {
            TVNative.setInfo_native(b2, i, iArr);
        }
    }

    public static int setInputLabelIdx_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.setInputLabelIdx_native(i, i2);
        }
        Log.d(TAG, "setInputLabelIdx_native called");
        return 0;
    }

    public static int setInputLabelUserDefName_native(int i, String str) {
        if (!is_emulator()) {
            return TVNative.setInputLabelUserDefName_native(i, str);
        }
        Log.d(TAG, "setInputLabelUserDefName_native called");
        return 0;
    }

    public static int setLinuxBGMStart_native() {
        if (!is_emulator()) {
            return TVNative.setLinuxBGMStart_native();
        }
        Log.d(TAG, "setLinuxBGMStart_native called");
        return 0;
    }

    public static void setLinuxKeyReceive_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "setLinuxKeyReceive_native called");
        } else {
            TVNative.setLinuxKeyReceive_native(i);
        }
    }

    public static void setLocalTimeZone_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "setLocalTimeZone_native called...\n");
        } else {
            TVNative.setLocalTimeZone_native(str);
        }
    }

    public static void setMMICloseDone_navtive(int i) {
        if (is_emulator()) {
            Log.d(TAG, "setMMICloseDone_navtive called...");
        } else {
            TVNative.setMMICloseDone_navtive(i);
        }
    }

    public static void setMMIClose_navtive(int i) {
        if (is_emulator()) {
            Log.d(TAG, "setMMIClose_navtive called...");
        } else {
            TVNative.setMMIClose_navtive(i);
        }
    }

    public static int setMenuAnswer_navtive(int i, int i2, int i3) {
        if (!is_emulator()) {
            return TVNative.setMenuAnswer_navtive(i, i2, i3);
        }
        Log.d(TAG, "setMenuAnswer_navtive called...");
        return 0;
    }

    public static void setMheg5Disable_native() {
        if (is_emulator()) {
            Log.d(TAG, "setMheg5Disable_native called...\n");
        } else {
            TVNative.setMheg5Disable_native();
        }
    }

    public static void setMheg5Enable_native() {
        if (is_emulator()) {
            Log.d(TAG, "setMheg5Enable_native called...\n");
        } else {
            TVNative.setMheg5Enable_native();
        }
    }

    public static void setMheg5Status_native(int i, int i2, int i3, int i4) {
        if (is_emulator()) {
            Log.d(TAG, "setMheg5Status called...\n");
        } else {
            TVNative.setMheg5Status_native(i, i2, i3, i4);
        }
    }

    public static int setMute_native(boolean z) {
        if (!is_emulator()) {
            return TVNative.setMute_native(z);
        }
        Log.d(TAG, "setMute_native called");
        return 0;
    }

    public static int setNTSCScanParas(MtkTvNTSCScanParaBase mtkTvNTSCScanParaBase) {
        if (!is_emulator()) {
            return TVNative.setNTSCScanParas(mtkTvNTSCScanParaBase);
        }
        Log.d(TAG, "setNTSCScanParas called...\n");
        return 0;
    }

    public static int setNewTvMode_native(int i) {
        if (!is_emulator()) {
            return TVNative.setNewTvMode_native(i);
        }
        Log.d(TAG, "setNewTvMode_native called");
        return 0;
    }

    public static int setNextAudioLang_native() {
        if (!is_emulator()) {
            return TVNative.setNextAudioLang_native();
        }
        Log.d(TAG, "setNextAudioLang_native...\n");
        return 0;
    }

    public static boolean setOSDOpacity_native(int i) {
        if (!is_emulator()) {
            return TVNative.setOSDOpacity_native(i);
        }
        Log.d(TAG, "setOSDOpacity_native called");
        return false;
    }

    public static int setOpenVCHIPSettingInfo_native(MtkTvOpenVCHIPSettingInfoBase mtkTvOpenVCHIPSettingInfoBase) {
        if (!is_emulator()) {
            return TVNative.setOpenVCHIPSettingInfo_native(mtkTvOpenVCHIPSettingInfoBase);
        }
        Log.d(TAG, "setOpenVCHIPSettingInfo_native called...\n");
        return 0;
    }

    public static int setPclWakeupSetup_native(int i) {
        if (!is_emulator()) {
            return TVNative.setPclWakeupSetup_native(i);
        }
        Log.d(TAG, "setPclWakeupSetup_native called");
        return 0;
    }

    public static void setPfgResult_native(boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "setPfgResult called...\n");
        } else {
            TVNative.setPfgResult_native(z);
        }
    }

    public static int setPictureMode_native(int i) {
        if (!is_emulator()) {
            return TVNative.setPictureMode_native(i);
        }
        Log.d(TAG, "setPictureMode_native called ...\n");
        return 0;
    }

    public static int setPipConfig_native(int i) {
        if (!is_emulator()) {
            return TVNative.setPipConfig_native(i);
        }
        Log.d(TAG, "setPipConfig_native called");
        return 0;
    }

    public static int setPkgPathname_native(String str) {
        if (!is_emulator()) {
            return TVNative.setPkgPathname_native(str);
        }
        Log.d(TAG, "setPkgPathname_native called...");
        return 0;
    }

    public static int setPlaybackPause_native() {
        if (!is_emulator()) {
            return TVNative.setPlaybackPause_native();
        }
        Log.d(TAG, "setPlaybackPause_native called...\n");
        return 0;
    }

    public static int setPlaybackResume_native() {
        if (!is_emulator()) {
            return TVNative.setPlaybackResume_native();
        }
        Log.d(TAG, "setPlaybackResume_native called...\n");
        return 0;
    }

    public static int setPlaybackSpeed_native(MtkTvTimeshiftBase.TimeshiftPlaybackSpeed timeshiftPlaybackSpeed) {
        if (!is_emulator()) {
            return TVNative.setPlaybackSpeed_native(timeshiftPlaybackSpeed);
        }
        Log.d(TAG, "setPlaybackSpeed_native called...\n");
        return 0;
    }

    public static int setRatingEnable_native(boolean z) {
        if (!is_emulator()) {
            return TVNative.setRatingEnable_native(z);
        }
        Log.d(TAG, "setRatingEnable_native called...\n");
        return 0;
    }

    public static int setRecordModeByHandle_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "setRecordModeByHandle called...\n");
            return 0;
        }
        Log.d(TAG, "setRecordModeByHandle \n");
        return TVNative.setRecordModeByHandle_native(i, i2);
    }

    public static int setRecordOff_native(int i) {
        if (!is_emulator()) {
            return TVNative.setRecordOff_native(i);
        }
        Log.d(TAG, "setRecordOff_native called...\n");
        return 0;
    }

    public static int setRecordOn_native(int i, MtkTvCecRecordSouceInfoBase mtkTvCecRecordSouceInfoBase) {
        if (!is_emulator()) {
            return TVNative.setRecordOn_native(i, mtkTvCecRecordSouceInfoBase);
        }
        Log.d(TAG, "setRecordOn_native called...\n");
        return 0;
    }

    public static boolean setRecordRegisterFile_native(String str) {
        if (!is_emulator()) {
            return TVNative.setRecordRegisterFile_native(str);
        }
        Log.d(TAG, "setRecordRegisterFile_native called...\n");
        return true;
    }

    public static int setSasForceItvExit_navtive(int i) {
        if (is_emulator()) {
            Log.d(TAG, "setSasForceItvExit_navtive called...");
            return 0;
        }
        Log.d(TAG, "setSasForceItvExit_navtive called...");
        return TVNative.setSasForceItvExit_navtive(i);
    }

    public static int setSatelliteSetting(MtkTvDvbsSatelliteSettingBase mtkTvDvbsSatelliteSettingBase) {
        if (!is_emulator()) {
            return TVNative.setSatelliteSetting(mtkTvDvbsSatelliteSettingBase);
        }
        Log.d(TAG, "setSatelliteSetting called...\n");
        return 0;
    }

    public static int setScanComplete_navtive(int i) {
        if (is_emulator()) {
            Log.d(TAG, "setScanComplete_navtive called...");
            return 0;
        }
        Log.d(TAG, "setScanComplete_navtive called...");
        return TVNative.setScanComplete_navtive(i);
    }

    public static int setScartAutoJump_native(boolean z) {
        if (!is_emulator()) {
            return TVNative.setScartAutoJump_native(z);
        }
        Log.d(TAG, "setScartAutoJump_native called");
        return 0;
    }

    public static int setScreenMode_native(int i) {
        if (!is_emulator()) {
            return TVNative.setScreenMode_native(i);
        }
        Log.d(TAG, "setScreenMode_native called ...\n");
        return 0;
    }

    public static int setScreenOutputDispRect_native(String str, MtkTvRectangle mtkTvRectangle) {
        if (!is_emulator()) {
            return TVNative.setScreenOutputDispRect_native(str, mtkTvRectangle);
        }
        Log.d(TAG, "setScreenOutputDispRect_native called");
        return 0;
    }

    public static int setScreenSourceRect_native(String str, MtkTvRectangle mtkTvRectangle) {
        if (!is_emulator()) {
            return TVNative.setScreenSourceRect_native(str, mtkTvRectangle);
        }
        Log.d(TAG, "setScreenSourceRect_native called");
        return 0;
    }

    public static void setSignalMonitor_native(boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "setSignalMonitor_native called");
        } else {
            TVNative.setSignalMonitor_native(z);
        }
    }

    public static void setSleepTimer_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "setSleepTimer_native called...\n");
        } else {
            TVNative.setSleepTimer_native(i);
        }
    }

    public static int setSoundEffect_native(int i) {
        if (!is_emulator()) {
            return TVNative.setSoundEffect_native(i);
        }
        Log.d(TAG, "setSoundEffect_native called ...\n");
        return 0;
    }

    public static int setStandbyToAll_native(boolean z) {
        if (!is_emulator()) {
            return TVNative.setStandbyToAll_native(z);
        }
        Log.d(TAG, "setStandbyToAll_native called...\n");
        return 0;
    }

    public static int setStandby_native(int i) {
        if (!is_emulator()) {
            return TVNative.setStandby_native(i);
        }
        Log.d(TAG, "setStandby_native called...\n");
        return 0;
    }

    public static int setSystemAudioModeRequest_native(int i) {
        if (!is_emulator()) {
            return TVNative.setSystemAudioModeRequest_native(i);
        }
        Log.d(TAG, "setSystemAudioModeRequest_native called...\n");
        return 0;
    }

    public static int setSystemProperties_native(String str, String str2) {
        if (!is_emulator()) {
            return TVNative.setSystemProperties_native(str, str2);
        }
        Log.d(TAG, "setSystemProperties_native called...\n");
        return 0;
    }

    public static int setTeletextPage_native(MtkTvTeletextPageBase mtkTvTeletextPageBase) {
        if (!is_emulator()) {
            return TVNative.setTeletextPage_native(mtkTvTeletextPageBase);
        }
        Log.d(TAG, "setTeletextPage_native called ...\n");
        return 0;
    }

    public static void setTimeOffset_native(long j) {
        if (is_emulator()) {
            Log.d(TAG, "setTimeOffset_native called...\n");
        } else {
            TVNative.setTimeOffset_native(j);
        }
    }

    public static void setTimeSyncSource_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "setTimeSyncSource_native called...\n");
        } else {
            TVNative.setTimeSyncSource_native(i);
        }
    }

    public static void setTimeZone_native(long j) {
        if (is_emulator()) {
            Log.d(TAG, "setTimeZone_native called...\n");
        } else {
            TVNative.setTimeZone_native(j);
        }
    }

    public static int setTimer_native(int i, MtkTvCecTimeInfoBase mtkTvCecTimeInfoBase) {
        if (!is_emulator()) {
            return TVNative.setTimer_native(i, mtkTvCecTimeInfoBase);
        }
        Log.d(TAG, "setTimer_native called...\n");
        return 0;
    }

    public static int setUARTSerialOperationMode_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.setUARTSerialOperationMode_native(i, i2);
        }
        Log.d(TAG, "setUARTSerialOperationMode_native called...\n");
        return 0;
    }

    public static int setUARTSerialSetting_native(int i, int[] iArr) {
        if (!is_emulator()) {
            return TVNative.setUARTSerialSetting_native(i, iArr);
        }
        Log.d(TAG, "setUARTSerialSetting_native called...\n");
        return 0;
    }

    public static int setUSMovieRatingSettingInfo_native(int i) {
        if (!is_emulator()) {
            return TVNative.setUSMovieRatingSettingInfo_native(i);
        }
        Log.d(TAG, "setUSMovieRatingSettingInfo_native called...\n");
        return 0;
    }

    public static int setUSTvRatingSettingInfo_native(MtkTvUSTvRatingSettingInfoBase mtkTvUSTvRatingSettingInfoBase) {
        if (!is_emulator()) {
            return TVNative.setUSTvRatingSettingInfo_native(mtkTvUSTvRatingSettingInfoBase);
        }
        Log.d(TAG, "setUSTvRatingSettingInfo_native called...\n");
        return 0;
    }

    public static int setUserCtrlPressed_native(MtkTvCecBase.CecUserCtrlInfo cecUserCtrlInfo) {
        if (!is_emulator()) {
            return TVNative.setUserCtrlPressed_native(cecUserCtrlInfo);
        }
        Log.d(TAG, "setUserCtrlPressed_native called...\n");
        return 0;
    }

    public static int setUserCtrlReleased_native(MtkTvCecBase.CecUserCtrlInfo cecUserCtrlInfo) {
        if (!is_emulator()) {
            return TVNative.setUserCtrlReleased_native(cecUserCtrlInfo);
        }
        Log.d(TAG, "setUserCtrlReleased_native called...\n");
        return 0;
    }

    public static void setUtcTime_native(long j) {
        if (is_emulator()) {
            Log.d(TAG, "setUtcTime_native called...\n");
        } else {
            TVNative.setUtcTime_native(j);
        }
    }

    public static int setVendorCmdWithId_native(MtkTvCecBase.CecVndrCmdWithIdInfo cecVndrCmdWithIdInfo) {
        if (!is_emulator()) {
            return TVNative.setVendorCmdWithId_native(cecVndrCmdWithIdInfo);
        }
        Log.d(TAG, "setVendorCmdWithId_native called...\n");
        return 0;
    }

    public static int setVideoInfoData_native(int i, MtkTvVideoInfoBase mtkTvVideoInfoBase) {
        if (!is_emulator()) {
            return TVNative.setVideoInfoData_native(i, mtkTvVideoInfoBase);
        }
        Log.d(TAG, "setVideoBackLight_native...\n");
        return 0;
    }

    public static int setVideoInfoValue_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.setVideoInfoValue_native(i, i2);
        }
        Log.d(TAG, "setVideoBackLight_native...\n");
        return 0;
    }

    public static int setVolume_native(int i) {
        if (!is_emulator()) {
            return TVNative.setVolume_native(i);
        }
        Log.d(TAG, "setVolume_native called");
        return 0;
    }

    public static boolean setWifiWolCtl_native(boolean z) {
        if (!is_emulator()) {
            return TVNative.setWifiWolCtl_native(z);
        }
        Log.d(TAG, "setWifiWolCtl_native called...\n");
        return true;
    }

    public static int sifReadMultipleSubAddr(int i, int i2, byte b2, byte[] bArr, byte[] bArr2) {
        if (!is_emulator()) {
            return TVNative.sifReadMultipleSubAddr_native(i, i2, b2, bArr, bArr2);
        }
        Log.d(TAG, "sifReadMultipleSubAddr called...\n");
        return 0;
    }

    public static int sifWriteMultipleSubAddr(int i, int i2, byte b2, byte[] bArr, byte[] bArr2) {
        if (!is_emulator()) {
            return TVNative.sifWriteMultipleSubAddr_native(i, i2, b2, bArr, bArr2);
        }
        Log.d(TAG, "sifWriteMultipleSubAddr called...\n");
        return 0;
    }

    public static int slideForOnePartChannelId_native(int i, int i2, int i3) {
        if (!is_emulator()) {
            return TVNative.slideForOnePartChannelId_native(i, i2, i3);
        }
        Log.d(TAG, "getChannelListByMask_native called...\n");
        return 0;
    }

    public static void softKeyboardInit_native() {
        if (is_emulator()) {
            Log.d(TAG, "softKeyboardInit_native called...\n");
        } else {
            TVNative.softKeyboardInit_native();
        }
    }

    public static int startApplication_native(String str) {
        if (!is_emulator()) {
            return TVNative.startApplication_native(str);
        }
        Log.d(TAG, "startApplication_native called ...\n");
        return 0;
    }

    public static int startCamScan_navtive(int i, boolean z) {
        if (!is_emulator()) {
            return TVNative.startCamScan_navtive(i, z);
        }
        Log.d(TAG, "startCamScan_navtive called...");
        return 0;
    }

    public static int startDownloadFirmware_native(MtkTvUpgradeDeliveryTypeBase mtkTvUpgradeDeliveryTypeBase, String str, String str2) {
        if (!is_emulator()) {
            return TVNative.startDownloadFirmware_native(mtkTvUpgradeDeliveryTypeBase, str, str2);
        }
        Log.d(TAG, "startDownloadFirmware_native called ...");
        return 0;
    }

    public static int startDownload_native() {
        if (!is_emulator()) {
            return TVNative.startDownload_native();
        }
        Log.d(TAG, "startDownload_native called...");
        return 0;
    }

    public static int startFlash_native() {
        if (!is_emulator()) {
            return TVNative.startFlash_native();
        }
        Log.d(TAG, "startFlash_native called...");
        return 0;
    }

    public static int startGinga_native() {
        if (!is_emulator()) {
            return TVNative.startGinga_native();
        }
        Log.d(TAG, "startGinga_native called ...\n");
        return 0;
    }

    public static int startJumpChannel_native() {
        if (!is_emulator()) {
            return TVNative.startJumpChannel_native();
        }
        Log.d(TAG, "startJumpChannel_native called...");
        return 0;
    }

    public static void startLanB2BWorkThread_native() {
        if (is_emulator()) {
            Log.d(TAG, "[B2B]startLanB2BWorkThread_native called");
        } else {
            TVNative.startLanB2BWorkThread_native();
            Log.v(TAG, "[B2B] TVNative.startLanB2BWorkThread_native called");
        }
    }

    public static int startMainVideo_native(int i, MtkTvMultiViewBase.Region_Info_T region_Info_T) {
        if (!is_emulator()) {
            return TVNative.startMainVideo_native(i, region_Info_T);
        }
        Log.d(TAG, "startMainVideo_native called");
        return 0;
    }

    public static int startManualDetect_native() {
        if (!is_emulator()) {
            return TVNative.startManualDetect_native();
        }
        Log.d(TAG, "startManualDetect_native called...");
        return 0;
    }

    public static int startRebootUpgrade_native(MtkTvUpgradeDeliveryTypeBase mtkTvUpgradeDeliveryTypeBase) {
        if (!is_emulator()) {
            return TVNative.startRebootUpgrade_native(mtkTvUpgradeDeliveryTypeBase);
        }
        Log.d(TAG, "startRebootUpgrade_native called ...");
        return 0;
    }

    public static int startScan_native(int i, int i2, boolean z) {
        if (!is_emulator()) {
            return TVNative.startScan_native(i, i2, z);
        }
        Log.d(TAG, "startScan_native called...\n");
        return 0;
    }

    public static void startSerialWorkThread_native() {
        if (is_emulator()) {
            Log.d(TAG, "startSerialWorkThread_native called");
        } else {
            TVNative.startSerialWorkThread_native();
            Log.v(TAG, " TVNative.startSerialWorkThread_native called");
        }
    }

    public static int startSubVideo_native(int i, MtkTvMultiViewBase.Region_Info_T region_Info_T) {
        if (!is_emulator()) {
            return TVNative.startSubVideo_native(i, region_Info_T);
        }
        Log.d(TAG, "startSubVideo_native called");
        return 0;
    }

    public static void startTestB2BHWPortWorkThread_native() {
        if (is_emulator()) {
            Log.d(TAG, "[B2B]startTestB2BHWPortWorkThread_native called");
        } else {
            TVNative.startTestB2BHWPortWorkThread_native();
            Log.v(TAG, "[B2B] TVNative.startTestB2BHWPortWorkThread called");
        }
    }

    public static int startTimeshift_native() {
        if (!is_emulator()) {
            return TVNative.startTimeshift_native();
        }
        Log.d(TAG, "startTimeshift_native called...\n");
        return 0;
    }

    public static int startUpgrade_native(MtkTvUpgradeDeliveryTypeBase mtkTvUpgradeDeliveryTypeBase, boolean z) {
        if (!is_emulator()) {
            return TVNative.startUpgrade_native(mtkTvUpgradeDeliveryTypeBase, z);
        }
        Log.d(TAG, "startUpgrade_native called ...");
        return 0;
    }

    public static int stopApplication_native(String str) {
        if (!is_emulator()) {
            return TVNative.stopApplication_native(str);
        }
        Log.d(TAG, "stopApplication_native called ...\n");
        return 0;
    }

    public static int stopDownload_native() {
        if (!is_emulator()) {
            return TVNative.stopDownload_native();
        }
        Log.d(TAG, "stopDownload_native called...");
        return 0;
    }

    public static int stopGinga_native() {
        if (!is_emulator()) {
            return TVNative.stopGinga_native();
        }
        Log.d(TAG, "stopGinga_native called ...\n");
        return 0;
    }

    public static int stopMainVideo_native() {
        if (!is_emulator()) {
            return TVNative.stopMainVideo_native();
        }
        Log.d(TAG, "stopMainVideo_native called");
        return 0;
    }

    public static int stopManualDetect_native() {
        if (!is_emulator()) {
            return TVNative.stopManualDetect_native();
        }
        Log.d(TAG, "stopManualDetect_native called...");
        return 0;
    }

    public static int stopSubVideo_native() {
        if (!is_emulator()) {
            return TVNative.stopSubVideo_native();
        }
        Log.d(TAG, "stopSubVideo_native called");
        return 0;
    }

    public static int stopTimeshift_native(MtkTvTimeshiftBase.TimeshiftStopFlag timeshiftStopFlag) {
        if (!is_emulator()) {
            return TVNative.stopTimeshift_native(timeshiftStopFlag);
        }
        Log.d(TAG, "getRecordStatus_native called...\n");
        return 0;
    }

    public static int storeFavoritelistChannel_native() {
        if (!is_emulator()) {
            return TVNative.storeFavoritelistChannel_native();
        }
        Log.d(TAG, "storeFavoritelistChannel_native called...\n");
        return 0;
    }

    public static int swapChannelInfo_native(int i, int i2, int i3) {
        if (!is_emulator()) {
            return TVNative.swapChannelInfo_native(i, i2, i3);
        }
        Log.d(TAG, "swapChannelInfo_native called...\n");
        return 0;
    }

    public static int swapDigitalFavorites_native(int i, int i2, int i3, int i4) {
        if (!is_emulator()) {
            return TVNative.swapDigitalFavorites_native(i, i2, i3, i4);
        }
        Log.d(TAG, "swapDigitalFavorites_native called...\n");
        return 0;
    }

    public static int swapFavoritelistByIndex_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNative.swapFavoritelistByIndex_native(i, i2);
        }
        Log.d(TAG, "swapFavoritelistByIndex_native called...\n");
        return 0;
    }

    public static int switchInputToTvSource_native() {
        if (!is_emulator()) {
            return TVNative.switchInputToTvSource_native();
        }
        Log.d(TAG, "switchInputToTvSource_native called...\n");
        return 0;
    }

    public static int syncStop_native(String str, boolean z) {
        if (!is_emulator()) {
            return TVNative.syncStop_native(str, z);
        }
        Log.d(TAG, "syncStop_native called...\n");
        return 0;
    }

    public static boolean teletextHasTopInfo_native() {
        if (!is_emulator()) {
            return TVNative.teletextHasTopInfo_native();
        }
        Log.d(TAG, "teletextHasTopInfo_native called ...\n");
        return false;
    }

    public static int temp_unblock_native(int i, boolean z) {
        if (!is_emulator()) {
            return TVNative.temp_unblock_native(i, z);
        }
        Log.d(TAG, "temp_unblock_native called");
        return 0;
    }

    public static int transferExternalUIStatus_native(MtkTvExternalUIStatusBase mtkTvExternalUIStatusBase) {
        if (!is_emulator()) {
            return TVNative.transferExternalUIStatus_native(mtkTvExternalUIStatusBase);
        }
        Log.d(TAG, "transferExternalUIStatus_native called...\n");
        return 0;
    }

    public static int trickPlayTimeshift_native(double d2) {
        if (!is_emulator()) {
            return TVNative.trickPlayTimeshift_native(d2);
        }
        Log.d(TAG, "trickPlayTimeshift_native called...\n");
        return 0;
    }

    public static int triggerUpgrade_native(MtkTvUpgradeDeliveryTypeBase mtkTvUpgradeDeliveryTypeBase) {
        if (!is_emulator()) {
            return TVNative.triggerUpgrade_native(mtkTvUpgradeDeliveryTypeBase);
        }
        Log.d(TAG, "triggerUpgrade_native called ...");
        return 0;
    }

    public static int tunerFacQuery_navtive(boolean z, List<String> list, List<String> list2) {
        if (is_emulator()) {
            Log.d(TAG, "tunerFacQuery Emulator called");
            return 0;
        }
        Log.d(TAG, "tunerFacQuery  called");
        return TVNative.tunerFacQuery_navtive(z, list, list2);
    }

    public static int unmountPerm_native() {
        if (!is_emulator()) {
            return TVNative.unmountPerm_native();
        }
        Log.d(TAG, "unmountPerm_native called...\n");
        return -1;
    }

    public static int unregisterDefaultCallback_native() {
        if (!mIsregisterCallback || is_emulator()) {
            Log.d(TAG, "unregisterDefaultCallback_native called");
            return 0;
        }
        mIsregisterCallback = false;
        return TVNative.unregisterDefaultCallback_native();
    }

    public static int unselectAudio_native() {
        if (!is_emulator()) {
            return TVNative.unselectAudio_native();
        }
        Log.d(TAG, "unselectAudio_native...\n");
        return 0;
    }

    public static void updateBooking_native() {
        if (is_emulator()) {
            Log.d(TAG, "updateBooking_native called...\n");
        } else {
            TVNative.updateBooking_native();
        }
    }

    public static int updateCIKeyEx_navtive(int i) {
        if (is_emulator()) {
            Log.d(TAG, "updateCIKeyEx_navtive called ...\n");
            return 0;
        }
        Log.d(TAG, "updateCIKeyEx_navtive ...\n");
        return TVNative.updateCIKeyEx_navtive(i);
    }

    public static int updateCIKey_navtive() {
        if (!is_emulator()) {
            return TVNative.updateCIKey_navtive();
        }
        Log.d(TAG, "updateCIKey_navtive called ...\n");
        return 0;
    }

    public static int updateOverscanIni_native() {
        if (!is_emulator()) {
            return TVNative.updateOverscanIni_native();
        }
        Log.d(TAG, "updateOverscanIni_native called ...\n");
        return 0;
    }

    public static int updateSatlRecord_native(int i, MtkTvDvbsConfigInfoBase mtkTvDvbsConfigInfoBase, boolean z) {
        if (!is_emulator()) {
            return TVNative.updateSatlRecord_native(i, mtkTvDvbsConfigInfoBase, z);
        }
        Log.d(TAG, "updateSatlRecord_native called...\n");
        return 0;
    }

    public static int warningStartCC_native(boolean z) {
        if (!is_emulator()) {
            return TVNative.warningStartCC_native(z);
        }
        Log.d(TAG, "warningStartCC_native called ...\n");
        return 0;
    }

    public static int warningStartGingaApp_native(boolean z) {
        if (!is_emulator()) {
            return TVNative.warningStartGingaApp_native(z);
        }
        Log.d(TAG, "warningStartGingaApp_native called ...\n");
        return 0;
    }

    public static int writeToExternalControl_native(int i, byte[] bArr, int i2, int i3) {
        if (!is_emulator()) {
            return TVNative.writeToExternalControl_native(i, bArr, i2, i3);
        }
        Log.d(TAG, "getTvKeyName_native called");
        return -1;
    }

    public static boolean addBooking_native(int i, long j, int i2, String str) {
        if (is_emulator()) {
            Log.d(TAG, "addBooking_native called...\n");
            return true;
        }
        return TVNative.addBooking_native(i, j, i2, str);
    }

    public static int changeInputSourcebySourceid_native(int i, String str) {
        if (is_emulator()) {
            Log.d(TAG, "changeInputSourcebySourceid_native called");
            return 0;
        }
        return TVNative.changeInputSourcebySourceid_native(i, str);
    }

    public static int channelSelect_native(MtkTvChannelInfoBase mtkTvChannelInfoBase, boolean z, int i) {
        if (is_emulator()) {
            Log.d(TAG, "channelSelect_native called...\n");
            return 0;
        }
        return TVNative.channelSelect_native(mtkTvChannelInfoBase, z, i);
    }

    public static int decrVolume_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "decrVolume_native called");
            return 0;
        }
        return TVNative.decrVolume_native(i);
    }

    public static String getCurrentInputSourceName_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "getCurrentInputSourceName_native called");
            return null;
        }
        return TVNative.getCurrentInputSourceName_native(str);
    }

    public static boolean getMute_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "setMute_native called");
            return false;
        }
        return TVNative.getMute_native(i);
    }

    public static long getPvrBrowserItemChannelId_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "getPvrBrowserItemChannelId_native called...\n");
            return 0L;
        }
        return TVNative.getPvrBrowserItemChannelId_native(str);
    }

    public static int getPvrBrowserItemChannelLock_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, " getPvrBrowserItemChannelLock called...\n");
            return 0;
        }
        return TVNative.getPvrBrowserItemChannelLock_native(str);
    }

    public static String getPvrBrowserItemChannelName_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getPvrBrowserItemChannelName_native called...\n");
            return "";
        }
        return TVNative.getPvrBrowserItemChannelName_native(i);
    }

    public static String getPvrBrowserItemDate_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "getPvrBrowserItemDate_native called...\n");
            return "";
        }
        return TVNative.getPvrBrowserItemDate_native(str);
    }

    public static long getPvrBrowserItemDuration_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getPvrBrowserItemDuration_native called...\n");
            return 0L;
        }
        return TVNative.getPvrBrowserItemDuration_native(i);
    }

    public static long getPvrBrowserItemFirstRatingRange_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "getPvrBrowserItemFirstRatingRange_native called...\n");
            return 0L;
        }
        return TVNative.getPvrBrowserItemFirstRatingRange_native(str);
    }

    public static int getPvrBrowserItemInputLock_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, " getPvrBrowserItemInputLock called...\n");
            return 0;
        }
        return TVNative.getPvrBrowserItemInputLock_native(str);
    }

    public static int getPvrBrowserItemMajorChannelNum_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "getPvrBrowserItemMajorChannelNum_native called...\n");
            return 0;
        }
        return TVNative.getPvrBrowserItemMajorChannelNum_native(str);
    }

    public static int getPvrBrowserItemMinorChannelNum_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "getPvrBrowserItemMinorChannelNum_native called...\n");
            return 0;
        }
        return TVNative.getPvrBrowserItemMinorChannelNum_native(str);
    }

    public static String getPvrBrowserItemProgramName_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getPvrBrowserItemProgramName_native called...\n");
            return "";
        }
        return TVNative.getPvrBrowserItemProgramName_native(i);
    }

    public static long getPvrBrowserItemRentention_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "getPvrBrowserItemRentention_native called...\n");
            return 0L;
        }
        return TVNative.getPvrBrowserItemRentention_native(str);
    }

    public static long getPvrBrowserItemStartTime_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getPvrBrowserItemStartTime_native called...\n");
            return 0L;
        }
        return TVNative.getPvrBrowserItemStartTime_native(i);
    }

    public static long getPvrBrowserItemStopReason_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getPvrBrowserItemStopReason_native called...\n");
            return 0L;
        }
        return TVNative.getPvrBrowserItemStopReason_native(i);
    }

    public static String getPvrBrowserItemTime_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "getPvrBrowserItemTime_native called...\n");
            return "";
        }
        return TVNative.getPvrBrowserItemTime_native(str);
    }

    public static String getPvrBrowserItemWeek_native(String str) {
        if (is_emulator()) {
            Log.d(TAG, "getPvrBrowserItemWeek_native called...\n");
            return "";
        }
        return TVNative.getPvrBrowserItemWeek_native(str);
    }

    public static int getVolume_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "getVolume_native called");
            return 0;
        }
        return TVNative.getVolume_native(i);
    }

    public static int incrVolume_native(int i) {
        if (is_emulator()) {
            Log.d(TAG, "incrVolume_native called");
            return 0;
        }
        return TVNative.incrVolume_native(i);
    }

    public static int setMute_native(int i, boolean z) {
        if (is_emulator()) {
            Log.d(TAG, "setMute_native called");
            return 0;
        }
        return TVNative.setMute_native(i, z);
    }

    public static int setVolume_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "setVolume_native called");
            return 0;
        }
        return TVNative.setVolume_native(i, i2);
    }

    public static int stopTimeshift_native() {
        if (is_emulator()) {
            Log.d(TAG, "stopTimeshift_native called...\n");
            return 0;
        }
        return TVNative.stopTimeshift_native();
    }

    public static int AppTVupdatedSysStatus_native(String str, int i) {
        if (is_emulator()) {
            Log.d(TAG, "AppTVupdatedSysStatus_native Emulator called");
            return 0;
        }
        Log.d(TAG, "AppTVupdatedSysStatus_native  called");
        return TVNative.AppTVupdatedSysStatus_native(str, i);
    }
}
