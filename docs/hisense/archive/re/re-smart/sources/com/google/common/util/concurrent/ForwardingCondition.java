package com.google.common.util.concurrent;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

/* JADX INFO: loaded from: classes.dex */
public abstract class ForwardingCondition implements Condition {
    @Override // java.util.concurrent.locks.Condition
    public void await() throws InterruptedException {
        delegate().await();
    }

    @Override // java.util.concurrent.locks.Condition
    public long awaitNanos(long j) {
        return delegate().awaitNanos(j);
    }

    @Override // java.util.concurrent.locks.Condition
    public void awaitUninterruptibly() {
        delegate().awaitUninterruptibly();
    }

    @Override // java.util.concurrent.locks.Condition
    public boolean awaitUntil(Date date) {
        return delegate().awaitUntil(date);
    }

    public abstract Condition delegate();

    @Override // java.util.concurrent.locks.Condition
    public void signal() {
        delegate().signal();
    }

    @Override // java.util.concurrent.locks.Condition
    public void signalAll() {
        delegate().signalAll();
    }

    @Override // java.util.concurrent.locks.Condition
    public boolean await(long j, TimeUnit timeUnit) {
        return delegate().await(j, timeUnit);
    }
}
