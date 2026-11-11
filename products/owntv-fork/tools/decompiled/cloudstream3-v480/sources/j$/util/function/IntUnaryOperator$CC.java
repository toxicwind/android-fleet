package j$.util.function;

import j$.util.Objects;
import java.util.function.IntUnaryOperator;

/* JADX INFO: renamed from: j$.util.function.IntUnaryOperator$-CC, reason: invalid class name */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class IntUnaryOperator$CC {
    public static IntUnaryOperator $default$compose(final IntUnaryOperator intUnaryOperator, final IntUnaryOperator intUnaryOperator2) {
        Objects.requireNonNull(intUnaryOperator2);
        return new IntUnaryOperator() { // from class: j$.util.function.IntUnaryOperator$1
            public /* synthetic */ IntUnaryOperator andThen(IntUnaryOperator intUnaryOperator3) {
                return IntUnaryOperator$CC.$default$andThen(this, intUnaryOperator3);
            }

            @Override // java.util.function.IntUnaryOperator
            public final int applyAsInt(int i) {
                return intUnaryOperator.applyAsInt(intUnaryOperator2.applyAsInt(i));
            }

            public /* synthetic */ IntUnaryOperator compose(IntUnaryOperator intUnaryOperator3) {
                return IntUnaryOperator$CC.$default$compose(this, intUnaryOperator3);
            }
        };
    }

    public static IntUnaryOperator $default$andThen(final IntUnaryOperator intUnaryOperator, final IntUnaryOperator intUnaryOperator2) {
        Objects.requireNonNull(intUnaryOperator2);
        return new IntUnaryOperator() { // from class: j$.util.function.IntUnaryOperator$0
            public /* synthetic */ IntUnaryOperator andThen(IntUnaryOperator intUnaryOperator3) {
                return IntUnaryOperator$CC.$default$andThen(this, intUnaryOperator3);
            }

            @Override // java.util.function.IntUnaryOperator
            public final int applyAsInt(int i) {
                return intUnaryOperator2.applyAsInt(intUnaryOperator.applyAsInt(i));
            }

            public /* synthetic */ IntUnaryOperator compose(IntUnaryOperator intUnaryOperator3) {
                return IntUnaryOperator$CC.$default$compose(this, intUnaryOperator3);
            }
        };
    }
}
