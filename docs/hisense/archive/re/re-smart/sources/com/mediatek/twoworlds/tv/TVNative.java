package com.mediatek.twoworlds.tv;

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
public class TVNative {
    public static final String TAG = "TVNative";

    static {
        TvDebugLog.i("TVNative", "Load libcom_mediatek_twoworlds_tv_jni.so start !");
        System.loadLibrary("com_mediatek_twoworlds_tv_jni");
        TvDebugLog.i("TVNative", "Load libcom_mediatek_twoworlds_tv_jni.so OK !");
    }

    public static native boolean AppTVAutoClockPhasePostionCondSuccess_native(String str);

    public static native boolean AppTVAutoColorCondSuccess_native(String str);

    public static native int AppTVGetCapabilityEx_native(int i);

    public static native int AppTV_swapTmpUnlockInfo_native(int i);

    public static native int AppTVchangeFreq_native(String str, MtkTvFreqChgParamBase mtkTvFreqChgParamBase);

    public static native int AppTVgetMatchedChannel_native(int i, boolean z, int i2, int i3);

    public static native int AppTVsaveTimestamp_native(String str);

    public static native int AppTVsetAutoClockPhasePostion_native(String str);

    public static native int AppTVsetAutoColor_native(String str);

    public static native int AppTVsetFinetuneFreq_native(String str, int i, boolean z);

    public static native int AppTVsetVideoMute_native(String str, boolean z);

    public static native int AppTVunblockSvc_native(String str, boolean z);

    public static native int AppTVunlockService_native(String str);

    public static native int AppTVupdatedSysStatus_native(int i);

    public static native int AppTVupdatedSysStatus_native(String str, int i);

    public static native int ChangeModeforBuiltInVideoUpgrade_native();

    public static native int GamSendcmd_native(MtkTvGamCmdInfoBase mtkTvGamCmdInfoBase);

    public static native long GetConnAtrrAGC_native(String str);

    public static native int GetConnAttrBER_native(String str);

    public static native long GetConnAttrDBMSNR_native(String str);

    public static native long GetConnAttrUEC_native(String str);

    public static native String GetCurrentChannelServiceText_native();

    public static native boolean GetDivXHDSupport_native();

    public static native boolean GetDivXPlusSupport_native();

    public static native String GetDrmRegistrationCode_native();

    public static native long GetDrmUiHelpInfo_native();

    public static native int GetModulation_native(String str);

    public static native String GetRecordDisk_native();

    public static native int GetSignalBER_native(String str);

    public static native long GetSymRate_native(String str);

    public static native int GetVideoSrcTag3DType_native(String str);

    public static native int HighLevel_native(int i, int i2, int i3, int i4, int i5, int i6, int i7);

    public static native int ISDBCCEnable_native(boolean z);

    public static native int ISDBCCGetCCString_native();

    public static native int ISDBCCNextStream_native();

    public static native int PWDShow_native();

    public static native int ScanATSCExchangeData_native(int[] iArr);

    public static native int ScanCeExchangeData_native(int[] iArr);

    public static native int ScanDtmbExchangeData_native(int[] iArr);

    public static native int ScanDvbcExchangeData_native(int[] iArr);

    public static native String ScanDvbcGetStrData_native(int i);

    public static native int ScanDvbsExchangeData_native(int[] iArr);

    public static native String ScanDvbsgetUserMessage_native(String str);

    public static native int ScanDvbtExchangeData_native(int[] iArr);

    public static native int ScanISDBExchangeData_native(int[] iArr);

    public static native int ScanPalSecamExchangeData_native(int[] iArr);

    public static native void SendKeyClick_native(int i);

    public static native void SendKey_native(int i, int i2);

    public static native void SendMouseButton_native(int i, int i2);

    public static native void SendMouseMove_native(int i, int i2, int i3, int i4);

    public static native String SetDrmDeactivation_native();

    public static native boolean SetRecordDiskByHandle_native(int i, String str);

    public static native boolean SetRecordDisk_native(String str);

    public static native int SubtitleGetTracks_native(int[] iArr);

    public static native boolean VideoSrcIsProgressive_native(String str);

    public static native int acceptRestart_native();

    public static native int acceptSchedule_native();

    public static native int ackChooseAppDialog_native(String str, String str2, boolean z);

    public static native int ackDialog_native(int i);

    public static native int ackTrustDialog_native(boolean z);

    public static native boolean addBooking_native(int i, int i2);

    public static native boolean addBooking_native(int i, long j, int i2, String str);

    public static native int addConfigListener_native(String str);

    public static native int addFavoritelistChannelByIndex_native(int i);

    public static native int addFavoritelistChannel_native();

    public static native int analogCCEnable_native(boolean z);

    public static native int analogCCGetCcIndex_native();

    public static native int analogCCNextStream_native();

    public static native int analogCCSetCcVisible_native(boolean z);

    public static native int applyGpioStatus_native(int i, int i2);

    public static native int atscCCDemoSet_native(int i, int i2);

    public static native int atscCCEnable_native(boolean z);

    public static native int atscCCGetCCIndex_native();

    public static native int atscCCNextStream_native();

    public static native int atscCCSetCcVisible_native(boolean z);

    public static native int bisskeyClean_native(int i);

    public static native MtkTvBisskeyInfoBase bisskeyGetDefaultRecord_native();

    public static native int bisskeyIsSameRecord_native(MtkTvBisskeyInfoBase mtkTvBisskeyInfoBase, MtkTvBisskeyInfoBase mtkTvBisskeyInfoBase2);

    public static native int bisskeySetKeyForCurrentChannel_native();

    public static native int block_native(int i, boolean z);

    public static native int cancelCamScan_navtive(int i);

    public static native int cancelDownloadFirmware_native(MtkTvUpgradeDeliveryTypeBase mtkTvUpgradeDeliveryTypeBase);

    public static native int cancelScan_native();

    public static native int changeFreq_native(String str, MtkTvFreqChgParamBase mtkTvFreqChgParamBase);

    public static native int changeInputSourcebySourceid_native(int i);

    public static native int changeInputSourcebySourceid_native(int i, String str);

    public static native boolean changeRecordPBAudioByIndex_native(int i);

    public static native boolean changeRecordPBCaptionByIndex_native(int i);

    public static native int channelSelectByChannelId_native(int i, boolean z);

    public static native int channelSelectByChannelNumber_native(int i, int i2, boolean z);

    public static native int channelSelectByKeys_native(int i);

