package com.mediatek.twoworlds.tv;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvScanDtmbBase {
    public static final int DTMB_RET_INTERNAL_ERR = -1;
    public static final int DTMB_RET_OK = 0;
    private static final int EX_HEADER_LEN = 2;
    private static final int EX_HEADER_LEN_IDX = 0;
    private static final int EX_HEADER_TYPE_IDX = 1;
    private static final int EX_MAX_LEN = 50;
    private static final int EX_PAYLOAD_1ST_IDX = 2;
    private static final int EX_TYPE_AUTO_SCAN = 3;
    private static final int EX_TYPE_CANCEL_SCAN = 1;
    private static final int EX_TYPE_GET_RF_INFO = 4;
    private static final int EX_TYPE_MANUAL_FREQ_SCAN = 5;
    private static final int EX_TYPE_RF_SCAN = 2;
    private static final int EX_TYPE_UNKNOWN = 0;
    public static final int RF_CURR = 0;
    public static final int RF_NEXT = 2;
    public static final int RF_PREV = 1;
    private static final String TAG = "";

    private String _toolMapIntToString(int i, int i2) {
        if (i != 0) {
            return "no-ops";
        }
        if (i2 == 0) {
            return "crnt";
        }
        if (i2 != 1) {
            return i2 != 2 ? "unknown" : "next";
        }
        return "prev";
    }

    private static void Log(int i) {
        StackTraceElement[] stackTrace = new Exception().getStackTrace();
        Log.e(TAG, stackTrace[1].getClassName() + "." + stackTrace[1].getMethodName() + " [" + new Integer(stackTrace[1].getLineNumber()).toString() + "] :---> " + i);
    }

    private static void Log(String str) {
        StackTraceElement[] stackTrace = new Exception().getStackTrace();
        Log.e(TAG, stackTrace[1].getClassName() + "." + stackTrace[1].getMethodName() + " [" + new Integer(stackTrace[1].getLineNumber()).toString() + "] :---> " + str);
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

    public int startRfScan() {
        Log("enter");
        int i_toolExchange = _toolExchange(2);
        Log("ret: " + i_toolExchange);
        return i_toolExchange;
    }

    public int startManualFreqScan(int i) {
        Log("enter: freq = " + i);
        int i_toolExchange = _toolExchange(5, new int[]{i});
        Log("ret: " + i_toolExchange);
        return i_toolExchange;
    }

    public class RfInfo {
        private static final int MAX_RF_NAME_LEN = 20;
        public String rfChannelName;
        public int rfFreq;
        public int rfIndex;

        public RfInfo() {
        }
    }

    public RfInfo gotoDestinationRf(int i) {
        if (i < 0 || i > 2) {
            Log("return: unknown rf direction " + i);
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("rfDirection: ");
        sb.append(i);
        sb.append(", ");
        sb.append(_toolMapIntToString(0, i));
        Log(sb.toString());
        int[] iArr = new int[20];
        int[] iArr2 = new int[24];
        iArr2[0] = 0;
        iArr2[1] = i;
        int i_toolExchange = _toolExchange(4, iArr2);
        int i2 = iArr2[0];
        if (i_toolExchange != 0 || i2 != 0) {
            Log("return null: exRet " + i_toolExchange + ", mwScanRet " + i2);
            return null;
        }
        for (int i3 = 0; i3 < 20; i3++) {
            iArr[i3] = iArr2[4 + i3];
        }
        RfInfo rfInfo = new RfInfo();
        rfInfo.rfIndex = iArr2[2];
        rfInfo.rfFreq = iArr2[3];
        rfInfo.rfChannelName = _toolConvertAsciiArrayToString(iArr);
        Log("RF [" + rfInfo.rfChannelName + "]: idx " + rfInfo.rfIndex + ", freq " + rfInfo.rfFreq);
        return rfInfo;
    }

    private String _toolConvertAsciiArrayToString(int[] iArr) {
        if (iArr == null) {
            Log("return null: null == asciiArray");
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i : iArr) {
            sb.append((char) i);
        }
        return sb.toString();
    }

    private String _toolConvertIntArrayToString(int[] iArr) {
        if (iArr == null) {
            Log("return null: null == intArray");
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iArr.length; i++) {
            sb.append("  [" + i + "]");
            sb.append(iArr[i]);
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
        Log("ex_pkg len(" + length + "): " + _toolConvertIntArrayToString(iArr2));
        int iScanDtmbExchangeData_native = TVNativeWrapper.ScanDtmbExchangeData_native(iArr2);
        StringBuilder sb = new StringBuilder();
        sb.append("ex_pkg cooked: ");
        sb.append(_toolConvertIntArrayToString(iArr2));
        Log(sb.toString());
        int i4 = 0;
        for (int i5 = 2; i5 < length; i5++) {
            iArr[i4] = iArr2[i5];
            i4++;
        }
        return iScanDtmbExchangeData_native >= 0 ? 0 : -1;
    }
}
