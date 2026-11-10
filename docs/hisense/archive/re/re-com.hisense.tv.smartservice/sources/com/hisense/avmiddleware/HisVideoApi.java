package com.hisense.avmiddleware;

import android.os.SystemProperties;
import b.a.a.a.a;
import java.util.ArrayList;
import java.util.Iterator;
import vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi;

/* JADX INFO: loaded from: classes2.dex */
public class HisVideoApi {
    public static final int ALL_SOURCE = 1;
    public static final int COLORSPACE_AUTO = 1;
    public static final int COLORSPACE_NATIVE = 0;
    public static final int CURRENT_SOURCE = 0;
    public static final int HIGH = 3;
    public static final int HIS_BACKLIGHT_LASER4K_L5_MAX = 20;
    public static final int HIS_BACKLIGHT_LASER4K_L9_MAX = 10;
    public static final int HIS_BACKLIGHT_MAX = 100;
    public static final int HIS_BACKLIGHT_MIN = 0;
    public static final int HIS_BLACKLEVEL_MAX = 10;
    public static final int HIS_BLACKLEVEL_MIN = -10;
    public static final int HIS_BLURRDC_MAX = 10;
    public static final int HIS_BLURRDC_MIN = 0;
    public static final int HIS_BRIGHTNESS_MAX = 100;
    public static final int HIS_BRIGHTNESS_MAX_TVS = 50;
    public static final int HIS_BRIGHTNESS_MIN = 0;
    public static final int HIS_BRIGHTNESS_MIN_TVS = -50;
    public static final int HIS_COLORTUNER_BRIGHTNESS_MAX = 10;
    public static final int HIS_COLORTUNER_BRIGHTNESS_MIN = -10;
    public static final int HIS_COLORTUNER_HUE_MAX = 10;
    public static final int HIS_COLORTUNER_HUE_MIN = -10;
    public static final int HIS_COLORTUNER_SATURATION_MAX = 10;
    public static final int HIS_COLORTUNER_SATURATION_MIN = -10;
    public static final int HIS_CONTRAST_MAX = 100;
    public static final int HIS_CONTRAST_MAX_TVS = 50;
    public static final int HIS_CONTRAST_MIN = 0;
    public static final int HIS_CONTRAST_MIN_TVS = -50;
    public static final int HIS_GAMMA_CALI_GAIN_MAX = 25;
    public static final int HIS_GAMMA_CALI_GAIN_MIN = -25;
    public static final int HIS_GAMMA_CALI_LEVEL_MAX = 19;
    public static final int HIS_GAMMA_CALI_LEVEL_MIN = 0;
    public static final int HIS_JUDDERRDC_MAX = 10;
    public static final int HIS_JUDDERRDC_MIN = 0;
    public static final int HIS_MAXBACKLIGHT_MAX = 5;
    public static final int HIS_MAXBACKLIGHT_MIN = 1;
    public static final int HIS_MINBACKLIGHT_MAX = 10;
    public static final int HIS_MINBACKLIGHT_MIN = -10;
    public static final int HIS_SATURATION_MAX = 100;
    public static final int HIS_SATURATION_MAX_TVS = 50;
    public static final int HIS_SATURATION_MIN = 0;
    public static final int HIS_SATURATION_MIN_TVS = -50;
    public static final int HIS_SHARPNESS_MAX = 50;
    public static final int HIS_SHARPNESS_MAX_TVS = 50;
    public static final int HIS_SHARPNESS_MIN = 0;
    public static final int HIS_SHARPNESS_MIN_TVS = -50;
    public static final int HIS_TINT_MAX = 50;
    public static final int HIS_TINT_MIN = -50;
    public static final int HIS_WB20P_BLUE_MAX = 25;
    public static final int HIS_WB20P_BLUE_MIN = -25;
    public static final int HIS_WB20P_EXT_MAX = 100;
    public static final int HIS_WB20P_EXT_MIN = -100;
    public static final int HIS_WB20P_GREEN_MAX = 25;
    public static final int HIS_WB20P_GREEN_MIN = -25;
    public static final int HIS_WB20P_LEVEL_MAX = 19;
    public static final int HIS_WB20P_LEVEL_MIN = 0;
    public static final int HIS_WB20P_RED_MAX = 25;
    public static final int HIS_WB20P_RED_MIN = -25;
    public static final int HIS_WB2P_B_GAIN_MAX = 25;
    public static final int HIS_WB2P_B_GAIN_MIN = -25;
    public static final int HIS_WB2P_B_OFFSET_MAX = 25;
    public static final int HIS_WB2P_B_OFFSET_MIN = -25;
    public static final int HIS_WB2P_G_GAIN_MAX = 25;
    public static final int HIS_WB2P_G_GAIN_MIN = -25;
    public static final int HIS_WB2P_G_OFFSET_MAX = 25;
    public static final int HIS_WB2P_G_OFFSET_MIN = -25;
    public static final int HIS_WB2P_R_GAIN_MAX = 25;
    public static final int HIS_WB2P_R_GAIN_MIN = -25;
    public static final int HIS_WB2P_R_OFFSET_MAX = 25;
    public static final int HIS_WB2P_R_OFFSET_MIN = -25;
    public static final int HIS_WHITELEVEL_MAX = 10;
    public static final int HIS_WHITELEVEL_MIN = -10;
    public static ArrayList<Integer> Hue = null;
    public static final int IDX_ITEM_DATA_GAMMA_LIST = 0;
    public static final int IDX_ITEM_DATA_LASER_SCREEN_SIZE = 28;
    public static final int IDX_ITEM_DATA_SUB_RECT_REGION = 18;
    public static final int IDX_ITEM_STATE_CALMAN_SERVICE = 0;
    public static final int IDX_ITEM_STRING_CHECK_GAMMA_STATUS = 6;
    public static final int IDX_ITEM_STRING_DOLBY_IQ_USER_BIN_INFO = 5;
    public static final int IDX_ITEM_STRING_DOLBY_IQ_USER_BIN_PATH = 4;
    public static final int IDX_ITEM_STRING_GAMMA_LIST = 0;
    public static final int IDX_ITEM_VALUE_4K120HZ_DIRECT_PATH_EN = 44;
    public static final int IDX_ITEM_VALUE_4K120HZ_OPTION_STATE = 45;
    public static final int IDX_ITEM_VALUE_AIRPLAY_MODE = 72;
    public static final int IDX_ITEM_VALUE_BACKGROUND_COLOR = 31;
    public static final int IDX_ITEM_VALUE_DOLBYIQ_LUX = 23;
    public static final int IDX_ITEM_VALUE_FORCE_120HZ_ENABLE = 54;
    public static final int IDX_ITEM_VALUE_GAMMA_TUNING = 0;
    public static final int IDX_ITEM_VALUE_IS_CALMAN_CALIBRATED = 71;
    public static final int IDX_ITEM_VALUE_MUTE_COLOR = 26;
    public static final int IDX_ITEM_VALUE_VRR_FRAME_RATE = 12;
    public static final int IDX_ITEM_VALUE_VRR_STATUS = 19;
    public static final int LOW = 1;
    public static ArrayList<Integer> Lum = null;
    public static final int MEDIUM = 2;
    public static final int OFF = 0;
    public static final int RGB_BLUE = 3;
    public static final int RGB_GREEN = 2;
    public static final int RGB_OFF = 0;
    public static final int RGB_RED = 1;
    public static ArrayList<Integer> Rgb = null;
    public static ArrayList<Integer> Sat = null;
    public static final String TAG = "HISMW@videojava";
    public static ArrayList<ArrayList> hslList = null;
    public static ArrayList<Byte> mBytes = null;
    public static String mGetParamStr = "NAK";
    public static int mGetParamVal = -1;
    public static int mIsDolbyBright = 0;
    public static int mIsDolbyIQEnable = 0;
    public static int mIsTvs = 0;
    public static ArrayList<Integer> mItemData = null;
    public static int mItemDataIndex = 0;
    public static String mItemStr = null;
    public static int mItemStrIndex = 0;
    public static int mItemValueIndex = 0;
    public static ArrayList<Integer> mRemoteNowData = null;
    public static int mReturnVal = -1;
    public static IHisVideoApi mVideoServer;

