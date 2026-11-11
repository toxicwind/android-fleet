package org.mozilla.javascript;

import java.util.Iterator;

/* JADX INFO: loaded from: classes8.dex */
class SlotMapContainer implements SlotMap {
    private static final int DEFAULT_SIZE = 10;
    private static final int LARGE_HASH_SIZE = 2000;
    protected SlotMap map;

    public long readLock() {
        return 0L;
    }

    public void unlockRead(long j) {
    }

    SlotMapContainer() {
        this(10);
    }

    SlotMapContainer(int i) {
        if (i > 2000) {
            this.map = new HashSlotMap();
        } else {
            this.map = new EmbeddedSlotMap();
        }
    }

    @Override // org.mozilla.javascript.SlotMap
    public int size() {
        return this.map.size();
    }

    public int dirtySize() {
        return this.map.size();
    }

    @Override // org.mozilla.javascript.SlotMap
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override // org.mozilla.javascript.SlotMap
    public Slot modify(Object obj, int i, int i2) {
        checkMapSize();
        return this.map.modify(obj, i, i2);
    }

    @Override // org.mozilla.javascript.SlotMap
    public <S extends Slot> S compute(Object obj, int i, SlotMap.SlotComputer<S> slotComputer) {
        return (S) this.map.compute(obj, i, slotComputer);
    }

    @Override // org.mozilla.javascript.SlotMap
    public Slot query(Object obj, int i) {
        return this.map.query(obj, i);
    }

    @Override // org.mozilla.javascript.SlotMap
    public void add(Slot slot) {
        checkMapSize();
        this.map.add(slot);
    }

    @Override // java.lang.Iterable
    public Iterator<Slot> iterator() {
        return this.map.iterator();
    }

    protected void checkMapSize() {
        SlotMap slotMap = this.map;
        if (!(slotMap instanceof EmbeddedSlotMap) || slotMap.size() < 2000) {
            return;
        }
        this.map = new HashSlotMap(this.map);
    }
}
