package c.a.b;

import com.google.common.annotations.VisibleForTesting;

/* JADX INFO: loaded from: classes2.dex */
public final class Td {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f1157a = new a(Pd.f1120a);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Pd f1158b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f1159c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f1160d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f1161e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f1162f;
    public long g;
    public final Tb h;

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Pd f1163a;

        @VisibleForTesting
        public a(Pd pd) {
            this.f1163a = pd;
        }

        public Td a() {
            return new Td(this.f1163a, null);
        }
    }

    public interface b {
    }

    public Td() {
        this.h = b.b.a.c.a.a();
        this.f1158b = Pd.f1120a;
    }

    public void a(int i) {
        if (i == 0) {
            return;
        }
        this.g += (long) i;
        ((Od) this.f1158b).a();
    }

    public void a(b bVar) {
        if (bVar == null) {
            throw new NullPointerException();
        }
    }

    public /* synthetic */ Td(Pd pd, Sd sd) {
        this.h = b.b.a.c.a.a();
        this.f1158b = pd;
    }
}
