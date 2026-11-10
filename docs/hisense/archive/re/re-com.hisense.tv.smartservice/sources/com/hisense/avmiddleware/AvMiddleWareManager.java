package com.hisense.avmiddleware;

import b.a.a.a.a;
import java.util.HashMap;
import java.util.Map;
import vendor.hisense.mw.hs_av_service.V1_0.IHisAVApiCallback;
import vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi;

/* JADX INFO: loaded from: classes2.dex */
public class AvMiddleWareManager {
    public static final int HIS_SOURCE_APP_AMAZON = 11;
    public static final int HIS_SOURCE_APP_NETFLIX = 10;
    public static final int HIS_SOURCE_APP_VUDU = 13;
    public static final int HIS_SOURCE_APP_YOUTUBE = 12;
    public static final int HIS_SOURCE_DMP = 8;
    public static final int HIS_SOURCE_LIVETV = 14;
    public static final int HIS_SOURCE_NETWORK = 9;
    public static final int HIS_SOURCE_VIRTUL_DEFAULT = 8;
    public static final String TAG = "HISMW@AvMiddleWareManager";
    public static int mGetParamVal = -1;
    public static HisAVCallbackImpl mHisAVCallback = null;
    public static int mReturnVal = -1;
    public static IHisSystemApi mSystemServer;
    public static final Map<Integer, Integer> mVirtualSourceMaps = new HashMap<Integer, Integer>() { // from class: com.hisense.avmiddleware.AvMiddleWareManager.1
        {
            put(1, 10);
            put(2, 13);
            put(3, 11);
            put(4, 12);
            put(5, 9);
            put(6, 8);
            put(7, 14);
        }
    };

