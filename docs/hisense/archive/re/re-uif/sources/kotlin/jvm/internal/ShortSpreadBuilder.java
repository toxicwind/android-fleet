package kotlin.jvm.internal;

import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: PrimitiveSpreadBuilders.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ShortSpreadBuilder extends PrimitiveSpreadBuilder<short[]> {
    private final short[] values;

    public ShortSpreadBuilder(int i) {
        super(i);
        this.values = new short[i];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.jvm.internal.PrimitiveSpreadBuilder
    public int getSize(@NotNull short[] getSize) {
        Intrinsics.checkParameterIsNotNull(getSize, "$this$getSize");
        return getSize.length;
    }

    public final void add(short s) {
        short[] sArr = this.values;
        int position = getPosition();
        setPosition(position + 1);
        sArr[position] = s;
    }

    @NotNull
    public final short[] toArray() {
        return toArray(this.values, new short[size()]);
    }
}
