package kotlin.jvm.internal;

import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: PrimitiveSpreadBuilders.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ByteSpreadBuilder extends PrimitiveSpreadBuilder<byte[]> {
    private final byte[] values;

    public ByteSpreadBuilder(int i) {
        super(i);
        this.values = new byte[i];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.jvm.internal.PrimitiveSpreadBuilder
    public int getSize(@NotNull byte[] getSize) {
        Intrinsics.checkParameterIsNotNull(getSize, "$this$getSize");
        return getSize.length;
    }

    public final void add(byte b) {
        byte[] bArr = this.values;
        int position = getPosition();
        setPosition(position + 1);
        bArr[position] = b;
    }

    @NotNull
    public final byte[] toArray() {
        return toArray(this.values, new byte[size()]);
    }
}
