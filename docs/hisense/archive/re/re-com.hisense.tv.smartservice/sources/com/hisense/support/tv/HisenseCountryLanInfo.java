package com.hisense.support.tv;

import androidx.core.location.GpsStatusWrapper;
import androidx.fragment.app.FragmentManagerImpl;
import com.mediatek.twoworlds.tv.MtkTvScanDvbtBase;
import com.mediatek.twoworlds.tv.common.MtkTvConfigTypeBase;
import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;
import com.mediatek.twoworlds.tv.model.MtkTvRatingConvert2Goo;
import java.util.HashMap;
import java.util.HashSet;

/* JADX INFO: loaded from: classes2.dex */
public class HisenseCountryLanInfo {
    public static final int EM_AFRICA_REGION = 2;
    public static final int EM_ASIA_PACIFIC_REGION = 3;
    public static final int EM_CIS_REGION = 0;
    public static final int EM_MIDDLE_EAST_REGION = 1;
    public static final int EM_SOUTH_AMERICA_REGION = 4;
    public static final int FAC_COUNTRY_AFG_INDEX = 156;
    public static final int FAC_COUNTRY_AGO_INDEX = 9;
    public static final int FAC_COUNTRY_ARE_INDEX = 42;
    public static final int FAC_COUNTRY_ARG_INDEX = 55;
    public static final int FAC_COUNTRY_ARM_INDEX = 37;
    public static final int FAC_COUNTRY_AUS_INDEX = 51;
    public static final int FAC_COUNTRY_AZE_INDEX = 35;
    public static final int FAC_COUNTRY_BDI_INDEX = 95;
    public static final int FAC_COUNTRY_BEN_INDEX = 10;
    public static final int FAC_COUNTRY_BFA_INDEX = 134;
    public static final int FAC_COUNTRY_BGD_INDEX = 99;
    public static final int FAC_COUNTRY_BHR_INDEX = 104;
    public static final int FAC_COUNTRY_BIH_INDEX = 132;
    public static final int FAC_COUNTRY_BLR_INDEX = 92;
    public static final int FAC_COUNTRY_BOL_INDEX = 109;
    public static final int FAC_COUNTRY_BRA_INDEX = 53;
    public static final int FAC_COUNTRY_BRB_INDEX = 123;
    public static final int FAC_COUNTRY_BRN_INDEX = 169;
    public static final int FAC_COUNTRY_BWA_INDEX = 133;
    public static final int FAC_COUNTRY_CAN_INDEX = 60;
    public static final int FAC_COUNTRY_CHL_INDEX = 86;
    public static final int FAC_COUNTRY_CIV_INDEX = 8;
    public static final int FAC_COUNTRY_CMR_INDEX = 13;
    public static final int FAC_COUNTRY_COD_INDEX = 12;
    public static final int FAC_COUNTRY_COG_INDEX = 79;
    public static final int FAC_COUNTRY_COL_INDEX = 83;
    public static final int FAC_COUNTRY_CUW_INDEX = 122;
    public static final int FAC_COUNTRY_DJI_INDEX = 19;
    public static final int FAC_COUNTRY_DOM_INDEX = 118;
    public static final int FAC_COUNTRY_DXB_INDEX = 50;
    public static final int FAC_COUNTRY_DZA_INDEX = 23;
    public static final int FAC_COUNTRY_ECU_INDEX = 82;
    public static final int FAC_COUNTRY_EGY_INDEX = 2;
    public static final int FAC_COUNTRY_ERI_INDEX = 135;
    public static final int FAC_COUNTRY_ETH_INDEX = 20;
    public static final int FAC_COUNTRY_FJI_INDEX = 89;
    public static final int FAC_COUNTRY_GAB_INDEX = 136;
    public static final int FAC_COUNTRY_GEO_INDEX = 36;
    public static final int FAC_COUNTRY_GHA_INDEX = 6;
    public static final int FAC_COUNTRY_GIN_INDEX = 94;
    public static final int FAC_COUNTRY_GMB_INDEX = 187;
    public static final int FAC_COUNTRY_GTM_INDEX = 117;
    public static final int FAC_COUNTRY_HK_INDEX = 154;
    public static final int FAC_COUNTRY_HND_INDEX = 115;
    public static final int FAC_COUNTRY_IDN_INDEX = 39;
    public static final int FAC_COUNTRY_IND_INDEX = 38;
    public static final int FAC_COUNTRY_IRN_INDEX = 48;
    public static final int FAC_COUNTRY_IRQ_INDEX = 40;
    public static final int FAC_COUNTRY_ISR_INDEX = 49;
    public static final int FAC_COUNTRY_JAM_INDEX = 121;
    public static final int FAC_COUNTRY_JOR_INDEX = 47;
    public static final int FAC_COUNTRY_JPN_INDEX = 202;
    public static final int FAC_COUNTRY_KAZ_INDEX = 85;
    public static final int FAC_COUNTRY_KEN_INDEX = 80;
    public static final int FAC_COUNTRY_KGZ_INDEX = 31;
    public static final int FAC_COUNTRY_KHM_INDEX = 103;
    public static final int FAC_COUNTRY_KOR_INDEX = 206;
    public static final int FAC_COUNTRY_KWT_INDEX = 43;
    public static final int FAC_COUNTRY_LAO_INDEX = 207;
    public static final int FAC_COUNTRY_LBN_INDEX = 105;
    public static final int FAC_COUNTRY_LBR_INDEX = 11;
    public static final int FAC_COUNTRY_LBY_INDEX = 5;
    public static final int FAC_COUNTRY_LKA_INDEX = 81;
    public static final int FAC_COUNTRY_MAF_INDEX = 124;
    public static final int FAC_COUNTRY_MAR_INDEX = 3;
    public static final int FAC_COUNTRY_MAY_INDEX = 151;
    public static final int FAC_COUNTRY_MDA_INDEX = 93;
    public static final int FAC_COUNTRY_MDG_INDEX = 22;
    public static final int FAC_COUNTRY_MDV_INDEX = 88;
    public static final int FAC_COUNTRY_MEX_INDEX = 61;
    public static final int FAC_COUNTRY_MLI_INDEX = 129;
    public static final int FAC_COUNTRY_MMR_INDEX = 27;
    public static final int FAC_COUNTRY_MNE_INDEX = 147;
    public static final int FAC_COUNTRY_MNG_INDEX = 101;
    public static final int FAC_COUNTRY_MOZ_INDEX = 21;
    public static final int FAC_COUNTRY_MRT_INDEX = 139;
    public static final int FAC_COUNTRY_MUS_INDEX = 17;
    public static final int FAC_COUNTRY_MWI_INDEX = 138;
    public static final int FAC_COUNTRY_MYS_INDEX = 28;
    public static final int FAC_COUNTRY_NAM_INDEX = 140;
    public static final int FAC_COUNTRY_NER_INDEX = 141;
    public static final int FAC_COUNTRY_NGA_INDEX = 4;
    public static final int FAC_COUNTRY_NIC_INDEX = 114;
    public static final int FAC_COUNTRY_NPL_INDEX = 102;
    public static final int FAC_COUNTRY_NZL_INDEX = 152;
    public static final int FAC_COUNTRY_OMN_INDEX = 45;
    public static final int FAC_COUNTRY_PAK_INDEX = 100;
    public static final int FAC_COUNTRY_PAN_INDEX = 57;
    public static final int FAC_COUNTRY_PER_INDEX = 84;
    public static final int FAC_COUNTRY_PHL_INDEX = 56;
    public static final int FAC_COUNTRY_PRI_INDEX = 125;
    public static final int FAC_COUNTRY_PRY_INDEX = 108;
    public static final int FAC_COUNTRY_PSE_INDEX = 107;
    public static final int FAC_COUNTRY_QAT_INDEX = 46;
    public static final int FAC_COUNTRY_REU_INDEX = 150;
    public static final int FAC_COUNTRY_RUS_INDEX = 29;
    public static final int FAC_COUNTRY_RWA_INDEX = 137;
    public static final int FAC_COUNTRY_SAU_INDEX = 41;
    public static final int FAC_COUNTRY_SDN_INDEX = 18;
    public static final int FAC_COUNTRY_SEN_INDEX = 96;
    public static final int FAC_COUNTRY_SGP_INDEX = 126;
    public static final int FAC_COUNTRY_SLE_INDEX = 7;
    public static final int FAC_COUNTRY_SLV_INDEX = 116;
    public static final int FAC_COUNTRY_SOM_INDEX = 98;
    public static final int FAC_COUNTRY_SRB_INDEX = 91;
    public static final int FAC_COUNTRY_SSD_INDEX = 130;
    public static final int FAC_COUNTRY_SYC_INDEX = 142;
    public static final int FAC_COUNTRY_SYR_INDEX = 106;
    public static final int FAC_COUNTRY_TCD_INDEX = 144;
    public static final int FAC_COUNTRY_THA_INDEX = 25;
    public static final int FAC_COUNTRY_TJK_INDEX = 90;
    public static final int FAC_COUNTRY_TKM_INDEX = 32;
    public static final int FAC_COUNTRY_TTO_INDEX = 120;
    public static final int FAC_COUNTRY_TUN_INDEX = 97;
    public static final int FAC_COUNTRY_TUR_INDEX = 33;
    public static final int FAC_COUNTRY_TW_INDEX = 52;
    public static final int FAC_COUNTRY_TZA_INDEX = 16;
    public static final int FAC_COUNTRY_UGA_INDEX = 15;
    public static final int FAC_COUNTRY_UKR_INDEX = 34;
    public static final int FAC_COUNTRY_URY_INDEX = 58;
    public static final int FAC_COUNTRY_USA_INDEX = 59;
    public static final int FAC_COUNTRY_UZB_INDEX = 30;
    public static final int FAC_COUNTRY_VEN_INDEX = 54;
    public static final int FAC_COUNTRY_VNM_INDEX = 26;
    public static final int FAC_COUNTRY_YEM_INDEX = 44;
    public static final int FAC_COUNTRY_ZAF_INDEX = 24;
    public static final int FAC_COUNTRY_ZMB_INDEX = 143;
    public static final int FAC_COUNTRY_ZWE_INDEX = 14;
    public static final int FAC_COUNTRY_ZZZ_INDEX = 1;
    public static final int FAC_LANGUAGE_ALB_INDEX = 34;
    public static final int FAC_LANGUAGE_AMH_INDEX = 46;
    public static final int FAC_LANGUAGE_ARA_INDEX = 5;
    public static final int FAC_LANGUAGE_ARM_INDEX = 43;
    public static final int FAC_LANGUAGE_AZE_INDEX = 49;
    public static final int FAC_LANGUAGE_BNG_INDEX = 55;
    public static final int FAC_LANGUAGE_BSN_INDEX = 50;
    public static final int FAC_LANGUAGE_BUL_INDEX = 27;
    public static final int FAC_LANGUAGE_BUR_INDEX = 10;
    public static final int FAC_LANGUAGE_BYL_INDEX = 52;
    public static final int FAC_LANGUAGE_CHI_INDEX = 7;
    public static final int FAC_LANGUAGE_CZE_INDEX = 23;
    public static final int FAC_LANGUAGE_DAN_INDEX = 20;
    public static final int FAC_LANGUAGE_DUT_INDEX = 40;
    public static final int FAC_LANGUAGE_ENG_INDEX = 1;
    public static final int FAC_LANGUAGE_EST_INDEX = 36;
    public static final int FAC_LANGUAGE_FIN_INDEX = 21;
    public static final int FAC_LANGUAGE_FRE_INDEX = 2;
    public static final int FAC_LANGUAGE_GEO_INDEX = 44;
    public static final int FAC_LANGUAGE_GER_INDEX = 17;
    public static final int FAC_LANGUAGE_GRE_INDEX = 42;
    public static final int FAC_LANGUAGE_GUJ_INDEX = 96;
    public static final int FAC_LANGUAGE_HBR_INDEX = 15;
    public static final int FAC_LANGUAGE_HID_INDEX = 53;
    public static final int FAC_LANGUAGE_HIN_INDEX = 12;
    public static final int FAC_LANGUAGE_HRV_INDEX = 31;
    public static final int FAC_LANGUAGE_HUN_INDEX = 26;
    public static final int FAC_LANGUAGE_ICL_INDEX = 51;
    public static final int FAC_LANGUAGE_IND_INDEX = 30;
    public static final int FAC_LANGUAGE_ITA_INDEX = 18;
    public static final int FAC_LANGUAGE_JPN_INDEX = 68;
    public static final int FAC_LANGUAGE_KAN_INDEX = 93;
    public static final int FAC_LANGUAGE_KAZ_INDEX = 48;
    public static final int FAC_LANGUAGE_KHM_INDEX = 69;
    public static final int FAC_LANGUAGE_KIR_INDEX = 47;
    public static final int FAC_LANGUAGE_KOR_INDEX = 71;
    public static final int FAC_LANGUAGE_KUR_INDEX = 38;
    public static final int FAC_LANGUAGE_LAV_INDEX = 35;
    public static final int FAC_LANGUAGE_LIT_INDEX = 37;
    public static final int FAC_LANGUAGE_MAC_INDEX = 33;
    public static final int FAC_LANGUAGE_MAL_INDEX = 14;
    public static final int FAC_LANGUAGE_MAR_INDEX = 94;
    public static final int FAC_LANGUAGE_MLD_INDEX = 54;
    public static final int FAC_LANGUAGE_MOG_INDEX = 56;
    public static final int FAC_LANGUAGE_NOR_INDEX = 22;
    public static final int FAC_LANGUAGE_PER_INDEX = 29;
    public static final int FAC_LANGUAGE_POL_INDEX = 25;
    public static final int FAC_LANGUAGE_POR_INDEX = 4;
    public static final int FAC_LANGUAGE_RUM_INDEX = 41;
    public static final int FAC_LANGUAGE_RUS_INDEX = 6;
    public static final int FAC_LANGUAGE_SIN_INDEX = 81;
    public static final int FAC_LANGUAGE_SLK_INDEX = 24;
    public static final int FAC_LANGUAGE_SLV_INDEX = 39;
    public static final int FAC_LANGUAGE_SPA_INDEX = 3;
    public static final int FAC_LANGUAGE_SRP_INDEX = 32;
    public static final int FAC_LANGUAGE_SWE_INDEX = 19;
    public static final int FAC_LANGUAGE_TAM_INDEX = 90;
    public static final int FAC_LANGUAGE_TEL_INDEX = 91;
    public static final int FAC_LANGUAGE_THA_INDEX = 9;
    public static final int FAC_LANGUAGE_TUR_INDEX = 28;
    public static final int FAC_LANGUAGE_UKR_INDEX = 13;
    public static final int FAC_LANGUAGE_URD_INDEX = 45;
    public static final int FAC_LANGUAGE_UZB_INDEX = 11;
    public static final int FAC_LANGUAGE_VIE_INDEX = 8;
    public static final int FAC_LANGUAGE_ZHO_INDEX = 16;
    public static final String LOCALE_AMH_CODE = "am";
    public static final String LOCALE_ARA_CODE = "ar";
    public static final String LOCALE_ARM_CODE = "hy";
    public static final String LOCALE_AZE_CODE = "az";
    public static final String LOCALE_BEL_CODE = "be";
    public static final String LOCALE_BNG_CODE = "bn";
    public static final String LOCALE_BOS_CODE = "bs";
    public static final String LOCALE_BUG_CODE = "bg";
    public static final String LOCALE_BUR_CODE = "my";
    public static final String LOCALE_CHI_CODE = "zh";
    public static final String LOCALE_CZE_CODE = "cs";
    public static final String LOCALE_DAN_CODE = "da";
    public static final String LOCALE_DEU_CODE = "de";
    public static final String LOCALE_ENG_CODE = "en";
    public static final String LOCALE_EST_CODE = "et";
    public static final String LOCALE_FIN_CODE = "fi";
    public static final String LOCALE_FRE_CODE = "fr";
    public static final String LOCALE_GEO_CODE = "ka";
    public static final String LOCALE_GRE_CODE = "el";
    public static final String LOCALE_GUJ_CODE = "gu";
    public static final String LOCALE_HBR_CODE = "iw";
    public static final String LOCALE_HIN_CODE = "hi";
    public static final String LOCALE_HIN_COUNTRY = "IN";
    public static final String LOCALE_HRV_CODE = "hr";
    public static final String LOCALE_HUN_CODE = "hu";
    public static final String LOCALE_IND_CODE = "in";
    public static final String LOCALE_ISL_CODE = "is";
    public static final String LOCALE_ITA_CODE = "it";
    public static final String LOCALE_JPN_CODE = "ja";
    public static final String LOCALE_KAN_CODE = "kn";
    public static final String LOCALE_KAZ_CODE = "kk";
    public static final String LOCALE_KHM_CODE = "km";
    public static final String LOCALE_KIR_CODE = "ky";
    public static final String LOCALE_KOR_CODE = "ko";
    public static final String LOCALE_KUR_CODE = "ku";
    public static final String LOCALE_LTU_CODE = "lt";
    public static final String LOCALE_LTV_CODE = "lv";
    public static final String LOCALE_MAK_CODE = "mk";
    public static final String LOCALE_MAL_CODE = "ms";
    public static final String LOCALE_MAR_CODE = "mr";
    public static final String LOCALE_MOG_CODE = "mn";
    public static final String LOCALE_NLD_CODE = "nl";
    public static final String LOCALE_NOB_CODE = "nb";
    public static final String LOCALE_PER_CODE = "fa";
    public static final String LOCALE_POL_CODE = "pl";
    public static final String LOCALE_POR_CODE = "pt";
    public static final String LOCALE_RUM_CODE = "ro";
    public static final String LOCALE_RUS_CODE = "ru";
    public static final String LOCALE_SHQ_CODE = "sq";
    public static final String LOCALE_SIN_CODE = "si";
    public static final String LOCALE_SLK_CODE = "sk";
    public static final String LOCALE_SLV_CODE = "sl";
    public static final String LOCALE_SPA_CODE = "es";
    public static final String LOCALE_SRP_CODE = "sr";
    public static final String LOCALE_SRP_COUNTRY = "SP";
    public static final String LOCALE_SWE_CODE = "sv";
    public static final String LOCALE_TAM_CODE = "ta";
    public static final String LOCALE_TEL_CODE = "te";
    public static final String LOCALE_THA_CODE = "th";
    public static final String LOCALE_TUR_CODE = "tr";
    public static final String LOCALE_UKR_CODE = "uk";
    public static final String LOCALE_URD_CODE = "ur";
    public static final String LOCALE_URD_COUNTRY = "IN";
    public static final String LOCALE_UZB_CODE = "uz";
    public static final String LOCALE_VIE_CODE = "vi";
    public static final String LOCALE_ZHO_CODE = "zh";
    public static final String LOGO_HISENSE_INDEX = "his";
    public static final String LOGO_TOSHIBA_INDEX = "tos";
    public static final int SA_A_REGION = 5;
    public static final int SA_B_REGION = 6;
    public static HashMap<Integer, String> singleTimezoneList = new HashMap<>();
    public static HashSet<Integer> multipleTimezoneSet = new HashSet<>();
    public static String COUNTRY_AD = "AD";
    public static String COUNTRY_AE = "AE";
    public static String COUNTRY_AF = "AF";
    public static String COUNTRY_AG = "AG";
    public static String COUNTRY_AI = "AI";
    public static final String LOCALE_SHQ_COUNTRY = "AL";
    public static String COUNTRY_AL = LOCALE_SHQ_COUNTRY;
    public static final String LOCALE_ARM_COUNTRY = "AM";
    public static String COUNTRY_AM = LOCALE_ARM_COUNTRY;
    public static String COUNTRY_AO = MtkTvRatingConvert2Goo.RATING_STR_AO;
    public static String COUNTRY_AR = "AR";
    public static String COUNTRY_AS = "AS";
    public static String COUNTRY_AT = "AT";
    public static String COUNTRY_AU = "AU";
    public static String COUNTRY_AW = "AW";
    public static String COUNTRY_AX = "AX";
    public static final String LOCALE_AZE_COUNTRY = "AZ";
    public static String COUNTRY_AZ = LOCALE_AZE_COUNTRY;
    public static String COUNTRY_BA = "BA";
    public static String COUNTRY_BB = "BB";
    public static final String LOCALE_BNG_COUNTRY = "BD";
    public static String COUNTRY_BD = LOCALE_BNG_COUNTRY;
    public static String COUNTRY_BE = "BE";
    public static String COUNTRY_BF = "BF";
    public static final String LOCALE_BUG_COUNTRY = "BG";
    public static String COUNTRY_BG = LOCALE_BUG_COUNTRY;
    public static String COUNTRY_BH = "BH";
    public static final String LOCALE_BOS_COUNTRY = "BI";
    public static String COUNTRY_BI = LOCALE_BOS_COUNTRY;
    public static String COUNTRY_BJ = "BJ";
    public static String COUNTRY_BL = "BL";
    public static String COUNTRY_BM = "BM";
    public static String COUNTRY_BN = "BN";
    public static String COUNTRY_BO = "BO";
    public static String COUNTRY_BQ = "BQ";
    public static String COUNTRY_BR = "BR";
    public static String COUNTRY_BS = "BS";
    public static String COUNTRY_BT = "BT";
    public static String COUNTRY_BW = "BW";
    public static final String LOCALE_BEL_COUNTRY = "BY";
    public static String COUNTRY_BY = LOCALE_BEL_COUNTRY;
    public static String COUNTRY_BZ = "BZ";
    public static final String LOCALE_CAN_COUNTRY = "CA";
    public static String COUNTRY_CA = LOCALE_CAN_COUNTRY;
    public static String COUNTRY_CC = "CC";
    public static String COUNTRY_CD = "CD";
    public static String COUNTRY_CF = "CF";
    public static String COUNTRY_CG = "CG";
    public static String COUNTRY_CH = "CH";
    public static String COUNTRY_CI = "CI";
    public static String COUNTRY_CK = "CK";
    public static String COUNTRY_CL = "CL";
    public static String COUNTRY_CM = "CM";
    public static final String LOCALE_CHI_COUNTRY = "CN";
    public static String COUNTRY_CN = LOCALE_CHI_COUNTRY;
    public static final String LOCALE_COL_COUNTRY = "CO";
    public static String COUNTRY_CO = LOCALE_COL_COUNTRY;
    public static String COUNTRY_CR = "CR";
    public static String COUNTRY_CU = "CU";
    public static String COUNTRY_CV = "CV";
    public static String COUNTRY_CW = "CW";
    public static String COUNTRY_CX = "CX";
    public static String COUNTRY_CY = "CY";
    public static final String LOCALE_CZE_COUNTRY = "CZ";
    public static String COUNTRY_CZ = LOCALE_CZE_COUNTRY;
    public static final String LOCALE_DEU_COUNTRY = "DE";
    public static String COUNTRY_DE = LOCALE_DEU_COUNTRY;
    public static String COUNTRY_DG = "DG";
    public static String COUNTRY_DJ = "DJ";
    public static final String LOCALE_DAN_COUNTRY = "DK";
    public static String COUNTRY_DK = LOCALE_DAN_COUNTRY;
    public static String COUNTRY_DM = "DM";
    public static String COUNTRY_DO = "DO";
    public static String COUNTRY_DZ = "DZ";
    public static String COUNTRY_EA = "EA";
    public static String COUNTRY_EC = "EC";
    public static final String LOCALE_EST_COUNTRY = "EE";
    public static String COUNTRY_EE = LOCALE_EST_COUNTRY;
    public static String COUNTRY_EG = "EG";
    public static String COUNTRY_EH = "EH";
    public static String COUNTRY_ER = "ER";
    public static final String LOCALE_SPA_COUNTRY = "ES";
    public static String COUNTRY_ES = LOCALE_SPA_COUNTRY;
    public static final String LOCALE_AMH_COUNTRY = "ET";
    public static String COUNTRY_ET = LOCALE_AMH_COUNTRY;
    public static final String LOCALE_FIN_COUNTRY = "FI";
    public static String COUNTRY_FI = LOCALE_FIN_COUNTRY;
    public static String COUNTRY_FJ = "FJ";
    public static String COUNTRY_FK = "FK";
    public static String COUNTRY_FM = "FM";
    public static String COUNTRY_FO = "FO";
    public static final String LOCALE_FRE_COUNTRY = "FR";
    public static String COUNTRY_FR = LOCALE_FRE_COUNTRY;
    public static String COUNTRY_GA = "GA";
    public static final String LOCALE_GB_COUNTRY = "GB";
    public static String COUNTRY_GB = LOCALE_GB_COUNTRY;
    public static String COUNTRY_GD = "GD";
    public static final String LOCALE_GEO_COUNTRY = "GE";
    public static String COUNTRY_GE = LOCALE_GEO_COUNTRY;
    public static String COUNTRY_GF = "GF";
    public static String COUNTRY_GG = "GG";
    public static String COUNTRY_GH = "GH";
    public static String COUNTRY_GI = "GI";
    public static String COUNTRY_GL = "GL";
    public static String COUNTRY_GM = "GM";
    public static String COUNTRY_GN = "GN";
    public static String COUNTRY_GP = "GP";
    public static String COUNTRY_GQ = "GQ";
    public static final String LOCALE_GRE_COUNTRY = "GR";
    public static String COUNTRY_GR = LOCALE_GRE_COUNTRY;
    public static String COUNTRY_GT = "GT";
    public static String COUNTRY_GU = "GU";
    public static String COUNTRY_GW = "GW";
    public static String COUNTRY_GY = "GY";
    public static final String LOCALE_ZHO_COUNTRY_HK = "HK";
    public static String COUNTRY_HK = LOCALE_ZHO_COUNTRY_HK;
    public static String COUNTRY_HN = "HN";
    public static final String LOCALE_HRV_COUNTRY = "HR";
    public static String COUNTRY_HR = LOCALE_HRV_COUNTRY;
    public static String COUNTRY_HT = "HT";
    public static final String LOCALE_HUN_COUNTRY = "HU";
    public static String COUNTRY_HU = LOCALE_HUN_COUNTRY;
    public static String COUNTRY_IC = "IC";
    public static final String LOCALE_IND_COUNTRY = "ID";
    public static String COUNTRY_ID = LOCALE_IND_COUNTRY;
    public static String COUNTRY_IE = "IE";
    public static final String LOCALE_HBR_COUNTRY = "IL";
    public static String COUNTRY_IL = LOCALE_HBR_COUNTRY;
    public static String COUNTRY_IM = "IM";
    public static String COUNTRY_IN = "IN";
    public static String COUNTRY_IO = "IO";
    public static final String LOCALE_KUR_COUNTRY = "IQ";
    public static String COUNTRY_IQ = LOCALE_KUR_COUNTRY;
    public static final String LOCALE_PER_COUNTRY = "IR";
    public static String COUNTRY_IR = LOCALE_PER_COUNTRY;
    public static final String LOCALE_ISL_COUNTRY = "IS";
    public static String COUNTRY_IS = LOCALE_ISL_COUNTRY;
    public static final String LOCALE_ITA_COUNTRY = "IT";
    public static String COUNTRY_IT = LOCALE_ITA_COUNTRY;
    public static String COUNTRY_JE = "JE";
    public static String COUNTRY_JM = "JM";
    public static String COUNTRY_JO = "JO";
    public static final String LOCALE_JPN_COUNTRY = "JP";
    public static String COUNTRY_JP = LOCALE_JPN_COUNTRY;
    public static String COUNTRY_KE = "KE";
    public static final String LOCALE_KIR_COUNTRY = "KG";
    public static String COUNTRY_KG = LOCALE_KIR_COUNTRY;
    public static final String LOCALE_KHM_COUNTRY = "KH";
    public static String COUNTRY_KH = LOCALE_KHM_COUNTRY;
    public static String COUNTRY_KI = "KI";
    public static String COUNTRY_KM = "KM";
    public static String COUNTRY_KN = "KN";
    public static String COUNTRY_KP = "KP";
    public static final String LOCALE_KOR_COUNTRY = "KR";
    public static String COUNTRY_KR = LOCALE_KOR_COUNTRY;
    public static String COUNTRY_KW = "KW";
    public static String COUNTRY_KY = "KY";
    public static final String LOCALE_KAZ_COUNTRY = "KZ";
    public static String COUNTRY_KZ = LOCALE_KAZ_COUNTRY;
    public static String COUNTRY_LA = "LA";
    public static String COUNTRY_LB = "LB";
    public static String COUNTRY_LC = "LC";
    public static String COUNTRY_LI = "LI";
    public static final String LOCALE_SIN_COUNTRY = "LK";
    public static String COUNTRY_LK = LOCALE_SIN_COUNTRY;
    public static String COUNTRY_LR = "LR";
    public static String COUNTRY_LS = "LS";
    public static final String LOCALE_LTU_COUNTRY = "LT";
    public static String COUNTRY_LT = LOCALE_LTU_COUNTRY;
    public static String COUNTRY_LU = "LU";
    public static final String LOCALE_LTV_COUNTRY = "LV";
    public static String COUNTRY_LV = LOCALE_LTV_COUNTRY;
    public static String COUNTRY_LY = "LY";
    public static String COUNTRY_MA = MtkTvRatingConvert2Goo.RATING_STR_MA;
    public static String COUNTRY_MC = "MC";
    public static String COUNTRY_MD = "MD";
    public static String COUNTRY_ME = "ME";
    public static String COUNTRY_MF = "MF";
    public static String COUNTRY_MG = "MG";
    public static String COUNTRY_MH = "MH";
    public static final String LOCALE_MAK_COUNTRY = "MK";
    public static String COUNTRY_MK = LOCALE_MAK_COUNTRY;
    public static String COUNTRY_ML = "ML";
    public static final String LOCALE_BUR_COUNTRY = "MM";
    public static String COUNTRY_MM = LOCALE_BUR_COUNTRY;
    public static final String LOCALE_MOG_COUNTRY = "MN";
    public static String COUNTRY_MN = LOCALE_MOG_COUNTRY;
    public static String COUNTRY_MO = "MO";
    public static String COUNTRY_MP = "MP";
    public static String COUNTRY_MQ = "MQ";
    public static String COUNTRY_MR = "MR";
    public static String COUNTRY_MS = "MS";
    public static String COUNTRY_MT = "MT";
    public static String COUNTRY_MU = "MU";
    public static String COUNTRY_MW = "MW";
    public static String COUNTRY_MX = "MX";
    public static final String LOCALE_MAL_COUNTRY = "MY";
    public static String COUNTRY_MY = LOCALE_MAL_COUNTRY;
    public static String COUNTRY_MZ = "MZ";
    public static String COUNTRY_NA = MtkTvRatingConvert2Goo.STR_MPAA_NA;
    public static String COUNTRY_NC = "NC";
    public static String COUNTRY_NE = "NE";
    public static String COUNTRY_NF = "NF";
    public static String COUNTRY_NG = "NG";
    public static String COUNTRY_NI = "NI";
    public static final String LOCALE_NDL_COUNTRY = "NL";
    public static String COUNTRY_NL = LOCALE_NDL_COUNTRY;
    public static final String LOCALE_NOB_COUNTRY = "NO";
    public static String COUNTRY_NO = LOCALE_NOB_COUNTRY;
    public static String COUNTRY_NP = "NP";
    public static String COUNTRY_NR = MtkTvRatingConvert2Goo.STR_MPAA_NR;
    public static String COUNTRY_NU = "NU";
    public static String COUNTRY_NZ = "NZ";
    public static String COUNTRY_OM = "OM";
    public static String COUNTRY_PA = "PA";
    public static String COUNTRY_PE = "PE";
    public static String COUNTRY_PF = "PF";
    public static String COUNTRY_PG = "PG";
    public static String COUNTRY_PH = "PH";
    public static String COUNTRY_PK = "PK";
    public static final String LOCALE_POL_COUNTRY = "PL";
    public static String COUNTRY_PL = LOCALE_POL_COUNTRY;
    public static String COUNTRY_PM = "PM";
    public static String COUNTRY_PN = "PN";
    public static String COUNTRY_PR = "PR";
    public static String COUNTRY_PS = "PS";
    public static final String LOCALE_POR_COUNTRY = "PT";
    public static String COUNTRY_PT = LOCALE_POR_COUNTRY;
    public static String COUNTRY_PW = "PW";
    public static String COUNTRY_PY = "PY";
    public static String COUNTRY_QA = "QA";
    public static String COUNTRY_RE = "RE";
    public static final String LOCALE_RUM_COUNTRY = "RO";
    public static String COUNTRY_RO = LOCALE_RUM_COUNTRY;
    public static String COUNTRY_RS = "RS";
    public static final String LOCALE_RUS_COUNTRY = "RU";
    public static String COUNTRY_RU = LOCALE_RUS_COUNTRY;
    public static String COUNTRY_RW = "RW";
    public static final String LOCALE_ARA_COUNTRY = "SA";
    public static String COUNTRY_SA = LOCALE_ARA_COUNTRY;
    public static String COUNTRY_SB = "SB";
    public static String COUNTRY_SC = "SC";
    public static String COUNTRY_SD = "SD";
    public static final String LOCALE_SWE_COUNTRY = "SE";
    public static String COUNTRY_SE = LOCALE_SWE_COUNTRY;
    public static String COUNTRY_SG = "SG";
    public static String COUNTRY_SH = "SH";
    public static final String LOCALE_SLV_COUNTRY = "SI";
    public static String COUNTRY_SI = LOCALE_SLV_COUNTRY;
    public static String COUNTRY_SJ = "SJ";
    public static final String LOCALE_SLK_COUNTRY = "SK";
    public static String COUNTRY_SK = LOCALE_SLK_COUNTRY;
    public static String COUNTRY_SL = "SL";
    public static String COUNTRY_SM = "SM";
    public static String COUNTRY_SN = "SN";
    public static String COUNTRY_SO = "SO";
    public static String COUNTRY_SR = "SR";
    public static String COUNTRY_SS = "SS";
    public static String COUNTRY_ST = "ST";
    public static String COUNTRY_SV = "SV";
    public static String COUNTRY_SX = "SX";
    public static String COUNTRY_SY = "SY";
    public static String COUNTRY_SZ = "SZ";
    public static String COUNTRY_TC = "TC";
    public static String COUNTRY_TD = "TD";
    public static String COUNTRY_TG = "TG";
    public static final String LOCALE_THA_COUNTRY = "TH";
    public static String COUNTRY_TH = LOCALE_THA_COUNTRY;
    public static String COUNTRY_TJ = "TJ";
    public static String COUNTRY_TK = "TK";
    public static String COUNTRY_TL = "TL";
    public static String COUNTRY_TM = "TM";
    public static String COUNTRY_TN = "TN";
    public static String COUNTRY_TO = "TO";
    public static final String LOCALE_TUR_COUNTRY = "TR";
    public static String COUNTRY_TR = LOCALE_TUR_COUNTRY;
    public static String COUNTRY_TT = "TT";
    public static String COUNTRY_TV = "TV";
    public static final String LOCALE_ZHO_COUNTRY = "TW";
    public static String COUNTRY_TW = LOCALE_ZHO_COUNTRY;
    public static String COUNTRY_TZ = "TZ";
    public static final String LOCALE_UKR_COUNTRY = "UA";
    public static String COUNTRY_UA = LOCALE_UKR_COUNTRY;
    public static String COUNTRY_UG = "UG";
    public static String COUNTRY_UM = "UM";
    public static final String LOCALE_ENG_COUNTRY = "US";
    public static String COUNTRY_US = LOCALE_ENG_COUNTRY;
    public static String COUNTRY_UY = "UY";
    public static final String LOCALE_UZB_COUNTRY = "UZ";
    public static String COUNTRY_UZ = LOCALE_UZB_COUNTRY;
    public static String COUNTRY_VA = "VA";
    public static String COUNTRY_VC = "VC";
    public static String COUNTRY_VE = "VE";
    public static String COUNTRY_VG = "VG";
    public static String COUNTRY_VI = "VI";
    public static final String LOCALE_VIE_COUNTRY = "VN";
    public static String COUNTRY_VN = LOCALE_VIE_COUNTRY;
    public static String COUNTRY_VU = "VU";
    public static String COUNTRY_WF = "WF";
    public static String COUNTRY_WS = "WS";
    public static String COUNTRY_XK = "XK";
    public static String COUNTRY_YE = "YE";
    public static String COUNTRY_YT = "YT";
    public static String COUNTRY_ZA = "ZA";
    public static String COUNTRY_ZM = "ZM";
    public static String COUNTRY_ZW = "ZW";
    public static String COUNTRY_ZZ = "ZZ";

