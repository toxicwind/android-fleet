package com.hisense.tv.smartsense.module.emmc.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import androidx.annotation.Nullable;
import b.b.a.c.a;
import b.f.b.b.b.a.d.c;

/* JADX INFO: loaded from: classes2.dex */
public class FstrimService extends IntentService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f1960a = "FstrimService";

    public FstrimService() {
        super(f1960a);
    }

    @Override // android.app.IntentService
    public void onHandleIntent(@Nullable Intent intent) {
        try {
            if (!a.f26b.getBoolean("emmc_fstrim_enable", true)) {
                Log.i(f1960a, "emmc_fstrim_enable is false,skipped!!");
                return;
            }
            Log.i(f1960a, "emmc_fstrim_enable is true,start fstrim...");
            long jCurrentTimeMillis = System.currentTimeMillis();
            long jA = b.f.b.b.b.a.a.a.a(0L);
            long j = jCurrentTimeMillis - jA;
            Log.d(f1960a, "current=" + jCurrentTimeMillis + ",last=" + jA + ",span=" + j);
            String str = f1960a;
            StringBuilder sb = new StringBuilder();
            sb.append("[EMMC fstrim]have pasted ");
            sb.append(j / 3600000);
            sb.append(" hours since last fstrim");
            Log.d(str, sb.toString());
            if (j >= 86400000) {
                Log.d(f1960a, "[EMMC fstrim]it's time to do fstrim");
                c.a(getApplicationContext()).a(1000L);
                b.f.b.b.b.a.b.a.a();
                b.f.b.b.b.a.a.a.f(jCurrentTimeMillis);
            }
        } catch (Exception e2) {
            Log.e(f1960a, e2.toString());
        }
    }
}
