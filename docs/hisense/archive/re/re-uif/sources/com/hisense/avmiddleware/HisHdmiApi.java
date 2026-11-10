package com.hisense.avmiddleware;

import vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi;

/* JADX INFO: loaded from: classes.dex */
public class HisHdmiApi {
    private static int mGetParamVal = -1;
    private static IHisHdmiApi mHdmiServer = null;
    private static int mReturnVal = -1;

    public static boolean getHidlService() {
        try {
            IHisHdmiApi service = IHisHdmiApi.getService();
            mHdmiServer = service;
            return service != null;
        } catch (Exception e) {
            HisLogger.e("HISMW@hdmi_java", "get Hdmi HIDL Service exception" + e);
            return false;
        }
    }

    public static int setEarcEnable(int i) {
        HisLogger.d("HISMW@hdmi_java", "setEarcEnable:" + i);
        int itemValue = -1;
        if (getHidlService()) {
            try {
                itemValue = mHdmiServer.setItemValue(512, i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@hdmi_java", "setEarcEnable ret = " + itemValue);
        }
        return itemValue;
    }

    public static int getEarcStatus() {
        HisLogger.d("HISMW@hdmi_java", "getEarcStatus");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mHdmiServer.getItemValue(513, new IHisHdmiApi.getItemValueCallback() { // from class: com.hisense.avmiddleware.HisHdmiApi.17
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi.getItemValueCallback
                    public void onValues(int i, int i2) {
                        int unused = HisHdmiApi.mReturnVal = i;
                        int unused2 = HisHdmiApi.mGetParamVal = i2;
                        HisLogger.d("HISMW@hdmi_java", "getEarcStatus ret =" + HisHdmiApi.mReturnVal + ",EarcStatus = " + HisHdmiApi.mGetParamVal);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGetParamVal;
    }
}
