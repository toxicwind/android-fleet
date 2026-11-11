package org.mozilla.javascript;

/* JADX INFO: loaded from: classes8.dex */
public interface SlotMap extends Iterable<Slot> {

    @FunctionalInterface
    public interface SlotComputer<S extends Slot> {
        S compute(Object obj, int i, Slot slot);
    }

    void add(Slot slot);

    <S extends Slot> S compute(Object obj, int i, SlotComputer<S> slotComputer);

    boolean isEmpty();

    Slot modify(Object obj, int i, int i2);

    Slot query(Object obj, int i);

    int size();
}
