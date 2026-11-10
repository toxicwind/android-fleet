package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible(serializable = true)
public class TreeBasedTable<R, C, V> extends StandardRowSortedTable<R, C, V> {
    public static final long serialVersionUID = 0;
    public final Comparator<? super C> columnComparator;

    private static class Factory<C, V> implements Supplier<TreeMap<C, V>>, Serializable {
        public static final long serialVersionUID = 0;
        public final Comparator<? super C> comparator;

        public Factory(Comparator<? super C> comparator) {
            this.comparator = comparator;
        }

        @Override // com.google.common.base.Supplier
        public TreeMap<C, V> get() {
            return new TreeMap<>(this.comparator);
        }
    }

    private class TreeRow extends StandardTable<R, C, V>.Row implements SortedMap<C, V> {
        public final C lowerBound;
        public final C upperBound;
        public transient SortedMap<C, V> wholeRow;

        public TreeRow(TreeBasedTable treeBasedTable, R r) {
            this(r, null, null);
        }

        @Override // java.util.SortedMap
        public Comparator<? super C> comparator() {
            return TreeBasedTable.this.columnComparator();
        }

        public int compare(Object obj, Object obj2) {
            return comparator().compare(obj, obj2);
        }

        @Override // com.google.common.collect.StandardTable.Row, java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object obj) {
            return rangeContains(obj) && super.containsKey(obj);
        }

