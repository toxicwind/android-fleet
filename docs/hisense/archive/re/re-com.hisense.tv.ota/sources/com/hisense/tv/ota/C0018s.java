package com.hisense.tv.ota;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/* JADX INFO: renamed from: com.hisense.tv.ota.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
class C0018s extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ SysUpgradeService f48a;

    C0018s(SysUpgradeService sysUpgradeService) {
        this.f48a = sysUpgradeService;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (context == null || intent == null) {
            return;
        }
        String action = intent.getAction();
        if ("eula.ota.message".equals(action)) {
            Log.i("SysUpgradeService", " OTA receive eula action");
            String stringExtra = intent.getStringExtra("eula_path");
            Log.i("SysUpgradeService", "OTA Revice eula path = " + stringExtra);
            if (stringExtra != null) {
                this.f48a.f22a.G(stringExtra);
                return;
            } else {
                Log.e("SysUpgradeService", "eula_Path = null");
                return;
            }
        }
        if (!"android.intent.action.SCREEN_OFF".equals(action)) {
            if ("android.intent.action.SCREEN_ON".equals(action)) {
                Log.i("SysUpgradeService", " OTA receive screen ON action");
                C0016q unused = this.f48a.f22a;
                if (C0016q.t == 1) {
                    C0016q unused2 = this.f48a.f22a;
                    C0016q.t++;
                    return;
                }
                return;
            }
            return;
        }
        Log.i("SysUpgradeService", " OTA receive screen OFF action");
        C0016q unused3 = this.f48a.f22a;
        if (C0016q.u == 1) {
            Log.i("SysUpgradeService", "OTA hold wakelock now, begin to startservice");
            Intent intent2 = new Intent("com.hisense.action.ota");
            intent2.putExtra("is_screen_off", true);
            intent2.putExtra("auto_check", true);
            intent2.putExtra("is_str_boot", true);
            intent2.setPackage(context.getPackageName());
            context.startForegroundService(intent2);
        }
    }
}
