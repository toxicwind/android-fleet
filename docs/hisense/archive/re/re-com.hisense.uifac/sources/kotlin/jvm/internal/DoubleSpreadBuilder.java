package kotlin.jvm.internal;

import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: PrimitiveSpreadBuilders.kt */
/* JADX INFO: loaded from: classes.dex */
public final class DoubleSpreadBuilder extends PrimitiveSpreadBuilder<double[]> {
    private final double[] values;

    public DoubleSpreadBuilder(int i) {
        super(i);
        this.values = new double[i];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.jvm.internal.PrimitiveSpreadBuilder
    public int getSize(@NotNull double[] getSize) {
        Intrinsics.checkParameterIsNotNull(getSize, "$this$getSize");
        return getSize.length;
    }

    public final void add(double d) {
        double[] dArr = this.values;
        int position = getPosition();
        setPosition(position + 1);
        dArr[position] = d;
    }

    @NotNull
    public final double[] toArray() {
        return toArray(this.values, new double[size()]);
    }
}
