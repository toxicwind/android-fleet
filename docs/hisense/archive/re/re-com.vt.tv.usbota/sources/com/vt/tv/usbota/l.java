package com.vt.tv.usbota;

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
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class l {
    private static l q;
    private WeakReference a;
    private Handler b;
    private C0000a c;
    private final UpdateEngine d;
    private final g e = new g(this);
    private i f = new i(this);
    private h g = new h(this);
    private k h = new k(this);
    public int i = 0;
    public int j = 49;
    public int k = 0;
    public int l = 0;
    public int m = 0;
    public String n = null;
    public String o = null;
    private final Handler p = new HandlerC0003d(this);

    private l(Context context) {
        this.a = new WeakReference(context);
        HandlerThread handlerThread = new HandlerThread("SysUpgrade");
        handlerThread.start();
        this.b = new Handler(handlerThread.getLooper());
        this.d = new UpdateEngine();
        this.c = new C0000a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, int i2) {
        Message message = new Message();
        message.what = i;
        this.p.removeMessages(i);
        this.p.sendMessageDelayed(message, i2);
    }

    public static l k(Context context) {
        if (q == null) {
            synchronized (l.class) {
                if (q == null) {
                    q = new l(context);
                }
            }
        }
        return q;
    }

    public void a(int i) {
        String str;
        if (i == 0) {
            Log.i("SysUpgradeManager", "A/B update status is idle");
            this.g.c(1);
            return;
        }
        if (i == 1) {
            str = "A/B update status is checking_for_update";
        } else if (i == 2) {
            str = "A/B update status is update_available";
        } else if (i != 4) {
            switch (i) {
                case 7:
                    this.g.a(5, new Object[0]);
                    return;
                case 8:
                    this.g.a(5, new Object[0]);
                    return;
                case 9:
                    this.g.a(5, new Object[0]);
                    return;
                case 10:
                    this.g.a(5, new Object[0]);
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

    public void i() {
        m mVarA = m.a();
        if (!SysUpgradeState.FOUND_NEW_VERSION.equals(mVarA.b())) {
            Log.e("SysUpgradeManager", "cancel upgrade failed, state is not found new version");
        } else {
            mVarA.c(SysUpgradeState.UPGRADE_CANCEL);
            mVarA.c(SysUpgradeState.IDLE);
        }
    }

    /* JADX WARN: Code duplicated, block: B:90:0x0459  */
    /* JADX WARN: Code duplicated, block: B:91:0x0467  */
    /* JADX WARN: Code duplicated, block: B:93:0x046d  */
    /* JADX WARN: Code duplicated, block: B:94:0x0487 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:95:0x0489  */
    /* JADX WARN: Code duplicated, block: B:96:0x04a3  */
    /* JADX WARN: Instruction removed from duplicated block: B:93:0x046d, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:95:0x0489, please report this as an issue */
    public void j(j jVar) throws Throwable {
        j jVar2;
        int i;
        int i2;
        int i3;
        Iterator it;
        int i4;
        int i5;
        Log.i("SysUpgradeManager", "checkVersion ");
        SystemClock.sleep(2500L);
        if (jVar == null) {
            Log.e("SysUpgradeManager", "check version failed, listener is null");
            m.a().c(SysUpgradeState.CHECK_FAILED);
            return;
        }
        String strC = com.vt.tv.usbota.G.e.c();
        Log.i("SysUpgradeManager", "machineVersion: " + strC);
        this.c.l(strC);
        String str = SystemProperties.get("ro.vt.product.model");
        Log.i("SysUpgradeManager", "initMachineType machine name: " + str);
        if (TextUtils.isEmpty(str)) {
            Log.e("SysUpgradeManager", "initMachineType failed, machine name is empty");
        }
        List listB = com.vt.tv.usbota.G.e.b((Context) this.a.get());
        if (listB == null || listB.isEmpty()) {
            jVar2 = jVar;
            Log.i("SysUpgradeManager", "There is no external usb storage!!!");
            i = 1;
        } else {
            List listA = com.vt.tv.usbota.G.e.a((Context) this.a.get(), listB);
            if (listA == null) {
                jVar.a(2);
            } else {
                if (listA != null && !listA.isEmpty()) {
                    Iterator it2 = listA.iterator();
                    int i6 = 0;
                    int i7 = 0;
                    while (it2.hasNext()) {
                        File file = new File((String) it2.next());
                        if (file.isFile()) {
                            it = it2;
                            i4 = i6;
                            i5 = i7;
                            if (file.toString().contains("_To_") || file.toString().contains("_to_")) {
                                Log.i("SysUpgradeManager", "Find USB incremental OTA package = " + file.toString());
                                int iIndexOf = file.toString().contains("_To_") ? file.toString().indexOf("_To_") : file.toString().indexOf("_to_");
                                String strSubstring = file.toString().substring(iIndexOf - 5, iIndexOf);
                                Log.i("SysUpgradeManager", "sourceVersoin = " + strSubstring);
                                String strSubstring2 = file.toString().substring(iIndexOf + 4, file.toString().indexOf(".zip"));
                                Log.i("SysUpgradeManager", "targetVersion = " + strSubstring2);
                                String strSubstring3 = file.toString().substring(file.toString().indexOf("USBOTA_") + 7, iIndexOf + (-6));
                                Log.i("SysUpgradeManager", "pkgMachineName = " + strSubstring3);
                                if (!TextUtils.isEmpty(strSubstring) && !TextUtils.isEmpty(strC) && strC.equalsIgnoreCase(strSubstring) && strSubstring3.equalsIgnoreCase(str)) {
                                    Log.i("SysUpgradeManager", "find right incremental OTA package = " + file.toString());
                                    HashMap mapA = com.vt.tv.usbota.G.a.a(file.toString());
                                    if (mapA != null) {
                                        this.l = Integer.valueOf((String) mapA.get("incremental_flag")).intValue();
                                        Log.i("SysUpgradeManager", "IS_INCREMENTAL_FLAG =" + this.l);
                                        this.n = (String) mapA.get("security_patch_level");
                                        this.o = (String) mapA.get("post-build");
                                        Log.i("SysUpgradeManager", "package_build_type =" + this.o);
                                        if (this.o.contains("debug")) {
                                            String str2 = SystemProperties.get("ro.build.type");
                                            Log.i("SysUpgradeManager", "NativeVersionBuild =" + str2);
                                            if (str2.contains("debug")) {
                                                Log.i("SysUpgradeManager", "Incremental upgrade, source verison build type == target version build type");
                                                this.c.j(false);
                                            } else {
                                                Log.i("SysUpgradeManager", "Incremental upgrade, source verison build type != target version build type");
                                            }
                                        }
                                        i6 = i4 + 1;
                                        this.c.k(strSubstring2);
                                        this.c.m(file);
                                        this.c.n("Incremental OTA upgrade, please confirm the version number.\n");
                                    }
                                    it2 = it;
                                    i6 = i4;
                                    i7 = i5;
                                } else {
                                    i6 = i4;
                                }
                                i7 = i5;
                                it2 = it;
                            } else {
                                Log.i("SysUpgradeManager", "Find USB full OTA package = " + file.toString());
                                int iIndexOf2 = file.toString().indexOf("zip");
                                String strSubstring4 = file.toString().substring(iIndexOf2 + (-6), iIndexOf2 + (-1));
                                Log.i("SysUpgradeManager", "FullTargetVersion = " + strSubstring4);
                                String strSubstring5 = file.toString().substring(file.toString().indexOf("USBOTA_") + 7, iIndexOf2 + (-7));
                                Log.i("SysUpgradeManager", "pkgMachineName = " + strSubstring5);
                                boolean z = (TextUtils.isEmpty(str) || TextUtils.isEmpty(strSubstring5) || !strSubstring5.equalsIgnoreCase(str)) ? false : true;
                                Log.i("SysUpgradeManager", "machineNameFlag = " + z);
                                if (z) {
                                    int iCompareTo = strC.compareTo(strSubstring4);
                                    HashMap mapA2 = com.vt.tv.usbota.G.a.a(file.toString());
                                    if (mapA2 != null) {
                                        Log.i("SysUpgradeManager", "Package_Time_stamp = " + ((String) mapA2.get("post-timestamp")));
                                        Log.i("SysUpgradeManager", "machine Time Stamp = " + SystemProperties.get("ro.build.date.utc"));
                                        Log.i("SysUpgradeManager", "checkVersion verison compare flag = " + iCompareTo);
                                        if (iCompareTo <= 0 && z) {
                                            Log.i("SysUpgradeManager", "find right full OTA package = " + file.toString());
                                            HashMap mapA3 = com.vt.tv.usbota.G.a.a(file.toString());
                                            this.l = Integer.valueOf((String) mapA3.get("incremental_flag")).intValue();
                                            Log.i("SysUpgradeManager", "IS_INCREMENTAL_FLAG =" + this.l);
                                            this.n = (String) mapA3.get("security_patch_level");
                                            this.o = (String) mapA3.get("post-build");
                                            Log.i("SysUpgradeManager", "package_build_type =" + this.o);
                                            if (this.o.contains("debug")) {
                                                String str3 = SystemProperties.get("ro.build.type");
                                                Log.i("SysUpgradeManager", "NativeVersionBuild =" + str3);
                                                if (str3.contains("debug")) {
                                                    Log.i("SysUpgradeManager", "full upgrade, source verison build type == target version build type");
                                                    this.c.j(false);
                                                } else {
                                                    Log.i("SysUpgradeManager", "full upgrade, source verison build type != target version build type");
                                                    Log.i("SysUpgradeManager", "but we will ignore this");
                                                }
                                            }
                                            i7 = i5 + 1;
                                            this.c.k(strSubstring4);
                                            this.c.m(file);
                                            this.c.n("Full OTA upgrade, please confirm the version number.\n");
                                            i6 = i4;
                                        }
                                        it2 = it;
                                    }
                                }
                                it2 = it;
                                i6 = i4;
                                i7 = i5;
                            }
                            Log.e("SysUpgradeManager", "getNeedMessageFromZipFile error");
                            it2 = it;
                            i6 = i4;
                            i7 = i5;
                        } else {
                            it = it2;
                            i4 = i6;
                            i5 = i7;
                        }
                        i6 = i4;
                        i7 = i5;
                        it2 = it;
                    }
                    i2 = i6;
                    i3 = i7;
                }
                if (i2 > 1) {
                    Log.i("SysUpgradeManager", "There are multiple OTA package ");
                    this.c.g();
                    i = 3;
                    jVar2 = jVar;
                } else {
                    jVar2 = jVar;
                    if (i2 == 1) {
                        Log.i("SysUpgradeManager", "Find one OTA incremental package ");
                        Log.i("SysUpgradeManager", "fullPackageNumber = " + i3);
                        i = 100;
                    } else if (i3 >= 1) {
                        Log.i("SysUpgradeManager", "Find OTA full package ");
                        Log.i("SysUpgradeManager", "fullPackageNumber = " + i3);
                        i = 200;
                    } else {
                        if (i2 == 0 && i3 != 0) {
                            return;
                        }
                        Log.i("SysUpgradeManager", "Find no applicabel OTA package ");
                        i = 4;
                    }
                }
            }
            i3 = 0;
            i2 = 0;
            if (i2 > 1) {
                Log.i("SysUpgradeManager", "There are multiple OTA package ");
                this.c.g();
                i = 3;
                jVar2 = jVar;
            } else {
                jVar2 = jVar;
                if (i2 == 1) {
                    Log.i("SysUpgradeManager", "Find one OTA incremental package ");
                    Log.i("SysUpgradeManager", "fullPackageNumber = " + i3);
                    i = 100;
                } else if (i3 >= 1) {
                    Log.i("SysUpgradeManager", "Find OTA full package ");
                    Log.i("SysUpgradeManager", "fullPackageNumber = " + i3);
                    i = 200;
                } else {
                    if (i2 == 0) {
                    }
                    Log.i("SysUpgradeManager", "Find no applicabel OTA package ");
                    i = 4;
                }
            }
        }
        jVar2.a(i);
    }

    public C0000a l() {
        return this.c;
    }

    public boolean m(Context context, String str, com.vt.tv.usbota.G.c cVar) throws Throwable {
        String str2;
        if (context == null) {
            str2 = "installPackage failed, context is empty";
        } else if (TextUtils.isEmpty(str)) {
            str2 = "installPackage failed, package path is empty";
        } else if (com.vt.tv.usbota.G.f.a(str)) {
            String str3 = "file://" + str;
            Log.i("SysUpgradeManager", "installPackage, zipurl = " + str3);
            long[] jArrB = com.vt.tv.usbota.G.a.b(str);
            if (jArrB == null) {
                str2 = "getOffsetAndSizeFromZipFile error";
            } else {
                Log.i("SysUpgradeManager", "installPackage, offset = " + jArrB[0]);
                Log.i("SysUpgradeManager", "installPackage, size = " + jArrB[1]);
                this.g.c(1);
                String[] strArrC = com.vt.tv.usbota.G.a.c(str);
                if (strArrC != null) {
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
        } else {
            str2 = "installPackage failed, package file is not exists";
        }
        Log.e("SysUpgradeManager", str2);
        return false;
    }

    public boolean n(File file, com.vt.tv.usbota.G.c cVar) {
        String str;
        if (file == null) {
            str = "install package failed, package file is null";
        } else if (!file.isFile()) {
            str = "install package failed, package file is not file";
        } else if (file.exists()) {
            WeakReference weakReference = this.a;
            if (weakReference != null && weakReference.get() != null) {
                return m((Context) this.a.get(), file.getAbsolutePath(), cVar);
            }
            str = "install package failed, context is null";
        } else {
            str = "install package failed, package file not exists";
        }
        Log.e("SysUpgradeManager", str);
        return false;
    }

    public void o(j jVar) {
        this.h.b(jVar);
    }

    public void p(com.vt.tv.usbota.G.c cVar) {
        this.g.d(cVar);
    }

    public void q(com.vt.tv.usbota.G.d dVar) {
        this.f.c(dVar);
    }

    public void r(boolean z) {
        C0000a c0000a = this.c;
        if (c0000a != null) {
            c0000a.i(z);
        }
    }

    public void s(boolean z) {
        int i;
        E eJ = E.j((Context) this.a.get());
        SysUpgradeState sysUpgradeStateB = m.a().b();
        Log.i("SysUpgradeManager", "manual start state: " + sysUpgradeStateB);
        this.c.h(false);
        this.c.i(false);
        switch (f.a[sysUpgradeStateB.ordinal()]) {
            case 1:
                eJ.r(this.c);
                t(z);
                break;
            case 3:
                Log.i("SysUpgradeManager", "canceling version check, show checking ui, then post check action");
            case 2:
                eJ.r(this.c);
                break;
            case 4:
                i = 4;
                eJ.m(i);
                break;
            case 5:
                eJ.o(this.c);
                break;
            case 6:
                i = 2;
                eJ.m(i);
                break;
            case 7:
                eJ.q(this.c);
                break;
            case 8:
                i = 8;
                eJ.m(i);
                break;
            case 9:
            case 10:
                eJ.n(this.c);
                break;
            case 11:
            case 13:
            case 14:
                eJ.p(this.c);
                break;
            case 12:
                i = 11;
                eJ.m(i);
                break;
            case 15:
                i = 9;
                eJ.m(i);
                break;
        }
    }

    public synchronized void t(boolean z) {
        m mVarA = m.a();
        SysUpgradeState sysUpgradeStateB = mVarA.b();
        if (SysUpgradeState.IDLE.equals(sysUpgradeStateB)) {
            mVarA.c(SysUpgradeState.CHECKING);
            this.c.g();
            this.b.post(new RunnableC0004e(this));
        } else {
            Log.e("SysUpgradeManager", "state is not idle, won't start again. state: " + sysUpgradeStateB);
        }
    }

    public void u() {
        Log.i("SysUpgradeManager", "start install package");
        m mVarA = m.a();
        if (!SysUpgradeState.VERIFIED.equals(mVarA.b())) {
            Log.e("SysUpgradeManager", "start install failed, state is not verified");
            E.j((Context) this.a.get()).m(10);
            return;
        }
        if (this.c.f()) {
            Log.i("SysUpgradeManager", "debug OTA to debug,we verify security patch level during install ");
            if (this.l == 0) {
                Log.i("SysUpgradeManager", "package_Security_Level = " + this.n);
                String str = SystemProperties.get("ro.build.version.security_patch");
                Log.i("SysUpgradeManager", "nativeSecurityLevel =" + str);
                int iCompareTo = str.compareTo(this.n);
                if (iCompareTo > 0) {
                    Log.i("SysUpgradeManager", "can not cut down security patch level" + iCompareTo);
                    E.j((Context) this.a.get()).m(10);
                    return;
                }
            }
        }
        this.b.post(new RunnableC0002c(this, mVarA));
    }

    public boolean v() {
        Context context = (Context) this.a.get();
        ((PowerManager) context.getSystemService("power")).reboot(null);
        return true;
    }

    public void w() {
        Log.i("SysUpgradeManager", "startVerifyPkg");
        m mVarA = m.a();
        if (SysUpgradeState.FOUND_NEW_VERSION.equals(mVarA.b())) {
            this.b.post(new RunnableC0001b(this, mVarA));
        } else {
            Log.e("SysUpgradeManager", "start verify package failed, state is not downloaded");
            E.j((Context) this.a.get()).m(0);
        }
    }

    public void x() {
        Log.i("SysUpgradeManager", "Stop Service ");
        Intent intent = new Intent("com.vt.action.usbota");
        intent.setPackage(((Context) this.a.get()).getPackageName());
        ((Context) this.a.get()).stopService(intent);
    }

    public void y(File file, com.vt.tv.usbota.G.d dVar) {
        if (dVar == null) {
            Log.e("SysUpgradeManager", "verify package failed, listener is null");
            return;
        }
        Log.i("SysUpgradeManager", "verifyPkg pkgFile =" + file.toString());
        Log.i("SysUpgradeManager", "package_Security_Level = " + this.n);
        String str = SystemProperties.get("ro.build.version.security_patch");
        Log.i("SysUpgradeManager", "nativeSecurityLevel =" + str);
        int iCompareTo = str.compareTo(this.n);
        if (iCompareTo <= 0) {
            com.vt.tv.usbota.G.e.d(file, dVar);
            return;
        }
        Log.i("SysUpgradeManager", "can not cut down security patch level" + iCompareTo);
        dVar.a(1, new Object[0]);
    }
}
