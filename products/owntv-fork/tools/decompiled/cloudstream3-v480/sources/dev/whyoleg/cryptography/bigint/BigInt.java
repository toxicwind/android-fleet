package dev.whyoleg.cryptography.bigint;

import com.google.android.gms.cast.MediaTrack;
import java.math.BigInteger;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;

/* JADX INFO: compiled from: BigInt.jvm.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0004\n\u0002\u0010\u000f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\u0005\n\u0002\u0010\n\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0007\u0018\u0000 %2\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002:\u0001%B\u0011\b@\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\n\u0010\u000e\u001a\u00020\u000fH\u0086\u0080\u0004J\n\u0010\u0010\u001a\u00020\u0000H\u0086\u0082\u0004J\n\u0010\u0011\u001a\u00020\u0000H\u0086\u0082\u0004J\u0012\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0086\u0082\u0004J\u0012\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u0015H\u0086\u0082\u0004J\u0012\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\bH\u0086\u0082\u0004J\u0012\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u0016H\u0086\u0082\u0004J\u0012\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u0000H\u0096\u0082\u0004J\n\u0010\u0017\u001a\u00020\u0014H\u0096\u0080\u0004J\n\u0010\u0018\u001a\u00020\u0015H\u0096\u0080\u0004J\n\u0010\u0019\u001a\u00020\bH\u0096\u0080\u0004J\n\u0010\u001a\u001a\u00020\u0016H\u0096\u0080\u0004J\n\u0010\u001b\u001a\u00020\u001cH\u0096\u0080\u0004J\n\u0010\u001d\u001a\u00020\u001eH\u0096\u0080\u0004J\n\u0010\u001f\u001a\u00020 H\u0096\u0080\u0004J\n\u0010!\u001a\u00020\bH\u0096\u0080\u0004J\u0014\u0010\"\u001a\u00020#2\b\u0010\u0013\u001a\u0004\u0018\u00010$H\u0096\u0082\u0004R\u0011\u0010\u0003\u001a\u00020\u00048\u0000X\u0081\u0084\b¢\u0006\u0002\n\u0000R\u0015\u0010\u0007\u001a\u00020\b8FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0015\u0010\u000b\u001a\u00020\u00008FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006&"}, d2 = {"Ldev/whyoleg/cryptography/bigint/BigInt;", "", "", "javaBigInteger", "Ljava/math/BigInteger;", "<init>", "(Ljava/math/BigInteger;)V", MediaTrack.ROLE_SIGN, "", "getSign", "()I", "absoluteValue", "getAbsoluteValue", "()Ldev/whyoleg/cryptography/bigint/BigInt;", "magnitudeToByteArray", "", "unaryPlus", "unaryMinus", "compareTo", "other", "", "", "", "toByte", "toShort", "toInt", "toLong", "toFloat", "", "toDouble", "", "toString", "", "hashCode", "equals", "", "", "Companion", "cryptography-bigint"}, k = 1, mv = {2, 3, 0}, xi = 48)
@Serializable(with = BigIntAsStringSerializer.class)
public final class BigInt extends Number implements Comparable<BigInt> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final BigInt ZERO;
    public final BigInteger javaBigInteger;

    public final BigInt unaryPlus() {
        return this;
    }

    public BigInt(BigInteger javaBigInteger) {
        Intrinsics.checkNotNullParameter(javaBigInteger, "javaBigInteger");
        this.javaBigInteger = javaBigInteger;
    }

    @Override // java.lang.Number
    public final /* bridge */ byte byteValue() {
        return toByte();
    }

    @Override // java.lang.Number
    public final /* bridge */ double doubleValue() {
        return toDouble();
    }

    @Override // java.lang.Number
    public final /* bridge */ float floatValue() {
        return toFloat();
    }

    @Override // java.lang.Number
    public final /* bridge */ int intValue() {
        return toInt();
    }

    @Override // java.lang.Number
    public final /* bridge */ long longValue() {
        return toLong();
    }

    @Override // java.lang.Number
    public final /* bridge */ short shortValue() {
        return toShort();
    }

    /* JADX INFO: compiled from: BigInt.jvm.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0086\u0080\u0004J\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u000eR\u0015\u0010\u0004\u001a\u00020\u0005X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Ldev/whyoleg/cryptography/bigint/BigInt$Companion;", "", "<init>", "()V", "ZERO", "Ldev/whyoleg/cryptography/bigint/BigInt;", "getZERO", "()Ldev/whyoleg/cryptography/bigint/BigInt;", "fromMagnitude", MediaTrack.ROLE_SIGN, "", "magnitude", "", "serializer", "Lkotlinx/serialization/KSerializer;", "cryptography-bigint"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<BigInt> serializer() {
            return BigIntAsStringSerializer.INSTANCE;
        }

        public final BigInt getZERO() {
            return BigInt.ZERO;
        }

        public final BigInt fromMagnitude(int sign, byte[] magnitude) {
            Intrinsics.checkNotNullParameter(magnitude, "magnitude");
            return magnitude.length == 0 ? getZERO() : new BigInt(new BigInteger(sign, magnitude));
        }
    }

    static {
        BigInteger ZERO2 = BigInteger.ZERO;
        Intrinsics.checkNotNullExpressionValue(ZERO2, "ZERO");
        ZERO = new BigInt(ZERO2);
    }

    public final int getSign() {
        return this.javaBigInteger.signum();
    }

    public final BigInt getAbsoluteValue() {
        if (getSign() >= 0) {
            return this;
        }
        BigInteger bigIntegerAbs = this.javaBigInteger.abs();
        Intrinsics.checkNotNullExpressionValue(bigIntegerAbs, "abs(...)");
        return new BigInt(bigIntegerAbs);
    }

    /* JADX WARN: Code duplicated, block: B:11:0x001d A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:12:0x001e  */
    public final byte[] magnitudeToByteArray() {
        byte[] byteArray = this.javaBigInteger.abs().toByteArray();
        Intrinsics.checkNotNull(byteArray);
        int length = byteArray.length;
        int i = 0;
        while (i < length) {
            if (byteArray[i] != 0) {
                if (i == -1) {
                    return byteArray;
                }
                return ArraysKt.copyOfRange(byteArray, i, byteArray.length);
            }
            i++;
        }
        i = -1;
        if (i == -1) {
            return byteArray;
        }
        return ArraysKt.copyOfRange(byteArray, i, byteArray.length);
    }

    public final BigInt unaryMinus() {
        BigInteger bigIntegerNegate = this.javaBigInteger.negate();
        Intrinsics.checkNotNullExpressionValue(bigIntegerNegate, "negate(...)");
        return new BigInt(bigIntegerNegate);
    }

    public final int compareTo(byte other) {
        return compareTo(BigIntKt.toBigInt(other));
    }

    public final int compareTo(short other) {
        return compareTo(BigIntKt.toBigInt(other));
    }

    public final int compareTo(int other) {
        return compareTo(BigIntKt.toBigInt(other));
    }

    public final int compareTo(long other) {
        return compareTo(BigIntKt.toBigInt(other));
    }

    @Override // java.lang.Comparable
    public int compareTo(BigInt other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return this.javaBigInteger.compareTo(other.javaBigInteger);
    }

    public byte toByte() {
        return this.javaBigInteger.byteValue();
    }

    public short toShort() {
        return this.javaBigInteger.shortValue();
    }

    public int toInt() {
        return this.javaBigInteger.intValue();
    }

    public long toLong() {
        return this.javaBigInteger.longValue();
    }

    public float toFloat() {
        return this.javaBigInteger.floatValue();
    }

    public double toDouble() {
        return this.javaBigInteger.doubleValue();
    }

    public String toString() {
        String string = this.javaBigInteger.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    public int hashCode() {
        return this.javaBigInteger.hashCode();
    }

    public boolean equals(Object other) {
        if (other instanceof BigInt) {
            return Intrinsics.areEqual(this.javaBigInteger, ((BigInt) other).javaBigInteger);
        }
        return false;
    }
}
