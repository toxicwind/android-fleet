package com.google.common.base;

import b.a.a.a.a;
import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible
public final class Functions {

    /* JADX INFO: Access modifiers changed from: private */
    public static class ConstantFunction<E> implements Function<Object, E>, Serializable {
        public static final long serialVersionUID = 0;
        public final E value;

        public ConstantFunction(E e2) {
            this.value = e2;
        }

        @Override // com.google.common.base.Function
        public E apply(Object obj) {
            return this.value;
        }

        @Override // com.google.common.base.Function
        public boolean equals(Object obj) {
            if (obj instanceof ConstantFunction) {
                return Objects.equal(this.value, ((ConstantFunction) obj).value);
            }
            return false;
        }

        public int hashCode() {
            E e2 = this.value;
            if (e2 == null) {
                return 0;
            }
            return e2.hashCode();
        }

        public String toString() {
            String strValueOf = String.valueOf(this.value);
            return a.a(strValueOf.length() + 20, "Functions.constant(", strValueOf, ")");
        }
    }

    private static class ForMapWithDefault<K, V> implements Function<K, V>, Serializable {
        public static final long serialVersionUID = 0;
        public final V defaultValue;
        public final Map<K, ? extends V> map;

        public ForMapWithDefault(Map<K, ? extends V> map, V v) {
            if (map == null) {
                throw new NullPointerException();
            }
            this.map = map;
            this.defaultValue = v;
        }

        @Override // com.google.common.base.Function
        public V apply(K k) {
            V v = this.map.get(k);
            return (v != null || this.map.containsKey(k)) ? v : this.defaultValue;
        }

        @Override // com.google.common.base.Function
        public boolean equals(Object obj) {
            if (!(obj instanceof ForMapWithDefault)) {
                return false;
            }
            ForMapWithDefault forMapWithDefault = (ForMapWithDefault) obj;
            return this.map.equals(forMapWithDefault.map) && Objects.equal(this.defaultValue, forMapWithDefault.defaultValue);
        }

        public int hashCode() {
            return Arrays.hashCode(new Object[]{this.map, this.defaultValue});
        }

        public String toString() {
            String strValueOf = String.valueOf(this.map);
            String strValueOf2 = String.valueOf(this.defaultValue);
            StringBuilder sbA = a.a(strValueOf2.length() + strValueOf.length() + 33, "Functions.forMap(", strValueOf, ", defaultValue=", strValueOf2);
            sbA.append(")");
            return sbA.toString();
        }
    }

    private static class FunctionComposition<A, B, C> implements Function<A, C>, Serializable {
        public static final long serialVersionUID = 0;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final Function<A, ? extends B> f1950f;
        public final Function<B, C> g;

        public FunctionComposition(Function<B, C> function, Function<A, ? extends B> function2) {
            if (function == null) {
                throw new NullPointerException();
            }
            this.g = function;
            if (function2 == null) {
                throw new NullPointerException();
            }
            this.f1950f = function2;
        }

        @Override // com.google.common.base.Function
        public C apply(A a2) {
            return (C) this.g.apply(this.f1950f.apply(a2));
        }

        @Override // com.google.common.base.Function
        public boolean equals(Object obj) {
            if (!(obj instanceof FunctionComposition)) {
                return false;
            }
            FunctionComposition functionComposition = (FunctionComposition) obj;
            return this.f1950f.equals(functionComposition.f1950f) && this.g.equals(functionComposition.g);
        }

        public int hashCode() {
            return this.f1950f.hashCode() ^ this.g.hashCode();
        }

        public String toString() {
            String strValueOf = String.valueOf(this.g);
            String strValueOf2 = String.valueOf(this.f1950f);
            return a.b(strValueOf2.length() + strValueOf.length() + 2, strValueOf, "(", strValueOf2, ")");
        }
    }

    private static class FunctionForMapNoDefault<K, V> implements Function<K, V>, Serializable {
        public static final long serialVersionUID = 0;
        public final Map<K, V> map;

        public FunctionForMapNoDefault(Map<K, V> map) {
            if (map == null) {
                throw new NullPointerException();
            }
            this.map = map;
        }

        @Override // com.google.common.base.Function
        public V apply(K k) {
            V v = this.map.get(k);
            Preconditions.checkArgument(v != null || this.map.containsKey(k), "Key '%s' not present in map", k);
            return v;
        }

