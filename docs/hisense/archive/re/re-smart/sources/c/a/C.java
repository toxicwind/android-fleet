package c.a;

import c.a.C;
import com.google.common.base.MoreObjects;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public abstract class C<T extends C<T>> extends Y<T> {
    @Override // c.a.Y
    public Y a(long j, TimeUnit timeUnit) {
        ((c.a.a.d) this).f866a.a(j, timeUnit);
        c();
        return this;
    }

    @Override // c.a.Y
    public Y b() {
        ((c.a.a.d) this).f866a.b();
        c();
        return this;
    }

    public final T c() {
        return this;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("delegate", ((c.a.a.d) this).f866a).toString();
    }
}
