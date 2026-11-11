package androidx.emoji2.text.flatbuffer;

import java.util.function.Supplier;

/* JADX INFO: compiled from: D8$$SyntheticClass */
/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class Utf8Old$$ExternalSyntheticThreadLocal1 extends ThreadLocal {
    public final /* synthetic */ Supplier initialValueSupplier;

    @Override // java.lang.ThreadLocal
    protected /* synthetic */ Object initialValue() {
        return this.initialValueSupplier.get();
    }
}