    public static native int channelSelectSilently_native(MtkTvChannelInfoBase mtkTvChannelInfoBase, int i, int i2);

    public static native int channelSelect_native(MtkTvChannelInfoBase mtkTvChannelInfoBase, boolean z);

    public static native int channelSelect_native(MtkTvChannelInfoBase mtkTvChannelInfoBase, boolean z, int i);

    public static native boolean checkAtscEventBlock_native(int i, int i2);

    public static native int[] checkEITStauts_native(int i, int i2);

    public static native boolean checkEventBlock_native(int i, int i2);

    public static native int checkFairPlayKey_native();

    public static native boolean checkPCLWakeupReasonToBGM_native();

    public static native boolean checkPWD_native(String str);

    public static native int checkPlayready40KeyBox_native();

    public static native int checkQDAKey_native();

    public static native boolean checkRecordRegisterFile_native(String str);

    public static native int checkWideVineKeyBox_native();

    public static native int cleanChannelList_native(int i, boolean z);

    public static native int clearActiveWindow_native();

    public static native int clearOadVersion_native();

    public static native int closeUARTSerial_native(int i);

    public static native long convertDTGToSeconds_native(MtkTvTimeFormatBase mtkTvTimeFormatBase);

    public static native long convertLocalTimeToMillis_native(MtkTvTimeFormatBase mtkTvTimeFormatBase);

    public static native void convertMillisToLocalTime_native(long j, MtkTvTimeFormatBase mtkTvTimeFormatBase);

    public static native int convertTime_native(int i, MtkTvTimeFormatBase mtkTvTimeFormatBase, MtkTvTimeFormatBase mtkTvTimeFormatBase2);

    public static native int convertUTCSecToDTG_native(long j, MtkTvTimeFormatBase mtkTvTimeFormatBase);

    public static native void createMonitorInst_native(byte b2);

    public static native int createSatlSnapshot_native(int i);

    public static native int createSnapshot_native(int i);

    public static native int decrVolume_native();

    public static native int decrVolume_native(int i);

    public static native int delBooking_native(int i);

    public static native int deleteChannelByBrdcstType_native(int i, int i2, boolean z);

    public static native void deleteMonitorInst_native(byte b2);

    public static native boolean deletePlaybackListItem_native(int i);

    public static native int deletePvrBrowserFileByIndex_native(int i);

    public static native int discoveryDevice_native(MtkTvCecDevDiscoveryInfoBase mtkTvCecDevDiscoveryInfoBase);

    public static native int drmpInitQDAForQdc_native();

    public static native int drmpKeyUpdate_native();

    public static native int drmpQdaFactoryResetWithReturn_native();

    public static native int drmpQdaFactoryReset_native();

    public static native int drmpQdaForceCleanup_native();

    public static native int drmpQdaStop_native();

    public static native void endLanB2BWorkThread_native();

    public static native void enterMMI_navtive(int i);

    public static native int enter_native();

    public static native int eraseCIKey_navtive();

    public static native int factoryCheckKey_native(String str);

    public static native int factoryCleanKey_native(String str);

    public static native String factoryReadFile_native(String str);

    public static native String factoryReadLog_native();

    public static native int factoryRemoveFile_native(String str);

    public static native int factoryRemovePlayReady25Key_native();

    public static native int factoryWriteAttestationKey_native(String str);

    public static native int factoryWriteFileAppend_native(String str, String str2, int i);

    public static native int factoryWriteFile_native(String str, String str2);

    public static native int factoryWriteKeyFinish_native();

    public static native int factoryWriteKey_native(String str, String str2);

    public static native int factoryWriteLog_native(String str);

    public static native int favoriteListAddTail_native(int i, int i2, int i3);

    public static native int favoriteListInsertMove_native(int i, int i2, int i3, int i4);

    public static native int freeEvent_native(int i);

    public static native int freeSatlSnapshot_native(int i);

    public static native int freeSnapshot_native(int i);

    public static native int fvpMtdtOperator_native(String str);

    public static native int getActiveSourceInfo_native(MtkTvCecActiveSourceBase mtkTvCecActiveSourceBase);

    public static native boolean getAdbStatus_native();

    public static native int[] getAllPictureMode_native();

    public static native int[] getAllScreenMode_native();

    public static native int[] getAllSoundEffect_native();

    public static native int getAllTvprovider_native(List<TvProviderChannelInfoBase> list);

    public static native int getApdText_native(int i, MtkTvAudioApdBase mtkTvAudioApdBase);

    public static native int getAppExtendedData_native(String str, MtkTvGingaAppExtendedDataBase mtkTvGingaAppExtendedDataBase);

    public static native int getApplicationInfoList_native(List<MtkTvGingaAppInfoBase> list);

    public static native int getApplicationInfo_native();

    public static native void getAudioAvailableRecord_native(List<TvProviderAudioTrackBase> list);

    public static native int getAudioDecType_native();

    public static native int getAudioFocus_native();

    public static native int getAudioInfoData_native(int i, MtkTvAudioInfoBase mtkTvAudioInfoBase);

    public static native int getAudioInfoValue_native(int i);

    public static native String getAudioInfo_native(int i, int i2);

    public static native void getAudioLang_native(MtkTvAVModeBase mtkTvAVModeBase);

    public static native boolean getBlockUnrated_native();

    public static native int getBookingChannelId_native(int i);

    public static native int getBookingCount_native();

    public static native int getBookingDeviceIndex_native(int i);

    public static native int getBookingEventId_native(int i);

    public static native String getBookingEventTitle_native(int i);

    public static native int getBookingGenre_native(int i);

    public static native int getBookingID_native(int i);

    public static native int getBookingInfoData_native(int i);

    public static native int getBookingRecordDelay_native(int i);

    public static native long getBookingRecordDuration_native(int i);

    public static native int getBookingRecordMode_native(int i);

    public static native int getBookingRepeatMode_native(int i);

    public static native int getBookingSourceType_native(int i);

    public static native long getBookingStartTime_native(int i);

    public static native int getBookingTunerType_native(int i);

    public static native int getBookingType_native(int i);

    public static native String getBookingUrlInfo_native(int i);

    public static native MtkTvTimeRawDataBase getBrdcstRawData_native();

    public static native long getBroadcastTime_native(MtkTvTimeFormatBase mtkTvTimeFormatBase);

    public static native int getCANEngRatingSettingInfo_native();

    public static native int getCANFreRatingSettingInfo_native();

    public static native int getCIHostID_navtive(int[] iArr);

