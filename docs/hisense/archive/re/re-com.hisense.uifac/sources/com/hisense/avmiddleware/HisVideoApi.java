package com.hisense.avmiddleware;

import java.util.ArrayList;
import vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi;

/* JADX INFO: loaded from: classes.dex */
public class HisVideoApi {
    private static int mGetParamVal = -1;
    private static ArrayList<Integer> mItemData = null;
    private static String mItemStr = null;
    private static int mReturnVal = -1;
    private static IHisVideoApi mVideoServer;

    public static boolean getHidlService() {
        try {
            IHisVideoApi service = IHisVideoApi.getService();
            mVideoServer = service;
            return service != null;
        } catch (Exception e) {
            HisLogger.e("HISMW@videojava", "get Video HIDL Service exception" + e);
            return false;
        }
    }

    public static int setAspectRatio(int i) {
        HisLogger.d("HISMW@videojava", "setAspectRatio val:" + i);
        int aspectRatio = -1;
        if (getHidlService()) {
            try {
                aspectRatio = mVideoServer.setAspectRatio(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setAspectRatio ret = " + aspectRatio);
        }
        return aspectRatio;
    }

    public static int getAspectRatio() {
        HisLogger.d("HISMW@videojava", "getAspectRatio");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getAspectRatio(new IHisVideoApi.getAspectRatioCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.1
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getAspectRatioCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getAspectRatio ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setLocalDimming(int i) {
        HisLogger.d("HISMW@videojava", "setLocalDimming val:" + i);
        int localDimming = -1;
        if (getHidlService()) {
            try {
                localDimming = mVideoServer.setLocalDimming(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setLocalDimming ret = " + localDimming);
        }
        return localDimming;
    }

    public static int getLocalDimming() {
        HisLogger.d("HISMW@videojava", "getLocalDimming");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getLocalDimming(new IHisVideoApi.getLocalDimmingCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.3
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLocalDimmingCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getLocalDimming ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setLocalDimmingCheck(int i) {
        HisLogger.d("HISMW@videojava", "setLocalDimmingCheck val:" + i);
        int itemValue = -1;
        if (getHidlService()) {
            try {
                itemValue = mVideoServer.setItemValue(11, i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setLocalDimmingCheck ret = " + itemValue);
        }
        return itemValue;
    }

    public static int setBacklightPercentByFactory(int i) {
        HisLogger.d("HISMW@videojava", "setBacklightPercentByFactory val:" + i);
        int backlightPercentByFactory = -1;
        if (getHidlService()) {
            try {
                backlightPercentByFactory = mVideoServer.setBacklightPercentByFactory(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setBacklightPercentByFactory ret = " + backlightPercentByFactory);
        }
        return backlightPercentByFactory;
    }

    public static int getBacklightPercentByFactory() {
        HisLogger.d("HISMW@videojava", "getBacklightPercentByFactory");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getBacklightPercentByFactory(new IHisVideoApi.getBacklightPercentByFactoryCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.7
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBacklightPercentByFactoryCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getBacklightPercentByFactory ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getLightSensorValue() {
        HisLogger.d("HISMW@videojava", "getLightSensorValue");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getLightSensorValue(new IHisVideoApi.getLightSensorValueCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.13
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLightSensorValueCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getLightSensorValue ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setLightSensorCurve(int i, int i2, int i3) {
        HisLogger.d("HISMW@videojava", "setLightSensorCurve mode: " + i + "index: " + i2 + "value: " + i3);
        int lightSensorCurve = -1;
        if (getHidlService()) {
            try {
                lightSensorCurve = mVideoServer.setLightSensorCurve(i, i2, i3);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setLightSensorCurve ret = " + lightSensorCurve);
        }
        return lightSensorCurve;
    }

    public static int getLightSensorCurve(final int i, final int i2) {
        HisLogger.d("HISMW@videojava", "getLightSensorCurve");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getLightSensorCurve(i, i2, new IHisVideoApi.getLightSensorCurveCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.15
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLightSensorCurveCallback
                    public void onValues(int i3, int i4) {
                        int unused = HisVideoApi.mReturnVal = i3;
                        int unused2 = HisVideoApi.mGetParamVal = i4;
                        HisLogger.d("HISMW@videojava", "getLightSensorCurve ret =" + HisVideoApi.mReturnVal + "mode: " + i + "index: " + i2 + "value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setPictureModeByFactory(int i) {
        HisLogger.d("HISMW@videojava", "setPictureModeByFactory val:" + i);
        int pictureModeByFactory = -1;
        if (getHidlService()) {
            try {
                pictureModeByFactory = mVideoServer.setPictureModeByFactory(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setPictureModeByFactory ret = " + pictureModeByFactory);
        }
        return pictureModeByFactory;
    }

    public static int getPictureModeByFactory() {
        HisLogger.d("HISMW@videojava", "getPictureModeByFactory");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getPictureModeByFactory(new IHisVideoApi.getPictureModeByFactoryCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.21
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getPictureModeByFactoryCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getPictureModeByFactory ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setContrastByFactory(int i) {
        HisLogger.d("HISMW@videojava", "setContrastByFactory val:" + i);
        int contrastByFactory = -1;
        if (getHidlService()) {
            try {
                contrastByFactory = mVideoServer.setContrastByFactory(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setContrastByFactory ret = " + contrastByFactory);
        }
        return contrastByFactory;
    }

    public static int getContrastByFactory() {
        HisLogger.d("HISMW@videojava", "getContrastByFactory");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getContrastByFactory(new IHisVideoApi.getContrastByFactoryCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.30
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getContrastByFactoryCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getContrastByFactory ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setBrightnessByFactory(int i) {
        HisLogger.d("HISMW@videojava", "setBrightnessByFactory val:" + i);
        int brightnessByFactory = -1;
        if (getHidlService()) {
            try {
                brightnessByFactory = mVideoServer.setBrightnessByFactory(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setBrightnessByFactory ret = " + brightnessByFactory);
        }
        return brightnessByFactory;
    }

    public static int getBrightnessByFactory() {
        HisLogger.d("HISMW@videojava", "getBrightnessByFactory");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getBrightnessByFactory(new IHisVideoApi.getBrightnessByFactoryCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.36
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBrightnessByFactoryCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getBrightnessByFactory ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setSaturationByFactory(int i) {
        HisLogger.d("HISMW@videojava", "setSaturationByFactory val:" + i);
        int saturationByFactory = -1;
        if (getHidlService()) {
            try {
                saturationByFactory = mVideoServer.setSaturationByFactory(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setSaturationByFactory ret = " + saturationByFactory);
        }
        return saturationByFactory;
    }

    public static int getSaturationByFactory() {
        HisLogger.d("HISMW@videojava", "getSaturationByFactory");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getSaturationByFactory(new IHisVideoApi.getSaturationByFactoryCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.42
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSaturationByFactoryCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getSaturationByFactory ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setSharpnessByFactory(int i) {
        HisLogger.d("HISMW@videojava", "setSharpnessByFactory val:" + i);
        int sharpnessByFactory = -1;
        if (getHidlService()) {
            try {
                sharpnessByFactory = mVideoServer.setSharpnessByFactory(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setSharpnessByFactory ret = " + sharpnessByFactory);
        }
        return sharpnessByFactory;
    }

    public static int getSharpnessByFactory() {
        HisLogger.d("HISMW@videojava", "getSharpnessByFactory");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getSharpnessByFactory(new IHisVideoApi.getSharpnessByFactoryCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.48
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSharpnessByFactoryCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getSharpnessByFactory ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setActiveContrast(int i) {
        HisLogger.d("HISMW@videojava", "setActiveContrast val:" + i);
        int activeContrast = -1;
        if (getHidlService()) {
            try {
                activeContrast = mVideoServer.setActiveContrast(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setActiveContrast ret = " + activeContrast);
        }
        return activeContrast;
    }

    public static int setFactoryMultiPointWhiteBalanceLevel(int i) {
        HisLogger.d("HISMW@videojava", "setFactoryMultiPointWhiteBalanceLevel val:" + i);
        int factoryMultiPointWhiteBalanceLevel = -1;
        if (getHidlService()) {
            try {
                factoryMultiPointWhiteBalanceLevel = mVideoServer.setFactoryMultiPointWhiteBalanceLevel(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setFactoryMultiPointWhiteBalanceLevel ret = " + factoryMultiPointWhiteBalanceLevel);
        }
        return factoryMultiPointWhiteBalanceLevel;
    }

    public static int getFactoryMultiPointWhiteBalanceLevel() {
        HisLogger.d("HISMW@videojava", "getFactoryMultiPointWhiteBalanceLevel");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getFactoryMultiPointWhiteBalanceLevel(new IHisVideoApi.getFactoryMultiPointWhiteBalanceLevelCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.86
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFactoryMultiPointWhiteBalanceLevelCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getFactoryMultiPointWhiteBalanceLevel ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setFactoryMultiPointWhiteBalanceRed(int i) {
        HisLogger.d("HISMW@videojava", "setFactoryMultiPointWhiteBalanceRed val:" + i);
        int factoryMultiPointWhiteBalanceRed = -1;
        if (getHidlService()) {
            try {
                factoryMultiPointWhiteBalanceRed = mVideoServer.setFactoryMultiPointWhiteBalanceRed(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setFactoryMultiPointWhiteBalanceRed ret = " + factoryMultiPointWhiteBalanceRed);
        }
        return factoryMultiPointWhiteBalanceRed;
    }

    public static int getFactoryMultiPointWhiteBalanceRed() {
        HisLogger.d("HISMW@videojava", "getFactoryMultiPointWhiteBalanceRed");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getFactoryMultiPointWhiteBalanceRed(new IHisVideoApi.getFactoryMultiPointWhiteBalanceRedCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.87
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFactoryMultiPointWhiteBalanceRedCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getFactoryMultiPointWhiteBalanceRed ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setFactoryMultiPointWhiteBalanceGreen(int i) {
        HisLogger.d("HISMW@videojava", "setFactoryMultiPointWhiteBalanceGreen val:" + i);
        int factoryMultiPointWhiteBalanceGreen = -1;
        if (getHidlService()) {
            try {
                factoryMultiPointWhiteBalanceGreen = mVideoServer.setFactoryMultiPointWhiteBalanceGreen(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setFactoryMultiPointWhiteBalanceGreen ret = " + factoryMultiPointWhiteBalanceGreen);
        }
        return factoryMultiPointWhiteBalanceGreen;
    }

    public static int getFactoryMultiPointWhiteBalanceGreen() {
        HisLogger.d("HISMW@videojava", "getFactoryMultiPointWhiteBalanceGreen");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getFactoryMultiPointWhiteBalanceGreen(new IHisVideoApi.getFactoryMultiPointWhiteBalanceGreenCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.88
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFactoryMultiPointWhiteBalanceGreenCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getFactoryMultiPointWhiteBalanceGreen ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setFactoryMultiPointWhiteBalanceBlue(int i) {
        HisLogger.d("HISMW@videojava", "setFactoryMultiPointWhiteBalanceBlue val:" + i);
        int factoryMultiPointWhiteBalanceBlue = -1;
        if (getHidlService()) {
            try {
                factoryMultiPointWhiteBalanceBlue = mVideoServer.setFactoryMultiPointWhiteBalanceBlue(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setFactoryMultiPointWhiteBalanceBlue ret = " + factoryMultiPointWhiteBalanceBlue);
        }
        return factoryMultiPointWhiteBalanceBlue;
    }

    public static int getFactoryMultiPointWhiteBalanceBlue() {
        HisLogger.d("HISMW@videojava", "getFactoryMultiPointWhiteBalanceBlue");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getFactoryMultiPointWhiteBalanceBlue(new IHisVideoApi.getFactoryMultiPointWhiteBalanceBlueCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.89
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFactoryMultiPointWhiteBalanceBlueCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getFactoryMultiPointWhiteBalanceBlue ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setBrightnessMin(int i) {
        HisLogger.d("HISMW@videojava", "setBrightnessMin val:" + i);
        int brightnessMin = -1;
        if (getHidlService()) {
            try {
                brightnessMin = mVideoServer.setBrightnessMin(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setBrightnessMin ret = " + brightnessMin);
        }
        return brightnessMin;
    }

    public static int getBrightnessMin() {
        HisLogger.d("HISMW@videojava", "getBrightnessMin");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getBrightnessMin(new IHisVideoApi.getBrightnessMinCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.97
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBrightnessMinCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getBrightnessMin ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setBrightness20(int i) {
        HisLogger.d("HISMW@videojava", "setBrightness20 val:" + i);
        int brightness20 = -1;
        if (getHidlService()) {
            try {
                brightness20 = mVideoServer.setBrightness20(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setBrightness20 ret = " + brightness20);
        }
        return brightness20;
    }

    public static int getBrightness20() {
        HisLogger.d("HISMW@videojava", "getBrightness20");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getBrightness20(new IHisVideoApi.getBrightness20Callback() { // from class: com.hisense.avmiddleware.HisVideoApi.98
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBrightness20Callback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getBrightness20 ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setBrightnessMid(int i) {
        HisLogger.d("HISMW@videojava", "setBrightnessMid val:" + i);
        int brightnessMid = -1;
        if (getHidlService()) {
            try {
                brightnessMid = mVideoServer.setBrightnessMid(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setBrightnessMid ret = " + brightnessMid);
        }
        return brightnessMid;
    }

    public static int getBrightnessMid() {
        HisLogger.d("HISMW@videojava", "getBrightnessMid");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getBrightnessMid(new IHisVideoApi.getBrightnessMidCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.99
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBrightnessMidCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getBrightnessMid ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setBrightness80(int i) {
        HisLogger.d("HISMW@videojava", "setBrightness80 val:" + i);
        int brightness80 = -1;
        if (getHidlService()) {
            try {
                brightness80 = mVideoServer.setBrightness80(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setBrightness80 ret = " + brightness80);
        }
        return brightness80;
    }

    public static int getBrightness80() {
        HisLogger.d("HISMW@videojava", "getBrightness80");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getBrightness80(new IHisVideoApi.getBrightness80Callback() { // from class: com.hisense.avmiddleware.HisVideoApi.100
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBrightness80Callback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getBrightness80 ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setBrightnessMax(int i) {
        HisLogger.d("HISMW@videojava", "setBrightnessMax val:" + i);
        int brightnessMax = -1;
        if (getHidlService()) {
            try {
                brightnessMax = mVideoServer.setBrightnessMax(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setBrightnessMax ret = " + brightnessMax);
        }
        return brightnessMax;
    }

    public static int getBrightnessMax() {
        HisLogger.d("HISMW@videojava", "getBrightnessMax");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getBrightnessMax(new IHisVideoApi.getBrightnessMaxCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.101
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBrightnessMaxCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getBrightnessMax ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setContrastMin(int i) {
        HisLogger.d("HISMW@videojava", "setContrastMin val:" + i);
        int contrastMin = -1;
        if (getHidlService()) {
            try {
                contrastMin = mVideoServer.setContrastMin(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setContrastMin ret = " + contrastMin);
        }
        return contrastMin;
    }

    public static int getContrastMin() {
        HisLogger.d("HISMW@videojava", "getContrastMin");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getContrastMin(new IHisVideoApi.getContrastMinCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.102
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getContrastMinCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getContrastMin ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setContrast20(int i) {
        HisLogger.d("HISMW@videojava", "setContrast20 val:" + i);
        int contrast20 = -1;
        if (getHidlService()) {
            try {
                contrast20 = mVideoServer.setContrast20(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setContrast20 ret = " + contrast20);
        }
        return contrast20;
    }

    public static int getContrast20() {
        HisLogger.d("HISMW@videojava", "getContrast20");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getContrast20(new IHisVideoApi.getContrast20Callback() { // from class: com.hisense.avmiddleware.HisVideoApi.103
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getContrast20Callback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getContrast20 ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setContrastMid(int i) {
        HisLogger.d("HISMW@videojava", "setContrastMid val:" + i);
        int contrastMid = -1;
        if (getHidlService()) {
            try {
                contrastMid = mVideoServer.setContrastMid(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setContrastMid ret = " + contrastMid);
        }
        return contrastMid;
    }

    public static int getContrastMid() {
        HisLogger.d("HISMW@videojava", "getContrastMid");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getContrastMid(new IHisVideoApi.getContrastMidCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.104
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getContrastMidCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getContrastMid ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setContrast80(int i) {
        HisLogger.d("HISMW@videojava", "setContrast80 val:" + i);
        int contrast80 = -1;
        if (getHidlService()) {
            try {
                contrast80 = mVideoServer.setContrast80(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setContrast80 ret = " + contrast80);
        }
        return contrast80;
    }

    public static int getContrast80() {
        HisLogger.d("HISMW@videojava", "getContrast80");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getContrast80(new IHisVideoApi.getContrast80Callback() { // from class: com.hisense.avmiddleware.HisVideoApi.105
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getContrast80Callback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getContrast80 ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setContrastMax(int i) {
        HisLogger.d("HISMW@videojava", "setContrastMax val:" + i);
        int contrastMax = -1;
        if (getHidlService()) {
            try {
                contrastMax = mVideoServer.setContrastMax(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setContrastMax ret = " + contrastMax);
        }
        return contrastMax;
    }

    public static int getContrastMax() {
        HisLogger.d("HISMW@videojava", "getContrastMax");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getContrastMax(new IHisVideoApi.getContrastMaxCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.106
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getContrastMaxCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getContrastMax ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setSaturationMin(int i) {
        HisLogger.d("HISMW@videojava", "setSaturationMin val:" + i);
        int saturationMin = -1;
        if (getHidlService()) {
            try {
                saturationMin = mVideoServer.setSaturationMin(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setSaturationMin ret = " + saturationMin);
        }
        return saturationMin;
    }

    public static int getSaturationMin() {
        HisLogger.d("HISMW@videojava", "getSaturationMin");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getSaturationMin(new IHisVideoApi.getSaturationMinCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.107
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSaturationMinCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getSaturationMin ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setSaturation20(int i) {
        HisLogger.d("HISMW@videojava", "setSaturation20 val:" + i);
        int saturation20 = -1;
        if (getHidlService()) {
            try {
                saturation20 = mVideoServer.setSaturation20(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setSaturation20 ret = " + saturation20);
        }
        return saturation20;
    }

    public static int getSaturation20() {
        HisLogger.d("HISMW@videojava", "getSaturation20");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getSaturation20(new IHisVideoApi.getSaturation20Callback() { // from class: com.hisense.avmiddleware.HisVideoApi.108
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSaturation20Callback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getSaturation20 ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setSaturationMid(int i) {
        HisLogger.d("HISMW@videojava", "setSaturationMid val:" + i);
        int saturationMid = -1;
        if (getHidlService()) {
            try {
                saturationMid = mVideoServer.setSaturationMid(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setSaturationMid ret = " + saturationMid);
        }
        return saturationMid;
    }

    public static int getSaturationMid() {
        HisLogger.d("HISMW@videojava", "getSaturationMid");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getSaturationMid(new IHisVideoApi.getSaturationMidCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.109
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSaturationMidCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getSaturationMid ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setSaturation80(int i) {
        HisLogger.d("HISMW@videojava", "setSaturation80 val:" + i);
        int saturation80 = -1;
        if (getHidlService()) {
            try {
                saturation80 = mVideoServer.setSaturation80(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setSaturation80 ret = " + saturation80);
        }
        return saturation80;
    }

    public static int getSaturation80() {
        HisLogger.d("HISMW@videojava", "getSaturation80");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getSaturation80(new IHisVideoApi.getSaturation80Callback() { // from class: com.hisense.avmiddleware.HisVideoApi.110
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSaturation80Callback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getSaturation80 ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setSaturationMax(int i) {
        HisLogger.d("HISMW@videojava", "setSaturationMax val:" + i);
        int saturationMax = -1;
        if (getHidlService()) {
            try {
                saturationMax = mVideoServer.setSaturationMax(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setSaturationMax ret = " + saturationMax);
        }
        return saturationMax;
    }

    public static int getSaturationMax() {
        HisLogger.d("HISMW@videojava", "getSaturationMax");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getSaturationMax(new IHisVideoApi.getSaturationMaxCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.111
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSaturationMaxCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getSaturationMax ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setHueMin(int i) {
        HisLogger.d("HISMW@videojava", "setHueMin val:" + i);
        int hueMin = -1;
        if (getHidlService()) {
            try {
                hueMin = mVideoServer.setHueMin(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setHueMin ret = " + hueMin);
        }
        return hueMin;
    }

    public static int getHueMin() {
        HisLogger.d("HISMW@videojava", "getHueMin");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getHueMin(new IHisVideoApi.getHueMinCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.112
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getHueMinCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getHueMin ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setHue20(int i) {
        HisLogger.d("HISMW@videojava", "setHue20 val:" + i);
        int hue20 = -1;
        if (getHidlService()) {
            try {
                hue20 = mVideoServer.setHue20(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setHue20 ret = " + hue20);
        }
        return hue20;
    }

    public static int getHue20() {
        HisLogger.d("HISMW@videojava", "getHue20");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getHue20(new IHisVideoApi.getHue20Callback() { // from class: com.hisense.avmiddleware.HisVideoApi.113
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getHue20Callback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getHue20 ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setHueMid(int i) {
        HisLogger.d("HISMW@videojava", "setHueMid val:" + i);
        int hueMid = -1;
        if (getHidlService()) {
            try {
                hueMid = mVideoServer.setHueMid(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setHueMid ret = " + hueMid);
        }
        return hueMid;
    }

    public static int getHueMid() {
        HisLogger.d("HISMW@videojava", "getHueMid");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getHueMid(new IHisVideoApi.getHueMidCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.114
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getHueMidCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getHueMid ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setHue80(int i) {
        HisLogger.d("HISMW@videojava", "setHue80 val:" + i);
        int hue80 = -1;
        if (getHidlService()) {
            try {
                hue80 = mVideoServer.setHue80(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setHue80 ret = " + hue80);
        }
        return hue80;
    }

    public static int getHue80() {
        HisLogger.d("HISMW@videojava", "getHue80");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getHue80(new IHisVideoApi.getHue80Callback() { // from class: com.hisense.avmiddleware.HisVideoApi.115
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getHue80Callback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getHue80 ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setHueMax(int i) {
        HisLogger.d("HISMW@videojava", "setHueMax val:" + i);
        int hueMax = -1;
        if (getHidlService()) {
            try {
                hueMax = mVideoServer.setHueMax(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setHueMax ret = " + hueMax);
        }
        return hueMax;
    }

    public static int getHueMax() {
        HisLogger.d("HISMW@videojava", "getHueMax");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getHueMax(new IHisVideoApi.getHueMaxCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.116
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getHueMaxCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getHueMax ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setSharpnessMin(int i) {
        HisLogger.d("HISMW@videojava", "setSharpnessMin val:" + i);
        int sharpnessMin = -1;
        if (getHidlService()) {
            try {
                sharpnessMin = mVideoServer.setSharpnessMin(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setSharpnessMin ret = " + sharpnessMin);
        }
        return sharpnessMin;
    }

    public static int getSharpnessMin() {
        HisLogger.d("HISMW@videojava", "getSharpnessMin");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getSharpnessMin(new IHisVideoApi.getSharpnessMinCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.117
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSharpnessMinCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getSharpnessMin ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setSharpness20(int i) {
        HisLogger.d("HISMW@videojava", "setSharpness20 val:" + i);
        int sharpness20 = -1;
        if (getHidlService()) {
            try {
                sharpness20 = mVideoServer.setSharpness20(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setSharpness20 ret = " + sharpness20);
        }
        return sharpness20;
    }

    public static int getSharpness20() {
        HisLogger.d("HISMW@videojava", "getSharpness20");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getSharpness20(new IHisVideoApi.getSharpness20Callback() { // from class: com.hisense.avmiddleware.HisVideoApi.118
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSharpness20Callback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getSharpness20 ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setSharpnessMid(int i) {
        HisLogger.d("HISMW@videojava", "setSharpnessMid val:" + i);
        int sharpnessMid = -1;
        if (getHidlService()) {
            try {
                sharpnessMid = mVideoServer.setSharpnessMid(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setSharpnessMid ret = " + sharpnessMid);
        }
        return sharpnessMid;
    }

    public static int getSharpnessMid() {
        HisLogger.d("HISMW@videojava", "getSharpnessMid");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getSharpnessMid(new IHisVideoApi.getSharpnessMidCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.119
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSharpnessMidCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getSharpnessMid ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setSharpness80(int i) {
        HisLogger.d("HISMW@videojava", "setSharpness80 val:" + i);
        int sharpness80 = -1;
        if (getHidlService()) {
            try {
                sharpness80 = mVideoServer.setSharpness80(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setSharpness80 ret = " + sharpness80);
        }
        return sharpness80;
    }

    public static int getSharpness80() {
        HisLogger.d("HISMW@videojava", "getSharpness80");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getSharpness80(new IHisVideoApi.getSharpness80Callback() { // from class: com.hisense.avmiddleware.HisVideoApi.120
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSharpness80Callback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getSharpness80 ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setSharpnessMax(int i) {
        HisLogger.d("HISMW@videojava", "setSharpnessMax val:" + i);
        int sharpnessMax = -1;
        if (getHidlService()) {
            try {
                sharpnessMax = mVideoServer.setSharpnessMax(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setSharpnessMax ret = " + sharpnessMax);
        }
        return sharpnessMax;
    }

    public static int getSharpnessMax() {
        HisLogger.d("HISMW@videojava", "getSharpnessMax");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getSharpnessMax(new IHisVideoApi.getSharpnessMaxCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.121
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSharpnessMaxCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getSharpnessMax ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setBacklightMin(int i) {
        HisLogger.d("HISMW@videojava", "setBacklightMin val:" + i);
        int backlightMin = -1;
        if (getHidlService()) {
            try {
                backlightMin = mVideoServer.setBacklightMin(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setBacklightMin ret = " + backlightMin);
        }
        return backlightMin;
    }

    public static int getBacklightMin() {
        HisLogger.d("HISMW@videojava", "getBacklightMin");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getBacklightMin(new IHisVideoApi.getBacklightMinCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.122
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBacklightMinCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getBacklightMin ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setBacklight20(int i) {
        HisLogger.d("HISMW@videojava", "setBacklight20 val:" + i);
        int backlight20 = -1;
        if (getHidlService()) {
            try {
                backlight20 = mVideoServer.setBacklight20(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setBacklight20 ret = " + backlight20);
        }
        return backlight20;
    }

    public static int getBacklight20() {
        HisLogger.d("HISMW@videojava", "getSharpnessMax");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getBacklight20(new IHisVideoApi.getBacklight20Callback() { // from class: com.hisense.avmiddleware.HisVideoApi.123
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBacklight20Callback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getBacklight20 ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setBacklightMid(int i) {
        HisLogger.d("HISMW@videojava", "setBacklightMid val:" + i);
        int backlightMid = -1;
        if (getHidlService()) {
            try {
                backlightMid = mVideoServer.setBacklightMid(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setBacklightMid ret = " + backlightMid);
        }
        return backlightMid;
    }

    public static int getBacklightMid() {
        HisLogger.d("HISMW@videojava", "getBacklightMid");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getBacklightMid(new IHisVideoApi.getBacklightMidCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.124
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBacklightMidCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getBacklightMid ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setBacklight80(int i) {
        HisLogger.d("HISMW@videojava", "setBacklight80 val:" + i);
        int backlight80 = -1;
        if (getHidlService()) {
            try {
                backlight80 = mVideoServer.setBacklight80(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setBacklight80 ret = " + backlight80);
        }
        return backlight80;
    }

    public static int getBacklight80() {
        HisLogger.d("HISMW@videojava", "getBacklight80");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getBacklight80(new IHisVideoApi.getBacklight80Callback() { // from class: com.hisense.avmiddleware.HisVideoApi.125
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBacklight80Callback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getBacklight80 ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setBacklightMax(int i) {
        HisLogger.d("HISMW@videojava", "setBacklightMax val:" + i);
        int backlightMax = -1;
        if (getHidlService()) {
            try {
                backlightMax = mVideoServer.setBacklightMax(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setBacklightMax ret = " + backlightMax);
        }
        return backlightMax;
    }

    public static int getBacklightMax() {
        HisLogger.d("HISMW@videojava", "getBacklightMax");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getBacklightMax(new IHisVideoApi.getBacklightMaxCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.126
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBacklightMaxCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getBacklightMax ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setBacklightPWMFreq(int i) {
        HisLogger.d("HISMW@videojava", "setBacklightPWMFreq val:" + i);
        int backlightPWMFreq = -1;
        if (getHidlService()) {
            try {
                backlightPWMFreq = mVideoServer.setBacklightPWMFreq(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setBacklightPWMFreq ret = " + backlightPWMFreq);
        }
        return backlightPWMFreq;
    }

    public static int getBacklightPWMFreq() {
        HisLogger.d("HISMW@videojava", "getBacklightPWMFreq");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getBacklightPWMFreq(new IHisVideoApi.getBacklightPWMFreqCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.127
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBacklightPWMFreqCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getBacklightPWMFreq ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setOverscanTop(int i) {
        HisLogger.d("HISMW@videojava", "setOverscanTop val:" + i);
        int overscanTop = -1;
        if (getHidlService()) {
            try {
                overscanTop = mVideoServer.setOverscanTop(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setOverscanTop ret = " + overscanTop);
        }
        return overscanTop;
    }

    public static int getOverscanTop() {
        HisLogger.d("HISMW@videojava", "getOverscanTop");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getOverscanTop(new IHisVideoApi.getOverscanTopCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.128
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getOverscanTopCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getOverscanTop ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setOverscanBottom(int i) {
        HisLogger.d("HISMW@videojava", "setOverscanBottom val:" + i);
        int overscanBottom = -1;
        if (getHidlService()) {
            try {
                overscanBottom = mVideoServer.setOverscanBottom(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setOverscanBottom ret = " + overscanBottom);
        }
        return overscanBottom;
    }

    public static int getOverscanBottom() {
        HisLogger.d("HISMW@videojava", "getOverscanBottom");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getOverscanBottom(new IHisVideoApi.getOverscanBottomCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.129
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getOverscanBottomCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getOverscanBottom ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setOverscanLeft(int i) {
        HisLogger.d("HISMW@videojava", "setOverscanLeft val:" + i);
        int overscanLeft = -1;
        if (getHidlService()) {
            try {
                overscanLeft = mVideoServer.setOverscanLeft(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setOverscanLeft ret = " + overscanLeft);
        }
        return overscanLeft;
    }

    public static int getOverscanLeft() {
        HisLogger.d("HISMW@videojava", "getOverscanLeft");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getOverscanLeft(new IHisVideoApi.getOverscanLeftCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.130
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getOverscanLeftCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getOverscanLeft ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setOverscanRight(int i) {
        HisLogger.d("HISMW@videojava", "setOverscanRight val:" + i);
        int overscanRight = -1;
        if (getHidlService()) {
            try {
                overscanRight = mVideoServer.setOverscanRight(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setOverscanRight ret = " + overscanRight);
        }
        return overscanRight;
    }

    public static int getOverscanRight() {
        HisLogger.d("HISMW@videojava", "getOverscanRight");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getOverscanRight(new IHisVideoApi.getOverscanRightCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.131
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getOverscanRightCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getOverscanRight ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setPanel(int i) {
        HisLogger.d("HISMW@videojava", "setPanel val:" + i);
        int panel = -1;
        if (getHidlService()) {
            try {
                panel = mVideoServer.setPanel(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setPanel ret = " + panel);
        }
        return panel;
    }

    public static int getPanel() {
        HisLogger.d("HISMW@videojava", "getPanel");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getPanel(new IHisVideoApi.getPanelCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.132
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getPanelCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getPanel ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setFactoryColorTemperature(int i) {
        HisLogger.d("HISMW@videojava", "setFactoryColorTemperature val:" + i);
        int factoryColorTemperature = -1;
        if (getHidlService()) {
            try {
                factoryColorTemperature = mVideoServer.setFactoryColorTemperature(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setFactoryColorTemperature ret = " + factoryColorTemperature);
        }
        return factoryColorTemperature;
    }

    public static int getFactoryColorTemperature() {
        HisLogger.d("HISMW@videojava", "getFactoryColorTemperature");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getFactoryColorTemperature(new IHisVideoApi.getFactoryColorTemperatureCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.133
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFactoryColorTemperatureCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getFactoryColorTemperature ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setFactoryWhiteBalanceOffsetRed(int i) {
        HisLogger.d("HISMW@videojava", "setFactoryWhiteBalanceOffsetRed val:" + i);
        int factoryWhiteBalanceOffsetRed = -1;
        if (getHidlService()) {
            try {
                factoryWhiteBalanceOffsetRed = mVideoServer.setFactoryWhiteBalanceOffsetRed(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setFactoryWhiteBalanceOffsetRed ret = " + factoryWhiteBalanceOffsetRed);
        }
        return factoryWhiteBalanceOffsetRed;
    }

    public static int getFactoryWhiteBalanceOffsetRed() {
        HisLogger.d("HISMW@videojava", "getFactoryWhiteBalanceOffsetRed");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getFactoryWhiteBalanceOffsetRed(new IHisVideoApi.getFactoryWhiteBalanceOffsetRedCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.134
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFactoryWhiteBalanceOffsetRedCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getFactoryWhiteBalanceOffsetRed ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setFactoryWhiteBalanceOffsetGreen(int i) {
        HisLogger.d("HISMW@videojava", "setFactoryWhiteBalanceOffsetGreen val:" + i);
        int factoryWhiteBalanceOffsetGreen = -1;
        if (getHidlService()) {
            try {
                factoryWhiteBalanceOffsetGreen = mVideoServer.setFactoryWhiteBalanceOffsetGreen(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setFactoryWhiteBalanceOffsetGreen ret = " + factoryWhiteBalanceOffsetGreen);
        }
        return factoryWhiteBalanceOffsetGreen;
    }

    public static int getFactoryWhiteBalanceOffsetGreen() {
        HisLogger.d("HISMW@videojava", "getFactoryWhiteBalanceOffsetGreen");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getFactoryWhiteBalanceOffsetGreen(new IHisVideoApi.getFactoryWhiteBalanceOffsetGreenCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.135
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFactoryWhiteBalanceOffsetGreenCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getFactoryWhiteBalanceOffsetGreen ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setFactoryWhiteBalanceOffsetBlue(int i) {
        HisLogger.d("HISMW@videojava", "setFactoryWhiteBalanceOffsetBlue val:" + i);
        int factoryWhiteBalanceOffsetBlue = -1;
        if (getHidlService()) {
            try {
                factoryWhiteBalanceOffsetBlue = mVideoServer.setFactoryWhiteBalanceOffsetBlue(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setFactoryWhiteBalanceOffsetBlue ret = " + factoryWhiteBalanceOffsetBlue);
        }
        return factoryWhiteBalanceOffsetBlue;
    }

    public static int getFactoryWhiteBalanceOffsetBlue() {
        HisLogger.d("HISMW@videojava", "getFactoryWhiteBalanceOffsetBlue");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getFactoryWhiteBalanceOffsetBlue(new IHisVideoApi.getFactoryWhiteBalanceOffsetBlueCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.136
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFactoryWhiteBalanceOffsetBlueCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getFactoryWhiteBalanceOffsetBlue ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setFactoryWhiteBalanceGainRed(int i) {
        HisLogger.d("HISMW@videojava", "setFactoryWhiteBalanceGainRed val:" + i);
        int factoryWhiteBalanceGainRed = -1;
        if (getHidlService()) {
            try {
                factoryWhiteBalanceGainRed = mVideoServer.setFactoryWhiteBalanceGainRed(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setFactoryWhiteBalanceGainRed ret = " + factoryWhiteBalanceGainRed);
        }
        return factoryWhiteBalanceGainRed;
    }

    public static int getFactoryWhiteBalanceGainRed() {
        HisLogger.d("HISMW@videojava", "getFactoryWhiteBalanceGainRed");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getFactoryWhiteBalanceGainRed(new IHisVideoApi.getFactoryWhiteBalanceGainRedCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.137
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFactoryWhiteBalanceGainRedCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getFactoryWhiteBalanceGainRed ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setFactoryWhiteBalanceGainGreen(int i) {
        HisLogger.d("HISMW@videojava", "setFactoryWhiteBlanceGainGreen val:" + i);
        int factoryWhiteBalanceGainGreen = -1;
        if (getHidlService()) {
            try {
                factoryWhiteBalanceGainGreen = mVideoServer.setFactoryWhiteBalanceGainGreen(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setFactoryWhiteBalanceGainGreen ret = " + factoryWhiteBalanceGainGreen);
        }
        return factoryWhiteBalanceGainGreen;
    }

    public static int getFactoryWhiteBalanceGainGreen() {
        HisLogger.d("HISMW@videojava", "getFactoryWhiteBalanceGainGreen");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getFactoryWhiteBalanceGainGreen(new IHisVideoApi.getFactoryWhiteBalanceGainGreenCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.138
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFactoryWhiteBalanceGainGreenCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getFactoryWhiteBalanceGainGreen ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setFactoryWhiteBalanceGainBlue(int i) {
        HisLogger.d("HISMW@videojava", "setFactoryWhiteBalanceGainBlue val:" + i);
        int factoryWhiteBalanceGainBlue = -1;
        if (getHidlService()) {
            try {
                factoryWhiteBalanceGainBlue = mVideoServer.setFactoryWhiteBalanceGainBlue(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setFactoryWhiteBalanceGainBlue ret = " + factoryWhiteBalanceGainBlue);
        }
        return factoryWhiteBalanceGainBlue;
    }

    public static int getFactoryWhiteBalanceGainBlue() {
        HisLogger.d("HISMW@videojava", "getFactoryWhiteBalanceGainBlue");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getFactoryWhiteBalanceGainBlue(new IHisVideoApi.getFactoryWhiteBalanceGainBlueCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.139
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFactoryWhiteBalanceGainBlueCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getFactoryWhiteBalanceGainBlue ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setInsidePattern(int i) {
        HisLogger.d("HISMW@videojava", "setInsidePattern val:" + i);
        int insidePattern = -1;
        if (getHidlService()) {
            try {
                insidePattern = mVideoServer.setInsidePattern(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setInsidePattern ret = " + insidePattern);
        }
        return insidePattern;
    }

    public static int setTestPattern(int i) {
        HisLogger.d("HISMW@videojava", "setTestPattern val:" + i);
        int testPattern = -1;
        if (getHidlService()) {
            try {
                testPattern = mVideoServer.setTestPattern(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setTestPattern ret = " + testPattern);
        }
        return testPattern;
    }

    public static int setRGBPattern(int i, int i2, int i3, int i4) {
        HisLogger.d("HISMW@videojava", "setRGBPattern mode:" + i + "red:" + i2 + "green" + i3 + "blue" + i4);
        int rGBPattern = -1;
        if (getHidlService()) {
            try {
                rGBPattern = mVideoServer.setRGBPattern(i, i2, i3, i4);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setRGBPattern ret = " + rGBPattern);
        }
        return rGBPattern;
    }

    public static int setFactoryColorTunerColor(int i) {
        HisLogger.d("HISMW@videojava", "setFactoryColorTunerColor val:" + i);
        int factoryColorTunerColor = -1;
        if (getHidlService()) {
            try {
                factoryColorTunerColor = mVideoServer.setFactoryColorTunerColor(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setFactoryColorTunerColor ret = " + factoryColorTunerColor);
        }
        return factoryColorTunerColor;
    }

    public static int getFactoryColorTunerColor() {
        HisLogger.d("HISMW@videojava", "getFactoryColorTunerColor");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getFactoryColorTunerColor(new IHisVideoApi.getFactoryColorTunerColorCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.140
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFactoryColorTunerColorCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getFactoryColorTunerColor ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setFactoryColorTunerBrightness(int i) {
        HisLogger.d("HISMW@videojava", "setFactoryColorTunerBrightness val:" + i);
        int factoryColorTunerBrightness = -1;
        if (getHidlService()) {
            try {
                factoryColorTunerBrightness = mVideoServer.setFactoryColorTunerBrightness(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setFactoryColorTunerBrightness ret = " + factoryColorTunerBrightness);
        }
        return factoryColorTunerBrightness;
    }

    public static int getFactoryColorTunerBrightness() {
        HisLogger.d("HISMW@videojava", "getFactoryColorTunerBrightness");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getFactoryColorTunerBrightness(new IHisVideoApi.getFactoryColorTunerBrightnessCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.141
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFactoryColorTunerBrightnessCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getFactoryColorTunerBrightness ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setFactoryColorTunerSaturation(int i) {
        HisLogger.d("HISMW@videojava", "setFactoryColorTunerSaturation val:" + i);
        int factoryColorTunerSaturation = -1;
        if (getHidlService()) {
            try {
                factoryColorTunerSaturation = mVideoServer.setFactoryColorTunerSaturation(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setFactoryColorTunerSaturation ret = " + factoryColorTunerSaturation);
        }
        return factoryColorTunerSaturation;
    }

    public static int getFactoryColorTunerSaturation() {
        HisLogger.d("HISMW@videojava", "getFactoryColorTunerSaturation");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getFactoryColorTunerSaturation(new IHisVideoApi.getFactoryColorTunerSaturationCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.142
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFactoryColorTunerSaturationCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getFactoryColorTunerSaturation ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setFactoryColorTunerHue(int i) {
        HisLogger.d("HISMW@videojava", "setFactoryColorTunerHue val:" + i);
        int factoryColorTunerHue = -1;
        if (getHidlService()) {
            try {
                factoryColorTunerHue = mVideoServer.setFactoryColorTunerHue(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setFactoryColorTunerHue ret = " + factoryColorTunerHue);
        }
        return factoryColorTunerHue;
    }

    public static int getFactoryColorTunerHue() {
        HisLogger.d("HISMW@videojava", "getFactoryColorTunerHue");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getFactoryColorTunerHue(new IHisVideoApi.getFactoryColorTunerHueCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.143
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFactoryColorTunerHueCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getFactoryColorTunerHue ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setVcomTestEnable(int i) {
        HisLogger.d("HISMW@videojava", "setVcomTestEnable val:" + i);
        int vcomTestEnable = -1;
        if (getHidlService()) {
            try {
                vcomTestEnable = mVideoServer.setVcomTestEnable(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setVcomTestEnable ret = " + vcomTestEnable);
        }
        return vcomTestEnable;
    }

    public static int setBypassPqEnable(int i) {
        HisLogger.d("HISMW@videojava", "setBypassPqEnable val:" + i);
        int pqEnable = -1;
        if (getHidlService()) {
            try {
                pqEnable = mVideoServer.setPqEnable(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setBypassPqEnable ret = " + pqEnable);
        }
        return pqEnable;
    }

    public static int getBypassPqEnable() {
        HisLogger.d("HISMW@videojava", "getBypassPqEnable");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getPqEnable(new IHisVideoApi.getPqEnableCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.145
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getPqEnableCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getBypassPqEnable ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setBypassGammaEnable(int i) {
        HisLogger.d("HISMW@videojava", "setBypassGammaEnable val:" + i);
        int gammaEnable = -1;
        if (getHidlService()) {
            try {
                gammaEnable = mVideoServer.setGammaEnable(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setBypassGammaEnable ret = " + gammaEnable);
        }
        return gammaEnable;
    }

    public static int getBypassGammaEnable() {
        HisLogger.d("HISMW@videojava", "getBypassGammaEnable");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getGammaEnable(new IHisVideoApi.getGammaEnableCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.146
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getGammaEnableCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getBypassGammaEnable ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setBypassSceEnable(int i) {
        HisLogger.d("HISMW@videojava", "setBypassSceEnable val:" + i);
        int sceEnable = -1;
        if (getHidlService()) {
            try {
                sceEnable = mVideoServer.setSceEnable(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setBypassSceEnable ret = " + sceEnable);
        }
        return sceEnable;
    }

    public static int getBypassSceEnable() {
        HisLogger.d("HISMW@videojava", "getBypassSceEnable");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getSceEnable(new IHisVideoApi.getSceEnableCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.147
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSceEnableCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getBypassSceEnable ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setBypassXvyccEnable(int i) {
        HisLogger.d("HISMW@videojava", "setBypassXvyccEnable val:" + i);
        int xvyccEnable = -1;
        if (getHidlService()) {
            try {
                xvyccEnable = mVideoServer.setXvyccEnable(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setBypassXvyccEnable ret = " + xvyccEnable);
        }
        return xvyccEnable;
    }

    public static int getBypassXvyccEnable() {
        HisLogger.d("HISMW@videojava", "getBypassXvyccEnable");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getXvyccEnable(new IHisVideoApi.getXvyccEnableCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.148
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getXvyccEnableCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getBypassXvyccEnable ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setAutoGammaEnable(int i) {
        HisLogger.d("HISMW@videojava", "setAutoGammaEnable val:" + i);
        int iSetAutoGammaEnable = -1;
        if (getHidlService()) {
            try {
                iSetAutoGammaEnable = mVideoServer.SetAutoGammaEnable(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setAutoGammaEnable ret = " + iSetAutoGammaEnable);
        }
        return iSetAutoGammaEnable;
    }

    public static int setAgingModeEnable(int i) {
        HisLogger.d("HISMW@videojava", "setAgingModeEnable val:" + i);
        int agingModeEnable = -1;
        if (getHidlService()) {
            try {
                agingModeEnable = mVideoServer.setAgingModeEnable(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setAgingModeEnable ret = " + agingModeEnable);
        }
        return agingModeEnable;
    }

    public static int getLightSensorOptionState() {
        HisLogger.d("HISMW@videojava", "getLightSensorOptionState");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getLightSensorOptionState(new IHisVideoApi.getLightSensorOptionStateCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.164
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLightSensorOptionStateCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getLightSensorOptionState ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int[] getSignalVideoFormat() {
        HisLogger.d("HISMW@videojava", "getSignalVideoFormat");
        mReturnVal = -1;
        final int[] iArr = {0, 0, 0, 0};
        if (getHidlService()) {
            try {
                mVideoServer.getSignalVideoFormat(new IHisVideoApi.getSignalVideoFormatCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.183
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSignalVideoFormatCallback
                    public void onValues(int i, int i2, int i3, int i4, int i5) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int[] iArr2 = iArr;
                        iArr2[0] = i2;
                        iArr2[1] = i3;
                        iArr2[2] = i4;
                        iArr2[3] = i5;
                        HisLogger.d("HISMW@videojava", "getSignalVideoFormat ret =" + HisVideoApi.mReturnVal + ",value = { " + iArr + "}");
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return iArr;
    }

    public static int set2PWBOffsetRedDelta(int i) {
        HisLogger.d("HISMW@videojava", "set2PWBOffsetRedDelta val:" + i);
        int i2 = -1;
        if (getHidlService()) {
            try {
                i2 = mVideoServer.set2PWBOffsetRedDelta(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "set2PWBOffsetRedDelta ret = " + i2);
        }
        return i2;
    }

    public static int get2PWBOffsetRedDelta() {
        HisLogger.d("HISMW@videojava", "get2PWBOffsetRedDelta");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.get2PWBOffsetRedDelta(new IHisVideoApi.get2PWBOffsetRedDeltaCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.210
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.get2PWBOffsetRedDeltaCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "get2PWBOffsetRedDelta ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int set2PWBOffsetGreenDelta(int i) {
        HisLogger.d("HISMW@videojava", "set2PWBOffsetGreenDelta val:" + i);
        int i2 = -1;
        if (getHidlService()) {
            try {
                i2 = mVideoServer.set2PWBOffsetGreenDelta(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "set2PWBOffsetGreenDelta ret = " + i2);
        }
        return i2;
    }

    public static int get2PWBOffsetGreenDelta() {
        HisLogger.d("HISMW@videojava", "get2PWBOffsetGreenDelta");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.get2PWBOffsetGreenDelta(new IHisVideoApi.get2PWBOffsetGreenDeltaCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.211
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.get2PWBOffsetGreenDeltaCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "get2PWBOffsetGreenDelta ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int set2PWBOffsetBlueDelta(int i) {
        HisLogger.d("HISMW@videojava", "set2PWBOffsetBlueDelta val:" + i);
        int i2 = -1;
        if (getHidlService()) {
            try {
                i2 = mVideoServer.set2PWBOffsetBlueDelta(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "set2PWBOffsetBlueDelta ret = " + i2);
        }
        return i2;
    }

    public static int get2PWBOffsetBlueDelta() {
        HisLogger.d("HISMW@videojava", "get2PWBOffsetBlueDelta");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.get2PWBOffsetBlueDelta(new IHisVideoApi.get2PWBOffsetBlueDeltaCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.212
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.get2PWBOffsetBlueDeltaCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "get2PWBOffsetBlueDelta ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int set2PWBGainRedDelta(int i) {
        HisLogger.d("HISMW@videojava", "set2PWBGainRedDelta val:" + i);
        int i2 = -1;
        if (getHidlService()) {
            try {
                i2 = mVideoServer.set2PWBGainRedDelta(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "set2PWBGainRedDelta ret = " + i2);
        }
        return i2;
    }

    public static int get2PWBGainRedDelta() {
        HisLogger.d("HISMW@videojava", "get2PWBGainRedDelta");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.get2PWBGainRedDelta(new IHisVideoApi.get2PWBGainRedDeltaCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.213
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.get2PWBGainRedDeltaCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "get2PWBGainRedDelta ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int set2PWBGainGreenDelta(int i) {
        HisLogger.d("HISMW@videojava", "set2PWBGainGreenDelta val:" + i);
        int i2 = -1;
        if (getHidlService()) {
            try {
                i2 = mVideoServer.set2PWBGainGreenDelta(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "set2PWBGainGreenDelta ret = " + i2);
        }
        return i2;
    }

    public static int get2PWBGainGreenDelta() {
        HisLogger.d("HISMW@videojava", "get2PWBGainGreenDelta");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.get2PWBGainGreenDelta(new IHisVideoApi.get2PWBGainGreenDeltaCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.214
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.get2PWBGainGreenDeltaCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "get2PWBGainGreenDelta ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int set2PWBGainBlueDelta(int i) {
        HisLogger.d("HISMW@videojava", "set2PWBGainBlueDelta val:" + i);
        int i2 = -1;
        if (getHidlService()) {
            try {
                i2 = mVideoServer.set2PWBGainBlueDelta(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "set2PWBGainBlueDelta ret = " + i2);
        }
        return i2;
    }

    public static int get2PWBGainBlueDelta() {
        HisLogger.d("HISMW@videojava", "get2PWBGainBlueDelta");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.get2PWBGainBlueDelta(new IHisVideoApi.get2PWBGainBlueDeltaCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.215
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.get2PWBGainBlueDeltaCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "get2PWBGainBlueDelta ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int resetAllFac2PWhiteBalance() {
        HisLogger.d("HISMW@videojava", "resetAllFac2PWhiteBalance");
        int iResetAllFac2PWhiteBalance = -1;
        if (getHidlService()) {
            try {
                iResetAllFac2PWhiteBalance = mVideoServer.resetAllFac2PWhiteBalance(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "resetAllFac2PWhiteBalance ret = " + iResetAllFac2PWhiteBalance);
        }
        return iResetAllFac2PWhiteBalance;
    }

    public static ArrayList<Integer> getAutoGammaList() {
        HisLogger.d("HISMW@videojava", "getAutoGammaList");
        mReturnVal = -1;
        mItemData = null;
        if (getHidlService()) {
            try {
                mVideoServer.getItemData(0, new IHisVideoApi.getItemDataCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.218
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getItemDataCallback
                    public void onValues(int i, ArrayList<Integer> arrayList) {
                        int unused = HisVideoApi.mReturnVal = i;
                        ArrayList unused2 = HisVideoApi.mItemData = (ArrayList) arrayList.clone();
                        HisLogger.d("HISMW@videojava", "getAutoGammaList ret =" + HisVideoApi.mReturnVal + ",vec = " + HisVideoApi.mItemData);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mItemData;
    }

    public static String getAutoGammaListStr() {
        HisLogger.d("HISMW@videojava", "getAutoGammaListStr");
        mReturnVal = -1;
        mItemStr = "N/A";
        if (getHidlService()) {
            try {
                mVideoServer.getItemString(0, new IHisVideoApi.getItemStringCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.219
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getItemStringCallback
                    public void onValues(int i, String str) {
                        int unused = HisVideoApi.mReturnVal = i;
                        String unused2 = HisVideoApi.mItemStr = str;
                        HisLogger.d("HISMW@videojava", "getAutoGammaList ret =" + HisVideoApi.mReturnVal + ",str = " + HisVideoApi.mItemStr);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mItemStr;
    }

    public static String getAutoGammaStatus() {
        HisLogger.d("HISMW@videojava", "getAutoGammaStatus");
        mReturnVal = -1;
        mItemStr = "N/A";
        if (getHidlService()) {
            try {
                mVideoServer.getItemString(6, new IHisVideoApi.getItemStringCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.220
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getItemStringCallback
                    public void onValues(int i, String str) {
                        int unused = HisVideoApi.mReturnVal = i;
                        String unused2 = HisVideoApi.mItemStr = str;
                        HisLogger.d("HISMW@videojava", "getAutoGammaStatus ret =" + HisVideoApi.mReturnVal + ",str = " + HisVideoApi.mItemStr);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mItemStr;
    }

    public static int setAutoGammaTuning(int i) {
        HisLogger.d("HISMW@videojava", "setAutoGammaTuning val:" + i);
        int itemValue = -1;
        if (getHidlService()) {
            try {
                itemValue = mVideoServer.setItemValue(0, i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setAutoGammaTuning ret = " + itemValue);
        }
        return itemValue;
    }

    public static int getDolbyIQEnable() {
        HisLogger.d("HISMW@videojava", "getDolbyIqEnable");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getDolbyIQEnableFlag(new IHisVideoApi.getDolbyIQEnableFlagCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.244
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getDolbyIQEnableFlagCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getDolbyIQEnable ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setDolbyIQUserBinUpdate(String str) {
        HisLogger.d("HISMW@videojava", "setDolbyIQUserBinUpdate, path: '" + str + "'");
        int itemString = -1;
        if (getHidlService()) {
            try {
                itemString = mVideoServer.setItemString(4, str);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setDolbyIQUserBinUpdate ret = " + itemString);
        }
        return itemString;
    }

    public static String getDolbyIQUserBinInfo() {
        HisLogger.d("HISMW@videojava", "getDolbyIQUserBinInfo");
        mReturnVal = -1;
        mItemStr = "N/A";
        if (getHidlService()) {
            try {
                mVideoServer.getItemString(5, new IHisVideoApi.getItemStringCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.245
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getItemStringCallback
                    public void onValues(int i, String str) {
                        int unused = HisVideoApi.mReturnVal = i;
                        String unused2 = HisVideoApi.mItemStr = str;
                        HisLogger.d("HISMW@videojava", "getDolbyIQUserBinInfo ret =" + HisVideoApi.mReturnVal + ",str = " + HisVideoApi.mItemStr);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mItemStr;
    }

    public static int setDolbyIQUserBinEnable(int i) {
        HisLogger.d("HISMW@videojava", "setDolbyIQUserBinEnable val:" + i);
        int dolbyIQUserBinEnable = -1;
        if (getHidlService()) {
            try {
                dolbyIQUserBinEnable = mVideoServer.setDolbyIQUserBinEnable(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setDolbyIQUserBinEnable ret = " + dolbyIQUserBinEnable);
        }
        return dolbyIQUserBinEnable;
    }

    public static int getDolbyIQLux() {
        HisLogger.d("HISMW@videojava", "getDolbyIQLux");
        mReturnVal = -1;
        mGetParamVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getItemValue(23, new IHisVideoApi.getItemValueCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.246
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getItemValueCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getDolbyIQLux ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getDolbyIQUserBinEnable() {
        HisLogger.d("HISMW@videojava", "getDolbyIqEnable");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getDolbyIQUserBinEnable(new IHisVideoApi.getDolbyIQUserBinEnableCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.247
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getDolbyIQUserBinEnableCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getDolbyIQUserBinEnable ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setBlueMuteEnable(int i) {
        HisLogger.d("HISMW@videojava", "setBlueMuteEnable val: " + i);
        int i2 = 1 == i ? 255 : 0;
        int itemValue = -1;
        if (getHidlService()) {
            try {
                itemValue = mVideoServer.setItemValue(26, i2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setBlueMuteEnable, mute color: " + Integer.toHexString(i2) + ", ret = " + itemValue);
        }
        return itemValue;
    }

    public static int setPixelShiftEnable(int i) {
        HisLogger.d("HISMW@videojava", "setPixelShiftEnable val:" + i);
        int pixelShiftEnable = -1;
        if (getHidlService()) {
            try {
                pixelShiftEnable = mVideoServer.setPixelShiftEnable(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setPixelShiftEnable ret = " + pixelShiftEnable);
        }
        return pixelShiftEnable;
    }

    public static int getPixelShiftEnable() {
        HisLogger.d("HISMW@videojava", "getPixelShiftEnable");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getPixelShiftEnable(new IHisVideoApi.getPixelShiftEnableCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.276
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getPixelShiftEnableCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getPixelShiftEnable ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setOLEDLocalFrozenChkEnable(int i) {
        HisLogger.d("HISMW@videojava", "setOLEDLocalFrozenChkEnable val:" + i);
        int oLEDLocalFrozenChkEnable = -1;
        if (getHidlService()) {
            try {
                oLEDLocalFrozenChkEnable = mVideoServer.setOLEDLocalFrozenChkEnable(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setOLEDLocalFrozenChkEnable ret = " + oLEDLocalFrozenChkEnable);
        }
        return oLEDLocalFrozenChkEnable;
    }

    public static int getOLEDLocalFrozenChkEnable() {
        HisLogger.d("HISMW@videojava", "getOLEDLocalFrozenChkEnable");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getOLEDLocalFrozenChkEnable(new IHisVideoApi.getOLEDLocalFrozenChkEnableCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.280
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getOLEDLocalFrozenChkEnableCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getOLEDLocalFrozenChkEnable ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setOLEDLEAEnable(int i) {
        HisLogger.d("HISMW@videojava", "setOLEDLEAEnable val:" + i);
        int oLEDLEAEnable = -1;
        if (getHidlService()) {
            try {
                oLEDLEAEnable = mVideoServer.setOLEDLEAEnable(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setOLEDLEAEnable ret = " + oLEDLEAEnable);
        }
        return oLEDLEAEnable;
    }

    public static int getOLEDLEAEnable() {
        HisLogger.d("HISMW@videojava", "getOLEDLEAEnable");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getOLEDLEAEnable(new IHisVideoApi.getOLEDLEAEnableCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.282
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getOLEDLEAEnableCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getOLEDLEAEnable ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setOLEDHDREnable(int i) {
        HisLogger.d("HISMW@videojava", "setOLEDHDREnable val:" + i);
        int oLEDHDREnable = -1;
        if (getHidlService()) {
            try {
                oLEDHDREnable = mVideoServer.setOLEDHDREnable(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setOLEDHDREnable ret = " + oLEDHDREnable);
        }
        return oLEDHDREnable;
    }

    public static int getOLEDHDREnable() {
        HisLogger.d("HISMW@videojava", "getOLEDHDREnable");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getOLEDHDREnable(new IHisVideoApi.getOLEDHDREnableCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.284
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getOLEDHDREnableCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getOLEDHDREnable ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setForce120HzEnable(int i) {
        HisLogger.d("HISMW@videojava", "setForce120HzEnable val:" + i);
        int itemValue = -1;
        if (getHidlService()) {
            try {
                itemValue = mVideoServer.setItemValue(54, i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setForce120HzEnable ret = " + itemValue);
        }
        return itemValue;
    }

    public static int setOLEDTPCEnable(int i) {
        HisLogger.d("HISMW@videojava", "setOLEDTPCEnable val:" + i);
        int oLEDTPCEnable = -1;
        if (getHidlService()) {
            try {
                oLEDTPCEnable = mVideoServer.setOLEDTPCEnable(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setOLEDTPCEnable ret = " + oLEDTPCEnable);
        }
        return oLEDTPCEnable;
    }

    public static int getOLEDTPCEnable() {
        HisLogger.d("HISMW@videojava", "getOLEDTPCEnable");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getOLEDTPCEnable(new IHisVideoApi.getOLEDTPCEnableCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.286
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getOLEDTPCEnableCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getOLEDTPCEnable ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int setLDTestEnable(int i) {
        HisLogger.d("HISMW@videojava", "setLDTestEnable val:" + i);
        int lDTestEnable = -1;
        if (getHidlService()) {
            try {
                lDTestEnable = mVideoServer.setLDTestEnable(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@videojava", "setLDTestEnable ret = " + lDTestEnable);
        }
        return lDTestEnable;
    }

    public static int getFRCVersion() {
        HisLogger.d("HISMW@videojava", "getFRCVersion");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mVideoServer.getFRCVersion(new IHisVideoApi.getFRCVersionCallback() { // from class: com.hisense.avmiddleware.HisVideoApi.287
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFRCVersionCallback
                    public void onValues(int i, int i2) {
                        int unused = HisVideoApi.mReturnVal = i;
                        int unused2 = HisVideoApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@videojava", "getFRCVersion ret =" + HisVideoApi.mReturnVal + ",value = " + HisVideoApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }
}
