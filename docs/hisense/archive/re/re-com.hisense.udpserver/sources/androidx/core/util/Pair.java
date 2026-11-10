package androidx.core.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import b.a.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public class Pair<F, S> {

    @Nullable
    public final F first;

    @Nullable
    public final S second;

    public Pair(@Nullable F f, @Nullable S s) {
        this.first = f;
        this.second = s;
    }

    @NonNull
    public static <A, B> Pair<A, B> create(@Nullable A a2, @Nullable B b2) {
        return new Pair<>(a2, b2);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Pair)) {
            return false;
        }
        Pair pair = (Pair) obj;
        return ObjectsCompat.equals(pair.first, this.first) && ObjectsCompat.equals(pair.second, this.second);
    }

    public int hashCode() {
        F f = this.first;
        int iHashCode = f == null ? 0 : f.hashCode();
        S s = this.second;
        return iHashCode ^ (s != null ? s.hashCode() : 0);
    }

    public String toString() {
        StringBuilder sbC = a.c("Pair{");
        sbC.append(String.valueOf(this.first));
        sbC.append(" ");
        sbC.append(String.valueOf(this.second));
        sbC.append("}");
        return sbC.toString();
    }
}
