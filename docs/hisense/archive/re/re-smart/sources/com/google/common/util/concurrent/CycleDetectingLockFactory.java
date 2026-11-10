package com.google.common.util.concurrent;

import b.a.a.a.a;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.MapMaker;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.j2objc.annotations.Weak;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
@CanIgnoreReturnValue
@Beta
@GwtIncompatible
public class CycleDetectingLockFactory {
    public final Policy policy;
    public static final ConcurrentMap<Class<? extends Enum>, Map<? extends Enum, LockGraphNode>> lockGraphNodesPerType = new MapMaker().weakKeys().makeMap();
    public static final Logger logger = Logger.getLogger(CycleDetectingLockFactory.class.getName());
    public static final ThreadLocal<ArrayList<LockGraphNode>> acquiredLocks = new ThreadLocal<ArrayList<LockGraphNode>>() { // from class: com.google.common.util.concurrent.CycleDetectingLockFactory.1
        @Override // java.lang.ThreadLocal
        public ArrayList<LockGraphNode> initialValue() {
            return Lists.newArrayListWithCapacity(3);
        }
    };

    private interface CycleDetectingLock {
        LockGraphNode getLockGraphNode();

        boolean isAcquiredByCurrentThread();
    }

    final class CycleDetectingReentrantLock extends ReentrantLock implements CycleDetectingLock {
        public final LockGraphNode lockGraphNode;

        @Override // com.google.common.util.concurrent.CycleDetectingLockFactory.CycleDetectingLock
        public LockGraphNode getLockGraphNode() {
            return this.lockGraphNode;
        }

        @Override // com.google.common.util.concurrent.CycleDetectingLockFactory.CycleDetectingLock
        public boolean isAcquiredByCurrentThread() {
            return isHeldByCurrentThread();
        }

        @Override // java.util.concurrent.locks.ReentrantLock, java.util.concurrent.locks.Lock
        public void lock() {
            CycleDetectingLockFactory.this.aboutToAcquire(this);
            try {
                super.lock();
            } finally {
                CycleDetectingLockFactory.lockStateChanged(this);
            }
        }

        @Override // java.util.concurrent.locks.ReentrantLock, java.util.concurrent.locks.Lock
        public void lockInterruptibly() {
            CycleDetectingLockFactory.this.aboutToAcquire(this);
            try {
                super.lockInterruptibly();
            } finally {
                CycleDetectingLockFactory.lockStateChanged(this);
            }
        }

        @Override // java.util.concurrent.locks.ReentrantLock, java.util.concurrent.locks.Lock
        public boolean tryLock() {
            CycleDetectingLockFactory.this.aboutToAcquire(this);
            try {
                return super.tryLock();
            } finally {
                CycleDetectingLockFactory.lockStateChanged(this);
            }
        }

        @Override // java.util.concurrent.locks.ReentrantLock, java.util.concurrent.locks.Lock
        public void unlock() {
            try {
                super.unlock();
            } finally {
                CycleDetectingLockFactory.lockStateChanged(this);
            }
        }

        public CycleDetectingReentrantLock(LockGraphNode lockGraphNode, boolean z) {
            super(z);
            if (lockGraphNode == null) {
                throw new NullPointerException();
            }
            this.lockGraphNode = lockGraphNode;
        }

        @Override // java.util.concurrent.locks.ReentrantLock, java.util.concurrent.locks.Lock
        public boolean tryLock(long j, TimeUnit timeUnit) {
            CycleDetectingLockFactory.this.aboutToAcquire(this);
            try {
                return super.tryLock(j, timeUnit);
            } finally {
                CycleDetectingLockFactory.lockStateChanged(this);
            }
        }
    }

    final class CycleDetectingReentrantReadWriteLock extends ReentrantReadWriteLock implements CycleDetectingLock {
        public final LockGraphNode lockGraphNode;
        public final CycleDetectingReentrantReadLock readLock;
        public final CycleDetectingReentrantWriteLock writeLock;

        @Override // com.google.common.util.concurrent.CycleDetectingLockFactory.CycleDetectingLock
        public LockGraphNode getLockGraphNode() {
            return this.lockGraphNode;
        }

        @Override // com.google.common.util.concurrent.CycleDetectingLockFactory.CycleDetectingLock
        public boolean isAcquiredByCurrentThread() {
            return isWriteLockedByCurrentThread() || getReadHoldCount() > 0;
        }

