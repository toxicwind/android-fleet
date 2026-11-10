package com.mediatek.twoworlds.tv;

import android.util.Log;
import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvScanDvbcBase {
    public static final int DVBC_EXCHANGE_GET_DEF_FREQ = 200;
    public static final int DVBC_EXCHANGE_GET_DEF_NETWORK_ID = 201;
    public static final int DVBC_EXCHANGE_GET_DEF_QAM = 203;
    public static final int DVBC_EXCHANGE_GET_DEF_SYMBOL_RATE = 202;
    public static final int DVBC_EXCHANGE_GET_TYPE_SCAN_MAP = 200;
    public static final int DVBC_EXCHANGE_GET_TYPE_UNKNOWN = 0;
    public static final int DVBC_EXCHANGE_HEADER_LEN = 3;
    public static final int DVBC_EXCHANGE_HEADER_LEN_IDX = 1;
    public static final int DVBC_EXCHANGE_HEADER_TYPE_IDX = 2;
    public static final int DVBC_EXCHANGE_MAX_LEN = 50;
    public static final int DVBC_EXCHANGE_PAYLOAD_1ST_IDX = 3;
    public static final int DVBC_EXCHANGE_PAYLOAD_COUNTRY_ID = 3;
    public static final int DVBC_EXCHANGE_PAYLOAD_DATA = 6;
    public static final int DVBC_EXCHANGE_PAYLOAD_DATA_LEN = 5;
    public static final int DVBC_EXCHANGE_PAYLOAD_OPERATOR = 4;
    public static final int DVBC_EXCHANGE_SET_ASU_STATUS = 1001;
    public static final int DVBC_EXCHANGE_SET_TYPE_AUTO_SCAN = 1003;
    public static final int DVBC_EXCHANGE_SET_TYPE_CANCEL_SCAN = 1006;
    public static final int DVBC_EXCHANGE_SET_TYPE_RF_SCAN = 1005;
    public static final int DVBC_EXCHANGE_SET_TYPE_SCAN_PARAS = 1002;
    public static final int DVBC_EXCHANGE_SET_TYPE_UNKNOWN = 1000;
    public static final int DVBC_EXCHANGE_SET_TYPE_UPDATE_SCAN = 1004;
    public static final int DVBC_EXCHANGE_TOTAL_LEN_IDX = 0;
    public static final int DVBC_STR_GET_TYPE_SCANNING_NW_NAME = 0;
    public static final String TAG = "MtkTvScanDvbc";

    public class MtkTvScanDvbcParameter {
        public static final String TAG = "MtkTvScanDvbcParameter";
        public int mCfgFlag;
        public int mEndFreq;
        public int mModulation;
        public int mNetWorkId;
        public ScanDvbcNitMode mNitMode;
        public int mStartFreq;
        public int mSymRate;

        public MtkTvScanDvbcParameter() {
            ScanDvbcNitMode scanDvbcNitMode = ScanDvbcNitMode.DVBC_NIT_SEARCH_MODE_OFF;
            this.mNitMode = scanDvbcNitMode;
            this.mNetWorkId = -1;
            this.mStartFreq = -1;
            this.mEndFreq = -1;
            this.mCfgFlag = 0;
            this.mModulation = -1;
            this.mSymRate = -1;
            this.mNitMode = scanDvbcNitMode;
            this.mNetWorkId = -1;
            this.mStartFreq = -1;
            this.mEndFreq = -1;
            this.mCfgFlag = 0;
            this.mModulation = -1;
            this.mSymRate = -1;
        }

        public int getCfgFlag() {
            Log.d(TAG, "getCfgFlag Ocurr\n");
            return this.mCfgFlag;
        }

        public int getEndFreq() {
            Log.d(TAG, "getEndFreq Ocurr\n");
            return this.mEndFreq;
        }

        public int getModulation() {
            Log.d(TAG, "getModulation Ocurr\n");
            return this.mModulation;
        }

        public int getNetWorkID() {
            a.a(a.a("getNetWorkID ret="), this.mNetWorkId, "\n", TAG);
            return this.mNetWorkId;
        }

        public ScanDvbcNitMode getNitMode() {
            Log.d(TAG, "getNitMode Ocurr\n");
            return this.mNitMode;
        }

        public int getStartFreq() {
            Log.d(TAG, "getStartFreq Ocurr\n");
            return this.mStartFreq;
        }

        public int getSymRate() {
            Log.d(TAG, "getSymRate Ocurr\n");
            return this.mSymRate;
        }

        public int setCfgFlag(int i) {
            a.a("Enter setCfgFlag(", i, ")\n", TAG);
            this.mCfgFlag = i;
            a.a("Leave setCfgFlag ret=", 0, "\n", TAG);
            return 0;
        }

        public int setEndFreq(int i) {
            a.a("Enter setEndFreq(", i, ")\n", TAG);
            if (i > 0) {
                this.mEndFreq = i;
            } else {
                this.mEndFreq = -1;
            }
            a.a("Leave setEndFreq ret=", 0, "\n", TAG);
            return 0;
        }

        public int setModulation(int i) {
            a.a("Enter setModulation(", i, ")\n", TAG);
            if (i > 0) {
                this.mModulation = i;
            } else {
                this.mModulation = -1;
            }
            a.a("Leave setModulation ret=", 0, "\n", TAG);
            return 0;
        }

        public int setNetWorkID(int i) {
            a.a("Enter setNetWorkID (", i, ")\n", TAG);
            if (i < 0 || i > 65535) {
                this.mNetWorkId = -1;
            } else {
                this.mNetWorkId = i;
            }
            a.a("Leave setNetWorkID ret=", 0, "\n", TAG);
            return 0;
        }

        public int setNitMode(ScanDvbcNitMode scanDvbcNitMode) {
            Log.d(TAG, "Enter setNitMode\n");
            if (scanDvbcNitMode == ScanDvbcNitMode.DVBC_NIT_SEARCH_MODE_OFF || scanDvbcNitMode == ScanDvbcNitMode.DVBC_NIT_SEARCH_MODE_QUICK || scanDvbcNitMode == ScanDvbcNitMode.DVBC_NIT_SEARCH_MODE_EX_QUICK) {
                this.mNitMode = scanDvbcNitMode;
            } else {
                this.mNitMode = ScanDvbcNitMode.DVBC_NIT_SEARCH_MODE_OFF;
            }
            Log.d(TAG, "Leave setNitMode\n");
            return 0;
        }

        public int setStartFreq(int i) {
            a.a("Enter setStartFreq(", i, ")\n", TAG);
            if (i > 0) {
                this.mStartFreq = i;
            } else {
                this.mStartFreq = -1;
            }
            a.a("Leave setScanFreq ret=", 0, "\n", TAG);
            return 0;
        }

        public int setSymRate(int i) {
            a.a("Enter setSymRate(", i, ")\n", TAG);
            if (i > 0) {
                this.mSymRate = i;
            } else {
                this.mSymRate = -1;
            }
            a.a("Leave setSymRate ret=", 0, "\n", TAG);
            return 0;
        }
    }

    public enum ScanDvbcCountryId {
        DVBC_COUNRY_NUL(0),
        DVBC_COUNRY_AUT(1),
        DVBC_COUNRY_BEL(2),
        DVBC_COUNRY_BGR(3),
        DVBC_COUNRY_CHE(4),
        DVBC_COUNRY_CZE(5),
        DVBC_COUNRY_DEU(6),
        DVBC_COUNRY_DNK(7),
        DVBC_COUNRY_ESP(8),
        DVBC_COUNRY_EST(9),
        DVBC_COUNRY_FIN(10),
        DVBC_COUNRY_FRA(11),
        DVBC_COUNRY_GBR(12),
        DVBC_COUNRY_GRC(13),
        DVBC_COUNRY_HRV(14),
        DVBC_COUNRY_HUN(15),
        DVBC_COUNRY_IRL(16),
        DVBC_COUNRY_ITA(17),
        DVBC_COUNRY_LUX(18),
        DVBC_COUNRY_LVA(19),
        DVBC_COUNRY_NLD(20),
        DVBC_COUNRY_NOR(21),
        DVBC_COUNRY_POL(22),
        DVBC_COUNRY_PRT(23),
        DVBC_COUNRY_ROU(24),
        DVBC_COUNRY_RUS(25),
        DVBC_COUNRY_SRB(26),
        DVBC_COUNRY_SVK(27),
        DVBC_COUNRY_SVN(28),
        DVBC_COUNRY_SWE(29),
        DVBC_COUNRY_TUR(30),
        DVBC_COUNRY_UKR(31),
        DVBC_COUNRY_COL(32),
        DVBC_COUNRY_AUS(33),
        DVBC_COUNRY_AZE(34),
        DVBC_COUNRY_GHA(35),
        DVBC_COUNRY_IDN(36),
        DVBC_COUNRY_IND(37),
        DVBC_COUNRY_IRN(38),
        DVBC_COUNRY_KEN(39),
        DVBC_COUNRY_MAR(40),
        DVBC_COUNRY_MMR(41),
        DVBC_COUNRY_MYS(42),
        DVBC_COUNRY_NGA(43),
        DVBC_COUNRY_NZL(44),
        DVBC_COUNRY_PHL(45),
        DVBC_COUNRY_SAU(46),
        DVBC_COUNRY_SGP(47),
        DVBC_COUNRY_THA(48),
        DVBC_COUNRY_TUN(49),
        DVBC_COUNRY_TZA(50),
        DVBC_COUNRY_UGA(51),
        DVBC_COUNRY_VNM(52),
        DVBC_COUNRY_XEA(53),
        DVBC_COUNRY_XGC(54),
        DVBC_COUNRY_XIS(55),
        DVBC_COUNRY_XLV(56),
        DVBC_COUNRY_XNA(57),
        DVBC_COUNRY_XWA(58),
        DVBC_COUNRY_ZAF(59);

        public int index;

        ScanDvbcCountryId(int i) {
            this.index = 0;
            this.index = i;
        }

        public int idOf() {
            return this.index;
        }
    }

    public enum ScanDvbcNitMode {
        DVBC_NIT_SEARCH_MODE_OFF(0),
        DVBC_NIT_SEARCH_MODE_QUICK(1),
        DVBC_NIT_SEARCH_MODE_EX_QUICK(2),
        DVBC_NIT_SEARCH_MODE_NUM(3);

        public int index;

        ScanDvbcNitMode(int i) {
            this.index = 0;
            this.index = i;
        }

        public int idOf() {
            return this.index;
        }
    }

    public enum ScanDvbcOperator {
        DVBC_OPERATOR_NAME_OTHERS(0),
        DVBC_OPERATOR_NAME_UPC(1),
        DVBC_OPERATOR_NAME_COMHEM(2),
        DVBC_OPERATOR_NAME_CANAL_DIGITAL(3),
        DVBC_OPERATOR_NAME_TELE2(4),
        DVBC_OPERATOR_NAME_STOFA(5),
        DVBC_OPERATOR_NAME_YOUSEE(6),
        DVBC_OPERATOR_NAME_ZIGGO(7),
        DVBC_OPERATOR_NAME_UNITYMEDIA(8),
        DVBC_OPERATOR_NAME_NUMERICABLE(9),
        DVBC_OPERATOR_NAME_VOLIA(10),
        DVBC_OPERATOR_NAME_TELEMACH(11),
        DVBC_OPERATOR_NAME_ONLIME(12),
        DVBC_OPERATOR_NAME_AKADO(13),
        DVBC_OPERATOR_NAME_TKT(14),
        DVBC_OPERATOR_NAME_DIVAN_TV(15),
        DVBC_OPERATOR_NAME_NET1(16),
        DVBC_OPERATOR_NAME_KDG(17),
        DVBC_OPERATOR_NAME_KBW(18),
        DVBC_OPERATOR_NAME_BLIZOO(19),
        DVBC_OPERATOR_NAME_TELENET(20),
        DVBC_OPERATOR_NAME_GLENTEN(21),
        DVBC_OPERATOR_NAME_TELECOLUMBUS(22),
        DVBC_OPERATOR_NAME_RCS_RDS(23),
        DVBC_OPERATOR_NAME_VOO(24),
        DVBC_OPERATOR_NAME_KDG_HD(25),
        DVBC_OPERATOR_NAME_KRS(26),
        DVBC_OPERATOR_NAME_TELEING(27),
        DVBC_OPERATOR_NAME_MTS(28),
        DVBC_OPERATOR_NAME_TVOE_STP(29),
        DVBC_OPERATOR_NAME_TVOE_EKA(30),
        DVBC_OPERATOR_NAME_TELEKOM(31);

        public int index;

        ScanDvbcOperator(int i) {
            this.index = 0;
            this.index = i;
        }

        public int idOf() {
            return this.index;
        }
    }

    public enum ScanDvbcRet {
        SCAN_DVBC_RET_OK,
        SCAN_DVBC_RET_INTERNAL_ERROR
    }

    private ScanDvbcRet exchangeData(int i, int[] iArr) {
        ScanDvbcRet scanDvbcRet = ScanDvbcRet.SCAN_DVBC_RET_INTERNAL_ERROR;
        Log.d(TAG, "Enter exchangeData\n");
        int[] iArr2 = iArr == null ? new int[]{0} : iArr;
        int length = iArr2.length + 3;
        if (length > 50) {
            Log.d(TAG, "[Error]bigger than EXCHANGE_MAX_LEN\n");
            return ScanDvbcRet.SCAN_DVBC_RET_INTERNAL_ERROR;
        }
        int[] iArr3 = new int[length];
        iArr3[0] = length;
        iArr3[1] = 3;
        iArr3[2] = i;
        int i2 = 0;
        for (int i3 = 3; i3 < length; i3++) {
            iArr3[i3] = iArr2[i2];
            i2++;
        }
        Log.d(TAG, "\npayload: \n");
        for (int iA = 0; iA < iArr2.length; iA = a.a(a.a("[", iA, "]->"), iArr2[iA], "\t", TAG, iA, 1)) {
        }
        String str = "\n";
        Log.d(TAG, "\n");
        Log.d(TAG, "\ninput exData: \n");
        int iA2 = 0;
        while (iA2 < length) {
            iA2 = a.a(a.a("[", iA2, "]->"), iArr3[iA2], "\t", TAG, iA2, 1);
            str = str;
        }
        String str2 = str;
        Log.d(TAG, str2);
        int iScanDvbcExchangeData_native = TVNativeWrapper.ScanDvbcExchangeData_native(iArr3);
        Log.d(TAG, "\noutput exData: \n");
        for (int iA3 = 0; iA3 < length; iA3 = a.a(a.a("[", iA3, "]->"), iArr3[iA3], "\t", TAG, iA3, 1)) {
        }
        Log.d(TAG, str2);
        int i4 = 0;
        for (int i5 = 3; i5 < length; i5++) {
            iArr2[i4] = iArr3[i5];
            i4++;
        }
        ScanDvbcRet scanDvbcRet2 = iScanDvbcExchangeData_native >= 0 ? ScanDvbcRet.SCAN_DVBC_RET_OK : ScanDvbcRet.SCAN_DVBC_RET_INTERNAL_ERROR;
        Log.d(TAG, "Exit exchangeData\n");
        return scanDvbcRet2;
    }

    private int getOpOneData(ScanDvbcCountryId scanDvbcCountryId, ScanDvbcOperator scanDvbcOperator, int i) {
        int[] iArr = {scanDvbcCountryId.idOf(), scanDvbcOperator.idOf(), 1, 0};
        if (ScanDvbcRet.SCAN_DVBC_RET_OK != exchangeData(i, iArr)) {
            Log.d(TAG, "getOpOneData fail");
            return -1;
        }
        int i2 = iArr[3];
        a.b("getOpOneData ", i2, TAG);
        return i2;
    }

    private ScanDvbcRet setOpOneData(ScanDvbcCountryId scanDvbcCountryId, ScanDvbcOperator scanDvbcOperator, int i, int i2) {
        ScanDvbcRet scanDvbcRetExchangeData = exchangeData(i, new int[]{scanDvbcCountryId.idOf(), scanDvbcOperator.idOf(), 1, i2});
        if (ScanDvbcRet.SCAN_DVBC_RET_OK == scanDvbcRetExchangeData) {
            Log.d(TAG, "setOpOneData ok");
        } else {
            Log.d(TAG, "setOpOneData fail");
        }
        return scanDvbcRetExchangeData;
    }

    public ScanDvbcRet cancelScan() {
        ScanDvbcCountryId scanDvbcCountryId = ScanDvbcCountryId.DVBC_COUNRY_NUL;
        ScanDvbcOperator scanDvbcOperator = ScanDvbcOperator.DVBC_OPERATOR_NAME_OTHERS;
        Log.d(TAG, "Enter cancelScan");
        ScanDvbcRet opOneData = setOpOneData(scanDvbcCountryId, scanDvbcOperator, 1006, 0);
        Log.d(TAG, "Exit cancelScan");
        return opOneData;
    }

    public int getDefaultFrequency(ScanDvbcCountryId scanDvbcCountryId, ScanDvbcOperator scanDvbcOperator) {
        Log.d(TAG, "Enter getDefaultFrequency");
        int opOneData = getOpOneData(scanDvbcCountryId, scanDvbcOperator, 200);
        a.a("Exit getDefaultFrequency: ", opOneData, "\n", TAG);
        return opOneData;
    }

    public int getDefaultModulation(ScanDvbcCountryId scanDvbcCountryId, ScanDvbcOperator scanDvbcOperator) {
        Log.d(TAG, "Enter getDefaultModulation");
        int opOneData = getOpOneData(scanDvbcCountryId, scanDvbcOperator, 203);
        a.a("Exit getDefaultModulation: ", opOneData, "\n", TAG);
        return opOneData;
    }

    public int getDefaultNetworkId(ScanDvbcCountryId scanDvbcCountryId, ScanDvbcOperator scanDvbcOperator) {
        Log.d(TAG, "Enter getDefaultNetworkId");
        int opOneData = getOpOneData(scanDvbcCountryId, scanDvbcOperator, 201);
        a.a("Exit getDefaultNetworkId: ", opOneData, "\n", TAG);
        return opOneData;
    }

    public int getDefaultSymbolRate(ScanDvbcCountryId scanDvbcCountryId, ScanDvbcOperator scanDvbcOperator) {
        Log.d(TAG, "Enter getDefaultSymbolRate");
        int opOneData = getOpOneData(scanDvbcCountryId, scanDvbcOperator, 202);
        a.a("Exit getDefaultSymbolRate: ", opOneData, "\n", TAG);
        return opOneData;
    }

    public String getScanningNwName() {
        Log.d(TAG, "Enter getScanningNwName\n");
        String strScanDvbcGetStrData_native = TVNativeWrapper.ScanDvbcGetStrData_native(0);
        a.a("Exit getScanningNwName: ", strScanDvbcGetStrData_native, "\n", TAG);
        return strScanDvbcGetStrData_native;
    }

    public ScanDvbcRet setDvbcScanParas(MtkTvScanDvbcParameter mtkTvScanDvbcParameter) {
        ScanDvbcCountryId scanDvbcCountryId = ScanDvbcCountryId.DVBC_COUNRY_NUL;
        ScanDvbcOperator scanDvbcOperator = ScanDvbcOperator.DVBC_OPERATOR_NAME_OTHERS;
        Log.d(TAG, "Enter setDvbcScanParas\n");
        ScanDvbcRet scanDvbcRetExchangeData = exchangeData(1002, new int[]{scanDvbcCountryId.idOf(), scanDvbcOperator.idOf(), 7, mtkTvScanDvbcParameter.getNitMode().idOf(), mtkTvScanDvbcParameter.getNetWorkID(), mtkTvScanDvbcParameter.getStartFreq(), mtkTvScanDvbcParameter.getEndFreq(), mtkTvScanDvbcParameter.getCfgFlag(), mtkTvScanDvbcParameter.getModulation(), mtkTvScanDvbcParameter.getSymRate()});
        if (ScanDvbcRet.SCAN_DVBC_RET_OK == scanDvbcRetExchangeData) {
            Log.d(TAG, "Leave setDvbcScanParas ok");
        } else {
            Log.d(TAG, "Leave setDvbcScanParas fail");
        }
        return scanDvbcRetExchangeData;
    }

    public ScanDvbcRet startAutoScan() {
        ScanDvbcCountryId scanDvbcCountryId = ScanDvbcCountryId.DVBC_COUNRY_NUL;
        ScanDvbcOperator scanDvbcOperator = ScanDvbcOperator.DVBC_OPERATOR_NAME_OTHERS;
        Log.d(TAG, "Enter startAutoScan");
        ScanDvbcRet opOneData = setOpOneData(scanDvbcCountryId, scanDvbcOperator, 1003, 0);
        Log.d(TAG, "Exit startAutoScan");
        return opOneData;
    }

    public ScanDvbcRet startRfScan() {
        ScanDvbcCountryId scanDvbcCountryId = ScanDvbcCountryId.DVBC_COUNRY_NUL;
        ScanDvbcOperator scanDvbcOperator = ScanDvbcOperator.DVBC_OPERATOR_NAME_OTHERS;
        Log.d(TAG, "Enter startRfScan");
        ScanDvbcRet opOneData = setOpOneData(scanDvbcCountryId, scanDvbcOperator, 1005, 0);
        Log.d(TAG, "Exit startRfScan");
        return opOneData;
    }

    public ScanDvbcRet startUpdateScan() {
        ScanDvbcCountryId scanDvbcCountryId = ScanDvbcCountryId.DVBC_COUNRY_NUL;
        ScanDvbcOperator scanDvbcOperator = ScanDvbcOperator.DVBC_OPERATOR_NAME_OTHERS;
        Log.d(TAG, "Enter startUpdateScan");
        ScanDvbcRet opOneData = setOpOneData(scanDvbcCountryId, scanDvbcOperator, 1004, 0);
        Log.d(TAG, "Exit startUpdateScan");
        return opOneData;
    }
}
