package c.a.b;

import java.util.ArrayList;

/* JADX INFO: renamed from: c.a.b.wb, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0198wb {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList<String> f1502a = new ArrayList<>();

    public C0198wb a(Object obj) {
        this.f1502a.add(String.valueOf(obj));
        return this;
    }

    public String toString() {
        return this.f1502a.toString();
    }

    public C0198wb a(String str, Object obj) {
        this.f1502a.add(str + "=" + obj);
        return this;
    }
}
