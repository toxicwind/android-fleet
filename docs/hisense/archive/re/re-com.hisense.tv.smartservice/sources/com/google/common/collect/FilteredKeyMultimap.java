package com.google.common.collect;

import b.a.a.a.a;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible
public class FilteredKeyMultimap<K, V> extends AbstractMultimap<K, V> implements FilteredMultimap<K, V> {
    public final Predicate<? super K> keyPredicate;
    public final Multimap<K, V> unfiltered;

    static class AddRejectingList<K, V> extends ForwardingList<V> {
        public final K key;

        public AddRejectingList(K k) {
            this.key = k;
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Queue
        public boolean add(V v) {
            add(0, v);
            return true;
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection
        public boolean addAll(Collection<? extends V> collection) {
            addAll(0, collection);
            return true;
        }

        @Override // com.google.common.collect.ForwardingList, java.util.List
        public void add(int i, V v) {
            Preconditions.checkPositionIndex(i, 0, FirebaseAnalytics.Param.INDEX);
            String strValueOf = String.valueOf(this.key);
            throw new IllegalArgumentException(a.a(strValueOf.length() + 32, "Key does not satisfy predicate: ", strValueOf));
        }

        @Override // com.google.common.collect.ForwardingList, java.util.List
        @CanIgnoreReturnValue
        public boolean addAll(int i, Collection<? extends V> collection) {
            if (collection == null) {
                throw new NullPointerException();
            }
            Preconditions.checkPositionIndex(i, 0, FirebaseAnalytics.Param.INDEX);
            String strValueOf = String.valueOf(this.key);
            throw new IllegalArgumentException(a.a(strValueOf.length() + 32, "Key does not satisfy predicate: ", strValueOf));
        }

        @Override // com.google.common.collect.ForwardingList, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public List<V> delegate() {
            return Collections.emptyList();
        }
    }

    class Entries extends ForwardingCollection<Map.Entry<K, V>> {
        public Entries() {
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            if (FilteredKeyMultimap.this.unfiltered.containsKey(entry.getKey()) && FilteredKeyMultimap.this.keyPredicate.apply((Object) entry.getKey())) {
                return FilteredKeyMultimap.this.unfiltered.remove(entry.getKey(), entry.getValue());
            }
            return false;
        }

        @Override // com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public Collection<Map.Entry<K, V>> delegate() {
            return Collections2.filter(FilteredKeyMultimap.this.unfiltered.entries(), FilteredKeyMultimap.this.entryPredicate());
        }
    }

    public FilteredKeyMultimap(Multimap<K, V> multimap, Predicate<? super K> predicate) {
        if (multimap == null) {
            throw new NullPointerException();
        }
        this.unfiltered = multimap;
        if (predicate == null) {
            throw new NullPointerException();
        }
        this.keyPredicate = predicate;
    }

    @Override // com.google.common.collect.Multimap
    public void clear() {
        keySet().clear();
    }

    @Override // com.google.common.collect.Multimap
    public boolean containsKey(Object obj) {
        if (this.unfiltered.containsKey(obj)) {
            return this.keyPredicate.apply(obj);
        }
        return false;
    }

    @Override // com.google.common.collect.AbstractMultimap
    public Map<K, Collection<V>> createAsMap() {
        return Maps.filterKeys(this.unfiltered.asMap(), this.keyPredicate);
    }

    @Override // com.google.common.collect.AbstractMultimap
    public Collection<Map.Entry<K, V>> createEntries() {
        return new Entries();
    }

    @Override // com.google.common.collect.AbstractMultimap
    public Set<K> createKeySet() {
        return Sets.filter(this.unfiltered.keySet(), this.keyPredicate);
    }

    @Override // com.google.common.collect.AbstractMultimap
    public Multiset<K> createKeys() {
        return Multisets.filter(this.unfiltered.keys(), this.keyPredicate);
    }

    @Override // com.google.common.collect.AbstractMultimap
    public Collection<V> createValues() {
        return new FilteredMultimapValues(this);
    }

    @Override // com.google.common.collect.AbstractMultimap
    public Iterator<Map.Entry<K, V>> entryIterator() {
        throw new AssertionError("should never be called");
    }

    @Override // com.google.common.collect.FilteredMultimap
    public Predicate<? super Map.Entry<K, V>> entryPredicate() {
        return Maps.keyPredicateOnEntries(this.keyPredicate);
    }

    @Override // com.google.common.collect.Multimap
    public Collection<V> get(K k) {
        if (this.keyPredicate.apply(k)) {
            return this.unfiltered.get(k);
        }
        return this.unfiltered instanceof SetMultimap ? new AddRejectingSet(k) : new AddRejectingList(k);
    }

    @Override // com.google.common.collect.Multimap
    public Collection<V> removeAll(Object obj) {
        return containsKey(obj) ? this.unfiltered.removeAll(obj) : unmodifiableEmptyCollection();
    }

    @Override // com.google.common.collect.Multimap
    public int size() {
        Iterator<Collection<V>> it = asMap().values().iterator();
        int size = 0;
        while (it.hasNext()) {
            size += it.next().size();
        }
        return size;
    }

    public Multimap<K, V> unfiltered() {
        return this.unfiltered;
    }

    public Collection<V> unmodifiableEmptyCollection() {
        return this.unfiltered instanceof SetMultimap ? ImmutableSet.of() : ImmutableList.of();
    }

    static class AddRejectingSet<K, V> extends ForwardingSet<V> {
        public final K key;

        public AddRejectingSet(K k) {
            this.key = k;
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Queue
        public boolean add(V v) {
            String strValueOf = String.valueOf(this.key);
            throw new IllegalArgumentException(a.a(strValueOf.length() + 32, "Key does not satisfy predicate: ", strValueOf));
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection
        public boolean addAll(Collection<? extends V> collection) {
            if (collection == null) {
                throw new NullPointerException();
            }
            String strValueOf = String.valueOf(this.key);
            throw new IllegalArgumentException(a.a(strValueOf.length() + 32, "Key does not satisfy predicate: ", strValueOf));
        }

        @Override // com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public Set<V> delegate() {
            return Collections.emptySet();
        }
    }
}