    public enum LanguageCode {
        ENG(HisenseCountryLanInfo.LOCALE_ENG_CODE, MtkTvConfigTypeBase.S639_CFG_LANG_ENG, 1),
        FRE(HisenseCountryLanInfo.LOCALE_FRE_CODE, MtkTvConfigTypeBase.S639_CFG_LANG_FRE, 2),
        SPA(HisenseCountryLanInfo.LOCALE_SPA_CODE, MtkTvConfigTypeBase.S639_CFG_LANG_SPA, 3),
        POR(HisenseCountryLanInfo.LOCALE_POR_CODE, MtkTvConfigTypeBase.S639_CFG_LANG_POR, 4),
        ARA(HisenseCountryLanInfo.LOCALE_ARA_CODE, MtkTvConfigTypeBase.S639_CFG_LANG_ARA, 5),
        RUS(HisenseCountryLanInfo.LOCALE_RUS_CODE, MtkTvConfigTypeBase.S639_CFG_LANG_RUS, 6),
        CHI("zh", MtkTvConfigTypeBase.S639_CFG_LANG_CHI, 7),
        VIE(HisenseCountryLanInfo.LOCALE_VIE_CODE, MtkTvConfigTypeBase.S639_CFG_LANG_VIE, 8),
        THA(HisenseCountryLanInfo.LOCALE_THA_CODE, MtkTvConfigTypeBase.S639_CFG_LANG_THA, 9),
        BUR(HisenseCountryLanInfo.LOCALE_BUR_CODE, MtkTvConfigTypeBase.S639_CFG_LANG_MMR, 10),
        UZB(HisenseCountryLanInfo.LOCALE_UZB_CODE, MtkTvConfigTypeBase.S639_CFG_LANG_UZB, 11),
        HIN(HisenseCountryLanInfo.LOCALE_HIN_CODE, MtkTvConfigTypeBase.S639_CFG_LANG_HIN, 12),
        UKR(HisenseCountryLanInfo.LOCALE_UKR_CODE, MtkTvConfigTypeBase.S639_CFG_LANG_UKR, 13),
        MAL(HisenseCountryLanInfo.LOCALE_MAL_CODE, "msa", 14),
        HBR(HisenseCountryLanInfo.LOCALE_HBR_CODE, MtkTvConfigTypeBase.S639_CFG_LANG_HBR, 15),
        ZHO("zh", MtkTvConfigTypeBase.S639_CFG_LANG_ZHO, 16),
        GER(HisenseCountryLanInfo.LOCALE_DEU_CODE, MtkTvConfigTypeBase.S639_CFG_LANG_GER, 17),
        ITA(HisenseCountryLanInfo.LOCALE_ITA_CODE, MtkTvConfigTypeBase.S639_CFG_LANG_ITA, 18),
        SWE(HisenseCountryLanInfo.LOCALE_SWE_CODE, MtkTvConfigTypeBase.S639_CFG_LANG_SWE, 19),
        DAN(HisenseCountryLanInfo.LOCALE_DAN_CODE, MtkTvConfigTypeBase.S638_CFG_LANG_DAN, 20),
        FIN(HisenseCountryLanInfo.LOCALE_FIN_CODE, MtkTvConfigTypeBase.S639_CFG_LANG_FIN, 21),
        NOR(MtkTvConfigTypeBase.S639_CFG_LANG_NOR, MtkTvConfigTypeBase.S639_CFG_LANG_NOR, 22),
        CZE(HisenseCountryLanInfo.LOCALE_CZE_CODE, MtkTvConfigTypeBase.S639_CFG_LANG_CZE, 23),
        SLK(HisenseCountryLanInfo.LOCALE_SLK_CODE, MtkTvConfigTypeBase.S639_CFG_LANG_SLK, 24),
        POL(HisenseCountryLanInfo.LOCALE_POL_CODE, MtkTvConfigTypeBase.S639_CFG_LANG_POL, 25),
        HUN(HisenseCountryLanInfo.LOCALE_HUN_CODE, MtkTvConfigTypeBase.S639_CFG_LANG_HUN, 26),
        BUL(MtkTvConfigTypeBase.S639_CFG_LANG_BUL, MtkTvConfigTypeBase.S639_CFG_LANG_BUL, 27),
        TUR(HisenseCountryLanInfo.LOCALE_TUR_CODE, MtkTvConfigTypeBase.S639_CFG_LANG_TUR, 28),
        PER(HisenseCountryLanInfo.LOCALE_PER_CODE, MtkTvConfigTypeBase.S639_CFG_LANG_PER, 29),
        IND(HisenseCountryLanInfo.LOCALE_IND_CODE, MtkTvConfigTypeBase.S639_CFG_LANG_IND, 30),
        HRV(HisenseCountryLanInfo.LOCALE_HRV_CODE, MtkTvConfigTypeBase.S639_CFG_LANG_HRV, 31),
        SRP(HisenseCountryLanInfo.LOCALE_SRP_CODE, MtkTvConfigTypeBase.S639_CFG_LANG_SRP, 32),
        MAC("mac", "mac", 33),
        ALB("alb", "alb", 34),
        LAV(MtkTvConfigTypeBase.S639_CFG_LANG_LAV, MtkTvConfigTypeBase.S639_CFG_LANG_LAV, 35),
        EST(HisenseCountryLanInfo.LOCALE_EST_CODE, MtkTvConfigTypeBase.S639_CFG_LANG_EST, 36),
        LIT(MtkTvConfigTypeBase.S639_CFG_LANG_LIT, MtkTvConfigTypeBase.S639_CFG_LANG_LIT, 37),
        KUR(HisenseCountryLanInfo.LOCALE_KUR_CODE, "kur", 38),
        SLV(HisenseCountryLanInfo.LOCALE_SLV_CODE, MtkTvConfigTypeBase.S639_CFG_LANG_SLV, 39),
        DUT(MtkTvConfigTypeBase.S639_CFG_LANG_DUT, MtkTvConfigTypeBase.S639_CFG_LANG_DUT, 40),
        RUM(HisenseCountryLanInfo.LOCALE_RUM_CODE, MtkTvConfigTypeBase.S639_CFG_LANG_RUM, 41),
        GRE(HisenseCountryLanInfo.LOCALE_GRE_CODE, MtkTvConfigTypeBase.S639_CFG_LANG_GRE, 42),
        ARM(HisenseCountryLanInfo.LOCALE_ARM_CODE, "arm", 43),
        GEO(HisenseCountryLanInfo.LOCALE_GEO_CODE, "geo", 44),
        URD(HisenseCountryLanInfo.LOCALE_URD_CODE, MtkTvConfigTypeBase.S639_CFG_LANG_URD, 45),
        AMH(HisenseCountryLanInfo.LOCALE_AMH_CODE, MtkTvConfigTypeBase.S639_CFG_LANG_AMH, 46),
        KIR(HisenseCountryLanInfo.LOCALE_KIR_CODE, "kir", 47),
        KAZ(HisenseCountryLanInfo.LOCALE_KAZ_CODE, MtkTvConfigTypeBase.S639_CFG_LANG_KAZ, 48),
        AZE(HisenseCountryLanInfo.LOCALE_AZE_CODE, MtkTvConfigTypeBase.S639_CFG_LANG_AZE, 49),
        BSN("bsn", "bsn", 50),
        ICL("icl", "icl", 51),
        BYL("byl", "byl", 52),
        HIN2(MtkTvConfigTypeBase.S639_CFG_LANG_HIN, MtkTvConfigTypeBase.S639_CFG_LANG_HIN, 53),
        MLD("mld", "mld", 54),
        BNG(HisenseCountryLanInfo.LOCALE_BNG_CODE, "bng", 55),
        MOG(HisenseCountryLanInfo.LOCALE_MOG_CODE, "mog", 56),
        CNR("cnr", "cnr", 57),
        JPN(HisenseCountryLanInfo.LOCALE_JPN_CODE, MtkTvConfigTypeBase.S639_CFG_LANG_JPN, 68),
        KHM(HisenseCountryLanInfo.LOCALE_KHM_CODE, MtkTvConfigTypeBase.S3166_CFG_COUNT_KHM, 69),
        KOR(HisenseCountryLanInfo.LOCALE_KOR_CODE, MtkTvConfigTypeBase.S3166_CFG_COUNT_KOR, 71),
        SIN(HisenseCountryLanInfo.LOCALE_SIN_CODE, "SIN", 81),
        TAM(HisenseCountryLanInfo.LOCALE_TAM_CODE, "TAM", 90),
        TEL("te", "TEL", 91),
        KAN(HisenseCountryLanInfo.LOCALE_KAN_CODE, "KAN", 93),
        MAR(HisenseCountryLanInfo.LOCALE_MAR_CODE, MtkTvConfigTypeBase.S3166_CFG_COUNT_MAR, 94),
        GUJ(HisenseCountryLanInfo.LOCALE_GUJ_CODE, "GUJ", 96),
        SWA("sw", MtkTvConfigTypeBase.S639_CFG_LANG_SWA, 97),
        BOS(HisenseCountryLanInfo.LOCALE_BOS_CODE, "bos", 98),
        NDL(HisenseCountryLanInfo.LOCALE_NLD_CODE, "nld", 100),
        LTU(HisenseCountryLanInfo.LOCALE_LTU_CODE, MtkTvConfigTypeBase.S639_CFG_LANG_LIT, 101),
        LTV(HisenseCountryLanInfo.LOCALE_LTV_CODE, MtkTvConfigTypeBase.S639_CFG_LANG_LAV, 102),
        BUG(HisenseCountryLanInfo.LOCALE_BUG_CODE, MtkTvConfigTypeBase.S639_CFG_LANG_BUL, 103),
        SHQ(HisenseCountryLanInfo.LOCALE_SHQ_CODE, "SHQ", 104),
        MAK(HisenseCountryLanInfo.LOCALE_MAK_CODE, "MAK", 105),
        NOB(HisenseCountryLanInfo.LOCALE_NOB_CODE, "nob", 106),
        ISL(HisenseCountryLanInfo.LOCALE_ISL_CODE, MtkTvConfigTypeBase.S3166_CFG_COUNT_ISL, 107),
        BEL(HisenseCountryLanInfo.LOCALE_BEL_CODE, MtkTvConfigTypeBase.S3166_CFG_COUNT_BEL, 108),
        ZUL("zu", MtkTvConfigTypeBase.S639_CFG_LANG_ZUL, 109),
        GLG("gl", "glg", MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_OUT_OF_SAFE_RANGE_END),
        FIL("fil", "fil", 111),
        EUS("eu", "eus", 112),
        CAT("ca", MtkTvConfigTypeBase.S639_CFG_LANG_CAT, 113),
        AFR("af", MtkTvConfigTypeBase.S639_CFG_LANG_AFR, 114);

