package com.mediatek.twoworlds.tv;

import android.util.Log;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvEWSPABase {
    private static final int EWS_GET_HAS_EWS = 2;
    private static final int EWS_GET_INFO_MSG_NUM = 3;
    private static final int EWS_GET_MORE_INFO_MSG = 4;
    private static final int EWS_GET_NONE = 0;
    private static final int EWS_GET_SVC_INFO = 1;
    private static final int EWS_SET_LOCATION_CODE = 1;
    private static final int EWS_SET_NONE = 0;
    private static final int MAX_INFO_MSG_NUM_ONCE = 1;
    private static final int MAX_INFO_MSG_STRING_LEN = 2100;
    private static final int MAX_STRING_LEN = 256;
    public static final String TAG = "MtkTvEWSPABase";

    public enum EwspaRet {
        EWSPA_RET_OK,
        EWSPA_RET_INTERNAL_ERROR
    }

    public MtkTvEWSPABase() {
        Log.d(TAG, "MtkTvEWSPABase object created");
    }

    public class TmdwInfoMsg {
        public String charInfoMsg;
        public short charInfoMsgLen;

        public TmdwInfoMsg() {
        }
    }

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

    private String _toolConvertAsciiArrayToString(int[] iArr) {
        StringBuilder sb = new StringBuilder();
        if (iArr == null) {
            return null;
        }
        byte[] bArr = new byte[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            bArr[i] = (byte) iArr[i];
            Log.d(TAG, ((int) bArr[i]) + "\n");
        }
        sb.append(new String(bArr));
        return sb.toString();
    }

    public void createMonitorInst(byte b) {
        Log.d(TAG, "createMonitorInst entered, monitorType:" + ((int) b));
        TVNativeWrapper.createMonitorInst_native(b);
        Log.d(TAG, "createMonitorInst exit");
    }

    public void deleteMonitorInst(byte b) {
        Log.d(TAG, "deleteMonitorInst entered, monitorType:" + ((int) b));
        TVNativeWrapper.deleteMonitorInst_native(b);
        Log.d(TAG, "deleteMonitorInst exit");
    }

    public EwsInfo getEWSInfo(byte b) {
        TmdwInfoMsg[] tmdwInfoMsgArr;
        int[] iArr = new int[256];
        int[] iArr2 = new int[3];
        EwsInfo ewsInfo = new EwsInfo();
        Log.d(TAG, "getEWSInfo entered");
        TVNativeWrapper.getInfo_native(b, 3, iArr2);
        if (iArr2[0] != 0) {
            return null;
        }
        ewsInfo.charInfoMsgNum = (short) iArr2[1];
        Log.d(TAG, "infoMsgNum:" + ((int) ewsInfo.charInfoMsgNum));
        Log.d(TAG, "MaxLen[2100]:2100");
        Log.d(TAG, "ews information total len:" + MAX_INFO_MSG_STRING_LEN);
        int[] iArr3 = new int[MAX_INFO_MSG_STRING_LEN];
        iArr3[1] = ewsInfo.charInfoMsgNum;
        iArr3[2] = 1;
        TVNativeWrapper.getInfo_native(b, 1, iArr3);
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
        int i = 13;
        ewsInfo.charLocationCodeLen = (byte) iArr3[12];
        Log.d(TAG, "disasterCode:" + ((int) ewsInfo.disasterCode));
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
        int i2 = 0;
        while (i2 < 256) {
            iArr[i2] = iArr3[i + i2];
            if (i2 < 20) {
                Log.d(TAG, "intEwsString[]:" + iArr[i2]);
            }
            i2++;
            i = 13;
        }
        ewsInfo.charDisasterCode = _toolConvertAsciiArrayToString(iArr);
        Log.d(TAG, "ewsInfo.charDisasterCode:" + ewsInfo.charDisasterCode);
        Log.d(TAG, "idx:269");
        for (int i3 = 0; i3 < 256; i3++) {
            iArr[i3] = iArr3[269 + i3];
        }
        ewsInfo.charDisasterDate = _toolConvertAsciiArrayToString(iArr);
        Log.d(TAG, "ewsInfo.charDisasterDate:" + ewsInfo.charDisasterDate);
        Log.d(TAG, "idx:525");
        for (int i4 = 0; i4 < 256; i4++) {
            iArr[i4] = iArr3[525 + i4];
        }
        ewsInfo.charDisasterPosition = _toolConvertAsciiArrayToString(iArr);
        Log.d(TAG, "ewsInfo.charDisasterPosition:" + ewsInfo.charDisasterPosition);
        Log.d(TAG, "idx:781");
        for (int i5 = 0; i5 < 256; i5++) {
            iArr[i5] = iArr3[781 + i5];
        }
        ewsInfo.charDisasterCharacterstic = _toolConvertAsciiArrayToString(iArr);
        Log.d(TAG, "ewsInfo.charDisasterCharacterstic:" + ewsInfo.charDisasterCharacterstic);
        Log.d(TAG, "idx:1037");
        for (int i6 = 0; i6 < 256; i6++) {
            iArr[i6] = iArr3[1037 + i6];
        }
        ewsInfo.charLocationCode = _toolConvertAsciiArrayToString(iArr);
        Log.d(TAG, "ewsInfo.charLocationCode:" + ewsInfo.charLocationCode);
        Log.d(TAG, "idx:1293");
        int i7 = ewsInfo.charInfoMsgNum;
        if (i7 > 0) {
            ewsInfo.infoMsg = new TmdwInfoMsg[i7];
            int i8 = 0;
            do {
                Log.d(TAG, "leftInfoMsgNum:" + i7);
                Log.d(TAG, "Current InfoMsg Index:" + i7);
                Arrays.fill(iArr3, 0);
                iArr3[1] = i8;
                iArr3[2] = 2101;
                TVNativeWrapper.getInfo_native(b, 4, iArr3);
                if (iArr3[0] != 0) {
                    return null;
                }
                ewsInfo.infoMsg[i8] = new TmdwInfoMsg();
                ewsInfo.infoMsg[i8].charInfoMsgLen = (short) iArr3[1];
                Log.d(TAG, "Current InfoMsg Length:" + ((int) ewsInfo.infoMsg[i8].charInfoMsgLen));
                int i9 = 0;
                while (true) {
                    tmdwInfoMsgArr = ewsInfo.infoMsg;
                    if (i9 >= tmdwInfoMsgArr[i8].charInfoMsgLen + 2) {
                        break;
                    }
                    iArr3[i9] = iArr3[i9 + 2];
                    i9++;
                }
                tmdwInfoMsgArr[i8].charInfoMsg = _toolConvertAsciiArrayToString(iArr3);
                Log.d(TAG, "Current InfoMsg:" + ewsInfo.infoMsg[i8].charInfoMsg);
                i8++;
                i7 = (short) (i7 + (-1));
            } while (i7 > 0);
            Log.d(TAG, "getEWSInfo exit");
            return ewsInfo;
        }
        Log.d(TAG, "ewsInfo.charInfoMsgNum is 0! return NULL!");
        return null;
    }

    public boolean bWithEWSInfo(byte b) {
        int[] iArr = new int[2];
        Log.d(TAG, "bWithEWSInfo entered");
        TVNativeWrapper.getInfo_native(b, 2, iArr);
        if (iArr[0] != 0) {
            return false;
        }
        boolean z = iArr[1] == 1;
        Log.d(TAG, "bWithEWSInfo exit");
        return z;
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
