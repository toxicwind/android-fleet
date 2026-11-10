package com.ju.lib.datareport;

import b.a.a.a.a;
import com.ju.lib.utils.log.LogUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/* JADX INFO: loaded from: classes2.dex */
public class Zip {
    public static final String TAG = "Zip";

    public byte[] compress(byte[] bArr) throws Throwable {
        GZIPOutputStream gZIPOutputStream;
        GZIPOutputStream gZIPOutputStream2 = null;
        try {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                try {
                    gZIPOutputStream.write(bArr);
                    gZIPOutputStream.flush();
                    Util.close(gZIPOutputStream);
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    String str = TAG;
                    StringBuilder sbA = a.a("compress ok, ");
                    sbA.append(bArr.length);
                    sbA.append(" to ");
                    sbA.append(byteArray.length);
                    LogUtil.i(str, sbA.toString());
                    return byteArray;
                } catch (IOException e2) {
                    e = e2;
                    gZIPOutputStream2 = gZIPOutputStream;
                    throw new RuntimeException("compress error! ", e);
                } catch (Throwable th) {
                    th = th;
                    Util.close(gZIPOutputStream);
                    throw th;
                }
            } catch (IOException e3) {
                e = e3;
            }
        } catch (Throwable th2) {
            th = th2;
            gZIPOutputStream = gZIPOutputStream2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v18, types: [java.io.Closeable[]] */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.io.Closeable[]] */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference failed for: r7v8, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r7v9 */
    public byte[] uncompress(byte[] bArr) throws Throwable {
        ByteArrayInputStream byteArrayInputStream;
        GZIPInputStream gZIPInputStream;
        ?? byteArrayOutputStream;
        ByteArrayInputStream byteArrayInputStream2 = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(bArr);
            try {
                gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
                try {
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    try {
                        byte[] bArr2 = new byte[1024];
                        while (true) {
                            int i = gZIPInputStream.read(bArr2);
                            if (i == -1) {
                                byte[] byteArray = byteArrayOutputStream.toByteArray();
                                LogUtil.i(TAG, "uncompress ok, " + bArr.length + " to " + byteArray.length);
                                Util.close(new Closeable[]{byteArrayInputStream, gZIPInputStream, byteArrayOutputStream});
                                return byteArray;
                            }
                            byteArrayOutputStream.write(bArr2, 0, i);
                        }
                    } catch (IOException e2) {
                        e = e2;
                        byteArrayInputStream2 = byteArrayInputStream;
                        byteArrayOutputStream = byteArrayOutputStream;
                        try {
                            throw new RuntimeException("uncompress error! ", e);
                        } catch (Throwable th) {
                            th = th;
                            byteArrayInputStream = byteArrayInputStream2;
                            Util.close(new Closeable[]{byteArrayInputStream, gZIPInputStream, byteArrayOutputStream});
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        Util.close(new Closeable[]{byteArrayInputStream, gZIPInputStream, byteArrayOutputStream});
                        throw th;
                    }
                } catch (IOException e3) {
                    e = e3;
                    byteArrayOutputStream = 0;
                } catch (Throwable th3) {
                    th = th3;
                    byteArrayOutputStream = 0;
                }
            } catch (IOException e4) {
                e = e4;
                gZIPInputStream = null;
                byteArrayOutputStream = 0;
            } catch (Throwable th4) {
                th = th4;
                gZIPInputStream = null;
                byteArrayOutputStream = gZIPInputStream;
                Util.close(new Closeable[]{byteArrayInputStream, gZIPInputStream, byteArrayOutputStream});
                throw th;
            }
        } catch (IOException e5) {
            e = e5;
            gZIPInputStream = null;
            byteArrayOutputStream = 0;
        } catch (Throwable th5) {
            th = th5;
            byteArrayInputStream = null;
            gZIPInputStream = null;
        }
    }
}
