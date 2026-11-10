package com.hisense.tv.ota;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.PowerManager;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.os.UpdateEngine;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Locale;

/* JADX INFO: renamed from: com.hisense.tv.ota.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0016q {
    private static C0016q r;
    private static String s;
    public static int t;
    public static int u;
    public static int v;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private WeakReference f46a;
    private Handler b;
    private C0002c c;
    private final UpdateEngine d;
    private final UpdateEngineCallbackC0011l e = new UpdateEngineCallbackC0011l(this);
    private C0010k f = new C0010k(this);
    private C0013n g = new C0013n(this);
    private C0012m h = new C0012m(this);
    private C0015p i = new C0015p(this);
    public int j = 0;
    public int k = 49;
    public int l = 0;
    public int m = 0;
    public int n = 0;
    public int o = 0;
    private final Handler p = new HandlerC0007h(this);
    private Runnable q = new RunnableC0008i(this);

    private C0016q(Context context) {
        this.f46a = new WeakReference(context);
        HandlerThread handlerThread = new HandlerThread("SysUpgrade");
        handlerThread.start();
        this.b = new Handler(handlerThread.getLooper());
        this.d = new UpdateEngine();
        this.c = new C0002c();
    }

    private void k(boolean z, boolean z2, boolean z3, boolean z4) {
        SysUpgradeState sysUpgradeStateB = C0019t.a().b();
        Log.i("SysUpgradeManager", "auto start, state: " + sysUpgradeStateB);
        if (SystemProperties.getInt("persist.vt.factory.mode", 0) == 1) {
            Log.i("SysUpgradeManager", "auto OTA, but in factory mode just kill myself");
        }
        if (SystemProperties.getBoolean("sys.vt.ota.manual.start", false)) {
            SystemProperties.set("sys.vt.ota.manual.start", "false");
        }
        if (!z4 && com.hisense.tv.ota.W.k.x((Context) this.f46a.get())) {
            Log.i("SysUpgradeManager", "auto OTA, but in FTE now,just return");
            return;
        }
        if (z3) {
            if (!SysUpgradeState.IDLE.equals(sysUpgradeStateB)) {
                Log.e("SysUpgradeManager", "state is not idle, won't start again. state: " + sysUpgradeStateB);
                return;
            }
            if (!SystemProperties.get("sys.vt.ota.mode").equals("silent")) {
                Log.e("SysUpgradeManager", "not silent mode, won't start");
                return;
            }
        }
        if (!z2) {
            if (SysUpgradeState.IDLE.equals(sysUpgradeStateB)) {
                this.c.q(z4);
                I(true, 1, z);
                return;
            } else {
                Log.e("SysUpgradeManager", "state is not idle, won't start again. state: " + sysUpgradeStateB);
                return;
            }
        }
        if (u != 1) {
            Log.i("SysUpgradeManager", "OTA receive screen off broadcast, but status is not desired,do nothing");
            return;
        }
        switch (C0009j.f40a[sysUpgradeStateB.ordinal()]) {
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
                Log.i("SysUpgradeManager", "OTA receive screen off broadcast,but status wrong,just return");
                break;
            default:
                Log.i("SysUpgradeManager", "OTA receive screen off broadcast,begin to work");
                d(4, 2000);
                break;
        }
    }

    private HashMap p(String str, String str2, String str3, String str4, String str5) {
        HashMap map = new HashMap();
        map.put("dvcRsv", str);
        map.put("currentVersion", str2);
        map.put("languageId", String.valueOf(com.hisense.tv.ota.W.l.g(Locale.getDefault().getLanguage())));
        if (!TextUtils.isEmpty(str3)) {
            map.put("statCode", str3);
        }
        if (!TextUtils.isEmpty(str4)) {
            map.put("brandCode", str4);
        }
        if (!TextUtils.isEmpty(str5)) {
            map.put("MAC", str5);
        }
        if (SystemProperties.get("ro.vt.market").equals("NA")) {
            String strU = com.hisense.tv.ota.W.k.u((Context) this.f46a.get());
            Log.i("SysUpgradeManager", "ZipCode = " + strU);
            map.put("postCode", strU);
        }
        return map;
    }

    private void q(int i, String str, String str2, long j, com.hisense.tv.ota.V.c cVar) {
        Log.i("SysUpgradeManager", " doDownloadPkg action");
        com.hisense.tv.ota.V.b.a().a(str, str2, true, cVar);
    }

    private void r(int i, HashMap map, InterfaceC0014o interfaceC0014o) {
        int[] iArr = {1000, 2000, 2000, 3000};
        String strB = com.hisense.tv.ota.W.d.b((Context) this.f46a.get());
        if (this.c.j()) {
            interfaceC0014o.a();
            return;
        }
        if (TextUtils.isEmpty(strB)) {
            if (!this.c.f()) {
                Log.e("SysUpgradeManager", "[manual-OTA]do version check failed, can't get token");
                interfaceC0014o.b(i, -2, null);
                return;
            }
            int i2 = 0;
            while (TextUtils.isEmpty(strB) && i2 <= 3) {
                SystemClock.sleep(iArr[i2]);
                Log.i("SysUpgradeManager", "wait until get token ");
                i2++;
                strB = com.hisense.tv.ota.W.d.b((Context) this.f46a.get());
            }
            if (i2 >= 4) {
                interfaceC0014o.b(i, -2, null);
                return;
            }
        }
        VersionData versionDataA = U.b(C0000a.f32a).a(strB, map, (Context) this.f46a.get());
        if (this.c.j()) {
            interfaceC0014o.a();
            return;
        }
        if (versionDataA == null || TextUtils.isEmpty(versionDataA.c()) || com.hisense.tv.ota.W.k.y((Context) this.f46a.get()) || versionDataA.j()) {
            Log.i("SysUpgradeManager", "no Eula deploy or in the store mode now ");
        } else {
            String strC = versionDataA.c();
            Log.i("SysUpgradeManager", "eulaUrl = " + strC);
            String strG = com.hisense.tv.ota.W.k.g(strC);
            Log.i("SysUpgradeManager", "TmpeulaZipPath = " + strG);
            if (strG == null) {
                Log.e("SysUpgradeManager", "TmpeulaZipPath is null,do not show Eula message ");
                interfaceC0014o.b(i, 0, versionDataA);
                return;
            }
            String str = strG + ".tar.gz";
            if (!TextUtils.isEmpty(str) && com.hisense.tv.ota.W.k.j(versionDataA.c(), str)) {
                com.hisense.tv.ota.W.a.a(str);
                C(str);
                this.c.p(new File(str));
                SystemClock.sleep(500L);
                String str2 = s;
                if (str2 != null) {
                    versionDataA.l(str2);
                } else {
                    SystemClock.sleep(2000L);
                    String str3 = s;
                    if (str3 != null) {
                        versionDataA.l(str3);
                        this.c.o(new File(s));
                    } else {
                        Log.e("SysUpgradeManager", "can not get eula path from EulaAPK");
                    }
                }
            }
        }
        interfaceC0014o.b(i, 0, versionDataA);
    }

    public static C0016q t(Context context) {
        if (r == null) {
            synchronized (C0016q.class) {
                if (r == null) {
                    r = new C0016q(context);
                }
            }
        }
        return r;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void x(boolean z) {
        int i;
        Q qR = Q.r((Context) this.f46a.get());
        SysUpgradeState sysUpgradeStateB = C0019t.a().b();
        Log.i("SysUpgradeManager", "manual start state: " + sysUpgradeStateB + " isFTEBring =" + z);
        SystemProperties.set("sys.vt.ota.manual.start", "true");
        if (com.hisense.tv.ota.W.k.y((Context) this.f46a.get()) && !z) {
            SystemClock.sleep(100L);
            qR.E(this.c);
            d(5, 3000);
            return;
        }
        this.c.l(false);
        this.c.q(z);
        this.c.m(false);
        this.c.s(false);
        if (!z) {
            switch (C0009j.f40a[sysUpgradeStateB.ordinal()]) {
                case 1:
                    qR.B(this.c);
                    break;
                case 2:
                case 18:
                    qR.A(this.c);
                    break;
                case 3:
                case 4:
                case 19:
                    qR.C(this.c);
                    break;
                case 5:
                    qR.E(this.c);
                    break;
                case 6:
                    qR.E(this.c);
                    break;
                case 7:
                    Log.i("SysUpgradeManager", "canceling version check, show checking ui, then post check action");
                    qR.E(this.c);
                    this.b.post(this.q);
                    break;
                case 8:
                case 20:
                    qR.v(-3);
                    break;
                case 9:
                    i = -2;
                    qR.v(i);
                    break;
                case 10:
                case 12:
                    qR.E(this.c);
                    this.b.post(this.q);
                    break;
                case 11:
                    qR.u(this.c);
                    break;
                case 13:
                    i = -4;
                    qR.v(i);
                    break;
                case 14:
                case 15:
                    qR.D(this.c);
                    break;
                case 16:
                    i = -6;
                    qR.v(i);
                    break;
                case 17:
                    i = -9;
                    qR.v(i);
                    break;
                case 21:
                    i = -7;
                    qR.v(i);
                    break;
            }
            return;
        }
        qR.x(this.c);
        I(false, 1, false);
    }

    public void A(com.hisense.tv.ota.W.i iVar) {
        this.h.d(iVar);
    }

    public void B(com.hisense.tv.ota.W.j jVar) {
        this.g.c(jVar);
    }

    public void C(String str) {
        String str2;
        if (str == null) {
            str2 = "message is null.\n";
        } else {
            Context context = (Context) this.f46a.get();
            Intent intent = new Intent("ota.eula.message");
            intent.setPackage("com.vt.eula");
            intent.addFlags(268435456);
            String strSubstring = str.substring(str.indexOf("eula_") + 5, str.indexOf(".tar.gz"));
            Log.i("SysUpgradeManager", "version = " + strSubstring);
            intent.putExtra("eula_version", strSubstring);
            intent.putExtra("eula_path", str);
            Log.i("SysUpgradeManager", "eula_path = " + str);
            context.sendBroadcast(intent);
            str2 = " notify Eula APK to show Eula.\n";
        }
        Log.i("SysUpgradeManager", str2);
    }

    public void D(String str) {
        String str2;
        if (str == null) {
            str2 = "message is null.\n";
        } else {
            Context context = (Context) this.f46a.get();
            Intent intent = new Intent("ota.fte.message");
            intent.addFlags(268435456);
            intent.setAction("com.hisense.ota.UPDATE");
            intent.setPackage("com.hisense.fte");
            intent.putExtra("backToFte", str);
            context.sendBroadcast(intent);
            str2 = " notify FTE APK done.\n";
        }
        Log.i("SysUpgradeManager", str2);
    }

    public void E(boolean z) {
        C0002c c0002c = this.c;
        if (c0002c != null) {
            c0002c.m(z);
        }
    }

    public void F() {
        C0002c c0002c = this.c;
        if (c0002c == null || c0002c.e() == null) {
            Log.e("SysUpgradeManager", "set not remind failed, session is null or version data is null");
        } else {
            com.hisense.tv.ota.W.k.L((Context) this.f46a.get(), this.c.e().e());
        }
        C0019t c0019tA = C0019t.a();
        c0019tA.c(SysUpgradeState.UPGRADE_CANCEL);
        c0019tA.c(SysUpgradeState.IDLE);
    }

    public void G(String str) {
        Log.i("SysUpgradeManager", "setReceivedEulaPath= " + str);
        if (str == null) {
            Log.i("SysUpgradeManager", " path is null.\n");
        } else {
            s = str;
        }
    }

    public void H(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        boolean z6;
        Log.i("SysUpgradeManager", "OTA start, auto: " + z + ", isSTR: " + z2 + ", isScreenOff: " + z3 + ",isAgentBring: " + z4 + ",isFTEBring: " + z5);
        if (z) {
            k(z2, z3, z4, z5);
            return;
        }
        if (z5) {
            Log.i("SysUpgradeManager", "FTE bring OTA now ");
            z6 = true;
        } else {
            z6 = false;
        }
        x(z6);
    }

    public synchronized void I(boolean z, int i, boolean z2) {
        C0019t c0019tA = C0019t.a();
        SysUpgradeState sysUpgradeStateB = c0019tA.b();
        if (this.c.i()) {
            Log.i("SysUpgradeManager", "FTE bring OTA, do not check state");
        } else if (!SysUpgradeState.IDLE.equals(sysUpgradeStateB)) {
            Log.e("SysUpgradeManager", "state is not idle, won't start again. state: " + sysUpgradeStateB);
            return;
        }
        c0019tA.c(SysUpgradeState.CHECKING);
        this.c.s(false);
        this.c.l(z);
        if (z2) {
            this.b.postDelayed(new RunnableC0003d(this, z, i), 15000L);
        } else {
            this.b.post(new RunnableC0004e(this, z, i));
        }
    }

    public void J() {
        C0019t c0019tA = C0019t.a();
        Log.i("SysUpgradeManager", "enter startDownloadPkg function");
        if (!SysUpgradeState.FOUND_NEW_VERSION.equals(c0019tA.b())) {
            Log.e("SysUpgradeManager", "state is not found_new_version, won't start download");
            this.f.b(null, -4);
            return;
        }
        d(3, 200);
        c0019tA.c(SysUpgradeState.DOWNLOADING);
        SystemProperties.set("sys.vt.ota.state", "2");
        VersionData versionDataE = this.c.e();
        if (versionDataE != null) {
            s(this.c.d(), versionDataE.a(), versionDataE.f(), versionDataE.e(), versionDataE.d(), this.f);
            return;
        }
        Log.e("SysUpgradeManager", "start download package failed, version data is null");
        c0019tA.c(SysUpgradeState.DOWNLOAD_FAILED);
        this.f.b(null, -4);
    }

    public void K() {
        Log.i("SysUpgradeManager", "start install package");
        C0019t c0019tA = C0019t.a();
        if (SysUpgradeState.VERIFIED.equals(c0019tA.b())) {
            this.b.post(new RunnableC0006g(this, c0019tA));
        } else {
            Log.e("SysUpgradeManager", "start install failed, state is not verified");
            this.h.c(-6, new Object[0]);
        }
    }

    public boolean L() {
        if (!com.hisense.tv.ota.W.k.a()) {
            SystemProperties.set("sys.powerctl", "reboot,quiescent");
            return true;
        }
        Log.i("SysUpgradeManager", "current status is screen_on now");
        C0019t.a().c(SysUpgradeState.WAIT_REBOOT);
        return true;
    }

    public boolean M() {
        Context context = (Context) this.f46a.get();
        ((PowerManager) context.getSystemService("power")).reboot(null);
        return true;
    }

    public void N() {
        Log.i("SysUpgradeManager", "startVerifyPkg");
        C0019t c0019tA = C0019t.a();
        if (!SysUpgradeState.DOWNLOADED.equals(c0019tA.b())) {
            Log.e("SysUpgradeManager", "start verify package failed, state is not downloaded");
            this.g.a(5, new Object[0]);
            return;
        }
        String str = SystemProperties.get("ro.build.type");
        if (str.contains("debug")) {
            Log.i("SysUpgradeManager", "Native Version Build type=" + str + " maybe install failed");
        }
        this.b.post(new RunnableC0005f(this, c0019tA));
    }

    public void O(int i, File file, com.hisense.tv.ota.W.j jVar) {
        if (jVar == null) {
            Log.e("SysUpgradeManager", "verify package failed, listener is null");
            return;
        }
        if (i < 0) {
            Log.e("SysUpgradeManager", "verify package failed, invalid type: " + i);
            jVar.a(5, new Object[0]);
            return;
        }
        String strH = this.c.e().h();
        Log.i("SysUpgradeManager", "Cloud upgrade package md5 =" + strH);
        if (!com.hisense.tv.ota.W.k.E(this.c.c(), strH)) {
            Log.e("SysUpgradeManager", "MD5 check failed");
            jVar.a(5, new Object[0]);
        } else {
            if (!C0000a.b) {
                com.hisense.tv.ota.W.k.O(file.getAbsolutePath(), jVar);
                return;
            }
            for (int i2 = 0; i2 < 30; i2++) {
                jVar.onProgress(i2 * 3);
                SystemClock.sleep(1000L);
            }
            jVar.onProgress(100);
            jVar.b();
        }
    }

    public void a() {
        String str;
        SysUpgradeState sysUpgradeStateB = C0019t.a().b();
        Log.i("SysUpgradeManager", "screen off start, state: " + sysUpgradeStateB);
        Log.i("SysUpgradeManager", "based on the current status, OTA begin to work");
        t((Context) this.f46a.get()).E(true);
        t = 1;
        int i = C0009j.f40a[sysUpgradeStateB.ordinal()];
        if (i != 1) {
            if (i == 2) {
                Log.i("SysUpgradeManager", "Status is VERIFIED,begin to install");
                K();
                return;
            }
            if (i == 3) {
                str = "Status is INSTALLED,begin to reboot";
            } else if (i != 4) {
                return;
            }
            L();
        }
        if (SystemProperties.getInt("sys.vt.ota.state", 0) != 7) {
            Log.i("SysUpgradeManager", "Status is FOUND_NEW_VERSION,begin to download the OTA package");
            com.hisense.tv.ota.W.k.N((Context) this.f46a.get(), "03");
            Log.i("SysUpgradeManager", " upload EVENT_CODE_SELECT_DOWNLOAD to server ");
            if (!this.c.h()) {
                Log.i("SysUpgradeManager", "begin to startDownload Pkg ");
                J();
                return;
            } else {
                Log.i("SysUpgradeManager", "package exists, won't download, just to verify ");
                C0019t.a().c(SysUpgradeState.DOWNLOADED);
                N();
                return;
            }
        }
        str = "new version already installed, just to call quiescent reboot";
        Log.i("SysUpgradeManager", str);
        L();
    }

    public void b() {
        if (SystemProperties.get("sys.vt.ota.state", "0").equals("7")) {
            Log.i("SysUpgradeManager", "DoInstallEndingwork interface already called, just return");
            return;
        }
        com.hisense.tv.ota.W.l lVar = new com.hisense.tv.ota.W.l((Context) this.f46a.get());
        com.hisense.tv.ota.W.k.J((Context) this.f46a.get(), lVar.d(), lVar.j(), this.c.e().e());
        Log.i("SysUpgradeManager", "if A/B finfished  delete OTA package");
        if (!this.c.c().delete()) {
            Log.e("SysUpgradeManager", "delete package file failed");
        }
        if (this.c.b() != null) {
            Log.i("SysUpgradeManager", "delete Eula zip file");
            this.c.b().delete();
            if (this.c.a() != null) {
                Log.i("SysUpgradeManager", "delete Eula text file");
                this.c.a().delete();
            }
        }
        this.h.a(100);
        this.h.b();
    }

    public void c(int i) {
        String str;
        if (i == 0) {
            Log.i("SysUpgradeManager", "A/B update status is idle");
            this.h.a(1);
            C0019t.a().c(SysUpgradeState.INSTALLING);
            SystemProperties.set("sys.vt.ota.state", "6");
            return;
        }
        if (i == 1) {
            str = "A/B update status is checking_for_update";
        } else if (i == 2) {
            Log.i("SysUpgradeManager", "A/B update status is update_available");
            com.hisense.tv.ota.W.k.N((Context) this.f46a.get(), "09");
            str = "begin install,upload EVENT_CODE_SELECT_UPGRADE to server ";
        } else if (i != 4) {
            switch (i) {
                case 7:
                    this.h.c(-6, new Object[0]);
                    return;
                case 8:
                    this.h.c(-6, new Object[0]);
                    return;
                case 9:
                    this.h.c(-6, new Object[0]);
                    return;
                case 10:
                    this.h.c(-6, new Object[0]);
                    return;
                case 11:
                    str = "A/B update status is cleanup_previous_update";
                    break;
                default:
                    str = "unknown update_engine status status= " + i;
                    break;
            }
        } else {
            str = "A/B update status is verifying";
        }
        Log.i("SysUpgradeManager", str);
    }

    public void d(int i, int i2) {
        Message message = new Message();
        message.what = i;
        this.p.removeMessages(i);
        this.p.sendMessageDelayed(message, i2);
    }

    public void l() {
        this.b.removeCallbacks(this.q);
        C0019t c0019tA = C0019t.a();
        if (!SysUpgradeState.CHECKING.equals(c0019tA.b())) {
            Log.e("SysUpgradeManager", "cancel check failed, state is not checking");
        } else {
            this.c.s(true);
            c0019tA.c(SysUpgradeState.CHECK_CANCELING);
        }
    }

    public void m() {
        C0019t c0019tA = C0019t.a();
        if (SysUpgradeState.DOWNLOADING.equals(c0019tA.b())) {
            c0019tA.c(SysUpgradeState.DOWNLOAD_CANCELING);
        } else {
            Log.e("SysUpgradeManager", "cancel download failed, state is not downloading");
        }
    }

    public void n() {
        C0019t c0019tA = C0019t.a();
        if (!SysUpgradeState.FOUND_NEW_VERSION.equals(c0019tA.b())) {
            Log.e("SysUpgradeManager", "cancel upgrade failed, state is not found new version");
        } else {
            c0019tA.c(SysUpgradeState.UPGRADE_CANCEL);
            c0019tA.c(SysUpgradeState.IDLE);
        }
    }

    public void o(boolean z, int i, InterfaceC0014o interfaceC0014o) {
        Log.i("SysUpgradeManager", "checkVersion, auto: " + z + ", type: " + i);
        SystemClock.sleep(1500L);
        com.hisense.tv.ota.W.k.c((Context) this.f46a.get());
        if (!this.c.i()) {
            while (com.hisense.tv.ota.W.k.x((Context) this.f46a.get())) {
                Log.i("SysUpgradeManager", "in FTE now, won't check upgrade version");
                SystemClock.sleep(1500L);
            }
        }
        this.c.t(i);
        if (interfaceC0014o == null) {
            Log.e("SysUpgradeManager", "check version failed, listener is null");
            C0019t.a().c(SysUpgradeState.CHECK_FAILED);
            return;
        }
        if (i < 0) {
            Log.e("SysUpgradeManager", "check version failed, invalid type: " + i);
            interfaceC0014o.b(i, -1, null);
            return;
        }
        if (this.f46a == null) {
            Log.e("SysUpgradeManager", "check version failed, context is null");
            interfaceC0014o.b(i, -3, null);
            return;
        }
        if (this.c.f()) {
            while (!com.hisense.tv.ota.W.k.z((Context) this.f46a.get())) {
                Log.i("SysUpgradeManager", "Network is not ready");
                if (this.n >= 12) {
                    break;
                }
                SystemClock.sleep(2000L);
                this.n++;
            }
        }
        boolean z2 = com.hisense.tv.ota.W.k.z((Context) this.f46a.get());
        if (this.c.j()) {
            interfaceC0014o.a();
            return;
        }
        if (!z2) {
            Log.e("SysUpgradeManager", "check version failed, network is not ready");
            interfaceC0014o.b(i, -2, null);
            return;
        }
        com.hisense.tv.ota.W.l lVar = new com.hisense.tv.ota.W.l((Context) this.f46a.get());
        String strI = lVar.i();
        String strB = lVar.b();
        String strA = lVar.a();
        String strH = lVar.h();
        String strD = i == 0 ? lVar.d() : lVar.j();
        Log.i("SysUpgradeManager", "machine type : " + strI);
        Log.i("SysUpgradeManager", "curversion   : " + strD);
        Log.i("SysUpgradeManager", "country code : " + strB);
        Log.i("SysUpgradeManager", "brand code   : " + strA);
        if (this.c.j()) {
            interfaceC0014o.a();
            return;
        }
        if (TextUtils.isEmpty(strI)) {
            Log.e("SysUpgradeManager", "check version failed, can't get machine type");
            interfaceC0014o.b(i, -4, null);
        } else if (!TextUtils.isEmpty(strD)) {
            r(i, p(strI, strD, strB, strA, strH), interfaceC0014o);
        } else {
            Log.e("SysUpgradeManager", "check version failed, can't get current version");
            interfaceC0014o.b(i, -5, null);
        }
    }

    public void s(int i, String str, String str2, String str3, long j, com.hisense.tv.ota.V.c cVar) {
        if (cVar == null) {
            Log.e("SysUpgradeManager", "download package failed, listener is null");
        }
        Log.i("SysUpgradeManager", " Step #2 downloadPackage");
        String strR = com.hisense.tv.ota.W.k.r((Context) this.f46a.get(), str2, str3, j);
        Log.i("SysUpgradeManager", " downloadPackage destPath = " + strR);
        if (!TextUtils.isEmpty(strR)) {
            q(i, str, strR, j, cVar);
        } else {
            Log.e("SysUpgradeManager", "download package failed, space not enough");
            cVar.b("", -1);
        }
    }

    public C0002c u() {
        return this.c;
    }

    public boolean v(Context context, String str, com.hisense.tv.ota.W.i iVar) throws Throwable {
        String str2;
        if (context == null) {
            str2 = "installPackage failed, context is empty";
        } else if (TextUtils.isEmpty(str)) {
            str2 = "installPackage failed, package path is empty";
        } else if (com.hisense.tv.ota.W.m.b(str)) {
            String str3 = "file://" + str;
            Log.i("SysUpgradeManager", "installPackage, zipurl = " + str3);
            this.m = com.hisense.tv.ota.W.e.a(str);
            Log.i("SysUpgradeManager", "installPackage, IS_INCREMENTAL_FLAG = " + this.m);
            if (this.m == -1) {
                str2 = "getIncrementalFlagFromZipFile error";
            } else {
                long[] jArrB = com.hisense.tv.ota.W.e.b(str);
                if (jArrB == null) {
                    str2 = "getOffsetAndSizeFromZipFile error";
                } else {
                    Log.i("SysUpgradeManager", "installPackage, offset = " + jArrB[0]);
                    Log.i("SysUpgradeManager", "installPackage, size = " + jArrB[1]);
                    String[] strArrC = com.hisense.tv.ota.W.e.c(str);
                    if (strArrC != null) {
                        this.h.a(1);
                        this.d.bind(this.e);
                        try {
                            this.d.applyPayload(str3, jArrB[0], jArrB[1], strArrC);
                        } catch (Exception e) {
                            Log.e("SysUpgradeManager", "UpdateEngine failed to apply the update", e);
                        }
                        return true;
                    }
                    str2 = "getPropertiesFromZipFile error";
                }
            }
        } else {
            str2 = "installPackage failed, package file is not exists";
        }
        Log.e("SysUpgradeManager", str2);
        return false;
    }

    public boolean w(int i, File file, com.hisense.tv.ota.W.i iVar) {
        String str;
        if (i < 0) {
            str = "install package failed, invalid type: " + i;
        } else if (file == null) {
            str = "install package failed, package file is null";
        } else if (!file.isFile()) {
            str = "install package failed, package file is not file";
        } else if (file.exists()) {
            WeakReference weakReference = this.f46a;
            if (weakReference != null && weakReference.get() != null) {
                return v((Context) this.f46a.get(), file.getAbsolutePath(), iVar);
            }
            str = "install package failed, context is null";
        } else {
            str = "install package failed, package file not exists";
        }
        Log.e("SysUpgradeManager", str);
        return false;
    }

    public void y(InterfaceC0014o interfaceC0014o) {
        this.i.c(interfaceC0014o);
    }

    public void z(com.hisense.tv.ota.V.c cVar) {
        this.f.f(cVar);
    }
}
