package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Sets;
import com.google.j2objc.annotations.ReflectionSupport;
import java.util.Set;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
@ReflectionSupport(ReflectionSupport.Level.FULL)
@GwtCompatible(emulated = true)
public abstract class AggregateFutureState<OutputT> extends AbstractFuture.TrustedFuture<OutputT> {
    public static final AtomicHelper ATOMIC_HELPER;
    public static final Logger log = Logger.getLogger(AggregateFutureState.class.getName());
    public volatile int remaining;
    public volatile Set<Throwable> seenExceptions = null;

    private static abstract class AtomicHelper {
        public AtomicHelper() {
        }

        public abstract void compareAndSetSeenExceptions(AggregateFutureState aggregateFutureState, Set<Throwable> set, Set<Throwable> set2);

        public abstract int decrementAndGetRemainingCount(AggregateFutureState aggregateFutureState);

        public /* synthetic */ AtomicHelper(AnonymousClass1 anonymousClass1) {
        }
    }

    private static final class SafeAtomicHelper extends AtomicHelper {
        public final AtomicIntegerFieldUpdater<AggregateFutureState> remainingCountUpdater;
        public final AtomicReferenceFieldUpdater<AggregateFutureState, Set<Throwable>> seenExceptionsUpdater;

        public SafeAtomicHelper(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicIntegerFieldUpdater atomicIntegerFieldUpdater) {
            super(null);
            this.seenExceptionsUpdater = atomicReferenceFieldUpdater;
            this.remainingCountUpdater = atomicIntegerFieldUpdater;
        }

        @Override // com.google.common.util.concurrent.AggregateFutureState.AtomicHelper
        public void compareAndSetSeenExceptions(AggregateFutureState aggregateFutureState, Set<Throwable> set, Set<Throwable> set2) {
            this.seenExceptionsUpdater.compareAndSet(aggregateFutureState, set, set2);
        }

        @Override // com.google.common.util.concurrent.AggregateFutureState.AtomicHelper
        public int decrementAndGetRemainingCount(AggregateFutureState aggregateFutureState) {
            return this.remainingCountUpdater.decrementAndGet(aggregateFutureState);
        }
    }

    private static final class SynchronizedAtomicHelper extends AtomicHelper {
        public SynchronizedAtomicHelper() {
            super(null);
        }

        @Override // com.google.common.util.concurrent.AggregateFutureState.AtomicHelper
        public void compareAndSetSeenExceptions(AggregateFutureState aggregateFutureState, Set<Throwable> set, Set<Throwable> set2) {
            synchronized (aggregateFutureState) {
                if (aggregateFutureState.seenExceptions == set) {
                    aggregateFutureState.seenExceptions = set2;
                }
            }
        }

        @Override // com.google.common.util.concurrent.AggregateFutureState.AtomicHelper
        public int decrementAndGetRemainingCount(AggregateFutureState aggregateFutureState) {
            int iAccess$306;
            synchronized (aggregateFutureState) {
                iAccess$306 = AggregateFutureState.access$306(aggregateFutureState);
            }
            return iAccess$306;
        }

        public /* synthetic */ SynchronizedAtomicHelper(AnonymousClass1 anonymousClass1) {
            super(null);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static {
        AtomicHelper safeAtomicHelper;
        Throwable th = null;
        Object[] objArr = 0;
        try {
            safeAtomicHelper = new SafeAtomicHelper(AtomicReferenceFieldUpdater.newUpdater(AggregateFutureState.class, Set.class, "seenExceptions"), AtomicIntegerFieldUpdater.newUpdater(AggregateFutureState.class, "remaining"));
        } catch (Throwable th2) {
            SynchronizedAtomicHelper synchronizedAtomicHelper = new SynchronizedAtomicHelper(objArr == true ? 1 : 0);
            th = th2;
            safeAtomicHelper = synchronizedAtomicHelper;
        }
        ATOMIC_HELPER = safeAtomicHelper;
        if (th != null) {
            log.log(Level.SEVERE, "SafeAtomicHelper is broken!", th);
        }
    }

    public AggregateFutureState(int i) {
        this.remaining = i;
    }

    public static /* synthetic */ int access$306(AggregateFutureState aggregateFutureState) {
        int i = aggregateFutureState.remaining - 1;
        aggregateFutureState.remaining = i;
        return i;
    }

    public abstract void addInitialException(Set<Throwable> set);

    public final void clearSeenExceptions() {
        this.seenExceptions = null;
    }

    public final int decrementRemainingAndGet() {
        return ATOMIC_HELPER.decrementAndGetRemainingCount(this);
    }

    public final Set<Throwable> getOrInitSeenExceptions() {
        Set<Throwable> set = this.seenExceptions;
        if (set != null) {
            return set;
        }
        Set<Throwable> setNewConcurrentHashSet = Sets.newConcurrentHashSet();
        addInitialException(setNewConcurrentHashSet);
        ATOMIC_HELPER.compareAndSetSeenExceptions(this, null, setNewConcurrentHashSet);
        return this.seenExceptions;
    }
}
