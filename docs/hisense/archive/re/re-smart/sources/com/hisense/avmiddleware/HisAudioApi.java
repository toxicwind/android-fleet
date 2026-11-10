package com.hisense.avmiddleware;

import b.a.a.a.a;
import vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi;

/* JADX INFO: loaded from: classes2.dex */
public class HisAudioApi {
    public static final int HIS_BALANCE_MAX = 50;
    public static final int HIS_BALANCE_MIN = -50;
    public static final int HIS_DIGITAL_AUDIO_DELAY_MAX = 25;
    public static final int HIS_DIGITAL_AUDIO_DELAY_MIN = 0;
    public static final int HIS_GEQ_MAX = 10;
    public static final int HIS_GEQ_MIN = -10;
    public static final int HIS_LIP_SYNC_MAX = 25;
    public static final int HIS_LIP_SYNC_MIN = 0;
    public static final int HIS_VOLUME_CURVE_MAX = 255;
    public static final int HIS_VOLUME_CURVE_MIN = 0;
    public static final int HIS_VOLUME_SHIFT_MAX = 6;
    public static final int HIS_VOLUME_SHIFT_MIN = -6;
    public static final int SOUNDMODE_LATE_NIGHT = 5;
    public static final int SOUNDMODE_MUSIC = 3;
    public static final int SOUNDMODE_SPEECH = 4;
    public static final int SOUNDMODE_SPORTS = 2;
    public static final int SOUNDMODE_STANDARD = 0;
    public static final int SOUNDMODE_THEATER = 1;
    public static final String TAG = "HISMW@audio_java";
    public static IHisAudioApi mAudioServer = null;
    public static int mGetParamSubVal = -1;
    public static int mGetParamVal = -1;
    public static int mReturnVal = -1;

