package com.fasterxml.jackson.module.kotlin;

import java.math.BigInteger;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UnsignedNumbers.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\u001a\u000f\u0010\u0002\u001a\u0004\u0018\u00010\u0003*\u00020\u0004ø\u0001\u0000\u001a\u000f\u0010\u0005\u001a\u0004\u0018\u00010\u0006*\u00020\u0007ø\u0001\u0000\u001a\u000f\u0010\b\u001a\u0004\u0018\u00010\t*\u00020\u0001ø\u0001\u0000\u001a\u000f\u0010\n\u001a\u0004\u0018\u00010\u000b*\u00020\fø\u0001\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, d2 = {"uLongMaxValue", "Ljava/math/BigInteger;", "asUByte", "Lkotlin/UByte;", "", "asUInt", "Lkotlin/UInt;", "", "asULong", "Lkotlin/ULong;", "asUShort", "Lkotlin/UShort;", "", "jackson-module-kotlin"}, k = 2, mv = {1, 5, 1}, xi = 48)
public final class UnsignedNumbersKt {
    private static final BigInteger uLongMaxValue = new BigInteger(UByte$$ExternalSyntheticBackport0.m(-1L, 10));

    public static final UByte asUByte(short s) {
        if (s < 0 || s > 255) {
            return null;
        }
        return UByte.m8948boximpl(UByte.m8954constructorimpl((byte) s));
    }

    public static final UShort asUShort(int i) {
        if (i < 0 || i > 65535) {
            return null;
        }
        return UShort.m9222boximpl(UShort.m9228constructorimpl((short) i));
    }

    public static final UInt asUInt(long j) {
        if (j < 0 || j > 4294967295L) {
            return null;
        }
        return UInt.m9036boximpl(UInt.m9042constructorimpl((int) j));
    }

    public static final ULong asULong(BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "<this>");
        if (bigInteger.compareTo(BigInteger.ZERO) < 0 || bigInteger.compareTo(uLongMaxValue) > 0) {
            return null;
        }
        return ULong.m9115boximpl(ULong.m9121constructorimpl(bigInteger.longValue()));
    }
}
