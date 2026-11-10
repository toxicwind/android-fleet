package com.hisense.uifac;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.hisense.uifac.menu.util.MenuConfigManager;

/* JADX INFO: loaded from: classes.dex */
public class STRShutDownReceiver extends BroadcastReceiver {
    private static MenuConfigManager mcf;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Log.i("Fac_STRShutDownReceiver", " received android.intent.action.ACTION_PREPARE_SHUTDOWN*******");
        if (mcf == null) {
            mcf = MenuConfigManager.getInstance(context);
        }
        if (mcf.getDefault("HIFAC_STATUS") != 1 || mcf.getDefault("HIFAC_AGING") == 1) {
            return;
        }
        mcf.setValue("HIFAC_STATUS", 0);
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i("Fac_STRShutDownReceiver", "Exit factory menu by STR shut down");
    }
}
