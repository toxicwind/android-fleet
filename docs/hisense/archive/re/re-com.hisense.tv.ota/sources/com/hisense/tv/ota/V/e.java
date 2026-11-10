package com.hisense.tv.ota.V;

import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import okhttp3.InterfaceC0053m;
import okhttp3.S;
import okhttp3.T;
import okhttp3.X;
import okhttp3.Y;
import okhttp3.c0;

/* JADX INFO: loaded from: classes.dex */
public class e extends a {
    private static final Object b = new Object();
    private static e c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private T f25a;

    private e() {
        T tB;
        this.f25a = null;
        if (SystemProperties.get("sys.vt.ota.test.env").equals("true")) {
            tB = new T();
        } else {
            S s = new S();
            com.hisense.tv.ota.W.b bVar = com.hisense.tv.ota.W.b.e;
            s.j(bVar, bVar.a());
            tB = s.b();
        }
        this.f25a = tB;
    }

    public static e c() {
        if (c == null) {
            synchronized (e.class) {
                if (c == null) {
                    c = new e();
                }
            }
        }
        return c;
    }

    private String d(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String lowerCase = str.toLowerCase(Locale.US);
        if (lowerCase.startsWith("http://") || lowerCase.startsWith("https://")) {
            return str;
        }
        return "http://" + str;
    }

    @Override // com.hisense.tv.ota.V.a
    public boolean a(String str, String str2, boolean z, c cVar) {
        Log.i("OkDownloadEngine", String.format("request download ...\nurl: %s\nfilePath: %s\nresume: %b", str, str2, Boolean.valueOf(z)));
        Log.i("OkDownloadEngine", String.format("request download ...\n filePath: %s\nresume: %b", str2, Boolean.valueOf(z)));
        if (cVar == null) {
            Log.e("OkDownloadEngine", "download failed, listener is null");
            return false;
        }
        if (TextUtils.isEmpty(str)) {
            Log.e("OkDownloadEngine", "download failed, url is empty");
            cVar.b(str2, 1);
            return false;
        }
        if (TextUtils.isEmpty(str2)) {
            Log.e("OkDownloadEngine", "download failed, file path is empty");
            cVar.b(str2, 2);
            return false;
        }
        long jC = z ? f.c(str2, 10485760L) : 0L;
        Log.i("OkDownloadEngine", "range: " + jC);
        Log.i("OkDownloadEngine", "use ok http client ...");
        X x = new X();
        x.d();
        x.o(str);
        x.f("RANGE", "bytes=" + jC + "-");
        x.n(b);
        Y yB = x.b();
        Log.i("OkDownloadEngine", "download request: " + yB);
        InterfaceC0053m interfaceC0053mU = this.f25a.u(yB);
        interfaceC0053mU.B(new d(this, cVar, interfaceC0053mU, str2));
        return true;
    }

    @Override // com.hisense.tv.ota.V.a
    public boolean b(String str, String str2) throws Throwable {
        String str3;
        boolean zE = false;
        if (TextUtils.isEmpty(str)) {
            str3 = "sync download failed, url is empty";
        } else if (TextUtils.isEmpty(str2)) {
            str3 = "sync download failed, file path is empty";
        } else {
            File file = new File(str2);
            if (!file.exists() || file.delete()) {
                T t = new T();
                try {
                    String strD = d(str);
                    X x = new X();
                    x.o(strD);
                    try {
                        c0 c0VarE = t.u(x.b()).e();
                        if (c0VarE == null || !c0VarE.P()) {
                            Log.e("OkDownloadEngine", "sync download failed, response is not successful");
                        } else {
                            zE = f.e(c0VarE.a().a(), str2, true);
                        }
                    } catch (IOException e) {
                        Log.e("OkDownloadEngine", "sync download failed IOException", e);
                    }
                } catch (Exception unused) {
                    str3 = "sync download failed, invalid url: " + str;
                    Log.e("OkDownloadEngine", str3);
                }
                return zE;
            }
            str3 = "sync download failed, file already exists, but can't delete";
        }
        Log.e("OkDownloadEngine", str3);
        return zE;
    }
}
