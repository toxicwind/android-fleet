package dev.whyoleg.cryptography.bigint;

import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BigInt.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0013\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0086\u0080\u0004¢\u0006\u0002\u0010\u0003\u001a\u0013\u0010\u0004\u001a\u00020\u0005*\u00020\u0002H\u0086\u0080\u0004¢\u0006\u0002\u0010\u0006\u001a\u0013\u0010\u0007\u001a\u00020\b*\u00020\u0002H\u0086\u0080\u0004¢\u0006\u0002\u0010\t\u001a\u0013\u0010\n\u001a\u00020\u000b*\u00020\u0002H\u0086\u0080\u0004¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"toUByte", "Lkotlin/UByte;", "Ldev/whyoleg/cryptography/bigint/BigInt;", "(Ldev/whyoleg/cryptography/bigint/BigInt;)B", "toUShort", "Lkotlin/UShort;", "(Ldev/whyoleg/cryptography/bigint/BigInt;)S", "toUInt", "Lkotlin/UInt;", "(Ldev/whyoleg/cryptography/bigint/BigInt;)I", "toULong", "Lkotlin/ULong;", "(Ldev/whyoleg/cryptography/bigint/BigInt;)J", "cryptography-bigint"}, k = 5, mv = {2, 3, 0}, xi = 48, xs = "dev/whyoleg/cryptography/bigint/BigIntKt")
final /* synthetic */ class BigIntKt__BigIntKt {
    public static final byte toUByte(BigInt bigInt) {
        Intrinsics.checkNotNullParameter(bigInt, "<this>");
        return UByte.m8954constructorimpl(bigInt.byteValue());
    }

    public static final short toUShort(BigInt bigInt) {
        Intrinsics.checkNotNullParameter(bigInt, "<this>");
        return UShort.m9228constructorimpl(bigInt.shortValue());
    }

    public static final int toUInt(BigInt bigInt) {
        Intrinsics.checkNotNullParameter(bigInt, "<this>");
        return UInt.m9042constructorimpl(bigInt.intValue());
    }

    public static final long toULong(BigInt bigInt) {
        Intrinsics.checkNotNullParameter(bigInt, "<this>");
        return ULong.m9121constructorimpl(bigInt.longValue());
    }
}