    public static native boolean getCIKeyStatus_navtive();

    public static native String getCIKeyinfo_navtive();

    public static native int getCaSystemIDMatch_navtive(int i);

    public static native String getCamID_navtive(int i);

    public static native String getCamName_navtive(int i);

    public static native int getCamPinCaps_navtive();

    public static native String getCamPinCode_navtive(int i);

    public static native int getCamRatingValue_navtive(int i);

    public static native int getCamScanReqTypeACON_navtive(int i);

    public static native String getCaption_native(int i, int i2);

    public static native int getCecDevInfo_native(int i, MtkTvCecBase.CecDevInfo cecDevInfo);

    public static native int getCecDevListInfo_native(List<MtkTvCecBase.CecDevInfo> list);

    public static native int getChannelCountByBrdcst_native(int i, int i2, int i3);

    public static native int getChannelCountByFilter_native(int i, int i2);

    public static native int getChannelCountByMask_native(int i, int i2, int i3, int i4);

    public static native int getChannelCountByQueryInfo_native(int i, MtkTvChannelQuery mtkTvChannelQuery);

    public static native int getChannelInfoByChannelId_native(int i, MtkTvChannelInfoBase mtkTvChannelInfoBase);

    public static native MtkTvChannelInfoBase getChannelInfoBySvlRecId_native(int i, int i2);

    public static native int getChannelListByBrdcst_native(int i, int i2, int i3, int i4, List<MtkTvChannelInfoBase> list);

    public static native int getChannelListByFavoriteIdx_native(int i, int i2, int i3, boolean z, int i4, int i5, int i6, List<MtkTvChannelInfoBase> list);

    public static native int getChannelListByFilter_native(int i, int i2, int i3, int i4, int i5, List<MtkTvChannelInfoBase> list);

    public static native int getChannelListByMask_native(int i, int i2, int i3, int i4, int i5, int i6, int i7, List<MtkTvChannelInfoBase> list);

    public static native int getChannelListByQueryInfo_native(int i, MtkTvChannelQuery mtkTvChannelQuery, List<MtkTvChannelInfoBase> list, int i2);

    public static native int getChannelListMode_native();

    public static native int getChannelList_native(int i, List<MtkTvChannelInfoBase> list);

    public static native String getChannelLogo_native(int i, int i2);

    public static native String getChannelName_native(int i, int i2);

    public static native String getChannelNumber_native(int i, int i2);

    public static native int getChannelPumpVer_native(int i);

    public static native int getChannelRFInfo_native(int i, int i2, int i3);

    public static native int getChannelType_native(int i);

    public static native int getChgSource_native();

    public static native String getCmdExecResult_native(String str);

    public static native int[] getConfigIntArrayValue_native(int i, int i2, String str);

    public static native String getConfigString_native(String str);

    public static native int getConfigValue_native(int i, String str);

    public static native int getConnectAttr_native(String str, int i);

    public static native int getCrntOverscan_native(MtkTvScreenModeOverscan mtkTvScreenModeOverscan);

    public static native int getCrntRatingInfo_native(MtkTvRatingConvert2Goo mtkTvRatingConvert2Goo);

    public static native int[] getCurrentActiveWinChannelList_native();

    public static native long getCurrentActiveWinEndTime_native();

    public static native long getCurrentActiveWinStartTime_native();

    public static native int getCurrentAd_native(TvProviderAudioTrackBase tvProviderAudioTrackBase);

    public static native int getCurrentAudio_native(TvProviderAudioTrackBase tvProviderAudioTrackBase);

    public static native MtkTvChannelInfoBase getCurrentChannelFromNav_native(int i);

    public static native int getCurrentChannelId_native();

    public static native int getCurrentChannel_native(MtkTvChannelInfoBase mtkTvChannelInfoBase);

    public static native String getCurrentInputSourceName_native();

    public static native String getCurrentInputSourceName_native(String str);

    public static native long getCurrentPosition_native();

    public static native int getCurrentTeletextPage_native(MtkTvTeletextPageBase mtkTvTeletextPageBase);

    public static native int getCurrentVideoStreamInfo_native(MtkTvVideoStreamInfoBase mtkTvVideoStreamInfoBase);

    public static native int getDVBAgeRatingSetting_native();

    public static native int getDataInfo_native(MtkTvOADBase mtkTvOADBase, int i);

    public static native int getDigitalFavoritesList_native(int i, int i2, int i3, List<MtkTvChannelInfoBase> list);

    public static native int getDispRegionCapability_native(String str, MtkTvRegionCapability mtkTvRegionCapability);

    public static native byte[] getDrmpDeviceID_native();

    public static native boolean getEASAndroidLaunchStatus_native();

    public static native int getEASCurrentStatus_native(MtkTvEASParaBase mtkTvEASParaBase);

    public static native boolean getEarcCaps_native();

    public static native boolean getEarcStatus_native(int i);

    public static native int getEnqID_navtive();

    public static native boolean getEthernetWolCtl_native();

    public static native void getEventByIndex_native(int i, int i2, MtkTvEventInfoBase mtkTvEventInfoBase);

    public static native String getEventDetailByIndex_native(int i, int i2);

    public static native void getEventInfoByEventId_native(int i, int i2, MtkTvEventInfoBase mtkTvEventInfoBase);

    public static native int getEventListByChannelId_native(int i, long j, long j2, int i2, List<MtkTvEventInfoBase> list);

    public static native int getEventNumberInEIT_native(int i);

    public static native int getEventRatingMapById_native(int i, int i2, MtkTvRatingConvert2Goo mtkTvRatingConvert2Goo);

    public static native int getEventRatingMapByIndex_native(int i, int i2, MtkTvRatingConvert2Goo mtkTvRatingConvert2Goo);

    public static native void getEvent_native(int i, MtkTvEventInfoBase mtkTvEventInfoBase);

    public static native boolean getExternalDeviceHasSignal_native(MtkTvInputSourceBase.InputDeviceType inputDeviceType, int i);

    public static native int getFavoriteIdx_native(int i, int i2, int i3, int i4);

    public static native int getFavoritelistByFilter_native(List<MtkTvFavoritelistInfoBase> list);

    public static native String getFileBasePath_native();

    public static native int getFirmwareInfo_native(MtkTvUpgradeFirmwareInfoBase mtkTvUpgradeFirmwareInfoBase);

    public static native boolean getGingaScreenModeisEnable_native();

    public static native int getHostQuietTuneStatus_navtive(int i);

