package com.regza.persistent.logd.lib;

import android.content.Context;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class LogSaver {
    private static final String TAG = "LogSaver";
    private static RegzaPersistentLogServiceConnection sConnection;

    public static boolean saveLog(Context context) {
        if (sConnection == null) {
            sConnection = new RegzaPersistentLogServiceConnection(context);
        }
        if (sConnection.executeLogSave()) {
            return true;
        }
        Log.d(TAG, "Failed to executeLogSave()");
        return false;
    }
}
