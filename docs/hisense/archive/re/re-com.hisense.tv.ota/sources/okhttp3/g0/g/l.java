package okhttp3.g0.g;

import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.Flushable;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import okio.t;

/* JADX INFO: loaded from: classes.dex */
public final class l implements Closeable, Flushable {
    static final Pattern u = Pattern.compile("[a-z0-9_-]{1,120}");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final okhttp3.g0.j.b f194a;
    final File b;
    private final File c;
    private final File d;
    private final File e;
    private final int f;
    private long g;
    final int h;
    okio.i j;
    int l;
    boolean m;
    boolean n;
    boolean o;
    boolean p;
    boolean q;
    private final Executor s;
    private long i = 0;
    final LinkedHashMap k = new LinkedHashMap(0, 0.75f, true);
    private long r = 0;
    private final Runnable t = new f(this);

    l(okhttp3.g0.j.b bVar, File file, int i, int i2, long j, Executor executor) {
        this.f194a = bVar;
        this.b = file;
        this.f = i;
        this.c = new File(file, "journal");
        this.d = new File(file, "journal.tmp");
        this.e = new File(file, "journal.bkp");
        this.h = i2;
        this.g = j;
        this.s = executor;
    }

    private okio.i S() {
        return t.c(new g(this, this.f194a.h(this.c)));
    }

    private void T() {
        this.f194a.b(this.d);
        Iterator it = this.k.values().iterator();
        while (it.hasNext()) {
            j jVar = (j) it.next();
            int i = 0;
            if (jVar.f == null) {
                while (i < this.h) {
                    this.i += jVar.b[i];
                    i++;
                }
            } else {
                jVar.f = null;
                while (i < this.h) {
                    this.f194a.b(jVar.c[i]);
                    this.f194a.b(jVar.d[i]);
                    i++;
                }
                it.remove();
            }
        }
    }

    private void U() {
        okio.j jVarD = t.d(this.f194a.c(this.c));
        try {
            String strE = jVarD.E();
            String strE2 = jVarD.E();
            String strE3 = jVarD.E();
            String strE4 = jVarD.E();
            String strE5 = jVarD.E();
            if (!"libcore.io.DiskLruCache".equals(strE) || !"1".equals(strE2) || !Integer.toString(this.f).equals(strE3) || !Integer.toString(this.h).equals(strE4) || !"".equals(strE5)) {
                throw new IOException("unexpected journal header: [" + strE + ", " + strE2 + ", " + strE4 + ", " + strE5 + "]");
            }
            int i = 0;
            while (true) {
                try {
                    V(jVarD.E());
                    i++;
                } catch (EOFException unused) {
                    this.l = i - this.k.size();
                    if (jVarD.n()) {
                        this.j = S();
                    } else {
                        W();
                    }
                    okhttp3.g0.e.g(jVarD);
                    return;
                }
            }
        } catch (Throwable th) {
            okhttp3.g0.e.g(jVarD);
            throw th;
        }
    }

    private void V(String str) throws IOException {
        String strSubstring;
        int iIndexOf = str.indexOf(32);
        if (iIndexOf == -1) {
            throw new IOException("unexpected journal line: " + str);
        }
        int i = iIndexOf + 1;
        int iIndexOf2 = str.indexOf(32, i);
        if (iIndexOf2 == -1) {
            strSubstring = str.substring(i);
            if (iIndexOf == 6 && str.startsWith("REMOVE")) {
                this.k.remove(strSubstring);
                return;
            }
        } else {
            strSubstring = str.substring(i, iIndexOf2);
        }
        j jVar = (j) this.k.get(strSubstring);
        if (jVar == null) {
            jVar = new j(this, strSubstring);
            this.k.put(strSubstring, jVar);
        }
        if (iIndexOf2 != -1 && iIndexOf == 5 && str.startsWith("CLEAN")) {
            String[] strArrSplit = str.substring(iIndexOf2 + 1).split(" ");
            jVar.e = true;
            jVar.f = null;
            jVar.b(strArrSplit);
            return;
        }
        if (iIndexOf2 == -1 && iIndexOf == 5 && str.startsWith("DIRTY")) {
            jVar.f = new i(this, jVar);
            return;
        }
        if (iIndexOf2 == -1 && iIndexOf == 4 && str.startsWith("READ")) {
            return;
        }
        throw new IOException("unexpected journal line: " + str);
    }

    private synchronized void a() {
        if (Q()) {
            throw new IllegalStateException("cache is closed");
        }
    }

