package com.mediatek.twoworlds.tv;

import android.util.Log;
import com.mediatek.twoworlds.tv.common.MtkTvExceptionBase;
import com.mediatek.twoworlds.tv.model.MtkTvUpgradeDeliveryTypeBase;
import com.mediatek.twoworlds.tv.model.MtkTvUpgradeFirmwareInfoBase;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvUpgradeBase {
    public static final String TAG = "MtkTvUpgrageBase";

    public int startUpgrade(MtkTvUpgradeDeliveryTypeBase mtkTvUpgradeDeliveryTypeBase, boolean z) throws MtkTvExceptionBase {
        int iStartUpgrade_native;
        Log.d(TAG, "+ startUpgrade(" + mtkTvUpgradeDeliveryTypeBase + ", " + z);
        try {
            synchronized (this) {
                iStartUpgrade_native = TVNativeWrapper.startUpgrade_native(mtkTvUpgradeDeliveryTypeBase, z);
            }
            if (iStartUpgrade_native != 0) {
                throw new MtkTvExceptionBase(iStartUpgrade_native, "TVNativeWrapper.startUpgrade_native fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(TAG, "- startUpgrade(" + mtkTvUpgradeDeliveryTypeBase + ", " + z);
        return 0;
    }

    public int leaveUpgrade(MtkTvUpgradeDeliveryTypeBase mtkTvUpgradeDeliveryTypeBase) throws MtkTvExceptionBase {
        int iStartUpgrade_native;
        Log.d(TAG, "+ leaveUpgrade(" + mtkTvUpgradeDeliveryTypeBase);
        try {
            synchronized (this) {
                iStartUpgrade_native = TVNativeWrapper.startUpgrade_native(mtkTvUpgradeDeliveryTypeBase, false);
            }
            if (iStartUpgrade_native != 0) {
                throw new MtkTvExceptionBase(iStartUpgrade_native, "TVNativeWrapper.startUpgrade_native fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(TAG, "- leaveUpgrade(" + mtkTvUpgradeDeliveryTypeBase);
        return 0;
    }

    public int triggerUpgrade(MtkTvUpgradeDeliveryTypeBase mtkTvUpgradeDeliveryTypeBase) {
        int iTriggerUpgrade_native;
        Log.d(TAG, "+ triggerUpgrade(" + mtkTvUpgradeDeliveryTypeBase + ")");
        try {
            synchronized (this) {
                iTriggerUpgrade_native = TVNativeWrapper.triggerUpgrade_native(mtkTvUpgradeDeliveryTypeBase);
            }
            if (iTriggerUpgrade_native != 0) {
                throw new MtkTvExceptionBase(iTriggerUpgrade_native, "TVNativeWrapper.triggerUpgrade_native fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(TAG, "- triggerUpgrade(" + mtkTvUpgradeDeliveryTypeBase + ")");
        return 0;
    }

    public int getFirmwareInfo(MtkTvUpgradeFirmwareInfoBase mtkTvUpgradeFirmwareInfoBase) {
        int firmwareInfo_native;
        Log.d(TAG, "+ getFirmwareInfo()");
        try {
            synchronized (this) {
                firmwareInfo_native = TVNativeWrapper.getFirmwareInfo_native(mtkTvUpgradeFirmwareInfoBase);
            }
            return firmwareInfo_native;
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "- getFirmwareInfo()");
            return -1;
        }
    }

    public int startDownloadFirmware(MtkTvUpgradeDeliveryTypeBase mtkTvUpgradeDeliveryTypeBase, String str, String str2) {
        int iStartDownloadFirmware_native;
        Log.d(TAG, "+ startDownloadFirmware()");
        try {
            synchronized (this) {
                iStartDownloadFirmware_native = TVNativeWrapper.startDownloadFirmware_native(mtkTvUpgradeDeliveryTypeBase, str, str2);
            }
            return iStartDownloadFirmware_native;
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "- startDownloadFirmware()");
            return -1;
        }
    }

    public int cancelDownloadFirmware(MtkTvUpgradeDeliveryTypeBase mtkTvUpgradeDeliveryTypeBase) {
        int iCancelDownloadFirmware_native;
        Log.d(TAG, "+ cancelDownloadFirmware()");
        try {
            synchronized (this) {
                iCancelDownloadFirmware_native = TVNativeWrapper.cancelDownloadFirmware_native(mtkTvUpgradeDeliveryTypeBase);
            }
            return iCancelDownloadFirmware_native;
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "- cancelDownloadFirmware()");
            return -1;
        }
    }

    public int startRebootUpgrade(MtkTvUpgradeDeliveryTypeBase mtkTvUpgradeDeliveryTypeBase) {
        int iStartRebootUpgrade_native;
        Log.d(TAG, "+ startRebootUpgrade()");
        try {
            synchronized (this) {
                iStartRebootUpgrade_native = TVNativeWrapper.startRebootUpgrade_native(mtkTvUpgradeDeliveryTypeBase);
            }
            return iStartRebootUpgrade_native;
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "- startRebootUpgrade()");
            return -1;
        }
    }

    public int queryUpgradeResult(MtkTvUpgradeDeliveryTypeBase mtkTvUpgradeDeliveryTypeBase) {
        int iQueryUpgradeResult_native;
        Log.d(TAG, "+ queryUpgradeResult()");
        try {
            synchronized (this) {
                iQueryUpgradeResult_native = TVNativeWrapper.queryUpgradeResult_native(mtkTvUpgradeDeliveryTypeBase);
            }
            return iQueryUpgradeResult_native;
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "- queryUpgradeResult()");
            return -1;
        }
    }
}
