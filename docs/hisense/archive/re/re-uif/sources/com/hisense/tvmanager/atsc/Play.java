package com.hisense.tvmanager.atsc;

import android.util.Log;
import vendor.hisense.tv.hs_tv_service.V1_0.IHisTvService;

/* JADX INFO: loaded from: classes.dex */
public class Play {
    private static Play mPlay;
    private static IHisTvService mService;

    public static boolean getHidlService() {
        try {
            IHisTvService service = IHisTvService.getService();
            mService = service;
            return service != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private Play() {
    }

    public static Play getInstance() {
        if (mPlay == null) {
            synchronized (Play.class) {
                if (mPlay == null) {
                    mPlay = new Play();
                }
            }
        }
        return mPlay;
    }

    public int startPlay(String str) {
        if (getHidlService()) {
            try {
                return mService.startPlay(str, 0, 0);
            } catch (Exception e) {
                Log.e("Play", "startPlay error: " + e);
                return 1;
            }
        }
        Log.d("Play", "getHidlService is false, call startPlay failed");
        return 1;
    }

    public String getCurrentPlayChannel() {
        if (getHidlService()) {
            try {
                return mService.getCurrentPlayChannel();
            } catch (Exception e) {
                Log.e("Play", " error: " + e);
                return null;
            }
        }
        Log.d("Play", "getHidlService is false, call getCurrentPlayChannel failed");
        return null;
    }
}
