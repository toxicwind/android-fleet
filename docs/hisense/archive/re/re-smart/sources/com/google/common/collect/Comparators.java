package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import java.util.Comparator;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
@Beta
@GwtCompatible
public final class Comparators {
    public static <T> boolean isInOrder(Iterable<? extends T> iterable, Comparator<T> comparator) {
        if (comparator == null) {
            throw new NullPointerException();
        }
        Iterator<? extends T> it = iterable.iterator();
        if (!it.hasNext()) {
            return true;
        }
        T next = it.next();
        while (it.hasNext()) {
            T next2 = it.next();
            if (comparator.compare(next, next2) > 0) {
                return false;
            }
            next = next2;
        }
        return true;
    }

    public static <T> boolean isInStrictOrder(Iterable<? extends T> iterable, Comparator<T> comparator) {
        if (comparator == null) {
            throw new NullPointerException();
        }
        Iterator<? extends T> it = iterable.iterator();
        if (!it.hasNext()) {
            return true;
        }
        T next = it.next();
        while (it.hasNext()) {
            T next2 = it.next();
            if (comparator.compare(next, next2) >= 0) {
                return false;
            }
            next = next2;
        }
        return true;
    }

    public static <T, S extends T> Comparator<Iterable<S>> lexicographical(Comparator<T> comparator) {
        if (comparator != null) {
            return new LexicographicalOrdering(comparator);
        }
        throw new NullPointerException();
    }

    @Beta
    public static <T extends Comparable<? super T>> T max(T t, T t2) {
        return t.compareTo(t2) >= 0 ? t : t2;
    }

    @Beta
    public static <T extends Comparable<? super T>> T min(T t, T t2) {
        return t.compareTo(t2) <= 0 ? t : t2;
    }

    @Beta
    public static <T> T max(T t, T t2, Comparator<T> comparator) {
        return comparator.compare(t, t2) >= 0 ? t : t2;
    }

    @Beta
    public static <T> T min(T t, T t2, Comparator<T> comparator) {
        return comparator.compare(t, t2) <= 0 ? t : t2;
    }
}
