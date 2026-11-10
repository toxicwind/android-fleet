package com.mediatek.twoworlds.tv.model;

import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvDvbsSatelliteSettingBase {
    public static final String TAG = "TV_MtkTvDvbsSatelliteSetting";
    public TunerUserBandType mTunerUserBand = TunerUserBandType.USER_BAND_1;
    public int mBandfreq = 950;
    public DvbsSatelliteRecID mSatelliteRecID = DvbsSatelliteRecID.DVBS_SATELLITE_1;
    public DvbsSatelliteStatus mSatelliteStatus = DvbsSatelliteStatus.SATELLITE_ON;
    public DvbsSatelliteScanType mSatelliteScanType = DvbsSatelliteScanType.DVBS_SCAN_TYPE_FULL;
    public DvbsServiceType mServiceType = DvbsServiceType.DVBS_SCAN_SERVICE_TYPE_ALL;
    public DvbsChannelType mChannelType = DvbsChannelType.DVBS_SCAN_CHANNEL_TYPE_ALL;
    public int mLnbLowFreq = 9750;
    public int mLnbHighFreq = 10600;
    public DvbsSatellite22kType m22k = DvbsSatellite22kType.TUNER_DISEQC_22K_OFF;
    public DisqecCfgUniversal mDisqCfg = DisqecCfgUniversal.DISCQEC_CFG_DISABLE;
    public TunerLnbPowerType mTunerLnbPower = TunerLnbPowerType.LNB_POWER_OFF;
    public int mTransponderFreq = 10714;
    public int mSymbolRate = 10714;
    public DvbsTunerPolarization mPolarization = DvbsTunerPolarization.POL_LIN_HORIZONTAL;
    public TunerDiseqcType mTunerDiseqc = TunerDiseqcType.DISEQC_NONE;
    public int mPort = 0;
    public int mToneBurst = 0;
    public TunerLnbType mTunerLnb = TunerLnbType.LNB_DUAL_FREQ;
    public TunerUnicablePosType mTunerUnicablePos = TunerUnicablePosType.UNICABLE_POS_A;

    public enum DisqecCfgUniversal {
        DISCQEC_CFG_DISABLE,
        DISCQEC_CFG_A,
        DISCQEC_CFG_B,
        DISCQEC_CFG_C,
        DISCQEC_CFG_D,
        DISCQEC_CFG_TONE_A,
        DISCQEC_CFG_TONE_B
    }

    public enum DvbsChannelType {
        DVBS_SCAN_CHANNEL_TYPE_ALL,
        DVBS_SCAN_CHANNEL_TYPE_TV,
        DVBS_SCAN_CHANNEL_TYPE_TV_AND_RADIO,
        DVBS_SCAN_CHANNEL_TYPE_NUM
    }

    public enum DvbsSatellite22kType {
        TUNER_DISEQC_22K_OFF,
        TUNER_DISEQC_22K_ON,
        TUNER_DISEQC_22K_AUTO,
        TUNER_DISEQC_22K_LAST_ENTRY
    }

    public enum DvbsSatelliteRecID {
        DVBS_SATELLITE_1,
        DVBS_SATELLITE_2,
        DVBS_SATELLITE_3,
        DVBS_SATELLITE_4
    }

    public enum DvbsSatelliteScanType {
        DVBS_SCAN_TYPE_FULL,
        DVBS_SCAN_TYPE_NETWORK,
        DVBS_SCAN_TYPE_MANUAL,
        DVBS_SCAN_TYPE_LAST_ENTRY
    }

    public enum DvbsSatelliteStatus {
        SATELLITE_ON,
        SATELLITE_OFF,
        SATELLITE_LAST_ENTRY
    }

    public enum DvbsServiceType {
        DVBS_SCAN_SERVICE_TYPE_ALL,
        DVBS_SCAN_SERVICE_TYPE_FREE,
        DVBS_SCAN_SERVICE_TYPE_NUM
    }

    public enum DvbsTunerPolarization {
        POL_UNKNOWN,
        POL_LIN_HORIZONTAL,
        POL_LIN_VERTICAL,
        POL_CIR_LEFT,
        POL_CIR_RIGHT
    }

    public enum TunerDiseqcType {
        DISEQC_NONE,
        DISEQC_2X1,
        DISEQC_4X1,
        DISEQC_8X1,
        DISEQC_16X1,
        DISEQC_1DOT2,
        DISEQC_USALS,
        DISEQC_UNICABLE
    }

    public enum TunerLnbPowerType {
        LNB_POWER_OFF,
        LNB_POWER_13V_18V,
        LNB_POWER_14V_19V,
        LNB_POWER_ON
    }

    public enum TunerLnbType {
        LNB_UNKNOWN,
        LNB_SINGLE_FREQ,
        LNB_DUAL_FREQ
    }

    public enum TunerUnicablePosType {
        UNICABLE_POS_UNKNOWN,
        UNICABLE_POS_A,
        UNICABLE_POS_B
    }

    public enum TunerUserBandType {
        USER_BAND_1,
        USER_BAND_2,
        USER_BAND_3,
        USER_BAND_4,
        USER_BAND_5,
        USER_BAND_6,
        USER_BAND_7,
        USER_BAND_8
    }

    public int Set22kType(DvbsSatellite22kType dvbsSatellite22kType) {
        Log.d(TAG, "Enter setToneBurst\n");
        this.m22k = dvbsSatellite22kType;
        Log.d(TAG, "Leave setToneBurst\n");
        return 0;
    }

    public int SetTunerPolarization(DvbsTunerPolarization dvbsTunerPolarization) {
        Log.d(TAG, "Enter setToneBurst\n");
        this.mPolarization = dvbsTunerPolarization;
        Log.d(TAG, "Leave setToneBurst\n");
        return 0;
    }

    public int get22kType() {
        Log.d(TAG, "getToneBurst Ocurr\n");
        DvbsSatellite22kType dvbsSatellite22kType = DvbsSatellite22kType.TUNER_DISEQC_22K_OFF;
        DvbsSatellite22kType dvbsSatellite22kType2 = this.m22k;
        if (dvbsSatellite22kType == dvbsSatellite22kType2) {
            return 0;
        }
        if (DvbsSatellite22kType.TUNER_DISEQC_22K_ON == dvbsSatellite22kType2) {
            return 1;
        }
        return DvbsSatellite22kType.TUNER_DISEQC_22K_AUTO == dvbsSatellite22kType2 ? 2 : 0;
    }

    public DvbsSatellite22kType get22kTypeEnum() {
        Log.d(TAG, "getToneBurst Ocurr\n");
        return this.m22k;
    }

    public int getBandfreq() {
        Log.d(TAG, "getBandfreq Ocurr\n");
        return this.mBandfreq;
    }

    public int getDisqecCfgUniversal() {
        Log.d(TAG, "getTunerUnicablePosType Ocurr\n");
        DisqecCfgUniversal disqecCfgUniversal = DisqecCfgUniversal.DISCQEC_CFG_DISABLE;
        DisqecCfgUniversal disqecCfgUniversal2 = this.mDisqCfg;
        if (disqecCfgUniversal == disqecCfgUniversal2) {
            return 0;
        }
        if (DisqecCfgUniversal.DISCQEC_CFG_A == disqecCfgUniversal2) {
            return 1;
        }
        if (DisqecCfgUniversal.DISCQEC_CFG_B == disqecCfgUniversal2) {
            return 2;
        }
        if (DisqecCfgUniversal.DISCQEC_CFG_C == disqecCfgUniversal2) {
            return 3;
        }
        if (DisqecCfgUniversal.DISCQEC_CFG_D == disqecCfgUniversal2) {
            return 4;
        }
        if (DisqecCfgUniversal.DISCQEC_CFG_TONE_A == disqecCfgUniversal2) {
            return 5;
        }
        return DisqecCfgUniversal.DISCQEC_CFG_TONE_B == disqecCfgUniversal2 ? 6 : 0;
    }

    public DisqecCfgUniversal getDisqecCfgUniversalEnum() {
        Log.d(TAG, "getDisqecCfgUniversal Ocurr\n");
        return this.mDisqCfg;
    }

    public int getDvbsScanChannelType() {
        Log.d(TAG, "getDvbsScanChannelType Ocurr\n");
        DvbsChannelType dvbsChannelType = DvbsChannelType.DVBS_SCAN_CHANNEL_TYPE_ALL;
        DvbsChannelType dvbsChannelType2 = this.mChannelType;
        if (dvbsChannelType == dvbsChannelType2) {
            return 0;
        }
        if (DvbsChannelType.DVBS_SCAN_CHANNEL_TYPE_TV == dvbsChannelType2) {
            return 1;
        }
        return DvbsChannelType.DVBS_SCAN_CHANNEL_TYPE_TV_AND_RADIO == dvbsChannelType2 ? 2 : 3;
    }

    public DvbsChannelType getDvbsScanChannelTypeEnum() {
        Log.d(TAG, "getDvbsScanChannelType Ocurr\n");
        return this.mChannelType;
    }

    public int getDvbsScanServiceType() {
        Log.d(TAG, "getDvbsScanServiceType Ocurr\n");
        DvbsServiceType dvbsServiceType = DvbsServiceType.DVBS_SCAN_SERVICE_TYPE_ALL;
        DvbsServiceType dvbsServiceType2 = this.mServiceType;
        if (dvbsServiceType == dvbsServiceType2) {
            return 0;
        }
        if (DvbsServiceType.DVBS_SCAN_SERVICE_TYPE_FREE == dvbsServiceType2) {
            return 1;
        }
        return DvbsServiceType.DVBS_SCAN_SERVICE_TYPE_NUM == dvbsServiceType2 ? 2 : 0;
    }

    public DvbsServiceType getDvbsScanServiceTypeEnum() {
        Log.d(TAG, "getDvbsScanServiceType Ocurr\n");
        return this.mServiceType;
    }

    public int getLnbHighFreq() {
        Log.d(TAG, "getLnbHighFreq Ocurr\n");
        return this.mLnbHighFreq;
    }

    public int getLnbLowFreq() {
        Log.d(TAG, "getLnbLowFreq Ocurr\n");
        return this.mLnbLowFreq;
    }

    public int getSatelliteOnOff() {
        Log.d(TAG, "getSatelliteOnOff Ocurr\n");
        if (DvbsSatelliteStatus.SATELLITE_ON == this.mSatelliteStatus) {
            return 1;
        }
        DvbsSatelliteStatus dvbsSatelliteStatus = DvbsSatelliteStatus.SATELLITE_OFF;
        return 0;
    }

    public DvbsSatelliteStatus getSatelliteOnOffEnum() {
        Log.d(TAG, "getSatelliteOnOff Ocurr\n");
        return this.mSatelliteStatus;
    }

    public int getSatelliteRecID() {
        Log.d(TAG, "getSatelliteRecID Ocurr\n");
        DvbsSatelliteRecID dvbsSatelliteRecID = DvbsSatelliteRecID.DVBS_SATELLITE_1;
        DvbsSatelliteRecID dvbsSatelliteRecID2 = this.mSatelliteRecID;
        if (dvbsSatelliteRecID == dvbsSatelliteRecID2) {
            return 1;
        }
        if (DvbsSatelliteRecID.DVBS_SATELLITE_2 == dvbsSatelliteRecID2) {
            return 2;
        }
        if (DvbsSatelliteRecID.DVBS_SATELLITE_3 == dvbsSatelliteRecID2) {
            return 3;
        }
        return DvbsSatelliteRecID.DVBS_SATELLITE_4 == dvbsSatelliteRecID2 ? 4 : 1;
    }

    public DvbsSatelliteRecID getSatelliteRecIDEnum() {
        Log.d(TAG, "getSatelliteRecID Ocurr\n");
        return this.mSatelliteRecID;
    }

    public int getSatelliteScanType() {
        Log.d(TAG, "getSatelliteScanType Ocurr\n");
        DvbsSatelliteScanType dvbsSatelliteScanType = DvbsSatelliteScanType.DVBS_SCAN_TYPE_FULL;
        DvbsSatelliteScanType dvbsSatelliteScanType2 = this.mSatelliteScanType;
        if (dvbsSatelliteScanType == dvbsSatelliteScanType2) {
            return 0;
        }
        if (DvbsSatelliteScanType.DVBS_SCAN_TYPE_NETWORK == dvbsSatelliteScanType2) {
            return 1;
        }
        return DvbsSatelliteScanType.DVBS_SCAN_TYPE_MANUAL == dvbsSatelliteScanType2 ? 2 : 0;
    }

    public DvbsSatelliteScanType getSatelliteScanTypeEnum() {
        Log.d(TAG, "getSatelliteScanType Ocurr\n");
        return this.mSatelliteScanType;
    }

    public int getSymbolRate() {
        Log.d(TAG, "getSymbolRate Ocurr\n");
        return this.mSymbolRate;
    }

    public int getTransponderFreq() {
        Log.d(TAG, "getTransponderFreq Ocurr\n");
        return this.mTransponderFreq;
    }

    public int getTunerLnbPowerType() {
        Log.d(TAG, "getTunerLnbPowerType Ocurr\n");
        TunerLnbPowerType tunerLnbPowerType = TunerLnbPowerType.LNB_POWER_OFF;
        TunerLnbPowerType tunerLnbPowerType2 = this.mTunerLnbPower;
        if (tunerLnbPowerType == tunerLnbPowerType2) {
            return 0;
        }
        if (TunerLnbPowerType.LNB_POWER_13V_18V == tunerLnbPowerType2) {
            return 1;
        }
        if (TunerLnbPowerType.LNB_POWER_14V_19V == tunerLnbPowerType2) {
            return 2;
        }
        return TunerLnbPowerType.LNB_POWER_ON == tunerLnbPowerType2 ? 3 : 0;
    }

    public TunerLnbPowerType getTunerLnbPowerTypeEnum() {
        Log.d(TAG, "getTunerLnbPowerType Ocurr\n");
        return this.mTunerLnbPower;
    }

    public int getTunerPolarization() {
        Log.d(TAG, "getToneBurst Ocurr\n");
        DvbsTunerPolarization dvbsTunerPolarization = DvbsTunerPolarization.POL_UNKNOWN;
        DvbsTunerPolarization dvbsTunerPolarization2 = this.mPolarization;
        if (dvbsTunerPolarization == dvbsTunerPolarization2) {
            return 0;
        }
        if (DvbsTunerPolarization.POL_LIN_HORIZONTAL == dvbsTunerPolarization2) {
            return 1;
        }
        if (DvbsTunerPolarization.POL_LIN_VERTICAL == dvbsTunerPolarization2) {
            return 2;
        }
        if (DvbsTunerPolarization.POL_CIR_LEFT == dvbsTunerPolarization2) {
            return 3;
        }
        return DvbsTunerPolarization.POL_CIR_RIGHT == dvbsTunerPolarization2 ? 4 : 0;
    }

    public DvbsTunerPolarization getTunerPolarizationEnum() {
        Log.d(TAG, "getTunerPolarizationEnum Ocurr\n");
        return this.mPolarization;
    }

    public int getTunerUserBandType() {
        Log.d(TAG, "getTunerUserBandType Ocurr\n");
        TunerUserBandType tunerUserBandType = TunerUserBandType.USER_BAND_1;
        TunerUserBandType tunerUserBandType2 = this.mTunerUserBand;
        if (tunerUserBandType == tunerUserBandType2) {
            return 0;
        }
        if (TunerUserBandType.USER_BAND_2 == tunerUserBandType2) {
            return 1;
        }
        if (TunerUserBandType.USER_BAND_3 == tunerUserBandType2) {
            return 2;
        }
        if (TunerUserBandType.USER_BAND_4 == tunerUserBandType2) {
            return 3;
        }
        if (TunerUserBandType.USER_BAND_5 == tunerUserBandType2) {
            return 4;
        }
        if (TunerUserBandType.USER_BAND_6 == tunerUserBandType2) {
            return 5;
        }
        if (TunerUserBandType.USER_BAND_7 == tunerUserBandType2) {
            return 6;
        }
        return TunerUserBandType.USER_BAND_8 == tunerUserBandType2 ? 7 : 0;
    }

    public TunerUserBandType getTunerUserBandTypeEnum() {
        Log.d(TAG, "getTunerUserBandType Ocurr\n");
        return this.mTunerUserBand;
    }

    public int setBandfreq(int i) {
        int i2;
        Log.d(TAG, "Enter setBandfreq\n");
        if (i < 0 || i > 9999) {
            i2 = -1;
        } else {
            this.mBandfreq = i;
            i2 = 0;
        }
        Log.d(TAG, "Leave setBandfreq\n");
        return i2;
    }

    public int setDisqecCfgUniversal(DisqecCfgUniversal disqecCfgUniversal) {
        Log.d(TAG, "Enter setTunerUnicablePosType\n");
        this.mDisqCfg = disqecCfgUniversal;
        Log.d(TAG, "Leave setTunerUnicablePosType\n");
        return 0;
    }

    public int setDvbsScanChannelType(DvbsChannelType dvbsChannelType) {
        Log.d(TAG, "Enter setDvbsScanChannelType\n");
        this.mChannelType = dvbsChannelType;
        Log.d(TAG, "Leave setDvbsScanChannelType\n");
        return 0;
    }

    public int setDvbsScanServiceType(DvbsServiceType dvbsServiceType) {
        Log.d(TAG, "Enter setDvbsScanServiceType\n");
        this.mServiceType = dvbsServiceType;
        Log.d(TAG, "Leave setDvbsScanServiceType\n");
        return 0;
    }

    public int setLnbHighFreq(int i) {
        int i2;
        Log.d(TAG, "Enter setLnbHighFreq\n");
        if (i < 0 || i > 99999) {
            i2 = -1;
        } else {
            this.mLnbHighFreq = i;
            i2 = 0;
        }
        Log.d(TAG, "Leave setLnbHighFreq\n");
        return i2;
    }

    public int setLnbLowFreq(int i) {
        int i2;
        Log.d(TAG, "Enter setLnbLowFreq\n");
        if (i < 0 || i > 9999) {
            i2 = -1;
        } else {
            this.mLnbLowFreq = i;
            i2 = 0;
        }
        Log.d(TAG, "Leave setLnbLowFreq\n");
        return i2;
    }

    public int setSatelliteOnOff(DvbsSatelliteStatus dvbsSatelliteStatus) {
        Log.d(TAG, "Enter setSatelliteOnOff\n");
        this.mSatelliteStatus = dvbsSatelliteStatus;
        Log.d(TAG, "Leave setSatelliteOnOff\n");
        return 0;
    }

    public int setSatelliteRecID(DvbsSatelliteRecID dvbsSatelliteRecID) {
        Log.d(TAG, "Enter setSatelliteRecID\n");
        this.mSatelliteRecID = dvbsSatelliteRecID;
        Log.d(TAG, "Leave setSatelliteRecID\n");
        return 0;
    }

    public int setSatelliteScanType(DvbsSatelliteScanType dvbsSatelliteScanType) {
        Log.d(TAG, "Enter setSatelliteScanType\n");
        this.mSatelliteScanType = dvbsSatelliteScanType;
        Log.d(TAG, "Leave setSatelliteScanType\n");
        return 0;
    }

    public int setSymbolRate(int i) {
        int i2;
        Log.d(TAG, "Enter setSymbolRate\n");
        if (i < 0 || i > 99999) {
            i2 = -1;
        } else {
            this.mSymbolRate = i;
            i2 = 0;
        }
        Log.d(TAG, "Leave setSymbolRate\n");
        return i2;
    }

    public int setTransponderFreq(int i) {
        int i2;
        Log.d(TAG, "Enter setTransponderFreq\n");
        if (i < 0 || i > 99999) {
            i2 = -1;
        } else {
            this.mTransponderFreq = i;
            i2 = 0;
        }
        Log.d(TAG, "Leave setTransponderFreq\n");
        return i2;
    }

    public int setTunerLnbPowerType(TunerLnbPowerType tunerLnbPowerType) {
        Log.d(TAG, "Enter setTunerLnbPowerType\n");
        this.mTunerLnbPower = tunerLnbPowerType;
        Log.d(TAG, "Leave setTunerLnbPowerType\n");
        return 0;
    }

    public int setTunerUserBandType(TunerUserBandType tunerUserBandType) {
        Log.d(TAG, "Enter setTunerUserBandType\n");
        this.mTunerUserBand = tunerUserBandType;
        Log.d(TAG, "Leave setTunerUserBandType\n");
        return 0;
    }
}
