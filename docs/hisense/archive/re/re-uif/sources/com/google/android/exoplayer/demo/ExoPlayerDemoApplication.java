package com.google.android.exoplayer.demo;

import android.app.Application;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ExoPlayerDemoApplication extends Application {
    public static Map<String, Object> vendorClassMap;

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        getApplicationContext();
        vendorClassMap = new HashMap();
    }
}
