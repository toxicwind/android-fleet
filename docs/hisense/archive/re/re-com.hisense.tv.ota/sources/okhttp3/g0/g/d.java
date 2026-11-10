package okhttp3.g0.g;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import okhttp3.C0052l;
import okhttp3.J;
import okhttp3.K;
import okhttp3.X;
import okhttp3.Y;
import okhttp3.b0;
import okhttp3.c0;

/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final long f188a;
    final Y b;
    final c0 c;
    private Date d;
    private String e;
    private Date f;
    private String g;
    private Date h;
    private long i;
    private long j;
    private String k;
    private int l;

    public d(long j, Y y, c0 c0Var) {
        this.l = -1;
        this.f188a = j;
        this.b = y;
        this.c = c0Var;
        if (c0Var != null) {
            this.i = c0Var.X();
            this.j = c0Var.V();
            K kO = c0Var.O();
            int iG = kO.g();
            for (int i = 0; i < iG; i++) {
                String strE = kO.e(i);
                String strH = kO.h(i);
                if ("Date".equalsIgnoreCase(strE)) {
                    this.d = okhttp3.g0.h.f.b(strH);
                    this.e = strH;
                } else if ("Expires".equalsIgnoreCase(strE)) {
                    this.h = okhttp3.g0.h.f.b(strH);
                } else if ("Last-Modified".equalsIgnoreCase(strE)) {
                    this.f = okhttp3.g0.h.f.b(strH);
                    this.g = strH;
                } else if ("ETag".equalsIgnoreCase(strE)) {
                    this.k = strH;
                } else if ("Age".equalsIgnoreCase(strE)) {
                    this.l = okhttp3.g0.h.g.f(strH, -1);
                }
            }
        }
    }

    private long a() {
        Date date = this.d;
        long jMax = date != null ? Math.max(0L, this.j - date.getTime()) : 0L;
        int i = this.l;
        if (i != -1) {
            jMax = Math.max(jMax, TimeUnit.SECONDS.toMillis(i));
        }
        long j = this.j;
        return jMax + (j - this.i) + (this.f188a - j);
    }

    private long b() {
        C0052l c0052lE = this.c.e();
        if (c0052lE.d() != -1) {
            return TimeUnit.SECONDS.toMillis(c0052lE.d());
        }
        if (this.h != null) {
            Date date = this.d;
            long time = this.h.getTime() - (date != null ? date.getTime() : this.j);
            if (time > 0) {
                return time;
            }
            return 0L;
        }
        if (this.f == null || this.c.W().k().x() != null) {
            return 0L;
        }
        Date date2 = this.d;
        long time2 = (date2 != null ? date2.getTime() : this.i) - this.f.getTime();
        if (time2 > 0) {
            return time2 / 10;
        }
        return 0L;
    }

    private e d() {
        if (this.c == null) {
            return new e(this.b, null);
        }
        if ((!this.b.f() || this.c.B() != null) && e.a(this.c, this.b)) {
            C0052l c0052lB = this.b.b();
            if (c0052lB.h() || e(this.b)) {
                return new e(this.b, null);
            }
            C0052l c0052lE = this.c.e();
            long jA = a();
            long jB = b();
            if (c0052lB.d() != -1) {
                jB = Math.min(jB, TimeUnit.SECONDS.toMillis(c0052lB.d()));
            }
            long millis = 0;
            long millis2 = c0052lB.f() != -1 ? TimeUnit.SECONDS.toMillis(c0052lB.f()) : 0L;
            if (!c0052lE.g() && c0052lB.e() != -1) {
                millis = TimeUnit.SECONDS.toMillis(c0052lB.e());
            }
            if (!c0052lE.h()) {
                long j = millis2 + jA;
                if (j < millis + jB) {
                    b0 b0VarS = this.c.S();
                    if (j >= jB) {
                        b0VarS.a("Warning", "110 HttpURLConnection \"Response is stale\"");
                    }
                    if (jA > 86400000 && f()) {
                        b0VarS.a("Warning", "113 HttpURLConnection \"Heuristic expiration\"");
                    }
                    return new e(null, b0VarS.c());
                }
            }
            String str = this.k;
            String str2 = "If-Modified-Since";
            if (str != null) {
                str2 = "If-None-Match";
            } else if (this.f != null) {
                str = this.g;
            } else {
                if (this.d == null) {
                    return new e(this.b, null);
                }
                str = this.e;
            }
            J jF = this.b.e().f();
            okhttp3.g0.a.f182a.b(jF, str2, str);
            X xH = this.b.h();
            xH.g(jF.d());
            return new e(xH.b(), this.c);
        }
        return new e(this.b, null);
    }

    private static boolean e(Y y) {
        return (y.c("If-Modified-Since") == null && y.c("If-None-Match") == null) ? false : true;
    }

    private boolean f() {
        return this.c.e().d() == -1 && this.h == null;
    }

    public e c() {
        e eVarD = d();
        return (eVarD.f189a == null || !this.b.b().j()) ? eVarD : new e(null, null);
    }
}
