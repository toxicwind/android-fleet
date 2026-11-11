package org.mozilla.javascript;

/* JADX INFO: compiled from: D8$$SyntheticClass */
/* JADX INFO: loaded from: classes8.dex */
public final /* synthetic */ class ScriptableObject$$ExternalSyntheticLambda4 implements SlotMap.SlotComputer {
    @Override // org.mozilla.javascript.SlotMap.SlotComputer
    public final Slot compute(Object obj, int i, Slot slot) {
        return ScriptableObject.ensureAccessorSlot(obj, i, slot);
    }
}
