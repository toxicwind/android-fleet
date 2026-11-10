package com.mediatek.twoworlds.tv;

import android.util.Log;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvDrmpBase {
    public static final String TAG = "MtkTvDrmpBase";

    public enum DrmpRet {
        DRMP_RET_OK,
        DRMP_RET_NOT_SUPPORT,
        DRMP_RET_ERROR
    }

    public MtkTvDrmpBase() {
        Log.d(TAG, "MtkTvDrmpBase object created\n");
    }

    public DrmpRet DrmpInitQDAForQdc() {
        DrmpRet drmpRet = DrmpRet.DRMP_RET_OK;
        Log.d(TAG, "enter nitQDAForQdc retTVNativeWrapper: \n");
        int iDrmpInitQDAForQdc_native = TVNativeWrapper.drmpInitQDAForQdc_native();
        DrmpRet drmpRet2 = iDrmpInitQDAForQdc_native >= 0 ? DrmpRet.DRMP_RET_OK : DrmpRet.DRMP_RET_ERROR;
        Log.d(TAG, "- drmpInitQDAForQdc retTVNativeWrapper:" + iDrmpInitQDAForQdc_native + ", ret:" + drmpRet2 + "\n");
        return drmpRet2;
    }

    public DrmpRet DrmpKeyUpdate() {
        DrmpRet drmpRet = DrmpRet.DRMP_RET_OK;
        int iDrmpKeyUpdate_native = TVNativeWrapper.drmpKeyUpdate_native();
        DrmpRet drmpRet2 = iDrmpKeyUpdate_native >= 0 ? DrmpRet.DRMP_RET_OK : DrmpRet.DRMP_RET_ERROR;
        Log.d(TAG, "- DrmpKeyUpdate retTVNativeWrapper:" + iDrmpKeyUpdate_native + ", ret:" + drmpRet2 + "\n");
        return drmpRet2;
    }

    public DrmpRet DrmpQdaFactoryReset() {
        DrmpRet drmpRet = DrmpRet.DRMP_RET_OK;
        Log.d(TAG, "enter DrmpQdaFactoryReset retTVNativeWrapper: \n");
        int iDrmpQdaFactoryReset_native = TVNativeWrapper.drmpQdaFactoryReset_native();
        DrmpRet drmpRet2 = iDrmpQdaFactoryReset_native >= 0 ? DrmpRet.DRMP_RET_OK : DrmpRet.DRMP_RET_ERROR;
        Log.d(TAG, "- drmpQdaFactoryReset retTVNativeWrapper:" + iDrmpQdaFactoryReset_native + ", ret:" + drmpRet2 + "\n");
        return drmpRet2;
    }

    public DrmpRet DrmpQdaFactoryResetWithReturn() {
        DrmpRet drmpRet = DrmpRet.DRMP_RET_OK;
        Log.d(TAG, "enter DrmpQdaFactoryResetWithReturn retTVNativeWrapper: \n");
        int iDrmpQdaFactoryResetWithReturn_native = TVNativeWrapper.drmpQdaFactoryResetWithReturn_native();
        DrmpRet drmpRet2 = iDrmpQdaFactoryResetWithReturn_native >= 0 ? DrmpRet.DRMP_RET_OK : DrmpRet.DRMP_RET_ERROR;
        Log.d(TAG, "- DrmpQdaFactoryResetWithReturn retTVNativeWrapper:" + iDrmpQdaFactoryResetWithReturn_native + ", ret:" + drmpRet2 + "\n");
        return drmpRet2;
    }

    public DrmpRet DrmpQdaForceCleanup() {
        DrmpRet drmpRet = DrmpRet.DRMP_RET_OK;
        Log.d(TAG, "enter DrmpQdaForceCleanup retTVNativeWrapper: \n");
        int iDrmpQdaForceCleanup_native = TVNativeWrapper.drmpQdaForceCleanup_native();
        DrmpRet drmpRet2 = iDrmpQdaForceCleanup_native >= 0 ? DrmpRet.DRMP_RET_OK : DrmpRet.DRMP_RET_ERROR;
        Log.d(TAG, "- DrmpQdaForceCleanup retTVNativeWrapper:" + iDrmpQdaForceCleanup_native + ", ret:" + drmpRet2 + "\n");
        return drmpRet2;
    }

    public DrmpRet DrmpQdaStop() {
        DrmpRet drmpRet = DrmpRet.DRMP_RET_OK;
        Log.d(TAG, "enter DrmpQdaStop retTVNativeWrapper: \n");
        int iDrmpQdaStop_native = TVNativeWrapper.drmpQdaStop_native();
        DrmpRet drmpRet2 = iDrmpQdaStop_native >= 0 ? DrmpRet.DRMP_RET_OK : DrmpRet.DRMP_RET_ERROR;
        Log.d(TAG, "- DrmpQdaStop retTVNativeWrapper:" + iDrmpQdaStop_native + ", ret:" + drmpRet2 + "\n");
        return drmpRet2;
    }

    public ArrayList<Byte> getDrmpDeviceID() {
        ArrayList<Byte> arrayList = new ArrayList<>();
        byte[] drmpDeviceID_native = TVNativeWrapper.getDrmpDeviceID_native();
        if (drmpDeviceID_native != null) {
            for (byte b2 : drmpDeviceID_native) {
                arrayList.add(Byte.valueOf(b2));
            }
        }
        Log.i(TAG, "getDrmpDeviceID arrayListData:" + arrayList);
        Log.i(TAG, "getDrmpDeviceID data:" + drmpDeviceID_native);
        return arrayList;
    }
}