    public static int GetVideoBeautySwitchOptionState() {
        HisLogger.d(TAG, "getVideoBeautySwitchOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getVideoBeautySwitchOptionState(new IHisVideoApi.getVideoBeautySwitchOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.256
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getVideoBeautySwitchOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getVideoBeautySwitchOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int IsAirPlayEnable() {
        HisLogger.d(TAG, "IsAirPlayEnable");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getItemValue(72, new IHisVideoApi.getItemValueCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.82
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getItemValueCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("IsAirPlayEnable ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int PictureMenuQuit(int i) {
        int iPictureMenuQuit = -1;
        if (a.f("PictureMenuQuit val:", i, TAG)) {
            try {
                iPictureMenuQuit = mVideoServer.PictureMenuQuit(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("PictureMenuQuit ret = ", iPictureMenuQuit, TAG);
        }
        return iPictureMenuQuit;
    }

    public static int ResetAllUserSettings() {
        HisLogger.d(TAG, "ResetAllUserSettings");
        int iResetAllUserSettings = -1;
        if (getHidlService()) {
            try {
                iResetAllUserSettings = mVideoServer.ResetAllUserSettings(1);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("ResetAllUserSettings ret = ", iResetAllUserSettings, TAG);
        }
        return iResetAllUserSettings;
    }

    public static int SetItemValue(int i, int i2) {
        int itemValue = -1;
        if (a.f("SetItemValue val:", i2, TAG)) {
            try {
                itemValue = mVideoServer.setItemValue(i, i2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("SetItemValue ret = ", itemValue, TAG);
        }
        return itemValue;
    }

    public static int SetStandyScreenStatus(int i) {
        int standyScreenStatus = -1;
        if (a.f("SetStandyScreenStatus val:", i, TAG)) {
            try {
                standyScreenStatus = mVideoServer.setStandyScreenStatus(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("SetStandyScreenStatus ret = ", standyScreenStatus, TAG);
        }
        return standyScreenStatus;
    }

    public static int SetTvsCtrlQDPQUpdateEnable() {
        HisLogger.d(TAG, "SetTvsCtrlQDPQUpdateEnable ");
        int tvsCtrlQDPQUpdateEnable = -1;
        if (getHidlService()) {
            try {
                tvsCtrlQDPQUpdateEnable = mVideoServer.setTvsCtrlQDPQUpdateEnable(1);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("SetTvsCtrlQDPQUpdateEnable ret = ", tvsCtrlQDPQUpdateEnable, TAG);
        }
        return tvsCtrlQDPQUpdateEnable;
    }

    public static int backupPQSetting() {
        HisLogger.d(TAG, "backupPQSetting");
        int iBackupPQSetting = -1;
        if (getHidlService()) {
            try {
                iBackupPQSetting = mVideoServer.backupPQSetting();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("backupPQSetting ret = ", iBackupPQSetting, TAG);
        }
        return iBackupPQSetting;
    }

    public static int cleanCalManData(int i) {
        int iCleanCalMANData = -1;
        if (a.f("cleanCalManData val:", i, TAG)) {
            try {
                iCleanCalMANData = mVideoServer.cleanCalMANData(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("cleanCalManData ret = ", iCleanCalMANData, TAG);
        }
        return iCleanCalMANData;
    }

    public static int get2PWBGainBlueDelta() {
        HisLogger.d(TAG, "get2PWBGainBlueDelta");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.get2PWBGainBlueDelta(new IHisVideoApi.get2PWBGainBlueDeltaCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.215
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.get2PWBGainBlueDeltaCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("get2PWBGainBlueDelta ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int get2PWBGainGreenDelta() {
        HisLogger.d(TAG, "get2PWBGainGreenDelta");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.get2PWBGainGreenDelta(new IHisVideoApi.get2PWBGainGreenDeltaCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.214
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.get2PWBGainGreenDeltaCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("get2PWBGainGreenDelta ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int get2PWBGainRedDelta() {
        HisLogger.d(TAG, "get2PWBGainRedDelta");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.get2PWBGainRedDelta(new IHisVideoApi.get2PWBGainRedDeltaCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.213
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.get2PWBGainRedDeltaCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("get2PWBGainRedDelta ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int get2PWBOffsetBlueDelta() {
        HisLogger.d(TAG, "get2PWBOffsetBlueDelta");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.get2PWBOffsetBlueDelta(new IHisVideoApi.get2PWBOffsetBlueDeltaCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.212
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.get2PWBOffsetBlueDeltaCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("get2PWBOffsetBlueDelta ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int get2PWBOffsetGreenDelta() {
        HisLogger.d(TAG, "get2PWBOffsetGreenDelta");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.get2PWBOffsetGreenDelta(new IHisVideoApi.get2PWBOffsetGreenDeltaCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.211
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.get2PWBOffsetGreenDeltaCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("get2PWBOffsetGreenDelta ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int get2PWBOffsetRedDelta() {
        HisLogger.d(TAG, "get2PWBOffsetRedDelta");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.get2PWBOffsetRedDelta(new IHisVideoApi.get2PWBOffsetRedDeltaCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.210
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.get2PWBOffsetRedDeltaCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("get2PWBOffsetRedDelta ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int get4K120HzDirectPathEnable() {
        HisLogger.d(TAG, "get4K120HzDirectPathEnable");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getItemValue(44, new IHisVideoApi.getItemValueCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.273
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getItemValueCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("get4K120HzDirectPathEnable ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(", value = ");
                        sbA.append(Integer.toHexString(HisVideoApi.mGetParamVal));
                        HisLogger.d(HisVideoApi.TAG, sbA.toString());
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int get4K120HzDirectPathOptionState() {
        HisLogger.d(TAG, "get4K120HzDirectPathOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getItemValue(45, new IHisVideoApi.getItemValueCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.274
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getItemValueCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("get4K120HzDirectPathOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getAIPQDetectSwitch() {
        HisLogger.d(TAG, "getAIPQDetectSwitch");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getAIPQDetectSwitch(new IHisVideoApi.getAIPQDetectSwitchCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.223
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getAIPQDetectSwitchCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getAIPQDetectSwitch ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getAIPQDetectSwitchOptionState() {
        HisLogger.d(TAG, "getAIPQDetectSwitchOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getAIPQDetectSwitchOptionState(new IHisVideoApi.getAIPQDetectSwitchOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.224
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getAIPQDetectSwitchOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getAIPQDetectSwitchOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getAPOContentCurve(final int i, final int i2) {
        HisLogger.d(TAG, "getAPOContentCurve");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getAPOContentCurve(i, i2, new IHisVideoApi.getAPOContentCurveCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.250
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getAPOContentCurveCallback
                    public void onValues(int i3, int i4) {
                        HisVideoApi.mReturnVal = i3;
                        HisVideoApi.mGetParamVal = i4;
                        StringBuilder sbA = a.a("getAPOContentCurve ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append("mode: ");
                        sbA.append(i);
                        sbA.append("index: ");
                        sbA.append(i2);
                        sbA.append("value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getAPOWBCurve(final int i, final int i2) {
        HisLogger.d(TAG, "getAPOWBCurve");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getAPOWBCurve(i, i2, new IHisVideoApi.getAPOWBCurveCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.251
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getAPOWBCurveCallback
                    public void onValues(int i3, int i4) {
                        HisVideoApi.mReturnVal = i3;
                        HisVideoApi.mGetParamVal = i4;
                        StringBuilder sbA = a.a("getAPOWBCurve ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append("mode: ");
                        sbA.append(i);
                        sbA.append("index: ");
                        sbA.append(i2);
                        sbA.append("value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getActiveContrast() {
        HisLogger.d(TAG, "getPictureSettingsToAllSource");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getActiveContrast(new IHisVideoApi.getActiveContrastCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.50
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getActiveContrastCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getActiveContrast ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getActiveContrastOptionState() {
        HisLogger.d(TAG, "getActiveContrastOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getActiveContrastOptionState(new IHisVideoApi.getActiveContrastOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.267
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getActiveContrastOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getActiveContrastOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getAgingModeEnable() {
        HisLogger.d(TAG, "getAgingModeEnable");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getAgingModeEnable(new IHisVideoApi.getAgingModeEnableCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.150
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getAgingModeEnableCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getAgingModeEnable ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static ArrayList<Integer> getAllPictureSettingsForRemoteNow() {
        HisLogger.d(TAG, "getAllPictureSettingsForRemoteNow");
        mRemoteNowData = null;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getAllPictureSettingsForRemoteNow(new IHisVideoApi.getAllPictureSettingsForRemoteNowCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.191
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getAllPictureSettingsForRemoteNowCallback
                    public void onValues(int i, ArrayList<Integer> arrayList) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mRemoteNowData = (ArrayList) arrayList.clone();
                        StringBuilder sbA = a.a("getAllPictureSettingsForRemoteNow ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        sbA.append(HisVideoApi.mRemoteNowData);
                        HisLogger.d(HisVideoApi.TAG, sbA.toString());
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mRemoteNowData;
    }

    public static int getAspectRatio() {
        HisLogger.d(TAG, "getAspectRatio");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getAspectRatio(new IHisVideoApi.getAspectRatioCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.1
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getAspectRatioCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getAspectRatio ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getAspectRatioDirectOptionState() {
        HisLogger.d(TAG, "getAspectRatioDirectOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getAspectRatioDirectOptionState(new IHisVideoApi.getAspectRatioDirectOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.160
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getAspectRatioDirectOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getAspectRatioDirectOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getAspectRatioDotByDotOptionState() {
        HisLogger.d(TAG, "getAspectRatioDotByDotOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getAspectRatioDotByDotOptionState(new IHisVideoApi.getAspectRatioDotByDotOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.159
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getAspectRatioDotByDotOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getAspectRatioDotByDotOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getAspectRatioOptionState() {
        HisLogger.d(TAG, "getAspectRatioOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getAspectRatioOptionState(new IHisVideoApi.getAspectRatioOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.157
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getAspectRatioOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getAspectRatioOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getAspectRatioPanoramicOptionState() {
        HisLogger.d(TAG, "getAspectRatioPanoramicOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getAspectRatioPanoramicOptionState(new IHisVideoApi.getAspectRatioPanoramicOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.158
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getAspectRatioPanoramicOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getAspectRatioPanoramicOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getAudioOnlyEnable() {
        HisLogger.d(TAG, "getAudioOnlyEnable");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getAudioOnlyEnable(new IHisVideoApi.getAudioOnlyEnableCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.9
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getAudioOnlyEnableCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getAudioOnlyEnable ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getAutoFilmmakerModeStatus() {
        HisLogger.d(TAG, "getAutoFilmmakerModeStatus");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getAutoFilmmakerModeStatus(new IHisVideoApi.getAutoFilmmakerModeStatusCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.153
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getAutoFilmmakerModeStatusCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getAutoFilmmakerModeStatus ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getAutoGammaEnable() {
        HisLogger.d(TAG, "getAutoGammaEnable");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.GetAutoGammaEnable(new IHisVideoApi.GetAutoGammaEnableCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.149
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.GetAutoGammaEnableCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getAutoGammaEnable ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static ArrayList<Integer> getAutoGammaList() {
        HisLogger.d(TAG, "getAutoGammaList");
        mReturnVal = -1;
        mItemData = null;
        mItemDataIndex = 0;
        if (getHidlService()) {
            try {
                mVideoServer.getItemData(0, new IHisVideoApi.getItemDataCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.218
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getItemDataCallback
                    public void onValues(int i, ArrayList<Integer> arrayList) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mItemData = (ArrayList) arrayList.clone();
                        StringBuilder sbA = a.a("getAutoGammaList ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",vec = ");
                        sbA.append(HisVideoApi.mItemData);
                        HisLogger.d(HisVideoApi.TAG, sbA.toString());
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mItemData;
    }

    public static String getAutoGammaListStr() {
        HisLogger.d(TAG, "getAutoGammaListStr");
        mReturnVal = -1;
        mItemStr = "N/A";
        mItemStrIndex = 0;
        if (getHidlService()) {
            try {
                mVideoServer.getItemString(0, new IHisVideoApi.getItemStringCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.219
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getItemStringCallback
                    public void onValues(int i, String str) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mItemStr = str;
                        StringBuilder sbA = a.a("getAutoGammaList ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",str = ");
                        sbA.append(HisVideoApi.mItemStr);
                        HisLogger.d(HisVideoApi.TAG, sbA.toString());
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mItemStr;
    }

    public static String getAutoGammaStatus() {
        HisLogger.d(TAG, "getAutoGammaStatus");
        mReturnVal = -1;
        mItemStr = "N/A";
        if (getHidlService()) {
            try {
                mVideoServer.getItemString(6, new IHisVideoApi.getItemStringCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.220
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getItemStringCallback
                    public void onValues(int i, String str) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mItemStr = str;
                        StringBuilder sbA = a.a("getAutoGammaStatus ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",str = ");
                        sbA.append(HisVideoApi.mItemStr);
                        HisLogger.d(HisVideoApi.TAG, sbA.toString());
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mItemStr;
    }

    public static int getBacklight20() {
        HisLogger.d(TAG, "getSharpnessMax");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getBacklight20(new IHisVideoApi.getBacklight20Callback() { // from class: com.hisense.avmiddleware.HisVideoApi.123
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBacklight20Callback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getBacklight20 ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getBacklight80() {
        HisLogger.d(TAG, "getBacklight80");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getBacklight80(new IHisVideoApi.getBacklight80Callback() { // from class: com.hisense.avmiddleware.HisVideoApi.125
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBacklight80Callback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getBacklight80 ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getBacklightLevelOptionState() {
        HisLogger.d(TAG, "getBacklightLevelOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getBacklightLevelOptionState(new IHisVideoApi.getBacklightLevelOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.162
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBacklightLevelOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getBacklightLevelOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getBacklightMax() {
        HisLogger.d(TAG, "getBacklightMax");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getBacklightMax(new IHisVideoApi.getBacklightMaxCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.126
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBacklightMaxCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getBacklightMax ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getBacklightMid() {
        HisLogger.d(TAG, "getBacklightMid");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getBacklightMid(new IHisVideoApi.getBacklightMidCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.124
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBacklightMidCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getBacklightMid ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getBacklightMin() {
        HisLogger.d(TAG, "getBacklightMin");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getBacklightMin(new IHisVideoApi.getBacklightMinCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.122
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBacklightMinCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getBacklightMin ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getBacklightPWMFreq() {
        HisLogger.d(TAG, "getBacklightPWMFreq");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getBacklightPWMFreq(new IHisVideoApi.getBacklightPWMFreqCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.127
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBacklightPWMFreqCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getBacklightPWMFreq ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getBacklightPercent() {
        HisLogger.d(TAG, "getBacklightPercent");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getBacklightPercent(new IHisVideoApi.getBacklightPercentCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.6
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBacklightPercentCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getBacklightPercent ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getBacklightPercentByFactory() {
        HisLogger.d(TAG, "getBacklightPercentByFactory");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getBacklightPercentByFactory(new IHisVideoApi.getBacklightPercentByFactoryCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.7
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBacklightPercentByFactoryCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getBacklightPercentByFactory ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getBacklightPercentRangeMax() {
        if (SystemProperties.getInt("ro.vt.product.laser4k", 0) == 1) {
            return SystemProperties.get("ro.product.system.name").equals("hisense_us_laser_Hisense_L5G") ? 20 : 10;
        }
        return 100;
    }

    public static int getBacklightPercentRangeMin() {
        return 0;
    }

    public static int getBlackLevel() {
        HisLogger.d(TAG, "getBlackLevel");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getBlackLevel(new IHisVideoApi.getBlackLevelCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.258
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBlackLevelCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getBlackLevel ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getBlackLevelMax() {
        HisLogger.d(TAG, "getBlackLevelMax");
        return 10;
    }

    public static int getBlackLevelMin() {
        HisLogger.d(TAG, "getBlackLevelMin");
        return -10;
    }

    public static int getBlackLevelOptionState() {
        HisLogger.d(TAG, "getBlackLevelOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getBlackLevelOptionState(new IHisVideoApi.getBlackLevelOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.260
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBlackLevelOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getBlackLevelOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getBlueMuteEnable() {
        HisLogger.d(TAG, "getBlueMuteEnable");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getItemValue(31, new IHisVideoApi.getItemValueCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.252
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getItemValueCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("get background color ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(", value = ");
                        sbA.append(Integer.toHexString(HisVideoApi.mGetParamVal));
                        HisLogger.d(HisVideoApi.TAG, sbA.toString());
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return 255 == (mGetParamVal & 255) ? 1 : 0;
    }

    public static int getBlurReductionOptionState() {
        HisLogger.d(TAG, "getBlurReductionOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getBlurReductionOptionState(new IHisVideoApi.getBlurReductionOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.55
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBlurReductionOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getBlurReductionOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getBrightness() {
        HisLogger.d(TAG, "getBrightness");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.isTVSModel(new IHisVideoApi.isTVSModelCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.32
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.isTVSModelCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mIsTvs = i2;
                        StringBuilder sbA = a.a("isTVSModel ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mIsTvs, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            try {
                mVideoServer.getBrightness(new IHisVideoApi.getBrightnessCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.33
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBrightnessCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        if (1 == HisVideoApi.mIsTvs) {
                            HisVideoApi.mGetParamVal = i2 - 50;
                        } else {
                            HisVideoApi.mGetParamVal = i2;
                        }
                        StringBuilder sbA = a.a("getBrightness ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getBrightness20() {
        HisLogger.d(TAG, "getBrightness20");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getBrightness20(new IHisVideoApi.getBrightness20Callback() { // from class: com.hisense.avmiddleware.HisVideoApi.98
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBrightness20Callback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getBrightness20 ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getBrightness80() {
        HisLogger.d(TAG, "getBrightness80");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getBrightness80(new IHisVideoApi.getBrightness80Callback() { // from class: com.hisense.avmiddleware.HisVideoApi.100
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBrightness80Callback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getBrightness80 ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getBrightnessByFactory() {
        HisLogger.d(TAG, "getBrightnessByFactory");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getBrightnessByFactory(new IHisVideoApi.getBrightnessByFactoryCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.36
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBrightnessByFactoryCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getBrightnessByFactory ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getBrightnessMax() {
        HisLogger.d(TAG, "getBrightnessMax");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getBrightnessMax(new IHisVideoApi.getBrightnessMaxCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.101
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBrightnessMaxCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getBrightnessMax ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getBrightnessMid() {
        HisLogger.d(TAG, "getBrightnessMid");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getBrightnessMid(new IHisVideoApi.getBrightnessMidCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.99
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBrightnessMidCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getBrightnessMid ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getBrightnessMin() {
        HisLogger.d(TAG, "getBrightnessMin");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getBrightnessMin(new IHisVideoApi.getBrightnessMinCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.97
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBrightnessMinCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getBrightnessMin ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getBrightnessOptionState() {
        HisLogger.d(TAG, "getBrightnessOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getBrightnessOptionState(new IHisVideoApi.getBrightnessOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.235
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBrightnessOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getBrightnessOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getBrightnessRangeMax() {
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.isTVSModel(new IHisVideoApi.isTVSModelCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.35
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.isTVSModelCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mIsTvs = i2;
                        StringBuilder sbA = a.a("isTVSModel ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mIsTvs, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return 1 == mIsTvs ? 50 : 100;
    }

    public static int getBrightnessRangeMin() {
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.isTVSModel(new IHisVideoApi.isTVSModelCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.34
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.isTVSModelCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mIsTvs = i2;
                        StringBuilder sbA = a.a("isVSModel ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mIsTvs, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return 1 == mIsTvs ? -50 : 0;
    }

    public static int getBypassGammaEnable() {
        HisLogger.d(TAG, "getBypassGammaEnable");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getGammaEnable(new IHisVideoApi.getGammaEnableCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.146
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getGammaEnableCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getBypassGammaEnable ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getBypassPqEnable() {
        HisLogger.d(TAG, "getBypassPqEnable");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getPqEnable(new IHisVideoApi.getPqEnableCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.145
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getPqEnableCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getBypassPqEnable ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getBypassSceEnable() {
        HisLogger.d(TAG, "getBypassSceEnable");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getSceEnable(new IHisVideoApi.getSceEnableCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.147
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSceEnableCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getBypassSceEnable ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getBypassXvyccEnable() {
        HisLogger.d(TAG, "getBypassXvyccEnable");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getXvyccEnable(new IHisVideoApi.getXvyccEnableCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.148
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getXvyccEnableCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getBypassXvyccEnable ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getCalManCalibrated(int i) {
        HisLogger.d(TAG, "getCalManCalibrated");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getCalMANCalibrated(i, new IHisVideoApi.getCalMANCalibratedCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.264
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getCalMANCalibratedCallback
                    public void onValues(int i2, int i3) {
                        HisVideoApi.mReturnVal = i2;
                        HisVideoApi.mGetParamVal = i3;
                        StringBuilder sbA = a.a("getCalManCalibrated ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getCalManEnable() {
        HisLogger.d(TAG, "getCalManEnable");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getCalMANEnable(new IHisVideoApi.getCalMANEnableCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.263
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getCalMANEnableCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getCalManEnable ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getCalManServiceOptionState() {
        HisLogger.d(TAG, "getCalManServiceOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getItemOptionState(0, new IHisVideoApi.getItemOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.283
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getItemOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getCalManServiceOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getColorSpace() {
        HisLogger.d(TAG, "getColorSpace");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getColorSpace(new IHisVideoApi.getColorSpaceCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.90
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getColorSpaceCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getColorSpace ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getColorSpaceOptionState() {
        HisLogger.d(TAG, "getColorSpaceOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getColorSpaceOptionState(new IHisVideoApi.getColorSpaceOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.168
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getColorSpaceOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getColorSpaceOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getColorTemperature() {
        HisLogger.d(TAG, "getColorTemperature");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getColorTemperature(new IHisVideoApi.getColorTemperatureCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.62
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getColorTemperatureCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getColorTemperature ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getColorTunerApplyToAll() {
        HisLogger.d(TAG, "getColorTunerApplyToAll");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getColorTunerApplyToAll(new IHisVideoApi.getColorTunerApplyToAllCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.70
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getColorTunerApplyToAllCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getColorTunerApplyToAll ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getColorTunerBrightness() {
        HisLogger.d(TAG, "getColorTunerBrightness");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getColorTunerBrightness(new IHisVideoApi.getColorTunerBrightnessCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.67
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getColorTunerBrightnessCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getColorTunerBrightness ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getColorTunerBrightnessRangeMax() {
        return 10;
    }

    public static int getColorTunerBrightnessRangeMin() {
        return -10;
    }

    public static int getColorTunerColor() {
        HisLogger.d(TAG, "getColorTunerColor");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getColorTunerColor(new IHisVideoApi.getColorTunerColorCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.66
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getColorTunerColorCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getColorTunerColor ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getColorTunerHue() {
        HisLogger.d(TAG, "getColorTunerHue");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getColorTunerHue(new IHisVideoApi.getColorTunerHueCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.69
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getColorTunerHueCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getColorTunerHue ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getColorTunerHueRangeMax() {
        return 10;
    }

    public static int getColorTunerHueRangeMin() {
        return -10;
    }

    public static int getColorTunerOptionState() {
        HisLogger.d(TAG, "getColorTunerOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getColorTunerOptionState(new IHisVideoApi.getColorTunerOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.222
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getColorTunerOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getColorTunerOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getColorTunerSaturation() {
        HisLogger.d(TAG, "getColorTunerSaturation");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getColorTunerSaturation(new IHisVideoApi.getColorTunerSaturationCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.68
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getColorTunerSaturationCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getColorTunerSaturation ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getColorTunerSaturationRangeMax() {
        return 10;
    }

    public static int getColorTunerSaturationRangeMin() {
        return -10;
    }

    public static int getContrast() {
        HisLogger.d(TAG, "getContrast");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.isTVSModel(new IHisVideoApi.isTVSModelCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.24
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.isTVSModelCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mIsTvs = i2;
                        StringBuilder sbA = a.a("isTVSModel ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mIsTvs, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            try {
                mVideoServer.getContrast(new IHisVideoApi.getContrastCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.25
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getContrastCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        if (1 == HisVideoApi.mIsTvs) {
                            HisVideoApi.mGetParamVal = i2 - 50;
                        } else {
                            HisVideoApi.mGetParamVal = i2;
                        }
                        StringBuilder sbA = a.a("getContrast ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getContrast20() {
        HisLogger.d(TAG, "getContrast20");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getContrast20(new IHisVideoApi.getContrast20Callback() { // from class: com.hisense.avmiddleware.HisVideoApi.103
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getContrast20Callback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getContrast20 ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getContrast80() {
        HisLogger.d(TAG, "getContrast80");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getContrast80(new IHisVideoApi.getContrast80Callback() { // from class: com.hisense.avmiddleware.HisVideoApi.105
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getContrast80Callback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getContrast80 ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getContrastByFactory() {
        HisLogger.d(TAG, "getContrastByFactory");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getContrastByFactory(new IHisVideoApi.getContrastByFactoryCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.30
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getContrastByFactoryCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getContrastByFactory ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getContrastMax() {
        HisLogger.d(TAG, "getContrastMax");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getContrastMax(new IHisVideoApi.getContrastMaxCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.106
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getContrastMaxCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getContrastMax ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getContrastMid() {
        HisLogger.d(TAG, "getContrastMid");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getContrastMid(new IHisVideoApi.getContrastMidCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.104
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getContrastMidCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getContrastMid ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getContrastMin() {
        HisLogger.d(TAG, "getContrastMin");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getContrastMin(new IHisVideoApi.getContrastMinCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.102
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getContrastMinCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getContrastMin ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getContrastOptionState() {
        HisLogger.d(TAG, "getContrastOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getContrastOptionState(new IHisVideoApi.getContrastOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.236
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getContrastOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getContrastOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getContrastRangeMax() {
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.isTVSModel(new IHisVideoApi.isTVSModelCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.29
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.isTVSModelCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mIsTvs = i2;
                        StringBuilder sbA = a.a("isTVSModel ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mIsTvs, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return 1 == mIsTvs ? 50 : 100;
    }

    public static int getContrastRangeMin() {
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.isTVSModel(new IHisVideoApi.isTVSModelCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.28
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.isTVSModelCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mIsTvs = i2;
                        StringBuilder sbA = a.a("isTVSModel ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mIsTvs, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return 1 == mIsTvs ? -50 : 0;
    }

    public static int getDigitalNoiseReductionOptionState() {
        HisLogger.d(TAG, "getDigitalNoiseReductionOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getDigitalNoiseReductionOptionState(new IHisVideoApi.getDigitalNoiseReductionOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.61
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getDigitalNoiseReductionOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getDigitalNoiseReductionOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getDolbyIQAPOEnable() {
        HisLogger.d(TAG, "getDolbyIQAPOEnable");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getAPOEnable(new IHisVideoApi.getAPOEnableCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.249
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getAPOEnableCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getDolbyIQAPOEnable ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getDolbyIQEnable() {
        HisLogger.d(TAG, "getDolbyIqEnable");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getDolbyIQEnableFlag(new IHisVideoApi.getDolbyIQEnableFlagCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.244
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getDolbyIQEnableFlagCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getDolbyIQEnable ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getDolbyIQLux() {
        HisLogger.d(TAG, "getDolbyIQLux");
        mReturnVal = -1;
        mGetParamVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getItemValue(23, new IHisVideoApi.getItemValueCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.246
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getItemValueCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getDolbyIQLux ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getDolbyIQUserBinEnable() {
        HisLogger.d(TAG, "getDolbyIqEnable");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getDolbyIQUserBinEnable(new IHisVideoApi.getDolbyIQUserBinEnableCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.247
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getDolbyIQUserBinEnableCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getDolbyIQUserBinEnable ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static String getDolbyIQUserBinInfo() {
        HisLogger.d(TAG, "getDolbyIQUserBinInfo");
        mReturnVal = -1;
        mItemStr = "N/A";
        if (getHidlService()) {
            try {
                mVideoServer.getItemString(5, new IHisVideoApi.getItemStringCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.245
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getItemStringCallback
                    public void onValues(int i, String str) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mItemStr = str;
                        StringBuilder sbA = a.a("getDolbyIQUserBinInfo ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",str = ");
                        sbA.append(HisVideoApi.mItemStr);
                        HisLogger.d(HisVideoApi.TAG, sbA.toString());
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mItemStr;
    }

    public static int getDynamicBacklight() {
        HisLogger.d(TAG, "getDynamicBacklight");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getDynamicBacklight(new IHisVideoApi.getDynamicBacklightCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.4
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getDynamicBacklightCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getDynamicBacklight ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getDynamicBacklightOptionState() {
        HisLogger.d(TAG, "getDynamicBacklightOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getDynamicBacklightOptionState(new IHisVideoApi.getDynamicBacklightOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.225
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getDynamicBacklightOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getDynamicBacklightOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getDynamicContrast() {
        HisLogger.d(TAG, "getDynamicContrast");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getDynamicContrast(new IHisVideoApi.getDynamicContrastCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.255
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getDynamicContrastCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getDynamicContrast ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getDynamicToneMappingEnable() {
        HisLogger.d(TAG, "getDynamicToneMappingEnable");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getDynamicToneMappingEnable(new IHisVideoApi.getDynamicToneMappingEnableCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.261
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getDynamicToneMappingEnableCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getDynamicToneMappingEnable ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getDynamicToneMappingOptionState() {
        HisLogger.d(TAG, "getDynamicToneMappingOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getDynamicToneMappingOptionState(new IHisVideoApi.getDynamicToneMappingOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.262
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getDynamicToneMappingOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getDynamicToneMappingOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getEnergySavingEnable() {
        HisLogger.d(TAG, "getEnergySavingEnable");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getEnergySavingEnable(new IHisVideoApi.getEnergySavingEnableCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.10
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getEnergySavingEnableCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getEnergySavingEnable ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getEnergySavingOptionState() {
        HisLogger.d(TAG, "getEnergySavingOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getEnergySavingOptionState(new IHisVideoApi.getEnergySavingOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.228
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getEnergySavingOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getEnergySavingOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getEnhancedViewingAngleEnable() {
        HisLogger.d(TAG, "getEnhancedViewingAngleEnable");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getEnhancedViewingAngleEnable(new IHisVideoApi.getEnhancedViewingAngleEnableCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.57
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getEnhancedViewingAngleEnableCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getEnhancedViewingAngleEnable ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getEnhancedViewingAngleOptionState() {
        HisLogger.d(TAG, "getEnhancedViewingAngleOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getEnhancedViewingAngleOptionState(new IHisVideoApi.getEnhancedViewingAngleOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.167
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getEnhancedViewingAngleOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getEnhancedViewingAngleOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getFRCVersion() {
        HisLogger.d(TAG, "getFRCVersion");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getFRCVersion(new IHisVideoApi.getFRCVersionCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.287
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFRCVersionCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getFRCVersion ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getFactoryColorTemperature() {
        HisLogger.d(TAG, "getFactoryColorTemperature");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getFactoryColorTemperature(new IHisVideoApi.getFactoryColorTemperatureCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.133
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFactoryColorTemperatureCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getFactoryColorTemperature ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getFactoryColorTunerBrightness() {
        HisLogger.d(TAG, "getFactoryColorTunerBrightness");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getFactoryColorTunerBrightness(new IHisVideoApi.getFactoryColorTunerBrightnessCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.141
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFactoryColorTunerBrightnessCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getFactoryColorTunerBrightness ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getFactoryColorTunerColor() {
        HisLogger.d(TAG, "getFactoryColorTunerColor");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getFactoryColorTunerColor(new IHisVideoApi.getFactoryColorTunerColorCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.140
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFactoryColorTunerColorCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getFactoryColorTunerColor ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getFactoryColorTunerHue() {
        HisLogger.d(TAG, "getFactoryColorTunerHue");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getFactoryColorTunerHue(new IHisVideoApi.getFactoryColorTunerHueCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.143
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFactoryColorTunerHueCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getFactoryColorTunerHue ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getFactoryColorTunerSaturation() {
        HisLogger.d(TAG, "getFactoryColorTunerSaturation");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getFactoryColorTunerSaturation(new IHisVideoApi.getFactoryColorTunerSaturationCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.142
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFactoryColorTunerSaturationCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getFactoryColorTunerSaturation ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getFactoryMultiPointWhiteBalanceBlue() {
        HisLogger.d(TAG, "getFactoryMultiPointWhiteBalanceBlue");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getFactoryMultiPointWhiteBalanceBlue(new IHisVideoApi.getFactoryMultiPointWhiteBalanceBlueCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.89
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFactoryMultiPointWhiteBalanceBlueCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getFactoryMultiPointWhiteBalanceBlue ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getFactoryMultiPointWhiteBalanceGreen() {
        HisLogger.d(TAG, "getFactoryMultiPointWhiteBalanceGreen");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getFactoryMultiPointWhiteBalanceGreen(new IHisVideoApi.getFactoryMultiPointWhiteBalanceGreenCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.88
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFactoryMultiPointWhiteBalanceGreenCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getFactoryMultiPointWhiteBalanceGreen ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getFactoryMultiPointWhiteBalanceLevel() {
        HisLogger.d(TAG, "getFactoryMultiPointWhiteBalanceLevel");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getFactoryMultiPointWhiteBalanceLevel(new IHisVideoApi.getFactoryMultiPointWhiteBalanceLevelCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.86
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFactoryMultiPointWhiteBalanceLevelCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getFactoryMultiPointWhiteBalanceLevel ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getFactoryMultiPointWhiteBalanceRed() {
        HisLogger.d(TAG, "getFactoryMultiPointWhiteBalanceRed");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getFactoryMultiPointWhiteBalanceRed(new IHisVideoApi.getFactoryMultiPointWhiteBalanceRedCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.87
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFactoryMultiPointWhiteBalanceRedCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getFactoryMultiPointWhiteBalanceRed ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getFactoryWhiteBalanceGainBlue() {
        HisLogger.d(TAG, "getFactoryWhiteBalanceGainBlue");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getFactoryWhiteBalanceGainBlue(new IHisVideoApi.getFactoryWhiteBalanceGainBlueCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.139
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFactoryWhiteBalanceGainBlueCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getFactoryWhiteBalanceGainBlue ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getFactoryWhiteBalanceGainGreen() {
        HisLogger.d(TAG, "getFactoryWhiteBalanceGainGreen");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getFactoryWhiteBalanceGainGreen(new IHisVideoApi.getFactoryWhiteBalanceGainGreenCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.138
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFactoryWhiteBalanceGainGreenCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getFactoryWhiteBalanceGainGreen ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getFactoryWhiteBalanceGainRed() {
        HisLogger.d(TAG, "getFactoryWhiteBalanceGainRed");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getFactoryWhiteBalanceGainRed(new IHisVideoApi.getFactoryWhiteBalanceGainRedCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.137
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFactoryWhiteBalanceGainRedCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getFactoryWhiteBalanceGainRed ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getFactoryWhiteBalanceOffsetBlue() {
        HisLogger.d(TAG, "getFactoryWhiteBalanceOffsetBlue");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getFactoryWhiteBalanceOffsetBlue(new IHisVideoApi.getFactoryWhiteBalanceOffsetBlueCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.136
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFactoryWhiteBalanceOffsetBlueCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getFactoryWhiteBalanceOffsetBlue ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getFactoryWhiteBalanceOffsetGreen() {
        HisLogger.d(TAG, "getFactoryWhiteBalanceOffsetGreen");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getFactoryWhiteBalanceOffsetGreen(new IHisVideoApi.getFactoryWhiteBalanceOffsetGreenCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.135
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFactoryWhiteBalanceOffsetGreenCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getFactoryWhiteBalanceOffsetGreen ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getFactoryWhiteBalanceOffsetRed() {
        HisLogger.d(TAG, "getFactoryWhiteBalanceOffsetRed");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getFactoryWhiteBalanceOffsetRed(new IHisVideoApi.getFactoryWhiteBalanceOffsetRedCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.134
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFactoryWhiteBalanceOffsetRedCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getFactoryWhiteBalanceOffsetRed ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getFilmmakerDetectionEnable() {
        HisLogger.d(TAG, "getFilmmakerDetectionEnable");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getFilmmakerDetectionEnable(new IHisVideoApi.getFilmmakerDetectionEnableCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.151
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFilmmakerDetectionEnableCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getFilmmakerDetectionEnable ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getFilmmakerModeSelected() {
        HisLogger.d(TAG, "getFilmmakerModeSelected");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getFilmmakerModeSelected(new IHisVideoApi.getFilmmakerModeSelectedCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.152
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFilmmakerModeSelectedCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getFilmmakerModeSelected ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getFilmmakerOptionState() {
        HisLogger.d(TAG, "getFilmmakerOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getFilmmakerOptionState(new IHisVideoApi.getFilmmakerOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.172
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFilmmakerOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getFilmmakerOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getFreeSyncOptionState() {
        HisLogger.d(TAG, "getFreeSyncOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getFreeSyncOptionState(new IHisVideoApi.getFreeSyncOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.232
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFreeSyncOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getFreeSyncOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getFreeSyncSwitch() {
        HisLogger.d(TAG, "getFreeSyncSwitch");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getFreeSyncSwitch(new IHisVideoApi.getFreeSyncSwitchCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.231
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFreeSyncSwitchCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getFreeSyncSwitch ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getGameDetectionEnable() {
        HisLogger.d(TAG, "getGameDetectionEnable");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getGameDetectionEnable(new IHisVideoApi.getGameDetectionEnableCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.154
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getGameDetectionEnableCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getGameDetectionEnable ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getGammaAdjustOptionState() {
        HisLogger.d(TAG, "getGammaAdjustOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getGammaAdjustOptionState(new IHisVideoApi.getGammaAdjustOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.170
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getGammaAdjustOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getGammaAdjustOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getGammaAdjustment() {
        HisLogger.d(TAG, "getGammaAdjustment");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getGammaAdjustment(new IHisVideoApi.getGammaAdjustmentCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.92
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getGammaAdjustmentCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getGammaAdjustment ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getGammaCalibrationGain() {
        HisLogger.d(TAG, "getGammaCalibrationGain");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getGammaCalibrationGain(new IHisVideoApi.getGammaCalibrationGainCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.94
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getGammaCalibrationGainCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getGammaCalibrationGain ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getGammaCalibrationGainRangeMax() {
        return 25;
    }

    public static int getGammaCalibrationGainRangeMin() {
        return -25;
    }

    public static int getGammaCalibrationLevel() {
        HisLogger.d(TAG, "getGammaCalibrationLevel");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getGammaCalibrationLevel(new IHisVideoApi.getGammaCalibrationLevelCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.93
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getGammaCalibrationLevelCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getGammaCalibrationLevel ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getGammaCalibrationOptionState() {
        HisLogger.d(TAG, "getGammaCalibrationOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getGammaCalibrationOptionState(new IHisVideoApi.getGammaCalibrationOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.242
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getGammaCalibrationOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getGammaCalibrationOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getHDMI20Format() {
        HisLogger.d(TAG, "getHDMI20Format");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getHDMI20Format(new IHisVideoApi.getHDMI20FormatCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.96
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getHDMI20FormatCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getHDMI20Format ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getHDMIDynamicRangeOptionState() {
        HisLogger.d(TAG, "getHDMIDynamicRangeOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getHDMIDynamicRangeOptionState(new IHisVideoApi.getHDMIDynamicRangeOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.169
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getHDMIDynamicRangeOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getHDMIDynamicRangeOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getHdmiDynamicRange() {
        HisLogger.d(TAG, "getHdmiDynamicRange");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getHdmiDynamicRange(new IHisVideoApi.getHdmiDynamicRangeCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.91
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getHdmiDynamicRangeCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getHdmiDynamicRange ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static boolean getHidlService() {
        try {
            mVideoServer = IHisVideoApi.getService();
            return mVideoServer != null;
        } catch (Exception e2) {
            HisLogger.e(TAG, "get Video HIDL Service exception" + e2);
            return false;
        }
    }

    public static int getHue20() {
        HisLogger.d(TAG, "getHue20");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getHue20(new IHisVideoApi.getHue20Callback() { // from class: com.hisense.avmiddleware.HisVideoApi.113
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getHue20Callback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getHue20 ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getHue80() {
        HisLogger.d(TAG, "getHue80");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getHue80(new IHisVideoApi.getHue80Callback() { // from class: com.hisense.avmiddleware.HisVideoApi.115
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getHue80Callback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getHue80 ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getHueMax() {
        HisLogger.d(TAG, "getHueMax");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getHueMax(new IHisVideoApi.getHueMaxCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.116
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getHueMaxCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getHueMax ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getHueMid() {
        HisLogger.d(TAG, "getHueMid");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getHueMid(new IHisVideoApi.getHueMidCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.114
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getHueMidCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getHueMid ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getHueMin() {
        HisLogger.d(TAG, "getHueMin");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getHueMin(new IHisVideoApi.getHueMinCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.112
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getHueMinCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getHueMin ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getHueOptionState() {
        HisLogger.d(TAG, "getHueOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getHueOptionState(new IHisVideoApi.getHueOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.238
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getHueOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getHueOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getIMAXOptionState() {
        HisLogger.d(TAG, "getIMAXOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getIMAXOptionState(new IHisVideoApi.getIMAXOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.248
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getIMAXOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getIMAXOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getISFOptionState() {
        HisLogger.d(TAG, "getISFOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getISFOptionState(new IHisVideoApi.getISFOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.195
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getISFOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getISFOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getISFSwitch() {
        HisLogger.d(TAG, "getISFSwitch");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getISFSwitch(new IHisVideoApi.getISFSwitchCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.194
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getISFSwitchCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getISFSwitch ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getISFType() {
        HisLogger.d(TAG, "getISFType");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getISFType(new IHisVideoApi.getISFTypeCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.196
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getISFTypeCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getISFType ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getInstantGameResponseOptionState() {
        HisLogger.d(TAG, "getInstantGameResponseOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getInstantGameResponseOptionState(new IHisVideoApi.getInstantGameResponseOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.230
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getInstantGameResponseOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getInstantGameResponseOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getInstantGameResponseSwitch() {
        HisLogger.d(TAG, "getInstantGameResponseSwitch");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getInstantGameResponseSwitch(new IHisVideoApi.getInstantGameResponseSwitchCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.229
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getInstantGameResponseSwitchCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getInstantGameResponseSwitch ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getIsTechnicolorHDR() {
        HisLogger.d(TAG, "getIsTechnicolorHDR");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getIsTechnicolorHDR(new IHisVideoApi.getIsTechnicolorHDRCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.275
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getIsTechnicolorHDRCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getIsTechnicolorHDR ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getJudderReductionOptionState() {
        HisLogger.d(TAG, "getJudderReductionOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getJudderReductionOptionState(new IHisVideoApi.getJudderReductionOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.53
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getJudderReductionOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getJudderReductionOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getLaserScreenSize() {
        HisLogger.d(TAG, "getLaserScreenSize");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getLaserScreenSize(new IHisVideoApi.getLaserScreenSizeCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.269
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLaserScreenSizeCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getLaserScreenSize ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getLaserScreenSizeOptionState() {
        HisLogger.d(TAG, "getLaserScreenSizeOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getLaserScreenSizeOptionState(new IHisVideoApi.getLaserScreenSizeOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.270
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLaserScreenSizeOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getLaserScreenSizeOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getLaserScreenType() {
        HisLogger.d(TAG, "getLaserScreenType");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getLaserScreenType(new IHisVideoApi.getLaserScreenTypeCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.271
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLaserScreenTypeCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getLaserScreenType ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getLaserScreenTypeOptionState() {
        HisLogger.d(TAG, "getLaserScreenTypeOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getLaserScreenTypeOptionState(new IHisVideoApi.getLaserScreenTypeOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.272
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLaserScreenTypeOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getLaserScreenTypeOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getLightSensorALS10() {
        HisLogger.d(TAG, "getLightSensorALS10");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getLightSensorALS10(new IHisVideoApi.getLightSensorALS10Callback() { // from class: com.hisense.avmiddleware.HisVideoApi.198
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLightSensorALS10Callback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getLightSensorALS10 ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getLightSensorALS300() {
        HisLogger.d(TAG, "getLightSensorALS300");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getLightSensorALS300(new IHisVideoApi.getLightSensorALS300Callback() { // from class: com.hisense.avmiddleware.HisVideoApi.200
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLightSensorALS300Callback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getLightSensorALS300 ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getLightSensorALS80() {
        HisLogger.d(TAG, "getLightSensorALS80");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getLightSensorALS80(new IHisVideoApi.getLightSensorALS80Callback() { // from class: com.hisense.avmiddleware.HisVideoApi.199
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLightSensorALS80Callback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getLightSensorALS80 ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getLightSensorALSMax() {
        HisLogger.d(TAG, "getLightSensorALSMax");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getLightSensorALSMax(new IHisVideoApi.getLightSensorALSMaxCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.201
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLightSensorALSMaxCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getLightSensorALSMax ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getLightSensorALSMin() {
        HisLogger.d(TAG, "getLightSensorALSMin");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getLightSensorALSMin(new IHisVideoApi.getLightSensorALSMinCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.197
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLightSensorALSMinCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getLightSensorALSMin ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getLightSensorCurve(final int i, final int i2) {
        HisLogger.d(TAG, "getLightSensorCurve");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getLightSensorCurve(i, i2, new IHisVideoApi.getLightSensorCurveCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.15
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLightSensorCurveCallback
                    public void onValues(int i3, int i4) {
                        HisVideoApi.mReturnVal = i3;
                        HisVideoApi.mGetParamVal = i4;
                        StringBuilder sbA = a.a("getLightSensorCurve ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append("mode: ");
                        sbA.append(i);
                        sbA.append("index: ");
                        sbA.append(i2);
                        sbA.append("value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getLightSensorEnable() {
        HisLogger.d(TAG, "getLightSensorEnable");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getLightSensorEnable(new IHisVideoApi.getLightSensorEnableCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.12
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLightSensorEnableCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getLightSensorEnable ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getLightSensorLS10() {
        HisLogger.d(TAG, "getLightSensorLS10");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getLightSensorLS10(new IHisVideoApi.getLightSensorLS10Callback() { // from class: com.hisense.avmiddleware.HisVideoApi.203
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLightSensorLS10Callback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getLightSensorLS10 ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getLightSensorLS300() {
        HisLogger.d(TAG, "getLightSensorLS300");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getLightSensorLS300(new IHisVideoApi.getLightSensorLS300Callback() { // from class: com.hisense.avmiddleware.HisVideoApi.205
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLightSensorLS300Callback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getLightSensorLS300 ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getLightSensorLS80() {
        HisLogger.d(TAG, "getLightSensorLS80");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getLightSensorLS80(new IHisVideoApi.getLightSensorLS80Callback() { // from class: com.hisense.avmiddleware.HisVideoApi.204
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLightSensorLS80Callback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getLightSensorLS80 ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getLightSensorLSMax() {
        HisLogger.d(TAG, "getLightSensorLSMax");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getLightSensorLSMax(new IHisVideoApi.getLightSensorLSMaxCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.206
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLightSensorLSMaxCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getLightSensorLSMax ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getLightSensorLSMin() {
        HisLogger.d(TAG, "getLightSensorLSMin");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getLightSensorLSMin(new IHisVideoApi.getLightSensorLSMinCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.202
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLightSensorLSMinCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getLightSensorLSMin ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getLightSensorLux() {
        HisLogger.d(TAG, "getLightSensorLux");
        mReturnVal = -1;
        mGetParamVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getLightSensorLux(new IHisVideoApi.getLightSensorLuxCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.14
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLightSensorLuxCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getLightSensorLux ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getLightSensorMax() {
        HisLogger.d(TAG, "getLightSensorMax");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getLightSensorMax(new IHisVideoApi.getLightSensorMaxCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.19
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLightSensorMaxCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getLightSensorMax ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getLightSensorMaxRangeMax() {
        return 5;
    }

    public static int getLightSensorMaxRangeMin() {
        return 1;
    }

    public static int getLightSensorMin() {
        HisLogger.d(TAG, "getLightSensorMin");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getLightSensorMin(new IHisVideoApi.getLightSensorMinCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.16
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLightSensorMinCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getLightSensorMin ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getLightSensorMinRangeMax() {
        return 10;
    }

    public static int getLightSensorMinRangeMin() {
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.isDolbyBrightness(new IHisVideoApi.isDolbyBrightnessCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.17
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.isDolbyBrightnessCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mIsDolbyBright = i2;
                        StringBuilder sbA = a.a("isDolbyBrightness ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mIsDolbyBright, HisVideoApi.TAG);
                    }
                });
                mVideoServer.getAPOEnable(new IHisVideoApi.getAPOEnableCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.18
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getAPOEnableCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mIsDolbyIQEnable = i2;
                        StringBuilder sbA = a.a("getDolbyIQAPOEnable ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mIsDolbyIQEnable, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return (mIsDolbyBright == 0 || mIsDolbyIQEnable == 0) ? -10 : 0;
    }

    public static int getLightSensorOptionState() {
        HisLogger.d(TAG, "getLightSensorOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getLightSensorOptionState(new IHisVideoApi.getLightSensorOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.164
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLightSensorOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getLightSensorOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getLightSensorValue() {
        HisLogger.d(TAG, "getLightSensorValue");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getLightSensorValue(new IHisVideoApi.getLightSensorValueCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.13
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLightSensorValueCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getLightSensorValue ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getLinkSoundEnable() {
        HisLogger.d(TAG, "getLinkSoundEnable");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getLinkSoundEnable(new IHisVideoApi.getLinkSoundEnableCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.192
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLinkSoundEnableCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getLinkSoundEnable ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getLinkSoundOptionState() {
        HisLogger.d(TAG, "getLinkSoundOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getLinkSoundOptionState(new IHisVideoApi.getLinkSoundOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.193
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLinkSoundOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getLinkSoundOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getLocalDimming() {
        HisLogger.d(TAG, "getLocalDimming");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getLocalDimming(new IHisVideoApi.getLocalDimmingCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.3
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLocalDimmingCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getLocalDimming ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getLocalDimmingOptionState() {
        HisLogger.d(TAG, "getLocalDimmingOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getLocalDimmingOptionState(new IHisVideoApi.getLocalDimmingOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.163
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLocalDimmingOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getLocalDimmingOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getLowBlueLight() {
        HisLogger.d(TAG, "getLowBlueLight");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getLowBlueLight(new IHisVideoApi.getLowBlueLightCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.11
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLowBlueLightCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getLowBlueLight ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getLowBlueLightOptionState() {
        HisLogger.d(TAG, "getLowBlueLightOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getLowBlueLightOptionState(new IHisVideoApi.getLowBlueLightOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.161
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLowBlueLightOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getLowBlueLightOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getMaximumBacklightOptionState() {
        HisLogger.d(TAG, "getMaximumBacklightOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getMaximumBacklightOptionState(new IHisVideoApi.getMaximumBacklightOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.227
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getMaximumBacklightOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getMaximumBacklightOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getMemc() {
        HisLogger.d(TAG, "getMemc");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getMemc(new IHisVideoApi.getMemcCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.51
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getMemcCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getMemc ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getMemcBlurReduction() {
        HisLogger.d(TAG, "getMemcBlurReduction");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getMemcBlurReduction(new IHisVideoApi.getMemcBlurReductionCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.54
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getMemcBlurReductionCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getMemcBlurReduction ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getMemcBlurReductionRangeMax() {
        return 10;
    }

    public static int getMemcBlurReductionRangeMin() {
        return 0;
    }

    public static int getMemcJudderReduction() {
        HisLogger.d(TAG, "getMemcJudderReduction");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getMemcJudderReduction(new IHisVideoApi.getMemcJudderReductionCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.52
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getMemcJudderReductionCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getMemcJudderReduction ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getMemcJudderReductionRangeMax() {
        return 10;
    }

    public static int getMemcJudderReductionRangeMin() {
        return 0;
    }

    public static int getMemcOptionState() {
        HisLogger.d(TAG, "getMemcOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getMemcOptionState(new IHisVideoApi.getMemcOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.165
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getMemcOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getMemcOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getMinimumBacklightOptionState() {
        HisLogger.d(TAG, "getMinimumBacklightOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getMinimumBacklightOptionState(new IHisVideoApi.getMinimumBacklightOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.226
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getMinimumBacklightOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getMinimumBacklightOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getMotionClearnessEnable() {
        HisLogger.d(TAG, "getMotionClearnessEnable");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getMotionClearnessEnable(new IHisVideoApi.getMotionClearnessEnableCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.56
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getMotionClearnessEnableCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getMotionClearnessEnable ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getMotionClearnessOptionState() {
        HisLogger.d(TAG, "getMotionClearnessOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getMotionClearnessOptionState(new IHisVideoApi.getMotionClearnessOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.166
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getMotionClearnessOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getMotionClearnessOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getMpegNoiseReduction() {
        HisLogger.d(TAG, "getMpegNoiseReduction");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getMpegNoiseReduction(new IHisVideoApi.getMpegNoiseReductionCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.60
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getMpegNoiseReductionCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getMpegNoiseReduction ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getMultiPointWhiteBalanceApplyToAll() {
        HisLogger.d(TAG, "getMultiPointWhiteBalanceApplyToAll");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getMultiPointWhiteBalanceApplyToAll(new IHisVideoApi.getMultiPointWhiteBalanceApplyToAllCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.85
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getMultiPointWhiteBalanceApplyToAllCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getMultiPointWhiteBalanceApplyToAll ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getMultiPointWhiteBalanceBlue() {
        HisLogger.d(TAG, "getMultiPointWhiteBalanceBlue");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getMultiPointWhiteBalanceBlue(new IHisVideoApi.getMultiPointWhiteBalanceBlueCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.84
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getMultiPointWhiteBalanceBlueCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getMultiPointWhiteBalanceBlue ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getMultiPointWhiteBalanceBlueRangeMax() {
        return 1 == isCalManCalibrated() ? 100 : 25;
    }

    public static int getMultiPointWhiteBalanceBlueRangeMin() {
        return 1 == isCalManCalibrated() ? -100 : -25;
    }

    public static int getMultiPointWhiteBalanceEnable() {
        HisLogger.d(TAG, "getMultiPointWhiteBalanceEnable");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getMultiPointWhiteBalanceEnable(new IHisVideoApi.getMultiPointWhiteBalanceEnableCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.78
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getMultiPointWhiteBalanceEnableCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getMultiPointWhiteBalanceEnable ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getMultiPointWhiteBalanceGreen() {
        HisLogger.d(TAG, "getMultiPointWhiteBalanceGreen");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getMultiPointWhiteBalanceGreen(new IHisVideoApi.getMultiPointWhiteBalanceGreenCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.83
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getMultiPointWhiteBalanceGreenCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getMultiPointWhiteBalanceGreen ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getMultiPointWhiteBalanceGreenRangeMax() {
        return 1 == isCalManCalibrated() ? 100 : 25;
    }

    public static int getMultiPointWhiteBalanceGreenRangeMin() {
        return 1 == isCalManCalibrated() ? -100 : -25;
    }

    public static int getMultiPointWhiteBalanceLevel() {
        HisLogger.d(TAG, "getMultiPointWhiteBalanceLevel");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getMultiPointWhiteBalanceLevel(new IHisVideoApi.getMultiPointWhiteBalanceLevelCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.79
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getMultiPointWhiteBalanceLevelCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getMultiPointWhiteBalanceLevel ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getMultiPointWhiteBalanceOptionState() {
        HisLogger.d(TAG, "getMultiPointWhiteBalanceOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getMultiPointWhiteBalanceOptionState(new IHisVideoApi.getMultiPointWhiteBalanceOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.241
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getMultiPointWhiteBalanceOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getMultiPointWhiteBalanceOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getMultiPointWhiteBalanceRed() {
        HisLogger.d(TAG, "getMultiPointWhiteBalanceRed");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getMultiPointWhiteBalanceRed(new IHisVideoApi.getMultiPointWhiteBalanceRedCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.80
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getMultiPointWhiteBalanceRedCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getMultiPointWhiteBalanceRed ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getMultiPointWhiteBalanceRedRangeMax() {
        return 1 == isCalManCalibrated() ? 100 : 25;
    }

    public static int getMultiPointWhiteBalanceRedRangeMin() {
        return 1 == isCalManCalibrated() ? -100 : -25;
    }

    public static int getNetVideoBeautySwitch() {
        HisLogger.d(TAG, "getNetVideoBeautySwitch");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getNetVideoBeautySwitch(new IHisVideoApi.getNetVideoBeautySwitchCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.65
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getNetVideoBeautySwitchCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getNetVideoBeautySwitch ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getNoiseReduction() {
        HisLogger.d(TAG, "getNoiseReduction");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getNoiseReduction(new IHisVideoApi.getNoiseReductionCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.58
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getNoiseReductionCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getNoiseReduction ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getNoiseReductionOptionState() {
        HisLogger.d(TAG, "getNoiseReductionOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getNoiseReductionOptionState(new IHisVideoApi.getNoiseReductionOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.59
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getNoiseReductionOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getNoiseReductionOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getOLEDHDREnable() {
        HisLogger.d(TAG, "getOLEDHDREnable");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getOLEDHDREnable(new IHisVideoApi.getOLEDHDREnableCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.284
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getOLEDHDREnableCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getOLEDHDREnable ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getOLEDLEAEnable() {
        HisLogger.d(TAG, "getOLEDLEAEnable");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getOLEDLEAEnable(new IHisVideoApi.getOLEDLEAEnableCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.282
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getOLEDLEAEnableCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getOLEDLEAEnable ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getOLEDLightPercent() {
        HisLogger.d(TAG, "getOLEDlightPercent");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getBacklightPercent(new IHisVideoApi.getBacklightPercentCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.8
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBacklightPercentCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getOLEDlightPercent ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getOLEDLightPercentRangeMax() {
        return 100;
    }

    public static int getOLEDLightPercentRangeMin() {
        return 0;
    }

    public static int getOLEDLocalFrozenChkEnable() {
        HisLogger.d(TAG, "getOLEDLocalFrozenChkEnable");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getOLEDLocalFrozenChkEnable(new IHisVideoApi.getOLEDLocalFrozenChkEnableCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.280
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getOLEDLocalFrozenChkEnableCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getOLEDLocalFrozenChkEnable ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getOLEDLocalLumaChkEnable() {
        HisLogger.d(TAG, "getOLEDLocalLumaChkEnable");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getOLEDLocalLumaChkEnable(new IHisVideoApi.getOLEDLocalLumaChkEnableCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.281
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getOLEDLocalLumaChkEnableCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getOLEDLocalLumaChkEnable ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getOLEDPLCCurve() {
        HisLogger.d(TAG, "getOLEDPLCCurve");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getOLEDPLCCurve(new IHisVideoApi.getOLEDPLCCurveCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.279
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getOLEDPLCCurveCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getOLEDPLCCurve ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getOLEDPLCCurveOptionState() {
        HisLogger.d(TAG, "getOLEDPLCCurveOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getOLEDPLCCurveOptionState(new IHisVideoApi.getOLEDPLCCurveOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.285
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getOLEDPLCCurveOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getOLEDPLCCurveOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getOLEDPeakingBrightness() {
        HisLogger.d(TAG, "getOLEDPeakingBrightness");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getDynamicBacklight(new IHisVideoApi.getDynamicBacklightCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.5
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getDynamicBacklightCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getOLEDPeakingBrightness ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getOLEDTPCEnable() {
        HisLogger.d(TAG, "getOLEDTPCEnable");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getOLEDTPCEnable(new IHisVideoApi.getOLEDTPCEnableCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.286
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getOLEDTPCEnableCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getOLEDTPCEnable ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getOptionStateColorTemperature() {
        HisLogger.d(TAG, "getOptionStateColorTemperature");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getOptionStateColorTemperature(new IHisVideoApi.getOptionStateColorTemperatureCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.63
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getOptionStateColorTemperatureCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getOptionStateColorTemperature ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getOverscanBottom() {
        HisLogger.d(TAG, "getOverscanBottom");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getOverscanBottom(new IHisVideoApi.getOverscanBottomCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.129
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getOverscanBottomCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getOverscanBottom ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getOverscanEnable() {
        HisLogger.d(TAG, "getOverscanEnable");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getOverscanEnable(new IHisVideoApi.getOverscanEnableCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.2
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getOverscanEnableCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getOverscanEnable ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getOverscanLeft() {
        HisLogger.d(TAG, "getOverscanLeft");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getOverscanLeft(new IHisVideoApi.getOverscanLeftCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.130
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getOverscanLeftCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getOverscanLeft ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getOverscanOptionState() {
        HisLogger.d(TAG, "getOverscanOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getOverscanOptionState(new IHisVideoApi.getOverscanOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.156
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getOverscanOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getOverscanOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getOverscanRight() {
        HisLogger.d(TAG, "getOverscanRight");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getOverscanRight(new IHisVideoApi.getOverscanRightCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.131
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getOverscanRightCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getOverscanRight ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getOverscanTop() {
        HisLogger.d(TAG, "getOverscanTop");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getOverscanTop(new IHisVideoApi.getOverscanTopCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.128
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getOverscanTopCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getOverscanTop ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getPanel() {
        HisLogger.d(TAG, "getPanel");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getPanel(new IHisVideoApi.getPanelCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.132
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getPanelCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getPanel ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getPictureMode() {
        HisLogger.d(TAG, "getPictureMode");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getPictureMode(new IHisVideoApi.getPictureModeCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.20
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getPictureModeCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getPictureMode ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getPictureModeByFactory() {
        HisLogger.d(TAG, "getPictureModeByFactory");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getPictureModeByFactory(new IHisVideoApi.getPictureModeByFactoryCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.21
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getPictureModeByFactoryCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getPictureModeByFactory ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getPictureModeGameModeOptionState() {
        HisLogger.d(TAG, "getPictureModeGameModeOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getPictureModeGameModeOptionState(new IHisVideoApi.getPictureModeGameModeOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.171
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getPictureModeGameModeOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getPictureModeGameModeOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getPictureSettingsToAllSource() {
        HisLogger.d(TAG, "getPictureSettingsToAllSource");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getPictureSettingsToAllSource(new IHisVideoApi.getPictureSettingsToAllSourceCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.22
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getPictureSettingsToAllSourceCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getPictureSettingsToAllSource ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getPixelShiftEnable() {
        HisLogger.d(TAG, "getPixelShiftEnable");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getPixelShiftEnable(new IHisVideoApi.getPixelShiftEnableCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.276
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getPixelShiftEnableCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getPixelShiftEnable ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getPixelShiftX() {
        HisLogger.d(TAG, "getPixelShiftEnable");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getPixelShiftEnable(new IHisVideoApi.getPixelShiftEnableCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.277
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getPixelShiftEnableCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getPixelShiftEnable ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getPixelShiftY() {
        HisLogger.d(TAG, "getPixelShiftEnable");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getPixelShiftEnable(new IHisVideoApi.getPixelShiftEnableCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.278
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getPixelShiftEnableCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getPixelShiftEnable ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getQDCtrlPQUpdateEnable() {
        HisLogger.d(TAG, "getQDCtrlPQUpdateEnable");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getQDCtrlPQUpdateEnable(new IHisVideoApi.getQDCtrlPQUpdateEnableCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.253
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getQDCtrlPQUpdateEnableCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getQDCtrlPQUpdateEnable ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getRGBOnlyOptionState() {
        HisLogger.d(TAG, "getRGBOnlyOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getRGBOnlyOptionState(new IHisVideoApi.getRGBOnlyOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.221
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getRGBOnlyOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getRGBOnlyOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getRgbOnly() {
        HisLogger.d(TAG, "getRgbOnly");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getRgbOnly(new IHisVideoApi.getRgbOnlyCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.95
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getRgbOnlyCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getRgbOnly ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getSaturation() {
        HisLogger.d(TAG, "getSaturation");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.isTVSModel(new IHisVideoApi.isTVSModelCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.38
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.isTVSModelCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mIsTvs = i2;
                        StringBuilder sbA = a.a("isTVSMode ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mIsTvs, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            try {
                mVideoServer.getSaturation(new IHisVideoApi.getSaturationCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.39
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSaturationCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        if (1 == HisVideoApi.mIsTvs) {
                            HisVideoApi.mGetParamVal = i2 - 50;
                        } else {
                            HisVideoApi.mGetParamVal = i2;
                        }
                        StringBuilder sbA = a.a("getSaturation ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getSaturation20() {
        HisLogger.d(TAG, "getSaturation20");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getSaturation20(new IHisVideoApi.getSaturation20Callback() { // from class: com.hisense.avmiddleware.HisVideoApi.108
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSaturation20Callback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getSaturation20 ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getSaturation80() {
        HisLogger.d(TAG, "getSaturation80");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getSaturation80(new IHisVideoApi.getSaturation80Callback() { // from class: com.hisense.avmiddleware.HisVideoApi.110
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSaturation80Callback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getSaturation80 ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getSaturationByFactory() {
        HisLogger.d(TAG, "getSaturationByFactory");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getSaturationByFactory(new IHisVideoApi.getSaturationByFactoryCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.42
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSaturationByFactoryCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getSaturationByFactory ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getSaturationMax() {
        HisLogger.d(TAG, "getSaturationMax");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getSaturationMax(new IHisVideoApi.getSaturationMaxCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.111
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSaturationMaxCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getSaturationMax ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getSaturationMid() {
        HisLogger.d(TAG, "getSaturationMid");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getSaturationMid(new IHisVideoApi.getSaturationMidCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.109
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSaturationMidCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getSaturationMid ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getSaturationMin() {
        HisLogger.d(TAG, "getSaturationMin");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getSaturationMin(new IHisVideoApi.getSaturationMinCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.107
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSaturationMinCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getSaturationMin ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getSaturationOptionState() {
        HisLogger.d(TAG, "getSaturationOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getSaturationOptionState(new IHisVideoApi.getSaturationOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.237
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSaturationOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getSaturationOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getSaturationRangeMax() {
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.isTVSModel(new IHisVideoApi.isTVSModelCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.41
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.isTVSModelCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mIsTvs = i2;
                        StringBuilder sbA = a.a("isTVSModel ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mIsTvs, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return 1 == mIsTvs ? 50 : 100;
    }

    public static int getSaturationRangeMin() {
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.isTVSModel(new IHisVideoApi.isTVSModelCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.40
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.isTVSModelCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mIsTvs = i2;
                        StringBuilder sbA = a.a("isTVSMode ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mIsTvs, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return 1 == mIsTvs ? -50 : 0;
    }

    public static int[] getScreenOutputDispRect() {
        HisLogger.d(TAG, "getScreenOutputDispRect");
        mReturnVal = -1;
        final int[] iArr = {-1, -1, -1, -1};
        if (getHidlService()) {
            try {
                mVideoServer.getScreenOutputDispRect(new IHisVideoApi.getScreenOutputDispRectCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.207
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getScreenOutputDispRectCallback
                    public void onValues(int i, int i2, int i3, int i4, int i5) {
                        HisVideoApi.mReturnVal = i;
                        int[] iArr2 = iArr;
                        iArr2[0] = i2;
                        iArr2[1] = i3;
                        iArr2[2] = i4;
                        iArr2[3] = i5;
                        StringBuilder sbA = a.a("getScreenOutputDispRect ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(", x = ");
                        sbA.append(iArr[0]);
                        sbA.append(", y = ");
                        sbA.append(iArr[1]);
                        sbA.append(", width = ");
                        sbA.append(iArr[2]);
                        sbA.append(", height = ");
                        a.c(sbA, iArr[3], HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return iArr;
    }

    public static int[] getScreenOutputDispRectEx(int i) {
        HisLogger.d(TAG, "getScreenOutputDispRect");
        mReturnVal = -1;
        final int[] iArr = {-1, -1, -1, -1};
        if (getHidlService()) {
            try {
                if (i == 0) {
                    mVideoServer.getScreenOutputDispRect(new IHisVideoApi.getScreenOutputDispRectCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.208
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getScreenOutputDispRectCallback
                        public void onValues(int i2, int i3, int i4, int i5, int i6) {
                            HisVideoApi.mReturnVal = i2;
                            int[] iArr2 = iArr;
                            iArr2[0] = i3;
                            iArr2[1] = i4;
                            iArr2[2] = i5;
                            iArr2[3] = i6;
                            StringBuilder sbA = a.a("get main rect ret =");
                            sbA.append(HisVideoApi.mReturnVal);
                            sbA.append(", x = ");
                            sbA.append(iArr[0]);
                            sbA.append(", y = ");
                            sbA.append(iArr[1]);
                            sbA.append(", width = ");
                            sbA.append(iArr[2]);
                            sbA.append(", height = ");
                            a.c(sbA, iArr[3], HisVideoApi.TAG);
                        }
                    });
                } else if (1 == i) {
                    mVideoServer.getItemData(18, new IHisVideoApi.getItemDataCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.209
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getItemDataCallback
                        public void onValues(int i2, ArrayList<Integer> arrayList) {
                            HisVideoApi.mReturnVal = i2;
                            iArr[0] = arrayList.get(0).intValue();
                            iArr[1] = arrayList.get(1).intValue();
                            iArr[2] = arrayList.get(2).intValue();
                            iArr[3] = arrayList.get(3).intValue();
                            StringBuilder sbA = a.a("get sub rect ret =");
                            sbA.append(HisVideoApi.mReturnVal);
                            sbA.append(", x = ");
                            sbA.append(iArr[0]);
                            sbA.append(", y = ");
                            sbA.append(iArr[1]);
                            sbA.append(", width = ");
                            sbA.append(iArr[2]);
                            sbA.append(", height = ");
                            a.c(sbA, iArr[3], HisVideoApi.TAG);
                        }
                    });
                } else {
                    HisLogger.e(TAG, "getScreenOutputDispRectEx not support vdpId: " + i);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return iArr;
    }

    public static ArrayList<Integer> getScreenSize() {
        HisLogger.d(TAG, "getScreenSize");
        mReturnVal = -1;
        mItemData = null;
        mItemDataIndex = 28;
        if (getHidlService()) {
            try {
                mVideoServer.getItemData(28, new IHisVideoApi.getItemDataCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.288
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getItemDataCallback
                    public void onValues(int i, ArrayList<Integer> arrayList) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mItemData = (ArrayList) arrayList.clone();
                        StringBuilder sbA = a.a("getScreenSize ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",vec = ");
                        sbA.append(HisVideoApi.mItemData);
                        HisLogger.d(HisVideoApi.TAG, sbA.toString());
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mItemData;
    }

    public static int getSharpness() {
        HisLogger.d(TAG, "getSharpness");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.isTVSModel(new IHisVideoApi.isTVSModelCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.44
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.isTVSModelCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mIsTvs = i2;
                        StringBuilder sbA = a.a("isTVSMode ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mIsTvs, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            try {
                mVideoServer.getSharpness(new IHisVideoApi.getSharpnessCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.45
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSharpnessCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        if (1 == HisVideoApi.mIsTvs) {
                            HisVideoApi.mGetParamVal = i2 - 50;
                        } else {
                            HisVideoApi.mGetParamVal = i2;
                        }
                        StringBuilder sbA = a.a("getSharpness ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getSharpness20() {
        HisLogger.d(TAG, "getSharpness20");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getSharpness20(new IHisVideoApi.getSharpness20Callback() { // from class: com.hisense.avmiddleware.HisVideoApi.118
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSharpness20Callback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getSharpness20 ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getSharpness80() {
        HisLogger.d(TAG, "getSharpness80");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getSharpness80(new IHisVideoApi.getSharpness80Callback() { // from class: com.hisense.avmiddleware.HisVideoApi.120
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSharpness80Callback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getSharpness80 ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getSharpnessByFactory() {
        HisLogger.d(TAG, "getSharpnessByFactory");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getSharpnessByFactory(new IHisVideoApi.getSharpnessByFactoryCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.48
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSharpnessByFactoryCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getSharpnessByFactory ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getSharpnessMax() {
        HisLogger.d(TAG, "getSharpnessMax");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getSharpnessMax(new IHisVideoApi.getSharpnessMaxCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.121
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSharpnessMaxCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getSharpnessMax ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getSharpnessMid() {
        HisLogger.d(TAG, "getSharpnessMid");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getSharpnessMid(new IHisVideoApi.getSharpnessMidCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.119
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSharpnessMidCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getSharpnessMid ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getSharpnessMin() {
        HisLogger.d(TAG, "getSharpnessMin");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getSharpnessMin(new IHisVideoApi.getSharpnessMinCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.117
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSharpnessMinCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getSharpnessMin ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getSharpnessOptionState() {
        HisLogger.d(TAG, "getSharpnessOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getSharpnessOptionState(new IHisVideoApi.getSharpnessOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.239
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSharpnessOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getSharpnessOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getSharpnessRangeMax() {
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.isTVSModel(new IHisVideoApi.isTVSModelCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.47
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.isTVSModelCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mIsTvs = i2;
                        StringBuilder sbA = a.a("isTVSMode ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mIsTvs, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (1 == mIsTvs) {
        }
        return 50;
    }

    public static int getSharpnessRangeMin() {
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.isTVSModel(new IHisVideoApi.isTVSModelCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.46
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.isTVSModelCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mIsTvs = i2;
                        StringBuilder sbA = a.a("isTVSModel ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mIsTvs, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return 1 == mIsTvs ? -50 : 0;
    }

    public static int getSignalCodecType() {
        HisLogger.d(TAG, "getSignalCodecType");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getSignalCodecType(new IHisVideoApi.getSignalCodecTypeCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.186
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSignalCodecTypeCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getSignalCodecType ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getSignalColorDepth() {
        HisLogger.d(TAG, "getSignalColorDepth");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getSignalColorDepth(new IHisVideoApi.getSignalColorDepthCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.185
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSignalColorDepthCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getSignalColorDepth ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getSignalColorFormat() {
        HisLogger.d(TAG, "getSignalColorFormat");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getSignalColorFormat(new IHisVideoApi.getSignalColorFormatCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.184
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSignalColorFormatCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getSignalColorFormat ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getSignalColorSpace() {
        HisLogger.d(TAG, "getSignalColorSpace");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getSignalColorSpace(new IHisVideoApi.getSignalColorSpaceCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.187
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSignalColorSpaceCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getSignalColorSpace ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getSignalColorSystem() {
        HisLogger.d(TAG, "getSignalColorSystem");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getSignalColorSystem(new IHisVideoApi.getSignalColorSystemCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.188
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSignalColorSystemCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getSignalColorSystem ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getSignalDRType() {
        HisLogger.d(TAG, "getSignalDRType");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getSignalDRType(new IHisVideoApi.getSignalDRTypeCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.181
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSignalDRTypeCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getSignalDRType ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getSignalFilmmakerType() {
        HisLogger.d(TAG, "getSignalFilmmakerType");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getSignalFilmmakerType(new IHisVideoApi.getSignalFilmmakerTypeCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.190
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSignalFilmmakerTypeCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getSignalFilmmakerType ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getSignalIsUHD() {
        HisLogger.d(TAG, "getSignalIsUHD");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getSignalIsUHD(new IHisVideoApi.getSignalIsUHDCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.189
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSignalIsUHDCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getSignalIsUHD ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int[] getSignalRatio() {
        HisLogger.d(TAG, "getSignalRatio");
        mReturnVal = -1;
        final int[] iArr = {0, 0};
        if (getHidlService()) {
            try {
                mVideoServer.getSignalRatio(new IHisVideoApi.getSignalRatioCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.182
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSignalRatioCallback
                    public void onValues(int i, int i2, int i3) {
                        HisVideoApi.mReturnVal = i;
                        int[] iArr2 = iArr;
                        iArr2[0] = i2;
                        iArr2[1] = i3;
                        StringBuilder sbA = a.a("getSignalRatio ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = { ");
                        sbA.append(iArr);
                        sbA.append("}");
                        HisLogger.d(HisVideoApi.TAG, sbA.toString());
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return iArr;
    }

    public static int getSignalType() {
        HisLogger.d(TAG, "getSignalType");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getSignalDRType(new IHisVideoApi.getSignalDRTypeCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.155
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSignalDRTypeCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getSignalType ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int[] getSignalVideoFormat() {
        HisLogger.d(TAG, "getSignalVideoFormat");
        mReturnVal = -1;
        final int[] iArr = {0, 0, 0, 0};
        if (getHidlService()) {
            try {
                mVideoServer.getSignalVideoFormat(new IHisVideoApi.getSignalVideoFormatCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.183
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSignalVideoFormatCallback
                    public void onValues(int i, int i2, int i3, int i4, int i5) {
                        HisVideoApi.mReturnVal = i;
                        int[] iArr2 = iArr;
                        iArr2[0] = i2;
                        iArr2[1] = i3;
                        iArr2[2] = i4;
                        iArr2[3] = i5;
                        StringBuilder sbA = a.a("getSignalVideoFormat ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = { ");
                        sbA.append(iArr);
                        sbA.append("}");
                        HisLogger.d(HisVideoApi.TAG, sbA.toString());
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return iArr;
    }

    public static int getTVSModel() {
        HisLogger.d(TAG, "getTVSModel");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getTVSModel(new IHisVideoApi.getTVSModelCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.254
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getTVSModelCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getTVSModel ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getTint() {
        HisLogger.d(TAG, "getTint");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getTint(new IHisVideoApi.getTintCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.49
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getTintCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getTint ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getTintRangeMax() {
        return 50;
    }

    public static int getTintRangeMin() {
        return -50;
    }

    public static int getUsageMode() {
        HisLogger.d(TAG, "getUsageMode");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getUsageMode(new IHisVideoApi.getUsageModeCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.243
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getUsageModeCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getUsageMode ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getVRRFrameRate() {
        HisLogger.d(TAG, "getVRRFrameRate");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getItemValue(12, new IHisVideoApi.getItemValueCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.233
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getItemValueCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getVRRFrameRate ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getVRRPlusOptionState() {
        HisLogger.d(TAG, "getVRRPlusOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getVRRPlusOptionState(new IHisVideoApi.getVRRPlusOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.268
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getVRRPlusOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getVRRPlusOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getVRRStatus() {
        HisLogger.d(TAG, "getVRRStatus");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getItemValue(19, new IHisVideoApi.getItemValueCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.234
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getItemValueCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getVRRFrameRate ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getVcomTestEnable() {
        HisLogger.d(TAG, "getVcomTestEnable");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getVcomTestEnable(new IHisVideoApi.getVcomTestEnableCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.144
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getVcomTestEnableCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getVcomTestEnable ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getWhiteBalanceGainBlue() {
        HisLogger.d(TAG, "getWhiteBalanceGainBlue");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getWhiteBalanceGainBlue(new IHisVideoApi.getWhiteBalanceGainBlueCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.76
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getWhiteBalanceGainBlueCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getWhiteBalanceGainBlue ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getWhiteBalanceGainBlueRangeMax() {
        return 25;
    }

    public static int getWhiteBalanceGainBlueRangeMin() {
        return -25;
    }

    public static int getWhiteBalanceGainGreen() {
        HisLogger.d(TAG, "getWhiteBalanceGainGreen");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getWhiteBalanceGainGreen(new IHisVideoApi.getWhiteBalanceGainGreenCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.75
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getWhiteBalanceGainGreenCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getWhiteBalanceGainGreen ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getWhiteBalanceGainGreenRangeMax() {
        return 25;
    }

    public static int getWhiteBalanceGainGreenRangeMin() {
        return -25;
    }

    public static int getWhiteBalanceGainRed() {
        HisLogger.d(TAG, "getWhiteBalanceGainRed");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getWhiteBalanceGainRed(new IHisVideoApi.getWhiteBalanceGainRedCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.74
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getWhiteBalanceGainRedCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getWhiteBalanceGainRed ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getWhiteBalanceGainRedRangeMax() {
        return 25;
    }

    public static int getWhiteBalanceGainRedRangeMin() {
        return -25;
    }

    public static int getWhiteBalanceOffsetBlue() {
        HisLogger.d(TAG, "getWhiteBalanceOffsetBlue");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getWhiteBalanceOffsetBlue(new IHisVideoApi.getWhiteBalanceOffsetBlueCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.73
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getWhiteBalanceOffsetBlueCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getWhiteBalanceOffsetBlue ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getWhiteBalanceOffsetBlueRangeMax() {
        return 25;
    }

    public static int getWhiteBalanceOffsetBlueRangeMin() {
        return -25;
    }

    public static int getWhiteBalanceOffsetGreen() {
        HisLogger.d(TAG, "getWhiteBalanceOffsetGreen");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getWhiteBalanceOffsetGreen(new IHisVideoApi.getWhiteBalanceOffsetGreenCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.72
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getWhiteBalanceOffsetGreenCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getWhiteBalanceOffsetGreen ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getWhiteBalanceOffsetGreenRangeMax() {
        return 25;
    }

    public static int getWhiteBalanceOffsetGreenRangeMin() {
        return -25;
    }

    public static int getWhiteBalanceOffsetRed() {
        HisLogger.d(TAG, "getWhiteBalanceOffsetRed");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getWhiteBalanceOffsetRed(new IHisVideoApi.getWhiteBalanceOffsetRedCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.71
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getWhiteBalanceOffsetRedCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getWhiteBalanceOffsetRed ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getWhiteBalanceOffsetRedRangeMax() {
        return 25;
    }

    public static int getWhiteBalanceOffsetRedRangeMin() {
        return -25;
    }

    public static int getWhiteBalanceOptionState() {
        HisLogger.d(TAG, "getWhiteBalanceOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getWhiteBalanceOptionState(new IHisVideoApi.getWhiteBalanceOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.240
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getWhiteBalanceOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getWhiteBalanceOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getWhiteBalanceiApplyToAll() {
        HisLogger.d(TAG, "getWhiteBlanceiApplyToAll");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getWhiteBalanceiApplyToAll(new IHisVideoApi.getWhiteBalanceiApplyToAllCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.77
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getWhiteBalanceiApplyToAllCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getWhiteBalanceiApplyToAll ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getWhiteLevel() {
        HisLogger.d(TAG, "getWhiteLevel");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getWhiteLevel(new IHisVideoApi.getWhiteLevelCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.257
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getWhiteLevelCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getWhiteLevel ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getWhiteLevelMax() {
        HisLogger.d(TAG, "getWhiteLevelMax");
        return 10;
    }

    public static int getWhiteLevelMin() {
        HisLogger.d(TAG, "getWhiteLevelMin");
        return -10;
    }

    public static int getWhiteLevelOptionState() {
        HisLogger.d(TAG, "getBrightnessOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getWhiteLevelOptionState(new IHisVideoApi.getWhiteLevelOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.259
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getWhiteLevelOptionStateCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getWhiteLevelOptionState ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static ArrayList<ArrayList> getWindowHSLHist() {
        HisLogger.d(TAG, "getWindowHSLHist");
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getWindowHSLHist(new IHisVideoApi.getWindowHSLHistCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.216
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getWindowHSLHistCallback
                    public void onValues(int i, ArrayList<Integer> arrayList, ArrayList<Integer> arrayList2, ArrayList<Integer> arrayList3, ArrayList<Integer> arrayList4) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.Hue = (ArrayList) arrayList.clone();
                        HisVideoApi.Sat = (ArrayList) arrayList2.clone();
                        HisVideoApi.Lum = (ArrayList) arrayList3.clone();
                        HisVideoApi.Rgb = (ArrayList) arrayList4.clone();
                        HisVideoApi.hslList.add(HisVideoApi.Hue);
                        HisVideoApi.hslList.add(HisVideoApi.Sat);
                        HisVideoApi.hslList.add(HisVideoApi.Lum);
                        HisVideoApi.hslList.add(HisVideoApi.Rgb);
                        StringBuilder sb = new StringBuilder();
                        sb.append("getWindowHSLHist ret =");
                        a.c(sb, HisVideoApi.mReturnVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return hslList;
    }

    public static int isCalManCalibrated() {
        HisLogger.d(TAG, "isCalManCalibrated");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getItemValue(71, new IHisVideoApi.getItemValueCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.81
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getItemValueCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("isCalibrated ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int isDolby() {
        HisLogger.d(TAG, "isDolby");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.isDolby(new IHisVideoApi.isDolbyCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.173
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.isDolbyCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("isDolby ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int isDolbyBrightness() {
        HisLogger.d(TAG, "isDolbyBrightness");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.isDolbyBrightness(new IHisVideoApi.isDolbyBrightnessCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.175
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.isDolbyBrightnessCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("isDolbyBrightness ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int isDolbyCustom() {
        HisLogger.d(TAG, "isDolbyCustom");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.isDolbyCustom(new IHisVideoApi.isDolbyCustomCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.176
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.isDolbyCustomCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("isDolbyCustom ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int isDolbyDark() {
        HisLogger.d(TAG, "isDolbyDark");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.isDolbyDark(new IHisVideoApi.isDolbyDarkCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.174
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.isDolbyDarkCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("isDolbyDark ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int isDoubleDispalyMemc() {
        HisLogger.d(TAG, "isDoubleDispalyMemc");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.isDoubleDispalyMemc(new IHisVideoApi.isDoubleDispalyMemcCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.64
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.isDoubleDispalyMemcCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("isDoubleDispalyMemc ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int isGame() {
        HisLogger.d(TAG, "isGame");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.isGame(new IHisVideoApi.isGameCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.180
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.isGameCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("isGame ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int isHDR() {
        HisLogger.d(TAG, "isHDR");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.isHDR(new IHisVideoApi.isHDRCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.177
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.isHDRCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("isHDR ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int isHDR10Plus() {
        HisLogger.d(TAG, "isHDR10Plus");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.isHDR10Plus(new IHisVideoApi.isHDR10PlusCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.178
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.isHDR10PlusCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("isHDR10Plus ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int isOLEDModel() {
        HisLogger.d(TAG, "isOLEDModel");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.isOLEDModel(new IHisVideoApi.isOLEDModelCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.27
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.isOLEDModelCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("isOLEDModel ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int isSignalPlaying() {
        HisLogger.d(TAG, "isSignalPlaying");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.isSignalPlaying(new IHisVideoApi.isSignalPlayingCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.179
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.isSignalPlayingCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("isSignalPlaying ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int isTVSModel() {
        HisLogger.d(TAG, "isTVSModel");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.isTVSModel(new IHisVideoApi.isTVSModelCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.26
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.isTVSModelCallback
                    public void onValues(int i, int i2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("isTVSModel ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mGetParamVal, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static String processCalManULMPI(String str) {
        HisLogger.d(TAG, "processCalManULMPI, cmd = '" + str + "', start.");
        mGetParamStr = "NAK";
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.CalMAN_ULMPI(str, new IHisVideoApi.CalMAN_ULMPICallback() { // from class: com.hisense.avmiddleware.HisVideoApi.265
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.CalMAN_ULMPICallback
                    public void onValues(int i, String str2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mGetParamStr = str2;
                        StringBuilder sbA = a.a("processCalManULMPI ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",string = '");
                        sbA.append(HisVideoApi.mGetParamStr);
                        sbA.append("', done.");
                        HisLogger.d(HisVideoApi.TAG, sbA.toString());
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamStr;
    }

    public static ArrayList<Byte> processCalManULMPIByte(ArrayList<Byte> arrayList) {
        StringBuilder sbA = a.a("processCalManULMPIByte, cmd.size() = '");
        sbA.append(arrayList.size());
        sbA.append("', start.");
        HisLogger.d(TAG, sbA.toString());
        if (getHidlService()) {
            try {
                mVideoServer.CalMAN_ULMPI_BYTES(arrayList, new IHisVideoApi.CalMAN_ULMPI_BYTESCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.266
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.CalMAN_ULMPI_BYTESCallback
                    public void onValues(int i, ArrayList<Byte> arrayList2) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.mBytes = (ArrayList) arrayList2.clone();
                        StringBuilder sbA2 = a.a("processCalManULMPIByte ret =");
                        sbA2.append(HisVideoApi.mReturnVal);
                        sbA2.append(", ack.size() = '");
                        sbA2.append(HisVideoApi.mBytes.size());
                        sbA2.append("', done.");
                        HisLogger.d(HisVideoApi.TAG, sbA2.toString());
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mBytes;
    }

    public static int reloadISFData(int i) {
        int iReloadISFData = -1;
        if (a.f("reloadISFData val:", i, TAG)) {
            try {
                iReloadISFData = mVideoServer.ReloadISFData(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("reloadISFData ret = ", iReloadISFData, TAG);
        }
        return iReloadISFData;
    }

    public static int resetAllFac2PWhiteBalance() {
        HisLogger.d(TAG, "resetAllFac2PWhiteBalance");
        int iResetAllFac2PWhiteBalance = -1;
        if (getHidlService()) {
            try {
                iResetAllFac2PWhiteBalance = mVideoServer.resetAllFac2PWhiteBalance(1);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("resetAllFac2PWhiteBalance ret = ", iResetAllFac2PWhiteBalance, TAG);
        }
        return iResetAllFac2PWhiteBalance;
    }

    public static int resetColorTuner() {
        HisLogger.d(TAG, "resetColorTuner");
        int iResetColorTuner = -1;
        if (getHidlService()) {
            try {
                iResetColorTuner = mVideoServer.ResetColorTuner(1);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("resetColorTuner ret = ", iResetColorTuner, TAG);
        }
        return iResetColorTuner;
    }

    public static int resetGammaCalibration() {
        HisLogger.d(TAG, "resetGammaCalibration");
        int iResetGammaCalibration = -1;
        if (getHidlService()) {
            try {
                iResetGammaCalibration = mVideoServer.ResetGammaCalibration(1);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("resetGammaCalibration ret = ", iResetGammaCalibration, TAG);
        }
        return iResetGammaCalibration;
    }

    public static int resetMultiPointWhiteBalance() {
        HisLogger.d(TAG, "resetMultiPointWhiteBalance");
        int iResetMultiPointWhiteBalance = -1;
        if (getHidlService()) {
            try {
                iResetMultiPointWhiteBalance = mVideoServer.ResetMultiPointWhiteBalance(1);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("resetMultiPointWhiteBalance ret = ", iResetMultiPointWhiteBalance, TAG);
        }
        return iResetMultiPointWhiteBalance;
    }

    public static int resetPictureSettings() {
        HisLogger.d(TAG, "resetPictureSettings");
        int iResetPictureSettings = -1;
        if (getHidlService()) {
            try {
                iResetPictureSettings = mVideoServer.resetPictureSettings(1);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("resetPictureSettings ret = ", iResetPictureSettings, TAG);
        }
        return iResetPictureSettings;
    }

    public static int resetStoreExpertData(int i) {
        HisLogger.d(TAG, "resetStoreExpertData");
        int iResetStoreExpertData = -1;
        if (getHidlService()) {
            try {
                iResetStoreExpertData = mVideoServer.resetStoreExpertData(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("resetStoreExpertData ret = ", iResetStoreExpertData, TAG);
        }
        return iResetStoreExpertData;
    }

    public static int resetWhiteBalance() {
        HisLogger.d(TAG, "resetWhiteBalance");
        int iResetWhiteBalance = -1;
        if (getHidlService()) {
            try {
                iResetWhiteBalance = mVideoServer.ResetWhiteBalance(1);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("resetWhiteBlance ret = ", iResetWhiteBalance, TAG);
        }
        return iResetWhiteBalance;
    }

    public static int restorePQSetting() {
        HisLogger.d(TAG, "restorePQSetting");
        int iRestorePQSetting = -1;
        if (getHidlService()) {
            try {
                iRestorePQSetting = mVideoServer.restorePQSetting();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("restorePQSetting ret = ", iRestorePQSetting, TAG);
        }
        return iRestorePQSetting;
    }

    public static int saveStoreExpertData(int i) {
        HisLogger.d(TAG, "backupPQSetting");
        int iSaveStoreExpertData = -1;
        if (getHidlService()) {
            try {
                iSaveStoreExpertData = mVideoServer.saveStoreExpertData(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("saveStoreExpertData ret = ", iSaveStoreExpertData, TAG);
        }
        return iSaveStoreExpertData;
    }

    public static int set2PWBGainBlueDelta(int i) {
        int i2 = -1;
        if (a.f("set2PWBGainBlueDelta val:", i, TAG)) {
            try {
                i2 = mVideoServer.set2PWBGainBlueDelta(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("set2PWBGainBlueDelta ret = ", i2, TAG);
        }
        return i2;
    }

    public static int set2PWBGainGreenDelta(int i) {
        int i2 = -1;
        if (a.f("set2PWBGainGreenDelta val:", i, TAG)) {
            try {
                i2 = mVideoServer.set2PWBGainGreenDelta(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("set2PWBGainGreenDelta ret = ", i2, TAG);
        }
        return i2;
    }

    public static int set2PWBGainRedDelta(int i) {
        int i2 = -1;
        if (a.f("set2PWBGainRedDelta val:", i, TAG)) {
            try {
                i2 = mVideoServer.set2PWBGainRedDelta(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("set2PWBGainRedDelta ret = ", i2, TAG);
        }
        return i2;
    }

    public static int set2PWBOffsetBlueDelta(int i) {
        int i2 = -1;
        if (a.f("set2PWBOffsetBlueDelta val:", i, TAG)) {
            try {
                i2 = mVideoServer.set2PWBOffsetBlueDelta(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("set2PWBOffsetBlueDelta ret = ", i2, TAG);
        }
        return i2;
    }

    public static int set2PWBOffsetGreenDelta(int i) {
        int i2 = -1;
        if (a.f("set2PWBOffsetGreenDelta val:", i, TAG)) {
            try {
                i2 = mVideoServer.set2PWBOffsetGreenDelta(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("set2PWBOffsetGreenDelta ret = ", i2, TAG);
        }
        return i2;
    }

    public static int set2PWBOffsetRedDelta(int i) {
        int i2 = -1;
        if (a.f("set2PWBOffsetRedDelta val:", i, TAG)) {
            try {
                i2 = mVideoServer.set2PWBOffsetRedDelta(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("set2PWBOffsetRedDelta ret = ", i2, TAG);
        }
        return i2;
    }

    public static int set4K120HzDirectPathEnable(int i) {
        int itemValue = -1;
        if (a.f("set4K120HzDirectPathEnable val:", i, TAG)) {
            try {
                itemValue = mVideoServer.setItemValue(44, i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("set4K120HzDirectPathEnable ret = ", itemValue, TAG);
        }
        return itemValue;
    }

    public static int setAIPQDetectSwitch(int i) {
        int aIPQDetectSwitch = -1;
        if (a.f("setAIPQDetectSwitch val:", i, TAG)) {
            try {
                aIPQDetectSwitch = mVideoServer.setAIPQDetectSwitch(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setAIPQDetectSwitch ret = ", aIPQDetectSwitch, TAG);
        }
        return aIPQDetectSwitch;
    }

    public static int setAPOContentCurve(int i, int i2, int i3) {
        StringBuilder sbB = a.b("setAPOContentCurve mode: ", i, "index: ", i2, "value: ");
        sbB.append(i3);
        HisLogger.d(TAG, sbB.toString());
        int aPOContentCurve = -1;
        if (getHidlService()) {
            try {
                aPOContentCurve = mVideoServer.setAPOContentCurve(i, i2, i3);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setAPOContentCurve ret = ", aPOContentCurve, TAG);
        }
        return aPOContentCurve;
    }

    public static int setAPOWBCurve(int i, int i2, int i3) {
        StringBuilder sbB = a.b("setAPOWBCurve mode: ", i, "index: ", i2, "value: ");
        sbB.append(i3);
        HisLogger.d(TAG, sbB.toString());
        int aPOWBCurve = -1;
        if (getHidlService()) {
            try {
                aPOWBCurve = mVideoServer.setAPOWBCurve(i, i2, i3);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setAPOWBCurve ret = ", aPOWBCurve, TAG);
        }
        return aPOWBCurve;
    }

    public static int setActiveContrast(int i) {
        int activeContrast = -1;
        if (a.f("setActiveContrast val:", i, TAG)) {
            try {
                activeContrast = mVideoServer.setActiveContrast(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setActiveContrast ret = ", activeContrast, TAG);
        }
        return activeContrast;
    }

    public static int setAgingModeEnable(int i) {
        int agingModeEnable = -1;
        if (a.f("setAgingModeEnable val:", i, TAG)) {
            try {
                agingModeEnable = mVideoServer.setAgingModeEnable(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setAgingModeEnable ret = ", agingModeEnable, TAG);
        }
        return agingModeEnable;
    }

    public static int setAirPlayEnable(int i) {
        int itemValue = -1;
        if (a.f("setAirPlayEnable val:", i, TAG)) {
            try {
                itemValue = mVideoServer.setItemValue(72, i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setAirPlayEnable ret = ", itemValue, TAG);
        }
        return itemValue;
    }

    public static int setAnimationFlag(int i) {
        int bootLogoState = -1;
        if (a.f("setAnimationFlag val:", i, TAG)) {
            try {
                bootLogoState = mVideoServer.setBootLogoState(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setAnimationFlag ret = ", bootLogoState, TAG);
        }
        return bootLogoState;
    }

    public static int setAspectRatio(int i) {
        int aspectRatio = -1;
        if (a.f("setAspectRatio val:", i, TAG)) {
            try {
                aspectRatio = mVideoServer.setAspectRatio(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setAspectRatio ret = ", aspectRatio, TAG);
        }
        return aspectRatio;
    }

    public static int setAudioOnlyEnable(int i) {
        int audioOnlyEnable = -1;
        if (a.f("setAudioOnlyEnable val:", i, TAG)) {
            try {
                audioOnlyEnable = mVideoServer.setAudioOnlyEnable(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setAudioOnlyEnable ret = ", audioOnlyEnable, TAG);
        }
        return audioOnlyEnable;
    }

    public static int setAutoFilmmakerModeStatus(int i) {
        int autoFilmmakerModeStatus = -1;
        if (a.f("setAutoFilmmakerModeStatus val:", i, TAG)) {
            try {
                autoFilmmakerModeStatus = mVideoServer.setAutoFilmmakerModeStatus(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setAutoFilmmakerModeStatus ret = ", autoFilmmakerModeStatus, TAG);
        }
        return autoFilmmakerModeStatus;
    }

    public static int setAutoGammaEnable(int i) {
        int iSetAutoGammaEnable = -1;
        if (a.f("setAutoGammaEnable val:", i, TAG)) {
            try {
                iSetAutoGammaEnable = mVideoServer.SetAutoGammaEnable(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setAutoGammaEnable ret = ", iSetAutoGammaEnable, TAG);
        }
        return iSetAutoGammaEnable;
    }

    public static int setAutoGammaTuning(int i) {
        int itemValue = -1;
        if (a.f("setAutoGammaTuning val:", i, TAG)) {
            try {
                itemValue = mVideoServer.setItemValue(0, i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setAutoGammaTuning ret = ", itemValue, TAG);
        }
        return itemValue;
    }

    public static int setAutoScanMode(int i) {
        int autoScanMode = -1;
        if (a.f("setAutoScanMode val: ", i, TAG)) {
            try {
                autoScanMode = mVideoServer.setAutoScanMode(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setAutoScanMode, ret = ", autoScanMode, TAG);
        }
        return autoScanMode;
    }

    public static int setBacklight20(int i) {
        int backlight20 = -1;
        if (a.f("setBacklight20 val:", i, TAG)) {
            try {
                backlight20 = mVideoServer.setBacklight20(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setBacklight20 ret = ", backlight20, TAG);
        }
        return backlight20;
    }

    public static int setBacklight80(int i) {
        int backlight80 = -1;
        if (a.f("setBacklight80 val:", i, TAG)) {
            try {
                backlight80 = mVideoServer.setBacklight80(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setBacklight80 ret = ", backlight80, TAG);
        }
        return backlight80;
    }

    public static int setBacklightMax(int i) {
        int backlightMax = -1;
        if (a.f("setBacklightMax val:", i, TAG)) {
            try {
                backlightMax = mVideoServer.setBacklightMax(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setBacklightMax ret = ", backlightMax, TAG);
        }
        return backlightMax;
    }

    public static int setBacklightMid(int i) {
        int backlightMid = -1;
        if (a.f("setBacklightMid val:", i, TAG)) {
            try {
                backlightMid = mVideoServer.setBacklightMid(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setBacklightMid ret = ", backlightMid, TAG);
        }
        return backlightMid;
    }

    public static int setBacklightMin(int i) {
        int backlightMin = -1;
        if (a.f("setBacklightMin val:", i, TAG)) {
            try {
                backlightMin = mVideoServer.setBacklightMin(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setBacklightMin ret = ", backlightMin, TAG);
        }
        return backlightMin;
    }

    public static int setBacklightPWMFreq(int i) {
        int backlightPWMFreq = -1;
        if (a.f("setBacklightPWMFreq val:", i, TAG)) {
            try {
                backlightPWMFreq = mVideoServer.setBacklightPWMFreq(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setBacklightPWMFreq ret = ", backlightPWMFreq, TAG);
        }
        return backlightPWMFreq;
    }

    public static int setBacklightPercent(int i) {
        int backlightPercent = -1;
        if (a.f("setBacklightPercent val:", i, TAG)) {
            try {
                backlightPercent = mVideoServer.setBacklightPercent(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setBacklightPercent ret = ", backlightPercent, TAG);
        }
        return backlightPercent;
    }

    public static int setBacklightPercentByFactory(int i) {
        int backlightPercentByFactory = -1;
        if (a.f("setBacklightPercentByFactory val:", i, TAG)) {
            try {
                backlightPercentByFactory = mVideoServer.setBacklightPercentByFactory(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setBacklightPercentByFactory ret = ", backlightPercentByFactory, TAG);
        }
        return backlightPercentByFactory;
    }

    public static int setBlackLevel(int i) {
        int blackLevel = -1;
        if (a.f("setBlackLevel val:", i, TAG)) {
            try {
                blackLevel = mVideoServer.setBlackLevel(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setBlackLevel ret = ", blackLevel, TAG);
        }
        return blackLevel;
    }

    public static int setBlueMuteEnable(int i) {
        a.c("setBlueMuteEnable val: ", i, TAG);
        int i2 = 1 == i ? 255 : 0;
        int itemValue = -1;
        if (getHidlService()) {
            try {
                itemValue = mVideoServer.setItemValue(26, i2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            StringBuilder sbA = a.a("setBlueMuteEnable, mute color: ");
            sbA.append(Integer.toHexString(i2));
            sbA.append(", ret = ");
            sbA.append(itemValue);
            HisLogger.d(TAG, sbA.toString());
        }
        return itemValue;
    }

    public static int setBrightness(int i) {
        if (getHidlService()) {
            try {
                mVideoServer.isTVSModel(new IHisVideoApi.isTVSModelCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.31
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.isTVSModelCallback
                    public void onValues(int i2, int i3) {
                        HisVideoApi.mReturnVal = i2;
                        HisVideoApi.mIsTvs = i3;
                        StringBuilder sbA = a.a("isTVSModel ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mIsTvs, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (1 == mIsTvs) {
            i += 50;
        }
        int brightness = -1;
        if (a.f("setBrightness val:", i, TAG)) {
            try {
                brightness = mVideoServer.setBrightness(i);
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            a.c("setBrightness ret = ", brightness, TAG);
        }
        return brightness;
    }

    public static int setBrightness20(int i) {
        int brightness20 = -1;
        if (a.f("setBrightness20 val:", i, TAG)) {
            try {
                brightness20 = mVideoServer.setBrightness20(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setBrightness20 ret = ", brightness20, TAG);
        }
        return brightness20;
    }

    public static int setBrightness80(int i) {
        int brightness80 = -1;
        if (a.f("setBrightness80 val:", i, TAG)) {
            try {
                brightness80 = mVideoServer.setBrightness80(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setBrightness80 ret = ", brightness80, TAG);
        }
        return brightness80;
    }

    public static int setBrightnessByFactory(int i) {
        int brightnessByFactory = -1;
        if (a.f("setBrightnessByFactory val:", i, TAG)) {
            try {
                brightnessByFactory = mVideoServer.setBrightnessByFactory(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setBrightnessByFactory ret = ", brightnessByFactory, TAG);
        }
        return brightnessByFactory;
    }

    public static int setBrightnessMax(int i) {
        int brightnessMax = -1;
        if (a.f("setBrightnessMax val:", i, TAG)) {
            try {
                brightnessMax = mVideoServer.setBrightnessMax(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setBrightnessMax ret = ", brightnessMax, TAG);
        }
        return brightnessMax;
    }

    public static int setBrightnessMid(int i) {
        int brightnessMid = -1;
        if (a.f("setBrightnessMid val:", i, TAG)) {
            try {
                brightnessMid = mVideoServer.setBrightnessMid(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setBrightnessMid ret = ", brightnessMid, TAG);
        }
        return brightnessMid;
    }

    public static int setBrightnessMin(int i) {
        int brightnessMin = -1;
        if (a.f("setBrightnessMin val:", i, TAG)) {
            try {
                brightnessMin = mVideoServer.setBrightnessMin(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setBrightnessMin ret = ", brightnessMin, TAG);
        }
        return brightnessMin;
    }

    public static int setBypassGammaEnable(int i) {
        int gammaEnable = -1;
        if (a.f("setBypassGammaEnable val:", i, TAG)) {
            try {
                gammaEnable = mVideoServer.setGammaEnable(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setBypassGammaEnable ret = ", gammaEnable, TAG);
        }
        return gammaEnable;
    }

    public static int setBypassPqEnable(int i) {
        int pqEnable = -1;
        if (a.f("setBypassPqEnable val:", i, TAG)) {
            try {
                pqEnable = mVideoServer.setPqEnable(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setBypassPqEnable ret = ", pqEnable, TAG);
        }
        return pqEnable;
    }

    public static int setBypassSceEnable(int i) {
        int sceEnable = -1;
        if (a.f("setBypassSceEnable val:", i, TAG)) {
            try {
                sceEnable = mVideoServer.setSceEnable(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setBypassSceEnable ret = ", sceEnable, TAG);
        }
        return sceEnable;
    }

    public static int setBypassXvyccEnable(int i) {
        int xvyccEnable = -1;
        if (a.f("setBypassXvyccEnable val:", i, TAG)) {
            try {
                xvyccEnable = mVideoServer.setXvyccEnable(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setBypassXvyccEnable ret = ", xvyccEnable, TAG);
        }
        return xvyccEnable;
    }

    public static int setCalManEnable(int i) {
        int calMANEnable = -1;
        if (a.f("setCalManEnable val:", i, TAG)) {
            try {
                calMANEnable = mVideoServer.setCalMANEnable(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setCalManEnable ret = ", calMANEnable, TAG);
        }
        return calMANEnable;
    }

    public static int setCalManTuning(int i) {
        int calMANTuningEnable = -1;
        if (a.f("setCalManTuning val:", i, TAG)) {
            try {
                calMANTuningEnable = mVideoServer.setCalMANTuningEnable(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setCalManTuning ret = ", calMANTuningEnable, TAG);
        }
        return calMANTuningEnable;
    }

    public static int setColorSpace(int i) {
        int colorSpace = -1;
        if (a.f("setColorSpace val:", i, TAG)) {
            try {
                colorSpace = mVideoServer.setColorSpace(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setColorSpace ret = ", colorSpace, TAG);
        }
        return colorSpace;
    }

    public static int setColorTemperature(int i) {
        int colorTemperature = -1;
        if (a.f("setColorTemperature val:", i, TAG)) {
            try {
                colorTemperature = mVideoServer.setColorTemperature(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setColorTemperature ret = ", colorTemperature, TAG);
        }
        return colorTemperature;
    }

    public static int setColorTunerApplyToAll(int i) {
        int colorTunerApplyToAll = -1;
        if (a.f("setColorTunerApplyToAll val:", i, TAG)) {
            try {
                colorTunerApplyToAll = mVideoServer.setColorTunerApplyToAll(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setColorTunerApplyToAll ret = ", colorTunerApplyToAll, TAG);
        }
        return colorTunerApplyToAll;
    }

    public static int setColorTunerBrightness(int i) {
        int colorTunerBrightness = -1;
        if (a.f("setColorTunerBrightness val:", i, TAG)) {
            try {
                colorTunerBrightness = mVideoServer.setColorTunerBrightness(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setColorTunerBrightness ret = ", colorTunerBrightness, TAG);
        }
        return colorTunerBrightness;
    }

    public static int setColorTunerColor(int i) {
        int colorTunerColor = -1;
        if (a.f("setColorTunerColor val:", i, TAG)) {
            try {
                colorTunerColor = mVideoServer.setColorTunerColor(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setColorTunerColor ret = ", colorTunerColor, TAG);
        }
        return colorTunerColor;
    }

    public static int setColorTunerHue(int i) {
        int colorTunerHue = -1;
        if (a.f("setColorTunerHue val:", i, TAG)) {
            try {
                colorTunerHue = mVideoServer.setColorTunerHue(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setColorTunerHue ret = ", colorTunerHue, TAG);
        }
        return colorTunerHue;
    }

    public static int setColorTunerSaturation(int i) {
        int colorTunerSaturation = -1;
        if (a.f("setColorTunerSaturation val:", i, TAG)) {
            try {
                colorTunerSaturation = mVideoServer.setColorTunerSaturation(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setColorTunerSaturation ret = ", colorTunerSaturation, TAG);
        }
        return colorTunerSaturation;
    }

    public static int setContrast(int i) {
        if (getHidlService()) {
            try {
                mVideoServer.isTVSModel(new IHisVideoApi.isTVSModelCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.23
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.isTVSModelCallback
                    public void onValues(int i2, int i3) {
                        HisVideoApi.mReturnVal = i2;
                        HisVideoApi.mIsTvs = i3;
                        StringBuilder sbA = a.a("isTVSModel ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mIsTvs, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (1 == mIsTvs) {
            i += 50;
        }
        int contrast = -1;
        if (a.f("setContrast val:", i, TAG)) {
            try {
                contrast = mVideoServer.setContrast(i);
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            a.c("setContrast ret = ", contrast, TAG);
        }
        return contrast;
    }

    public static int setContrast20(int i) {
        int contrast20 = -1;
        if (a.f("setContrast20 val:", i, TAG)) {
            try {
                contrast20 = mVideoServer.setContrast20(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setContrast20 ret = ", contrast20, TAG);
        }
        return contrast20;
    }

    public static int setContrast80(int i) {
        int contrast80 = -1;
        if (a.f("setContrast80 val:", i, TAG)) {
            try {
                contrast80 = mVideoServer.setContrast80(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setContrast80 ret = ", contrast80, TAG);
        }
        return contrast80;
    }

    public static int setContrastByFactory(int i) {
        int contrastByFactory = -1;
        if (a.f("setContrastByFactory val:", i, TAG)) {
            try {
                contrastByFactory = mVideoServer.setContrastByFactory(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setContrastByFactory ret = ", contrastByFactory, TAG);
        }
        return contrastByFactory;
    }

    public static int setContrastMax(int i) {
        int contrastMax = -1;
        if (a.f("setContrastMax val:", i, TAG)) {
            try {
                contrastMax = mVideoServer.setContrastMax(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setContrastMax ret = ", contrastMax, TAG);
        }
        return contrastMax;
    }

    public static int setContrastMid(int i) {
        int contrastMid = -1;
        if (a.f("setContrastMid val:", i, TAG)) {
            try {
                contrastMid = mVideoServer.setContrastMid(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setContrastMid ret = ", contrastMid, TAG);
        }
        return contrastMid;
    }

    public static int setContrastMin(int i) {
        int contrastMin = -1;
        if (a.f("setContrastMin val:", i, TAG)) {
            try {
                contrastMin = mVideoServer.setContrastMin(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setContrastMin ret = ", contrastMin, TAG);
        }
        return contrastMin;
    }

    public static int setDMPPlayPhotoMode(int i) {
        int dMPPlayPhotoMode = -1;
        if (a.f("setDMPPlayPhotoMode val:", i, TAG)) {
            try {
                dMPPlayPhotoMode = mVideoServer.setDMPPlayPhotoMode(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setDMPPlayPhotoMode ret = ", dMPPlayPhotoMode, TAG);
        }
        return dMPPlayPhotoMode;
    }

    public static int setDolbyIQAPOEnable(int i) {
        int aPOEnable = -1;
        if (a.f("setDolbyIQAPOEnable val:", i, TAG)) {
            try {
                aPOEnable = mVideoServer.setAPOEnable(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setDolbyIQAPOEnable ret = ", aPOEnable, TAG);
        }
        return aPOEnable;
    }

    public static int setDolbyIQUserBinEnable(int i) {
        int dolbyIQUserBinEnable = -1;
        if (a.f("setDolbyIQUserBinEnable val:", i, TAG)) {
            try {
                dolbyIQUserBinEnable = mVideoServer.setDolbyIQUserBinEnable(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setDolbyIQUserBinEnable ret = ", dolbyIQUserBinEnable, TAG);
        }
        return dolbyIQUserBinEnable;
    }

    public static int setDolbyIQUserBinUpdate(String str) {
        HisLogger.d(TAG, "setDolbyIQUserBinUpdate, path: '" + str + "'");
        int itemString = -1;
        if (getHidlService()) {
            try {
                itemString = mVideoServer.setItemString(4, str);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setDolbyIQUserBinUpdate ret = ", itemString, TAG);
        }
        return itemString;
    }

    public static int setDynamicBacklight(int i) {
        int dynamicBacklight = -1;
        if (a.f("setDynamicBacklight val:", i, TAG)) {
            try {
                dynamicBacklight = mVideoServer.setDynamicBacklight(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setDynamicBacklight ret = ", dynamicBacklight, TAG);
        }
        return dynamicBacklight;
    }

    public static int setDynamicContrast(int i) {
        int dynamicContrast = -1;
        if (a.f("setDynamicContrast val:", i, TAG)) {
            try {
                dynamicContrast = mVideoServer.setDynamicContrast(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setDynamicContrast ret = ", dynamicContrast, TAG);
        }
        return dynamicContrast;
    }

    public static int setDynamicToneMappingEnable(int i) {
        int dynamicToneMappingEnable = -1;
        if (a.f("setDynamicToneMappingEnable val:", i, TAG)) {
            try {
                dynamicToneMappingEnable = mVideoServer.setDynamicToneMappingEnable(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setDynamicToneMappingEnable ret = ", dynamicToneMappingEnable, TAG);
        }
        return dynamicToneMappingEnable;
    }

    public static int setEPOSFlag(int i) {
        int ePOSEnable = -1;
        if (a.f("setEPOSFlag val:", i, TAG)) {
            try {
                ePOSEnable = mVideoServer.setEPOSEnable(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setEPOSFlag ret = ", ePOSEnable, TAG);
        }
        return ePOSEnable;
    }

    public static int setEnergySavingEnable(int i) {
        int energySavingEnable = -1;
        if (a.f("setEnergySavingEnable val:", i, TAG)) {
            try {
                energySavingEnable = mVideoServer.setEnergySavingEnable(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setEnergySavingEnable ret = ", energySavingEnable, TAG);
        }
        return energySavingEnable;
    }

    public static int setEnhancedViewingAngleEnable(int i) {
        int enhancedViewingAngleEnable = -1;
        if (a.f("setEnhancedViewingAngleEnable val:", i, TAG)) {
            try {
                enhancedViewingAngleEnable = mVideoServer.setEnhancedViewingAngleEnable(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setEnhancedViewingAngleEnable ret = ", enhancedViewingAngleEnable, TAG);
        }
        return enhancedViewingAngleEnable;
    }

    public static int setFactoryColorTemperature(int i) {
        int factoryColorTemperature = -1;
        if (a.f("setFactoryColorTemperature val:", i, TAG)) {
            try {
                factoryColorTemperature = mVideoServer.setFactoryColorTemperature(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setFactoryColorTemperature ret = ", factoryColorTemperature, TAG);
        }
        return factoryColorTemperature;
    }

    public static int setFactoryColorTunerBrightness(int i) {
        int factoryColorTunerBrightness = -1;
        if (a.f("setFactoryColorTunerBrightness val:", i, TAG)) {
            try {
                factoryColorTunerBrightness = mVideoServer.setFactoryColorTunerBrightness(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setFactoryColorTunerBrightness ret = ", factoryColorTunerBrightness, TAG);
        }
        return factoryColorTunerBrightness;
    }

    public static int setFactoryColorTunerColor(int i) {
        int factoryColorTunerColor = -1;
        if (a.f("setFactoryColorTunerColor val:", i, TAG)) {
            try {
                factoryColorTunerColor = mVideoServer.setFactoryColorTunerColor(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setFactoryColorTunerColor ret = ", factoryColorTunerColor, TAG);
        }
        return factoryColorTunerColor;
    }

    public static int setFactoryColorTunerHue(int i) {
        int factoryColorTunerHue = -1;
        if (a.f("setFactoryColorTunerHue val:", i, TAG)) {
            try {
                factoryColorTunerHue = mVideoServer.setFactoryColorTunerHue(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setFactoryColorTunerHue ret = ", factoryColorTunerHue, TAG);
        }
        return factoryColorTunerHue;
    }

    public static int setFactoryColorTunerSaturation(int i) {
        int factoryColorTunerSaturation = -1;
        if (a.f("setFactoryColorTunerSaturation val:", i, TAG)) {
            try {
                factoryColorTunerSaturation = mVideoServer.setFactoryColorTunerSaturation(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setFactoryColorTunerSaturation ret = ", factoryColorTunerSaturation, TAG);
        }
        return factoryColorTunerSaturation;
    }

    public static int setFactoryMultiPointWhiteBalanceBlue(int i) {
        int factoryMultiPointWhiteBalanceBlue = -1;
        if (a.f("setFactoryMultiPointWhiteBalanceBlue val:", i, TAG)) {
            try {
                factoryMultiPointWhiteBalanceBlue = mVideoServer.setFactoryMultiPointWhiteBalanceBlue(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setFactoryMultiPointWhiteBalanceBlue ret = ", factoryMultiPointWhiteBalanceBlue, TAG);
        }
        return factoryMultiPointWhiteBalanceBlue;
    }

    public static int setFactoryMultiPointWhiteBalanceGreen(int i) {
        int factoryMultiPointWhiteBalanceGreen = -1;
        if (a.f("setFactoryMultiPointWhiteBalanceGreen val:", i, TAG)) {
            try {
                factoryMultiPointWhiteBalanceGreen = mVideoServer.setFactoryMultiPointWhiteBalanceGreen(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setFactoryMultiPointWhiteBalanceGreen ret = ", factoryMultiPointWhiteBalanceGreen, TAG);
        }
        return factoryMultiPointWhiteBalanceGreen;
    }

    public static int setFactoryMultiPointWhiteBalanceLevel(int i) {
        int factoryMultiPointWhiteBalanceLevel = -1;
        if (a.f("setFactoryMultiPointWhiteBalanceLevel val:", i, TAG)) {
            try {
                factoryMultiPointWhiteBalanceLevel = mVideoServer.setFactoryMultiPointWhiteBalanceLevel(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setFactoryMultiPointWhiteBalanceLevel ret = ", factoryMultiPointWhiteBalanceLevel, TAG);
        }
        return factoryMultiPointWhiteBalanceLevel;
    }

    public static int setFactoryMultiPointWhiteBalanceRed(int i) {
        int factoryMultiPointWhiteBalanceRed = -1;
        if (a.f("setFactoryMultiPointWhiteBalanceRed val:", i, TAG)) {
            try {
                factoryMultiPointWhiteBalanceRed = mVideoServer.setFactoryMultiPointWhiteBalanceRed(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setFactoryMultiPointWhiteBalanceRed ret = ", factoryMultiPointWhiteBalanceRed, TAG);
        }
        return factoryMultiPointWhiteBalanceRed;
    }

    public static int setFactoryWhiteBalanceGainBlue(int i) {
        int factoryWhiteBalanceGainBlue = -1;
        if (a.f("setFactoryWhiteBalanceGainBlue val:", i, TAG)) {
            try {
                factoryWhiteBalanceGainBlue = mVideoServer.setFactoryWhiteBalanceGainBlue(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setFactoryWhiteBalanceGainBlue ret = ", factoryWhiteBalanceGainBlue, TAG);
        }
        return factoryWhiteBalanceGainBlue;
    }

    public static int setFactoryWhiteBalanceGainGreen(int i) {
        int factoryWhiteBalanceGainGreen = -1;
        if (a.f("setFactoryWhiteBlanceGainGreen val:", i, TAG)) {
            try {
                factoryWhiteBalanceGainGreen = mVideoServer.setFactoryWhiteBalanceGainGreen(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setFactoryWhiteBalanceGainGreen ret = ", factoryWhiteBalanceGainGreen, TAG);
        }
        return factoryWhiteBalanceGainGreen;
    }

    public static int setFactoryWhiteBalanceGainRed(int i) {
        int factoryWhiteBalanceGainRed = -1;
        if (a.f("setFactoryWhiteBalanceGainRed val:", i, TAG)) {
            try {
                factoryWhiteBalanceGainRed = mVideoServer.setFactoryWhiteBalanceGainRed(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setFactoryWhiteBalanceGainRed ret = ", factoryWhiteBalanceGainRed, TAG);
        }
        return factoryWhiteBalanceGainRed;
    }

    public static int setFactoryWhiteBalanceOffsetBlue(int i) {
        int factoryWhiteBalanceOffsetBlue = -1;
        if (a.f("setFactoryWhiteBalanceOffsetBlue val:", i, TAG)) {
            try {
                factoryWhiteBalanceOffsetBlue = mVideoServer.setFactoryWhiteBalanceOffsetBlue(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setFactoryWhiteBalanceOffsetBlue ret = ", factoryWhiteBalanceOffsetBlue, TAG);
        }
        return factoryWhiteBalanceOffsetBlue;
    }

    public static int setFactoryWhiteBalanceOffsetGreen(int i) {
        int factoryWhiteBalanceOffsetGreen = -1;
        if (a.f("setFactoryWhiteBalanceOffsetGreen val:", i, TAG)) {
            try {
                factoryWhiteBalanceOffsetGreen = mVideoServer.setFactoryWhiteBalanceOffsetGreen(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setFactoryWhiteBalanceOffsetGreen ret = ", factoryWhiteBalanceOffsetGreen, TAG);
        }
        return factoryWhiteBalanceOffsetGreen;
    }

    public static int setFactoryWhiteBalanceOffsetRed(int i) {
        int factoryWhiteBalanceOffsetRed = -1;
        if (a.f("setFactoryWhiteBalanceOffsetRed val:", i, TAG)) {
            try {
                factoryWhiteBalanceOffsetRed = mVideoServer.setFactoryWhiteBalanceOffsetRed(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setFactoryWhiteBalanceOffsetRed ret = ", factoryWhiteBalanceOffsetRed, TAG);
        }
        return factoryWhiteBalanceOffsetRed;
    }

    public static int setFilmmakerDetectionEnable(int i) {
        int filmmakerDetectionEnable = -1;
        if (a.f("setFilmmakerDetectionEnable val:", i, TAG)) {
            try {
                filmmakerDetectionEnable = mVideoServer.setFilmmakerDetectionEnable(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setFilmmakerDetectionEnable ret = ", filmmakerDetectionEnable, TAG);
        }
        return filmmakerDetectionEnable;
    }

    public static int setFilmmakerModeSelected(int i) {
        int filmmakerModeSelected = -1;
        if (a.f("setFilmmakerModeSelected val:", i, TAG)) {
            try {
                filmmakerModeSelected = mVideoServer.setFilmmakerModeSelected(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setFilmmakerModeSelected ret = ", filmmakerModeSelected, TAG);
        }
        return filmmakerModeSelected;
    }

    public static int setForce120HzEnable(int i) {
        int itemValue = -1;
        if (a.f("setForce120HzEnable val:", i, TAG)) {
            try {
                itemValue = mVideoServer.setItemValue(54, i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setForce120HzEnable ret = ", itemValue, TAG);
        }
        return itemValue;
    }

    public static int setFreeSyncSwitch(int i) {
        int freeSyncSwitch = -1;
        if (a.f("setFreeSyncSwitch val:", i, TAG)) {
            try {
                freeSyncSwitch = mVideoServer.setFreeSyncSwitch(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setFreeSyncSwitch ret = ", freeSyncSwitch, TAG);
        }
        return freeSyncSwitch;
    }

    public static int setFteFlag(int i) {
        int fTEState = -1;
        if (a.f("setFteFlag val:", i, TAG)) {
            try {
                fTEState = mVideoServer.setFTEState(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setFteFlag ret = ", fTEState, TAG);
        }
        return fTEState;
    }

    public static int setGameDetectionEnable(int i) {
        int gameDetectionEnable = -1;
        if (a.f("setGameDetectionEnable val:", i, TAG)) {
            try {
                gameDetectionEnable = mVideoServer.setGameDetectionEnable(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setGameDetectionEnable ret = ", gameDetectionEnable, TAG);
        }
        return gameDetectionEnable;
    }

    public static int setGammaAdjustment(int i) {
        int gammaAdjustment = -1;
        if (a.f("setGammaAdjustment val:", i, TAG)) {
            try {
                gammaAdjustment = mVideoServer.setGammaAdjustment(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setGammaAdjustment ret = ", gammaAdjustment, TAG);
        }
        return gammaAdjustment;
    }

    public static int setGammaCalibrationGain(int i) {
        int gammaCalibrationGain = -1;
        if (a.f("setGammaCalibrationGain val:", i, TAG)) {
            try {
                gammaCalibrationGain = mVideoServer.setGammaCalibrationGain(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setGammaCalibrationGain ret = ", gammaCalibrationGain, TAG);
        }
        return gammaCalibrationGain;
    }

    public static int setGammaCalibrationLevel(int i) {
        int gammaCalibrationLevel = -1;
        if (a.f("setGammaCalibrationLevel val:", i, TAG)) {
            try {
                gammaCalibrationLevel = mVideoServer.setGammaCalibrationLevel(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setGammaCalibrationLevel ret = ", gammaCalibrationLevel, TAG);
        }
        return gammaCalibrationLevel;
    }

    public static int setHDMI20Format(int i) {
        int hDMI20Format = -1;
        if (a.f("setHDMI20Format val:", i, TAG)) {
            try {
                hDMI20Format = mVideoServer.setHDMI20Format(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setHDMI20Format ret = ", hDMI20Format, TAG);
        }
        return hDMI20Format;
    }

    public static int setHdmiDynamicRange(int i) {
        int hdmiDynamicRange = -1;
        if (a.f("setHdmiDynamicRange val:", i, TAG)) {
            try {
                hdmiDynamicRange = mVideoServer.setHdmiDynamicRange(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setHdmiDynamicRange ret = ", hdmiDynamicRange, TAG);
        }
        return hdmiDynamicRange;
    }

    public static int setHue20(int i) {
        int hue20 = -1;
        if (a.f("setHue20 val:", i, TAG)) {
            try {
                hue20 = mVideoServer.setHue20(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setHue20 ret = ", hue20, TAG);
        }
        return hue20;
    }

    public static int setHue80(int i) {
        int hue80 = -1;
        if (a.f("setHue80 val:", i, TAG)) {
            try {
                hue80 = mVideoServer.setHue80(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setHue80 ret = ", hue80, TAG);
        }
        return hue80;
    }

    public static int setHueMax(int i) {
        int hueMax = -1;
        if (a.f("setHueMax val:", i, TAG)) {
            try {
                hueMax = mVideoServer.setHueMax(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setHueMax ret = ", hueMax, TAG);
        }
        return hueMax;
    }

    public static int setHueMid(int i) {
        int hueMid = -1;
        if (a.f("setHueMid val:", i, TAG)) {
            try {
                hueMid = mVideoServer.setHueMid(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setHueMid ret = ", hueMid, TAG);
        }
        return hueMid;
    }

    public static int setHueMin(int i) {
        int hueMin = -1;
        if (a.f("setHueMin val:", i, TAG)) {
            try {
                hueMin = mVideoServer.setHueMin(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setHueMin ret = ", hueMin, TAG);
        }
        return hueMin;
    }

    public static int setISFSwitch(int i) {
        int iSFSwitch = -1;
        if (a.f("setISFSwitch val:", i, TAG)) {
            try {
                iSFSwitch = mVideoServer.setISFSwitch(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setISFSwitch ret = ", iSFSwitch, TAG);
        }
        return iSFSwitch;
    }

    public static int setISFType(int i) {
        int iSFType = -1;
        if (a.f("setISFType val:", i, TAG)) {
            try {
                iSFType = mVideoServer.setISFType(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setISFType ret = ", iSFType, TAG);
        }
        return iSFType;
    }

    public static int setInsidePattern(int i) {
        int insidePattern = -1;
        if (a.f("setInsidePattern val:", i, TAG)) {
            try {
                insidePattern = mVideoServer.setInsidePattern(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setInsidePattern ret = ", insidePattern, TAG);
        }
        return insidePattern;
    }

    public static int setInstantGameResponseSwitch(int i) {
        int instantGameResponseSwitch = -1;
        if (a.f("setInstantGameResponseSwitch val:", i, TAG)) {
            try {
                instantGameResponseSwitch = mVideoServer.setInstantGameResponseSwitch(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setInstantGameResponseSwitch ret = ", instantGameResponseSwitch, TAG);
        }
        return instantGameResponseSwitch;
    }

    public static int setLDTestEnable(int i) {
        int lDTestEnable = -1;
        if (a.f("setLDTestEnable val:", i, TAG)) {
            try {
                lDTestEnable = mVideoServer.setLDTestEnable(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setLDTestEnable ret = ", lDTestEnable, TAG);
        }
        return lDTestEnable;
    }

    public static int setLaserScreenSize(int i) {
        int laserScreenSize = -1;
        if (a.f("setLaserScreenSize val:", i, TAG)) {
            try {
                laserScreenSize = mVideoServer.setLaserScreenSize(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setLaserScreenSize ret = ", laserScreenSize, TAG);
        }
        return laserScreenSize;
    }

    public static int setLaserScreenType(int i) {
        int laserScreenType = -1;
        if (a.f("setLaserScreenType val:", i, TAG)) {
            try {
                laserScreenType = mVideoServer.setLaserScreenType(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setLaserScreenType ret = ", laserScreenType, TAG);
        }
        return laserScreenType;
    }

    public static int setLightSensorALS10(int i) {
        int lightSensorALS10 = -1;
        if (a.f("setLightSensorALS10 val:", i, TAG)) {
            try {
                lightSensorALS10 = mVideoServer.setLightSensorALS10(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setLightSensorALS10 ret = ", lightSensorALS10, TAG);
        }
        return lightSensorALS10;
    }

    public static int setLightSensorALS300(int i) {
        int lightSensorALS300 = -1;
        if (a.f("setLightSensorALS300 val:", i, TAG)) {
            try {
                lightSensorALS300 = mVideoServer.setLightSensorALS300(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setLightSensorALS300 ret = ", lightSensorALS300, TAG);
        }
        return lightSensorALS300;
    }

    public static int setLightSensorALS80(int i) {
        int lightSensorALS80 = -1;
        if (a.f("setLightSensorALS80 val:", i, TAG)) {
            try {
                lightSensorALS80 = mVideoServer.setLightSensorALS80(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setLightSensorALS80 ret = ", lightSensorALS80, TAG);
        }
        return lightSensorALS80;
    }

    public static int setLightSensorALSMax(int i) {
        int lightSensorALSMax = -1;
        if (a.f("setLightSensorALSMax val:", i, TAG)) {
            try {
                lightSensorALSMax = mVideoServer.setLightSensorALSMax(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setLightSensorALSMax ret = ", lightSensorALSMax, TAG);
        }
        return lightSensorALSMax;
    }

    public static int setLightSensorALSMin(int i) {
        int lightSensorALSMin = -1;
        if (a.f("setLightSensorALSMin val:", i, TAG)) {
            try {
                lightSensorALSMin = mVideoServer.setLightSensorALSMin(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setLightSensorALSMin ret = ", lightSensorALSMin, TAG);
        }
        return lightSensorALSMin;
    }

    public static int setLightSensorCurve(int i, int i2, int i3) {
        StringBuilder sbB = a.b("setLightSensorCurve mode: ", i, "index: ", i2, "value: ");
        sbB.append(i3);
        HisLogger.d(TAG, sbB.toString());
        int lightSensorCurve = -1;
        if (getHidlService()) {
            try {
                lightSensorCurve = mVideoServer.setLightSensorCurve(i, i2, i3);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setLightSensorCurve ret = ", lightSensorCurve, TAG);
        }
        return lightSensorCurve;
    }

    public static int setLightSensorEnable(int i) {
        int lightSensorEnable = -1;
        if (a.f("setLightSensorEnable val:", i, TAG)) {
            try {
                lightSensorEnable = mVideoServer.setLightSensorEnable(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setLightSensorEnable ret = ", lightSensorEnable, TAG);
        }
        return lightSensorEnable;
    }

    public static int setLightSensorLS10(int i) {
        int lightSensorLS10 = -1;
        if (a.f("setLightSensorLS10 val:", i, TAG)) {
            try {
                lightSensorLS10 = mVideoServer.setLightSensorLS10(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setLightSensorLS10 ret = ", lightSensorLS10, TAG);
        }
        return lightSensorLS10;
    }

    public static int setLightSensorLS300(int i) {
        int lightSensorLS300 = -1;
        if (a.f("setLightSensorLS300 val:", i, TAG)) {
            try {
                lightSensorLS300 = mVideoServer.setLightSensorLS300(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setLightSensorLS300 ret = ", lightSensorLS300, TAG);
        }
        return lightSensorLS300;
    }

    public static int setLightSensorLS80(int i) {
        int lightSensorLS80 = -1;
        if (a.f("setLightSensorLS80 val:", i, TAG)) {
            try {
                lightSensorLS80 = mVideoServer.setLightSensorLS80(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setLightSensorLS80 ret = ", lightSensorLS80, TAG);
        }
        return lightSensorLS80;
    }

    public static int setLightSensorLSMax(int i) {
        int lightSensorLSMax = -1;
        if (a.f("setLightSensorLSMax val:", i, TAG)) {
            try {
                lightSensorLSMax = mVideoServer.setLightSensorLSMax(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setLightSensorLSMax ret = ", lightSensorLSMax, TAG);
        }
        return lightSensorLSMax;
    }

    public static int setLightSensorLSMin(int i) {
        int lightSensorLSMin = -1;
        if (a.f("setLightSensorLSMin val:", i, TAG)) {
            try {
                lightSensorLSMin = mVideoServer.setLightSensorLSMin(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setLightSensorLSMin ret = ", lightSensorLSMin, TAG);
        }
        return lightSensorLSMin;
    }

    public static int setLightSensorMax(int i) {
        int lightSensorMax = -1;
        if (a.f("setLightSensorMax val:", i, TAG)) {
            try {
                lightSensorMax = mVideoServer.setLightSensorMax(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setLightSensorMax ret = ", lightSensorMax, TAG);
        }
        return lightSensorMax;
    }

    public static int setLightSensorMin(int i) {
        int lightSensorMin = -1;
        if (a.f("setLightSensorMin val:", i, TAG)) {
            try {
                lightSensorMin = mVideoServer.setLightSensorMin(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setLightSensorMin ret = ", lightSensorMin, TAG);
        }
        return lightSensorMin;
    }

    public static int setLinkSoundEnable(int i) {
        int linkSoundEnable = -1;
        if (a.f("setLinkSoundEnable val:", i, TAG)) {
            try {
                linkSoundEnable = mVideoServer.setLinkSoundEnable(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setLinkSoundEnable ret = ", linkSoundEnable, TAG);
        }
        return linkSoundEnable;
    }

    public static int setLocalDimming(int i) {
        int localDimming = -1;
        if (a.f("setLocalDimming val:", i, TAG)) {
            try {
                localDimming = mVideoServer.setLocalDimming(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setLocalDimming ret = ", localDimming, TAG);
        }
        return localDimming;
    }

    public static int setLocalDimmingCheck(int i) {
        int itemValue = -1;
        if (a.f("setLocalDimmingCheck val:", i, TAG)) {
            try {
                itemValue = mVideoServer.setItemValue(11, i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setLocalDimmingCheck ret = ", itemValue, TAG);
        }
        return itemValue;
    }

    public static int setLowBlueLight(int i) {
        int lowBlueLight = -1;
        if (a.f("setLowBlueLight val:", i, TAG)) {
            try {
                lowBlueLight = mVideoServer.setLowBlueLight(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setLowBlueLight ret = ", lowBlueLight, TAG);
        }
        return lowBlueLight;
    }

    public static int setMemc(int i) {
        int memc = -1;
        if (a.f("setMemc val:", i, TAG)) {
            try {
                memc = mVideoServer.setMemc(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setMemc ret = ", memc, TAG);
        }
        return memc;
    }

    public static int setMemcBlurReduction(int i) {
        int memcBlurReduction = -1;
        if (a.f("setMemcBlurReduction val:", i, TAG)) {
            try {
                memcBlurReduction = mVideoServer.setMemcBlurReduction(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setMemcBlurReduction ret = ", memcBlurReduction, TAG);
        }
        return memcBlurReduction;
    }

    public static int setMemcJudderReduction(int i) {
        int memcJudderReduction = -1;
        if (a.f("setMemcJudderReduction val:", i, TAG)) {
            try {
                memcJudderReduction = mVideoServer.setMemcJudderReduction(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setMemcJudderReduction ret = ", memcJudderReduction, TAG);
        }
        return memcJudderReduction;
    }

    public static int setMotionClearnessEnable(int i) {
        int motionClearnessEnable = -1;
        if (a.f("setMotionClearnessEnable val:", i, TAG)) {
            try {
                motionClearnessEnable = mVideoServer.setMotionClearnessEnable(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setMotionClearnessEnable ret = ", motionClearnessEnable, TAG);
        }
        return motionClearnessEnable;
    }

    public static int setMpegNoiseReduction(int i) {
        int mpegNoiseReduction = -1;
        if (a.f("setMpegNoiseReduction val:", i, TAG)) {
            try {
                mpegNoiseReduction = mVideoServer.setMpegNoiseReduction(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setMpegNoiseReduction ret = ", mpegNoiseReduction, TAG);
        }
        return mpegNoiseReduction;
    }

    public static int setMultiPointWhiteBalanceApplyToAll(int i) {
        int multiPointWhiteBalanceApplyToAll = -1;
        if (a.f("setMultiPointWhiteBalanceApplyToAll val:", i, TAG)) {
            try {
                multiPointWhiteBalanceApplyToAll = mVideoServer.setMultiPointWhiteBalanceApplyToAll(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setMultiPointWhiteBalanceApplyToAll ret = ", multiPointWhiteBalanceApplyToAll, TAG);
        }
        return multiPointWhiteBalanceApplyToAll;
    }

    public static int setMultiPointWhiteBalanceBlue(int i) {
        int multiPointWhiteBalanceBlue = -1;
        if (a.f("setMultiPointWhiteBalanceBlue val:", i, TAG)) {
            try {
                multiPointWhiteBalanceBlue = mVideoServer.setMultiPointWhiteBalanceBlue(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setMultiPointWhiteBalanceBlue ret = ", multiPointWhiteBalanceBlue, TAG);
        }
        return multiPointWhiteBalanceBlue;
    }

    public static int setMultiPointWhiteBalanceEnable(int i) {
        int multiPointWhiteBalanceEnable = -1;
        if (a.f("setMultiPointWhiteBalanceEnable val:", i, TAG)) {
            try {
                multiPointWhiteBalanceEnable = mVideoServer.setMultiPointWhiteBalanceEnable(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setMultiPointWhiteBalanceEnable ret = ", multiPointWhiteBalanceEnable, TAG);
        }
        return multiPointWhiteBalanceEnable;
    }

    public static int setMultiPointWhiteBalanceGreen(int i) {
        int multiPointWhiteBalanceGreen = -1;
        if (a.f("setMultiPointWhiteBalanceGreen val:", i, TAG)) {
            try {
                multiPointWhiteBalanceGreen = mVideoServer.setMultiPointWhiteBalanceGreen(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setMultiPointWhiteBalanceGreen ret = ", multiPointWhiteBalanceGreen, TAG);
        }
        return multiPointWhiteBalanceGreen;
    }

    public static int setMultiPointWhiteBalanceLevel(int i) {
        int multiPointWhiteBalanceLevel = -1;
        if (a.f("setMultiPointWhiteBalanceLevel val:", i, TAG)) {
            try {
                multiPointWhiteBalanceLevel = mVideoServer.setMultiPointWhiteBalanceLevel(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setMultiPointWhiteBalanceLevel ret = ", multiPointWhiteBalanceLevel, TAG);
        }
        return multiPointWhiteBalanceLevel;
    }

    public static int setMultiPointWhiteBalanceRed(int i) {
        int multiPointWhiteBalanceRed = -1;
        if (a.f("setMultiPointWhiteBalanceRed val:", i, TAG)) {
            try {
                multiPointWhiteBalanceRed = mVideoServer.setMultiPointWhiteBalanceRed(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setMultiPointWhiteBalanceRed ret = ", multiPointWhiteBalanceRed, TAG);
        }
        return multiPointWhiteBalanceRed;
    }

    public static int setNetVideoBeautySwitch(int i) {
        int netVideoBeautySwitch = -1;
        if (a.f("setNetVideoBeautySwitch val:", i, TAG)) {
            try {
                netVideoBeautySwitch = mVideoServer.setNetVideoBeautySwitch(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setNetVideoBeautySwitch ret = ", netVideoBeautySwitch, TAG);
        }
        return netVideoBeautySwitch;
    }

    public static int setNoiseReduction(int i) {
        int noiseReduction = -1;
        if (a.f("setNoiseReduction val:", i, TAG)) {
            try {
                noiseReduction = mVideoServer.setNoiseReduction(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setNoiseReduction ret = ", noiseReduction, TAG);
        }
        return noiseReduction;
    }

    public static int setOLEDHDREnable(int i) {
        int oLEDHDREnable = -1;
        if (a.f("setOLEDHDREnable val:", i, TAG)) {
            try {
                oLEDHDREnable = mVideoServer.setOLEDHDREnable(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setOLEDHDREnable ret = ", oLEDHDREnable, TAG);
        }
        return oLEDHDREnable;
    }

    public static int setOLEDLEAEnable(int i) {
        int oLEDLEAEnable = -1;
        if (a.f("setOLEDLEAEnable val:", i, TAG)) {
            try {
                oLEDLEAEnable = mVideoServer.setOLEDLEAEnable(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setOLEDLEAEnable ret = ", oLEDLEAEnable, TAG);
        }
        return oLEDLEAEnable;
    }

    public static int setOLEDLightPercent(int i) {
        int backlightPercent = -1;
        if (a.f("setOLEDlightPercent val:", i, TAG)) {
            try {
                backlightPercent = mVideoServer.setBacklightPercent(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setOLEDlightPercent ret = ", backlightPercent, TAG);
        }
        return backlightPercent;
    }

    public static int setOLEDLocalFrozenChkEnable(int i) {
        int oLEDLocalFrozenChkEnable = -1;
        if (a.f("setOLEDLocalFrozenChkEnable val:", i, TAG)) {
            try {
                oLEDLocalFrozenChkEnable = mVideoServer.setOLEDLocalFrozenChkEnable(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setOLEDLocalFrozenChkEnable ret = ", oLEDLocalFrozenChkEnable, TAG);
        }
        return oLEDLocalFrozenChkEnable;
    }

    public static int setOLEDLocalLumaChkEnable(int i) {
        int oLEDLocalLumaChkEnable = -1;
        if (a.f("setOLEDLocalLumaChkEnable val:", i, TAG)) {
            try {
                oLEDLocalLumaChkEnable = mVideoServer.setOLEDLocalLumaChkEnable(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setOLEDLocalLumaChkEnable ret = ", oLEDLocalLumaChkEnable, TAG);
        }
        return oLEDLocalLumaChkEnable;
    }

    public static int setOLEDPLCCurve(int i) {
        int oLEDPLCCurve = -1;
        if (a.f("setOLEDPLCCurve val:", i, TAG)) {
            try {
                oLEDPLCCurve = mVideoServer.setOLEDPLCCurve(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setOLEDPLCCurve ret = ", oLEDPLCCurve, TAG);
        }
        return oLEDPLCCurve;
    }

    public static int setOLEDPeakingBrightness(int i) {
        int dynamicBacklight = -1;
        if (a.f("setOLEDPeakingBrightness val:", i, TAG)) {
            try {
                dynamicBacklight = mVideoServer.setDynamicBacklight(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setOLEDPeakingBrightness ret = ", dynamicBacklight, TAG);
        }
        return dynamicBacklight;
    }

    public static int setOLEDTPCEnable(int i) {
        int oLEDTPCEnable = -1;
        if (a.f("setOLEDTPCEnable val:", i, TAG)) {
            try {
                oLEDTPCEnable = mVideoServer.setOLEDTPCEnable(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setOLEDTPCEnable ret = ", oLEDTPCEnable, TAG);
        }
        return oLEDTPCEnable;
    }

    public static int setOverscanBottom(int i) {
        int overscanBottom = -1;
        if (a.f("setOverscanBottom val:", i, TAG)) {
            try {
                overscanBottom = mVideoServer.setOverscanBottom(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setOverscanBottom ret = ", overscanBottom, TAG);
        }
        return overscanBottom;
    }

    public static int setOverscanEnable(int i) {
        int overscanEnable = -1;
        if (a.f("setOverscanEnable val:", i, TAG)) {
            try {
                overscanEnable = mVideoServer.setOverscanEnable(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setOverscanEnable ret = ", overscanEnable, TAG);
        }
        return overscanEnable;
    }

    public static int setOverscanLeft(int i) {
        int overscanLeft = -1;
        if (a.f("setOverscanLeft val:", i, TAG)) {
            try {
                overscanLeft = mVideoServer.setOverscanLeft(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setOverscanLeft ret = ", overscanLeft, TAG);
        }
        return overscanLeft;
    }

    public static int setOverscanRight(int i) {
        int overscanRight = -1;
        if (a.f("setOverscanRight val:", i, TAG)) {
            try {
                overscanRight = mVideoServer.setOverscanRight(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setOverscanRight ret = ", overscanRight, TAG);
        }
        return overscanRight;
    }

    public static int setOverscanTop(int i) {
        int overscanTop = -1;
        if (a.f("setOverscanTop val:", i, TAG)) {
            try {
                overscanTop = mVideoServer.setOverscanTop(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setOverscanTop ret = ", overscanTop, TAG);
        }
        return overscanTop;
    }

    public static int setPanel(int i) {
        int panel = -1;
        if (a.f("setPanel val:", i, TAG)) {
            try {
                panel = mVideoServer.setPanel(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setPanel ret = ", panel, TAG);
        }
        return panel;
    }

    public static int setPictureMode(int i) {
        int pictureMode = -1;
        if (a.f("setPictureMode val:", i, TAG)) {
            try {
                pictureMode = mVideoServer.setPictureMode(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setPictureMode ret = ", pictureMode, TAG);
        }
        return pictureMode;
    }

    public static int setPictureModeByFactory(int i) {
        int pictureModeByFactory = -1;
        if (a.f("setPictureModeByFactory val:", i, TAG)) {
            try {
                pictureModeByFactory = mVideoServer.setPictureModeByFactory(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setPictureModeByFactory ret = ", pictureModeByFactory, TAG);
        }
        return pictureModeByFactory;
    }

    public static int setPictureSettingsToAllSource(int i) {
        int pictureSettingsToAllSource = -1;
        if (a.f("setPictureSettingsToAllSource val:", i, TAG)) {
            try {
                pictureSettingsToAllSource = mVideoServer.setPictureSettingsToAllSource(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setPictureSettingsToAllSource ret = ", pictureSettingsToAllSource, TAG);
        }
        return pictureSettingsToAllSource;
    }

    public static int setPixelShiftEnable(int i) {
        int pixelShiftEnable = -1;
        if (a.f("setPixelShiftEnable val:", i, TAG)) {
            try {
                pixelShiftEnable = mVideoServer.setPixelShiftEnable(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setPixelShiftEnable ret = ", pixelShiftEnable, TAG);
        }
        return pixelShiftEnable;
    }

    public static int setPixelShiftX(int i) {
        int pixelShiftEnable = -1;
        if (a.f("setPixelShiftEnable val:", i, TAG)) {
            try {
                pixelShiftEnable = mVideoServer.setPixelShiftEnable(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setPixelShiftEnable ret = ", pixelShiftEnable, TAG);
        }
        return pixelShiftEnable;
    }

    public static int setPixelShiftY(int i) {
        int pixelShiftEnable = -1;
        if (a.f("setPixelShiftEnable val:", i, TAG)) {
            try {
                pixelShiftEnable = mVideoServer.setPixelShiftEnable(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setPixelShiftEnable ret = ", pixelShiftEnable, TAG);
        }
        return pixelShiftEnable;
    }

    public static int setRGBPattern(int i, int i2, int i3, int i4) {
        StringBuilder sbB = a.b("setRGBPattern mode:", i, "red:", i2, "green");
        sbB.append(i3);
        sbB.append("blue");
        sbB.append(i4);
        HisLogger.d(TAG, sbB.toString());
        int rGBPattern = -1;
        if (getHidlService()) {
            try {
                rGBPattern = mVideoServer.setRGBPattern(i, i2, i3, i4);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setRGBPattern ret = ", rGBPattern, TAG);
        }
        return rGBPattern;
    }

    public static int setRgbOnly(int i) {
        int rgbOnly = -1;
        if (a.f("setRgbOnly val:", i, TAG)) {
            try {
                rgbOnly = mVideoServer.setRgbOnly(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setRgbOnly ret = ", rgbOnly, TAG);
        }
        return rgbOnly;
    }

    public static int setSaturation(int i) {
        if (getHidlService()) {
            try {
                mVideoServer.isTVSModel(new IHisVideoApi.isTVSModelCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.37
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.isTVSModelCallback
                    public void onValues(int i2, int i3) {
                        HisVideoApi.mReturnVal = i2;
                        HisVideoApi.mIsTvs = i3;
                        StringBuilder sbA = a.a("isTVSModel ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mIsTvs, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (1 == mIsTvs) {
            i += 50;
        }
        int saturation = -1;
        if (a.f("setSaturation val:", i, TAG)) {
            try {
                saturation = mVideoServer.setSaturation(i);
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            a.c("setSaturation ret = ", saturation, TAG);
        }
        return saturation;
    }

    public static int setSaturation20(int i) {
        int saturation20 = -1;
        if (a.f("setSaturation20 val:", i, TAG)) {
            try {
                saturation20 = mVideoServer.setSaturation20(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setSaturation20 ret = ", saturation20, TAG);
        }
        return saturation20;
    }

    public static int setSaturation80(int i) {
        int saturation80 = -1;
        if (a.f("setSaturation80 val:", i, TAG)) {
            try {
                saturation80 = mVideoServer.setSaturation80(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setSaturation80 ret = ", saturation80, TAG);
        }
        return saturation80;
    }

    public static int setSaturationByFactory(int i) {
        int saturationByFactory = -1;
        if (a.f("setSaturationByFactory val:", i, TAG)) {
            try {
                saturationByFactory = mVideoServer.setSaturationByFactory(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setSaturationByFactory ret = ", saturationByFactory, TAG);
        }
        return saturationByFactory;
    }

    public static int setSaturationMax(int i) {
        int saturationMax = -1;
        if (a.f("setSaturationMax val:", i, TAG)) {
            try {
                saturationMax = mVideoServer.setSaturationMax(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setSaturationMax ret = ", saturationMax, TAG);
        }
        return saturationMax;
    }

    public static int setSaturationMid(int i) {
        int saturationMid = -1;
        if (a.f("setSaturationMid val:", i, TAG)) {
            try {
                saturationMid = mVideoServer.setSaturationMid(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setSaturationMid ret = ", saturationMid, TAG);
        }
        return saturationMid;
    }

    public static int setSaturationMin(int i) {
        int saturationMin = -1;
        if (a.f("setSaturationMin val:", i, TAG)) {
            try {
                saturationMin = mVideoServer.setSaturationMin(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setSaturationMin ret = ", saturationMin, TAG);
        }
        return saturationMin;
    }

    public static int setScreenCapture(String str, int i) {
        HisLogger.d(TAG, "setScreenCapture, path: '" + str + "' bufferSize: " + i);
        int iScreenCapture = -1;
        if (getHidlService()) {
            try {
                iScreenCapture = mVideoServer.screenCapture(str, i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setScreenCapture ret = ", iScreenCapture, TAG);
        }
        return iScreenCapture;
    }

    public static int setScreenOutputDispRect(int i, int i2, int i3, int i4) {
        StringBuilder sbB = a.b("setScreenOutputDispRect x: ", i, " y: ", i2, " width: ");
        sbB.append(i3);
        sbB.append(" height: ");
        sbB.append(i4);
        HisLogger.d(TAG, sbB.toString());
        int screenOutputDispRect = -1;
        if (getHidlService()) {
            try {
                screenOutputDispRect = mVideoServer.setScreenOutputDispRect(i, i2, i3, i4);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setScreenOutputDispRect ret = ", screenOutputDispRect, TAG);
        }
        return screenOutputDispRect;
    }

    public static int setScreenOutputDispRectEx(int i, int i2, int i3, int i4, int i5) {
        int itemData;
        StringBuilder sbB = a.b("setScreenOutputDispRectEx vdpId: ", i, " x: ", i2, " y: ");
        sbB.append(i3);
        sbB.append(" width: ");
        sbB.append(i4);
        sbB.append(" height: ");
        sbB.append(i5);
        HisLogger.d(TAG, sbB.toString());
        int i6 = -1;
        if (getHidlService()) {
            try {
                if (i == 0) {
                    itemData = mVideoServer.setScreenOutputDispRect(i2, i3, i4, i5);
                } else {
                    if (1 == i) {
                        ArrayList<Integer> arrayList = new ArrayList<>();
                        arrayList.add(Integer.valueOf(i2));
                        arrayList.add(Integer.valueOf(i3));
                        arrayList.add(Integer.valueOf(i4));
                        arrayList.add(Integer.valueOf(i5));
                        itemData = mVideoServer.setItemData(18, arrayList);
                    } else {
                        HisLogger.e(TAG, "setScreenOutputDispRectEx not support vdpId: " + i);
                    }
                    a.c("setScreenOutputDispRectEx ret = ", i6, TAG);
                }
                i6 = itemData;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setScreenOutputDispRectEx ret = ", i6, TAG);
        }
        return i6;
    }

    public static int setSharpness(int i) {
        if (getHidlService()) {
            try {
                mVideoServer.isTVSModel(new IHisVideoApi.isTVSModelCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.43
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.isTVSModelCallback
                    public void onValues(int i2, int i3) {
                        HisVideoApi.mReturnVal = i2;
                        HisVideoApi.mIsTvs = i3;
                        StringBuilder sbA = a.a("isTVSModel ret =");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisVideoApi.mIsTvs, HisVideoApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (1 == mIsTvs) {
            i += 50;
        }
        int sharpness = -1;
        if (a.f("setSharpness val:", i, TAG)) {
            try {
                sharpness = mVideoServer.setSharpness(i);
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            a.c("setSharpness ret = ", sharpness, TAG);
        }
        return sharpness;
    }

    public static int setSharpness20(int i) {
        int sharpness20 = -1;
        if (a.f("setSharpness20 val:", i, TAG)) {
            try {
                sharpness20 = mVideoServer.setSharpness20(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setSharpness20 ret = ", sharpness20, TAG);
        }
        return sharpness20;
    }

    public static int setSharpness80(int i) {
        int sharpness80 = -1;
        if (a.f("setSharpness80 val:", i, TAG)) {
            try {
                sharpness80 = mVideoServer.setSharpness80(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setSharpness80 ret = ", sharpness80, TAG);
        }
        return sharpness80;
    }

    public static int setSharpnessByFactory(int i) {
        int sharpnessByFactory = -1;
        if (a.f("setSharpnessByFactory val:", i, TAG)) {
            try {
                sharpnessByFactory = mVideoServer.setSharpnessByFactory(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setSharpnessByFactory ret = ", sharpnessByFactory, TAG);
        }
        return sharpnessByFactory;
    }

    public static int setSharpnessMax(int i) {
        int sharpnessMax = -1;
        if (a.f("setSharpnessMax val:", i, TAG)) {
            try {
                sharpnessMax = mVideoServer.setSharpnessMax(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setSharpnessMax ret = ", sharpnessMax, TAG);
        }
        return sharpnessMax;
    }

    public static int setSharpnessMid(int i) {
        int sharpnessMid = -1;
        if (a.f("setSharpnessMid val:", i, TAG)) {
            try {
                sharpnessMid = mVideoServer.setSharpnessMid(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setSharpnessMid ret = ", sharpnessMid, TAG);
        }
        return sharpnessMid;
    }

    public static int setSharpnessMin(int i) {
        int sharpnessMin = -1;
        if (a.f("setSharpnessMin val:", i, TAG)) {
            try {
                sharpnessMin = mVideoServer.setSharpnessMin(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setSharpnessMin ret = ", sharpnessMin, TAG);
        }
        return sharpnessMin;
    }

    public static int setTestPattern(int i) {
        int testPattern = -1;
        if (a.f("setTestPattern val:", i, TAG)) {
            try {
                testPattern = mVideoServer.setTestPattern(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setTestPattern ret = ", testPattern, TAG);
        }
        return testPattern;
    }

    public static int setTint(int i) {
        int tint = -1;
        if (a.f("setTint val:", i, TAG)) {
            try {
                tint = mVideoServer.setTint(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setTint ret = ", tint, TAG);
        }
        return tint;
    }

    public static int setUsageMode(int i) {
        int usageMode = -1;
        if (a.f("setUsageMode val:", i, TAG)) {
            try {
                usageMode = mVideoServer.setUsageMode(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setUsageMode ret = ", usageMode, TAG);
        }
        return usageMode;
    }

    public static int setVcomTestEnable(int i) {
        int vcomTestEnable = -1;
        if (a.f("setVcomTestEnable val:", i, TAG)) {
            try {
                vcomTestEnable = mVideoServer.setVcomTestEnable(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setVcomTestEnable ret = ", vcomTestEnable, TAG);
        }
        return vcomTestEnable;
    }

    public static int setWhiteBalanceGainBlue(int i) {
        int whiteBalanceGainBlue = -1;
        if (a.f("setWhiteBalanceGainBlue val:", i, TAG)) {
            try {
                whiteBalanceGainBlue = mVideoServer.setWhiteBalanceGainBlue(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setWhiteBalanceGainBlue ret = ", whiteBalanceGainBlue, TAG);
        }
        return whiteBalanceGainBlue;
    }

    public static int setWhiteBalanceGainGreen(int i) {
        int whiteBalanceGainGreen = -1;
        if (a.f("setWhiteBalanceGainGreen val:", i, TAG)) {
            try {
                whiteBalanceGainGreen = mVideoServer.setWhiteBalanceGainGreen(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setWhiteBalanceGainGreen ret = ", whiteBalanceGainGreen, TAG);
        }
        return whiteBalanceGainGreen;
    }

    public static int setWhiteBalanceGainRed(int i) {
        int whiteBalanceGainRed = -1;
        if (a.f("setWhiteBalanceGainRed val:", i, TAG)) {
            try {
                whiteBalanceGainRed = mVideoServer.setWhiteBalanceGainRed(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setWhiteBalanceGainRed ret = ", whiteBalanceGainRed, TAG);
        }
        return whiteBalanceGainRed;
    }

    public static int setWhiteBalanceOffsetBlue(int i) {
        int whiteBalanceOffsetBlue = -1;
        if (a.f("setWhiteBalanceOffsetBlue val:", i, TAG)) {
            try {
                whiteBalanceOffsetBlue = mVideoServer.setWhiteBalanceOffsetBlue(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setWhiteBalanceOffsetBlue ret = ", whiteBalanceOffsetBlue, TAG);
        }
        return whiteBalanceOffsetBlue;
    }

    public static int setWhiteBalanceOffsetGreen(int i) {
        int whiteBalanceOffsetGreen = -1;
        if (a.f("setWhiteBalanceOffsetGreen val:", i, TAG)) {
            try {
                whiteBalanceOffsetGreen = mVideoServer.setWhiteBalanceOffsetGreen(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setWhiteBalanceOffsetGreen ret = ", whiteBalanceOffsetGreen, TAG);
        }
        return whiteBalanceOffsetGreen;
    }

    public static int setWhiteBalanceOffsetRed(int i) {
        int whiteBalanceOffsetRed = -1;
        if (a.f("setWhiteBalanceOffsetRed val:", i, TAG)) {
            try {
                whiteBalanceOffsetRed = mVideoServer.setWhiteBalanceOffsetRed(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setWhiteBalanceOffsetRed ret = ", whiteBalanceOffsetRed, TAG);
        }
        return whiteBalanceOffsetRed;
    }

    public static int setWhiteBalanceiApplyToAll(int i) {
        int whiteBalanceiApplyToAll = -1;
        if (a.f("setWhiteBlanceiApplyToAll val:", i, TAG)) {
            try {
                whiteBalanceiApplyToAll = mVideoServer.setWhiteBalanceiApplyToAll(1);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setWhiteBalanceiApplyToAll ret = ", whiteBalanceiApplyToAll, TAG);
        }
        return whiteBalanceiApplyToAll;
    }

    public static int setWhiteLevel(int i) {
        int whiteLevel = -1;
        if (a.f("setWhiteLevel val:", i, TAG)) {
            try {
                whiteLevel = mVideoServer.setWhiteLevel(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setWhiteLevel ret = ", whiteLevel, TAG);
        }
        return whiteLevel;
    }

    public static int setWindowHSLRange(int i, int i2, int i3, int i4) {
        StringBuilder sbB = a.b("setWindowHSLRange, xStart: ", i, " yStart: ", i2, " xEnd: ");
        sbB.append(i3);
        sbB.append(" yEnd: ");
        sbB.append(i4);
        HisLogger.d(TAG, sbB.toString());
        int windowHSLRange = -1;
        if (getHidlService()) {
            try {
                windowHSLRange = mVideoServer.setWindowHSLRange(i, i2, i3, i4);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setWindowHSLRange ret = ", windowHSLRange, TAG);
        }
        return windowHSLRange;
    }

    public static int getWindowHSLHist(ArrayList<Integer> arrayList, ArrayList<Integer> arrayList2, ArrayList<Integer> arrayList3, ArrayList<Integer> arrayList4) {
        HisLogger.d(TAG, "getWindowHSLHist");
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getWindowHSLHist(new IHisVideoApi.getWindowHSLHistCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.217
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getWindowHSLHistCallback
                    public void onValues(int i, ArrayList<Integer> arrayList5, ArrayList<Integer> arrayList6, ArrayList<Integer> arrayList7, ArrayList<Integer> arrayList8) {
                        HisVideoApi.mReturnVal = i;
                        HisVideoApi.Hue = (ArrayList) arrayList5.clone();
                        HisVideoApi.Sat = (ArrayList) arrayList6.clone();
                        HisVideoApi.Lum = (ArrayList) arrayList7.clone();
                        HisVideoApi.Rgb = (ArrayList) arrayList8.clone();
                        StringBuilder sbA = a.a("getWindowHSLHist ret = ");
                        sbA.append(HisVideoApi.mReturnVal);
                        sbA.append("Hue size ");
                        sbA.append(HisVideoApi.Hue.size());
                        sbA.append("Sat size: ");
                        sbA.append(HisVideoApi.Sat.size());
                        sbA.append("Lum size: ");
                        sbA.append(HisVideoApi.Lum.size());
                        sbA.append("Rgb size: ");
                        sbA.append(HisVideoApi.Rgb.size());
                        HisLogger.d(HisVideoApi.TAG, sbA.toString());
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        Iterator<Integer> it = Hue.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        Iterator<Integer> it2 = Sat.iterator();
        while (it2.hasNext()) {
            arrayList2.add(it2.next());
        }
        Iterator<Integer> it3 = Lum.iterator();
        while (it3.hasNext()) {
            arrayList3.add(it3.next());
        }
        Iterator<Integer> it4 = Rgb.iterator();
        while (it4.hasNext()) {
            arrayList4.add(it4.next());
        }
        return mReturnVal;
    }
}
