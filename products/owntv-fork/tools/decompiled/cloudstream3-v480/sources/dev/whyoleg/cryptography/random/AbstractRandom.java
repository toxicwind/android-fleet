package dev.whyoleg.cryptography.random;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AbstractRandom.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b \u0018\u00002\u00020\u0001B\t\bF¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0086\u0080\u0004J\u0012\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0086\u0080\bJ\u0012\u0010\n\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\bH¤\u0080\u0004¨\u0006\f"}, d2 = {"Ldev/whyoleg/cryptography/random/AbstractRandom;", "Ldev/whyoleg/cryptography/random/CryptographyRandom;", "<init>", "()V", "nextBits", "", "bitCount", "nextBytes", "", "array", "fillBytes", "", "cryptography-random"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class AbstractRandom extends CryptographyRandom {
    protected abstract void fillBytes(byte[] array);

    @Override // kotlin.random.Random
    public final int nextBits(int bitCount) {
        int i = (bitCount + 7) / 8;
        byte[] bArrNextBytes = nextBytes(i);
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 = (i2 << 8) + (bArrNextBytes[i3] & 255);
        }
        return i2 >>> ((i * 8) - bitCount);
    }

    @Override // kotlin.random.Random
    public final byte[] nextBytes(byte[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        if (!(array.length == 0)) {
            fillBytes(array);
        }
        return array;
    }
}
