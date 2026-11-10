package com.hisense.dtvservice;

import android.util.Log;
import java.util.ArrayList;
import vendor.hisense.dtv.dtvservice.V1_0.IHisPlayApi;

/* JADX INFO: loaded from: classes.dex */
public class HisPlayApiImpl {
    private static HisPlayApiImpl mPlayApi;
    private static IHisPlayApi mPlayServer;

    static {
        new ArrayList();
        mPlayServer = null;
        mPlayApi = null;
    }

    private HisPlayApiImpl() {
    }

    public static HisPlayApiImpl getInstance() {
        if (mPlayApi == null) {
            synchronized (HisPlayApiImpl.class) {
                if (mPlayApi == null) {
                    mPlayApi = new HisPlayApiImpl();
                }
            }
        }
        return mPlayApi;
    }

    private static boolean getHidlService() {
        try {
            IHisPlayApi service = IHisPlayApi.getService();
            mPlayServer = service;
            return service != null;
        } catch (Exception e) {
            Log.e("HISDTV@playimpl_java", "get play HIDL Service exception" + e);
            return false;
        }
    }

    public int playChannelByChannelKey(int i, String str, int i2) {
        int iPlayChannelByChKey = -1;
        if (getHidlService()) {
            try {
                iPlayChannelByChKey = mPlayServer.playChannelByChKey(i, str, i2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.d("HISDTV@playimpl_java", "call playChannelByChKey ret = " + iPlayChannelByChKey);
        } else {
            Log.d("HISDTV@playimpl_java", "getHidlService is false, call playChannelByChKey failed");
        }
        return iPlayChannelByChKey;
    }

    public String getCurrentChannelKey() {
        String currentChannelKey = "";
        if (getHidlService()) {
            try {
                currentChannelKey = mPlayServer.getCurrentChannelKey();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.d("HISDTV@playimpl_java", "call getCurrentChannelKey channelKey = " + currentChannelKey);
        } else {
            Log.d("HISDTV@playimpl_java", "getHidlService is false, call getCurrentChannelKey failed");
        }
        return currentChannelKey;
    }
}
