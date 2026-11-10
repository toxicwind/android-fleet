package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible(emulated = true, serializable = true)
public final class LinkedHashMultiset<E> extends AbstractMapBasedMultiset<E> {
    public LinkedHashMultiset(int i) {
        super(i);
    }

    public static <E> LinkedHashMultiset<E> create() {
        return new LinkedHashMultiset<>(3);
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    public boolean contains(Object obj) {
        return count(obj) > 0;
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ Set elementSet() {
        return super.elementSet();
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ Set entrySet() {
        return super.entrySet();
    }

    @Override // com.google.common.collect.AbstractMapBasedMultiset
    public void init(int i) {
        this.backingMap = new ObjectCountLinkedHashMap(i);
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection
    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    public static <E> LinkedHashMultiset<E> create(int i) {
        return new LinkedHashMultiset<>(i);
    }

    public static <E> LinkedHashMultiset<E> create(Iterable<? extends E> iterable) {
        LinkedHashMultiset<E> linkedHashMultiset = new LinkedHashMultiset<>(Multisets.inferDistinctElements(iterable));
        Iterables.addAll(linkedHashMultiset, iterable);
        return linkedHashMultiset;
    }
}
