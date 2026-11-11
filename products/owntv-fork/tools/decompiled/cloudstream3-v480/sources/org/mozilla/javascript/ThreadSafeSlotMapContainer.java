package org.mozilla.javascript;

import java.util.Iterator;
import java.util.concurrent.locks.StampedLock;
import retrofit2.Reflection$Java8$$ExternalSyntheticApiModelOutline0;

/* JADX INFO: loaded from: classes8.dex */
class ThreadSafeSlotMapContainer extends SlotMapContainer {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final StampedLock lock;

    ThreadSafeSlotMapContainer() {
        this.lock = Reflection$Java8$$ExternalSyntheticApiModelOutline0.m11036m();
    }

    ThreadSafeSlotMapContainer(int i) {
        super(i);
        this.lock = Reflection$Java8$$ExternalSyntheticApiModelOutline0.m11036m();
    }

    @Override // org.mozilla.javascript.SlotMapContainer, org.mozilla.javascript.SlotMap
    public int size() {
        long jTryOptimisticRead = this.lock.tryOptimisticRead();
        int size = this.map.size();
        if (this.lock.validate(jTryOptimisticRead)) {
            return size;
        }
        long lock = this.lock.readLock();
        try {
            return this.map.size();
        } finally {
            this.lock.unlockRead(lock);
        }
    }

    @Override // org.mozilla.javascript.SlotMapContainer
    public int dirtySize() {
        return this.map.size();
    }

    @Override // org.mozilla.javascript.SlotMapContainer, org.mozilla.javascript.SlotMap
    public boolean isEmpty() {
        long jTryOptimisticRead = this.lock.tryOptimisticRead();
        boolean zIsEmpty = this.map.isEmpty();
        if (this.lock.validate(jTryOptimisticRead)) {
            return zIsEmpty;
        }
        long lock = this.lock.readLock();
        try {
            return this.map.isEmpty();
        } finally {
            this.lock.unlockRead(lock);
        }
    }

    @Override // org.mozilla.javascript.SlotMapContainer, org.mozilla.javascript.SlotMap
    public Slot modify(Object obj, int i, int i2) {
        long jWriteLock = this.lock.writeLock();
        try {
            checkMapSize();
            return this.map.modify(obj, i, i2);
        } finally {
            this.lock.unlockWrite(jWriteLock);
        }
    }

    @Override // org.mozilla.javascript.SlotMapContainer, org.mozilla.javascript.SlotMap
    public <S extends Slot> S compute(Object obj, int i, SlotMap.SlotComputer<S> slotComputer) {
        long jWriteLock = this.lock.writeLock();
        try {
            return (S) this.map.compute(obj, i, slotComputer);
        } finally {
            this.lock.unlockWrite(jWriteLock);
        }
    }

    @Override // org.mozilla.javascript.SlotMapContainer, org.mozilla.javascript.SlotMap
    public Slot query(Object obj, int i) {
        long jTryOptimisticRead = this.lock.tryOptimisticRead();
        Slot slotQuery = this.map.query(obj, i);
        if (this.lock.validate(jTryOptimisticRead)) {
            return slotQuery;
        }
        long lock = this.lock.readLock();
        try {
            return this.map.query(obj, i);
        } finally {
            this.lock.unlockRead(lock);
        }
    }

    @Override // org.mozilla.javascript.SlotMapContainer, org.mozilla.javascript.SlotMap
    public void add(Slot slot) {
        long jWriteLock = this.lock.writeLock();
        try {
            checkMapSize();
            this.map.add(slot);
        } finally {
            this.lock.unlockWrite(jWriteLock);
        }
    }

    @Override // org.mozilla.javascript.SlotMapContainer
    public long readLock() {
        return this.lock.readLock();
    }

    @Override // org.mozilla.javascript.SlotMapContainer
    public void unlockRead(long j) {
        this.lock.unlockRead(j);
    }

    @Override // org.mozilla.javascript.SlotMapContainer, java.lang.Iterable
    public Iterator<Slot> iterator() {
        return this.map.iterator();
    }

    @Override // org.mozilla.javascript.SlotMapContainer
    protected void checkMapSize() {
        super.checkMapSize();
    }
}