    public static native int getHostTuneBrdcstStatus_navtive(int i);

    public static native int getHostTuneStatus_navtive(int i);

    public static native int getISDBAgeRatingSetting_native();

    public static native int getISDBContentRatingSetting_native();

    public static native void getInfo_native(byte b2, int i, int[] iArr);

    public static native int getInputLabelIdx_native(int i);

    public static native String getInputLabelUserDefName_native(int i);

    public static native void getInputSourceAudioAvailableRecord_native(List<TvProviderAudioTrackBase> list);

    public static native int getInputSourceCurrentAudio_native(TvProviderAudioTrackBase tvProviderAudioTrackBase);

    public static native String getInputSourceNamebySourceid_native(int i);

    public static native int getInputSourceRecbyidx_native(int i, MtkTvInputSourceBase.InputSourceRecord inputSourceRecord);

    public static native int getInputSourceTotalNumber_native();

    public static native MtkTvParserIniInfoBase getIntConfigData_native(String str, String str2);

    public static native void getInternalScrnMode_native(MtkTvMHEG5Base mtkTvMHEG5Base);

    public static native String getIptChannelNumber_native(int i, int i2);

    public static native String getIptsCC_native(int i, int i2);

    public static native String getIptsName_native(int i, int i2);

    public static native String getIptsRating_native(int i, int i2);

    public static native String getIptsRslt_native(int i, int i2);

    public static native String getMacAddress_native();

    public static native int getMarketRegion();

    public static native int getMenuListID_navtive();

    public static native int getMhlPortNum_native();

    public static native int getMinMaxConfigValue_native(int i, String str);

    public static native String getMsg_native(int i, int i2);

    public static native boolean getMute_native();

    public static native boolean getMute_native(int i);

    public static native String getNextProgCategoryIdx_native(int i, int i2);

    public static native String getNextProgDetail_native(int i, int i2);

    public static native String getNextProgSecondaryTitle_native(int i, int i2);

    public static native String getNextProgTime_native(int i, int i2);

    public static native String getNextProgTitle_native(int i, int i2);

    public static native String getNextRating_native(int i, int i2);

    public static native int getOpenVCHIPInfo_native(MtkTvOpenVCHIPParaBase mtkTvOpenVCHIPParaBase, MtkTvOpenVCHIPInfoBase mtkTvOpenVCHIPInfoBase);

    public static native int getOpenVCHIPSettingInfo_native(MtkTvOpenVCHIPSettingInfoBase mtkTvOpenVCHIPSettingInfoBase);

    public static native int getPFEventInfoByChannel_native(int i, boolean z, MtkTvEventInfoBase mtkTvEventInfoBase);

    public static native int getPOPTunerFocus_native();

    public static native int getPclWakeupSetup_native();

    public static native void getPfgInfo_native(MtkTvMHEG5Base mtkTvMHEG5Base);

    public static native int getPictureMode_native();

    public static native int getPlaybackItemCount_native();

    public static native int getPowerOnStatus_native();

    public static native String getProfileISO639LangCode_navtive(int i);

    public static native String getProfileName_navtive(int i);

    public static native int getProfileResourceIsOpen_navtive(int i);

    public static native int getProfileSupport_navtive(int i, int i2);

    public static native int getProfileValid_navtive(int i);

    public static native String getProgCategoryIdx_native(int i, int i2);

    public static native String getProgCategory_native(int i, int i2);

    public static native String getProgDetailPageIdx_native(int i, int i2);

    public static native String getProgDetail_native(int i, int i2);

    public static native String getProgSecondaryTitle_native(int i, int i2);

    public static native String getProgTime_native(int i, int i2);

    public static native String getProgTitle_native(int i, int i2);

    public static native long getPvrBrowserItemChannelId_native(int i);

    public static native long getPvrBrowserItemChannelId_native(String str);

    public static native int getPvrBrowserItemChannelLock_native(int i);

    public static native int getPvrBrowserItemChannelLock_native(String str);

    public static native String getPvrBrowserItemChannelName_native(int i);

    public static native String getPvrBrowserItemChannelName_native(String str);

    public static native int getPvrBrowserItemCount_native();

    public static native String getPvrBrowserItemDate_native(int i);

    public static native String getPvrBrowserItemDate_native(String str);

    public static native long getPvrBrowserItemDuration_native(int i);

    public static native long getPvrBrowserItemDuration_native(String str);

    public static native long getPvrBrowserItemFirstRatingRange_native(int i);

    public static native long getPvrBrowserItemFirstRatingRange_native(String str);

    public static native int getPvrBrowserItemInputLock_native(int i);

    public static native int getPvrBrowserItemInputLock_native(String str);

    public static native int getPvrBrowserItemMajorChannelNum_native(int i);

    public static native int getPvrBrowserItemMajorChannelNum_native(String str);

    public static native int getPvrBrowserItemMinorChannelNum_native(int i);

    public static native int getPvrBrowserItemMinorChannelNum_native(String str);

    public static native String getPvrBrowserItemPath_native(int i);

    public static native String getPvrBrowserItemProgramName_native(int i);

    public static native String getPvrBrowserItemProgramName_native(String str);

    public static native long getPvrBrowserItemRentention_native(int i);

    public static native long getPvrBrowserItemRentention_native(String str);

    public static native long getPvrBrowserItemStartTime_native(int i);

    public static native long getPvrBrowserItemStartTime_native(String str);

    public static native long getPvrBrowserItemStopReason_native(int i);

    public static native long getPvrBrowserItemStopReason_native(String str);

    public static native String getPvrBrowserItemTime_native(int i);

    public static native String getPvrBrowserItemTime_native(String str);

    public static native String getPvrBrowserItemWeek_native(int i);

    public static native String getPvrBrowserItemWeek_native(String str);

    public static native int getPvrBrowserRecordingFileCount_native();

    public static native String getPvrBrowserRecordingFileName_native(int i);

    public static native boolean getRatingEnable_native();

    public static native String getRating_native(int i, int i2);

    public static native MtkTvBisskeyInfoBase getRecordByBslRecId_native(int i, int i2);

    public static native MtkTvBisskeyInfoBase getRecordByHeader_native(int i, MtkTvBisskeyHeader mtkTvBisskeyHeader, int i2);

    public static native MtkTvBisskeyInfoBase getRecordByIndex_native(int i, int i2);

    public static native int getRecordModeByHandle_native(int i);

