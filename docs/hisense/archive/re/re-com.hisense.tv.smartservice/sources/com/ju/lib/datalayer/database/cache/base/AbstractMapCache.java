package com.ju.lib.datalayer.database.cache.base;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractMapCache<K, V> implements IDatabaseCache<K, V> {
    public ReentrantReadWriteLock mRrwl = new ReentrantReadWriteLock();

    @Override // com.ju.lib.datalayer.database.cache.base.IDatabaseCache
    public void dumpCache() {
        this.mRrwl.writeLock().lock();
        evictAll();
        this.mRrwl.writeLock().unlock();
    }

    public abstract void evictAll();

    @Override // com.ju.lib.datalayer.database.cache.base.IDatabaseCache
    public V get(K k) {
        this.mRrwl.readLock().lock();
        V value = getValue(k);
        this.mRrwl.readLock().unlock();
        return value;
    }

    public abstract V getValue(K k);

    @Override // com.ju.lib.datalayer.database.cache.base.IDatabaseCache
    public void put(K k, V v) {
        this.mRrwl.writeLock().lock();
        putValue(k, v);
        this.mRrwl.writeLock().unlock();
    }

    public abstract void putValue(K k, V v);
}
