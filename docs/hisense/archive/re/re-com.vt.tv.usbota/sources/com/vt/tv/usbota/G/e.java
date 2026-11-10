package com.vt.tv.usbota.G;

import android.content.Context;
import android.os.Build;
import android.os.RecoverySystem;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes.dex */
public class e {
    public static List a(Context context, List list) {
        Log.i("SysUpgradeUtils", "GetOTAPackageFileListInUsb search began");
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            File file = new File((String) it.next());
            if (file.isDirectory()) {
                Log.i("SysUpgradeUtils", "GetOTAPackageFileListInUsb \t mountUsb:" + file.toString());
                for (File file2 : file.listFiles()) {
                    if (file2.isFile() && file2.getName().startsWith("USBOTA_") && file2.getName().endsWith(".zip")) {
                        Log.i("SysUpgradeUtils", "Find USB OTA package = " + file2.toString());
                        arrayList.add(file2.toString());
                    }
                }
            }
        }
        return arrayList;
    }

    public static List b(Context context) {
        if (context == null) {
            Log.e("SysUpgradeUtils", "getExternalStorageByAOSP failed, context is null");
            return null;
        }
        List<StorageVolume> storageVolumes = ((StorageManager) context.getSystemService("storage")).getStorageVolumes();
        if (storageVolumes == null || storageVolumes.isEmpty()) {
            Log.w("SysUpgradeUtils", "not found storage by storage manager");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (StorageVolume storageVolume : storageVolumes) {
            if (!storageVolume.isEmulated() && storageVolume.isRemovable()) {
                String path = storageVolume.getPath();
                if (path.contains("/storage")) {
                    Log.d("SysUpgradeUtils", "add volume path: " + path);
                    arrayList.add(path);
                }
            }
        }
        return arrayList;
    }

    public static String c() {
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Shanghai");
        Date date = new Date(Build.TIME);
        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.setTime(date);
        int i = calendar.get(1);
        StringBuffer stringBuffer = new StringBuffer("");
        stringBuffer.append((char) ((i - 2010) + 65));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMdd");
        simpleDateFormat.setTimeZone(timeZone);
        stringBuffer.append(simpleDateFormat.format(date));
        return stringBuffer.toString();
    }

    public static void d(File file, d dVar) {
        if (dVar == null) {
            Log.e("SysUpgradeUtils", "verify package failed, the listener is null");
            return;
        }
        if (!f.a("/system/etc/security/otacerts.zip")) {
            Log.e("SysUpgradeUtils", "verify package failed, the ota certs file path is not exists: /system/etc/security/otacerts.zip");
            dVar.a(2, new Object[0]);
        }
        Log.i("SysUpgradeUtils", "verifyPackage pkgFile =" + file.toString());
        try {
            RecoverySystem.verifyPackage(file, new b(dVar), new File("/system/etc/security/otacerts.zip"));
        } catch (IOException e) {
            Log.e("SysUpgradeUtils", "verify package IOException", e);
            dVar.a(3, new Object[0]);
        } catch (GeneralSecurityException e2) {
            Log.e("SysUpgradeUtils", "verify package GeneralSecurityException", e2);
            dVar.a(4, new Object[0]);
        }
    }
}
