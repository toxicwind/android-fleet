package com.ju.lib.datalayer.database.asist;

import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: classes2.dex */
public class CollSpliter {

    public interface Spliter<T> {
        int oneSplit(ArrayList<T> arrayList);
    }

    public static <T> int split(Collection<T> collection, int i, Spliter<T> spliter) {
        ArrayList<T> arrayList = new ArrayList<>();
        int i2 = 0;
        if (collection.size() <= i) {
            arrayList.addAll(collection);
            return spliter.oneSplit(arrayList) + 0;
        }
        int i3 = 1;
        int i4 = 0;
        for (T t : collection) {
            if (i2 < i3 * i) {
                arrayList.add(t);
            } else {
                int iOneSplit = spliter.oneSplit(arrayList) + i4;
                i3++;
                arrayList.clear();
                arrayList.add(t);
                i4 = iOneSplit;
            }
            i2++;
        }
        return arrayList.size() > 0 ? spliter.oneSplit(arrayList) + i4 : i4;
    }
}
