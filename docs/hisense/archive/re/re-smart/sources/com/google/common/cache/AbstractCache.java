package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableMap;
import com.google.common.primitives.UnsignedLong;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible
public abstract class AbstractCache<K, V> implements Cache<K, V> {

    public static final class SimpleStatsCounter implements StatsCounter {
        public final LongAddable hitCount = LongAddables.create();
        public final LongAddable missCount = LongAddables.create();
        public final LongAddable loadSuccessCount = LongAddables.create();
        public final LongAddable loadExceptionCount = LongAddables.create();
        public final LongAddable totalLoadTime = LongAddables.create();
        public final LongAddable evictionCount = LongAddables.create();

        public static long negativeToMaxValue(long j) {
            return j >= 0 ? j : UnsignedLong.UNSIGNED_MASK;
        }

        public void incrementBy(StatsCounter statsCounter) {
            CacheStats cacheStatsSnapshot = statsCounter.snapshot();
            this.hitCount.add(cacheStatsSnapshot.hitCount());
            this.missCount.add(cacheStatsSnapshot.missCount());
            this.loadSuccessCount.add(cacheStatsSnapshot.loadSuccessCount());
            this.loadExceptionCount.add(cacheStatsSnapshot.loadExceptionCount());
            this.totalLoadTime.add(cacheStatsSnapshot.totalLoadTime());
            this.evictionCount.add(cacheStatsSnapshot.evictionCount());
        }

        @Override // com.google.common.cache.AbstractCache.StatsCounter
        public void recordEviction() {
            this.evictionCount.increment();
        }

        @Override // com.google.common.cache.AbstractCache.StatsCounter
        public void recordHits(int i) {
            this.hitCount.add(i);
        }

        @Override // com.google.common.cache.AbstractCache.StatsCounter
        public void recordLoadException(long j) {
            this.loadExceptionCount.increment();
            this.totalLoadTime.add(j);
        }

        @Override // com.google.common.cache.AbstractCache.StatsCounter
        public void recordLoadSuccess(long j) {
            this.loadSuccessCount.increment();
            this.totalLoadTime.add(j);
        }

        @Override // com.google.common.cache.AbstractCache.StatsCounter
        public void recordMisses(int i) {
            this.missCount.add(i);
        }

        @Override // com.google.common.cache.AbstractCache.StatsCounter
        public CacheStats snapshot() {
            return new CacheStats(negativeToMaxValue(this.hitCount.sum()), negativeToMaxValue(this.missCount.sum()), negativeToMaxValue(this.loadSuccessCount.sum()), negativeToMaxValue(this.loadExceptionCount.sum()), negativeToMaxValue(this.totalLoadTime.sum()), negativeToMaxValue(this.evictionCount.sum()));
        }
    }

    public interface StatsCounter {
        void recordEviction();

        void recordHits(int i);

        void recordLoadException(long j);

        void recordLoadSuccess(long j);

        void recordMisses(int i);

        CacheStats snapshot();
    }

    @Override // com.google.common.cache.Cache
    public ConcurrentMap<K, V> asMap() {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.cache.Cache
    public void cleanUp() {
    }

    @Override // com.google.common.cache.Cache
    public V get(K k, Callable<? extends V> callable) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.cache.Cache
    public ImmutableMap<K, V> getAllPresent(Iterable<?> iterable) {
        V ifPresent;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : iterable) {
            if (!linkedHashMap.containsKey(obj) && (ifPresent = getIfPresent(obj)) != null) {
                linkedHashMap.put(obj, ifPresent);
            }
        }
        return ImmutableMap.copyOf((Map) linkedHashMap);
    }

    @Override // com.google.common.cache.Cache
    public void invalidate(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.cache.Cache
    public void invalidateAll(Iterable<?> iterable) {
        Iterator<?> it = iterable.iterator();
        while (it.hasNext()) {
            invalidate(it.next());
        }
    }

    @Override // com.google.common.cache.Cache
    public void put(K k, V v) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.cache.Cache
    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // com.google.common.cache.Cache
    public long size() {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.cache.Cache
    public CacheStats stats() {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.cache.Cache
    public void invalidateAll() {
        throw new UnsupportedOperationException();
    }
}
