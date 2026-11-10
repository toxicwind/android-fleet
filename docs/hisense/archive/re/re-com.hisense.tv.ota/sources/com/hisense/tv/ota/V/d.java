package com.hisense.tv.ota.V;

import android.os.SystemClock;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.lang.ref.WeakReference;
import okhttp3.InterfaceC0053m;
import okhttp3.InterfaceC0054n;
import okhttp3.c0;
import okhttp3.e0;

/* JADX INFO: loaded from: classes.dex */
class d implements InterfaceC0054n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private c f24a;
    private WeakReference b;
    private String c;

    d(e eVar, c cVar, InterfaceC0053m interfaceC0053m, String str) {
        this.f24a = cVar;
        this.b = new WeakReference(interfaceC0053m);
        this.c = str;
    }

    private void c(e0 e0Var) throws Throwable {
        InputStream inputStreamA;
        int i = -1;
        RandomAccessFile randomAccessFileD = null;
        try {
            long jP = e0Var.p();
            Log.i("OkDownloadEngine", "content length: " + jP);
            if (jP <= 0) {
                if (this.f24a != null) {
                    this.f24a.b(this.c, 7);
                }
                f.b(null);
                f.a(null);
                return;
            }
            Log.i("OkDownloadEngine", "content type: " + e0Var.B());
            inputStreamA = e0Var.a();
            try {
                try {
                    randomAccessFileD = f.d(this.c, 10485760L);
                    try {
                        byte[] bArr = new byte[1048576];
                        long filePointer = randomAccessFileD != null ? randomAccessFileD.getFilePointer() : 0L;
                        long j = jP + filePointer;
                        Log.i("OkDownloadEngine", "total length: " + j);
                        long jUptimeMillis = SystemClock.uptimeMillis();
                        if (this.f24a != null) {
                            this.f24a.c(this.c);
                        }
                        while (true) {
                            int i2 = inputStreamA.read(bArr);
                            if (i2 == i) {
                                break;
                            }
                            randomAccessFileD.write(bArr, 0, i2);
                            RandomAccessFile randomAccessFile = randomAccessFileD;
                            filePointer += (long) i2;
                            try {
                                long jUptimeMillis2 = SystemClock.uptimeMillis();
                                if (jUptimeMillis2 - jUptimeMillis >= 1000) {
                                    if (this.f24a != null) {
                                        this.f24a.e(this.c, (int) ((100 * filePointer) / j));
                                    }
                                    jUptimeMillis = jUptimeMillis2;
                                }
                                randomAccessFileD = randomAccessFile;
                                i = -1;
                            } catch (IOException e) {
                                e = e;
                                randomAccessFileD = randomAccessFile;
                            } catch (Throwable th) {
                                th = th;
                                randomAccessFileD = randomAccessFile;
                                f.b(randomAccessFileD);
                                f.a(inputStreamA);
                                throw th;
                            }
                            Log.e("OkDownloadEngine", "download IOException", e);
                            if (this.b != null && this.b.get() != null && ((InterfaceC0053m) this.b.get()).p()) {
                                Log.i("OkDownloadEngine", "user canceled download");
                                if (this.f24a != null) {
                                    this.f24a.a(this.c);
                                    f.b(randomAccessFileD);
                                    f.a(inputStreamA);
                                    return;
                                }
                            }
                            String message = e.getMessage();
                            Log.e("OkDownloadEngine", "error message is :" + message);
                            if (message != null && message.contains("Quota exceeded")) {
                                Log.i("OkDownloadEngine", "Quota exceeded, delete downloaded package");
                                Log.i("OkDownloadEngine", "delete reult: " + new File(this.c).delete());
                            }
                            if (this.f24a != null) {
                                if (message != null && message.contains("connection abort")) {
                                    Log.i("OkDownloadEngine", "network is broken off");
                                    this.f24a.b(this.c, -4);
                                } else if (message == null || !message.contains("ENOSPC")) {
                                    this.f24a.b(this.c, 6);
                                } else {
                                    this.f24a.b(this.c, -1);
                                }
                            }
                            f.b(randomAccessFileD);
                            f.a(inputStreamA);
                        }
                        RandomAccessFile randomAccessFile2 = randomAccessFileD;
                        if (this.f24a != null) {
                            this.f24a.e(this.c, (int) ((100 * filePointer) / j));
                        }
                        if (filePointer == j) {
                            Log.i("OkDownloadEngine", "download finished");
                            if (this.f24a != null) {
                                this.f24a.d(this.c);
                            }
                        } else {
                            Log.e("OkDownloadEngine", "download error, size not matched");
                            Log.e("OkDownloadEngine", "downloadLen: " + filePointer + ", contentLen: " + jP);
                            if (this.f24a != null) {
                                this.f24a.b(this.c, 5);
                            }
                        }
                        f.b(randomAccessFile2);
                    } catch (IOException e2) {
                        e = e2;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (IOException e3) {
                    e = e3;
                }
                f.a(inputStreamA);
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (IOException e4) {
            e = e4;
            inputStreamA = null;
        } catch (Throwable th4) {
            th = th4;
            inputStreamA = null;
        }
    }

    @Override // okhttp3.InterfaceC0054n
    public void a(InterfaceC0053m interfaceC0053m, c0 c0Var) throws Throwable {
        Log.i("OkDownloadEngine", "onResponse, response: " + c0Var);
        if (c0Var.P()) {
            c(c0Var.a());
            return;
        }
        Log.e("OkDownloadEngine", "response is not successful");
        c cVar = this.f24a;
        if (cVar != null) {
            cVar.b(this.c, 3);
        }
    }

    @Override // okhttp3.InterfaceC0054n
    public void b(InterfaceC0053m interfaceC0053m, IOException iOException) {
        Log.e("OkDownloadEngine", "onFailure e =" + iOException);
        SystemClock.sleep(1500L);
        c cVar = this.f24a;
        if (cVar != null) {
            cVar.b(this.c, 4);
        }
    }
}