    public static int backupStoreSetting() {
        HisLogger.d(TAG, "backupStoreSetting");
        int iBackupStoreSetting = -1;
        if (getHidlService()) {
            try {
                iBackupStoreSetting = mSystemServer.backupStoreSetting();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("backupStoreSetting ret = ", iBackupStoreSetting, TAG);
        }
        return iBackupStoreSetting;
    }

    public static int backupUserSetting(String str) {
        HisLogger.d(TAG, "backupUserSetting, path: '" + str + "'");
        int iBackupUserSetting = -1;
        if (getHidlService()) {
            try {
                iBackupUserSetting = mSystemServer.backupUserSetting(str);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("backupUserSetting ret = ", iBackupUserSetting, TAG);
        }
        return iBackupUserSetting;
    }

    public static int getCurrentSource() {
        HisLogger.d(TAG, "getCurrentSource");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mSystemServer.getCurrentSource(new IHisSystemApi.getCurrentSourceCallback() { // from class: com.hisense.avmiddleware.AvMiddleWareManager.2
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi.getCurrentSourceCallback
                    public void onValues(int i, int i2) {
                        AvMiddleWareManager.mReturnVal = i;
                        AvMiddleWareManager.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getCurrentSource ret =");
                        sbA.append(AvMiddleWareManager.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, AvMiddleWareManager.mGetParamVal, AvMiddleWareManager.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getFactoryMode() {
        HisLogger.d(TAG, "getFactoryMode");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mSystemServer.getFactoryMode(new IHisSystemApi.getFactoryModeCallback() { // from class: com.hisense.avmiddleware.AvMiddleWareManager.4
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi.getFactoryModeCallback
                    public void onValues(int i, int i2) {
                        AvMiddleWareManager.mReturnVal = i;
                        AvMiddleWareManager.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getFactoryMode ret =");
                        sbA.append(AvMiddleWareManager.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, AvMiddleWareManager.mGetParamVal, AvMiddleWareManager.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int getHidlAVMiddlewareEnable() {
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mSystemServer.getHidlAVMiddlewareEnable(new IHisSystemApi.getHidlAVMiddlewareEnableCallback() { // from class: com.hisense.avmiddleware.AvMiddleWareManager.5
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi.getHidlAVMiddlewareEnableCallback
                    public void onValues(int i, int i2) {
                        AvMiddleWareManager.mReturnVal = i;
                        AvMiddleWareManager.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getHidlAVMiddlewareEnable ret =");
                        sbA.append(AvMiddleWareManager.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, AvMiddleWareManager.mGetParamVal, AvMiddleWareManager.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static boolean getHidlService() {
        try {
            mSystemServer = IHisSystemApi.getService();
            return mSystemServer != null;
        } catch (Exception e2) {
            HisLogger.e(TAG, "get System HIDL Service exception" + e2);
            return false;
        }
    }

    public static int getUserMode() {
        HisLogger.d(TAG, "getUserMode");
        mGetParamVal = -1;
        mReturnVal = -1;
        if (getHidlService()) {
            try {
                mSystemServer.getUserMode(new IHisSystemApi.getUserModeCallback() { // from class: com.hisense.avmiddleware.AvMiddleWareManager.3
                    @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi.getUserModeCallback
                    public void onValues(int i, int i2) {
                        AvMiddleWareManager.mReturnVal = i;
                        AvMiddleWareManager.mGetParamVal = i2;
                        StringBuilder sbA = a.a("getUserMode ret =");
                        sbA.append(AvMiddleWareManager.mReturnVal);
                        sbA.append(",value = ");
                        a.c(sbA, AvMiddleWareManager.mGetParamVal, AvMiddleWareManager.TAG);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mGetParamVal;
    }

    public static int registerCallback(IHisAVApiCallback.Stub stub) {
        HisLogger.d(TAG, "registerCallback");
        int iRegisterCallback = -1;
        if (getHidlService()) {
            try {
                iRegisterCallback = mSystemServer.registerCallback(stub);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("registerCallback ret = ", iRegisterCallback, TAG);
        }
        return iRegisterCallback;
    }

    public static int registerCallbackWithPid(IHisAVApiCallback.Stub stub, int i) {
        HisLogger.d(TAG, "registerCallback" + i);
        int iRegisterCallbackWithPid = -1;
        if (getHidlService()) {
            try {
                iRegisterCallbackWithPid = mSystemServer.registerCallbackWithPid(stub, i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("registerCallback ret = ", iRegisterCallbackWithPid, TAG);
        }
        return iRegisterCallbackWithPid;
    }

    public static int resetFactoryOKSetting() {
        HisLogger.d(TAG, "resetFactoryOKSetting ");
        int iResetFactoryOKSetting = -1;
        if (getHidlService()) {
            try {
                iResetFactoryOKSetting = mSystemServer.resetFactoryOKSetting();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("resetFactoryOKSetting ret = ", iResetFactoryOKSetting, TAG);
        }
        return iResetFactoryOKSetting;
    }

    public static int resetFactoryUnprotectedSetting() {
        HisLogger.d(TAG, "resetFactoryUnprotectedSetting ");
        int iResetFactoryUnprotectedSetting = -1;
        if (getHidlService()) {
            try {
                iResetFactoryUnprotectedSetting = mSystemServer.resetFactoryUnprotectedSetting();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("resetFactoryUnprotectedSetting ret = ", iResetFactoryUnprotectedSetting, TAG);
        }
        return iResetFactoryUnprotectedSetting;
    }

    public static int resetStoreSetting(int i) {
        HisLogger.d(TAG, "resetStoreSetting ");
        int iResetStoreSetting = -1;
        if (getHidlService()) {
            try {
                iResetStoreSetting = mSystemServer.resetStoreSetting(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("resetStoreSetting ret = ", iResetStoreSetting, TAG);
        }
        return iResetStoreSetting;
    }

    public static int resetUserSetting() {
        HisLogger.d(TAG, "resetUserSetting ");
        int iResetUserSetting = -1;
        if (getHidlService()) {
            try {
                iResetUserSetting = mSystemServer.resetUserSetting();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("resetUserSetting ret = ", iResetUserSetting, TAG);
        }
        return iResetUserSetting;
    }

    public static int restoreUserSetting(String str) {
        HisLogger.d(TAG, "restoreUserSetting, path: '" + str + "'");
        int iRestoreUserSetting = -1;
        if (getHidlService()) {
            try {
                iRestoreUserSetting = mSystemServer.restoreUserSetting(str);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("restoreUserSetting ret = ", iRestoreUserSetting, TAG);
        }
        return iRestoreUserSetting;
    }

    public static int setCurrentSource(int i) {
        HisLogger.d(TAG, "setCurrentSource val:" + i);
        int currentSource = -1;
        if (getHidlService()) {
            try {
                currentSource = mSystemServer.setCurrentSource(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setCurrentSource ret = ", currentSource, TAG);
        }
        return currentSource;
    }

    public static int setCurrentVirtualSource(int i) {
        a.c("setCurrentVirtualSource val:", i, TAG);
        Integer num = mVirtualSourceMaps.get(Integer.valueOf(i));
        if (num == null) {
            return -1;
        }
        return setCurrentSource(num.intValue());
    }

    public static int setFactoryMode(int i) {
        HisLogger.d(TAG, "setFactoryMode val:" + i);
        int factoryMode = -1;
        if (getHidlService()) {
            try {
                factoryMode = mSystemServer.setFactoryMode(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setFactoryMode ret = ", factoryMode, TAG);
        }
        return factoryMode;
    }

    public static void setScreenOn(int i) {
        HisLogger.d(TAG, "setScreenOn val:" + i);
        if (getHidlService()) {
            try {
                mSystemServer.setScreenOn(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            HisLogger.d(TAG, "setScreenOn done");
        }
    }

    public static int setUserMode(int i) {
        HisLogger.d(TAG, "setUserMode val:" + i);
        int userMode = -1;
        if (getHidlService()) {
            try {
                userMode = mSystemServer.setUserMode(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("setUserMode ret = ", userMode, TAG);
        }
        return userMode;
    }

    public static int unRegisterCallback(IHisAVApiCallback.Stub stub) {
        HisLogger.d(TAG, "unRegisterCallback");
        int iUnRegisterCallback = -1;
        if (getHidlService()) {
            try {
                iUnRegisterCallback = mSystemServer.unRegisterCallback(stub);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("unRegisterCallback ret = ", iUnRegisterCallback, TAG);
        }
        return iUnRegisterCallback;
    }

    public static int unRegisterCallbackWithPid(int i) {
        HisLogger.d(TAG, "unRegisterCallback, pid = " + i);
        int iUnRegisterCallbackWithPid = -1;
        if (getHidlService()) {
            try {
                iUnRegisterCallbackWithPid = mSystemServer.unRegisterCallbackWithPid(i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("unRegisterCallback ret = ", iUnRegisterCallbackWithPid, TAG);
        }
        return iUnRegisterCallbackWithPid;
    }

    public static int registerCallback() {
        HisLogger.d(TAG, "registerCallback");
        int iRegisterCallback = -1;
        if (getHidlService()) {
            try {
                mHisAVCallback = new HisAVCallbackImpl();
                iRegisterCallback = mSystemServer.registerCallback(mHisAVCallback);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a.c("registerCallback ret = ", iRegisterCallback, TAG);
        }
        return iRegisterCallback;
    }
}
