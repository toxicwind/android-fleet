package c.a.b;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;

/* JADX INFO: renamed from: c.a.b.vb, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0193vb<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Set<T> f1493a = Collections.newSetFromMap(new IdentityHashMap());

    public abstract void a();

    public final void a(T t, boolean z) {
        int size = this.f1493a.size();
        if (z) {
            this.f1493a.add(t);
            if (size == 0) {
                a();
                return;
            }
            return;
        }
        if (this.f1493a.remove(t) && size == 1) {
            b();
        }
    }

    public abstract void b();

    public final boolean a(Object... objArr) {
        for (Object obj : objArr) {
            if (this.f1493a.contains(obj)) {
                return true;
            }
        }
        return false;
    }
}
