package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Equivalence;
import com.google.common.base.Function;

/* JADX INFO: loaded from: classes.dex */
@Beta
@GwtIncompatible
public final class Interners {

    public static class InternerBuilder {
        public final MapMaker mapMaker;
        public boolean strong;

        public <E> Interner<E> build() {
            if (!this.strong) {
                this.mapMaker.weakKeys();
            }
            return new InternerImpl(this.mapMaker);
        }

        public InternerBuilder concurrencyLevel(int i) {
            this.mapMaker.concurrencyLevel(i);
            return this;
        }

        public InternerBuilder strong() {
            this.strong = true;
            return this;
        }

        @GwtIncompatible("java.lang.ref.WeakReference")
        public InternerBuilder weak() {
            this.strong = false;
            return this;
        }

        public InternerBuilder() {
            this.mapMaker = new MapMaker();
            this.strong = true;
        }
    }

    private static class InternerFunction<E> implements Function<E, E> {
        public final Interner<E> interner;

        public InternerFunction(Interner<E> interner) {
            this.interner = interner;
        }

        @Override // com.google.common.base.Function
        public E apply(E e2) {
            return this.interner.intern(e2);
        }

        @Override // com.google.common.base.Function
        public boolean equals(Object obj) {
            if (obj instanceof InternerFunction) {
                return this.interner.equals(((InternerFunction) obj).interner);
            }
            return false;
        }

        public int hashCode() {
            return this.interner.hashCode();
        }
    }

    @VisibleForTesting
    static final class InternerImpl<E> implements Interner<E> {

        @VisibleForTesting
        public final MapMakerInternalMap<E, MapMaker.Dummy, ?, ?> map;

        @Override // com.google.common.collect.Interner
        public E intern(E e2) {
            E e3;
            do {
                MapMakerInternalMap.InternalEntry entry = this.map.getEntry(e2);
                if (entry != null && (e3 = (E) entry.getKey()) != null) {
                    return e3;
                }
            } while (this.map.putIfAbsent(e2, MapMaker.Dummy.VALUE) != null);
            return e2;
        }

        public InternerImpl(MapMaker mapMaker) {
            this.map = MapMakerInternalMap.createWithDummyValues(mapMaker.keyEquivalence(Equivalence.Equals.INSTANCE));
        }
    }

    public static <E> Function<E, E> asFunction(Interner<E> interner) {
        if (interner != null) {
            return new InternerFunction(interner);
        }
        throw new NullPointerException();
    }

    public static InternerBuilder newBuilder() {
        return new InternerBuilder();
    }

    public static <E> Interner<E> newStrongInterner() {
        return new InternerBuilder().strong().build();
    }

    @GwtIncompatible("java.lang.ref.WeakReference")
    public static <E> Interner<E> newWeakInterner() {
        return new InternerBuilder().weak().build();
    }
}