        public int langIndex;
        public String langLocaleStr;
        public String langTvStr;

        LanguageCode(String str, String str2, int i) {
            this.langLocaleStr = str;
            this.langTvStr = str2;
            this.langIndex = i;
        }

        public static int getIndexByLocaleLanguage(String str) {
            for (LanguageCode languageCode : values()) {
                if (languageCode.getLocaleLanguage().equalsIgnoreCase(str)) {
                    return languageCode.langIndex;
                }
            }
            return -1;
        }

        public static int getIndexByTvLanguage(String str) {
            for (LanguageCode languageCode : values()) {
                if (languageCode.getTvLanguage().equalsIgnoreCase(str)) {
                    return languageCode.langIndex;
                }
            }
            return -1;
        }

        public static String getLocaleLanguageByIndex(int i) {
            for (LanguageCode languageCode : values()) {
                if (languageCode.getIndex() == i) {
                    return languageCode.langLocaleStr;
                }
            }
            return null;
        }

        public static String getLocaleLanguageByTvLanguage(String str) {
            for (LanguageCode languageCode : values()) {
                if (languageCode.getTvLanguage().equalsIgnoreCase(str)) {
                    return languageCode.langTvStr;
                }
            }
            return null;
        }

        public static String getTvLanguageByIndex(int i) {
            for (LanguageCode languageCode : values()) {
                if (languageCode.getIndex() == i) {
                    return languageCode.langTvStr;
                }
            }
            return null;
        }

