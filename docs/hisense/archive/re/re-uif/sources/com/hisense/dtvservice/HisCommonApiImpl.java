package com.hisense.dtvservice;

import android.util.Log;
import vendor.hisense.dtv.dtvservice.V1_0.IHisCommonApi;

/* JADX INFO: loaded from: classes.dex */
public class HisCommonApiImpl {
    private static HisCommonApiImpl mCommonApi;
    private static IHisCommonApi mCommonServer;

    private HisCommonApiImpl() {
    }

    public static HisCommonApiImpl getInstance() {
        if (mCommonApi == null) {
            synchronized (HisCommonApiImpl.class) {
                if (mCommonApi == null) {
                    mCommonApi = new HisCommonApiImpl();
                }
            }
        }
        return mCommonApi;
    }

    public static boolean getHidlService() {
        try {
            IHisCommonApi service = IHisCommonApi.getService();
            mCommonServer = service;
            return service != null;
        } catch (Exception e) {
            Log.e("HISDTV@commonimpl_java", "get common HIDL Service exception" + e);
            return false;
        }
    }

    public static int getTunerMode() {
        int tunerMode = 0;
        if (getHidlService()) {
            try {
                tunerMode = mCommonServer.getTunerMode();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.d("HISDTV@commonimpl_java", "getTunerMode tunerMode = " + tunerMode);
        } else {
            Log.d("HISDTV@commonimpl_java", "getHidlService is false, call getTunerMode failed");
        }
        return tunerMode;
    }

    public static int setTunerMode(int i, boolean z) {
        Log.d("HISDTV@commonimpl_java", "call setTunerMode, tunerMode is:" + i + ", isNeedPlay:" + z);
        int tunerMode = -1;
        if (getHidlService()) {
            try {
                tunerMode = mCommonServer.setTunerMode(i, z);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.d("HISDTV@commonimpl_java", "setTunerMode ret = " + tunerMode);
        } else {
            Log.d("HISDTV@commonimpl_java", "getHidlService is false, call setTunerMode failed");
        }
        return tunerMode;
    }
}
