package c.a;

import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* JADX INFO: renamed from: c.a.w, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0238w implements Comparable<C0238w> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f1921a = new a(null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final long f1922b = TimeUnit.DAYS.toNanos(36500);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final long f1923c = -f1922b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final long f1924d = TimeUnit.SECONDS.toNanos(1);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final b f1925e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f1926f;
    public volatile boolean g;

    /* JADX INFO: renamed from: c.a.w$a */
    private static class a extends b {
        public /* synthetic */ a(C0237v c0237v) {
        }

        @Override // c.a.C0238w.b
        public long a() {
            return System.nanoTime();
        }
    }

    /* JADX INFO: renamed from: c.a.w$b */
    public static abstract class b {
        public abstract long a();
    }

    public C0238w(b bVar, long j, boolean z) {
        long jA = bVar.a();
        this.f1925e = bVar;
        long jMin = Math.min(f1922b, Math.max(f1923c, j));
        this.f1926f = jA + jMin;
        this.g = z && jMin <= 0;
    }

    public static C0238w a(long j, TimeUnit timeUnit) {
        a aVar = f1921a;
        if (timeUnit != null) {
            return new C0238w(aVar, timeUnit.toNanos(j), true);
        }
        throw new NullPointerException("units");
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public int compareTo(C0238w c0238w) {
        a(c0238w);
        long j = this.f1926f - c0238w.f1926f;
        if (j < 0) {
            return -1;
        }
        return j > 0 ? 1 : 0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C0238w)) {
            return false;
        }
        C0238w c0238w = (C0238w) obj;
        b bVar = this.f1925e;
        if (bVar != null ? bVar == c0238w.f1925e : c0238w.f1925e == null) {
            return this.f1926f == c0238w.f1926f;
        }
        return false;
    }

    public int hashCode() {
        return Arrays.asList(this.f1925e, Long.valueOf(this.f1926f)).hashCode();
    }

    public String toString() {
        long jA = a(TimeUnit.NANOSECONDS);
        long jAbs = Math.abs(jA) / f1924d;
        long jAbs2 = Math.abs(jA) % f1924d;
        StringBuilder sb = new StringBuilder();
        if (jA < 0) {
            sb.append('-');
        }
        sb.append(jAbs);
        if (jAbs2 > 0) {
            sb.append(String.format(Locale.US, ".%09d", Long.valueOf(jAbs2)));
        }
        sb.append("s from now");
        if (this.f1925e != f1921a) {
            StringBuilder sbA = b.a.a.a.a.a(" (ticker=");
            sbA.append(this.f1925e);
            sbA.append(")");
            sb.append(sbA.toString());
        }
        return sb.toString();
    }

    public boolean a() {
        if (!this.g) {
            if (this.f1926f - this.f1925e.a() > 0) {
                return false;
            }
            this.g = true;
        }
        return true;
    }

    public long a(TimeUnit timeUnit) {
        long jA = this.f1925e.a();
        if (!this.g && this.f1926f - jA <= 0) {
            this.g = true;
        }
        return timeUnit.convert(this.f1926f - jA, TimeUnit.NANOSECONDS);
    }

    public final void a(C0238w c0238w) {
        if (this.f1925e == c0238w.f1925e) {
            return;
        }
        StringBuilder sbA = b.a.a.a.a.a("Tickers (");
        sbA.append(this.f1925e);
        sbA.append(" and ");
        throw new AssertionError(b.a.a.a.a.a(sbA, c0238w.f1925e, ") don't match. Custom Ticker should only be used in tests!"));
    }
}
