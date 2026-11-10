package com.mediatek.twoworlds.tv;

import android.util.Log;
import b.a.a.a.a;
import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;
import com.mediatek.twoworlds.tv.model.MtkTvChannelInfoBase;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvScanDvbsBase {
    public static final String DVBS_ITR_DIVEO_BR_PRE = "BR [Region ";
    public static final String DVBS_ITR_DIVEO_MDR_PRE = "MDR [Region ";
    public static final String DVBS_ITR_DIVEO_NDR_PRE = "NDR [Region ";
    public static final String DVBS_ITR_DIVEO_RBB_PRE = "RBB [Region ";
    public static final String DVBS_ITR_DIVEO_SWR_PRE = "SWR [Region ";
    public static final String DVBS_ITR_DIVEO_WDR_PRE = "WDR [Region ";
    public static final String DVBS_ITR_ORF_PRE = "ORF [Region ";
    public static final int DVBS_OPERATOR_NAME_ASTRA_HD_PLUS = 1;
    public static final int DVBS_OPERATOR_NAME_ASTRA_INTERNATIONAL_LCN = 22;
    public static final int DVBS_OPERATOR_NAME_AUSTRIASAT = 3;
    public static final int DVBS_OPERATOR_NAME_CANALDIGITAAL_HD = 4;
    public static final int DVBS_OPERATOR_NAME_CANALDIGITAAL_SD = 5;
    public static final int DVBS_OPERATOR_NAME_CANAL_DIGITAL = 13;
    public static final int DVBS_OPERATOR_NAME_CANAL_DIGITAL_DNK = 101;
    public static final int DVBS_OPERATOR_NAME_CANAL_DIGITAL_FIN = 102;
    public static final int DVBS_OPERATOR_NAME_CANAL_DIGITAL_NOR = 103;
    public static final int DVBS_OPERATOR_NAME_CANAL_DIGITAL_SWE = 104;
    public static final int DVBS_OPERATOR_NAME_CYFRA_PLUS = 18;
    public static final int DVBS_OPERATOR_NAME_CYFROWY_POLSAT = 19;
    public static final int DVBS_OPERATOR_NAME_DEUTSCHLAND = 30;
    public static final int DVBS_OPERATOR_NAME_DIGITURK_EUTELSAT = 16;
    public static final int DVBS_OPERATOR_NAME_DIGITURK_TURKSAT = 15;
    public static final int DVBS_OPERATOR_NAME_DIGI_TV = 29;
    public static final int DVBS_OPERATOR_NAME_DIGI_TV_CZE = 114;
    public static final int DVBS_OPERATOR_NAME_DIGI_TV_CZE_MOBILE = 116;
    public static final int DVBS_OPERATOR_NAME_DIGI_TV_HUN = 112;
    public static final int DVBS_OPERATOR_NAME_DIGI_TV_ROU = 111;
    public static final int DVBS_OPERATOR_NAME_DIGI_TV_SRB = 113;
    public static final int DVBS_OPERATOR_NAME_DIGI_TV_SVK = 115;
    public static final int DVBS_OPERATOR_NAME_DIGI_TV_SVK_MAGIO = 117;
    public static final int DVBS_OPERATOR_NAME_D_SMART = 20;
    public static final int DVBS_OPERATOR_NAME_FOCUSSAT = 43;
    public static final int DVBS_OPERATOR_NAME_FOCUSSAT_SD = 47;
    public static final int DVBS_OPERATOR_NAME_FRANSAT = 17;
    public static final int DVBS_OPERATOR_NAME_FREENETTV = 37;
    public static final int DVBS_OPERATOR_NAME_FREESAT = 40;
    public static final int DVBS_OPERATOR_NAME_FREESAT_CZ = 41;
    public static final int DVBS_OPERATOR_NAME_FREESAT_CZ_SD = 45;
    public static final int DVBS_OPERATOR_NAME_FREESAT_SK = 42;
    public static final int DVBS_OPERATOR_NAME_FREESAT_SK_SD = 46;
    public static final int DVBS_OPERATOR_NAME_FREEVIEW_SAT = 28;
    public static final int DVBS_OPERATOR_NAME_HELLO = 26;
    public static final int DVBS_OPERATOR_NAME_JOYNE_BEL = 39;
    public static final int DVBS_OPERATOR_NAME_JOYNE_NLD = 38;
    public static final int DVBS_OPERATOR_NAME_M7_FAST_SCAN = 49;
    public static final int DVBS_OPERATOR_NAME_MTS = 34;
    public static final int DVBS_OPERATOR_NAME_NC_PLUS = 24;
    public static final int DVBS_OPERATOR_NAME_NNK = 14;
    public static final int DVBS_OPERATOR_NAME_NTV_PLUS = 21;
    public static final int DVBS_OPERATOR_NAME_ORS = 10;
    public static final int DVBS_OPERATOR_NAME_OTHERS = 0;
    public static final int DVBS_OPERATOR_NAME_SIMPLITV = 33;
    public static final int DVBS_OPERATOR_NAME_SKY_DEUTSCHLAND = 2;
    public static final int DVBS_OPERATOR_NAME_SMART_HD_PLUS = 23;
    public static final int DVBS_OPERATOR_NAME_SYKLINK_CZ = 8;
    public static final int DVBS_OPERATOR_NAME_SYKLINK_SK = 9;
    public static final int DVBS_OPERATOR_NAME_TELEKARTA = 35;
    public static final int DVBS_OPERATOR_NAME_TELESAT_BELGIUM = 11;
    public static final int DVBS_OPERATOR_NAME_TELESAT_LUXEMBOURG = 12;
    public static final int DVBS_OPERATOR_NAME_TIVIBU = 31;
    public static final int DVBS_OPERATOR_NAME_TIVU_SAT = 25;
    public static final int DVBS_OPERATOR_NAME_TKGS = 27;
    public static final int DVBS_OPERATOR_NAME_TRICOLOR = 32;
    public static final int DVBS_OPERATOR_NAME_TV_VLAANDEREN_HD = 6;
    public static final int DVBS_OPERATOR_NAME_TV_VLAANDEREN_SD = 7;
    public static final int DVBS_OPERATOR_NAME_UPC_DIRECT = 44;
    public static final int DVBS_OPERATOR_NAME_UPC_DIRECT_SD = 48;
    public static final int DVBS_OPERATOR_NAME_VALUE_MAX = 255;
    public static final int DVBS_OPERATOR_NAME_WHITE_LABEL_PLATFORM_LCN = 36;
    public static final int DVBS_OPERATOR_STEPS = 100;
    public static final int DVBS_PREFERRED_LIST_ID = 4;
    public static final int EXCHANGE_MAX_LEN = 256;
    public static final int EXCHANGE_MAX_PAYLOAD_LEN = 253;
    public static final int EXCHANGE_OTHER_LEN_EXCEPT_PAYLOAD_DATA = 3;
    public static final int EXCHANGE_PAYLOAD_1ST_IDX = 3;
    public static final int EXCHANGE_PAYLOAD_LEN_IDX = 2;
    public static final int EXCHANGE_RET_IDX = 1;
    public static final int EXCHANGE_TYPE_CANCEL_SCAN = 2;
    public static final int EXCHANGE_TYPE_IDX = 0;
    public static final int EXCHANGE_TYPE_M7G_CHANNEL_SEARCH_CANCEL = 6;
    public static final int EXCHANGE_TYPE_M7G_CHANNEL_SEARCH_START = 5;
    public static final int EXCHANGE_TYPE_M7G_LNB_SEARCH_CANCEL = 4;
    public static final int EXCHANGE_TYPE_M7G_LNB_SEARCH_START = 3;
    public static final int EXCHANGE_TYPE_SB_GET = 2000;
    public static final int EXCHANGE_TYPE_SB_GET_BAT_ID = 2010;
    public static final int EXCHANGE_TYPE_SB_GET_CATEGORY_NUM = 2014;
    public static final int EXCHANGE_TYPE_SB_GET_DEFAULT_LNB_CONFIG = 2009;
    public static final int EXCHANGE_TYPE_SB_GET_M7_FAST_SCAN_REGIONS_BY_IDX = 2018;
    public static final int EXCHANGE_TYPE_SB_GET_M7_FAST_SCAN_REGIONS_LIST_NUM = 2017;
    public static final int EXCHANGE_TYPE_SB_GET_M7_MAIN_SATELLITE = 2008;
    public static final int EXCHANGE_TYPE_SB_GET_M7_OPT_BY_IDX = 2021;
    public static final int EXCHANGE_TYPE_SB_GET_M7_OPT_NUM = 2020;
    public static final int EXCHANGE_TYPE_SB_GET_M7_PREF_OPT = 2019;
    public static final int EXCHANGE_TYPE_SB_GET_NFY_BAT_INFO = 2004;
    public static final int EXCHANGE_TYPE_SB_GET_NFY_GET_INFO = 2011;
    public static final int EXCHANGE_TYPE_SB_GET_NFY_MDU_DETECT = 2005;
    public static final int EXCHANGE_TYPE_SB_GET_NFY_NEW_SVC = 2003;
    public static final int EXCHANGE_TYPE_SB_GET_NFY_SAT_NAME = 2001;
    public static final int EXCHANGE_TYPE_SB_GET_NFY_SVC_UPDATE = 2002;
    public static final int EXCHANGE_TYPE_SB_GET_NUM_SCANNED_CH = 2012;
    public static final int EXCHANGE_TYPE_SB_GET_SAVE_BGM_INFO = 2007;
    public static final int EXCHANGE_TYPE_SB_GET_SB_KEY_TYPE_SAVE_ORBIT_INFO = 2015;
    public static final int EXCHANGE_TYPE_SB_GET_SPECIAL_SVC_LIST = 2016;
    public static final int EXCHANGE_TYPE_SB_GET_TABLE_VERSION = 2013;
    public static final int EXCHANGE_TYPE_SB_GET_UI_SATE_TRANSPONDER = 2006;
    public static final int EXCHANGE_TYPE_SB_SET = 1000;
    public static final int EXCHANGE_TYPE_SB_SET_DISABLE_AUTO_UPDATE = 1018;
    public static final int EXCHANGE_TYPE_SB_SET_M7_DISABLE_VER_CHG_NFY = 1026;
    public static final int EXCHANGE_TYPE_SB_SET_M7_PREF_OPT = 1025;
    public static final int EXCHANGE_TYPE_SB_SET_SAVE_BGM_INFO = 1003;
    public static final int EXCHANGE_TYPE_SB_SET_SB_KEY_TYPE_SAVE_ORBIT_INFO = 1022;
    public static final int EXCHANGE_TYPE_SB_SET_TABLE_VERSION = 1019;
    public static final int EXCHANGE_TYPE_SB_SET_UI_22K = 1004;
    public static final int EXCHANGE_TYPE_SB_SET_UI_DEFT_SATE_INFO_BY_OPT = 1001;
    public static final int EXCHANGE_TYPE_SB_SET_UI_DEFT_SATE_INFO_BY_OP_NAME = 1024;
    public static final int EXCHANGE_TYPE_SB_SET_UI_DISEQC_10_DISABLE = 1006;
    public static final int EXCHANGE_TYPE_SB_SET_UI_DISEQC_10_PORT = 1007;
    public static final int EXCHANGE_TYPE_SB_SET_UI_DISEQC_10_RESET = 1009;
    public static final int EXCHANGE_TYPE_SB_SET_UI_DISEQC_10_TONE_BURST = 1008;
    public static final int EXCHANGE_TYPE_SB_SET_UI_DISEQC_11_DISABLE = 1020;
    public static final int EXCHANGE_TYPE_SB_SET_UI_DISEQC_11_PORT = 1021;
    public static final int EXCHANGE_TYPE_SB_SET_UI_DISEQC_12_DISABLE_LIMITS = 1010;
    public static final int EXCHANGE_TYPE_SB_SET_UI_DISEQC_12_GOTO_POS = 1014;
    public static final int EXCHANGE_TYPE_SB_SET_UI_DISEQC_12_GOTO_POS_XX = 1023;
    public static final int EXCHANGE_TYPE_SB_SET_UI_DISEQC_12_LIMIT_EAST = 1011;
    public static final int EXCHANGE_TYPE_SB_SET_UI_DISEQC_12_LIMIT_WEST = 1012;
    public static final int EXCHANGE_TYPE_SB_SET_UI_DISEQC_12_MOVE_EAST = 1015;
    public static final int EXCHANGE_TYPE_SB_SET_UI_DISEQC_12_MOVE_WEST = 1016;
    public static final int EXCHANGE_TYPE_SB_SET_UI_DISEQC_12_STOP_MOVE = 1017;
    public static final int EXCHANGE_TYPE_SB_SET_UI_DISEQC_12_STORE_POS = 1013;
    public static final int EXCHANGE_TYPE_SB_SET_UI_LNB_POWER = 1005;
    public static final int EXCHANGE_TYPE_SB_SET_UI_SATE_TRANSPONDER = 1002;
    public static final int EXCHANGE_TYPE_SB_TKGS = 3000;
    public static final int EXCHANGE_TYPE_SB_TKGS_ADD_ONE_VISIBLE_LOCATOR = 3004;
    public static final int EXCHANGE_TYPE_SB_TKGS_CLEAN_ALL_HIDDEN_LOCATORS = 3002;
    public static final int EXCHANGE_TYPE_SB_TKGS_CLEAN_ALL_VISIBLE_LOCATORS = 3011;
    public static final int EXCHANGE_TYPE_SB_TKGS_DEL_ONE_VISIBLE_LOCATOR = 3005;
    public static final int EXCHANGE_TYPE_SB_TKGS_GET_ALL_HIDDEN_LOCATORS = 3001;
    public static final int EXCHANGE_TYPE_SB_TKGS_GET_ALL_SVC_LIST = 3008;
    public static final int EXCHANGE_TYPE_SB_TKGS_GET_ALL_VISIBLE_LOCATORS = 3003;
    public static final int EXCHANGE_TYPE_SB_TKGS_GET_CATEGORY_NUN = 3010;
    public static final int EXCHANGE_TYPE_SB_TKGS_IS_SAME_LOCATOR = 3007;
    public static final int EXCHANGE_TYPE_SB_TKGS_SEL_PREF_SVC_LIST = 3009;
    public static final int EXCHANGE_TYPE_SB_TKGS_UPD_ONE_VISIBLE_LOCATOR = 3006;
    public static final int EXCHANGE_TYPE_SCAN = 0;
    public static final int EXCHANGE_TYPE_START_SCAN = 1;
    public static final int ISO_639_LANG_LEN = 4;
    public static final int MAX_BAT_NAME_LEN = 32;
    public static final int MAX_NFY_DATA_REC_NAME_LEN = 32;
    public static final int MAX_OPT_NAME_LEN = 64;
    public static final int MAX_PROG_NAME_LEN = 64;
    public static final int MAX_SAT_NAME_LEN = 65;
    public static final int MAX_SAT_NUM = 4;
    public static final int MAX_SVC_LIST_NAME_LEN = 32;
    public static final int MAX_USER_MESSAGE_LEN = 64;
    public static final int SB_DVBS_CONFIG_BGM_SCAN = 65536;
    public static final int SB_DVBS_CONFIG_INSTALL_FREE_SVC_ONLY = 8;
    public static final int SB_DVBS_CONFIG_INSTALL_RADIO_SVC_ONLY = 32;
    public static final int SB_DVBS_CONFIG_INSTALL_SCRAMBLE_SVC_ONLY = 262144;
    public static final int SB_DVBS_CONFIG_INSTALL_TV_RADIO_SVC_ONLY = 64;
    public static final int SB_DVBS_CONFIG_INSTALL_TV_SVC_ONLY = 16;
    public static final int SB_DVBS_GENERAL_LIST_ID = 3;
    public static final int SB_DVBS_GET_INFO_MASK_FREESAT_INS_DEFT_PC = 32;
    public static final int SB_DVBS_GET_INFO_MASK_LIST_TO_SELECT = 16;
    public static final int SB_DVBS_GET_INFO_MASK_SVC_UPD_NFY_DATA = 8;
    public static final int SB_DVBS_GET_INFO_MASK_TKGS_SAME_VERSION = 1;
    public static final int SB_DVBS_GET_INFO_MASK_TKGS_SVC_LIST = 4;
    public static final int SB_DVBS_GET_INFO_MASK_TKGS_USER_MESSAGE = 2;
    public static final int SB_DVBS_GET_INFO_MASK_VERSION_CHANGE = 64;
    public static final int SB_DVBS_INVALID_RECORD_ID = 0;
    public static final int SB_DVBS_INVALID_VALUE = -1;
    public static final int SB_DVBS_PID_INVALID_VALUE = 8191;
    public static final int SB_DVBS_PREFERRED_LIST_ID = 4;
    public static final int SB_DVBS_TABLE_INVALID_VERSION = 255;
    public static final String TAG = "MtkTvScanDvbs";
    public static final String TVAPI_DVBS_CATEGORY_INFO_BY_IDX = "g_tkgs__category_info_by_idx";
    public static final String TVAPI_DVBS_GRP_CATEGORY_PREFIX = "g_category__";
    public static final String TVAPI_DVBS_GRP_TKGS_PREFIX = "g_tkgs__";
    public static final String TVAPI_DVBS_LINK_STRING = "-";
    public static final String TVAPI_DVBS_NFY_LIST_NAME_BY_IDX = "nfy_list_name_by_idx";
    public static final String TVAPI_DVBS_NFY_SAT_NAME = "nfy_sat_name";
    public static final String TVAPI_DVBS_TKGS_CATEGORY_INFO_BY_IDX = "g_tkgs__category_info_by_idx";
    public static final String TVAPI_DVBS_TKGS_USER_MESSAGE = "g_tkgs__user_message";
    public M7OneOPT[] M7_OptLists;
    public int M7_OptNum;
    public int M7_PrefOptNWID;
    public int M7_PrefOptSLID;
    public TKGSOneSvcList[] TKGS_AllSvcLists;
    public int TKGS_PrefSvcListNo;
    public int TKGS_SvclistNum;
    public int TKGS_allHiddenLocators;
    public int TKGS_allVisibleLocators;
    public TKGSOneLocator[] TKGS_hiddenLocatorsList;
    public Boolean TKGS_isSameLocator;
    public TKGSOneLocator[] TKGS_visibleLocatorsList;
    public DvbsM7MainSatelliteIdx getM7MainSat_idx;
    public OneLNBConfig[] lnbConfig_lnbList;
    public int lnbConfig_lnbNum;
    public int nfyBatInfo_batNum;
    public int nfyGetInfo_appAdded;
    public int nfyGetInfo_appDelete;
    public int nfyGetInfo_lstNfyNum;
    public int nfyGetInfo_mask;
    public int nfyGetInfo_orbitPos;
    public int nfyGetInfo_radioAdded;
    public int nfyGetInfo_radioDelete;
    public int nfyGetInfo_satlRecID;
    public int nfyGetInfo_tvAdded;
    public int nfyGetInfo_tvDelete;
    public String nfyGetInfo_usrMessage;
    public TunerMduType nfyMduDetect_mduType;
    public TunerMduType nfySatName_mduType;
    public int nfySatName_orbPos;
    public String nfySatName_satName;
    public int nfySatName_satlRecId;
    public int nfySvcUpd_apAdd;
    public int nfySvcUpd_apDel;
    public int nfySvcUpd_rdAdd;
    public int nfySvcUpd_rdDel;
    public int nfySvcUpd_tvAdd;
    public int nfySvcUpd_tvDel;
    public int numCH_dataScanned;
    public int numCH_radioScanned;
    public int numCH_tvScanned;
    public OneBatData[] nyfBatInfo_batList;
    public OneRECNfyData[] nyfGetInfo_list;
    public int svc_RNum;
    public SvcOneRegion[] svc_RegionLists;
    public int getBat_id = -1;
    public MtkTvScanTpInfo nfyGetInfo_tpInfo = new MtkTvScanTpInfo();
    public int tkgs_category_num = 0;
    public int getTable_version = 255;
    public int category_num = 0;

    public enum DvbsM7MainSatelliteIdx {
        M7_MAIN_SATELLITE_IDX_INVALID,
        M7_MAIN_SATELLITE_IDX_1,
        M7_MAIN_SATELLITE_IDX_2,
        M7_MAIN_SATELLITE_IDX_3,
        M7_MAIN_SATELLITE_IDX_4
    }

    public enum DvbsTableType {
        DVBS_TABLE_TYPE_UNKNOWN,
        DVBS_TABLE_TYPE_TKGS
    }

    public class M7OneOPT {
        public static final int I4C_ONE_OPT_LEN = 159;
        public int deftCharSet;
        public int pareContRate;
        public boolean spclRegnSetup;
        public boolean subsEnabled;
        public int[] orbitInfo = new int[4];
        public int optNWId = -1;
        public int optSLId = -1;
        public String optName = "";
        public String subOptName = "";
        public String country = "";
        public String menuLangCode = "";
        public String aud1LangCode = "";
        public String aud2LangCode = "";
        public String subsLangCode = "";
        public int orbitNum = 0;

        public M7OneOPT() {
        }
    }

    public class MtkTvSbDvbsBGMData {
        public static final int I4C_DVBS_BGM_DATA_LEN = 51;
        public static final String TAG = "MtkTvSbDvbsBGMData";
        public OneBGMData[] bgmData_List = new OneBGMData[4];
        public int i4SatlID = 3;
        public int i4DvbsOperatorName = 0;
        public int i4ScanTimes = 0;

        public class OneBGMData {
            public static final int I4C_DVBS_ONE_BGM_DATA_LEN = 12;
            public SbDvbsScanType eSbDvbsScanType = SbDvbsScanType.SB_DVBS_SCAN_TYPE_UNKNOWN;
            public int i4EngCfgFlag = 0;
            public int i4SatRecID = 0;
            public MtkTvSbDvbsNetworkScanInfo networkScanInfo;

            public OneBGMData() {
                this.networkScanInfo = MtkTvScanDvbsBase.this.new MtkTvSbDvbsNetworkScanInfo();
            }
        }

        public MtkTvSbDvbsBGMData() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void dispatchData(int[] iArr) {
            this.i4SatlID = iArr[0];
            this.i4DvbsOperatorName = iArr[1];
            this.i4ScanTimes = iArr[2];
            for (int i = 0; i < this.i4ScanTimes && i < 4; i++) {
                this.bgmData_List[i] = new OneBGMData();
                int i2 = i * 12;
                this.bgmData_List[i].eSbDvbsScanType = SbDvbsScanType.values()[iArr[i2 + 3]];
                OneBGMData[] oneBGMDataArr = this.bgmData_List;
                oneBGMDataArr[i].i4EngCfgFlag = iArr[i2 + 4];
                oneBGMDataArr[i].i4SatRecID = iArr[i2 + 5];
                if (oneBGMDataArr[i].eSbDvbsScanType == SbDvbsScanType.SB_DVBS_SCAN_TYPE_NETWORK_SCAN) {
                    int[] iArr2 = new int[9];
                    System.arraycopy(iArr, i2 + 6, iArr2, 0, 9);
                    this.bgmData_List[i].networkScanInfo.dispatchData(iArr2);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void patchData(int[] iArr) {
            iArr[0] = this.i4SatlID;
            iArr[1] = this.i4DvbsOperatorName;
            iArr[2] = this.i4ScanTimes;
            for (int i = 0; i < this.i4ScanTimes && i < 4; i++) {
                int i2 = i * 12;
                iArr[i2 + 3] = this.bgmData_List[i].eSbDvbsScanType.ordinal();
                OneBGMData[] oneBGMDataArr = this.bgmData_List;
                iArr[i2 + 4] = oneBGMDataArr[i].i4EngCfgFlag;
                iArr[i2 + 5] = oneBGMDataArr[i].i4SatRecID;
                if (oneBGMDataArr[i].eSbDvbsScanType == SbDvbsScanType.SB_DVBS_SCAN_TYPE_NETWORK_SCAN) {
                    int[] iArr2 = new int[9];
                    oneBGMDataArr[i].networkScanInfo.patchData(iArr2);
                    System.arraycopy(iArr2, 0, iArr, i2 + 6, 9);
                }
            }
        }
    }

    public class MtkTvSbDvbsNetworkScanInfo {
        public static final int I4C_DVBS_NETWORK_SCAN_INFO_LEN = 9;
        public static final int SB_DVBS_SCAN_NUM_ASSOCIATED_SATL_REC_ID = 3;
        public static final String TAG = "MtkTvSbDvbsNetworkScanInfo";
        public MtkTvScanTpInfo tpInfo;
        public int[] aAssocSatlRec = new int[3];
        public int i4NetworkID = -1;
        public int i4BatID = -1;
        public int i4AssocSatlRecNum = 0;

        public MtkTvSbDvbsNetworkScanInfo() {
            this.tpInfo = MtkTvScanDvbsBase.this.new MtkTvScanTpInfo();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void dispatchData(int[] iArr) {
            this.i4NetworkID = iArr[0];
            this.i4BatID = iArr[1];
            this.i4AssocSatlRecNum = iArr[2];
            for (int i = 0; i < this.i4AssocSatlRecNum && i < 3; i++) {
                this.aAssocSatlRec[i] = iArr[i + 3];
            }
            int[] iArr2 = new int[3];
            System.arraycopy(iArr, 6, iArr2, 0, 3);
            this.tpInfo.dispatchData(iArr2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void patchData(int[] iArr) {
            iArr[0] = this.i4NetworkID;
            iArr[1] = this.i4BatID;
            iArr[2] = this.i4AssocSatlRecNum;
            for (int i = 0; i < this.i4AssocSatlRecNum && i < 3; i++) {
                iArr[i + 3] = this.aAssocSatlRec[i];
            }
            int[] iArr2 = new int[3];
            this.tpInfo.patchData(iArr2);
            System.arraycopy(iArr2, 0, iArr, 6, 3);
        }
    }

    public class MtkTvSbDvbsPresetScanInfo {
        public static final int I4C_DVBS_PRESET_SCAN_INFO_LEN = 2;
        public static final String TAG = "MtkTvSbDvbsPresetScanInfo";
        public Boolean bIsNetworkSearch = false;
        public int i4NetworkID = -1;

        public MtkTvSbDvbsPresetScanInfo() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void dispatchData(int[] iArr) {
            StringBuilder sbA = a.a("");
            sbA.append(iArr[0]);
            this.bIsNetworkSearch = Boolean.valueOf(sbA.toString());
            this.i4NetworkID = iArr[1];
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void patchData(int[] iArr) {
            iArr[0] = this.bIsNetworkSearch.booleanValue() ? 1 : 0;
            iArr[1] = this.i4NetworkID;
        }
    }

    public class MtkTvSbDvbsSatSearchInfo {
        public static final String TAG = "MtkTvSbDvbsSatSearchInfo";
        public Boolean bIsCustomizedLNB = false;

        public MtkTvSbDvbsSatSearchInfo() {
        }

        public static /* synthetic */ void access$500(MtkTvSbDvbsSatSearchInfo mtkTvSbDvbsSatSearchInfo, int[] iArr) {
            iArr[0] = mtkTvSbDvbsSatSearchInfo.bIsCustomizedLNB.booleanValue() ? 1 : 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void dispatchData(int[] iArr) {
            StringBuilder sbA = a.a("");
            sbA.append(iArr[0]);
            this.bIsCustomizedLNB = Boolean.valueOf(sbA.toString());
        }

        private void patchData(int[] iArr) {
            iArr[0] = this.bIsCustomizedLNB.booleanValue() ? 1 : 0;
        }
    }

    public class MtkTvSbDvbsScanData {
        public static final int I4C_DVBS_SCAN_DATA_LEN = 18;
        public static final String TAG = "MtkTvSbDvbsScanData";
        public MtkTvSbDvbsScanInfo scanInfo = new MtkTvSbDvbsScanInfo();
        public int i4SatlID = 3;
        public int i4SatlRecID = 0;
        public int i4DvbsOperatorName = 0;
        public SbDvbsScanType eSbDvbsScanType = SbDvbsScanType.SB_DVBS_SCAN_TYPE_UNKNOWN;
        public int i4EngCfgFlag = 0;
        public Boolean bIsBgm = false;
        public Boolean bIsGetInfoStep = false;
        public Boolean bIsMduDetect = false;
        public Boolean bIsM7PortDetect = false;

        public class MtkTvSbDvbsScanInfo {
            public static final int I4C_SCAN_INFO_LEN = 9;
            public static final String TAG = "MtkTvSbDvbsScanInfo";
            public SbDvbsScanType eSbDvbsScanType = SbDvbsScanType.SB_DVBS_SCAN_TYPE_UNKNOWN;
            public MtkTvSbDvbsNetworkScanInfo networkScanInfo;
            public MtkTvSbDvbsPresetScanInfo presetScanInfo;
            public MtkTvScanTpInfo satCheckInfo;
            public MtkTvSbDvbsSatSearchInfo satSearchInfo;
            public MtkTvScanTpInfo singleTpScanInfo;

            public MtkTvSbDvbsScanInfo() {
                this.singleTpScanInfo = MtkTvScanDvbsBase.this.new MtkTvScanTpInfo();
                this.networkScanInfo = MtkTvScanDvbsBase.this.new MtkTvSbDvbsNetworkScanInfo();
                this.presetScanInfo = MtkTvScanDvbsBase.this.new MtkTvSbDvbsPresetScanInfo();
                this.satSearchInfo = MtkTvScanDvbsBase.this.new MtkTvSbDvbsSatSearchInfo();
                this.satCheckInfo = MtkTvScanDvbsBase.this.new MtkTvScanTpInfo();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void dispatchData(int[] iArr) {
                SbDvbsScanType sbDvbsScanType = this.eSbDvbsScanType;
                if (sbDvbsScanType == SbDvbsScanType.SB_DVBS_SCAN_TYPE_SINGLE_TP_SCAN) {
                    this.singleTpScanInfo.dispatchData(iArr);
                    return;
                }
                if (sbDvbsScanType == SbDvbsScanType.SB_DVBS_SCAN_TYPE_NETWORK_SCAN) {
                    this.networkScanInfo.dispatchData(iArr);
                    return;
                }
                if (sbDvbsScanType == SbDvbsScanType.SB_DVBS_SCAN_TYPE_PRESET_MODE) {
                    this.presetScanInfo.dispatchData(iArr);
                } else if (sbDvbsScanType == SbDvbsScanType.SB_DVBS_SCAN_TYPE_SAT_SEARCH) {
                    this.satSearchInfo.dispatchData(iArr);
                } else if (sbDvbsScanType == SbDvbsScanType.SB_DVBS_SCAN_TYPE_SAT_CHECK) {
                    this.satCheckInfo.dispatchData(iArr);
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void patchData(int[] iArr) {
                SbDvbsScanType sbDvbsScanType = this.eSbDvbsScanType;
                if (sbDvbsScanType == SbDvbsScanType.SB_DVBS_SCAN_TYPE_SINGLE_TP_SCAN) {
                    this.singleTpScanInfo.patchData(iArr);
                    return;
                }
                if (sbDvbsScanType == SbDvbsScanType.SB_DVBS_SCAN_TYPE_NETWORK_SCAN) {
                    this.networkScanInfo.patchData(iArr);
                    return;
                }
                if (sbDvbsScanType == SbDvbsScanType.SB_DVBS_SCAN_TYPE_PRESET_MODE) {
                    this.presetScanInfo.patchData(iArr);
                } else if (sbDvbsScanType == SbDvbsScanType.SB_DVBS_SCAN_TYPE_SAT_SEARCH) {
                    MtkTvSbDvbsSatSearchInfo.access$500(this.satSearchInfo, iArr);
                } else if (sbDvbsScanType == SbDvbsScanType.SB_DVBS_SCAN_TYPE_SAT_CHECK) {
                    this.satCheckInfo.patchData(iArr);
                }
            }
        }

        public MtkTvSbDvbsScanData() {
        }

        private void dispatchData(int[] iArr) {
            this.i4SatlID = iArr[0];
            this.i4SatlRecID = iArr[1];
            this.i4DvbsOperatorName = iArr[2];
            this.eSbDvbsScanType = SbDvbsScanType.values()[iArr[3]];
            this.i4EngCfgFlag = iArr[4];
            StringBuilder sbA = a.a("");
            sbA.append(iArr[5]);
            this.bIsBgm = Boolean.valueOf(sbA.toString());
            StringBuilder sbA2 = a.a("");
            sbA2.append(iArr[6]);
            this.bIsGetInfoStep = Boolean.valueOf(sbA2.toString());
            StringBuilder sbA3 = a.a("");
            sbA3.append(iArr[7]);
            this.bIsMduDetect = Boolean.valueOf(sbA3.toString());
            StringBuilder sbA4 = a.a("");
            sbA4.append(iArr[8]);
            this.bIsM7PortDetect = Boolean.valueOf(sbA4.toString());
            int[] iArr2 = new int[9];
            System.arraycopy(iArr, 9, iArr2, 0, 9);
            this.scanInfo.dispatchData(iArr2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void patchData(int[] iArr) {
            iArr[0] = this.i4SatlID;
            iArr[1] = this.i4SatlRecID;
            iArr[2] = this.i4DvbsOperatorName;
            iArr[3] = this.eSbDvbsScanType.ordinal();
            iArr[4] = this.i4EngCfgFlag;
            iArr[5] = this.bIsBgm.booleanValue() ? 1 : 0;
            iArr[6] = this.bIsGetInfoStep.booleanValue() ? 1 : 0;
            iArr[7] = this.bIsMduDetect.booleanValue() ? 1 : 0;
            iArr[8] = this.bIsM7PortDetect.booleanValue() ? 1 : 0;
            int[] iArr2 = new int[9];
            this.scanInfo.eSbDvbsScanType = this.eSbDvbsScanType;
            this.scanInfo.patchData(iArr2);
            System.arraycopy(iArr2, 0, iArr, 9, 9);
        }
    }

    public class MtkTvScanTpInfo {
        public static final int I4C_TP_INFO_LEN = 3;
        public static final String TAG = "MtkTvScanTpInfo";
        public int i4Frequency = 0;
        public int i4Symbolrate = 0;
        public TunerPolarizationType ePol = TunerPolarizationType.POL_UNKNOWN;

        public MtkTvScanTpInfo() {
        }

        public void dispatchData(int[] iArr) {
            this.i4Frequency = iArr[0];
            this.i4Symbolrate = iArr[1];
            this.ePol = TunerPolarizationType.values()[iArr[2]];
        }

        public void patchData(int[] iArr) {
            iArr[0] = this.i4Frequency;
            iArr[1] = this.i4Symbolrate;
            iArr[2] = this.ePol.ordinal();
        }
    }

    public class OneBatData {
        public static final int I4C_ONE_BAT_DATA_LEN = 33;
        public int batId = -1;
        public String batName = "";

        public OneBatData() {
        }
    }

    public class OneLNBConfig {
        public static final int I4C_ONE_LNB_CONFIG_LEN = 4;
        public TunerLnbType lnbType = TunerLnbType.TUNER_LNB_TYPE_UNKNOWN;
        public int lowFreq = 0;
        public int highFreq = 0;
        public int switchFreq = 0;

        public OneLNBConfig() {
        }
    }

    public class OneRECNfyData {
        public static final int I4C_ONE_REC_NFY_DATA_LEN = 33;
        public int recId = -1;
        public String recName = "";

        public OneRECNfyData() {
        }
    }

    public class OrbitInfo {
        public static final int I4C_ORBIT_INFO_LEN = 5;
        public int[] scannedOrbit;
        public int scannedOrbitLen = 0;

        public OrbitInfo() {
        }
    }

    public enum SbDvbsScanType {
        SB_DVBS_SCAN_TYPE_UNKNOWN,
        SB_DVBS_SCAN_TYPE_AUTO_MODE,
        SB_DVBS_SCAN_TYPE_PRESET_MODE,
        SB_DVBS_SCAN_TYPE_NETWORK_SCAN,
        SB_DVBS_SCAN_TYPE_SINGLE_TP_SCAN,
        SB_DVBS_SCAN_TYPE_UPDATE_SCAN,
        SB_DVBS_SCAN_TYPE_FREQ_MANUAL_SCAN,
        SB_DVBS_SCAN_TYPE_SAT_CHECK,
        SB_DVBS_SCAN_TYPE_SAT_SEARCH,
        SB_DVBS_SCAN_TYPE_COMPLETE,
        SB_DVBS_SCAN_TYPE_NUM
    }

    public enum ScanDvbsRet {
        SCAN_DVBS_RET_OK,
        SCAN_DVBS_RET_INTERNAL_ERROR,
        SCAN_DVBS_RET_INV_ARG
    }

    public class SvcOneRegion {
        public static final int I4C_ONE_REGION_LEN = 66;
        public int svcRecId = 0;
        public String svcName = "";

        public SvcOneRegion() {
        }
    }

    public class TKGSOneSvcList {
        public static final int I4C_ONE_SVC_LIST_LEN = 33;
        public int svcListNo = -1;
        public String svcListName = "";

        public TKGSOneSvcList() {
        }
    }

    public enum TunerLnbPower {
        TUNER_LNB_POWER_OFF,
        TUNER_LNB_POWER_13V_18V,
        TUNER_LNB_POWER_14V_19V,
        TUNER_LNB_POWER_ON
    }

    public enum TunerLnbType {
        TUNER_LNB_TYPE_UNKNOWN,
        TUNER_LNB_TYPE_SINGLE_FREQ,
        TUNER_LNB_TYPE_DUAL_FREQ
    }

    public enum TunerMduType {
        TUNER_MDU_TYPE_UNKNOWN,
        TUNER_MDU_TYPE_1,
        TUNER_MDU_TYPE_2,
        TUNER_MDU_TYPE_3,
        TUNER_MDU_TYPE_4,
        TUNER_MDU_TYPE_5
    }

    public enum TunerPolarizationType {
        POL_UNKNOWN(0),
        POL_LIN_HORIZONTAL(1),
        POL_LIN_VERTICAL(2),
        POL_CIR_LEFT(3),
        POL_CIR_RIGHT(4);

        public final int value;

        TunerPolarizationType(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    private String convertAsciiArrayToString(int[] iArr) {
        StringBuilder sb = new StringBuilder();
        if (iArr == null) {
            return null;
        }
        for (int i = 0; i < iArr.length && iArr[i] != 0; i++) {
            sb.append((char) iArr[i]);
        }
        return sb.toString();
    }

    private int[] convertStringToAsciiArray(String str) {
        byte[] bytes = str.getBytes();
        int[] iArr = new int[bytes.length];
        for (int iA = 0; iA < bytes.length; iA = a.a(new StringBuilder(), iArr[iA], "\n", TAG, iA, 1)) {
            iArr[iA] = bytes[iA];
        }
        return iArr;
    }

    private ScanDvbsRet dvbsSetDisableAutoUpdateForCrnt(Boolean bool) {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        int[] iArr = {1, bool.booleanValue() ? 1 : 0};
        Log.d(TAG, "Enter dvbsSetDisableAutoUpdateForCrnt, isDisAutoUpd=" + bool + "\n");
        ScanDvbsRet scanDvbsRetExchange = exchange(1018, iArr);
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsSetDisableAutoUpdateForCrnt\n");
        }
        Log.d(TAG, "Leave dvbsSetDisableAutoUpdateForCrnt\n");
        return scanDvbsRetExchange;
    }

    private ScanDvbsRet exchange(int i, int[] iArr) {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        int length = iArr.length > 0 ? 3 + (iArr.length - 1) : 3;
        a.a("totalLen: ", length, "\n", TAG);
        if (length > 256) {
            Log.d(TAG, "[Error]bigger than EXCHANGE_MAX_LEN\n");
            return ScanDvbsRet.SCAN_DVBS_RET_INTERNAL_ERROR;
        }
        int[] iArr2 = new int[length];
        int i2 = 0;
        iArr2[0] = i;
        int i3 = -1;
        iArr2[1] = -1;
        int i4 = 2;
        int i5 = 0;
        int i6 = 2;
        while (i5 < iArr.length) {
            iArr2[i6] = iArr[i5];
            i5++;
            i6++;
        }
        int iScanDvbsExchangeData_native = TVNativeWrapper.ScanDvbsExchangeData_native(iArr2);
        if (iScanDvbsExchangeData_native >= 0) {
            int i7 = iArr2[0];
            int i8 = iArr2[1];
            if (i8 >= 0 && i7 == i) {
                while (i2 < iArr.length) {
                    iArr[i2] = iArr2[i4];
                    i2++;
                    i4++;
                }
            }
            i3 = i8;
        }
        return (iScanDvbsExchangeData_native < 0 || i3 < 0) ? ScanDvbsRet.SCAN_DVBS_RET_INTERNAL_ERROR : ScanDvbsRet.SCAN_DVBS_RET_OK;
    }

    private String getNfyInfo_recName_ByIdx(int i) {
        Log.d(TAG, "Enter getNfyInfo_recName_ByIdx:" + i + "\n");
        String str = TVAPI_DVBS_NFY_LIST_NAME_BY_IDX + TVAPI_DVBS_LINK_STRING + Integer.toString(i);
        a.a("str_recIdx:", str, "\n", TAG);
        String userMessage = getUserMessage(str);
        a.a("Leave getNfyInfo_recName_ByIdx:", userMessage, "\n", TAG);
        return userMessage;
    }

    private String getNfy_satName() {
        Log.d(TAG, "Enter getNfy_satName\n");
        String userMessage = getUserMessage(TVAPI_DVBS_NFY_SAT_NAME);
        a.a("Leave getNfy_satName:", userMessage, "\n", TAG);
        return userMessage;
    }

    private String getUserMessage(String str) {
        Log.d(TAG, "Enter getUserMessage:" + str + "\n");
        String strScanDvbsgetUserMessage_native = TVNativeWrapper.ScanDvbsgetUserMessage_native(str);
        StringBuilder sbA = a.a("Leave getUserMessage, return value = ");
        sbA.append(strScanDvbsgetUserMessage_native == null ? "null" : strScanDvbsgetUserMessage_native);
        sbA.append("\n");
        Log.d(TAG, sbA.toString());
        return strScanDvbsgetUserMessage_native;
    }

    public ScanDvbsRet dvbsCancelScan() {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        Log.d(TAG, "Enter dvbsCancelScan\n");
        ScanDvbsRet scanDvbsRetExchange = exchange(2, new int[]{0});
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsCancelScan\n");
        }
        Log.d(TAG, "Leave dvbsCancelScan\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsGetBatId(int i) {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        int[] iArr = new int[254];
        Log.d(TAG, "Enter dvbsGetBatId\n");
        iArr[0] = 253;
        iArr[1] = i;
        ScanDvbsRet scanDvbsRetExchange = exchange(EXCHANGE_TYPE_SB_GET_BAT_ID, iArr);
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsGetBatId\n");
            return scanDvbsRetExchange;
        }
        int i2 = iArr[0];
        a.a("payLoadLen:", i2, "\n", TAG);
        if (i2 > 0) {
            this.getBat_id = iArr[1];
            a.a(a.a("getBat_id:"), this.getBat_id, "\n", TAG);
        }
        Log.d(TAG, "Leave dvbsGetBatId\n");
        return scanDvbsRetExchange;
    }

    public String dvbsGetCategoryInfoByIdx(int i) {
        Log.d(TAG, "Enter dvbsGetCategoryInfoByIdx:" + i + "\n");
        String str = "g_tkgs__category_info_by_idx" + TVAPI_DVBS_LINK_STRING + Integer.toString(i);
        a.a("str_categoryIdx:", str, "\n", TAG);
        String userMessage = getUserMessage(str);
        a.a("Leave dvbsGetCategoryInfoByIdx:", userMessage, "\n", TAG);
        return userMessage;
    }

    public ScanDvbsRet dvbsGetCategoryNum() {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        int[] iArr = new int[254];
        Log.d(TAG, "Enter dvbsGetCategoryNum\n");
        iArr[0] = 253;
        ScanDvbsRet scanDvbsRetExchange = exchange(EXCHANGE_TYPE_SB_GET_CATEGORY_NUM, iArr);
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsGetCategoryNum\n");
            return scanDvbsRetExchange;
        }
        int i = iArr[0];
        a.a("payLoadLen:", i, "\n", TAG);
        if (i > 0) {
            this.category_num = iArr[1];
            a.a(a.a("category_num:"), this.category_num, "\n", TAG);
        }
        Log.d(TAG, "Leave dvbsGetCategoryNum\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsGetDefaultLNBConfig() {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        int[] iArr = new int[254];
        Log.d(TAG, "Enter dvbsGetDefaultLNBConfig\n");
        int i = 0;
        iArr[0] = 253;
        ScanDvbsRet scanDvbsRetExchange = exchange(EXCHANGE_TYPE_SB_GET_DEFAULT_LNB_CONFIG, iArr);
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsGetDefaultLNBConfig\n");
            return scanDvbsRetExchange;
        }
        int iA = iArr[0];
        a.a("payLoadLen:", iA, "\n", TAG);
        int i2 = 1;
        if (iA > 0) {
            this.lnbConfig_lnbNum = iArr[1];
            iA = a.a(a.a("lnbConfig_lnbNum:"), this.lnbConfig_lnbNum, "\n", TAG, iA, -1);
            i2 = 2;
        }
        int i3 = this.lnbConfig_lnbNum;
        if (i3 > 0) {
            this.lnbConfig_lnbList = new OneLNBConfig[i3];
            while (i < this.lnbConfig_lnbNum && iA >= 4) {
                this.lnbConfig_lnbList[i] = new OneLNBConfig();
                int i4 = i2 + 1;
                this.lnbConfig_lnbList[i].lnbType = TunerLnbType.values()[iArr[i2]];
                OneLNBConfig[] oneLNBConfigArr = this.lnbConfig_lnbList;
                int i5 = i4 + 1;
                oneLNBConfigArr[i].lowFreq = iArr[i4];
                int i6 = i5 + 1;
                oneLNBConfigArr[i].highFreq = iArr[i5];
                oneLNBConfigArr[i].switchFreq = iArr[i6];
                StringBuilder sbA = a.a("lnbType:");
                sbA.append(this.lnbConfig_lnbList[i].lnbType);
                sbA.append("\n");
                Log.d(TAG, sbA.toString());
                Log.d(TAG, "lowFreq:" + this.lnbConfig_lnbList[i].lowFreq + "\n");
                Log.d(TAG, "highFreq:" + this.lnbConfig_lnbList[i].highFreq + "\n");
                StringBuilder sb = new StringBuilder();
                sb.append("switchFreq:");
                iA = a.a(sb, this.lnbConfig_lnbList[i].switchFreq, "\n", TAG, iA, -4);
                i++;
                i2 = i6 + 1;
            }
        }
        Log.d(TAG, "Leave dvbsGetDefaultLNBConfig\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsGetM7MainSatIdx(int i) {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        int[] iArr = new int[254];
        Log.d(TAG, "Enter dvbsGetM7MainSatIdx\n");
        iArr[0] = 253;
        iArr[1] = i;
        ScanDvbsRet scanDvbsRetExchange = exchange(EXCHANGE_TYPE_SB_GET_M7_MAIN_SATELLITE, iArr);
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsGetM7MainSatIdx\n");
            return scanDvbsRetExchange;
        }
        int i2 = iArr[0];
        a.a("payLoadLen:", i2, "\n", TAG);
        if (i2 > 0) {
            this.getM7MainSat_idx = DvbsM7MainSatelliteIdx.values()[iArr[1]];
            StringBuilder sbA = a.a("getM7MainSat_idx:");
            sbA.append(this.getM7MainSat_idx);
            sbA.append("\n");
            Log.d(TAG, sbA.toString());
        }
        Log.d(TAG, "Leave dvbsGetM7MainSatIdx\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsGetNfyBatInfo() {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        int[] iArr = new int[254];
        int[] iArr2 = new int[32];
        Log.d(TAG, "Enter dvbsGetNfyBatInfo\n");
        iArr[0] = 253;
        ScanDvbsRet scanDvbsRetExchange = exchange(EXCHANGE_TYPE_SB_GET_NFY_BAT_INFO, iArr);
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsGetNfyBatInfo\n");
            return scanDvbsRetExchange;
        }
        int iA = iArr[0];
        a.a("payLoadLen:", iA, "\n", TAG);
        int i = 1;
        if (iA > 0) {
            this.nfyBatInfo_batNum = iArr[1];
            iA = a.a(a.a("nfyBatInfo_batNum:"), this.nfyBatInfo_batNum, "\n", TAG, iA, -1);
            i = 2;
        }
        int i2 = this.nfyBatInfo_batNum;
        if (i2 > 0) {
            this.nyfBatInfo_batList = new OneBatData[i2];
            int i3 = i;
            for (int i4 = 0; i4 < this.nfyBatInfo_batNum && iA >= 33; i4++) {
                this.nyfBatInfo_batList[i4] = new OneBatData();
                int i5 = i3 + 1;
                this.nyfBatInfo_batList[i4].batId = iArr[i3];
                a.a(a.a("batId:"), this.nyfBatInfo_batList[i4].batId, "\n", TAG);
                System.arraycopy(iArr, i5, iArr2, 0, 32);
                this.nyfBatInfo_batList[i4].batName = convertAsciiArrayToString(iArr2);
                StringBuilder sbA = a.a("batName:");
                sbA.append(this.nyfBatInfo_batList[i4].batName);
                sbA.append("\n");
                Log.d(TAG, sbA.toString());
                i3 = i5 + 32;
                iA -= 33;
            }
        }
        Log.d(TAG, "Leave dvbsGetNfyBatInfo\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsGetNfyGetInfo() {
        int i;
        int iA;
        int i2;
        String str;
        int iA2;
        int iA3;
        int i3;
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        int[] iArr = new int[254];
        int[] iArr2 = new int[3];
        int[] iArr3 = new int[64];
        int[] iArr4 = new int[32];
        Log.d(TAG, "Enter dvbsGetNfyGetInfo\n");
        iArr[0] = 253;
        ScanDvbsRet scanDvbsRetExchange = exchange(EXCHANGE_TYPE_SB_GET_NFY_GET_INFO, iArr);
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsGetNfyGetInfo\n");
            return scanDvbsRetExchange;
        }
        int iA4 = iArr[0];
        a.a("payLoadLen:", iA4, "\n", TAG);
        if (iA4 > 0) {
            i = 2;
            this.nfyGetInfo_satlRecID = iArr[1];
            iA4 = a.a(a.a("nfyGetInfo_satlRecID:"), this.nfyGetInfo_satlRecID, "\n", TAG, iA4, -1);
        } else {
            i = 1;
        }
        if (iA4 > 0) {
            this.nfyGetInfo_orbitPos = iArr[i];
            iA = a.a(a.a("nfyGetInfo_orbitPos:"), this.nfyGetInfo_orbitPos, "\n", TAG, iA4, -1);
            i2 = i + 1;
        } else {
            iA = iA4;
            i2 = i;
        }
        if (iA >= 3) {
            System.arraycopy(iArr, i2, iArr2, 0, 3);
            this.nfyGetInfo_tpInfo.dispatchData(iArr2);
            StringBuilder sb = new StringBuilder();
            sb.append("nfyGetInfo_tpInfo_freq:");
            sb.append(this.nfyGetInfo_tpInfo.i4Frequency);
            str = r12;
            sb.append(str);
            Log.d(TAG, sb.toString());
            Log.d(TAG, "nfyGetInfo_tpInfo_Pol:" + this.nfyGetInfo_tpInfo.ePol + str);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("nfyGetInfo_tpInfo_SymbolRate:");
            iA2 = a.a(sb2, this.nfyGetInfo_tpInfo.i4Symbolrate, str, TAG, i2, 3);
            iA3 = iA + (-3);
        } else {
            str = r12;
            iA2 = i2;
            iA3 = iA;
        }
        if (iA3 > 0) {
            i3 = iA2 + 1;
            this.nfyGetInfo_mask = iArr[iA2];
            iA3 = a.a(a.a("nfyGetInfo_mask:"), this.nfyGetInfo_mask, str, TAG, iA3, -1);
        } else {
            i3 = iA2;
        }
        if (iA3 >= 64) {
            this.nfyGetInfo_usrMessage = getUserMessage(TVAPI_DVBS_TKGS_USER_MESSAGE);
            StringBuilder sbA = a.a("nfyGetInfo_usrMessage:");
            sbA.append(this.nfyGetInfo_usrMessage);
            sbA.append(str);
            Log.d(TAG, sbA.toString());
            i3 += 64;
            iA3 -= 64;
        }
        if (iA3 > 0) {
            this.nfyGetInfo_tvAdded = iArr[i3];
            i3++;
            iA3 = a.a(a.a("nfyGetInfo_tvAdded:"), this.nfyGetInfo_tvAdded, str, TAG, iA3, -1);
        }
        if (iA3 > 0) {
            this.nfyGetInfo_tvDelete = iArr[i3];
            i3++;
            iA3 = a.a(a.a("nfyGetInfo_tvDelete:"), this.nfyGetInfo_tvDelete, str, TAG, iA3, -1);
        }
        if (iA3 > 0) {
            this.nfyGetInfo_radioAdded = iArr[i3];
            i3++;
            iA3 = a.a(a.a("nfyGetInfo_radioAdded:"), this.nfyGetInfo_radioAdded, str, TAG, iA3, -1);
        }
        if (iA3 > 0) {
            this.nfyGetInfo_radioDelete = iArr[i3];
            i3++;
            iA3 = a.a(a.a("nfyGetInfo_radioDelete:"), this.nfyGetInfo_radioDelete, str, TAG, iA3, -1);
        }
        if (iA3 > 0) {
            this.nfyGetInfo_appAdded = iArr[i3];
            i3++;
            iA3 = a.a(a.a("nfyGetInfo_appAdded:"), this.nfyGetInfo_appAdded, str, TAG, iA3, -1);
        }
        if (iA3 > 0) {
            this.nfyGetInfo_appDelete = iArr[i3];
            i3++;
            iA3 = a.a(a.a("nfyGetInfo_appDelete:"), this.nfyGetInfo_appDelete, str, TAG, iA3, -1);
        }
        if (iA3 > 0) {
            this.nfyGetInfo_lstNfyNum = iArr[i3];
            iA3 = a.a(a.a("nfyGetInfo_lstNfyNum:"), this.nfyGetInfo_lstNfyNum, str, TAG, iA3, -1);
            i3++;
        }
        int i4 = this.nfyGetInfo_lstNfyNum;
        if (i4 > 0) {
            this.nyfGetInfo_list = new OneRECNfyData[i4];
            for (int i5 = 0; i5 < this.nfyGetInfo_lstNfyNum && iA3 >= 33; i5++) {
                this.nyfGetInfo_list[i5] = new OneRECNfyData();
                int i6 = i3 + 1;
                this.nyfGetInfo_list[i5].recId = iArr[i3];
                a.a(a.a("recId:"), this.nyfGetInfo_list[i5].recId, str, TAG);
                System.arraycopy(iArr, i6, iArr4, 0, 32);
                this.nyfGetInfo_list[i5].recName = convertAsciiArrayToString(iArr4);
                StringBuilder sbA2 = a.a("recName:");
                sbA2.append(this.nyfGetInfo_list[i5].recName);
                sbA2.append(str);
                Log.d(TAG, sbA2.toString());
                this.nyfGetInfo_list[i5].recName = getNfyInfo_recName_ByIdx(i5);
                StringBuilder sbA3 = a.a("recName-by userMessage:");
                sbA3.append(this.nyfGetInfo_list[i5].recName);
                sbA3.append(str);
                Log.d(TAG, sbA3.toString());
                i3 = i6 + 32;
                iA3 -= 33;
            }
        }
        Log.d(TAG, "Leave dvbsGetNfyGetInfo\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsGetNfyMduDetect() {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        int[] iArr = new int[254];
        Log.d(TAG, "Enter dvbsGetNfyMduDetect\n");
        iArr[0] = 253;
        ScanDvbsRet scanDvbsRetExchange = exchange(EXCHANGE_TYPE_SB_GET_NFY_MDU_DETECT, iArr);
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsGetNfyMduDetect\n");
            return scanDvbsRetExchange;
        }
        int i = iArr[0];
        a.a("payLoadLen:", i, "\n", TAG);
        if (i > 0) {
            this.nfyMduDetect_mduType = TunerMduType.values()[iArr[1]];
            StringBuilder sbA = a.a("nfyMduDetect_mduType:");
            sbA.append(this.nfyMduDetect_mduType);
            sbA.append("\n");
            Log.d(TAG, sbA.toString());
        }
        Log.d(TAG, "Leave dvbsGetNfyMduDetect\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsGetNfySatName() {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        int[] iArr = new int[254];
        int[] iArr2 = new int[65];
        Log.d(TAG, "Enter dvbsGetNfySatName\n");
        iArr[0] = 253;
        ScanDvbsRet scanDvbsRetExchange = exchange(EXCHANGE_TYPE_SB_GET_NFY_SAT_NAME, iArr);
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsGetNfySatName\n");
            return scanDvbsRetExchange;
        }
        int iA = iArr[0];
        a.a("payLoadLen:", iA, "\n", TAG);
        int i = 1;
        if (iA > 0) {
            this.nfySatName_satlRecId = iArr[1];
            iA = a.a(a.a("nfySatName_satlRecId:"), this.nfySatName_satlRecId, "\n", TAG, iA, -1);
            i = 2;
        }
        if (iA > 0) {
            this.nfySatName_orbPos = iArr[i];
            iA = a.a(a.a("nfySatName_orbPos:"), this.nfySatName_orbPos, "\n", TAG, iA, -1);
            i++;
        }
        if (iA > 0) {
            this.nfySatName_mduType = TunerMduType.values()[iArr[i]];
            StringBuilder sbA = a.a("nfySatName_mduType:");
            sbA.append(this.nfySatName_mduType);
            sbA.append("\n");
            Log.d(TAG, sbA.toString());
            i++;
            iA--;
        }
        if (iA > 0) {
            System.arraycopy(iArr, i, iArr2, 0, iA);
            this.nfySatName_satName = getNfy_satName();
            StringBuilder sbA2 = a.a("nfySatName_satName:");
            sbA2.append(this.nfySatName_satName);
            sbA2.append("\n");
            Log.d(TAG, sbA2.toString());
        }
        Log.d(TAG, "Leave dvbsGetNfySatName\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsGetNfySvcUpd() {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        int[] iArr = new int[254];
        Log.d(TAG, "Enter dvbsGetNfySvcUpd\n");
        iArr[0] = 253;
        ScanDvbsRet scanDvbsRetExchange = exchange(EXCHANGE_TYPE_SB_GET_NFY_SVC_UPDATE, iArr);
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsGetNfySvcUpd\n");
            return scanDvbsRetExchange;
        }
        int iA = iArr[0];
        a.a("payLoadLen:", iA, "\n", TAG);
        int i = 1;
        if (iA > 0) {
            this.nfySvcUpd_tvAdd = iArr[1];
            iA = a.a(a.a("nfySvcUpd_tvAdd:"), this.nfySvcUpd_tvAdd, "\n", TAG, iA, -1);
            i = 2;
        }
        if (iA > 0) {
            this.nfySvcUpd_tvDel = iArr[i];
            iA = a.a(a.a("nfySvcUpd_tvDel:"), this.nfySvcUpd_tvDel, "\n", TAG, iA, -1);
            i++;
        }
        if (iA > 0) {
            this.nfySvcUpd_rdAdd = iArr[i];
            iA = a.a(a.a("nfySvcUpd_rdAdd:"), this.nfySvcUpd_rdAdd, "\n", TAG, iA, -1);
            i++;
        }
        if (iA > 0) {
            this.nfySvcUpd_rdDel = iArr[i];
            iA = a.a(a.a("nfySvcUpd_rdDel:"), this.nfySvcUpd_rdDel, "\n", TAG, iA, -1);
            i++;
        }
        if (iA > 0) {
            this.nfySvcUpd_apAdd = iArr[i];
            iA = a.a(a.a("nfySvcUpd_apAdd:"), this.nfySvcUpd_apAdd, "\n", TAG, iA, -1);
            i++;
        }
        if (iA > 0) {
            this.nfySvcUpd_apDel = iArr[i];
            a.a(a.a("nfySvcUpd_apDel:"), this.nfySvcUpd_apDel, "\n", TAG);
        }
        Log.d(TAG, "Leave dvbsGetNfySvcUpd\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsGetNumScannedCH() {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        int[] iArr = new int[254];
        Log.d(TAG, "Enter dvbsGetNumScannedCH\n");
        iArr[0] = 253;
        ScanDvbsRet scanDvbsRetExchange = exchange(EXCHANGE_TYPE_SB_GET_NUM_SCANNED_CH, iArr);
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsGetNumScannedCH\n");
            return scanDvbsRetExchange;
        }
        int iA = iArr[0];
        a.a("payLoadLen:", iA, "\n", TAG);
        int i = 1;
        if (iA > 0) {
            this.numCH_tvScanned = iArr[1];
            iA = a.a(a.a("numCH_tvScanned:"), this.numCH_tvScanned, "\n", TAG, iA, -1);
            i = 2;
        }
        if (iA > 0) {
            this.numCH_radioScanned = iArr[i];
            iA = a.a(a.a("numCH_radioScanned:"), this.numCH_radioScanned, "\n", TAG, iA, -1);
            i++;
        }
        if (iA > 0) {
            this.numCH_dataScanned = iArr[i];
            a.a(a.a("numCH_dataScanned:"), this.numCH_dataScanned, "\n", TAG);
        }
        Log.d(TAG, "Leave dvbsGetNumScannedCH\n");
        return scanDvbsRetExchange;
    }

    public OrbitInfo dvbsGetOrbitInfo() {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        int[] iArr = new int[254];
        Log.d(TAG, "Enter dvbsGetOribitInfo\n");
        iArr[0] = 253;
        OrbitInfo orbitInfo = null;
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != exchange(EXCHANGE_TYPE_SB_GET_SB_KEY_TYPE_SAVE_ORBIT_INFO, iArr)) {
            Log.d(TAG, "Error dvbsGetOribitInfo\n");
            return null;
        }
        int i = iArr[0];
        a.a("payLoadLen:", i, "\n", TAG);
        if (i > 0) {
            OrbitInfo orbitInfo2 = new OrbitInfo();
            int i2 = 2;
            orbitInfo2.scannedOrbitLen = iArr[1];
            a.a(a.a("scannedOrbitLen:"), orbitInfo2.scannedOrbitLen, "\n", TAG);
            int i3 = orbitInfo2.scannedOrbitLen;
            if (i3 > 0) {
                orbitInfo2.scannedOrbit = new int[i3];
                int iA = 0;
                for (int i4 = i - 1; iA < orbitInfo2.scannedOrbitLen && i4 > 0; i4--) {
                    orbitInfo2.scannedOrbit[iA] = iArr[i2];
                    iA = a.a(a.a("scannedOrbit:"), orbitInfo2.scannedOrbit[iA], "\n", TAG, iA, 1);
                    i2++;
                }
            }
            orbitInfo = orbitInfo2;
        }
        Log.d(TAG, "Leave dvbsGetOrbitInfo\n");
        return orbitInfo;
    }

    public ScanDvbsRet dvbsGetSaveBgmData(MtkTvSbDvbsBGMData mtkTvSbDvbsBGMData) {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        int[] iArr = new int[51];
        int[] iArr2 = new int[52];
        Log.d(TAG, "Enter dvbsGetSaveBgmData\n");
        iArr2[0] = 51;
        ScanDvbsRet scanDvbsRetExchange = exchange(EXCHANGE_TYPE_SB_GET_SAVE_BGM_INFO, iArr2);
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsGetSaveBgmData\n");
            return scanDvbsRetExchange;
        }
        System.arraycopy(iArr2, 1, iArr, 0, 51);
        mtkTvSbDvbsBGMData.dispatchData(iArr);
        Log.d(TAG, "Leave dvbsGetSaveBgmData\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsGetSpecialSvcList(String str) {
        int i;
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        int[] iArr = new int[254];
        MtkTvChannelListBase mtkTvChannelListBase = new MtkTvChannelListBase();
        a.a("Enter dvbsGetSpecialSvcList:", str, "\n", TAG);
        iArr[0] = 253;
        int i2 = 0;
        while (i2 < str.length()) {
            StringBuilder sbA = a.a("");
            sbA.append(str.charAt(i2));
            int i3 = i2 + 1;
            iArr[i3] = Integer.parseInt(sbA.toString());
            a.a(a.a("data[", i3, "]:"), iArr[i2], "\n", TAG);
            i2 = i3;
        }
        ScanDvbsRet scanDvbsRetExchange = exchange(EXCHANGE_TYPE_SB_GET_SPECIAL_SVC_LIST, iArr);
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsGetSpecialSvcList\n");
            return scanDvbsRetExchange;
        }
        int iA = iArr[0];
        a.a("payLoadLen:", iA, "\n", TAG);
        if (iA > 0) {
            i = 2;
            this.svc_RNum = iArr[1];
            iA = a.a(a.a("svc_RNum:"), this.svc_RNum, "\n", TAG, iA, -1);
        } else {
            i = 1;
        }
        int i4 = this.svc_RNum;
        if (i4 > 0) {
            this.svc_RegionLists = new SvcOneRegion[i4];
            for (int i5 = 0; i5 < this.svc_RNum && iA >= 1; i5++) {
                this.svc_RegionLists[i5] = new SvcOneRegion();
                this.svc_RegionLists[i5].svcRecId = iArr[i];
                a.a(a.a("svcRecId:"), this.svc_RegionLists[i5].svcRecId, "\n", TAG);
                MtkTvChannelInfoBase channelInfoBySvlRecId = mtkTvChannelListBase.getChannelInfoBySvlRecId(4, this.svc_RegionLists[i5].svcRecId);
                if (channelInfoBySvlRecId != null) {
                    this.svc_RegionLists[i5].svcName = channelInfoBySvlRecId.getServiceName();
                    StringBuilder sbA2 = a.a("svcName:");
                    sbA2.append(this.svc_RegionLists[i5].svcName);
                    sbA2.append("\n");
                    Log.d(TAG, sbA2.toString());
                }
                i++;
                iA--;
            }
        }
        Log.d(TAG, "Leave dvbsGetSpecialSvcList\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsGetTKGSCagegoryNum() {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        int[] iArr = new int[254];
        Log.d(TAG, "Enter dvbsGetTKGSCagegoryNum\n");
        iArr[0] = 253;
        ScanDvbsRet scanDvbsRetExchange = exchange(EXCHANGE_TYPE_SB_TKGS_GET_CATEGORY_NUN, iArr);
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsGetTKGSCagegoryNum\n");
            return scanDvbsRetExchange;
        }
        int i = iArr[0];
        a.a("payLoadLen:", i, "\n", TAG);
        if (i > 0) {
            this.tkgs_category_num = iArr[1];
            a.a(a.a("tkgs_category_num:"), this.tkgs_category_num, "\n", TAG);
        }
        Log.d(TAG, "Leave dvbsGetTKGSCagegoryNum\n");
        return scanDvbsRetExchange;
    }

    public String dvbsGetTKGSCategoryInfoByIdx(int i) {
        Log.d(TAG, "Enter dvbsGetTKGSCategoryInfoByIdx:" + i + "\n");
        String str = "g_tkgs__category_info_by_idx" + TVAPI_DVBS_LINK_STRING + Integer.toString(i);
        a.a("str_categoryIdx:", str, "\n", TAG);
        String userMessage = getUserMessage(str);
        a.a("Leave dvbsGetTKGSCategoryInfoByIdx:", userMessage, "\n", TAG);
        return userMessage;
    }

    public String dvbsGetTKGSUserMessage() {
        Log.d(TAG, "Enter dvbsGetTKGSUserMessage\n");
        String userMessage = getUserMessage(TVAPI_DVBS_TKGS_USER_MESSAGE);
        a.a("Leave dvbsGetTKGSUserMessage:", userMessage, "\n", TAG);
        return userMessage;
    }

    public ScanDvbsRet dvbsGetTableVersion(DvbsTableType dvbsTableType) {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        int[] iArr = new int[254];
        Log.d(TAG, "Enter dvbsGetTableVersion:" + dvbsTableType + "\n");
        iArr[0] = 253;
        iArr[1] = dvbsTableType.ordinal();
        ScanDvbsRet scanDvbsRetExchange = exchange(EXCHANGE_TYPE_SB_GET_TABLE_VERSION, iArr);
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsGetTableVersion\n");
            return scanDvbsRetExchange;
        }
        int i = iArr[0];
        a.a("payLoadLen:", i, "\n", TAG);
        if (i > 0) {
            this.getTable_version = iArr[1];
            a.a(a.a("getTable_version:"), this.getTable_version, "\n", TAG);
        }
        Log.d(TAG, "Leave dvbsGetTableVersion\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsGetUISateTransponder(int i, MtkTvScanTpInfo mtkTvScanTpInfo) {
        int i2;
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        int[] iArr = new int[254];
        a.a("Enter dvbsGetUISateTransponder: ", i, "\n", TAG);
        iArr[0] = 253;
        iArr[1] = i;
        ScanDvbsRet scanDvbsRetExchange = exchange(EXCHANGE_TYPE_SB_GET_UI_SATE_TRANSPONDER, iArr);
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsGetUISateTransponder\n");
            return scanDvbsRetExchange;
        }
        int iA = iArr[0];
        a.a("payLoadLen:", iA, "\n", TAG);
        if (iA > 0) {
            i2 = 2;
            mtkTvScanTpInfo.i4Frequency = iArr[1];
            iA = a.a(a.a("get Frequency:"), mtkTvScanTpInfo.i4Frequency, "\n", TAG, iA, -1);
        } else {
            i2 = 1;
        }
        if (iA > 0) {
            mtkTvScanTpInfo.i4Symbolrate = iArr[i2];
            iA = a.a(a.a("get Symbolrate:"), mtkTvScanTpInfo.i4Symbolrate, "\n", TAG, iA, -1);
            i2++;
        }
        if (iA > 0) {
            mtkTvScanTpInfo.ePol = TunerPolarizationType.values()[iArr[i2]];
            StringBuilder sbA = a.a("get Pol:");
            sbA.append(mtkTvScanTpInfo.ePol);
            sbA.append("\n");
            Log.d(TAG, sbA.toString());
        }
        Log.d(TAG, "Leave dvbsGetUISateTransponder\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsM7ChannelSearch(int i) {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        a.a("Enter dvbsM7ChannelSearch: ", i, "\n", TAG);
        ScanDvbsRet scanDvbsRetExchange = exchange(5, new int[]{1, i});
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsM7ChannelSearch\n");
        }
        Log.d(TAG, "Leave dvbsM7ChannelSearch\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsM7ChannelSearchCancel() {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        Log.d(TAG, "Enter dvbsM7ChannelSearchCancel\n");
        ScanDvbsRet scanDvbsRetExchange = exchange(6, new int[]{0});
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsM7ChannelSearchCancel\n");
        }
        Log.d(TAG, "Leave dvbsM7ChannelSearchCancel\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsM7DisableVerChgNfy() {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        Log.d(TAG, "Enter dvbsM7DisableVerChgNfy\n");
        ScanDvbsRet scanDvbsRetExchange = exchange(1026, new int[]{1, 1});
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsM7DisableVerChgNfy\n");
        }
        Log.d(TAG, "Leave dvbsM7DisableVerChgNfy\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsM7GetOptList() {
        char c2;
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        int[] iArr = new int[254];
        int[] iArr2 = new int[64];
        int[] iArr3 = new int[64];
        int[] iArr4 = new int[3];
        Log.d(TAG, "Enter dvbsM7GetOptList\n");
        int i = 253;
        iArr[0] = 253;
        ScanDvbsRet scanDvbsRetExchange = exchange(EXCHANGE_TYPE_SB_GET_M7_PREF_OPT, iArr);
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsM7GetOptList-EXCHANGE_TYPE_SB_GET_M7_PREF_OPT\n");
            return scanDvbsRetExchange;
        }
        int iA = iArr[0];
        a.a("payLoadLen:", iA, "\n", TAG);
        char c3 = 2;
        if (iA > 0) {
            this.M7_PrefOptNWID = iArr[1];
            iA = a.a(a.a("M7_PrefOptNWID:"), this.M7_PrefOptNWID, "\n", TAG, iA, -1);
            c2 = 2;
        } else {
            c2 = 1;
        }
        if (iA > 0) {
            this.M7_PrefOptSLID = iArr[c2];
            a.a(a.a("M7_PrefOptSLID:"), this.M7_PrefOptSLID, "\n", TAG);
        }
        iArr[0] = 253;
        ScanDvbsRet scanDvbsRetExchange2 = exchange(EXCHANGE_TYPE_SB_GET_M7_OPT_NUM, iArr);
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange2) {
            Log.d(TAG, "Error dvbsM7GetOptList-EXCHANGE_TYPE_SB_GET_M7_OPT_NUM\n");
            return scanDvbsRetExchange2;
        }
        int i2 = iArr[0];
        a.a("payLoadLen:", i2, "\n", TAG);
        if (i2 > 0) {
            this.M7_OptNum = iArr[1];
            a.a(a.a("M7_OptNum:"), this.M7_OptNum, "\n", TAG);
        }
        int i3 = this.M7_OptNum;
        if (i3 > 0) {
            this.M7_OptLists = new M7OneOPT[i3];
            ScanDvbsRet scanDvbsRetExchange3 = scanDvbsRetExchange2;
            int i4 = 0;
            while (i4 < this.M7_OptNum) {
                iArr[0] = i;
                iArr[1] = i4;
                scanDvbsRetExchange3 = exchange(EXCHANGE_TYPE_SB_GET_M7_OPT_BY_IDX, iArr);
                if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange3) {
                    Log.d(TAG, "Error dvbsM7GetOptList-EXCHANGE_TYPE_SB_GET_M7_OPT_BY_IDX\n");
                    return scanDvbsRetExchange3;
                }
                int i5 = iArr[0];
                a.a("payLoadLen:", i5, "\n", TAG);
                if (i5 >= 159) {
                    this.M7_OptLists[i4] = new M7OneOPT();
                    M7OneOPT[] m7OneOPTArr = this.M7_OptLists;
                    m7OneOPTArr[i4].optNWId = iArr[1];
                    m7OneOPTArr[i4].optSLId = iArr[c3];
                    System.arraycopy(iArr, 3, iArr2, 0, 64);
                    this.M7_OptLists[i4].optName = convertAsciiArrayToString(iArr2);
                    System.arraycopy(iArr, 67, iArr3, 0, 64);
                    this.M7_OptLists[i4].subOptName = convertAsciiArrayToString(iArr3);
                    StringBuilder sbA = a.a("optNWId:");
                    sbA.append(this.M7_OptLists[i4].optNWId);
                    sbA.append(" optSLId:");
                    sbA.append(this.M7_OptLists[i4].optSLId);
                    sbA.append(" optName:");
                    sbA.append(this.M7_OptLists[i4].optName);
                    sbA.append(" subOptName:");
                    sbA.append(this.M7_OptLists[i4].subOptName);
                    sbA.append("\n");
                    Log.d(TAG, sbA.toString());
                    System.arraycopy(iArr, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_GUIDANCE_BLOCKED_DUR_THUMB, iArr4, 0, 3);
                    this.M7_OptLists[i4].country = convertAsciiArrayToString(iArr4);
                    System.arraycopy(iArr, 135, iArr4, 0, 3);
                    this.M7_OptLists[i4].menuLangCode = convertAsciiArrayToString(iArr4);
                    System.arraycopy(iArr, 139, iArr4, 0, 3);
                    this.M7_OptLists[i4].aud1LangCode = convertAsciiArrayToString(iArr4);
                    System.arraycopy(iArr, 143, iArr4, 0, 3);
                    this.M7_OptLists[i4].aud2LangCode = convertAsciiArrayToString(iArr4);
                    System.arraycopy(iArr, 147, iArr4, 0, 3);
                    this.M7_OptLists[i4].subsLangCode = convertAsciiArrayToString(iArr4);
                    StringBuilder sbA2 = a.a("country:");
                    sbA2.append(this.M7_OptLists[i4].country);
                    sbA2.append(" menuLangCode:");
                    sbA2.append(this.M7_OptLists[i4].menuLangCode);
                    sbA2.append(" aud1LangCode:");
                    sbA2.append(this.M7_OptLists[i4].aud1LangCode);
                    sbA2.append("\n");
                    Log.d(TAG, sbA2.toString());
                    Log.d(TAG, "aud2LangCode:" + this.M7_OptLists[i4].aud2LangCode + " subsLangCode:" + this.M7_OptLists[i4].subsLangCode + "\n");
                    M7OneOPT[] m7OneOPTArr2 = this.M7_OptLists;
                    m7OneOPTArr2[i4].pareContRate = iArr[151];
                    m7OneOPTArr2[i4].deftCharSet = iArr[152];
                    m7OneOPTArr2[i4].subsEnabled = iArr[153] > 0;
                    this.M7_OptLists[i4].spclRegnSetup = iArr[154] > 0;
                    this.M7_OptLists[i4].orbitNum = iArr[155];
                    int i6 = 0;
                    while (true) {
                        M7OneOPT[] m7OneOPTArr3 = this.M7_OptLists;
                        if (i6 >= m7OneOPTArr3[i4].orbitNum || i6 >= 4) {
                            break;
                        }
                        m7OneOPTArr3[i4].orbitInfo[i6] = iArr[156 + i6];
                        i6++;
                    }
                    StringBuilder sbA3 = a.a("pareContRate:");
                    sbA3.append(this.M7_OptLists[i4].pareContRate);
                    sbA3.append(" deftCharSet:");
                    sbA3.append(this.M7_OptLists[i4].deftCharSet);
                    sbA3.append("\n");
                    Log.d(TAG, sbA3.toString());
                    Log.d(TAG, "subsEnabled:" + this.M7_OptLists[i4].subsEnabled + " spclRegnSetup:" + this.M7_OptLists[i4].spclRegnSetup + "\n");
                    StringBuilder sb = new StringBuilder();
                    sb.append("orbitNum:");
                    sb.append(this.M7_OptLists[i4].orbitNum);
                    sb.append(" 1:");
                    sb.append(this.M7_OptLists[i4].orbitInfo[0]);
                    sb.append(" 2:");
                    sb.append(this.M7_OptLists[i4].orbitInfo[1]);
                    sb.append(" 3:");
                    sb.append(this.M7_OptLists[i4].orbitInfo[2]);
                    sb.append(" 4:");
                    a.a(sb, this.M7_OptLists[i4].orbitInfo[3], "\n", TAG);
                    c3 = 2;
                }
                i4++;
                i = 253;
            }
            scanDvbsRetExchange2 = scanDvbsRetExchange3;
        }
        Log.d(TAG, "Leave dvbsM7GetOptList\n");
        return scanDvbsRetExchange2;
    }

    public ScanDvbsRet dvbsM7LNBSearch() {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        Log.d(TAG, "Enter dvbsM7LNBSearch\n");
        ScanDvbsRet scanDvbsRetExchange = exchange(3, new int[]{0});
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsM7LNBSearch\n");
        }
        Log.d(TAG, "Leave dvbsM7LNBSearch\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsM7LNBSearchCancel() {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        Log.d(TAG, "Enter dvbsM7LNBSearchCancel\n");
        ScanDvbsRet scanDvbsRetExchange = exchange(4, new int[]{0});
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsM7LNBSearchCancel\n");
        }
        Log.d(TAG, "Leave dvbsM7LNBSearchCancel\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsM7SelPrefOpt(int i, int i2) {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        Log.d(TAG, "Enter dvbsM7SelPrefOpt_prefOptNWID: " + i + "\n");
        Log.d(TAG, "Enter dvbsM7SelPrefOpt_prefOptSLID: " + i2 + "\n");
        ScanDvbsRet scanDvbsRetExchange = exchange(1025, new int[]{2, i, i2});
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsM7SelPrefOpt\n");
        }
        Log.d(TAG, "Leave dvbsM7SelPrefOpt\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsSetEnableAutoUpdateForCrnt() {
        return dvbsSetDisableAutoUpdateForCrnt(false);
    }

    public ScanDvbsRet dvbsSetOrbitInfo(OrbitInfo orbitInfo) {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        int[] iArr = new int[6];
        Log.d(TAG, "Enter dvbsSetOrbitInfo\n");
        if (orbitInfo == null) {
            Log.d(TAG, "Error dvbsSetOrbitInfo\n");
            return ScanDvbsRet.SCAN_DVBS_RET_INV_ARG;
        }
        iArr[0] = 5;
        iArr[1] = orbitInfo.scannedOrbitLen;
        for (int i = 0; i < orbitInfo.scannedOrbitLen && i < 4; i++) {
            iArr[i + 2] = orbitInfo.scannedOrbit[i];
        }
        ScanDvbsRet scanDvbsRetExchange = exchange(1022, iArr);
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsSetOrbitInfo\n");
            return scanDvbsRetExchange;
        }
        Log.d(TAG, "Leave dvbsSetOrbitInfo\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsSetSaveBgmData(MtkTvSbDvbsBGMData mtkTvSbDvbsBGMData) {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        int[] iArr = new int[51];
        int[] iArr2 = new int[52];
        Log.d(TAG, "Enter dvbsSetSaveBgmData\n");
        mtkTvSbDvbsBGMData.patchData(iArr);
        iArr2[0] = 51;
        System.arraycopy(iArr, 0, iArr2, 1, 51);
        ScanDvbsRet scanDvbsRetExchange = exchange(1003, iArr2);
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsSetSaveBgmData\n");
        }
        Log.d(TAG, "Leave dvbsSetSaveBgmData\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsSetTableVersion(DvbsTableType dvbsTableType, int i) {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        int[] iArr = {2, dvbsTableType.ordinal(), i};
        Log.d(TAG, "Enter dvbsSetTableVersion: table type" + dvbsTableType + "table ver" + i + "\n");
        ScanDvbsRet scanDvbsRetExchange = exchange(1019, iArr);
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsSetTableVersion\n");
        }
        Log.d(TAG, "Leave dvbsSetTableVersion\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsSetTuner22k(int i) {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        a.a("Enter dvbsSetTuner22k: ", i, "\n", TAG);
        ScanDvbsRet scanDvbsRetExchange = exchange(1004, new int[]{1, i});
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsSetTuner22k\n");
        }
        Log.d(TAG, "Leave dvbsSetTuner22k\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsSetTunerDiseqc10Disable() {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        Log.d(TAG, "Enter dvbsSetTunerDiseqc10Disable\n");
        ScanDvbsRet scanDvbsRetExchange = exchange(1006, new int[]{0});
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsSetTunerDiseqc10Disable\n");
        }
        Log.d(TAG, "Leave dvbsSetTunerDiseqc10Disable\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsSetTunerDiseqc10Port(int i) {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        a.a("Enter dvbsSetTunerDiseqc10Port: ", i, "\n", TAG);
        ScanDvbsRet scanDvbsRetExchange = exchange(1007, new int[]{1, i});
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsSetTunerDiseqc10Port\n");
        }
        Log.d(TAG, "Leave dvbsSetTunerDiseqc10Port\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsSetTunerDiseqc10Reset() {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        Log.d(TAG, "Enter dvbsSetTunerDiseqc10Reset\n");
        ScanDvbsRet scanDvbsRetExchange = exchange(1009, new int[]{0});
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsSetTunerDiseqc10Reset\n");
        }
        Log.d(TAG, "Leave dvbsSetTunerDiseqc10Reset\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsSetTunerDiseqc10ToneBurst(int i) {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        a.a("Enter dvbsSetTunerDiseqc10ToneBurst: ", i, "\n", TAG);
        ScanDvbsRet scanDvbsRetExchange = exchange(1008, new int[]{1, i});
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsSetTunerDiseqc10ToneBurst\n");
        }
        Log.d(TAG, "Leave dvbsSetTunerDiseqc10ToneBurst\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsSetTunerDiseqc11Disable() {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        Log.d(TAG, "Enter dvbsSetTunerDiseqc11Disable\n");
        ScanDvbsRet scanDvbsRetExchange = exchange(1020, new int[]{0});
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsSetTunerDiseqc11Disable\n");
        }
        Log.d(TAG, "Leave dvbsSetTunerDiseqc11Disable\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsSetTunerDiseqc11Port(int i) {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        a.a("Enter dvbsSetTunerDiseqc11Port: ", i, "\n", TAG);
        ScanDvbsRet scanDvbsRetExchange = exchange(1021, new int[]{1, i});
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsSetTunerDiseqc11Port\n");
        }
        Log.d(TAG, "Leave dvbsSetTunerDiseqc11Port\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsSetTunerDiseqc12DisableLimits() {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        Log.d(TAG, "Enter dvbsSetTunerDiseqc12DisableLimits\n");
        ScanDvbsRet scanDvbsRetExchange = exchange(1010, new int[]{0});
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsSetTunerDiseqc12DisableLimits\n");
        }
        Log.d(TAG, "Leave dvbsSetTunerDiseqc12DisableLimits\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsSetTunerDiseqc12GotoPos(int i) {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        a.a("Enter dvbsSetTunerDiseqc12GotoPos: ", i, "\n", TAG);
        ScanDvbsRet scanDvbsRetExchange = exchange(1014, new int[]{1, i});
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsSetTunerDiseqc12GotoPos\n");
        }
        Log.d(TAG, "Leave dvbsSetTunerDiseqc12GotoPos\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsSetTunerDiseqc12LimitEast() {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        Log.d(TAG, "Enter dvbsSetTunerDiseqc12LimitEast\n");
        ScanDvbsRet scanDvbsRetExchange = exchange(1011, new int[]{0});
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsSetTunerDiseqc12LimitEast\n");
        }
        Log.d(TAG, "Leave dvbsSetTunerDiseqc12LimitEast\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsSetTunerDiseqc12LimitWest() {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        Log.d(TAG, "Enter dvbsSetTunerDiseqc12LimitWest\n");
        ScanDvbsRet scanDvbsRetExchange = exchange(1012, new int[]{0});
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsSetTunerDiseqc12LimitWest\n");
        }
        Log.d(TAG, "Leave dvbsSetTunerDiseqc12LimitWest\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsSetTunerDiseqc12MoveEast(int i) {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        a.a("Enter dvbsSetTunerDiseqc12MoveEast: ", i, "\n", TAG);
        ScanDvbsRet scanDvbsRetExchange = exchange(1015, new int[]{1, i});
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsSetTunerDiseqc12MoveEast\n");
        }
        Log.d(TAG, "Leave dvbsSetTunerDiseqc12MoveEast\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsSetTunerDiseqc12MoveWest(int i) {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        a.a("Enter dvbsSetTunerDiseqc12MoveWest: ", i, "\n", TAG);
        ScanDvbsRet scanDvbsRetExchange = exchange(1016, new int[]{1, i});
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsSetTunerDiseqc12MoveWest\n");
        }
        Log.d(TAG, "Leave dvbsSetTunerDiseqc12MoveWest\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsSetTunerDiseqc12StopMove() {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        Log.d(TAG, "Enter dvbsSetTunerDiseqc12StopMove\n");
        ScanDvbsRet scanDvbsRetExchange = exchange(1017, new int[]{0});
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsSetTunerDiseqc12StopMove\n");
        }
        Log.d(TAG, "Leave dvbsSetTunerDiseqc12StopMove\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsSetTunerDiseqc12StorePos(int i) {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        a.a("Enter dvbsSetTunerDiseqc12StorePos: ", i, "\n", TAG);
        ScanDvbsRet scanDvbsRetExchange = exchange(1013, new int[]{1, i});
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsSetTunerDiseqc12StorePos\n");
        }
        Log.d(TAG, "Leave dvbsSetTunerDiseqc12StorePos\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsSetTunerLnbPower(int i) {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        a.a("Enter dvbsSetTunerLnbPower: ", i, "\n", TAG);
        ScanDvbsRet scanDvbsRetExchange = exchange(1005, new int[]{1, i});
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsSetTunerLnbPower\n");
        }
        Log.d(TAG, "Leave dvbsSetTunerLnbPower\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsSetUIDeftSateInfoByOpt(int i) {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        a.a("Enter dvbsSetUIDeftSateInfoByOpt: ", i, "\n", TAG);
        ScanDvbsRet scanDvbsRetExchange = exchange(1001, new int[]{1, i});
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsSetUIDeftSateInfoByOpt\n");
        }
        Log.d(TAG, "Leave dvbsSetUIDeftSateInfoByOpt\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsSetUISateTransponder(int i, MtkTvScanTpInfo mtkTvScanTpInfo) {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        int[] iArr = new int[3];
        mtkTvScanTpInfo.patchData(iArr);
        int[] iArr2 = {4, i, 0, 0, 0};
        System.arraycopy(iArr, 0, iArr2, 2, 3);
        Log.d(TAG, "Enter dvbsSetUISateTransponder: " + i + "\n");
        Log.d(TAG, "Enter dvbsSetUISateTransponder: " + mtkTvScanTpInfo.i4Frequency + "\n");
        Log.d(TAG, "Enter dvbsSetUISateTransponder: " + mtkTvScanTpInfo.i4Symbolrate + "\n");
        Log.d(TAG, "Enter dvbsSetUISateTransponder: " + mtkTvScanTpInfo.ePol + "\n");
        ScanDvbsRet scanDvbsRetExchange = exchange(1002, iArr2);
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsSetUISateTransponder\n");
        }
        Log.d(TAG, "Leave dvbsSetUISateTransponder\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsStartScan(MtkTvSbDvbsScanData mtkTvSbDvbsScanData) {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        int[] iArr = new int[18];
        int[] iArr2 = new int[19];
        Log.d(TAG, "Enter dvbsStartScan\n");
        mtkTvSbDvbsScanData.patchData(iArr);
        iArr2[0] = 18;
        System.arraycopy(iArr, 0, iArr2, 1, 18);
        ScanDvbsRet scanDvbsRetExchange = exchange(1, iArr2);
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsStartScan\n");
        }
        Log.d(TAG, "Leave dvbsStartScan\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsTKGSAddOneVisibleLocator(TKGSOneLocator tKGSOneLocator) {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        int[] iArr = new int[5];
        int[] iArr2 = new int[6];
        Log.d(TAG, "Enter dvbsTKGSAddOneVisibleLocator\n");
        Log.d(TAG, "Before add:recordID=" + tKGSOneLocator.recordID + "PID=" + tKGSOneLocator.PID + "\n");
        StringBuilder sb = new StringBuilder();
        sb.append("tpinfo:freq=");
        sb.append(tKGSOneLocator.tpInfo.i4Frequency);
        sb.append("pol=");
        sb.append(tKGSOneLocator.tpInfo.ePol);
        sb.append("symbol=");
        a.a(sb, tKGSOneLocator.tpInfo.i4Symbolrate, "\n", TAG);
        iArr2[0] = 5;
        tKGSOneLocator.patchData(iArr);
        System.arraycopy(iArr, 0, iArr2, 1, 5);
        ScanDvbsRet scanDvbsRetExchange = exchange(EXCHANGE_TYPE_SB_TKGS_ADD_ONE_VISIBLE_LOCATOR, iArr2);
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsTKGSAddOneVisibleLocator\n");
            return scanDvbsRetExchange;
        }
        if (iArr2[0] >= 5) {
            System.arraycopy(iArr2, 1, iArr, 0, 5);
            tKGSOneLocator.dispatchData(iArr);
            Log.d(TAG, "After add:recordID=" + tKGSOneLocator.recordID + "PID=" + tKGSOneLocator.PID + "\n");
            StringBuilder sb2 = new StringBuilder();
            sb2.append("tpinfo:freq=");
            sb2.append(tKGSOneLocator.tpInfo.i4Frequency);
            sb2.append("pol=");
            sb2.append(tKGSOneLocator.tpInfo.ePol);
            sb2.append("symbol=");
            a.a(sb2, tKGSOneLocator.tpInfo.i4Symbolrate, "\n", TAG);
        }
        Log.d(TAG, "Leave dvbsTKGSAddOneVisibleLocator\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsTKGSCleanAllHiddenLocators() {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        Log.d(TAG, "Enter dvbsTKGSCleanAllHiddenLocators\n");
        ScanDvbsRet scanDvbsRetExchange = exchange(EXCHANGE_TYPE_SB_TKGS_CLEAN_ALL_HIDDEN_LOCATORS, new int[]{0});
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsTKGSCleanAllHiddenLocators\n");
        }
        Log.d(TAG, "Leave dvbsTKGSCleanAllHiddenLocators\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsTKGSCleanAllVisibleLocators() {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        Log.d(TAG, "Enter dvbsTKGSCleanAllVisibleLocators\n");
        ScanDvbsRet scanDvbsRetExchange = exchange(EXCHANGE_TYPE_SB_TKGS_CLEAN_ALL_VISIBLE_LOCATORS, new int[]{0});
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsTKGSCleanAllVisibleLocators\n");
        }
        Log.d(TAG, "Leave dvbsTKGSCleanAllVisibleLocators\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsTKGSDelOneVisibleLocator(int i) {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        a.a("Enter dvbsTKGSDelOneVisibleLocator, recordID=", i, "\n", TAG);
        ScanDvbsRet scanDvbsRetExchange = exchange(EXCHANGE_TYPE_SB_TKGS_DEL_ONE_VISIBLE_LOCATOR, new int[]{1, i});
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsTKGSDelOneVisibleLocator\n");
            return scanDvbsRetExchange;
        }
        Log.d(TAG, "Leave dvbsTKGSDelOneVisibleLocator\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsTKGSGetAllHiddenLocators() {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        int[] iArr = new int[254];
        int[] iArr2 = new int[5];
        Log.d(TAG, "Enter dvbsTKGSGetAllHiddenLocators\n");
        iArr[0] = 253;
        ScanDvbsRet scanDvbsRetExchange = exchange(EXCHANGE_TYPE_SB_TKGS_GET_ALL_HIDDEN_LOCATORS, iArr);
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsTKGSGetAllHiddenLocators\n");
            return scanDvbsRetExchange;
        }
        int iA = iArr[0];
        a.a("payLoadLen:", iA, "\n", TAG);
        int i = 1;
        if (iA > 0) {
            this.TKGS_allHiddenLocators = iArr[1];
            iA = a.a(a.a("TKGS_allHiddenLocators:"), this.TKGS_allHiddenLocators, "\n", TAG, iA, -1);
            i = 2;
        }
        int i2 = this.TKGS_allHiddenLocators;
        if (i2 > 0) {
            this.TKGS_hiddenLocatorsList = new TKGSOneLocator[i2];
            int i3 = iA;
            int iA2 = i;
            for (int i4 = 0; i4 < this.TKGS_allHiddenLocators && i3 >= 5; i4++) {
                this.TKGS_hiddenLocatorsList[i4] = new TKGSOneLocator();
                System.arraycopy(iArr, iA2, iArr2, 0, 5);
                this.TKGS_hiddenLocatorsList[i4].dispatchData(iArr2);
                Log.d(TAG, "recordID=" + this.TKGS_hiddenLocatorsList[i4].recordID + "PID=" + this.TKGS_hiddenLocatorsList[i4].PID + "\n");
                StringBuilder sb = new StringBuilder();
                sb.append("tpinfo:freq=");
                sb.append(this.TKGS_hiddenLocatorsList[i4].tpInfo.i4Frequency);
                sb.append("pol=");
                sb.append(this.TKGS_hiddenLocatorsList[i4].tpInfo.ePol);
                sb.append("symbol=");
                iA2 = a.a(sb, this.TKGS_hiddenLocatorsList[i4].tpInfo.i4Symbolrate, "\n", TAG, iA2, 5);
                i3 -= 5;
            }
        }
        Log.d(TAG, "Leave dvbsTKGSGetAllHiddenLocators\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsTKGSGetAllSvcList() {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        int[] iArr = new int[254];
        int[] iArr2 = new int[32];
        Log.d(TAG, "Enter dvbsTKGSGetAllSvcList\n");
        iArr[0] = 253;
        ScanDvbsRet scanDvbsRetExchange = exchange(EXCHANGE_TYPE_SB_TKGS_GET_ALL_SVC_LIST, iArr);
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsTKGSGetAllSvcList\n");
            return scanDvbsRetExchange;
        }
        int iA = iArr[0];
        a.a("payLoadLen:", iA, "\n", TAG);
        int i = 1;
        if (iA > 0) {
            this.TKGS_SvclistNum = iArr[1];
            iA = a.a(a.a("TKGS_SvclistNum:"), this.TKGS_SvclistNum, "\n", TAG, iA, -1);
            i = 2;
        }
        if (iA > 0) {
            this.TKGS_PrefSvcListNo = iArr[i];
            iA = a.a(a.a("TKGS_PrefSvcListNo:"), this.TKGS_PrefSvcListNo, "\n", TAG, iA, -1);
            i++;
        }
        int i2 = this.TKGS_SvclistNum;
        if (i2 > 0) {
            this.TKGS_AllSvcLists = new TKGSOneSvcList[i2];
            int i3 = i;
            for (int i4 = 0; i4 < this.TKGS_SvclistNum && iA >= 33; i4++) {
                this.TKGS_AllSvcLists[i4] = new TKGSOneSvcList();
                int i5 = i3 + 1;
                this.TKGS_AllSvcLists[i4].svcListNo = iArr[i3];
                a.a(a.a("svcListNo:"), this.TKGS_AllSvcLists[i4].svcListNo, "\n", TAG);
                System.arraycopy(iArr, i5, iArr2, 0, 32);
                this.TKGS_AllSvcLists[i4].svcListName = convertAsciiArrayToString(iArr2);
                StringBuilder sbA = a.a("svcListName:");
                sbA.append(this.TKGS_AllSvcLists[i4].svcListName);
                sbA.append("\n");
                Log.d(TAG, sbA.toString());
                i3 = i5 + 32;
                iA -= 33;
            }
        }
        Log.d(TAG, "Leave dvbsTKGSGetAllSvcList\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsTKGSGetAllVisibleLocators() {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        int[] iArr = new int[254];
        int[] iArr2 = new int[5];
        Log.d(TAG, "Enter dvbsTKGSGetAllVisibleLocators\n");
        iArr[0] = 253;
        ScanDvbsRet scanDvbsRetExchange = exchange(EXCHANGE_TYPE_SB_TKGS_GET_ALL_VISIBLE_LOCATORS, iArr);
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsTKGSGetAllVisibleLocators\n");
            return scanDvbsRetExchange;
        }
        int iA = iArr[0];
        a.a("payLoadLen:", iA, "\n", TAG);
        int i = 1;
        if (iA > 0) {
            this.TKGS_allVisibleLocators = iArr[1];
            iA = a.a(a.a("TKGS_allVisibleLocators:"), this.TKGS_allVisibleLocators, "\n", TAG, iA, -1);
            i = 2;
        }
        int i2 = this.TKGS_allVisibleLocators;
        if (i2 > 0) {
            this.TKGS_visibleLocatorsList = new TKGSOneLocator[i2];
            int i3 = iA;
            int iA2 = i;
            for (int i4 = 0; i4 < this.TKGS_allVisibleLocators && i3 >= 5; i4++) {
                this.TKGS_visibleLocatorsList[i4] = new TKGSOneLocator();
                System.arraycopy(iArr, iA2, iArr2, 0, 5);
                this.TKGS_visibleLocatorsList[i4].dispatchData(iArr2);
                Log.d(TAG, "recordID=" + this.TKGS_visibleLocatorsList[i4].recordID + "PID=" + this.TKGS_visibleLocatorsList[i4].PID + "\n");
                StringBuilder sb = new StringBuilder();
                sb.append("tpinfo:freq=");
                sb.append(this.TKGS_visibleLocatorsList[i4].tpInfo.i4Frequency);
                sb.append("pol=");
                sb.append(this.TKGS_visibleLocatorsList[i4].tpInfo.ePol);
                sb.append("symbol=");
                iA2 = a.a(sb, this.TKGS_visibleLocatorsList[i4].tpInfo.i4Symbolrate, "\n", TAG, iA2, 5);
                i3 -= 5;
            }
        }
        Log.d(TAG, "Leave dvbsTKGSGetAllVisibleLocators\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsTKGSIsSameVisibleLocator(TKGSOneLocator tKGSOneLocator, TKGSOneLocator tKGSOneLocator2) {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        int[] iArr = new int[5];
        int[] iArr2 = new int[5];
        int[] iArr3 = new int[11];
        Log.d(TAG, "Enter dvbsTKGSIsSameVisibleLocator\n");
        Log.d(TAG, "One is:recordID=" + tKGSOneLocator.recordID + "PID=" + tKGSOneLocator.PID + "\n");
        Log.d(TAG, "tpinfo:freq=" + tKGSOneLocator.tpInfo.i4Frequency + "pol=" + tKGSOneLocator.tpInfo.ePol + "symbol=" + tKGSOneLocator.tpInfo.i4Symbolrate + "\n");
        StringBuilder sb = new StringBuilder();
        sb.append("Other is:recordID=");
        sb.append(tKGSOneLocator2.recordID);
        sb.append("PID=");
        sb.append(tKGSOneLocator2.PID);
        sb.append("\n");
        Log.d(TAG, sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append("tpinfo:freq=");
        sb2.append(tKGSOneLocator2.tpInfo.i4Frequency);
        sb2.append("pol=");
        sb2.append(tKGSOneLocator2.tpInfo.ePol);
        sb2.append("symbol=");
        a.a(sb2, tKGSOneLocator2.tpInfo.i4Symbolrate, "\n", TAG);
        iArr3[0] = 10;
        tKGSOneLocator.patchData(iArr);
        System.arraycopy(iArr, 0, iArr3, 1, 5);
        tKGSOneLocator2.patchData(iArr2);
        System.arraycopy(iArr2, 0, iArr3, 6, 5);
        ScanDvbsRet scanDvbsRetExchange = exchange(EXCHANGE_TYPE_SB_TKGS_IS_SAME_LOCATOR, iArr3);
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsTKGSIsSameVisibleLocator\n");
            return scanDvbsRetExchange;
        }
        int i = iArr3[0];
        a.a("payLoadLen:", i, "\n", TAG);
        if (i > 0) {
            this.TKGS_isSameLocator = Boolean.valueOf(iArr3[1] != 0);
            StringBuilder sbA = a.a("TKGS_isSameLocator:");
            sbA.append(this.TKGS_isSameLocator);
            sbA.append("\n");
            Log.d(TAG, sbA.toString());
        }
        Log.d(TAG, "Leave dvbsTKGSIsSameVisibleLocator\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsTKGSSelSvcList(int i) {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        a.a("Enter dvbsTKGSSelSvcList: prefSvcListNo:", i, "\n", TAG);
        ScanDvbsRet scanDvbsRetExchange = exchange(EXCHANGE_TYPE_SB_TKGS_SEL_PREF_SVC_LIST, new int[]{1, i});
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsTKGSSelSvcList\n");
        }
        Log.d(TAG, "Leave dvbsTKGSSelSvcList\n");
        return scanDvbsRetExchange;
    }

    public ScanDvbsRet dvbsTKGSUpdOneVisibleLocator(TKGSOneLocator tKGSOneLocator) {
        ScanDvbsRet scanDvbsRet = ScanDvbsRet.SCAN_DVBS_RET_OK;
        int[] iArr = new int[5];
        int[] iArr2 = new int[6];
        Log.d(TAG, "Enter dvbsTKGSUpdOneVisibleLocator\n");
        Log.d(TAG, "Before upd:recordID=" + tKGSOneLocator.recordID + "PID=" + tKGSOneLocator.PID + "\n");
        StringBuilder sb = new StringBuilder();
        sb.append("tpinfo:freq=");
        sb.append(tKGSOneLocator.tpInfo.i4Frequency);
        sb.append("pol=");
        sb.append(tKGSOneLocator.tpInfo.ePol);
        sb.append("symbol=");
        a.a(sb, tKGSOneLocator.tpInfo.i4Symbolrate, "\n", TAG);
        if (tKGSOneLocator.recordID == 0) {
            Log.d(TAG, "Error dvbsTKGSUpdOneVisibleLocator, invalid record id\n");
            return ScanDvbsRet.SCAN_DVBS_RET_INV_ARG;
        }
        iArr2[0] = 5;
        tKGSOneLocator.patchData(iArr);
        System.arraycopy(iArr, 0, iArr2, 1, 5);
        ScanDvbsRet scanDvbsRetExchange = exchange(EXCHANGE_TYPE_SB_TKGS_UPD_ONE_VISIBLE_LOCATOR, iArr2);
        if (ScanDvbsRet.SCAN_DVBS_RET_OK != scanDvbsRetExchange) {
            Log.d(TAG, "Error dvbsTKGSUpdOneVisibleLocator\n");
            return scanDvbsRetExchange;
        }
        Log.d(TAG, "Leave dvbsTKGSUpdOneVisibleLocator\n");
        return scanDvbsRetExchange;
    }

    public class TKGSOneLocator {
        public static final int I4C_TKGS_ONE_LOCATOR_LEN = 5;
        public int PID;
        public int recordID;
        public MtkTvScanTpInfo tpInfo;

        public TKGSOneLocator() {
            this.tpInfo = MtkTvScanDvbsBase.this.new MtkTvScanTpInfo();
            this.recordID = 0;
            this.PID = MtkTvScanDvbsBase.SB_DVBS_PID_INVALID_VALUE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void dispatchData(int[] iArr) {
            this.recordID = iArr[0];
            this.PID = iArr[1];
            int[] iArr2 = new int[3];
            System.arraycopy(iArr, 2, iArr2, 0, 3);
            this.tpInfo.dispatchData(iArr2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void patchData(int[] iArr) {
            iArr[0] = this.recordID;
            iArr[1] = this.PID;
            int[] iArr2 = new int[3];
            this.tpInfo.patchData(iArr2);
            System.arraycopy(iArr2, 0, iArr, 2, 3);
        }

        public TKGSOneLocator(int i, int i2, int i3, int i4) {
            this.tpInfo = MtkTvScanDvbsBase.this.new MtkTvScanTpInfo();
            this.PID = i;
            MtkTvScanTpInfo mtkTvScanTpInfo = this.tpInfo;
            mtkTvScanTpInfo.i4Frequency = i2;
            mtkTvScanTpInfo.i4Symbolrate = i3;
            mtkTvScanTpInfo.ePol = TunerPolarizationType.values()[i4];
        }
    }

    public ScanDvbsRet dvbsSetDisableAutoUpdateForCrnt() {
        return dvbsSetDisableAutoUpdateForCrnt(true);
    }
}
