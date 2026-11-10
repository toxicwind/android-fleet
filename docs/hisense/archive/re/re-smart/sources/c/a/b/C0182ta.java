package c.a.b;

import c.a.AbstractC0230n;
import c.a.C0219ea;
import c.a.C0220f;
import c.a.C0223ga;
import c.a.C0236u;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Queue;
import java.util.concurrent.Executor;

/* JADX INFO: renamed from: c.a.b.ta, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0182ta implements Cc {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Executor f1466c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final c.a.Ea f1467d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Runnable f1468e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Runnable f1469f;
    public Runnable g;
    public Cc.a h;
    public c.a.za j;
    public c.a.U.h k;
    public long l;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c.a.O f1464a = c.a.O.a((Class<?>) C0182ta.class, (String) null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f1465b = new Object();
    public Collection<a> i = new LinkedHashSet();

    public C0182ta(Executor executor, c.a.Ea ea) {
        this.f1466c = executor;
        this.f1467d = ea;
    }

    @Override // c.a.b.Cc
    public final Runnable a(Cc.a aVar) {
        this.h = aVar;
        this.f1468e = new RunnableC0163pa(this, aVar);
        this.f1469f = new RunnableC0168qa(this, aVar);
        this.g = new RunnableC0172ra(this, aVar);
        return null;
    }

    @Override // c.a.b.Cc
    public final void b(c.a.za zaVar) {
        synchronized (this.f1465b) {
            if (this.j != null) {
                return;
            }
            this.j = zaVar;
            c.a.Ea ea = this.f1467d;
            RunnableC0177sa runnableC0177sa = new RunnableC0177sa(this, zaVar);
            Queue<Runnable> queue = ea.f796b;
            Preconditions.checkNotNull(runnableC0177sa, "runnable is null");
            queue.add(runnableC0177sa);
            if (!c() && this.g != null) {
                this.f1467d.a(this.g);
                this.g = null;
            }
            this.f1467d.a();
        }
    }

    public final boolean c() {
        boolean z;
        synchronized (this.f1465b) {
            z = !this.i.isEmpty();
        }
        return z;
    }

    @Override // c.a.b.O
    public final M a(C0223ga<?, ?> c0223ga, C0219ea c0219ea, C0220f c0220f, AbstractC0230n[] abstractC0230nArr) {
        M ta;
        O oA;
        try {
            Pc pc = new Pc(c0223ga, c0219ea, c0220f);
            c.a.U.h hVar = null;
            long j = -1;
            do {
                synchronized (this.f1465b) {
                    if (this.j != null) {
                        ta = new Ta(this.j, N.a.PROCESSED, abstractC0230nArr);
                    } else if (this.k == null) {
                        ta = a(pc, abstractC0230nArr);
                    } else if (hVar != null && j == this.l) {
                        ta = a(pc, abstractC0230nArr);
                    } else {
                        hVar = this.k;
                        j = this.l;
                        oA = C0149mb.a(hVar.a(pc), c0220f.a());
                    }
                }
                this.f1467d.a();
                return ta;
            } while (oA == null);
            ta = oA.a(pc.f1119c, pc.f1118b, pc.f1117a, abstractC0230nArr);
            this.f1467d.a();
            return ta;
        } catch (Throwable th) {
            this.f1467d.a();
            throw th;
        }
    }

    @VisibleForTesting
    public final int b() {
        int size;
        synchronized (this.f1465b) {
            size = this.i.size();
        }
        return size;
    }

    public final a a(c.a.U.e eVar, AbstractC0230n[] abstractC0230nArr) {
        a aVar = new a(eVar, abstractC0230nArr, null);
        this.i.add(aVar);
        if (b() == 1) {
            this.f1467d.a(this.f1468e);
        }
        return aVar;
    }

    /* JADX INFO: renamed from: c.a.b.ta$a */
    private class a extends Na {
        public final c.a.U.e j;
        public final C0236u k = C0236u.c();
        public final AbstractC0230n[] l;

        public /* synthetic */ a(c.a.U.e eVar, AbstractC0230n[] abstractC0230nArr, RunnableC0163pa runnableC0163pa) {
            this.j = eVar;
            this.l = abstractC0230nArr;
        }

        @Override // c.a.b.Na, c.a.b.M
        public void a(c.a.za zaVar) {
            super.a(zaVar);
            synchronized (C0182ta.this.f1465b) {
                try {
                    if (C0182ta.this.g != null) {
                        boolean zRemove = C0182ta.this.i.remove(this);
                        if (!C0182ta.this.c() && zRemove) {
                            C0182ta.this.f1467d.a(C0182ta.this.f1469f);
                            if (C0182ta.this.j != null) {
                                C0182ta.this.f1467d.a(C0182ta.this.g);
                                C0182ta.this.g = null;
                            }
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            C0182ta.this.f1467d.a();
        }

        @Override // c.a.b.Na
        public void b(c.a.za zaVar) {
            for (AbstractC0230n abstractC0230n : this.l) {
                abstractC0230n.a(zaVar);
            }
        }

        @Override // c.a.b.Na, c.a.b.M
        public void a(C0198wb c0198wb) {
            if (((Pc) this.j).f1117a.a()) {
                c0198wb.f1502a.add("wait_for_ready");
            }
            super.a(c0198wb);
        }
    }

    @Override // c.a.b.Cc
    public final void a(c.a.za zaVar) {
        Collection<a> collection;
        Runnable runnable;
        b(zaVar);
        synchronized (this.f1465b) {
            collection = this.i;
            runnable = this.g;
            this.g = null;
            if (!this.i.isEmpty()) {
                this.i = Collections.emptyList();
            }
        }
        if (runnable != null) {
            for (a aVar : collection) {
                Runnable runnableB = aVar.b(new Ta(zaVar, N.a.REFUSED, aVar.l));
                if (runnableB != null) {
                    runnableB.run();
                }
            }
            c.a.Ea ea = this.f1467d;
            b.a.a.a.a.a(runnable, "runnable is null", ea.f796b, runnable, ea);
        }
    }

    public final void a(c.a.U.h hVar) {
        synchronized (this.f1465b) {
            this.k = hVar;
            this.l++;
            if (hVar != null && c()) {
                ArrayList<a> arrayList = new ArrayList(this.i);
                ArrayList arrayList2 = new ArrayList();
                for (a aVar : arrayList) {
                    c.a.U.d dVarA = hVar.a(aVar.j);
                    C0220f c0220f = ((Pc) aVar.j).f1117a;
                    O oA = C0149mb.a(dVarA, c0220f.a());
                    if (oA != null) {
                        Executor executor = this.f1466c;
                        Executor executor2 = c0220f.f1814c;
                        if (executor2 == null) {
                            executor2 = executor;
                        }
                        C0236u c0236uA = aVar.k.a();
                        try {
                            c.a.U.e eVar = aVar.j;
                            M mA = oA.a(((Pc) eVar).f1119c, ((Pc) eVar).f1118b, ((Pc) eVar).f1117a, aVar.l);
                            aVar.k.a(c0236uA);
                            Runnable runnableB = aVar.b(mA);
                            if (runnableB != null) {
                                executor2.execute(runnableB);
                            }
                            arrayList2.add(aVar);
                        } catch (Throwable th) {
                            aVar.k.a(c0236uA);
                            throw th;
                        }
                    }
                }
                synchronized (this.f1465b) {
                    if (c()) {
                        this.i.removeAll(arrayList2);
                        if (this.i.isEmpty()) {
                            this.i = new LinkedHashSet();
                        }
                        if (!c()) {
                            this.f1467d.a(this.f1469f);
                            if (this.j != null && this.g != null) {
                                this.f1467d.a(this.g);
                                this.g = null;
                            }
                        }
                        this.f1467d.a();
                    }
                }
            }
        }
    }

    @Override // c.a.N
    public c.a.O a() {
        return this.f1464a;
    }
}
