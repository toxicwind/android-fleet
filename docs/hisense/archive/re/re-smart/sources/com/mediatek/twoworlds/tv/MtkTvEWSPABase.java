package com.mediatek.twoworlds.tv;

import android.util.Log;
import b.a.a.a.a;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvEWSPABase {
    public static final int EWS_GET_HAS_EWS = 2;
    public static final int EWS_GET_INFO_MSG_NUM = 3;
    public static final int EWS_GET_MORE_INFO_MSG = 4;
    public static final int EWS_GET_NONE = 0;
    public static final int EWS_GET_SVC_INFO = 1;
    public static final int EWS_SET_LOCATION_CODE = 1;
    public static final int EWS_SET_NONE = 0;
    public static final int MAX_INFO_MSG_NUM_ONCE = 1;
    public static final int MAX_INFO_MSG_STRING_LEN = 2100;
    public static final int MAX_STRING_LEN = 256;
    public static final String TAG = "MtkTvEWSPABase";

    public class EwsInfo {
        public byte authority;
        public String charDisasterCharacterstic;
        public byte charDisasterCharactersticLen;
        public String charDisasterCode;
        public byte charDisasterCodeLen;
        public String charDisasterDate;
        public byte charDisasterDateLen;
        public String charDisasterPosition;
        public byte charDisasterPositionLen;
        public short charInfoMsgNum;
        public String charLocationCode;
        public byte charLocationCodeLen;
        public short disasterCode;
        public TmdwInfoMsg[] infoMsg;
        public byte[] locationCode = {0, 0, 0};
        public byte locationTypeCode;

        public EwsInfo() {
        }
    }

    public enum EwspaRet {
        EWSPA_RET_OK,
        EWSPA_RET_INTERNAL_ERROR
    }

    public class TmdwInfoMsg {
        public String charInfoMsg;
        public short charInfoMsgLen;

        public TmdwInfoMsg() {
        }
    }

    public MtkTvEWSPABase() {
        Log.d(TAG, "MtkTvEWSPABase object created");
    }

    private String _toolConvertAsciiArrayToString(int[] iArr) {
        StringBuilder sb = new StringBuilder();
        if (iArr == null) {
            return null;
        }
        byte[] bArr = new byte[iArr.length];
        for (int iA = 0; iA < iArr.length; iA = a.a(new StringBuilder(), bArr[iA], "\n", TAG, iA, 1)) {
            bArr[iA] = (byte) iArr[iA];
        }
        sb.append(new String(bArr));
        return sb.toString();
    }

    public boolean bWithEWSInfo(byte b2) {
        int[] iArr = new int[2];
        Log.d(TAG, "bWithEWSInfo entered");
        TVNativeWrapper.getInfo_native(b2, 2, iArr);
        if (iArr[0] != 0) {
            return false;
        }
        boolean z = iArr[1] == 1;
        Log.d(TAG, "bWithEWSInfo exit");
        return z;
    }

    public void createMonitorInst(byte b2) {
        Log.d(TAG, "createMonitorInst entered, monitorType:" + ((int) b2));
        TVNativeWrapper.createMonitorInst_native(b2);
        Log.d(TAG, "createMonitorInst exit");
    }

    public void deleteMonitorInst(byte b2) {
        Log.d(TAG, "deleteMonitorInst entered, monitorType:" + ((int) b2));
        TVNativeWrapper.deleteMonitorInst_native(b2);
        Log.d(TAG, "deleteMonitorInst exit");
    }

    public EwsInfo getEWSInfo(byte b2) {
        TmdwInfoMsg[] tmdwInfoMsgArr;
        int[] iArr = new int[256];
        int[] iArr2 = new int[3];
        EwsInfo ewsInfo = new EwsInfo();
        Log.d(TAG, "getEWSInfo entered");
        TVNativeWrapper.getInfo_native(b2, 3, iArr2);
        if (iArr2[0] != 0) {
            return null;
        }
        ewsInfo.charInfoMsgNum = (short) iArr2[1];
        StringBuilder sbA = a.a("infoMsgNum:");
        sbA.append((int) ewsInfo.charInfoMsgNum);
        Log.d(TAG, sbA.toString());
        Log.d(TAG, "MaxLen[2100]:2100");
        Log.d(TAG, "ews information total len:" + MAX_INFO_MSG_STRING_LEN);
        int[] iArr3 = new int[MAX_INFO_MSG_STRING_LEN];
        iArr3[1] = ewsInfo.charInfoMsgNum;
        iArr3[2] = 1;
        TVNativeWrapper.getInfo_native(b2, 1, iArr3);
        if (iArr3[0] != 0) {
            return null;
        }
        ewsInfo.disasterCode = (short) iArr3[1];
        ewsInfo.charInfoMsgNum = (short) iArr3[2];
        ewsInfo.locationTypeCode = (byte) iArr3[3];
        ewsInfo.authority = (byte) iArr3[4];
        byte[] bArr = ewsInfo.locationCode;
        bArr[0] = (byte) iArr3[5];
        bArr[1] = (byte) iArr3[6];
        bArr[2] = (byte) iArr3[7];
        ewsInfo.charDisasterCodeLen = (byte) iArr3[8];
        ewsInfo.charDisasterDateLen = (byte) iArr3[9];
        ewsInfo.charDisasterPositionLen = (byte) iArr3[10];
        ewsInfo.charDisasterCharactersticLen = (byte) iArr3[11];
        ewsInfo.charLocationCodeLen = (byte) iArr3[12];
        StringBuilder sbA2 = a.a("disasterCode:");
        sbA2.append((int) ewsInfo.disasterCode);
        Log.d(TAG, sbA2.toString());
        Log.d(TAG, "ewsInfo.charInfoMsgNum:" + ((int) ewsInfo.charInfoMsgNum));
        Log.d(TAG, "ewsInfo.locationTypeCode:" + ((int) ewsInfo.locationTypeCode));
        Log.d(TAG, "ewsInfo.authority:" + ((int) ewsInfo.authority));
        Log.d(TAG, "ewsInfo.locationCode:" + ewsInfo.locationCode);
        Log.d(TAG, "ewsInfo.charDisasterCodeLen:" + ((int) ewsInfo.charDisasterCodeLen));
        Log.d(TAG, "ewsInfo.charDisasterDateLen:" + ((int) ewsInfo.charDisasterDateLen));
        Log.d(TAG, "ewsInfo.charDisasterPositionLen:" + ((int) ewsInfo.charDisasterPositionLen));
        Log.d(TAG, "ewsInfo.charDisasterCharactersticLen:" + ((int) ewsInfo.charDisasterCharactersticLen));
        Log.d(TAG, "ewsInfo.charLocationCodeLen:" + ((int) ewsInfo.charLocationCodeLen));
        Log.d(TAG, "idx:13");
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = iArr3[13 + i];
            if (i < 20) {
                a.b(a.a("intEwsString[]:"), iArr[i], TAG);
            }
        }
        ewsInfo.charDisasterCode = _toolConvertAsciiArrayToString(iArr);
        int length = 13 + iArr.length;
        StringBuilder sbA3 = a.a("ewsInfo.charDisasterCode:");
        sbA3.append(ewsInfo.charDisasterCode);
        Log.d(TAG, sbA3.toString());
        Log.d(TAG, "idx:" + length);
        for (int i2 = 0; i2 < iArr.length; i2++) {
            iArr[i2] = iArr3[length + i2];
        }
        ewsInfo.charDisasterDate = _toolConvertAsciiArrayToString(iArr);
        int length2 = length + iArr.length;
        StringBuilder sbA4 = a.a("ewsInfo.charDisasterDate:");
        sbA4.append(ewsInfo.charDisasterDate);
        Log.d(TAG, sbA4.toString());
        Log.d(TAG, "idx:" + length2);
        for (int i3 = 0; i3 < iArr.length; i3++) {
            iArr[i3] = iArr3[length2 + i3];
        }
        ewsInfo.charDisasterPosition = _toolConvertAsciiArrayToString(iArr);
        int length3 = length2 + iArr.length;
        StringBuilder sbA5 = a.a("ewsInfo.charDisasterPosition:");
        sbA5.append(ewsInfo.charDisasterPosition);
        Log.d(TAG, sbA5.toString());
        Log.d(TAG, "idx:" + length3);
        for (int i4 = 0; i4 < iArr.length; i4++) {
            iArr[i4] = iArr3[length3 + i4];
        }
        ewsInfo.charDisasterCharacterstic = _toolConvertAsciiArrayToString(iArr);
        int length4 = length3 + iArr.length;
        StringBuilder sbA6 = a.a("ewsInfo.charDisasterCharacterstic:");
        sbA6.append(ewsInfo.charDisasterCharacterstic);
        Log.d(TAG, sbA6.toString());
        Log.d(TAG, "idx:" + length4);
        for (int i5 = 0; i5 < iArr.length; i5++) {
            iArr[i5] = iArr3[length4 + i5];
        }
        ewsInfo.charLocationCode = _toolConvertAsciiArrayToString(iArr);
        int length5 = length4 + iArr.length;
        StringBuilder sbA7 = a.a("ewsInfo.charLocationCode:");
        sbA7.append(ewsInfo.charLocationCode);
        Log.d(TAG, sbA7.toString());
        Log.d(TAG, "idx:" + length5);
        int i6 = ewsInfo.charInfoMsgNum;
        if (i6 <= 0) {
            Log.d(TAG, "ewsInfo.charInfoMsgNum is 0! return NULL!");
            return null;
        }
        ewsInfo.infoMsg = new TmdwInfoMsg[i6];
        int i7 = 0;
        do {
            Log.d(TAG, "leftInfoMsgNum:" + i6);
            Log.d(TAG, "Current InfoMsg Index:" + i6);
            Arrays.fill(iArr3, 0);
            iArr3[1] = i7;
            iArr3[2] = 2101;
            TVNativeWrapper.getInfo_native(b2, 4, iArr3);
            if (iArr3[0] != 0) {
                return null;
            }
            ewsInfo.infoMsg[i7] = new TmdwInfoMsg();
            ewsInfo.infoMsg[i7].charInfoMsgLen = (short) iArr3[1];
            a.b(a.a("Current InfoMsg Length:"), ewsInfo.infoMsg[i7].charInfoMsgLen, TAG);
            int i8 = 0;
            while (true) {
                tmdwInfoMsgArr = ewsInfo.infoMsg;
                if (i8 >= tmdwInfoMsgArr[i7].charInfoMsgLen + 2) {
                    break;
                }
                iArr3[i8] = iArr3[i8 + 2];
                i8++;
            }
            tmdwInfoMsgArr[i7].charInfoMsg = _toolConvertAsciiArrayToString(iArr3);
            StringBuilder sbA8 = a.a("Current InfoMsg:");
            sbA8.append(ewsInfo.infoMsg[i7].charInfoMsg);
            Log.d(TAG, sbA8.toString());
            i7++;
            i6 = (short) (i6 - 1);
        } while (i6 > 0);
        Log.d(TAG, "getEWSInfo exit");
        return ewsInfo;
    }

    public EwspaRet setLocationCode(byte[] bArr) {
        Log.d(TAG, "setLocationCode entered");
        int[] iArr = {0, bArr[0], bArr[1], bArr[2]};
        TVNativeWrapper.setInfo_native((byte) -1, 1, iArr);
        if (iArr[0] != 0) {
            return EwspaRet.EWSPA_RET_INTERNAL_ERROR;
        }
        Log.d(TAG, "setLocationCode exit");
        return EwspaRet.EWSPA_RET_OK;
    }
}
