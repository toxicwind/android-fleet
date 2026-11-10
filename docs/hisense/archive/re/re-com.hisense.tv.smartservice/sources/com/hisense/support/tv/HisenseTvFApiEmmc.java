package com.hisense.support.tv;

import android.util.Log;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class HisenseTvFApiEmmc {
    public static final String TAG = "TV_ HisenseTvFApiEmmc";

    public ArrayList<Byte> factoryReadDevInfoEmmc(String str, int i, int i2) {
        Log.d(TAG, "factoryReadDevInfoEmmc\n");
        return null;
    }

    public ArrayList<Byte> factoryReadProjectId() {
        return null;
    }

    public int factoryWriteDevInfoEmmc(String str, int i, ArrayList<Byte> arrayList) {
        Log.d(TAG, "factoryWriteDevInfoEmmc\n");
        return 0;
    }

    public int factoryWriteProjectId(ArrayList<Byte> arrayList) {
        return 0;
    }
}
