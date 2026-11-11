package dev.whyoleg.cryptography.bigint;

import java.math.BigInteger;
import kotlin.Metadata;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.internal.ws.WebSocketProtocol;

/* JADX INFO: compiled from: BigInt.jvm.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000N\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0002\u0010\n\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0086\u0080\u0004\u001a\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u0003H\u0086\u0080\u0004\u001a\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u0004H\u0086\u0080\u0004\u001a\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u0005H\u0086\u0080\u0004\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u0006H\u0086\u0080\u0004¢\u0006\u0004\b\u0007\u0010\b\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\tH\u0086\u0080\u0004¢\u0006\u0004\b\n\u0010\u000b\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\fH\u0086\u0080\u0004¢\u0006\u0004\b\r\u0010\u000e\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u000fH\u0086\u0080\u0004¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u0012H\u0086\u0080\u0004\u001a\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0001*\u00020\u0012H\u0086\u0080\u0004\u001a\u000e\u0010\u0014\u001a\u00020\u0001*\u00020\u0015H\u0086\u0080\u0004\u001a\u000e\u0010\u0016\u001a\u00020\u0015*\u00020\u0001H\u0086\u0080\u0004\u001a\u000e\u0010\u0017\u001a\u00020\u0018*\u00020\u0001H\u0086\u0080\u0004\u001a\u000e\u0010\u0019\u001a\u00020\u0001*\u00020\u0018H\u0086\u0080\u0004¨\u0006\u001a"}, d2 = {"toBigInt", "Ldev/whyoleg/cryptography/bigint/BigInt;", "", "", "", "", "Lkotlin/UByte;", "toBigInt-7apg3OU", "(B)Ldev/whyoleg/cryptography/bigint/BigInt;", "Lkotlin/UShort;", "toBigInt-xj2QHRw", "(S)Ldev/whyoleg/cryptography/bigint/BigInt;", "Lkotlin/UInt;", "toBigInt-WZ4Q5Ns", "(I)Ldev/whyoleg/cryptography/bigint/BigInt;", "Lkotlin/ULong;", "toBigInt-VKZWuLQ", "(J)Ldev/whyoleg/cryptography/bigint/BigInt;", "", "toBigIntOrNull", "decodeToBigInt", "", "encodeToByteArray", "toJavaBigInteger", "Ljava/math/BigInteger;", "toKotlinBigInt", "cryptography-bigint"}, k = 5, mv = {2, 3, 0}, xi = 48, xs = "dev/whyoleg/cryptography/bigint/BigIntKt")
final /* synthetic */ class BigIntKt__BigInt_jvmKt {
    public static final BigInt toBigInt(byte b) {
        return BigIntKt.toBigInt(b);
    }

    public static final BigInt toBigInt(short s) {
        return BigIntKt.toBigInt(s);
    }

    public static final BigInt toBigInt(int i) {
        return BigIntKt.toBigInt(i);
    }

    public static final BigInt toBigInt(long j) {
        if (j == 0) {
            return BigInt.INSTANCE.getZERO();
        }
        BigInteger bigIntegerValueOf = BigInteger.valueOf(j);
        Intrinsics.checkNotNullExpressionValue(bigIntegerValueOf, "valueOf(...)");
        return new BigInt(bigIntegerValueOf);
    }

    /* JADX INFO: renamed from: toBigInt-7apg3OU, reason: not valid java name */
    public static final BigInt m8731toBigInt7apg3OU(byte b) {
        return BigIntKt.toBigInt(((long) b) & 255);
    }

    /* JADX INFO: renamed from: toBigInt-xj2QHRw, reason: not valid java name */
    public static final BigInt m8734toBigIntxj2QHRw(short s) {
        return BigIntKt.toBigInt(((long) s) & WebSocketProtocol.PAYLOAD_SHORT_MAX);
    }

    /* JADX INFO: renamed from: toBigInt-WZ4Q5Ns, reason: not valid java name */
    public static final BigInt m8733toBigIntWZ4Q5Ns(int i) {
        return BigIntKt.toBigInt(((long) i) & 4294967295L);
    }

    /* JADX INFO: renamed from: toBigInt-VKZWuLQ, reason: not valid java name */
    public static final BigInt m8732toBigIntVKZWuLQ(long j) {
        if (j == 0) {
            return BigInt.INSTANCE.getZERO();
        }
        return new BigInt(new BigInteger(UByte$$ExternalSyntheticBackport0.m(j, 10)));
    }

    public static final BigInt toBigInt(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return new BigInt(new BigInteger(str));
    }

    public static final BigInt toBigIntOrNull(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        BigInteger bigIntegerOrNull = StringsKt.toBigIntegerOrNull(str);
        if (bigIntegerOrNull != null) {
            return new BigInt(bigIntegerOrNull);
        }
        return null;
    }

    public static final BigInt decodeToBigInt(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return new BigInt(new BigInteger(bArr));
    }

    public static final byte[] encodeToByteArray(BigInt bigInt) {
        Intrinsics.checkNotNullParameter(bigInt, "<this>");
        byte[] byteArray = bigInt.javaBigInteger.toByteArray();
        Intrinsics.checkNotNullExpressionValue(byteArray, "toByteArray(...)");
        return byteArray;
    }

    public static final BigInteger toJavaBigInteger(BigInt bigInt) {
        Intrinsics.checkNotNullParameter(bigInt, "<this>");
        return bigInt.javaBigInteger;
    }

    public static final BigInt toKotlinBigInt(BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "<this>");
        return new BigInt(bigInteger);
    }
}