        public static String getTvLanguageByLocaleLanguage(String str) {
            for (LanguageCode languageCode : values()) {
                if (languageCode.getLocaleLanguage().equalsIgnoreCase(str)) {
                    return languageCode.langTvStr;
                }
            }
            return null;
        }

        public int getIndex() {
            return this.langIndex;
        }

        public String getLocaleLanguage() {
            return this.langLocaleStr;
        }

        public String getTvLanguage() {
            return this.langTvStr;
        }

        public void setIndex(int i) {
            this.langIndex = i;
        }

        public void setLocaleLanguage(String str) {
            this.langLocaleStr = str;
        }

        public void setTvLanguage(String str) {
            this.langTvStr = str;
        }
    }

    public enum BrandCode {
        HIS(1, HisenseCountryLanInfo.LOGO_HISENSE_INDEX),
        WEL(2, "wel"),
        NOB(3, "nob"),
        JVC(4, "jvc"),
        SAN(5, "san"),
        PHI(6, "phi"),
        ILO(7, "ilo"),
        PIO(8, "pio"),
        SMA(9, "sma"),
        BGH(10, "bgh"),
        BLU(11, "blu"),
        DEV(12, "dev"),
        KAL(13, "kal"),
        CHA(14, "cha"),
        PAN(15, "pan"),
        TEL(16, MtkTvConfigTypeBase.S639_CFG_LANG_TEL),
        RIV(17, "riv"),
        CON(18, "con"),
        GEN(19, "gen"),
        HAA(20, "haa"),
        TIT(21, "tit"),
        KMC(22, "kmc"),
        VUZ(23, "vuz"),
        LLO(24, "llo"),
        INN(25, "inn"),
        SHO(26, "sho"),
        ENF(27, "enf"),
        DLC(28, "dlc"),
        HYA(29, "hya"),
        DEX(30, "dex"),
        SUP(31, "sup"),
        OKE(32, "oke"),
        ROL(33, "rol"),
        SHA(34, "sha"),
        HAR(35, "har"),
        EZY(36, "ezy"),
        AME(37, "ame"),
        DIK(38, "dik"),
        CYB(39, "cyb"),
        HYU(40, "hyu"),
        GFO(41, "gfo"),
        SEI(42, "sei"),
        TON(43, "ton"),
        PSC(44, "psc"),
        THO(45, "tho"),
        ORA(46, "ora"),
        VTB(47, "vtb"),
        ROS(48, "ros"),
        SHP(49, "shp"),
        DEL(50, "del"),
        STR(51, "str"),
        DAM(52, "dam"),
        COR(53, "cor"),
        MTX(54, "mtx"),
        GUA(55, "gua"),
        BEC(56, "bec"),
        IDH(57, "idh"),
        VIV(58, "viv"),
        TAT(59, "tat"),
        BEN(60, "ben"),
        CMO(61, "cmo"),
        ORO(62, "oro"),
        SCH(63, "sch"),
        RCA(64, "rca"),
        PRO(65, "pro"),
        NIK(66, "nik"),
        FAM(67, "fam"),
        MAG(68, "mag"),
        XVI(69, "xvi"),
        ABA(70, "aba"),
        AKI(71, "aki"),
        BPL(72, "bpl"),
        ECO(73, "eco"),
        GLE(74, MtkTvConfigTypeBase.S639_CFG_LANG_GLE),
        LIV(75, "liv"),
        MTC(76, "mtc"),
        SIR(77, "sir"),
        SOL(78, "sol"),
        WAL(79, "wal"),
        ALM(80, "alm"),
        NOR(81, MtkTvConfigTypeBase.S639_CFG_LANG_NOR),
        POL(82, MtkTvConfigTypeBase.S639_CFG_LANG_POL),
        MEO(83, "meo"),
        AVI(84, "avi"),
        ADM(85, "adm"),
        ALH(86, "alh"),
        TOH(87, "toh"),
        AKA(88, "aka"),
        VIG(89, "vig"),
        VIS(90, "vis"),
        RAN(91, "ran"),
        DOF(92, "dof"),
        ONI(93, "Oni"),
        OHI(94, "ohi"),
        TOS(95, HisenseCountryLanInfo.LOGO_TOSHIBA_INDEX),
        KIO(96, "kio"),
        MUL(97, MtkTvConfigTypeBase.S639_CFG_LANG_MUL),
        MAR(98, "mar"),
        OFF(100, MtkTvConfigTypeBase.S639_CFG_SUBTITLE_LANG_OFF),
        OTH(101, "oth"),
        IKA(105, "ika"),
        GLO(106, "glo"),
        NEX(107, "nex"),
        MAS(108, "mas"),
        VIT(109, "vit"),
        SIG(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_OUT_OF_SAFE_RANGE_END, "sig"),
        IND(111, MtkTvConfigTypeBase.S639_CFG_LANG_IND),
        BRN(112, "brn"),
        KEN(113, "ken"),
        TUB(114, "tub"),
        HIF(115, "hif"),
        LTT(116, "ltt"),
        MAQ(117, "maq"),
        ABJ(118, "abj"),
        ONR(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_NO_CONTENT, "onr"),
        AMS(120, "ams"),
        IMM(121, "imm"),
        ZIF(122, "zif"),
        CAS(123, "cas"),
        SGL(124, "sgl"),
        ECP(125, "ecp"),
        WSA(126, "wsa"),
        SMP(127, "smp"),
        QUA(128, "qua"),
        ADI(129, "adi"),
        IVW(130, "ivw"),
        UNE(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_GUIDANCE_BLOCKED_DUR_THUMB, "une"),
        OTW(132, "otw"),
        OZO(133, "ozo"),
        DOR(134, "dor"),
        HAS(135, "has"),
        CLA(136, "cla"),
        AKN(136, "akn");

