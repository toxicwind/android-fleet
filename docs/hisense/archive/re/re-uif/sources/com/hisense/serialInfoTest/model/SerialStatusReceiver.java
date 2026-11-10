package com.hisense.serialInfoTest.model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemProperties;
import com.hisense.serialInfoTest.util.Logger;

/* JADX INFO: loaded from: classes.dex */
public class SerialStatusReceiver extends BroadcastReceiver {
    public static boolean mServiceFlag = false;
    private static int serviceStartTime = 1;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if ("0".equalsIgnoreCase(SystemProperties.get("persist.sys.factory.mode", "0"))) {
            Logger.d("factory mode is 0,serial test do not respond!!!");
            return;
        }
        Logger.d(" SerialMessageReceiver received broadcast");
        serviceStartTime++;
        String action = intent.getAction();
        StringBuilder sb = new StringBuilder();
        sb.append("SerialStatusReceiver mContext ");
        sb.append(context != null);
        Logger.d(sb.toString());
        action.equals("com.jamdeo.intent.SERIAL_CONTROL");
    }
}
