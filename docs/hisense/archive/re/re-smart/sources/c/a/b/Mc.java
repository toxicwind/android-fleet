package c.a.b;

import c.a.C0086b;
import c.a.C0235t;
import c.a.EnumC0234s;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
public final class Mc extends c.a.U {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c.a.U.c f1076b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c.a.U.g f1077c;

    private static final class a extends c.a.U.h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final c.a.U.d f1078a;

        public a(c.a.U.d dVar) {
            Preconditions.checkNotNull(dVar, "result");
            this.f1078a = dVar;
        }

        @Override // c.a.U.h
        public c.a.U.d a(c.a.U.e eVar) {
            return this.f1078a;
        }

        public String toString() {
            return MoreObjects.toStringHelper((Class<?>) a.class).add("result", this.f1078a).toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    final class b extends c.a.U.h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final c.a.U.g f1079a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final AtomicBoolean f1080b = new AtomicBoolean(false);

        public b(c.a.U.g gVar) {
            Preconditions.checkNotNull(gVar, "subchannel");
            this.f1079a = gVar;
        }

        @Override // c.a.U.h
        public c.a.U.d a(c.a.U.e eVar) {
            if (this.f1080b.compareAndSet(false, true)) {
                c.a.Ea ea = C0130ic.this.w;
                Nc nc = new Nc(this);
                b.a.a.a.a.a(nc, "runnable is null", ea.f796b, nc, ea);
            }
            return c.a.U.d.f848a;
        }
    }

    public Mc(c.a.U.c cVar) {
        Preconditions.checkNotNull(cVar, "helper");
        this.f1076b = cVar;
    }

    @Override // c.a.U
    public void b() {
        c.a.U.g gVar = this.f1077c;
        if (gVar != null) {
            gVar.b();
        }
    }

    @Override // c.a.U
    public void c() {
        c.a.U.g gVar = this.f1077c;
        if (gVar != null) {
            gVar.c();
        }
    }

    public final void a(c.a.U.g gVar, C0235t c0235t) {
        c.a.U.h aVar;
        EnumC0234s enumC0234s = c0235t.f1906a;
        if (enumC0234s == EnumC0234s.SHUTDOWN) {
            return;
        }
        if (enumC0234s == EnumC0234s.TRANSIENT_FAILURE || enumC0234s == EnumC0234s.IDLE) {
            this.f1076b.a();
        }
        int iOrdinal = enumC0234s.ordinal();
        if (iOrdinal == 0) {
            aVar = new a(c.a.U.d.f848a);
        } else if (iOrdinal == 1) {
            aVar = new a(c.a.U.d.a(gVar));
        } else if (iOrdinal == 2) {
            aVar = new a(c.a.U.d.b(c0235t.f1907b));
        } else if (iOrdinal == 3) {
            aVar = new b(gVar);
        } else {
            throw new IllegalArgumentException(b.a.a.a.a.a("Unsupported state:", enumC0234s));
        }
        this.f1076b.a(enumC0234s, aVar);
    }

    @Override // c.a.U
    public void a(c.a.za zaVar) {
        c.a.U.g gVar = this.f1077c;
        if (gVar != null) {
            gVar.c();
            this.f1077c = null;
        }
        this.f1076b.a(EnumC0234s.TRANSIENT_FAILURE, new a(c.a.U.d.b(zaVar)));
    }

    @Override // c.a.U
    public void a(c.a.U.f fVar) {
        List<c.a.B> list = fVar.f853a;
        c.a.U.g gVar = this.f1077c;
        if (gVar == null) {
            c.a.U.c cVar = this.f1076b;
            C0086b c0086b = C0086b.f876a;
            Object[][] objArr = (Object[][]) Array.newInstance((Class<?>) Object.class, 0, 2);
            Preconditions.checkArgument(!list.isEmpty(), "addrs is empty");
            c.a.U.g gVarA = cVar.a(new c.a.U.a(Collections.unmodifiableList(new ArrayList(list)), c0086b, objArr, null));
            gVarA.a(new Lc(this, gVarA));
            this.f1077c = gVarA;
            this.f1076b.a(EnumC0234s.CONNECTING, new a(c.a.U.d.a(gVarA)));
            gVarA.b();
            return;
        }
        gVar.a(list);
    }
}