        @Override // java.util.SortedMap
        public C firstKey() {
            if (backingRowMap() != null) {
                return backingRowMap().firstKey();
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.SortedMap
        public SortedMap<C, V> headMap(C c2) {
            if (c2 == null) {
                throw new NullPointerException();
            }
            Preconditions.checkArgument(rangeContains(c2));
            return new TreeRow(this.rowKey, this.lowerBound, c2);
        }

        @Override // java.util.SortedMap
        public C lastKey() {
            if (backingRowMap() != null) {
                return backingRowMap().lastKey();
            }
            throw new NoSuchElementException();
        }

        @Override // com.google.common.collect.StandardTable.Row
        public void maintainEmptyInvariant() {
            if (wholeRow() == null || !this.wholeRow.isEmpty()) {
                return;
            }
            TreeBasedTable.this.backingMap.remove(this.rowKey);
            this.wholeRow = null;
            this.backingRowMap = null;
        }

        @Override // com.google.common.collect.StandardTable.Row, java.util.AbstractMap, java.util.Map
        public V put(C c2, V v) {
            if (c2 == null) {
                throw new NullPointerException();
            }
            Preconditions.checkArgument(rangeContains(c2));
            return (V) super.put(c2, v);
        }

        public boolean rangeContains(Object obj) {
            C c2;
            C c3;
            return obj != null && ((c2 = this.lowerBound) == null || compare(c2, obj) <= 0) && ((c3 = this.upperBound) == null || compare(c3, obj) > 0);
        }

        /* JADX WARN: Code duplicated, block: B:11:0x0018  */
        @Override // java.util.SortedMap
        public SortedMap<C, V> subMap(C c2, C c3) {
            boolean z;
            if (c2 == null) {
                throw new NullPointerException();
            }
            if (!rangeContains(c2)) {
                z = false;
            } else {
                if (c3 == null) {
                    throw new NullPointerException();
                }
                if (rangeContains(c3)) {
                    z = true;
                } else {
                    z = false;
                }
            }
            Preconditions.checkArgument(z);
            return new TreeRow(this.rowKey, c2, c3);
        }

        @Override // java.util.SortedMap
        public SortedMap<C, V> tailMap(C c2) {
            if (c2 == null) {
                throw new NullPointerException();
            }
            Preconditions.checkArgument(rangeContains(c2));
            return new TreeRow(this.rowKey, c2, this.upperBound);
        }

        public SortedMap<C, V> wholeRow() {
            SortedMap<C, V> sortedMap = this.wholeRow;
            if (sortedMap == null || (sortedMap.isEmpty() && TreeBasedTable.this.backingMap.containsKey(this.rowKey))) {
                this.wholeRow = (SortedMap) TreeBasedTable.this.backingMap.get(this.rowKey);
            }
            return this.wholeRow;
        }

        public TreeRow(R r, C c2, C c3) {
            super(r);
            this.lowerBound = c2;
            this.upperBound = c3;
            Preconditions.checkArgument(c2 == null || c3 == null || compare(c2, c3) <= 0);
        }

        @Override // com.google.common.collect.StandardTable.Row
        public SortedMap<C, V> backingRowMap() {
            return (SortedMap) super.backingRowMap();
        }

        @Override // com.google.common.collect.StandardTable.Row
        public SortedMap<C, V> computeBackingRowMap() {
            SortedMap<C, V> sortedMapWholeRow = wholeRow();
            if (sortedMapWholeRow == null) {
                return null;
            }
            C c2 = this.lowerBound;
            if (c2 != null) {
                sortedMapWholeRow = sortedMapWholeRow.tailMap(c2);
            }
            C c3 = this.upperBound;
            return c3 != null ? sortedMapWholeRow.headMap(c3) : sortedMapWholeRow;
        }

        @Override // java.util.AbstractMap, java.util.Map, java.util.SortedMap
        public SortedSet<C> keySet() {
            return new Maps.SortedKeySet(this);
        }
    }

    public TreeBasedTable(Comparator<? super R> comparator, Comparator<? super C> comparator2) {
        super(new TreeMap(comparator), new Factory(comparator2));
        this.columnComparator = comparator2;
    }

    public static <R extends Comparable, C extends Comparable, V> TreeBasedTable<R, C, V> create() {
        return new TreeBasedTable<>(Ordering.natural(), Ordering.natural());
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ Set cellSet() {
        return super.cellSet();
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public void clear() {
        this.backingMap.clear();
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.Table
    public Map column(Object obj) {
        return new StandardTable.Column(obj);
    }

    @Deprecated
    public Comparator<? super C> columnComparator() {
        return this.columnComparator;
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ Set columnKeySet() {
        return super.columnKeySet();
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ Map columnMap() {
        return super.columnMap();
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ boolean contains(Object obj, Object obj2) {
        return super.contains(obj, obj2);
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ boolean containsColumn(Object obj) {
        return super.containsColumn(obj);
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ boolean containsRow(Object obj) {
        return super.containsRow(obj);
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ boolean containsValue(Object obj) {
        return super.containsValue(obj);
    }

    @Override // com.google.common.collect.StandardTable
    public Iterator<C> createColumnKeyIterator() {
        final Comparator<? super C> comparatorColumnComparator = columnComparator();
        final UnmodifiableIterator unmodifiableIteratorMergeSorted = Iterators.mergeSorted(Iterables.transform(this.backingMap.values(), new Function<Map<C, V>, Iterator<C>>(this) { // from class: com.google.common.collect.TreeBasedTable.1
            @Override // com.google.common.base.Function
            public Iterator<C> apply(Map<C, V> map) {
                return map.keySet().iterator();
            }
        }), comparatorColumnComparator);
        return new AbstractIterator<C>(this) { // from class: com.google.common.collect.TreeBasedTable.2
            public C lastValue;

            @Override // com.google.common.collect.AbstractIterator
            public C computeNext() {
                while (unmodifiableIteratorMergeSorted.hasNext()) {
                    C c2 = (C) unmodifiableIteratorMergeSorted.next();
                    C c3 = this.lastValue;
                    if (!(c3 != null && comparatorColumnComparator.compare(c2, c3) == 0)) {
                        this.lastValue = c2;
                        return this.lastValue;
                    }
                }
                this.lastValue = null;
                return endOfData();
            }
        };
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public boolean equals(Object obj) {
        return Tables.equalsImpl(this, obj);
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ Object get(Object obj, Object obj2) {
        return super.get(obj, obj2);
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.StandardTable, com.google.common.collect.AbstractTable, com.google.common.collect.Table
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ Object put(Object obj, Object obj2, Object obj3) {
        return super.put(obj, obj2, obj3);
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ void putAll(Table table) {
        super.putAll(table);
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.AbstractTable, com.google.common.collect.Table
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ Object remove(Object obj, Object obj2) {
        return super.remove(obj, obj2);
    }

    @Deprecated
    public Comparator<? super R> rowComparator() {
        return rowKeySet().comparator();
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ int size() {
        return super.size();
    }

    @Override // com.google.common.collect.AbstractTable
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ Collection values() {
        return super.values();
    }

    public static <R, C, V> TreeBasedTable<R, C, V> create(Comparator<? super R> comparator, Comparator<? super C> comparator2) {
        if (comparator == null) {
            throw new NullPointerException();
        }
        if (comparator2 != null) {
            return new TreeBasedTable<>(comparator, comparator2);
        }
        throw new NullPointerException();
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.Table
    public SortedMap<C, V> row(R r) {
        return new TreeRow(r, null, null);
    }

    @Override // com.google.common.collect.StandardRowSortedTable, com.google.common.collect.StandardTable, com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public SortedSet<R> rowKeySet() {
        return super.rowKeySet();
    }

    @Override // com.google.common.collect.StandardRowSortedTable, com.google.common.collect.StandardTable, com.google.common.collect.Table
    public SortedMap<R, Map<C, V>> rowMap() {
        return super.rowMap();
    }

    public static <R, C, V> TreeBasedTable<R, C, V> create(TreeBasedTable<R, C, ? extends V> treeBasedTable) {
        TreeBasedTable<R, C, V> treeBasedTable2 = new TreeBasedTable<>(treeBasedTable.rowComparator(), treeBasedTable.columnComparator());
        treeBasedTable2.putAll(treeBasedTable);
        return treeBasedTable2;
    }
}
