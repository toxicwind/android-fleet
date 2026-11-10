package com.hisense.tv.smartsense.module.emmc.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import androidx.annotation.Nullable;
import b.a.a.a.a;
import b.f.b.a.b;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.hisense.support.tv.HisenseCountryLanInfo;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class EmmcWarningLogService extends IntentService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f1959a = "EmmcWarningLogService";

    public EmmcWarningLogService() {
        super(f1959a);
    }

    /* JADX WARN: Code duplicated, block: B:50:0x0205  */
    /* JADX WARN: Code duplicated, block: B:51:0x021d A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:52:0x021f  */
    /* JADX WARN: Code duplicated, block: B:55:0x024d  */
    /* JADX WARN: Code duplicated, block: B:56:0x0254  */
    /* JADX WARN: Code duplicated, block: B:58:0x0258  */
    /* JADX WARN: Code duplicated, block: B:60:0x0282  */
    /* JADX WARN: Instruction removed from duplicated block: B:50:0x0205, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:58:0x0258, please report this as an issue */
    @Override // android.app.IntentService
    public void onHandleIntent(@Nullable Intent intent) {
        String str;
        String str2;
        boolean z;
        String str3;
        long jA;
        long j;
        long jD;
        String str4;
        long jC;
        long j2;
        long j3;
        int iA;
        b.a();
        boolean zBooleanValue = true;
        try {
            zBooleanValue = ((Boolean) b.f670d.invoke(b.f668b, "sys.vt.tv.emmcwarn.firstrunning", true)).booleanValue();
        } catch (Exception e2) {
            Log.e(b.f667a, e2.toString());
        }
        a.a("firstRunning=", zBooleanValue, f1959a);
        if (zBooleanValue) {
            b.a("sys.vt.tv.emmcwarn.firstrunning", "false");
        }
        try {
            long jA2 = b.b.a.c.a.a("emmc_warning_amount", 2097152L);
            Log.d(f1959a, "warningAmount=" + jA2);
            long jG = b.f.b.a.a.g(getApplicationContext());
            try {
                long jE = b.f.b.b.b.a.a.a.e(0L);
                long jB = b.f.b.b.b.a.a.a.b(0L);
                Log.d(f1959a, "amount=" + jG + ",total=" + jE + ",last=" + jB);
                if (jG >= 0) {
                    if (zBooleanValue) {
                        Log.d(f1959a, "fist Running...");
                        jB = 0;
                    }
                    long j4 = jG - jB;
                    z = zBooleanValue;
                    try {
                        long j5 = j4 / 1024;
                        String str5 = f1959a;
                        str = "fist Running...";
                        try {
                            StringBuilder sb = new StringBuilder();
                            str2 = ",last=";
                            try {
                                sb.append("[EMMC] 30min increaseAmount = ");
                                sb.append(j5);
                                sb.append("MB");
                                Log.i(str5, sb.toString());
                                if (j4 < 0) {
                                    Log.e(f1959a, "increaseAmount < 0");
                                } else {
                                    if (j4 >= jA2) {
                                        Log.i(f1959a, "[EMMC WARNING] 30min increaseAmount = " + j5 + "MB");
                                    }
                                    b.f.b.b.b.a.a.a.g(jG);
                                    long j6 = jE + j4;
                                    long j7 = j6 / FirebaseFirestoreSettings.MINIMUM_CACHE_BYTES;
                                    String str6 = f1959a;
                                    StringBuilder sb2 = new StringBuilder();
                                    sb2.append("[EMMC] totalmount=");
                                    sb2.append(j7);
                                    str3 = HisenseCountryLanInfo.LOCALE_GB_COUNTRY;
                                    try {
                                        sb2.append(str3);
                                        Log.i(str6, sb2.toString());
                                        b.f.b.b.b.a.a.a.j(j6);
                                    } catch (Exception e3) {
                                        e = e3;
                                        Log.e(f1959a, e.toString());
                                    }
                                }
                            } catch (Exception e4) {
                                e = e4;
                                str3 = HisenseCountryLanInfo.LOCALE_GB_COUNTRY;
                                Log.e(f1959a, e.toString());
                                long jA3 = b.f.b.b.b.a.d.a.a(getApplicationContext());
                                Log.d(b.f.b.b.b.a.d.a.f693a, "EMMC totoalSize is " + jA3);
                                long j8 = (jA3 * 3000) / 175200;
                                Log.d(f1959a, "warningDefaultSize is " + j8 + " kb");
                                jA = b.b.a.c.a.a("emmc_warning_amount", j8);
                                Log.d(f1959a, "[EMMC]warningAmount is " + jA + " kb");
                                j = b.f.b.a.a.j(getApplicationContext());
                                jD = b.f.b.b.b.a.a.a.d(0L);
                                str4 = str3;
                                jC = b.f.b.b.b.a.a.a.c(0L);
                                Log.d(f1959a, "amount=" + j + ",total=" + jD + str2 + jC);
                                if (j < 0) {
                                    Log.e(f1959a, "getMTKEmmcWrittenSize error,amount=" + j);
                                    return;
                                }
                                if (z) {
                                    Log.d(f1959a, str);
                                    jC = 0;
                                }
                                j2 = j - jC;
                                j3 = j2 / 1024;
                                Log.i(f1959a, "[MTKEMMC] 30min increaseAmount = " + j3 + "MB");
                                if (j2 < 0) {
                                    Log.e(f1959a, "increaseAmount < 0");
                                    return;
                                }
                                if (j2 >= jA) {
                                    iA = b.a("sys.vt.tv.emmcwarn.times", 0) + 1;
                                    Log.i(f1959a, "[MTKEMMC WARNING] 30min increaseAmount = " + j3 + "MB,warningTimes = " + iA);
                                    if (iA == 3) {
                                        HashMap map = new HashMap();
                                        map.put("eventCode", "001005");
                                        map.put("amount", String.valueOf(j2));
                                        map.put("package1", "NULL");
                                        map.put("write1", "-1");
                                        map.put("package2", "NULL");
                                        map.put("write2", "-1");
                                        map.put("package3", "NULL");
                                        map.put("write3", "-1");
                                        map.put("package4", "NULL");
                                        map.put("write4", "-1");
                                        map.put("package5", "NULL");
                                        map.put("write5", "-1");
                                        b.f.b.b.c.b.a.a(getApplicationContext(), "log_emmc_001005", map);
                                        b.f.b.b.b.a.d.b.a(getApplicationContext()).a(map);
                                    }
                                    b.a("sys.vt.tv.emmcwarn.times", String.valueOf(iA));
                                }
                                b.f.b.b.b.a.a.a.h(j);
                                long j9 = jD + j2;
                                long j10 = j9 / FirebaseFirestoreSettings.MINIMUM_CACHE_BYTES;
                                Log.i(f1959a, "[MTKEMMC] totalmount=" + j10 + str4);
                                b.f.b.b.b.a.a.a.i(j9);
                            }
                        } catch (Exception e5) {
                            e = e5;
                            str3 = HisenseCountryLanInfo.LOCALE_GB_COUNTRY;
                            str2 = ",last=";
                        }
                    } catch (Exception e6) {
                        e = e6;
                        str = "fist Running...";
                        str2 = ",last=";
                    }
                    long jA4 = b.f.b.b.b.a.d.a.a(getApplicationContext());
                    Log.d(b.f.b.b.b.a.d.a.f693a, "EMMC totoalSize is " + jA4);
                    long j11 = (jA4 * 3000) / 175200;
                    Log.d(f1959a, "warningDefaultSize is " + j11 + " kb");
                    jA = b.b.a.c.a.a("emmc_warning_amount", j11);
                    Log.d(f1959a, "[EMMC]warningAmount is " + jA + " kb");
                    j = b.f.b.a.a.j(getApplicationContext());
                    jD = b.f.b.b.b.a.a.a.d(0L);
                    str4 = str3;
                    jC = b.f.b.b.b.a.a.a.c(0L);
                    Log.d(f1959a, "amount=" + j + ",total=" + jD + str2 + jC);
                    if (j < 0) {
                        Log.e(f1959a, "getMTKEmmcWrittenSize error,amount=" + j);
                        return;
                    }
                    if (z) {
                        Log.d(f1959a, str);
                        jC = 0;
                    }
                    j2 = j - jC;
                    j3 = j2 / 1024;
                    Log.i(f1959a, "[MTKEMMC] 30min increaseAmount = " + j3 + "MB");
                    if (j2 < 0) {
                        Log.e(f1959a, "increaseAmount < 0");
                        return;
                    }
                    if (j2 >= jA) {
                        iA = b.a("sys.vt.tv.emmcwarn.times", 0) + 1;
                        Log.i(f1959a, "[MTKEMMC WARNING] 30min increaseAmount = " + j3 + "MB,warningTimes = " + iA);
                        if (iA == 3) {
                            HashMap map2 = new HashMap();
                            map2.put("eventCode", "001005");
                            map2.put("amount", String.valueOf(j2));
                            map2.put("package1", "NULL");
                            map2.put("write1", "-1");
                            map2.put("package2", "NULL");
                            map2.put("write2", "-1");
                            map2.put("package3", "NULL");
                            map2.put("write3", "-1");
                            map2.put("package4", "NULL");
                            map2.put("write4", "-1");
                            map2.put("package5", "NULL");
                            map2.put("write5", "-1");
                            b.f.b.b.c.b.a.a(getApplicationContext(), "log_emmc_001005", map2);
                            b.f.b.b.b.a.d.b.a(getApplicationContext()).a(map2);
                        }
                        b.a("sys.vt.tv.emmcwarn.times", String.valueOf(iA));
                    }
                    b.f.b.b.b.a.a.a.h(j);
                    long j12 = jD + j2;
                    long j13 = j12 / FirebaseFirestoreSettings.MINIMUM_CACHE_BYTES;
                    Log.i(f1959a, "[MTKEMMC] totalmount=" + j13 + str4);
                    b.f.b.b.b.a.a.a.i(j12);
                }
                Log.e(f1959a, "getEmmcWrittenSize error,amount=" + jG);
                str = "fist Running...";
                str2 = ",last=";
                z = zBooleanValue;
                str3 = HisenseCountryLanInfo.LOCALE_GB_COUNTRY;
            } catch (Exception e7) {
                e = e7;
                str = "fist Running...";
                str2 = ",last=";
                z = zBooleanValue;
            }
        } catch (Exception e8) {
            e = e8;
            str = "fist Running...";
            str2 = ",last=";
            z = zBooleanValue;
            str3 = HisenseCountryLanInfo.LOCALE_GB_COUNTRY;
        }
        long jA5 = b.f.b.b.b.a.d.a.a(getApplicationContext());
        Log.d(b.f.b.b.b.a.d.a.f693a, "EMMC totoalSize is " + jA5);
        long j14 = (jA5 * 3000) / 175200;
        Log.d(f1959a, "warningDefaultSize is " + j14 + " kb");
        jA = b.b.a.c.a.a("emmc_warning_amount", j14);
        Log.d(f1959a, "[EMMC]warningAmount is " + jA + " kb");
        j = b.f.b.a.a.j(getApplicationContext());
        jD = b.f.b.b.b.a.a.a.d(0L);
        str4 = str3;
        jC = b.f.b.b.b.a.a.a.c(0L);
        Log.d(f1959a, "amount=" + j + ",total=" + jD + str2 + jC);
        if (j < 0) {
            Log.e(f1959a, "getMTKEmmcWrittenSize error,amount=" + j);
            return;
        }
        if (z) {
            Log.d(f1959a, str);
            jC = 0;
        }
        j2 = j - jC;
        j3 = j2 / 1024;
        Log.i(f1959a, "[MTKEMMC] 30min increaseAmount = " + j3 + "MB");
        if (j2 < 0) {
            Log.e(f1959a, "increaseAmount < 0");
            return;
        }
        if (j2 >= jA) {
            iA = b.a("sys.vt.tv.emmcwarn.times", 0) + 1;
            Log.i(f1959a, "[MTKEMMC WARNING] 30min increaseAmount = " + j3 + "MB,warningTimes = " + iA);
            if (iA == 3) {
                HashMap map3 = new HashMap();
                map3.put("eventCode", "001005");
                map3.put("amount", String.valueOf(j2));
                map3.put("package1", "NULL");
                map3.put("write1", "-1");
                map3.put("package2", "NULL");
                map3.put("write2", "-1");
                map3.put("package3", "NULL");
                map3.put("write3", "-1");
                map3.put("package4", "NULL");
                map3.put("write4", "-1");
                map3.put("package5", "NULL");
                map3.put("write5", "-1");
                b.f.b.b.c.b.a.a(getApplicationContext(), "log_emmc_001005", map3);
                b.f.b.b.b.a.d.b.a(getApplicationContext()).a(map3);
            }
            b.a("sys.vt.tv.emmcwarn.times", String.valueOf(iA));
        }
        b.f.b.b.b.a.a.a.h(j);
        long j15 = jD + j2;
        long j16 = j15 / FirebaseFirestoreSettings.MINIMUM_CACHE_BYTES;
        Log.i(f1959a, "[MTKEMMC] totalmount=" + j16 + str4);
        b.f.b.b.b.a.a.a.i(j15);
    }
}
