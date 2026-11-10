package com.hsp.autogen.utils;

import android.util.Log;
import com.hsp.rod.HSPBuffer;
import com.hsp.rod.IRodListener;
import com.hsp.rod.IRodObserver;
import com.hsp.rod.Rod;

/* JADX INFO: loaded from: classes.dex */
public class AutoGenHSP {
    public static final String TAG = "AutoGenHSP";
    private static final String TOKEN = "HSPJDEMOC760CA0D03ACE451A3AC0768";
    private static AutoGenHSP _instance;
    private NotifyListener mNotifyListener = new NotifyListener();
    private RodObserver mRodObserver = new RodObserver();

    public static AutoGenHSP getInstance() {
        if (_instance == null) {
            _instance = new AutoGenHSP();
        }
        return _instance;
    }

    public class NotifyListener extends IRodListener {
        public NotifyListener() {
        }

        public void onNotified(String str, HSPBuffer hSPBuffer) {
            Log.d(AutoGenHSP.TAG, "onNotified capbilities:" + str + ", param:" + hSPBuffer.toString());
        }

        public void onBroadcast(String str, HSPBuffer hSPBuffer) {
            Log.d(AutoGenHSP.TAG, "onBroadcast capbility:" + str + ", param:" + hSPBuffer.toString());
        }
    }

    public class RodObserver extends IRodObserver {
        public RodObserver() {
        }

        public HSPBuffer onCalled(int i, String str, HSPBuffer hSPBuffer, String str2) {
            Log.d(AutoGenHSP.TAG, "action:" + i + ", capbility:" + str + ", from:" + str2);
            return new HSPBuffer("vt_hsp_customer_test_java return value".getBytes(), "vt_hsp_customer_test_java return value".getBytes().length);
        }
    }

    public void initHSPEnv() {
        Rod.subscribe(new String[]{"setScanSource", "startManualScan", "startAutoScan", "cancelScan", "isScanRunning", "setSurfaceToSupply", "startPlay", "stopPlay", "getCurrentPlayChannel", "setCurrentChannel", "getCurrentChannelWithTunerMode", "getChannelInfoByUUID", "getChannelListByFreIndex", "getChannelListByTunerMode", "cleanChannelListByTunerMode", "setChannelList", "getAudioTrackInfoList", "getCurrentAudioTrackId", "selectAudioByTrackId", "setPrimaryAudioLanguage", "getPrimaryAudioLanguage", "setSecondaryAudioLanguage", "getSecondaryAudioLanguage", "setAudioDescriptionEnable", "getAudioDescriptionEnable", "setAudioDescriptionVolume", "getAudioDescriptionVolume", "setAtvMtsOutputMode", "getAtvMtsOutputMode", "isLockEnabled", "setLockEnabled", "setTime", "getStartTime", "getStopTime", "getWeeklyTime", "getPin", "setPin", "checkPin", "unLockService", "setTempLockFlag", "getTempLockFlag", "setBlockUnrated", "isBlockUnrated", "setUSTVBlockedRatings", "getUSTVSettings", "setUSMovieSettings", "getUSMovieSettings", "setCanEngSettings", "getCanEngSettings", "setCanFreSettings", "getCanFreSettings", "isOpenVchipAvailable", "getOpenVchipInfo", "setOpenVchipSetting", "getOpenVchipSetting", "isInputSourceBlocked", "blockInputSource", "getTvTimeUtcInSeconds", "getTvTimeUtcInMilliSeconds", "getTvTimeZoneOffset", "getTvTimeDstOffset", "saveTimeZoneOffset", "loadTimeZoneOffset", "saveTimeSyncSource", "loadTimeSyncSource", "getInfoBarChannelInfo", "getInfoBarProgramEvent", "getInfoBarTunerInfo", "getInfoBarAVStatus", "isCCExist", "setCCState", "getCCState", "getTotalCCLanguage", "saveSetting", "loadSetting", "changeToTvSource", "changeToOtherSource", "hasDtvTuner", "presetChannel", "getAEATInfoAll", "getAEATInfoByAeaID", "setAEATInfoPriority", "setDeviceLocation", "setAEALanguage", "importChannellist", "exportChannellist", "setCountry", "getCountry", "setLanguage", "getLanguage", "setTvMute", "isTvMute", "setInteractiveService", "getInteractiveService", "startService", "stopService", "reset", "resetTempLockStatus", "requestTVAudioFocus", "abandonTVAudioFocus", "getAudioEmergencyInformationEnable", "setAudioEmergencyInformationEnable", "getDialogEnhancementGain", "setDialogEnhancementGain", "getProgramListByChannelKey"});
        Rod.setProviderName("vt_hsp_customer_test_java");
        Rod.run(TOKEN, this.mNotifyListener);
    }
}
