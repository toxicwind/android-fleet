package com.ju.lib.utils.file;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Parcelable;
import com.ju.lib.utils.base.JuPlatException;
import com.ju.lib.utils.base.persistence.ParcelablePersistence;
import com.ju.lib.utils.jsonxml.JsonUtil;
import com.ju.lib.utils.log.LogUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class Assets {
    public static final String ASSETS_CACHE_DIR = "/assets";
    public static final String CACHE_VERSION = "version";
    public static final String CACHE_VERSION_FILE_NAME = "assets_version";
    public static final int DEFAULT_VERSION = -1;
    public static final String TAG = "Assets";
    public static boolean checkVersion = false;
    public static String sCacheDirPath;

    /* JADX INFO: renamed from: com.ju.lib.utils.file.Assets$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$ju$lib$utils$file$Assets$ParentClassType = new int[ParentClassType.values().length];

        static {
            try {
                $SwitchMap$com$ju$lib$utils$file$Assets$ParentClassType[ParentClassType.Parcelable.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$ju$lib$utils$file$Assets$ParentClassType[ParentClassType.Serializable.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$ju$lib$utils$file$Assets$ParentClassType[ParentClassType.Other.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    enum ParentClassType {
        Serializable,
        Parcelable,
        Other
    }

    public static boolean cacheFileIsValid(String str) {
        File file = new File(str);
        return file.exists() && file.isFile() && file.length() > 0;
    }

    public static boolean checkCacheValid(Context context, String str) {
        boolean z;
        if (checkVersion) {
            z = true;
        } else {
            long jCurrentTimeMillis = System.currentTimeMillis();
            int packageVersionCode = getPackageVersionCode(context);
            int cacheVersion = readCacheVersion(context);
            LogUtil.d(TAG, "checkCacheValid(), packageVersion ", Integer.valueOf(packageVersionCode), ", cacheVersion : ", Integer.valueOf(cacheVersion));
            if (packageVersionCode != cacheVersion) {
                File file = new File(getCacheDir(context));
                if (file.exists()) {
                    LogUtil.d(TAG, "checkCacheValid(), delete ", file.getPath());
                    FileProcess.deleteFileOrDirectory(file);
                }
                z = false;
            } else {
                z = true;
            }
            checkVersion = true;
            LogUtil.d(TAG, "checkCacheValid(Context context, String cachePath), duration : ", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
        }
        LogUtil.d(TAG, "checkCacheValid(), ret : ", Boolean.valueOf(z));
        return z && cacheFileIsValid(str);
    }

    /* JADX WARN: Code duplicated, block: B:44:0x007d A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:45:0x007f A[Catch: IOException -> 0x007b, TRY_LEAVE, TryCatch #7 {IOException -> 0x007b, blocks: (B:41:0x0077, B:45:0x007f), top: B:52:0x0077 }] */
    /* JADX WARN: Code duplicated, block: B:52:0x0077 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public static void copyFileToDataDir(Context context, String str, String str2) throws Throwable {
        FileOutputStream fileOutputStream;
        InputStream inputStream = null;
        try {
            try {
                File file = new File(str2);
                if (file.exists()) {
                    file.deleteOnExit();
                } else {
                    new File(file.getParent()).mkdirs();
                }
                file.createNewFile();
                InputStream inputStreamOpen = context.getAssets().open(str);
                try {
                    fileOutputStream = new FileOutputStream(file);
                    try {
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int i = inputStreamOpen.read(bArr);
                            if (i == -1) {
                                fileOutputStream.flush();
                                inputStreamOpen.close();
                                fileOutputStream.close();
                                return;
                            }
                            fileOutputStream.write(bArr, 0, i);
                        }
                    } catch (Exception e2) {
                        inputStream = inputStreamOpen;
                        e = e2;
                        try {
                            e.printStackTrace();
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                        } catch (Throwable th) {
                            th = th;
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                    if (fileOutputStream != null) {
                                        fileOutputStream.close();
                                    }
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                    throw th;
                                }
                            } else if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            throw th;
                        }
                    } catch (Throwable th2) {
                        inputStream = inputStreamOpen;
                        th = th2;
                        if (inputStream != null) {
                            inputStream.close();
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                        } else if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        throw th;
                    }
                } catch (Exception e4) {
                    inputStream = inputStreamOpen;
                    e = e4;
                    fileOutputStream = null;
                } catch (Throwable th3) {
                    inputStream = inputStreamOpen;
                    th = th3;
                    fileOutputStream = null;
                }
            } catch (IOException e5) {
                e5.printStackTrace();
            }
        } catch (Exception e6) {
            e = e6;
            fileOutputStream = null;
        } catch (Throwable th4) {
            th = th4;
            fileOutputStream = null;
        }
    }

    public static List<String> getAssetsMatchingItems(Context context, String str, String str2) {
        if (context == null) {
            return null;
        }
        try {
            return getAssetsMatchingItems(context.getAssets(), str, str2);
        } catch (JuPlatException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static synchronized String getCacheDir(Context context) {
        if (sCacheDirPath == null) {
            sCacheDirPath = String.valueOf(context.getCacheDir());
        }
        return sCacheDirPath + ASSETS_CACHE_DIR;
    }

    public static synchronized String getCacheFilePath(Context context, String str) {
        if (sCacheDirPath == null) {
            sCacheDirPath = String.valueOf(context.getCacheDir());
        }
        return sCacheDirPath + ASSETS_CACHE_DIR + File.separator + str;
    }

    public static synchronized String getFromAssets(Context context, String str) {
        if (context == null) {
            return null;
        }
        try {
            return getFromAssets(context.getAssets(), str);
        } catch (JuPlatException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static int getPackageVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            LogUtil.e(TAG, "getPackageVersionCode(), get package version fail!");
            return 0;
        }
    }

    public static final ParentClassType getParentClassType(Class cls) {
        ParentClassType parentClassType = ParentClassType.Other;
        if (Parcelable.class.isAssignableFrom(cls)) {
            return ParentClassType.Parcelable;
        }
        return Serializable.class.isAssignableFrom(cls) ? ParentClassType.Serializable : parentClassType;
    }

    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(CACHE_VERSION_FILE_NAME, 0);
    }

    public static int readCacheVersion(Context context) {
        return context.getSharedPreferences(CACHE_VERSION_FILE_NAME, 0).getInt("version", -1);
    }

    public static synchronized <T> T readObject(Context context, String str, Class<T> cls) {
        T t;
        try {
            if (context == null || str == null || cls == null) {
                throw new JuPlatException(0, "readObject() param is null");
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            ParentClassType parentClassType = getParentClassType(cls);
            String cacheFilePath = getCacheFilePath(context, str);
            if (checkCacheValid(context, cacheFilePath)) {
                LogUtil.d(TAG, "readObject(Context context, String fileName, Class<T> classOfT) cache is valid fileName :", str);
                int iOrdinal = parentClassType.ordinal();
                if (iOrdinal == 0) {
                    t = (T) JsonUtil.parse(FileProcess.readFromFile(cacheFilePath), cls);
                } else {
                    if (iOrdinal != 1) {
                        throw new JuPlatException(0, "readObject() classOfT is invalid");
                    }
                    t = (T) new ParcelablePersistence().read(cacheFilePath, cls.getClassLoader());
                }
            } else {
                LogUtil.d(TAG, "readObject(Context context, String fileName, Class<T> classOfT) cache invalid fileName :", str);
                String fromAssets = getFromAssets(context, str);
                if (fromAssets == null) {
                    return null;
                }
                Object obj = JsonUtil.parse(fromAssets, cls);
                int iOrdinal2 = parentClassType.ordinal();
                if (iOrdinal2 == 0) {
                    FileProcess.saveObjectToFile(fromAssets, cacheFilePath);
                    updateCacheVersion(context);
                } else {
                    if (iOrdinal2 != 1) {
                        throw new JuPlatException(0, "readObject() classOfT is invalid");
                    }
                    if (new ParcelablePersistence().write(cacheFilePath, obj)) {
                        updateCacheVersion(context);
                    }
                }
                t = (T) obj;
            }
            LogUtil.d(TAG, "readObject(Context context, String fileName, Class<T> classOfT)  fileName : ", str, ", type : ", parentClassType, ", duration : ", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
            return t;
        } catch (Throwable th) {
            throw th;
        }
    }

    public static void updateCacheVersion(Context context) {
        writeCacheVersion(context, getPackageVersionCode(context));
    }

    public static boolean writeCacheVersion(Context context, int i) {
        return context.getSharedPreferences(CACHE_VERSION_FILE_NAME, 0).edit().putInt("version", i).commit();
    }

    public static List<String> getAssetsMatchingItems(AssetManager assetManager, String str, String str2) throws JuPlatException {
        ArrayList arrayList;
        LogUtil.i(TAG, "getAssetsMatchingItems(), path : ", str, ", matchingWord : ", str2);
        if (assetManager != null && str != null) {
            try {
                String[] list = assetManager.list(str);
                if (list != null && list.length != 0) {
                    if (str2 != null && !"".equals(str2)) {
                        arrayList = new ArrayList();
                        for (int i = 0; i < list.length; i++) {
                            try {
                                if (list[i].contains(str2)) {
                                    arrayList.add(list[i]);
                                }
                            } catch (IOException e2) {
                                e = e2;
                                e.printStackTrace();
                            }
                        }
                        return arrayList;
                    }
                    LogUtil.d(TAG, "getAssetsMatchingItems(), length : ", Integer.valueOf(list.length));
                    return Arrays.asList(list);
                }
                LogUtil.i(TAG, "getAssetsMatchingItems(), ret is null");
                return null;
            } catch (IOException e3) {
                e = e3;
                arrayList = null;
            }
        } else {
            throw new JuPlatException(0, "param is invalid");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0, types: [android.content.res.AssetManager] */
    /* JADX WARN: Type inference failed for: r11v10 */
    /* JADX WARN: Type inference failed for: r11v3 */
    /* JADX WARN: Type inference failed for: r11v5, types: [java.io.BufferedReader] */
    public static synchronized String getFromAssets(AssetManager assetManager, String str) {
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        if (assetManager != 0 && str != null) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            try {
                try {
                    inputStreamReader = new InputStreamReader(assetManager.open(str), "UTF-8");
                    try {
                        bufferedReader = new BufferedReader(inputStreamReader);
                        try {
                            StringBuffer stringBuffer = new StringBuffer();
                            while (true) {
                                String line = bufferedReader.readLine();
                                if (line == null) {
                                    break;
                                }
                                stringBuffer.append(line);
                                throw th;
                            }
                            String string = stringBuffer.toString();
                            LogUtil.d(TAG, "getFromAssets(AssetManager assetManager, String fileName), fileName : ", str, ", duration : ", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
                            try {
                                bufferedReader.close();
                                inputStreamReader.close();
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                            return string;
                        } catch (Exception e3) {
                            e = e3;
                            e.printStackTrace();
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (Exception e4) {
                                    e4.printStackTrace();
                                    return null;
                                }
                            }
                            if (inputStreamReader != null) {
                                inputStreamReader.close();
                            }
                            return null;
                        }
                    } catch (Exception e5) {
                        e = e5;
                        bufferedReader = null;
                    } catch (Throwable th) {
                        th = th;
                        assetManager = 0;
                        if (assetManager != 0) {
                            try {
                                assetManager.close();
                            } catch (Exception e6) {
                                e6.printStackTrace();
                                throw th;
                            }
                        }
                        if (inputStreamReader != null) {
                            inputStreamReader.close();
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e7) {
                e = e7;
                bufferedReader = null;
                inputStreamReader = null;
            } catch (Throwable th3) {
                th = th3;
                assetManager = 0;
                inputStreamReader = null;
            }
        } else {
            throw new JuPlatException(0, "param is invalid");
        }
    }
}
