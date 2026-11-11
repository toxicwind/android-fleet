package j$.util;

import j$.util.function.Consumer$CC;
import j$.util.stream.StreamSupport;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.ListIterator;
import java.util.RandomAccess;
import java.util.SortedMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/* JADX INFO: loaded from: classes3.dex */
public class DesugarCollections {
    public static <T> java.util.Collection<T> unmodifiableCollection(java.util.Collection<? extends T> collection) {
        return new UnmodifiableCollection(collection);
    }

    static class UnmodifiableCollection implements java.util.Collection, Serializable, Collection {
        private static final long serialVersionUID = 1820017752578914078L;
        final java.util.Collection c;

        @Override // java.util.Collection
        public /* synthetic */ Stream parallelStream() {
            return j$.util.stream.Stream.Wrapper.convert(parallelStream());
        }

        @Override // java.util.Collection, java.lang.Iterable
        public /* synthetic */ java.util.Spliterator spliterator() {
            return Spliterator.Wrapper.convert(spliterator());
        }

        @Override // java.util.Collection
        public /* synthetic */ Stream stream() {
            return j$.util.stream.Stream.Wrapper.convert(stream());
        }

        @Override // java.util.Collection, j$.util.Collection
        public /* synthetic */ Object[] toArray(IntFunction intFunction) {
            return toArray((Object[]) intFunction.apply(0));
        }

        UnmodifiableCollection(java.util.Collection collection) {
            collection.getClass();
            this.c = collection;
        }

        @Override // java.util.Collection
        public int size() {
            return this.c.size();
        }

        @Override // java.util.Collection
        public boolean isEmpty() {
            return this.c.isEmpty();
        }

        @Override // java.util.Collection
        public boolean contains(Object obj) {
            return this.c.contains(obj);
        }

        @Override // java.util.Collection
        public Object[] toArray() {
            return this.c.toArray();
        }

        @Override // java.util.Collection
        public Object[] toArray(Object[] objArr) {
            return this.c.toArray(objArr);
        }

        public String toString() {
            return this.c.toString();
        }

        /* JADX INFO: renamed from: j$.util.DesugarCollections$UnmodifiableCollection$1, reason: invalid class name */
        class AnonymousClass1 implements java.util.Iterator, Iterator {
            private final java.util.Iterator i;

            AnonymousClass1() {
                this.i = UnmodifiableCollection.this.c.iterator();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.i.hasNext();
            }

            @Override // java.util.Iterator
            public Object next() {
                return this.i.next();
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override // java.util.Iterator, j$.util.Iterator
            public void forEachRemaining(Consumer consumer) {
                Iterator.EL.forEachRemaining(this.i, consumer);
            }
        }

        @Override // java.util.Collection, java.lang.Iterable
        public java.util.Iterator iterator() {
            return new AnonymousClass1();
        }

