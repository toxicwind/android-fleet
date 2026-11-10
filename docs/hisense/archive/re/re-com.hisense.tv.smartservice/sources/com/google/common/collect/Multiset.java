package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.CompatibleWith;
import com.mediatek.twoworlds.tv.model.MtkTvRatingConvert2Goo;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible
public interface Multiset<E> extends Collection<E> {

    public interface Entry<E> {
        boolean equals(Object obj);

        int getCount();

        E getElement();

        int hashCode();

        String toString();
    }

    @CanIgnoreReturnValue
    int add(E e2, int i);

    @CanIgnoreReturnValue
    boolean add(E e2);

    boolean contains(Object obj);

    @Override // java.util.Collection
    boolean containsAll(Collection<?> collection);

    int count(@CompatibleWith(MtkTvRatingConvert2Goo.STR_CA_TV_E) Object obj);

    Set<E> elementSet();

    Set<Entry<E>> entrySet();

    boolean equals(Object obj);

    int hashCode();

    Iterator<E> iterator();

    @CanIgnoreReturnValue
    int remove(@CompatibleWith(MtkTvRatingConvert2Goo.STR_CA_TV_E) Object obj, int i);

    @CanIgnoreReturnValue
    boolean remove(Object obj);

    @CanIgnoreReturnValue
    boolean removeAll(Collection<?> collection);

    @CanIgnoreReturnValue
    boolean retainAll(Collection<?> collection);

    @CanIgnoreReturnValue
    int setCount(E e2, int i);

    @CanIgnoreReturnValue
    boolean setCount(E e2, int i, int i2);

    int size();

    String toString();
}
