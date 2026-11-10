package com.mediatek.twoworlds.tv;

import android.util.Log;
import b.a.a.a.a;
import com.mediatek.twoworlds.tv.model.MtkTvATSCScanParaBase;
import com.mediatek.twoworlds.tv.model.MtkTvCQAMScanParaBase;
import com.mediatek.twoworlds.tv.model.MtkTvDvbScanParaBase;
import com.mediatek.twoworlds.tv.model.MtkTvDvbcManualScanParaBase;
import com.mediatek.twoworlds.tv.model.MtkTvDvbcScanParaBase;
import com.mediatek.twoworlds.tv.model.MtkTvDvbsSatelliteSettingBase;
import com.mediatek.twoworlds.tv.model.MtkTvISDBScanParaBase;
import com.mediatek.twoworlds.tv.model.MtkTvNTSCScanParaBase;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvScanBase {
    public static final String TAG = "TV_MtkTvScan";
    public static MarketRegion mRegion = MarketRegion.PLF_OPT_REGION_EU;
    public MtkTvScanDvbcBase mtkTvScanDvbcBase = new MtkTvScanDvbcBase();
    public MtkTvScanATSCBase mtkTvScanATSCBase = new MtkTvScanATSCBase();
    public MtkTvScanISDBBase mtkTvScanISDBBase = new MtkTvScanISDBBase();
    public MtkTvScanDvbtBase mtkTvScanDvbtBase = new MtkTvScanDvbtBase();
    public MtkTvScanPalSecamBase mtkTvScanPalSecamBase = new MtkTvScanPalSecamBase();

    public enum CallBackType {
        CALL_BACK_TYPE_AUTO,
        CALL_BACK_TYPE_ATV,
        CALL_BACK_TYPE_DTV,
        CALL_BACK_TYPE_DTV_DVBT_UI_OP,
        CALL_BACK_TYPE_DTV_DVBT_NWK_UPDATE,
        CALL_BACK_TYPE_DTV_DVBT_TYPE_NUM,
        CALL_BACK_TYPE_DTV_DVBT_FULL_SCAN,
        CALL_BACK_TYPE_DTV_DVBT_SVC_UPDATE,
        CALL_BACK_TYPE_DTV_DVBC_NWK_UPDATE,
        CALL_BACK_TYPE_DTV_DVBS_SAT_NAME_NFY_FCT,
        CALL_BACK_TYPE_DTV_DVBS_SVC_UPDATE_NFY_FCT,
        CALL_BACK_TYPE_DTV_DVBS_NEW_SVC_NFY_FCT,
        CALL_BACK_TYPE_DTV_DVBS_BOUQUET_INFO_NFY_FCT,
        CALL_BACK_TYPE_DTV_DVBS_MDU_DETECT_NFY_FCT,
        CALL_BACK_TYPE_DTV_DVBS_INFO_GET_NFY_FCT,
        CALL_BACK_TYPE_DTV_DVBC_NW_NAME_GOTTEN,
        CALL_BACK_TYPE_DTV_DVBC_FREQ_LOCK_FAIL,
        CALL_BACK_TYPE_DTV_DVBC_NID_NOT_FOUND,
        CALL_BACK_TYPE_DTV_DVBC_SVC_UPDATE,
        CALL_BACK_TYPE_DTV_DVBC_TYPE_NUM,
        CALL_BACK_TYPE_NUM
    }

    public enum DvbcNitMode {
        DVBC_NIT_SEARCH_MODE_OFF,
        DVBC_NIT_SEARCH_MODE_QUICK,
        DVBC_NIT_SEARCH_MODE_EX_QUICK,
        DVBC_NIT_SEARCH_MODE_NUM
    }

    public enum MarketRegion {
        PLF_OPT_REGION_NAFTA,
        PLF_OPT_REGION_LATAM,
        PLF_OPT_REGION_GA,
        PLF_OPT_REGION_CHINA,
        PLF_OPT_REGION_EU,
        PLF_OPT_REGION_RUSSIA,
        PLF_OPT_REGION_PAD,
        PLF_OPT_REGION_TAIWAN,
        PLF_OPT_REGION_COLOMBIA,
        PLF_OPT_REGION_LAST_VALID_ENTRY
    }

    public enum ScanMode {
        SCAN_MODE_FULL,
        SCAN_MODE_UPDATE,
        SCAN_MODE_RANGE,
        SCAN_MODE_QUICK,
        SCAN_MODE_ADD_ON,
        SCAN_MODE_SINGLE_RF_CHANNEL,
        SCAN_MODE_RANGE_RF_CHANNEL,
        SCAN_MODE_MANUAL_RF_CHANNEL,
        SCAN_MODE_MANUAL_FREQ,
        SCAN_MODE_BGM,
        SCAN_MODE_DVBC_NIT_SEARCH_MODE_OFF,
        SCAN_MODE_DVBC_NIT_SEARCH_MODE_QUICK,
        SCAN_MODE_DVBC_NIT_SEARCH_MODE_EX_QUICK,
        SCAN_MODE_NUM
    }

    public enum ScanType {
        SCAN_TYPE_PAL,
        SCAN_TYPE_DVBT,
        SCAN_TYPE_DVBC,
        SCAN_TYPE_NTSC,
        SCAN_TYPE_ATSC,
        SCAN_TYPE_ISDB,
        SCAN_TYPE_CQAM,
        SCAN_TYPE_DVBT2,
        SCAN_TYPE_DVBS,
        SCAN_TYPE_DTMB,
        SCAN_TYPE_US,
        SCAN_TYPE_SA,
        SCAN_TYPE_NUM
    }

    public enum UIMode {
        UI_MODE_NETWORK,
        UI_MODE_LCN,
        UI_MODE_LCN_V2,
        UI_MODE_UK_REGION,
        UI_MODE_NULL,
        UI_MODE_NUM
    }

    public static MarketRegion getMarketRegion() {
        Log.d(TAG, "Enter getMarketRegion\n");
        int marketRegion = TVNativeWrapper.getMarketRegion();
        a.b("getMarketRegion ", marketRegion, TAG);
        if (marketRegion == 0) {
            mRegion = MarketRegion.PLF_OPT_REGION_NAFTA;
            Log.d(TAG, "PLF_OPT_REGION_NAFTA\n");
        } else if (1 == marketRegion) {
            mRegion = MarketRegion.PLF_OPT_REGION_LATAM;
            Log.d(TAG, "PLF_OPT_REGION_LATAM\n");
        } else if (2 == marketRegion) {
            mRegion = MarketRegion.PLF_OPT_REGION_GA;
            Log.d(TAG, "PLF_OPT_REGION_GA\n");
        } else if (3 == marketRegion) {
            mRegion = MarketRegion.PLF_OPT_REGION_CHINA;
            Log.d(TAG, "PLF_OPT_REGION_CHINA\n");
        } else if (4 == marketRegion) {
            mRegion = MarketRegion.PLF_OPT_REGION_EU;
            Log.d(TAG, "PLF_OPT_REGION_EU\n");
        } else if (5 == marketRegion) {
            mRegion = MarketRegion.PLF_OPT_REGION_RUSSIA;
            Log.d(TAG, "PLF_OPT_REGION_RUSSIA\n");
        } else if (6 == marketRegion) {
            mRegion = MarketRegion.PLF_OPT_REGION_PAD;
            Log.d(TAG, "PLF_OPT_REGION_PAD\n");
        } else if (7 == marketRegion) {
            mRegion = MarketRegion.PLF_OPT_REGION_TAIWAN;
            Log.d(TAG, "PLF_OPT_REGION_TAIWAN\n");
        } else if (8 == marketRegion) {
            mRegion = MarketRegion.PLF_OPT_REGION_COLOMBIA;
            Log.d(TAG, "PLF_OPT_REGION_COLOMBIA\n");
        } else {
            mRegion = MarketRegion.PLF_OPT_REGION_EU;
            Log.d(TAG, "PLF_OPT_REGION_EU\n");
        }
        Log.d(TAG, "Leave getMarketRegion\n");
        return mRegion;
    }

    public int cancelScan() {
        Log.d(TAG, "Enter cancelScan\n");
        int iCancelScan_native = TVNativeWrapper.cancelScan_native();
        Log.d(TAG, "Leave cancelScan\n");
        return iCancelScan_native;
    }

    public MtkTvScanATSCBase getScanATSCInstance() {
        return this.mtkTvScanATSCBase;
    }

    public MtkTvScanDvbcBase getScanDvbcInstance() {
        return this.mtkTvScanDvbcBase;
    }

    public MtkTvScanDvbtBase getScanDvbtInstance() {
        return this.mtkTvScanDvbtBase;
    }

    public MtkTvScanISDBBase getScanISDBInstance() {
        return this.mtkTvScanISDBBase;
    }

    public MtkTvScanPalSecamBase getScanPalSecamInstance() {
        return this.mtkTvScanPalSecamBase;
    }

    public UIMode getUIMode() {
        UIMode uIMode;
        Log.d(TAG, "Enter getUIMode\n");
        UIMode uIMode2 = UIMode.UI_MODE_NUM;
        int iCancelScan_native = TVNativeWrapper.cancelScan_native();
        if (iCancelScan_native == 0) {
            uIMode = UIMode.UI_MODE_NETWORK;
        } else if (1 == iCancelScan_native) {
            uIMode = UIMode.UI_MODE_LCN;
        } else if (2 == iCancelScan_native) {
            uIMode = UIMode.UI_MODE_LCN_V2;
        } else if (3 == iCancelScan_native) {
            uIMode = UIMode.UI_MODE_UK_REGION;
        } else {
            uIMode = 4 == iCancelScan_native ? UIMode.UI_MODE_NULL : UIMode.UI_MODE_NUM;
        }
        Log.d(TAG, "Leave getUIMode\n");
        return uIMode;
    }

    public boolean isScanning() {
        return TVNativeWrapper.isScaning_native();
    }

    public int setATSCScanParas(MtkTvATSCScanParaBase mtkTvATSCScanParaBase) {
        Log.d(TAG, "Enter setATSCScanParas\n");
        int aTSCScanParas = TVNativeWrapper.setATSCScanParas(mtkTvATSCScanParaBase);
        Log.d(TAG, "Leave setATSCScanParas\n");
        return aTSCScanParas;
    }

    public int setCQAMScanParas(MtkTvCQAMScanParaBase mtkTvCQAMScanParaBase) {
        return 0;
    }

    public int setDvbScanParas(MtkTvDvbScanParaBase mtkTvDvbScanParaBase) {
        Log.d(TAG, "Enter setDvbScanParas\n");
        int dvbScanParas = TVNativeWrapper.setDvbScanParas(mtkTvDvbScanParaBase);
        Log.d(TAG, "Leave setDvbScanParas\n");
        return dvbScanParas;
    }

    public int setDvbcManualScanParas(MtkTvDvbcManualScanParaBase mtkTvDvbcManualScanParaBase) {
        Log.d(TAG, "Enter setDvbcManualScanParas\n");
        int dvbcManualScanParas = TVNativeWrapper.setDvbcManualScanParas(mtkTvDvbcManualScanParaBase);
        Log.d(TAG, "Leave setDvbcManualScanParas\n");
        return dvbcManualScanParas;
    }

    public int setDvbcScanParas(MtkTvDvbcScanParaBase mtkTvDvbcScanParaBase) {
        Log.d(TAG, "Enter setDvbcScanParas\n");
        int dvbcScanParas = TVNativeWrapper.setDvbcScanParas(mtkTvDvbcScanParaBase);
        Log.d(TAG, "Leave setDvbcScanParas\n");
        return dvbcScanParas;
    }

    public int setISDBScanParas(MtkTvISDBScanParaBase mtkTvISDBScanParaBase) {
        Log.d(TAG, "Enter setISDBScanParas\n");
        int iSDBScanParas = TVNativeWrapper.setISDBScanParas(mtkTvISDBScanParaBase);
        Log.d(TAG, "Leave setISDBScanParas\n");
        return iSDBScanParas;
    }

    public int setNTSCScanParas(MtkTvNTSCScanParaBase mtkTvNTSCScanParaBase) {
        Log.d(TAG, "Enter setNTSCScanParas\n");
        int nTSCScanParas = TVNativeWrapper.setNTSCScanParas(mtkTvNTSCScanParaBase);
        Log.d(TAG, "Leave setNTSCScanParas\n");
        return nTSCScanParas;
    }

    public int setSatelliteSetting(MtkTvDvbsSatelliteSettingBase mtkTvDvbsSatelliteSettingBase) {
        Log.d(TAG, "Enter setSatelliteSetting\n");
        int satelliteSetting = TVNativeWrapper.setSatelliteSetting(mtkTvDvbsSatelliteSettingBase);
        Log.d(TAG, "Leave setSatelliteSetting\n");
        return satelliteSetting;
    }

    /* JADX WARN: Code duplicated, block: B:4:0x0015  */
    public int startScan(ScanType scanType, ScanMode scanMode, boolean z) {
        int i;
        Log.d(TAG, "Enter startScan\n");
        int i2 = 0;
        if (ScanType.SCAN_TYPE_PAL == scanType) {
            i = 0;
        } else if (ScanType.SCAN_TYPE_DVBT == scanType) {
            i = 1;
        } else if (ScanType.SCAN_TYPE_DVBC == scanType) {
            i = 2;
        } else if (ScanType.SCAN_TYPE_NTSC == scanType) {
            i = 3;
        } else if (ScanType.SCAN_TYPE_ATSC == scanType) {
            i = 4;
        } else if (ScanType.SCAN_TYPE_ISDB == scanType) {
            i = 5;
        } else if (ScanType.SCAN_TYPE_CQAM == scanType) {
            i = 6;
        } else if (ScanType.SCAN_TYPE_DVBT2 == scanType) {
            i = 7;
        } else if (ScanType.SCAN_TYPE_DVBS == scanType) {
            i = 8;
        } else if (ScanType.SCAN_TYPE_DTMB == scanType) {
            i = 9;
        } else if (ScanType.SCAN_TYPE_US == scanType) {
            i = 10;
        } else if (ScanType.SCAN_TYPE_SA == scanType) {
            i = 11;
        } else {
            i = 0;
        }
        if (ScanMode.SCAN_MODE_FULL != scanMode) {
            if (ScanMode.SCAN_MODE_UPDATE == scanMode) {
                i2 = 1;
            } else if (ScanMode.SCAN_MODE_RANGE == scanMode) {
                i2 = 2;
            } else if (ScanMode.SCAN_MODE_QUICK == scanMode) {
                i2 = 3;
            } else if (ScanMode.SCAN_MODE_ADD_ON == scanMode) {
                i2 = 4;
            } else if (ScanMode.SCAN_MODE_SINGLE_RF_CHANNEL == scanMode) {
                i2 = 5;
            } else if (ScanMode.SCAN_MODE_RANGE_RF_CHANNEL == scanMode) {
                i2 = 6;
            } else if (ScanMode.SCAN_MODE_MANUAL_RF_CHANNEL == scanMode) {
                i2 = 7;
            } else if (ScanMode.SCAN_MODE_MANUAL_FREQ == scanMode) {
                i2 = 8;
            }
        }
        int iStartScan_native = TVNativeWrapper.startScan_native(i, i2, z);
        Log.d(TAG, "Leave startScan\n");
        return iStartScan_native;
    }
}
