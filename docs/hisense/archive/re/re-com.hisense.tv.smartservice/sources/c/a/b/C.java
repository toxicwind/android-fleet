package c.a.b;

import java.util.ArrayDeque;

/* JADX INFO: loaded from: classes2.dex */
public class C extends ArrayDeque<c.a.J> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f918a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ D f919b;

    public C(D d2, int i) {
        this.f919b = d2;
        this.f918a = i;
    }

    @Override // java.util.ArrayDeque, java.util.AbstractCollection, java.util.Collection, java.util.Deque, java.util.Queue
    public boolean add(Object obj) {
        c.a.J j = (c.a.J) obj;
        if (size() == this.f918a) {
            removeFirst();
        }
        D.a(this.f919b);
        return super.add(j);
    }
}