    public static int backupAQSetting() {
        HisLogger.d(TAG, "backupAQSetting");
        int iBackupAQSetting = -1;
        if (getHidlService()) {
            try {
                iBackupAQSetting = mAudioServer.backupAQSetting();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("backupAQSetting ret = ", iBackupAQSetting, TAG);
        }
        return iBackupAQSetting;
    }

    public static int getAGC() {
        HisLogger.d(TAG, "getAGC");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getAGC(new IHisAudioApi.getAGCCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.12
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getAGCCallback
                    public void onValues(int i, int i2) {
                        HisAudioApi.mReturnVal = i;
                        HisAudioApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getAGC ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getAVCFeature(int i) {
        a.c("getAVCFeature index:", i, TAG);
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getAVCFeature(i, new IHisAudioApi.getAVCFeatureCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.11
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getAVCFeatureCallback
                    public void onValues(int i2, int i3) {
                        HisAudioApi.mReturnVal = i2;
                        HisAudioApi.mGetParamVal = i3;
                        StringBuilder sbA = a.a("getAVCFeature ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getAmpDrcH() {
        HisLogger.d(TAG, "getAmpDrcH");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getAmpDrcH(new IHisAudioApi.getAmpDrcHCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.43
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getAmpDrcHCallback
                    public void onValues(int i, int i2) {
                        HisAudioApi.mReturnVal = i;
                        HisAudioApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getAmpDrcH ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getAmpDrcL() {
        HisLogger.d(TAG, "getAmpDrcL");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getAmpDrcL(new IHisAudioApi.getAmpDrcLCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.42
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getAmpDrcLCallback
                    public void onValues(int i, int i2) {
                        HisAudioApi.mReturnVal = i;
                        HisAudioApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getAmpDrcL ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getAmpDrcP() {
        HisLogger.d(TAG, "getAmpDrcP");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getAmpDrcP(new IHisAudioApi.getAmpDrcPCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.45
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getAmpDrcPCallback
                    public void onValues(int i, int i2) {
                        HisAudioApi.mReturnVal = i;
                        HisAudioApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getAmpDrcP ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getAmpDrcS() {
        HisLogger.d(TAG, "getAmpDrcS");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getAmpDrcS(new IHisAudioApi.getAmpDrcSCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.44
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getAmpDrcSCallback
                    public void onValues(int i, int i2) {
                        HisAudioApi.mReturnVal = i;
                        HisAudioApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getAmpDrcS ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getAmpGain() {
        HisLogger.d(TAG, "getAmpGain");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getAmpGain(new IHisAudioApi.getAmpGainCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.46
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getAmpGainCallback
                    public void onValues(int i, int i2) {
                        HisAudioApi.mReturnVal = i;
                        HisAudioApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getAmpGain ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getAmpPrescaler() {
        HisLogger.d(TAG, "getLipSync");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getAmpPrescaler(new IHisAudioApi.getAmpPrescalerCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.47
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getAmpPrescalerCallback
                    public void onValues(int i, int i2) {
                        HisAudioApi.mReturnVal = i;
                        HisAudioApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getAmpPrescaler ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getApplySoundMode() {
        HisLogger.d(TAG, "getApplySoundMode");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getApplySoundMode(new IHisAudioApi.getApplySoundModeCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.15
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getApplySoundModeCallback
                    public void onValues(int i, int i2) {
                        HisAudioApi.mReturnVal = i;
                        HisAudioApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getApplySoundMode ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getBalance() {
        HisLogger.d(TAG, "getBalance");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getBalance(new IHisAudioApi.getBalanceCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.13
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getBalanceCallback
                    public void onValues(int i, int i2) {
                        HisAudioApi.mReturnVal = i;
                        HisAudioApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getBalance ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getBalanceRangeMax() {
        return 50;
    }

    public static int getBalanceRangeMin() {
        return -50;
    }

    public static int getBassBoost() {
        HisLogger.d(TAG, "getBassBoost");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getBassBoost(new IHisAudioApi.getBassBoostCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.17
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getBassBoostCallback
                    public void onValues(int i, int i2) {
                        HisAudioApi.mReturnVal = i;
                        HisAudioApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getBassBoost ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getCodecType() {
        HisLogger.d(TAG, "getCodecType");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getCodecType(new IHisAudioApi.getCodecTypeCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.48
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getCodecTypeCallback
                    public void onValues(int i, int i2) {
                        HisAudioApi.mReturnVal = i;
                        HisAudioApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getCodecType ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getCurrAMPType() {
        HisLogger.d(TAG, "getCurrAMPType");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getCurrAMPType(new IHisAudioApi.getCurrAMPTypeCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.40
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getCurrAMPTypeCallback
                    public void onValues(int i, int i2, int i3) {
                        HisAudioApi.mReturnVal = i;
                        HisAudioApi.mGetParamVal = i2;
                        HisAudioApi.mGetParamSubVal = i3;
                        StringBuilder sbA = a.a("getCurrAMPType ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(", main value: ");
                        sbA.append(HisAudioApi.mGetParamVal);
                        sbA.append(" ,sub value:");
                        a.c(sbA, HisAudioApi.mGetParamSubVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getCurrSoundEffect() {
        HisLogger.d(TAG, "getCurrSoundEffect");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getCurrSoundEffect(new IHisAudioApi.getCurrSoundEffectCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.39
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getCurrSoundEffectCallback
                    public void onValues(int i, int i2) {
                        HisAudioApi.mReturnVal = i;
                        HisAudioApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getCurrSoundEffect ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getDBXFeature(int i) {
        a.c("getDBXFeature index:", i, TAG);
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getDBXFeature(i, new IHisAudioApi.getDBXFeatureCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.18
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getDBXFeatureCallback
                    public void onValues(int i2, int i3) {
                        HisAudioApi.mReturnVal = i2;
                        HisAudioApi.mGetParamVal = i3;
                        StringBuilder sbA = a.a("getDBXFeature ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getDTSFeature(int i) {
        a.c("getDTSFeature index:", i, TAG);
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getDTSFeature(i, new IHisAudioApi.getDTSFeatureCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.19
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getDTSFeatureCallback
                    public void onValues(int i2, int i3) {
                        HisAudioApi.mReturnVal = i2;
                        HisAudioApi.mGetParamVal = i3;
                        StringBuilder sbA = a.a("getDTSFeature ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getDolbyAudioFeature(int i) {
        a.c("getDolbyAudioFeature index:", i, TAG);
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getDolbyAudioFeature(i, new IHisAudioApi.getDolbyAudioFeatureCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.20
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getDolbyAudioFeatureCallback
                    public void onValues(int i2, int i3) {
                        HisAudioApi.mReturnVal = i2;
                        HisAudioApi.mGetParamVal = i3;
                        StringBuilder sbA = a.a("getDolbyAudioFeature ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getGEQ(int i) {
        a.c("getGEQ index =", i, TAG);
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getGEQ(i, new IHisAudioApi.getGEQCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.2
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getGEQCallback
                    public void onValues(int i2, int i3) {
                        HisAudioApi.mReturnVal = i2;
                        HisAudioApi.mGetParamVal = i3;
                        StringBuilder sbA = a.a("getGEQ ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getGEQRangeMax(int i) {
        return 10;
    }

    public static int getGEQRangeMin(int i) {
        return -10;
    }

    public static int getHeadphoneMode() {
        HisLogger.d(TAG, "getHeadphoneMode");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getHeadphoneMode(new IHisAudioApi.getHeadphoneModeCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.8
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getHeadphoneModeCallback
                    public void onValues(int i, int i2) {
                        HisAudioApi.mReturnVal = i;
                        HisAudioApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getHeadphoneMode ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getHeadphoneVol() {
        HisLogger.d(TAG, "getHeadphoneVol");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getHeadphoneVol(new IHisAudioApi.getHeadphoneVolCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.9
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getHeadphoneVolCallback
                    public void onValues(int i, int i2) {
                        HisAudioApi.mReturnVal = i;
                        HisAudioApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getHeadphoneVol ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
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
            mAudioServer = IHisAudioApi.getService();
            return mAudioServer != null;
        } catch (Exception e2) {
            HisLogger.e(TAG, "get Audio HIDL Service exception" + e2);
            return false;
        }
    }

    public static int getIsBTSupport() {
        HisLogger.d(TAG, "getIsBTSupport");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getIsBTSupport(new IHisAudioApi.getIsBTSupportCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.34
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getIsBTSupportCallback
                    public void onValues(int i, int i2) {
                        HisAudioApi.mReturnVal = i;
                        HisAudioApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getIsBTSupport ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getIsHPAMPSupport() {
        HisLogger.d(TAG, "getIsHPAMPSupport");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getIsHPAMPSupport(new IHisAudioApi.getIsHPAMPSupportCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.37
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getIsHPAMPSupportCallback
                    public void onValues(int i, int i2) {
                        HisAudioApi.mReturnVal = i;
                        HisAudioApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getIsHPAMPSupport ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getIsHPSupport() {
        HisLogger.d(TAG, "getIsHPSupport");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getIsHPSupport(new IHisAudioApi.getIsHPSupportCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.36
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getIsHPSupportCallback
                    public void onValues(int i, int i2) {
                        HisAudioApi.mReturnVal = i;
                        HisAudioApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getIsHPSupport ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getIsSubwooferSupport() {
        HisLogger.d(TAG, "getIsSubwooferSupport");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getIsSubwooferSupport(new IHisAudioApi.getIsSubwooferSupportCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.35
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getIsSubwooferSupportCallback
                    public void onValues(int i, int i2) {
                        HisAudioApi.mReturnVal = i;
                        HisAudioApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getIsSubwooferSupport ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getLineOut() {
        HisLogger.d(TAG, "getLineOut");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getLineOut(new IHisAudioApi.getLineOutCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.10
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getLineOutCallback
                    public void onValues(int i, int i2) {
                        HisAudioApi.mReturnVal = i;
                        HisAudioApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getLineOut ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getLipSync() {
        HisLogger.d(TAG, "getLipSync");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getLipSync(new IHisAudioApi.getLipSyncCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.5
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getLipSyncCallback
                    public void onValues(int i, int i2) {
                        HisAudioApi.mReturnVal = i;
                        HisAudioApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getLipSync ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getLipSyncRangeMax() {
        return 25;
    }

    public static int getLipSyncRangeMin() {
        return 0;
    }

    public static int getMultiBalance() {
        HisLogger.d(TAG, "getMultiBalance ");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getMultiBalance(new IHisAudioApi.getMultiBalanceCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.51
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getMultiBalanceCallback
                    public void onValues(int i, int i2) {
                        HisAudioApi.mReturnVal = i;
                        HisAudioApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getMultiBalance ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getMultiDigitalAMPValueByDevId(int i, int i2) {
        HisLogger.d(TAG, "getMultiDigitalAMPValueByDevId i4DevId " + i + "i4Index " + i2);
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getMultiDigitalAMPValueByDevId(i, i2, new IHisAudioApi.getMultiDigitalAMPValueByDevIdCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.50
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getMultiDigitalAMPValueByDevIdCallback
                    public void onValues(int i3, int i4) {
                        HisAudioApi.mReturnVal = i3;
                        HisAudioApi.mGetParamVal = i4;
                        StringBuilder sbA = a.a("getMultiDigitalAMPValueByDevId ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getSPDefaultVol() {
        HisLogger.d(TAG, "getIsBTSupport");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getSPDefaultVol(new IHisAudioApi.getSPDefaultVolCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.38
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSPDefaultVolCallback
                    public void onValues(int i, int i2) {
                        HisAudioApi.mReturnVal = i;
                        HisAudioApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getSPDefaultVol ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getSoundCommonInf(int i, int i2, int i3) {
        a.c(a.b("getSoundCommonInf feature:", i, ", index:", i2, ", val:"), i3, TAG);
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getSoundCommonInf(i, i2, i3, new IHisAudioApi.getSoundCommonInfCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.41
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSoundCommonInfCallback
                    public void onValues(int i4, int i5) {
                        HisAudioApi.mReturnVal = i4;
                        HisAudioApi.mGetParamVal = i5;
                        StringBuilder sbA = a.a("getSoundCommonInf ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getSoundEffectFeature1(int i) {
        a.c("getSoundEffectFeature1 index:", i, TAG);
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getSoundEffectFeature1(i, new IHisAudioApi.getSoundEffectFeature1Callback() { // from class: com.hisense.avmiddleware.HisAudioApi.21
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSoundEffectFeature1Callback
                    public void onValues(int i2, int i3) {
                        HisAudioApi.mReturnVal = i2;
                        HisAudioApi.mGetParamVal = i3;
                        StringBuilder sbA = a.a("getSoundEffectFeature1 ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getSoundEffectFeature2(int i) {
        a.c("getSoundEffectFeature2 index:", i, TAG);
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getSoundEffectFeature2(i, new IHisAudioApi.getSoundEffectFeature2Callback() { // from class: com.hisense.avmiddleware.HisAudioApi.22
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSoundEffectFeature2Callback
                    public void onValues(int i2, int i3) {
                        HisAudioApi.mReturnVal = i2;
                        HisAudioApi.mGetParamVal = i3;
                        StringBuilder sbA = a.a("getSoundEffectFeature2 ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getSoundEffectFeature3(int i) {
        a.c("getSoundEffectFeature3 index:", i, TAG);
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getSoundEffectFeature3(i, new IHisAudioApi.getSoundEffectFeature3Callback() { // from class: com.hisense.avmiddleware.HisAudioApi.23
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSoundEffectFeature3Callback
                    public void onValues(int i2, int i3) {
                        HisAudioApi.mReturnVal = i2;
                        HisAudioApi.mGetParamVal = i3;
                        StringBuilder sbA = a.a("getSoundEffectFeature3 ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getSoundMainAMPValue(int i) {
        a.c("getSoundMainAMPValue index:", i, TAG);
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getSoundMainAMPValue(i, new IHisAudioApi.getSoundMainAMPValueCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.26
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSoundMainAMPValueCallback
                    public void onValues(int i2, int i3) {
                        HisAudioApi.mReturnVal = i2;
                        HisAudioApi.mGetParamVal = i3;
                        StringBuilder sbA = a.a("getSoundMainAMPValue ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getSoundMode() {
        HisLogger.d(TAG, "getSoundMode");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getSoundMode(new IHisAudioApi.getSoundModeCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.1
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSoundModeCallback
                    public void onValues(int i, int i2) {
                        HisAudioApi.mReturnVal = i;
                        HisAudioApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getSoundMode ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",soundMode = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getSoundSocDRCFeature(int i) {
        a.c("getSoundSocDRCFeature index =", i, TAG);
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getSoundSocDRCFeature(i, new IHisAudioApi.getSoundSocDRCFeatureCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.28
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSoundSocDRCFeatureCallback
                    public void onValues(int i2, int i3) {
                        HisAudioApi.mReturnVal = i2;
                        HisAudioApi.mGetParamVal = i3;
                        StringBuilder sbA = a.a("getSoundSocDRCFeature ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getSoundSocHPFFeature(int i) {
        a.c("getSoundSocHPFFeature index =", i, TAG);
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getSoundSocHPFFeature(i, new IHisAudioApi.getSoundSocHPFFeatureCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.29
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSoundSocHPFFeatureCallback
                    public void onValues(int i2, int i3) {
                        HisAudioApi.mReturnVal = i2;
                        HisAudioApi.mGetParamVal = i3;
                        StringBuilder sbA = a.a("getSoundSocHPFFeature ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getSoundSocPEQEnable() {
        HisLogger.d(TAG, "getSoundSocPEQEnable");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getSoundSocPEQEnable(new IHisAudioApi.getSoundSocPEQEnableCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.33
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSoundSocPEQEnableCallback
                    public void onValues(int i, int i2) {
                        HisAudioApi.mReturnVal = i;
                        HisAudioApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getSoundSocPEQEnable ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getSoundSocPEQFreq(int i) {
        a.c("getSoundSocPEQFreq index:", i, TAG);
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getSoundSocPEQFreq(i, new IHisAudioApi.getSoundSocPEQFreqCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.30
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSoundSocPEQFreqCallback
                    public void onValues(int i2, int i3) {
                        HisAudioApi.mReturnVal = i2;
                        HisAudioApi.mGetParamVal = i3;
                        StringBuilder sbA = a.a("getSoundSocPEQFreq ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getSoundSocPEQGain(int i) {
        a.c("getSoundSocPEQGain index:", i, TAG);
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getSoundSocPEQGain(i, new IHisAudioApi.getSoundSocPEQGainCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.31
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSoundSocPEQGainCallback
                    public void onValues(int i2, int i3) {
                        HisAudioApi.mReturnVal = i2;
                        HisAudioApi.mGetParamVal = i3;
                        StringBuilder sbA = a.a("getSoundSocPEQGain ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getSoundSocPEQQ(int i) {
        a.c("getSoundSocPEQQ index:", i, TAG);
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getSoundSocPEQQ(i, new IHisAudioApi.getSoundSocPEQQCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.32
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSoundSocPEQQCallback
                    public void onValues(int i2, int i3) {
                        HisAudioApi.mReturnVal = i2;
                        HisAudioApi.mGetParamVal = i3;
                        StringBuilder sbA = a.a("getSoundSocPEQQ ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getSoundSocPrescale() {
        HisLogger.d(TAG, "getSoundMode");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getSoundSocPrescale(new IHisAudioApi.getSoundSocPrescaleCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.25
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSoundSocPrescaleCallback
                    public void onValues(int i, int i2) {
                        HisAudioApi.mReturnVal = i;
                        HisAudioApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getSoundSocPrescale ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getSoundSubAMPValue(int i) {
        a.c("getSoundSubAMPValue index:", i, TAG);
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getSoundSubAMPValue(i, new IHisAudioApi.getSoundSubAMPValueCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.27
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSoundSubAMPValueCallback
                    public void onValues(int i2, int i3) {
                        HisAudioApi.mReturnVal = i2;
                        HisAudioApi.mGetParamVal = i3;
                        StringBuilder sbA = a.a("getSoundSubAMPValue ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getSoundVolCurveMax() {
        return 255;
    }

    public static int getSoundVolCurveMin() {
        return 0;
    }

    public static int getSoundVolCurveVal(int i) {
        a.c("getSoundVolCurveVal index:", i, TAG);
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getSoundVolCurveVal(i, new IHisAudioApi.getSoundVolCurveValCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.24
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSoundVolCurveValCallback
                    public void onValues(int i2, int i3) {
                        HisAudioApi.mReturnVal = i2;
                        HisAudioApi.mGetParamVal = i3;
                        StringBuilder sbA = a.a("getSoundVolCurveVal ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getSpdifDelay() {
        HisLogger.d(TAG, "getSpdifDelay");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getSpdifDelay(new IHisAudioApi.getSpdifDelayCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.7
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSpdifDelayCallback
                    public void onValues(int i, int i2) {
                        HisAudioApi.mReturnVal = i;
                        HisAudioApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getWallMode ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getSpdifDelayRangeMax() {
        return 25;
    }

    public static int getSpdifDelayRangeMin() {
        return 0;
    }

    public static int getSpdifType() {
        HisLogger.d(TAG, "getSpdifType");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getSpdifType(new IHisAudioApi.getSpdifTypeCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.6
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSpdifTypeCallback
                    public void onValues(int i, int i2) {
                        HisAudioApi.mReturnVal = i;
                        HisAudioApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getSpdifType ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getSpeakerDevices() {
        HisLogger.d(TAG, "getSpeakerDevices");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getSpeakerDevices(new IHisAudioApi.getSpeakerDevicesCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.4
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSpeakerDevicesCallback
                    public void onValues(int i, int i2) {
                        HisAudioApi.mReturnVal = i;
                        HisAudioApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getSpeakerDevices ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getSpeakerEnable() {
        HisLogger.d(TAG, "getSpeakerEnable");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getSpeakerEnable(new IHisAudioApi.getSpeakerEnableCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.49
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSpeakerEnableCallback
                    public void onValues(int i, int i2) {
                        HisAudioApi.mReturnVal = i;
                        HisAudioApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getSpeakerEnable ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getSubwoofer() {
        HisLogger.d(TAG, "getSubwoofer");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getSubwoofer(new IHisAudioApi.getSubwooferCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.16
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSubwooferCallback
                    public void onValues(int i, int i2) {
                        HisAudioApi.mReturnVal = i;
                        HisAudioApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getSubwoofer ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getVolumeShift() {
        HisLogger.d(TAG, "getVolumeShift");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getVolumeShift(new IHisAudioApi.getVolumeShiftCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.14
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getVolumeShiftCallback
                    public void onValues(int i, int i2) {
                        HisAudioApi.mReturnVal = i;
                        HisAudioApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getVolumeShift ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getVolumeShiftRangeMax() {
        return 6;
    }

    public static int getVolumeShiftRangeMin() {
        return -6;
    }

    public static int getWallMode() {
        HisLogger.d(TAG, "getWallMode");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getWallMode(new IHisAudioApi.getWallModeCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.3
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getWallModeCallback
                    public void onValues(int i, int i2) {
                        HisAudioApi.mReturnVal = i;
                        HisAudioApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getWallMode ret =");
                        sbA.append(HisAudioApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisAudioApi.mGetParamVal, HisAudioApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int resetEQ() {
        HisLogger.d(TAG, "resetEQ");
        int iResetEQ = -1;
        if (getHidlService()) {
            try {
                iResetEQ = mAudioServer.ResetEQ();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("resetEQ ret = ", iResetEQ, TAG);
        }
        return iResetEQ;
    }

    public static int restoreAQSetting() {
        HisLogger.d(TAG, "restoreAQSetting");
        int iRestoreAQSetting = -1;
        if (getHidlService()) {
            try {
                iRestoreAQSetting = mAudioServer.restoreAQSetting();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("restoreAQSetting ret = ", iRestoreAQSetting, TAG);
        }
        return iRestoreAQSetting;
    }

    public static int restoreSound() {
        HisLogger.d(TAG, "RestoreSound");
        int iRestoreSound = -1;
        if (getHidlService()) {
            try {
                iRestoreSound = mAudioServer.RestoreSound();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("RestoreSound ret = ", iRestoreSound, TAG);
        }
        return iRestoreSound;
    }

    public static int setACRSwitch(int i) {
        int aCRSwitch = -1;
        if (a.e("setACRSwitch val:", i, TAG)) {
            try {
                aCRSwitch = mAudioServer.setACRSwitch(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setACRSwitch ret = ", aCRSwitch, TAG);
        }
        return aCRSwitch;
    }

    public static int setAGC(int i) {
        int agc = -1;
        if (a.e("setAGC val:", i, TAG)) {
            try {
                agc = mAudioServer.setAGC(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setAGC ret = ", agc, TAG);
        }
        return agc;
    }

    public static int setAVCFeature(int i, int i2) {
        HisLogger.d(TAG, "setAVCFeature index:" + i + ",val:" + i2);
        int aVCFeature = -1;
        if (getHidlService()) {
            try {
                aVCFeature = mAudioServer.setAVCFeature(i, i2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setAVCFeature ret = ", aVCFeature, TAG);
        }
        return aVCFeature;
    }

    public static int setAmpDrcH(int i) {
        int ampDrcH = -1;
        if (a.e("setAmpDrcH val:", i, TAG)) {
            try {
                ampDrcH = mAudioServer.setAmpDrcH(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setAmpDrcH ret = ", ampDrcH, TAG);
        }
        return ampDrcH;
    }

    public static int setAmpDrcL(int i) {
        int ampDrcL = -1;
        if (a.e("setAmpDrcL val:", i, TAG)) {
            try {
                ampDrcL = mAudioServer.setAmpDrcL(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setAmpDrcL ret = ", ampDrcL, TAG);
        }
        return ampDrcL;
    }

    public static int setAmpDrcP(int i) {
        int ampDrcP = -1;
        if (a.e("setAmpDrcP val:", i, TAG)) {
            try {
                ampDrcP = mAudioServer.setAmpDrcP(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setAmpDrcP ret = ", ampDrcP, TAG);
        }
        return ampDrcP;
    }

    public static int setAmpDrcS(int i) {
        int ampDrcS = -1;
        if (a.e("setAmpDrcS val:", i, TAG)) {
            try {
                ampDrcS = mAudioServer.setAmpDrcS(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setAmpDrcS ret = ", ampDrcS, TAG);
        }
        return ampDrcS;
    }

    public static int setAmpGain(int i) {
        int ampGain = -1;
        if (a.e("setAmpGain val:", i, TAG)) {
            try {
                ampGain = mAudioServer.setAmpGain(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setAmpGain ret = ", ampGain, TAG);
        }
        return ampGain;
    }

    public static int setAmpPrescaler(int i) {
        int ampPrescaler = -1;
        if (a.e("setLipSync val:", i, TAG)) {
            try {
                ampPrescaler = mAudioServer.setAmpPrescaler(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setAmpPrescaler ret = ", ampPrescaler, TAG);
        }
        return ampPrescaler;
    }

    public static int setApplySoundMode(int i) {
        int applySoundMode = -1;
        if (a.e("setApplySoundMode val:", i, TAG)) {
            try {
                applySoundMode = mAudioServer.setApplySoundMode(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setApplySoundMode ret = ", applySoundMode, TAG);
        }
        return applySoundMode;
    }

    public static int setBalance(int i) {
        int balance = -1;
        if (a.e("setBalance val:", i, TAG)) {
            try {
                balance = mAudioServer.setBalance(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setBalance ret = ", balance, TAG);
        }
        return balance;
    }

    public static int setBassBoost(int i) {
        int bassBoost = -1;
        if (a.e("setBassBoost val:", i, TAG)) {
            try {
                bassBoost = mAudioServer.setBassBoost(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setBassBoost ret = ", bassBoost, TAG);
        }
        return bassBoost;
    }

    public static int setDBXFeature(int i, int i2) {
        HisLogger.d(TAG, "setDBXFeature index:" + i + "val:" + i2);
        int dBXFeature = -1;
        if (getHidlService()) {
            try {
                dBXFeature = mAudioServer.setDBXFeature(i, i2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setDBXFeature ret = ", dBXFeature, TAG);
        }
        return dBXFeature;
    }

    public static int setDTSFeature(int i, int i2) {
        HisLogger.d(TAG, "setDTSFeature index:" + i + "val:" + i2);
        int dTSFeature = -1;
        if (getHidlService()) {
            try {
                dTSFeature = mAudioServer.setDTSFeature(i, i2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setDTSFeature ret = ", dTSFeature, TAG);
        }
        return dTSFeature;
    }

    public static int setDolbyAudioFeature(int i, int i2) {
        HisLogger.d(TAG, "setDolbyAudioFeature index:" + i + "val:" + i2);
        int dolbyAudioFeature = -1;
        if (getHidlService()) {
            try {
                dolbyAudioFeature = mAudioServer.setDolbyAudioFeature(i, i2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setDolbyAudioFeature ret = ", dolbyAudioFeature, TAG);
        }
        return dolbyAudioFeature;
    }

    public static int setGEQ(int i, int i2) {
        HisLogger.d(TAG, "setGEQ index:" + i + " val:" + i2);
        int geq = -1;
        if (getHidlService()) {
            try {
                geq = mAudioServer.setGEQ(i, i2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setGEQ ret = ", geq, TAG);
        }
        return geq;
    }

    public static int setHeadphoneMode(int i) {
        int headphoneMode = -1;
        if (a.e("setHeadphoneMode val:", i, TAG)) {
            try {
                headphoneMode = mAudioServer.setHeadphoneMode(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setHeadphoneMode ret = ", headphoneMode, TAG);
        }
        return headphoneMode;
    }

    public static int setHeadphoneVol(int i) {
        int headphoneVol = -1;
        if (a.e("setHeadphoneVol val:", i, TAG)) {
            try {
                headphoneVol = mAudioServer.setHeadphoneVol(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setHeadphoneVol ret = ", headphoneVol, TAG);
        }
        return headphoneVol;
    }

    public static int setIsFacMode(int i) {
        int isFacMode = -1;
        if (a.e("setIsFacMode val:", i, TAG)) {
            try {
                isFacMode = mAudioServer.setIsFacMode(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setIsFacMode ret = ", isFacMode, TAG);
        }
        return isFacMode;
    }

    public static int setLineOut(int i) {
        int lineOut = -1;
        if (a.e("setLineOut val:", i, TAG)) {
            try {
                lineOut = mAudioServer.setLineOut(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setLineOut ret = ", lineOut, TAG);
        }
        return lineOut;
    }

    public static int setLipSync(int i) {
        int lipSync = -1;
        if (a.e("setLipSync val:", i, TAG)) {
            try {
                lipSync = mAudioServer.setLipSync(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setLipSync ret = ", lipSync, TAG);
        }
        return lipSync;
    }

    public static int setMultiBalance(int i) {
        int multiBalance = -1;
        if (a.e("setMultiBalance i4InVal:", i, TAG)) {
            try {
                multiBalance = mAudioServer.setMultiBalance(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setMultiBalance ret = ", multiBalance, TAG);
        }
        return multiBalance;
    }

    public static int setMultiDigitalAMPValueByDevId(int i, int i2, int i3) {
        StringBuilder sbB = a.b("setMultiDigitalAMPValueByDevId i4DevId:", i, " i4Index", i2, " i4InVal");
        sbB.append(i3);
        HisLogger.d(TAG, sbB.toString());
        int multiDigitalAMPValueByDevId = -1;
        if (getHidlService()) {
            try {
                multiDigitalAMPValueByDevId = mAudioServer.setMultiDigitalAMPValueByDevId(i, i2, i3);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setMultiDigitalAMPValueByDevId ret = ", multiDigitalAMPValueByDevId, TAG);
        }
        return multiDigitalAMPValueByDevId;
    }

    public static int setSoundCommonInf(int i, int i2, int i3) {
        StringBuilder sbB = a.b("setSoundCommonInf feature:", i, ", index:", i2, ", val:");
        sbB.append(i3);
        HisLogger.d(TAG, sbB.toString());
        int soundCommonInf = -1;
        if (getHidlService()) {
            try {
                soundCommonInf = mAudioServer.setSoundCommonInf(i, i2, i3);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setSoundCommonInf ret = ", soundCommonInf, TAG);
        }
        return soundCommonInf;
    }

    public static int setSoundEffectFeature1(int i, int i2) {
        HisLogger.d(TAG, "setSoundEffectFeature1 index:" + i + "val:" + i2);
        int soundEffectFeature1 = -1;
        if (getHidlService()) {
            try {
                soundEffectFeature1 = mAudioServer.setSoundEffectFeature1(i, i2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setSoundEffectFeature1 ret = ", soundEffectFeature1, TAG);
        }
        return soundEffectFeature1;
    }

    public static int setSoundEffectFeature2(int i, int i2) {
        HisLogger.d(TAG, "setSoundEffectFeature2 index:" + i + "val:" + i2);
        int soundEffectFeature2 = -1;
        if (getHidlService()) {
            try {
                soundEffectFeature2 = mAudioServer.setSoundEffectFeature2(i, i2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setSoundEffectFeature2 ret = ", soundEffectFeature2, TAG);
        }
        return soundEffectFeature2;
    }

    public static int setSoundEffectFeature3(int i, int i2) {
        HisLogger.d(TAG, "setSoundEffectFeature3 index:" + i + "val:" + i2);
        int soundEffectFeature3 = -1;
        if (getHidlService()) {
            try {
                soundEffectFeature3 = mAudioServer.setSoundEffectFeature3(i, i2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setSoundEffectFeature3 ret = ", soundEffectFeature3, TAG);
        }
        return soundEffectFeature3;
    }

    public static int setSoundMainAMPValue(int i, int i2) {
        HisLogger.d(TAG, "setSoundMainAMPValue index:" + i + "val:" + i2);
        int soundMainAMPValue = -1;
        if (getHidlService()) {
            try {
                soundMainAMPValue = mAudioServer.setSoundMainAMPValue(i, i2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setSoundMainAMPValue ret = ", soundMainAMPValue, TAG);
        }
        return soundMainAMPValue;
    }

    public static int setSoundMode(int i) {
        int soundMode = -1;
        if (a.e("setSoundMode soundMode:", i, TAG)) {
            try {
                soundMode = mAudioServer.setSoundMode(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setSoundMode ret = ", soundMode, TAG);
        }
        return soundMode;
    }

    public static int setSoundSocDRCFeature(int i, int i2) {
        HisLogger.d(TAG, "setSoundSocDRCFeature index:" + i + " val:" + i2);
        int soundSocDRCFeature = -1;
        if (getHidlService()) {
            try {
                soundSocDRCFeature = mAudioServer.setSoundSocDRCFeature(i, i2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setSoundSocDRCFeature ret = ", soundSocDRCFeature, TAG);
        }
        return soundSocDRCFeature;
    }

    public static int setSoundSocHPFFeature(int i, int i2) {
        HisLogger.d(TAG, "setSoundSocHPFFeature index:" + i + " val:" + i2);
        int soundSocHPFFeature = -1;
        if (getHidlService()) {
            try {
                soundSocHPFFeature = mAudioServer.setSoundSocHPFFeature(i, i2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setSoundSocHPFFeature ret = ", soundSocHPFFeature, TAG);
        }
        return soundSocHPFFeature;
    }

    public static int setSoundSocPEQEnable(int i) {
        int soundSocPEQEnable = -1;
        if (a.e("setSoundSocPEQEnable val:", i, TAG)) {
            try {
                soundSocPEQEnable = mAudioServer.setSoundSocPEQEnable(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setSoundSocPEQEnable ret = ", soundSocPEQEnable, TAG);
        }
        return soundSocPEQEnable;
    }

    public static int setSoundSocPEQFreq(int i, int i2) {
        HisLogger.d(TAG, "setSoundSocPEQFreq index:" + i + "val:" + i2);
        int soundSocPEQFreq = -1;
        if (getHidlService()) {
            try {
                soundSocPEQFreq = mAudioServer.setSoundSocPEQFreq(i, i2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setSoundSocPEQFreq ret = ", soundSocPEQFreq, TAG);
        }
        return soundSocPEQFreq;
    }

    public static int setSoundSocPEQGain(int i, int i2) {
        HisLogger.d(TAG, "setSoundSocPEQGain index:" + i + "val:" + i2);
        int soundSocPEQGain = -1;
        if (getHidlService()) {
            try {
                soundSocPEQGain = mAudioServer.setSoundSocPEQGain(i, i2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setSoundSocPEQGain ret = ", soundSocPEQGain, TAG);
        }
        return soundSocPEQGain;
    }

    public static int setSoundSocPEQQ(int i, int i2) {
        HisLogger.d(TAG, "setSoundSocPEQQ index:" + i + "val:" + i2);
        int soundSocPEQQ = -1;
        if (getHidlService()) {
            try {
                soundSocPEQQ = mAudioServer.setSoundSocPEQQ(i, i2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setSoundSocPEQQ ret = ", soundSocPEQQ, TAG);
        }
        return soundSocPEQQ;
    }

    public static int setSoundSocPrescale(int i) {
        int soundSocPrescale = -1;
        if (a.e("setSoundSocPrescale val:", i, TAG)) {
            try {
                soundSocPrescale = mAudioServer.setSoundSocPrescale(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setSoundSocPrescale ret = ", soundSocPrescale, TAG);
        }
        return soundSocPrescale;
    }

    public static int setSoundSubAMPValue(int i, int i2) {
        HisLogger.d(TAG, "setSoundSubAMPValue index:" + i + "val:" + i2);
        int soundSubAMPValue = -1;
        if (getHidlService()) {
            try {
                soundSubAMPValue = mAudioServer.setSoundSubAMPValue(i, i2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setSoundSubAMPValue ret = ", soundSubAMPValue, TAG);
        }
        return soundSubAMPValue;
    }

    public static int setSoundVolCurveVal(int i, int i2) {
        HisLogger.d(TAG, "setSoundVolCurveVal index:" + i + "val:" + i2);
        int soundVolCurveVal = -1;
        if (getHidlService()) {
            try {
                soundVolCurveVal = mAudioServer.setSoundVolCurveVal(i, i2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setSoundVolCurveVal ret = ", soundVolCurveVal, TAG);
        }
        return soundVolCurveVal;
    }

    public static int setSpdifDelay(int i) {
        int spdifDelay = -1;
        if (a.e("setSpdifDelay val:", i, TAG)) {
            try {
                spdifDelay = mAudioServer.setSpdifDelay(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setSpdifDelay ret = ", spdifDelay, TAG);
        }
        return spdifDelay;
    }

    public static int setSpdifType(int i) {
        int spdifType = -1;
        if (a.e("setSpdifType val:", i, TAG)) {
            try {
                spdifType = mAudioServer.setSpdifType(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setSpdifType ret = ", spdifType, TAG);
        }
        return spdifType;
    }

    public static int setSpeakerDevices(int i) {
        int speakerDevices = -1;
        if (a.e("setSpeakerDevices val:", i, TAG)) {
            try {
                speakerDevices = mAudioServer.setSpeakerDevices(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setSpeakerDevices ret = ", speakerDevices, TAG);
        }
        return speakerDevices;
    }

    public static int setSpeakerEnable(int i) {
        int speakerEnable = -1;
        if (a.e("setSpeakerEnable val:", i, TAG)) {
            try {
                speakerEnable = mAudioServer.setSpeakerEnable(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setSpeakerEnable ret = ", speakerEnable, TAG);
        }
        return speakerEnable;
    }

    public static int setStrResume(int i) {
        int strResume = -1;
        if (a.e("setStrResume val:", i, TAG)) {
            try {
                strResume = mAudioServer.setStrResume(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setStrResume ret = ", strResume, TAG);
        }
        return strResume;
    }

    public static int setStrSusspend(int i) {
        int strSusspend = -1;
        if (a.e("setStrSusspend val:", i, TAG)) {
            try {
                strSusspend = mAudioServer.setStrSusspend(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setStrSusspend ret = ", strSusspend, TAG);
        }
        return strSusspend;
    }

    public static int setSubwoofer(int i) {
        int subwoofer = -1;
        if (a.e("setSubwoofer val:", i, TAG)) {
            try {
                subwoofer = mAudioServer.setSubwoofer(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setSubwoofer ret = ", subwoofer, TAG);
        }
        return subwoofer;
    }

    public static int setVolumeShift(int i) {
        int volumeShift = -1;
        if (a.e("setVolumeShift val:", i, TAG)) {
            try {
                volumeShift = mAudioServer.setVolumeShift(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setVolumeShift ret = ", volumeShift, TAG);
        }
        return volumeShift;
    }

    public static int setWallMode(int i) {
        int wallMode = -1;
        if (a.e("setWallMode val:", i, TAG)) {
            try {
                wallMode = mAudioServer.setWallMode(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setWallMode ret = ", wallMode, TAG);
        }
        return wallMode;
    }
}
