package com.google.android.gms.common.data;

import com.mediatek.twoworlds.tv.TVCallback;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class FreezableUtils {
    public static <T, E extends Freezable<T>> ArrayList<T> freeze(ArrayList<E> arrayList) {
        TVCallback.CallbackList callbackList = (ArrayList<T>) new ArrayList(arrayList.size());
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            callbackList.add(arrayList.get(i).freeze());
        }
        return callbackList;
    }

    public static <T, E extends Freezable<T>> ArrayList<T> freezeIterable(Iterable<E> iterable) {
        TVCallback.CallbackList callbackList = (ArrayList<T>) new ArrayList();
        Iterator<E> it = iterable.iterator();
        while (it.hasNext()) {
            callbackList.add(it.next().freeze());
        }
        return callbackList;
    }

    public static <T, E extends Freezable<T>> ArrayList<T> freeze(E[] eArr) {
        TVCallback.CallbackList callbackList = (ArrayList<T>) new ArrayList(eArr.length);
        for (E e2 : eArr) {
            callbackList.add(e2.freeze());
        }
        return callbackList;
    }
}
