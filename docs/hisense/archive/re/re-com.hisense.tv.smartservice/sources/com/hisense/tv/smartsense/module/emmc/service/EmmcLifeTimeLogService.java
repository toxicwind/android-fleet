package com.hisense.tv.smartsense.module.emmc.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import b.f.b.a.a;
import b.f.b.b.b.a.d.b;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class EmmcLifeTimeLogService extends IntentService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f1958a = "EmmcLifeTimeLogService";

    public EmmcLifeTimeLogService() {
        super(f1958a);
    }

    @Override // android.app.IntentService
    public void onHandleIntent(@Nullable Intent intent) {
        Log.d(f1958a, "start check EMMC lifetime...");
        try {
            int[] iArrE = a.e(getApplicationContext());
            if (iArrE == null || iArrE.length < 3) {
                return;
            }
            int i = iArrE[0];
            int i2 = iArrE[1];
            int i3 = iArrE[2];
            Log.d(f1958a, "EMMC lifetime_data:life_a=" + i2 + ",life_b=" + i3 + ",status=" + i);
            if (!(i2 >= 0 && i2 <= 11)) {
                i2 = 0;
            }
            if (!(i3 >= 0 && i3 <= 11)) {
                i3 = 0;
            }
            String str = "normal";
            if (i != 1) {
                if (i == 2) {
                    str = "warning";
                } else if (i == 3) {
                    str = "urgent";
                }
            }
            long jE = b.f.b.b.b.a.a.a.e(0L);
            long jD = b.f.b.b.b.a.a.a.d(0L);
            long jCurrentTimeMillis = System.currentTimeMillis();
            long jA = b.f.b.b.b.a.d.a.a(getApplicationContext());
            Log.i(f1958a, "[EMMC]status =" + str + ",lifeTimeA=" + i2 + ",lifeTimeB=" + i3);
            int iL = a.l(getApplicationContext());
            String str2 = f1958a;
            StringBuilder sb = new StringBuilder();
            sb.append("usedtime = ");
            sb.append(iL);
            sb.append(" mins");
            Log.d(str2, sb.toString());
            HashMap map = new HashMap();
            map.put("eventCode", "001004");
            map.put("lifetimeA", String.valueOf(i2));
            map.put("lifetimeB", String.valueOf(i3));
            map.put(NotificationCompat.CATEGORY_STATUS, str);
            map.put("amount", String.valueOf(jE));
            map.put("mtkamount", String.valueOf(jD));
            map.put("clienttime", String.valueOf(jCurrentTimeMillis));
            map.put("usedtime", String.valueOf(iL));
            map.put("storage", String.valueOf(jA));
            b.f.b.b.c.b.a.a(getApplicationContext(), "log_emmc_001004", map);
            b.a(getApplicationContext()).a(map);
        } catch (Exception e2) {
            Log.e(f1958a, e2.toString());
        }
    }
}