    private void a0(String str) {
        if (u.matcher(str).matches()) {
            return;
        }
        throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + str + "\"");
    }

    public static l p(okhttp3.g0.j.b bVar, File file, int i, int i2, long j) {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        if (i2 > 0) {
            return new l(bVar, file, i, i2, j, new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), okhttp3.g0.e.G("OkHttp DiskLruCache", true)));
        }
        throw new IllegalArgumentException("valueCount <= 0");
    }

    public void B() {
        close();
        this.f194a.g(this.b);
    }

    public i M(String str) {
        return N(str, -1L);
    }

    synchronized i N(String str, long j) {
        P();
        a();
        a0(str);
        j jVar = (j) this.k.get(str);
        if (j != -1 && (jVar == null || jVar.g != j)) {
            return null;
        }
        if (jVar != null && jVar.f != null) {
            return null;
        }
        if (!this.p && !this.q) {
            this.j.f("DIRTY").q(32).f(str).q(10);
            this.j.flush();
            if (this.m) {
                return null;
            }
            if (jVar == null) {
                jVar = new j(this, str);
                this.k.put(str, jVar);
            }
            i iVar = new i(this, jVar);
            jVar.f = iVar;
            return iVar;
        }
        this.s.execute(this.t);
        return null;
    }

    public synchronized k O(String str) {
        P();
        a();
        a0(str);
        j jVar = (j) this.k.get(str);
        if (jVar != null && jVar.e) {
            k kVarC = jVar.c();
            if (kVarC == null) {
                return null;
            }
            this.l++;
            this.j.f("READ").q(32).f(str).q(10);
            if (R()) {
                this.s.execute(this.t);
            }
            return kVarC;
        }
        return null;
    }

    public synchronized void P() {
        if (this.n) {
            return;
        }
        if (this.f194a.a(this.e)) {
            if (this.f194a.a(this.c)) {
                this.f194a.b(this.e);
            } else {
                this.f194a.e(this.e, this.c);
            }
        }
        if (this.f194a.a(this.c)) {
            try {
                U();
                T();
                this.n = true;
                return;
            } catch (IOException e) {
                okhttp3.g0.k.k.l().t(5, "DiskLruCache " + this.b + " is corrupt: " + e.getMessage() + ", removing", e);
                try {
                    B();
                    this.o = false;
                    W();
                    this.n = true;
                } catch (Throwable th) {
                    this.o = false;
                    throw th;
                }
            }
        }
        W();
        this.n = true;
    }

    public synchronized boolean Q() {
        return this.o;
    }

    boolean R() {
        int i = this.l;
        return i >= 2000 && i >= this.k.size();
    }

    synchronized void W() {
        if (this.j != null) {
            this.j.close();
        }
        okio.i iVarC = t.c(this.f194a.f(this.d));
        try {
            iVarC.f("libcore.io.DiskLruCache").q(10);
            iVarC.f("1").q(10);
            iVarC.k(this.f).q(10);
            iVarC.k(this.h).q(10);
            iVarC.q(10);
            for (j jVar : this.k.values()) {
                if (jVar.f != null) {
                    iVarC.f("DIRTY").q(32);
                    iVarC.f(jVar.f192a);
                } else {
                    iVarC.f("CLEAN").q(32);
                    iVarC.f(jVar.f192a);
                    jVar.d(iVarC);
                }
                iVarC.q(10);
            }
            iVarC.close();
            if (this.f194a.a(this.c)) {
                this.f194a.e(this.c, this.e);
            }
            this.f194a.e(this.d, this.c);
            this.f194a.b(this.e);
            this.j = S();
            this.m = false;
            this.q = false;
        } catch (Throwable th) {
            iVarC.close();
            throw th;
        }
    }

    public synchronized boolean X(String str) {
        P();
        a();
        a0(str);
        j jVar = (j) this.k.get(str);
        if (jVar == null) {
            return false;
        }
        boolean zY = Y(jVar);
        if (zY && this.i <= this.g) {
            this.p = false;
        }
        return zY;
    }

    boolean Y(j jVar) {
        i iVar = jVar.f;
        if (iVar != null) {
            iVar.c();
        }
        for (int i = 0; i < this.h; i++) {
            this.f194a.b(jVar.c[i]);
            long j = this.i;
            long[] jArr = jVar.b;
            this.i = j - jArr[i];
            jArr[i] = 0;
        }
        this.l++;
        this.j.f("REMOVE").q(32).f(jVar.f192a).q(10);
        this.k.remove(jVar.f192a);
        if (R()) {
            this.s.execute(this.t);
        }
        return true;
    }

    void Z() {
        while (this.i > this.g) {
            Y((j) this.k.values().iterator().next());
        }
        this.p = false;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        if (this.n && !this.o) {
            for (j jVar : (j[]) this.k.values().toArray(new j[this.k.size()])) {
                if (jVar.f != null) {
                    jVar.f.a();
                }
            }
            Z();
            this.j.close();
            this.j = null;
            this.o = true;
            return;
        }
        this.o = true;
    }

    synchronized void e(i iVar, boolean z) {
        j jVar = iVar.f191a;
        if (jVar.f != iVar) {
            throw new IllegalStateException();
        }
        if (z && !jVar.e) {
            for (int i = 0; i < this.h; i++) {
                if (!iVar.b[i]) {
                    iVar.a();
                    throw new IllegalStateException("Newly created entry didn't create value for index " + i);
                }
                if (!this.f194a.a(jVar.d[i])) {
                    iVar.a();
                    return;
                }
            }
        }
        for (int i2 = 0; i2 < this.h; i2++) {
            File file = jVar.d[i2];
            if (!z) {
                this.f194a.b(file);
            } else if (this.f194a.a(file)) {
                File file2 = jVar.c[i2];
                this.f194a.e(file, file2);
                long j = jVar.b[i2];
                long jD = this.f194a.d(file2);
                jVar.b[i2] = jD;
                this.i = (this.i - j) + jD;
            }
        }
        this.l++;
        jVar.f = null;
        if (jVar.e || z) {
            jVar.e = true;
            this.j.f("CLEAN").q(32);
            this.j.f(jVar.f192a);
            jVar.d(this.j);
            this.j.q(10);
            if (z) {
                long j2 = this.r;
                this.r = 1 + j2;
                jVar.g = j2;
            }
        } else {
            this.k.remove(jVar.f192a);
            this.j.f("REMOVE").q(32);
            this.j.f(jVar.f192a);
            this.j.q(10);
        }
        this.j.flush();
        if (this.i > this.g || R()) {
            this.s.execute(this.t);
        }
    }

    @Override // java.io.Flushable
    public synchronized void flush() {
        if (this.n) {
            a();
            Z();
            this.j.flush();
        }
    }
}
