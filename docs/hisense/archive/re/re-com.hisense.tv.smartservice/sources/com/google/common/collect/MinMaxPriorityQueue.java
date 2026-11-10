package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.j2objc.annotations.Weak;
import java.util.AbstractQueue;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

/* JADX INFO: loaded from: classes.dex */
@Beta
@GwtCompatible
public final class MinMaxPriorityQueue<E> extends AbstractQueue<E> {
    public static final int DEFAULT_CAPACITY = 11;
    public static final int EVEN_POWERS_OF_TWO = 1431655765;
    public static final int ODD_POWERS_OF_TWO = -1431655766;
    public final MinMaxPriorityQueue<E>.Heap maxHeap;

    @VisibleForTesting
    public final int maximumSize;
    public final MinMaxPriorityQueue<E>.Heap minHeap;
    public int modCount;
    public Object[] queue;
    public int size;

    @Beta
    public static final class Builder<B> {
        public static final int UNSET_EXPECTED_SIZE = -1;
        public final Comparator<B> comparator;
        public int expectedSize;
        public int maximumSize;

        /* JADX INFO: Access modifiers changed from: private */
        public <T extends B> Ordering<T> ordering() {
            return Ordering.from(this.comparator);
        }

        public <T extends B> MinMaxPriorityQueue<T> create() {
            return create(Collections.emptySet());
        }

        @CanIgnoreReturnValue
        public Builder<B> expectedSize(int i) {
            Preconditions.checkArgument(i >= 0);
            this.expectedSize = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<B> maximumSize(int i) {
            Preconditions.checkArgument(i > 0);
            this.maximumSize = i;
            return this;
        }

        public Builder(Comparator<B> comparator) {
            this.expectedSize = -1;
            this.maximumSize = Integer.MAX_VALUE;
            if (comparator == null) {
                throw new NullPointerException();
            }
            this.comparator = comparator;
        }

        public <T extends B> MinMaxPriorityQueue<T> create(Iterable<? extends T> iterable) {
            MinMaxPriorityQueue<T> minMaxPriorityQueue = new MinMaxPriorityQueue<>(this, MinMaxPriorityQueue.initialQueueSize(this.expectedSize, this.maximumSize, iterable));
            Iterator<? extends T> it = iterable.iterator();
            while (it.hasNext()) {
                minMaxPriorityQueue.offer(it.next());
            }
            return minMaxPriorityQueue;
        }
    }

    private class Heap {
        public final Ordering<E> ordering;

        @Weak
        public MinMaxPriorityQueue<E>.Heap otherHeap;

        public Heap(Ordering<E> ordering) {
            this.ordering = ordering;
        }

        private int getGrandparentIndex(int i) {
            return (((i - 1) / 2) - 1) / 2;
        }

        private int getLeftChildIndex(int i) {
            return (i * 2) + 1;
        }

        private int getParentIndex(int i) {
            return (i - 1) / 2;
        }

