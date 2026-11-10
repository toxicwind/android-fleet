package com.google.common.base;

import b.a.a.a.a;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import java.io.Serializable;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible
public final class Suppliers {

    @VisibleForTesting
    static class ExpiringMemoizingSupplier<T> implements Supplier<T>, Serializable {
        public static final long serialVersionUID = 0;
        public final Supplier<T> delegate;
        public final long durationNanos;
        public volatile transient long expirationNanos;
        public volatile transient T value;

        public ExpiringMemoizingSupplier(Supplier<T> supplier, long j, TimeUnit timeUnit) {
            if (supplier == null) {
                throw new NullPointerException();
            }
            this.delegate = supplier;
            this.durationNanos = timeUnit.toNanos(j);
            Preconditions.checkArgument(j > 0, "duration (%s %s) must be > 0", j, timeUnit);
        }

        @Override // com.google.common.base.Supplier
        public T get() {
            long j = this.expirationNanos;
            long jSystemNanoTime = Platform.systemNanoTime();
            if (j == 0 || jSystemNanoTime - j >= 0) {
                synchronized (this) {
                    if (j == this.expirationNanos) {
                        T t = this.delegate.get();
                        this.value = t;
                        long j2 = jSystemNanoTime + this.durationNanos;
                        if (j2 == 0) {
                            j2 = 1;
                        }
                        this.expirationNanos = j2;
                        return t;
                    }
                }
            }
            return this.value;
        }

        public String toString() {
            String strValueOf = String.valueOf(this.delegate);
            long j = this.durationNanos;
            StringBuilder sb = new StringBuilder(strValueOf.length() + 62);
            sb.append("Suppliers.memoizeWithExpiration(");
            sb.append(strValueOf);
            sb.append(", ");
            sb.append(j);
            sb.append(", NANOS)");
            return sb.toString();
        }
    }

    @VisibleForTesting
    static class MemoizingSupplier<T> implements Supplier<T>, Serializable {
        public static final long serialVersionUID = 0;
        public final Supplier<T> delegate;
        public volatile transient boolean initialized;
        public transient T value;

        public MemoizingSupplier(Supplier<T> supplier) {
            if (supplier == null) {
                throw new NullPointerException();
            }
            this.delegate = supplier;
        }

        @Override // com.google.common.base.Supplier
        public T get() {
            if (!this.initialized) {
                synchronized (this) {
                    if (!this.initialized) {
                        T t = this.delegate.get();
                        this.value = t;
                        this.initialized = true;
                        return t;
                    }
                }
            }
            return this.value;
        }

        public String toString() {
            Object objA;
            if (this.initialized) {
                String strValueOf = String.valueOf(this.value);
                objA = a.a(strValueOf.length() + 25, "<supplier that returned ", strValueOf, ">");
            } else {
                objA = this.delegate;
            }
            String strValueOf2 = String.valueOf(objA);
            return a.a(strValueOf2.length() + 19, "Suppliers.memoize(", strValueOf2, ")");
        }
    }

    @VisibleForTesting
    static class NonSerializableMemoizingSupplier<T> implements Supplier<T> {
        public volatile Supplier<T> delegate;
        public volatile boolean initialized;
        public T value;

        public NonSerializableMemoizingSupplier(Supplier<T> supplier) {
            if (supplier == null) {
                throw new NullPointerException();
            }
            this.delegate = supplier;
        }

        @Override // com.google.common.base.Supplier
        public T get() {
            if (!this.initialized) {
                synchronized (this) {
                    if (!this.initialized) {
                        T t = this.delegate.get();
                        this.value = t;
                        this.initialized = true;
                        this.delegate = null;
                        return t;
                    }
                }
            }
            return this.value;
        }

        public String toString() {
            Object objA = this.delegate;
            if (objA == null) {
                String strValueOf = String.valueOf(this.value);
                objA = a.a(strValueOf.length() + 25, "<supplier that returned ", strValueOf, ">");
            }
            String strValueOf2 = String.valueOf(objA);
            return a.a(strValueOf2.length() + 19, "Suppliers.memoize(", strValueOf2, ")");
        }
    }

    private static class SupplierComposition<F, T> implements Supplier<T>, Serializable {
        public static final long serialVersionUID = 0;
        public final Function<? super F, T> function;
        public final Supplier<F> supplier;