        public CycleDetectingReentrantReadWriteLock(CycleDetectingLockFactory cycleDetectingLockFactory, LockGraphNode lockGraphNode, boolean z) {
            super(z);
            this.readLock = cycleDetectingLockFactory.new CycleDetectingReentrantReadLock(this);
            this.writeLock = cycleDetectingLockFactory.new CycleDetectingReentrantWriteLock(this);
            if (lockGraphNode == null) {
                throw new NullPointerException();
            }
            this.lockGraphNode = lockGraphNode;
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock, java.util.concurrent.locks.ReadWriteLock
        public ReentrantReadWriteLock.ReadLock readLock() {
            return this.readLock;
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock, java.util.concurrent.locks.ReadWriteLock
        public ReentrantReadWriteLock.WriteLock writeLock() {
            return this.writeLock;
        }
    }

    private static class ExampleStackTrace extends IllegalStateException {
        public static final StackTraceElement[] EMPTY_STACK_TRACE = new StackTraceElement[0];
        public static final ImmutableSet<String> EXCLUDED_CLASS_NAMES = ImmutableSet.of(CycleDetectingLockFactory.class.getName(), ExampleStackTrace.class.getName(), LockGraphNode.class.getName());

        /* JADX WARN: Illegal instructions before constructor call */
        public ExampleStackTrace(LockGraphNode lockGraphNode, LockGraphNode lockGraphNode2) {
            String lockName = lockGraphNode.getLockName();
            String lockName2 = lockGraphNode2.getLockName();
            super(a.a(a.a((Object) lockName2, a.a((Object) lockName, 4)), lockName, " -> ", lockName2));
            StackTraceElement[] stackTrace = getStackTrace();
            int length = stackTrace.length;
            for (int i = 0; i < length; i++) {
                if (WithExplicitOrdering.class.getName().equals(stackTrace[i].getClassName())) {
                    setStackTrace(EMPTY_STACK_TRACE);
                    return;
                } else {
                    if (!EXCLUDED_CLASS_NAMES.contains(stackTrace[i].getClassName())) {
                        setStackTrace((StackTraceElement[]) Arrays.copyOfRange(stackTrace, i, length));
                        return;
                    }
                }
            }
        }
    }

    private static class LockGraphNode {
        public final Map<LockGraphNode, ExampleStackTrace> allowedPriorLocks = new MapMaker().weakKeys().makeMap();
        public final Map<LockGraphNode, PotentialDeadlockException> disallowedPriorLocks = new MapMaker().weakKeys().makeMap();
        public final String lockName;

        public LockGraphNode(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.lockName = str;
        }

        private ExampleStackTrace findPathTo(LockGraphNode lockGraphNode, Set<LockGraphNode> set) {
            if (!set.add(this)) {
                return null;
            }
            ExampleStackTrace exampleStackTrace = this.allowedPriorLocks.get(lockGraphNode);
            if (exampleStackTrace != null) {
                return exampleStackTrace;
            }
            for (Map.Entry<LockGraphNode, ExampleStackTrace> entry : this.allowedPriorLocks.entrySet()) {
                LockGraphNode key = entry.getKey();
                ExampleStackTrace exampleStackTraceFindPathTo = key.findPathTo(lockGraphNode, set);
                if (exampleStackTraceFindPathTo != null) {
                    ExampleStackTrace exampleStackTrace2 = new ExampleStackTrace(key, this);
                    exampleStackTrace2.setStackTrace(entry.getValue().getStackTrace());
                    exampleStackTrace2.initCause(exampleStackTraceFindPathTo);
                    return exampleStackTrace2;
                }
            }
            return null;
        }

        public void checkAcquiredLock(Policy policy, LockGraphNode lockGraphNode) {
            Preconditions.checkState(this != lockGraphNode, "Attempted to acquire multiple locks with the same rank %s", lockGraphNode.getLockName());
            if (this.allowedPriorLocks.containsKey(lockGraphNode)) {
                return;
            }
            PotentialDeadlockException potentialDeadlockException = this.disallowedPriorLocks.get(lockGraphNode);
            AnonymousClass1 anonymousClass1 = null;
            if (potentialDeadlockException != null) {
                policy.handlePotentialDeadlock(new PotentialDeadlockException(lockGraphNode, this, potentialDeadlockException.getConflictingStackTrace(), anonymousClass1));
                return;
            }
            ExampleStackTrace exampleStackTraceFindPathTo = lockGraphNode.findPathTo(this, Sets.newIdentityHashSet());
            if (exampleStackTraceFindPathTo == null) {
                this.allowedPriorLocks.put(lockGraphNode, new ExampleStackTrace(lockGraphNode, this));
                return;
            }
            PotentialDeadlockException potentialDeadlockException2 = new PotentialDeadlockException(lockGraphNode, this, exampleStackTraceFindPathTo, anonymousClass1);
            this.disallowedPriorLocks.put(lockGraphNode, potentialDeadlockException2);
            policy.handlePotentialDeadlock(potentialDeadlockException2);
        }

        public void checkAcquiredLocks(Policy policy, List<LockGraphNode> list) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                checkAcquiredLock(policy, list.get(i));
            }
        }

