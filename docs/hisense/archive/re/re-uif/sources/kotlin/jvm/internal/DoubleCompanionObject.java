package kotlin.jvm.internal;

/* JADX INFO: compiled from: PrimitiveCompanionObjects.kt */
/* JADX INFO: loaded from: classes.dex */
public final class DoubleCompanionObject {
    public static final DoubleCompanionObject INSTANCE = new DoubleCompanionObject();
    private static final double MIN_VALUE = Double.MIN_VALUE;
    private static final double MAX_VALUE = Double.MAX_VALUE;
    private static final double POSITIVE_INFINITY = Double.POSITIVE_INFINITY;
    private static final double NEGATIVE_INFINITY = Double.NEGATIVE_INFINITY;
    private static final double NaN = Double.NaN;

    private DoubleCompanionObject() {
    }

    public final double getMIN_VALUE() {
        return MIN_VALUE;
    }

    public final double getMAX_VALUE() {
        return MAX_VALUE;
    }

    public final double getPOSITIVE_INFINITY() {
        return POSITIVE_INFINITY;
    }

    public final double getNEGATIVE_INFINITY() {
        return NEGATIVE_INFINITY;
    }

    public final double getNaN() {
        return NaN;
    }
}
