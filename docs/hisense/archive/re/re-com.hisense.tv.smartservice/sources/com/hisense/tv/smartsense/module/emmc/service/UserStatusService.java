package com.hisense.tv.smartsense.module.emmc.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import b.b.a.c.a;
import b.f.b.a.b;
import com.mediatek.twoworlds.tv.MtkTvInputSourceBase;
import e.b.d;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class UserStatusService extends IntentService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f1961a = "UserStatusService";

    public UserStatusService() {
        super(f1961a);
    }

    @Override // android.app.IntentService
    public void onHandleIntent(@Nullable Intent intent) {
        long availableBytes;
        int iK;
        if (!a.f26b.getBoolean("user_status_enable", true)) {
            Log.d(f1961a, "skip!");
            return;
        }
        b.f.b.b.b.a.c.a aVar = new b.f.b.b.b.a.c.a(getApplicationContext());
        try {
            boolean zN = b.f.b.a.a.n(aVar.f692b);
            Log.d(b.f.b.b.b.a.c.a.f691a, "woc=" + (zN ? 1 : 0));
            try {
                availableBytes = new StatFs(Environment.getDataDirectory().getPath()).getAvailableBytes();
            } catch (Exception e2) {
                Log.e(b.f.b.b.b.a.c.a.f691a, e2.toString());
                availableBytes = 0;
            }
            Log.d(b.f.b.b.b.a.c.a.f691a, "free=" + availableBytes);
            int iL = b.f.b.a.a.l(aVar.f692b);
            try {
                iK = b.f.b.a.a.k(aVar.f692b);
                if (iK >= 0) {
                    try {
                        iK += b.f.b.b.b.a.a.a.a();
                        b.f.b.b.b.a.a.a.a(iK);
                    } catch (Exception e3) {
                        e = e3;
                        Log.e(b.f.b.b.b.a.c.a.f691a, e.toString());
                    }
                }
            } catch (Exception e4) {
                e = e4;
                iK = 0;
            }
            Log.d(b.f.b.b.b.a.c.a.f691a, "shutdowntimes=" + iK);
            int i = Settings.Global.getInt(aVar.f692b.getContentResolver(), "acr_service_enable", 0);
            int iA = b.a("persist.vt.boot_count", 0);
            Log.d(b.f.b.b.b.a.c.a.f691a, "AC_ON=" + iA);
            int iMax = Math.max(0, iA);
            int iA2 = b.a("persist.vt.fstrim_count", 0);
            Log.d(b.f.b.b.b.a.c.a.f691a, "fstrim count=" + iA2);
            int iMax2 = Math.max(0, iA2);
            String strB = aVar.b();
            Log.d(b.f.b.b.b.a.c.a.f691a, "usb=" + strB);
            String strA = aVar.a();
            Log.d(b.f.b.b.b.a.c.a.f691a, "hdmi=" + strA);
            String strF = b.f.b.a.a.f(aVar.f692b);
            String strD = b.f.b.a.a.d(aVar.f692b);
            String strC = b.f.b.a.a.c(aVar.f692b);
            d dVar = new d();
            dVar.a("usb", (Object) strB);
            dVar.a(MtkTvInputSourceBase.INPUT_TYPE_HDMI, (Object) strA);
            dVar.b("acshutdowntimes", iMax);
            dVar.b("fstimes", iMax2);
            dVar.b("acr", i);
            dVar.a("emmcversion", (Object) strF);
            dVar.a("hwrev", (Object) strD);
            dVar.a("fwrev", (Object) strC);
            Context context = aVar.f692b;
            int[] iArrD = b.f.b.a.a.d();
            if (iArrD.length >= 11) {
                int i2 = iArrD[0];
                Log.i(b.f.b.b.b.a.c.a.f691a, "slc_reserved_blocks_num=" + i2);
                int i3 = iArrD[1];
                Log.i(b.f.b.b.b.a.c.a.f691a, "slc_max_erase_count=" + i3);
                int i4 = iArrD[2];
                Log.i(b.f.b.b.b.a.c.a.f691a, "slc_min_erase_count=" + i4);
                int i5 = iArrD[3];
                Log.i(b.f.b.b.b.a.c.a.f691a, "slc_avg_erase_count=" + i5);
                int i6 = iArrD[4];
                Log.i(b.f.b.b.b.a.c.a.f691a, "mlc_reserved_blocks_num=" + i6);
                int i7 = iArrD[5];
                Log.i(b.f.b.b.b.a.c.a.f691a, "mlc_max_erase_count=" + i7);
                int i8 = iArrD[6];
                Log.i(b.f.b.b.b.a.c.a.f691a, "mlc_min_erase_count=" + i8);
                int i9 = iArrD[7];
                Log.i(b.f.b.b.b.a.c.a.f691a, "mlc_avg_erase_count=" + i9);
                int i10 = iArrD[8];
                Log.i(b.f.b.b.b.a.c.a.f691a, "slc_mlc_reserved_blocks_num=" + i10);
                int i11 = iArrD[9];
                Log.i(b.f.b.b.b.a.c.a.f691a, "spo_count=" + i11);
                int i12 = iArrD[10];
                Log.i(b.f.b.b.b.a.c.a.f691a, "runtime_bad_block_count=" + i12);
                dVar.b("slc_reserved_blocks_num", i2);
                dVar.b("slc_max_erase_count", i3);
                dVar.b("slc_min_erase_count", i4);
                dVar.b("slc_avg_erase_count", i5);
                dVar.b("mlc_reserved_blocks_num", i6);
                dVar.b("mlc_max_erase_count", i7);
                dVar.b("mlc_min_erase_count", i8);
                dVar.b("mlc_avg_erase_count", i9);
                dVar.b("slc_mlc_reserved_blocks_num", i10);
                dVar.b("spo_count", i11);
                dVar.b("rtbb", i12);
            }
            HashMap map = new HashMap();
            map.put("eventCode", "001006");
            map.put(NotificationCompat.CATEGORY_STATUS, String.valueOf(zN ? 1 : 0));
            map.put("amount", String.valueOf(availableBytes));
            map.put("usedtime", String.valueOf(iL));
            map.put("shutdowntimes", String.valueOf(iK));
            map.put("extramsg", dVar.toString());
            b.f.b.b.b.a.d.b.a(aVar.f692b).a(map);
        } catch (Exception e5) {
            Log.e(b.f.b.b.b.a.c.a.f691a, e5.toString());
        }
    }
}
