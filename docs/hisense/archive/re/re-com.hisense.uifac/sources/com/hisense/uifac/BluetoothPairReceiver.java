package com.hisense.uifac;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.hisense.tvcm.FacFunction_Adapter;
import com.hisense.tvcm.FacPropertyDemo;
import com.hisense.uifac.menu.util.MenuConfigManager;
import com.hisense.uifac.util.ISystemManagerStatusCallBack;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothPairReceiver extends BroadcastReceiver {
    private static MenuConfigManager mcf;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Log.i("Fac_BluetoothPairReceiver", "received bluetoothPair broadcast*******");
        FacPropertyDemo.initProperties();
        if (mcf == null) {
            mcf = MenuConfigManager.getInstance(context);
        }
        FacFunction_Adapter.getInstance(context).checkSystemManagerStatus(new ISystemManagerStatusCallBack(this, intent, context) { // from class: com.hisense.uifac.BluetoothPairReceiver.1
        });
    }
}
