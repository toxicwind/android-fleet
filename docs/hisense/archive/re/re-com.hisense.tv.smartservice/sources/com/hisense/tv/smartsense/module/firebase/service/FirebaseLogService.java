package com.hisense.tv.smartsense.module.firebase.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import b.f.b.b.b.b.b.c;
import b.f.b.b.b.b.e.d;
import com.hisense.tv.smartsense.module.firebase.service.FirebaseLogService;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
public class FirebaseLogService extends IntentService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f1968a = "FirebaseLogService";

    public FirebaseLogService() {
        super(f1968a);
    }

    public String a(Bundle bundle) {
        return bundle.getString("_eventName", null);
    }

    public Map<String, Object> b(final Bundle bundle) {
        final HashMap map = new HashMap();
        bundle.keySet().forEach(new Consumer() { // from class: b.f.b.b.b.b.c.a
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                FirebaseLogService.a(bundle, map, (String) obj);
            }
        });
        return map;
    }

    @Override // android.app.IntentService
    public void onHandleIntent(@Nullable Intent intent) {
        Bundle extras;
        if (intent == null || (extras = intent.getExtras()) == null) {
            return;
        }
        String strA = a(extras);
        if (TextUtils.isEmpty(strA)) {
            Log.e(f1968a, "eventName cant be null");
            return;
        }
        try {
            a(strA, extras);
        } catch (Exception e2) {
            Log.e(f1968a, e2.toString());
        }
    }

    public static /* synthetic */ void a(Bundle bundle, Map map, String str) {
        Object obj;
        if ("_eventName".equals(str) || (obj = bundle.get(str)) == null) {
            return;
        }
        map.put(str, obj);
    }

    public void a(String str, Bundle bundle) {
        Map<String, Object> mapB = b(bundle);
        if ("app_crash".equals(str)) {
            new c(getApplicationContext()).b(mapB);
        } else if ("app_anr".equals(str)) {
            new c(getApplicationContext()).a(mapB);
        } else {
            d.a(getApplicationContext()).a(str, mapB);
        }
    }
}
