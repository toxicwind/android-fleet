package c.a.c;

import androidx.core.app.NotificationCompat;
import com.google.common.base.Preconditions;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public class z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final r f1753a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c.a.c.a.a.c f1754b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1755c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final a f1756d;

    private static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1763a;

        public /* synthetic */ b(y yVar) {
        }
    }

    public z(r rVar, c.a.c.a.a.c cVar) {
        Preconditions.checkNotNull(rVar, NotificationCompat.CATEGORY_TRANSPORT);
        this.f1753a = rVar;
        Preconditions.checkNotNull(cVar, "frameWriter");
        this.f1754b = cVar;
        this.f1755c = 65535;
        this.f1756d = new a(0, 65535);
    }

    public boolean a(int i) {
        if (i < 0) {
            throw new IllegalArgumentException(b.a.a.a.a.a("Invalid initial window size: ", i));
        }
        int i2 = i - this.f1755c;
        this.f1755c = i;
        for (k kVar : this.f1753a.b()) {
            a aVar = (a) kVar.m;
            if (aVar == null) {
                kVar.m = new a(this, kVar, this.f1755c);
            } else {
                aVar.a(i2);
            }
        }
        return i2 > 0;
    }

    public void b() {
        k[] kVarArrB = this.f1753a.b();
        int i = this.f1756d.f1759c;
        int length = kVarArrB.length;
        while (true) {
            if (length <= 0 || i <= 0) {
                break;
            }
            int iCeil = (int) Math.ceil(i / length);
            int i2 = 0;
            for (int i3 = 0; i3 < length && i > 0; i3++) {
                k kVar = kVarArrB[i3];
                a aVarA = a(kVar);
                int iMin = Math.min(i, Math.min(aVarA.a(), iCeil));
                if (iMin > 0) {
                    aVarA.f1760d += iMin;
                    i -= iMin;
                }
                if (aVarA.a() > 0) {
                    kVarArrB[i2] = kVar;
                    i2++;
                }
            }
            length = i2;
        }
        b bVar = new b(null);
        for (k kVar2 : this.f1753a.b()) {
            a aVarA2 = a(kVar2);
            aVarA2.a(aVarA2.f1760d, bVar);
            aVarA2.f1760d = 0;
        }
        if ((bVar.f1763a > 0 ? 1 : 0) != 0) {
            a();
        }
    }

    private final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final d.g f1757a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f1758b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1759c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1760d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public k f1761e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f1762f;

        public a(z zVar, k kVar, int i) {
            int i2 = kVar.n;
            z.this = zVar;
            this.f1762f = false;
            this.f1758b = i2;
            this.f1759c = i;
            this.f1757a = new d.g();
            this.f1761e = kVar;
        }

        public int a(int i) {
            if (i <= 0 || Integer.MAX_VALUE - i >= this.f1759c) {
                this.f1759c += i;
                return this.f1759c;
            }
            StringBuilder sbA = b.a.a.a.a.a("Window size overflow for stream: ");
            sbA.append(this.f1758b);
            throw new IllegalArgumentException(sbA.toString());
        }

        public int b() {
            return Math.min(this.f1759c, z.this.f1756d.f1759c);
        }

        public int a() {
            return Math.max(0, Math.min(this.f1759c, (int) this.f1757a.f2102c)) - this.f1760d;
        }

        public a(int i, int i2) {
            this.f1762f = false;
            this.f1758b = i;
            this.f1759c = i2;
            this.f1757a = new d.g();
        }

        public int a(int i, b bVar) {
            int iMin = Math.min(i, b());
            int i2 = 0;
            while (true) {
                if (!(this.f1757a.f2102c > 0) || iMin <= 0) {
                    break;
                }
                long j = iMin;
                d.g gVar = this.f1757a;
                long j2 = gVar.f2102c;
                if (j >= j2) {
                    int i3 = (int) j2;
                    i2 += i3;
                    a(gVar, i3, this.f1762f);
                } else {
                    i2 += iMin;
                    a(gVar, iMin, false);
                }
                bVar.f1763a++;
                iMin = Math.min(i - i2, b());
            }
            return i2;
        }

        public void a(d.g gVar, int i, boolean z) {
            do {
                int iMin = Math.min(i, z.this.f1754b.maxDataLength());
                int i2 = -iMin;
                z.this.f1756d.a(i2);
                a(i2);
                try {
                    z.this.f1754b.data(gVar.f2102c == ((long) iMin) && z, this.f1758b, gVar, iMin);
                    this.f1761e.o.c(iMin);
                    i -= iMin;
                } catch (IOException e2) {
                    throw new RuntimeException(e2);
                }
            } while (i > 0);
        }
    }

    public int a(k kVar, int i) {
        if (kVar == null) {
            int iA = this.f1756d.a(i);
            b();
            return iA;
        }
        a aVarA = a(kVar);
        int iA2 = aVarA.a(i);
        b bVar = new b(null);
        aVarA.a(aVarA.b(), bVar);
        if (bVar.f1763a > 0) {
            a();
        }
        return iA2;
    }

    public void a(boolean z, int i, d.g gVar, boolean z2) {
        Preconditions.checkNotNull(gVar, FirebaseAnalytics.Param.SOURCE);
        k kVarA = this.f1753a.a(i);
        if (kVarA == null) {
            return;
        }
        a aVarA = a(kVarA);
        int iB = aVarA.b();
        boolean z3 = aVarA.f1757a.f2102c > 0;
        int i2 = (int) gVar.f2102c;
        if (!z3 && iB >= i2) {
            aVarA.a(gVar, i2, z);
        } else {
            if (!z3 && iB > 0) {
                aVarA.a(gVar, iB, false);
            }
            aVarA.f1757a.write(gVar, (int) gVar.f2102c);
            aVarA.f1762f = z | aVarA.f1762f;
        }
        if (z2) {
            a();
        }
    }

    public void a() {
        try {
            this.f1754b.flush();
        } catch (IOException e2) {
            throw new RuntimeException(e2);
        }
    }

    public final a a(k kVar) {
        a aVar = (a) kVar.m;
        if (aVar != null) {
            return aVar;
        }
        a aVar2 = new a(this, kVar, this.f1755c);
        kVar.m = aVar2;
        return aVar2;
    }
}
