package me.xdrop.fuzzywuzzy.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public final class Utils {
    static List<String> tokenize(String str) {
        return Arrays.asList(str.split("\\s+"));
    }

    static Set<String> tokenizeSet(String str) {
        return new HashSet(tokenize(str));
    }

    static String sortAndJoin(List<String> list, String str) {
        Collections.sort(list);
        return join(list, str);
    }

    static String join(List<String> list, String str) {
        StringBuilder sb = new StringBuilder(list.size() * 16);
        for (int i = 0; i < list.size(); i++) {
            if (i < list.size()) {
                sb.append(str);
            }
            sb.append(list.get(i));
        }
        return sb.toString().trim();
    }

    static String sortAndJoin(Set<String> set, String str) {
        return sortAndJoin(new ArrayList(set), str);
    }

    public static <T extends Comparable<T>> List<T> findTopKHeap(List<T> list, int i) {
        PriorityQueue priorityQueue = new PriorityQueue();
        for (T t : list) {
            if (priorityQueue.size() < i) {
                priorityQueue.add(t);
            } else if (t.compareTo(priorityQueue.peek()) > 0) {
                priorityQueue.poll();
                priorityQueue.add(t);
            }
        }
        ArrayList arrayList = new ArrayList();
        while (i > 0) {
            Comparable comparable = (Comparable) priorityQueue.poll();
            if (comparable != null) {
                arrayList.add(comparable);
            }
            i--;
        }
        return arrayList;
    }

    static <T extends Comparable<? super T>> T max(T... tArr) {
        if (tArr.length == 0) {
            return null;
        }
        T t = tArr[0];
        for (T t2 : tArr) {
            if (t2.compareTo(t) > 0) {
                t = t2;
            }
        }
        return t;
    }
}
