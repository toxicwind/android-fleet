package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.CompatibleWith;
import com.google.errorprone.annotations.DoNotMock;
import com.mediatek.twoworlds.tv.model.MtkTvRatingConvert2Goo;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
@DoNotMock("Use ImmutableMultimap, HashMultimap, or another implementation")
@GwtCompatible
public interface Multimap<K, V> {
    Map<K, Collection<V>> asMap();

    void clear();

    boolean containsEntry(@CompatibleWith("K") Object obj, @CompatibleWith(MtkTvRatingConvert2Goo.SUB_RATING_STR_V) Object obj2);

    boolean containsKey(@CompatibleWith("K") Object obj);

    boolean containsValue(@CompatibleWith(MtkTvRatingConvert2Goo.SUB_RATING_STR_V) Object obj);

    Collection<Map.Entry<K, V>> entries();

    boolean equals(Object obj);

    Collection<V> get(K k);

    int hashCode();

    boolean isEmpty();

    Set<K> keySet();

    Multiset<K> keys();

    @CanIgnoreReturnValue
    boolean put(K k, V v);

    @CanIgnoreReturnValue
    boolean putAll(Multimap<? extends K, ? extends V> multimap);

    @CanIgnoreReturnValue
    boolean putAll(K k, Iterable<? extends V> iterable);

    @CanIgnoreReturnValue
    boolean remove(@CompatibleWith("K") Object obj, @CompatibleWith(MtkTvRatingConvert2Goo.SUB_RATING_STR_V) Object obj2);

    @CanIgnoreReturnValue
    Collection<V> removeAll(@CompatibleWith("K") Object obj);

    @CanIgnoreReturnValue
    Collection<V> replaceValues(K k, Iterable<? extends V> iterable);

    int size();

    Collection<V> values();
}
