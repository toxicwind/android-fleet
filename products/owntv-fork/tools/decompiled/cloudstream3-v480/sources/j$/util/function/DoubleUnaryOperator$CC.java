package j$.util.function;

import j$.util.Objects;
import java.util.function.DoubleUnaryOperator;

/* JADX INFO: renamed from: j$.util.function.DoubleUnaryOperator$-CC, reason: invalid class name */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class DoubleUnaryOperator$CC {
    public static DoubleUnaryOperator $default$compose(final DoubleUnaryOperator doubleUnaryOperator, final DoubleUnaryOperator doubleUnaryOperator2) {
        Objects.requireNonNull(doubleUnaryOperator2);
        return new DoubleUnaryOperator() { // from class: j$.util.function.DoubleUnaryOperator$0
            public /* synthetic */ DoubleUnaryOperator andThen(DoubleUnaryOperator doubleUnaryOperator3) {
                return DoubleUnaryOperator$CC.$default$andThen(this, doubleUnaryOperator3);
            }

            @Override // java.util.function.DoubleUnaryOperator
            public final double applyAsDouble(double d) {
                return doubleUnaryOperator.applyAsDouble(doubleUnaryOperator2.applyAsDouble(d));
            }

            public /* synthetic */ DoubleUnaryOperator compose(DoubleUnaryOperator doubleUnaryOperator3) {
                return DoubleUnaryOperator$CC.$default$compose(this, doubleUnaryOperator3);
            }
        };
    }

    public static DoubleUnaryOperator $default$andThen(final DoubleUnaryOperator doubleUnaryOperator, final DoubleUnaryOperator doubleUnaryOperator2) {
        Objects.requireNonNull(doubleUnaryOperator2);
        return new DoubleUnaryOperator() { // from class: j$.util.function.DoubleUnaryOperator$2
            public /* synthetic */ DoubleUnaryOperator andThen(DoubleUnaryOperator doubleUnaryOperator3) {
                return DoubleUnaryOperator$CC.$default$andThen(this, doubleUnaryOperator3);
            }

            @Override // java.util.function.DoubleUnaryOperator
            public final double applyAsDouble(double d) {
                return doubleUnaryOperator2.applyAsDouble(doubleUnaryOperator.applyAsDouble(d));
            }

            public /* synthetic */ DoubleUnaryOperator compose(DoubleUnaryOperator doubleUnaryOperator3) {
                return DoubleUnaryOperator$CC.$default$compose(this, doubleUnaryOperator3);
            }
        };
    }
}
