package com.mediatek.twoworlds.tv.model;

import android.util.Log;
import com.mediatek.twoworlds.tv.MtkTvATSCRating;
import com.mediatek.twoworlds.tv.MtkTvDVBRating;
import com.mediatek.twoworlds.tv.MtkTvISDBRating;
import com.mediatek.twoworlds.tv.TVNativeWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvRatingConvert2Goo {
    public static final int BRTV_AGE_10 = 2;
    public static final int BRTV_AGE_12 = 3;
    public static final int BRTV_AGE_14 = 4;
    public static final int BRTV_AGE_16 = 5;
    public static final int BRTV_AGE_18 = 6;
    public static final int BRTV_AGE_L = 1;
    public static final int BRTV_CNT_D = 1;
    public static final int BRTV_CNT_DS = 5;
    public static final int BRTV_CNT_DV = 3;
    public static final int BRTV_CNT_DVS = 7;
    public static final int BRTV_CNT_S = 4;
    public static final int BRTV_CNT_V = 2;
    public static final int BRTV_CNT_VS = 6;
    public static final int CATV_EN_14 = 5;
    public static final int CATV_EN_18 = 6;
    public static final int CATV_EN_C = 1;
    public static final int CATV_EN_C8 = 2;
    public static final int CATV_EN_E = 0;
    public static final int CATV_EN_G = 3;
    public static final int CATV_EN_OFF = 7;
    public static final int CATV_EN_PG = 4;
    public static final int CATV_FR_13 = 3;
    public static final int CATV_FR_16 = 4;
    public static final int CATV_FR_18 = 5;
    public static final int CATV_FR_8 = 2;
    public static final int CATV_FR_E = 0;
    public static final int CATV_FR_G = 1;
    public static final int CATV_FR_OFF = 6;
    public static final int CA_TV_ENG = 0;
    public static final int CA_TV_FRA = 1;
    private static final boolean DEBUG = true;
    private static final String DELIMITER = ",";
    public static final int LOCKED_STATUS_CHANNEL_LOCKED = 2;
    public static final int LOCKED_STATUS_INP_SRC_LOCKED = 3;
    private static final int LOCKED_STATUS_M7_18AGE = 65536;
    public static final int LOCKED_STATUS_NONE = 0;
    public static final int LOCKED_STATUS_PROGRAM_RATING = 1;
    public static final int RATING_MAX_AGE = 255;
    public static final String RATING_STR_10 = "10";
    public static final String RATING_STR_11 = "11";
    public static final String RATING_STR_12 = "12";
    public static final String RATING_STR_13 = "13";
    public static final String RATING_STR_14 = "14";
    public static final String RATING_STR_15 = "15";
    public static final String RATING_STR_16 = "16";
    public static final String RATING_STR_17 = "17";
    public static final String RATING_STR_18 = "18";
    public static final String RATING_STR_19 = "19";
    public static final String RATING_STR_20 = "20";
    public static final String RATING_STR_3 = "3";
    public static final String RATING_STR_4 = "4";
    public static final String RATING_STR_5 = "5";
    public static final String RATING_STR_6 = "6";
    public static final String RATING_STR_7 = "7";
    public static final String RATING_STR_8 = "8";
    public static final String RATING_STR_9 = "9";
    public static final String RATING_STR_ALL = "ALL";
    public static final String RATING_STR_AO = "AO";
    public static final String RATING_STR_ATP = "ATP";
    public static final String RATING_STR_AV = "AV";
    public static final String RATING_STR_C = "C";
    public static final String RATING_STR_CH_LOCK = "channel_lock";
    public static final String RATING_STR_F = "F";
    public static final String RATING_STR_G = "G";
    public static final String RATING_STR_INPUT_LOCK = "input_lock";
    public static final String RATING_STR_L = "L";
    public static final String RATING_STR_M = "M";
    public static final String RATING_STR_M18 = "M18";
    public static final String RATING_STR_MA = "MA";
    public static final String RATING_STR_NC16 = "NC16";
    public static final String RATING_STR_NC18 = "NC_18";
    public static final String RATING_STR_NONE = "NONE";
    public static final String RATING_STR_P = "P";
    public static final String RATING_STR_PG = "PG";
    public static final String RATING_STR_PG10 = "PG10";
    public static final String RATING_STR_PG13 = "PG13";
    public static final String RATING_STR_PG18 = "PG18";
    public static final String RATING_STR_PGR = "PGR";
    public static final String RATING_STR_R = "R";
    public static final String RATING_STR_R18 = "R18";
    public static final String RATING_STR_R21 = "R21";
    public static final String RATING_STR_SAM_13 = "SAM_13";
    public static final String RATING_STR_SAM_16 = "SAM_16";
    public static final String RATING_STR_SAM_18 = "SAM_18";
    public static final String RATING_STR_TV_14 = "US_TV_14";
    public static final String RATING_STR_TV_G = "US_TV_G";
    public static final String RATING_STR_TV_MA = "US_TV_MA";
    public static final String RATING_STR_TV_NONE = "US_TV_NONE";
    public static final String RATING_STR_TV_PG = "US_TV_PG";
    public static final String RATING_STR_TV_Y = "US_TV_Y";
    public static final String RATING_STR_TV_Y7 = "US_TV_Y7";
    public static final String RATING_STR_U = "U";
    public static final String RATING_STR_X = "X";
    public static final String RATING_SYS_STR_AR_TV = "AR_TV";
    public static final String RATING_SYS_STR_AU_TV = "AU_TV";
    public static final String RATING_SYS_STR_BR_TV = "BR_TV";
    public static final String RATING_SYS_STR_CA_TV_EN = "CA_TV_EN";
    public static final String RATING_SYS_STR_CA_TV_FR = "CA_TV_FR";
    public static final String RATING_SYS_STR_DVB = "DVB";
    public static final String RATING_SYS_STR_ES_DVB = "ES_DVB";
    public static final String RATING_SYS_STR_FR_DVB = "FR_DVB";
    public static final String RATING_SYS_STR_ISDB = "ISDB";
    public static final String RATING_SYS_STR_KR_TV = "KR_TV";
    public static final String RATING_SYS_STR_NZ_TV = "NZ_TV";
    public static final String RATING_SYS_STR_SG_TV = "SG_TV";
    public static final String RATING_SYS_STR_TH_TV = "TH_TV";
    public static final String RATING_SYS_STR_US_MV = "US_MV";
    public static final String RATING_SYS_STR_US_TV = "US_TV";
    public static final String RATING_SYS_STR_ZA_TV = "ZA_TV";
    public static final int RATING_TYPE_CATV = 4;
    public static final int RATING_TYPE_DVB = 1;
    public static final int RATING_TYPE_ISDB = 0;
    public static final int RATING_TYPE_USMV = 3;
    public static final int RATING_TYPE_USTV = 2;
    public static final String STR_CA_TV_13 = "13";
    public static final String STR_CA_TV_14 = "14";
    public static final String STR_CA_TV_16 = "16";
    public static final String STR_CA_TV_18 = "18";
    public static final String STR_CA_TV_8 = "8";
    public static final String STR_CA_TV_C = "C";
    public static final String STR_CA_TV_C8 = "C8";
    public static final String STR_CA_TV_E = "E";
    public static final String STR_CA_TV_EXEMPT = "EXEMPT";
    public static final String STR_CA_TV_G = "G";
    public static final String STR_CA_TV_PG = "PG";
    public static final String STR_MPAA_G = "G";
    public static final String STR_MPAA_NA = "NA";
    public static final String STR_MPAA_NC_17 = "NC17";
    public static final String STR_MPAA_NR = "NR";
    public static final String STR_MPAA_PG = "PG";
    public static final String STR_MPAA_PG13 = "PG13";
    public static final String STR_MPAA_R = "R";
    public static final String STR_MPAA_X = "X";
    public static final String SUB_RATING_STR_A = "A";
    public static final String SUB_RATING_STR_D = "D";
    public static final String SUB_RATING_STR_FV = "FV";
    public static final String SUB_RATING_STR_L = "L";
    public static final String SUB_RATING_STR_S = "S";
    public static final String SUB_RATING_STR_V = "V";
    private static final String TAG = "MtkTvRatingConvert2Goo";
    public static final int USTV_AGE_14 = 5;
    public static final int USTV_AGE_G = 3;
    public static final int USTV_AGE_MA = 6;
    public static final int USTV_AGE_PG = 4;
    public static final int USTV_AGE_Y = 1;
    public static final int USTV_AGE_Y7 = 2;
    public static final int USTV_CONTENT_A = 1;
    public static final int USTV_CONTENT_D = 2;
    public static final int USTV_CONTENT_FV = 6;
    public static final int USTV_CONTENT_L = 3;
    public static final int USTV_CONTENT_S = 4;
    public static final int USTV_CONTENT_V = 5;
    public static final int USTV_MPAA = 7;
    public static final int USTV_MPAA_G = 0;
    public static final int USTV_MPAA_NC_17 = 4;
    public static final int USTV_MPAA_PG = 1;
    public static final int USTV_MPAA_PG13 = 2;
    public static final int USTV_MPAA_R = 3;
    public static final int USTV_MPAA_X = 5;
    public static final String dvb_country_code_esp = "ESP";
    private static final String mDomain = "com.android.tv";
    public static final String t_dvb_country_code_arg = "ARG";
    public static final String t_dvb_country_code_aus = "AUS";
    public static final String t_dvb_country_code_bra = "BRA";
    public static final String t_dvb_country_code_chl = "CHL";
    public static final String t_dvb_country_code_fra = "FRA";
    public static final String t_dvb_country_code_nz = "NZL";
    public static final String t_dvb_country_code_sgp = "SGP";
    public static final String t_dvb_country_code_tha = "THA";
    public static final String t_dvb_country_code_zaf = "ZAF";
    private Map<String, Integer> mAgeMap;
    private String mRating;
    private String mRatingSystem;
    private String[] mSubRating;

    private int convertARTV2TCRFormat() {
        return 0;
    }

    private int convertAUTV2TCRFormat() {
        return 0;
    }

    private int convertDVB2TCRFormat() {
        return 0;
    }

    private int convertESDVB2TCRFormat() {
        return 0;
    }

    private int convertFRDVB2TCRFormat() {
        return 0;
    }

    private int convertISDB2TCRFormat() {
        return 0;
    }

    private int convertKRTV2TCRFormat() {
        return 0;
    }

    private int convertSGTV2TCRFormat() {
        return 0;
    }

    private int convertUSMV2TCRFormat() {
        return 0;
    }

    public String getDomain() {
        return mDomain;
    }

    private void initAgeMap() {
        this.mAgeMap.put("AR_TV_ATP", 1);
        this.mAgeMap.put("AR_TV_SAM_13", 2);
        this.mAgeMap.put("AR_TV_SAM_16", 3);
        this.mAgeMap.put("AR_TV_SAM_18", 4);
        this.mAgeMap.put("AR_TV_NC_18", 5);
        this.mAgeMap.put("BR_TV_L", 1);
        this.mAgeMap.put("BR_TV_10", 2);
        this.mAgeMap.put("BR_TV_12", 3);
        this.mAgeMap.put("BR_TV_14", 4);
        this.mAgeMap.put("BR_TV_16", 5);
        this.mAgeMap.put("BR_TV_18", 6);
        this.mAgeMap.put("BR_TV_D", 1);
        this.mAgeMap.put("BR_TV_V", 2);
        this.mAgeMap.put("BR_TV_S", 4);
        this.mAgeMap.put("BR_TV_D,BR_TV_V", 3);
        this.mAgeMap.put("BR_TV_D,BR_TV_S", 5);
        this.mAgeMap.put("BR_TV_V,BR_TV_S", 6);
        this.mAgeMap.put("BR_TV_D,BR_TV_S,BR_TV_V", 7);
        this.mAgeMap.put("ISDB_4", 4);
        this.mAgeMap.put("ISDB_5", 5);
        this.mAgeMap.put("ISDB_6", 6);
        this.mAgeMap.put("ISDB_7", 7);
        this.mAgeMap.put("ISDB_8", 8);
        this.mAgeMap.put("ISDB_9", 9);
        this.mAgeMap.put("ISDB_10", 10);
        this.mAgeMap.put("ISDB_11", 11);
        this.mAgeMap.put("ISDB_12", 12);
        this.mAgeMap.put("ISDB_13", 13);
        this.mAgeMap.put("ISDB_14", 14);
        this.mAgeMap.put("ISDB_15", 15);
        this.mAgeMap.put("ISDB_16", 16);
        this.mAgeMap.put("ISDB_17", 17);
        this.mAgeMap.put("ISDB_18", 18);
        this.mAgeMap.put("ISDB_19", 19);
        this.mAgeMap.put("ISDB_20", 20);
        this.mAgeMap.put("FR_DVB_U", 1);
        this.mAgeMap.put("FR_DVB_4", 4);
        this.mAgeMap.put("FR_DVB_5", 5);
        this.mAgeMap.put("FR_DVB_6", 6);
        this.mAgeMap.put("FR_DVB_7", 7);
        this.mAgeMap.put("FR_DVB_8", 8);
        this.mAgeMap.put("FR_DVB_9", 9);
        this.mAgeMap.put("FR_DVB_10", 10);
        this.mAgeMap.put("FR_DVB_11", 11);
        this.mAgeMap.put("FR_DVB_12", 12);
        this.mAgeMap.put("FR_DVB_13", 13);
        this.mAgeMap.put("FR_DVB_14", 14);
        this.mAgeMap.put("FR_DVB_15", 15);
        this.mAgeMap.put("FR_DVB_16", 16);
        this.mAgeMap.put("FR_DVB_17", 17);
        this.mAgeMap.put("FR_DVB_18", 18);
        this.mAgeMap.put("ES_DVB_ALL", 29);
        this.mAgeMap.put("ES_DVB_C", 30);
        this.mAgeMap.put("ES_DVB_X", 31);
        this.mAgeMap.put("ES_DVB_4", 4);
        this.mAgeMap.put("ES_DVB_5", 5);
        this.mAgeMap.put("ES_DVB_6", 6);
        this.mAgeMap.put("ES_DVB_7", 7);
        this.mAgeMap.put("ES_DVB_8", 8);
        this.mAgeMap.put("ES_DVB_9", 9);
        this.mAgeMap.put("ES_DVB_10", 10);
        this.mAgeMap.put("ES_DVB_11", 11);
        this.mAgeMap.put("ES_DVB_12", 12);
        this.mAgeMap.put("ES_DVB_13", 13);
        this.mAgeMap.put("ES_DVB_14", 14);
        this.mAgeMap.put("ES_DVB_15", 15);
        this.mAgeMap.put("ES_DVB_16", 16);
        this.mAgeMap.put("ES_DVB_17", 17);
        this.mAgeMap.put("ES_DVB_18", 18);
        this.mAgeMap.put("AU_TV_P", 5);
        this.mAgeMap.put("AU_TV_C", 7);
        this.mAgeMap.put("AU_TV_G", 9);
        this.mAgeMap.put("AU_TV_PG", 11);
        this.mAgeMap.put("AU_TV_M", 13);
        this.mAgeMap.put("AU_TV_MA", 15);
        this.mAgeMap.put("AU_TV_AV", 17);
        this.mAgeMap.put("AU_TV_R", 18);
        this.mAgeMap.put("AU_TV_ALL", 1);
        this.mAgeMap.put("SG_TV_G", 4);
        this.mAgeMap.put("SG_TV_PG", 5);
        this.mAgeMap.put("SG_TV_PG13", 8);
        this.mAgeMap.put("SG_TV_NC16", 14);
        this.mAgeMap.put("SG_TV_M18", 17);
        this.mAgeMap.put("SG_TV_R21", 19);
        this.mAgeMap.put("NZ_TV_G", 4);
        this.mAgeMap.put("NZ_TV_PGR", 10);
        this.mAgeMap.put("NZ_TV_AO", 12);
        this.mAgeMap.put("ZA_TV_PG10", 4);
        this.mAgeMap.put("ZA_TV_10", 6);
        this.mAgeMap.put("ZA_TV_PG13", 7);
        this.mAgeMap.put("ZA_TV_13", 10);
        this.mAgeMap.put("ZA_TV_16", 11);
        this.mAgeMap.put("ZA_TV_18", 14);
        this.mAgeMap.put("ZA_TV_R18", 17);
        this.mAgeMap.put("TH_TV_P", 4);
        this.mAgeMap.put("TH_TV_C", 6);
        this.mAgeMap.put("TH_TV_G", 10);
        this.mAgeMap.put("TH_TV_PG13", 13);
        this.mAgeMap.put("TH_TV_PG18", 18);
        this.mAgeMap.put("TH_TV_X", 19);
        this.mAgeMap.put("DVB_3", 3);
        this.mAgeMap.put("DVB_4", 4);
        this.mAgeMap.put("DVB_5", 5);
        this.mAgeMap.put("DVB_6", 6);
        this.mAgeMap.put("DVB_7", 7);
        this.mAgeMap.put("DVB_8", 8);
        this.mAgeMap.put("DVB_9", 9);
        this.mAgeMap.put("DVB_10", 10);
        this.mAgeMap.put("DVB_11", 11);
        this.mAgeMap.put("DVB_12", 12);
        this.mAgeMap.put("DVB_13", 13);
        this.mAgeMap.put("DVB_14", 14);
        this.mAgeMap.put("DVB_15", 15);
        this.mAgeMap.put("DVB_16", 16);
        this.mAgeMap.put("DVB_17", 17);
        this.mAgeMap.put("DVB_18", 18);
        this.mAgeMap.put("DVB_19", 19);
        this.mAgeMap.put("DVB_NONE", 0);
        this.mAgeMap.put(RATING_STR_TV_Y, 1);
        this.mAgeMap.put(RATING_STR_TV_Y7, 2);
        this.mAgeMap.put(RATING_STR_TV_G, 3);
        this.mAgeMap.put(RATING_STR_TV_PG, 4);
        this.mAgeMap.put(RATING_STR_TV_14, 5);
        this.mAgeMap.put(RATING_STR_TV_MA, 6);
        this.mAgeMap.put(RATING_SYS_STR_US_MV, 7);
        this.mAgeMap.put("US_TV_A", 1);
        this.mAgeMap.put("US_TV_D", 2);
        this.mAgeMap.put("US_TV_L", 3);
        this.mAgeMap.put("US_TV_S", 4);
        this.mAgeMap.put("US_TV_V", 5);
        this.mAgeMap.put("US_TV_FV", 6);
        this.mAgeMap.put("US_MV_G", 0);
        this.mAgeMap.put("US_MV_PG", 1);
        this.mAgeMap.put("US_MV_PG13", 2);
        this.mAgeMap.put("US_MV_R", 3);
        this.mAgeMap.put("US_MV_NC17", 4);
        this.mAgeMap.put("US_MV_X", 5);
        this.mAgeMap.put(RATING_SYS_STR_CA_TV_EN, 0);
        this.mAgeMap.put(RATING_SYS_STR_CA_TV_FR, 1);
        this.mAgeMap.put("CA_TV_EN_EXEMPT", 0);
        this.mAgeMap.put("CA_TV_EN_C", 1);
        this.mAgeMap.put("CA_TV_EN_C8", 2);
        this.mAgeMap.put("CA_TV_EN_G", 3);
        this.mAgeMap.put("CA_TV_EN_PG", 4);
        this.mAgeMap.put("CA_TV_EN_14", 5);
        this.mAgeMap.put("CA_TV_EN_18", 6);
        this.mAgeMap.put("CA_TV_FR_E", 0);
        this.mAgeMap.put("CA_TV_FR_G", 1);
        this.mAgeMap.put("CA_TV_FR_8", 2);
        this.mAgeMap.put("CA_TV_FR_13", 3);
        this.mAgeMap.put("CA_TV_FR_16", 4);
        this.mAgeMap.put("CA_TV_FR_18", 5);
    }

    public MtkTvRatingConvert2Goo() {
        this.mAgeMap = new HashMap();
        this.mRatingSystem = "";
        this.mRating = "";
        this.mSubRating = null;
        initAgeMap();
    }

    public MtkTvRatingConvert2Goo(String str, String str2, String str3, List<String> list) {
        this.mAgeMap = new HashMap();
        this.mRatingSystem = str2;
        this.mRating = str3;
        if (list != null && list.size() > 0) {
            this.mSubRating = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                this.mSubRating[i] = list.get(i);
            }
        } else {
            this.mSubRating = null;
        }
        initAgeMap();
    }

    public void setRatingSystem(String str) {
        this.mRatingSystem = str;
    }

    public String getRatingSystem() {
        return this.mRatingSystem;
    }

    public void setRating(String str) {
        this.mRating = str;
    }

    public String getRating() {
        return this.mRating;
    }

    public void setSubRating(String str) {
        this.mSubRating = str.split(DELIMITER);
    }

    public void setSubRating(List<String> list) {
        if (list != null && list.size() > 0) {
            this.mSubRating = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                this.mSubRating[i] = list.get(i);
            }
            return;
        }
        this.mSubRating = null;
    }

    public void mergeSubRating(List<String> list) {
        boolean z;
        if (list == null || list.size() == 0) {
            return;
        }
        ArrayList arrayList = new ArrayList(list);
        String[] strArr = this.mSubRating;
        if (strArr != null && strArr.length > 0) {
            for (int i = 0; i < this.mSubRating.length; i++) {
                int i2 = 0;
                while (true) {
                    if (i2 >= arrayList.size()) {
                        z = false;
                        break;
                    } else {
                        if (this.mSubRating[i].equals(arrayList.get(i2))) {
                            z = true;
                            break;
                        }
                        i2++;
                    }
                }
                if (!z) {
                    arrayList.add(this.mSubRating[i]);
                }
            }
        }
        setSubRating(arrayList);
    }

    public String[] getSubRating() {
        return this.mSubRating;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(mDomain);
        sb.append("]");
        sb.append("[");
        sb.append(this.mRatingSystem);
        sb.append("]");
        sb.append("[");
        sb.append(this.mRating);
        sb.append("]");
        String[] strArr = this.mSubRating;
        if (strArr != null && strArr.length > 0) {
            int i = 0;
            while (true) {
                String[] strArr2 = this.mSubRating;
                if (i >= strArr2.length) {
                    break;
                }
                String str = strArr2[i];
                sb.append("[");
                sb.append(str);
                sb.append("]");
                i++;
            }
        }
        return sb.toString();
    }

    public static int getCurrentRating(MtkTvRatingConvert2Goo mtkTvRatingConvert2Goo) {
        TVNativeWrapper.getCrntRatingInfo_native(mtkTvRatingConvert2Goo);
        return mtkTvRatingConvert2Goo.convert2TCRFormat();
    }

    public int createSpecialRating4ChLock() {
        setRatingSystem(RATING_SYS_STR_DVB);
        setRating(RATING_STR_CH_LOCK);
        return 0;
    }

    public int createSpecialRating4InputLock() {
        setRatingSystem(RATING_SYS_STR_DVB);
        setRating(RATING_STR_INPUT_LOCK);
        return 0;
    }

    public int createSpecialRatingByBlockedStatus(int i) {
        Log.d(TAG, "createSpecialRatingByBlockedStatus:" + i);
        if ((i & 1) != 0) {
            return 0;
        }
        if ((i & 2) != 0) {
            createSpecialRating4ChLock();
            if ((i & 65536) == 0) {
                return 0;
            }
            setSubRating("M7_18AGE");
            return 0;
        }
        if ((i & 3) != 0) {
            createSpecialRating4InputLock();
            return 0;
        }
        Log.d(TAG, "blocked status invalid, default set as channel lock!");
        createSpecialRating4ChLock();
        return 0;
    }

    public int getRatingString2Age() {
        Log.d(TAG, "getRatingString2Age: mRating=" + this.mRating + "\n");
        String str = this.mRating;
        if (str == null || str.length() == 0) {
            return 0;
        }
        Integer num = this.mAgeMap.get(this.mRating);
        if (num != null) {
            return num.intValue();
        }
        return 255;
    }

    public int getRatingString2Age(String str) {
        Integer num;
        Log.d(TAG, "getRatingString2Age: strAge=[" + str + "]\n");
        if (str == null || str.length() == 0 || (num = this.mAgeMap.get(str)) == null) {
            return 255;
        }
        return num.intValue();
    }

    private int convertBRTV2TCRFormat() {
        if (this.mRatingSystem.equals(RATING_SYS_STR_BR_TV)) {
            int ratingString2Age = getRatingString2Age(this.mRating);
            if (ratingString2Age >= 1 && ratingString2Age <= 6) {
                String[] strArr = this.mSubRating;
                if (strArr == null || strArr.length == 0) {
                    Log.d(TAG, "convertUSTV2TCRFormat: ageRating=" + ratingString2Age + "\n");
                    this.mSubRating = new String[]{"BR_TV_A", "BR_TV_D", "BR_TV_S", "BR_TV_V"};
                }
                Log.d(TAG, "convertUSTV2TCRFormat: Convert done.\n");
            } else {
                Log.d(TAG, "convertUSTV2TCRFormat: ERROR Invalid rating age.\n");
            }
        } else {
            Log.d(TAG, "convertUSTV2TCRFormat: ERROR Invalid BR-TV Rating system.\n");
        }
        return 0;
    }

    private int convertUSTV2TCRFormat() {
        if (this.mRatingSystem.equals(RATING_SYS_STR_US_TV)) {
            int ratingString2Age = getRatingString2Age(this.mRating);
            if (ratingString2Age == 1) {
                Log.d(TAG, "convertUSTV2TCRFormat: USTV_AGE_Y.\n");
                this.mSubRating = new String[]{"US_TV_A"};
            } else if (ratingString2Age == 2) {
                String[] strArr = this.mSubRating;
                if (strArr == null || strArr.length == 0) {
                    Log.d(TAG, "convertUSTV2TCRFormat: USTV_AGE_Y7 A.\n");
                    this.mSubRating = new String[]{"US_TV_A", "US_TV_FV"};
                }
            } else if (ratingString2Age == 3) {
                Log.d(TAG, "convertUSTV2TCRFormat: USTV_AGE_G.\n");
                this.mSubRating = new String[]{"US_TV_A"};
            } else if (ratingString2Age == 4) {
                String[] strArr2 = this.mSubRating;
                if (strArr2 == null || strArr2.length == 0) {
                    Log.d(TAG, "convertUSTV2TCRFormat: USTV_AGE_PG A.\n");
                    this.mSubRating = new String[]{"US_TV_A", "US_TV_D", "US_TV_L", "US_TV_S", "US_TV_V"};
                }
            } else if (ratingString2Age == 5) {
                String[] strArr3 = this.mSubRating;
                if (strArr3 == null || strArr3.length == 0) {
                    Log.d(TAG, "convertUSTV2TCRFormat: USTV_AGE_14 A.\n");
                    this.mSubRating = new String[]{"US_TV_A", "US_TV_D", "US_TV_L", "US_TV_S", "US_TV_V"};
                }
            } else if (ratingString2Age == 6) {
                String[] strArr4 = this.mSubRating;
                if (strArr4 == null || strArr4.length == 0) {
                    Log.d(TAG, "convertUSTV2TCRFormat: USTV_AGE_MA A.\n");
                    this.mSubRating = new String[]{"US_TV_A", "US_TV_L", "US_TV_S", "US_TV_V"};
                }
            } else {
                Log.d(TAG, "convertUSTV2TCRFormat: ERROR Invalid rating age.\n");
            }
        } else {
            Log.d(TAG, "convertUSTV2TCRFormat: ERROR Invalid US-TV Rating system.\n");
        }
        return 0;
    }

    private int convertCATV2TCRFormat() {
        if (getRatingType(this.mRatingSystem) != 4) {
            return 0;
        }
        getRatingString2Age(this.mRating);
        if (this.mRatingSystem.equals(RATING_SYS_STR_CA_TV_EN)) {
            Log.d(TAG, "convertUSTV2TCRFormat: CA_TV_ENG.\n");
            return 0;
        }
        if (this.mRatingSystem.equals(RATING_SYS_STR_CA_TV_FR)) {
            Log.d(TAG, "convertUSTV2TCRFormat: CA_TV_FRA.\n");
            return 0;
        }
        Log.d(TAG, "convertUSTV2TCRFormat: ERROR Invalid rating age.\n");
        return 0;
    }

    private int convert2TCRFormat() {
        String str = this.mRatingSystem;
        if (str == null || str.length() == 0) {
            Log.d(TAG, "convert2TCRFormat: Warning!! Invalid rating system\n");
        } else {
            if (this.mRatingSystem.equals(RATING_SYS_STR_AR_TV)) {
                int iConvertARTV2TCRFormat = convertARTV2TCRFormat();
                Log.d(TAG, "convert2TCRFormat: " + toString());
                return iConvertARTV2TCRFormat;
            }
            if (this.mRatingSystem.equals(RATING_SYS_STR_AU_TV)) {
                int iConvertAUTV2TCRFormat = convertAUTV2TCRFormat();
                Log.d(TAG, "convert2TCRFormat: " + toString());
                return iConvertAUTV2TCRFormat;
            }
            if (this.mRatingSystem.equals(RATING_SYS_STR_BR_TV)) {
                int iConvertBRTV2TCRFormat = convertBRTV2TCRFormat();
                Log.d(TAG, "convert2TCRFormat: " + toString());
                return iConvertBRTV2TCRFormat;
            }
            if (this.mRatingSystem.equals(RATING_SYS_STR_DVB)) {
                int iConvertDVB2TCRFormat = convertDVB2TCRFormat();
                Log.d(TAG, "convert2TCRFormat: " + toString());
                return iConvertDVB2TCRFormat;
            }
            if (this.mRatingSystem.equals(RATING_SYS_STR_ES_DVB)) {
                int iConvertESDVB2TCRFormat = convertESDVB2TCRFormat();
                Log.d(TAG, "convert2TCRFormat: " + toString());
                return iConvertESDVB2TCRFormat;
            }
            if (this.mRatingSystem.equals(RATING_SYS_STR_FR_DVB)) {
                int iConvertFRDVB2TCRFormat = convertFRDVB2TCRFormat();
                Log.d(TAG, "convert2TCRFormat: " + toString());
                return iConvertFRDVB2TCRFormat;
            }
            if (this.mRatingSystem.equals(RATING_SYS_STR_ISDB)) {
                int iConvertISDB2TCRFormat = convertISDB2TCRFormat();
                Log.d(TAG, "convert2TCRFormat: " + toString());
                return iConvertISDB2TCRFormat;
            }
            if (this.mRatingSystem.equals(RATING_SYS_STR_KR_TV)) {
                int iConvertKRTV2TCRFormat = convertKRTV2TCRFormat();
                Log.d(TAG, "convert2TCRFormat: " + toString());
                return iConvertKRTV2TCRFormat;
            }
            if (this.mRatingSystem.equals(RATING_SYS_STR_SG_TV)) {
                int iConvertSGTV2TCRFormat = convertSGTV2TCRFormat();
                Log.d(TAG, "convert2TCRFormat: " + toString());
                return iConvertSGTV2TCRFormat;
            }
            if (this.mRatingSystem.equals(RATING_SYS_STR_US_TV)) {
                int iConvertUSTV2TCRFormat = convertUSTV2TCRFormat();
                Log.d(TAG, "convert2TCRFormat: " + toString());
                return iConvertUSTV2TCRFormat;
            }
            if (this.mRatingSystem.equals(RATING_SYS_STR_US_MV)) {
                int iConvertUSMV2TCRFormat = convertUSMV2TCRFormat();
                Log.d(TAG, "convert2TCRFormat: " + toString());
                return iConvertUSMV2TCRFormat;
            }
            if (getRatingType(this.mRatingSystem) == 4) {
                int iConvertCATV2TCRFormat = convertCATV2TCRFormat();
                Log.d(TAG, "convert2TCRFormat: " + toString());
                return iConvertCATV2TCRFormat;
            }
            Log.d(TAG, "convert2TCRFormat: \n");
        }
        return 0;
    }

    public int convert2USTVRatingInfo(MtkTvUSTvRatingSettingInfoBase mtkTvUSTvRatingSettingInfoBase, boolean z) {
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        if (this.mRatingSystem.equals(RATING_SYS_STR_US_TV)) {
            int ratingString2Age = getRatingString2Age(this.mRating);
            if (z) {
                if (ratingString2Age == 1) {
                    Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_Y.\n");
                    mtkTvUSTvRatingSettingInfoBase.setUsAgeTvYBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsAgeTvY7Block(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvY7FVBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsAgeTvGBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsAgeTvPGBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGDBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGLBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGSBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGVBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsAgeTv14Block(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTv14DBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTv14LBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTv14SBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTv14VBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsAgeTvMABlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvMALBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvMASBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvMAVBlock(z);
                } else if (ratingString2Age == 2) {
                    Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_Y7.\n");
                    String[] strArr = this.mSubRating;
                    if (strArr != null && strArr.length > 0) {
                        int i = 0;
                        while (true) {
                            String[] strArr2 = this.mSubRating;
                            if (i >= strArr2.length) {
                                z5 = false;
                                break;
                            }
                            String str = strArr2[i];
                            if (str.equals("US_TV_A")) {
                                z5 = true;
                                break;
                            }
                            if (str.equals("US_TV_FV")) {
                                Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_Y7 FV.\n");
                                mtkTvUSTvRatingSettingInfoBase.setUsCntTvY7FVBlock(z);
                            } else {
                                Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_Y7 Unknow Sub-Rating.\n");
                            }
                            i++;
                        }
                    } else {
                        z5 = false;
                        break;
                    }
                    if (this.mSubRating == null || z5) {
                        Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_Y7 A.\n");
                        mtkTvUSTvRatingSettingInfoBase.setUsAgeTvY7Block(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvY7FVBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsAgeTvGBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsAgeTvPGBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGDBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGLBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGSBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGVBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsAgeTv14Block(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTv14DBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTv14LBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTv14SBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTv14VBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsAgeTvMABlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvMALBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvMASBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvMAVBlock(z);
                    }
                } else if (ratingString2Age == 3) {
                    Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_G.\n");
                    mtkTvUSTvRatingSettingInfoBase.setUsAgeTvGBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsAgeTvPGBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGDBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGLBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGSBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGVBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsAgeTv14Block(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTv14DBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTv14LBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTv14SBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTv14VBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsAgeTvMABlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvMALBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvMASBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvMAVBlock(z);
                } else if (ratingString2Age == 4) {
                    Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_PG.\n");
                    String[] strArr3 = this.mSubRating;
                    if (strArr3 != null && strArr3.length > 0) {
                        int i2 = 0;
                        while (true) {
                            String[] strArr4 = this.mSubRating;
                            if (i2 >= strArr4.length) {
                                z4 = false;
                                break;
                            }
                            String str2 = strArr4[i2];
                            if (str2.equals("US_TV_A")) {
                                z4 = true;
                                break;
                            }
                            if (str2.equals("US_TV_D")) {
                                Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_PG D.\n");
                                mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGDBlock(z);
                                mtkTvUSTvRatingSettingInfoBase.setUsCntTv14DBlock(z);
                            } else if (str2.equals("US_TV_L")) {
                                Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_PG L.\n");
                                mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGLBlock(z);
                                mtkTvUSTvRatingSettingInfoBase.setUsCntTv14LBlock(z);
                                mtkTvUSTvRatingSettingInfoBase.setUsCntTvMALBlock(z);
                            } else if (str2.equals("US_TV_S")) {
                                Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_PG S.\n");
                                mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGSBlock(z);
                                mtkTvUSTvRatingSettingInfoBase.setUsCntTv14SBlock(z);
                                mtkTvUSTvRatingSettingInfoBase.setUsCntTvMASBlock(z);
                            } else if (str2.equals("US_TV_V")) {
                                Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_PG V.\n");
                                mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGVBlock(z);
                                mtkTvUSTvRatingSettingInfoBase.setUsCntTv14VBlock(z);
                                mtkTvUSTvRatingSettingInfoBase.setUsCntTvMAVBlock(z);
                            } else {
                                Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_PG Unknow Sub-Rating.\n");
                            }
                            i2++;
                        }
                    } else {
                        z4 = false;
                        break;
                    }
                    if (this.mSubRating == null || z4) {
                        Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_PG A.\n");
                        mtkTvUSTvRatingSettingInfoBase.setUsAgeTvPGBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGDBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGLBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGSBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGVBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsAgeTv14Block(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTv14DBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTv14LBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTv14SBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTv14VBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsAgeTvMABlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvMALBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvMASBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvMAVBlock(z);
                    }
                } else if (ratingString2Age == 5) {
                    Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_14.\n");
                    String[] strArr5 = this.mSubRating;
                    if (strArr5 != null && strArr5.length > 0) {
                        int i3 = 0;
                        while (true) {
                            String[] strArr6 = this.mSubRating;
                            if (i3 >= strArr6.length) {
                                z3 = false;
                                break;
                            }
                            String str3 = strArr6[i3];
                            if (str3.equals("US_TV_A")) {
                                z3 = true;
                                break;
                            }
                            if (str3.equals("US_TV_D")) {
                                Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_14 D.\n");
                                mtkTvUSTvRatingSettingInfoBase.setUsCntTv14DBlock(z);
                            } else if (str3.equals("US_TV_L")) {
                                Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_14 L.\n");
                                mtkTvUSTvRatingSettingInfoBase.setUsCntTv14LBlock(z);
                                mtkTvUSTvRatingSettingInfoBase.setUsCntTvMALBlock(z);
                            } else if (str3.equals("US_TV_S")) {
                                Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_14 S.\n");
                                mtkTvUSTvRatingSettingInfoBase.setUsCntTv14SBlock(z);
                                mtkTvUSTvRatingSettingInfoBase.setUsCntTvMASBlock(z);
                            } else if (str3.equals("US_TV_V")) {
                                Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_14 V.\n");
                                mtkTvUSTvRatingSettingInfoBase.setUsCntTv14VBlock(z);
                                mtkTvUSTvRatingSettingInfoBase.setUsCntTvMAVBlock(z);
                            } else {
                                Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_14 Unknow Sub-Rating.\n");
                            }
                            i3++;
                        }
                    } else {
                        z3 = false;
                        break;
                    }
                    if (this.mSubRating == null || z3) {
                        Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_14 A.\n");
                        mtkTvUSTvRatingSettingInfoBase.setUsAgeTv14Block(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTv14DBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTv14LBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTv14SBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTv14VBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsAgeTvMABlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvMALBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvMASBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvMAVBlock(z);
                    }
                } else if (ratingString2Age == 6) {
                    Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_MA\n");
                    String[] strArr7 = this.mSubRating;
                    if (strArr7 != null && strArr7.length > 0) {
                        int i4 = 0;
                        while (true) {
                            String[] strArr8 = this.mSubRating;
                            if (i4 >= strArr8.length) {
                                z2 = false;
                                break;
                            }
                            String str4 = strArr8[i4];
                            if (str4.equals("US_TV_A")) {
                                z2 = true;
                                break;
                            }
                            if (str4.equals("US_TV_L")) {
                                Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_MA L.\n");
                                mtkTvUSTvRatingSettingInfoBase.setUsCntTvMALBlock(z);
                            } else if (str4.equals("US_TV_S")) {
                                Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_MA S.\n");
                                mtkTvUSTvRatingSettingInfoBase.setUsCntTvMASBlock(z);
                            } else if (str4.equals("US_TV_V")) {
                                Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_MA V.\n");
                                mtkTvUSTvRatingSettingInfoBase.setUsCntTvMAVBlock(z);
                            } else {
                                Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_MA Unknow Sub-Rating.\n");
                            }
                            i4++;
                        }
                    } else {
                        z2 = false;
                        break;
                    }
                    if (this.mSubRating == null || z2) {
                        Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_MA A.\n");
                        mtkTvUSTvRatingSettingInfoBase.setUsAgeTvMABlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvMALBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvMASBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvMAVBlock(z);
                    }
                } else {
                    Log.d(TAG, "convert2USTVRatingInfo: ERROR Invalid rating age.\n");
                }
            } else if (ratingString2Age == 1) {
                Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_Y.\n");
                mtkTvUSTvRatingSettingInfoBase.setUsAgeTvYBlock(z);
            } else if (ratingString2Age == 2) {
                Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_Y7.\n");
                String[] strArr9 = this.mSubRating;
                if (strArr9 != null && strArr9.length > 0) {
                    int i5 = 0;
                    while (true) {
                        String[] strArr10 = this.mSubRating;
                        if (i5 >= strArr10.length) {
                            break;
                        }
                        if (strArr10[i5].equals("US_TV_FV")) {
                            Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_Y7 FV.\n");
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTvY7FVBlock(z);
                        } else {
                            Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_Y7 Unknow Sub-Rating.\n");
                        }
                        i5++;
                    }
                } else {
                    mtkTvUSTvRatingSettingInfoBase.setUsAgeTvY7Block(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvY7FVBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsAgeTvYBlock(z);
                }
            } else if (ratingString2Age == 3) {
                Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_G.\n");
                mtkTvUSTvRatingSettingInfoBase.setUsAgeTvYBlock(z);
                mtkTvUSTvRatingSettingInfoBase.setUsAgeTvY7Block(z);
                mtkTvUSTvRatingSettingInfoBase.setUsCntTvY7FVBlock(z);
                mtkTvUSTvRatingSettingInfoBase.setUsAgeTvGBlock(z);
            } else if (ratingString2Age == 4) {
                Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_PG.\n");
                String[] strArr11 = this.mSubRating;
                if (strArr11 != null && strArr11.length > 0) {
                    int i6 = 0;
                    while (true) {
                        String[] strArr12 = this.mSubRating;
                        if (i6 >= strArr12.length) {
                            break;
                        }
                        String str5 = strArr12[i6];
                        if (str5.equals("US_TV_D")) {
                            Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_PG D.\n");
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGDBlock(z);
                        } else if (str5.equals("US_TV_L")) {
                            Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_PG L.\n");
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGLBlock(z);
                        } else if (str5.equals("US_TV_S")) {
                            Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_PG S.\n");
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGSBlock(z);
                        } else if (str5.equals("US_TV_V")) {
                            Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_PG V.\n");
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGVBlock(z);
                        } else {
                            Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_PG Unknow Sub-Rating.\n");
                        }
                        i6++;
                    }
                } else {
                    mtkTvUSTvRatingSettingInfoBase.setUsAgeTvYBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsAgeTvY7Block(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvY7FVBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsAgeTvGBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsAgeTvPGBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGDBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGLBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGSBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGVBlock(z);
                }
            } else if (ratingString2Age == 5) {
                Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_14.\n");
                String[] strArr13 = this.mSubRating;
                if (strArr13 != null && strArr13.length > 0) {
                    int i7 = 0;
                    while (true) {
                        String[] strArr14 = this.mSubRating;
                        if (i7 >= strArr14.length) {
                            break;
                        }
                        String str6 = strArr14[i7];
                        if (str6.equals("US_TV_D")) {
                            Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_14 D.\n");
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGDBlock(z);
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTv14DBlock(z);
                        } else if (str6.equals("US_TV_L")) {
                            Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_14 L.\n");
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGLBlock(z);
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTv14LBlock(z);
                        } else if (str6.equals("US_TV_S")) {
                            Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_14 S.\n");
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGSBlock(z);
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTv14SBlock(z);
                        } else if (str6.equals("US_TV_V")) {
                            Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_14 V.\n");
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGVBlock(z);
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTv14VBlock(z);
                        } else {
                            Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_14 Unknow Sub-Rating.\n");
                        }
                        i7++;
                    }
                } else {
                    mtkTvUSTvRatingSettingInfoBase.setUsAgeTvYBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsAgeTvY7Block(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvY7FVBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsAgeTvGBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsAgeTvPGBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGDBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGLBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGSBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGVBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsAgeTv14Block(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTv14DBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTv14LBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTv14SBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTv14VBlock(z);
                }
            } else if (ratingString2Age == 6) {
                Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_MA.\n");
                String[] strArr15 = this.mSubRating;
                if (strArr15 != null && strArr15.length > 0) {
                    int i8 = 0;
                    while (true) {
                        String[] strArr16 = this.mSubRating;
                        if (i8 >= strArr16.length) {
                            break;
                        }
                        String str7 = strArr16[i8];
                        if (str7.equals("US_TV_L")) {
                            Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_MA L.\n");
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGLBlock(z);
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTv14LBlock(z);
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTvMALBlock(z);
                        } else if (str7.equals("US_TV_S")) {
                            Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_MA S.\n");
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGSBlock(z);
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTv14SBlock(z);
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTvMASBlock(z);
                        } else if (str7.equals("US_TV_V")) {
                            Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_MA V.\n");
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGVBlock(z);
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTv14VBlock(z);
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTvMAVBlock(z);
                        } else {
                            Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_MA Unknow Sub-Rating.\n");
                        }
                        i8++;
                    }
                } else {
                    mtkTvUSTvRatingSettingInfoBase.setUsAgeTvYBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsAgeTvY7Block(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvY7FVBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsAgeTvGBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsAgeTvPGBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGDBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGLBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGSBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGVBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsAgeTv14Block(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTv14DBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTv14LBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTv14SBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTv14VBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsAgeTvMABlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvMALBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvMASBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvMAVBlock(z);
                }
            } else {
                Log.d(TAG, "convert2USTVRatingInfo: ERROR Invalid rating age.\n");
            }
            return 0;
        }
        Log.d(TAG, "convert2USTVRatingInfo: Invalid rating for US-TV.\n");
        return -1;
    }

    public int convert2USTVRatingInfo(MtkTvUSTvRatingSettingInfoBase mtkTvUSTvRatingSettingInfoBase, boolean z, boolean z2) {
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        Log.d(TAG, "convert2USTVRatingInfo(): Enter(ChildSeparate=" + z2 + ")\n");
        if (this.mRatingSystem.equals(RATING_SYS_STR_US_TV)) {
            int ratingString2Age = getRatingString2Age(this.mRating);
            if (z) {
                if (ratingString2Age == 1) {
                    Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_Y.\n");
                    mtkTvUSTvRatingSettingInfoBase.setUsAgeTvYBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsAgeTvY7Block(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvY7FVBlock(z);
                    if (z2) {
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvYBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvY7Block(z);
                    }
                    if (!z2) {
                        mtkTvUSTvRatingSettingInfoBase.setUsAgeTvGBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsAgeTvPGBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGDBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGLBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGSBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGVBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsAgeTv14Block(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTv14DBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTv14LBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTv14SBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTv14VBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsAgeTvMABlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvMALBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvMASBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvMAVBlock(z);
                    }
                } else if (ratingString2Age == 2) {
                    Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_Y7.\n");
                    String[] strArr = this.mSubRating;
                    if (strArr != null && strArr.length > 0) {
                        int i = 0;
                        while (true) {
                            String[] strArr2 = this.mSubRating;
                            if (i >= strArr2.length) {
                                z6 = false;
                                break;
                            }
                            String str = strArr2[i];
                            if (str.equals("US_TV_A")) {
                                z6 = true;
                                break;
                            }
                            if (str.equals("US_TV_FV")) {
                                Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_Y7 FV.\n");
                                mtkTvUSTvRatingSettingInfoBase.setUsCntTvY7FVBlock(z);
                            } else {
                                Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_Y7 Unknow Sub-Rating.\n");
                            }
                            i++;
                        }
                    } else {
                        z6 = false;
                        break;
                    }
                    if (this.mSubRating == null || z6) {
                        Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_Y7 A.\n");
                        mtkTvUSTvRatingSettingInfoBase.setUsAgeTvY7Block(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvY7FVBlock(z);
                        if (z2) {
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTvY7Block(z);
                        }
                        if (!z2) {
                            mtkTvUSTvRatingSettingInfoBase.setUsAgeTvGBlock(z);
                            mtkTvUSTvRatingSettingInfoBase.setUsAgeTvPGBlock(z);
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGDBlock(z);
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGLBlock(z);
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGSBlock(z);
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGVBlock(z);
                            mtkTvUSTvRatingSettingInfoBase.setUsAgeTv14Block(z);
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTv14DBlock(z);
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTv14LBlock(z);
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTv14SBlock(z);
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTv14VBlock(z);
                            mtkTvUSTvRatingSettingInfoBase.setUsAgeTvMABlock(z);
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTvMALBlock(z);
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTvMASBlock(z);
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTvMAVBlock(z);
                        }
                    }
                } else if (ratingString2Age == 3) {
                    Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_G.\n");
                    mtkTvUSTvRatingSettingInfoBase.setUsAgeTvGBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsAgeTvPGBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGDBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGLBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGSBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGVBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsAgeTv14Block(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTv14DBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTv14LBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTv14SBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTv14VBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsAgeTvMABlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvMALBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvMASBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvMAVBlock(z);
                } else if (ratingString2Age == 4) {
                    Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_PG.\n");
                    String[] strArr3 = this.mSubRating;
                    if (strArr3 != null && strArr3.length > 0) {
                        int i2 = 0;
                        while (true) {
                            String[] strArr4 = this.mSubRating;
                            if (i2 >= strArr4.length) {
                                z5 = false;
                                break;
                            }
                            String str2 = strArr4[i2];
                            if (str2.equals("US_TV_A")) {
                                z5 = true;
                                break;
                            }
                            if (str2.equals("US_TV_D")) {
                                Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_PG D.\n");
                                mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGDBlock(z);
                                mtkTvUSTvRatingSettingInfoBase.setUsCntTv14DBlock(z);
                            } else if (str2.equals("US_TV_L")) {
                                Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_PG L.\n");
                                mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGLBlock(z);
                                mtkTvUSTvRatingSettingInfoBase.setUsCntTv14LBlock(z);
                                mtkTvUSTvRatingSettingInfoBase.setUsCntTvMALBlock(z);
                            } else if (str2.equals("US_TV_S")) {
                                Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_PG S.\n");
                                mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGSBlock(z);
                                mtkTvUSTvRatingSettingInfoBase.setUsCntTv14SBlock(z);
                                mtkTvUSTvRatingSettingInfoBase.setUsCntTvMASBlock(z);
                            } else if (str2.equals("US_TV_V")) {
                                Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_PG V.\n");
                                mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGVBlock(z);
                                mtkTvUSTvRatingSettingInfoBase.setUsCntTv14VBlock(z);
                                mtkTvUSTvRatingSettingInfoBase.setUsCntTvMAVBlock(z);
                            } else {
                                Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_PG Unknow Sub-Rating.\n");
                            }
                            i2++;
                        }
                    } else {
                        z5 = false;
                        break;
                    }
                    if (this.mSubRating == null || z5) {
                        Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_PG A.\n");
                        mtkTvUSTvRatingSettingInfoBase.setUsAgeTvPGBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGDBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGLBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGSBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGVBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsAgeTv14Block(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTv14DBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTv14LBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTv14SBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTv14VBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsAgeTvMABlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvMALBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvMASBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvMAVBlock(z);
                    }
                } else if (ratingString2Age == 5) {
                    Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_14.\n");
                    String[] strArr5 = this.mSubRating;
                    if (strArr5 != null && strArr5.length > 0) {
                        int i3 = 0;
                        while (true) {
                            String[] strArr6 = this.mSubRating;
                            if (i3 >= strArr6.length) {
                                z4 = false;
                                break;
                            }
                            String str3 = strArr6[i3];
                            if (str3.equals("US_TV_A")) {
                                z4 = true;
                                break;
                            }
                            if (str3.equals("US_TV_D")) {
                                Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_14 D.\n");
                                mtkTvUSTvRatingSettingInfoBase.setUsCntTv14DBlock(z);
                            } else if (str3.equals("US_TV_L")) {
                                Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_14 L.\n");
                                mtkTvUSTvRatingSettingInfoBase.setUsCntTv14LBlock(z);
                                mtkTvUSTvRatingSettingInfoBase.setUsCntTvMALBlock(z);
                            } else if (str3.equals("US_TV_S")) {
                                Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_14 S.\n");
                                mtkTvUSTvRatingSettingInfoBase.setUsCntTv14SBlock(z);
                                mtkTvUSTvRatingSettingInfoBase.setUsCntTvMASBlock(z);
                            } else if (str3.equals("US_TV_V")) {
                                Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_14 V.\n");
                                mtkTvUSTvRatingSettingInfoBase.setUsCntTv14VBlock(z);
                                mtkTvUSTvRatingSettingInfoBase.setUsCntTvMAVBlock(z);
                            } else {
                                Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_14 Unknow Sub-Rating.\n");
                            }
                            i3++;
                        }
                    } else {
                        z4 = false;
                        break;
                    }
                    if (this.mSubRating == null || z4) {
                        Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_14 A.\n");
                        mtkTvUSTvRatingSettingInfoBase.setUsAgeTv14Block(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTv14DBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTv14LBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTv14SBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTv14VBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsAgeTvMABlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvMALBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvMASBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvMAVBlock(z);
                    }
                } else if (ratingString2Age == 6) {
                    Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_MA\n");
                    String[] strArr7 = this.mSubRating;
                    if (strArr7 != null && strArr7.length > 0) {
                        int i4 = 0;
                        while (true) {
                            String[] strArr8 = this.mSubRating;
                            if (i4 >= strArr8.length) {
                                z3 = false;
                                break;
                            }
                            String str4 = strArr8[i4];
                            if (str4.equals("US_TV_A")) {
                                z3 = true;
                                break;
                            }
                            if (str4.equals("US_TV_L")) {
                                Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_MA L.\n");
                                mtkTvUSTvRatingSettingInfoBase.setUsCntTvMALBlock(z);
                            } else if (str4.equals("US_TV_S")) {
                                Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_MA S.\n");
                                mtkTvUSTvRatingSettingInfoBase.setUsCntTvMASBlock(z);
                            } else if (str4.equals("US_TV_V")) {
                                Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_MA V.\n");
                                mtkTvUSTvRatingSettingInfoBase.setUsCntTvMAVBlock(z);
                            } else {
                                Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_MA Unknow Sub-Rating.\n");
                            }
                            i4++;
                        }
                    } else {
                        z3 = false;
                        break;
                    }
                    if (this.mSubRating == null || z3) {
                        Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_MA A.\n");
                        mtkTvUSTvRatingSettingInfoBase.setUsAgeTvMABlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvMALBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvMASBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvMAVBlock(z);
                    }
                } else {
                    Log.d(TAG, "convert2USTVRatingInfo: ERROR Invalid rating age.\n");
                }
            } else if (ratingString2Age == 1) {
                Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_Y.\n");
                mtkTvUSTvRatingSettingInfoBase.setUsAgeTvYBlock(z);
                if (z2) {
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvYBlock(z);
                }
            } else if (ratingString2Age == 2) {
                Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_Y7.\n");
                String[] strArr9 = this.mSubRating;
                if (strArr9 != null && strArr9.length > 0) {
                    int i5 = 0;
                    while (true) {
                        String[] strArr10 = this.mSubRating;
                        if (i5 >= strArr10.length) {
                            break;
                        }
                        if (strArr10[i5].equals("US_TV_FV")) {
                            Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_Y7 FV.\n");
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTvY7FVBlock(z);
                        } else {
                            Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_Y7 Unknow Sub-Rating.\n");
                        }
                        i5++;
                    }
                } else {
                    mtkTvUSTvRatingSettingInfoBase.setUsAgeTvY7Block(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvY7FVBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsAgeTvYBlock(z);
                    if (z2) {
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvYBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvY7Block(z);
                    }
                }
            } else if (ratingString2Age == 3) {
                Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_G.\n");
                if (!z2) {
                    mtkTvUSTvRatingSettingInfoBase.setUsAgeTvYBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsAgeTvY7Block(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvY7FVBlock(z);
                }
                mtkTvUSTvRatingSettingInfoBase.setUsAgeTvGBlock(z);
            } else if (ratingString2Age == 4) {
                Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_PG.\n");
                String[] strArr11 = this.mSubRating;
                if (strArr11 != null && strArr11.length > 0) {
                    int i6 = 0;
                    while (true) {
                        String[] strArr12 = this.mSubRating;
                        if (i6 >= strArr12.length) {
                            break;
                        }
                        String str5 = strArr12[i6];
                        if (str5.equals("US_TV_D")) {
                            Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_PG D.\n");
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGDBlock(z);
                        } else if (str5.equals("US_TV_L")) {
                            Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_PG L.\n");
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGLBlock(z);
                        } else if (str5.equals("US_TV_S")) {
                            Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_PG S.\n");
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGSBlock(z);
                        } else if (str5.equals("US_TV_V")) {
                            Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_PG V.\n");
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGVBlock(z);
                        } else {
                            Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_PG Unknow Sub-Rating.\n");
                        }
                        i6++;
                    }
                } else {
                    if (!z2) {
                        mtkTvUSTvRatingSettingInfoBase.setUsAgeTvYBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsAgeTvY7Block(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvY7FVBlock(z);
                    }
                    mtkTvUSTvRatingSettingInfoBase.setUsAgeTvGBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsAgeTvPGBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGDBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGLBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGSBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGVBlock(z);
                }
            } else if (ratingString2Age == 5) {
                Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_14.\n");
                String[] strArr13 = this.mSubRating;
                if (strArr13 != null && strArr13.length > 0) {
                    int i7 = 0;
                    while (true) {
                        String[] strArr14 = this.mSubRating;
                        if (i7 >= strArr14.length) {
                            break;
                        }
                        String str6 = strArr14[i7];
                        if (str6.equals("US_TV_D")) {
                            Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_14 D.\n");
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGDBlock(z);
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTv14DBlock(z);
                        } else if (str6.equals("US_TV_L")) {
                            Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_14 L.\n");
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGLBlock(z);
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTv14LBlock(z);
                        } else if (str6.equals("US_TV_S")) {
                            Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_14 S.\n");
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGSBlock(z);
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTv14SBlock(z);
                        } else if (str6.equals("US_TV_V")) {
                            Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_14 V.\n");
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGVBlock(z);
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTv14VBlock(z);
                        } else {
                            Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_14 Unknow Sub-Rating.\n");
                        }
                        i7++;
                    }
                } else {
                    if (!z2) {
                        mtkTvUSTvRatingSettingInfoBase.setUsAgeTvYBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsAgeTvY7Block(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvY7FVBlock(z);
                    }
                    mtkTvUSTvRatingSettingInfoBase.setUsAgeTvGBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsAgeTvPGBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGDBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGLBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGSBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGVBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsAgeTv14Block(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTv14DBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTv14LBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTv14SBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTv14VBlock(z);
                }
            } else if (ratingString2Age == 6) {
                Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_MA.\n");
                String[] strArr15 = this.mSubRating;
                if (strArr15 != null && strArr15.length > 0) {
                    int i8 = 0;
                    while (true) {
                        String[] strArr16 = this.mSubRating;
                        if (i8 >= strArr16.length) {
                            break;
                        }
                        String str7 = strArr16[i8];
                        if (str7.equals("US_TV_L")) {
                            Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_MA L.\n");
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGLBlock(z);
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTv14LBlock(z);
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTvMALBlock(z);
                        } else if (str7.equals("US_TV_S")) {
                            Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_MA S.\n");
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGSBlock(z);
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTv14SBlock(z);
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTvMASBlock(z);
                        } else if (str7.equals("US_TV_V")) {
                            Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_MA V.\n");
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGVBlock(z);
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTv14VBlock(z);
                            mtkTvUSTvRatingSettingInfoBase.setUsCntTvMAVBlock(z);
                        } else {
                            Log.d(TAG, "convert2USTVRatingInfo: USTV_AGE_MA Unknow Sub-Rating.\n");
                        }
                        i8++;
                    }
                } else {
                    if (!z2) {
                        mtkTvUSTvRatingSettingInfoBase.setUsAgeTvYBlock(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsAgeTvY7Block(z);
                        mtkTvUSTvRatingSettingInfoBase.setUsCntTvY7FVBlock(z);
                    }
                    mtkTvUSTvRatingSettingInfoBase.setUsAgeTvGBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsAgeTvPGBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGDBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGLBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGSBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvPGVBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsAgeTv14Block(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTv14DBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTv14LBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTv14SBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTv14VBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsAgeTvMABlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvMALBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvMASBlock(z);
                    mtkTvUSTvRatingSettingInfoBase.setUsCntTvMAVBlock(z);
                }
            } else {
                Log.d(TAG, "convert2USTVRatingInfo: ERROR Invalid rating age.\n");
            }
            return 0;
        }
        Log.d(TAG, "convert2USTVRatingInfo: Invalid rating for US-TV.\n");
        return -1;
    }

    public int blockContent() {
        int ratingType = getRatingType(this.mRatingSystem);
        if (ratingType == 0) {
            int ratingString2Age = getRatingString2Age(this.mRating);
            Log.d(TAG, "blockContent: ISDB Rating ageRating=" + ratingString2Age);
            MtkTvISDBRating.getInstance().setISDBAgeRatingSetting(ratingString2Age);
            if (this.mRatingSystem.equals(RATING_SYS_STR_BR_TV)) {
                String[] strArr = this.mSubRating;
                if (strArr != null && strArr.length > 0) {
                    int i = 0;
                    int i2 = 0;
                    while (true) {
                        String[] strArr2 = this.mSubRating;
                        if (i >= strArr2.length) {
                            break;
                        }
                        int ratingString2Age2 = getRatingString2Age(strArr2[i]);
                        if (ratingString2Age2 != 255) {
                            i2 |= ratingString2Age2;
                        }
                        i++;
                    }
                    Log.d(TAG, "blockContent: ISDB Rating contentRating=" + i2);
                    MtkTvISDBRating.getInstance().setISDBContentRatingSetting(i2);
                } else {
                    MtkTvISDBRating.getInstance().setISDBContentRatingSetting(0);
                }
            }
        } else if (ratingType == 1) {
            int ratingString2Age3 = getRatingString2Age(this.mRating);
            MtkTvDVBRating.getInstance().setDVBAgeRatingSetting(ratingString2Age3);
            Log.d(TAG, "blockContent: DVB Rating Age=" + ratingString2Age3 + "\n");
        } else if (ratingType == 2) {
            MtkTvUSTvRatingSettingInfoBase uSTvRatingSettingInfo = MtkTvATSCRating.getInstance().getUSTvRatingSettingInfo();
            if (convert2USTVRatingInfo(uSTvRatingSettingInfo, true, MtkTvATSCRating.getInstance().getUSTVGrougByChild()) == 0) {
                Log.d(TAG, "blockContent: US-TV Rating.\n");
                MtkTvATSCRating.getInstance().setUSTvRatingSettingInfo(uSTvRatingSettingInfo);
            } else {
                Log.d(TAG, "blockContent: US-TV Rating Fail!\n");
            }
        } else {
            int i3 = 6;
            if (ratingType == 3) {
                int ratingString2Age4 = getRatingString2Age();
                if (255 != ratingString2Age4 && ratingString2Age4 <= 5) {
                    i3 = ratingString2Age4;
                }
                Log.d(TAG, "blockContent: US-TV MPAA Rating " + i3 + ".\n\n");
                MtkTvATSCRating.getInstance().setUSMovieRatingSettingInfo(i3);
            } else if (ratingType == 4) {
                int ratingString2Age5 = getRatingString2Age(this.mRating);
                if (getRatingString2Age(this.mRatingSystem) == 0) {
                    if (255 == ratingString2Age5) {
                        ratingString2Age5 = 7;
                    }
                    Log.d(TAG, "blockContent: CA-TV ENG Rating(" + ratingString2Age5 + ")\n");
                    MtkTvATSCRating.getInstance().setCANEngRatingSettingInfo(ratingString2Age5);
                } else if (getRatingString2Age(this.mRatingSystem) == 1) {
                    i3 = 255 != ratingString2Age5 ? ratingString2Age5 : 6;
                    Log.d(TAG, "blockContent: CA-TV FRA Rating(" + i3 + ")\n");
                    MtkTvATSCRating.getInstance().setCANFreRatingSettingInfo(i3);
                } else {
                    Log.d(TAG, "blockContent: CA-TV Invalid(" + toString() + ")\n");
                }
            } else {
                Log.d(TAG, "blockContent: Unknow Rating.\n");
            }
        }
        return 0;
    }

    public int unBlockContent() {
        int i;
        int i2;
        int i3;
        int ratingType = getRatingType(this.mRatingSystem);
        if (ratingType == 0) {
            int ratingString2Age = getRatingString2Age(this.mRating);
            Log.d(TAG, "blockContent: ISDB Rating ageRating=" + ratingString2Age);
            MtkTvISDBRating.getInstance().setISDBAgeRatingSetting(ratingString2Age);
            if (this.mRatingSystem.equals(RATING_SYS_STR_BR_TV)) {
                String[] strArr = this.mSubRating;
                if (strArr != null && strArr.length > 0) {
                    int i4 = 0;
                    int i5 = 0;
                    while (true) {
                        String[] strArr2 = this.mSubRating;
                        if (i4 >= strArr2.length) {
                            break;
                        }
                        int ratingString2Age2 = getRatingString2Age(strArr2[i4]);
                        if (ratingString2Age2 != 255) {
                            i5 |= ratingString2Age2;
                        }
                        i4++;
                    }
                    Log.d(TAG, "blockContent: ISDB Rating contentRating=" + i5);
                    MtkTvISDBRating.getInstance().setISDBContentRatingSetting(i5);
                }
            } else {
                MtkTvISDBRating.getInstance().setISDBContentRatingSetting(0);
            }
        } else if (ratingType == 1) {
            int ratingString2Age3 = getRatingString2Age(this.mRating);
            Log.d(TAG, "unBlockContent: DVB Rating=" + ratingString2Age3);
            MtkTvDVBRating.getInstance().setDVBAgeRatingSetting(ratingString2Age3);
        } else if (ratingType == 2) {
            MtkTvUSTvRatingSettingInfoBase uSTvRatingSettingInfo = MtkTvATSCRating.getInstance().getUSTvRatingSettingInfo();
            if (convert2USTVRatingInfo(uSTvRatingSettingInfo, false, MtkTvATSCRating.getInstance().getUSTVGrougByChild()) == 0) {
                Log.d(TAG, "unBlockContent: US-TV Rating.\n");
                MtkTvATSCRating.getInstance().setUSTvRatingSettingInfo(uSTvRatingSettingInfo);
            } else {
                Log.d(TAG, "unBlockContent: US-TV Rating Fail!\n");
            }
        } else {
            int i6 = 7;
            int i7 = 6;
            if (ratingType == 3) {
                int ratingString2Age4 = getRatingString2Age(this.mRating);
                if (255 != ratingString2Age4 && (i3 = ratingString2Age4 + 1) <= 6) {
                    i6 = i3;
                }
                Log.d(TAG, "unBlockContent: US-TV MPAA Rating=" + i6 + ".");
                MtkTvATSCRating.getInstance().setUSMovieRatingSettingInfo(i6);
            } else if (ratingType == 4) {
                int ratingString2Age5 = getRatingString2Age(this.mRating);
                if (getRatingString2Age(this.mRatingSystem) == 0) {
                    if (255 != ratingString2Age5 && (i2 = ratingString2Age5 + 1) <= 6) {
                        i6 = i2;
                    }
                    Log.d(TAG, "unBlockContent: CA-TV ENG Rating=" + i6 + ".");
                    MtkTvATSCRating.getInstance().setCANEngRatingSettingInfo(i6);
                } else if (getRatingString2Age(this.mRatingSystem) == 1) {
                    if (255 != ratingString2Age5 && (i = ratingString2Age5 + 1) <= 5) {
                        i7 = i;
                    }
                    Log.d(TAG, "unBlockContent: CA-TV FRA Rating=" + i7 + ".");
                    MtkTvATSCRating.getInstance().setCANFreRatingSettingInfo(i7);
                }
            } else {
                Log.d(TAG, "unBlockContent: Unknow Rating.\n");
            }
        }
        return 0;
    }

    public int getRatingType(String str) {
        if (str.equals(RATING_SYS_STR_ISDB) || str.equals(RATING_SYS_STR_AR_TV) || str.equals(RATING_SYS_STR_BR_TV)) {
            return 0;
        }
        if (str.equals(RATING_SYS_STR_US_TV)) {
            return 2;
        }
        if (str.equals(RATING_SYS_STR_US_MV)) {
            return 3;
        }
        return (str.equals(RATING_SYS_STR_CA_TV_FR) || str.equals(RATING_SYS_STR_CA_TV_EN)) ? 4 : 1;
    }
}
