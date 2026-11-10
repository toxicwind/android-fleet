package kotlin.jvm.internal;

import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: PrimitiveSpreadBuilders.kt */
/* JADX INFO: loaded from: classes.dex */
public final class FloatSpreadBuilder extends PrimitiveSpreadBuilder<float[]> {
    private final float[] values;

    public FloatSpreadBuilder(int i) {
        super(i);
        this.values = new float[i];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.jvm.internal.PrimitiveSpreadBuilder
    public int getSize(@NotNull float[] getSize) {
        Intrinsics.checkParameterIsNotNull(getSize, "$this$getSize");
        return getSize.length;
    }

    public final void add(float f) {
        float[] fArr = this.values;
        int position = getPosition();
        setPosition(position + 1);
        fArr[position] = f;
    }

    @NotNull
    public final float[] toArray() {
        return toArray(this.values, new float[size()]);
    }
}