        public SupplierComposition(Function<? super F, T> function, Supplier<F> supplier) {
            if (function == null) {
                throw new NullPointerException();
            }
            this.function = function;
            if (supplier == null) {
                throw new NullPointerException();
            }
            this.supplier = supplier;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof SupplierComposition)) {
                return false;
            }
            SupplierComposition supplierComposition = (SupplierComposition) obj;
            return this.function.equals(supplierComposition.function) && this.supplier.equals(supplierComposition.supplier);
        }

        @Override // com.google.common.base.Supplier
        public T get() {
            return this.function.apply(this.supplier.get());
        }

        public int hashCode() {
            return Arrays.hashCode(new Object[]{this.function, this.supplier});
        }

        public String toString() {
            String strValueOf = String.valueOf(this.function);
            String strValueOf2 = String.valueOf(this.supplier);
            StringBuilder sbA = a.a(strValueOf2.length() + strValueOf.length() + 21, "Suppliers.compose(", strValueOf, ", ", strValueOf2);
            sbA.append(")");
            return sbA.toString();
        }
    }

    private interface SupplierFunction<T> extends Function<Supplier<T>, T> {
    }

    private enum SupplierFunctionImpl implements SupplierFunction<Object> {
        INSTANCE;

        @Override // java.lang.Enum
        public String toString() {
            return "Suppliers.supplierFunction()";
        }

        @Override // com.google.common.base.Function
        public Object apply(Supplier<Object> supplier) {
            return supplier.get();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static class SupplierOfInstance<T> implements Supplier<T>, Serializable {
        public static final long serialVersionUID = 0;
        public final T instance;

        public SupplierOfInstance(T t) {
            this.instance = t;
        }

        public boolean equals(Object obj) {
            if (obj instanceof SupplierOfInstance) {
                return Objects.equal(this.instance, ((SupplierOfInstance) obj).instance);
            }
            return false;
        }

        @Override // com.google.common.base.Supplier
        public T get() {
            return this.instance;
        }

        public int hashCode() {
            return Arrays.hashCode(new Object[]{this.instance});
        }

        public String toString() {
            String strValueOf = String.valueOf(this.instance);
            return a.a(strValueOf.length() + 22, "Suppliers.ofInstance(", strValueOf, ")");
        }
    }

    private static class ThreadSafeSupplier<T> implements Supplier<T>, Serializable {
        public static final long serialVersionUID = 0;
        public final Supplier<T> delegate;

        public ThreadSafeSupplier(Supplier<T> supplier) {
            if (supplier == null) {
                throw new NullPointerException();
            }
            this.delegate = supplier;
        }

        @Override // com.google.common.base.Supplier
        public T get() {
            T t;
            synchronized (this.delegate) {
                t = this.delegate.get();
            }
            return t;
        }

        public String toString() {
            String strValueOf = String.valueOf(this.delegate);
            return a.a(strValueOf.length() + 32, "Suppliers.synchronizedSupplier(", strValueOf, ")");
        }
    }

    public static <F, T> Supplier<T> compose(Function<? super F, T> function, Supplier<F> supplier) {
        return new SupplierComposition(function, supplier);
    }

    public static <T> Supplier<T> memoize(Supplier<T> supplier) {
        if ((supplier instanceof NonSerializableMemoizingSupplier) || (supplier instanceof MemoizingSupplier)) {
            return supplier;
        }
        return supplier instanceof Serializable ? new MemoizingSupplier(supplier) : new NonSerializableMemoizingSupplier(supplier);
    }

    public static <T> Supplier<T> memoizeWithExpiration(Supplier<T> supplier, long j, TimeUnit timeUnit) {
        return new ExpiringMemoizingSupplier(supplier, j, timeUnit);
    }

    public static <T> Supplier<T> ofInstance(T t) {
        return new SupplierOfInstance(t);
    }

    public static <T> Function<Supplier<T>, T> supplierFunction() {
        return SupplierFunctionImpl.INSTANCE;
    }

    public static <T> Supplier<T> synchronizedSupplier(Supplier<T> supplier) {
        return new ThreadSafeSupplier(supplier);
    }
}
