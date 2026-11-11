package com.lagradost.cloudstream3.utils;

import androidx.exifinterface.media.ExifInterface;
import io.ktor.http.ContentDisposition;
import j$.lang.Iterable;
import j$.util.Spliterator;
import j$.util.stream.Stream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.mozilla.javascript.optimizer.Signatures;

/* JADX INFO: compiled from: AtomicList.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u001e\n\u0002\b\u0007\n\u0002\u0010(\n\u0000\n\u0002\u0010*\n\u0002\b\u0006\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00060\u0003j\u0002`\u0004B\u0017\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\b\u001a\u0002H\t\"\u0004\b\u0001\u0010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\u000b¢\u0006\u0002\u0010\fJ \u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00100\u000fJ\"\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0014\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u000fJ\n\u0010\u0017\u001a\u00020\u0010H\u0096\u0080\u0004J\u0017\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00028\u0000H\u0096\u0082\u0004¢\u0006\u0002\u0010\u001aJ\u0018\u0010\u001b\u001a\u00020\u00102\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00000\u001dH\u0096\u0080\u0004J\u0017\u0010\u001e\u001a\u00028\u00002\u0006\u0010\u001f\u001a\u00020\u0014H\u0096\u0082\u0004¢\u0006\u0002\u0010 J\u0017\u0010!\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00028\u0000H\u0096\u0080\u0004¢\u0006\u0002\u0010\"J\u0017\u0010#\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00028\u0000H\u0096\u0080\u0004¢\u0006\u0002\u0010\"J\u0010\u0010$\u001a\b\u0012\u0004\u0012\u00028\u00000%H\u0096\u0082\u0004J\u0010\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000'H\u0096\u0080\u0004J\u0018\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000'2\u0006\u0010\u001f\u001a\u00020\u0014H\u0096\u0080\u0004J \u0010(\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010)\u001a\u00020\u00142\u0006\u0010*\u001a\u00020\u0014H\u0096\u0080\u0004J\u001c\u0010+\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u0019\u001a\u00028\u0000H\u0086\u0002¢\u0006\u0002\u0010,J\u001d\u0010+\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00000\u001dH\u0086\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u0015\u0010\u0013\u001a\u00020\u00148VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006-"}, d2 = {"Lcom/lagradost/cloudstream3/utils/AtomicList;", ExifInterface.GPS_DIRECTION_TRUE, "", "", "Lkotlinx/atomicfu/locks/SynchronizedObject;", "delegate", "<init>", "(Ljava/util/List;)V", "withLock", "R", "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "filter", "predicate", "Lkotlin/Function1;", "", "distinctBy", "selector", ContentDisposition.Parameters.Size, "", "getSize", "()I", "isEmpty", "contains", "element", Signatures.MATH_TO_BOOLEAN, "containsAll", "elements", "", "get", "index", "(I)Ljava/lang/Object;", "indexOf", Signatures.MATH_TO_INT32, "lastIndexOf", "iterator", "", "listIterator", "", "subList", "fromIndex", "toIndex", "plus", "(Ljava/lang/Object;)Lcom/lagradost/cloudstream3/utils/AtomicList;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class AtomicList<T> implements List<T>, KMappedMarker, j$.util.List {
    private final List<T> delegate;

    /* JADX WARN: Multi-variable type inference failed */
    public AtomicList() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @Override // java.util.List
    public void add(int i, T t) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public boolean add(T t) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public boolean addAll(int i, Collection<? extends T> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(Collection<? extends T> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public void addFirst(T t) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public void addLast(T t) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.lang.Iterable, j$.util.Collection, j$.lang.Iterable
    public /* synthetic */ void forEach(Consumer consumer) {
        Iterable.CC.$default$forEach(this, consumer);
    }

    @Override // java.util.Collection, j$.util.Collection
    public /* synthetic */ Stream parallelStream() {
        return j$.util.Collection.CC.$default$parallelStream(this);
    }

    @Override // java.util.Collection
    public /* synthetic */ java.util.stream.Stream parallelStream() {
        return Stream.Wrapper.convert(parallelStream());
    }

    @Override // java.util.List
    public T remove(int i) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public T removeFirst() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection, j$.util.Collection
    public /* synthetic */ boolean removeIf(Predicate predicate) {
        return j$.util.Collection.CC.$default$removeIf(this, predicate);
    }

    public T removeLast() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, j$.util.List
    public void replaceAll(UnaryOperator<T> unaryOperator) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public T set(int i, T t) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, j$.util.List
    public void sort(Comparator<? super T> comparator) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable, j$.util.List, j$.util.Collection, j$.lang.Iterable
    public /* synthetic */ Spliterator spliterator() {
        return j$.util.List.CC.$default$spliterator(this);
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public /* synthetic */ java.util.Spliterator spliterator() {
        return Spliterator.Wrapper.convert(spliterator());
    }

    @Override // java.util.Collection, j$.util.Collection
    public /* synthetic */ Stream stream() {
        return j$.util.Collection.CC.$default$stream(this);
    }

    @Override // java.util.Collection
    public /* synthetic */ java.util.stream.Stream stream() {
        return Stream.Wrapper.convert(stream());
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    @Override // java.util.Collection, j$.util.Collection
    public /* synthetic */ Object[] toArray(IntFunction intFunction) {
        return toArray((Object[]) intFunction.apply(0));
    }

    @Override // java.util.List, java.util.Collection
    public <T> T[] toArray(T[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        return (T[]) CollectionToArray.toArray(this, array);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AtomicList(List<? extends T> delegate) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.delegate = delegate;
    }

    @Override // java.util.List, java.util.Collection
    public final /* bridge */ int size() {
        return getSize();
    }

    public /* synthetic */ AtomicList(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? CollectionsKt.emptyList() : list);
    }

    public final <R> R withLock(Function0<? extends R> block) {
        R rInvoke;
        Intrinsics.checkNotNullParameter(block, "block");
        synchronized (this) {
            rInvoke = block.invoke();
        }
        return rInvoke;
    }

    public final AtomicList<T> filter(Function1<? super T, Boolean> predicate) {
        AtomicList<T> atomicList;
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        synchronized (this) {
            List<T> list = this.delegate;
            ArrayList arrayList = new ArrayList();
            for (T t : list) {
                if (predicate.invoke(t).booleanValue()) {
                    arrayList.add(t);
                }
            }
            atomicList = new AtomicList<>(arrayList);
        }
        return atomicList;
    }

    public final AtomicList<T> distinctBy(Function1<? super T, ? extends Object> selector) {
        AtomicList<T> atomicList;
        Intrinsics.checkNotNullParameter(selector, "selector");
        synchronized (this) {
            List<T> list = this.delegate;
            HashSet hashSet = new HashSet();
            ArrayList arrayList = new ArrayList();
            for (T t : list) {
                if (hashSet.add(selector.invoke(t))) {
                    arrayList.add(t);
                }
            }
            atomicList = new AtomicList<>(arrayList);
        }
        return atomicList;
    }

    public int getSize() {
        int size;
        synchronized (this) {
            size = this.delegate.size();
        }
        return size;
    }

    @Override // java.util.List, java.util.Collection
    public boolean isEmpty() {
        boolean zIsEmpty;
        synchronized (this) {
            zIsEmpty = this.delegate.isEmpty();
        }
        return zIsEmpty;
    }

    @Override // java.util.List, java.util.Collection
    public boolean contains(Object element) {
        boolean zContains;
        synchronized (this) {
            zContains = this.delegate.contains(element);
        }
        return zContains;
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection<?> elements) {
        boolean zContainsAll;
        Intrinsics.checkNotNullParameter(elements, "elements");
        synchronized (this) {
            zContainsAll = this.delegate.containsAll(elements);
        }
        return zContainsAll;
    }

    @Override // java.util.List
    public T get(int index) {
        T t;
        synchronized (this) {
            t = this.delegate.get(index);
        }
        return t;
    }

    @Override // java.util.List
    public int indexOf(Object element) {
        int iIndexOf;
        synchronized (this) {
            iIndexOf = this.delegate.indexOf(element);
        }
        return iIndexOf;
    }

    @Override // java.util.List
    public int lastIndexOf(Object element) {
        int iLastIndexOf;
        synchronized (this) {
            iLastIndexOf = this.delegate.lastIndexOf(element);
        }
        return iLastIndexOf;
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<T> iterator() {
        return this.delegate.iterator();
    }

    @Override // java.util.List
    public ListIterator<T> listIterator() {
        return this.delegate.listIterator();
    }

    @Override // java.util.List
    public ListIterator<T> listIterator(int index) {
        return this.delegate.listIterator(index);
    }

    @Override // java.util.List
    public List<T> subList(int fromIndex, int toIndex) {
        return this.delegate.subList(fromIndex, toIndex);
    }

    public final AtomicList<T> plus(T element) {
        AtomicList<T> atomicList;
        synchronized (this) {
            atomicList = new AtomicList<>(CollectionsKt.plus((Collection) this.delegate, (Object) element));
        }
        return atomicList;
    }

    public final AtomicList<T> plus(Collection<? extends T> elements) {
        AtomicList<T> atomicList;
        Intrinsics.checkNotNullParameter(elements, "elements");
        synchronized (this) {
            atomicList = new AtomicList<>(CollectionsKt.plus((Collection) this.delegate, (Iterable) elements));
        }
        return atomicList;
    }
}