        @Override // java.util.Collection
        public boolean add(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public boolean remove(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public boolean containsAll(java.util.Collection collection) {
            return this.c.containsAll(collection);
        }

        @Override // java.util.Collection
        public boolean addAll(java.util.Collection collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public boolean removeAll(java.util.Collection collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public boolean retainAll(java.util.Collection collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public void clear() {
            throw new UnsupportedOperationException();
        }

        @Override // java.lang.Iterable, j$.util.Collection, j$.lang.Iterable
        public void forEach(Consumer consumer) {
            Collection.EL.forEach(this.c, consumer);
        }

        @Override // java.util.Collection, j$.util.Collection
        public boolean removeIf(Predicate predicate) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection, java.lang.Iterable, j$.util.Collection, j$.lang.Iterable
        public Spliterator spliterator() {
            return Collection.EL.spliterator(this.c);
        }

        @Override // java.util.Collection, j$.util.Collection
        public j$.util.stream.Stream stream() {
            return Collection.EL.stream(this.c);
        }

        @Override // java.util.Collection, j$.util.Collection
        public j$.util.stream.Stream parallelStream() {
            return Collection.EL.parallelStream(this.c);
        }
    }

    public static <T> java.util.Set<T> unmodifiableSet(java.util.Set<? extends T> set) {
        return new UnmodifiableSet(set);
    }

    static class UnmodifiableSet extends UnmodifiableCollection implements java.util.Set, Serializable, Set {
        private static final long serialVersionUID = -9215047833775013803L;

        UnmodifiableSet(java.util.Set set) {
            super(set);
        }

        @Override // java.util.Collection, java.util.Set
        public boolean equals(Object obj) {
            return obj == this || this.c.equals(obj);
        }

        @Override // java.util.Collection, java.util.Set
        public int hashCode() {
            return this.c.hashCode();
        }
    }

    public static <T> java.util.SortedSet<T> unmodifiableSortedSet(java.util.SortedSet<T> sortedSet) {
        return new UnmodifiableSortedSet(sortedSet);
    }

    static class UnmodifiableSortedSet extends UnmodifiableSet implements java.util.SortedSet, Serializable, SortedSet {
        private static final long serialVersionUID = -4929149591599911165L;
        private final java.util.SortedSet ss;

        UnmodifiableSortedSet(java.util.SortedSet sortedSet) {
            super(sortedSet);
            this.ss = sortedSet;
        }

        @Override // java.util.SortedSet
        public java.util.Comparator comparator() {
            return this.ss.comparator();
        }

        @Override // java.util.SortedSet
        public java.util.SortedSet subSet(Object obj, Object obj2) {
            return new UnmodifiableSortedSet(this.ss.subSet(obj, obj2));
        }

        @Override // java.util.SortedSet
        public java.util.SortedSet headSet(Object obj) {
            return new UnmodifiableSortedSet(this.ss.headSet(obj));
        }

        @Override // java.util.SortedSet
        public java.util.SortedSet tailSet(Object obj) {
            return new UnmodifiableSortedSet(this.ss.tailSet(obj));
        }

        @Override // java.util.SortedSet
        public Object first() {
            return this.ss.first();
        }

        @Override // java.util.SortedSet
        public Object last() {
            return this.ss.last();
        }
    }

    public static <T> java.util.List<T> unmodifiableList(java.util.List<? extends T> list) {
        if (list instanceof RandomAccess) {
            return new UnmodifiableRandomAccessList(list);
        }
        return new UnmodifiableList(list);
    }

    static class UnmodifiableList extends UnmodifiableCollection implements java.util.List, List {
        private static final long serialVersionUID = -283967356065247728L;
        final java.util.List list;

        UnmodifiableList(java.util.List list) {
            super(list);
            this.list = list;
        }

        @Override // java.util.Collection, java.util.List
        public boolean equals(Object obj) {
            return obj == this || this.list.equals(obj);
        }

        @Override // java.util.Collection, java.util.List
        public int hashCode() {
            return this.list.hashCode();
        }

        @Override // java.util.List
        public Object get(int i) {
            return this.list.get(i);
        }

        @Override // java.util.List
        public Object set(int i, Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.List
        public void add(int i, Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.List
        public Object remove(int i) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.List
        public int indexOf(Object obj) {
            return this.list.indexOf(obj);
        }

        @Override // java.util.List
        public int lastIndexOf(Object obj) {
            return this.list.lastIndexOf(obj);
        }

        @Override // java.util.List
        public boolean addAll(int i, java.util.Collection collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.List, j$.util.List
        public void replaceAll(UnaryOperator unaryOperator) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.List, j$.util.List
        public void sort(java.util.Comparator comparator) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.List
        public ListIterator listIterator() {
            return listIterator(0);
        }

        /* JADX INFO: renamed from: j$.util.DesugarCollections$UnmodifiableList$1, reason: invalid class name */
        class AnonymousClass1 implements ListIterator, Iterator {
            private final ListIterator i;
            final /* synthetic */ int val$index;

            AnonymousClass1(int i) {
                this.val$index = i;
                this.i = UnmodifiableList.this.list.listIterator(i);
            }

            @Override // java.util.ListIterator, java.util.Iterator
            public boolean hasNext() {
                return this.i.hasNext();
            }

            @Override // java.util.ListIterator, java.util.Iterator
            public Object next() {
                return this.i.next();
            }

            @Override // java.util.ListIterator
            public boolean hasPrevious() {
                return this.i.hasPrevious();
            }

            @Override // java.util.ListIterator
            public Object previous() {
                return this.i.previous();
            }

            @Override // java.util.ListIterator
            public int nextIndex() {
                return this.i.nextIndex();
            }

            @Override // java.util.ListIterator
            public int previousIndex() {
                return this.i.previousIndex();
            }

            @Override // java.util.ListIterator, java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override // java.util.ListIterator
            public void set(Object obj) {
                throw new UnsupportedOperationException();
            }

            @Override // java.util.ListIterator
            public void add(Object obj) {
                throw new UnsupportedOperationException();
            }

            @Override // java.util.Iterator, j$.util.Iterator
            public void forEachRemaining(Consumer consumer) {
                Iterator.EL.forEachRemaining(this.i, consumer);
            }
        }

        @Override // java.util.List
        public ListIterator listIterator(int i) {
            return new AnonymousClass1(i);
        }

        @Override // java.util.List
        public java.util.List subList(int i, int i2) {
            return new UnmodifiableList(this.list.subList(i, i2));
        }

        private Object readResolve() {
            java.util.List list = this.list;
            return list instanceof RandomAccess ? new UnmodifiableRandomAccessList(list) : this;
        }
    }

    static class UnmodifiableRandomAccessList extends UnmodifiableList implements RandomAccess {
        private static final long serialVersionUID = -2542308836966382001L;

        UnmodifiableRandomAccessList(java.util.List list) {
            super(list);
        }

        @Override // j$.util.DesugarCollections.UnmodifiableList, java.util.List
        public java.util.List subList(int i, int i2) {
            return new UnmodifiableRandomAccessList(this.list.subList(i, i2));
        }

        private Object writeReplace() {
            return new UnmodifiableList(this.list);
        }
    }

    public static <K, V> java.util.Map<K, V> unmodifiableMap(java.util.Map<? extends K, ? extends V> map) {
        return new UnmodifiableMap(map);
    }

    private static class UnmodifiableMap implements java.util.Map, Serializable, Map {
        private static final long serialVersionUID = -1034234728574286014L;
        private transient java.util.Set entrySet;
        private transient java.util.Set keySet;
        private final java.util.Map m;
        private transient java.util.Collection values;

        UnmodifiableMap(java.util.Map map) {
            map.getClass();
            this.m = map;
        }

        @Override // java.util.Map
        public int size() {
            return this.m.size();
        }

        @Override // java.util.Map
        public boolean isEmpty() {
            return this.m.isEmpty();
        }

        @Override // java.util.Map
        public boolean containsKey(Object obj) {
            return this.m.containsKey(obj);
        }

        @Override // java.util.Map
        public boolean containsValue(Object obj) {
            return this.m.containsValue(obj);
        }

        @Override // java.util.Map
        public Object get(Object obj) {
            return this.m.get(obj);
        }

        @Override // java.util.Map
        public Object put(Object obj, Object obj2) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map
        public Object remove(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map
        public void putAll(java.util.Map map) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map
        public void clear() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map
        public java.util.Set keySet() {
            if (this.keySet == null) {
                this.keySet = DesugarCollections.unmodifiableSet(this.m.keySet());
            }
            return this.keySet;
        }

        @Override // java.util.Map
        public java.util.Set entrySet() {
            if (this.entrySet == null) {
                this.entrySet = new UnmodifiableEntrySet(this.m.entrySet());
            }
            return this.entrySet;
        }

        @Override // java.util.Map
        public java.util.Collection values() {
            if (this.values == null) {
                this.values = DesugarCollections.unmodifiableCollection(this.m.values());
            }
            return this.values;
        }

        @Override // java.util.Map
        public boolean equals(Object obj) {
            return obj == this || this.m.equals(obj);
        }

        @Override // java.util.Map
        public int hashCode() {
            return this.m.hashCode();
        }

        public String toString() {
            return this.m.toString();
        }

        @Override // java.util.Map, j$.util.Map
        public Object getOrDefault(Object obj, Object obj2) {
            return Map.EL.getOrDefault(this.m, obj, obj2);
        }

        @Override // java.util.Map, j$.util.Map
        public void forEach(BiConsumer biConsumer) {
            Map.EL.forEach(this.m, biConsumer);
        }

        @Override // java.util.Map, j$.util.Map
        public void replaceAll(BiFunction biFunction) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map, j$.util.Map
        public Object putIfAbsent(Object obj, Object obj2) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map, j$.util.Map
        public boolean remove(Object obj, Object obj2) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map, j$.util.Map
        public boolean replace(Object obj, Object obj2, Object obj3) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map, j$.util.Map
        public Object replace(Object obj, Object obj2) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map, j$.util.Map
        public Object computeIfAbsent(Object obj, Function function) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map, j$.util.Map
        public Object computeIfPresent(Object obj, BiFunction biFunction) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map, j$.util.Map
        public Object compute(Object obj, BiFunction biFunction) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map, j$.util.Map
        public Object merge(Object obj, Object obj2, BiFunction biFunction) {
            throw new UnsupportedOperationException();
        }

        static class UnmodifiableEntrySet extends UnmodifiableSet {
            private static final long serialVersionUID = 7854390611657943733L;

            UnmodifiableEntrySet(java.util.Set set) {
                super(set);
            }

            static Consumer entryConsumer(final Consumer consumer) {
                return new Consumer() { // from class: j$.util.DesugarCollections.UnmodifiableMap.UnmodifiableEntrySet.0
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        UnmodifiableEntrySet.lambda$entryConsumer$0(consumer, (java.util.Map.Entry) obj);
                    }

                    public /* synthetic */ Consumer andThen(Consumer consumer2) {
                        return Consumer$CC.$default$andThen(this, consumer2);
                    }
                };
            }

            static /* synthetic */ void lambda$entryConsumer$0(Consumer consumer, java.util.Map.Entry entry) {
                consumer.accept(new UnmodifiableEntry(entry));
            }

            @Override // j$.util.DesugarCollections.UnmodifiableCollection, java.lang.Iterable, j$.util.Collection, j$.lang.Iterable
            public void forEach(Consumer consumer) {
                Objects.requireNonNull(consumer);
                Collection.EL.forEach(this.c, entryConsumer(consumer));
            }

            static final class UnmodifiableEntrySetSpliterator implements Spliterator {
                final Spliterator s;

                UnmodifiableEntrySetSpliterator(Spliterator spliterator) {
                    this.s = spliterator;
                }

                @Override // j$.util.Spliterator
                public boolean tryAdvance(Consumer consumer) {
                    Objects.requireNonNull(consumer);
                    return this.s.tryAdvance(UnmodifiableEntrySet.entryConsumer(consumer));
                }

                @Override // j$.util.Spliterator
                public void forEachRemaining(Consumer consumer) {
                    Objects.requireNonNull(consumer);
                    this.s.forEachRemaining(UnmodifiableEntrySet.entryConsumer(consumer));
                }

                @Override // j$.util.Spliterator
                public Spliterator trySplit() {
                    Spliterator spliteratorTrySplit = this.s.trySplit();
                    if (spliteratorTrySplit == null) {
                        return null;
                    }
                    return new UnmodifiableEntrySetSpliterator(spliteratorTrySplit);
                }

                @Override // j$.util.Spliterator
                public long estimateSize() {
                    return this.s.estimateSize();
                }

                @Override // j$.util.Spliterator
                public long getExactSizeIfKnown() {
                    return this.s.getExactSizeIfKnown();
                }

                @Override // j$.util.Spliterator
                public int characteristics() {
                    return this.s.characteristics();
                }

                @Override // j$.util.Spliterator
                public boolean hasCharacteristics(int i) {
                    return this.s.hasCharacteristics(i);
                }

                @Override // j$.util.Spliterator
                public java.util.Comparator getComparator() {
                    return this.s.getComparator();
                }
            }

            @Override // j$.util.DesugarCollections.UnmodifiableCollection, java.util.Collection, java.lang.Iterable, j$.util.Collection, j$.lang.Iterable
            public Spliterator spliterator() {
                return new UnmodifiableEntrySetSpliterator(Collection.EL.spliterator(this.c));
            }

            @Override // j$.util.DesugarCollections.UnmodifiableCollection, java.util.Collection, j$.util.Collection
            public j$.util.stream.Stream stream() {
                return StreamSupport.stream(spliterator(), false);
            }

            @Override // j$.util.DesugarCollections.UnmodifiableCollection, java.util.Collection, j$.util.Collection
            public j$.util.stream.Stream parallelStream() {
                return StreamSupport.stream(spliterator(), true);
            }

            /* JADX INFO: renamed from: j$.util.DesugarCollections$UnmodifiableMap$UnmodifiableEntrySet$1, reason: invalid class name */
            class AnonymousClass1 implements java.util.Iterator, Iterator {
                private final java.util.Iterator i;

                AnonymousClass1() {
                    this.i = UnmodifiableEntrySet.this.c.iterator();
                }

                @Override // java.util.Iterator
                public boolean hasNext() {
                    return this.i.hasNext();
                }

                @Override // java.util.Iterator
                public java.util.Map.Entry next() {
                    return new UnmodifiableEntry((java.util.Map.Entry) this.i.next());
                }

                @Override // java.util.Iterator
                public void remove() {
                    throw new UnsupportedOperationException();
                }

                @Override // java.util.Iterator, j$.util.Iterator
                public void forEachRemaining(Consumer consumer) {
                    Iterator.EL.forEachRemaining(this.i, UnmodifiableEntrySet.entryConsumer(consumer));
                }
            }

            @Override // j$.util.DesugarCollections.UnmodifiableCollection, java.util.Collection, java.lang.Iterable
            public java.util.Iterator iterator() {
                return new AnonymousClass1();
            }

            @Override // j$.util.DesugarCollections.UnmodifiableCollection, java.util.Collection
            public Object[] toArray() {
                Object[] array = this.c.toArray();
                for (int i = 0; i < array.length; i++) {
                    array[i] = new UnmodifiableEntry((java.util.Map.Entry) array[i]);
                }
                return array;
            }

            @Override // j$.util.DesugarCollections.UnmodifiableCollection, java.util.Collection
            public Object[] toArray(Object[] objArr) {
                Object[] array = this.c.toArray(objArr.length == 0 ? objArr : Arrays.copyOf(objArr, 0));
                for (int i = 0; i < array.length; i++) {
                    array[i] = new UnmodifiableEntry((java.util.Map.Entry) array[i]);
                }
                if (array.length > objArr.length) {
                    return array;
                }
                System.arraycopy(array, 0, objArr, 0, array.length);
                if (objArr.length > array.length) {
                    objArr[array.length] = null;
                }
                return objArr;
            }

            @Override // j$.util.DesugarCollections.UnmodifiableCollection, java.util.Collection
            public boolean contains(Object obj) {
                if (obj instanceof java.util.Map.Entry) {
                    return this.c.contains(new UnmodifiableEntry((java.util.Map.Entry) obj));
                }
                return false;
            }

            @Override // j$.util.DesugarCollections.UnmodifiableCollection, java.util.Collection
            public boolean containsAll(java.util.Collection collection) {
                java.util.Iterator it = collection.iterator();
                while (it.hasNext()) {
                    if (!contains(it.next())) {
                        return false;
                    }
                }
                return true;
            }

            @Override // j$.util.DesugarCollections.UnmodifiableSet, java.util.Collection, java.util.Set
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof java.util.Set)) {
                    return false;
                }
                java.util.Set set = (java.util.Set) obj;
                if (set.size() != this.c.size()) {
                    return false;
                }
                return containsAll(set);
            }

            private static class UnmodifiableEntry implements java.util.Map.Entry {
                private java.util.Map.Entry e;

                UnmodifiableEntry(java.util.Map.Entry entry) {
                    this.e = (java.util.Map.Entry) Objects.requireNonNull(entry);
                }

                @Override // java.util.Map.Entry
                public Object getKey() {
                    return this.e.getKey();
                }

                @Override // java.util.Map.Entry
                public Object getValue() {
                    return this.e.getValue();
                }

                @Override // java.util.Map.Entry
                public Object setValue(Object obj) {
                    throw new UnsupportedOperationException();
                }

                @Override // java.util.Map.Entry
                public int hashCode() {
                    return this.e.hashCode();
                }

                @Override // java.util.Map.Entry
                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof java.util.Map.Entry)) {
                        return false;
                    }
                    java.util.Map.Entry entry = (java.util.Map.Entry) obj;
                    return DesugarCollections.eq(this.e.getKey(), entry.getKey()) && DesugarCollections.eq(this.e.getValue(), entry.getValue());
                }

                public String toString() {
                    return this.e.toString();
                }
            }
        }
    }

    public static <K, V> SortedMap<K, V> unmodifiableSortedMap(SortedMap<K, ? extends V> sortedMap) {
        return new UnmodifiableSortedMap(sortedMap);
    }

    static class UnmodifiableSortedMap extends UnmodifiableMap implements SortedMap, Serializable, Map {
        private static final long serialVersionUID = -8806743815996713206L;
        private final SortedMap sm;

        UnmodifiableSortedMap(SortedMap sortedMap) {
            super(sortedMap);
            this.sm = sortedMap;
        }

        @Override // java.util.SortedMap
        public java.util.Comparator comparator() {
            return this.sm.comparator();
        }

        @Override // java.util.SortedMap
        public SortedMap subMap(Object obj, Object obj2) {
            return new UnmodifiableSortedMap(this.sm.subMap(obj, obj2));
        }

        @Override // java.util.SortedMap
        public SortedMap headMap(Object obj) {
            return new UnmodifiableSortedMap(this.sm.headMap(obj));
        }

        @Override // java.util.SortedMap
        public SortedMap tailMap(Object obj) {
            return new UnmodifiableSortedMap(this.sm.tailMap(obj));
        }

        @Override // java.util.SortedMap
        public Object firstKey() {
            return this.sm.firstKey();
        }

        @Override // java.util.SortedMap
        public Object lastKey() {
            return this.sm.lastKey();
        }
    }

    public static <T> java.util.Collection<T> synchronizedCollection(java.util.Collection<T> collection) {
        return new SynchronizedCollection(collection);
    }

    static class SynchronizedCollection implements java.util.Collection, Serializable, Collection {
        private static final long serialVersionUID = 3053995032091335093L;
        final java.util.Collection c;
        final Object mutex;

        @Override // java.util.Collection
        public /* synthetic */ Stream parallelStream() {
            return j$.util.stream.Stream.Wrapper.convert(parallelStream());
        }

        @Override // java.util.Collection, java.lang.Iterable
        public /* synthetic */ java.util.Spliterator spliterator() {
            return Spliterator.Wrapper.convert(spliterator());
        }

        @Override // java.util.Collection
        public /* synthetic */ Stream stream() {
            return j$.util.stream.Stream.Wrapper.convert(stream());
        }

        @Override // java.util.Collection, j$.util.Collection
        public /* synthetic */ Object[] toArray(IntFunction intFunction) {
            return toArray((Object[]) intFunction.apply(0));
        }

        SynchronizedCollection(java.util.Collection collection) {
            this.c = (java.util.Collection) Objects.requireNonNull(collection);
            this.mutex = this;
        }

        SynchronizedCollection(java.util.Collection collection, Object obj) {
            this.c = (java.util.Collection) Objects.requireNonNull(collection);
            this.mutex = Objects.requireNonNull(obj);
        }

        @Override // java.util.Collection
        public int size() {
            int size;
            synchronized (this.mutex) {
                size = this.c.size();
            }
            return size;
        }

        @Override // java.util.Collection
        public boolean isEmpty() {
            boolean zIsEmpty;
            synchronized (this.mutex) {
                zIsEmpty = this.c.isEmpty();
            }
            return zIsEmpty;
        }

        @Override // java.util.Collection
        public boolean contains(Object obj) {
            boolean zContains;
            synchronized (this.mutex) {
                zContains = this.c.contains(obj);
            }
            return zContains;
        }

        @Override // java.util.Collection
        public Object[] toArray() {
            Object[] array;
            synchronized (this.mutex) {
                array = this.c.toArray();
            }
            return array;
        }

        @Override // java.util.Collection
        public Object[] toArray(Object[] objArr) {
            Object[] array;
            synchronized (this.mutex) {
                array = this.c.toArray(objArr);
            }
            return array;
        }

        @Override // java.util.Collection, java.lang.Iterable
        public java.util.Iterator iterator() {
            return this.c.iterator();
        }

        @Override // java.util.Collection
        public boolean add(Object obj) {
            boolean zAdd;
            synchronized (this.mutex) {
                zAdd = this.c.add(obj);
            }
            return zAdd;
        }

        @Override // java.util.Collection
        public boolean remove(Object obj) {
            boolean zRemove;
            synchronized (this.mutex) {
                zRemove = this.c.remove(obj);
            }
            return zRemove;
        }

        @Override // java.util.Collection
        public boolean containsAll(java.util.Collection collection) {
            boolean zContainsAll;
            synchronized (this.mutex) {
                zContainsAll = this.c.containsAll(collection);
            }
            return zContainsAll;
        }

        @Override // java.util.Collection
        public boolean addAll(java.util.Collection collection) {
            boolean zAddAll;
            synchronized (this.mutex) {
                zAddAll = this.c.addAll(collection);
            }
            return zAddAll;
        }

        @Override // java.util.Collection
        public boolean removeAll(java.util.Collection collection) {
            boolean zRemoveAll;
            synchronized (this.mutex) {
                zRemoveAll = this.c.removeAll(collection);
            }
            return zRemoveAll;
        }

        @Override // java.util.Collection
        public boolean retainAll(java.util.Collection collection) {
            boolean zRetainAll;
            synchronized (this.mutex) {
                zRetainAll = this.c.retainAll(collection);
            }
            return zRetainAll;
        }

        @Override // java.util.Collection
        public void clear() {
            synchronized (this.mutex) {
                this.c.clear();
            }
        }

        public String toString() {
            String string;
            synchronized (this.mutex) {
                string = this.c.toString();
            }
            return string;
        }

        @Override // java.lang.Iterable, j$.util.Collection, j$.lang.Iterable
        public void forEach(Consumer consumer) {
            synchronized (this.mutex) {
                Collection.EL.forEach(this.c, consumer);
            }
        }

        @Override // java.util.Collection, j$.util.Collection
        public boolean removeIf(Predicate predicate) {
            boolean zRemoveIf;
            synchronized (this.mutex) {
                zRemoveIf = Collection.EL.removeIf(this.c, predicate);
            }
            return zRemoveIf;
        }

        @Override // java.util.Collection, java.lang.Iterable, j$.util.Collection, j$.lang.Iterable
        public Spliterator spliterator() {
            return Collection.EL.spliterator(this.c);
        }

        @Override // java.util.Collection, j$.util.Collection
        public j$.util.stream.Stream stream() {
            return Collection.EL.stream(this.c);
        }

        @Override // java.util.Collection, j$.util.Collection
        public j$.util.stream.Stream parallelStream() {
            return Collection.EL.parallelStream(this.c);
        }

        private void writeObject(ObjectOutputStream objectOutputStream) {
            synchronized (this.mutex) {
                objectOutputStream.defaultWriteObject();
            }
        }
    }

    public static <T> java.util.Set<T> synchronizedSet(java.util.Set<T> set) {
        return new SynchronizedSet(set);
    }

    static class SynchronizedSet extends SynchronizedCollection implements java.util.Set, Set {
        private static final long serialVersionUID = 487447009682186044L;

        SynchronizedSet(java.util.Set set) {
            super(set);
        }

        SynchronizedSet(java.util.Set set, Object obj) {
            super(set, obj);
        }

        @Override // java.util.Collection, java.util.Set
        public boolean equals(Object obj) {
            boolean zEquals;
            if (this == obj) {
                return true;
            }
            synchronized (this.mutex) {
                zEquals = this.c.equals(obj);
            }
            return zEquals;
        }

        @Override // java.util.Collection, java.util.Set
        public int hashCode() {
            int iHashCode;
            synchronized (this.mutex) {
                iHashCode = this.c.hashCode();
            }
            return iHashCode;
        }
    }

    public static <T> java.util.List<T> synchronizedList(java.util.List<T> list) {
        if (list instanceof RandomAccess) {
            return new SynchronizedRandomAccessList(list);
        }
        return new SynchronizedList(list);
    }

    static class SynchronizedList extends SynchronizedCollection implements java.util.List, List {
        private static final long serialVersionUID = -7754090372962971524L;
        final java.util.List list;

        SynchronizedList(java.util.List list) {
            super(list);
            this.list = list;
        }

        SynchronizedList(java.util.List list, Object obj) {
            super(list, obj);
            this.list = list;
        }

        @Override // java.util.Collection, java.util.List
        public boolean equals(Object obj) {
            boolean zEquals;
            if (this == obj) {
                return true;
            }
            synchronized (this.mutex) {
                zEquals = this.list.equals(obj);
            }
            return zEquals;
        }

        @Override // java.util.Collection, java.util.List
        public int hashCode() {
            int iHashCode;
            synchronized (this.mutex) {
                iHashCode = this.list.hashCode();
            }
            return iHashCode;
        }

        @Override // java.util.List
        public Object get(int i) {
            Object obj;
            synchronized (this.mutex) {
                obj = this.list.get(i);
            }
            return obj;
        }

        @Override // java.util.List
        public Object set(int i, Object obj) {
            Object obj2;
            synchronized (this.mutex) {
                obj2 = this.list.set(i, obj);
            }
            return obj2;
        }

        @Override // java.util.List
        public void add(int i, Object obj) {
            synchronized (this.mutex) {
                this.list.add(i, obj);
            }
        }

        @Override // java.util.List
        public Object remove(int i) {
            Object objRemove;
            synchronized (this.mutex) {
                objRemove = this.list.remove(i);
            }
            return objRemove;
        }

        @Override // java.util.List
        public int indexOf(Object obj) {
            int iIndexOf;
            synchronized (this.mutex) {
                iIndexOf = this.list.indexOf(obj);
            }
            return iIndexOf;
        }

        @Override // java.util.List
        public int lastIndexOf(Object obj) {
            int iLastIndexOf;
            synchronized (this.mutex) {
                iLastIndexOf = this.list.lastIndexOf(obj);
            }
            return iLastIndexOf;
        }

        @Override // java.util.List
        public boolean addAll(int i, java.util.Collection collection) {
            boolean zAddAll;
            synchronized (this.mutex) {
                zAddAll = this.list.addAll(i, collection);
            }
            return zAddAll;
        }

        @Override // java.util.List
        public ListIterator listIterator() {
            return this.list.listIterator();
        }

        @Override // java.util.List
        public ListIterator listIterator(int i) {
            return this.list.listIterator(i);
        }

        @Override // java.util.List
        public java.util.List subList(int i, int i2) {
            SynchronizedList synchronizedList;
            synchronized (this.mutex) {
                synchronizedList = new SynchronizedList(this.list.subList(i, i2), this.mutex);
            }
            return synchronizedList;
        }

        @Override // java.util.List, j$.util.List
        public void replaceAll(UnaryOperator unaryOperator) {
            synchronized (this.mutex) {
                List.EL.replaceAll(this.list, unaryOperator);
            }
        }

        @Override // java.util.List, j$.util.List
        public void sort(java.util.Comparator comparator) {
            synchronized (this.mutex) {
                List.EL.sort(this.list, comparator);
            }
        }

        private Object readResolve() {
            java.util.List list = this.list;
            return list instanceof RandomAccess ? new SynchronizedRandomAccessList(list) : this;
        }
    }

    static class SynchronizedRandomAccessList extends SynchronizedList implements RandomAccess {
        private static final long serialVersionUID = 1530674583602358482L;

        SynchronizedRandomAccessList(java.util.List list) {
            super(list);
        }

        SynchronizedRandomAccessList(java.util.List list, Object obj) {
            super(list, obj);
        }

        @Override // j$.util.DesugarCollections.SynchronizedList, java.util.List
        public java.util.List subList(int i, int i2) {
            SynchronizedRandomAccessList synchronizedRandomAccessList;
            synchronized (this.mutex) {
                synchronizedRandomAccessList = new SynchronizedRandomAccessList(this.list.subList(i, i2), this.mutex);
            }
            return synchronizedRandomAccessList;
        }

        private Object writeReplace() {
            return new SynchronizedList(this.list);
        }
    }

    public static <K, V> java.util.Map<K, V> synchronizedMap(java.util.Map<K, V> map) {
        return new SynchronizedMap(map);
    }

    private static class SynchronizedMap implements java.util.Map, Serializable, Map {
        private static final long serialVersionUID = 1978198479659022715L;
        private transient java.util.Set entrySet;
        private transient java.util.Set keySet;
        private final java.util.Map m;
        final Object mutex = this;
        private transient java.util.Collection values;

        SynchronizedMap(java.util.Map map) {
            this.m = (java.util.Map) Objects.requireNonNull(map);
        }

        @Override // java.util.Map
        public int size() {
            int size;
            synchronized (this.mutex) {
                size = this.m.size();
            }
            return size;
        }

        @Override // java.util.Map
        public boolean isEmpty() {
            boolean zIsEmpty;
            synchronized (this.mutex) {
                zIsEmpty = this.m.isEmpty();
            }
            return zIsEmpty;
        }

        @Override // java.util.Map
        public boolean containsKey(Object obj) {
            boolean zContainsKey;
            synchronized (this.mutex) {
                zContainsKey = this.m.containsKey(obj);
            }
            return zContainsKey;
        }

        @Override // java.util.Map
        public boolean containsValue(Object obj) {
            boolean zContainsValue;
            synchronized (this.mutex) {
                zContainsValue = this.m.containsValue(obj);
            }
            return zContainsValue;
        }

        @Override // java.util.Map
        public Object get(Object obj) {
            Object obj2;
            synchronized (this.mutex) {
                obj2 = this.m.get(obj);
            }
            return obj2;
        }

        @Override // java.util.Map
        public Object put(Object obj, Object obj2) {
            Object objPut;
            synchronized (this.mutex) {
                objPut = this.m.put(obj, obj2);
            }
            return objPut;
        }

        @Override // java.util.Map
        public Object remove(Object obj) {
            Object objRemove;
            synchronized (this.mutex) {
                objRemove = this.m.remove(obj);
            }
            return objRemove;
        }

        @Override // java.util.Map
        public void putAll(java.util.Map map) {
            synchronized (this.mutex) {
                this.m.putAll(map);
            }
        }

        @Override // java.util.Map
        public void clear() {
            synchronized (this.mutex) {
                this.m.clear();
            }
        }

        @Override // java.util.Map
        public java.util.Set keySet() {
            java.util.Set set;
            synchronized (this.mutex) {
                try {
                    if (this.keySet == null) {
                        this.keySet = new SynchronizedSet(this.m.keySet(), this.mutex);
                    }
                    set = this.keySet;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return set;
        }

        @Override // java.util.Map
        public java.util.Set entrySet() {
            java.util.Set set;
            synchronized (this.mutex) {
                try {
                    if (this.entrySet == null) {
                        this.entrySet = new SynchronizedSet(this.m.entrySet(), this.mutex);
                    }
                    set = this.entrySet;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return set;
        }

        @Override // java.util.Map
        public java.util.Collection values() {
            java.util.Collection collection;
            synchronized (this.mutex) {
                try {
                    if (this.values == null) {
                        this.values = new SynchronizedCollection(this.m.values(), this.mutex);
                    }
                    collection = this.values;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return collection;
        }

        @Override // java.util.Map
        public boolean equals(Object obj) {
            boolean zEquals;
            if (this == obj) {
                return true;
            }
            synchronized (this.mutex) {
                zEquals = this.m.equals(obj);
            }
            return zEquals;
        }

        @Override // java.util.Map
        public int hashCode() {
            int iHashCode;
            synchronized (this.mutex) {
                iHashCode = this.m.hashCode();
            }
            return iHashCode;
        }

        public String toString() {
            String string;
            synchronized (this.mutex) {
                string = this.m.toString();
            }
            return string;
        }

        @Override // java.util.Map, j$.util.Map
        public Object getOrDefault(Object obj, Object obj2) {
            Object orDefault;
            synchronized (this.mutex) {
                orDefault = Map.EL.getOrDefault(this.m, obj, obj2);
            }
            return orDefault;
        }

        @Override // java.util.Map, j$.util.Map
        public void forEach(BiConsumer biConsumer) {
            synchronized (this.mutex) {
                Map.EL.forEach(this.m, biConsumer);
            }
        }

        @Override // java.util.Map, j$.util.Map
        public void replaceAll(BiFunction biFunction) {
            synchronized (this.mutex) {
                Map.EL.replaceAll(this.m, biFunction);
            }
        }

        @Override // java.util.Map, j$.util.Map
        public Object putIfAbsent(Object obj, Object obj2) {
            Object objPutIfAbsent;
            synchronized (this.mutex) {
                objPutIfAbsent = Map.EL.putIfAbsent(this.m, obj, obj2);
            }
            return objPutIfAbsent;
        }

        @Override // java.util.Map, j$.util.Map
        public boolean remove(Object obj, Object obj2) {
            boolean zRemove;
            synchronized (this.mutex) {
                zRemove = Map.EL.remove(this.m, obj, obj2);
            }
            return zRemove;
        }

        @Override // java.util.Map, j$.util.Map
        public boolean replace(Object obj, Object obj2, Object obj3) {
            boolean zReplace;
            synchronized (this.mutex) {
                zReplace = Map.EL.replace(this.m, obj, obj2, obj3);
            }
            return zReplace;
        }

        @Override // java.util.Map, j$.util.Map
        public Object replace(Object obj, Object obj2) {
            Object objReplace;
            synchronized (this.mutex) {
                objReplace = Map.EL.replace(this.m, obj, obj2);
            }
            return objReplace;
        }

        @Override // java.util.Map, j$.util.Map
        public Object computeIfAbsent(Object obj, Function function) {
            Object objComputeIfAbsent;
            synchronized (this.mutex) {
                objComputeIfAbsent = Map.EL.computeIfAbsent(this.m, obj, function);
            }
            return objComputeIfAbsent;
        }

        @Override // java.util.Map, j$.util.Map
        public Object computeIfPresent(Object obj, BiFunction biFunction) {
            Object objComputeIfPresent;
            synchronized (this.mutex) {
                objComputeIfPresent = Map.EL.computeIfPresent(this.m, obj, biFunction);
            }
            return objComputeIfPresent;
        }

        @Override // java.util.Map, j$.util.Map
        public Object compute(Object obj, BiFunction biFunction) {
            Object objCompute;
            synchronized (this.mutex) {
                objCompute = Map.EL.compute(this.m, obj, biFunction);
            }
            return objCompute;
        }

        @Override // java.util.Map, j$.util.Map
        public Object merge(Object obj, Object obj2, BiFunction biFunction) {
            Object objMerge;
            synchronized (this.mutex) {
                objMerge = Map.EL.merge(this.m, obj, obj2, biFunction);
            }
            return objMerge;
        }

        private void writeObject(ObjectOutputStream objectOutputStream) {
            synchronized (this.mutex) {
                objectOutputStream.defaultWriteObject();
            }
        }
    }

    static boolean eq(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }
}
