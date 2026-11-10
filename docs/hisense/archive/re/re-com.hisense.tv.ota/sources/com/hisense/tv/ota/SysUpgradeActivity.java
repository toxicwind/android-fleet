package com.hisense.tv.ota;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemProperties;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class SysUpgradeActivity extends Activity {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    String f21a = "SysUpgradeActivity";
    private Context b;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.i(this.f21a, "Setting manual start OTA now ");
        this.b = this;
        SystemProperties.set("sys.vt.ota.manual.start", com.hisense.tv.ota.W.k.y(this) ? "true" : "false");
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.hisense.tv.ota", "com.hisense.tv.ota.SysUpgradeService"));
        startForegroundService(intent);
        finish();
    }
}
