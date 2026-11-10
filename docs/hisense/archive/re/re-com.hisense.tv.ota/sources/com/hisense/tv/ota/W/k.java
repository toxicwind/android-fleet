package com.hisense.tv.ota.W;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.RecoverySystem;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.ArraySet;
import android.util.Log;
import com.hisense.tv.ota.UploadEventService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class k {
    private static boolean A(Context context) {
        if (context != null) {
            return v(context) && !w(context);
        }
        Log.e("SysUpgradeUtils", "isUpgradeNG failed, context is null");
        return false;
    }

    private static boolean B(Context context) {
        if (context != null) {
            return w(context);
        }
        Log.e("SysUpgradeUtils", "isUpgradeOK failed, context is null");
        return false;
    }

    public static boolean C(Context context, String str) {
        Set<String> stringSet;
        Log.i("SysUpgradeUtils", "begin to check this verison is set Not Reminder by user");
        if (context == null || TextUtils.isEmpty(str) || (stringSet = context.getSharedPreferences("sys_upgrade_config", 0).getStringSet("ignore_version", null)) == null) {
            return false;
        }
        return stringSet.contains(str);
    }

    public static boolean D(Context context, String str) {
        SharedPreferences sharedPreferences;
        Set<String> stringSet;
        int i;
        Log.i("SysUpgradeUtils", "begin to check this verison is upgrade failed");
        if (context == null || TextUtils.isEmpty(str) || (stringSet = (sharedPreferences = context.getSharedPreferences("sys_upgrade_fail_version", 0)).getStringSet("upgrade_fail_version", null)) == null || !stringSet.contains(str) || (i = sharedPreferences.getInt("upgrade_fail_times", -1)) == -1 || i < 3) {
            return false;
        }
        Log.i("SysUpgradeUtils", "This version update fialed three times,Do not remind user");
        SystemProperties.set("persist.vt.ota.ACboot.number", "0");
        return true;
    }

    public static boolean E(File file, String str) throws Throwable {
        String strQ = q(file);
        Log.i("SysUpgradeUtils", "Downloaded OTA pacckage md5 =" + strQ);
        if (strQ != null && strQ.equalsIgnoreCase(str)) {
            return true;
        }
        Log.e("SysUpgradeUtils", "check out OTA pacckage md5 failed");
        return false;
    }

    public static boolean F() {
        int i = SystemProperties.getInt("persist.vt.ota.ACboot.number", 0);
        Log.i("SysUpgradeUtils", "max AC boot times =" + i);
        return i >= 10;
    }

    public static void G(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        Log.i("SysUpgradeUtils", "reset Not Remind Version ");
        SharedPreferences sharedPreferences = context.getSharedPreferences("sys_upgrade_config", 0);
        Set<String> stringSet = sharedPreferences.getStringSet("ignore_version", null);
        if (stringSet != null) {
            stringSet.remove(str);
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putStringSet("ignore_version", stringSet);
            editorEdit.commit();
        }
    }

    public static void H(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("last_package_path", 0).edit();
        editorEdit.putString("package", str);
        editorEdit.commit();
    }

    public static void I(Context context) {
        String strC = new l(context).c();
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("last_slot_number", 0).edit();
        editorEdit.putString("slot_num", strC);
        editorEdit.commit();
    }

    public static void J(Context context, String str, String str2, String str3) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("last_version", 0).edit();
        editorEdit.putString("data_version", str);
        editorEdit.putString("sys_version", str2);
        editorEdit.putString("target_version", str3);
        editorEdit.commit();
    }

    public static void K(Context context) {
        if (context == null) {
            Log.e("SysUpgradeUtils", "setOTAVersionDB failed, context is empty");
            return;
        }
        String str = context.getSharedPreferences("last_version", 0).getString("sys_version", null) + "_To_" + new l(context).j();
        Log.i("SysUpgradeUtils", "Source2Target = " + str);
        SharedPreferences sharedPreferences = context.getSharedPreferences("OTA_upgrade_db", 0);
        Set<String> stringSet = sharedPreferences.getStringSet("OTA_target_to_source", null);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        ArraySet arraySet = new ArraySet();
        if (stringSet != null) {
            arraySet.addAll(stringSet);
        }
        arraySet.add(str);
        editorEdit.putStringSet("OTA_target_to_source", arraySet);
        editorEdit.commit();
    }

    public static void L(Context context, String str) {
        if (context == null) {
            Log.e("SysUpgradeUtils", "set version not remind failed, context is empty");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            Log.e("SysUpgradeUtils", "set version not remind failed, version is empty");
            return;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("sys_upgrade_config", 0);
        Set<String> stringSet = sharedPreferences.getStringSet("ignore_version", null);
        ArraySet arraySet = new ArraySet();
        if (stringSet != null) {
            arraySet.addAll(stringSet);
        }
        arraySet.add(str);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putStringSet("ignore_version", arraySet);
        editorEdit.commit();
    }

    public static void M(Context context, String str) {
        int i;
        if (context == null) {
            Log.e("SysUpgradeUtils", "set version not remind failed, context is empty");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            Log.e("SysUpgradeUtils", "set version not remind failed, version is empty");
            return;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("sys_upgrade_fail_version", 0);
        Set<String> stringSet = sharedPreferences.getStringSet("upgrade_fail_version", null);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        int i2 = 1;
        if (stringSet == null || !stringSet.contains(str)) {
            ArraySet arraySet = new ArraySet();
            if (stringSet != null) {
                arraySet.addAll(stringSet);
            }
            arraySet.add(str);
            editorEdit.putStringSet("upgrade_fail_version", arraySet);
        } else {
            int i3 = sharedPreferences.getInt("upgrade_fail_times", 0);
            Log.i("SysUpgradeUtils", "failedTimes =" + i3);
            if (i3 != 0) {
                i = i3 + 1;
            } else {
                Log.e("SysUpgradeUtils", "failed times is wrong, set newFailedTime as 2");
                i = 2;
            }
            i2 = i;
        }
        editorEdit.putInt("upgrade_fail_times", i2);
        editorEdit.commit();
    }

    public static void N(Context context, String str) {
        if (context == null) {
            Log.e("SysUpgradeUtils", "upload event failed, context is null");
            return;
        }
        Intent intent = new Intent(context, (Class<?>) UploadEventService.class);
        intent.putExtra("event", str);
        context.startService(intent);
    }

    public static void O(String str, j jVar) {
        if (jVar == null) {
            Log.e("SysUpgradeUtils", "verify package failed, the listener is null");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            Log.e("SysUpgradeUtils", "verify package failed, the package path is empty");
            jVar.a(0, new Object[0]);
            return;
        }
        if (!m.b(str)) {
            Log.e("SysUpgradeUtils", "verify package failed, the package file is not exists: " + str);
            jVar.a(1, new Object[0]);
        }
        if (!m.b("/system/etc/security/otacerts.zip")) {
            Log.e("SysUpgradeUtils", "verify package failed, the ota certs file path is not exists: /system/etc/security/otacerts.zip");
            jVar.a(2, new Object[0]);
        }
        try {
            RecoverySystem.verifyPackage(new File(str), new f(jVar), new File("/system/etc/security/otacerts.zip"));
        } catch (IOException e) {
            Log.e("SysUpgradeUtils", "verify package IOException", e);
            jVar.a(3, new Object[0]);
        } catch (GeneralSecurityException e2) {
            Log.e("SysUpgradeUtils", "verify package GeneralSecurityException", e2);
            jVar.a(4, new Object[0]);
        }
    }

    public static boolean a() {
        String str;
        if (SystemProperties.get("sys.vt.screen.state", "none").equals("on")) {
            str = "screen on now";
        } else {
            if (!SystemProperties.get("sys.vt.screen.state", "none").equals("none")) {
                Log.i("SysUpgradeUtils", SystemProperties.get("sys.vt.screen.state", "none").equals("off") ? "screen off now" : "can not judge screen state");
                return false;
            }
            str = "screen on now, first AC on";
        }
        Log.i("SysUpgradeUtils", str);
        return true;
    }

    public static boolean b(Context context, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
        String str;
        if (z) {
            if (z2) {
                Log.i("SysUpgradeUtils", "FTE bring OTA and FteOTAMode in the server");
                return true;
            }
            str = "FTE bring OTA and but not FteOTAMode in the server";
        } else {
            if (z3) {
                return !z7;
            }
            if (z5) {
                return !z7;
            }
            if (!y(context)) {
                return (z4 && z6) ? false : true;
            }
            str = "In store mode, normal upgrade config,do not upgrade ";
        }
        Log.i("SysUpgradeUtils", str);
        return false;
    }

    public static void c(Context context) {
        if (context == null) {
            Log.e("SysUpgradeUtils", "checkUpgradeResult failed, context is null");
            return;
        }
        if (B(context)) {
            Log.i("SysUpgradeUtils", "is upgrade ok");
            N(context, "11");
            Log.i("SysUpgradeUtils", "OTA success first reboot, upload EVENT_CODE_UPGRADE_OK to server ");
            K(context);
            String strP = p(context);
            Log.i("SysUpgradeUtils", "Last Install packagepath = " + strP);
            if (!TextUtils.isEmpty(strP)) {
                if (new File(strP).delete()) {
                    Log.i("SysUpgradeUtils", "Delete last install zip package success ");
                } else {
                    Log.w("SysUpgradeUtils", "zip package has been deleted in InstallOK interface");
                }
            }
            SystemProperties.set("persist.vt.ota.ACboot.number", "0");
            f(context);
            e(context);
            d(context);
            N(context, "71");
            return;
        }
        if (!A(context)) {
            Log.i("SysUpgradeUtils", "is not upgrade ok & is not upgrade ng");
            if (SystemProperties.get("sys.vt.ota.merge.state", "none").equals("done") && SystemProperties.get("sys.vt.ota.upload.double.flag", "0").equals("0")) {
                N(context, "11");
                SystemProperties.set("sys.vt.ota.upload.double.flag", "1");
                return;
            }
            return;
        }
        Log.i("SysUpgradeUtils", "OTA failed first reboot, upload EVENT_CODE_UPGRADE_NG to server ");
        N(context, "12");
        M(context, context.getSharedPreferences("last_version", 0).getString("target_version", null));
        String strP2 = p(context);
        Log.i("SysUpgradeUtils", "Last Install packagepath = " + strP2);
        if (!TextUtils.isEmpty(strP2)) {
            if (new File(strP2).delete()) {
                Log.i("SysUpgradeUtils", "Delete last install zip package success ");
            } else {
                Log.w("SysUpgradeUtils", "zip package has been deleted in InstallOK interface");
            }
        }
        f(context);
        e(context);
        d(context);
    }

    private static void d(Context context) {
        if (context == null) {
            Log.e("SysUpgradeUtils", "clearLastInstallPackagePath failed, context is null");
            return;
        }
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("last_package_path", 0).edit();
        editorEdit.clear();
        editorEdit.commit();
        Log.i("SysUpgradeUtils", "clearLastInstallPackagePath success ");
    }

    private static void e(Context context) {
        if (context == null) {
            Log.e("SysUpgradeUtils", "clearLastSlotNumber failed, context is null");
            return;
        }
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("last_slot_number", 0).edit();
        editorEdit.clear();
        editorEdit.commit();
        Log.i("SysUpgradeUtils", "clearLastSlotNumber success ");
    }

    private static void f(Context context) {
        if (context == null) {
            Log.e("SysUpgradeUtils", "clearLastVersion failed, context is null");
            return;
        }
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("last_version", 0).edit();
        editorEdit.clear();
        editorEdit.commit();
        Log.i("SysUpgradeUtils", "clearLastVersion success ");
    }

    public static String g(String str) {
        String str2;
        if (str == null) {
            str2 = "eulaUrl is null";
        } else {
            if (str.endsWith("tar.gz")) {
                String strSubstring = str.substring(str.indexOf("eula_"), str.indexOf(".tar.gz"));
                Log.i("SysUpgradeUtils", "eulaZipFileName = " + strSubstring);
                return "/data/vendor/eulapath/" + strSubstring;
            }
            str2 = "eula package error";
        }
        Log.e("SysUpgradeUtils", str2);
        return null;
    }

    public static void h(Context context, boolean z, String str, String str2) {
        String str3;
        String strS;
        String strS2;
        File[] fileArrListFiles;
        String strT = t(true);
        String strT2 = t(false);
        if (!TextUtils.isEmpty(strT) && !TextUtils.isEmpty(strT2)) {
            if (z) {
                strS = s(str, str2, true);
                strS2 = s(str, str2, false);
                str3 = (TextUtils.isEmpty(strS) || TextUtils.isEmpty(strS2)) ? "delete all package failed, except is true, but can't get except package name" : "delete all package failed, regulra is empty";
            } else {
                strS = null;
                strS2 = null;
            }
            List listL = l(context);
            if (listL == null || listL.isEmpty()) {
                return;
            }
            Iterator it = listL.iterator();
            while (it.hasNext()) {
                File file = new File((String) it.next());
                if (file.exists() && file.isDirectory() && (fileArrListFiles = file.listFiles(new h(strT, strT2, z, strS2, strS))) != null && fileArrListFiles.length > 0) {
                    for (File file2 : fileArrListFiles) {
                        if (!file2.delete()) {
                            m.a(file2);
                            Log.w("SysUpgradeUtils", "delete package failed, path: " + file2.getPath());
                        }
                    }
                }
            }
            return;
        }
        Log.e("SysUpgradeUtils", str3);
    }

    private static void i(File file) {
        File[] fileArrListFiles;
        if (file != null && file.exists()) {
            if (file.isDirectory() && (fileArrListFiles = file.listFiles()) != null) {
                for (File file2 : fileArrListFiles) {
                    Log.i("SysUpgradeUtils", "subFile: " + file2.toString());
                    i(file2);
                }
            }
            if (file.toString().equalsIgnoreCase("/vdow/storemode")) {
                return;
            }
            Log.i("SysUpgradeUtils", "last : " + file.toString());
            if (file.toString().contains(".mp4")) {
                file.delete();
            }
        }
    }

    public static boolean j(String str, String str2) {
        String str3;
        String str4;
        if (TextUtils.isEmpty(str)) {
            str4 = "download eula failed, url is empty";
        } else {
            if (!TextUtils.isEmpty(str2)) {
                File file = new File(str2);
                File file2 = new File(str2 + ".tmp");
                Log.i("SysUpgradeUtils", "start download eula Zip file");
                boolean zB = com.hisense.tv.ota.V.b.a().b(str, file2.getAbsolutePath());
                Log.i("SysUpgradeUtils", "download eula result: " + zB);
                if (zB) {
                    if (!file2.renameTo(file)) {
                        Log.e("SysUpgradeUtils", "rename file failed");
                    }
                    File file3 = new File("/data/vendor/eulapath/eula.txt");
                    if (file3.exists()) {
                        str3 = "eual text txt is exist";
                    } else {
                        Log.i("SysUpgradeUtils", "create eual text txt");
                        try {
                            file3.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                            str3 = "create eual text txt error";
                            Log.i("SysUpgradeUtils", str3);
                        }
                        a.a("/data/vendor/eulapath/eula.txt");
                    }
                    Log.i("SysUpgradeUtils", str3);
                    a.a("/data/vendor/eulapath/eula.txt");
                }
                return zB;
            }
            str4 = "download eula failed, local path is empty";
        }
        Log.e("SysUpgradeUtils", str4);
        return false;
    }

    private static void k(String str) {
        if (TextUtils.isEmpty(str)) {
            Log.e("SysUpgradeUtils", "free space failed, path is empty");
        }
    }

    private static List l(Context context) {
        if (context == null) {
            Log.e("SysUpgradeUtils", "get all download to path failed, context is null");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add("/data/ota_package/");
        arrayList.add("/vdow/ota_package/");
        return arrayList;
    }

    private static List m(Context context) {
        String strT = t(true);
        String strT2 = t(false);
        if (TextUtils.isEmpty(strT) || TextUtils.isEmpty(strT2)) {
            Log.e("SysUpgradeUtils", "get all package failed, regulra is empty");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        List<String> listL = l(context);
        Log.e("SysUpgradeUtils", "getAllUpgradePackage getAllDownloadToPath: " + listL);
        if (listL != null && !listL.isEmpty()) {
            for (String str : listL) {
                File file = new File(str);
                if (file.exists() && file.isDirectory()) {
                    if (file.canRead() && file.canExecute()) {
                        File[] fileArrListFiles = file.listFiles(new g(strT, strT2));
                        if (fileArrListFiles != null && fileArrListFiles.length > 0) {
                            for (File file2 : fileArrListFiles) {
                                arrayList.add(file2);
                            }
                        }
                    } else {
                        Log.e("SysUpgradeUtils", "permission denied to access the path: " + str);
                    }
                }
            }
        }
        return arrayList;
    }

    public static File n(Context context, String str, String str2, long j) {
        String strR = r(context, str, str2, j);
        Log.i("SysUpgradeUtils", "getPkgDownloadToPath path =" + strR);
        if (!TextUtils.isEmpty(strR)) {
            File file = new File(strR);
            if (file.exists() && file.isFile()) {
                if (!strR.endsWith("tmp")) {
                    return new File(strR);
                }
                if (file.length() == j) {
                    try {
                        String str3 = strR.substring(0, strR.length() - 3) + "zip";
                        File file2 = new File(str3);
                        if (file.renameTo(file2)) {
                            return file2;
                        }
                        Log.e("SysUpgradeUtils", "renameTo failed, path: " + str3);
                        return null;
                    } catch (Exception e) {
                        Log.e("SysUpgradeUtils", "string exception", e);
                    }
                }
            }
        }
        return null;
    }

    private static long o(String str) {
        if (TextUtils.isEmpty(str)) {
            Log.e("SysUpgradeUtils", "get free space bytes failed, path is empty");
        }
        return 0L;
    }

    private static String p(Context context) {
        return context.getSharedPreferences("last_package_path", 0).getString("package", null);
    }

    /* JADX WARN: Code duplicated, block: B:38:0x00a1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Not initialized variable reg: 3, insn: 0x009e: MOVE (r1 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY]), block:B:29:0x009e */
    public static String q(File file) throws Throwable {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        FileInputStream fileInputStream3 = null;
        try {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                fileInputStream = new FileInputStream(file);
                try {
                    byte[] bArr = new byte[8192];
                    while (true) {
                        int i = fileInputStream.read(bArr);
                        if (i == -1) {
                            break;
                        }
                        messageDigest.update(bArr, 0, i);
                    }
                    String string = new BigInteger(1, messageDigest.digest()).toString(16);
                    Log.i("SysUpgradeUtils", "first dmd5sumString = " + string);
                    String strReplace = String.format("%32s", string).replace(' ', '0');
                    Log.i("SysUpgradeUtils", "format md5sumString = " + strReplace);
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return strReplace;
                } catch (Exception e2) {
                    e = e2;
                    e.printStackTrace();
                    Log.e("SysUpgradeUtils", "check out OTA package md5 error " + e.getMessage());
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                fileInputStream3 = fileInputStream2;
                if (fileInputStream3 != null) {
                    try {
                        fileInputStream3.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Exception e5) {
            e = e5;
            fileInputStream = null;
        } catch (Throwable th2) {
            th = th2;
            if (fileInputStream3 != null) {
                fileInputStream3.close();
            }
            throw th;
        }
    }

    public static String r(Context context, String str, String str2, long j) {
        StringBuilder sb;
        File file;
        File file2;
        String str3;
        if (TextUtils.isEmpty(str)) {
            str3 = "get pacakge download to path failed, old version is empty";
        } else if (TextUtils.isEmpty(str2)) {
            str3 = "get pacakge download to path failed, new version is empty";
        } else {
            if (j > 0) {
                String strS = s(str, str2, false);
                String strS2 = s(str, str2, true);
                if (TextUtils.isEmpty(strS) || TextUtils.isEmpty(strS2)) {
                    Log.e("SysUpgradeUtils", "get pacakge download to path failed, can't get package file name");
                    return null;
                }
                List<File> listM = m(context);
                Log.i("SysUpgradeUtils", "getAllUpgradePackage pkgList : " + listM);
                ArrayList arrayList = new ArrayList();
                if (listM != null && !listM.isEmpty()) {
                    for (File file3 : listM) {
                        String name = file3.getName();
                        long length = file3.length();
                        if (!name.equalsIgnoreCase(strS2) ? name.equalsIgnoreCase(strS) && length == j : length <= j) {
                            if (!file3.delete()) {
                                m.a(file3);
                                Log.e("SysUpgradeUtils", "delete invalid package failed, path: " + file3.getPath());
                            }
                            arrayList.add(file3);
                        }
                    }
                    listM.removeAll(arrayList);
                    arrayList.clear();
                    if (!listM.isEmpty()) {
                        HashMap map = new HashMap();
                        for (File file4 : listM) {
                            String parent = file4.getParent();
                            List arrayList2 = (List) map.get(parent);
                            if (arrayList2 == null) {
                                arrayList2 = new ArrayList();
                                map.put(parent, arrayList2);
                            }
                            arrayList2.add(file4);
                        }
                        Iterator it = map.keySet().iterator();
                        while (it.hasNext()) {
                            List list = (List) map.get((String) it.next());
                            int size = list.size();
                            if (size <= 0 || size > 2) {
                                Log.w("SysUpgradeUtils", "invalid path list size: " + size + ", check why ?");
                            }
                            if (size == 2) {
                                Iterator it2 = list.iterator();
                                do {
                                    if (!it2.hasNext()) {
                                        file2 = null;
                                        break;
                                    }
                                    file2 = (File) it2.next();
                                } while (!file2.getName().equalsIgnoreCase(strS2));
                                if (file2 == null) {
                                    Log.w("SysUpgradeUtils", "check why the remove file is null ?");
                                } else {
                                    if (!file2.delete()) {
                                        m.a(file2);
                                        Log.e("SysUpgradeUtils", "delete tmp file failed, path: " + file2.getPath());
                                    }
                                    list.remove(file2);
                                }
                            }
                        }
                        listM.clear();
                        Iterator it3 = map.keySet().iterator();
                        while (it3.hasNext()) {
                            List list2 = (List) map.get((String) it3.next());
                            if (list2.size() != 1) {
                                Log.w("SysUpgradeUtils", "check why list size is not 1 ?");
                            } else {
                                listM.add((File) list2.get(0));
                            }
                        }
                    }
                    if (!listM.isEmpty()) {
                        Iterator it4 = listM.iterator();
                        while (true) {
                            if (!it4.hasNext()) {
                                file = null;
                                break;
                            }
                            file = (File) it4.next();
                            if (file.getName().equalsIgnoreCase(strS) && file.getParentFile().getUsableSpace() > 209715200) {
                                break;
                            }
                        }
                        if (file == null) {
                            for (File file5 : listM) {
                                if (file5.getName().equalsIgnoreCase(strS)) {
                                    File parentFile = file5.getParentFile();
                                    if (parentFile.getUsableSpace() + o(parentFile.getPath()) >= 209715200) {
                                        k(parentFile.getPath());
                                        if (parentFile.getUsableSpace() >= 209715200) {
                                            file = file5;
                                            break;
                                        }
                                    } else {
                                        continue;
                                    }
                                }
                            }
                        }
                        if (file != null) {
                            for (File file6 : listM) {
                                if (file6 != file && !file6.delete()) {
                                    m.a(file6);
                                    Log.e("SysUpgradeUtils", "delete file failed, path: " + file6.getPath());
                                }
                            }
                            return file.getAbsolutePath();
                        }
                        for (File file7 : listM) {
                            if (file7.getName().equalsIgnoreCase(strS2) && file7.getParentFile().getUsableSpace() - (j - file7.length()) > 209715200) {
                                file = file7;
                                break;
                            }
                        }
                        if (file == null) {
                            for (File file8 : listM) {
                                if (file8.getName().equalsIgnoreCase(strS2)) {
                                    File parentFile2 = file8.getParentFile();
                                    if ((parentFile2.getUsableSpace() + o(parentFile2.getPath())) - (j - file8.length()) >= 209715200) {
                                        k(parentFile2.getPath());
                                        if (parentFile2.getUsableSpace() - (j - file8.length()) >= 209715200) {
                                            file = file8;
                                            break;
                                        }
                                    } else {
                                        continue;
                                    }
                                }
                            }
                        }
                        if (file != null) {
                            for (File file9 : listM) {
                                if (file9 != file && !file9.delete()) {
                                    m.a(file9);
                                    Log.e("SysUpgradeUtils", "delete file failed, path: " + file9.getPath());
                                }
                            }
                            return file.getAbsolutePath();
                        }
                        for (File file10 : listM) {
                            if (file10.exists() && !file10.delete()) {
                                m.a(file10);
                                Log.e("SysUpgradeUtils", "delete file failed, path: " + file10.getPath());
                            }
                        }
                    }
                }
                Log.i("SysUpgradeUtils", "no any tmp package or completed package on the device");
                Log.i("SysUpgradeUtils", "get a new download path");
                for (String str4 : l(context)) {
                    if (str4.equalsIgnoreCase("/data/ota_package/")) {
                        if (new File(str4).getUsableSpace() > j + 209715200) {
                            Log.i("SysUpgradeUtils", "getPkgDownloadToPath path = " + str4);
                            Log.i("SysUpgradeUtils", "getPkgDownloadToPath pkgTmpFileName = " + strS2);
                            sb = new StringBuilder();
                        } else {
                            str4 = "/vdow/ota_package/";
                            File file11 = new File("/vdow/ota_package/");
                            long usableSpace = file11.getUsableSpace();
                            Log.i("SysUpgradeUtils", "getPkgDownloadToPath vdowFilefreeSpace = " + usableSpace);
                            if (usableSpace > j) {
                                Log.i("SysUpgradeUtils", "getPkgDownloadToPath path = /vdow/ota_package/");
                                Log.i("SysUpgradeUtils", "getPkgDownloadToPath pkgTmpFileName = " + strS2);
                                sb = new StringBuilder();
                            } else if (y(context)) {
                                Log.i("SysUpgradeUtils", "in storemode now, can not use /vdow/storemode dir");
                            } else {
                                i(new File("/vdow/storemode"));
                                SystemClock.sleep(200L);
                                long usableSpace2 = file11.getUsableSpace();
                                Log.i("SysUpgradeUtils", "getPkgDownloadToPath vdowFilefreeSpaceAgain = " + usableSpace2);
                                if (usableSpace2 > j) {
                                    Log.i("SysUpgradeUtils", "getPkgDownloadToPath path = /vdow/ota_package/");
                                    Log.i("SysUpgradeUtils", "getPkgDownloadToPath pkgTmpFileName = " + strS2);
                                    sb = new StringBuilder();
                                }
                            }
                        }
                        sb.append(str4);
                        sb.append(strS2);
                        return sb.toString();
                    }
                }
                return null;
            }
            str3 = "get pacakge download to path failed, invalid package size: " + j;
        }
        Log.e("SysUpgradeUtils", str3);
        return null;
    }

    private static String s(String str, String str2, boolean z) {
        String str3;
        if (TextUtils.isEmpty(str)) {
            str3 = "get package name failed, old version is empty";
        } else {
            if (!TextUtils.isEmpty(str2)) {
                return String.format("%s.%s%s%s.%s", "hisense.update", str, "@", str2, z ? "tmp" : "zip");
            }
            str3 = "get package name failed, new version is empty";
        }
        Log.e("SysUpgradeUtils", str3);
        return null;
    }

    private static String t(boolean z) {
        return z ? String.format("%s.\\S+%s\\S+.%s", "hisense.update", "@", "tmp") : String.format("%s.\\S+%s\\S+.%s", "hisense.update", "@", "zip");
    }

    public static String u(Context context) {
        return Settings.Global.getString(context.getContentResolver(), "zip_code");
    }

    private static boolean v(Context context) {
        if (context == null) {
            Log.e("SysUpgradeUtils", "hasLastVersion failed, context is null");
            return false;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("last_version", 0);
        boolean z = (TextUtils.isEmpty(sharedPreferences.getString("data_version", null)) && TextUtils.isEmpty(sharedPreferences.getString("sys_version", null))) ? false : true;
        Log.i("SysUpgradeUtils", "has last version: " + z);
        return z;
    }

    private static boolean w(Context context) {
        if (context == null) {
            Log.e("SysUpgradeUtils", "hasSlotChanged failed, context is null");
            return false;
        }
        String strC = new l(context).c();
        Log.i("SysUpgradeUtils", "hasSlotChanged current slot = " + strC);
        String string = context.getSharedPreferences("last_slot_number", 0).getString("slot_num", null);
        Log.i("SysUpgradeUtils", "hasSlotChanged last slot = " + string);
        return (TextUtils.isEmpty(string) || TextUtils.isEmpty(strC) || string.equalsIgnoreCase(strC)) ? false : true;
    }

    public static boolean x(Context context) {
        int i = Settings.Secure.getInt(context.getContentResolver(), "tv_user_setup_complete", 0);
        Log.i("SysUpgradeUtils", "tv_user_setup_complete_flag = " + i);
        int i2 = Settings.Secure.getInt(context.getContentResolver(), "user_setup_complete", 0);
        Log.i("SysUpgradeUtils", "user_setup_complete_flag = " + i2);
        return (i2 & i) == 0;
    }

    public static boolean y(Context context) {
        int i = Settings.Global.getInt(context.getContentResolver(), "use_mode", 0);
        Log.i("SysUpgradeUtils", "isInStoreModeWithUsemodeFlag flag =" + i);
        return 1 == i;
    }

    public static boolean z(Context context) {
        NetworkInfo[] allNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getAllNetworkInfo();
        if (allNetworkInfo != null) {
            for (NetworkInfo networkInfo : allNetworkInfo) {
                if (NetworkInfo.State.CONNECTED == networkInfo.getState()) {
                    return true;
                }
            }
        }
        Log.i("SysUpgradeUtils", "Network is not ready now");
        return false;
    }
}
