package com.hisense.tvmanager.atsc;

import android.util.Log;
import vendor.hisense.tv.hs_tv_service.V1_0.IHisTvService;

/* JADX INFO: loaded from: classes.dex */
public class Factory {
    private static Factory mFactory;
    private static IHisTvService mService;

    public static boolean getHidlService() {
        try {
            IHisTvService service = IHisTvService.getService();
            mService = service;
            return service != null;
        } catch (Exception e) {
            Log.e("Factory", "get HisTvService HIDL Service exception" + e);
            return false;
        }
    }

    public static Factory getInstance() {
        if (mFactory == null) {
            synchronized (Factory.class) {
                if (mFactory == null) {
                    mFactory = new Factory();
                }
            }
        }
        return mFactory;
    }

    public int presetChannel(int i) {
        if (!getHidlService()) {
            return -1;
        }
        try {
            return mService.presetChannel(i);
        } catch (Exception e) {
            Log.e("Factory", " error: " + e);
            return -1;
        }
    }
}
