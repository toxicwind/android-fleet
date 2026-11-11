package dev.whyoleg.cryptography.bigint;

import java.math.BigInteger;
import kotlin.Metadata;
import kotlin.text.HexFormat;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"dev/whyoleg/cryptography/bigint/BigIntKt__BigIntKt", "dev/whyoleg/cryptography/bigint/BigIntKt__BigInt_jvmKt", "dev/whyoleg/cryptography/bigint/BigIntKt__HexKt"}, k = 4, mv = {2, 3, 0}, xi = 48)
public final class BigIntKt {
    public static final BigInt decodeToBigInt(byte[] bArr) {
        return BigIntKt__BigInt_jvmKt.decodeToBigInt(bArr);
    }

    public static final byte[] encodeToByteArray(BigInt bigInt) {
        return BigIntKt__BigInt_jvmKt.encodeToByteArray(bigInt);
    }

    public static final BigInt hexToBigInt(String str, HexFormat hexFormat) {
        return BigIntKt__HexKt.hexToBigInt(str, hexFormat);
    }

    public static final BigInt toBigInt(byte b) {
        return BigIntKt__BigInt_jvmKt.toBigInt(b);
    }

    public static final BigInt toBigInt(int i) {
        return BigIntKt__BigInt_jvmKt.toBigInt(i);
    }

    public static final BigInt toBigInt(long j) {
        return BigIntKt__BigInt_jvmKt.toBigInt(j);
    }

    public static final BigInt toBigInt(String str) {
        return BigIntKt__BigInt_jvmKt.toBigInt(str);
    }

    public static final BigInt toBigInt(short s) {
        return BigIntKt__BigInt_jvmKt.toBigInt(s);
    }

    /* JADX INFO: renamed from: toBigInt-7apg3OU, reason: not valid java name */
    public static final BigInt m8727toBigInt7apg3OU(byte b) {
        return BigIntKt__BigInt_jvmKt.m8731toBigInt7apg3OU(b);
    }

    /* JADX INFO: renamed from: toBigInt-VKZWuLQ, reason: not valid java name */
    public static final BigInt m8728toBigIntVKZWuLQ(long j) {
        return BigIntKt__BigInt_jvmKt.m8732toBigIntVKZWuLQ(j);
    }

    /* JADX INFO: renamed from: toBigInt-WZ4Q5Ns, reason: not valid java name */
    public static final BigInt m8729toBigIntWZ4Q5Ns(int i) {
        return BigIntKt__BigInt_jvmKt.m8733toBigIntWZ4Q5Ns(i);
    }

    /* JADX INFO: renamed from: toBigInt-xj2QHRw, reason: not valid java name */
    public static final BigInt m8730toBigIntxj2QHRw(short s) {
        return BigIntKt__BigInt_jvmKt.m8734toBigIntxj2QHRw(s);
    }

    public static final BigInt toBigIntOrNull(String str) {
        return BigIntKt__BigInt_jvmKt.toBigIntOrNull(str);
    }

    public static final String toHexString(BigInt bigInt, HexFormat hexFormat) {
        return BigIntKt__HexKt.toHexString(bigInt, hexFormat);
    }

    public static final BigInteger toJavaBigInteger(BigInt bigInt) {
        return BigIntKt__BigInt_jvmKt.toJavaBigInteger(bigInt);
    }

    public static final BigInt toKotlinBigInt(BigInteger bigInteger) {
        return BigIntKt__BigInt_jvmKt.toKotlinBigInt(bigInteger);
    }

    public static final byte toUByte(BigInt bigInt) {
        return BigIntKt__BigIntKt.toUByte(bigInt);
    }

    public static final int toUInt(BigInt bigInt) {
        return BigIntKt__BigIntKt.toUInt(bigInt);
    }

    public static final long toULong(BigInt bigInt) {
        return BigIntKt__BigIntKt.toULong(bigInt);
    }

    public static final short toUShort(BigInt bigInt) {
        return BigIntKt__BigIntKt.toUShort(bigInt);
    }
}
