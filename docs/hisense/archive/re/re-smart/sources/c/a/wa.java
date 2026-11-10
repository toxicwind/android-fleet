package c.a;

import java.util.Comparator;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: loaded from: classes2.dex */
public class wa<T> implements Comparator<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ xa f1927a;

    public wa(xa xaVar) {
        this.f1927a = xaVar;
    }

    @Override // java.util.Comparator
    public int compare(T t, T t2) {
        int iB = this.f1927a.b(t) - this.f1927a.b(t2);
        return iB != 0 ? iB : t.getClass().getName().compareTo(t2.getClass().getName());
    }
}