        @Override // com.google.common.base.Function
        public boolean equals(Object obj) {
            if (obj instanceof FunctionForMapNoDefault) {
                return this.map.equals(((FunctionForMapNoDefault) obj).map);
            }
            return false;
        }

        public int hashCode() {
            return this.map.hashCode();
        }

        public String toString() {
            String strValueOf = String.valueOf(this.map);
            return a.a(strValueOf.length() + 18, "Functions.forMap(", strValueOf, ")");
        }
    }

    private enum IdentityFunction implements Function<Object, Object> {
        INSTANCE;

        @Override // com.google.common.base.Function
        public Object apply(Object obj) {
            return obj;
        }

        @Override // java.lang.Enum
        public String toString() {
            return "Functions.identity()";
        }
    }

    private static class PredicateFunction<T> implements Function<T, Boolean>, Serializable {
        public static final long serialVersionUID = 0;
        public final Predicate<T> predicate;

        public PredicateFunction(Predicate<T> predicate) {
            if (predicate == null) {
                throw new NullPointerException();
            }
            this.predicate = predicate;
        }

        @Override // com.google.common.base.Function
        public boolean equals(Object obj) {
            if (obj instanceof PredicateFunction) {
                return this.predicate.equals(((PredicateFunction) obj).predicate);
            }
            return false;
        }

        public int hashCode() {
            return this.predicate.hashCode();
        }

        public String toString() {
            String strValueOf = String.valueOf(this.predicate);
            return a.a(strValueOf.length() + 24, "Functions.forPredicate(", strValueOf, ")");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.common.base.Function
        public Boolean apply(T t) {
            return Boolean.valueOf(this.predicate.apply(t));
        }

        public /* synthetic */ PredicateFunction(Predicate predicate, AnonymousClass1 anonymousClass1) {
            if (predicate != null) {
                this.predicate = predicate;
                return;
            }
            throw new NullPointerException();
        }
    }

    private enum ToStringFunction implements Function<Object, String> {
        INSTANCE;

        @Override // java.lang.Enum
        public String toString() {
            return "Functions.toStringFunction()";
        }

        @Override // com.google.common.base.Function
        public String apply(Object obj) {
            if (obj != null) {
                return obj.toString();
            }
            throw new NullPointerException();
        }
    }

    public static <A, B, C> Function<A, C> compose(Function<B, C> function, Function<A, ? extends B> function2) {
        return new FunctionComposition(function, function2);
    }

    public static <E> Function<Object, E> constant(E e2) {
        return new ConstantFunction(e2);
    }

    public static <K, V> Function<K, V> forMap(Map<K, V> map) {
        return new FunctionForMapNoDefault(map);
    }

    public static <T> Function<T, Boolean> forPredicate(Predicate<T> predicate) {
        return new PredicateFunction(predicate, null);
    }

    public static <T> Function<Object, T> forSupplier(Supplier<T> supplier) {
        return new SupplierFunction(supplier, null);
    }

    public static <E> Function<E, E> identity() {
        return IdentityFunction.INSTANCE;
    }

    public static Function<Object, String> toStringFunction() {
        return ToStringFunction.INSTANCE;
    }

    public static <K, V> Function<K, V> forMap(Map<K, ? extends V> map, V v) {
        return new ForMapWithDefault(map, v);
    }

    private static class SupplierFunction<T> implements Function<Object, T>, Serializable {
        public static final long serialVersionUID = 0;
        public final Supplier<T> supplier;

        public SupplierFunction(Supplier<T> supplier) {
            if (supplier == null) {
                throw new NullPointerException();
            }
            this.supplier = supplier;
        }

        @Override // com.google.common.base.Function
        public T apply(Object obj) {
            return this.supplier.get();
        }

        @Override // com.google.common.base.Function
        public boolean equals(Object obj) {
            if (obj instanceof SupplierFunction) {
                return this.supplier.equals(((SupplierFunction) obj).supplier);
            }
            return false;
        }

        public int hashCode() {
            return this.supplier.hashCode();
        }

        public String toString() {
            String strValueOf = String.valueOf(this.supplier);
            return a.a(strValueOf.length() + 23, "Functions.forSupplier(", strValueOf, ")");
        }

        public /* synthetic */ SupplierFunction(Supplier supplier, AnonymousClass1 anonymousClass1) {
            if (supplier != null) {
                this.supplier = supplier;
                return;
            }
            throw new NullPointerException();
        }
    }
}
