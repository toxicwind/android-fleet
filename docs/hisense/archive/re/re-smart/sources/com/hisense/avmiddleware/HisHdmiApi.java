package com.hisense.avmiddleware;

import b.a.a.a.a;
import java.util.ArrayList;
import vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi;

/* JADX INFO: loaded from: classes2.dex */
public class HisHdmiApi {
    public static final int HDD_HDMI_ENABLE_VRR = 1030;
    public static final int HDD_HDMI_HDMI_TEST = 1024;
    public static final int HDD_HDMI_NOW_CONFIG_PARA = 1029;
    public static final int HDD_HDMI_NOW_CONFIG_TIMING = 1028;
    public static final int HDD_HDMI_NOW_SAVE_REIGSTER = 1027;
    public static final int HDD_HDMI_NOW_TRIGER_PATTERN = 1026;
    public static final int HDD_HDMI_NOW_VDE_HDE = 1025;
    public static final int HDD_HDMI_S_PORT_EDID_TYPE = 1031;
    public static final int HD_EARC_AUDIO_STATUS = 514;
    public static final int HD_EARC_ENABLE = 512;
    public static final int HD_EARC_STATUS = 513;
    public static final int HD_HDMI_EDID_MODE = 87;
    public static final int HD_HDMI_G_PORT_EDID_TYPE = 86;
    public static final int HD_HDMI_LIST_HDMI_INFO = 1281;
    public static final int HD_HDMI_LIST_TEST = 1280;
    public static final int HD_HDMI_NOW_AVMUTE_STATUS = 12;
    public static final int HD_HDMI_NOW_BCH_ERROR = 8;
    public static final int HD_HDMI_NOW_COLOR_DEPTH = 11;
    public static final int HD_HDMI_NOW_COLOR_SPACE = 10;
    public static final int HD_HDMI_NOW_HDCP_STATUS = 9;
    public static final int HD_HDMI_NOW_HPD_STATUS = 3;
    public static final int HD_HDMI_NOW_PORT5V_STATUS = 2;
    public static final int HD_HDMI_NOW_SIGNAL_MODE = 7;
    public static final int HD_HDMI_NOW_SIGNAL_TYPE = 6;
    public static final int HD_HDMI_NOW_SOURCE = 1;
    public static final int HD_HDMI_NOW_TMDS_STATUS = 4;
    public static final int HD_HDMI_NOW_TMDS_VALUE = 5;
    public static final int HD_HDMI_PORT1_5V_STATUS = 80;
    public static final int HD_HDMI_PORT2_5V_STATUS = 81;
    public static final int HD_HDMI_PORT3_5V_STATUS = 82;
    public static final int HD_HDMI_PORT4_5V_STATUS = 83;
    public static final int HD_HDMI_PORT5_5V_STATUS = 84;
    public static final int HD_HDMI_PORT6_5V_STATUS = 85;
    public static final int HD_HDMI_STR_CEC_CMD = 1537;
    public static final int HD_HDMI_STR_TEST = 1536;
    public static final int HD_HDMI_TEST = 0;
    public static final int HD_IDX_UI_VRR_STATUS_1PORT = 257;
    public static final int HD_IDX_UI_VRR_STATUS_2PORT = 258;
    public static final int HD_IDX_UI_VRR_STATUS_3PORT = 259;
    public static final int HD_IDX_UI_VRR_STATUS_4PORT = 260;
    public static final int HD_ITEM_MAX_CMD = 768;
    public static final int HIDL_INDEX_0x100 = 256;
    public static final int HIDL_INDEX_0x200 = 512;
    public static final int HIDL_INDEX_0x300 = 768;
    public static final int HIDL_INDEX_0x50 = 80;
    public static final String TAG = "HISMW@hdmi_java";
    public static ArrayList<Integer> mGetList32Val = null;
    public static ArrayList<Byte> mGetList8Val = null;
    public static int mGetParamSubVal = -1;
    public static int mGetParamVal = -1;
    public static HisHdmiInfo mHdmiInfo = new HisHdmiInfo();
    public static IHisHdmiApi mHdmiServer = null;
    public static int mReturnVal = -1;

