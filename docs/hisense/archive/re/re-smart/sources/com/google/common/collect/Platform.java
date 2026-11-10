package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible(emulated = true)
public final class Platform {
    public static void checkGwtRpcEnabled() {
    }

    public static <T> T[] copy(Object[] objArr, int i, int i2, T[] tArr) {
        return (T[]) Arrays.copyOfRange(objArr, i, i2, tArr.getClass());
    }

    public static <T> T[] newArray(T[] tArr, int i) {
        return (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), i));
    }

    public static <K, V> Map<K, V> newHashMapWithExpectedSize(int i) {
        return new CompactHashMap(i);
    }

    public static <E> Set<E> newHashSetWithExpectedSize(int i) {
        return new CompactHashSet(i);
    }

    public static <K, V> Map<K, V> newLinkedHashMapWithExpectedSize(int i) {
        return new CompactLinkedHashMap(i);
    }

    public static <E> Set<E> newLinkedHashSetWithExpectedSize(int i) {
        return new CompactLinkedHashSet(i);
    }

    public static <E> Set<E> preservesInsertionOrderOnAddsSet() {
        return new CompactHashSet();
    }

    public static <K, V> Map<K, V> preservesInsertionOrderOnPutsMap() {
        return new CompactHashMap();
    }

    public static int reduceExponentIfGwt(int i) {
        return i;
    }

    public static int reduceIterationsIfGwt(int i) {
        return i;
    }

    public static MapMaker tryWeakKeys(MapMaker mapMaker) {
        return mapMaker.weakKeys();
    }
}
