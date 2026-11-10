package kotlin.jvm.internal;

import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: PrimitiveSpreadBuilders.kt */
/* JADX INFO: loaded from: classes.dex */
public final class CharSpreadBuilder extends PrimitiveSpreadBuilder<char[]> {
    private final char[] values;

    public CharSpreadBuilder(int i) {
        super(i);
        this.values = new char[i];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.jvm.internal.PrimitiveSpreadBuilder
    public int getSize(@NotNull char[] getSize) {
        Intrinsics.checkParameterIsNotNull(getSize, "$this$getSize");
        return getSize.length;
    }

    public final void add(char c) {
        char[] cArr = this.values;
        int position = getPosition();
        setPosition(position + 1);
        cArr[position] = c;
    }

    @NotNull
    public final char[] toArray() {
        return toArray(this.values, new char[size()]);
    }
}
