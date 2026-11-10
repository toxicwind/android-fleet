package com.google.common.hash;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.Immutable;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.zip.Adler32;
import java.util.zip.CRC32;
import java.util.zip.Checksum;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes.dex */
@Beta
public final class Hashing {
    public static final int GOOD_FAST_HASH_SEED = (int) System.currentTimeMillis();

    private static final class ConcatenatedHashFunction extends AbstractCompositeHashFunction {
        @Override // com.google.common.hash.HashFunction
        public int bits() {
            int iBits = 0;
            for (HashFunction hashFunction : this.functions) {
                iBits += hashFunction.bits();
            }
            return iBits;
        }

        public boolean equals(Object obj) {
            if (obj instanceof ConcatenatedHashFunction) {
                return Arrays.equals(this.functions, ((ConcatenatedHashFunction) obj).functions);
            }
            return false;
        }

        public int hashCode() {
            return Arrays.hashCode(this.functions);
        }

        @Override // com.google.common.hash.AbstractCompositeHashFunction
        public HashCode makeHash(Hasher[] hasherArr) {
            byte[] bArr = new byte[bits() / 8];
            int iWriteBytesTo = 0;
            for (Hasher hasher : hasherArr) {
                HashCode hashCodeHash = hasher.hash();
                iWriteBytesTo += hashCodeHash.writeBytesTo(bArr, iWriteBytesTo, hashCodeHash.bits() / 8);
            }
            return HashCode.fromBytesNoCopy(bArr);
        }

        public ConcatenatedHashFunction(HashFunction... hashFunctionArr) {
            super(hashFunctionArr);
            for (HashFunction hashFunction : hashFunctionArr) {
                Preconditions.checkArgument(hashFunction.bits() % 8 == 0, "the number of bits (%s) in hashFunction (%s) must be divisible by 8", hashFunction.bits(), (Object) hashFunction);
            }
        }
    }

    private static final class LinearCongruentialGenerator {
        public long state;

        public LinearCongruentialGenerator(long j) {
            this.state = j;
        }

        public double nextDouble() {
            this.state = (this.state * 2862933555777941757L) + 1;
            return ((double) (((int) (this.state >>> 33)) + 1)) / 2.147483648E9d;
        }
    }

    private static class Md5Holder {
        public static final HashFunction MD5 = new MessageDigestHashFunction("MD5", "Hashing.md5()");
    }

    private static class Sha1Holder {
        public static final HashFunction SHA_1 = new MessageDigestHashFunction("SHA-1", "Hashing.sha1()");
    }

    private static class Sha256Holder {
        public static final HashFunction SHA_256 = new MessageDigestHashFunction("SHA-256", "Hashing.sha256()");
    }

    private static class Sha384Holder {
        public static final HashFunction SHA_384 = new MessageDigestHashFunction("SHA-384", "Hashing.sha384()");
    }

    private static class Sha512Holder {
        public static final HashFunction SHA_512 = new MessageDigestHashFunction("SHA-512", "Hashing.sha512()");
    }

    public static HashFunction adler32() {
        return ChecksumType.ADLER_32.hashFunction;
    }

    public static int checkPositiveAndMakeMultipleOf32(int i) {
        Preconditions.checkArgument(i > 0, "Number of bits must be positive");
        return (i + 31) & (-32);
    }

    public static HashCode combineOrdered(Iterable<HashCode> iterable) {
        Iterator<HashCode> it = iterable.iterator();
        Preconditions.checkArgument(it.hasNext(), "Must be at least 1 hash code to combine.");
        byte[] bArr = new byte[it.next().bits() / 8];
        Iterator<HashCode> it2 = iterable.iterator();
        while (it2.hasNext()) {
            byte[] bArrAsBytes = it2.next().asBytes();
            Preconditions.checkArgument(bArrAsBytes.length == bArr.length, "All hashcodes must have the same bit length.");
            for (int i = 0; i < bArrAsBytes.length; i++) {
                bArr[i] = (byte) ((bArr[i] * 37) ^ bArrAsBytes[i]);
            }
        }
        return HashCode.fromBytesNoCopy(bArr);
    }

    public static HashCode combineUnordered(Iterable<HashCode> iterable) {
        Iterator<HashCode> it = iterable.iterator();
        Preconditions.checkArgument(it.hasNext(), "Must be at least 1 hash code to combine.");
        byte[] bArr = new byte[it.next().bits() / 8];
        Iterator<HashCode> it2 = iterable.iterator();
        while (it2.hasNext()) {
            byte[] bArrAsBytes = it2.next().asBytes();
            Preconditions.checkArgument(bArrAsBytes.length == bArr.length, "All hashcodes must have the same bit length.");
            for (int i = 0; i < bArrAsBytes.length; i++) {
                bArr[i] = (byte) (bArr[i] + bArrAsBytes[i]);
            }
        }
        return HashCode.fromBytesNoCopy(bArr);
    }

    public static HashFunction concatenating(HashFunction hashFunction, HashFunction hashFunction2, HashFunction... hashFunctionArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(hashFunction);
        arrayList.add(hashFunction2);
        arrayList.addAll(Arrays.asList(hashFunctionArr));
        return new ConcatenatedHashFunction((HashFunction[]) arrayList.toArray(new HashFunction[0]));
    }

    public static int consistentHash(HashCode hashCode, int i) {
        return consistentHash(hashCode.padToLong(), i);
    }

    public static HashFunction crc32() {
        return ChecksumType.CRC_32.hashFunction;
    }

    public static HashFunction crc32c() {
        return Crc32cHashFunction.CRC_32_C;
    }

    public static HashFunction farmHashFingerprint64() {
        return FarmHashFingerprint64.FARMHASH_FINGERPRINT_64;
    }

