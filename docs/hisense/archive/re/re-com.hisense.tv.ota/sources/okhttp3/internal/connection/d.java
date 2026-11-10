package okhttp3.internal.connection;

import java.util.LinkedHashSet;
import java.util.Set;
import okhttp3.f0;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Set f221a = new LinkedHashSet();

    public synchronized void a(f0 f0Var) {
        this.f221a.remove(f0Var);
    }

    public synchronized void b(f0 f0Var) {
        this.f221a.add(f0Var);
    }

    public synchronized boolean c(f0 f0Var) {
        return this.f221a.contains(f0Var);
    }
}
