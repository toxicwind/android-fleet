package com.hisense.tvs;

/* JADX INFO: loaded from: classes.dex */
public class FacTvsFunctionUtil {
    public static int FAC_TVS_TUNE_CHANNEL_DOWN;
    public static int FAC_TVS_TUNE_CHANNEL_UP;
    public static int FAC_TVS_TUNE_TYPE_BS;
    public static int FAC_TVS_TUNE_TYPE_BS4K;
    public static int FAC_TVS_TUNE_TYPE_CS;
    public static int FAC_TVS_TUNE_TYPE_TDJ15;
    public static int FAC_TVS_TUNE_TYPE_TDJ62;
    public static int TVS_ANTENNA_POWER_MODE_AUTO;
    public static int TVS_ANTENNA_POWER_MODE_OFF;
    public static int TVS_SAVING_MODE_1;
    public static int TVS_SAVING_MODE_2;
    public static int TVS_SAVING_MODE_STANDARD;
    public static final int TVS_SOURCE_AV;
    public static final int TVS_SOURCE_HDMI;
    public static final int TVS_VALUE_ALL_OFF;
    public static final int TVS_VALUE_AUTO;
    public static final int DTCPIP_KEY_TYPE = CESIConstants.getValue("FactoryKeyDataType_e", "DTCP_IP");
    public static final int MARLIN_KEY_TYPE = CESIConstants.getValue("FactoryKeyDataType_e", "MARLIN");
    public static final int PVR000_KEY_TYPE = CESIConstants.getValue("FactoryKeyDataType_e", "PVR");
    public static final int SQV000_KEY_TYPE = CESIConstants.getValue("FactoryKeyDataType_e", "SQV");

    static {
        CESIConstants.getValue("FactoryKeyDataType_e", "TRMP");
        FAC_TVS_TUNE_CHANNEL_DOWN = CESIConstants.getValue("FactoryTuneType_e", "NEXTDOWN");
        FAC_TVS_TUNE_CHANNEL_UP = CESIConstants.getValue("FactoryTuneType_e", "NEXTUP");
        FAC_TVS_TUNE_TYPE_TDJ15 = CESIConstants.getValue("FactoryTuneType_e", "POS1");
        FAC_TVS_TUNE_TYPE_TDJ62 = CESIConstants.getValue("FactoryTuneType_e", "POS2");
        FAC_TVS_TUNE_TYPE_BS = CESIConstants.getValue("FactoryTuneType_e", "POS3");
        FAC_TVS_TUNE_TYPE_CS = CESIConstants.getValue("FactoryTuneType_e", "POS4");
        FAC_TVS_TUNE_TYPE_BS4K = CESIConstants.getValue("FactoryTuneType_e", "POS5");
        CESIConstants.getValue("UsbPort_e", "GENERAL1");
        CESIConstants.getValue("UsbPort_e", "GENERAL2");
        CESIConstants.getValue("UsbPort_e", "RECORDING1");
        TVS_ANTENNA_POWER_MODE_AUTO = CESIConstants.getValue("AntennaPowerMode_e", "AUTO");
        TVS_ANTENNA_POWER_MODE_OFF = CESIConstants.getValue("AntennaPowerMode_e", "OFF");
        CESIConstants.getValue("AntennaPowerMode_e", "ON");
        TVS_SAVING_MODE_1 = CESIConstants.getValue("PropertyValue_e", "POWERSAVE_MODE_1");
        TVS_SAVING_MODE_2 = CESIConstants.getValue("PropertyValue_e", "POWERSAVE_MODE_2");
        TVS_SAVING_MODE_STANDARD = CESIConstants.getValue("PropertyValue_e", "POWERSAVE_STANDARD");
        TVS_VALUE_ALL_OFF = CESIConstants.getValue("PropertyValue_e", "VALUE_ALL_OFF");
        TVS_VALUE_AUTO = CESIConstants.getValue("PropertyValue_e", "VALUE_AUTO");
        TVS_SOURCE_HDMI = CESIConstants.getValue("ExternalInputType_e", "HDMI");
        TVS_SOURCE_AV = CESIConstants.getValue("ExternalInputType_e", "VIDEO");
    }

    public static void initData() {
        FAC_TVS_TUNE_CHANNEL_DOWN = CESIConstants.getValue("FactoryTuneType_e", "NEXTDOWN");
        FAC_TVS_TUNE_CHANNEL_UP = CESIConstants.getValue("FactoryTuneType_e", "NEXTUP");
        FAC_TVS_TUNE_TYPE_TDJ15 = CESIConstants.getValue("FactoryTuneType_e", "POS1");
        FAC_TVS_TUNE_TYPE_TDJ62 = CESIConstants.getValue("FactoryTuneType_e", "POS2");
        FAC_TVS_TUNE_TYPE_BS = CESIConstants.getValue("FactoryTuneType_e", "POS3");
        FAC_TVS_TUNE_TYPE_CS = CESIConstants.getValue("FactoryTuneType_e", "POS4");
        FAC_TVS_TUNE_TYPE_BS4K = CESIConstants.getValue("FactoryTuneType_e", "POS5");
        CESIConstants.getValue("UsbPort_e", "GENERAL1");
        CESIConstants.getValue("UsbPort_e", "GENERAL2");
        CESIConstants.getValue("UsbPort_e", "RECORDING1");
        TVS_ANTENNA_POWER_MODE_AUTO = CESIConstants.getValue("AntennaPowerMode_e", "AUTO");
        TVS_ANTENNA_POWER_MODE_OFF = CESIConstants.getValue("AntennaPowerMode_e", "OFF");
        CESIConstants.getValue("AntennaPowerMode_e", "ON");
        TVS_SAVING_MODE_1 = CESIConstants.getValue("PropertyValue_e", "POWERSAVE_MODE_1");
        TVS_SAVING_MODE_2 = CESIConstants.getValue("PropertyValue_e", "POWERSAVE_MODE_2");
        TVS_SAVING_MODE_STANDARD = CESIConstants.getValue("PropertyValue_e", "POWERSAVE_STANDARD");
    }
}
