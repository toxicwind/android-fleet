package com.google.firebase.database.collection;

import java.lang.Comparable;
import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
public class StandardComparator<A extends Comparable<A>> implements Comparator<A> {
    public static StandardComparator INSTANCE = new StandardComparator();

    public static <T extends Comparable<T>> StandardComparator<T> getComparator(Class<T> cls) {
        return INSTANCE;
    }

    @Override // java.util.Comparator
    public int compare(A a2, A a3) {
        return a2.compareTo(a3);
    }
}
