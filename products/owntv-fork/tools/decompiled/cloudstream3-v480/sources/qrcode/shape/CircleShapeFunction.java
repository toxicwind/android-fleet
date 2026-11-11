package qrcode.shape;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: CircleShapeFunction.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0016\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u001d\b\u0007\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Lqrcode/shape/CircleShapeFunction;", "Lqrcode/shape/RoundSquaresShapeFunction;", "squareSize", "", "innerSpace", "<init>", "(II)V", "Companion", "qrcode-kotlin_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public class CircleShapeFunction extends RoundSquaresShapeFunction {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX WARN: Illegal instructions before constructor call */
    public CircleShapeFunction() {
        int i = 0;
        this(i, i, 3, null);
    }

    public CircleShapeFunction(int i) {
        this(i, 0, 2, null);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ CircleShapeFunction(int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        i = (i3 & 1) != 0 ? 25 : i;
        this(i, (i3 & 2) != 0 ? INSTANCE.defaultInnerSpace(i) : i2);
    }

    /* JADX INFO: compiled from: CircleShapeFunction.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005J\u000e\u0010\u0007\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005¨\u0006\b"}, d2 = {"Lqrcode/shape/CircleShapeFunction$Companion;", "", "<init>", "()V", "defaultRadius", "", "squareSize", "defaultInnerSpace", "qrcode-kotlin_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int defaultRadius(int squareSize) {
            return MathKt.roundToInt(((double) squareSize) / 1.75d);
        }

        public final int defaultInnerSpace(int squareSize) {
            return MathKt.roundToInt(((double) squareSize) * 0.05d);
        }
    }

    public CircleShapeFunction(int i, int i2) {
        super(i, i, i2);
    }
}
