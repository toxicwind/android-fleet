package c.a.b;

import java.io.Closeable;

/* JADX INFO: renamed from: c.a.b.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0132j implements Closeable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Tc f1318a;

    public C0132j(C0147m c0147m, Tc tc) {
        this.f1318a = tc;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f1318a.close();
    }
}
