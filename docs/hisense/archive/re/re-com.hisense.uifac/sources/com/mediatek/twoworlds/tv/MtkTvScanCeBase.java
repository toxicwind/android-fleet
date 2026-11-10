package com.mediatek.twoworlds.tv;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvScanCeBase {
    public static final int CE_RET_INTERNAL_ERR = -1;
    public static final int CE_RET_OK = 0;
    public static final int COUNTRY_CHN = 0;
    private static final int EX_HEADER_LEN = 2;
    private static final int EX_HEADER_LEN_IDX = 0;
    private static final int EX_HEADER_TYPE_IDX = 1;
    private static final int EX_MAX_LEN = 50;
    private static final int EX_PAYLOAD_1ST_IDX = 2;
    private static final int EX_TYPE_AUTO_SCAN = 3;
    private static final int EX_TYPE_CANCEL_SCAN = 1;
    private static final int EX_TYPE_QUICK_SCAN = 4;
    private static final int EX_TYPE_RF_SCAN = 2;
    private static final int EX_TYPE_UNKNOWN = 0;
    private static final int FREQ_MAX = 858000000;
    private static final int FREQ_MIN = 48000000;
    public static final int NIT_SEARCH_MODE_NUM = 2;
    public static final int NIT_SEARCH_MODE_OFF = 0;
    public static final int NIT_SEARCH_MODE_QUICK = 1;
    public static final int OPERATOR_BEIJING_GEHUAYOUXIAN = 1008;
    public static final int OPERATOR_CHANGSHA_GUOAN = 1004;
    public static final int OPERATOR_CHONGQING_YOUXIAN = 1019;
    public static final int OPERATOR_DALIAN_GUANGDIAN = 1011;
    public static final int OPERATOR_DAZHOU = 1024;
    public static final int OPERATOR_EZHOU_YOUXIAN = 1014;
    public static final int OPERATOR_GUANGZHOU_SHENGWANG = 1001;
    public static final int OPERATOR_GUANGZHOU_SHIWANG = 1002;
    public static final int OPERATOR_HANGZHOU_HUASHU = 1018;
    public static final int OPERATOR_HUBEI_HUANGSHI = 1010;
    public static final int OPERATOR_HUNAN_GUANGDIAN = 1003;
    public static final int OPERATOR_JIANGXI = 1021;
    public static final int OPERATOR_KUNSHAN = 1020;
    public static final int OPERATOR_MAX = 1024;
    public static final int OPERATOR_NEIMENGGU_YOUXIAN = 1013;
    public static final int OPERATOR_OTHERS = 0;
    public static final int OPERATOR_QINGDAO_GUANGDIAN = 1009;
    public static final int OPERATOR_SANMING_YOUXIAN = 1016;
    public static final int OPERATOR_SHANGHAI = 1015;
    public static final int OPERATOR_SHAOXING_GUANGDIAN = 1012;
    public static final int OPERATOR_SNN_OCN = 1022;
    public static final int OPERATOR_SNN_OTHERS = 1023;
    public static final int OPERATOR_WUHAN_SHENGWANG = 1005;
    public static final int OPERATOR_WUHAN_SHIWANG = 1006;
    public static final int OPERATOR_XIAN_GUANGDIAN = 1007;
    public static final int OPERATOR_ZHANGJIAGANG = 1017;
    private static final String TAG = "";
    private int country = 0;
    private int operator = 0;
    private int nitMode = 0;
    private int networkId = 0;
    private int startFreq = 0;
    private int endFreq = 0;
    private int cfgFlag = 0;

    private static void Log(int i) {
        StackTraceElement[] stackTrace = new Exception().getStackTrace();
        Log.e(TAG, stackTrace[1].getClassName() + "." + stackTrace[1].getMethodName() + " [" + new Integer(stackTrace[1].getLineNumber()).toString() + "] :---> " + i);
    }

    private static void Log(String str) {
        StackTraceElement[] stackTrace = new Exception().getStackTrace();
        Log.e(TAG, stackTrace[1].getClassName() + "." + stackTrace[1].getMethodName() + " [" + new Integer(stackTrace[1].getLineNumber()).toString() + "] :---> " + str);
    }

    public int setOperator(int i) {
        if (i == 0 || (i >= 1001 && i <= 1024)) {
            Log(this.operator + " -> " + i);
            this.operator = i;
            return 0;
        }
        Log("setting value (" + i + ") invalid, keep crnt " + this.operator);
        return -1;
    }

    public int getOperator() {
        Log(this.operator);
        return this.operator;
    }

    public int setNitMode(int i) {
        if (i >= 0 && i < 2) {
            Log(this.nitMode + " -> " + i);
            this.nitMode = i;
            return 0;
        }
        Log("setting value (" + i + ") invalid, keep crnt " + this.nitMode);
        return -1;
    }

    public int getNitMode() {
        Log(this.nitMode);
        return this.nitMode;
    }

    public int setNetworkID(int i) {
        if (i >= 0 && i <= 65535) {
            Log(this.networkId + " -> " + i);
            this.networkId = i;
            return 0;
        }
        Log("setting value (" + i + ") invalid, keep crnt " + this.networkId);
        return -1;
    }

    public int getNetWorkID() {
        Log(this.networkId);
        return this.networkId;
    }

    public int setStartFreq(int i) {
        if (i >= FREQ_MIN && i <= FREQ_MAX) {
            Log(this.startFreq + " -> " + i);
            this.startFreq = i;
            return 0;
        }
        Log("setting value (" + i + ") invalid, keep crnt " + this.startFreq);
        return -1;
    }

    public int getStartFreq() {
        Log(this.startFreq);
        return this.startFreq;
    }

    public int setEndFreq(int i) {
        if (i >= FREQ_MIN && i <= FREQ_MAX) {
            Log(this.endFreq + " -> " + i);
            this.endFreq = i;
            return 0;
        }
        Log("setting value (" + i + ") invalid, keep crnt " + this.endFreq);
        return -1;
    }

    public int getEndFreq() {
        Log(this.endFreq);
        return this.endFreq;
    }

    public int setCfgFlag(int i) {
        if (i >= 0) {
            Log(this.cfgFlag + " -> " + i);
            this.cfgFlag = i;
            return 0;
        }
        Log("setting value (" + i + ") invalid, keep crnt " + this.cfgFlag);
        return -1;
    }

    public int getCfgFlag() {
        Log(this.cfgFlag);
        return this.cfgFlag;
    }

    public int cancelScan() {
        Log("enter");
        int i_toolExchange = _toolExchange(1);
        Log("ret: " + i_toolExchange);
        return i_toolExchange;
    }

    public int startAutoScan() {
        Log("enter");
        int i_toolExchange = _toolExchange(3);
        Log("ret: " + i_toolExchange);
        return i_toolExchange;
    }

    public int startQuickScan(int i) {
        Log(i);
        int startFreq = setStartFreq(i);
        if (startFreq != 0) {
            return startFreq;
        }
        int endFreq = setEndFreq(i);
        if (endFreq != 0) {
            return endFreq;
        }
        int i_toolExchange = _toolExchange(4, new int[]{0, i});
        Log("ret: " + i_toolExchange);
        return i_toolExchange;
    }

    public int startRfScan(int i) {
        Log(i);
        int startFreq = setStartFreq(i);
        if (startFreq != 0) {
            return startFreq;
        }
        int endFreq = setEndFreq(i);
        if (endFreq != 0) {
            return endFreq;
        }
        int i_toolExchange = _toolExchange(2, new int[]{0, i});
        Log("ret: " + i_toolExchange);
        return i_toolExchange;
    }

    private String _toolConvertIntArrayToString(int[] iArr) {
        if (iArr == null) {
            Log("return null: null == intArray");
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i : iArr) {
            sb.append(i);
            sb.append("  ");
        }
        return sb.toString();
    }

    private int _toolExchange(int i) {
        return _toolExchange(i, new int[]{0});
    }

    private int _toolExchange(int i, int[] iArr) {
        if (iArr == null) {
            iArr = new int[]{0};
        }
        int length = iArr.length + 2;
        if (length > 50) {
            Log("return -1: ex_pkg len " + length + " > limit len 50");
            return -1;
        }
        int[] iArr2 = new int[length];
        iArr2[0] = 2;
        iArr2[1] = i;
        int i2 = 0;
        for (int i3 = 2; i3 < length; i3++) {
            iArr2[i3] = iArr[i2];
            i2++;
        }
        Log("ex_pkg len: " + length + " [" + _toolConvertIntArrayToString(iArr2) + "]");
        int iScanCeExchangeData_native = TVNativeWrapper.ScanCeExchangeData_native(iArr2);
        StringBuilder sb = new StringBuilder();
        sb.append("ex_pkg cooked: ");
        sb.append(_toolConvertIntArrayToString(iArr2));
        Log(sb.toString());
        int i4 = 0;
        for (int i5 = 2; i5 < length; i5++) {
            iArr[i4] = iArr2[i5];
            i4++;
        }
        return iScanCeExchangeData_native >= 0 ? 0 : -1;
    }
}
