package me.xdrop.fuzzywuzzy.algorithms;

import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
final class SetUtils {
    SetUtils() {
    }

    static <T> Set<T> intersection(Set<T> set, Set<T> set2) {
        HashSet hashSet = new HashSet(set);
        hashSet.retainAll(set2);
        return hashSet;
    }

    static <T> Set<T> difference(Set<T> set, Set<T> set2) {
        HashSet hashSet = new HashSet(set);
        hashSet.removeAll(set2);
        return hashSet;
    }
}