    public static native int getRecordNumByHeader_native(int i, MtkTvBisskeyHeader mtkTvBisskeyHeader);

    public static native int getRecordPBAudioNum_native();

    public static native int getRecordPBCaptionNum_native();

    public static native String getRecordPBDetailsInformation_native(String str);

    public static native String getRecordPBItemChannelName_native(int i);

    public static native long getRecordPBItemDuration_native(int i);

    public static native int getRecordPBItemMajorChannelNum_native(int i);

    public static native int getRecordPBItemMinorChannelNum_native(int i);

    public static native String getRecordPBItemPath_native(int i);

    public static native String getRecordPBItemProgramName_native(int i);

    public static native int getRecordPBSpeed_native();

    public static native MtkTvTimeshiftBase.TimeshiftRecordStatus getRecordStatus_native();

    public static native String getRecordingFileByHandle_native(int i);

    public static native String getRecordingFile_native();

    public static native int getRecordingPosition();

    public static native int getRecordingPositionByHandle(int i);

    public static native int getRecordsNumber_native(int i);

    public static native int getSasCbctState_navtive(int i);

    public static native int getSasItvState_navtive(int i);

    public static native int getSatlNumRecs_native(int i);

    public static native int getSatlRecordByRecIdx_native(int i, int i2, List<MtkTvDvbsConfigInfoBase> list);

    public static native int getSatlRecord_native(int i, int i2, List<MtkTvDvbsConfigInfoBase> list);

    public static native int getScheduleInfo_native(MtkTvOADBase mtkTvOADBase);

    public static native int getScreenMode_native();

    public static native int getScreenOutputDispRect_native(String str, MtkTvRectangle mtkTvRectangle);

    public static native int getScreenSourceRect_native(String str, MtkTvRectangle mtkTvRectangle);

    public static native int getScrnSvrMsgID_native();

    public static native int getSelinuxEnvironment_native(String str);

    public static native String getSerialNumber_native();

    public static native int getSignalLevel_native();

    public static native int getSignalQuality_native();

    public static native int getSleepTimerRemainingTime_native();

    public static native int getSleepTimer_native(boolean z);

    public static native boolean getSlotActive_navtive(int i);

    public static native int getSlotNum_navtive();

    public static native int getSoundEffect_native();

    public static native int getSrcVideoResolution_native(String str, MtkTvSrcVideoResolution mtkTvSrcVideoResolution);

    public static native long getStartPosition_native();

    public static native long getStorageFreeSize_native();

    public static native long getStorageSize_native();

    public static native MtkTvParserIniInfoBase getStringConfigData_native(String str, String str2);

    public static native String getSysVersion_native(int i, String str);

    public static native String getTVSrc_native(int i, int i2);

    public static native int getTeletextTopBlockList_native(List<MtkTvTeletextTopBlockBase> list);

    public static native int getTeletextTopGroupList_native(MtkTvTeletextTopBlockBase mtkTvTeletextTopBlockBase, List<MtkTvTeletextTopGroupBase> list);

    public static native int getTeletextTopPageList_native(MtkTvTeletextTopGroupBase mtkTvTeletextTopGroupBase, List<MtkTvTeletextTopPageBase> list);

    public static native long getTimeOffset_native();

    public static native int getTimeSyncSource_native();

    public static native long getTimeZone_native();

    public static native String getTimer_native(int i, int i2);

    public static native int getTimeshiftDeviceStatus_native();

    public static native long getTimeshiftDuration_native();

    public static native int getTimeshiftErrorID_native();

    public static native long getTimeshiftPosition_native();

    public static native int getTimeshiftRegisterDeviceSchedule_native();

    public static native int getTvproviderBySvlId_native(int i, List<TvProviderChannelInfoBase> list);

    public static native int getTvproviderBySvlRecId_native(int i, int i2, TvProviderChannelInfoBase tvProviderChannelInfoBase);

    public static native int getTvproviderOcl_native(List<TvProviderChannelInfoBase> list);

    public static native int getUARTSerialOperationMode_native(int i, int[] iArr);

    public static native int getUARTSerialSetting_native(int i, int[] iArr);

    public static native int getUIMode();

    public static native int getUSMovieRatingSettingInfo_native();

    public static native boolean getUSTVGrougByChild_native();

    public static native int getUSTvRatingSettingInfo_native(MtkTvUSTvRatingSettingInfoBase mtkTvUSTvRatingSettingInfoBase);

    public static native long getUtcTime_native();

    public static native int getVideoInfoData_native(int i, MtkTvVideoInfoBase mtkTvVideoInfoBase);

    public static native int getVideoInfoValue_native(int i);

    public static native String getVideoInfo_native(int i, int i2);

    public static native int getVideoSrcRegionCapability_native(String str, MtkTvRegionCapability mtkTvRegionCapability);

    public static native String getVirtualChannelInfo_navtive(int i);

    public static native int getVolume_native();

    public static native int getVolume_native(int i);

    public static native int getWakeUpIrKey_native();

    public static native int getWakeUpReason_native();

    public static native int getWebServiceClient_native(List<MtkTvGingaWebServiceClientInfoBase> list);

    public static native boolean getWifiWolCtl_native();

    public static native int hbbtvExchangeData_native(int[] iArr);

    public static native int hbbtvSetAudioDescription_native(int i);

    public static native int hbbtvSetDefaultAudioLang_native(String str);

    public static native int hbbtvSetDefaultSubtitleLang_native(String str, int i);

    public static native int hbbtvStmGetAudioCount_native();

    public static native String hbbtvStmGetAudioLang_native(int i);

    public static native int hbbtvStmGetAudio_native();

    public static native int hbbtvStmSetAudioIndex_native(int i);

    public static native int incrVolume_native();

    public static native int incrVolume_native(int i);

    public static native int inputSyncStop_native(String str, boolean z);

    public static native int installQDAKey_native(String str);

    public static native boolean isAutoDetectPlugStatus_native(int i);

    public static native boolean isBGMapplicable_native(boolean z);

    public static native boolean isBlockEx_native(int i);

    public static native boolean isBlock_native(int i);

    public static native boolean isCamInstalled_native();

    public static native boolean isCaptureLogo_native();

    public static native boolean isChannelNumberExsit_native(int i, int i2);

    public static native int isConfigEnabled_native(int i, String str);

    public static native int isConfigItemsEnabled_native(int i, String str, MtkTvConfigBase mtkTvConfigBase);

    public static native int isConfigVisible_native(int i, String str);

    public static native int isDeviceExist_native(int i);

