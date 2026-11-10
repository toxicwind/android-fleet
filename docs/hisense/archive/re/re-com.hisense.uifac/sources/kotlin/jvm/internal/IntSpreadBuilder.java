package kotlin.jvm.internal;

import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: PrimitiveSpreadBuilders.kt */
/* JADX INFO: loaded from: classes.dex */
public final class IntSpreadBuilder extends PrimitiveSpreadBuilder<int[]> {
    private final int[] values;

    public IntSpreadBuilder(int i) {
        super(i);
        this.values = new int[i];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.jvm.internal.PrimitiveSpreadBuilder
    public int getSize(@NotNull int[] getSize) {
        Intrinsics.checkParameterIsNotNull(getSize, "$this$getSize");
        return getSize.length;
    }

    public final void add(int i) {
        int[] iArr = this.values;
        int position = getPosition();
        setPosition(position + 1);
        iArr[position] = i;
    }

    @NotNull
    public final int[] toArray() {
        return toArray(this.values, new int[size()]);
    }
}
