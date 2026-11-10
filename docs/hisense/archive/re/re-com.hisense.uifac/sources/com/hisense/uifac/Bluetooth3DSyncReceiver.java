package com.hisense.uifac;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemProperties;
import android.util.Log;
import com.hisense.tvcm.FactoryConstants;
import com.hisense.uifac.menu.util.MenuConfigManager;

/* JADX INFO: loaded from: classes.dex */
public class Bluetooth3DSyncReceiver extends BroadcastReceiver {
    private static MenuConfigManager mcf;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Log.i("Fac_Bluetooth3DSyncReceiver", "received bluetooth3dsync broadcast*******");
        if (mcf == null) {
            mcf = MenuConfigManager.getInstance(context);
        }
        if ((SystemProperties.getInt("ro.jamdeo.hardware_3d_support", 0) == 1) && mcf.getDefault("HIFAC_STATUS") == 1 && FactoryConstants.canBluetooth3DBroadcastReceive) {
            Log.i("Fac_Bluetooth3DSyncReceiver", "show bluetooth3dsync toast");
            mcf.displayHisToast(context.getResources().getString(R.string.str_bluetooth_3d_sync));
            FactoryConstants.canBluetooth3DBroadcastReceive = false;
        }
    }
}