        public String getLockName() {
            return this.lockName;
        }
    }

    @Beta
    public enum Policies implements Policy {
        THROW { // from class: com.google.common.util.concurrent.CycleDetectingLockFactory.Policies.1
            @Override // com.google.common.util.concurrent.CycleDetectingLockFactory.Policy
            public void handlePotentialDeadlock(PotentialDeadlockException potentialDeadlockException) {
                throw potentialDeadlockException;
            }
        },
        WARN { // from class: com.google.common.util.concurrent.CycleDetectingLockFactory.Policies.2
            @Override // com.google.common.util.concurrent.CycleDetectingLockFactory.Policy
            public void handlePotentialDeadlock(PotentialDeadlockException potentialDeadlockException) {
                CycleDetectingLockFactory.logger.log(Level.SEVERE, "Detected potential deadlock", (Throwable) potentialDeadlockException);
            }
        },
        DISABLED { // from class: com.google.common.util.concurrent.CycleDetectingLockFactory.Policies.3
            @Override // com.google.common.util.concurrent.CycleDetectingLockFactory.Policy
            public void handlePotentialDeadlock(PotentialDeadlockException potentialDeadlockException) {
            }
        };

        /* synthetic */ Policies(AnonymousClass1 anonymousClass1) {
        }
    }

    @Beta
    public interface Policy {
        void handlePotentialDeadlock(PotentialDeadlockException potentialDeadlockException);
    }

    @Beta
    public static final class WithExplicitOrdering<E extends Enum<E>> extends CycleDetectingLockFactory {
        public final Map<E, LockGraphNode> lockGraphNodes;

        @VisibleForTesting
        public WithExplicitOrdering(Policy policy, Map<E, LockGraphNode> map) {
            super(policy, null);
            this.lockGraphNodes = map;
        }

        public ReentrantLock newReentrantLock(E e2) {
            return newReentrantLock((Enum) e2, false);
        }

        public ReentrantReadWriteLock newReentrantReadWriteLock(E e2) {
            return newReentrantReadWriteLock((Enum) e2, false);
        }

        public ReentrantLock newReentrantLock(E e2, boolean z) {
            return this.policy == Policies.DISABLED ? new ReentrantLock(z) : new CycleDetectingReentrantLock(this.lockGraphNodes.get(e2), z);
        }

        public ReentrantReadWriteLock newReentrantReadWriteLock(E e2, boolean z) {
            return this.policy == Policies.DISABLED ? new ReentrantReadWriteLock(z) : new CycleDetectingReentrantReadWriteLock(this, this.lockGraphNodes.get(e2), z);
        }
    }

