package com.mediatek.twoworlds.tv;

import android.util.Log;
import androidx.core.os.EnvironmentCompat;
import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvScanDtmbBase {
    public static final int DTMB_RET_INTERNAL_ERR = -1;
    public static final int DTMB_RET_OK = 0;
    public static final int EX_HEADER_LEN = 2;
    public static final int EX_HEADER_LEN_IDX = 0;
    public static final int EX_HEADER_TYPE_IDX = 1;
    public static final int EX_MAX_LEN = 50;
    public static final int EX_PAYLOAD_1ST_IDX = 2;
    public static final int EX_TYPE_AUTO_SCAN = 3;
    public static final int EX_TYPE_CANCEL_SCAN = 1;
    public static final int EX_TYPE_GET_RF_INFO = 4;
    public static final int EX_TYPE_MANUAL_FREQ_SCAN = 5;
    public static final int EX_TYPE_RF_SCAN = 2;
    public static final int EX_TYPE_UNKNOWN = 0;
    public static final int RF_CURR = 0;
    public static final int RF_NEXT = 2;
    public static final int RF_PREV = 1;
    public static final String TAG = "";

    public class RfInfo {
        public static final int MAX_RF_NAME_LEN = 20;
        public String rfChannelName;
        public int rfFreq;
        public int rfIndex;

        public RfInfo() {
        }
    }

    public static void Log(int i) {
        StackTraceElement[] stackTrace = new Exception().getStackTrace();
        Log.e("", stackTrace[1].getClassName() + "." + stackTrace[1].getMethodName() + " [" + new Integer(stackTrace[1].getLineNumber()).toString() + "] :---> " + i);
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

    private String _toolMapIntToString(int i, int i2) {
        if (i != 0) {
            return "no-ops";
        }
        if (i2 == 0) {
            return "crnt";
        }
        if (i2 != 1) {
            return i2 != 2 ? EnvironmentCompat.MEDIA_UNKNOWN : "next";
        }
        return "prev";
    }

    public int cancelScan() {
        Log("enter");
        int i_toolExchange = _toolExchange(1);
        Log("ret: " + i_toolExchange);
        return i_toolExchange;
    }

    public RfInfo gotoDestinationRf(int i) {
        if (i < 0 || i > 2) {
            Log("return: unknown rf direction " + i);
            return null;
        }
        StringBuilder sbA = a.a("rfDirection: ", i, ", ");
        sbA.append(_toolMapIntToString(0, i));
        Log(sbA.toString());
        int[] iArr = {0, i, 0, 0};
        int[] iArr2 = new int[20];
        int[] iArr3 = new int[iArr.length + iArr2.length];
        iArr3[0] = 0;
        iArr3[1] = i;
        int i_toolExchange = _toolExchange(4, iArr3);
        int i2 = iArr3[0];
        if (i_toolExchange != 0 || i2 != 0) {
            Log("return null: exRet " + i_toolExchange + ", mwScanRet " + i2);
            return null;
        }
        for (int i3 = 0; i3 < iArr2.length; i3++) {
            iArr2[i3] = iArr3[iArr.length + i3];
        }
        RfInfo rfInfo = new RfInfo();
        rfInfo.rfIndex = iArr3[2];
        rfInfo.rfFreq = iArr3[3];
        rfInfo.rfChannelName = _toolConvertAsciiArrayToString(iArr2);
        StringBuilder sbA2 = a.a("RF [");
        sbA2.append(rfInfo.rfChannelName);
        sbA2.append("]: idx ");
        sbA2.append(rfInfo.rfIndex);
        sbA2.append(", freq ");
        sbA2.append(rfInfo.rfFreq);
        Log(sbA2.toString());
        return rfInfo;
    }

    public int startAutoScan() {
        Log("enter");
        int i_toolExchange = _toolExchange(3);
        Log("ret: " + i_toolExchange);
        return i_toolExchange;
    }

    public int startManualFreqScan(int i) {
        Log("enter: freq = " + i);
        int i_toolExchange = _toolExchange(5, new int[]{i});
        Log("ret: " + i_toolExchange);
        return i_toolExchange;
    }

    public int startRfScan() {
        Log("enter");
        int i_toolExchange = _toolExchange(2);
        Log("ret: " + i_toolExchange);
        return i_toolExchange;
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
        StringBuilder sbA = a.a("ex_pkg len(", length, "): ");
        sbA.append(_toolConvertIntArrayToString(iArr2));
        Log(sbA.toString());
        int iScanDtmbExchangeData_native = TVNativeWrapper.ScanDtmbExchangeData_native(iArr2);
        StringBuilder sbA2 = a.a("ex_pkg cooked: ");
        sbA2.append(_toolConvertIntArrayToString(iArr2));
        Log(sbA2.toString());
        int i4 = 0;
        for (int i5 = 2; i5 < length; i5++) {
            iArr[i4] = iArr2[i5];
            i4++;
        }
        return iScanDtmbExchangeData_native >= 0 ? 0 : -1;
    }

    public static void Log(String str) {
        StackTraceElement[] stackTrace = new Exception().getStackTrace();
        Log.e("", stackTrace[1].getClassName() + "." + stackTrace[1].getMethodName() + " [" + new Integer(stackTrace[1].getLineNumber()).toString() + "] :---> " + str);
    }
}
