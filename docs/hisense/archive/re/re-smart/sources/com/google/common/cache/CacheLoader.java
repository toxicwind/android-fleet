package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Function;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible(emulated = true)
public abstract class CacheLoader<K, V> {

    private static final class FunctionToCacheLoader<K, V> extends CacheLoader<K, V> implements Serializable {
        public static final long serialVersionUID = 0;
        public final Function<K, V> computingFunction;

        public FunctionToCacheLoader(Function<K, V> function) {
            if (function == null) {
                throw new NullPointerException();
            }
            this.computingFunction = function;
        }

        @Override // com.google.common.cache.CacheLoader
        public V load(K k) {
            Function<K, V> function = this.computingFunction;
            if (k != null) {
                return function.apply(k);
            }
            throw new NullPointerException();
        }
    }

    public static final class InvalidCacheLoadException extends RuntimeException {
        public InvalidCacheLoadException(String str) {
            super(str);
        }
    }

    private static final class SupplierToCacheLoader<V> extends CacheLoader<Object, V> implements Serializable {
        public static final long serialVersionUID = 0;
        public final Supplier<V> computingSupplier;

        public SupplierToCacheLoader(Supplier<V> supplier) {
            if (supplier == null) {
                throw new NullPointerException();
            }
            this.computingSupplier = supplier;
        }

        @Override // com.google.common.cache.CacheLoader
        public V load(Object obj) {
            if (obj != null) {
                return this.computingSupplier.get();
            }
            throw new NullPointerException();
        }
    }

    public static final class UnsupportedLoadingOperationException extends UnsupportedOperationException {
    }

    @GwtIncompatible
    public static <K, V> CacheLoader<K, V> asyncReloading(CacheLoader<K, V> cacheLoader, final Executor executor) {
        if (cacheLoader == null) {
            throw new NullPointerException();
        }
        if (executor != null) {
            return new CacheLoader<K, V>() { // from class: com.google.common.cache.CacheLoader.1
                @Override // com.google.common.cache.CacheLoader
                public V load(K k) {
                    return (V) CacheLoader.this.load(k);
                }

                @Override // com.google.common.cache.CacheLoader
                public Map<K, V> loadAll(Iterable<? extends K> iterable) {
                    return CacheLoader.this.loadAll(iterable);
                }

                @Override // com.google.common.cache.CacheLoader
                public ListenableFuture<V> reload(final K k, final V v) {
                    ListenableFutureTask listenableFutureTask = new ListenableFutureTask(new Callable<V>() { // from class: com.google.common.cache.CacheLoader.1.1
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // java.util.concurrent.Callable
                        public V call() {
                            return CacheLoader.this.reload(k, v).get();
                        }
                    });
                    executor.execute(listenableFutureTask);
                    return listenableFutureTask;
                }
            };
        }
        throw new NullPointerException();
    }

    public static <K, V> CacheLoader<K, V> from(Function<K, V> function) {
        return new FunctionToCacheLoader(function);
    }

    public abstract V load(K k);

    public Map<K, V> loadAll(Iterable<? extends K> iterable) {
        throw new UnsupportedLoadingOperationException();
    }

    @GwtIncompatible
    public ListenableFuture<V> reload(K k, V v) {
        if (k == null) {
            throw new NullPointerException();
        }
        if (v != null) {
            return Futures.immediateFuture(load(k));
        }
        throw new NullPointerException();
    }

    public static <V> CacheLoader<Object, V> from(Supplier<V> supplier) {
        return new SupplierToCacheLoader(supplier);
    }
}
