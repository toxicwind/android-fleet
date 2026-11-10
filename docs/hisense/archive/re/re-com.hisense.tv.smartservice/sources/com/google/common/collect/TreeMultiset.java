package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible(emulated = true)
public final class TreeMultiset<E> extends AbstractSortedMultiset<E> implements Serializable {

    @GwtIncompatible
    public static final long serialVersionUID = 1;
    public final transient AvlNode<E> header;
    public final transient GeneralRange<E> range;
    public final transient Reference<AvlNode<E>> rootReference;

    /* JADX INFO: renamed from: com.google.common.collect.TreeMultiset$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        public static final /* synthetic */ int[] $SwitchMap$com$google$common$collect$BoundType = new int[BoundType.values().length];

        static {
            try {
                $SwitchMap$com$google$common$collect$BoundType[BoundType.OPEN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$common$collect$BoundType[BoundType.CLOSED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private enum Aggregate {
        SIZE { // from class: com.google.common.collect.TreeMultiset.Aggregate.1
            @Override // com.google.common.collect.TreeMultiset.Aggregate
            public int nodeAggregate(AvlNode<?> avlNode) {
                return avlNode.elemCount;
            }

            @Override // com.google.common.collect.TreeMultiset.Aggregate
            public long treeAggregate(AvlNode<?> avlNode) {
                if (avlNode == null) {
                    return 0L;
                }
                return avlNode.totalCount;
            }
        },
        DISTINCT { // from class: com.google.common.collect.TreeMultiset.Aggregate.2
            @Override // com.google.common.collect.TreeMultiset.Aggregate
            public int nodeAggregate(AvlNode<?> avlNode) {
                return 1;
            }

            @Override // com.google.common.collect.TreeMultiset.Aggregate
            public long treeAggregate(AvlNode<?> avlNode) {
                if (avlNode == null) {
                    return 0L;
                }
                return avlNode.distinctElements;
            }
        };

        public abstract int nodeAggregate(AvlNode<?> avlNode);

        public abstract long treeAggregate(AvlNode<?> avlNode);

        /* synthetic */ Aggregate(AnonymousClass1 anonymousClass1) {
        }
    }

    private static final class Reference<T> {
        public T value;

        public Reference() {
        }

        public void checkAndSet(T t, T t2) {
            if (this.value != t) {
                throw new ConcurrentModificationException();
            }
            this.value = t2;
        }

        public void clear() {
            this.value = null;
        }

        public T get() {
            return this.value;
        }

        public /* synthetic */ Reference(AnonymousClass1 anonymousClass1) {
        }
    }

    public TreeMultiset(Reference<AvlNode<E>> reference, GeneralRange<E> generalRange, AvlNode<E> avlNode) {
        super(generalRange.comparator());
        this.rootReference = reference;
        this.range = generalRange;
        this.header = avlNode;
    }

    public static /* synthetic */ void access$1700(AvlNode avlNode, AvlNode avlNode2, AvlNode avlNode3) {
        avlNode.succ = avlNode2;
        avlNode2.pred = avlNode;
        avlNode2.succ = avlNode3;
        avlNode3.pred = avlNode2;
    }

    public static /* synthetic */ void access$1800(AvlNode avlNode, AvlNode avlNode2) {
        avlNode.succ = avlNode2;
        avlNode2.pred = avlNode;
    }

    private long aggregateAboveRange(Aggregate aggregate, AvlNode<E> avlNode) {
        if (avlNode == null) {
            return 0L;
        }
        int iCompare = comparator().compare(this.range.getUpperEndpoint(), avlNode.elem);
        if (iCompare > 0) {
            return aggregateAboveRange(aggregate, avlNode.right);
        }
        if (iCompare != 0) {
            return aggregate.treeAggregate(avlNode.right) + ((long) aggregate.nodeAggregate(avlNode)) + aggregateAboveRange(aggregate, avlNode.left);
        }
        int iOrdinal = this.range.getUpperBoundType().ordinal();
        if (iOrdinal == 0) {
            return aggregate.treeAggregate(avlNode.right) + ((long) aggregate.nodeAggregate(avlNode));
        }
        if (iOrdinal == 1) {
            return aggregate.treeAggregate(avlNode.right);
        }
        throw new AssertionError();
    }

    private long aggregateBelowRange(Aggregate aggregate, AvlNode<E> avlNode) {
        if (avlNode == null) {
            return 0L;
        }
        int iCompare = comparator().compare(this.range.getLowerEndpoint(), avlNode.elem);
        if (iCompare < 0) {
            return aggregateBelowRange(aggregate, avlNode.left);
        }
        if (iCompare != 0) {
            return aggregate.treeAggregate(avlNode.left) + ((long) aggregate.nodeAggregate(avlNode)) + aggregateBelowRange(aggregate, avlNode.right);
        }
        int iOrdinal = this.range.getLowerBoundType().ordinal();
        if (iOrdinal == 0) {
            return aggregate.treeAggregate(avlNode.left) + ((long) aggregate.nodeAggregate(avlNode));
        }
        if (iOrdinal == 1) {
            return aggregate.treeAggregate(avlNode.left);
        }
        throw new AssertionError();
    }

    private long aggregateForEntries(Aggregate aggregate) {
        AvlNode<E> avlNode = this.rootReference.get();
        long jTreeAggregate = aggregate.treeAggregate(avlNode);
        if (this.range.hasLowerBound()) {
            jTreeAggregate -= aggregateBelowRange(aggregate, avlNode);
        }
        return this.range.hasUpperBound() ? jTreeAggregate - aggregateAboveRange(aggregate, avlNode) : jTreeAggregate;
    }

    public static <E extends Comparable> TreeMultiset<E> create() {
        return new TreeMultiset<>(Ordering.natural());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AvlNode<E> firstNode() {
        AvlNode<E> avlNode;
        if (this.rootReference.get() == null) {
            return null;
        }
        if (this.range.hasLowerBound()) {
            E lowerEndpoint = this.range.getLowerEndpoint();
            AvlNode<E> avlNodeCeiling = this.rootReference.get().ceiling(comparator(), lowerEndpoint);
            if (avlNodeCeiling == null) {
                return null;
            }
            if (this.range.getLowerBoundType() == BoundType.OPEN && comparator().compare(lowerEndpoint, avlNodeCeiling.getElement()) == 0) {
                avlNodeCeiling = avlNodeCeiling.succ;
            }
            avlNode = avlNodeCeiling;
        } else {
            avlNode = this.header.succ;
        }
        if (avlNode == this.header || !this.range.contains(avlNode.getElement())) {
            return null;
        }
        return avlNode;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AvlNode<E> lastNode() {
        AvlNode<E> avlNode;
        if (this.rootReference.get() == null) {
            return null;
        }
        if (this.range.hasUpperBound()) {
            E upperEndpoint = this.range.getUpperEndpoint();
            AvlNode<E> avlNodeFloor = this.rootReference.get().floor(comparator(), upperEndpoint);
            if (avlNodeFloor == null) {
                return null;
            }
            if (this.range.getUpperBoundType() == BoundType.OPEN && comparator().compare(upperEndpoint, avlNodeFloor.getElement()) == 0) {
                avlNodeFloor = avlNodeFloor.pred;
            }
            avlNode = avlNodeFloor;
        } else {
            avlNode = this.header.pred;
        }
        if (avlNode == this.header || !this.range.contains(avlNode.getElement())) {
            return null;
        }
        return avlNode;
    }

    @GwtIncompatible
    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        Comparator comparator = (Comparator) objectInputStream.readObject();
        Serialization.getFieldSetter(AbstractSortedMultiset.class, "comparator").set(this, comparator);
        Serialization.getFieldSetter(TreeMultiset.class, "range").set(this, GeneralRange.all(comparator));
        Serialization.getFieldSetter(TreeMultiset.class, "rootReference").set(this, new Reference(null));
        AvlNode<E> avlNode = new AvlNode<>(null, 1);
        Serialization.getFieldSetter(TreeMultiset.class, "header").set(this, avlNode);
        avlNode.succ = avlNode;
        avlNode.pred = avlNode;
        Serialization.populateMultiset(this, objectInputStream, objectInputStream.readInt());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> void successor(AvlNode<T> avlNode, AvlNode<T> avlNode2) {
        avlNode.succ = avlNode2;
        avlNode2.pred = avlNode;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Multiset.Entry<E> wrapEntry(final AvlNode<E> avlNode) {
        return new Multisets.AbstractEntry<E>() { // from class: com.google.common.collect.TreeMultiset.1
            @Override // com.google.common.collect.Multiset.Entry
            public int getCount() {
                int count = avlNode.getCount();
                return count == 0 ? TreeMultiset.this.count(getElement()) : count;
            }

            @Override // com.google.common.collect.Multiset.Entry
            public E getElement() {
                return (E) avlNode.getElement();
            }
        };
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(elementSet().comparator());
        Serialization.writeMultiset(this, objectOutputStream);
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public int add(E e2, int i) {
        CollectPreconditions.checkNonnegative(i, "occurrences");
        if (i == 0) {
            return count(e2);
        }
        Preconditions.checkArgument(this.range.contains(e2));
        AvlNode<E> avlNode = this.rootReference.get();
        if (avlNode != null) {
            int[] iArr = new int[1];
            this.rootReference.checkAndSet(avlNode, avlNode.add(comparator(), e2, i, iArr));
            return iArr[0];
        }
        comparator().compare(e2, e2);
        AvlNode<E> avlNode2 = new AvlNode<>(e2, i);
        AvlNode<E> avlNode3 = this.header;
        successor(avlNode3, avlNode2, avlNode3);
        this.rootReference.checkAndSet(avlNode, avlNode2);
        return 0;
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection
    public void clear() {
        if (this.range.hasLowerBound() || this.range.hasUpperBound()) {
            Iterators.clear(entryIterator());
            return;
        }
        AvlNode<E> avlNode = this.header.succ;
        while (true) {
            AvlNode<E> avlNode2 = this.header;
            if (avlNode == avlNode2) {
                avlNode2.succ = avlNode2;
                avlNode2.pred = avlNode2;
                this.rootReference.clear();
                return;
            } else {
                AvlNode<E> avlNode3 = avlNode.succ;
                avlNode.elemCount = 0;
                avlNode.left = null;
                avlNode.right = null;
                avlNode.pred = null;
                avlNode.succ = null;
                avlNode = avlNode3;
            }
        }
    }

    @Override // com.google.common.collect.AbstractSortedMultiset, com.google.common.collect.SortedMultiset, com.google.common.collect.SortedIterable
    public Comparator comparator() {
        return this.comparator;
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    public boolean contains(Object obj) {
        return count(obj) > 0;
    }

    @Override // com.google.common.collect.Multiset
    public int count(Object obj) {
        try {
            AvlNode<E> avlNode = this.rootReference.get();
            if (this.range.contains(obj) && avlNode != null) {
                return avlNode.count(comparator(), obj);
            }
        } catch (ClassCastException | NullPointerException unused) {
        }
        return 0;
    }

    @Override // com.google.common.collect.AbstractSortedMultiset
    public Iterator<Multiset.Entry<E>> descendingEntryIterator() {
        return new Iterator<Multiset.Entry<E>>() { // from class: com.google.common.collect.TreeMultiset.3
            public AvlNode<E> current;
            public Multiset.Entry<E> prevEntry = null;

            {
                this.current = TreeMultiset.this.lastNode();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                if (this.current == null) {
                    return false;
                }
                if (!TreeMultiset.this.range.tooLow(this.current.getElement())) {
                    return true;
                }
                this.current = null;
                return false;
            }

            @Override // java.util.Iterator
            public void remove() {
                Preconditions.checkState(this.prevEntry != null, "no calls to next() since the last call to remove()");
                TreeMultiset.this.setCount(this.prevEntry.getElement(), 0);
                this.prevEntry = null;
            }

            @Override // java.util.Iterator
            public Multiset.Entry<E> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Multiset.Entry<E> entryWrapEntry = TreeMultiset.this.wrapEntry(this.current);
                this.prevEntry = entryWrapEntry;
                if (this.current.pred == TreeMultiset.this.header) {
                    this.current = null;
                } else {
                    this.current = this.current.pred;
                }
                return entryWrapEntry;
            }
        };
    }

    @Override // com.google.common.collect.AbstractSortedMultiset, com.google.common.collect.SortedMultiset
    public /* bridge */ /* synthetic */ SortedMultiset descendingMultiset() {
        return super.descendingMultiset();
    }

    @Override // com.google.common.collect.AbstractMultiset
    public int distinctElements() {
        return Ints.saturatedCast(aggregateForEntries(Aggregate.DISTINCT));
    }

    @Override // com.google.common.collect.AbstractMultiset
    public Iterator<E> elementIterator() {
        return new Multisets.AnonymousClass5(entryIterator());
    }

    @Override // com.google.common.collect.AbstractSortedMultiset, com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ NavigableSet elementSet() {
        return super.elementSet();
    }

    @Override // com.google.common.collect.AbstractMultiset
    public Iterator<Multiset.Entry<E>> entryIterator() {
        return new Iterator<Multiset.Entry<E>>() { // from class: com.google.common.collect.TreeMultiset.2
            public AvlNode<E> current;
            public Multiset.Entry<E> prevEntry;

            {
                this.current = TreeMultiset.this.firstNode();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                if (this.current == null) {
                    return false;
                }
                if (!TreeMultiset.this.range.tooHigh(this.current.getElement())) {
                    return true;
                }
                this.current = null;
                return false;
            }

            @Override // java.util.Iterator
            public void remove() {
                Preconditions.checkState(this.prevEntry != null, "no calls to next() since the last call to remove()");
                TreeMultiset.this.setCount(this.prevEntry.getElement(), 0);
                this.prevEntry = null;
            }

            @Override // java.util.Iterator
            public Multiset.Entry<E> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Multiset.Entry<E> entryWrapEntry = TreeMultiset.this.wrapEntry(this.current);
                this.prevEntry = entryWrapEntry;
                if (this.current.succ == TreeMultiset.this.header) {
                    this.current = null;
                } else {
                    this.current = this.current.succ;
                }
                return entryWrapEntry;
            }
        };
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ Set entrySet() {
        return super.entrySet();
    }

    @Override // com.google.common.collect.AbstractSortedMultiset, com.google.common.collect.SortedMultiset
    public /* bridge */ /* synthetic */ Multiset.Entry firstEntry() {
        return super.firstEntry();
    }

    @Override // com.google.common.collect.SortedMultiset
    public SortedMultiset<E> headMultiset(E e2, BoundType boundType) {
        return new TreeMultiset(this.rootReference, this.range.intersect(GeneralRange.upTo(comparator(), e2, boundType)), this.header);
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection
    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.google.common.collect.Multiset
    public Iterator<E> iterator() {
        return Multisets.iteratorImpl(this);
    }

    @Override // com.google.common.collect.AbstractSortedMultiset, com.google.common.collect.SortedMultiset
    public /* bridge */ /* synthetic */ Multiset.Entry lastEntry() {
        return super.lastEntry();
    }

    @Override // com.google.common.collect.AbstractSortedMultiset, com.google.common.collect.SortedMultiset
    public /* bridge */ /* synthetic */ Multiset.Entry pollFirstEntry() {
        return super.pollFirstEntry();
    }

    @Override // com.google.common.collect.AbstractSortedMultiset, com.google.common.collect.SortedMultiset
    public /* bridge */ /* synthetic */ Multiset.Entry pollLastEntry() {
        return super.pollLastEntry();
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public int remove(Object obj, int i) {
        CollectPreconditions.checkNonnegative(i, "occurrences");
        if (i == 0) {
            return count(obj);
        }
        AvlNode<E> avlNode = this.rootReference.get();
        int[] iArr = new int[1];
        try {
            if (this.range.contains(obj) && avlNode != null) {
                this.rootReference.checkAndSet(avlNode, avlNode.remove(comparator(), obj, i, iArr));
                return iArr[0];
            }
        } catch (ClassCastException | NullPointerException unused) {
        }
        return 0;
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public int setCount(E e2, int i) {
        CollectPreconditions.checkNonnegative(i, "count");
        if (!this.range.contains(e2)) {
            Preconditions.checkArgument(i == 0);
            return 0;
        }
        AvlNode<E> avlNode = this.rootReference.get();
        if (avlNode == null) {
            if (i > 0) {
                add(e2, i);
            }
            return 0;
        }
        int[] iArr = new int[1];
        this.rootReference.checkAndSet(avlNode, avlNode.setCount(comparator(), e2, i, iArr));
        return iArr[0];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    public int size() {
        return Ints.saturatedCast(aggregateForEntries(Aggregate.SIZE));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.AbstractSortedMultiset, com.google.common.collect.SortedMultiset
    public /* bridge */ /* synthetic */ SortedMultiset subMultiset(Object obj, BoundType boundType, Object obj2, BoundType boundType2) {
        return super.subMultiset(obj, boundType, obj2, boundType2);
    }

    @Override // com.google.common.collect.SortedMultiset
    public SortedMultiset<E> tailMultiset(E e2, BoundType boundType) {
        return new TreeMultiset(this.rootReference, this.range.intersect(GeneralRange.downTo(comparator(), e2, boundType)), this.header);
    }

    public static <E> TreeMultiset<E> create(Comparator<? super E> comparator) {
        return comparator == null ? new TreeMultiset<>(Ordering.natural()) : new TreeMultiset<>(comparator);
    }

    public static int distinctElements(AvlNode<?> avlNode) {
        if (avlNode == null) {
            return 0;
        }
        return avlNode.distinctElements;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> void successor(AvlNode<T> avlNode, AvlNode<T> avlNode2, AvlNode<T> avlNode3) {
        avlNode.succ = avlNode2;
        avlNode2.pred = avlNode;
        avlNode2.succ = avlNode3;
        avlNode3.pred = avlNode2;
    }

    public static <E extends Comparable> TreeMultiset<E> create(Iterable<? extends E> iterable) {
        TreeMultiset<E> treeMultisetCreate = create();
        Iterables.addAll(treeMultisetCreate, iterable);
        return treeMultisetCreate;
    }

    public TreeMultiset(Comparator<? super E> comparator) {
        super(comparator);
        this.range = GeneralRange.all(comparator);
        this.header = new AvlNode<>(null, 1);
        AvlNode<E> avlNode = this.header;
        avlNode.succ = avlNode;
        avlNode.pred = avlNode;
        this.rootReference = new Reference<>(null);
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public boolean setCount(E e2, int i, int i2) {
        CollectPreconditions.checkNonnegative(i2, "newCount");
        CollectPreconditions.checkNonnegative(i, "oldCount");
        Preconditions.checkArgument(this.range.contains(e2));
        AvlNode<E> avlNode = this.rootReference.get();
        if (avlNode != null) {
            int[] iArr = new int[1];
            this.rootReference.checkAndSet(avlNode, avlNode.setCount(comparator(), e2, i, i2, iArr));
            return iArr[0] == i;
        }
        if (i != 0) {
            return false;
        }
        if (i2 > 0) {
            add(e2, i2);
        }
        return true;
    }

    private static final class AvlNode<E> {
        public int distinctElements;
        public final E elem;
        public int elemCount;
        public int height;
        public AvlNode<E> left;
        public AvlNode<E> pred;
        public AvlNode<E> right;
        public AvlNode<E> succ;
        public long totalCount;

        public AvlNode(E e2, int i) {
            Preconditions.checkArgument(i > 0);
            this.elem = e2;
            this.elemCount = i;
            this.totalCount = i;
            this.distinctElements = 1;
            this.height = 1;
            this.left = null;
            this.right = null;
        }

        private AvlNode<E> addLeftChild(E e2, int i) {
            this.left = new AvlNode<>(e2, i);
            TreeMultiset.access$1700(this.pred, this.left, this);
            this.height = Math.max(2, this.height);
            this.distinctElements++;
            this.totalCount += (long) i;
            return this;
        }

        private AvlNode<E> addRightChild(E e2, int i) {
            this.right = new AvlNode<>(e2, i);
            TreeMultiset.access$1700(this, this.right, this.succ);
            this.height = Math.max(2, this.height);
            this.distinctElements++;
            this.totalCount += (long) i;
            return this;
        }

        private int balanceFactor() {
            return height(this.left) - height(this.right);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        public AvlNode<E> ceiling(Comparator<? super E> comparator, E e2) {
            int iCompare = comparator.compare(e2, this.elem);
            if (iCompare < 0) {
                AvlNode<E> avlNode = this.left;
                return avlNode == null ? this : (AvlNode) MoreObjects.firstNonNull(avlNode.ceiling(comparator, e2), this);
            }
            if (iCompare == 0) {
                return this;
            }
            AvlNode<E> avlNode2 = this.right;
            if (avlNode2 == null) {
                return null;
            }
            return avlNode2.ceiling(comparator, e2);
        }

        private AvlNode<E> deleteMe() {
            int i = this.elemCount;
            this.elemCount = 0;
            TreeMultiset.access$1800(this.pred, this.succ);
            AvlNode<E> avlNode = this.left;
            if (avlNode == null) {
                return this.right;
            }
            AvlNode<E> avlNode2 = this.right;
            if (avlNode2 == null) {
                return avlNode;
            }
            if (avlNode.height >= avlNode2.height) {
                AvlNode<E> avlNode3 = this.pred;
                avlNode3.left = avlNode.removeMax(avlNode3);
                avlNode3.right = this.right;
                avlNode3.distinctElements = this.distinctElements - 1;
                avlNode3.totalCount = this.totalCount - ((long) i);
                return avlNode3.rebalance();
            }
            AvlNode<E> avlNode4 = this.succ;
            avlNode4.right = avlNode2.removeMin(avlNode4);
            avlNode4.left = this.left;
            avlNode4.distinctElements = this.distinctElements - 1;
            avlNode4.totalCount = this.totalCount - ((long) i);
            return avlNode4.rebalance();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        public AvlNode<E> floor(Comparator<? super E> comparator, E e2) {
            int iCompare = comparator.compare(e2, this.elem);
            if (iCompare > 0) {
                AvlNode<E> avlNode = this.right;
                return avlNode == null ? this : (AvlNode) MoreObjects.firstNonNull(avlNode.floor(comparator, e2), this);
            }
            if (iCompare == 0) {
                return this;
            }
            AvlNode<E> avlNode2 = this.left;
            if (avlNode2 == null) {
                return null;
            }
            return avlNode2.floor(comparator, e2);
        }

        public static int height(AvlNode<?> avlNode) {
            if (avlNode == null) {
                return 0;
            }
            return avlNode.height;
        }

        private AvlNode<E> rebalance() {
            int iBalanceFactor = balanceFactor();
            if (iBalanceFactor == -2) {
                if (this.right.balanceFactor() > 0) {
                    this.right = this.right.rotateRight();
                }
                return rotateLeft();
            }
            if (iBalanceFactor != 2) {
                recomputeHeight();
                return this;
            }
            if (this.left.balanceFactor() < 0) {
                this.left = this.left.rotateLeft();
            }
            return rotateRight();
        }

        private void recompute() {
            recomputeMultiset();
            recomputeHeight();
        }

        private void recomputeHeight() {
            this.height = Math.max(height(this.left), height(this.right)) + 1;
        }

        private void recomputeMultiset() {
            this.distinctElements = TreeMultiset.distinctElements(this.right) + TreeMultiset.distinctElements(this.left) + 1;
            this.totalCount = totalCount(this.right) + totalCount(this.left) + ((long) this.elemCount);
        }

        private AvlNode<E> removeMax(AvlNode<E> avlNode) {
            AvlNode<E> avlNode2 = this.right;
            if (avlNode2 == null) {
                return this.left;
            }
            this.right = avlNode2.removeMax(avlNode);
            this.distinctElements--;
            this.totalCount -= (long) avlNode.elemCount;
            return rebalance();
        }

        private AvlNode<E> removeMin(AvlNode<E> avlNode) {
            AvlNode<E> avlNode2 = this.left;
            if (avlNode2 == null) {
                return this.right;
            }
            this.left = avlNode2.removeMin(avlNode);
            this.distinctElements--;
            this.totalCount -= (long) avlNode.elemCount;
            return rebalance();
        }

        private AvlNode<E> rotateLeft() {
            Preconditions.checkState(this.right != null);
            AvlNode<E> avlNode = this.right;
            this.right = avlNode.left;
            avlNode.left = this;
            avlNode.totalCount = this.totalCount;
            avlNode.distinctElements = this.distinctElements;
            recomputeMultiset();
            recomputeHeight();
            avlNode.recomputeHeight();
            return avlNode;
        }

        private AvlNode<E> rotateRight() {
            Preconditions.checkState(this.left != null);
            AvlNode<E> avlNode = this.left;
            this.left = avlNode.right;
            avlNode.right = this;
            avlNode.totalCount = this.totalCount;
            avlNode.distinctElements = this.distinctElements;
            recomputeMultiset();
            recomputeHeight();
            avlNode.recomputeHeight();
            return avlNode;
        }

        public static long totalCount(AvlNode<?> avlNode) {
            if (avlNode == null) {
                return 0L;
            }
            return avlNode.totalCount;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public AvlNode<E> add(Comparator<? super E> comparator, E e2, int i, int[] iArr) {
            int iCompare = comparator.compare(e2, this.elem);
            if (iCompare < 0) {
                AvlNode<E> avlNode = this.left;
                if (avlNode == null) {
                    iArr[0] = 0;
                    addLeftChild(e2, i);
                    return this;
                }
                int i2 = avlNode.height;
                this.left = avlNode.add(comparator, e2, i, iArr);
                if (iArr[0] == 0) {
                    this.distinctElements++;
                }
                this.totalCount += (long) i;
                return this.left.height == i2 ? this : rebalance();
            }
            if (iCompare <= 0) {
                int i3 = this.elemCount;
                iArr[0] = i3;
                long j = i;
                Preconditions.checkArgument(((long) i3) + j <= 2147483647L);
                this.elemCount += i;
                this.totalCount += j;
                return this;
            }
            AvlNode<E> avlNode2 = this.right;
            if (avlNode2 == null) {
                iArr[0] = 0;
                addRightChild(e2, i);
                return this;
            }
            int i4 = avlNode2.height;
            this.right = avlNode2.add(comparator, e2, i, iArr);
            if (iArr[0] == 0) {
                this.distinctElements++;
            }
            this.totalCount += (long) i;
            return this.right.height == i4 ? this : rebalance();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int count(Comparator<? super E> comparator, E e2) {
            int iCompare = comparator.compare(e2, this.elem);
            if (iCompare < 0) {
                AvlNode<E> avlNode = this.left;
                if (avlNode == null) {
                    return 0;
                }
                return avlNode.count(comparator, e2);
            }
            if (iCompare <= 0) {
                return this.elemCount;
            }
            AvlNode<E> avlNode2 = this.right;
            if (avlNode2 == null) {
                return 0;
            }
            return avlNode2.count(comparator, e2);
        }

        public int getCount() {
            return this.elemCount;
        }

        public E getElement() {
            return this.elem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public AvlNode<E> remove(Comparator<? super E> comparator, E e2, int i, int[] iArr) {
            int iCompare = comparator.compare(e2, this.elem);
            if (iCompare < 0) {
                AvlNode<E> avlNode = this.left;
                if (avlNode == null) {
                    iArr[0] = 0;
                    return this;
                }
                this.left = avlNode.remove(comparator, e2, i, iArr);
                if (iArr[0] > 0) {
                    if (i >= iArr[0]) {
                        this.distinctElements--;
                        this.totalCount -= (long) iArr[0];
                    } else {
                        this.totalCount -= (long) i;
                    }
                }
                return iArr[0] == 0 ? this : rebalance();
            }
            if (iCompare <= 0) {
                int i2 = this.elemCount;
                iArr[0] = i2;
                if (i >= i2) {
                    return deleteMe();
                }
                this.elemCount = i2 - i;
                this.totalCount -= (long) i;
                return this;
            }
            AvlNode<E> avlNode2 = this.right;
            if (avlNode2 == null) {
                iArr[0] = 0;
                return this;
            }
            this.right = avlNode2.remove(comparator, e2, i, iArr);
            if (iArr[0] > 0) {
                if (i >= iArr[0]) {
                    this.distinctElements--;
                    this.totalCount -= (long) iArr[0];
                } else {
                    this.totalCount -= (long) i;
                }
            }
            return rebalance();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public AvlNode<E> setCount(Comparator<? super E> comparator, E e2, int i, int[] iArr) {
            int iCompare = comparator.compare(e2, this.elem);
            if (iCompare < 0) {
                AvlNode<E> avlNode = this.left;
                if (avlNode == null) {
                    iArr[0] = 0;
                    if (i > 0) {
                        addLeftChild(e2, i);
                    }
                    return this;
                }
                this.left = avlNode.setCount(comparator, e2, i, iArr);
                if (i == 0 && iArr[0] != 0) {
                    this.distinctElements--;
                } else if (i > 0 && iArr[0] == 0) {
                    this.distinctElements++;
                }
                this.totalCount += (long) (i - iArr[0]);
                return rebalance();
            }
            if (iCompare <= 0) {
                int i2 = this.elemCount;
                iArr[0] = i2;
                if (i == 0) {
                    return deleteMe();
                }
                this.totalCount += (long) (i - i2);
                this.elemCount = i;
                return this;
            }
            AvlNode<E> avlNode2 = this.right;
            if (avlNode2 == null) {
                iArr[0] = 0;
                if (i > 0) {
                    addRightChild(e2, i);
                }
                return this;
            }
            this.right = avlNode2.setCount(comparator, e2, i, iArr);
            if (i == 0 && iArr[0] != 0) {
                this.distinctElements--;
            } else if (i > 0 && iArr[0] == 0) {
                this.distinctElements++;
            }
            this.totalCount += (long) (i - iArr[0]);
            return rebalance();
        }

        public String toString() {
            return new Multisets.ImmutableEntry(getElement(), getCount()).toString();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public AvlNode<E> setCount(Comparator<? super E> comparator, E e2, int i, int i2, int[] iArr) {
            int iCompare = comparator.compare(e2, this.elem);
            if (iCompare < 0) {
                AvlNode<E> avlNode = this.left;
                if (avlNode == null) {
                    iArr[0] = 0;
                    if (i == 0 && i2 > 0) {
                        addLeftChild(e2, i2);
                    }
                    return this;
                }
                this.left = avlNode.setCount(comparator, e2, i, i2, iArr);
                if (iArr[0] == i) {
                    if (i2 == 0 && iArr[0] != 0) {
                        this.distinctElements--;
                    } else if (i2 > 0 && iArr[0] == 0) {
                        this.distinctElements++;
                    }
                    this.totalCount += (long) (i2 - iArr[0]);
                }
                return rebalance();
            }
            if (iCompare > 0) {
                AvlNode<E> avlNode2 = this.right;
                if (avlNode2 == null) {
                    iArr[0] = 0;
                    if (i == 0 && i2 > 0) {
                        addRightChild(e2, i2);
                    }
                    return this;
                }
                this.right = avlNode2.setCount(comparator, e2, i, i2, iArr);
                if (iArr[0] == i) {
                    if (i2 == 0 && iArr[0] != 0) {
                        this.distinctElements--;
                    } else if (i2 > 0 && iArr[0] == 0) {
                        this.distinctElements++;
                    }
                    this.totalCount += (long) (i2 - iArr[0]);
                }
                return rebalance();
            }
            int i3 = this.elemCount;
            iArr[0] = i3;
            if (i == i3) {
                if (i2 == 0) {
                    return deleteMe();
                }
                this.totalCount += (long) (i2 - i3);
                this.elemCount = i2;
            }
            return this;
        }
    }
}
