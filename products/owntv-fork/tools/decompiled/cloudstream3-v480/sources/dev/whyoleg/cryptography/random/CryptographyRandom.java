package dev.whyoleg.cryptography.random;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;

/* JADX INFO: compiled from: CryptographyRandom.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\t\bF¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Ldev/whyoleg/cryptography/random/CryptographyRandom;", "Lkotlin/random/Random;", "<init>", "()V", "Default", "cryptography-random"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class CryptographyRandom extends Random {

    /* JADX INFO: renamed from: Default, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final CryptographyRandom defaultRandom = CryptographyRandom_jvmKt.defaultCryptographyRandom();

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.random.CryptographyRandom$Default, reason: from kotlin metadata */
    /* JADX INFO: compiled from: CryptographyRandom.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0096\u0080\u0004J\n\u0010\b\u001a\u00020\u0006H\u0096\u0080\u0004J\u0012\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0096\u0080\u0004J\u001a\u0010\b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0096\u0080\u0004J\n\u0010\u000b\u001a\u00020\fH\u0096\u0080\u0004J\u0012\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\fH\u0096\u0080\u0004J\u001a\u0010\u000b\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\fH\u0096\u0080\u0004J\n\u0010\r\u001a\u00020\u000eH\u0096\u0080\u0004J\n\u0010\u000f\u001a\u00020\u0010H\u0096\u0080\u0004J\u0012\u0010\u000f\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0096\u0080\u0004J\u001a\u0010\u000f\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0096\u0080\u0004J\n\u0010\u0011\u001a\u00020\u0012H\u0096\u0080\u0004J\u0012\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0096\u0080\bJ\u0012\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0006H\u0096\u0080\u0004J\"\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u0006H\u0096\u0080\bR\u000f\u0010\u0004\u001a\u00020\u0001X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Ldev/whyoleg/cryptography/random/CryptographyRandom$Default;", "Ldev/whyoleg/cryptography/random/CryptographyRandom;", "<init>", "()V", "defaultRandom", "nextBits", "", "bitCount", "nextInt", "until", TypedValues.TransitionType.S_FROM, "nextLong", "", "nextBoolean", "", "nextDouble", "", "nextFloat", "", "nextBytes", "", "array", ContentDisposition.Parameters.Size, "fromIndex", "toIndex", "cryptography-random"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion extends CryptographyRandom {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @Override // kotlin.random.Random
        public int nextBits(int bitCount) {
            return CryptographyRandom.defaultRandom.nextBits(bitCount);
        }

        @Override // kotlin.random.Random
        public int nextInt() {
            return CryptographyRandom.defaultRandom.nextInt();
        }

        @Override // kotlin.random.Random
        public int nextInt(int until) {
            return CryptographyRandom.defaultRandom.nextInt(until);
        }

        @Override // kotlin.random.Random
        public int nextInt(int from, int until) {
            return CryptographyRandom.defaultRandom.nextInt(from, until);
        }

        @Override // kotlin.random.Random
        public long nextLong() {
            return CryptographyRandom.defaultRandom.nextLong();
        }

        @Override // kotlin.random.Random
        public long nextLong(long until) {
            return CryptographyRandom.defaultRandom.nextLong(until);
        }

        @Override // kotlin.random.Random
        public long nextLong(long from, long until) {
            return CryptographyRandom.defaultRandom.nextLong(from, until);
        }

        @Override // kotlin.random.Random
        public boolean nextBoolean() {
            return CryptographyRandom.defaultRandom.nextBoolean();
        }

        @Override // kotlin.random.Random
        public double nextDouble() {
            return CryptographyRandom.defaultRandom.nextDouble();
        }

        @Override // kotlin.random.Random
        public double nextDouble(double until) {
            return CryptographyRandom.defaultRandom.nextDouble(until);
        }

        @Override // kotlin.random.Random
        public double nextDouble(double from, double until) {
            return CryptographyRandom.defaultRandom.nextDouble(from, until);
        }

        @Override // kotlin.random.Random
        public float nextFloat() {
            return CryptographyRandom.defaultRandom.nextFloat();
        }

        @Override // kotlin.random.Random
        public byte[] nextBytes(byte[] array) {
            Intrinsics.checkNotNullParameter(array, "array");
            return CryptographyRandom.defaultRandom.nextBytes(array);
        }

        @Override // kotlin.random.Random
        public byte[] nextBytes(int size) {
            return CryptographyRandom.defaultRandom.nextBytes(size);
        }

        @Override // kotlin.random.Random
        public byte[] nextBytes(byte[] array, int fromIndex, int toIndex) {
            Intrinsics.checkNotNullParameter(array, "array");
            return CryptographyRandom.defaultRandom.nextBytes(array, fromIndex, toIndex);
        }
    }
}
