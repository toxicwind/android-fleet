package com.ju.lib.utils.system;

import android.app.ActivityManager;
import android.content.Context;
import com.ju.lib.utils.log.LogUtil;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class Process {
    public static synchronized String getProcessName(Context context) {
        String str;
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return null;
        }
        int iMyPid = android.os.Process.myPid();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == iMyPid && (str = runningAppProcessInfo.processName) != null) {
                return str;
            }
        }
        return null;
    }

    public static void quitCurrentProcess() {
        LogUtil.i("Process-BaseLib", "quitCurrentProcess");
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}
