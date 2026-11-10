package com.google.common.base;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.DoNotMock;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
@DoNotMock("Use Optional.of(value) or Optional.absent()")
@GwtCompatible(serializable = true)
public abstract class Optional<T> implements Serializable {
    public static final long serialVersionUID = 0;

    public static <T> Optional<T> absent() {
        return Absent.INSTANCE;
    }

    public static <T> Optional<T> fromNullable(T t) {
        return t == null ? Absent.INSTANCE : new Present(t);
    }

    public static <T> Optional<T> of(T t) {
        if (t != null) {
            return new Present(t);
        }
        throw new NullPointerException();
    }

    @Beta
    public static <T> Iterable<T> presentInstances(final Iterable<? extends Optional<? extends T>> iterable) {
        if (iterable != null) {
            return new Iterable<T>() { // from class: com.google.common.base.Optional.1
                @Override // java.lang.Iterable
                public Iterator<T> iterator() {
                    return new AbstractIterator<T>() { // from class: com.google.common.base.Optional.1.1
                        public final Iterator<? extends Optional<? extends T>> iterator;

                        {
                            Iterator<? extends Optional<? extends T>> it = iterable.iterator();
                            Preconditions.checkNotNull(it);
                            this.iterator = it;
                        }

                        @Override // com.google.common.base.AbstractIterator
                        public T computeNext() {
                            while (this.iterator.hasNext()) {
                                Optional<? extends T> next = this.iterator.next();
                                if (next.isPresent()) {
                                    return next.get();
                                }
                            }
                            return endOfData();
                        }
                    };
                }
            };
        }
        throw new NullPointerException();
    }

    public abstract Set<T> asSet();

    public abstract boolean equals(Object obj);

    public abstract T get();

    public abstract int hashCode();

    public abstract boolean isPresent();

    public abstract Optional<T> or(Optional<? extends T> optional);

    @Beta
    public abstract T or(Supplier<? extends T> supplier);

    public abstract T or(T t);

    public abstract T orNull();

    public abstract String toString();

    public abstract <V> Optional<V> transform(Function<? super T, V> function);
}
