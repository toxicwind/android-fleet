package com.hisense.tv.ota.V;

import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

/* JADX INFO: loaded from: classes.dex */
class f {
    static void a(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static void b(RandomAccessFile randomAccessFile) {
        if (randomAccessFile != null) {
            try {
                randomAccessFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static long c(String str, long j) {
        if (TextUtils.isEmpty(str)) {
            return 0L;
        }
        File file = new File(str);
        if (!file.exists()) {
            return 0L;
        }
        long length = file.length() - j;
        if (length < 0) {
            return 0L;
        }
        return length;
    }

    static RandomAccessFile d(String str, long j) {
        String str2;
        String str3;
        RandomAccessFile randomAccessFile = null;
        if (TextUtils.isEmpty(str)) {
            str3 = "getRandomFileOS failed, file path is empty";
        } else {
            long j2 = 0;
            if (j < 0) {
                str3 = "getRandomFileOS failed, invalid seek back value: " + j;
            } else {
                try {
                    RandomAccessFile randomAccessFile2 = new RandomAccessFile(str, "rws");
                    try {
                        long length = randomAccessFile2.length();
                        Log.i("Utils", "old file length: " + length);
                        long j3 = length - j;
                        if (j3 >= 0) {
                            j2 = j3;
                        }
                        Log.i("Utils", "getRandomFileOS, seek: " + j2);
                        randomAccessFile2.seek(j2);
                        return randomAccessFile2;
                    } catch (FileNotFoundException e) {
                        e = e;
                        randomAccessFile = randomAccessFile2;
                        str2 = "getRandomFileOS FileNotFoundException";
                        Log.e("Utils", str2, e);
                        return randomAccessFile;
                    } catch (IOException e2) {
                        e = e2;
                        randomAccessFile = randomAccessFile2;
                        str2 = "getRandomFileOS IOException";
                        Log.e("Utils", str2, e);
                        return randomAccessFile;
                    }
                } catch (FileNotFoundException e3) {
                    e = e3;
                } catch (IOException e4) {
                    e = e4;
                }
            }
        }
        Log.e("Utils", str3);
        return randomAccessFile;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11, types: [int] */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r2v5, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.io.FileOutputStream] */
    static boolean e(InputStream inputStream, String str, boolean z) throws Throwable {
        String str2;
        boolean z2 = false;
        if (inputStream != null) {
            if (TextUtils.isEmpty(str)) {
                str2 = "write file from in stream failed, file path is empty";
            } else {
                ?? r2 = 0;
                r2 = 0;
                r2 = 0;
                try {
                    try {
                        ?? fileOutputStream = new FileOutputStream(str);
                        try {
                            byte[] bArr = new byte[10240];
                            while (true) {
                                r2 = inputStream.read(bArr);
                                if (r2 < 0) {
                                    break;
                                }
                                fileOutputStream.write(bArr, 0, r2);
                            }
                            z2 = true;
                            try {
                                fileOutputStream.close();
                            } catch (IOException unused) {
                            }
                            if (z && inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException unused2) {
                                }
                            }
                        } catch (FileNotFoundException e) {
                            e = e;
                            r2 = fileOutputStream;
                            Log.e("Utils", "write file from in stream failed, FileNotFoundException", e);
                            if (r2 != 0) {
                                try {
                                    r2.close();
                                } catch (IOException unused3) {
                                }
                            }
                            if (z && inputStream != null) {
                            }
                            return z2;
                        } catch (IOException e2) {
                            e = e2;
                            r2 = fileOutputStream;
                            Log.e("Utils", "write file from in stream failed, IOException", e);
                            if (r2 != 0) {
                                try {
                                    r2.close();
                                } catch (IOException unused4) {
                                }
                            }
                            if (z && inputStream != null) {
                            }
                            return z2;
                        } catch (Throwable th) {
                            th = th;
                            r2 = fileOutputStream;
                            if (r2 != 0) {
                                try {
                                    r2.close();
                                } catch (IOException unused5) {
                                }
                            }
                            if (!z || inputStream == null) {
                                throw th;
                            }
                            try {
                                inputStream.close();
                                throw th;
                            } catch (IOException unused6) {
                                throw th;
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (FileNotFoundException e3) {
                    e = e3;
                } catch (IOException e4) {
                    e = e4;
                }
            }
            return z2;
        }
        str2 = "write file from in stream failed, input stream is null";
        Log.e("Utils", str2);
        return z2;
    }
}
