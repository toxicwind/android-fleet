package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Supplier;
import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible
public class StandardRowSortedTable<R, C, V> extends StandardTable<R, C, V> implements RowSortedTable<R, C, V> {
    public static final long serialVersionUID = 0;

    private class RowSortedMap extends StandardTable<R, C, V>.RowMap implements SortedMap<R, Map<C, V>> {
        public RowSortedMap() {
            super();
        }

        @Override // java.util.SortedMap
        public Comparator<? super R> comparator() {
            return ((SortedMap) StandardRowSortedTable.this.backingMap).comparator();
        }

        @Override // java.util.SortedMap
        public R firstKey() {
            return (R) ((SortedMap) StandardRowSortedTable.this.backingMap).firstKey();
        }

        @Override // java.util.SortedMap
        public SortedMap<R, Map<C, V>> headMap(R r) {
            if (r != null) {
                return new StandardRowSortedTable(((SortedMap) StandardRowSortedTable.this.backingMap).headMap(r), StandardRowSortedTable.this.factory).rowMap();
            }
            throw new NullPointerException();
        }

        @Override // java.util.SortedMap
        public R lastKey() {
            return (R) ((SortedMap) StandardRowSortedTable.this.backingMap).lastKey();
        }

        @Override // java.util.SortedMap
        public SortedMap<R, Map<C, V>> subMap(R r, R r2) {
            if (r == null) {
                throw new NullPointerException();
            }
            if (r2 != null) {
                return new StandardRowSortedTable(((SortedMap) StandardRowSortedTable.this.backingMap).subMap(r, r2), StandardRowSortedTable.this.factory).rowMap();
            }
            throw new NullPointerException();
        }

        @Override // java.util.SortedMap
        public SortedMap<R, Map<C, V>> tailMap(R r) {
            if (r != null) {
                return new StandardRowSortedTable(((SortedMap) StandardRowSortedTable.this.backingMap).tailMap(r), StandardRowSortedTable.this.factory).rowMap();
            }
            throw new NullPointerException();
        }

        public /* synthetic */ RowSortedMap(AnonymousClass1 anonymousClass1) {
            super();
        }

        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap
        public SortedSet<R> createKeySet() {
            return new Maps.SortedKeySet(this);
        }

        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap, java.util.AbstractMap, java.util.Map
        public SortedSet<R> keySet() {
            return (SortedSet) super.keySet();
        }
    }

    public StandardRowSortedTable(SortedMap<R, Map<C, V>> sortedMap, Supplier<? extends Map<C, V>> supplier) {
        super(sortedMap, supplier);
    }

    public static /* synthetic */ SortedMap access$100(StandardRowSortedTable standardRowSortedTable) {
        return (SortedMap) standardRowSortedTable.backingMap;
    }

    private SortedMap<R, Map<C, V>> sortedBackingMap() {
        return (SortedMap) this.backingMap;
    }

    @Override // com.google.common.collect.StandardTable
    public SortedMap<R, Map<C, V>> createRowMap() {
        return new RowSortedMap(null);
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public SortedSet<R> rowKeySet() {
        return (SortedSet) rowMap().keySet();
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.Table
    public SortedMap<R, Map<C, V>> rowMap() {
        return (SortedMap) super.rowMap();
    }
}
