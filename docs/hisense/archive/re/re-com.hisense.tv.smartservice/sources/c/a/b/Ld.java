package c.a.b;

import com.google.common.annotations.VisibleForTesting;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
public final class Ld {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c.a.Ca[] f1069a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final AtomicBoolean f1070b = new AtomicBoolean(false);

    static {
        new Ld(new c.a.Ca[0]);
    }

    @VisibleForTesting
    public Ld(c.a.Ca[] caArr) {
        this.f1069a = caArr;
    }

    public void a(int i, long j, long j2) {
        for (c.a.Ca ca : this.f1069a) {
            ca.b(i, j, j2);
        }
    }

    public void b(long j) {
        for (c.a.Ca ca : this.f1069a) {
            ca.b(j);
        }
    }

    public void a(long j) {
        for (c.a.Ca ca : this.f1069a) {
            ca.a(j);
        }
    }
}
