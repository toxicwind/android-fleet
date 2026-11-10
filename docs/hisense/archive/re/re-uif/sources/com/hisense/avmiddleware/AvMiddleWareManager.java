package com.hisense.avmiddleware;

import java.util.HashMap;
import java.util.Map;
import vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi;

/* JADX INFO: loaded from: classes.dex */
public class AvMiddleWareManager {
    private static IHisSystemApi mSystemServer;
    private static final Map<Integer, Integer> mVirtualSourceMaps = new HashMap<Integer, Integer>() { // from class: com.hisense.avmiddleware.AvMiddleWareManager.1
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

    public static boolean getHidlService() {
        try {
            IHisSystemApi service = IHisSystemApi.getService();
            mSystemServer = service;
            return service != null;
        } catch (Exception e) {
            HisLogger.e("HISMW@AvMiddleWareManager", "get System HIDL Service exception" + e);
            return false;
        }
    }

    public static int setCurrentSource(int i) {
        HisLogger.d("HISMW@AvMiddleWareManager", "setCurrentSource val:" + i);
        int currentSource = -1;
        if (getHidlService()) {
            try {
                currentSource = mSystemServer.setCurrentSource(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@AvMiddleWareManager", "setCurrentSource ret = " + currentSource);
        }
        return currentSource;
    }

    public static int setCurrentVirtualSource(int i) {
        HisLogger.d("HISMW@AvMiddleWareManager", "setCurrentVirtualSource val:" + i);
        Integer num = mVirtualSourceMaps.get(Integer.valueOf(i));
        if (num == null) {
            return -1;
        }
        return setCurrentSource(num.intValue());
    }

    public static int setFactoryMode(int i) {
        HisLogger.d("HISMW@AvMiddleWareManager", "setFactoryMode val:" + i);
        int factoryMode = -1;
        if (getHidlService()) {
            try {
                factoryMode = mSystemServer.setFactoryMode(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@AvMiddleWareManager", "setFactoryMode ret = " + factoryMode);
        }
        return factoryMode;
    }

    public static int resetFactoryOKSetting() {
        HisLogger.d("HISMW@AvMiddleWareManager", "resetFactoryOKSetting ");
        int iResetFactoryOKSetting = -1;
        if (getHidlService()) {
            try {
                iResetFactoryOKSetting = mSystemServer.resetFactoryOKSetting();
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@AvMiddleWareManager", "resetFactoryOKSetting ret = " + iResetFactoryOKSetting);
        }
        return iResetFactoryOKSetting;
    }

    public static int resetFactoryUnprotectedSetting() {
        HisLogger.d("HISMW@AvMiddleWareManager", "resetFactoryUnprotectedSetting ");
        int iResetFactoryUnprotectedSetting = -1;
        if (getHidlService()) {
            try {
                iResetFactoryUnprotectedSetting = mSystemServer.resetFactoryUnprotectedSetting();
            } catch (Exception e) {
                e.printStackTrace();
            }
            HisLogger.d("HISMW@AvMiddleWareManager", "resetFactoryUnprotectedSetting ret = " + iResetFactoryUnprotectedSetting);
        }
        return iResetFactoryUnprotectedSetting;
    }
}
