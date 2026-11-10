package com.google.android.exoplayer.util;

import android.util.Log;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class MtkDebugLogUtil {
    private static final int[] LOG_TYPE_LIST = {1, 2, 4, 8, 16, 32, 64};
    private static int debugLogType;

    static {
        init();
    }

    private static void init() {
        String str = Util.SDK_INT > 27 ? "/mnt/vendor/tmp" : "/tmp";
        for (int i = 0; i < LOG_TYPE_LIST.length; i++) {
            if (new File(str + "/mtk_exoplayer_debug_" + LOG_TYPE_LIST[i]).exists()) {
                debugLogType |= LOG_TYPE_LIST[i];
            }
        }
        if (debugLogType > 0) {
            Log.i("MtkDebugLogUtil", "MtkDebugLog is enabled, type[" + debugLogType + "]");
        }
    }

    public static void log(String str, String str2, int i) {
        if ((debugLogType & i) > 0) {
            Log.d(str, "MtkDebugLogUtil[" + i + "]:" + str2);
        }
    }

    public static void setDebugLogType(int i) {
        debugLogType = i;
    }
}
