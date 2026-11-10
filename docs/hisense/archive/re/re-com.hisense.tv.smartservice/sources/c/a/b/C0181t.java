package c.a.b;

import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: renamed from: c.a.b.t, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0181t implements Tb {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AtomicLong f1463a = new AtomicLong();

    @Override // c.a.b.Tb
    public void add(long j) {
        this.f1463a.getAndAdd(j);
    }
}