    public static class HisHdmiInfo {
        public int i325VStatus;
        public int i32AVmute;
        public int i32ClrDepth;
        public int i32DSCEnable;
        public int i32FRLRate;
        public int i32HDCP14Check;
        public int i32HDCP14Exist;
        public int i32HDCP22Check;
        public int i32HDCP22Exist;
        public int i32HTotal;
        public int i32Hdmi21;
        public int i32HdmiMode;
        public int i32HdmiPlug;
        public int i32Interlaced;
        public int i32PixClk;
        public int i32TMDSClkKhz;
        public int i32VTotal;
        public int i32clrspc;
        public int i32range;
    }

    public static int VolumeDown() {
        return sendCECCommand("volume-");
    }

    public static int VolumeUp() {
        return sendCECCommand("volume+");
    }

    public static ArrayList<Byte> getCurrentEdidByport() {
        HisLogger.d(TAG, "getCurrentEdidByport");
        mGetList8Val = null;
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mHdmiServer.getCurrentEdidByport(new IHisHdmiApi.getCurrentEdidByportCallback() { // from class: com.hisense.avmiddleware.HisHdmiApi.13
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi.getCurrentEdidByportCallback
                    public void onValues(int i, ArrayList<Byte> arrayList) {
                        HisHdmiApi.mReturnVal = i;
                        HisHdmiApi.mGetList8Val = (ArrayList) arrayList.clone();
                        StringBuilder sbA = a.a("getCurrentEdidByport ret =");
                        sbA.append(HisHdmiApi.mReturnVal);
                        sbA.append(",Edid = ");
                        sbA.append(HisHdmiApi.mGetList8Val);
                        HisLogger.d(HisHdmiApi.TAG, sbA.toString());
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetList8Val;
    }

    public static int getEarcStatus() {
        HisLogger.d(TAG, "getEarcStatus");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mHdmiServer.getItemValue(513, new IHisHdmiApi.getItemValueCallback() { // from class: com.hisense.avmiddleware.HisHdmiApi.17
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi.getItemValueCallback
                    public void onValues(int i, int i2) {
                        HisHdmiApi.mReturnVal = i;
                        HisHdmiApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getEarcStatus ret =");
                        sbA.append(HisHdmiApi.mReturnVal);
                        sbA.append(",EarcStatus = ");
                        a.c(sbA, HisHdmiApi.mGetParamVal, HisHdmiApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getEdidIndex() {
        HisLogger.d(TAG, "getEdidIndex");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mHdmiServer.getItemValueByArg1(86, 255, new IHisHdmiApi.getItemValueByArg1Callback() { // from class: com.hisense.avmiddleware.HisHdmiApi.14
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi.getItemValueByArg1Callback
                    public void onValues(int i, int i2) {
                        HisHdmiApi.mReturnVal = i;
                        HisHdmiApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getHdmiEdidIndex ret =");
                        sbA.append(HisHdmiApi.mReturnVal);
                        sbA.append(",EdidIndex = ");
                        a.c(sbA, HisHdmiApi.mGetParamVal, HisHdmiApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getEdidIndexByportId(int i) {
        a.c("getEdidIndexByportId = ", i, TAG);
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mHdmiServer.getItemValueByArg1(86, i, new IHisHdmiApi.getItemValueByArg1Callback() { // from class: com.hisense.avmiddleware.HisHdmiApi.15
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi.getItemValueByArg1Callback
                    public void onValues(int i2, int i3) {
                        HisHdmiApi.mReturnVal = i2;
                        HisHdmiApi.mGetParamVal = i3;
                        StringBuilder sbA = a.a("getHdmiEdidIndex ret =");
                        sbA.append(HisHdmiApi.mReturnVal);
                        sbA.append(",EdidIndex = ");
                        a.c(sbA, HisHdmiApi.mGetParamVal, HisHdmiApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getHdmi5VStatus() {
        HisLogger.d(TAG, "getHdmi5VStatus");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mHdmiServer.getItemValue(2, new IHisHdmiApi.getItemValueCallback() { // from class: com.hisense.avmiddleware.HisHdmiApi.1
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi.getItemValueCallback
                    public void onValues(int i, int i2) {
                        HisHdmiApi.mReturnVal = i;
                        HisHdmiApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getHdmi5VStatus ret =");
                        sbA.append(HisHdmiApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisHdmiApi.mGetParamVal, HisHdmiApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getHdmi5VStatusByPort(int i) {
        a.c("getHdmi5VStatusByPort = ", i, TAG);
        mGetParamVal = -1;
        mReturnVal = -1;
        int i2 = (i - 1) + 80;
        if (getHidlService()) {
            try {
                mHdmiServer.getItemValue(i2, new IHisHdmiApi.getItemValueCallback() { // from class: com.hisense.avmiddleware.HisHdmiApi.18
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi.getItemValueCallback
                    public void onValues(int i3, int i4) {
                        HisHdmiApi.mReturnVal = i3;
                        HisHdmiApi.mGetParamVal = i4;
                        StringBuilder sbA = a.a("getHdmi5VStatusByPort ret =");
                        sbA.append(HisHdmiApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisHdmiApi.mGetParamVal, HisHdmiApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getHdmiAVmuteStatus() {
        HisLogger.d(TAG, "getHdmiAVmuteStatus");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mHdmiServer.getItemValue(12, new IHisHdmiApi.getItemValueCallback() { // from class: com.hisense.avmiddleware.HisHdmiApi.12
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi.getItemValueCallback
                    public void onValues(int i, int i2) {
                        HisHdmiApi.mReturnVal = i;
                        HisHdmiApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getHdmiAVmuteStatus ret =");
                        sbA.append(HisHdmiApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisHdmiApi.mGetParamVal, HisHdmiApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getHdmiBCHError() {
        HisLogger.d(TAG, "getHdmiBCHError");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mHdmiServer.getItemValue(8, new IHisHdmiApi.getItemValueCallback() { // from class: com.hisense.avmiddleware.HisHdmiApi.8
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi.getItemValueCallback
                    public void onValues(int i, int i2) {
                        HisHdmiApi.mReturnVal = i;
                        HisHdmiApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getHdmiBCHError ret =");
                        sbA.append(HisHdmiApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisHdmiApi.mGetParamVal, HisHdmiApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getHdmiColorDepth() {
        HisLogger.d(TAG, "getHdmiColorDepth");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mHdmiServer.getItemValue(11, new IHisHdmiApi.getItemValueCallback() { // from class: com.hisense.avmiddleware.HisHdmiApi.11
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi.getItemValueCallback
                    public void onValues(int i, int i2) {
                        HisHdmiApi.mReturnVal = i;
                        HisHdmiApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getHdmiColorDepth ret =");
                        sbA.append(HisHdmiApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisHdmiApi.mGetParamVal, HisHdmiApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getHdmiColorSpace() {
        HisLogger.d(TAG, "getHdmiColorSpace");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mHdmiServer.getItemValue(10, new IHisHdmiApi.getItemValueCallback() { // from class: com.hisense.avmiddleware.HisHdmiApi.10
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi.getItemValueCallback
                    public void onValues(int i, int i2) {
                        HisHdmiApi.mReturnVal = i;
                        HisHdmiApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getHdmiColorSpace ret =");
                        sbA.append(HisHdmiApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisHdmiApi.mGetParamVal, HisHdmiApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getHdmiHDCPCheckResult() {
        HisLogger.d(TAG, "getHdmiHDCPCheckResult");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mHdmiServer.getItemValue(9, new IHisHdmiApi.getItemValueCallback() { // from class: com.hisense.avmiddleware.HisHdmiApi.9
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi.getItemValueCallback
                    public void onValues(int i, int i2) {
                        HisHdmiApi.mReturnVal = i;
                        HisHdmiApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getHdmiHDCPCheckResult ret =");
                        sbA.append(HisHdmiApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisHdmiApi.mGetParamVal, HisHdmiApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getHdmiHDEAndVDE(ArrayList<Integer> arrayList) {
        HisLogger.d(TAG, "getHdmiHDEAndVDE");
        mGetList32Val = null;
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mHdmiServer.getItem2Value(1025, new IHisHdmiApi.getItem2ValueCallback() { // from class: com.hisense.avmiddleware.HisHdmiApi.6
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi.getItem2ValueCallback
                    public void onValues(int i, ArrayList<Integer> arrayList2) {
                        HisHdmiApi.mReturnVal = i;
                        HisHdmiApi.mGetList32Val = (ArrayList) arrayList2.clone();
                        StringBuilder sbA = a.a("getHdmiHDEAndVDE ret =");
                        sbA.append(HisHdmiApi.mReturnVal);
                        sbA.append(",HVDE = ");
                        sbA.append(HisHdmiApi.mGetList32Val);
                        HisLogger.d(HisHdmiApi.TAG, sbA.toString());
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        ArrayList<Integer> arrayList2 = mGetList32Val;
        return mReturnVal;
    }

    public static int getHdmiHPDStatus() {
        HisLogger.d(TAG, "getHdmiHPDStatus");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mHdmiServer.getItemValue(3, new IHisHdmiApi.getItemValueCallback() { // from class: com.hisense.avmiddleware.HisHdmiApi.2
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi.getItemValueCallback
                    public void onValues(int i, int i2) {
                        HisHdmiApi.mReturnVal = i;
                        HisHdmiApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getHdmiHPDStatus ret =");
                        sbA.append(HisHdmiApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisHdmiApi.mGetParamVal, HisHdmiApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static HisHdmiInfo getHdmiInfo() {
        HisLogger.d(TAG, "getHdmiInfo");
        mGetList32Val = null;
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mHdmiServer.getItemData(HD_HDMI_LIST_HDMI_INFO, new IHisHdmiApi.getItemDataCallback() { // from class: com.hisense.avmiddleware.HisHdmiApi.16
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi.getItemDataCallback
                    public void onValues(int i, ArrayList<Integer> arrayList) {
                        HisHdmiApi.mReturnVal = i;
                        HisHdmiApi.mGetList32Val = (ArrayList) arrayList.clone();
                        StringBuilder sbA = a.a("getHdmiInfo ret =");
                        sbA.append(HisHdmiApi.mReturnVal);
                        sbA.append(",Edid = ");
                        sbA.append(HisHdmiApi.mGetList32Val);
                        HisLogger.d(HisHdmiApi.TAG, sbA.toString());
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (mHdmiInfo != null) {
            ArrayList<Integer> arrayList = mGetList32Val;
            Integer[] numArr = (Integer[]) arrayList.toArray(new Integer[arrayList.size()]);
            mHdmiInfo.i32HTotal = numArr[0].intValue();
            mHdmiInfo.i32VTotal = numArr[1].intValue();
            mHdmiInfo.i32PixClk = numArr[2].intValue();
            mHdmiInfo.i32ClrDepth = numArr[3].intValue();
            mHdmiInfo.i32Interlaced = numArr[4].intValue();
            mHdmiInfo.i32HdmiMode = numArr[5].intValue();
            mHdmiInfo.i32AVmute = numArr[6].intValue();
            mHdmiInfo.i325VStatus = numArr[7].intValue();
            mHdmiInfo.i32HdmiPlug = numArr[8].intValue();
            mHdmiInfo.i32Hdmi21 = numArr[9].intValue();
            mHdmiInfo.i32DSCEnable = numArr[10].intValue();
            mHdmiInfo.i32HDCP14Exist = numArr[11].intValue();
            mHdmiInfo.i32HDCP22Exist = numArr[12].intValue();
            mHdmiInfo.i32HDCP14Check = numArr[13].intValue();
            mHdmiInfo.i32HDCP22Check = numArr[14].intValue();
            mHdmiInfo.i32TMDSClkKhz = numArr[15].intValue();
            mHdmiInfo.i32clrspc = numArr[16].intValue();
            mHdmiInfo.i32range = numArr[17].intValue();
            mHdmiInfo.i32FRLRate = numArr[18].intValue();
        }
        return mHdmiInfo;
    }

    public static int getHdmiMode() {
        HisLogger.d(TAG, "getHdmiMode");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mHdmiServer.getItemValue(7, new IHisHdmiApi.getItemValueCallback() { // from class: com.hisense.avmiddleware.HisHdmiApi.7
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi.getItemValueCallback
                    public void onValues(int i, int i2) {
                        HisHdmiApi.mReturnVal = i;
                        HisHdmiApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getHdmiMode ret =");
                        sbA.append(HisHdmiApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisHdmiApi.mGetParamVal, HisHdmiApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getHdmiTMDSStatus() {
        HisLogger.d(TAG, "getHdmiTMDSStatus");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mHdmiServer.getItemValue(4, new IHisHdmiApi.getItemValueCallback() { // from class: com.hisense.avmiddleware.HisHdmiApi.3
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi.getItemValueCallback
                    public void onValues(int i, int i2) {
                        HisHdmiApi.mReturnVal = i;
                        HisHdmiApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getHdmiHPDStatus ret =");
                        sbA.append(HisHdmiApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisHdmiApi.mGetParamVal, HisHdmiApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getHdmiTMDSValue() {
        HisLogger.d(TAG, "getHdmiTMDSValue");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mHdmiServer.getItemValue(5, new IHisHdmiApi.getItemValueCallback() { // from class: com.hisense.avmiddleware.HisHdmiApi.4
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi.getItemValueCallback
                    public void onValues(int i, int i2) {
                        HisHdmiApi.mReturnVal = i;
                        HisHdmiApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getHdmiTMDSValue ret =");
                        sbA.append(HisHdmiApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisHdmiApi.mGetParamVal, HisHdmiApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getHdmiType() {
        HisLogger.d(TAG, "getHdmiType");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mHdmiServer.getItemValue(6, new IHisHdmiApi.getItemValueCallback() { // from class: com.hisense.avmiddleware.HisHdmiApi.5
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi.getItemValueCallback
                    public void onValues(int i, int i2) {
                        HisHdmiApi.mReturnVal = i;
                        HisHdmiApi.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getHdmiType ret =");
                        sbA.append(HisHdmiApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisHdmiApi.mGetParamVal, HisHdmiApi.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getHdmiVrrStatusByPort(int i) {
        a.c("getHdmiVrrStatusByPort = ", i, TAG);
        mGetParamVal = -1;
        mReturnVal = -1;
        int i2 = (i - 1) + 257;
        if (getHidlService()) {
            try {
                mHdmiServer.getItemValue(i2, new IHisHdmiApi.getItemValueCallback() { // from class: com.hisense.avmiddleware.HisHdmiApi.19
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi.getItemValueCallback
                    public void onValues(int i3, int i4) {
                        HisHdmiApi.mReturnVal = i3;
                        HisHdmiApi.mGetParamVal = i4;
                        StringBuilder sbA = a.a("getVRRFrameRate ret =");
                        sbA.append(HisHdmiApi.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, HisHdmiApi.mGetParamVal, HisHdmiApi.TAG);
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
            mHdmiServer = IHisHdmiApi.getService();
            return mHdmiServer != null;
        } catch (Exception e2) {
            HisLogger.e(TAG, "get Hdmi HIDL Service exception" + e2);
            return false;
        }
    }

    public static int saveCurrentRegister(int i) {
        HisLogger.d(TAG, "saveCurrentRegister Module = " + i);
        int itemValue = -1;
        if (getHidlService()) {
            try {
                itemValue = mHdmiServer.setItemValue(HDD_HDMI_NOW_SAVE_REIGSTER, i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("saveCurrentRegister ret = ", itemValue, TAG);
        }
        return itemValue;
    }

    public static int sendCECCommand(String str) {
        HisLogger.d(TAG, "sendCECCommand cmd:" + str);
        int itemString = -1;
        if (getHidlService()) {
            try {
                itemString = mHdmiServer.setItemString(HD_HDMI_STR_CEC_CMD, str);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("sendCECCommand ret = ", itemString, TAG);
        }
        return itemString;
    }

    public static int setAudioStatusOnEarc(int i) {
        HisLogger.d(TAG, "setAudioStatusOnEarc- bAudSta:" + i);
        int itemValue = -1;
        if (getHidlService()) {
            try {
                itemValue = mHdmiServer.setItemValue(HD_EARC_AUDIO_STATUS, i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setAudioStatusOnEarc ret = ", itemValue, TAG);
        }
        return itemValue;
    }

    public static int setClkStableCounter(int i, int i2) {
        HisLogger.d(TAG, "setClkStableCounter- stb_threshold:" + i + ",stb_counter:" + i2);
        int item2Value = -1;
        if (getHidlService()) {
            try {
                item2Value = mHdmiServer.setItem2Value(HDD_HDMI_NOW_CONFIG_PARA, i, i2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setClkStableCounter ret = ", item2Value, TAG);
        }
        return item2Value;
    }

    public static int setCurrentEdidByport() {
        HisLogger.d(TAG, "setCurrentEdidByport");
        int currentEdidByport = -1;
        if (getHidlService()) {
            try {
                currentEdidByport = mHdmiServer.setCurrentEdidByport();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setCurrentEdidByport ret = ", currentEdidByport, TAG);
        }
        return currentEdidByport;
    }

    public static int setEarcEnable(int i) {
        HisLogger.d(TAG, "setEarcEnable:" + i);
        int itemValue = -1;
        if (getHidlService()) {
            try {
                itemValue = mHdmiServer.setItemValue(512, i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setEarcEnable ret = ", itemValue, TAG);
        }
        return itemValue;
    }

    public static int setEdidIndex(int i) {
        HisLogger.d(TAG, "setEdidIndex");
        int item2Value = -1;
        if (getHidlService()) {
            try {
                item2Value = mHdmiServer.setItem2Value(HDD_HDMI_S_PORT_EDID_TYPE, 255, i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setHdmiEdidIndex ret = ", item2Value, TAG);
        }
        return item2Value;
    }

    public static int setEdidIndexByportId(int i, int i2) {
        HisLogger.d(TAG, "setEdidIndexByportId idx=" + i2 + ",portid = " + i);
        int item2Value = -1;
        if (getHidlService()) {
            try {
                item2Value = mHdmiServer.setItem2Value(HDD_HDMI_S_PORT_EDID_TYPE, i, i2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setHdmiEdidIndex ret = ", item2Value, TAG);
        }
        return item2Value;
    }

    public static int setEdidVrrType(int i, int i2) {
        HisLogger.d(TAG, "setEdidVrrType- vrrId:" + i + ",value:" + i2);
        int item2Value = -1;
        if (getHidlService()) {
            try {
                item2Value = mHdmiServer.setItem2Value(HDD_HDMI_ENABLE_VRR, i, i2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setEdidVrrType ret = ", item2Value, TAG);
        }
        return item2Value;
    }

    public static int setHdmiTimingUp(int i, int i2, int i3, int i4) {
        StringBuilder sbB = a.b("setHdmiTimingUp Module = ", i, "UpOrDwon= ", i2, "Deleytmds = ");
        sbB.append(i3);
        sbB.append("DelayHPD = ");
        sbB.append(i4);
        HisLogger.d(TAG, sbB.toString());
        int item2Value = -1;
        if (getHidlService()) {
            try {
                item2Value = mHdmiServer.setItem2Value(1028, i, i2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setHdmiTimingUp ret = ", item2Value, TAG);
        }
        return item2Value;
    }

    public static int setHdmiVrrStatusByPort(int i, int i2) {
        HisLogger.d(TAG, "setHdmiVrrStatusByPort = " + i + ",bEnable = " + i2);
        int i3 = i + (-1) + 257;
        int itemValue = -1;
        if (getHidlService()) {
            try {
                itemValue = mHdmiServer.setItemValue(i3, i2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setHdmiVrrStatusByPort ret = ", itemValue, TAG);
        }
        return itemValue;
    }

    public static int triggerPattern(int i) {
        HisLogger.d(TAG, "triggerPattern PTIndex = " + i);
        int itemValue = -1;
        if (getHidlService()) {
            try {
                itemValue = mHdmiServer.setItemValue(1026, i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("triggerPattern ret = ", itemValue, TAG);
        }
        return itemValue;
    }
}
