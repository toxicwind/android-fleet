package com.hisense.uifac;

import android.content.Context;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.text.TextUtils;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/* JADX INFO: loaded from: classes.dex */
public class ExternalStorageHelper {
    private static boolean DEBUG = true;
    private static boolean VERSION_NEW = true;

    public static List<String> getExternalStoragePath(Context context) throws Throwable {
        if (VERSION_NEW) {
            return getExternalStorageByAOSP(context);
        }
        List<String> externalStoragePathByState = getExternalStoragePathByState(context);
        if (externalStoragePathByState != null && !externalStoragePathByState.isEmpty()) {
            List<String> mountsContent = getMountsContent();
            if (mountsContent != null && !mountsContent.isEmpty()) {
                ArrayList arrayList = new ArrayList();
                for (String str : externalStoragePathByState) {
                    if (mountsContent.contains(str)) {
                        if (isDebug()) {
                            Log.d("Fac_ExternalStorageHelper", "true external storage path: " + str);
                        }
                        arrayList.add(str);
                    }
                }
                return arrayList;
            }
            Log.w("Fac_ExternalStorageHelper", "mount info has no external storage path");
            return null;
        }
        Log.w("Fac_ExternalStorageHelper", "volume paths are null, has no external storage");
        return null;
    }

    private static List<String> getExternalStorageByAOSP(Context context) {
        if (context == null) {
            Log.e("Fac_ExternalStorageHelper", "getExternalStorageByAOSP failed, context is null");
            return null;
        }
        List<StorageVolume> storageVolumes = ((StorageManager) context.getSystemService("storage")).getStorageVolumes();
        if (storageVolumes == null || storageVolumes.isEmpty()) {
            Log.w("Fac_ExternalStorageHelper", "not found storage by storage manager");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (StorageVolume storageVolume : storageVolumes) {
            if (!storageVolume.isEmulated() && storageVolume.isRemovable()) {
                String path = storageVolume.getPath();
                if (isDebug()) {
                    Log.d("Fac_ExternalStorageHelper", "add volume path: " + path);
                }
                arrayList.add(path);
            }
        }
        return arrayList;
    }

    private static List<String> getExternalStoragePathByState(Context context) {
        StorageVolume[] volumeList = ((StorageManager) context.getSystemService("storage")).getVolumeList();
        if (volumeList != null && volumeList.length > 0) {
            ArrayList arrayList = new ArrayList();
            for (StorageVolume storageVolume : volumeList) {
                String state = storageVolume.getState();
                String path = storageVolume.getPath();
                if (isDebug()) {
                    Log.d("Fac_ExternalStorageHelper", "path: " + path + "  state: " + state);
                }
                if (storageVolume.isEmulated() && isDebug()) {
                    Log.d("Fac_ExternalStorageHelper", "emulated volume");
                } else if ("null".equalsIgnoreCase(state) || "mounted".equalsIgnoreCase(state)) {
                    if (isDebug()) {
                        Log.d("Fac_ExternalStorageHelper", "add path: " + path);
                    }
                    arrayList.add(path);
                }
            }
            return arrayList;
        }
        if (!isDebug()) {
            return null;
        }
        Log.e("Fac_ExternalStorageHelper", "volume list is null");
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:110:0x00ad A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:111:0x00bb A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:117:0x0093 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:118:0x0093 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:124:0x0119 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:126:0x0107 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:128:0x0135 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:130:0x011d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:38:0x007a  */
    /* JADX WARN: Code duplicated, block: B:41:0x0085  */
    /* JADX WARN: Code duplicated, block: B:44:0x009c  */
    /* JADX WARN: Code duplicated, block: B:64:0x00fa  */
    /* JADX WARN: Code duplicated, block: B:67:0x010d  */
    /* JADX WARN: Code duplicated, block: B:72:0x0123  */
    /* JADX WARN: Code duplicated, block: B:77:0x013f  */
    /* JADX WARN: Code duplicated, block: B:80:0x0149 A[LOOP:4: B:78:0x0143->B:80:0x0149, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:83:0x016c  */
    /* JADX WARN: Code duplicated, block: B:88:0x017c A[LOOP:5: B:86:0x0176->B:88:0x017c, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:90:0x0187  */
    /* JADX WARN: Code duplicated, block: B:92:0x018d  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v3, types: [boolean] */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v12, types: [java.io.BufferedReader] */
    private static List<String> getMountsContent() throws Throwable {
        HashMap map;
        ArrayList arrayList;
        Iterator it;
        ArrayList<String> arrayList2;
        Set<String> setKeySet;
        Set setKeySet2;
        Iterator it2;
        StringTokenizer stringTokenizer;
        String strNextToken;
        BufferedReader bufferedReader;
        File file = new File("/proc/mounts");
        ArrayList arrayList3 = new ArrayList();
        ?? r3 = 0;
        if (file.exists() && file.isFile()) {
            ?? CanRead = file.canRead();
            try {
                if (CanRead != 0) {
                    try {
                        bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                        while (true) {
                            try {
                                String line = bufferedReader.readLine();
                                if (line == null) {
                                    break;
                                }
                                arrayList3.add(line);
                                if (isDebug()) {
                                    Log.d("Fac_ExternalStorageHelper", "mount info >>>  " + line);
                                }
                            } catch (FileNotFoundException e) {
                                e = e;
                                Log.e("Fac_ExternalStorageHelper", "read mounts file FileNotFoundException", e);
                                if (bufferedReader != null) {
                                    try {
                                        bufferedReader.close();
                                    } catch (IOException unused) {
                                    }
                                }
                                if (!arrayList3.isEmpty()) {
                                    map = new HashMap();
                                    arrayList = new ArrayList();
                                    it = arrayList3.iterator();
                                    while (it.hasNext()) {
                                        stringTokenizer = new StringTokenizer((String) it.next());
                                        if (stringTokenizer.countTokens() >= 2) {
                                            strNextToken = stringTokenizer.nextToken();
                                            String strNextToken2 = stringTokenizer.nextToken();
                                            if (TextUtils.isEmpty(strNextToken)) {
                                            }
                                        }
                                    }
                                    if (!map.isEmpty()) {
                                        arrayList2 = new ArrayList();
                                        setKeySet = map.keySet();
                                        for (String str : setKeySet) {
                                            if (map.containsValue(str)) {
                                                for (String str2 : setKeySet) {
                                                    if (str.equalsIgnoreCase((String) map.get(str2))) {
                                                        arrayList2.add(str2);
                                                    }
                                                }
                                            }
                                        }
                                        if (!arrayList2.isEmpty()) {
                                            for (String str3 : arrayList2) {
                                                Log.d("Fac_ExternalStorageHelper", String.format("duplicate, so remove, device: %s  path: %s", map.get(str3), str3));
                                                map.remove(str3);
                                            }
                                        }
                                        setKeySet2 = map.keySet();
                                        if (setKeySet2 != null) {
                                            it2 = setKeySet2.iterator();
                                            while (it2.hasNext()) {
                                                arrayList.add((String) it2.next());
                                            }
                                        }
                                    }
                                    return arrayList;
                                }
                                if (isDebug()) {
                                    Log.d("Fac_ExternalStorageHelper", "the mounts list is empty");
                                }
                                return null;
                            } catch (IOException e2) {
                                e = e2;
                                Log.e("Fac_ExternalStorageHelper", "read mounts file IOException", e);
                                if (bufferedReader != null) {
                                    bufferedReader.close();
                                }
                                if (!arrayList3.isEmpty()) {
                                    map = new HashMap();
                                    arrayList = new ArrayList();
                                    it = arrayList3.iterator();
                                    while (it.hasNext()) {
                                        stringTokenizer = new StringTokenizer((String) it.next());
                                        if (stringTokenizer.countTokens() >= 2) {
                                            strNextToken = stringTokenizer.nextToken();
                                            String strNextToken3 = stringTokenizer.nextToken();
                                            if (TextUtils.isEmpty(strNextToken)) {
                                            }
                                        }
                                    }
                                    if (!map.isEmpty()) {
                                        arrayList2 = new ArrayList();
                                        setKeySet = map.keySet();
                                        while (r8.hasNext()) {
                                            if (map.containsValue(str)) {
                                                while (r10.hasNext()) {
                                                    if (str.equalsIgnoreCase((String) map.get(str2))) {
                                                        arrayList2.add(str2);
                                                    }
                                                }
                                            }
                                        }
                                        if (!arrayList2.isEmpty()) {
                                            while (r1.hasNext()) {
                                                Log.d("Fac_ExternalStorageHelper", String.format("duplicate, so remove, device: %s  path: %s", map.get(str3), str3));
                                                map.remove(str3);
                                            }
                                        }
                                        setKeySet2 = map.keySet();
                                        if (setKeySet2 != null) {
                                            it2 = setKeySet2.iterator();
                                            while (it2.hasNext()) {
                                                arrayList.add((String) it2.next());
                                            }
                                        }
                                    }
                                    return arrayList;
                                }
                                if (isDebug()) {
                                    Log.d("Fac_ExternalStorageHelper", "the mounts list is empty");
                                }
                                return null;
                            }
                        }
                    } catch (FileNotFoundException e3) {
                        e = e3;
                        bufferedReader = null;
                    } catch (IOException e4) {
                        e = e4;
                        bufferedReader = null;
                    } catch (Throwable th) {
                        th = th;
                        if (r3 != 0) {
                            try {
                                r3.close();
                            } catch (IOException unused2) {
                            }
                        }
                        throw th;
                    }
                    bufferedReader.close();
                } else {
                    Log.e("Fac_ExternalStorageHelper", "read mounts file: /proc/mounts error");
                }
            } catch (Throwable th2) {
                th = th2;
                r3 = CanRead;
            }
        } else {
            Log.e("Fac_ExternalStorageHelper", "read mounts file: /proc/mounts error");
        }
        if (!arrayList3.isEmpty()) {
            map = new HashMap();
            arrayList = new ArrayList();
            it = arrayList3.iterator();
            while (it.hasNext()) {
                stringTokenizer = new StringTokenizer((String) it.next());
                if (stringTokenizer.countTokens() >= 2) {
                    strNextToken = stringTokenizer.nextToken();
                    String strNextToken4 = stringTokenizer.nextToken();
                    if (TextUtils.isEmpty(strNextToken) && !TextUtils.isEmpty(strNextToken4) && !strNextToken4.startsWith("/mnt/secure") && (strNextToken4.startsWith("/mnt") || strNextToken4.startsWith("/storage"))) {
                        if (isDebug()) {
                            Log.d("Fac_ExternalStorageHelper", String.format("external storage, device: %s  path: %s", strNextToken, strNextToken4));
                        }
                        map.put(strNextToken4, strNextToken);
                    }
                }
            }
            if (!map.isEmpty()) {
                arrayList2 = new ArrayList();
                setKeySet = map.keySet();
                while (r8.hasNext()) {
                    if (map.containsValue(str)) {
                        while (r10.hasNext()) {
                            if (str.equalsIgnoreCase((String) map.get(str2))) {
                                arrayList2.add(str2);
                            }
                        }
                    }
                }
                if (!arrayList2.isEmpty()) {
                    while (r1.hasNext()) {
                        Log.d("Fac_ExternalStorageHelper", String.format("duplicate, so remove, device: %s  path: %s", map.get(str3), str3));
                        map.remove(str3);
                    }
                }
                setKeySet2 = map.keySet();
                if (setKeySet2 != null && !setKeySet2.isEmpty()) {
                    it2 = setKeySet2.iterator();
                    while (it2.hasNext()) {
                        arrayList.add((String) it2.next());
                    }
                }
            }
            return arrayList;
        }
        if (isDebug()) {
            Log.d("Fac_ExternalStorageHelper", "the mounts list is empty");
        }
        return null;
    }

    public static boolean isSDCardInsert(Context context) {
        StorageVolume[] volumeList = ((StorageManager) context.getSystemService("storage")).getVolumeList();
        if (volumeList != null && volumeList.length > 0) {
            for (StorageVolume storageVolume : volumeList) {
                if (storageVolume.isPrimary()) {
                    if (storageVolume.isEmulated()) {
                        Log.w("Storage", "primary volume is emulated");
                        return false;
                    }
                    String state = storageVolume.getState();
                    Log.d("Storage", "primary volume state: " + state);
                    if (state == null || "null".equalsIgnoreCase(state) || "mounted".equalsIgnoreCase(state) || "mounted_ro".equalsIgnoreCase(state)) {
                        return true;
                    }
                    Log.w("Storage", "primary volume not mounted");
                    return false;
                }
            }
            Log.w("Storage", "has no primary storage volume");
        } else {
            Log.w("Storage", "has no storage volume");
        }
        return false;
    }

    private static boolean isDebug() {
        return DEBUG || Log.isLoggable("Fac_ExternalStorageHelper", 3);
    }
}
