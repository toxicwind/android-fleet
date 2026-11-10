package okhttp3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class B {
    private Runnable c;
    private ExecutorService d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f156a = 64;
    private int b = 5;
    private final Deque e = new ArrayDeque();
    private final Deque f = new ArrayDeque();
    private final Deque g = new ArrayDeque();

    private void d(Deque deque, Object obj) {
        Runnable runnable;
        synchronized (this) {
            if (!deque.remove(obj)) {
                throw new AssertionError("Call wasn't in-flight!");
            }
            runnable = this.c;
        }
        if (g() || runnable == null) {
            return;
        }
        runnable.run();
    }

    private boolean g() {
        int i;
        boolean z;
        ArrayList arrayList = new ArrayList();
        synchronized (this) {
            Iterator it = this.e.iterator();
            while (it.hasNext()) {
                V v = (V) it.next();
                if (this.f.size() >= this.f156a) {
                    break;
                }
                if (i(v) < this.b) {
                    it.remove();
                    arrayList.add(v);
                    this.f.add(v);
                }
            }
            z = h() > 0;
        }
        int size = arrayList.size();
        for (i = 0; i < size; i++) {
            ((V) arrayList.get(i)).l(c());
        }
        return z;
    }

    private int i(V v) {
        int i = 0;
        for (V v2 : this.f) {
            if (!v2.m().f && v2.n().equals(v.n())) {
                i++;
            }
        }
        return i;
    }

    void a(V v) {
        synchronized (this) {
            this.e.add(v);
        }
        g();
    }

    synchronized void b(W w) {
        this.g.add(w);
    }

    public synchronized ExecutorService c() {
        if (this.d == null) {
            this.d = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue(), okhttp3.g0.e.G("OkHttp Dispatcher", false));
        }
        return this.d;
    }

    void e(V v) {
        d(this.f, v);
    }

    void f(W w) {
        d(this.g, w);
    }

    public synchronized int h() {
        return this.f.size() + this.g.size();
    }
}
