package com.google.common.collect;

import b.a.a.a.a;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Converter;
import com.google.common.base.Equivalence;
import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.j2objc.annotations.RetainedWith;
import com.google.j2objc.annotations.Weak;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Properties;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible(emulated = true)
public final class Maps {

    /* JADX INFO: Add missing generic type declarations: [V, K] */
    /* JADX INFO: renamed from: com.google.common.collect.Maps$1, reason: invalid class name */
    public class AnonymousClass1<K, V> extends TransformedIterator<Map.Entry<K, V>, K> {
        public AnonymousClass1(Iterator it) {
            super(it);
        }

        @Override // com.google.common.collect.TransformedIterator
        public K transform(Map.Entry<K, V> entry) {
            return entry.getKey();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [V, K] */
    /* JADX INFO: renamed from: com.google.common.collect.Maps$2, reason: invalid class name */
    public class AnonymousClass2<K, V> extends TransformedIterator<Map.Entry<K, V>, V> {
        public AnonymousClass2(Iterator it) {
            super(it);
        }

        @Override // com.google.common.collect.TransformedIterator
        public V transform(Map.Entry<K, V> entry) {
            return entry.getValue();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [V, K] */
    /* JADX INFO: renamed from: com.google.common.collect.Maps$7, reason: invalid class name */
    public class AnonymousClass7<K, V> extends AbstractMapEntry<K, V> {
        public final /* synthetic */ Map.Entry val$entry;

        public AnonymousClass7(Map.Entry entry) {
            this.val$entry = entry;
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public K getKey() {
            return (K) this.val$entry.getKey();
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public V getValue() {
            return (V) this.val$entry.getValue();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [V, K] */
    /* JADX INFO: renamed from: com.google.common.collect.Maps$8, reason: invalid class name */
    public class AnonymousClass8<K, V> extends UnmodifiableIterator<Map.Entry<K, V>> {
        public final /* synthetic */ Iterator val$entryIterator;

        public AnonymousClass8(Iterator it) {
            this.val$entryIterator = it;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.val$entryIterator.hasNext();
        }

        @Override // java.util.Iterator
        public Map.Entry<K, V> next() {
            return Maps.unmodifiableEntry((Map.Entry) this.val$entryIterator.next());
        }
    }

    /* JADX INFO: Add missing generic type declarations: [K, V1, V2] */
    /* JADX INFO: renamed from: com.google.common.collect.Maps$9, reason: invalid class name */
    public class AnonymousClass9<K, V1, V2> implements EntryTransformer<K, V1, V2> {
        public final /* synthetic */ Function val$function;

        public AnonymousClass9(Function function) {
            this.val$function = function;
        }

        @Override // com.google.common.collect.Maps.EntryTransformer
        public V2 transformEntry(K k, V1 v1) {
            return (V2) this.val$function.apply(v1);
        }
    }

    private static abstract class AbstractFilteredMap<K, V> extends ViewCachingAbstractMap<K, V> {
        public final Predicate<? super Map.Entry<K, V>> predicate;
        public final Map<K, V> unfiltered;

        public AbstractFilteredMap(Map<K, V> map, Predicate<? super Map.Entry<K, V>> predicate) {
            this.unfiltered = map;
            this.predicate = predicate;
        }

        public boolean apply(Object obj, V v) {
            return this.predicate.apply(new ImmutableEntry(obj, v));
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object obj) {
            return this.unfiltered.containsKey(obj) && apply(obj, this.unfiltered.get(obj));
        }

        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap
        public Collection<V> createValues() {
            return new FilteredMapValues(this, this.unfiltered, this.predicate);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V get(Object obj) {
            V v = this.unfiltered.get(obj);
            if (v == null || !apply(obj, v)) {
                return null;
            }
            return v;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean isEmpty() {
            return entrySet().isEmpty();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V put(K k, V v) {
            Preconditions.checkArgument(apply(k, v));
            return this.unfiltered.put(k, v);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public void putAll(Map<? extends K, ? extends V> map) {
            for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
                Preconditions.checkArgument(apply(entry.getKey(), entry.getValue()));
            }
            this.unfiltered.putAll(map);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V remove(Object obj) {
            if (containsKey(obj)) {
                return this.unfiltered.remove(obj);
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static class AsMapView<K, V> extends ViewCachingAbstractMap<K, V> {
        public final Function<? super K, V> function;
        public final Set<K> set;

        public AsMapView(Set<K> set, Function<? super K, V> function) {
            if (set == null) {
                throw new NullPointerException();
            }
            this.set = set;
            if (function == null) {
                throw new NullPointerException();
            }
            this.function = function;
        }

        public Set<K> backingSet() {
            return this.set;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public void clear() {
            backingSet().clear();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object obj) {
            return backingSet().contains(obj);
        }

        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap
        public Set<Map.Entry<K, V>> createEntrySet() {
            return new EntrySet<K, V>() { // from class: com.google.common.collect.Maps.AsMapView.1EntrySetImpl
                @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
                public Iterator<Map.Entry<K, V>> iterator() {
                    return Maps.asMapEntryIterator(AsMapView.this.backingSet(), AsMapView.this.function);
                }

                @Override // com.google.common.collect.Maps.EntrySet
                public Map<K, V> map() {
                    return AsMapView.this;
                }
            };
        }

        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap
        public Set<K> createKeySet() {
            return new AnonymousClass4(backingSet());
        }

        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap
        public Collection<V> createValues() {
            return new Collections2.TransformedCollection(this.set, this.function);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V get(Object obj) {
            if (Collections2.safeContains(backingSet(), obj)) {
                return this.function.apply(obj);
            }
            return null;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V remove(Object obj) {
            if (backingSet().remove(obj)) {
                return this.function.apply(obj);
            }
            return null;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int size() {
            return backingSet().size();
        }
    }

    private static final class BiMapConverter<A, B> extends Converter<A, B> implements Serializable {
        public static final long serialVersionUID = 0;
        public final BiMap<A, B> bimap;

        public BiMapConverter(BiMap<A, B> biMap) {
            if (biMap == null) {
                throw new NullPointerException();
            }
            this.bimap = biMap;
        }

        public static <X, Y> Y convert(BiMap<X, Y> biMap, X x) {
            Y y = biMap.get(x);
            Preconditions.checkArgument(y != null, "No non-null mapping present for input: %s", x);
            return y;
        }

        @Override // com.google.common.base.Converter
        public A doBackward(B b2) {
            return (A) convert(this.bimap.inverse(), b2);
        }

        @Override // com.google.common.base.Converter
        public B doForward(A a2) {
            return (B) convert(this.bimap, a2);
        }

        @Override // com.google.common.base.Converter, com.google.common.base.Function
        public boolean equals(Object obj) {
            if (obj instanceof BiMapConverter) {
                return this.bimap.equals(((BiMapConverter) obj).bimap);
            }
            return false;
        }

        public int hashCode() {
            return this.bimap.hashCode();
        }

        public String toString() {
            String strValueOf = String.valueOf(this.bimap);
            return a.a(strValueOf.length() + 18, "Maps.asConverter(", strValueOf, ")");
        }
    }

    @GwtIncompatible
    static abstract class DescendingMap<K, V> extends ForwardingMap<K, V> implements NavigableMap<K, V> {
        public transient Comparator<? super K> comparator;
        public transient Set<Map.Entry<K, V>> entrySet;
        public transient NavigableSet<K> navigableKeySet;

        public static <T> Ordering<T> reverse(Comparator<T> comparator) {
            return Ordering.from(comparator).reverse();
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> ceilingEntry(K k) {
            return forward().floorEntry(k);
        }

        @Override // java.util.NavigableMap
        public K ceilingKey(K k) {
            return forward().floorKey(k);
        }

        @Override // java.util.SortedMap
        public Comparator<? super K> comparator() {
            Comparator<? super K> comparator = this.comparator;
            if (comparator != null) {
                return comparator;
            }
            Comparator<? super K> comparator2 = forward().comparator();
            if (comparator2 == null) {
                comparator2 = Ordering.natural();
            }
            Ordering orderingReverse = reverse(comparator2);
            this.comparator = orderingReverse;
            return orderingReverse;
        }

        public Set<Map.Entry<K, V>> createEntrySet() {
            return new EntrySet<K, V>() { // from class: com.google.common.collect.Maps.DescendingMap.1EntrySetImpl
                @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
                public Iterator<Map.Entry<K, V>> iterator() {
                    return DescendingMap.this.entryIterator();
                }

                @Override // com.google.common.collect.Maps.EntrySet
                public Map<K, V> map() {
                    return DescendingMap.this;
                }
            };
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> descendingKeySet() {
            return forward().navigableKeySet();
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> descendingMap() {
            return forward();
        }

        public abstract Iterator<Map.Entry<K, V>> entryIterator();

        @Override // com.google.common.collect.ForwardingMap, java.util.Map
        public Set<Map.Entry<K, V>> entrySet() {
            Set<Map.Entry<K, V>> set = this.entrySet;
            if (set != null) {
                return set;
            }
            Set<Map.Entry<K, V>> setCreateEntrySet = createEntrySet();
            this.entrySet = setCreateEntrySet;
            return setCreateEntrySet;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> firstEntry() {
            return forward().lastEntry();
        }

        @Override // java.util.SortedMap
        public K firstKey() {
            return forward().lastKey();
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> floorEntry(K k) {
            return forward().ceilingEntry(k);
        }

        @Override // java.util.NavigableMap
        public K floorKey(K k) {
            return forward().ceilingKey(k);
        }

        public abstract NavigableMap<K, V> forward();

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> headMap(K k, boolean z) {
            return forward().tailMap(k, z).descendingMap();
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> higherEntry(K k) {
            return forward().lowerEntry(k);
        }

        @Override // java.util.NavigableMap
        public K higherKey(K k) {
            return forward().lowerKey(k);
        }

        @Override // com.google.common.collect.ForwardingMap, java.util.Map
        public Set<K> keySet() {
            return navigableKeySet();
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> lastEntry() {
            return forward().firstEntry();
        }

        @Override // java.util.SortedMap
        public K lastKey() {
            return forward().firstKey();
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> lowerEntry(K k) {
            return forward().higherEntry(k);
        }

        @Override // java.util.NavigableMap
        public K lowerKey(K k) {
            return forward().higherKey(k);
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> navigableKeySet() {
            NavigableSet<K> navigableSet = this.navigableKeySet;
            if (navigableSet != null) {
                return navigableSet;
            }
            NavigableKeySet navigableKeySet = new NavigableKeySet(this);
            this.navigableKeySet = navigableKeySet;
            return navigableKeySet;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> pollFirstEntry() {
            return forward().pollLastEntry();
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> pollLastEntry() {
            return forward().pollFirstEntry();
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> subMap(K k, boolean z, K k2, boolean z2) {
            return forward().subMap(k2, z2, k, z).descendingMap();
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> tailMap(K k, boolean z) {
            return forward().headMap(k, z).descendingMap();
        }

        @Override // com.google.common.collect.ForwardingObject
        public String toString() {
            return standardToString();
        }

        @Override // com.google.common.collect.ForwardingMap, java.util.Map
        public Collection<V> values() {
            return new Values(this);
        }

        @Override // com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingObject
        public final Map<K, V> delegate() {
            return forward();
        }

        @Override // java.util.NavigableMap, java.util.SortedMap
        public SortedMap<K, V> headMap(K k) {
            return headMap(k, false);
        }

        @Override // java.util.NavigableMap, java.util.SortedMap
        public SortedMap<K, V> subMap(K k, K k2) {
            return subMap(k, true, k2, false);
        }

        @Override // java.util.NavigableMap, java.util.SortedMap
        public SortedMap<K, V> tailMap(K k) {
            return tailMap(k, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    enum EntryFunction implements Function<Map.Entry<?, ?>, Object> {
        KEY { // from class: com.google.common.collect.Maps.EntryFunction.1
            @Override // com.google.common.base.Function
            public Object apply(Map.Entry<?, ?> entry) {
                return entry.getKey();
            }
        },
        VALUE { // from class: com.google.common.collect.Maps.EntryFunction.2
            @Override // com.google.common.base.Function
            public Object apply(Map.Entry<?, ?> entry) {
                return entry.getValue();
            }
        };

        /* synthetic */ EntryFunction(AnonymousClass1 anonymousClass1) {
        }
    }

    static abstract class EntrySet<K, V> extends Sets.ImprovedAbstractSet<Map.Entry<K, V>> {
        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            map().clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object objSafeGet = Maps.safeGet(map(), key);
            if (Objects.equal(objSafeGet, entry.getValue())) {
                return objSafeGet != null || map().containsKey(key);
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return map().isEmpty();
        }

        public abstract Map<K, V> map();

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            if (contains(obj)) {
                return map().keySet().remove(((Map.Entry) obj).getKey());
            }
            return false;
        }

        @Override // com.google.common.collect.Sets.ImprovedAbstractSet, java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean removeAll(Collection<?> collection) {
            try {
                if (collection != null) {
                    return Sets.removeAllImpl(this, collection);
                }
                throw new NullPointerException();
            } catch (UnsupportedOperationException unused) {
                return Sets.removeAllImpl(this, collection.iterator());
            }
        }

        @Override // com.google.common.collect.Sets.ImprovedAbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean retainAll(Collection<?> collection) {
            try {
                if (collection != null) {
                    return super.retainAll(collection);
                }
                throw new NullPointerException();
            } catch (UnsupportedOperationException unused) {
                HashSet hashSetNewHashSetWithExpectedSize = Sets.newHashSetWithExpectedSize(collection.size());
                for (Object obj : collection) {
                    if (contains(obj)) {
                        hashSetNewHashSetWithExpectedSize.add(((Map.Entry) obj).getKey());
                    }
                }
                return map().keySet().retainAll(hashSetNewHashSetWithExpectedSize);
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return map().size();
        }
    }

    public interface EntryTransformer<K, V1, V2> {
        V2 transformEntry(K k, V1 v1);
    }

    static final class FilteredEntryBiMap<K, V> extends FilteredEntryMap<K, V> implements BiMap<K, V> {

        @RetainedWith
        public final BiMap<V, K> inverse;

        /* JADX INFO: renamed from: com.google.common.collect.Maps$FilteredEntryBiMap$1, reason: invalid class name */
        public class AnonymousClass1 implements Predicate<Map.Entry<V, K>> {
            public final /* synthetic */ Predicate val$forwardPredicate;

            public AnonymousClass1(Predicate predicate) {
                this.val$forwardPredicate = predicate;
            }

            @Override // com.google.common.base.Predicate
            public boolean apply(Map.Entry<V, K> entry) {
                return this.val$forwardPredicate.apply(new ImmutableEntry(entry.getValue(), entry.getKey()));
            }
        }

        public FilteredEntryBiMap(BiMap<K, V> biMap, Predicate<? super Map.Entry<K, V>> predicate) {
            super(biMap, predicate);
            FilteredEntryBiMap filteredEntryBiMap = new FilteredEntryBiMap((Map) biMap.inverse(), (Predicate) new AnonymousClass1(predicate));
            filteredEntryBiMap.inverse = this;
            this.inverse = filteredEntryBiMap;
        }

        public static <K, V> Predicate<Map.Entry<V, K>> inversePredicate(Predicate<? super Map.Entry<K, V>> predicate) {
            return new AnonymousClass1(predicate);
        }

        @Override // com.google.common.collect.BiMap
        public V forcePut(K k, V v) {
            Preconditions.checkArgument(apply(k, v));
            return unfiltered().forcePut(k, v);
        }

        @Override // com.google.common.collect.BiMap
        public BiMap<V, K> inverse() {
            return this.inverse;
        }

        public BiMap<K, V> unfiltered() {
            return (BiMap) this.unfiltered;
        }

        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap, java.util.AbstractMap, java.util.Map
        public Set<V> values() {
            return this.inverse.keySet();
        }

        public FilteredEntryBiMap(BiMap<K, V> biMap, Predicate<? super Map.Entry<K, V>> predicate, BiMap<V, K> biMap2) {
            super(biMap, predicate);
            this.inverse = biMap2;
        }
    }

    static class FilteredEntryMap<K, V> extends AbstractFilteredMap<K, V> {
        public final Set<Map.Entry<K, V>> filteredEntrySet;

        private class EntrySet extends ForwardingSet<Map.Entry<K, V>> {
            public EntrySet() {
            }

            @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public Iterator<Map.Entry<K, V>> iterator() {
                return new TransformedIterator<Map.Entry<K, V>, Map.Entry<K, V>>(FilteredEntryMap.this.filteredEntrySet.iterator()) { // from class: com.google.common.collect.Maps.FilteredEntryMap.EntrySet.1
                    @Override // com.google.common.collect.TransformedIterator
                    public Map.Entry<K, V> transform(final Map.Entry<K, V> entry) {
                        return new ForwardingMapEntry<K, V>() { // from class: com.google.common.collect.Maps.FilteredEntryMap.EntrySet.1.1
                            @Override // com.google.common.collect.ForwardingMapEntry, java.util.Map.Entry
                            public V setValue(V v) {
                                Preconditions.checkArgument(FilteredEntryMap.this.apply(getKey(), v));
                                return delegate().setValue(v);
                            }

                            @Override // com.google.common.collect.ForwardingMapEntry, com.google.common.collect.ForwardingObject
                            public Map.Entry<K, V> delegate() {
                                return entry;
                            }
                        };
                    }
                };
            }

            public /* synthetic */ EntrySet(AnonymousClass1 anonymousClass1) {
            }

            @Override // com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
            public Set<Map.Entry<K, V>> delegate() {
                return FilteredEntryMap.this.filteredEntrySet;
            }
        }

        class KeySet extends KeySet<K, V> {
            public KeySet() {
                super(FilteredEntryMap.this);
            }

            @Override // com.google.common.collect.Maps.KeySet, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean remove(Object obj) {
                if (!FilteredEntryMap.this.containsKey(obj)) {
                    return false;
                }
                FilteredEntryMap.this.unfiltered.remove(obj);
                return true;
            }

            @Override // com.google.common.collect.Sets.ImprovedAbstractSet, java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean removeAll(Collection<?> collection) {
                FilteredEntryMap filteredEntryMap = FilteredEntryMap.this;
                return FilteredEntryMap.removeAllKeys(filteredEntryMap.unfiltered, filteredEntryMap.predicate, collection);
            }

            @Override // com.google.common.collect.Sets.ImprovedAbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean retainAll(Collection<?> collection) {
                FilteredEntryMap filteredEntryMap = FilteredEntryMap.this;
                return FilteredEntryMap.retainAllKeys(filteredEntryMap.unfiltered, filteredEntryMap.predicate, collection);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public Object[] toArray() {
                return Lists.newArrayList(iterator()).toArray();
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public <T> T[] toArray(T[] tArr) {
                return (T[]) Lists.newArrayList(iterator()).toArray(tArr);
            }
        }

        public FilteredEntryMap(Map<K, V> map, Predicate<? super Map.Entry<K, V>> predicate) {
            super(map, predicate);
            this.filteredEntrySet = Sets.filter(map.entrySet(), this.predicate);
        }

        public static <K, V> boolean removeAllKeys(Map<K, V> map, Predicate<? super Map.Entry<K, V>> predicate, Collection<?> collection) {
            Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
            boolean z = false;
            while (it.hasNext()) {
                Map.Entry<K, V> next = it.next();
                if (predicate.apply(next) && collection.contains(next.getKey())) {
                    it.remove();
                    z = true;
                }
            }
            return z;
        }

        public static <K, V> boolean retainAllKeys(Map<K, V> map, Predicate<? super Map.Entry<K, V>> predicate, Collection<?> collection) {
            Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
            boolean z = false;
            while (it.hasNext()) {
                Map.Entry<K, V> next = it.next();
                if (predicate.apply(next) && !collection.contains(next.getKey())) {
                    it.remove();
                    z = true;
                }
            }
            return z;
        }

        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap
        public Set<Map.Entry<K, V>> createEntrySet() {
            return new EntrySet(null);
        }

        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap
        public Set<K> createKeySet() {
            return new KeySet();
        }
    }

    @GwtIncompatible
    private static class FilteredEntryNavigableMap<K, V> extends AbstractNavigableMap<K, V> {
        public final Predicate<? super Map.Entry<K, V>> entryPredicate;
        public final Map<K, V> filteredDelegate;
        public final NavigableMap<K, V> unfiltered;

        public FilteredEntryNavigableMap(NavigableMap<K, V> navigableMap, Predicate<? super Map.Entry<K, V>> predicate) {
            if (navigableMap == null) {
                throw new NullPointerException();
            }
            this.unfiltered = navigableMap;
            this.entryPredicate = predicate;
            this.filteredDelegate = new FilteredEntryMap(navigableMap, predicate);
        }

        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap, java.util.AbstractMap, java.util.Map
        public void clear() {
            this.filteredDelegate.clear();
        }

        @Override // java.util.SortedMap
        public Comparator<? super K> comparator() {
            return this.unfiltered.comparator();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object obj) {
            return this.filteredDelegate.containsKey(obj);
        }

        @Override // com.google.common.collect.AbstractNavigableMap
        public Iterator<Map.Entry<K, V>> descendingEntryIterator() {
            return Iterators.filter(this.unfiltered.descendingMap().entrySet().iterator(), this.entryPredicate);
        }

        @Override // com.google.common.collect.AbstractNavigableMap, java.util.NavigableMap
        public NavigableMap<K, V> descendingMap() {
            return Maps.filterEntries((NavigableMap) this.unfiltered.descendingMap(), (Predicate) this.entryPredicate);
        }

        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap
        public Iterator<Map.Entry<K, V>> entryIterator() {
            return Iterators.filter(this.unfiltered.entrySet().iterator(), this.entryPredicate);
        }

        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap, java.util.AbstractMap, java.util.Map, java.util.SortedMap
        public Set<Map.Entry<K, V>> entrySet() {
            return this.filteredDelegate.entrySet();
        }

        @Override // com.google.common.collect.AbstractNavigableMap, java.util.AbstractMap, java.util.Map
        public V get(Object obj) {
            return this.filteredDelegate.get(obj);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> headMap(K k, boolean z) {
            return Maps.filterEntries((NavigableMap) this.unfiltered.headMap(k, z), (Predicate) this.entryPredicate);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean isEmpty() {
            return !Iterables.any(this.unfiltered.entrySet(), this.entryPredicate);
        }

        @Override // com.google.common.collect.AbstractNavigableMap, java.util.NavigableMap
        public NavigableSet<K> navigableKeySet() {
            return new NavigableKeySet<K, V>(this) { // from class: com.google.common.collect.Maps.FilteredEntryNavigableMap.1
                @Override // com.google.common.collect.Sets.ImprovedAbstractSet, java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
                public boolean removeAll(Collection<?> collection) {
                    return FilteredEntryMap.removeAllKeys(FilteredEntryNavigableMap.this.unfiltered, FilteredEntryNavigableMap.this.entryPredicate, collection);
                }

                @Override // com.google.common.collect.Sets.ImprovedAbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
                public boolean retainAll(Collection<?> collection) {
                    return FilteredEntryMap.retainAllKeys(FilteredEntryNavigableMap.this.unfiltered, FilteredEntryNavigableMap.this.entryPredicate, collection);
                }
            };
        }

        @Override // com.google.common.collect.AbstractNavigableMap, java.util.NavigableMap
        public Map.Entry<K, V> pollFirstEntry() {
            return (Map.Entry) Iterables.removeFirstMatching(this.unfiltered.entrySet(), this.entryPredicate);
        }

        @Override // com.google.common.collect.AbstractNavigableMap, java.util.NavigableMap
        public Map.Entry<K, V> pollLastEntry() {
            return (Map.Entry) Iterables.removeFirstMatching(this.unfiltered.descendingMap().entrySet(), this.entryPredicate);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V put(K k, V v) {
            return this.filteredDelegate.put(k, v);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public void putAll(Map<? extends K, ? extends V> map) {
            this.filteredDelegate.putAll(map);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V remove(Object obj) {
            return this.filteredDelegate.remove(obj);
        }

        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap, java.util.AbstractMap, java.util.Map
        public int size() {
            return this.filteredDelegate.size();
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> subMap(K k, boolean z, K k2, boolean z2) {
            return Maps.filterEntries((NavigableMap) this.unfiltered.subMap(k, z, k2, z2), (Predicate) this.entryPredicate);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> tailMap(K k, boolean z) {
            return Maps.filterEntries((NavigableMap) this.unfiltered.tailMap(k, z), (Predicate) this.entryPredicate);
        }

        @Override // java.util.AbstractMap, java.util.Map, java.util.SortedMap
        public Collection<V> values() {
            return new FilteredMapValues(this, this.unfiltered, this.entryPredicate);
        }
    }

    private static class FilteredEntrySortedMap<K, V> extends FilteredEntryMap<K, V> implements SortedMap<K, V> {

        class SortedKeySet extends FilteredEntryMap<K, V>.KeySet implements SortedSet<K> {
            public SortedKeySet() {
                super();
            }

            @Override // java.util.SortedSet
            public Comparator<? super K> comparator() {
                return FilteredEntrySortedMap.this.sortedMap().comparator();
            }

            @Override // java.util.SortedSet
            public K first() {
                return (K) FilteredEntrySortedMap.this.firstKey();
            }

            @Override // java.util.SortedSet
            public SortedSet<K> headSet(K k) {
                return (SortedSet) FilteredEntrySortedMap.this.headMap(k).keySet();
            }

            @Override // java.util.SortedSet
            public K last() {
                return (K) FilteredEntrySortedMap.this.lastKey();
            }

            @Override // java.util.SortedSet
            public SortedSet<K> subSet(K k, K k2) {
                return (SortedSet) FilteredEntrySortedMap.this.subMap(k, k2).keySet();
            }

            @Override // java.util.SortedSet
            public SortedSet<K> tailSet(K k) {
                return (SortedSet) FilteredEntrySortedMap.this.tailMap(k).keySet();
            }
        }

        public FilteredEntrySortedMap(SortedMap<K, V> sortedMap, Predicate<? super Map.Entry<K, V>> predicate) {
            super(sortedMap, predicate);
        }

        @Override // java.util.SortedMap
        public Comparator<? super K> comparator() {
            return sortedMap().comparator();
        }

        @Override // java.util.SortedMap
        public K firstKey() {
            return keySet().iterator().next();
        }

        @Override // java.util.SortedMap
        public SortedMap<K, V> headMap(K k) {
            return new FilteredEntrySortedMap(sortedMap().headMap(k), this.predicate);
        }

        @Override // java.util.SortedMap
        public K lastKey() {
            SortedMap<K, V> sortedMap = sortedMap();
            while (true) {
                K kLastKey = sortedMap.lastKey();
                if (apply(kLastKey, this.unfiltered.get(kLastKey))) {
                    return kLastKey;
                }
                sortedMap = sortedMap().headMap(kLastKey);
            }
        }

        public SortedMap<K, V> sortedMap() {
            return (SortedMap) this.unfiltered;
        }

        @Override // java.util.SortedMap
        public SortedMap<K, V> subMap(K k, K k2) {
            return new FilteredEntrySortedMap(sortedMap().subMap(k, k2), this.predicate);
        }

        @Override // java.util.SortedMap
        public SortedMap<K, V> tailMap(K k) {
            return new FilteredEntrySortedMap(sortedMap().tailMap(k), this.predicate);
        }

        @Override // com.google.common.collect.Maps.FilteredEntryMap, com.google.common.collect.Maps.ViewCachingAbstractMap
        public SortedSet<K> createKeySet() {
            return new SortedKeySet();
        }

        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap, java.util.AbstractMap, java.util.Map
        public SortedSet<K> keySet() {
            return (SortedSet) super.keySet();
        }
    }

    private static class FilteredKeyMap<K, V> extends AbstractFilteredMap<K, V> {
        public final Predicate<? super K> keyPredicate;

        public FilteredKeyMap(Map<K, V> map, Predicate<? super K> predicate, Predicate<? super Map.Entry<K, V>> predicate2) {
            super(map, predicate2);
            this.keyPredicate = predicate;
        }

        @Override // com.google.common.collect.Maps.AbstractFilteredMap, java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object obj) {
            return this.unfiltered.containsKey(obj) && this.keyPredicate.apply(obj);
        }

        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap
        public Set<Map.Entry<K, V>> createEntrySet() {
            return Sets.filter(this.unfiltered.entrySet(), this.predicate);
        }

        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap
        public Set<K> createKeySet() {
            return Sets.filter(this.unfiltered.keySet(), this.keyPredicate);
        }
    }

    private static final class FilteredMapValues<K, V> extends Values<K, V> {
        public final Predicate<? super Map.Entry<K, V>> predicate;
        public final Map<K, V> unfiltered;

        public FilteredMapValues(Map<K, V> map, Map<K, V> map2, Predicate<? super Map.Entry<K, V>> predicate) {
            super(map);
            this.unfiltered = map2;
            this.predicate = predicate;
        }

        @Override // com.google.common.collect.Maps.Values, java.util.AbstractCollection, java.util.Collection
        public boolean remove(Object obj) {
            Iterator<Map.Entry<K, V>> it = this.unfiltered.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<K, V> next = it.next();
                if (this.predicate.apply(next) && Objects.equal(next.getValue(), obj)) {
                    it.remove();
                    return true;
                }
            }
            return false;
        }

        @Override // com.google.common.collect.Maps.Values, java.util.AbstractCollection, java.util.Collection
        public boolean removeAll(Collection<?> collection) {
            Iterator<Map.Entry<K, V>> it = this.unfiltered.entrySet().iterator();
            boolean z = false;
            while (it.hasNext()) {
                Map.Entry<K, V> next = it.next();
                if (this.predicate.apply(next) && collection.contains(next.getValue())) {
                    it.remove();
                    z = true;
                }
            }
            return z;
        }

        @Override // com.google.common.collect.Maps.Values, java.util.AbstractCollection, java.util.Collection
        public boolean retainAll(Collection<?> collection) {
            Iterator<Map.Entry<K, V>> it = this.unfiltered.entrySet().iterator();
            boolean z = false;
            while (it.hasNext()) {
                Map.Entry<K, V> next = it.next();
                if (this.predicate.apply(next) && !collection.contains(next.getValue())) {
                    it.remove();
                    z = true;
                }
            }
            return z;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public Object[] toArray() {
            return Lists.newArrayList(iterator()).toArray();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public <T> T[] toArray(T[] tArr) {
            return (T[]) Lists.newArrayList(iterator()).toArray(tArr);
        }
    }

    static abstract class IteratorBasedAbstractMap<K, V> extends AbstractMap<K, V> {
        @Override // java.util.AbstractMap, java.util.Map
        public void clear() {
            Iterators.clear(entryIterator());
        }

        public abstract Iterator<Map.Entry<K, V>> entryIterator();

        @Override // java.util.AbstractMap, java.util.Map, java.util.SortedMap
        public Set<Map.Entry<K, V>> entrySet() {
            return new EntrySet<K, V>() { // from class: com.google.common.collect.Maps.IteratorBasedAbstractMap.1
                @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
                public Iterator<Map.Entry<K, V>> iterator() {
                    return IteratorBasedAbstractMap.this.entryIterator();
                }

                @Override // com.google.common.collect.Maps.EntrySet
                public Map<K, V> map() {
                    return IteratorBasedAbstractMap.this;
                }
            };
        }

        @Override // java.util.AbstractMap, java.util.Map
        public abstract int size();
    }

    static class KeySet<K, V> extends Sets.ImprovedAbstractSet<K> {

        @Weak
        public final Map<K, V> map;

        public KeySet(Map<K, V> map) {
            if (map == null) {
                throw new NullPointerException();
            }
            this.map = map;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            map().clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return map().containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return map().isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return new AnonymousClass1(map().entrySet().iterator());
        }

        public Map<K, V> map() {
            return this.map;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            if (!contains(obj)) {
                return false;
            }
            map().remove(obj);
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return map().size();
        }
    }

    static class MapDifferenceImpl<K, V> implements MapDifference<K, V> {
        public final Map<K, MapDifference.ValueDifference<V>> differences;
        public final Map<K, V> onBoth;
        public final Map<K, V> onlyOnLeft;
        public final Map<K, V> onlyOnRight;

        public MapDifferenceImpl(Map<K, V> map, Map<K, V> map2, Map<K, V> map3, Map<K, MapDifference.ValueDifference<V>> map4) {
            this.onlyOnLeft = Maps.unmodifiableMap(map);
            this.onlyOnRight = Maps.unmodifiableMap(map2);
            this.onBoth = Maps.unmodifiableMap(map3);
            this.differences = Maps.unmodifiableMap(map4);
        }

        @Override // com.google.common.collect.MapDifference
        public boolean areEqual() {
            return this.onlyOnLeft.isEmpty() && this.onlyOnRight.isEmpty() && this.differences.isEmpty();
        }

        @Override // com.google.common.collect.MapDifference
        public Map<K, MapDifference.ValueDifference<V>> entriesDiffering() {
            return this.differences;
        }

        @Override // com.google.common.collect.MapDifference
        public Map<K, V> entriesInCommon() {
            return this.onBoth;
        }

        @Override // com.google.common.collect.MapDifference
        public Map<K, V> entriesOnlyOnLeft() {
            return this.onlyOnLeft;
        }

        @Override // com.google.common.collect.MapDifference
        public Map<K, V> entriesOnlyOnRight() {
            return this.onlyOnRight;
        }

        @Override // com.google.common.collect.MapDifference
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof MapDifference)) {
                return false;
            }
            MapDifference mapDifference = (MapDifference) obj;
            return entriesOnlyOnLeft().equals(mapDifference.entriesOnlyOnLeft()) && entriesOnlyOnRight().equals(mapDifference.entriesOnlyOnRight()) && entriesInCommon().equals(mapDifference.entriesInCommon()) && entriesDiffering().equals(mapDifference.entriesDiffering());
        }

        @Override // com.google.common.collect.MapDifference
        public int hashCode() {
            return Arrays.hashCode(new Object[]{entriesOnlyOnLeft(), entriesOnlyOnRight(), entriesInCommon(), entriesDiffering()});
        }

        public String toString() {
            if (areEqual()) {
                return "equal";
            }
            StringBuilder sb = new StringBuilder("not equal");
            if (!this.onlyOnLeft.isEmpty()) {
                sb.append(": only on left=");
                sb.append(this.onlyOnLeft);
            }
            if (!this.onlyOnRight.isEmpty()) {
                sb.append(": only on right=");
                sb.append(this.onlyOnRight);
            }
            if (!this.differences.isEmpty()) {
                sb.append(": value differences=");
                sb.append(this.differences);
            }
            return sb.toString();
        }
    }

    @GwtIncompatible
    private static final class NavigableAsMapView<K, V> extends AbstractNavigableMap<K, V> {
        public final Function<? super K, V> function;
        public final NavigableSet<K> set;

        public NavigableAsMapView(NavigableSet<K> navigableSet, Function<? super K, V> function) {
            if (navigableSet == null) {
                throw new NullPointerException();
            }
            this.set = navigableSet;
            if (function == null) {
                throw new NullPointerException();
            }
            this.function = function;
        }

        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap, java.util.AbstractMap, java.util.Map
        public void clear() {
            this.set.clear();
        }

        @Override // java.util.SortedMap
        public Comparator<? super K> comparator() {
            return this.set.comparator();
        }

        @Override // com.google.common.collect.AbstractNavigableMap
        public Iterator<Map.Entry<K, V>> descendingEntryIterator() {
            return descendingMap().entrySet().iterator();
        }

        @Override // com.google.common.collect.AbstractNavigableMap, java.util.NavigableMap
        public NavigableMap<K, V> descendingMap() {
            return new NavigableAsMapView(this.set.descendingSet(), this.function);
        }

        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap
        public Iterator<Map.Entry<K, V>> entryIterator() {
            return Maps.asMapEntryIterator(this.set, this.function);
        }

        @Override // com.google.common.collect.AbstractNavigableMap, java.util.AbstractMap, java.util.Map
        public V get(Object obj) {
            if (Collections2.safeContains(this.set, obj)) {
                return this.function.apply(obj);
            }
            return null;
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> headMap(K k, boolean z) {
            return new NavigableAsMapView(this.set.headSet(k, z), this.function);
        }

        @Override // com.google.common.collect.AbstractNavigableMap, java.util.NavigableMap
        public NavigableSet<K> navigableKeySet() {
            return new AnonymousClass6(this.set);
        }

        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap, java.util.AbstractMap, java.util.Map
        public int size() {
            return this.set.size();
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> subMap(K k, boolean z, K k2, boolean z2) {
            return new NavigableAsMapView(this.set.subSet(k, z, k2, z2), this.function);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> tailMap(K k, boolean z) {
            return new NavigableAsMapView(this.set.tailSet(k, z), this.function);
        }
    }

    @GwtIncompatible
    static class NavigableKeySet<K, V> extends SortedKeySet<K, V> implements NavigableSet<K> {
        public NavigableKeySet(NavigableMap<K, V> navigableMap) {
            super(navigableMap);
        }

        @Override // java.util.NavigableSet
        public K ceiling(K k) {
            return map().ceilingKey(k);
        }

        @Override // java.util.NavigableSet
        public Iterator<K> descendingIterator() {
            return descendingSet().iterator();
        }

        @Override // java.util.NavigableSet
        public NavigableSet<K> descendingSet() {
            return map().descendingKeySet();
        }

        @Override // java.util.NavigableSet
        public K floor(K k) {
            return map().floorKey(k);
        }

        @Override // java.util.NavigableSet
        public NavigableSet<K> headSet(K k, boolean z) {
            return map().headMap(k, z).navigableKeySet();
        }

        @Override // java.util.NavigableSet
        public K higher(K k) {
            return map().higherKey(k);
        }

        @Override // java.util.NavigableSet
        public K lower(K k) {
            return map().lowerKey(k);
        }

        @Override // java.util.NavigableSet
        public K pollFirst() {
            return (K) Maps.keyOrNull(map().pollFirstEntry());
        }

        @Override // java.util.NavigableSet
        public K pollLast() {
            return (K) Maps.keyOrNull(map().pollLastEntry());
        }

        @Override // java.util.NavigableSet
        public NavigableSet<K> subSet(K k, boolean z, K k2, boolean z2) {
            return map().subMap(k, z, k2, z2).navigableKeySet();
        }

        @Override // java.util.NavigableSet
        public NavigableSet<K> tailSet(K k, boolean z) {
            return map().tailMap(k, z).navigableKeySet();
        }

        @Override // com.google.common.collect.Maps.SortedKeySet, java.util.SortedSet, java.util.NavigableSet
        public SortedSet<K> headSet(K k) {
            return headSet(k, false);
        }

        @Override // com.google.common.collect.Maps.SortedKeySet, java.util.SortedSet, java.util.NavigableSet
        public SortedSet<K> subSet(K k, K k2) {
            return subSet(k, true, k2, false);
        }

        @Override // com.google.common.collect.Maps.SortedKeySet, java.util.SortedSet, java.util.NavigableSet
        public SortedSet<K> tailSet(K k) {
            return tailSet(k, true);
        }

        @Override // com.google.common.collect.Maps.SortedKeySet, com.google.common.collect.Maps.KeySet
        public NavigableMap<K, V> map() {
            return (NavigableMap) this.map;
        }
    }

    private static class SortedAsMapView<K, V> extends AsMapView<K, V> implements SortedMap<K, V> {
        public SortedAsMapView(SortedSet<K> sortedSet, Function<? super K, V> function) {
            super(sortedSet, function);
        }

        @Override // com.google.common.collect.Maps.AsMapView
        public SortedSet<K> backingSet() {
            return (SortedSet) this.set;
        }

        @Override // java.util.SortedMap
        public Comparator<? super K> comparator() {
            return backingSet().comparator();
        }

        @Override // java.util.SortedMap
        public K firstKey() {
            return backingSet().first();
        }

        @Override // java.util.SortedMap
        public SortedMap<K, V> headMap(K k) {
            return new SortedAsMapView(backingSet().headSet(k), this.function);
        }

        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap, java.util.AbstractMap, java.util.Map
        public Set<K> keySet() {
            return new AnonymousClass5(backingSet());
        }

        @Override // java.util.SortedMap
        public K lastKey() {
            return backingSet().last();
        }

        @Override // java.util.SortedMap
        public SortedMap<K, V> subMap(K k, K k2) {
            return new SortedAsMapView(backingSet().subSet(k, k2), this.function);
        }

        @Override // java.util.SortedMap
        public SortedMap<K, V> tailMap(K k) {
            return new SortedAsMapView(backingSet().tailSet(k), this.function);
        }
    }

    static class SortedKeySet<K, V> extends KeySet<K, V> implements SortedSet<K> {
        public SortedKeySet(SortedMap<K, V> sortedMap) {
            super(sortedMap);
        }

        @Override // java.util.SortedSet
        public Comparator<? super K> comparator() {
            return map().comparator();
        }

        @Override // java.util.SortedSet
        public K first() {
            return map().firstKey();
        }

        public SortedSet<K> headSet(K k) {
            return new SortedKeySet(map().headMap(k));
        }

        @Override // java.util.SortedSet
        public K last() {
            return map().lastKey();
        }

        @Override // com.google.common.collect.Maps.KeySet
        public SortedMap<K, V> map() {
            return (SortedMap) this.map;
        }

        public SortedSet<K> subSet(K k, K k2) {
            return new SortedKeySet(map().subMap(k, k2));
        }

        public SortedSet<K> tailSet(K k) {
            return new SortedKeySet(map().tailMap(k));
        }
    }

    static class SortedMapDifferenceImpl<K, V> extends MapDifferenceImpl<K, V> implements SortedMapDifference<K, V> {
        public SortedMapDifferenceImpl(SortedMap<K, V> sortedMap, SortedMap<K, V> sortedMap2, SortedMap<K, V> sortedMap3, SortedMap<K, MapDifference.ValueDifference<V>> sortedMap4) {
            super(sortedMap, sortedMap2, sortedMap3, sortedMap4);
        }

        @Override // com.google.common.collect.Maps.MapDifferenceImpl, com.google.common.collect.MapDifference
        public SortedMap<K, MapDifference.ValueDifference<V>> entriesDiffering() {
            return (SortedMap) this.differences;
        }

        @Override // com.google.common.collect.Maps.MapDifferenceImpl, com.google.common.collect.MapDifference
        public SortedMap<K, V> entriesInCommon() {
            return (SortedMap) this.onBoth;
        }

        @Override // com.google.common.collect.Maps.MapDifferenceImpl, com.google.common.collect.MapDifference
        public SortedMap<K, V> entriesOnlyOnLeft() {
            return (SortedMap) this.onlyOnLeft;
        }

        @Override // com.google.common.collect.Maps.MapDifferenceImpl, com.google.common.collect.MapDifference
        public SortedMap<K, V> entriesOnlyOnRight() {
            return (SortedMap) this.onlyOnRight;
        }
    }

    static class TransformedEntriesMap<K, V1, V2> extends IteratorBasedAbstractMap<K, V2> {
        public final Map<K, V1> fromMap;
        public final EntryTransformer<? super K, ? super V1, V2> transformer;

        public TransformedEntriesMap(Map<K, V1> map, EntryTransformer<? super K, ? super V1, V2> entryTransformer) {
            if (map == null) {
                throw new NullPointerException();
            }
            this.fromMap = map;
            if (entryTransformer == null) {
                throw new NullPointerException();
            }
            this.transformer = entryTransformer;
        }

        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap, java.util.AbstractMap, java.util.Map
        public void clear() {
            this.fromMap.clear();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object obj) {
            return this.fromMap.containsKey(obj);
        }

        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap
        public Iterator<Map.Entry<K, V2>> entryIterator() {
            return new Iterators.AnonymousClass6(this.fromMap.entrySet().iterator(), Maps.asEntryToEntryFunction(this.transformer));
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V2 get(Object obj) {
            V1 v1 = this.fromMap.get(obj);
            if (v1 != null || this.fromMap.containsKey(obj)) {
                return this.transformer.transformEntry(obj, v1);
            }
            return null;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<K> keySet() {
            return this.fromMap.keySet();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V2 remove(Object obj) {
            if (this.fromMap.containsKey(obj)) {
                return this.transformer.transformEntry(obj, this.fromMap.remove(obj));
            }
            return null;
        }

        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap, java.util.AbstractMap, java.util.Map
        public int size() {
            return this.fromMap.size();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Collection<V2> values() {
            return new Values(this);
        }
    }

    @GwtIncompatible
    private static class TransformedEntriesNavigableMap<K, V1, V2> extends TransformedEntriesSortedMap<K, V1, V2> implements NavigableMap<K, V2> {
        public TransformedEntriesNavigableMap(NavigableMap<K, V1> navigableMap, EntryTransformer<? super K, ? super V1, V2> entryTransformer) {
            super(navigableMap, entryTransformer);
        }

        private Map.Entry<K, V2> transformEntry(Map.Entry<K, V1> entry) {
            if (entry == null) {
                return null;
            }
            return Maps.transformEntry(this.transformer, entry);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V2> ceilingEntry(K k) {
            return transformEntry(fromMap().ceilingEntry(k));
        }

        @Override // java.util.NavigableMap
        public K ceilingKey(K k) {
            return fromMap().ceilingKey(k);
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> descendingKeySet() {
            return fromMap().descendingKeySet();
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V2> descendingMap() {
            return new TransformedEntriesNavigableMap(fromMap().descendingMap(), this.transformer);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V2> firstEntry() {
            return transformEntry(fromMap().firstEntry());
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V2> floorEntry(K k) {
            return transformEntry(fromMap().floorEntry(k));
        }

        @Override // java.util.NavigableMap
        public K floorKey(K k) {
            return fromMap().floorKey(k);
        }

        @Override // com.google.common.collect.Maps.TransformedEntriesSortedMap
        public NavigableMap<K, V1> fromMap() {
            return (NavigableMap) this.fromMap;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V2> higherEntry(K k) {
            return transformEntry(fromMap().higherEntry(k));
        }

        @Override // java.util.NavigableMap
        public K higherKey(K k) {
            return fromMap().higherKey(k);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V2> lastEntry() {
            return transformEntry(fromMap().lastEntry());
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V2> lowerEntry(K k) {
            return transformEntry(fromMap().lowerEntry(k));
        }

        @Override // java.util.NavigableMap
        public K lowerKey(K k) {
            return fromMap().lowerKey(k);
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> navigableKeySet() {
            return fromMap().navigableKeySet();
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V2> pollFirstEntry() {
            return transformEntry(fromMap().pollFirstEntry());
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V2> pollLastEntry() {
            return transformEntry(fromMap().pollLastEntry());
        }

        @Override // com.google.common.collect.Maps.TransformedEntriesSortedMap, java.util.SortedMap, java.util.NavigableMap
        public NavigableMap<K, V2> headMap(K k) {
            return headMap(k, false);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V2> subMap(K k, boolean z, K k2, boolean z2) {
            return new TransformedEntriesNavigableMap(fromMap().subMap(k, z, k2, z2), this.transformer);
        }

        @Override // com.google.common.collect.Maps.TransformedEntriesSortedMap, java.util.SortedMap, java.util.NavigableMap
        public NavigableMap<K, V2> tailMap(K k) {
            return tailMap(k, true);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V2> headMap(K k, boolean z) {
            return new TransformedEntriesNavigableMap(fromMap().headMap(k, z), this.transformer);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V2> tailMap(K k, boolean z) {
            return new TransformedEntriesNavigableMap(fromMap().tailMap(k, z), this.transformer);
        }

        @Override // com.google.common.collect.Maps.TransformedEntriesSortedMap, java.util.SortedMap, java.util.NavigableMap
        public NavigableMap<K, V2> subMap(K k, K k2) {
            return subMap(k, true, k2, false);
        }
    }

    static class TransformedEntriesSortedMap<K, V1, V2> extends TransformedEntriesMap<K, V1, V2> implements SortedMap<K, V2> {
        public TransformedEntriesSortedMap(SortedMap<K, V1> sortedMap, EntryTransformer<? super K, ? super V1, V2> entryTransformer) {
            super(sortedMap, entryTransformer);
        }

        @Override // java.util.SortedMap
        public Comparator<? super K> comparator() {
            return fromMap().comparator();
        }

        @Override // java.util.SortedMap
        public K firstKey() {
            return fromMap().firstKey();
        }

        public SortedMap<K, V1> fromMap() {
            return (SortedMap) this.fromMap;
        }

        public SortedMap<K, V2> headMap(K k) {
            return new TransformedEntriesSortedMap(fromMap().headMap(k), this.transformer);
        }

        @Override // java.util.SortedMap
        public K lastKey() {
            return fromMap().lastKey();
        }

        public SortedMap<K, V2> subMap(K k, K k2) {
            return new TransformedEntriesSortedMap(fromMap().subMap(k, k2), this.transformer);
        }

        public SortedMap<K, V2> tailMap(K k) {
            return new TransformedEntriesSortedMap(fromMap().tailMap(k), this.transformer);
        }
    }

    private static class UnmodifiableBiMap<K, V> extends ForwardingMap<K, V> implements BiMap<K, V>, Serializable {
        public static final long serialVersionUID = 0;
        public final BiMap<? extends K, ? extends V> delegate;

        @RetainedWith
        public BiMap<V, K> inverse;
        public final Map<K, V> unmodifiableMap;
        public transient Set<V> values;

        public UnmodifiableBiMap(BiMap<? extends K, ? extends V> biMap, BiMap<V, K> biMap2) {
            this.unmodifiableMap = Collections.unmodifiableMap(biMap);
            this.delegate = biMap;
            this.inverse = biMap2;
        }

        @Override // com.google.common.collect.BiMap
        public V forcePut(K k, V v) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.BiMap
        public BiMap<V, K> inverse() {
            BiMap<V, K> biMap = this.inverse;
            if (biMap != null) {
                return biMap;
            }
            UnmodifiableBiMap unmodifiableBiMap = new UnmodifiableBiMap(this.delegate.inverse(), this);
            this.inverse = unmodifiableBiMap;
            return unmodifiableBiMap;
        }

        @Override // com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingObject
        public Map<K, V> delegate() {
            return this.unmodifiableMap;
        }

        @Override // com.google.common.collect.ForwardingMap, java.util.Map
        public Set<V> values() {
            Set<V> set = this.values;
            if (set != null) {
                return set;
            }
            Set<V> setUnmodifiableSet = Collections.unmodifiableSet(this.delegate.values());
            this.values = setUnmodifiableSet;
            return setUnmodifiableSet;
        }
    }

    static class UnmodifiableEntries<K, V> extends ForwardingCollection<Map.Entry<K, V>> {
        public final Collection<Map.Entry<K, V>> entries;

        public UnmodifiableEntries(Collection<Map.Entry<K, V>> collection) {
            this.entries = collection;
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return new AnonymousClass8(this.entries.iterator());
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public Object[] toArray() {
            return standardToArray();
        }

        @Override // com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public Collection<Map.Entry<K, V>> delegate() {
            return this.entries;
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public <T> T[] toArray(T[] tArr) {
            return (T[]) standardToArray(tArr);
        }
    }

    static class UnmodifiableEntrySet<K, V> extends UnmodifiableEntries<K, V> implements Set<Map.Entry<K, V>> {
        public UnmodifiableEntrySet(Set<Map.Entry<K, V>> set) {
            super(set);
        }

        @Override // java.util.Collection, java.util.Set
        public boolean equals(Object obj) {
            return Sets.equalsImpl(this, obj);
        }

        @Override // java.util.Collection, java.util.Set
        public int hashCode() {
            return Sets.hashCodeImpl(this);
        }
    }

    @GwtIncompatible
    static class UnmodifiableNavigableMap<K, V> extends ForwardingSortedMap<K, V> implements NavigableMap<K, V>, Serializable {
        public final NavigableMap<K, ? extends V> delegate;
        public transient UnmodifiableNavigableMap<K, V> descendingMap;

        public UnmodifiableNavigableMap(NavigableMap<K, ? extends V> navigableMap) {
            this.delegate = navigableMap;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> ceilingEntry(K k) {
            return Maps.unmodifiableOrNull(this.delegate.ceilingEntry(k));
        }

        @Override // java.util.NavigableMap
        public K ceilingKey(K k) {
            return this.delegate.ceilingKey(k);
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> descendingKeySet() {
            return Sets.unmodifiableNavigableSet(this.delegate.descendingKeySet());
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> descendingMap() {
            UnmodifiableNavigableMap<K, V> unmodifiableNavigableMap = this.descendingMap;
            if (unmodifiableNavigableMap != null) {
                return unmodifiableNavigableMap;
            }
            UnmodifiableNavigableMap<K, V> unmodifiableNavigableMap2 = new UnmodifiableNavigableMap<>(this.delegate.descendingMap(), this);
            this.descendingMap = unmodifiableNavigableMap2;
            return unmodifiableNavigableMap2;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> firstEntry() {
            return Maps.unmodifiableOrNull(this.delegate.firstEntry());
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> floorEntry(K k) {
            return Maps.unmodifiableOrNull(this.delegate.floorEntry(k));
        }

        @Override // java.util.NavigableMap
        public K floorKey(K k) {
            return this.delegate.floorKey(k);
        }

        @Override // com.google.common.collect.ForwardingSortedMap, java.util.SortedMap
        public SortedMap<K, V> headMap(K k) {
            return headMap(k, false);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> higherEntry(K k) {
            return Maps.unmodifiableOrNull(this.delegate.higherEntry(k));
        }

        @Override // java.util.NavigableMap
        public K higherKey(K k) {
            return this.delegate.higherKey(k);
        }

        @Override // com.google.common.collect.ForwardingMap, java.util.Map
        public Set<K> keySet() {
            return navigableKeySet();
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> lastEntry() {
            return Maps.unmodifiableOrNull(this.delegate.lastEntry());
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> lowerEntry(K k) {
            return Maps.unmodifiableOrNull(this.delegate.lowerEntry(k));
        }

        @Override // java.util.NavigableMap
        public K lowerKey(K k) {
            return this.delegate.lowerKey(k);
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> navigableKeySet() {
            return Sets.unmodifiableNavigableSet(this.delegate.navigableKeySet());
        }

        @Override // java.util.NavigableMap
        public final Map.Entry<K, V> pollFirstEntry() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.NavigableMap
        public final Map.Entry<K, V> pollLastEntry() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingSortedMap, java.util.SortedMap
        public SortedMap<K, V> subMap(K k, K k2) {
            return subMap(k, true, k2, false);
        }

        @Override // com.google.common.collect.ForwardingSortedMap, java.util.SortedMap
        public SortedMap<K, V> tailMap(K k) {
            return tailMap(k, true);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> headMap(K k, boolean z) {
            return Maps.unmodifiableNavigableMap(this.delegate.headMap(k, z));
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> subMap(K k, boolean z, K k2, boolean z2) {
            return Maps.unmodifiableNavigableMap(this.delegate.subMap(k, z, k2, z2));
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> tailMap(K k, boolean z) {
            return Maps.unmodifiableNavigableMap(this.delegate.tailMap(k, z));
        }

        public UnmodifiableNavigableMap(NavigableMap<K, ? extends V> navigableMap, UnmodifiableNavigableMap<K, V> unmodifiableNavigableMap) {
            this.delegate = navigableMap;
            this.descendingMap = unmodifiableNavigableMap;
        }

        @Override // com.google.common.collect.ForwardingSortedMap, com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingObject
        public SortedMap<K, V> delegate() {
            return Collections.unmodifiableSortedMap(this.delegate);
        }
    }

    static class ValueDifferenceImpl<V> implements MapDifference.ValueDifference<V> {
        public final V left;
        public final V right;

        public ValueDifferenceImpl(V v, V v2) {
            this.left = v;
            this.right = v2;
        }

        public static <V> MapDifference.ValueDifference<V> create(V v, V v2) {
            return new ValueDifferenceImpl(v, v2);
        }

        @Override // com.google.common.collect.MapDifference.ValueDifference
        public boolean equals(Object obj) {
            if (!(obj instanceof MapDifference.ValueDifference)) {
                return false;
            }
            MapDifference.ValueDifference valueDifference = (MapDifference.ValueDifference) obj;
            return Objects.equal(this.left, valueDifference.leftValue()) && Objects.equal(this.right, valueDifference.rightValue());
        }

        @Override // com.google.common.collect.MapDifference.ValueDifference
        public int hashCode() {
            return Arrays.hashCode(new Object[]{this.left, this.right});
        }

        @Override // com.google.common.collect.MapDifference.ValueDifference
        public V leftValue() {
            return this.left;
        }

        @Override // com.google.common.collect.MapDifference.ValueDifference
        public V rightValue() {
            return this.right;
        }

        public String toString() {
            String strValueOf = String.valueOf(this.left);
            String strValueOf2 = String.valueOf(this.right);
            StringBuilder sbA = a.a(strValueOf2.length() + strValueOf.length() + 4, "(", strValueOf, ", ", strValueOf2);
            sbA.append(")");
            return sbA.toString();
        }
    }

    static class Values<K, V> extends AbstractCollection<V> {

        @Weak
        public final Map<K, V> map;

        public Values(Map<K, V> map) {
            if (map == null) {
                throw new NullPointerException();
            }
            this.map = map;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            map().clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            return map().containsValue(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return map().isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return new AnonymousClass2(map().entrySet().iterator());
        }

        public final Map<K, V> map() {
            return this.map;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean remove(Object obj) {
            try {
                return super.remove(obj);
            } catch (UnsupportedOperationException unused) {
                for (Map.Entry<K, V> entry : map().entrySet()) {
                    if (Objects.equal(obj, entry.getValue())) {
                        map().remove(entry.getKey());
                        return true;
                    }
                }
                return false;
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean removeAll(Collection<?> collection) {
            try {
                if (collection != null) {
                    return super.removeAll(collection);
                }
                throw new NullPointerException();
            } catch (UnsupportedOperationException unused) {
                HashSet hashSet = new HashSet();
                for (Map.Entry<K, V> entry : map().entrySet()) {
                    if (collection.contains(entry.getValue())) {
                        hashSet.add(entry.getKey());
                    }
                }
                return map().keySet().removeAll(hashSet);
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean retainAll(Collection<?> collection) {
            try {
                if (collection != null) {
                    return super.retainAll(collection);
                }
                throw new NullPointerException();
            } catch (UnsupportedOperationException unused) {
                HashSet hashSet = new HashSet();
                for (Map.Entry<K, V> entry : map().entrySet()) {
                    if (collection.contains(entry.getValue())) {
                        hashSet.add(entry.getKey());
                    }
                }
                return map().keySet().retainAll(hashSet);
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return map().size();
        }
    }

    @GwtCompatible
    static abstract class ViewCachingAbstractMap<K, V> extends AbstractMap<K, V> {
        public transient Set<Map.Entry<K, V>> entrySet;
        public transient Set<K> keySet;
        public transient Collection<V> values;

        public abstract Set<Map.Entry<K, V>> createEntrySet();

        public Set<K> createKeySet() {
            return new KeySet(this);
        }

        public Collection<V> createValues() {
            return new Values(this);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<Map.Entry<K, V>> entrySet() {
            Set<Map.Entry<K, V>> set = this.entrySet;
            if (set != null) {
                return set;
            }
            Set<Map.Entry<K, V>> setCreateEntrySet = createEntrySet();
            this.entrySet = setCreateEntrySet;
            return setCreateEntrySet;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<K> keySet() {
            Set<K> set = this.keySet;
            if (set != null) {
                return set;
            }
            Set<K> setCreateKeySet = createKeySet();
            this.keySet = setCreateKeySet;
            return setCreateKeySet;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Collection<V> values() {
            Collection<V> collection = this.values;
            if (collection != null) {
                return collection;
            }
            Collection<V> collectionCreateValues = createValues();
            this.values = collectionCreateValues;
            return collectionCreateValues;
        }
    }

    public static /* synthetic */ Set access$200(Set set) {
        return new AnonymousClass4(set);
    }

    public static /* synthetic */ SortedSet access$300(SortedSet sortedSet) {
        return new AnonymousClass5(sortedSet);
    }

    public static /* synthetic */ NavigableSet access$400(NavigableSet navigableSet) {
        return new AnonymousClass6(navigableSet);
    }

    public static <A, B> Converter<A, B> asConverter(BiMap<A, B> biMap) {
        return new BiMapConverter(biMap);
    }

    public static <K, V1, V2> Function<Map.Entry<K, V1>, Map.Entry<K, V2>> asEntryToEntryFunction(final EntryTransformer<? super K, ? super V1, V2> entryTransformer) {
        if (entryTransformer != null) {
            return new Function<Map.Entry<K, V1>, Map.Entry<K, V2>>() { // from class: com.google.common.collect.Maps.13
                @Override // com.google.common.base.Function
                public Map.Entry<K, V2> apply(Map.Entry<K, V1> entry) {
                    return Maps.transformEntry(entryTransformer, entry);
                }
            };
        }
        throw new NullPointerException();
    }

    public static <K, V1, V2> Function<Map.Entry<K, V1>, V2> asEntryToValueFunction(final EntryTransformer<? super K, ? super V1, V2> entryTransformer) {
        if (entryTransformer != null) {
            return new Function<Map.Entry<K, V1>, V2>() { // from class: com.google.common.collect.Maps.11
                @Override // com.google.common.base.Function
                public V2 apply(Map.Entry<K, V1> entry) {
                    return (V2) entryTransformer.transformEntry(entry.getKey(), entry.getValue());
                }
            };
        }
        throw new NullPointerException();
    }

    public static <K, V1, V2> EntryTransformer<K, V1, V2> asEntryTransformer(Function<? super V1, V2> function) {
        if (function != null) {
            return new AnonymousClass9(function);
        }
        throw new NullPointerException();
    }

    public static <K, V> Map<K, V> asMap(Set<K> set, Function<? super K, V> function) {
        return new AsMapView(set, function);
    }

    public static <K, V> Iterator<Map.Entry<K, V>> asMapEntryIterator(Set<K> set, final Function<? super K, V> function) {
        return new TransformedIterator<K, Map.Entry<K, V>>(set.iterator()) { // from class: com.google.common.collect.Maps.3
            @Override // com.google.common.collect.TransformedIterator
            public Map.Entry<K, V> transform(K k) {
                return new ImmutableEntry(k, function.apply(k));
            }
        };
    }

    public static <K, V1, V2> Function<V1, V2> asValueToValueFunction(final EntryTransformer<? super K, V1, V2> entryTransformer, final K k) {
        if (entryTransformer != null) {
            return new Function<V1, V2>() { // from class: com.google.common.collect.Maps.10
                @Override // com.google.common.base.Function
                public V2 apply(V1 v1) {
                    return (V2) entryTransformer.transformEntry(k, v1);
                }
            };
        }
        throw new NullPointerException();
    }

    public static int capacity(int i) {
        if (i < 3) {
            CollectPreconditions.checkNonnegative(i, "expectedSize");
            return i + 1;
        }
        if (i < 1073741824) {
            return (int) ((i / 0.75f) + 1.0f);
        }
        return Integer.MAX_VALUE;
    }

    public static <K, V> boolean containsEntryImpl(Collection<Map.Entry<K, V>> collection, Object obj) {
        if (obj instanceof Map.Entry) {
            return collection.contains(unmodifiableEntry((Map.Entry) obj));
        }
        return false;
    }

    public static boolean containsKeyImpl(Map<?, ?> map, Object obj) {
        return Iterators.contains(new AnonymousClass1(map.entrySet().iterator()), obj);
    }

    public static boolean containsValueImpl(Map<?, ?> map, Object obj) {
        return Iterators.contains(new AnonymousClass2(map.entrySet().iterator()), obj);
    }

    public static <K, V> MapDifference<K, V> difference(Map<? extends K, ? extends V> map, Map<? extends K, ? extends V> map2, Equivalence<? super V> equivalence) {
        if (equivalence == null) {
            throw new NullPointerException();
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(map2);
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        LinkedHashMap linkedHashMap4 = new LinkedHashMap();
        doDifference(map, map2, equivalence, linkedHashMap, linkedHashMap2, linkedHashMap3, linkedHashMap4);
        return new MapDifferenceImpl(linkedHashMap, linkedHashMap2, linkedHashMap3, linkedHashMap4);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <K, V> void doDifference(Map<? extends K, ? extends V> map, Map<? extends K, ? extends V> map2, Equivalence<? super V> equivalence, Map<K, V> map3, Map<K, V> map4, Map<K, V> map5, Map<K, MapDifference.ValueDifference<V>> map6) {
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            K key = entry.getKey();
            V value = entry.getValue();
            if (map2.containsKey(key)) {
                V vRemove = map4.remove(key);
                if (equivalence.equivalent(value, vRemove)) {
                    map5.put(key, value);
                } else {
                    map6.put(key, new ValueDifferenceImpl(value, vRemove));
                }
            } else {
                map3.put(key, value);
            }
        }
    }

    public static boolean equalsImpl(Map<?, ?> map, Object obj) {
        if (map == obj) {
            return true;
        }
        if (obj instanceof Map) {
            return map.entrySet().equals(((Map) obj).entrySet());
        }
        return false;
    }

    public static <K, V> Map<K, V> filterEntries(Map<K, V> map, Predicate<? super Map.Entry<K, V>> predicate) {
        if (predicate == null) {
            throw new NullPointerException();
        }
        if (map instanceof AbstractFilteredMap) {
            return filterFiltered((AbstractFilteredMap) map, predicate);
        }
        if (map != null) {
            return new FilteredEntryMap(map, predicate);
        }
        throw new NullPointerException();
    }

    public static <K, V> Map<K, V> filterFiltered(AbstractFilteredMap<K, V> abstractFilteredMap, Predicate<? super Map.Entry<K, V>> predicate) {
        return new FilteredEntryMap(abstractFilteredMap.unfiltered, Predicates.and(abstractFilteredMap.predicate, predicate));
    }

    public static <K, V> Map<K, V> filterKeys(Map<K, V> map, Predicate<? super K> predicate) {
        if (predicate == null) {
            throw new NullPointerException();
        }
        Predicate predicateKeyPredicateOnEntries = keyPredicateOnEntries(predicate);
        if (map instanceof AbstractFilteredMap) {
            return filterFiltered((AbstractFilteredMap) map, predicateKeyPredicateOnEntries);
        }
        if (map != null) {
            return new FilteredKeyMap(map, predicate, predicateKeyPredicateOnEntries);
        }
        throw new NullPointerException();
    }

    public static <K, V> Map<K, V> filterValues(Map<K, V> map, Predicate<? super V> predicate) {
        return filterEntries(map, valuePredicateOnEntries(predicate));
    }

    @GwtIncompatible
    public static ImmutableMap<String, String> fromProperties(Properties properties) {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        Enumeration<?> enumerationPropertyNames = properties.propertyNames();
        while (enumerationPropertyNames.hasMoreElements()) {
            String str = (String) enumerationPropertyNames.nextElement();
            builder.put(str, properties.getProperty(str));
        }
        return builder.build();
    }

    @GwtCompatible(serializable = true)
    public static <K, V> Map.Entry<K, V> immutableEntry(K k, V v) {
        return new ImmutableEntry(k, v);
    }

    @GwtCompatible(serializable = true)
    public static <K extends Enum<K>, V> ImmutableMap<K, V> immutableEnumMap(Map<K, ? extends V> map) {
        if (map instanceof ImmutableEnumMap) {
            return (ImmutableEnumMap) map;
        }
        Iterator<Map.Entry<K, ? extends V>> it = map.entrySet().iterator();
        if (!it.hasNext()) {
            return ImmutableMap.of();
        }
        Map.Entry<K, ? extends V> next = it.next();
        K key = next.getKey();
        V value = next.getValue();
        CollectPreconditions.checkEntryNotNull(key, value);
        EnumMap enumMap = new EnumMap(key.getDeclaringClass());
        enumMap.put((Enum) key, (Object) value);
        while (it.hasNext()) {
            Map.Entry<K, ? extends V> next2 = it.next();
            K key2 = next2.getKey();
            V value2 = next2.getValue();
            CollectPreconditions.checkEntryNotNull(key2, value2);
            enumMap.put((Enum) key2, (Object) value2);
        }
        return ImmutableEnumMap.asImmutable(enumMap);
    }

    public static <E> ImmutableMap<E, Integer> indexMap(Collection<E> collection) {
        ImmutableMap.Builder builder = new ImmutableMap.Builder(collection.size());
        Iterator<E> it = collection.iterator();
        int i = 0;
        while (it.hasNext()) {
            builder.put(it.next(), Integer.valueOf(i));
            i++;
        }
        return builder.build();
    }

    public static <K> Function<Map.Entry<K, ?>, K> keyFunction() {
        return EntryFunction.KEY;
    }

    public static <K, V> Iterator<K> keyIterator(Iterator<Map.Entry<K, V>> it) {
        return new AnonymousClass1(it);
    }

    public static <K> K keyOrNull(Map.Entry<K, ?> entry) {
        if (entry == null) {
            return null;
        }
        return entry.getKey();
    }

    public static <K> Predicate<Map.Entry<K, ?>> keyPredicateOnEntries(Predicate<? super K> predicate) {
        return new Predicates.CompositionPredicate(predicate, EntryFunction.KEY);
    }

    public static <K, V> ConcurrentMap<K, V> newConcurrentMap() {
        return new ConcurrentHashMap();
    }

    public static <K extends Enum<K>, V> EnumMap<K, V> newEnumMap(Class<K> cls) {
        if (cls != null) {
            return new EnumMap<>(cls);
        }
        throw new NullPointerException();
    }

    public static <K, V> HashMap<K, V> newHashMap() {
        return new HashMap<>();
    }

    public static <K, V> HashMap<K, V> newHashMapWithExpectedSize(int i) {
        return new HashMap<>(capacity(i));
    }

    public static <K, V> IdentityHashMap<K, V> newIdentityHashMap() {
        return new IdentityHashMap<>();
    }

    public static <K, V> LinkedHashMap<K, V> newLinkedHashMap() {
        return new LinkedHashMap<>();
    }

    public static <K, V> LinkedHashMap<K, V> newLinkedHashMapWithExpectedSize(int i) {
        return new LinkedHashMap<>(capacity(i));
    }

    public static <K extends Comparable, V> TreeMap<K, V> newTreeMap() {
        return new TreeMap<>();
    }

    public static <E> Comparator<? super E> orNaturalOrder(Comparator<? super E> comparator) {
        return comparator != null ? comparator : Ordering.natural();
    }

    public static <K, V> void putAllImpl(Map<K, V> map, Map<? extends K, ? extends V> map2) {
        for (Map.Entry<? extends K, ? extends V> entry : map2.entrySet()) {
            map.put(entry.getKey(), entry.getValue());
        }
    }

    public static <K, V> boolean removeEntryImpl(Collection<Map.Entry<K, V>> collection, Object obj) {
        if (obj instanceof Map.Entry) {
            return collection.remove(unmodifiableEntry((Map.Entry) obj));
        }
        return false;
    }

    @GwtIncompatible
    public static <E> NavigableSet<E> removeOnlyNavigableSet(NavigableSet<E> navigableSet) {
        return new AnonymousClass6(navigableSet);
    }

    public static <E> Set<E> removeOnlySet(Set<E> set) {
        return new AnonymousClass4(set);
    }

    public static <E> SortedSet<E> removeOnlySortedSet(SortedSet<E> sortedSet) {
        return new AnonymousClass5(sortedSet);
    }

    public static boolean safeContainsKey(Map<?, ?> map, Object obj) {
        if (map == null) {
            throw new NullPointerException();
        }
        try {
            return map.containsKey(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    public static <V> V safeGet(Map<?, V> map, Object obj) {
        if (map == null) {
            throw new NullPointerException();
        }
        try {
            return map.get(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return null;
        }
    }

    public static <V> V safeRemove(Map<?, V> map, Object obj) {
        if (map == null) {
            throw new NullPointerException();
        }
        try {
            return map.remove(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Beta
    @GwtIncompatible
    public static <K extends Comparable<? super K>, V> NavigableMap<K, V> subMap(NavigableMap<K, V> navigableMap, Range<K> range) {
        if (navigableMap.comparator() != null && navigableMap.comparator() != Ordering.natural() && range.hasLowerBound() && range.hasUpperBound()) {
            Preconditions.checkArgument(navigableMap.comparator().compare(range.lowerEndpoint(), range.upperEndpoint()) <= 0, "map is using a custom comparator which is inconsistent with the natural ordering.");
        }
        if (range.hasLowerBound() && range.hasUpperBound()) {
            return navigableMap.subMap(range.lowerEndpoint(), range.lowerBoundType() == BoundType.CLOSED, range.upperEndpoint(), range.upperBoundType() == BoundType.CLOSED);
        }
        if (range.hasLowerBound()) {
            return navigableMap.tailMap(range.lowerEndpoint(), range.lowerBoundType() == BoundType.CLOSED);
        }
        if (range.hasUpperBound()) {
            return navigableMap.headMap(range.upperEndpoint(), range.upperBoundType() == BoundType.CLOSED);
        }
        return navigableMap;
    }

    public static <K, V> BiMap<K, V> synchronizedBiMap(BiMap<K, V> biMap) {
        return Synchronized.biMap(biMap, null);
    }

    @GwtIncompatible
    public static <K, V> NavigableMap<K, V> synchronizedNavigableMap(NavigableMap<K, V> navigableMap) {
        return Synchronized.navigableMap(navigableMap);
    }

    public static <K, V> ImmutableMap<K, V> toMap(Iterator<K> it, Function<? super K, V> function) {
        if (function == null) {
            throw new NullPointerException();
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        while (it.hasNext()) {
            K next = it.next();
            linkedHashMap.put(next, function.apply(next));
        }
        return ImmutableMap.copyOf((Map) linkedHashMap);
    }

    public static String toStringImpl(Map<?, ?> map) {
        StringBuilder sbNewStringBuilderForCollection = Collections2.newStringBuilderForCollection(map.size());
        sbNewStringBuilderForCollection.append('{');
        boolean z = true;
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (!z) {
                sbNewStringBuilderForCollection.append(", ");
            }
            z = false;
            sbNewStringBuilderForCollection.append(entry.getKey());
            sbNewStringBuilderForCollection.append('=');
            sbNewStringBuilderForCollection.append(entry.getValue());
        }
        sbNewStringBuilderForCollection.append('}');
        return sbNewStringBuilderForCollection.toString();
    }

    public static <K, V1, V2> Map<K, V2> transformEntries(Map<K, V1> map, EntryTransformer<? super K, ? super V1, V2> entryTransformer) {
        return new TransformedEntriesMap(map, entryTransformer);
    }

    public static <V2, K, V1> Map.Entry<K, V2> transformEntry(final EntryTransformer<? super K, ? super V1, V2> entryTransformer, final Map.Entry<K, V1> entry) {
        if (entryTransformer == null) {
            throw new NullPointerException();
        }
        if (entry != null) {
            return new AbstractMapEntry<K, V2>() { // from class: com.google.common.collect.Maps.12
                @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
                public K getKey() {
                    return (K) entry.getKey();
                }

                /* JADX WARN: Multi-variable type inference failed */
                @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
                public V2 getValue() {
                    return (V2) entryTransformer.transformEntry(entry.getKey(), entry.getValue());
                }
            };
        }
        throw new NullPointerException();
    }

    public static <K, V1, V2> Map<K, V2> transformValues(Map<K, V1> map, Function<? super V1, V2> function) {
        if (function != null) {
            return new TransformedEntriesMap(map, new AnonymousClass9(function));
        }
        throw new NullPointerException();
    }

    @CanIgnoreReturnValue
    public static <K, V> ImmutableMap<K, V> uniqueIndex(Iterable<V> iterable, Function<? super V, K> function) {
        return uniqueIndex(iterable.iterator(), function);
    }

    public static <K, V> BiMap<K, V> unmodifiableBiMap(BiMap<? extends K, ? extends V> biMap) {
        return new UnmodifiableBiMap(biMap, null);
    }

    public static <K, V> Map.Entry<K, V> unmodifiableEntry(Map.Entry<? extends K, ? extends V> entry) {
        if (entry != null) {
            return new AnonymousClass7(entry);
        }
        throw new NullPointerException();
    }

    public static <K, V> UnmodifiableIterator<Map.Entry<K, V>> unmodifiableEntryIterator(Iterator<Map.Entry<K, V>> it) {
        return new AnonymousClass8(it);
    }

    public static <K, V> Set<Map.Entry<K, V>> unmodifiableEntrySet(Set<Map.Entry<K, V>> set) {
        return new UnmodifiableEntrySet(Collections.unmodifiableSet(set));
    }

    public static <K, V> Map<K, V> unmodifiableMap(Map<K, ? extends V> map) {
        return map instanceof SortedMap ? Collections.unmodifiableSortedMap((SortedMap) map) : Collections.unmodifiableMap(map);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @GwtIncompatible
    public static <K, V> NavigableMap<K, V> unmodifiableNavigableMap(NavigableMap<K, ? extends V> navigableMap) {
        if (navigableMap != 0) {
            return navigableMap instanceof UnmodifiableNavigableMap ? navigableMap : new UnmodifiableNavigableMap(navigableMap);
        }
        throw new NullPointerException();
    }

    public static <K, V> Map.Entry<K, V> unmodifiableOrNull(Map.Entry<K, ? extends V> entry) {
        if (entry == null) {
            return null;
        }
        return new AnonymousClass7(entry);
    }

    public static <V> Function<Map.Entry<?, V>, V> valueFunction() {
        return EntryFunction.VALUE;
    }

    public static <K, V> Iterator<V> valueIterator(Iterator<Map.Entry<K, V>> it) {
        return new AnonymousClass2(it);
    }

    public static <V> V valueOrNull(Map.Entry<?, V> entry) {
        if (entry == null) {
            return null;
        }
        return entry.getValue();
    }

    public static <V> Predicate<Map.Entry<?, V>> valuePredicateOnEntries(Predicate<? super V> predicate) {
        return new Predicates.CompositionPredicate(predicate, EntryFunction.VALUE);
    }

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* JADX INFO: renamed from: com.google.common.collect.Maps$4, reason: invalid class name */
    public class AnonymousClass4<E> extends ForwardingSet<E> {
        public final /* synthetic */ Set val$set;

        public AnonymousClass4(Set set) {
            this.val$set = set;
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Queue
        public boolean add(E e2) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection
        public boolean addAll(Collection<? extends E> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public Set<E> delegate() {
            return this.val$set;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* JADX INFO: renamed from: com.google.common.collect.Maps$6, reason: invalid class name */
    public class AnonymousClass6<E> extends ForwardingNavigableSet<E> {
        public final /* synthetic */ NavigableSet val$set;

        public AnonymousClass6(NavigableSet navigableSet) {
            this.val$set = navigableSet;
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Queue
        public boolean add(E e2) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection
        public boolean addAll(Collection<? extends E> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public NavigableSet<E> descendingSet() {
            return new AnonymousClass6(super.descendingSet());
        }

        @Override // com.google.common.collect.ForwardingSortedSet, java.util.SortedSet
        public SortedSet<E> headSet(E e2) {
            return new AnonymousClass5(delegate().headSet(e2));
        }

        @Override // com.google.common.collect.ForwardingSortedSet, java.util.SortedSet
        public SortedSet<E> subSet(E e2, E e3) {
            return new AnonymousClass5(delegate().subSet(e2, e3));
        }

        @Override // com.google.common.collect.ForwardingSortedSet, java.util.SortedSet
        public SortedSet<E> tailSet(E e2) {
            return new AnonymousClass5(delegate().tailSet(e2));
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public NavigableSet<E> headSet(E e2, boolean z) {
            return new AnonymousClass6(delegate().headSet(e2, z));
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public NavigableSet<E> subSet(E e2, boolean z, E e3, boolean z2) {
            return new AnonymousClass6(delegate().subSet(e2, z, e3, z2));
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public NavigableSet<E> tailSet(E e2, boolean z) {
            return new AnonymousClass6(delegate().tailSet(e2, z));
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, com.google.common.collect.ForwardingSortedSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public NavigableSet<E> delegate() {
            return this.val$set;
        }
    }

    public static <K, V> SortedMap<K, V> asMap(SortedSet<K> sortedSet, Function<? super K, V> function) {
        return new SortedAsMapView(sortedSet, function);
    }

    public static <K, V> SortedMap<K, V> filterValues(SortedMap<K, V> sortedMap, Predicate<? super V> predicate) {
        return filterEntries((SortedMap) sortedMap, valuePredicateOnEntries(predicate));
    }

    public static <K, V> HashMap<K, V> newHashMap(Map<? extends K, ? extends V> map) {
        return new HashMap<>(map);
    }

    public static <K, V> LinkedHashMap<K, V> newLinkedHashMap(Map<? extends K, ? extends V> map) {
        return new LinkedHashMap<>(map);
    }

    public static <K, V> TreeMap<K, V> newTreeMap(SortedMap<K, ? extends V> sortedMap) {
        return new TreeMap<>((SortedMap) sortedMap);
    }

    public static <K, V1, V2> SortedMap<K, V2> transformEntries(SortedMap<K, V1> sortedMap, EntryTransformer<? super K, ? super V1, V2> entryTransformer) {
        return new TransformedEntriesSortedMap(sortedMap, entryTransformer);
    }

    @CanIgnoreReturnValue
    public static <K, V> ImmutableMap<K, V> uniqueIndex(Iterator<V> it, Function<? super V, K> function) {
        if (function == null) {
            throw new NullPointerException();
        }
        ImmutableMap.Builder builder = ImmutableMap.builder();
        while (it.hasNext()) {
            V next = it.next();
            builder.put(function.apply(next), next);
        }
        try {
            return builder.build();
        } catch (IllegalArgumentException e2) {
            throw new IllegalArgumentException(String.valueOf(e2.getMessage()).concat(". To index multiple values under a key, use Multimaps.index."));
        }
    }

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* JADX INFO: renamed from: com.google.common.collect.Maps$5, reason: invalid class name */
    public class AnonymousClass5<E> extends ForwardingSortedSet<E> {
        public final /* synthetic */ SortedSet val$set;

        public AnonymousClass5(SortedSet sortedSet) {
            this.val$set = sortedSet;
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Queue
        public boolean add(E e2) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection
        public boolean addAll(Collection<? extends E> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingSortedSet, java.util.SortedSet
        public SortedSet<E> headSet(E e2) {
            return new AnonymousClass5(delegate().headSet(e2));
        }

        @Override // com.google.common.collect.ForwardingSortedSet, java.util.SortedSet
        public SortedSet<E> subSet(E e2, E e3) {
            return new AnonymousClass5(delegate().subSet(e2, e3));
        }

        @Override // com.google.common.collect.ForwardingSortedSet, java.util.SortedSet
        public SortedSet<E> tailSet(E e2) {
            return new AnonymousClass5(delegate().tailSet(e2));
        }

        @Override // com.google.common.collect.ForwardingSortedSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public SortedSet<E> delegate() {
            return this.val$set;
        }
    }

    @GwtIncompatible
    public static <K, V> NavigableMap<K, V> asMap(NavigableSet<K> navigableSet, Function<? super K, V> function) {
        return new NavigableAsMapView(navigableSet, function);
    }

    public static <K, V> SortedMap<K, V> filterFiltered(FilteredEntrySortedMap<K, V> filteredEntrySortedMap, Predicate<? super Map.Entry<K, V>> predicate) {
        return new FilteredEntrySortedMap(filteredEntrySortedMap.sortedMap(), Predicates.and(filteredEntrySortedMap.predicate, predicate));
    }

    @GwtIncompatible
    public static <K, V> NavigableMap<K, V> filterValues(NavigableMap<K, V> navigableMap, Predicate<? super V> predicate) {
        return filterEntries((NavigableMap) navigableMap, valuePredicateOnEntries(predicate));
    }

    public static <K extends Enum<K>, V> EnumMap<K, V> newEnumMap(Map<K, ? extends V> map) {
        return new EnumMap<>(map);
    }

    public static <C, K extends C, V> TreeMap<K, V> newTreeMap(Comparator<C> comparator) {
        return new TreeMap<>(comparator);
    }

    @GwtIncompatible
    public static <K, V1, V2> NavigableMap<K, V2> transformEntries(NavigableMap<K, V1> navigableMap, EntryTransformer<? super K, ? super V1, V2> entryTransformer) {
        return new TransformedEntriesNavigableMap(navigableMap, entryTransformer);
    }

    public static <K, V> BiMap<K, V> filterValues(BiMap<K, V> biMap, Predicate<? super V> predicate) {
        return filterEntries((BiMap) biMap, valuePredicateOnEntries(predicate));
    }

    @GwtIncompatible
    public static <K, V1, V2> NavigableMap<K, V2> transformValues(NavigableMap<K, V1> navigableMap, Function<? super V1, V2> function) {
        if (function != null) {
            return new TransformedEntriesNavigableMap(navigableMap, new AnonymousClass9(function));
        }
        throw new NullPointerException();
    }

    @GwtIncompatible
    public static <K, V> NavigableMap<K, V> filterFiltered(FilteredEntryNavigableMap<K, V> filteredEntryNavigableMap, Predicate<? super Map.Entry<K, V>> predicate) {
        return new FilteredEntryNavigableMap(filteredEntryNavigableMap.unfiltered, Predicates.and(filteredEntryNavigableMap.entryPredicate, predicate));
    }

    public static <K, V> SortedMap<K, V> filterEntries(SortedMap<K, V> sortedMap, Predicate<? super Map.Entry<K, V>> predicate) {
        if (predicate != null) {
            if (sortedMap instanceof FilteredEntrySortedMap) {
                return filterFiltered((FilteredEntrySortedMap) sortedMap, (Predicate) predicate);
            }
            if (sortedMap != null) {
                return new FilteredEntrySortedMap(sortedMap, predicate);
            }
            throw new NullPointerException();
        }
        throw new NullPointerException();
    }

    public static <K, V> BiMap<K, V> filterFiltered(FilteredEntryBiMap<K, V> filteredEntryBiMap, Predicate<? super Map.Entry<K, V>> predicate) {
        return new FilteredEntryBiMap(filteredEntryBiMap.unfiltered(), Predicates.and(filteredEntryBiMap.predicate, predicate));
    }

    public static <K, V> SortedMap<K, V> filterKeys(SortedMap<K, V> sortedMap, Predicate<? super K> predicate) {
        return filterEntries((SortedMap) sortedMap, keyPredicateOnEntries(predicate));
    }

    public static <K, V> ImmutableMap<K, V> toMap(Iterable<K> iterable, Function<? super K, V> function) {
        return toMap(iterable.iterator(), function);
    }

    public static <K, V1, V2> SortedMap<K, V2> transformValues(SortedMap<K, V1> sortedMap, Function<? super V1, V2> function) {
        if (function != null) {
            return new TransformedEntriesSortedMap(sortedMap, new AnonymousClass9(function));
        }
        throw new NullPointerException();
    }

    public static <K, V> MapDifference<K, V> difference(Map<? extends K, ? extends V> map, Map<? extends K, ? extends V> map2) {
        if (map instanceof SortedMap) {
            return difference((SortedMap) map, (Map) map2);
        }
        return difference(map, map2, Equivalence.Equals.INSTANCE);
    }

    @GwtIncompatible
    public static <K, V> NavigableMap<K, V> filterKeys(NavigableMap<K, V> navigableMap, Predicate<? super K> predicate) {
        return filterEntries((NavigableMap) navigableMap, keyPredicateOnEntries(predicate));
    }

    public static <K, V> BiMap<K, V> filterKeys(BiMap<K, V> biMap, Predicate<? super K> predicate) {
        if (predicate != null) {
            return filterEntries((BiMap) biMap, keyPredicateOnEntries(predicate));
        }
        throw new NullPointerException();
    }

    @GwtIncompatible
    public static <K, V> NavigableMap<K, V> filterEntries(NavigableMap<K, V> navigableMap, Predicate<? super Map.Entry<K, V>> predicate) {
        if (predicate != null) {
            if (navigableMap instanceof FilteredEntryNavigableMap) {
                return filterFiltered((FilteredEntryNavigableMap) navigableMap, predicate);
            }
            if (navigableMap != null) {
                return new FilteredEntryNavigableMap(navigableMap, predicate);
            }
            throw new NullPointerException();
        }
        throw new NullPointerException();
    }

    public static <K, V> SortedMapDifference<K, V> difference(SortedMap<K, ? extends V> sortedMap, Map<? extends K, ? extends V> map) {
        if (sortedMap == null) {
            throw new NullPointerException();
        }
        if (map != null) {
            Comparator comparatorOrNaturalOrder = orNaturalOrder(sortedMap.comparator());
            TreeMap treeMap = new TreeMap(comparatorOrNaturalOrder);
            TreeMap treeMap2 = new TreeMap(comparatorOrNaturalOrder);
            treeMap2.putAll(map);
            TreeMap treeMap3 = new TreeMap(comparatorOrNaturalOrder);
            TreeMap treeMap4 = new TreeMap(comparatorOrNaturalOrder);
            doDifference(sortedMap, map, Equivalence.Equals.INSTANCE, treeMap, treeMap2, treeMap3, treeMap4);
            return new SortedMapDifferenceImpl(treeMap, treeMap2, treeMap3, treeMap4);
        }
        throw new NullPointerException();
    }

    public static <K, V> BiMap<K, V> filterEntries(BiMap<K, V> biMap, Predicate<? super Map.Entry<K, V>> predicate) {
        if (biMap == null) {
            throw new NullPointerException();
        }
        if (predicate != null) {
            if (biMap instanceof FilteredEntryBiMap) {
                return filterFiltered((FilteredEntryBiMap) biMap, (Predicate) predicate);
            }
            return new FilteredEntryBiMap(biMap, predicate);
        }
        throw new NullPointerException();
    }
}
