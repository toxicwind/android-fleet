package org.mozilla.javascript;

/* JADX INFO: loaded from: classes8.dex */
public class LazyLoadSlot extends Slot {
    LazyLoadSlot(Object obj, int i) {
        super(obj, i, 0);
    }

    LazyLoadSlot(Slot slot) {
        super(slot);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.mozilla.javascript.Slot
    public LazyLoadSlot copySlot() {
        LazyLoadSlot lazyLoadSlot = new LazyLoadSlot(this);
        lazyLoadSlot.value = this.value;
        lazyLoadSlot.next = null;
        lazyLoadSlot.orderedNext = null;
        return lazyLoadSlot;
    }

    @Override // org.mozilla.javascript.Slot
    public Object getValue(Scriptable scriptable) {
        Object obj = this.value;
        if (!(obj instanceof LazilyLoadedCtor)) {
            return obj;
        }
        LazilyLoadedCtor lazilyLoadedCtor = (LazilyLoadedCtor) obj;
        try {
            lazilyLoadedCtor.init();
            return lazilyLoadedCtor.getValue();
        } finally {
            this.value = lazilyLoadedCtor.getValue();
        }
    }
}
