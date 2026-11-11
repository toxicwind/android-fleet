package org.jsoup.select;

import j$.lang.Iterable;
import j$.util.Collection;
import j$.util.List;
import j$.util.Spliterator;
import j$.util.function.Function$CC;
import j$.util.stream.Stream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import org.jsoup.helper.Validate;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Node;

/* JADX INFO: loaded from: classes8.dex */
public class Nodes<T extends Node> extends ArrayList<T> implements List {
    @Override // java.util.ArrayList, java.lang.Iterable, j$.util.Collection, j$.lang.Iterable
    public /* synthetic */ void forEach(Consumer consumer) {
        Iterable.CC.$default$forEach(this, consumer);
    }

    @Override // java.util.Collection, j$.util.Collection
    public /* synthetic */ Stream parallelStream() {
        return Collection.CC.$default$parallelStream(this);
    }

    @Override // java.util.Collection
    public /* synthetic */ java.util.stream.Stream parallelStream() {
        return Stream.Wrapper.convert(parallelStream());
    }

    @Override // java.util.ArrayList, java.util.List, j$.util.List
    public /* synthetic */ void sort(Comparator comparator) {
        List.CC.$default$sort(this, comparator);
    }

    @Override // java.util.ArrayList, java.util.Collection, java.lang.Iterable, java.util.List, j$.util.List, j$.util.Collection, j$.lang.Iterable
    public /* synthetic */ Spliterator spliterator() {
        return List.CC.$default$spliterator(this);
    }

    @Override // java.util.ArrayList, java.util.Collection, java.lang.Iterable, java.util.List
    public /* synthetic */ java.util.Spliterator spliterator() {
        return Spliterator.Wrapper.convert(spliterator());
    }

    @Override // java.util.Collection, j$.util.Collection
    public /* synthetic */ Stream stream() {
        return Collection.CC.$default$stream(this);
    }

    @Override // java.util.Collection
    public /* synthetic */ java.util.stream.Stream stream() {
        return Stream.Wrapper.convert(stream());
    }

    @Override // java.util.Collection, j$.util.Collection
    public /* synthetic */ Object[] toArray(IntFunction intFunction) {
        return toArray((T[]) intFunction.apply(0));
    }

    public Nodes() {
    }

    public Nodes(int i) {
        super(i);
    }

    public Nodes(java.util.Collection<T> collection) {
        super(collection);
    }

    public Nodes(java.util.List<T> list) {
        super(list);
    }

    @SafeVarargs
    public Nodes(T... tArr) {
        super(Arrays.asList(tArr));
    }

    @Override // java.util.ArrayList
    public Nodes<T> clone() {
        Nodes<T> nodes = new Nodes<>(size());
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            nodes.add(it.next().mo10839clone());
        }
        return nodes;
    }

    public ArrayList<T> asList() {
        return new ArrayList<>(this);
    }

    public Nodes<T> remove() {
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            it.next().remove();
        }
        return this;
    }

    public String outerHtml() {
        return (String) Collection.EL.stream(this).map(new Function() { // from class: org.jsoup.select.Nodes$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Node) obj).outerHtml();
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).collect(StringUtil.joining("\n"));
    }

    @Override // java.util.AbstractCollection
    public String toString() {
        return outerHtml();
    }

    public Nodes<T> before(String str) {
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            it.next().before(str);
        }
        return this;
    }

    public Nodes<T> after(String str) {
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            it.next().after(str);
        }
        return this;
    }

    public Nodes<T> wrap(String str) {
        Validate.notEmpty(str);
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            it.next().wrap(str);
        }
        return this;
    }

    public T first() {
        if (isEmpty()) {
            return null;
        }
        return get(0);
    }

    public T last() {
        if (isEmpty()) {
            return null;
        }
        return get(size() - 1);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public T set(int i, T t) {
        Validate.notNull(t);
        T t2 = (T) super.set(i, t);
        t2.replaceWith(t);
        return t2;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public T remove(int i) {
        T t = (T) super.remove(i);
        t.remove();
        return t;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean remove(Object obj) {
        int iIndexOf = super.indexOf(obj);
        if (iIndexOf == -1) {
            return false;
        }
        remove(iIndexOf);
        return true;
    }

    public T deselect(int i) {
        return (T) super.remove(i);
    }

    public boolean deselect(Object obj) {
        return super.remove(obj);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        remove();
        super.clear();
    }

    public void deselectAll() {
        super.clear();
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean removeAll(java.util.Collection<?> collection) {
        Iterator<?> it = collection.iterator();
        boolean zRemove = false;
        while (it.hasNext()) {
            zRemove |= remove(it.next());
        }
        return zRemove;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean retainAll(java.util.Collection<?> collection) {
        Iterator<T> it = iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (!collection.contains(it.next())) {
                it.remove();
                z = true;
            }
        }
        return z;
    }

    @Override // java.util.ArrayList, java.util.Collection, j$.util.Collection
    public boolean removeIf(Predicate<? super T> predicate) {
        Iterator<T> it = iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (predicate.test(it.next())) {
                it.remove();
                z = true;
            }
        }
        return z;
    }

    @Override // java.util.ArrayList, java.util.List, j$.util.List
    public void replaceAll(UnaryOperator<T> unaryOperator) {
        for (int i = 0; i < size(); i++) {
            set(i, (Node) unaryOperator.apply(get(i)));
        }
    }
}
