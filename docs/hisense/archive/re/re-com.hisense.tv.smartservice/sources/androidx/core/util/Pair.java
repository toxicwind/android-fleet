package androidx.core.util;

import androidx.annotation.NonNull;
import b.a.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public class Pair<F, S> {
    public final F first;
    public final S second;

    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    @NonNull
    public static <A, B> Pair<A, B> create(A a2, B b2) {
        return new Pair<>(a2, b2);
    }

    public boolean equals(Object o) {
        if (!(o instanceof Pair)) {
            return false;
        }
        Pair pair = (Pair) o;
        return ObjectsCompat.equals(pair.first, this.first) && ObjectsCompat.equals(pair.second, this.second);
    }

    public int hashCode() {
        F f2 = this.first;
        int iHashCode = f2 == null ? 0 : f2.hashCode();
        S s = this.second;
        return iHashCode ^ (s != null ? s.hashCode() : 0);
    }

    @NonNull
    public String toString() {
        StringBuilder sbA = a.a("Pair{");
        sbA.append(this.first);
        sbA.append(" ");
        return a.a(sbA, this.second, "}");
    }
}
