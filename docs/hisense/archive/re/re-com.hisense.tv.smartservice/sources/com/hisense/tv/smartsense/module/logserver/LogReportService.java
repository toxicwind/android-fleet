package com.hisense.tv.smartsense.module.logserver;

import android.app.IntentService;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import b.a.a.a.a;
import b.f.b.b.b.c.b;
import com.google.gson.GsonBuilder;
import com.hmct.cloud.sdk.utils.DeviceConfig;
import com.ju.lib.datareport.ReportManager;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class LogReportService extends IntentService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f1970a = "LogReportService";

    public LogReportService() {
        super(f1970a);
    }

    public void a(Intent intent) {
        Map map;
        if (intent == null) {
            return;
        }
        String strA = a();
        if (strA == null || strA.equals("0")) {
            Log.e(f1970a, "usage state is off,should not do logreport.");
            return;
        }
        String stringExtra = intent.getStringExtra("INTENT_PARMS_EVENTCODE");
        String stringExtra2 = intent.getStringExtra("INTENT_PARMS_HOST");
        String stringExtra3 = intent.getStringExtra("INTENT_PARMS_LOGMAP");
        if (TextUtils.isEmpty(stringExtra)) {
            Log.d(f1970a, "eventCode is null,skip.");
            return;
        }
        if (TextUtils.isEmpty(stringExtra2)) {
            Log.d(f1970a, "host is null,skip.");
            return;
        }
        if (TextUtils.isEmpty(stringExtra3)) {
            Log.d(f1970a, "logMap json is null,skip.");
            return;
        }
        try {
            map = (Map) new GsonBuilder().create().fromJson(stringExtra3, new b(this).getType());
        } catch (Exception e2) {
            Log.e(f1970a, e2.toString());
            map = null;
        }
        if (map == null) {
            Log.d(f1970a, "logMap is null,skip.");
            return;
        }
        String deviceId = DeviceConfig.getDeviceId(getApplicationContext());
        Log.i(f1970a, "deviceId is " + deviceId);
        if (stringExtra.equals("200304") && deviceId == null) {
            deviceId = "-1";
        }
        if (deviceId == null) {
            Log.d(f1970a, "deviceId is null,skip.");
        } else {
            map.put("DeviceId", deviceId);
            a(deviceId, stringExtra2, map);
        }
    }

    @Override // android.app.IntentService
    public void onHandleIntent(Intent intent) {
        a(intent);
    }

    public void a(String str, String str2, Map map) {
        try {
            Log.d(f1970a, "The Log map sent out is:" + map.toString());
            ReportManager.getInstance(getApplicationContext(), str, "2.0", str2).getReporter("1178487606").report((Map<String, String>) map);
            Log.i(f1970a, "HiReport end send out");
        } catch (Exception e2) {
            Log.e(f1970a, e2.toString());
        }
    }

    public String a() {
        return a("persist.sys.tvusage.switch", "0");
    }

    public String a(String str, String str2) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getDeclaredMethod("get", String.class, String.class).invoke(cls, str, str2);
        } catch (Exception e2) {
            a.c("reflect setPlayerType exception", e2, f1970a);
            return str2;
        }
    }
}