    public static native boolean isDigitKeysHandled_native();

    public static native boolean isDisplayADAtmos_native(int i, int i2);

    public static native boolean isDisplayADEarIcon_native(int i, int i2);

    public static native boolean isDisplayADEyeIcon_native(int i, int i2);

    public static native boolean isDisplayBanner_native(int i, int i2);

    public static native boolean isDisplayCaptionIcon_native(int i, int i2);

    public static native boolean isDisplayFavIcon_native(int i, int i2);

    public static native boolean isDisplayFrmCH_native(int i, int i2);

    public static native boolean isDisplayFrmDetail_native(int i, int i2);

    public static native boolean isDisplayFrmInfo_native(int i, int i2);

    public static native boolean isDisplayGingaIcon_native(int i, int i2);

    public static native boolean isDisplayIptsLockIcon_native(int i, int i2);

    public static native boolean isDisplayLogoIcon_native(int i, int i2);

    public static native boolean isDisplayProgDetailDownIcon_native(int i, int i2);

    public static native boolean isDisplayProgDetailUpIcon_native(int i, int i2);

    public static native boolean isDisplayTVLockIcon_native(int i, int i2);

    public static native boolean isDisplayTtxIcon_native(int i, int i2);

    public static native boolean isFreeze_native(String str);

    public static native boolean isMultiviewInputsourceAvailable_native(int i, String str);

    public static native boolean isOpenVCHIPInfoAvailable_native();

    public static native boolean isOrigScrambleStrm_native(String str, int i);

    public static native boolean isReceivedTOT_native();

    public static native boolean isScaning_native();

    public static native boolean isSignalLoss_native();

    public static native boolean isSupport4DigitsChannelNo_native();

    public static native boolean isTimeEditable_native();

    public static native boolean isTimeOffsetEditable_native();

    public static native boolean isTimeZoneEditable_native();

    public static native boolean isTvRunning_native(String str);

    public static native boolean isZoomEnable_native();

    public static native String isdbCDTGetChannelLogo_native(int i, int i2, int i3);

    public static native int isfDataExport_native(String str, String str2);

    public static native int isfDataImport_native(String str);

    public static native String isfDataSearch_native(String str);

    public static native int launchHbbtv_native(String str, String str2, String str3);

    public static native int loadEventByEIT_native(int i, int i2);

    public static native int[] loadEvents_native(int i, long j, int i2);

    public static native int markWebServiceTrustClient_native(String str, boolean z);

    public static native boolean menuIsTvBlock_native();

    public static native int mountPerm_native();

    public static native int nextStream_native();

    public static native int notifyCecCompInfo_native(String str, int i, String str2);

    public static native void notifySoftKeyboardStatusChange_native(int i);

    public static native void notifyTextChange_native(String str);

    public static native int oneChannelListAddToMax_native(int i, int i2);

    public static native int oneChannelListArrange_native(int i, int i2);

    public static native int onlyChgFocus_native(String str);

    public static native int openUARTSerial_native(int i, int[] iArr, int[] iArr2);

    public static native int outputUARTSerial_native(int i, byte[] bArr);

    public static native boolean pauseTimeshift_native();

    public static native int playStream_native(int i);

    public static native boolean playTimeshift_native();

    public static native int popEnterNextTvMode_native();

    public static native int popGetFocusInfo_native(MtkTvPipPopFucusInfoBase mtkTvPipPopFucusInfoBase);

    public static native boolean popGetPipFocusId_native();

    public static native int popNextPipWindowPosition_native();

    public static native int popNextPipWindowSize_native();

    public static native int popSwapWindow_native();

    public static native int popSwitchAudioFocus_native();

    public static native int powerOnDeviceByLogicAddr_native(int i);

    public static native boolean queryConflict_native(int i, int i2);

    public static native int queryGpioStatus_native(int i, int i2);

    public static native int queryUpgradeResult_native(MtkTvUpgradeDeliveryTypeBase mtkTvUpgradeDeliveryTypeBase);

    public static native int recordClosePVR(int i);

    public static native int recordGetPVRSrcType();

    public static native int recordGetPVRSrcTypeByHandle(int i);

    public static native int recordGetPVRStatus();

    public static native int recordGetPVRStatusByHandle(int i);

    public static native long recordGetRecordingFilesize();

    public static native int recordOpenPVR(int i);

    public static native int recordOpenPVREx(int i, boolean z);

    public static native boolean recordPBPause_native();

    public static native int recordPBPlay_native(String str, boolean z);

    public static native boolean recordPBResume_native();

    public static native int recordPBSeek_native(long j);

    public static native boolean recordPBStop_native();

    public static native int recordPB_TrickPlay_native(double d2);

    public static native long recordPB_getDuration_native();

    public static native long recordPB_getPosition_native();

    public static native int recordPVRSelectSvc(int i, int i2, int i3);

    public static native int recordStartPVR(int i);

    public static native int recordStartPVRByHandle(int i);

    public static native boolean recordStartSpeedTest_native(String str, int i);

    public static native int recordStopPVR();

    public static native int recordStopPVRByHandle(int i);

    public static native int recordgetErrorID();

    public static native int recordgetErrorIDByhandle(int i);

    public static native int registerDefaultCallback_native();

    public static native int registerTimeshiftDevice_native(String str, long j);

    public static native int remindMeLater_native();

    public static native int removeConfigListener_native(String str);

    public static native int removeFavoritelistChannelByIndexWithoutShowFavIcon_native(int i);

    public static native int removeFavoritelistChannel_native(int i);

    public static native int removeWebServiceClient_native(String str);

    public static native int resetConfigValues_native(int i);

    public static native void resetDigitKeysHandledFlag_native();

    public static native void resetFac_native();

    public static native void resetPri_native();

    public static native void resetPub_native();

    public static native int restoreSatlSnapshot_native(int i);

    public static native int restoreSnapshot_native(int i);

    public static native void rpcCloseClient_native();

    public static native int satllistCleanDatabase_native(int i);

    public static native int satllistLockDatabase_native(int i);

    public static native int satllistReadLockDatabase_native(int i);

    public static native int satllistReadUnLockDatabase_native(int i);

    public static native int satllistUnLockDatabase_native(int i);

    public static native void saveTimeStamp_native(String str);

    public static native int sbAtscGetMajorChannelNum_native(int i);

    public static native int sbAtscGetMinorChannelNum_native(int i);

    public static native int sbIsdbGetChannelIndex_native(int i);

