package c.a;

import java.util.logging.Logger;

/* JADX INFO: loaded from: classes2.dex */
public final class Fa extends C0236u.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Logger f803a = Logger.getLogger(Fa.class.getName());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final ThreadLocal<C0236u> f804b = new ThreadLocal<>();

    @Override // c.a.C0236u.c
    public C0236u a() {
        C0236u c0236u = f804b.get();
        return c0236u == null ? C0236u.f1912b : c0236u;
    }
}