        public int brandIndex;
        public String brandName;

        BrandCode(int i, String str) {
            this.brandIndex = i;
            this.brandName = str;
        }

        public static String getName(int i) {
            for (BrandCode brandCode : values()) {
                if (brandCode.getIndex() == i) {
                    return brandCode.brandName;
                }
            }
            return null;
        }

        public int getIndex() {
            return this.brandIndex;
        }

        public void setIndex(int i) {
            this.brandIndex = i;
        }

        public void setName(String str) {
            this.brandName = str;
        }

        public String getName() {
            return this.brandName;
        }
    }

    public enum CountryCode {
        ZZZ(HisenseCountryLanInfo.COUNTRY_ZZ, "ZZZ", 1),
        EGY(HisenseCountryLanInfo.COUNTRY_EG, MtkTvConfigTypeBase.S3166_CFG_COUNT_EGY, 2),
        MAR(HisenseCountryLanInfo.COUNTRY_MA, MtkTvConfigTypeBase.S3166_CFG_COUNT_MAR, 3),
        NGA(HisenseCountryLanInfo.COUNTRY_NG, MtkTvConfigTypeBase.S3166_CFG_COUNT_NGQ, 4),
        LBY(HisenseCountryLanInfo.COUNTRY_LY, MtkTvConfigTypeBase.S3166_CFG_COUNT_LBY, 5),
        GHA(HisenseCountryLanInfo.COUNTRY_GH, MtkTvConfigTypeBase.S3166_CFG_COUNT_GHA, 6),
        SLE(HisenseCountryLanInfo.COUNTRY_SL, MtkTvConfigTypeBase.S3166_CFG_COUNT_SLE, 7),
        CIV(HisenseCountryLanInfo.COUNTRY_CI, MtkTvConfigTypeBase.S3166_CFG_COUNT_CIV, 8),
        AGO(HisenseCountryLanInfo.COUNTRY_AO, MtkTvConfigTypeBase.S3166_CFG_COUNT_AGO, 9),
        BEN(HisenseCountryLanInfo.COUNTRY_BJ, MtkTvConfigTypeBase.S3166_CFG_COUNT_BEN, 10),
        LBR(HisenseCountryLanInfo.COUNTRY_LR, MtkTvConfigTypeBase.S3166_CFG_COUNT_LBR, 11),
        COD(HisenseCountryLanInfo.COUNTRY_CD, MtkTvConfigTypeBase.S3166_CFG_COUNT_COD, 12),
        CMR(HisenseCountryLanInfo.COUNTRY_CM, MtkTvConfigTypeBase.S3166_CFG_COUNT_CMR, 13),
        ZWE(HisenseCountryLanInfo.COUNTRY_ZW, MtkTvConfigTypeBase.S3166_CFG_COUNT_ZWE, 14),
        UGA(HisenseCountryLanInfo.COUNTRY_UG, MtkTvConfigTypeBase.S3166_CFG_COUNT_UGA, 15),
        TZA(HisenseCountryLanInfo.COUNTRY_TZ, MtkTvConfigTypeBase.S3166_CFG_COUNT_TZA, 16),
        MUS(HisenseCountryLanInfo.COUNTRY_MU, MtkTvConfigTypeBase.S3166_CFG_COUNT_MUS, 17),
        SDN(HisenseCountryLanInfo.COUNTRY_SD, MtkTvConfigTypeBase.S3166_CFG_COUNT_SDN, 18),
        DJI(HisenseCountryLanInfo.COUNTRY_DJ, MtkTvConfigTypeBase.S3166_CFG_COUNT_DJI, 19),
        ETH(HisenseCountryLanInfo.COUNTRY_ET, MtkTvConfigTypeBase.S3166_CFG_COUNT_ETH, 20),
        MOZ(HisenseCountryLanInfo.COUNTRY_MZ, MtkTvConfigTypeBase.S3166_CFG_COUNT_MOZ, 21),
        MDG(HisenseCountryLanInfo.COUNTRY_MG, MtkTvConfigTypeBase.S3166_CFG_COUNT_MDG, 22),
        DZA(HisenseCountryLanInfo.COUNTRY_DZ, MtkTvConfigTypeBase.S3166_CFG_COUNT_DZA, 23),
        ZAF(HisenseCountryLanInfo.COUNTRY_ZA, "ZAF", 24),
        THA(HisenseCountryLanInfo.COUNTRY_TH, "THA", 25),
        VNM(HisenseCountryLanInfo.COUNTRY_VN, MtkTvConfigTypeBase.S3166_CFG_COUNT_VNM, 26),
        MMR(HisenseCountryLanInfo.COUNTRY_MM, MtkTvConfigTypeBase.S3166_CFG_COUNT_MMR, 27),
        MYS(HisenseCountryLanInfo.COUNTRY_MY, MtkTvConfigTypeBase.S3166_CFG_COUNT_MYS, 28),
        RUS(HisenseCountryLanInfo.COUNTRY_RU, MtkTvConfigTypeBase.S3166_CFG_COUNT_RUS, 29),
        UZB(HisenseCountryLanInfo.COUNTRY_UZ, MtkTvConfigTypeBase.S3166_CFG_COUNT_UZB, 30),
        KGZ(HisenseCountryLanInfo.COUNTRY_KG, MtkTvConfigTypeBase.S3166_CFG_COUNT_KGZ, 31),
        TKM(HisenseCountryLanInfo.COUNTRY_TM, MtkTvConfigTypeBase.S3166_CFG_COUNT_TKM, 32),
        TUR(HisenseCountryLanInfo.COUNTRY_TR, MtkTvConfigTypeBase.S3166_CFG_COUNT_TUR, 33),
        UKR(HisenseCountryLanInfo.COUNTRY_UA, MtkTvConfigTypeBase.S3166_CFG_COUNT_UKR, 34),
        AZE(HisenseCountryLanInfo.COUNTRY_AZ, MtkTvConfigTypeBase.S3166_CFG_COUNT_AZE, 35),
        GEO(HisenseCountryLanInfo.COUNTRY_GE, MtkTvConfigTypeBase.S3166_CFG_COUNT_GEO, 36),
        ARM(HisenseCountryLanInfo.COUNTRY_AM, MtkTvConfigTypeBase.S3166_CFG_COUNT_ARM, 37),
        IND(HisenseCountryLanInfo.COUNTRY_IN, MtkTvConfigTypeBase.S3166_CFG_COUNT_IND, 38),
        IDN(HisenseCountryLanInfo.COUNTRY_ID, MtkTvConfigTypeBase.S3166_CFG_COUNT_IDN, 39),
        IRQ(HisenseCountryLanInfo.COUNTRY_IQ, MtkTvConfigTypeBase.S3166_CFG_COUNT_IRQ, 40),
        SAU(HisenseCountryLanInfo.COUNTRY_SA, MtkTvConfigTypeBase.S3166_CFG_COUNT_SAU, 41),
        ARE(HisenseCountryLanInfo.COUNTRY_AE, MtkTvConfigTypeBase.S3166_CFG_COUNT_ARE, 42),
        KWT(HisenseCountryLanInfo.COUNTRY_KW, MtkTvConfigTypeBase.S3166_CFG_COUNT_KWT, 43),
        YEM(HisenseCountryLanInfo.COUNTRY_YE, MtkTvConfigTypeBase.S3166_CFG_COUNT_YEM, 44),
        OMN(HisenseCountryLanInfo.COUNTRY_OM, MtkTvConfigTypeBase.S3166_CFG_COUNT_OMN, 45),
        QAT(HisenseCountryLanInfo.COUNTRY_QA, MtkTvConfigTypeBase.S3166_CFG_COUNT_QAT, 46),
        JOR(HisenseCountryLanInfo.COUNTRY_JO, MtkTvConfigTypeBase.S3166_CFG_COUNT_JOR, 47),
        IRN(HisenseCountryLanInfo.COUNTRY_IR, MtkTvConfigTypeBase.S3166_CFG_COUNT_IRN, 48),
        ISR(HisenseCountryLanInfo.COUNTRY_IL, MtkTvConfigTypeBase.S3166_CFG_COUNT_ISR, 49),
        DXB("DXB", "DXB", 50),
        AUS(HisenseCountryLanInfo.COUNTRY_AU, "AUS", 51),
        TWN(HisenseCountryLanInfo.COUNTRY_TW, MtkTvConfigTypeBase.S3166_CFG_COUNT_TWN, 52),
        BRA(HisenseCountryLanInfo.COUNTRY_BR, "BRA", 53),
        VEN(HisenseCountryLanInfo.COUNTRY_VE, MtkTvConfigTypeBase.S3166_CFG_COUNT_VEN, 54),
        ARG(HisenseCountryLanInfo.COUNTRY_AR, "ARG", 55),
        PHL(HisenseCountryLanInfo.COUNTRY_PH, MtkTvConfigTypeBase.S3166_CFG_COUNT_PHL, 56),
        PAN(HisenseCountryLanInfo.COUNTRY_PA, MtkTvConfigTypeBase.S3166_CFG_COUNT_PAN, 57),
        URY(HisenseCountryLanInfo.COUNTRY_UY, MtkTvConfigTypeBase.S3166_CFG_COUNT_URY, 58),
        USA(HisenseCountryLanInfo.COUNTRY_US, MtkTvConfigTypeBase.S3166_CFG_COUNT_USA, 59),
        CAN(HisenseCountryLanInfo.COUNTRY_CA, MtkTvConfigTypeBase.S3166_CFG_COUNT_CAN, 60),
        MEX(HisenseCountryLanInfo.COUNTRY_MX, MtkTvConfigTypeBase.S3166_CFG_COUNT_MEX, 61),
        DEU(HisenseCountryLanInfo.COUNTRY_DE, MtkTvConfigTypeBase.S3166_CFG_COUNT_DEU, 62),
        AUT(HisenseCountryLanInfo.COUNTRY_AT, MtkTvConfigTypeBase.S3166_CFG_COUNT_AUT, 63),
        ITA(HisenseCountryLanInfo.COUNTRY_IT, MtkTvConfigTypeBase.S3166_CFG_COUNT_ITA, 64),
        GBR(HisenseCountryLanInfo.COUNTRY_GB, MtkTvConfigTypeBase.S3166_CFG_COUNT_GBR, 65),
        ESP(HisenseCountryLanInfo.COUNTRY_ES, "ESP", 66),
        FRA(HisenseCountryLanInfo.COUNTRY_FR, "FRA", 67),
        CHE(HisenseCountryLanInfo.COUNTRY_CH, MtkTvConfigTypeBase.S3166_CFG_COUNT_CHE, 68),
        PRT(HisenseCountryLanInfo.COUNTRY_PT, MtkTvConfigTypeBase.S3166_CFG_COUNT_PRT, 69),
        SWE(HisenseCountryLanInfo.COUNTRY_SE, MtkTvConfigTypeBase.S3166_CFG_COUNT_SWE, 70),
        DNK(HisenseCountryLanInfo.COUNTRY_DK, MtkTvConfigTypeBase.S3166_CFG_COUNT_DNK, 71),
        FIN(HisenseCountryLanInfo.COUNTRY_FI, MtkTvConfigTypeBase.S3166_CFG_COUNT_FIN, 72),
        NOR(HisenseCountryLanInfo.COUNTRY_NO, MtkTvConfigTypeBase.S3166_CFG_COUNT_NOR, 73),
        CZE(HisenseCountryLanInfo.COUNTRY_CZ, MtkTvConfigTypeBase.S3166_CFG_COUNT_CZE, 74),
        SVK(HisenseCountryLanInfo.COUNTRY_SK, MtkTvConfigTypeBase.S3166_CFG_COUNT_SVK, 75),
        POL(HisenseCountryLanInfo.COUNTRY_PL, MtkTvConfigTypeBase.S3166_CFG_COUNT_POL, 76),
        HUN(HisenseCountryLanInfo.COUNTRY_HU, MtkTvConfigTypeBase.S3166_CFG_COUNT_HUN, 77),
        BGR(HisenseCountryLanInfo.COUNTRY_BG, MtkTvConfigTypeBase.S3166_CFG_COUNT_BGR, 78),
        COG(HisenseCountryLanInfo.COUNTRY_CG, MtkTvConfigTypeBase.S3166_CFG_COUNT_COG, 79),
        KEN(HisenseCountryLanInfo.COUNTRY_KE, MtkTvConfigTypeBase.S3166_CFG_COUNT_KEN, 80),
        LKA(HisenseCountryLanInfo.COUNTRY_LK, MtkTvConfigTypeBase.S3166_CFG_COUNT_LKA, 81),
        ECU(HisenseCountryLanInfo.COUNTRY_EC, MtkTvConfigTypeBase.S3166_CFG_COUNT_ECU, 82),
        COL(HisenseCountryLanInfo.COUNTRY_CO, MtkTvConfigTypeBase.S3166_CFG_COUNT_COL, 83),
        PER(HisenseCountryLanInfo.COUNTRY_PE, MtkTvConfigTypeBase.S3166_CFG_COUNT_PER, 84),
        KAZ(HisenseCountryLanInfo.COUNTRY_KZ, MtkTvConfigTypeBase.S3166_CFG_COUNT_KAZ, 85),
        CHL(HisenseCountryLanInfo.COUNTRY_CL, "CHL", 86),
        CRI(HisenseCountryLanInfo.COUNTRY_CR, MtkTvConfigTypeBase.S3166_CFG_COUNT_CRI, 87),
        MDV("MV", MtkTvConfigTypeBase.S3166_CFG_COUNT_MDV, 88),
        FJI(HisenseCountryLanInfo.COUNTRY_FJ, MtkTvConfigTypeBase.S3166_CFG_COUNT_FJI, 89),
        TJK(HisenseCountryLanInfo.COUNTRY_TJ, MtkTvConfigTypeBase.S3166_CFG_COUNT_TJK, 90),
        SRB(HisenseCountryLanInfo.COUNTRY_RS, MtkTvConfigTypeBase.S3166_CFG_COUNT_SRB, 91),
        BLR(HisenseCountryLanInfo.COUNTRY_BY, MtkTvConfigTypeBase.S3166_CFG_COUNT_BLR, 92),
        MDA(HisenseCountryLanInfo.COUNTRY_MD, MtkTvConfigTypeBase.S3166_CFG_COUNT_MDA, 93),
        GIN(HisenseCountryLanInfo.COUNTRY_GN, MtkTvConfigTypeBase.S3166_CFG_COUNT_GIN, 94),
        BDI(HisenseCountryLanInfo.COUNTRY_BI, MtkTvConfigTypeBase.S3166_CFG_COUNT_BDI, 95),
        SEN(HisenseCountryLanInfo.COUNTRY_SN, MtkTvConfigTypeBase.S3166_CFG_COUNT_SEN, 96),
        TUN(HisenseCountryLanInfo.COUNTRY_TN, MtkTvConfigTypeBase.S3166_CFG_COUNT_TUN, 97),
        SOM(HisenseCountryLanInfo.COUNTRY_SO, MtkTvConfigTypeBase.S3166_CFG_COUNT_SOM, 98),
        BGD(HisenseCountryLanInfo.COUNTRY_BD, MtkTvConfigTypeBase.S3166_CFG_COUNT_BGD, 99),
        PAK(HisenseCountryLanInfo.COUNTRY_PK, MtkTvConfigTypeBase.S3166_CFG_COUNT_PAK, 100),
        MNG(HisenseCountryLanInfo.COUNTRY_MN, MtkTvConfigTypeBase.S3166_CFG_COUNT_MNG, 101),
        NPL(HisenseCountryLanInfo.COUNTRY_NP, MtkTvConfigTypeBase.S3166_CFG_COUNT_NPL, 102),
        KHM(HisenseCountryLanInfo.COUNTRY_KH, MtkTvConfigTypeBase.S3166_CFG_COUNT_KHM, 103),
        BHR(HisenseCountryLanInfo.COUNTRY_BH, MtkTvConfigTypeBase.S3166_CFG_COUNT_BHR, 104),
        LBN(HisenseCountryLanInfo.COUNTRY_LB, MtkTvConfigTypeBase.S3166_CFG_COUNT_LBN, 105),
        SYR(HisenseCountryLanInfo.COUNTRY_SY, MtkTvConfigTypeBase.S3166_CFG_COUNT_SYR, 106),
        PSE(HisenseCountryLanInfo.COUNTRY_PS, MtkTvConfigTypeBase.S3166_CFG_COUNT_PSE, 107),
        PRY(HisenseCountryLanInfo.COUNTRY_PY, MtkTvConfigTypeBase.S3166_CFG_COUNT_PRY, 108),
        BOL(HisenseCountryLanInfo.COUNTRY_BO, MtkTvConfigTypeBase.S3166_CFG_COUNT_BOL, 109),
        HRV(HisenseCountryLanInfo.COUNTRY_HR, MtkTvConfigTypeBase.S3166_CFG_COUNT_HRV, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_OUT_OF_SAFE_RANGE_END),
        LVA(HisenseCountryLanInfo.COUNTRY_LV, MtkTvConfigTypeBase.S3166_CFG_COUNT_LVA, 111),
        EST(HisenseCountryLanInfo.COUNTRY_EE, MtkTvConfigTypeBase.S3166_CFG_COUNT_EST, 112),
        LTU(HisenseCountryLanInfo.COUNTRY_LT, MtkTvConfigTypeBase.S3166_CFG_COUNT_LTU, 113),
        NIC(HisenseCountryLanInfo.COUNTRY_NI, MtkTvConfigTypeBase.S3166_CFG_COUNT_NIC, 114),
        HND(HisenseCountryLanInfo.COUNTRY_HN, MtkTvConfigTypeBase.S3166_CFG_COUNT_HND, 115),
        SLV(HisenseCountryLanInfo.COUNTRY_SV, MtkTvConfigTypeBase.S3166_CFG_COUNT_SLV, 116),
        GTM(HisenseCountryLanInfo.COUNTRY_GT, MtkTvConfigTypeBase.S3166_CFG_COUNT_GTM, 117),
        DOM(HisenseCountryLanInfo.COUNTRY_DO, MtkTvConfigTypeBase.S3166_CFG_COUNT_DOM, 118),
        CUB(HisenseCountryLanInfo.COUNTRY_CU, MtkTvConfigTypeBase.S3166_CFG_COUNT_CUB, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_NO_CONTENT),
        TTO(HisenseCountryLanInfo.COUNTRY_TT, MtkTvConfigTypeBase.S3166_CFG_COUNT_TTO, 120),
        JAM(HisenseCountryLanInfo.COUNTRY_JM, MtkTvConfigTypeBase.S3166_CFG_COUNT_JAM, 121),
        CUW(HisenseCountryLanInfo.COUNTRY_CW, MtkTvConfigTypeBase.S3166_CFG_COUNT_CUW, 122),
        BRB(HisenseCountryLanInfo.COUNTRY_BB, MtkTvConfigTypeBase.S3166_CFG_COUNT_BRB, 123),
        MAF(HisenseCountryLanInfo.COUNTRY_MF, MtkTvConfigTypeBase.S3166_CFG_COUNT_MAF, 124),
        PRI(HisenseCountryLanInfo.COUNTRY_PR, MtkTvConfigTypeBase.S3166_CFG_COUNT_PRI, 125),
        SGP(HisenseCountryLanInfo.COUNTRY_SG, "SGP", 126),
        SVN(HisenseCountryLanInfo.COUNTRY_SI, MtkTvConfigTypeBase.S3166_CFG_COUNT_SVN, 127),
        NLD(HisenseCountryLanInfo.COUNTRY_NL, MtkTvConfigTypeBase.S3166_CFG_COUNT_NLD, 128),
        MLI(HisenseCountryLanInfo.COUNTRY_ML, MtkTvConfigTypeBase.S3166_CFG_COUNT_MLI, 129),
        SSD(HisenseCountryLanInfo.COUNTRY_SS, MtkTvConfigTypeBase.S3166_CFG_COUNT_SSD, 130),
        GRC(HisenseCountryLanInfo.COUNTRY_GR, MtkTvConfigTypeBase.S3166_CFG_COUNT_GRC, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_GUIDANCE_BLOCKED_DUR_THUMB),
        BIH(HisenseCountryLanInfo.COUNTRY_BA, MtkTvConfigTypeBase.S3166_CFG_COUNT_BIH, 132),
        BWA(HisenseCountryLanInfo.COUNTRY_BW, MtkTvConfigTypeBase.S3166_CFG_COUNT_BWA, 133),
        BFA(HisenseCountryLanInfo.COUNTRY_BF, MtkTvConfigTypeBase.S3166_CFG_COUNT_BFA, 134),
        ERI(HisenseCountryLanInfo.COUNTRY_ER, MtkTvConfigTypeBase.S3166_CFG_COUNT_ERI, 135),
        GAB(HisenseCountryLanInfo.COUNTRY_GA, MtkTvConfigTypeBase.S3166_CFG_COUNT_GAB, 136),
        RWA(HisenseCountryLanInfo.COUNTRY_RW, MtkTvConfigTypeBase.S3166_CFG_COUNT_RWA, 137),
        MWI(HisenseCountryLanInfo.COUNTRY_MW, MtkTvConfigTypeBase.S3166_CFG_COUNT_MWI, 138),
        MRT(HisenseCountryLanInfo.COUNTRY_MR, MtkTvConfigTypeBase.S3166_CFG_COUNT_MRT, 139),
        NAM(HisenseCountryLanInfo.COUNTRY_NA, MtkTvConfigTypeBase.S3166_CFG_COUNT_NAM, 140),
        NER(HisenseCountryLanInfo.COUNTRY_NE, MtkTvConfigTypeBase.S3166_CFG_COUNT_NER, 141),
        SYC(HisenseCountryLanInfo.COUNTRY_SC, MtkTvConfigTypeBase.S3166_CFG_COUNT_SYC, 142),
        ZMB(HisenseCountryLanInfo.COUNTRY_ZM, MtkTvConfigTypeBase.S3166_CFG_COUNT_ZMB, 143),
        TCD(HisenseCountryLanInfo.COUNTRY_TD, MtkTvConfigTypeBase.S3166_CFG_COUNT_TCD, 144),
        MKD(HisenseCountryLanInfo.COUNTRY_MK, MtkTvConfigTypeBase.S3166_CFG_COUNT_MKD, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_STARTED),
        ALB(HisenseCountryLanInfo.COUNTRY_AL, MtkTvConfigTypeBase.S3166_CFG_COUNT_ALB, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_STOPPED),
        MNE(HisenseCountryLanInfo.COUNTRY_ME, MtkTvConfigTypeBase.S3166_CFG_COUNT_MNE, 147),
        BHS(HisenseCountryLanInfo.COUNTRY_BS, MtkTvConfigTypeBase.S3166_CFG_COUNT_BHS, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_SOURCE_READY),
        BEL(HisenseCountryLanInfo.COUNTRY_BE, MtkTvConfigTypeBase.S3166_CFG_COUNT_BEL, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_REQ_ES_LIST),
        REU(HisenseCountryLanInfo.COUNTRY_RE, MtkTvConfigTypeBase.S3166_CFG_COUNT_REU, 150),
        MYT(HisenseCountryLanInfo.COUNTRY_YT, MtkTvConfigTypeBase.S3166_CFG_COUNT_MYT, 151),
        NZL(HisenseCountryLanInfo.COUNTRY_NZ, "NZL", 152),
        ROU(HisenseCountryLanInfo.COUNTRY_RO, MtkTvConfigTypeBase.S3166_CFG_COUNT_ROU, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_REACH_BOUND),
        HKG(HisenseCountryLanInfo.COUNTRY_HK, MtkTvConfigTypeBase.S3166_CFG_COUNT_HKG, 154),
        LUX(HisenseCountryLanInfo.COUNTRY_LU, MtkTvConfigTypeBase.S3166_CFG_COUNT_LUX, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_BUF_FULL),
        AFG(HisenseCountryLanInfo.COUNTRY_AF, MtkTvConfigTypeBase.S3166_CFG_COUNT_AFG, 156),
        ALA(HisenseCountryLanInfo.COUNTRY_AX, MtkTvConfigTypeBase.S3166_CFG_COUNT_ALA, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_BLOCKED),
        ASM(HisenseCountryLanInfo.COUNTRY_AS, MtkTvConfigTypeBase.S3166_CFG_COUNT_ASM, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_STRG_FULL),
        AND(HisenseCountryLanInfo.COUNTRY_AD, MtkTvConfigTypeBase.S3166_CFG_COUNT_AND, 159),
        AIA(HisenseCountryLanInfo.COUNTRY_AI, MtkTvConfigTypeBase.S3166_CFG_COUNT_AIA, 160),
        ATG(HisenseCountryLanInfo.COUNTRY_AG, MtkTvConfigTypeBase.S3166_CFG_COUNT_ATG, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_STRG_ERROR),
        ABW(HisenseCountryLanInfo.COUNTRY_AW, MtkTvConfigTypeBase.S3166_CFG_COUNT_ABW, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_INTERNAL_ERROR),
        BLZ(HisenseCountryLanInfo.COUNTRY_BZ, MtkTvConfigTypeBase.S3166_CFG_COUNT_BLZ, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_BAD_TICK),
        BMU(HisenseCountryLanInfo.COUNTRY_BM, MtkTvConfigTypeBase.S3166_CFG_COUNT_BMU, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_CHANNEL_LOCKED),
        BTN(HisenseCountryLanInfo.COUNTRY_BT, MtkTvConfigTypeBase.S3166_CFG_COUNT_BTN, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_PIN_ERROR),
        BES(HisenseCountryLanInfo.COUNTRY_BQ, MtkTvConfigTypeBase.S3166_CFG_COUNT_BES, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_18_PLUS_LOCKED),
        BVT(MtkTvConfigTypeBase.S3166_CFG_COUNT_BVT, MtkTvConfigTypeBase.S3166_CFG_COUNT_BVT, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_CHAPTER_CHANGE),
        IOT(HisenseCountryLanInfo.COUNTRY_IO, MtkTvConfigTypeBase.S3166_CFG_COUNT_IOT, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_CONNECTION_ANA_FREQ),
        BRN(HisenseCountryLanInfo.COUNTRY_BN, MtkTvConfigTypeBase.S3166_CFG_COUNT_BRN, 169),
        CPV(HisenseCountryLanInfo.COUNTRY_CV, MtkTvConfigTypeBase.S3166_CFG_COUNT_CPV, 170),
        CYM(HisenseCountryLanInfo.COUNTRY_KY, MtkTvConfigTypeBase.S3166_CFG_COUNT_CYM, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_TIME_UPDATE),
        CAF(HisenseCountryLanInfo.COUNTRY_CF, MtkTvConfigTypeBase.S3166_CFG_COUNT_CAF, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_UNDERFLOW),
        CHN(HisenseCountryLanInfo.COUNTRY_CN, MtkTvConfigTypeBase.S3166_CFG_COUNT_CHN, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_EOF),
        CXR(HisenseCountryLanInfo.COUNTRY_CX, MtkTvConfigTypeBase.S3166_CFG_COUNT_CXR, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_CLEAR_EOF),
        CCK(HisenseCountryLanInfo.COUNTRY_CC, MtkTvConfigTypeBase.S3166_CFG_COUNT_CCK, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_AUDIO_SWITCH_DONE),
        COM(HisenseCountryLanInfo.COUNTRY_KM, MtkTvConfigTypeBase.S3166_CFG_COUNT_COM, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_GET_IFRAME),
        COK(HisenseCountryLanInfo.COUNTRY_CK, MtkTvConfigTypeBase.S3166_CFG_COUNT_COK, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_STREAM_OPENED),
        CYP(HisenseCountryLanInfo.COUNTRY_CY, MtkTvConfigTypeBase.S3166_CFG_COUNT_CYP, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_VIDEO_NUM_READY),
        DMA(HisenseCountryLanInfo.COUNTRY_DM, MtkTvConfigTypeBase.S3166_CFG_COUNT_DMA, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_MSGQ_FULL),
        GNQ(HisenseCountryLanInfo.COUNTRY_GQ, MtkTvConfigTypeBase.S3166_CFG_COUNT_GNQ, 180),
        SWZ(HisenseCountryLanInfo.COUNTRY_SZ, MtkTvConfigTypeBase.S3166_CFG_COUNT_SWZ, 181),
        FLK(HisenseCountryLanInfo.COUNTRY_FK, MtkTvConfigTypeBase.S3166_CFG_COUNT_FLK, 182),
        FRO(HisenseCountryLanInfo.COUNTRY_FO, MtkTvConfigTypeBase.S3166_CFG_COUNT_FRO, 183),
        GUF(HisenseCountryLanInfo.COUNTRY_GF, MtkTvConfigTypeBase.S3166_CFG_COUNT_GUF, 184),
        PYF(HisenseCountryLanInfo.COUNTRY_PF, MtkTvConfigTypeBase.S3166_CFG_COUNT_PYF, 185),
        ATF(MtkTvConfigTypeBase.S3166_CFG_COUNT_ATF, MtkTvConfigTypeBase.S3166_CFG_COUNT_ATF, 186),
        GMB(HisenseCountryLanInfo.COUNTRY_GM, MtkTvConfigTypeBase.S3166_CFG_COUNT_GMB, HisenseCountryLanInfo.FAC_COUNTRY_GMB_INDEX),
        GIB(HisenseCountryLanInfo.COUNTRY_GI, MtkTvConfigTypeBase.S3166_CFG_COUNT_GIB, 188),
        GRL(HisenseCountryLanInfo.COUNTRY_GL, MtkTvConfigTypeBase.S3166_CFG_COUNT_GRL, 189),
        GRD(HisenseCountryLanInfo.COUNTRY_GD, MtkTvConfigTypeBase.S3166_CFG_COUNT_GRN, 190),
        GLP(HisenseCountryLanInfo.COUNTRY_GP, MtkTvConfigTypeBase.S3166_CFG_COUNT_GLP, 191),
        GUM(HisenseCountryLanInfo.COUNTRY_GU, MtkTvConfigTypeBase.S3166_CFG_COUNT_GUM, 192),
        GGY(HisenseCountryLanInfo.COUNTRY_GG, MtkTvConfigTypeBase.S3166_CFG_COUNT_GGY, GpsStatusWrapper.QZSS_SVID_MIN),
        GNB(HisenseCountryLanInfo.COUNTRY_GW, MtkTvConfigTypeBase.S3166_CFG_COUNT_GNB, 194),
        GUY(HisenseCountryLanInfo.COUNTRY_GY, MtkTvConfigTypeBase.S3166_CFG_COUNT_GUY, 195),
        HTI(HisenseCountryLanInfo.COUNTRY_HT, MtkTvConfigTypeBase.S3166_CFG_COUNT_HTI, 196),
        HMD(MtkTvConfigTypeBase.S3166_CFG_COUNT_HMD, MtkTvConfigTypeBase.S3166_CFG_COUNT_HMD, 197),
        VAT(HisenseCountryLanInfo.COUNTRY_VA, MtkTvConfigTypeBase.S3166_CFG_COUNT_VAT, 198),
        ISL(HisenseCountryLanInfo.COUNTRY_IS, MtkTvConfigTypeBase.S3166_CFG_COUNT_ISL, 199),
        IRL(HisenseCountryLanInfo.COUNTRY_IE, MtkTvConfigTypeBase.S3166_CFG_COUNT_IRL, 200),
        IMN(HisenseCountryLanInfo.COUNTRY_IM, MtkTvConfigTypeBase.S3166_CFG_COUNT_IMN, 201),
        JPN(HisenseCountryLanInfo.COUNTRY_JP, MtkTvConfigTypeBase.S3166_CFG_COUNT_JPN, 202),
        JEY(HisenseCountryLanInfo.COUNTRY_JE, MtkTvConfigTypeBase.S3166_CFG_COUNT_JEY, 203),
        KIR(HisenseCountryLanInfo.COUNTRY_KI, MtkTvConfigTypeBase.S3166_CFG_COUNT_KIR, 204),
        PRK(HisenseCountryLanInfo.COUNTRY_KP, MtkTvConfigTypeBase.S3166_CFG_COUNT_PRK, 205),
        KOR(HisenseCountryLanInfo.COUNTRY_KR, MtkTvConfigTypeBase.S3166_CFG_COUNT_KOR, HisenseCountryLanInfo.FAC_COUNTRY_KOR_INDEX),
        LAO(HisenseCountryLanInfo.COUNTRY_LA, MtkTvConfigTypeBase.S3166_CFG_COUNT_LAO, HisenseCountryLanInfo.FAC_COUNTRY_LAO_INDEX),
        LIE(HisenseCountryLanInfo.COUNTRY_LI, MtkTvConfigTypeBase.S3166_CFG_COUNT_LIE, 208),
        MAC(HisenseCountryLanInfo.COUNTRY_MO, MtkTvConfigTypeBase.S3166_CFG_COUNT_MAC, 210),
        MLT(HisenseCountryLanInfo.COUNTRY_MT, MtkTvConfigTypeBase.S3166_CFG_COUNT_MLT, 211),
        MHL(HisenseCountryLanInfo.COUNTRY_MH, MtkTvConfigTypeBase.S3166_CFG_COUNT_MHL, 212),
        MTQ(HisenseCountryLanInfo.COUNTRY_MQ, MtkTvConfigTypeBase.S3166_CFG_COUNT_MTQ, 213),
        FSM(HisenseCountryLanInfo.COUNTRY_FM, MtkTvConfigTypeBase.S3166_CFG_COUNT_FSM, 214),
        MSR(HisenseCountryLanInfo.COUNTRY_MS, MtkTvConfigTypeBase.S3166_CFG_COUNT_MSR, 215),
        NRU(HisenseCountryLanInfo.COUNTRY_NR, MtkTvConfigTypeBase.S3166_CFG_COUNT_NRU, 217),
        NCL(HisenseCountryLanInfo.COUNTRY_NC, MtkTvConfigTypeBase.S3166_CFG_COUNT_NCL, 218),
        NIU(HisenseCountryLanInfo.COUNTRY_NU, MtkTvConfigTypeBase.S3166_CFG_COUNT_NIU, 219),
        NFK(HisenseCountryLanInfo.COUNTRY_NF, MtkTvConfigTypeBase.S3166_CFG_COUNT_NFK, FragmentManagerImpl.ANIM_DUR),
        MNP(HisenseCountryLanInfo.COUNTRY_MP, MtkTvConfigTypeBase.S3166_CFG_COUNT_MNP, 221),
        PLW(HisenseCountryLanInfo.COUNTRY_PW, MtkTvConfigTypeBase.S3166_CFG_COUNT_PLW, 222),
        PCN(HisenseCountryLanInfo.COUNTRY_PN, MtkTvConfigTypeBase.S3166_CFG_COUNT_PCN, 223),
        BLM(MtkTvConfigTypeBase.S3166_CFG_COUNT_BLM, MtkTvConfigTypeBase.S3166_CFG_COUNT_BLM, 224),
        SHN(MtkTvConfigTypeBase.S3166_CFG_COUNT_SHN, MtkTvConfigTypeBase.S3166_CFG_COUNT_SHN, 225),
        KNA(MtkTvConfigTypeBase.S3166_CFG_COUNT_KNA, MtkTvConfigTypeBase.S3166_CFG_COUNT_KNA, 226),
        LCA(MtkTvConfigTypeBase.S3166_CFG_COUNT_LCA, MtkTvConfigTypeBase.S3166_CFG_COUNT_LCA, 227),
        SPM(MtkTvConfigTypeBase.S3166_CFG_COUNT_SPM, MtkTvConfigTypeBase.S3166_CFG_COUNT_SPM, 228),
        VCT(MtkTvConfigTypeBase.S3166_CFG_COUNT_VCT, MtkTvConfigTypeBase.S3166_CFG_COUNT_VCT, 229),
        WSM(HisenseCountryLanInfo.COUNTRY_WS, MtkTvConfigTypeBase.S3166_CFG_COUNT_WSM, 230),
        SMR(HisenseCountryLanInfo.COUNTRY_SM, MtkTvConfigTypeBase.S3166_CFG_COUNT_SMR, 231),
        STP(HisenseCountryLanInfo.COUNTRY_ST, MtkTvConfigTypeBase.S3166_CFG_COUNT_STP, 232),
        SXM(HisenseCountryLanInfo.COUNTRY_SX, MtkTvConfigTypeBase.S3166_CFG_COUNT_SXM, 233),
        SLB(HisenseCountryLanInfo.COUNTRY_SB, MtkTvConfigTypeBase.S3166_CFG_COUNT_SLB, 234),
        SGS(MtkTvConfigTypeBase.S3166_CFG_COUNT_SQS, MtkTvConfigTypeBase.S3166_CFG_COUNT_SQS, 235),
        SUR(HisenseCountryLanInfo.COUNTRY_SR, MtkTvConfigTypeBase.S3166_CFG_COUNT_SUR, 236),
        SJM(HisenseCountryLanInfo.COUNTRY_SJ, MtkTvConfigTypeBase.S3166_CFG_COUNT_SJM, 237),
        TLS(HisenseCountryLanInfo.COUNTRY_TL, MtkTvConfigTypeBase.S3166_CFG_COUNT_TLS, 238),
        TGO(HisenseCountryLanInfo.COUNTRY_TG, MtkTvConfigTypeBase.S3166_CFG_COUNT_TGO, 239),
        TKL(HisenseCountryLanInfo.COUNTRY_TK, MtkTvConfigTypeBase.S3166_CFG_COUNT_TKL, 240),
        TON(HisenseCountryLanInfo.COUNTRY_TO, MtkTvConfigTypeBase.S3166_CFG_COUNT_TON, 241),
        TCA(HisenseCountryLanInfo.COUNTRY_TC, MtkTvConfigTypeBase.S3166_CFG_COUNT_TCA, 242),
        TUV(HisenseCountryLanInfo.COUNTRY_TV, MtkTvConfigTypeBase.S3166_CFG_COUNT_TUV, 243),
        UMI(HisenseCountryLanInfo.COUNTRY_UM, MtkTvConfigTypeBase.S3166_CFG_COUNT_UNI, 244),
        VUT(HisenseCountryLanInfo.COUNTRY_VU, MtkTvConfigTypeBase.S3166_CFG_COUNT_VUT, 245),
        VGB(HisenseCountryLanInfo.COUNTRY_VG, MtkTvConfigTypeBase.S3166_CFG_COUNT_VGB, 246),
        VIR(HisenseCountryLanInfo.COUNTRY_VI, MtkTvConfigTypeBase.S3166_CFG_COUNT_VIR, 247),
        WLF(HisenseCountryLanInfo.COUNTRY_WF, MtkTvConfigTypeBase.S3166_CFG_COUNT_WLF, 248),
        PNG(HisenseCountryLanInfo.COUNTRY_PG, MtkTvConfigTypeBase.S3166_CFG_COUNT_PNG, 249),
        KOS(HisenseCountryLanInfo.COUNTRY_XK, "KOS", 250),
        ASC("ASC", "ASC", MtkTvScanDvbtBase.EXCHANGE_MAX_LEN);