    public static native int sbIsdbGetMajorNumber_native(int i);

    public static native int sbIsdbGetMinorNumber_native(int i);

    public static native int seekTimeshift_native(long j);

    public static native int seekTo_native(long j);

    public static native int selectAudioById_native(int i);

    public static native int selectDefaultAudio_native(String str);

    public static native int selectMainSubAudioById_native(int i, int i2);

    public static native int set2ndCurrentChannelBySvlId_native(int i, int i2);

    public static native int setATSCScanParas(MtkTvATSCScanParaBase mtkTvATSCScanParaBase);

    public static native int setAdbStatus_native(boolean z);

    public static native int setAtscStorage_native(boolean z);

    public static native int setAudioFocus_native(String str);

    public static native int setAudioFocusbySourceid_native(int i);

    public static native int setAudioInfoData_native(int i, MtkTvAudioInfoBase mtkTvAudioInfoBase);

    public static native int setAudioInfoValue_native(int i, int i2);

    public static native int setAutoDetectPlugStatus_native(int i, boolean z);

    public static native int setAutoDownload_native(boolean z);

    public static native int setAutoRecord_native(boolean z);

    public static native int setBisskeyInfo_native(int i, MtkTvBisskeyInfoBase mtkTvBisskeyInfoBase, int i2);

    public static native int setBlockUnrated_native(boolean z);

    public static native int setBookingChannelId_native(int i, int i2);

    public static native int setBookingDeviceIndex_native(int i, int i2);

    public static native int setBookingEventId_native(int i, int i2);

    public static native int setBookingEventTitle_native(int i, String str);

    public static native int setBookingGenre_native(int i, int i2);

    public static native int setBookingInfoData_native(int i, int i2);

    public static native int setBookingRecordDelay_native(int i, int i2);

    public static native int setBookingRecordDuration_native(int i, long j);

    public static native int setBookingRecordMode_native(int i, int i2);

    public static native int setBookingRepeatMode_native(int i, int i2);

    public static native int setBookingSourceType_native(int i, int i2);

    public static native int setBookingStartTime_native(int i, long j);

    public static native int setBookingTunerType_native(int i, int i2);

    public static native int setBookingType_native(int i, int i2);

    public static native int setBookingUrlInfo_native(int i, String str);

    public static native int setCANEngRatingSettingInfo_native(int i);

    public static native int setCANFreRatingSettingInfo_native(int i);

    public static native int setCamPinCode_navtive(int i, String str);

    public static native int setChannelListWithoutNotify_native(int i, int i2, List<MtkTvChannelInfoBase> list);

    public static native int setChannelList_native(int i, int i2, List<MtkTvChannelInfoBase> list);

    public static native int setChannelPumpVer_native(int i, int i2);

    public static native int setChgSource_native(boolean z);

    public static native int setConfigIntArrayValue_native(String str, int[] iArr, int i);

    public static native int setConfigStringEx_native(String str, String str2, int i);

    public static native int setConfigString_native(String str, String str2);

    public static native int setConfigValue_native(int i, String str, int i2, int i3);

    public static native int setCrntOverscan_native(int i, int i2, int i3, int i4);

    public static native void setCurrentActiveWindowsInfoEx_native(List<MtkTvChannelInfoBase> list, long j, long j2);

    public static native void setCurrentActiveWindowsInfo_native(List<MtkTvChannelInfoBase> list, long j);

    public static native int setCurrentChannelBySvlId_native(int i, int i2);

    public static native int setCurrentChannel_native(int i);

    public static native int setDVBAgeRatingSetting_native(int i);

    public static native int setDigitalFavoritesList_native(int i, int i2, int i3, List<MtkTvChannelInfoBase> list, int i4);

    public static native int setDvbScanParas(MtkTvDvbScanParaBase mtkTvDvbScanParaBase);

    public static native int setDvbcManualScanParas(MtkTvDvbcManualScanParaBase mtkTvDvbcManualScanParaBase);

    public static native int setDvbcScanParas(MtkTvDvbcScanParaBase mtkTvDvbcScanParaBase);

    public static native int setEASAndroidLaunchStatus_native(boolean z);

    public static native int setEarcEnable_native(boolean z);

    public static native int setEnqAnswer_navtive(int i, int i2, int i3, String str);

    public static native boolean setEthernetWolCtl_native(boolean z);

    public static native void setEventCallback_native(boolean z);

    public static native int setFileLockChannel(String str, int i);

    public static native int setFileLockInput(String str, int i);

    public static native int setFreeze_native(String str, boolean z);

    public static native int setHDSConfirm_navtive(int i, int i2);

    public static native void setHbbtvStatus_native(boolean z);

    public static native int setISDBAgeRatingSetting_native(int i);

    public static native int setISDBContentRatingSetting_native(int i);

    public static native int setISDBScanParas(MtkTvISDBScanParaBase mtkTvISDBScanParaBase);

    public static native void setInfo_native(byte b2, int i, int[] iArr);

    public static native int setInputLabelIdx_native(int i, int i2);

    public static native int setInputLabelUserDefName_native(int i, String str);

    public static native int setLinuxBGMStart_native();

    public static native void setLinuxKeyReceive_native(int i);

    public static native void setLocalTimeZone_native(String str);

    public static native void setMMICloseDone_navtive(int i);

    public static native void setMMIClose_navtive(int i);

    public static native int setMenuAnswer_navtive(int i, int i2, int i3);

    public static native void setMheg5Disable_native();

    public static native void setMheg5Enable_native();

    public static native void setMheg5Status_native(int i, int i2, int i3, int i4);

    public static native int setMute_native(int i, boolean z);

    public static native int setMute_native(boolean z);

    public static native int setNTSCScanParas(MtkTvNTSCScanParaBase mtkTvNTSCScanParaBase);

    public static native int setNewTvMode_native(int i);

    public static native int setNextAudioLang_native();

    public static native boolean setOSDOpacity_native(int i);

    public static native int setOpenVCHIPSettingInfo_native(MtkTvOpenVCHIPSettingInfoBase mtkTvOpenVCHIPSettingInfoBase);

    public static native int setPclWakeupSetup_native(int i);

    public static native void setPfgResult_native(boolean z);

    public static native int setPictureMode_native(int i);

    public static native int setPipConfig_native(int i);

    public static native int setPkgPathname_native(String str);

    public static native int setPlaybackPause_native();

    public static native int setPlaybackResume_native();

