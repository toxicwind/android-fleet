package com.google.common.base;

import b.a.a.a.a;
import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.ForOverride;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.RetainedWith;
import java.io.Serializable;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible
public abstract class Converter<A, B> implements Function<A, B> {
    public final boolean handleNullAutomatically;

    @RetainedWith
    @LazyInit
    public transient Converter<B, A> reverse;

    private static final class ConverterComposition<A, B, C> extends Converter<A, C> implements Serializable {
        public static final long serialVersionUID = 0;
        public final Converter<A, B> first;
        public final Converter<B, C> second;

        public ConverterComposition(Converter<A, B> converter, Converter<B, C> converter2) {
            this.first = converter;
            this.second = converter2;
        }

        @Override // com.google.common.base.Converter
        public A correctedDoBackward(C c2) {
            return (A) this.first.correctedDoBackward(this.second.correctedDoBackward(c2));
        }

        @Override // com.google.common.base.Converter
        public C correctedDoForward(A a2) {
            return (C) this.second.correctedDoForward(this.first.correctedDoForward(a2));
        }

        @Override // com.google.common.base.Converter
        public A doBackward(C c2) {
            throw new AssertionError();
        }

        @Override // com.google.common.base.Converter
        public C doForward(A a2) {
            throw new AssertionError();
        }

        @Override // com.google.common.base.Converter, com.google.common.base.Function
        public boolean equals(Object obj) {
            if (!(obj instanceof ConverterComposition)) {
                return false;
            }
            ConverterComposition converterComposition = (ConverterComposition) obj;
            return this.first.equals(converterComposition.first) && this.second.equals(converterComposition.second);
        }

        public int hashCode() {
            return this.second.hashCode() + (this.first.hashCode() * 31);
        }

        public String toString() {
            String strValueOf = String.valueOf(this.first);
            String strValueOf2 = String.valueOf(this.second);
            return a.b(strValueOf2.length() + strValueOf.length() + 10, strValueOf, ".andThen(", strValueOf2, ")");
        }
    }

    private static final class FunctionBasedConverter<A, B> extends Converter<A, B> implements Serializable {
        public final Function<? super B, ? extends A> backwardFunction;
        public final Function<? super A, ? extends B> forwardFunction;

        @Override // com.google.common.base.Converter
        public A doBackward(B b2) {
            return this.backwardFunction.apply(b2);
        }

        @Override // com.google.common.base.Converter
        public B doForward(A a2) {
            return this.forwardFunction.apply(a2);
        }

        @Override // com.google.common.base.Converter, com.google.common.base.Function
        public boolean equals(Object obj) {
            if (!(obj instanceof FunctionBasedConverter)) {
                return false;
            }
            FunctionBasedConverter functionBasedConverter = (FunctionBasedConverter) obj;
            return this.forwardFunction.equals(functionBasedConverter.forwardFunction) && this.backwardFunction.equals(functionBasedConverter.backwardFunction);
        }

        public int hashCode() {
            return this.backwardFunction.hashCode() + (this.forwardFunction.hashCode() * 31);
        }

        public String toString() {
            String strValueOf = String.valueOf(this.forwardFunction);
            String strValueOf2 = String.valueOf(this.backwardFunction);
            StringBuilder sbA = a.a(strValueOf2.length() + strValueOf.length() + 18, "Converter.from(", strValueOf, ", ", strValueOf2);
            sbA.append(")");
            return sbA.toString();
        }

        public FunctionBasedConverter(Function<? super A, ? extends B> function, Function<? super B, ? extends A> function2) {
            if (function == null) {
                throw new NullPointerException();
            }
            this.forwardFunction = function;
            if (function2 == null) {
                throw new NullPointerException();
            }
            this.backwardFunction = function2;
        }
    }

    private static final class IdentityConverter<T> extends Converter<T, T> implements Serializable {
        public static final IdentityConverter<?> INSTANCE = new IdentityConverter<>();
        public static final long serialVersionUID = 0;

        private Object readResolve() {
            return INSTANCE;
        }

        @Override // com.google.common.base.Converter
        public <S> Converter<T, S> doAndThen(Converter<T, S> converter) {
            Preconditions.checkNotNull(converter, "otherConverter");
            return converter;
        }

        @Override // com.google.common.base.Converter
        public T doBackward(T t) {
            return t;
        }

        @Override // com.google.common.base.Converter
        public T doForward(T t) {
            return t;
        }

        @Override // com.google.common.base.Converter
        public IdentityConverter<T> reverse() {
            return this;
        }

