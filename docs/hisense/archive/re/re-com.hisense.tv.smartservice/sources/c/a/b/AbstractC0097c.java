package c.a.b;

import c.a.Y;
import com.google.common.base.MoreObjects;

/* JADX INFO: renamed from: c.a.b.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0097c<T extends c.a.Y<T>> extends c.a.Y<T> {
    @Override // c.a.Y
    public c.a.X a() {
        return c().a();
    }

    public abstract c.a.Y<?> c();

    public String toString() {
        return MoreObjects.toStringHelper(this).add("delegate", c()).toString();
    }
}