        private int getRightChildIndex(int i) {
            return (i * 2) + 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean verifyIndex(int i) {
            int i2 = i * 2;
            int i3 = i2 + 1;
            if (i3 < MinMaxPriorityQueue.this.size && compareElements(i, i3) > 0) {
                return false;
            }
            int i4 = i2 + 2;
            if (i4 < MinMaxPriorityQueue.this.size && compareElements(i, i4) > 0) {
                return false;
            }
            if (i <= 0 || compareElements(i, (i - 1) / 2) <= 0) {
                return i <= 2 || compareElements(getGrandparentIndex(i), i) <= 0;
            }
            return false;
        }

        public void bubbleUp(int i, E e2) {
            Heap heap;
            int iCrossOverUp = crossOverUp(i, e2);
            if (iCrossOverUp == i) {
                iCrossOverUp = i;
                heap = this;
            } else {
                heap = this.otherHeap;
            }
            heap.bubbleUpAlternatingLevels(iCrossOverUp, e2);
        }

        @CanIgnoreReturnValue
        public int bubbleUpAlternatingLevels(int i, E e2) {
            while (i > 2) {
                int grandparentIndex = getGrandparentIndex(i);
                Object objElementData = MinMaxPriorityQueue.this.elementData(grandparentIndex);
                if (this.ordering.compare((E) objElementData, e2) <= 0) {
                    break;
                }
                MinMaxPriorityQueue.this.queue[i] = objElementData;
                i = grandparentIndex;
            }
            MinMaxPriorityQueue.this.queue[i] = e2;
            return i;
        }

        public int compareElements(int i, int i2) {
            return this.ordering.compare((E) MinMaxPriorityQueue.this.elementData(i), (E) MinMaxPriorityQueue.this.elementData(i2));
        }

        public int crossOver(int i, E e2) {
            int iFindMinChild = findMinChild(i);
            if (iFindMinChild <= 0 || this.ordering.compare((E) MinMaxPriorityQueue.this.elementData(iFindMinChild), e2) >= 0) {
                return crossOverUp(i, e2);
            }
            MinMaxPriorityQueue.this.queue[i] = MinMaxPriorityQueue.this.elementData(iFindMinChild);
            MinMaxPriorityQueue.this.queue[iFindMinChild] = e2;
            return iFindMinChild;
        }

        public int crossOverUp(int i, E e2) {
            int i2;
            if (i == 0) {
                MinMaxPriorityQueue.this.queue[0] = e2;
                return 0;
            }
            int i3 = (i - 1) / 2;
            Object objElementData = MinMaxPriorityQueue.this.elementData(i3);
            if (i3 != 0 && (i2 = (((i3 - 1) / 2) * 2) + 2) != i3 && (i2 * 2) + 1 >= MinMaxPriorityQueue.this.size) {
                Object objElementData2 = MinMaxPriorityQueue.this.elementData(i2);
                if (this.ordering.compare((E) objElementData2, (E) objElementData) < 0) {
                    i3 = i2;
                    objElementData = objElementData2;
                }
            }
            if (this.ordering.compare((E) objElementData, e2) >= 0) {
                MinMaxPriorityQueue.this.queue[i] = e2;
                return i;
            }
            MinMaxPriorityQueue.this.queue[i] = objElementData;
            MinMaxPriorityQueue.this.queue[i3] = e2;
            return i3;
        }

        public int fillHoleAt(int i) {
            while (true) {
                int iFindMinGrandChild = findMinGrandChild(i);
                if (iFindMinGrandChild <= 0) {
                    return i;
                }
                MinMaxPriorityQueue.this.queue[i] = MinMaxPriorityQueue.this.elementData(iFindMinGrandChild);
                i = iFindMinGrandChild;
            }
        }

        public int findMin(int i, int i2) {
            if (i >= MinMaxPriorityQueue.this.size) {
                return -1;
            }
            Preconditions.checkState(i > 0);
            int iMin = Math.min(i, MinMaxPriorityQueue.this.size - i2) + i2;
            for (int i3 = i + 1; i3 < iMin; i3++) {
                if (compareElements(i3, i) < 0) {
                    i = i3;
                }
            }
            return i;
        }

        public int findMinChild(int i) {
            return findMin((i * 2) + 1, 2);
        }

        public int findMinGrandChild(int i) {
            int i2 = (i * 2) + 1;
            if (i2 < 0) {
                return -1;
            }
            return findMin((i2 * 2) + 1, 4);
        }

        public int swapWithConceptuallyLastElement(E e2) {
            int i;
            int i2 = (MinMaxPriorityQueue.this.size - 1) / 2;
            if (i2 != 0 && (i = (((i2 - 1) / 2) * 2) + 2) != i2 && (i * 2) + 1 >= MinMaxPriorityQueue.this.size) {
                Object objElementData = MinMaxPriorityQueue.this.elementData(i);
                if (this.ordering.compare((E) objElementData, e2) < 0) {
                    MinMaxPriorityQueue.this.queue[i] = e2;
                    MinMaxPriorityQueue.this.queue[MinMaxPriorityQueue.this.size] = objElementData;
                    return i;
                }
            }
            return MinMaxPriorityQueue.this.size;
        }

        public MoveDesc<E> tryCrossOverAndBubbleUp(int i, int i2, E e2) {
            int iCrossOver = crossOver(i2, e2);
            if (iCrossOver == i2) {
                return null;
            }
            Object objElementData = iCrossOver < i ? MinMaxPriorityQueue.this.elementData(i) : MinMaxPriorityQueue.this.elementData((i - 1) / 2);
            if (this.otherHeap.bubbleUpAlternatingLevels(iCrossOver, e2) < i) {
                return new MoveDesc<>(e2, objElementData);
            }
            return null;
        }
    }

