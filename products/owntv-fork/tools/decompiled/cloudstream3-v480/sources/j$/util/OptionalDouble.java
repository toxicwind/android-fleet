package j$.util;

import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes3.dex */
public final class OptionalDouble {
    private static final OptionalDouble EMPTY = new OptionalDouble();
    private final boolean isPresent;
    private final double value;

    /* JADX INFO: renamed from: j$.util.OptionalDouble$0, reason: invalid class name */
    public abstract /* synthetic */ class AnonymousClass0 {
        public static /* synthetic */ int m(double d) {
            long jDoubleToLongBits = Double.doubleToLongBits(d);
            return (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
        }
    }

    private OptionalDouble() {
        this.isPresent = false;
        this.value = Double.NaN;
    }

    public static OptionalDouble empty() {
        return EMPTY;
    }

    private OptionalDouble(double d) {
        this.isPresent = true;
        this.value = d;
    }

    public static OptionalDouble of(double d) {
        return new OptionalDouble(d);
    }

    public double getAsDouble() {
        if (!this.isPresent) {
            throw new NoSuchElementException("No value present");
        }
        return this.value;
    }

    public boolean isPresent() {
        return this.isPresent;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OptionalDouble)) {
            return false;
        }
        OptionalDouble optionalDouble = (OptionalDouble) obj;
        boolean z = this.isPresent;
        if (z && optionalDouble.isPresent) {
            return Double.compare(this.value, optionalDouble.value) == 0;
        }
        return z == optionalDouble.isPresent;
    }

    public int hashCode() {
        if (this.isPresent) {
            return AnonymousClass0.m(this.value);
        }
        return 0;
    }

    public String toString() {
        return this.isPresent ? String.format("OptionalDouble[%s]", Double.valueOf(this.value)) : "OptionalDouble.empty";
    }
}
