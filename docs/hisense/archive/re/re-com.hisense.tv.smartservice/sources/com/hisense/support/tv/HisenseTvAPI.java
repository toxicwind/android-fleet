package com.hisense.support.tv;

import android.content.Context;
import android.util.Log;
import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class HisenseTvAPI extends HisenseTvAPIBase {
    public static HisenseTvAPI hisenseTvAPI;

    public HisenseTvAPI(Context context) {
        super(context);
    }

    public static HisenseTvAPI getInstance(Context context) {
        HisenseTvAPI hisenseTvAPI2 = hisenseTvAPI;
        if (hisenseTvAPI2 != null) {
            return hisenseTvAPI2;
        }
        hisenseTvAPI = new HisenseTvAPI(context);
        return hisenseTvAPI;
    }

    public int setAndroidWorldInfoToLinux(int i, int i2) {
        Log.d(HisenseTvAPIBase.TAG, "Enter setAndroidWorldInfoToLinux\n");
        synchronized (HisenseTvAPI.class) {
        }
        a.a("Leave setAndroidWorldInfoToLinux, return value = ", 0, "\n", HisenseTvAPIBase.TAG);
        return 0;
    }
}
