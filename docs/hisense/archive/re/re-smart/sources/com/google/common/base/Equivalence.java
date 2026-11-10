package com.google.common.base;

import b.a.a.a.a;
import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.ForOverride;
import java.io.Serializable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible
public abstract class Equivalence<T> {

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final class Equals extends Equivalence<Object> implements Serializable {
        public static final Equals INSTANCE = new Equals();
        public static final long serialVersionUID = 1;

        private Object readResolve() {
            return INSTANCE;
        }

        @Override // com.google.common.base.Equivalence
        public boolean doEquivalent(Object obj, Object obj2) {
            return obj.equals(obj2);
        }

        @Override // com.google.common.base.Equivalence
        public int doHash(Object obj) {
            return obj.hashCode();
        }
    }

    private static final class EquivalentToPredicate<T> implements Predicate<T>, Serializable {
        public static final long serialVersionUID = 0;
        public final Equivalence<T> equivalence;
        public final T target;

        public EquivalentToPredicate(Equivalence<T> equivalence, T t) {
            if (equivalence == null) {
                throw new NullPointerException();
            }
            this.equivalence = equivalence;
            this.target = t;
        }

        @Override // com.google.common.base.Predicate
        public boolean apply(T t) {
            return this.equivalence.equivalent(t, this.target);
        }

        @Override // com.google.common.base.Predicate
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof EquivalentToPredicate)) {
                return false;
            }
            EquivalentToPredicate equivalentToPredicate = (EquivalentToPredicate) obj;
            return this.equivalence.equals(equivalentToPredicate.equivalence) && Objects.equal(this.target, equivalentToPredicate.target);
        }

        public int hashCode() {
            return Arrays.hashCode(new Object[]{this.equivalence, this.target});
        }

        public String toString() {
            String strValueOf = String.valueOf(this.equivalence);
            String strValueOf2 = String.valueOf(this.target);
            return a.b(strValueOf2.length() + strValueOf.length() + 15, strValueOf, ".equivalentTo(", strValueOf2, ")");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final class Identity extends Equivalence<Object> implements Serializable {
        public static final Identity INSTANCE = new Identity();
        public static final long serialVersionUID = 1;

        private Object readResolve() {
            return INSTANCE;
        }

        @Override // com.google.common.base.Equivalence
        public boolean doEquivalent(Object obj, Object obj2) {
            return false;
        }

        @Override // com.google.common.base.Equivalence
        public int doHash(Object obj) {
            return System.identityHashCode(obj);
        }
    }

    public static final class Wrapper<T> implements Serializable {
        public static final long serialVersionUID = 0;
        public final Equivalence<? super T> equivalence;
        public final T reference;

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Wrapper)) {
                return false;
            }
            Wrapper wrapper = (Wrapper) obj;
            if (this.equivalence.equals(wrapper.equivalence)) {
                return this.equivalence.equivalent(this.reference, wrapper.reference);
            }
            return false;
        }

        public T get() {
            return this.reference;
        }

        public int hashCode() {
            return this.equivalence.hash(this.reference);
        }

        public String toString() {
            String strValueOf = String.valueOf(this.equivalence);
            String strValueOf2 = String.valueOf(this.reference);
            return a.b(strValueOf2.length() + strValueOf.length() + 7, strValueOf, ".wrap(", strValueOf2, ")");
        }

        public Wrapper(Equivalence<? super T> equivalence, T t) {
            if (equivalence == null) {
                throw new NullPointerException();
            }
            this.equivalence = equivalence;
            this.reference = t;
        }
    }

    public static Equivalence<Object> equals() {
        return Equals.INSTANCE;
    }

    public static Equivalence<Object> identity() {
        return Identity.INSTANCE;
    }

    @ForOverride
    public abstract boolean doEquivalent(T t, T t2);

    @ForOverride
    public abstract int doHash(T t);

    public final boolean equivalent(T t, T t2) {
        if (t == t2) {
            return true;
        }
        if (t == null || t2 == null) {
            return false;
        }
        return doEquivalent(t, t2);
    }

    public final Predicate<T> equivalentTo(T t) {
        return new EquivalentToPredicate(this, t);
    }

    public final int hash(T t) {
        if (t == null) {
            return 0;
        }
        return doHash(t);
    }

    public final <F> Equivalence<F> onResultOf(Function<F, ? extends T> function) {
        return new FunctionalEquivalence(function, this);
    }

    @GwtCompatible(serializable = true)
    public final <S extends T> Equivalence<Iterable<S>> pairwise() {
        return new PairwiseEquivalence(this);
    }

    public final <S extends T> Wrapper<S> wrap(S s) {
        return new Wrapper<>(this, s);
    }
}