    public static HashFunction goodFastHash(int i) {
        int iCheckPositiveAndMakeMultipleOf32 = checkPositiveAndMakeMultipleOf32(i);
        if (iCheckPositiveAndMakeMultipleOf32 == 32) {
            return Murmur3_32HashFunction.GOOD_FAST_HASH_32;
        }
        if (iCheckPositiveAndMakeMultipleOf32 <= 128) {
            return Murmur3_128HashFunction.GOOD_FAST_HASH_128;
        }
        int i2 = (iCheckPositiveAndMakeMultipleOf32 + 127) / 128;
        HashFunction[] hashFunctionArr = new HashFunction[i2];
        hashFunctionArr[0] = Murmur3_128HashFunction.GOOD_FAST_HASH_128;
        int i3 = GOOD_FAST_HASH_SEED;
        for (int i4 = 1; i4 < i2; i4++) {
            i3 += 1500450271;
            hashFunctionArr[i4] = new Murmur3_128HashFunction(i3);
        }
        return new ConcatenatedHashFunction(hashFunctionArr);
    }

    public static HashFunction hmacMd5(Key key) {
        return new MacHashFunction("HmacMD5", key, hmacToString("hmacMd5", key));
    }

    public static HashFunction hmacSha1(Key key) {
        return new MacHashFunction("HmacSHA1", key, hmacToString("hmacSha1", key));
    }

    public static HashFunction hmacSha256(Key key) {
        return new MacHashFunction("HmacSHA256", key, hmacToString("hmacSha256", key));
    }

    public static HashFunction hmacSha512(Key key) {
        return new MacHashFunction("HmacSHA512", key, hmacToString("hmacSha512", key));
    }

    public static String hmacToString(String str, Key key) {
        return String.format("Hashing.%s(Key[algorithm=%s, format=%s])", str, key.getAlgorithm(), key.getFormat());
    }

    @Deprecated
    public static HashFunction md5() {
        return Md5Holder.MD5;
    }

    public static HashFunction murmur3_128(int i) {
        return new Murmur3_128HashFunction(i);
    }

    public static HashFunction murmur3_32(int i) {
        return new Murmur3_32HashFunction(i);
    }

    @Deprecated
    public static HashFunction sha1() {
        return Sha1Holder.SHA_1;
    }

    public static HashFunction sha256() {
        return Sha256Holder.SHA_256;
    }

    public static HashFunction sha384() {
        return Sha384Holder.SHA_384;
    }

    public static HashFunction sha512() {
        return Sha512Holder.SHA_512;
    }

    public static HashFunction sipHash24() {
        return SipHashFunction.SIP_HASH_24;
    }

    @Immutable
    enum ChecksumType implements ImmutableSupplier<Checksum> {
        CRC_32("Hashing.crc32()") { // from class: com.google.common.hash.Hashing.ChecksumType.1
            @Override // com.google.common.base.Supplier
            public Checksum get() {
                return new CRC32();
            }
        },
        ADLER_32("Hashing.adler32()") { // from class: com.google.common.hash.Hashing.ChecksumType.2
            @Override // com.google.common.base.Supplier
            public Checksum get() {
                return new Adler32();
            }
        };

        public final HashFunction hashFunction;

        ChecksumType(String str) {
            this.hashFunction = new ChecksumHashFunction(this, 32, str);
        }

        /* synthetic */ ChecksumType(String str, AnonymousClass1 anonymousClass1) {
            this.hashFunction = new ChecksumHashFunction(this, 32, str);
        }
    }

    public static int consistentHash(long j, int i) {
        int i2 = 0;
        Preconditions.checkArgument(i > 0, "buckets must be positive: %s", i);
        while (true) {
            j = (j * 2862933555777941757L) + 1;
            int i3 = (int) (((double) (i2 + 1)) / (((double) (((int) (j >>> 33)) + 1)) / 2.147483648E9d));
            if (i3 < 0 || i3 >= i) {
                break;
            }
            i2 = i3;
        }
        return i2;
    }

    public static HashFunction hmacMd5(byte[] bArr) {
        if (bArr != null) {
            return hmacMd5(new SecretKeySpec(bArr, "HmacMD5"));
        }
        throw new NullPointerException();
    }

    public static HashFunction hmacSha1(byte[] bArr) {
        if (bArr != null) {
            return hmacSha1(new SecretKeySpec(bArr, "HmacSHA1"));
        }
        throw new NullPointerException();
    }

    public static HashFunction hmacSha256(byte[] bArr) {
        if (bArr != null) {
            return hmacSha256(new SecretKeySpec(bArr, "HmacSHA256"));
        }
        throw new NullPointerException();
    }

    public static HashFunction hmacSha512(byte[] bArr) {
        if (bArr != null) {
            return hmacSha512(new SecretKeySpec(bArr, "HmacSHA512"));
        }
        throw new NullPointerException();
    }

    public static HashFunction murmur3_128() {
        return Murmur3_128HashFunction.MURMUR3_128;
    }

    public static HashFunction murmur3_32() {
        return Murmur3_32HashFunction.MURMUR3_32;
    }

    public static HashFunction sipHash24(long j, long j2) {
        return new SipHashFunction(2, 4, j, j2);
    }

    public static HashFunction concatenating(Iterable<HashFunction> iterable) {
        if (iterable != null) {
            ArrayList arrayList = new ArrayList();
            Iterator<HashFunction> it = iterable.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next());
            }
            Preconditions.checkArgument(arrayList.size() > 0, "number of hash functions (%s) must be > 0", arrayList.size());
            return new ConcatenatedHashFunction((HashFunction[]) arrayList.toArray(new HashFunction[0]));
        }
        throw new NullPointerException();
    }
}
