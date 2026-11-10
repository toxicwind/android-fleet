package com.hisense.tv.ota.W;

import android.text.TextUtils;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* JADX INFO: loaded from: classes.dex */
public class e {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v10, types: [java.io.InputStream, java.util.zip.ZipInputStream] */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r2v19 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v20 */
    /* JADX WARN: Type inference failed for: r2v21 */
    /* JADX WARN: Type inference failed for: r2v22 */
    /* JADX WARN: Type inference failed for: r2v23 */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x005f -> B:50:0x0081). Please report as a decompilation issue!!! */
    public static int a(String str) throws Throwable {
        if (TextUtils.isEmpty(str)) {
            Log.e("ParseZipFileUtils", " getIncrementalFlagFromZipFile FilePath is empty");
            return -1;
        }
        int i = 0;
        ?? r2 = 0;
        BufferedInputStream bufferedInputStream = null;
        BufferedInputStream bufferedInputStream2 = null;
        r2 = 0;
        try {
            try {
                try {
                    BufferedInputStream bufferedInputStream3 = new BufferedInputStream(new FileInputStream(str));
                    try {
                        Charset charsetForName = Charset.forName("UTF-8");
                        ?? zipInputStream = new ZipInputStream(bufferedInputStream3, charsetForName);
                        while (true) {
                            ZipEntry nextEntry = zipInputStream.getNextEntry();
                            if (nextEntry == null) {
                                break;
                            }
                            if (nextEntry.getName().endsWith("metadata")) {
                                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((InputStream) zipInputStream, charsetForName));
                                while (true) {
                                    String line = bufferedReader.readLine();
                                    if (line == null) {
                                        break;
                                    }
                                    zipInputStream = "pre-build-incremental";
                                    if (line.contains("pre-build-incremental")) {
                                        Log.i("ParseZipFileUtils", "incremental OTA upgrade ");
                                        i = 1;
                                        break;
                                    }
                                }
                                bufferedReader.close();
                                break;
                            }
                        }
                        bufferedInputStream3.close();
                        r2 = zipInputStream;
                    } catch (FileNotFoundException e) {
                        e = e;
                        bufferedInputStream = bufferedInputStream3;
                        e.printStackTrace();
                        r2 = bufferedInputStream;
                        if (bufferedInputStream != null) {
                            bufferedInputStream.close();
                            r2 = bufferedInputStream;
                        }
                    } catch (IOException e2) {
                        e = e2;
                        bufferedInputStream2 = bufferedInputStream3;
                        e.printStackTrace();
                        r2 = bufferedInputStream2;
                        if (bufferedInputStream2 != null) {
                            bufferedInputStream2.close();
                            r2 = bufferedInputStream2;
                        }
                    } catch (Throwable th) {
                        th = th;
                        r2 = bufferedInputStream3;
                        if (r2 != 0) {
                            try {
                                r2.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (FileNotFoundException e4) {
                e = e4;
            } catch (IOException e5) {
                e = e5;
            }
        } catch (IOException e6) {
            e6.printStackTrace();
            r2 = r2;
        }
        return i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v2, types: [long[]] */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11, types: [java.io.InputStream, java.util.zip.ZipInputStream] */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v18, types: [boolean] */
    /* JADX WARN: Type inference failed for: r4v19 */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r4v20, types: [boolean] */
    /* JADX WARN: Type inference failed for: r4v21, types: [boolean] */
    /* JADX WARN: Type inference failed for: r4v24, types: [long] */
    /* JADX WARN: Type inference failed for: r4v25 */
    /* JADX WARN: Type inference failed for: r4v26 */
    /* JADX WARN: Type inference failed for: r4v27 */
    /* JADX WARN: Type inference failed for: r4v28 */
    /* JADX WARN: Type inference failed for: r4v29 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v30 */
    /* JADX WARN: Type inference failed for: r4v4, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r4v5, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9 */
    public static long[] b(String str) throws Throwable {
        ?? r4 = 0;
        r4 = 0;
        r4 = 0;
        r4 = 0;
        if (TextUtils.isEmpty(str)) {
            Log.e("ParseZipFileUtils", " getOffsetAndSizeFromZipFile FilePath is empty");
            return null;
        }
        ?? r3 = new long[2];
        try {
            try {
                try {
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(str));
                    try {
                        Charset charsetForName = Charset.forName("UTF-8");
                        ?? zipInputStream = new ZipInputStream(bufferedInputStream, charsetForName);
                        while (true) {
                            ZipEntry nextEntry = zipInputStream.getNextEntry();
                            if (nextEntry == null) {
                                break;
                            }
                            Log.i("ParseZipFileUtils", "filename is " + nextEntry.getName());
                            if (nextEntry.getName().endsWith("metadata")) {
                                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((InputStream) zipInputStream, charsetForName));
                                while (true) {
                                    String line = bufferedReader.readLine();
                                    if (line == null) {
                                        break;
                                    }
                                    Log.i("ParseZipFileUtils", "line 1 =  " + line);
                                    zipInputStream = line.contains("ota-downgrade");
                                    if (zipInputStream == 0) {
                                        zipInputStream = line.contains("payload.bin:");
                                        if (zipInputStream != 0 && (zipInputStream = line.contains("payload_properties.txt")) != 0) {
                                            String strSubstring = line.substring(line.indexOf("payload.bin:"), line.indexOf("payload_properties.txt"));
                                            Log.i("ParseZipFileUtils", "line 2 =  " + strSubstring);
                                            String strSubstring2 = strSubstring.substring(12, strSubstring.indexOf(","));
                                            Log.i("ParseZipFileUtils", "line 3 =  " + strSubstring2);
                                            String strSubstring3 = strSubstring2.substring(0, strSubstring2.indexOf(":"));
                                            Log.i("ParseZipFileUtils", "stroffset  " + strSubstring3);
                                            r3[0] = Long.parseLong(strSubstring3);
                                            String strSubstring4 = strSubstring2.substring(strSubstring2.indexOf(":") + 1);
                                            Log.i("ParseZipFileUtils", "strsize  " + strSubstring4);
                                            zipInputStream = Long.parseLong(strSubstring4);
                                            r3[1] = zipInputStream;
                                            break;
                                        }
                                    } else {
                                        Log.i("ParseZipFileUtils", "OTA downgrade maybe this is abnormal ");
                                    }
                                }
                                bufferedReader.close();
                                break;
                            }
                        }
                        bufferedInputStream.close();
                        r4 = zipInputStream;
                    } catch (FileNotFoundException e) {
                        e = e;
                        r4 = bufferedInputStream;
                        e.printStackTrace();
                        if (r4 != 0) {
                            r4.close();
                            r4 = r4;
                        }
                        return r3;
                    } catch (IOException e2) {
                        e = e2;
                        r4 = bufferedInputStream;
                        e.printStackTrace();
                        if (r4 != 0) {
                            r4.close();
                            r4 = r4;
                        }
                        return r3;
                    } catch (Throwable th) {
                        th = th;
                        r4 = bufferedInputStream;
                        if (r4 != 0) {
                            try {
                                r4.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            } catch (FileNotFoundException e5) {
                e = e5;
            } catch (IOException e6) {
                e = e6;
            }
            return r3;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static String[] c(String str) throws Throwable {
        InputStream inputStream = null;
        inputStream = null;
        inputStream = null;
        inputStream = null;
        inputStream = null;
        if (TextUtils.isEmpty(str)) {
            Log.e("ParseZipFileUtils", " getPropertiesFromZipFile FilePath is empty");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        try {
            try {
                try {
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(str));
                    try {
                        Charset charsetForName = Charset.forName("UTF-8");
                        ZipInputStream zipInputStream = new ZipInputStream(bufferedInputStream, charsetForName);
                        while (true) {
                            ZipEntry nextEntry = zipInputStream.getNextEntry();
                            if (nextEntry == null) {
                                break;
                            }
                            if (nextEntry.getName().endsWith("payload_properties.txt")) {
                                nextEntry.getName();
                                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(zipInputStream, charsetForName));
                                while (true) {
                                    String line = bufferedReader.readLine();
                                    if (line == null) {
                                        break;
                                    }
                                    arrayList.add(line);
                                }
                                bufferedReader.close();
                                break;
                            }
                        }
                        bufferedInputStream.close();
                        inputStream = zipInputStream;
                    } catch (FileNotFoundException e) {
                        e = e;
                        inputStream = bufferedInputStream;
                        e.printStackTrace();
                        if (inputStream != null) {
                            inputStream.close();
                            inputStream = inputStream;
                        }
                        return (String[]) arrayList.toArray(new String[arrayList.size()]);
                    } catch (IOException e2) {
                        e = e2;
                        inputStream = bufferedInputStream;
                        e.printStackTrace();
                        if (inputStream != null) {
                            inputStream.close();
                            inputStream = inputStream;
                        }
                        return (String[]) arrayList.toArray(new String[arrayList.size()]);
                    } catch (Exception e3) {
                        e = e3;
                        inputStream = bufferedInputStream;
                        e.printStackTrace();
                        if (inputStream != null) {
                            inputStream.close();
                            inputStream = inputStream;
                        }
                        return (String[]) arrayList.toArray(new String[arrayList.size()]);
                    } catch (Throwable th) {
                        th = th;
                        inputStream = bufferedInputStream;
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            } catch (FileNotFoundException e6) {
                e = e6;
            } catch (IOException e7) {
                e = e7;
            } catch (Exception e8) {
                e = e8;
            }
            return (String[]) arrayList.toArray(new String[arrayList.size()]);
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
