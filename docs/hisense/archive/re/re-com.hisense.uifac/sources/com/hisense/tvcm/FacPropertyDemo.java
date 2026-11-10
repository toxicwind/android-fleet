package com.hisense.tvcm;

import android.os.SystemProperties;
import android.util.Log;
import com.hisense.uifac.util.FacFileUtils;
import com.mediatek.twoworlds.tv.common.MtkTvConfigTypeBase;
import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;
import com.mediatek.twoworlds.tv.model.MtkTvRatingConvert2Goo;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class FacPropertyDemo {
    public static boolean HIFAC_4K_VIDEO_SUPPORT = false;
    public static boolean HIFAC_ATSC30_SUPPORT = false;
    public static boolean HIFAC_AUDIO_MORE_CURVE = false;
    public static boolean HIFAC_AUDIO_MORE_EQUALIZER = false;
    public static boolean HIFAC_AVC_SUPPORT = true;
    public static String HIFAC_BRAND = "";
    public static int HIFAC_KEYBOARD_NUM = 5;
    public static boolean HIFAC_LASER_PROJECTOR = false;
    public static int HIFAC_MAX_BALANCE = 50;
    public static int HIFAC_MAX_BALANCE_LIKE_850 = 4;
    public static int HIFAC_MAX_DDRSSC_MODU = 60;
    public static int HIFAC_MAX_DDRSSC_SPAN = 100;
    public static int HIFAC_MAX_DOUBLE_AMP_BALANCE = 4;
    public static int HIFAC_MAX_FREQ = 20000;
    public static int HIFAC_MAX_GAIN = 360;
    public static int HIFAC_MAX_HPF_FREQUENCY = 300;
    public static int HIFAC_MAX_MULTI_AMP_BALANCE = 6;
    public static int HIFAC_MAX_Q = 16;
    public static int HIFAC_MAX_SHARPNESS_UI = 20;
    public static int HIFAC_MAX_SOC_DRC = 0;
    public static int HIFAC_MAX_STEP = 1000;
    public static int HIFAC_MAX_TWEETER_BALANCE = 3;
    public static int HIFAC_MAX_TWEETER_WOOFER_BALANCE = 5;
    public static int HIFAC_MAX_UNIV_MULTI_AMP_BALANCE = 10;
    public static int HIFAC_MAX_VBYONE_MODU = 100;
    public static int HIFAC_MAX_VBYONE_SPAN = 1000;
    public static int HIFAC_MAX_VOLUME_CTRL_THRESHOLD = 180;
    public static boolean HIFAC_MIC_DETECT_SUPPORT = false;
    public static int HIFAC_MIN_BALANCE = -50;
    public static int HIFAC_MIN_BALANCE_LIKE_850 = 0;
    public static int HIFAC_MIN_DDRSSC_MODU = 0;
    public static int HIFAC_MIN_DDRSSC_SPAN = 0;
    public static int HIFAC_MIN_DOUBLE_AMP_BALANCE = 0;
    public static int HIFAC_MIN_FREQ = 20;
    public static int HIFAC_MIN_GAIN = -360;
    public static int HIFAC_MIN_HPF_FREQUENCY = 50;
    public static int HIFAC_MIN_MULTI_AMP_BALANCE = 0;
    public static int HIFAC_MIN_Q = 0;
    public static int HIFAC_MIN_SOC_DRC = -480;
    public static int HIFAC_MIN_STEP = 10;
    public static int HIFAC_MIN_TWEETER_BALANCE = 0;
    public static int HIFAC_MIN_TWEETER_WOOFER_BALANCE = 0;
    public static int HIFAC_MIN_UNIV_MULTI_AMP_BALANCE = 0;
    public static int HIFAC_MIN_VBYONE_MODU = 20;
    public static int HIFAC_MIN_VOLUME_CTRL_THRESHOLD = -480;
    public static int HIFAC_ML_DIGITAL_AMP_BALANCE = 1;
    public static int HIFAC_PICTURECURVE_POINT_COUNT = 3;
    public static boolean HIFAC_PRODUCT_OEM = false;
    public static boolean HIFAC_SHARP_LOGO_SUPPORT = false;
    public static boolean HIFAC_SPORTS_MODE_SUPPORT = false;
    public static boolean HIFAC_SSC_DDR_ENABLE = true;
    public static boolean HIFAC_SSC_SUPPORT = true;
    public static boolean HIFAC_SSC_VBYONE_ENABLE = true;
    public static int HIFAC_STEP_VOM_VOLTAGE = 1;
    public static boolean HIFAC_TVS_BALANCE_LIKE_550 = false;
    public static boolean HIFAC_TVS_BALANCE_LIKE_650 = false;
    public static boolean HIFAC_TVS_BALANCE_LIKE_850 = false;
    public static boolean HIFAC_TYPE_C_SUPPORT = false;
    public static boolean HIFAC_UART_DEBUG_SUPPORT = false;
    public static boolean HIFAC_UART_SWITCH_SUPPORT = true;
    public static int HIFAC_USB_COUNT = 3;
    public static int HIFAC_VBYONE_SPAN_STEP = 10;
    public static String HIFAC_VENDOR = "mtk";
    public static F7_Wifi_Bluetooth HIFAC_F7_MODEL = F7_Wifi_Bluetooth.F7_WIFI;
    public static AmplifierModel HIFAC_AMP_MODEL = AmplifierModel.NTP8824;
    public static boolean HIFAC_AMP_WOOFER_SUPPORT = false;
    public static int HIFAC_VCOM_SUPPORT = 0;
    public static boolean HIFAC_DOUBLE_CELL_SUPPORT = false;
    public static boolean HIFAC_DEMURA_SWITCH_SUPPORT = false;
    public static int HIFAC_MAX_VCOM_SPAN = 255;
    public static int HIFAC_MIN_VCOM_SPAN = 0;
    public static int HIFAC_MAX_VCOM_DOUBLE_CELL_FHD_SPAN = 49;
    public static int HIFAC_MIN_VCOM_DOUBLE_CELL_FHD_SPAN = 0;
    public static boolean HIFAC_TF_CARD_SUPPORT = false;
    public static boolean HIFAC_CI_CARD_SUPPORT = false;
    public static boolean HIFAC_CA_CARD_SUPPORT = false;
    public static DtvSupportType HIFAC_DTV_TYPES_SUPPORT = DtvSupportType.DTV_DVBC_DTMB;
    public static boolean HIFAC_BLUETOOTH_SUPPORT = true;
    public static boolean HIFAC_WIDE_VINE_KEY_SUPPORT = true;
    public static boolean HIFAC_CHINA_DRM_KEY_SUPPORT = false;
    public static boolean HIFAC_HDCP_KEY_SUPPORT = false;
    public static boolean HIFAC_PLAY_READY_KEY_SUPPORT = false;
    public static boolean HIFAC_PLAY_READY_25KEY_SUPPORT = false;
    public static boolean HIFAC_PLAY_READY_30KEY_SUPPORT = false;
    public static boolean HIFAC_PLAY_READY_40KEY_SUPPORT = false;
    public static boolean HIFAC_HDCP_1_4_KEY_SUPPORT = true;
    public static boolean HIFAC_HDCP_2_X_KEY_SUPPORT = true;
    public static boolean HIFAC_MGK_KEY_SUPPORT = true;
    public static boolean HIFAC_ATTEST_KEY_SUPPORT = true;
    public static boolean HIFAC_CI_PLUS_KEY_SUPPORT = false;
    public static boolean HIFAC_TEE_ENVIRON_SUPPORT = false;
    public static String HIFAC_WIDE_VINE_KEY_FILENAME = "widevine_9980";
    public static String HIFAC_WIDE_VINE_2GKEY_FILENAME = "widevine_6886_2G";
    public static String HIFAC_WIDE_VINE_2GMICKEY_FILENAME = "widevine_6886_2G_MIC";
    public static String HIFAC_CHINA_DRM_KEY_FILENAME = "CHINADRMKEY";
    public static String HIFAC_HDCP14_KEY_FILENAME = "HDCP14KEY";
    public static String HIFAC_HDCP2X_KEY_FILENAME = "HDCP22KEY";
    public static String HIFAC_MGK_KEY_FILENAME = "MGK_9980_hisense_8K";
    public static String HIFAC_MGK_TVS_KEY_FILENAME = "MGK_9980_hisense_8K";
    public static String HIFAC_MGK_OEM_KEY_FILENAME = "MGK_9980_OEM_8K";
    public static String HIFAC_CIPLUS_KEY_FILENAME = "CIPLUS5660";
    public static String HIFAC_DTCPIP_KEY_FILENAME = "DTCPIPCOMMON";
    public static String HIFAC_MARLIN_KEY_FILENAME = "MARLINCOMMON";
    public static String HIFAC_PVR_KEY_FILENAME = "PVR000COMMON";
    public static String HIFAC_SQV_KEY_FILENAME = "SQV000COMMON";
    public static String QDA_NAME = "QDA000MTK9900HIS";
    public static String QDA_FLODER_NAME = "QDAGTV_9900_hisense";
    public static String QDA_LCA_NAME = "QDA0MTK9900LEIGLA03034";
    public static String QDA_LCA_FLODER_NAME = "QDAGTV_9900_Leica_Laser";
    public static boolean HIFAC_LOCALDIMMING_SUPPORT = false;
    public static boolean HIFAC_LD_PATTERN_SUPPORT = false;
    public static boolean HIFAC_WAVES_SUPPORT = false;
    public static boolean HIFAC_BYPASS_SUPPORT = false;
    public static boolean HIFAC_LIGHT_SENSOR_SUPPORT = false;
    public static boolean HIFAC_IS_LASER4K = false;
    public static boolean HIFAC_5G_CHANNEL_SUPPORT = true;
    public static boolean HIFAC_LASER_IS_L9 = false;
    public static String HIFAC_CHINA_DRM_KEY_FILENAME_PREFIX = null;
    public static String HIFAC_WIDEVINE_KEY_FILENAME_PREFIX = null;
    public static String HIFAC_DDR = "1g";
    public static boolean HIFAC_MIC_SUPPORT = false;
    public static String HIFAC_PLAYREADY_KEY_FILENAME_PREFIX = null;
    public static String HIFAC_HDCP14_KEY_FILENAME_PREFIX = null;
    public static String HIFAC_HDCP2X_KEY_FILENAME_PREFIX = null;
    public static String HIFAC_CI_PLUS_KEY_FILENAME_PREFIX = null;
    public static String HIFAC_MGK_KEY_FILENAME_PREFIX = null;
    public static String HIFAC_MGK_OEM_KEY_FILENAME_PREFIX = null;
    public static String HIFAC_MGK_TVS_KEY_FILENAME_PREFIX = null;
    public static String HIFAC_MGK_TOSHIBA_KEY_FILENAME_PREFIX = null;
    public static String HIFAC_DTCPIP_KEY_FILENAME_PREFIX = null;
    public static String HIFAC_MARLIN_KEY_FILENAME_PREFIX = null;
    public static String HIFAC_SQV_KEY_FILENAME_PREFIX = null;
    public static String HIFAC_PVR_KEY_FILENAME_PREFIX = null;
    public static String HIFAC_MAC_FILENAME = "TVChipset_TVOS_TVUI_VideoClient_AppStore.txt";
    public static boolean HIFAC_REGION5_SUPPORT = false;
    public static boolean HIFAC_MARKET_SA = false;
    public static boolean HIFAC_MARKET_TW = false;
    public static boolean HIFAC_MARKET_JP = false;
    public static boolean HIFAC_MARKET_HK = false;
    public static boolean HIFAC_IS_OLED_TVS = false;
    public static boolean HIFAC_IS_OLED = false;
    public static boolean HIFAC_MARKET_KO = false;
    public static boolean HIFAC_LASER_FOCUS_SUPPORT = false;
    public static boolean HIFAC_MARKET_MONITOR = false;
    public static String WIDEVINE_DRM_SERVICE_NAME = "vendor.drm-widevine-hal-1-3";
    public static String HIFAC_PROJECT_ID_FILENAME = "Project_Id.txt";
    public static MarketType HIFAC_MARKET = MarketType.US;
    public static boolean HIFAC_COLORTUNER_SUPPORT = true;
    public static boolean HIFAC_20_POINT_WB_BALANCE_SUPPORT = false;

    public enum AmplifierModel {
        TAS5707,
        TAS5711,
        TAS5727,
        TAS5733,
        TAS5751,
        NTP8212,
        NTP8214,
        NTP8822,
        NTP8824
    }

    public enum BACKLIGHTCURVE_POINT {
        BACKLIGHT_20,
        BACKLIGHT_MIN,
        BACKLIGHT_MID,
        BACKLIGHT_80,
        BACKLIGHT_MAX,
        BACKLIGHT_PWM_FREQ
    }

    public enum ColorTuner {
        RED,
        GREEN,
        BLUE,
        YELLOW,
        CYAN,
        MAGENTA,
        FLESH_TONE
    }

    public enum DtvDemodulationType {
        DVBC,
        DTMB
    }

    public enum DtvSupportType {
        DTV_NOT_SUPPORT,
        DTV_DVBC_DTMB,
        DTV_DVBC,
        DTV_DTMB
    }

    public enum F7_Wifi_Bluetooth {
        F7_WIFI,
        F7_WIFI_AND_BLUETOOTH
    }

    public enum HomeOrTVMode {
        HOME,
        TV
    }

    public enum KindOfFile {
        PICTURE,
        MUSIC,
        VIDEO
    }

    public enum MarketType {
        US,
        EM,
        SA,
        JP,
        HK
    }

    public enum MicDevice {
        REMOTE,
        LOCALHOST,
        VIRTUAL,
        USB
    }

    public enum OSDLanguage {
        ENGLISH,
        CHINESE
    }

    public enum PictureModeEM {
        VIVID,
        STANDARD,
        ENERGY_SAVING,
        THEATER_DAY,
        GAME,
        SPORT,
        THEATER_NIGHT
    }

    public enum PictureModeTW_Toshiba {
        VIVID,
        STANDARD,
        ENERGY_SAVING,
        THEATER_DAY,
        GAME,
        FILMMAKER_MODE
    }

    public enum PowerModeEnum {
        ON,
        STANDBY,
        LAST
    }

    public enum SavingMode {
        BRIGHT,
        SOFT,
        LITI,
        SHUSHI,
        USER
    }

    public enum ThreeDimensionMode {
        OFF,
        MODE_2DTO3D,
        SIDEBYSIDE,
        TOPANDBOTTOM,
        AUTO
    }

    public enum ToFacEnum {
        TOFAC_U,
        TOFAC_M
    }

    public enum ZoomMode {
        AUTO,
        NORMAL,
        ZOOM,
        WIDE,
        DIRECT,
        DOTYBYDOT,
        PANARAMIC,
        CINEMA
    }

    public enum ZoomModeForEM {
        AUTOMATIC,
        NORMAL,
        MOVIE_ZOOM,
        WIDE,
        DIRECT,
        PANARAMIC
    }

    public enum ADCColorSpaceGroup {
        VGA,
        YPBPR_SD,
        YPBPR_HD;

        public static int getSize() {
            return values().length;
        }
    }

    public enum AudioMode {
        STANDARD,
        THEATER,
        SPORTS,
        CONCERT_HAIL,
        MUSIC,
        SPEECH,
        LATE_NIGHT,
        USER;

        public static int getSize() {
            return values().length;
        }
    }

    public enum AudioSourceGroup {
        ATV,
        AV,
        HDMI,
        DMP;

        public static int getSize() {
            return values().length;
        }
    }

    public enum ColorTemperature {
        STANDARD,
        WARM,
        COOL;

        public static int getSize() {
            return values().length;
        }
    }

    public enum DimensionGroup {
        DIMENSION2,
        DIMENSION3;

        public static int getSize() {
            return values().length;
        }
    }

    public enum ModelPanelGroup {
        B1,
        B2,
        B3;

        public static int getSize() {
            return values().length;
        }
    }

    public enum OVERSCAN_TIMING {
        EN_FACTORY_OverScan_TVNTSC,
        EN_FACTORY_OverScan_TVPAL,
        EN_FACTORY_OverScan_TVSECAM,
        EN_FACTORY_OverScan_TVNTSC443,
        EN_FACTORY_OverScan_TVPALM,
        EN_FACTORY_OverScan_TVPALN,
        EN_FACTORY_OverScan_AVNTSC,
        EN_FACTORY_OverScan_AVPAL,
        EN_FACTORY_OverScan_AVSECAM,
        EN_FACTORY_OverScan_AVNTSC443,
        EN_FACTORY_OverScan_AVPALM,
        EN_FACTORY_OverScan_AVPALN,
        EN_FACTORY_OverScan_HDMI480I,
        EN_FACTORY_OverScan_HDMI480P,
        EN_FACTORY_OverScan_HDMI1440_480I,
        EN_FACTORY_OverScan_HDMI1440_480P,
        EN_FACTORY_OverScan_HDMI576I,
        EN_FACTORY_OverScan_HDMI576P,
        EN_FACTORY_OverScan_HDMI720P,
        EN_FACTORY_OverScan_HDMI1080I,
        EN_FACTORY_OverScan_HDMI1080P,
        EN_FACTORY_OverScan_HDMIDefault,
        EN_FACTORY_OverScan_YPBPR480I,
        EN_FACTORY_OverScan_YPBPR480P,
        EN_FACTORY_OverScan_YPBPR576I,
        EN_FACTORY_OverScan_YPBPR576P,
        EN_FACTORY_OverScan_YPBPR720P50,
        EN_FACTORY_OverScan_YPBPR720P60,
        EN_FACTORY_OverScan_YPBPR1080I50,
        EN_FACTORY_OverScan_YPBPR1080I60,
        EN_FACTORY_OverScan_YPBPR1080P24,
        EN_FACTORY_OverScan_YPBPR1080P30,
        EN_FACTORY_OverScan_YPBPR1080P50,
        EN_FACTORY_OverScan_YPBPR1080P60,
        EN_FACTORY_OverScan_DTV480I,
        EN_FACTORY_OverScan_DTVR480P,
        EN_FACTORY_OverScan_DTV576I,
        EN_FACTORY_OverScan_DTV576P,
        EN_FACTORY_OverScan_DTV720P,
        EN_FACTORY_OverScan_DTV1080I,
        EN_FACTORY_OverScan_DTV1080P;

        public static int getSize() {
            return values().length;
        }
    }

    public enum PictureMode {
        VIVID,
        STANDARD,
        ENERGY_SAVING,
        THEATER,
        GAME,
        SPORT,
        CALIBRATED;

        public static int getSize() {
            return values().length;
        }
    }

    public enum Country {
        USA(MtkTvConfigTypeBase.S3166_CFG_COUNT_USA, 59),
        CAN(MtkTvConfigTypeBase.S3166_CFG_COUNT_CAN, 60),
        MEX(MtkTvConfigTypeBase.S3166_CFG_COUNT_MEX, 61);

        int countryIndex;
        String countryName;

        Country(String str, int i) {
            this.countryName = str;
            this.countryIndex = i;
        }
    }

    public enum CountryForRegion1 {
        SAU(MtkTvConfigTypeBase.S3166_CFG_COUNT_SAU, 41),
        ARE(MtkTvConfigTypeBase.S3166_CFG_COUNT_ARE, 42),
        KWT(MtkTvConfigTypeBase.S3166_CFG_COUNT_KWT, 43),
        QAT(MtkTvConfigTypeBase.S3166_CFG_COUNT_QAT, 46),
        JOR(MtkTvConfigTypeBase.S3166_CFG_COUNT_JOR, 47),
        BHR(MtkTvConfigTypeBase.S3166_CFG_COUNT_BHR, 104),
        OMN(MtkTvConfigTypeBase.S3166_CFG_COUNT_OMN, 45),
        LBN(MtkTvConfigTypeBase.S3166_CFG_COUNT_LBN, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_DRM_NOT_SUPPORT),
        IRN(MtkTvConfigTypeBase.S3166_CFG_COUNT_IRN, 48),
        IRQ(MtkTvConfigTypeBase.S3166_CFG_COUNT_IRQ, 40),
        LBY(MtkTvConfigTypeBase.S3166_CFG_COUNT_LBY, 5),
        YEM(MtkTvConfigTypeBase.S3166_CFG_COUNT_YEM, 44),
        SYR(MtkTvConfigTypeBase.S3166_CFG_COUNT_SYR, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_SUBTITLE_FMT_UPDATE),
        MAR(MtkTvConfigTypeBase.S3166_CFG_COUNT_MAR, 3),
        DZA(MtkTvConfigTypeBase.S3166_CFG_COUNT_DZA, 23),
        EGY(MtkTvConfigTypeBase.S3166_CFG_COUNT_EGY, 2),
        SDN(MtkTvConfigTypeBase.S3166_CFG_COUNT_SDN, 18),
        TUN(MtkTvConfigTypeBase.S3166_CFG_COUNT_TUN, 97),
        GAB(MtkTvConfigTypeBase.S3166_CFG_COUNT_GAB, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_HDCP_NOT_DETECT),
        NGA(MtkTvConfigTypeBase.S3166_CFG_COUNT_NGQ, 4),
        SEN(MtkTvConfigTypeBase.S3166_CFG_COUNT_SEN, 96),
        MRT(MtkTvConfigTypeBase.S3166_CFG_COUNT_MRT, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_EWS_ON),
        CIV(MtkTvConfigTypeBase.S3166_CFG_COUNT_CIV, 8),
        MLI(MtkTvConfigTypeBase.S3166_CFG_COUNT_MLI, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_PVR_PLAYING_PARENT_RATING),
        NER(MtkTvConfigTypeBase.S3166_CFG_COUNT_NER, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_BRDCSTING_SVC),
        BFA(MtkTvConfigTypeBase.S3166_CFG_COUNT_BFA, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_INP_SIGNAL_UNKNOWN),
        GMB(MtkTvConfigTypeBase.S3166_CFG_COUNT_GMB, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LIC_NOT_ALLOW),
        TCD(MtkTvConfigTypeBase.S3166_CFG_COUNT_TCD, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DVBS_SHORT_OFF),
        CMR(MtkTvConfigTypeBase.S3166_CFG_COUNT_CMR, 13),
        GIN(MtkTvConfigTypeBase.S3166_CFG_COUNT_GIN, 94),
        SOM(MtkTvConfigTypeBase.S3166_CFG_COUNT_SOM, 98),
        DJI(MtkTvConfigTypeBase.S3166_CFG_COUNT_DJI, 19),
        UGA(MtkTvConfigTypeBase.S3166_CFG_COUNT_UGA, 15),
        GHA(MtkTvConfigTypeBase.S3166_CFG_COUNT_GHA, 6),
        LBR(MtkTvConfigTypeBase.S3166_CFG_COUNT_LBR, 11),
        SLE(MtkTvConfigTypeBase.S3166_CFG_COUNT_SLE, 7);

        int countryIndex;
        String countryName;

        CountryForRegion1(String str, int i) {
            this.countryName = str;
            this.countryIndex = i;
        }

        public int getIndex() {
            return this.countryIndex;
        }

        public String getName() {
            return this.countryName;
        }
    }

    public enum CountryForRegion2 {
        ZAF("ZAF", 24),
        ZWE(MtkTvConfigTypeBase.S3166_CFG_COUNT_ZWE, 14),
        ETH(MtkTvConfigTypeBase.S3166_CFG_COUNT_ETH, 20),
        KEN(MtkTvConfigTypeBase.S3166_CFG_COUNT_KEN, 80),
        COD(MtkTvConfigTypeBase.S3166_CFG_COUNT_COD, 12),
        COG(MtkTvConfigTypeBase.S3166_CFG_COUNT_COG, 79),
        ERI(MtkTvConfigTypeBase.S3166_CFG_COUNT_ERI, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_HDCP_DETECT),
        RWA(MtkTvConfigTypeBase.S3166_CFG_COUNT_RWA, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MACROVISION_DETECT),
        TZA(MtkTvConfigTypeBase.S3166_CFG_COUNT_TZA, 16),
        AGO(MtkTvConfigTypeBase.S3166_CFG_COUNT_AGO, 9),
        MDG(MtkTvConfigTypeBase.S3166_CFG_COUNT_MDG, 22),
        BEN(MtkTvConfigTypeBase.S3166_CFG_COUNT_BEN, 10),
        MUS(MtkTvConfigTypeBase.S3166_CFG_COUNT_MUS, 17),
        REU(MtkTvConfigTypeBase.S3166_CFG_COUNT_REU, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_REQ_STOP),
        MYT(MtkTvConfigTypeBase.S3166_CFG_COUNT_MYT, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_PIN_CAP_CHANGE),
        ZMB(MtkTvConfigTypeBase.S3166_CFG_COUNT_ZMB, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DVBS_SHORT_ON),
        MWI(MtkTvConfigTypeBase.S3166_CFG_COUNT_MWI, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MACROVISION_NOT_DETECT),
        MOZ(MtkTvConfigTypeBase.S3166_CFG_COUNT_MOZ, 21),
        NAM(MtkTvConfigTypeBase.S3166_CFG_COUNT_NAM, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_EWS_OFF),
        BWA(MtkTvConfigTypeBase.S3166_CFG_COUNT_BWA, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_PAUSE_STATUS),
        SYC(MtkTvConfigTypeBase.S3166_CFG_COUNT_SYC, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_NON_BRDCSTING_SVC),
        SSD(MtkTvConfigTypeBase.S3166_CFG_COUNT_SSD, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_PVR_PROG_BLOCKED);

        int countryIndex;
        String countryName;

        CountryForRegion2(String str, int i) {
            this.countryName = str;
            this.countryIndex = i;
        }

        public int getIndex() {
            return this.countryIndex;
        }

        public String getName() {
            return this.countryName;
        }
    }

    public enum CountryForRegion3 {
        AUS("AUS", 51),
        NZL("NZL", MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_ES_AUTH_UPDATED),
        THA("THA", 25),
        VNM(MtkTvConfigTypeBase.S3166_CFG_COUNT_VNM, 26),
        MMR(MtkTvConfigTypeBase.S3166_CFG_COUNT_MMR, 27),
        IND(MtkTvConfigTypeBase.S3166_CFG_COUNT_IND, 38),
        IDN(MtkTvConfigTypeBase.S3166_CFG_COUNT_IDN, 39),
        LKA(MtkTvConfigTypeBase.S3166_CFG_COUNT_LKA, 81),
        MYS(MtkTvConfigTypeBase.S3166_CFG_COUNT_MYS, 28),
        FJI(MtkTvConfigTypeBase.S3166_CFG_COUNT_FJI, 89),
        MDV(MtkTvConfigTypeBase.S3166_CFG_COUNT_MDV, 88),
        BGD(MtkTvConfigTypeBase.S3166_CFG_COUNT_BGD, 99),
        PAK(MtkTvConfigTypeBase.S3166_CFG_COUNT_PAK, 100),
        NPL(MtkTvConfigTypeBase.S3166_CFG_COUNT_NPL, 102),
        KHM(MtkTvConfigTypeBase.S3166_CFG_COUNT_KHM, 103),
        SGP("SGP", MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_RATING_BLOCKED),
        MNG(MtkTvConfigTypeBase.S3166_CFG_COUNT_MNG, 101),
        LAO(MtkTvConfigTypeBase.S3166_CFG_COUNT_LAO, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_CC_ATTR_CHANGED),
        BRN(MtkTvConfigTypeBase.S3166_CFG_COUNT_BRN, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_ANA_AUTO_DETECTED_AUD_SYS);

        int countryIndex;
        String countryName;

        CountryForRegion3(String str, int i) {
            this.countryName = str;
            this.countryIndex = i;
        }

        public int getIndex() {
            return this.countryIndex;
        }

        public String getName() {
            return this.countryName;
        }
    }

    public enum CountryForRegion0 {
        UZB(MtkTvConfigTypeBase.S3166_CFG_COUNT_UZB, 30),
        KGZ(MtkTvConfigTypeBase.S3166_CFG_COUNT_KGZ, 31),
        KAZ(MtkTvConfigTypeBase.S3166_CFG_COUNT_KAZ, 85),
        ARM(MtkTvConfigTypeBase.S3166_CFG_COUNT_ARM, 37),
        AZE(MtkTvConfigTypeBase.S3166_CFG_COUNT_AZE, 35),
        GEO(MtkTvConfigTypeBase.S3166_CFG_COUNT_GEO, 36),
        TKM(MtkTvConfigTypeBase.S3166_CFG_COUNT_TKM, 32),
        ISR(MtkTvConfigTypeBase.S3166_CFG_COUNT_ISR, 49),
        MDA(MtkTvConfigTypeBase.S3166_CFG_COUNT_MDA, 93),
        TJK(MtkTvConfigTypeBase.S3166_CFG_COUNT_TJK, 90),
        BLR(MtkTvConfigTypeBase.S3166_CFG_COUNT_BLR, 92),
        TUR(MtkTvConfigTypeBase.S3166_CFG_COUNT_TUR, 33),
        AFG(MtkTvConfigTypeBase.S3166_CFG_COUNT_AFG, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_UNAUTHORIZED),
        RUS(MtkTvConfigTypeBase.S3166_CFG_COUNT_RUS, 29),
        UKR(MtkTvConfigTypeBase.S3166_CFG_COUNT_UKR, 34),
        SRB(MtkTvConfigTypeBase.S3166_CFG_COUNT_SRB, 91);

        int countryIndex;
        String countryName;

        CountryForRegion0(String str, int i) {
            this.countryName = str;
            this.countryIndex = i;
        }

        public int getIndex() {
            return this.countryIndex;
        }

        public String getName() {
            return this.countryName;
        }
    }

    public enum CountryForRegion4 {
        PAN(MtkTvConfigTypeBase.S3166_CFG_COUNT_PAN, 57),
        COL(MtkTvConfigTypeBase.S3166_CFG_COUNT_COL, 83);

        int countryIndex;
        String countryName;

        CountryForRegion4(String str, int i) {
            this.countryName = str;
            this.countryIndex = i;
        }

        public int getIndex() {
            return this.countryIndex;
        }

        public String getName() {
            return this.countryName;
        }
    }

    public enum CountryForRegion5 {
        BRA(0, "BRA", 53),
        ECU(1, MtkTvConfigTypeBase.S3166_CFG_COUNT_ECU, 82),
        PER(2, MtkTvConfigTypeBase.S3166_CFG_COUNT_PER, 84),
        CHL(3, "CHL", 86),
        PRY(4, MtkTvConfigTypeBase.S3166_CFG_COUNT_PRY, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REACH_VALID_RANGE_END),
        BOL(5, MtkTvConfigTypeBase.S3166_CFG_COUNT_BOL, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_OUT_OF_SAFE_RANGE_START),
        PHL(6, MtkTvConfigTypeBase.S3166_CFG_COUNT_PHL, 56),
        ESA(7, "ESA", 116),
        GUA(8, "GUA", 117);

        int countryIndex;
        String countryName;
        int enumindex;

        CountryForRegion5(int i, String str, int i2) {
            this.enumindex = i;
            this.countryName = str;
            this.countryIndex = i2;
        }

        public int getIndex() {
            return this.countryIndex;
        }

        public String getName() {
            return this.countryName;
        }
    }

    public enum CountryForRegion6 {
        VEN(0, MtkTvConfigTypeBase.S3166_CFG_COUNT_VEN, 54),
        ARG(1, "ARG", 55),
        URY(2, MtkTvConfigTypeBase.S3166_CFG_COUNT_URY, 58);

        int countryIndex;
        String countryName;
        int enumindex;

        CountryForRegion6(int i, String str, int i2) {
            this.enumindex = i;
            this.countryName = str;
            this.countryIndex = i2;
        }

        public int getIndex() {
            return this.countryIndex;
        }

        public String getName() {
            return this.countryName;
        }
    }

    public enum CountryForJPN {
        JPN(0, MtkTvConfigTypeBase.S3166_CFG_COUNT_JPN, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LICENSE_SERVER_CONNECTION_ERROR);

        int countryIndex;
        String countryName;
        int enumindex;

        CountryForJPN(int i, String str, int i2) {
            this.enumindex = i;
            this.countryName = str;
            this.countryIndex = i2;
        }
    }

    public enum LanguageForRegion1 {
        eng(MtkTvConfigTypeBase.S639_CFG_LANG_ENG, 1),
        fre(MtkTvConfigTypeBase.S639_CFG_LANG_FRE, 2),
        spa(MtkTvConfigTypeBase.S639_CFG_LANG_SPA, 3),
        por(MtkTvConfigTypeBase.S639_CFG_LANG_POR, 4),
        ara(MtkTvConfigTypeBase.S639_CFG_LANG_ARA, 5),
        rus(MtkTvConfigTypeBase.S639_CFG_LANG_RUS, 6),
        chi(MtkTvConfigTypeBase.S639_CFG_LANG_CHI, 7),
        per(MtkTvConfigTypeBase.S639_CFG_LANG_PER, 29),
        tur(MtkTvConfigTypeBase.S639_CFG_LANG_TUR, 28),
        urd(MtkTvConfigTypeBase.S639_CFG_LANG_URD, 45),
        ukr(MtkTvConfigTypeBase.S639_CFG_LANG_UKR, 13),
        ita(MtkTvConfigTypeBase.S639_CFG_LANG_ITA, 18),
        geo("geo", 44),
        arm("arm", 43),
        uzb(MtkTvConfigTypeBase.S639_CFG_LANG_UZB, 11),
        aze(MtkTvConfigTypeBase.S639_CFG_LANG_AZE, 49),
        kaz(MtkTvConfigTypeBase.S639_CFG_LANG_KAZ, 48),
        rum(MtkTvConfigTypeBase.S639_CFG_LANG_RUM, 41),
        vie(MtkTvConfigTypeBase.S639_CFG_LANG_VIE, 8),
        tha(MtkTvConfigTypeBase.S639_CFG_LANG_THA, 9),
        ind(MtkTvConfigTypeBase.S639_CFG_LANG_IND, 30),
        hin(MtkTvConfigTypeBase.S639_CFG_LANG_HIN, 12),
        mal(MtkTvConfigTypeBase.S639_CFG_LANG_MAL, 14),
        zho(MtkTvConfigTypeBase.S639_CFG_LANG_ZHO, 16),
        bur("bur", 10),
        bng("bng", 55),
        mog("mog", 56);

        int langIndex;
        String langName;

        LanguageForRegion1(String str, int i) {
            this.langName = str;
            this.langIndex = i;
        }

        public String getName() {
            return this.langName;
        }

        public int getIndex() {
            return this.langIndex;
        }
    }

    public enum LanguageForRegion2 {
        eng(MtkTvConfigTypeBase.S639_CFG_LANG_ENG, 1),
        fre(MtkTvConfigTypeBase.S639_CFG_LANG_FRE, 2),
        spa(MtkTvConfigTypeBase.S639_CFG_LANG_SPA, 3),
        por(MtkTvConfigTypeBase.S639_CFG_LANG_POR, 4),
        ara(MtkTvConfigTypeBase.S639_CFG_LANG_ARA, 5),
        rus(MtkTvConfigTypeBase.S639_CFG_LANG_RUS, 6),
        chi(MtkTvConfigTypeBase.S639_CFG_LANG_CHI, 7),
        per(MtkTvConfigTypeBase.S639_CFG_LANG_PER, 29),
        tur(MtkTvConfigTypeBase.S639_CFG_LANG_TUR, 28),
        amh(MtkTvConfigTypeBase.S639_CFG_LANG_AMH, 46),
        urd(MtkTvConfigTypeBase.S639_CFG_LANG_URD, 45),
        rum(MtkTvConfigTypeBase.S639_CFG_LANG_RUM, 41),
        aze(MtkTvConfigTypeBase.S639_CFG_LANG_AZE, 49),
        ukr(MtkTvConfigTypeBase.S639_CFG_LANG_UKR, 13),
        ita(MtkTvConfigTypeBase.S639_CFG_LANG_ITA, 18),
        geo("geo", 44),
        arm("arm", 43),
        uzb(MtkTvConfigTypeBase.S639_CFG_LANG_UZB, 11);

        int langIndex;
        String langName;

        LanguageForRegion2(String str, int i) {
            this.langName = str;
            this.langIndex = i;
        }

        public String getName() {
            return this.langName;
        }

        public int getIndex() {
            return this.langIndex;
        }
    }

    public enum LanguageForRegion3 {
        eng(MtkTvConfigTypeBase.S639_CFG_LANG_ENG, 1),
        fre(MtkTvConfigTypeBase.S639_CFG_LANG_FRE, 2),
        spa(MtkTvConfigTypeBase.S639_CFG_LANG_SPA, 3),
        por(MtkTvConfigTypeBase.S639_CFG_LANG_POR, 4),
        ara(MtkTvConfigTypeBase.S639_CFG_LANG_ARA, 5),
        rus(MtkTvConfigTypeBase.S639_CFG_LANG_RUS, 6),
        chi(MtkTvConfigTypeBase.S639_CFG_LANG_CHI, 7),
        vie(MtkTvConfigTypeBase.S639_CFG_LANG_VIE, 8),
        tha(MtkTvConfigTypeBase.S639_CFG_LANG_THA, 9),
        ind(MtkTvConfigTypeBase.S639_CFG_LANG_IND, 30),
        hin(MtkTvConfigTypeBase.S639_CFG_LANG_HIN, 12),
        mal(MtkTvConfigTypeBase.S639_CFG_LANG_MAL, 14),
        zho(MtkTvConfigTypeBase.S639_CFG_LANG_ZHO, 16),
        bur("bur", 10),
        urd(MtkTvConfigTypeBase.S639_CFG_LANG_URD, 45),
        bng("bng", 55),
        mog("mog", 56),
        guj(MtkTvConfigTypeBase.S639_CFG_LANG_GUJ, 96),
        kan(MtkTvConfigTypeBase.S639_CFG_LANG_KAN, 93),
        apr("apr", 94),
        tel(MtkTvConfigTypeBase.S639_CFG_LANG_TEL, 91),
        tam(MtkTvConfigTypeBase.S639_CFG_LANG_TAM, 90),
        khm(MtkTvConfigTypeBase.S639_CFG_LANG_KHM, 69),
        sin("sin", 81);

        int langIndex;
        String langName;

        LanguageForRegion3(String str, int i) {
            this.langName = str;
            this.langIndex = i;
        }

        public String getName() {
            return this.langName;
        }

        public int getIndex() {
            return this.langIndex;
        }
    }

    public enum LanguageForRegion0 {
        hbr(MtkTvConfigTypeBase.S639_CFG_LANG_HBR, 15),
        eng(MtkTvConfigTypeBase.S639_CFG_LANG_ENG, 1),
        rus(MtkTvConfigTypeBase.S639_CFG_LANG_RUS, 6),
        ukr(MtkTvConfigTypeBase.S639_CFG_LANG_UKR, 13),
        arm("arm", 43),
        geo("geo", 44),
        aze(MtkTvConfigTypeBase.S639_CFG_LANG_AZE, 49),
        kaz(MtkTvConfigTypeBase.S639_CFG_LANG_KAZ, 48),
        uzb(MtkTvConfigTypeBase.S639_CFG_LANG_UZB, 11),
        tur(MtkTvConfigTypeBase.S639_CFG_LANG_TUR, 28),
        ger(MtkTvConfigTypeBase.S639_CFG_LANG_GER, 17),
        fre(MtkTvConfigTypeBase.S639_CFG_LANG_FRE, 2),
        ita(MtkTvConfigTypeBase.S639_CFG_LANG_ITA, 18),
        spa(MtkTvConfigTypeBase.S639_CFG_LANG_SPA, 3),
        por(MtkTvConfigTypeBase.S639_CFG_LANG_POR, 4),
        dut(MtkTvConfigTypeBase.S639_CFG_LANG_DUT, 40),
        byl("byl", 52),
        ara(MtkTvConfigTypeBase.S639_CFG_LANG_ARA, 5),
        chi(MtkTvConfigTypeBase.S639_CFG_LANG_CHI, 7),
        rum(MtkTvConfigTypeBase.S639_CFG_LANG_RUM, 41),
        srp(MtkTvConfigTypeBase.S639_CFG_LANG_SRP, 32);

        int langIndex;
        String langName;

        LanguageForRegion0(String str, int i) {
            this.langName = str;
            this.langIndex = i;
        }

        public String getName() {
            return this.langName;
        }

        public int getIndex() {
            return this.langIndex;
        }
    }

    public enum LanguageForRegion4 {
        eng(MtkTvConfigTypeBase.S639_CFG_LANG_ENG, 1),
        spa(MtkTvConfigTypeBase.S639_CFG_LANG_SPA, 3),
        por(MtkTvConfigTypeBase.S639_CFG_LANG_POR, 4);

        int langIndex;
        String langName;

        LanguageForRegion4(String str, int i) {
            this.langName = str;
            this.langIndex = i;
        }

        public String getName() {
            return this.langName;
        }

        public int getIndex() {
            return this.langIndex;
        }
    }

    public enum LanguageForRegion5 {
        eng(0, MtkTvConfigTypeBase.S639_CFG_LANG_ENG, 1),
        spa(1, MtkTvConfigTypeBase.S639_CFG_LANG_SPA, 3),
        por(2, MtkTvConfigTypeBase.S639_CFG_LANG_POR, 4),
        chi(3, MtkTvConfigTypeBase.S639_CFG_LANG_CHI, 7);

        int enumIndex;
        int langIndex;
        String langName;

        LanguageForRegion5(int i, String str, int i2) {
            this.enumIndex = i;
            this.langName = str;
            this.langIndex = i2;
        }

        public String getName() {
            return this.langName;
        }

        public int getIndex() {
            return this.langIndex;
        }
    }

    public enum LanguageForRegion6 {
        eng(0, MtkTvConfigTypeBase.S639_CFG_LANG_ENG, 1),
        spa(1, MtkTvConfigTypeBase.S639_CFG_LANG_SPA, 3),
        por(2, MtkTvConfigTypeBase.S639_CFG_LANG_POR, 4);

        int enumIndex;
        int langIndex;
        String langName;

        LanguageForRegion6(int i, String str, int i2) {
            this.enumIndex = i;
            this.langName = str;
            this.langIndex = i2;
        }

        public String getName() {
            return this.langName;
        }

        public int getIndex() {
            return this.langIndex;
        }
    }

    public enum LanguageForTWN {
        eng(0, MtkTvConfigTypeBase.S639_CFG_LANG_ENG, 1),
        zho(1, MtkTvConfigTypeBase.S639_CFG_LANG_ZHO, 16);

        int enumIndex;
        int langIndex;
        String langName;

        LanguageForTWN(int i, String str, int i2) {
            this.enumIndex = i;
            this.langName = str;
            this.langIndex = i2;
        }

        public String getName() {
            return this.langName;
        }

        public int getIndex() {
            return this.langIndex;
        }

        public static int getSize() {
            return values().length;
        }
    }

    public enum LanguageForJPN {
        jpn(0, MtkTvConfigTypeBase.S639_CFG_LANG_JPN, 68);

        int enumIndex;
        int langIndex;
        String langName;

        LanguageForJPN(int i, String str, int i2) {
            this.enumIndex = i;
            this.langName = str;
            this.langIndex = i2;
        }

        public String getName() {
            return this.langName;
        }

        public int getIndex() {
            return this.langIndex;
        }

        public static int getSize() {
            return values().length;
        }
    }

    public enum Region {
        REGION1("REGION1", 0),
        REGION2("REGION2", 1),
        REGION3("REGION3", 2),
        REGION4("REGION4", 3),
        REGION5("REGION5", 4),
        REGION6("REGION6", 5),
        REGION7("REGION7", 6);

        int regionIndex;
        String regionName;

        Region(String str, int i) {
            this.regionName = str;
            this.regionIndex = i;
        }
    }

    public enum Language {
        eng(MtkTvConfigTypeBase.S639_CFG_LANG_ENG, 1),
        fre(MtkTvConfigTypeBase.S639_CFG_LANG_FRE, 2),
        spa(MtkTvConfigTypeBase.S639_CFG_LANG_SPA, 3),
        chi(MtkTvConfigTypeBase.S639_CFG_LANG_CHI, 7),
        zho(MtkTvConfigTypeBase.S639_CFG_LANG_ZHO, 16),
        ger(MtkTvConfigTypeBase.S639_CFG_LANG_GER, 17);

        int langIndex;
        String langName;

        Language(String str, int i) {
            this.langName = str;
            this.langIndex = i;
        }

        public String getName() {
            return this.langName;
        }

        public int getIndex() {
            return this.langIndex;
        }
    }

    public enum Logo {
        his("his", 1),
        vuz("vuz", 23),
        lca("lca", 259),
        shp("shp", 49);

        int logoIndex;
        String logoName;

        Logo(String str, int i) {
            this.logoName = str;
            this.logoIndex = i;
        }

        public String getName() {
            return this.logoName;
        }
    }

    public enum LogoForEM_NonBrandIsolation {
        his("his", 1),
        vuz("vuz", 23),
        tos("tos", 95),
        alh("alh", 86),
        gua("gua", 55),
        llo("llo", 24),
        bpl("bpl", 72),
        vis("vis", 90),
        aba("aba", 70),
        inn("inn", 25),
        ben("ben", 60),
        mag("mag", 68),
        dof("dof", 92),
        dex("dex", 30);

        int logoIndex;
        String logoName;

        LogoForEM_NonBrandIsolation(String str, int i) {
            this.logoName = str;
            this.logoIndex = i;
        }
    }

    public enum LogoForEM_SA {
        his("his", 1),
        kal("kal", 13);

        int logoIndex;
        String logoName;

        LogoForEM_SA(String str, int i) {
            this.logoName = str;
            this.logoIndex = i;
        }

        public String getName() {
            return this.logoName;
        }
    }

    public enum LogoForJPN {
        tos("tos", 95);

        int logoIndex;
        String logoName;

        LogoForJPN(String str, int i) {
            this.logoName = str;
            this.logoIndex = i;
        }
    }

    public enum LogoForCon_brandIsolation {
        con("con", 18),
        wel("wel", 2),
        off(MtkTvConfigTypeBase.S639_CFG_SUBTITLE_LANG_OFF, 100);

        int logoIndex;
        String logoName;

        LogoForCon_brandIsolation(String str, int i) {
            this.logoName = str;
            this.logoIndex = i;
        }
    }

    public enum LogoForTho_brandIsolation {
        tho("tho", 45),
        wel("wel", 2),
        off(MtkTvConfigTypeBase.S639_CFG_SUBTITLE_LANG_OFF, 100);

        int logoIndex;
        String logoName;

        LogoForTho_brandIsolation(String str, int i) {
            this.logoName = str;
            this.logoIndex = i;
        }
    }

    public enum LogoForStr_brandIsolation {
        str("str", 51),
        wel("wel", 2),
        off(MtkTvConfigTypeBase.S639_CFG_SUBTITLE_LANG_OFF, 100);

        int logoIndex;
        String logoName;

        LogoForStr_brandIsolation(String str2, int i) {
            this.logoName = str2;
            this.logoIndex = i;
        }
    }

    public enum LogoForGen_brandIsolation {
        gen("gen", 19),
        wel("wel", 2),
        off(MtkTvConfigTypeBase.S639_CFG_SUBTITLE_LANG_OFF, 100);

        int logoIndex;
        String logoName;

        LogoForGen_brandIsolation(String str, int i) {
            this.logoName = str;
            this.logoIndex = i;
        }
    }

    public enum LogoForHaa_brandIsolation {
        haa("haa", 20),
        wel("wel", 2),
        off(MtkTvConfigTypeBase.S639_CFG_SUBTITLE_LANG_OFF, 100);

        int logoIndex;
        String logoName;

        LogoForHaa_brandIsolation(String str, int i) {
            this.logoName = str;
            this.logoIndex = i;
        }
    }

    public enum LogoForTit_brandIsolation {
        tit("tit", 21),
        wel("wel", 2),
        off(MtkTvConfigTypeBase.S639_CFG_SUBTITLE_LANG_OFF, 100);

        int logoIndex;
        String logoName;

        LogoForTit_brandIsolation(String str, int i) {
            this.logoName = str;
            this.logoIndex = i;
        }
    }

    public enum LogoForKmc_brandIsolation {
        kmc("kmc", 22),
        wel("wel", 2),
        off(MtkTvConfigTypeBase.S639_CFG_SUBTITLE_LANG_OFF, 100);

        int logoIndex;
        String logoName;

        LogoForKmc_brandIsolation(String str, int i) {
            this.logoName = str;
            this.logoIndex = i;
        }
    }

    public enum LogoForBGH_SABrandIsolation {
        his(0, "his", 1),
        bgh(1, "bgh", 10),
        tel(2, MtkTvConfigTypeBase.S639_CFG_LANG_TEL, 16),
        toh(3, "toh", 87);

        int enumIndex;
        int logoIndex;
        String logoName;

        LogoForBGH_SABrandIsolation(int i, String str, int i2) {
            this.enumIndex = i;
            this.logoName = str;
            this.logoIndex = i2;
        }

        public String getName() {
            return this.logoName;
        }

        public int getIndex() {
            return this.logoIndex;
        }

        public static int getSize() {
            return values().length;
        }
    }

    public enum LogoForDEVANT_SABrandIsolation {
        his(0, "his", 1),
        dev(1, "dev", 12),
        avi(2, "avi", 84);

        int enumIndex;
        int logoIndex;
        String logoName;

        LogoForDEVANT_SABrandIsolation(int i, String str, int i2) {
            this.enumIndex = i;
            this.logoName = str;
            this.logoIndex = i2;
        }

        public String getName() {
            return this.logoName;
        }

        public int getIndex() {
            return this.logoIndex;
        }

        public static int getSize() {
            return values().length;
        }
    }

    public enum LogoForNEWSAN_SABrandIsolation {
        nob(0, "nob", 3),
        jvc(1, "jvc", 4),
        san(2, "san", 5),
        phi(3, "phi", 6),
        ilo(4, "ilo", 7),
        pio(5, "pio", 8),
        sei(6, "sei", 42),
        ton(7, "ton", 43),
        psc(8, "psc", 44),
        shp(9, "shp", 49),
        qua(10, "qua", 128),
        adi(11, "adi", MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_PVR_PLAYING_PARENT_RATING);

        int enumIndex;
        int logoIndex;
        String logoName;

        LogoForNEWSAN_SABrandIsolation(int i, String str, int i2) {
            this.enumIndex = i;
            this.logoName = str;
            this.logoIndex = i2;
        }

        public String getName() {
            return this.logoName;
        }

        public int getIndex() {
            return this.logoIndex;
        }

        public static int getSize() {
            return values().length;
        }
    }

    public enum LogoForDefault_SABrandIsolation {
        his(0, "his", 1),
        blu(1, "blu", 11),
        cha(2, "cha", 14),
        pan(3, "pan", 15),
        riv(4, "riv", 17),
        ezy(5, "ezy", 36),
        ame(6, "ame", 37),
        shp(7, "shp", 49),
        fam(8, "fam", 67),
        adm(9, "adm", 85),
        kio(10, "kio", 96),
        mul(11, MtkTvConfigTypeBase.S639_CFG_LANG_MUL, 97),
        ika(12, "ika", MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_DRM_NOT_SUPPORT),
        glo(13, "glo", MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_SUBTITLE_FMT_UPDATE),
        nex(14, "nex", MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REACH_VALID_RANGE_START),
        mas(15, "mas", MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REACH_VALID_RANGE_END),
        ind(16, MtkTvConfigTypeBase.S639_CFG_LANG_IND, 111),
        tos(17, "tos", 95);

        int enumIndex;
        int logoIndex;
        String logoName;

        LogoForDefault_SABrandIsolation(int i, String str, int i2) {
            this.enumIndex = i;
            this.logoName = str;
            this.logoIndex = i2;
        }

        public String getName() {
            return this.logoName;
        }

        public int getIndex() {
            return this.logoIndex;
        }

        public static int getSize() {
            return values().length;
        }
    }

    public enum SourceEnum {
        TV("TV", false, false, 1, 257),
        ATV("ATV", false, false, 1, 257),
        DTV("DTV", false, false, 1, 513),
        COMPONENT("COMPONENT", false, false, 1, 769),
        COMPONENT1("COMPONENT1", false, false, 1, 769),
        COMPONENT2("COMPONENT2", false, false, 1, 770),
        COMPONENT3("COMPONENT3", false, false, 1, 771),
        AV(MtkTvRatingConvert2Goo.RATING_STR_AV, false, false, 1, 1025),
        AV1("AV1", false, false, 2, 1025),
        AV2("AV2", false, false, 2, 1026),
        AV3("AV3", false, false, 2, 1027),
        HDMI1("HDMI1", false, false, 3, 1281),
        HDMI2("HDMI2", false, false, 3, 1282),
        HDMI3("HDMI3", false, false, 3, 1283),
        HDMI4("HDMI4", false, false, 3, 1284),
        HDMI5("HDMI5", false, false, 3, 1285),
        VGA("VGA", false, false, 1, 2049),
        DMP("DMP", false, false, 1, 2305),
        DMP1("DMP1", false, false, 1, 2305),
        DMP2("DMP2", false, false, 1, 2306),
        SVIDEO("SVIDEO", false, false, 0, 2817),
        SVIDEO2("SVIDEO2", false, false, 0, 2818),
        SVIDEO3("SVIDEO3", false, false, 0, 2819),
        SCART("SCART", false, false, 0, 2817),
        SCART1("SCART1", false, false, 0, 2817),
        SCART2("SCART2", false, false, 0, 2818),
        SCART3("SCART3", false, false, 0, 2819),
        DVI1("DVI1", false, false, 0, 2817),
        DVI2("DVI2", false, false, 0, 2818);

        private static List<SourceConfiguration> mSourceList = new ArrayList();
        private static List<SourceEnum> shownSourceEnumList;
        private static List<SourceEnum> supportedSourceEnumList;
        private int countOfThisSource;
        private String displayName;
        private boolean isEnabled;
        private boolean isShown;
        private int tilValue;

        public static void addToShownSourceList(SourceConfiguration sourceConfiguration) {
            mSourceList.add(sourceConfiguration);
        }

        public String getDisplayName() {
            return this.displayName;
        }

        public void setDisplayName(String str) {
            this.displayName = str;
        }

        public boolean isEnabled() {
            return this.isEnabled;
        }

        public void setEnabled(boolean z) {
            this.isEnabled = z;
        }

        public void setCountOfThisSource(int i) {
            this.countOfThisSource = i;
        }

        public int getTilValue() {
            return this.tilValue;
        }

        public void setTilValue(int i) {
            this.tilValue = i;
        }

        public boolean isShown() {
            return this.isShown;
        }

        public void setShown(boolean z) {
            this.isShown = z;
        }

        SourceEnum(String str, boolean z, boolean z2, int i, int i2) {
            this.displayName = str;
            this.isEnabled = z;
            this.isShown = z2;
            this.countOfThisSource = i;
            this.tilValue = i2;
        }

        public static synchronized List<SourceEnum> getShownSourceList() {
            if (shownSourceEnumList == null) {
                shownSourceEnumList = new LinkedList();
                for (SourceEnum sourceEnum : values()) {
                    if (sourceEnum.isEnabled && sourceEnum.isShown()) {
                        shownSourceEnumList.add(sourceEnum);
                    }
                }
            }
            return shownSourceEnumList;
        }

        public static synchronized List<SourceEnum> getSupportedSourceList() {
            if (supportedSourceEnumList == null) {
                supportedSourceEnumList = new LinkedList();
                for (SourceEnum sourceEnum : values()) {
                    if (sourceEnum.isEnabled()) {
                        supportedSourceEnumList.add(sourceEnum);
                    }
                }
            }
            return supportedSourceEnumList;
        }

        public static void setWhichSourcesSupported() {
            SourceEnum sourceEnum;
            SourceEnum sourceEnum2;
            SourceEnum sourceEnum3;
            SourceEnum sourceEnum4 = DMP;
            SourceEnum sourceEnum5 = VGA;
            SourceEnum sourceEnum6 = HDMI5;
            SourceEnum sourceEnum7 = HDMI4;
            SourceEnum sourceEnum8 = HDMI3;
            SourceEnum sourceEnum9 = HDMI2;
            SourceEnum sourceEnum10 = HDMI1;
            SourceEnum sourceEnum11 = AV3;
            SourceEnum sourceEnum12 = AV2;
            SourceEnum sourceEnum13 = AV1;
            SourceEnum sourceEnum14 = AV;
            SourceEnum sourceEnum15 = COMPONENT3;
            SourceEnum sourceEnum16 = COMPONENT2;
            SourceEnum sourceEnum17 = COMPONENT1;
            SourceEnum sourceEnum18 = COMPONENT;
            SourceEnum sourceEnum19 = DTV;
            SourceEnum sourceEnum20 = sourceEnum4;
            SourceEnum sourceEnum21 = ATV;
            SourceEnum sourceEnum22 = TV;
            int i = 0;
            while (true) {
                SourceEnum sourceEnum23 = sourceEnum7;
                if (i >= mSourceList.size()) {
                    return;
                }
                SourceEnum sourceEnum24 = sourceEnum8;
                if ("TV".equalsIgnoreCase(mSourceList.get(i).getOriSourceName())) {
                    sourceEnum22.setCountOfThisSource(mSourceList.get(i).getCountOfThisSource());
                    sourceEnum22.setDisplayName(mSourceList.get(i).getDisplayName());
                    sourceEnum22.setEnabled(mSourceList.get(i).isEnabled());
                    sourceEnum22.setShown(mSourceList.get(i).isShown());
                    sourceEnum22.setTilValue(mSourceList.get(i).getTilValue());
                } else if ("ATV".equalsIgnoreCase(mSourceList.get(i).getOriSourceName())) {
                    sourceEnum21.setCountOfThisSource(mSourceList.get(i).getCountOfThisSource());
                    sourceEnum21.setDisplayName(mSourceList.get(i).getDisplayName());
                    sourceEnum21.setEnabled(mSourceList.get(i).isEnabled());
                    sourceEnum21.setShown(mSourceList.get(i).isShown());
                    sourceEnum21.setTilValue(mSourceList.get(i).getTilValue());
                } else if ("DTV".equalsIgnoreCase(mSourceList.get(i).getOriSourceName())) {
                    sourceEnum19.setCountOfThisSource(mSourceList.get(i).getCountOfThisSource());
                    sourceEnum19.setDisplayName(mSourceList.get(i).getDisplayName());
                    sourceEnum19.setEnabled(mSourceList.get(i).isEnabled());
                    sourceEnum19.setShown(mSourceList.get(i).isShown());
                    sourceEnum19.setTilValue(mSourceList.get(i).getTilValue());
                } else if ("COMPONENT".equalsIgnoreCase(mSourceList.get(i).getOriSourceName())) {
                    sourceEnum18.setCountOfThisSource(mSourceList.get(i).getCountOfThisSource());
                    sourceEnum18.setDisplayName(mSourceList.get(i).getDisplayName());
                    sourceEnum18.setEnabled(mSourceList.get(i).isEnabled());
                    sourceEnum18.setShown(mSourceList.get(i).isShown());
                    sourceEnum18.setTilValue(mSourceList.get(i).getTilValue());
                } else if ("COMPONENT1".equalsIgnoreCase(mSourceList.get(i).getOriSourceName())) {
                    sourceEnum17.setCountOfThisSource(mSourceList.get(i).getCountOfThisSource());
                    sourceEnum17.setDisplayName(mSourceList.get(i).getDisplayName());
                    sourceEnum17.setEnabled(mSourceList.get(i).isEnabled());
                    sourceEnum17.setShown(mSourceList.get(i).isShown());
                    sourceEnum17.setTilValue(mSourceList.get(i).getTilValue());
                } else if ("COMPONENT2".equalsIgnoreCase(mSourceList.get(i).getOriSourceName())) {
                    sourceEnum16.setCountOfThisSource(mSourceList.get(i).getCountOfThisSource());
                    sourceEnum16.setDisplayName(mSourceList.get(i).getDisplayName());
                    sourceEnum16.setEnabled(mSourceList.get(i).isEnabled());
                    sourceEnum16.setShown(mSourceList.get(i).isShown());
                    sourceEnum16.setTilValue(mSourceList.get(i).getTilValue());
                } else if ("COMPONENT3".equalsIgnoreCase(mSourceList.get(i).getOriSourceName())) {
                    sourceEnum15.setCountOfThisSource(mSourceList.get(i).getCountOfThisSource());
                    sourceEnum15.setDisplayName(mSourceList.get(i).getDisplayName());
                    sourceEnum15.setEnabled(mSourceList.get(i).isEnabled());
                    sourceEnum15.setShown(mSourceList.get(i).isShown());
                    sourceEnum15.setTilValue(mSourceList.get(i).getTilValue());
                } else if (MtkTvRatingConvert2Goo.RATING_STR_AV.equalsIgnoreCase(mSourceList.get(i).getOriSourceName())) {
                    sourceEnum14.setCountOfThisSource(mSourceList.get(i).getCountOfThisSource());
                    sourceEnum14.setDisplayName(mSourceList.get(i).getDisplayName());
                    sourceEnum14.setEnabled(mSourceList.get(i).isEnabled());
                    sourceEnum14.setShown(mSourceList.get(i).isShown());
                    sourceEnum14.setTilValue(mSourceList.get(i).getTilValue());
                } else if ("AV1".equalsIgnoreCase(mSourceList.get(i).getOriSourceName())) {
                    sourceEnum13.setCountOfThisSource(mSourceList.get(i).getCountOfThisSource());
                    sourceEnum13.setDisplayName(mSourceList.get(i).getDisplayName());
                    sourceEnum13.setEnabled(mSourceList.get(i).isEnabled());
                    sourceEnum13.setShown(mSourceList.get(i).isShown());
                    sourceEnum13.setTilValue(mSourceList.get(i).getTilValue());
                } else if ("AV2".equalsIgnoreCase(mSourceList.get(i).getOriSourceName())) {
                    sourceEnum12.setCountOfThisSource(mSourceList.get(i).getCountOfThisSource());
                    sourceEnum12.setDisplayName(mSourceList.get(i).getDisplayName());
                    sourceEnum12.setEnabled(mSourceList.get(i).isEnabled());
                    sourceEnum12.setShown(mSourceList.get(i).isShown());
                    sourceEnum12.setTilValue(mSourceList.get(i).getTilValue());
                } else if ("AV3".equalsIgnoreCase(mSourceList.get(i).getOriSourceName())) {
                    sourceEnum11.setCountOfThisSource(mSourceList.get(i).getCountOfThisSource());
                    sourceEnum11.setDisplayName(mSourceList.get(i).getDisplayName());
                    sourceEnum11.setEnabled(mSourceList.get(i).isEnabled());
                    sourceEnum11.setShown(mSourceList.get(i).isShown());
                    sourceEnum11.setTilValue(mSourceList.get(i).getTilValue());
                } else if ("HDMI1".equalsIgnoreCase(mSourceList.get(i).getOriSourceName())) {
                    sourceEnum10.setCountOfThisSource(mSourceList.get(i).getCountOfThisSource());
                    sourceEnum10.setDisplayName(mSourceList.get(i).getDisplayName());
                    sourceEnum10.setEnabled(mSourceList.get(i).isEnabled());
                    sourceEnum10.setShown(mSourceList.get(i).isShown());
                    sourceEnum10.setTilValue(mSourceList.get(i).getTilValue());
                } else {
                    if ("HDMI2".equalsIgnoreCase(mSourceList.get(i).getOriSourceName())) {
                        sourceEnum9.setCountOfThisSource(mSourceList.get(i).getCountOfThisSource());
                        sourceEnum9.setDisplayName(mSourceList.get(i).getDisplayName());
                        sourceEnum9.setEnabled(mSourceList.get(i).isEnabled());
                        sourceEnum9.setShown(mSourceList.get(i).isShown());
                        sourceEnum9.setTilValue(mSourceList.get(i).getTilValue());
                    } else {
                        if ("HDMI3".equalsIgnoreCase(mSourceList.get(i).getOriSourceName())) {
                            sourceEnum8 = sourceEnum24;
                            sourceEnum8.setCountOfThisSource(mSourceList.get(i).getCountOfThisSource());
                            sourceEnum8.setDisplayName(mSourceList.get(i).getDisplayName());
                            sourceEnum8.setEnabled(mSourceList.get(i).isEnabled());
                            sourceEnum8.setShown(mSourceList.get(i).isShown());
                            sourceEnum8.setTilValue(mSourceList.get(i).getTilValue());
                            sourceEnum = sourceEnum21;
                            sourceEnum7 = sourceEnum23;
                        } else {
                            sourceEnum8 = sourceEnum24;
                            sourceEnum = sourceEnum21;
                            if ("HDMI4".equalsIgnoreCase(mSourceList.get(i).getOriSourceName())) {
                                sourceEnum7 = sourceEnum23;
                                sourceEnum7.setCountOfThisSource(mSourceList.get(i).getCountOfThisSource());
                                sourceEnum7.setDisplayName(mSourceList.get(i).getDisplayName());
                                sourceEnum7.setEnabled(mSourceList.get(i).isEnabled());
                                sourceEnum7.setShown(mSourceList.get(i).isShown());
                                sourceEnum7.setTilValue(mSourceList.get(i).getTilValue());
                            } else {
                                sourceEnum7 = sourceEnum23;
                                sourceEnum2 = sourceEnum22;
                                if ("HDMI5".equalsIgnoreCase(mSourceList.get(i).getOriSourceName())) {
                                    sourceEnum6.setCountOfThisSource(mSourceList.get(i).getCountOfThisSource());
                                    sourceEnum6.setDisplayName(mSourceList.get(i).getDisplayName());
                                    sourceEnum6.setEnabled(mSourceList.get(i).isEnabled());
                                    sourceEnum6.setShown(mSourceList.get(i).isShown());
                                    sourceEnum6.setTilValue(mSourceList.get(i).getTilValue());
                                } else if ("VGA".equalsIgnoreCase(mSourceList.get(i).getOriSourceName())) {
                                    sourceEnum5.setCountOfThisSource(mSourceList.get(i).getCountOfThisSource());
                                    sourceEnum5.setDisplayName(mSourceList.get(i).getDisplayName());
                                    sourceEnum5.setEnabled(mSourceList.get(i).isEnabled());
                                    sourceEnum5.setShown(mSourceList.get(i).isShown());
                                    sourceEnum5.setTilValue(mSourceList.get(i).getTilValue());
                                } else if ("DMP".equalsIgnoreCase(mSourceList.get(i).getOriSourceName())) {
                                    sourceEnum3 = sourceEnum20;
                                    sourceEnum3.setCountOfThisSource(mSourceList.get(i).getCountOfThisSource());
                                    sourceEnum3.setDisplayName(mSourceList.get(i).getDisplayName());
                                    sourceEnum3.setEnabled(mSourceList.get(i).isEnabled());
                                    sourceEnum3.setShown(mSourceList.get(i).isShown());
                                    sourceEnum3.setTilValue(mSourceList.get(i).getTilValue());
                                }
                                sourceEnum3 = sourceEnum20;
                            }
                        }
                        sourceEnum2 = sourceEnum22;
                        sourceEnum3 = sourceEnum20;
                    }
                    i++;
                    sourceEnum20 = sourceEnum3;
                    sourceEnum21 = sourceEnum;
                    sourceEnum22 = sourceEnum2;
                }
                sourceEnum8 = sourceEnum24;
                sourceEnum7 = sourceEnum23;
                sourceEnum = sourceEnum21;
                sourceEnum2 = sourceEnum22;
                sourceEnum3 = sourceEnum20;
                i++;
                sourceEnum20 = sourceEnum3;
                sourceEnum21 = sourceEnum;
                sourceEnum22 = sourceEnum2;
            }
        }
    }

    public enum VideoCurveModeGroup {
        BRIGHTNESS,
        CONTRAST,
        COLOR,
        SHARPNESS,
        Hue;

        public static int getSize() {
            return values().length;
        }
    }

    public enum VideoSourceGroup {
        ATV,
        AV,
        SV,
        YPBPR,
        VGA,
        HDMI,
        DTV,
        DMP;

        public static int getSize() {
            return values().length;
        }
    }

    public enum WhiteBalanceSourceGroup {
        CVBS,
        YUV,
        RGB,
        DTV,
        DMP;

        public static int getSize() {
            return values().length;
        }
    }

    public static void initProperties() {
        String str = SystemProperties.get("ro.vendor.mtk.system.marketregion", "US");
        String str2 = SystemProperties.get("ro.product.board");
        String str3 = SystemProperties.get("ro.vt.market");
        if (str3.equalsIgnoreCase("TW")) {
            HIFAC_MARKET_TW = true;
        } else if (str3.equalsIgnoreCase("KO")) {
            HIFAC_MARKET_KO = true;
        } else if (str3.equalsIgnoreCase("EM") && str3.equalsIgnoreCase("HK")) {
            HIFAC_MARKET_HK = true;
        }
        if (str.equalsIgnoreCase("EM")) {
            HIFAC_MARKET = MarketType.EM;
            HIFAC_PLAY_READY_40KEY_SUPPORT = true;
            if (str2.equalsIgnoreCase("MT9900")) {
                HIFAC_WIDEVINE_KEY_FILENAME_PREFIX = SystemProperties.get("ro.vt.wide_vine_name_prefix", "WIDEVNMTK99000000000HS");
                HIFAC_HDCP14_KEY_FILENAME_PREFIX = SystemProperties.get("ro.vt.hdcp_1_4_name_prefix", "HDCP14MTK00000000000HS");
                HIFAC_HDCP2X_KEY_FILENAME_PREFIX = SystemProperties.get("ro.vt.hdcp_2_x_name_prefix", "HDCP22MTK99000000000HS");
                HIFAC_MGK_KEY_FILENAME_PREFIX = SystemProperties.get("ro.vt.mgk_name_prefix", "MGK000MTK9900HIS0000HS");
                HIFAC_MGK_OEM_KEY_FILENAME_PREFIX = SystemProperties.get("ro.vt.mgk_name_prefix", "MGK000MTK9900OEM0000HS");
                HIFAC_MGK_TOSHIBA_KEY_FILENAME_PREFIX = SystemProperties.get("ro.vt.mgk_name_prefix", "MGK000MTK9900HIS0000HS");
                HIFAC_WIDE_VINE_KEY_FILENAME = "widevine_9900";
                HIFAC_MGK_KEY_FILENAME = "MGK_9900_hisense";
                HIFAC_MGK_OEM_KEY_FILENAME = "MGK_9900_OEM";
            } else if (str2.equalsIgnoreCase("MT9980")) {
                HIFAC_WIDEVINE_KEY_FILENAME_PREFIX = SystemProperties.get("ro.vt.wide_vine_name_prefix", "WIDEVNMTK99800000000HS");
                HIFAC_HDCP14_KEY_FILENAME_PREFIX = SystemProperties.get("ro.vt.hdcp_1_4_name_prefix", "HDCP14MTK00000000000HS");
                HIFAC_HDCP2X_KEY_FILENAME_PREFIX = SystemProperties.get("ro.vt.hdcp_2_x_name_prefix", "HDCP22MTK99800000000HS");
                HIFAC_MGK_KEY_FILENAME_PREFIX = SystemProperties.get("ro.vt.mgk_name_prefix", "MGK000MTK9980HIS8K00HS");
                HIFAC_MGK_OEM_KEY_FILENAME_PREFIX = SystemProperties.get("ro.vt.mgk_name_prefix", "MGK000MTK9980OEM8K00HS");
                HIFAC_MGK_TOSHIBA_KEY_FILENAME_PREFIX = SystemProperties.get("ro.vt.mgk_name_prefix", "MGK000MTK9980HIS8K00HS");
            }
        } else if (str.equalsIgnoreCase("US")) {
            HIFAC_MARKET = MarketType.US;
            HIFAC_PLAY_READY_40KEY_SUPPORT = true;
            HIFAC_WIDEVINE_KEY_FILENAME_PREFIX = SystemProperties.get("ro.vt.wide_vine_name_prefix", "WIDEVNMTK9900GTV0000HS");
            HIFAC_HDCP14_KEY_FILENAME_PREFIX = SystemProperties.get("ro.vt.hdcp_1_4_name_prefix", "HDCP14MTK00000000000HS");
            HIFAC_HDCP2X_KEY_FILENAME_PREFIX = SystemProperties.get("ro.vt.hdcp_2_x_name_prefix", "HDCP22MTK99000000000HS");
            HIFAC_MGK_KEY_FILENAME_PREFIX = SystemProperties.get("ro.vt.mgk_name_prefix", "MGK000MTK9900HISGTV0HS");
            HIFAC_MGK_OEM_KEY_FILENAME_PREFIX = SystemProperties.get("ro.vt.mgk_name_prefix", "MGK000MTK9900OEMGTV0HS");
            HIFAC_WIDE_VINE_KEY_FILENAME = "WIDEVNGTV_9900";
            HIFAC_MGK_KEY_FILENAME = "MGKHISGTV_9900";
            HIFAC_MGK_OEM_KEY_FILENAME = "MGKOEMGTV_9900";
            HIFAC_20_POINT_WB_BALANCE_SUPPORT = true;
            HIFAC_LD_PATTERN_SUPPORT = true;
        } else if (str.equalsIgnoreCase("JP")) {
            HIFAC_MARKET = MarketType.JP;
            HIFAC_MARKET_JP = true;
            HIFAC_PLAY_READY_40KEY_SUPPORT = true;
            HIFAC_CA_CARD_SUPPORT = true;
            HIFAC_MGK_TVS_KEY_FILENAME = "MGK_9980_toshiba_4K_JP";
            HIFAC_WIDEVINE_KEY_FILENAME_PREFIX = SystemProperties.get("ro.vt.wide_vine_name_prefix", "WIDEVNMTK99800000000HS");
            HIFAC_HDCP14_KEY_FILENAME_PREFIX = SystemProperties.get("ro.vt.hdcp_1_4_name_prefix", "HDCP14MTK00000000000HS");
            HIFAC_HDCP2X_KEY_FILENAME_PREFIX = SystemProperties.get("ro.vt.hdcp_2_x_name_prefix", "HDCP22MTK99800000000HS");
            HIFAC_MGK_TVS_KEY_FILENAME_PREFIX = SystemProperties.get("ro.vt.mgk_name_prefix", "MGK000MTK9980TOS4KJPHS");
            HIFAC_MGK_KEY_FILENAME_PREFIX = SystemProperties.get("ro.vt.mgk_name_prefix", "MGK000MTK9980HIS8K00HS");
            HIFAC_MGK_OEM_KEY_FILENAME_PREFIX = SystemProperties.get("ro.vt.mgk_name_prefix", "MGK000MTK9980OEM8K00HS");
            HIFAC_DTCPIP_KEY_FILENAME_PREFIX = SystemProperties.get("ro.vt.dtcpip_name_prefix", "DTCPIPCOMMON_TV");
            HIFAC_MARLIN_KEY_FILENAME_PREFIX = SystemProperties.get("ro.vt.marlin_name_prefix", "MARLINCOMMON_TV");
            HIFAC_SQV_KEY_FILENAME_PREFIX = SystemProperties.get("ro.vt.sqv_name_prefix", "SQV000COMMON_TV");
            HIFAC_PVR_KEY_FILENAME_PREFIX = SystemProperties.get("ro.vt.pvr_name_prefix", "PVR000COMMON_TV");
        } else if (str.equalsIgnoreCase("sa")) {
            HIFAC_MARKET = MarketType.SA;
            HIFAC_MARKET_SA = true;
            HIFAC_PLAY_READY_40KEY_SUPPORT = true;
            HIFAC_WIDEVINE_KEY_FILENAME_PREFIX = SystemProperties.get("ro.vt.wide_vine_name_prefix", "WIDEVNMTK9900GTV0000HS");
            HIFAC_HDCP14_KEY_FILENAME_PREFIX = SystemProperties.get("ro.vt.hdcp_1_4_name_prefix", "HDCP14MTK00000000000HS");
            HIFAC_HDCP2X_KEY_FILENAME_PREFIX = SystemProperties.get("ro.vt.hdcp_2_x_name_prefix", "HDCP22MTK99000000000HS");
            HIFAC_MGK_KEY_FILENAME_PREFIX = SystemProperties.get("ro.vt.mgk_name_prefix", "MGK000MTK9900HISGTV0HS");
            HIFAC_MGK_OEM_KEY_FILENAME_PREFIX = SystemProperties.get("ro.vt.mgk_name_prefix", "MGK000MTK9900OEMGTV0HS");
            HIFAC_WIDE_VINE_KEY_FILENAME = "WIDEVNGTV_9900";
            HIFAC_MGK_KEY_FILENAME = "MGKHISGTV_9900";
            HIFAC_MGK_OEM_KEY_FILENAME = "MGKOEMGTV_9900";
        }
        HIFAC_IS_LASER4K = SystemProperties.getInt("ro.vt.product.laser4k", 0) == 1;
        HIFAC_REGION5_SUPPORT = SystemProperties.getInt("ro.vt.market.extend_model", 0) == 1;
        HIFAC_MAC_FILENAME = SystemProperties.get("ro.vt.usb_mac_file", "mac-mtk-9980-smart-em.txt");
        if (HIFAC_MARKET_JP) {
            HIFAC_MGK_KEY_SUPPORT = false;
        }
        if (HIFAC_IS_LASER4K) {
            QDA_NAME = "QDA0MTK9900HISLA003031";
            QDA_FLODER_NAME = "QDAGTV_9900_hisense_Laser";
            QDA_LCA_NAME = "QDA0MTK9900LEIGLA03034";
            QDA_LCA_FLODER_NAME = "QDAGTV_9900_Leica_Laser";
            HIFAC_MGK_KEY_FILENAME_PREFIX = SystemProperties.get("ro.vt.mgk_name_prefix", "MGK000MTK9900HISGLA0HS");
            HIFAC_MGK_KEY_FILENAME = "MGKHISGLA_9900";
            HIFAC_MGK_OEM_KEY_FILENAME_PREFIX = SystemProperties.get("ro.vt.mgk_name_prefix", "MGK000MTK9900OEMGLA0HS");
            HIFAC_MGK_OEM_KEY_FILENAME = "MGKOEMGLA_9900";
        }
        if (HIFAC_MARKET == MarketType.EM) {
            if (str2.equalsIgnoreCase("MT9900")) {
                HIFAC_MAC_FILENAME = "mac-mtk-9900-smart-em.txt";
            } else if (str2.equalsIgnoreCase("MT9980")) {
                HIFAC_MAC_FILENAME = "mac-mtk-9980-smart-em.txt";
            }
        } else if (HIFAC_MARKET == MarketType.SA) {
            if (str2.equalsIgnoreCase("MT9900")) {
                HIFAC_MAC_FILENAME = "mac-mtk-9900-smart-sa.txt";
            } else if (str2.equalsIgnoreCase("MT9980")) {
                HIFAC_MAC_FILENAME = "mac-mtk-9980-smart-sa.txt";
            }
        } else if (HIFAC_MARKET == MarketType.JP) {
            HIFAC_MAC_FILENAME = "mac-mtk-9980-smart-jp.txt";
        } else if (HIFAC_MARKET_KO && HIFAC_MARKET == MarketType.US) {
            HIFAC_MAC_FILENAME = SystemProperties.get("ro.vt.usb_mac_file", "mac-mtk-9900-smart-us.txt");
        } else {
            HIFAC_MAC_FILENAME = "mac-mtk-9900-smart-us.txt";
        }
        HIFAC_PROJECT_ID_FILENAME = SystemProperties.get("ro.vt.usb_project_id_file", "project-id-mtk-9980-smart-em.txt");
        if (HIFAC_MARKET == MarketType.EM) {
            if (str2.equalsIgnoreCase("MT9900")) {
                HIFAC_PROJECT_ID_FILENAME = "project-id-mtk-9900-smart-em.txt";
            } else if (str2.equalsIgnoreCase("MT9980")) {
                HIFAC_PROJECT_ID_FILENAME = "project-id-mtk-9980-smart-em.txt";
            }
        } else if (HIFAC_MARKET == MarketType.SA) {
            if (str2.equalsIgnoreCase("MT9900")) {
                HIFAC_PROJECT_ID_FILENAME = "project-id-mtk-9900-smart-sa.txt";
            } else if (str2.equalsIgnoreCase("MT9980")) {
                HIFAC_PROJECT_ID_FILENAME = "project-id-mtk-9980-smart-sa.txt";
            }
        } else if (HIFAC_MARKET == MarketType.JP) {
            HIFAC_PROJECT_ID_FILENAME = "project-id-mtk-9980-smart-jp.txt";
        } else {
            HIFAC_PROJECT_ID_FILENAME = "project-id-mtk-9900-smart-us.txt";
        }
        HIFAC_MIN_VCOM_SPAN = SystemProperties.getInt("ro.vt.vcom_min_range", 0);
        HIFAC_MAX_VCOM_SPAN = SystemProperties.getInt("ro.vt.vcom_max_range", 255);
        HIFAC_MAX_VBYONE_SPAN = SystemProperties.getInt("ro.vt.max_vbyone_span", 1000);
        HIFAC_VBYONE_SPAN_STEP = SystemProperties.getInt("ro.vt.fac.vbyone_span_step", 10);
        HIFAC_MAX_VBYONE_MODU = SystemProperties.getInt("ro.vt.max_vbyone_step", 100);
        HIFAC_MIN_VBYONE_MODU = SystemProperties.getInt("ro.vt.min_vbyone_step", 20);
        HIFAC_MAX_DDRSSC_SPAN = SystemProperties.getInt("ro.vt.max_ddrssc_span", 100);
        HIFAC_MIN_DDRSSC_SPAN = SystemProperties.getInt("ro.vt.min_ddrssc_span", 0);
        HIFAC_MAX_DDRSSC_MODU = SystemProperties.getInt("ro.vt.max_ddrssc_step", 60);
        HIFAC_MIN_DDRSSC_MODU = SystemProperties.getInt("ro.vt.min_ddrssc_step", 20);
        HIFAC_CI_CARD_SUPPORT = SystemProperties.getBoolean("CI_CARD.Supported", false);
        HIFAC_VENDOR = SystemProperties.get("ro.vendor", "mtk");
        SystemProperties.getBoolean("ro.vt.frc_support", false);
        SystemProperties.getInt("ro.vt.fac.lcm_tcon_support", 0);
        if ("tas5707".equalsIgnoreCase(SystemProperties.get("ro.vt.amplifier_type", "tas5751"))) {
            HIFAC_AMP_MODEL = AmplifierModel.TAS5707;
        } else if ("tas5727".equalsIgnoreCase(SystemProperties.get("ro.vt.amplifier_type", "tas5751"))) {
            HIFAC_AMP_MODEL = AmplifierModel.TAS5727;
        } else if ("tas5733".equalsIgnoreCase(SystemProperties.get("ro.vt.amplifier_type", "tas5751"))) {
            HIFAC_AMP_MODEL = AmplifierModel.TAS5733;
        } else if ("ntp8212".equalsIgnoreCase(SystemProperties.get("ro.vt.amplifier_type", "tas5751"))) {
            HIFAC_AMP_MODEL = AmplifierModel.NTP8212;
        } else if ("ntp8214".equalsIgnoreCase(SystemProperties.get("ro.vt.amplifier_type", "tas5751")) || "ntp8204".equalsIgnoreCase(SystemProperties.get("ro.vt.amplifier_type", "tas5751"))) {
            HIFAC_AMP_MODEL = AmplifierModel.NTP8214;
        } else if (!"tas5751".equalsIgnoreCase(SystemProperties.get("ro.vt.amplifier_type", "tas5751")) && "ntp8822".equalsIgnoreCase(SystemProperties.get("ro.vt.amplifier_type", "tas5751"))) {
            HIFAC_AMP_MODEL = AmplifierModel.NTP8822;
        } else {
            HIFAC_AMP_MODEL = AmplifierModel.TAS5751;
        }
        HIFAC_4K_VIDEO_SUPPORT = SystemProperties.getBoolean("ro.vt.2K_4K.Supported", false);
        if (SystemProperties.getBoolean("BLUETOOTH.Supported", true)) {
            HIFAC_F7_MODEL = F7_Wifi_Bluetooth.F7_WIFI_AND_BLUETOOTH;
        } else {
            HIFAC_F7_MODEL = F7_Wifi_Bluetooth.F7_WIFI;
        }
        SystemProperties.getBoolean("ro.vt.amp_with_treble_bass", false);
        HIFAC_AMP_WOOFER_SUPPORT = SystemProperties.getBoolean("ro.vt.amp_woofer_supported", false);
        SystemProperties.getBoolean("ro.vt.dtv_ns_nvod_support", true);
        SystemProperties.getBoolean("ro.vt.atv_ns_support", true);
        SystemProperties.getInt("ro.vt.tv.blu_support", 0);
        if ("dvbc_dtmb".equalsIgnoreCase(SystemProperties.get("ro.vt.dtv_support_type", "dvbc_dtmb"))) {
            HIFAC_DTV_TYPES_SUPPORT = DtvSupportType.DTV_DVBC_DTMB;
        } else if ("not_supported".equalsIgnoreCase(SystemProperties.get("ro.vt.dtv_support_type", "dvbc_dtmb"))) {
            HIFAC_DTV_TYPES_SUPPORT = DtvSupportType.DTV_NOT_SUPPORT;
        } else if ("dtmb".equalsIgnoreCase(SystemProperties.get("ro.vt.dtv_support_type", "dvbc_dtmb"))) {
            HIFAC_DTV_TYPES_SUPPORT = DtvSupportType.DTV_DTMB;
        } else {
            HIFAC_DTV_TYPES_SUPPORT = DtvSupportType.DTV_DVBC;
        }
        HIFAC_LOCALDIMMING_SUPPORT = SystemProperties.getInt("ro.vt..tv.lcdm_support", 0) == 1;
        HIFAC_MARKET_MONITOR = SystemProperties.getBoolean("ro.vt.product.monitor", false);
        SystemProperties.getBoolean("ro.vt.3d_sync_delay_supported", true);
        SystemProperties.getInt("ro.jamdeo.hardware_3d_support", 0);
        HIFAC_WIDE_VINE_KEY_SUPPORT = SystemProperties.getBoolean("ro.vt.widevine_key_supported", true);
        HIFAC_CHINA_DRM_KEY_SUPPORT = SystemProperties.getBoolean("ro.vt.chinadrm_key_supported", false);
        HIFAC_PLAY_READY_KEY_SUPPORT = SystemProperties.getBoolean("ro.vt.playready_key_supported", true);
        HIFAC_HDCP_KEY_SUPPORT = SystemProperties.getBoolean("ro.vt.hdcp_key_supported", false);
        HIFAC_HDCP_1_4_KEY_SUPPORT = SystemProperties.getBoolean("ro.vt.hdcp_1_4_key_supported", true);
        HIFAC_HDCP_2_X_KEY_SUPPORT = SystemProperties.getBoolean("ro.vt.hdcp_2_x_key_supported", true);
        HIFAC_CI_PLUS_KEY_SUPPORT = SystemProperties.getBoolean("ro.vt.ci_plus_key_supported", false);
        HIFAC_TEE_ENVIRON_SUPPORT = SystemProperties.getBoolean("ro.tee_environ_supported", false);
        HIFAC_CHINA_DRM_KEY_FILENAME = SystemProperties.get("ro.vt.china_drm_file_name", "CHINADRMKEY");
        HIFAC_CHINA_DRM_KEY_FILENAME_PREFIX = SystemProperties.get("ro.vt.china_drm_name_prefix");
        HIFAC_MIC_SUPPORT = SystemProperties.getBoolean("ro.vt.ffga", false);
        HIFAC_BLUETOOTH_SUPPORT = SystemProperties.getBoolean("ro.vt.bluetooth.Supported", true);
        SystemProperties.getBoolean("ro.vt.adc_supported", false);
        HIFAC_PICTURECURVE_POINT_COUNT = SystemProperties.getInt("ro.vt.pic_curve_nums", 3);
        HIFAC_USB_COUNT = SystemProperties.getInt("ro.vt.usb_num", 3);
        HIFAC_SSC_SUPPORT = SystemProperties.getBoolean("ro.vt.ssc_supported", true);
        HIFAC_AVC_SUPPORT = SystemProperties.getBoolean("ro.vt.avc_supported", false);
        SystemProperties.getBoolean("ro.vt.overscan_tblr_supported", true);
        SystemProperties.getBoolean("ro.vt.colortemp_cut_supported", false);
        SystemProperties.getBoolean("ro.vt.wb_pattern_supported", false);
        SystemProperties.get("ro.vt.innertcon_picture", "1920_1080").equalsIgnoreCase("3840_2160");
        if ("mstar".equalsIgnoreCase(HIFAC_VENDOR)) {
            HIFAC_UART_DEBUG_SUPPORT = true;
            HIFAC_MIN_BALANCE = -10;
            HIFAC_MAX_BALANCE = 10;
        }
        if ("hisi".equalsIgnoreCase(HIFAC_VENDOR)) {
            HIFAC_MIN_BALANCE = -10;
            HIFAC_MAX_BALANCE = 10;
        }
        SystemProperties.getBoolean("ro.vt.hilink_supported", false);
        HIFAC_TYPE_C_SUPPORT = SystemProperties.getInt("ro.vt.tv.seperate", 0) == 1;
        if (SystemProperties.getBoolean("ro.vt.tcon_7bit", false)) {
            HIFAC_STEP_VOM_VOLTAGE = 2;
        }
        HIFAC_WAVES_SUPPORT = SystemProperties.getBoolean("ro.vt.waves_supported", false);
        HIFAC_BYPASS_SUPPORT = SystemProperties.getBoolean("ro.vt.bypass_supported", true);
        HIFAC_SPORTS_MODE_SUPPORT = SystemProperties.getInt("ro.vt.sports_mode_supported", 0) == 1;
        SystemProperties.get("ro.vt.vcom_display_resolution", "4k");
        HIFAC_SHARP_LOGO_SUPPORT = SystemProperties.getBoolean("ro.vt.sharp_supported", false);
        HIFAC_MAX_SHARPNESS_UI = SystemProperties.getInt("ro.vt.factory.sharpness_max_range", 20);
        HIFAC_BRAND = SystemProperties.get("ro.product.brand", "");
        SystemProperties.getInt("ro.vt.factory.autotest.writekey", 0);
        SystemProperties.getBoolean("ro.vt.uifac.key_in_eeprom", false);
        HIFAC_AUDIO_MORE_EQUALIZER = SystemProperties.getInt("persist.vt.customer.media.equalizer.num", 5) != 5;
        HIFAC_AUDIO_MORE_CURVE = SystemProperties.getInt("persist.vt.customer.media.audiocurve.num", 5) != 5;
        HIFAC_IS_OLED_TVS = SystemProperties.getInt("ro.vt.tvs_panel_type_oled", 0) == 1;
        HIFAC_IS_OLED = SystemProperties.getBoolean("ro.vt.product.oled", false);
        HIFAC_LASER_IS_L9 = SystemProperties.get("ro.vt.product.laser_light_mode", "L9").equals("L9");
        HIFAC_LASER_FOCUS_SUPPORT = SystemProperties.getBoolean("ro.vt.product.laser_electric_focus", false);
        HIFAC_ATSC30_SUPPORT = SystemProperties.getBoolean("ro.vt.atsc3", false);
        HIFAC_LASER_PROJECTOR = SystemProperties.getBoolean("ro.vt.product.laser_projector", false);
        HIFAC_PRODUCT_OEM = SystemProperties.getBoolean("ro.vt.product.oem", false);
        updateConfigFromFile();
    }

    public static void setVcomProp(int i) {
        HIFAC_VCOM_SUPPORT = i;
    }

    public static void setDualVcomProp(boolean z) {
        HIFAC_DOUBLE_CELL_SUPPORT = z;
    }

    private static void updateConfigFromFile() {
        List<String> contentFromFile = FacFileUtils.getContentFromFile("/hisprod/config/factory/factory_data_file.txt");
        if (contentFromFile == null || contentFromFile.size() <= 0) {
            return;
        }
        for (String str : contentFromFile) {
            if (str.contains("auto_gamma_file_path")) {
                FacParamManager.FAC_AUTO_GAMMA_FILE_PATH = getDataContent(str);
            } else if (str.contains("dmp_file_name")) {
                FacParamManager.FAC_DMP_FILE_NAME = getDataContent(str);
            } else if (str.contains("auto_gamma_bin_file_path")) {
                FacParamManager.FAC_AUTO_GAMMA_BIN_FILE_PATH = getDataContent(str);
            } else if (str.contains("hifac_light_sensor_support")) {
                if (getDataContent(str).equals("true")) {
                    HIFAC_LIGHT_SENSOR_SUPPORT = true;
                } else {
                    HIFAC_LIGHT_SENSOR_SUPPORT = false;
                }
            } else if (str.contains("hifac_mixbar_number")) {
                HIFAC_KEYBOARD_NUM = Integer.parseInt(getDataContent(str));
            } else if (str.contains("hifac_demura_switch_support")) {
                if (getDataContent(str).equals("true")) {
                    HIFAC_DEMURA_SWITCH_SUPPORT = true;
                } else {
                    HIFAC_DEMURA_SWITCH_SUPPORT = false;
                }
            } else if (str.contains("tvs_audio_balance_type")) {
                String dataContent = getDataContent(str);
                if (dataContent.equals("1")) {
                    HIFAC_TVS_BALANCE_LIKE_650 = true;
                } else if (dataContent.equals("2")) {
                    HIFAC_TVS_BALANCE_LIKE_550 = true;
                } else if (dataContent.equals(MtkTvRatingConvert2Goo.RATING_STR_3)) {
                    HIFAC_TVS_BALANCE_LIKE_850 = true;
                }
            }
        }
    }

    private static String getDataContent(String str) {
        String strSubstring = str.substring(str.indexOf("=") + 1);
        Log.e("Fac_FacPropertyDemo", "getDataContent content = " + strSubstring);
        return strSubstring;
    }
}