    public static native int setPlaybackSpeed_native(MtkTvTimeshiftBase.TimeshiftPlaybackSpeed timeshiftPlaybackSpeed);

    public static native int setRatingEnable_native(boolean z);

    public static native int setRecordModeByHandle_native(int i, int i2);

    public static native int setRecordOff_native(int i);

    public static native int setRecordOn_native(int i, MtkTvCecRecordSouceInfoBase mtkTvCecRecordSouceInfoBase);

    public static native boolean setRecordRegisterFile_native(String str);

    public static native int setSasForceItvExit_navtive(int i);

    public static native int setSatelliteSetting(MtkTvDvbsSatelliteSettingBase mtkTvDvbsSatelliteSettingBase);

    public static native int setScanComplete_navtive(int i);

    public static native int setScartAutoJump_native(boolean z);

    public static native int setScreenMode_native(int i);

    public static native int setScreenOutputDispRect_native(String str, MtkTvRectangle mtkTvRectangle);

    public static native int setScreenSourceRect_native(String str, MtkTvRectangle mtkTvRectangle);

    public static native void setSignalMonitor_native(boolean z);

    public static native void setSleepTimer_native(int i);

    public static native int setSoundEffect_native(int i);

    public static native int setStandbyToAll_native(boolean z);

    public static native int setStandby_native(int i);

    public static native int setSystemAudioModeRequest_native(int i);

    public static native int setSystemProperties_native(String str, String str2);

    public static native int setTeletextPage_native(MtkTvTeletextPageBase mtkTvTeletextPageBase);

    public static native void setTimeOffset_native(long j);

    public static native void setTimeSyncSource_native(int i);

    public static native void setTimeZone_native(long j);

    public static native int setTimer_native(int i, MtkTvCecTimeInfoBase mtkTvCecTimeInfoBase);

    public static native int setUARTSerialOperationMode_native(int i, int i2);

    public static native int setUARTSerialSetting_native(int i, int[] iArr);

    public static native int setUSMovieRatingSettingInfo_native(int i);

    public static native int setUSTvRatingSettingInfo_native(MtkTvUSTvRatingSettingInfoBase mtkTvUSTvRatingSettingInfoBase);

    public static native int setUserCtrlPressed_native(MtkTvCecBase.CecUserCtrlInfo cecUserCtrlInfo);

    public static native int setUserCtrlReleased_native(MtkTvCecBase.CecUserCtrlInfo cecUserCtrlInfo);

    public static native void setUtcTime_native(long j);

    public static native int setVendorCmdWithId_native(MtkTvCecBase.CecVndrCmdWithIdInfo cecVndrCmdWithIdInfo);

    public static native int setVideoInfoData_native(int i, MtkTvVideoInfoBase mtkTvVideoInfoBase);

    public static native int setVideoInfoValue_native(int i, int i2);

    public static native int setVolume_native(int i);

    public static native int setVolume_native(int i, int i2);

    public static native boolean setWifiWolCtl_native(boolean z);

    public static native int sifReadMultipleSubAddr_native(int i, int i2, byte b2, byte[] bArr, byte[] bArr2);

    public static native int sifWriteMultipleSubAddr_native(int i, int i2, byte b2, byte[] bArr, byte[] bArr2);

    public static native int slideForOnePartChannelId_native(int i, int i2, int i3);

    public static native void softKeyboardInit_native();

    public static native int startApplication_native(String str);

    public static native int startCamScan_navtive(int i, boolean z);

    public static native int startDownloadFirmware_native(MtkTvUpgradeDeliveryTypeBase mtkTvUpgradeDeliveryTypeBase, String str, String str2);

    public static native int startDownload_native();

    public static native int startFlash_native();

    public static native int startGinga_native();

    public static native int startJumpChannel_native();

    public static native void startLanB2BWorkThread_native();

    public static native int startMainVideo_native(int i, MtkTvMultiViewBase.Region_Info_T region_Info_T);

    public static native int startManualDetect_native();

    public static native int startRebootUpgrade_native(MtkTvUpgradeDeliveryTypeBase mtkTvUpgradeDeliveryTypeBase);

    public static native int startScan_native(int i, int i2, boolean z);

    public static native void startSerialWorkThread_native();

    public static native int startSubVideo_native(int i, MtkTvMultiViewBase.Region_Info_T region_Info_T);

    public static native void startTestB2BHWPortWorkThread_native();

    public static native int startTimeshift_native();

    public static native int startUpgrade_native(MtkTvUpgradeDeliveryTypeBase mtkTvUpgradeDeliveryTypeBase, boolean z);

    public static native int stopApplication_native(String str);

    public static native int stopDownload_native();

    public static native int stopGinga_native();

    public static native int stopMainVideo_native();

    public static native int stopManualDetect_native();

    public static native int stopSubVideo_native();

    public static native int stopTimeshift_native();

    public static native int stopTimeshift_native(MtkTvTimeshiftBase.TimeshiftStopFlag timeshiftStopFlag);

    public static native int storeFavoritelistChannel_native();

    public static native int swapChannelInfo_native(int i, int i2, int i3);

    public static native int swapDigitalFavorites_native(int i, int i2, int i3, int i4);

    public static native int swapFavoritelistByIndex_native(int i, int i2);

    public static native int switchInputToTvSource_native();

    public static native int syncStop_native(String str, boolean z);

    public static native boolean teletextHasTopInfo_native();

    public static native int temp_unblock_native(int i, boolean z);

    public static native int transferExternalUIStatus_native(MtkTvExternalUIStatusBase mtkTvExternalUIStatusBase);

    public static native int trickPlayTimeshift_native(double d2);

    public static native int triggerUpgrade_native(MtkTvUpgradeDeliveryTypeBase mtkTvUpgradeDeliveryTypeBase);

    public static native int tunerFacQuery_navtive(boolean z, List<String> list, List<String> list2);

    public static native int unmountPerm_native();

    public static native int unregisterDefaultCallback_native();

    public static native int unselectAudio_native();

    public static native void updateBooking_native();

    public static native int updateCIKeyEx_navtive(int i);

    public static native int updateCIKey_navtive();

    public static native int updateOverscanIni_native();

    public static native int updateSatlRecord_native(int i, MtkTvDvbsConfigInfoBase mtkTvDvbsConfigInfoBase, boolean z);

    public static native int warningStartCC_native(boolean z);

    public static native int warningStartGingaApp_native(boolean z);

    public static native int writeToExternalControl_native(int i, byte[] bArr, int i2, int i3);
}
