package b.f.b.b.b.b.a;

import android.util.Log;
import b.f.b.b.b.b.e;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile c f709a;

    public c() {
        FirebaseRemoteConfig.getInstance().setDefaultsAsync(e.remote_config_default);
    }

    public static c a() {
        if (f709a == null) {
            synchronized (c.class) {
                if (f709a == null) {
                    f709a = new c();
                }
            }
        }
        return f709a;
    }

    public boolean b() {
        return FirebaseRemoteConfig.getInstance().getBoolean("log_app_anr_enable");
    }

    public boolean c() {
        return FirebaseRemoteConfig.getInstance().getBoolean("log_app_common_enable");
    }

    public boolean d() {
        return FirebaseRemoteConfig.getInstance().getBoolean("log_app_crash_enable");
    }

    public void e() {
        FirebaseRemoteConfig.getInstance().fetchAndActivate().addOnCompleteListener(new OnCompleteListener() { // from class: b.f.b.b.b.b.a.b
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                this.f708a.a(task);
            }
        });
    }

    public void b(final String str, final String str2) {
        Log.d("RemoteConifgManager", "nps prefMode server_white=" + str + ",blackList=" + str2);
        b.f.b.b.a.b.c.a().execute(new Runnable() { // from class: b.f.b.b.b.b.a.a
            @Override // java.lang.Runnable
            public final void run() {
                c.a(str, str2);
            }
        });
    }

    public /* synthetic */ void a(Task task) {
        if (task.isSuccessful()) {
            try {
                Log.d("RemoteConifgManager", "remote config sync successfull");
                boolean zD = d();
                boolean zB = b();
                boolean zC = c();
                boolean z = FirebaseRemoteConfig.getInstance().getBoolean("emmc_fstrim_enable");
                Log.d("RemoteConifgManager", "log_app_crash_enable:" + zD);
                Log.d("RemoteConifgManager", "log_app_anr_enable:" + zB);
                Log.d("RemoteConifgManager", "log_app_common_enable:" + zC);
                Log.d("RemoteConifgManager", "emmc_fstrim_enable:" + z);
                b.b.a.c.a.f27c.putBoolean("emmc_fstrim_enable", z);
                b.b.a.c.a.f27c.apply();
                long j = FirebaseRemoteConfig.getInstance().getLong("emmc_warning_amount");
                Log.d("RemoteConifgManager", "emmc_warning_amount=" + j);
                long j2 = FirebaseRemoteConfig.getInstance().getLong("emmc_lifetime_interval");
                Log.d("RemoteConifgManager", "emmc_lifetime_interval=" + j2);
                long j3 = FirebaseRemoteConfig.getInstance().getLong("emmc_warning_interval");
                Log.d("RemoteConifgManager", "emmc_warning_interval=" + j3);
                long j4 = FirebaseRemoteConfig.getInstance().getLong("debug_userstatus_interval");
                Log.d("RemoteConifgManager", "debug_userstatus_interval=" + j4);
                long j5 = FirebaseRemoteConfig.getInstance().getLong("storagefull_threshold");
                Log.d("RemoteConifgManager", "storagefull_threshold=" + j5);
                boolean z2 = FirebaseRemoteConfig.getInstance().getBoolean("user_status_enable");
                Log.d("RemoteConifgManager", "user_status_enable=" + z2);
                b.b.a.c.a.f27c.putBoolean("user_status_enable", z2);
                b.b.a.c.a.f27c.apply();
                b.b.a.c.a.b("emmc_warning_amount", j);
                int i = FirebaseRemoteConfig.getInstance().getBoolean("emmc_emmccontrol_enable") ? 1 : 0;
                long j6 = FirebaseRemoteConfig.getInstance().getLong("emmc_emmccontrol_global_limitspeed");
                long j7 = FirebaseRemoteConfig.getInstance().getLong("emmc_emmccontrol_app_limitspeed");
                long j8 = FirebaseRemoteConfig.getInstance().getLong("emmc_emmccontrol_limitspeed_usedhour");
                b.f.b.a.b.a("persist.sys.vt.tv.emmc.lifetime.interval", String.valueOf(j2));
                b.f.b.a.b.a("persist.sys.vt.tv.emmc.warning.interval", String.valueOf(j3));
                b.f.b.a.b.a("persist.sys.vt.tv.user.status.interval", String.valueOf(j4));
                b.f.b.a.b.a("persist.sys.vt.tv.storagefull.threshold", String.valueOf(j5));
                b.f.b.a.b.a("persist.sys.vt.tv.emmc.emmccontrol.flag", String.valueOf(i));
                b.f.b.a.b.a("persist.sys.vt.tv.emmc.limitspeed", String.valueOf(j6));
                b.f.b.a.b.a("persist.sys.vt.tv.emmc.app.limitspeed", String.valueOf(j7));
                b.f.b.a.b.a("persist.sys.vt.tv.emmc.limitspeed.usedhour", String.valueOf(j8));
                boolean z3 = FirebaseRemoteConfig.getInstance().getBoolean("nps_log_enable");
                boolean z4 = FirebaseRemoteConfig.getInstance().getBoolean("nps_tombstones_enable");
                boolean z5 = FirebaseRemoteConfig.getInstance().getBoolean("nps_kernelpanic_enable");
                Log.d("RemoteConifgManager", "nps_log_enable=" + z5);
                Log.d("RemoteConifgManager", "nps_tomb_enable=" + z4);
                Log.d("RemoteConifgManager", "nps_kernel_enable=" + z5);
                b.f.b.a.b.a("persist.sys.vt.tv.nps.log.enable", String.valueOf(z3));
                b.f.b.a.b.a("persist.sys.vt.tv.nps.tombstones.enable", String.valueOf(z4));
                b.f.b.a.b.a("persist.sys.vt.tv.nps.kernelpanic.enable", String.valueOf(z5));
                String string = FirebaseRemoteConfig.getInstance().getString("nps_prefmode_whitelist");
                Log.d("RemoteConifgManager", "nps_prefmode_whitelist=" + string);
                String string2 = FirebaseRemoteConfig.getInstance().getString("nps_prefmode_blacklist");
                Log.d("RemoteConifgManager", "nps_prefmode_blacklist=" + string2);
                try {
                    b(string, string2);
                    b.f.b.a.b.a("persist.sys.vt.mm.codecrecord_support", String.valueOf(FirebaseRemoteConfig.getInstance().getBoolean("codecrecord_log_enable") ? 1 : 0));
                } catch (Exception e2) {
                    e = e2;
                    Log.e("RemoteConifgManager", e.toString());
                }
            } catch (Exception e3) {
                e = e3;
            }
        } else {
            Log.d("RemoteConifgManager", "remote config sync fail");
        }
    }

    /* JADX WARN: Code duplicated, block: B:17:0x007f  */
    public static /* synthetic */ void a(String str, String str2) {
        File file = new File("/vdow/hi_perf_mode.conf");
        if (file.exists()) {
            Log.d("RemoteConifgManager", "pre_mode config file.exist,check to update");
            try {
                List<String> listA = e.a.a.a.c.a(file, "UTF-8");
                boolean z = false;
                if (listA == null || listA.size() < 2) {
                    z = true;
                } else {
                    String str3 = listA.get(0);
                    String str4 = listA.get(1);
                    Log.d("RemoteConifgManager", "local_white =" + str3 + ",server_white=" + str);
                    Log.d("RemoteConifgManager", "local_black=" + str4 + ",server_black=" + str2);
                    if (str3 == null || str4 == null || !str3.equals(str) || !str4.equals(str2)) {
                        z = true;
                    } else {
                        Log.d("RemoteConifgManager", "nps perfmode config file.no need to update.");
                    }
                }
                if (z) {
                    Log.d("RemoteConifgManager", "nps perfmode confg file need to update");
                    e.a.a.a.c.b(file);
                    Thread.sleep(1000L);
                    ArrayList arrayList = new ArrayList(2);
                    arrayList.add(str);
                    arrayList.add(str2);
                    e.a.a.a.c.a(file, "UTF-8", arrayList);
                    b.b.a.c.a.a("/vdow/hi_perf_mode.conf");
                    Log.d("RemoteConifgManager", "nps perfmode confg file updated done.");
                    return;
                }
                return;
            } catch (Exception e2) {
                Log.e("RemoteConifgManager", e2.toString());
                return;
            }
        }
        try {
            Log.d("RemoteConifgManager", "create prefmode config file...");
            file.createNewFile();
            ArrayList arrayList2 = new ArrayList(2);
            arrayList2.add(str);
            arrayList2.add(str2);
            e.a.a.a.c.a(file, "UTF-8", arrayList2);
            b.b.a.c.a.a("/vdow/hi_perf_mode.conf");
            Log.d("RemoteConifgManager", "nps perfmode confg file create done.");
        } catch (Exception e3) {
            Log.e("RemoteConifgManager", e3.toString());
        }
    }
}
