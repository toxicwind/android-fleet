package b.f.b.b.a.b;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import dalvik.system.DexFile;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f677a = b.a.a.a.a.a(b.a.a.a.a.a("code_cache"), File.separator, "secondary-dexes");

    public static <T> List<T> a(Context context, Class<T> cls, String str) {
        ArrayList arrayList = new ArrayList();
        try {
            for (String str2 : a(context, str)) {
                Class<?> cls2 = Class.forName(str2);
                if (cls.isAssignableFrom(cls2) && !cls.equals(cls2) && !cls2.isInterface()) {
                    arrayList.add(Class.forName(str2).getConstructor(new Class[0]).newInstance(new Object[0]));
                }
            }
            if (arrayList.size() == 0) {
                Log.e("ClassUtils", "No files were found, check your configuration please!");
            }
        } catch (Exception e2) {
            e2.getStackTrace();
            Log.e("ClassUtils", "getObjectsWithInterface error, " + e2.getMessage());
        }
        return arrayList;
    }

    /* JADX WARN: Code duplicated, block: B:13:0x006d  */
    /* JADX WARN: Code duplicated, block: B:45:0x011a  */
    /* JADX WARN: Unreachable blocks removed: 1, instructions: 2 */
    public static List<String> a(Context context, String str) throws Throwable {
        boolean z;
        boolean z2;
        Throwable th;
        DexFile dexFileLoadDex;
        ArrayList arrayList = new ArrayList();
        ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
        File file = new File(applicationInfo.sourceDir);
        ArrayList<String> arrayList2 = new ArrayList();
        arrayList2.add(applicationInfo.sourceDir);
        String str2 = file.getName() + ".classes";
        DexFile dexFile = null;
        try {
            String property = System.getProperty("java.vm.version");
            if (property != null) {
                Matcher matcher = Pattern.compile("(\\d+)\\.(\\d+)(\\.\\d+)?").matcher(property);
                if (matcher.matches()) {
                    int i = Integer.parseInt(matcher.group(1));
                    int i2 = Integer.parseInt(matcher.group(2));
                    if (i > 2 || (i == 2 && i2 >= 1)) {
                        z = true;
                    } else {
                        z = false;
                    }
                } else {
                    z = false;
                }
            } else {
                z = false;
            }
        } catch (NumberFormatException | Exception unused) {
        }
        StringBuilder sbB = b.a.a.a.a.b("VM with name ", "'Android'");
        sbB.append(z ? " has multidex support" : " does not have multidex support");
        Log.i("ClassUtils", sbB.toString());
        if (!z) {
            int i3 = Build.VERSION.SDK_INT;
            int i4 = context.getSharedPreferences("multidex.version", 4).getInt("dex.number", 1);
            File file2 = new File(applicationInfo.dataDir, f677a);
            for (int i5 = 2; i5 <= i4; i5++) {
                File file3 = new File(file2, b.a.a.a.a.g(str2, i5, ".zip"));
                if (file3.isFile()) {
                    arrayList2.add(file3.getAbsolutePath());
                } else {
                    StringBuilder sbA = b.a.a.a.a.a("Missing extracted secondary dex file '");
                    sbA.append(file3.getPath());
                    sbA.append("'");
                    throw new IOException(sbA.toString());
                }
            }
        }
        String packageName = h.f689b.getPackageName();
        if (packageName == null || packageName.trim().length() == 0) {
            z2 = false;
        } else {
            try {
                ApplicationInfo applicationInfo2 = h.f689b.getPackageManager().getApplicationInfo(h.f689b.getPackageName(), 0);
                if (applicationInfo2 == null || (applicationInfo2.flags & 2) == 0) {
                    z2 = false;
                } else {
                    z2 = true;
                }
            } catch (PackageManager.NameNotFoundException e2) {
                Log.e(h.f688a, e2.toString());
            }
        }
        if (z2) {
            ArrayList arrayList3 = new ArrayList();
            int i6 = Build.VERSION.SDK_INT;
            String[] strArr = applicationInfo.splitSourceDirs;
            if (strArr != null) {
                arrayList3.addAll(Arrays.asList(strArr));
                Log.d("ClassUtils", "Found InstantRun support");
            } else {
                try {
                    File file4 = new File((String) Class.forName("com.android.tools.fd.runtime.Paths").getMethod("getDexFileDirectory", String.class).invoke(null, applicationInfo.packageName));
                    if (file4.exists() && file4.isDirectory()) {
                        for (File file5 : file4.listFiles()) {
                            if (file5 != null && file5.exists() && file5.isFile() && file5.getName().endsWith(".dex")) {
                                arrayList3.add(file5.getAbsolutePath());
                            }
                        }
                        Log.d("ClassUtils", "Found InstantRun support");
                    }
                } catch (Exception e3) {
                    StringBuilder sbA2 = b.a.a.a.a.a("InstantRun support error, ");
                    sbA2.append(e3.getMessage());
                    Log.e("ClassUtils", sbA2.toString());
                }
            }
            arrayList2.addAll(arrayList3);
        }
        for (String str3 : arrayList2) {
            try {
                dexFileLoadDex = str3.endsWith(".zip") ? DexFile.loadDex(str3, str3 + ".tmp", 0) : new DexFile(str3);
                try {
                    try {
                        Enumeration<String> enumerationEntries = dexFileLoadDex.entries();
                        while (enumerationEntries.hasMoreElements()) {
                            String strNextElement = enumerationEntries.nextElement();
                            if (strNextElement.contains(str)) {
                                arrayList.add(strNextElement);
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        Log.e("ClassUtils", "Scan map file in dex files made error.", th);
                        if (dexFileLoadDex != null) {
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    dexFile = dexFileLoadDex;
                    if (dexFile != null) {
                        try {
                            dexFile.close();
                        } catch (Throwable unused2) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
            }
            dexFileLoadDex.close();
        }
        StringBuilder sbA3 = b.a.a.a.a.a("Filter ");
        sbA3.append(arrayList.size());
        sbA3.append(" classes by packageName <");
        sbA3.append(str);
        sbA3.append(">");
        Log.d("ClassUtils", sbA3.toString());
        return arrayList;
    }
}
