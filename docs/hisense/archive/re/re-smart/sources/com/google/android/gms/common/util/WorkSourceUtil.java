package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Process;
import android.os.WorkSource;
import android.util.Log;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.firebase.storage.StorageMetadata;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@KeepForSdk
public class WorkSourceUtil {
    public static final int zza = Process.myUid();
    public static final Method zzb;
    public static final Method zzc;
    public static final Method zzd;
    public static final Method zze;
    public static final Method zzf;
    public static final Method zzg;
    public static final Method zzh;
    public static final Method zzi;

    static {
        Method method;
        Method method2;
        Method method3;
        Method method4;
        Method method5;
        Method method6;
        Method method7;
        Method method8 = null;
        try {
            method = WorkSource.class.getMethod("add", Integer.TYPE);
        } catch (Exception unused) {
            method = null;
        }
        zzb = method;
        int i = Build.VERSION.SDK_INT;
        try {
            method2 = WorkSource.class.getMethod("add", Integer.TYPE, String.class);
        } catch (Exception unused2) {
            method2 = null;
        }
        zzc = method2;
        try {
            method3 = WorkSource.class.getMethod(StorageMetadata.SIZE_KEY, new Class[0]);
        } catch (Exception unused3) {
            method3 = null;
        }
        zzd = method3;
        try {
            method4 = WorkSource.class.getMethod("get", Integer.TYPE);
        } catch (Exception unused4) {
            method4 = null;
        }
        zze = method4;
        int i2 = Build.VERSION.SDK_INT;
        try {
            method5 = WorkSource.class.getMethod("getName", Integer.TYPE);
        } catch (Exception unused5) {
            method5 = null;
        }
        zzf = method5;
        if (PlatformVersion.isAtLeastP()) {
            try {
                method6 = WorkSource.class.getMethod("createWorkChain", new Class[0]);
            } catch (Exception e2) {
                Log.w("WorkSourceUtil", "Missing WorkChain API createWorkChain", e2);
                method6 = null;
            }
        } else {
            method6 = null;
        }
        zzg = method6;
        if (PlatformVersion.isAtLeastP()) {
            try {
                method7 = Class.forName("android.os.WorkSource$WorkChain").getMethod("addNode", Integer.TYPE, String.class);
            } catch (Exception e3) {
                Log.w("WorkSourceUtil", "Missing WorkChain class", e3);
                method7 = null;
            }
        } else {
            method7 = null;
        }
        zzh = method7;
        if (PlatformVersion.isAtLeastP()) {
            try {
                method8 = WorkSource.class.getMethod("isEmpty", new Class[0]);
                method8.setAccessible(true);
            } catch (Exception unused6) {
            }
        }
        zzi = method8;
    }

    @KeepForSdk
    public static void add(@NonNull WorkSource workSource, int i, @NonNull String str) {
        Method method = zzc;
        if (method != null) {
            if (str == null) {
                str = "";
            }
            try {
                method.invoke(workSource, Integer.valueOf(i), str);
                return;
            } catch (Exception e2) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e2);
                return;
            }
        }
        Method method2 = zzb;
        if (method2 != null) {
            try {
                method2.invoke(workSource, Integer.valueOf(i));
            } catch (Exception e3) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e3);
            }
        }
    }

    @NonNull
    @KeepForSdk
    public static WorkSource fromPackage(@NonNull Context context, @NonNull String str) {
        if (context != null && context.getPackageManager() != null && str != null) {
            try {
                ApplicationInfo applicationInfo = Wrappers.packageManager(context).getApplicationInfo(str, 0);
                if (applicationInfo == null) {
                    Log.e("WorkSourceUtil", str.length() != 0 ? "Could not get applicationInfo from package: ".concat(str) : new String("Could not get applicationInfo from package: "));
                    return null;
                }
                int i = applicationInfo.uid;
                WorkSource workSource = new WorkSource();
                add(workSource, i, str);
                return workSource;
            } catch (PackageManager.NameNotFoundException unused) {
                Log.e("WorkSourceUtil", str.length() != 0 ? "Could not find package: ".concat(str) : new String("Could not find package: "));
            }
        }
        return null;
    }

    @NonNull
    @KeepForSdk
    public static WorkSource fromPackageAndModuleExperimentalPi(@NonNull Context context, @NonNull String str, @NonNull String str2) {
        if (context == null || context.getPackageManager() == null || str2 == null || str == null) {
            Log.w("WorkSourceUtil", "Unexpected null arguments");
            return null;
        }
        int i = -1;
        try {
            ApplicationInfo applicationInfo = Wrappers.packageManager(context).getApplicationInfo(str, 0);
            if (applicationInfo == null) {
                Log.e("WorkSourceUtil", str.length() != 0 ? "Could not get applicationInfo from package: ".concat(str) : new String("Could not get applicationInfo from package: "));
            } else {
                i = applicationInfo.uid;
            }
        } catch (PackageManager.NameNotFoundException unused) {
            Log.e("WorkSourceUtil", str.length() != 0 ? "Could not find package: ".concat(str) : new String("Could not find package: "));
        }
        if (i < 0) {
            return null;
        }
        WorkSource workSource = new WorkSource();
        Method method = zzg;
        if (method == null || zzh == null) {
            add(workSource, i, str);
        } else {
            try {
                Object objInvoke = method.invoke(workSource, new Object[0]);
                if (i != zza) {
                    zzh.invoke(objInvoke, Integer.valueOf(i), str);
                }
                zzh.invoke(objInvoke, Integer.valueOf(zza), str2);
            } catch (Exception e2) {
                Log.w("WorkSourceUtil", "Unable to assign chained blame through WorkSource", e2);
            }
        }
        return workSource;
    }

    @NonNull
    @KeepForSdk
    public static List<String> getNames(@NonNull WorkSource workSource) {
        ArrayList arrayList = new ArrayList();
        int iZza = workSource == null ? 0 : zza(workSource);
        if (iZza != 0) {
            for (int i = 0; i < iZza; i++) {
                Method method = zzf;
                String str = null;
                if (method != null) {
                    try {
                        str = (String) method.invoke(workSource, Integer.valueOf(i));
                    } catch (Exception e2) {
                        Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e2);
                    }
                }
                if (!Strings.isEmptyOrWhitespace(str)) {
                    Preconditions.checkNotNull(str);
                    arrayList.add(str);
                }
            }
        }
        return arrayList;
    }

    @KeepForSdk
    public static boolean hasWorkSourcePermission(@NonNull Context context) {
        return (context == null || context.getPackageManager() == null || Wrappers.packageManager(context).checkPermission("android.permission.UPDATE_DEVICE_STATS", context.getPackageName()) != 0) ? false : true;
    }

    @KeepForSdk
    public static boolean isEmpty(@NonNull WorkSource workSource) {
        Method method = zzi;
        if (method != null) {
            try {
                Object objInvoke = method.invoke(workSource, new Object[0]);
                Preconditions.checkNotNull(objInvoke);
                return ((Boolean) objInvoke).booleanValue();
            } catch (Exception e2) {
                Log.e("WorkSourceUtil", "Unable to check WorkSource emptiness", e2);
            }
        }
        return zza(workSource) == 0;
    }

    public static int zza(@NonNull WorkSource workSource) {
        Method method = zzd;
        if (method != null) {
            try {
                Object objInvoke = method.invoke(workSource, new Object[0]);
                Preconditions.checkNotNull(objInvoke);
                return ((Integer) objInvoke).intValue();
            } catch (Exception e2) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e2);
            }
        }
        return 0;
    }
}