    static class MoveDesc<E> {
        public final E replaced;
        public final E toTrickle;

        public MoveDesc(E e2, E e3) {
            this.toTrickle = e2;
            this.replaced = e3;
        }
    }

    private class QueueIterator implements Iterator<E> {
        public boolean canRemove;
        public int cursor;
        public int expectedModCount;
        public Queue<E> forgetMeNot;
        public E lastFromForgetMeNot;
        public int nextCursor;
        public List<E> skipMe;

        public QueueIterator() {
            this.cursor = -1;
            this.nextCursor = -1;
            this.expectedModCount = MinMaxPriorityQueue.this.modCount;
        }

        private void checkModCount() {
            if (MinMaxPriorityQueue.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

        private boolean foundAndRemovedExactReference(Iterable<E> iterable, E e2) {
            Iterator<E> it = iterable.iterator();
            while (it.hasNext()) {
                if (it.next() == e2) {
                    it.remove();
                    return true;
                }
            }
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private void nextNotInSkipMe(int i) {
            if (this.nextCursor < i) {
                if (this.skipMe != null) {
                    while (i < MinMaxPriorityQueue.this.size() && foundAndRemovedExactReference(this.skipMe, MinMaxPriorityQueue.this.elementData(i))) {
                        i++;
                    }
                }
                this.nextCursor = i;
            }
        }

        private boolean removeExact(Object obj) {
            for (int i = 0; i < MinMaxPriorityQueue.this.size; i++) {
                if (MinMaxPriorityQueue.this.queue[i] == obj) {
                    MinMaxPriorityQueue.this.removeAt(i);
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            checkModCount();
            nextNotInSkipMe(this.cursor + 1);
            if (this.nextCursor < MinMaxPriorityQueue.this.size()) {
                return true;
            }
            Queue<E> queue = this.forgetMeNot;
            return (queue == null || queue.isEmpty()) ? false : true;
        }

        @Override // java.util.Iterator
        public E next() {
            checkModCount();
            nextNotInSkipMe(this.cursor + 1);
            if (this.nextCursor < MinMaxPriorityQueue.this.size()) {
                this.cursor = this.nextCursor;
                this.canRemove = true;
                return (E) MinMaxPriorityQueue.this.elementData(this.cursor);
            }
            if (this.forgetMeNot != null) {
                this.cursor = MinMaxPriorityQueue.this.size();
                this.lastFromForgetMeNot = this.forgetMeNot.poll();
                E e2 = this.lastFromForgetMeNot;
                if (e2 != null) {
                    this.canRemove = true;
                    return e2;
                }
            }
            throw new NoSuchElementException("iterator moved past last element in queue.");
        }

        @Override // java.util.Iterator
        public void remove() {
            Preconditions.checkState(this.canRemove, "no calls to next() since the last call to remove()");
            checkModCount();
            this.canRemove = false;
            this.expectedModCount++;
            if (this.cursor >= MinMaxPriorityQueue.this.size()) {
                Preconditions.checkState(removeExact(this.lastFromForgetMeNot));
                this.lastFromForgetMeNot = null;
                return;
            }
            MoveDesc<E> moveDescRemoveAt = MinMaxPriorityQueue.this.removeAt(this.cursor);
            if (moveDescRemoveAt != null) {
                if (this.forgetMeNot == null) {
                    this.forgetMeNot = new ArrayDeque();
                    this.skipMe = new ArrayList(3);
                }
                if (!foundAndRemovedExactReference(this.skipMe, moveDescRemoveAt.toTrickle)) {
                    this.forgetMeNot.add(moveDescRemoveAt.toTrickle);
                }
                if (!foundAndRemovedExactReference(this.forgetMeNot, moveDescRemoveAt.replaced)) {
                    this.skipMe.add(moveDescRemoveAt.replaced);
                }
            }
            this.cursor--;
            this.nextCursor--;
        }
    }

    private int calculateNewCapacity() {
        int length = this.queue.length;
        return capAtMaximumSize(length < 64 ? (length + 1) * 2 : IntMath.checkedMultiply(length / 2, 3), this.maximumSize);
    }

    public static int capAtMaximumSize(int i, int i2) {
        return Math.min(i - 1, i2) + 1;
    }

    public static <E extends Comparable<E>> MinMaxPriorityQueue<E> create() {
        return new Builder(Ordering.natural()).create();
    }

    public static Builder<Comparable> expectedSize(int i) {
        return new Builder(Ordering.natural()).expectedSize(i);
    }

    private MoveDesc<E> fillHole(int i, E e2) {
        MinMaxPriorityQueue<E>.Heap heapHeapForIndex = heapForIndex(i);
        int iFillHoleAt = heapHeapForIndex.fillHoleAt(i);
        int iBubbleUpAlternatingLevels = heapHeapForIndex.bubbleUpAlternatingLevels(iFillHoleAt, e2);
        if (iBubbleUpAlternatingLevels == iFillHoleAt) {
            return heapHeapForIndex.tryCrossOverAndBubbleUp(i, iFillHoleAt, e2);
        }
        if (iBubbleUpAlternatingLevels < i) {
            return new MoveDesc<>(e2, elementData(i));
        }
        return null;
    }

    private int getMaxElementIndex() {
        int i = this.size;
        if (i != 1) {
            return (i == 2 || this.maxHeap.compareElements(1, 2) <= 0) ? 1 : 2;
        }
        return 0;
    }

    private void growIfNeeded() {
        if (this.size > this.queue.length) {
            Object[] objArr = new Object[calculateNewCapacity()];
            Object[] objArr2 = this.queue;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.queue = objArr;
        }
    }

    private MinMaxPriorityQueue<E>.Heap heapForIndex(int i) {
        return isEvenLevel(i) ? this.minHeap : this.maxHeap;
    }

    @VisibleForTesting
    public static int initialQueueSize(int i, int i2, Iterable<?> iterable) {
        if (i == -1) {
            i = 11;
        }
        if (iterable instanceof Collection) {
            i = Math.max(i, ((Collection) iterable).size());
        }
        return capAtMaximumSize(i, i2);
    }

    @VisibleForTesting
    public static boolean isEvenLevel(int i) {
        int i2 = ~(~(i + 1));
        Preconditions.checkState(i2 > 0, "negative index");
        return (1431655765 & i2) > (i2 & ODD_POWERS_OF_TWO);
    }

    public static Builder<Comparable> maximumSize(int i) {
        return new Builder(Ordering.natural()).maximumSize(i);
    }

    public static <B> Builder<B> orderedBy(Comparator<B> comparator) {
        return new Builder<>(comparator);
    }

    private E removeAndGet(int i) {
        E eElementData = elementData(i);
        removeAt(i);
        return eElementData;
    }

    @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection, java.util.Queue
    @CanIgnoreReturnValue
    public boolean add(E e2) {
        offer(e2);
        return true;
    }

    @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection
    @CanIgnoreReturnValue
    public boolean addAll(Collection<? extends E> collection) {
        Iterator<? extends E> it = collection.iterator();
        boolean z = false;
        while (it.hasNext()) {
            offer(it.next());
            z = true;
        }
        return z;
    }

    @VisibleForTesting
    public int capacity() {
        return this.queue.length;
    }

    @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection
    public void clear() {
        for (int i = 0; i < this.size; i++) {
            this.queue[i] = null;
        }
        this.size = 0;
    }

    public Comparator<? super E> comparator() {
        return this.minHeap.ordering;
    }

    public E elementData(int i) {
        return (E) this.queue[i];
    }

    @VisibleForTesting
    public boolean isIntact() {
        for (int i = 1; i < this.size; i++) {
            if (!heapForIndex(i).verifyIndex(i)) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return new QueueIterator();
    }

    @Override // java.util.Queue
    @CanIgnoreReturnValue
    public boolean offer(E e2) {
        if (e2 == null) {
            throw new NullPointerException();
        }
        this.modCount++;
        int i = this.size;
        this.size = i + 1;
        growIfNeeded();
        heapForIndex(i).bubbleUp(i, e2);
        return this.size <= this.maximumSize || pollLast() != e2;
    }

    @Override // java.util.Queue
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return elementData(0);
    }

    public E peekFirst() {
        return peek();
    }

    public E peekLast() {
        if (isEmpty()) {
            return null;
        }
        return elementData(getMaxElementIndex());
    }

    @Override // java.util.Queue
    @CanIgnoreReturnValue
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        return removeAndGet(0);
    }

    @CanIgnoreReturnValue
    public E pollFirst() {
        return poll();
    }

    @CanIgnoreReturnValue
    public E pollLast() {
        if (isEmpty()) {
            return null;
        }
        return removeAndGet(getMaxElementIndex());
    }

    @VisibleForTesting
    @CanIgnoreReturnValue
    public MoveDesc<E> removeAt(int i) {
        Preconditions.checkPositionIndex(i, this.size, FirebaseAnalytics.Param.INDEX);
        this.modCount++;
        this.size--;
        int i2 = this.size;
        if (i2 == i) {
            this.queue[i2] = null;
            return null;
        }
        E eElementData = elementData(i2);
        int iSwapWithConceptuallyLastElement = heapForIndex(this.size).swapWithConceptuallyLastElement(eElementData);
        if (iSwapWithConceptuallyLastElement == i) {
            this.queue[this.size] = null;
            return null;
        }
        E eElementData2 = elementData(this.size);
        this.queue[this.size] = null;
        MoveDesc<E> moveDescFillHole = fillHole(i, eElementData2);
        if (iSwapWithConceptuallyLastElement < i) {
            return moveDescFillHole == null ? new MoveDesc<>(eElementData, eElementData2) : new MoveDesc<>(eElementData, moveDescFillHole.replaced);
        }
        return moveDescFillHole;
    }

    @CanIgnoreReturnValue
    public E removeFirst() {
        return remove();
    }

    @CanIgnoreReturnValue
    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return removeAndGet(getMaxElementIndex());
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public int size() {
        return this.size;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public Object[] toArray() {
        int i = this.size;
        Object[] objArr = new Object[i];
        System.arraycopy(this.queue, 0, objArr, 0, i);
        return objArr;
    }

    public MinMaxPriorityQueue(Builder<? super E> builder, int i) {
        Ordering ordering = builder.ordering();
        this.minHeap = new Heap(ordering);
        this.maxHeap = new Heap(ordering.reverse());
        MinMaxPriorityQueue<E>.Heap heap = this.minHeap;
        MinMaxPriorityQueue<E>.Heap heap2 = this.maxHeap;
        heap.otherHeap = heap2;
        heap2.otherHeap = heap;
        this.maximumSize = builder.maximumSize;
        this.queue = new Object[i];
    }

    public static <E extends Comparable<E>> MinMaxPriorityQueue<E> create(Iterable<? extends E> iterable) {
        return new Builder(Ordering.natural()).create(iterable);
    }
}
