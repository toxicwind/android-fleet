package com.hisense.avmiddleware;

import vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi;

/* JADX INFO: loaded from: classes.dex */
public class HisAudioApi {
    private static IHisAudioApi mAudioServer = null;
    private static int mGetParamVal = -1;
    private static int mReturnVal = -1;

    public static int getSoundVolCurveMax() {
        return 255;
    }

    public static int getSoundVolCurveMin() {
        return 0;
    }

    public static boolean getHidlService() {
        try {
            IHisAudioApi service = IHisAudioApi.getService();
            mAudioServer = service;
            return service != null;
        } catch (Exception e) {
            HisLogger.e("HISMW@audio_java", "get Audio HIDL Service exception" + e);
            return false;
        }
    }

    public static int setSoundMode(int i) {
        HisLogger.d("HISMW@audio_java", "setSoundMode soundMode:" + i);
        int soundMode = -1;
        if (getHidlService()) {
            try {
                soundMode = mAudioServer.setSoundMode(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@audio_java", "setSoundMode ret = " + soundMode);
        }
        return soundMode;
    }

    public static int getSoundMode() {
        HisLogger.d("HISMW@audio_java", "getSoundMode");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getSoundMode(new IHisAudioApi.getSoundModeCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.1
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSoundModeCallback
                    public void onValues(int i, int i2) {
                        int unused = HisAudioApi.mReturnVal = i;
                        int unused2 = HisAudioApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@audio_java", "getSoundMode ret =" + HisAudioApi.mReturnVal + ",soundMode = " + HisAudioApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setGEQ(int i, int i2) {
        HisLogger.d("HISMW@audio_java", "setGEQ index:" + i + " val:" + i2);
        int geq = -1;
        if (getHidlService()) {
            try {
                geq = mAudioServer.setGEQ(i, i2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@audio_java", "setGEQ ret = " + geq);
        }
        return geq;
    }

    public static int getGEQ(int i) {
        HisLogger.d("HISMW@audio_java", "getGEQ index =" + i);
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getGEQ(i, new IHisAudioApi.getGEQCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.2
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getGEQCallback
                    public void onValues(int i2, int i3) {
                        int unused = HisAudioApi.mReturnVal = i2;
                        int unused2 = HisAudioApi.mGetParamVal = i3;
                        HisLogger.d("HISMW@audio_java", "getGEQ ret =" + HisAudioApi.mReturnVal + ",value = " + HisAudioApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setAVCFeature(int i, int i2) {
        HisLogger.d("HISMW@audio_java", "setAVCFeature index:" + i + ",val:" + i2);
        int aVCFeature = -1;
        if (getHidlService()) {
            try {
                aVCFeature = mAudioServer.setAVCFeature(i, i2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@audio_java", "setAVCFeature ret = " + aVCFeature);
        }
        return aVCFeature;
    }

    public static int getAVCFeature(int i) {
        HisLogger.d("HISMW@audio_java", "getAVCFeature index:" + i);
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getAVCFeature(i, new IHisAudioApi.getAVCFeatureCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.11
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getAVCFeatureCallback
                    public void onValues(int i2, int i3) {
                        int unused = HisAudioApi.mReturnVal = i2;
                        int unused2 = HisAudioApi.mGetParamVal = i3;
                        HisLogger.d("HISMW@audio_java", "getAVCFeature ret =" + HisAudioApi.mReturnVal + ",value = " + HisAudioApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setBalance(int i) {
        HisLogger.d("HISMW@audio_java", "setBalance val:" + i);
        int balance = -1;
        if (getHidlService()) {
            try {
                balance = mAudioServer.setBalance(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@audio_java", "setBalance ret = " + balance);
        }
        return balance;
    }

    public static int getBalance() {
        HisLogger.d("HISMW@audio_java", "getBalance");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getBalance(new IHisAudioApi.getBalanceCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.13
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getBalanceCallback
                    public void onValues(int i, int i2) {
                        int unused = HisAudioApi.mReturnVal = i;
                        int unused2 = HisAudioApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@audio_java", "getBalance ret =" + HisAudioApi.mReturnVal + ",value = " + HisAudioApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setSoundVolCurveVal(int i, int i2) {
        HisLogger.d("HISMW@audio_java", "setSoundVolCurveVal index:" + i + "val:" + i2);
        int soundVolCurveVal = -1;
        if (getHidlService()) {
            try {
                soundVolCurveVal = mAudioServer.setSoundVolCurveVal(i, i2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@audio_java", "setSoundVolCurveVal ret = " + soundVolCurveVal);
        }
        return soundVolCurveVal;
    }

    public static int getSoundVolCurveVal(int i) {
        HisLogger.d("HISMW@audio_java", "getSoundVolCurveVal index:" + i);
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getSoundVolCurveVal(i, new IHisAudioApi.getSoundVolCurveValCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.24
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSoundVolCurveValCallback
                    public void onValues(int i2, int i3) {
                        int unused = HisAudioApi.mReturnVal = i2;
                        int unused2 = HisAudioApi.mGetParamVal = i3;
                        HisLogger.d("HISMW@audio_java", "getSoundVolCurveVal ret =" + HisAudioApi.mReturnVal + ",value = " + HisAudioApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setSoundSocPrescale(int i) {
        HisLogger.d("HISMW@audio_java", "setSoundSocPrescale val:" + i);
        int soundSocPrescale = -1;
        if (getHidlService()) {
            try {
                soundSocPrescale = mAudioServer.setSoundSocPrescale(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@audio_java", "setSoundSocPrescale ret = " + soundSocPrescale);
        }
        return soundSocPrescale;
    }

    public static int getSoundSocPrescale() {
        HisLogger.d("HISMW@audio_java", "getSoundMode");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getSoundSocPrescale(new IHisAudioApi.getSoundSocPrescaleCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.25
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSoundSocPrescaleCallback
                    public void onValues(int i, int i2) {
                        int unused = HisAudioApi.mReturnVal = i;
                        int unused2 = HisAudioApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@audio_java", "getSoundSocPrescale ret =" + HisAudioApi.mReturnVal + ",value = " + HisAudioApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setSoundSocDRCFeature(int i, int i2) {
        HisLogger.d("HISMW@audio_java", "setSoundSocDRCFeature index:" + i + " val:" + i2);
        int soundSocDRCFeature = -1;
        if (getHidlService()) {
            try {
                soundSocDRCFeature = mAudioServer.setSoundSocDRCFeature(i, i2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@audio_java", "setSoundSocDRCFeature ret = " + soundSocDRCFeature);
        }
        return soundSocDRCFeature;
    }

    public static int getSoundSocDRCFeature(int i) {
        HisLogger.d("HISMW@audio_java", "getSoundSocDRCFeature index =" + i);
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getSoundSocDRCFeature(i, new IHisAudioApi.getSoundSocDRCFeatureCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.28
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSoundSocDRCFeatureCallback
                    public void onValues(int i2, int i3) {
                        int unused = HisAudioApi.mReturnVal = i2;
                        int unused2 = HisAudioApi.mGetParamVal = i3;
                        HisLogger.d("HISMW@audio_java", "getSoundSocDRCFeature ret =" + HisAudioApi.mReturnVal + ",value = " + HisAudioApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setSoundSocHPFFeature(int i, int i2) {
        HisLogger.d("HISMW@audio_java", "setSoundSocHPFFeature index:" + i + " val:" + i2);
        int soundSocHPFFeature = -1;
        if (getHidlService()) {
            try {
                soundSocHPFFeature = mAudioServer.setSoundSocHPFFeature(i, i2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@audio_java", "setSoundSocHPFFeature ret = " + soundSocHPFFeature);
        }
        return soundSocHPFFeature;
    }

    public static int getSoundSocHPFFeature(int i) {
        HisLogger.d("HISMW@audio_java", "getSoundSocHPFFeature index =" + i);
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getSoundSocHPFFeature(i, new IHisAudioApi.getSoundSocHPFFeatureCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.29
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSoundSocHPFFeatureCallback
                    public void onValues(int i2, int i3) {
                        int unused = HisAudioApi.mReturnVal = i2;
                        int unused2 = HisAudioApi.mGetParamVal = i3;
                        HisLogger.d("HISMW@audio_java", "getSoundSocHPFFeature ret =" + HisAudioApi.mReturnVal + ",value = " + HisAudioApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setSoundSocPEQFreq(int i, int i2) {
        HisLogger.d("HISMW@audio_java", "setSoundSocPEQFreq index:" + i + "val:" + i2);
        int soundSocPEQFreq = -1;
        if (getHidlService()) {
            try {
                soundSocPEQFreq = mAudioServer.setSoundSocPEQFreq(i, i2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@audio_java", "setSoundSocPEQFreq ret = " + soundSocPEQFreq);
        }
        return soundSocPEQFreq;
    }

    public static int getSoundSocPEQFreq(int i) {
        HisLogger.d("HISMW@audio_java", "getSoundSocPEQFreq index:" + i);
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getSoundSocPEQFreq(i, new IHisAudioApi.getSoundSocPEQFreqCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.30
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSoundSocPEQFreqCallback
                    public void onValues(int i2, int i3) {
                        int unused = HisAudioApi.mReturnVal = i2;
                        int unused2 = HisAudioApi.mGetParamVal = i3;
                        HisLogger.d("HISMW@audio_java", "getSoundSocPEQFreq ret =" + HisAudioApi.mReturnVal + ",value = " + HisAudioApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setSoundSocPEQGain(int i, int i2) {
        HisLogger.d("HISMW@audio_java", "setSoundSocPEQGain index:" + i + "val:" + i2);
        int soundSocPEQGain = -1;
        if (getHidlService()) {
            try {
                soundSocPEQGain = mAudioServer.setSoundSocPEQGain(i, i2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@audio_java", "setSoundSocPEQGain ret = " + soundSocPEQGain);
        }
        return soundSocPEQGain;
    }

    public static int getSoundSocPEQGain(int i) {
        HisLogger.d("HISMW@audio_java", "getSoundSocPEQGain index:" + i);
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getSoundSocPEQGain(i, new IHisAudioApi.getSoundSocPEQGainCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.31
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSoundSocPEQGainCallback
                    public void onValues(int i2, int i3) {
                        int unused = HisAudioApi.mReturnVal = i2;
                        int unused2 = HisAudioApi.mGetParamVal = i3;
                        HisLogger.d("HISMW@audio_java", "getSoundSocPEQGain ret =" + HisAudioApi.mReturnVal + ",value = " + HisAudioApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setSoundSocPEQQ(int i, int i2) {
        HisLogger.d("HISMW@audio_java", "setSoundSocPEQQ index:" + i + "val:" + i2);
        int soundSocPEQQ = -1;
        if (getHidlService()) {
            try {
                soundSocPEQQ = mAudioServer.setSoundSocPEQQ(i, i2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@audio_java", "setSoundSocPEQQ ret = " + soundSocPEQQ);
        }
        return soundSocPEQQ;
    }

    public static int getSoundSocPEQQ(int i) {
        HisLogger.d("HISMW@audio_java", "getSoundSocPEQQ index:" + i);
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getSoundSocPEQQ(i, new IHisAudioApi.getSoundSocPEQQCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.32
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSoundSocPEQQCallback
                    public void onValues(int i2, int i3) {
                        int unused = HisAudioApi.mReturnVal = i2;
                        int unused2 = HisAudioApi.mGetParamVal = i3;
                        HisLogger.d("HISMW@audio_java", "getSoundSocPEQQ ret =" + HisAudioApi.mReturnVal + ",value = " + HisAudioApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setSoundSocPEQEnable(int i) {
        HisLogger.d("HISMW@audio_java", "setSoundSocPEQEnable val:" + i);
        int soundSocPEQEnable = -1;
        if (getHidlService()) {
            try {
                soundSocPEQEnable = mAudioServer.setSoundSocPEQEnable(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@audio_java", "setSoundSocPEQEnable ret = " + soundSocPEQEnable);
        }
        return soundSocPEQEnable;
    }

    public static int getSoundSocPEQEnable() {
        HisLogger.d("HISMW@audio_java", "getSoundSocPEQEnable");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getSoundSocPEQEnable(new IHisAudioApi.getSoundSocPEQEnableCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.33
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSoundSocPEQEnableCallback
                    public void onValues(int i, int i2) {
                        int unused = HisAudioApi.mReturnVal = i;
                        int unused2 = HisAudioApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@audio_java", "getSoundSocPEQEnable ret =" + HisAudioApi.mReturnVal + ",value = " + HisAudioApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getIsSubwooferSupport() {
        HisLogger.d("HISMW@audio_java", "getIsSubwooferSupport");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getIsSubwooferSupport(new IHisAudioApi.getIsSubwooferSupportCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.35
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getIsSubwooferSupportCallback
                    public void onValues(int i, int i2) {
                        int unused = HisAudioApi.mReturnVal = i;
                        int unused2 = HisAudioApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@audio_java", "getIsSubwooferSupport ret =" + HisAudioApi.mReturnVal + ",value = " + HisAudioApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getSoundCommonInf(int i, int i2, int i3) {
        HisLogger.d("HISMW@audio_java", "getSoundCommonInf feature:" + i + ", index:" + i2 + ", val:" + i3);
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getSoundCommonInf(i, i2, i3, new IHisAudioApi.getSoundCommonInfCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.41
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSoundCommonInfCallback
                    public void onValues(int i4, int i5) {
                        int unused = HisAudioApi.mReturnVal = i4;
                        int unused2 = HisAudioApi.mGetParamVal = i5;
                        HisLogger.d("HISMW@audio_java", "getSoundCommonInf ret =" + HisAudioApi.mReturnVal + ",value = " + HisAudioApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setMultiDigitalAMPValueByDevId(int i, int i2, int i3) {
        HisLogger.d("HISMW@audio_java", "setMultiDigitalAMPValueByDevId i4DevId:" + i + " i4Index" + i2 + " i4InVal" + i3);
        int multiDigitalAMPValueByDevId = -1;
        if (getHidlService()) {
            try {
                multiDigitalAMPValueByDevId = mAudioServer.setMultiDigitalAMPValueByDevId(i, i2, i3);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@audio_java", "setMultiDigitalAMPValueByDevId ret = " + multiDigitalAMPValueByDevId);
        }
        return multiDigitalAMPValueByDevId;
    }

    public static int getMultiDigitalAMPValueByDevId(int i, int i2) {
        HisLogger.d("HISMW@audio_java", "getMultiDigitalAMPValueByDevId i4DevId " + i + "i4Index " + i2);
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getMultiDigitalAMPValueByDevId(i, i2, new IHisAudioApi.getMultiDigitalAMPValueByDevIdCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.50
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getMultiDigitalAMPValueByDevIdCallback
                    public void onValues(int i3, int i4) {
                        int unused = HisAudioApi.mReturnVal = i3;
                        int unused2 = HisAudioApi.mGetParamVal = i4;
                        HisLogger.d("HISMW@audio_java", "getMultiDigitalAMPValueByDevId ret =" + HisAudioApi.mReturnVal + ",value = " + HisAudioApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setMultiBalance(int i) {
        HisLogger.d("HISMW@audio_java", "setMultiBalance i4InVal:" + i);
        int multiBalance = -1;
        if (getHidlService()) {
            try {
                multiBalance = mAudioServer.setMultiBalance(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@audio_java", "setMultiBalance ret = " + multiBalance);
        }
        return multiBalance;
    }

    public static int getMultiBalance() {
        HisLogger.d("HISMW@audio_java", "getMultiBalance ");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mAudioServer.getMultiBalance(new IHisAudioApi.getMultiBalanceCallback() { // from class: com.hisense.avmiddleware.HisAudioApi.51
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getMultiBalanceCallback
                    public void onValues(int i, int i2) {
                        int unused = HisAudioApi.mReturnVal = i;
                        int unused2 = HisAudioApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@audio_java", "getMultiBalance ret =" + HisAudioApi.mReturnVal + ",value = " + HisAudioApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }
}
