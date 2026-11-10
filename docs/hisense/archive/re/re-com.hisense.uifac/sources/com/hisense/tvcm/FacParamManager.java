package com.hisense.tvcm;

import android.net.wifi.ScanResult;
import com.hisense.tvs.CESIConstants;
import com.hisense.uifac.R;

/* JADX INFO: loaded from: classes.dex */
public class FacParamManager {
    public static int FAC_ATTEST_COUNT = 20;
    public static String FAC_AUTO_GAMMA_BIN_FILE_PATH = "/data/vendor/3rd_rw/factory/GammaStd_His.bin";
    public static String FAC_AUTO_GAMMA_FILE_PATH = "/data/vendor/3rd_rw/factory/Gamma_His.ini";
    public static String FAC_DMP_FILE_NAME = "4K_VIDEO_TEST";
    public static int FAC_SN_EM_COUNT = 23;
    public static int FAC_SN_JP_COUNT = 8;
    public static int FAC_SN_US_COUNT = 15;
    public static String HIFAC_DOLBY_UPDATE_FILENAME = "hisense_dolby_vision_iq_update.cfg";
    public static String mBlueResult = "";
    public static ScanResult mWifiResult;
    public static int FAC_TVS_ACAS_STATE_VALID = CESIConstants.getValue("AcasState_e", "VALID");
    public static int FAC_SCREEN_PROTECT_TIME = 180000;
    public static int[] REGION_ARRAY = {R.array.factorymenu_country_for_Europe_B_region, R.array.factorymenu_country_for_muslims_region, R.array.factorymenu_country_for_not_muslims_region, R.array.factorymenu_country_for_asia_pacific_region};
    public static int[] REGION_ARRAY_WITH_SA = {R.array.factorymenu_country_for_Europe_B_region, R.array.factorymenu_country_for_muslims_region, R.array.factorymenu_country_for_not_muslims_region, R.array.factorymenu_country_for_asia_pacific_region, R.array.factorymenu_country_for_South_America_region};
    public static final int[] SALOGO = {R.array.factorymenu_logo_sa_bgh, R.array.factorymenu_logo_sa_devant, R.array.factorymenu_logo_sa_newsan, R.array.factorymenu_logo_sa_default};
}