        public int countryIndex;
        public String countryName;
        public String countryStr;

        CountryCode(String str, String str2, int i) {
            this.countryName = str;
            this.countryStr = str2;
            this.countryIndex = i;
        }

        public static String getCountryStr(String str) {
            for (CountryCode countryCode : values()) {
                if (countryCode.getName().equalsIgnoreCase(str)) {
                    return countryCode.countryStr;
                }
            }
            return null;
        }

        public static int getIndex(String str) {
            for (CountryCode countryCode : values()) {
                if (countryCode.getName().equalsIgnoreCase(str)) {
                    return countryCode.countryIndex;
                }
            }
            return -1;
        }

        public static String getName(int i) {
            for (CountryCode countryCode : values()) {
                if (countryCode.getIndex() == i) {
                    return countryCode.countryName;
                }
            }
            return null;
        }

        public void setCountryStr(String str) {
            this.countryStr = str;
        }

        public void setIndex(int i) {
            this.countryIndex = i;
        }

        public void setName(String str) {
            this.countryName = str;
        }

        public static String getCountryStr(int i) {
            for (CountryCode countryCode : values()) {
                if (countryCode.getIndex() == i) {
                    return countryCode.countryStr;
                }
            }
            return null;
        }

        public int getIndex() {
            return this.countryIndex;
        }

        public String getName() {
            return this.countryName;
        }

        public String getCountryStr() {
            return this.countryStr;
        }
    }
}
