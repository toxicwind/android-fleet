package okhttp3.internal.connection;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import okhttp3.f0;

/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List f222a;
    private int b = 0;

    e(List list) {
        this.f222a = list;
    }

    public List a() {
        return new ArrayList(this.f222a);
    }

    public boolean b() {
        return this.b < this.f222a.size();
    }

    public f0 c() {
        if (!b()) {
            throw new NoSuchElementException();
        }
        List list = this.f222a;
        int i = this.b;
        this.b = i + 1;
        return (f0) list.get(i);
    }
}