    public CycleDetectingLockFactory(Policy policy) {
        if (policy == null) {
            throw new NullPointerException();
        }
        this.policy = policy;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aboutToAcquire(CycleDetectingLock cycleDetectingLock) {
        if (cycleDetectingLock.isAcquiredByCurrentThread()) {
            return;
        }
        ArrayList<LockGraphNode> arrayList = acquiredLocks.get();
        LockGraphNode lockGraphNode = cycleDetectingLock.getLockGraphNode();
        lockGraphNode.checkAcquiredLocks(this.policy, arrayList);
        arrayList.add(lockGraphNode);
    }

    @VisibleForTesting
    public static <E extends Enum<E>> Map<E, LockGraphNode> createNodes(Class<E> cls) {
        EnumMap enumMapNewEnumMap = Maps.newEnumMap(cls);
        E[] enumConstants = cls.getEnumConstants();
        int length = enumConstants.length;
        ArrayList arrayListNewArrayListWithCapacity = Lists.newArrayListWithCapacity(length);
        int i = 0;
        for (E e2 : enumConstants) {
            LockGraphNode lockGraphNode = new LockGraphNode(getLockName(e2));
            arrayListNewArrayListWithCapacity.add(lockGraphNode);
            enumMapNewEnumMap.put(e2, lockGraphNode);
        }
        for (int i2 = 1; i2 < length; i2++) {
            ((LockGraphNode) arrayListNewArrayListWithCapacity.get(i2)).checkAcquiredLocks(Policies.THROW, arrayListNewArrayListWithCapacity.subList(0, i2));
        }
        while (i < length - 1) {
            i++;
            ((LockGraphNode) arrayListNewArrayListWithCapacity.get(i)).checkAcquiredLocks(Policies.DISABLED, arrayListNewArrayListWithCapacity.subList(i, length));
        }
        return Collections.unmodifiableMap(enumMapNewEnumMap);
    }

    public static String getLockName(Enum<?> r3) {
        String simpleName = r3.getDeclaringClass().getSimpleName();
        String strName = r3.name();
        return a.a(a.a((Object) strName, simpleName.length() + 1), simpleName, ".", strName);
    }

    public static Map<? extends Enum, LockGraphNode> getOrCreateNodes(Class<? extends Enum> cls) {
        Map<? extends Enum, LockGraphNode> map = lockGraphNodesPerType.get(cls);
        if (map != null) {
            return map;
        }
        Map<? extends Enum, LockGraphNode> mapCreateNodes = createNodes(cls);
        return (Map) MoreObjects.firstNonNull(lockGraphNodesPerType.putIfAbsent(cls, mapCreateNodes), mapCreateNodes);
    }

    public static void lockStateChanged(CycleDetectingLock cycleDetectingLock) {
        if (cycleDetectingLock.isAcquiredByCurrentThread()) {
            return;
        }
        ArrayList<LockGraphNode> arrayList = acquiredLocks.get();
        LockGraphNode lockGraphNode = cycleDetectingLock.getLockGraphNode();
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (arrayList.get(size) == lockGraphNode) {
                arrayList.remove(size);
                return;
            }
        }
    }

    public static CycleDetectingLockFactory newInstance(Policy policy) {
        return new CycleDetectingLockFactory(policy);
    }

    public static <E extends Enum<E>> WithExplicitOrdering<E> newInstanceWithExplicitOrdering(Class<E> cls, Policy policy) {
        if (cls == null) {
            throw new NullPointerException();
        }
        if (policy != null) {
            return new WithExplicitOrdering<>(policy, getOrCreateNodes(cls));
        }
        throw new NullPointerException();
    }

    public ReentrantLock newReentrantLock(String str) {
        return newReentrantLock(str, false);
    }

    public ReentrantReadWriteLock newReentrantReadWriteLock(String str) {
        return newReentrantReadWriteLock(str, false);
    }

    public ReentrantLock newReentrantLock(String str, boolean z) {
        return this.policy == Policies.DISABLED ? new ReentrantLock(z) : new CycleDetectingReentrantLock(new LockGraphNode(str), z);
    }

    public ReentrantReadWriteLock newReentrantReadWriteLock(String str, boolean z) {
        return this.policy == Policies.DISABLED ? new ReentrantReadWriteLock(z) : new CycleDetectingReentrantReadWriteLock(this, new LockGraphNode(str), z);
    }

    @Beta
    public static final class PotentialDeadlockException extends ExampleStackTrace {
        public final ExampleStackTrace conflictingStackTrace;

        public PotentialDeadlockException(LockGraphNode lockGraphNode, LockGraphNode lockGraphNode2, ExampleStackTrace exampleStackTrace) {
            super(lockGraphNode, lockGraphNode2);
            this.conflictingStackTrace = exampleStackTrace;
            initCause(exampleStackTrace);
        }

        public ExampleStackTrace getConflictingStackTrace() {
            return this.conflictingStackTrace;
        }

        @Override // java.lang.Throwable
        public String getMessage() {
            StringBuilder sb = new StringBuilder(super.getMessage());
            for (Throwable cause = this.conflictingStackTrace; cause != null; cause = cause.getCause()) {
                sb.append(", ");
                sb.append(cause.getMessage());
            }
            return sb.toString();
        }

