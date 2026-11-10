package com.hsp.autogen.tests;

import android.util.Log;
import com.hsp.autogen.utils.AutoGenHSP;

/* JADX INFO: loaded from: classes.dex */
public class CustomerTest {
    public static final String TAG = "CustomerTest---------==>>";

    public static void main(String[] strArr) {
        Log.d(TAG, "====>>>> START CUSTOMER TEST <<<<====");
        new AutoGenHSP().initHSPEnv();
        int i = 2;
        while (true) {
            i--;
            if (i <= 0) {
                break;
            }
            try {
                Thread.sleep(1000L);
                Log.d(TAG, "====>>>> Time tick");
            } catch (Exception unused) {
            }
        }
        Log.d(TAG, "====>>>> END CUSTOMER TEST <<<<====");
    }
}
