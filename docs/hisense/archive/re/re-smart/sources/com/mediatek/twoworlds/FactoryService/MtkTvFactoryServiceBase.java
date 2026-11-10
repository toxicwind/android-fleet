package com.mediatek.twoworlds.FactoryService;

import android.util.Log;
import b.a.a.a.a;
import com.hisense.support.tv.HisenseTvFactoryApi;
import com.mediatek.twoworlds.tv.TVNativeWrapper;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvFactoryServiceBase {
    public static final String TAG = "MtkTvFactoryService";
    public static Map<Integer, Integer> internalMapTable = new HashMap();

    public int closeUARTSerial(int i) {
        if (!internalMapTable.containsKey(new Integer(i))) {
            a.a("the ", i, " serial has not been found", TAG);
            return -1;
        }
        int iIntValue = internalMapTable.get(new Integer(i)).intValue();
        Log.d(TAG, "closeUARTSerial the handle 0x" + iIntValue);
        int iCloseUARTSerial_native = TVNativeWrapper.closeUARTSerial_native(iIntValue);
        a.b("closeUARTSerial_native return ", iCloseUARTSerial_native, TAG);
        internalMapTable.remove(new Integer(i));
        return iCloseUARTSerial_native;
    }

    public int factoryCheckKey(String str) {
        Log.d(TAG, "Enter factoryCheckKey(keyType" + str + "\n");
        if (!str.equals(HisenseTvFactoryApi.KM_ATTEST_KEY_MTK)) {
            return TVNativeWrapper.factoryCheckKey_native(str);
        }
        try {
            return MtkTvKeymasterKeyAttestation.testEcAttestationChain() | MtkTvKeymasterKeyAttestation.testRsaAttestationChain();
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public int factoryCleanKey(String str) {
        Log.d(TAG, "Enter factoryCleanKey(keyType" + str + "\n");
        return TVNativeWrapper.factoryCleanKey_native(str);
    }

    public int factoryWriteKey(String str, String str2) {
        Log.d(TAG, "Enter factoryWriteKey(keyType" + str + ", srcPath=" + str2 + "\n");
        return TVNativeWrapper.factoryWriteKey_native(str, str2);
    }

    public int factoryWriteKeyFinish() {
        Log.d(TAG, "Enter factoryWriteKeyFinish\n");
        return TVNativeWrapper.factoryWriteKeyFinish_native();
    }

    public int getUARTSerialOperationMode(int i) {
        if (!internalMapTable.containsKey(new Integer(i))) {
            a.a("the ", i, " serial has not been found", TAG);
            return -1;
        }
        int[] iArr = new int[1];
        Log.d(TAG, "enter getUARTSerialSetting");
        int uARTSerialOperationMode_native = TVNativeWrapper.getUARTSerialOperationMode_native(internalMapTable.get(new Integer(i)).intValue(), iArr);
        if (uARTSerialOperationMode_native != 0) {
            a.b("getUARTSerialOperationMode ret= ", uARTSerialOperationMode_native, TAG);
        }
        a.b(a.a("leave getUARTSerialSetting operationMode[0]="), iArr[0], TAG);
        return iArr[0];
    }

    public int[] getUARTSerialSetting(int i) {
        if (!internalMapTable.containsKey(new Integer(i))) {
            a.a("the ", i, " serial has not been found", TAG);
            return null;
        }
        int[] iArr = new int[4];
        TVNativeWrapper.getUARTSerialSetting_native(internalMapTable.get(new Integer(i)).intValue(), iArr);
        Log.d(TAG, "getUARTSerialSetting uartSerialSetting[0] = " + iArr[0]);
        Log.d(TAG, "getUARTSerialSetting uartSerialSetting[1] = " + iArr[1]);
        Log.d(TAG, "getUARTSerialSetting uartSerialSetting[2] = " + iArr[2]);
        StringBuilder sb = new StringBuilder();
        sb.append("getUARTSerialSetting uartSerialSetting[3] = ");
        a.b(sb, iArr[3], TAG);
        return iArr;
    }

    public int openUARTSerial(int i, int[] iArr) {
        int[] iArr2 = new int[1];
        if (internalMapTable.containsKey(new Integer(i))) {
            a.a("the ", i, " serial has been opend", TAG);
            return 1;
        }
        Log.d(TAG, "enter openUARTSerial");
        StringBuilder sbA = a.a("openUARTSerial_native return ", TVNativeWrapper.openUARTSerial_native(i, iArr, iArr2), ", handle 0x");
        sbA.append(Integer.toHexString(iArr2[0]));
        Log.d(TAG, sbA.toString());
        return iArr2[0];
    }

    public int outputUARTSerial(int i, byte[] bArr) {
        if (!internalMapTable.containsKey(new Integer(i))) {
            a.a("the ", i, " serial has not been found", TAG);
            return -1;
        }
        Log.d(TAG, "enter outputUARTSerial, uartSerialID=" + i);
        Log.d(TAG, "enter outputUARTSerial, handle=" + internalMapTable.get(new Integer(i)).intValue());
        int iOutputUARTSerial_native = TVNativeWrapper.outputUARTSerial_native(internalMapTable.get(new Integer(i)).intValue(), bArr);
        if (iOutputUARTSerial_native != 0) {
            a.b("outputUARTSerial_native ret= ", iOutputUARTSerial_native, TAG);
        }
        Log.d(TAG, "leave outputUARTSerial");
        return iOutputUARTSerial_native;
    }

    public int setUARTSerialOperationMode(int i, int i2) {
        if (!internalMapTable.containsKey(new Integer(i))) {
            a.a("the ", i, " serial has not been found", TAG);
            return -1;
        }
        Log.d(TAG, "enter setUARTSerialSetting");
        int uARTSerialOperationMode_native = TVNativeWrapper.setUARTSerialOperationMode_native(internalMapTable.get(new Integer(i)).intValue(), i2);
        if (uARTSerialOperationMode_native != 0) {
            a.b("setUARTSerialOperationMode ret= ", uARTSerialOperationMode_native, TAG);
        }
        a.b("leave setUARTSerialOperationMode operationMode=", i2, TAG);
        return uARTSerialOperationMode_native;
    }

    public int setUARTSerialSetting(int i, int[] iArr) {
        if (!internalMapTable.containsKey(new Integer(i))) {
            a.a("the ", i, " serial has not been found", TAG);
            return -1;
        }
        StringBuilder sbA = a.a("setUARTSerialSetting uartSerialSetting[0] = ");
        sbA.append(iArr[0]);
        Log.d(TAG, sbA.toString());
        Log.d(TAG, "setUARTSerialSetting uartSerialSetting[1] = " + iArr[1]);
        Log.d(TAG, "setUARTSerialSetting uartSerialSetting[2] = " + iArr[2]);
        StringBuilder sb = new StringBuilder();
        sb.append("setUARTSerialSetting uartSerialSetting[3] = ");
        a.b(sb, iArr[3], TAG);
        int uARTSerialSetting_native = TVNativeWrapper.setUARTSerialSetting_native(internalMapTable.get(new Integer(i)).intValue(), iArr);
        if (uARTSerialSetting_native != 0) {
            a.b("setUARTSerialSetting ret= ", uARTSerialSetting_native, TAG);
        }
        return uARTSerialSetting_native;
    }
}