        public /* synthetic */ PotentialDeadlockException(LockGraphNode lockGraphNode, LockGraphNode lockGraphNode2, ExampleStackTrace exampleStackTrace, AnonymousClass1 anonymousClass1) {
            super(lockGraphNode, lockGraphNode2);
            this.conflictingStackTrace = exampleStackTrace;
            initCause(exampleStackTrace);
        }
    }

    public /* synthetic */ CycleDetectingLockFactory(Policy policy, AnonymousClass1 anonymousClass1) {
        if (policy != null) {
            this.policy = policy;
            return;
        }
        throw new NullPointerException();
    }

    private class CycleDetectingReentrantReadLock extends ReentrantReadWriteLock.ReadLock {

        @Weak
        public final CycleDetectingReentrantReadWriteLock readWriteLock;

        public CycleDetectingReentrantReadLock(CycleDetectingReentrantReadWriteLock cycleDetectingReentrantReadWriteLock) {
            super(cycleDetectingReentrantReadWriteLock);
            this.readWriteLock = cycleDetectingReentrantReadWriteLock;
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock, java.util.concurrent.locks.Lock
        public void lock() {
            CycleDetectingLockFactory.this.aboutToAcquire(this.readWriteLock);
            try {
                super.lock();
            } finally {
                CycleDetectingLockFactory.lockStateChanged(this.readWriteLock);
            }
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock, java.util.concurrent.locks.Lock
        public void lockInterruptibly() {
            CycleDetectingLockFactory.this.aboutToAcquire(this.readWriteLock);
            try {
                super.lockInterruptibly();
            } finally {
                CycleDetectingLockFactory.lockStateChanged(this.readWriteLock);
            }
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock, java.util.concurrent.locks.Lock
        public boolean tryLock() {
            CycleDetectingLockFactory.this.aboutToAcquire(this.readWriteLock);
            try {
                return super.tryLock();
            } finally {
                CycleDetectingLockFactory.lockStateChanged(this.readWriteLock);
            }
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock, java.util.concurrent.locks.Lock
        public void unlock() {
            try {
                super.unlock();
            } finally {
                CycleDetectingLockFactory.lockStateChanged(this.readWriteLock);
            }
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock, java.util.concurrent.locks.Lock
        public boolean tryLock(long j, TimeUnit timeUnit) {
            CycleDetectingLockFactory.this.aboutToAcquire(this.readWriteLock);
            try {
                return super.tryLock(j, timeUnit);
            } finally {
                CycleDetectingLockFactory.lockStateChanged(this.readWriteLock);
            }
        }
    }

    private class CycleDetectingReentrantWriteLock extends ReentrantReadWriteLock.WriteLock {

        @Weak
        public final CycleDetectingReentrantReadWriteLock readWriteLock;

        public CycleDetectingReentrantWriteLock(CycleDetectingReentrantReadWriteLock cycleDetectingReentrantReadWriteLock) {
            super(cycleDetectingReentrantReadWriteLock);
            this.readWriteLock = cycleDetectingReentrantReadWriteLock;
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock, java.util.concurrent.locks.Lock
        public void lock() {
            CycleDetectingLockFactory.this.aboutToAcquire(this.readWriteLock);
            try {
                super.lock();
            } finally {
                CycleDetectingLockFactory.lockStateChanged(this.readWriteLock);
            }
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock, java.util.concurrent.locks.Lock
        public void lockInterruptibly() {
            CycleDetectingLockFactory.this.aboutToAcquire(this.readWriteLock);
            try {
                super.lockInterruptibly();
            } finally {
                CycleDetectingLockFactory.lockStateChanged(this.readWriteLock);
            }
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock, java.util.concurrent.locks.Lock
        public boolean tryLock() {
            CycleDetectingLockFactory.this.aboutToAcquire(this.readWriteLock);
            try {
                return super.tryLock();
            } finally {
                CycleDetectingLockFactory.lockStateChanged(this.readWriteLock);
            }
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock, java.util.concurrent.locks.Lock
        public void unlock() {
            try {
                super.unlock();
            } finally {
                CycleDetectingLockFactory.lockStateChanged(this.readWriteLock);
            }
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock, java.util.concurrent.locks.Lock
        public boolean tryLock(long j, TimeUnit timeUnit) {
            CycleDetectingLockFactory.this.aboutToAcquire(this.readWriteLock);
            try {
                return super.tryLock(j, timeUnit);
            } finally {
                CycleDetectingLockFactory.lockStateChanged(this.readWriteLock);
            }
        }
    }
}
