package c.a.f;

import androidx.core.app.NotificationCompat;
import c.a.B;
import c.a.C0086b;
import c.a.C0235t;
import c.a.EnumC0234s;
import c.a.U;
import c.a.b.C0130ic;
import c.a.za;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: loaded from: classes2.dex */
public final class b extends U {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @VisibleForTesting
    public static final C0086b.C0007b<c<C0235t>> f1822b = new C0086b.C0007b<>("state-info");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final za f1823c = za.f1936c.b("no subchannels ready");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final U.c f1824d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Random f1826f;
    public EnumC0234s g;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Map<B, U.g> f1825e = new HashMap();
    public d h = new a(f1823c);

    @VisibleForTesting
    static final class c<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public T f1831a;

        public c(T t) {
            this.f1831a = t;
        }
    }

    private static abstract class d extends U.h {
        public /* synthetic */ d(c.a.f.a aVar) {
        }

        public abstract boolean a(d dVar);
    }

    public b(U.c cVar) {
        Preconditions.checkNotNull(cVar, "helper");
        this.f1824d = cVar;
        this.f1826f = new Random();
    }

    @Override // c.a.U
    public void a(za zaVar) {
        if (this.g != EnumC0234s.READY) {
            a(EnumC0234s.TRANSIENT_FAILURE, new a(zaVar));
        }
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [T, c.a.t] */
    @Override // c.a.U
    public void c() {
        for (U.g gVar : d()) {
            gVar.c();
            a(gVar).f1831a = C0235t.a(EnumC0234s.SHUTDOWN);
        }
        this.f1825e.clear();
    }

    @VisibleForTesting
    public Collection<U.g> d() {
        return this.f1825e.values();
    }

    public final void e() {
        boolean z;
        Collection<U.g> collectionD = d();
        ArrayList arrayList = new ArrayList(collectionD.size());
        Iterator<U.g> it = collectionD.iterator();
        while (true) {
            z = false;
            if (!it.hasNext()) {
                break;
            }
            U.g next = it.next();
            if (a(next).f1831a.f1906a == EnumC0234s.READY) {
                arrayList.add(next);
            }
        }
        if (!arrayList.isEmpty()) {
            a(EnumC0234s.READY, new C0012b(arrayList, this.f1826f.nextInt(arrayList.size())));
            return;
        }
        za zaVar = f1823c;
        Iterator<U.g> it2 = d().iterator();
        while (it2.hasNext()) {
            C0235t c0235t = a(it2.next()).f1831a;
            EnumC0234s enumC0234s = c0235t.f1906a;
            if (enumC0234s == EnumC0234s.CONNECTING || enumC0234s == EnumC0234s.IDLE) {
                z = true;
            }
            if (zaVar == f1823c || !zaVar.c()) {
                zaVar = c0235t.f1907b;
            }
        }
        a(z ? EnumC0234s.CONNECTING : EnumC0234s.TRANSIENT_FAILURE, new a(zaVar));
    }

    @VisibleForTesting
    static final class a extends d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final za f1827a;

        public a(za zaVar) {
            super(null);
            Preconditions.checkNotNull(zaVar, NotificationCompat.CATEGORY_STATUS);
            this.f1827a = zaVar;
        }

        @Override // c.a.U.h
        public U.d a(U.e eVar) {
            return this.f1827a.c() ? U.d.f848a : U.d.b(this.f1827a);
        }

        public String toString() {
            return MoreObjects.toStringHelper((Class<?>) a.class).add(NotificationCompat.CATEGORY_STATUS, this.f1827a).toString();
        }

        @Override // c.a.f.b.d
        public boolean a(d dVar) {
            if (dVar instanceof a) {
                a aVar = (a) dVar;
                if (Objects.equal(this.f1827a, aVar.f1827a) || (this.f1827a.c() && aVar.f1827a.c())) {
                    return true;
                }
            }
            return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void a(b bVar, U.g gVar, C0235t c0235t) {
        if (bVar.f1825e.get(a(gVar.a())) != gVar) {
            return;
        }
        EnumC0234s enumC0234s = c0235t.f1906a;
        if (enumC0234s == EnumC0234s.TRANSIENT_FAILURE || enumC0234s == EnumC0234s.IDLE) {
            bVar.f1824d.a();
        }
        if (c0235t.f1906a == EnumC0234s.IDLE) {
            gVar.b();
        }
        c<C0235t> cVarA = a(gVar);
        if (cVarA.f1831a.f1906a.equals(EnumC0234s.TRANSIENT_FAILURE) && (c0235t.f1906a.equals(EnumC0234s.CONNECTING) || c0235t.f1906a.equals(EnumC0234s.IDLE))) {
            return;
        }
        cVarA.f1831a = c0235t;
        bVar.e();
    }

    /* JADX INFO: renamed from: c.a.f.b$b, reason: collision with other inner class name */
    @VisibleForTesting
    static final class C0012b extends d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AtomicIntegerFieldUpdater<C0012b> f1828a = AtomicIntegerFieldUpdater.newUpdater(C0012b.class, "c");

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final List<U.g> f1829b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public volatile int f1830c;

        public C0012b(List<U.g> list, int i) {
            super(null);
            Preconditions.checkArgument(!list.isEmpty(), "empty list");
            this.f1829b = list;
            this.f1830c = i - 1;
        }

        @Override // c.a.U.h
        public U.d a(U.e eVar) {
            int i;
            int size = this.f1829b.size();
            int iIncrementAndGet = f1828a.incrementAndGet(this);
            if (iIncrementAndGet >= size) {
                i = iIncrementAndGet % size;
                f1828a.compareAndSet(this, iIncrementAndGet, i);
            } else {
                i = iIncrementAndGet;
            }
            return U.d.a(this.f1829b.get(i));
        }

        public String toString() {
            return MoreObjects.toStringHelper((Class<?>) C0012b.class).add("list", this.f1829b).toString();
        }

        @Override // c.a.f.b.d
        public boolean a(d dVar) {
            if (!(dVar instanceof C0012b)) {
                return false;
            }
            C0012b c0012b = (C0012b) dVar;
            return c0012b == this || (this.f1829b.size() == c0012b.f1829b.size() && new HashSet(this.f1829b).containsAll(c0012b.f1829b));
        }
    }

    public final void a(EnumC0234s enumC0234s, d dVar) {
        if (enumC0234s == this.g && dVar.a(this.h)) {
            return;
        }
        this.f1824d.a(enumC0234s, dVar);
        this.g = enumC0234s;
        this.h = dVar;
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [T, c.a.t] */
    @Override // c.a.U
    public void a(U.f fVar) {
        List<B> list = fVar.f853a;
        Set<B> setKeySet = this.f1825e.keySet();
        HashMap map = new HashMap(list.size() * 2);
        for (B b2 : list) {
            map.put(new B(b2.f783b, C0086b.f876a), b2);
        }
        Set setKeySet2 = map.keySet();
        HashSet hashSet = new HashSet(setKeySet);
        hashSet.removeAll(setKeySet2);
        for (Map.Entry entry : map.entrySet()) {
            B b3 = (B) entry.getKey();
            B b4 = (B) entry.getValue();
            U.g gVar = this.f1825e.get(b3);
            if (gVar != null) {
                gVar.a(Collections.singletonList(b4));
            } else {
                C0086b.a aVarA = C0086b.a();
                aVarA.a(f1822b, new c(C0235t.a(EnumC0234s.IDLE)));
                U.c cVar = this.f1824d;
                C0086b c0086b = C0086b.f876a;
                Object[][] objArr = (Object[][]) Array.newInstance((Class<?>) Object.class, 0, 2);
                List listSingletonList = Collections.singletonList(b4);
                C0086b c0086bA = aVarA.a();
                Preconditions.checkNotNull(c0086bA, "attrs");
                U.g gVarA = cVar.a(new U.a(listSingletonList, c0086bA, objArr, null));
                Preconditions.checkNotNull(gVarA, "subchannel");
                gVarA.a(new c.a.f.a(this, gVarA));
                this.f1825e.put(b3, gVarA);
                gVarA.b();
            }
        }
        ArrayList<U.g> arrayList = new ArrayList();
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            arrayList.add(this.f1825e.remove((B) it.next()));
        }
        e();
        for (U.g gVar2 : arrayList) {
            gVar2.c();
            a(gVar2).f1831a = C0235t.a(EnumC0234s.SHUTDOWN);
        }
    }

    public static B a(B b2) {
        return new B(b2.f783b, C0086b.f876a);
    }

    public static c<C0235t> a(U.g gVar) {
        Object objA = ((C0130ic.m) gVar).f1307a.f846b.a(f1822b);
        Preconditions.checkNotNull(objA, "STATE_INFO");
        return (c) objA;
    }
}
