package okhttp3.logging;

import java.io.EOFException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import okhttp3.InterfaceC0059t;
import okhttp3.K;
import okhttp3.N;
import okhttp3.O;
import okhttp3.P;
import okhttp3.Y;
import okhttp3.a0;
import okhttp3.c0;
import okhttp3.e0;
import okhttp3.g0.h.g;
import okio.h;
import okio.j;
import okio.n;

/* JADX INFO: loaded from: classes.dex */
public final class b implements O {
    private static final Charset d = Charset.forName("UTF-8");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final a f250a;
    private volatile Set b = Collections.emptySet();
    private volatile HttpLoggingInterceptor$Level c = HttpLoggingInterceptor$Level.NONE;

    public b(a aVar) {
        this.f250a = aVar;
    }

    private static boolean b(K k) {
        String strC = k.c("Content-Encoding");
        return (strC == null || strC.equalsIgnoreCase("identity") || strC.equalsIgnoreCase("gzip")) ? false : true;
    }

    static boolean c(h hVar) {
        try {
            h hVar2 = new h();
            hVar.P(hVar2, 0L, hVar.c0() < 64 ? hVar.c0() : 64L);
            for (int i = 0; i < 16 && !hVar2.n(); i++) {
                int iA0 = hVar2.a0();
                if (Character.isISOControl(iA0) && !Character.isWhitespace(iA0)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException unused) {
            return false;
        }
    }

    private void d(K k, int i) {
        String strH = this.b.contains(k.e(i)) ? "██" : k.h(i);
        this.f250a.a(k.e(i) + ": " + strH);
    }

    @Override // okhttp3.O
    public c0 a(N n) throws Exception {
        char c;
        String string;
        a aVar;
        String string2;
        StringBuilder sb;
        String str;
        a aVar2;
        StringBuilder sb2;
        String strG;
        String string3;
        StringBuilder sb3;
        HttpLoggingInterceptor$Level httpLoggingInterceptor$Level = this.c;
        Y yA = n.a();
        if (httpLoggingInterceptor$Level == HttpLoggingInterceptor$Level.NONE) {
            return n.f(yA);
        }
        boolean z = httpLoggingInterceptor$Level == HttpLoggingInterceptor$Level.BODY;
        boolean z2 = z || httpLoggingInterceptor$Level == HttpLoggingInterceptor$Level.HEADERS;
        a0 a0VarA = yA.a();
        boolean z3 = a0VarA != null;
        InterfaceC0059t interfaceC0059tE = n.e();
        StringBuilder sb4 = new StringBuilder();
        sb4.append("--> ");
        sb4.append(yA.g());
        sb4.append(' ');
        sb4.append(yA.k());
        sb4.append(interfaceC0059tE != null ? " " + interfaceC0059tE.b() : "");
        String string4 = sb4.toString();
        if (!z2 && z3) {
            string4 = string4 + " (" + a0VarA.a() + "-byte body)";
        }
        this.f250a.a(string4);
        if (z2) {
            if (z3) {
                if (a0VarA.b() != null) {
                    this.f250a.a("Content-Type: " + a0VarA.b());
                }
                if (a0VarA.a() != -1) {
                    this.f250a.a("Content-Length: " + a0VarA.a());
                }
            }
            K kE = yA.e();
            int iG = kE.g();
            for (int i = 0; i < iG; i++) {
                String strE = kE.e(i);
                if (!"Content-Type".equalsIgnoreCase(strE) && !"Content-Length".equalsIgnoreCase(strE)) {
                    d(kE, i);
                }
            }
            if (z && z3) {
                if (b(yA.e())) {
                    aVar2 = this.f250a;
                    sb2 = new StringBuilder();
                    sb2.append("--> END ");
                    sb2.append(yA.g());
                    strG = " (encoded body omitted)";
                } else {
                    h hVar = new h();
                    a0VarA.e(hVar);
                    Charset charsetB = d;
                    P pB = a0VarA.b();
                    if (pB != null) {
                        charsetB = pB.b(d);
                    }
                    this.f250a.a("");
                    if (c(hVar)) {
                        this.f250a.a(hVar.C(charsetB));
                        aVar2 = this.f250a;
                        sb3 = new StringBuilder();
                        sb3.append("--> END ");
                        sb3.append(yA.g());
                        sb3.append(" (");
                        sb3.append(a0VarA.a());
                        sb3.append("-byte body)");
                    } else {
                        aVar2 = this.f250a;
                        sb3 = new StringBuilder();
                        sb3.append("--> END ");
                        sb3.append(yA.g());
                        sb3.append(" (binary ");
                        sb3.append(a0VarA.a());
                        sb3.append("-byte body omitted)");
                    }
                    string3 = sb3.toString();
                }
                aVar2.a(string3);
            } else {
                aVar2 = this.f250a;
                sb2 = new StringBuilder();
                sb2.append("--> END ");
                strG = yA.g();
            }
            sb2.append(strG);
            string3 = sb2.toString();
            aVar2.a(string3);
        }
        long jNanoTime = System.nanoTime();
        try {
            c0 c0VarF = n.f(yA);
            long millis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - jNanoTime);
            e0 e0VarA = c0VarF.a();
            long jP = e0VarA.p();
            String str2 = jP != -1 ? jP + "-byte" : "unknown-length";
            a aVar3 = this.f250a;
            StringBuilder sb5 = new StringBuilder();
            sb5.append("<-- ");
            sb5.append(c0VarF.p());
            if (c0VarF.Q().isEmpty()) {
                string = "";
                c = ' ';
            } else {
                StringBuilder sb6 = new StringBuilder();
                c = ' ';
                sb6.append(' ');
                sb6.append(c0VarF.Q());
                string = sb6.toString();
            }
            sb5.append(string);
            sb5.append(c);
            sb5.append(c0VarF.W().k());
            sb5.append(" (");
            sb5.append(millis);
            sb5.append("ms");
            sb5.append(z2 ? "" : ", " + str2 + " body");
            sb5.append(')');
            aVar3.a(sb5.toString());
            if (z2) {
                K kO = c0VarF.O();
                int iG2 = kO.g();
                for (int i2 = 0; i2 < iG2; i2++) {
                    d(kO, i2);
                }
                if (!z || !g.c(c0VarF)) {
                    aVar = this.f250a;
                    string2 = "<-- END HTTP";
                } else if (b(c0VarF.O())) {
                    aVar = this.f250a;
                    string2 = "<-- END HTTP (encoded body omitted)";
                } else {
                    j jVarP = e0VarA.P();
                    jVarP.z(Long.MAX_VALUE);
                    h hVarC = jVarP.c();
                    Long l = null;
                    n nVar = null;
                    if ("gzip".equalsIgnoreCase(kO.c("Content-Encoding"))) {
                        Long lValueOf = Long.valueOf(hVarC.c0());
                        try {
                            n nVar2 = new n(hVarC.clone());
                            try {
                                hVarC = new h();
                                hVarC.j0(nVar2);
                                nVar2.close();
                                l = lValueOf;
                            } catch (Throwable th) {
                                th = th;
                                nVar = nVar2;
                                if (nVar != null) {
                                    nVar.close();
                                }
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    }
                    Charset charsetB2 = d;
                    P pB2 = e0VarA.B();
                    if (pB2 != null) {
                        charsetB2 = pB2.b(d);
                    }
                    if (!c(hVarC)) {
                        this.f250a.a("");
                        this.f250a.a("<-- END HTTP (binary " + hVarC.c0() + "-byte body omitted)");
                        return c0VarF;
                    }
                    if (jP != 0) {
                        this.f250a.a("");
                        this.f250a.a(hVarC.clone().C(charsetB2));
                    }
                    aVar = this.f250a;
                    if (l != null) {
                        sb = new StringBuilder();
                        sb.append("<-- END HTTP (");
                        sb.append(hVarC.c0());
                        sb.append("-byte, ");
                        sb.append(l);
                        str = "-gzipped-byte body)";
                    } else {
                        sb = new StringBuilder();
                        sb.append("<-- END HTTP (");
                        sb.append(hVarC.c0());
                        str = "-byte body)";
                    }
                    sb.append(str);
                    string2 = sb.toString();
                }
                aVar.a(string2);
            }
            return c0VarF;
        } catch (Exception e) {
            this.f250a.a("<-- HTTP FAILED: " + e);
            throw e;
        }
    }

    public b e(HttpLoggingInterceptor$Level httpLoggingInterceptor$Level) {
        if (httpLoggingInterceptor$Level == null) {
            throw new NullPointerException("level == null. Use Level.NONE instead.");
        }
        this.c = httpLoggingInterceptor$Level;
        return this;
    }
}
