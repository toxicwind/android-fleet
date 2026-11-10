package com.hisense.tv.smartsense.module.logserver;

import android.app.IntentService;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import b.f.b.b.b.c.a;
import com.google.gson.GsonBuilder;
import com.hmct.cloud.sdk.utils.DeviceConfig;
import com.ju.lib.datareport.ReportManager;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class LogBatchReportService extends IntentService {
    public LogBatchReportService() {
        super("LogBatchReportService");
    }

    public void a(String str, String str2, Collection<Map<String, String>> collection) {
        try {
            Iterator<Map<String, String>> it = collection.iterator();
            while (it.hasNext()) {
                Log.d("LogBatchReportService", "The Batch Log map sent out is:" + it.next().toString());
            }
            ReportManager.getInstance(getApplicationContext(), str, "2.0", str2).getReporter("1178487606").report(collection);
            Log.i("LogBatchReportService", "HiReport end send out");
        } catch (Exception e2) {
            Log.e("LogBatchReportService", e2.toString());
        }
    }

    @Override // android.app.IntentService
    public void onHandleIntent(@Nullable Intent intent) {
        Collection<Map<String, String>> collection;
        if (intent == null) {
            return;
        }
        String strA = a();
        if (strA == null || strA.equals("0")) {
            Log.e("LogBatchReportService", "usage state is off,should not do logreport.");
            return;
        }
        String stringExtra = intent.getStringExtra("INTENT_PARMS_HOST");
        String stringExtra2 = intent.getStringExtra("INTENT_PARMS_LOGMAPLIST");
        if (TextUtils.isEmpty(stringExtra)) {
            Log.d("LogBatchReportService", "host is null,skip.");
            return;
        }
        if (TextUtils.isEmpty(stringExtra2)) {
            Log.d("LogBatchReportService", "logMapList json is null,skip.");
            return;
        }
        try {
            collection = (Collection) new GsonBuilder().create().fromJson(stringExtra2, new a(this).getType());
        } catch (Exception e2) {
            Log.e("LogBatchReportService", e2.toString());
            collection = null;
        }
        if (collection == null) {
            Log.d("LogBatchReportService", "logMap is null,skip.");
            return;
        }
        String deviceId = DeviceConfig.getDeviceId(getApplicationContext());
        Log.i("LogBatchReportService", "deviceId is " + deviceId);
        if (deviceId == null) {
            Log.d("LogBatchReportService", "deviceId is null,skip.");
            return;
        }
        Iterator<Map<String, String>> it = collection.iterator();
        while (it.hasNext()) {
            it.next().put("DeviceId", deviceId);
        }
        a(deviceId, stringExtra, collection);
    }

    public String a() {
        return a("persist.sys.tvusage.switch", "0");
    }

    public String a(String str, String str2) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getDeclaredMethod("get", String.class, String.class).invoke(cls, str, str2);
        } catch (Exception e2) {
            b.a.a.a.a.c("reflect setPlayerType exception", e2, "LogBatchReportService");
            return str2;
        }
    }
}
