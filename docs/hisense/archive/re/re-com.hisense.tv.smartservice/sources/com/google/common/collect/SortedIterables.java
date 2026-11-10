package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Comparator;
import java.util.SortedSet;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible
public final class SortedIterables {
    public static <E> Comparator<? super E> comparator(SortedSet<E> sortedSet) {
        Comparator<? super E> comparator = sortedSet.comparator();
        return comparator == null ? Ordering.natural() : comparator;
    }

    public static boolean hasSameComparator(Comparator<?> comparator, Iterable<?> iterable) {
        Object objComparator;
        if (comparator == null) {
            throw new NullPointerException();
        }
        if (iterable == null) {
            throw new NullPointerException();
        }
        if (iterable instanceof SortedSet) {
            objComparator = ((SortedSet) iterable).comparator();
            if (objComparator == null) {
                objComparator = Ordering.natural();
            }
        } else {
            if (!(iterable instanceof SortedIterable)) {
                return false;
            }
            objComparator = ((SortedIterable) iterable).comparator();
        }
        return comparator.equals(objComparator);
    }
}
