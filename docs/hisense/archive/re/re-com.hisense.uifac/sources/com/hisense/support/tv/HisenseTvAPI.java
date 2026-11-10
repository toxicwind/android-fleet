package com.hisense.support.tv;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class HisenseTvAPI extends HisenseTvAPIBase {
    private static HisenseTvAPI hisenseTvAPI;

    private HisenseTvAPI(Context context) {
        super(context);
    }

    public static HisenseTvAPI getInstance(Context context) {
        HisenseTvAPI hisenseTvAPI2 = hisenseTvAPI;
        if (hisenseTvAPI2 != null) {
            return hisenseTvAPI2;
        }
        HisenseTvAPI hisenseTvAPI3 = new HisenseTvAPI(context);
        hisenseTvAPI = hisenseTvAPI3;
        return hisenseTvAPI3;
    }
}