        public String toString() {
            return "Converter.identity()";
        }
    }

    private static final class ReverseConverter<A, B> extends Converter<B, A> implements Serializable {
        public static final long serialVersionUID = 0;
        public final Converter<A, B> original;

        public ReverseConverter(Converter<A, B> converter) {
            this.original = converter;
        }

        @Override // com.google.common.base.Converter
        public B correctedDoBackward(A a2) {
            return this.original.correctedDoForward(a2);
        }

        @Override // com.google.common.base.Converter
        public A correctedDoForward(B b2) {
            return this.original.correctedDoBackward(b2);
        }

        @Override // com.google.common.base.Converter
        public B doBackward(A a2) {
            throw new AssertionError();
        }

        @Override // com.google.common.base.Converter
        public A doForward(B b2) {
            throw new AssertionError();
        }

        @Override // com.google.common.base.Converter, com.google.common.base.Function
        public boolean equals(Object obj) {
            if (obj instanceof ReverseConverter) {
                return this.original.equals(((ReverseConverter) obj).original);
            }
            return false;
        }

        public int hashCode() {
            return ~this.original.hashCode();
        }

        @Override // com.google.common.base.Converter
        public Converter<A, B> reverse() {
            return this.original;
        }

        public String toString() {
            String strValueOf = String.valueOf(this.original);
            return a.a(strValueOf.length() + 10, strValueOf, ".reverse()");
        }
    }

    public Converter() {
        this.handleNullAutomatically = true;
    }

    public static <A, B> Converter<A, B> from(Function<? super A, ? extends B> function, Function<? super B, ? extends A> function2) {
        return new FunctionBasedConverter(function, function2);
    }

    public static <T> Converter<T, T> identity() {
        return IdentityConverter.INSTANCE;
    }

    public final <C> Converter<A, C> andThen(Converter<B, C> converter) {
        return doAndThen(converter);
    }

    @Override // com.google.common.base.Function
    @CanIgnoreReturnValue
    @Deprecated
    public final B apply(A a2) {
        return convert(a2);
    }

    @CanIgnoreReturnValue
    public final B convert(A a2) {
        return correctedDoForward(a2);
    }

    @CanIgnoreReturnValue
    public Iterable<B> convertAll(final Iterable<? extends A> iterable) {
        Preconditions.checkNotNull(iterable, "fromIterable");
        return new Iterable<B>() { // from class: com.google.common.base.Converter.1
            @Override // java.lang.Iterable
            public Iterator<B> iterator() {
                return new Iterator<B>() { // from class: com.google.common.base.Converter.1.1
                    public final Iterator<? extends A> fromIterator;

                    {
                        this.fromIterator = iterable.iterator();
                    }

                    @Override // java.util.Iterator
                    public boolean hasNext() {
                        return this.fromIterator.hasNext();
                    }

                    @Override // java.util.Iterator
                    public B next() {
                        return (B) Converter.this.convert(this.fromIterator.next());
                    }

                    @Override // java.util.Iterator
                    public void remove() {
                        this.fromIterator.remove();
                    }
                };
            }
        };
    }

    public A correctedDoBackward(B b2) {
        if (!this.handleNullAutomatically) {
            return doBackward(b2);
        }
        if (b2 == null) {
            return null;
        }
        A aDoBackward = doBackward(b2);
        Preconditions.checkNotNull(aDoBackward);
        return aDoBackward;
    }

    public B correctedDoForward(A a2) {
        if (!this.handleNullAutomatically) {
            return doForward(a2);
        }
        if (a2 == null) {
            return null;
        }
        B bDoForward = doForward(a2);
        Preconditions.checkNotNull(bDoForward);
        return bDoForward;
    }

    public <C> Converter<A, C> doAndThen(Converter<B, C> converter) {
        if (converter != null) {
            return new ConverterComposition(this, converter);
        }
        throw new NullPointerException();
    }

    @ForOverride
    public abstract A doBackward(B b2);

    @ForOverride
    public abstract B doForward(A a2);

    @Override // com.google.common.base.Function
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @CanIgnoreReturnValue
    public Converter<B, A> reverse() {
        Converter<B, A> converter = this.reverse;
        if (converter != null) {
            return converter;
        }
        ReverseConverter reverseConverter = new ReverseConverter(this);
        this.reverse = reverseConverter;
        return reverseConverter;
    }

    public Converter(boolean z) {
        this.handleNullAutomatically = z;
    }
}
