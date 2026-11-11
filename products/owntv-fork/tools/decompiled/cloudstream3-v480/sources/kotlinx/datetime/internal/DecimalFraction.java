package kotlinx.datetime.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: math.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0003J\u0011\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0000H\u0096\u0002J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\r\u001a\u0004\u0018\u00010\u0010H\u0096\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lkotlinx/datetime/internal/DecimalFraction;", "", "fractionalPart", "", "digits", "<init>", "(II)V", "getFractionalPart", "()I", "getDigits", "fractionalPartWithNDigits", "newDigits", "compareTo", "other", "equals", "", "", "toString", "", "hashCode", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class DecimalFraction implements Comparable<DecimalFraction> {
    private final int digits;
    private final int fractionalPart;

    public DecimalFraction(int i, int i2) {
        this.fractionalPart = i;
        this.digits = i2;
        if (i2 >= 0) {
            return;
        }
        throw new IllegalArgumentException(("Digits must be non-negative, but was " + i2).toString());
    }

    public final int getFractionalPart() {
        return this.fractionalPart;
    }

    public final int getDigits() {
        return this.digits;
    }

    public final int fractionalPartWithNDigits(int newDigits) {
        int i = this.digits;
        if (newDigits == i) {
            return this.fractionalPart;
        }
        int i2 = this.fractionalPart;
        if (newDigits > i) {
            return i2 * MathKt.getPOWERS_OF_TEN()[newDigits - this.digits];
        }
        return i2 / MathKt.getPOWERS_OF_TEN()[this.digits - newDigits];
    }

    @Override // java.lang.Comparable
    public int compareTo(DecimalFraction other) {
        Intrinsics.checkNotNullParameter(other, "other");
        int iMax = Math.max(this.digits, other.digits);
        return Intrinsics.compare(fractionalPartWithNDigits(iMax), other.fractionalPartWithNDigits(iMax));
    }

    public boolean equals(Object other) {
        return (other instanceof DecimalFraction) && compareTo((DecimalFraction) other) == 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = MathKt.getPOWERS_OF_TEN()[this.digits];
        sb.append(this.fractionalPart / i);
        sb.append('.');
        sb.append(StringsKt.removePrefix(String.valueOf(i + (this.fractionalPart % i)), (CharSequence) "1"));
        return sb.toString();
    }

    public int hashCode() {
        throw new UnsupportedOperationException("DecimalFraction is not supposed to be used as a hash key");
    }
}
