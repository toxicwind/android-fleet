package com.hisense.tv.ota;

import android.content.Context;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;
import com.hmct.cloud.sdk.bean.global.ErrorInfo;
import com.hmct.cloud.sdk.bean.upgrade.DevUpgradeReply;
import com.hmct.cloud.sdk.bean.upgrade.FileInfo;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: renamed from: com.hisense.tv.ota.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0001b extends T {
    private VersionData b(String str, HashMap map, Context context) {
        String str2;
        String str3 = (String) map.get("currentVersion");
        a.b.a.a.c.c cVarC = com.hisense.tv.ota.W.d.c(str, context);
        if (cVarC == null) {
            Log.e("HiCloudVersionChecker", "doVersionCheck failed, hicloud upgrade service is null");
            return null;
        }
        HashMap map2 = new HashMap();
        map2.put("accessToken", str);
        map2.put("msgVersion", "11");
        map2.put("msgType", "0");
        map2.put("msgId", "11");
        map2.putAll(map);
        DevUpgradeReply devUpgradeReplyE = cVarC.e(com.hisense.tv.ota.W.d.a(2, context), true, map2);
        if (devUpgradeReplyE == null) {
            str2 = "do version check failed, device upgrade reply is null";
        } else {
            if (devUpgradeReplyE.b() == 0) {
                VersionData versionDataC = c(devUpgradeReplyE);
                if (versionDataC != null) {
                    versionDataC.m(str3);
                }
                return versionDataC;
            }
            ErrorInfo errorInfoA = devUpgradeReplyE.a();
            if (errorInfoA != null) {
                str2 = "do version check failed, error code: " + errorInfoA.a() + ", error name: " + errorInfoA.b();
            } else {
                str2 = "do version check failed, reply code is not success, but can't get error info";
            }
        }
        Log.w("HiCloudVersionChecker", str2);
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:16:0x004b  */
    private VersionData c(DevUpgradeReply devUpgradeReply) {
        boolean z;
        boolean z2;
        boolean z3;
        if (devUpgradeReply == null) {
            Log.e("HiCloudVersionChecker", "get version data failed, update info is null");
            return null;
        }
        List listG = devUpgradeReply.g();
        if (listG == null || listG.isEmpty()) {
            Log.e("HiCloudVersionChecker", "get version data failed, file info list is empty");
            return null;
        }
        FileInfo fileInfo = (FileInfo) listG.get(0);
        int i = devUpgradeReply.i();
        Log.i("HiCloudVersionChecker", "Update Flag from server is " + i);
        if (i == 1) {
            SystemProperties.set("sys.vt.ota.mode", "normal");
            z2 = false;
            z = false;
            z3 = false;
        } else if (i == 2) {
            z = false;
            z3 = false;
            z2 = true;
        } else if (i == 3) {
            SystemProperties.set("sys.vt.ota.mode", "silent");
            z3 = false;
            z2 = true;
            z = true;
        } else if (i == 11) {
            z = false;
            z2 = true;
            z3 = true;
        } else {
            z = false;
            z3 = false;
            z2 = true;
        }
        String strF = devUpgradeReply.f();
        if (fileInfo.b().startsWith("http://")) {
            Log.e("HiCloudVersionChecker", "This is test env now!!!");
            SystemProperties.set("sys.vt.ota.test.env", "true");
        }
        return new VersionData(null, devUpgradeReply.h(), fileInfo.b(), devUpgradeReply.j(), strF, fileInfo.c(), fileInfo.a(), z2, z, z3);
    }

    @Override // com.hisense.tv.ota.T
    public VersionData a(String str, HashMap map, Context context) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            str2 = "check version failed, token is empty";
        } else {
            if (map != null) {
                return b(str, map, context);
            }
            str2 = "check version failed, info map is null";
        }
        Log.e("HiCloudVersionChecker", str2);
        return null;
    }
}
